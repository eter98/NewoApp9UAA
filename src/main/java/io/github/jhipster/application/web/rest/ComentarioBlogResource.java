package io.github.jhipster.application.web.rest;

import io.github.jhipster.application.service.ComentarioBlogService;
import io.github.jhipster.application.web.rest.errors.BadRequestAlertException;
import io.github.jhipster.application.service.dto.ComentarioBlogDTO;
import io.github.jhipster.application.service.dto.ComentarioBlogCriteria;
import io.github.jhipster.application.service.ComentarioBlogQueryService;

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
 * REST controller for managing {@link io.github.jhipster.application.domain.ComentarioBlog}.
 */
@RestController
@RequestMapping("/api")
public class ComentarioBlogResource {

    private final Logger log = LoggerFactory.getLogger(ComentarioBlogResource.class);

    private static final String ENTITY_NAME = "comentarioBlog";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ComentarioBlogService comentarioBlogService;

    private final ComentarioBlogQueryService comentarioBlogQueryService;

    public ComentarioBlogResource(ComentarioBlogService comentarioBlogService, ComentarioBlogQueryService comentarioBlogQueryService) {
        this.comentarioBlogService = comentarioBlogService;
        this.comentarioBlogQueryService = comentarioBlogQueryService;
    }

    /**
     * {@code POST  /comentario-blogs} : Create a new comentarioBlog.
     *
     * @param comentarioBlogDTO the comentarioBlogDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new comentarioBlogDTO, or with status {@code 400 (Bad Request)} if the comentarioBlog has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/comentario-blogs")
    public ResponseEntity<ComentarioBlogDTO> createComentarioBlog(@Valid @RequestBody ComentarioBlogDTO comentarioBlogDTO) throws URISyntaxException {
        log.debug("REST request to save ComentarioBlog : {}", comentarioBlogDTO);
        if (comentarioBlogDTO.getId() != null) {
            throw new BadRequestAlertException("A new comentarioBlog cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ComentarioBlogDTO result = comentarioBlogService.save(comentarioBlogDTO);
        return ResponseEntity.created(new URI("/api/comentario-blogs/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /comentario-blogs} : Updates an existing comentarioBlog.
     *
     * @param comentarioBlogDTO the comentarioBlogDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated comentarioBlogDTO,
     * or with status {@code 400 (Bad Request)} if the comentarioBlogDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the comentarioBlogDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/comentario-blogs")
    public ResponseEntity<ComentarioBlogDTO> updateComentarioBlog(@Valid @RequestBody ComentarioBlogDTO comentarioBlogDTO) throws URISyntaxException {
        log.debug("REST request to update ComentarioBlog : {}", comentarioBlogDTO);
        if (comentarioBlogDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        ComentarioBlogDTO result = comentarioBlogService.save(comentarioBlogDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, comentarioBlogDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /comentario-blogs} : get all the comentarioBlogs.
     *
     * @param pageable the pagination information.
     * @param queryParams a {@link MultiValueMap} query parameters.
     * @param uriBuilder a {@link UriComponentsBuilder} URI builder.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of comentarioBlogs in body.
     */
    @GetMapping("/comentario-blogs")
    public ResponseEntity<List<ComentarioBlogDTO>> getAllComentarioBlogs(ComentarioBlogCriteria criteria, Pageable pageable, @RequestParam MultiValueMap<String, String> queryParams, UriComponentsBuilder uriBuilder) {
        log.debug("REST request to get ComentarioBlogs by criteria: {}", criteria);
        Page<ComentarioBlogDTO> page = comentarioBlogQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(uriBuilder.queryParams(queryParams), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
    * {@code GET  /comentario-blogs/count} : count all the comentarioBlogs.
    *
    * @param criteria the criteria which the requested entities should match.
    * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
    */
    @GetMapping("/comentario-blogs/count")
    public ResponseEntity<Long> countComentarioBlogs(ComentarioBlogCriteria criteria) {
        log.debug("REST request to count ComentarioBlogs by criteria: {}", criteria);
        return ResponseEntity.ok().body(comentarioBlogQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /comentario-blogs/:id} : get the "id" comentarioBlog.
     *
     * @param id the id of the comentarioBlogDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the comentarioBlogDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/comentario-blogs/{id}")
    public ResponseEntity<ComentarioBlogDTO> getComentarioBlog(@PathVariable Long id) {
        log.debug("REST request to get ComentarioBlog : {}", id);
        Optional<ComentarioBlogDTO> comentarioBlogDTO = comentarioBlogService.findOne(id);
        return ResponseUtil.wrapOrNotFound(comentarioBlogDTO);
    }

    /**
     * {@code DELETE  /comentario-blogs/:id} : delete the "id" comentarioBlog.
     *
     * @param id the id of the comentarioBlogDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/comentario-blogs/{id}")
    public ResponseEntity<Void> deleteComentarioBlog(@PathVariable Long id) {
        log.debug("REST request to delete ComentarioBlog : {}", id);
        comentarioBlogService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }

    /**
     * {@code SEARCH  /_search/comentario-blogs?query=:query} : search for the comentarioBlog corresponding
     * to the query.
     *
     * @param query the query of the comentarioBlog search.
     * @param pageable the pagination information.
     * @param queryParams a {@link MultiValueMap} query parameters.
     * @param uriBuilder a {@link UriComponentsBuilder} URI builder.
     * @return the result of the search.
     */
    @GetMapping("/_search/comentario-blogs")
    public ResponseEntity<List<ComentarioBlogDTO>> searchComentarioBlogs(@RequestParam String query, Pageable pageable, @RequestParam MultiValueMap<String, String> queryParams, UriComponentsBuilder uriBuilder) {
        log.debug("REST request to search for a page of ComentarioBlogs for query {}", query);
        Page<ComentarioBlogDTO> page = comentarioBlogService.search(query, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(uriBuilder.queryParams(queryParams), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

}
