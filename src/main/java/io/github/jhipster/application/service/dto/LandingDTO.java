package io.github.jhipster.application.service.dto;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Lob;
import io.github.jhipster.application.domain.enumeration.Impuestod;

/**
 * A DTO for the {@link io.github.jhipster.application.domain.Landing} entity.
 */
public class LandingDTO implements Serializable {

    private Long id;

    @NotNull
    private String nombre;

    @NotNull
    private String descripcion;

    private String telefonoNegocio;

    @NotNull
    private Integer numeroPuestos;

    @NotNull
    private Integer tarifa;

    @Lob
    private byte[] fotografia;

    private String fotografiaContentType;
    @NotNull
    private Impuestod impuesto;


    private Long sedeId;

    private String sedeNombreSede;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getTelefonoNegocio() {
        return telefonoNegocio;
    }

    public void setTelefonoNegocio(String telefonoNegocio) {
        this.telefonoNegocio = telefonoNegocio;
    }

    public Integer getNumeroPuestos() {
        return numeroPuestos;
    }

    public void setNumeroPuestos(Integer numeroPuestos) {
        this.numeroPuestos = numeroPuestos;
    }

    public Integer getTarifa() {
        return tarifa;
    }

    public void setTarifa(Integer tarifa) {
        this.tarifa = tarifa;
    }

    public byte[] getFotografia() {
        return fotografia;
    }

    public void setFotografia(byte[] fotografia) {
        this.fotografia = fotografia;
    }

    public String getFotografiaContentType() {
        return fotografiaContentType;
    }

    public void setFotografiaContentType(String fotografiaContentType) {
        this.fotografiaContentType = fotografiaContentType;
    }

    public Impuestod getImpuesto() {
        return impuesto;
    }

    public void setImpuesto(Impuestod impuesto) {
        this.impuesto = impuesto;
    }

    public Long getSedeId() {
        return sedeId;
    }

    public void setSedeId(Long sedesId) {
        this.sedeId = sedesId;
    }

    public String getSedeNombreSede() {
        return sedeNombreSede;
    }

    public void setSedeNombreSede(String sedesNombreSede) {
        this.sedeNombreSede = sedesNombreSede;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        LandingDTO landingDTO = (LandingDTO) o;
        if (landingDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), landingDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "LandingDTO{" +
            "id=" + getId() +
            ", nombre='" + getNombre() + "'" +
            ", descripcion='" + getDescripcion() + "'" +
            ", telefonoNegocio='" + getTelefonoNegocio() + "'" +
            ", numeroPuestos=" + getNumeroPuestos() +
            ", tarifa=" + getTarifa() +
            ", fotografia='" + getFotografia() + "'" +
            ", impuesto='" + getImpuesto() + "'" +
            ", sede=" + getSedeId() +
            ", sede='" + getSedeNombreSede() + "'" +
            "}";
    }
}
