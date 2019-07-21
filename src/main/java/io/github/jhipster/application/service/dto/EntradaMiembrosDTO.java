package io.github.jhipster.application.service.dto;
import java.time.ZonedDateTime;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;
import io.github.jhipster.application.domain.enumeration.TipoEntradad;
import io.github.jhipster.application.domain.enumeration.TipoIngresod;

/**
 * A DTO for the {@link io.github.jhipster.application.domain.EntradaMiembros} entity.
 */
public class EntradaMiembrosDTO implements Serializable {

    private Long id;

    @NotNull
    private ZonedDateTime registroFecha;

    private TipoEntradad tipoEntrada;

    private TipoIngresod tipoIngreso;

    private Boolean tiempoMaximo;


    private Long miembroId;

    private String miembroLogin;

    private Long espacioId;

    private String espacioNombre;

    private Long espacioReservaId;

    private String espacioReservaNombre;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ZonedDateTime getRegistroFecha() {
        return registroFecha;
    }

    public void setRegistroFecha(ZonedDateTime registroFecha) {
        this.registroFecha = registroFecha;
    }

    public TipoEntradad getTipoEntrada() {
        return tipoEntrada;
    }

    public void setTipoEntrada(TipoEntradad tipoEntrada) {
        this.tipoEntrada = tipoEntrada;
    }

    public TipoIngresod getTipoIngreso() {
        return tipoIngreso;
    }

    public void setTipoIngreso(TipoIngresod tipoIngreso) {
        this.tipoIngreso = tipoIngreso;
    }

    public Boolean isTiempoMaximo() {
        return tiempoMaximo;
    }

    public void setTiempoMaximo(Boolean tiempoMaximo) {
        this.tiempoMaximo = tiempoMaximo;
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

    public void setEspacioId(Long espacioLibreId) {
        this.espacioId = espacioLibreId;
    }

    public String getEspacioNombre() {
        return espacioNombre;
    }

    public void setEspacioNombre(String espacioLibreNombre) {
        this.espacioNombre = espacioLibreNombre;
    }

    public Long getEspacioReservaId() {
        return espacioReservaId;
    }

    public void setEspacioReservaId(Long espaciosReservaId) {
        this.espacioReservaId = espaciosReservaId;
    }

    public String getEspacioReservaNombre() {
        return espacioReservaNombre;
    }

    public void setEspacioReservaNombre(String espaciosReservaNombre) {
        this.espacioReservaNombre = espaciosReservaNombre;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        EntradaMiembrosDTO entradaMiembrosDTO = (EntradaMiembrosDTO) o;
        if (entradaMiembrosDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), entradaMiembrosDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "EntradaMiembrosDTO{" +
            "id=" + getId() +
            ", registroFecha='" + getRegistroFecha() + "'" +
            ", tipoEntrada='" + getTipoEntrada() + "'" +
            ", tipoIngreso='" + getTipoIngreso() + "'" +
            ", tiempoMaximo='" + isTiempoMaximo() + "'" +
            ", miembro=" + getMiembroId() +
            ", miembro='" + getMiembroLogin() + "'" +
            ", espacio=" + getEspacioId() +
            ", espacio='" + getEspacioNombre() + "'" +
            ", espacioReserva=" + getEspacioReservaId() +
            ", espacioReserva='" + getEspacioReservaNombre() + "'" +
            "}";
    }
}
