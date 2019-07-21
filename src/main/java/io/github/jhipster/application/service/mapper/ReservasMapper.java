package io.github.jhipster.application.service.mapper;

import io.github.jhipster.application.domain.*;
import io.github.jhipster.application.service.dto.ReservasDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Reservas} and its DTO {@link ReservasDTO}.
 */
@Mapper(componentModel = "spring", uses = {UserMapper.class, EspaciosReservaMapper.class})
public interface ReservasMapper extends EntityMapper<ReservasDTO, Reservas> {

    @Mapping(source = "miembro.id", target = "miembroId")
    @Mapping(source = "miembro.login", target = "miembroLogin")
    @Mapping(source = "espacio.id", target = "espacioId")
    @Mapping(source = "espacio.nombre", target = "espacioNombre")
    ReservasDTO toDto(Reservas reservas);

    @Mapping(target = "registroCompras", ignore = true)
    @Mapping(target = "removeRegistroCompra", ignore = true)
    @Mapping(source = "miembroId", target = "miembro")
    @Mapping(source = "espacioId", target = "espacio")
    Reservas toEntity(ReservasDTO reservasDTO);

    default Reservas fromId(Long id) {
        if (id == null) {
            return null;
        }
        Reservas reservas = new Reservas();
        reservas.setId(id);
        return reservas;
    }
}
