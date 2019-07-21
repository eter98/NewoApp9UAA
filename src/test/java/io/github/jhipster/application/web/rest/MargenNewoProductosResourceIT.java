package io.github.jhipster.application.web.rest;

import io.github.jhipster.application.NewoApp9UaaApp;
import io.github.jhipster.application.config.TestSecurityConfiguration;
import io.github.jhipster.application.domain.MargenNewoProductos;
import io.github.jhipster.application.domain.ProductosServicios;
import io.github.jhipster.application.repository.MargenNewoProductosRepository;
import io.github.jhipster.application.repository.search.MargenNewoProductosSearchRepository;
import io.github.jhipster.application.service.MargenNewoProductosService;
import io.github.jhipster.application.service.dto.MargenNewoProductosDTO;
import io.github.jhipster.application.service.mapper.MargenNewoProductosMapper;
import io.github.jhipster.application.web.rest.errors.ExceptionTranslator;
import io.github.jhipster.application.service.dto.MargenNewoProductosCriteria;
import io.github.jhipster.application.service.MargenNewoProductosQueryService;

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
 * Integration tests for the {@Link MargenNewoProductosResource} REST controller.
 */
@EmbeddedKafka
@SpringBootTest(classes = {NewoApp9UaaApp.class, TestSecurityConfiguration.class})
public class MargenNewoProductosResourceIT {

    private static final Integer DEFAULT_PORCENTAJE_MARGEN = 1;
    private static final Integer UPDATED_PORCENTAJE_MARGEN = 2;

    @Autowired
    private MargenNewoProductosRepository margenNewoProductosRepository;

    @Autowired
    private MargenNewoProductosMapper margenNewoProductosMapper;

    @Autowired
    private MargenNewoProductosService margenNewoProductosService;

    /**
     * This repository is mocked in the io.github.jhipster.application.repository.search test package.
     *
     * @see io.github.jhipster.application.repository.search.MargenNewoProductosSearchRepositoryMockConfiguration
     */
    @Autowired
    private MargenNewoProductosSearchRepository mockMargenNewoProductosSearchRepository;

    @Autowired
    private MargenNewoProductosQueryService margenNewoProductosQueryService;

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

    private MockMvc restMargenNewoProductosMockMvc;

    private MargenNewoProductos margenNewoProductos;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final MargenNewoProductosResource margenNewoProductosResource = new MargenNewoProductosResource(margenNewoProductosService, margenNewoProductosQueryService);
        this.restMargenNewoProductosMockMvc = MockMvcBuilders.standaloneSetup(margenNewoProductosResource)
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
    public static MargenNewoProductos createEntity(EntityManager em) {
        MargenNewoProductos margenNewoProductos = new MargenNewoProductos()
            .porcentajeMargen(DEFAULT_PORCENTAJE_MARGEN);
        return margenNewoProductos;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static MargenNewoProductos createUpdatedEntity(EntityManager em) {
        MargenNewoProductos margenNewoProductos = new MargenNewoProductos()
            .porcentajeMargen(UPDATED_PORCENTAJE_MARGEN);
        return margenNewoProductos;
    }

    @BeforeEach
    public void initTest() {
        margenNewoProductos = createEntity(em);
    }

    @Test
    @Transactional
    public void createMargenNewoProductos() throws Exception {
        int databaseSizeBeforeCreate = margenNewoProductosRepository.findAll().size();

        // Create the MargenNewoProductos
        MargenNewoProductosDTO margenNewoProductosDTO = margenNewoProductosMapper.toDto(margenNewoProductos);
        restMargenNewoProductosMockMvc.perform(post("/api/margen-newo-productos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(margenNewoProductosDTO)))
            .andExpect(status().isCreated());

        // Validate the MargenNewoProductos in the database
        List<MargenNewoProductos> margenNewoProductosList = margenNewoProductosRepository.findAll();
        assertThat(margenNewoProductosList).hasSize(databaseSizeBeforeCreate + 1);
        MargenNewoProductos testMargenNewoProductos = margenNewoProductosList.get(margenNewoProductosList.size() - 1);
        assertThat(testMargenNewoProductos.getPorcentajeMargen()).isEqualTo(DEFAULT_PORCENTAJE_MARGEN);

        // Validate the MargenNewoProductos in Elasticsearch
        verify(mockMargenNewoProductosSearchRepository, times(1)).save(testMargenNewoProductos);
    }

    @Test
    @Transactional
    public void createMargenNewoProductosWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = margenNewoProductosRepository.findAll().size();

        // Create the MargenNewoProductos with an existing ID
        margenNewoProductos.setId(1L);
        MargenNewoProductosDTO margenNewoProductosDTO = margenNewoProductosMapper.toDto(margenNewoProductos);

        // An entity with an existing ID cannot be created, so this API call must fail
        restMargenNewoProductosMockMvc.perform(post("/api/margen-newo-productos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(margenNewoProductosDTO)))
            .andExpect(status().isBadRequest());

        // Validate the MargenNewoProductos in the database
        List<MargenNewoProductos> margenNewoProductosList = margenNewoProductosRepository.findAll();
        assertThat(margenNewoProductosList).hasSize(databaseSizeBeforeCreate);

        // Validate the MargenNewoProductos in Elasticsearch
        verify(mockMargenNewoProductosSearchRepository, times(0)).save(margenNewoProductos);
    }


    @Test
    @Transactional
    public void getAllMargenNewoProductos() throws Exception {
        // Initialize the database
        margenNewoProductosRepository.saveAndFlush(margenNewoProductos);

        // Get all the margenNewoProductosList
        restMargenNewoProductosMockMvc.perform(get("/api/margen-newo-productos?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(margenNewoProductos.getId().intValue())))
            .andExpect(jsonPath("$.[*].porcentajeMargen").value(hasItem(DEFAULT_PORCENTAJE_MARGEN)));
    }
    
    @Test
    @Transactional
    public void getMargenNewoProductos() throws Exception {
        // Initialize the database
        margenNewoProductosRepository.saveAndFlush(margenNewoProductos);

        // Get the margenNewoProductos
        restMargenNewoProductosMockMvc.perform(get("/api/margen-newo-productos/{id}", margenNewoProductos.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(margenNewoProductos.getId().intValue()))
            .andExpect(jsonPath("$.porcentajeMargen").value(DEFAULT_PORCENTAJE_MARGEN));
    }

    @Test
    @Transactional
    public void getAllMargenNewoProductosByPorcentajeMargenIsEqualToSomething() throws Exception {
        // Initialize the database
        margenNewoProductosRepository.saveAndFlush(margenNewoProductos);

        // Get all the margenNewoProductosList where porcentajeMargen equals to DEFAULT_PORCENTAJE_MARGEN
        defaultMargenNewoProductosShouldBeFound("porcentajeMargen.equals=" + DEFAULT_PORCENTAJE_MARGEN);

        // Get all the margenNewoProductosList where porcentajeMargen equals to UPDATED_PORCENTAJE_MARGEN
        defaultMargenNewoProductosShouldNotBeFound("porcentajeMargen.equals=" + UPDATED_PORCENTAJE_MARGEN);
    }

    @Test
    @Transactional
    public void getAllMargenNewoProductosByPorcentajeMargenIsInShouldWork() throws Exception {
        // Initialize the database
        margenNewoProductosRepository.saveAndFlush(margenNewoProductos);

        // Get all the margenNewoProductosList where porcentajeMargen in DEFAULT_PORCENTAJE_MARGEN or UPDATED_PORCENTAJE_MARGEN
        defaultMargenNewoProductosShouldBeFound("porcentajeMargen.in=" + DEFAULT_PORCENTAJE_MARGEN + "," + UPDATED_PORCENTAJE_MARGEN);

        // Get all the margenNewoProductosList where porcentajeMargen equals to UPDATED_PORCENTAJE_MARGEN
        defaultMargenNewoProductosShouldNotBeFound("porcentajeMargen.in=" + UPDATED_PORCENTAJE_MARGEN);
    }

    @Test
    @Transactional
    public void getAllMargenNewoProductosByPorcentajeMargenIsNullOrNotNull() throws Exception {
        // Initialize the database
        margenNewoProductosRepository.saveAndFlush(margenNewoProductos);

        // Get all the margenNewoProductosList where porcentajeMargen is not null
        defaultMargenNewoProductosShouldBeFound("porcentajeMargen.specified=true");

        // Get all the margenNewoProductosList where porcentajeMargen is null
        defaultMargenNewoProductosShouldNotBeFound("porcentajeMargen.specified=false");
    }

    @Test
    @Transactional
    public void getAllMargenNewoProductosByPorcentajeMargenIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        margenNewoProductosRepository.saveAndFlush(margenNewoProductos);

        // Get all the margenNewoProductosList where porcentajeMargen greater than or equals to DEFAULT_PORCENTAJE_MARGEN
        defaultMargenNewoProductosShouldBeFound("porcentajeMargen.greaterOrEqualThan=" + DEFAULT_PORCENTAJE_MARGEN);

        // Get all the margenNewoProductosList where porcentajeMargen greater than or equals to UPDATED_PORCENTAJE_MARGEN
        defaultMargenNewoProductosShouldNotBeFound("porcentajeMargen.greaterOrEqualThan=" + UPDATED_PORCENTAJE_MARGEN);
    }

    @Test
    @Transactional
    public void getAllMargenNewoProductosByPorcentajeMargenIsLessThanSomething() throws Exception {
        // Initialize the database
        margenNewoProductosRepository.saveAndFlush(margenNewoProductos);

        // Get all the margenNewoProductosList where porcentajeMargen less than or equals to DEFAULT_PORCENTAJE_MARGEN
        defaultMargenNewoProductosShouldNotBeFound("porcentajeMargen.lessThan=" + DEFAULT_PORCENTAJE_MARGEN);

        // Get all the margenNewoProductosList where porcentajeMargen less than or equals to UPDATED_PORCENTAJE_MARGEN
        defaultMargenNewoProductosShouldBeFound("porcentajeMargen.lessThan=" + UPDATED_PORCENTAJE_MARGEN);
    }


    @Test
    @Transactional
    public void getAllMargenNewoProductosByProductoIsEqualToSomething() throws Exception {
        // Initialize the database
        ProductosServicios producto = ProductosServiciosResourceIT.createEntity(em);
        em.persist(producto);
        em.flush();
        margenNewoProductos.setProducto(producto);
        margenNewoProductosRepository.saveAndFlush(margenNewoProductos);
        Long productoId = producto.getId();

        // Get all the margenNewoProductosList where producto equals to productoId
        defaultMargenNewoProductosShouldBeFound("productoId.equals=" + productoId);

        // Get all the margenNewoProductosList where producto equals to productoId + 1
        defaultMargenNewoProductosShouldNotBeFound("productoId.equals=" + (productoId + 1));
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultMargenNewoProductosShouldBeFound(String filter) throws Exception {
        restMargenNewoProductosMockMvc.perform(get("/api/margen-newo-productos?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(margenNewoProductos.getId().intValue())))
            .andExpect(jsonPath("$.[*].porcentajeMargen").value(hasItem(DEFAULT_PORCENTAJE_MARGEN)));

        // Check, that the count call also returns 1
        restMargenNewoProductosMockMvc.perform(get("/api/margen-newo-productos/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultMargenNewoProductosShouldNotBeFound(String filter) throws Exception {
        restMargenNewoProductosMockMvc.perform(get("/api/margen-newo-productos?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restMargenNewoProductosMockMvc.perform(get("/api/margen-newo-productos/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("0"));
    }


    @Test
    @Transactional
    public void getNonExistingMargenNewoProductos() throws Exception {
        // Get the margenNewoProductos
        restMargenNewoProductosMockMvc.perform(get("/api/margen-newo-productos/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateMargenNewoProductos() throws Exception {
        // Initialize the database
        margenNewoProductosRepository.saveAndFlush(margenNewoProductos);

        int databaseSizeBeforeUpdate = margenNewoProductosRepository.findAll().size();

        // Update the margenNewoProductos
        MargenNewoProductos updatedMargenNewoProductos = margenNewoProductosRepository.findById(margenNewoProductos.getId()).get();
        // Disconnect from session so that the updates on updatedMargenNewoProductos are not directly saved in db
        em.detach(updatedMargenNewoProductos);
        updatedMargenNewoProductos
            .porcentajeMargen(UPDATED_PORCENTAJE_MARGEN);
        MargenNewoProductosDTO margenNewoProductosDTO = margenNewoProductosMapper.toDto(updatedMargenNewoProductos);

        restMargenNewoProductosMockMvc.perform(put("/api/margen-newo-productos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(margenNewoProductosDTO)))
            .andExpect(status().isOk());

        // Validate the MargenNewoProductos in the database
        List<MargenNewoProductos> margenNewoProductosList = margenNewoProductosRepository.findAll();
        assertThat(margenNewoProductosList).hasSize(databaseSizeBeforeUpdate);
        MargenNewoProductos testMargenNewoProductos = margenNewoProductosList.get(margenNewoProductosList.size() - 1);
        assertThat(testMargenNewoProductos.getPorcentajeMargen()).isEqualTo(UPDATED_PORCENTAJE_MARGEN);

        // Validate the MargenNewoProductos in Elasticsearch
        verify(mockMargenNewoProductosSearchRepository, times(1)).save(testMargenNewoProductos);
    }

    @Test
    @Transactional
    public void updateNonExistingMargenNewoProductos() throws Exception {
        int databaseSizeBeforeUpdate = margenNewoProductosRepository.findAll().size();

        // Create the MargenNewoProductos
        MargenNewoProductosDTO margenNewoProductosDTO = margenNewoProductosMapper.toDto(margenNewoProductos);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restMargenNewoProductosMockMvc.perform(put("/api/margen-newo-productos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(margenNewoProductosDTO)))
            .andExpect(status().isBadRequest());

        // Validate the MargenNewoProductos in the database
        List<MargenNewoProductos> margenNewoProductosList = margenNewoProductosRepository.findAll();
        assertThat(margenNewoProductosList).hasSize(databaseSizeBeforeUpdate);

        // Validate the MargenNewoProductos in Elasticsearch
        verify(mockMargenNewoProductosSearchRepository, times(0)).save(margenNewoProductos);
    }

    @Test
    @Transactional
    public void deleteMargenNewoProductos() throws Exception {
        // Initialize the database
        margenNewoProductosRepository.saveAndFlush(margenNewoProductos);

        int databaseSizeBeforeDelete = margenNewoProductosRepository.findAll().size();

        // Delete the margenNewoProductos
        restMargenNewoProductosMockMvc.perform(delete("/api/margen-newo-productos/{id}", margenNewoProductos.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<MargenNewoProductos> margenNewoProductosList = margenNewoProductosRepository.findAll();
        assertThat(margenNewoProductosList).hasSize(databaseSizeBeforeDelete - 1);

        // Validate the MargenNewoProductos in Elasticsearch
        verify(mockMargenNewoProductosSearchRepository, times(1)).deleteById(margenNewoProductos.getId());
    }

    @Test
    @Transactional
    public void searchMargenNewoProductos() throws Exception {
        // Initialize the database
        margenNewoProductosRepository.saveAndFlush(margenNewoProductos);
        when(mockMargenNewoProductosSearchRepository.search(queryStringQuery("id:" + margenNewoProductos.getId()), PageRequest.of(0, 20)))
            .thenReturn(new PageImpl<>(Collections.singletonList(margenNewoProductos), PageRequest.of(0, 1), 1));
        // Search the margenNewoProductos
        restMargenNewoProductosMockMvc.perform(get("/api/_search/margen-newo-productos?query=id:" + margenNewoProductos.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(margenNewoProductos.getId().intValue())))
            .andExpect(jsonPath("$.[*].porcentajeMargen").value(hasItem(DEFAULT_PORCENTAJE_MARGEN)));
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(MargenNewoProductos.class);
        MargenNewoProductos margenNewoProductos1 = new MargenNewoProductos();
        margenNewoProductos1.setId(1L);
        MargenNewoProductos margenNewoProductos2 = new MargenNewoProductos();
        margenNewoProductos2.setId(margenNewoProductos1.getId());
        assertThat(margenNewoProductos1).isEqualTo(margenNewoProductos2);
        margenNewoProductos2.setId(2L);
        assertThat(margenNewoProductos1).isNotEqualTo(margenNewoProductos2);
        margenNewoProductos1.setId(null);
        assertThat(margenNewoProductos1).isNotEqualTo(margenNewoProductos2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(MargenNewoProductosDTO.class);
        MargenNewoProductosDTO margenNewoProductosDTO1 = new MargenNewoProductosDTO();
        margenNewoProductosDTO1.setId(1L);
        MargenNewoProductosDTO margenNewoProductosDTO2 = new MargenNewoProductosDTO();
        assertThat(margenNewoProductosDTO1).isNotEqualTo(margenNewoProductosDTO2);
        margenNewoProductosDTO2.setId(margenNewoProductosDTO1.getId());
        assertThat(margenNewoProductosDTO1).isEqualTo(margenNewoProductosDTO2);
        margenNewoProductosDTO2.setId(2L);
        assertThat(margenNewoProductosDTO1).isNotEqualTo(margenNewoProductosDTO2);
        margenNewoProductosDTO1.setId(null);
        assertThat(margenNewoProductosDTO1).isNotEqualTo(margenNewoProductosDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(margenNewoProductosMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(margenNewoProductosMapper.fromId(null)).isNull();
    }
}
