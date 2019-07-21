package io.github.jhipster.application.service.impl;

import io.github.jhipster.application.service.ChatService;
import io.github.jhipster.application.domain.Chat;
import io.github.jhipster.application.repository.ChatRepository;
import io.github.jhipster.application.repository.search.ChatSearchRepository;
import io.github.jhipster.application.service.dto.ChatDTO;
import io.github.jhipster.application.service.mapper.ChatMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.elasticsearch.index.query.QueryBuilders.*;

/**
 * Service Implementation for managing {@link Chat}.
 */
@Service
@Transactional
public class ChatServiceImpl implements ChatService {

    private final Logger log = LoggerFactory.getLogger(ChatServiceImpl.class);

    private final ChatRepository chatRepository;

    private final ChatMapper chatMapper;

    private final ChatSearchRepository chatSearchRepository;

    public ChatServiceImpl(ChatRepository chatRepository, ChatMapper chatMapper, ChatSearchRepository chatSearchRepository) {
        this.chatRepository = chatRepository;
        this.chatMapper = chatMapper;
        this.chatSearchRepository = chatSearchRepository;
    }

    /**
     * Save a chat.
     *
     * @param chatDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public ChatDTO save(ChatDTO chatDTO) {
        log.debug("Request to save Chat : {}", chatDTO);
        Chat chat = chatMapper.toEntity(chatDTO);
        chat = chatRepository.save(chat);
        ChatDTO result = chatMapper.toDto(chat);
        chatSearchRepository.save(chat);
        return result;
    }

    /**
     * Get all the chats.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<ChatDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Chats");
        return chatRepository.findAll(pageable)
            .map(chatMapper::toDto);
    }


    /**
     * Get one chat by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<ChatDTO> findOne(Long id) {
        log.debug("Request to get Chat : {}", id);
        return chatRepository.findById(id)
            .map(chatMapper::toDto);
    }

    /**
     * Delete the chat by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Chat : {}", id);
        chatRepository.deleteById(id);
        chatSearchRepository.deleteById(id);
    }

    /**
     * Search for the chat corresponding to the query.
     *
     * @param query the query of the search.
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<ChatDTO> search(String query, Pageable pageable) {
        log.debug("Request to search for a page of Chats for query {}", query);
        return chatSearchRepository.search(queryStringQuery(query), pageable)
            .map(chatMapper::toDto);
    }
}
