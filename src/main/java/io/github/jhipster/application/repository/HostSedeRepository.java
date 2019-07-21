package io.github.jhipster.application.repository;

import io.github.jhipster.application.domain.HostSede;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Spring Data  repository for the HostSede entity.
 */
@SuppressWarnings("unused")
@Repository
public interface HostSedeRepository extends JpaRepository<HostSede, Long>, JpaSpecificationExecutor<HostSede> {

    @Query("select hostSede from HostSede hostSede where hostSede.miembro.login = ?#{principal.preferredUsername}")
    List<HostSede> findByMiembroIsCurrentUser();

}
