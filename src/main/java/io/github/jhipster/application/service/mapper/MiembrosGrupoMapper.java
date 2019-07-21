package io.github.jhipster.application.service.mapper;

import io.github.jhipster.application.domain.*;
import io.github.jhipster.application.service.dto.MiembrosGrupoDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link MiembrosGrupo} and its DTO {@link MiembrosGrupoDTO}.
 */
@Mapper(componentModel = "spring", uses = {UserMapper.class})
public interface MiembrosGrupoMapper extends EntityMapper<MiembrosGrupoDTO, MiembrosGrupo> {

    @Mapping(source = "miembro.id", target = "miembroId")
    @Mapping(source = "miembro.login", target = "miembroLogin")
    MiembrosGrupoDTO toDto(MiembrosGrupo miembrosGrupo);

    @Mapping(source = "miembroId", target = "miembro")
    MiembrosGrupo toEntity(MiembrosGrupoDTO miembrosGrupoDTO);

    default MiembrosGrupo fromId(Long id) {
        if (id == null) {
            return null;
        }
        MiembrosGrupo miembrosGrupo = new MiembrosGrupo();
        miembrosGrupo.setId(id);
        return miembrosGrupo;
    }
}
