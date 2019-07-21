package io.github.jhipster.application.web.rest;

import io.github.jhipster.application.service.GruposService;
import io.github.jhipster.application.web.rest.errors.BadRequestAlertException;
import io.github.jhipster.application.service.dto.GruposDTO;
import io.github.jhipster.application.service.dto.GruposCriteria;
import io.github.jhipster.application.service.GruposQueryService;

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
 * REST controller for managing {@link io.github.jhipster.application.domain.Grupos}.
 */
@RestController
@RequestMapping("/api")
public class GruposResource {

    private final Logger log = LoggerFactory.getLogger(GruposResource.class);

    private static final String ENTITY_NAME = "grupos";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final GruposService gruposService;

    private final GruposQueryService gruposQueryService;

    public GruposResource(GruposService gruposService, GruposQueryService gruposQueryService) {
        this.gruposService = gruposService;
        this.gruposQueryService = gruposQueryService;
    }

    /**
     * {@code POST  /grupos} : Create a new grupos.
     *
     * @param gruposDTO the gruposDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new gruposDTO, or with status {@code 400 (Bad Request)} if the grupos has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/grupos")
    public ResponseEntity<GruposDTO> createGrupos(@Valid @RequestBody GruposDTO gruposDTO) throws URISyntaxException {
        log.debug("REST request to save Grupos : {}", gruposDTO);
        if (gruposDTO.getId() != null) {
            throw new BadRequestAlertException("A new grupos cannot already have an ID", ENTITY_NAME, "idexists");
        }
        GruposDTO result = gruposService.save(gruposDTO);
        return ResponseEntity.created(new URI("/api/grupos/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /grupos} : Updates an existing grupos.
     *
     * @param gruposDTO the gruposDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated gruposDTO,
     * or with status {@code 400 (Bad Request)} if the gruposDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the gruposDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/grupos")
    public ResponseEntity<GruposDTO> updateGrupos(@Valid @RequestBody GruposDTO gruposDTO) throws URISyntaxException {
        log.debug("REST request to update Grupos : {}", gruposDTO);
        if (gruposDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        GruposDTO result = gruposService.save(gruposDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, gruposDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /grupos} : get all the grupos.
     *
     * @param pageable the pagination information.
     * @param queryParams a {@link MultiValueMap} query parameters.
     * @param uriBuilder a {@link UriComponentsBuilder} URI builder.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of grupos in body.
     */
    @GetMapping("/grupos")
    public ResponseEntity<List<GruposDTO>> getAllGrupos(GruposCriteria criteria, Pageable pageable, @RequestParam MultiValueMap<String, String> queryParams, UriComponentsBuilder uriBuilder) {
        log.debug("REST request to get Grupos by criteria: {}", criteria);
        Page<GruposDTO> page = gruposQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(uriBuilder.queryParams(queryParams), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
    * {@code GET  /grupos/count} : count all the grupos.
    *
    * @param criteria the criteria which the requested entities should match.
    * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
    */
    @GetMapping("/grupos/count")
    public ResponseEntity<Long> countGrupos(GruposCriteria criteria) {
        log.debug("REST request to count Grupos by criteria: {}", criteria);
        return ResponseEntity.ok().body(gruposQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /grupos/:id} : get the "id" grupos.
     *
     * @param id the id of the gruposDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the gruposDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/grupos/{id}")
    public ResponseEntity<GruposDTO> getGrupos(@PathVariable Long id) {
        log.debug("REST request to get Grupos : {}", id);
        Optional<GruposDTO> gruposDTO = gruposService.findOne(id);
        return ResponseUtil.wrapOrNotFound(gruposDTO);
    }

    /**
     * {@code DELETE  /grupos/:id} : delete the "id" grupos.
     *
     * @param id the id of the gruposDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/grupos/{id}")
    public ResponseEntity<Void> deleteGrupos(@PathVariable Long id) {
        log.debug("REST request to delete Grupos : {}", id);
        gruposService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }

    /**
     * {@code SEARCH  /_search/grupos?query=:query} : search for the grupos corresponding
     * to the query.
     *
     * @param query the query of the grupos search.
     * @param pageable the pagination information.
     * @param queryParams a {@link MultiValueMap} query parameters.
     * @param uriBuilder a {@link UriComponentsBuilder} URI builder.
     * @return the result of the search.
     */
    @GetMapping("/_search/grupos")
    public ResponseEntity<List<GruposDTO>> searchGrupos(@RequestParam String query, Pageable pageable, @RequestParam MultiValueMap<String, String> queryParams, UriComponentsBuilder uriBuilder) {
        log.debug("REST request to search for a page of Grupos for query {}", query);
        Page<GruposDTO> page = gruposService.search(query, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(uriBuilder.queryParams(queryParams), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

}
