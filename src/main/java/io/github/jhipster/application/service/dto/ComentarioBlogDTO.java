package io.github.jhipster.application.service.dto;
import java.time.LocalDate;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Lob;

/**
 * A DTO for the {@link io.github.jhipster.application.domain.ComentarioBlog} entity.
 */
public class ComentarioBlogDTO implements Serializable {

    private Long id;

    
    @Lob
    private String comentario;

    private LocalDate fecha;

    private Boolean meGusta;

    private Boolean seguir;


    private Long blogId;

    private String blogTitulo;

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

    public Long getBlogId() {
        return blogId;
    }

    public void setBlogId(Long blogId) {
        this.blogId = blogId;
    }

    public String getBlogTitulo() {
        return blogTitulo;
    }

    public void setBlogTitulo(String blogTitulo) {
        this.blogTitulo = blogTitulo;
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

        ComentarioBlogDTO comentarioBlogDTO = (ComentarioBlogDTO) o;
        if (comentarioBlogDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), comentarioBlogDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "ComentarioBlogDTO{" +
            "id=" + getId() +
            ", comentario='" + getComentario() + "'" +
            ", fecha='" + getFecha() + "'" +
            ", meGusta='" + isMeGusta() + "'" +
            ", seguir='" + isSeguir() + "'" +
            ", blog=" + getBlogId() +
            ", blog='" + getBlogTitulo() + "'" +
            ", miembro=" + getMiembroId() +
            ", miembro='" + getMiembroLogin() + "'" +
            "}";
    }
}
