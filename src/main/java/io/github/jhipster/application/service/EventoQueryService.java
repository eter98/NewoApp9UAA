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

import io.github.jhipster.application.domain.Evento;
import io.github.jhipster.application.domain.*; // for static metamodels
import io.github.jhipster.application.repository.EventoRepository;
import io.github.jhipster.application.repository.search.EventoSearchRepository;
import io.github.jhipster.application.service.dto.EventoCriteria;
import io.github.jhipster.application.service.dto.EventoDTO;
import io.github.jhipster.application.service.mapper.EventoMapper;

/**
 * Service for executing complex queries for {@link Evento} entities in the database.
 * The main input is a {@link EventoCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link EventoDTO} or a {@link Page} of {@link EventoDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class EventoQueryService extends QueryService<Evento> {

    private final Logger log = LoggerFactory.getLogger(EventoQueryService.class);

    private final EventoRepository eventoRepository;

    private final EventoMapper eventoMapper;

    private final EventoSearchRepository eventoSearchRepository;

    public EventoQueryService(EventoRepository eventoRepository, EventoMapper eventoMapper, EventoSearchRepository eventoSearchRepository) {
        this.eventoRepository = eventoRepository;
        this.eventoMapper = eventoMapper;
        this.eventoSearchRepository = eventoSearchRepository;
    }

    /**
     * Return a {@link List} of {@link EventoDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<EventoDTO> findByCriteria(EventoCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<Evento> specification = createSpecification(criteria);
        return eventoMapper.toDto(eventoRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link EventoDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<EventoDTO> findByCriteria(EventoCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<Evento> specification = createSpecification(criteria);
        return eventoRepository.findAll(specification, page)
            .map(eventoMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(EventoCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<Evento> specification = createSpecification(criteria);
        return eventoRepository.count(specification);
    }

    /**
     * Function to convert ConsumerCriteria to a {@link Specification}
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */    
    private Specification<Evento> createSpecification(EventoCriteria criteria) {
        Specification<Evento> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), Evento_.id));
            }
            if (criteria.getNombreEvento() != null) {
                specification = specification.and(buildStringSpecification(criteria.getNombreEvento(), Evento_.nombreEvento));
            }
            if (criteria.getDescripcion() != null) {
                specification = specification.and(buildStringSpecification(criteria.getDescripcion(), Evento_.descripcion));
            }
            if (criteria.getTipoConsumo() != null) {
                specification = specification.and(buildSpecification(criteria.getTipoConsumo(), Evento_.tipoConsumo));
            }
            if (criteria.getValor() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getValor(), Evento_.valor));
            }
            if (criteria.getImpuesto() != null) {
                specification = specification.and(buildSpecification(criteria.getImpuesto(), Evento_.impuesto));
            }
            if (criteria.getTipoEvento() != null) {
                specification = specification.and(buildSpecification(criteria.getTipoEvento(), Evento_.tipoEvento));
            }
            if (criteria.getCategoriaEventoId() != null) {
                specification = specification.and(buildSpecification(criteria.getCategoriaEventoId(),
                    root -> root.join(Evento_.categoriaEvento, JoinType.LEFT).get(CategoriaContenidos_.id)));
            }
            if (criteria.getGruposId() != null) {
                specification = specification.and(buildSpecification(criteria.getGruposId(),
                    root -> root.join(Evento_.grupos, JoinType.LEFT).get(Grupos_.id)));
            }
            if (criteria.getMiembroId() != null) {
                specification = specification.and(buildSpecification(criteria.getMiembroId(),
                    root -> root.join(Evento_.miembro, JoinType.LEFT).get(User_.id)));
            }
        }
        return specification;
    }
}
