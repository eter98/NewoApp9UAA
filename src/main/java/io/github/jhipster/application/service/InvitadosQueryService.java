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

import io.github.jhipster.application.domain.Invitados;
import io.github.jhipster.application.domain.*; // for static metamodels
import io.github.jhipster.application.repository.InvitadosRepository;
import io.github.jhipster.application.repository.search.InvitadosSearchRepository;
import io.github.jhipster.application.service.dto.InvitadosCriteria;
import io.github.jhipster.application.service.dto.InvitadosDTO;
import io.github.jhipster.application.service.mapper.InvitadosMapper;

/**
 * Service for executing complex queries for {@link Invitados} entities in the database.
 * The main input is a {@link InvitadosCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link InvitadosDTO} or a {@link Page} of {@link InvitadosDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class InvitadosQueryService extends QueryService<Invitados> {

    private final Logger log = LoggerFactory.getLogger(InvitadosQueryService.class);

    private final InvitadosRepository invitadosRepository;

    private final InvitadosMapper invitadosMapper;

    private final InvitadosSearchRepository invitadosSearchRepository;

    public InvitadosQueryService(InvitadosRepository invitadosRepository, InvitadosMapper invitadosMapper, InvitadosSearchRepository invitadosSearchRepository) {
        this.invitadosRepository = invitadosRepository;
        this.invitadosMapper = invitadosMapper;
        this.invitadosSearchRepository = invitadosSearchRepository;
    }

    /**
     * Return a {@link List} of {@link InvitadosDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<InvitadosDTO> findByCriteria(InvitadosCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<Invitados> specification = createSpecification(criteria);
        return invitadosMapper.toDto(invitadosRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link InvitadosDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<InvitadosDTO> findByCriteria(InvitadosCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<Invitados> specification = createSpecification(criteria);
        return invitadosRepository.findAll(specification, page)
            .map(invitadosMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(InvitadosCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<Invitados> specification = createSpecification(criteria);
        return invitadosRepository.count(specification);
    }

    /**
     * Function to convert ConsumerCriteria to a {@link Specification}
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */    
    private Specification<Invitados> createSpecification(InvitadosCriteria criteria) {
        Specification<Invitados> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), Invitados_.id));
            }
            if (criteria.getNombre() != null) {
                specification = specification.and(buildStringSpecification(criteria.getNombre(), Invitados_.nombre));
            }
            if (criteria.getApellido() != null) {
                specification = specification.and(buildStringSpecification(criteria.getApellido(), Invitados_.apellido));
            }
            if (criteria.getTipoDocumento() != null) {
                specification = specification.and(buildSpecification(criteria.getTipoDocumento(), Invitados_.tipoDocumento));
            }
            if (criteria.getIdentificacion() != null) {
                specification = specification.and(buildStringSpecification(criteria.getIdentificacion(), Invitados_.identificacion));
            }
            if (criteria.getCorreo() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCorreo(), Invitados_.correo));
            }
            if (criteria.getTelefono() != null) {
                specification = specification.and(buildStringSpecification(criteria.getTelefono(), Invitados_.telefono));
            }
            if (criteria.getMiembroId() != null) {
                specification = specification.and(buildSpecification(criteria.getMiembroId(),
                    root -> root.join(Invitados_.miembro, JoinType.LEFT).get(User_.id)));
            }
        }
        return specification;
    }
}
