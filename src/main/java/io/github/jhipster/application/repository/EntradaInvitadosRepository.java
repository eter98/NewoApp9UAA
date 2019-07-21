package io.github.jhipster.application.repository;

import io.github.jhipster.application.domain.EntradaInvitados;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Spring Data  repository for the EntradaInvitados entity.
 */
@SuppressWarnings("unused")
@Repository
public interface EntradaInvitadosRepository extends JpaRepository<EntradaInvitados, Long>, JpaSpecificationExecutor<EntradaInvitados> {

    @Query("select entradaInvitados from EntradaInvitados entradaInvitados where entradaInvitados.miembro.login = ?#{principal.preferredUsername}")
    List<EntradaInvitados> findByMiembroIsCurrentUser();

}
