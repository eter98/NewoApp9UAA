package io.github.jhipster.application.service.mapper;

import io.github.jhipster.application.domain.*;
import io.github.jhipster.application.service.dto.UsoRecursoFisicoDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link UsoRecursoFisico} and its DTO {@link UsoRecursoFisicoDTO}.
 */
@Mapper(componentModel = "spring", uses = {RecursosFisicosMapper.class, MiembrosMapper.class})
public interface UsoRecursoFisicoMapper extends EntityMapper<UsoRecursoFisicoDTO, UsoRecursoFisico> {

    @Mapping(source = "recurso.id", target = "recursoId")
    @Mapping(source = "recurso.recurso", target = "recursoRecurso")
    @Mapping(source = "miembro.id", target = "miembroId")
    @Mapping(source = "miembro.identificacion", target = "miembroIdentificacion")
    UsoRecursoFisicoDTO toDto(UsoRecursoFisico usoRecursoFisico);

    @Mapping(source = "recursoId", target = "recurso")
    @Mapping(source = "miembroId", target = "miembro")
    UsoRecursoFisico toEntity(UsoRecursoFisicoDTO usoRecursoFisicoDTO);

    default UsoRecursoFisico fromId(Long id) {
        if (id == null) {
            return null;
        }
        UsoRecursoFisico usoRecursoFisico = new UsoRecursoFisico();
        usoRecursoFisico.setId(id);
        return usoRecursoFisico;
    }
}
