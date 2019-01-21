package com.hslashart.repository.search;

import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Configuration;

/**
 * Configure a Mock version of GallerySearchRepository to test the
 * application without starting Elasticsearch.
 */
@Configuration
public class GallerySearchRepositoryMockConfiguration {

    @MockBean
    private GallerySearchRepository mockGallerySearchRepository;

}
