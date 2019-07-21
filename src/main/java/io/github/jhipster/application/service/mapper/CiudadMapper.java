package io.github.jhipster.application.service.mapper;

import io.github.jhipster.application.domain.*;
import io.github.jhipster.application.service.dto.CiudadDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Ciudad} and its DTO {@link CiudadDTO}.
 */
@Mapper(componentModel = "spring", uses = {PaisMapper.class})
public interface CiudadMapper extends EntityMapper<CiudadDTO, Ciudad> {

    @Mapping(source = "pais.id", target = "paisId")
    @Mapping(source = "pais.nombrePais", target = "paisNombrePais")
    CiudadDTO toDto(Ciudad ciudad);

    @Mapping(source = "paisId", target = "pais")
    Ciudad toEntity(CiudadDTO ciudadDTO);

    default Ciudad fromId(Long id) {
        if (id == null) {
            return null;
        }
        Ciudad ciudad = new Ciudad();
        ciudad.setId(id);
        return ciudad;
    }
}
