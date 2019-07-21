package io.github.jhipster.application.service.impl;

import io.github.jhipster.application.service.FeedService;
import io.github.jhipster.application.domain.Feed;
import io.github.jhipster.application.repository.FeedRepository;
import io.github.jhipster.application.repository.search.FeedSearchRepository;
import io.github.jhipster.application.service.dto.FeedDTO;
import io.github.jhipster.application.service.mapper.FeedMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.elasticsearch.index.query.QueryBuilders.*;

/**
 * Service Implementation for managing {@link Feed}.
 */
@Service
@Transactional
public class FeedServiceImpl implements FeedService {

    private final Logger log = LoggerFactory.getLogger(FeedServiceImpl.class);

    private final FeedRepository feedRepository;

    private final FeedMapper feedMapper;

    private final FeedSearchRepository feedSearchRepository;

    public FeedServiceImpl(FeedRepository feedRepository, FeedMapper feedMapper, FeedSearchRepository feedSearchRepository) {
        this.feedRepository = feedRepository;
        this.feedMapper = feedMapper;
        this.feedSearchRepository = feedSearchRepository;
    }

    /**
     * Save a feed.
     *
     * @param feedDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public FeedDTO save(FeedDTO feedDTO) {
        log.debug("Request to save Feed : {}", feedDTO);
        Feed feed = feedMapper.toEntity(feedDTO);
        feed = feedRepository.save(feed);
        FeedDTO result = feedMapper.toDto(feed);
        feedSearchRepository.save(feed);
        return result;
    }

    /**
     * Get all the feeds.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<FeedDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Feeds");
        return feedRepository.findAll(pageable)
            .map(feedMapper::toDto);
    }


    /**
     * Get one feed by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<FeedDTO> findOne(Long id) {
        log.debug("Request to get Feed : {}", id);
        return feedRepository.findById(id)
            .map(feedMapper::toDto);
    }

    /**
     * Delete the feed by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Feed : {}", id);
        feedRepository.deleteById(id);
        feedSearchRepository.deleteById(id);
    }

    /**
     * Search for the feed corresponding to the query.
     *
     * @param query the query of the search.
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<FeedDTO> search(String query, Pageable pageable) {
        log.debug("Request to search for a page of Feeds for query {}", query);
        return feedSearchRepository.search(queryStringQuery(query), pageable)
            .map(feedMapper::toDto);
    }
}
