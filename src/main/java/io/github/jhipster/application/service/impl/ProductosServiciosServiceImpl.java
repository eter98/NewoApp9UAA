package io.github.jhipster.application.service.impl;

import io.github.jhipster.application.service.ProductosServiciosService;
import io.github.jhipster.application.domain.ProductosServicios;
import io.github.jhipster.application.repository.ProductosServiciosRepository;
import io.github.jhipster.application.repository.search.ProductosServiciosSearchRepository;
import io.github.jhipster.application.service.dto.ProductosServiciosDTO;
import io.github.jhipster.application.service.mapper.ProductosServiciosMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.elasticsearch.index.query.QueryBuilders.*;

/**
 * Service Implementation for managing {@link ProductosServicios}.
 */
@Service
@Transactional
public class ProductosServiciosServiceImpl implements ProductosServiciosService {

    private final Logger log = LoggerFactory.getLogger(ProductosServiciosServiceImpl.class);

    private final ProductosServiciosRepository productosServiciosRepository;

    private final ProductosServiciosMapper productosServiciosMapper;

    private final ProductosServiciosSearchRepository productosServiciosSearchRepository;

    public ProductosServiciosServiceImpl(ProductosServiciosRepository productosServiciosRepository, ProductosServiciosMapper productosServiciosMapper, ProductosServiciosSearchRepository productosServiciosSearchRepository) {
        this.productosServiciosRepository = productosServiciosRepository;
        this.productosServiciosMapper = productosServiciosMapper;
        this.productosServiciosSearchRepository = productosServiciosSearchRepository;
    }

    /**
     * Save a productosServicios.
     *
     * @param productosServiciosDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public ProductosServiciosDTO save(ProductosServiciosDTO productosServiciosDTO) {
        log.debug("Request to save ProductosServicios : {}", productosServiciosDTO);
        ProductosServicios productosServicios = productosServiciosMapper.toEntity(productosServiciosDTO);
        productosServicios = productosServiciosRepository.save(productosServicios);
        ProductosServiciosDTO result = productosServiciosMapper.toDto(productosServicios);
        productosServiciosSearchRepository.save(productosServicios);
        return result;
    }

    /**
     * Get all the productosServicios.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<ProductosServiciosDTO> findAll(Pageable pageable) {
        log.debug("Request to get all ProductosServicios");
        return productosServiciosRepository.findAll(pageable)
            .map(productosServiciosMapper::toDto);
    }


    /**
     * Get one productosServicios by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<ProductosServiciosDTO> findOne(Long id) {
        log.debug("Request to get ProductosServicios : {}", id);
        return productosServiciosRepository.findById(id)
            .map(productosServiciosMapper::toDto);
    }

    /**
     * Delete the productosServicios by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete ProductosServicios : {}", id);
        productosServiciosRepository.deleteById(id);
        productosServiciosSearchRepository.deleteById(id);
    }

    /**
     * Search for the productosServicios corresponding to the query.
     *
     * @param query the query of the search.
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<ProductosServiciosDTO> search(String query, Pageable pageable) {
        log.debug("Request to search for a page of ProductosServicios for query {}", query);
        return productosServiciosSearchRepository.search(queryStringQuery(query), pageable)
            .map(productosServiciosMapper::toDto);
    }
}
