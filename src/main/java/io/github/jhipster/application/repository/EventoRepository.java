package io.github.jhipster.application.repository;

import io.github.jhipster.application.domain.Evento;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Spring Data  repository for the Evento entity.
 */
@SuppressWarnings("unused")
@Repository
public interface EventoRepository extends JpaRepository<Evento, Long>, JpaSpecificationExecutor<Evento> {

    @Query("select evento from Evento evento where evento.miembro.login = ?#{principal.preferredUsername}")
    List<Evento> findByMiembroIsCurrentUser();

}
