package io.github.jhipster.application.service.impl;

import io.github.jhipster.application.service.MiembrosService;
import io.github.jhipster.application.domain.Miembros;
import io.github.jhipster.application.repository.MiembrosRepository;
import io.github.jhipster.application.repository.search.MiembrosSearchRepository;
import io.github.jhipster.application.service.dto.MiembrosDTO;
import io.github.jhipster.application.service.mapper.MiembrosMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.elasticsearch.index.query.QueryBuilders.*;

/**
 * Service Implementation for managing {@link Miembros}.
 */
@Service
@Transactional
public class MiembrosServiceImpl implements MiembrosService {

    private final Logger log = LoggerFactory.getLogger(MiembrosServiceImpl.class);

    private final MiembrosRepository miembrosRepository;

    private final MiembrosMapper miembrosMapper;

    private final MiembrosSearchRepository miembrosSearchRepository;

    public MiembrosServiceImpl(MiembrosRepository miembrosRepository, MiembrosMapper miembrosMapper, MiembrosSearchRepository miembrosSearchRepository) {
        this.miembrosRepository = miembrosRepository;
        this.miembrosMapper = miembrosMapper;
        this.miembrosSearchRepository = miembrosSearchRepository;
    }

    /**
     * Save a miembros.
     *
     * @param miembrosDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public MiembrosDTO save(MiembrosDTO miembrosDTO) {
        log.debug("Request to save Miembros : {}", miembrosDTO);
        Miembros miembros = miembrosMapper.toEntity(miembrosDTO);
        miembros = miembrosRepository.save(miembros);
        MiembrosDTO result = miembrosMapper.toDto(miembros);
        miembrosSearchRepository.save(miembros);
        return result;
    }

    /**
     * Get all the miembros.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<MiembrosDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Miembros");
        return miembrosRepository.findAll(pageable)
            .map(miembrosMapper::toDto);
    }


    /**
     * Get one miembros by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<MiembrosDTO> findOne(Long id) {
        log.debug("Request to get Miembros : {}", id);
        return miembrosRepository.findById(id)
            .map(miembrosMapper::toDto);
    }

    /**
     * Delete the miembros by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Miembros : {}", id);
        miembrosRepository.deleteById(id);
        miembrosSearchRepository.deleteById(id);
    }

    /**
     * Search for the miembros corresponding to the query.
     *
     * @param query the query of the search.
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<MiembrosDTO> search(String query, Pageable pageable) {
        log.debug("Request to search for a page of Miembros for query {}", query);
        return miembrosSearchRepository.search(queryStringQuery(query), pageable)
            .map(miembrosMapper::toDto);
    }
}
