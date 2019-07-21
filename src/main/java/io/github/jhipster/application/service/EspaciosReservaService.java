package io.github.jhipster.application.service;

import io.github.jhipster.application.service.dto.EspaciosReservaDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link io.github.jhipster.application.domain.EspaciosReserva}.
 */
public interface EspaciosReservaService {

    /**
     * Save a espaciosReserva.
     *
     * @param espaciosReservaDTO the entity to save.
     * @return the persisted entity.
     */
    EspaciosReservaDTO save(EspaciosReservaDTO espaciosReservaDTO);

    /**
     * Get all the espaciosReservas.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<EspaciosReservaDTO> findAll(Pageable pageable);


    /**
     * Get the "id" espaciosReserva.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<EspaciosReservaDTO> findOne(Long id);

    /**
     * Delete the "id" espaciosReserva.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);

    /**
     * Search for the espaciosReserva corresponding to the query.
     *
     * @param query the query of the search.
     * 
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<EspaciosReservaDTO> search(String query, Pageable pageable);
}
