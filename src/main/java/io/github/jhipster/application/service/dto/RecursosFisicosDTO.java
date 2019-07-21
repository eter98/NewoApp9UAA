package io.github.jhipster.application.service.dto;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Lob;
import io.github.jhipster.application.domain.enumeration.TipoRecursod;
import io.github.jhipster.application.domain.enumeration.Impuestod;

/**
 * A DTO for the {@link io.github.jhipster.application.domain.RecursosFisicos} entity.
 */
public class RecursosFisicosDTO implements Serializable {

    private Long id;

    @NotNull
    private String recurso;

    @NotNull
    private TipoRecursod tipo;

    @NotNull
    private String unidadMedida;

    @NotNull
    private Integer valorUnitario;

    private Integer valor1H;

    private Integer valor2H;

    private Integer valor3H;

    private Integer valor4H;

    private Integer valor5H;

    private Integer valor6H;

    private Integer valor7H;

    private Integer valor8H;

    private Integer valor9H;

    private Integer valor10H;

    private Integer valor11H;

    private Integer valor12H;

    private Impuestod impuesto;

    
    @Lob
    private byte[] foto;

    private String fotoContentType;
    @Lob
    private byte[] video;

    private String videoContentType;

    private Long sedeId;

    private String sedeNombreSede;

    private Long tipoRecursoId;

    private String tipoRecursoNombre;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRecurso() {
        return recurso;
    }

    public void setRecurso(String recurso) {
        this.recurso = recurso;
    }

    public TipoRecursod getTipo() {
        return tipo;
    }

    public void setTipo(TipoRecursod tipo) {
        this.tipo = tipo;
    }

    public String getUnidadMedida() {
        return unidadMedida;
    }

    public void setUnidadMedida(String unidadMedida) {
        this.unidadMedida = unidadMedida;
    }

    public Integer getValorUnitario() {
        return valorUnitario;
    }

    public void setValorUnitario(Integer valorUnitario) {
        this.valorUnitario = valorUnitario;
    }

    public Integer getValor1H() {
        return valor1H;
    }

    public void setValor1H(Integer valor1H) {
        this.valor1H = valor1H;
    }

    public Integer getValor2H() {
        return valor2H;
    }

    public void setValor2H(Integer valor2H) {
        this.valor2H = valor2H;
    }

    public Integer getValor3H() {
        return valor3H;
    }

    public void setValor3H(Integer valor3H) {
        this.valor3H = valor3H;
    }

    public Integer getValor4H() {
        return valor4H;
    }

    public void setValor4H(Integer valor4H) {
        this.valor4H = valor4H;
    }

    public Integer getValor5H() {
        return valor5H;
    }

    public void setValor5H(Integer valor5H) {
        this.valor5H = valor5H;
    }

    public Integer getValor6H() {
        return valor6H;
    }

    public void setValor6H(Integer valor6H) {
        this.valor6H = valor6H;
    }

    public Integer getValor7H() {
        return valor7H;
    }

    public void setValor7H(Integer valor7H) {
        this.valor7H = valor7H;
    }

    public Integer getValor8H() {
        return valor8H;
    }

    public void setValor8H(Integer valor8H) {
        this.valor8H = valor8H;
    }

    public Integer getValor9H() {
        return valor9H;
    }

    public void setValor9H(Integer valor9H) {
        this.valor9H = valor9H;
    }

    public Integer getValor10H() {
        return valor10H;
    }

    public void setValor10H(Integer valor10H) {
        this.valor10H = valor10H;
    }

    public Integer getValor11H() {
        return valor11H;
    }

    public void setValor11H(Integer valor11H) {
        this.valor11H = valor11H;
    }

    public Integer getValor12H() {
        return valor12H;
    }

    public void setValor12H(Integer valor12H) {
        this.valor12H = valor12H;
    }

    public Impuestod getImpuesto() {
        return impuesto;
    }

    public void setImpuesto(Impuestod impuesto) {
        this.impuesto = impuesto;
    }

    public byte[] getFoto() {
        return foto;
    }

    public void setFoto(byte[] foto) {
        this.foto = foto;
    }

    public String getFotoContentType() {
        return fotoContentType;
    }

    public void setFotoContentType(String fotoContentType) {
        this.fotoContentType = fotoContentType;
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

    public Long getSedeId() {
        return sedeId;
    }

    public void setSedeId(Long sedesId) {
        this.sedeId = sedesId;
    }

    public String getSedeNombreSede() {
        return sedeNombreSede;
    }

    public void setSedeNombreSede(String sedesNombreSede) {
        this.sedeNombreSede = sedesNombreSede;
    }

    public Long getTipoRecursoId() {
        return tipoRecursoId;
    }

    public void setTipoRecursoId(Long tipoRecursoId) {
        this.tipoRecursoId = tipoRecursoId;
    }

    public String getTipoRecursoNombre() {
        return tipoRecursoNombre;
    }

    public void setTipoRecursoNombre(String tipoRecursoNombre) {
        this.tipoRecursoNombre = tipoRecursoNombre;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        RecursosFisicosDTO recursosFisicosDTO = (RecursosFisicosDTO) o;
        if (recursosFisicosDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), recursosFisicosDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "RecursosFisicosDTO{" +
            "id=" + getId() +
            ", recurso='" + getRecurso() + "'" +
            ", tipo='" + getTipo() + "'" +
            ", unidadMedida='" + getUnidadMedida() + "'" +
            ", valorUnitario=" + getValorUnitario() +
            ", valor1H=" + getValor1H() +
            ", valor2H=" + getValor2H() +
            ", valor3H=" + getValor3H() +
            ", valor4H=" + getValor4H() +
            ", valor5H=" + getValor5H() +
            ", valor6H=" + getValor6H() +
            ", valor7H=" + getValor7H() +
            ", valor8H=" + getValor8H() +
            ", valor9H=" + getValor9H() +
            ", valor10H=" + getValor10H() +
            ", valor11H=" + getValor11H() +
            ", valor12H=" + getValor12H() +
            ", impuesto='" + getImpuesto() + "'" +
            ", foto='" + getFoto() + "'" +
            ", video='" + getVideo() + "'" +
            ", sede=" + getSedeId() +
            ", sede='" + getSedeNombreSede() + "'" +
            ", tipoRecurso=" + getTipoRecursoId() +
            ", tipoRecurso='" + getTipoRecursoNombre() + "'" +
            "}";
    }
}
