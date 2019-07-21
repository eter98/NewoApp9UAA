package io.github.jhipster.application.service.impl;

import io.github.jhipster.application.service.MargenNewoProductosService;
import io.github.jhipster.application.domain.MargenNewoProductos;
import io.github.jhipster.application.repository.MargenNewoProductosRepository;
import io.github.jhipster.application.repository.search.MargenNewoProductosSearchRepository;
import io.github.jhipster.application.service.dto.MargenNewoProductosDTO;
import io.github.jhipster.application.service.mapper.MargenNewoProductosMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.elasticsearch.index.query.QueryBuilders.*;

/**
 * Service Implementation for managing {@link MargenNewoProductos}.
 */
@Service
@Transactional
public class MargenNewoProductosServiceImpl implements MargenNewoProductosService {

    private final Logger log = LoggerFactory.getLogger(MargenNewoProductosServiceImpl.class);

    private final MargenNewoProductosRepository margenNewoProductosRepository;

    private final MargenNewoProductosMapper margenNewoProductosMapper;

    private final MargenNewoProductosSearchRepository margenNewoProductosSearchRepository;

    public MargenNewoProductosServiceImpl(MargenNewoProductosRepository margenNewoProductosRepository, MargenNewoProductosMapper margenNewoProductosMapper, MargenNewoProductosSearchRepository margenNewoProductosSearchRepository) {
        this.margenNewoProductosRepository = margenNewoProductosRepository;
        this.margenNewoProductosMapper = margenNewoProductosMapper;
        this.margenNewoProductosSearchRepository = margenNewoProductosSearchRepository;
    }

    /**
     * Save a margenNewoProductos.
     *
     * @param margenNewoProductosDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public MargenNewoProductosDTO save(MargenNewoProductosDTO margenNewoProductosDTO) {
        log.debug("Request to save MargenNewoProductos : {}", margenNewoProductosDTO);
        MargenNewoProductos margenNewoProductos = margenNewoProductosMapper.toEntity(margenNewoProductosDTO);
        margenNewoProductos = margenNewoProductosRepository.save(margenNewoProductos);
        MargenNewoProductosDTO result = margenNewoProductosMapper.toDto(margenNewoProductos);
        margenNewoProductosSearchRepository.save(margenNewoProductos);
        return result;
    }

    /**
     * Get all the margenNewoProductos.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<MargenNewoProductosDTO> findAll(Pageable pageable) {
        log.debug("Request to get all MargenNewoProductos");
        return margenNewoProductosRepository.findAll(pageable)
            .map(margenNewoProductosMapper::toDto);
    }


    /**
     * Get one margenNewoProductos by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<MargenNewoProductosDTO> findOne(Long id) {
        log.debug("Request to get MargenNewoProductos : {}", id);
        return margenNewoProductosRepository.findById(id)
            .map(margenNewoProductosMapper::toDto);
    }

    /**
     * Delete the margenNewoProductos by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete MargenNewoProductos : {}", id);
        margenNewoProductosRepository.deleteById(id);
        margenNewoProductosSearchRepository.deleteById(id);
    }

    /**
     * Search for the margenNewoProductos corresponding to the query.
     *
     * @param query the query of the search.
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<MargenNewoProductosDTO> search(String query, Pageable pageable) {
        log.debug("Request to search for a page of MargenNewoProductos for query {}", query);
        return margenNewoProductosSearchRepository.search(queryStringQuery(query), pageable)
            .map(margenNewoProductosMapper::toDto);
    }
}
