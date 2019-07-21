package io.github.jhipster.application.service;

import io.github.jhipster.application.service.dto.GruposDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link io.github.jhipster.application.domain.Grupos}.
 */
public interface GruposService {

    /**
     * Save a grupos.
     *
     * @param gruposDTO the entity to save.
     * @return the persisted entity.
     */
    GruposDTO save(GruposDTO gruposDTO);

    /**
     * Get all the grupos.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<GruposDTO> findAll(Pageable pageable);


    /**
     * Get the "id" grupos.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<GruposDTO> findOne(Long id);

    /**
     * Delete the "id" grupos.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);

    /**
     * Search for the grupos corresponding to the query.
     *
     * @param query the query of the search.
     * 
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<GruposDTO> search(String query, Pageable pageable);
}
