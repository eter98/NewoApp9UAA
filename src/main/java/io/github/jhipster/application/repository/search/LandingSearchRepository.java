package io.github.jhipster.application.repository.search;

import io.github.jhipster.application.domain.Landing;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data Elasticsearch repository for the {@link Landing} entity.
 */
public interface LandingSearchRepository extends ElasticsearchRepository<Landing, Long> {
}
