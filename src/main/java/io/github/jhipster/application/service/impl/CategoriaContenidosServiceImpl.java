package io.github.jhipster.application.service.impl;

import io.github.jhipster.application.service.CategoriaContenidosService;
import io.github.jhipster.application.domain.CategoriaContenidos;
import io.github.jhipster.application.repository.CategoriaContenidosRepository;
import io.github.jhipster.application.repository.search.CategoriaContenidosSearchRepository;
import io.github.jhipster.application.service.dto.CategoriaContenidosDTO;
import io.github.jhipster.application.service.mapper.CategoriaContenidosMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.elasticsearch.index.query.QueryBuilders.*;

/**
 * Service Implementation for managing {@link CategoriaContenidos}.
 */
@Service
@Transactional
public class CategoriaContenidosServiceImpl implements CategoriaContenidosService {

    private final Logger log = LoggerFactory.getLogger(CategoriaContenidosServiceImpl.class);

    private final CategoriaContenidosRepository categoriaContenidosRepository;

    private final CategoriaContenidosMapper categoriaContenidosMapper;

    private final CategoriaContenidosSearchRepository categoriaContenidosSearchRepository;

    public CategoriaContenidosServiceImpl(CategoriaContenidosRepository categoriaContenidosRepository, CategoriaContenidosMapper categoriaContenidosMapper, CategoriaContenidosSearchRepository categoriaContenidosSearchRepository) {
        this.categoriaContenidosRepository = categoriaContenidosRepository;
        this.categoriaContenidosMapper = categoriaContenidosMapper;
        this.categoriaContenidosSearchRepository = categoriaContenidosSearchRepository;
    }

    /**
     * Save a categoriaContenidos.
     *
     * @param categoriaContenidosDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public CategoriaContenidosDTO save(CategoriaContenidosDTO categoriaContenidosDTO) {
        log.debug("Request to save CategoriaContenidos : {}", categoriaContenidosDTO);
        CategoriaContenidos categoriaContenidos = categoriaContenidosMapper.toEntity(categoriaContenidosDTO);
        categoriaContenidos = categoriaContenidosRepository.save(categoriaContenidos);
        CategoriaContenidosDTO result = categoriaContenidosMapper.toDto(categoriaContenidos);
        categoriaContenidosSearchRepository.save(categoriaContenidos);
        return result;
    }

    /**
     * Get all the categoriaContenidos.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<CategoriaContenidosDTO> findAll(Pageable pageable) {
        log.debug("Request to get all CategoriaContenidos");
        return categoriaContenidosRepository.findAll(pageable)
            .map(categoriaContenidosMapper::toDto);
    }


    /**
     * Get one categoriaContenidos by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<CategoriaContenidosDTO> findOne(Long id) {
        log.debug("Request to get CategoriaContenidos : {}", id);
        return categoriaContenidosRepository.findById(id)
            .map(categoriaContenidosMapper::toDto);
    }

    /**
     * Delete the categoriaContenidos by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete CategoriaContenidos : {}", id);
        categoriaContenidosRepository.deleteById(id);
        categoriaContenidosSearchRepository.deleteById(id);
    }

    /**
     * Search for the categoriaContenidos corresponding to the query.
     *
     * @param query the query of the search.
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<CategoriaContenidosDTO> search(String query, Pageable pageable) {
        log.debug("Request to search for a page of CategoriaContenidos for query {}", query);
        return categoriaContenidosSearchRepository.search(queryStringQuery(query), pageable)
            .map(categoriaContenidosMapper::toDto);
    }
}
