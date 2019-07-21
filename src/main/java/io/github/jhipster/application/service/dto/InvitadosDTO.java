package io.github.jhipster.application.service.dto;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;
import io.github.jhipster.application.domain.enumeration.TipoDocumentod;

/**
 * A DTO for the {@link io.github.jhipster.application.domain.Invitados} entity.
 */
public class InvitadosDTO implements Serializable {

    private Long id;

    @NotNull
    @Size(min = 3, max = 25)
    private String nombre;

    @NotNull
    @Size(min = 3, max = 25)
    private String apellido;

    @NotNull
    private TipoDocumentod tipoDocumento;

    @NotNull
    @Size(min = 6, max = 12)
    private String identificacion;

    @NotNull
    private String correo;

    @Size(min = 7, max = 12)
    private String telefono;


    private Long miembroId;

    private String miembroLogin;

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

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public TipoDocumentod getTipoDocumento() {
        return tipoDocumento;
    }

    public void setTipoDocumento(TipoDocumentod tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
    }

    public String getIdentificacion() {
        return identificacion;
    }

    public void setIdentificacion(String identificacion) {
        this.identificacion = identificacion;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        InvitadosDTO invitadosDTO = (InvitadosDTO) o;
        if (invitadosDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), invitadosDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "InvitadosDTO{" +
            "id=" + getId() +
            ", nombre='" + getNombre() + "'" +
            ", apellido='" + getApellido() + "'" +
            ", tipoDocumento='" + getTipoDocumento() + "'" +
            ", identificacion='" + getIdentificacion() + "'" +
            ", correo='" + getCorreo() + "'" +
            ", telefono='" + getTelefono() + "'" +
            ", miembro=" + getMiembroId() +
            ", miembro='" + getMiembroLogin() + "'" +
            "}";
    }
}
