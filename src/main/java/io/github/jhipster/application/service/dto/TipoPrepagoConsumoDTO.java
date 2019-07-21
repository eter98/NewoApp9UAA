package io.github.jhipster.application.service.dto;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link io.github.jhipster.application.domain.TipoPrepagoConsumo} entity.
 */
public class TipoPrepagoConsumoDTO implements Serializable {

    private Long id;

    @NotNull
    private String nombre;

    @NotNull
    private String descripcion;

    @NotNull
    private Integer valorMinimo;

    @NotNull
    private Integer valorMaximo;


    private Long tipoBeneficioId;

    private String tipoBeneficioTipoBeneficio;

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

    public Integer getValorMinimo() {
        return valorMinimo;
    }

    public void setValorMinimo(Integer valorMinimo) {
        this.valorMinimo = valorMinimo;
    }

    public Integer getValorMaximo() {
        return valorMaximo;
    }

    public void setValorMaximo(Integer valorMaximo) {
        this.valorMaximo = valorMaximo;
    }

    public Long getTipoBeneficioId() {
        return tipoBeneficioId;
    }

    public void setTipoBeneficioId(Long beneficioId) {
        this.tipoBeneficioId = beneficioId;
    }

    public String getTipoBeneficioTipoBeneficio() {
        return tipoBeneficioTipoBeneficio;
    }

    public void setTipoBeneficioTipoBeneficio(String beneficioTipoBeneficio) {
        this.tipoBeneficioTipoBeneficio = beneficioTipoBeneficio;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        TipoPrepagoConsumoDTO tipoPrepagoConsumoDTO = (TipoPrepagoConsumoDTO) o;
        if (tipoPrepagoConsumoDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), tipoPrepagoConsumoDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "TipoPrepagoConsumoDTO{" +
            "id=" + getId() +
            ", nombre='" + getNombre() + "'" +
            ", descripcion='" + getDescripcion() + "'" +
            ", valorMinimo=" + getValorMinimo() +
            ", valorMaximo=" + getValorMaximo() +
            ", tipoBeneficio=" + getTipoBeneficioId() +
            ", tipoBeneficio='" + getTipoBeneficioTipoBeneficio() + "'" +
            "}";
    }
}
