package io.github.jhipster.application.repository.search;

import io.github.jhipster.application.domain.TipoEspacio;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data Elasticsearch repository for the {@link TipoEspacio} entity.
 */
public interface TipoEspacioSearchRepository extends ElasticsearchRepository<TipoEspacio, Long> {
}
