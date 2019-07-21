package io.github.jhipster.application.service.mapper;

import io.github.jhipster.application.domain.*;
import io.github.jhipster.application.service.dto.FeedDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Feed} and its DTO {@link FeedDTO}.
 */
@Mapper(componentModel = "spring", uses = {UserMapper.class, CategoriaContenidosMapper.class})
public interface FeedMapper extends EntityMapper<FeedDTO, Feed> {

    @Mapping(source = "miembro.id", target = "miembroId")
    @Mapping(source = "miembro.login", target = "miembroLogin")
    @Mapping(source = "categoriaFeed.id", target = "categoriaFeedId")
    @Mapping(source = "categoriaFeed.categoria", target = "categoriaFeedCategoria")
    FeedDTO toDto(Feed feed);

    @Mapping(source = "miembroId", target = "miembro")
    @Mapping(source = "categoriaFeedId", target = "categoriaFeed")
    Feed toEntity(FeedDTO feedDTO);

    default Feed fromId(Long id) {
        if (id == null) {
            return null;
        }
        Feed feed = new Feed();
        feed.setId(id);
        return feed;
    }
}
