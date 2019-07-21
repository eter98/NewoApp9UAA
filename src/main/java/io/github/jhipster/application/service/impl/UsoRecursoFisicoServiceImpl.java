package io.github.jhipster.application.service.impl;

import io.github.jhipster.application.service.UsoRecursoFisicoService;
import io.github.jhipster.application.domain.UsoRecursoFisico;
import io.github.jhipster.application.repository.UsoRecursoFisicoRepository;
import io.github.jhipster.application.repository.search.UsoRecursoFisicoSearchRepository;
import io.github.jhipster.application.service.dto.UsoRecursoFisicoDTO;
import io.github.jhipster.application.service.mapper.UsoRecursoFisicoMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.elasticsearch.index.query.QueryBuilders.*;

/**
 * Service Implementation for managing {@link UsoRecursoFisico}.
 */
@Service
@Transactional
public class UsoRecursoFisicoServiceImpl implements UsoRecursoFisicoService {

    private final Logger log = LoggerFactory.getLogger(UsoRecursoFisicoServiceImpl.class);

    private final UsoRecursoFisicoRepository usoRecursoFisicoRepository;

    private final UsoRecursoFisicoMapper usoRecursoFisicoMapper;

    private final UsoRecursoFisicoSearchRepository usoRecursoFisicoSearchRepository;

    public UsoRecursoFisicoServiceImpl(UsoRecursoFisicoRepository usoRecursoFisicoRepository, UsoRecursoFisicoMapper usoRecursoFisicoMapper, UsoRecursoFisicoSearchRepository usoRecursoFisicoSearchRepository) {
        this.usoRecursoFisicoRepository = usoRecursoFisicoRepository;
        this.usoRecursoFisicoMapper = usoRecursoFisicoMapper;
        this.usoRecursoFisicoSearchRepository = usoRecursoFisicoSearchRepository;
    }

    /**
     * Save a usoRecursoFisico.
     *
     * @param usoRecursoFisicoDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public UsoRecursoFisicoDTO save(UsoRecursoFisicoDTO usoRecursoFisicoDTO) {
        log.debug("Request to save UsoRecursoFisico : {}", usoRecursoFisicoDTO);
        UsoRecursoFisico usoRecursoFisico = usoRecursoFisicoMapper.toEntity(usoRecursoFisicoDTO);
        usoRecursoFisico = usoRecursoFisicoRepository.save(usoRecursoFisico);
        UsoRecursoFisicoDTO result = usoRecursoFisicoMapper.toDto(usoRecursoFisico);
        usoRecursoFisicoSearchRepository.save(usoRecursoFisico);
        return result;
    }

    /**
     * Get all the usoRecursoFisicos.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<UsoRecursoFisicoDTO> findAll(Pageable pageable) {
        log.debug("Request to get all UsoRecursoFisicos");
        return usoRecursoFisicoRepository.findAll(pageable)
            .map(usoRecursoFisicoMapper::toDto);
    }


    /**
     * Get one usoRecursoFisico by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<UsoRecursoFisicoDTO> findOne(Long id) {
        log.debug("Request to get UsoRecursoFisico : {}", id);
        return usoRecursoFisicoRepository.findById(id)
            .map(usoRecursoFisicoMapper::toDto);
    }

    /**
     * Delete the usoRecursoFisico by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete UsoRecursoFisico : {}", id);
        usoRecursoFisicoRepository.deleteById(id);
        usoRecursoFisicoSearchRepository.deleteById(id);
    }

    /**
     * Search for the usoRecursoFisico corresponding to the query.
     *
     * @param query the query of the search.
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<UsoRecursoFisicoDTO> search(String query, Pageable pageable) {
        log.debug("Request to search for a page of UsoRecursoFisicos for query {}", query);
        return usoRecursoFisicoSearchRepository.search(queryStringQuery(query), pageable)
            .map(usoRecursoFisicoMapper::toDto);
    }
}
