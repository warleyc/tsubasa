package io.shm.tsubasa.web.rest;

import io.shm.tsubasa.TsubasaApp;
import io.shm.tsubasa.domain.MQuestWorld;
import io.shm.tsubasa.domain.MQuestStage;
import io.shm.tsubasa.repository.MQuestWorldRepository;
import io.shm.tsubasa.service.MQuestWorldService;
import io.shm.tsubasa.service.dto.MQuestWorldDTO;
import io.shm.tsubasa.service.mapper.MQuestWorldMapper;
import io.shm.tsubasa.web.rest.errors.ExceptionTranslator;
import io.shm.tsubasa.service.dto.MQuestWorldCriteria;
import io.shm.tsubasa.service.MQuestWorldQueryService;

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
 * Integration tests for the {@Link MQuestWorldResource} REST controller.
 */
@SpringBootTest(classes = TsubasaApp.class)
public class MQuestWorldResourceIT {

    private static final Integer DEFAULT_NUMBER = 1;
    private static final Integer UPDATED_NUMBER = 2;

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final Integer DEFAULT_START_AT = 1;
    private static final Integer UPDATED_START_AT = 2;

    private static final String DEFAULT_IMAGE_PATH = "AAAAAAAAAA";
    private static final String UPDATED_IMAGE_PATH = "BBBBBBBBBB";

    private static final String DEFAULT_BACKGROUND_IMAGE_PATH = "AAAAAAAAAA";
    private static final String UPDATED_BACKGROUND_IMAGE_PATH = "BBBBBBBBBB";

    private static final String DEFAULT_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPTION = "BBBBBBBBBB";

    private static final Integer DEFAULT_STAGE_UNLOCK_PATTERN = 1;
    private static final Integer UPDATED_STAGE_UNLOCK_PATTERN = 2;

    private static final Integer DEFAULT_SPECIAL_REWARD_CONTENT_TYPE = 1;
    private static final Integer UPDATED_SPECIAL_REWARD_CONTENT_TYPE = 2;

    private static final Integer DEFAULT_SPECIAL_REWARD_CONTENT_ID = 1;
    private static final Integer UPDATED_SPECIAL_REWARD_CONTENT_ID = 2;

    private static final Integer DEFAULT_IS_ENABLE_COOP = 1;
    private static final Integer UPDATED_IS_ENABLE_COOP = 2;

    @Autowired
    private MQuestWorldRepository mQuestWorldRepository;

    @Autowired
    private MQuestWorldMapper mQuestWorldMapper;

    @Autowired
    private MQuestWorldService mQuestWorldService;

    @Autowired
    private MQuestWorldQueryService mQuestWorldQueryService;

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

    private MockMvc restMQuestWorldMockMvc;

    private MQuestWorld mQuestWorld;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final MQuestWorldResource mQuestWorldResource = new MQuestWorldResource(mQuestWorldService, mQuestWorldQueryService);
        this.restMQuestWorldMockMvc = MockMvcBuilders.standaloneSetup(mQuestWorldResource)
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
    public static MQuestWorld createEntity(EntityManager em) {
        MQuestWorld mQuestWorld = new MQuestWorld()
            .number(DEFAULT_NUMBER)
            .name(DEFAULT_NAME)
            .startAt(DEFAULT_START_AT)
            .imagePath(DEFAULT_IMAGE_PATH)
            .backgroundImagePath(DEFAULT_BACKGROUND_IMAGE_PATH)
            .description(DEFAULT_DESCRIPTION)
            .stageUnlockPattern(DEFAULT_STAGE_UNLOCK_PATTERN)
            .specialRewardContentType(DEFAULT_SPECIAL_REWARD_CONTENT_TYPE)
            .specialRewardContentId(DEFAULT_SPECIAL_REWARD_CONTENT_ID)
            .isEnableCoop(DEFAULT_IS_ENABLE_COOP);
        return mQuestWorld;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static MQuestWorld createUpdatedEntity(EntityManager em) {
        MQuestWorld mQuestWorld = new MQuestWorld()
            .number(UPDATED_NUMBER)
            .name(UPDATED_NAME)
            .startAt(UPDATED_START_AT)
            .imagePath(UPDATED_IMAGE_PATH)
            .backgroundImagePath(UPDATED_BACKGROUND_IMAGE_PATH)
            .description(UPDATED_DESCRIPTION)
            .stageUnlockPattern(UPDATED_STAGE_UNLOCK_PATTERN)
            .specialRewardContentType(UPDATED_SPECIAL_REWARD_CONTENT_TYPE)
            .specialRewardContentId(UPDATED_SPECIAL_REWARD_CONTENT_ID)
            .isEnableCoop(UPDATED_IS_ENABLE_COOP);
        return mQuestWorld;
    }

    @BeforeEach
    public void initTest() {
        mQuestWorld = createEntity(em);
    }

    @Test
    @Transactional
    public void createMQuestWorld() throws Exception {
        int databaseSizeBeforeCreate = mQuestWorldRepository.findAll().size();

        // Create the MQuestWorld
        MQuestWorldDTO mQuestWorldDTO = mQuestWorldMapper.toDto(mQuestWorld);
        restMQuestWorldMockMvc.perform(post("/api/m-quest-worlds")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mQuestWorldDTO)))
            .andExpect(status().isCreated());

        // Validate the MQuestWorld in the database
        List<MQuestWorld> mQuestWorldList = mQuestWorldRepository.findAll();
        assertThat(mQuestWorldList).hasSize(databaseSizeBeforeCreate + 1);
        MQuestWorld testMQuestWorld = mQuestWorldList.get(mQuestWorldList.size() - 1);
        assertThat(testMQuestWorld.getNumber()).isEqualTo(DEFAULT_NUMBER);
        assertThat(testMQuestWorld.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testMQuestWorld.getStartAt()).isEqualTo(DEFAULT_START_AT);
        assertThat(testMQuestWorld.getImagePath()).isEqualTo(DEFAULT_IMAGE_PATH);
        assertThat(testMQuestWorld.getBackgroundImagePath()).isEqualTo(DEFAULT_BACKGROUND_IMAGE_PATH);
        assertThat(testMQuestWorld.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
        assertThat(testMQuestWorld.getStageUnlockPattern()).isEqualTo(DEFAULT_STAGE_UNLOCK_PATTERN);
        assertThat(testMQuestWorld.getSpecialRewardContentType()).isEqualTo(DEFAULT_SPECIAL_REWARD_CONTENT_TYPE);
        assertThat(testMQuestWorld.getSpecialRewardContentId()).isEqualTo(DEFAULT_SPECIAL_REWARD_CONTENT_ID);
        assertThat(testMQuestWorld.getIsEnableCoop()).isEqualTo(DEFAULT_IS_ENABLE_COOP);
    }

    @Test
    @Transactional
    public void createMQuestWorldWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = mQuestWorldRepository.findAll().size();

        // Create the MQuestWorld with an existing ID
        mQuestWorld.setId(1L);
        MQuestWorldDTO mQuestWorldDTO = mQuestWorldMapper.toDto(mQuestWorld);

        // An entity with an existing ID cannot be created, so this API call must fail
        restMQuestWorldMockMvc.perform(post("/api/m-quest-worlds")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mQuestWorldDTO)))
            .andExpect(status().isBadRequest());

        // Validate the MQuestWorld in the database
        List<MQuestWorld> mQuestWorldList = mQuestWorldRepository.findAll();
        assertThat(mQuestWorldList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkNumberIsRequired() throws Exception {
        int databaseSizeBeforeTest = mQuestWorldRepository.findAll().size();
        // set the field null
        mQuestWorld.setNumber(null);

        // Create the MQuestWorld, which fails.
        MQuestWorldDTO mQuestWorldDTO = mQuestWorldMapper.toDto(mQuestWorld);

        restMQuestWorldMockMvc.perform(post("/api/m-quest-worlds")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mQuestWorldDTO)))
            .andExpect(status().isBadRequest());

        List<MQuestWorld> mQuestWorldList = mQuestWorldRepository.findAll();
        assertThat(mQuestWorldList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkStartAtIsRequired() throws Exception {
        int databaseSizeBeforeTest = mQuestWorldRepository.findAll().size();
        // set the field null
        mQuestWorld.setStartAt(null);

        // Create the MQuestWorld, which fails.
        MQuestWorldDTO mQuestWorldDTO = mQuestWorldMapper.toDto(mQuestWorld);

        restMQuestWorldMockMvc.perform(post("/api/m-quest-worlds")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mQuestWorldDTO)))
            .andExpect(status().isBadRequest());

        List<MQuestWorld> mQuestWorldList = mQuestWorldRepository.findAll();
        assertThat(mQuestWorldList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkStageUnlockPatternIsRequired() throws Exception {
        int databaseSizeBeforeTest = mQuestWorldRepository.findAll().size();
        // set the field null
        mQuestWorld.setStageUnlockPattern(null);

        // Create the MQuestWorld, which fails.
        MQuestWorldDTO mQuestWorldDTO = mQuestWorldMapper.toDto(mQuestWorld);

        restMQuestWorldMockMvc.perform(post("/api/m-quest-worlds")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mQuestWorldDTO)))
            .andExpect(status().isBadRequest());

        List<MQuestWorld> mQuestWorldList = mQuestWorldRepository.findAll();
        assertThat(mQuestWorldList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkIsEnableCoopIsRequired() throws Exception {
        int databaseSizeBeforeTest = mQuestWorldRepository.findAll().size();
        // set the field null
        mQuestWorld.setIsEnableCoop(null);

        // Create the MQuestWorld, which fails.
        MQuestWorldDTO mQuestWorldDTO = mQuestWorldMapper.toDto(mQuestWorld);

        restMQuestWorldMockMvc.perform(post("/api/m-quest-worlds")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mQuestWorldDTO)))
            .andExpect(status().isBadRequest());

        List<MQuestWorld> mQuestWorldList = mQuestWorldRepository.findAll();
        assertThat(mQuestWorldList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllMQuestWorlds() throws Exception {
        // Initialize the database
        mQuestWorldRepository.saveAndFlush(mQuestWorld);

        // Get all the mQuestWorldList
        restMQuestWorldMockMvc.perform(get("/api/m-quest-worlds?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(mQuestWorld.getId().intValue())))
            .andExpect(jsonPath("$.[*].number").value(hasItem(DEFAULT_NUMBER)))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME.toString())))
            .andExpect(jsonPath("$.[*].startAt").value(hasItem(DEFAULT_START_AT)))
            .andExpect(jsonPath("$.[*].imagePath").value(hasItem(DEFAULT_IMAGE_PATH.toString())))
            .andExpect(jsonPath("$.[*].backgroundImagePath").value(hasItem(DEFAULT_BACKGROUND_IMAGE_PATH.toString())))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION.toString())))
            .andExpect(jsonPath("$.[*].stageUnlockPattern").value(hasItem(DEFAULT_STAGE_UNLOCK_PATTERN)))
            .andExpect(jsonPath("$.[*].specialRewardContentType").value(hasItem(DEFAULT_SPECIAL_REWARD_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].specialRewardContentId").value(hasItem(DEFAULT_SPECIAL_REWARD_CONTENT_ID)))
            .andExpect(jsonPath("$.[*].isEnableCoop").value(hasItem(DEFAULT_IS_ENABLE_COOP)));
    }
    
    @Test
    @Transactional
    public void getMQuestWorld() throws Exception {
        // Initialize the database
        mQuestWorldRepository.saveAndFlush(mQuestWorld);

        // Get the mQuestWorld
        restMQuestWorldMockMvc.perform(get("/api/m-quest-worlds/{id}", mQuestWorld.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(mQuestWorld.getId().intValue()))
            .andExpect(jsonPath("$.number").value(DEFAULT_NUMBER))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME.toString()))
            .andExpect(jsonPath("$.startAt").value(DEFAULT_START_AT))
            .andExpect(jsonPath("$.imagePath").value(DEFAULT_IMAGE_PATH.toString()))
            .andExpect(jsonPath("$.backgroundImagePath").value(DEFAULT_BACKGROUND_IMAGE_PATH.toString()))
            .andExpect(jsonPath("$.description").value(DEFAULT_DESCRIPTION.toString()))
            .andExpect(jsonPath("$.stageUnlockPattern").value(DEFAULT_STAGE_UNLOCK_PATTERN))
            .andExpect(jsonPath("$.specialRewardContentType").value(DEFAULT_SPECIAL_REWARD_CONTENT_TYPE))
            .andExpect(jsonPath("$.specialRewardContentId").value(DEFAULT_SPECIAL_REWARD_CONTENT_ID))
            .andExpect(jsonPath("$.isEnableCoop").value(DEFAULT_IS_ENABLE_COOP));
    }

    @Test
    @Transactional
    public void getAllMQuestWorldsByNumberIsEqualToSomething() throws Exception {
        // Initialize the database
        mQuestWorldRepository.saveAndFlush(mQuestWorld);

        // Get all the mQuestWorldList where number equals to DEFAULT_NUMBER
        defaultMQuestWorldShouldBeFound("number.equals=" + DEFAULT_NUMBER);

        // Get all the mQuestWorldList where number equals to UPDATED_NUMBER
        defaultMQuestWorldShouldNotBeFound("number.equals=" + UPDATED_NUMBER);
    }

    @Test
    @Transactional
    public void getAllMQuestWorldsByNumberIsInShouldWork() throws Exception {
        // Initialize the database
        mQuestWorldRepository.saveAndFlush(mQuestWorld);

        // Get all the mQuestWorldList where number in DEFAULT_NUMBER or UPDATED_NUMBER
        defaultMQuestWorldShouldBeFound("number.in=" + DEFAULT_NUMBER + "," + UPDATED_NUMBER);

        // Get all the mQuestWorldList where number equals to UPDATED_NUMBER
        defaultMQuestWorldShouldNotBeFound("number.in=" + UPDATED_NUMBER);
    }

    @Test
    @Transactional
    public void getAllMQuestWorldsByNumberIsNullOrNotNull() throws Exception {
        // Initialize the database
        mQuestWorldRepository.saveAndFlush(mQuestWorld);

        // Get all the mQuestWorldList where number is not null
        defaultMQuestWorldShouldBeFound("number.specified=true");

        // Get all the mQuestWorldList where number is null
        defaultMQuestWorldShouldNotBeFound("number.specified=false");
    }

    @Test
    @Transactional
    public void getAllMQuestWorldsByNumberIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mQuestWorldRepository.saveAndFlush(mQuestWorld);

        // Get all the mQuestWorldList where number greater than or equals to DEFAULT_NUMBER
        defaultMQuestWorldShouldBeFound("number.greaterOrEqualThan=" + DEFAULT_NUMBER);

        // Get all the mQuestWorldList where number greater than or equals to UPDATED_NUMBER
        defaultMQuestWorldShouldNotBeFound("number.greaterOrEqualThan=" + UPDATED_NUMBER);
    }

    @Test
    @Transactional
    public void getAllMQuestWorldsByNumberIsLessThanSomething() throws Exception {
        // Initialize the database
        mQuestWorldRepository.saveAndFlush(mQuestWorld);

        // Get all the mQuestWorldList where number less than or equals to DEFAULT_NUMBER
        defaultMQuestWorldShouldNotBeFound("number.lessThan=" + DEFAULT_NUMBER);

        // Get all the mQuestWorldList where number less than or equals to UPDATED_NUMBER
        defaultMQuestWorldShouldBeFound("number.lessThan=" + UPDATED_NUMBER);
    }


    @Test
    @Transactional
    public void getAllMQuestWorldsByStartAtIsEqualToSomething() throws Exception {
        // Initialize the database
        mQuestWorldRepository.saveAndFlush(mQuestWorld);

        // Get all the mQuestWorldList where startAt equals to DEFAULT_START_AT
        defaultMQuestWorldShouldBeFound("startAt.equals=" + DEFAULT_START_AT);

        // Get all the mQuestWorldList where startAt equals to UPDATED_START_AT
        defaultMQuestWorldShouldNotBeFound("startAt.equals=" + UPDATED_START_AT);
    }

    @Test
    @Transactional
    public void getAllMQuestWorldsByStartAtIsInShouldWork() throws Exception {
        // Initialize the database
        mQuestWorldRepository.saveAndFlush(mQuestWorld);

        // Get all the mQuestWorldList where startAt in DEFAULT_START_AT or UPDATED_START_AT
        defaultMQuestWorldShouldBeFound("startAt.in=" + DEFAULT_START_AT + "," + UPDATED_START_AT);

        // Get all the mQuestWorldList where startAt equals to UPDATED_START_AT
        defaultMQuestWorldShouldNotBeFound("startAt.in=" + UPDATED_START_AT);
    }

    @Test
    @Transactional
    public void getAllMQuestWorldsByStartAtIsNullOrNotNull() throws Exception {
        // Initialize the database
        mQuestWorldRepository.saveAndFlush(mQuestWorld);

        // Get all the mQuestWorldList where startAt is not null
        defaultMQuestWorldShouldBeFound("startAt.specified=true");

        // Get all the mQuestWorldList where startAt is null
        defaultMQuestWorldShouldNotBeFound("startAt.specified=false");
    }

    @Test
    @Transactional
    public void getAllMQuestWorldsByStartAtIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mQuestWorldRepository.saveAndFlush(mQuestWorld);

        // Get all the mQuestWorldList where startAt greater than or equals to DEFAULT_START_AT
        defaultMQuestWorldShouldBeFound("startAt.greaterOrEqualThan=" + DEFAULT_START_AT);

        // Get all the mQuestWorldList where startAt greater than or equals to UPDATED_START_AT
        defaultMQuestWorldShouldNotBeFound("startAt.greaterOrEqualThan=" + UPDATED_START_AT);
    }

    @Test
    @Transactional
    public void getAllMQuestWorldsByStartAtIsLessThanSomething() throws Exception {
        // Initialize the database
        mQuestWorldRepository.saveAndFlush(mQuestWorld);

        // Get all the mQuestWorldList where startAt less than or equals to DEFAULT_START_AT
        defaultMQuestWorldShouldNotBeFound("startAt.lessThan=" + DEFAULT_START_AT);

        // Get all the mQuestWorldList where startAt less than or equals to UPDATED_START_AT
        defaultMQuestWorldShouldBeFound("startAt.lessThan=" + UPDATED_START_AT);
    }


    @Test
    @Transactional
    public void getAllMQuestWorldsByStageUnlockPatternIsEqualToSomething() throws Exception {
        // Initialize the database
        mQuestWorldRepository.saveAndFlush(mQuestWorld);

        // Get all the mQuestWorldList where stageUnlockPattern equals to DEFAULT_STAGE_UNLOCK_PATTERN
        defaultMQuestWorldShouldBeFound("stageUnlockPattern.equals=" + DEFAULT_STAGE_UNLOCK_PATTERN);

        // Get all the mQuestWorldList where stageUnlockPattern equals to UPDATED_STAGE_UNLOCK_PATTERN
        defaultMQuestWorldShouldNotBeFound("stageUnlockPattern.equals=" + UPDATED_STAGE_UNLOCK_PATTERN);
    }

    @Test
    @Transactional
    public void getAllMQuestWorldsByStageUnlockPatternIsInShouldWork() throws Exception {
        // Initialize the database
        mQuestWorldRepository.saveAndFlush(mQuestWorld);

        // Get all the mQuestWorldList where stageUnlockPattern in DEFAULT_STAGE_UNLOCK_PATTERN or UPDATED_STAGE_UNLOCK_PATTERN
        defaultMQuestWorldShouldBeFound("stageUnlockPattern.in=" + DEFAULT_STAGE_UNLOCK_PATTERN + "," + UPDATED_STAGE_UNLOCK_PATTERN);

        // Get all the mQuestWorldList where stageUnlockPattern equals to UPDATED_STAGE_UNLOCK_PATTERN
        defaultMQuestWorldShouldNotBeFound("stageUnlockPattern.in=" + UPDATED_STAGE_UNLOCK_PATTERN);
    }

    @Test
    @Transactional
    public void getAllMQuestWorldsByStageUnlockPatternIsNullOrNotNull() throws Exception {
        // Initialize the database
        mQuestWorldRepository.saveAndFlush(mQuestWorld);

        // Get all the mQuestWorldList where stageUnlockPattern is not null
        defaultMQuestWorldShouldBeFound("stageUnlockPattern.specified=true");

        // Get all the mQuestWorldList where stageUnlockPattern is null
        defaultMQuestWorldShouldNotBeFound("stageUnlockPattern.specified=false");
    }

    @Test
    @Transactional
    public void getAllMQuestWorldsByStageUnlockPatternIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mQuestWorldRepository.saveAndFlush(mQuestWorld);

        // Get all the mQuestWorldList where stageUnlockPattern greater than or equals to DEFAULT_STAGE_UNLOCK_PATTERN
        defaultMQuestWorldShouldBeFound("stageUnlockPattern.greaterOrEqualThan=" + DEFAULT_STAGE_UNLOCK_PATTERN);

        // Get all the mQuestWorldList where stageUnlockPattern greater than or equals to UPDATED_STAGE_UNLOCK_PATTERN
        defaultMQuestWorldShouldNotBeFound("stageUnlockPattern.greaterOrEqualThan=" + UPDATED_STAGE_UNLOCK_PATTERN);
    }

    @Test
    @Transactional
    public void getAllMQuestWorldsByStageUnlockPatternIsLessThanSomething() throws Exception {
        // Initialize the database
        mQuestWorldRepository.saveAndFlush(mQuestWorld);

        // Get all the mQuestWorldList where stageUnlockPattern less than or equals to DEFAULT_STAGE_UNLOCK_PATTERN
        defaultMQuestWorldShouldNotBeFound("stageUnlockPattern.lessThan=" + DEFAULT_STAGE_UNLOCK_PATTERN);

        // Get all the mQuestWorldList where stageUnlockPattern less than or equals to UPDATED_STAGE_UNLOCK_PATTERN
        defaultMQuestWorldShouldBeFound("stageUnlockPattern.lessThan=" + UPDATED_STAGE_UNLOCK_PATTERN);
    }


    @Test
    @Transactional
    public void getAllMQuestWorldsBySpecialRewardContentTypeIsEqualToSomething() throws Exception {
        // Initialize the database
        mQuestWorldRepository.saveAndFlush(mQuestWorld);

        // Get all the mQuestWorldList where specialRewardContentType equals to DEFAULT_SPECIAL_REWARD_CONTENT_TYPE
        defaultMQuestWorldShouldBeFound("specialRewardContentType.equals=" + DEFAULT_SPECIAL_REWARD_CONTENT_TYPE);

        // Get all the mQuestWorldList where specialRewardContentType equals to UPDATED_SPECIAL_REWARD_CONTENT_TYPE
        defaultMQuestWorldShouldNotBeFound("specialRewardContentType.equals=" + UPDATED_SPECIAL_REWARD_CONTENT_TYPE);
    }

    @Test
    @Transactional
    public void getAllMQuestWorldsBySpecialRewardContentTypeIsInShouldWork() throws Exception {
        // Initialize the database
        mQuestWorldRepository.saveAndFlush(mQuestWorld);

        // Get all the mQuestWorldList where specialRewardContentType in DEFAULT_SPECIAL_REWARD_CONTENT_TYPE or UPDATED_SPECIAL_REWARD_CONTENT_TYPE
        defaultMQuestWorldShouldBeFound("specialRewardContentType.in=" + DEFAULT_SPECIAL_REWARD_CONTENT_TYPE + "," + UPDATED_SPECIAL_REWARD_CONTENT_TYPE);

        // Get all the mQuestWorldList where specialRewardContentType equals to UPDATED_SPECIAL_REWARD_CONTENT_TYPE
        defaultMQuestWorldShouldNotBeFound("specialRewardContentType.in=" + UPDATED_SPECIAL_REWARD_CONTENT_TYPE);
    }

    @Test
    @Transactional
    public void getAllMQuestWorldsBySpecialRewardContentTypeIsNullOrNotNull() throws Exception {
        // Initialize the database
        mQuestWorldRepository.saveAndFlush(mQuestWorld);

        // Get all the mQuestWorldList where specialRewardContentType is not null
        defaultMQuestWorldShouldBeFound("specialRewardContentType.specified=true");

        // Get all the mQuestWorldList where specialRewardContentType is null
        defaultMQuestWorldShouldNotBeFound("specialRewardContentType.specified=false");
    }

    @Test
    @Transactional
    public void getAllMQuestWorldsBySpecialRewardContentTypeIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mQuestWorldRepository.saveAndFlush(mQuestWorld);

        // Get all the mQuestWorldList where specialRewardContentType greater than or equals to DEFAULT_SPECIAL_REWARD_CONTENT_TYPE
        defaultMQuestWorldShouldBeFound("specialRewardContentType.greaterOrEqualThan=" + DEFAULT_SPECIAL_REWARD_CONTENT_TYPE);

        // Get all the mQuestWorldList where specialRewardContentType greater than or equals to UPDATED_SPECIAL_REWARD_CONTENT_TYPE
        defaultMQuestWorldShouldNotBeFound("specialRewardContentType.greaterOrEqualThan=" + UPDATED_SPECIAL_REWARD_CONTENT_TYPE);
    }

    @Test
    @Transactional
    public void getAllMQuestWorldsBySpecialRewardContentTypeIsLessThanSomething() throws Exception {
        // Initialize the database
        mQuestWorldRepository.saveAndFlush(mQuestWorld);

        // Get all the mQuestWorldList where specialRewardContentType less than or equals to DEFAULT_SPECIAL_REWARD_CONTENT_TYPE
        defaultMQuestWorldShouldNotBeFound("specialRewardContentType.lessThan=" + DEFAULT_SPECIAL_REWARD_CONTENT_TYPE);

        // Get all the mQuestWorldList where specialRewardContentType less than or equals to UPDATED_SPECIAL_REWARD_CONTENT_TYPE
        defaultMQuestWorldShouldBeFound("specialRewardContentType.lessThan=" + UPDATED_SPECIAL_REWARD_CONTENT_TYPE);
    }


    @Test
    @Transactional
    public void getAllMQuestWorldsBySpecialRewardContentIdIsEqualToSomething() throws Exception {
        // Initialize the database
        mQuestWorldRepository.saveAndFlush(mQuestWorld);

        // Get all the mQuestWorldList where specialRewardContentId equals to DEFAULT_SPECIAL_REWARD_CONTENT_ID
        defaultMQuestWorldShouldBeFound("specialRewardContentId.equals=" + DEFAULT_SPECIAL_REWARD_CONTENT_ID);

        // Get all the mQuestWorldList where specialRewardContentId equals to UPDATED_SPECIAL_REWARD_CONTENT_ID
        defaultMQuestWorldShouldNotBeFound("specialRewardContentId.equals=" + UPDATED_SPECIAL_REWARD_CONTENT_ID);
    }

    @Test
    @Transactional
    public void getAllMQuestWorldsBySpecialRewardContentIdIsInShouldWork() throws Exception {
        // Initialize the database
        mQuestWorldRepository.saveAndFlush(mQuestWorld);

        // Get all the mQuestWorldList where specialRewardContentId in DEFAULT_SPECIAL_REWARD_CONTENT_ID or UPDATED_SPECIAL_REWARD_CONTENT_ID
        defaultMQuestWorldShouldBeFound("specialRewardContentId.in=" + DEFAULT_SPECIAL_REWARD_CONTENT_ID + "," + UPDATED_SPECIAL_REWARD_CONTENT_ID);

        // Get all the mQuestWorldList where specialRewardContentId equals to UPDATED_SPECIAL_REWARD_CONTENT_ID
        defaultMQuestWorldShouldNotBeFound("specialRewardContentId.in=" + UPDATED_SPECIAL_REWARD_CONTENT_ID);
    }

    @Test
    @Transactional
    public void getAllMQuestWorldsBySpecialRewardContentIdIsNullOrNotNull() throws Exception {
        // Initialize the database
        mQuestWorldRepository.saveAndFlush(mQuestWorld);

        // Get all the mQuestWorldList where specialRewardContentId is not null
        defaultMQuestWorldShouldBeFound("specialRewardContentId.specified=true");

        // Get all the mQuestWorldList where specialRewardContentId is null
        defaultMQuestWorldShouldNotBeFound("specialRewardContentId.specified=false");
    }

    @Test
    @Transactional
    public void getAllMQuestWorldsBySpecialRewardContentIdIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mQuestWorldRepository.saveAndFlush(mQuestWorld);

        // Get all the mQuestWorldList where specialRewardContentId greater than or equals to DEFAULT_SPECIAL_REWARD_CONTENT_ID
        defaultMQuestWorldShouldBeFound("specialRewardContentId.greaterOrEqualThan=" + DEFAULT_SPECIAL_REWARD_CONTENT_ID);

        // Get all the mQuestWorldList where specialRewardContentId greater than or equals to UPDATED_SPECIAL_REWARD_CONTENT_ID
        defaultMQuestWorldShouldNotBeFound("specialRewardContentId.greaterOrEqualThan=" + UPDATED_SPECIAL_REWARD_CONTENT_ID);
    }

    @Test
    @Transactional
    public void getAllMQuestWorldsBySpecialRewardContentIdIsLessThanSomething() throws Exception {
        // Initialize the database
        mQuestWorldRepository.saveAndFlush(mQuestWorld);

        // Get all the mQuestWorldList where specialRewardContentId less than or equals to DEFAULT_SPECIAL_REWARD_CONTENT_ID
        defaultMQuestWorldShouldNotBeFound("specialRewardContentId.lessThan=" + DEFAULT_SPECIAL_REWARD_CONTENT_ID);

        // Get all the mQuestWorldList where specialRewardContentId less than or equals to UPDATED_SPECIAL_REWARD_CONTENT_ID
        defaultMQuestWorldShouldBeFound("specialRewardContentId.lessThan=" + UPDATED_SPECIAL_REWARD_CONTENT_ID);
    }


    @Test
    @Transactional
    public void getAllMQuestWorldsByIsEnableCoopIsEqualToSomething() throws Exception {
        // Initialize the database
        mQuestWorldRepository.saveAndFlush(mQuestWorld);

        // Get all the mQuestWorldList where isEnableCoop equals to DEFAULT_IS_ENABLE_COOP
        defaultMQuestWorldShouldBeFound("isEnableCoop.equals=" + DEFAULT_IS_ENABLE_COOP);

        // Get all the mQuestWorldList where isEnableCoop equals to UPDATED_IS_ENABLE_COOP
        defaultMQuestWorldShouldNotBeFound("isEnableCoop.equals=" + UPDATED_IS_ENABLE_COOP);
    }

    @Test
    @Transactional
    public void getAllMQuestWorldsByIsEnableCoopIsInShouldWork() throws Exception {
        // Initialize the database
        mQuestWorldRepository.saveAndFlush(mQuestWorld);

        // Get all the mQuestWorldList where isEnableCoop in DEFAULT_IS_ENABLE_COOP or UPDATED_IS_ENABLE_COOP
        defaultMQuestWorldShouldBeFound("isEnableCoop.in=" + DEFAULT_IS_ENABLE_COOP + "," + UPDATED_IS_ENABLE_COOP);

        // Get all the mQuestWorldList where isEnableCoop equals to UPDATED_IS_ENABLE_COOP
        defaultMQuestWorldShouldNotBeFound("isEnableCoop.in=" + UPDATED_IS_ENABLE_COOP);
    }

    @Test
    @Transactional
    public void getAllMQuestWorldsByIsEnableCoopIsNullOrNotNull() throws Exception {
        // Initialize the database
        mQuestWorldRepository.saveAndFlush(mQuestWorld);

        // Get all the mQuestWorldList where isEnableCoop is not null
        defaultMQuestWorldShouldBeFound("isEnableCoop.specified=true");

        // Get all the mQuestWorldList where isEnableCoop is null
        defaultMQuestWorldShouldNotBeFound("isEnableCoop.specified=false");
    }

    @Test
    @Transactional
    public void getAllMQuestWorldsByIsEnableCoopIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mQuestWorldRepository.saveAndFlush(mQuestWorld);

        // Get all the mQuestWorldList where isEnableCoop greater than or equals to DEFAULT_IS_ENABLE_COOP
        defaultMQuestWorldShouldBeFound("isEnableCoop.greaterOrEqualThan=" + DEFAULT_IS_ENABLE_COOP);

        // Get all the mQuestWorldList where isEnableCoop greater than or equals to UPDATED_IS_ENABLE_COOP
        defaultMQuestWorldShouldNotBeFound("isEnableCoop.greaterOrEqualThan=" + UPDATED_IS_ENABLE_COOP);
    }

    @Test
    @Transactional
    public void getAllMQuestWorldsByIsEnableCoopIsLessThanSomething() throws Exception {
        // Initialize the database
        mQuestWorldRepository.saveAndFlush(mQuestWorld);

        // Get all the mQuestWorldList where isEnableCoop less than or equals to DEFAULT_IS_ENABLE_COOP
        defaultMQuestWorldShouldNotBeFound("isEnableCoop.lessThan=" + DEFAULT_IS_ENABLE_COOP);

        // Get all the mQuestWorldList where isEnableCoop less than or equals to UPDATED_IS_ENABLE_COOP
        defaultMQuestWorldShouldBeFound("isEnableCoop.lessThan=" + UPDATED_IS_ENABLE_COOP);
    }


    @Test
    @Transactional
    public void getAllMQuestWorldsByMQuestStageIsEqualToSomething() throws Exception {
        // Initialize the database
        MQuestStage mQuestStage = MQuestStageResourceIT.createEntity(em);
        em.persist(mQuestStage);
        em.flush();
        mQuestWorld.addMQuestStage(mQuestStage);
        mQuestWorldRepository.saveAndFlush(mQuestWorld);
        Long mQuestStageId = mQuestStage.getId();

        // Get all the mQuestWorldList where mQuestStage equals to mQuestStageId
        defaultMQuestWorldShouldBeFound("mQuestStageId.equals=" + mQuestStageId);

        // Get all the mQuestWorldList where mQuestStage equals to mQuestStageId + 1
        defaultMQuestWorldShouldNotBeFound("mQuestStageId.equals=" + (mQuestStageId + 1));
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultMQuestWorldShouldBeFound(String filter) throws Exception {
        restMQuestWorldMockMvc.perform(get("/api/m-quest-worlds?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(mQuestWorld.getId().intValue())))
            .andExpect(jsonPath("$.[*].number").value(hasItem(DEFAULT_NUMBER)))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME.toString())))
            .andExpect(jsonPath("$.[*].startAt").value(hasItem(DEFAULT_START_AT)))
            .andExpect(jsonPath("$.[*].imagePath").value(hasItem(DEFAULT_IMAGE_PATH.toString())))
            .andExpect(jsonPath("$.[*].backgroundImagePath").value(hasItem(DEFAULT_BACKGROUND_IMAGE_PATH.toString())))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION.toString())))
            .andExpect(jsonPath("$.[*].stageUnlockPattern").value(hasItem(DEFAULT_STAGE_UNLOCK_PATTERN)))
            .andExpect(jsonPath("$.[*].specialRewardContentType").value(hasItem(DEFAULT_SPECIAL_REWARD_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].specialRewardContentId").value(hasItem(DEFAULT_SPECIAL_REWARD_CONTENT_ID)))
            .andExpect(jsonPath("$.[*].isEnableCoop").value(hasItem(DEFAULT_IS_ENABLE_COOP)));

        // Check, that the count call also returns 1
        restMQuestWorldMockMvc.perform(get("/api/m-quest-worlds/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultMQuestWorldShouldNotBeFound(String filter) throws Exception {
        restMQuestWorldMockMvc.perform(get("/api/m-quest-worlds?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restMQuestWorldMockMvc.perform(get("/api/m-quest-worlds/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("0"));
    }


    @Test
    @Transactional
    public void getNonExistingMQuestWorld() throws Exception {
        // Get the mQuestWorld
        restMQuestWorldMockMvc.perform(get("/api/m-quest-worlds/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateMQuestWorld() throws Exception {
        // Initialize the database
        mQuestWorldRepository.saveAndFlush(mQuestWorld);

        int databaseSizeBeforeUpdate = mQuestWorldRepository.findAll().size();

        // Update the mQuestWorld
        MQuestWorld updatedMQuestWorld = mQuestWorldRepository.findById(mQuestWorld.getId()).get();
        // Disconnect from session so that the updates on updatedMQuestWorld are not directly saved in db
        em.detach(updatedMQuestWorld);
        updatedMQuestWorld
            .number(UPDATED_NUMBER)
            .name(UPDATED_NAME)
            .startAt(UPDATED_START_AT)
            .imagePath(UPDATED_IMAGE_PATH)
            .backgroundImagePath(UPDATED_BACKGROUND_IMAGE_PATH)
            .description(UPDATED_DESCRIPTION)
            .stageUnlockPattern(UPDATED_STAGE_UNLOCK_PATTERN)
            .specialRewardContentType(UPDATED_SPECIAL_REWARD_CONTENT_TYPE)
            .specialRewardContentId(UPDATED_SPECIAL_REWARD_CONTENT_ID)
            .isEnableCoop(UPDATED_IS_ENABLE_COOP);
        MQuestWorldDTO mQuestWorldDTO = mQuestWorldMapper.toDto(updatedMQuestWorld);

        restMQuestWorldMockMvc.perform(put("/api/m-quest-worlds")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mQuestWorldDTO)))
            .andExpect(status().isOk());

        // Validate the MQuestWorld in the database
        List<MQuestWorld> mQuestWorldList = mQuestWorldRepository.findAll();
        assertThat(mQuestWorldList).hasSize(databaseSizeBeforeUpdate);
        MQuestWorld testMQuestWorld = mQuestWorldList.get(mQuestWorldList.size() - 1);
        assertThat(testMQuestWorld.getNumber()).isEqualTo(UPDATED_NUMBER);
        assertThat(testMQuestWorld.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testMQuestWorld.getStartAt()).isEqualTo(UPDATED_START_AT);
        assertThat(testMQuestWorld.getImagePath()).isEqualTo(UPDATED_IMAGE_PATH);
        assertThat(testMQuestWorld.getBackgroundImagePath()).isEqualTo(UPDATED_BACKGROUND_IMAGE_PATH);
        assertThat(testMQuestWorld.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
        assertThat(testMQuestWorld.getStageUnlockPattern()).isEqualTo(UPDATED_STAGE_UNLOCK_PATTERN);
        assertThat(testMQuestWorld.getSpecialRewardContentType()).isEqualTo(UPDATED_SPECIAL_REWARD_CONTENT_TYPE);
        assertThat(testMQuestWorld.getSpecialRewardContentId()).isEqualTo(UPDATED_SPECIAL_REWARD_CONTENT_ID);
        assertThat(testMQuestWorld.getIsEnableCoop()).isEqualTo(UPDATED_IS_ENABLE_COOP);
    }

    @Test
    @Transactional
    public void updateNonExistingMQuestWorld() throws Exception {
        int databaseSizeBeforeUpdate = mQuestWorldRepository.findAll().size();

        // Create the MQuestWorld
        MQuestWorldDTO mQuestWorldDTO = mQuestWorldMapper.toDto(mQuestWorld);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restMQuestWorldMockMvc.perform(put("/api/m-quest-worlds")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mQuestWorldDTO)))
            .andExpect(status().isBadRequest());

        // Validate the MQuestWorld in the database
        List<MQuestWorld> mQuestWorldList = mQuestWorldRepository.findAll();
        assertThat(mQuestWorldList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteMQuestWorld() throws Exception {
        // Initialize the database
        mQuestWorldRepository.saveAndFlush(mQuestWorld);

        int databaseSizeBeforeDelete = mQuestWorldRepository.findAll().size();

        // Delete the mQuestWorld
        restMQuestWorldMockMvc.perform(delete("/api/m-quest-worlds/{id}", mQuestWorld.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database is empty
        List<MQuestWorld> mQuestWorldList = mQuestWorldRepository.findAll();
        assertThat(mQuestWorldList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(MQuestWorld.class);
        MQuestWorld mQuestWorld1 = new MQuestWorld();
        mQuestWorld1.setId(1L);
        MQuestWorld mQuestWorld2 = new MQuestWorld();
        mQuestWorld2.setId(mQuestWorld1.getId());
        assertThat(mQuestWorld1).isEqualTo(mQuestWorld2);
        mQuestWorld2.setId(2L);
        assertThat(mQuestWorld1).isNotEqualTo(mQuestWorld2);
        mQuestWorld1.setId(null);
        assertThat(mQuestWorld1).isNotEqualTo(mQuestWorld2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(MQuestWorldDTO.class);
        MQuestWorldDTO mQuestWorldDTO1 = new MQuestWorldDTO();
        mQuestWorldDTO1.setId(1L);
        MQuestWorldDTO mQuestWorldDTO2 = new MQuestWorldDTO();
        assertThat(mQuestWorldDTO1).isNotEqualTo(mQuestWorldDTO2);
        mQuestWorldDTO2.setId(mQuestWorldDTO1.getId());
        assertThat(mQuestWorldDTO1).isEqualTo(mQuestWorldDTO2);
        mQuestWorldDTO2.setId(2L);
        assertThat(mQuestWorldDTO1).isNotEqualTo(mQuestWorldDTO2);
        mQuestWorldDTO1.setId(null);
        assertThat(mQuestWorldDTO1).isNotEqualTo(mQuestWorldDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(mQuestWorldMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(mQuestWorldMapper.fromId(null)).isNull();
    }
}
