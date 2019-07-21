package io.github.jhipster.application.service;

import io.github.jhipster.application.service.dto.ConsumoMarketDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link io.github.jhipster.application.domain.ConsumoMarket}.
 */
public interface ConsumoMarketService {

    /**
     * Save a consumoMarket.
     *
     * @param consumoMarketDTO the entity to save.
     * @return the persisted entity.
     */
    ConsumoMarketDTO save(ConsumoMarketDTO consumoMarketDTO);

    /**
     * Get all the consumoMarkets.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<ConsumoMarketDTO> findAll(Pageable pageable);


    /**
     * Get the "id" consumoMarket.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<ConsumoMarketDTO> findOne(Long id);

    /**
     * Delete the "id" consumoMarket.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);

    /**
     * Search for the consumoMarket corresponding to the query.
     *
     * @param query the query of the search.
     * 
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<ConsumoMarketDTO> search(String query, Pageable pageable);
}
