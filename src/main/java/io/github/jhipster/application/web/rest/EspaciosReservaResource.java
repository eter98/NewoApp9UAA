package io.github.jhipster.application.web.rest;

import io.github.jhipster.application.service.EspaciosReservaService;
import io.github.jhipster.application.web.rest.errors.BadRequestAlertException;
import io.github.jhipster.application.service.dto.EspaciosReservaDTO;
import io.github.jhipster.application.service.dto.EspaciosReservaCriteria;
import io.github.jhipster.application.service.EspaciosReservaQueryService;

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
 * REST controller for managing {@link io.github.jhipster.application.domain.EspaciosReserva}.
 */
@RestController
@RequestMapping("/api")
public class EspaciosReservaResource {

    private final Logger log = LoggerFactory.getLogger(EspaciosReservaResource.class);

    private static final String ENTITY_NAME = "espaciosReserva";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final EspaciosReservaService espaciosReservaService;

    private final EspaciosReservaQueryService espaciosReservaQueryService;

    public EspaciosReservaResource(EspaciosReservaService espaciosReservaService, EspaciosReservaQueryService espaciosReservaQueryService) {
        this.espaciosReservaService = espaciosReservaService;
        this.espaciosReservaQueryService = espaciosReservaQueryService;
    }

    /**
     * {@code POST  /espacios-reservas} : Create a new espaciosReserva.
     *
     * @param espaciosReservaDTO the espaciosReservaDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new espaciosReservaDTO, or with status {@code 400 (Bad Request)} if the espaciosReserva has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/espacios-reservas")
    public ResponseEntity<EspaciosReservaDTO> createEspaciosReserva(@Valid @RequestBody EspaciosReservaDTO espaciosReservaDTO) throws URISyntaxException {
        log.debug("REST request to save EspaciosReserva : {}", espaciosReservaDTO);
        if (espaciosReservaDTO.getId() != null) {
            throw new BadRequestAlertException("A new espaciosReserva cannot already have an ID", ENTITY_NAME, "idexists");
        }
        EspaciosReservaDTO result = espaciosReservaService.save(espaciosReservaDTO);
        return ResponseEntity.created(new URI("/api/espacios-reservas/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /espacios-reservas} : Updates an existing espaciosReserva.
     *
     * @param espaciosReservaDTO the espaciosReservaDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated espaciosReservaDTO,
     * or with status {@code 400 (Bad Request)} if the espaciosReservaDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the espaciosReservaDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/espacios-reservas")
    public ResponseEntity<EspaciosReservaDTO> updateEspaciosReserva(@Valid @RequestBody EspaciosReservaDTO espaciosReservaDTO) throws URISyntaxException {
        log.debug("REST request to update EspaciosReserva : {}", espaciosReservaDTO);
        if (espaciosReservaDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        EspaciosReservaDTO result = espaciosReservaService.save(espaciosReservaDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, espaciosReservaDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /espacios-reservas} : get all the espaciosReservas.
     *
     * @param pageable the pagination information.
     * @param queryParams a {@link MultiValueMap} query parameters.
     * @param uriBuilder a {@link UriComponentsBuilder} URI builder.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of espaciosReservas in body.
     */
    @GetMapping("/espacios-reservas")
    public ResponseEntity<List<EspaciosReservaDTO>> getAllEspaciosReservas(EspaciosReservaCriteria criteria, Pageable pageable, @RequestParam MultiValueMap<String, String> queryParams, UriComponentsBuilder uriBuilder) {
        log.debug("REST request to get EspaciosReservas by criteria: {}", criteria);
        Page<EspaciosReservaDTO> page = espaciosReservaQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(uriBuilder.queryParams(queryParams), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
    * {@code GET  /espacios-reservas/count} : count all the espaciosReservas.
    *
    * @param criteria the criteria which the requested entities should match.
    * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
    */
    @GetMapping("/espacios-reservas/count")
    public ResponseEntity<Long> countEspaciosReservas(EspaciosReservaCriteria criteria) {
        log.debug("REST request to count EspaciosReservas by criteria: {}", criteria);
        return ResponseEntity.ok().body(espaciosReservaQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /espacios-reservas/:id} : get the "id" espaciosReserva.
     *
     * @param id the id of the espaciosReservaDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the espaciosReservaDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/espacios-reservas/{id}")
    public ResponseEntity<EspaciosReservaDTO> getEspaciosReserva(@PathVariable Long id) {
        log.debug("REST request to get EspaciosReserva : {}", id);
        Optional<EspaciosReservaDTO> espaciosReservaDTO = espaciosReservaService.findOne(id);
        return ResponseUtil.wrapOrNotFound(espaciosReservaDTO);
    }

    /**
     * {@code DELETE  /espacios-reservas/:id} : delete the "id" espaciosReserva.
     *
     * @param id the id of the espaciosReservaDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/espacios-reservas/{id}")
    public ResponseEntity<Void> deleteEspaciosReserva(@PathVariable Long id) {
        log.debug("REST request to delete EspaciosReserva : {}", id);
        espaciosReservaService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }

    /**
     * {@code SEARCH  /_search/espacios-reservas?query=:query} : search for the espaciosReserva corresponding
     * to the query.
     *
     * @param query the query of the espaciosReserva search.
     * @param pageable the pagination information.
     * @param queryParams a {@link MultiValueMap} query parameters.
     * @param uriBuilder a {@link UriComponentsBuilder} URI builder.
     * @return the result of the search.
     */
    @GetMapping("/_search/espacios-reservas")
    public ResponseEntity<List<EspaciosReservaDTO>> searchEspaciosReservas(@RequestParam String query, Pageable pageable, @RequestParam MultiValueMap<String, String> queryParams, UriComponentsBuilder uriBuilder) {
        log.debug("REST request to search for a page of EspaciosReservas for query {}", query);
        Page<EspaciosReservaDTO> page = espaciosReservaService.search(query, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(uriBuilder.queryParams(queryParams), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

}
