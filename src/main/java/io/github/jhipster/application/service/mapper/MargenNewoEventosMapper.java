package io.github.jhipster.application.service.mapper;

import io.github.jhipster.application.domain.*;
import io.github.jhipster.application.service.dto.MargenNewoEventosDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link MargenNewoEventos} and its DTO {@link MargenNewoEventosDTO}.
 */
@Mapper(componentModel = "spring", uses = {EventoMapper.class})
public interface MargenNewoEventosMapper extends EntityMapper<MargenNewoEventosDTO, MargenNewoEventos> {

    @Mapping(source = "evento.id", target = "eventoId")
    @Mapping(source = "evento.nombreEvento", target = "eventoNombreEvento")
    MargenNewoEventosDTO toDto(MargenNewoEventos margenNewoEventos);

    @Mapping(source = "eventoId", target = "evento")
    MargenNewoEventos toEntity(MargenNewoEventosDTO margenNewoEventosDTO);

    default MargenNewoEventos fromId(Long id) {
        if (id == null) {
            return null;
        }
        MargenNewoEventos margenNewoEventos = new MargenNewoEventos();
        margenNewoEventos.setId(id);
        return margenNewoEventos;
    }
}
