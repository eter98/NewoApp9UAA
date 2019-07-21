package io.github.jhipster.application.service;

import io.github.jhipster.application.service.dto.MiembrosEquipoEmpresasDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link io.github.jhipster.application.domain.MiembrosEquipoEmpresas}.
 */
public interface MiembrosEquipoEmpresasService {

    /**
     * Save a miembrosEquipoEmpresas.
     *
     * @param miembrosEquipoEmpresasDTO the entity to save.
     * @return the persisted entity.
     */
    MiembrosEquipoEmpresasDTO save(MiembrosEquipoEmpresasDTO miembrosEquipoEmpresasDTO);

    /**
     * Get all the miembrosEquipoEmpresas.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<MiembrosEquipoEmpresasDTO> findAll(Pageable pageable);


    /**
     * Get the "id" miembrosEquipoEmpresas.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<MiembrosEquipoEmpresasDTO> findOne(Long id);

    /**
     * Delete the "id" miembrosEquipoEmpresas.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);

    /**
     * Search for the miembrosEquipoEmpresas corresponding to the query.
     *
     * @param query the query of the search.
     * 
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<MiembrosEquipoEmpresasDTO> search(String query, Pageable pageable);
}
