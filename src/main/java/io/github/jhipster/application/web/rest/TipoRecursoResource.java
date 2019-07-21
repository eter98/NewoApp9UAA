package io.github.jhipster.application.web.rest;

import io.github.jhipster.application.service.TipoRecursoService;
import io.github.jhipster.application.web.rest.errors.BadRequestAlertException;
import io.github.jhipster.application.service.dto.TipoRecursoDTO;
import io.github.jhipster.application.service.dto.TipoRecursoCriteria;
import io.github.jhipster.application.service.TipoRecursoQueryService;

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
 * REST controller for managing {@link io.github.jhipster.application.domain.TipoRecurso}.
 */
@RestController
@RequestMapping("/api")
public class TipoRecursoResource {

    private final Logger log = LoggerFactory.getLogger(TipoRecursoResource.class);

    private static final String ENTITY_NAME = "tipoRecurso";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final TipoRecursoService tipoRecursoService;

    private final TipoRecursoQueryService tipoRecursoQueryService;

    public TipoRecursoResource(TipoRecursoService tipoRecursoService, TipoRecursoQueryService tipoRecursoQueryService) {
        this.tipoRecursoService = tipoRecursoService;
        this.tipoRecursoQueryService = tipoRecursoQueryService;
    }

    /**
     * {@code POST  /tipo-recursos} : Create a new tipoRecurso.
     *
     * @param tipoRecursoDTO the tipoRecursoDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new tipoRecursoDTO, or with status {@code 400 (Bad Request)} if the tipoRecurso has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/tipo-recursos")
    public ResponseEntity<TipoRecursoDTO> createTipoRecurso(@Valid @RequestBody TipoRecursoDTO tipoRecursoDTO) throws URISyntaxException {
        log.debug("REST request to save TipoRecurso : {}", tipoRecursoDTO);
        if (tipoRecursoDTO.getId() != null) {
            throw new BadRequestAlertException("A new tipoRecurso cannot already have an ID", ENTITY_NAME, "idexists");
        }
        TipoRecursoDTO result = tipoRecursoService.save(tipoRecursoDTO);
        return ResponseEntity.created(new URI("/api/tipo-recursos/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /tipo-recursos} : Updates an existing tipoRecurso.
     *
     * @param tipoRecursoDTO the tipoRecursoDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated tipoRecursoDTO,
     * or with status {@code 400 (Bad Request)} if the tipoRecursoDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the tipoRecursoDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/tipo-recursos")
    public ResponseEntity<TipoRecursoDTO> updateTipoRecurso(@Valid @RequestBody TipoRecursoDTO tipoRecursoDTO) throws URISyntaxException {
        log.debug("REST request to update TipoRecurso : {}", tipoRecursoDTO);
        if (tipoRecursoDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        TipoRecursoDTO result = tipoRecursoService.save(tipoRecursoDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, tipoRecursoDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /tipo-recursos} : get all the tipoRecursos.
     *
     * @param pageable the pagination information.
     * @param queryParams a {@link MultiValueMap} query parameters.
     * @param uriBuilder a {@link UriComponentsBuilder} URI builder.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of tipoRecursos in body.
     */
    @GetMapping("/tipo-recursos")
    public ResponseEntity<List<TipoRecursoDTO>> getAllTipoRecursos(TipoRecursoCriteria criteria, Pageable pageable, @RequestParam MultiValueMap<String, String> queryParams, UriComponentsBuilder uriBuilder) {
        log.debug("REST request to get TipoRecursos by criteria: {}", criteria);
        Page<TipoRecursoDTO> page = tipoRecursoQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(uriBuilder.queryParams(queryParams), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
    * {@code GET  /tipo-recursos/count} : count all the tipoRecursos.
    *
    * @param criteria the criteria which the requested entities should match.
    * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
    */
    @GetMapping("/tipo-recursos/count")
    public ResponseEntity<Long> countTipoRecursos(TipoRecursoCriteria criteria) {
        log.debug("REST request to count TipoRecursos by criteria: {}", criteria);
        return ResponseEntity.ok().body(tipoRecursoQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /tipo-recursos/:id} : get the "id" tipoRecurso.
     *
     * @param id the id of the tipoRecursoDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the tipoRecursoDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/tipo-recursos/{id}")
    public ResponseEntity<TipoRecursoDTO> getTipoRecurso(@PathVariable Long id) {
        log.debug("REST request to get TipoRecurso : {}", id);
        Optional<TipoRecursoDTO> tipoRecursoDTO = tipoRecursoService.findOne(id);
        return ResponseUtil.wrapOrNotFound(tipoRecursoDTO);
    }

    /**
     * {@code DELETE  /tipo-recursos/:id} : delete the "id" tipoRecurso.
     *
     * @param id the id of the tipoRecursoDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/tipo-recursos/{id}")
    public ResponseEntity<Void> deleteTipoRecurso(@PathVariable Long id) {
        log.debug("REST request to delete TipoRecurso : {}", id);
        tipoRecursoService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }

    /**
     * {@code SEARCH  /_search/tipo-recursos?query=:query} : search for the tipoRecurso corresponding
     * to the query.
     *
     * @param query the query of the tipoRecurso search.
     * @param pageable the pagination information.
     * @param queryParams a {@link MultiValueMap} query parameters.
     * @param uriBuilder a {@link UriComponentsBuilder} URI builder.
     * @return the result of the search.
     */
    @GetMapping("/_search/tipo-recursos")
    public ResponseEntity<List<TipoRecursoDTO>> searchTipoRecursos(@RequestParam String query, Pageable pageable, @RequestParam MultiValueMap<String, String> queryParams, UriComponentsBuilder uriBuilder) {
        log.debug("REST request to search for a page of TipoRecursos for query {}", query);
        Page<TipoRecursoDTO> page = tipoRecursoService.search(query, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(uriBuilder.queryParams(queryParams), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

}
