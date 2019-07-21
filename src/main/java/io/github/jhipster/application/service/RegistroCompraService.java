package io.github.jhipster.application.service;

import io.github.jhipster.application.service.dto.RegistroCompraDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link io.github.jhipster.application.domain.RegistroCompra}.
 */
public interface RegistroCompraService {

    /**
     * Save a registroCompra.
     *
     * @param registroCompraDTO the entity to save.
     * @return the persisted entity.
     */
    RegistroCompraDTO save(RegistroCompraDTO registroCompraDTO);

    /**
     * Get all the registroCompras.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<RegistroCompraDTO> findAll(Pageable pageable);


    /**
     * Get the "id" registroCompra.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<RegistroCompraDTO> findOne(Long id);

    /**
     * Delete the "id" registroCompra.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);

    /**
     * Search for the registroCompra corresponding to the query.
     *
     * @param query the query of the search.
     * 
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<RegistroCompraDTO> search(String query, Pageable pageable);
}
