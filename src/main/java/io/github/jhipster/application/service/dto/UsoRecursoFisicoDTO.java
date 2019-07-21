package io.github.jhipster.application.service.dto;
import java.time.ZonedDateTime;
import java.io.Serializable;
import java.util.Objects;
import io.github.jhipster.application.domain.enumeration.TipoIniciod;

/**
 * A DTO for the {@link io.github.jhipster.application.domain.UsoRecursoFisico} entity.
 */
public class UsoRecursoFisicoDTO implements Serializable {

    private Long id;

    private ZonedDateTime registroFechaInicio;

    private ZonedDateTime registroFechaFinal;

    private TipoIniciod tipoRegistro;


    private Long recursoId;

    private String recursoRecurso;

    private Long miembroId;

    private String miembroIdentificacion;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ZonedDateTime getRegistroFechaInicio() {
        return registroFechaInicio;
    }

    public void setRegistroFechaInicio(ZonedDateTime registroFechaInicio) {
        this.registroFechaInicio = registroFechaInicio;
    }

    public ZonedDateTime getRegistroFechaFinal() {
        return registroFechaFinal;
    }

    public void setRegistroFechaFinal(ZonedDateTime registroFechaFinal) {
        this.registroFechaFinal = registroFechaFinal;
    }

    public TipoIniciod getTipoRegistro() {
        return tipoRegistro;
    }

    public void setTipoRegistro(TipoIniciod tipoRegistro) {
        this.tipoRegistro = tipoRegistro;
    }

    public Long getRecursoId() {
        return recursoId;
    }

    public void setRecursoId(Long recursosFisicosId) {
        this.recursoId = recursosFisicosId;
    }

    public String getRecursoRecurso() {
        return recursoRecurso;
    }

    public void setRecursoRecurso(String recursosFisicosRecurso) {
        this.recursoRecurso = recursosFisicosRecurso;
    }

    public Long getMiembroId() {
        return miembroId;
    }

    public void setMiembroId(Long miembrosId) {
        this.miembroId = miembrosId;
    }

    public String getMiembroIdentificacion() {
        return miembroIdentificacion;
    }

    public void setMiembroIdentificacion(String miembrosIdentificacion) {
        this.miembroIdentificacion = miembrosIdentificacion;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        UsoRecursoFisicoDTO usoRecursoFisicoDTO = (UsoRecursoFisicoDTO) o;
        if (usoRecursoFisicoDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), usoRecursoFisicoDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "UsoRecursoFisicoDTO{" +
            "id=" + getId() +
            ", registroFechaInicio='" + getRegistroFechaInicio() + "'" +
            ", registroFechaFinal='" + getRegistroFechaFinal() + "'" +
            ", tipoRegistro='" + getTipoRegistro() + "'" +
            ", recurso=" + getRecursoId() +
            ", recurso='" + getRecursoRecurso() + "'" +
            ", miembro=" + getMiembroId() +
            ", miembro='" + getMiembroIdentificacion() + "'" +
            "}";
    }
}
