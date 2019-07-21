package io.github.jhipster.application.service.mapper;

import io.github.jhipster.application.domain.*;
import io.github.jhipster.application.service.dto.MiembrosDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Miembros} and its DTO {@link MiembrosDTO}.
 */
@Mapper(componentModel = "spring", uses = {UserMapper.class, PaisMapper.class, SedesMapper.class})
public interface MiembrosMapper extends EntityMapper<MiembrosDTO, Miembros> {

    @Mapping(source = "miembro.id", target = "miembroId")
    @Mapping(source = "miembro.login", target = "miembroLogin")
    @Mapping(source = "nacionalidad.id", target = "nacionalidadId")
    @Mapping(source = "nacionalidad.nombrePais", target = "nacionalidadNombrePais")
    @Mapping(source = "sede.id", target = "sedeId")
    @Mapping(source = "sede.nombreSede", target = "sedeNombreSede")
    MiembrosDTO toDto(Miembros miembros);

    @Mapping(source = "miembroId", target = "miembro")
    @Mapping(source = "nacionalidadId", target = "nacionalidad")
    @Mapping(source = "sedeId", target = "sede")
    Miembros toEntity(MiembrosDTO miembrosDTO);

    default Miembros fromId(Long id) {
        if (id == null) {
            return null;
        }
        Miembros miembros = new Miembros();
        miembros.setId(id);
        return miembros;
    }
}
