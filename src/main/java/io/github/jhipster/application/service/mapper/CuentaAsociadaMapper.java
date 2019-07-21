package io.github.jhipster.application.service.mapper;

import io.github.jhipster.application.domain.*;
import io.github.jhipster.application.service.dto.CuentaAsociadaDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link CuentaAsociada} and its DTO {@link CuentaAsociadaDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface CuentaAsociadaMapper extends EntityMapper<CuentaAsociadaDTO, CuentaAsociada> {


    @Mapping(target = "facturacions", ignore = true)
    @Mapping(target = "removeFacturacion", ignore = true)
    CuentaAsociada toEntity(CuentaAsociadaDTO cuentaAsociadaDTO);

    default CuentaAsociada fromId(Long id) {
        if (id == null) {
            return null;
        }
        CuentaAsociada cuentaAsociada = new CuentaAsociada();
        cuentaAsociada.setId(id);
        return cuentaAsociada;
    }
}
