package io.github.jhipster.application.web.rest;

import io.github.jhipster.application.service.ComentarioFeedService;
import io.github.jhipster.application.web.rest.errors.BadRequestAlertException;
import io.github.jhipster.application.service.dto.ComentarioFeedDTO;
import io.github.jhipster.application.service.dto.ComentarioFeedCriteria;
import io.github.jhipster.application.service.ComentarioFeedQueryService;

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
 * REST controller for managing {@link io.github.jhipster.application.domain.ComentarioFeed}.
 */
@RestController
@RequestMapping("/api")
public class ComentarioFeedResource {

    private final Logger log = LoggerFactory.getLogger(ComentarioFeedResource.class);

    private static final String ENTITY_NAME = "comentarioFeed";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ComentarioFeedService comentarioFeedService;

    private final ComentarioFeedQueryService comentarioFeedQueryService;

    public ComentarioFeedResource(ComentarioFeedService comentarioFeedService, ComentarioFeedQueryService comentarioFeedQueryService) {
        this.comentarioFeedService = comentarioFeedService;
        this.comentarioFeedQueryService = comentarioFeedQueryService;
    }

    /**
     * {@code POST  /comentario-feeds} : Create a new comentarioFeed.
     *
     * @param comentarioFeedDTO the comentarioFeedDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new comentarioFeedDTO, or with status {@code 400 (Bad Request)} if the comentarioFeed has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/comentario-feeds")
    public ResponseEntity<ComentarioFeedDTO> createComentarioFeed(@Valid @RequestBody ComentarioFeedDTO comentarioFeedDTO) throws URISyntaxException {
        log.debug("REST request to save ComentarioFeed : {}", comentarioFeedDTO);
        if (comentarioFeedDTO.getId() != null) {
            throw new BadRequestAlertException("A new comentarioFeed cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ComentarioFeedDTO result = comentarioFeedService.save(comentarioFeedDTO);
        return ResponseEntity.created(new URI("/api/comentario-feeds/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /comentario-feeds} : Updates an existing comentarioFeed.
     *
     * @param comentarioFeedDTO the comentarioFeedDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated comentarioFeedDTO,
     * or with status {@code 400 (Bad Request)} if the comentarioFeedDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the comentarioFeedDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/comentario-feeds")
    public ResponseEntity<ComentarioFeedDTO> updateComentarioFeed(@Valid @RequestBody ComentarioFeedDTO comentarioFeedDTO) throws URISyntaxException {
        log.debug("REST request to update ComentarioFeed : {}", comentarioFeedDTO);
        if (comentarioFeedDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        ComentarioFeedDTO result = comentarioFeedService.save(comentarioFeedDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, comentarioFeedDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /comentario-feeds} : get all the comentarioFeeds.
     *
     * @param pageable the pagination information.
     * @param queryParams a {@link MultiValueMap} query parameters.
     * @param uriBuilder a {@link UriComponentsBuilder} URI builder.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of comentarioFeeds in body.
     */
    @GetMapping("/comentario-feeds")
    public ResponseEntity<List<ComentarioFeedDTO>> getAllComentarioFeeds(ComentarioFeedCriteria criteria, Pageable pageable, @RequestParam MultiValueMap<String, String> queryParams, UriComponentsBuilder uriBuilder) {
        log.debug("REST request to get ComentarioFeeds by criteria: {}", criteria);
        Page<ComentarioFeedDTO> page = comentarioFeedQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(uriBuilder.queryParams(queryParams), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
    * {@code GET  /comentario-feeds/count} : count all the comentarioFeeds.
    *
    * @param criteria the criteria which the requested entities should match.
    * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
    */
    @GetMapping("/comentario-feeds/count")
    public ResponseEntity<Long> countComentarioFeeds(ComentarioFeedCriteria criteria) {
        log.debug("REST request to count ComentarioFeeds by criteria: {}", criteria);
        return ResponseEntity.ok().body(comentarioFeedQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /comentario-feeds/:id} : get the "id" comentarioFeed.
     *
     * @param id the id of the comentarioFeedDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the comentarioFeedDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/comentario-feeds/{id}")
    public ResponseEntity<ComentarioFeedDTO> getComentarioFeed(@PathVariable Long id) {
        log.debug("REST request to get ComentarioFeed : {}", id);
        Optional<ComentarioFeedDTO> comentarioFeedDTO = comentarioFeedService.findOne(id);
        return ResponseUtil.wrapOrNotFound(comentarioFeedDTO);
    }

    /**
     * {@code DELETE  /comentario-feeds/:id} : delete the "id" comentarioFeed.
     *
     * @param id the id of the comentarioFeedDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/comentario-feeds/{id}")
    public ResponseEntity<Void> deleteComentarioFeed(@PathVariable Long id) {
        log.debug("REST request to delete ComentarioFeed : {}", id);
        comentarioFeedService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }

    /**
     * {@code SEARCH  /_search/comentario-feeds?query=:query} : search for the comentarioFeed corresponding
     * to the query.
     *
     * @param query the query of the comentarioFeed search.
     * @param pageable the pagination information.
     * @param queryParams a {@link MultiValueMap} query parameters.
     * @param uriBuilder a {@link UriComponentsBuilder} URI builder.
     * @return the result of the search.
     */
    @GetMapping("/_search/comentario-feeds")
    public ResponseEntity<List<ComentarioFeedDTO>> searchComentarioFeeds(@RequestParam String query, Pageable pageable, @RequestParam MultiValueMap<String, String> queryParams, UriComponentsBuilder uriBuilder) {
        log.debug("REST request to search for a page of ComentarioFeeds for query {}", query);
        Page<ComentarioFeedDTO> page = comentarioFeedService.search(query, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(uriBuilder.queryParams(queryParams), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

}
