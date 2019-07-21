package io.github.jhipster.application.service.impl;

import io.github.jhipster.application.service.RegistroCompraService;
import io.github.jhipster.application.domain.RegistroCompra;
import io.github.jhipster.application.repository.RegistroCompraRepository;
import io.github.jhipster.application.repository.search.RegistroCompraSearchRepository;
import io.github.jhipster.application.service.dto.RegistroCompraDTO;
import io.github.jhipster.application.service.mapper.RegistroCompraMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.elasticsearch.index.query.QueryBuilders.*;

/**
 * Service Implementation for managing {@link RegistroCompra}.
 */
@Service
@Transactional
public class RegistroCompraServiceImpl implements RegistroCompraService {

    private final Logger log = LoggerFactory.getLogger(RegistroCompraServiceImpl.class);

    private final RegistroCompraRepository registroCompraRepository;

    private final RegistroCompraMapper registroCompraMapper;

    private final RegistroCompraSearchRepository registroCompraSearchRepository;

    public RegistroCompraServiceImpl(RegistroCompraRepository registroCompraRepository, RegistroCompraMapper registroCompraMapper, RegistroCompraSearchRepository registroCompraSearchRepository) {
        this.registroCompraRepository = registroCompraRepository;
        this.registroCompraMapper = registroCompraMapper;
        this.registroCompraSearchRepository = registroCompraSearchRepository;
    }

    /**
     * Save a registroCompra.
     *
     * @param registroCompraDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public RegistroCompraDTO save(RegistroCompraDTO registroCompraDTO) {
        log.debug("Request to save RegistroCompra : {}", registroCompraDTO);
        RegistroCompra registroCompra = registroCompraMapper.toEntity(registroCompraDTO);
        registroCompra = registroCompraRepository.save(registroCompra);
        RegistroCompraDTO result = registroCompraMapper.toDto(registroCompra);
        registroCompraSearchRepository.save(registroCompra);
        return result;
    }

    /**
     * Get all the registroCompras.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<RegistroCompraDTO> findAll(Pageable pageable) {
        log.debug("Request to get all RegistroCompras");
        return registroCompraRepository.findAll(pageable)
            .map(registroCompraMapper::toDto);
    }


    /**
     * Get one registroCompra by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<RegistroCompraDTO> findOne(Long id) {
        log.debug("Request to get RegistroCompra : {}", id);
        return registroCompraRepository.findById(id)
            .map(registroCompraMapper::toDto);
    }

    /**
     * Delete the registroCompra by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete RegistroCompra : {}", id);
        registroCompraRepository.deleteById(id);
        registroCompraSearchRepository.deleteById(id);
    }

    /**
     * Search for the registroCompra corresponding to the query.
     *
     * @param query the query of the search.
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<RegistroCompraDTO> search(String query, Pageable pageable) {
        log.debug("Request to search for a page of RegistroCompras for query {}", query);
        return registroCompraSearchRepository.search(queryStringQuery(query), pageable)
            .map(registroCompraMapper::toDto);
    }
}
