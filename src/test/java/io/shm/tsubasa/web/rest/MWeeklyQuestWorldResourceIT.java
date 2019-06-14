package io.shm.tsubasa.web.rest;

import io.shm.tsubasa.TsubasaApp;
import io.shm.tsubasa.domain.MWeeklyQuestWorld;
import io.shm.tsubasa.domain.MWeeklyQuestStage;
import io.shm.tsubasa.repository.MWeeklyQuestWorldRepository;
import io.shm.tsubasa.service.MWeeklyQuestWorldService;
import io.shm.tsubasa.service.dto.MWeeklyQuestWorldDTO;
import io.shm.tsubasa.service.mapper.MWeeklyQuestWorldMapper;
import io.shm.tsubasa.web.rest.errors.ExceptionTranslator;
import io.shm.tsubasa.service.dto.MWeeklyQuestWorldCriteria;
import io.shm.tsubasa.service.MWeeklyQuestWorldQueryService;

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
 * Integration tests for the {@Link MWeeklyQuestWorldResource} REST controller.
 */
@SpringBootTest(classes = TsubasaApp.class)
public class MWeeklyQuestWorldResourceIT {

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

    @Autowired
    private MWeeklyQuestWorldRepository mWeeklyQuestWorldRepository;

    @Autowired
    private MWeeklyQuestWorldMapper mWeeklyQuestWorldMapper;

    @Autowired
    private MWeeklyQuestWorldService mWeeklyQuestWorldService;

    @Autowired
    private MWeeklyQuestWorldQueryService mWeeklyQuestWorldQueryService;

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

    private MockMvc restMWeeklyQuestWorldMockMvc;

    private MWeeklyQuestWorld mWeeklyQuestWorld;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final MWeeklyQuestWorldResource mWeeklyQuestWorldResource = new MWeeklyQuestWorldResource(mWeeklyQuestWorldService, mWeeklyQuestWorldQueryService);
        this.restMWeeklyQuestWorldMockMvc = MockMvcBuilders.standaloneSetup(mWeeklyQuestWorldResource)
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
    public static MWeeklyQuestWorld createEntity(EntityManager em) {
        MWeeklyQuestWorld mWeeklyQuestWorld = new MWeeklyQuestWorld()
            .setId(DEFAULT_SET_ID)
            .number(DEFAULT_NUMBER)
            .name(DEFAULT_NAME)
            .imagePath(DEFAULT_IMAGE_PATH)
            .description(DEFAULT_DESCRIPTION)
            .stageUnlockPattern(DEFAULT_STAGE_UNLOCK_PATTERN)
            .arousalBanner(DEFAULT_AROUSAL_BANNER)
            .specialRewardContentType(DEFAULT_SPECIAL_REWARD_CONTENT_TYPE)
            .specialRewardContentId(DEFAULT_SPECIAL_REWARD_CONTENT_ID)
            .isEnableCoop(DEFAULT_IS_ENABLE_COOP);
        return mWeeklyQuestWorld;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static MWeeklyQuestWorld createUpdatedEntity(EntityManager em) {
        MWeeklyQuestWorld mWeeklyQuestWorld = new MWeeklyQuestWorld()
            .setId(UPDATED_SET_ID)
            .number(UPDATED_NUMBER)
            .name(UPDATED_NAME)
            .imagePath(UPDATED_IMAGE_PATH)
            .description(UPDATED_DESCRIPTION)
            .stageUnlockPattern(UPDATED_STAGE_UNLOCK_PATTERN)
            .arousalBanner(UPDATED_AROUSAL_BANNER)
            .specialRewardContentType(UPDATED_SPECIAL_REWARD_CONTENT_TYPE)
            .specialRewardContentId(UPDATED_SPECIAL_REWARD_CONTENT_ID)
            .isEnableCoop(UPDATED_IS_ENABLE_COOP);
        return mWeeklyQuestWorld;
    }

    @BeforeEach
    public void initTest() {
        mWeeklyQuestWorld = createEntity(em);
    }

    @Test
    @Transactional
    public void createMWeeklyQuestWorld() throws Exception {
        int databaseSizeBeforeCreate = mWeeklyQuestWorldRepository.findAll().size();

        // Create the MWeeklyQuestWorld
        MWeeklyQuestWorldDTO mWeeklyQuestWorldDTO = mWeeklyQuestWorldMapper.toDto(mWeeklyQuestWorld);
        restMWeeklyQuestWorldMockMvc.perform(post("/api/m-weekly-quest-worlds")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mWeeklyQuestWorldDTO)))
            .andExpect(status().isCreated());

        // Validate the MWeeklyQuestWorld in the database
        List<MWeeklyQuestWorld> mWeeklyQuestWorldList = mWeeklyQuestWorldRepository.findAll();
        assertThat(mWeeklyQuestWorldList).hasSize(databaseSizeBeforeCreate + 1);
        MWeeklyQuestWorld testMWeeklyQuestWorld = mWeeklyQuestWorldList.get(mWeeklyQuestWorldList.size() - 1);
        assertThat(testMWeeklyQuestWorld.getSetId()).isEqualTo(DEFAULT_SET_ID);
        assertThat(testMWeeklyQuestWorld.getNumber()).isEqualTo(DEFAULT_NUMBER);
        assertThat(testMWeeklyQuestWorld.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testMWeeklyQuestWorld.getImagePath()).isEqualTo(DEFAULT_IMAGE_PATH);
        assertThat(testMWeeklyQuestWorld.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
        assertThat(testMWeeklyQuestWorld.getStageUnlockPattern()).isEqualTo(DEFAULT_STAGE_UNLOCK_PATTERN);
        assertThat(testMWeeklyQuestWorld.getArousalBanner()).isEqualTo(DEFAULT_AROUSAL_BANNER);
        assertThat(testMWeeklyQuestWorld.getSpecialRewardContentType()).isEqualTo(DEFAULT_SPECIAL_REWARD_CONTENT_TYPE);
        assertThat(testMWeeklyQuestWorld.getSpecialRewardContentId()).isEqualTo(DEFAULT_SPECIAL_REWARD_CONTENT_ID);
        assertThat(testMWeeklyQuestWorld.getIsEnableCoop()).isEqualTo(DEFAULT_IS_ENABLE_COOP);
    }

    @Test
    @Transactional
    public void createMWeeklyQuestWorldWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = mWeeklyQuestWorldRepository.findAll().size();

        // Create the MWeeklyQuestWorld with an existing ID
        mWeeklyQuestWorld.setId(1L);
        MWeeklyQuestWorldDTO mWeeklyQuestWorldDTO = mWeeklyQuestWorldMapper.toDto(mWeeklyQuestWorld);

        // An entity with an existing ID cannot be created, so this API call must fail
        restMWeeklyQuestWorldMockMvc.perform(post("/api/m-weekly-quest-worlds")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mWeeklyQuestWorldDTO)))
            .andExpect(status().isBadRequest());

        // Validate the MWeeklyQuestWorld in the database
        List<MWeeklyQuestWorld> mWeeklyQuestWorldList = mWeeklyQuestWorldRepository.findAll();
        assertThat(mWeeklyQuestWorldList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkSetIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = mWeeklyQuestWorldRepository.findAll().size();
        // set the field null
        mWeeklyQuestWorld.setSetId(null);

        // Create the MWeeklyQuestWorld, which fails.
        MWeeklyQuestWorldDTO mWeeklyQuestWorldDTO = mWeeklyQuestWorldMapper.toDto(mWeeklyQuestWorld);

        restMWeeklyQuestWorldMockMvc.perform(post("/api/m-weekly-quest-worlds")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mWeeklyQuestWorldDTO)))
            .andExpect(status().isBadRequest());

        List<MWeeklyQuestWorld> mWeeklyQuestWorldList = mWeeklyQuestWorldRepository.findAll();
        assertThat(mWeeklyQuestWorldList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkNumberIsRequired() throws Exception {
        int databaseSizeBeforeTest = mWeeklyQuestWorldRepository.findAll().size();
        // set the field null
        mWeeklyQuestWorld.setNumber(null);

        // Create the MWeeklyQuestWorld, which fails.
        MWeeklyQuestWorldDTO mWeeklyQuestWorldDTO = mWeeklyQuestWorldMapper.toDto(mWeeklyQuestWorld);

        restMWeeklyQuestWorldMockMvc.perform(post("/api/m-weekly-quest-worlds")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mWeeklyQuestWorldDTO)))
            .andExpect(status().isBadRequest());

        List<MWeeklyQuestWorld> mWeeklyQuestWorldList = mWeeklyQuestWorldRepository.findAll();
        assertThat(mWeeklyQuestWorldList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkStageUnlockPatternIsRequired() throws Exception {
        int databaseSizeBeforeTest = mWeeklyQuestWorldRepository.findAll().size();
        // set the field null
        mWeeklyQuestWorld.setStageUnlockPattern(null);

        // Create the MWeeklyQuestWorld, which fails.
        MWeeklyQuestWorldDTO mWeeklyQuestWorldDTO = mWeeklyQuestWorldMapper.toDto(mWeeklyQuestWorld);

        restMWeeklyQuestWorldMockMvc.perform(post("/api/m-weekly-quest-worlds")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mWeeklyQuestWorldDTO)))
            .andExpect(status().isBadRequest());

        List<MWeeklyQuestWorld> mWeeklyQuestWorldList = mWeeklyQuestWorldRepository.findAll();
        assertThat(mWeeklyQuestWorldList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkIsEnableCoopIsRequired() throws Exception {
        int databaseSizeBeforeTest = mWeeklyQuestWorldRepository.findAll().size();
        // set the field null
        mWeeklyQuestWorld.setIsEnableCoop(null);

        // Create the MWeeklyQuestWorld, which fails.
        MWeeklyQuestWorldDTO mWeeklyQuestWorldDTO = mWeeklyQuestWorldMapper.toDto(mWeeklyQuestWorld);

        restMWeeklyQuestWorldMockMvc.perform(post("/api/m-weekly-quest-worlds")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mWeeklyQuestWorldDTO)))
            .andExpect(status().isBadRequest());

        List<MWeeklyQuestWorld> mWeeklyQuestWorldList = mWeeklyQuestWorldRepository.findAll();
        assertThat(mWeeklyQuestWorldList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllMWeeklyQuestWorlds() throws Exception {
        // Initialize the database
        mWeeklyQuestWorldRepository.saveAndFlush(mWeeklyQuestWorld);

        // Get all the mWeeklyQuestWorldList
        restMWeeklyQuestWorldMockMvc.perform(get("/api/m-weekly-quest-worlds?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(mWeeklyQuestWorld.getId().intValue())))
            .andExpect(jsonPath("$.[*].setId").value(hasItem(DEFAULT_SET_ID)))
            .andExpect(jsonPath("$.[*].number").value(hasItem(DEFAULT_NUMBER)))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME.toString())))
            .andExpect(jsonPath("$.[*].imagePath").value(hasItem(DEFAULT_IMAGE_PATH.toString())))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION.toString())))
            .andExpect(jsonPath("$.[*].stageUnlockPattern").value(hasItem(DEFAULT_STAGE_UNLOCK_PATTERN)))
            .andExpect(jsonPath("$.[*].arousalBanner").value(hasItem(DEFAULT_AROUSAL_BANNER.toString())))
            .andExpect(jsonPath("$.[*].specialRewardContentType").value(hasItem(DEFAULT_SPECIAL_REWARD_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].specialRewardContentId").value(hasItem(DEFAULT_SPECIAL_REWARD_CONTENT_ID)))
            .andExpect(jsonPath("$.[*].isEnableCoop").value(hasItem(DEFAULT_IS_ENABLE_COOP)));
    }
    
    @Test
    @Transactional
    public void getMWeeklyQuestWorld() throws Exception {
        // Initialize the database
        mWeeklyQuestWorldRepository.saveAndFlush(mWeeklyQuestWorld);

        // Get the mWeeklyQuestWorld
        restMWeeklyQuestWorldMockMvc.perform(get("/api/m-weekly-quest-worlds/{id}", mWeeklyQuestWorld.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(mWeeklyQuestWorld.getId().intValue()))
            .andExpect(jsonPath("$.setId").value(DEFAULT_SET_ID))
            .andExpect(jsonPath("$.number").value(DEFAULT_NUMBER))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME.toString()))
            .andExpect(jsonPath("$.imagePath").value(DEFAULT_IMAGE_PATH.toString()))
            .andExpect(jsonPath("$.description").value(DEFAULT_DESCRIPTION.toString()))
            .andExpect(jsonPath("$.stageUnlockPattern").value(DEFAULT_STAGE_UNLOCK_PATTERN))
            .andExpect(jsonPath("$.arousalBanner").value(DEFAULT_AROUSAL_BANNER.toString()))
            .andExpect(jsonPath("$.specialRewardContentType").value(DEFAULT_SPECIAL_REWARD_CONTENT_TYPE))
            .andExpect(jsonPath("$.specialRewardContentId").value(DEFAULT_SPECIAL_REWARD_CONTENT_ID))
            .andExpect(jsonPath("$.isEnableCoop").value(DEFAULT_IS_ENABLE_COOP));
    }

    @Test
    @Transactional
    public void getAllMWeeklyQuestWorldsBySetIdIsEqualToSomething() throws Exception {
        // Initialize the database
        mWeeklyQuestWorldRepository.saveAndFlush(mWeeklyQuestWorld);

        // Get all the mWeeklyQuestWorldList where setId equals to DEFAULT_SET_ID
        defaultMWeeklyQuestWorldShouldBeFound("setId.equals=" + DEFAULT_SET_ID);

        // Get all the mWeeklyQuestWorldList where setId equals to UPDATED_SET_ID
        defaultMWeeklyQuestWorldShouldNotBeFound("setId.equals=" + UPDATED_SET_ID);
    }

    @Test
    @Transactional
    public void getAllMWeeklyQuestWorldsBySetIdIsInShouldWork() throws Exception {
        // Initialize the database
        mWeeklyQuestWorldRepository.saveAndFlush(mWeeklyQuestWorld);

        // Get all the mWeeklyQuestWorldList where setId in DEFAULT_SET_ID or UPDATED_SET_ID
        defaultMWeeklyQuestWorldShouldBeFound("setId.in=" + DEFAULT_SET_ID + "," + UPDATED_SET_ID);

        // Get all the mWeeklyQuestWorldList where setId equals to UPDATED_SET_ID
        defaultMWeeklyQuestWorldShouldNotBeFound("setId.in=" + UPDATED_SET_ID);
    }

    @Test
    @Transactional
    public void getAllMWeeklyQuestWorldsBySetIdIsNullOrNotNull() throws Exception {
        // Initialize the database
        mWeeklyQuestWorldRepository.saveAndFlush(mWeeklyQuestWorld);

        // Get all the mWeeklyQuestWorldList where setId is not null
        defaultMWeeklyQuestWorldShouldBeFound("setId.specified=true");

        // Get all the mWeeklyQuestWorldList where setId is null
        defaultMWeeklyQuestWorldShouldNotBeFound("setId.specified=false");
    }

    @Test
    @Transactional
    public void getAllMWeeklyQuestWorldsBySetIdIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mWeeklyQuestWorldRepository.saveAndFlush(mWeeklyQuestWorld);

        // Get all the mWeeklyQuestWorldList where setId greater than or equals to DEFAULT_SET_ID
        defaultMWeeklyQuestWorldShouldBeFound("setId.greaterOrEqualThan=" + DEFAULT_SET_ID);

        // Get all the mWeeklyQuestWorldList where setId greater than or equals to UPDATED_SET_ID
        defaultMWeeklyQuestWorldShouldNotBeFound("setId.greaterOrEqualThan=" + UPDATED_SET_ID);
    }

    @Test
    @Transactional
    public void getAllMWeeklyQuestWorldsBySetIdIsLessThanSomething() throws Exception {
        // Initialize the database
        mWeeklyQuestWorldRepository.saveAndFlush(mWeeklyQuestWorld);

        // Get all the mWeeklyQuestWorldList where setId less than or equals to DEFAULT_SET_ID
        defaultMWeeklyQuestWorldShouldNotBeFound("setId.lessThan=" + DEFAULT_SET_ID);

        // Get all the mWeeklyQuestWorldList where setId less than or equals to UPDATED_SET_ID
        defaultMWeeklyQuestWorldShouldBeFound("setId.lessThan=" + UPDATED_SET_ID);
    }


    @Test
    @Transactional
    public void getAllMWeeklyQuestWorldsByNumberIsEqualToSomething() throws Exception {
        // Initialize the database
        mWeeklyQuestWorldRepository.saveAndFlush(mWeeklyQuestWorld);

        // Get all the mWeeklyQuestWorldList where number equals to DEFAULT_NUMBER
        defaultMWeeklyQuestWorldShouldBeFound("number.equals=" + DEFAULT_NUMBER);

        // Get all the mWeeklyQuestWorldList where number equals to UPDATED_NUMBER
        defaultMWeeklyQuestWorldShouldNotBeFound("number.equals=" + UPDATED_NUMBER);
    }

    @Test
    @Transactional
    public void getAllMWeeklyQuestWorldsByNumberIsInShouldWork() throws Exception {
        // Initialize the database
        mWeeklyQuestWorldRepository.saveAndFlush(mWeeklyQuestWorld);

        // Get all the mWeeklyQuestWorldList where number in DEFAULT_NUMBER or UPDATED_NUMBER
        defaultMWeeklyQuestWorldShouldBeFound("number.in=" + DEFAULT_NUMBER + "," + UPDATED_NUMBER);

        // Get all the mWeeklyQuestWorldList where number equals to UPDATED_NUMBER
        defaultMWeeklyQuestWorldShouldNotBeFound("number.in=" + UPDATED_NUMBER);
    }

    @Test
    @Transactional
    public void getAllMWeeklyQuestWorldsByNumberIsNullOrNotNull() throws Exception {
        // Initialize the database
        mWeeklyQuestWorldRepository.saveAndFlush(mWeeklyQuestWorld);

        // Get all the mWeeklyQuestWorldList where number is not null
        defaultMWeeklyQuestWorldShouldBeFound("number.specified=true");

        // Get all the mWeeklyQuestWorldList where number is null
        defaultMWeeklyQuestWorldShouldNotBeFound("number.specified=false");
    }

    @Test
    @Transactional
    public void getAllMWeeklyQuestWorldsByNumberIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mWeeklyQuestWorldRepository.saveAndFlush(mWeeklyQuestWorld);

        // Get all the mWeeklyQuestWorldList where number greater than or equals to DEFAULT_NUMBER
        defaultMWeeklyQuestWorldShouldBeFound("number.greaterOrEqualThan=" + DEFAULT_NUMBER);

        // Get all the mWeeklyQuestWorldList where number greater than or equals to UPDATED_NUMBER
        defaultMWeeklyQuestWorldShouldNotBeFound("number.greaterOrEqualThan=" + UPDATED_NUMBER);
    }

    @Test
    @Transactional
    public void getAllMWeeklyQuestWorldsByNumberIsLessThanSomething() throws Exception {
        // Initialize the database
        mWeeklyQuestWorldRepository.saveAndFlush(mWeeklyQuestWorld);

        // Get all the mWeeklyQuestWorldList where number less than or equals to DEFAULT_NUMBER
        defaultMWeeklyQuestWorldShouldNotBeFound("number.lessThan=" + DEFAULT_NUMBER);

        // Get all the mWeeklyQuestWorldList where number less than or equals to UPDATED_NUMBER
        defaultMWeeklyQuestWorldShouldBeFound("number.lessThan=" + UPDATED_NUMBER);
    }


    @Test
    @Transactional
    public void getAllMWeeklyQuestWorldsByStageUnlockPatternIsEqualToSomething() throws Exception {
        // Initialize the database
        mWeeklyQuestWorldRepository.saveAndFlush(mWeeklyQuestWorld);

        // Get all the mWeeklyQuestWorldList where stageUnlockPattern equals to DEFAULT_STAGE_UNLOCK_PATTERN
        defaultMWeeklyQuestWorldShouldBeFound("stageUnlockPattern.equals=" + DEFAULT_STAGE_UNLOCK_PATTERN);

        // Get all the mWeeklyQuestWorldList where stageUnlockPattern equals to UPDATED_STAGE_UNLOCK_PATTERN
        defaultMWeeklyQuestWorldShouldNotBeFound("stageUnlockPattern.equals=" + UPDATED_STAGE_UNLOCK_PATTERN);
    }

    @Test
    @Transactional
    public void getAllMWeeklyQuestWorldsByStageUnlockPatternIsInShouldWork() throws Exception {
        // Initialize the database
        mWeeklyQuestWorldRepository.saveAndFlush(mWeeklyQuestWorld);

        // Get all the mWeeklyQuestWorldList where stageUnlockPattern in DEFAULT_STAGE_UNLOCK_PATTERN or UPDATED_STAGE_UNLOCK_PATTERN
        defaultMWeeklyQuestWorldShouldBeFound("stageUnlockPattern.in=" + DEFAULT_STAGE_UNLOCK_PATTERN + "," + UPDATED_STAGE_UNLOCK_PATTERN);

        // Get all the mWeeklyQuestWorldList where stageUnlockPattern equals to UPDATED_STAGE_UNLOCK_PATTERN
        defaultMWeeklyQuestWorldShouldNotBeFound("stageUnlockPattern.in=" + UPDATED_STAGE_UNLOCK_PATTERN);
    }

    @Test
    @Transactional
    public void getAllMWeeklyQuestWorldsByStageUnlockPatternIsNullOrNotNull() throws Exception {
        // Initialize the database
        mWeeklyQuestWorldRepository.saveAndFlush(mWeeklyQuestWorld);

        // Get all the mWeeklyQuestWorldList where stageUnlockPattern is not null
        defaultMWeeklyQuestWorldShouldBeFound("stageUnlockPattern.specified=true");

        // Get all the mWeeklyQuestWorldList where stageUnlockPattern is null
        defaultMWeeklyQuestWorldShouldNotBeFound("stageUnlockPattern.specified=false");
    }

    @Test
    @Transactional
    public void getAllMWeeklyQuestWorldsByStageUnlockPatternIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mWeeklyQuestWorldRepository.saveAndFlush(mWeeklyQuestWorld);

        // Get all the mWeeklyQuestWorldList where stageUnlockPattern greater than or equals to DEFAULT_STAGE_UNLOCK_PATTERN
        defaultMWeeklyQuestWorldShouldBeFound("stageUnlockPattern.greaterOrEqualThan=" + DEFAULT_STAGE_UNLOCK_PATTERN);

        // Get all the mWeeklyQuestWorldList where stageUnlockPattern greater than or equals to UPDATED_STAGE_UNLOCK_PATTERN
        defaultMWeeklyQuestWorldShouldNotBeFound("stageUnlockPattern.greaterOrEqualThan=" + UPDATED_STAGE_UNLOCK_PATTERN);
    }

    @Test
    @Transactional
    public void getAllMWeeklyQuestWorldsByStageUnlockPatternIsLessThanSomething() throws Exception {
        // Initialize the database
        mWeeklyQuestWorldRepository.saveAndFlush(mWeeklyQuestWorld);

        // Get all the mWeeklyQuestWorldList where stageUnlockPattern less than or equals to DEFAULT_STAGE_UNLOCK_PATTERN
        defaultMWeeklyQuestWorldShouldNotBeFound("stageUnlockPattern.lessThan=" + DEFAULT_STAGE_UNLOCK_PATTERN);

        // Get all the mWeeklyQuestWorldList where stageUnlockPattern less than or equals to UPDATED_STAGE_UNLOCK_PATTERN
        defaultMWeeklyQuestWorldShouldBeFound("stageUnlockPattern.lessThan=" + UPDATED_STAGE_UNLOCK_PATTERN);
    }


    @Test
    @Transactional
    public void getAllMWeeklyQuestWorldsBySpecialRewardContentTypeIsEqualToSomething() throws Exception {
        // Initialize the database
        mWeeklyQuestWorldRepository.saveAndFlush(mWeeklyQuestWorld);

        // Get all the mWeeklyQuestWorldList where specialRewardContentType equals to DEFAULT_SPECIAL_REWARD_CONTENT_TYPE
        defaultMWeeklyQuestWorldShouldBeFound("specialRewardContentType.equals=" + DEFAULT_SPECIAL_REWARD_CONTENT_TYPE);

        // Get all the mWeeklyQuestWorldList where specialRewardContentType equals to UPDATED_SPECIAL_REWARD_CONTENT_TYPE
        defaultMWeeklyQuestWorldShouldNotBeFound("specialRewardContentType.equals=" + UPDATED_SPECIAL_REWARD_CONTENT_TYPE);
    }

    @Test
    @Transactional
    public void getAllMWeeklyQuestWorldsBySpecialRewardContentTypeIsInShouldWork() throws Exception {
        // Initialize the database
        mWeeklyQuestWorldRepository.saveAndFlush(mWeeklyQuestWorld);

        // Get all the mWeeklyQuestWorldList where specialRewardContentType in DEFAULT_SPECIAL_REWARD_CONTENT_TYPE or UPDATED_SPECIAL_REWARD_CONTENT_TYPE
        defaultMWeeklyQuestWorldShouldBeFound("specialRewardContentType.in=" + DEFAULT_SPECIAL_REWARD_CONTENT_TYPE + "," + UPDATED_SPECIAL_REWARD_CONTENT_TYPE);

        // Get all the mWeeklyQuestWorldList where specialRewardContentType equals to UPDATED_SPECIAL_REWARD_CONTENT_TYPE
        defaultMWeeklyQuestWorldShouldNotBeFound("specialRewardContentType.in=" + UPDATED_SPECIAL_REWARD_CONTENT_TYPE);
    }

    @Test
    @Transactional
    public void getAllMWeeklyQuestWorldsBySpecialRewardContentTypeIsNullOrNotNull() throws Exception {
        // Initialize the database
        mWeeklyQuestWorldRepository.saveAndFlush(mWeeklyQuestWorld);

        // Get all the mWeeklyQuestWorldList where specialRewardContentType is not null
        defaultMWeeklyQuestWorldShouldBeFound("specialRewardContentType.specified=true");

        // Get all the mWeeklyQuestWorldList where specialRewardContentType is null
        defaultMWeeklyQuestWorldShouldNotBeFound("specialRewardContentType.specified=false");
    }

    @Test
    @Transactional
    public void getAllMWeeklyQuestWorldsBySpecialRewardContentTypeIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mWeeklyQuestWorldRepository.saveAndFlush(mWeeklyQuestWorld);

        // Get all the mWeeklyQuestWorldList where specialRewardContentType greater than or equals to DEFAULT_SPECIAL_REWARD_CONTENT_TYPE
        defaultMWeeklyQuestWorldShouldBeFound("specialRewardContentType.greaterOrEqualThan=" + DEFAULT_SPECIAL_REWARD_CONTENT_TYPE);

        // Get all the mWeeklyQuestWorldList where specialRewardContentType greater than or equals to UPDATED_SPECIAL_REWARD_CONTENT_TYPE
        defaultMWeeklyQuestWorldShouldNotBeFound("specialRewardContentType.greaterOrEqualThan=" + UPDATED_SPECIAL_REWARD_CONTENT_TYPE);
    }

    @Test
    @Transactional
    public void getAllMWeeklyQuestWorldsBySpecialRewardContentTypeIsLessThanSomething() throws Exception {
        // Initialize the database
        mWeeklyQuestWorldRepository.saveAndFlush(mWeeklyQuestWorld);

        // Get all the mWeeklyQuestWorldList where specialRewardContentType less than or equals to DEFAULT_SPECIAL_REWARD_CONTENT_TYPE
        defaultMWeeklyQuestWorldShouldNotBeFound("specialRewardContentType.lessThan=" + DEFAULT_SPECIAL_REWARD_CONTENT_TYPE);

        // Get all the mWeeklyQuestWorldList where specialRewardContentType less than or equals to UPDATED_SPECIAL_REWARD_CONTENT_TYPE
        defaultMWeeklyQuestWorldShouldBeFound("specialRewardContentType.lessThan=" + UPDATED_SPECIAL_REWARD_CONTENT_TYPE);
    }


    @Test
    @Transactional
    public void getAllMWeeklyQuestWorldsBySpecialRewardContentIdIsEqualToSomething() throws Exception {
        // Initialize the database
        mWeeklyQuestWorldRepository.saveAndFlush(mWeeklyQuestWorld);

        // Get all the mWeeklyQuestWorldList where specialRewardContentId equals to DEFAULT_SPECIAL_REWARD_CONTENT_ID
        defaultMWeeklyQuestWorldShouldBeFound("specialRewardContentId.equals=" + DEFAULT_SPECIAL_REWARD_CONTENT_ID);

        // Get all the mWeeklyQuestWorldList where specialRewardContentId equals to UPDATED_SPECIAL_REWARD_CONTENT_ID
        defaultMWeeklyQuestWorldShouldNotBeFound("specialRewardContentId.equals=" + UPDATED_SPECIAL_REWARD_CONTENT_ID);
    }

    @Test
    @Transactional
    public void getAllMWeeklyQuestWorldsBySpecialRewardContentIdIsInShouldWork() throws Exception {
        // Initialize the database
        mWeeklyQuestWorldRepository.saveAndFlush(mWeeklyQuestWorld);

        // Get all the mWeeklyQuestWorldList where specialRewardContentId in DEFAULT_SPECIAL_REWARD_CONTENT_ID or UPDATED_SPECIAL_REWARD_CONTENT_ID
        defaultMWeeklyQuestWorldShouldBeFound("specialRewardContentId.in=" + DEFAULT_SPECIAL_REWARD_CONTENT_ID + "," + UPDATED_SPECIAL_REWARD_CONTENT_ID);

        // Get all the mWeeklyQuestWorldList where specialRewardContentId equals to UPDATED_SPECIAL_REWARD_CONTENT_ID
        defaultMWeeklyQuestWorldShouldNotBeFound("specialRewardContentId.in=" + UPDATED_SPECIAL_REWARD_CONTENT_ID);
    }

    @Test
    @Transactional
    public void getAllMWeeklyQuestWorldsBySpecialRewardContentIdIsNullOrNotNull() throws Exception {
        // Initialize the database
        mWeeklyQuestWorldRepository.saveAndFlush(mWeeklyQuestWorld);

        // Get all the mWeeklyQuestWorldList where specialRewardContentId is not null
        defaultMWeeklyQuestWorldShouldBeFound("specialRewardContentId.specified=true");

        // Get all the mWeeklyQuestWorldList where specialRewardContentId is null
        defaultMWeeklyQuestWorldShouldNotBeFound("specialRewardContentId.specified=false");
    }

    @Test
    @Transactional
    public void getAllMWeeklyQuestWorldsBySpecialRewardContentIdIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mWeeklyQuestWorldRepository.saveAndFlush(mWeeklyQuestWorld);

        // Get all the mWeeklyQuestWorldList where specialRewardContentId greater than or equals to DEFAULT_SPECIAL_REWARD_CONTENT_ID
        defaultMWeeklyQuestWorldShouldBeFound("specialRewardContentId.greaterOrEqualThan=" + DEFAULT_SPECIAL_REWARD_CONTENT_ID);

        // Get all the mWeeklyQuestWorldList where specialRewardContentId greater than or equals to UPDATED_SPECIAL_REWARD_CONTENT_ID
        defaultMWeeklyQuestWorldShouldNotBeFound("specialRewardContentId.greaterOrEqualThan=" + UPDATED_SPECIAL_REWARD_CONTENT_ID);
    }

    @Test
    @Transactional
    public void getAllMWeeklyQuestWorldsBySpecialRewardContentIdIsLessThanSomething() throws Exception {
        // Initialize the database
        mWeeklyQuestWorldRepository.saveAndFlush(mWeeklyQuestWorld);

        // Get all the mWeeklyQuestWorldList where specialRewardContentId less than or equals to DEFAULT_SPECIAL_REWARD_CONTENT_ID
        defaultMWeeklyQuestWorldShouldNotBeFound("specialRewardContentId.lessThan=" + DEFAULT_SPECIAL_REWARD_CONTENT_ID);

        // Get all the mWeeklyQuestWorldList where specialRewardContentId less than or equals to UPDATED_SPECIAL_REWARD_CONTENT_ID
        defaultMWeeklyQuestWorldShouldBeFound("specialRewardContentId.lessThan=" + UPDATED_SPECIAL_REWARD_CONTENT_ID);
    }


    @Test
    @Transactional
    public void getAllMWeeklyQuestWorldsByIsEnableCoopIsEqualToSomething() throws Exception {
        // Initialize the database
        mWeeklyQuestWorldRepository.saveAndFlush(mWeeklyQuestWorld);

        // Get all the mWeeklyQuestWorldList where isEnableCoop equals to DEFAULT_IS_ENABLE_COOP
        defaultMWeeklyQuestWorldShouldBeFound("isEnableCoop.equals=" + DEFAULT_IS_ENABLE_COOP);

        // Get all the mWeeklyQuestWorldList where isEnableCoop equals to UPDATED_IS_ENABLE_COOP
        defaultMWeeklyQuestWorldShouldNotBeFound("isEnableCoop.equals=" + UPDATED_IS_ENABLE_COOP);
    }

    @Test
    @Transactional
    public void getAllMWeeklyQuestWorldsByIsEnableCoopIsInShouldWork() throws Exception {
        // Initialize the database
        mWeeklyQuestWorldRepository.saveAndFlush(mWeeklyQuestWorld);

        // Get all the mWeeklyQuestWorldList where isEnableCoop in DEFAULT_IS_ENABLE_COOP or UPDATED_IS_ENABLE_COOP
        defaultMWeeklyQuestWorldShouldBeFound("isEnableCoop.in=" + DEFAULT_IS_ENABLE_COOP + "," + UPDATED_IS_ENABLE_COOP);

        // Get all the mWeeklyQuestWorldList where isEnableCoop equals to UPDATED_IS_ENABLE_COOP
        defaultMWeeklyQuestWorldShouldNotBeFound("isEnableCoop.in=" + UPDATED_IS_ENABLE_COOP);
    }

    @Test
    @Transactional
    public void getAllMWeeklyQuestWorldsByIsEnableCoopIsNullOrNotNull() throws Exception {
        // Initialize the database
        mWeeklyQuestWorldRepository.saveAndFlush(mWeeklyQuestWorld);

        // Get all the mWeeklyQuestWorldList where isEnableCoop is not null
        defaultMWeeklyQuestWorldShouldBeFound("isEnableCoop.specified=true");

        // Get all the mWeeklyQuestWorldList where isEnableCoop is null
        defaultMWeeklyQuestWorldShouldNotBeFound("isEnableCoop.specified=false");
    }

    @Test
    @Transactional
    public void getAllMWeeklyQuestWorldsByIsEnableCoopIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mWeeklyQuestWorldRepository.saveAndFlush(mWeeklyQuestWorld);

        // Get all the mWeeklyQuestWorldList where isEnableCoop greater than or equals to DEFAULT_IS_ENABLE_COOP
        defaultMWeeklyQuestWorldShouldBeFound("isEnableCoop.greaterOrEqualThan=" + DEFAULT_IS_ENABLE_COOP);

        // Get all the mWeeklyQuestWorldList where isEnableCoop greater than or equals to UPDATED_IS_ENABLE_COOP
        defaultMWeeklyQuestWorldShouldNotBeFound("isEnableCoop.greaterOrEqualThan=" + UPDATED_IS_ENABLE_COOP);
    }

    @Test
    @Transactional
    public void getAllMWeeklyQuestWorldsByIsEnableCoopIsLessThanSomething() throws Exception {
        // Initialize the database
        mWeeklyQuestWorldRepository.saveAndFlush(mWeeklyQuestWorld);

        // Get all the mWeeklyQuestWorldList where isEnableCoop less than or equals to DEFAULT_IS_ENABLE_COOP
        defaultMWeeklyQuestWorldShouldNotBeFound("isEnableCoop.lessThan=" + DEFAULT_IS_ENABLE_COOP);

        // Get all the mWeeklyQuestWorldList where isEnableCoop less than or equals to UPDATED_IS_ENABLE_COOP
        defaultMWeeklyQuestWorldShouldBeFound("isEnableCoop.lessThan=" + UPDATED_IS_ENABLE_COOP);
    }


    @Test
    @Transactional
    public void getAllMWeeklyQuestWorldsByMWeeklyQuestStageIsEqualToSomething() throws Exception {
        // Initialize the database
        MWeeklyQuestStage mWeeklyQuestStage = MWeeklyQuestStageResourceIT.createEntity(em);
        em.persist(mWeeklyQuestStage);
        em.flush();
        mWeeklyQuestWorld.addMWeeklyQuestStage(mWeeklyQuestStage);
        mWeeklyQuestWorldRepository.saveAndFlush(mWeeklyQuestWorld);
        Long mWeeklyQuestStageId = mWeeklyQuestStage.getId();

        // Get all the mWeeklyQuestWorldList where mWeeklyQuestStage equals to mWeeklyQuestStageId
        defaultMWeeklyQuestWorldShouldBeFound("mWeeklyQuestStageId.equals=" + mWeeklyQuestStageId);

        // Get all the mWeeklyQuestWorldList where mWeeklyQuestStage equals to mWeeklyQuestStageId + 1
        defaultMWeeklyQuestWorldShouldNotBeFound("mWeeklyQuestStageId.equals=" + (mWeeklyQuestStageId + 1));
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultMWeeklyQuestWorldShouldBeFound(String filter) throws Exception {
        restMWeeklyQuestWorldMockMvc.perform(get("/api/m-weekly-quest-worlds?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(mWeeklyQuestWorld.getId().intValue())))
            .andExpect(jsonPath("$.[*].setId").value(hasItem(DEFAULT_SET_ID)))
            .andExpect(jsonPath("$.[*].number").value(hasItem(DEFAULT_NUMBER)))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME.toString())))
            .andExpect(jsonPath("$.[*].imagePath").value(hasItem(DEFAULT_IMAGE_PATH.toString())))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION.toString())))
            .andExpect(jsonPath("$.[*].stageUnlockPattern").value(hasItem(DEFAULT_STAGE_UNLOCK_PATTERN)))
            .andExpect(jsonPath("$.[*].arousalBanner").value(hasItem(DEFAULT_AROUSAL_BANNER.toString())))
            .andExpect(jsonPath("$.[*].specialRewardContentType").value(hasItem(DEFAULT_SPECIAL_REWARD_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].specialRewardContentId").value(hasItem(DEFAULT_SPECIAL_REWARD_CONTENT_ID)))
            .andExpect(jsonPath("$.[*].isEnableCoop").value(hasItem(DEFAULT_IS_ENABLE_COOP)));

        // Check, that the count call also returns 1
        restMWeeklyQuestWorldMockMvc.perform(get("/api/m-weekly-quest-worlds/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultMWeeklyQuestWorldShouldNotBeFound(String filter) throws Exception {
        restMWeeklyQuestWorldMockMvc.perform(get("/api/m-weekly-quest-worlds?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restMWeeklyQuestWorldMockMvc.perform(get("/api/m-weekly-quest-worlds/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("0"));
    }


    @Test
    @Transactional
    public void getNonExistingMWeeklyQuestWorld() throws Exception {
        // Get the mWeeklyQuestWorld
        restMWeeklyQuestWorldMockMvc.perform(get("/api/m-weekly-quest-worlds/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateMWeeklyQuestWorld() throws Exception {
        // Initialize the database
        mWeeklyQuestWorldRepository.saveAndFlush(mWeeklyQuestWorld);

        int databaseSizeBeforeUpdate = mWeeklyQuestWorldRepository.findAll().size();

        // Update the mWeeklyQuestWorld
        MWeeklyQuestWorld updatedMWeeklyQuestWorld = mWeeklyQuestWorldRepository.findById(mWeeklyQuestWorld.getId()).get();
        // Disconnect from session so that the updates on updatedMWeeklyQuestWorld are not directly saved in db
        em.detach(updatedMWeeklyQuestWorld);
        updatedMWeeklyQuestWorld
            .setId(UPDATED_SET_ID)
            .number(UPDATED_NUMBER)
            .name(UPDATED_NAME)
            .imagePath(UPDATED_IMAGE_PATH)
            .description(UPDATED_DESCRIPTION)
            .stageUnlockPattern(UPDATED_STAGE_UNLOCK_PATTERN)
            .arousalBanner(UPDATED_AROUSAL_BANNER)
            .specialRewardContentType(UPDATED_SPECIAL_REWARD_CONTENT_TYPE)
            .specialRewardContentId(UPDATED_SPECIAL_REWARD_CONTENT_ID)
            .isEnableCoop(UPDATED_IS_ENABLE_COOP);
        MWeeklyQuestWorldDTO mWeeklyQuestWorldDTO = mWeeklyQuestWorldMapper.toDto(updatedMWeeklyQuestWorld);

        restMWeeklyQuestWorldMockMvc.perform(put("/api/m-weekly-quest-worlds")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mWeeklyQuestWorldDTO)))
            .andExpect(status().isOk());

        // Validate the MWeeklyQuestWorld in the database
        List<MWeeklyQuestWorld> mWeeklyQuestWorldList = mWeeklyQuestWorldRepository.findAll();
        assertThat(mWeeklyQuestWorldList).hasSize(databaseSizeBeforeUpdate);
        MWeeklyQuestWorld testMWeeklyQuestWorld = mWeeklyQuestWorldList.get(mWeeklyQuestWorldList.size() - 1);
        assertThat(testMWeeklyQuestWorld.getSetId()).isEqualTo(UPDATED_SET_ID);
        assertThat(testMWeeklyQuestWorld.getNumber()).isEqualTo(UPDATED_NUMBER);
        assertThat(testMWeeklyQuestWorld.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testMWeeklyQuestWorld.getImagePath()).isEqualTo(UPDATED_IMAGE_PATH);
        assertThat(testMWeeklyQuestWorld.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
        assertThat(testMWeeklyQuestWorld.getStageUnlockPattern()).isEqualTo(UPDATED_STAGE_UNLOCK_PATTERN);
        assertThat(testMWeeklyQuestWorld.getArousalBanner()).isEqualTo(UPDATED_AROUSAL_BANNER);
        assertThat(testMWeeklyQuestWorld.getSpecialRewardContentType()).isEqualTo(UPDATED_SPECIAL_REWARD_CONTENT_TYPE);
        assertThat(testMWeeklyQuestWorld.getSpecialRewardContentId()).isEqualTo(UPDATED_SPECIAL_REWARD_CONTENT_ID);
        assertThat(testMWeeklyQuestWorld.getIsEnableCoop()).isEqualTo(UPDATED_IS_ENABLE_COOP);
    }

    @Test
    @Transactional
    public void updateNonExistingMWeeklyQuestWorld() throws Exception {
        int databaseSizeBeforeUpdate = mWeeklyQuestWorldRepository.findAll().size();

        // Create the MWeeklyQuestWorld
        MWeeklyQuestWorldDTO mWeeklyQuestWorldDTO = mWeeklyQuestWorldMapper.toDto(mWeeklyQuestWorld);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restMWeeklyQuestWorldMockMvc.perform(put("/api/m-weekly-quest-worlds")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mWeeklyQuestWorldDTO)))
            .andExpect(status().isBadRequest());

        // Validate the MWeeklyQuestWorld in the database
        List<MWeeklyQuestWorld> mWeeklyQuestWorldList = mWeeklyQuestWorldRepository.findAll();
        assertThat(mWeeklyQuestWorldList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteMWeeklyQuestWorld() throws Exception {
        // Initialize the database
        mWeeklyQuestWorldRepository.saveAndFlush(mWeeklyQuestWorld);

        int databaseSizeBeforeDelete = mWeeklyQuestWorldRepository.findAll().size();

        // Delete the mWeeklyQuestWorld
        restMWeeklyQuestWorldMockMvc.perform(delete("/api/m-weekly-quest-worlds/{id}", mWeeklyQuestWorld.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database is empty
        List<MWeeklyQuestWorld> mWeeklyQuestWorldList = mWeeklyQuestWorldRepository.findAll();
        assertThat(mWeeklyQuestWorldList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(MWeeklyQuestWorld.class);
        MWeeklyQuestWorld mWeeklyQuestWorld1 = new MWeeklyQuestWorld();
        mWeeklyQuestWorld1.setId(1L);
        MWeeklyQuestWorld mWeeklyQuestWorld2 = new MWeeklyQuestWorld();
        mWeeklyQuestWorld2.setId(mWeeklyQuestWorld1.getId());
        assertThat(mWeeklyQuestWorld1).isEqualTo(mWeeklyQuestWorld2);
        mWeeklyQuestWorld2.setId(2L);
        assertThat(mWeeklyQuestWorld1).isNotEqualTo(mWeeklyQuestWorld2);
        mWeeklyQuestWorld1.setId(null);
        assertThat(mWeeklyQuestWorld1).isNotEqualTo(mWeeklyQuestWorld2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(MWeeklyQuestWorldDTO.class);
        MWeeklyQuestWorldDTO mWeeklyQuestWorldDTO1 = new MWeeklyQuestWorldDTO();
        mWeeklyQuestWorldDTO1.setId(1L);
        MWeeklyQuestWorldDTO mWeeklyQuestWorldDTO2 = new MWeeklyQuestWorldDTO();
        assertThat(mWeeklyQuestWorldDTO1).isNotEqualTo(mWeeklyQuestWorldDTO2);
        mWeeklyQuestWorldDTO2.setId(mWeeklyQuestWorldDTO1.getId());
        assertThat(mWeeklyQuestWorldDTO1).isEqualTo(mWeeklyQuestWorldDTO2);
        mWeeklyQuestWorldDTO2.setId(2L);
        assertThat(mWeeklyQuestWorldDTO1).isNotEqualTo(mWeeklyQuestWorldDTO2);
        mWeeklyQuestWorldDTO1.setId(null);
        assertThat(mWeeklyQuestWorldDTO1).isNotEqualTo(mWeeklyQuestWorldDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(mWeeklyQuestWorldMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(mWeeklyQuestWorldMapper.fromId(null)).isNull();
    }
}
