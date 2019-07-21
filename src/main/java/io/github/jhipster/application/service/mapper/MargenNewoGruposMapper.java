package io.github.jhipster.application.service.mapper;

import io.github.jhipster.application.domain.*;
import io.github.jhipster.application.service.dto.MargenNewoGruposDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link MargenNewoGrupos} and its DTO {@link MargenNewoGruposDTO}.
 */
@Mapper(componentModel = "spring", uses = {GruposMapper.class})
public interface MargenNewoGruposMapper extends EntityMapper<MargenNewoGruposDTO, MargenNewoGrupos> {

    @Mapping(source = "grupo.id", target = "grupoId")
    @Mapping(source = "grupo.nombreGrupo", target = "grupoNombreGrupo")
    MargenNewoGruposDTO toDto(MargenNewoGrupos margenNewoGrupos);

    @Mapping(source = "grupoId", target = "grupo")
    MargenNewoGrupos toEntity(MargenNewoGruposDTO margenNewoGruposDTO);

    default MargenNewoGrupos fromId(Long id) {
        if (id == null) {
            return null;
        }
        MargenNewoGrupos margenNewoGrupos = new MargenNewoGrupos();
        margenNewoGrupos.setId(id);
        return margenNewoGrupos;
    }
}
