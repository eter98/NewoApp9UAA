package io.github.jhipster.application.service.impl;

import io.github.jhipster.application.service.LandingService;
import io.github.jhipster.application.domain.Landing;
import io.github.jhipster.application.repository.LandingRepository;
import io.github.jhipster.application.repository.search.LandingSearchRepository;
import io.github.jhipster.application.service.dto.LandingDTO;
import io.github.jhipster.application.service.mapper.LandingMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.elasticsearch.index.query.QueryBuilders.*;

/**
 * Service Implementation for managing {@link Landing}.
 */
@Service
@Transactional
public class LandingServiceImpl implements LandingService {

    private final Logger log = LoggerFactory.getLogger(LandingServiceImpl.class);

    private final LandingRepository landingRepository;

    private final LandingMapper landingMapper;

    private final LandingSearchRepository landingSearchRepository;

    public LandingServiceImpl(LandingRepository landingRepository, LandingMapper landingMapper, LandingSearchRepository landingSearchRepository) {
        this.landingRepository = landingRepository;
        this.landingMapper = landingMapper;
        this.landingSearchRepository = landingSearchRepository;
    }

    /**
     * Save a landing.
     *
     * @param landingDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public LandingDTO save(LandingDTO landingDTO) {
        log.debug("Request to save Landing : {}", landingDTO);
        Landing landing = landingMapper.toEntity(landingDTO);
        landing = landingRepository.save(landing);
        LandingDTO result = landingMapper.toDto(landing);
        landingSearchRepository.save(landing);
        return result;
    }

    /**
     * Get all the landings.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<LandingDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Landings");
        return landingRepository.findAll(pageable)
            .map(landingMapper::toDto);
    }


    /**
     * Get one landing by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<LandingDTO> findOne(Long id) {
        log.debug("Request to get Landing : {}", id);
        return landingRepository.findById(id)
            .map(landingMapper::toDto);
    }

    /**
     * Delete the landing by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Landing : {}", id);
        landingRepository.deleteById(id);
        landingSearchRepository.deleteById(id);
    }

    /**
     * Search for the landing corresponding to the query.
     *
     * @param query the query of the search.
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<LandingDTO> search(String query, Pageable pageable) {
        log.debug("Request to search for a page of Landings for query {}", query);
        return landingSearchRepository.search(queryStringQuery(query), pageable)
            .map(landingMapper::toDto);
    }
}
