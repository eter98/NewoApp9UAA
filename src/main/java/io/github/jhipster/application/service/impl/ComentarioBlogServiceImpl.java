package io.github.jhipster.application.service.impl;

import io.github.jhipster.application.service.ComentarioBlogService;
import io.github.jhipster.application.domain.ComentarioBlog;
import io.github.jhipster.application.repository.ComentarioBlogRepository;
import io.github.jhipster.application.repository.search.ComentarioBlogSearchRepository;
import io.github.jhipster.application.service.dto.ComentarioBlogDTO;
import io.github.jhipster.application.service.mapper.ComentarioBlogMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.elasticsearch.index.query.QueryBuilders.*;

/**
 * Service Implementation for managing {@link ComentarioBlog}.
 */
@Service
@Transactional
public class ComentarioBlogServiceImpl implements ComentarioBlogService {

    private final Logger log = LoggerFactory.getLogger(ComentarioBlogServiceImpl.class);

    private final ComentarioBlogRepository comentarioBlogRepository;

    private final ComentarioBlogMapper comentarioBlogMapper;

    private final ComentarioBlogSearchRepository comentarioBlogSearchRepository;

    public ComentarioBlogServiceImpl(ComentarioBlogRepository comentarioBlogRepository, ComentarioBlogMapper comentarioBlogMapper, ComentarioBlogSearchRepository comentarioBlogSearchRepository) {
        this.comentarioBlogRepository = comentarioBlogRepository;
        this.comentarioBlogMapper = comentarioBlogMapper;
        this.comentarioBlogSearchRepository = comentarioBlogSearchRepository;
    }

    /**
     * Save a comentarioBlog.
     *
     * @param comentarioBlogDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public ComentarioBlogDTO save(ComentarioBlogDTO comentarioBlogDTO) {
        log.debug("Request to save ComentarioBlog : {}", comentarioBlogDTO);
        ComentarioBlog comentarioBlog = comentarioBlogMapper.toEntity(comentarioBlogDTO);
        comentarioBlog = comentarioBlogRepository.save(comentarioBlog);
        ComentarioBlogDTO result = comentarioBlogMapper.toDto(comentarioBlog);
        comentarioBlogSearchRepository.save(comentarioBlog);
        return result;
    }

    /**
     * Get all the comentarioBlogs.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<ComentarioBlogDTO> findAll(Pageable pageable) {
        log.debug("Request to get all ComentarioBlogs");
        return comentarioBlogRepository.findAll(pageable)
            .map(comentarioBlogMapper::toDto);
    }


    /**
     * Get one comentarioBlog by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<ComentarioBlogDTO> findOne(Long id) {
        log.debug("Request to get ComentarioBlog : {}", id);
        return comentarioBlogRepository.findById(id)
            .map(comentarioBlogMapper::toDto);
    }

    /**
     * Delete the comentarioBlog by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete ComentarioBlog : {}", id);
        comentarioBlogRepository.deleteById(id);
        comentarioBlogSearchRepository.deleteById(id);
    }

    /**
     * Search for the comentarioBlog corresponding to the query.
     *
     * @param query the query of the search.
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<ComentarioBlogDTO> search(String query, Pageable pageable) {
        log.debug("Request to search for a page of ComentarioBlogs for query {}", query);
        return comentarioBlogSearchRepository.search(queryStringQuery(query), pageable)
            .map(comentarioBlogMapper::toDto);
    }
}
