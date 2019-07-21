package io.github.jhipster.application.service.impl;

import io.github.jhipster.application.service.HostSedeService;
import io.github.jhipster.application.domain.HostSede;
import io.github.jhipster.application.repository.HostSedeRepository;
import io.github.jhipster.application.repository.search.HostSedeSearchRepository;
import io.github.jhipster.application.service.dto.HostSedeDTO;
import io.github.jhipster.application.service.mapper.HostSedeMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.elasticsearch.index.query.QueryBuilders.*;

/**
 * Service Implementation for managing {@link HostSede}.
 */
@Service
@Transactional
public class HostSedeServiceImpl implements HostSedeService {

    private final Logger log = LoggerFactory.getLogger(HostSedeServiceImpl.class);

    private final HostSedeRepository hostSedeRepository;

    private final HostSedeMapper hostSedeMapper;

    private final HostSedeSearchRepository hostSedeSearchRepository;

    public HostSedeServiceImpl(HostSedeRepository hostSedeRepository, HostSedeMapper hostSedeMapper, HostSedeSearchRepository hostSedeSearchRepository) {
        this.hostSedeRepository = hostSedeRepository;
        this.hostSedeMapper = hostSedeMapper;
        this.hostSedeSearchRepository = hostSedeSearchRepository;
    }

    /**
     * Save a hostSede.
     *
     * @param hostSedeDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public HostSedeDTO save(HostSedeDTO hostSedeDTO) {
        log.debug("Request to save HostSede : {}", hostSedeDTO);
        HostSede hostSede = hostSedeMapper.toEntity(hostSedeDTO);
        hostSede = hostSedeRepository.save(hostSede);
        HostSedeDTO result = hostSedeMapper.toDto(hostSede);
        hostSedeSearchRepository.save(hostSede);
        return result;
    }

    /**
     * Get all the hostSedes.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<HostSedeDTO> findAll(Pageable pageable) {
        log.debug("Request to get all HostSedes");
        return hostSedeRepository.findAll(pageable)
            .map(hostSedeMapper::toDto);
    }


    /**
     * Get one hostSede by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<HostSedeDTO> findOne(Long id) {
        log.debug("Request to get HostSede : {}", id);
        return hostSedeRepository.findById(id)
            .map(hostSedeMapper::toDto);
    }

    /**
     * Delete the hostSede by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete HostSede : {}", id);
        hostSedeRepository.deleteById(id);
        hostSedeSearchRepository.deleteById(id);
    }

    /**
     * Search for the hostSede corresponding to the query.
     *
     * @param query the query of the search.
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<HostSedeDTO> search(String query, Pageable pageable) {
        log.debug("Request to search for a page of HostSedes for query {}", query);
        return hostSedeSearchRepository.search(queryStringQuery(query), pageable)
            .map(hostSedeMapper::toDto);
    }
}
