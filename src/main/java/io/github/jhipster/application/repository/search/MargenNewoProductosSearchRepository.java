package io.github.jhipster.application.repository.search;

import io.github.jhipster.application.domain.MargenNewoProductos;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data Elasticsearch repository for the {@link MargenNewoProductos} entity.
 */
public interface MargenNewoProductosSearchRepository extends ElasticsearchRepository<MargenNewoProductos, Long> {
}
