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
import io.github.jhipster.service.filter.LocalDateFilter;

/**
 * Criteria class for the {@link io.github.jhipster.application.domain.ComentarioFeed} entity. This class is used
 * in {@link io.github.jhipster.application.web.rest.ComentarioFeedResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /comentario-feeds?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class ComentarioFeedCriteria implements Serializable, Criteria {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private LocalDateFilter fecha;

    private BooleanFilter meGusta;

    private BooleanFilter seguir;

    private LongFilter feedId;

    private StringFilter miembroId;

    public ComentarioFeedCriteria(){
    }

    public ComentarioFeedCriteria(ComentarioFeedCriteria other){
        this.id = other.id == null ? null : other.id.copy();
        this.fecha = other.fecha == null ? null : other.fecha.copy();
        this.meGusta = other.meGusta == null ? null : other.meGusta.copy();
        this.seguir = other.seguir == null ? null : other.seguir.copy();
        this.feedId = other.feedId == null ? null : other.feedId.copy();
        this.miembroId = other.miembroId == null ? null : other.miembroId.copy();
    }

    @Override
    public ComentarioFeedCriteria copy() {
        return new ComentarioFeedCriteria(this);
    }

    public LongFilter getId() {
        return id;
    }

    public void setId(LongFilter id) {
        this.id = id;
    }

    public LocalDateFilter getFecha() {
        return fecha;
    }

    public void setFecha(LocalDateFilter fecha) {
        this.fecha = fecha;
    }

    public BooleanFilter getMeGusta() {
        return meGusta;
    }

    public void setMeGusta(BooleanFilter meGusta) {
        this.meGusta = meGusta;
    }

    public BooleanFilter getSeguir() {
        return seguir;
    }

    public void setSeguir(BooleanFilter seguir) {
        this.seguir = seguir;
    }

    public LongFilter getFeedId() {
        return feedId;
    }

    public void setFeedId(LongFilter feedId) {
        this.feedId = feedId;
    }

    public StringFilter getMiembroId() {
        return miembroId;
    }

    public void setMiembroId(StringFilter miembroId) {
        this.miembroId = miembroId;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final ComentarioFeedCriteria that = (ComentarioFeedCriteria) o;
        return
            Objects.equals(id, that.id) &&
            Objects.equals(fecha, that.fecha) &&
            Objects.equals(meGusta, that.meGusta) &&
            Objects.equals(seguir, that.seguir) &&
            Objects.equals(feedId, that.feedId) &&
            Objects.equals(miembroId, that.miembroId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
        id,
        fecha,
        meGusta,
        seguir,
        feedId,
        miembroId
        );
    }

    @Override
    public String toString() {
        return "ComentarioFeedCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (fecha != null ? "fecha=" + fecha + ", " : "") +
                (meGusta != null ? "meGusta=" + meGusta + ", " : "") +
                (seguir != null ? "seguir=" + seguir + ", " : "") +
                (feedId != null ? "feedId=" + feedId + ", " : "") +
                (miembroId != null ? "miembroId=" + miembroId + ", " : "") +
            "}";
    }

}
