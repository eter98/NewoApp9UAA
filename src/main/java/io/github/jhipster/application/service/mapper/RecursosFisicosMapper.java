package io.github.jhipster.application.service.mapper;

import io.github.jhipster.application.domain.*;
import io.github.jhipster.application.service.dto.RecursosFisicosDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link RecursosFisicos} and its DTO {@link RecursosFisicosDTO}.
 */
@Mapper(componentModel = "spring", uses = {SedesMapper.class, TipoRecursoMapper.class})
public interface RecursosFisicosMapper extends EntityMapper<RecursosFisicosDTO, RecursosFisicos> {

    @Mapping(source = "sede.id", target = "sedeId")
    @Mapping(source = "sede.nombreSede", target = "sedeNombreSede")
    @Mapping(source = "tipoRecurso.id", target = "tipoRecursoId")
    @Mapping(source = "tipoRecurso.nombre", target = "tipoRecursoNombre")
    RecursosFisicosDTO toDto(RecursosFisicos recursosFisicos);

    @Mapping(source = "sedeId", target = "sede")
    @Mapping(source = "tipoRecursoId", target = "tipoRecurso")
    RecursosFisicos toEntity(RecursosFisicosDTO recursosFisicosDTO);

    default RecursosFisicos fromId(Long id) {
        if (id == null) {
            return null;
        }
        RecursosFisicos recursosFisicos = new RecursosFisicos();
        recursosFisicos.setId(id);
        return recursosFisicos;
    }
}
