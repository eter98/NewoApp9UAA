package io.github.jhipster.application.repository.search;

import io.github.jhipster.application.domain.HostSede;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data Elasticsearch repository for the {@link HostSede} entity.
 */
public interface HostSedeSearchRepository extends ElasticsearchRepository<HostSede, Long> {
}
