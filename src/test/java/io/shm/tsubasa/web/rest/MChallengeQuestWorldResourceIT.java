package io.shm.tsubasa.web.rest;

import io.shm.tsubasa.TsubasaApp;
import io.shm.tsubasa.domain.MChallengeQuestWorld;
import io.shm.tsubasa.domain.MChallengeQuestStage;
import io.shm.tsubasa.repository.MChallengeQuestWorldRepository;
import io.shm.tsubasa.service.MChallengeQuestWorldService;
import io.shm.tsubasa.service.dto.MChallengeQuestWorldDTO;
import io.shm.tsubasa.service.mapper.MChallengeQuestWorldMapper;
import io.shm.tsubasa.web.rest.errors.ExceptionTranslator;
import io.shm.tsubasa.service.dto.MChallengeQuestWorldCriteria;
import io.shm.tsubasa.service.MChallengeQuestWorldQueryService;

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
 * Integration tests for the {@Link MChallengeQuestWorldResource} REST controller.
 */
@SpringBootTest(classes = TsubasaApp.class)
public class MChallengeQuestWorldResourceIT {

    private static final Integer DEFAULT_SET_ID = 1;
    private static final Integer UPDATED_SET_ID = 2;

    private static final Integer DEFAULT_NUMBER = 1;
    private static final Integer UPDATED_NUMBER = 2;

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_IMAGE_PATH = "AAAAAAAAAA";
    private static final String UPDATED_IMAGE_PATH = "BBBBBBBBBB";

    private static final String DEFAULT_BACKGROUND_IMAGE_PATH = "AAAAAAAAAA";
    private static final String UPDATED_BACKGROUND_IMAGE_PATH = "BBBBBBBBBB";

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

    @Autowired
    private MChallengeQuestWorldRepository mChallengeQuestWorldRepository;

    @Autowired
    private MChallengeQuestWorldMapper mChallengeQuestWorldMapper;

    @Autowired
    private MChallengeQuestWorldService mChallengeQuestWorldService;

    @Autowired
    private MChallengeQuestWorldQueryService mChallengeQuestWorldQueryService;

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

    private MockMvc restMChallengeQuestWorldMockMvc;

    private MChallengeQuestWorld mChallengeQuestWorld;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final MChallengeQuestWorldResource mChallengeQuestWorldResource = new MChallengeQuestWorldResource(mChallengeQuestWorldService, mChallengeQuestWorldQueryService);
        this.restMChallengeQuestWorldMockMvc = MockMvcBuilders.standaloneSetup(mChallengeQuestWorldResource)
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
    public static MChallengeQuestWorld createEntity(EntityManager em) {
        MChallengeQuestWorld mChallengeQuestWorld = new MChallengeQuestWorld()
            .setId(DEFAULT_SET_ID)
            .number(DEFAULT_NUMBER)
            .name(DEFAULT_NAME)
            .imagePath(DEFAULT_IMAGE_PATH)
            .backgroundImagePath(DEFAULT_BACKGROUND_IMAGE_PATH)
            .description(DEFAULT_DESCRIPTION)
            .stageUnlockPattern(DEFAULT_STAGE_UNLOCK_PATTERN)
            .arousalBanner(DEFAULT_AROUSAL_BANNER)
            .specialRewardContentType(DEFAULT_SPECIAL_REWARD_CONTENT_TYPE)
            .specialRewardContentId(DEFAULT_SPECIAL_REWARD_CONTENT_ID)
            .isEnableCoop(DEFAULT_IS_ENABLE_COOP);
        return mChallengeQuestWorld;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static MChallengeQuestWorld createUpdatedEntity(EntityManager em) {
        MChallengeQuestWorld mChallengeQuestWorld = new MChallengeQuestWorld()
            .setId(UPDATED_SET_ID)
            .number(UPDATED_NUMBER)
            .name(UPDATED_NAME)
            .imagePath(UPDATED_IMAGE_PATH)
            .backgroundImagePath(UPDATED_BACKGROUND_IMAGE_PATH)
            .description(UPDATED_DESCRIPTION)
            .stageUnlockPattern(UPDATED_STAGE_UNLOCK_PATTERN)
            .arousalBanner(UPDATED_AROUSAL_BANNER)
            .specialRewardContentType(UPDATED_SPECIAL_REWARD_CONTENT_TYPE)
            .specialRewardContentId(UPDATED_SPECIAL_REWARD_CONTENT_ID)
            .isEnableCoop(UPDATED_IS_ENABLE_COOP);
        return mChallengeQuestWorld;
    }

    @BeforeEach
    public void initTest() {
        mChallengeQuestWorld = createEntity(em);
    }

    @Test
    @Transactional
    public void createMChallengeQuestWorld() throws Exception {
        int databaseSizeBeforeCreate = mChallengeQuestWorldRepository.findAll().size();

        // Create the MChallengeQuestWorld
        MChallengeQuestWorldDTO mChallengeQuestWorldDTO = mChallengeQuestWorldMapper.toDto(mChallengeQuestWorld);
        restMChallengeQuestWorldMockMvc.perform(post("/api/m-challenge-quest-worlds")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mChallengeQuestWorldDTO)))
            .andExpect(status().isCreated());

        // Validate the MChallengeQuestWorld in the database
        List<MChallengeQuestWorld> mChallengeQuestWorldList = mChallengeQuestWorldRepository.findAll();
        assertThat(mChallengeQuestWorldList).hasSize(databaseSizeBeforeCreate + 1);
        MChallengeQuestWorld testMChallengeQuestWorld = mChallengeQuestWorldList.get(mChallengeQuestWorldList.size() - 1);
        assertThat(testMChallengeQuestWorld.getSetId()).isEqualTo(DEFAULT_SET_ID);
        assertThat(testMChallengeQuestWorld.getNumber()).isEqualTo(DEFAULT_NUMBER);
        assertThat(testMChallengeQuestWorld.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testMChallengeQuestWorld.getImagePath()).isEqualTo(DEFAULT_IMAGE_PATH);
        assertThat(testMChallengeQuestWorld.getBackgroundImagePath()).isEqualTo(DEFAULT_BACKGROUND_IMAGE_PATH);
        assertThat(testMChallengeQuestWorld.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
        assertThat(testMChallengeQuestWorld.getStageUnlockPattern()).isEqualTo(DEFAULT_STAGE_UNLOCK_PATTERN);
        assertThat(testMChallengeQuestWorld.getArousalBanner()).isEqualTo(DEFAULT_AROUSAL_BANNER);
        assertThat(testMChallengeQuestWorld.getSpecialRewardContentType()).isEqualTo(DEFAULT_SPECIAL_REWARD_CONTENT_TYPE);
        assertThat(testMChallengeQuestWorld.getSpecialRewardContentId()).isEqualTo(DEFAULT_SPECIAL_REWARD_CONTENT_ID);
        assertThat(testMChallengeQuestWorld.getIsEnableCoop()).isEqualTo(DEFAULT_IS_ENABLE_COOP);
    }

    @Test
    @Transactional
    public void createMChallengeQuestWorldWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = mChallengeQuestWorldRepository.findAll().size();

        // Create the MChallengeQuestWorld with an existing ID
        mChallengeQuestWorld.setId(1L);
        MChallengeQuestWorldDTO mChallengeQuestWorldDTO = mChallengeQuestWorldMapper.toDto(mChallengeQuestWorld);

        // An entity with an existing ID cannot be created, so this API call must fail
        restMChallengeQuestWorldMockMvc.perform(post("/api/m-challenge-quest-worlds")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mChallengeQuestWorldDTO)))
            .andExpect(status().isBadRequest());

        // Validate the MChallengeQuestWorld in the database
        List<MChallengeQuestWorld> mChallengeQuestWorldList = mChallengeQuestWorldRepository.findAll();
        assertThat(mChallengeQuestWorldList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkSetIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = mChallengeQuestWorldRepository.findAll().size();
        // set the field null
        mChallengeQuestWorld.setSetId(null);

        // Create the MChallengeQuestWorld, which fails.
        MChallengeQuestWorldDTO mChallengeQuestWorldDTO = mChallengeQuestWorldMapper.toDto(mChallengeQuestWorld);

        restMChallengeQuestWorldMockMvc.perform(post("/api/m-challenge-quest-worlds")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mChallengeQuestWorldDTO)))
            .andExpect(status().isBadRequest());

        List<MChallengeQuestWorld> mChallengeQuestWorldList = mChallengeQuestWorldRepository.findAll();
        assertThat(mChallengeQuestWorldList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkNumberIsRequired() throws Exception {
        int databaseSizeBeforeTest = mChallengeQuestWorldRepository.findAll().size();
        // set the field null
        mChallengeQuestWorld.setNumber(null);

        // Create the MChallengeQuestWorld, which fails.
        MChallengeQuestWorldDTO mChallengeQuestWorldDTO = mChallengeQuestWorldMapper.toDto(mChallengeQuestWorld);

        restMChallengeQuestWorldMockMvc.perform(post("/api/m-challenge-quest-worlds")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mChallengeQuestWorldDTO)))
            .andExpect(status().isBadRequest());

        List<MChallengeQuestWorld> mChallengeQuestWorldList = mChallengeQuestWorldRepository.findAll();
        assertThat(mChallengeQuestWorldList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkStageUnlockPatternIsRequired() throws Exception {
        int databaseSizeBeforeTest = mChallengeQuestWorldRepository.findAll().size();
        // set the field null
        mChallengeQuestWorld.setStageUnlockPattern(null);

        // Create the MChallengeQuestWorld, which fails.
        MChallengeQuestWorldDTO mChallengeQuestWorldDTO = mChallengeQuestWorldMapper.toDto(mChallengeQuestWorld);

        restMChallengeQuestWorldMockMvc.perform(post("/api/m-challenge-quest-worlds")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mChallengeQuestWorldDTO)))
            .andExpect(status().isBadRequest());

        List<MChallengeQuestWorld> mChallengeQuestWorldList = mChallengeQuestWorldRepository.findAll();
        assertThat(mChallengeQuestWorldList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkIsEnableCoopIsRequired() throws Exception {
        int databaseSizeBeforeTest = mChallengeQuestWorldRepository.findAll().size();
        // set the field null
        mChallengeQuestWorld.setIsEnableCoop(null);

        // Create the MChallengeQuestWorld, which fails.
        MChallengeQuestWorldDTO mChallengeQuestWorldDTO = mChallengeQuestWorldMapper.toDto(mChallengeQuestWorld);

        restMChallengeQuestWorldMockMvc.perform(post("/api/m-challenge-quest-worlds")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mChallengeQuestWorldDTO)))
            .andExpect(status().isBadRequest());

        List<MChallengeQuestWorld> mChallengeQuestWorldList = mChallengeQuestWorldRepository.findAll();
        assertThat(mChallengeQuestWorldList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllMChallengeQuestWorlds() throws Exception {
        // Initialize the database
        mChallengeQuestWorldRepository.saveAndFlush(mChallengeQuestWorld);

        // Get all the mChallengeQuestWorldList
        restMChallengeQuestWorldMockMvc.perform(get("/api/m-challenge-quest-worlds?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(mChallengeQuestWorld.getId().intValue())))
            .andExpect(jsonPath("$.[*].setId").value(hasItem(DEFAULT_SET_ID)))
            .andExpect(jsonPath("$.[*].number").value(hasItem(DEFAULT_NUMBER)))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME.toString())))
            .andExpect(jsonPath("$.[*].imagePath").value(hasItem(DEFAULT_IMAGE_PATH.toString())))
            .andExpect(jsonPath("$.[*].backgroundImagePath").value(hasItem(DEFAULT_BACKGROUND_IMAGE_PATH.toString())))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION.toString())))
            .andExpect(jsonPath("$.[*].stageUnlockPattern").value(hasItem(DEFAULT_STAGE_UNLOCK_PATTERN)))
            .andExpect(jsonPath("$.[*].arousalBanner").value(hasItem(DEFAULT_AROUSAL_BANNER.toString())))
            .andExpect(jsonPath("$.[*].specialRewardContentType").value(hasItem(DEFAULT_SPECIAL_REWARD_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].specialRewardContentId").value(hasItem(DEFAULT_SPECIAL_REWARD_CONTENT_ID)))
            .andExpect(jsonPath("$.[*].isEnableCoop").value(hasItem(DEFAULT_IS_ENABLE_COOP)));
    }
    
    @Test
    @Transactional
    public void getMChallengeQuestWorld() throws Exception {
        // Initialize the database
        mChallengeQuestWorldRepository.saveAndFlush(mChallengeQuestWorld);

        // Get the mChallengeQuestWorld
        restMChallengeQuestWorldMockMvc.perform(get("/api/m-challenge-quest-worlds/{id}", mChallengeQuestWorld.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(mChallengeQuestWorld.getId().intValue()))
            .andExpect(jsonPath("$.setId").value(DEFAULT_SET_ID))
            .andExpect(jsonPath("$.number").value(DEFAULT_NUMBER))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME.toString()))
            .andExpect(jsonPath("$.imagePath").value(DEFAULT_IMAGE_PATH.toString()))
            .andExpect(jsonPath("$.backgroundImagePath").value(DEFAULT_BACKGROUND_IMAGE_PATH.toString()))
            .andExpect(jsonPath("$.description").value(DEFAULT_DESCRIPTION.toString()))
            .andExpect(jsonPath("$.stageUnlockPattern").value(DEFAULT_STAGE_UNLOCK_PATTERN))
            .andExpect(jsonPath("$.arousalBanner").value(DEFAULT_AROUSAL_BANNER.toString()))
            .andExpect(jsonPath("$.specialRewardContentType").value(DEFAULT_SPECIAL_REWARD_CONTENT_TYPE))
            .andExpect(jsonPath("$.specialRewardContentId").value(DEFAULT_SPECIAL_REWARD_CONTENT_ID))
            .andExpect(jsonPath("$.isEnableCoop").value(DEFAULT_IS_ENABLE_COOP));
    }

    @Test
    @Transactional
    public void getAllMChallengeQuestWorldsBySetIdIsEqualToSomething() throws Exception {
        // Initialize the database
        mChallengeQuestWorldRepository.saveAndFlush(mChallengeQuestWorld);

        // Get all the mChallengeQuestWorldList where setId equals to DEFAULT_SET_ID
        defaultMChallengeQuestWorldShouldBeFound("setId.equals=" + DEFAULT_SET_ID);

        // Get all the mChallengeQuestWorldList where setId equals to UPDATED_SET_ID
        defaultMChallengeQuestWorldShouldNotBeFound("setId.equals=" + UPDATED_SET_ID);
    }

    @Test
    @Transactional
    public void getAllMChallengeQuestWorldsBySetIdIsInShouldWork() throws Exception {
        // Initialize the database
        mChallengeQuestWorldRepository.saveAndFlush(mChallengeQuestWorld);

        // Get all the mChallengeQuestWorldList where setId in DEFAULT_SET_ID or UPDATED_SET_ID
        defaultMChallengeQuestWorldShouldBeFound("setId.in=" + DEFAULT_SET_ID + "," + UPDATED_SET_ID);

        // Get all the mChallengeQuestWorldList where setId equals to UPDATED_SET_ID
        defaultMChallengeQuestWorldShouldNotBeFound("setId.in=" + UPDATED_SET_ID);
    }

    @Test
    @Transactional
    public void getAllMChallengeQuestWorldsBySetIdIsNullOrNotNull() throws Exception {
        // Initialize the database
        mChallengeQuestWorldRepository.saveAndFlush(mChallengeQuestWorld);

        // Get all the mChallengeQuestWorldList where setId is not null
        defaultMChallengeQuestWorldShouldBeFound("setId.specified=true");

        // Get all the mChallengeQuestWorldList where setId is null
        defaultMChallengeQuestWorldShouldNotBeFound("setId.specified=false");
    }

    @Test
    @Transactional
    public void getAllMChallengeQuestWorldsBySetIdIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mChallengeQuestWorldRepository.saveAndFlush(mChallengeQuestWorld);

        // Get all the mChallengeQuestWorldList where setId greater than or equals to DEFAULT_SET_ID
        defaultMChallengeQuestWorldShouldBeFound("setId.greaterOrEqualThan=" + DEFAULT_SET_ID);

        // Get all the mChallengeQuestWorldList where setId greater than or equals to UPDATED_SET_ID
        defaultMChallengeQuestWorldShouldNotBeFound("setId.greaterOrEqualThan=" + UPDATED_SET_ID);
    }

    @Test
    @Transactional
    public void getAllMChallengeQuestWorldsBySetIdIsLessThanSomething() throws Exception {
        // Initialize the database
        mChallengeQuestWorldRepository.saveAndFlush(mChallengeQuestWorld);

        // Get all the mChallengeQuestWorldList where setId less than or equals to DEFAULT_SET_ID
        defaultMChallengeQuestWorldShouldNotBeFound("setId.lessThan=" + DEFAULT_SET_ID);

        // Get all the mChallengeQuestWorldList where setId less than or equals to UPDATED_SET_ID
        defaultMChallengeQuestWorldShouldBeFound("setId.lessThan=" + UPDATED_SET_ID);
    }


    @Test
    @Transactional
    public void getAllMChallengeQuestWorldsByNumberIsEqualToSomething() throws Exception {
        // Initialize the database
        mChallengeQuestWorldRepository.saveAndFlush(mChallengeQuestWorld);

        // Get all the mChallengeQuestWorldList where number equals to DEFAULT_NUMBER
        defaultMChallengeQuestWorldShouldBeFound("number.equals=" + DEFAULT_NUMBER);

        // Get all the mChallengeQuestWorldList where number equals to UPDATED_NUMBER
        defaultMChallengeQuestWorldShouldNotBeFound("number.equals=" + UPDATED_NUMBER);
    }

    @Test
    @Transactional
    public void getAllMChallengeQuestWorldsByNumberIsInShouldWork() throws Exception {
        // Initialize the database
        mChallengeQuestWorldRepository.saveAndFlush(mChallengeQuestWorld);

        // Get all the mChallengeQuestWorldList where number in DEFAULT_NUMBER or UPDATED_NUMBER
        defaultMChallengeQuestWorldShouldBeFound("number.in=" + DEFAULT_NUMBER + "," + UPDATED_NUMBER);

        // Get all the mChallengeQuestWorldList where number equals to UPDATED_NUMBER
        defaultMChallengeQuestWorldShouldNotBeFound("number.in=" + UPDATED_NUMBER);
    }

    @Test
    @Transactional
    public void getAllMChallengeQuestWorldsByNumberIsNullOrNotNull() throws Exception {
        // Initialize the database
        mChallengeQuestWorldRepository.saveAndFlush(mChallengeQuestWorld);

        // Get all the mChallengeQuestWorldList where number is not null
        defaultMChallengeQuestWorldShouldBeFound("number.specified=true");

        // Get all the mChallengeQuestWorldList where number is null
        defaultMChallengeQuestWorldShouldNotBeFound("number.specified=false");
    }

    @Test
    @Transactional
    public void getAllMChallengeQuestWorldsByNumberIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mChallengeQuestWorldRepository.saveAndFlush(mChallengeQuestWorld);

        // Get all the mChallengeQuestWorldList where number greater than or equals to DEFAULT_NUMBER
        defaultMChallengeQuestWorldShouldBeFound("number.greaterOrEqualThan=" + DEFAULT_NUMBER);

        // Get all the mChallengeQuestWorldList where number greater than or equals to UPDATED_NUMBER
        defaultMChallengeQuestWorldShouldNotBeFound("number.greaterOrEqualThan=" + UPDATED_NUMBER);
    }

    @Test
    @Transactional
    public void getAllMChallengeQuestWorldsByNumberIsLessThanSomething() throws Exception {
        // Initialize the database
        mChallengeQuestWorldRepository.saveAndFlush(mChallengeQuestWorld);

        // Get all the mChallengeQuestWorldList where number less than or equals to DEFAULT_NUMBER
        defaultMChallengeQuestWorldShouldNotBeFound("number.lessThan=" + DEFAULT_NUMBER);

        // Get all the mChallengeQuestWorldList where number less than or equals to UPDATED_NUMBER
        defaultMChallengeQuestWorldShouldBeFound("number.lessThan=" + UPDATED_NUMBER);
    }


    @Test
    @Transactional
    public void getAllMChallengeQuestWorldsByStageUnlockPatternIsEqualToSomething() throws Exception {
        // Initialize the database
        mChallengeQuestWorldRepository.saveAndFlush(mChallengeQuestWorld);

        // Get all the mChallengeQuestWorldList where stageUnlockPattern equals to DEFAULT_STAGE_UNLOCK_PATTERN
        defaultMChallengeQuestWorldShouldBeFound("stageUnlockPattern.equals=" + DEFAULT_STAGE_UNLOCK_PATTERN);

        // Get all the mChallengeQuestWorldList where stageUnlockPattern equals to UPDATED_STAGE_UNLOCK_PATTERN
        defaultMChallengeQuestWorldShouldNotBeFound("stageUnlockPattern.equals=" + UPDATED_STAGE_UNLOCK_PATTERN);
    }

    @Test
    @Transactional
    public void getAllMChallengeQuestWorldsByStageUnlockPatternIsInShouldWork() throws Exception {
        // Initialize the database
        mChallengeQuestWorldRepository.saveAndFlush(mChallengeQuestWorld);

        // Get all the mChallengeQuestWorldList where stageUnlockPattern in DEFAULT_STAGE_UNLOCK_PATTERN or UPDATED_STAGE_UNLOCK_PATTERN
        defaultMChallengeQuestWorldShouldBeFound("stageUnlockPattern.in=" + DEFAULT_STAGE_UNLOCK_PATTERN + "," + UPDATED_STAGE_UNLOCK_PATTERN);

        // Get all the mChallengeQuestWorldList where stageUnlockPattern equals to UPDATED_STAGE_UNLOCK_PATTERN
        defaultMChallengeQuestWorldShouldNotBeFound("stageUnlockPattern.in=" + UPDATED_STAGE_UNLOCK_PATTERN);
    }

    @Test
    @Transactional
    public void getAllMChallengeQuestWorldsByStageUnlockPatternIsNullOrNotNull() throws Exception {
        // Initialize the database
        mChallengeQuestWorldRepository.saveAndFlush(mChallengeQuestWorld);

        // Get all the mChallengeQuestWorldList where stageUnlockPattern is not null
        defaultMChallengeQuestWorldShouldBeFound("stageUnlockPattern.specified=true");

        // Get all the mChallengeQuestWorldList where stageUnlockPattern is null
        defaultMChallengeQuestWorldShouldNotBeFound("stageUnlockPattern.specified=false");
    }

    @Test
    @Transactional
    public void getAllMChallengeQuestWorldsByStageUnlockPatternIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mChallengeQuestWorldRepository.saveAndFlush(mChallengeQuestWorld);

        // Get all the mChallengeQuestWorldList where stageUnlockPattern greater than or equals to DEFAULT_STAGE_UNLOCK_PATTERN
        defaultMChallengeQuestWorldShouldBeFound("stageUnlockPattern.greaterOrEqualThan=" + DEFAULT_STAGE_UNLOCK_PATTERN);

        // Get all the mChallengeQuestWorldList where stageUnlockPattern greater than or equals to UPDATED_STAGE_UNLOCK_PATTERN
        defaultMChallengeQuestWorldShouldNotBeFound("stageUnlockPattern.greaterOrEqualThan=" + UPDATED_STAGE_UNLOCK_PATTERN);
    }

    @Test
    @Transactional
    public void getAllMChallengeQuestWorldsByStageUnlockPatternIsLessThanSomething() throws Exception {
        // Initialize the database
        mChallengeQuestWorldRepository.saveAndFlush(mChallengeQuestWorld);

        // Get all the mChallengeQuestWorldList where stageUnlockPattern less than or equals to DEFAULT_STAGE_UNLOCK_PATTERN
        defaultMChallengeQuestWorldShouldNotBeFound("stageUnlockPattern.lessThan=" + DEFAULT_STAGE_UNLOCK_PATTERN);

        // Get all the mChallengeQuestWorldList where stageUnlockPattern less than or equals to UPDATED_STAGE_UNLOCK_PATTERN
        defaultMChallengeQuestWorldShouldBeFound("stageUnlockPattern.lessThan=" + UPDATED_STAGE_UNLOCK_PATTERN);
    }


    @Test
    @Transactional
    public void getAllMChallengeQuestWorldsBySpecialRewardContentTypeIsEqualToSomething() throws Exception {
        // Initialize the database
        mChallengeQuestWorldRepository.saveAndFlush(mChallengeQuestWorld);

        // Get all the mChallengeQuestWorldList where specialRewardContentType equals to DEFAULT_SPECIAL_REWARD_CONTENT_TYPE
        defaultMChallengeQuestWorldShouldBeFound("specialRewardContentType.equals=" + DEFAULT_SPECIAL_REWARD_CONTENT_TYPE);

        // Get all the mChallengeQuestWorldList where specialRewardContentType equals to UPDATED_SPECIAL_REWARD_CONTENT_TYPE
        defaultMChallengeQuestWorldShouldNotBeFound("specialRewardContentType.equals=" + UPDATED_SPECIAL_REWARD_CONTENT_TYPE);
    }

    @Test
    @Transactional
    public void getAllMChallengeQuestWorldsBySpecialRewardContentTypeIsInShouldWork() throws Exception {
        // Initialize the database
        mChallengeQuestWorldRepository.saveAndFlush(mChallengeQuestWorld);

        // Get all the mChallengeQuestWorldList where specialRewardContentType in DEFAULT_SPECIAL_REWARD_CONTENT_TYPE or UPDATED_SPECIAL_REWARD_CONTENT_TYPE
        defaultMChallengeQuestWorldShouldBeFound("specialRewardContentType.in=" + DEFAULT_SPECIAL_REWARD_CONTENT_TYPE + "," + UPDATED_SPECIAL_REWARD_CONTENT_TYPE);

        // Get all the mChallengeQuestWorldList where specialRewardContentType equals to UPDATED_SPECIAL_REWARD_CONTENT_TYPE
        defaultMChallengeQuestWorldShouldNotBeFound("specialRewardContentType.in=" + UPDATED_SPECIAL_REWARD_CONTENT_TYPE);
    }

    @Test
    @Transactional
    public void getAllMChallengeQuestWorldsBySpecialRewardContentTypeIsNullOrNotNull() throws Exception {
        // Initialize the database
        mChallengeQuestWorldRepository.saveAndFlush(mChallengeQuestWorld);

        // Get all the mChallengeQuestWorldList where specialRewardContentType is not null
        defaultMChallengeQuestWorldShouldBeFound("specialRewardContentType.specified=true");

        // Get all the mChallengeQuestWorldList where specialRewardContentType is null
        defaultMChallengeQuestWorldShouldNotBeFound("specialRewardContentType.specified=false");
    }

    @Test
    @Transactional
    public void getAllMChallengeQuestWorldsBySpecialRewardContentTypeIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mChallengeQuestWorldRepository.saveAndFlush(mChallengeQuestWorld);

        // Get all the mChallengeQuestWorldList where specialRewardContentType greater than or equals to DEFAULT_SPECIAL_REWARD_CONTENT_TYPE
        defaultMChallengeQuestWorldShouldBeFound("specialRewardContentType.greaterOrEqualThan=" + DEFAULT_SPECIAL_REWARD_CONTENT_TYPE);

        // Get all the mChallengeQuestWorldList where specialRewardContentType greater than or equals to UPDATED_SPECIAL_REWARD_CONTENT_TYPE
        defaultMChallengeQuestWorldShouldNotBeFound("specialRewardContentType.greaterOrEqualThan=" + UPDATED_SPECIAL_REWARD_CONTENT_TYPE);
    }

    @Test
    @Transactional
    public void getAllMChallengeQuestWorldsBySpecialRewardContentTypeIsLessThanSomething() throws Exception {
        // Initialize the database
        mChallengeQuestWorldRepository.saveAndFlush(mChallengeQuestWorld);

        // Get all the mChallengeQuestWorldList where specialRewardContentType less than or equals to DEFAULT_SPECIAL_REWARD_CONTENT_TYPE
        defaultMChallengeQuestWorldShouldNotBeFound("specialRewardContentType.lessThan=" + DEFAULT_SPECIAL_REWARD_CONTENT_TYPE);

        // Get all the mChallengeQuestWorldList where specialRewardContentType less than or equals to UPDATED_SPECIAL_REWARD_CONTENT_TYPE
        defaultMChallengeQuestWorldShouldBeFound("specialRewardContentType.lessThan=" + UPDATED_SPECIAL_REWARD_CONTENT_TYPE);
    }


    @Test
    @Transactional
    public void getAllMChallengeQuestWorldsBySpecialRewardContentIdIsEqualToSomething() throws Exception {
        // Initialize the database
        mChallengeQuestWorldRepository.saveAndFlush(mChallengeQuestWorld);

        // Get all the mChallengeQuestWorldList where specialRewardContentId equals to DEFAULT_SPECIAL_REWARD_CONTENT_ID
        defaultMChallengeQuestWorldShouldBeFound("specialRewardContentId.equals=" + DEFAULT_SPECIAL_REWARD_CONTENT_ID);

        // Get all the mChallengeQuestWorldList where specialRewardContentId equals to UPDATED_SPECIAL_REWARD_CONTENT_ID
        defaultMChallengeQuestWorldShouldNotBeFound("specialRewardContentId.equals=" + UPDATED_SPECIAL_REWARD_CONTENT_ID);
    }

    @Test
    @Transactional
    public void getAllMChallengeQuestWorldsBySpecialRewardContentIdIsInShouldWork() throws Exception {
        // Initialize the database
        mChallengeQuestWorldRepository.saveAndFlush(mChallengeQuestWorld);

        // Get all the mChallengeQuestWorldList where specialRewardContentId in DEFAULT_SPECIAL_REWARD_CONTENT_ID or UPDATED_SPECIAL_REWARD_CONTENT_ID
        defaultMChallengeQuestWorldShouldBeFound("specialRewardContentId.in=" + DEFAULT_SPECIAL_REWARD_CONTENT_ID + "," + UPDATED_SPECIAL_REWARD_CONTENT_ID);

        // Get all the mChallengeQuestWorldList where specialRewardContentId equals to UPDATED_SPECIAL_REWARD_CONTENT_ID
        defaultMChallengeQuestWorldShouldNotBeFound("specialRewardContentId.in=" + UPDATED_SPECIAL_REWARD_CONTENT_ID);
    }

    @Test
    @Transactional
    public void getAllMChallengeQuestWorldsBySpecialRewardContentIdIsNullOrNotNull() throws Exception {
        // Initialize the database
        mChallengeQuestWorldRepository.saveAndFlush(mChallengeQuestWorld);

        // Get all the mChallengeQuestWorldList where specialRewardContentId is not null
        defaultMChallengeQuestWorldShouldBeFound("specialRewardContentId.specified=true");

        // Get all the mChallengeQuestWorldList where specialRewardContentId is null
        defaultMChallengeQuestWorldShouldNotBeFound("specialRewardContentId.specified=false");
    }

    @Test
    @Transactional
    public void getAllMChallengeQuestWorldsBySpecialRewardContentIdIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mChallengeQuestWorldRepository.saveAndFlush(mChallengeQuestWorld);

        // Get all the mChallengeQuestWorldList where specialRewardContentId greater than or equals to DEFAULT_SPECIAL_REWARD_CONTENT_ID
        defaultMChallengeQuestWorldShouldBeFound("specialRewardContentId.greaterOrEqualThan=" + DEFAULT_SPECIAL_REWARD_CONTENT_ID);

        // Get all the mChallengeQuestWorldList where specialRewardContentId greater than or equals to UPDATED_SPECIAL_REWARD_CONTENT_ID
        defaultMChallengeQuestWorldShouldNotBeFound("specialRewardContentId.greaterOrEqualThan=" + UPDATED_SPECIAL_REWARD_CONTENT_ID);
    }

    @Test
    @Transactional
    public void getAllMChallengeQuestWorldsBySpecialRewardContentIdIsLessThanSomething() throws Exception {
        // Initialize the database
        mChallengeQuestWorldRepository.saveAndFlush(mChallengeQuestWorld);

        // Get all the mChallengeQuestWorldList where specialRewardContentId less than or equals to DEFAULT_SPECIAL_REWARD_CONTENT_ID
        defaultMChallengeQuestWorldShouldNotBeFound("specialRewardContentId.lessThan=" + DEFAULT_SPECIAL_REWARD_CONTENT_ID);

        // Get all the mChallengeQuestWorldList where specialRewardContentId less than or equals to UPDATED_SPECIAL_REWARD_CONTENT_ID
        defaultMChallengeQuestWorldShouldBeFound("specialRewardContentId.lessThan=" + UPDATED_SPECIAL_REWARD_CONTENT_ID);
    }


    @Test
    @Transactional
    public void getAllMChallengeQuestWorldsByIsEnableCoopIsEqualToSomething() throws Exception {
        // Initialize the database
        mChallengeQuestWorldRepository.saveAndFlush(mChallengeQuestWorld);

        // Get all the mChallengeQuestWorldList where isEnableCoop equals to DEFAULT_IS_ENABLE_COOP
        defaultMChallengeQuestWorldShouldBeFound("isEnableCoop.equals=" + DEFAULT_IS_ENABLE_COOP);

        // Get all the mChallengeQuestWorldList where isEnableCoop equals to UPDATED_IS_ENABLE_COOP
        defaultMChallengeQuestWorldShouldNotBeFound("isEnableCoop.equals=" + UPDATED_IS_ENABLE_COOP);
    }

    @Test
    @Transactional
    public void getAllMChallengeQuestWorldsByIsEnableCoopIsInShouldWork() throws Exception {
        // Initialize the database
        mChallengeQuestWorldRepository.saveAndFlush(mChallengeQuestWorld);

        // Get all the mChallengeQuestWorldList where isEnableCoop in DEFAULT_IS_ENABLE_COOP or UPDATED_IS_ENABLE_COOP
        defaultMChallengeQuestWorldShouldBeFound("isEnableCoop.in=" + DEFAULT_IS_ENABLE_COOP + "," + UPDATED_IS_ENABLE_COOP);

        // Get all the mChallengeQuestWorldList where isEnableCoop equals to UPDATED_IS_ENABLE_COOP
        defaultMChallengeQuestWorldShouldNotBeFound("isEnableCoop.in=" + UPDATED_IS_ENABLE_COOP);
    }

    @Test
    @Transactional
    public void getAllMChallengeQuestWorldsByIsEnableCoopIsNullOrNotNull() throws Exception {
        // Initialize the database
        mChallengeQuestWorldRepository.saveAndFlush(mChallengeQuestWorld);

        // Get all the mChallengeQuestWorldList where isEnableCoop is not null
        defaultMChallengeQuestWorldShouldBeFound("isEnableCoop.specified=true");

        // Get all the mChallengeQuestWorldList where isEnableCoop is null
        defaultMChallengeQuestWorldShouldNotBeFound("isEnableCoop.specified=false");
    }

    @Test
    @Transactional
    public void getAllMChallengeQuestWorldsByIsEnableCoopIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mChallengeQuestWorldRepository.saveAndFlush(mChallengeQuestWorld);

        // Get all the mChallengeQuestWorldList where isEnableCoop greater than or equals to DEFAULT_IS_ENABLE_COOP
        defaultMChallengeQuestWorldShouldBeFound("isEnableCoop.greaterOrEqualThan=" + DEFAULT_IS_ENABLE_COOP);

        // Get all the mChallengeQuestWorldList where isEnableCoop greater than or equals to UPDATED_IS_ENABLE_COOP
        defaultMChallengeQuestWorldShouldNotBeFound("isEnableCoop.greaterOrEqualThan=" + UPDATED_IS_ENABLE_COOP);
    }

    @Test
    @Transactional
    public void getAllMChallengeQuestWorldsByIsEnableCoopIsLessThanSomething() throws Exception {
        // Initialize the database
        mChallengeQuestWorldRepository.saveAndFlush(mChallengeQuestWorld);

        // Get all the mChallengeQuestWorldList where isEnableCoop less than or equals to DEFAULT_IS_ENABLE_COOP
        defaultMChallengeQuestWorldShouldNotBeFound("isEnableCoop.lessThan=" + DEFAULT_IS_ENABLE_COOP);

        // Get all the mChallengeQuestWorldList where isEnableCoop less than or equals to UPDATED_IS_ENABLE_COOP
        defaultMChallengeQuestWorldShouldBeFound("isEnableCoop.lessThan=" + UPDATED_IS_ENABLE_COOP);
    }


    @Test
    @Transactional
    public void getAllMChallengeQuestWorldsByMChallengeQuestStageIsEqualToSomething() throws Exception {
        // Initialize the database
        MChallengeQuestStage mChallengeQuestStage = MChallengeQuestStageResourceIT.createEntity(em);
        em.persist(mChallengeQuestStage);
        em.flush();
        mChallengeQuestWorld.addMChallengeQuestStage(mChallengeQuestStage);
        mChallengeQuestWorldRepository.saveAndFlush(mChallengeQuestWorld);
        Long mChallengeQuestStageId = mChallengeQuestStage.getId();

        // Get all the mChallengeQuestWorldList where mChallengeQuestStage equals to mChallengeQuestStageId
        defaultMChallengeQuestWorldShouldBeFound("mChallengeQuestStageId.equals=" + mChallengeQuestStageId);

        // Get all the mChallengeQuestWorldList where mChallengeQuestStage equals to mChallengeQuestStageId + 1
        defaultMChallengeQuestWorldShouldNotBeFound("mChallengeQuestStageId.equals=" + (mChallengeQuestStageId + 1));
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultMChallengeQuestWorldShouldBeFound(String filter) throws Exception {
        restMChallengeQuestWorldMockMvc.perform(get("/api/m-challenge-quest-worlds?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(mChallengeQuestWorld.getId().intValue())))
            .andExpect(jsonPath("$.[*].setId").value(hasItem(DEFAULT_SET_ID)))
            .andExpect(jsonPath("$.[*].number").value(hasItem(DEFAULT_NUMBER)))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME.toString())))
            .andExpect(jsonPath("$.[*].imagePath").value(hasItem(DEFAULT_IMAGE_PATH.toString())))
            .andExpect(jsonPath("$.[*].backgroundImagePath").value(hasItem(DEFAULT_BACKGROUND_IMAGE_PATH.toString())))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION.toString())))
            .andExpect(jsonPath("$.[*].stageUnlockPattern").value(hasItem(DEFAULT_STAGE_UNLOCK_PATTERN)))
            .andExpect(jsonPath("$.[*].arousalBanner").value(hasItem(DEFAULT_AROUSAL_BANNER.toString())))
            .andExpect(jsonPath("$.[*].specialRewardContentType").value(hasItem(DEFAULT_SPECIAL_REWARD_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].specialRewardContentId").value(hasItem(DEFAULT_SPECIAL_REWARD_CONTENT_ID)))
            .andExpect(jsonPath("$.[*].isEnableCoop").value(hasItem(DEFAULT_IS_ENABLE_COOP)));

        // Check, that the count call also returns 1
        restMChallengeQuestWorldMockMvc.perform(get("/api/m-challenge-quest-worlds/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultMChallengeQuestWorldShouldNotBeFound(String filter) throws Exception {
        restMChallengeQuestWorldMockMvc.perform(get("/api/m-challenge-quest-worlds?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restMChallengeQuestWorldMockMvc.perform(get("/api/m-challenge-quest-worlds/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("0"));
    }


    @Test
    @Transactional
    public void getNonExistingMChallengeQuestWorld() throws Exception {
        // Get the mChallengeQuestWorld
        restMChallengeQuestWorldMockMvc.perform(get("/api/m-challenge-quest-worlds/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateMChallengeQuestWorld() throws Exception {
        // Initialize the database
        mChallengeQuestWorldRepository.saveAndFlush(mChallengeQuestWorld);

        int databaseSizeBeforeUpdate = mChallengeQuestWorldRepository.findAll().size();

        // Update the mChallengeQuestWorld
        MChallengeQuestWorld updatedMChallengeQuestWorld = mChallengeQuestWorldRepository.findById(mChallengeQuestWorld.getId()).get();
        // Disconnect from session so that the updates on updatedMChallengeQuestWorld are not directly saved in db
        em.detach(updatedMChallengeQuestWorld);
        updatedMChallengeQuestWorld
            .setId(UPDATED_SET_ID)
            .number(UPDATED_NUMBER)
            .name(UPDATED_NAME)
            .imagePath(UPDATED_IMAGE_PATH)
            .backgroundImagePath(UPDATED_BACKGROUND_IMAGE_PATH)
            .description(UPDATED_DESCRIPTION)
            .stageUnlockPattern(UPDATED_STAGE_UNLOCK_PATTERN)
            .arousalBanner(UPDATED_AROUSAL_BANNER)
            .specialRewardContentType(UPDATED_SPECIAL_REWARD_CONTENT_TYPE)
            .specialRewardContentId(UPDATED_SPECIAL_REWARD_CONTENT_ID)
            .isEnableCoop(UPDATED_IS_ENABLE_COOP);
        MChallengeQuestWorldDTO mChallengeQuestWorldDTO = mChallengeQuestWorldMapper.toDto(updatedMChallengeQuestWorld);

        restMChallengeQuestWorldMockMvc.perform(put("/api/m-challenge-quest-worlds")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mChallengeQuestWorldDTO)))
            .andExpect(status().isOk());

        // Validate the MChallengeQuestWorld in the database
        List<MChallengeQuestWorld> mChallengeQuestWorldList = mChallengeQuestWorldRepository.findAll();
        assertThat(mChallengeQuestWorldList).hasSize(databaseSizeBeforeUpdate);
        MChallengeQuestWorld testMChallengeQuestWorld = mChallengeQuestWorldList.get(mChallengeQuestWorldList.size() - 1);
        assertThat(testMChallengeQuestWorld.getSetId()).isEqualTo(UPDATED_SET_ID);
        assertThat(testMChallengeQuestWorld.getNumber()).isEqualTo(UPDATED_NUMBER);
        assertThat(testMChallengeQuestWorld.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testMChallengeQuestWorld.getImagePath()).isEqualTo(UPDATED_IMAGE_PATH);
        assertThat(testMChallengeQuestWorld.getBackgroundImagePath()).isEqualTo(UPDATED_BACKGROUND_IMAGE_PATH);
        assertThat(testMChallengeQuestWorld.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
        assertThat(testMChallengeQuestWorld.getStageUnlockPattern()).isEqualTo(UPDATED_STAGE_UNLOCK_PATTERN);
        assertThat(testMChallengeQuestWorld.getArousalBanner()).isEqualTo(UPDATED_AROUSAL_BANNER);
        assertThat(testMChallengeQuestWorld.getSpecialRewardContentType()).isEqualTo(UPDATED_SPECIAL_REWARD_CONTENT_TYPE);
        assertThat(testMChallengeQuestWorld.getSpecialRewardContentId()).isEqualTo(UPDATED_SPECIAL_REWARD_CONTENT_ID);
        assertThat(testMChallengeQuestWorld.getIsEnableCoop()).isEqualTo(UPDATED_IS_ENABLE_COOP);
    }

    @Test
    @Transactional
    public void updateNonExistingMChallengeQuestWorld() throws Exception {
        int databaseSizeBeforeUpdate = mChallengeQuestWorldRepository.findAll().size();

        // Create the MChallengeQuestWorld
        MChallengeQuestWorldDTO mChallengeQuestWorldDTO = mChallengeQuestWorldMapper.toDto(mChallengeQuestWorld);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restMChallengeQuestWorldMockMvc.perform(put("/api/m-challenge-quest-worlds")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mChallengeQuestWorldDTO)))
            .andExpect(status().isBadRequest());

        // Validate the MChallengeQuestWorld in the database
        List<MChallengeQuestWorld> mChallengeQuestWorldList = mChallengeQuestWorldRepository.findAll();
        assertThat(mChallengeQuestWorldList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteMChallengeQuestWorld() throws Exception {
        // Initialize the database
        mChallengeQuestWorldRepository.saveAndFlush(mChallengeQuestWorld);

        int databaseSizeBeforeDelete = mChallengeQuestWorldRepository.findAll().size();

        // Delete the mChallengeQuestWorld
        restMChallengeQuestWorldMockMvc.perform(delete("/api/m-challenge-quest-worlds/{id}", mChallengeQuestWorld.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database is empty
        List<MChallengeQuestWorld> mChallengeQuestWorldList = mChallengeQuestWorldRepository.findAll();
        assertThat(mChallengeQuestWorldList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(MChallengeQuestWorld.class);
        MChallengeQuestWorld mChallengeQuestWorld1 = new MChallengeQuestWorld();
        mChallengeQuestWorld1.setId(1L);
        MChallengeQuestWorld mChallengeQuestWorld2 = new MChallengeQuestWorld();
        mChallengeQuestWorld2.setId(mChallengeQuestWorld1.getId());
        assertThat(mChallengeQuestWorld1).isEqualTo(mChallengeQuestWorld2);
        mChallengeQuestWorld2.setId(2L);
        assertThat(mChallengeQuestWorld1).isNotEqualTo(mChallengeQuestWorld2);
        mChallengeQuestWorld1.setId(null);
        assertThat(mChallengeQuestWorld1).isNotEqualTo(mChallengeQuestWorld2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(MChallengeQuestWorldDTO.class);
        MChallengeQuestWorldDTO mChallengeQuestWorldDTO1 = new MChallengeQuestWorldDTO();
        mChallengeQuestWorldDTO1.setId(1L);
        MChallengeQuestWorldDTO mChallengeQuestWorldDTO2 = new MChallengeQuestWorldDTO();
        assertThat(mChallengeQuestWorldDTO1).isNotEqualTo(mChallengeQuestWorldDTO2);
        mChallengeQuestWorldDTO2.setId(mChallengeQuestWorldDTO1.getId());
        assertThat(mChallengeQuestWorldDTO1).isEqualTo(mChallengeQuestWorldDTO2);
        mChallengeQuestWorldDTO2.setId(2L);
        assertThat(mChallengeQuestWorldDTO1).isNotEqualTo(mChallengeQuestWorldDTO2);
        mChallengeQuestWorldDTO1.setId(null);
        assertThat(mChallengeQuestWorldDTO1).isNotEqualTo(mChallengeQuestWorldDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(mChallengeQuestWorldMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(mChallengeQuestWorldMapper.fromId(null)).isNull();
    }
}
