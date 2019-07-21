package io.github.jhipster.application.service.mapper;

import io.github.jhipster.application.domain.*;
import io.github.jhipster.application.service.dto.MargenNewoProductosDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link MargenNewoProductos} and its DTO {@link MargenNewoProductosDTO}.
 */
@Mapper(componentModel = "spring", uses = {ProductosServiciosMapper.class})
public interface MargenNewoProductosMapper extends EntityMapper<MargenNewoProductosDTO, MargenNewoProductos> {

    @Mapping(source = "producto.id", target = "productoId")
    @Mapping(source = "producto.nombreProducto", target = "productoNombreProducto")
    MargenNewoProductosDTO toDto(MargenNewoProductos margenNewoProductos);

    @Mapping(source = "productoId", target = "producto")
    MargenNewoProductos toEntity(MargenNewoProductosDTO margenNewoProductosDTO);

    default MargenNewoProductos fromId(Long id) {
        if (id == null) {
            return null;
        }
        MargenNewoProductos margenNewoProductos = new MargenNewoProductos();
        margenNewoProductos.setId(id);
        return margenNewoProductos;
    }
}
