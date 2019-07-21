package io.github.jhipster.application.web.rest;

import io.github.jhipster.application.service.InvitadosService;
import io.github.jhipster.application.web.rest.errors.BadRequestAlertException;
import io.github.jhipster.application.service.dto.InvitadosDTO;
import io.github.jhipster.application.service.dto.InvitadosCriteria;
import io.github.jhipster.application.service.InvitadosQueryService;

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
 * REST controller for managing {@link io.github.jhipster.application.domain.Invitados}.
 */
@RestController
@RequestMapping("/api")
public class InvitadosResource {

    private final Logger log = LoggerFactory.getLogger(InvitadosResource.class);

    private static final String ENTITY_NAME = "invitados";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final InvitadosService invitadosService;

    private final InvitadosQueryService invitadosQueryService;

    public InvitadosResource(InvitadosService invitadosService, InvitadosQueryService invitadosQueryService) {
        this.invitadosService = invitadosService;
        this.invitadosQueryService = invitadosQueryService;
    }

    /**
     * {@code POST  /invitados} : Create a new invitados.
     *
     * @param invitadosDTO the invitadosDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new invitadosDTO, or with status {@code 400 (Bad Request)} if the invitados has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/invitados")
    public ResponseEntity<InvitadosDTO> createInvitados(@Valid @RequestBody InvitadosDTO invitadosDTO) throws URISyntaxException {
        log.debug("REST request to save Invitados : {}", invitadosDTO);
        if (invitadosDTO.getId() != null) {
            throw new BadRequestAlertException("A new invitados cannot already have an ID", ENTITY_NAME, "idexists");
        }
        InvitadosDTO result = invitadosService.save(invitadosDTO);
        return ResponseEntity.created(new URI("/api/invitados/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /invitados} : Updates an existing invitados.
     *
     * @param invitadosDTO the invitadosDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated invitadosDTO,
     * or with status {@code 400 (Bad Request)} if the invitadosDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the invitadosDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/invitados")
    public ResponseEntity<InvitadosDTO> updateInvitados(@Valid @RequestBody InvitadosDTO invitadosDTO) throws URISyntaxException {
        log.debug("REST request to update Invitados : {}", invitadosDTO);
        if (invitadosDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        InvitadosDTO result = invitadosService.save(invitadosDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, invitadosDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /invitados} : get all the invitados.
     *
     * @param pageable the pagination information.
     * @param queryParams a {@link MultiValueMap} query parameters.
     * @param uriBuilder a {@link UriComponentsBuilder} URI builder.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of invitados in body.
     */
    @GetMapping("/invitados")
    public ResponseEntity<List<InvitadosDTO>> getAllInvitados(InvitadosCriteria criteria, Pageable pageable, @RequestParam MultiValueMap<String, String> queryParams, UriComponentsBuilder uriBuilder) {
        log.debug("REST request to get Invitados by criteria: {}", criteria);
        Page<InvitadosDTO> page = invitadosQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(uriBuilder.queryParams(queryParams), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
    * {@code GET  /invitados/count} : count all the invitados.
    *
    * @param criteria the criteria which the requested entities should match.
    * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
    */
    @GetMapping("/invitados/count")
    public ResponseEntity<Long> countInvitados(InvitadosCriteria criteria) {
        log.debug("REST request to count Invitados by criteria: {}", criteria);
        return ResponseEntity.ok().body(invitadosQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /invitados/:id} : get the "id" invitados.
     *
     * @param id the id of the invitadosDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the invitadosDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/invitados/{id}")
    public ResponseEntity<InvitadosDTO> getInvitados(@PathVariable Long id) {
        log.debug("REST request to get Invitados : {}", id);
        Optional<InvitadosDTO> invitadosDTO = invitadosService.findOne(id);
        return ResponseUtil.wrapOrNotFound(invitadosDTO);
    }

    /**
     * {@code DELETE  /invitados/:id} : delete the "id" invitados.
     *
     * @param id the id of the invitadosDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/invitados/{id}")
    public ResponseEntity<Void> deleteInvitados(@PathVariable Long id) {
        log.debug("REST request to delete Invitados : {}", id);
        invitadosService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }

    /**
     * {@code SEARCH  /_search/invitados?query=:query} : search for the invitados corresponding
     * to the query.
     *
     * @param query the query of the invitados search.
     * @param pageable the pagination information.
     * @param queryParams a {@link MultiValueMap} query parameters.
     * @param uriBuilder a {@link UriComponentsBuilder} URI builder.
     * @return the result of the search.
     */
    @GetMapping("/_search/invitados")
    public ResponseEntity<List<InvitadosDTO>> searchInvitados(@RequestParam String query, Pageable pageable, @RequestParam MultiValueMap<String, String> queryParams, UriComponentsBuilder uriBuilder) {
        log.debug("REST request to search for a page of Invitados for query {}", query);
        Page<InvitadosDTO> page = invitadosService.search(query, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(uriBuilder.queryParams(queryParams), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

}
