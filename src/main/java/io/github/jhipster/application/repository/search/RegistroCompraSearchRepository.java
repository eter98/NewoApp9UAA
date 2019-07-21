package io.github.jhipster.application.repository.search;

import io.github.jhipster.application.domain.RegistroCompra;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data Elasticsearch repository for the {@link RegistroCompra} entity.
 */
public interface RegistroCompraSearchRepository extends ElasticsearchRepository<RegistroCompra, Long> {
}
