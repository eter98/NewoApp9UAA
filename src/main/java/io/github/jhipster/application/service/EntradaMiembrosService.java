package io.github.jhipster.application.service;

import io.github.jhipster.application.service.dto.EntradaMiembrosDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link io.github.jhipster.application.domain.EntradaMiembros}.
 */
public interface EntradaMiembrosService {

    /**
     * Save a entradaMiembros.
     *
     * @param entradaMiembrosDTO the entity to save.
     * @return the persisted entity.
     */
    EntradaMiembrosDTO save(EntradaMiembrosDTO entradaMiembrosDTO);

    /**
     * Get all the entradaMiembros.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<EntradaMiembrosDTO> findAll(Pageable pageable);


    /**
     * Get the "id" entradaMiembros.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<EntradaMiembrosDTO> findOne(Long id);

    /**
     * Delete the "id" entradaMiembros.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);

    /**
     * Search for the entradaMiembros corresponding to the query.
     *
     * @param query the query of the search.
     * 
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<EntradaMiembrosDTO> search(String query, Pageable pageable);
}
