package io.github.jhipster.application.repository;

import io.github.jhipster.application.domain.Feed;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Spring Data  repository for the Feed entity.
 */
@SuppressWarnings("unused")
@Repository
public interface FeedRepository extends JpaRepository<Feed, Long>, JpaSpecificationExecutor<Feed> {

    @Query("select feed from Feed feed where feed.miembro.login = ?#{principal.preferredUsername}")
    List<Feed> findByMiembroIsCurrentUser();

}
