package io.github.jhipster.application.service.impl;

import io.github.jhipster.application.service.InvitadosService;
import io.github.jhipster.application.domain.Invitados;
import io.github.jhipster.application.repository.InvitadosRepository;
import io.github.jhipster.application.repository.search.InvitadosSearchRepository;
import io.github.jhipster.application.service.dto.InvitadosDTO;
import io.github.jhipster.application.service.mapper.InvitadosMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.elasticsearch.index.query.QueryBuilders.*;

/**
 * Service Implementation for managing {@link Invitados}.
 */
@Service
@Transactional
public class InvitadosServiceImpl implements InvitadosService {

    private final Logger log = LoggerFactory.getLogger(InvitadosServiceImpl.class);

    private final InvitadosRepository invitadosRepository;

    private final InvitadosMapper invitadosMapper;

    private final InvitadosSearchRepository invitadosSearchRepository;

    public InvitadosServiceImpl(InvitadosRepository invitadosRepository, InvitadosMapper invitadosMapper, InvitadosSearchRepository invitadosSearchRepository) {
        this.invitadosRepository = invitadosRepository;
        this.invitadosMapper = invitadosMapper;
        this.invitadosSearchRepository = invitadosSearchRepository;
    }

    /**
     * Save a invitados.
     *
     * @param invitadosDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public InvitadosDTO save(InvitadosDTO invitadosDTO) {
        log.debug("Request to save Invitados : {}", invitadosDTO);
        Invitados invitados = invitadosMapper.toEntity(invitadosDTO);
        invitados = invitadosRepository.save(invitados);
        InvitadosDTO result = invitadosMapper.toDto(invitados);
        invitadosSearchRepository.save(invitados);
        return result;
    }

    /**
     * Get all the invitados.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<InvitadosDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Invitados");
        return invitadosRepository.findAll(pageable)
            .map(invitadosMapper::toDto);
    }


    /**
     * Get one invitados by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<InvitadosDTO> findOne(Long id) {
        log.debug("Request to get Invitados : {}", id);
        return invitadosRepository.findById(id)
            .map(invitadosMapper::toDto);
    }

    /**
     * Delete the invitados by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Invitados : {}", id);
        invitadosRepository.deleteById(id);
        invitadosSearchRepository.deleteById(id);
    }

    /**
     * Search for the invitados corresponding to the query.
     *
     * @param query the query of the search.
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<InvitadosDTO> search(String query, Pageable pageable) {
        log.debug("Request to search for a page of Invitados for query {}", query);
        return invitadosSearchRepository.search(queryStringQuery(query), pageable)
            .map(invitadosMapper::toDto);
    }
}
