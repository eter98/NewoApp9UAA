package io.github.jhipster.application.service.impl;

import io.github.jhipster.application.service.CiudadService;
import io.github.jhipster.application.domain.Ciudad;
import io.github.jhipster.application.repository.CiudadRepository;
import io.github.jhipster.application.repository.search.CiudadSearchRepository;
import io.github.jhipster.application.service.dto.CiudadDTO;
import io.github.jhipster.application.service.mapper.CiudadMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.elasticsearch.index.query.QueryBuilders.*;

/**
 * Service Implementation for managing {@link Ciudad}.
 */
@Service
@Transactional
public class CiudadServiceImpl implements CiudadService {

    private final Logger log = LoggerFactory.getLogger(CiudadServiceImpl.class);

    private final CiudadRepository ciudadRepository;

    private final CiudadMapper ciudadMapper;

    private final CiudadSearchRepository ciudadSearchRepository;

    public CiudadServiceImpl(CiudadRepository ciudadRepository, CiudadMapper ciudadMapper, CiudadSearchRepository ciudadSearchRepository) {
        this.ciudadRepository = ciudadRepository;
        this.ciudadMapper = ciudadMapper;
        this.ciudadSearchRepository = ciudadSearchRepository;
    }

    /**
     * Save a ciudad.
     *
     * @param ciudadDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public CiudadDTO save(CiudadDTO ciudadDTO) {
        log.debug("Request to save Ciudad : {}", ciudadDTO);
        Ciudad ciudad = ciudadMapper.toEntity(ciudadDTO);
        ciudad = ciudadRepository.save(ciudad);
        CiudadDTO result = ciudadMapper.toDto(ciudad);
        ciudadSearchRepository.save(ciudad);
        return result;
    }

    /**
     * Get all the ciudads.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<CiudadDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Ciudads");
        return ciudadRepository.findAll(pageable)
            .map(ciudadMapper::toDto);
    }


    /**
     * Get one ciudad by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<CiudadDTO> findOne(Long id) {
        log.debug("Request to get Ciudad : {}", id);
        return ciudadRepository.findById(id)
            .map(ciudadMapper::toDto);
    }

    /**
     * Delete the ciudad by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Ciudad : {}", id);
        ciudadRepository.deleteById(id);
        ciudadSearchRepository.deleteById(id);
    }

    /**
     * Search for the ciudad corresponding to the query.
     *
     * @param query the query of the search.
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<CiudadDTO> search(String query, Pageable pageable) {
        log.debug("Request to search for a page of Ciudads for query {}", query);
        return ciudadSearchRepository.search(queryStringQuery(query), pageable)
            .map(ciudadMapper::toDto);
    }
}
