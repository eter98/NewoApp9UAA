package io.github.jhipster.application.service;

import io.github.jhipster.application.service.dto.EspacioLibreDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link io.github.jhipster.application.domain.EspacioLibre}.
 */
public interface EspacioLibreService {

    /**
     * Save a espacioLibre.
     *
     * @param espacioLibreDTO the entity to save.
     * @return the persisted entity.
     */
    EspacioLibreDTO save(EspacioLibreDTO espacioLibreDTO);

    /**
     * Get all the espacioLibres.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<EspacioLibreDTO> findAll(Pageable pageable);


    /**
     * Get the "id" espacioLibre.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<EspacioLibreDTO> findOne(Long id);

    /**
     * Delete the "id" espacioLibre.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);

    /**
     * Search for the espacioLibre corresponding to the query.
     *
     * @param query the query of the search.
     * 
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<EspacioLibreDTO> search(String query, Pageable pageable);
}
