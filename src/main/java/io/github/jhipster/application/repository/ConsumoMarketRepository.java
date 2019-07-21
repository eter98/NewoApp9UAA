package io.github.jhipster.application.repository;

import io.github.jhipster.application.domain.ConsumoMarket;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Spring Data  repository for the ConsumoMarket entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ConsumoMarketRepository extends JpaRepository<ConsumoMarket, Long>, JpaSpecificationExecutor<ConsumoMarket> {

    @Query("select consumoMarket from ConsumoMarket consumoMarket where consumoMarket.miembro.login = ?#{principal.preferredUsername}")
    List<ConsumoMarket> findByMiembroIsCurrentUser();

}
