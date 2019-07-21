package io.github.jhipster.application.web.rest;

import io.github.jhipster.application.NewoApp9UaaApp;
import io.github.jhipster.application.config.TestSecurityConfiguration;
import io.github.jhipster.application.domain.MargenNewoEventos;
import io.github.jhipster.application.domain.Evento;
import io.github.jhipster.application.repository.MargenNewoEventosRepository;
import io.github.jhipster.application.repository.search.MargenNewoEventosSearchRepository;
import io.github.jhipster.application.service.MargenNewoEventosService;
import io.github.jhipster.application.service.dto.MargenNewoEventosDTO;
import io.github.jhipster.application.service.mapper.MargenNewoEventosMapper;
import io.github.jhipster.application.web.rest.errors.ExceptionTranslator;
import io.github.jhipster.application.service.dto.MargenNewoEventosCriteria;
import io.github.jhipster.application.service.MargenNewoEventosQueryService;

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
 * Integration tests for the {@Link MargenNewoEventosResource} REST controller.
 */
@EmbeddedKafka
@SpringBootTest(classes = {NewoApp9UaaApp.class, TestSecurityConfiguration.class})
public class MargenNewoEventosResourceIT {

    private static final Integer DEFAULT_PORCENTAJE_MARGEN = 1;
    private static final Integer UPDATED_PORCENTAJE_MARGEN = 2;

    @Autowired
    private MargenNewoEventosRepository margenNewoEventosRepository;

    @Autowired
    private MargenNewoEventosMapper margenNewoEventosMapper;

    @Autowired
    private MargenNewoEventosService margenNewoEventosService;

    /**
     * This repository is mocked in the io.github.jhipster.application.repository.search test package.
     *
     * @see io.github.jhipster.application.repository.search.MargenNewoEventosSearchRepositoryMockConfiguration
     */
    @Autowired
    private MargenNewoEventosSearchRepository mockMargenNewoEventosSearchRepository;

    @Autowired
    private MargenNewoEventosQueryService margenNewoEventosQueryService;

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

    private MockMvc restMargenNewoEventosMockMvc;

    private MargenNewoEventos margenNewoEventos;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final MargenNewoEventosResource margenNewoEventosResource = new MargenNewoEventosResource(margenNewoEventosService, margenNewoEventosQueryService);
        this.restMargenNewoEventosMockMvc = MockMvcBuilders.standaloneSetup(margenNewoEventosResource)
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
    public static MargenNewoEventos createEntity(EntityManager em) {
        MargenNewoEventos margenNewoEventos = new MargenNewoEventos()
            .porcentajeMargen(DEFAULT_PORCENTAJE_MARGEN);
        return margenNewoEventos;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static MargenNewoEventos createUpdatedEntity(EntityManager em) {
        MargenNewoEventos margenNewoEventos = new MargenNewoEventos()
            .porcentajeMargen(UPDATED_PORCENTAJE_MARGEN);
        return margenNewoEventos;
    }

    @BeforeEach
    public void initTest() {
        margenNewoEventos = createEntity(em);
    }

    @Test
    @Transactional
    public void createMargenNewoEventos() throws Exception {
        int databaseSizeBeforeCreate = margenNewoEventosRepository.findAll().size();

        // Create the MargenNewoEventos
        MargenNewoEventosDTO margenNewoEventosDTO = margenNewoEventosMapper.toDto(margenNewoEventos);
        restMargenNewoEventosMockMvc.perform(post("/api/margen-newo-eventos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(margenNewoEventosDTO)))
            .andExpect(status().isCreated());

        // Validate the MargenNewoEventos in the database
        List<MargenNewoEventos> margenNewoEventosList = margenNewoEventosRepository.findAll();
        assertThat(margenNewoEventosList).hasSize(databaseSizeBeforeCreate + 1);
        MargenNewoEventos testMargenNewoEventos = margenNewoEventosList.get(margenNewoEventosList.size() - 1);
        assertThat(testMargenNewoEventos.getPorcentajeMargen()).isEqualTo(DEFAULT_PORCENTAJE_MARGEN);

        // Validate the MargenNewoEventos in Elasticsearch
        verify(mockMargenNewoEventosSearchRepository, times(1)).save(testMargenNewoEventos);
    }

    @Test
    @Transactional
    public void createMargenNewoEventosWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = margenNewoEventosRepository.findAll().size();

        // Create the MargenNewoEventos with an existing ID
        margenNewoEventos.setId(1L);
        MargenNewoEventosDTO margenNewoEventosDTO = margenNewoEventosMapper.toDto(margenNewoEventos);

        // An entity with an existing ID cannot be created, so this API call must fail
        restMargenNewoEventosMockMvc.perform(post("/api/margen-newo-eventos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(margenNewoEventosDTO)))
            .andExpect(status().isBadRequest());

        // Validate the MargenNewoEventos in the database
        List<MargenNewoEventos> margenNewoEventosList = margenNewoEventosRepository.findAll();
        assertThat(margenNewoEventosList).hasSize(databaseSizeBeforeCreate);

        // Validate the MargenNewoEventos in Elasticsearch
        verify(mockMargenNewoEventosSearchRepository, times(0)).save(margenNewoEventos);
    }


    @Test
    @Transactional
    public void getAllMargenNewoEventos() throws Exception {
        // Initialize the database
        margenNewoEventosRepository.saveAndFlush(margenNewoEventos);

        // Get all the margenNewoEventosList
        restMargenNewoEventosMockMvc.perform(get("/api/margen-newo-eventos?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(margenNewoEventos.getId().intValue())))
            .andExpect(jsonPath("$.[*].porcentajeMargen").value(hasItem(DEFAULT_PORCENTAJE_MARGEN)));
    }
    
    @Test
    @Transactional
    public void getMargenNewoEventos() throws Exception {
        // Initialize the database
        margenNewoEventosRepository.saveAndFlush(margenNewoEventos);

        // Get the margenNewoEventos
        restMargenNewoEventosMockMvc.perform(get("/api/margen-newo-eventos/{id}", margenNewoEventos.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(margenNewoEventos.getId().intValue()))
            .andExpect(jsonPath("$.porcentajeMargen").value(DEFAULT_PORCENTAJE_MARGEN));
    }

    @Test
    @Transactional
    public void getAllMargenNewoEventosByPorcentajeMargenIsEqualToSomething() throws Exception {
        // Initialize the database
        margenNewoEventosRepository.saveAndFlush(margenNewoEventos);

        // Get all the margenNewoEventosList where porcentajeMargen equals to DEFAULT_PORCENTAJE_MARGEN
        defaultMargenNewoEventosShouldBeFound("porcentajeMargen.equals=" + DEFAULT_PORCENTAJE_MARGEN);

        // Get all the margenNewoEventosList where porcentajeMargen equals to UPDATED_PORCENTAJE_MARGEN
        defaultMargenNewoEventosShouldNotBeFound("porcentajeMargen.equals=" + UPDATED_PORCENTAJE_MARGEN);
    }

    @Test
    @Transactional
    public void getAllMargenNewoEventosByPorcentajeMargenIsInShouldWork() throws Exception {
        // Initialize the database
        margenNewoEventosRepository.saveAndFlush(margenNewoEventos);

        // Get all the margenNewoEventosList where porcentajeMargen in DEFAULT_PORCENTAJE_MARGEN or UPDATED_PORCENTAJE_MARGEN
        defaultMargenNewoEventosShouldBeFound("porcentajeMargen.in=" + DEFAULT_PORCENTAJE_MARGEN + "," + UPDATED_PORCENTAJE_MARGEN);

        // Get all the margenNewoEventosList where porcentajeMargen equals to UPDATED_PORCENTAJE_MARGEN
        defaultMargenNewoEventosShouldNotBeFound("porcentajeMargen.in=" + UPDATED_PORCENTAJE_MARGEN);
    }

    @Test
    @Transactional
    public void getAllMargenNewoEventosByPorcentajeMargenIsNullOrNotNull() throws Exception {
        // Initialize the database
        margenNewoEventosRepository.saveAndFlush(margenNewoEventos);

        // Get all the margenNewoEventosList where porcentajeMargen is not null
        defaultMargenNewoEventosShouldBeFound("porcentajeMargen.specified=true");

        // Get all the margenNewoEventosList where porcentajeMargen is null
        defaultMargenNewoEventosShouldNotBeFound("porcentajeMargen.specified=false");
    }

    @Test
    @Transactional
    public void getAllMargenNewoEventosByPorcentajeMargenIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        margenNewoEventosRepository.saveAndFlush(margenNewoEventos);

        // Get all the margenNewoEventosList where porcentajeMargen greater than or equals to DEFAULT_PORCENTAJE_MARGEN
        defaultMargenNewoEventosShouldBeFound("porcentajeMargen.greaterOrEqualThan=" + DEFAULT_PORCENTAJE_MARGEN);

        // Get all the margenNewoEventosList where porcentajeMargen greater than or equals to UPDATED_PORCENTAJE_MARGEN
        defaultMargenNewoEventosShouldNotBeFound("porcentajeMargen.greaterOrEqualThan=" + UPDATED_PORCENTAJE_MARGEN);
    }

    @Test
    @Transactional
    public void getAllMargenNewoEventosByPorcentajeMargenIsLessThanSomething() throws Exception {
        // Initialize the database
        margenNewoEventosRepository.saveAndFlush(margenNewoEventos);

        // Get all the margenNewoEventosList where porcentajeMargen less than or equals to DEFAULT_PORCENTAJE_MARGEN
        defaultMargenNewoEventosShouldNotBeFound("porcentajeMargen.lessThan=" + DEFAULT_PORCENTAJE_MARGEN);

        // Get all the margenNewoEventosList where porcentajeMargen less than or equals to UPDATED_PORCENTAJE_MARGEN
        defaultMargenNewoEventosShouldBeFound("porcentajeMargen.lessThan=" + UPDATED_PORCENTAJE_MARGEN);
    }


    @Test
    @Transactional
    public void getAllMargenNewoEventosByEventoIsEqualToSomething() throws Exception {
        // Initialize the database
        Evento evento = EventoResourceIT.createEntity(em);
        em.persist(evento);
        em.flush();
        margenNewoEventos.setEvento(evento);
        margenNewoEventosRepository.saveAndFlush(margenNewoEventos);
        Long eventoId = evento.getId();

        // Get all the margenNewoEventosList where evento equals to eventoId
        defaultMargenNewoEventosShouldBeFound("eventoId.equals=" + eventoId);

        // Get all the margenNewoEventosList where evento equals to eventoId + 1
        defaultMargenNewoEventosShouldNotBeFound("eventoId.equals=" + (eventoId + 1));
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultMargenNewoEventosShouldBeFound(String filter) throws Exception {
        restMargenNewoEventosMockMvc.perform(get("/api/margen-newo-eventos?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(margenNewoEventos.getId().intValue())))
            .andExpect(jsonPath("$.[*].porcentajeMargen").value(hasItem(DEFAULT_PORCENTAJE_MARGEN)));

        // Check, that the count call also returns 1
        restMargenNewoEventosMockMvc.perform(get("/api/margen-newo-eventos/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultMargenNewoEventosShouldNotBeFound(String filter) throws Exception {
        restMargenNewoEventosMockMvc.perform(get("/api/margen-newo-eventos?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restMargenNewoEventosMockMvc.perform(get("/api/margen-newo-eventos/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("0"));
    }


    @Test
    @Transactional
    public void getNonExistingMargenNewoEventos() throws Exception {
        // Get the margenNewoEventos
        restMargenNewoEventosMockMvc.perform(get("/api/margen-newo-eventos/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateMargenNewoEventos() throws Exception {
        // Initialize the database
        margenNewoEventosRepository.saveAndFlush(margenNewoEventos);

        int databaseSizeBeforeUpdate = margenNewoEventosRepository.findAll().size();

        // Update the margenNewoEventos
        MargenNewoEventos updatedMargenNewoEventos = margenNewoEventosRepository.findById(margenNewoEventos.getId()).get();
        // Disconnect from session so that the updates on updatedMargenNewoEventos are not directly saved in db
        em.detach(updatedMargenNewoEventos);
        updatedMargenNewoEventos
            .porcentajeMargen(UPDATED_PORCENTAJE_MARGEN);
        MargenNewoEventosDTO margenNewoEventosDTO = margenNewoEventosMapper.toDto(updatedMargenNewoEventos);

        restMargenNewoEventosMockMvc.perform(put("/api/margen-newo-eventos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(margenNewoEventosDTO)))
            .andExpect(status().isOk());

        // Validate the MargenNewoEventos in the database
        List<MargenNewoEventos> margenNewoEventosList = margenNewoEventosRepository.findAll();
        assertThat(margenNewoEventosList).hasSize(databaseSizeBeforeUpdate);
        MargenNewoEventos testMargenNewoEventos = margenNewoEventosList.get(margenNewoEventosList.size() - 1);
        assertThat(testMargenNewoEventos.getPorcentajeMargen()).isEqualTo(UPDATED_PORCENTAJE_MARGEN);

        // Validate the MargenNewoEventos in Elasticsearch
        verify(mockMargenNewoEventosSearchRepository, times(1)).save(testMargenNewoEventos);
    }

    @Test
    @Transactional
    public void updateNonExistingMargenNewoEventos() throws Exception {
        int databaseSizeBeforeUpdate = margenNewoEventosRepository.findAll().size();

        // Create the MargenNewoEventos
        MargenNewoEventosDTO margenNewoEventosDTO = margenNewoEventosMapper.toDto(margenNewoEventos);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restMargenNewoEventosMockMvc.perform(put("/api/margen-newo-eventos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(margenNewoEventosDTO)))
            .andExpect(status().isBadRequest());

        // Validate the MargenNewoEventos in the database
        List<MargenNewoEventos> margenNewoEventosList = margenNewoEventosRepository.findAll();
        assertThat(margenNewoEventosList).hasSize(databaseSizeBeforeUpdate);

        // Validate the MargenNewoEventos in Elasticsearch
        verify(mockMargenNewoEventosSearchRepository, times(0)).save(margenNewoEventos);
    }

    @Test
    @Transactional
    public void deleteMargenNewoEventos() throws Exception {
        // Initialize the database
        margenNewoEventosRepository.saveAndFlush(margenNewoEventos);

        int databaseSizeBeforeDelete = margenNewoEventosRepository.findAll().size();

        // Delete the margenNewoEventos
        restMargenNewoEventosMockMvc.perform(delete("/api/margen-newo-eventos/{id}", margenNewoEventos.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<MargenNewoEventos> margenNewoEventosList = margenNewoEventosRepository.findAll();
        assertThat(margenNewoEventosList).hasSize(databaseSizeBeforeDelete - 1);

        // Validate the MargenNewoEventos in Elasticsearch
        verify(mockMargenNewoEventosSearchRepository, times(1)).deleteById(margenNewoEventos.getId());
    }

    @Test
    @Transactional
    public void searchMargenNewoEventos() throws Exception {
        // Initialize the database
        margenNewoEventosRepository.saveAndFlush(margenNewoEventos);
        when(mockMargenNewoEventosSearchRepository.search(queryStringQuery("id:" + margenNewoEventos.getId()), PageRequest.of(0, 20)))
            .thenReturn(new PageImpl<>(Collections.singletonList(margenNewoEventos), PageRequest.of(0, 1), 1));
        // Search the margenNewoEventos
        restMargenNewoEventosMockMvc.perform(get("/api/_search/margen-newo-eventos?query=id:" + margenNewoEventos.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(margenNewoEventos.getId().intValue())))
            .andExpect(jsonPath("$.[*].porcentajeMargen").value(hasItem(DEFAULT_PORCENTAJE_MARGEN)));
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(MargenNewoEventos.class);
        MargenNewoEventos margenNewoEventos1 = new MargenNewoEventos();
        margenNewoEventos1.setId(1L);
        MargenNewoEventos margenNewoEventos2 = new MargenNewoEventos();
        margenNewoEventos2.setId(margenNewoEventos1.getId());
        assertThat(margenNewoEventos1).isEqualTo(margenNewoEventos2);
        margenNewoEventos2.setId(2L);
        assertThat(margenNewoEventos1).isNotEqualTo(margenNewoEventos2);
        margenNewoEventos1.setId(null);
        assertThat(margenNewoEventos1).isNotEqualTo(margenNewoEventos2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(MargenNewoEventosDTO.class);
        MargenNewoEventosDTO margenNewoEventosDTO1 = new MargenNewoEventosDTO();
        margenNewoEventosDTO1.setId(1L);
        MargenNewoEventosDTO margenNewoEventosDTO2 = new MargenNewoEventosDTO();
        assertThat(margenNewoEventosDTO1).isNotEqualTo(margenNewoEventosDTO2);
        margenNewoEventosDTO2.setId(margenNewoEventosDTO1.getId());
        assertThat(margenNewoEventosDTO1).isEqualTo(margenNewoEventosDTO2);
        margenNewoEventosDTO2.setId(2L);
        assertThat(margenNewoEventosDTO1).isNotEqualTo(margenNewoEventosDTO2);
        margenNewoEventosDTO1.setId(null);
        assertThat(margenNewoEventosDTO1).isNotEqualTo(margenNewoEventosDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(margenNewoEventosMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(margenNewoEventosMapper.fromId(null)).isNull();
    }
}
