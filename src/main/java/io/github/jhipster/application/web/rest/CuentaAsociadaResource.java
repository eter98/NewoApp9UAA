package io.github.jhipster.application.web.rest;

import io.github.jhipster.application.service.CuentaAsociadaService;
import io.github.jhipster.application.web.rest.errors.BadRequestAlertException;
import io.github.jhipster.application.service.dto.CuentaAsociadaDTO;
import io.github.jhipster.application.service.dto.CuentaAsociadaCriteria;
import io.github.jhipster.application.service.CuentaAsociadaQueryService;

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
 * REST controller for managing {@link io.github.jhipster.application.domain.CuentaAsociada}.
 */
@RestController
@RequestMapping("/api")
public class CuentaAsociadaResource {

    private final Logger log = LoggerFactory.getLogger(CuentaAsociadaResource.class);

    private static final String ENTITY_NAME = "cuentaAsociada";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final CuentaAsociadaService cuentaAsociadaService;

    private final CuentaAsociadaQueryService cuentaAsociadaQueryService;

    public CuentaAsociadaResource(CuentaAsociadaService cuentaAsociadaService, CuentaAsociadaQueryService cuentaAsociadaQueryService) {
        this.cuentaAsociadaService = cuentaAsociadaService;
        this.cuentaAsociadaQueryService = cuentaAsociadaQueryService;
    }

    /**
     * {@code POST  /cuenta-asociadas} : Create a new cuentaAsociada.
     *
     * @param cuentaAsociadaDTO the cuentaAsociadaDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new cuentaAsociadaDTO, or with status {@code 400 (Bad Request)} if the cuentaAsociada has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/cuenta-asociadas")
    public ResponseEntity<CuentaAsociadaDTO> createCuentaAsociada(@Valid @RequestBody CuentaAsociadaDTO cuentaAsociadaDTO) throws URISyntaxException {
        log.debug("REST request to save CuentaAsociada : {}", cuentaAsociadaDTO);
        if (cuentaAsociadaDTO.getId() != null) {
            throw new BadRequestAlertException("A new cuentaAsociada cannot already have an ID", ENTITY_NAME, "idexists");
        }
        CuentaAsociadaDTO result = cuentaAsociadaService.save(cuentaAsociadaDTO);
        return ResponseEntity.created(new URI("/api/cuenta-asociadas/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /cuenta-asociadas} : Updates an existing cuentaAsociada.
     *
     * @param cuentaAsociadaDTO the cuentaAsociadaDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated cuentaAsociadaDTO,
     * or with status {@code 400 (Bad Request)} if the cuentaAsociadaDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the cuentaAsociadaDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/cuenta-asociadas")
    public ResponseEntity<CuentaAsociadaDTO> updateCuentaAsociada(@Valid @RequestBody CuentaAsociadaDTO cuentaAsociadaDTO) throws URISyntaxException {
        log.debug("REST request to update CuentaAsociada : {}", cuentaAsociadaDTO);
        if (cuentaAsociadaDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        CuentaAsociadaDTO result = cuentaAsociadaService.save(cuentaAsociadaDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, cuentaAsociadaDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /cuenta-asociadas} : get all the cuentaAsociadas.
     *
     * @param pageable the pagination information.
     * @param queryParams a {@link MultiValueMap} query parameters.
     * @param uriBuilder a {@link UriComponentsBuilder} URI builder.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of cuentaAsociadas in body.
     */
    @GetMapping("/cuenta-asociadas")
    public ResponseEntity<List<CuentaAsociadaDTO>> getAllCuentaAsociadas(CuentaAsociadaCriteria criteria, Pageable pageable, @RequestParam MultiValueMap<String, String> queryParams, UriComponentsBuilder uriBuilder) {
        log.debug("REST request to get CuentaAsociadas by criteria: {}", criteria);
        Page<CuentaAsociadaDTO> page = cuentaAsociadaQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(uriBuilder.queryParams(queryParams), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
    * {@code GET  /cuenta-asociadas/count} : count all the cuentaAsociadas.
    *
    * @param criteria the criteria which the requested entities should match.
    * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
    */
    @GetMapping("/cuenta-asociadas/count")
    public ResponseEntity<Long> countCuentaAsociadas(CuentaAsociadaCriteria criteria) {
        log.debug("REST request to count CuentaAsociadas by criteria: {}", criteria);
        return ResponseEntity.ok().body(cuentaAsociadaQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /cuenta-asociadas/:id} : get the "id" cuentaAsociada.
     *
     * @param id the id of the cuentaAsociadaDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the cuentaAsociadaDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/cuenta-asociadas/{id}")
    public ResponseEntity<CuentaAsociadaDTO> getCuentaAsociada(@PathVariable Long id) {
        log.debug("REST request to get CuentaAsociada : {}", id);
        Optional<CuentaAsociadaDTO> cuentaAsociadaDTO = cuentaAsociadaService.findOne(id);
        return ResponseUtil.wrapOrNotFound(cuentaAsociadaDTO);
    }

    /**
     * {@code DELETE  /cuenta-asociadas/:id} : delete the "id" cuentaAsociada.
     *
     * @param id the id of the cuentaAsociadaDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/cuenta-asociadas/{id}")
    public ResponseEntity<Void> deleteCuentaAsociada(@PathVariable Long id) {
        log.debug("REST request to delete CuentaAsociada : {}", id);
        cuentaAsociadaService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }

    /**
     * {@code SEARCH  /_search/cuenta-asociadas?query=:query} : search for the cuentaAsociada corresponding
     * to the query.
     *
     * @param query the query of the cuentaAsociada search.
     * @param pageable the pagination information.
     * @param queryParams a {@link MultiValueMap} query parameters.
     * @param uriBuilder a {@link UriComponentsBuilder} URI builder.
     * @return the result of the search.
     */
    @GetMapping("/_search/cuenta-asociadas")
    public ResponseEntity<List<CuentaAsociadaDTO>> searchCuentaAsociadas(@RequestParam String query, Pageable pageable, @RequestParam MultiValueMap<String, String> queryParams, UriComponentsBuilder uriBuilder) {
        log.debug("REST request to search for a page of CuentaAsociadas for query {}", query);
        Page<CuentaAsociadaDTO> page = cuentaAsociadaService.search(query, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(uriBuilder.queryParams(queryParams), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

}
