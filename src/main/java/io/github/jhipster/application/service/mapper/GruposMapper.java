package io.github.jhipster.application.service.mapper;

import io.github.jhipster.application.domain.*;
import io.github.jhipster.application.service.dto.GruposDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Grupos} and its DTO {@link GruposDTO}.
 */
@Mapper(componentModel = "spring", uses = {CategoriaContenidosMapper.class, EventoMapper.class})
public interface GruposMapper extends EntityMapper<GruposDTO, Grupos> {

    @Mapping(source = "categoriaGrupo.id", target = "categoriaGrupoId")
    @Mapping(source = "categoriaGrupo.categoria", target = "categoriaGrupoCategoria")
    @Mapping(source = "evento.id", target = "eventoId")
    @Mapping(source = "evento.nombreEvento", target = "eventoNombreEvento")
    GruposDTO toDto(Grupos grupos);

    @Mapping(source = "categoriaGrupoId", target = "categoriaGrupo")
    @Mapping(source = "eventoId", target = "evento")
    Grupos toEntity(GruposDTO gruposDTO);

    default Grupos fromId(Long id) {
        if (id == null) {
            return null;
        }
        Grupos grupos = new Grupos();
        grupos.setId(id);
        return grupos;
    }
}
