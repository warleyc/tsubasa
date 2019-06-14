package io.shm.tsubasa.web.rest;

import io.shm.tsubasa.TsubasaApp;
import io.shm.tsubasa.domain.MWeeklyQuestTsubasaPointReward;
import io.shm.tsubasa.repository.MWeeklyQuestTsubasaPointRewardRepository;
import io.shm.tsubasa.service.MWeeklyQuestTsubasaPointRewardService;
import io.shm.tsubasa.service.dto.MWeeklyQuestTsubasaPointRewardDTO;
import io.shm.tsubasa.service.mapper.MWeeklyQuestTsubasaPointRewardMapper;
import io.shm.tsubasa.web.rest.errors.ExceptionTranslator;
import io.shm.tsubasa.service.dto.MWeeklyQuestTsubasaPointRewardCriteria;
import io.shm.tsubasa.service.MWeeklyQuestTsubasaPointRewardQueryService;

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
 * Integration tests for the {@Link MWeeklyQuestTsubasaPointRewardResource} REST controller.
 */
@SpringBootTest(classes = TsubasaApp.class)
public class MWeeklyQuestTsubasaPointRewardResourceIT {

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
    private MWeeklyQuestTsubasaPointRewardRepository mWeeklyQuestTsubasaPointRewardRepository;

    @Autowired
    private MWeeklyQuestTsubasaPointRewardMapper mWeeklyQuestTsubasaPointRewardMapper;

    @Autowired
    private MWeeklyQuestTsubasaPointRewardService mWeeklyQuestTsubasaPointRewardService;

    @Autowired
    private MWeeklyQuestTsubasaPointRewardQueryService mWeeklyQuestTsubasaPointRewardQueryService;

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

    private MockMvc restMWeeklyQuestTsubasaPointRewardMockMvc;

    private MWeeklyQuestTsubasaPointReward mWeeklyQuestTsubasaPointReward;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final MWeeklyQuestTsubasaPointRewardResource mWeeklyQuestTsubasaPointRewardResource = new MWeeklyQuestTsubasaPointRewardResource(mWeeklyQuestTsubasaPointRewardService, mWeeklyQuestTsubasaPointRewardQueryService);
        this.restMWeeklyQuestTsubasaPointRewardMockMvc = MockMvcBuilders.standaloneSetup(mWeeklyQuestTsubasaPointRewardResource)
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
    public static MWeeklyQuestTsubasaPointReward createEntity(EntityManager em) {
        MWeeklyQuestTsubasaPointReward mWeeklyQuestTsubasaPointReward = new MWeeklyQuestTsubasaPointReward()
            .stageId(DEFAULT_STAGE_ID)
            .tsubasaPoint(DEFAULT_TSUBASA_POINT)
            .contentType(DEFAULT_CONTENT_TYPE)
            .contentId(DEFAULT_CONTENT_ID)
            .contentAmount(DEFAULT_CONTENT_AMOUNT);
        return mWeeklyQuestTsubasaPointReward;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static MWeeklyQuestTsubasaPointReward createUpdatedEntity(EntityManager em) {
        MWeeklyQuestTsubasaPointReward mWeeklyQuestTsubasaPointReward = new MWeeklyQuestTsubasaPointReward()
            .stageId(UPDATED_STAGE_ID)
            .tsubasaPoint(UPDATED_TSUBASA_POINT)
            .contentType(UPDATED_CONTENT_TYPE)
            .contentId(UPDATED_CONTENT_ID)
            .contentAmount(UPDATED_CONTENT_AMOUNT);
        return mWeeklyQuestTsubasaPointReward;
    }

    @BeforeEach
    public void initTest() {
        mWeeklyQuestTsubasaPointReward = createEntity(em);
    }

    @Test
    @Transactional
    public void createMWeeklyQuestTsubasaPointReward() throws Exception {
        int databaseSizeBeforeCreate = mWeeklyQuestTsubasaPointRewardRepository.findAll().size();

        // Create the MWeeklyQuestTsubasaPointReward
        MWeeklyQuestTsubasaPointRewardDTO mWeeklyQuestTsubasaPointRewardDTO = mWeeklyQuestTsubasaPointRewardMapper.toDto(mWeeklyQuestTsubasaPointReward);
        restMWeeklyQuestTsubasaPointRewardMockMvc.perform(post("/api/m-weekly-quest-tsubasa-point-rewards")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mWeeklyQuestTsubasaPointRewardDTO)))
            .andExpect(status().isCreated());

        // Validate the MWeeklyQuestTsubasaPointReward in the database
        List<MWeeklyQuestTsubasaPointReward> mWeeklyQuestTsubasaPointRewardList = mWeeklyQuestTsubasaPointRewardRepository.findAll();
        assertThat(mWeeklyQuestTsubasaPointRewardList).hasSize(databaseSizeBeforeCreate + 1);
        MWeeklyQuestTsubasaPointReward testMWeeklyQuestTsubasaPointReward = mWeeklyQuestTsubasaPointRewardList.get(mWeeklyQuestTsubasaPointRewardList.size() - 1);
        assertThat(testMWeeklyQuestTsubasaPointReward.getStageId()).isEqualTo(DEFAULT_STAGE_ID);
        assertThat(testMWeeklyQuestTsubasaPointReward.getTsubasaPoint()).isEqualTo(DEFAULT_TSUBASA_POINT);
        assertThat(testMWeeklyQuestTsubasaPointReward.getContentType()).isEqualTo(DEFAULT_CONTENT_TYPE);
        assertThat(testMWeeklyQuestTsubasaPointReward.getContentId()).isEqualTo(DEFAULT_CONTENT_ID);
        assertThat(testMWeeklyQuestTsubasaPointReward.getContentAmount()).isEqualTo(DEFAULT_CONTENT_AMOUNT);
    }

    @Test
    @Transactional
    public void createMWeeklyQuestTsubasaPointRewardWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = mWeeklyQuestTsubasaPointRewardRepository.findAll().size();

        // Create the MWeeklyQuestTsubasaPointReward with an existing ID
        mWeeklyQuestTsubasaPointReward.setId(1L);
        MWeeklyQuestTsubasaPointRewardDTO mWeeklyQuestTsubasaPointRewardDTO = mWeeklyQuestTsubasaPointRewardMapper.toDto(mWeeklyQuestTsubasaPointReward);

        // An entity with an existing ID cannot be created, so this API call must fail
        restMWeeklyQuestTsubasaPointRewardMockMvc.perform(post("/api/m-weekly-quest-tsubasa-point-rewards")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mWeeklyQuestTsubasaPointRewardDTO)))
            .andExpect(status().isBadRequest());

        // Validate the MWeeklyQuestTsubasaPointReward in the database
        List<MWeeklyQuestTsubasaPointReward> mWeeklyQuestTsubasaPointRewardList = mWeeklyQuestTsubasaPointRewardRepository.findAll();
        assertThat(mWeeklyQuestTsubasaPointRewardList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkStageIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = mWeeklyQuestTsubasaPointRewardRepository.findAll().size();
        // set the field null
        mWeeklyQuestTsubasaPointReward.setStageId(null);

        // Create the MWeeklyQuestTsubasaPointReward, which fails.
        MWeeklyQuestTsubasaPointRewardDTO mWeeklyQuestTsubasaPointRewardDTO = mWeeklyQuestTsubasaPointRewardMapper.toDto(mWeeklyQuestTsubasaPointReward);

        restMWeeklyQuestTsubasaPointRewardMockMvc.perform(post("/api/m-weekly-quest-tsubasa-point-rewards")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mWeeklyQuestTsubasaPointRewardDTO)))
            .andExpect(status().isBadRequest());

        List<MWeeklyQuestTsubasaPointReward> mWeeklyQuestTsubasaPointRewardList = mWeeklyQuestTsubasaPointRewardRepository.findAll();
        assertThat(mWeeklyQuestTsubasaPointRewardList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkTsubasaPointIsRequired() throws Exception {
        int databaseSizeBeforeTest = mWeeklyQuestTsubasaPointRewardRepository.findAll().size();
        // set the field null
        mWeeklyQuestTsubasaPointReward.setTsubasaPoint(null);

        // Create the MWeeklyQuestTsubasaPointReward, which fails.
        MWeeklyQuestTsubasaPointRewardDTO mWeeklyQuestTsubasaPointRewardDTO = mWeeklyQuestTsubasaPointRewardMapper.toDto(mWeeklyQuestTsubasaPointReward);

        restMWeeklyQuestTsubasaPointRewardMockMvc.perform(post("/api/m-weekly-quest-tsubasa-point-rewards")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mWeeklyQuestTsubasaPointRewardDTO)))
            .andExpect(status().isBadRequest());

        List<MWeeklyQuestTsubasaPointReward> mWeeklyQuestTsubasaPointRewardList = mWeeklyQuestTsubasaPointRewardRepository.findAll();
        assertThat(mWeeklyQuestTsubasaPointRewardList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkContentTypeIsRequired() throws Exception {
        int databaseSizeBeforeTest = mWeeklyQuestTsubasaPointRewardRepository.findAll().size();
        // set the field null
        mWeeklyQuestTsubasaPointReward.setContentType(null);

        // Create the MWeeklyQuestTsubasaPointReward, which fails.
        MWeeklyQuestTsubasaPointRewardDTO mWeeklyQuestTsubasaPointRewardDTO = mWeeklyQuestTsubasaPointRewardMapper.toDto(mWeeklyQuestTsubasaPointReward);

        restMWeeklyQuestTsubasaPointRewardMockMvc.perform(post("/api/m-weekly-quest-tsubasa-point-rewards")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mWeeklyQuestTsubasaPointRewardDTO)))
            .andExpect(status().isBadRequest());

        List<MWeeklyQuestTsubasaPointReward> mWeeklyQuestTsubasaPointRewardList = mWeeklyQuestTsubasaPointRewardRepository.findAll();
        assertThat(mWeeklyQuestTsubasaPointRewardList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkContentAmountIsRequired() throws Exception {
        int databaseSizeBeforeTest = mWeeklyQuestTsubasaPointRewardRepository.findAll().size();
        // set the field null
        mWeeklyQuestTsubasaPointReward.setContentAmount(null);

        // Create the MWeeklyQuestTsubasaPointReward, which fails.
        MWeeklyQuestTsubasaPointRewardDTO mWeeklyQuestTsubasaPointRewardDTO = mWeeklyQuestTsubasaPointRewardMapper.toDto(mWeeklyQuestTsubasaPointReward);

        restMWeeklyQuestTsubasaPointRewardMockMvc.perform(post("/api/m-weekly-quest-tsubasa-point-rewards")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mWeeklyQuestTsubasaPointRewardDTO)))
            .andExpect(status().isBadRequest());

        List<MWeeklyQuestTsubasaPointReward> mWeeklyQuestTsubasaPointRewardList = mWeeklyQuestTsubasaPointRewardRepository.findAll();
        assertThat(mWeeklyQuestTsubasaPointRewardList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllMWeeklyQuestTsubasaPointRewards() throws Exception {
        // Initialize the database
        mWeeklyQuestTsubasaPointRewardRepository.saveAndFlush(mWeeklyQuestTsubasaPointReward);

        // Get all the mWeeklyQuestTsubasaPointRewardList
        restMWeeklyQuestTsubasaPointRewardMockMvc.perform(get("/api/m-weekly-quest-tsubasa-point-rewards?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(mWeeklyQuestTsubasaPointReward.getId().intValue())))
            .andExpect(jsonPath("$.[*].stageId").value(hasItem(DEFAULT_STAGE_ID)))
            .andExpect(jsonPath("$.[*].tsubasaPoint").value(hasItem(DEFAULT_TSUBASA_POINT)))
            .andExpect(jsonPath("$.[*].contentType").value(hasItem(DEFAULT_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].contentId").value(hasItem(DEFAULT_CONTENT_ID)))
            .andExpect(jsonPath("$.[*].contentAmount").value(hasItem(DEFAULT_CONTENT_AMOUNT)));
    }
    
    @Test
    @Transactional
    public void getMWeeklyQuestTsubasaPointReward() throws Exception {
        // Initialize the database
        mWeeklyQuestTsubasaPointRewardRepository.saveAndFlush(mWeeklyQuestTsubasaPointReward);

        // Get the mWeeklyQuestTsubasaPointReward
        restMWeeklyQuestTsubasaPointRewardMockMvc.perform(get("/api/m-weekly-quest-tsubasa-point-rewards/{id}", mWeeklyQuestTsubasaPointReward.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(mWeeklyQuestTsubasaPointReward.getId().intValue()))
            .andExpect(jsonPath("$.stageId").value(DEFAULT_STAGE_ID))
            .andExpect(jsonPath("$.tsubasaPoint").value(DEFAULT_TSUBASA_POINT))
            .andExpect(jsonPath("$.contentType").value(DEFAULT_CONTENT_TYPE))
            .andExpect(jsonPath("$.contentId").value(DEFAULT_CONTENT_ID))
            .andExpect(jsonPath("$.contentAmount").value(DEFAULT_CONTENT_AMOUNT));
    }

    @Test
    @Transactional
    public void getAllMWeeklyQuestTsubasaPointRewardsByStageIdIsEqualToSomething() throws Exception {
        // Initialize the database
        mWeeklyQuestTsubasaPointRewardRepository.saveAndFlush(mWeeklyQuestTsubasaPointReward);

        // Get all the mWeeklyQuestTsubasaPointRewardList where stageId equals to DEFAULT_STAGE_ID
        defaultMWeeklyQuestTsubasaPointRewardShouldBeFound("stageId.equals=" + DEFAULT_STAGE_ID);

        // Get all the mWeeklyQuestTsubasaPointRewardList where stageId equals to UPDATED_STAGE_ID
        defaultMWeeklyQuestTsubasaPointRewardShouldNotBeFound("stageId.equals=" + UPDATED_STAGE_ID);
    }

    @Test
    @Transactional
    public void getAllMWeeklyQuestTsubasaPointRewardsByStageIdIsInShouldWork() throws Exception {
        // Initialize the database
        mWeeklyQuestTsubasaPointRewardRepository.saveAndFlush(mWeeklyQuestTsubasaPointReward);

        // Get all the mWeeklyQuestTsubasaPointRewardList where stageId in DEFAULT_STAGE_ID or UPDATED_STAGE_ID
        defaultMWeeklyQuestTsubasaPointRewardShouldBeFound("stageId.in=" + DEFAULT_STAGE_ID + "," + UPDATED_STAGE_ID);

        // Get all the mWeeklyQuestTsubasaPointRewardList where stageId equals to UPDATED_STAGE_ID
        defaultMWeeklyQuestTsubasaPointRewardShouldNotBeFound("stageId.in=" + UPDATED_STAGE_ID);
    }

    @Test
    @Transactional
    public void getAllMWeeklyQuestTsubasaPointRewardsByStageIdIsNullOrNotNull() throws Exception {
        // Initialize the database
        mWeeklyQuestTsubasaPointRewardRepository.saveAndFlush(mWeeklyQuestTsubasaPointReward);

        // Get all the mWeeklyQuestTsubasaPointRewardList where stageId is not null
        defaultMWeeklyQuestTsubasaPointRewardShouldBeFound("stageId.specified=true");

        // Get all the mWeeklyQuestTsubasaPointRewardList where stageId is null
        defaultMWeeklyQuestTsubasaPointRewardShouldNotBeFound("stageId.specified=false");
    }

    @Test
    @Transactional
    public void getAllMWeeklyQuestTsubasaPointRewardsByStageIdIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mWeeklyQuestTsubasaPointRewardRepository.saveAndFlush(mWeeklyQuestTsubasaPointReward);

        // Get all the mWeeklyQuestTsubasaPointRewardList where stageId greater than or equals to DEFAULT_STAGE_ID
        defaultMWeeklyQuestTsubasaPointRewardShouldBeFound("stageId.greaterOrEqualThan=" + DEFAULT_STAGE_ID);

        // Get all the mWeeklyQuestTsubasaPointRewardList where stageId greater than or equals to UPDATED_STAGE_ID
        defaultMWeeklyQuestTsubasaPointRewardShouldNotBeFound("stageId.greaterOrEqualThan=" + UPDATED_STAGE_ID);
    }

    @Test
    @Transactional
    public void getAllMWeeklyQuestTsubasaPointRewardsByStageIdIsLessThanSomething() throws Exception {
        // Initialize the database
        mWeeklyQuestTsubasaPointRewardRepository.saveAndFlush(mWeeklyQuestTsubasaPointReward);

        // Get all the mWeeklyQuestTsubasaPointRewardList where stageId less than or equals to DEFAULT_STAGE_ID
        defaultMWeeklyQuestTsubasaPointRewardShouldNotBeFound("stageId.lessThan=" + DEFAULT_STAGE_ID);

        // Get all the mWeeklyQuestTsubasaPointRewardList where stageId less than or equals to UPDATED_STAGE_ID
        defaultMWeeklyQuestTsubasaPointRewardShouldBeFound("stageId.lessThan=" + UPDATED_STAGE_ID);
    }


    @Test
    @Transactional
    public void getAllMWeeklyQuestTsubasaPointRewardsByTsubasaPointIsEqualToSomething() throws Exception {
        // Initialize the database
        mWeeklyQuestTsubasaPointRewardRepository.saveAndFlush(mWeeklyQuestTsubasaPointReward);

        // Get all the mWeeklyQuestTsubasaPointRewardList where tsubasaPoint equals to DEFAULT_TSUBASA_POINT
        defaultMWeeklyQuestTsubasaPointRewardShouldBeFound("tsubasaPoint.equals=" + DEFAULT_TSUBASA_POINT);

        // Get all the mWeeklyQuestTsubasaPointRewardList where tsubasaPoint equals to UPDATED_TSUBASA_POINT
        defaultMWeeklyQuestTsubasaPointRewardShouldNotBeFound("tsubasaPoint.equals=" + UPDATED_TSUBASA_POINT);
    }

    @Test
    @Transactional
    public void getAllMWeeklyQuestTsubasaPointRewardsByTsubasaPointIsInShouldWork() throws Exception {
        // Initialize the database
        mWeeklyQuestTsubasaPointRewardRepository.saveAndFlush(mWeeklyQuestTsubasaPointReward);

        // Get all the mWeeklyQuestTsubasaPointRewardList where tsubasaPoint in DEFAULT_TSUBASA_POINT or UPDATED_TSUBASA_POINT
        defaultMWeeklyQuestTsubasaPointRewardShouldBeFound("tsubasaPoint.in=" + DEFAULT_TSUBASA_POINT + "," + UPDATED_TSUBASA_POINT);

        // Get all the mWeeklyQuestTsubasaPointRewardList where tsubasaPoint equals to UPDATED_TSUBASA_POINT
        defaultMWeeklyQuestTsubasaPointRewardShouldNotBeFound("tsubasaPoint.in=" + UPDATED_TSUBASA_POINT);
    }

    @Test
    @Transactional
    public void getAllMWeeklyQuestTsubasaPointRewardsByTsubasaPointIsNullOrNotNull() throws Exception {
        // Initialize the database
        mWeeklyQuestTsubasaPointRewardRepository.saveAndFlush(mWeeklyQuestTsubasaPointReward);

        // Get all the mWeeklyQuestTsubasaPointRewardList where tsubasaPoint is not null
        defaultMWeeklyQuestTsubasaPointRewardShouldBeFound("tsubasaPoint.specified=true");

        // Get all the mWeeklyQuestTsubasaPointRewardList where tsubasaPoint is null
        defaultMWeeklyQuestTsubasaPointRewardShouldNotBeFound("tsubasaPoint.specified=false");
    }

    @Test
    @Transactional
    public void getAllMWeeklyQuestTsubasaPointRewardsByTsubasaPointIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mWeeklyQuestTsubasaPointRewardRepository.saveAndFlush(mWeeklyQuestTsubasaPointReward);

        // Get all the mWeeklyQuestTsubasaPointRewardList where tsubasaPoint greater than or equals to DEFAULT_TSUBASA_POINT
        defaultMWeeklyQuestTsubasaPointRewardShouldBeFound("tsubasaPoint.greaterOrEqualThan=" + DEFAULT_TSUBASA_POINT);

        // Get all the mWeeklyQuestTsubasaPointRewardList where tsubasaPoint greater than or equals to UPDATED_TSUBASA_POINT
        defaultMWeeklyQuestTsubasaPointRewardShouldNotBeFound("tsubasaPoint.greaterOrEqualThan=" + UPDATED_TSUBASA_POINT);
    }

    @Test
    @Transactional
    public void getAllMWeeklyQuestTsubasaPointRewardsByTsubasaPointIsLessThanSomething() throws Exception {
        // Initialize the database
        mWeeklyQuestTsubasaPointRewardRepository.saveAndFlush(mWeeklyQuestTsubasaPointReward);

        // Get all the mWeeklyQuestTsubasaPointRewardList where tsubasaPoint less than or equals to DEFAULT_TSUBASA_POINT
        defaultMWeeklyQuestTsubasaPointRewardShouldNotBeFound("tsubasaPoint.lessThan=" + DEFAULT_TSUBASA_POINT);

        // Get all the mWeeklyQuestTsubasaPointRewardList where tsubasaPoint less than or equals to UPDATED_TSUBASA_POINT
        defaultMWeeklyQuestTsubasaPointRewardShouldBeFound("tsubasaPoint.lessThan=" + UPDATED_TSUBASA_POINT);
    }


    @Test
    @Transactional
    public void getAllMWeeklyQuestTsubasaPointRewardsByContentTypeIsEqualToSomething() throws Exception {
        // Initialize the database
        mWeeklyQuestTsubasaPointRewardRepository.saveAndFlush(mWeeklyQuestTsubasaPointReward);

        // Get all the mWeeklyQuestTsubasaPointRewardList where contentType equals to DEFAULT_CONTENT_TYPE
        defaultMWeeklyQuestTsubasaPointRewardShouldBeFound("contentType.equals=" + DEFAULT_CONTENT_TYPE);

        // Get all the mWeeklyQuestTsubasaPointRewardList where contentType equals to UPDATED_CONTENT_TYPE
        defaultMWeeklyQuestTsubasaPointRewardShouldNotBeFound("contentType.equals=" + UPDATED_CONTENT_TYPE);
    }

    @Test
    @Transactional
    public void getAllMWeeklyQuestTsubasaPointRewardsByContentTypeIsInShouldWork() throws Exception {
        // Initialize the database
        mWeeklyQuestTsubasaPointRewardRepository.saveAndFlush(mWeeklyQuestTsubasaPointReward);

        // Get all the mWeeklyQuestTsubasaPointRewardList where contentType in DEFAULT_CONTENT_TYPE or UPDATED_CONTENT_TYPE
        defaultMWeeklyQuestTsubasaPointRewardShouldBeFound("contentType.in=" + DEFAULT_CONTENT_TYPE + "," + UPDATED_CONTENT_TYPE);

        // Get all the mWeeklyQuestTsubasaPointRewardList where contentType equals to UPDATED_CONTENT_TYPE
        defaultMWeeklyQuestTsubasaPointRewardShouldNotBeFound("contentType.in=" + UPDATED_CONTENT_TYPE);
    }

    @Test
    @Transactional
    public void getAllMWeeklyQuestTsubasaPointRewardsByContentTypeIsNullOrNotNull() throws Exception {
        // Initialize the database
        mWeeklyQuestTsubasaPointRewardRepository.saveAndFlush(mWeeklyQuestTsubasaPointReward);

        // Get all the mWeeklyQuestTsubasaPointRewardList where contentType is not null
        defaultMWeeklyQuestTsubasaPointRewardShouldBeFound("contentType.specified=true");

        // Get all the mWeeklyQuestTsubasaPointRewardList where contentType is null
        defaultMWeeklyQuestTsubasaPointRewardShouldNotBeFound("contentType.specified=false");
    }

    @Test
    @Transactional
    public void getAllMWeeklyQuestTsubasaPointRewardsByContentTypeIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mWeeklyQuestTsubasaPointRewardRepository.saveAndFlush(mWeeklyQuestTsubasaPointReward);

        // Get all the mWeeklyQuestTsubasaPointRewardList where contentType greater than or equals to DEFAULT_CONTENT_TYPE
        defaultMWeeklyQuestTsubasaPointRewardShouldBeFound("contentType.greaterOrEqualThan=" + DEFAULT_CONTENT_TYPE);

        // Get all the mWeeklyQuestTsubasaPointRewardList where contentType greater than or equals to UPDATED_CONTENT_TYPE
        defaultMWeeklyQuestTsubasaPointRewardShouldNotBeFound("contentType.greaterOrEqualThan=" + UPDATED_CONTENT_TYPE);
    }

    @Test
    @Transactional
    public void getAllMWeeklyQuestTsubasaPointRewardsByContentTypeIsLessThanSomething() throws Exception {
        // Initialize the database
        mWeeklyQuestTsubasaPointRewardRepository.saveAndFlush(mWeeklyQuestTsubasaPointReward);

        // Get all the mWeeklyQuestTsubasaPointRewardList where contentType less than or equals to DEFAULT_CONTENT_TYPE
        defaultMWeeklyQuestTsubasaPointRewardShouldNotBeFound("contentType.lessThan=" + DEFAULT_CONTENT_TYPE);

        // Get all the mWeeklyQuestTsubasaPointRewardList where contentType less than or equals to UPDATED_CONTENT_TYPE
        defaultMWeeklyQuestTsubasaPointRewardShouldBeFound("contentType.lessThan=" + UPDATED_CONTENT_TYPE);
    }


    @Test
    @Transactional
    public void getAllMWeeklyQuestTsubasaPointRewardsByContentIdIsEqualToSomething() throws Exception {
        // Initialize the database
        mWeeklyQuestTsubasaPointRewardRepository.saveAndFlush(mWeeklyQuestTsubasaPointReward);

        // Get all the mWeeklyQuestTsubasaPointRewardList where contentId equals to DEFAULT_CONTENT_ID
        defaultMWeeklyQuestTsubasaPointRewardShouldBeFound("contentId.equals=" + DEFAULT_CONTENT_ID);

        // Get all the mWeeklyQuestTsubasaPointRewardList where contentId equals to UPDATED_CONTENT_ID
        defaultMWeeklyQuestTsubasaPointRewardShouldNotBeFound("contentId.equals=" + UPDATED_CONTENT_ID);
    }

    @Test
    @Transactional
    public void getAllMWeeklyQuestTsubasaPointRewardsByContentIdIsInShouldWork() throws Exception {
        // Initialize the database
        mWeeklyQuestTsubasaPointRewardRepository.saveAndFlush(mWeeklyQuestTsubasaPointReward);

        // Get all the mWeeklyQuestTsubasaPointRewardList where contentId in DEFAULT_CONTENT_ID or UPDATED_CONTENT_ID
        defaultMWeeklyQuestTsubasaPointRewardShouldBeFound("contentId.in=" + DEFAULT_CONTENT_ID + "," + UPDATED_CONTENT_ID);

        // Get all the mWeeklyQuestTsubasaPointRewardList where contentId equals to UPDATED_CONTENT_ID
        defaultMWeeklyQuestTsubasaPointRewardShouldNotBeFound("contentId.in=" + UPDATED_CONTENT_ID);
    }

    @Test
    @Transactional
    public void getAllMWeeklyQuestTsubasaPointRewardsByContentIdIsNullOrNotNull() throws Exception {
        // Initialize the database
        mWeeklyQuestTsubasaPointRewardRepository.saveAndFlush(mWeeklyQuestTsubasaPointReward);

        // Get all the mWeeklyQuestTsubasaPointRewardList where contentId is not null
        defaultMWeeklyQuestTsubasaPointRewardShouldBeFound("contentId.specified=true");

        // Get all the mWeeklyQuestTsubasaPointRewardList where contentId is null
        defaultMWeeklyQuestTsubasaPointRewardShouldNotBeFound("contentId.specified=false");
    }

    @Test
    @Transactional
    public void getAllMWeeklyQuestTsubasaPointRewardsByContentIdIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mWeeklyQuestTsubasaPointRewardRepository.saveAndFlush(mWeeklyQuestTsubasaPointReward);

        // Get all the mWeeklyQuestTsubasaPointRewardList where contentId greater than or equals to DEFAULT_CONTENT_ID
        defaultMWeeklyQuestTsubasaPointRewardShouldBeFound("contentId.greaterOrEqualThan=" + DEFAULT_CONTENT_ID);

        // Get all the mWeeklyQuestTsubasaPointRewardList where contentId greater than or equals to UPDATED_CONTENT_ID
        defaultMWeeklyQuestTsubasaPointRewardShouldNotBeFound("contentId.greaterOrEqualThan=" + UPDATED_CONTENT_ID);
    }

    @Test
    @Transactional
    public void getAllMWeeklyQuestTsubasaPointRewardsByContentIdIsLessThanSomething() throws Exception {
        // Initialize the database
        mWeeklyQuestTsubasaPointRewardRepository.saveAndFlush(mWeeklyQuestTsubasaPointReward);

        // Get all the mWeeklyQuestTsubasaPointRewardList where contentId less than or equals to DEFAULT_CONTENT_ID
        defaultMWeeklyQuestTsubasaPointRewardShouldNotBeFound("contentId.lessThan=" + DEFAULT_CONTENT_ID);

        // Get all the mWeeklyQuestTsubasaPointRewardList where contentId less than or equals to UPDATED_CONTENT_ID
        defaultMWeeklyQuestTsubasaPointRewardShouldBeFound("contentId.lessThan=" + UPDATED_CONTENT_ID);
    }


    @Test
    @Transactional
    public void getAllMWeeklyQuestTsubasaPointRewardsByContentAmountIsEqualToSomething() throws Exception {
        // Initialize the database
        mWeeklyQuestTsubasaPointRewardRepository.saveAndFlush(mWeeklyQuestTsubasaPointReward);

        // Get all the mWeeklyQuestTsubasaPointRewardList where contentAmount equals to DEFAULT_CONTENT_AMOUNT
        defaultMWeeklyQuestTsubasaPointRewardShouldBeFound("contentAmount.equals=" + DEFAULT_CONTENT_AMOUNT);

        // Get all the mWeeklyQuestTsubasaPointRewardList where contentAmount equals to UPDATED_CONTENT_AMOUNT
        defaultMWeeklyQuestTsubasaPointRewardShouldNotBeFound("contentAmount.equals=" + UPDATED_CONTENT_AMOUNT);
    }

    @Test
    @Transactional
    public void getAllMWeeklyQuestTsubasaPointRewardsByContentAmountIsInShouldWork() throws Exception {
        // Initialize the database
        mWeeklyQuestTsubasaPointRewardRepository.saveAndFlush(mWeeklyQuestTsubasaPointReward);

        // Get all the mWeeklyQuestTsubasaPointRewardList where contentAmount in DEFAULT_CONTENT_AMOUNT or UPDATED_CONTENT_AMOUNT
        defaultMWeeklyQuestTsubasaPointRewardShouldBeFound("contentAmount.in=" + DEFAULT_CONTENT_AMOUNT + "," + UPDATED_CONTENT_AMOUNT);

        // Get all the mWeeklyQuestTsubasaPointRewardList where contentAmount equals to UPDATED_CONTENT_AMOUNT
        defaultMWeeklyQuestTsubasaPointRewardShouldNotBeFound("contentAmount.in=" + UPDATED_CONTENT_AMOUNT);
    }

    @Test
    @Transactional
    public void getAllMWeeklyQuestTsubasaPointRewardsByContentAmountIsNullOrNotNull() throws Exception {
        // Initialize the database
        mWeeklyQuestTsubasaPointRewardRepository.saveAndFlush(mWeeklyQuestTsubasaPointReward);

        // Get all the mWeeklyQuestTsubasaPointRewardList where contentAmount is not null
        defaultMWeeklyQuestTsubasaPointRewardShouldBeFound("contentAmount.specified=true");

        // Get all the mWeeklyQuestTsubasaPointRewardList where contentAmount is null
        defaultMWeeklyQuestTsubasaPointRewardShouldNotBeFound("contentAmount.specified=false");
    }

    @Test
    @Transactional
    public void getAllMWeeklyQuestTsubasaPointRewardsByContentAmountIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mWeeklyQuestTsubasaPointRewardRepository.saveAndFlush(mWeeklyQuestTsubasaPointReward);

        // Get all the mWeeklyQuestTsubasaPointRewardList where contentAmount greater than or equals to DEFAULT_CONTENT_AMOUNT
        defaultMWeeklyQuestTsubasaPointRewardShouldBeFound("contentAmount.greaterOrEqualThan=" + DEFAULT_CONTENT_AMOUNT);

        // Get all the mWeeklyQuestTsubasaPointRewardList where contentAmount greater than or equals to UPDATED_CONTENT_AMOUNT
        defaultMWeeklyQuestTsubasaPointRewardShouldNotBeFound("contentAmount.greaterOrEqualThan=" + UPDATED_CONTENT_AMOUNT);
    }

    @Test
    @Transactional
    public void getAllMWeeklyQuestTsubasaPointRewardsByContentAmountIsLessThanSomething() throws Exception {
        // Initialize the database
        mWeeklyQuestTsubasaPointRewardRepository.saveAndFlush(mWeeklyQuestTsubasaPointReward);

        // Get all the mWeeklyQuestTsubasaPointRewardList where contentAmount less than or equals to DEFAULT_CONTENT_AMOUNT
        defaultMWeeklyQuestTsubasaPointRewardShouldNotBeFound("contentAmount.lessThan=" + DEFAULT_CONTENT_AMOUNT);

        // Get all the mWeeklyQuestTsubasaPointRewardList where contentAmount less than or equals to UPDATED_CONTENT_AMOUNT
        defaultMWeeklyQuestTsubasaPointRewardShouldBeFound("contentAmount.lessThan=" + UPDATED_CONTENT_AMOUNT);
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultMWeeklyQuestTsubasaPointRewardShouldBeFound(String filter) throws Exception {
        restMWeeklyQuestTsubasaPointRewardMockMvc.perform(get("/api/m-weekly-quest-tsubasa-point-rewards?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(mWeeklyQuestTsubasaPointReward.getId().intValue())))
            .andExpect(jsonPath("$.[*].stageId").value(hasItem(DEFAULT_STAGE_ID)))
            .andExpect(jsonPath("$.[*].tsubasaPoint").value(hasItem(DEFAULT_TSUBASA_POINT)))
            .andExpect(jsonPath("$.[*].contentType").value(hasItem(DEFAULT_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].contentId").value(hasItem(DEFAULT_CONTENT_ID)))
            .andExpect(jsonPath("$.[*].contentAmount").value(hasItem(DEFAULT_CONTENT_AMOUNT)));

        // Check, that the count call also returns 1
        restMWeeklyQuestTsubasaPointRewardMockMvc.perform(get("/api/m-weekly-quest-tsubasa-point-rewards/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultMWeeklyQuestTsubasaPointRewardShouldNotBeFound(String filter) throws Exception {
        restMWeeklyQuestTsubasaPointRewardMockMvc.perform(get("/api/m-weekly-quest-tsubasa-point-rewards?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restMWeeklyQuestTsubasaPointRewardMockMvc.perform(get("/api/m-weekly-quest-tsubasa-point-rewards/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("0"));
    }


    @Test
    @Transactional
    public void getNonExistingMWeeklyQuestTsubasaPointReward() throws Exception {
        // Get the mWeeklyQuestTsubasaPointReward
        restMWeeklyQuestTsubasaPointRewardMockMvc.perform(get("/api/m-weekly-quest-tsubasa-point-rewards/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateMWeeklyQuestTsubasaPointReward() throws Exception {
        // Initialize the database
        mWeeklyQuestTsubasaPointRewardRepository.saveAndFlush(mWeeklyQuestTsubasaPointReward);

        int databaseSizeBeforeUpdate = mWeeklyQuestTsubasaPointRewardRepository.findAll().size();

        // Update the mWeeklyQuestTsubasaPointReward
        MWeeklyQuestTsubasaPointReward updatedMWeeklyQuestTsubasaPointReward = mWeeklyQuestTsubasaPointRewardRepository.findById(mWeeklyQuestTsubasaPointReward.getId()).get();
        // Disconnect from session so that the updates on updatedMWeeklyQuestTsubasaPointReward are not directly saved in db
        em.detach(updatedMWeeklyQuestTsubasaPointReward);
        updatedMWeeklyQuestTsubasaPointReward
            .stageId(UPDATED_STAGE_ID)
            .tsubasaPoint(UPDATED_TSUBASA_POINT)
            .contentType(UPDATED_CONTENT_TYPE)
            .contentId(UPDATED_CONTENT_ID)
            .contentAmount(UPDATED_CONTENT_AMOUNT);
        MWeeklyQuestTsubasaPointRewardDTO mWeeklyQuestTsubasaPointRewardDTO = mWeeklyQuestTsubasaPointRewardMapper.toDto(updatedMWeeklyQuestTsubasaPointReward);

        restMWeeklyQuestTsubasaPointRewardMockMvc.perform(put("/api/m-weekly-quest-tsubasa-point-rewards")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mWeeklyQuestTsubasaPointRewardDTO)))
            .andExpect(status().isOk());

        // Validate the MWeeklyQuestTsubasaPointReward in the database
        List<MWeeklyQuestTsubasaPointReward> mWeeklyQuestTsubasaPointRewardList = mWeeklyQuestTsubasaPointRewardRepository.findAll();
        assertThat(mWeeklyQuestTsubasaPointRewardList).hasSize(databaseSizeBeforeUpdate);
        MWeeklyQuestTsubasaPointReward testMWeeklyQuestTsubasaPointReward = mWeeklyQuestTsubasaPointRewardList.get(mWeeklyQuestTsubasaPointRewardList.size() - 1);
        assertThat(testMWeeklyQuestTsubasaPointReward.getStageId()).isEqualTo(UPDATED_STAGE_ID);
        assertThat(testMWeeklyQuestTsubasaPointReward.getTsubasaPoint()).isEqualTo(UPDATED_TSUBASA_POINT);
        assertThat(testMWeeklyQuestTsubasaPointReward.getContentType()).isEqualTo(UPDATED_CONTENT_TYPE);
        assertThat(testMWeeklyQuestTsubasaPointReward.getContentId()).isEqualTo(UPDATED_CONTENT_ID);
        assertThat(testMWeeklyQuestTsubasaPointReward.getContentAmount()).isEqualTo(UPDATED_CONTENT_AMOUNT);
    }

    @Test
    @Transactional
    public void updateNonExistingMWeeklyQuestTsubasaPointReward() throws Exception {
        int databaseSizeBeforeUpdate = mWeeklyQuestTsubasaPointRewardRepository.findAll().size();

        // Create the MWeeklyQuestTsubasaPointReward
        MWeeklyQuestTsubasaPointRewardDTO mWeeklyQuestTsubasaPointRewardDTO = mWeeklyQuestTsubasaPointRewardMapper.toDto(mWeeklyQuestTsubasaPointReward);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restMWeeklyQuestTsubasaPointRewardMockMvc.perform(put("/api/m-weekly-quest-tsubasa-point-rewards")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mWeeklyQuestTsubasaPointRewardDTO)))
            .andExpect(status().isBadRequest());

        // Validate the MWeeklyQuestTsubasaPointReward in the database
        List<MWeeklyQuestTsubasaPointReward> mWeeklyQuestTsubasaPointRewardList = mWeeklyQuestTsubasaPointRewardRepository.findAll();
        assertThat(mWeeklyQuestTsubasaPointRewardList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteMWeeklyQuestTsubasaPointReward() throws Exception {
        // Initialize the database
        mWeeklyQuestTsubasaPointRewardRepository.saveAndFlush(mWeeklyQuestTsubasaPointReward);

        int databaseSizeBeforeDelete = mWeeklyQuestTsubasaPointRewardRepository.findAll().size();

        // Delete the mWeeklyQuestTsubasaPointReward
        restMWeeklyQuestTsubasaPointRewardMockMvc.perform(delete("/api/m-weekly-quest-tsubasa-point-rewards/{id}", mWeeklyQuestTsubasaPointReward.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database is empty
        List<MWeeklyQuestTsubasaPointReward> mWeeklyQuestTsubasaPointRewardList = mWeeklyQuestTsubasaPointRewardRepository.findAll();
        assertThat(mWeeklyQuestTsubasaPointRewardList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(MWeeklyQuestTsubasaPointReward.class);
        MWeeklyQuestTsubasaPointReward mWeeklyQuestTsubasaPointReward1 = new MWeeklyQuestTsubasaPointReward();
        mWeeklyQuestTsubasaPointReward1.setId(1L);
        MWeeklyQuestTsubasaPointReward mWeeklyQuestTsubasaPointReward2 = new MWeeklyQuestTsubasaPointReward();
        mWeeklyQuestTsubasaPointReward2.setId(mWeeklyQuestTsubasaPointReward1.getId());
        assertThat(mWeeklyQuestTsubasaPointReward1).isEqualTo(mWeeklyQuestTsubasaPointReward2);
        mWeeklyQuestTsubasaPointReward2.setId(2L);
        assertThat(mWeeklyQuestTsubasaPointReward1).isNotEqualTo(mWeeklyQuestTsubasaPointReward2);
        mWeeklyQuestTsubasaPointReward1.setId(null);
        assertThat(mWeeklyQuestTsubasaPointReward1).isNotEqualTo(mWeeklyQuestTsubasaPointReward2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(MWeeklyQuestTsubasaPointRewardDTO.class);
        MWeeklyQuestTsubasaPointRewardDTO mWeeklyQuestTsubasaPointRewardDTO1 = new MWeeklyQuestTsubasaPointRewardDTO();
        mWeeklyQuestTsubasaPointRewardDTO1.setId(1L);
        MWeeklyQuestTsubasaPointRewardDTO mWeeklyQuestTsubasaPointRewardDTO2 = new MWeeklyQuestTsubasaPointRewardDTO();
        assertThat(mWeeklyQuestTsubasaPointRewardDTO1).isNotEqualTo(mWeeklyQuestTsubasaPointRewardDTO2);
        mWeeklyQuestTsubasaPointRewardDTO2.setId(mWeeklyQuestTsubasaPointRewardDTO1.getId());
        assertThat(mWeeklyQuestTsubasaPointRewardDTO1).isEqualTo(mWeeklyQuestTsubasaPointRewardDTO2);
        mWeeklyQuestTsubasaPointRewardDTO2.setId(2L);
        assertThat(mWeeklyQuestTsubasaPointRewardDTO1).isNotEqualTo(mWeeklyQuestTsubasaPointRewardDTO2);
        mWeeklyQuestTsubasaPointRewardDTO1.setId(null);
        assertThat(mWeeklyQuestTsubasaPointRewardDTO1).isNotEqualTo(mWeeklyQuestTsubasaPointRewardDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(mWeeklyQuestTsubasaPointRewardMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(mWeeklyQuestTsubasaPointRewardMapper.fromId(null)).isNull();
    }
}
