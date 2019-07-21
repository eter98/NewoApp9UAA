package io.github.jhipster.application.service;

import io.github.jhipster.application.service.dto.MargenNewoProductosDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link io.github.jhipster.application.domain.MargenNewoProductos}.
 */
public interface MargenNewoProductosService {

    /**
     * Save a margenNewoProductos.
     *
     * @param margenNewoProductosDTO the entity to save.
     * @return the persisted entity.
     */
    MargenNewoProductosDTO save(MargenNewoProductosDTO margenNewoProductosDTO);

    /**
     * Get all the margenNewoProductos.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<MargenNewoProductosDTO> findAll(Pageable pageable);


    /**
     * Get the "id" margenNewoProductos.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<MargenNewoProductosDTO> findOne(Long id);

    /**
     * Delete the "id" margenNewoProductos.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);

    /**
     * Search for the margenNewoProductos corresponding to the query.
     *
     * @param query the query of the search.
     * 
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<MargenNewoProductosDTO> search(String query, Pageable pageable);
}
