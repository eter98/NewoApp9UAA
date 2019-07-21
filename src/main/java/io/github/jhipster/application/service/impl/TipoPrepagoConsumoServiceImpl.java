package io.github.jhipster.application.service.impl;

import io.github.jhipster.application.service.TipoPrepagoConsumoService;
import io.github.jhipster.application.domain.TipoPrepagoConsumo;
import io.github.jhipster.application.repository.TipoPrepagoConsumoRepository;
import io.github.jhipster.application.repository.search.TipoPrepagoConsumoSearchRepository;
import io.github.jhipster.application.service.dto.TipoPrepagoConsumoDTO;
import io.github.jhipster.application.service.mapper.TipoPrepagoConsumoMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.elasticsearch.index.query.QueryBuilders.*;

/**
 * Service Implementation for managing {@link TipoPrepagoConsumo}.
 */
@Service
@Transactional
public class TipoPrepagoConsumoServiceImpl implements TipoPrepagoConsumoService {

    private final Logger log = LoggerFactory.getLogger(TipoPrepagoConsumoServiceImpl.class);

    private final TipoPrepagoConsumoRepository tipoPrepagoConsumoRepository;

    private final TipoPrepagoConsumoMapper tipoPrepagoConsumoMapper;

    private final TipoPrepagoConsumoSearchRepository tipoPrepagoConsumoSearchRepository;

    public TipoPrepagoConsumoServiceImpl(TipoPrepagoConsumoRepository tipoPrepagoConsumoRepository, TipoPrepagoConsumoMapper tipoPrepagoConsumoMapper, TipoPrepagoConsumoSearchRepository tipoPrepagoConsumoSearchRepository) {
        this.tipoPrepagoConsumoRepository = tipoPrepagoConsumoRepository;
        this.tipoPrepagoConsumoMapper = tipoPrepagoConsumoMapper;
        this.tipoPrepagoConsumoSearchRepository = tipoPrepagoConsumoSearchRepository;
    }

    /**
     * Save a tipoPrepagoConsumo.
     *
     * @param tipoPrepagoConsumoDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public TipoPrepagoConsumoDTO save(TipoPrepagoConsumoDTO tipoPrepagoConsumoDTO) {
        log.debug("Request to save TipoPrepagoConsumo : {}", tipoPrepagoConsumoDTO);
        TipoPrepagoConsumo tipoPrepagoConsumo = tipoPrepagoConsumoMapper.toEntity(tipoPrepagoConsumoDTO);
        tipoPrepagoConsumo = tipoPrepagoConsumoRepository.save(tipoPrepagoConsumo);
        TipoPrepagoConsumoDTO result = tipoPrepagoConsumoMapper.toDto(tipoPrepagoConsumo);
        tipoPrepagoConsumoSearchRepository.save(tipoPrepagoConsumo);
        return result;
    }

    /**
     * Get all the tipoPrepagoConsumos.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<TipoPrepagoConsumoDTO> findAll(Pageable pageable) {
        log.debug("Request to get all TipoPrepagoConsumos");
        return tipoPrepagoConsumoRepository.findAll(pageable)
            .map(tipoPrepagoConsumoMapper::toDto);
    }


    /**
     * Get one tipoPrepagoConsumo by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<TipoPrepagoConsumoDTO> findOne(Long id) {
        log.debug("Request to get TipoPrepagoConsumo : {}", id);
        return tipoPrepagoConsumoRepository.findById(id)
            .map(tipoPrepagoConsumoMapper::toDto);
    }

    /**
     * Delete the tipoPrepagoConsumo by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete TipoPrepagoConsumo : {}", id);
        tipoPrepagoConsumoRepository.deleteById(id);
        tipoPrepagoConsumoSearchRepository.deleteById(id);
    }

    /**
     * Search for the tipoPrepagoConsumo corresponding to the query.
     *
     * @param query the query of the search.
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<TipoPrepagoConsumoDTO> search(String query, Pageable pageable) {
        log.debug("Request to search for a page of TipoPrepagoConsumos for query {}", query);
        return tipoPrepagoConsumoSearchRepository.search(queryStringQuery(query), pageable)
            .map(tipoPrepagoConsumoMapper::toDto);
    }
}
