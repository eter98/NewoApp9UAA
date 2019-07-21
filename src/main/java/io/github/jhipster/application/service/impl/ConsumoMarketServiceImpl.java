package io.github.jhipster.application.service.impl;

import io.github.jhipster.application.service.ConsumoMarketService;
import io.github.jhipster.application.domain.ConsumoMarket;
import io.github.jhipster.application.repository.ConsumoMarketRepository;
import io.github.jhipster.application.repository.search.ConsumoMarketSearchRepository;
import io.github.jhipster.application.service.dto.ConsumoMarketDTO;
import io.github.jhipster.application.service.mapper.ConsumoMarketMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.elasticsearch.index.query.QueryBuilders.*;

/**
 * Service Implementation for managing {@link ConsumoMarket}.
 */
@Service
@Transactional
public class ConsumoMarketServiceImpl implements ConsumoMarketService {

    private final Logger log = LoggerFactory.getLogger(ConsumoMarketServiceImpl.class);

    private final ConsumoMarketRepository consumoMarketRepository;

    private final ConsumoMarketMapper consumoMarketMapper;

    private final ConsumoMarketSearchRepository consumoMarketSearchRepository;

    public ConsumoMarketServiceImpl(ConsumoMarketRepository consumoMarketRepository, ConsumoMarketMapper consumoMarketMapper, ConsumoMarketSearchRepository consumoMarketSearchRepository) {
        this.consumoMarketRepository = consumoMarketRepository;
        this.consumoMarketMapper = consumoMarketMapper;
        this.consumoMarketSearchRepository = consumoMarketSearchRepository;
    }

    /**
     * Save a consumoMarket.
     *
     * @param consumoMarketDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public ConsumoMarketDTO save(ConsumoMarketDTO consumoMarketDTO) {
        log.debug("Request to save ConsumoMarket : {}", consumoMarketDTO);
        ConsumoMarket consumoMarket = consumoMarketMapper.toEntity(consumoMarketDTO);
        consumoMarket = consumoMarketRepository.save(consumoMarket);
        ConsumoMarketDTO result = consumoMarketMapper.toDto(consumoMarket);
        consumoMarketSearchRepository.save(consumoMarket);
        return result;
    }

    /**
     * Get all the consumoMarkets.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<ConsumoMarketDTO> findAll(Pageable pageable) {
        log.debug("Request to get all ConsumoMarkets");
        return consumoMarketRepository.findAll(pageable)
            .map(consumoMarketMapper::toDto);
    }


    /**
     * Get one consumoMarket by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<ConsumoMarketDTO> findOne(Long id) {
        log.debug("Request to get ConsumoMarket : {}", id);
        return consumoMarketRepository.findById(id)
            .map(consumoMarketMapper::toDto);
    }

    /**
     * Delete the consumoMarket by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete ConsumoMarket : {}", id);
        consumoMarketRepository.deleteById(id);
        consumoMarketSearchRepository.deleteById(id);
    }

    /**
     * Search for the consumoMarket corresponding to the query.
     *
     * @param query the query of the search.
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<ConsumoMarketDTO> search(String query, Pageable pageable) {
        log.debug("Request to search for a page of ConsumoMarkets for query {}", query);
        return consumoMarketSearchRepository.search(queryStringQuery(query), pageable)
            .map(consumoMarketMapper::toDto);
    }
}
