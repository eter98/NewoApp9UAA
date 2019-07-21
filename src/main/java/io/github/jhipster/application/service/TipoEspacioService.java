package io.github.jhipster.application.service;

import io.github.jhipster.application.service.dto.TipoEspacioDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link io.github.jhipster.application.domain.TipoEspacio}.
 */
public interface TipoEspacioService {

    /**
     * Save a tipoEspacio.
     *
     * @param tipoEspacioDTO the entity to save.
     * @return the persisted entity.
     */
    TipoEspacioDTO save(TipoEspacioDTO tipoEspacioDTO);

    /**
     * Get all the tipoEspacios.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<TipoEspacioDTO> findAll(Pageable pageable);


    /**
     * Get the "id" tipoEspacio.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<TipoEspacioDTO> findOne(Long id);

    /**
     * Delete the "id" tipoEspacio.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);

    /**
     * Search for the tipoEspacio corresponding to the query.
     *
     * @param query the query of the search.
     * 
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<TipoEspacioDTO> search(String query, Pageable pageable);
}
