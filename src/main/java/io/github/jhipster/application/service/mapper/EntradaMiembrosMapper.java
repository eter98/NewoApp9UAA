package io.github.jhipster.application.service.mapper;

import io.github.jhipster.application.domain.*;
import io.github.jhipster.application.service.dto.EntradaMiembrosDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link EntradaMiembros} and its DTO {@link EntradaMiembrosDTO}.
 */
@Mapper(componentModel = "spring", uses = {UserMapper.class, EspacioLibreMapper.class, EspaciosReservaMapper.class})
public interface EntradaMiembrosMapper extends EntityMapper<EntradaMiembrosDTO, EntradaMiembros> {

    @Mapping(source = "miembro.id", target = "miembroId")
    @Mapping(source = "miembro.login", target = "miembroLogin")
    @Mapping(source = "espacio.id", target = "espacioId")
    @Mapping(source = "espacio.nombre", target = "espacioNombre")
    @Mapping(source = "espacioReserva.id", target = "espacioReservaId")
    @Mapping(source = "espacioReserva.nombre", target = "espacioReservaNombre")
    EntradaMiembrosDTO toDto(EntradaMiembros entradaMiembros);

    @Mapping(target = "registroCompras", ignore = true)
    @Mapping(target = "removeRegistroCompra", ignore = true)
    @Mapping(source = "miembroId", target = "miembro")
    @Mapping(source = "espacioId", target = "espacio")
    @Mapping(source = "espacioReservaId", target = "espacioReserva")
    EntradaMiembros toEntity(EntradaMiembrosDTO entradaMiembrosDTO);

    default EntradaMiembros fromId(Long id) {
        if (id == null) {
            return null;
        }
        EntradaMiembros entradaMiembros = new EntradaMiembros();
        entradaMiembros.setId(id);
        return entradaMiembros;
    }
}
