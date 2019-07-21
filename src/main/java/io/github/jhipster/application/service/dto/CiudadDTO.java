package io.github.jhipster.application.service.dto;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link io.github.jhipster.application.domain.Ciudad} entity.
 */
public class CiudadDTO implements Serializable {

    private Long id;

    @NotNull
    private String nombreCiudad;


    private Long paisId;

    private String paisNombrePais;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombreCiudad() {
        return nombreCiudad;
    }

    public void setNombreCiudad(String nombreCiudad) {
        this.nombreCiudad = nombreCiudad;
    }

    public Long getPaisId() {
        return paisId;
    }

    public void setPaisId(Long paisId) {
        this.paisId = paisId;
    }

    public String getPaisNombrePais() {
        return paisNombrePais;
    }

    public void setPaisNombrePais(String paisNombrePais) {
        this.paisNombrePais = paisNombrePais;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        CiudadDTO ciudadDTO = (CiudadDTO) o;
        if (ciudadDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), ciudadDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "CiudadDTO{" +
            "id=" + getId() +
            ", nombreCiudad='" + getNombreCiudad() + "'" +
            ", pais=" + getPaisId() +
            ", pais='" + getPaisNombrePais() + "'" +
            "}";
    }
}
