package com.hslashart.web.rest;

import com.hslashart.HslashartApp;

import com.hslashart.domain.CountryArea;
import com.hslashart.repository.CountryAreaRepository;
import com.hslashart.repository.search.CountryAreaSearchRepository;
import com.hslashart.web.rest.errors.ExceptionTranslator;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
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
 * Test class for the CountryAreaResource REST controller.
 *
 * @see CountryAreaResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = HslashartApp.class)
public class CountryAreaResourceIntTest {

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_CODE = "AAAAAAAAAA";
    private static final String UPDATED_CODE = "BBBBBBBBBB";

    @Autowired
    private CountryAreaRepository countryAreaRepository;

    /**
     * This repository is mocked in the com.hslashart.repository.search test package.
     *
     * @see com.hslashart.repository.search.CountryAreaSearchRepositoryMockConfiguration
     */
    @Autowired
    private CountryAreaSearchRepository mockCountryAreaSearchRepository;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private Validator validator;

    private MockMvc restCountryAreaMockMvc;

    private CountryArea countryArea;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final CountryAreaResource countryAreaResource = new CountryAreaResource(countryAreaRepository, mockCountryAreaSearchRepository);
        this.restCountryAreaMockMvc = MockMvcBuilders.standaloneSetup(countryAreaResource)
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
    public static CountryArea createEntity() {
        CountryArea countryArea = new CountryArea()
            .name(DEFAULT_NAME)
            .code(DEFAULT_CODE);
        return countryArea;
    }

    @Before
    public void initTest() {
        countryAreaRepository.deleteAll();
        countryArea = createEntity();
    }

    @Test
    public void createCountryArea() throws Exception {
        int databaseSizeBeforeCreate = countryAreaRepository.findAll().size();

        // Create the CountryArea
        restCountryAreaMockMvc.perform(post("/api/country-areas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(countryArea)))
            .andExpect(status().isCreated());

        // Validate the CountryArea in the database
        List<CountryArea> countryAreaList = countryAreaRepository.findAll();
        assertThat(countryAreaList).hasSize(databaseSizeBeforeCreate + 1);
        CountryArea testCountryArea = countryAreaList.get(countryAreaList.size() - 1);
        assertThat(testCountryArea.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testCountryArea.getCode()).isEqualTo(DEFAULT_CODE);

        // Validate the CountryArea in Elasticsearch
        verify(mockCountryAreaSearchRepository, times(1)).save(testCountryArea);
    }

    @Test
    public void createCountryAreaWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = countryAreaRepository.findAll().size();

        // Create the CountryArea with an existing ID
        countryArea.setId("existing_id");

        // An entity with an existing ID cannot be created, so this API call must fail
        restCountryAreaMockMvc.perform(post("/api/country-areas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(countryArea)))
            .andExpect(status().isBadRequest());

        // Validate the CountryArea in the database
        List<CountryArea> countryAreaList = countryAreaRepository.findAll();
        assertThat(countryAreaList).hasSize(databaseSizeBeforeCreate);

        // Validate the CountryArea in Elasticsearch
        verify(mockCountryAreaSearchRepository, times(0)).save(countryArea);
    }

    @Test
    public void getAllCountryAreas() throws Exception {
        // Initialize the database
        countryAreaRepository.save(countryArea);

        // Get all the countryAreaList
        restCountryAreaMockMvc.perform(get("/api/country-areas?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(countryArea.getId())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME.toString())))
            .andExpect(jsonPath("$.[*].code").value(hasItem(DEFAULT_CODE.toString())));
    }
    
    @Test
    public void getCountryArea() throws Exception {
        // Initialize the database
        countryAreaRepository.save(countryArea);

        // Get the countryArea
        restCountryAreaMockMvc.perform(get("/api/country-areas/{id}", countryArea.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(countryArea.getId()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME.toString()))
            .andExpect(jsonPath("$.code").value(DEFAULT_CODE.toString()));
    }

    @Test
    public void getNonExistingCountryArea() throws Exception {
        // Get the countryArea
        restCountryAreaMockMvc.perform(get("/api/country-areas/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    public void updateCountryArea() throws Exception {
        // Initialize the database
        countryAreaRepository.save(countryArea);

        int databaseSizeBeforeUpdate = countryAreaRepository.findAll().size();

        // Update the countryArea
        CountryArea updatedCountryArea = countryAreaRepository.findById(countryArea.getId()).get();
        updatedCountryArea
            .name(UPDATED_NAME)
            .code(UPDATED_CODE);

        restCountryAreaMockMvc.perform(put("/api/country-areas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedCountryArea)))
            .andExpect(status().isOk());

        // Validate the CountryArea in the database
        List<CountryArea> countryAreaList = countryAreaRepository.findAll();
        assertThat(countryAreaList).hasSize(databaseSizeBeforeUpdate);
        CountryArea testCountryArea = countryAreaList.get(countryAreaList.size() - 1);
        assertThat(testCountryArea.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testCountryArea.getCode()).isEqualTo(UPDATED_CODE);

        // Validate the CountryArea in Elasticsearch
        verify(mockCountryAreaSearchRepository, times(1)).save(testCountryArea);
    }

    @Test
    public void updateNonExistingCountryArea() throws Exception {
        int databaseSizeBeforeUpdate = countryAreaRepository.findAll().size();

        // Create the CountryArea

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restCountryAreaMockMvc.perform(put("/api/country-areas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(countryArea)))
            .andExpect(status().isBadRequest());

        // Validate the CountryArea in the database
        List<CountryArea> countryAreaList = countryAreaRepository.findAll();
        assertThat(countryAreaList).hasSize(databaseSizeBeforeUpdate);

        // Validate the CountryArea in Elasticsearch
        verify(mockCountryAreaSearchRepository, times(0)).save(countryArea);
    }

    @Test
    public void deleteCountryArea() throws Exception {
        // Initialize the database
        countryAreaRepository.save(countryArea);

        int databaseSizeBeforeDelete = countryAreaRepository.findAll().size();

        // Get the countryArea
        restCountryAreaMockMvc.perform(delete("/api/country-areas/{id}", countryArea.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<CountryArea> countryAreaList = countryAreaRepository.findAll();
        assertThat(countryAreaList).hasSize(databaseSizeBeforeDelete - 1);

        // Validate the CountryArea in Elasticsearch
        verify(mockCountryAreaSearchRepository, times(1)).deleteById(countryArea.getId());
    }

    @Test
    public void searchCountryArea() throws Exception {
        // Initialize the database
        countryAreaRepository.save(countryArea);
        when(mockCountryAreaSearchRepository.search(queryStringQuery("id:" + countryArea.getId())))
            .thenReturn(Collections.singletonList(countryArea));
        // Search the countryArea
        restCountryAreaMockMvc.perform(get("/api/_search/country-areas?query=id:" + countryArea.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(countryArea.getId())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME)))
            .andExpect(jsonPath("$.[*].code").value(hasItem(DEFAULT_CODE)));
    }

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(CountryArea.class);
        CountryArea countryArea1 = new CountryArea();
        countryArea1.setId("id1");
        CountryArea countryArea2 = new CountryArea();
        countryArea2.setId(countryArea1.getId());
        assertThat(countryArea1).isEqualTo(countryArea2);
        countryArea2.setId("id2");
        assertThat(countryArea1).isNotEqualTo(countryArea2);
        countryArea1.setId(null);
        assertThat(countryArea1).isNotEqualTo(countryArea2);
    }
}
