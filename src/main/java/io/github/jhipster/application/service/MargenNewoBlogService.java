package io.github.jhipster.application.service;

import io.github.jhipster.application.service.dto.MargenNewoBlogDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link io.github.jhipster.application.domain.MargenNewoBlog}.
 */
public interface MargenNewoBlogService {

    /**
     * Save a margenNewoBlog.
     *
     * @param margenNewoBlogDTO the entity to save.
     * @return the persisted entity.
     */
    MargenNewoBlogDTO save(MargenNewoBlogDTO margenNewoBlogDTO);

    /**
     * Get all the margenNewoBlogs.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<MargenNewoBlogDTO> findAll(Pageable pageable);


    /**
     * Get the "id" margenNewoBlog.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<MargenNewoBlogDTO> findOne(Long id);

    /**
     * Delete the "id" margenNewoBlog.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);

    /**
     * Search for the margenNewoBlog corresponding to the query.
     *
     * @param query the query of the search.
     * 
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<MargenNewoBlogDTO> search(String query, Pageable pageable);
}
