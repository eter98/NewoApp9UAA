package io.github.jhipster.application.repository.search;

import io.github.jhipster.application.domain.MargenNewoGrupos;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data Elasticsearch repository for the {@link MargenNewoGrupos} entity.
 */
public interface MargenNewoGruposSearchRepository extends ElasticsearchRepository<MargenNewoGrupos, Long> {
}
