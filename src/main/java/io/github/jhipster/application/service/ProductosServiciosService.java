package io.github.jhipster.application.service;

import io.github.jhipster.application.service.dto.ProductosServiciosDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link io.github.jhipster.application.domain.ProductosServicios}.
 */
public interface ProductosServiciosService {

    /**
     * Save a productosServicios.
     *
     * @param productosServiciosDTO the entity to save.
     * @return the persisted entity.
     */
    ProductosServiciosDTO save(ProductosServiciosDTO productosServiciosDTO);

    /**
     * Get all the productosServicios.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<ProductosServiciosDTO> findAll(Pageable pageable);


    /**
     * Get the "id" productosServicios.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<ProductosServiciosDTO> findOne(Long id);

    /**
     * Delete the "id" productosServicios.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);

    /**
     * Search for the productosServicios corresponding to the query.
     *
     * @param query the query of the search.
     * 
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<ProductosServiciosDTO> search(String query, Pageable pageable);
}
