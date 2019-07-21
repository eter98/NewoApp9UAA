package io.github.jhipster.application.repository.search;

import io.github.jhipster.application.domain.ConsumoMarket;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data Elasticsearch repository for the {@link ConsumoMarket} entity.
 */
public interface ConsumoMarketSearchRepository extends ElasticsearchRepository<ConsumoMarket, Long> {
}
