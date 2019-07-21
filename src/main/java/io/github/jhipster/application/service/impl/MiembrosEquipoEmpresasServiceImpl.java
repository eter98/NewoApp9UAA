package io.github.jhipster.application.service.impl;

import io.github.jhipster.application.service.MiembrosEquipoEmpresasService;
import io.github.jhipster.application.domain.MiembrosEquipoEmpresas;
import io.github.jhipster.application.repository.MiembrosEquipoEmpresasRepository;
import io.github.jhipster.application.repository.search.MiembrosEquipoEmpresasSearchRepository;
import io.github.jhipster.application.service.dto.MiembrosEquipoEmpresasDTO;
import io.github.jhipster.application.service.mapper.MiembrosEquipoEmpresasMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.elasticsearch.index.query.QueryBuilders.*;

/**
 * Service Implementation for managing {@link MiembrosEquipoEmpresas}.
 */
@Service
@Transactional
public class MiembrosEquipoEmpresasServiceImpl implements MiembrosEquipoEmpresasService {

    private final Logger log = LoggerFactory.getLogger(MiembrosEquipoEmpresasServiceImpl.class);

    private final MiembrosEquipoEmpresasRepository miembrosEquipoEmpresasRepository;

    private final MiembrosEquipoEmpresasMapper miembrosEquipoEmpresasMapper;

    private final MiembrosEquipoEmpresasSearchRepository miembrosEquipoEmpresasSearchRepository;

    public MiembrosEquipoEmpresasServiceImpl(MiembrosEquipoEmpresasRepository miembrosEquipoEmpresasRepository, MiembrosEquipoEmpresasMapper miembrosEquipoEmpresasMapper, MiembrosEquipoEmpresasSearchRepository miembrosEquipoEmpresasSearchRepository) {
        this.miembrosEquipoEmpresasRepository = miembrosEquipoEmpresasRepository;
        this.miembrosEquipoEmpresasMapper = miembrosEquipoEmpresasMapper;
        this.miembrosEquipoEmpresasSearchRepository = miembrosEquipoEmpresasSearchRepository;
    }

    /**
     * Save a miembrosEquipoEmpresas.
     *
     * @param miembrosEquipoEmpresasDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public MiembrosEquipoEmpresasDTO save(MiembrosEquipoEmpresasDTO miembrosEquipoEmpresasDTO) {
        log.debug("Request to save MiembrosEquipoEmpresas : {}", miembrosEquipoEmpresasDTO);
        MiembrosEquipoEmpresas miembrosEquipoEmpresas = miembrosEquipoEmpresasMapper.toEntity(miembrosEquipoEmpresasDTO);
        miembrosEquipoEmpresas = miembrosEquipoEmpresasRepository.save(miembrosEquipoEmpresas);
        MiembrosEquipoEmpresasDTO result = miembrosEquipoEmpresasMapper.toDto(miembrosEquipoEmpresas);
        miembrosEquipoEmpresasSearchRepository.save(miembrosEquipoEmpresas);
        return result;
    }

    /**
     * Get all the miembrosEquipoEmpresas.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<MiembrosEquipoEmpresasDTO> findAll(Pageable pageable) {
        log.debug("Request to get all MiembrosEquipoEmpresas");
        return miembrosEquipoEmpresasRepository.findAll(pageable)
            .map(miembrosEquipoEmpresasMapper::toDto);
    }


    /**
     * Get one miembrosEquipoEmpresas by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<MiembrosEquipoEmpresasDTO> findOne(Long id) {
        log.debug("Request to get MiembrosEquipoEmpresas : {}", id);
        return miembrosEquipoEmpresasRepository.findById(id)
            .map(miembrosEquipoEmpresasMapper::toDto);
    }

    /**
     * Delete the miembrosEquipoEmpresas by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete MiembrosEquipoEmpresas : {}", id);
        miembrosEquipoEmpresasRepository.deleteById(id);
        miembrosEquipoEmpresasSearchRepository.deleteById(id);
    }

    /**
     * Search for the miembrosEquipoEmpresas corresponding to the query.
     *
     * @param query the query of the search.
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<MiembrosEquipoEmpresasDTO> search(String query, Pageable pageable) {
        log.debug("Request to search for a page of MiembrosEquipoEmpresas for query {}", query);
        return miembrosEquipoEmpresasSearchRepository.search(queryStringQuery(query), pageable)
            .map(miembrosEquipoEmpresasMapper::toDto);
    }
}
