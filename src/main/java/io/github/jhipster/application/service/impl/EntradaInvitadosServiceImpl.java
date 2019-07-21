package io.github.jhipster.application.service.impl;

import io.github.jhipster.application.service.EntradaInvitadosService;
import io.github.jhipster.application.domain.EntradaInvitados;
import io.github.jhipster.application.repository.EntradaInvitadosRepository;
import io.github.jhipster.application.repository.search.EntradaInvitadosSearchRepository;
import io.github.jhipster.application.service.dto.EntradaInvitadosDTO;
import io.github.jhipster.application.service.mapper.EntradaInvitadosMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.elasticsearch.index.query.QueryBuilders.*;

/**
 * Service Implementation for managing {@link EntradaInvitados}.
 */
@Service
@Transactional
public class EntradaInvitadosServiceImpl implements EntradaInvitadosService {

    private final Logger log = LoggerFactory.getLogger(EntradaInvitadosServiceImpl.class);

    private final EntradaInvitadosRepository entradaInvitadosRepository;

    private final EntradaInvitadosMapper entradaInvitadosMapper;

    private final EntradaInvitadosSearchRepository entradaInvitadosSearchRepository;

    public EntradaInvitadosServiceImpl(EntradaInvitadosRepository entradaInvitadosRepository, EntradaInvitadosMapper entradaInvitadosMapper, EntradaInvitadosSearchRepository entradaInvitadosSearchRepository) {
        this.entradaInvitadosRepository = entradaInvitadosRepository;
        this.entradaInvitadosMapper = entradaInvitadosMapper;
        this.entradaInvitadosSearchRepository = entradaInvitadosSearchRepository;
    }

    /**
     * Save a entradaInvitados.
     *
     * @param entradaInvitadosDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public EntradaInvitadosDTO save(EntradaInvitadosDTO entradaInvitadosDTO) {
        log.debug("Request to save EntradaInvitados : {}", entradaInvitadosDTO);
        EntradaInvitados entradaInvitados = entradaInvitadosMapper.toEntity(entradaInvitadosDTO);
        entradaInvitados = entradaInvitadosRepository.save(entradaInvitados);
        EntradaInvitadosDTO result = entradaInvitadosMapper.toDto(entradaInvitados);
        entradaInvitadosSearchRepository.save(entradaInvitados);
        return result;
    }

    /**
     * Get all the entradaInvitados.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<EntradaInvitadosDTO> findAll(Pageable pageable) {
        log.debug("Request to get all EntradaInvitados");
        return entradaInvitadosRepository.findAll(pageable)
            .map(entradaInvitadosMapper::toDto);
    }


    /**
     * Get one entradaInvitados by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<EntradaInvitadosDTO> findOne(Long id) {
        log.debug("Request to get EntradaInvitados : {}", id);
        return entradaInvitadosRepository.findById(id)
            .map(entradaInvitadosMapper::toDto);
    }

    /**
     * Delete the entradaInvitados by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete EntradaInvitados : {}", id);
        entradaInvitadosRepository.deleteById(id);
        entradaInvitadosSearchRepository.deleteById(id);
    }

    /**
     * Search for the entradaInvitados corresponding to the query.
     *
     * @param query the query of the search.
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<EntradaInvitadosDTO> search(String query, Pageable pageable) {
        log.debug("Request to search for a page of EntradaInvitados for query {}", query);
        return entradaInvitadosSearchRepository.search(queryStringQuery(query), pageable)
            .map(entradaInvitadosMapper::toDto);
    }
}
