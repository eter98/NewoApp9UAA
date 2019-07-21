package io.github.jhipster.application.service;

import io.github.jhipster.application.service.dto.CiudadDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link io.github.jhipster.application.domain.Ciudad}.
 */
public interface CiudadService {

    /**
     * Save a ciudad.
     *
     * @param ciudadDTO the entity to save.
     * @return the persisted entity.
     */
    CiudadDTO save(CiudadDTO ciudadDTO);

    /**
     * Get all the ciudads.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<CiudadDTO> findAll(Pageable pageable);


    /**
     * Get the "id" ciudad.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<CiudadDTO> findOne(Long id);

    /**
     * Delete the "id" ciudad.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);

    /**
     * Search for the ciudad corresponding to the query.
     *
     * @param query the query of the search.
     * 
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<CiudadDTO> search(String query, Pageable pageable);
}
