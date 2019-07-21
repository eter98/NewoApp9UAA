package io.github.jhipster.application.service.dto;

import java.io.Serializable;
import java.util.Objects;
import io.github.jhipster.service.Criteria;
import io.github.jhipster.application.domain.enumeration.TipoPersonad;
import io.github.jhipster.application.domain.enumeration.PeriodicidadFacturaciond;
import io.github.jhipster.service.filter.BooleanFilter;
import io.github.jhipster.service.filter.DoubleFilter;
import io.github.jhipster.service.filter.Filter;
import io.github.jhipster.service.filter.FloatFilter;
import io.github.jhipster.service.filter.IntegerFilter;
import io.github.jhipster.service.filter.LongFilter;
import io.github.jhipster.service.filter.StringFilter;

/**
 * Criteria class for the {@link io.github.jhipster.application.domain.Facturacion} entity. This class is used
 * in {@link io.github.jhipster.application.web.rest.FacturacionResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /facturacions?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class FacturacionCriteria implements Serializable, Criteria {
    /**
     * Class for filtering TipoPersonad
     */
    public static class TipoPersonadFilter extends Filter<TipoPersonad> {

        public TipoPersonadFilter() {
        }

        public TipoPersonadFilter(TipoPersonadFilter filter) {
            super(filter);
        }

        @Override
        public TipoPersonadFilter copy() {
            return new TipoPersonadFilter(this);
        }

    }
    /**
     * Class for filtering PeriodicidadFacturaciond
     */
    public static class PeriodicidadFacturaciondFilter extends Filter<PeriodicidadFacturaciond> {

        public PeriodicidadFacturaciondFilter() {
        }

        public PeriodicidadFacturaciondFilter(PeriodicidadFacturaciondFilter filter) {
            super(filter);
        }

        @Override
        public PeriodicidadFacturaciondFilter copy() {
            return new PeriodicidadFacturaciondFilter(this);
        }

    }

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private StringFilter titularFactura;

    private TipoPersonadFilter tipoPersona;

    private PeriodicidadFacturaciondFilter periodicidadFacturacion;

    private IntegerFilter maximoMonto;

    private LongFilter registroCompraId;

    private LongFilter empresaId;

    private LongFilter cuentaAsociadaId;

    public FacturacionCriteria(){
    }

    public FacturacionCriteria(FacturacionCriteria other){
        this.id = other.id == null ? null : other.id.copy();
        this.titularFactura = other.titularFactura == null ? null : other.titularFactura.copy();
        this.tipoPersona = other.tipoPersona == null ? null : other.tipoPersona.copy();
        this.periodicidadFacturacion = other.periodicidadFacturacion == null ? null : other.periodicidadFacturacion.copy();
        this.maximoMonto = other.maximoMonto == null ? null : other.maximoMonto.copy();
        this.registroCompraId = other.registroCompraId == null ? null : other.registroCompraId.copy();
        this.empresaId = other.empresaId == null ? null : other.empresaId.copy();
        this.cuentaAsociadaId = other.cuentaAsociadaId == null ? null : other.cuentaAsociadaId.copy();
    }

    @Override
    public FacturacionCriteria copy() {
        return new FacturacionCriteria(this);
    }

    public LongFilter getId() {
        return id;
    }

    public void setId(LongFilter id) {
        this.id = id;
    }

    public StringFilter getTitularFactura() {
        return titularFactura;
    }

    public void setTitularFactura(StringFilter titularFactura) {
        this.titularFactura = titularFactura;
    }

    public TipoPersonadFilter getTipoPersona() {
        return tipoPersona;
    }

    public void setTipoPersona(TipoPersonadFilter tipoPersona) {
        this.tipoPersona = tipoPersona;
    }

    public PeriodicidadFacturaciondFilter getPeriodicidadFacturacion() {
        return periodicidadFacturacion;
    }

    public void setPeriodicidadFacturacion(PeriodicidadFacturaciondFilter periodicidadFacturacion) {
        this.periodicidadFacturacion = periodicidadFacturacion;
    }

    public IntegerFilter getMaximoMonto() {
        return maximoMonto;
    }

    public void setMaximoMonto(IntegerFilter maximoMonto) {
        this.maximoMonto = maximoMonto;
    }

    public LongFilter getRegistroCompraId() {
        return registroCompraId;
    }

    public void setRegistroCompraId(LongFilter registroCompraId) {
        this.registroCompraId = registroCompraId;
    }

    public LongFilter getEmpresaId() {
        return empresaId;
    }

    public void setEmpresaId(LongFilter empresaId) {
        this.empresaId = empresaId;
    }

    public LongFilter getCuentaAsociadaId() {
        return cuentaAsociadaId;
    }

    public void setCuentaAsociadaId(LongFilter cuentaAsociadaId) {
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
        final FacturacionCriteria that = (FacturacionCriteria) o;
        return
            Objects.equals(id, that.id) &&
            Objects.equals(titularFactura, that.titularFactura) &&
            Objects.equals(tipoPersona, that.tipoPersona) &&
            Objects.equals(periodicidadFacturacion, that.periodicidadFacturacion) &&
            Objects.equals(maximoMonto, that.maximoMonto) &&
            Objects.equals(registroCompraId, that.registroCompraId) &&
            Objects.equals(empresaId, that.empresaId) &&
            Objects.equals(cuentaAsociadaId, that.cuentaAsociadaId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
        id,
        titularFactura,
        tipoPersona,
        periodicidadFacturacion,
        maximoMonto,
        registroCompraId,
        empresaId,
        cuentaAsociadaId
        );
    }

    @Override
    public String toString() {
        return "FacturacionCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (titularFactura != null ? "titularFactura=" + titularFactura + ", " : "") +
                (tipoPersona != null ? "tipoPersona=" + tipoPersona + ", " : "") +
                (periodicidadFacturacion != null ? "periodicidadFacturacion=" + periodicidadFacturacion + ", " : "") +
                (maximoMonto != null ? "maximoMonto=" + maximoMonto + ", " : "") +
                (registroCompraId != null ? "registroCompraId=" + registroCompraId + ", " : "") +
                (empresaId != null ? "empresaId=" + empresaId + ", " : "") +
                (cuentaAsociadaId != null ? "cuentaAsociadaId=" + cuentaAsociadaId + ", " : "") +
            "}";
    }

}
