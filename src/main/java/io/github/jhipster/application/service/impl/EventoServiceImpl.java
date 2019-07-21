package io.github.jhipster.application.service.impl;

import io.github.jhipster.application.service.EventoService;
import io.github.jhipster.application.domain.Evento;
import io.github.jhipster.application.repository.EventoRepository;
import io.github.jhipster.application.repository.search.EventoSearchRepository;
import io.github.jhipster.application.service.dto.EventoDTO;
import io.github.jhipster.application.service.mapper.EventoMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.elasticsearch.index.query.QueryBuilders.*;

/**
 * Service Implementation for managing {@link Evento}.
 */
@Service
@Transactional
public class EventoServiceImpl implements EventoService {

    private final Logger log = LoggerFactory.getLogger(EventoServiceImpl.class);

    private final EventoRepository eventoRepository;

    private final EventoMapper eventoMapper;

    private final EventoSearchRepository eventoSearchRepository;

    public EventoServiceImpl(EventoRepository eventoRepository, EventoMapper eventoMapper, EventoSearchRepository eventoSearchRepository) {
        this.eventoRepository = eventoRepository;
        this.eventoMapper = eventoMapper;
        this.eventoSearchRepository = eventoSearchRepository;
    }

    /**
     * Save a evento.
     *
     * @param eventoDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public EventoDTO save(EventoDTO eventoDTO) {
        log.debug("Request to save Evento : {}", eventoDTO);
        Evento evento = eventoMapper.toEntity(eventoDTO);
        evento = eventoRepository.save(evento);
        EventoDTO result = eventoMapper.toDto(evento);
        eventoSearchRepository.save(evento);
        return result;
    }

    /**
     * Get all the eventos.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<EventoDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Eventos");
        return eventoRepository.findAll(pageable)
            .map(eventoMapper::toDto);
    }


    /**
     * Get one evento by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<EventoDTO> findOne(Long id) {
        log.debug("Request to get Evento : {}", id);
        return eventoRepository.findById(id)
            .map(eventoMapper::toDto);
    }

    /**
     * Delete the evento by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Evento : {}", id);
        eventoRepository.deleteById(id);
        eventoSearchRepository.deleteById(id);
    }

    /**
     * Search for the evento corresponding to the query.
     *
     * @param query the query of the search.
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<EventoDTO> search(String query, Pageable pageable) {
        log.debug("Request to search for a page of Eventos for query {}", query);
        return eventoSearchRepository.search(queryStringQuery(query), pageable)
            .map(eventoMapper::toDto);
    }
}
