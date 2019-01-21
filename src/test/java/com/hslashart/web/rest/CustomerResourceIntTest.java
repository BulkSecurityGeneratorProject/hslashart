package com.hslashart.web.rest;

import com.hslashart.HslashartApp;

import com.hslashart.domain.Customer;
import com.hslashart.repository.CustomerRepository;
import com.hslashart.repository.search.CustomerSearchRepository;
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

import com.hslashart.domain.enumeration.Gender;
import com.hslashart.domain.enumeration.Gender;
import com.hslashart.domain.enumeration.Gender;
/**
 * Test class for the CustomerResource REST controller.
 *
 * @see CustomerResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = HslashartApp.class)
public class CustomerResourceIntTest {

    private static final String DEFAULT_FIRST_NAME = "AAAAAAAAAA";
    private static final String UPDATED_FIRST_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_LAST_NAME = "AAAAAAAAAA";
    private static final String UPDATED_LAST_NAME = "BBBBBBBBBB";

    private static final Gender DEFAULT_GENDER = Gender.MALE;
    private static final Gender UPDATED_GENDER = Gender.FEMALE;

    private static final String DEFAULT_GENDER_OTHER = "AAAAAAAAAA";
    private static final String UPDATED_GENDER_OTHER = "BBBBBBBBBB";

    private static final String DEFAULT_PHONE_MAIN = "AAAAAAAAAA";
    private static final String UPDATED_PHONE_MAIN = "BBBBBBBBBB";

    private static final String DEFAULT_PHONE_MOBILE = "AAAAAAAAAA";
    private static final String UPDATED_PHONE_MOBILE = "BBBBBBBBBB";

    private static final String DEFAULT_SHIPPING_LAST_NAME = "AAAAAAAAAA";
    private static final String UPDATED_SHIPPING_LAST_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_SHIPPING_FIRST_NAME = "AAAAAAAAAA";
    private static final String UPDATED_SHIPPING_FIRST_NAME = "BBBBBBBBBB";

    private static final Gender DEFAULT_SHIPPING_GENDER = Gender.;
    private static final Gender UPDATED_SHIPPING_GENDER = Gender.;

    private static final String DEFAULT_SHIPPING_GENDER_OTHER = "AAAAAAAAAA";
    private static final String UPDATED_SHIPPING_GENDER_OTHER = "BBBBBBBBBB";

    private static final String DEFAULT_SHIPPING_ADDRESS_LINE_1 = "AAAAAAAAAA";
    private static final String UPDATED_SHIPPING_ADDRESS_LINE_1 = "BBBBBBBBBB";

    private static final String DEFAULT_SHIPPING_ADDRESS_LINE_2 = "AAAAAAAAAA";
    private static final String UPDATED_SHIPPING_ADDRESS_LINE_2 = "BBBBBBBBBB";

    private static final String DEFAULT_SHIPPING_CITY = "AAAAAAAAAA";
    private static final String UPDATED_SHIPPING_CITY = "BBBBBBBBBB";

    private static final String DEFAULT_SHIPPING_COMMENTARY = "AAAAAAAAAA";
    private static final String UPDATED_SHIPPING_COMMENTARY = "BBBBBBBBBB";

    private static final String DEFAULT_BILLING_LAST_NAME = "AAAAAAAAAA";
    private static final String UPDATED_BILLING_LAST_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_BILLING_FIRST_NAME = "AAAAAAAAAA";
    private static final String UPDATED_BILLING_FIRST_NAME = "BBBBBBBBBB";

    private static final Gender DEFAULT_BILLING_GENDER = Gender.;
    private static final Gender UPDATED_BILLING_GENDER = Gender.;

    private static final String DEFAULT_BILLING_GENDER_OTHER = "AAAAAAAAAA";
    private static final String UPDATED_BILLING_GENDER_OTHER = "BBBBBBBBBB";

    private static final String DEFAULT_BILLING_ADDRESS_LINE_1 = "AAAAAAAAAA";
    private static final String UPDATED_BILLING_ADDRESS_LINE_1 = "BBBBBBBBBB";

    private static final String DEFAULT_BILLING_ADDRESS_LINE_2 = "AAAAAAAAAA";
    private static final String UPDATED_BILLING_ADDRESS_LINE_2 = "BBBBBBBBBB";

    private static final String DEFAULT_SHIPPING_POSTAL_CODE = "AAAAAAAAAA";
    private static final String UPDATED_SHIPPING_POSTAL_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_BILLING_POSTAL_CODE = "AAAAAAAAAA";
    private static final String UPDATED_BILLING_POSTAL_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_SHIPPING_COUNTRY = "AAAAAAAAAA";
    private static final String UPDATED_SHIPPING_COUNTRY = "BBBBBBBBBB";

    private static final String DEFAULT_SHIPPING_PROVINCE = "AAAAAAAAAA";
    private static final String UPDATED_SHIPPING_PROVINCE = "BBBBBBBBBB";

    private static final String DEFAULT_SHIPPING_COUNTRY_STATE = "AAAAAAAAAA";
    private static final String UPDATED_SHIPPING_COUNTRY_STATE = "BBBBBBBBBB";

    private static final String DEFAULT_SHIPPING_TERRITORY = "AAAAAAAAAA";
    private static final String UPDATED_SHIPPING_TERRITORY = "BBBBBBBBBB";

    private static final String DEFAULT_BILLING_CITY = "AAAAAAAAAA";
    private static final String UPDATED_BILLING_CITY = "BBBBBBBBBB";

    private static final String DEFAULT_BILLING_COUNTRY = "AAAAAAAAAA";
    private static final String UPDATED_BILLING_COUNTRY = "BBBBBBBBBB";

    private static final String DEFAULT_BILLING_PROVINCE = "AAAAAAAAAA";
    private static final String UPDATED_BILLING_PROVINCE = "BBBBBBBBBB";

    private static final String DEFAULT_BILLING_TERRITORY = "AAAAAAAAAA";
    private static final String UPDATED_BILLING_TERRITORY = "BBBBBBBBBB";

    private static final String DEFAULT_BILLING_COUNTRY_STATE = "AAAAAAAAAA";
    private static final String UPDATED_BILLING_COUNTRY_STATE = "BBBBBBBBBB";

    @Autowired
    private CustomerRepository customerRepository;

    /**
     * This repository is mocked in the com.hslashart.repository.search test package.
     *
     * @see com.hslashart.repository.search.CustomerSearchRepositoryMockConfiguration
     */
    @Autowired
    private CustomerSearchRepository mockCustomerSearchRepository;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private Validator validator;

    private MockMvc restCustomerMockMvc;

    private Customer customer;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final CustomerResource customerResource = new CustomerResource(customerRepository, mockCustomerSearchRepository);
        this.restCustomerMockMvc = MockMvcBuilders.standaloneSetup(customerResource)
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
    public static Customer createEntity() {
        Customer customer = new Customer()
            .firstName(DEFAULT_FIRST_NAME)
            .lastName(DEFAULT_LAST_NAME)
            .gender(DEFAULT_GENDER)
            .genderOther(DEFAULT_GENDER_OTHER)
            .phoneMain(DEFAULT_PHONE_MAIN)
            .phoneMobile(DEFAULT_PHONE_MOBILE)
            .shippingLastName(DEFAULT_SHIPPING_LAST_NAME)
            .shippingFirstName(DEFAULT_SHIPPING_FIRST_NAME)
            .shippingGender(DEFAULT_SHIPPING_GENDER)
            .shippingGenderOther(DEFAULT_SHIPPING_GENDER_OTHER)
            .shippingAddressLine1(DEFAULT_SHIPPING_ADDRESS_LINE_1)
            .shippingAddressLine2(DEFAULT_SHIPPING_ADDRESS_LINE_2)
            .shippingCity(DEFAULT_SHIPPING_CITY)
            .shippingCommentary(DEFAULT_SHIPPING_COMMENTARY)
            .billingLastName(DEFAULT_BILLING_LAST_NAME)
            .billingFirstName(DEFAULT_BILLING_FIRST_NAME)
            .billingGender(DEFAULT_BILLING_GENDER)
            .billingGenderOther(DEFAULT_BILLING_GENDER_OTHER)
            .billingAddressLine1(DEFAULT_BILLING_ADDRESS_LINE_1)
            .billingAddressLine2(DEFAULT_BILLING_ADDRESS_LINE_2)
            .shippingPostalCode(DEFAULT_SHIPPING_POSTAL_CODE)
            .billingPostalCode(DEFAULT_BILLING_POSTAL_CODE)
            .shippingCountry(DEFAULT_SHIPPING_COUNTRY)
            .shippingProvince(DEFAULT_SHIPPING_PROVINCE)
            .shippingCountryState(DEFAULT_SHIPPING_COUNTRY_STATE)
            .shippingTerritory(DEFAULT_SHIPPING_TERRITORY)
            .billingCity(DEFAULT_BILLING_CITY)
            .billingCountry(DEFAULT_BILLING_COUNTRY)
            .billingProvince(DEFAULT_BILLING_PROVINCE)
            .billingTerritory(DEFAULT_BILLING_TERRITORY)
            .billingCountryState(DEFAULT_BILLING_COUNTRY_STATE);
        return customer;
    }

    @Before
    public void initTest() {
        customerRepository.deleteAll();
        customer = createEntity();
    }

    @Test
    public void createCustomer() throws Exception {
        int databaseSizeBeforeCreate = customerRepository.findAll().size();

        // Create the Customer
        restCustomerMockMvc.perform(post("/api/customers")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(customer)))
            .andExpect(status().isCreated());

        // Validate the Customer in the database
        List<Customer> customerList = customerRepository.findAll();
        assertThat(customerList).hasSize(databaseSizeBeforeCreate + 1);
        Customer testCustomer = customerList.get(customerList.size() - 1);
        assertThat(testCustomer.getFirstName()).isEqualTo(DEFAULT_FIRST_NAME);
        assertThat(testCustomer.getLastName()).isEqualTo(DEFAULT_LAST_NAME);
        assertThat(testCustomer.getGender()).isEqualTo(DEFAULT_GENDER);
        assertThat(testCustomer.getGenderOther()).isEqualTo(DEFAULT_GENDER_OTHER);
        assertThat(testCustomer.getPhoneMain()).isEqualTo(DEFAULT_PHONE_MAIN);
        assertThat(testCustomer.getPhoneMobile()).isEqualTo(DEFAULT_PHONE_MOBILE);
        assertThat(testCustomer.getShippingLastName()).isEqualTo(DEFAULT_SHIPPING_LAST_NAME);
        assertThat(testCustomer.getShippingFirstName()).isEqualTo(DEFAULT_SHIPPING_FIRST_NAME);
        assertThat(testCustomer.getShippingGender()).isEqualTo(DEFAULT_SHIPPING_GENDER);
        assertThat(testCustomer.getShippingGenderOther()).isEqualTo(DEFAULT_SHIPPING_GENDER_OTHER);
        assertThat(testCustomer.getShippingAddressLine1()).isEqualTo(DEFAULT_SHIPPING_ADDRESS_LINE_1);
        assertThat(testCustomer.getShippingAddressLine2()).isEqualTo(DEFAULT_SHIPPING_ADDRESS_LINE_2);
        assertThat(testCustomer.getShippingCity()).isEqualTo(DEFAULT_SHIPPING_CITY);
        assertThat(testCustomer.getShippingCommentary()).isEqualTo(DEFAULT_SHIPPING_COMMENTARY);
        assertThat(testCustomer.getBillingLastName()).isEqualTo(DEFAULT_BILLING_LAST_NAME);
        assertThat(testCustomer.getBillingFirstName()).isEqualTo(DEFAULT_BILLING_FIRST_NAME);
        assertThat(testCustomer.getBillingGender()).isEqualTo(DEFAULT_BILLING_GENDER);
        assertThat(testCustomer.getBillingGenderOther()).isEqualTo(DEFAULT_BILLING_GENDER_OTHER);
        assertThat(testCustomer.getBillingAddressLine1()).isEqualTo(DEFAULT_BILLING_ADDRESS_LINE_1);
        assertThat(testCustomer.getBillingAddressLine2()).isEqualTo(DEFAULT_BILLING_ADDRESS_LINE_2);
        assertThat(testCustomer.getShippingPostalCode()).isEqualTo(DEFAULT_SHIPPING_POSTAL_CODE);
        assertThat(testCustomer.getBillingPostalCode()).isEqualTo(DEFAULT_BILLING_POSTAL_CODE);
        assertThat(testCustomer.getShippingCountry()).isEqualTo(DEFAULT_SHIPPING_COUNTRY);
        assertThat(testCustomer.getShippingProvince()).isEqualTo(DEFAULT_SHIPPING_PROVINCE);
        assertThat(testCustomer.getShippingCountryState()).isEqualTo(DEFAULT_SHIPPING_COUNTRY_STATE);
        assertThat(testCustomer.getShippingTerritory()).isEqualTo(DEFAULT_SHIPPING_TERRITORY);
        assertThat(testCustomer.getBillingCity()).isEqualTo(DEFAULT_BILLING_CITY);
        assertThat(testCustomer.getBillingCountry()).isEqualTo(DEFAULT_BILLING_COUNTRY);
        assertThat(testCustomer.getBillingProvince()).isEqualTo(DEFAULT_BILLING_PROVINCE);
        assertThat(testCustomer.getBillingTerritory()).isEqualTo(DEFAULT_BILLING_TERRITORY);
        assertThat(testCustomer.getBillingCountryState()).isEqualTo(DEFAULT_BILLING_COUNTRY_STATE);

        // Validate the Customer in Elasticsearch
        verify(mockCustomerSearchRepository, times(1)).save(testCustomer);
    }

    @Test
    public void createCustomerWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = customerRepository.findAll().size();

        // Create the Customer with an existing ID
        customer.setId("existing_id");

        // An entity with an existing ID cannot be created, so this API call must fail
        restCustomerMockMvc.perform(post("/api/customers")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(customer)))
            .andExpect(status().isBadRequest());

        // Validate the Customer in the database
        List<Customer> customerList = customerRepository.findAll();
        assertThat(customerList).hasSize(databaseSizeBeforeCreate);

        // Validate the Customer in Elasticsearch
        verify(mockCustomerSearchRepository, times(0)).save(customer);
    }

    @Test
    public void getAllCustomers() throws Exception {
        // Initialize the database
        customerRepository.save(customer);

        // Get all the customerList
        restCustomerMockMvc.perform(get("/api/customers?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(customer.getId())))
            .andExpect(jsonPath("$.[*].firstName").value(hasItem(DEFAULT_FIRST_NAME.toString())))
            .andExpect(jsonPath("$.[*].lastName").value(hasItem(DEFAULT_LAST_NAME.toString())))
            .andExpect(jsonPath("$.[*].gender").value(hasItem(DEFAULT_GENDER.toString())))
            .andExpect(jsonPath("$.[*].genderOther").value(hasItem(DEFAULT_GENDER_OTHER.toString())))
            .andExpect(jsonPath("$.[*].phoneMain").value(hasItem(DEFAULT_PHONE_MAIN.toString())))
            .andExpect(jsonPath("$.[*].phoneMobile").value(hasItem(DEFAULT_PHONE_MOBILE.toString())))
            .andExpect(jsonPath("$.[*].shippingLastName").value(hasItem(DEFAULT_SHIPPING_LAST_NAME.toString())))
            .andExpect(jsonPath("$.[*].shippingFirstName").value(hasItem(DEFAULT_SHIPPING_FIRST_NAME.toString())))
            .andExpect(jsonPath("$.[*].shippingGender").value(hasItem(DEFAULT_SHIPPING_GENDER.toString())))
            .andExpect(jsonPath("$.[*].shippingGenderOther").value(hasItem(DEFAULT_SHIPPING_GENDER_OTHER.toString())))
            .andExpect(jsonPath("$.[*].shippingAddressLine1").value(hasItem(DEFAULT_SHIPPING_ADDRESS_LINE_1.toString())))
            .andExpect(jsonPath("$.[*].shippingAddressLine2").value(hasItem(DEFAULT_SHIPPING_ADDRESS_LINE_2.toString())))
            .andExpect(jsonPath("$.[*].shippingCity").value(hasItem(DEFAULT_SHIPPING_CITY.toString())))
            .andExpect(jsonPath("$.[*].shippingCommentary").value(hasItem(DEFAULT_SHIPPING_COMMENTARY.toString())))
            .andExpect(jsonPath("$.[*].billingLastName").value(hasItem(DEFAULT_BILLING_LAST_NAME.toString())))
            .andExpect(jsonPath("$.[*].billingFirstName").value(hasItem(DEFAULT_BILLING_FIRST_NAME.toString())))
            .andExpect(jsonPath("$.[*].billingGender").value(hasItem(DEFAULT_BILLING_GENDER.toString())))
            .andExpect(jsonPath("$.[*].billingGenderOther").value(hasItem(DEFAULT_BILLING_GENDER_OTHER.toString())))
            .andExpect(jsonPath("$.[*].billingAddressLine1").value(hasItem(DEFAULT_BILLING_ADDRESS_LINE_1.toString())))
            .andExpect(jsonPath("$.[*].billingAddressLine2").value(hasItem(DEFAULT_BILLING_ADDRESS_LINE_2.toString())))
            .andExpect(jsonPath("$.[*].shippingPostalCode").value(hasItem(DEFAULT_SHIPPING_POSTAL_CODE.toString())))
            .andExpect(jsonPath("$.[*].billingPostalCode").value(hasItem(DEFAULT_BILLING_POSTAL_CODE.toString())))
            .andExpect(jsonPath("$.[*].shippingCountry").value(hasItem(DEFAULT_SHIPPING_COUNTRY.toString())))
            .andExpect(jsonPath("$.[*].shippingProvince").value(hasItem(DEFAULT_SHIPPING_PROVINCE.toString())))
            .andExpect(jsonPath("$.[*].shippingCountryState").value(hasItem(DEFAULT_SHIPPING_COUNTRY_STATE.toString())))
            .andExpect(jsonPath("$.[*].shippingTerritory").value(hasItem(DEFAULT_SHIPPING_TERRITORY.toString())))
            .andExpect(jsonPath("$.[*].billingCity").value(hasItem(DEFAULT_BILLING_CITY.toString())))
            .andExpect(jsonPath("$.[*].billingCountry").value(hasItem(DEFAULT_BILLING_COUNTRY.toString())))
            .andExpect(jsonPath("$.[*].billingProvince").value(hasItem(DEFAULT_BILLING_PROVINCE.toString())))
            .andExpect(jsonPath("$.[*].billingTerritory").value(hasItem(DEFAULT_BILLING_TERRITORY.toString())))
            .andExpect(jsonPath("$.[*].billingCountryState").value(hasItem(DEFAULT_BILLING_COUNTRY_STATE.toString())));
    }
    
    @Test
    public void getCustomer() throws Exception {
        // Initialize the database
        customerRepository.save(customer);

        // Get the customer
        restCustomerMockMvc.perform(get("/api/customers/{id}", customer.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(customer.getId()))
            .andExpect(jsonPath("$.firstName").value(DEFAULT_FIRST_NAME.toString()))
            .andExpect(jsonPath("$.lastName").value(DEFAULT_LAST_NAME.toString()))
            .andExpect(jsonPath("$.gender").value(DEFAULT_GENDER.toString()))
            .andExpect(jsonPath("$.genderOther").value(DEFAULT_GENDER_OTHER.toString()))
            .andExpect(jsonPath("$.phoneMain").value(DEFAULT_PHONE_MAIN.toString()))
            .andExpect(jsonPath("$.phoneMobile").value(DEFAULT_PHONE_MOBILE.toString()))
            .andExpect(jsonPath("$.shippingLastName").value(DEFAULT_SHIPPING_LAST_NAME.toString()))
            .andExpect(jsonPath("$.shippingFirstName").value(DEFAULT_SHIPPING_FIRST_NAME.toString()))
            .andExpect(jsonPath("$.shippingGender").value(DEFAULT_SHIPPING_GENDER.toString()))
            .andExpect(jsonPath("$.shippingGenderOther").value(DEFAULT_SHIPPING_GENDER_OTHER.toString()))
            .andExpect(jsonPath("$.shippingAddressLine1").value(DEFAULT_SHIPPING_ADDRESS_LINE_1.toString()))
            .andExpect(jsonPath("$.shippingAddressLine2").value(DEFAULT_SHIPPING_ADDRESS_LINE_2.toString()))
            .andExpect(jsonPath("$.shippingCity").value(DEFAULT_SHIPPING_CITY.toString()))
            .andExpect(jsonPath("$.shippingCommentary").value(DEFAULT_SHIPPING_COMMENTARY.toString()))
            .andExpect(jsonPath("$.billingLastName").value(DEFAULT_BILLING_LAST_NAME.toString()))
            .andExpect(jsonPath("$.billingFirstName").value(DEFAULT_BILLING_FIRST_NAME.toString()))
            .andExpect(jsonPath("$.billingGender").value(DEFAULT_BILLING_GENDER.toString()))
            .andExpect(jsonPath("$.billingGenderOther").value(DEFAULT_BILLING_GENDER_OTHER.toString()))
            .andExpect(jsonPath("$.billingAddressLine1").value(DEFAULT_BILLING_ADDRESS_LINE_1.toString()))
            .andExpect(jsonPath("$.billingAddressLine2").value(DEFAULT_BILLING_ADDRESS_LINE_2.toString()))
            .andExpect(jsonPath("$.shippingPostalCode").value(DEFAULT_SHIPPING_POSTAL_CODE.toString()))
            .andExpect(jsonPath("$.billingPostalCode").value(DEFAULT_BILLING_POSTAL_CODE.toString()))
            .andExpect(jsonPath("$.shippingCountry").value(DEFAULT_SHIPPING_COUNTRY.toString()))
            .andExpect(jsonPath("$.shippingProvince").value(DEFAULT_SHIPPING_PROVINCE.toString()))
            .andExpect(jsonPath("$.shippingCountryState").value(DEFAULT_SHIPPING_COUNTRY_STATE.toString()))
            .andExpect(jsonPath("$.shippingTerritory").value(DEFAULT_SHIPPING_TERRITORY.toString()))
            .andExpect(jsonPath("$.billingCity").value(DEFAULT_BILLING_CITY.toString()))
            .andExpect(jsonPath("$.billingCountry").value(DEFAULT_BILLING_COUNTRY.toString()))
            .andExpect(jsonPath("$.billingProvince").value(DEFAULT_BILLING_PROVINCE.toString()))
            .andExpect(jsonPath("$.billingTerritory").value(DEFAULT_BILLING_TERRITORY.toString()))
            .andExpect(jsonPath("$.billingCountryState").value(DEFAULT_BILLING_COUNTRY_STATE.toString()));
    }

    @Test
    public void getNonExistingCustomer() throws Exception {
        // Get the customer
        restCustomerMockMvc.perform(get("/api/customers/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    public void updateCustomer() throws Exception {
        // Initialize the database
        customerRepository.save(customer);

        int databaseSizeBeforeUpdate = customerRepository.findAll().size();

        // Update the customer
        Customer updatedCustomer = customerRepository.findById(customer.getId()).get();
        updatedCustomer
            .firstName(UPDATED_FIRST_NAME)
            .lastName(UPDATED_LAST_NAME)
            .gender(UPDATED_GENDER)
            .genderOther(UPDATED_GENDER_OTHER)
            .phoneMain(UPDATED_PHONE_MAIN)
            .phoneMobile(UPDATED_PHONE_MOBILE)
            .shippingLastName(UPDATED_SHIPPING_LAST_NAME)
            .shippingFirstName(UPDATED_SHIPPING_FIRST_NAME)
            .shippingGender(UPDATED_SHIPPING_GENDER)
            .shippingGenderOther(UPDATED_SHIPPING_GENDER_OTHER)
            .shippingAddressLine1(UPDATED_SHIPPING_ADDRESS_LINE_1)
            .shippingAddressLine2(UPDATED_SHIPPING_ADDRESS_LINE_2)
            .shippingCity(UPDATED_SHIPPING_CITY)
            .shippingCommentary(UPDATED_SHIPPING_COMMENTARY)
            .billingLastName(UPDATED_BILLING_LAST_NAME)
            .billingFirstName(UPDATED_BILLING_FIRST_NAME)
            .billingGender(UPDATED_BILLING_GENDER)
            .billingGenderOther(UPDATED_BILLING_GENDER_OTHER)
            .billingAddressLine1(UPDATED_BILLING_ADDRESS_LINE_1)
            .billingAddressLine2(UPDATED_BILLING_ADDRESS_LINE_2)
            .shippingPostalCode(UPDATED_SHIPPING_POSTAL_CODE)
            .billingPostalCode(UPDATED_BILLING_POSTAL_CODE)
            .shippingCountry(UPDATED_SHIPPING_COUNTRY)
            .shippingProvince(UPDATED_SHIPPING_PROVINCE)
            .shippingCountryState(UPDATED_SHIPPING_COUNTRY_STATE)
            .shippingTerritory(UPDATED_SHIPPING_TERRITORY)
            .billingCity(UPDATED_BILLING_CITY)
            .billingCountry(UPDATED_BILLING_COUNTRY)
            .billingProvince(UPDATED_BILLING_PROVINCE)
            .billingTerritory(UPDATED_BILLING_TERRITORY)
            .billingCountryState(UPDATED_BILLING_COUNTRY_STATE);

        restCustomerMockMvc.perform(put("/api/customers")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedCustomer)))
            .andExpect(status().isOk());

        // Validate the Customer in the database
        List<Customer> customerList = customerRepository.findAll();
        assertThat(customerList).hasSize(databaseSizeBeforeUpdate);
        Customer testCustomer = customerList.get(customerList.size() - 1);
        assertThat(testCustomer.getFirstName()).isEqualTo(UPDATED_FIRST_NAME);
        assertThat(testCustomer.getLastName()).isEqualTo(UPDATED_LAST_NAME);
        assertThat(testCustomer.getGender()).isEqualTo(UPDATED_GENDER);
        assertThat(testCustomer.getGenderOther()).isEqualTo(UPDATED_GENDER_OTHER);
        assertThat(testCustomer.getPhoneMain()).isEqualTo(UPDATED_PHONE_MAIN);
        assertThat(testCustomer.getPhoneMobile()).isEqualTo(UPDATED_PHONE_MOBILE);
        assertThat(testCustomer.getShippingLastName()).isEqualTo(UPDATED_SHIPPING_LAST_NAME);
        assertThat(testCustomer.getShippingFirstName()).isEqualTo(UPDATED_SHIPPING_FIRST_NAME);
        assertThat(testCustomer.getShippingGender()).isEqualTo(UPDATED_SHIPPING_GENDER);
        assertThat(testCustomer.getShippingGenderOther()).isEqualTo(UPDATED_SHIPPING_GENDER_OTHER);
        assertThat(testCustomer.getShippingAddressLine1()).isEqualTo(UPDATED_SHIPPING_ADDRESS_LINE_1);
        assertThat(testCustomer.getShippingAddressLine2()).isEqualTo(UPDATED_SHIPPING_ADDRESS_LINE_2);
        assertThat(testCustomer.getShippingCity()).isEqualTo(UPDATED_SHIPPING_CITY);
        assertThat(testCustomer.getShippingCommentary()).isEqualTo(UPDATED_SHIPPING_COMMENTARY);
        assertThat(testCustomer.getBillingLastName()).isEqualTo(UPDATED_BILLING_LAST_NAME);
        assertThat(testCustomer.getBillingFirstName()).isEqualTo(UPDATED_BILLING_FIRST_NAME);
        assertThat(testCustomer.getBillingGender()).isEqualTo(UPDATED_BILLING_GENDER);
        assertThat(testCustomer.getBillingGenderOther()).isEqualTo(UPDATED_BILLING_GENDER_OTHER);
        assertThat(testCustomer.getBillingAddressLine1()).isEqualTo(UPDATED_BILLING_ADDRESS_LINE_1);
        assertThat(testCustomer.getBillingAddressLine2()).isEqualTo(UPDATED_BILLING_ADDRESS_LINE_2);
        assertThat(testCustomer.getShippingPostalCode()).isEqualTo(UPDATED_SHIPPING_POSTAL_CODE);
        assertThat(testCustomer.getBillingPostalCode()).isEqualTo(UPDATED_BILLING_POSTAL_CODE);
        assertThat(testCustomer.getShippingCountry()).isEqualTo(UPDATED_SHIPPING_COUNTRY);
        assertThat(testCustomer.getShippingProvince()).isEqualTo(UPDATED_SHIPPING_PROVINCE);
        assertThat(testCustomer.getShippingCountryState()).isEqualTo(UPDATED_SHIPPING_COUNTRY_STATE);
        assertThat(testCustomer.getShippingTerritory()).isEqualTo(UPDATED_SHIPPING_TERRITORY);
        assertThat(testCustomer.getBillingCity()).isEqualTo(UPDATED_BILLING_CITY);
        assertThat(testCustomer.getBillingCountry()).isEqualTo(UPDATED_BILLING_COUNTRY);
        assertThat(testCustomer.getBillingProvince()).isEqualTo(UPDATED_BILLING_PROVINCE);
        assertThat(testCustomer.getBillingTerritory()).isEqualTo(UPDATED_BILLING_TERRITORY);
        assertThat(testCustomer.getBillingCountryState()).isEqualTo(UPDATED_BILLING_COUNTRY_STATE);

        // Validate the Customer in Elasticsearch
        verify(mockCustomerSearchRepository, times(1)).save(testCustomer);
    }

    @Test
    public void updateNonExistingCustomer() throws Exception {
        int databaseSizeBeforeUpdate = customerRepository.findAll().size();

        // Create the Customer

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restCustomerMockMvc.perform(put("/api/customers")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(customer)))
            .andExpect(status().isBadRequest());

        // Validate the Customer in the database
        List<Customer> customerList = customerRepository.findAll();
        assertThat(customerList).hasSize(databaseSizeBeforeUpdate);

        // Validate the Customer in Elasticsearch
        verify(mockCustomerSearchRepository, times(0)).save(customer);
    }

    @Test
    public void deleteCustomer() throws Exception {
        // Initialize the database
        customerRepository.save(customer);

        int databaseSizeBeforeDelete = customerRepository.findAll().size();

        // Get the customer
        restCustomerMockMvc.perform(delete("/api/customers/{id}", customer.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Customer> customerList = customerRepository.findAll();
        assertThat(customerList).hasSize(databaseSizeBeforeDelete - 1);

        // Validate the Customer in Elasticsearch
        verify(mockCustomerSearchRepository, times(1)).deleteById(customer.getId());
    }

    @Test
    public void searchCustomer() throws Exception {
        // Initialize the database
        customerRepository.save(customer);
        when(mockCustomerSearchRepository.search(queryStringQuery("id:" + customer.getId()), PageRequest.of(0, 20)))
            .thenReturn(new PageImpl<>(Collections.singletonList(customer), PageRequest.of(0, 1), 1));
        // Search the customer
        restCustomerMockMvc.perform(get("/api/_search/customers?query=id:" + customer.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(customer.getId())))
            .andExpect(jsonPath("$.[*].firstName").value(hasItem(DEFAULT_FIRST_NAME)))
            .andExpect(jsonPath("$.[*].lastName").value(hasItem(DEFAULT_LAST_NAME)))
            .andExpect(jsonPath("$.[*].gender").value(hasItem(DEFAULT_GENDER.toString())))
            .andExpect(jsonPath("$.[*].genderOther").value(hasItem(DEFAULT_GENDER_OTHER)))
            .andExpect(jsonPath("$.[*].phoneMain").value(hasItem(DEFAULT_PHONE_MAIN)))
            .andExpect(jsonPath("$.[*].phoneMobile").value(hasItem(DEFAULT_PHONE_MOBILE)))
            .andExpect(jsonPath("$.[*].shippingLastName").value(hasItem(DEFAULT_SHIPPING_LAST_NAME)))
            .andExpect(jsonPath("$.[*].shippingFirstName").value(hasItem(DEFAULT_SHIPPING_FIRST_NAME)))
            .andExpect(jsonPath("$.[*].shippingGender").value(hasItem(DEFAULT_SHIPPING_GENDER.toString())))
            .andExpect(jsonPath("$.[*].shippingGenderOther").value(hasItem(DEFAULT_SHIPPING_GENDER_OTHER)))
            .andExpect(jsonPath("$.[*].shippingAddressLine1").value(hasItem(DEFAULT_SHIPPING_ADDRESS_LINE_1)))
            .andExpect(jsonPath("$.[*].shippingAddressLine2").value(hasItem(DEFAULT_SHIPPING_ADDRESS_LINE_2)))
            .andExpect(jsonPath("$.[*].shippingCity").value(hasItem(DEFAULT_SHIPPING_CITY)))
            .andExpect(jsonPath("$.[*].shippingCommentary").value(hasItem(DEFAULT_SHIPPING_COMMENTARY)))
            .andExpect(jsonPath("$.[*].billingLastName").value(hasItem(DEFAULT_BILLING_LAST_NAME)))
            .andExpect(jsonPath("$.[*].billingFirstName").value(hasItem(DEFAULT_BILLING_FIRST_NAME)))
            .andExpect(jsonPath("$.[*].billingGender").value(hasItem(DEFAULT_BILLING_GENDER.toString())))
            .andExpect(jsonPath("$.[*].billingGenderOther").value(hasItem(DEFAULT_BILLING_GENDER_OTHER)))
            .andExpect(jsonPath("$.[*].billingAddressLine1").value(hasItem(DEFAULT_BILLING_ADDRESS_LINE_1)))
            .andExpect(jsonPath("$.[*].billingAddressLine2").value(hasItem(DEFAULT_BILLING_ADDRESS_LINE_2)))
            .andExpect(jsonPath("$.[*].shippingPostalCode").value(hasItem(DEFAULT_SHIPPING_POSTAL_CODE)))
            .andExpect(jsonPath("$.[*].billingPostalCode").value(hasItem(DEFAULT_BILLING_POSTAL_CODE)))
            .andExpect(jsonPath("$.[*].shippingCountry").value(hasItem(DEFAULT_SHIPPING_COUNTRY)))
            .andExpect(jsonPath("$.[*].shippingProvince").value(hasItem(DEFAULT_SHIPPING_PROVINCE)))
            .andExpect(jsonPath("$.[*].shippingCountryState").value(hasItem(DEFAULT_SHIPPING_COUNTRY_STATE)))
            .andExpect(jsonPath("$.[*].shippingTerritory").value(hasItem(DEFAULT_SHIPPING_TERRITORY)))
            .andExpect(jsonPath("$.[*].billingCity").value(hasItem(DEFAULT_BILLING_CITY)))
            .andExpect(jsonPath("$.[*].billingCountry").value(hasItem(DEFAULT_BILLING_COUNTRY)))
            .andExpect(jsonPath("$.[*].billingProvince").value(hasItem(DEFAULT_BILLING_PROVINCE)))
            .andExpect(jsonPath("$.[*].billingTerritory").value(hasItem(DEFAULT_BILLING_TERRITORY)))
            .andExpect(jsonPath("$.[*].billingCountryState").value(hasItem(DEFAULT_BILLING_COUNTRY_STATE)));
    }

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Customer.class);
        Customer customer1 = new Customer();
        customer1.setId("id1");
        Customer customer2 = new Customer();
        customer2.setId(customer1.getId());
        assertThat(customer1).isEqualTo(customer2);
        customer2.setId("id2");
        assertThat(customer1).isNotEqualTo(customer2);
        customer1.setId(null);
        assertThat(customer1).isNotEqualTo(customer2);
    }
}
