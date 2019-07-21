package io.github.jhipster.application.repository.search;

import io.github.jhipster.application.domain.MargenNewoBlog;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data Elasticsearch repository for the {@link MargenNewoBlog} entity.
 */
public interface MargenNewoBlogSearchRepository extends ElasticsearchRepository<MargenNewoBlog, Long> {
}
