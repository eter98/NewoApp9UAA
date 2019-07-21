package io.github.jhipster.application.web.rest;

import io.github.jhipster.application.service.UsoRecursoFisicoService;
import io.github.jhipster.application.web.rest.errors.BadRequestAlertException;
import io.github.jhipster.application.service.dto.UsoRecursoFisicoDTO;
import io.github.jhipster.application.service.dto.UsoRecursoFisicoCriteria;
import io.github.jhipster.application.service.UsoRecursoFisicoQueryService;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.PaginationUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.util.MultiValueMap;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;
import java.util.stream.StreamSupport;

import static org.elasticsearch.index.query.QueryBuilders.*;

/**
 * REST controller for managing {@link io.github.jhipster.application.domain.UsoRecursoFisico}.
 */
@RestController
@RequestMapping("/api")
public class UsoRecursoFisicoResource {

    private final Logger log = LoggerFactory.getLogger(UsoRecursoFisicoResource.class);

    private static final String ENTITY_NAME = "usoRecursoFisico";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final UsoRecursoFisicoService usoRecursoFisicoService;

    private final UsoRecursoFisicoQueryService usoRecursoFisicoQueryService;

    public UsoRecursoFisicoResource(UsoRecursoFisicoService usoRecursoFisicoService, UsoRecursoFisicoQueryService usoRecursoFisicoQueryService) {
        this.usoRecursoFisicoService = usoRecursoFisicoService;
        this.usoRecursoFisicoQueryService = usoRecursoFisicoQueryService;
    }

    /**
     * {@code POST  /uso-recurso-fisicos} : Create a new usoRecursoFisico.
     *
     * @param usoRecursoFisicoDTO the usoRecursoFisicoDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new usoRecursoFisicoDTO, or with status {@code 400 (Bad Request)} if the usoRecursoFisico has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/uso-recurso-fisicos")
    public ResponseEntity<UsoRecursoFisicoDTO> createUsoRecursoFisico(@RequestBody UsoRecursoFisicoDTO usoRecursoFisicoDTO) throws URISyntaxException {
        log.debug("REST request to save UsoRecursoFisico : {}", usoRecursoFisicoDTO);
        if (usoRecursoFisicoDTO.getId() != null) {
            throw new BadRequestAlertException("A new usoRecursoFisico cannot already have an ID", ENTITY_NAME, "idexists");
        }
        UsoRecursoFisicoDTO result = usoRecursoFisicoService.save(usoRecursoFisicoDTO);
        return ResponseEntity.created(new URI("/api/uso-recurso-fisicos/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /uso-recurso-fisicos} : Updates an existing usoRecursoFisico.
     *
     * @param usoRecursoFisicoDTO the usoRecursoFisicoDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated usoRecursoFisicoDTO,
     * or with status {@code 400 (Bad Request)} if the usoRecursoFisicoDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the usoRecursoFisicoDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/uso-recurso-fisicos")
    public ResponseEntity<UsoRecursoFisicoDTO> updateUsoRecursoFisico(@RequestBody UsoRecursoFisicoDTO usoRecursoFisicoDTO) throws URISyntaxException {
        log.debug("REST request to update UsoRecursoFisico : {}", usoRecursoFisicoDTO);
        if (usoRecursoFisicoDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        UsoRecursoFisicoDTO result = usoRecursoFisicoService.save(usoRecursoFisicoDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, usoRecursoFisicoDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /uso-recurso-fisicos} : get all the usoRecursoFisicos.
     *
     * @param pageable the pagination information.
     * @param queryParams a {@link MultiValueMap} query parameters.
     * @param uriBuilder a {@link UriComponentsBuilder} URI builder.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of usoRecursoFisicos in body.
     */
    @GetMapping("/uso-recurso-fisicos")
    public ResponseEntity<List<UsoRecursoFisicoDTO>> getAllUsoRecursoFisicos(UsoRecursoFisicoCriteria criteria, Pageable pageable, @RequestParam MultiValueMap<String, String> queryParams, UriComponentsBuilder uriBuilder) {
        log.debug("REST request to get UsoRecursoFisicos by criteria: {}", criteria);
        Page<UsoRecursoFisicoDTO> page = usoRecursoFisicoQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(uriBuilder.queryParams(queryParams), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
    * {@code GET  /uso-recurso-fisicos/count} : count all the usoRecursoFisicos.
    *
    * @param criteria the criteria which the requested entities should match.
    * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
    */
    @GetMapping("/uso-recurso-fisicos/count")
    public ResponseEntity<Long> countUsoRecursoFisicos(UsoRecursoFisicoCriteria criteria) {
        log.debug("REST request to count UsoRecursoFisicos by criteria: {}", criteria);
        return ResponseEntity.ok().body(usoRecursoFisicoQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /uso-recurso-fisicos/:id} : get the "id" usoRecursoFisico.
     *
     * @param id the id of the usoRecursoFisicoDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the usoRecursoFisicoDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/uso-recurso-fisicos/{id}")
    public ResponseEntity<UsoRecursoFisicoDTO> getUsoRecursoFisico(@PathVariable Long id) {
        log.debug("REST request to get UsoRecursoFisico : {}", id);
        Optional<UsoRecursoFisicoDTO> usoRecursoFisicoDTO = usoRecursoFisicoService.findOne(id);
        return ResponseUtil.wrapOrNotFound(usoRecursoFisicoDTO);
    }

    /**
     * {@code DELETE  /uso-recurso-fisicos/:id} : delete the "id" usoRecursoFisico.
     *
     * @param id the id of the usoRecursoFisicoDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/uso-recurso-fisicos/{id}")
    public ResponseEntity<Void> deleteUsoRecursoFisico(@PathVariable Long id) {
        log.debug("REST request to delete UsoRecursoFisico : {}", id);
        usoRecursoFisicoService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }

    /**
     * {@code SEARCH  /_search/uso-recurso-fisicos?query=:query} : search for the usoRecursoFisico corresponding
     * to the query.
     *
     * @param query the query of the usoRecursoFisico search.
     * @param pageable the pagination information.
     * @param queryParams a {@link MultiValueMap} query parameters.
     * @param uriBuilder a {@link UriComponentsBuilder} URI builder.
     * @return the result of the search.
     */
    @GetMapping("/_search/uso-recurso-fisicos")
    public ResponseEntity<List<UsoRecursoFisicoDTO>> searchUsoRecursoFisicos(@RequestParam String query, Pageable pageable, @RequestParam MultiValueMap<String, String> queryParams, UriComponentsBuilder uriBuilder) {
        log.debug("REST request to search for a page of UsoRecursoFisicos for query {}", query);
        Page<UsoRecursoFisicoDTO> page = usoRecursoFisicoService.search(query, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(uriBuilder.queryParams(queryParams), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

}
