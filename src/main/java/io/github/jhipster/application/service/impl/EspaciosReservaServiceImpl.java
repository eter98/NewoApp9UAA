package io.github.jhipster.application.service.impl;

import io.github.jhipster.application.service.EspaciosReservaService;
import io.github.jhipster.application.domain.EspaciosReserva;
import io.github.jhipster.application.repository.EspaciosReservaRepository;
import io.github.jhipster.application.repository.search.EspaciosReservaSearchRepository;
import io.github.jhipster.application.service.dto.EspaciosReservaDTO;
import io.github.jhipster.application.service.mapper.EspaciosReservaMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.elasticsearch.index.query.QueryBuilders.*;

/**
 * Service Implementation for managing {@link EspaciosReserva}.
 */
@Service
@Transactional
public class EspaciosReservaServiceImpl implements EspaciosReservaService {

    private final Logger log = LoggerFactory.getLogger(EspaciosReservaServiceImpl.class);

    private final EspaciosReservaRepository espaciosReservaRepository;

    private final EspaciosReservaMapper espaciosReservaMapper;

    private final EspaciosReservaSearchRepository espaciosReservaSearchRepository;

    public EspaciosReservaServiceImpl(EspaciosReservaRepository espaciosReservaRepository, EspaciosReservaMapper espaciosReservaMapper, EspaciosReservaSearchRepository espaciosReservaSearchRepository) {
        this.espaciosReservaRepository = espaciosReservaRepository;
        this.espaciosReservaMapper = espaciosReservaMapper;
        this.espaciosReservaSearchRepository = espaciosReservaSearchRepository;
    }

    /**
     * Save a espaciosReserva.
     *
     * @param espaciosReservaDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public EspaciosReservaDTO save(EspaciosReservaDTO espaciosReservaDTO) {
        log.debug("Request to save EspaciosReserva : {}", espaciosReservaDTO);
        EspaciosReserva espaciosReserva = espaciosReservaMapper.toEntity(espaciosReservaDTO);
        espaciosReserva = espaciosReservaRepository.save(espaciosReserva);
        EspaciosReservaDTO result = espaciosReservaMapper.toDto(espaciosReserva);
        espaciosReservaSearchRepository.save(espaciosReserva);
        return result;
    }

    /**
     * Get all the espaciosReservas.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<EspaciosReservaDTO> findAll(Pageable pageable) {
        log.debug("Request to get all EspaciosReservas");
        return espaciosReservaRepository.findAll(pageable)
            .map(espaciosReservaMapper::toDto);
    }


    /**
     * Get one espaciosReserva by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<EspaciosReservaDTO> findOne(Long id) {
        log.debug("Request to get EspaciosReserva : {}", id);
        return espaciosReservaRepository.findById(id)
            .map(espaciosReservaMapper::toDto);
    }

    /**
     * Delete the espaciosReserva by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete EspaciosReserva : {}", id);
        espaciosReservaRepository.deleteById(id);
        espaciosReservaSearchRepository.deleteById(id);
    }

    /**
     * Search for the espaciosReserva corresponding to the query.
     *
     * @param query the query of the search.
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<EspaciosReservaDTO> search(String query, Pageable pageable) {
        log.debug("Request to search for a page of EspaciosReservas for query {}", query);
        return espaciosReservaSearchRepository.search(queryStringQuery(query), pageable)
            .map(espaciosReservaMapper::toDto);
    }
}
