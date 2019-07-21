package io.github.jhipster.application.service;

import io.github.jhipster.application.service.dto.MargenNewoGruposDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link io.github.jhipster.application.domain.MargenNewoGrupos}.
 */
public interface MargenNewoGruposService {

    /**
     * Save a margenNewoGrupos.
     *
     * @param margenNewoGruposDTO the entity to save.
     * @return the persisted entity.
     */
    MargenNewoGruposDTO save(MargenNewoGruposDTO margenNewoGruposDTO);

    /**
     * Get all the margenNewoGrupos.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<MargenNewoGruposDTO> findAll(Pageable pageable);


    /**
     * Get the "id" margenNewoGrupos.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<MargenNewoGruposDTO> findOne(Long id);

    /**
     * Delete the "id" margenNewoGrupos.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);

    /**
     * Search for the margenNewoGrupos corresponding to the query.
     *
     * @param query the query of the search.
     * 
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<MargenNewoGruposDTO> search(String query, Pageable pageable);
}
