package io.github.jhipster.application.web.rest;

import io.github.jhipster.application.NewoApp9UaaApp;
import io.github.jhipster.application.config.TestSecurityConfiguration;
import io.github.jhipster.application.domain.Landing;
import io.github.jhipster.application.domain.Sedes;
import io.github.jhipster.application.repository.LandingRepository;
import io.github.jhipster.application.repository.search.LandingSearchRepository;
import io.github.jhipster.application.service.LandingService;
import io.github.jhipster.application.service.dto.LandingDTO;
import io.github.jhipster.application.service.mapper.LandingMapper;
import io.github.jhipster.application.web.rest.errors.ExceptionTranslator;
import io.github.jhipster.application.service.dto.LandingCriteria;
import io.github.jhipster.application.service.LandingQueryService;

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
 * Integration tests for the {@Link LandingResource} REST controller.
 */
@EmbeddedKafka
@SpringBootTest(classes = {NewoApp9UaaApp.class, TestSecurityConfiguration.class})
public class LandingResourceIT {

    private static final String DEFAULT_NOMBRE = "AAAAAAAAAA";
    private static final String UPDATED_NOMBRE = "BBBBBBBBBB";

    private static final String DEFAULT_DESCRIPCION = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPCION = "BBBBBBBBBB";

    private static final String DEFAULT_TELEFONO_NEGOCIO = "AAAAAAAAAA";
    private static final String UPDATED_TELEFONO_NEGOCIO = "BBBBBBBBBB";

    private static final Integer DEFAULT_NUMERO_PUESTOS = 1;
    private static final Integer UPDATED_NUMERO_PUESTOS = 2;

    private static final Integer DEFAULT_TARIFA = 1;
    private static final Integer UPDATED_TARIFA = 2;

    private static final byte[] DEFAULT_FOTOGRAFIA = TestUtil.createByteArray(1, "0");
    private static final byte[] UPDATED_FOTOGRAFIA = TestUtil.createByteArray(1, "1");
    private static final String DEFAULT_FOTOGRAFIA_CONTENT_TYPE = "image/jpg";
    private static final String UPDATED_FOTOGRAFIA_CONTENT_TYPE = "image/png";

    private static final Impuestod DEFAULT_IMPUESTO = Impuestod.IVA19;
    private static final Impuestod UPDATED_IMPUESTO = Impuestod.IVA6;

    @Autowired
    private LandingRepository landingRepository;

    @Autowired
    private LandingMapper landingMapper;

    @Autowired
    private LandingService landingService;

    /**
     * This repository is mocked in the io.github.jhipster.application.repository.search test package.
     *
     * @see io.github.jhipster.application.repository.search.LandingSearchRepositoryMockConfiguration
     */
    @Autowired
    private LandingSearchRepository mockLandingSearchRepository;

    @Autowired
    private LandingQueryService landingQueryService;

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

    private MockMvc restLandingMockMvc;

    private Landing landing;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final LandingResource landingResource = new LandingResource(landingService, landingQueryService);
        this.restLandingMockMvc = MockMvcBuilders.standaloneSetup(landingResource)
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
    public static Landing createEntity(EntityManager em) {
        Landing landing = new Landing()
            .nombre(DEFAULT_NOMBRE)
            .descripcion(DEFAULT_DESCRIPCION)
            .telefonoNegocio(DEFAULT_TELEFONO_NEGOCIO)
            .numeroPuestos(DEFAULT_NUMERO_PUESTOS)
            .tarifa(DEFAULT_TARIFA)
            .fotografia(DEFAULT_FOTOGRAFIA)
            .fotografiaContentType(DEFAULT_FOTOGRAFIA_CONTENT_TYPE)
            .impuesto(DEFAULT_IMPUESTO);
        return landing;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Landing createUpdatedEntity(EntityManager em) {
        Landing landing = new Landing()
            .nombre(UPDATED_NOMBRE)
            .descripcion(UPDATED_DESCRIPCION)
            .telefonoNegocio(UPDATED_TELEFONO_NEGOCIO)
            .numeroPuestos(UPDATED_NUMERO_PUESTOS)
            .tarifa(UPDATED_TARIFA)
            .fotografia(UPDATED_FOTOGRAFIA)
            .fotografiaContentType(UPDATED_FOTOGRAFIA_CONTENT_TYPE)
            .impuesto(UPDATED_IMPUESTO);
        return landing;
    }

    @BeforeEach
    public void initTest() {
        landing = createEntity(em);
    }

    @Test
    @Transactional
    public void createLanding() throws Exception {
        int databaseSizeBeforeCreate = landingRepository.findAll().size();

        // Create the Landing
        LandingDTO landingDTO = landingMapper.toDto(landing);
        restLandingMockMvc.perform(post("/api/landings")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(landingDTO)))
            .andExpect(status().isCreated());

        // Validate the Landing in the database
        List<Landing> landingList = landingRepository.findAll();
        assertThat(landingList).hasSize(databaseSizeBeforeCreate + 1);
        Landing testLanding = landingList.get(landingList.size() - 1);
        assertThat(testLanding.getNombre()).isEqualTo(DEFAULT_NOMBRE);
        assertThat(testLanding.getDescripcion()).isEqualTo(DEFAULT_DESCRIPCION);
        assertThat(testLanding.getTelefonoNegocio()).isEqualTo(DEFAULT_TELEFONO_NEGOCIO);
        assertThat(testLanding.getNumeroPuestos()).isEqualTo(DEFAULT_NUMERO_PUESTOS);
        assertThat(testLanding.getTarifa()).isEqualTo(DEFAULT_TARIFA);
        assertThat(testLanding.getFotografia()).isEqualTo(DEFAULT_FOTOGRAFIA);
        assertThat(testLanding.getFotografiaContentType()).isEqualTo(DEFAULT_FOTOGRAFIA_CONTENT_TYPE);
        assertThat(testLanding.getImpuesto()).isEqualTo(DEFAULT_IMPUESTO);

        // Validate the Landing in Elasticsearch
        verify(mockLandingSearchRepository, times(1)).save(testLanding);
    }

    @Test
    @Transactional
    public void createLandingWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = landingRepository.findAll().size();

        // Create the Landing with an existing ID
        landing.setId(1L);
        LandingDTO landingDTO = landingMapper.toDto(landing);

        // An entity with an existing ID cannot be created, so this API call must fail
        restLandingMockMvc.perform(post("/api/landings")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(landingDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Landing in the database
        List<Landing> landingList = landingRepository.findAll();
        assertThat(landingList).hasSize(databaseSizeBeforeCreate);

        // Validate the Landing in Elasticsearch
        verify(mockLandingSearchRepository, times(0)).save(landing);
    }


    @Test
    @Transactional
    public void checkNombreIsRequired() throws Exception {
        int databaseSizeBeforeTest = landingRepository.findAll().size();
        // set the field null
        landing.setNombre(null);

        // Create the Landing, which fails.
        LandingDTO landingDTO = landingMapper.toDto(landing);

        restLandingMockMvc.perform(post("/api/landings")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(landingDTO)))
            .andExpect(status().isBadRequest());

        List<Landing> landingList = landingRepository.findAll();
        assertThat(landingList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkDescripcionIsRequired() throws Exception {
        int databaseSizeBeforeTest = landingRepository.findAll().size();
        // set the field null
        landing.setDescripcion(null);

        // Create the Landing, which fails.
        LandingDTO landingDTO = landingMapper.toDto(landing);

        restLandingMockMvc.perform(post("/api/landings")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(landingDTO)))
            .andExpect(status().isBadRequest());

        List<Landing> landingList = landingRepository.findAll();
        assertThat(landingList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkNumeroPuestosIsRequired() throws Exception {
        int databaseSizeBeforeTest = landingRepository.findAll().size();
        // set the field null
        landing.setNumeroPuestos(null);

        // Create the Landing, which fails.
        LandingDTO landingDTO = landingMapper.toDto(landing);

        restLandingMockMvc.perform(post("/api/landings")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(landingDTO)))
            .andExpect(status().isBadRequest());

        List<Landing> landingList = landingRepository.findAll();
        assertThat(landingList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkTarifaIsRequired() throws Exception {
        int databaseSizeBeforeTest = landingRepository.findAll().size();
        // set the field null
        landing.setTarifa(null);

        // Create the Landing, which fails.
        LandingDTO landingDTO = landingMapper.toDto(landing);

        restLandingMockMvc.perform(post("/api/landings")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(landingDTO)))
            .andExpect(status().isBadRequest());

        List<Landing> landingList = landingRepository.findAll();
        assertThat(landingList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkImpuestoIsRequired() throws Exception {
        int databaseSizeBeforeTest = landingRepository.findAll().size();
        // set the field null
        landing.setImpuesto(null);

        // Create the Landing, which fails.
        LandingDTO landingDTO = landingMapper.toDto(landing);

        restLandingMockMvc.perform(post("/api/landings")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(landingDTO)))
            .andExpect(status().isBadRequest());

        List<Landing> landingList = landingRepository.findAll();
        assertThat(landingList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllLandings() throws Exception {
        // Initialize the database
        landingRepository.saveAndFlush(landing);

        // Get all the landingList
        restLandingMockMvc.perform(get("/api/landings?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(landing.getId().intValue())))
            .andExpect(jsonPath("$.[*].nombre").value(hasItem(DEFAULT_NOMBRE.toString())))
            .andExpect(jsonPath("$.[*].descripcion").value(hasItem(DEFAULT_DESCRIPCION.toString())))
            .andExpect(jsonPath("$.[*].telefonoNegocio").value(hasItem(DEFAULT_TELEFONO_NEGOCIO.toString())))
            .andExpect(jsonPath("$.[*].numeroPuestos").value(hasItem(DEFAULT_NUMERO_PUESTOS)))
            .andExpect(jsonPath("$.[*].tarifa").value(hasItem(DEFAULT_TARIFA)))
            .andExpect(jsonPath("$.[*].fotografiaContentType").value(hasItem(DEFAULT_FOTOGRAFIA_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].fotografia").value(hasItem(Base64Utils.encodeToString(DEFAULT_FOTOGRAFIA))))
            .andExpect(jsonPath("$.[*].impuesto").value(hasItem(DEFAULT_IMPUESTO.toString())));
    }
    
    @Test
    @Transactional
    public void getLanding() throws Exception {
        // Initialize the database
        landingRepository.saveAndFlush(landing);

        // Get the landing
        restLandingMockMvc.perform(get("/api/landings/{id}", landing.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(landing.getId().intValue()))
            .andExpect(jsonPath("$.nombre").value(DEFAULT_NOMBRE.toString()))
            .andExpect(jsonPath("$.descripcion").value(DEFAULT_DESCRIPCION.toString()))
            .andExpect(jsonPath("$.telefonoNegocio").value(DEFAULT_TELEFONO_NEGOCIO.toString()))
            .andExpect(jsonPath("$.numeroPuestos").value(DEFAULT_NUMERO_PUESTOS))
            .andExpect(jsonPath("$.tarifa").value(DEFAULT_TARIFA))
            .andExpect(jsonPath("$.fotografiaContentType").value(DEFAULT_FOTOGRAFIA_CONTENT_TYPE))
            .andExpect(jsonPath("$.fotografia").value(Base64Utils.encodeToString(DEFAULT_FOTOGRAFIA)))
            .andExpect(jsonPath("$.impuesto").value(DEFAULT_IMPUESTO.toString()));
    }

    @Test
    @Transactional
    public void getAllLandingsByNombreIsEqualToSomething() throws Exception {
        // Initialize the database
        landingRepository.saveAndFlush(landing);

        // Get all the landingList where nombre equals to DEFAULT_NOMBRE
        defaultLandingShouldBeFound("nombre.equals=" + DEFAULT_NOMBRE);

        // Get all the landingList where nombre equals to UPDATED_NOMBRE
        defaultLandingShouldNotBeFound("nombre.equals=" + UPDATED_NOMBRE);
    }

    @Test
    @Transactional
    public void getAllLandingsByNombreIsInShouldWork() throws Exception {
        // Initialize the database
        landingRepository.saveAndFlush(landing);

        // Get all the landingList where nombre in DEFAULT_NOMBRE or UPDATED_NOMBRE
        defaultLandingShouldBeFound("nombre.in=" + DEFAULT_NOMBRE + "," + UPDATED_NOMBRE);

        // Get all the landingList where nombre equals to UPDATED_NOMBRE
        defaultLandingShouldNotBeFound("nombre.in=" + UPDATED_NOMBRE);
    }

    @Test
    @Transactional
    public void getAllLandingsByNombreIsNullOrNotNull() throws Exception {
        // Initialize the database
        landingRepository.saveAndFlush(landing);

        // Get all the landingList where nombre is not null
        defaultLandingShouldBeFound("nombre.specified=true");

        // Get all the landingList where nombre is null
        defaultLandingShouldNotBeFound("nombre.specified=false");
    }

    @Test
    @Transactional
    public void getAllLandingsByDescripcionIsEqualToSomething() throws Exception {
        // Initialize the database
        landingRepository.saveAndFlush(landing);

        // Get all the landingList where descripcion equals to DEFAULT_DESCRIPCION
        defaultLandingShouldBeFound("descripcion.equals=" + DEFAULT_DESCRIPCION);

        // Get all the landingList where descripcion equals to UPDATED_DESCRIPCION
        defaultLandingShouldNotBeFound("descripcion.equals=" + UPDATED_DESCRIPCION);
    }

    @Test
    @Transactional
    public void getAllLandingsByDescripcionIsInShouldWork() throws Exception {
        // Initialize the database
        landingRepository.saveAndFlush(landing);

        // Get all the landingList where descripcion in DEFAULT_DESCRIPCION or UPDATED_DESCRIPCION
        defaultLandingShouldBeFound("descripcion.in=" + DEFAULT_DESCRIPCION + "," + UPDATED_DESCRIPCION);

        // Get all the landingList where descripcion equals to UPDATED_DESCRIPCION
        defaultLandingShouldNotBeFound("descripcion.in=" + UPDATED_DESCRIPCION);
    }

    @Test
    @Transactional
    public void getAllLandingsByDescripcionIsNullOrNotNull() throws Exception {
        // Initialize the database
        landingRepository.saveAndFlush(landing);

        // Get all the landingList where descripcion is not null
        defaultLandingShouldBeFound("descripcion.specified=true");

        // Get all the landingList where descripcion is null
        defaultLandingShouldNotBeFound("descripcion.specified=false");
    }

    @Test
    @Transactional
    public void getAllLandingsByTelefonoNegocioIsEqualToSomething() throws Exception {
        // Initialize the database
        landingRepository.saveAndFlush(landing);

        // Get all the landingList where telefonoNegocio equals to DEFAULT_TELEFONO_NEGOCIO
        defaultLandingShouldBeFound("telefonoNegocio.equals=" + DEFAULT_TELEFONO_NEGOCIO);

        // Get all the landingList where telefonoNegocio equals to UPDATED_TELEFONO_NEGOCIO
        defaultLandingShouldNotBeFound("telefonoNegocio.equals=" + UPDATED_TELEFONO_NEGOCIO);
    }

    @Test
    @Transactional
    public void getAllLandingsByTelefonoNegocioIsInShouldWork() throws Exception {
        // Initialize the database
        landingRepository.saveAndFlush(landing);

        // Get all the landingList where telefonoNegocio in DEFAULT_TELEFONO_NEGOCIO or UPDATED_TELEFONO_NEGOCIO
        defaultLandingShouldBeFound("telefonoNegocio.in=" + DEFAULT_TELEFONO_NEGOCIO + "," + UPDATED_TELEFONO_NEGOCIO);

        // Get all the landingList where telefonoNegocio equals to UPDATED_TELEFONO_NEGOCIO
        defaultLandingShouldNotBeFound("telefonoNegocio.in=" + UPDATED_TELEFONO_NEGOCIO);
    }

    @Test
    @Transactional
    public void getAllLandingsByTelefonoNegocioIsNullOrNotNull() throws Exception {
        // Initialize the database
        landingRepository.saveAndFlush(landing);

        // Get all the landingList where telefonoNegocio is not null
        defaultLandingShouldBeFound("telefonoNegocio.specified=true");

        // Get all the landingList where telefonoNegocio is null
        defaultLandingShouldNotBeFound("telefonoNegocio.specified=false");
    }

    @Test
    @Transactional
    public void getAllLandingsByNumeroPuestosIsEqualToSomething() throws Exception {
        // Initialize the database
        landingRepository.saveAndFlush(landing);

        // Get all the landingList where numeroPuestos equals to DEFAULT_NUMERO_PUESTOS
        defaultLandingShouldBeFound("numeroPuestos.equals=" + DEFAULT_NUMERO_PUESTOS);

        // Get all the landingList where numeroPuestos equals to UPDATED_NUMERO_PUESTOS
        defaultLandingShouldNotBeFound("numeroPuestos.equals=" + UPDATED_NUMERO_PUESTOS);
    }

    @Test
    @Transactional
    public void getAllLandingsByNumeroPuestosIsInShouldWork() throws Exception {
        // Initialize the database
        landingRepository.saveAndFlush(landing);

        // Get all the landingList where numeroPuestos in DEFAULT_NUMERO_PUESTOS or UPDATED_NUMERO_PUESTOS
        defaultLandingShouldBeFound("numeroPuestos.in=" + DEFAULT_NUMERO_PUESTOS + "," + UPDATED_NUMERO_PUESTOS);

        // Get all the landingList where numeroPuestos equals to UPDATED_NUMERO_PUESTOS
        defaultLandingShouldNotBeFound("numeroPuestos.in=" + UPDATED_NUMERO_PUESTOS);
    }

    @Test
    @Transactional
    public void getAllLandingsByNumeroPuestosIsNullOrNotNull() throws Exception {
        // Initialize the database
        landingRepository.saveAndFlush(landing);

        // Get all the landingList where numeroPuestos is not null
        defaultLandingShouldBeFound("numeroPuestos.specified=true");

        // Get all the landingList where numeroPuestos is null
        defaultLandingShouldNotBeFound("numeroPuestos.specified=false");
    }

    @Test
    @Transactional
    public void getAllLandingsByNumeroPuestosIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        landingRepository.saveAndFlush(landing);

        // Get all the landingList where numeroPuestos greater than or equals to DEFAULT_NUMERO_PUESTOS
        defaultLandingShouldBeFound("numeroPuestos.greaterOrEqualThan=" + DEFAULT_NUMERO_PUESTOS);

        // Get all the landingList where numeroPuestos greater than or equals to UPDATED_NUMERO_PUESTOS
        defaultLandingShouldNotBeFound("numeroPuestos.greaterOrEqualThan=" + UPDATED_NUMERO_PUESTOS);
    }

    @Test
    @Transactional
    public void getAllLandingsByNumeroPuestosIsLessThanSomething() throws Exception {
        // Initialize the database
        landingRepository.saveAndFlush(landing);

        // Get all the landingList where numeroPuestos less than or equals to DEFAULT_NUMERO_PUESTOS
        defaultLandingShouldNotBeFound("numeroPuestos.lessThan=" + DEFAULT_NUMERO_PUESTOS);

        // Get all the landingList where numeroPuestos less than or equals to UPDATED_NUMERO_PUESTOS
        defaultLandingShouldBeFound("numeroPuestos.lessThan=" + UPDATED_NUMERO_PUESTOS);
    }


    @Test
    @Transactional
    public void getAllLandingsByTarifaIsEqualToSomething() throws Exception {
        // Initialize the database
        landingRepository.saveAndFlush(landing);

        // Get all the landingList where tarifa equals to DEFAULT_TARIFA
        defaultLandingShouldBeFound("tarifa.equals=" + DEFAULT_TARIFA);

        // Get all the landingList where tarifa equals to UPDATED_TARIFA
        defaultLandingShouldNotBeFound("tarifa.equals=" + UPDATED_TARIFA);
    }

    @Test
    @Transactional
    public void getAllLandingsByTarifaIsInShouldWork() throws Exception {
        // Initialize the database
        landingRepository.saveAndFlush(landing);

        // Get all the landingList where tarifa in DEFAULT_TARIFA or UPDATED_TARIFA
        defaultLandingShouldBeFound("tarifa.in=" + DEFAULT_TARIFA + "," + UPDATED_TARIFA);

        // Get all the landingList where tarifa equals to UPDATED_TARIFA
        defaultLandingShouldNotBeFound("tarifa.in=" + UPDATED_TARIFA);
    }

    @Test
    @Transactional
    public void getAllLandingsByTarifaIsNullOrNotNull() throws Exception {
        // Initialize the database
        landingRepository.saveAndFlush(landing);

        // Get all the landingList where tarifa is not null
        defaultLandingShouldBeFound("tarifa.specified=true");

        // Get all the landingList where tarifa is null
        defaultLandingShouldNotBeFound("tarifa.specified=false");
    }

    @Test
    @Transactional
    public void getAllLandingsByTarifaIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        landingRepository.saveAndFlush(landing);

        // Get all the landingList where tarifa greater than or equals to DEFAULT_TARIFA
        defaultLandingShouldBeFound("tarifa.greaterOrEqualThan=" + DEFAULT_TARIFA);

        // Get all the landingList where tarifa greater than or equals to UPDATED_TARIFA
        defaultLandingShouldNotBeFound("tarifa.greaterOrEqualThan=" + UPDATED_TARIFA);
    }

    @Test
    @Transactional
    public void getAllLandingsByTarifaIsLessThanSomething() throws Exception {
        // Initialize the database
        landingRepository.saveAndFlush(landing);

        // Get all the landingList where tarifa less than or equals to DEFAULT_TARIFA
        defaultLandingShouldNotBeFound("tarifa.lessThan=" + DEFAULT_TARIFA);

        // Get all the landingList where tarifa less than or equals to UPDATED_TARIFA
        defaultLandingShouldBeFound("tarifa.lessThan=" + UPDATED_TARIFA);
    }


    @Test
    @Transactional
    public void getAllLandingsByImpuestoIsEqualToSomething() throws Exception {
        // Initialize the database
        landingRepository.saveAndFlush(landing);

        // Get all the landingList where impuesto equals to DEFAULT_IMPUESTO
        defaultLandingShouldBeFound("impuesto.equals=" + DEFAULT_IMPUESTO);

        // Get all the landingList where impuesto equals to UPDATED_IMPUESTO
        defaultLandingShouldNotBeFound("impuesto.equals=" + UPDATED_IMPUESTO);
    }

    @Test
    @Transactional
    public void getAllLandingsByImpuestoIsInShouldWork() throws Exception {
        // Initialize the database
        landingRepository.saveAndFlush(landing);

        // Get all the landingList where impuesto in DEFAULT_IMPUESTO or UPDATED_IMPUESTO
        defaultLandingShouldBeFound("impuesto.in=" + DEFAULT_IMPUESTO + "," + UPDATED_IMPUESTO);

        // Get all the landingList where impuesto equals to UPDATED_IMPUESTO
        defaultLandingShouldNotBeFound("impuesto.in=" + UPDATED_IMPUESTO);
    }

    @Test
    @Transactional
    public void getAllLandingsByImpuestoIsNullOrNotNull() throws Exception {
        // Initialize the database
        landingRepository.saveAndFlush(landing);

        // Get all the landingList where impuesto is not null
        defaultLandingShouldBeFound("impuesto.specified=true");

        // Get all the landingList where impuesto is null
        defaultLandingShouldNotBeFound("impuesto.specified=false");
    }

    @Test
    @Transactional
    public void getAllLandingsBySedeIsEqualToSomething() throws Exception {
        // Initialize the database
        Sedes sede = SedesResourceIT.createEntity(em);
        em.persist(sede);
        em.flush();
        landing.setSede(sede);
        landingRepository.saveAndFlush(landing);
        Long sedeId = sede.getId();

        // Get all the landingList where sede equals to sedeId
        defaultLandingShouldBeFound("sedeId.equals=" + sedeId);

        // Get all the landingList where sede equals to sedeId + 1
        defaultLandingShouldNotBeFound("sedeId.equals=" + (sedeId + 1));
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultLandingShouldBeFound(String filter) throws Exception {
        restLandingMockMvc.perform(get("/api/landings?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(landing.getId().intValue())))
            .andExpect(jsonPath("$.[*].nombre").value(hasItem(DEFAULT_NOMBRE)))
            .andExpect(jsonPath("$.[*].descripcion").value(hasItem(DEFAULT_DESCRIPCION)))
            .andExpect(jsonPath("$.[*].telefonoNegocio").value(hasItem(DEFAULT_TELEFONO_NEGOCIO)))
            .andExpect(jsonPath("$.[*].numeroPuestos").value(hasItem(DEFAULT_NUMERO_PUESTOS)))
            .andExpect(jsonPath("$.[*].tarifa").value(hasItem(DEFAULT_TARIFA)))
            .andExpect(jsonPath("$.[*].fotografiaContentType").value(hasItem(DEFAULT_FOTOGRAFIA_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].fotografia").value(hasItem(Base64Utils.encodeToString(DEFAULT_FOTOGRAFIA))))
            .andExpect(jsonPath("$.[*].impuesto").value(hasItem(DEFAULT_IMPUESTO.toString())));

        // Check, that the count call also returns 1
        restLandingMockMvc.perform(get("/api/landings/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultLandingShouldNotBeFound(String filter) throws Exception {
        restLandingMockMvc.perform(get("/api/landings?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restLandingMockMvc.perform(get("/api/landings/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("0"));
    }


    @Test
    @Transactional
    public void getNonExistingLanding() throws Exception {
        // Get the landing
        restLandingMockMvc.perform(get("/api/landings/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateLanding() throws Exception {
        // Initialize the database
        landingRepository.saveAndFlush(landing);

        int databaseSizeBeforeUpdate = landingRepository.findAll().size();

        // Update the landing
        Landing updatedLanding = landingRepository.findById(landing.getId()).get();
        // Disconnect from session so that the updates on updatedLanding are not directly saved in db
        em.detach(updatedLanding);
        updatedLanding
            .nombre(UPDATED_NOMBRE)
            .descripcion(UPDATED_DESCRIPCION)
            .telefonoNegocio(UPDATED_TELEFONO_NEGOCIO)
            .numeroPuestos(UPDATED_NUMERO_PUESTOS)
            .tarifa(UPDATED_TARIFA)
            .fotografia(UPDATED_FOTOGRAFIA)
            .fotografiaContentType(UPDATED_FOTOGRAFIA_CONTENT_TYPE)
            .impuesto(UPDATED_IMPUESTO);
        LandingDTO landingDTO = landingMapper.toDto(updatedLanding);

        restLandingMockMvc.perform(put("/api/landings")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(landingDTO)))
            .andExpect(status().isOk());

        // Validate the Landing in the database
        List<Landing> landingList = landingRepository.findAll();
        assertThat(landingList).hasSize(databaseSizeBeforeUpdate);
        Landing testLanding = landingList.get(landingList.size() - 1);
        assertThat(testLanding.getNombre()).isEqualTo(UPDATED_NOMBRE);
        assertThat(testLanding.getDescripcion()).isEqualTo(UPDATED_DESCRIPCION);
        assertThat(testLanding.getTelefonoNegocio()).isEqualTo(UPDATED_TELEFONO_NEGOCIO);
        assertThat(testLanding.getNumeroPuestos()).isEqualTo(UPDATED_NUMERO_PUESTOS);
        assertThat(testLanding.getTarifa()).isEqualTo(UPDATED_TARIFA);
        assertThat(testLanding.getFotografia()).isEqualTo(UPDATED_FOTOGRAFIA);
        assertThat(testLanding.getFotografiaContentType()).isEqualTo(UPDATED_FOTOGRAFIA_CONTENT_TYPE);
        assertThat(testLanding.getImpuesto()).isEqualTo(UPDATED_IMPUESTO);

        // Validate the Landing in Elasticsearch
        verify(mockLandingSearchRepository, times(1)).save(testLanding);
    }

    @Test
    @Transactional
    public void updateNonExistingLanding() throws Exception {
        int databaseSizeBeforeUpdate = landingRepository.findAll().size();

        // Create the Landing
        LandingDTO landingDTO = landingMapper.toDto(landing);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restLandingMockMvc.perform(put("/api/landings")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(landingDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Landing in the database
        List<Landing> landingList = landingRepository.findAll();
        assertThat(landingList).hasSize(databaseSizeBeforeUpdate);

        // Validate the Landing in Elasticsearch
        verify(mockLandingSearchRepository, times(0)).save(landing);
    }

    @Test
    @Transactional
    public void deleteLanding() throws Exception {
        // Initialize the database
        landingRepository.saveAndFlush(landing);

        int databaseSizeBeforeDelete = landingRepository.findAll().size();

        // Delete the landing
        restLandingMockMvc.perform(delete("/api/landings/{id}", landing.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Landing> landingList = landingRepository.findAll();
        assertThat(landingList).hasSize(databaseSizeBeforeDelete - 1);

        // Validate the Landing in Elasticsearch
        verify(mockLandingSearchRepository, times(1)).deleteById(landing.getId());
    }

    @Test
    @Transactional
    public void searchLanding() throws Exception {
        // Initialize the database
        landingRepository.saveAndFlush(landing);
        when(mockLandingSearchRepository.search(queryStringQuery("id:" + landing.getId()), PageRequest.of(0, 20)))
            .thenReturn(new PageImpl<>(Collections.singletonList(landing), PageRequest.of(0, 1), 1));
        // Search the landing
        restLandingMockMvc.perform(get("/api/_search/landings?query=id:" + landing.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(landing.getId().intValue())))
            .andExpect(jsonPath("$.[*].nombre").value(hasItem(DEFAULT_NOMBRE)))
            .andExpect(jsonPath("$.[*].descripcion").value(hasItem(DEFAULT_DESCRIPCION)))
            .andExpect(jsonPath("$.[*].telefonoNegocio").value(hasItem(DEFAULT_TELEFONO_NEGOCIO)))
            .andExpect(jsonPath("$.[*].numeroPuestos").value(hasItem(DEFAULT_NUMERO_PUESTOS)))
            .andExpect(jsonPath("$.[*].tarifa").value(hasItem(DEFAULT_TARIFA)))
            .andExpect(jsonPath("$.[*].fotografiaContentType").value(hasItem(DEFAULT_FOTOGRAFIA_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].fotografia").value(hasItem(Base64Utils.encodeToString(DEFAULT_FOTOGRAFIA))))
            .andExpect(jsonPath("$.[*].impuesto").value(hasItem(DEFAULT_IMPUESTO.toString())));
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Landing.class);
        Landing landing1 = new Landing();
        landing1.setId(1L);
        Landing landing2 = new Landing();
        landing2.setId(landing1.getId());
        assertThat(landing1).isEqualTo(landing2);
        landing2.setId(2L);
        assertThat(landing1).isNotEqualTo(landing2);
        landing1.setId(null);
        assertThat(landing1).isNotEqualTo(landing2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(LandingDTO.class);
        LandingDTO landingDTO1 = new LandingDTO();
        landingDTO1.setId(1L);
        LandingDTO landingDTO2 = new LandingDTO();
        assertThat(landingDTO1).isNotEqualTo(landingDTO2);
        landingDTO2.setId(landingDTO1.getId());
        assertThat(landingDTO1).isEqualTo(landingDTO2);
        landingDTO2.setId(2L);
        assertThat(landingDTO1).isNotEqualTo(landingDTO2);
        landingDTO1.setId(null);
        assertThat(landingDTO1).isNotEqualTo(landingDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(landingMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(landingMapper.fromId(null)).isNull();
    }
}
