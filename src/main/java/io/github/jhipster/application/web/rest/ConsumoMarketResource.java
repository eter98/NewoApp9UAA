package io.github.jhipster.application.web.rest;

import io.github.jhipster.application.service.ConsumoMarketService;
import io.github.jhipster.application.web.rest.errors.BadRequestAlertException;
import io.github.jhipster.application.service.dto.ConsumoMarketDTO;
import io.github.jhipster.application.service.dto.ConsumoMarketCriteria;
import io.github.jhipster.application.service.ConsumoMarketQueryService;

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
 * REST controller for managing {@link io.github.jhipster.application.domain.ConsumoMarket}.
 */
@RestController
@RequestMapping("/api")
public class ConsumoMarketResource {

    private final Logger log = LoggerFactory.getLogger(ConsumoMarketResource.class);

    private static final String ENTITY_NAME = "consumoMarket";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ConsumoMarketService consumoMarketService;

    private final ConsumoMarketQueryService consumoMarketQueryService;

    public ConsumoMarketResource(ConsumoMarketService consumoMarketService, ConsumoMarketQueryService consumoMarketQueryService) {
        this.consumoMarketService = consumoMarketService;
        this.consumoMarketQueryService = consumoMarketQueryService;
    }

    /**
     * {@code POST  /consumo-markets} : Create a new consumoMarket.
     *
     * @param consumoMarketDTO the consumoMarketDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new consumoMarketDTO, or with status {@code 400 (Bad Request)} if the consumoMarket has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/consumo-markets")
    public ResponseEntity<ConsumoMarketDTO> createConsumoMarket(@RequestBody ConsumoMarketDTO consumoMarketDTO) throws URISyntaxException {
        log.debug("REST request to save ConsumoMarket : {}", consumoMarketDTO);
        if (consumoMarketDTO.getId() != null) {
            throw new BadRequestAlertException("A new consumoMarket cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ConsumoMarketDTO result = consumoMarketService.save(consumoMarketDTO);
        return ResponseEntity.created(new URI("/api/consumo-markets/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /consumo-markets} : Updates an existing consumoMarket.
     *
     * @param consumoMarketDTO the consumoMarketDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated consumoMarketDTO,
     * or with status {@code 400 (Bad Request)} if the consumoMarketDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the consumoMarketDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/consumo-markets")
    public ResponseEntity<ConsumoMarketDTO> updateConsumoMarket(@RequestBody ConsumoMarketDTO consumoMarketDTO) throws URISyntaxException {
        log.debug("REST request to update ConsumoMarket : {}", consumoMarketDTO);
        if (consumoMarketDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        ConsumoMarketDTO result = consumoMarketService.save(consumoMarketDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, consumoMarketDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /consumo-markets} : get all the consumoMarkets.
     *
     * @param pageable the pagination information.
     * @param queryParams a {@link MultiValueMap} query parameters.
     * @param uriBuilder a {@link UriComponentsBuilder} URI builder.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of consumoMarkets in body.
     */
    @GetMapping("/consumo-markets")
    public ResponseEntity<List<ConsumoMarketDTO>> getAllConsumoMarkets(ConsumoMarketCriteria criteria, Pageable pageable, @RequestParam MultiValueMap<String, String> queryParams, UriComponentsBuilder uriBuilder) {
        log.debug("REST request to get ConsumoMarkets by criteria: {}", criteria);
        Page<ConsumoMarketDTO> page = consumoMarketQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(uriBuilder.queryParams(queryParams), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
    * {@code GET  /consumo-markets/count} : count all the consumoMarkets.
    *
    * @param criteria the criteria which the requested entities should match.
    * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
    */
    @GetMapping("/consumo-markets/count")
    public ResponseEntity<Long> countConsumoMarkets(ConsumoMarketCriteria criteria) {
        log.debug("REST request to count ConsumoMarkets by criteria: {}", criteria);
        return ResponseEntity.ok().body(consumoMarketQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /consumo-markets/:id} : get the "id" consumoMarket.
     *
     * @param id the id of the consumoMarketDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the consumoMarketDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/consumo-markets/{id}")
    public ResponseEntity<ConsumoMarketDTO> getConsumoMarket(@PathVariable Long id) {
        log.debug("REST request to get ConsumoMarket : {}", id);
        Optional<ConsumoMarketDTO> consumoMarketDTO = consumoMarketService.findOne(id);
        return ResponseUtil.wrapOrNotFound(consumoMarketDTO);
    }

    /**
     * {@code DELETE  /consumo-markets/:id} : delete the "id" consumoMarket.
     *
     * @param id the id of the consumoMarketDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/consumo-markets/{id}")
    public ResponseEntity<Void> deleteConsumoMarket(@PathVariable Long id) {
        log.debug("REST request to delete ConsumoMarket : {}", id);
        consumoMarketService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }

    /**
     * {@code SEARCH  /_search/consumo-markets?query=:query} : search for the consumoMarket corresponding
     * to the query.
     *
     * @param query the query of the consumoMarket search.
     * @param pageable the pagination information.
     * @param queryParams a {@link MultiValueMap} query parameters.
     * @param uriBuilder a {@link UriComponentsBuilder} URI builder.
     * @return the result of the search.
     */
    @GetMapping("/_search/consumo-markets")
    public ResponseEntity<List<ConsumoMarketDTO>> searchConsumoMarkets(@RequestParam String query, Pageable pageable, @RequestParam MultiValueMap<String, String> queryParams, UriComponentsBuilder uriBuilder) {
        log.debug("REST request to search for a page of ConsumoMarkets for query {}", query);
        Page<ConsumoMarketDTO> page = consumoMarketService.search(query, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(uriBuilder.queryParams(queryParams), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

}
