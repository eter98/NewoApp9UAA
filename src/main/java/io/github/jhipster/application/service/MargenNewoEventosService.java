package io.github.jhipster.application.service;

import io.github.jhipster.application.service.dto.MargenNewoEventosDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link io.github.jhipster.application.domain.MargenNewoEventos}.
 */
public interface MargenNewoEventosService {

    /**
     * Save a margenNewoEventos.
     *
     * @param margenNewoEventosDTO the entity to save.
     * @return the persisted entity.
     */
    MargenNewoEventosDTO save(MargenNewoEventosDTO margenNewoEventosDTO);

    /**
     * Get all the margenNewoEventos.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<MargenNewoEventosDTO> findAll(Pageable pageable);


    /**
     * Get the "id" margenNewoEventos.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<MargenNewoEventosDTO> findOne(Long id);

    /**
     * Delete the "id" margenNewoEventos.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);

    /**
     * Search for the margenNewoEventos corresponding to the query.
     *
     * @param query the query of the search.
     * 
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<MargenNewoEventosDTO> search(String query, Pageable pageable);
}
