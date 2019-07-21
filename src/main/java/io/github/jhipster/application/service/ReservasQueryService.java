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

import io.github.jhipster.application.domain.Reservas;
import io.github.jhipster.application.domain.*; // for static metamodels
import io.github.jhipster.application.repository.ReservasRepository;
import io.github.jhipster.application.repository.search.ReservasSearchRepository;
import io.github.jhipster.application.service.dto.ReservasCriteria;
import io.github.jhipster.application.service.dto.ReservasDTO;
import io.github.jhipster.application.service.mapper.ReservasMapper;

/**
 * Service for executing complex queries for {@link Reservas} entities in the database.
 * The main input is a {@link ReservasCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link ReservasDTO} or a {@link Page} of {@link ReservasDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class ReservasQueryService extends QueryService<Reservas> {

    private final Logger log = LoggerFactory.getLogger(ReservasQueryService.class);

    private final ReservasRepository reservasRepository;

    private final ReservasMapper reservasMapper;

    private final ReservasSearchRepository reservasSearchRepository;

    public ReservasQueryService(ReservasRepository reservasRepository, ReservasMapper reservasMapper, ReservasSearchRepository reservasSearchRepository) {
        this.reservasRepository = reservasRepository;
        this.reservasMapper = reservasMapper;
        this.reservasSearchRepository = reservasSearchRepository;
    }

    /**
     * Return a {@link List} of {@link ReservasDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<ReservasDTO> findByCriteria(ReservasCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<Reservas> specification = createSpecification(criteria);
        return reservasMapper.toDto(reservasRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link ReservasDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<ReservasDTO> findByCriteria(ReservasCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<Reservas> specification = createSpecification(criteria);
        return reservasRepository.findAll(specification, page)
            .map(reservasMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(ReservasCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<Reservas> specification = createSpecification(criteria);
        return reservasRepository.count(specification);
    }

    /**
     * Function to convert ConsumerCriteria to a {@link Specification}
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */    
    private Specification<Reservas> createSpecification(ReservasCriteria criteria) {
        Specification<Reservas> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), Reservas_.id));
            }
            if (criteria.getRegistroFechaEntrada() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getRegistroFechaEntrada(), Reservas_.registroFechaEntrada));
            }
            if (criteria.getRegistroFechaSalida() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getRegistroFechaSalida(), Reservas_.registroFechaSalida));
            }
            if (criteria.getEstadoReserva() != null) {
                specification = specification.and(buildSpecification(criteria.getEstadoReserva(), Reservas_.estadoReserva));
            }
            if (criteria.getRegistroCompraId() != null) {
                specification = specification.and(buildSpecification(criteria.getRegistroCompraId(),
                    root -> root.join(Reservas_.registroCompras, JoinType.LEFT).get(RegistroCompra_.id)));
            }
            if (criteria.getMiembroId() != null) {
                specification = specification.and(buildSpecification(criteria.getMiembroId(),
                    root -> root.join(Reservas_.miembro, JoinType.LEFT).get(User_.id)));
            }
            if (criteria.getEspacioId() != null) {
                specification = specification.and(buildSpecification(criteria.getEspacioId(),
                    root -> root.join(Reservas_.espacio, JoinType.LEFT).get(EspaciosReserva_.id)));
            }
        }
        return specification;
    }
}
