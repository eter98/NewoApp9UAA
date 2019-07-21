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
import io.github.jhipster.service.filter.ZonedDateTimeFilter;

/**
 * Criteria class for the {@link io.github.jhipster.application.domain.ChatGrupo} entity. This class is used
 * in {@link io.github.jhipster.application.web.rest.ChatGrupoResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /chat-grupos?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class ChatGrupoCriteria implements Serializable, Criteria {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private StringFilter mensaje;

    private ZonedDateTimeFilter fecha;

    private BooleanFilter leido;

    private StringFilter deId;

    private StringFilter paraId;

    private LongFilter grupoId;

    public ChatGrupoCriteria(){
    }

    public ChatGrupoCriteria(ChatGrupoCriteria other){
        this.id = other.id == null ? null : other.id.copy();
        this.mensaje = other.mensaje == null ? null : other.mensaje.copy();
        this.fecha = other.fecha == null ? null : other.fecha.copy();
        this.leido = other.leido == null ? null : other.leido.copy();
        this.deId = other.deId == null ? null : other.deId.copy();
        this.paraId = other.paraId == null ? null : other.paraId.copy();
        this.grupoId = other.grupoId == null ? null : other.grupoId.copy();
    }

    @Override
    public ChatGrupoCriteria copy() {
        return new ChatGrupoCriteria(this);
    }

    public LongFilter getId() {
        return id;
    }

    public void setId(LongFilter id) {
        this.id = id;
    }

    public StringFilter getMensaje() {
        return mensaje;
    }

    public void setMensaje(StringFilter mensaje) {
        this.mensaje = mensaje;
    }

    public ZonedDateTimeFilter getFecha() {
        return fecha;
    }

    public void setFecha(ZonedDateTimeFilter fecha) {
        this.fecha = fecha;
    }

    public BooleanFilter getLeido() {
        return leido;
    }

    public void setLeido(BooleanFilter leido) {
        this.leido = leido;
    }

    public StringFilter getDeId() {
        return deId;
    }

    public void setDeId(StringFilter deId) {
        this.deId = deId;
    }

    public StringFilter getParaId() {
        return paraId;
    }

    public void setParaId(StringFilter paraId) {
        this.paraId = paraId;
    }

    public LongFilter getGrupoId() {
        return grupoId;
    }

    public void setGrupoId(LongFilter grupoId) {
        this.grupoId = grupoId;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final ChatGrupoCriteria that = (ChatGrupoCriteria) o;
        return
            Objects.equals(id, that.id) &&
            Objects.equals(mensaje, that.mensaje) &&
            Objects.equals(fecha, that.fecha) &&
            Objects.equals(leido, that.leido) &&
            Objects.equals(deId, that.deId) &&
            Objects.equals(paraId, that.paraId) &&
            Objects.equals(grupoId, that.grupoId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
        id,
        mensaje,
        fecha,
        leido,
        deId,
        paraId,
        grupoId
        );
    }

    @Override
    public String toString() {
        return "ChatGrupoCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (mensaje != null ? "mensaje=" + mensaje + ", " : "") +
                (fecha != null ? "fecha=" + fecha + ", " : "") +
                (leido != null ? "leido=" + leido + ", " : "") +
                (deId != null ? "deId=" + deId + ", " : "") +
                (paraId != null ? "paraId=" + paraId + ", " : "") +
                (grupoId != null ? "grupoId=" + grupoId + ", " : "") +
            "}";
    }

}
