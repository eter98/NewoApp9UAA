package io.github.jhipster.application.service.dto;
import java.time.ZonedDateTime;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link io.github.jhipster.application.domain.Chat} entity.
 */
public class ChatDTO implements Serializable {

    private Long id;

    @NotNull
    private String mensaje;

    private ZonedDateTime fecha;

    private Boolean leido;


    private Long deId;

    private String deLogin;

    private Long paraId;

    private String paraLogin;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public ZonedDateTime getFecha() {
        return fecha;
    }

    public void setFecha(ZonedDateTime fecha) {
        this.fecha = fecha;
    }

    public Boolean isLeido() {
        return leido;
    }

    public void setLeido(Boolean leido) {
        this.leido = leido;
    }

    public Long getDeId() {
        return deId;
    }

    public void setDeId(Long userId) {
        this.deId = userId;
    }

    public String getDeLogin() {
        return deLogin;
    }

    public void setDeLogin(String userLogin) {
        this.deLogin = userLogin;
    }

    public Long getParaId() {
        return paraId;
    }

    public void setParaId(Long userId) {
        this.paraId = userId;
    }

    public String getParaLogin() {
        return paraLogin;
    }

    public void setParaLogin(String userLogin) {
        this.paraLogin = userLogin;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        ChatDTO chatDTO = (ChatDTO) o;
        if (chatDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), chatDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "ChatDTO{" +
            "id=" + getId() +
            ", mensaje='" + getMensaje() + "'" +
            ", fecha='" + getFecha() + "'" +
            ", leido='" + isLeido() + "'" +
            ", de=" + getDeId() +
            ", de='" + getDeLogin() + "'" +
            ", para=" + getParaId() +
            ", para='" + getParaLogin() + "'" +
            "}";
    }
}
