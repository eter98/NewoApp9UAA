package io.github.jhipster.application.web.rest;

import io.github.jhipster.application.service.MargenNewoGruposService;
import io.github.jhipster.application.web.rest.errors.BadRequestAlertException;
import io.github.jhipster.application.service.dto.MargenNewoGruposDTO;
import io.github.jhipster.application.service.dto.MargenNewoGruposCriteria;
import io.github.jhipster.application.service.MargenNewoGruposQueryService;

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
 * REST controller for managing {@link io.github.jhipster.application.domain.MargenNewoGrupos}.
 */
@RestController
@RequestMapping("/api")
public class MargenNewoGruposResource {

    private final Logger log = LoggerFactory.getLogger(MargenNewoGruposResource.class);

    private static final String ENTITY_NAME = "margenNewoGrupos";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final MargenNewoGruposService margenNewoGruposService;

    private final MargenNewoGruposQueryService margenNewoGruposQueryService;

    public MargenNewoGruposResource(MargenNewoGruposService margenNewoGruposService, MargenNewoGruposQueryService margenNewoGruposQueryService) {
        this.margenNewoGruposService = margenNewoGruposService;
        this.margenNewoGruposQueryService = margenNewoGruposQueryService;
    }

    /**
     * {@code POST  /margen-newo-grupos} : Create a new margenNewoGrupos.
     *
     * @param margenNewoGruposDTO the margenNewoGruposDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new margenNewoGruposDTO, or with status {@code 400 (Bad Request)} if the margenNewoGrupos has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/margen-newo-grupos")
    public ResponseEntity<MargenNewoGruposDTO> createMargenNewoGrupos(@RequestBody MargenNewoGruposDTO margenNewoGruposDTO) throws URISyntaxException {
        log.debug("REST request to save MargenNewoGrupos : {}", margenNewoGruposDTO);
        if (margenNewoGruposDTO.getId() != null) {
            throw new BadRequestAlertException("A new margenNewoGrupos cannot already have an ID", ENTITY_NAME, "idexists");
        }
        MargenNewoGruposDTO result = margenNewoGruposService.save(margenNewoGruposDTO);
        return ResponseEntity.created(new URI("/api/margen-newo-grupos/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /margen-newo-grupos} : Updates an existing margenNewoGrupos.
     *
     * @param margenNewoGruposDTO the margenNewoGruposDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated margenNewoGruposDTO,
     * or with status {@code 400 (Bad Request)} if the margenNewoGruposDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the margenNewoGruposDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/margen-newo-grupos")
    public ResponseEntity<MargenNewoGruposDTO> updateMargenNewoGrupos(@RequestBody MargenNewoGruposDTO margenNewoGruposDTO) throws URISyntaxException {
        log.debug("REST request to update MargenNewoGrupos : {}", margenNewoGruposDTO);
        if (margenNewoGruposDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        MargenNewoGruposDTO result = margenNewoGruposService.save(margenNewoGruposDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, margenNewoGruposDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /margen-newo-grupos} : get all the margenNewoGrupos.
     *
     * @param pageable the pagination information.
     * @param queryParams a {@link MultiValueMap} query parameters.
     * @param uriBuilder a {@link UriComponentsBuilder} URI builder.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of margenNewoGrupos in body.
     */
    @GetMapping("/margen-newo-grupos")
    public ResponseEntity<List<MargenNewoGruposDTO>> getAllMargenNewoGrupos(MargenNewoGruposCriteria criteria, Pageable pageable, @RequestParam MultiValueMap<String, String> queryParams, UriComponentsBuilder uriBuilder) {
        log.debug("REST request to get MargenNewoGrupos by criteria: {}", criteria);
        Page<MargenNewoGruposDTO> page = margenNewoGruposQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(uriBuilder.queryParams(queryParams), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
    * {@code GET  /margen-newo-grupos/count} : count all the margenNewoGrupos.
    *
    * @param criteria the criteria which the requested entities should match.
    * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
    */
    @GetMapping("/margen-newo-grupos/count")
    public ResponseEntity<Long> countMargenNewoGrupos(MargenNewoGruposCriteria criteria) {
        log.debug("REST request to count MargenNewoGrupos by criteria: {}", criteria);
        return ResponseEntity.ok().body(margenNewoGruposQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /margen-newo-grupos/:id} : get the "id" margenNewoGrupos.
     *
     * @param id the id of the margenNewoGruposDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the margenNewoGruposDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/margen-newo-grupos/{id}")
    public ResponseEntity<MargenNewoGruposDTO> getMargenNewoGrupos(@PathVariable Long id) {
        log.debug("REST request to get MargenNewoGrupos : {}", id);
        Optional<MargenNewoGruposDTO> margenNewoGruposDTO = margenNewoGruposService.findOne(id);
        return ResponseUtil.wrapOrNotFound(margenNewoGruposDTO);
    }

    /**
     * {@code DELETE  /margen-newo-grupos/:id} : delete the "id" margenNewoGrupos.
     *
     * @param id the id of the margenNewoGruposDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/margen-newo-grupos/{id}")
    public ResponseEntity<Void> deleteMargenNewoGrupos(@PathVariable Long id) {
        log.debug("REST request to delete MargenNewoGrupos : {}", id);
        margenNewoGruposService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }

    /**
     * {@code SEARCH  /_search/margen-newo-grupos?query=:query} : search for the margenNewoGrupos corresponding
     * to the query.
     *
     * @param query the query of the margenNewoGrupos search.
     * @param pageable the pagination information.
     * @param queryParams a {@link MultiValueMap} query parameters.
     * @param uriBuilder a {@link UriComponentsBuilder} URI builder.
     * @return the result of the search.
     */
    @GetMapping("/_search/margen-newo-grupos")
    public ResponseEntity<List<MargenNewoGruposDTO>> searchMargenNewoGrupos(@RequestParam String query, Pageable pageable, @RequestParam MultiValueMap<String, String> queryParams, UriComponentsBuilder uriBuilder) {
        log.debug("REST request to search for a page of MargenNewoGrupos for query {}", query);
        Page<MargenNewoGruposDTO> page = margenNewoGruposService.search(query, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(uriBuilder.queryParams(queryParams), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

}
