package io.github.jhipster.application.service.dto;
import java.time.LocalDate;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Lob;
import io.github.jhipster.application.domain.enumeration.Categoriad;
import io.github.jhipster.application.domain.enumeration.EstadoPublicaciond;
import io.github.jhipster.application.domain.enumeration.TipoConsumod;
import io.github.jhipster.application.domain.enumeration.Impuestod;

/**
 * A DTO for the {@link io.github.jhipster.application.domain.Blog} entity.
 */
public class BlogDTO implements Serializable {

    private Long id;

    @NotNull
    private String titulo;

    @NotNull
    private String descripcion;

    private Categoriad tipoContenido;

    
    @Lob
    private String contenido;

    private LocalDate fecha;

    @Lob
    private byte[] audio;

    private String audioContentType;
    @Lob
    private byte[] video;

    private String videoContentType;
    
    @Lob
    private byte[] imagen1;

    private String imagen1ContentType;
    @Lob
    private byte[] imagen2;

    private String imagen2ContentType;
    @Lob
    private byte[] banner;

    private String bannerContentType;
    private EstadoPublicaciond estadoPublicacion;

    private TipoConsumod tipoConsumo;

    private Float valor;

    private Impuestod impuesto;


    private Long categoriaBlogId;

    private String categoriaBlogCategoria;

    private Long gruposId;

    private String gruposNombreGrupo;

    private Long miembroId;

    private String miembroLogin;

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

    public byte[] getAudio() {
        return audio;
    }

    public void setAudio(byte[] audio) {
        this.audio = audio;
    }

    public String getAudioContentType() {
        return audioContentType;
    }

    public void setAudioContentType(String audioContentType) {
        this.audioContentType = audioContentType;
    }

    public byte[] getVideo() {
        return video;
    }

    public void setVideo(byte[] video) {
        this.video = video;
    }

    public String getVideoContentType() {
        return videoContentType;
    }

    public void setVideoContentType(String videoContentType) {
        this.videoContentType = videoContentType;
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

    public byte[] getBanner() {
        return banner;
    }

    public void setBanner(byte[] banner) {
        this.banner = banner;
    }

    public String getBannerContentType() {
        return bannerContentType;
    }

    public void setBannerContentType(String bannerContentType) {
        this.bannerContentType = bannerContentType;
    }

    public EstadoPublicaciond getEstadoPublicacion() {
        return estadoPublicacion;
    }

    public void setEstadoPublicacion(EstadoPublicaciond estadoPublicacion) {
        this.estadoPublicacion = estadoPublicacion;
    }

    public TipoConsumod getTipoConsumo() {
        return tipoConsumo;
    }

    public void setTipoConsumo(TipoConsumod tipoConsumo) {
        this.tipoConsumo = tipoConsumo;
    }

    public Float getValor() {
        return valor;
    }

    public void setValor(Float valor) {
        this.valor = valor;
    }

    public Impuestod getImpuesto() {
        return impuesto;
    }

    public void setImpuesto(Impuestod impuesto) {
        this.impuesto = impuesto;
    }

    public Long getCategoriaBlogId() {
        return categoriaBlogId;
    }

    public void setCategoriaBlogId(Long categoriaContenidosId) {
        this.categoriaBlogId = categoriaContenidosId;
    }

    public String getCategoriaBlogCategoria() {
        return categoriaBlogCategoria;
    }

    public void setCategoriaBlogCategoria(String categoriaContenidosCategoria) {
        this.categoriaBlogCategoria = categoriaContenidosCategoria;
    }

    public Long getGruposId() {
        return gruposId;
    }

    public void setGruposId(Long gruposId) {
        this.gruposId = gruposId;
    }

    public String getGruposNombreGrupo() {
        return gruposNombreGrupo;
    }

    public void setGruposNombreGrupo(String gruposNombreGrupo) {
        this.gruposNombreGrupo = gruposNombreGrupo;
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

        BlogDTO blogDTO = (BlogDTO) o;
        if (blogDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), blogDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "BlogDTO{" +
            "id=" + getId() +
            ", titulo='" + getTitulo() + "'" +
            ", descripcion='" + getDescripcion() + "'" +
            ", tipoContenido='" + getTipoContenido() + "'" +
            ", contenido='" + getContenido() + "'" +
            ", fecha='" + getFecha() + "'" +
            ", audio='" + getAudio() + "'" +
            ", video='" + getVideo() + "'" +
            ", imagen1='" + getImagen1() + "'" +
            ", imagen2='" + getImagen2() + "'" +
            ", banner='" + getBanner() + "'" +
            ", estadoPublicacion='" + getEstadoPublicacion() + "'" +
            ", tipoConsumo='" + getTipoConsumo() + "'" +
            ", valor=" + getValor() +
            ", impuesto='" + getImpuesto() + "'" +
            ", categoriaBlog=" + getCategoriaBlogId() +
            ", categoriaBlog='" + getCategoriaBlogCategoria() + "'" +
            ", grupos=" + getGruposId() +
            ", grupos='" + getGruposNombreGrupo() + "'" +
            ", miembro=" + getMiembroId() +
            ", miembro='" + getMiembroLogin() + "'" +
            "}";
    }
}
