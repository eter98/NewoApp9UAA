package io.github.jhipster.application.repository.search;

import io.github.jhipster.application.domain.Reservas;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data Elasticsearch repository for the {@link Reservas} entity.
 */
public interface ReservasSearchRepository extends ElasticsearchRepository<Reservas, Long> {
}
