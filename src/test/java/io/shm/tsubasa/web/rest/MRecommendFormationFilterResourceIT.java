package io.shm.tsubasa.web.rest;

import io.shm.tsubasa.TsubasaApp;
import io.shm.tsubasa.domain.MRecommendFormationFilter;
import io.shm.tsubasa.repository.MRecommendFormationFilterRepository;
import io.shm.tsubasa.service.MRecommendFormationFilterService;
import io.shm.tsubasa.service.dto.MRecommendFormationFilterDTO;
import io.shm.tsubasa.service.mapper.MRecommendFormationFilterMapper;
import io.shm.tsubasa.web.rest.errors.ExceptionTranslator;
import io.shm.tsubasa.service.dto.MRecommendFormationFilterCriteria;
import io.shm.tsubasa.service.MRecommendFormationFilterQueryService;

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
 * Integration tests for the {@Link MRecommendFormationFilterResource} REST controller.
 */
@SpringBootTest(classes = TsubasaApp.class)
public class MRecommendFormationFilterResourceIT {

    private static final Integer DEFAULT_FILTER_TYPE = 1;
    private static final Integer UPDATED_FILTER_TYPE = 2;

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    @Autowired
    private MRecommendFormationFilterRepository mRecommendFormationFilterRepository;

    @Autowired
    private MRecommendFormationFilterMapper mRecommendFormationFilterMapper;

    @Autowired
    private MRecommendFormationFilterService mRecommendFormationFilterService;

    @Autowired
    private MRecommendFormationFilterQueryService mRecommendFormationFilterQueryService;

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

    private MockMvc restMRecommendFormationFilterMockMvc;

    private MRecommendFormationFilter mRecommendFormationFilter;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final MRecommendFormationFilterResource mRecommendFormationFilterResource = new MRecommendFormationFilterResource(mRecommendFormationFilterService, mRecommendFormationFilterQueryService);
        this.restMRecommendFormationFilterMockMvc = MockMvcBuilders.standaloneSetup(mRecommendFormationFilterResource)
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
    public static MRecommendFormationFilter createEntity(EntityManager em) {
        MRecommendFormationFilter mRecommendFormationFilter = new MRecommendFormationFilter()
            .filterType(DEFAULT_FILTER_TYPE)
            .name(DEFAULT_NAME);
        return mRecommendFormationFilter;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static MRecommendFormationFilter createUpdatedEntity(EntityManager em) {
        MRecommendFormationFilter mRecommendFormationFilter = new MRecommendFormationFilter()
            .filterType(UPDATED_FILTER_TYPE)
            .name(UPDATED_NAME);
        return mRecommendFormationFilter;
    }

    @BeforeEach
    public void initTest() {
        mRecommendFormationFilter = createEntity(em);
    }

    @Test
    @Transactional
    public void createMRecommendFormationFilter() throws Exception {
        int databaseSizeBeforeCreate = mRecommendFormationFilterRepository.findAll().size();

        // Create the MRecommendFormationFilter
        MRecommendFormationFilterDTO mRecommendFormationFilterDTO = mRecommendFormationFilterMapper.toDto(mRecommendFormationFilter);
        restMRecommendFormationFilterMockMvc.perform(post("/api/m-recommend-formation-filters")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mRecommendFormationFilterDTO)))
            .andExpect(status().isCreated());

        // Validate the MRecommendFormationFilter in the database
        List<MRecommendFormationFilter> mRecommendFormationFilterList = mRecommendFormationFilterRepository.findAll();
        assertThat(mRecommendFormationFilterList).hasSize(databaseSizeBeforeCreate + 1);
        MRecommendFormationFilter testMRecommendFormationFilter = mRecommendFormationFilterList.get(mRecommendFormationFilterList.size() - 1);
        assertThat(testMRecommendFormationFilter.getFilterType()).isEqualTo(DEFAULT_FILTER_TYPE);
        assertThat(testMRecommendFormationFilter.getName()).isEqualTo(DEFAULT_NAME);
    }

    @Test
    @Transactional
    public void createMRecommendFormationFilterWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = mRecommendFormationFilterRepository.findAll().size();

        // Create the MRecommendFormationFilter with an existing ID
        mRecommendFormationFilter.setId(1L);
        MRecommendFormationFilterDTO mRecommendFormationFilterDTO = mRecommendFormationFilterMapper.toDto(mRecommendFormationFilter);

        // An entity with an existing ID cannot be created, so this API call must fail
        restMRecommendFormationFilterMockMvc.perform(post("/api/m-recommend-formation-filters")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mRecommendFormationFilterDTO)))
            .andExpect(status().isBadRequest());

        // Validate the MRecommendFormationFilter in the database
        List<MRecommendFormationFilter> mRecommendFormationFilterList = mRecommendFormationFilterRepository.findAll();
        assertThat(mRecommendFormationFilterList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkFilterTypeIsRequired() throws Exception {
        int databaseSizeBeforeTest = mRecommendFormationFilterRepository.findAll().size();
        // set the field null
        mRecommendFormationFilter.setFilterType(null);

        // Create the MRecommendFormationFilter, which fails.
        MRecommendFormationFilterDTO mRecommendFormationFilterDTO = mRecommendFormationFilterMapper.toDto(mRecommendFormationFilter);

        restMRecommendFormationFilterMockMvc.perform(post("/api/m-recommend-formation-filters")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mRecommendFormationFilterDTO)))
            .andExpect(status().isBadRequest());

        List<MRecommendFormationFilter> mRecommendFormationFilterList = mRecommendFormationFilterRepository.findAll();
        assertThat(mRecommendFormationFilterList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllMRecommendFormationFilters() throws Exception {
        // Initialize the database
        mRecommendFormationFilterRepository.saveAndFlush(mRecommendFormationFilter);

        // Get all the mRecommendFormationFilterList
        restMRecommendFormationFilterMockMvc.perform(get("/api/m-recommend-formation-filters?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(mRecommendFormationFilter.getId().intValue())))
            .andExpect(jsonPath("$.[*].filterType").value(hasItem(DEFAULT_FILTER_TYPE)))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME.toString())));
    }
    
    @Test
    @Transactional
    public void getMRecommendFormationFilter() throws Exception {
        // Initialize the database
        mRecommendFormationFilterRepository.saveAndFlush(mRecommendFormationFilter);

        // Get the mRecommendFormationFilter
        restMRecommendFormationFilterMockMvc.perform(get("/api/m-recommend-formation-filters/{id}", mRecommendFormationFilter.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(mRecommendFormationFilter.getId().intValue()))
            .andExpect(jsonPath("$.filterType").value(DEFAULT_FILTER_TYPE))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME.toString()));
    }

    @Test
    @Transactional
    public void getAllMRecommendFormationFiltersByFilterTypeIsEqualToSomething() throws Exception {
        // Initialize the database
        mRecommendFormationFilterRepository.saveAndFlush(mRecommendFormationFilter);

        // Get all the mRecommendFormationFilterList where filterType equals to DEFAULT_FILTER_TYPE
        defaultMRecommendFormationFilterShouldBeFound("filterType.equals=" + DEFAULT_FILTER_TYPE);

        // Get all the mRecommendFormationFilterList where filterType equals to UPDATED_FILTER_TYPE
        defaultMRecommendFormationFilterShouldNotBeFound("filterType.equals=" + UPDATED_FILTER_TYPE);
    }

    @Test
    @Transactional
    public void getAllMRecommendFormationFiltersByFilterTypeIsInShouldWork() throws Exception {
        // Initialize the database
        mRecommendFormationFilterRepository.saveAndFlush(mRecommendFormationFilter);

        // Get all the mRecommendFormationFilterList where filterType in DEFAULT_FILTER_TYPE or UPDATED_FILTER_TYPE
        defaultMRecommendFormationFilterShouldBeFound("filterType.in=" + DEFAULT_FILTER_TYPE + "," + UPDATED_FILTER_TYPE);

        // Get all the mRecommendFormationFilterList where filterType equals to UPDATED_FILTER_TYPE
        defaultMRecommendFormationFilterShouldNotBeFound("filterType.in=" + UPDATED_FILTER_TYPE);
    }

    @Test
    @Transactional
    public void getAllMRecommendFormationFiltersByFilterTypeIsNullOrNotNull() throws Exception {
        // Initialize the database
        mRecommendFormationFilterRepository.saveAndFlush(mRecommendFormationFilter);

        // Get all the mRecommendFormationFilterList where filterType is not null
        defaultMRecommendFormationFilterShouldBeFound("filterType.specified=true");

        // Get all the mRecommendFormationFilterList where filterType is null
        defaultMRecommendFormationFilterShouldNotBeFound("filterType.specified=false");
    }

    @Test
    @Transactional
    public void getAllMRecommendFormationFiltersByFilterTypeIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mRecommendFormationFilterRepository.saveAndFlush(mRecommendFormationFilter);

        // Get all the mRecommendFormationFilterList where filterType greater than or equals to DEFAULT_FILTER_TYPE
        defaultMRecommendFormationFilterShouldBeFound("filterType.greaterOrEqualThan=" + DEFAULT_FILTER_TYPE);

        // Get all the mRecommendFormationFilterList where filterType greater than or equals to UPDATED_FILTER_TYPE
        defaultMRecommendFormationFilterShouldNotBeFound("filterType.greaterOrEqualThan=" + UPDATED_FILTER_TYPE);
    }

    @Test
    @Transactional
    public void getAllMRecommendFormationFiltersByFilterTypeIsLessThanSomething() throws Exception {
        // Initialize the database
        mRecommendFormationFilterRepository.saveAndFlush(mRecommendFormationFilter);

        // Get all the mRecommendFormationFilterList where filterType less than or equals to DEFAULT_FILTER_TYPE
        defaultMRecommendFormationFilterShouldNotBeFound("filterType.lessThan=" + DEFAULT_FILTER_TYPE);

        // Get all the mRecommendFormationFilterList where filterType less than or equals to UPDATED_FILTER_TYPE
        defaultMRecommendFormationFilterShouldBeFound("filterType.lessThan=" + UPDATED_FILTER_TYPE);
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultMRecommendFormationFilterShouldBeFound(String filter) throws Exception {
        restMRecommendFormationFilterMockMvc.perform(get("/api/m-recommend-formation-filters?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(mRecommendFormationFilter.getId().intValue())))
            .andExpect(jsonPath("$.[*].filterType").value(hasItem(DEFAULT_FILTER_TYPE)))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME.toString())));

        // Check, that the count call also returns 1
        restMRecommendFormationFilterMockMvc.perform(get("/api/m-recommend-formation-filters/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultMRecommendFormationFilterShouldNotBeFound(String filter) throws Exception {
        restMRecommendFormationFilterMockMvc.perform(get("/api/m-recommend-formation-filters?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restMRecommendFormationFilterMockMvc.perform(get("/api/m-recommend-formation-filters/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("0"));
    }


    @Test
    @Transactional
    public void getNonExistingMRecommendFormationFilter() throws Exception {
        // Get the mRecommendFormationFilter
        restMRecommendFormationFilterMockMvc.perform(get("/api/m-recommend-formation-filters/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateMRecommendFormationFilter() throws Exception {
        // Initialize the database
        mRecommendFormationFilterRepository.saveAndFlush(mRecommendFormationFilter);

        int databaseSizeBeforeUpdate = mRecommendFormationFilterRepository.findAll().size();

        // Update the mRecommendFormationFilter
        MRecommendFormationFilter updatedMRecommendFormationFilter = mRecommendFormationFilterRepository.findById(mRecommendFormationFilter.getId()).get();
        // Disconnect from session so that the updates on updatedMRecommendFormationFilter are not directly saved in db
        em.detach(updatedMRecommendFormationFilter);
        updatedMRecommendFormationFilter
            .filterType(UPDATED_FILTER_TYPE)
            .name(UPDATED_NAME);
        MRecommendFormationFilterDTO mRecommendFormationFilterDTO = mRecommendFormationFilterMapper.toDto(updatedMRecommendFormationFilter);

        restMRecommendFormationFilterMockMvc.perform(put("/api/m-recommend-formation-filters")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mRecommendFormationFilterDTO)))
            .andExpect(status().isOk());

        // Validate the MRecommendFormationFilter in the database
        List<MRecommendFormationFilter> mRecommendFormationFilterList = mRecommendFormationFilterRepository.findAll();
        assertThat(mRecommendFormationFilterList).hasSize(databaseSizeBeforeUpdate);
        MRecommendFormationFilter testMRecommendFormationFilter = mRecommendFormationFilterList.get(mRecommendFormationFilterList.size() - 1);
        assertThat(testMRecommendFormationFilter.getFilterType()).isEqualTo(UPDATED_FILTER_TYPE);
        assertThat(testMRecommendFormationFilter.getName()).isEqualTo(UPDATED_NAME);
    }

    @Test
    @Transactional
    public void updateNonExistingMRecommendFormationFilter() throws Exception {
        int databaseSizeBeforeUpdate = mRecommendFormationFilterRepository.findAll().size();

        // Create the MRecommendFormationFilter
        MRecommendFormationFilterDTO mRecommendFormationFilterDTO = mRecommendFormationFilterMapper.toDto(mRecommendFormationFilter);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restMRecommendFormationFilterMockMvc.perform(put("/api/m-recommend-formation-filters")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mRecommendFormationFilterDTO)))
            .andExpect(status().isBadRequest());

        // Validate the MRecommendFormationFilter in the database
        List<MRecommendFormationFilter> mRecommendFormationFilterList = mRecommendFormationFilterRepository.findAll();
        assertThat(mRecommendFormationFilterList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteMRecommendFormationFilter() throws Exception {
        // Initialize the database
        mRecommendFormationFilterRepository.saveAndFlush(mRecommendFormationFilter);

        int databaseSizeBeforeDelete = mRecommendFormationFilterRepository.findAll().size();

        // Delete the mRecommendFormationFilter
        restMRecommendFormationFilterMockMvc.perform(delete("/api/m-recommend-formation-filters/{id}", mRecommendFormationFilter.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database is empty
        List<MRecommendFormationFilter> mRecommendFormationFilterList = mRecommendFormationFilterRepository.findAll();
        assertThat(mRecommendFormationFilterList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(MRecommendFormationFilter.class);
        MRecommendFormationFilter mRecommendFormationFilter1 = new MRecommendFormationFilter();
        mRecommendFormationFilter1.setId(1L);
        MRecommendFormationFilter mRecommendFormationFilter2 = new MRecommendFormationFilter();
        mRecommendFormationFilter2.setId(mRecommendFormationFilter1.getId());
        assertThat(mRecommendFormationFilter1).isEqualTo(mRecommendFormationFilter2);
        mRecommendFormationFilter2.setId(2L);
        assertThat(mRecommendFormationFilter1).isNotEqualTo(mRecommendFormationFilter2);
        mRecommendFormationFilter1.setId(null);
        assertThat(mRecommendFormationFilter1).isNotEqualTo(mRecommendFormationFilter2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(MRecommendFormationFilterDTO.class);
        MRecommendFormationFilterDTO mRecommendFormationFilterDTO1 = new MRecommendFormationFilterDTO();
        mRecommendFormationFilterDTO1.setId(1L);
        MRecommendFormationFilterDTO mRecommendFormationFilterDTO2 = new MRecommendFormationFilterDTO();
        assertThat(mRecommendFormationFilterDTO1).isNotEqualTo(mRecommendFormationFilterDTO2);
        mRecommendFormationFilterDTO2.setId(mRecommendFormationFilterDTO1.getId());
        assertThat(mRecommendFormationFilterDTO1).isEqualTo(mRecommendFormationFilterDTO2);
        mRecommendFormationFilterDTO2.setId(2L);
        assertThat(mRecommendFormationFilterDTO1).isNotEqualTo(mRecommendFormationFilterDTO2);
        mRecommendFormationFilterDTO1.setId(null);
        assertThat(mRecommendFormationFilterDTO1).isNotEqualTo(mRecommendFormationFilterDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(mRecommendFormationFilterMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(mRecommendFormationFilterMapper.fromId(null)).isNull();
    }
}
