package io.github.jhipster.application.service;

import io.github.jhipster.application.service.dto.MiembrosDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link io.github.jhipster.application.domain.Miembros}.
 */
public interface MiembrosService {

    /**
     * Save a miembros.
     *
     * @param miembrosDTO the entity to save.
     * @return the persisted entity.
     */
    MiembrosDTO save(MiembrosDTO miembrosDTO);

    /**
     * Get all the miembros.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<MiembrosDTO> findAll(Pageable pageable);


    /**
     * Get the "id" miembros.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<MiembrosDTO> findOne(Long id);

    /**
     * Delete the "id" miembros.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);

    /**
     * Search for the miembros corresponding to the query.
     *
     * @param query the query of the search.
     * 
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<MiembrosDTO> search(String query, Pageable pageable);
}
