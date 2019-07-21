package io.github.jhipster.application.web.rest;

import io.github.jhipster.application.NewoApp9UaaApp;
import io.github.jhipster.application.config.TestSecurityConfiguration;
import io.github.jhipster.application.domain.RegistroCompra;
import io.github.jhipster.application.domain.User;
import io.github.jhipster.application.domain.Facturacion;
import io.github.jhipster.application.domain.Reservas;
import io.github.jhipster.application.domain.EntradaInvitados;
import io.github.jhipster.application.domain.EntradaMiembros;
import io.github.jhipster.application.repository.RegistroCompraRepository;
import io.github.jhipster.application.repository.search.RegistroCompraSearchRepository;
import io.github.jhipster.application.service.RegistroCompraService;
import io.github.jhipster.application.service.dto.RegistroCompraDTO;
import io.github.jhipster.application.service.mapper.RegistroCompraMapper;
import io.github.jhipster.application.web.rest.errors.ExceptionTranslator;
import io.github.jhipster.application.service.dto.RegistroCompraCriteria;
import io.github.jhipster.application.service.RegistroCompraQueryService;

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
import java.util.Collections;
import java.util.List;

import static io.github.jhipster.application.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.elasticsearch.index.query.QueryBuilders.queryStringQuery;
import static org.hamcrest.Matchers.hasItem;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the {@Link RegistroCompraResource} REST controller.
 */
@EmbeddedKafka
@SpringBootTest(classes = {NewoApp9UaaApp.class, TestSecurityConfiguration.class})
public class RegistroCompraResourceIT {

    private static final Boolean DEFAULT_CONSUMO_MARKET = false;
    private static final Boolean UPDATED_CONSUMO_MARKET = true;

    @Autowired
    private RegistroCompraRepository registroCompraRepository;

    @Autowired
    private RegistroCompraMapper registroCompraMapper;

    @Autowired
    private RegistroCompraService registroCompraService;

    /**
     * This repository is mocked in the io.github.jhipster.application.repository.search test package.
     *
     * @see io.github.jhipster.application.repository.search.RegistroCompraSearchRepositoryMockConfiguration
     */
    @Autowired
    private RegistroCompraSearchRepository mockRegistroCompraSearchRepository;

    @Autowired
    private RegistroCompraQueryService registroCompraQueryService;

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

    private MockMvc restRegistroCompraMockMvc;

    private RegistroCompra registroCompra;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final RegistroCompraResource registroCompraResource = new RegistroCompraResource(registroCompraService, registroCompraQueryService);
        this.restRegistroCompraMockMvc = MockMvcBuilders.standaloneSetup(registroCompraResource)
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
    public static RegistroCompra createEntity(EntityManager em) {
        RegistroCompra registroCompra = new RegistroCompra()
            .consumoMarket(DEFAULT_CONSUMO_MARKET);
        return registroCompra;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static RegistroCompra createUpdatedEntity(EntityManager em) {
        RegistroCompra registroCompra = new RegistroCompra()
            .consumoMarket(UPDATED_CONSUMO_MARKET);
        return registroCompra;
    }

    @BeforeEach
    public void initTest() {
        registroCompra = createEntity(em);
    }

    @Test
    @Transactional
    public void createRegistroCompra() throws Exception {
        int databaseSizeBeforeCreate = registroCompraRepository.findAll().size();

        // Create the RegistroCompra
        RegistroCompraDTO registroCompraDTO = registroCompraMapper.toDto(registroCompra);
        restRegistroCompraMockMvc.perform(post("/api/registro-compras")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(registroCompraDTO)))
            .andExpect(status().isCreated());

        // Validate the RegistroCompra in the database
        List<RegistroCompra> registroCompraList = registroCompraRepository.findAll();
        assertThat(registroCompraList).hasSize(databaseSizeBeforeCreate + 1);
        RegistroCompra testRegistroCompra = registroCompraList.get(registroCompraList.size() - 1);
        assertThat(testRegistroCompra.isConsumoMarket()).isEqualTo(DEFAULT_CONSUMO_MARKET);

        // Validate the RegistroCompra in Elasticsearch
        verify(mockRegistroCompraSearchRepository, times(1)).save(testRegistroCompra);
    }

    @Test
    @Transactional
    public void createRegistroCompraWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = registroCompraRepository.findAll().size();

        // Create the RegistroCompra with an existing ID
        registroCompra.setId(1L);
        RegistroCompraDTO registroCompraDTO = registroCompraMapper.toDto(registroCompra);

        // An entity with an existing ID cannot be created, so this API call must fail
        restRegistroCompraMockMvc.perform(post("/api/registro-compras")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(registroCompraDTO)))
            .andExpect(status().isBadRequest());

        // Validate the RegistroCompra in the database
        List<RegistroCompra> registroCompraList = registroCompraRepository.findAll();
        assertThat(registroCompraList).hasSize(databaseSizeBeforeCreate);

        // Validate the RegistroCompra in Elasticsearch
        verify(mockRegistroCompraSearchRepository, times(0)).save(registroCompra);
    }


    @Test
    @Transactional
    public void getAllRegistroCompras() throws Exception {
        // Initialize the database
        registroCompraRepository.saveAndFlush(registroCompra);

        // Get all the registroCompraList
        restRegistroCompraMockMvc.perform(get("/api/registro-compras?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(registroCompra.getId().intValue())))
            .andExpect(jsonPath("$.[*].consumoMarket").value(hasItem(DEFAULT_CONSUMO_MARKET.booleanValue())));
    }
    
    @Test
    @Transactional
    public void getRegistroCompra() throws Exception {
        // Initialize the database
        registroCompraRepository.saveAndFlush(registroCompra);

        // Get the registroCompra
        restRegistroCompraMockMvc.perform(get("/api/registro-compras/{id}", registroCompra.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(registroCompra.getId().intValue()))
            .andExpect(jsonPath("$.consumoMarket").value(DEFAULT_CONSUMO_MARKET.booleanValue()));
    }

    @Test
    @Transactional
    public void getAllRegistroComprasByConsumoMarketIsEqualToSomething() throws Exception {
        // Initialize the database
        registroCompraRepository.saveAndFlush(registroCompra);

        // Get all the registroCompraList where consumoMarket equals to DEFAULT_CONSUMO_MARKET
        defaultRegistroCompraShouldBeFound("consumoMarket.equals=" + DEFAULT_CONSUMO_MARKET);

        // Get all the registroCompraList where consumoMarket equals to UPDATED_CONSUMO_MARKET
        defaultRegistroCompraShouldNotBeFound("consumoMarket.equals=" + UPDATED_CONSUMO_MARKET);
    }

    @Test
    @Transactional
    public void getAllRegistroComprasByConsumoMarketIsInShouldWork() throws Exception {
        // Initialize the database
        registroCompraRepository.saveAndFlush(registroCompra);

        // Get all the registroCompraList where consumoMarket in DEFAULT_CONSUMO_MARKET or UPDATED_CONSUMO_MARKET
        defaultRegistroCompraShouldBeFound("consumoMarket.in=" + DEFAULT_CONSUMO_MARKET + "," + UPDATED_CONSUMO_MARKET);

        // Get all the registroCompraList where consumoMarket equals to UPDATED_CONSUMO_MARKET
        defaultRegistroCompraShouldNotBeFound("consumoMarket.in=" + UPDATED_CONSUMO_MARKET);
    }

    @Test
    @Transactional
    public void getAllRegistroComprasByConsumoMarketIsNullOrNotNull() throws Exception {
        // Initialize the database
        registroCompraRepository.saveAndFlush(registroCompra);

        // Get all the registroCompraList where consumoMarket is not null
        defaultRegistroCompraShouldBeFound("consumoMarket.specified=true");

        // Get all the registroCompraList where consumoMarket is null
        defaultRegistroCompraShouldNotBeFound("consumoMarket.specified=false");
    }

    @Test
    @Transactional
    public void getAllRegistroComprasByMiembroIsEqualToSomething() throws Exception {
        // Initialize the database
        User miembro = UserResourceIT.createEntity(em);
        em.persist(miembro);
        em.flush();
        registroCompra.setMiembro(miembro);
        registroCompraRepository.saveAndFlush(registroCompra);
        Long miembroId = miembro.getId();

        // Get all the registroCompraList where miembro equals to miembroId
        defaultRegistroCompraShouldBeFound("miembroId.equals=" + miembroId);

        // Get all the registroCompraList where miembro equals to miembroId + 1
        defaultRegistroCompraShouldNotBeFound("miembroId.equals=" + (miembroId + 1));
    }


    @Test
    @Transactional
    public void getAllRegistroComprasByFacturacionIsEqualToSomething() throws Exception {
        // Initialize the database
        Facturacion facturacion = FacturacionResourceIT.createEntity(em);
        em.persist(facturacion);
        em.flush();
        registroCompra.setFacturacion(facturacion);
        registroCompraRepository.saveAndFlush(registroCompra);
        Long facturacionId = facturacion.getId();

        // Get all the registroCompraList where facturacion equals to facturacionId
        defaultRegistroCompraShouldBeFound("facturacionId.equals=" + facturacionId);

        // Get all the registroCompraList where facturacion equals to facturacionId + 1
        defaultRegistroCompraShouldNotBeFound("facturacionId.equals=" + (facturacionId + 1));
    }


    @Test
    @Transactional
    public void getAllRegistroComprasByReservasIsEqualToSomething() throws Exception {
        // Initialize the database
        Reservas reservas = ReservasResourceIT.createEntity(em);
        em.persist(reservas);
        em.flush();
        registroCompra.setReservas(reservas);
        registroCompraRepository.saveAndFlush(registroCompra);
        Long reservasId = reservas.getId();

        // Get all the registroCompraList where reservas equals to reservasId
        defaultRegistroCompraShouldBeFound("reservasId.equals=" + reservasId);

        // Get all the registroCompraList where reservas equals to reservasId + 1
        defaultRegistroCompraShouldNotBeFound("reservasId.equals=" + (reservasId + 1));
    }


    @Test
    @Transactional
    public void getAllRegistroComprasByEntradaInvitadosIsEqualToSomething() throws Exception {
        // Initialize the database
        EntradaInvitados entradaInvitados = EntradaInvitadosResourceIT.createEntity(em);
        em.persist(entradaInvitados);
        em.flush();
        registroCompra.setEntradaInvitados(entradaInvitados);
        registroCompraRepository.saveAndFlush(registroCompra);
        Long entradaInvitadosId = entradaInvitados.getId();

        // Get all the registroCompraList where entradaInvitados equals to entradaInvitadosId
        defaultRegistroCompraShouldBeFound("entradaInvitadosId.equals=" + entradaInvitadosId);

        // Get all the registroCompraList where entradaInvitados equals to entradaInvitadosId + 1
        defaultRegistroCompraShouldNotBeFound("entradaInvitadosId.equals=" + (entradaInvitadosId + 1));
    }


    @Test
    @Transactional
    public void getAllRegistroComprasByEntradaMiembrosIsEqualToSomething() throws Exception {
        // Initialize the database
        EntradaMiembros entradaMiembros = EntradaMiembrosResourceIT.createEntity(em);
        em.persist(entradaMiembros);
        em.flush();
        registroCompra.setEntradaMiembros(entradaMiembros);
        registroCompraRepository.saveAndFlush(registroCompra);
        Long entradaMiembrosId = entradaMiembros.getId();

        // Get all the registroCompraList where entradaMiembros equals to entradaMiembrosId
        defaultRegistroCompraShouldBeFound("entradaMiembrosId.equals=" + entradaMiembrosId);

        // Get all the registroCompraList where entradaMiembros equals to entradaMiembrosId + 1
        defaultRegistroCompraShouldNotBeFound("entradaMiembrosId.equals=" + (entradaMiembrosId + 1));
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultRegistroCompraShouldBeFound(String filter) throws Exception {
        restRegistroCompraMockMvc.perform(get("/api/registro-compras?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(registroCompra.getId().intValue())))
            .andExpect(jsonPath("$.[*].consumoMarket").value(hasItem(DEFAULT_CONSUMO_MARKET.booleanValue())));

        // Check, that the count call also returns 1
        restRegistroCompraMockMvc.perform(get("/api/registro-compras/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultRegistroCompraShouldNotBeFound(String filter) throws Exception {
        restRegistroCompraMockMvc.perform(get("/api/registro-compras?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restRegistroCompraMockMvc.perform(get("/api/registro-compras/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("0"));
    }


    @Test
    @Transactional
    public void getNonExistingRegistroCompra() throws Exception {
        // Get the registroCompra
        restRegistroCompraMockMvc.perform(get("/api/registro-compras/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateRegistroCompra() throws Exception {
        // Initialize the database
        registroCompraRepository.saveAndFlush(registroCompra);

        int databaseSizeBeforeUpdate = registroCompraRepository.findAll().size();

        // Update the registroCompra
        RegistroCompra updatedRegistroCompra = registroCompraRepository.findById(registroCompra.getId()).get();
        // Disconnect from session so that the updates on updatedRegistroCompra are not directly saved in db
        em.detach(updatedRegistroCompra);
        updatedRegistroCompra
            .consumoMarket(UPDATED_CONSUMO_MARKET);
        RegistroCompraDTO registroCompraDTO = registroCompraMapper.toDto(updatedRegistroCompra);

        restRegistroCompraMockMvc.perform(put("/api/registro-compras")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(registroCompraDTO)))
            .andExpect(status().isOk());

        // Validate the RegistroCompra in the database
        List<RegistroCompra> registroCompraList = registroCompraRepository.findAll();
        assertThat(registroCompraList).hasSize(databaseSizeBeforeUpdate);
        RegistroCompra testRegistroCompra = registroCompraList.get(registroCompraList.size() - 1);
        assertThat(testRegistroCompra.isConsumoMarket()).isEqualTo(UPDATED_CONSUMO_MARKET);

        // Validate the RegistroCompra in Elasticsearch
        verify(mockRegistroCompraSearchRepository, times(1)).save(testRegistroCompra);
    }

    @Test
    @Transactional
    public void updateNonExistingRegistroCompra() throws Exception {
        int databaseSizeBeforeUpdate = registroCompraRepository.findAll().size();

        // Create the RegistroCompra
        RegistroCompraDTO registroCompraDTO = registroCompraMapper.toDto(registroCompra);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restRegistroCompraMockMvc.perform(put("/api/registro-compras")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(registroCompraDTO)))
            .andExpect(status().isBadRequest());

        // Validate the RegistroCompra in the database
        List<RegistroCompra> registroCompraList = registroCompraRepository.findAll();
        assertThat(registroCompraList).hasSize(databaseSizeBeforeUpdate);

        // Validate the RegistroCompra in Elasticsearch
        verify(mockRegistroCompraSearchRepository, times(0)).save(registroCompra);
    }

    @Test
    @Transactional
    public void deleteRegistroCompra() throws Exception {
        // Initialize the database
        registroCompraRepository.saveAndFlush(registroCompra);

        int databaseSizeBeforeDelete = registroCompraRepository.findAll().size();

        // Delete the registroCompra
        restRegistroCompraMockMvc.perform(delete("/api/registro-compras/{id}", registroCompra.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<RegistroCompra> registroCompraList = registroCompraRepository.findAll();
        assertThat(registroCompraList).hasSize(databaseSizeBeforeDelete - 1);

        // Validate the RegistroCompra in Elasticsearch
        verify(mockRegistroCompraSearchRepository, times(1)).deleteById(registroCompra.getId());
    }

    @Test
    @Transactional
    public void searchRegistroCompra() throws Exception {
        // Initialize the database
        registroCompraRepository.saveAndFlush(registroCompra);
        when(mockRegistroCompraSearchRepository.search(queryStringQuery("id:" + registroCompra.getId()), PageRequest.of(0, 20)))
            .thenReturn(new PageImpl<>(Collections.singletonList(registroCompra), PageRequest.of(0, 1), 1));
        // Search the registroCompra
        restRegistroCompraMockMvc.perform(get("/api/_search/registro-compras?query=id:" + registroCompra.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(registroCompra.getId().intValue())))
            .andExpect(jsonPath("$.[*].consumoMarket").value(hasItem(DEFAULT_CONSUMO_MARKET.booleanValue())));
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(RegistroCompra.class);
        RegistroCompra registroCompra1 = new RegistroCompra();
        registroCompra1.setId(1L);
        RegistroCompra registroCompra2 = new RegistroCompra();
        registroCompra2.setId(registroCompra1.getId());
        assertThat(registroCompra1).isEqualTo(registroCompra2);
        registroCompra2.setId(2L);
        assertThat(registroCompra1).isNotEqualTo(registroCompra2);
        registroCompra1.setId(null);
        assertThat(registroCompra1).isNotEqualTo(registroCompra2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(RegistroCompraDTO.class);
        RegistroCompraDTO registroCompraDTO1 = new RegistroCompraDTO();
        registroCompraDTO1.setId(1L);
        RegistroCompraDTO registroCompraDTO2 = new RegistroCompraDTO();
        assertThat(registroCompraDTO1).isNotEqualTo(registroCompraDTO2);
        registroCompraDTO2.setId(registroCompraDTO1.getId());
        assertThat(registroCompraDTO1).isEqualTo(registroCompraDTO2);
        registroCompraDTO2.setId(2L);
        assertThat(registroCompraDTO1).isNotEqualTo(registroCompraDTO2);
        registroCompraDTO1.setId(null);
        assertThat(registroCompraDTO1).isNotEqualTo(registroCompraDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(registroCompraMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(registroCompraMapper.fromId(null)).isNull();
    }
}
