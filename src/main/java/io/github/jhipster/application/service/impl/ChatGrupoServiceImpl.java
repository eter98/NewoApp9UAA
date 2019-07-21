package io.github.jhipster.application.service.impl;

import io.github.jhipster.application.service.ChatGrupoService;
import io.github.jhipster.application.domain.ChatGrupo;
import io.github.jhipster.application.repository.ChatGrupoRepository;
import io.github.jhipster.application.repository.search.ChatGrupoSearchRepository;
import io.github.jhipster.application.service.dto.ChatGrupoDTO;
import io.github.jhipster.application.service.mapper.ChatGrupoMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.elasticsearch.index.query.QueryBuilders.*;

/**
 * Service Implementation for managing {@link ChatGrupo}.
 */
@Service
@Transactional
public class ChatGrupoServiceImpl implements ChatGrupoService {

    private final Logger log = LoggerFactory.getLogger(ChatGrupoServiceImpl.class);

    private final ChatGrupoRepository chatGrupoRepository;

    private final ChatGrupoMapper chatGrupoMapper;

    private final ChatGrupoSearchRepository chatGrupoSearchRepository;

    public ChatGrupoServiceImpl(ChatGrupoRepository chatGrupoRepository, ChatGrupoMapper chatGrupoMapper, ChatGrupoSearchRepository chatGrupoSearchRepository) {
        this.chatGrupoRepository = chatGrupoRepository;
        this.chatGrupoMapper = chatGrupoMapper;
        this.chatGrupoSearchRepository = chatGrupoSearchRepository;
    }

    /**
     * Save a chatGrupo.
     *
     * @param chatGrupoDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public ChatGrupoDTO save(ChatGrupoDTO chatGrupoDTO) {
        log.debug("Request to save ChatGrupo : {}", chatGrupoDTO);
        ChatGrupo chatGrupo = chatGrupoMapper.toEntity(chatGrupoDTO);
        chatGrupo = chatGrupoRepository.save(chatGrupo);
        ChatGrupoDTO result = chatGrupoMapper.toDto(chatGrupo);
        chatGrupoSearchRepository.save(chatGrupo);
        return result;
    }

    /**
     * Get all the chatGrupos.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<ChatGrupoDTO> findAll(Pageable pageable) {
        log.debug("Request to get all ChatGrupos");
        return chatGrupoRepository.findAll(pageable)
            .map(chatGrupoMapper::toDto);
    }


    /**
     * Get one chatGrupo by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<ChatGrupoDTO> findOne(Long id) {
        log.debug("Request to get ChatGrupo : {}", id);
        return chatGrupoRepository.findById(id)
            .map(chatGrupoMapper::toDto);
    }

    /**
     * Delete the chatGrupo by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete ChatGrupo : {}", id);
        chatGrupoRepository.deleteById(id);
        chatGrupoSearchRepository.deleteById(id);
    }

    /**
     * Search for the chatGrupo corresponding to the query.
     *
     * @param query the query of the search.
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<ChatGrupoDTO> search(String query, Pageable pageable) {
        log.debug("Request to search for a page of ChatGrupos for query {}", query);
        return chatGrupoSearchRepository.search(queryStringQuery(query), pageable)
            .map(chatGrupoMapper::toDto);
    }
}
