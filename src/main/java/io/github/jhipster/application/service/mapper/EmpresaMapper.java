package io.github.jhipster.application.service.mapper;

import io.github.jhipster.application.domain.*;
import io.github.jhipster.application.service.dto.EmpresaDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Empresa} and its DTO {@link EmpresaDTO}.
 */
@Mapper(componentModel = "spring", uses = {MiembrosMapper.class})
public interface EmpresaMapper extends EntityMapper<EmpresaDTO, Empresa> {

    @Mapping(source = "aliado.id", target = "aliadoId")
    @Mapping(source = "aliado.aliado", target = "aliadoAliado")
    EmpresaDTO toDto(Empresa empresa);

    @Mapping(source = "aliadoId", target = "aliado")
    Empresa toEntity(EmpresaDTO empresaDTO);

    default Empresa fromId(Long id) {
        if (id == null) {
            return null;
        }
        Empresa empresa = new Empresa();
        empresa.setId(id);
        return empresa;
    }
}
