package io.github.jhipster.application.service;

import java.util.List;

import javax.persistence.criteria.JoinType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import io.github.jhipster.service.QueryService;

import io.github.jhipster.application.domain.MargenNewoProductos;
import io.github.jhipster.application.domain.*; // for static metamodels
import io.github.jhipster.application.repository.MargenNewoProductosRepository;
import io.github.jhipster.application.repository.search.MargenNewoProductosSearchRepository;
import io.github.jhipster.application.service.dto.MargenNewoProductosCriteria;
import io.github.jhipster.application.service.dto.MargenNewoProductosDTO;
import io.github.jhipster.application.service.mapper.MargenNewoProductosMapper;

/**
 * Service for executing complex queries for {@link MargenNewoProductos} entities in the database.
 * The main input is a {@link MargenNewoProductosCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link MargenNewoProductosDTO} or a {@link Page} of {@link MargenNewoProductosDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class MargenNewoProductosQueryService extends QueryService<MargenNewoProductos> {

    private final Logger log = LoggerFactory.getLogger(MargenNewoProductosQueryService.class);

    private final MargenNewoProductosRepository margenNewoProductosRepository;

    private final MargenNewoProductosMapper margenNewoProductosMapper;

    private final MargenNewoProductosSearchRepository margenNewoProductosSearchRepository;

    public MargenNewoProductosQueryService(MargenNewoProductosRepository margenNewoProductosRepository, MargenNewoProductosMapper margenNewoProductosMapper, MargenNewoProductosSearchRepository margenNewoProductosSearchRepository) {
        this.margenNewoProductosRepository = margenNewoProductosRepository;
        this.margenNewoProductosMapper = margenNewoProductosMapper;
        this.margenNewoProductosSearchRepository = margenNewoProductosSearchRepository;
    }

    /**
     * Return a {@link List} of {@link MargenNewoProductosDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<MargenNewoProductosDTO> findByCriteria(MargenNewoProductosCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<MargenNewoProductos> specification = createSpecification(criteria);
        return margenNewoProductosMapper.toDto(margenNewoProductosRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link MargenNewoProductosDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<MargenNewoProductosDTO> findByCriteria(MargenNewoProductosCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<MargenNewoProductos> specification = createSpecification(criteria);
        return margenNewoProductosRepository.findAll(specification, page)
            .map(margenNewoProductosMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(MargenNewoProductosCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<MargenNewoProductos> specification = createSpecification(criteria);
        return margenNewoProductosRepository.count(specification);
    }

    /**
     * Function to convert ConsumerCriteria to a {@link Specification}
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */    
    private Specification<MargenNewoProductos> createSpecification(MargenNewoProductosCriteria criteria) {
        Specification<MargenNewoProductos> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), MargenNewoProductos_.id));
            }
            if (criteria.getPorcentajeMargen() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getPorcentajeMargen(), MargenNewoProductos_.porcentajeMargen));
            }
            if (criteria.getProductoId() != null) {
                specification = specification.and(buildSpecification(criteria.getProductoId(),
                    root -> root.join(MargenNewoProductos_.producto, JoinType.LEFT).get(ProductosServicios_.id)));
            }
        }
        return specification;
    }
}
