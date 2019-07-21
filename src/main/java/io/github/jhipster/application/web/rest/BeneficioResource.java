package io.github.jhipster.application.web.rest;

import io.github.jhipster.application.service.BeneficioService;
import io.github.jhipster.application.web.rest.errors.BadRequestAlertException;
import io.github.jhipster.application.service.dto.BeneficioDTO;
import io.github.jhipster.application.service.dto.BeneficioCriteria;
import io.github.jhipster.application.service.BeneficioQueryService;

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
 * REST controller for managing {@link io.github.jhipster.application.domain.Beneficio}.
 */
@RestController
@RequestMapping("/api")
public class BeneficioResource {

    private final Logger log = LoggerFactory.getLogger(BeneficioResource.class);

    private static final String ENTITY_NAME = "beneficio";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final BeneficioService beneficioService;

    private final BeneficioQueryService beneficioQueryService;

    public BeneficioResource(BeneficioService beneficioService, BeneficioQueryService beneficioQueryService) {
        this.beneficioService = beneficioService;
        this.beneficioQueryService = beneficioQueryService;
    }

    /**
     * {@code POST  /beneficios} : Create a new beneficio.
     *
     * @param beneficioDTO the beneficioDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new beneficioDTO, or with status {@code 400 (Bad Request)} if the beneficio has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/beneficios")
    public ResponseEntity<BeneficioDTO> createBeneficio(@Valid @RequestBody BeneficioDTO beneficioDTO) throws URISyntaxException {
        log.debug("REST request to save Beneficio : {}", beneficioDTO);
        if (beneficioDTO.getId() != null) {
            throw new BadRequestAlertException("A new beneficio cannot already have an ID", ENTITY_NAME, "idexists");
        }
        BeneficioDTO result = beneficioService.save(beneficioDTO);
        return ResponseEntity.created(new URI("/api/beneficios/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /beneficios} : Updates an existing beneficio.
     *
     * @param beneficioDTO the beneficioDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated beneficioDTO,
     * or with status {@code 400 (Bad Request)} if the beneficioDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the beneficioDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/beneficios")
    public ResponseEntity<BeneficioDTO> updateBeneficio(@Valid @RequestBody BeneficioDTO beneficioDTO) throws URISyntaxException {
        log.debug("REST request to update Beneficio : {}", beneficioDTO);
        if (beneficioDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        BeneficioDTO result = beneficioService.save(beneficioDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, beneficioDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /beneficios} : get all the beneficios.
     *
     * @param pageable the pagination information.
     * @param queryParams a {@link MultiValueMap} query parameters.
     * @param uriBuilder a {@link UriComponentsBuilder} URI builder.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of beneficios in body.
     */
    @GetMapping("/beneficios")
    public ResponseEntity<List<BeneficioDTO>> getAllBeneficios(BeneficioCriteria criteria, Pageable pageable, @RequestParam MultiValueMap<String, String> queryParams, UriComponentsBuilder uriBuilder) {
        log.debug("REST request to get Beneficios by criteria: {}", criteria);
        Page<BeneficioDTO> page = beneficioQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(uriBuilder.queryParams(queryParams), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
    * {@code GET  /beneficios/count} : count all the beneficios.
    *
    * @param criteria the criteria which the requested entities should match.
    * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
    */
    @GetMapping("/beneficios/count")
    public ResponseEntity<Long> countBeneficios(BeneficioCriteria criteria) {
        log.debug("REST request to count Beneficios by criteria: {}", criteria);
        return ResponseEntity.ok().body(beneficioQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /beneficios/:id} : get the "id" beneficio.
     *
     * @param id the id of the beneficioDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the beneficioDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/beneficios/{id}")
    public ResponseEntity<BeneficioDTO> getBeneficio(@PathVariable Long id) {
        log.debug("REST request to get Beneficio : {}", id);
        Optional<BeneficioDTO> beneficioDTO = beneficioService.findOne(id);
        return ResponseUtil.wrapOrNotFound(beneficioDTO);
    }

    /**
     * {@code DELETE  /beneficios/:id} : delete the "id" beneficio.
     *
     * @param id the id of the beneficioDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/beneficios/{id}")
    public ResponseEntity<Void> deleteBeneficio(@PathVariable Long id) {
        log.debug("REST request to delete Beneficio : {}", id);
        beneficioService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }

    /**
     * {@code SEARCH  /_search/beneficios?query=:query} : search for the beneficio corresponding
     * to the query.
     *
     * @param query the query of the beneficio search.
     * @param pageable the pagination information.
     * @param queryParams a {@link MultiValueMap} query parameters.
     * @param uriBuilder a {@link UriComponentsBuilder} URI builder.
     * @return the result of the search.
     */
    @GetMapping("/_search/beneficios")
    public ResponseEntity<List<BeneficioDTO>> searchBeneficios(@RequestParam String query, Pageable pageable, @RequestParam MultiValueMap<String, String> queryParams, UriComponentsBuilder uriBuilder) {
        log.debug("REST request to search for a page of Beneficios for query {}", query);
        Page<BeneficioDTO> page = beneficioService.search(query, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(uriBuilder.queryParams(queryParams), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

}
