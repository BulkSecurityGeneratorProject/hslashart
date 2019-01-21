package com.hslashart.web.rest;

import com.hslashart.HslashartApp;

import com.hslashart.domain.Artwork;
import com.hslashart.repository.ArtworkRepository;
import com.hslashart.repository.search.ArtworkSearchRepository;
import com.hslashart.web.rest.errors.ExceptionTranslator;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.validation.Validator;

import java.util.Collections;
import java.util.List;


import static com.hslashart.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.elasticsearch.index.query.QueryBuilders.queryStringQuery;
import static org.hamcrest.Matchers.hasItem;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for the ArtworkResource REST controller.
 *
 * @see ArtworkResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = HslashartApp.class)
public class ArtworkResourceIntTest {

    private static final String DEFAULT_TITLE = "AAAAAAAAAA";
    private static final String UPDATED_TITLE = "BBBBBBBBBB";

    @Autowired
    private ArtworkRepository artworkRepository;

    /**
     * This repository is mocked in the com.hslashart.repository.search test package.
     *
     * @see com.hslashart.repository.search.ArtworkSearchRepositoryMockConfiguration
     */
    @Autowired
    private ArtworkSearchRepository mockArtworkSearchRepository;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private Validator validator;

    private MockMvc restArtworkMockMvc;

    private Artwork artwork;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final ArtworkResource artworkResource = new ArtworkResource(artworkRepository, mockArtworkSearchRepository);
        this.restArtworkMockMvc = MockMvcBuilders.standaloneSetup(artworkResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setConversionService(createFormattingConversionService())
            .setMessageConverters(jacksonMessageConverter)
            .setValidator(validator).build();
    }

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Artwork createEntity() {
        Artwork artwork = new Artwork()
            .title(DEFAULT_TITLE);
        return artwork;
    }

    @Before
    public void initTest() {
        artworkRepository.deleteAll();
        artwork = createEntity();
    }

    @Test
    public void createArtwork() throws Exception {
        int databaseSizeBeforeCreate = artworkRepository.findAll().size();

        // Create the Artwork
        restArtworkMockMvc.perform(post("/api/artworks")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(artwork)))
            .andExpect(status().isCreated());

        // Validate the Artwork in the database
        List<Artwork> artworkList = artworkRepository.findAll();
        assertThat(artworkList).hasSize(databaseSizeBeforeCreate + 1);
        Artwork testArtwork = artworkList.get(artworkList.size() - 1);
        assertThat(testArtwork.getTitle()).isEqualTo(DEFAULT_TITLE);

        // Validate the Artwork in Elasticsearch
        verify(mockArtworkSearchRepository, times(1)).save(testArtwork);
    }

    @Test
    public void createArtworkWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = artworkRepository.findAll().size();

        // Create the Artwork with an existing ID
        artwork.setId("existing_id");

        // An entity with an existing ID cannot be created, so this API call must fail
        restArtworkMockMvc.perform(post("/api/artworks")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(artwork)))
            .andExpect(status().isBadRequest());

        // Validate the Artwork in the database
        List<Artwork> artworkList = artworkRepository.findAll();
        assertThat(artworkList).hasSize(databaseSizeBeforeCreate);

        // Validate the Artwork in Elasticsearch
        verify(mockArtworkSearchRepository, times(0)).save(artwork);
    }

    @Test
    public void getAllArtworks() throws Exception {
        // Initialize the database
        artworkRepository.save(artwork);

        // Get all the artworkList
        restArtworkMockMvc.perform(get("/api/artworks?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(artwork.getId())))
            .andExpect(jsonPath("$.[*].title").value(hasItem(DEFAULT_TITLE.toString())));
    }
    
    @Test
    public void getArtwork() throws Exception {
        // Initialize the database
        artworkRepository.save(artwork);

        // Get the artwork
        restArtworkMockMvc.perform(get("/api/artworks/{id}", artwork.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(artwork.getId()))
            .andExpect(jsonPath("$.title").value(DEFAULT_TITLE.toString()));
    }

    @Test
    public void getNonExistingArtwork() throws Exception {
        // Get the artwork
        restArtworkMockMvc.perform(get("/api/artworks/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    public void updateArtwork() throws Exception {
        // Initialize the database
        artworkRepository.save(artwork);

        int databaseSizeBeforeUpdate = artworkRepository.findAll().size();

        // Update the artwork
        Artwork updatedArtwork = artworkRepository.findById(artwork.getId()).get();
        updatedArtwork
            .title(UPDATED_TITLE);

        restArtworkMockMvc.perform(put("/api/artworks")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedArtwork)))
            .andExpect(status().isOk());

        // Validate the Artwork in the database
        List<Artwork> artworkList = artworkRepository.findAll();
        assertThat(artworkList).hasSize(databaseSizeBeforeUpdate);
        Artwork testArtwork = artworkList.get(artworkList.size() - 1);
        assertThat(testArtwork.getTitle()).isEqualTo(UPDATED_TITLE);

        // Validate the Artwork in Elasticsearch
        verify(mockArtworkSearchRepository, times(1)).save(testArtwork);
    }

    @Test
    public void updateNonExistingArtwork() throws Exception {
        int databaseSizeBeforeUpdate = artworkRepository.findAll().size();

        // Create the Artwork

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restArtworkMockMvc.perform(put("/api/artworks")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(artwork)))
            .andExpect(status().isBadRequest());

        // Validate the Artwork in the database
        List<Artwork> artworkList = artworkRepository.findAll();
        assertThat(artworkList).hasSize(databaseSizeBeforeUpdate);

        // Validate the Artwork in Elasticsearch
        verify(mockArtworkSearchRepository, times(0)).save(artwork);
    }

    @Test
    public void deleteArtwork() throws Exception {
        // Initialize the database
        artworkRepository.save(artwork);

        int databaseSizeBeforeDelete = artworkRepository.findAll().size();

        // Get the artwork
        restArtworkMockMvc.perform(delete("/api/artworks/{id}", artwork.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Artwork> artworkList = artworkRepository.findAll();
        assertThat(artworkList).hasSize(databaseSizeBeforeDelete - 1);

        // Validate the Artwork in Elasticsearch
        verify(mockArtworkSearchRepository, times(1)).deleteById(artwork.getId());
    }

    @Test
    public void searchArtwork() throws Exception {
        // Initialize the database
        artworkRepository.save(artwork);
        when(mockArtworkSearchRepository.search(queryStringQuery("id:" + artwork.getId()), PageRequest.of(0, 20)))
            .thenReturn(new PageImpl<>(Collections.singletonList(artwork), PageRequest.of(0, 1), 1));
        // Search the artwork
        restArtworkMockMvc.perform(get("/api/_search/artworks?query=id:" + artwork.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(artwork.getId())))
            .andExpect(jsonPath("$.[*].title").value(hasItem(DEFAULT_TITLE)));
    }

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Artwork.class);
        Artwork artwork1 = new Artwork();
        artwork1.setId("id1");
        Artwork artwork2 = new Artwork();
        artwork2.setId(artwork1.getId());
        assertThat(artwork1).isEqualTo(artwork2);
        artwork2.setId("id2");
        assertThat(artwork1).isNotEqualTo(artwork2);
        artwork1.setId(null);
        assertThat(artwork1).isNotEqualTo(artwork2);
    }
}
