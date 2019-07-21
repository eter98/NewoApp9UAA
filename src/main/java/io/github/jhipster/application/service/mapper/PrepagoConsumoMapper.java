package io.github.jhipster.application.service.mapper;

import io.github.jhipster.application.domain.*;
import io.github.jhipster.application.service.dto.PrepagoConsumoDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link PrepagoConsumo} and its DTO {@link PrepagoConsumoDTO}.
 */
@Mapper(componentModel = "spring", uses = {UserMapper.class, TipoPrepagoConsumoMapper.class})
public interface PrepagoConsumoMapper extends EntityMapper<PrepagoConsumoDTO, PrepagoConsumo> {

    @Mapping(source = "miembro.id", target = "miembroId")
    @Mapping(source = "miembro.login", target = "miembroLogin")
    @Mapping(source = "tipoPrepago.id", target = "tipoPrepagoId")
    @Mapping(source = "tipoPrepago.nombre", target = "tipoPrepagoNombre")
    PrepagoConsumoDTO toDto(PrepagoConsumo prepagoConsumo);

    @Mapping(source = "miembroId", target = "miembro")
    @Mapping(source = "tipoPrepagoId", target = "tipoPrepago")
    PrepagoConsumo toEntity(PrepagoConsumoDTO prepagoConsumoDTO);

    default PrepagoConsumo fromId(Long id) {
        if (id == null) {
            return null;
        }
        PrepagoConsumo prepagoConsumo = new PrepagoConsumo();
        prepagoConsumo.setId(id);
        return prepagoConsumo;
    }
}
