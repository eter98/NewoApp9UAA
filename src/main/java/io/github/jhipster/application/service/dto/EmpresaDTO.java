package io.github.jhipster.application.service.dto;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link io.github.jhipster.application.domain.Empresa} entity.
 */
public class EmpresaDTO implements Serializable {

    private Long id;

    @NotNull
    private String razonSocial;

    @NotNull
    @Size(min = 9, max = 13)
    private String nit;

    private String direccion;

    private String telefono;

    private String correo;

    private String celular;


    private Long aliadoId;

    private String aliadoAliado;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRazonSocial() {
        return razonSocial;
    }

    public void setRazonSocial(String razonSocial) {
        this.razonSocial = razonSocial;
    }

    public String getNit() {
        return nit;
    }

    public void setNit(String nit) {
        this.nit = nit;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public Long getAliadoId() {
        return aliadoId;
    }

    public void setAliadoId(Long miembrosId) {
        this.aliadoId = miembrosId;
    }

    public String getAliadoAliado() {
        return aliadoAliado;
    }

    public void setAliadoAliado(String miembrosAliado) {
        this.aliadoAliado = miembrosAliado;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        EmpresaDTO empresaDTO = (EmpresaDTO) o;
        if (empresaDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), empresaDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "EmpresaDTO{" +
            "id=" + getId() +
            ", razonSocial='" + getRazonSocial() + "'" +
            ", nit='" + getNit() + "'" +
            ", direccion='" + getDireccion() + "'" +
            ", telefono='" + getTelefono() + "'" +
            ", correo='" + getCorreo() + "'" +
            ", celular='" + getCelular() + "'" +
            ", aliado=" + getAliadoId() +
            ", aliado='" + getAliadoAliado() + "'" +
            "}";
    }
}
