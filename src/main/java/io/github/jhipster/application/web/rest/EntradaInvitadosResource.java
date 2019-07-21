package io.github.jhipster.application.web.rest;

import io.github.jhipster.application.service.EntradaInvitadosService;
import io.github.jhipster.application.web.rest.errors.BadRequestAlertException;
import io.github.jhipster.application.service.dto.EntradaInvitadosDTO;
import io.github.jhipster.application.service.dto.EntradaInvitadosCriteria;
import io.github.jhipster.application.service.EntradaInvitadosQueryService;

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
 * REST controller for managing {@link io.github.jhipster.application.domain.EntradaInvitados}.
 */
@RestController
@RequestMapping("/api")
public class EntradaInvitadosResource {

    private final Logger log = LoggerFactory.getLogger(EntradaInvitadosResource.class);

    private static final String ENTITY_NAME = "entradaInvitados";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final EntradaInvitadosService entradaInvitadosService;

    private final EntradaInvitadosQueryService entradaInvitadosQueryService;

    public EntradaInvitadosResource(EntradaInvitadosService entradaInvitadosService, EntradaInvitadosQueryService entradaInvitadosQueryService) {
        this.entradaInvitadosService = entradaInvitadosService;
        this.entradaInvitadosQueryService = entradaInvitadosQueryService;
    }

    /**
     * {@code POST  /entrada-invitados} : Create a new entradaInvitados.
     *
     * @param entradaInvitadosDTO the entradaInvitadosDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new entradaInvitadosDTO, or with status {@code 400 (Bad Request)} if the entradaInvitados has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/entrada-invitados")
    public ResponseEntity<EntradaInvitadosDTO> createEntradaInvitados(@Valid @RequestBody EntradaInvitadosDTO entradaInvitadosDTO) throws URISyntaxException {
        log.debug("REST request to save EntradaInvitados : {}", entradaInvitadosDTO);
        if (entradaInvitadosDTO.getId() != null) {
            throw new BadRequestAlertException("A new entradaInvitados cannot already have an ID", ENTITY_NAME, "idexists");
        }
        EntradaInvitadosDTO result = entradaInvitadosService.save(entradaInvitadosDTO);
        return ResponseEntity.created(new URI("/api/entrada-invitados/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /entrada-invitados} : Updates an existing entradaInvitados.
     *
     * @param entradaInvitadosDTO the entradaInvitadosDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated entradaInvitadosDTO,
     * or with status {@code 400 (Bad Request)} if the entradaInvitadosDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the entradaInvitadosDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/entrada-invitados")
    public ResponseEntity<EntradaInvitadosDTO> updateEntradaInvitados(@Valid @RequestBody EntradaInvitadosDTO entradaInvitadosDTO) throws URISyntaxException {
        log.debug("REST request to update EntradaInvitados : {}", entradaInvitadosDTO);
        if (entradaInvitadosDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        EntradaInvitadosDTO result = entradaInvitadosService.save(entradaInvitadosDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, entradaInvitadosDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /entrada-invitados} : get all the entradaInvitados.
     *
     * @param pageable the pagination information.
     * @param queryParams a {@link MultiValueMap} query parameters.
     * @param uriBuilder a {@link UriComponentsBuilder} URI builder.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of entradaInvitados in body.
     */
    @GetMapping("/entrada-invitados")
    public ResponseEntity<List<EntradaInvitadosDTO>> getAllEntradaInvitados(EntradaInvitadosCriteria criteria, Pageable pageable, @RequestParam MultiValueMap<String, String> queryParams, UriComponentsBuilder uriBuilder) {
        log.debug("REST request to get EntradaInvitados by criteria: {}", criteria);
        Page<EntradaInvitadosDTO> page = entradaInvitadosQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(uriBuilder.queryParams(queryParams), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
    * {@code GET  /entrada-invitados/count} : count all the entradaInvitados.
    *
    * @param criteria the criteria which the requested entities should match.
    * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
    */
    @GetMapping("/entrada-invitados/count")
    public ResponseEntity<Long> countEntradaInvitados(EntradaInvitadosCriteria criteria) {
        log.debug("REST request to count EntradaInvitados by criteria: {}", criteria);
        return ResponseEntity.ok().body(entradaInvitadosQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /entrada-invitados/:id} : get the "id" entradaInvitados.
     *
     * @param id the id of the entradaInvitadosDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the entradaInvitadosDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/entrada-invitados/{id}")
    public ResponseEntity<EntradaInvitadosDTO> getEntradaInvitados(@PathVariable Long id) {
        log.debug("REST request to get EntradaInvitados : {}", id);
        Optional<EntradaInvitadosDTO> entradaInvitadosDTO = entradaInvitadosService.findOne(id);
        return ResponseUtil.wrapOrNotFound(entradaInvitadosDTO);
    }

    /**
     * {@code DELETE  /entrada-invitados/:id} : delete the "id" entradaInvitados.
     *
     * @param id the id of the entradaInvitadosDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/entrada-invitados/{id}")
    public ResponseEntity<Void> deleteEntradaInvitados(@PathVariable Long id) {
        log.debug("REST request to delete EntradaInvitados : {}", id);
        entradaInvitadosService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }

    /**
     * {@code SEARCH  /_search/entrada-invitados?query=:query} : search for the entradaInvitados corresponding
     * to the query.
     *
     * @param query the query of the entradaInvitados search.
     * @param pageable the pagination information.
     * @param queryParams a {@link MultiValueMap} query parameters.
     * @param uriBuilder a {@link UriComponentsBuilder} URI builder.
     * @return the result of the search.
     */
    @GetMapping("/_search/entrada-invitados")
    public ResponseEntity<List<EntradaInvitadosDTO>> searchEntradaInvitados(@RequestParam String query, Pageable pageable, @RequestParam MultiValueMap<String, String> queryParams, UriComponentsBuilder uriBuilder) {
        log.debug("REST request to search for a page of EntradaInvitados for query {}", query);
        Page<EntradaInvitadosDTO> page = entradaInvitadosService.search(query, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(uriBuilder.queryParams(queryParams), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

}
