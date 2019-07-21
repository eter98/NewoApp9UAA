package io.github.jhipster.application.service.dto;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Lob;

/**
 * A DTO for the {@link io.github.jhipster.application.domain.EquipoEmpresas} entity.
 */
public class EquipoEmpresasDTO implements Serializable {

    private Long id;

    @NotNull
    private String nombre;

    private String telefono;

    private String correo;

    private String direccion;

    @Lob
    private String descripcion;

    @Lob
    private byte[] logos;

    private String logosContentType;
    private String paginaWeb;


    private Long miembroId;

    private String miembroLogin;

    private Long empresaId;

    private String empresaRazonSocial;

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

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public byte[] getLogos() {
        return logos;
    }

    public void setLogos(byte[] logos) {
        this.logos = logos;
    }

    public String getLogosContentType() {
        return logosContentType;
    }

    public void setLogosContentType(String logosContentType) {
        this.logosContentType = logosContentType;
    }

    public String getPaginaWeb() {
        return paginaWeb;
    }

    public void setPaginaWeb(String paginaWeb) {
        this.paginaWeb = paginaWeb;
    }

    public Long getMiembroId() {
        return miembroId;
    }

    public void setMiembroId(Long userId) {
        this.miembroId = userId;
    }

    public String getMiembroLogin() {
        return miembroLogin;
    }

    public void setMiembroLogin(String userLogin) {
        this.miembroLogin = userLogin;
    }

    public Long getEmpresaId() {
        return empresaId;
    }

    public void setEmpresaId(Long empresaId) {
        this.empresaId = empresaId;
    }

    public String getEmpresaRazonSocial() {
        return empresaRazonSocial;
    }

    public void setEmpresaRazonSocial(String empresaRazonSocial) {
        this.empresaRazonSocial = empresaRazonSocial;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        EquipoEmpresasDTO equipoEmpresasDTO = (EquipoEmpresasDTO) o;
        if (equipoEmpresasDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), equipoEmpresasDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "EquipoEmpresasDTO{" +
            "id=" + getId() +
            ", nombre='" + getNombre() + "'" +
            ", telefono='" + getTelefono() + "'" +
            ", correo='" + getCorreo() + "'" +
            ", direccion='" + getDireccion() + "'" +
            ", descripcion='" + getDescripcion() + "'" +
            ", logos='" + getLogos() + "'" +
            ", paginaWeb='" + getPaginaWeb() + "'" +
            ", miembro=" + getMiembroId() +
            ", miembro='" + getMiembroLogin() + "'" +
            ", empresa=" + getEmpresaId() +
            ", empresa='" + getEmpresaRazonSocial() + "'" +
            "}";
    }
}
