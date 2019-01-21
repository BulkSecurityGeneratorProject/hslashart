package com.hslashart.repository;

import com.hslashart.domain.Gallery;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Spring Data MongoDB repository for the Gallery entity.
 */
@SuppressWarnings("unused")
@Repository
public interface GalleryRepository extends MongoRepository<Gallery, String> {
    @Query("{}")
    Page<Gallery> findAllWithEagerRelationships(Pageable pageable);

    @Query("{}")
    List<Gallery> findAllWithEagerRelationships();

    @Query("{'id': ?0}")
    Optional<Gallery> findOneWithEagerRelationships(String id);

}
