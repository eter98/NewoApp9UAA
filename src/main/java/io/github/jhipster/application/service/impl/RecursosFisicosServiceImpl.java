package io.github.jhipster.application.service.impl;

import io.github.jhipster.application.service.RecursosFisicosService;
import io.github.jhipster.application.domain.RecursosFisicos;
import io.github.jhipster.application.repository.RecursosFisicosRepository;
import io.github.jhipster.application.repository.search.RecursosFisicosSearchRepository;
import io.github.jhipster.application.service.dto.RecursosFisicosDTO;
import io.github.jhipster.application.service.mapper.RecursosFisicosMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.elasticsearch.index.query.QueryBuilders.*;

/**
 * Service Implementation for managing {@link RecursosFisicos}.
 */
@Service
@Transactional
public class RecursosFisicosServiceImpl implements RecursosFisicosService {

    private final Logger log = LoggerFactory.getLogger(RecursosFisicosServiceImpl.class);

    private final RecursosFisicosRepository recursosFisicosRepository;

    private final RecursosFisicosMapper recursosFisicosMapper;

    private final RecursosFisicosSearchRepository recursosFisicosSearchRepository;

    public RecursosFisicosServiceImpl(RecursosFisicosRepository recursosFisicosRepository, RecursosFisicosMapper recursosFisicosMapper, RecursosFisicosSearchRepository recursosFisicosSearchRepository) {
        this.recursosFisicosRepository = recursosFisicosRepository;
        this.recursosFisicosMapper = recursosFisicosMapper;
        this.recursosFisicosSearchRepository = recursosFisicosSearchRepository;
    }

    /**
     * Save a recursosFisicos.
     *
     * @param recursosFisicosDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public RecursosFisicosDTO save(RecursosFisicosDTO recursosFisicosDTO) {
        log.debug("Request to save RecursosFisicos : {}", recursosFisicosDTO);
        RecursosFisicos recursosFisicos = recursosFisicosMapper.toEntity(recursosFisicosDTO);
        recursosFisicos = recursosFisicosRepository.save(recursosFisicos);
        RecursosFisicosDTO result = recursosFisicosMapper.toDto(recursosFisicos);
        recursosFisicosSearchRepository.save(recursosFisicos);
        return result;
    }

    /**
     * Get all the recursosFisicos.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<RecursosFisicosDTO> findAll(Pageable pageable) {
        log.debug("Request to get all RecursosFisicos");
        return recursosFisicosRepository.findAll(pageable)
            .map(recursosFisicosMapper::toDto);
    }


    /**
     * Get one recursosFisicos by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<RecursosFisicosDTO> findOne(Long id) {
        log.debug("Request to get RecursosFisicos : {}", id);
        return recursosFisicosRepository.findById(id)
            .map(recursosFisicosMapper::toDto);
    }

    /**
     * Delete the recursosFisicos by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete RecursosFisicos : {}", id);
        recursosFisicosRepository.deleteById(id);
        recursosFisicosSearchRepository.deleteById(id);
    }

    /**
     * Search for the recursosFisicos corresponding to the query.
     *
     * @param query the query of the search.
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<RecursosFisicosDTO> search(String query, Pageable pageable) {
        log.debug("Request to search for a page of RecursosFisicos for query {}", query);
        return recursosFisicosSearchRepository.search(queryStringQuery(query), pageable)
            .map(recursosFisicosMapper::toDto);
    }
}
