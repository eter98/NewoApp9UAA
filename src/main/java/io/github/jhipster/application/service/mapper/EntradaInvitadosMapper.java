package io.github.jhipster.application.service.mapper;

import io.github.jhipster.application.domain.*;
import io.github.jhipster.application.service.dto.EntradaInvitadosDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link EntradaInvitados} and its DTO {@link EntradaInvitadosDTO}.
 */
@Mapper(componentModel = "spring", uses = {EspacioLibreMapper.class, EspaciosReservaMapper.class, InvitadosMapper.class, UserMapper.class})
public interface EntradaInvitadosMapper extends EntityMapper<EntradaInvitadosDTO, EntradaInvitados> {

    @Mapping(source = "espacio.id", target = "espacioId")
    @Mapping(source = "espacio.nombre", target = "espacioNombre")
    @Mapping(source = "espacioReserva.id", target = "espacioReservaId")
    @Mapping(source = "espacioReserva.nombre", target = "espacioReservaNombre")
    @Mapping(source = "invitado.id", target = "invitadoId")
    @Mapping(source = "invitado.identificacion", target = "invitadoIdentificacion")
    @Mapping(source = "miembro.id", target = "miembroId")
    @Mapping(source = "miembro.login", target = "miembroLogin")
    EntradaInvitadosDTO toDto(EntradaInvitados entradaInvitados);

    @Mapping(target = "registroCompras", ignore = true)
    @Mapping(target = "removeRegistroCompra", ignore = true)
    @Mapping(source = "espacioId", target = "espacio")
    @Mapping(source = "espacioReservaId", target = "espacioReserva")
    @Mapping(source = "invitadoId", target = "invitado")
    @Mapping(source = "miembroId", target = "miembro")
    EntradaInvitados toEntity(EntradaInvitadosDTO entradaInvitadosDTO);

    default EntradaInvitados fromId(Long id) {
        if (id == null) {
            return null;
        }
        EntradaInvitados entradaInvitados = new EntradaInvitados();
        entradaInvitados.setId(id);
        return entradaInvitados;
    }
}
