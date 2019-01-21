package com.hslashart.repository.search;

import com.hslashart.domain.CountryArea;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data Elasticsearch repository for the CountryArea entity.
 */
public interface CountryAreaSearchRepository extends ElasticsearchRepository<CountryArea, String> {
}
