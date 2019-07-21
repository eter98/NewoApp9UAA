package io.github.jhipster.application.service.mapper;

import io.github.jhipster.application.domain.*;
import io.github.jhipster.application.service.dto.HostSedeDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link HostSede} and its DTO {@link HostSedeDTO}.
 */
@Mapper(componentModel = "spring", uses = {SedesMapper.class, UserMapper.class})
public interface HostSedeMapper extends EntityMapper<HostSedeDTO, HostSede> {

    @Mapping(source = "sede.id", target = "sedeId")
    @Mapping(source = "sede.nombreSede", target = "sedeNombreSede")
    @Mapping(source = "miembro.id", target = "miembroId")
    @Mapping(source = "miembro.login", target = "miembroLogin")
    HostSedeDTO toDto(HostSede hostSede);

    @Mapping(source = "sedeId", target = "sede")
    @Mapping(source = "miembroId", target = "miembro")
    HostSede toEntity(HostSedeDTO hostSedeDTO);

    default HostSede fromId(Long id) {
        if (id == null) {
            return null;
        }
        HostSede hostSede = new HostSede();
        hostSede.setId(id);
        return hostSede;
    }
}
