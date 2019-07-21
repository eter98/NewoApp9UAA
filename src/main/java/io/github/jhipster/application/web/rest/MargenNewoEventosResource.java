package io.github.jhipster.application.web.rest;

import io.github.jhipster.application.service.MargenNewoEventosService;
import io.github.jhipster.application.web.rest.errors.BadRequestAlertException;
import io.github.jhipster.application.service.dto.MargenNewoEventosDTO;
import io.github.jhipster.application.service.dto.MargenNewoEventosCriteria;
import io.github.jhipster.application.service.MargenNewoEventosQueryService;

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
 * REST controller for managing {@link io.github.jhipster.application.domain.MargenNewoEventos}.
 */
@RestController
@RequestMapping("/api")
public class MargenNewoEventosResource {

    private final Logger log = LoggerFactory.getLogger(MargenNewoEventosResource.class);

    private static final String ENTITY_NAME = "margenNewoEventos";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final MargenNewoEventosService margenNewoEventosService;

    private final MargenNewoEventosQueryService margenNewoEventosQueryService;

    public MargenNewoEventosResource(MargenNewoEventosService margenNewoEventosService, MargenNewoEventosQueryService margenNewoEventosQueryService) {
        this.margenNewoEventosService = margenNewoEventosService;
        this.margenNewoEventosQueryService = margenNewoEventosQueryService;
    }

    /**
     * {@code POST  /margen-newo-eventos} : Create a new margenNewoEventos.
     *
     * @param margenNewoEventosDTO the margenNewoEventosDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new margenNewoEventosDTO, or with status {@code 400 (Bad Request)} if the margenNewoEventos has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/margen-newo-eventos")
    public ResponseEntity<MargenNewoEventosDTO> createMargenNewoEventos(@RequestBody MargenNewoEventosDTO margenNewoEventosDTO) throws URISyntaxException {
        log.debug("REST request to save MargenNewoEventos : {}", margenNewoEventosDTO);
        if (margenNewoEventosDTO.getId() != null) {
            throw new BadRequestAlertException("A new margenNewoEventos cannot already have an ID", ENTITY_NAME, "idexists");
        }
        MargenNewoEventosDTO result = margenNewoEventosService.save(margenNewoEventosDTO);
        return ResponseEntity.created(new URI("/api/margen-newo-eventos/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /margen-newo-eventos} : Updates an existing margenNewoEventos.
     *
     * @param margenNewoEventosDTO the margenNewoEventosDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated margenNewoEventosDTO,
     * or with status {@code 400 (Bad Request)} if the margenNewoEventosDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the margenNewoEventosDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/margen-newo-eventos")
    public ResponseEntity<MargenNewoEventosDTO> updateMargenNewoEventos(@RequestBody MargenNewoEventosDTO margenNewoEventosDTO) throws URISyntaxException {
        log.debug("REST request to update MargenNewoEventos : {}", margenNewoEventosDTO);
        if (margenNewoEventosDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        MargenNewoEventosDTO result = margenNewoEventosService.save(margenNewoEventosDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, margenNewoEventosDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /margen-newo-eventos} : get all the margenNewoEventos.
     *
     * @param pageable the pagination information.
     * @param queryParams a {@link MultiValueMap} query parameters.
     * @param uriBuilder a {@link UriComponentsBuilder} URI builder.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of margenNewoEventos in body.
     */
    @GetMapping("/margen-newo-eventos")
    public ResponseEntity<List<MargenNewoEventosDTO>> getAllMargenNewoEventos(MargenNewoEventosCriteria criteria, Pageable pageable, @RequestParam MultiValueMap<String, String> queryParams, UriComponentsBuilder uriBuilder) {
        log.debug("REST request to get MargenNewoEventos by criteria: {}", criteria);
        Page<MargenNewoEventosDTO> page = margenNewoEventosQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(uriBuilder.queryParams(queryParams), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
    * {@code GET  /margen-newo-eventos/count} : count all the margenNewoEventos.
    *
    * @param criteria the criteria which the requested entities should match.
    * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
    */
    @GetMapping("/margen-newo-eventos/count")
    public ResponseEntity<Long> countMargenNewoEventos(MargenNewoEventosCriteria criteria) {
        log.debug("REST request to count MargenNewoEventos by criteria: {}", criteria);
        return ResponseEntity.ok().body(margenNewoEventosQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /margen-newo-eventos/:id} : get the "id" margenNewoEventos.
     *
     * @param id the id of the margenNewoEventosDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the margenNewoEventosDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/margen-newo-eventos/{id}")
    public ResponseEntity<MargenNewoEventosDTO> getMargenNewoEventos(@PathVariable Long id) {
        log.debug("REST request to get MargenNewoEventos : {}", id);
        Optional<MargenNewoEventosDTO> margenNewoEventosDTO = margenNewoEventosService.findOne(id);
        return ResponseUtil.wrapOrNotFound(margenNewoEventosDTO);
    }

    /**
     * {@code DELETE  /margen-newo-eventos/:id} : delete the "id" margenNewoEventos.
     *
     * @param id the id of the margenNewoEventosDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/margen-newo-eventos/{id}")
    public ResponseEntity<Void> deleteMargenNewoEventos(@PathVariable Long id) {
        log.debug("REST request to delete MargenNewoEventos : {}", id);
        margenNewoEventosService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }

    /**
     * {@code SEARCH  /_search/margen-newo-eventos?query=:query} : search for the margenNewoEventos corresponding
     * to the query.
     *
     * @param query the query of the margenNewoEventos search.
     * @param pageable the pagination information.
     * @param queryParams a {@link MultiValueMap} query parameters.
     * @param uriBuilder a {@link UriComponentsBuilder} URI builder.
     * @return the result of the search.
     */
    @GetMapping("/_search/margen-newo-eventos")
    public ResponseEntity<List<MargenNewoEventosDTO>> searchMargenNewoEventos(@RequestParam String query, Pageable pageable, @RequestParam MultiValueMap<String, String> queryParams, UriComponentsBuilder uriBuilder) {
        log.debug("REST request to search for a page of MargenNewoEventos for query {}", query);
        Page<MargenNewoEventosDTO> page = margenNewoEventosService.search(query, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(uriBuilder.queryParams(queryParams), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

}
