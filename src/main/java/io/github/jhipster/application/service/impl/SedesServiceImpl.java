package io.github.jhipster.application.service.impl;

import io.github.jhipster.application.service.SedesService;
import io.github.jhipster.application.domain.Sedes;
import io.github.jhipster.application.repository.SedesRepository;
import io.github.jhipster.application.repository.search.SedesSearchRepository;
import io.github.jhipster.application.service.dto.SedesDTO;
import io.github.jhipster.application.service.mapper.SedesMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.elasticsearch.index.query.QueryBuilders.*;

/**
 * Service Implementation for managing {@link Sedes}.
 */
@Service
@Transactional
public class SedesServiceImpl implements SedesService {

    private final Logger log = LoggerFactory.getLogger(SedesServiceImpl.class);

    private final SedesRepository sedesRepository;

    private final SedesMapper sedesMapper;

    private final SedesSearchRepository sedesSearchRepository;

    public SedesServiceImpl(SedesRepository sedesRepository, SedesMapper sedesMapper, SedesSearchRepository sedesSearchRepository) {
        this.sedesRepository = sedesRepository;
        this.sedesMapper = sedesMapper;
        this.sedesSearchRepository = sedesSearchRepository;
    }

    /**
     * Save a sedes.
     *
     * @param sedesDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public SedesDTO save(SedesDTO sedesDTO) {
        log.debug("Request to save Sedes : {}", sedesDTO);
        Sedes sedes = sedesMapper.toEntity(sedesDTO);
        sedes = sedesRepository.save(sedes);
        SedesDTO result = sedesMapper.toDto(sedes);
        sedesSearchRepository.save(sedes);
        return result;
    }

    /**
     * Get all the sedes.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<SedesDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Sedes");
        return sedesRepository.findAll(pageable)
            .map(sedesMapper::toDto);
    }


    /**
     * Get one sedes by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<SedesDTO> findOne(Long id) {
        log.debug("Request to get Sedes : {}", id);
        return sedesRepository.findById(id)
            .map(sedesMapper::toDto);
    }

    /**
     * Delete the sedes by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Sedes : {}", id);
        sedesRepository.deleteById(id);
        sedesSearchRepository.deleteById(id);
    }

    /**
     * Search for the sedes corresponding to the query.
     *
     * @param query the query of the search.
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<SedesDTO> search(String query, Pageable pageable) {
        log.debug("Request to search for a page of Sedes for query {}", query);
        return sedesSearchRepository.search(queryStringQuery(query), pageable)
            .map(sedesMapper::toDto);
    }
}
