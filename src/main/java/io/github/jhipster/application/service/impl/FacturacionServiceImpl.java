package io.github.jhipster.application.service.impl;

import io.github.jhipster.application.service.FacturacionService;
import io.github.jhipster.application.domain.Facturacion;
import io.github.jhipster.application.repository.FacturacionRepository;
import io.github.jhipster.application.repository.search.FacturacionSearchRepository;
import io.github.jhipster.application.service.dto.FacturacionDTO;
import io.github.jhipster.application.service.mapper.FacturacionMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.elasticsearch.index.query.QueryBuilders.*;

/**
 * Service Implementation for managing {@link Facturacion}.
 */
@Service
@Transactional
public class FacturacionServiceImpl implements FacturacionService {

    private final Logger log = LoggerFactory.getLogger(FacturacionServiceImpl.class);

    private final FacturacionRepository facturacionRepository;

    private final FacturacionMapper facturacionMapper;

    private final FacturacionSearchRepository facturacionSearchRepository;

    public FacturacionServiceImpl(FacturacionRepository facturacionRepository, FacturacionMapper facturacionMapper, FacturacionSearchRepository facturacionSearchRepository) {
        this.facturacionRepository = facturacionRepository;
        this.facturacionMapper = facturacionMapper;
        this.facturacionSearchRepository = facturacionSearchRepository;
    }

    /**
     * Save a facturacion.
     *
     * @param facturacionDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public FacturacionDTO save(FacturacionDTO facturacionDTO) {
        log.debug("Request to save Facturacion : {}", facturacionDTO);
        Facturacion facturacion = facturacionMapper.toEntity(facturacionDTO);
        facturacion = facturacionRepository.save(facturacion);
        FacturacionDTO result = facturacionMapper.toDto(facturacion);
        facturacionSearchRepository.save(facturacion);
        return result;
    }

    /**
     * Get all the facturacions.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<FacturacionDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Facturacions");
        return facturacionRepository.findAll(pageable)
            .map(facturacionMapper::toDto);
    }


    /**
     * Get one facturacion by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<FacturacionDTO> findOne(Long id) {
        log.debug("Request to get Facturacion : {}", id);
        return facturacionRepository.findById(id)
            .map(facturacionMapper::toDto);
    }

    /**
     * Delete the facturacion by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Facturacion : {}", id);
        facturacionRepository.deleteById(id);
        facturacionSearchRepository.deleteById(id);
    }

    /**
     * Search for the facturacion corresponding to the query.
     *
     * @param query the query of the search.
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<FacturacionDTO> search(String query, Pageable pageable) {
        log.debug("Request to search for a page of Facturacions for query {}", query);
        return facturacionSearchRepository.search(queryStringQuery(query), pageable)
            .map(facturacionMapper::toDto);
    }
}
