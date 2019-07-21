package io.github.jhipster.application.web.rest;

import io.github.jhipster.application.service.PerfilEquipoEmpresaService;
import io.github.jhipster.application.web.rest.errors.BadRequestAlertException;
import io.github.jhipster.application.service.dto.PerfilEquipoEmpresaDTO;
import io.github.jhipster.application.service.dto.PerfilEquipoEmpresaCriteria;
import io.github.jhipster.application.service.PerfilEquipoEmpresaQueryService;

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
 * REST controller for managing {@link io.github.jhipster.application.domain.PerfilEquipoEmpresa}.
 */
@RestController
@RequestMapping("/api")
public class PerfilEquipoEmpresaResource {

    private final Logger log = LoggerFactory.getLogger(PerfilEquipoEmpresaResource.class);

    private static final String ENTITY_NAME = "perfilEquipoEmpresa";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final PerfilEquipoEmpresaService perfilEquipoEmpresaService;

    private final PerfilEquipoEmpresaQueryService perfilEquipoEmpresaQueryService;

    public PerfilEquipoEmpresaResource(PerfilEquipoEmpresaService perfilEquipoEmpresaService, PerfilEquipoEmpresaQueryService perfilEquipoEmpresaQueryService) {
        this.perfilEquipoEmpresaService = perfilEquipoEmpresaService;
        this.perfilEquipoEmpresaQueryService = perfilEquipoEmpresaQueryService;
    }

    /**
     * {@code POST  /perfil-equipo-empresas} : Create a new perfilEquipoEmpresa.
     *
     * @param perfilEquipoEmpresaDTO the perfilEquipoEmpresaDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new perfilEquipoEmpresaDTO, or with status {@code 400 (Bad Request)} if the perfilEquipoEmpresa has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/perfil-equipo-empresas")
    public ResponseEntity<PerfilEquipoEmpresaDTO> createPerfilEquipoEmpresa(@Valid @RequestBody PerfilEquipoEmpresaDTO perfilEquipoEmpresaDTO) throws URISyntaxException {
        log.debug("REST request to save PerfilEquipoEmpresa : {}", perfilEquipoEmpresaDTO);
        if (perfilEquipoEmpresaDTO.getId() != null) {
            throw new BadRequestAlertException("A new perfilEquipoEmpresa cannot already have an ID", ENTITY_NAME, "idexists");
        }
        PerfilEquipoEmpresaDTO result = perfilEquipoEmpresaService.save(perfilEquipoEmpresaDTO);
        return ResponseEntity.created(new URI("/api/perfil-equipo-empresas/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /perfil-equipo-empresas} : Updates an existing perfilEquipoEmpresa.
     *
     * @param perfilEquipoEmpresaDTO the perfilEquipoEmpresaDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated perfilEquipoEmpresaDTO,
     * or with status {@code 400 (Bad Request)} if the perfilEquipoEmpresaDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the perfilEquipoEmpresaDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/perfil-equipo-empresas")
    public ResponseEntity<PerfilEquipoEmpresaDTO> updatePerfilEquipoEmpresa(@Valid @RequestBody PerfilEquipoEmpresaDTO perfilEquipoEmpresaDTO) throws URISyntaxException {
        log.debug("REST request to update PerfilEquipoEmpresa : {}", perfilEquipoEmpresaDTO);
        if (perfilEquipoEmpresaDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        PerfilEquipoEmpresaDTO result = perfilEquipoEmpresaService.save(perfilEquipoEmpresaDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, perfilEquipoEmpresaDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /perfil-equipo-empresas} : get all the perfilEquipoEmpresas.
     *
     * @param pageable the pagination information.
     * @param queryParams a {@link MultiValueMap} query parameters.
     * @param uriBuilder a {@link UriComponentsBuilder} URI builder.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of perfilEquipoEmpresas in body.
     */
    @GetMapping("/perfil-equipo-empresas")
    public ResponseEntity<List<PerfilEquipoEmpresaDTO>> getAllPerfilEquipoEmpresas(PerfilEquipoEmpresaCriteria criteria, Pageable pageable, @RequestParam MultiValueMap<String, String> queryParams, UriComponentsBuilder uriBuilder) {
        log.debug("REST request to get PerfilEquipoEmpresas by criteria: {}", criteria);
        Page<PerfilEquipoEmpresaDTO> page = perfilEquipoEmpresaQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(uriBuilder.queryParams(queryParams), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
    * {@code GET  /perfil-equipo-empresas/count} : count all the perfilEquipoEmpresas.
    *
    * @param criteria the criteria which the requested entities should match.
    * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
    */
    @GetMapping("/perfil-equipo-empresas/count")
    public ResponseEntity<Long> countPerfilEquipoEmpresas(PerfilEquipoEmpresaCriteria criteria) {
        log.debug("REST request to count PerfilEquipoEmpresas by criteria: {}", criteria);
        return ResponseEntity.ok().body(perfilEquipoEmpresaQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /perfil-equipo-empresas/:id} : get the "id" perfilEquipoEmpresa.
     *
     * @param id the id of the perfilEquipoEmpresaDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the perfilEquipoEmpresaDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/perfil-equipo-empresas/{id}")
    public ResponseEntity<PerfilEquipoEmpresaDTO> getPerfilEquipoEmpresa(@PathVariable Long id) {
        log.debug("REST request to get PerfilEquipoEmpresa : {}", id);
        Optional<PerfilEquipoEmpresaDTO> perfilEquipoEmpresaDTO = perfilEquipoEmpresaService.findOne(id);
        return ResponseUtil.wrapOrNotFound(perfilEquipoEmpresaDTO);
    }

    /**
     * {@code DELETE  /perfil-equipo-empresas/:id} : delete the "id" perfilEquipoEmpresa.
     *
     * @param id the id of the perfilEquipoEmpresaDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/perfil-equipo-empresas/{id}")
    public ResponseEntity<Void> deletePerfilEquipoEmpresa(@PathVariable Long id) {
        log.debug("REST request to delete PerfilEquipoEmpresa : {}", id);
        perfilEquipoEmpresaService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }

    /**
     * {@code SEARCH  /_search/perfil-equipo-empresas?query=:query} : search for the perfilEquipoEmpresa corresponding
     * to the query.
     *
     * @param query the query of the perfilEquipoEmpresa search.
     * @param pageable the pagination information.
     * @param queryParams a {@link MultiValueMap} query parameters.
     * @param uriBuilder a {@link UriComponentsBuilder} URI builder.
     * @return the result of the search.
     */
    @GetMapping("/_search/perfil-equipo-empresas")
    public ResponseEntity<List<PerfilEquipoEmpresaDTO>> searchPerfilEquipoEmpresas(@RequestParam String query, Pageable pageable, @RequestParam MultiValueMap<String, String> queryParams, UriComponentsBuilder uriBuilder) {
        log.debug("REST request to search for a page of PerfilEquipoEmpresas for query {}", query);
        Page<PerfilEquipoEmpresaDTO> page = perfilEquipoEmpresaService.search(query, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(uriBuilder.queryParams(queryParams), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

}
