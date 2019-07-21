package io.github.jhipster.application.service.mapper;

import io.github.jhipster.application.domain.*;
import io.github.jhipster.application.service.dto.SedesDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Sedes} and its DTO {@link SedesDTO}.
 */
@Mapper(componentModel = "spring", uses = {CiudadMapper.class})
public interface SedesMapper extends EntityMapper<SedesDTO, Sedes> {

    @Mapping(source = "ciudad.id", target = "ciudadId")
    @Mapping(source = "ciudad.nombreCiudad", target = "ciudadNombreCiudad")
    SedesDTO toDto(Sedes sedes);

    @Mapping(source = "ciudadId", target = "ciudad")
    Sedes toEntity(SedesDTO sedesDTO);

    default Sedes fromId(Long id) {
        if (id == null) {
            return null;
        }
        Sedes sedes = new Sedes();
        sedes.setId(id);
        return sedes;
    }
}
