package io.github.jhipster.application.repository.search;

import io.github.jhipster.application.domain.PrepagoConsumo;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data Elasticsearch repository for the {@link PrepagoConsumo} entity.
 */
public interface PrepagoConsumoSearchRepository extends ElasticsearchRepository<PrepagoConsumo, Long> {
}
