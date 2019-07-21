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

import io.github.jhipster.application.domain.ChatGrupo;
import io.github.jhipster.application.domain.*; // for static metamodels
import io.github.jhipster.application.repository.ChatGrupoRepository;
import io.github.jhipster.application.repository.search.ChatGrupoSearchRepository;
import io.github.jhipster.application.service.dto.ChatGrupoCriteria;
import io.github.jhipster.application.service.dto.ChatGrupoDTO;
import io.github.jhipster.application.service.mapper.ChatGrupoMapper;

/**
 * Service for executing complex queries for {@link ChatGrupo} entities in the database.
 * The main input is a {@link ChatGrupoCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link ChatGrupoDTO} or a {@link Page} of {@link ChatGrupoDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class ChatGrupoQueryService extends QueryService<ChatGrupo> {

    private final Logger log = LoggerFactory.getLogger(ChatGrupoQueryService.class);

    private final ChatGrupoRepository chatGrupoRepository;

    private final ChatGrupoMapper chatGrupoMapper;

    private final ChatGrupoSearchRepository chatGrupoSearchRepository;

    public ChatGrupoQueryService(ChatGrupoRepository chatGrupoRepository, ChatGrupoMapper chatGrupoMapper, ChatGrupoSearchRepository chatGrupoSearchRepository) {
        this.chatGrupoRepository = chatGrupoRepository;
        this.chatGrupoMapper = chatGrupoMapper;
        this.chatGrupoSearchRepository = chatGrupoSearchRepository;
    }

    /**
     * Return a {@link List} of {@link ChatGrupoDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<ChatGrupoDTO> findByCriteria(ChatGrupoCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<ChatGrupo> specification = createSpecification(criteria);
        return chatGrupoMapper.toDto(chatGrupoRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link ChatGrupoDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<ChatGrupoDTO> findByCriteria(ChatGrupoCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<ChatGrupo> specification = createSpecification(criteria);
        return chatGrupoRepository.findAll(specification, page)
            .map(chatGrupoMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(ChatGrupoCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<ChatGrupo> specification = createSpecification(criteria);
        return chatGrupoRepository.count(specification);
    }

    /**
     * Function to convert ConsumerCriteria to a {@link Specification}
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */    
    private Specification<ChatGrupo> createSpecification(ChatGrupoCriteria criteria) {
        Specification<ChatGrupo> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), ChatGrupo_.id));
            }
            if (criteria.getMensaje() != null) {
                specification = specification.and(buildStringSpecification(criteria.getMensaje(), ChatGrupo_.mensaje));
            }
            if (criteria.getFecha() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getFecha(), ChatGrupo_.fecha));
            }
            if (criteria.getLeido() != null) {
                specification = specification.and(buildSpecification(criteria.getLeido(), ChatGrupo_.leido));
            }
            if (criteria.getDeId() != null) {
                specification = specification.and(buildSpecification(criteria.getDeId(),
                    root -> root.join(ChatGrupo_.de, JoinType.LEFT).get(User_.id)));
            }
            if (criteria.getParaId() != null) {
                specification = specification.and(buildSpecification(criteria.getParaId(),
                    root -> root.join(ChatGrupo_.para, JoinType.LEFT).get(User_.id)));
            }
            if (criteria.getGrupoId() != null) {
                specification = specification.and(buildSpecification(criteria.getGrupoId(),
                    root -> root.join(ChatGrupo_.grupo, JoinType.LEFT).get(Grupos_.id)));
            }
        }
        return specification;
    }
}
