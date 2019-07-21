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

import io.github.jhipster.application.domain.CuentaAsociada;
import io.github.jhipster.application.domain.*; // for static metamodels
import io.github.jhipster.application.repository.CuentaAsociadaRepository;
import io.github.jhipster.application.repository.search.CuentaAsociadaSearchRepository;
import io.github.jhipster.application.service.dto.CuentaAsociadaCriteria;
import io.github.jhipster.application.service.dto.CuentaAsociadaDTO;
import io.github.jhipster.application.service.mapper.CuentaAsociadaMapper;

/**
 * Service for executing complex queries for {@link CuentaAsociada} entities in the database.
 * The main input is a {@link CuentaAsociadaCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link CuentaAsociadaDTO} or a {@link Page} of {@link CuentaAsociadaDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class CuentaAsociadaQueryService extends QueryService<CuentaAsociada> {

    private final Logger log = LoggerFactory.getLogger(CuentaAsociadaQueryService.class);

    private final CuentaAsociadaRepository cuentaAsociadaRepository;

    private final CuentaAsociadaMapper cuentaAsociadaMapper;

    private final CuentaAsociadaSearchRepository cuentaAsociadaSearchRepository;

    public CuentaAsociadaQueryService(CuentaAsociadaRepository cuentaAsociadaRepository, CuentaAsociadaMapper cuentaAsociadaMapper, CuentaAsociadaSearchRepository cuentaAsociadaSearchRepository) {
        this.cuentaAsociadaRepository = cuentaAsociadaRepository;
        this.cuentaAsociadaMapper = cuentaAsociadaMapper;
        this.cuentaAsociadaSearchRepository = cuentaAsociadaSearchRepository;
    }

    /**
     * Return a {@link List} of {@link CuentaAsociadaDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<CuentaAsociadaDTO> findByCriteria(CuentaAsociadaCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<CuentaAsociada> specification = createSpecification(criteria);
        return cuentaAsociadaMapper.toDto(cuentaAsociadaRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link CuentaAsociadaDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<CuentaAsociadaDTO> findByCriteria(CuentaAsociadaCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<CuentaAsociada> specification = createSpecification(criteria);
        return cuentaAsociadaRepository.findAll(specification, page)
            .map(cuentaAsociadaMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(CuentaAsociadaCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<CuentaAsociada> specification = createSpecification(criteria);
        return cuentaAsociadaRepository.count(specification);
    }

    /**
     * Function to convert ConsumerCriteria to a {@link Specification}
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */    
    private Specification<CuentaAsociada> createSpecification(CuentaAsociadaCriteria criteria) {
        Specification<CuentaAsociada> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), CuentaAsociada_.id));
            }
            if (criteria.getIdentificaciontitular() != null) {
                specification = specification.and(buildStringSpecification(criteria.getIdentificaciontitular(), CuentaAsociada_.identificaciontitular));
            }
            if (criteria.getNombreTitular() != null) {
                specification = specification.and(buildStringSpecification(criteria.getNombreTitular(), CuentaAsociada_.nombreTitular));
            }
            if (criteria.getApellidoTitular() != null) {
                specification = specification.and(buildStringSpecification(criteria.getApellidoTitular(), CuentaAsociada_.apellidoTitular));
            }
            if (criteria.getNumeroCuenta() != null) {
                specification = specification.and(buildStringSpecification(criteria.getNumeroCuenta(), CuentaAsociada_.numeroCuenta));
            }
            if (criteria.getTipoCuenta() != null) {
                specification = specification.and(buildStringSpecification(criteria.getTipoCuenta(), CuentaAsociada_.tipoCuenta));
            }
            if (criteria.getCodigoSeguridad() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCodigoSeguridad(), CuentaAsociada_.codigoSeguridad));
            }
            if (criteria.getFechaVencimiento() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getFechaVencimiento(), CuentaAsociada_.fechaVencimiento));
            }
            if (criteria.getFacturacionId() != null) {
                specification = specification.and(buildSpecification(criteria.getFacturacionId(),
                    root -> root.join(CuentaAsociada_.facturacions, JoinType.LEFT).get(Facturacion_.id)));
            }
        }
        return specification;
    }
}
