package io.github.jhipster.application.repository.search;

import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Configuration;

/**
 * Configure a Mock version of {@link FeedSearchRepository} to test the
 * application without starting Elasticsearch.
 */
@Configuration
public class FeedSearchRepositoryMockConfiguration {

    @MockBean
    private FeedSearchRepository mockFeedSearchRepository;

}
