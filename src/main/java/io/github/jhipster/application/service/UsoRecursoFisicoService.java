package io.github.jhipster.application.service;

import io.github.jhipster.application.service.dto.UsoRecursoFisicoDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link io.github.jhipster.application.domain.UsoRecursoFisico}.
 */
public interface UsoRecursoFisicoService {

    /**
     * Save a usoRecursoFisico.
     *
     * @param usoRecursoFisicoDTO the entity to save.
     * @return the persisted entity.
     */
    UsoRecursoFisicoDTO save(UsoRecursoFisicoDTO usoRecursoFisicoDTO);

    /**
     * Get all the usoRecursoFisicos.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<UsoRecursoFisicoDTO> findAll(Pageable pageable);


    /**
     * Get the "id" usoRecursoFisico.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<UsoRecursoFisicoDTO> findOne(Long id);

    /**
     * Delete the "id" usoRecursoFisico.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);

    /**
     * Search for the usoRecursoFisico corresponding to the query.
     *
     * @param query the query of the search.
     * 
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<UsoRecursoFisicoDTO> search(String query, Pageable pageable);
}
