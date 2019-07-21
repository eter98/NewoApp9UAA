package io.github.jhipster.application.repository.search;

import io.github.jhipster.application.domain.MargenNewoEventos;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data Elasticsearch repository for the {@link MargenNewoEventos} entity.
 */
public interface MargenNewoEventosSearchRepository extends ElasticsearchRepository<MargenNewoEventos, Long> {
}
