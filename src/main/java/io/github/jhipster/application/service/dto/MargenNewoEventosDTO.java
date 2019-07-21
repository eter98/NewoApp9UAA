package io.github.jhipster.application.service.dto;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link io.github.jhipster.application.domain.MargenNewoEventos} entity.
 */
public class MargenNewoEventosDTO implements Serializable {

    private Long id;

    private Integer porcentajeMargen;


    private Long eventoId;

    private String eventoNombreEvento;

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

    public Long getEventoId() {
        return eventoId;
    }

    public void setEventoId(Long eventoId) {
        this.eventoId = eventoId;
    }

    public String getEventoNombreEvento() {
        return eventoNombreEvento;
    }

    public void setEventoNombreEvento(String eventoNombreEvento) {
        this.eventoNombreEvento = eventoNombreEvento;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        MargenNewoEventosDTO margenNewoEventosDTO = (MargenNewoEventosDTO) o;
        if (margenNewoEventosDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), margenNewoEventosDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "MargenNewoEventosDTO{" +
            "id=" + getId() +
            ", porcentajeMargen=" + getPorcentajeMargen() +
            ", evento=" + getEventoId() +
            ", evento='" + getEventoNombreEvento() + "'" +
            "}";
    }
}
