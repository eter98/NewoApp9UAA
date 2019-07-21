package io.github.jhipster.application.repository.search;

import io.github.jhipster.application.domain.UsoRecursoFisico;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data Elasticsearch repository for the {@link UsoRecursoFisico} entity.
 */
public interface UsoRecursoFisicoSearchRepository extends ElasticsearchRepository<UsoRecursoFisico, Long> {
}
