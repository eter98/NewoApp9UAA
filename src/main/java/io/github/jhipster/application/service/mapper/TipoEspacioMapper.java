package io.github.jhipster.application.service.mapper;

import io.github.jhipster.application.domain.*;
import io.github.jhipster.application.service.dto.TipoEspacioDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link TipoEspacio} and its DTO {@link TipoEspacioDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface TipoEspacioMapper extends EntityMapper<TipoEspacioDTO, TipoEspacio> {



    default TipoEspacio fromId(Long id) {
        if (id == null) {
            return null;
        }
        TipoEspacio tipoEspacio = new TipoEspacio();
        tipoEspacio.setId(id);
        return tipoEspacio;
    }
}
