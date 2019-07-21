package io.github.jhipster.application.service;

import io.github.jhipster.application.service.dto.EquipoEmpresasDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link io.github.jhipster.application.domain.EquipoEmpresas}.
 */
public interface EquipoEmpresasService {

    /**
     * Save a equipoEmpresas.
     *
     * @param equipoEmpresasDTO the entity to save.
     * @return the persisted entity.
     */
    EquipoEmpresasDTO save(EquipoEmpresasDTO equipoEmpresasDTO);

    /**
     * Get all the equipoEmpresas.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<EquipoEmpresasDTO> findAll(Pageable pageable);


    /**
     * Get the "id" equipoEmpresas.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<EquipoEmpresasDTO> findOne(Long id);

    /**
     * Delete the "id" equipoEmpresas.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);

    /**
     * Search for the equipoEmpresas corresponding to the query.
     *
     * @param query the query of the search.
     * 
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<EquipoEmpresasDTO> search(String query, Pageable pageable);
}
