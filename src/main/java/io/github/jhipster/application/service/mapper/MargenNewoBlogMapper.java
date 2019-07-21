package io.github.jhipster.application.service.mapper;

import io.github.jhipster.application.domain.*;
import io.github.jhipster.application.service.dto.MargenNewoBlogDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link MargenNewoBlog} and its DTO {@link MargenNewoBlogDTO}.
 */
@Mapper(componentModel = "spring", uses = {BlogMapper.class})
public interface MargenNewoBlogMapper extends EntityMapper<MargenNewoBlogDTO, MargenNewoBlog> {

    @Mapping(source = "blog.id", target = "blogId")
    @Mapping(source = "blog.descripcion", target = "blogDescripcion")
    MargenNewoBlogDTO toDto(MargenNewoBlog margenNewoBlog);

    @Mapping(source = "blogId", target = "blog")
    MargenNewoBlog toEntity(MargenNewoBlogDTO margenNewoBlogDTO);

    default MargenNewoBlog fromId(Long id) {
        if (id == null) {
            return null;
        }
        MargenNewoBlog margenNewoBlog = new MargenNewoBlog();
        margenNewoBlog.setId(id);
        return margenNewoBlog;
    }
}
