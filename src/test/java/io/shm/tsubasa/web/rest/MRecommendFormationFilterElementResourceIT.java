package io.shm.tsubasa.web.rest;

import io.shm.tsubasa.TsubasaApp;
import io.shm.tsubasa.domain.MRecommendFormationFilterElement;
import io.shm.tsubasa.repository.MRecommendFormationFilterElementRepository;
import io.shm.tsubasa.service.MRecommendFormationFilterElementService;
import io.shm.tsubasa.service.dto.MRecommendFormationFilterElementDTO;
import io.shm.tsubasa.service.mapper.MRecommendFormationFilterElementMapper;
import io.shm.tsubasa.web.rest.errors.ExceptionTranslator;
import io.shm.tsubasa.service.dto.MRecommendFormationFilterElementCriteria;
import io.shm.tsubasa.service.MRecommendFormationFilterElementQueryService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Base64Utils;
import org.springframework.validation.Validator;

import javax.persistence.EntityManager;
import java.util.List;

import static io.shm.tsubasa.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the {@Link MRecommendFormationFilterElementResource} REST controller.
 */
@SpringBootTest(classes = TsubasaApp.class)
public class MRecommendFormationFilterElementResourceIT {

    private static final Integer DEFAULT_FILTER_TYPE = 1;
    private static final Integer UPDATED_FILTER_TYPE = 2;

    private static final Integer DEFAULT_ELEMENT_ID = 1;
    private static final Integer UPDATED_ELEMENT_ID = 2;

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    @Autowired
    private MRecommendFormationFilterElementRepository mRecommendFormationFilterElementRepository;

    @Autowired
    private MRecommendFormationFilterElementMapper mRecommendFormationFilterElementMapper;

    @Autowired
    private MRecommendFormationFilterElementService mRecommendFormationFilterElementService;

    @Autowired
    private MRecommendFormationFilterElementQueryService mRecommendFormationFilterElementQueryService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    @Autowired
    private Validator validator;

    private MockMvc restMRecommendFormationFilterElementMockMvc;

    private MRecommendFormationFilterElement mRecommendFormationFilterElement;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final MRecommendFormationFilterElementResource mRecommendFormationFilterElementResource = new MRecommendFormationFilterElementResource(mRecommendFormationFilterElementService, mRecommendFormationFilterElementQueryService);
        this.restMRecommendFormationFilterElementMockMvc = MockMvcBuilders.standaloneSetup(mRecommendFormationFilterElementResource)
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
    public static MRecommendFormationFilterElement createEntity(EntityManager em) {
        MRecommendFormationFilterElement mRecommendFormationFilterElement = new MRecommendFormationFilterElement()
            .filterType(DEFAULT_FILTER_TYPE)
            .elementId(DEFAULT_ELEMENT_ID)
            .name(DEFAULT_NAME);
        return mRecommendFormationFilterElement;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static MRecommendFormationFilterElement createUpdatedEntity(EntityManager em) {
        MRecommendFormationFilterElement mRecommendFormationFilterElement = new MRecommendFormationFilterElement()
            .filterType(UPDATED_FILTER_TYPE)
            .elementId(UPDATED_ELEMENT_ID)
            .name(UPDATED_NAME);
        return mRecommendFormationFilterElement;
    }

    @BeforeEach
    public void initTest() {
        mRecommendFormationFilterElement = createEntity(em);
    }

    @Test
    @Transactional
    public void createMRecommendFormationFilterElement() throws Exception {
        int databaseSizeBeforeCreate = mRecommendFormationFilterElementRepository.findAll().size();

        // Create the MRecommendFormationFilterElement
        MRecommendFormationFilterElementDTO mRecommendFormationFilterElementDTO = mRecommendFormationFilterElementMapper.toDto(mRecommendFormationFilterElement);
        restMRecommendFormationFilterElementMockMvc.perform(post("/api/m-recommend-formation-filter-elements")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mRecommendFormationFilterElementDTO)))
            .andExpect(status().isCreated());

        // Validate the MRecommendFormationFilterElement in the database
        List<MRecommendFormationFilterElement> mRecommendFormationFilterElementList = mRecommendFormationFilterElementRepository.findAll();
        assertThat(mRecommendFormationFilterElementList).hasSize(databaseSizeBeforeCreate + 1);
        MRecommendFormationFilterElement testMRecommendFormationFilterElement = mRecommendFormationFilterElementList.get(mRecommendFormationFilterElementList.size() - 1);
        assertThat(testMRecommendFormationFilterElement.getFilterType()).isEqualTo(DEFAULT_FILTER_TYPE);
        assertThat(testMRecommendFormationFilterElement.getElementId()).isEqualTo(DEFAULT_ELEMENT_ID);
        assertThat(testMRecommendFormationFilterElement.getName()).isEqualTo(DEFAULT_NAME);
    }

    @Test
    @Transactional
    public void createMRecommendFormationFilterElementWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = mRecommendFormationFilterElementRepository.findAll().size();

        // Create the MRecommendFormationFilterElement with an existing ID
        mRecommendFormationFilterElement.setId(1L);
        MRecommendFormationFilterElementDTO mRecommendFormationFilterElementDTO = mRecommendFormationFilterElementMapper.toDto(mRecommendFormationFilterElement);

        // An entity with an existing ID cannot be created, so this API call must fail
        restMRecommendFormationFilterElementMockMvc.perform(post("/api/m-recommend-formation-filter-elements")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mRecommendFormationFilterElementDTO)))
            .andExpect(status().isBadRequest());

        // Validate the MRecommendFormationFilterElement in the database
        List<MRecommendFormationFilterElement> mRecommendFormationFilterElementList = mRecommendFormationFilterElementRepository.findAll();
        assertThat(mRecommendFormationFilterElementList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkFilterTypeIsRequired() throws Exception {
        int databaseSizeBeforeTest = mRecommendFormationFilterElementRepository.findAll().size();
        // set the field null
        mRecommendFormationFilterElement.setFilterType(null);

        // Create the MRecommendFormationFilterElement, which fails.
        MRecommendFormationFilterElementDTO mRecommendFormationFilterElementDTO = mRecommendFormationFilterElementMapper.toDto(mRecommendFormationFilterElement);

        restMRecommendFormationFilterElementMockMvc.perform(post("/api/m-recommend-formation-filter-elements")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mRecommendFormationFilterElementDTO)))
            .andExpect(status().isBadRequest());

        List<MRecommendFormationFilterElement> mRecommendFormationFilterElementList = mRecommendFormationFilterElementRepository.findAll();
        assertThat(mRecommendFormationFilterElementList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkElementIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = mRecommendFormationFilterElementRepository.findAll().size();
        // set the field null
        mRecommendFormationFilterElement.setElementId(null);

        // Create the MRecommendFormationFilterElement, which fails.
        MRecommendFormationFilterElementDTO mRecommendFormationFilterElementDTO = mRecommendFormationFilterElementMapper.toDto(mRecommendFormationFilterElement);

        restMRecommendFormationFilterElementMockMvc.perform(post("/api/m-recommend-formation-filter-elements")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mRecommendFormationFilterElementDTO)))
            .andExpect(status().isBadRequest());

        List<MRecommendFormationFilterElement> mRecommendFormationFilterElementList = mRecommendFormationFilterElementRepository.findAll();
        assertThat(mRecommendFormationFilterElementList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllMRecommendFormationFilterElements() throws Exception {
        // Initialize the database
        mRecommendFormationFilterElementRepository.saveAndFlush(mRecommendFormationFilterElement);

        // Get all the mRecommendFormationFilterElementList
        restMRecommendFormationFilterElementMockMvc.perform(get("/api/m-recommend-formation-filter-elements?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(mRecommendFormationFilterElement.getId().intValue())))
            .andExpect(jsonPath("$.[*].filterType").value(hasItem(DEFAULT_FILTER_TYPE)))
            .andExpect(jsonPath("$.[*].elementId").value(hasItem(DEFAULT_ELEMENT_ID)))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME.toString())));
    }
    
    @Test
    @Transactional
    public void getMRecommendFormationFilterElement() throws Exception {
        // Initialize the database
        mRecommendFormationFilterElementRepository.saveAndFlush(mRecommendFormationFilterElement);

        // Get the mRecommendFormationFilterElement
        restMRecommendFormationFilterElementMockMvc.perform(get("/api/m-recommend-formation-filter-elements/{id}", mRecommendFormationFilterElement.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(mRecommendFormationFilterElement.getId().intValue()))
            .andExpect(jsonPath("$.filterType").value(DEFAULT_FILTER_TYPE))
            .andExpect(jsonPath("$.elementId").value(DEFAULT_ELEMENT_ID))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME.toString()));
    }

    @Test
    @Transactional
    public void getAllMRecommendFormationFilterElementsByFilterTypeIsEqualToSomething() throws Exception {
        // Initialize the database
        mRecommendFormationFilterElementRepository.saveAndFlush(mRecommendFormationFilterElement);

        // Get all the mRecommendFormationFilterElementList where filterType equals to DEFAULT_FILTER_TYPE
        defaultMRecommendFormationFilterElementShouldBeFound("filterType.equals=" + DEFAULT_FILTER_TYPE);

        // Get all the mRecommendFormationFilterElementList where filterType equals to UPDATED_FILTER_TYPE
        defaultMRecommendFormationFilterElementShouldNotBeFound("filterType.equals=" + UPDATED_FILTER_TYPE);
    }

    @Test
    @Transactional
    public void getAllMRecommendFormationFilterElementsByFilterTypeIsInShouldWork() throws Exception {
        // Initialize the database
        mRecommendFormationFilterElementRepository.saveAndFlush(mRecommendFormationFilterElement);

        // Get all the mRecommendFormationFilterElementList where filterType in DEFAULT_FILTER_TYPE or UPDATED_FILTER_TYPE
        defaultMRecommendFormationFilterElementShouldBeFound("filterType.in=" + DEFAULT_FILTER_TYPE + "," + UPDATED_FILTER_TYPE);

        // Get all the mRecommendFormationFilterElementList where filterType equals to UPDATED_FILTER_TYPE
        defaultMRecommendFormationFilterElementShouldNotBeFound("filterType.in=" + UPDATED_FILTER_TYPE);
    }

    @Test
    @Transactional
    public void getAllMRecommendFormationFilterElementsByFilterTypeIsNullOrNotNull() throws Exception {
        // Initialize the database
        mRecommendFormationFilterElementRepository.saveAndFlush(mRecommendFormationFilterElement);

        // Get all the mRecommendFormationFilterElementList where filterType is not null
        defaultMRecommendFormationFilterElementShouldBeFound("filterType.specified=true");

        // Get all the mRecommendFormationFilterElementList where filterType is null
        defaultMRecommendFormationFilterElementShouldNotBeFound("filterType.specified=false");
    }

    @Test
    @Transactional
    public void getAllMRecommendFormationFilterElementsByFilterTypeIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mRecommendFormationFilterElementRepository.saveAndFlush(mRecommendFormationFilterElement);

        // Get all the mRecommendFormationFilterElementList where filterType greater than or equals to DEFAULT_FILTER_TYPE
        defaultMRecommendFormationFilterElementShouldBeFound("filterType.greaterOrEqualThan=" + DEFAULT_FILTER_TYPE);

        // Get all the mRecommendFormationFilterElementList where filterType greater than or equals to UPDATED_FILTER_TYPE
        defaultMRecommendFormationFilterElementShouldNotBeFound("filterType.greaterOrEqualThan=" + UPDATED_FILTER_TYPE);
    }

    @Test
    @Transactional
    public void getAllMRecommendFormationFilterElementsByFilterTypeIsLessThanSomething() throws Exception {
        // Initialize the database
        mRecommendFormationFilterElementRepository.saveAndFlush(mRecommendFormationFilterElement);

        // Get all the mRecommendFormationFilterElementList where filterType less than or equals to DEFAULT_FILTER_TYPE
        defaultMRecommendFormationFilterElementShouldNotBeFound("filterType.lessThan=" + DEFAULT_FILTER_TYPE);

        // Get all the mRecommendFormationFilterElementList where filterType less than or equals to UPDATED_FILTER_TYPE
        defaultMRecommendFormationFilterElementShouldBeFound("filterType.lessThan=" + UPDATED_FILTER_TYPE);
    }


    @Test
    @Transactional
    public void getAllMRecommendFormationFilterElementsByElementIdIsEqualToSomething() throws Exception {
        // Initialize the database
        mRecommendFormationFilterElementRepository.saveAndFlush(mRecommendFormationFilterElement);

        // Get all the mRecommendFormationFilterElementList where elementId equals to DEFAULT_ELEMENT_ID
        defaultMRecommendFormationFilterElementShouldBeFound("elementId.equals=" + DEFAULT_ELEMENT_ID);

        // Get all the mRecommendFormationFilterElementList where elementId equals to UPDATED_ELEMENT_ID
        defaultMRecommendFormationFilterElementShouldNotBeFound("elementId.equals=" + UPDATED_ELEMENT_ID);
    }

    @Test
    @Transactional
    public void getAllMRecommendFormationFilterElementsByElementIdIsInShouldWork() throws Exception {
        // Initialize the database
        mRecommendFormationFilterElementRepository.saveAndFlush(mRecommendFormationFilterElement);

        // Get all the mRecommendFormationFilterElementList where elementId in DEFAULT_ELEMENT_ID or UPDATED_ELEMENT_ID
        defaultMRecommendFormationFilterElementShouldBeFound("elementId.in=" + DEFAULT_ELEMENT_ID + "," + UPDATED_ELEMENT_ID);

        // Get all the mRecommendFormationFilterElementList where elementId equals to UPDATED_ELEMENT_ID
        defaultMRecommendFormationFilterElementShouldNotBeFound("elementId.in=" + UPDATED_ELEMENT_ID);
    }

    @Test
    @Transactional
    public void getAllMRecommendFormationFilterElementsByElementIdIsNullOrNotNull() throws Exception {
        // Initialize the database
        mRecommendFormationFilterElementRepository.saveAndFlush(mRecommendFormationFilterElement);

        // Get all the mRecommendFormationFilterElementList where elementId is not null
        defaultMRecommendFormationFilterElementShouldBeFound("elementId.specified=true");

        // Get all the mRecommendFormationFilterElementList where elementId is null
        defaultMRecommendFormationFilterElementShouldNotBeFound("elementId.specified=false");
    }

    @Test
    @Transactional
    public void getAllMRecommendFormationFilterElementsByElementIdIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mRecommendFormationFilterElementRepository.saveAndFlush(mRecommendFormationFilterElement);

        // Get all the mRecommendFormationFilterElementList where elementId greater than or equals to DEFAULT_ELEMENT_ID
        defaultMRecommendFormationFilterElementShouldBeFound("elementId.greaterOrEqualThan=" + DEFAULT_ELEMENT_ID);

        // Get all the mRecommendFormationFilterElementList where elementId greater than or equals to UPDATED_ELEMENT_ID
        defaultMRecommendFormationFilterElementShouldNotBeFound("elementId.greaterOrEqualThan=" + UPDATED_ELEMENT_ID);
    }

    @Test
    @Transactional
    public void getAllMRecommendFormationFilterElementsByElementIdIsLessThanSomething() throws Exception {
        // Initialize the database
        mRecommendFormationFilterElementRepository.saveAndFlush(mRecommendFormationFilterElement);

        // Get all the mRecommendFormationFilterElementList where elementId less than or equals to DEFAULT_ELEMENT_ID
        defaultMRecommendFormationFilterElementShouldNotBeFound("elementId.lessThan=" + DEFAULT_ELEMENT_ID);

        // Get all the mRecommendFormationFilterElementList where elementId less than or equals to UPDATED_ELEMENT_ID
        defaultMRecommendFormationFilterElementShouldBeFound("elementId.lessThan=" + UPDATED_ELEMENT_ID);
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultMRecommendFormationFilterElementShouldBeFound(String filter) throws Exception {
        restMRecommendFormationFilterElementMockMvc.perform(get("/api/m-recommend-formation-filter-elements?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(mRecommendFormationFilterElement.getId().intValue())))
            .andExpect(jsonPath("$.[*].filterType").value(hasItem(DEFAULT_FILTER_TYPE)))
            .andExpect(jsonPath("$.[*].elementId").value(hasItem(DEFAULT_ELEMENT_ID)))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME.toString())));

        // Check, that the count call also returns 1
        restMRecommendFormationFilterElementMockMvc.perform(get("/api/m-recommend-formation-filter-elements/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultMRecommendFormationFilterElementShouldNotBeFound(String filter) throws Exception {
        restMRecommendFormationFilterElementMockMvc.perform(get("/api/m-recommend-formation-filter-elements?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restMRecommendFormationFilterElementMockMvc.perform(get("/api/m-recommend-formation-filter-elements/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("0"));
    }


    @Test
    @Transactional
    public void getNonExistingMRecommendFormationFilterElement() throws Exception {
        // Get the mRecommendFormationFilterElement
        restMRecommendFormationFilterElementMockMvc.perform(get("/api/m-recommend-formation-filter-elements/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateMRecommendFormationFilterElement() throws Exception {
        // Initialize the database
        mRecommendFormationFilterElementRepository.saveAndFlush(mRecommendFormationFilterElement);

        int databaseSizeBeforeUpdate = mRecommendFormationFilterElementRepository.findAll().size();

        // Update the mRecommendFormationFilterElement
        MRecommendFormationFilterElement updatedMRecommendFormationFilterElement = mRecommendFormationFilterElementRepository.findById(mRecommendFormationFilterElement.getId()).get();
        // Disconnect from session so that the updates on updatedMRecommendFormationFilterElement are not directly saved in db
        em.detach(updatedMRecommendFormationFilterElement);
        updatedMRecommendFormationFilterElement
            .filterType(UPDATED_FILTER_TYPE)
            .elementId(UPDATED_ELEMENT_ID)
            .name(UPDATED_NAME);
        MRecommendFormationFilterElementDTO mRecommendFormationFilterElementDTO = mRecommendFormationFilterElementMapper.toDto(updatedMRecommendFormationFilterElement);

        restMRecommendFormationFilterElementMockMvc.perform(put("/api/m-recommend-formation-filter-elements")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mRecommendFormationFilterElementDTO)))
            .andExpect(status().isOk());

        // Validate the MRecommendFormationFilterElement in the database
        List<MRecommendFormationFilterElement> mRecommendFormationFilterElementList = mRecommendFormationFilterElementRepository.findAll();
        assertThat(mRecommendFormationFilterElementList).hasSize(databaseSizeBeforeUpdate);
        MRecommendFormationFilterElement testMRecommendFormationFilterElement = mRecommendFormationFilterElementList.get(mRecommendFormationFilterElementList.size() - 1);
        assertThat(testMRecommendFormationFilterElement.getFilterType()).isEqualTo(UPDATED_FILTER_TYPE);
        assertThat(testMRecommendFormationFilterElement.getElementId()).isEqualTo(UPDATED_ELEMENT_ID);
        assertThat(testMRecommendFormationFilterElement.getName()).isEqualTo(UPDATED_NAME);
    }

    @Test
    @Transactional
    public void updateNonExistingMRecommendFormationFilterElement() throws Exception {
        int databaseSizeBeforeUpdate = mRecommendFormationFilterElementRepository.findAll().size();

        // Create the MRecommendFormationFilterElement
        MRecommendFormationFilterElementDTO mRecommendFormationFilterElementDTO = mRecommendFormationFilterElementMapper.toDto(mRecommendFormationFilterElement);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restMRecommendFormationFilterElementMockMvc.perform(put("/api/m-recommend-formation-filter-elements")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mRecommendFormationFilterElementDTO)))
            .andExpect(status().isBadRequest());

        // Validate the MRecommendFormationFilterElement in the database
        List<MRecommendFormationFilterElement> mRecommendFormationFilterElementList = mRecommendFormationFilterElementRepository.findAll();
        assertThat(mRecommendFormationFilterElementList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteMRecommendFormationFilterElement() throws Exception {
        // Initialize the database
        mRecommendFormationFilterElementRepository.saveAndFlush(mRecommendFormationFilterElement);

        int databaseSizeBeforeDelete = mRecommendFormationFilterElementRepository.findAll().size();

        // Delete the mRecommendFormationFilterElement
        restMRecommendFormationFilterElementMockMvc.perform(delete("/api/m-recommend-formation-filter-elements/{id}", mRecommendFormationFilterElement.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database is empty
        List<MRecommendFormationFilterElement> mRecommendFormationFilterElementList = mRecommendFormationFilterElementRepository.findAll();
        assertThat(mRecommendFormationFilterElementList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(MRecommendFormationFilterElement.class);
        MRecommendFormationFilterElement mRecommendFormationFilterElement1 = new MRecommendFormationFilterElement();
        mRecommendFormationFilterElement1.setId(1L);
        MRecommendFormationFilterElement mRecommendFormationFilterElement2 = new MRecommendFormationFilterElement();
        mRecommendFormationFilterElement2.setId(mRecommendFormationFilterElement1.getId());
        assertThat(mRecommendFormationFilterElement1).isEqualTo(mRecommendFormationFilterElement2);
        mRecommendFormationFilterElement2.setId(2L);
        assertThat(mRecommendFormationFilterElement1).isNotEqualTo(mRecommendFormationFilterElement2);
        mRecommendFormationFilterElement1.setId(null);
        assertThat(mRecommendFormationFilterElement1).isNotEqualTo(mRecommendFormationFilterElement2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(MRecommendFormationFilterElementDTO.class);
        MRecommendFormationFilterElementDTO mRecommendFormationFilterElementDTO1 = new MRecommendFormationFilterElementDTO();
        mRecommendFormationFilterElementDTO1.setId(1L);
        MRecommendFormationFilterElementDTO mRecommendFormationFilterElementDTO2 = new MRecommendFormationFilterElementDTO();
        assertThat(mRecommendFormationFilterElementDTO1).isNotEqualTo(mRecommendFormationFilterElementDTO2);
        mRecommendFormationFilterElementDTO2.setId(mRecommendFormationFilterElementDTO1.getId());
        assertThat(mRecommendFormationFilterElementDTO1).isEqualTo(mRecommendFormationFilterElementDTO2);
        mRecommendFormationFilterElementDTO2.setId(2L);
        assertThat(mRecommendFormationFilterElementDTO1).isNotEqualTo(mRecommendFormationFilterElementDTO2);
        mRecommendFormationFilterElementDTO1.setId(null);
        assertThat(mRecommendFormationFilterElementDTO1).isNotEqualTo(mRecommendFormationFilterElementDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(mRecommendFormationFilterElementMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(mRecommendFormationFilterElementMapper.fromId(null)).isNull();
    }
}
