package io.github.jhipster.application.service;

import io.github.jhipster.application.service.dto.SedesDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link io.github.jhipster.application.domain.Sedes}.
 */
public interface SedesService {

    /**
     * Save a sedes.
     *
     * @param sedesDTO the entity to save.
     * @return the persisted entity.
     */
    SedesDTO save(SedesDTO sedesDTO);

    /**
     * Get all the sedes.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<SedesDTO> findAll(Pageable pageable);


    /**
     * Get the "id" sedes.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<SedesDTO> findOne(Long id);

    /**
     * Delete the "id" sedes.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);

    /**
     * Search for the sedes corresponding to the query.
     *
     * @param query the query of the search.
     * 
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<SedesDTO> search(String query, Pageable pageable);
}
