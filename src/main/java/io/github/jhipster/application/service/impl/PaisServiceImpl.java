package io.github.jhipster.application.service.impl;

import io.github.jhipster.application.service.PaisService;
import io.github.jhipster.application.domain.Pais;
import io.github.jhipster.application.repository.PaisRepository;
import io.github.jhipster.application.repository.search.PaisSearchRepository;
import io.github.jhipster.application.service.dto.PaisDTO;
import io.github.jhipster.application.service.mapper.PaisMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.elasticsearch.index.query.QueryBuilders.*;

/**
 * Service Implementation for managing {@link Pais}.
 */
@Service
@Transactional
public class PaisServiceImpl implements PaisService {

    private final Logger log = LoggerFactory.getLogger(PaisServiceImpl.class);

    private final PaisRepository paisRepository;

    private final PaisMapper paisMapper;

    private final PaisSearchRepository paisSearchRepository;

    public PaisServiceImpl(PaisRepository paisRepository, PaisMapper paisMapper, PaisSearchRepository paisSearchRepository) {
        this.paisRepository = paisRepository;
        this.paisMapper = paisMapper;
        this.paisSearchRepository = paisSearchRepository;
    }

    /**
     * Save a pais.
     *
     * @param paisDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public PaisDTO save(PaisDTO paisDTO) {
        log.debug("Request to save Pais : {}", paisDTO);
        Pais pais = paisMapper.toEntity(paisDTO);
        pais = paisRepository.save(pais);
        PaisDTO result = paisMapper.toDto(pais);
        paisSearchRepository.save(pais);
        return result;
    }

    /**
     * Get all the pais.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<PaisDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Pais");
        return paisRepository.findAll(pageable)
            .map(paisMapper::toDto);
    }


    /**
     * Get one pais by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<PaisDTO> findOne(Long id) {
        log.debug("Request to get Pais : {}", id);
        return paisRepository.findById(id)
            .map(paisMapper::toDto);
    }

    /**
     * Delete the pais by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Pais : {}", id);
        paisRepository.deleteById(id);
        paisSearchRepository.deleteById(id);
    }

    /**
     * Search for the pais corresponding to the query.
     *
     * @param query the query of the search.
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<PaisDTO> search(String query, Pageable pageable) {
        log.debug("Request to search for a page of Pais for query {}", query);
        return paisSearchRepository.search(queryStringQuery(query), pageable)
            .map(paisMapper::toDto);
    }
}
