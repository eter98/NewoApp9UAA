package io.github.jhipster.application.service.impl;

import io.github.jhipster.application.service.BeneficioService;
import io.github.jhipster.application.domain.Beneficio;
import io.github.jhipster.application.repository.BeneficioRepository;
import io.github.jhipster.application.repository.search.BeneficioSearchRepository;
import io.github.jhipster.application.service.dto.BeneficioDTO;
import io.github.jhipster.application.service.mapper.BeneficioMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.elasticsearch.index.query.QueryBuilders.*;

/**
 * Service Implementation for managing {@link Beneficio}.
 */
@Service
@Transactional
public class BeneficioServiceImpl implements BeneficioService {

    private final Logger log = LoggerFactory.getLogger(BeneficioServiceImpl.class);

    private final BeneficioRepository beneficioRepository;

    private final BeneficioMapper beneficioMapper;

    private final BeneficioSearchRepository beneficioSearchRepository;

    public BeneficioServiceImpl(BeneficioRepository beneficioRepository, BeneficioMapper beneficioMapper, BeneficioSearchRepository beneficioSearchRepository) {
        this.beneficioRepository = beneficioRepository;
        this.beneficioMapper = beneficioMapper;
        this.beneficioSearchRepository = beneficioSearchRepository;
    }

    /**
     * Save a beneficio.
     *
     * @param beneficioDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public BeneficioDTO save(BeneficioDTO beneficioDTO) {
        log.debug("Request to save Beneficio : {}", beneficioDTO);
        Beneficio beneficio = beneficioMapper.toEntity(beneficioDTO);
        beneficio = beneficioRepository.save(beneficio);
        BeneficioDTO result = beneficioMapper.toDto(beneficio);
        beneficioSearchRepository.save(beneficio);
        return result;
    }

    /**
     * Get all the beneficios.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<BeneficioDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Beneficios");
        return beneficioRepository.findAll(pageable)
            .map(beneficioMapper::toDto);
    }


    /**
     * Get one beneficio by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<BeneficioDTO> findOne(Long id) {
        log.debug("Request to get Beneficio : {}", id);
        return beneficioRepository.findById(id)
            .map(beneficioMapper::toDto);
    }

    /**
     * Delete the beneficio by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Beneficio : {}", id);
        beneficioRepository.deleteById(id);
        beneficioSearchRepository.deleteById(id);
    }

    /**
     * Search for the beneficio corresponding to the query.
     *
     * @param query the query of the search.
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<BeneficioDTO> search(String query, Pageable pageable) {
        log.debug("Request to search for a page of Beneficios for query {}", query);
        return beneficioSearchRepository.search(queryStringQuery(query), pageable)
            .map(beneficioMapper::toDto);
    }
}
