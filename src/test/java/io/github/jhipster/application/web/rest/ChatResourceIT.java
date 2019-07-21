package io.github.jhipster.application.web.rest;

import io.github.jhipster.application.NewoApp9UaaApp;
import io.github.jhipster.application.config.TestSecurityConfiguration;
import io.github.jhipster.application.domain.Chat;
import io.github.jhipster.application.domain.User;
import io.github.jhipster.application.repository.ChatRepository;
import io.github.jhipster.application.repository.search.ChatSearchRepository;
import io.github.jhipster.application.service.ChatService;
import io.github.jhipster.application.service.dto.ChatDTO;
import io.github.jhipster.application.service.mapper.ChatMapper;
import io.github.jhipster.application.web.rest.errors.ExceptionTranslator;
import io.github.jhipster.application.service.dto.ChatCriteria;
import io.github.jhipster.application.service.ChatQueryService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.kafka.test.context.EmbeddedKafka;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.Validator;

import javax.persistence.EntityManager;
import java.time.Instant;
import java.time.ZonedDateTime;
import java.time.ZoneOffset;
import java.time.ZoneId;
import java.util.Collections;
import java.util.List;

import static io.github.jhipster.application.web.rest.TestUtil.sameInstant;
import static io.github.jhipster.application.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.elasticsearch.index.query.QueryBuilders.queryStringQuery;
import static org.hamcrest.Matchers.hasItem;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the {@Link ChatResource} REST controller.
 */
@EmbeddedKafka
@SpringBootTest(classes = {NewoApp9UaaApp.class, TestSecurityConfiguration.class})
public class ChatResourceIT {

    private static final String DEFAULT_MENSAJE = "AAAAAAAAAA";
    private static final String UPDATED_MENSAJE = "BBBBBBBBBB";

    private static final ZonedDateTime DEFAULT_FECHA = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_FECHA = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final Boolean DEFAULT_LEIDO = false;
    private static final Boolean UPDATED_LEIDO = true;

    @Autowired
    private ChatRepository chatRepository;

    @Autowired
    private ChatMapper chatMapper;

    @Autowired
    private ChatService chatService;

    /**
     * This repository is mocked in the io.github.jhipster.application.repository.search test package.
     *
     * @see io.github.jhipster.application.repository.search.ChatSearchRepositoryMockConfiguration
     */
    @Autowired
    private ChatSearchRepository mockChatSearchRepository;

    @Autowired
    private ChatQueryService chatQueryService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    @Autowired
    private Validator validator;

    private MockMvc restChatMockMvc;

    private Chat chat;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final ChatResource chatResource = new ChatResource(chatService, chatQueryService);
        this.restChatMockMvc = MockMvcBuilders.standaloneSetup(chatResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setConversionService(createFormattingConversionService())
            .setMessageConverters(jacksonMessageConverter)
            .setValidator(validator).build();
    }

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Chat createEntity(EntityManager em) {
        Chat chat = new Chat()
            .mensaje(DEFAULT_MENSAJE)
            .fecha(DEFAULT_FECHA)
            .leido(DEFAULT_LEIDO);
        return chat;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Chat createUpdatedEntity(EntityManager em) {
        Chat chat = new Chat()
            .mensaje(UPDATED_MENSAJE)
            .fecha(UPDATED_FECHA)
            .leido(UPDATED_LEIDO);
        return chat;
    }

    @BeforeEach
    public void initTest() {
        chat = createEntity(em);
    }

    @Test
    @Transactional
    public void createChat() throws Exception {
        int databaseSizeBeforeCreate = chatRepository.findAll().size();

        // Create the Chat
        ChatDTO chatDTO = chatMapper.toDto(chat);
        restChatMockMvc.perform(post("/api/chats")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(chatDTO)))
            .andExpect(status().isCreated());

        // Validate the Chat in the database
        List<Chat> chatList = chatRepository.findAll();
        assertThat(chatList).hasSize(databaseSizeBeforeCreate + 1);
        Chat testChat = chatList.get(chatList.size() - 1);
        assertThat(testChat.getMensaje()).isEqualTo(DEFAULT_MENSAJE);
        assertThat(testChat.getFecha()).isEqualTo(DEFAULT_FECHA);
        assertThat(testChat.isLeido()).isEqualTo(DEFAULT_LEIDO);

        // Validate the Chat in Elasticsearch
        verify(mockChatSearchRepository, times(1)).save(testChat);
    }

    @Test
    @Transactional
    public void createChatWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = chatRepository.findAll().size();

        // Create the Chat with an existing ID
        chat.setId(1L);
        ChatDTO chatDTO = chatMapper.toDto(chat);

        // An entity with an existing ID cannot be created, so this API call must fail
        restChatMockMvc.perform(post("/api/chats")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(chatDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Chat in the database
        List<Chat> chatList = chatRepository.findAll();
        assertThat(chatList).hasSize(databaseSizeBeforeCreate);

        // Validate the Chat in Elasticsearch
        verify(mockChatSearchRepository, times(0)).save(chat);
    }


    @Test
    @Transactional
    public void checkMensajeIsRequired() throws Exception {
        int databaseSizeBeforeTest = chatRepository.findAll().size();
        // set the field null
        chat.setMensaje(null);

        // Create the Chat, which fails.
        ChatDTO chatDTO = chatMapper.toDto(chat);

        restChatMockMvc.perform(post("/api/chats")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(chatDTO)))
            .andExpect(status().isBadRequest());

        List<Chat> chatList = chatRepository.findAll();
        assertThat(chatList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllChats() throws Exception {
        // Initialize the database
        chatRepository.saveAndFlush(chat);

        // Get all the chatList
        restChatMockMvc.perform(get("/api/chats?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(chat.getId().intValue())))
            .andExpect(jsonPath("$.[*].mensaje").value(hasItem(DEFAULT_MENSAJE.toString())))
            .andExpect(jsonPath("$.[*].fecha").value(hasItem(sameInstant(DEFAULT_FECHA))))
            .andExpect(jsonPath("$.[*].leido").value(hasItem(DEFAULT_LEIDO.booleanValue())));
    }
    
    @Test
    @Transactional
    public void getChat() throws Exception {
        // Initialize the database
        chatRepository.saveAndFlush(chat);

        // Get the chat
        restChatMockMvc.perform(get("/api/chats/{id}", chat.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(chat.getId().intValue()))
            .andExpect(jsonPath("$.mensaje").value(DEFAULT_MENSAJE.toString()))
            .andExpect(jsonPath("$.fecha").value(sameInstant(DEFAULT_FECHA)))
            .andExpect(jsonPath("$.leido").value(DEFAULT_LEIDO.booleanValue()));
    }

    @Test
    @Transactional
    public void getAllChatsByMensajeIsEqualToSomething() throws Exception {
        // Initialize the database
        chatRepository.saveAndFlush(chat);

        // Get all the chatList where mensaje equals to DEFAULT_MENSAJE
        defaultChatShouldBeFound("mensaje.equals=" + DEFAULT_MENSAJE);

        // Get all the chatList where mensaje equals to UPDATED_MENSAJE
        defaultChatShouldNotBeFound("mensaje.equals=" + UPDATED_MENSAJE);
    }

    @Test
    @Transactional
    public void getAllChatsByMensajeIsInShouldWork() throws Exception {
        // Initialize the database
        chatRepository.saveAndFlush(chat);

        // Get all the chatList where mensaje in DEFAULT_MENSAJE or UPDATED_MENSAJE
        defaultChatShouldBeFound("mensaje.in=" + DEFAULT_MENSAJE + "," + UPDATED_MENSAJE);

        // Get all the chatList where mensaje equals to UPDATED_MENSAJE
        defaultChatShouldNotBeFound("mensaje.in=" + UPDATED_MENSAJE);
    }

    @Test
    @Transactional
    public void getAllChatsByMensajeIsNullOrNotNull() throws Exception {
        // Initialize the database
        chatRepository.saveAndFlush(chat);

        // Get all the chatList where mensaje is not null
        defaultChatShouldBeFound("mensaje.specified=true");

        // Get all the chatList where mensaje is null
        defaultChatShouldNotBeFound("mensaje.specified=false");
    }

    @Test
    @Transactional
    public void getAllChatsByFechaIsEqualToSomething() throws Exception {
        // Initialize the database
        chatRepository.saveAndFlush(chat);

        // Get all the chatList where fecha equals to DEFAULT_FECHA
        defaultChatShouldBeFound("fecha.equals=" + DEFAULT_FECHA);

        // Get all the chatList where fecha equals to UPDATED_FECHA
        defaultChatShouldNotBeFound("fecha.equals=" + UPDATED_FECHA);
    }

    @Test
    @Transactional
    public void getAllChatsByFechaIsInShouldWork() throws Exception {
        // Initialize the database
        chatRepository.saveAndFlush(chat);

        // Get all the chatList where fecha in DEFAULT_FECHA or UPDATED_FECHA
        defaultChatShouldBeFound("fecha.in=" + DEFAULT_FECHA + "," + UPDATED_FECHA);

        // Get all the chatList where fecha equals to UPDATED_FECHA
        defaultChatShouldNotBeFound("fecha.in=" + UPDATED_FECHA);
    }

    @Test
    @Transactional
    public void getAllChatsByFechaIsNullOrNotNull() throws Exception {
        // Initialize the database
        chatRepository.saveAndFlush(chat);

        // Get all the chatList where fecha is not null
        defaultChatShouldBeFound("fecha.specified=true");

        // Get all the chatList where fecha is null
        defaultChatShouldNotBeFound("fecha.specified=false");
    }

    @Test
    @Transactional
    public void getAllChatsByFechaIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        chatRepository.saveAndFlush(chat);

        // Get all the chatList where fecha greater than or equals to DEFAULT_FECHA
        defaultChatShouldBeFound("fecha.greaterOrEqualThan=" + DEFAULT_FECHA);

        // Get all the chatList where fecha greater than or equals to UPDATED_FECHA
        defaultChatShouldNotBeFound("fecha.greaterOrEqualThan=" + UPDATED_FECHA);
    }

    @Test
    @Transactional
    public void getAllChatsByFechaIsLessThanSomething() throws Exception {
        // Initialize the database
        chatRepository.saveAndFlush(chat);

        // Get all the chatList where fecha less than or equals to DEFAULT_FECHA
        defaultChatShouldNotBeFound("fecha.lessThan=" + DEFAULT_FECHA);

        // Get all the chatList where fecha less than or equals to UPDATED_FECHA
        defaultChatShouldBeFound("fecha.lessThan=" + UPDATED_FECHA);
    }


    @Test
    @Transactional
    public void getAllChatsByLeidoIsEqualToSomething() throws Exception {
        // Initialize the database
        chatRepository.saveAndFlush(chat);

        // Get all the chatList where leido equals to DEFAULT_LEIDO
        defaultChatShouldBeFound("leido.equals=" + DEFAULT_LEIDO);

        // Get all the chatList where leido equals to UPDATED_LEIDO
        defaultChatShouldNotBeFound("leido.equals=" + UPDATED_LEIDO);
    }

    @Test
    @Transactional
    public void getAllChatsByLeidoIsInShouldWork() throws Exception {
        // Initialize the database
        chatRepository.saveAndFlush(chat);

        // Get all the chatList where leido in DEFAULT_LEIDO or UPDATED_LEIDO
        defaultChatShouldBeFound("leido.in=" + DEFAULT_LEIDO + "," + UPDATED_LEIDO);

        // Get all the chatList where leido equals to UPDATED_LEIDO
        defaultChatShouldNotBeFound("leido.in=" + UPDATED_LEIDO);
    }

    @Test
    @Transactional
    public void getAllChatsByLeidoIsNullOrNotNull() throws Exception {
        // Initialize the database
        chatRepository.saveAndFlush(chat);

        // Get all the chatList where leido is not null
        defaultChatShouldBeFound("leido.specified=true");

        // Get all the chatList where leido is null
        defaultChatShouldNotBeFound("leido.specified=false");
    }

    @Test
    @Transactional
    public void getAllChatsByDeIsEqualToSomething() throws Exception {
        // Initialize the database
        User de = UserResourceIT.createEntity(em);
        em.persist(de);
        em.flush();
        chat.setDe(de);
        chatRepository.saveAndFlush(chat);
        Long deId = de.getId();

        // Get all the chatList where de equals to deId
        defaultChatShouldBeFound("deId.equals=" + deId);

        // Get all the chatList where de equals to deId + 1
        defaultChatShouldNotBeFound("deId.equals=" + (deId + 1));
    }


    @Test
    @Transactional
    public void getAllChatsByParaIsEqualToSomething() throws Exception {
        // Initialize the database
        User para = UserResourceIT.createEntity(em);
        em.persist(para);
        em.flush();
        chat.setPara(para);
        chatRepository.saveAndFlush(chat);
        Long paraId = para.getId();

        // Get all the chatList where para equals to paraId
        defaultChatShouldBeFound("paraId.equals=" + paraId);

        // Get all the chatList where para equals to paraId + 1
        defaultChatShouldNotBeFound("paraId.equals=" + (paraId + 1));
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultChatShouldBeFound(String filter) throws Exception {
        restChatMockMvc.perform(get("/api/chats?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(chat.getId().intValue())))
            .andExpect(jsonPath("$.[*].mensaje").value(hasItem(DEFAULT_MENSAJE)))
            .andExpect(jsonPath("$.[*].fecha").value(hasItem(sameInstant(DEFAULT_FECHA))))
            .andExpect(jsonPath("$.[*].leido").value(hasItem(DEFAULT_LEIDO.booleanValue())));

        // Check, that the count call also returns 1
        restChatMockMvc.perform(get("/api/chats/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultChatShouldNotBeFound(String filter) throws Exception {
        restChatMockMvc.perform(get("/api/chats?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restChatMockMvc.perform(get("/api/chats/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("0"));
    }


    @Test
    @Transactional
    public void getNonExistingChat() throws Exception {
        // Get the chat
        restChatMockMvc.perform(get("/api/chats/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateChat() throws Exception {
        // Initialize the database
        chatRepository.saveAndFlush(chat);

        int databaseSizeBeforeUpdate = chatRepository.findAll().size();

        // Update the chat
        Chat updatedChat = chatRepository.findById(chat.getId()).get();
        // Disconnect from session so that the updates on updatedChat are not directly saved in db
        em.detach(updatedChat);
        updatedChat
            .mensaje(UPDATED_MENSAJE)
            .fecha(UPDATED_FECHA)
            .leido(UPDATED_LEIDO);
        ChatDTO chatDTO = chatMapper.toDto(updatedChat);

        restChatMockMvc.perform(put("/api/chats")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(chatDTO)))
            .andExpect(status().isOk());

        // Validate the Chat in the database
        List<Chat> chatList = chatRepository.findAll();
        assertThat(chatList).hasSize(databaseSizeBeforeUpdate);
        Chat testChat = chatList.get(chatList.size() - 1);
        assertThat(testChat.getMensaje()).isEqualTo(UPDATED_MENSAJE);
        assertThat(testChat.getFecha()).isEqualTo(UPDATED_FECHA);
        assertThat(testChat.isLeido()).isEqualTo(UPDATED_LEIDO);

        // Validate the Chat in Elasticsearch
        verify(mockChatSearchRepository, times(1)).save(testChat);
    }

    @Test
    @Transactional
    public void updateNonExistingChat() throws Exception {
        int databaseSizeBeforeUpdate = chatRepository.findAll().size();

        // Create the Chat
        ChatDTO chatDTO = chatMapper.toDto(chat);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restChatMockMvc.perform(put("/api/chats")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(chatDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Chat in the database
        List<Chat> chatList = chatRepository.findAll();
        assertThat(chatList).hasSize(databaseSizeBeforeUpdate);

        // Validate the Chat in Elasticsearch
        verify(mockChatSearchRepository, times(0)).save(chat);
    }

    @Test
    @Transactional
    public void deleteChat() throws Exception {
        // Initialize the database
        chatRepository.saveAndFlush(chat);

        int databaseSizeBeforeDelete = chatRepository.findAll().size();

        // Delete the chat
        restChatMockMvc.perform(delete("/api/chats/{id}", chat.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Chat> chatList = chatRepository.findAll();
        assertThat(chatList).hasSize(databaseSizeBeforeDelete - 1);

        // Validate the Chat in Elasticsearch
        verify(mockChatSearchRepository, times(1)).deleteById(chat.getId());
    }

    @Test
    @Transactional
    public void searchChat() throws Exception {
        // Initialize the database
        chatRepository.saveAndFlush(chat);
        when(mockChatSearchRepository.search(queryStringQuery("id:" + chat.getId()), PageRequest.of(0, 20)))
            .thenReturn(new PageImpl<>(Collections.singletonList(chat), PageRequest.of(0, 1), 1));
        // Search the chat
        restChatMockMvc.perform(get("/api/_search/chats?query=id:" + chat.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(chat.getId().intValue())))
            .andExpect(jsonPath("$.[*].mensaje").value(hasItem(DEFAULT_MENSAJE)))
            .andExpect(jsonPath("$.[*].fecha").value(hasItem(sameInstant(DEFAULT_FECHA))))
            .andExpect(jsonPath("$.[*].leido").value(hasItem(DEFAULT_LEIDO.booleanValue())));
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Chat.class);
        Chat chat1 = new Chat();
        chat1.setId(1L);
        Chat chat2 = new Chat();
        chat2.setId(chat1.getId());
        assertThat(chat1).isEqualTo(chat2);
        chat2.setId(2L);
        assertThat(chat1).isNotEqualTo(chat2);
        chat1.setId(null);
        assertThat(chat1).isNotEqualTo(chat2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(ChatDTO.class);
        ChatDTO chatDTO1 = new ChatDTO();
        chatDTO1.setId(1L);
        ChatDTO chatDTO2 = new ChatDTO();
        assertThat(chatDTO1).isNotEqualTo(chatDTO2);
        chatDTO2.setId(chatDTO1.getId());
        assertThat(chatDTO1).isEqualTo(chatDTO2);
        chatDTO2.setId(2L);
        assertThat(chatDTO1).isNotEqualTo(chatDTO2);
        chatDTO1.setId(null);
        assertThat(chatDTO1).isNotEqualTo(chatDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(chatMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(chatMapper.fromId(null)).isNull();
    }
}
