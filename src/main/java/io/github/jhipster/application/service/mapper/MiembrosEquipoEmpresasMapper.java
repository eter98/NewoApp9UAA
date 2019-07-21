package io.github.jhipster.application.service.mapper;

import io.github.jhipster.application.domain.*;
import io.github.jhipster.application.service.dto.MiembrosEquipoEmpresasDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link MiembrosEquipoEmpresas} and its DTO {@link MiembrosEquipoEmpresasDTO}.
 */
@Mapper(componentModel = "spring", uses = {EmpresaMapper.class, EquipoEmpresasMapper.class, UserMapper.class})
public interface MiembrosEquipoEmpresasMapper extends EntityMapper<MiembrosEquipoEmpresasDTO, MiembrosEquipoEmpresas> {

    @Mapping(source = "empresa.id", target = "empresaId")
    @Mapping(source = "empresa.razonSocial", target = "empresaRazonSocial")
    @Mapping(source = "equipo.id", target = "equipoId")
    @Mapping(source = "equipo.nombre", target = "equipoNombre")
    @Mapping(source = "miembro.id", target = "miembroId")
    @Mapping(source = "miembro.login", target = "miembroLogin")
    MiembrosEquipoEmpresasDTO toDto(MiembrosEquipoEmpresas miembrosEquipoEmpresas);

    @Mapping(source = "empresaId", target = "empresa")
    @Mapping(source = "equipoId", target = "equipo")
    @Mapping(source = "miembroId", target = "miembro")
    MiembrosEquipoEmpresas toEntity(MiembrosEquipoEmpresasDTO miembrosEquipoEmpresasDTO);

    default MiembrosEquipoEmpresas fromId(Long id) {
        if (id == null) {
            return null;
        }
        MiembrosEquipoEmpresas miembrosEquipoEmpresas = new MiembrosEquipoEmpresas();
        miembrosEquipoEmpresas.setId(id);
        return miembrosEquipoEmpresas;
    }
}
