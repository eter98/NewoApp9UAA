package io.github.jhipster.application.repository.search;

import io.github.jhipster.application.domain.EntradaInvitados;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data Elasticsearch repository for the {@link EntradaInvitados} entity.
 */
public interface EntradaInvitadosSearchRepository extends ElasticsearchRepository<EntradaInvitados, Long> {
}
