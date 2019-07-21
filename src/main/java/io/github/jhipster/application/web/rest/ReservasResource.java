package io.github.jhipster.application.web.rest;

import io.github.jhipster.application.service.ReservasService;
import io.github.jhipster.application.web.rest.errors.BadRequestAlertException;
import io.github.jhipster.application.service.dto.ReservasDTO;
import io.github.jhipster.application.service.dto.ReservasCriteria;
import io.github.jhipster.application.service.ReservasQueryService;

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
 * REST controller for managing {@link io.github.jhipster.application.domain.Reservas}.
 */
@RestController
@RequestMapping("/api")
public class ReservasResource {

    private final Logger log = LoggerFactory.getLogger(ReservasResource.class);

    private static final String ENTITY_NAME = "reservas";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ReservasService reservasService;

    private final ReservasQueryService reservasQueryService;

    public ReservasResource(ReservasService reservasService, ReservasQueryService reservasQueryService) {
        this.reservasService = reservasService;
        this.reservasQueryService = reservasQueryService;
    }

    /**
     * {@code POST  /reservas} : Create a new reservas.
     *
     * @param reservasDTO the reservasDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new reservasDTO, or with status {@code 400 (Bad Request)} if the reservas has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/reservas")
    public ResponseEntity<ReservasDTO> createReservas(@Valid @RequestBody ReservasDTO reservasDTO) throws URISyntaxException {
        log.debug("REST request to save Reservas : {}", reservasDTO);
        if (reservasDTO.getId() != null) {
            throw new BadRequestAlertException("A new reservas cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ReservasDTO result = reservasService.save(reservasDTO);
        return ResponseEntity.created(new URI("/api/reservas/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /reservas} : Updates an existing reservas.
     *
     * @param reservasDTO the reservasDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated reservasDTO,
     * or with status {@code 400 (Bad Request)} if the reservasDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the reservasDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/reservas")
    public ResponseEntity<ReservasDTO> updateReservas(@Valid @RequestBody ReservasDTO reservasDTO) throws URISyntaxException {
        log.debug("REST request to update Reservas : {}", reservasDTO);
        if (reservasDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        ReservasDTO result = reservasService.save(reservasDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, reservasDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /reservas} : get all the reservas.
     *
     * @param pageable the pagination information.
     * @param queryParams a {@link MultiValueMap} query parameters.
     * @param uriBuilder a {@link UriComponentsBuilder} URI builder.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of reservas in body.
     */
    @GetMapping("/reservas")
    public ResponseEntity<List<ReservasDTO>> getAllReservas(ReservasCriteria criteria, Pageable pageable, @RequestParam MultiValueMap<String, String> queryParams, UriComponentsBuilder uriBuilder) {
        log.debug("REST request to get Reservas by criteria: {}", criteria);
        Page<ReservasDTO> page = reservasQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(uriBuilder.queryParams(queryParams), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
    * {@code GET  /reservas/count} : count all the reservas.
    *
    * @param criteria the criteria which the requested entities should match.
    * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
    */
    @GetMapping("/reservas/count")
    public ResponseEntity<Long> countReservas(ReservasCriteria criteria) {
        log.debug("REST request to count Reservas by criteria: {}", criteria);
        return ResponseEntity.ok().body(reservasQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /reservas/:id} : get the "id" reservas.
     *
     * @param id the id of the reservasDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the reservasDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/reservas/{id}")
    public ResponseEntity<ReservasDTO> getReservas(@PathVariable Long id) {
        log.debug("REST request to get Reservas : {}", id);
        Optional<ReservasDTO> reservasDTO = reservasService.findOne(id);
        return ResponseUtil.wrapOrNotFound(reservasDTO);
    }

    /**
     * {@code DELETE  /reservas/:id} : delete the "id" reservas.
     *
     * @param id the id of the reservasDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/reservas/{id}")
    public ResponseEntity<Void> deleteReservas(@PathVariable Long id) {
        log.debug("REST request to delete Reservas : {}", id);
        reservasService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }

    /**
     * {@code SEARCH  /_search/reservas?query=:query} : search for the reservas corresponding
     * to the query.
     *
     * @param query the query of the reservas search.
     * @param pageable the pagination information.
     * @param queryParams a {@link MultiValueMap} query parameters.
     * @param uriBuilder a {@link UriComponentsBuilder} URI builder.
     * @return the result of the search.
     */
    @GetMapping("/_search/reservas")
    public ResponseEntity<List<ReservasDTO>> searchReservas(@RequestParam String query, Pageable pageable, @RequestParam MultiValueMap<String, String> queryParams, UriComponentsBuilder uriBuilder) {
        log.debug("REST request to search for a page of Reservas for query {}", query);
        Page<ReservasDTO> page = reservasService.search(query, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(uriBuilder.queryParams(queryParams), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

}
