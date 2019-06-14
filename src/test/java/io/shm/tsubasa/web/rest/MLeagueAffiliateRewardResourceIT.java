package io.shm.tsubasa.web.rest;

import io.shm.tsubasa.TsubasaApp;
import io.shm.tsubasa.domain.MLeagueAffiliateReward;
import io.shm.tsubasa.repository.MLeagueAffiliateRewardRepository;
import io.shm.tsubasa.service.MLeagueAffiliateRewardService;
import io.shm.tsubasa.service.dto.MLeagueAffiliateRewardDTO;
import io.shm.tsubasa.service.mapper.MLeagueAffiliateRewardMapper;
import io.shm.tsubasa.web.rest.errors.ExceptionTranslator;
import io.shm.tsubasa.service.dto.MLeagueAffiliateRewardCriteria;
import io.shm.tsubasa.service.MLeagueAffiliateRewardQueryService;

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
import org.springframework.validation.Validator;

import javax.persistence.EntityManager;
import java.util.List;

import static io.shm.tsubasa.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the {@Link MLeagueAffiliateRewardResource} REST controller.
 */
@SpringBootTest(classes = TsubasaApp.class)
public class MLeagueAffiliateRewardResourceIT {

    private static final Integer DEFAULT_HIERARCHY = 1;
    private static final Integer UPDATED_HIERARCHY = 2;

    private static final Integer DEFAULT_CONTENT_TYPE = 1;
    private static final Integer UPDATED_CONTENT_TYPE = 2;

    private static final Integer DEFAULT_CONTENT_ID = 1;
    private static final Integer UPDATED_CONTENT_ID = 2;

    private static final Integer DEFAULT_CONTENT_AMOUNT = 1;
    private static final Integer UPDATED_CONTENT_AMOUNT = 2;

    @Autowired
    private MLeagueAffiliateRewardRepository mLeagueAffiliateRewardRepository;

    @Autowired
    private MLeagueAffiliateRewardMapper mLeagueAffiliateRewardMapper;

    @Autowired
    private MLeagueAffiliateRewardService mLeagueAffiliateRewardService;

    @Autowired
    private MLeagueAffiliateRewardQueryService mLeagueAffiliateRewardQueryService;

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

    private MockMvc restMLeagueAffiliateRewardMockMvc;

    private MLeagueAffiliateReward mLeagueAffiliateReward;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final MLeagueAffiliateRewardResource mLeagueAffiliateRewardResource = new MLeagueAffiliateRewardResource(mLeagueAffiliateRewardService, mLeagueAffiliateRewardQueryService);
        this.restMLeagueAffiliateRewardMockMvc = MockMvcBuilders.standaloneSetup(mLeagueAffiliateRewardResource)
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
    public static MLeagueAffiliateReward createEntity(EntityManager em) {
        MLeagueAffiliateReward mLeagueAffiliateReward = new MLeagueAffiliateReward()
            .hierarchy(DEFAULT_HIERARCHY)
            .contentType(DEFAULT_CONTENT_TYPE)
            .contentId(DEFAULT_CONTENT_ID)
            .contentAmount(DEFAULT_CONTENT_AMOUNT);
        return mLeagueAffiliateReward;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static MLeagueAffiliateReward createUpdatedEntity(EntityManager em) {
        MLeagueAffiliateReward mLeagueAffiliateReward = new MLeagueAffiliateReward()
            .hierarchy(UPDATED_HIERARCHY)
            .contentType(UPDATED_CONTENT_TYPE)
            .contentId(UPDATED_CONTENT_ID)
            .contentAmount(UPDATED_CONTENT_AMOUNT);
        return mLeagueAffiliateReward;
    }

    @BeforeEach
    public void initTest() {
        mLeagueAffiliateReward = createEntity(em);
    }

    @Test
    @Transactional
    public void createMLeagueAffiliateReward() throws Exception {
        int databaseSizeBeforeCreate = mLeagueAffiliateRewardRepository.findAll().size();

        // Create the MLeagueAffiliateReward
        MLeagueAffiliateRewardDTO mLeagueAffiliateRewardDTO = mLeagueAffiliateRewardMapper.toDto(mLeagueAffiliateReward);
        restMLeagueAffiliateRewardMockMvc.perform(post("/api/m-league-affiliate-rewards")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mLeagueAffiliateRewardDTO)))
            .andExpect(status().isCreated());

        // Validate the MLeagueAffiliateReward in the database
        List<MLeagueAffiliateReward> mLeagueAffiliateRewardList = mLeagueAffiliateRewardRepository.findAll();
        assertThat(mLeagueAffiliateRewardList).hasSize(databaseSizeBeforeCreate + 1);
        MLeagueAffiliateReward testMLeagueAffiliateReward = mLeagueAffiliateRewardList.get(mLeagueAffiliateRewardList.size() - 1);
        assertThat(testMLeagueAffiliateReward.getHierarchy()).isEqualTo(DEFAULT_HIERARCHY);
        assertThat(testMLeagueAffiliateReward.getContentType()).isEqualTo(DEFAULT_CONTENT_TYPE);
        assertThat(testMLeagueAffiliateReward.getContentId()).isEqualTo(DEFAULT_CONTENT_ID);
        assertThat(testMLeagueAffiliateReward.getContentAmount()).isEqualTo(DEFAULT_CONTENT_AMOUNT);
    }

    @Test
    @Transactional
    public void createMLeagueAffiliateRewardWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = mLeagueAffiliateRewardRepository.findAll().size();

        // Create the MLeagueAffiliateReward with an existing ID
        mLeagueAffiliateReward.setId(1L);
        MLeagueAffiliateRewardDTO mLeagueAffiliateRewardDTO = mLeagueAffiliateRewardMapper.toDto(mLeagueAffiliateReward);

        // An entity with an existing ID cannot be created, so this API call must fail
        restMLeagueAffiliateRewardMockMvc.perform(post("/api/m-league-affiliate-rewards")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mLeagueAffiliateRewardDTO)))
            .andExpect(status().isBadRequest());

        // Validate the MLeagueAffiliateReward in the database
        List<MLeagueAffiliateReward> mLeagueAffiliateRewardList = mLeagueAffiliateRewardRepository.findAll();
        assertThat(mLeagueAffiliateRewardList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkHierarchyIsRequired() throws Exception {
        int databaseSizeBeforeTest = mLeagueAffiliateRewardRepository.findAll().size();
        // set the field null
        mLeagueAffiliateReward.setHierarchy(null);

        // Create the MLeagueAffiliateReward, which fails.
        MLeagueAffiliateRewardDTO mLeagueAffiliateRewardDTO = mLeagueAffiliateRewardMapper.toDto(mLeagueAffiliateReward);

        restMLeagueAffiliateRewardMockMvc.perform(post("/api/m-league-affiliate-rewards")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mLeagueAffiliateRewardDTO)))
            .andExpect(status().isBadRequest());

        List<MLeagueAffiliateReward> mLeagueAffiliateRewardList = mLeagueAffiliateRewardRepository.findAll();
        assertThat(mLeagueAffiliateRewardList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkContentTypeIsRequired() throws Exception {
        int databaseSizeBeforeTest = mLeagueAffiliateRewardRepository.findAll().size();
        // set the field null
        mLeagueAffiliateReward.setContentType(null);

        // Create the MLeagueAffiliateReward, which fails.
        MLeagueAffiliateRewardDTO mLeagueAffiliateRewardDTO = mLeagueAffiliateRewardMapper.toDto(mLeagueAffiliateReward);

        restMLeagueAffiliateRewardMockMvc.perform(post("/api/m-league-affiliate-rewards")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mLeagueAffiliateRewardDTO)))
            .andExpect(status().isBadRequest());

        List<MLeagueAffiliateReward> mLeagueAffiliateRewardList = mLeagueAffiliateRewardRepository.findAll();
        assertThat(mLeagueAffiliateRewardList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkContentAmountIsRequired() throws Exception {
        int databaseSizeBeforeTest = mLeagueAffiliateRewardRepository.findAll().size();
        // set the field null
        mLeagueAffiliateReward.setContentAmount(null);

        // Create the MLeagueAffiliateReward, which fails.
        MLeagueAffiliateRewardDTO mLeagueAffiliateRewardDTO = mLeagueAffiliateRewardMapper.toDto(mLeagueAffiliateReward);

        restMLeagueAffiliateRewardMockMvc.perform(post("/api/m-league-affiliate-rewards")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mLeagueAffiliateRewardDTO)))
            .andExpect(status().isBadRequest());

        List<MLeagueAffiliateReward> mLeagueAffiliateRewardList = mLeagueAffiliateRewardRepository.findAll();
        assertThat(mLeagueAffiliateRewardList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllMLeagueAffiliateRewards() throws Exception {
        // Initialize the database
        mLeagueAffiliateRewardRepository.saveAndFlush(mLeagueAffiliateReward);

        // Get all the mLeagueAffiliateRewardList
        restMLeagueAffiliateRewardMockMvc.perform(get("/api/m-league-affiliate-rewards?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(mLeagueAffiliateReward.getId().intValue())))
            .andExpect(jsonPath("$.[*].hierarchy").value(hasItem(DEFAULT_HIERARCHY)))
            .andExpect(jsonPath("$.[*].contentType").value(hasItem(DEFAULT_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].contentId").value(hasItem(DEFAULT_CONTENT_ID)))
            .andExpect(jsonPath("$.[*].contentAmount").value(hasItem(DEFAULT_CONTENT_AMOUNT)));
    }
    
    @Test
    @Transactional
    public void getMLeagueAffiliateReward() throws Exception {
        // Initialize the database
        mLeagueAffiliateRewardRepository.saveAndFlush(mLeagueAffiliateReward);

        // Get the mLeagueAffiliateReward
        restMLeagueAffiliateRewardMockMvc.perform(get("/api/m-league-affiliate-rewards/{id}", mLeagueAffiliateReward.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(mLeagueAffiliateReward.getId().intValue()))
            .andExpect(jsonPath("$.hierarchy").value(DEFAULT_HIERARCHY))
            .andExpect(jsonPath("$.contentType").value(DEFAULT_CONTENT_TYPE))
            .andExpect(jsonPath("$.contentId").value(DEFAULT_CONTENT_ID))
            .andExpect(jsonPath("$.contentAmount").value(DEFAULT_CONTENT_AMOUNT));
    }

    @Test
    @Transactional
    public void getAllMLeagueAffiliateRewardsByHierarchyIsEqualToSomething() throws Exception {
        // Initialize the database
        mLeagueAffiliateRewardRepository.saveAndFlush(mLeagueAffiliateReward);

        // Get all the mLeagueAffiliateRewardList where hierarchy equals to DEFAULT_HIERARCHY
        defaultMLeagueAffiliateRewardShouldBeFound("hierarchy.equals=" + DEFAULT_HIERARCHY);

        // Get all the mLeagueAffiliateRewardList where hierarchy equals to UPDATED_HIERARCHY
        defaultMLeagueAffiliateRewardShouldNotBeFound("hierarchy.equals=" + UPDATED_HIERARCHY);
    }

    @Test
    @Transactional
    public void getAllMLeagueAffiliateRewardsByHierarchyIsInShouldWork() throws Exception {
        // Initialize the database
        mLeagueAffiliateRewardRepository.saveAndFlush(mLeagueAffiliateReward);

        // Get all the mLeagueAffiliateRewardList where hierarchy in DEFAULT_HIERARCHY or UPDATED_HIERARCHY
        defaultMLeagueAffiliateRewardShouldBeFound("hierarchy.in=" + DEFAULT_HIERARCHY + "," + UPDATED_HIERARCHY);

        // Get all the mLeagueAffiliateRewardList where hierarchy equals to UPDATED_HIERARCHY
        defaultMLeagueAffiliateRewardShouldNotBeFound("hierarchy.in=" + UPDATED_HIERARCHY);
    }

    @Test
    @Transactional
    public void getAllMLeagueAffiliateRewardsByHierarchyIsNullOrNotNull() throws Exception {
        // Initialize the database
        mLeagueAffiliateRewardRepository.saveAndFlush(mLeagueAffiliateReward);

        // Get all the mLeagueAffiliateRewardList where hierarchy is not null
        defaultMLeagueAffiliateRewardShouldBeFound("hierarchy.specified=true");

        // Get all the mLeagueAffiliateRewardList where hierarchy is null
        defaultMLeagueAffiliateRewardShouldNotBeFound("hierarchy.specified=false");
    }

    @Test
    @Transactional
    public void getAllMLeagueAffiliateRewardsByHierarchyIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mLeagueAffiliateRewardRepository.saveAndFlush(mLeagueAffiliateReward);

        // Get all the mLeagueAffiliateRewardList where hierarchy greater than or equals to DEFAULT_HIERARCHY
        defaultMLeagueAffiliateRewardShouldBeFound("hierarchy.greaterOrEqualThan=" + DEFAULT_HIERARCHY);

        // Get all the mLeagueAffiliateRewardList where hierarchy greater than or equals to UPDATED_HIERARCHY
        defaultMLeagueAffiliateRewardShouldNotBeFound("hierarchy.greaterOrEqualThan=" + UPDATED_HIERARCHY);
    }

    @Test
    @Transactional
    public void getAllMLeagueAffiliateRewardsByHierarchyIsLessThanSomething() throws Exception {
        // Initialize the database
        mLeagueAffiliateRewardRepository.saveAndFlush(mLeagueAffiliateReward);

        // Get all the mLeagueAffiliateRewardList where hierarchy less than or equals to DEFAULT_HIERARCHY
        defaultMLeagueAffiliateRewardShouldNotBeFound("hierarchy.lessThan=" + DEFAULT_HIERARCHY);

        // Get all the mLeagueAffiliateRewardList where hierarchy less than or equals to UPDATED_HIERARCHY
        defaultMLeagueAffiliateRewardShouldBeFound("hierarchy.lessThan=" + UPDATED_HIERARCHY);
    }


    @Test
    @Transactional
    public void getAllMLeagueAffiliateRewardsByContentTypeIsEqualToSomething() throws Exception {
        // Initialize the database
        mLeagueAffiliateRewardRepository.saveAndFlush(mLeagueAffiliateReward);

        // Get all the mLeagueAffiliateRewardList where contentType equals to DEFAULT_CONTENT_TYPE
        defaultMLeagueAffiliateRewardShouldBeFound("contentType.equals=" + DEFAULT_CONTENT_TYPE);

        // Get all the mLeagueAffiliateRewardList where contentType equals to UPDATED_CONTENT_TYPE
        defaultMLeagueAffiliateRewardShouldNotBeFound("contentType.equals=" + UPDATED_CONTENT_TYPE);
    }

    @Test
    @Transactional
    public void getAllMLeagueAffiliateRewardsByContentTypeIsInShouldWork() throws Exception {
        // Initialize the database
        mLeagueAffiliateRewardRepository.saveAndFlush(mLeagueAffiliateReward);

        // Get all the mLeagueAffiliateRewardList where contentType in DEFAULT_CONTENT_TYPE or UPDATED_CONTENT_TYPE
        defaultMLeagueAffiliateRewardShouldBeFound("contentType.in=" + DEFAULT_CONTENT_TYPE + "," + UPDATED_CONTENT_TYPE);

        // Get all the mLeagueAffiliateRewardList where contentType equals to UPDATED_CONTENT_TYPE
        defaultMLeagueAffiliateRewardShouldNotBeFound("contentType.in=" + UPDATED_CONTENT_TYPE);
    }

    @Test
    @Transactional
    public void getAllMLeagueAffiliateRewardsByContentTypeIsNullOrNotNull() throws Exception {
        // Initialize the database
        mLeagueAffiliateRewardRepository.saveAndFlush(mLeagueAffiliateReward);

        // Get all the mLeagueAffiliateRewardList where contentType is not null
        defaultMLeagueAffiliateRewardShouldBeFound("contentType.specified=true");

        // Get all the mLeagueAffiliateRewardList where contentType is null
        defaultMLeagueAffiliateRewardShouldNotBeFound("contentType.specified=false");
    }

    @Test
    @Transactional
    public void getAllMLeagueAffiliateRewardsByContentTypeIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mLeagueAffiliateRewardRepository.saveAndFlush(mLeagueAffiliateReward);

        // Get all the mLeagueAffiliateRewardList where contentType greater than or equals to DEFAULT_CONTENT_TYPE
        defaultMLeagueAffiliateRewardShouldBeFound("contentType.greaterOrEqualThan=" + DEFAULT_CONTENT_TYPE);

        // Get all the mLeagueAffiliateRewardList where contentType greater than or equals to UPDATED_CONTENT_TYPE
        defaultMLeagueAffiliateRewardShouldNotBeFound("contentType.greaterOrEqualThan=" + UPDATED_CONTENT_TYPE);
    }

    @Test
    @Transactional
    public void getAllMLeagueAffiliateRewardsByContentTypeIsLessThanSomething() throws Exception {
        // Initialize the database
        mLeagueAffiliateRewardRepository.saveAndFlush(mLeagueAffiliateReward);

        // Get all the mLeagueAffiliateRewardList where contentType less than or equals to DEFAULT_CONTENT_TYPE
        defaultMLeagueAffiliateRewardShouldNotBeFound("contentType.lessThan=" + DEFAULT_CONTENT_TYPE);

        // Get all the mLeagueAffiliateRewardList where contentType less than or equals to UPDATED_CONTENT_TYPE
        defaultMLeagueAffiliateRewardShouldBeFound("contentType.lessThan=" + UPDATED_CONTENT_TYPE);
    }


    @Test
    @Transactional
    public void getAllMLeagueAffiliateRewardsByContentIdIsEqualToSomething() throws Exception {
        // Initialize the database
        mLeagueAffiliateRewardRepository.saveAndFlush(mLeagueAffiliateReward);

        // Get all the mLeagueAffiliateRewardList where contentId equals to DEFAULT_CONTENT_ID
        defaultMLeagueAffiliateRewardShouldBeFound("contentId.equals=" + DEFAULT_CONTENT_ID);

        // Get all the mLeagueAffiliateRewardList where contentId equals to UPDATED_CONTENT_ID
        defaultMLeagueAffiliateRewardShouldNotBeFound("contentId.equals=" + UPDATED_CONTENT_ID);
    }

    @Test
    @Transactional
    public void getAllMLeagueAffiliateRewardsByContentIdIsInShouldWork() throws Exception {
        // Initialize the database
        mLeagueAffiliateRewardRepository.saveAndFlush(mLeagueAffiliateReward);

        // Get all the mLeagueAffiliateRewardList where contentId in DEFAULT_CONTENT_ID or UPDATED_CONTENT_ID
        defaultMLeagueAffiliateRewardShouldBeFound("contentId.in=" + DEFAULT_CONTENT_ID + "," + UPDATED_CONTENT_ID);

        // Get all the mLeagueAffiliateRewardList where contentId equals to UPDATED_CONTENT_ID
        defaultMLeagueAffiliateRewardShouldNotBeFound("contentId.in=" + UPDATED_CONTENT_ID);
    }

    @Test
    @Transactional
    public void getAllMLeagueAffiliateRewardsByContentIdIsNullOrNotNull() throws Exception {
        // Initialize the database
        mLeagueAffiliateRewardRepository.saveAndFlush(mLeagueAffiliateReward);

        // Get all the mLeagueAffiliateRewardList where contentId is not null
        defaultMLeagueAffiliateRewardShouldBeFound("contentId.specified=true");

        // Get all the mLeagueAffiliateRewardList where contentId is null
        defaultMLeagueAffiliateRewardShouldNotBeFound("contentId.specified=false");
    }

    @Test
    @Transactional
    public void getAllMLeagueAffiliateRewardsByContentIdIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mLeagueAffiliateRewardRepository.saveAndFlush(mLeagueAffiliateReward);

        // Get all the mLeagueAffiliateRewardList where contentId greater than or equals to DEFAULT_CONTENT_ID
        defaultMLeagueAffiliateRewardShouldBeFound("contentId.greaterOrEqualThan=" + DEFAULT_CONTENT_ID);

        // Get all the mLeagueAffiliateRewardList where contentId greater than or equals to UPDATED_CONTENT_ID
        defaultMLeagueAffiliateRewardShouldNotBeFound("contentId.greaterOrEqualThan=" + UPDATED_CONTENT_ID);
    }

    @Test
    @Transactional
    public void getAllMLeagueAffiliateRewardsByContentIdIsLessThanSomething() throws Exception {
        // Initialize the database
        mLeagueAffiliateRewardRepository.saveAndFlush(mLeagueAffiliateReward);

        // Get all the mLeagueAffiliateRewardList where contentId less than or equals to DEFAULT_CONTENT_ID
        defaultMLeagueAffiliateRewardShouldNotBeFound("contentId.lessThan=" + DEFAULT_CONTENT_ID);

        // Get all the mLeagueAffiliateRewardList where contentId less than or equals to UPDATED_CONTENT_ID
        defaultMLeagueAffiliateRewardShouldBeFound("contentId.lessThan=" + UPDATED_CONTENT_ID);
    }


    @Test
    @Transactional
    public void getAllMLeagueAffiliateRewardsByContentAmountIsEqualToSomething() throws Exception {
        // Initialize the database
        mLeagueAffiliateRewardRepository.saveAndFlush(mLeagueAffiliateReward);

        // Get all the mLeagueAffiliateRewardList where contentAmount equals to DEFAULT_CONTENT_AMOUNT
        defaultMLeagueAffiliateRewardShouldBeFound("contentAmount.equals=" + DEFAULT_CONTENT_AMOUNT);

        // Get all the mLeagueAffiliateRewardList where contentAmount equals to UPDATED_CONTENT_AMOUNT
        defaultMLeagueAffiliateRewardShouldNotBeFound("contentAmount.equals=" + UPDATED_CONTENT_AMOUNT);
    }

    @Test
    @Transactional
    public void getAllMLeagueAffiliateRewardsByContentAmountIsInShouldWork() throws Exception {
        // Initialize the database
        mLeagueAffiliateRewardRepository.saveAndFlush(mLeagueAffiliateReward);

        // Get all the mLeagueAffiliateRewardList where contentAmount in DEFAULT_CONTENT_AMOUNT or UPDATED_CONTENT_AMOUNT
        defaultMLeagueAffiliateRewardShouldBeFound("contentAmount.in=" + DEFAULT_CONTENT_AMOUNT + "," + UPDATED_CONTENT_AMOUNT);

        // Get all the mLeagueAffiliateRewardList where contentAmount equals to UPDATED_CONTENT_AMOUNT
        defaultMLeagueAffiliateRewardShouldNotBeFound("contentAmount.in=" + UPDATED_CONTENT_AMOUNT);
    }

    @Test
    @Transactional
    public void getAllMLeagueAffiliateRewardsByContentAmountIsNullOrNotNull() throws Exception {
        // Initialize the database
        mLeagueAffiliateRewardRepository.saveAndFlush(mLeagueAffiliateReward);

        // Get all the mLeagueAffiliateRewardList where contentAmount is not null
        defaultMLeagueAffiliateRewardShouldBeFound("contentAmount.specified=true");

        // Get all the mLeagueAffiliateRewardList where contentAmount is null
        defaultMLeagueAffiliateRewardShouldNotBeFound("contentAmount.specified=false");
    }

    @Test
    @Transactional
    public void getAllMLeagueAffiliateRewardsByContentAmountIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mLeagueAffiliateRewardRepository.saveAndFlush(mLeagueAffiliateReward);

        // Get all the mLeagueAffiliateRewardList where contentAmount greater than or equals to DEFAULT_CONTENT_AMOUNT
        defaultMLeagueAffiliateRewardShouldBeFound("contentAmount.greaterOrEqualThan=" + DEFAULT_CONTENT_AMOUNT);

        // Get all the mLeagueAffiliateRewardList where contentAmount greater than or equals to UPDATED_CONTENT_AMOUNT
        defaultMLeagueAffiliateRewardShouldNotBeFound("contentAmount.greaterOrEqualThan=" + UPDATED_CONTENT_AMOUNT);
    }

    @Test
    @Transactional
    public void getAllMLeagueAffiliateRewardsByContentAmountIsLessThanSomething() throws Exception {
        // Initialize the database
        mLeagueAffiliateRewardRepository.saveAndFlush(mLeagueAffiliateReward);

        // Get all the mLeagueAffiliateRewardList where contentAmount less than or equals to DEFAULT_CONTENT_AMOUNT
        defaultMLeagueAffiliateRewardShouldNotBeFound("contentAmount.lessThan=" + DEFAULT_CONTENT_AMOUNT);

        // Get all the mLeagueAffiliateRewardList where contentAmount less than or equals to UPDATED_CONTENT_AMOUNT
        defaultMLeagueAffiliateRewardShouldBeFound("contentAmount.lessThan=" + UPDATED_CONTENT_AMOUNT);
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultMLeagueAffiliateRewardShouldBeFound(String filter) throws Exception {
        restMLeagueAffiliateRewardMockMvc.perform(get("/api/m-league-affiliate-rewards?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(mLeagueAffiliateReward.getId().intValue())))
            .andExpect(jsonPath("$.[*].hierarchy").value(hasItem(DEFAULT_HIERARCHY)))
            .andExpect(jsonPath("$.[*].contentType").value(hasItem(DEFAULT_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].contentId").value(hasItem(DEFAULT_CONTENT_ID)))
            .andExpect(jsonPath("$.[*].contentAmount").value(hasItem(DEFAULT_CONTENT_AMOUNT)));

        // Check, that the count call also returns 1
        restMLeagueAffiliateRewardMockMvc.perform(get("/api/m-league-affiliate-rewards/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultMLeagueAffiliateRewardShouldNotBeFound(String filter) throws Exception {
        restMLeagueAffiliateRewardMockMvc.perform(get("/api/m-league-affiliate-rewards?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restMLeagueAffiliateRewardMockMvc.perform(get("/api/m-league-affiliate-rewards/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("0"));
    }


    @Test
    @Transactional
    public void getNonExistingMLeagueAffiliateReward() throws Exception {
        // Get the mLeagueAffiliateReward
        restMLeagueAffiliateRewardMockMvc.perform(get("/api/m-league-affiliate-rewards/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateMLeagueAffiliateReward() throws Exception {
        // Initialize the database
        mLeagueAffiliateRewardRepository.saveAndFlush(mLeagueAffiliateReward);

        int databaseSizeBeforeUpdate = mLeagueAffiliateRewardRepository.findAll().size();

        // Update the mLeagueAffiliateReward
        MLeagueAffiliateReward updatedMLeagueAffiliateReward = mLeagueAffiliateRewardRepository.findById(mLeagueAffiliateReward.getId()).get();
        // Disconnect from session so that the updates on updatedMLeagueAffiliateReward are not directly saved in db
        em.detach(updatedMLeagueAffiliateReward);
        updatedMLeagueAffiliateReward
            .hierarchy(UPDATED_HIERARCHY)
            .contentType(UPDATED_CONTENT_TYPE)
            .contentId(UPDATED_CONTENT_ID)
            .contentAmount(UPDATED_CONTENT_AMOUNT);
        MLeagueAffiliateRewardDTO mLeagueAffiliateRewardDTO = mLeagueAffiliateRewardMapper.toDto(updatedMLeagueAffiliateReward);

        restMLeagueAffiliateRewardMockMvc.perform(put("/api/m-league-affiliate-rewards")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mLeagueAffiliateRewardDTO)))
            .andExpect(status().isOk());

        // Validate the MLeagueAffiliateReward in the database
        List<MLeagueAffiliateReward> mLeagueAffiliateRewardList = mLeagueAffiliateRewardRepository.findAll();
        assertThat(mLeagueAffiliateRewardList).hasSize(databaseSizeBeforeUpdate);
        MLeagueAffiliateReward testMLeagueAffiliateReward = mLeagueAffiliateRewardList.get(mLeagueAffiliateRewardList.size() - 1);
        assertThat(testMLeagueAffiliateReward.getHierarchy()).isEqualTo(UPDATED_HIERARCHY);
        assertThat(testMLeagueAffiliateReward.getContentType()).isEqualTo(UPDATED_CONTENT_TYPE);
        assertThat(testMLeagueAffiliateReward.getContentId()).isEqualTo(UPDATED_CONTENT_ID);
        assertThat(testMLeagueAffiliateReward.getContentAmount()).isEqualTo(UPDATED_CONTENT_AMOUNT);
    }

    @Test
    @Transactional
    public void updateNonExistingMLeagueAffiliateReward() throws Exception {
        int databaseSizeBeforeUpdate = mLeagueAffiliateRewardRepository.findAll().size();

        // Create the MLeagueAffiliateReward
        MLeagueAffiliateRewardDTO mLeagueAffiliateRewardDTO = mLeagueAffiliateRewardMapper.toDto(mLeagueAffiliateReward);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restMLeagueAffiliateRewardMockMvc.perform(put("/api/m-league-affiliate-rewards")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mLeagueAffiliateRewardDTO)))
            .andExpect(status().isBadRequest());

        // Validate the MLeagueAffiliateReward in the database
        List<MLeagueAffiliateReward> mLeagueAffiliateRewardList = mLeagueAffiliateRewardRepository.findAll();
        assertThat(mLeagueAffiliateRewardList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteMLeagueAffiliateReward() throws Exception {
        // Initialize the database
        mLeagueAffiliateRewardRepository.saveAndFlush(mLeagueAffiliateReward);

        int databaseSizeBeforeDelete = mLeagueAffiliateRewardRepository.findAll().size();

        // Delete the mLeagueAffiliateReward
        restMLeagueAffiliateRewardMockMvc.perform(delete("/api/m-league-affiliate-rewards/{id}", mLeagueAffiliateReward.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database is empty
        List<MLeagueAffiliateReward> mLeagueAffiliateRewardList = mLeagueAffiliateRewardRepository.findAll();
        assertThat(mLeagueAffiliateRewardList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(MLeagueAffiliateReward.class);
        MLeagueAffiliateReward mLeagueAffiliateReward1 = new MLeagueAffiliateReward();
        mLeagueAffiliateReward1.setId(1L);
        MLeagueAffiliateReward mLeagueAffiliateReward2 = new MLeagueAffiliateReward();
        mLeagueAffiliateReward2.setId(mLeagueAffiliateReward1.getId());
        assertThat(mLeagueAffiliateReward1).isEqualTo(mLeagueAffiliateReward2);
        mLeagueAffiliateReward2.setId(2L);
        assertThat(mLeagueAffiliateReward1).isNotEqualTo(mLeagueAffiliateReward2);
        mLeagueAffiliateReward1.setId(null);
        assertThat(mLeagueAffiliateReward1).isNotEqualTo(mLeagueAffiliateReward2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(MLeagueAffiliateRewardDTO.class);
        MLeagueAffiliateRewardDTO mLeagueAffiliateRewardDTO1 = new MLeagueAffiliateRewardDTO();
        mLeagueAffiliateRewardDTO1.setId(1L);
        MLeagueAffiliateRewardDTO mLeagueAffiliateRewardDTO2 = new MLeagueAffiliateRewardDTO();
        assertThat(mLeagueAffiliateRewardDTO1).isNotEqualTo(mLeagueAffiliateRewardDTO2);
        mLeagueAffiliateRewardDTO2.setId(mLeagueAffiliateRewardDTO1.getId());
        assertThat(mLeagueAffiliateRewardDTO1).isEqualTo(mLeagueAffiliateRewardDTO2);
        mLeagueAffiliateRewardDTO2.setId(2L);
        assertThat(mLeagueAffiliateRewardDTO1).isNotEqualTo(mLeagueAffiliateRewardDTO2);
        mLeagueAffiliateRewardDTO1.setId(null);
        assertThat(mLeagueAffiliateRewardDTO1).isNotEqualTo(mLeagueAffiliateRewardDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(mLeagueAffiliateRewardMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(mLeagueAffiliateRewardMapper.fromId(null)).isNull();
    }
}
