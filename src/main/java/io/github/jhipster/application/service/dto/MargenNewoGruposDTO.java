package io.github.jhipster.application.service.dto;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link io.github.jhipster.application.domain.MargenNewoGrupos} entity.
 */
public class MargenNewoGruposDTO implements Serializable {

    private Long id;

    private Integer porcentajeMargen;


    private Long grupoId;

    private String grupoNombreGrupo;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getPorcentajeMargen() {
        return porcentajeMargen;
    }

    public void setPorcentajeMargen(Integer porcentajeMargen) {
        this.porcentajeMargen = porcentajeMargen;
    }

    public Long getGrupoId() {
        return grupoId;
    }

    public void setGrupoId(Long gruposId) {
        this.grupoId = gruposId;
    }

    public String getGrupoNombreGrupo() {
        return grupoNombreGrupo;
    }

    public void setGrupoNombreGrupo(String gruposNombreGrupo) {
        this.grupoNombreGrupo = gruposNombreGrupo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        MargenNewoGruposDTO margenNewoGruposDTO = (MargenNewoGruposDTO) o;
        if (margenNewoGruposDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), margenNewoGruposDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "MargenNewoGruposDTO{" +
            "id=" + getId() +
            ", porcentajeMargen=" + getPorcentajeMargen() +
            ", grupo=" + getGrupoId() +
            ", grupo='" + getGrupoNombreGrupo() + "'" +
            "}";
    }
}
