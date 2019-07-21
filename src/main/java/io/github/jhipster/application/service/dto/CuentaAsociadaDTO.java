package io.github.jhipster.application.service.dto;
import java.time.LocalDate;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link io.github.jhipster.application.domain.CuentaAsociada} entity.
 */
public class CuentaAsociadaDTO implements Serializable {

    private Long id;

    @NotNull
    private String identificaciontitular;

    @NotNull
    private String nombreTitular;

    @NotNull
    private String apellidoTitular;

    @NotNull
    private String numeroCuenta;

    @NotNull
    private String tipoCuenta;

    @NotNull
    private String codigoSeguridad;

    @NotNull
    private LocalDate fechaVencimiento;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIdentificaciontitular() {
        return identificaciontitular;
    }

    public void setIdentificaciontitular(String identificaciontitular) {
        this.identificaciontitular = identificaciontitular;
    }

    public String getNombreTitular() {
        return nombreTitular;
    }

    public void setNombreTitular(String nombreTitular) {
        this.nombreTitular = nombreTitular;
    }

    public String getApellidoTitular() {
        return apellidoTitular;
    }

    public void setApellidoTitular(String apellidoTitular) {
        this.apellidoTitular = apellidoTitular;
    }

    public String getNumeroCuenta() {
        return numeroCuenta;
    }

    public void setNumeroCuenta(String numeroCuenta) {
        this.numeroCuenta = numeroCuenta;
    }

    public String getTipoCuenta() {
        return tipoCuenta;
    }

    public void setTipoCuenta(String tipoCuenta) {
        this.tipoCuenta = tipoCuenta;
    }

    public String getCodigoSeguridad() {
        return codigoSeguridad;
    }

    public void setCodigoSeguridad(String codigoSeguridad) {
        this.codigoSeguridad = codigoSeguridad;
    }

    public LocalDate getFechaVencimiento() {
        return fechaVencimiento;
    }

    public void setFechaVencimiento(LocalDate fechaVencimiento) {
        this.fechaVencimiento = fechaVencimiento;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        CuentaAsociadaDTO cuentaAsociadaDTO = (CuentaAsociadaDTO) o;
        if (cuentaAsociadaDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), cuentaAsociadaDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "CuentaAsociadaDTO{" +
            "id=" + getId() +
            ", identificaciontitular='" + getIdentificaciontitular() + "'" +
            ", nombreTitular='" + getNombreTitular() + "'" +
            ", apellidoTitular='" + getApellidoTitular() + "'" +
            ", numeroCuenta='" + getNumeroCuenta() + "'" +
            ", tipoCuenta='" + getTipoCuenta() + "'" +
            ", codigoSeguridad='" + getCodigoSeguridad() + "'" +
            ", fechaVencimiento='" + getFechaVencimiento() + "'" +
            "}";
    }
}
