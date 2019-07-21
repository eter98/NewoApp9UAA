package io.github.jhipster.application.service.impl;

import io.github.jhipster.application.service.EntradaMiembrosService;
import io.github.jhipster.application.domain.EntradaMiembros;
import io.github.jhipster.application.repository.EntradaMiembrosRepository;
import io.github.jhipster.application.repository.search.EntradaMiembrosSearchRepository;
import io.github.jhipster.application.service.dto.EntradaMiembrosDTO;
import io.github.jhipster.application.service.mapper.EntradaMiembrosMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.elasticsearch.index.query.QueryBuilders.*;

/**
 * Service Implementation for managing {@link EntradaMiembros}.
 */
@Service
@Transactional
public class EntradaMiembrosServiceImpl implements EntradaMiembrosService {

    private final Logger log = LoggerFactory.getLogger(EntradaMiembrosServiceImpl.class);

    private final EntradaMiembrosRepository entradaMiembrosRepository;

    private final EntradaMiembrosMapper entradaMiembrosMapper;

    private final EntradaMiembrosSearchRepository entradaMiembrosSearchRepository;

    public EntradaMiembrosServiceImpl(EntradaMiembrosRepository entradaMiembrosRepository, EntradaMiembrosMapper entradaMiembrosMapper, EntradaMiembrosSearchRepository entradaMiembrosSearchRepository) {
        this.entradaMiembrosRepository = entradaMiembrosRepository;
        this.entradaMiembrosMapper = entradaMiembrosMapper;
        this.entradaMiembrosSearchRepository = entradaMiembrosSearchRepository;
    }

    /**
     * Save a entradaMiembros.
     *
     * @param entradaMiembrosDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public EntradaMiembrosDTO save(EntradaMiembrosDTO entradaMiembrosDTO) {
        log.debug("Request to save EntradaMiembros : {}", entradaMiembrosDTO);
        EntradaMiembros entradaMiembros = entradaMiembrosMapper.toEntity(entradaMiembrosDTO);
        entradaMiembros = entradaMiembrosRepository.save(entradaMiembros);
        EntradaMiembrosDTO result = entradaMiembrosMapper.toDto(entradaMiembros);
        entradaMiembrosSearchRepository.save(entradaMiembros);
        return result;
    }

    /**
     * Get all the entradaMiembros.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<EntradaMiembrosDTO> findAll(Pageable pageable) {
        log.debug("Request to get all EntradaMiembros");
        return entradaMiembrosRepository.findAll(pageable)
            .map(entradaMiembrosMapper::toDto);
    }


    /**
     * Get one entradaMiembros by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<EntradaMiembrosDTO> findOne(Long id) {
        log.debug("Request to get EntradaMiembros : {}", id);
        return entradaMiembrosRepository.findById(id)
            .map(entradaMiembrosMapper::toDto);
    }

    /**
     * Delete the entradaMiembros by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete EntradaMiembros : {}", id);
        entradaMiembrosRepository.deleteById(id);
        entradaMiembrosSearchRepository.deleteById(id);
    }

    /**
     * Search for the entradaMiembros corresponding to the query.
     *
     * @param query the query of the search.
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<EntradaMiembrosDTO> search(String query, Pageable pageable) {
        log.debug("Request to search for a page of EntradaMiembros for query {}", query);
        return entradaMiembrosSearchRepository.search(queryStringQuery(query), pageable)
            .map(entradaMiembrosMapper::toDto);
    }
}
