package io.github.jhipster.application.repository.search;

import io.github.jhipster.application.domain.Feed;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data Elasticsearch repository for the {@link Feed} entity.
 */
public interface FeedSearchRepository extends ElasticsearchRepository<Feed, Long> {
}
