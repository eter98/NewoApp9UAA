package io.github.jhipster.application.repository.search;

import io.github.jhipster.application.domain.Grupos;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data Elasticsearch repository for the {@link Grupos} entity.
 */
public interface GruposSearchRepository extends ElasticsearchRepository<Grupos, Long> {
}
