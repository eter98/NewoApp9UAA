package io.github.jhipster.application.repository.search;

import io.github.jhipster.application.domain.MiembrosGrupo;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data Elasticsearch repository for the {@link MiembrosGrupo} entity.
 */
public interface MiembrosGrupoSearchRepository extends ElasticsearchRepository<MiembrosGrupo, Long> {
}
