package io.github.jhipster.application.repository.search;

import io.github.jhipster.application.domain.RecursosFisicos;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data Elasticsearch repository for the {@link RecursosFisicos} entity.
 */
public interface RecursosFisicosSearchRepository extends ElasticsearchRepository<RecursosFisicos, Long> {
}
