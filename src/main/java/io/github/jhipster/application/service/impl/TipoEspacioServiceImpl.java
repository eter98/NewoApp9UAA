package io.github.jhipster.application.service.impl;

import io.github.jhipster.application.service.TipoEspacioService;
import io.github.jhipster.application.domain.TipoEspacio;
import io.github.jhipster.application.repository.TipoEspacioRepository;
import io.github.jhipster.application.repository.search.TipoEspacioSearchRepository;
import io.github.jhipster.application.service.dto.TipoEspacioDTO;
import io.github.jhipster.application.service.mapper.TipoEspacioMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.elasticsearch.index.query.QueryBuilders.*;

/**
 * Service Implementation for managing {@link TipoEspacio}.
 */
@Service
@Transactional
public class TipoEspacioServiceImpl implements TipoEspacioService {

    private final Logger log = LoggerFactory.getLogger(TipoEspacioServiceImpl.class);

    private final TipoEspacioRepository tipoEspacioRepository;

    private final TipoEspacioMapper tipoEspacioMapper;

    private final TipoEspacioSearchRepository tipoEspacioSearchRepository;

    public TipoEspacioServiceImpl(TipoEspacioRepository tipoEspacioRepository, TipoEspacioMapper tipoEspacioMapper, TipoEspacioSearchRepository tipoEspacioSearchRepository) {
        this.tipoEspacioRepository = tipoEspacioRepository;
        this.tipoEspacioMapper = tipoEspacioMapper;
        this.tipoEspacioSearchRepository = tipoEspacioSearchRepository;
    }

    /**
     * Save a tipoEspacio.
     *
     * @param tipoEspacioDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public TipoEspacioDTO save(TipoEspacioDTO tipoEspacioDTO) {
        log.debug("Request to save TipoEspacio : {}", tipoEspacioDTO);
        TipoEspacio tipoEspacio = tipoEspacioMapper.toEntity(tipoEspacioDTO);
        tipoEspacio = tipoEspacioRepository.save(tipoEspacio);
        TipoEspacioDTO result = tipoEspacioMapper.toDto(tipoEspacio);
        tipoEspacioSearchRepository.save(tipoEspacio);
        return result;
    }

    /**
     * Get all the tipoEspacios.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<TipoEspacioDTO> findAll(Pageable pageable) {
        log.debug("Request to get all TipoEspacios");
        return tipoEspacioRepository.findAll(pageable)
            .map(tipoEspacioMapper::toDto);
    }


    /**
     * Get one tipoEspacio by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<TipoEspacioDTO> findOne(Long id) {
        log.debug("Request to get TipoEspacio : {}", id);
        return tipoEspacioRepository.findById(id)
            .map(tipoEspacioMapper::toDto);
    }

    /**
     * Delete the tipoEspacio by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete TipoEspacio : {}", id);
        tipoEspacioRepository.deleteById(id);
        tipoEspacioSearchRepository.deleteById(id);
    }

    /**
     * Search for the tipoEspacio corresponding to the query.
     *
     * @param query the query of the search.
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<TipoEspacioDTO> search(String query, Pageable pageable) {
        log.debug("Request to search for a page of TipoEspacios for query {}", query);
        return tipoEspacioSearchRepository.search(queryStringQuery(query), pageable)
            .map(tipoEspacioMapper::toDto);
    }
}
