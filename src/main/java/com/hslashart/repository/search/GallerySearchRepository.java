package com.hslashart.repository.search;

import com.hslashart.domain.Gallery;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data Elasticsearch repository for the Gallery entity.
 */
public interface GallerySearchRepository extends ElasticsearchRepository<Gallery, String> {
}
