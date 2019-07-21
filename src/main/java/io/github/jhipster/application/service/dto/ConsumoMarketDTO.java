package io.github.jhipster.application.service.dto;
import java.time.LocalDate;
import java.io.Serializable;
import java.util.Objects;
import io.github.jhipster.application.domain.enumeration.Impuestod;

/**
 * A DTO for the {@link io.github.jhipster.application.domain.ConsumoMarket} entity.
 */
public class ConsumoMarketDTO implements Serializable {

    private Long id;

    private Integer totalConsumido;

    private LocalDate registroFechaInicial;

    private LocalDate registroFechaFinal;

    private Impuestod impuesto;


    private Long miembroId;

    private String miembroLogin;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getTotalConsumido() {
        return totalConsumido;
    }

    public void setTotalConsumido(Integer totalConsumido) {
        this.totalConsumido = totalConsumido;
    }

    public LocalDate getRegistroFechaInicial() {
        return registroFechaInicial;
    }

    public void setRegistroFechaInicial(LocalDate registroFechaInicial) {
        this.registroFechaInicial = registroFechaInicial;
    }

    public LocalDate getRegistroFechaFinal() {
        return registroFechaFinal;
    }

    public void setRegistroFechaFinal(LocalDate registroFechaFinal) {
        this.registroFechaFinal = registroFechaFinal;
    }

    public Impuestod getImpuesto() {
        return impuesto;
    }

    public void setImpuesto(Impuestod impuesto) {
        this.impuesto = impuesto;
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

        ConsumoMarketDTO consumoMarketDTO = (ConsumoMarketDTO) o;
        if (consumoMarketDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), consumoMarketDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "ConsumoMarketDTO{" +
            "id=" + getId() +
            ", totalConsumido=" + getTotalConsumido() +
            ", registroFechaInicial='" + getRegistroFechaInicial() + "'" +
            ", registroFechaFinal='" + getRegistroFechaFinal() + "'" +
            ", impuesto='" + getImpuesto() + "'" +
            ", miembro=" + getMiembroId() +
            ", miembro='" + getMiembroLogin() + "'" +
            "}";
    }
}
