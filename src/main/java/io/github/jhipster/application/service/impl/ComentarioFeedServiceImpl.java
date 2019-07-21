package io.github.jhipster.application.service.impl;

import io.github.jhipster.application.service.ComentarioFeedService;
import io.github.jhipster.application.domain.ComentarioFeed;
import io.github.jhipster.application.repository.ComentarioFeedRepository;
import io.github.jhipster.application.repository.search.ComentarioFeedSearchRepository;
import io.github.jhipster.application.service.dto.ComentarioFeedDTO;
import io.github.jhipster.application.service.mapper.ComentarioFeedMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.elasticsearch.index.query.QueryBuilders.*;

/**
 * Service Implementation for managing {@link ComentarioFeed}.
 */
@Service
@Transactional
public class ComentarioFeedServiceImpl implements ComentarioFeedService {

    private final Logger log = LoggerFactory.getLogger(ComentarioFeedServiceImpl.class);

    private final ComentarioFeedRepository comentarioFeedRepository;

    private final ComentarioFeedMapper comentarioFeedMapper;

    private final ComentarioFeedSearchRepository comentarioFeedSearchRepository;

    public ComentarioFeedServiceImpl(ComentarioFeedRepository comentarioFeedRepository, ComentarioFeedMapper comentarioFeedMapper, ComentarioFeedSearchRepository comentarioFeedSearchRepository) {
        this.comentarioFeedRepository = comentarioFeedRepository;
        this.comentarioFeedMapper = comentarioFeedMapper;
        this.comentarioFeedSearchRepository = comentarioFeedSearchRepository;
    }

    /**
     * Save a comentarioFeed.
     *
     * @param comentarioFeedDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public ComentarioFeedDTO save(ComentarioFeedDTO comentarioFeedDTO) {
        log.debug("Request to save ComentarioFeed : {}", comentarioFeedDTO);
        ComentarioFeed comentarioFeed = comentarioFeedMapper.toEntity(comentarioFeedDTO);
        comentarioFeed = comentarioFeedRepository.save(comentarioFeed);
        ComentarioFeedDTO result = comentarioFeedMapper.toDto(comentarioFeed);
        comentarioFeedSearchRepository.save(comentarioFeed);
        return result;
    }

    /**
     * Get all the comentarioFeeds.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<ComentarioFeedDTO> findAll(Pageable pageable) {
        log.debug("Request to get all ComentarioFeeds");
        return comentarioFeedRepository.findAll(pageable)
            .map(comentarioFeedMapper::toDto);
    }


    /**
     * Get one comentarioFeed by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<ComentarioFeedDTO> findOne(Long id) {
        log.debug("Request to get ComentarioFeed : {}", id);
        return comentarioFeedRepository.findById(id)
            .map(comentarioFeedMapper::toDto);
    }

    /**
     * Delete the comentarioFeed by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete ComentarioFeed : {}", id);
        comentarioFeedRepository.deleteById(id);
        comentarioFeedSearchRepository.deleteById(id);
    }

    /**
     * Search for the comentarioFeed corresponding to the query.
     *
     * @param query the query of the search.
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<ComentarioFeedDTO> search(String query, Pageable pageable) {
        log.debug("Request to search for a page of ComentarioFeeds for query {}", query);
        return comentarioFeedSearchRepository.search(queryStringQuery(query), pageable)
            .map(comentarioFeedMapper::toDto);
    }
}
