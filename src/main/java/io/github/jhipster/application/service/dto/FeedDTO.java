package io.github.jhipster.application.service.dto;
import java.time.LocalDate;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Lob;
import io.github.jhipster.application.domain.enumeration.Categoriad;

/**
 * A DTO for the {@link io.github.jhipster.application.domain.Feed} entity.
 */
public class FeedDTO implements Serializable {

    private Long id;

    @NotNull
    private String titulo;

    @NotNull
    private String descripcion;

    
    @Lob
    private byte[] imagen1;

    private String imagen1ContentType;
    @Lob
    private byte[] imagen2;

    private String imagen2ContentType;
    private Categoriad tipoContenido;

    
    @Lob
    private String contenido;

    private LocalDate fecha;


    private Long miembroId;

    private String miembroLogin;

    private Long categoriaFeedId;

    private String categoriaFeedCategoria;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public byte[] getImagen1() {
        return imagen1;
    }

    public void setImagen1(byte[] imagen1) {
        this.imagen1 = imagen1;
    }

    public String getImagen1ContentType() {
        return imagen1ContentType;
    }

    public void setImagen1ContentType(String imagen1ContentType) {
        this.imagen1ContentType = imagen1ContentType;
    }

    public byte[] getImagen2() {
        return imagen2;
    }

    public void setImagen2(byte[] imagen2) {
        this.imagen2 = imagen2;
    }

    public String getImagen2ContentType() {
        return imagen2ContentType;
    }

    public void setImagen2ContentType(String imagen2ContentType) {
        this.imagen2ContentType = imagen2ContentType;
    }

    public Categoriad getTipoContenido() {
        return tipoContenido;
    }

    public void setTipoContenido(Categoriad tipoContenido) {
        this.tipoContenido = tipoContenido;
    }

    public String getContenido() {
        return contenido;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
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

    public Long getCategoriaFeedId() {
        return categoriaFeedId;
    }

    public void setCategoriaFeedId(Long categoriaContenidosId) {
        this.categoriaFeedId = categoriaContenidosId;
    }

    public String getCategoriaFeedCategoria() {
        return categoriaFeedCategoria;
    }

    public void setCategoriaFeedCategoria(String categoriaContenidosCategoria) {
        this.categoriaFeedCategoria = categoriaContenidosCategoria;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        FeedDTO feedDTO = (FeedDTO) o;
        if (feedDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), feedDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "FeedDTO{" +
            "id=" + getId() +
            ", titulo='" + getTitulo() + "'" +
            ", descripcion='" + getDescripcion() + "'" +
            ", imagen1='" + getImagen1() + "'" +
            ", imagen2='" + getImagen2() + "'" +
            ", tipoContenido='" + getTipoContenido() + "'" +
            ", contenido='" + getContenido() + "'" +
            ", fecha='" + getFecha() + "'" +
            ", miembro=" + getMiembroId() +
            ", miembro='" + getMiembroLogin() + "'" +
            ", categoriaFeed=" + getCategoriaFeedId() +
            ", categoriaFeed='" + getCategoriaFeedCategoria() + "'" +
            "}";
    }
}
