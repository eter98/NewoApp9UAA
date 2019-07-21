package io.github.jhipster.application.service.dto;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;
import io.github.jhipster.application.domain.enumeration.Beneficiosd;

/**
 * A DTO for the {@link io.github.jhipster.application.domain.Beneficio} entity.
 */
public class BeneficioDTO implements Serializable {

    private Long id;

    @NotNull
    private Beneficiosd tipoBeneficio;

    @NotNull
    private Integer descuento;


    private Long miembroId;

    private String miembroLogin;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Beneficiosd getTipoBeneficio() {
        return tipoBeneficio;
    }

    public void setTipoBeneficio(Beneficiosd tipoBeneficio) {
        this.tipoBeneficio = tipoBeneficio;
    }

    public Integer getDescuento() {
        return descuento;
    }

    public void setDescuento(Integer descuento) {
        this.descuento = descuento;
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

        BeneficioDTO beneficioDTO = (BeneficioDTO) o;
        if (beneficioDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), beneficioDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "BeneficioDTO{" +
            "id=" + getId() +
            ", tipoBeneficio='" + getTipoBeneficio() + "'" +
            ", descuento=" + getDescuento() +
            ", miembro=" + getMiembroId() +
            ", miembro='" + getMiembroLogin() + "'" +
            "}";
    }
}
