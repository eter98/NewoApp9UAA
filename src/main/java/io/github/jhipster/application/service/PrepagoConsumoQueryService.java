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

import io.github.jhipster.application.domain.PrepagoConsumo;
import io.github.jhipster.application.domain.*; // for static metamodels
import io.github.jhipster.application.repository.PrepagoConsumoRepository;
import io.github.jhipster.application.repository.search.PrepagoConsumoSearchRepository;
import io.github.jhipster.application.service.dto.PrepagoConsumoCriteria;
import io.github.jhipster.application.service.dto.PrepagoConsumoDTO;
import io.github.jhipster.application.service.mapper.PrepagoConsumoMapper;

/**
 * Service for executing complex queries for {@link PrepagoConsumo} entities in the database.
 * The main input is a {@link PrepagoConsumoCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link PrepagoConsumoDTO} or a {@link Page} of {@link PrepagoConsumoDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class PrepagoConsumoQueryService extends QueryService<PrepagoConsumo> {

    private final Logger log = LoggerFactory.getLogger(PrepagoConsumoQueryService.class);

    private final PrepagoConsumoRepository prepagoConsumoRepository;

    private final PrepagoConsumoMapper prepagoConsumoMapper;

    private final PrepagoConsumoSearchRepository prepagoConsumoSearchRepository;

    public PrepagoConsumoQueryService(PrepagoConsumoRepository prepagoConsumoRepository, PrepagoConsumoMapper prepagoConsumoMapper, PrepagoConsumoSearchRepository prepagoConsumoSearchRepository) {
        this.prepagoConsumoRepository = prepagoConsumoRepository;
        this.prepagoConsumoMapper = prepagoConsumoMapper;
        this.prepagoConsumoSearchRepository = prepagoConsumoSearchRepository;
    }

    /**
     * Return a {@link List} of {@link PrepagoConsumoDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<PrepagoConsumoDTO> findByCriteria(PrepagoConsumoCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<PrepagoConsumo> specification = createSpecification(criteria);
        return prepagoConsumoMapper.toDto(prepagoConsumoRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link PrepagoConsumoDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<PrepagoConsumoDTO> findByCriteria(PrepagoConsumoCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<PrepagoConsumo> specification = createSpecification(criteria);
        return prepagoConsumoRepository.findAll(specification, page)
            .map(prepagoConsumoMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(PrepagoConsumoCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<PrepagoConsumo> specification = createSpecification(criteria);
        return prepagoConsumoRepository.count(specification);
    }

    /**
     * Function to convert ConsumerCriteria to a {@link Specification}
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */    
    private Specification<PrepagoConsumo> createSpecification(PrepagoConsumoCriteria criteria) {
        Specification<PrepagoConsumo> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), PrepagoConsumo_.id));
            }
            if (criteria.getAporte() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getAporte(), PrepagoConsumo_.aporte));
            }
            if (criteria.getSaldoActual() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getSaldoActual(), PrepagoConsumo_.saldoActual));
            }
            if (criteria.getFechaRegistro() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getFechaRegistro(), PrepagoConsumo_.fechaRegistro));
            }
            if (criteria.getFechaSaldoActual() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getFechaSaldoActual(), PrepagoConsumo_.fechaSaldoActual));
            }
            if (criteria.getMiembroId() != null) {
                specification = specification.and(buildSpecification(criteria.getMiembroId(),
                    root -> root.join(PrepagoConsumo_.miembro, JoinType.LEFT).get(User_.id)));
            }
            if (criteria.getTipoPrepagoId() != null) {
                specification = specification.and(buildSpecification(criteria.getTipoPrepagoId(),
                    root -> root.join(PrepagoConsumo_.tipoPrepago, JoinType.LEFT).get(TipoPrepagoConsumo_.id)));
            }
        }
        return specification;
    }
}
