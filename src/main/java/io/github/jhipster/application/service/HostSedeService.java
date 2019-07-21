package io.github.jhipster.application.service;

import io.github.jhipster.application.service.dto.HostSedeDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link io.github.jhipster.application.domain.HostSede}.
 */
public interface HostSedeService {

    /**
     * Save a hostSede.
     *
     * @param hostSedeDTO the entity to save.
     * @return the persisted entity.
     */
    HostSedeDTO save(HostSedeDTO hostSedeDTO);

    /**
     * Get all the hostSedes.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<HostSedeDTO> findAll(Pageable pageable);


    /**
     * Get the "id" hostSede.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<HostSedeDTO> findOne(Long id);

    /**
     * Delete the "id" hostSede.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);

    /**
     * Search for the hostSede corresponding to the query.
     *
     * @param query the query of the search.
     * 
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<HostSedeDTO> search(String query, Pageable pageable);
}
