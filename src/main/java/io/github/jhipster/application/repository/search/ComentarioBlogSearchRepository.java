package io.github.jhipster.application.repository.search;

import io.github.jhipster.application.domain.ComentarioBlog;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data Elasticsearch repository for the {@link ComentarioBlog} entity.
 */
public interface ComentarioBlogSearchRepository extends ElasticsearchRepository<ComentarioBlog, Long> {
}
