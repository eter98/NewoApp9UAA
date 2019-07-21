package io.github.jhipster.application.repository;

import io.github.jhipster.application.domain.ChatGrupo;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Spring Data  repository for the ChatGrupo entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ChatGrupoRepository extends JpaRepository<ChatGrupo, Long>, JpaSpecificationExecutor<ChatGrupo> {

    @Query("select chatGrupo from ChatGrupo chatGrupo where chatGrupo.de.login = ?#{principal.preferredUsername}")
    List<ChatGrupo> findByDeIsCurrentUser();

    @Query("select chatGrupo from ChatGrupo chatGrupo where chatGrupo.para.login = ?#{principal.preferredUsername}")
    List<ChatGrupo> findByParaIsCurrentUser();

}
