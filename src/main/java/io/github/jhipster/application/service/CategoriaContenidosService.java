package io.github.jhipster.application.service;

import io.github.jhipster.application.service.dto.CategoriaContenidosDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link io.github.jhipster.application.domain.CategoriaContenidos}.
 */
public interface CategoriaContenidosService {

    /**
     * Save a categoriaContenidos.
     *
     * @param categoriaContenidosDTO the entity to save.
     * @return the persisted entity.
     */
    CategoriaContenidosDTO save(CategoriaContenidosDTO categoriaContenidosDTO);

    /**
     * Get all the categoriaContenidos.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<CategoriaContenidosDTO> findAll(Pageable pageable);


    /**
     * Get the "id" categoriaContenidos.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<CategoriaContenidosDTO> findOne(Long id);

    /**
     * Delete the "id" categoriaContenidos.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);

    /**
     * Search for the categoriaContenidos corresponding to the query.
     *
     * @param query the query of the search.
     * 
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<CategoriaContenidosDTO> search(String query, Pageable pageable);
}
