package io.github.jhipster.application.service.dto;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link io.github.jhipster.application.domain.MiembrosGrupo} entity.
 */
public class MiembrosGrupoDTO implements Serializable {

    private Long id;


    private Long miembroId;

    private String miembroLogin;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

        MiembrosGrupoDTO miembrosGrupoDTO = (MiembrosGrupoDTO) o;
        if (miembrosGrupoDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), miembrosGrupoDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "MiembrosGrupoDTO{" +
            "id=" + getId() +
            ", miembro=" + getMiembroId() +
            ", miembro='" + getMiembroLogin() + "'" +
            "}";
    }
}
