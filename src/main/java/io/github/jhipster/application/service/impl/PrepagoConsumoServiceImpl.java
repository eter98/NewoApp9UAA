package io.github.jhipster.application.service.impl;

import io.github.jhipster.application.service.PrepagoConsumoService;
import io.github.jhipster.application.domain.PrepagoConsumo;
import io.github.jhipster.application.repository.PrepagoConsumoRepository;
import io.github.jhipster.application.repository.search.PrepagoConsumoSearchRepository;
import io.github.jhipster.application.service.dto.PrepagoConsumoDTO;
import io.github.jhipster.application.service.mapper.PrepagoConsumoMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.elasticsearch.index.query.QueryBuilders.*;

/**
 * Service Implementation for managing {@link PrepagoConsumo}.
 */
@Service
@Transactional
public class PrepagoConsumoServiceImpl implements PrepagoConsumoService {

    private final Logger log = LoggerFactory.getLogger(PrepagoConsumoServiceImpl.class);

    private final PrepagoConsumoRepository prepagoConsumoRepository;

    private final PrepagoConsumoMapper prepagoConsumoMapper;

    private final PrepagoConsumoSearchRepository prepagoConsumoSearchRepository;

    public PrepagoConsumoServiceImpl(PrepagoConsumoRepository prepagoConsumoRepository, PrepagoConsumoMapper prepagoConsumoMapper, PrepagoConsumoSearchRepository prepagoConsumoSearchRepository) {
        this.prepagoConsumoRepository = prepagoConsumoRepository;
        this.prepagoConsumoMapper = prepagoConsumoMapper;
        this.prepagoConsumoSearchRepository = prepagoConsumoSearchRepository;
    }

    /**
     * Save a prepagoConsumo.
     *
     * @param prepagoConsumoDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public PrepagoConsumoDTO save(PrepagoConsumoDTO prepagoConsumoDTO) {
        log.debug("Request to save PrepagoConsumo : {}", prepagoConsumoDTO);
        PrepagoConsumo prepagoConsumo = prepagoConsumoMapper.toEntity(prepagoConsumoDTO);
        prepagoConsumo = prepagoConsumoRepository.save(prepagoConsumo);
        PrepagoConsumoDTO result = prepagoConsumoMapper.toDto(prepagoConsumo);
        prepagoConsumoSearchRepository.save(prepagoConsumo);
        return result;
    }

    /**
     * Get all the prepagoConsumos.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<PrepagoConsumoDTO> findAll(Pageable pageable) {
        log.debug("Request to get all PrepagoConsumos");
        return prepagoConsumoRepository.findAll(pageable)
            .map(prepagoConsumoMapper::toDto);
    }


    /**
     * Get one prepagoConsumo by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<PrepagoConsumoDTO> findOne(Long id) {
        log.debug("Request to get PrepagoConsumo : {}", id);
        return prepagoConsumoRepository.findById(id)
            .map(prepagoConsumoMapper::toDto);
    }

    /**
     * Delete the prepagoConsumo by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete PrepagoConsumo : {}", id);
        prepagoConsumoRepository.deleteById(id);
        prepagoConsumoSearchRepository.deleteById(id);
    }

    /**
     * Search for the prepagoConsumo corresponding to the query.
     *
     * @param query the query of the search.
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<PrepagoConsumoDTO> search(String query, Pageable pageable) {
        log.debug("Request to search for a page of PrepagoConsumos for query {}", query);
        return prepagoConsumoSearchRepository.search(queryStringQuery(query), pageable)
            .map(prepagoConsumoMapper::toDto);
    }
}
