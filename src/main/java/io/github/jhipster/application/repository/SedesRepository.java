package io.github.jhipster.application.repository;

import io.github.jhipster.application.domain.Sedes;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the Sedes entity.
 */
@SuppressWarnings("unused")
@Repository
public interface SedesRepository extends JpaRepository<Sedes, Long>, JpaSpecificationExecutor<Sedes> {

}
