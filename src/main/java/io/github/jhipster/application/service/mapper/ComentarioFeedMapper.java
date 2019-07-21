package io.github.jhipster.application.service.mapper;

import io.github.jhipster.application.domain.*;
import io.github.jhipster.application.service.dto.ComentarioFeedDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link ComentarioFeed} and its DTO {@link ComentarioFeedDTO}.
 */
@Mapper(componentModel = "spring", uses = {FeedMapper.class, UserMapper.class})
public interface ComentarioFeedMapper extends EntityMapper<ComentarioFeedDTO, ComentarioFeed> {

    @Mapping(source = "feed.id", target = "feedId")
    @Mapping(source = "feed.titulo", target = "feedTitulo")
    @Mapping(source = "miembro.id", target = "miembroId")
    @Mapping(source = "miembro.login", target = "miembroLogin")
    ComentarioFeedDTO toDto(ComentarioFeed comentarioFeed);

    @Mapping(source = "feedId", target = "feed")
    @Mapping(source = "miembroId", target = "miembro")
    ComentarioFeed toEntity(ComentarioFeedDTO comentarioFeedDTO);

    default ComentarioFeed fromId(Long id) {
        if (id == null) {
            return null;
        }
        ComentarioFeed comentarioFeed = new ComentarioFeed();
        comentarioFeed.setId(id);
        return comentarioFeed;
    }
}
