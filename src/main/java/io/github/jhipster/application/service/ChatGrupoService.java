package io.github.jhipster.application.service;

import io.github.jhipster.application.service.dto.ChatGrupoDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link io.github.jhipster.application.domain.ChatGrupo}.
 */
public interface ChatGrupoService {

    /**
     * Save a chatGrupo.
     *
     * @param chatGrupoDTO the entity to save.
     * @return the persisted entity.
     */
    ChatGrupoDTO save(ChatGrupoDTO chatGrupoDTO);

    /**
     * Get all the chatGrupos.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<ChatGrupoDTO> findAll(Pageable pageable);


    /**
     * Get the "id" chatGrupo.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<ChatGrupoDTO> findOne(Long id);

    /**
     * Delete the "id" chatGrupo.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);

    /**
     * Search for the chatGrupo corresponding to the query.
     *
     * @param query the query of the search.
     * 
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<ChatGrupoDTO> search(String query, Pageable pageable);
}
