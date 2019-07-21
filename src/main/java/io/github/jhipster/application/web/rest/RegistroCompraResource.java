package io.github.jhipster.application.web.rest;

import io.github.jhipster.application.service.RegistroCompraService;
import io.github.jhipster.application.web.rest.errors.BadRequestAlertException;
import io.github.jhipster.application.service.dto.RegistroCompraDTO;
import io.github.jhipster.application.service.dto.RegistroCompraCriteria;
import io.github.jhipster.application.service.RegistroCompraQueryService;

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
 * REST controller for managing {@link io.github.jhipster.application.domain.RegistroCompra}.
 */
@RestController
@RequestMapping("/api")
public class RegistroCompraResource {

    private final Logger log = LoggerFactory.getLogger(RegistroCompraResource.class);

    private static final String ENTITY_NAME = "registroCompra";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final RegistroCompraService registroCompraService;

    private final RegistroCompraQueryService registroCompraQueryService;

    public RegistroCompraResource(RegistroCompraService registroCompraService, RegistroCompraQueryService registroCompraQueryService) {
        this.registroCompraService = registroCompraService;
        this.registroCompraQueryService = registroCompraQueryService;
    }

    /**
     * {@code POST  /registro-compras} : Create a new registroCompra.
     *
     * @param registroCompraDTO the registroCompraDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new registroCompraDTO, or with status {@code 400 (Bad Request)} if the registroCompra has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/registro-compras")
    public ResponseEntity<RegistroCompraDTO> createRegistroCompra(@RequestBody RegistroCompraDTO registroCompraDTO) throws URISyntaxException {
        log.debug("REST request to save RegistroCompra : {}", registroCompraDTO);
        if (registroCompraDTO.getId() != null) {
            throw new BadRequestAlertException("A new registroCompra cannot already have an ID", ENTITY_NAME, "idexists");
        }
        RegistroCompraDTO result = registroCompraService.save(registroCompraDTO);
        return ResponseEntity.created(new URI("/api/registro-compras/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /registro-compras} : Updates an existing registroCompra.
     *
     * @param registroCompraDTO the registroCompraDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated registroCompraDTO,
     * or with status {@code 400 (Bad Request)} if the registroCompraDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the registroCompraDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/registro-compras")
    public ResponseEntity<RegistroCompraDTO> updateRegistroCompra(@RequestBody RegistroCompraDTO registroCompraDTO) throws URISyntaxException {
        log.debug("REST request to update RegistroCompra : {}", registroCompraDTO);
        if (registroCompraDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        RegistroCompraDTO result = registroCompraService.save(registroCompraDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, registroCompraDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /registro-compras} : get all the registroCompras.
     *
     * @param pageable the pagination information.
     * @param queryParams a {@link MultiValueMap} query parameters.
     * @param uriBuilder a {@link UriComponentsBuilder} URI builder.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of registroCompras in body.
     */
    @GetMapping("/registro-compras")
    public ResponseEntity<List<RegistroCompraDTO>> getAllRegistroCompras(RegistroCompraCriteria criteria, Pageable pageable, @RequestParam MultiValueMap<String, String> queryParams, UriComponentsBuilder uriBuilder) {
        log.debug("REST request to get RegistroCompras by criteria: {}", criteria);
        Page<RegistroCompraDTO> page = registroCompraQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(uriBuilder.queryParams(queryParams), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
    * {@code GET  /registro-compras/count} : count all the registroCompras.
    *
    * @param criteria the criteria which the requested entities should match.
    * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
    */
    @GetMapping("/registro-compras/count")
    public ResponseEntity<Long> countRegistroCompras(RegistroCompraCriteria criteria) {
        log.debug("REST request to count RegistroCompras by criteria: {}", criteria);
        return ResponseEntity.ok().body(registroCompraQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /registro-compras/:id} : get the "id" registroCompra.
     *
     * @param id the id of the registroCompraDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the registroCompraDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/registro-compras/{id}")
    public ResponseEntity<RegistroCompraDTO> getRegistroCompra(@PathVariable Long id) {
        log.debug("REST request to get RegistroCompra : {}", id);
        Optional<RegistroCompraDTO> registroCompraDTO = registroCompraService.findOne(id);
        return ResponseUtil.wrapOrNotFound(registroCompraDTO);
    }

    /**
     * {@code DELETE  /registro-compras/:id} : delete the "id" registroCompra.
     *
     * @param id the id of the registroCompraDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/registro-compras/{id}")
    public ResponseEntity<Void> deleteRegistroCompra(@PathVariable Long id) {
        log.debug("REST request to delete RegistroCompra : {}", id);
        registroCompraService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }

    /**
     * {@code SEARCH  /_search/registro-compras?query=:query} : search for the registroCompra corresponding
     * to the query.
     *
     * @param query the query of the registroCompra search.
     * @param pageable the pagination information.
     * @param queryParams a {@link MultiValueMap} query parameters.
     * @param uriBuilder a {@link UriComponentsBuilder} URI builder.
     * @return the result of the search.
     */
    @GetMapping("/_search/registro-compras")
    public ResponseEntity<List<RegistroCompraDTO>> searchRegistroCompras(@RequestParam String query, Pageable pageable, @RequestParam MultiValueMap<String, String> queryParams, UriComponentsBuilder uriBuilder) {
        log.debug("REST request to search for a page of RegistroCompras for query {}", query);
        Page<RegistroCompraDTO> page = registroCompraService.search(query, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(uriBuilder.queryParams(queryParams), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

}
