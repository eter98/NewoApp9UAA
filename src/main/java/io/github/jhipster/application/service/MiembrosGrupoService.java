package io.github.jhipster.application.service;

import io.github.jhipster.application.service.dto.MiembrosGrupoDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link io.github.jhipster.application.domain.MiembrosGrupo}.
 */
public interface MiembrosGrupoService {

    /**
     * Save a miembrosGrupo.
     *
     * @param miembrosGrupoDTO the entity to save.
     * @return the persisted entity.
     */
    MiembrosGrupoDTO save(MiembrosGrupoDTO miembrosGrupoDTO);

    /**
     * Get all the miembrosGrupos.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<MiembrosGrupoDTO> findAll(Pageable pageable);


    /**
     * Get the "id" miembrosGrupo.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<MiembrosGrupoDTO> findOne(Long id);

    /**
     * Delete the "id" miembrosGrupo.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);

    /**
     * Search for the miembrosGrupo corresponding to the query.
     *
     * @param query the query of the search.
     * 
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<MiembrosGrupoDTO> search(String query, Pageable pageable);
}
