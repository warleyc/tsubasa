package io.shm.tsubasa.web.rest;

import io.shm.tsubasa.TsubasaApp;
import io.shm.tsubasa.domain.MLuckWeeklyQuestWorld;
import io.shm.tsubasa.domain.MLuckWeeklyQuestStage;
import io.shm.tsubasa.repository.MLuckWeeklyQuestWorldRepository;
import io.shm.tsubasa.service.MLuckWeeklyQuestWorldService;
import io.shm.tsubasa.service.dto.MLuckWeeklyQuestWorldDTO;
import io.shm.tsubasa.service.mapper.MLuckWeeklyQuestWorldMapper;
import io.shm.tsubasa.web.rest.errors.ExceptionTranslator;
import io.shm.tsubasa.service.dto.MLuckWeeklyQuestWorldCriteria;
import io.shm.tsubasa.service.MLuckWeeklyQuestWorldQueryService;

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
 * Integration tests for the {@Link MLuckWeeklyQuestWorldResource} REST controller.
 */
@SpringBootTest(classes = TsubasaApp.class)
public class MLuckWeeklyQuestWorldResourceIT {

    private static final Integer DEFAULT_SET_ID = 1;
    private static final Integer UPDATED_SET_ID = 2;

    private static final Integer DEFAULT_NUMBER = 1;
    private static final Integer UPDATED_NUMBER = 2;

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_IMAGE_PATH = "AAAAAAAAAA";
    private static final String UPDATED_IMAGE_PATH = "BBBBBBBBBB";

    private static final String DEFAULT_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPTION = "BBBBBBBBBB";

    private static final Integer DEFAULT_STAGE_UNLOCK_PATTERN = 1;
    private static final Integer UPDATED_STAGE_UNLOCK_PATTERN = 2;

    private static final String DEFAULT_AROUSAL_BANNER = "AAAAAAAAAA";
    private static final String UPDATED_AROUSAL_BANNER = "BBBBBBBBBB";

    private static final Integer DEFAULT_SPECIAL_REWARD_CONTENT_TYPE = 1;
    private static final Integer UPDATED_SPECIAL_REWARD_CONTENT_TYPE = 2;

    private static final Integer DEFAULT_SPECIAL_REWARD_CONTENT_ID = 1;
    private static final Integer UPDATED_SPECIAL_REWARD_CONTENT_ID = 2;

    private static final Integer DEFAULT_IS_ENABLE_COOP = 1;
    private static final Integer UPDATED_IS_ENABLE_COOP = 2;

    private static final Integer DEFAULT_CLEAR_LIMIT = 1;
    private static final Integer UPDATED_CLEAR_LIMIT = 2;

    @Autowired
    private MLuckWeeklyQuestWorldRepository mLuckWeeklyQuestWorldRepository;

    @Autowired
    private MLuckWeeklyQuestWorldMapper mLuckWeeklyQuestWorldMapper;

    @Autowired
    private MLuckWeeklyQuestWorldService mLuckWeeklyQuestWorldService;

    @Autowired
    private MLuckWeeklyQuestWorldQueryService mLuckWeeklyQuestWorldQueryService;

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

    private MockMvc restMLuckWeeklyQuestWorldMockMvc;

    private MLuckWeeklyQuestWorld mLuckWeeklyQuestWorld;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final MLuckWeeklyQuestWorldResource mLuckWeeklyQuestWorldResource = new MLuckWeeklyQuestWorldResource(mLuckWeeklyQuestWorldService, mLuckWeeklyQuestWorldQueryService);
        this.restMLuckWeeklyQuestWorldMockMvc = MockMvcBuilders.standaloneSetup(mLuckWeeklyQuestWorldResource)
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
    public static MLuckWeeklyQuestWorld createEntity(EntityManager em) {
        MLuckWeeklyQuestWorld mLuckWeeklyQuestWorld = new MLuckWeeklyQuestWorld()
            .setId(DEFAULT_SET_ID)
            .number(DEFAULT_NUMBER)
            .name(DEFAULT_NAME)
            .imagePath(DEFAULT_IMAGE_PATH)
            .description(DEFAULT_DESCRIPTION)
            .stageUnlockPattern(DEFAULT_STAGE_UNLOCK_PATTERN)
            .arousalBanner(DEFAULT_AROUSAL_BANNER)
            .specialRewardContentType(DEFAULT_SPECIAL_REWARD_CONTENT_TYPE)
            .specialRewardContentId(DEFAULT_SPECIAL_REWARD_CONTENT_ID)
            .isEnableCoop(DEFAULT_IS_ENABLE_COOP)
            .clearLimit(DEFAULT_CLEAR_LIMIT);
        return mLuckWeeklyQuestWorld;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static MLuckWeeklyQuestWorld createUpdatedEntity(EntityManager em) {
        MLuckWeeklyQuestWorld mLuckWeeklyQuestWorld = new MLuckWeeklyQuestWorld()
            .setId(UPDATED_SET_ID)
            .number(UPDATED_NUMBER)
            .name(UPDATED_NAME)
            .imagePath(UPDATED_IMAGE_PATH)
            .description(UPDATED_DESCRIPTION)
            .stageUnlockPattern(UPDATED_STAGE_UNLOCK_PATTERN)
            .arousalBanner(UPDATED_AROUSAL_BANNER)
            .specialRewardContentType(UPDATED_SPECIAL_REWARD_CONTENT_TYPE)
            .specialRewardContentId(UPDATED_SPECIAL_REWARD_CONTENT_ID)
            .isEnableCoop(UPDATED_IS_ENABLE_COOP)
            .clearLimit(UPDATED_CLEAR_LIMIT);
        return mLuckWeeklyQuestWorld;
    }

    @BeforeEach
    public void initTest() {
        mLuckWeeklyQuestWorld = createEntity(em);
    }

    @Test
    @Transactional
    public void createMLuckWeeklyQuestWorld() throws Exception {
        int databaseSizeBeforeCreate = mLuckWeeklyQuestWorldRepository.findAll().size();

        // Create the MLuckWeeklyQuestWorld
        MLuckWeeklyQuestWorldDTO mLuckWeeklyQuestWorldDTO = mLuckWeeklyQuestWorldMapper.toDto(mLuckWeeklyQuestWorld);
        restMLuckWeeklyQuestWorldMockMvc.perform(post("/api/m-luck-weekly-quest-worlds")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mLuckWeeklyQuestWorldDTO)))
            .andExpect(status().isCreated());

        // Validate the MLuckWeeklyQuestWorld in the database
        List<MLuckWeeklyQuestWorld> mLuckWeeklyQuestWorldList = mLuckWeeklyQuestWorldRepository.findAll();
        assertThat(mLuckWeeklyQuestWorldList).hasSize(databaseSizeBeforeCreate + 1);
        MLuckWeeklyQuestWorld testMLuckWeeklyQuestWorld = mLuckWeeklyQuestWorldList.get(mLuckWeeklyQuestWorldList.size() - 1);
        assertThat(testMLuckWeeklyQuestWorld.getSetId()).isEqualTo(DEFAULT_SET_ID);
        assertThat(testMLuckWeeklyQuestWorld.getNumber()).isEqualTo(DEFAULT_NUMBER);
        assertThat(testMLuckWeeklyQuestWorld.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testMLuckWeeklyQuestWorld.getImagePath()).isEqualTo(DEFAULT_IMAGE_PATH);
        assertThat(testMLuckWeeklyQuestWorld.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
        assertThat(testMLuckWeeklyQuestWorld.getStageUnlockPattern()).isEqualTo(DEFAULT_STAGE_UNLOCK_PATTERN);
        assertThat(testMLuckWeeklyQuestWorld.getArousalBanner()).isEqualTo(DEFAULT_AROUSAL_BANNER);
        assertThat(testMLuckWeeklyQuestWorld.getSpecialRewardContentType()).isEqualTo(DEFAULT_SPECIAL_REWARD_CONTENT_TYPE);
        assertThat(testMLuckWeeklyQuestWorld.getSpecialRewardContentId()).isEqualTo(DEFAULT_SPECIAL_REWARD_CONTENT_ID);
        assertThat(testMLuckWeeklyQuestWorld.getIsEnableCoop()).isEqualTo(DEFAULT_IS_ENABLE_COOP);
        assertThat(testMLuckWeeklyQuestWorld.getClearLimit()).isEqualTo(DEFAULT_CLEAR_LIMIT);
    }

    @Test
    @Transactional
    public void createMLuckWeeklyQuestWorldWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = mLuckWeeklyQuestWorldRepository.findAll().size();

        // Create the MLuckWeeklyQuestWorld with an existing ID
        mLuckWeeklyQuestWorld.setId(1L);
        MLuckWeeklyQuestWorldDTO mLuckWeeklyQuestWorldDTO = mLuckWeeklyQuestWorldMapper.toDto(mLuckWeeklyQuestWorld);

        // An entity with an existing ID cannot be created, so this API call must fail
        restMLuckWeeklyQuestWorldMockMvc.perform(post("/api/m-luck-weekly-quest-worlds")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mLuckWeeklyQuestWorldDTO)))
            .andExpect(status().isBadRequest());

        // Validate the MLuckWeeklyQuestWorld in the database
        List<MLuckWeeklyQuestWorld> mLuckWeeklyQuestWorldList = mLuckWeeklyQuestWorldRepository.findAll();
        assertThat(mLuckWeeklyQuestWorldList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkSetIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = mLuckWeeklyQuestWorldRepository.findAll().size();
        // set the field null
        mLuckWeeklyQuestWorld.setSetId(null);

        // Create the MLuckWeeklyQuestWorld, which fails.
        MLuckWeeklyQuestWorldDTO mLuckWeeklyQuestWorldDTO = mLuckWeeklyQuestWorldMapper.toDto(mLuckWeeklyQuestWorld);

        restMLuckWeeklyQuestWorldMockMvc.perform(post("/api/m-luck-weekly-quest-worlds")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mLuckWeeklyQuestWorldDTO)))
            .andExpect(status().isBadRequest());

        List<MLuckWeeklyQuestWorld> mLuckWeeklyQuestWorldList = mLuckWeeklyQuestWorldRepository.findAll();
        assertThat(mLuckWeeklyQuestWorldList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkNumberIsRequired() throws Exception {
        int databaseSizeBeforeTest = mLuckWeeklyQuestWorldRepository.findAll().size();
        // set the field null
        mLuckWeeklyQuestWorld.setNumber(null);

        // Create the MLuckWeeklyQuestWorld, which fails.
        MLuckWeeklyQuestWorldDTO mLuckWeeklyQuestWorldDTO = mLuckWeeklyQuestWorldMapper.toDto(mLuckWeeklyQuestWorld);

        restMLuckWeeklyQuestWorldMockMvc.perform(post("/api/m-luck-weekly-quest-worlds")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mLuckWeeklyQuestWorldDTO)))
            .andExpect(status().isBadRequest());

        List<MLuckWeeklyQuestWorld> mLuckWeeklyQuestWorldList = mLuckWeeklyQuestWorldRepository.findAll();
        assertThat(mLuckWeeklyQuestWorldList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkStageUnlockPatternIsRequired() throws Exception {
        int databaseSizeBeforeTest = mLuckWeeklyQuestWorldRepository.findAll().size();
        // set the field null
        mLuckWeeklyQuestWorld.setStageUnlockPattern(null);

        // Create the MLuckWeeklyQuestWorld, which fails.
        MLuckWeeklyQuestWorldDTO mLuckWeeklyQuestWorldDTO = mLuckWeeklyQuestWorldMapper.toDto(mLuckWeeklyQuestWorld);

        restMLuckWeeklyQuestWorldMockMvc.perform(post("/api/m-luck-weekly-quest-worlds")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mLuckWeeklyQuestWorldDTO)))
            .andExpect(status().isBadRequest());

        List<MLuckWeeklyQuestWorld> mLuckWeeklyQuestWorldList = mLuckWeeklyQuestWorldRepository.findAll();
        assertThat(mLuckWeeklyQuestWorldList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkIsEnableCoopIsRequired() throws Exception {
        int databaseSizeBeforeTest = mLuckWeeklyQuestWorldRepository.findAll().size();
        // set the field null
        mLuckWeeklyQuestWorld.setIsEnableCoop(null);

        // Create the MLuckWeeklyQuestWorld, which fails.
        MLuckWeeklyQuestWorldDTO mLuckWeeklyQuestWorldDTO = mLuckWeeklyQuestWorldMapper.toDto(mLuckWeeklyQuestWorld);

        restMLuckWeeklyQuestWorldMockMvc.perform(post("/api/m-luck-weekly-quest-worlds")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mLuckWeeklyQuestWorldDTO)))
            .andExpect(status().isBadRequest());

        List<MLuckWeeklyQuestWorld> mLuckWeeklyQuestWorldList = mLuckWeeklyQuestWorldRepository.findAll();
        assertThat(mLuckWeeklyQuestWorldList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkClearLimitIsRequired() throws Exception {
        int databaseSizeBeforeTest = mLuckWeeklyQuestWorldRepository.findAll().size();
        // set the field null
        mLuckWeeklyQuestWorld.setClearLimit(null);

        // Create the MLuckWeeklyQuestWorld, which fails.
        MLuckWeeklyQuestWorldDTO mLuckWeeklyQuestWorldDTO = mLuckWeeklyQuestWorldMapper.toDto(mLuckWeeklyQuestWorld);

        restMLuckWeeklyQuestWorldMockMvc.perform(post("/api/m-luck-weekly-quest-worlds")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mLuckWeeklyQuestWorldDTO)))
            .andExpect(status().isBadRequest());

        List<MLuckWeeklyQuestWorld> mLuckWeeklyQuestWorldList = mLuckWeeklyQuestWorldRepository.findAll();
        assertThat(mLuckWeeklyQuestWorldList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllMLuckWeeklyQuestWorlds() throws Exception {
        // Initialize the database
        mLuckWeeklyQuestWorldRepository.saveAndFlush(mLuckWeeklyQuestWorld);

        // Get all the mLuckWeeklyQuestWorldList
        restMLuckWeeklyQuestWorldMockMvc.perform(get("/api/m-luck-weekly-quest-worlds?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(mLuckWeeklyQuestWorld.getId().intValue())))
            .andExpect(jsonPath("$.[*].setId").value(hasItem(DEFAULT_SET_ID)))
            .andExpect(jsonPath("$.[*].number").value(hasItem(DEFAULT_NUMBER)))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME.toString())))
            .andExpect(jsonPath("$.[*].imagePath").value(hasItem(DEFAULT_IMAGE_PATH.toString())))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION.toString())))
            .andExpect(jsonPath("$.[*].stageUnlockPattern").value(hasItem(DEFAULT_STAGE_UNLOCK_PATTERN)))
            .andExpect(jsonPath("$.[*].arousalBanner").value(hasItem(DEFAULT_AROUSAL_BANNER.toString())))
            .andExpect(jsonPath("$.[*].specialRewardContentType").value(hasItem(DEFAULT_SPECIAL_REWARD_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].specialRewardContentId").value(hasItem(DEFAULT_SPECIAL_REWARD_CONTENT_ID)))
            .andExpect(jsonPath("$.[*].isEnableCoop").value(hasItem(DEFAULT_IS_ENABLE_COOP)))
            .andExpect(jsonPath("$.[*].clearLimit").value(hasItem(DEFAULT_CLEAR_LIMIT)));
    }
    
    @Test
    @Transactional
    public void getMLuckWeeklyQuestWorld() throws Exception {
        // Initialize the database
        mLuckWeeklyQuestWorldRepository.saveAndFlush(mLuckWeeklyQuestWorld);

        // Get the mLuckWeeklyQuestWorld
        restMLuckWeeklyQuestWorldMockMvc.perform(get("/api/m-luck-weekly-quest-worlds/{id}", mLuckWeeklyQuestWorld.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(mLuckWeeklyQuestWorld.getId().intValue()))
            .andExpect(jsonPath("$.setId").value(DEFAULT_SET_ID))
            .andExpect(jsonPath("$.number").value(DEFAULT_NUMBER))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME.toString()))
            .andExpect(jsonPath("$.imagePath").value(DEFAULT_IMAGE_PATH.toString()))
            .andExpect(jsonPath("$.description").value(DEFAULT_DESCRIPTION.toString()))
            .andExpect(jsonPath("$.stageUnlockPattern").value(DEFAULT_STAGE_UNLOCK_PATTERN))
            .andExpect(jsonPath("$.arousalBanner").value(DEFAULT_AROUSAL_BANNER.toString()))
            .andExpect(jsonPath("$.specialRewardContentType").value(DEFAULT_SPECIAL_REWARD_CONTENT_TYPE))
            .andExpect(jsonPath("$.specialRewardContentId").value(DEFAULT_SPECIAL_REWARD_CONTENT_ID))
            .andExpect(jsonPath("$.isEnableCoop").value(DEFAULT_IS_ENABLE_COOP))
            .andExpect(jsonPath("$.clearLimit").value(DEFAULT_CLEAR_LIMIT));
    }

    @Test
    @Transactional
    public void getAllMLuckWeeklyQuestWorldsBySetIdIsEqualToSomething() throws Exception {
        // Initialize the database
        mLuckWeeklyQuestWorldRepository.saveAndFlush(mLuckWeeklyQuestWorld);

        // Get all the mLuckWeeklyQuestWorldList where setId equals to DEFAULT_SET_ID
        defaultMLuckWeeklyQuestWorldShouldBeFound("setId.equals=" + DEFAULT_SET_ID);

        // Get all the mLuckWeeklyQuestWorldList where setId equals to UPDATED_SET_ID
        defaultMLuckWeeklyQuestWorldShouldNotBeFound("setId.equals=" + UPDATED_SET_ID);
    }

    @Test
    @Transactional
    public void getAllMLuckWeeklyQuestWorldsBySetIdIsInShouldWork() throws Exception {
        // Initialize the database
        mLuckWeeklyQuestWorldRepository.saveAndFlush(mLuckWeeklyQuestWorld);

        // Get all the mLuckWeeklyQuestWorldList where setId in DEFAULT_SET_ID or UPDATED_SET_ID
        defaultMLuckWeeklyQuestWorldShouldBeFound("setId.in=" + DEFAULT_SET_ID + "," + UPDATED_SET_ID);

        // Get all the mLuckWeeklyQuestWorldList where setId equals to UPDATED_SET_ID
        defaultMLuckWeeklyQuestWorldShouldNotBeFound("setId.in=" + UPDATED_SET_ID);
    }

    @Test
    @Transactional
    public void getAllMLuckWeeklyQuestWorldsBySetIdIsNullOrNotNull() throws Exception {
        // Initialize the database
        mLuckWeeklyQuestWorldRepository.saveAndFlush(mLuckWeeklyQuestWorld);

        // Get all the mLuckWeeklyQuestWorldList where setId is not null
        defaultMLuckWeeklyQuestWorldShouldBeFound("setId.specified=true");

        // Get all the mLuckWeeklyQuestWorldList where setId is null
        defaultMLuckWeeklyQuestWorldShouldNotBeFound("setId.specified=false");
    }

    @Test
    @Transactional
    public void getAllMLuckWeeklyQuestWorldsBySetIdIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mLuckWeeklyQuestWorldRepository.saveAndFlush(mLuckWeeklyQuestWorld);

        // Get all the mLuckWeeklyQuestWorldList where setId greater than or equals to DEFAULT_SET_ID
        defaultMLuckWeeklyQuestWorldShouldBeFound("setId.greaterOrEqualThan=" + DEFAULT_SET_ID);

        // Get all the mLuckWeeklyQuestWorldList where setId greater than or equals to UPDATED_SET_ID
        defaultMLuckWeeklyQuestWorldShouldNotBeFound("setId.greaterOrEqualThan=" + UPDATED_SET_ID);
    }

    @Test
    @Transactional
    public void getAllMLuckWeeklyQuestWorldsBySetIdIsLessThanSomething() throws Exception {
        // Initialize the database
        mLuckWeeklyQuestWorldRepository.saveAndFlush(mLuckWeeklyQuestWorld);

        // Get all the mLuckWeeklyQuestWorldList where setId less than or equals to DEFAULT_SET_ID
        defaultMLuckWeeklyQuestWorldShouldNotBeFound("setId.lessThan=" + DEFAULT_SET_ID);

        // Get all the mLuckWeeklyQuestWorldList where setId less than or equals to UPDATED_SET_ID
        defaultMLuckWeeklyQuestWorldShouldBeFound("setId.lessThan=" + UPDATED_SET_ID);
    }


    @Test
    @Transactional
    public void getAllMLuckWeeklyQuestWorldsByNumberIsEqualToSomething() throws Exception {
        // Initialize the database
        mLuckWeeklyQuestWorldRepository.saveAndFlush(mLuckWeeklyQuestWorld);

        // Get all the mLuckWeeklyQuestWorldList where number equals to DEFAULT_NUMBER
        defaultMLuckWeeklyQuestWorldShouldBeFound("number.equals=" + DEFAULT_NUMBER);

        // Get all the mLuckWeeklyQuestWorldList where number equals to UPDATED_NUMBER
        defaultMLuckWeeklyQuestWorldShouldNotBeFound("number.equals=" + UPDATED_NUMBER);
    }

    @Test
    @Transactional
    public void getAllMLuckWeeklyQuestWorldsByNumberIsInShouldWork() throws Exception {
        // Initialize the database
        mLuckWeeklyQuestWorldRepository.saveAndFlush(mLuckWeeklyQuestWorld);

        // Get all the mLuckWeeklyQuestWorldList where number in DEFAULT_NUMBER or UPDATED_NUMBER
        defaultMLuckWeeklyQuestWorldShouldBeFound("number.in=" + DEFAULT_NUMBER + "," + UPDATED_NUMBER);

        // Get all the mLuckWeeklyQuestWorldList where number equals to UPDATED_NUMBER
        defaultMLuckWeeklyQuestWorldShouldNotBeFound("number.in=" + UPDATED_NUMBER);
    }

    @Test
    @Transactional
    public void getAllMLuckWeeklyQuestWorldsByNumberIsNullOrNotNull() throws Exception {
        // Initialize the database
        mLuckWeeklyQuestWorldRepository.saveAndFlush(mLuckWeeklyQuestWorld);

        // Get all the mLuckWeeklyQuestWorldList where number is not null
        defaultMLuckWeeklyQuestWorldShouldBeFound("number.specified=true");

        // Get all the mLuckWeeklyQuestWorldList where number is null
        defaultMLuckWeeklyQuestWorldShouldNotBeFound("number.specified=false");
    }

    @Test
    @Transactional
    public void getAllMLuckWeeklyQuestWorldsByNumberIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mLuckWeeklyQuestWorldRepository.saveAndFlush(mLuckWeeklyQuestWorld);

        // Get all the mLuckWeeklyQuestWorldList where number greater than or equals to DEFAULT_NUMBER
        defaultMLuckWeeklyQuestWorldShouldBeFound("number.greaterOrEqualThan=" + DEFAULT_NUMBER);

        // Get all the mLuckWeeklyQuestWorldList where number greater than or equals to UPDATED_NUMBER
        defaultMLuckWeeklyQuestWorldShouldNotBeFound("number.greaterOrEqualThan=" + UPDATED_NUMBER);
    }

    @Test
    @Transactional
    public void getAllMLuckWeeklyQuestWorldsByNumberIsLessThanSomething() throws Exception {
        // Initialize the database
        mLuckWeeklyQuestWorldRepository.saveAndFlush(mLuckWeeklyQuestWorld);

        // Get all the mLuckWeeklyQuestWorldList where number less than or equals to DEFAULT_NUMBER
        defaultMLuckWeeklyQuestWorldShouldNotBeFound("number.lessThan=" + DEFAULT_NUMBER);

        // Get all the mLuckWeeklyQuestWorldList where number less than or equals to UPDATED_NUMBER
        defaultMLuckWeeklyQuestWorldShouldBeFound("number.lessThan=" + UPDATED_NUMBER);
    }


    @Test
    @Transactional
    public void getAllMLuckWeeklyQuestWorldsByStageUnlockPatternIsEqualToSomething() throws Exception {
        // Initialize the database
        mLuckWeeklyQuestWorldRepository.saveAndFlush(mLuckWeeklyQuestWorld);

        // Get all the mLuckWeeklyQuestWorldList where stageUnlockPattern equals to DEFAULT_STAGE_UNLOCK_PATTERN
        defaultMLuckWeeklyQuestWorldShouldBeFound("stageUnlockPattern.equals=" + DEFAULT_STAGE_UNLOCK_PATTERN);

        // Get all the mLuckWeeklyQuestWorldList where stageUnlockPattern equals to UPDATED_STAGE_UNLOCK_PATTERN
        defaultMLuckWeeklyQuestWorldShouldNotBeFound("stageUnlockPattern.equals=" + UPDATED_STAGE_UNLOCK_PATTERN);
    }

    @Test
    @Transactional
    public void getAllMLuckWeeklyQuestWorldsByStageUnlockPatternIsInShouldWork() throws Exception {
        // Initialize the database
        mLuckWeeklyQuestWorldRepository.saveAndFlush(mLuckWeeklyQuestWorld);

        // Get all the mLuckWeeklyQuestWorldList where stageUnlockPattern in DEFAULT_STAGE_UNLOCK_PATTERN or UPDATED_STAGE_UNLOCK_PATTERN
        defaultMLuckWeeklyQuestWorldShouldBeFound("stageUnlockPattern.in=" + DEFAULT_STAGE_UNLOCK_PATTERN + "," + UPDATED_STAGE_UNLOCK_PATTERN);

        // Get all the mLuckWeeklyQuestWorldList where stageUnlockPattern equals to UPDATED_STAGE_UNLOCK_PATTERN
        defaultMLuckWeeklyQuestWorldShouldNotBeFound("stageUnlockPattern.in=" + UPDATED_STAGE_UNLOCK_PATTERN);
    }

    @Test
    @Transactional
    public void getAllMLuckWeeklyQuestWorldsByStageUnlockPatternIsNullOrNotNull() throws Exception {
        // Initialize the database
        mLuckWeeklyQuestWorldRepository.saveAndFlush(mLuckWeeklyQuestWorld);

        // Get all the mLuckWeeklyQuestWorldList where stageUnlockPattern is not null
        defaultMLuckWeeklyQuestWorldShouldBeFound("stageUnlockPattern.specified=true");

        // Get all the mLuckWeeklyQuestWorldList where stageUnlockPattern is null
        defaultMLuckWeeklyQuestWorldShouldNotBeFound("stageUnlockPattern.specified=false");
    }

    @Test
    @Transactional
    public void getAllMLuckWeeklyQuestWorldsByStageUnlockPatternIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mLuckWeeklyQuestWorldRepository.saveAndFlush(mLuckWeeklyQuestWorld);

        // Get all the mLuckWeeklyQuestWorldList where stageUnlockPattern greater than or equals to DEFAULT_STAGE_UNLOCK_PATTERN
        defaultMLuckWeeklyQuestWorldShouldBeFound("stageUnlockPattern.greaterOrEqualThan=" + DEFAULT_STAGE_UNLOCK_PATTERN);

        // Get all the mLuckWeeklyQuestWorldList where stageUnlockPattern greater than or equals to UPDATED_STAGE_UNLOCK_PATTERN
        defaultMLuckWeeklyQuestWorldShouldNotBeFound("stageUnlockPattern.greaterOrEqualThan=" + UPDATED_STAGE_UNLOCK_PATTERN);
    }

    @Test
    @Transactional
    public void getAllMLuckWeeklyQuestWorldsByStageUnlockPatternIsLessThanSomething() throws Exception {
        // Initialize the database
        mLuckWeeklyQuestWorldRepository.saveAndFlush(mLuckWeeklyQuestWorld);

        // Get all the mLuckWeeklyQuestWorldList where stageUnlockPattern less than or equals to DEFAULT_STAGE_UNLOCK_PATTERN
        defaultMLuckWeeklyQuestWorldShouldNotBeFound("stageUnlockPattern.lessThan=" + DEFAULT_STAGE_UNLOCK_PATTERN);

        // Get all the mLuckWeeklyQuestWorldList where stageUnlockPattern less than or equals to UPDATED_STAGE_UNLOCK_PATTERN
        defaultMLuckWeeklyQuestWorldShouldBeFound("stageUnlockPattern.lessThan=" + UPDATED_STAGE_UNLOCK_PATTERN);
    }


    @Test
    @Transactional
    public void getAllMLuckWeeklyQuestWorldsBySpecialRewardContentTypeIsEqualToSomething() throws Exception {
        // Initialize the database
        mLuckWeeklyQuestWorldRepository.saveAndFlush(mLuckWeeklyQuestWorld);

        // Get all the mLuckWeeklyQuestWorldList where specialRewardContentType equals to DEFAULT_SPECIAL_REWARD_CONTENT_TYPE
        defaultMLuckWeeklyQuestWorldShouldBeFound("specialRewardContentType.equals=" + DEFAULT_SPECIAL_REWARD_CONTENT_TYPE);

        // Get all the mLuckWeeklyQuestWorldList where specialRewardContentType equals to UPDATED_SPECIAL_REWARD_CONTENT_TYPE
        defaultMLuckWeeklyQuestWorldShouldNotBeFound("specialRewardContentType.equals=" + UPDATED_SPECIAL_REWARD_CONTENT_TYPE);
    }

    @Test
    @Transactional
    public void getAllMLuckWeeklyQuestWorldsBySpecialRewardContentTypeIsInShouldWork() throws Exception {
        // Initialize the database
        mLuckWeeklyQuestWorldRepository.saveAndFlush(mLuckWeeklyQuestWorld);

        // Get all the mLuckWeeklyQuestWorldList where specialRewardContentType in DEFAULT_SPECIAL_REWARD_CONTENT_TYPE or UPDATED_SPECIAL_REWARD_CONTENT_TYPE
        defaultMLuckWeeklyQuestWorldShouldBeFound("specialRewardContentType.in=" + DEFAULT_SPECIAL_REWARD_CONTENT_TYPE + "," + UPDATED_SPECIAL_REWARD_CONTENT_TYPE);

        // Get all the mLuckWeeklyQuestWorldList where specialRewardContentType equals to UPDATED_SPECIAL_REWARD_CONTENT_TYPE
        defaultMLuckWeeklyQuestWorldShouldNotBeFound("specialRewardContentType.in=" + UPDATED_SPECIAL_REWARD_CONTENT_TYPE);
    }

    @Test
    @Transactional
    public void getAllMLuckWeeklyQuestWorldsBySpecialRewardContentTypeIsNullOrNotNull() throws Exception {
        // Initialize the database
        mLuckWeeklyQuestWorldRepository.saveAndFlush(mLuckWeeklyQuestWorld);

        // Get all the mLuckWeeklyQuestWorldList where specialRewardContentType is not null
        defaultMLuckWeeklyQuestWorldShouldBeFound("specialRewardContentType.specified=true");

        // Get all the mLuckWeeklyQuestWorldList where specialRewardContentType is null
        defaultMLuckWeeklyQuestWorldShouldNotBeFound("specialRewardContentType.specified=false");
    }

    @Test
    @Transactional
    public void getAllMLuckWeeklyQuestWorldsBySpecialRewardContentTypeIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mLuckWeeklyQuestWorldRepository.saveAndFlush(mLuckWeeklyQuestWorld);

        // Get all the mLuckWeeklyQuestWorldList where specialRewardContentType greater than or equals to DEFAULT_SPECIAL_REWARD_CONTENT_TYPE
        defaultMLuckWeeklyQuestWorldShouldBeFound("specialRewardContentType.greaterOrEqualThan=" + DEFAULT_SPECIAL_REWARD_CONTENT_TYPE);

        // Get all the mLuckWeeklyQuestWorldList where specialRewardContentType greater than or equals to UPDATED_SPECIAL_REWARD_CONTENT_TYPE
        defaultMLuckWeeklyQuestWorldShouldNotBeFound("specialRewardContentType.greaterOrEqualThan=" + UPDATED_SPECIAL_REWARD_CONTENT_TYPE);
    }

    @Test
    @Transactional
    public void getAllMLuckWeeklyQuestWorldsBySpecialRewardContentTypeIsLessThanSomething() throws Exception {
        // Initialize the database
        mLuckWeeklyQuestWorldRepository.saveAndFlush(mLuckWeeklyQuestWorld);

        // Get all the mLuckWeeklyQuestWorldList where specialRewardContentType less than or equals to DEFAULT_SPECIAL_REWARD_CONTENT_TYPE
        defaultMLuckWeeklyQuestWorldShouldNotBeFound("specialRewardContentType.lessThan=" + DEFAULT_SPECIAL_REWARD_CONTENT_TYPE);

        // Get all the mLuckWeeklyQuestWorldList where specialRewardContentType less than or equals to UPDATED_SPECIAL_REWARD_CONTENT_TYPE
        defaultMLuckWeeklyQuestWorldShouldBeFound("specialRewardContentType.lessThan=" + UPDATED_SPECIAL_REWARD_CONTENT_TYPE);
    }


    @Test
    @Transactional
    public void getAllMLuckWeeklyQuestWorldsBySpecialRewardContentIdIsEqualToSomething() throws Exception {
        // Initialize the database
        mLuckWeeklyQuestWorldRepository.saveAndFlush(mLuckWeeklyQuestWorld);

        // Get all the mLuckWeeklyQuestWorldList where specialRewardContentId equals to DEFAULT_SPECIAL_REWARD_CONTENT_ID
        defaultMLuckWeeklyQuestWorldShouldBeFound("specialRewardContentId.equals=" + DEFAULT_SPECIAL_REWARD_CONTENT_ID);

        // Get all the mLuckWeeklyQuestWorldList where specialRewardContentId equals to UPDATED_SPECIAL_REWARD_CONTENT_ID
        defaultMLuckWeeklyQuestWorldShouldNotBeFound("specialRewardContentId.equals=" + UPDATED_SPECIAL_REWARD_CONTENT_ID);
    }

    @Test
    @Transactional
    public void getAllMLuckWeeklyQuestWorldsBySpecialRewardContentIdIsInShouldWork() throws Exception {
        // Initialize the database
        mLuckWeeklyQuestWorldRepository.saveAndFlush(mLuckWeeklyQuestWorld);

        // Get all the mLuckWeeklyQuestWorldList where specialRewardContentId in DEFAULT_SPECIAL_REWARD_CONTENT_ID or UPDATED_SPECIAL_REWARD_CONTENT_ID
        defaultMLuckWeeklyQuestWorldShouldBeFound("specialRewardContentId.in=" + DEFAULT_SPECIAL_REWARD_CONTENT_ID + "," + UPDATED_SPECIAL_REWARD_CONTENT_ID);

        // Get all the mLuckWeeklyQuestWorldList where specialRewardContentId equals to UPDATED_SPECIAL_REWARD_CONTENT_ID
        defaultMLuckWeeklyQuestWorldShouldNotBeFound("specialRewardContentId.in=" + UPDATED_SPECIAL_REWARD_CONTENT_ID);
    }

    @Test
    @Transactional
    public void getAllMLuckWeeklyQuestWorldsBySpecialRewardContentIdIsNullOrNotNull() throws Exception {
        // Initialize the database
        mLuckWeeklyQuestWorldRepository.saveAndFlush(mLuckWeeklyQuestWorld);

        // Get all the mLuckWeeklyQuestWorldList where specialRewardContentId is not null
        defaultMLuckWeeklyQuestWorldShouldBeFound("specialRewardContentId.specified=true");

        // Get all the mLuckWeeklyQuestWorldList where specialRewardContentId is null
        defaultMLuckWeeklyQuestWorldShouldNotBeFound("specialRewardContentId.specified=false");
    }

    @Test
    @Transactional
    public void getAllMLuckWeeklyQuestWorldsBySpecialRewardContentIdIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mLuckWeeklyQuestWorldRepository.saveAndFlush(mLuckWeeklyQuestWorld);

        // Get all the mLuckWeeklyQuestWorldList where specialRewardContentId greater than or equals to DEFAULT_SPECIAL_REWARD_CONTENT_ID
        defaultMLuckWeeklyQuestWorldShouldBeFound("specialRewardContentId.greaterOrEqualThan=" + DEFAULT_SPECIAL_REWARD_CONTENT_ID);

        // Get all the mLuckWeeklyQuestWorldList where specialRewardContentId greater than or equals to UPDATED_SPECIAL_REWARD_CONTENT_ID
        defaultMLuckWeeklyQuestWorldShouldNotBeFound("specialRewardContentId.greaterOrEqualThan=" + UPDATED_SPECIAL_REWARD_CONTENT_ID);
    }

    @Test
    @Transactional
    public void getAllMLuckWeeklyQuestWorldsBySpecialRewardContentIdIsLessThanSomething() throws Exception {
        // Initialize the database
        mLuckWeeklyQuestWorldRepository.saveAndFlush(mLuckWeeklyQuestWorld);

        // Get all the mLuckWeeklyQuestWorldList where specialRewardContentId less than or equals to DEFAULT_SPECIAL_REWARD_CONTENT_ID
        defaultMLuckWeeklyQuestWorldShouldNotBeFound("specialRewardContentId.lessThan=" + DEFAULT_SPECIAL_REWARD_CONTENT_ID);

        // Get all the mLuckWeeklyQuestWorldList where specialRewardContentId less than or equals to UPDATED_SPECIAL_REWARD_CONTENT_ID
        defaultMLuckWeeklyQuestWorldShouldBeFound("specialRewardContentId.lessThan=" + UPDATED_SPECIAL_REWARD_CONTENT_ID);
    }


    @Test
    @Transactional
    public void getAllMLuckWeeklyQuestWorldsByIsEnableCoopIsEqualToSomething() throws Exception {
        // Initialize the database
        mLuckWeeklyQuestWorldRepository.saveAndFlush(mLuckWeeklyQuestWorld);

        // Get all the mLuckWeeklyQuestWorldList where isEnableCoop equals to DEFAULT_IS_ENABLE_COOP
        defaultMLuckWeeklyQuestWorldShouldBeFound("isEnableCoop.equals=" + DEFAULT_IS_ENABLE_COOP);

        // Get all the mLuckWeeklyQuestWorldList where isEnableCoop equals to UPDATED_IS_ENABLE_COOP
        defaultMLuckWeeklyQuestWorldShouldNotBeFound("isEnableCoop.equals=" + UPDATED_IS_ENABLE_COOP);
    }

    @Test
    @Transactional
    public void getAllMLuckWeeklyQuestWorldsByIsEnableCoopIsInShouldWork() throws Exception {
        // Initialize the database
        mLuckWeeklyQuestWorldRepository.saveAndFlush(mLuckWeeklyQuestWorld);

        // Get all the mLuckWeeklyQuestWorldList where isEnableCoop in DEFAULT_IS_ENABLE_COOP or UPDATED_IS_ENABLE_COOP
        defaultMLuckWeeklyQuestWorldShouldBeFound("isEnableCoop.in=" + DEFAULT_IS_ENABLE_COOP + "," + UPDATED_IS_ENABLE_COOP);

        // Get all the mLuckWeeklyQuestWorldList where isEnableCoop equals to UPDATED_IS_ENABLE_COOP
        defaultMLuckWeeklyQuestWorldShouldNotBeFound("isEnableCoop.in=" + UPDATED_IS_ENABLE_COOP);
    }

    @Test
    @Transactional
    public void getAllMLuckWeeklyQuestWorldsByIsEnableCoopIsNullOrNotNull() throws Exception {
        // Initialize the database
        mLuckWeeklyQuestWorldRepository.saveAndFlush(mLuckWeeklyQuestWorld);

        // Get all the mLuckWeeklyQuestWorldList where isEnableCoop is not null
        defaultMLuckWeeklyQuestWorldShouldBeFound("isEnableCoop.specified=true");

        // Get all the mLuckWeeklyQuestWorldList where isEnableCoop is null
        defaultMLuckWeeklyQuestWorldShouldNotBeFound("isEnableCoop.specified=false");
    }

    @Test
    @Transactional
    public void getAllMLuckWeeklyQuestWorldsByIsEnableCoopIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mLuckWeeklyQuestWorldRepository.saveAndFlush(mLuckWeeklyQuestWorld);

        // Get all the mLuckWeeklyQuestWorldList where isEnableCoop greater than or equals to DEFAULT_IS_ENABLE_COOP
        defaultMLuckWeeklyQuestWorldShouldBeFound("isEnableCoop.greaterOrEqualThan=" + DEFAULT_IS_ENABLE_COOP);

        // Get all the mLuckWeeklyQuestWorldList where isEnableCoop greater than or equals to UPDATED_IS_ENABLE_COOP
        defaultMLuckWeeklyQuestWorldShouldNotBeFound("isEnableCoop.greaterOrEqualThan=" + UPDATED_IS_ENABLE_COOP);
    }

    @Test
    @Transactional
    public void getAllMLuckWeeklyQuestWorldsByIsEnableCoopIsLessThanSomething() throws Exception {
        // Initialize the database
        mLuckWeeklyQuestWorldRepository.saveAndFlush(mLuckWeeklyQuestWorld);

        // Get all the mLuckWeeklyQuestWorldList where isEnableCoop less than or equals to DEFAULT_IS_ENABLE_COOP
        defaultMLuckWeeklyQuestWorldShouldNotBeFound("isEnableCoop.lessThan=" + DEFAULT_IS_ENABLE_COOP);

        // Get all the mLuckWeeklyQuestWorldList where isEnableCoop less than or equals to UPDATED_IS_ENABLE_COOP
        defaultMLuckWeeklyQuestWorldShouldBeFound("isEnableCoop.lessThan=" + UPDATED_IS_ENABLE_COOP);
    }


    @Test
    @Transactional
    public void getAllMLuckWeeklyQuestWorldsByClearLimitIsEqualToSomething() throws Exception {
        // Initialize the database
        mLuckWeeklyQuestWorldRepository.saveAndFlush(mLuckWeeklyQuestWorld);

        // Get all the mLuckWeeklyQuestWorldList where clearLimit equals to DEFAULT_CLEAR_LIMIT
        defaultMLuckWeeklyQuestWorldShouldBeFound("clearLimit.equals=" + DEFAULT_CLEAR_LIMIT);

        // Get all the mLuckWeeklyQuestWorldList where clearLimit equals to UPDATED_CLEAR_LIMIT
        defaultMLuckWeeklyQuestWorldShouldNotBeFound("clearLimit.equals=" + UPDATED_CLEAR_LIMIT);
    }

    @Test
    @Transactional
    public void getAllMLuckWeeklyQuestWorldsByClearLimitIsInShouldWork() throws Exception {
        // Initialize the database
        mLuckWeeklyQuestWorldRepository.saveAndFlush(mLuckWeeklyQuestWorld);

        // Get all the mLuckWeeklyQuestWorldList where clearLimit in DEFAULT_CLEAR_LIMIT or UPDATED_CLEAR_LIMIT
        defaultMLuckWeeklyQuestWorldShouldBeFound("clearLimit.in=" + DEFAULT_CLEAR_LIMIT + "," + UPDATED_CLEAR_LIMIT);

        // Get all the mLuckWeeklyQuestWorldList where clearLimit equals to UPDATED_CLEAR_LIMIT
        defaultMLuckWeeklyQuestWorldShouldNotBeFound("clearLimit.in=" + UPDATED_CLEAR_LIMIT);
    }

    @Test
    @Transactional
    public void getAllMLuckWeeklyQuestWorldsByClearLimitIsNullOrNotNull() throws Exception {
        // Initialize the database
        mLuckWeeklyQuestWorldRepository.saveAndFlush(mLuckWeeklyQuestWorld);

        // Get all the mLuckWeeklyQuestWorldList where clearLimit is not null
        defaultMLuckWeeklyQuestWorldShouldBeFound("clearLimit.specified=true");

        // Get all the mLuckWeeklyQuestWorldList where clearLimit is null
        defaultMLuckWeeklyQuestWorldShouldNotBeFound("clearLimit.specified=false");
    }

    @Test
    @Transactional
    public void getAllMLuckWeeklyQuestWorldsByClearLimitIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mLuckWeeklyQuestWorldRepository.saveAndFlush(mLuckWeeklyQuestWorld);

        // Get all the mLuckWeeklyQuestWorldList where clearLimit greater than or equals to DEFAULT_CLEAR_LIMIT
        defaultMLuckWeeklyQuestWorldShouldBeFound("clearLimit.greaterOrEqualThan=" + DEFAULT_CLEAR_LIMIT);

        // Get all the mLuckWeeklyQuestWorldList where clearLimit greater than or equals to UPDATED_CLEAR_LIMIT
        defaultMLuckWeeklyQuestWorldShouldNotBeFound("clearLimit.greaterOrEqualThan=" + UPDATED_CLEAR_LIMIT);
    }

    @Test
    @Transactional
    public void getAllMLuckWeeklyQuestWorldsByClearLimitIsLessThanSomething() throws Exception {
        // Initialize the database
        mLuckWeeklyQuestWorldRepository.saveAndFlush(mLuckWeeklyQuestWorld);

        // Get all the mLuckWeeklyQuestWorldList where clearLimit less than or equals to DEFAULT_CLEAR_LIMIT
        defaultMLuckWeeklyQuestWorldShouldNotBeFound("clearLimit.lessThan=" + DEFAULT_CLEAR_LIMIT);

        // Get all the mLuckWeeklyQuestWorldList where clearLimit less than or equals to UPDATED_CLEAR_LIMIT
        defaultMLuckWeeklyQuestWorldShouldBeFound("clearLimit.lessThan=" + UPDATED_CLEAR_LIMIT);
    }


    @Test
    @Transactional
    public void getAllMLuckWeeklyQuestWorldsByMLuckWeeklyQuestStageIsEqualToSomething() throws Exception {
        // Initialize the database
        MLuckWeeklyQuestStage mLuckWeeklyQuestStage = MLuckWeeklyQuestStageResourceIT.createEntity(em);
        em.persist(mLuckWeeklyQuestStage);
        em.flush();
        mLuckWeeklyQuestWorld.addMLuckWeeklyQuestStage(mLuckWeeklyQuestStage);
        mLuckWeeklyQuestWorldRepository.saveAndFlush(mLuckWeeklyQuestWorld);
        Long mLuckWeeklyQuestStageId = mLuckWeeklyQuestStage.getId();

        // Get all the mLuckWeeklyQuestWorldList where mLuckWeeklyQuestStage equals to mLuckWeeklyQuestStageId
        defaultMLuckWeeklyQuestWorldShouldBeFound("mLuckWeeklyQuestStageId.equals=" + mLuckWeeklyQuestStageId);

        // Get all the mLuckWeeklyQuestWorldList where mLuckWeeklyQuestStage equals to mLuckWeeklyQuestStageId + 1
        defaultMLuckWeeklyQuestWorldShouldNotBeFound("mLuckWeeklyQuestStageId.equals=" + (mLuckWeeklyQuestStageId + 1));
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultMLuckWeeklyQuestWorldShouldBeFound(String filter) throws Exception {
        restMLuckWeeklyQuestWorldMockMvc.perform(get("/api/m-luck-weekly-quest-worlds?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(mLuckWeeklyQuestWorld.getId().intValue())))
            .andExpect(jsonPath("$.[*].setId").value(hasItem(DEFAULT_SET_ID)))
            .andExpect(jsonPath("$.[*].number").value(hasItem(DEFAULT_NUMBER)))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME.toString())))
            .andExpect(jsonPath("$.[*].imagePath").value(hasItem(DEFAULT_IMAGE_PATH.toString())))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION.toString())))
            .andExpect(jsonPath("$.[*].stageUnlockPattern").value(hasItem(DEFAULT_STAGE_UNLOCK_PATTERN)))
            .andExpect(jsonPath("$.[*].arousalBanner").value(hasItem(DEFAULT_AROUSAL_BANNER.toString())))
            .andExpect(jsonPath("$.[*].specialRewardContentType").value(hasItem(DEFAULT_SPECIAL_REWARD_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].specialRewardContentId").value(hasItem(DEFAULT_SPECIAL_REWARD_CONTENT_ID)))
            .andExpect(jsonPath("$.[*].isEnableCoop").value(hasItem(DEFAULT_IS_ENABLE_COOP)))
            .andExpect(jsonPath("$.[*].clearLimit").value(hasItem(DEFAULT_CLEAR_LIMIT)));

        // Check, that the count call also returns 1
        restMLuckWeeklyQuestWorldMockMvc.perform(get("/api/m-luck-weekly-quest-worlds/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultMLuckWeeklyQuestWorldShouldNotBeFound(String filter) throws Exception {
        restMLuckWeeklyQuestWorldMockMvc.perform(get("/api/m-luck-weekly-quest-worlds?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restMLuckWeeklyQuestWorldMockMvc.perform(get("/api/m-luck-weekly-quest-worlds/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("0"));
    }


    @Test
    @Transactional
    public void getNonExistingMLuckWeeklyQuestWorld() throws Exception {
        // Get the mLuckWeeklyQuestWorld
        restMLuckWeeklyQuestWorldMockMvc.perform(get("/api/m-luck-weekly-quest-worlds/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateMLuckWeeklyQuestWorld() throws Exception {
        // Initialize the database
        mLuckWeeklyQuestWorldRepository.saveAndFlush(mLuckWeeklyQuestWorld);

        int databaseSizeBeforeUpdate = mLuckWeeklyQuestWorldRepository.findAll().size();

        // Update the mLuckWeeklyQuestWorld
        MLuckWeeklyQuestWorld updatedMLuckWeeklyQuestWorld = mLuckWeeklyQuestWorldRepository.findById(mLuckWeeklyQuestWorld.getId()).get();
        // Disconnect from session so that the updates on updatedMLuckWeeklyQuestWorld are not directly saved in db
        em.detach(updatedMLuckWeeklyQuestWorld);
        updatedMLuckWeeklyQuestWorld
            .setId(UPDATED_SET_ID)
            .number(UPDATED_NUMBER)
            .name(UPDATED_NAME)
            .imagePath(UPDATED_IMAGE_PATH)
            .description(UPDATED_DESCRIPTION)
            .stageUnlockPattern(UPDATED_STAGE_UNLOCK_PATTERN)
            .arousalBanner(UPDATED_AROUSAL_BANNER)
            .specialRewardContentType(UPDATED_SPECIAL_REWARD_CONTENT_TYPE)
            .specialRewardContentId(UPDATED_SPECIAL_REWARD_CONTENT_ID)
            .isEnableCoop(UPDATED_IS_ENABLE_COOP)
            .clearLimit(UPDATED_CLEAR_LIMIT);
        MLuckWeeklyQuestWorldDTO mLuckWeeklyQuestWorldDTO = mLuckWeeklyQuestWorldMapper.toDto(updatedMLuckWeeklyQuestWorld);

        restMLuckWeeklyQuestWorldMockMvc.perform(put("/api/m-luck-weekly-quest-worlds")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mLuckWeeklyQuestWorldDTO)))
            .andExpect(status().isOk());

        // Validate the MLuckWeeklyQuestWorld in the database
        List<MLuckWeeklyQuestWorld> mLuckWeeklyQuestWorldList = mLuckWeeklyQuestWorldRepository.findAll();
        assertThat(mLuckWeeklyQuestWorldList).hasSize(databaseSizeBeforeUpdate);
        MLuckWeeklyQuestWorld testMLuckWeeklyQuestWorld = mLuckWeeklyQuestWorldList.get(mLuckWeeklyQuestWorldList.size() - 1);
        assertThat(testMLuckWeeklyQuestWorld.getSetId()).isEqualTo(UPDATED_SET_ID);
        assertThat(testMLuckWeeklyQuestWorld.getNumber()).isEqualTo(UPDATED_NUMBER);
        assertThat(testMLuckWeeklyQuestWorld.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testMLuckWeeklyQuestWorld.getImagePath()).isEqualTo(UPDATED_IMAGE_PATH);
        assertThat(testMLuckWeeklyQuestWorld.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
        assertThat(testMLuckWeeklyQuestWorld.getStageUnlockPattern()).isEqualTo(UPDATED_STAGE_UNLOCK_PATTERN);
        assertThat(testMLuckWeeklyQuestWorld.getArousalBanner()).isEqualTo(UPDATED_AROUSAL_BANNER);
        assertThat(testMLuckWeeklyQuestWorld.getSpecialRewardContentType()).isEqualTo(UPDATED_SPECIAL_REWARD_CONTENT_TYPE);
        assertThat(testMLuckWeeklyQuestWorld.getSpecialRewardContentId()).isEqualTo(UPDATED_SPECIAL_REWARD_CONTENT_ID);
        assertThat(testMLuckWeeklyQuestWorld.getIsEnableCoop()).isEqualTo(UPDATED_IS_ENABLE_COOP);
        assertThat(testMLuckWeeklyQuestWorld.getClearLimit()).isEqualTo(UPDATED_CLEAR_LIMIT);
    }

    @Test
    @Transactional
    public void updateNonExistingMLuckWeeklyQuestWorld() throws Exception {
        int databaseSizeBeforeUpdate = mLuckWeeklyQuestWorldRepository.findAll().size();

        // Create the MLuckWeeklyQuestWorld
        MLuckWeeklyQuestWorldDTO mLuckWeeklyQuestWorldDTO = mLuckWeeklyQuestWorldMapper.toDto(mLuckWeeklyQuestWorld);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restMLuckWeeklyQuestWorldMockMvc.perform(put("/api/m-luck-weekly-quest-worlds")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mLuckWeeklyQuestWorldDTO)))
            .andExpect(status().isBadRequest());

        // Validate the MLuckWeeklyQuestWorld in the database
        List<MLuckWeeklyQuestWorld> mLuckWeeklyQuestWorldList = mLuckWeeklyQuestWorldRepository.findAll();
        assertThat(mLuckWeeklyQuestWorldList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteMLuckWeeklyQuestWorld() throws Exception {
        // Initialize the database
        mLuckWeeklyQuestWorldRepository.saveAndFlush(mLuckWeeklyQuestWorld);

        int databaseSizeBeforeDelete = mLuckWeeklyQuestWorldRepository.findAll().size();

        // Delete the mLuckWeeklyQuestWorld
        restMLuckWeeklyQuestWorldMockMvc.perform(delete("/api/m-luck-weekly-quest-worlds/{id}", mLuckWeeklyQuestWorld.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database is empty
        List<MLuckWeeklyQuestWorld> mLuckWeeklyQuestWorldList = mLuckWeeklyQuestWorldRepository.findAll();
        assertThat(mLuckWeeklyQuestWorldList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(MLuckWeeklyQuestWorld.class);
        MLuckWeeklyQuestWorld mLuckWeeklyQuestWorld1 = new MLuckWeeklyQuestWorld();
        mLuckWeeklyQuestWorld1.setId(1L);
        MLuckWeeklyQuestWorld mLuckWeeklyQuestWorld2 = new MLuckWeeklyQuestWorld();
        mLuckWeeklyQuestWorld2.setId(mLuckWeeklyQuestWorld1.getId());
        assertThat(mLuckWeeklyQuestWorld1).isEqualTo(mLuckWeeklyQuestWorld2);
        mLuckWeeklyQuestWorld2.setId(2L);
        assertThat(mLuckWeeklyQuestWorld1).isNotEqualTo(mLuckWeeklyQuestWorld2);
        mLuckWeeklyQuestWorld1.setId(null);
        assertThat(mLuckWeeklyQuestWorld1).isNotEqualTo(mLuckWeeklyQuestWorld2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(MLuckWeeklyQuestWorldDTO.class);
        MLuckWeeklyQuestWorldDTO mLuckWeeklyQuestWorldDTO1 = new MLuckWeeklyQuestWorldDTO();
        mLuckWeeklyQuestWorldDTO1.setId(1L);
        MLuckWeeklyQuestWorldDTO mLuckWeeklyQuestWorldDTO2 = new MLuckWeeklyQuestWorldDTO();
        assertThat(mLuckWeeklyQuestWorldDTO1).isNotEqualTo(mLuckWeeklyQuestWorldDTO2);
        mLuckWeeklyQuestWorldDTO2.setId(mLuckWeeklyQuestWorldDTO1.getId());
        assertThat(mLuckWeeklyQuestWorldDTO1).isEqualTo(mLuckWeeklyQuestWorldDTO2);
        mLuckWeeklyQuestWorldDTO2.setId(2L);
        assertThat(mLuckWeeklyQuestWorldDTO1).isNotEqualTo(mLuckWeeklyQuestWorldDTO2);
        mLuckWeeklyQuestWorldDTO1.setId(null);
        assertThat(mLuckWeeklyQuestWorldDTO1).isNotEqualTo(mLuckWeeklyQuestWorldDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(mLuckWeeklyQuestWorldMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(mLuckWeeklyQuestWorldMapper.fromId(null)).isNull();
    }
}
