package io.github.jhipster.application.repository;

import io.github.jhipster.application.domain.Invitados;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Spring Data  repository for the Invitados entity.
 */
@SuppressWarnings("unused")
@Repository
public interface InvitadosRepository extends JpaRepository<Invitados, Long>, JpaSpecificationExecutor<Invitados> {

    @Query("select invitados from Invitados invitados where invitados.miembro.login = ?#{principal.preferredUsername}")
    List<Invitados> findByMiembroIsCurrentUser();

}
