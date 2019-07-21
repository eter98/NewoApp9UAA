package io.github.jhipster.application.service.dto;

import java.io.Serializable;
import java.util.Objects;
import io.github.jhipster.service.Criteria;
import io.github.jhipster.service.filter.BooleanFilter;
import io.github.jhipster.service.filter.DoubleFilter;
import io.github.jhipster.service.filter.Filter;
import io.github.jhipster.service.filter.FloatFilter;
import io.github.jhipster.service.filter.IntegerFilter;
import io.github.jhipster.service.filter.LongFilter;
import io.github.jhipster.service.filter.StringFilter;

/**
 * Criteria class for the {@link io.github.jhipster.application.domain.RegistroCompra} entity. This class is used
 * in {@link io.github.jhipster.application.web.rest.RegistroCompraResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /registro-compras?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class RegistroCompraCriteria implements Serializable, Criteria {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private BooleanFilter consumoMarket;

    private StringFilter miembroId;

    private LongFilter facturacionId;

    private LongFilter reservasId;

    private LongFilter entradaInvitadosId;

    private LongFilter entradaMiembrosId;

    public RegistroCompraCriteria(){
    }

    public RegistroCompraCriteria(RegistroCompraCriteria other){
        this.id = other.id == null ? null : other.id.copy();
        this.consumoMarket = other.consumoMarket == null ? null : other.consumoMarket.copy();
        this.miembroId = other.miembroId == null ? null : other.miembroId.copy();
        this.facturacionId = other.facturacionId == null ? null : other.facturacionId.copy();
        this.reservasId = other.reservasId == null ? null : other.reservasId.copy();
        this.entradaInvitadosId = other.entradaInvitadosId == null ? null : other.entradaInvitadosId.copy();
        this.entradaMiembrosId = other.entradaMiembrosId == null ? null : other.entradaMiembrosId.copy();
    }

    @Override
    public RegistroCompraCriteria copy() {
        return new RegistroCompraCriteria(this);
    }

    public LongFilter getId() {
        return id;
    }

    public void setId(LongFilter id) {
        this.id = id;
    }

    public BooleanFilter getConsumoMarket() {
        return consumoMarket;
    }

    public void setConsumoMarket(BooleanFilter consumoMarket) {
        this.consumoMarket = consumoMarket;
    }

    public StringFilter getMiembroId() {
        return miembroId;
    }

    public void setMiembroId(StringFilter miembroId) {
        this.miembroId = miembroId;
    }

    public LongFilter getFacturacionId() {
        return facturacionId;
    }

    public void setFacturacionId(LongFilter facturacionId) {
        this.facturacionId = facturacionId;
    }

    public LongFilter getReservasId() {
        return reservasId;
    }

    public void setReservasId(LongFilter reservasId) {
        this.reservasId = reservasId;
    }

    public LongFilter getEntradaInvitadosId() {
        return entradaInvitadosId;
    }

    public void setEntradaInvitadosId(LongFilter entradaInvitadosId) {
        this.entradaInvitadosId = entradaInvitadosId;
    }

    public LongFilter getEntradaMiembrosId() {
        return entradaMiembrosId;
    }

    public void setEntradaMiembrosId(LongFilter entradaMiembrosId) {
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
        final RegistroCompraCriteria that = (RegistroCompraCriteria) o;
        return
            Objects.equals(id, that.id) &&
            Objects.equals(consumoMarket, that.consumoMarket) &&
            Objects.equals(miembroId, that.miembroId) &&
            Objects.equals(facturacionId, that.facturacionId) &&
            Objects.equals(reservasId, that.reservasId) &&
            Objects.equals(entradaInvitadosId, that.entradaInvitadosId) &&
            Objects.equals(entradaMiembrosId, that.entradaMiembrosId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
        id,
        consumoMarket,
        miembroId,
        facturacionId,
        reservasId,
        entradaInvitadosId,
        entradaMiembrosId
        );
    }

    @Override
    public String toString() {
        return "RegistroCompraCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (consumoMarket != null ? "consumoMarket=" + consumoMarket + ", " : "") +
                (miembroId != null ? "miembroId=" + miembroId + ", " : "") +
                (facturacionId != null ? "facturacionId=" + facturacionId + ", " : "") +
                (reservasId != null ? "reservasId=" + reservasId + ", " : "") +
                (entradaInvitadosId != null ? "entradaInvitadosId=" + entradaInvitadosId + ", " : "") +
                (entradaMiembrosId != null ? "entradaMiembrosId=" + entradaMiembrosId + ", " : "") +
            "}";
    }

}
