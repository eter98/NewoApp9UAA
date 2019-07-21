package io.github.jhipster.application.service.impl;

import io.github.jhipster.application.service.EquipoEmpresasService;
import io.github.jhipster.application.domain.EquipoEmpresas;
import io.github.jhipster.application.repository.EquipoEmpresasRepository;
import io.github.jhipster.application.repository.search.EquipoEmpresasSearchRepository;
import io.github.jhipster.application.service.dto.EquipoEmpresasDTO;
import io.github.jhipster.application.service.mapper.EquipoEmpresasMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.elasticsearch.index.query.QueryBuilders.*;

/**
 * Service Implementation for managing {@link EquipoEmpresas}.
 */
@Service
@Transactional
public class EquipoEmpresasServiceImpl implements EquipoEmpresasService {

    private final Logger log = LoggerFactory.getLogger(EquipoEmpresasServiceImpl.class);

    private final EquipoEmpresasRepository equipoEmpresasRepository;

    private final EquipoEmpresasMapper equipoEmpresasMapper;

    private final EquipoEmpresasSearchRepository equipoEmpresasSearchRepository;

    public EquipoEmpresasServiceImpl(EquipoEmpresasRepository equipoEmpresasRepository, EquipoEmpresasMapper equipoEmpresasMapper, EquipoEmpresasSearchRepository equipoEmpresasSearchRepository) {
        this.equipoEmpresasRepository = equipoEmpresasRepository;
        this.equipoEmpresasMapper = equipoEmpresasMapper;
        this.equipoEmpresasSearchRepository = equipoEmpresasSearchRepository;
    }

    /**
     * Save a equipoEmpresas.
     *
     * @param equipoEmpresasDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public EquipoEmpresasDTO save(EquipoEmpresasDTO equipoEmpresasDTO) {
        log.debug("Request to save EquipoEmpresas : {}", equipoEmpresasDTO);
        EquipoEmpresas equipoEmpresas = equipoEmpresasMapper.toEntity(equipoEmpresasDTO);
        equipoEmpresas = equipoEmpresasRepository.save(equipoEmpresas);
        EquipoEmpresasDTO result = equipoEmpresasMapper.toDto(equipoEmpresas);
        equipoEmpresasSearchRepository.save(equipoEmpresas);
        return result;
    }

    /**
     * Get all the equipoEmpresas.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<EquipoEmpresasDTO> findAll(Pageable pageable) {
        log.debug("Request to get all EquipoEmpresas");
        return equipoEmpresasRepository.findAll(pageable)
            .map(equipoEmpresasMapper::toDto);
    }


    /**
     * Get one equipoEmpresas by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<EquipoEmpresasDTO> findOne(Long id) {
        log.debug("Request to get EquipoEmpresas : {}", id);
        return equipoEmpresasRepository.findById(id)
            .map(equipoEmpresasMapper::toDto);
    }

    /**
     * Delete the equipoEmpresas by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete EquipoEmpresas : {}", id);
        equipoEmpresasRepository.deleteById(id);
        equipoEmpresasSearchRepository.deleteById(id);
    }

    /**
     * Search for the equipoEmpresas corresponding to the query.
     *
     * @param query the query of the search.
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<EquipoEmpresasDTO> search(String query, Pageable pageable) {
        log.debug("Request to search for a page of EquipoEmpresas for query {}", query);
        return equipoEmpresasSearchRepository.search(queryStringQuery(query), pageable)
            .map(equipoEmpresasMapper::toDto);
    }
}
