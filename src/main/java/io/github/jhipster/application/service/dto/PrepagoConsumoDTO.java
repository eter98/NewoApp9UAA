package io.github.jhipster.application.service.dto;
import java.time.LocalDate;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link io.github.jhipster.application.domain.PrepagoConsumo} entity.
 */
public class PrepagoConsumoDTO implements Serializable {

    private Long id;

    private Integer aporte;

    private Integer saldoActual;

    private LocalDate fechaRegistro;

    private LocalDate fechaSaldoActual;


    private Long miembroId;

    private String miembroLogin;

    private Long tipoPrepagoId;

    private String tipoPrepagoNombre;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getAporte() {
        return aporte;
    }

    public void setAporte(Integer aporte) {
        this.aporte = aporte;
    }

    public Integer getSaldoActual() {
        return saldoActual;
    }

    public void setSaldoActual(Integer saldoActual) {
        this.saldoActual = saldoActual;
    }

    public LocalDate getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(LocalDate fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public LocalDate getFechaSaldoActual() {
        return fechaSaldoActual;
    }

    public void setFechaSaldoActual(LocalDate fechaSaldoActual) {
        this.fechaSaldoActual = fechaSaldoActual;
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

    public Long getTipoPrepagoId() {
        return tipoPrepagoId;
    }

    public void setTipoPrepagoId(Long tipoPrepagoConsumoId) {
        this.tipoPrepagoId = tipoPrepagoConsumoId;
    }

    public String getTipoPrepagoNombre() {
        return tipoPrepagoNombre;
    }

    public void setTipoPrepagoNombre(String tipoPrepagoConsumoNombre) {
        this.tipoPrepagoNombre = tipoPrepagoConsumoNombre;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        PrepagoConsumoDTO prepagoConsumoDTO = (PrepagoConsumoDTO) o;
        if (prepagoConsumoDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), prepagoConsumoDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "PrepagoConsumoDTO{" +
            "id=" + getId() +
            ", aporte=" + getAporte() +
            ", saldoActual=" + getSaldoActual() +
            ", fechaRegistro='" + getFechaRegistro() + "'" +
            ", fechaSaldoActual='" + getFechaSaldoActual() + "'" +
            ", miembro=" + getMiembroId() +
            ", miembro='" + getMiembroLogin() + "'" +
            ", tipoPrepago=" + getTipoPrepagoId() +
            ", tipoPrepago='" + getTipoPrepagoNombre() + "'" +
            "}";
    }
}
