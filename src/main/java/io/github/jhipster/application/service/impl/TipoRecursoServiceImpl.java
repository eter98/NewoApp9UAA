package io.github.jhipster.application.service.impl;

import io.github.jhipster.application.service.TipoRecursoService;
import io.github.jhipster.application.domain.TipoRecurso;
import io.github.jhipster.application.repository.TipoRecursoRepository;
import io.github.jhipster.application.repository.search.TipoRecursoSearchRepository;
import io.github.jhipster.application.service.dto.TipoRecursoDTO;
import io.github.jhipster.application.service.mapper.TipoRecursoMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.elasticsearch.index.query.QueryBuilders.*;

/**
 * Service Implementation for managing {@link TipoRecurso}.
 */
@Service
@Transactional
public class TipoRecursoServiceImpl implements TipoRecursoService {

    private final Logger log = LoggerFactory.getLogger(TipoRecursoServiceImpl.class);

    private final TipoRecursoRepository tipoRecursoRepository;

    private final TipoRecursoMapper tipoRecursoMapper;

    private final TipoRecursoSearchRepository tipoRecursoSearchRepository;

    public TipoRecursoServiceImpl(TipoRecursoRepository tipoRecursoRepository, TipoRecursoMapper tipoRecursoMapper, TipoRecursoSearchRepository tipoRecursoSearchRepository) {
        this.tipoRecursoRepository = tipoRecursoRepository;
        this.tipoRecursoMapper = tipoRecursoMapper;
        this.tipoRecursoSearchRepository = tipoRecursoSearchRepository;
    }

    /**
     * Save a tipoRecurso.
     *
     * @param tipoRecursoDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public TipoRecursoDTO save(TipoRecursoDTO tipoRecursoDTO) {
        log.debug("Request to save TipoRecurso : {}", tipoRecursoDTO);
        TipoRecurso tipoRecurso = tipoRecursoMapper.toEntity(tipoRecursoDTO);
        tipoRecurso = tipoRecursoRepository.save(tipoRecurso);
        TipoRecursoDTO result = tipoRecursoMapper.toDto(tipoRecurso);
        tipoRecursoSearchRepository.save(tipoRecurso);
        return result;
    }

    /**
     * Get all the tipoRecursos.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<TipoRecursoDTO> findAll(Pageable pageable) {
        log.debug("Request to get all TipoRecursos");
        return tipoRecursoRepository.findAll(pageable)
            .map(tipoRecursoMapper::toDto);
    }


    /**
     * Get one tipoRecurso by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<TipoRecursoDTO> findOne(Long id) {
        log.debug("Request to get TipoRecurso : {}", id);
        return tipoRecursoRepository.findById(id)
            .map(tipoRecursoMapper::toDto);
    }

    /**
     * Delete the tipoRecurso by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete TipoRecurso : {}", id);
        tipoRecursoRepository.deleteById(id);
        tipoRecursoSearchRepository.deleteById(id);
    }

    /**
     * Search for the tipoRecurso corresponding to the query.
     *
     * @param query the query of the search.
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<TipoRecursoDTO> search(String query, Pageable pageable) {
        log.debug("Request to search for a page of TipoRecursos for query {}", query);
        return tipoRecursoSearchRepository.search(queryStringQuery(query), pageable)
            .map(tipoRecursoMapper::toDto);
    }
}
