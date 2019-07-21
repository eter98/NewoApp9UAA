package io.github.jhipster.application.service;

import io.github.jhipster.application.service.dto.FacturacionDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link io.github.jhipster.application.domain.Facturacion}.
 */
public interface FacturacionService {

    /**
     * Save a facturacion.
     *
     * @param facturacionDTO the entity to save.
     * @return the persisted entity.
     */
    FacturacionDTO save(FacturacionDTO facturacionDTO);

    /**
     * Get all the facturacions.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<FacturacionDTO> findAll(Pageable pageable);


    /**
     * Get the "id" facturacion.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<FacturacionDTO> findOne(Long id);

    /**
     * Delete the "id" facturacion.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);

    /**
     * Search for the facturacion corresponding to the query.
     *
     * @param query the query of the search.
     * 
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<FacturacionDTO> search(String query, Pageable pageable);
}
