package io.github.jhipster.application.service.mapper;

import io.github.jhipster.application.domain.*;
import io.github.jhipster.application.service.dto.CategoriaContenidosDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link CategoriaContenidos} and its DTO {@link CategoriaContenidosDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface CategoriaContenidosMapper extends EntityMapper<CategoriaContenidosDTO, CategoriaContenidos> {



    default CategoriaContenidos fromId(Long id) {
        if (id == null) {
            return null;
        }
        CategoriaContenidos categoriaContenidos = new CategoriaContenidos();
        categoriaContenidos.setId(id);
        return categoriaContenidos;
    }
}
