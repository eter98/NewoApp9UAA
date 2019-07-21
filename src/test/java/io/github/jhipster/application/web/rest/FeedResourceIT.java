package io.github.jhipster.application.web.rest;

import io.github.jhipster.application.NewoApp9UaaApp;
import io.github.jhipster.application.config.TestSecurityConfiguration;
import io.github.jhipster.application.domain.Feed;
import io.github.jhipster.application.domain.User;
import io.github.jhipster.application.domain.CategoriaContenidos;
import io.github.jhipster.application.repository.FeedRepository;
import io.github.jhipster.application.repository.search.FeedSearchRepository;
import io.github.jhipster.application.service.FeedService;
import io.github.jhipster.application.service.dto.FeedDTO;
import io.github.jhipster.application.service.mapper.FeedMapper;
import io.github.jhipster.application.web.rest.errors.ExceptionTranslator;
import io.github.jhipster.application.service.dto.FeedCriteria;
import io.github.jhipster.application.service.FeedQueryService;

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
import org.springframework.util.Base64Utils;
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

import io.github.jhipster.application.domain.enumeration.Categoriad;
/**
 * Integration tests for the {@Link FeedResource} REST controller.
 */
@EmbeddedKafka
@SpringBootTest(classes = {NewoApp9UaaApp.class, TestSecurityConfiguration.class})
public class FeedResourceIT {

    private static final String DEFAULT_TITULO = "AAAAAAAAAA";
    private static final String UPDATED_TITULO = "BBBBBBBBBB";

    private static final String DEFAULT_DESCRIPCION = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPCION = "BBBBBBBBBB";

    private static final byte[] DEFAULT_IMAGEN_1 = TestUtil.createByteArray(1, "0");
    private static final byte[] UPDATED_IMAGEN_1 = TestUtil.createByteArray(1, "1");
    private static final String DEFAULT_IMAGEN_1_CONTENT_TYPE = "image/jpg";
    private static final String UPDATED_IMAGEN_1_CONTENT_TYPE = "image/png";

    private static final byte[] DEFAULT_IMAGEN_2 = TestUtil.createByteArray(1, "0");
    private static final byte[] UPDATED_IMAGEN_2 = TestUtil.createByteArray(1, "1");
    private static final String DEFAULT_IMAGEN_2_CONTENT_TYPE = "image/jpg";
    private static final String UPDATED_IMAGEN_2_CONTENT_TYPE = "image/png";

    private static final Categoriad DEFAULT_TIPO_CONTENIDO = Categoriad.GENERAL;
    private static final Categoriad UPDATED_TIPO_CONTENIDO = Categoriad.DE_GRUPO;

    private static final String DEFAULT_CONTENIDO = "AAAAAAAAAA";
    private static final String UPDATED_CONTENIDO = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_FECHA = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_FECHA = LocalDate.now(ZoneId.systemDefault());

    @Autowired
    private FeedRepository feedRepository;

    @Autowired
    private FeedMapper feedMapper;

    @Autowired
    private FeedService feedService;

    /**
     * This repository is mocked in the io.github.jhipster.application.repository.search test package.
     *
     * @see io.github.jhipster.application.repository.search.FeedSearchRepositoryMockConfiguration
     */
    @Autowired
    private FeedSearchRepository mockFeedSearchRepository;

    @Autowired
    private FeedQueryService feedQueryService;

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

    private MockMvc restFeedMockMvc;

    private Feed feed;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final FeedResource feedResource = new FeedResource(feedService, feedQueryService);
        this.restFeedMockMvc = MockMvcBuilders.standaloneSetup(feedResource)
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
    public static Feed createEntity(EntityManager em) {
        Feed feed = new Feed()
            .titulo(DEFAULT_TITULO)
            .descripcion(DEFAULT_DESCRIPCION)
            .imagen1(DEFAULT_IMAGEN_1)
            .imagen1ContentType(DEFAULT_IMAGEN_1_CONTENT_TYPE)
            .imagen2(DEFAULT_IMAGEN_2)
            .imagen2ContentType(DEFAULT_IMAGEN_2_CONTENT_TYPE)
            .tipoContenido(DEFAULT_TIPO_CONTENIDO)
            .contenido(DEFAULT_CONTENIDO)
            .fecha(DEFAULT_FECHA);
        return feed;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Feed createUpdatedEntity(EntityManager em) {
        Feed feed = new Feed()
            .titulo(UPDATED_TITULO)
            .descripcion(UPDATED_DESCRIPCION)
            .imagen1(UPDATED_IMAGEN_1)
            .imagen1ContentType(UPDATED_IMAGEN_1_CONTENT_TYPE)
            .imagen2(UPDATED_IMAGEN_2)
            .imagen2ContentType(UPDATED_IMAGEN_2_CONTENT_TYPE)
            .tipoContenido(UPDATED_TIPO_CONTENIDO)
            .contenido(UPDATED_CONTENIDO)
            .fecha(UPDATED_FECHA);
        return feed;
    }

    @BeforeEach
    public void initTest() {
        feed = createEntity(em);
    }

    @Test
    @Transactional
    public void createFeed() throws Exception {
        int databaseSizeBeforeCreate = feedRepository.findAll().size();

        // Create the Feed
        FeedDTO feedDTO = feedMapper.toDto(feed);
        restFeedMockMvc.perform(post("/api/feeds")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(feedDTO)))
            .andExpect(status().isCreated());

        // Validate the Feed in the database
        List<Feed> feedList = feedRepository.findAll();
        assertThat(feedList).hasSize(databaseSizeBeforeCreate + 1);
        Feed testFeed = feedList.get(feedList.size() - 1);
        assertThat(testFeed.getTitulo()).isEqualTo(DEFAULT_TITULO);
        assertThat(testFeed.getDescripcion()).isEqualTo(DEFAULT_DESCRIPCION);
        assertThat(testFeed.getImagen1()).isEqualTo(DEFAULT_IMAGEN_1);
        assertThat(testFeed.getImagen1ContentType()).isEqualTo(DEFAULT_IMAGEN_1_CONTENT_TYPE);
        assertThat(testFeed.getImagen2()).isEqualTo(DEFAULT_IMAGEN_2);
        assertThat(testFeed.getImagen2ContentType()).isEqualTo(DEFAULT_IMAGEN_2_CONTENT_TYPE);
        assertThat(testFeed.getTipoContenido()).isEqualTo(DEFAULT_TIPO_CONTENIDO);
        assertThat(testFeed.getContenido()).isEqualTo(DEFAULT_CONTENIDO);
        assertThat(testFeed.getFecha()).isEqualTo(DEFAULT_FECHA);

        // Validate the Feed in Elasticsearch
        verify(mockFeedSearchRepository, times(1)).save(testFeed);
    }

    @Test
    @Transactional
    public void createFeedWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = feedRepository.findAll().size();

        // Create the Feed with an existing ID
        feed.setId(1L);
        FeedDTO feedDTO = feedMapper.toDto(feed);

        // An entity with an existing ID cannot be created, so this API call must fail
        restFeedMockMvc.perform(post("/api/feeds")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(feedDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Feed in the database
        List<Feed> feedList = feedRepository.findAll();
        assertThat(feedList).hasSize(databaseSizeBeforeCreate);

        // Validate the Feed in Elasticsearch
        verify(mockFeedSearchRepository, times(0)).save(feed);
    }


    @Test
    @Transactional
    public void checkTituloIsRequired() throws Exception {
        int databaseSizeBeforeTest = feedRepository.findAll().size();
        // set the field null
        feed.setTitulo(null);

        // Create the Feed, which fails.
        FeedDTO feedDTO = feedMapper.toDto(feed);

        restFeedMockMvc.perform(post("/api/feeds")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(feedDTO)))
            .andExpect(status().isBadRequest());

        List<Feed> feedList = feedRepository.findAll();
        assertThat(feedList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkDescripcionIsRequired() throws Exception {
        int databaseSizeBeforeTest = feedRepository.findAll().size();
        // set the field null
        feed.setDescripcion(null);

        // Create the Feed, which fails.
        FeedDTO feedDTO = feedMapper.toDto(feed);

        restFeedMockMvc.perform(post("/api/feeds")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(feedDTO)))
            .andExpect(status().isBadRequest());

        List<Feed> feedList = feedRepository.findAll();
        assertThat(feedList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllFeeds() throws Exception {
        // Initialize the database
        feedRepository.saveAndFlush(feed);

        // Get all the feedList
        restFeedMockMvc.perform(get("/api/feeds?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(feed.getId().intValue())))
            .andExpect(jsonPath("$.[*].titulo").value(hasItem(DEFAULT_TITULO.toString())))
            .andExpect(jsonPath("$.[*].descripcion").value(hasItem(DEFAULT_DESCRIPCION.toString())))
            .andExpect(jsonPath("$.[*].imagen1ContentType").value(hasItem(DEFAULT_IMAGEN_1_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].imagen1").value(hasItem(Base64Utils.encodeToString(DEFAULT_IMAGEN_1))))
            .andExpect(jsonPath("$.[*].imagen2ContentType").value(hasItem(DEFAULT_IMAGEN_2_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].imagen2").value(hasItem(Base64Utils.encodeToString(DEFAULT_IMAGEN_2))))
            .andExpect(jsonPath("$.[*].tipoContenido").value(hasItem(DEFAULT_TIPO_CONTENIDO.toString())))
            .andExpect(jsonPath("$.[*].contenido").value(hasItem(DEFAULT_CONTENIDO.toString())))
            .andExpect(jsonPath("$.[*].fecha").value(hasItem(DEFAULT_FECHA.toString())));
    }
    
    @Test
    @Transactional
    public void getFeed() throws Exception {
        // Initialize the database
        feedRepository.saveAndFlush(feed);

        // Get the feed
        restFeedMockMvc.perform(get("/api/feeds/{id}", feed.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(feed.getId().intValue()))
            .andExpect(jsonPath("$.titulo").value(DEFAULT_TITULO.toString()))
            .andExpect(jsonPath("$.descripcion").value(DEFAULT_DESCRIPCION.toString()))
            .andExpect(jsonPath("$.imagen1ContentType").value(DEFAULT_IMAGEN_1_CONTENT_TYPE))
            .andExpect(jsonPath("$.imagen1").value(Base64Utils.encodeToString(DEFAULT_IMAGEN_1)))
            .andExpect(jsonPath("$.imagen2ContentType").value(DEFAULT_IMAGEN_2_CONTENT_TYPE))
            .andExpect(jsonPath("$.imagen2").value(Base64Utils.encodeToString(DEFAULT_IMAGEN_2)))
            .andExpect(jsonPath("$.tipoContenido").value(DEFAULT_TIPO_CONTENIDO.toString()))
            .andExpect(jsonPath("$.contenido").value(DEFAULT_CONTENIDO.toString()))
            .andExpect(jsonPath("$.fecha").value(DEFAULT_FECHA.toString()));
    }

    @Test
    @Transactional
    public void getAllFeedsByTituloIsEqualToSomething() throws Exception {
        // Initialize the database
        feedRepository.saveAndFlush(feed);

        // Get all the feedList where titulo equals to DEFAULT_TITULO
        defaultFeedShouldBeFound("titulo.equals=" + DEFAULT_TITULO);

        // Get all the feedList where titulo equals to UPDATED_TITULO
        defaultFeedShouldNotBeFound("titulo.equals=" + UPDATED_TITULO);
    }

    @Test
    @Transactional
    public void getAllFeedsByTituloIsInShouldWork() throws Exception {
        // Initialize the database
        feedRepository.saveAndFlush(feed);

        // Get all the feedList where titulo in DEFAULT_TITULO or UPDATED_TITULO
        defaultFeedShouldBeFound("titulo.in=" + DEFAULT_TITULO + "," + UPDATED_TITULO);

        // Get all the feedList where titulo equals to UPDATED_TITULO
        defaultFeedShouldNotBeFound("titulo.in=" + UPDATED_TITULO);
    }

    @Test
    @Transactional
    public void getAllFeedsByTituloIsNullOrNotNull() throws Exception {
        // Initialize the database
        feedRepository.saveAndFlush(feed);

        // Get all the feedList where titulo is not null
        defaultFeedShouldBeFound("titulo.specified=true");

        // Get all the feedList where titulo is null
        defaultFeedShouldNotBeFound("titulo.specified=false");
    }

    @Test
    @Transactional
    public void getAllFeedsByDescripcionIsEqualToSomething() throws Exception {
        // Initialize the database
        feedRepository.saveAndFlush(feed);

        // Get all the feedList where descripcion equals to DEFAULT_DESCRIPCION
        defaultFeedShouldBeFound("descripcion.equals=" + DEFAULT_DESCRIPCION);

        // Get all the feedList where descripcion equals to UPDATED_DESCRIPCION
        defaultFeedShouldNotBeFound("descripcion.equals=" + UPDATED_DESCRIPCION);
    }

    @Test
    @Transactional
    public void getAllFeedsByDescripcionIsInShouldWork() throws Exception {
        // Initialize the database
        feedRepository.saveAndFlush(feed);

        // Get all the feedList where descripcion in DEFAULT_DESCRIPCION or UPDATED_DESCRIPCION
        defaultFeedShouldBeFound("descripcion.in=" + DEFAULT_DESCRIPCION + "," + UPDATED_DESCRIPCION);

        // Get all the feedList where descripcion equals to UPDATED_DESCRIPCION
        defaultFeedShouldNotBeFound("descripcion.in=" + UPDATED_DESCRIPCION);
    }

    @Test
    @Transactional
    public void getAllFeedsByDescripcionIsNullOrNotNull() throws Exception {
        // Initialize the database
        feedRepository.saveAndFlush(feed);

        // Get all the feedList where descripcion is not null
        defaultFeedShouldBeFound("descripcion.specified=true");

        // Get all the feedList where descripcion is null
        defaultFeedShouldNotBeFound("descripcion.specified=false");
    }

    @Test
    @Transactional
    public void getAllFeedsByTipoContenidoIsEqualToSomething() throws Exception {
        // Initialize the database
        feedRepository.saveAndFlush(feed);

        // Get all the feedList where tipoContenido equals to DEFAULT_TIPO_CONTENIDO
        defaultFeedShouldBeFound("tipoContenido.equals=" + DEFAULT_TIPO_CONTENIDO);

        // Get all the feedList where tipoContenido equals to UPDATED_TIPO_CONTENIDO
        defaultFeedShouldNotBeFound("tipoContenido.equals=" + UPDATED_TIPO_CONTENIDO);
    }

    @Test
    @Transactional
    public void getAllFeedsByTipoContenidoIsInShouldWork() throws Exception {
        // Initialize the database
        feedRepository.saveAndFlush(feed);

        // Get all the feedList where tipoContenido in DEFAULT_TIPO_CONTENIDO or UPDATED_TIPO_CONTENIDO
        defaultFeedShouldBeFound("tipoContenido.in=" + DEFAULT_TIPO_CONTENIDO + "," + UPDATED_TIPO_CONTENIDO);

        // Get all the feedList where tipoContenido equals to UPDATED_TIPO_CONTENIDO
        defaultFeedShouldNotBeFound("tipoContenido.in=" + UPDATED_TIPO_CONTENIDO);
    }

    @Test
    @Transactional
    public void getAllFeedsByTipoContenidoIsNullOrNotNull() throws Exception {
        // Initialize the database
        feedRepository.saveAndFlush(feed);

        // Get all the feedList where tipoContenido is not null
        defaultFeedShouldBeFound("tipoContenido.specified=true");

        // Get all the feedList where tipoContenido is null
        defaultFeedShouldNotBeFound("tipoContenido.specified=false");
    }

    @Test
    @Transactional
    public void getAllFeedsByFechaIsEqualToSomething() throws Exception {
        // Initialize the database
        feedRepository.saveAndFlush(feed);

        // Get all the feedList where fecha equals to DEFAULT_FECHA
        defaultFeedShouldBeFound("fecha.equals=" + DEFAULT_FECHA);

        // Get all the feedList where fecha equals to UPDATED_FECHA
        defaultFeedShouldNotBeFound("fecha.equals=" + UPDATED_FECHA);
    }

    @Test
    @Transactional
    public void getAllFeedsByFechaIsInShouldWork() throws Exception {
        // Initialize the database
        feedRepository.saveAndFlush(feed);

        // Get all the feedList where fecha in DEFAULT_FECHA or UPDATED_FECHA
        defaultFeedShouldBeFound("fecha.in=" + DEFAULT_FECHA + "," + UPDATED_FECHA);

        // Get all the feedList where fecha equals to UPDATED_FECHA
        defaultFeedShouldNotBeFound("fecha.in=" + UPDATED_FECHA);
    }

    @Test
    @Transactional
    public void getAllFeedsByFechaIsNullOrNotNull() throws Exception {
        // Initialize the database
        feedRepository.saveAndFlush(feed);

        // Get all the feedList where fecha is not null
        defaultFeedShouldBeFound("fecha.specified=true");

        // Get all the feedList where fecha is null
        defaultFeedShouldNotBeFound("fecha.specified=false");
    }

    @Test
    @Transactional
    public void getAllFeedsByFechaIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        feedRepository.saveAndFlush(feed);

        // Get all the feedList where fecha greater than or equals to DEFAULT_FECHA
        defaultFeedShouldBeFound("fecha.greaterOrEqualThan=" + DEFAULT_FECHA);

        // Get all the feedList where fecha greater than or equals to UPDATED_FECHA
        defaultFeedShouldNotBeFound("fecha.greaterOrEqualThan=" + UPDATED_FECHA);
    }

    @Test
    @Transactional
    public void getAllFeedsByFechaIsLessThanSomething() throws Exception {
        // Initialize the database
        feedRepository.saveAndFlush(feed);

        // Get all the feedList where fecha less than or equals to DEFAULT_FECHA
        defaultFeedShouldNotBeFound("fecha.lessThan=" + DEFAULT_FECHA);

        // Get all the feedList where fecha less than or equals to UPDATED_FECHA
        defaultFeedShouldBeFound("fecha.lessThan=" + UPDATED_FECHA);
    }


    @Test
    @Transactional
    public void getAllFeedsByMiembroIsEqualToSomething() throws Exception {
        // Initialize the database
        User miembro = UserResourceIT.createEntity(em);
        em.persist(miembro);
        em.flush();
        feed.setMiembro(miembro);
        feedRepository.saveAndFlush(feed);
        Long miembroId = miembro.getId();

        // Get all the feedList where miembro equals to miembroId
        defaultFeedShouldBeFound("miembroId.equals=" + miembroId);

        // Get all the feedList where miembro equals to miembroId + 1
        defaultFeedShouldNotBeFound("miembroId.equals=" + (miembroId + 1));
    }


    @Test
    @Transactional
    public void getAllFeedsByCategoriaFeedIsEqualToSomething() throws Exception {
        // Initialize the database
        CategoriaContenidos categoriaFeed = CategoriaContenidosResourceIT.createEntity(em);
        em.persist(categoriaFeed);
        em.flush();
        feed.setCategoriaFeed(categoriaFeed);
        feedRepository.saveAndFlush(feed);
        Long categoriaFeedId = categoriaFeed.getId();

        // Get all the feedList where categoriaFeed equals to categoriaFeedId
        defaultFeedShouldBeFound("categoriaFeedId.equals=" + categoriaFeedId);

        // Get all the feedList where categoriaFeed equals to categoriaFeedId + 1
        defaultFeedShouldNotBeFound("categoriaFeedId.equals=" + (categoriaFeedId + 1));
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultFeedShouldBeFound(String filter) throws Exception {
        restFeedMockMvc.perform(get("/api/feeds?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(feed.getId().intValue())))
            .andExpect(jsonPath("$.[*].titulo").value(hasItem(DEFAULT_TITULO)))
            .andExpect(jsonPath("$.[*].descripcion").value(hasItem(DEFAULT_DESCRIPCION)))
            .andExpect(jsonPath("$.[*].imagen1ContentType").value(hasItem(DEFAULT_IMAGEN_1_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].imagen1").value(hasItem(Base64Utils.encodeToString(DEFAULT_IMAGEN_1))))
            .andExpect(jsonPath("$.[*].imagen2ContentType").value(hasItem(DEFAULT_IMAGEN_2_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].imagen2").value(hasItem(Base64Utils.encodeToString(DEFAULT_IMAGEN_2))))
            .andExpect(jsonPath("$.[*].tipoContenido").value(hasItem(DEFAULT_TIPO_CONTENIDO.toString())))
            .andExpect(jsonPath("$.[*].contenido").value(hasItem(DEFAULT_CONTENIDO.toString())))
            .andExpect(jsonPath("$.[*].fecha").value(hasItem(DEFAULT_FECHA.toString())));

        // Check, that the count call also returns 1
        restFeedMockMvc.perform(get("/api/feeds/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultFeedShouldNotBeFound(String filter) throws Exception {
        restFeedMockMvc.perform(get("/api/feeds?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restFeedMockMvc.perform(get("/api/feeds/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("0"));
    }


    @Test
    @Transactional
    public void getNonExistingFeed() throws Exception {
        // Get the feed
        restFeedMockMvc.perform(get("/api/feeds/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateFeed() throws Exception {
        // Initialize the database
        feedRepository.saveAndFlush(feed);

        int databaseSizeBeforeUpdate = feedRepository.findAll().size();

        // Update the feed
        Feed updatedFeed = feedRepository.findById(feed.getId()).get();
        // Disconnect from session so that the updates on updatedFeed are not directly saved in db
        em.detach(updatedFeed);
        updatedFeed
            .titulo(UPDATED_TITULO)
            .descripcion(UPDATED_DESCRIPCION)
            .imagen1(UPDATED_IMAGEN_1)
            .imagen1ContentType(UPDATED_IMAGEN_1_CONTENT_TYPE)
            .imagen2(UPDATED_IMAGEN_2)
            .imagen2ContentType(UPDATED_IMAGEN_2_CONTENT_TYPE)
            .tipoContenido(UPDATED_TIPO_CONTENIDO)
            .contenido(UPDATED_CONTENIDO)
            .fecha(UPDATED_FECHA);
        FeedDTO feedDTO = feedMapper.toDto(updatedFeed);

        restFeedMockMvc.perform(put("/api/feeds")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(feedDTO)))
            .andExpect(status().isOk());

        // Validate the Feed in the database
        List<Feed> feedList = feedRepository.findAll();
        assertThat(feedList).hasSize(databaseSizeBeforeUpdate);
        Feed testFeed = feedList.get(feedList.size() - 1);
        assertThat(testFeed.getTitulo()).isEqualTo(UPDATED_TITULO);
        assertThat(testFeed.getDescripcion()).isEqualTo(UPDATED_DESCRIPCION);
        assertThat(testFeed.getImagen1()).isEqualTo(UPDATED_IMAGEN_1);
        assertThat(testFeed.getImagen1ContentType()).isEqualTo(UPDATED_IMAGEN_1_CONTENT_TYPE);
        assertThat(testFeed.getImagen2()).isEqualTo(UPDATED_IMAGEN_2);
        assertThat(testFeed.getImagen2ContentType()).isEqualTo(UPDATED_IMAGEN_2_CONTENT_TYPE);
        assertThat(testFeed.getTipoContenido()).isEqualTo(UPDATED_TIPO_CONTENIDO);
        assertThat(testFeed.getContenido()).isEqualTo(UPDATED_CONTENIDO);
        assertThat(testFeed.getFecha()).isEqualTo(UPDATED_FECHA);

        // Validate the Feed in Elasticsearch
        verify(mockFeedSearchRepository, times(1)).save(testFeed);
    }

    @Test
    @Transactional
    public void updateNonExistingFeed() throws Exception {
        int databaseSizeBeforeUpdate = feedRepository.findAll().size();

        // Create the Feed
        FeedDTO feedDTO = feedMapper.toDto(feed);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restFeedMockMvc.perform(put("/api/feeds")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(feedDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Feed in the database
        List<Feed> feedList = feedRepository.findAll();
        assertThat(feedList).hasSize(databaseSizeBeforeUpdate);

        // Validate the Feed in Elasticsearch
        verify(mockFeedSearchRepository, times(0)).save(feed);
    }

    @Test
    @Transactional
    public void deleteFeed() throws Exception {
        // Initialize the database
        feedRepository.saveAndFlush(feed);

        int databaseSizeBeforeDelete = feedRepository.findAll().size();

        // Delete the feed
        restFeedMockMvc.perform(delete("/api/feeds/{id}", feed.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Feed> feedList = feedRepository.findAll();
        assertThat(feedList).hasSize(databaseSizeBeforeDelete - 1);

        // Validate the Feed in Elasticsearch
        verify(mockFeedSearchRepository, times(1)).deleteById(feed.getId());
    }

    @Test
    @Transactional
    public void searchFeed() throws Exception {
        // Initialize the database
        feedRepository.saveAndFlush(feed);
        when(mockFeedSearchRepository.search(queryStringQuery("id:" + feed.getId()), PageRequest.of(0, 20)))
            .thenReturn(new PageImpl<>(Collections.singletonList(feed), PageRequest.of(0, 1), 1));
        // Search the feed
        restFeedMockMvc.perform(get("/api/_search/feeds?query=id:" + feed.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(feed.getId().intValue())))
            .andExpect(jsonPath("$.[*].titulo").value(hasItem(DEFAULT_TITULO)))
            .andExpect(jsonPath("$.[*].descripcion").value(hasItem(DEFAULT_DESCRIPCION)))
            .andExpect(jsonPath("$.[*].imagen1ContentType").value(hasItem(DEFAULT_IMAGEN_1_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].imagen1").value(hasItem(Base64Utils.encodeToString(DEFAULT_IMAGEN_1))))
            .andExpect(jsonPath("$.[*].imagen2ContentType").value(hasItem(DEFAULT_IMAGEN_2_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].imagen2").value(hasItem(Base64Utils.encodeToString(DEFAULT_IMAGEN_2))))
            .andExpect(jsonPath("$.[*].tipoContenido").value(hasItem(DEFAULT_TIPO_CONTENIDO.toString())))
            .andExpect(jsonPath("$.[*].contenido").value(hasItem(DEFAULT_CONTENIDO.toString())))
            .andExpect(jsonPath("$.[*].fecha").value(hasItem(DEFAULT_FECHA.toString())));
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Feed.class);
        Feed feed1 = new Feed();
        feed1.setId(1L);
        Feed feed2 = new Feed();
        feed2.setId(feed1.getId());
        assertThat(feed1).isEqualTo(feed2);
        feed2.setId(2L);
        assertThat(feed1).isNotEqualTo(feed2);
        feed1.setId(null);
        assertThat(feed1).isNotEqualTo(feed2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(FeedDTO.class);
        FeedDTO feedDTO1 = new FeedDTO();
        feedDTO1.setId(1L);
        FeedDTO feedDTO2 = new FeedDTO();
        assertThat(feedDTO1).isNotEqualTo(feedDTO2);
        feedDTO2.setId(feedDTO1.getId());
        assertThat(feedDTO1).isEqualTo(feedDTO2);
        feedDTO2.setId(2L);
        assertThat(feedDTO1).isNotEqualTo(feedDTO2);
        feedDTO1.setId(null);
        assertThat(feedDTO1).isNotEqualTo(feedDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(feedMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(feedMapper.fromId(null)).isNull();
    }
}
