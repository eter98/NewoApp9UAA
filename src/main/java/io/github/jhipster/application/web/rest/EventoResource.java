package io.github.jhipster.application.web.rest;

import io.github.jhipster.application.service.EventoService;
import io.github.jhipster.application.web.rest.errors.BadRequestAlertException;
import io.github.jhipster.application.service.dto.EventoDTO;
import io.github.jhipster.application.service.dto.EventoCriteria;
import io.github.jhipster.application.service.EventoQueryService;

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
 * REST controller for managing {@link io.github.jhipster.application.domain.Evento}.
 */
@RestController
@RequestMapping("/api")
public class EventoResource {

    private final Logger log = LoggerFactory.getLogger(EventoResource.class);

    private static final String ENTITY_NAME = "evento";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final EventoService eventoService;

    private final EventoQueryService eventoQueryService;

    public EventoResource(EventoService eventoService, EventoQueryService eventoQueryService) {
        this.eventoService = eventoService;
        this.eventoQueryService = eventoQueryService;
    }

    /**
     * {@code POST  /eventos} : Create a new evento.
     *
     * @param eventoDTO the eventoDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new eventoDTO, or with status {@code 400 (Bad Request)} if the evento has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/eventos")
    public ResponseEntity<EventoDTO> createEvento(@Valid @RequestBody EventoDTO eventoDTO) throws URISyntaxException {
        log.debug("REST request to save Evento : {}", eventoDTO);
        if (eventoDTO.getId() != null) {
            throw new BadRequestAlertException("A new evento cannot already have an ID", ENTITY_NAME, "idexists");
        }
        EventoDTO result = eventoService.save(eventoDTO);
        return ResponseEntity.created(new URI("/api/eventos/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /eventos} : Updates an existing evento.
     *
     * @param eventoDTO the eventoDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated eventoDTO,
     * or with status {@code 400 (Bad Request)} if the eventoDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the eventoDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/eventos")
    public ResponseEntity<EventoDTO> updateEvento(@Valid @RequestBody EventoDTO eventoDTO) throws URISyntaxException {
        log.debug("REST request to update Evento : {}", eventoDTO);
        if (eventoDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        EventoDTO result = eventoService.save(eventoDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, eventoDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /eventos} : get all the eventos.
     *
     * @param pageable the pagination information.
     * @param queryParams a {@link MultiValueMap} query parameters.
     * @param uriBuilder a {@link UriComponentsBuilder} URI builder.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of eventos in body.
     */
    @GetMapping("/eventos")
    public ResponseEntity<List<EventoDTO>> getAllEventos(EventoCriteria criteria, Pageable pageable, @RequestParam MultiValueMap<String, String> queryParams, UriComponentsBuilder uriBuilder) {
        log.debug("REST request to get Eventos by criteria: {}", criteria);
        Page<EventoDTO> page = eventoQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(uriBuilder.queryParams(queryParams), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
    * {@code GET  /eventos/count} : count all the eventos.
    *
    * @param criteria the criteria which the requested entities should match.
    * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
    */
    @GetMapping("/eventos/count")
    public ResponseEntity<Long> countEventos(EventoCriteria criteria) {
        log.debug("REST request to count Eventos by criteria: {}", criteria);
        return ResponseEntity.ok().body(eventoQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /eventos/:id} : get the "id" evento.
     *
     * @param id the id of the eventoDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the eventoDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/eventos/{id}")
    public ResponseEntity<EventoDTO> getEvento(@PathVariable Long id) {
        log.debug("REST request to get Evento : {}", id);
        Optional<EventoDTO> eventoDTO = eventoService.findOne(id);
        return ResponseUtil.wrapOrNotFound(eventoDTO);
    }

    /**
     * {@code DELETE  /eventos/:id} : delete the "id" evento.
     *
     * @param id the id of the eventoDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/eventos/{id}")
    public ResponseEntity<Void> deleteEvento(@PathVariable Long id) {
        log.debug("REST request to delete Evento : {}", id);
        eventoService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }

    /**
     * {@code SEARCH  /_search/eventos?query=:query} : search for the evento corresponding
     * to the query.
     *
     * @param query the query of the evento search.
     * @param pageable the pagination information.
     * @param queryParams a {@link MultiValueMap} query parameters.
     * @param uriBuilder a {@link UriComponentsBuilder} URI builder.
     * @return the result of the search.
     */
    @GetMapping("/_search/eventos")
    public ResponseEntity<List<EventoDTO>> searchEventos(@RequestParam String query, Pageable pageable, @RequestParam MultiValueMap<String, String> queryParams, UriComponentsBuilder uriBuilder) {
        log.debug("REST request to search for a page of Eventos for query {}", query);
        Page<EventoDTO> page = eventoService.search(query, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(uriBuilder.queryParams(queryParams), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

}
