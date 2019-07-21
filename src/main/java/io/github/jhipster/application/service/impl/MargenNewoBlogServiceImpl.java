package io.github.jhipster.application.service.impl;

import io.github.jhipster.application.service.MargenNewoBlogService;
import io.github.jhipster.application.domain.MargenNewoBlog;
import io.github.jhipster.application.repository.MargenNewoBlogRepository;
import io.github.jhipster.application.repository.search.MargenNewoBlogSearchRepository;
import io.github.jhipster.application.service.dto.MargenNewoBlogDTO;
import io.github.jhipster.application.service.mapper.MargenNewoBlogMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.elasticsearch.index.query.QueryBuilders.*;

/**
 * Service Implementation for managing {@link MargenNewoBlog}.
 */
@Service
@Transactional
public class MargenNewoBlogServiceImpl implements MargenNewoBlogService {

    private final Logger log = LoggerFactory.getLogger(MargenNewoBlogServiceImpl.class);

    private final MargenNewoBlogRepository margenNewoBlogRepository;

    private final MargenNewoBlogMapper margenNewoBlogMapper;

    private final MargenNewoBlogSearchRepository margenNewoBlogSearchRepository;

    public MargenNewoBlogServiceImpl(MargenNewoBlogRepository margenNewoBlogRepository, MargenNewoBlogMapper margenNewoBlogMapper, MargenNewoBlogSearchRepository margenNewoBlogSearchRepository) {
        this.margenNewoBlogRepository = margenNewoBlogRepository;
        this.margenNewoBlogMapper = margenNewoBlogMapper;
        this.margenNewoBlogSearchRepository = margenNewoBlogSearchRepository;
    }

    /**
     * Save a margenNewoBlog.
     *
     * @param margenNewoBlogDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public MargenNewoBlogDTO save(MargenNewoBlogDTO margenNewoBlogDTO) {
        log.debug("Request to save MargenNewoBlog : {}", margenNewoBlogDTO);
        MargenNewoBlog margenNewoBlog = margenNewoBlogMapper.toEntity(margenNewoBlogDTO);
        margenNewoBlog = margenNewoBlogRepository.save(margenNewoBlog);
        MargenNewoBlogDTO result = margenNewoBlogMapper.toDto(margenNewoBlog);
        margenNewoBlogSearchRepository.save(margenNewoBlog);
        return result;
    }

    /**
     * Get all the margenNewoBlogs.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<MargenNewoBlogDTO> findAll(Pageable pageable) {
        log.debug("Request to get all MargenNewoBlogs");
        return margenNewoBlogRepository.findAll(pageable)
            .map(margenNewoBlogMapper::toDto);
    }


    /**
     * Get one margenNewoBlog by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<MargenNewoBlogDTO> findOne(Long id) {
        log.debug("Request to get MargenNewoBlog : {}", id);
        return margenNewoBlogRepository.findById(id)
            .map(margenNewoBlogMapper::toDto);
    }

    /**
     * Delete the margenNewoBlog by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete MargenNewoBlog : {}", id);
        margenNewoBlogRepository.deleteById(id);
        margenNewoBlogSearchRepository.deleteById(id);
    }

    /**
     * Search for the margenNewoBlog corresponding to the query.
     *
     * @param query the query of the search.
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<MargenNewoBlogDTO> search(String query, Pageable pageable) {
        log.debug("Request to search for a page of MargenNewoBlogs for query {}", query);
        return margenNewoBlogSearchRepository.search(queryStringQuery(query), pageable)
            .map(margenNewoBlogMapper::toDto);
    }
}
