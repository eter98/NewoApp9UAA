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

import io.github.jhipster.application.domain.Chat;
import io.github.jhipster.application.domain.*; // for static metamodels
import io.github.jhipster.application.repository.ChatRepository;
import io.github.jhipster.application.repository.search.ChatSearchRepository;
import io.github.jhipster.application.service.dto.ChatCriteria;
import io.github.jhipster.application.service.dto.ChatDTO;
import io.github.jhipster.application.service.mapper.ChatMapper;

/**
 * Service for executing complex queries for {@link Chat} entities in the database.
 * The main input is a {@link ChatCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link ChatDTO} or a {@link Page} of {@link ChatDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class ChatQueryService extends QueryService<Chat> {

    private final Logger log = LoggerFactory.getLogger(ChatQueryService.class);

    private final ChatRepository chatRepository;

    private final ChatMapper chatMapper;

    private final ChatSearchRepository chatSearchRepository;

    public ChatQueryService(ChatRepository chatRepository, ChatMapper chatMapper, ChatSearchRepository chatSearchRepository) {
        this.chatRepository = chatRepository;
        this.chatMapper = chatMapper;
        this.chatSearchRepository = chatSearchRepository;
    }

    /**
     * Return a {@link List} of {@link ChatDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<ChatDTO> findByCriteria(ChatCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<Chat> specification = createSpecification(criteria);
        return chatMapper.toDto(chatRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link ChatDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<ChatDTO> findByCriteria(ChatCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<Chat> specification = createSpecification(criteria);
        return chatRepository.findAll(specification, page)
            .map(chatMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(ChatCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<Chat> specification = createSpecification(criteria);
        return chatRepository.count(specification);
    }

    /**
     * Function to convert ConsumerCriteria to a {@link Specification}
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */    
    private Specification<Chat> createSpecification(ChatCriteria criteria) {
        Specification<Chat> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), Chat_.id));
            }
            if (criteria.getMensaje() != null) {
                specification = specification.and(buildStringSpecification(criteria.getMensaje(), Chat_.mensaje));
            }
            if (criteria.getFecha() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getFecha(), Chat_.fecha));
            }
            if (criteria.getLeido() != null) {
                specification = specification.and(buildSpecification(criteria.getLeido(), Chat_.leido));
            }
            if (criteria.getDeId() != null) {
                specification = specification.and(buildSpecification(criteria.getDeId(),
                    root -> root.join(Chat_.de, JoinType.LEFT).get(User_.id)));
            }
            if (criteria.getParaId() != null) {
                specification = specification.and(buildSpecification(criteria.getParaId(),
                    root -> root.join(Chat_.para, JoinType.LEFT).get(User_.id)));
            }
        }
        return specification;
    }
}
