package io.github.jhipster.application.service.mapper;

import io.github.jhipster.application.domain.*;
import io.github.jhipster.application.service.dto.InvitadosDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Invitados} and its DTO {@link InvitadosDTO}.
 */
@Mapper(componentModel = "spring", uses = {UserMapper.class})
public interface InvitadosMapper extends EntityMapper<InvitadosDTO, Invitados> {

    @Mapping(source = "miembro.id", target = "miembroId")
    @Mapping(source = "miembro.login", target = "miembroLogin")
    InvitadosDTO toDto(Invitados invitados);

    @Mapping(source = "miembroId", target = "miembro")
    Invitados toEntity(InvitadosDTO invitadosDTO);

    default Invitados fromId(Long id) {
        if (id == null) {
            return null;
        }
        Invitados invitados = new Invitados();
        invitados.setId(id);
        return invitados;
    }
}
