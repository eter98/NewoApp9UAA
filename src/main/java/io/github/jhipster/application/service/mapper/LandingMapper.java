package io.github.jhipster.application.service.mapper;

import io.github.jhipster.application.domain.*;
import io.github.jhipster.application.service.dto.LandingDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Landing} and its DTO {@link LandingDTO}.
 */
@Mapper(componentModel = "spring", uses = {SedesMapper.class})
public interface LandingMapper extends EntityMapper<LandingDTO, Landing> {

    @Mapping(source = "sede.id", target = "sedeId")
    @Mapping(source = "sede.nombreSede", target = "sedeNombreSede")
    LandingDTO toDto(Landing landing);

    @Mapping(source = "sedeId", target = "sede")
    Landing toEntity(LandingDTO landingDTO);

    default Landing fromId(Long id) {
        if (id == null) {
            return null;
        }
        Landing landing = new Landing();
        landing.setId(id);
        return landing;
    }
}
