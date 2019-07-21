package io.github.jhipster.application.service.mapper;

import io.github.jhipster.application.domain.*;
import io.github.jhipster.application.service.dto.EventoDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Evento} and its DTO {@link EventoDTO}.
 */
@Mapper(componentModel = "spring", uses = {CategoriaContenidosMapper.class, GruposMapper.class, UserMapper.class})
public interface EventoMapper extends EntityMapper<EventoDTO, Evento> {

    @Mapping(source = "categoriaEvento.id", target = "categoriaEventoId")
    @Mapping(source = "categoriaEvento.categoria", target = "categoriaEventoCategoria")
    @Mapping(source = "grupos.id", target = "gruposId")
    @Mapping(source = "grupos.nombreGrupo", target = "gruposNombreGrupo")
    @Mapping(source = "miembro.id", target = "miembroId")
    @Mapping(source = "miembro.login", target = "miembroLogin")
    EventoDTO toDto(Evento evento);

    @Mapping(source = "categoriaEventoId", target = "categoriaEvento")
    @Mapping(source = "gruposId", target = "grupos")
    @Mapping(source = "miembroId", target = "miembro")
    Evento toEntity(EventoDTO eventoDTO);

    default Evento fromId(Long id) {
        if (id == null) {
            return null;
        }
        Evento evento = new Evento();
        evento.setId(id);
        return evento;
    }
}
