package io.github.jhipster.application.service.dto;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link io.github.jhipster.application.domain.MargenNewoBlog} entity.
 */
public class MargenNewoBlogDTO implements Serializable {

    private Long id;

    private Integer porcentajeMargen;


    private Long blogId;

    private String blogDescripcion;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getPorcentajeMargen() {
        return porcentajeMargen;
    }

    public void setPorcentajeMargen(Integer porcentajeMargen) {
        this.porcentajeMargen = porcentajeMargen;
    }

    public Long getBlogId() {
        return blogId;
    }

    public void setBlogId(Long blogId) {
        this.blogId = blogId;
    }

    public String getBlogDescripcion() {
        return blogDescripcion;
    }

    public void setBlogDescripcion(String blogDescripcion) {
        this.blogDescripcion = blogDescripcion;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        MargenNewoBlogDTO margenNewoBlogDTO = (MargenNewoBlogDTO) o;
        if (margenNewoBlogDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), margenNewoBlogDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "MargenNewoBlogDTO{" +
            "id=" + getId() +
            ", porcentajeMargen=" + getPorcentajeMargen() +
            ", blog=" + getBlogId() +
            ", blog='" + getBlogDescripcion() + "'" +
            "}";
    }
}
