package io.github.jhipster.application.service;

import io.github.jhipster.application.service.dto.TipoPrepagoConsumoDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link io.github.jhipster.application.domain.TipoPrepagoConsumo}.
 */
public interface TipoPrepagoConsumoService {

    /**
     * Save a tipoPrepagoConsumo.
     *
     * @param tipoPrepagoConsumoDTO the entity to save.
     * @return the persisted entity.
     */
    TipoPrepagoConsumoDTO save(TipoPrepagoConsumoDTO tipoPrepagoConsumoDTO);

    /**
     * Get all the tipoPrepagoConsumos.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<TipoPrepagoConsumoDTO> findAll(Pageable pageable);


    /**
     * Get the "id" tipoPrepagoConsumo.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<TipoPrepagoConsumoDTO> findOne(Long id);

    /**
     * Delete the "id" tipoPrepagoConsumo.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);

    /**
     * Search for the tipoPrepagoConsumo corresponding to the query.
     *
     * @param query the query of the search.
     * 
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<TipoPrepagoConsumoDTO> search(String query, Pageable pageable);
}
