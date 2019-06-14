package io.shm.tsubasa.web.rest;

import io.shm.tsubasa.TsubasaApp;
import io.shm.tsubasa.domain.MTimeattackQuestWorld;
import io.shm.tsubasa.domain.MTimeattackQuestStage;
import io.shm.tsubasa.repository.MTimeattackQuestWorldRepository;
import io.shm.tsubasa.service.MTimeattackQuestWorldService;
import io.shm.tsubasa.service.dto.MTimeattackQuestWorldDTO;
import io.shm.tsubasa.service.mapper.MTimeattackQuestWorldMapper;
import io.shm.tsubasa.web.rest.errors.ExceptionTranslator;
import io.shm.tsubasa.service.dto.MTimeattackQuestWorldCriteria;
import io.shm.tsubasa.service.MTimeattackQuestWorldQueryService;

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
 * Integration tests for the {@Link MTimeattackQuestWorldResource} REST controller.
 */
@SpringBootTest(classes = TsubasaApp.class)
public class MTimeattackQuestWorldResourceIT {

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
    private MTimeattackQuestWorldRepository mTimeattackQuestWorldRepository;

    @Autowired
    private MTimeattackQuestWorldMapper mTimeattackQuestWorldMapper;

    @Autowired
    private MTimeattackQuestWorldService mTimeattackQuestWorldService;

    @Autowired
    private MTimeattackQuestWorldQueryService mTimeattackQuestWorldQueryService;

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

    private MockMvc restMTimeattackQuestWorldMockMvc;

    private MTimeattackQuestWorld mTimeattackQuestWorld;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final MTimeattackQuestWorldResource mTimeattackQuestWorldResource = new MTimeattackQuestWorldResource(mTimeattackQuestWorldService, mTimeattackQuestWorldQueryService);
        this.restMTimeattackQuestWorldMockMvc = MockMvcBuilders.standaloneSetup(mTimeattackQuestWorldResource)
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
    public static MTimeattackQuestWorld createEntity(EntityManager em) {
        MTimeattackQuestWorld mTimeattackQuestWorld = new MTimeattackQuestWorld()
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
        return mTimeattackQuestWorld;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static MTimeattackQuestWorld createUpdatedEntity(EntityManager em) {
        MTimeattackQuestWorld mTimeattackQuestWorld = new MTimeattackQuestWorld()
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
        return mTimeattackQuestWorld;
    }

    @BeforeEach
    public void initTest() {
        mTimeattackQuestWorld = createEntity(em);
    }

    @Test
    @Transactional
    public void createMTimeattackQuestWorld() throws Exception {
        int databaseSizeBeforeCreate = mTimeattackQuestWorldRepository.findAll().size();

        // Create the MTimeattackQuestWorld
        MTimeattackQuestWorldDTO mTimeattackQuestWorldDTO = mTimeattackQuestWorldMapper.toDto(mTimeattackQuestWorld);
        restMTimeattackQuestWorldMockMvc.perform(post("/api/m-timeattack-quest-worlds")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mTimeattackQuestWorldDTO)))
            .andExpect(status().isCreated());

        // Validate the MTimeattackQuestWorld in the database
        List<MTimeattackQuestWorld> mTimeattackQuestWorldList = mTimeattackQuestWorldRepository.findAll();
        assertThat(mTimeattackQuestWorldList).hasSize(databaseSizeBeforeCreate + 1);
        MTimeattackQuestWorld testMTimeattackQuestWorld = mTimeattackQuestWorldList.get(mTimeattackQuestWorldList.size() - 1);
        assertThat(testMTimeattackQuestWorld.getSetId()).isEqualTo(DEFAULT_SET_ID);
        assertThat(testMTimeattackQuestWorld.getNumber()).isEqualTo(DEFAULT_NUMBER);
        assertThat(testMTimeattackQuestWorld.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testMTimeattackQuestWorld.getImagePath()).isEqualTo(DEFAULT_IMAGE_PATH);
        assertThat(testMTimeattackQuestWorld.getBackgroundImagePath()).isEqualTo(DEFAULT_BACKGROUND_IMAGE_PATH);
        assertThat(testMTimeattackQuestWorld.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
        assertThat(testMTimeattackQuestWorld.getStageUnlockPattern()).isEqualTo(DEFAULT_STAGE_UNLOCK_PATTERN);
        assertThat(testMTimeattackQuestWorld.getArousalBanner()).isEqualTo(DEFAULT_AROUSAL_BANNER);
        assertThat(testMTimeattackQuestWorld.getSpecialRewardContentType()).isEqualTo(DEFAULT_SPECIAL_REWARD_CONTENT_TYPE);
        assertThat(testMTimeattackQuestWorld.getSpecialRewardContentId()).isEqualTo(DEFAULT_SPECIAL_REWARD_CONTENT_ID);
        assertThat(testMTimeattackQuestWorld.getIsEnableCoop()).isEqualTo(DEFAULT_IS_ENABLE_COOP);
    }

    @Test
    @Transactional
    public void createMTimeattackQuestWorldWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = mTimeattackQuestWorldRepository.findAll().size();

        // Create the MTimeattackQuestWorld with an existing ID
        mTimeattackQuestWorld.setId(1L);
        MTimeattackQuestWorldDTO mTimeattackQuestWorldDTO = mTimeattackQuestWorldMapper.toDto(mTimeattackQuestWorld);

        // An entity with an existing ID cannot be created, so this API call must fail
        restMTimeattackQuestWorldMockMvc.perform(post("/api/m-timeattack-quest-worlds")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mTimeattackQuestWorldDTO)))
            .andExpect(status().isBadRequest());

        // Validate the MTimeattackQuestWorld in the database
        List<MTimeattackQuestWorld> mTimeattackQuestWorldList = mTimeattackQuestWorldRepository.findAll();
        assertThat(mTimeattackQuestWorldList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkSetIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = mTimeattackQuestWorldRepository.findAll().size();
        // set the field null
        mTimeattackQuestWorld.setSetId(null);

        // Create the MTimeattackQuestWorld, which fails.
        MTimeattackQuestWorldDTO mTimeattackQuestWorldDTO = mTimeattackQuestWorldMapper.toDto(mTimeattackQuestWorld);

        restMTimeattackQuestWorldMockMvc.perform(post("/api/m-timeattack-quest-worlds")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mTimeattackQuestWorldDTO)))
            .andExpect(status().isBadRequest());

        List<MTimeattackQuestWorld> mTimeattackQuestWorldList = mTimeattackQuestWorldRepository.findAll();
        assertThat(mTimeattackQuestWorldList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkNumberIsRequired() throws Exception {
        int databaseSizeBeforeTest = mTimeattackQuestWorldRepository.findAll().size();
        // set the field null
        mTimeattackQuestWorld.setNumber(null);

        // Create the MTimeattackQuestWorld, which fails.
        MTimeattackQuestWorldDTO mTimeattackQuestWorldDTO = mTimeattackQuestWorldMapper.toDto(mTimeattackQuestWorld);

        restMTimeattackQuestWorldMockMvc.perform(post("/api/m-timeattack-quest-worlds")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mTimeattackQuestWorldDTO)))
            .andExpect(status().isBadRequest());

        List<MTimeattackQuestWorld> mTimeattackQuestWorldList = mTimeattackQuestWorldRepository.findAll();
        assertThat(mTimeattackQuestWorldList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkStageUnlockPatternIsRequired() throws Exception {
        int databaseSizeBeforeTest = mTimeattackQuestWorldRepository.findAll().size();
        // set the field null
        mTimeattackQuestWorld.setStageUnlockPattern(null);

        // Create the MTimeattackQuestWorld, which fails.
        MTimeattackQuestWorldDTO mTimeattackQuestWorldDTO = mTimeattackQuestWorldMapper.toDto(mTimeattackQuestWorld);

        restMTimeattackQuestWorldMockMvc.perform(post("/api/m-timeattack-quest-worlds")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mTimeattackQuestWorldDTO)))
            .andExpect(status().isBadRequest());

        List<MTimeattackQuestWorld> mTimeattackQuestWorldList = mTimeattackQuestWorldRepository.findAll();
        assertThat(mTimeattackQuestWorldList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkIsEnableCoopIsRequired() throws Exception {
        int databaseSizeBeforeTest = mTimeattackQuestWorldRepository.findAll().size();
        // set the field null
        mTimeattackQuestWorld.setIsEnableCoop(null);

        // Create the MTimeattackQuestWorld, which fails.
        MTimeattackQuestWorldDTO mTimeattackQuestWorldDTO = mTimeattackQuestWorldMapper.toDto(mTimeattackQuestWorld);

        restMTimeattackQuestWorldMockMvc.perform(post("/api/m-timeattack-quest-worlds")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mTimeattackQuestWorldDTO)))
            .andExpect(status().isBadRequest());

        List<MTimeattackQuestWorld> mTimeattackQuestWorldList = mTimeattackQuestWorldRepository.findAll();
        assertThat(mTimeattackQuestWorldList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllMTimeattackQuestWorlds() throws Exception {
        // Initialize the database
        mTimeattackQuestWorldRepository.saveAndFlush(mTimeattackQuestWorld);

        // Get all the mTimeattackQuestWorldList
        restMTimeattackQuestWorldMockMvc.perform(get("/api/m-timeattack-quest-worlds?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(mTimeattackQuestWorld.getId().intValue())))
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
    public void getMTimeattackQuestWorld() throws Exception {
        // Initialize the database
        mTimeattackQuestWorldRepository.saveAndFlush(mTimeattackQuestWorld);

        // Get the mTimeattackQuestWorld
        restMTimeattackQuestWorldMockMvc.perform(get("/api/m-timeattack-quest-worlds/{id}", mTimeattackQuestWorld.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(mTimeattackQuestWorld.getId().intValue()))
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
    public void getAllMTimeattackQuestWorldsBySetIdIsEqualToSomething() throws Exception {
        // Initialize the database
        mTimeattackQuestWorldRepository.saveAndFlush(mTimeattackQuestWorld);

        // Get all the mTimeattackQuestWorldList where setId equals to DEFAULT_SET_ID
        defaultMTimeattackQuestWorldShouldBeFound("setId.equals=" + DEFAULT_SET_ID);

        // Get all the mTimeattackQuestWorldList where setId equals to UPDATED_SET_ID
        defaultMTimeattackQuestWorldShouldNotBeFound("setId.equals=" + UPDATED_SET_ID);
    }

    @Test
    @Transactional
    public void getAllMTimeattackQuestWorldsBySetIdIsInShouldWork() throws Exception {
        // Initialize the database
        mTimeattackQuestWorldRepository.saveAndFlush(mTimeattackQuestWorld);

        // Get all the mTimeattackQuestWorldList where setId in DEFAULT_SET_ID or UPDATED_SET_ID
        defaultMTimeattackQuestWorldShouldBeFound("setId.in=" + DEFAULT_SET_ID + "," + UPDATED_SET_ID);

        // Get all the mTimeattackQuestWorldList where setId equals to UPDATED_SET_ID
        defaultMTimeattackQuestWorldShouldNotBeFound("setId.in=" + UPDATED_SET_ID);
    }

    @Test
    @Transactional
    public void getAllMTimeattackQuestWorldsBySetIdIsNullOrNotNull() throws Exception {
        // Initialize the database
        mTimeattackQuestWorldRepository.saveAndFlush(mTimeattackQuestWorld);

        // Get all the mTimeattackQuestWorldList where setId is not null
        defaultMTimeattackQuestWorldShouldBeFound("setId.specified=true");

        // Get all the mTimeattackQuestWorldList where setId is null
        defaultMTimeattackQuestWorldShouldNotBeFound("setId.specified=false");
    }

    @Test
    @Transactional
    public void getAllMTimeattackQuestWorldsBySetIdIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mTimeattackQuestWorldRepository.saveAndFlush(mTimeattackQuestWorld);

        // Get all the mTimeattackQuestWorldList where setId greater than or equals to DEFAULT_SET_ID
        defaultMTimeattackQuestWorldShouldBeFound("setId.greaterOrEqualThan=" + DEFAULT_SET_ID);

        // Get all the mTimeattackQuestWorldList where setId greater than or equals to UPDATED_SET_ID
        defaultMTimeattackQuestWorldShouldNotBeFound("setId.greaterOrEqualThan=" + UPDATED_SET_ID);
    }

    @Test
    @Transactional
    public void getAllMTimeattackQuestWorldsBySetIdIsLessThanSomething() throws Exception {
        // Initialize the database
        mTimeattackQuestWorldRepository.saveAndFlush(mTimeattackQuestWorld);

        // Get all the mTimeattackQuestWorldList where setId less than or equals to DEFAULT_SET_ID
        defaultMTimeattackQuestWorldShouldNotBeFound("setId.lessThan=" + DEFAULT_SET_ID);

        // Get all the mTimeattackQuestWorldList where setId less than or equals to UPDATED_SET_ID
        defaultMTimeattackQuestWorldShouldBeFound("setId.lessThan=" + UPDATED_SET_ID);
    }


    @Test
    @Transactional
    public void getAllMTimeattackQuestWorldsByNumberIsEqualToSomething() throws Exception {
        // Initialize the database
        mTimeattackQuestWorldRepository.saveAndFlush(mTimeattackQuestWorld);

        // Get all the mTimeattackQuestWorldList where number equals to DEFAULT_NUMBER
        defaultMTimeattackQuestWorldShouldBeFound("number.equals=" + DEFAULT_NUMBER);

        // Get all the mTimeattackQuestWorldList where number equals to UPDATED_NUMBER
        defaultMTimeattackQuestWorldShouldNotBeFound("number.equals=" + UPDATED_NUMBER);
    }

    @Test
    @Transactional
    public void getAllMTimeattackQuestWorldsByNumberIsInShouldWork() throws Exception {
        // Initialize the database
        mTimeattackQuestWorldRepository.saveAndFlush(mTimeattackQuestWorld);

        // Get all the mTimeattackQuestWorldList where number in DEFAULT_NUMBER or UPDATED_NUMBER
        defaultMTimeattackQuestWorldShouldBeFound("number.in=" + DEFAULT_NUMBER + "," + UPDATED_NUMBER);

        // Get all the mTimeattackQuestWorldList where number equals to UPDATED_NUMBER
        defaultMTimeattackQuestWorldShouldNotBeFound("number.in=" + UPDATED_NUMBER);
    }

    @Test
    @Transactional
    public void getAllMTimeattackQuestWorldsByNumberIsNullOrNotNull() throws Exception {
        // Initialize the database
        mTimeattackQuestWorldRepository.saveAndFlush(mTimeattackQuestWorld);

        // Get all the mTimeattackQuestWorldList where number is not null
        defaultMTimeattackQuestWorldShouldBeFound("number.specified=true");

        // Get all the mTimeattackQuestWorldList where number is null
        defaultMTimeattackQuestWorldShouldNotBeFound("number.specified=false");
    }

    @Test
    @Transactional
    public void getAllMTimeattackQuestWorldsByNumberIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mTimeattackQuestWorldRepository.saveAndFlush(mTimeattackQuestWorld);

        // Get all the mTimeattackQuestWorldList where number greater than or equals to DEFAULT_NUMBER
        defaultMTimeattackQuestWorldShouldBeFound("number.greaterOrEqualThan=" + DEFAULT_NUMBER);

        // Get all the mTimeattackQuestWorldList where number greater than or equals to UPDATED_NUMBER
        defaultMTimeattackQuestWorldShouldNotBeFound("number.greaterOrEqualThan=" + UPDATED_NUMBER);
    }

    @Test
    @Transactional
    public void getAllMTimeattackQuestWorldsByNumberIsLessThanSomething() throws Exception {
        // Initialize the database
        mTimeattackQuestWorldRepository.saveAndFlush(mTimeattackQuestWorld);

        // Get all the mTimeattackQuestWorldList where number less than or equals to DEFAULT_NUMBER
        defaultMTimeattackQuestWorldShouldNotBeFound("number.lessThan=" + DEFAULT_NUMBER);

        // Get all the mTimeattackQuestWorldList where number less than or equals to UPDATED_NUMBER
        defaultMTimeattackQuestWorldShouldBeFound("number.lessThan=" + UPDATED_NUMBER);
    }


    @Test
    @Transactional
    public void getAllMTimeattackQuestWorldsByStageUnlockPatternIsEqualToSomething() throws Exception {
        // Initialize the database
        mTimeattackQuestWorldRepository.saveAndFlush(mTimeattackQuestWorld);

        // Get all the mTimeattackQuestWorldList where stageUnlockPattern equals to DEFAULT_STAGE_UNLOCK_PATTERN
        defaultMTimeattackQuestWorldShouldBeFound("stageUnlockPattern.equals=" + DEFAULT_STAGE_UNLOCK_PATTERN);

        // Get all the mTimeattackQuestWorldList where stageUnlockPattern equals to UPDATED_STAGE_UNLOCK_PATTERN
        defaultMTimeattackQuestWorldShouldNotBeFound("stageUnlockPattern.equals=" + UPDATED_STAGE_UNLOCK_PATTERN);
    }

    @Test
    @Transactional
    public void getAllMTimeattackQuestWorldsByStageUnlockPatternIsInShouldWork() throws Exception {
        // Initialize the database
        mTimeattackQuestWorldRepository.saveAndFlush(mTimeattackQuestWorld);

        // Get all the mTimeattackQuestWorldList where stageUnlockPattern in DEFAULT_STAGE_UNLOCK_PATTERN or UPDATED_STAGE_UNLOCK_PATTERN
        defaultMTimeattackQuestWorldShouldBeFound("stageUnlockPattern.in=" + DEFAULT_STAGE_UNLOCK_PATTERN + "," + UPDATED_STAGE_UNLOCK_PATTERN);

        // Get all the mTimeattackQuestWorldList where stageUnlockPattern equals to UPDATED_STAGE_UNLOCK_PATTERN
        defaultMTimeattackQuestWorldShouldNotBeFound("stageUnlockPattern.in=" + UPDATED_STAGE_UNLOCK_PATTERN);
    }

    @Test
    @Transactional
    public void getAllMTimeattackQuestWorldsByStageUnlockPatternIsNullOrNotNull() throws Exception {
        // Initialize the database
        mTimeattackQuestWorldRepository.saveAndFlush(mTimeattackQuestWorld);

        // Get all the mTimeattackQuestWorldList where stageUnlockPattern is not null
        defaultMTimeattackQuestWorldShouldBeFound("stageUnlockPattern.specified=true");

        // Get all the mTimeattackQuestWorldList where stageUnlockPattern is null
        defaultMTimeattackQuestWorldShouldNotBeFound("stageUnlockPattern.specified=false");
    }

    @Test
    @Transactional
    public void getAllMTimeattackQuestWorldsByStageUnlockPatternIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mTimeattackQuestWorldRepository.saveAndFlush(mTimeattackQuestWorld);

        // Get all the mTimeattackQuestWorldList where stageUnlockPattern greater than or equals to DEFAULT_STAGE_UNLOCK_PATTERN
        defaultMTimeattackQuestWorldShouldBeFound("stageUnlockPattern.greaterOrEqualThan=" + DEFAULT_STAGE_UNLOCK_PATTERN);

        // Get all the mTimeattackQuestWorldList where stageUnlockPattern greater than or equals to UPDATED_STAGE_UNLOCK_PATTERN
        defaultMTimeattackQuestWorldShouldNotBeFound("stageUnlockPattern.greaterOrEqualThan=" + UPDATED_STAGE_UNLOCK_PATTERN);
    }

    @Test
    @Transactional
    public void getAllMTimeattackQuestWorldsByStageUnlockPatternIsLessThanSomething() throws Exception {
        // Initialize the database
        mTimeattackQuestWorldRepository.saveAndFlush(mTimeattackQuestWorld);

        // Get all the mTimeattackQuestWorldList where stageUnlockPattern less than or equals to DEFAULT_STAGE_UNLOCK_PATTERN
        defaultMTimeattackQuestWorldShouldNotBeFound("stageUnlockPattern.lessThan=" + DEFAULT_STAGE_UNLOCK_PATTERN);

        // Get all the mTimeattackQuestWorldList where stageUnlockPattern less than or equals to UPDATED_STAGE_UNLOCK_PATTERN
        defaultMTimeattackQuestWorldShouldBeFound("stageUnlockPattern.lessThan=" + UPDATED_STAGE_UNLOCK_PATTERN);
    }


    @Test
    @Transactional
    public void getAllMTimeattackQuestWorldsBySpecialRewardContentTypeIsEqualToSomething() throws Exception {
        // Initialize the database
        mTimeattackQuestWorldRepository.saveAndFlush(mTimeattackQuestWorld);

        // Get all the mTimeattackQuestWorldList where specialRewardContentType equals to DEFAULT_SPECIAL_REWARD_CONTENT_TYPE
        defaultMTimeattackQuestWorldShouldBeFound("specialRewardContentType.equals=" + DEFAULT_SPECIAL_REWARD_CONTENT_TYPE);

        // Get all the mTimeattackQuestWorldList where specialRewardContentType equals to UPDATED_SPECIAL_REWARD_CONTENT_TYPE
        defaultMTimeattackQuestWorldShouldNotBeFound("specialRewardContentType.equals=" + UPDATED_SPECIAL_REWARD_CONTENT_TYPE);
    }

    @Test
    @Transactional
    public void getAllMTimeattackQuestWorldsBySpecialRewardContentTypeIsInShouldWork() throws Exception {
        // Initialize the database
        mTimeattackQuestWorldRepository.saveAndFlush(mTimeattackQuestWorld);

        // Get all the mTimeattackQuestWorldList where specialRewardContentType in DEFAULT_SPECIAL_REWARD_CONTENT_TYPE or UPDATED_SPECIAL_REWARD_CONTENT_TYPE
        defaultMTimeattackQuestWorldShouldBeFound("specialRewardContentType.in=" + DEFAULT_SPECIAL_REWARD_CONTENT_TYPE + "," + UPDATED_SPECIAL_REWARD_CONTENT_TYPE);

        // Get all the mTimeattackQuestWorldList where specialRewardContentType equals to UPDATED_SPECIAL_REWARD_CONTENT_TYPE
        defaultMTimeattackQuestWorldShouldNotBeFound("specialRewardContentType.in=" + UPDATED_SPECIAL_REWARD_CONTENT_TYPE);
    }

    @Test
    @Transactional
    public void getAllMTimeattackQuestWorldsBySpecialRewardContentTypeIsNullOrNotNull() throws Exception {
        // Initialize the database
        mTimeattackQuestWorldRepository.saveAndFlush(mTimeattackQuestWorld);

        // Get all the mTimeattackQuestWorldList where specialRewardContentType is not null
        defaultMTimeattackQuestWorldShouldBeFound("specialRewardContentType.specified=true");

        // Get all the mTimeattackQuestWorldList where specialRewardContentType is null
        defaultMTimeattackQuestWorldShouldNotBeFound("specialRewardContentType.specified=false");
    }

    @Test
    @Transactional
    public void getAllMTimeattackQuestWorldsBySpecialRewardContentTypeIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mTimeattackQuestWorldRepository.saveAndFlush(mTimeattackQuestWorld);

        // Get all the mTimeattackQuestWorldList where specialRewardContentType greater than or equals to DEFAULT_SPECIAL_REWARD_CONTENT_TYPE
        defaultMTimeattackQuestWorldShouldBeFound("specialRewardContentType.greaterOrEqualThan=" + DEFAULT_SPECIAL_REWARD_CONTENT_TYPE);

        // Get all the mTimeattackQuestWorldList where specialRewardContentType greater than or equals to UPDATED_SPECIAL_REWARD_CONTENT_TYPE
        defaultMTimeattackQuestWorldShouldNotBeFound("specialRewardContentType.greaterOrEqualThan=" + UPDATED_SPECIAL_REWARD_CONTENT_TYPE);
    }

    @Test
    @Transactional
    public void getAllMTimeattackQuestWorldsBySpecialRewardContentTypeIsLessThanSomething() throws Exception {
        // Initialize the database
        mTimeattackQuestWorldRepository.saveAndFlush(mTimeattackQuestWorld);

        // Get all the mTimeattackQuestWorldList where specialRewardContentType less than or equals to DEFAULT_SPECIAL_REWARD_CONTENT_TYPE
        defaultMTimeattackQuestWorldShouldNotBeFound("specialRewardContentType.lessThan=" + DEFAULT_SPECIAL_REWARD_CONTENT_TYPE);

        // Get all the mTimeattackQuestWorldList where specialRewardContentType less than or equals to UPDATED_SPECIAL_REWARD_CONTENT_TYPE
        defaultMTimeattackQuestWorldShouldBeFound("specialRewardContentType.lessThan=" + UPDATED_SPECIAL_REWARD_CONTENT_TYPE);
    }


    @Test
    @Transactional
    public void getAllMTimeattackQuestWorldsBySpecialRewardContentIdIsEqualToSomething() throws Exception {
        // Initialize the database
        mTimeattackQuestWorldRepository.saveAndFlush(mTimeattackQuestWorld);

        // Get all the mTimeattackQuestWorldList where specialRewardContentId equals to DEFAULT_SPECIAL_REWARD_CONTENT_ID
        defaultMTimeattackQuestWorldShouldBeFound("specialRewardContentId.equals=" + DEFAULT_SPECIAL_REWARD_CONTENT_ID);

        // Get all the mTimeattackQuestWorldList where specialRewardContentId equals to UPDATED_SPECIAL_REWARD_CONTENT_ID
        defaultMTimeattackQuestWorldShouldNotBeFound("specialRewardContentId.equals=" + UPDATED_SPECIAL_REWARD_CONTENT_ID);
    }

    @Test
    @Transactional
    public void getAllMTimeattackQuestWorldsBySpecialRewardContentIdIsInShouldWork() throws Exception {
        // Initialize the database
        mTimeattackQuestWorldRepository.saveAndFlush(mTimeattackQuestWorld);

        // Get all the mTimeattackQuestWorldList where specialRewardContentId in DEFAULT_SPECIAL_REWARD_CONTENT_ID or UPDATED_SPECIAL_REWARD_CONTENT_ID
        defaultMTimeattackQuestWorldShouldBeFound("specialRewardContentId.in=" + DEFAULT_SPECIAL_REWARD_CONTENT_ID + "," + UPDATED_SPECIAL_REWARD_CONTENT_ID);

        // Get all the mTimeattackQuestWorldList where specialRewardContentId equals to UPDATED_SPECIAL_REWARD_CONTENT_ID
        defaultMTimeattackQuestWorldShouldNotBeFound("specialRewardContentId.in=" + UPDATED_SPECIAL_REWARD_CONTENT_ID);
    }

    @Test
    @Transactional
    public void getAllMTimeattackQuestWorldsBySpecialRewardContentIdIsNullOrNotNull() throws Exception {
        // Initialize the database
        mTimeattackQuestWorldRepository.saveAndFlush(mTimeattackQuestWorld);

        // Get all the mTimeattackQuestWorldList where specialRewardContentId is not null
        defaultMTimeattackQuestWorldShouldBeFound("specialRewardContentId.specified=true");

        // Get all the mTimeattackQuestWorldList where specialRewardContentId is null
        defaultMTimeattackQuestWorldShouldNotBeFound("specialRewardContentId.specified=false");
    }

    @Test
    @Transactional
    public void getAllMTimeattackQuestWorldsBySpecialRewardContentIdIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mTimeattackQuestWorldRepository.saveAndFlush(mTimeattackQuestWorld);

        // Get all the mTimeattackQuestWorldList where specialRewardContentId greater than or equals to DEFAULT_SPECIAL_REWARD_CONTENT_ID
        defaultMTimeattackQuestWorldShouldBeFound("specialRewardContentId.greaterOrEqualThan=" + DEFAULT_SPECIAL_REWARD_CONTENT_ID);

        // Get all the mTimeattackQuestWorldList where specialRewardContentId greater than or equals to UPDATED_SPECIAL_REWARD_CONTENT_ID
        defaultMTimeattackQuestWorldShouldNotBeFound("specialRewardContentId.greaterOrEqualThan=" + UPDATED_SPECIAL_REWARD_CONTENT_ID);
    }

    @Test
    @Transactional
    public void getAllMTimeattackQuestWorldsBySpecialRewardContentIdIsLessThanSomething() throws Exception {
        // Initialize the database
        mTimeattackQuestWorldRepository.saveAndFlush(mTimeattackQuestWorld);

        // Get all the mTimeattackQuestWorldList where specialRewardContentId less than or equals to DEFAULT_SPECIAL_REWARD_CONTENT_ID
        defaultMTimeattackQuestWorldShouldNotBeFound("specialRewardContentId.lessThan=" + DEFAULT_SPECIAL_REWARD_CONTENT_ID);

        // Get all the mTimeattackQuestWorldList where specialRewardContentId less than or equals to UPDATED_SPECIAL_REWARD_CONTENT_ID
        defaultMTimeattackQuestWorldShouldBeFound("specialRewardContentId.lessThan=" + UPDATED_SPECIAL_REWARD_CONTENT_ID);
    }


    @Test
    @Transactional
    public void getAllMTimeattackQuestWorldsByIsEnableCoopIsEqualToSomething() throws Exception {
        // Initialize the database
        mTimeattackQuestWorldRepository.saveAndFlush(mTimeattackQuestWorld);

        // Get all the mTimeattackQuestWorldList where isEnableCoop equals to DEFAULT_IS_ENABLE_COOP
        defaultMTimeattackQuestWorldShouldBeFound("isEnableCoop.equals=" + DEFAULT_IS_ENABLE_COOP);

        // Get all the mTimeattackQuestWorldList where isEnableCoop equals to UPDATED_IS_ENABLE_COOP
        defaultMTimeattackQuestWorldShouldNotBeFound("isEnableCoop.equals=" + UPDATED_IS_ENABLE_COOP);
    }

    @Test
    @Transactional
    public void getAllMTimeattackQuestWorldsByIsEnableCoopIsInShouldWork() throws Exception {
        // Initialize the database
        mTimeattackQuestWorldRepository.saveAndFlush(mTimeattackQuestWorld);

        // Get all the mTimeattackQuestWorldList where isEnableCoop in DEFAULT_IS_ENABLE_COOP or UPDATED_IS_ENABLE_COOP
        defaultMTimeattackQuestWorldShouldBeFound("isEnableCoop.in=" + DEFAULT_IS_ENABLE_COOP + "," + UPDATED_IS_ENABLE_COOP);

        // Get all the mTimeattackQuestWorldList where isEnableCoop equals to UPDATED_IS_ENABLE_COOP
        defaultMTimeattackQuestWorldShouldNotBeFound("isEnableCoop.in=" + UPDATED_IS_ENABLE_COOP);
    }

    @Test
    @Transactional
    public void getAllMTimeattackQuestWorldsByIsEnableCoopIsNullOrNotNull() throws Exception {
        // Initialize the database
        mTimeattackQuestWorldRepository.saveAndFlush(mTimeattackQuestWorld);

        // Get all the mTimeattackQuestWorldList where isEnableCoop is not null
        defaultMTimeattackQuestWorldShouldBeFound("isEnableCoop.specified=true");

        // Get all the mTimeattackQuestWorldList where isEnableCoop is null
        defaultMTimeattackQuestWorldShouldNotBeFound("isEnableCoop.specified=false");
    }

    @Test
    @Transactional
    public void getAllMTimeattackQuestWorldsByIsEnableCoopIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mTimeattackQuestWorldRepository.saveAndFlush(mTimeattackQuestWorld);

        // Get all the mTimeattackQuestWorldList where isEnableCoop greater than or equals to DEFAULT_IS_ENABLE_COOP
        defaultMTimeattackQuestWorldShouldBeFound("isEnableCoop.greaterOrEqualThan=" + DEFAULT_IS_ENABLE_COOP);

        // Get all the mTimeattackQuestWorldList where isEnableCoop greater than or equals to UPDATED_IS_ENABLE_COOP
        defaultMTimeattackQuestWorldShouldNotBeFound("isEnableCoop.greaterOrEqualThan=" + UPDATED_IS_ENABLE_COOP);
    }

    @Test
    @Transactional
    public void getAllMTimeattackQuestWorldsByIsEnableCoopIsLessThanSomething() throws Exception {
        // Initialize the database
        mTimeattackQuestWorldRepository.saveAndFlush(mTimeattackQuestWorld);

        // Get all the mTimeattackQuestWorldList where isEnableCoop less than or equals to DEFAULT_IS_ENABLE_COOP
        defaultMTimeattackQuestWorldShouldNotBeFound("isEnableCoop.lessThan=" + DEFAULT_IS_ENABLE_COOP);

        // Get all the mTimeattackQuestWorldList where isEnableCoop less than or equals to UPDATED_IS_ENABLE_COOP
        defaultMTimeattackQuestWorldShouldBeFound("isEnableCoop.lessThan=" + UPDATED_IS_ENABLE_COOP);
    }


    @Test
    @Transactional
    public void getAllMTimeattackQuestWorldsByMTimeattackQuestStageIsEqualToSomething() throws Exception {
        // Initialize the database
        MTimeattackQuestStage mTimeattackQuestStage = MTimeattackQuestStageResourceIT.createEntity(em);
        em.persist(mTimeattackQuestStage);
        em.flush();
        mTimeattackQuestWorld.addMTimeattackQuestStage(mTimeattackQuestStage);
        mTimeattackQuestWorldRepository.saveAndFlush(mTimeattackQuestWorld);
        Long mTimeattackQuestStageId = mTimeattackQuestStage.getId();

        // Get all the mTimeattackQuestWorldList where mTimeattackQuestStage equals to mTimeattackQuestStageId
        defaultMTimeattackQuestWorldShouldBeFound("mTimeattackQuestStageId.equals=" + mTimeattackQuestStageId);

        // Get all the mTimeattackQuestWorldList where mTimeattackQuestStage equals to mTimeattackQuestStageId + 1
        defaultMTimeattackQuestWorldShouldNotBeFound("mTimeattackQuestStageId.equals=" + (mTimeattackQuestStageId + 1));
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultMTimeattackQuestWorldShouldBeFound(String filter) throws Exception {
        restMTimeattackQuestWorldMockMvc.perform(get("/api/m-timeattack-quest-worlds?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(mTimeattackQuestWorld.getId().intValue())))
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
        restMTimeattackQuestWorldMockMvc.perform(get("/api/m-timeattack-quest-worlds/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultMTimeattackQuestWorldShouldNotBeFound(String filter) throws Exception {
        restMTimeattackQuestWorldMockMvc.perform(get("/api/m-timeattack-quest-worlds?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restMTimeattackQuestWorldMockMvc.perform(get("/api/m-timeattack-quest-worlds/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("0"));
    }


    @Test
    @Transactional
    public void getNonExistingMTimeattackQuestWorld() throws Exception {
        // Get the mTimeattackQuestWorld
        restMTimeattackQuestWorldMockMvc.perform(get("/api/m-timeattack-quest-worlds/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateMTimeattackQuestWorld() throws Exception {
        // Initialize the database
        mTimeattackQuestWorldRepository.saveAndFlush(mTimeattackQuestWorld);

        int databaseSizeBeforeUpdate = mTimeattackQuestWorldRepository.findAll().size();

        // Update the mTimeattackQuestWorld
        MTimeattackQuestWorld updatedMTimeattackQuestWorld = mTimeattackQuestWorldRepository.findById(mTimeattackQuestWorld.getId()).get();
        // Disconnect from session so that the updates on updatedMTimeattackQuestWorld are not directly saved in db
        em.detach(updatedMTimeattackQuestWorld);
        updatedMTimeattackQuestWorld
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
        MTimeattackQuestWorldDTO mTimeattackQuestWorldDTO = mTimeattackQuestWorldMapper.toDto(updatedMTimeattackQuestWorld);

        restMTimeattackQuestWorldMockMvc.perform(put("/api/m-timeattack-quest-worlds")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mTimeattackQuestWorldDTO)))
            .andExpect(status().isOk());

        // Validate the MTimeattackQuestWorld in the database
        List<MTimeattackQuestWorld> mTimeattackQuestWorldList = mTimeattackQuestWorldRepository.findAll();
        assertThat(mTimeattackQuestWorldList).hasSize(databaseSizeBeforeUpdate);
        MTimeattackQuestWorld testMTimeattackQuestWorld = mTimeattackQuestWorldList.get(mTimeattackQuestWorldList.size() - 1);
        assertThat(testMTimeattackQuestWorld.getSetId()).isEqualTo(UPDATED_SET_ID);
        assertThat(testMTimeattackQuestWorld.getNumber()).isEqualTo(UPDATED_NUMBER);
        assertThat(testMTimeattackQuestWorld.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testMTimeattackQuestWorld.getImagePath()).isEqualTo(UPDATED_IMAGE_PATH);
        assertThat(testMTimeattackQuestWorld.getBackgroundImagePath()).isEqualTo(UPDATED_BACKGROUND_IMAGE_PATH);
        assertThat(testMTimeattackQuestWorld.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
        assertThat(testMTimeattackQuestWorld.getStageUnlockPattern()).isEqualTo(UPDATED_STAGE_UNLOCK_PATTERN);
        assertThat(testMTimeattackQuestWorld.getArousalBanner()).isEqualTo(UPDATED_AROUSAL_BANNER);
        assertThat(testMTimeattackQuestWorld.getSpecialRewardContentType()).isEqualTo(UPDATED_SPECIAL_REWARD_CONTENT_TYPE);
        assertThat(testMTimeattackQuestWorld.getSpecialRewardContentId()).isEqualTo(UPDATED_SPECIAL_REWARD_CONTENT_ID);
        assertThat(testMTimeattackQuestWorld.getIsEnableCoop()).isEqualTo(UPDATED_IS_ENABLE_COOP);
    }

    @Test
    @Transactional
    public void updateNonExistingMTimeattackQuestWorld() throws Exception {
        int databaseSizeBeforeUpdate = mTimeattackQuestWorldRepository.findAll().size();

        // Create the MTimeattackQuestWorld
        MTimeattackQuestWorldDTO mTimeattackQuestWorldDTO = mTimeattackQuestWorldMapper.toDto(mTimeattackQuestWorld);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restMTimeattackQuestWorldMockMvc.perform(put("/api/m-timeattack-quest-worlds")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mTimeattackQuestWorldDTO)))
            .andExpect(status().isBadRequest());

        // Validate the MTimeattackQuestWorld in the database
        List<MTimeattackQuestWorld> mTimeattackQuestWorldList = mTimeattackQuestWorldRepository.findAll();
        assertThat(mTimeattackQuestWorldList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteMTimeattackQuestWorld() throws Exception {
        // Initialize the database
        mTimeattackQuestWorldRepository.saveAndFlush(mTimeattackQuestWorld);

        int databaseSizeBeforeDelete = mTimeattackQuestWorldRepository.findAll().size();

        // Delete the mTimeattackQuestWorld
        restMTimeattackQuestWorldMockMvc.perform(delete("/api/m-timeattack-quest-worlds/{id}", mTimeattackQuestWorld.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database is empty
        List<MTimeattackQuestWorld> mTimeattackQuestWorldList = mTimeattackQuestWorldRepository.findAll();
        assertThat(mTimeattackQuestWorldList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(MTimeattackQuestWorld.class);
        MTimeattackQuestWorld mTimeattackQuestWorld1 = new MTimeattackQuestWorld();
        mTimeattackQuestWorld1.setId(1L);
        MTimeattackQuestWorld mTimeattackQuestWorld2 = new MTimeattackQuestWorld();
        mTimeattackQuestWorld2.setId(mTimeattackQuestWorld1.getId());
        assertThat(mTimeattackQuestWorld1).isEqualTo(mTimeattackQuestWorld2);
        mTimeattackQuestWorld2.setId(2L);
        assertThat(mTimeattackQuestWorld1).isNotEqualTo(mTimeattackQuestWorld2);
        mTimeattackQuestWorld1.setId(null);
        assertThat(mTimeattackQuestWorld1).isNotEqualTo(mTimeattackQuestWorld2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(MTimeattackQuestWorldDTO.class);
        MTimeattackQuestWorldDTO mTimeattackQuestWorldDTO1 = new MTimeattackQuestWorldDTO();
        mTimeattackQuestWorldDTO1.setId(1L);
        MTimeattackQuestWorldDTO mTimeattackQuestWorldDTO2 = new MTimeattackQuestWorldDTO();
        assertThat(mTimeattackQuestWorldDTO1).isNotEqualTo(mTimeattackQuestWorldDTO2);
        mTimeattackQuestWorldDTO2.setId(mTimeattackQuestWorldDTO1.getId());
        assertThat(mTimeattackQuestWorldDTO1).isEqualTo(mTimeattackQuestWorldDTO2);
        mTimeattackQuestWorldDTO2.setId(2L);
        assertThat(mTimeattackQuestWorldDTO1).isNotEqualTo(mTimeattackQuestWorldDTO2);
        mTimeattackQuestWorldDTO1.setId(null);
        assertThat(mTimeattackQuestWorldDTO1).isNotEqualTo(mTimeattackQuestWorldDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(mTimeattackQuestWorldMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(mTimeattackQuestWorldMapper.fromId(null)).isNull();
    }
}
