package io.github.jhipster.application.service.dto;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Lob;
import io.github.jhipster.application.domain.enumeration.Impuestod;

/**
 * A DTO for the {@link io.github.jhipster.application.domain.ProductosServicios} entity.
 */
public class ProductosServiciosDTO implements Serializable {

    private Long id;

    @NotNull
    private String nombreProducto;

    @NotNull
    private String descripcion;

    private Boolean inventariables;

    @NotNull
    private Integer valor;

    @NotNull
    private Impuestod impuesto;

    @Lob
    private byte[] video;

    private String videoContentType;
    @Lob
    private byte[] imagen1;

    private String imagen1ContentType;
    @Lob
    private byte[] imagen2;

    private String imagen2ContentType;
    @Lob
    private byte[] imagen3;

    private String imagen3ContentType;
    @Lob
    private byte[] imagen4;

    private String imagen4ContentType;
    @Lob
    private byte[] imagen5;

    private String imagen5ContentType;
    @Lob
    private byte[] imagen6;

    private String imagen6ContentType;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombreProducto() {
        return nombreProducto;
    }

    public void setNombreProducto(String nombreProducto) {
        this.nombreProducto = nombreProducto;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Boolean isInventariables() {
        return inventariables;
    }

    public void setInventariables(Boolean inventariables) {
        this.inventariables = inventariables;
    }

    public Integer getValor() {
        return valor;
    }

    public void setValor(Integer valor) {
        this.valor = valor;
    }

    public Impuestod getImpuesto() {
        return impuesto;
    }

    public void setImpuesto(Impuestod impuesto) {
        this.impuesto = impuesto;
    }

    public byte[] getVideo() {
        return video;
    }

    public void setVideo(byte[] video) {
        this.video = video;
    }

    public String getVideoContentType() {
        return videoContentType;
    }

    public void setVideoContentType(String videoContentType) {
        this.videoContentType = videoContentType;
    }

    public byte[] getImagen1() {
        return imagen1;
    }

    public void setImagen1(byte[] imagen1) {
        this.imagen1 = imagen1;
    }

    public String getImagen1ContentType() {
        return imagen1ContentType;
    }

    public void setImagen1ContentType(String imagen1ContentType) {
        this.imagen1ContentType = imagen1ContentType;
    }

    public byte[] getImagen2() {
        return imagen2;
    }

    public void setImagen2(byte[] imagen2) {
        this.imagen2 = imagen2;
    }

    public String getImagen2ContentType() {
        return imagen2ContentType;
    }

    public void setImagen2ContentType(String imagen2ContentType) {
        this.imagen2ContentType = imagen2ContentType;
    }

    public byte[] getImagen3() {
        return imagen3;
    }

    public void setImagen3(byte[] imagen3) {
        this.imagen3 = imagen3;
    }

    public String getImagen3ContentType() {
        return imagen3ContentType;
    }

    public void setImagen3ContentType(String imagen3ContentType) {
        this.imagen3ContentType = imagen3ContentType;
    }

    public byte[] getImagen4() {
        return imagen4;
    }

    public void setImagen4(byte[] imagen4) {
        this.imagen4 = imagen4;
    }

    public String getImagen4ContentType() {
        return imagen4ContentType;
    }

    public void setImagen4ContentType(String imagen4ContentType) {
        this.imagen4ContentType = imagen4ContentType;
    }

    public byte[] getImagen5() {
        return imagen5;
    }

    public void setImagen5(byte[] imagen5) {
        this.imagen5 = imagen5;
    }

    public String getImagen5ContentType() {
        return imagen5ContentType;
    }

    public void setImagen5ContentType(String imagen5ContentType) {
        this.imagen5ContentType = imagen5ContentType;
    }

    public byte[] getImagen6() {
        return imagen6;
    }

    public void setImagen6(byte[] imagen6) {
        this.imagen6 = imagen6;
    }

    public String getImagen6ContentType() {
        return imagen6ContentType;
    }

    public void setImagen6ContentType(String imagen6ContentType) {
        this.imagen6ContentType = imagen6ContentType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        ProductosServiciosDTO productosServiciosDTO = (ProductosServiciosDTO) o;
        if (productosServiciosDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), productosServiciosDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "ProductosServiciosDTO{" +
            "id=" + getId() +
            ", nombreProducto='" + getNombreProducto() + "'" +
            ", descripcion='" + getDescripcion() + "'" +
            ", inventariables='" + isInventariables() + "'" +
            ", valor=" + getValor() +
            ", impuesto='" + getImpuesto() + "'" +
            ", video='" + getVideo() + "'" +
            ", imagen1='" + getImagen1() + "'" +
            ", imagen2='" + getImagen2() + "'" +
            ", imagen3='" + getImagen3() + "'" +
            ", imagen4='" + getImagen4() + "'" +
            ", imagen5='" + getImagen5() + "'" +
            ", imagen6='" + getImagen6() + "'" +
            "}";
    }
}
