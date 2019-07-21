package io.github.jhipster.application.service.dto;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Lob;
import io.github.jhipster.application.domain.enumeration.Impuestod;

/**
 * A DTO for the {@link io.github.jhipster.application.domain.EspacioLibre} entity.
 */
public class EspacioLibreDTO implements Serializable {

    private Long id;

    @NotNull
    private String nombre;

    @NotNull
    private Integer capacidadInstalada;

    private String wifi;

    private Integer tarifa1hMiembro;

    private Integer tarifa2hMiembro;

    private Integer tarifa3hMiembro;

    private Integer tarifa4hMiembro;

    private Integer tarifa5hMiembro;

    private Integer tarifa6hMiembro;

    private Integer tarifa7hMiembro;

    private Integer tarifa8hMiembro;

    private Integer tarifa1hInvitado;

    private Integer tarifa2hInvitado;

    private Integer tarifa3hInvitado;

    private Integer tarifa4hInvitado;

    private Integer tarifa5hInvitado;

    private Integer tarifa6hInvitado;

    private Integer tarifa7hInvitado;

    private Integer tarifa8hInvitado;

    private Impuestod impuesto;

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

    private Long sedeId;

    private String sedeNombreSede;

    private Long tipoEspacioId;

    private String tipoEspacioTipoEspacio;

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

    public Integer getCapacidadInstalada() {
        return capacidadInstalada;
    }

    public void setCapacidadInstalada(Integer capacidadInstalada) {
        this.capacidadInstalada = capacidadInstalada;
    }

    public String getWifi() {
        return wifi;
    }

    public void setWifi(String wifi) {
        this.wifi = wifi;
    }

    public Integer getTarifa1hMiembro() {
        return tarifa1hMiembro;
    }

    public void setTarifa1hMiembro(Integer tarifa1hMiembro) {
        this.tarifa1hMiembro = tarifa1hMiembro;
    }

    public Integer getTarifa2hMiembro() {
        return tarifa2hMiembro;
    }

    public void setTarifa2hMiembro(Integer tarifa2hMiembro) {
        this.tarifa2hMiembro = tarifa2hMiembro;
    }

    public Integer getTarifa3hMiembro() {
        return tarifa3hMiembro;
    }

    public void setTarifa3hMiembro(Integer tarifa3hMiembro) {
        this.tarifa3hMiembro = tarifa3hMiembro;
    }

    public Integer getTarifa4hMiembro() {
        return tarifa4hMiembro;
    }

    public void setTarifa4hMiembro(Integer tarifa4hMiembro) {
        this.tarifa4hMiembro = tarifa4hMiembro;
    }

    public Integer getTarifa5hMiembro() {
        return tarifa5hMiembro;
    }

    public void setTarifa5hMiembro(Integer tarifa5hMiembro) {
        this.tarifa5hMiembro = tarifa5hMiembro;
    }

    public Integer getTarifa6hMiembro() {
        return tarifa6hMiembro;
    }

    public void setTarifa6hMiembro(Integer tarifa6hMiembro) {
        this.tarifa6hMiembro = tarifa6hMiembro;
    }

    public Integer getTarifa7hMiembro() {
        return tarifa7hMiembro;
    }

    public void setTarifa7hMiembro(Integer tarifa7hMiembro) {
        this.tarifa7hMiembro = tarifa7hMiembro;
    }

    public Integer getTarifa8hMiembro() {
        return tarifa8hMiembro;
    }

    public void setTarifa8hMiembro(Integer tarifa8hMiembro) {
        this.tarifa8hMiembro = tarifa8hMiembro;
    }

    public Integer getTarifa1hInvitado() {
        return tarifa1hInvitado;
    }

    public void setTarifa1hInvitado(Integer tarifa1hInvitado) {
        this.tarifa1hInvitado = tarifa1hInvitado;
    }

    public Integer getTarifa2hInvitado() {
        return tarifa2hInvitado;
    }

    public void setTarifa2hInvitado(Integer tarifa2hInvitado) {
        this.tarifa2hInvitado = tarifa2hInvitado;
    }

    public Integer getTarifa3hInvitado() {
        return tarifa3hInvitado;
    }

    public void setTarifa3hInvitado(Integer tarifa3hInvitado) {
        this.tarifa3hInvitado = tarifa3hInvitado;
    }

    public Integer getTarifa4hInvitado() {
        return tarifa4hInvitado;
    }

    public void setTarifa4hInvitado(Integer tarifa4hInvitado) {
        this.tarifa4hInvitado = tarifa4hInvitado;
    }

    public Integer getTarifa5hInvitado() {
        return tarifa5hInvitado;
    }

    public void setTarifa5hInvitado(Integer tarifa5hInvitado) {
        this.tarifa5hInvitado = tarifa5hInvitado;
    }

    public Integer getTarifa6hInvitado() {
        return tarifa6hInvitado;
    }

    public void setTarifa6hInvitado(Integer tarifa6hInvitado) {
        this.tarifa6hInvitado = tarifa6hInvitado;
    }

    public Integer getTarifa7hInvitado() {
        return tarifa7hInvitado;
    }

    public void setTarifa7hInvitado(Integer tarifa7hInvitado) {
        this.tarifa7hInvitado = tarifa7hInvitado;
    }

    public Integer getTarifa8hInvitado() {
        return tarifa8hInvitado;
    }

    public void setTarifa8hInvitado(Integer tarifa8hInvitado) {
        this.tarifa8hInvitado = tarifa8hInvitado;
    }

    public Impuestod getImpuesto() {
        return impuesto;
    }

    public void setImpuesto(Impuestod impuesto) {
        this.impuesto = impuesto;
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

    public Long getTipoEspacioId() {
        return tipoEspacioId;
    }

    public void setTipoEspacioId(Long tipoEspacioId) {
        this.tipoEspacioId = tipoEspacioId;
    }

    public String getTipoEspacioTipoEspacio() {
        return tipoEspacioTipoEspacio;
    }

    public void setTipoEspacioTipoEspacio(String tipoEspacioTipoEspacio) {
        this.tipoEspacioTipoEspacio = tipoEspacioTipoEspacio;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        EspacioLibreDTO espacioLibreDTO = (EspacioLibreDTO) o;
        if (espacioLibreDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), espacioLibreDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "EspacioLibreDTO{" +
            "id=" + getId() +
            ", nombre='" + getNombre() + "'" +
            ", capacidadInstalada=" + getCapacidadInstalada() +
            ", wifi='" + getWifi() + "'" +
            ", tarifa1hMiembro=" + getTarifa1hMiembro() +
            ", tarifa2hMiembro=" + getTarifa2hMiembro() +
            ", tarifa3hMiembro=" + getTarifa3hMiembro() +
            ", tarifa4hMiembro=" + getTarifa4hMiembro() +
            ", tarifa5hMiembro=" + getTarifa5hMiembro() +
            ", tarifa6hMiembro=" + getTarifa6hMiembro() +
            ", tarifa7hMiembro=" + getTarifa7hMiembro() +
            ", tarifa8hMiembro=" + getTarifa8hMiembro() +
            ", tarifa1hInvitado=" + getTarifa1hInvitado() +
            ", tarifa2hInvitado=" + getTarifa2hInvitado() +
            ", tarifa3hInvitado=" + getTarifa3hInvitado() +
            ", tarifa4hInvitado=" + getTarifa4hInvitado() +
            ", tarifa5hInvitado=" + getTarifa5hInvitado() +
            ", tarifa6hInvitado=" + getTarifa6hInvitado() +
            ", tarifa7hInvitado=" + getTarifa7hInvitado() +
            ", tarifa8hInvitado=" + getTarifa8hInvitado() +
            ", impuesto='" + getImpuesto() + "'" +
            ", video='" + getVideo() + "'" +
            ", imagen1='" + getImagen1() + "'" +
            ", imagen2='" + getImagen2() + "'" +
            ", imagen3='" + getImagen3() + "'" +
            ", imagen4='" + getImagen4() + "'" +
            ", imagen5='" + getImagen5() + "'" +
            ", imagen6='" + getImagen6() + "'" +
            ", sede=" + getSedeId() +
            ", sede='" + getSedeNombreSede() + "'" +
            ", tipoEspacio=" + getTipoEspacioId() +
            ", tipoEspacio='" + getTipoEspacioTipoEspacio() + "'" +
            "}";
    }
}
