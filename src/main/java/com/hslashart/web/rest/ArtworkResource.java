package com.hslashart.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.hslashart.domain.Artwork;
import com.hslashart.repository.ArtworkRepository;
import com.hslashart.repository.search.ArtworkSearchRepository;
import com.hslashart.web.rest.errors.BadRequestAlertException;
import com.hslashart.web.rest.util.HeaderUtil;
import com.hslashart.web.rest.util.PaginationUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
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
 * REST controller for managing Artwork.
 */
@RestController
@RequestMapping("/api")
public class ArtworkResource {

    private final Logger log = LoggerFactory.getLogger(ArtworkResource.class);

    private static final String ENTITY_NAME = "artwork";

    private final ArtworkRepository artworkRepository;

    private final ArtworkSearchRepository artworkSearchRepository;

    public ArtworkResource(ArtworkRepository artworkRepository, ArtworkSearchRepository artworkSearchRepository) {
        this.artworkRepository = artworkRepository;
        this.artworkSearchRepository = artworkSearchRepository;
    }

    /**
     * POST  /artworks : Create a new artwork.
     *
     * @param artwork the artwork to create
     * @return the ResponseEntity with status 201 (Created) and with body the new artwork, or with status 400 (Bad Request) if the artwork has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/artworks")
    @Timed
    public ResponseEntity<Artwork> createArtwork(@RequestBody Artwork artwork) throws URISyntaxException {
        log.debug("REST request to save Artwork : {}", artwork);
        if (artwork.getId() != null) {
            throw new BadRequestAlertException("A new artwork cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Artwork result = artworkRepository.save(artwork);
        artworkSearchRepository.save(result);
        return ResponseEntity.created(new URI("/api/artworks/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /artworks : Updates an existing artwork.
     *
     * @param artwork the artwork to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated artwork,
     * or with status 400 (Bad Request) if the artwork is not valid,
     * or with status 500 (Internal Server Error) if the artwork couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/artworks")
    @Timed
    public ResponseEntity<Artwork> updateArtwork(@RequestBody Artwork artwork) throws URISyntaxException {
        log.debug("REST request to update Artwork : {}", artwork);
        if (artwork.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        Artwork result = artworkRepository.save(artwork);
        artworkSearchRepository.save(result);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, artwork.getId().toString()))
            .body(result);
    }

    /**
     * GET  /artworks : get all the artworks.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of artworks in body
     */
    @GetMapping("/artworks")
    @Timed
    public ResponseEntity<List<Artwork>> getAllArtworks(Pageable pageable) {
        log.debug("REST request to get a page of Artworks");
        Page<Artwork> page = artworkRepository.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/artworks");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * GET  /artworks/:id : get the "id" artwork.
     *
     * @param id the id of the artwork to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the artwork, or with status 404 (Not Found)
     */
    @GetMapping("/artworks/{id}")
    @Timed
    public ResponseEntity<Artwork> getArtwork(@PathVariable String id) {
        log.debug("REST request to get Artwork : {}", id);
        Optional<Artwork> artwork = artworkRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(artwork);
    }

    /**
     * DELETE  /artworks/:id : delete the "id" artwork.
     *
     * @param id the id of the artwork to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/artworks/{id}")
    @Timed
    public ResponseEntity<Void> deleteArtwork(@PathVariable String id) {
        log.debug("REST request to delete Artwork : {}", id);

        artworkRepository.deleteById(id);
        artworkSearchRepository.deleteById(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id)).build();
    }

    /**
     * SEARCH  /_search/artworks?query=:query : search for the artwork corresponding
     * to the query.
     *
     * @param query the query of the artwork search
     * @param pageable the pagination information
     * @return the result of the search
     */
    @GetMapping("/_search/artworks")
    @Timed
    public ResponseEntity<List<Artwork>> searchArtworks(@RequestParam String query, Pageable pageable) {
        log.debug("REST request to search for a page of Artworks for query {}", query);
        Page<Artwork> page = artworkSearchRepository.search(queryStringQuery(query), pageable);
        HttpHeaders headers = PaginationUtil.generateSearchPaginationHttpHeaders(query, page, "/api/_search/artworks");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

}
