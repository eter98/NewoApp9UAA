package io.github.jhipster.application.web.rest;

import io.github.jhipster.application.service.PrepagoConsumoService;
import io.github.jhipster.application.web.rest.errors.BadRequestAlertException;
import io.github.jhipster.application.service.dto.PrepagoConsumoDTO;
import io.github.jhipster.application.service.dto.PrepagoConsumoCriteria;
import io.github.jhipster.application.service.PrepagoConsumoQueryService;

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
 * REST controller for managing {@link io.github.jhipster.application.domain.PrepagoConsumo}.
 */
@RestController
@RequestMapping("/api")
public class PrepagoConsumoResource {

    private final Logger log = LoggerFactory.getLogger(PrepagoConsumoResource.class);

    private static final String ENTITY_NAME = "prepagoConsumo";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final PrepagoConsumoService prepagoConsumoService;

    private final PrepagoConsumoQueryService prepagoConsumoQueryService;

    public PrepagoConsumoResource(PrepagoConsumoService prepagoConsumoService, PrepagoConsumoQueryService prepagoConsumoQueryService) {
        this.prepagoConsumoService = prepagoConsumoService;
        this.prepagoConsumoQueryService = prepagoConsumoQueryService;
    }

    /**
     * {@code POST  /prepago-consumos} : Create a new prepagoConsumo.
     *
     * @param prepagoConsumoDTO the prepagoConsumoDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new prepagoConsumoDTO, or with status {@code 400 (Bad Request)} if the prepagoConsumo has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/prepago-consumos")
    public ResponseEntity<PrepagoConsumoDTO> createPrepagoConsumo(@RequestBody PrepagoConsumoDTO prepagoConsumoDTO) throws URISyntaxException {
        log.debug("REST request to save PrepagoConsumo : {}", prepagoConsumoDTO);
        if (prepagoConsumoDTO.getId() != null) {
            throw new BadRequestAlertException("A new prepagoConsumo cannot already have an ID", ENTITY_NAME, "idexists");
        }
        PrepagoConsumoDTO result = prepagoConsumoService.save(prepagoConsumoDTO);
        return ResponseEntity.created(new URI("/api/prepago-consumos/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /prepago-consumos} : Updates an existing prepagoConsumo.
     *
     * @param prepagoConsumoDTO the prepagoConsumoDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated prepagoConsumoDTO,
     * or with status {@code 400 (Bad Request)} if the prepagoConsumoDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the prepagoConsumoDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/prepago-consumos")
    public ResponseEntity<PrepagoConsumoDTO> updatePrepagoConsumo(@RequestBody PrepagoConsumoDTO prepagoConsumoDTO) throws URISyntaxException {
        log.debug("REST request to update PrepagoConsumo : {}", prepagoConsumoDTO);
        if (prepagoConsumoDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        PrepagoConsumoDTO result = prepagoConsumoService.save(prepagoConsumoDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, prepagoConsumoDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /prepago-consumos} : get all the prepagoConsumos.
     *
     * @param pageable the pagination information.
     * @param queryParams a {@link MultiValueMap} query parameters.
     * @param uriBuilder a {@link UriComponentsBuilder} URI builder.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of prepagoConsumos in body.
     */
    @GetMapping("/prepago-consumos")
    public ResponseEntity<List<PrepagoConsumoDTO>> getAllPrepagoConsumos(PrepagoConsumoCriteria criteria, Pageable pageable, @RequestParam MultiValueMap<String, String> queryParams, UriComponentsBuilder uriBuilder) {
        log.debug("REST request to get PrepagoConsumos by criteria: {}", criteria);
        Page<PrepagoConsumoDTO> page = prepagoConsumoQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(uriBuilder.queryParams(queryParams), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
    * {@code GET  /prepago-consumos/count} : count all the prepagoConsumos.
    *
    * @param criteria the criteria which the requested entities should match.
    * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
    */
    @GetMapping("/prepago-consumos/count")
    public ResponseEntity<Long> countPrepagoConsumos(PrepagoConsumoCriteria criteria) {
        log.debug("REST request to count PrepagoConsumos by criteria: {}", criteria);
        return ResponseEntity.ok().body(prepagoConsumoQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /prepago-consumos/:id} : get the "id" prepagoConsumo.
     *
     * @param id the id of the prepagoConsumoDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the prepagoConsumoDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/prepago-consumos/{id}")
    public ResponseEntity<PrepagoConsumoDTO> getPrepagoConsumo(@PathVariable Long id) {
        log.debug("REST request to get PrepagoConsumo : {}", id);
        Optional<PrepagoConsumoDTO> prepagoConsumoDTO = prepagoConsumoService.findOne(id);
        return ResponseUtil.wrapOrNotFound(prepagoConsumoDTO);
    }

    /**
     * {@code DELETE  /prepago-consumos/:id} : delete the "id" prepagoConsumo.
     *
     * @param id the id of the prepagoConsumoDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/prepago-consumos/{id}")
    public ResponseEntity<Void> deletePrepagoConsumo(@PathVariable Long id) {
        log.debug("REST request to delete PrepagoConsumo : {}", id);
        prepagoConsumoService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }

    /**
     * {@code SEARCH  /_search/prepago-consumos?query=:query} : search for the prepagoConsumo corresponding
     * to the query.
     *
     * @param query the query of the prepagoConsumo search.
     * @param pageable the pagination information.
     * @param queryParams a {@link MultiValueMap} query parameters.
     * @param uriBuilder a {@link UriComponentsBuilder} URI builder.
     * @return the result of the search.
     */
    @GetMapping("/_search/prepago-consumos")
    public ResponseEntity<List<PrepagoConsumoDTO>> searchPrepagoConsumos(@RequestParam String query, Pageable pageable, @RequestParam MultiValueMap<String, String> queryParams, UriComponentsBuilder uriBuilder) {
        log.debug("REST request to search for a page of PrepagoConsumos for query {}", query);
        Page<PrepagoConsumoDTO> page = prepagoConsumoService.search(query, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(uriBuilder.queryParams(queryParams), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

}
