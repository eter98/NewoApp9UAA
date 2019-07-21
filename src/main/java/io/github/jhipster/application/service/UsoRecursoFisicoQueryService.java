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

import io.github.jhipster.application.domain.UsoRecursoFisico;
import io.github.jhipster.application.domain.*; // for static metamodels
import io.github.jhipster.application.repository.UsoRecursoFisicoRepository;
import io.github.jhipster.application.repository.search.UsoRecursoFisicoSearchRepository;
import io.github.jhipster.application.service.dto.UsoRecursoFisicoCriteria;
import io.github.jhipster.application.service.dto.UsoRecursoFisicoDTO;
import io.github.jhipster.application.service.mapper.UsoRecursoFisicoMapper;

/**
 * Service for executing complex queries for {@link UsoRecursoFisico} entities in the database.
 * The main input is a {@link UsoRecursoFisicoCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link UsoRecursoFisicoDTO} or a {@link Page} of {@link UsoRecursoFisicoDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class UsoRecursoFisicoQueryService extends QueryService<UsoRecursoFisico> {

    private final Logger log = LoggerFactory.getLogger(UsoRecursoFisicoQueryService.class);

    private final UsoRecursoFisicoRepository usoRecursoFisicoRepository;

    private final UsoRecursoFisicoMapper usoRecursoFisicoMapper;

    private final UsoRecursoFisicoSearchRepository usoRecursoFisicoSearchRepository;

    public UsoRecursoFisicoQueryService(UsoRecursoFisicoRepository usoRecursoFisicoRepository, UsoRecursoFisicoMapper usoRecursoFisicoMapper, UsoRecursoFisicoSearchRepository usoRecursoFisicoSearchRepository) {
        this.usoRecursoFisicoRepository = usoRecursoFisicoRepository;
        this.usoRecursoFisicoMapper = usoRecursoFisicoMapper;
        this.usoRecursoFisicoSearchRepository = usoRecursoFisicoSearchRepository;
    }

    /**
     * Return a {@link List} of {@link UsoRecursoFisicoDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<UsoRecursoFisicoDTO> findByCriteria(UsoRecursoFisicoCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<UsoRecursoFisico> specification = createSpecification(criteria);
        return usoRecursoFisicoMapper.toDto(usoRecursoFisicoRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link UsoRecursoFisicoDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<UsoRecursoFisicoDTO> findByCriteria(UsoRecursoFisicoCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<UsoRecursoFisico> specification = createSpecification(criteria);
        return usoRecursoFisicoRepository.findAll(specification, page)
            .map(usoRecursoFisicoMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(UsoRecursoFisicoCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<UsoRecursoFisico> specification = createSpecification(criteria);
        return usoRecursoFisicoRepository.count(specification);
    }

    /**
     * Function to convert ConsumerCriteria to a {@link Specification}
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */    
    private Specification<UsoRecursoFisico> createSpecification(UsoRecursoFisicoCriteria criteria) {
        Specification<UsoRecursoFisico> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), UsoRecursoFisico_.id));
            }
            if (criteria.getRegistroFechaInicio() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getRegistroFechaInicio(), UsoRecursoFisico_.registroFechaInicio));
            }
            if (criteria.getRegistroFechaFinal() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getRegistroFechaFinal(), UsoRecursoFisico_.registroFechaFinal));
            }
            if (criteria.getTipoRegistro() != null) {
                specification = specification.and(buildSpecification(criteria.getTipoRegistro(), UsoRecursoFisico_.tipoRegistro));
            }
            if (criteria.getRecursoId() != null) {
                specification = specification.and(buildSpecification(criteria.getRecursoId(),
                    root -> root.join(UsoRecursoFisico_.recurso, JoinType.LEFT).get(RecursosFisicos_.id)));
            }
            if (criteria.getMiembroId() != null) {
                specification = specification.and(buildSpecification(criteria.getMiembroId(),
                    root -> root.join(UsoRecursoFisico_.miembro, JoinType.LEFT).get(Miembros_.id)));
            }
        }
        return specification;
    }
}
