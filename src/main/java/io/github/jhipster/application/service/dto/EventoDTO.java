package io.github.jhipster.application.service.dto;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Lob;
import io.github.jhipster.application.domain.enumeration.TipoConsumod;
import io.github.jhipster.application.domain.enumeration.Impuestod;
import io.github.jhipster.application.domain.enumeration.Categoriad;

/**
 * A DTO for the {@link io.github.jhipster.application.domain.Evento} entity.
 */
public class EventoDTO implements Serializable {

    private Long id;

    @NotNull
    private String nombreEvento;

    @NotNull
    private String descripcion;

    
    @Lob
    private String contenido;

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
    private TipoConsumod tipoConsumo;

    private Float valor;

    private Impuestod impuesto;

    private Categoriad tipoEvento;


    private Long categoriaEventoId;

    private String categoriaEventoCategoria;

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

    public String getNombreEvento() {
        return nombreEvento;
    }

    public void setNombreEvento(String nombreEvento) {
        this.nombreEvento = nombreEvento;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getContenido() {
        return contenido;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
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

    public Categoriad getTipoEvento() {
        return tipoEvento;
    }

    public void setTipoEvento(Categoriad tipoEvento) {
        this.tipoEvento = tipoEvento;
    }

    public Long getCategoriaEventoId() {
        return categoriaEventoId;
    }

    public void setCategoriaEventoId(Long categoriaContenidosId) {
        this.categoriaEventoId = categoriaContenidosId;
    }

    public String getCategoriaEventoCategoria() {
        return categoriaEventoCategoria;
    }

    public void setCategoriaEventoCategoria(String categoriaContenidosCategoria) {
        this.categoriaEventoCategoria = categoriaContenidosCategoria;
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

        EventoDTO eventoDTO = (EventoDTO) o;
        if (eventoDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), eventoDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "EventoDTO{" +
            "id=" + getId() +
            ", nombreEvento='" + getNombreEvento() + "'" +
            ", descripcion='" + getDescripcion() + "'" +
            ", contenido='" + getContenido() + "'" +
            ", audio='" + getAudio() + "'" +
            ", video='" + getVideo() + "'" +
            ", imagen1='" + getImagen1() + "'" +
            ", imagen2='" + getImagen2() + "'" +
            ", banner='" + getBanner() + "'" +
            ", tipoConsumo='" + getTipoConsumo() + "'" +
            ", valor=" + getValor() +
            ", impuesto='" + getImpuesto() + "'" +
            ", tipoEvento='" + getTipoEvento() + "'" +
            ", categoriaEvento=" + getCategoriaEventoId() +
            ", categoriaEvento='" + getCategoriaEventoCategoria() + "'" +
            ", grupos=" + getGruposId() +
            ", grupos='" + getGruposNombreGrupo() + "'" +
            ", miembro=" + getMiembroId() +
            ", miembro='" + getMiembroLogin() + "'" +
            "}";
    }
}
