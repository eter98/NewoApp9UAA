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

import io.github.jhipster.application.domain.ComentarioFeed;
import io.github.jhipster.application.domain.*; // for static metamodels
import io.github.jhipster.application.repository.ComentarioFeedRepository;
import io.github.jhipster.application.repository.search.ComentarioFeedSearchRepository;
import io.github.jhipster.application.service.dto.ComentarioFeedCriteria;
import io.github.jhipster.application.service.dto.ComentarioFeedDTO;
import io.github.jhipster.application.service.mapper.ComentarioFeedMapper;

/**
 * Service for executing complex queries for {@link ComentarioFeed} entities in the database.
 * The main input is a {@link ComentarioFeedCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link ComentarioFeedDTO} or a {@link Page} of {@link ComentarioFeedDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class ComentarioFeedQueryService extends QueryService<ComentarioFeed> {

    private final Logger log = LoggerFactory.getLogger(ComentarioFeedQueryService.class);

    private final ComentarioFeedRepository comentarioFeedRepository;

    private final ComentarioFeedMapper comentarioFeedMapper;

    private final ComentarioFeedSearchRepository comentarioFeedSearchRepository;

    public ComentarioFeedQueryService(ComentarioFeedRepository comentarioFeedRepository, ComentarioFeedMapper comentarioFeedMapper, ComentarioFeedSearchRepository comentarioFeedSearchRepository) {
        this.comentarioFeedRepository = comentarioFeedRepository;
        this.comentarioFeedMapper = comentarioFeedMapper;
        this.comentarioFeedSearchRepository = comentarioFeedSearchRepository;
    }

    /**
     * Return a {@link List} of {@link ComentarioFeedDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<ComentarioFeedDTO> findByCriteria(ComentarioFeedCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<ComentarioFeed> specification = createSpecification(criteria);
        return comentarioFeedMapper.toDto(comentarioFeedRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link ComentarioFeedDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<ComentarioFeedDTO> findByCriteria(ComentarioFeedCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<ComentarioFeed> specification = createSpecification(criteria);
        return comentarioFeedRepository.findAll(specification, page)
            .map(comentarioFeedMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(ComentarioFeedCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<ComentarioFeed> specification = createSpecification(criteria);
        return comentarioFeedRepository.count(specification);
    }

    /**
     * Function to convert ConsumerCriteria to a {@link Specification}
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */    
    private Specification<ComentarioFeed> createSpecification(ComentarioFeedCriteria criteria) {
        Specification<ComentarioFeed> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), ComentarioFeed_.id));
            }
            if (criteria.getFecha() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getFecha(), ComentarioFeed_.fecha));
            }
            if (criteria.getMeGusta() != null) {
                specification = specification.and(buildSpecification(criteria.getMeGusta(), ComentarioFeed_.meGusta));
            }
            if (criteria.getSeguir() != null) {
                specification = specification.and(buildSpecification(criteria.getSeguir(), ComentarioFeed_.seguir));
            }
            if (criteria.getFeedId() != null) {
                specification = specification.and(buildSpecification(criteria.getFeedId(),
                    root -> root.join(ComentarioFeed_.feed, JoinType.LEFT).get(Feed_.id)));
            }
            if (criteria.getMiembroId() != null) {
                specification = specification.and(buildSpecification(criteria.getMiembroId(),
                    root -> root.join(ComentarioFeed_.miembro, JoinType.LEFT).get(User_.id)));
            }
        }
        return specification;
    }
}
