package io.github.jhipster.application.service;

import io.github.jhipster.application.service.dto.TipoRecursoDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link io.github.jhipster.application.domain.TipoRecurso}.
 */
public interface TipoRecursoService {

    /**
     * Save a tipoRecurso.
     *
     * @param tipoRecursoDTO the entity to save.
     * @return the persisted entity.
     */
    TipoRecursoDTO save(TipoRecursoDTO tipoRecursoDTO);

    /**
     * Get all the tipoRecursos.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<TipoRecursoDTO> findAll(Pageable pageable);


    /**
     * Get the "id" tipoRecurso.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<TipoRecursoDTO> findOne(Long id);

    /**
     * Delete the "id" tipoRecurso.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);

    /**
     * Search for the tipoRecurso corresponding to the query.
     *
     * @param query the query of the search.
     * 
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<TipoRecursoDTO> search(String query, Pageable pageable);
}
