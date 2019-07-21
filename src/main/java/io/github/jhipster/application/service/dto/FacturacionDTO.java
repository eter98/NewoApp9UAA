package io.github.jhipster.application.service.dto;
import java.io.Serializable;
import java.util.Objects;
import io.github.jhipster.application.domain.enumeration.TipoPersonad;
import io.github.jhipster.application.domain.enumeration.PeriodicidadFacturaciond;

/**
 * A DTO for the {@link io.github.jhipster.application.domain.Facturacion} entity.
 */
public class FacturacionDTO implements Serializable {

    private Long id;

    private String titularFactura;

    private TipoPersonad tipoPersona;

    private PeriodicidadFacturaciond periodicidadFacturacion;

    private Integer maximoMonto;


    private Long empresaId;

    private String empresaRazonSocial;

    private Long cuentaAsociadaId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitularFactura() {
        return titularFactura;
    }

    public void setTitularFactura(String titularFactura) {
        this.titularFactura = titularFactura;
    }

    public TipoPersonad getTipoPersona() {
        return tipoPersona;
    }

    public void setTipoPersona(TipoPersonad tipoPersona) {
        this.tipoPersona = tipoPersona;
    }

    public PeriodicidadFacturaciond getPeriodicidadFacturacion() {
        return periodicidadFacturacion;
    }

    public void setPeriodicidadFacturacion(PeriodicidadFacturaciond periodicidadFacturacion) {
        this.periodicidadFacturacion = periodicidadFacturacion;
    }

    public Integer getMaximoMonto() {
        return maximoMonto;
    }

    public void setMaximoMonto(Integer maximoMonto) {
        this.maximoMonto = maximoMonto;
    }

    public Long getEmpresaId() {
        return empresaId;
    }

    public void setEmpresaId(Long empresaId) {
        this.empresaId = empresaId;
    }

    public String getEmpresaRazonSocial() {
        return empresaRazonSocial;
    }

    public void setEmpresaRazonSocial(String empresaRazonSocial) {
        this.empresaRazonSocial = empresaRazonSocial;
    }

    public Long getCuentaAsociadaId() {
        return cuentaAsociadaId;
    }

    public void setCuentaAsociadaId(Long cuentaAsociadaId) {
        this.cuentaAsociadaId = cuentaAsociadaId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        FacturacionDTO facturacionDTO = (FacturacionDTO) o;
        if (facturacionDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), facturacionDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "FacturacionDTO{" +
            "id=" + getId() +
            ", titularFactura='" + getTitularFactura() + "'" +
            ", tipoPersona='" + getTipoPersona() + "'" +
            ", periodicidadFacturacion='" + getPeriodicidadFacturacion() + "'" +
            ", maximoMonto=" + getMaximoMonto() +
            ", empresa=" + getEmpresaId() +
            ", empresa='" + getEmpresaRazonSocial() + "'" +
            ", cuentaAsociada=" + getCuentaAsociadaId() +
            "}";
    }
}
