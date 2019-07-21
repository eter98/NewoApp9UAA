package io.github.jhipster.application.web.rest;

import io.github.jhipster.application.service.MiembrosService;
import io.github.jhipster.application.web.rest.errors.BadRequestAlertException;
import io.github.jhipster.application.service.dto.MiembrosDTO;
import io.github.jhipster.application.service.dto.MiembrosCriteria;
import io.github.jhipster.application.service.MiembrosQueryService;

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
 * REST controller for managing {@link io.github.jhipster.application.domain.Miembros}.
 */
@RestController
@RequestMapping("/api")
public class MiembrosResource {

    private final Logger log = LoggerFactory.getLogger(MiembrosResource.class);

    private static final String ENTITY_NAME = "miembros";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final MiembrosService miembrosService;

    private final MiembrosQueryService miembrosQueryService;

    public MiembrosResource(MiembrosService miembrosService, MiembrosQueryService miembrosQueryService) {
        this.miembrosService = miembrosService;
        this.miembrosQueryService = miembrosQueryService;
    }

    /**
     * {@code POST  /miembros} : Create a new miembros.
     *
     * @param miembrosDTO the miembrosDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new miembrosDTO, or with status {@code 400 (Bad Request)} if the miembros has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/miembros")
    public ResponseEntity<MiembrosDTO> createMiembros(@Valid @RequestBody MiembrosDTO miembrosDTO) throws URISyntaxException {
        log.debug("REST request to save Miembros : {}", miembrosDTO);
        if (miembrosDTO.getId() != null) {
            throw new BadRequestAlertException("A new miembros cannot already have an ID", ENTITY_NAME, "idexists");
        }
        MiembrosDTO result = miembrosService.save(miembrosDTO);
        return ResponseEntity.created(new URI("/api/miembros/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /miembros} : Updates an existing miembros.
     *
     * @param miembrosDTO the miembrosDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated miembrosDTO,
     * or with status {@code 400 (Bad Request)} if the miembrosDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the miembrosDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/miembros")
    public ResponseEntity<MiembrosDTO> updateMiembros(@Valid @RequestBody MiembrosDTO miembrosDTO) throws URISyntaxException {
        log.debug("REST request to update Miembros : {}", miembrosDTO);
        if (miembrosDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        MiembrosDTO result = miembrosService.save(miembrosDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, miembrosDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /miembros} : get all the miembros.
     *
     * @param pageable the pagination information.
     * @param queryParams a {@link MultiValueMap} query parameters.
     * @param uriBuilder a {@link UriComponentsBuilder} URI builder.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of miembros in body.
     */
    @GetMapping("/miembros")
    public ResponseEntity<List<MiembrosDTO>> getAllMiembros(MiembrosCriteria criteria, Pageable pageable, @RequestParam MultiValueMap<String, String> queryParams, UriComponentsBuilder uriBuilder) {
        log.debug("REST request to get Miembros by criteria: {}", criteria);
        Page<MiembrosDTO> page = miembrosQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(uriBuilder.queryParams(queryParams), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
    * {@code GET  /miembros/count} : count all the miembros.
    *
    * @param criteria the criteria which the requested entities should match.
    * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
    */
    @GetMapping("/miembros/count")
    public ResponseEntity<Long> countMiembros(MiembrosCriteria criteria) {
        log.debug("REST request to count Miembros by criteria: {}", criteria);
        return ResponseEntity.ok().body(miembrosQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /miembros/:id} : get the "id" miembros.
     *
     * @param id the id of the miembrosDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the miembrosDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/miembros/{id}")
    public ResponseEntity<MiembrosDTO> getMiembros(@PathVariable Long id) {
        log.debug("REST request to get Miembros : {}", id);
        Optional<MiembrosDTO> miembrosDTO = miembrosService.findOne(id);
        return ResponseUtil.wrapOrNotFound(miembrosDTO);
    }

    /**
     * {@code DELETE  /miembros/:id} : delete the "id" miembros.
     *
     * @param id the id of the miembrosDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/miembros/{id}")
    public ResponseEntity<Void> deleteMiembros(@PathVariable Long id) {
        log.debug("REST request to delete Miembros : {}", id);
        miembrosService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }

    /**
     * {@code SEARCH  /_search/miembros?query=:query} : search for the miembros corresponding
     * to the query.
     *
     * @param query the query of the miembros search.
     * @param pageable the pagination information.
     * @param queryParams a {@link MultiValueMap} query parameters.
     * @param uriBuilder a {@link UriComponentsBuilder} URI builder.
     * @return the result of the search.
     */
    @GetMapping("/_search/miembros")
    public ResponseEntity<List<MiembrosDTO>> searchMiembros(@RequestParam String query, Pageable pageable, @RequestParam MultiValueMap<String, String> queryParams, UriComponentsBuilder uriBuilder) {
        log.debug("REST request to search for a page of Miembros for query {}", query);
        Page<MiembrosDTO> page = miembrosService.search(query, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(uriBuilder.queryParams(queryParams), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

}
