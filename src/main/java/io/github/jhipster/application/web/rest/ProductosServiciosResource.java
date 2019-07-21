package io.github.jhipster.application.web.rest;

import io.github.jhipster.application.service.ProductosServiciosService;
import io.github.jhipster.application.web.rest.errors.BadRequestAlertException;
import io.github.jhipster.application.service.dto.ProductosServiciosDTO;
import io.github.jhipster.application.service.dto.ProductosServiciosCriteria;
import io.github.jhipster.application.service.ProductosServiciosQueryService;

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
 * REST controller for managing {@link io.github.jhipster.application.domain.ProductosServicios}.
 */
@RestController
@RequestMapping("/api")
public class ProductosServiciosResource {

    private final Logger log = LoggerFactory.getLogger(ProductosServiciosResource.class);

    private static final String ENTITY_NAME = "productosServicios";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ProductosServiciosService productosServiciosService;

    private final ProductosServiciosQueryService productosServiciosQueryService;

    public ProductosServiciosResource(ProductosServiciosService productosServiciosService, ProductosServiciosQueryService productosServiciosQueryService) {
        this.productosServiciosService = productosServiciosService;
        this.productosServiciosQueryService = productosServiciosQueryService;
    }

    /**
     * {@code POST  /productos-servicios} : Create a new productosServicios.
     *
     * @param productosServiciosDTO the productosServiciosDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new productosServiciosDTO, or with status {@code 400 (Bad Request)} if the productosServicios has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/productos-servicios")
    public ResponseEntity<ProductosServiciosDTO> createProductosServicios(@Valid @RequestBody ProductosServiciosDTO productosServiciosDTO) throws URISyntaxException {
        log.debug("REST request to save ProductosServicios : {}", productosServiciosDTO);
        if (productosServiciosDTO.getId() != null) {
            throw new BadRequestAlertException("A new productosServicios cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ProductosServiciosDTO result = productosServiciosService.save(productosServiciosDTO);
        return ResponseEntity.created(new URI("/api/productos-servicios/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /productos-servicios} : Updates an existing productosServicios.
     *
     * @param productosServiciosDTO the productosServiciosDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated productosServiciosDTO,
     * or with status {@code 400 (Bad Request)} if the productosServiciosDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the productosServiciosDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/productos-servicios")
    public ResponseEntity<ProductosServiciosDTO> updateProductosServicios(@Valid @RequestBody ProductosServiciosDTO productosServiciosDTO) throws URISyntaxException {
        log.debug("REST request to update ProductosServicios : {}", productosServiciosDTO);
        if (productosServiciosDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        ProductosServiciosDTO result = productosServiciosService.save(productosServiciosDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, productosServiciosDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /productos-servicios} : get all the productosServicios.
     *
     * @param pageable the pagination information.
     * @param queryParams a {@link MultiValueMap} query parameters.
     * @param uriBuilder a {@link UriComponentsBuilder} URI builder.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of productosServicios in body.
     */
    @GetMapping("/productos-servicios")
    public ResponseEntity<List<ProductosServiciosDTO>> getAllProductosServicios(ProductosServiciosCriteria criteria, Pageable pageable, @RequestParam MultiValueMap<String, String> queryParams, UriComponentsBuilder uriBuilder) {
        log.debug("REST request to get ProductosServicios by criteria: {}", criteria);
        Page<ProductosServiciosDTO> page = productosServiciosQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(uriBuilder.queryParams(queryParams), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
    * {@code GET  /productos-servicios/count} : count all the productosServicios.
    *
    * @param criteria the criteria which the requested entities should match.
    * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
    */
    @GetMapping("/productos-servicios/count")
    public ResponseEntity<Long> countProductosServicios(ProductosServiciosCriteria criteria) {
        log.debug("REST request to count ProductosServicios by criteria: {}", criteria);
        return ResponseEntity.ok().body(productosServiciosQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /productos-servicios/:id} : get the "id" productosServicios.
     *
     * @param id the id of the productosServiciosDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the productosServiciosDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/productos-servicios/{id}")
    public ResponseEntity<ProductosServiciosDTO> getProductosServicios(@PathVariable Long id) {
        log.debug("REST request to get ProductosServicios : {}", id);
        Optional<ProductosServiciosDTO> productosServiciosDTO = productosServiciosService.findOne(id);
        return ResponseUtil.wrapOrNotFound(productosServiciosDTO);
    }

    /**
     * {@code DELETE  /productos-servicios/:id} : delete the "id" productosServicios.
     *
     * @param id the id of the productosServiciosDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/productos-servicios/{id}")
    public ResponseEntity<Void> deleteProductosServicios(@PathVariable Long id) {
        log.debug("REST request to delete ProductosServicios : {}", id);
        productosServiciosService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }

    /**
     * {@code SEARCH  /_search/productos-servicios?query=:query} : search for the productosServicios corresponding
     * to the query.
     *
     * @param query the query of the productosServicios search.
     * @param pageable the pagination information.
     * @param queryParams a {@link MultiValueMap} query parameters.
     * @param uriBuilder a {@link UriComponentsBuilder} URI builder.
     * @return the result of the search.
     */
    @GetMapping("/_search/productos-servicios")
    public ResponseEntity<List<ProductosServiciosDTO>> searchProductosServicios(@RequestParam String query, Pageable pageable, @RequestParam MultiValueMap<String, String> queryParams, UriComponentsBuilder uriBuilder) {
        log.debug("REST request to search for a page of ProductosServicios for query {}", query);
        Page<ProductosServiciosDTO> page = productosServiciosService.search(query, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(uriBuilder.queryParams(queryParams), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

}
