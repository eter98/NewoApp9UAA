package io.github.jhipster.application.service.dto;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Lob;

/**
 * A DTO for the {@link io.github.jhipster.application.domain.PerfilEquipoEmpresa} entity.
 */
public class PerfilEquipoEmpresaDTO implements Serializable {

    private Long id;

    
    @Lob
    private String biografia;

    @Lob
    private byte[] foto1;

    private String foto1ContentType;
    @Lob
    private byte[] foto2;

    private String foto2ContentType;
    @Lob
    private byte[] fot3;

    private String fot3ContentType;
    @NotNull
    private String conocimientosQueDomina;

    @NotNull
    private String temasDeInteres;

    private String facebook;

    private String instagram;

    private String idGoogle;

    private String twiter;


    private Long empresaId;

    private String empresaRazonSocial;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBiografia() {
        return biografia;
    }

    public void setBiografia(String biografia) {
        this.biografia = biografia;
    }

    public byte[] getFoto1() {
        return foto1;
    }

    public void setFoto1(byte[] foto1) {
        this.foto1 = foto1;
    }

    public String getFoto1ContentType() {
        return foto1ContentType;
    }

    public void setFoto1ContentType(String foto1ContentType) {
        this.foto1ContentType = foto1ContentType;
    }

    public byte[] getFoto2() {
        return foto2;
    }

    public void setFoto2(byte[] foto2) {
        this.foto2 = foto2;
    }

    public String getFoto2ContentType() {
        return foto2ContentType;
    }

    public void setFoto2ContentType(String foto2ContentType) {
        this.foto2ContentType = foto2ContentType;
    }

    public byte[] getFot3() {
        return fot3;
    }

    public void setFot3(byte[] fot3) {
        this.fot3 = fot3;
    }

    public String getFot3ContentType() {
        return fot3ContentType;
    }

    public void setFot3ContentType(String fot3ContentType) {
        this.fot3ContentType = fot3ContentType;
    }

    public String getConocimientosQueDomina() {
        return conocimientosQueDomina;
    }

    public void setConocimientosQueDomina(String conocimientosQueDomina) {
        this.conocimientosQueDomina = conocimientosQueDomina;
    }

    public String getTemasDeInteres() {
        return temasDeInteres;
    }

    public void setTemasDeInteres(String temasDeInteres) {
        this.temasDeInteres = temasDeInteres;
    }

    public String getFacebook() {
        return facebook;
    }

    public void setFacebook(String facebook) {
        this.facebook = facebook;
    }

    public String getInstagram() {
        return instagram;
    }

    public void setInstagram(String instagram) {
        this.instagram = instagram;
    }

    public String getIdGoogle() {
        return idGoogle;
    }

    public void setIdGoogle(String idGoogle) {
        this.idGoogle = idGoogle;
    }

    public String getTwiter() {
        return twiter;
    }

    public void setTwiter(String twiter) {
        this.twiter = twiter;
    }

    public Long getEmpresaId() {
        return empresaId;
    }

    public void setEmpresaId(Long empresaId) {
        this.empresaId = empresaId;
    }

    public String getEmpresaRazonSocial() {
        return empresaRazonSocial;
    }

    public void setEmpresaRazonSocial(String empresaRazonSocial) {
        this.empresaRazonSocial = empresaRazonSocial;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        PerfilEquipoEmpresaDTO perfilEquipoEmpresaDTO = (PerfilEquipoEmpresaDTO) o;
        if (perfilEquipoEmpresaDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), perfilEquipoEmpresaDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "PerfilEquipoEmpresaDTO{" +
            "id=" + getId() +
            ", biografia='" + getBiografia() + "'" +
            ", foto1='" + getFoto1() + "'" +
            ", foto2='" + getFoto2() + "'" +
            ", fot3='" + getFot3() + "'" +
            ", conocimientosQueDomina='" + getConocimientosQueDomina() + "'" +
            ", temasDeInteres='" + getTemasDeInteres() + "'" +
            ", facebook='" + getFacebook() + "'" +
            ", instagram='" + getInstagram() + "'" +
            ", idGoogle='" + getIdGoogle() + "'" +
            ", twiter='" + getTwiter() + "'" +
            ", empresa=" + getEmpresaId() +
            ", empresa='" + getEmpresaRazonSocial() + "'" +
            "}";
    }
}
