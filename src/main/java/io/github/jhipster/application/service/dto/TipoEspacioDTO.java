package io.github.jhipster.application.service.dto;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link io.github.jhipster.application.domain.TipoEspacio} entity.
 */
public class TipoEspacioDTO implements Serializable {

    private Long id;

    @NotNull
    private String tipoEspacio;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTipoEspacio() {
        return tipoEspacio;
    }

    public void setTipoEspacio(String tipoEspacio) {
        this.tipoEspacio = tipoEspacio;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        TipoEspacioDTO tipoEspacioDTO = (TipoEspacioDTO) o;
        if (tipoEspacioDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), tipoEspacioDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "TipoEspacioDTO{" +
            "id=" + getId() +
            ", tipoEspacio='" + getTipoEspacio() + "'" +
            "}";
    }
}
