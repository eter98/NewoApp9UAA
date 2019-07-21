package io.github.jhipster.application.service.dto;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link io.github.jhipster.application.domain.MargenNewoProductos} entity.
 */
public class MargenNewoProductosDTO implements Serializable {

    private Long id;

    private Integer porcentajeMargen;


    private Long productoId;

    private String productoNombreProducto;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getPorcentajeMargen() {
        return porcentajeMargen;
    }

    public void setPorcentajeMargen(Integer porcentajeMargen) {
        this.porcentajeMargen = porcentajeMargen;
    }

    public Long getProductoId() {
        return productoId;
    }

    public void setProductoId(Long productosServiciosId) {
        this.productoId = productosServiciosId;
    }

    public String getProductoNombreProducto() {
        return productoNombreProducto;
    }

    public void setProductoNombreProducto(String productosServiciosNombreProducto) {
        this.productoNombreProducto = productosServiciosNombreProducto;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        MargenNewoProductosDTO margenNewoProductosDTO = (MargenNewoProductosDTO) o;
        if (margenNewoProductosDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), margenNewoProductosDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "MargenNewoProductosDTO{" +
            "id=" + getId() +
            ", porcentajeMargen=" + getPorcentajeMargen() +
            ", producto=" + getProductoId() +
            ", producto='" + getProductoNombreProducto() + "'" +
            "}";
    }
}
