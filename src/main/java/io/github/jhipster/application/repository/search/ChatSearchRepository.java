package io.github.jhipster.application.repository.search;

import io.github.jhipster.application.domain.Chat;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data Elasticsearch repository for the {@link Chat} entity.
 */
public interface ChatSearchRepository extends ElasticsearchRepository<Chat, Long> {
}
