package io.github.jhipster.application.repository;

import io.github.jhipster.application.domain.PrepagoConsumo;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Spring Data  repository for the PrepagoConsumo entity.
 */
@SuppressWarnings("unused")
@Repository
public interface PrepagoConsumoRepository extends JpaRepository<PrepagoConsumo, Long>, JpaSpecificationExecutor<PrepagoConsumo> {

    @Query("select prepagoConsumo from PrepagoConsumo prepagoConsumo where prepagoConsumo.miembro.login = ?#{principal.preferredUsername}")
    List<PrepagoConsumo> findByMiembroIsCurrentUser();

}
