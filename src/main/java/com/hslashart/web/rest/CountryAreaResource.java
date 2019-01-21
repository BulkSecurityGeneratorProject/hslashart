package com.hslashart.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.hslashart.domain.CountryArea;
import com.hslashart.repository.CountryAreaRepository;
import com.hslashart.repository.search.CountryAreaSearchRepository;
import com.hslashart.web.rest.errors.BadRequestAlertException;
import com.hslashart.web.rest.util.HeaderUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import static org.elasticsearch.index.query.QueryBuilders.*;

/**
 * REST controller for managing CountryArea.
 */
@RestController
@RequestMapping("/api")
public class CountryAreaResource {

    private final Logger log = LoggerFactory.getLogger(CountryAreaResource.class);

    private static final String ENTITY_NAME = "countryArea";

    private final CountryAreaRepository countryAreaRepository;

    private final CountryAreaSearchRepository countryAreaSearchRepository;

    public CountryAreaResource(CountryAreaRepository countryAreaRepository, CountryAreaSearchRepository countryAreaSearchRepository) {
        this.countryAreaRepository = countryAreaRepository;
        this.countryAreaSearchRepository = countryAreaSearchRepository;
    }

    /**
     * POST  /country-areas : Create a new countryArea.
     *
     * @param countryArea the countryArea to create
     * @return the ResponseEntity with status 201 (Created) and with body the new countryArea, or with status 400 (Bad Request) if the countryArea has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/country-areas")
    @Timed
    public ResponseEntity<CountryArea> createCountryArea(@RequestBody CountryArea countryArea) throws URISyntaxException {
        log.debug("REST request to save CountryArea : {}", countryArea);
        if (countryArea.getId() != null) {
            throw new BadRequestAlertException("A new countryArea cannot already have an ID", ENTITY_NAME, "idexists");
        }
        CountryArea result = countryAreaRepository.save(countryArea);
        countryAreaSearchRepository.save(result);
        return ResponseEntity.created(new URI("/api/country-areas/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /country-areas : Updates an existing countryArea.
     *
     * @param countryArea the countryArea to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated countryArea,
     * or with status 400 (Bad Request) if the countryArea is not valid,
     * or with status 500 (Internal Server Error) if the countryArea couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/country-areas")
    @Timed
    public ResponseEntity<CountryArea> updateCountryArea(@RequestBody CountryArea countryArea) throws URISyntaxException {
        log.debug("REST request to update CountryArea : {}", countryArea);
        if (countryArea.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        CountryArea result = countryAreaRepository.save(countryArea);
        countryAreaSearchRepository.save(result);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, countryArea.getId().toString()))
            .body(result);
    }

    /**
     * GET  /country-areas : get all the countryAreas.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of countryAreas in body
     */
    @GetMapping("/country-areas")
    @Timed
    public List<CountryArea> getAllCountryAreas() {
        log.debug("REST request to get all CountryAreas");
        return countryAreaRepository.findAll();
    }

    /**
     * GET  /country-areas/:id : get the "id" countryArea.
     *
     * @param id the id of the countryArea to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the countryArea, or with status 404 (Not Found)
     */
    @GetMapping("/country-areas/{id}")
    @Timed
    public ResponseEntity<CountryArea> getCountryArea(@PathVariable String id) {
        log.debug("REST request to get CountryArea : {}", id);
        Optional<CountryArea> countryArea = countryAreaRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(countryArea);
    }

    /**
     * DELETE  /country-areas/:id : delete the "id" countryArea.
     *
     * @param id the id of the countryArea to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/country-areas/{id}")
    @Timed
    public ResponseEntity<Void> deleteCountryArea(@PathVariable String id) {
        log.debug("REST request to delete CountryArea : {}", id);

        countryAreaRepository.deleteById(id);
        countryAreaSearchRepository.deleteById(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id)).build();
    }

    /**
     * SEARCH  /_search/country-areas?query=:query : search for the countryArea corresponding
     * to the query.
     *
     * @param query the query of the countryArea search
     * @return the result of the search
     */
    @GetMapping("/_search/country-areas")
    @Timed
    public List<CountryArea> searchCountryAreas(@RequestParam String query) {
        log.debug("REST request to search CountryAreas for query {}", query);
        return StreamSupport
            .stream(countryAreaSearchRepository.search(queryStringQuery(query)).spliterator(), false)
            .collect(Collectors.toList());
    }

}
