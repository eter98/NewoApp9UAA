package io.github.jhipster.application.service.mapper;

import io.github.jhipster.application.domain.*;
import io.github.jhipster.application.service.dto.EquipoEmpresasDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link EquipoEmpresas} and its DTO {@link EquipoEmpresasDTO}.
 */
@Mapper(componentModel = "spring", uses = {UserMapper.class, EmpresaMapper.class})
public interface EquipoEmpresasMapper extends EntityMapper<EquipoEmpresasDTO, EquipoEmpresas> {

    @Mapping(source = "miembro.id", target = "miembroId")
    @Mapping(source = "miembro.login", target = "miembroLogin")
    @Mapping(source = "empresa.id", target = "empresaId")
    @Mapping(source = "empresa.razonSocial", target = "empresaRazonSocial")
    EquipoEmpresasDTO toDto(EquipoEmpresas equipoEmpresas);

    @Mapping(source = "miembroId", target = "miembro")
    @Mapping(source = "empresaId", target = "empresa")
    EquipoEmpresas toEntity(EquipoEmpresasDTO equipoEmpresasDTO);

    default EquipoEmpresas fromId(Long id) {
        if (id == null) {
            return null;
        }
        EquipoEmpresas equipoEmpresas = new EquipoEmpresas();
        equipoEmpresas.setId(id);
        return equipoEmpresas;
    }
}
