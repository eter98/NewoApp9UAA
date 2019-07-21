package io.github.jhipster.application.service.impl;

import io.github.jhipster.application.service.MargenNewoEventosService;
import io.github.jhipster.application.domain.MargenNewoEventos;
import io.github.jhipster.application.repository.MargenNewoEventosRepository;
import io.github.jhipster.application.repository.search.MargenNewoEventosSearchRepository;
import io.github.jhipster.application.service.dto.MargenNewoEventosDTO;
import io.github.jhipster.application.service.mapper.MargenNewoEventosMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.elasticsearch.index.query.QueryBuilders.*;

/**
 * Service Implementation for managing {@link MargenNewoEventos}.
 */
@Service
@Transactional
public class MargenNewoEventosServiceImpl implements MargenNewoEventosService {

    private final Logger log = LoggerFactory.getLogger(MargenNewoEventosServiceImpl.class);

    private final MargenNewoEventosRepository margenNewoEventosRepository;

    private final MargenNewoEventosMapper margenNewoEventosMapper;

    private final MargenNewoEventosSearchRepository margenNewoEventosSearchRepository;

    public MargenNewoEventosServiceImpl(MargenNewoEventosRepository margenNewoEventosRepository, MargenNewoEventosMapper margenNewoEventosMapper, MargenNewoEventosSearchRepository margenNewoEventosSearchRepository) {
        this.margenNewoEventosRepository = margenNewoEventosRepository;
        this.margenNewoEventosMapper = margenNewoEventosMapper;
        this.margenNewoEventosSearchRepository = margenNewoEventosSearchRepository;
    }

    /**
     * Save a margenNewoEventos.
     *
     * @param margenNewoEventosDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public MargenNewoEventosDTO save(MargenNewoEventosDTO margenNewoEventosDTO) {
        log.debug("Request to save MargenNewoEventos : {}", margenNewoEventosDTO);
        MargenNewoEventos margenNewoEventos = margenNewoEventosMapper.toEntity(margenNewoEventosDTO);
        margenNewoEventos = margenNewoEventosRepository.save(margenNewoEventos);
        MargenNewoEventosDTO result = margenNewoEventosMapper.toDto(margenNewoEventos);
        margenNewoEventosSearchRepository.save(margenNewoEventos);
        return result;
    }

    /**
     * Get all the margenNewoEventos.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<MargenNewoEventosDTO> findAll(Pageable pageable) {
        log.debug("Request to get all MargenNewoEventos");
        return margenNewoEventosRepository.findAll(pageable)
            .map(margenNewoEventosMapper::toDto);
    }


    /**
     * Get one margenNewoEventos by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<MargenNewoEventosDTO> findOne(Long id) {
        log.debug("Request to get MargenNewoEventos : {}", id);
        return margenNewoEventosRepository.findById(id)
            .map(margenNewoEventosMapper::toDto);
    }

    /**
     * Delete the margenNewoEventos by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete MargenNewoEventos : {}", id);
        margenNewoEventosRepository.deleteById(id);
        margenNewoEventosSearchRepository.deleteById(id);
    }

    /**
     * Search for the margenNewoEventos corresponding to the query.
     *
     * @param query the query of the search.
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<MargenNewoEventosDTO> search(String query, Pageable pageable) {
        log.debug("Request to search for a page of MargenNewoEventos for query {}", query);
        return margenNewoEventosSearchRepository.search(queryStringQuery(query), pageable)
            .map(margenNewoEventosMapper::toDto);
    }
}
