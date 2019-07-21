package io.github.jhipster.application.service.mapper;

import io.github.jhipster.application.domain.*;
import io.github.jhipster.application.service.dto.BeneficioDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Beneficio} and its DTO {@link BeneficioDTO}.
 */
@Mapper(componentModel = "spring", uses = {UserMapper.class})
public interface BeneficioMapper extends EntityMapper<BeneficioDTO, Beneficio> {

    @Mapping(source = "miembro.id", target = "miembroId")
    @Mapping(source = "miembro.login", target = "miembroLogin")
    BeneficioDTO toDto(Beneficio beneficio);

    @Mapping(source = "miembroId", target = "miembro")
    Beneficio toEntity(BeneficioDTO beneficioDTO);

    default Beneficio fromId(Long id) {
        if (id == null) {
            return null;
        }
        Beneficio beneficio = new Beneficio();
        beneficio.setId(id);
        return beneficio;
    }
}
