package io.github.jhipster.application.repository.search;

import io.github.jhipster.application.domain.Sedes;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data Elasticsearch repository for the {@link Sedes} entity.
 */
public interface SedesSearchRepository extends ElasticsearchRepository<Sedes, Long> {
}
