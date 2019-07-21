package io.github.jhipster.application.service.mapper;

import io.github.jhipster.application.domain.*;
import io.github.jhipster.application.service.dto.TipoRecursoDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link TipoRecurso} and its DTO {@link TipoRecursoDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface TipoRecursoMapper extends EntityMapper<TipoRecursoDTO, TipoRecurso> {



    default TipoRecurso fromId(Long id) {
        if (id == null) {
            return null;
        }
        TipoRecurso tipoRecurso = new TipoRecurso();
        tipoRecurso.setId(id);
        return tipoRecurso;
    }
}
