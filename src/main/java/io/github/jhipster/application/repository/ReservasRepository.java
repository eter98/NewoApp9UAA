package io.github.jhipster.application.repository;

import io.github.jhipster.application.domain.Reservas;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Spring Data  repository for the Reservas entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ReservasRepository extends JpaRepository<Reservas, Long>, JpaSpecificationExecutor<Reservas> {

    @Query("select reservas from Reservas reservas where reservas.miembro.login = ?#{principal.preferredUsername}")
    List<Reservas> findByMiembroIsCurrentUser();

}
