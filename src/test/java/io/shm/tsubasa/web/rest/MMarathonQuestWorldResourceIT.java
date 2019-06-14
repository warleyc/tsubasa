package io.shm.tsubasa.web.rest;

import io.shm.tsubasa.TsubasaApp;
import io.shm.tsubasa.domain.MMarathonQuestWorld;
import io.shm.tsubasa.domain.MMarathonQuestStage;
import io.shm.tsubasa.repository.MMarathonQuestWorldRepository;
import io.shm.tsubasa.service.MMarathonQuestWorldService;
import io.shm.tsubasa.service.dto.MMarathonQuestWorldDTO;
import io.shm.tsubasa.service.mapper.MMarathonQuestWorldMapper;
import io.shm.tsubasa.web.rest.errors.ExceptionTranslator;
import io.shm.tsubasa.service.dto.MMarathonQuestWorldCriteria;
import io.shm.tsubasa.service.MMarathonQuestWorldQueryService;

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
 * Integration tests for the {@Link MMarathonQuestWorldResource} REST controller.
 */
@SpringBootTest(classes = TsubasaApp.class)
public class MMarathonQuestWorldResourceIT {

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
    private MMarathonQuestWorldRepository mMarathonQuestWorldRepository;

    @Autowired
    private MMarathonQuestWorldMapper mMarathonQuestWorldMapper;

    @Autowired
    private MMarathonQuestWorldService mMarathonQuestWorldService;

    @Autowired
    private MMarathonQuestWorldQueryService mMarathonQuestWorldQueryService;

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

    private MockMvc restMMarathonQuestWorldMockMvc;

    private MMarathonQuestWorld mMarathonQuestWorld;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final MMarathonQuestWorldResource mMarathonQuestWorldResource = new MMarathonQuestWorldResource(mMarathonQuestWorldService, mMarathonQuestWorldQueryService);
        this.restMMarathonQuestWorldMockMvc = MockMvcBuilders.standaloneSetup(mMarathonQuestWorldResource)
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
    public static MMarathonQuestWorld createEntity(EntityManager em) {
        MMarathonQuestWorld mMarathonQuestWorld = new MMarathonQuestWorld()
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
        return mMarathonQuestWorld;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static MMarathonQuestWorld createUpdatedEntity(EntityManager em) {
        MMarathonQuestWorld mMarathonQuestWorld = new MMarathonQuestWorld()
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
        return mMarathonQuestWorld;
    }

    @BeforeEach
    public void initTest() {
        mMarathonQuestWorld = createEntity(em);
    }

    @Test
    @Transactional
    public void createMMarathonQuestWorld() throws Exception {
        int databaseSizeBeforeCreate = mMarathonQuestWorldRepository.findAll().size();

        // Create the MMarathonQuestWorld
        MMarathonQuestWorldDTO mMarathonQuestWorldDTO = mMarathonQuestWorldMapper.toDto(mMarathonQuestWorld);
        restMMarathonQuestWorldMockMvc.perform(post("/api/m-marathon-quest-worlds")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mMarathonQuestWorldDTO)))
            .andExpect(status().isCreated());

        // Validate the MMarathonQuestWorld in the database
        List<MMarathonQuestWorld> mMarathonQuestWorldList = mMarathonQuestWorldRepository.findAll();
        assertThat(mMarathonQuestWorldList).hasSize(databaseSizeBeforeCreate + 1);
        MMarathonQuestWorld testMMarathonQuestWorld = mMarathonQuestWorldList.get(mMarathonQuestWorldList.size() - 1);
        assertThat(testMMarathonQuestWorld.getSetId()).isEqualTo(DEFAULT_SET_ID);
        assertThat(testMMarathonQuestWorld.getNumber()).isEqualTo(DEFAULT_NUMBER);
        assertThat(testMMarathonQuestWorld.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testMMarathonQuestWorld.getImagePath()).isEqualTo(DEFAULT_IMAGE_PATH);
        assertThat(testMMarathonQuestWorld.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
        assertThat(testMMarathonQuestWorld.getStageUnlockPattern()).isEqualTo(DEFAULT_STAGE_UNLOCK_PATTERN);
        assertThat(testMMarathonQuestWorld.getArousalBanner()).isEqualTo(DEFAULT_AROUSAL_BANNER);
        assertThat(testMMarathonQuestWorld.getSpecialRewardContentType()).isEqualTo(DEFAULT_SPECIAL_REWARD_CONTENT_TYPE);
        assertThat(testMMarathonQuestWorld.getSpecialRewardContentId()).isEqualTo(DEFAULT_SPECIAL_REWARD_CONTENT_ID);
        assertThat(testMMarathonQuestWorld.getIsEnableCoop()).isEqualTo(DEFAULT_IS_ENABLE_COOP);
    }

    @Test
    @Transactional
    public void createMMarathonQuestWorldWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = mMarathonQuestWorldRepository.findAll().size();

        // Create the MMarathonQuestWorld with an existing ID
        mMarathonQuestWorld.setId(1L);
        MMarathonQuestWorldDTO mMarathonQuestWorldDTO = mMarathonQuestWorldMapper.toDto(mMarathonQuestWorld);

        // An entity with an existing ID cannot be created, so this API call must fail
        restMMarathonQuestWorldMockMvc.perform(post("/api/m-marathon-quest-worlds")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mMarathonQuestWorldDTO)))
            .andExpect(status().isBadRequest());

        // Validate the MMarathonQuestWorld in the database
        List<MMarathonQuestWorld> mMarathonQuestWorldList = mMarathonQuestWorldRepository.findAll();
        assertThat(mMarathonQuestWorldList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkSetIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = mMarathonQuestWorldRepository.findAll().size();
        // set the field null
        mMarathonQuestWorld.setSetId(null);

        // Create the MMarathonQuestWorld, which fails.
        MMarathonQuestWorldDTO mMarathonQuestWorldDTO = mMarathonQuestWorldMapper.toDto(mMarathonQuestWorld);

        restMMarathonQuestWorldMockMvc.perform(post("/api/m-marathon-quest-worlds")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mMarathonQuestWorldDTO)))
            .andExpect(status().isBadRequest());

        List<MMarathonQuestWorld> mMarathonQuestWorldList = mMarathonQuestWorldRepository.findAll();
        assertThat(mMarathonQuestWorldList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkNumberIsRequired() throws Exception {
        int databaseSizeBeforeTest = mMarathonQuestWorldRepository.findAll().size();
        // set the field null
        mMarathonQuestWorld.setNumber(null);

        // Create the MMarathonQuestWorld, which fails.
        MMarathonQuestWorldDTO mMarathonQuestWorldDTO = mMarathonQuestWorldMapper.toDto(mMarathonQuestWorld);

        restMMarathonQuestWorldMockMvc.perform(post("/api/m-marathon-quest-worlds")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mMarathonQuestWorldDTO)))
            .andExpect(status().isBadRequest());

        List<MMarathonQuestWorld> mMarathonQuestWorldList = mMarathonQuestWorldRepository.findAll();
        assertThat(mMarathonQuestWorldList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkStageUnlockPatternIsRequired() throws Exception {
        int databaseSizeBeforeTest = mMarathonQuestWorldRepository.findAll().size();
        // set the field null
        mMarathonQuestWorld.setStageUnlockPattern(null);

        // Create the MMarathonQuestWorld, which fails.
        MMarathonQuestWorldDTO mMarathonQuestWorldDTO = mMarathonQuestWorldMapper.toDto(mMarathonQuestWorld);

        restMMarathonQuestWorldMockMvc.perform(post("/api/m-marathon-quest-worlds")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mMarathonQuestWorldDTO)))
            .andExpect(status().isBadRequest());

        List<MMarathonQuestWorld> mMarathonQuestWorldList = mMarathonQuestWorldRepository.findAll();
        assertThat(mMarathonQuestWorldList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkIsEnableCoopIsRequired() throws Exception {
        int databaseSizeBeforeTest = mMarathonQuestWorldRepository.findAll().size();
        // set the field null
        mMarathonQuestWorld.setIsEnableCoop(null);

        // Create the MMarathonQuestWorld, which fails.
        MMarathonQuestWorldDTO mMarathonQuestWorldDTO = mMarathonQuestWorldMapper.toDto(mMarathonQuestWorld);

        restMMarathonQuestWorldMockMvc.perform(post("/api/m-marathon-quest-worlds")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mMarathonQuestWorldDTO)))
            .andExpect(status().isBadRequest());

        List<MMarathonQuestWorld> mMarathonQuestWorldList = mMarathonQuestWorldRepository.findAll();
        assertThat(mMarathonQuestWorldList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllMMarathonQuestWorlds() throws Exception {
        // Initialize the database
        mMarathonQuestWorldRepository.saveAndFlush(mMarathonQuestWorld);

        // Get all the mMarathonQuestWorldList
        restMMarathonQuestWorldMockMvc.perform(get("/api/m-marathon-quest-worlds?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(mMarathonQuestWorld.getId().intValue())))
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
    public void getMMarathonQuestWorld() throws Exception {
        // Initialize the database
        mMarathonQuestWorldRepository.saveAndFlush(mMarathonQuestWorld);

        // Get the mMarathonQuestWorld
        restMMarathonQuestWorldMockMvc.perform(get("/api/m-marathon-quest-worlds/{id}", mMarathonQuestWorld.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(mMarathonQuestWorld.getId().intValue()))
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
    public void getAllMMarathonQuestWorldsBySetIdIsEqualToSomething() throws Exception {
        // Initialize the database
        mMarathonQuestWorldRepository.saveAndFlush(mMarathonQuestWorld);

        // Get all the mMarathonQuestWorldList where setId equals to DEFAULT_SET_ID
        defaultMMarathonQuestWorldShouldBeFound("setId.equals=" + DEFAULT_SET_ID);

        // Get all the mMarathonQuestWorldList where setId equals to UPDATED_SET_ID
        defaultMMarathonQuestWorldShouldNotBeFound("setId.equals=" + UPDATED_SET_ID);
    }

    @Test
    @Transactional
    public void getAllMMarathonQuestWorldsBySetIdIsInShouldWork() throws Exception {
        // Initialize the database
        mMarathonQuestWorldRepository.saveAndFlush(mMarathonQuestWorld);

        // Get all the mMarathonQuestWorldList where setId in DEFAULT_SET_ID or UPDATED_SET_ID
        defaultMMarathonQuestWorldShouldBeFound("setId.in=" + DEFAULT_SET_ID + "," + UPDATED_SET_ID);

        // Get all the mMarathonQuestWorldList where setId equals to UPDATED_SET_ID
        defaultMMarathonQuestWorldShouldNotBeFound("setId.in=" + UPDATED_SET_ID);
    }

    @Test
    @Transactional
    public void getAllMMarathonQuestWorldsBySetIdIsNullOrNotNull() throws Exception {
        // Initialize the database
        mMarathonQuestWorldRepository.saveAndFlush(mMarathonQuestWorld);

        // Get all the mMarathonQuestWorldList where setId is not null
        defaultMMarathonQuestWorldShouldBeFound("setId.specified=true");

        // Get all the mMarathonQuestWorldList where setId is null
        defaultMMarathonQuestWorldShouldNotBeFound("setId.specified=false");
    }

    @Test
    @Transactional
    public void getAllMMarathonQuestWorldsBySetIdIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mMarathonQuestWorldRepository.saveAndFlush(mMarathonQuestWorld);

        // Get all the mMarathonQuestWorldList where setId greater than or equals to DEFAULT_SET_ID
        defaultMMarathonQuestWorldShouldBeFound("setId.greaterOrEqualThan=" + DEFAULT_SET_ID);

        // Get all the mMarathonQuestWorldList where setId greater than or equals to UPDATED_SET_ID
        defaultMMarathonQuestWorldShouldNotBeFound("setId.greaterOrEqualThan=" + UPDATED_SET_ID);
    }

    @Test
    @Transactional
    public void getAllMMarathonQuestWorldsBySetIdIsLessThanSomething() throws Exception {
        // Initialize the database
        mMarathonQuestWorldRepository.saveAndFlush(mMarathonQuestWorld);

        // Get all the mMarathonQuestWorldList where setId less than or equals to DEFAULT_SET_ID
        defaultMMarathonQuestWorldShouldNotBeFound("setId.lessThan=" + DEFAULT_SET_ID);

        // Get all the mMarathonQuestWorldList where setId less than or equals to UPDATED_SET_ID
        defaultMMarathonQuestWorldShouldBeFound("setId.lessThan=" + UPDATED_SET_ID);
    }


    @Test
    @Transactional
    public void getAllMMarathonQuestWorldsByNumberIsEqualToSomething() throws Exception {
        // Initialize the database
        mMarathonQuestWorldRepository.saveAndFlush(mMarathonQuestWorld);

        // Get all the mMarathonQuestWorldList where number equals to DEFAULT_NUMBER
        defaultMMarathonQuestWorldShouldBeFound("number.equals=" + DEFAULT_NUMBER);

        // Get all the mMarathonQuestWorldList where number equals to UPDATED_NUMBER
        defaultMMarathonQuestWorldShouldNotBeFound("number.equals=" + UPDATED_NUMBER);
    }

    @Test
    @Transactional
    public void getAllMMarathonQuestWorldsByNumberIsInShouldWork() throws Exception {
        // Initialize the database
        mMarathonQuestWorldRepository.saveAndFlush(mMarathonQuestWorld);

        // Get all the mMarathonQuestWorldList where number in DEFAULT_NUMBER or UPDATED_NUMBER
        defaultMMarathonQuestWorldShouldBeFound("number.in=" + DEFAULT_NUMBER + "," + UPDATED_NUMBER);

        // Get all the mMarathonQuestWorldList where number equals to UPDATED_NUMBER
        defaultMMarathonQuestWorldShouldNotBeFound("number.in=" + UPDATED_NUMBER);
    }

    @Test
    @Transactional
    public void getAllMMarathonQuestWorldsByNumberIsNullOrNotNull() throws Exception {
        // Initialize the database
        mMarathonQuestWorldRepository.saveAndFlush(mMarathonQuestWorld);

        // Get all the mMarathonQuestWorldList where number is not null
        defaultMMarathonQuestWorldShouldBeFound("number.specified=true");

        // Get all the mMarathonQuestWorldList where number is null
        defaultMMarathonQuestWorldShouldNotBeFound("number.specified=false");
    }

    @Test
    @Transactional
    public void getAllMMarathonQuestWorldsByNumberIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mMarathonQuestWorldRepository.saveAndFlush(mMarathonQuestWorld);

        // Get all the mMarathonQuestWorldList where number greater than or equals to DEFAULT_NUMBER
        defaultMMarathonQuestWorldShouldBeFound("number.greaterOrEqualThan=" + DEFAULT_NUMBER);

        // Get all the mMarathonQuestWorldList where number greater than or equals to UPDATED_NUMBER
        defaultMMarathonQuestWorldShouldNotBeFound("number.greaterOrEqualThan=" + UPDATED_NUMBER);
    }

    @Test
    @Transactional
    public void getAllMMarathonQuestWorldsByNumberIsLessThanSomething() throws Exception {
        // Initialize the database
        mMarathonQuestWorldRepository.saveAndFlush(mMarathonQuestWorld);

        // Get all the mMarathonQuestWorldList where number less than or equals to DEFAULT_NUMBER
        defaultMMarathonQuestWorldShouldNotBeFound("number.lessThan=" + DEFAULT_NUMBER);

        // Get all the mMarathonQuestWorldList where number less than or equals to UPDATED_NUMBER
        defaultMMarathonQuestWorldShouldBeFound("number.lessThan=" + UPDATED_NUMBER);
    }


    @Test
    @Transactional
    public void getAllMMarathonQuestWorldsByStageUnlockPatternIsEqualToSomething() throws Exception {
        // Initialize the database
        mMarathonQuestWorldRepository.saveAndFlush(mMarathonQuestWorld);

        // Get all the mMarathonQuestWorldList where stageUnlockPattern equals to DEFAULT_STAGE_UNLOCK_PATTERN
        defaultMMarathonQuestWorldShouldBeFound("stageUnlockPattern.equals=" + DEFAULT_STAGE_UNLOCK_PATTERN);

        // Get all the mMarathonQuestWorldList where stageUnlockPattern equals to UPDATED_STAGE_UNLOCK_PATTERN
        defaultMMarathonQuestWorldShouldNotBeFound("stageUnlockPattern.equals=" + UPDATED_STAGE_UNLOCK_PATTERN);
    }

    @Test
    @Transactional
    public void getAllMMarathonQuestWorldsByStageUnlockPatternIsInShouldWork() throws Exception {
        // Initialize the database
        mMarathonQuestWorldRepository.saveAndFlush(mMarathonQuestWorld);

        // Get all the mMarathonQuestWorldList where stageUnlockPattern in DEFAULT_STAGE_UNLOCK_PATTERN or UPDATED_STAGE_UNLOCK_PATTERN
        defaultMMarathonQuestWorldShouldBeFound("stageUnlockPattern.in=" + DEFAULT_STAGE_UNLOCK_PATTERN + "," + UPDATED_STAGE_UNLOCK_PATTERN);

        // Get all the mMarathonQuestWorldList where stageUnlockPattern equals to UPDATED_STAGE_UNLOCK_PATTERN
        defaultMMarathonQuestWorldShouldNotBeFound("stageUnlockPattern.in=" + UPDATED_STAGE_UNLOCK_PATTERN);
    }

    @Test
    @Transactional
    public void getAllMMarathonQuestWorldsByStageUnlockPatternIsNullOrNotNull() throws Exception {
        // Initialize the database
        mMarathonQuestWorldRepository.saveAndFlush(mMarathonQuestWorld);

        // Get all the mMarathonQuestWorldList where stageUnlockPattern is not null
        defaultMMarathonQuestWorldShouldBeFound("stageUnlockPattern.specified=true");

        // Get all the mMarathonQuestWorldList where stageUnlockPattern is null
        defaultMMarathonQuestWorldShouldNotBeFound("stageUnlockPattern.specified=false");
    }

    @Test
    @Transactional
    public void getAllMMarathonQuestWorldsByStageUnlockPatternIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mMarathonQuestWorldRepository.saveAndFlush(mMarathonQuestWorld);

        // Get all the mMarathonQuestWorldList where stageUnlockPattern greater than or equals to DEFAULT_STAGE_UNLOCK_PATTERN
        defaultMMarathonQuestWorldShouldBeFound("stageUnlockPattern.greaterOrEqualThan=" + DEFAULT_STAGE_UNLOCK_PATTERN);

        // Get all the mMarathonQuestWorldList where stageUnlockPattern greater than or equals to UPDATED_STAGE_UNLOCK_PATTERN
        defaultMMarathonQuestWorldShouldNotBeFound("stageUnlockPattern.greaterOrEqualThan=" + UPDATED_STAGE_UNLOCK_PATTERN);
    }

    @Test
    @Transactional
    public void getAllMMarathonQuestWorldsByStageUnlockPatternIsLessThanSomething() throws Exception {
        // Initialize the database
        mMarathonQuestWorldRepository.saveAndFlush(mMarathonQuestWorld);

        // Get all the mMarathonQuestWorldList where stageUnlockPattern less than or equals to DEFAULT_STAGE_UNLOCK_PATTERN
        defaultMMarathonQuestWorldShouldNotBeFound("stageUnlockPattern.lessThan=" + DEFAULT_STAGE_UNLOCK_PATTERN);

        // Get all the mMarathonQuestWorldList where stageUnlockPattern less than or equals to UPDATED_STAGE_UNLOCK_PATTERN
        defaultMMarathonQuestWorldShouldBeFound("stageUnlockPattern.lessThan=" + UPDATED_STAGE_UNLOCK_PATTERN);
    }


    @Test
    @Transactional
    public void getAllMMarathonQuestWorldsBySpecialRewardContentTypeIsEqualToSomething() throws Exception {
        // Initialize the database
        mMarathonQuestWorldRepository.saveAndFlush(mMarathonQuestWorld);

        // Get all the mMarathonQuestWorldList where specialRewardContentType equals to DEFAULT_SPECIAL_REWARD_CONTENT_TYPE
        defaultMMarathonQuestWorldShouldBeFound("specialRewardContentType.equals=" + DEFAULT_SPECIAL_REWARD_CONTENT_TYPE);

        // Get all the mMarathonQuestWorldList where specialRewardContentType equals to UPDATED_SPECIAL_REWARD_CONTENT_TYPE
        defaultMMarathonQuestWorldShouldNotBeFound("specialRewardContentType.equals=" + UPDATED_SPECIAL_REWARD_CONTENT_TYPE);
    }

    @Test
    @Transactional
    public void getAllMMarathonQuestWorldsBySpecialRewardContentTypeIsInShouldWork() throws Exception {
        // Initialize the database
        mMarathonQuestWorldRepository.saveAndFlush(mMarathonQuestWorld);

        // Get all the mMarathonQuestWorldList where specialRewardContentType in DEFAULT_SPECIAL_REWARD_CONTENT_TYPE or UPDATED_SPECIAL_REWARD_CONTENT_TYPE
        defaultMMarathonQuestWorldShouldBeFound("specialRewardContentType.in=" + DEFAULT_SPECIAL_REWARD_CONTENT_TYPE + "," + UPDATED_SPECIAL_REWARD_CONTENT_TYPE);

        // Get all the mMarathonQuestWorldList where specialRewardContentType equals to UPDATED_SPECIAL_REWARD_CONTENT_TYPE
        defaultMMarathonQuestWorldShouldNotBeFound("specialRewardContentType.in=" + UPDATED_SPECIAL_REWARD_CONTENT_TYPE);
    }

    @Test
    @Transactional
    public void getAllMMarathonQuestWorldsBySpecialRewardContentTypeIsNullOrNotNull() throws Exception {
        // Initialize the database
        mMarathonQuestWorldRepository.saveAndFlush(mMarathonQuestWorld);

        // Get all the mMarathonQuestWorldList where specialRewardContentType is not null
        defaultMMarathonQuestWorldShouldBeFound("specialRewardContentType.specified=true");

        // Get all the mMarathonQuestWorldList where specialRewardContentType is null
        defaultMMarathonQuestWorldShouldNotBeFound("specialRewardContentType.specified=false");
    }

    @Test
    @Transactional
    public void getAllMMarathonQuestWorldsBySpecialRewardContentTypeIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mMarathonQuestWorldRepository.saveAndFlush(mMarathonQuestWorld);

        // Get all the mMarathonQuestWorldList where specialRewardContentType greater than or equals to DEFAULT_SPECIAL_REWARD_CONTENT_TYPE
        defaultMMarathonQuestWorldShouldBeFound("specialRewardContentType.greaterOrEqualThan=" + DEFAULT_SPECIAL_REWARD_CONTENT_TYPE);

        // Get all the mMarathonQuestWorldList where specialRewardContentType greater than or equals to UPDATED_SPECIAL_REWARD_CONTENT_TYPE
        defaultMMarathonQuestWorldShouldNotBeFound("specialRewardContentType.greaterOrEqualThan=" + UPDATED_SPECIAL_REWARD_CONTENT_TYPE);
    }

    @Test
    @Transactional
    public void getAllMMarathonQuestWorldsBySpecialRewardContentTypeIsLessThanSomething() throws Exception {
        // Initialize the database
        mMarathonQuestWorldRepository.saveAndFlush(mMarathonQuestWorld);

        // Get all the mMarathonQuestWorldList where specialRewardContentType less than or equals to DEFAULT_SPECIAL_REWARD_CONTENT_TYPE
        defaultMMarathonQuestWorldShouldNotBeFound("specialRewardContentType.lessThan=" + DEFAULT_SPECIAL_REWARD_CONTENT_TYPE);

        // Get all the mMarathonQuestWorldList where specialRewardContentType less than or equals to UPDATED_SPECIAL_REWARD_CONTENT_TYPE
        defaultMMarathonQuestWorldShouldBeFound("specialRewardContentType.lessThan=" + UPDATED_SPECIAL_REWARD_CONTENT_TYPE);
    }


    @Test
    @Transactional
    public void getAllMMarathonQuestWorldsBySpecialRewardContentIdIsEqualToSomething() throws Exception {
        // Initialize the database
        mMarathonQuestWorldRepository.saveAndFlush(mMarathonQuestWorld);

        // Get all the mMarathonQuestWorldList where specialRewardContentId equals to DEFAULT_SPECIAL_REWARD_CONTENT_ID
        defaultMMarathonQuestWorldShouldBeFound("specialRewardContentId.equals=" + DEFAULT_SPECIAL_REWARD_CONTENT_ID);

        // Get all the mMarathonQuestWorldList where specialRewardContentId equals to UPDATED_SPECIAL_REWARD_CONTENT_ID
        defaultMMarathonQuestWorldShouldNotBeFound("specialRewardContentId.equals=" + UPDATED_SPECIAL_REWARD_CONTENT_ID);
    }

    @Test
    @Transactional
    public void getAllMMarathonQuestWorldsBySpecialRewardContentIdIsInShouldWork() throws Exception {
        // Initialize the database
        mMarathonQuestWorldRepository.saveAndFlush(mMarathonQuestWorld);

        // Get all the mMarathonQuestWorldList where specialRewardContentId in DEFAULT_SPECIAL_REWARD_CONTENT_ID or UPDATED_SPECIAL_REWARD_CONTENT_ID
        defaultMMarathonQuestWorldShouldBeFound("specialRewardContentId.in=" + DEFAULT_SPECIAL_REWARD_CONTENT_ID + "," + UPDATED_SPECIAL_REWARD_CONTENT_ID);

        // Get all the mMarathonQuestWorldList where specialRewardContentId equals to UPDATED_SPECIAL_REWARD_CONTENT_ID
        defaultMMarathonQuestWorldShouldNotBeFound("specialRewardContentId.in=" + UPDATED_SPECIAL_REWARD_CONTENT_ID);
    }

    @Test
    @Transactional
    public void getAllMMarathonQuestWorldsBySpecialRewardContentIdIsNullOrNotNull() throws Exception {
        // Initialize the database
        mMarathonQuestWorldRepository.saveAndFlush(mMarathonQuestWorld);

        // Get all the mMarathonQuestWorldList where specialRewardContentId is not null
        defaultMMarathonQuestWorldShouldBeFound("specialRewardContentId.specified=true");

        // Get all the mMarathonQuestWorldList where specialRewardContentId is null
        defaultMMarathonQuestWorldShouldNotBeFound("specialRewardContentId.specified=false");
    }

    @Test
    @Transactional
    public void getAllMMarathonQuestWorldsBySpecialRewardContentIdIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mMarathonQuestWorldRepository.saveAndFlush(mMarathonQuestWorld);

        // Get all the mMarathonQuestWorldList where specialRewardContentId greater than or equals to DEFAULT_SPECIAL_REWARD_CONTENT_ID
        defaultMMarathonQuestWorldShouldBeFound("specialRewardContentId.greaterOrEqualThan=" + DEFAULT_SPECIAL_REWARD_CONTENT_ID);

        // Get all the mMarathonQuestWorldList where specialRewardContentId greater than or equals to UPDATED_SPECIAL_REWARD_CONTENT_ID
        defaultMMarathonQuestWorldShouldNotBeFound("specialRewardContentId.greaterOrEqualThan=" + UPDATED_SPECIAL_REWARD_CONTENT_ID);
    }

    @Test
    @Transactional
    public void getAllMMarathonQuestWorldsBySpecialRewardContentIdIsLessThanSomething() throws Exception {
        // Initialize the database
        mMarathonQuestWorldRepository.saveAndFlush(mMarathonQuestWorld);

        // Get all the mMarathonQuestWorldList where specialRewardContentId less than or equals to DEFAULT_SPECIAL_REWARD_CONTENT_ID
        defaultMMarathonQuestWorldShouldNotBeFound("specialRewardContentId.lessThan=" + DEFAULT_SPECIAL_REWARD_CONTENT_ID);

        // Get all the mMarathonQuestWorldList where specialRewardContentId less than or equals to UPDATED_SPECIAL_REWARD_CONTENT_ID
        defaultMMarathonQuestWorldShouldBeFound("specialRewardContentId.lessThan=" + UPDATED_SPECIAL_REWARD_CONTENT_ID);
    }


    @Test
    @Transactional
    public void getAllMMarathonQuestWorldsByIsEnableCoopIsEqualToSomething() throws Exception {
        // Initialize the database
        mMarathonQuestWorldRepository.saveAndFlush(mMarathonQuestWorld);

        // Get all the mMarathonQuestWorldList where isEnableCoop equals to DEFAULT_IS_ENABLE_COOP
        defaultMMarathonQuestWorldShouldBeFound("isEnableCoop.equals=" + DEFAULT_IS_ENABLE_COOP);

        // Get all the mMarathonQuestWorldList where isEnableCoop equals to UPDATED_IS_ENABLE_COOP
        defaultMMarathonQuestWorldShouldNotBeFound("isEnableCoop.equals=" + UPDATED_IS_ENABLE_COOP);
    }

    @Test
    @Transactional
    public void getAllMMarathonQuestWorldsByIsEnableCoopIsInShouldWork() throws Exception {
        // Initialize the database
        mMarathonQuestWorldRepository.saveAndFlush(mMarathonQuestWorld);

        // Get all the mMarathonQuestWorldList where isEnableCoop in DEFAULT_IS_ENABLE_COOP or UPDATED_IS_ENABLE_COOP
        defaultMMarathonQuestWorldShouldBeFound("isEnableCoop.in=" + DEFAULT_IS_ENABLE_COOP + "," + UPDATED_IS_ENABLE_COOP);

        // Get all the mMarathonQuestWorldList where isEnableCoop equals to UPDATED_IS_ENABLE_COOP
        defaultMMarathonQuestWorldShouldNotBeFound("isEnableCoop.in=" + UPDATED_IS_ENABLE_COOP);
    }

    @Test
    @Transactional
    public void getAllMMarathonQuestWorldsByIsEnableCoopIsNullOrNotNull() throws Exception {
        // Initialize the database
        mMarathonQuestWorldRepository.saveAndFlush(mMarathonQuestWorld);

        // Get all the mMarathonQuestWorldList where isEnableCoop is not null
        defaultMMarathonQuestWorldShouldBeFound("isEnableCoop.specified=true");

        // Get all the mMarathonQuestWorldList where isEnableCoop is null
        defaultMMarathonQuestWorldShouldNotBeFound("isEnableCoop.specified=false");
    }

    @Test
    @Transactional
    public void getAllMMarathonQuestWorldsByIsEnableCoopIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mMarathonQuestWorldRepository.saveAndFlush(mMarathonQuestWorld);

        // Get all the mMarathonQuestWorldList where isEnableCoop greater than or equals to DEFAULT_IS_ENABLE_COOP
        defaultMMarathonQuestWorldShouldBeFound("isEnableCoop.greaterOrEqualThan=" + DEFAULT_IS_ENABLE_COOP);

        // Get all the mMarathonQuestWorldList where isEnableCoop greater than or equals to UPDATED_IS_ENABLE_COOP
        defaultMMarathonQuestWorldShouldNotBeFound("isEnableCoop.greaterOrEqualThan=" + UPDATED_IS_ENABLE_COOP);
    }

    @Test
    @Transactional
    public void getAllMMarathonQuestWorldsByIsEnableCoopIsLessThanSomething() throws Exception {
        // Initialize the database
        mMarathonQuestWorldRepository.saveAndFlush(mMarathonQuestWorld);

        // Get all the mMarathonQuestWorldList where isEnableCoop less than or equals to DEFAULT_IS_ENABLE_COOP
        defaultMMarathonQuestWorldShouldNotBeFound("isEnableCoop.lessThan=" + DEFAULT_IS_ENABLE_COOP);

        // Get all the mMarathonQuestWorldList where isEnableCoop less than or equals to UPDATED_IS_ENABLE_COOP
        defaultMMarathonQuestWorldShouldBeFound("isEnableCoop.lessThan=" + UPDATED_IS_ENABLE_COOP);
    }


    @Test
    @Transactional
    public void getAllMMarathonQuestWorldsByMMarathonQuestStageIsEqualToSomething() throws Exception {
        // Initialize the database
        MMarathonQuestStage mMarathonQuestStage = MMarathonQuestStageResourceIT.createEntity(em);
        em.persist(mMarathonQuestStage);
        em.flush();
        mMarathonQuestWorld.addMMarathonQuestStage(mMarathonQuestStage);
        mMarathonQuestWorldRepository.saveAndFlush(mMarathonQuestWorld);
        Long mMarathonQuestStageId = mMarathonQuestStage.getId();

        // Get all the mMarathonQuestWorldList where mMarathonQuestStage equals to mMarathonQuestStageId
        defaultMMarathonQuestWorldShouldBeFound("mMarathonQuestStageId.equals=" + mMarathonQuestStageId);

        // Get all the mMarathonQuestWorldList where mMarathonQuestStage equals to mMarathonQuestStageId + 1
        defaultMMarathonQuestWorldShouldNotBeFound("mMarathonQuestStageId.equals=" + (mMarathonQuestStageId + 1));
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultMMarathonQuestWorldShouldBeFound(String filter) throws Exception {
        restMMarathonQuestWorldMockMvc.perform(get("/api/m-marathon-quest-worlds?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(mMarathonQuestWorld.getId().intValue())))
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
        restMMarathonQuestWorldMockMvc.perform(get("/api/m-marathon-quest-worlds/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultMMarathonQuestWorldShouldNotBeFound(String filter) throws Exception {
        restMMarathonQuestWorldMockMvc.perform(get("/api/m-marathon-quest-worlds?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restMMarathonQuestWorldMockMvc.perform(get("/api/m-marathon-quest-worlds/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("0"));
    }


    @Test
    @Transactional
    public void getNonExistingMMarathonQuestWorld() throws Exception {
        // Get the mMarathonQuestWorld
        restMMarathonQuestWorldMockMvc.perform(get("/api/m-marathon-quest-worlds/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateMMarathonQuestWorld() throws Exception {
        // Initialize the database
        mMarathonQuestWorldRepository.saveAndFlush(mMarathonQuestWorld);

        int databaseSizeBeforeUpdate = mMarathonQuestWorldRepository.findAll().size();

        // Update the mMarathonQuestWorld
        MMarathonQuestWorld updatedMMarathonQuestWorld = mMarathonQuestWorldRepository.findById(mMarathonQuestWorld.getId()).get();
        // Disconnect from session so that the updates on updatedMMarathonQuestWorld are not directly saved in db
        em.detach(updatedMMarathonQuestWorld);
        updatedMMarathonQuestWorld
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
        MMarathonQuestWorldDTO mMarathonQuestWorldDTO = mMarathonQuestWorldMapper.toDto(updatedMMarathonQuestWorld);

        restMMarathonQuestWorldMockMvc.perform(put("/api/m-marathon-quest-worlds")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mMarathonQuestWorldDTO)))
            .andExpect(status().isOk());

        // Validate the MMarathonQuestWorld in the database
        List<MMarathonQuestWorld> mMarathonQuestWorldList = mMarathonQuestWorldRepository.findAll();
        assertThat(mMarathonQuestWorldList).hasSize(databaseSizeBeforeUpdate);
        MMarathonQuestWorld testMMarathonQuestWorld = mMarathonQuestWorldList.get(mMarathonQuestWorldList.size() - 1);
        assertThat(testMMarathonQuestWorld.getSetId()).isEqualTo(UPDATED_SET_ID);
        assertThat(testMMarathonQuestWorld.getNumber()).isEqualTo(UPDATED_NUMBER);
        assertThat(testMMarathonQuestWorld.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testMMarathonQuestWorld.getImagePath()).isEqualTo(UPDATED_IMAGE_PATH);
        assertThat(testMMarathonQuestWorld.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
        assertThat(testMMarathonQuestWorld.getStageUnlockPattern()).isEqualTo(UPDATED_STAGE_UNLOCK_PATTERN);
        assertThat(testMMarathonQuestWorld.getArousalBanner()).isEqualTo(UPDATED_AROUSAL_BANNER);
        assertThat(testMMarathonQuestWorld.getSpecialRewardContentType()).isEqualTo(UPDATED_SPECIAL_REWARD_CONTENT_TYPE);
        assertThat(testMMarathonQuestWorld.getSpecialRewardContentId()).isEqualTo(UPDATED_SPECIAL_REWARD_CONTENT_ID);
        assertThat(testMMarathonQuestWorld.getIsEnableCoop()).isEqualTo(UPDATED_IS_ENABLE_COOP);
    }

    @Test
    @Transactional
    public void updateNonExistingMMarathonQuestWorld() throws Exception {
        int databaseSizeBeforeUpdate = mMarathonQuestWorldRepository.findAll().size();

        // Create the MMarathonQuestWorld
        MMarathonQuestWorldDTO mMarathonQuestWorldDTO = mMarathonQuestWorldMapper.toDto(mMarathonQuestWorld);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restMMarathonQuestWorldMockMvc.perform(put("/api/m-marathon-quest-worlds")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mMarathonQuestWorldDTO)))
            .andExpect(status().isBadRequest());

        // Validate the MMarathonQuestWorld in the database
        List<MMarathonQuestWorld> mMarathonQuestWorldList = mMarathonQuestWorldRepository.findAll();
        assertThat(mMarathonQuestWorldList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteMMarathonQuestWorld() throws Exception {
        // Initialize the database
        mMarathonQuestWorldRepository.saveAndFlush(mMarathonQuestWorld);

        int databaseSizeBeforeDelete = mMarathonQuestWorldRepository.findAll().size();

        // Delete the mMarathonQuestWorld
        restMMarathonQuestWorldMockMvc.perform(delete("/api/m-marathon-quest-worlds/{id}", mMarathonQuestWorld.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database is empty
        List<MMarathonQuestWorld> mMarathonQuestWorldList = mMarathonQuestWorldRepository.findAll();
        assertThat(mMarathonQuestWorldList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(MMarathonQuestWorld.class);
        MMarathonQuestWorld mMarathonQuestWorld1 = new MMarathonQuestWorld();
        mMarathonQuestWorld1.setId(1L);
        MMarathonQuestWorld mMarathonQuestWorld2 = new MMarathonQuestWorld();
        mMarathonQuestWorld2.setId(mMarathonQuestWorld1.getId());
        assertThat(mMarathonQuestWorld1).isEqualTo(mMarathonQuestWorld2);
        mMarathonQuestWorld2.setId(2L);
        assertThat(mMarathonQuestWorld1).isNotEqualTo(mMarathonQuestWorld2);
        mMarathonQuestWorld1.setId(null);
        assertThat(mMarathonQuestWorld1).isNotEqualTo(mMarathonQuestWorld2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(MMarathonQuestWorldDTO.class);
        MMarathonQuestWorldDTO mMarathonQuestWorldDTO1 = new MMarathonQuestWorldDTO();
        mMarathonQuestWorldDTO1.setId(1L);
        MMarathonQuestWorldDTO mMarathonQuestWorldDTO2 = new MMarathonQuestWorldDTO();
        assertThat(mMarathonQuestWorldDTO1).isNotEqualTo(mMarathonQuestWorldDTO2);
        mMarathonQuestWorldDTO2.setId(mMarathonQuestWorldDTO1.getId());
        assertThat(mMarathonQuestWorldDTO1).isEqualTo(mMarathonQuestWorldDTO2);
        mMarathonQuestWorldDTO2.setId(2L);
        assertThat(mMarathonQuestWorldDTO1).isNotEqualTo(mMarathonQuestWorldDTO2);
        mMarathonQuestWorldDTO1.setId(null);
        assertThat(mMarathonQuestWorldDTO1).isNotEqualTo(mMarathonQuestWorldDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(mMarathonQuestWorldMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(mMarathonQuestWorldMapper.fromId(null)).isNull();
    }
}
