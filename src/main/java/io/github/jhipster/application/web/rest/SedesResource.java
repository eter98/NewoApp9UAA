package io.github.jhipster.application.web.rest;

import io.github.jhipster.application.service.SedesService;
import io.github.jhipster.application.web.rest.errors.BadRequestAlertException;
import io.github.jhipster.application.service.dto.SedesDTO;
import io.github.jhipster.application.service.dto.SedesCriteria;
import io.github.jhipster.application.service.SedesQueryService;

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

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;
import java.util.stream.StreamSupport;

import static org.elasticsearch.index.query.QueryBuilders.*;

/**
 * REST controller for managing {@link io.github.jhipster.application.domain.Sedes}.
 */
@RestController
@RequestMapping("/api")
public class SedesResource {

    private final Logger log = LoggerFactory.getLogger(SedesResource.class);

    private static final String ENTITY_NAME = "sedes";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final SedesService sedesService;

    private final SedesQueryService sedesQueryService;

    public SedesResource(SedesService sedesService, SedesQueryService sedesQueryService) {
        this.sedesService = sedesService;
        this.sedesQueryService = sedesQueryService;
    }

    /**
     * {@code POST  /sedes} : Create a new sedes.
     *
     * @param sedesDTO the sedesDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new sedesDTO, or with status {@code 400 (Bad Request)} if the sedes has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/sedes")
    public ResponseEntity<SedesDTO> createSedes(@Valid @RequestBody SedesDTO sedesDTO) throws URISyntaxException {
        log.debug("REST request to save Sedes : {}", sedesDTO);
        if (sedesDTO.getId() != null) {
            throw new BadRequestAlertException("A new sedes cannot already have an ID", ENTITY_NAME, "idexists");
        }
        SedesDTO result = sedesService.save(sedesDTO);
        return ResponseEntity.created(new URI("/api/sedes/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /sedes} : Updates an existing sedes.
     *
     * @param sedesDTO the sedesDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated sedesDTO,
     * or with status {@code 400 (Bad Request)} if the sedesDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the sedesDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/sedes")
    public ResponseEntity<SedesDTO> updateSedes(@Valid @RequestBody SedesDTO sedesDTO) throws URISyntaxException {
        log.debug("REST request to update Sedes : {}", sedesDTO);
        if (sedesDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        SedesDTO result = sedesService.save(sedesDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, sedesDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /sedes} : get all the sedes.
     *
     * @param pageable the pagination information.
     * @param queryParams a {@link MultiValueMap} query parameters.
     * @param uriBuilder a {@link UriComponentsBuilder} URI builder.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of sedes in body.
     */
    @GetMapping("/sedes")
    public ResponseEntity<List<SedesDTO>> getAllSedes(SedesCriteria criteria, Pageable pageable, @RequestParam MultiValueMap<String, String> queryParams, UriComponentsBuilder uriBuilder) {
        log.debug("REST request to get Sedes by criteria: {}", criteria);
        Page<SedesDTO> page = sedesQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(uriBuilder.queryParams(queryParams), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
    * {@code GET  /sedes/count} : count all the sedes.
    *
    * @param criteria the criteria which the requested entities should match.
    * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
    */
    @GetMapping("/sedes/count")
    public ResponseEntity<Long> countSedes(SedesCriteria criteria) {
        log.debug("REST request to count Sedes by criteria: {}", criteria);
        return ResponseEntity.ok().body(sedesQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /sedes/:id} : get the "id" sedes.
     *
     * @param id the id of the sedesDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the sedesDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/sedes/{id}")
    public ResponseEntity<SedesDTO> getSedes(@PathVariable Long id) {
        log.debug("REST request to get Sedes : {}", id);
        Optional<SedesDTO> sedesDTO = sedesService.findOne(id);
        return ResponseUtil.wrapOrNotFound(sedesDTO);
    }

    /**
     * {@code DELETE  /sedes/:id} : delete the "id" sedes.
     *
     * @param id the id of the sedesDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/sedes/{id}")
    public ResponseEntity<Void> deleteSedes(@PathVariable Long id) {
        log.debug("REST request to delete Sedes : {}", id);
        sedesService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }

    /**
     * {@code SEARCH  /_search/sedes?query=:query} : search for the sedes corresponding
     * to the query.
     *
     * @param query the query of the sedes search.
     * @param pageable the pagination information.
     * @param queryParams a {@link MultiValueMap} query parameters.
     * @param uriBuilder a {@link UriComponentsBuilder} URI builder.
     * @return the result of the search.
     */
    @GetMapping("/_search/sedes")
    public ResponseEntity<List<SedesDTO>> searchSedes(@RequestParam String query, Pageable pageable, @RequestParam MultiValueMap<String, String> queryParams, UriComponentsBuilder uriBuilder) {
        log.debug("REST request to search for a page of Sedes for query {}", query);
        Page<SedesDTO> page = sedesService.search(query, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(uriBuilder.queryParams(queryParams), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

}
