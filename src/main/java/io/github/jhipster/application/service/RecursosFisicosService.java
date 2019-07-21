package io.github.jhipster.application.service;

import io.github.jhipster.application.service.dto.RecursosFisicosDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link io.github.jhipster.application.domain.RecursosFisicos}.
 */
public interface RecursosFisicosService {

    /**
     * Save a recursosFisicos.
     *
     * @param recursosFisicosDTO the entity to save.
     * @return the persisted entity.
     */
    RecursosFisicosDTO save(RecursosFisicosDTO recursosFisicosDTO);

    /**
     * Get all the recursosFisicos.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<RecursosFisicosDTO> findAll(Pageable pageable);


    /**
     * Get the "id" recursosFisicos.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<RecursosFisicosDTO> findOne(Long id);

    /**
     * Delete the "id" recursosFisicos.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);

    /**
     * Search for the recursosFisicos corresponding to the query.
     *
     * @param query the query of the search.
     * 
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<RecursosFisicosDTO> search(String query, Pageable pageable);
}
