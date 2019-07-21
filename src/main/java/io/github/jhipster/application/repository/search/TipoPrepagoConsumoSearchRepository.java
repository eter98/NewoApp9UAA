package io.github.jhipster.application.repository.search;

import io.github.jhipster.application.domain.TipoPrepagoConsumo;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data Elasticsearch repository for the {@link TipoPrepagoConsumo} entity.
 */
public interface TipoPrepagoConsumoSearchRepository extends ElasticsearchRepository<TipoPrepagoConsumo, Long> {
}
