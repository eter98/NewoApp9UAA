package io.github.jhipster.application.service.mapper;

import io.github.jhipster.application.domain.*;
import io.github.jhipster.application.service.dto.ChatGrupoDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link ChatGrupo} and its DTO {@link ChatGrupoDTO}.
 */
@Mapper(componentModel = "spring", uses = {UserMapper.class, GruposMapper.class})
public interface ChatGrupoMapper extends EntityMapper<ChatGrupoDTO, ChatGrupo> {

    @Mapping(source = "de.id", target = "deId")
    @Mapping(source = "de.login", target = "deLogin")
    @Mapping(source = "para.id", target = "paraId")
    @Mapping(source = "para.login", target = "paraLogin")
    @Mapping(source = "grupo.id", target = "grupoId")
    @Mapping(source = "grupo.nombreGrupo", target = "grupoNombreGrupo")
    ChatGrupoDTO toDto(ChatGrupo chatGrupo);

    @Mapping(source = "deId", target = "de")
    @Mapping(source = "paraId", target = "para")
    @Mapping(source = "grupoId", target = "grupo")
    ChatGrupo toEntity(ChatGrupoDTO chatGrupoDTO);

    default ChatGrupo fromId(Long id) {
        if (id == null) {
            return null;
        }
        ChatGrupo chatGrupo = new ChatGrupo();
        chatGrupo.setId(id);
        return chatGrupo;
    }
}
