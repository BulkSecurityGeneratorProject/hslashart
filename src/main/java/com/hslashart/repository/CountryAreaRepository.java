package com.hslashart.repository;

import com.hslashart.domain.CountryArea;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;


/**
 * Spring Data MongoDB repository for the CountryArea entity.
 */
@SuppressWarnings("unused")
@Repository
public interface CountryAreaRepository extends MongoRepository<CountryArea, String> {

}
