package io.github.jhipster.application.service.mapper;

import io.github.jhipster.application.domain.*;
import io.github.jhipster.application.service.dto.TipoPrepagoConsumoDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link TipoPrepagoConsumo} and its DTO {@link TipoPrepagoConsumoDTO}.
 */
@Mapper(componentModel = "spring", uses = {BeneficioMapper.class})
public interface TipoPrepagoConsumoMapper extends EntityMapper<TipoPrepagoConsumoDTO, TipoPrepagoConsumo> {

    @Mapping(source = "tipoBeneficio.id", target = "tipoBeneficioId")
    @Mapping(source = "tipoBeneficio.tipoBeneficio", target = "tipoBeneficioTipoBeneficio")
    TipoPrepagoConsumoDTO toDto(TipoPrepagoConsumo tipoPrepagoConsumo);

    @Mapping(source = "tipoBeneficioId", target = "tipoBeneficio")
    TipoPrepagoConsumo toEntity(TipoPrepagoConsumoDTO tipoPrepagoConsumoDTO);

    default TipoPrepagoConsumo fromId(Long id) {
        if (id == null) {
            return null;
        }
        TipoPrepagoConsumo tipoPrepagoConsumo = new TipoPrepagoConsumo();
        tipoPrepagoConsumo.setId(id);
        return tipoPrepagoConsumo;
    }
}
