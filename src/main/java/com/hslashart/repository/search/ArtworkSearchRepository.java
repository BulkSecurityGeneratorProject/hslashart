package com.hslashart.repository.search;

import com.hslashart.domain.Artwork;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data Elasticsearch repository for the Artwork entity.
 */
public interface ArtworkSearchRepository extends ElasticsearchRepository<Artwork, String> {
}
