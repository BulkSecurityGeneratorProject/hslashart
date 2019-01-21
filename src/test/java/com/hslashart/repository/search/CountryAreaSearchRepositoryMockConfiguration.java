package com.hslashart.repository.search;

import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Configuration;

/**
 * Configure a Mock version of CountryAreaSearchRepository to test the
 * application without starting Elasticsearch.
 */
@Configuration
public class CountryAreaSearchRepositoryMockConfiguration {

    @MockBean
    private CountryAreaSearchRepository mockCountryAreaSearchRepository;

}
