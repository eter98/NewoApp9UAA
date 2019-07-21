package io.github.jhipster.application.service.dto;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link io.github.jhipster.application.domain.MiembrosEquipoEmpresas} entity.
 */
public class MiembrosEquipoEmpresasDTO implements Serializable {

    private Long id;


    private Long empresaId;

    private String empresaRazonSocial;

    private Long equipoId;

    private String equipoNombre;

    private Long miembroId;

    private String miembroLogin;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Long getEquipoId() {
        return equipoId;
    }

    public void setEquipoId(Long equipoEmpresasId) {
        this.equipoId = equipoEmpresasId;
    }

    public String getEquipoNombre() {
        return equipoNombre;
    }

    public void setEquipoNombre(String equipoEmpresasNombre) {
        this.equipoNombre = equipoEmpresasNombre;
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

        MiembrosEquipoEmpresasDTO miembrosEquipoEmpresasDTO = (MiembrosEquipoEmpresasDTO) o;
        if (miembrosEquipoEmpresasDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), miembrosEquipoEmpresasDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "MiembrosEquipoEmpresasDTO{" +
            "id=" + getId() +
            ", empresa=" + getEmpresaId() +
            ", empresa='" + getEmpresaRazonSocial() + "'" +
            ", equipo=" + getEquipoId() +
            ", equipo='" + getEquipoNombre() + "'" +
            ", miembro=" + getMiembroId() +
            ", miembro='" + getMiembroLogin() + "'" +
            "}";
    }
}
