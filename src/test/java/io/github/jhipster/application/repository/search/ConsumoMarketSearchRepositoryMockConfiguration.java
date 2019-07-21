package io.github.jhipster.application.repository.search;

import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Configuration;

/**
 * Configure a Mock version of {@link ConsumoMarketSearchRepository} to test the
 * application without starting Elasticsearch.
 */
@Configuration
public class ConsumoMarketSearchRepositoryMockConfiguration {

    @MockBean
    private ConsumoMarketSearchRepository mockConsumoMarketSearchRepository;

}
