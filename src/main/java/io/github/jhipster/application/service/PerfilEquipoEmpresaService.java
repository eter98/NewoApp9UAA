package io.github.jhipster.application.service;

import io.github.jhipster.application.service.dto.PerfilEquipoEmpresaDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link io.github.jhipster.application.domain.PerfilEquipoEmpresa}.
 */
public interface PerfilEquipoEmpresaService {

    /**
     * Save a perfilEquipoEmpresa.
     *
     * @param perfilEquipoEmpresaDTO the entity to save.
     * @return the persisted entity.
     */
    PerfilEquipoEmpresaDTO save(PerfilEquipoEmpresaDTO perfilEquipoEmpresaDTO);

    /**
     * Get all the perfilEquipoEmpresas.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<PerfilEquipoEmpresaDTO> findAll(Pageable pageable);


    /**
     * Get the "id" perfilEquipoEmpresa.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<PerfilEquipoEmpresaDTO> findOne(Long id);

    /**
     * Delete the "id" perfilEquipoEmpresa.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);

    /**
     * Search for the perfilEquipoEmpresa corresponding to the query.
     *
     * @param query the query of the search.
     * 
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<PerfilEquipoEmpresaDTO> search(String query, Pageable pageable);
}
