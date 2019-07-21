package io.github.jhipster.application.repository.search;

import io.github.jhipster.application.domain.ComentarioFeed;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data Elasticsearch repository for the {@link ComentarioFeed} entity.
 */
public interface ComentarioFeedSearchRepository extends ElasticsearchRepository<ComentarioFeed, Long> {
}
