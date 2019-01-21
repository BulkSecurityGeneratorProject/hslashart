package com.hslashart.repository.search;

import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Configuration;

/**
 * Configure a Mock version of ArtworkSearchRepository to test the
 * application without starting Elasticsearch.
 */
@Configuration
public class ArtworkSearchRepositoryMockConfiguration {

    @MockBean
    private ArtworkSearchRepository mockArtworkSearchRepository;

}
