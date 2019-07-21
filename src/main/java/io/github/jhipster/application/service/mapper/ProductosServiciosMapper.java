package io.github.jhipster.application.service.mapper;

import io.github.jhipster.application.domain.*;
import io.github.jhipster.application.service.dto.ProductosServiciosDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link ProductosServicios} and its DTO {@link ProductosServiciosDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface ProductosServiciosMapper extends EntityMapper<ProductosServiciosDTO, ProductosServicios> {



    default ProductosServicios fromId(Long id) {
        if (id == null) {
            return null;
        }
        ProductosServicios productosServicios = new ProductosServicios();
        productosServicios.setId(id);
        return productosServicios;
    }
}
