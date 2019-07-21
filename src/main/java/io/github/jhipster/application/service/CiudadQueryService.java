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

import io.github.jhipster.application.domain.Ciudad;
import io.github.jhipster.application.domain.*; // for static metamodels
import io.github.jhipster.application.repository.CiudadRepository;
import io.github.jhipster.application.repository.search.CiudadSearchRepository;
import io.github.jhipster.application.service.dto.CiudadCriteria;
import io.github.jhipster.application.service.dto.CiudadDTO;
import io.github.jhipster.application.service.mapper.CiudadMapper;

/**
 * Service for executing complex queries for {@link Ciudad} entities in the database.
 * The main input is a {@link CiudadCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link CiudadDTO} or a {@link Page} of {@link CiudadDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class CiudadQueryService extends QueryService<Ciudad> {

    private final Logger log = LoggerFactory.getLogger(CiudadQueryService.class);

    private final CiudadRepository ciudadRepository;

    private final CiudadMapper ciudadMapper;

    private final CiudadSearchRepository ciudadSearchRepository;

    public CiudadQueryService(CiudadRepository ciudadRepository, CiudadMapper ciudadMapper, CiudadSearchRepository ciudadSearchRepository) {
        this.ciudadRepository = ciudadRepository;
        this.ciudadMapper = ciudadMapper;
        this.ciudadSearchRepository = ciudadSearchRepository;
    }

    /**
     * Return a {@link List} of {@link CiudadDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<CiudadDTO> findByCriteria(CiudadCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<Ciudad> specification = createSpecification(criteria);
        return ciudadMapper.toDto(ciudadRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link CiudadDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<CiudadDTO> findByCriteria(CiudadCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<Ciudad> specification = createSpecification(criteria);
        return ciudadRepository.findAll(specification, page)
            .map(ciudadMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(CiudadCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<Ciudad> specification = createSpecification(criteria);
        return ciudadRepository.count(specification);
    }

    /**
     * Function to convert ConsumerCriteria to a {@link Specification}
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */    
    private Specification<Ciudad> createSpecification(CiudadCriteria criteria) {
        Specification<Ciudad> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), Ciudad_.id));
            }
            if (criteria.getNombreCiudad() != null) {
                specification = specification.and(buildStringSpecification(criteria.getNombreCiudad(), Ciudad_.nombreCiudad));
            }
            if (criteria.getPaisId() != null) {
                specification = specification.and(buildSpecification(criteria.getPaisId(),
                    root -> root.join(Ciudad_.pais, JoinType.LEFT).get(Pais_.id)));
            }
        }
        return specification;
    }
}
