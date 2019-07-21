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

import io.github.jhipster.application.domain.MargenNewoEventos;
import io.github.jhipster.application.domain.*; // for static metamodels
import io.github.jhipster.application.repository.MargenNewoEventosRepository;
import io.github.jhipster.application.repository.search.MargenNewoEventosSearchRepository;
import io.github.jhipster.application.service.dto.MargenNewoEventosCriteria;
import io.github.jhipster.application.service.dto.MargenNewoEventosDTO;
import io.github.jhipster.application.service.mapper.MargenNewoEventosMapper;

/**
 * Service for executing complex queries for {@link MargenNewoEventos} entities in the database.
 * The main input is a {@link MargenNewoEventosCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link MargenNewoEventosDTO} or a {@link Page} of {@link MargenNewoEventosDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class MargenNewoEventosQueryService extends QueryService<MargenNewoEventos> {

    private final Logger log = LoggerFactory.getLogger(MargenNewoEventosQueryService.class);

    private final MargenNewoEventosRepository margenNewoEventosRepository;

    private final MargenNewoEventosMapper margenNewoEventosMapper;

    private final MargenNewoEventosSearchRepository margenNewoEventosSearchRepository;

    public MargenNewoEventosQueryService(MargenNewoEventosRepository margenNewoEventosRepository, MargenNewoEventosMapper margenNewoEventosMapper, MargenNewoEventosSearchRepository margenNewoEventosSearchRepository) {
        this.margenNewoEventosRepository = margenNewoEventosRepository;
        this.margenNewoEventosMapper = margenNewoEventosMapper;
        this.margenNewoEventosSearchRepository = margenNewoEventosSearchRepository;
    }

    /**
     * Return a {@link List} of {@link MargenNewoEventosDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<MargenNewoEventosDTO> findByCriteria(MargenNewoEventosCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<MargenNewoEventos> specification = createSpecification(criteria);
        return margenNewoEventosMapper.toDto(margenNewoEventosRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link MargenNewoEventosDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<MargenNewoEventosDTO> findByCriteria(MargenNewoEventosCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<MargenNewoEventos> specification = createSpecification(criteria);
        return margenNewoEventosRepository.findAll(specification, page)
            .map(margenNewoEventosMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(MargenNewoEventosCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<MargenNewoEventos> specification = createSpecification(criteria);
        return margenNewoEventosRepository.count(specification);
    }

    /**
     * Function to convert ConsumerCriteria to a {@link Specification}
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */    
    private Specification<MargenNewoEventos> createSpecification(MargenNewoEventosCriteria criteria) {
        Specification<MargenNewoEventos> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), MargenNewoEventos_.id));
            }
            if (criteria.getPorcentajeMargen() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getPorcentajeMargen(), MargenNewoEventos_.porcentajeMargen));
            }
            if (criteria.getEventoId() != null) {
                specification = specification.and(buildSpecification(criteria.getEventoId(),
                    root -> root.join(MargenNewoEventos_.evento, JoinType.LEFT).get(Evento_.id)));
            }
        }
        return specification;
    }
}
