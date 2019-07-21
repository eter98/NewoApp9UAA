package io.github.jhipster.application.web.rest;

import io.github.jhipster.application.NewoApp9UaaApp;
import io.github.jhipster.application.config.TestSecurityConfiguration;
import io.github.jhipster.application.domain.ConsumoMarket;
import io.github.jhipster.application.domain.User;
import io.github.jhipster.application.repository.ConsumoMarketRepository;
import io.github.jhipster.application.repository.search.ConsumoMarketSearchRepository;
import io.github.jhipster.application.service.ConsumoMarketService;
import io.github.jhipster.application.service.dto.ConsumoMarketDTO;
import io.github.jhipster.application.service.mapper.ConsumoMarketMapper;
import io.github.jhipster.application.web.rest.errors.ExceptionTranslator;
import io.github.jhipster.application.service.dto.ConsumoMarketCriteria;
import io.github.jhipster.application.service.ConsumoMarketQueryService;

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
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Collections;
import java.util.List;

import static io.github.jhipster.application.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.elasticsearch.index.query.QueryBuilders.queryStringQuery;
import static org.hamcrest.Matchers.hasItem;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import io.github.jhipster.application.domain.enumeration.Impuestod;
/**
 * Integration tests for the {@Link ConsumoMarketResource} REST controller.
 */
@EmbeddedKafka
@SpringBootTest(classes = {NewoApp9UaaApp.class, TestSecurityConfiguration.class})
public class ConsumoMarketResourceIT {

    private static final Integer DEFAULT_TOTAL_CONSUMIDO = 1;
    private static final Integer UPDATED_TOTAL_CONSUMIDO = 2;

    private static final LocalDate DEFAULT_REGISTRO_FECHA_INICIAL = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_REGISTRO_FECHA_INICIAL = LocalDate.now(ZoneId.systemDefault());

    private static final LocalDate DEFAULT_REGISTRO_FECHA_FINAL = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_REGISTRO_FECHA_FINAL = LocalDate.now(ZoneId.systemDefault());

    private static final Impuestod DEFAULT_IMPUESTO = Impuestod.IVA19;
    private static final Impuestod UPDATED_IMPUESTO = Impuestod.IVA6;

    @Autowired
    private ConsumoMarketRepository consumoMarketRepository;

    @Autowired
    private ConsumoMarketMapper consumoMarketMapper;

    @Autowired
    private ConsumoMarketService consumoMarketService;

    /**
     * This repository is mocked in the io.github.jhipster.application.repository.search test package.
     *
     * @see io.github.jhipster.application.repository.search.ConsumoMarketSearchRepositoryMockConfiguration
     */
    @Autowired
    private ConsumoMarketSearchRepository mockConsumoMarketSearchRepository;

    @Autowired
    private ConsumoMarketQueryService consumoMarketQueryService;

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

    private MockMvc restConsumoMarketMockMvc;

    private ConsumoMarket consumoMarket;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final ConsumoMarketResource consumoMarketResource = new ConsumoMarketResource(consumoMarketService, consumoMarketQueryService);
        this.restConsumoMarketMockMvc = MockMvcBuilders.standaloneSetup(consumoMarketResource)
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
    public static ConsumoMarket createEntity(EntityManager em) {
        ConsumoMarket consumoMarket = new ConsumoMarket()
            .totalConsumido(DEFAULT_TOTAL_CONSUMIDO)
            .registroFechaInicial(DEFAULT_REGISTRO_FECHA_INICIAL)
            .registroFechaFinal(DEFAULT_REGISTRO_FECHA_FINAL)
            .impuesto(DEFAULT_IMPUESTO);
        return consumoMarket;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ConsumoMarket createUpdatedEntity(EntityManager em) {
        ConsumoMarket consumoMarket = new ConsumoMarket()
            .totalConsumido(UPDATED_TOTAL_CONSUMIDO)
            .registroFechaInicial(UPDATED_REGISTRO_FECHA_INICIAL)
            .registroFechaFinal(UPDATED_REGISTRO_FECHA_FINAL)
            .impuesto(UPDATED_IMPUESTO);
        return consumoMarket;
    }

    @BeforeEach
    public void initTest() {
        consumoMarket = createEntity(em);
    }

    @Test
    @Transactional
    public void createConsumoMarket() throws Exception {
        int databaseSizeBeforeCreate = consumoMarketRepository.findAll().size();

        // Create the ConsumoMarket
        ConsumoMarketDTO consumoMarketDTO = consumoMarketMapper.toDto(consumoMarket);
        restConsumoMarketMockMvc.perform(post("/api/consumo-markets")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(consumoMarketDTO)))
            .andExpect(status().isCreated());

        // Validate the ConsumoMarket in the database
        List<ConsumoMarket> consumoMarketList = consumoMarketRepository.findAll();
        assertThat(consumoMarketList).hasSize(databaseSizeBeforeCreate + 1);
        ConsumoMarket testConsumoMarket = consumoMarketList.get(consumoMarketList.size() - 1);
        assertThat(testConsumoMarket.getTotalConsumido()).isEqualTo(DEFAULT_TOTAL_CONSUMIDO);
        assertThat(testConsumoMarket.getRegistroFechaInicial()).isEqualTo(DEFAULT_REGISTRO_FECHA_INICIAL);
        assertThat(testConsumoMarket.getRegistroFechaFinal()).isEqualTo(DEFAULT_REGISTRO_FECHA_FINAL);
        assertThat(testConsumoMarket.getImpuesto()).isEqualTo(DEFAULT_IMPUESTO);

        // Validate the ConsumoMarket in Elasticsearch
        verify(mockConsumoMarketSearchRepository, times(1)).save(testConsumoMarket);
    }

    @Test
    @Transactional
    public void createConsumoMarketWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = consumoMarketRepository.findAll().size();

        // Create the ConsumoMarket with an existing ID
        consumoMarket.setId(1L);
        ConsumoMarketDTO consumoMarketDTO = consumoMarketMapper.toDto(consumoMarket);

        // An entity with an existing ID cannot be created, so this API call must fail
        restConsumoMarketMockMvc.perform(post("/api/consumo-markets")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(consumoMarketDTO)))
            .andExpect(status().isBadRequest());

        // Validate the ConsumoMarket in the database
        List<ConsumoMarket> consumoMarketList = consumoMarketRepository.findAll();
        assertThat(consumoMarketList).hasSize(databaseSizeBeforeCreate);

        // Validate the ConsumoMarket in Elasticsearch
        verify(mockConsumoMarketSearchRepository, times(0)).save(consumoMarket);
    }


    @Test
    @Transactional
    public void getAllConsumoMarkets() throws Exception {
        // Initialize the database
        consumoMarketRepository.saveAndFlush(consumoMarket);

        // Get all the consumoMarketList
        restConsumoMarketMockMvc.perform(get("/api/consumo-markets?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(consumoMarket.getId().intValue())))
            .andExpect(jsonPath("$.[*].totalConsumido").value(hasItem(DEFAULT_TOTAL_CONSUMIDO)))
            .andExpect(jsonPath("$.[*].registroFechaInicial").value(hasItem(DEFAULT_REGISTRO_FECHA_INICIAL.toString())))
            .andExpect(jsonPath("$.[*].registroFechaFinal").value(hasItem(DEFAULT_REGISTRO_FECHA_FINAL.toString())))
            .andExpect(jsonPath("$.[*].impuesto").value(hasItem(DEFAULT_IMPUESTO.toString())));
    }
    
    @Test
    @Transactional
    public void getConsumoMarket() throws Exception {
        // Initialize the database
        consumoMarketRepository.saveAndFlush(consumoMarket);

        // Get the consumoMarket
        restConsumoMarketMockMvc.perform(get("/api/consumo-markets/{id}", consumoMarket.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(consumoMarket.getId().intValue()))
            .andExpect(jsonPath("$.totalConsumido").value(DEFAULT_TOTAL_CONSUMIDO))
            .andExpect(jsonPath("$.registroFechaInicial").value(DEFAULT_REGISTRO_FECHA_INICIAL.toString()))
            .andExpect(jsonPath("$.registroFechaFinal").value(DEFAULT_REGISTRO_FECHA_FINAL.toString()))
            .andExpect(jsonPath("$.impuesto").value(DEFAULT_IMPUESTO.toString()));
    }

    @Test
    @Transactional
    public void getAllConsumoMarketsByTotalConsumidoIsEqualToSomething() throws Exception {
        // Initialize the database
        consumoMarketRepository.saveAndFlush(consumoMarket);

        // Get all the consumoMarketList where totalConsumido equals to DEFAULT_TOTAL_CONSUMIDO
        defaultConsumoMarketShouldBeFound("totalConsumido.equals=" + DEFAULT_TOTAL_CONSUMIDO);

        // Get all the consumoMarketList where totalConsumido equals to UPDATED_TOTAL_CONSUMIDO
        defaultConsumoMarketShouldNotBeFound("totalConsumido.equals=" + UPDATED_TOTAL_CONSUMIDO);
    }

    @Test
    @Transactional
    public void getAllConsumoMarketsByTotalConsumidoIsInShouldWork() throws Exception {
        // Initialize the database
        consumoMarketRepository.saveAndFlush(consumoMarket);

        // Get all the consumoMarketList where totalConsumido in DEFAULT_TOTAL_CONSUMIDO or UPDATED_TOTAL_CONSUMIDO
        defaultConsumoMarketShouldBeFound("totalConsumido.in=" + DEFAULT_TOTAL_CONSUMIDO + "," + UPDATED_TOTAL_CONSUMIDO);

        // Get all the consumoMarketList where totalConsumido equals to UPDATED_TOTAL_CONSUMIDO
        defaultConsumoMarketShouldNotBeFound("totalConsumido.in=" + UPDATED_TOTAL_CONSUMIDO);
    }

    @Test
    @Transactional
    public void getAllConsumoMarketsByTotalConsumidoIsNullOrNotNull() throws Exception {
        // Initialize the database
        consumoMarketRepository.saveAndFlush(consumoMarket);

        // Get all the consumoMarketList where totalConsumido is not null
        defaultConsumoMarketShouldBeFound("totalConsumido.specified=true");

        // Get all the consumoMarketList where totalConsumido is null
        defaultConsumoMarketShouldNotBeFound("totalConsumido.specified=false");
    }

    @Test
    @Transactional
    public void getAllConsumoMarketsByTotalConsumidoIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        consumoMarketRepository.saveAndFlush(consumoMarket);

        // Get all the consumoMarketList where totalConsumido greater than or equals to DEFAULT_TOTAL_CONSUMIDO
        defaultConsumoMarketShouldBeFound("totalConsumido.greaterOrEqualThan=" + DEFAULT_TOTAL_CONSUMIDO);

        // Get all the consumoMarketList where totalConsumido greater than or equals to UPDATED_TOTAL_CONSUMIDO
        defaultConsumoMarketShouldNotBeFound("totalConsumido.greaterOrEqualThan=" + UPDATED_TOTAL_CONSUMIDO);
    }

    @Test
    @Transactional
    public void getAllConsumoMarketsByTotalConsumidoIsLessThanSomething() throws Exception {
        // Initialize the database
        consumoMarketRepository.saveAndFlush(consumoMarket);

        // Get all the consumoMarketList where totalConsumido less than or equals to DEFAULT_TOTAL_CONSUMIDO
        defaultConsumoMarketShouldNotBeFound("totalConsumido.lessThan=" + DEFAULT_TOTAL_CONSUMIDO);

        // Get all the consumoMarketList where totalConsumido less than or equals to UPDATED_TOTAL_CONSUMIDO
        defaultConsumoMarketShouldBeFound("totalConsumido.lessThan=" + UPDATED_TOTAL_CONSUMIDO);
    }


    @Test
    @Transactional
    public void getAllConsumoMarketsByRegistroFechaInicialIsEqualToSomething() throws Exception {
        // Initialize the database
        consumoMarketRepository.saveAndFlush(consumoMarket);

        // Get all the consumoMarketList where registroFechaInicial equals to DEFAULT_REGISTRO_FECHA_INICIAL
        defaultConsumoMarketShouldBeFound("registroFechaInicial.equals=" + DEFAULT_REGISTRO_FECHA_INICIAL);

        // Get all the consumoMarketList where registroFechaInicial equals to UPDATED_REGISTRO_FECHA_INICIAL
        defaultConsumoMarketShouldNotBeFound("registroFechaInicial.equals=" + UPDATED_REGISTRO_FECHA_INICIAL);
    }

    @Test
    @Transactional
    public void getAllConsumoMarketsByRegistroFechaInicialIsInShouldWork() throws Exception {
        // Initialize the database
        consumoMarketRepository.saveAndFlush(consumoMarket);

        // Get all the consumoMarketList where registroFechaInicial in DEFAULT_REGISTRO_FECHA_INICIAL or UPDATED_REGISTRO_FECHA_INICIAL
        defaultConsumoMarketShouldBeFound("registroFechaInicial.in=" + DEFAULT_REGISTRO_FECHA_INICIAL + "," + UPDATED_REGISTRO_FECHA_INICIAL);

        // Get all the consumoMarketList where registroFechaInicial equals to UPDATED_REGISTRO_FECHA_INICIAL
        defaultConsumoMarketShouldNotBeFound("registroFechaInicial.in=" + UPDATED_REGISTRO_FECHA_INICIAL);
    }

    @Test
    @Transactional
    public void getAllConsumoMarketsByRegistroFechaInicialIsNullOrNotNull() throws Exception {
        // Initialize the database
        consumoMarketRepository.saveAndFlush(consumoMarket);

        // Get all the consumoMarketList where registroFechaInicial is not null
        defaultConsumoMarketShouldBeFound("registroFechaInicial.specified=true");

        // Get all the consumoMarketList where registroFechaInicial is null
        defaultConsumoMarketShouldNotBeFound("registroFechaInicial.specified=false");
    }

    @Test
    @Transactional
    public void getAllConsumoMarketsByRegistroFechaInicialIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        consumoMarketRepository.saveAndFlush(consumoMarket);

        // Get all the consumoMarketList where registroFechaInicial greater than or equals to DEFAULT_REGISTRO_FECHA_INICIAL
        defaultConsumoMarketShouldBeFound("registroFechaInicial.greaterOrEqualThan=" + DEFAULT_REGISTRO_FECHA_INICIAL);

        // Get all the consumoMarketList where registroFechaInicial greater than or equals to UPDATED_REGISTRO_FECHA_INICIAL
        defaultConsumoMarketShouldNotBeFound("registroFechaInicial.greaterOrEqualThan=" + UPDATED_REGISTRO_FECHA_INICIAL);
    }

    @Test
    @Transactional
    public void getAllConsumoMarketsByRegistroFechaInicialIsLessThanSomething() throws Exception {
        // Initialize the database
        consumoMarketRepository.saveAndFlush(consumoMarket);

        // Get all the consumoMarketList where registroFechaInicial less than or equals to DEFAULT_REGISTRO_FECHA_INICIAL
        defaultConsumoMarketShouldNotBeFound("registroFechaInicial.lessThan=" + DEFAULT_REGISTRO_FECHA_INICIAL);

        // Get all the consumoMarketList where registroFechaInicial less than or equals to UPDATED_REGISTRO_FECHA_INICIAL
        defaultConsumoMarketShouldBeFound("registroFechaInicial.lessThan=" + UPDATED_REGISTRO_FECHA_INICIAL);
    }


    @Test
    @Transactional
    public void getAllConsumoMarketsByRegistroFechaFinalIsEqualToSomething() throws Exception {
        // Initialize the database
        consumoMarketRepository.saveAndFlush(consumoMarket);

        // Get all the consumoMarketList where registroFechaFinal equals to DEFAULT_REGISTRO_FECHA_FINAL
        defaultConsumoMarketShouldBeFound("registroFechaFinal.equals=" + DEFAULT_REGISTRO_FECHA_FINAL);

        // Get all the consumoMarketList where registroFechaFinal equals to UPDATED_REGISTRO_FECHA_FINAL
        defaultConsumoMarketShouldNotBeFound("registroFechaFinal.equals=" + UPDATED_REGISTRO_FECHA_FINAL);
    }

    @Test
    @Transactional
    public void getAllConsumoMarketsByRegistroFechaFinalIsInShouldWork() throws Exception {
        // Initialize the database
        consumoMarketRepository.saveAndFlush(consumoMarket);

        // Get all the consumoMarketList where registroFechaFinal in DEFAULT_REGISTRO_FECHA_FINAL or UPDATED_REGISTRO_FECHA_FINAL
        defaultConsumoMarketShouldBeFound("registroFechaFinal.in=" + DEFAULT_REGISTRO_FECHA_FINAL + "," + UPDATED_REGISTRO_FECHA_FINAL);

        // Get all the consumoMarketList where registroFechaFinal equals to UPDATED_REGISTRO_FECHA_FINAL
        defaultConsumoMarketShouldNotBeFound("registroFechaFinal.in=" + UPDATED_REGISTRO_FECHA_FINAL);
    }

    @Test
    @Transactional
    public void getAllConsumoMarketsByRegistroFechaFinalIsNullOrNotNull() throws Exception {
        // Initialize the database
        consumoMarketRepository.saveAndFlush(consumoMarket);

        // Get all the consumoMarketList where registroFechaFinal is not null
        defaultConsumoMarketShouldBeFound("registroFechaFinal.specified=true");

        // Get all the consumoMarketList where registroFechaFinal is null
        defaultConsumoMarketShouldNotBeFound("registroFechaFinal.specified=false");
    }

    @Test
    @Transactional
    public void getAllConsumoMarketsByRegistroFechaFinalIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        consumoMarketRepository.saveAndFlush(consumoMarket);

        // Get all the consumoMarketList where registroFechaFinal greater than or equals to DEFAULT_REGISTRO_FECHA_FINAL
        defaultConsumoMarketShouldBeFound("registroFechaFinal.greaterOrEqualThan=" + DEFAULT_REGISTRO_FECHA_FINAL);

        // Get all the consumoMarketList where registroFechaFinal greater than or equals to UPDATED_REGISTRO_FECHA_FINAL
        defaultConsumoMarketShouldNotBeFound("registroFechaFinal.greaterOrEqualThan=" + UPDATED_REGISTRO_FECHA_FINAL);
    }

    @Test
    @Transactional
    public void getAllConsumoMarketsByRegistroFechaFinalIsLessThanSomething() throws Exception {
        // Initialize the database
        consumoMarketRepository.saveAndFlush(consumoMarket);

        // Get all the consumoMarketList where registroFechaFinal less than or equals to DEFAULT_REGISTRO_FECHA_FINAL
        defaultConsumoMarketShouldNotBeFound("registroFechaFinal.lessThan=" + DEFAULT_REGISTRO_FECHA_FINAL);

        // Get all the consumoMarketList where registroFechaFinal less than or equals to UPDATED_REGISTRO_FECHA_FINAL
        defaultConsumoMarketShouldBeFound("registroFechaFinal.lessThan=" + UPDATED_REGISTRO_FECHA_FINAL);
    }


    @Test
    @Transactional
    public void getAllConsumoMarketsByImpuestoIsEqualToSomething() throws Exception {
        // Initialize the database
        consumoMarketRepository.saveAndFlush(consumoMarket);

        // Get all the consumoMarketList where impuesto equals to DEFAULT_IMPUESTO
        defaultConsumoMarketShouldBeFound("impuesto.equals=" + DEFAULT_IMPUESTO);

        // Get all the consumoMarketList where impuesto equals to UPDATED_IMPUESTO
        defaultConsumoMarketShouldNotBeFound("impuesto.equals=" + UPDATED_IMPUESTO);
    }

    @Test
    @Transactional
    public void getAllConsumoMarketsByImpuestoIsInShouldWork() throws Exception {
        // Initialize the database
        consumoMarketRepository.saveAndFlush(consumoMarket);

        // Get all the consumoMarketList where impuesto in DEFAULT_IMPUESTO or UPDATED_IMPUESTO
        defaultConsumoMarketShouldBeFound("impuesto.in=" + DEFAULT_IMPUESTO + "," + UPDATED_IMPUESTO);

        // Get all the consumoMarketList where impuesto equals to UPDATED_IMPUESTO
        defaultConsumoMarketShouldNotBeFound("impuesto.in=" + UPDATED_IMPUESTO);
    }

    @Test
    @Transactional
    public void getAllConsumoMarketsByImpuestoIsNullOrNotNull() throws Exception {
        // Initialize the database
        consumoMarketRepository.saveAndFlush(consumoMarket);

        // Get all the consumoMarketList where impuesto is not null
        defaultConsumoMarketShouldBeFound("impuesto.specified=true");

        // Get all the consumoMarketList where impuesto is null
        defaultConsumoMarketShouldNotBeFound("impuesto.specified=false");
    }

    @Test
    @Transactional
    public void getAllConsumoMarketsByMiembroIsEqualToSomething() throws Exception {
        // Initialize the database
        User miembro = UserResourceIT.createEntity(em);
        em.persist(miembro);
        em.flush();
        consumoMarket.setMiembro(miembro);
        consumoMarketRepository.saveAndFlush(consumoMarket);
        Long miembroId = miembro.getId();

        // Get all the consumoMarketList where miembro equals to miembroId
        defaultConsumoMarketShouldBeFound("miembroId.equals=" + miembroId);

        // Get all the consumoMarketList where miembro equals to miembroId + 1
        defaultConsumoMarketShouldNotBeFound("miembroId.equals=" + (miembroId + 1));
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultConsumoMarketShouldBeFound(String filter) throws Exception {
        restConsumoMarketMockMvc.perform(get("/api/consumo-markets?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(consumoMarket.getId().intValue())))
            .andExpect(jsonPath("$.[*].totalConsumido").value(hasItem(DEFAULT_TOTAL_CONSUMIDO)))
            .andExpect(jsonPath("$.[*].registroFechaInicial").value(hasItem(DEFAULT_REGISTRO_FECHA_INICIAL.toString())))
            .andExpect(jsonPath("$.[*].registroFechaFinal").value(hasItem(DEFAULT_REGISTRO_FECHA_FINAL.toString())))
            .andExpect(jsonPath("$.[*].impuesto").value(hasItem(DEFAULT_IMPUESTO.toString())));

        // Check, that the count call also returns 1
        restConsumoMarketMockMvc.perform(get("/api/consumo-markets/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultConsumoMarketShouldNotBeFound(String filter) throws Exception {
        restConsumoMarketMockMvc.perform(get("/api/consumo-markets?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restConsumoMarketMockMvc.perform(get("/api/consumo-markets/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("0"));
    }


    @Test
    @Transactional
    public void getNonExistingConsumoMarket() throws Exception {
        // Get the consumoMarket
        restConsumoMarketMockMvc.perform(get("/api/consumo-markets/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateConsumoMarket() throws Exception {
        // Initialize the database
        consumoMarketRepository.saveAndFlush(consumoMarket);

        int databaseSizeBeforeUpdate = consumoMarketRepository.findAll().size();

        // Update the consumoMarket
        ConsumoMarket updatedConsumoMarket = consumoMarketRepository.findById(consumoMarket.getId()).get();
        // Disconnect from session so that the updates on updatedConsumoMarket are not directly saved in db
        em.detach(updatedConsumoMarket);
        updatedConsumoMarket
            .totalConsumido(UPDATED_TOTAL_CONSUMIDO)
            .registroFechaInicial(UPDATED_REGISTRO_FECHA_INICIAL)
            .registroFechaFinal(UPDATED_REGISTRO_FECHA_FINAL)
            .impuesto(UPDATED_IMPUESTO);
        ConsumoMarketDTO consumoMarketDTO = consumoMarketMapper.toDto(updatedConsumoMarket);

        restConsumoMarketMockMvc.perform(put("/api/consumo-markets")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(consumoMarketDTO)))
            .andExpect(status().isOk());

        // Validate the ConsumoMarket in the database
        List<ConsumoMarket> consumoMarketList = consumoMarketRepository.findAll();
        assertThat(consumoMarketList).hasSize(databaseSizeBeforeUpdate);
        ConsumoMarket testConsumoMarket = consumoMarketList.get(consumoMarketList.size() - 1);
        assertThat(testConsumoMarket.getTotalConsumido()).isEqualTo(UPDATED_TOTAL_CONSUMIDO);
        assertThat(testConsumoMarket.getRegistroFechaInicial()).isEqualTo(UPDATED_REGISTRO_FECHA_INICIAL);
        assertThat(testConsumoMarket.getRegistroFechaFinal()).isEqualTo(UPDATED_REGISTRO_FECHA_FINAL);
        assertThat(testConsumoMarket.getImpuesto()).isEqualTo(UPDATED_IMPUESTO);

        // Validate the ConsumoMarket in Elasticsearch
        verify(mockConsumoMarketSearchRepository, times(1)).save(testConsumoMarket);
    }

    @Test
    @Transactional
    public void updateNonExistingConsumoMarket() throws Exception {
        int databaseSizeBeforeUpdate = consumoMarketRepository.findAll().size();

        // Create the ConsumoMarket
        ConsumoMarketDTO consumoMarketDTO = consumoMarketMapper.toDto(consumoMarket);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restConsumoMarketMockMvc.perform(put("/api/consumo-markets")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(consumoMarketDTO)))
            .andExpect(status().isBadRequest());

        // Validate the ConsumoMarket in the database
        List<ConsumoMarket> consumoMarketList = consumoMarketRepository.findAll();
        assertThat(consumoMarketList).hasSize(databaseSizeBeforeUpdate);

        // Validate the ConsumoMarket in Elasticsearch
        verify(mockConsumoMarketSearchRepository, times(0)).save(consumoMarket);
    }

    @Test
    @Transactional
    public void deleteConsumoMarket() throws Exception {
        // Initialize the database
        consumoMarketRepository.saveAndFlush(consumoMarket);

        int databaseSizeBeforeDelete = consumoMarketRepository.findAll().size();

        // Delete the consumoMarket
        restConsumoMarketMockMvc.perform(delete("/api/consumo-markets/{id}", consumoMarket.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<ConsumoMarket> consumoMarketList = consumoMarketRepository.findAll();
        assertThat(consumoMarketList).hasSize(databaseSizeBeforeDelete - 1);

        // Validate the ConsumoMarket in Elasticsearch
        verify(mockConsumoMarketSearchRepository, times(1)).deleteById(consumoMarket.getId());
    }

    @Test
    @Transactional
    public void searchConsumoMarket() throws Exception {
        // Initialize the database
        consumoMarketRepository.saveAndFlush(consumoMarket);
        when(mockConsumoMarketSearchRepository.search(queryStringQuery("id:" + consumoMarket.getId()), PageRequest.of(0, 20)))
            .thenReturn(new PageImpl<>(Collections.singletonList(consumoMarket), PageRequest.of(0, 1), 1));
        // Search the consumoMarket
        restConsumoMarketMockMvc.perform(get("/api/_search/consumo-markets?query=id:" + consumoMarket.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(consumoMarket.getId().intValue())))
            .andExpect(jsonPath("$.[*].totalConsumido").value(hasItem(DEFAULT_TOTAL_CONSUMIDO)))
            .andExpect(jsonPath("$.[*].registroFechaInicial").value(hasItem(DEFAULT_REGISTRO_FECHA_INICIAL.toString())))
            .andExpect(jsonPath("$.[*].registroFechaFinal").value(hasItem(DEFAULT_REGISTRO_FECHA_FINAL.toString())))
            .andExpect(jsonPath("$.[*].impuesto").value(hasItem(DEFAULT_IMPUESTO.toString())));
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(ConsumoMarket.class);
        ConsumoMarket consumoMarket1 = new ConsumoMarket();
        consumoMarket1.setId(1L);
        ConsumoMarket consumoMarket2 = new ConsumoMarket();
        consumoMarket2.setId(consumoMarket1.getId());
        assertThat(consumoMarket1).isEqualTo(consumoMarket2);
        consumoMarket2.setId(2L);
        assertThat(consumoMarket1).isNotEqualTo(consumoMarket2);
        consumoMarket1.setId(null);
        assertThat(consumoMarket1).isNotEqualTo(consumoMarket2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(ConsumoMarketDTO.class);
        ConsumoMarketDTO consumoMarketDTO1 = new ConsumoMarketDTO();
        consumoMarketDTO1.setId(1L);
        ConsumoMarketDTO consumoMarketDTO2 = new ConsumoMarketDTO();
        assertThat(consumoMarketDTO1).isNotEqualTo(consumoMarketDTO2);
        consumoMarketDTO2.setId(consumoMarketDTO1.getId());
        assertThat(consumoMarketDTO1).isEqualTo(consumoMarketDTO2);
        consumoMarketDTO2.setId(2L);
        assertThat(consumoMarketDTO1).isNotEqualTo(consumoMarketDTO2);
        consumoMarketDTO1.setId(null);
        assertThat(consumoMarketDTO1).isNotEqualTo(consumoMarketDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(consumoMarketMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(consumoMarketMapper.fromId(null)).isNull();
    }
}
