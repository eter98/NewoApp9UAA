package io.github.jhipster.application.service.dto;
import java.time.LocalDate;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Lob;
import io.github.jhipster.application.domain.enumeration.TipoDocumentod;
import io.github.jhipster.application.domain.enumeration.Generod;

/**
 * A DTO for the {@link io.github.jhipster.application.domain.Miembros} entity.
 */
public class MiembrosDTO implements Serializable {

    private Long id;

    @NotNull
    private TipoDocumentod tipoDocumento;

    @NotNull
    private Integer identificacion;

    @NotNull
    private LocalDate fechaNacimiento;

    @NotNull
    private LocalDate fechaRegistro;

    @NotNull
    private Generod genero;

    @NotNull
    @Size(min = 11, max = 13)
    private String celular;

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

    private Boolean derechosDeCompra;

    private Boolean accesoIlimitado;

    private Boolean aliado;

    private Boolean host;


    private Long miembroId;

    private String miembroLogin;

    private Long nacionalidadId;

    private String nacionalidadNombrePais;

    private Long sedeId;

    private String sedeNombreSede;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public TipoDocumentod getTipoDocumento() {
        return tipoDocumento;
    }

    public void setTipoDocumento(TipoDocumentod tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
    }

    public Integer getIdentificacion() {
        return identificacion;
    }

    public void setIdentificacion(Integer identificacion) {
        this.identificacion = identificacion;
    }

    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(LocalDate fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public LocalDate getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(LocalDate fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public Generod getGenero() {
        return genero;
    }

    public void setGenero(Generod genero) {
        this.genero = genero;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
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

    public Boolean isDerechosDeCompra() {
        return derechosDeCompra;
    }

    public void setDerechosDeCompra(Boolean derechosDeCompra) {
        this.derechosDeCompra = derechosDeCompra;
    }

    public Boolean isAccesoIlimitado() {
        return accesoIlimitado;
    }

    public void setAccesoIlimitado(Boolean accesoIlimitado) {
        this.accesoIlimitado = accesoIlimitado;
    }

    public Boolean isAliado() {
        return aliado;
    }

    public void setAliado(Boolean aliado) {
        this.aliado = aliado;
    }

    public Boolean isHost() {
        return host;
    }

    public void setHost(Boolean host) {
        this.host = host;
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

    public Long getNacionalidadId() {
        return nacionalidadId;
    }

    public void setNacionalidadId(Long paisId) {
        this.nacionalidadId = paisId;
    }

    public String getNacionalidadNombrePais() {
        return nacionalidadNombrePais;
    }

    public void setNacionalidadNombrePais(String paisNombrePais) {
        this.nacionalidadNombrePais = paisNombrePais;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        MiembrosDTO miembrosDTO = (MiembrosDTO) o;
        if (miembrosDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), miembrosDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "MiembrosDTO{" +
            "id=" + getId() +
            ", tipoDocumento='" + getTipoDocumento() + "'" +
            ", identificacion=" + getIdentificacion() +
            ", fechaNacimiento='" + getFechaNacimiento() + "'" +
            ", fechaRegistro='" + getFechaRegistro() + "'" +
            ", genero='" + getGenero() + "'" +
            ", celular='" + getCelular() + "'" +
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
            ", derechosDeCompra='" + isDerechosDeCompra() + "'" +
            ", accesoIlimitado='" + isAccesoIlimitado() + "'" +
            ", aliado='" + isAliado() + "'" +
            ", host='" + isHost() + "'" +
            ", miembro=" + getMiembroId() +
            ", miembro='" + getMiembroLogin() + "'" +
            ", nacionalidad=" + getNacionalidadId() +
            ", nacionalidad='" + getNacionalidadNombrePais() + "'" +
            ", sede=" + getSedeId() +
            ", sede='" + getSedeNombreSede() + "'" +
            "}";
    }
}
