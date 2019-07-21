package io.github.jhipster.application.service.impl;

import io.github.jhipster.application.service.PerfilEquipoEmpresaService;
import io.github.jhipster.application.domain.PerfilEquipoEmpresa;
import io.github.jhipster.application.repository.PerfilEquipoEmpresaRepository;
import io.github.jhipster.application.repository.search.PerfilEquipoEmpresaSearchRepository;
import io.github.jhipster.application.service.dto.PerfilEquipoEmpresaDTO;
import io.github.jhipster.application.service.mapper.PerfilEquipoEmpresaMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.elasticsearch.index.query.QueryBuilders.*;

/**
 * Service Implementation for managing {@link PerfilEquipoEmpresa}.
 */
@Service
@Transactional
public class PerfilEquipoEmpresaServiceImpl implements PerfilEquipoEmpresaService {

    private final Logger log = LoggerFactory.getLogger(PerfilEquipoEmpresaServiceImpl.class);

    private final PerfilEquipoEmpresaRepository perfilEquipoEmpresaRepository;

    private final PerfilEquipoEmpresaMapper perfilEquipoEmpresaMapper;

    private final PerfilEquipoEmpresaSearchRepository perfilEquipoEmpresaSearchRepository;

    public PerfilEquipoEmpresaServiceImpl(PerfilEquipoEmpresaRepository perfilEquipoEmpresaRepository, PerfilEquipoEmpresaMapper perfilEquipoEmpresaMapper, PerfilEquipoEmpresaSearchRepository perfilEquipoEmpresaSearchRepository) {
        this.perfilEquipoEmpresaRepository = perfilEquipoEmpresaRepository;
        this.perfilEquipoEmpresaMapper = perfilEquipoEmpresaMapper;
        this.perfilEquipoEmpresaSearchRepository = perfilEquipoEmpresaSearchRepository;
    }

    /**
     * Save a perfilEquipoEmpresa.
     *
     * @param perfilEquipoEmpresaDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public PerfilEquipoEmpresaDTO save(PerfilEquipoEmpresaDTO perfilEquipoEmpresaDTO) {
        log.debug("Request to save PerfilEquipoEmpresa : {}", perfilEquipoEmpresaDTO);
        PerfilEquipoEmpresa perfilEquipoEmpresa = perfilEquipoEmpresaMapper.toEntity(perfilEquipoEmpresaDTO);
        perfilEquipoEmpresa = perfilEquipoEmpresaRepository.save(perfilEquipoEmpresa);
        PerfilEquipoEmpresaDTO result = perfilEquipoEmpresaMapper.toDto(perfilEquipoEmpresa);
        perfilEquipoEmpresaSearchRepository.save(perfilEquipoEmpresa);
        return result;
    }

    /**
     * Get all the perfilEquipoEmpresas.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<PerfilEquipoEmpresaDTO> findAll(Pageable pageable) {
        log.debug("Request to get all PerfilEquipoEmpresas");
        return perfilEquipoEmpresaRepository.findAll(pageable)
            .map(perfilEquipoEmpresaMapper::toDto);
    }


    /**
     * Get one perfilEquipoEmpresa by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<PerfilEquipoEmpresaDTO> findOne(Long id) {
        log.debug("Request to get PerfilEquipoEmpresa : {}", id);
        return perfilEquipoEmpresaRepository.findById(id)
            .map(perfilEquipoEmpresaMapper::toDto);
    }

    /**
     * Delete the perfilEquipoEmpresa by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete PerfilEquipoEmpresa : {}", id);
        perfilEquipoEmpresaRepository.deleteById(id);
        perfilEquipoEmpresaSearchRepository.deleteById(id);
    }

    /**
     * Search for the perfilEquipoEmpresa corresponding to the query.
     *
     * @param query the query of the search.
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<PerfilEquipoEmpresaDTO> search(String query, Pageable pageable) {
        log.debug("Request to search for a page of PerfilEquipoEmpresas for query {}", query);
        return perfilEquipoEmpresaSearchRepository.search(queryStringQuery(query), pageable)
            .map(perfilEquipoEmpresaMapper::toDto);
    }
}
