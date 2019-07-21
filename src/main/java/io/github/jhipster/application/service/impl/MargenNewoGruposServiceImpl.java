package io.github.jhipster.application.service.impl;

import io.github.jhipster.application.service.MargenNewoGruposService;
import io.github.jhipster.application.domain.MargenNewoGrupos;
import io.github.jhipster.application.repository.MargenNewoGruposRepository;
import io.github.jhipster.application.repository.search.MargenNewoGruposSearchRepository;
import io.github.jhipster.application.service.dto.MargenNewoGruposDTO;
import io.github.jhipster.application.service.mapper.MargenNewoGruposMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.elasticsearch.index.query.QueryBuilders.*;

/**
 * Service Implementation for managing {@link MargenNewoGrupos}.
 */
@Service
@Transactional
public class MargenNewoGruposServiceImpl implements MargenNewoGruposService {

    private final Logger log = LoggerFactory.getLogger(MargenNewoGruposServiceImpl.class);

    private final MargenNewoGruposRepository margenNewoGruposRepository;

    private final MargenNewoGruposMapper margenNewoGruposMapper;

    private final MargenNewoGruposSearchRepository margenNewoGruposSearchRepository;

    public MargenNewoGruposServiceImpl(MargenNewoGruposRepository margenNewoGruposRepository, MargenNewoGruposMapper margenNewoGruposMapper, MargenNewoGruposSearchRepository margenNewoGruposSearchRepository) {
        this.margenNewoGruposRepository = margenNewoGruposRepository;
        this.margenNewoGruposMapper = margenNewoGruposMapper;
        this.margenNewoGruposSearchRepository = margenNewoGruposSearchRepository;
    }

    /**
     * Save a margenNewoGrupos.
     *
     * @param margenNewoGruposDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public MargenNewoGruposDTO save(MargenNewoGruposDTO margenNewoGruposDTO) {
        log.debug("Request to save MargenNewoGrupos : {}", margenNewoGruposDTO);
        MargenNewoGrupos margenNewoGrupos = margenNewoGruposMapper.toEntity(margenNewoGruposDTO);
        margenNewoGrupos = margenNewoGruposRepository.save(margenNewoGrupos);
        MargenNewoGruposDTO result = margenNewoGruposMapper.toDto(margenNewoGrupos);
        margenNewoGruposSearchRepository.save(margenNewoGrupos);
        return result;
    }

    /**
     * Get all the margenNewoGrupos.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<MargenNewoGruposDTO> findAll(Pageable pageable) {
        log.debug("Request to get all MargenNewoGrupos");
        return margenNewoGruposRepository.findAll(pageable)
            .map(margenNewoGruposMapper::toDto);
    }


    /**
     * Get one margenNewoGrupos by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<MargenNewoGruposDTO> findOne(Long id) {
        log.debug("Request to get MargenNewoGrupos : {}", id);
        return margenNewoGruposRepository.findById(id)
            .map(margenNewoGruposMapper::toDto);
    }

    /**
     * Delete the margenNewoGrupos by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete MargenNewoGrupos : {}", id);
        margenNewoGruposRepository.deleteById(id);
        margenNewoGruposSearchRepository.deleteById(id);
    }

    /**
     * Search for the margenNewoGrupos corresponding to the query.
     *
     * @param query the query of the search.
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<MargenNewoGruposDTO> search(String query, Pageable pageable) {
        log.debug("Request to search for a page of MargenNewoGrupos for query {}", query);
        return margenNewoGruposSearchRepository.search(queryStringQuery(query), pageable)
            .map(margenNewoGruposMapper::toDto);
    }
}
