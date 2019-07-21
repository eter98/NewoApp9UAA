package io.github.jhipster.application.repository;

import io.github.jhipster.application.domain.ComentarioBlog;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Spring Data  repository for the ComentarioBlog entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ComentarioBlogRepository extends JpaRepository<ComentarioBlog, Long>, JpaSpecificationExecutor<ComentarioBlog> {

    @Query("select comentarioBlog from ComentarioBlog comentarioBlog where comentarioBlog.miembro.login = ?#{principal.preferredUsername}")
    List<ComentarioBlog> findByMiembroIsCurrentUser();

}
