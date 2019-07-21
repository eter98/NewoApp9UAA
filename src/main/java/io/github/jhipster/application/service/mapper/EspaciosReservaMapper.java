package io.github.jhipster.application.service.mapper;

import io.github.jhipster.application.domain.*;
import io.github.jhipster.application.service.dto.EspaciosReservaDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link EspaciosReserva} and its DTO {@link EspaciosReservaDTO}.
 */
@Mapper(componentModel = "spring", uses = {SedesMapper.class})
public interface EspaciosReservaMapper extends EntityMapper<EspaciosReservaDTO, EspaciosReserva> {

    @Mapping(source = "sede.id", target = "sedeId")
    @Mapping(source = "sede.nombreSede", target = "sedeNombreSede")
    EspaciosReservaDTO toDto(EspaciosReserva espaciosReserva);

    @Mapping(source = "sedeId", target = "sede")
    EspaciosReserva toEntity(EspaciosReservaDTO espaciosReservaDTO);

    default EspaciosReserva fromId(Long id) {
        if (id == null) {
            return null;
        }
        EspaciosReserva espaciosReserva = new EspaciosReserva();
        espaciosReserva.setId(id);
        return espaciosReserva;
    }
}
