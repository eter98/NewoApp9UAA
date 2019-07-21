package io.github.jhipster.application.service;

import io.github.jhipster.application.service.dto.LandingDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link io.github.jhipster.application.domain.Landing}.
 */
public interface LandingService {

    /**
     * Save a landing.
     *
     * @param landingDTO the entity to save.
     * @return the persisted entity.
     */
    LandingDTO save(LandingDTO landingDTO);

    /**
     * Get all the landings.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<LandingDTO> findAll(Pageable pageable);


    /**
     * Get the "id" landing.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<LandingDTO> findOne(Long id);

    /**
     * Delete the "id" landing.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);

    /**
     * Search for the landing corresponding to the query.
     *
     * @param query the query of the search.
     * 
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<LandingDTO> search(String query, Pageable pageable);
}
