package io.github.jhipster.application.service.mapper;

import io.github.jhipster.application.domain.*;
import io.github.jhipster.application.service.dto.ComentarioBlogDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link ComentarioBlog} and its DTO {@link ComentarioBlogDTO}.
 */
@Mapper(componentModel = "spring", uses = {BlogMapper.class, UserMapper.class})
public interface ComentarioBlogMapper extends EntityMapper<ComentarioBlogDTO, ComentarioBlog> {

    @Mapping(source = "blog.id", target = "blogId")
    @Mapping(source = "blog.titulo", target = "blogTitulo")
    @Mapping(source = "miembro.id", target = "miembroId")
    @Mapping(source = "miembro.login", target = "miembroLogin")
    ComentarioBlogDTO toDto(ComentarioBlog comentarioBlog);

    @Mapping(source = "blogId", target = "blog")
    @Mapping(source = "miembroId", target = "miembro")
    ComentarioBlog toEntity(ComentarioBlogDTO comentarioBlogDTO);

    default ComentarioBlog fromId(Long id) {
        if (id == null) {
            return null;
        }
        ComentarioBlog comentarioBlog = new ComentarioBlog();
        comentarioBlog.setId(id);
        return comentarioBlog;
    }
}
