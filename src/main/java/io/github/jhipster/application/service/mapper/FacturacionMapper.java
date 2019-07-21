package io.github.jhipster.application.service.mapper;

import io.github.jhipster.application.domain.*;
import io.github.jhipster.application.service.dto.FacturacionDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Facturacion} and its DTO {@link FacturacionDTO}.
 */
@Mapper(componentModel = "spring", uses = {EmpresaMapper.class, CuentaAsociadaMapper.class})
public interface FacturacionMapper extends EntityMapper<FacturacionDTO, Facturacion> {

    @Mapping(source = "empresa.id", target = "empresaId")
    @Mapping(source = "empresa.razonSocial", target = "empresaRazonSocial")
    @Mapping(source = "cuentaAsociada.id", target = "cuentaAsociadaId")
    FacturacionDTO toDto(Facturacion facturacion);

    @Mapping(target = "registroCompras", ignore = true)
    @Mapping(target = "removeRegistroCompra", ignore = true)
    @Mapping(source = "empresaId", target = "empresa")
    @Mapping(source = "cuentaAsociadaId", target = "cuentaAsociada")
    Facturacion toEntity(FacturacionDTO facturacionDTO);

    default Facturacion fromId(Long id) {
        if (id == null) {
            return null;
        }
        Facturacion facturacion = new Facturacion();
        facturacion.setId(id);
        return facturacion;
    }
}
