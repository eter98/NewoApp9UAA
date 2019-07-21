package io.github.jhipster.application.web.rest;

import io.github.jhipster.application.service.EspacioLibreService;
import io.github.jhipster.application.web.rest.errors.BadRequestAlertException;
import io.github.jhipster.application.service.dto.EspacioLibreDTO;
import io.github.jhipster.application.service.dto.EspacioLibreCriteria;
import io.github.jhipster.application.service.EspacioLibreQueryService;

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
 * REST controller for managing {@link io.github.jhipster.application.domain.EspacioLibre}.
 */
@RestController
@RequestMapping("/api")
public class EspacioLibreResource {

    private final Logger log = LoggerFactory.getLogger(EspacioLibreResource.class);

    private static final String ENTITY_NAME = "espacioLibre";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final EspacioLibreService espacioLibreService;

    private final EspacioLibreQueryService espacioLibreQueryService;

    public EspacioLibreResource(EspacioLibreService espacioLibreService, EspacioLibreQueryService espacioLibreQueryService) {
        this.espacioLibreService = espacioLibreService;
        this.espacioLibreQueryService = espacioLibreQueryService;
    }

    /**
     * {@code POST  /espacio-libres} : Create a new espacioLibre.
     *
     * @param espacioLibreDTO the espacioLibreDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new espacioLibreDTO, or with status {@code 400 (Bad Request)} if the espacioLibre has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/espacio-libres")
    public ResponseEntity<EspacioLibreDTO> createEspacioLibre(@Valid @RequestBody EspacioLibreDTO espacioLibreDTO) throws URISyntaxException {
        log.debug("REST request to save EspacioLibre : {}", espacioLibreDTO);
        if (espacioLibreDTO.getId() != null) {
            throw new BadRequestAlertException("A new espacioLibre cannot already have an ID", ENTITY_NAME, "idexists");
        }
        EspacioLibreDTO result = espacioLibreService.save(espacioLibreDTO);
        return ResponseEntity.created(new URI("/api/espacio-libres/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /espacio-libres} : Updates an existing espacioLibre.
     *
     * @param espacioLibreDTO the espacioLibreDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated espacioLibreDTO,
     * or with status {@code 400 (Bad Request)} if the espacioLibreDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the espacioLibreDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/espacio-libres")
    public ResponseEntity<EspacioLibreDTO> updateEspacioLibre(@Valid @RequestBody EspacioLibreDTO espacioLibreDTO) throws URISyntaxException {
        log.debug("REST request to update EspacioLibre : {}", espacioLibreDTO);
        if (espacioLibreDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        EspacioLibreDTO result = espacioLibreService.save(espacioLibreDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, espacioLibreDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /espacio-libres} : get all the espacioLibres.
     *
     * @param pageable the pagination information.
     * @param queryParams a {@link MultiValueMap} query parameters.
     * @param uriBuilder a {@link UriComponentsBuilder} URI builder.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of espacioLibres in body.
     */
    @GetMapping("/espacio-libres")
    public ResponseEntity<List<EspacioLibreDTO>> getAllEspacioLibres(EspacioLibreCriteria criteria, Pageable pageable, @RequestParam MultiValueMap<String, String> queryParams, UriComponentsBuilder uriBuilder) {
        log.debug("REST request to get EspacioLibres by criteria: {}", criteria);
        Page<EspacioLibreDTO> page = espacioLibreQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(uriBuilder.queryParams(queryParams), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
    * {@code GET  /espacio-libres/count} : count all the espacioLibres.
    *
    * @param criteria the criteria which the requested entities should match.
    * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
    */
    @GetMapping("/espacio-libres/count")
    public ResponseEntity<Long> countEspacioLibres(EspacioLibreCriteria criteria) {
        log.debug("REST request to count EspacioLibres by criteria: {}", criteria);
        return ResponseEntity.ok().body(espacioLibreQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /espacio-libres/:id} : get the "id" espacioLibre.
     *
     * @param id the id of the espacioLibreDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the espacioLibreDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/espacio-libres/{id}")
    public ResponseEntity<EspacioLibreDTO> getEspacioLibre(@PathVariable Long id) {
        log.debug("REST request to get EspacioLibre : {}", id);
        Optional<EspacioLibreDTO> espacioLibreDTO = espacioLibreService.findOne(id);
        return ResponseUtil.wrapOrNotFound(espacioLibreDTO);
    }

    /**
     * {@code DELETE  /espacio-libres/:id} : delete the "id" espacioLibre.
     *
     * @param id the id of the espacioLibreDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/espacio-libres/{id}")
    public ResponseEntity<Void> deleteEspacioLibre(@PathVariable Long id) {
        log.debug("REST request to delete EspacioLibre : {}", id);
        espacioLibreService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }

    /**
     * {@code SEARCH  /_search/espacio-libres?query=:query} : search for the espacioLibre corresponding
     * to the query.
     *
     * @param query the query of the espacioLibre search.
     * @param pageable the pagination information.
     * @param queryParams a {@link MultiValueMap} query parameters.
     * @param uriBuilder a {@link UriComponentsBuilder} URI builder.
     * @return the result of the search.
     */
    @GetMapping("/_search/espacio-libres")
    public ResponseEntity<List<EspacioLibreDTO>> searchEspacioLibres(@RequestParam String query, Pageable pageable, @RequestParam MultiValueMap<String, String> queryParams, UriComponentsBuilder uriBuilder) {
        log.debug("REST request to search for a page of EspacioLibres for query {}", query);
        Page<EspacioLibreDTO> page = espacioLibreService.search(query, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(uriBuilder.queryParams(queryParams), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

}
