package io.github.jhipster.application.repository.search;

import io.github.jhipster.application.domain.ChatGrupo;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data Elasticsearch repository for the {@link ChatGrupo} entity.
 */
public interface ChatGrupoSearchRepository extends ElasticsearchRepository<ChatGrupo, Long> {
}
