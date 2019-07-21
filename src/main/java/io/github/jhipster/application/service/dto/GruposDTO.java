package io.github.jhipster.application.service.dto;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Lob;
import io.github.jhipster.application.domain.enumeration.TipoGrupod;
import io.github.jhipster.application.domain.enumeration.TipoConsumod;
import io.github.jhipster.application.domain.enumeration.Impuestod;

/**
 * A DTO for the {@link io.github.jhipster.application.domain.Grupos} entity.
 */
public class GruposDTO implements Serializable {

    private Long id;

    @NotNull
    private String nombreGrupo;

    private String instagram;

    private String facebook;

    private String twiter;

    private String linkedIn;

    private TipoGrupod tipoGrupo;

    private TipoConsumod tipoConsumo;

    private Integer valorSuscripcion;

    private Impuestod impuesto;

    @Lob
    private String reglasGrupo;

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

    private Long categoriaGrupoId;

    private String categoriaGrupoCategoria;

    private Long eventoId;

    private String eventoNombreEvento;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombreGrupo() {
        return nombreGrupo;
    }

    public void setNombreGrupo(String nombreGrupo) {
        this.nombreGrupo = nombreGrupo;
    }

    public String getInstagram() {
        return instagram;
    }

    public void setInstagram(String instagram) {
        this.instagram = instagram;
    }

    public String getFacebook() {
        return facebook;
    }

    public void setFacebook(String facebook) {
        this.facebook = facebook;
    }

    public String getTwiter() {
        return twiter;
    }

    public void setTwiter(String twiter) {
        this.twiter = twiter;
    }

    public String getLinkedIn() {
        return linkedIn;
    }

    public void setLinkedIn(String linkedIn) {
        this.linkedIn = linkedIn;
    }

    public TipoGrupod getTipoGrupo() {
        return tipoGrupo;
    }

    public void setTipoGrupo(TipoGrupod tipoGrupo) {
        this.tipoGrupo = tipoGrupo;
    }

    public TipoConsumod getTipoConsumo() {
        return tipoConsumo;
    }

    public void setTipoConsumo(TipoConsumod tipoConsumo) {
        this.tipoConsumo = tipoConsumo;
    }

    public Integer getValorSuscripcion() {
        return valorSuscripcion;
    }

    public void setValorSuscripcion(Integer valorSuscripcion) {
        this.valorSuscripcion = valorSuscripcion;
    }

    public Impuestod getImpuesto() {
        return impuesto;
    }

    public void setImpuesto(Impuestod impuesto) {
        this.impuesto = impuesto;
    }

    public String getReglasGrupo() {
        return reglasGrupo;
    }

    public void setReglasGrupo(String reglasGrupo) {
        this.reglasGrupo = reglasGrupo;
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

    public Long getCategoriaGrupoId() {
        return categoriaGrupoId;
    }

    public void setCategoriaGrupoId(Long categoriaContenidosId) {
        this.categoriaGrupoId = categoriaContenidosId;
    }

    public String getCategoriaGrupoCategoria() {
        return categoriaGrupoCategoria;
    }

    public void setCategoriaGrupoCategoria(String categoriaContenidosCategoria) {
        this.categoriaGrupoCategoria = categoriaContenidosCategoria;
    }

    public Long getEventoId() {
        return eventoId;
    }

    public void setEventoId(Long eventoId) {
        this.eventoId = eventoId;
    }

    public String getEventoNombreEvento() {
        return eventoNombreEvento;
    }

    public void setEventoNombreEvento(String eventoNombreEvento) {
        this.eventoNombreEvento = eventoNombreEvento;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        GruposDTO gruposDTO = (GruposDTO) o;
        if (gruposDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), gruposDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "GruposDTO{" +
            "id=" + getId() +
            ", nombreGrupo='" + getNombreGrupo() + "'" +
            ", instagram='" + getInstagram() + "'" +
            ", facebook='" + getFacebook() + "'" +
            ", twiter='" + getTwiter() + "'" +
            ", linkedIn='" + getLinkedIn() + "'" +
            ", tipoGrupo='" + getTipoGrupo() + "'" +
            ", tipoConsumo='" + getTipoConsumo() + "'" +
            ", valorSuscripcion=" + getValorSuscripcion() +
            ", impuesto='" + getImpuesto() + "'" +
            ", reglasGrupo='" + getReglasGrupo() + "'" +
            ", audio='" + getAudio() + "'" +
            ", video='" + getVideo() + "'" +
            ", imagen1='" + getImagen1() + "'" +
            ", imagen2='" + getImagen2() + "'" +
            ", banner='" + getBanner() + "'" +
            ", categoriaGrupo=" + getCategoriaGrupoId() +
            ", categoriaGrupo='" + getCategoriaGrupoCategoria() + "'" +
            ", evento=" + getEventoId() +
            ", evento='" + getEventoNombreEvento() + "'" +
            "}";
    }
}
