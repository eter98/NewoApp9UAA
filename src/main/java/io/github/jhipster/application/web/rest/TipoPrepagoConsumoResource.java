package io.github.jhipster.application.web.rest;

import io.github.jhipster.application.service.TipoPrepagoConsumoService;
import io.github.jhipster.application.web.rest.errors.BadRequestAlertException;
import io.github.jhipster.application.service.dto.TipoPrepagoConsumoDTO;
import io.github.jhipster.application.service.dto.TipoPrepagoConsumoCriteria;
import io.github.jhipster.application.service.TipoPrepagoConsumoQueryService;

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
 * REST controller for managing {@link io.github.jhipster.application.domain.TipoPrepagoConsumo}.
 */
@RestController
@RequestMapping("/api")
public class TipoPrepagoConsumoResource {

    private final Logger log = LoggerFactory.getLogger(TipoPrepagoConsumoResource.class);

    private static final String ENTITY_NAME = "tipoPrepagoConsumo";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final TipoPrepagoConsumoService tipoPrepagoConsumoService;

    private final TipoPrepagoConsumoQueryService tipoPrepagoConsumoQueryService;

    public TipoPrepagoConsumoResource(TipoPrepagoConsumoService tipoPrepagoConsumoService, TipoPrepagoConsumoQueryService tipoPrepagoConsumoQueryService) {
        this.tipoPrepagoConsumoService = tipoPrepagoConsumoService;
        this.tipoPrepagoConsumoQueryService = tipoPrepagoConsumoQueryService;
    }

    /**
     * {@code POST  /tipo-prepago-consumos} : Create a new tipoPrepagoConsumo.
     *
     * @param tipoPrepagoConsumoDTO the tipoPrepagoConsumoDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new tipoPrepagoConsumoDTO, or with status {@code 400 (Bad Request)} if the tipoPrepagoConsumo has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/tipo-prepago-consumos")
    public ResponseEntity<TipoPrepagoConsumoDTO> createTipoPrepagoConsumo(@Valid @RequestBody TipoPrepagoConsumoDTO tipoPrepagoConsumoDTO) throws URISyntaxException {
        log.debug("REST request to save TipoPrepagoConsumo : {}", tipoPrepagoConsumoDTO);
        if (tipoPrepagoConsumoDTO.getId() != null) {
            throw new BadRequestAlertException("A new tipoPrepagoConsumo cannot already have an ID", ENTITY_NAME, "idexists");
        }
        TipoPrepagoConsumoDTO result = tipoPrepagoConsumoService.save(tipoPrepagoConsumoDTO);
        return ResponseEntity.created(new URI("/api/tipo-prepago-consumos/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /tipo-prepago-consumos} : Updates an existing tipoPrepagoConsumo.
     *
     * @param tipoPrepagoConsumoDTO the tipoPrepagoConsumoDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated tipoPrepagoConsumoDTO,
     * or with status {@code 400 (Bad Request)} if the tipoPrepagoConsumoDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the tipoPrepagoConsumoDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/tipo-prepago-consumos")
    public ResponseEntity<TipoPrepagoConsumoDTO> updateTipoPrepagoConsumo(@Valid @RequestBody TipoPrepagoConsumoDTO tipoPrepagoConsumoDTO) throws URISyntaxException {
        log.debug("REST request to update TipoPrepagoConsumo : {}", tipoPrepagoConsumoDTO);
        if (tipoPrepagoConsumoDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        TipoPrepagoConsumoDTO result = tipoPrepagoConsumoService.save(tipoPrepagoConsumoDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, tipoPrepagoConsumoDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /tipo-prepago-consumos} : get all the tipoPrepagoConsumos.
     *
     * @param pageable the pagination information.
     * @param queryParams a {@link MultiValueMap} query parameters.
     * @param uriBuilder a {@link UriComponentsBuilder} URI builder.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of tipoPrepagoConsumos in body.
     */
    @GetMapping("/tipo-prepago-consumos")
    public ResponseEntity<List<TipoPrepagoConsumoDTO>> getAllTipoPrepagoConsumos(TipoPrepagoConsumoCriteria criteria, Pageable pageable, @RequestParam MultiValueMap<String, String> queryParams, UriComponentsBuilder uriBuilder) {
        log.debug("REST request to get TipoPrepagoConsumos by criteria: {}", criteria);
        Page<TipoPrepagoConsumoDTO> page = tipoPrepagoConsumoQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(uriBuilder.queryParams(queryParams), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
    * {@code GET  /tipo-prepago-consumos/count} : count all the tipoPrepagoConsumos.
    *
    * @param criteria the criteria which the requested entities should match.
    * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
    */
    @GetMapping("/tipo-prepago-consumos/count")
    public ResponseEntity<Long> countTipoPrepagoConsumos(TipoPrepagoConsumoCriteria criteria) {
        log.debug("REST request to count TipoPrepagoConsumos by criteria: {}", criteria);
        return ResponseEntity.ok().body(tipoPrepagoConsumoQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /tipo-prepago-consumos/:id} : get the "id" tipoPrepagoConsumo.
     *
     * @param id the id of the tipoPrepagoConsumoDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the tipoPrepagoConsumoDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/tipo-prepago-consumos/{id}")
    public ResponseEntity<TipoPrepagoConsumoDTO> getTipoPrepagoConsumo(@PathVariable Long id) {
        log.debug("REST request to get TipoPrepagoConsumo : {}", id);
        Optional<TipoPrepagoConsumoDTO> tipoPrepagoConsumoDTO = tipoPrepagoConsumoService.findOne(id);
        return ResponseUtil.wrapOrNotFound(tipoPrepagoConsumoDTO);
    }

    /**
     * {@code DELETE  /tipo-prepago-consumos/:id} : delete the "id" tipoPrepagoConsumo.
     *
     * @param id the id of the tipoPrepagoConsumoDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/tipo-prepago-consumos/{id}")
    public ResponseEntity<Void> deleteTipoPrepagoConsumo(@PathVariable Long id) {
        log.debug("REST request to delete TipoPrepagoConsumo : {}", id);
        tipoPrepagoConsumoService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }

    /**
     * {@code SEARCH  /_search/tipo-prepago-consumos?query=:query} : search for the tipoPrepagoConsumo corresponding
     * to the query.
     *
     * @param query the query of the tipoPrepagoConsumo search.
     * @param pageable the pagination information.
     * @param queryParams a {@link MultiValueMap} query parameters.
     * @param uriBuilder a {@link UriComponentsBuilder} URI builder.
     * @return the result of the search.
     */
    @GetMapping("/_search/tipo-prepago-consumos")
    public ResponseEntity<List<TipoPrepagoConsumoDTO>> searchTipoPrepagoConsumos(@RequestParam String query, Pageable pageable, @RequestParam MultiValueMap<String, String> queryParams, UriComponentsBuilder uriBuilder) {
        log.debug("REST request to search for a page of TipoPrepagoConsumos for query {}", query);
        Page<TipoPrepagoConsumoDTO> page = tipoPrepagoConsumoService.search(query, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(uriBuilder.queryParams(queryParams), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

}
