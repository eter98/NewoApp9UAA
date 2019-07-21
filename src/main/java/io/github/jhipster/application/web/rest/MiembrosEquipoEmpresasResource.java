package io.github.jhipster.application.web.rest;

import io.github.jhipster.application.service.MiembrosEquipoEmpresasService;
import io.github.jhipster.application.web.rest.errors.BadRequestAlertException;
import io.github.jhipster.application.service.dto.MiembrosEquipoEmpresasDTO;
import io.github.jhipster.application.service.dto.MiembrosEquipoEmpresasCriteria;
import io.github.jhipster.application.service.MiembrosEquipoEmpresasQueryService;

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
 * REST controller for managing {@link io.github.jhipster.application.domain.MiembrosEquipoEmpresas}.
 */
@RestController
@RequestMapping("/api")
public class MiembrosEquipoEmpresasResource {

    private final Logger log = LoggerFactory.getLogger(MiembrosEquipoEmpresasResource.class);

    private static final String ENTITY_NAME = "miembrosEquipoEmpresas";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final MiembrosEquipoEmpresasService miembrosEquipoEmpresasService;

    private final MiembrosEquipoEmpresasQueryService miembrosEquipoEmpresasQueryService;

    public MiembrosEquipoEmpresasResource(MiembrosEquipoEmpresasService miembrosEquipoEmpresasService, MiembrosEquipoEmpresasQueryService miembrosEquipoEmpresasQueryService) {
        this.miembrosEquipoEmpresasService = miembrosEquipoEmpresasService;
        this.miembrosEquipoEmpresasQueryService = miembrosEquipoEmpresasQueryService;
    }

    /**
     * {@code POST  /miembros-equipo-empresas} : Create a new miembrosEquipoEmpresas.
     *
     * @param miembrosEquipoEmpresasDTO the miembrosEquipoEmpresasDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new miembrosEquipoEmpresasDTO, or with status {@code 400 (Bad Request)} if the miembrosEquipoEmpresas has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/miembros-equipo-empresas")
    public ResponseEntity<MiembrosEquipoEmpresasDTO> createMiembrosEquipoEmpresas(@RequestBody MiembrosEquipoEmpresasDTO miembrosEquipoEmpresasDTO) throws URISyntaxException {
        log.debug("REST request to save MiembrosEquipoEmpresas : {}", miembrosEquipoEmpresasDTO);
        if (miembrosEquipoEmpresasDTO.getId() != null) {
            throw new BadRequestAlertException("A new miembrosEquipoEmpresas cannot already have an ID", ENTITY_NAME, "idexists");
        }
        MiembrosEquipoEmpresasDTO result = miembrosEquipoEmpresasService.save(miembrosEquipoEmpresasDTO);
        return ResponseEntity.created(new URI("/api/miembros-equipo-empresas/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /miembros-equipo-empresas} : Updates an existing miembrosEquipoEmpresas.
     *
     * @param miembrosEquipoEmpresasDTO the miembrosEquipoEmpresasDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated miembrosEquipoEmpresasDTO,
     * or with status {@code 400 (Bad Request)} if the miembrosEquipoEmpresasDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the miembrosEquipoEmpresasDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/miembros-equipo-empresas")
    public ResponseEntity<MiembrosEquipoEmpresasDTO> updateMiembrosEquipoEmpresas(@RequestBody MiembrosEquipoEmpresasDTO miembrosEquipoEmpresasDTO) throws URISyntaxException {
        log.debug("REST request to update MiembrosEquipoEmpresas : {}", miembrosEquipoEmpresasDTO);
        if (miembrosEquipoEmpresasDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        MiembrosEquipoEmpresasDTO result = miembrosEquipoEmpresasService.save(miembrosEquipoEmpresasDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, miembrosEquipoEmpresasDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /miembros-equipo-empresas} : get all the miembrosEquipoEmpresas.
     *
     * @param pageable the pagination information.
     * @param queryParams a {@link MultiValueMap} query parameters.
     * @param uriBuilder a {@link UriComponentsBuilder} URI builder.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of miembrosEquipoEmpresas in body.
     */
    @GetMapping("/miembros-equipo-empresas")
    public ResponseEntity<List<MiembrosEquipoEmpresasDTO>> getAllMiembrosEquipoEmpresas(MiembrosEquipoEmpresasCriteria criteria, Pageable pageable, @RequestParam MultiValueMap<String, String> queryParams, UriComponentsBuilder uriBuilder) {
        log.debug("REST request to get MiembrosEquipoEmpresas by criteria: {}", criteria);
        Page<MiembrosEquipoEmpresasDTO> page = miembrosEquipoEmpresasQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(uriBuilder.queryParams(queryParams), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
    * {@code GET  /miembros-equipo-empresas/count} : count all the miembrosEquipoEmpresas.
    *
    * @param criteria the criteria which the requested entities should match.
    * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
    */
    @GetMapping("/miembros-equipo-empresas/count")
    public ResponseEntity<Long> countMiembrosEquipoEmpresas(MiembrosEquipoEmpresasCriteria criteria) {
        log.debug("REST request to count MiembrosEquipoEmpresas by criteria: {}", criteria);
        return ResponseEntity.ok().body(miembrosEquipoEmpresasQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /miembros-equipo-empresas/:id} : get the "id" miembrosEquipoEmpresas.
     *
     * @param id the id of the miembrosEquipoEmpresasDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the miembrosEquipoEmpresasDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/miembros-equipo-empresas/{id}")
    public ResponseEntity<MiembrosEquipoEmpresasDTO> getMiembrosEquipoEmpresas(@PathVariable Long id) {
        log.debug("REST request to get MiembrosEquipoEmpresas : {}", id);
        Optional<MiembrosEquipoEmpresasDTO> miembrosEquipoEmpresasDTO = miembrosEquipoEmpresasService.findOne(id);
        return ResponseUtil.wrapOrNotFound(miembrosEquipoEmpresasDTO);
    }

    /**
     * {@code DELETE  /miembros-equipo-empresas/:id} : delete the "id" miembrosEquipoEmpresas.
     *
     * @param id the id of the miembrosEquipoEmpresasDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/miembros-equipo-empresas/{id}")
    public ResponseEntity<Void> deleteMiembrosEquipoEmpresas(@PathVariable Long id) {
        log.debug("REST request to delete MiembrosEquipoEmpresas : {}", id);
        miembrosEquipoEmpresasService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }

    /**
     * {@code SEARCH  /_search/miembros-equipo-empresas?query=:query} : search for the miembrosEquipoEmpresas corresponding
     * to the query.
     *
     * @param query the query of the miembrosEquipoEmpresas search.
     * @param pageable the pagination information.
     * @param queryParams a {@link MultiValueMap} query parameters.
     * @param uriBuilder a {@link UriComponentsBuilder} URI builder.
     * @return the result of the search.
     */
    @GetMapping("/_search/miembros-equipo-empresas")
    public ResponseEntity<List<MiembrosEquipoEmpresasDTO>> searchMiembrosEquipoEmpresas(@RequestParam String query, Pageable pageable, @RequestParam MultiValueMap<String, String> queryParams, UriComponentsBuilder uriBuilder) {
        log.debug("REST request to search for a page of MiembrosEquipoEmpresas for query {}", query);
        Page<MiembrosEquipoEmpresasDTO> page = miembrosEquipoEmpresasService.search(query, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(uriBuilder.queryParams(queryParams), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

}
