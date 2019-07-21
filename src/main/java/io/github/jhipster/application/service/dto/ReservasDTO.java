package io.github.jhipster.application.service.dto;
import java.time.ZonedDateTime;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;
import io.github.jhipster.application.domain.enumeration.EstadoReservad;

/**
 * A DTO for the {@link io.github.jhipster.application.domain.Reservas} entity.
 */
public class ReservasDTO implements Serializable {

    private Long id;

    @NotNull
    private ZonedDateTime registroFechaEntrada;

    @NotNull
    private ZonedDateTime registroFechaSalida;

    private EstadoReservad estadoReserva;


    private Long miembroId;

    private String miembroLogin;

    private Long espacioId;

    private String espacioNombre;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ZonedDateTime getRegistroFechaEntrada() {
        return registroFechaEntrada;
    }

    public void setRegistroFechaEntrada(ZonedDateTime registroFechaEntrada) {
        this.registroFechaEntrada = registroFechaEntrada;
    }

    public ZonedDateTime getRegistroFechaSalida() {
        return registroFechaSalida;
    }

    public void setRegistroFechaSalida(ZonedDateTime registroFechaSalida) {
        this.registroFechaSalida = registroFechaSalida;
    }

    public EstadoReservad getEstadoReserva() {
        return estadoReserva;
    }

    public void setEstadoReserva(EstadoReservad estadoReserva) {
        this.estadoReserva = estadoReserva;
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

    public Long getEspacioId() {
        return espacioId;
    }

    public void setEspacioId(Long espaciosReservaId) {
        this.espacioId = espaciosReservaId;
    }

    public String getEspacioNombre() {
        return espacioNombre;
    }

    public void setEspacioNombre(String espaciosReservaNombre) {
        this.espacioNombre = espaciosReservaNombre;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        ReservasDTO reservasDTO = (ReservasDTO) o;
        if (reservasDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), reservasDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "ReservasDTO{" +
            "id=" + getId() +
            ", registroFechaEntrada='" + getRegistroFechaEntrada() + "'" +
            ", registroFechaSalida='" + getRegistroFechaSalida() + "'" +
            ", estadoReserva='" + getEstadoReserva() + "'" +
            ", miembro=" + getMiembroId() +
            ", miembro='" + getMiembroLogin() + "'" +
            ", espacio=" + getEspacioId() +
            ", espacio='" + getEspacioNombre() + "'" +
            "}";
    }
}
