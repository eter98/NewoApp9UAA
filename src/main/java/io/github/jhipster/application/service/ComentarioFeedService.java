package io.github.jhipster.application.service;

import io.github.jhipster.application.service.dto.ComentarioFeedDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link io.github.jhipster.application.domain.ComentarioFeed}.
 */
public interface ComentarioFeedService {

    /**
     * Save a comentarioFeed.
     *
     * @param comentarioFeedDTO the entity to save.
     * @return the persisted entity.
     */
    ComentarioFeedDTO save(ComentarioFeedDTO comentarioFeedDTO);

    /**
     * Get all the comentarioFeeds.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<ComentarioFeedDTO> findAll(Pageable pageable);


    /**
     * Get the "id" comentarioFeed.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<ComentarioFeedDTO> findOne(Long id);

    /**
     * Delete the "id" comentarioFeed.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);

    /**
     * Search for the comentarioFeed corresponding to the query.
     *
     * @param query the query of the search.
     * 
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<ComentarioFeedDTO> search(String query, Pageable pageable);
}
