package io.github.jhipster.application.service.mapper;

import io.github.jhipster.application.domain.*;
import io.github.jhipster.application.service.dto.RegistroCompraDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link RegistroCompra} and its DTO {@link RegistroCompraDTO}.
 */
@Mapper(componentModel = "spring", uses = {UserMapper.class, FacturacionMapper.class, ReservasMapper.class, EntradaInvitadosMapper.class, EntradaMiembrosMapper.class})
public interface RegistroCompraMapper extends EntityMapper<RegistroCompraDTO, RegistroCompra> {

    @Mapping(source = "miembro.id", target = "miembroId")
    @Mapping(source = "miembro.login", target = "miembroLogin")
    @Mapping(source = "facturacion.id", target = "facturacionId")
    @Mapping(source = "reservas.id", target = "reservasId")
    @Mapping(source = "entradaInvitados.id", target = "entradaInvitadosId")
    @Mapping(source = "entradaMiembros.id", target = "entradaMiembrosId")
    RegistroCompraDTO toDto(RegistroCompra registroCompra);

    @Mapping(source = "miembroId", target = "miembro")
    @Mapping(source = "facturacionId", target = "facturacion")
    @Mapping(source = "reservasId", target = "reservas")
    @Mapping(source = "entradaInvitadosId", target = "entradaInvitados")
    @Mapping(source = "entradaMiembrosId", target = "entradaMiembros")
    RegistroCompra toEntity(RegistroCompraDTO registroCompraDTO);

    default RegistroCompra fromId(Long id) {
        if (id == null) {
            return null;
        }
        RegistroCompra registroCompra = new RegistroCompra();
        registroCompra.setId(id);
        return registroCompra;
    }
}
