package io.github.jhipster.application.repository;

import io.github.jhipster.application.domain.Beneficio;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Spring Data  repository for the Beneficio entity.
 */
@SuppressWarnings("unused")
@Repository
public interface BeneficioRepository extends JpaRepository<Beneficio, Long>, JpaSpecificationExecutor<Beneficio> {

    @Query("select beneficio from Beneficio beneficio where beneficio.miembro.login = ?#{principal.preferredUsername}")
    List<Beneficio> findByMiembroIsCurrentUser();

}
