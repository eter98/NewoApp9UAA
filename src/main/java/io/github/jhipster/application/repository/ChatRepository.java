package io.github.jhipster.application.repository;

import io.github.jhipster.application.domain.Chat;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Spring Data  repository for the Chat entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ChatRepository extends JpaRepository<Chat, Long>, JpaSpecificationExecutor<Chat> {

    @Query("select chat from Chat chat where chat.de.login = ?#{principal.preferredUsername}")
    List<Chat> findByDeIsCurrentUser();

    @Query("select chat from Chat chat where chat.para.login = ?#{principal.preferredUsername}")
    List<Chat> findByParaIsCurrentUser();

}
