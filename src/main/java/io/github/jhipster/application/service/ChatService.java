package io.github.jhipster.application.service;

import io.github.jhipster.application.service.dto.ChatDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link io.github.jhipster.application.domain.Chat}.
 */
public interface ChatService {

    /**
     * Save a chat.
     *
     * @param chatDTO the entity to save.
     * @return the persisted entity.
     */
    ChatDTO save(ChatDTO chatDTO);

    /**
     * Get all the chats.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<ChatDTO> findAll(Pageable pageable);


    /**
     * Get the "id" chat.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<ChatDTO> findOne(Long id);

    /**
     * Delete the "id" chat.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);

    /**
     * Search for the chat corresponding to the query.
     *
     * @param query the query of the search.
     * 
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<ChatDTO> search(String query, Pageable pageable);
}
