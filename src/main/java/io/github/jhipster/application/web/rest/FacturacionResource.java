package io.github.jhipster.application.web.rest;

import io.github.jhipster.application.service.FacturacionService;
import io.github.jhipster.application.web.rest.errors.BadRequestAlertException;
import io.github.jhipster.application.service.dto.FacturacionDTO;
import io.github.jhipster.application.service.dto.FacturacionCriteria;
import io.github.jhipster.application.service.FacturacionQueryService;

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
 * REST controller for managing {@link io.github.jhipster.application.domain.Facturacion}.
 */
@RestController
@RequestMapping("/api")
public class FacturacionResource {

    private final Logger log = LoggerFactory.getLogger(FacturacionResource.class);

    private static final String ENTITY_NAME = "facturacion";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final FacturacionService facturacionService;

    private final FacturacionQueryService facturacionQueryService;

    public FacturacionResource(FacturacionService facturacionService, FacturacionQueryService facturacionQueryService) {
        this.facturacionService = facturacionService;
        this.facturacionQueryService = facturacionQueryService;
    }

    /**
     * {@code POST  /facturacions} : Create a new facturacion.
     *
     * @param facturacionDTO the facturacionDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new facturacionDTO, or with status {@code 400 (Bad Request)} if the facturacion has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/facturacions")
    public ResponseEntity<FacturacionDTO> createFacturacion(@RequestBody FacturacionDTO facturacionDTO) throws URISyntaxException {
        log.debug("REST request to save Facturacion : {}", facturacionDTO);
        if (facturacionDTO.getId() != null) {
            throw new BadRequestAlertException("A new facturacion cannot already have an ID", ENTITY_NAME, "idexists");
        }
        FacturacionDTO result = facturacionService.save(facturacionDTO);
        return ResponseEntity.created(new URI("/api/facturacions/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /facturacions} : Updates an existing facturacion.
     *
     * @param facturacionDTO the facturacionDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated facturacionDTO,
     * or with status {@code 400 (Bad Request)} if the facturacionDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the facturacionDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/facturacions")
    public ResponseEntity<FacturacionDTO> updateFacturacion(@RequestBody FacturacionDTO facturacionDTO) throws URISyntaxException {
        log.debug("REST request to update Facturacion : {}", facturacionDTO);
        if (facturacionDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        FacturacionDTO result = facturacionService.save(facturacionDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, facturacionDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /facturacions} : get all the facturacions.
     *
     * @param pageable the pagination information.
     * @param queryParams a {@link MultiValueMap} query parameters.
     * @param uriBuilder a {@link UriComponentsBuilder} URI builder.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of facturacions in body.
     */
    @GetMapping("/facturacions")
    public ResponseEntity<List<FacturacionDTO>> getAllFacturacions(FacturacionCriteria criteria, Pageable pageable, @RequestParam MultiValueMap<String, String> queryParams, UriComponentsBuilder uriBuilder) {
        log.debug("REST request to get Facturacions by criteria: {}", criteria);
        Page<FacturacionDTO> page = facturacionQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(uriBuilder.queryParams(queryParams), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
    * {@code GET  /facturacions/count} : count all the facturacions.
    *
    * @param criteria the criteria which the requested entities should match.
    * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
    */
    @GetMapping("/facturacions/count")
    public ResponseEntity<Long> countFacturacions(FacturacionCriteria criteria) {
        log.debug("REST request to count Facturacions by criteria: {}", criteria);
        return ResponseEntity.ok().body(facturacionQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /facturacions/:id} : get the "id" facturacion.
     *
     * @param id the id of the facturacionDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the facturacionDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/facturacions/{id}")
    public ResponseEntity<FacturacionDTO> getFacturacion(@PathVariable Long id) {
        log.debug("REST request to get Facturacion : {}", id);
        Optional<FacturacionDTO> facturacionDTO = facturacionService.findOne(id);
        return ResponseUtil.wrapOrNotFound(facturacionDTO);
    }

    /**
     * {@code DELETE  /facturacions/:id} : delete the "id" facturacion.
     *
     * @param id the id of the facturacionDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/facturacions/{id}")
    public ResponseEntity<Void> deleteFacturacion(@PathVariable Long id) {
        log.debug("REST request to delete Facturacion : {}", id);
        facturacionService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }

    /**
     * {@code SEARCH  /_search/facturacions?query=:query} : search for the facturacion corresponding
     * to the query.
     *
     * @param query the query of the facturacion search.
     * @param pageable the pagination information.
     * @param queryParams a {@link MultiValueMap} query parameters.
     * @param uriBuilder a {@link UriComponentsBuilder} URI builder.
     * @return the result of the search.
     */
    @GetMapping("/_search/facturacions")
    public ResponseEntity<List<FacturacionDTO>> searchFacturacions(@RequestParam String query, Pageable pageable, @RequestParam MultiValueMap<String, String> queryParams, UriComponentsBuilder uriBuilder) {
        log.debug("REST request to search for a page of Facturacions for query {}", query);
        Page<FacturacionDTO> page = facturacionService.search(query, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(uriBuilder.queryParams(queryParams), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

}
