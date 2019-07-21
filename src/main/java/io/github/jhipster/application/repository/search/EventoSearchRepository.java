package io.github.jhipster.application.repository.search;

import io.github.jhipster.application.domain.Evento;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data Elasticsearch repository for the {@link Evento} entity.
 */
public interface EventoSearchRepository extends ElasticsearchRepository<Evento, Long> {
}
