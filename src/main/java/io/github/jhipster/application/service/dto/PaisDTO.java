package io.github.jhipster.application.service.dto;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link io.github.jhipster.application.domain.Pais} entity.
 */
public class PaisDTO implements Serializable {

    private Long id;

    @NotNull
    private String nombrePais;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombrePais() {
        return nombrePais;
    }

    public void setNombrePais(String nombrePais) {
        this.nombrePais = nombrePais;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        PaisDTO paisDTO = (PaisDTO) o;
        if (paisDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), paisDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "PaisDTO{" +
            "id=" + getId() +
            ", nombrePais='" + getNombrePais() + "'" +
            "}";
    }
}
