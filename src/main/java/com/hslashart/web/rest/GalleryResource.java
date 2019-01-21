package com.hslashart.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.hslashart.domain.Gallery;
import com.hslashart.repository.GalleryRepository;
import com.hslashart.repository.search.GallerySearchRepository;
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
 * REST controller for managing Gallery.
 */
@RestController
@RequestMapping("/api")
public class GalleryResource {

    private final Logger log = LoggerFactory.getLogger(GalleryResource.class);

    private static final String ENTITY_NAME = "gallery";

    private final GalleryRepository galleryRepository;

    private final GallerySearchRepository gallerySearchRepository;

    public GalleryResource(GalleryRepository galleryRepository, GallerySearchRepository gallerySearchRepository) {
        this.galleryRepository = galleryRepository;
        this.gallerySearchRepository = gallerySearchRepository;
    }

    /**
     * POST  /galleries : Create a new gallery.
     *
     * @param gallery the gallery to create
     * @return the ResponseEntity with status 201 (Created) and with body the new gallery, or with status 400 (Bad Request) if the gallery has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/galleries")
    @Timed
    public ResponseEntity<Gallery> createGallery(@RequestBody Gallery gallery) throws URISyntaxException {
        log.debug("REST request to save Gallery : {}", gallery);
        if (gallery.getId() != null) {
            throw new BadRequestAlertException("A new gallery cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Gallery result = galleryRepository.save(gallery);
        gallerySearchRepository.save(result);
        return ResponseEntity.created(new URI("/api/galleries/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /galleries : Updates an existing gallery.
     *
     * @param gallery the gallery to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated gallery,
     * or with status 400 (Bad Request) if the gallery is not valid,
     * or with status 500 (Internal Server Error) if the gallery couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/galleries")
    @Timed
    public ResponseEntity<Gallery> updateGallery(@RequestBody Gallery gallery) throws URISyntaxException {
        log.debug("REST request to update Gallery : {}", gallery);
        if (gallery.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        Gallery result = galleryRepository.save(gallery);
        gallerySearchRepository.save(result);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, gallery.getId().toString()))
            .body(result);
    }

    /**
     * GET  /galleries : get all the galleries.
     *
     * @param pageable the pagination information
     * @param eagerload flag to eager load entities from relationships (This is applicable for many-to-many)
     * @return the ResponseEntity with status 200 (OK) and the list of galleries in body
     */
    @GetMapping("/galleries")
    @Timed
    public ResponseEntity<List<Gallery>> getAllGalleries(Pageable pageable, @RequestParam(required = false, defaultValue = "false") boolean eagerload) {
        log.debug("REST request to get a page of Galleries");
        Page<Gallery> page;
        if (eagerload) {
            page = galleryRepository.findAllWithEagerRelationships(pageable);
        } else {
            page = galleryRepository.findAll(pageable);
        }
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, String.format("/api/galleries?eagerload=%b", eagerload));
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * GET  /galleries/:id : get the "id" gallery.
     *
     * @param id the id of the gallery to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the gallery, or with status 404 (Not Found)
     */
    @GetMapping("/galleries/{id}")
    @Timed
    public ResponseEntity<Gallery> getGallery(@PathVariable String id) {
        log.debug("REST request to get Gallery : {}", id);
        Optional<Gallery> gallery = galleryRepository.findOneWithEagerRelationships(id);
        return ResponseUtil.wrapOrNotFound(gallery);
    }

    /**
     * DELETE  /galleries/:id : delete the "id" gallery.
     *
     * @param id the id of the gallery to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/galleries/{id}")
    @Timed
    public ResponseEntity<Void> deleteGallery(@PathVariable String id) {
        log.debug("REST request to delete Gallery : {}", id);

        galleryRepository.deleteById(id);
        gallerySearchRepository.deleteById(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id)).build();
    }

    /**
     * SEARCH  /_search/galleries?query=:query : search for the gallery corresponding
     * to the query.
     *
     * @param query the query of the gallery search
     * @param pageable the pagination information
     * @return the result of the search
     */
    @GetMapping("/_search/galleries")
    @Timed
    public ResponseEntity<List<Gallery>> searchGalleries(@RequestParam String query, Pageable pageable) {
        log.debug("REST request to search for a page of Galleries for query {}", query);
        Page<Gallery> page = gallerySearchRepository.search(queryStringQuery(query), pageable);
        HttpHeaders headers = PaginationUtil.generateSearchPaginationHttpHeaders(query, page, "/api/_search/galleries");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

}
