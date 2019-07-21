package io.github.jhipster.application.service.dto;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link io.github.jhipster.application.domain.RegistroCompra} entity.
 */
public class RegistroCompraDTO implements Serializable {

    private Long id;

    private Boolean consumoMarket;


    private Long miembroId;

    private String miembroLogin;

    private Long facturacionId;

    private Long reservasId;

    private Long entradaInvitadosId;

    private Long entradaMiembrosId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Boolean isConsumoMarket() {
        return consumoMarket;
    }

    public void setConsumoMarket(Boolean consumoMarket) {
        this.consumoMarket = consumoMarket;
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

    public Long getFacturacionId() {
        return facturacionId;
    }

    public void setFacturacionId(Long facturacionId) {
        this.facturacionId = facturacionId;
    }

    public Long getReservasId() {
        return reservasId;
    }

    public void setReservasId(Long reservasId) {
        this.reservasId = reservasId;
    }

    public Long getEntradaInvitadosId() {
        return entradaInvitadosId;
    }

    public void setEntradaInvitadosId(Long entradaInvitadosId) {
        this.entradaInvitadosId = entradaInvitadosId;
    }

    public Long getEntradaMiembrosId() {
        return entradaMiembrosId;
    }

    public void setEntradaMiembrosId(Long entradaMiembrosId) {
        this.entradaMiembrosId = entradaMiembrosId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        RegistroCompraDTO registroCompraDTO = (RegistroCompraDTO) o;
        if (registroCompraDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), registroCompraDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "RegistroCompraDTO{" +
            "id=" + getId() +
            ", consumoMarket='" + isConsumoMarket() + "'" +
            ", miembro=" + getMiembroId() +
            ", miembro='" + getMiembroLogin() + "'" +
            ", facturacion=" + getFacturacionId() +
            ", reservas=" + getReservasId() +
            ", entradaInvitados=" + getEntradaInvitadosId() +
            ", entradaMiembros=" + getEntradaMiembrosId() +
            "}";
    }
}
