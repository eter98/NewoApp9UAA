package io.github.jhipster.application.service.mapper;

import io.github.jhipster.application.domain.*;
import io.github.jhipster.application.service.dto.ChatDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Chat} and its DTO {@link ChatDTO}.
 */
@Mapper(componentModel = "spring", uses = {UserMapper.class})
public interface ChatMapper extends EntityMapper<ChatDTO, Chat> {

    @Mapping(source = "de.id", target = "deId")
    @Mapping(source = "de.login", target = "deLogin")
    @Mapping(source = "para.id", target = "paraId")
    @Mapping(source = "para.login", target = "paraLogin")
    ChatDTO toDto(Chat chat);

    @Mapping(source = "deId", target = "de")
    @Mapping(source = "paraId", target = "para")
    Chat toEntity(ChatDTO chatDTO);

    default Chat fromId(Long id) {
        if (id == null) {
            return null;
        }
        Chat chat = new Chat();
        chat.setId(id);
        return chat;
    }
}
