package io.github.jhipster.application.service.impl;

import io.github.jhipster.application.service.EspacioLibreService;
import io.github.jhipster.application.domain.EspacioLibre;
import io.github.jhipster.application.repository.EspacioLibreRepository;
import io.github.jhipster.application.repository.search.EspacioLibreSearchRepository;
import io.github.jhipster.application.service.dto.EspacioLibreDTO;
import io.github.jhipster.application.service.mapper.EspacioLibreMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.elasticsearch.index.query.QueryBuilders.*;

/**
 * Service Implementation for managing {@link EspacioLibre}.
 */
@Service
@Transactional
public class EspacioLibreServiceImpl implements EspacioLibreService {

    private final Logger log = LoggerFactory.getLogger(EspacioLibreServiceImpl.class);

    private final EspacioLibreRepository espacioLibreRepository;

    private final EspacioLibreMapper espacioLibreMapper;

    private final EspacioLibreSearchRepository espacioLibreSearchRepository;

    public EspacioLibreServiceImpl(EspacioLibreRepository espacioLibreRepository, EspacioLibreMapper espacioLibreMapper, EspacioLibreSearchRepository espacioLibreSearchRepository) {
        this.espacioLibreRepository = espacioLibreRepository;
        this.espacioLibreMapper = espacioLibreMapper;
        this.espacioLibreSearchRepository = espacioLibreSearchRepository;
    }

    /**
     * Save a espacioLibre.
     *
     * @param espacioLibreDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public EspacioLibreDTO save(EspacioLibreDTO espacioLibreDTO) {
        log.debug("Request to save EspacioLibre : {}", espacioLibreDTO);
        EspacioLibre espacioLibre = espacioLibreMapper.toEntity(espacioLibreDTO);
        espacioLibre = espacioLibreRepository.save(espacioLibre);
        EspacioLibreDTO result = espacioLibreMapper.toDto(espacioLibre);
        espacioLibreSearchRepository.save(espacioLibre);
        return result;
    }

    /**
     * Get all the espacioLibres.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<EspacioLibreDTO> findAll(Pageable pageable) {
        log.debug("Request to get all EspacioLibres");
        return espacioLibreRepository.findAll(pageable)
            .map(espacioLibreMapper::toDto);
    }


    /**
     * Get one espacioLibre by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<EspacioLibreDTO> findOne(Long id) {
        log.debug("Request to get EspacioLibre : {}", id);
        return espacioLibreRepository.findById(id)
            .map(espacioLibreMapper::toDto);
    }

    /**
     * Delete the espacioLibre by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete EspacioLibre : {}", id);
        espacioLibreRepository.deleteById(id);
        espacioLibreSearchRepository.deleteById(id);
    }

    /**
     * Search for the espacioLibre corresponding to the query.
     *
     * @param query the query of the search.
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<EspacioLibreDTO> search(String query, Pageable pageable) {
        log.debug("Request to search for a page of EspacioLibres for query {}", query);
        return espacioLibreSearchRepository.search(queryStringQuery(query), pageable)
            .map(espacioLibreMapper::toDto);
    }
}
