package io.github.jhipster.application.service.dto;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Lob;
import io.github.jhipster.application.domain.enumeration.Impuestod;

/**
 * A DTO for the {@link io.github.jhipster.application.domain.EspaciosReserva} entity.
 */
public class EspaciosReservaDTO implements Serializable {

    private Long id;

    @NotNull
    private String nombre;

    @NotNull
    private String descripcion;

    @NotNull
    private String facilidades;

    @NotNull
    private Integer capacidad;

    @NotNull
    @Size(max = 5)
    private String apertura;

    @NotNull
    @Size(max = 5)
    private String cierre;

    private String wifi;

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
    private byte[] imagen3;

    private String imagen3ContentType;
    @Lob
    private byte[] imagen4;

    private String imagen4ContentType;
    @Lob
    private byte[] imagen5;

    private String imagen5ContentType;
    @Lob
    private byte[] imagen6;

    private String imagen6ContentType;
    private Integer tarifa1Hora;

    private Integer tarifa2Hora;

    private Integer tarifa3Hora;

    private Integer tarifa4Hora;

    private Integer tarifa5Hora;

    private Integer tarifa6Hora;

    private Integer tarifa7Hora;

    private Integer tarifa8Hora;

    private Impuestod impuesto;


    private Long sedeId;

    private String sedeNombreSede;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getFacilidades() {
        return facilidades;
    }

    public void setFacilidades(String facilidades) {
        this.facilidades = facilidades;
    }

    public Integer getCapacidad() {
        return capacidad;
    }

    public void setCapacidad(Integer capacidad) {
        this.capacidad = capacidad;
    }

    public String getApertura() {
        return apertura;
    }

    public void setApertura(String apertura) {
        this.apertura = apertura;
    }

    public String getCierre() {
        return cierre;
    }

    public void setCierre(String cierre) {
        this.cierre = cierre;
    }

    public String getWifi() {
        return wifi;
    }

    public void setWifi(String wifi) {
        this.wifi = wifi;
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

    public byte[] getImagen3() {
        return imagen3;
    }

    public void setImagen3(byte[] imagen3) {
        this.imagen3 = imagen3;
    }

    public String getImagen3ContentType() {
        return imagen3ContentType;
    }

    public void setImagen3ContentType(String imagen3ContentType) {
        this.imagen3ContentType = imagen3ContentType;
    }

    public byte[] getImagen4() {
        return imagen4;
    }

    public void setImagen4(byte[] imagen4) {
        this.imagen4 = imagen4;
    }

    public String getImagen4ContentType() {
        return imagen4ContentType;
    }

    public void setImagen4ContentType(String imagen4ContentType) {
        this.imagen4ContentType = imagen4ContentType;
    }

    public byte[] getImagen5() {
        return imagen5;
    }

    public void setImagen5(byte[] imagen5) {
        this.imagen5 = imagen5;
    }

    public String getImagen5ContentType() {
        return imagen5ContentType;
    }

    public void setImagen5ContentType(String imagen5ContentType) {
        this.imagen5ContentType = imagen5ContentType;
    }

    public byte[] getImagen6() {
        return imagen6;
    }

    public void setImagen6(byte[] imagen6) {
        this.imagen6 = imagen6;
    }

    public String getImagen6ContentType() {
        return imagen6ContentType;
    }

    public void setImagen6ContentType(String imagen6ContentType) {
        this.imagen6ContentType = imagen6ContentType;
    }

    public Integer getTarifa1Hora() {
        return tarifa1Hora;
    }

    public void setTarifa1Hora(Integer tarifa1Hora) {
        this.tarifa1Hora = tarifa1Hora;
    }

    public Integer getTarifa2Hora() {
        return tarifa2Hora;
    }

    public void setTarifa2Hora(Integer tarifa2Hora) {
        this.tarifa2Hora = tarifa2Hora;
    }

    public Integer getTarifa3Hora() {
        return tarifa3Hora;
    }

    public void setTarifa3Hora(Integer tarifa3Hora) {
        this.tarifa3Hora = tarifa3Hora;
    }

    public Integer getTarifa4Hora() {
        return tarifa4Hora;
    }

    public void setTarifa4Hora(Integer tarifa4Hora) {
        this.tarifa4Hora = tarifa4Hora;
    }

    public Integer getTarifa5Hora() {
        return tarifa5Hora;
    }

    public void setTarifa5Hora(Integer tarifa5Hora) {
        this.tarifa5Hora = tarifa5Hora;
    }

    public Integer getTarifa6Hora() {
        return tarifa6Hora;
    }

    public void setTarifa6Hora(Integer tarifa6Hora) {
        this.tarifa6Hora = tarifa6Hora;
    }

    public Integer getTarifa7Hora() {
        return tarifa7Hora;
    }

    public void setTarifa7Hora(Integer tarifa7Hora) {
        this.tarifa7Hora = tarifa7Hora;
    }

    public Integer getTarifa8Hora() {
        return tarifa8Hora;
    }

    public void setTarifa8Hora(Integer tarifa8Hora) {
        this.tarifa8Hora = tarifa8Hora;
    }

    public Impuestod getImpuesto() {
        return impuesto;
    }

    public void setImpuesto(Impuestod impuesto) {
        this.impuesto = impuesto;
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

        EspaciosReservaDTO espaciosReservaDTO = (EspaciosReservaDTO) o;
        if (espaciosReservaDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), espaciosReservaDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "EspaciosReservaDTO{" +
            "id=" + getId() +
            ", nombre='" + getNombre() + "'" +
            ", descripcion='" + getDescripcion() + "'" +
            ", facilidades='" + getFacilidades() + "'" +
            ", capacidad=" + getCapacidad() +
            ", apertura='" + getApertura() + "'" +
            ", cierre='" + getCierre() + "'" +
            ", wifi='" + getWifi() + "'" +
            ", video='" + getVideo() + "'" +
            ", imagen1='" + getImagen1() + "'" +
            ", imagen2='" + getImagen2() + "'" +
            ", imagen3='" + getImagen3() + "'" +
            ", imagen4='" + getImagen4() + "'" +
            ", imagen5='" + getImagen5() + "'" +
            ", imagen6='" + getImagen6() + "'" +
            ", tarifa1Hora=" + getTarifa1Hora() +
            ", tarifa2Hora=" + getTarifa2Hora() +
            ", tarifa3Hora=" + getTarifa3Hora() +
            ", tarifa4Hora=" + getTarifa4Hora() +
            ", tarifa5Hora=" + getTarifa5Hora() +
            ", tarifa6Hora=" + getTarifa6Hora() +
            ", tarifa7Hora=" + getTarifa7Hora() +
            ", tarifa8Hora=" + getTarifa8Hora() +
            ", impuesto='" + getImpuesto() + "'" +
            ", sede=" + getSedeId() +
            ", sede='" + getSedeNombreSede() + "'" +
            "}";
    }
}
