package io.github.jhipster.application.service.mapper;

import io.github.jhipster.application.domain.*;
import io.github.jhipster.application.service.dto.PerfilEquipoEmpresaDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link PerfilEquipoEmpresa} and its DTO {@link PerfilEquipoEmpresaDTO}.
 */
@Mapper(componentModel = "spring", uses = {EmpresaMapper.class})
public interface PerfilEquipoEmpresaMapper extends EntityMapper<PerfilEquipoEmpresaDTO, PerfilEquipoEmpresa> {

    @Mapping(source = "empresa.id", target = "empresaId")
    @Mapping(source = "empresa.razonSocial", target = "empresaRazonSocial")
    PerfilEquipoEmpresaDTO toDto(PerfilEquipoEmpresa perfilEquipoEmpresa);

    @Mapping(source = "empresaId", target = "empresa")
    PerfilEquipoEmpresa toEntity(PerfilEquipoEmpresaDTO perfilEquipoEmpresaDTO);

    default PerfilEquipoEmpresa fromId(Long id) {
        if (id == null) {
            return null;
        }
        PerfilEquipoEmpresa perfilEquipoEmpresa = new PerfilEquipoEmpresa();
        perfilEquipoEmpresa.setId(id);
        return perfilEquipoEmpresa;
    }
}
