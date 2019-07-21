package io.github.jhipster.application.service.impl;

import io.github.jhipster.application.service.GruposService;
import io.github.jhipster.application.domain.Grupos;
import io.github.jhipster.application.repository.GruposRepository;
import io.github.jhipster.application.repository.search.GruposSearchRepository;
import io.github.jhipster.application.service.dto.GruposDTO;
import io.github.jhipster.application.service.mapper.GruposMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.elasticsearch.index.query.QueryBuilders.*;

/**
 * Service Implementation for managing {@link Grupos}.
 */
@Service
@Transactional
public class GruposServiceImpl implements GruposService {

    private final Logger log = LoggerFactory.getLogger(GruposServiceImpl.class);

    private final GruposRepository gruposRepository;

    private final GruposMapper gruposMapper;

    private final GruposSearchRepository gruposSearchRepository;

    public GruposServiceImpl(GruposRepository gruposRepository, GruposMapper gruposMapper, GruposSearchRepository gruposSearchRepository) {
        this.gruposRepository = gruposRepository;
        this.gruposMapper = gruposMapper;
        this.gruposSearchRepository = gruposSearchRepository;
    }

    /**
     * Save a grupos.
     *
     * @param gruposDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public GruposDTO save(GruposDTO gruposDTO) {
        log.debug("Request to save Grupos : {}", gruposDTO);
        Grupos grupos = gruposMapper.toEntity(gruposDTO);
        grupos = gruposRepository.save(grupos);
        GruposDTO result = gruposMapper.toDto(grupos);
        gruposSearchRepository.save(grupos);
        return result;
    }

    /**
     * Get all the grupos.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<GruposDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Grupos");
        return gruposRepository.findAll(pageable)
            .map(gruposMapper::toDto);
    }


    /**
     * Get one grupos by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<GruposDTO> findOne(Long id) {
        log.debug("Request to get Grupos : {}", id);
        return gruposRepository.findById(id)
            .map(gruposMapper::toDto);
    }

    /**
     * Delete the grupos by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Grupos : {}", id);
        gruposRepository.deleteById(id);
        gruposSearchRepository.deleteById(id);
    }

    /**
     * Search for the grupos corresponding to the query.
     *
     * @param query the query of the search.
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<GruposDTO> search(String query, Pageable pageable) {
        log.debug("Request to search for a page of Grupos for query {}", query);
        return gruposSearchRepository.search(queryStringQuery(query), pageable)
            .map(gruposMapper::toDto);
    }
}
