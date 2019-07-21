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

import io.github.jhipster.application.domain.MiembrosGrupo;
import io.github.jhipster.application.domain.*; // for static metamodels
import io.github.jhipster.application.repository.MiembrosGrupoRepository;
import io.github.jhipster.application.repository.search.MiembrosGrupoSearchRepository;
import io.github.jhipster.application.service.dto.MiembrosGrupoCriteria;
import io.github.jhipster.application.service.dto.MiembrosGrupoDTO;
import io.github.jhipster.application.service.mapper.MiembrosGrupoMapper;

/**
 * Service for executing complex queries for {@link MiembrosGrupo} entities in the database.
 * The main input is a {@link MiembrosGrupoCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link MiembrosGrupoDTO} or a {@link Page} of {@link MiembrosGrupoDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class MiembrosGrupoQueryService extends QueryService<MiembrosGrupo> {

    private final Logger log = LoggerFactory.getLogger(MiembrosGrupoQueryService.class);

    private final MiembrosGrupoRepository miembrosGrupoRepository;

    private final MiembrosGrupoMapper miembrosGrupoMapper;

    private final MiembrosGrupoSearchRepository miembrosGrupoSearchRepository;

    public MiembrosGrupoQueryService(MiembrosGrupoRepository miembrosGrupoRepository, MiembrosGrupoMapper miembrosGrupoMapper, MiembrosGrupoSearchRepository miembrosGrupoSearchRepository) {
        this.miembrosGrupoRepository = miembrosGrupoRepository;
        this.miembrosGrupoMapper = miembrosGrupoMapper;
        this.miembrosGrupoSearchRepository = miembrosGrupoSearchRepository;
    }

    /**
     * Return a {@link List} of {@link MiembrosGrupoDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<MiembrosGrupoDTO> findByCriteria(MiembrosGrupoCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<MiembrosGrupo> specification = createSpecification(criteria);
        return miembrosGrupoMapper.toDto(miembrosGrupoRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link MiembrosGrupoDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<MiembrosGrupoDTO> findByCriteria(MiembrosGrupoCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<MiembrosGrupo> specification = createSpecification(criteria);
        return miembrosGrupoRepository.findAll(specification, page)
            .map(miembrosGrupoMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(MiembrosGrupoCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<MiembrosGrupo> specification = createSpecification(criteria);
        return miembrosGrupoRepository.count(specification);
    }

    /**
     * Function to convert ConsumerCriteria to a {@link Specification}
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */    
    private Specification<MiembrosGrupo> createSpecification(MiembrosGrupoCriteria criteria) {
        Specification<MiembrosGrupo> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), MiembrosGrupo_.id));
            }
            if (criteria.getMiembroId() != null) {
                specification = specification.and(buildSpecification(criteria.getMiembroId(),
                    root -> root.join(MiembrosGrupo_.miembro, JoinType.LEFT).get(User_.id)));
            }
        }
        return specification;
    }
}
