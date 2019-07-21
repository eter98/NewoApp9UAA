package io.github.jhipster.application.service.impl;

import io.github.jhipster.application.service.MiembrosGrupoService;
import io.github.jhipster.application.domain.MiembrosGrupo;
import io.github.jhipster.application.repository.MiembrosGrupoRepository;
import io.github.jhipster.application.repository.search.MiembrosGrupoSearchRepository;
import io.github.jhipster.application.service.dto.MiembrosGrupoDTO;
import io.github.jhipster.application.service.mapper.MiembrosGrupoMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.elasticsearch.index.query.QueryBuilders.*;

/**
 * Service Implementation for managing {@link MiembrosGrupo}.
 */
@Service
@Transactional
public class MiembrosGrupoServiceImpl implements MiembrosGrupoService {

    private final Logger log = LoggerFactory.getLogger(MiembrosGrupoServiceImpl.class);

    private final MiembrosGrupoRepository miembrosGrupoRepository;

    private final MiembrosGrupoMapper miembrosGrupoMapper;

    private final MiembrosGrupoSearchRepository miembrosGrupoSearchRepository;

    public MiembrosGrupoServiceImpl(MiembrosGrupoRepository miembrosGrupoRepository, MiembrosGrupoMapper miembrosGrupoMapper, MiembrosGrupoSearchRepository miembrosGrupoSearchRepository) {
        this.miembrosGrupoRepository = miembrosGrupoRepository;
        this.miembrosGrupoMapper = miembrosGrupoMapper;
        this.miembrosGrupoSearchRepository = miembrosGrupoSearchRepository;
    }

    /**
     * Save a miembrosGrupo.
     *
     * @param miembrosGrupoDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public MiembrosGrupoDTO save(MiembrosGrupoDTO miembrosGrupoDTO) {
        log.debug("Request to save MiembrosGrupo : {}", miembrosGrupoDTO);
        MiembrosGrupo miembrosGrupo = miembrosGrupoMapper.toEntity(miembrosGrupoDTO);
        miembrosGrupo = miembrosGrupoRepository.save(miembrosGrupo);
        MiembrosGrupoDTO result = miembrosGrupoMapper.toDto(miembrosGrupo);
        miembrosGrupoSearchRepository.save(miembrosGrupo);
        return result;
    }

    /**
     * Get all the miembrosGrupos.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<MiembrosGrupoDTO> findAll(Pageable pageable) {
        log.debug("Request to get all MiembrosGrupos");
        return miembrosGrupoRepository.findAll(pageable)
            .map(miembrosGrupoMapper::toDto);
    }


    /**
     * Get one miembrosGrupo by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<MiembrosGrupoDTO> findOne(Long id) {
        log.debug("Request to get MiembrosGrupo : {}", id);
        return miembrosGrupoRepository.findById(id)
            .map(miembrosGrupoMapper::toDto);
    }

    /**
     * Delete the miembrosGrupo by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete MiembrosGrupo : {}", id);
        miembrosGrupoRepository.deleteById(id);
        miembrosGrupoSearchRepository.deleteById(id);
    }

    /**
     * Search for the miembrosGrupo corresponding to the query.
     *
     * @param query the query of the search.
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<MiembrosGrupoDTO> search(String query, Pageable pageable) {
        log.debug("Request to search for a page of MiembrosGrupos for query {}", query);
        return miembrosGrupoSearchRepository.search(queryStringQuery(query), pageable)
            .map(miembrosGrupoMapper::toDto);
    }
}
