package io.github.jhipster.application.service.impl;

import io.github.jhipster.application.service.ReservasService;
import io.github.jhipster.application.domain.Reservas;
import io.github.jhipster.application.repository.ReservasRepository;
import io.github.jhipster.application.repository.search.ReservasSearchRepository;
import io.github.jhipster.application.service.dto.ReservasDTO;
import io.github.jhipster.application.service.mapper.ReservasMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.elasticsearch.index.query.QueryBuilders.*;

/**
 * Service Implementation for managing {@link Reservas}.
 */
@Service
@Transactional
public class ReservasServiceImpl implements ReservasService {

    private final Logger log = LoggerFactory.getLogger(ReservasServiceImpl.class);

    private final ReservasRepository reservasRepository;

    private final ReservasMapper reservasMapper;

    private final ReservasSearchRepository reservasSearchRepository;

    public ReservasServiceImpl(ReservasRepository reservasRepository, ReservasMapper reservasMapper, ReservasSearchRepository reservasSearchRepository) {
        this.reservasRepository = reservasRepository;
        this.reservasMapper = reservasMapper;
        this.reservasSearchRepository = reservasSearchRepository;
    }

    /**
     * Save a reservas.
     *
     * @param reservasDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public ReservasDTO save(ReservasDTO reservasDTO) {
        log.debug("Request to save Reservas : {}", reservasDTO);
        Reservas reservas = reservasMapper.toEntity(reservasDTO);
        reservas = reservasRepository.save(reservas);
        ReservasDTO result = reservasMapper.toDto(reservas);
        reservasSearchRepository.save(reservas);
        return result;
    }

    /**
     * Get all the reservas.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<ReservasDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Reservas");
        return reservasRepository.findAll(pageable)
            .map(reservasMapper::toDto);
    }


    /**
     * Get one reservas by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<ReservasDTO> findOne(Long id) {
        log.debug("Request to get Reservas : {}", id);
        return reservasRepository.findById(id)
            .map(reservasMapper::toDto);
    }

    /**
     * Delete the reservas by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Reservas : {}", id);
        reservasRepository.deleteById(id);
        reservasSearchRepository.deleteById(id);
    }

    /**
     * Search for the reservas corresponding to the query.
     *
     * @param query the query of the search.
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<ReservasDTO> search(String query, Pageable pageable) {
        log.debug("Request to search for a page of Reservas for query {}", query);
        return reservasSearchRepository.search(queryStringQuery(query), pageable)
            .map(reservasMapper::toDto);
    }
}
