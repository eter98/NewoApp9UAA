package io.github.jhipster.application.web.rest;

import io.github.jhipster.application.NewoApp9UaaApp;
import io.github.jhipster.application.config.TestSecurityConfiguration;
import io.github.jhipster.application.domain.MargenNewoBlog;
import io.github.jhipster.application.domain.Blog;
import io.github.jhipster.application.repository.MargenNewoBlogRepository;
import io.github.jhipster.application.repository.search.MargenNewoBlogSearchRepository;
import io.github.jhipster.application.service.MargenNewoBlogService;
import io.github.jhipster.application.service.dto.MargenNewoBlogDTO;
import io.github.jhipster.application.service.mapper.MargenNewoBlogMapper;
import io.github.jhipster.application.web.rest.errors.ExceptionTranslator;
import io.github.jhipster.application.service.dto.MargenNewoBlogCriteria;
import io.github.jhipster.application.service.MargenNewoBlogQueryService;

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
 * Integration tests for the {@Link MargenNewoBlogResource} REST controller.
 */
@EmbeddedKafka
@SpringBootTest(classes = {NewoApp9UaaApp.class, TestSecurityConfiguration.class})
public class MargenNewoBlogResourceIT {

    private static final Integer DEFAULT_PORCENTAJE_MARGEN = 1;
    private static final Integer UPDATED_PORCENTAJE_MARGEN = 2;

    @Autowired
    private MargenNewoBlogRepository margenNewoBlogRepository;

    @Autowired
    private MargenNewoBlogMapper margenNewoBlogMapper;

    @Autowired
    private MargenNewoBlogService margenNewoBlogService;

    /**
     * This repository is mocked in the io.github.jhipster.application.repository.search test package.
     *
     * @see io.github.jhipster.application.repository.search.MargenNewoBlogSearchRepositoryMockConfiguration
     */
    @Autowired
    private MargenNewoBlogSearchRepository mockMargenNewoBlogSearchRepository;

    @Autowired
    private MargenNewoBlogQueryService margenNewoBlogQueryService;

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

    private MockMvc restMargenNewoBlogMockMvc;

    private MargenNewoBlog margenNewoBlog;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final MargenNewoBlogResource margenNewoBlogResource = new MargenNewoBlogResource(margenNewoBlogService, margenNewoBlogQueryService);
        this.restMargenNewoBlogMockMvc = MockMvcBuilders.standaloneSetup(margenNewoBlogResource)
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
    public static MargenNewoBlog createEntity(EntityManager em) {
        MargenNewoBlog margenNewoBlog = new MargenNewoBlog()
            .porcentajeMargen(DEFAULT_PORCENTAJE_MARGEN);
        return margenNewoBlog;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static MargenNewoBlog createUpdatedEntity(EntityManager em) {
        MargenNewoBlog margenNewoBlog = new MargenNewoBlog()
            .porcentajeMargen(UPDATED_PORCENTAJE_MARGEN);
        return margenNewoBlog;
    }

    @BeforeEach
    public void initTest() {
        margenNewoBlog = createEntity(em);
    }

    @Test
    @Transactional
    public void createMargenNewoBlog() throws Exception {
        int databaseSizeBeforeCreate = margenNewoBlogRepository.findAll().size();

        // Create the MargenNewoBlog
        MargenNewoBlogDTO margenNewoBlogDTO = margenNewoBlogMapper.toDto(margenNewoBlog);
        restMargenNewoBlogMockMvc.perform(post("/api/margen-newo-blogs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(margenNewoBlogDTO)))
            .andExpect(status().isCreated());

        // Validate the MargenNewoBlog in the database
        List<MargenNewoBlog> margenNewoBlogList = margenNewoBlogRepository.findAll();
        assertThat(margenNewoBlogList).hasSize(databaseSizeBeforeCreate + 1);
        MargenNewoBlog testMargenNewoBlog = margenNewoBlogList.get(margenNewoBlogList.size() - 1);
        assertThat(testMargenNewoBlog.getPorcentajeMargen()).isEqualTo(DEFAULT_PORCENTAJE_MARGEN);

        // Validate the MargenNewoBlog in Elasticsearch
        verify(mockMargenNewoBlogSearchRepository, times(1)).save(testMargenNewoBlog);
    }

    @Test
    @Transactional
    public void createMargenNewoBlogWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = margenNewoBlogRepository.findAll().size();

        // Create the MargenNewoBlog with an existing ID
        margenNewoBlog.setId(1L);
        MargenNewoBlogDTO margenNewoBlogDTO = margenNewoBlogMapper.toDto(margenNewoBlog);

        // An entity with an existing ID cannot be created, so this API call must fail
        restMargenNewoBlogMockMvc.perform(post("/api/margen-newo-blogs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(margenNewoBlogDTO)))
            .andExpect(status().isBadRequest());

        // Validate the MargenNewoBlog in the database
        List<MargenNewoBlog> margenNewoBlogList = margenNewoBlogRepository.findAll();
        assertThat(margenNewoBlogList).hasSize(databaseSizeBeforeCreate);

        // Validate the MargenNewoBlog in Elasticsearch
        verify(mockMargenNewoBlogSearchRepository, times(0)).save(margenNewoBlog);
    }


    @Test
    @Transactional
    public void getAllMargenNewoBlogs() throws Exception {
        // Initialize the database
        margenNewoBlogRepository.saveAndFlush(margenNewoBlog);

        // Get all the margenNewoBlogList
        restMargenNewoBlogMockMvc.perform(get("/api/margen-newo-blogs?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(margenNewoBlog.getId().intValue())))
            .andExpect(jsonPath("$.[*].porcentajeMargen").value(hasItem(DEFAULT_PORCENTAJE_MARGEN)));
    }
    
    @Test
    @Transactional
    public void getMargenNewoBlog() throws Exception {
        // Initialize the database
        margenNewoBlogRepository.saveAndFlush(margenNewoBlog);

        // Get the margenNewoBlog
        restMargenNewoBlogMockMvc.perform(get("/api/margen-newo-blogs/{id}", margenNewoBlog.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(margenNewoBlog.getId().intValue()))
            .andExpect(jsonPath("$.porcentajeMargen").value(DEFAULT_PORCENTAJE_MARGEN));
    }

    @Test
    @Transactional
    public void getAllMargenNewoBlogsByPorcentajeMargenIsEqualToSomething() throws Exception {
        // Initialize the database
        margenNewoBlogRepository.saveAndFlush(margenNewoBlog);

        // Get all the margenNewoBlogList where porcentajeMargen equals to DEFAULT_PORCENTAJE_MARGEN
        defaultMargenNewoBlogShouldBeFound("porcentajeMargen.equals=" + DEFAULT_PORCENTAJE_MARGEN);

        // Get all the margenNewoBlogList where porcentajeMargen equals to UPDATED_PORCENTAJE_MARGEN
        defaultMargenNewoBlogShouldNotBeFound("porcentajeMargen.equals=" + UPDATED_PORCENTAJE_MARGEN);
    }

    @Test
    @Transactional
    public void getAllMargenNewoBlogsByPorcentajeMargenIsInShouldWork() throws Exception {
        // Initialize the database
        margenNewoBlogRepository.saveAndFlush(margenNewoBlog);

        // Get all the margenNewoBlogList where porcentajeMargen in DEFAULT_PORCENTAJE_MARGEN or UPDATED_PORCENTAJE_MARGEN
        defaultMargenNewoBlogShouldBeFound("porcentajeMargen.in=" + DEFAULT_PORCENTAJE_MARGEN + "," + UPDATED_PORCENTAJE_MARGEN);

        // Get all the margenNewoBlogList where porcentajeMargen equals to UPDATED_PORCENTAJE_MARGEN
        defaultMargenNewoBlogShouldNotBeFound("porcentajeMargen.in=" + UPDATED_PORCENTAJE_MARGEN);
    }

    @Test
    @Transactional
    public void getAllMargenNewoBlogsByPorcentajeMargenIsNullOrNotNull() throws Exception {
        // Initialize the database
        margenNewoBlogRepository.saveAndFlush(margenNewoBlog);

        // Get all the margenNewoBlogList where porcentajeMargen is not null
        defaultMargenNewoBlogShouldBeFound("porcentajeMargen.specified=true");

        // Get all the margenNewoBlogList where porcentajeMargen is null
        defaultMargenNewoBlogShouldNotBeFound("porcentajeMargen.specified=false");
    }

    @Test
    @Transactional
    public void getAllMargenNewoBlogsByPorcentajeMargenIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        margenNewoBlogRepository.saveAndFlush(margenNewoBlog);

        // Get all the margenNewoBlogList where porcentajeMargen greater than or equals to DEFAULT_PORCENTAJE_MARGEN
        defaultMargenNewoBlogShouldBeFound("porcentajeMargen.greaterOrEqualThan=" + DEFAULT_PORCENTAJE_MARGEN);

        // Get all the margenNewoBlogList where porcentajeMargen greater than or equals to UPDATED_PORCENTAJE_MARGEN
        defaultMargenNewoBlogShouldNotBeFound("porcentajeMargen.greaterOrEqualThan=" + UPDATED_PORCENTAJE_MARGEN);
    }

    @Test
    @Transactional
    public void getAllMargenNewoBlogsByPorcentajeMargenIsLessThanSomething() throws Exception {
        // Initialize the database
        margenNewoBlogRepository.saveAndFlush(margenNewoBlog);

        // Get all the margenNewoBlogList where porcentajeMargen less than or equals to DEFAULT_PORCENTAJE_MARGEN
        defaultMargenNewoBlogShouldNotBeFound("porcentajeMargen.lessThan=" + DEFAULT_PORCENTAJE_MARGEN);

        // Get all the margenNewoBlogList where porcentajeMargen less than or equals to UPDATED_PORCENTAJE_MARGEN
        defaultMargenNewoBlogShouldBeFound("porcentajeMargen.lessThan=" + UPDATED_PORCENTAJE_MARGEN);
    }


    @Test
    @Transactional
    public void getAllMargenNewoBlogsByBlogIsEqualToSomething() throws Exception {
        // Initialize the database
        Blog blog = BlogResourceIT.createEntity(em);
        em.persist(blog);
        em.flush();
        margenNewoBlog.setBlog(blog);
        margenNewoBlogRepository.saveAndFlush(margenNewoBlog);
        Long blogId = blog.getId();

        // Get all the margenNewoBlogList where blog equals to blogId
        defaultMargenNewoBlogShouldBeFound("blogId.equals=" + blogId);

        // Get all the margenNewoBlogList where blog equals to blogId + 1
        defaultMargenNewoBlogShouldNotBeFound("blogId.equals=" + (blogId + 1));
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultMargenNewoBlogShouldBeFound(String filter) throws Exception {
        restMargenNewoBlogMockMvc.perform(get("/api/margen-newo-blogs?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(margenNewoBlog.getId().intValue())))
            .andExpect(jsonPath("$.[*].porcentajeMargen").value(hasItem(DEFAULT_PORCENTAJE_MARGEN)));

        // Check, that the count call also returns 1
        restMargenNewoBlogMockMvc.perform(get("/api/margen-newo-blogs/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultMargenNewoBlogShouldNotBeFound(String filter) throws Exception {
        restMargenNewoBlogMockMvc.perform(get("/api/margen-newo-blogs?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restMargenNewoBlogMockMvc.perform(get("/api/margen-newo-blogs/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("0"));
    }


    @Test
    @Transactional
    public void getNonExistingMargenNewoBlog() throws Exception {
        // Get the margenNewoBlog
        restMargenNewoBlogMockMvc.perform(get("/api/margen-newo-blogs/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateMargenNewoBlog() throws Exception {
        // Initialize the database
        margenNewoBlogRepository.saveAndFlush(margenNewoBlog);

        int databaseSizeBeforeUpdate = margenNewoBlogRepository.findAll().size();

        // Update the margenNewoBlog
        MargenNewoBlog updatedMargenNewoBlog = margenNewoBlogRepository.findById(margenNewoBlog.getId()).get();
        // Disconnect from session so that the updates on updatedMargenNewoBlog are not directly saved in db
        em.detach(updatedMargenNewoBlog);
        updatedMargenNewoBlog
            .porcentajeMargen(UPDATED_PORCENTAJE_MARGEN);
        MargenNewoBlogDTO margenNewoBlogDTO = margenNewoBlogMapper.toDto(updatedMargenNewoBlog);

        restMargenNewoBlogMockMvc.perform(put("/api/margen-newo-blogs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(margenNewoBlogDTO)))
            .andExpect(status().isOk());

        // Validate the MargenNewoBlog in the database
        List<MargenNewoBlog> margenNewoBlogList = margenNewoBlogRepository.findAll();
        assertThat(margenNewoBlogList).hasSize(databaseSizeBeforeUpdate);
        MargenNewoBlog testMargenNewoBlog = margenNewoBlogList.get(margenNewoBlogList.size() - 1);
        assertThat(testMargenNewoBlog.getPorcentajeMargen()).isEqualTo(UPDATED_PORCENTAJE_MARGEN);

        // Validate the MargenNewoBlog in Elasticsearch
        verify(mockMargenNewoBlogSearchRepository, times(1)).save(testMargenNewoBlog);
    }

    @Test
    @Transactional
    public void updateNonExistingMargenNewoBlog() throws Exception {
        int databaseSizeBeforeUpdate = margenNewoBlogRepository.findAll().size();

        // Create the MargenNewoBlog
        MargenNewoBlogDTO margenNewoBlogDTO = margenNewoBlogMapper.toDto(margenNewoBlog);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restMargenNewoBlogMockMvc.perform(put("/api/margen-newo-blogs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(margenNewoBlogDTO)))
            .andExpect(status().isBadRequest());

        // Validate the MargenNewoBlog in the database
        List<MargenNewoBlog> margenNewoBlogList = margenNewoBlogRepository.findAll();
        assertThat(margenNewoBlogList).hasSize(databaseSizeBeforeUpdate);

        // Validate the MargenNewoBlog in Elasticsearch
        verify(mockMargenNewoBlogSearchRepository, times(0)).save(margenNewoBlog);
    }

    @Test
    @Transactional
    public void deleteMargenNewoBlog() throws Exception {
        // Initialize the database
        margenNewoBlogRepository.saveAndFlush(margenNewoBlog);

        int databaseSizeBeforeDelete = margenNewoBlogRepository.findAll().size();

        // Delete the margenNewoBlog
        restMargenNewoBlogMockMvc.perform(delete("/api/margen-newo-blogs/{id}", margenNewoBlog.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<MargenNewoBlog> margenNewoBlogList = margenNewoBlogRepository.findAll();
        assertThat(margenNewoBlogList).hasSize(databaseSizeBeforeDelete - 1);

        // Validate the MargenNewoBlog in Elasticsearch
        verify(mockMargenNewoBlogSearchRepository, times(1)).deleteById(margenNewoBlog.getId());
    }

    @Test
    @Transactional
    public void searchMargenNewoBlog() throws Exception {
        // Initialize the database
        margenNewoBlogRepository.saveAndFlush(margenNewoBlog);
        when(mockMargenNewoBlogSearchRepository.search(queryStringQuery("id:" + margenNewoBlog.getId()), PageRequest.of(0, 20)))
            .thenReturn(new PageImpl<>(Collections.singletonList(margenNewoBlog), PageRequest.of(0, 1), 1));
        // Search the margenNewoBlog
        restMargenNewoBlogMockMvc.perform(get("/api/_search/margen-newo-blogs?query=id:" + margenNewoBlog.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(margenNewoBlog.getId().intValue())))
            .andExpect(jsonPath("$.[*].porcentajeMargen").value(hasItem(DEFAULT_PORCENTAJE_MARGEN)));
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(MargenNewoBlog.class);
        MargenNewoBlog margenNewoBlog1 = new MargenNewoBlog();
        margenNewoBlog1.setId(1L);
        MargenNewoBlog margenNewoBlog2 = new MargenNewoBlog();
        margenNewoBlog2.setId(margenNewoBlog1.getId());
        assertThat(margenNewoBlog1).isEqualTo(margenNewoBlog2);
        margenNewoBlog2.setId(2L);
        assertThat(margenNewoBlog1).isNotEqualTo(margenNewoBlog2);
        margenNewoBlog1.setId(null);
        assertThat(margenNewoBlog1).isNotEqualTo(margenNewoBlog2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(MargenNewoBlogDTO.class);
        MargenNewoBlogDTO margenNewoBlogDTO1 = new MargenNewoBlogDTO();
        margenNewoBlogDTO1.setId(1L);
        MargenNewoBlogDTO margenNewoBlogDTO2 = new MargenNewoBlogDTO();
        assertThat(margenNewoBlogDTO1).isNotEqualTo(margenNewoBlogDTO2);
        margenNewoBlogDTO2.setId(margenNewoBlogDTO1.getId());
        assertThat(margenNewoBlogDTO1).isEqualTo(margenNewoBlogDTO2);
        margenNewoBlogDTO2.setId(2L);
        assertThat(margenNewoBlogDTO1).isNotEqualTo(margenNewoBlogDTO2);
        margenNewoBlogDTO1.setId(null);
        assertThat(margenNewoBlogDTO1).isNotEqualTo(margenNewoBlogDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(margenNewoBlogMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(margenNewoBlogMapper.fromId(null)).isNull();
    }
}
