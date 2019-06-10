package io.shm.tsubasa.web.rest;

import io.shm.tsubasa.TsubasaApp;
import io.shm.tsubasa.domain.MAdventQuestTsubasaPointReward;
import io.shm.tsubasa.repository.MAdventQuestTsubasaPointRewardRepository;
import io.shm.tsubasa.service.MAdventQuestTsubasaPointRewardService;
import io.shm.tsubasa.service.dto.MAdventQuestTsubasaPointRewardDTO;
import io.shm.tsubasa.service.mapper.MAdventQuestTsubasaPointRewardMapper;
import io.shm.tsubasa.web.rest.errors.ExceptionTranslator;
import io.shm.tsubasa.service.dto.MAdventQuestTsubasaPointRewardCriteria;
import io.shm.tsubasa.service.MAdventQuestTsubasaPointRewardQueryService;

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
 * Integration tests for the {@Link MAdventQuestTsubasaPointRewardResource} REST controller.
 */
@SpringBootTest(classes = TsubasaApp.class)
public class MAdventQuestTsubasaPointRewardResourceIT {

    private static final Integer DEFAULT_STAGE_ID = 1;
    private static final Integer UPDATED_STAGE_ID = 2;

    private static final Integer DEFAULT_TSUBASA_POINT = 1;
    private static final Integer UPDATED_TSUBASA_POINT = 2;

    private static final Integer DEFAULT_CONTENT_TYPE = 1;
    private static final Integer UPDATED_CONTENT_TYPE = 2;

    private static final Integer DEFAULT_CONTENT_ID = 1;
    private static final Integer UPDATED_CONTENT_ID = 2;

    private static final Integer DEFAULT_CONTENT_AMOUNT = 1;
    private static final Integer UPDATED_CONTENT_AMOUNT = 2;

    @Autowired
    private MAdventQuestTsubasaPointRewardRepository mAdventQuestTsubasaPointRewardRepository;

    @Autowired
    private MAdventQuestTsubasaPointRewardMapper mAdventQuestTsubasaPointRewardMapper;

    @Autowired
    private MAdventQuestTsubasaPointRewardService mAdventQuestTsubasaPointRewardService;

    @Autowired
    private MAdventQuestTsubasaPointRewardQueryService mAdventQuestTsubasaPointRewardQueryService;

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

    private MockMvc restMAdventQuestTsubasaPointRewardMockMvc;

    private MAdventQuestTsubasaPointReward mAdventQuestTsubasaPointReward;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final MAdventQuestTsubasaPointRewardResource mAdventQuestTsubasaPointRewardResource = new MAdventQuestTsubasaPointRewardResource(mAdventQuestTsubasaPointRewardService, mAdventQuestTsubasaPointRewardQueryService);
        this.restMAdventQuestTsubasaPointRewardMockMvc = MockMvcBuilders.standaloneSetup(mAdventQuestTsubasaPointRewardResource)
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
    public static MAdventQuestTsubasaPointReward createEntity(EntityManager em) {
        MAdventQuestTsubasaPointReward mAdventQuestTsubasaPointReward = new MAdventQuestTsubasaPointReward()
            .stageId(DEFAULT_STAGE_ID)
            .tsubasaPoint(DEFAULT_TSUBASA_POINT)
            .contentType(DEFAULT_CONTENT_TYPE)
            .contentId(DEFAULT_CONTENT_ID)
            .contentAmount(DEFAULT_CONTENT_AMOUNT);
        return mAdventQuestTsubasaPointReward;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static MAdventQuestTsubasaPointReward createUpdatedEntity(EntityManager em) {
        MAdventQuestTsubasaPointReward mAdventQuestTsubasaPointReward = new MAdventQuestTsubasaPointReward()
            .stageId(UPDATED_STAGE_ID)
            .tsubasaPoint(UPDATED_TSUBASA_POINT)
            .contentType(UPDATED_CONTENT_TYPE)
            .contentId(UPDATED_CONTENT_ID)
            .contentAmount(UPDATED_CONTENT_AMOUNT);
        return mAdventQuestTsubasaPointReward;
    }

    @BeforeEach
    public void initTest() {
        mAdventQuestTsubasaPointReward = createEntity(em);
    }

    @Test
    @Transactional
    public void createMAdventQuestTsubasaPointReward() throws Exception {
        int databaseSizeBeforeCreate = mAdventQuestTsubasaPointRewardRepository.findAll().size();

        // Create the MAdventQuestTsubasaPointReward
        MAdventQuestTsubasaPointRewardDTO mAdventQuestTsubasaPointRewardDTO = mAdventQuestTsubasaPointRewardMapper.toDto(mAdventQuestTsubasaPointReward);
        restMAdventQuestTsubasaPointRewardMockMvc.perform(post("/api/m-advent-quest-tsubasa-point-rewards")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mAdventQuestTsubasaPointRewardDTO)))
            .andExpect(status().isCreated());

        // Validate the MAdventQuestTsubasaPointReward in the database
        List<MAdventQuestTsubasaPointReward> mAdventQuestTsubasaPointRewardList = mAdventQuestTsubasaPointRewardRepository.findAll();
        assertThat(mAdventQuestTsubasaPointRewardList).hasSize(databaseSizeBeforeCreate + 1);
        MAdventQuestTsubasaPointReward testMAdventQuestTsubasaPointReward = mAdventQuestTsubasaPointRewardList.get(mAdventQuestTsubasaPointRewardList.size() - 1);
        assertThat(testMAdventQuestTsubasaPointReward.getStageId()).isEqualTo(DEFAULT_STAGE_ID);
        assertThat(testMAdventQuestTsubasaPointReward.getTsubasaPoint()).isEqualTo(DEFAULT_TSUBASA_POINT);
        assertThat(testMAdventQuestTsubasaPointReward.getContentType()).isEqualTo(DEFAULT_CONTENT_TYPE);
        assertThat(testMAdventQuestTsubasaPointReward.getContentId()).isEqualTo(DEFAULT_CONTENT_ID);
        assertThat(testMAdventQuestTsubasaPointReward.getContentAmount()).isEqualTo(DEFAULT_CONTENT_AMOUNT);
    }

    @Test
    @Transactional
    public void createMAdventQuestTsubasaPointRewardWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = mAdventQuestTsubasaPointRewardRepository.findAll().size();

        // Create the MAdventQuestTsubasaPointReward with an existing ID
        mAdventQuestTsubasaPointReward.setId(1L);
        MAdventQuestTsubasaPointRewardDTO mAdventQuestTsubasaPointRewardDTO = mAdventQuestTsubasaPointRewardMapper.toDto(mAdventQuestTsubasaPointReward);

        // An entity with an existing ID cannot be created, so this API call must fail
        restMAdventQuestTsubasaPointRewardMockMvc.perform(post("/api/m-advent-quest-tsubasa-point-rewards")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mAdventQuestTsubasaPointRewardDTO)))
            .andExpect(status().isBadRequest());

        // Validate the MAdventQuestTsubasaPointReward in the database
        List<MAdventQuestTsubasaPointReward> mAdventQuestTsubasaPointRewardList = mAdventQuestTsubasaPointRewardRepository.findAll();
        assertThat(mAdventQuestTsubasaPointRewardList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkStageIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = mAdventQuestTsubasaPointRewardRepository.findAll().size();
        // set the field null
        mAdventQuestTsubasaPointReward.setStageId(null);

        // Create the MAdventQuestTsubasaPointReward, which fails.
        MAdventQuestTsubasaPointRewardDTO mAdventQuestTsubasaPointRewardDTO = mAdventQuestTsubasaPointRewardMapper.toDto(mAdventQuestTsubasaPointReward);

        restMAdventQuestTsubasaPointRewardMockMvc.perform(post("/api/m-advent-quest-tsubasa-point-rewards")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mAdventQuestTsubasaPointRewardDTO)))
            .andExpect(status().isBadRequest());

        List<MAdventQuestTsubasaPointReward> mAdventQuestTsubasaPointRewardList = mAdventQuestTsubasaPointRewardRepository.findAll();
        assertThat(mAdventQuestTsubasaPointRewardList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkTsubasaPointIsRequired() throws Exception {
        int databaseSizeBeforeTest = mAdventQuestTsubasaPointRewardRepository.findAll().size();
        // set the field null
        mAdventQuestTsubasaPointReward.setTsubasaPoint(null);

        // Create the MAdventQuestTsubasaPointReward, which fails.
        MAdventQuestTsubasaPointRewardDTO mAdventQuestTsubasaPointRewardDTO = mAdventQuestTsubasaPointRewardMapper.toDto(mAdventQuestTsubasaPointReward);

        restMAdventQuestTsubasaPointRewardMockMvc.perform(post("/api/m-advent-quest-tsubasa-point-rewards")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mAdventQuestTsubasaPointRewardDTO)))
            .andExpect(status().isBadRequest());

        List<MAdventQuestTsubasaPointReward> mAdventQuestTsubasaPointRewardList = mAdventQuestTsubasaPointRewardRepository.findAll();
        assertThat(mAdventQuestTsubasaPointRewardList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkContentTypeIsRequired() throws Exception {
        int databaseSizeBeforeTest = mAdventQuestTsubasaPointRewardRepository.findAll().size();
        // set the field null
        mAdventQuestTsubasaPointReward.setContentType(null);

        // Create the MAdventQuestTsubasaPointReward, which fails.
        MAdventQuestTsubasaPointRewardDTO mAdventQuestTsubasaPointRewardDTO = mAdventQuestTsubasaPointRewardMapper.toDto(mAdventQuestTsubasaPointReward);

        restMAdventQuestTsubasaPointRewardMockMvc.perform(post("/api/m-advent-quest-tsubasa-point-rewards")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mAdventQuestTsubasaPointRewardDTO)))
            .andExpect(status().isBadRequest());

        List<MAdventQuestTsubasaPointReward> mAdventQuestTsubasaPointRewardList = mAdventQuestTsubasaPointRewardRepository.findAll();
        assertThat(mAdventQuestTsubasaPointRewardList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkContentAmountIsRequired() throws Exception {
        int databaseSizeBeforeTest = mAdventQuestTsubasaPointRewardRepository.findAll().size();
        // set the field null
        mAdventQuestTsubasaPointReward.setContentAmount(null);

        // Create the MAdventQuestTsubasaPointReward, which fails.
        MAdventQuestTsubasaPointRewardDTO mAdventQuestTsubasaPointRewardDTO = mAdventQuestTsubasaPointRewardMapper.toDto(mAdventQuestTsubasaPointReward);

        restMAdventQuestTsubasaPointRewardMockMvc.perform(post("/api/m-advent-quest-tsubasa-point-rewards")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mAdventQuestTsubasaPointRewardDTO)))
            .andExpect(status().isBadRequest());

        List<MAdventQuestTsubasaPointReward> mAdventQuestTsubasaPointRewardList = mAdventQuestTsubasaPointRewardRepository.findAll();
        assertThat(mAdventQuestTsubasaPointRewardList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllMAdventQuestTsubasaPointRewards() throws Exception {
        // Initialize the database
        mAdventQuestTsubasaPointRewardRepository.saveAndFlush(mAdventQuestTsubasaPointReward);

        // Get all the mAdventQuestTsubasaPointRewardList
        restMAdventQuestTsubasaPointRewardMockMvc.perform(get("/api/m-advent-quest-tsubasa-point-rewards?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(mAdventQuestTsubasaPointReward.getId().intValue())))
            .andExpect(jsonPath("$.[*].stageId").value(hasItem(DEFAULT_STAGE_ID)))
            .andExpect(jsonPath("$.[*].tsubasaPoint").value(hasItem(DEFAULT_TSUBASA_POINT)))
            .andExpect(jsonPath("$.[*].contentType").value(hasItem(DEFAULT_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].contentId").value(hasItem(DEFAULT_CONTENT_ID)))
            .andExpect(jsonPath("$.[*].contentAmount").value(hasItem(DEFAULT_CONTENT_AMOUNT)));
    }
    
    @Test
    @Transactional
    public void getMAdventQuestTsubasaPointReward() throws Exception {
        // Initialize the database
        mAdventQuestTsubasaPointRewardRepository.saveAndFlush(mAdventQuestTsubasaPointReward);

        // Get the mAdventQuestTsubasaPointReward
        restMAdventQuestTsubasaPointRewardMockMvc.perform(get("/api/m-advent-quest-tsubasa-point-rewards/{id}", mAdventQuestTsubasaPointReward.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(mAdventQuestTsubasaPointReward.getId().intValue()))
            .andExpect(jsonPath("$.stageId").value(DEFAULT_STAGE_ID))
            .andExpect(jsonPath("$.tsubasaPoint").value(DEFAULT_TSUBASA_POINT))
            .andExpect(jsonPath("$.contentType").value(DEFAULT_CONTENT_TYPE))
            .andExpect(jsonPath("$.contentId").value(DEFAULT_CONTENT_ID))
            .andExpect(jsonPath("$.contentAmount").value(DEFAULT_CONTENT_AMOUNT));
    }

    @Test
    @Transactional
    public void getAllMAdventQuestTsubasaPointRewardsByStageIdIsEqualToSomething() throws Exception {
        // Initialize the database
        mAdventQuestTsubasaPointRewardRepository.saveAndFlush(mAdventQuestTsubasaPointReward);

        // Get all the mAdventQuestTsubasaPointRewardList where stageId equals to DEFAULT_STAGE_ID
        defaultMAdventQuestTsubasaPointRewardShouldBeFound("stageId.equals=" + DEFAULT_STAGE_ID);

        // Get all the mAdventQuestTsubasaPointRewardList where stageId equals to UPDATED_STAGE_ID
        defaultMAdventQuestTsubasaPointRewardShouldNotBeFound("stageId.equals=" + UPDATED_STAGE_ID);
    }

    @Test
    @Transactional
    public void getAllMAdventQuestTsubasaPointRewardsByStageIdIsInShouldWork() throws Exception {
        // Initialize the database
        mAdventQuestTsubasaPointRewardRepository.saveAndFlush(mAdventQuestTsubasaPointReward);

        // Get all the mAdventQuestTsubasaPointRewardList where stageId in DEFAULT_STAGE_ID or UPDATED_STAGE_ID
        defaultMAdventQuestTsubasaPointRewardShouldBeFound("stageId.in=" + DEFAULT_STAGE_ID + "," + UPDATED_STAGE_ID);

        // Get all the mAdventQuestTsubasaPointRewardList where stageId equals to UPDATED_STAGE_ID
        defaultMAdventQuestTsubasaPointRewardShouldNotBeFound("stageId.in=" + UPDATED_STAGE_ID);
    }

    @Test
    @Transactional
    public void getAllMAdventQuestTsubasaPointRewardsByStageIdIsNullOrNotNull() throws Exception {
        // Initialize the database
        mAdventQuestTsubasaPointRewardRepository.saveAndFlush(mAdventQuestTsubasaPointReward);

        // Get all the mAdventQuestTsubasaPointRewardList where stageId is not null
        defaultMAdventQuestTsubasaPointRewardShouldBeFound("stageId.specified=true");

        // Get all the mAdventQuestTsubasaPointRewardList where stageId is null
        defaultMAdventQuestTsubasaPointRewardShouldNotBeFound("stageId.specified=false");
    }

    @Test
    @Transactional
    public void getAllMAdventQuestTsubasaPointRewardsByStageIdIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mAdventQuestTsubasaPointRewardRepository.saveAndFlush(mAdventQuestTsubasaPointReward);

        // Get all the mAdventQuestTsubasaPointRewardList where stageId greater than or equals to DEFAULT_STAGE_ID
        defaultMAdventQuestTsubasaPointRewardShouldBeFound("stageId.greaterOrEqualThan=" + DEFAULT_STAGE_ID);

        // Get all the mAdventQuestTsubasaPointRewardList where stageId greater than or equals to UPDATED_STAGE_ID
        defaultMAdventQuestTsubasaPointRewardShouldNotBeFound("stageId.greaterOrEqualThan=" + UPDATED_STAGE_ID);
    }

    @Test
    @Transactional
    public void getAllMAdventQuestTsubasaPointRewardsByStageIdIsLessThanSomething() throws Exception {
        // Initialize the database
        mAdventQuestTsubasaPointRewardRepository.saveAndFlush(mAdventQuestTsubasaPointReward);

        // Get all the mAdventQuestTsubasaPointRewardList where stageId less than or equals to DEFAULT_STAGE_ID
        defaultMAdventQuestTsubasaPointRewardShouldNotBeFound("stageId.lessThan=" + DEFAULT_STAGE_ID);

        // Get all the mAdventQuestTsubasaPointRewardList where stageId less than or equals to UPDATED_STAGE_ID
        defaultMAdventQuestTsubasaPointRewardShouldBeFound("stageId.lessThan=" + UPDATED_STAGE_ID);
    }


    @Test
    @Transactional
    public void getAllMAdventQuestTsubasaPointRewardsByTsubasaPointIsEqualToSomething() throws Exception {
        // Initialize the database
        mAdventQuestTsubasaPointRewardRepository.saveAndFlush(mAdventQuestTsubasaPointReward);

        // Get all the mAdventQuestTsubasaPointRewardList where tsubasaPoint equals to DEFAULT_TSUBASA_POINT
        defaultMAdventQuestTsubasaPointRewardShouldBeFound("tsubasaPoint.equals=" + DEFAULT_TSUBASA_POINT);

        // Get all the mAdventQuestTsubasaPointRewardList where tsubasaPoint equals to UPDATED_TSUBASA_POINT
        defaultMAdventQuestTsubasaPointRewardShouldNotBeFound("tsubasaPoint.equals=" + UPDATED_TSUBASA_POINT);
    }

    @Test
    @Transactional
    public void getAllMAdventQuestTsubasaPointRewardsByTsubasaPointIsInShouldWork() throws Exception {
        // Initialize the database
        mAdventQuestTsubasaPointRewardRepository.saveAndFlush(mAdventQuestTsubasaPointReward);

        // Get all the mAdventQuestTsubasaPointRewardList where tsubasaPoint in DEFAULT_TSUBASA_POINT or UPDATED_TSUBASA_POINT
        defaultMAdventQuestTsubasaPointRewardShouldBeFound("tsubasaPoint.in=" + DEFAULT_TSUBASA_POINT + "," + UPDATED_TSUBASA_POINT);

        // Get all the mAdventQuestTsubasaPointRewardList where tsubasaPoint equals to UPDATED_TSUBASA_POINT
        defaultMAdventQuestTsubasaPointRewardShouldNotBeFound("tsubasaPoint.in=" + UPDATED_TSUBASA_POINT);
    }

    @Test
    @Transactional
    public void getAllMAdventQuestTsubasaPointRewardsByTsubasaPointIsNullOrNotNull() throws Exception {
        // Initialize the database
        mAdventQuestTsubasaPointRewardRepository.saveAndFlush(mAdventQuestTsubasaPointReward);

        // Get all the mAdventQuestTsubasaPointRewardList where tsubasaPoint is not null
        defaultMAdventQuestTsubasaPointRewardShouldBeFound("tsubasaPoint.specified=true");

        // Get all the mAdventQuestTsubasaPointRewardList where tsubasaPoint is null
        defaultMAdventQuestTsubasaPointRewardShouldNotBeFound("tsubasaPoint.specified=false");
    }

    @Test
    @Transactional
    public void getAllMAdventQuestTsubasaPointRewardsByTsubasaPointIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mAdventQuestTsubasaPointRewardRepository.saveAndFlush(mAdventQuestTsubasaPointReward);

        // Get all the mAdventQuestTsubasaPointRewardList where tsubasaPoint greater than or equals to DEFAULT_TSUBASA_POINT
        defaultMAdventQuestTsubasaPointRewardShouldBeFound("tsubasaPoint.greaterOrEqualThan=" + DEFAULT_TSUBASA_POINT);

        // Get all the mAdventQuestTsubasaPointRewardList where tsubasaPoint greater than or equals to UPDATED_TSUBASA_POINT
        defaultMAdventQuestTsubasaPointRewardShouldNotBeFound("tsubasaPoint.greaterOrEqualThan=" + UPDATED_TSUBASA_POINT);
    }

    @Test
    @Transactional
    public void getAllMAdventQuestTsubasaPointRewardsByTsubasaPointIsLessThanSomething() throws Exception {
        // Initialize the database
        mAdventQuestTsubasaPointRewardRepository.saveAndFlush(mAdventQuestTsubasaPointReward);

        // Get all the mAdventQuestTsubasaPointRewardList where tsubasaPoint less than or equals to DEFAULT_TSUBASA_POINT
        defaultMAdventQuestTsubasaPointRewardShouldNotBeFound("tsubasaPoint.lessThan=" + DEFAULT_TSUBASA_POINT);

        // Get all the mAdventQuestTsubasaPointRewardList where tsubasaPoint less than or equals to UPDATED_TSUBASA_POINT
        defaultMAdventQuestTsubasaPointRewardShouldBeFound("tsubasaPoint.lessThan=" + UPDATED_TSUBASA_POINT);
    }


    @Test
    @Transactional
    public void getAllMAdventQuestTsubasaPointRewardsByContentTypeIsEqualToSomething() throws Exception {
        // Initialize the database
        mAdventQuestTsubasaPointRewardRepository.saveAndFlush(mAdventQuestTsubasaPointReward);

        // Get all the mAdventQuestTsubasaPointRewardList where contentType equals to DEFAULT_CONTENT_TYPE
        defaultMAdventQuestTsubasaPointRewardShouldBeFound("contentType.equals=" + DEFAULT_CONTENT_TYPE);

        // Get all the mAdventQuestTsubasaPointRewardList where contentType equals to UPDATED_CONTENT_TYPE
        defaultMAdventQuestTsubasaPointRewardShouldNotBeFound("contentType.equals=" + UPDATED_CONTENT_TYPE);
    }

    @Test
    @Transactional
    public void getAllMAdventQuestTsubasaPointRewardsByContentTypeIsInShouldWork() throws Exception {
        // Initialize the database
        mAdventQuestTsubasaPointRewardRepository.saveAndFlush(mAdventQuestTsubasaPointReward);

        // Get all the mAdventQuestTsubasaPointRewardList where contentType in DEFAULT_CONTENT_TYPE or UPDATED_CONTENT_TYPE
        defaultMAdventQuestTsubasaPointRewardShouldBeFound("contentType.in=" + DEFAULT_CONTENT_TYPE + "," + UPDATED_CONTENT_TYPE);

        // Get all the mAdventQuestTsubasaPointRewardList where contentType equals to UPDATED_CONTENT_TYPE
        defaultMAdventQuestTsubasaPointRewardShouldNotBeFound("contentType.in=" + UPDATED_CONTENT_TYPE);
    }

    @Test
    @Transactional
    public void getAllMAdventQuestTsubasaPointRewardsByContentTypeIsNullOrNotNull() throws Exception {
        // Initialize the database
        mAdventQuestTsubasaPointRewardRepository.saveAndFlush(mAdventQuestTsubasaPointReward);

        // Get all the mAdventQuestTsubasaPointRewardList where contentType is not null
        defaultMAdventQuestTsubasaPointRewardShouldBeFound("contentType.specified=true");

        // Get all the mAdventQuestTsubasaPointRewardList where contentType is null
        defaultMAdventQuestTsubasaPointRewardShouldNotBeFound("contentType.specified=false");
    }

    @Test
    @Transactional
    public void getAllMAdventQuestTsubasaPointRewardsByContentTypeIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mAdventQuestTsubasaPointRewardRepository.saveAndFlush(mAdventQuestTsubasaPointReward);

        // Get all the mAdventQuestTsubasaPointRewardList where contentType greater than or equals to DEFAULT_CONTENT_TYPE
        defaultMAdventQuestTsubasaPointRewardShouldBeFound("contentType.greaterOrEqualThan=" + DEFAULT_CONTENT_TYPE);

        // Get all the mAdventQuestTsubasaPointRewardList where contentType greater than or equals to UPDATED_CONTENT_TYPE
        defaultMAdventQuestTsubasaPointRewardShouldNotBeFound("contentType.greaterOrEqualThan=" + UPDATED_CONTENT_TYPE);
    }

    @Test
    @Transactional
    public void getAllMAdventQuestTsubasaPointRewardsByContentTypeIsLessThanSomething() throws Exception {
        // Initialize the database
        mAdventQuestTsubasaPointRewardRepository.saveAndFlush(mAdventQuestTsubasaPointReward);

        // Get all the mAdventQuestTsubasaPointRewardList where contentType less than or equals to DEFAULT_CONTENT_TYPE
        defaultMAdventQuestTsubasaPointRewardShouldNotBeFound("contentType.lessThan=" + DEFAULT_CONTENT_TYPE);

        // Get all the mAdventQuestTsubasaPointRewardList where contentType less than or equals to UPDATED_CONTENT_TYPE
        defaultMAdventQuestTsubasaPointRewardShouldBeFound("contentType.lessThan=" + UPDATED_CONTENT_TYPE);
    }


    @Test
    @Transactional
    public void getAllMAdventQuestTsubasaPointRewardsByContentIdIsEqualToSomething() throws Exception {
        // Initialize the database
        mAdventQuestTsubasaPointRewardRepository.saveAndFlush(mAdventQuestTsubasaPointReward);

        // Get all the mAdventQuestTsubasaPointRewardList where contentId equals to DEFAULT_CONTENT_ID
        defaultMAdventQuestTsubasaPointRewardShouldBeFound("contentId.equals=" + DEFAULT_CONTENT_ID);

        // Get all the mAdventQuestTsubasaPointRewardList where contentId equals to UPDATED_CONTENT_ID
        defaultMAdventQuestTsubasaPointRewardShouldNotBeFound("contentId.equals=" + UPDATED_CONTENT_ID);
    }

    @Test
    @Transactional
    public void getAllMAdventQuestTsubasaPointRewardsByContentIdIsInShouldWork() throws Exception {
        // Initialize the database
        mAdventQuestTsubasaPointRewardRepository.saveAndFlush(mAdventQuestTsubasaPointReward);

        // Get all the mAdventQuestTsubasaPointRewardList where contentId in DEFAULT_CONTENT_ID or UPDATED_CONTENT_ID
        defaultMAdventQuestTsubasaPointRewardShouldBeFound("contentId.in=" + DEFAULT_CONTENT_ID + "," + UPDATED_CONTENT_ID);

        // Get all the mAdventQuestTsubasaPointRewardList where contentId equals to UPDATED_CONTENT_ID
        defaultMAdventQuestTsubasaPointRewardShouldNotBeFound("contentId.in=" + UPDATED_CONTENT_ID);
    }

    @Test
    @Transactional
    public void getAllMAdventQuestTsubasaPointRewardsByContentIdIsNullOrNotNull() throws Exception {
        // Initialize the database
        mAdventQuestTsubasaPointRewardRepository.saveAndFlush(mAdventQuestTsubasaPointReward);

        // Get all the mAdventQuestTsubasaPointRewardList where contentId is not null
        defaultMAdventQuestTsubasaPointRewardShouldBeFound("contentId.specified=true");

        // Get all the mAdventQuestTsubasaPointRewardList where contentId is null
        defaultMAdventQuestTsubasaPointRewardShouldNotBeFound("contentId.specified=false");
    }

    @Test
    @Transactional
    public void getAllMAdventQuestTsubasaPointRewardsByContentIdIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mAdventQuestTsubasaPointRewardRepository.saveAndFlush(mAdventQuestTsubasaPointReward);

        // Get all the mAdventQuestTsubasaPointRewardList where contentId greater than or equals to DEFAULT_CONTENT_ID
        defaultMAdventQuestTsubasaPointRewardShouldBeFound("contentId.greaterOrEqualThan=" + DEFAULT_CONTENT_ID);

        // Get all the mAdventQuestTsubasaPointRewardList where contentId greater than or equals to UPDATED_CONTENT_ID
        defaultMAdventQuestTsubasaPointRewardShouldNotBeFound("contentId.greaterOrEqualThan=" + UPDATED_CONTENT_ID);
    }

    @Test
    @Transactional
    public void getAllMAdventQuestTsubasaPointRewardsByContentIdIsLessThanSomething() throws Exception {
        // Initialize the database
        mAdventQuestTsubasaPointRewardRepository.saveAndFlush(mAdventQuestTsubasaPointReward);

        // Get all the mAdventQuestTsubasaPointRewardList where contentId less than or equals to DEFAULT_CONTENT_ID
        defaultMAdventQuestTsubasaPointRewardShouldNotBeFound("contentId.lessThan=" + DEFAULT_CONTENT_ID);

        // Get all the mAdventQuestTsubasaPointRewardList where contentId less than or equals to UPDATED_CONTENT_ID
        defaultMAdventQuestTsubasaPointRewardShouldBeFound("contentId.lessThan=" + UPDATED_CONTENT_ID);
    }


    @Test
    @Transactional
    public void getAllMAdventQuestTsubasaPointRewardsByContentAmountIsEqualToSomething() throws Exception {
        // Initialize the database
        mAdventQuestTsubasaPointRewardRepository.saveAndFlush(mAdventQuestTsubasaPointReward);

        // Get all the mAdventQuestTsubasaPointRewardList where contentAmount equals to DEFAULT_CONTENT_AMOUNT
        defaultMAdventQuestTsubasaPointRewardShouldBeFound("contentAmount.equals=" + DEFAULT_CONTENT_AMOUNT);

        // Get all the mAdventQuestTsubasaPointRewardList where contentAmount equals to UPDATED_CONTENT_AMOUNT
        defaultMAdventQuestTsubasaPointRewardShouldNotBeFound("contentAmount.equals=" + UPDATED_CONTENT_AMOUNT);
    }

    @Test
    @Transactional
    public void getAllMAdventQuestTsubasaPointRewardsByContentAmountIsInShouldWork() throws Exception {
        // Initialize the database
        mAdventQuestTsubasaPointRewardRepository.saveAndFlush(mAdventQuestTsubasaPointReward);

        // Get all the mAdventQuestTsubasaPointRewardList where contentAmount in DEFAULT_CONTENT_AMOUNT or UPDATED_CONTENT_AMOUNT
        defaultMAdventQuestTsubasaPointRewardShouldBeFound("contentAmount.in=" + DEFAULT_CONTENT_AMOUNT + "," + UPDATED_CONTENT_AMOUNT);

        // Get all the mAdventQuestTsubasaPointRewardList where contentAmount equals to UPDATED_CONTENT_AMOUNT
        defaultMAdventQuestTsubasaPointRewardShouldNotBeFound("contentAmount.in=" + UPDATED_CONTENT_AMOUNT);
    }

    @Test
    @Transactional
    public void getAllMAdventQuestTsubasaPointRewardsByContentAmountIsNullOrNotNull() throws Exception {
        // Initialize the database
        mAdventQuestTsubasaPointRewardRepository.saveAndFlush(mAdventQuestTsubasaPointReward);

        // Get all the mAdventQuestTsubasaPointRewardList where contentAmount is not null
        defaultMAdventQuestTsubasaPointRewardShouldBeFound("contentAmount.specified=true");

        // Get all the mAdventQuestTsubasaPointRewardList where contentAmount is null
        defaultMAdventQuestTsubasaPointRewardShouldNotBeFound("contentAmount.specified=false");
    }

    @Test
    @Transactional
    public void getAllMAdventQuestTsubasaPointRewardsByContentAmountIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mAdventQuestTsubasaPointRewardRepository.saveAndFlush(mAdventQuestTsubasaPointReward);

        // Get all the mAdventQuestTsubasaPointRewardList where contentAmount greater than or equals to DEFAULT_CONTENT_AMOUNT
        defaultMAdventQuestTsubasaPointRewardShouldBeFound("contentAmount.greaterOrEqualThan=" + DEFAULT_CONTENT_AMOUNT);

        // Get all the mAdventQuestTsubasaPointRewardList where contentAmount greater than or equals to UPDATED_CONTENT_AMOUNT
        defaultMAdventQuestTsubasaPointRewardShouldNotBeFound("contentAmount.greaterOrEqualThan=" + UPDATED_CONTENT_AMOUNT);
    }

    @Test
    @Transactional
    public void getAllMAdventQuestTsubasaPointRewardsByContentAmountIsLessThanSomething() throws Exception {
        // Initialize the database
        mAdventQuestTsubasaPointRewardRepository.saveAndFlush(mAdventQuestTsubasaPointReward);

        // Get all the mAdventQuestTsubasaPointRewardList where contentAmount less than or equals to DEFAULT_CONTENT_AMOUNT
        defaultMAdventQuestTsubasaPointRewardShouldNotBeFound("contentAmount.lessThan=" + DEFAULT_CONTENT_AMOUNT);

        // Get all the mAdventQuestTsubasaPointRewardList where contentAmount less than or equals to UPDATED_CONTENT_AMOUNT
        defaultMAdventQuestTsubasaPointRewardShouldBeFound("contentAmount.lessThan=" + UPDATED_CONTENT_AMOUNT);
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultMAdventQuestTsubasaPointRewardShouldBeFound(String filter) throws Exception {
        restMAdventQuestTsubasaPointRewardMockMvc.perform(get("/api/m-advent-quest-tsubasa-point-rewards?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(mAdventQuestTsubasaPointReward.getId().intValue())))
            .andExpect(jsonPath("$.[*].stageId").value(hasItem(DEFAULT_STAGE_ID)))
            .andExpect(jsonPath("$.[*].tsubasaPoint").value(hasItem(DEFAULT_TSUBASA_POINT)))
            .andExpect(jsonPath("$.[*].contentType").value(hasItem(DEFAULT_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].contentId").value(hasItem(DEFAULT_CONTENT_ID)))
            .andExpect(jsonPath("$.[*].contentAmount").value(hasItem(DEFAULT_CONTENT_AMOUNT)));

        // Check, that the count call also returns 1
        restMAdventQuestTsubasaPointRewardMockMvc.perform(get("/api/m-advent-quest-tsubasa-point-rewards/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultMAdventQuestTsubasaPointRewardShouldNotBeFound(String filter) throws Exception {
        restMAdventQuestTsubasaPointRewardMockMvc.perform(get("/api/m-advent-quest-tsubasa-point-rewards?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restMAdventQuestTsubasaPointRewardMockMvc.perform(get("/api/m-advent-quest-tsubasa-point-rewards/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("0"));
    }


    @Test
    @Transactional
    public void getNonExistingMAdventQuestTsubasaPointReward() throws Exception {
        // Get the mAdventQuestTsubasaPointReward
        restMAdventQuestTsubasaPointRewardMockMvc.perform(get("/api/m-advent-quest-tsubasa-point-rewards/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateMAdventQuestTsubasaPointReward() throws Exception {
        // Initialize the database
        mAdventQuestTsubasaPointRewardRepository.saveAndFlush(mAdventQuestTsubasaPointReward);

        int databaseSizeBeforeUpdate = mAdventQuestTsubasaPointRewardRepository.findAll().size();

        // Update the mAdventQuestTsubasaPointReward
        MAdventQuestTsubasaPointReward updatedMAdventQuestTsubasaPointReward = mAdventQuestTsubasaPointRewardRepository.findById(mAdventQuestTsubasaPointReward.getId()).get();
        // Disconnect from session so that the updates on updatedMAdventQuestTsubasaPointReward are not directly saved in db
        em.detach(updatedMAdventQuestTsubasaPointReward);
        updatedMAdventQuestTsubasaPointReward
            .stageId(UPDATED_STAGE_ID)
            .tsubasaPoint(UPDATED_TSUBASA_POINT)
            .contentType(UPDATED_CONTENT_TYPE)
            .contentId(UPDATED_CONTENT_ID)
            .contentAmount(UPDATED_CONTENT_AMOUNT);
        MAdventQuestTsubasaPointRewardDTO mAdventQuestTsubasaPointRewardDTO = mAdventQuestTsubasaPointRewardMapper.toDto(updatedMAdventQuestTsubasaPointReward);

        restMAdventQuestTsubasaPointRewardMockMvc.perform(put("/api/m-advent-quest-tsubasa-point-rewards")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mAdventQuestTsubasaPointRewardDTO)))
            .andExpect(status().isOk());

        // Validate the MAdventQuestTsubasaPointReward in the database
        List<MAdventQuestTsubasaPointReward> mAdventQuestTsubasaPointRewardList = mAdventQuestTsubasaPointRewardRepository.findAll();
        assertThat(mAdventQuestTsubasaPointRewardList).hasSize(databaseSizeBeforeUpdate);
        MAdventQuestTsubasaPointReward testMAdventQuestTsubasaPointReward = mAdventQuestTsubasaPointRewardList.get(mAdventQuestTsubasaPointRewardList.size() - 1);
        assertThat(testMAdventQuestTsubasaPointReward.getStageId()).isEqualTo(UPDATED_STAGE_ID);
        assertThat(testMAdventQuestTsubasaPointReward.getTsubasaPoint()).isEqualTo(UPDATED_TSUBASA_POINT);
        assertThat(testMAdventQuestTsubasaPointReward.getContentType()).isEqualTo(UPDATED_CONTENT_TYPE);
        assertThat(testMAdventQuestTsubasaPointReward.getContentId()).isEqualTo(UPDATED_CONTENT_ID);
        assertThat(testMAdventQuestTsubasaPointReward.getContentAmount()).isEqualTo(UPDATED_CONTENT_AMOUNT);
    }

    @Test
    @Transactional
    public void updateNonExistingMAdventQuestTsubasaPointReward() throws Exception {
        int databaseSizeBeforeUpdate = mAdventQuestTsubasaPointRewardRepository.findAll().size();

        // Create the MAdventQuestTsubasaPointReward
        MAdventQuestTsubasaPointRewardDTO mAdventQuestTsubasaPointRewardDTO = mAdventQuestTsubasaPointRewardMapper.toDto(mAdventQuestTsubasaPointReward);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restMAdventQuestTsubasaPointRewardMockMvc.perform(put("/api/m-advent-quest-tsubasa-point-rewards")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mAdventQuestTsubasaPointRewardDTO)))
            .andExpect(status().isBadRequest());

        // Validate the MAdventQuestTsubasaPointReward in the database
        List<MAdventQuestTsubasaPointReward> mAdventQuestTsubasaPointRewardList = mAdventQuestTsubasaPointRewardRepository.findAll();
        assertThat(mAdventQuestTsubasaPointRewardList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteMAdventQuestTsubasaPointReward() throws Exception {
        // Initialize the database
        mAdventQuestTsubasaPointRewardRepository.saveAndFlush(mAdventQuestTsubasaPointReward);

        int databaseSizeBeforeDelete = mAdventQuestTsubasaPointRewardRepository.findAll().size();

        // Delete the mAdventQuestTsubasaPointReward
        restMAdventQuestTsubasaPointRewardMockMvc.perform(delete("/api/m-advent-quest-tsubasa-point-rewards/{id}", mAdventQuestTsubasaPointReward.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database is empty
        List<MAdventQuestTsubasaPointReward> mAdventQuestTsubasaPointRewardList = mAdventQuestTsubasaPointRewardRepository.findAll();
        assertThat(mAdventQuestTsubasaPointRewardList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(MAdventQuestTsubasaPointReward.class);
        MAdventQuestTsubasaPointReward mAdventQuestTsubasaPointReward1 = new MAdventQuestTsubasaPointReward();
        mAdventQuestTsubasaPointReward1.setId(1L);
        MAdventQuestTsubasaPointReward mAdventQuestTsubasaPointReward2 = new MAdventQuestTsubasaPointReward();
        mAdventQuestTsubasaPointReward2.setId(mAdventQuestTsubasaPointReward1.getId());
        assertThat(mAdventQuestTsubasaPointReward1).isEqualTo(mAdventQuestTsubasaPointReward2);
        mAdventQuestTsubasaPointReward2.setId(2L);
        assertThat(mAdventQuestTsubasaPointReward1).isNotEqualTo(mAdventQuestTsubasaPointReward2);
        mAdventQuestTsubasaPointReward1.setId(null);
        assertThat(mAdventQuestTsubasaPointReward1).isNotEqualTo(mAdventQuestTsubasaPointReward2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(MAdventQuestTsubasaPointRewardDTO.class);
        MAdventQuestTsubasaPointRewardDTO mAdventQuestTsubasaPointRewardDTO1 = new MAdventQuestTsubasaPointRewardDTO();
        mAdventQuestTsubasaPointRewardDTO1.setId(1L);
        MAdventQuestTsubasaPointRewardDTO mAdventQuestTsubasaPointRewardDTO2 = new MAdventQuestTsubasaPointRewardDTO();
        assertThat(mAdventQuestTsubasaPointRewardDTO1).isNotEqualTo(mAdventQuestTsubasaPointRewardDTO2);
        mAdventQuestTsubasaPointRewardDTO2.setId(mAdventQuestTsubasaPointRewardDTO1.getId());
        assertThat(mAdventQuestTsubasaPointRewardDTO1).isEqualTo(mAdventQuestTsubasaPointRewardDTO2);
        mAdventQuestTsubasaPointRewardDTO2.setId(2L);
        assertThat(mAdventQuestTsubasaPointRewardDTO1).isNotEqualTo(mAdventQuestTsubasaPointRewardDTO2);
        mAdventQuestTsubasaPointRewardDTO1.setId(null);
        assertThat(mAdventQuestTsubasaPointRewardDTO1).isNotEqualTo(mAdventQuestTsubasaPointRewardDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(mAdventQuestTsubasaPointRewardMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(mAdventQuestTsubasaPointRewardMapper.fromId(null)).isNull();
    }
}
