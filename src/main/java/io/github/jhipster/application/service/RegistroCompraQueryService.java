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

import io.github.jhipster.application.domain.RegistroCompra;
import io.github.jhipster.application.domain.*; // for static metamodels
import io.github.jhipster.application.repository.RegistroCompraRepository;
import io.github.jhipster.application.repository.search.RegistroCompraSearchRepository;
import io.github.jhipster.application.service.dto.RegistroCompraCriteria;
import io.github.jhipster.application.service.dto.RegistroCompraDTO;
import io.github.jhipster.application.service.mapper.RegistroCompraMapper;

/**
 * Service for executing complex queries for {@link RegistroCompra} entities in the database.
 * The main input is a {@link RegistroCompraCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link RegistroCompraDTO} or a {@link Page} of {@link RegistroCompraDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class RegistroCompraQueryService extends QueryService<RegistroCompra> {

    private final Logger log = LoggerFactory.getLogger(RegistroCompraQueryService.class);

    private final RegistroCompraRepository registroCompraRepository;

    private final RegistroCompraMapper registroCompraMapper;

    private final RegistroCompraSearchRepository registroCompraSearchRepository;

    public RegistroCompraQueryService(RegistroCompraRepository registroCompraRepository, RegistroCompraMapper registroCompraMapper, RegistroCompraSearchRepository registroCompraSearchRepository) {
        this.registroCompraRepository = registroCompraRepository;
        this.registroCompraMapper = registroCompraMapper;
        this.registroCompraSearchRepository = registroCompraSearchRepository;
    }

    /**
     * Return a {@link List} of {@link RegistroCompraDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<RegistroCompraDTO> findByCriteria(RegistroCompraCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<RegistroCompra> specification = createSpecification(criteria);
        return registroCompraMapper.toDto(registroCompraRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link RegistroCompraDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<RegistroCompraDTO> findByCriteria(RegistroCompraCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<RegistroCompra> specification = createSpecification(criteria);
        return registroCompraRepository.findAll(specification, page)
            .map(registroCompraMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(RegistroCompraCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<RegistroCompra> specification = createSpecification(criteria);
        return registroCompraRepository.count(specification);
    }

    /**
     * Function to convert ConsumerCriteria to a {@link Specification}
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */    
    private Specification<RegistroCompra> createSpecification(RegistroCompraCriteria criteria) {
        Specification<RegistroCompra> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), RegistroCompra_.id));
            }
            if (criteria.getConsumoMarket() != null) {
                specification = specification.and(buildSpecification(criteria.getConsumoMarket(), RegistroCompra_.consumoMarket));
            }
            if (criteria.getMiembroId() != null) {
                specification = specification.and(buildSpecification(criteria.getMiembroId(),
                    root -> root.join(RegistroCompra_.miembro, JoinType.LEFT).get(User_.id)));
            }
            if (criteria.getFacturacionId() != null) {
                specification = specification.and(buildSpecification(criteria.getFacturacionId(),
                    root -> root.join(RegistroCompra_.facturacion, JoinType.LEFT).get(Facturacion_.id)));
            }
            if (criteria.getReservasId() != null) {
                specification = specification.and(buildSpecification(criteria.getReservasId(),
                    root -> root.join(RegistroCompra_.reservas, JoinType.LEFT).get(Reservas_.id)));
            }
            if (criteria.getEntradaInvitadosId() != null) {
                specification = specification.and(buildSpecification(criteria.getEntradaInvitadosId(),
                    root -> root.join(RegistroCompra_.entradaInvitados, JoinType.LEFT).get(EntradaInvitados_.id)));
            }
            if (criteria.getEntradaMiembrosId() != null) {
                specification = specification.and(buildSpecification(criteria.getEntradaMiembrosId(),
                    root -> root.join(RegistroCompra_.entradaMiembros, JoinType.LEFT).get(EntradaMiembros_.id)));
            }
        }
        return specification;
    }
}
