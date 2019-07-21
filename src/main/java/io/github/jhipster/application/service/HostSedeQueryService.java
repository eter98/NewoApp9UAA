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

import io.github.jhipster.application.domain.HostSede;
import io.github.jhipster.application.domain.*; // for static metamodels
import io.github.jhipster.application.repository.HostSedeRepository;
import io.github.jhipster.application.repository.search.HostSedeSearchRepository;
import io.github.jhipster.application.service.dto.HostSedeCriteria;
import io.github.jhipster.application.service.dto.HostSedeDTO;
import io.github.jhipster.application.service.mapper.HostSedeMapper;

/**
 * Service for executing complex queries for {@link HostSede} entities in the database.
 * The main input is a {@link HostSedeCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link HostSedeDTO} or a {@link Page} of {@link HostSedeDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class HostSedeQueryService extends QueryService<HostSede> {

    private final Logger log = LoggerFactory.getLogger(HostSedeQueryService.class);

    private final HostSedeRepository hostSedeRepository;

    private final HostSedeMapper hostSedeMapper;

    private final HostSedeSearchRepository hostSedeSearchRepository;

    public HostSedeQueryService(HostSedeRepository hostSedeRepository, HostSedeMapper hostSedeMapper, HostSedeSearchRepository hostSedeSearchRepository) {
        this.hostSedeRepository = hostSedeRepository;
        this.hostSedeMapper = hostSedeMapper;
        this.hostSedeSearchRepository = hostSedeSearchRepository;
    }

    /**
     * Return a {@link List} of {@link HostSedeDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<HostSedeDTO> findByCriteria(HostSedeCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<HostSede> specification = createSpecification(criteria);
        return hostSedeMapper.toDto(hostSedeRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link HostSedeDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<HostSedeDTO> findByCriteria(HostSedeCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<HostSede> specification = createSpecification(criteria);
        return hostSedeRepository.findAll(specification, page)
            .map(hostSedeMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(HostSedeCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<HostSede> specification = createSpecification(criteria);
        return hostSedeRepository.count(specification);
    }

    /**
     * Function to convert ConsumerCriteria to a {@link Specification}
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */    
    private Specification<HostSede> createSpecification(HostSedeCriteria criteria) {
        Specification<HostSede> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), HostSede_.id));
            }
            if (criteria.getSedeId() != null) {
                specification = specification.and(buildSpecification(criteria.getSedeId(),
                    root -> root.join(HostSede_.sede, JoinType.LEFT).get(Sedes_.id)));
            }
            if (criteria.getMiembroId() != null) {
                specification = specification.and(buildSpecification(criteria.getMiembroId(),
                    root -> root.join(HostSede_.miembro, JoinType.LEFT).get(User_.id)));
            }
        }
        return specification;
    }
}
