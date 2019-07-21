package io.github.jhipster.application.service;

import io.github.jhipster.application.service.dto.ReservasDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link io.github.jhipster.application.domain.Reservas}.
 */
public interface ReservasService {

    /**
     * Save a reservas.
     *
     * @param reservasDTO the entity to save.
     * @return the persisted entity.
     */
    ReservasDTO save(ReservasDTO reservasDTO);

    /**
     * Get all the reservas.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<ReservasDTO> findAll(Pageable pageable);


    /**
     * Get the "id" reservas.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<ReservasDTO> findOne(Long id);

    /**
     * Delete the "id" reservas.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);

    /**
     * Search for the reservas corresponding to the query.
     *
     * @param query the query of the search.
     * 
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<ReservasDTO> search(String query, Pageable pageable);
}
