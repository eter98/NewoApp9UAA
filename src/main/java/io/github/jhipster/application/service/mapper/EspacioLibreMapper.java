package io.github.jhipster.application.service.mapper;

import io.github.jhipster.application.domain.*;
import io.github.jhipster.application.service.dto.EspacioLibreDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link EspacioLibre} and its DTO {@link EspacioLibreDTO}.
 */
@Mapper(componentModel = "spring", uses = {SedesMapper.class, TipoEspacioMapper.class})
public interface EspacioLibreMapper extends EntityMapper<EspacioLibreDTO, EspacioLibre> {

    @Mapping(source = "sede.id", target = "sedeId")
    @Mapping(source = "sede.nombreSede", target = "sedeNombreSede")
    @Mapping(source = "tipoEspacio.id", target = "tipoEspacioId")
    @Mapping(source = "tipoEspacio.tipoEspacio", target = "tipoEspacioTipoEspacio")
    EspacioLibreDTO toDto(EspacioLibre espacioLibre);

    @Mapping(source = "sedeId", target = "sede")
    @Mapping(source = "tipoEspacioId", target = "tipoEspacio")
    EspacioLibre toEntity(EspacioLibreDTO espacioLibreDTO);

    default EspacioLibre fromId(Long id) {
        if (id == null) {
            return null;
        }
        EspacioLibre espacioLibre = new EspacioLibre();
        espacioLibre.setId(id);
        return espacioLibre;
    }
}
