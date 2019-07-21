package io.github.jhipster.application.web.rest;

import io.github.jhipster.application.service.FeedService;
import io.github.jhipster.application.web.rest.errors.BadRequestAlertException;
import io.github.jhipster.application.service.dto.FeedDTO;
import io.github.jhipster.application.service.dto.FeedCriteria;
import io.github.jhipster.application.service.FeedQueryService;

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
 * REST controller for managing {@link io.github.jhipster.application.domain.Feed}.
 */
@RestController
@RequestMapping("/api")
public class FeedResource {

    private final Logger log = LoggerFactory.getLogger(FeedResource.class);

    private static final String ENTITY_NAME = "feed";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final FeedService feedService;

    private final FeedQueryService feedQueryService;

    public FeedResource(FeedService feedService, FeedQueryService feedQueryService) {
        this.feedService = feedService;
        this.feedQueryService = feedQueryService;
    }

    /**
     * {@code POST  /feeds} : Create a new feed.
     *
     * @param feedDTO the feedDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new feedDTO, or with status {@code 400 (Bad Request)} if the feed has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/feeds")
    public ResponseEntity<FeedDTO> createFeed(@Valid @RequestBody FeedDTO feedDTO) throws URISyntaxException {
        log.debug("REST request to save Feed : {}", feedDTO);
        if (feedDTO.getId() != null) {
            throw new BadRequestAlertException("A new feed cannot already have an ID", ENTITY_NAME, "idexists");
        }
        FeedDTO result = feedService.save(feedDTO);
        return ResponseEntity.created(new URI("/api/feeds/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /feeds} : Updates an existing feed.
     *
     * @param feedDTO the feedDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated feedDTO,
     * or with status {@code 400 (Bad Request)} if the feedDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the feedDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/feeds")
    public ResponseEntity<FeedDTO> updateFeed(@Valid @RequestBody FeedDTO feedDTO) throws URISyntaxException {
        log.debug("REST request to update Feed : {}", feedDTO);
        if (feedDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        FeedDTO result = feedService.save(feedDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, feedDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /feeds} : get all the feeds.
     *
     * @param pageable the pagination information.
     * @param queryParams a {@link MultiValueMap} query parameters.
     * @param uriBuilder a {@link UriComponentsBuilder} URI builder.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of feeds in body.
     */
    @GetMapping("/feeds")
    public ResponseEntity<List<FeedDTO>> getAllFeeds(FeedCriteria criteria, Pageable pageable, @RequestParam MultiValueMap<String, String> queryParams, UriComponentsBuilder uriBuilder) {
        log.debug("REST request to get Feeds by criteria: {}", criteria);
        Page<FeedDTO> page = feedQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(uriBuilder.queryParams(queryParams), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
    * {@code GET  /feeds/count} : count all the feeds.
    *
    * @param criteria the criteria which the requested entities should match.
    * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
    */
    @GetMapping("/feeds/count")
    public ResponseEntity<Long> countFeeds(FeedCriteria criteria) {
        log.debug("REST request to count Feeds by criteria: {}", criteria);
        return ResponseEntity.ok().body(feedQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /feeds/:id} : get the "id" feed.
     *
     * @param id the id of the feedDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the feedDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/feeds/{id}")
    public ResponseEntity<FeedDTO> getFeed(@PathVariable Long id) {
        log.debug("REST request to get Feed : {}", id);
        Optional<FeedDTO> feedDTO = feedService.findOne(id);
        return ResponseUtil.wrapOrNotFound(feedDTO);
    }

    /**
     * {@code DELETE  /feeds/:id} : delete the "id" feed.
     *
     * @param id the id of the feedDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/feeds/{id}")
    public ResponseEntity<Void> deleteFeed(@PathVariable Long id) {
        log.debug("REST request to delete Feed : {}", id);
        feedService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }

    /**
     * {@code SEARCH  /_search/feeds?query=:query} : search for the feed corresponding
     * to the query.
     *
     * @param query the query of the feed search.
     * @param pageable the pagination information.
     * @param queryParams a {@link MultiValueMap} query parameters.
     * @param uriBuilder a {@link UriComponentsBuilder} URI builder.
     * @return the result of the search.
     */
    @GetMapping("/_search/feeds")
    public ResponseEntity<List<FeedDTO>> searchFeeds(@RequestParam String query, Pageable pageable, @RequestParam MultiValueMap<String, String> queryParams, UriComponentsBuilder uriBuilder) {
        log.debug("REST request to search for a page of Feeds for query {}", query);
        Page<FeedDTO> page = feedService.search(query, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(uriBuilder.queryParams(queryParams), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

}
