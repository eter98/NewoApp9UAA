package io.github.jhipster.application.service.impl;

import io.github.jhipster.application.service.CuentaAsociadaService;
import io.github.jhipster.application.domain.CuentaAsociada;
import io.github.jhipster.application.repository.CuentaAsociadaRepository;
import io.github.jhipster.application.repository.search.CuentaAsociadaSearchRepository;
import io.github.jhipster.application.service.dto.CuentaAsociadaDTO;
import io.github.jhipster.application.service.mapper.CuentaAsociadaMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.elasticsearch.index.query.QueryBuilders.*;

/**
 * Service Implementation for managing {@link CuentaAsociada}.
 */
@Service
@Transactional
public class CuentaAsociadaServiceImpl implements CuentaAsociadaService {

    private final Logger log = LoggerFactory.getLogger(CuentaAsociadaServiceImpl.class);

    private final CuentaAsociadaRepository cuentaAsociadaRepository;

    private final CuentaAsociadaMapper cuentaAsociadaMapper;

    private final CuentaAsociadaSearchRepository cuentaAsociadaSearchRepository;

    public CuentaAsociadaServiceImpl(CuentaAsociadaRepository cuentaAsociadaRepository, CuentaAsociadaMapper cuentaAsociadaMapper, CuentaAsociadaSearchRepository cuentaAsociadaSearchRepository) {
        this.cuentaAsociadaRepository = cuentaAsociadaRepository;
        this.cuentaAsociadaMapper = cuentaAsociadaMapper;
        this.cuentaAsociadaSearchRepository = cuentaAsociadaSearchRepository;
    }

    /**
     * Save a cuentaAsociada.
     *
     * @param cuentaAsociadaDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public CuentaAsociadaDTO save(CuentaAsociadaDTO cuentaAsociadaDTO) {
        log.debug("Request to save CuentaAsociada : {}", cuentaAsociadaDTO);
        CuentaAsociada cuentaAsociada = cuentaAsociadaMapper.toEntity(cuentaAsociadaDTO);
        cuentaAsociada = cuentaAsociadaRepository.save(cuentaAsociada);
        CuentaAsociadaDTO result = cuentaAsociadaMapper.toDto(cuentaAsociada);
        cuentaAsociadaSearchRepository.save(cuentaAsociada);
        return result;
    }

    /**
     * Get all the cuentaAsociadas.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<CuentaAsociadaDTO> findAll(Pageable pageable) {
        log.debug("Request to get all CuentaAsociadas");
        return cuentaAsociadaRepository.findAll(pageable)
            .map(cuentaAsociadaMapper::toDto);
    }


    /**
     * Get one cuentaAsociada by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<CuentaAsociadaDTO> findOne(Long id) {
        log.debug("Request to get CuentaAsociada : {}", id);
        return cuentaAsociadaRepository.findById(id)
            .map(cuentaAsociadaMapper::toDto);
    }

    /**
     * Delete the cuentaAsociada by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete CuentaAsociada : {}", id);
        cuentaAsociadaRepository.deleteById(id);
        cuentaAsociadaSearchRepository.deleteById(id);
    }

    /**
     * Search for the cuentaAsociada corresponding to the query.
     *
     * @param query the query of the search.
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<CuentaAsociadaDTO> search(String query, Pageable pageable) {
        log.debug("Request to search for a page of CuentaAsociadas for query {}", query);
        return cuentaAsociadaSearchRepository.search(queryStringQuery(query), pageable)
            .map(cuentaAsociadaMapper::toDto);
    }
}
