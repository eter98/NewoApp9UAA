package io.github.jhipster.application.web.rest;

import io.github.jhipster.application.service.LandingService;
import io.github.jhipster.application.web.rest.errors.BadRequestAlertException;
import io.github.jhipster.application.service.dto.LandingDTO;
import io.github.jhipster.application.service.dto.LandingCriteria;
import io.github.jhipster.application.service.LandingQueryService;

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
 * REST controller for managing {@link io.github.jhipster.application.domain.Landing}.
 */
@RestController
@RequestMapping("/api")
public class LandingResource {

    private final Logger log = LoggerFactory.getLogger(LandingResource.class);

    private static final String ENTITY_NAME = "landing";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final LandingService landingService;

    private final LandingQueryService landingQueryService;

    public LandingResource(LandingService landingService, LandingQueryService landingQueryService) {
        this.landingService = landingService;
        this.landingQueryService = landingQueryService;
    }

    /**
     * {@code POST  /landings} : Create a new landing.
     *
     * @param landingDTO the landingDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new landingDTO, or with status {@code 400 (Bad Request)} if the landing has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/landings")
    public ResponseEntity<LandingDTO> createLanding(@Valid @RequestBody LandingDTO landingDTO) throws URISyntaxException {
        log.debug("REST request to save Landing : {}", landingDTO);
        if (landingDTO.getId() != null) {
            throw new BadRequestAlertException("A new landing cannot already have an ID", ENTITY_NAME, "idexists");
        }
        LandingDTO result = landingService.save(landingDTO);
        return ResponseEntity.created(new URI("/api/landings/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /landings} : Updates an existing landing.
     *
     * @param landingDTO the landingDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated landingDTO,
     * or with status {@code 400 (Bad Request)} if the landingDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the landingDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/landings")
    public ResponseEntity<LandingDTO> updateLanding(@Valid @RequestBody LandingDTO landingDTO) throws URISyntaxException {
        log.debug("REST request to update Landing : {}", landingDTO);
        if (landingDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        LandingDTO result = landingService.save(landingDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, landingDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /landings} : get all the landings.
     *
     * @param pageable the pagination information.
     * @param queryParams a {@link MultiValueMap} query parameters.
     * @param uriBuilder a {@link UriComponentsBuilder} URI builder.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of landings in body.
     */
    @GetMapping("/landings")
    public ResponseEntity<List<LandingDTO>> getAllLandings(LandingCriteria criteria, Pageable pageable, @RequestParam MultiValueMap<String, String> queryParams, UriComponentsBuilder uriBuilder) {
        log.debug("REST request to get Landings by criteria: {}", criteria);
        Page<LandingDTO> page = landingQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(uriBuilder.queryParams(queryParams), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
    * {@code GET  /landings/count} : count all the landings.
    *
    * @param criteria the criteria which the requested entities should match.
    * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
    */
    @GetMapping("/landings/count")
    public ResponseEntity<Long> countLandings(LandingCriteria criteria) {
        log.debug("REST request to count Landings by criteria: {}", criteria);
        return ResponseEntity.ok().body(landingQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /landings/:id} : get the "id" landing.
     *
     * @param id the id of the landingDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the landingDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/landings/{id}")
    public ResponseEntity<LandingDTO> getLanding(@PathVariable Long id) {
        log.debug("REST request to get Landing : {}", id);
        Optional<LandingDTO> landingDTO = landingService.findOne(id);
        return ResponseUtil.wrapOrNotFound(landingDTO);
    }

    /**
     * {@code DELETE  /landings/:id} : delete the "id" landing.
     *
     * @param id the id of the landingDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/landings/{id}")
    public ResponseEntity<Void> deleteLanding(@PathVariable Long id) {
        log.debug("REST request to delete Landing : {}", id);
        landingService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }

    /**
     * {@code SEARCH  /_search/landings?query=:query} : search for the landing corresponding
     * to the query.
     *
     * @param query the query of the landing search.
     * @param pageable the pagination information.
     * @param queryParams a {@link MultiValueMap} query parameters.
     * @param uriBuilder a {@link UriComponentsBuilder} URI builder.
     * @return the result of the search.
     */
    @GetMapping("/_search/landings")
    public ResponseEntity<List<LandingDTO>> searchLandings(@RequestParam String query, Pageable pageable, @RequestParam MultiValueMap<String, String> queryParams, UriComponentsBuilder uriBuilder) {
        log.debug("REST request to search for a page of Landings for query {}", query);
        Page<LandingDTO> page = landingService.search(query, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(uriBuilder.queryParams(queryParams), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

}
