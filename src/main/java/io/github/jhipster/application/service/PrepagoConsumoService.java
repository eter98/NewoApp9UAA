package io.github.jhipster.application.service;

import io.github.jhipster.application.service.dto.PrepagoConsumoDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link io.github.jhipster.application.domain.PrepagoConsumo}.
 */
public interface PrepagoConsumoService {

    /**
     * Save a prepagoConsumo.
     *
     * @param prepagoConsumoDTO the entity to save.
     * @return the persisted entity.
     */
    PrepagoConsumoDTO save(PrepagoConsumoDTO prepagoConsumoDTO);

    /**
     * Get all the prepagoConsumos.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<PrepagoConsumoDTO> findAll(Pageable pageable);


    /**
     * Get the "id" prepagoConsumo.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<PrepagoConsumoDTO> findOne(Long id);

    /**
     * Delete the "id" prepagoConsumo.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);

    /**
     * Search for the prepagoConsumo corresponding to the query.
     *
     * @param query the query of the search.
     * 
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<PrepagoConsumoDTO> search(String query, Pageable pageable);
}
