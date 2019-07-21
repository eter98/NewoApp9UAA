package io.github.jhipster.application.service.dto;
import java.time.LocalDate;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Lob;

/**
 * A DTO for the {@link io.github.jhipster.application.domain.ComentarioFeed} entity.
 */
public class ComentarioFeedDTO implements Serializable {

    private Long id;

    
    @Lob
    private String comentario;

    private LocalDate fecha;

    private Boolean meGusta;

    private Boolean seguir;


    private Long feedId;

    private String feedTitulo;

    private Long miembroId;

    private String miembroLogin;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public Boolean isMeGusta() {
        return meGusta;
    }

    public void setMeGusta(Boolean meGusta) {
        this.meGusta = meGusta;
    }

    public Boolean isSeguir() {
        return seguir;
    }

    public void setSeguir(Boolean seguir) {
        this.seguir = seguir;
    }

    public Long getFeedId() {
        return feedId;
    }

    public void setFeedId(Long feedId) {
        this.feedId = feedId;
    }

    public String getFeedTitulo() {
        return feedTitulo;
    }

    public void setFeedTitulo(String feedTitulo) {
        this.feedTitulo = feedTitulo;
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

        ComentarioFeedDTO comentarioFeedDTO = (ComentarioFeedDTO) o;
        if (comentarioFeedDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), comentarioFeedDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "ComentarioFeedDTO{" +
            "id=" + getId() +
            ", comentario='" + getComentario() + "'" +
            ", fecha='" + getFecha() + "'" +
            ", meGusta='" + isMeGusta() + "'" +
            ", seguir='" + isSeguir() + "'" +
            ", feed=" + getFeedId() +
            ", feed='" + getFeedTitulo() + "'" +
            ", miembro=" + getMiembroId() +
            ", miembro='" + getMiembroLogin() + "'" +
            "}";
    }
}
