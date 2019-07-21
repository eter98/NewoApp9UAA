package io.github.jhipster.application.repository;

import io.github.jhipster.application.domain.Grupos;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the Grupos entity.
 */
@SuppressWarnings("unused")
@Repository
public interface GruposRepository extends JpaRepository<Grupos, Long>, JpaSpecificationExecutor<Grupos> {

}
