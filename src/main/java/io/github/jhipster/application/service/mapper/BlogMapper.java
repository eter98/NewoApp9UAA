package io.github.jhipster.application.service.mapper;

import io.github.jhipster.application.domain.*;
import io.github.jhipster.application.service.dto.BlogDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Blog} and its DTO {@link BlogDTO}.
 */
@Mapper(componentModel = "spring", uses = {CategoriaContenidosMapper.class, GruposMapper.class, UserMapper.class})
public interface BlogMapper extends EntityMapper<BlogDTO, Blog> {

    @Mapping(source = "categoriaBlog.id", target = "categoriaBlogId")
    @Mapping(source = "categoriaBlog.categoria", target = "categoriaBlogCategoria")
    @Mapping(source = "grupos.id", target = "gruposId")
    @Mapping(source = "grupos.nombreGrupo", target = "gruposNombreGrupo")
    @Mapping(source = "miembro.id", target = "miembroId")
    @Mapping(source = "miembro.login", target = "miembroLogin")
    BlogDTO toDto(Blog blog);

    @Mapping(source = "categoriaBlogId", target = "categoriaBlog")
    @Mapping(source = "gruposId", target = "grupos")
    @Mapping(source = "miembroId", target = "miembro")
    Blog toEntity(BlogDTO blogDTO);

    default Blog fromId(Long id) {
        if (id == null) {
            return null;
        }
        Blog blog = new Blog();
        blog.setId(id);
        return blog;
    }
}
