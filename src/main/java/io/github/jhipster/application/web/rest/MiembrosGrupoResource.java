package io.github.jhipster.application.web.rest;

import io.github.jhipster.application.service.MiembrosGrupoService;
import io.github.jhipster.application.web.rest.errors.BadRequestAlertException;
import io.github.jhipster.application.service.dto.MiembrosGrupoDTO;
import io.github.jhipster.application.service.dto.MiembrosGrupoCriteria;
import io.github.jhipster.application.service.MiembrosGrupoQueryService;

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
 * REST controller for managing {@link io.github.jhipster.application.domain.MiembrosGrupo}.
 */
@RestController
@RequestMapping("/api")
public class MiembrosGrupoResource {

    private final Logger log = LoggerFactory.getLogger(MiembrosGrupoResource.class);

    private static final String ENTITY_NAME = "miembrosGrupo";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final MiembrosGrupoService miembrosGrupoService;

    private final MiembrosGrupoQueryService miembrosGrupoQueryService;

    public MiembrosGrupoResource(MiembrosGrupoService miembrosGrupoService, MiembrosGrupoQueryService miembrosGrupoQueryService) {
        this.miembrosGrupoService = miembrosGrupoService;
        this.miembrosGrupoQueryService = miembrosGrupoQueryService;
    }

    /**
     * {@code POST  /miembros-grupos} : Create a new miembrosGrupo.
     *
     * @param miembrosGrupoDTO the miembrosGrupoDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new miembrosGrupoDTO, or with status {@code 400 (Bad Request)} if the miembrosGrupo has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/miembros-grupos")
    public ResponseEntity<MiembrosGrupoDTO> createMiembrosGrupo(@RequestBody MiembrosGrupoDTO miembrosGrupoDTO) throws URISyntaxException {
        log.debug("REST request to save MiembrosGrupo : {}", miembrosGrupoDTO);
        if (miembrosGrupoDTO.getId() != null) {
            throw new BadRequestAlertException("A new miembrosGrupo cannot already have an ID", ENTITY_NAME, "idexists");
        }
        MiembrosGrupoDTO result = miembrosGrupoService.save(miembrosGrupoDTO);
        return ResponseEntity.created(new URI("/api/miembros-grupos/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /miembros-grupos} : Updates an existing miembrosGrupo.
     *
     * @param miembrosGrupoDTO the miembrosGrupoDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated miembrosGrupoDTO,
     * or with status {@code 400 (Bad Request)} if the miembrosGrupoDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the miembrosGrupoDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/miembros-grupos")
    public ResponseEntity<MiembrosGrupoDTO> updateMiembrosGrupo(@RequestBody MiembrosGrupoDTO miembrosGrupoDTO) throws URISyntaxException {
        log.debug("REST request to update MiembrosGrupo : {}", miembrosGrupoDTO);
        if (miembrosGrupoDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        MiembrosGrupoDTO result = miembrosGrupoService.save(miembrosGrupoDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, miembrosGrupoDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /miembros-grupos} : get all the miembrosGrupos.
     *
     * @param pageable the pagination information.
     * @param queryParams a {@link MultiValueMap} query parameters.
     * @param uriBuilder a {@link UriComponentsBuilder} URI builder.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of miembrosGrupos in body.
     */
    @GetMapping("/miembros-grupos")
    public ResponseEntity<List<MiembrosGrupoDTO>> getAllMiembrosGrupos(MiembrosGrupoCriteria criteria, Pageable pageable, @RequestParam MultiValueMap<String, String> queryParams, UriComponentsBuilder uriBuilder) {
        log.debug("REST request to get MiembrosGrupos by criteria: {}", criteria);
        Page<MiembrosGrupoDTO> page = miembrosGrupoQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(uriBuilder.queryParams(queryParams), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
    * {@code GET  /miembros-grupos/count} : count all the miembrosGrupos.
    *
    * @param criteria the criteria which the requested entities should match.
    * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
    */
    @GetMapping("/miembros-grupos/count")
    public ResponseEntity<Long> countMiembrosGrupos(MiembrosGrupoCriteria criteria) {
        log.debug("REST request to count MiembrosGrupos by criteria: {}", criteria);
        return ResponseEntity.ok().body(miembrosGrupoQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /miembros-grupos/:id} : get the "id" miembrosGrupo.
     *
     * @param id the id of the miembrosGrupoDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the miembrosGrupoDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/miembros-grupos/{id}")
    public ResponseEntity<MiembrosGrupoDTO> getMiembrosGrupo(@PathVariable Long id) {
        log.debug("REST request to get MiembrosGrupo : {}", id);
        Optional<MiembrosGrupoDTO> miembrosGrupoDTO = miembrosGrupoService.findOne(id);
        return ResponseUtil.wrapOrNotFound(miembrosGrupoDTO);
    }

    /**
     * {@code DELETE  /miembros-grupos/:id} : delete the "id" miembrosGrupo.
     *
     * @param id the id of the miembrosGrupoDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/miembros-grupos/{id}")
    public ResponseEntity<Void> deleteMiembrosGrupo(@PathVariable Long id) {
        log.debug("REST request to delete MiembrosGrupo : {}", id);
        miembrosGrupoService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }

    /**
     * {@code SEARCH  /_search/miembros-grupos?query=:query} : search for the miembrosGrupo corresponding
     * to the query.
     *
     * @param query the query of the miembrosGrupo search.
     * @param pageable the pagination information.
     * @param queryParams a {@link MultiValueMap} query parameters.
     * @param uriBuilder a {@link UriComponentsBuilder} URI builder.
     * @return the result of the search.
     */
    @GetMapping("/_search/miembros-grupos")
    public ResponseEntity<List<MiembrosGrupoDTO>> searchMiembrosGrupos(@RequestParam String query, Pageable pageable, @RequestParam MultiValueMap<String, String> queryParams, UriComponentsBuilder uriBuilder) {
        log.debug("REST request to search for a page of MiembrosGrupos for query {}", query);
        Page<MiembrosGrupoDTO> page = miembrosGrupoService.search(query, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(uriBuilder.queryParams(queryParams), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

}
