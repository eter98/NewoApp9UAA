package io.github.jhipster.application.service.mapper;

import io.github.jhipster.application.domain.*;
import io.github.jhipster.application.service.dto.ConsumoMarketDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link ConsumoMarket} and its DTO {@link ConsumoMarketDTO}.
 */
@Mapper(componentModel = "spring", uses = {UserMapper.class})
public interface ConsumoMarketMapper extends EntityMapper<ConsumoMarketDTO, ConsumoMarket> {

    @Mapping(source = "miembro.id", target = "miembroId")
    @Mapping(source = "miembro.login", target = "miembroLogin")
    ConsumoMarketDTO toDto(ConsumoMarket consumoMarket);

    @Mapping(source = "miembroId", target = "miembro")
    ConsumoMarket toEntity(ConsumoMarketDTO consumoMarketDTO);

    default ConsumoMarket fromId(Long id) {
        if (id == null) {
            return null;
        }
        ConsumoMarket consumoMarket = new ConsumoMarket();
        consumoMarket.setId(id);
        return consumoMarket;
    }
}
