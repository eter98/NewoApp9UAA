package io.github.jhipster.application.web.rest;

import io.github.jhipster.application.NewoApp9UaaApp;
import io.github.jhipster.application.config.TestSecurityConfiguration;
import io.github.jhipster.application.domain.ChatGrupo;
import io.github.jhipster.application.domain.User;
import io.github.jhipster.application.domain.Grupos;
import io.github.jhipster.application.repository.ChatGrupoRepository;
import io.github.jhipster.application.repository.search.ChatGrupoSearchRepository;
import io.github.jhipster.application.service.ChatGrupoService;
import io.github.jhipster.application.service.dto.ChatGrupoDTO;
import io.github.jhipster.application.service.mapper.ChatGrupoMapper;
import io.github.jhipster.application.web.rest.errors.ExceptionTranslator;
import io.github.jhipster.application.service.dto.ChatGrupoCriteria;
import io.github.jhipster.application.service.ChatGrupoQueryService;

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
 * Integration tests for the {@Link ChatGrupoResource} REST controller.
 */
@EmbeddedKafka
@SpringBootTest(classes = {NewoApp9UaaApp.class, TestSecurityConfiguration.class})
public class ChatGrupoResourceIT {

    private static final String DEFAULT_MENSAJE = "AAAAAAAAAA";
    private static final String UPDATED_MENSAJE = "BBBBBBBBBB";

    private static final ZonedDateTime DEFAULT_FECHA = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_FECHA = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final Boolean DEFAULT_LEIDO = false;
    private static final Boolean UPDATED_LEIDO = true;

    @Autowired
    private ChatGrupoRepository chatGrupoRepository;

    @Autowired
    private ChatGrupoMapper chatGrupoMapper;

    @Autowired
    private ChatGrupoService chatGrupoService;

    /**
     * This repository is mocked in the io.github.jhipster.application.repository.search test package.
     *
     * @see io.github.jhipster.application.repository.search.ChatGrupoSearchRepositoryMockConfiguration
     */
    @Autowired
    private ChatGrupoSearchRepository mockChatGrupoSearchRepository;

    @Autowired
    private ChatGrupoQueryService chatGrupoQueryService;

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

    private MockMvc restChatGrupoMockMvc;

    private ChatGrupo chatGrupo;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final ChatGrupoResource chatGrupoResource = new ChatGrupoResource(chatGrupoService, chatGrupoQueryService);
        this.restChatGrupoMockMvc = MockMvcBuilders.standaloneSetup(chatGrupoResource)
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
    public static ChatGrupo createEntity(EntityManager em) {
        ChatGrupo chatGrupo = new ChatGrupo()
            .mensaje(DEFAULT_MENSAJE)
            .fecha(DEFAULT_FECHA)
            .leido(DEFAULT_LEIDO);
        return chatGrupo;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ChatGrupo createUpdatedEntity(EntityManager em) {
        ChatGrupo chatGrupo = new ChatGrupo()
            .mensaje(UPDATED_MENSAJE)
            .fecha(UPDATED_FECHA)
            .leido(UPDATED_LEIDO);
        return chatGrupo;
    }

    @BeforeEach
    public void initTest() {
        chatGrupo = createEntity(em);
    }

    @Test
    @Transactional
    public void createChatGrupo() throws Exception {
        int databaseSizeBeforeCreate = chatGrupoRepository.findAll().size();

        // Create the ChatGrupo
        ChatGrupoDTO chatGrupoDTO = chatGrupoMapper.toDto(chatGrupo);
        restChatGrupoMockMvc.perform(post("/api/chat-grupos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(chatGrupoDTO)))
            .andExpect(status().isCreated());

        // Validate the ChatGrupo in the database
        List<ChatGrupo> chatGrupoList = chatGrupoRepository.findAll();
        assertThat(chatGrupoList).hasSize(databaseSizeBeforeCreate + 1);
        ChatGrupo testChatGrupo = chatGrupoList.get(chatGrupoList.size() - 1);
        assertThat(testChatGrupo.getMensaje()).isEqualTo(DEFAULT_MENSAJE);
        assertThat(testChatGrupo.getFecha()).isEqualTo(DEFAULT_FECHA);
        assertThat(testChatGrupo.isLeido()).isEqualTo(DEFAULT_LEIDO);

        // Validate the ChatGrupo in Elasticsearch
        verify(mockChatGrupoSearchRepository, times(1)).save(testChatGrupo);
    }

    @Test
    @Transactional
    public void createChatGrupoWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = chatGrupoRepository.findAll().size();

        // Create the ChatGrupo with an existing ID
        chatGrupo.setId(1L);
        ChatGrupoDTO chatGrupoDTO = chatGrupoMapper.toDto(chatGrupo);

        // An entity with an existing ID cannot be created, so this API call must fail
        restChatGrupoMockMvc.perform(post("/api/chat-grupos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(chatGrupoDTO)))
            .andExpect(status().isBadRequest());

        // Validate the ChatGrupo in the database
        List<ChatGrupo> chatGrupoList = chatGrupoRepository.findAll();
        assertThat(chatGrupoList).hasSize(databaseSizeBeforeCreate);

        // Validate the ChatGrupo in Elasticsearch
        verify(mockChatGrupoSearchRepository, times(0)).save(chatGrupo);
    }


    @Test
    @Transactional
    public void checkMensajeIsRequired() throws Exception {
        int databaseSizeBeforeTest = chatGrupoRepository.findAll().size();
        // set the field null
        chatGrupo.setMensaje(null);

        // Create the ChatGrupo, which fails.
        ChatGrupoDTO chatGrupoDTO = chatGrupoMapper.toDto(chatGrupo);

        restChatGrupoMockMvc.perform(post("/api/chat-grupos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(chatGrupoDTO)))
            .andExpect(status().isBadRequest());

        List<ChatGrupo> chatGrupoList = chatGrupoRepository.findAll();
        assertThat(chatGrupoList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllChatGrupos() throws Exception {
        // Initialize the database
        chatGrupoRepository.saveAndFlush(chatGrupo);

        // Get all the chatGrupoList
        restChatGrupoMockMvc.perform(get("/api/chat-grupos?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(chatGrupo.getId().intValue())))
            .andExpect(jsonPath("$.[*].mensaje").value(hasItem(DEFAULT_MENSAJE.toString())))
            .andExpect(jsonPath("$.[*].fecha").value(hasItem(sameInstant(DEFAULT_FECHA))))
            .andExpect(jsonPath("$.[*].leido").value(hasItem(DEFAULT_LEIDO.booleanValue())));
    }
    
    @Test
    @Transactional
    public void getChatGrupo() throws Exception {
        // Initialize the database
        chatGrupoRepository.saveAndFlush(chatGrupo);

        // Get the chatGrupo
        restChatGrupoMockMvc.perform(get("/api/chat-grupos/{id}", chatGrupo.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(chatGrupo.getId().intValue()))
            .andExpect(jsonPath("$.mensaje").value(DEFAULT_MENSAJE.toString()))
            .andExpect(jsonPath("$.fecha").value(sameInstant(DEFAULT_FECHA)))
            .andExpect(jsonPath("$.leido").value(DEFAULT_LEIDO.booleanValue()));
    }

    @Test
    @Transactional
    public void getAllChatGruposByMensajeIsEqualToSomething() throws Exception {
        // Initialize the database
        chatGrupoRepository.saveAndFlush(chatGrupo);

        // Get all the chatGrupoList where mensaje equals to DEFAULT_MENSAJE
        defaultChatGrupoShouldBeFound("mensaje.equals=" + DEFAULT_MENSAJE);

        // Get all the chatGrupoList where mensaje equals to UPDATED_MENSAJE
        defaultChatGrupoShouldNotBeFound("mensaje.equals=" + UPDATED_MENSAJE);
    }

    @Test
    @Transactional
    public void getAllChatGruposByMensajeIsInShouldWork() throws Exception {
        // Initialize the database
        chatGrupoRepository.saveAndFlush(chatGrupo);

        // Get all the chatGrupoList where mensaje in DEFAULT_MENSAJE or UPDATED_MENSAJE
        defaultChatGrupoShouldBeFound("mensaje.in=" + DEFAULT_MENSAJE + "," + UPDATED_MENSAJE);

        // Get all the chatGrupoList where mensaje equals to UPDATED_MENSAJE
        defaultChatGrupoShouldNotBeFound("mensaje.in=" + UPDATED_MENSAJE);
    }

    @Test
    @Transactional
    public void getAllChatGruposByMensajeIsNullOrNotNull() throws Exception {
        // Initialize the database
        chatGrupoRepository.saveAndFlush(chatGrupo);

        // Get all the chatGrupoList where mensaje is not null
        defaultChatGrupoShouldBeFound("mensaje.specified=true");

        // Get all the chatGrupoList where mensaje is null
        defaultChatGrupoShouldNotBeFound("mensaje.specified=false");
    }

    @Test
    @Transactional
    public void getAllChatGruposByFechaIsEqualToSomething() throws Exception {
        // Initialize the database
        chatGrupoRepository.saveAndFlush(chatGrupo);

        // Get all the chatGrupoList where fecha equals to DEFAULT_FECHA
        defaultChatGrupoShouldBeFound("fecha.equals=" + DEFAULT_FECHA);

        // Get all the chatGrupoList where fecha equals to UPDATED_FECHA
        defaultChatGrupoShouldNotBeFound("fecha.equals=" + UPDATED_FECHA);
    }

    @Test
    @Transactional
    public void getAllChatGruposByFechaIsInShouldWork() throws Exception {
        // Initialize the database
        chatGrupoRepository.saveAndFlush(chatGrupo);

        // Get all the chatGrupoList where fecha in DEFAULT_FECHA or UPDATED_FECHA
        defaultChatGrupoShouldBeFound("fecha.in=" + DEFAULT_FECHA + "," + UPDATED_FECHA);

        // Get all the chatGrupoList where fecha equals to UPDATED_FECHA
        defaultChatGrupoShouldNotBeFound("fecha.in=" + UPDATED_FECHA);
    }

    @Test
    @Transactional
    public void getAllChatGruposByFechaIsNullOrNotNull() throws Exception {
        // Initialize the database
        chatGrupoRepository.saveAndFlush(chatGrupo);

        // Get all the chatGrupoList where fecha is not null
        defaultChatGrupoShouldBeFound("fecha.specified=true");

        // Get all the chatGrupoList where fecha is null
        defaultChatGrupoShouldNotBeFound("fecha.specified=false");
    }

    @Test
    @Transactional
    public void getAllChatGruposByFechaIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        chatGrupoRepository.saveAndFlush(chatGrupo);

        // Get all the chatGrupoList where fecha greater than or equals to DEFAULT_FECHA
        defaultChatGrupoShouldBeFound("fecha.greaterOrEqualThan=" + DEFAULT_FECHA);

        // Get all the chatGrupoList where fecha greater than or equals to UPDATED_FECHA
        defaultChatGrupoShouldNotBeFound("fecha.greaterOrEqualThan=" + UPDATED_FECHA);
    }

    @Test
    @Transactional
    public void getAllChatGruposByFechaIsLessThanSomething() throws Exception {
        // Initialize the database
        chatGrupoRepository.saveAndFlush(chatGrupo);

        // Get all the chatGrupoList where fecha less than or equals to DEFAULT_FECHA
        defaultChatGrupoShouldNotBeFound("fecha.lessThan=" + DEFAULT_FECHA);

        // Get all the chatGrupoList where fecha less than or equals to UPDATED_FECHA
        defaultChatGrupoShouldBeFound("fecha.lessThan=" + UPDATED_FECHA);
    }


    @Test
    @Transactional
    public void getAllChatGruposByLeidoIsEqualToSomething() throws Exception {
        // Initialize the database
        chatGrupoRepository.saveAndFlush(chatGrupo);

        // Get all the chatGrupoList where leido equals to DEFAULT_LEIDO
        defaultChatGrupoShouldBeFound("leido.equals=" + DEFAULT_LEIDO);

        // Get all the chatGrupoList where leido equals to UPDATED_LEIDO
        defaultChatGrupoShouldNotBeFound("leido.equals=" + UPDATED_LEIDO);
    }

    @Test
    @Transactional
    public void getAllChatGruposByLeidoIsInShouldWork() throws Exception {
        // Initialize the database
        chatGrupoRepository.saveAndFlush(chatGrupo);

        // Get all the chatGrupoList where leido in DEFAULT_LEIDO or UPDATED_LEIDO
        defaultChatGrupoShouldBeFound("leido.in=" + DEFAULT_LEIDO + "," + UPDATED_LEIDO);

        // Get all the chatGrupoList where leido equals to UPDATED_LEIDO
        defaultChatGrupoShouldNotBeFound("leido.in=" + UPDATED_LEIDO);
    }

    @Test
    @Transactional
    public void getAllChatGruposByLeidoIsNullOrNotNull() throws Exception {
        // Initialize the database
        chatGrupoRepository.saveAndFlush(chatGrupo);

        // Get all the chatGrupoList where leido is not null
        defaultChatGrupoShouldBeFound("leido.specified=true");

        // Get all the chatGrupoList where leido is null
        defaultChatGrupoShouldNotBeFound("leido.specified=false");
    }

    @Test
    @Transactional
    public void getAllChatGruposByDeIsEqualToSomething() throws Exception {
        // Initialize the database
        User de = UserResourceIT.createEntity(em);
        em.persist(de);
        em.flush();
        chatGrupo.setDe(de);
        chatGrupoRepository.saveAndFlush(chatGrupo);
        Long deId = de.getId();

        // Get all the chatGrupoList where de equals to deId
        defaultChatGrupoShouldBeFound("deId.equals=" + deId);

        // Get all the chatGrupoList where de equals to deId + 1
        defaultChatGrupoShouldNotBeFound("deId.equals=" + (deId + 1));
    }


    @Test
    @Transactional
    public void getAllChatGruposByParaIsEqualToSomething() throws Exception {
        // Initialize the database
        User para = UserResourceIT.createEntity(em);
        em.persist(para);
        em.flush();
        chatGrupo.setPara(para);
        chatGrupoRepository.saveAndFlush(chatGrupo);
        Long paraId = para.getId();

        // Get all the chatGrupoList where para equals to paraId
        defaultChatGrupoShouldBeFound("paraId.equals=" + paraId);

        // Get all the chatGrupoList where para equals to paraId + 1
        defaultChatGrupoShouldNotBeFound("paraId.equals=" + (paraId + 1));
    }


    @Test
    @Transactional
    public void getAllChatGruposByGrupoIsEqualToSomething() throws Exception {
        // Initialize the database
        Grupos grupo = GruposResourceIT.createEntity(em);
        em.persist(grupo);
        em.flush();
        chatGrupo.setGrupo(grupo);
        chatGrupoRepository.saveAndFlush(chatGrupo);
        Long grupoId = grupo.getId();

        // Get all the chatGrupoList where grupo equals to grupoId
        defaultChatGrupoShouldBeFound("grupoId.equals=" + grupoId);

        // Get all the chatGrupoList where grupo equals to grupoId + 1
        defaultChatGrupoShouldNotBeFound("grupoId.equals=" + (grupoId + 1));
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultChatGrupoShouldBeFound(String filter) throws Exception {
        restChatGrupoMockMvc.perform(get("/api/chat-grupos?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(chatGrupo.getId().intValue())))
            .andExpect(jsonPath("$.[*].mensaje").value(hasItem(DEFAULT_MENSAJE)))
            .andExpect(jsonPath("$.[*].fecha").value(hasItem(sameInstant(DEFAULT_FECHA))))
            .andExpect(jsonPath("$.[*].leido").value(hasItem(DEFAULT_LEIDO.booleanValue())));

        // Check, that the count call also returns 1
        restChatGrupoMockMvc.perform(get("/api/chat-grupos/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultChatGrupoShouldNotBeFound(String filter) throws Exception {
        restChatGrupoMockMvc.perform(get("/api/chat-grupos?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restChatGrupoMockMvc.perform(get("/api/chat-grupos/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("0"));
    }


    @Test
    @Transactional
    public void getNonExistingChatGrupo() throws Exception {
        // Get the chatGrupo
        restChatGrupoMockMvc.perform(get("/api/chat-grupos/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateChatGrupo() throws Exception {
        // Initialize the database
        chatGrupoRepository.saveAndFlush(chatGrupo);

        int databaseSizeBeforeUpdate = chatGrupoRepository.findAll().size();

        // Update the chatGrupo
        ChatGrupo updatedChatGrupo = chatGrupoRepository.findById(chatGrupo.getId()).get();
        // Disconnect from session so that the updates on updatedChatGrupo are not directly saved in db
        em.detach(updatedChatGrupo);
        updatedChatGrupo
            .mensaje(UPDATED_MENSAJE)
            .fecha(UPDATED_FECHA)
            .leido(UPDATED_LEIDO);
        ChatGrupoDTO chatGrupoDTO = chatGrupoMapper.toDto(updatedChatGrupo);

        restChatGrupoMockMvc.perform(put("/api/chat-grupos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(chatGrupoDTO)))
            .andExpect(status().isOk());

        // Validate the ChatGrupo in the database
        List<ChatGrupo> chatGrupoList = chatGrupoRepository.findAll();
        assertThat(chatGrupoList).hasSize(databaseSizeBeforeUpdate);
        ChatGrupo testChatGrupo = chatGrupoList.get(chatGrupoList.size() - 1);
        assertThat(testChatGrupo.getMensaje()).isEqualTo(UPDATED_MENSAJE);
        assertThat(testChatGrupo.getFecha()).isEqualTo(UPDATED_FECHA);
        assertThat(testChatGrupo.isLeido()).isEqualTo(UPDATED_LEIDO);

        // Validate the ChatGrupo in Elasticsearch
        verify(mockChatGrupoSearchRepository, times(1)).save(testChatGrupo);
    }

    @Test
    @Transactional
    public void updateNonExistingChatGrupo() throws Exception {
        int databaseSizeBeforeUpdate = chatGrupoRepository.findAll().size();

        // Create the ChatGrupo
        ChatGrupoDTO chatGrupoDTO = chatGrupoMapper.toDto(chatGrupo);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restChatGrupoMockMvc.perform(put("/api/chat-grupos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(chatGrupoDTO)))
            .andExpect(status().isBadRequest());

        // Validate the ChatGrupo in the database
        List<ChatGrupo> chatGrupoList = chatGrupoRepository.findAll();
        assertThat(chatGrupoList).hasSize(databaseSizeBeforeUpdate);

        // Validate the ChatGrupo in Elasticsearch
        verify(mockChatGrupoSearchRepository, times(0)).save(chatGrupo);
    }

    @Test
    @Transactional
    public void deleteChatGrupo() throws Exception {
        // Initialize the database
        chatGrupoRepository.saveAndFlush(chatGrupo);

        int databaseSizeBeforeDelete = chatGrupoRepository.findAll().size();

        // Delete the chatGrupo
        restChatGrupoMockMvc.perform(delete("/api/chat-grupos/{id}", chatGrupo.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<ChatGrupo> chatGrupoList = chatGrupoRepository.findAll();
        assertThat(chatGrupoList).hasSize(databaseSizeBeforeDelete - 1);

        // Validate the ChatGrupo in Elasticsearch
        verify(mockChatGrupoSearchRepository, times(1)).deleteById(chatGrupo.getId());
    }

    @Test
    @Transactional
    public void searchChatGrupo() throws Exception {
        // Initialize the database
        chatGrupoRepository.saveAndFlush(chatGrupo);
        when(mockChatGrupoSearchRepository.search(queryStringQuery("id:" + chatGrupo.getId()), PageRequest.of(0, 20)))
            .thenReturn(new PageImpl<>(Collections.singletonList(chatGrupo), PageRequest.of(0, 1), 1));
        // Search the chatGrupo
        restChatGrupoMockMvc.perform(get("/api/_search/chat-grupos?query=id:" + chatGrupo.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(chatGrupo.getId().intValue())))
            .andExpect(jsonPath("$.[*].mensaje").value(hasItem(DEFAULT_MENSAJE)))
            .andExpect(jsonPath("$.[*].fecha").value(hasItem(sameInstant(DEFAULT_FECHA))))
            .andExpect(jsonPath("$.[*].leido").value(hasItem(DEFAULT_LEIDO.booleanValue())));
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(ChatGrupo.class);
        ChatGrupo chatGrupo1 = new ChatGrupo();
        chatGrupo1.setId(1L);
        ChatGrupo chatGrupo2 = new ChatGrupo();
        chatGrupo2.setId(chatGrupo1.getId());
        assertThat(chatGrupo1).isEqualTo(chatGrupo2);
        chatGrupo2.setId(2L);
        assertThat(chatGrupo1).isNotEqualTo(chatGrupo2);
        chatGrupo1.setId(null);
        assertThat(chatGrupo1).isNotEqualTo(chatGrupo2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(ChatGrupoDTO.class);
        ChatGrupoDTO chatGrupoDTO1 = new ChatGrupoDTO();
        chatGrupoDTO1.setId(1L);
        ChatGrupoDTO chatGrupoDTO2 = new ChatGrupoDTO();
        assertThat(chatGrupoDTO1).isNotEqualTo(chatGrupoDTO2);
        chatGrupoDTO2.setId(chatGrupoDTO1.getId());
        assertThat(chatGrupoDTO1).isEqualTo(chatGrupoDTO2);
        chatGrupoDTO2.setId(2L);
        assertThat(chatGrupoDTO1).isNotEqualTo(chatGrupoDTO2);
        chatGrupoDTO1.setId(null);
        assertThat(chatGrupoDTO1).isNotEqualTo(chatGrupoDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(chatGrupoMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(chatGrupoMapper.fromId(null)).isNull();
    }
}
