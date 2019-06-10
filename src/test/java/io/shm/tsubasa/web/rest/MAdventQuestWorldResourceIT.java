package io.shm.tsubasa.web.rest;

import io.shm.tsubasa.TsubasaApp;
import io.shm.tsubasa.domain.MAdventQuestWorld;
import io.shm.tsubasa.domain.MAdventQuestStage;
import io.shm.tsubasa.repository.MAdventQuestWorldRepository;
import io.shm.tsubasa.service.MAdventQuestWorldService;
import io.shm.tsubasa.service.dto.MAdventQuestWorldDTO;
import io.shm.tsubasa.service.mapper.MAdventQuestWorldMapper;
import io.shm.tsubasa.web.rest.errors.ExceptionTranslator;
import io.shm.tsubasa.service.dto.MAdventQuestWorldCriteria;
import io.shm.tsubasa.service.MAdventQuestWorldQueryService;

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
 * Integration tests for the {@Link MAdventQuestWorldResource} REST controller.
 */
@SpringBootTest(classes = TsubasaApp.class)
public class MAdventQuestWorldResourceIT {

    private static final Integer DEFAULT_SET_ID = 1;
    private static final Integer UPDATED_SET_ID = 2;

    private static final Integer DEFAULT_NUMBER = 1;
    private static final Integer UPDATED_NUMBER = 2;

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final Integer DEFAULT_SYMBOL_TYPE = 1;
    private static final Integer UPDATED_SYMBOL_TYPE = 2;

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
    private MAdventQuestWorldRepository mAdventQuestWorldRepository;

    @Autowired
    private MAdventQuestWorldMapper mAdventQuestWorldMapper;

    @Autowired
    private MAdventQuestWorldService mAdventQuestWorldService;

    @Autowired
    private MAdventQuestWorldQueryService mAdventQuestWorldQueryService;

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

    private MockMvc restMAdventQuestWorldMockMvc;

    private MAdventQuestWorld mAdventQuestWorld;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final MAdventQuestWorldResource mAdventQuestWorldResource = new MAdventQuestWorldResource(mAdventQuestWorldService, mAdventQuestWorldQueryService);
        this.restMAdventQuestWorldMockMvc = MockMvcBuilders.standaloneSetup(mAdventQuestWorldResource)
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
    public static MAdventQuestWorld createEntity(EntityManager em) {
        MAdventQuestWorld mAdventQuestWorld = new MAdventQuestWorld()
            .setId(DEFAULT_SET_ID)
            .number(DEFAULT_NUMBER)
            .name(DEFAULT_NAME)
            .symbolType(DEFAULT_SYMBOL_TYPE)
            .imagePath(DEFAULT_IMAGE_PATH)
            .description(DEFAULT_DESCRIPTION)
            .stageUnlockPattern(DEFAULT_STAGE_UNLOCK_PATTERN)
            .arousalBanner(DEFAULT_AROUSAL_BANNER)
            .specialRewardContentType(DEFAULT_SPECIAL_REWARD_CONTENT_TYPE)
            .specialRewardContentId(DEFAULT_SPECIAL_REWARD_CONTENT_ID)
            .isEnableCoop(DEFAULT_IS_ENABLE_COOP);
        return mAdventQuestWorld;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static MAdventQuestWorld createUpdatedEntity(EntityManager em) {
        MAdventQuestWorld mAdventQuestWorld = new MAdventQuestWorld()
            .setId(UPDATED_SET_ID)
            .number(UPDATED_NUMBER)
            .name(UPDATED_NAME)
            .symbolType(UPDATED_SYMBOL_TYPE)
            .imagePath(UPDATED_IMAGE_PATH)
            .description(UPDATED_DESCRIPTION)
            .stageUnlockPattern(UPDATED_STAGE_UNLOCK_PATTERN)
            .arousalBanner(UPDATED_AROUSAL_BANNER)
            .specialRewardContentType(UPDATED_SPECIAL_REWARD_CONTENT_TYPE)
            .specialRewardContentId(UPDATED_SPECIAL_REWARD_CONTENT_ID)
            .isEnableCoop(UPDATED_IS_ENABLE_COOP);
        return mAdventQuestWorld;
    }

    @BeforeEach
    public void initTest() {
        mAdventQuestWorld = createEntity(em);
    }

    @Test
    @Transactional
    public void createMAdventQuestWorld() throws Exception {
        int databaseSizeBeforeCreate = mAdventQuestWorldRepository.findAll().size();

        // Create the MAdventQuestWorld
        MAdventQuestWorldDTO mAdventQuestWorldDTO = mAdventQuestWorldMapper.toDto(mAdventQuestWorld);
        restMAdventQuestWorldMockMvc.perform(post("/api/m-advent-quest-worlds")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mAdventQuestWorldDTO)))
            .andExpect(status().isCreated());

        // Validate the MAdventQuestWorld in the database
        List<MAdventQuestWorld> mAdventQuestWorldList = mAdventQuestWorldRepository.findAll();
        assertThat(mAdventQuestWorldList).hasSize(databaseSizeBeforeCreate + 1);
        MAdventQuestWorld testMAdventQuestWorld = mAdventQuestWorldList.get(mAdventQuestWorldList.size() - 1);
        assertThat(testMAdventQuestWorld.getSetId()).isEqualTo(DEFAULT_SET_ID);
        assertThat(testMAdventQuestWorld.getNumber()).isEqualTo(DEFAULT_NUMBER);
        assertThat(testMAdventQuestWorld.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testMAdventQuestWorld.getSymbolType()).isEqualTo(DEFAULT_SYMBOL_TYPE);
        assertThat(testMAdventQuestWorld.getImagePath()).isEqualTo(DEFAULT_IMAGE_PATH);
        assertThat(testMAdventQuestWorld.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
        assertThat(testMAdventQuestWorld.getStageUnlockPattern()).isEqualTo(DEFAULT_STAGE_UNLOCK_PATTERN);
        assertThat(testMAdventQuestWorld.getArousalBanner()).isEqualTo(DEFAULT_AROUSAL_BANNER);
        assertThat(testMAdventQuestWorld.getSpecialRewardContentType()).isEqualTo(DEFAULT_SPECIAL_REWARD_CONTENT_TYPE);
        assertThat(testMAdventQuestWorld.getSpecialRewardContentId()).isEqualTo(DEFAULT_SPECIAL_REWARD_CONTENT_ID);
        assertThat(testMAdventQuestWorld.getIsEnableCoop()).isEqualTo(DEFAULT_IS_ENABLE_COOP);
    }

    @Test
    @Transactional
    public void createMAdventQuestWorldWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = mAdventQuestWorldRepository.findAll().size();

        // Create the MAdventQuestWorld with an existing ID
        mAdventQuestWorld.setId(1L);
        MAdventQuestWorldDTO mAdventQuestWorldDTO = mAdventQuestWorldMapper.toDto(mAdventQuestWorld);

        // An entity with an existing ID cannot be created, so this API call must fail
        restMAdventQuestWorldMockMvc.perform(post("/api/m-advent-quest-worlds")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mAdventQuestWorldDTO)))
            .andExpect(status().isBadRequest());

        // Validate the MAdventQuestWorld in the database
        List<MAdventQuestWorld> mAdventQuestWorldList = mAdventQuestWorldRepository.findAll();
        assertThat(mAdventQuestWorldList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkSetIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = mAdventQuestWorldRepository.findAll().size();
        // set the field null
        mAdventQuestWorld.setSetId(null);

        // Create the MAdventQuestWorld, which fails.
        MAdventQuestWorldDTO mAdventQuestWorldDTO = mAdventQuestWorldMapper.toDto(mAdventQuestWorld);

        restMAdventQuestWorldMockMvc.perform(post("/api/m-advent-quest-worlds")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mAdventQuestWorldDTO)))
            .andExpect(status().isBadRequest());

        List<MAdventQuestWorld> mAdventQuestWorldList = mAdventQuestWorldRepository.findAll();
        assertThat(mAdventQuestWorldList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkNumberIsRequired() throws Exception {
        int databaseSizeBeforeTest = mAdventQuestWorldRepository.findAll().size();
        // set the field null
        mAdventQuestWorld.setNumber(null);

        // Create the MAdventQuestWorld, which fails.
        MAdventQuestWorldDTO mAdventQuestWorldDTO = mAdventQuestWorldMapper.toDto(mAdventQuestWorld);

        restMAdventQuestWorldMockMvc.perform(post("/api/m-advent-quest-worlds")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mAdventQuestWorldDTO)))
            .andExpect(status().isBadRequest());

        List<MAdventQuestWorld> mAdventQuestWorldList = mAdventQuestWorldRepository.findAll();
        assertThat(mAdventQuestWorldList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkSymbolTypeIsRequired() throws Exception {
        int databaseSizeBeforeTest = mAdventQuestWorldRepository.findAll().size();
        // set the field null
        mAdventQuestWorld.setSymbolType(null);

        // Create the MAdventQuestWorld, which fails.
        MAdventQuestWorldDTO mAdventQuestWorldDTO = mAdventQuestWorldMapper.toDto(mAdventQuestWorld);

        restMAdventQuestWorldMockMvc.perform(post("/api/m-advent-quest-worlds")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mAdventQuestWorldDTO)))
            .andExpect(status().isBadRequest());

        List<MAdventQuestWorld> mAdventQuestWorldList = mAdventQuestWorldRepository.findAll();
        assertThat(mAdventQuestWorldList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkStageUnlockPatternIsRequired() throws Exception {
        int databaseSizeBeforeTest = mAdventQuestWorldRepository.findAll().size();
        // set the field null
        mAdventQuestWorld.setStageUnlockPattern(null);

        // Create the MAdventQuestWorld, which fails.
        MAdventQuestWorldDTO mAdventQuestWorldDTO = mAdventQuestWorldMapper.toDto(mAdventQuestWorld);

        restMAdventQuestWorldMockMvc.perform(post("/api/m-advent-quest-worlds")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mAdventQuestWorldDTO)))
            .andExpect(status().isBadRequest());

        List<MAdventQuestWorld> mAdventQuestWorldList = mAdventQuestWorldRepository.findAll();
        assertThat(mAdventQuestWorldList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkIsEnableCoopIsRequired() throws Exception {
        int databaseSizeBeforeTest = mAdventQuestWorldRepository.findAll().size();
        // set the field null
        mAdventQuestWorld.setIsEnableCoop(null);

        // Create the MAdventQuestWorld, which fails.
        MAdventQuestWorldDTO mAdventQuestWorldDTO = mAdventQuestWorldMapper.toDto(mAdventQuestWorld);

        restMAdventQuestWorldMockMvc.perform(post("/api/m-advent-quest-worlds")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mAdventQuestWorldDTO)))
            .andExpect(status().isBadRequest());

        List<MAdventQuestWorld> mAdventQuestWorldList = mAdventQuestWorldRepository.findAll();
        assertThat(mAdventQuestWorldList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllMAdventQuestWorlds() throws Exception {
        // Initialize the database
        mAdventQuestWorldRepository.saveAndFlush(mAdventQuestWorld);

        // Get all the mAdventQuestWorldList
        restMAdventQuestWorldMockMvc.perform(get("/api/m-advent-quest-worlds?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(mAdventQuestWorld.getId().intValue())))
            .andExpect(jsonPath("$.[*].setId").value(hasItem(DEFAULT_SET_ID)))
            .andExpect(jsonPath("$.[*].number").value(hasItem(DEFAULT_NUMBER)))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME.toString())))
            .andExpect(jsonPath("$.[*].symbolType").value(hasItem(DEFAULT_SYMBOL_TYPE)))
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
    public void getMAdventQuestWorld() throws Exception {
        // Initialize the database
        mAdventQuestWorldRepository.saveAndFlush(mAdventQuestWorld);

        // Get the mAdventQuestWorld
        restMAdventQuestWorldMockMvc.perform(get("/api/m-advent-quest-worlds/{id}", mAdventQuestWorld.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(mAdventQuestWorld.getId().intValue()))
            .andExpect(jsonPath("$.setId").value(DEFAULT_SET_ID))
            .andExpect(jsonPath("$.number").value(DEFAULT_NUMBER))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME.toString()))
            .andExpect(jsonPath("$.symbolType").value(DEFAULT_SYMBOL_TYPE))
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
    public void getAllMAdventQuestWorldsBySetIdIsEqualToSomething() throws Exception {
        // Initialize the database
        mAdventQuestWorldRepository.saveAndFlush(mAdventQuestWorld);

        // Get all the mAdventQuestWorldList where setId equals to DEFAULT_SET_ID
        defaultMAdventQuestWorldShouldBeFound("setId.equals=" + DEFAULT_SET_ID);

        // Get all the mAdventQuestWorldList where setId equals to UPDATED_SET_ID
        defaultMAdventQuestWorldShouldNotBeFound("setId.equals=" + UPDATED_SET_ID);
    }

    @Test
    @Transactional
    public void getAllMAdventQuestWorldsBySetIdIsInShouldWork() throws Exception {
        // Initialize the database
        mAdventQuestWorldRepository.saveAndFlush(mAdventQuestWorld);

        // Get all the mAdventQuestWorldList where setId in DEFAULT_SET_ID or UPDATED_SET_ID
        defaultMAdventQuestWorldShouldBeFound("setId.in=" + DEFAULT_SET_ID + "," + UPDATED_SET_ID);

        // Get all the mAdventQuestWorldList where setId equals to UPDATED_SET_ID
        defaultMAdventQuestWorldShouldNotBeFound("setId.in=" + UPDATED_SET_ID);
    }

    @Test
    @Transactional
    public void getAllMAdventQuestWorldsBySetIdIsNullOrNotNull() throws Exception {
        // Initialize the database
        mAdventQuestWorldRepository.saveAndFlush(mAdventQuestWorld);

        // Get all the mAdventQuestWorldList where setId is not null
        defaultMAdventQuestWorldShouldBeFound("setId.specified=true");

        // Get all the mAdventQuestWorldList where setId is null
        defaultMAdventQuestWorldShouldNotBeFound("setId.specified=false");
    }

    @Test
    @Transactional
    public void getAllMAdventQuestWorldsBySetIdIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mAdventQuestWorldRepository.saveAndFlush(mAdventQuestWorld);

        // Get all the mAdventQuestWorldList where setId greater than or equals to DEFAULT_SET_ID
        defaultMAdventQuestWorldShouldBeFound("setId.greaterOrEqualThan=" + DEFAULT_SET_ID);

        // Get all the mAdventQuestWorldList where setId greater than or equals to UPDATED_SET_ID
        defaultMAdventQuestWorldShouldNotBeFound("setId.greaterOrEqualThan=" + UPDATED_SET_ID);
    }

    @Test
    @Transactional
    public void getAllMAdventQuestWorldsBySetIdIsLessThanSomething() throws Exception {
        // Initialize the database
        mAdventQuestWorldRepository.saveAndFlush(mAdventQuestWorld);

        // Get all the mAdventQuestWorldList where setId less than or equals to DEFAULT_SET_ID
        defaultMAdventQuestWorldShouldNotBeFound("setId.lessThan=" + DEFAULT_SET_ID);

        // Get all the mAdventQuestWorldList where setId less than or equals to UPDATED_SET_ID
        defaultMAdventQuestWorldShouldBeFound("setId.lessThan=" + UPDATED_SET_ID);
    }


    @Test
    @Transactional
    public void getAllMAdventQuestWorldsByNumberIsEqualToSomething() throws Exception {
        // Initialize the database
        mAdventQuestWorldRepository.saveAndFlush(mAdventQuestWorld);

        // Get all the mAdventQuestWorldList where number equals to DEFAULT_NUMBER
        defaultMAdventQuestWorldShouldBeFound("number.equals=" + DEFAULT_NUMBER);

        // Get all the mAdventQuestWorldList where number equals to UPDATED_NUMBER
        defaultMAdventQuestWorldShouldNotBeFound("number.equals=" + UPDATED_NUMBER);
    }

    @Test
    @Transactional
    public void getAllMAdventQuestWorldsByNumberIsInShouldWork() throws Exception {
        // Initialize the database
        mAdventQuestWorldRepository.saveAndFlush(mAdventQuestWorld);

        // Get all the mAdventQuestWorldList where number in DEFAULT_NUMBER or UPDATED_NUMBER
        defaultMAdventQuestWorldShouldBeFound("number.in=" + DEFAULT_NUMBER + "," + UPDATED_NUMBER);

        // Get all the mAdventQuestWorldList where number equals to UPDATED_NUMBER
        defaultMAdventQuestWorldShouldNotBeFound("number.in=" + UPDATED_NUMBER);
    }

    @Test
    @Transactional
    public void getAllMAdventQuestWorldsByNumberIsNullOrNotNull() throws Exception {
        // Initialize the database
        mAdventQuestWorldRepository.saveAndFlush(mAdventQuestWorld);

        // Get all the mAdventQuestWorldList where number is not null
        defaultMAdventQuestWorldShouldBeFound("number.specified=true");

        // Get all the mAdventQuestWorldList where number is null
        defaultMAdventQuestWorldShouldNotBeFound("number.specified=false");
    }

    @Test
    @Transactional
    public void getAllMAdventQuestWorldsByNumberIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mAdventQuestWorldRepository.saveAndFlush(mAdventQuestWorld);

        // Get all the mAdventQuestWorldList where number greater than or equals to DEFAULT_NUMBER
        defaultMAdventQuestWorldShouldBeFound("number.greaterOrEqualThan=" + DEFAULT_NUMBER);

        // Get all the mAdventQuestWorldList where number greater than or equals to UPDATED_NUMBER
        defaultMAdventQuestWorldShouldNotBeFound("number.greaterOrEqualThan=" + UPDATED_NUMBER);
    }

    @Test
    @Transactional
    public void getAllMAdventQuestWorldsByNumberIsLessThanSomething() throws Exception {
        // Initialize the database
        mAdventQuestWorldRepository.saveAndFlush(mAdventQuestWorld);

        // Get all the mAdventQuestWorldList where number less than or equals to DEFAULT_NUMBER
        defaultMAdventQuestWorldShouldNotBeFound("number.lessThan=" + DEFAULT_NUMBER);

        // Get all the mAdventQuestWorldList where number less than or equals to UPDATED_NUMBER
        defaultMAdventQuestWorldShouldBeFound("number.lessThan=" + UPDATED_NUMBER);
    }


    @Test
    @Transactional
    public void getAllMAdventQuestWorldsBySymbolTypeIsEqualToSomething() throws Exception {
        // Initialize the database
        mAdventQuestWorldRepository.saveAndFlush(mAdventQuestWorld);

        // Get all the mAdventQuestWorldList where symbolType equals to DEFAULT_SYMBOL_TYPE
        defaultMAdventQuestWorldShouldBeFound("symbolType.equals=" + DEFAULT_SYMBOL_TYPE);

        // Get all the mAdventQuestWorldList where symbolType equals to UPDATED_SYMBOL_TYPE
        defaultMAdventQuestWorldShouldNotBeFound("symbolType.equals=" + UPDATED_SYMBOL_TYPE);
    }

    @Test
    @Transactional
    public void getAllMAdventQuestWorldsBySymbolTypeIsInShouldWork() throws Exception {
        // Initialize the database
        mAdventQuestWorldRepository.saveAndFlush(mAdventQuestWorld);

        // Get all the mAdventQuestWorldList where symbolType in DEFAULT_SYMBOL_TYPE or UPDATED_SYMBOL_TYPE
        defaultMAdventQuestWorldShouldBeFound("symbolType.in=" + DEFAULT_SYMBOL_TYPE + "," + UPDATED_SYMBOL_TYPE);

        // Get all the mAdventQuestWorldList where symbolType equals to UPDATED_SYMBOL_TYPE
        defaultMAdventQuestWorldShouldNotBeFound("symbolType.in=" + UPDATED_SYMBOL_TYPE);
    }

    @Test
    @Transactional
    public void getAllMAdventQuestWorldsBySymbolTypeIsNullOrNotNull() throws Exception {
        // Initialize the database
        mAdventQuestWorldRepository.saveAndFlush(mAdventQuestWorld);

        // Get all the mAdventQuestWorldList where symbolType is not null
        defaultMAdventQuestWorldShouldBeFound("symbolType.specified=true");

        // Get all the mAdventQuestWorldList where symbolType is null
        defaultMAdventQuestWorldShouldNotBeFound("symbolType.specified=false");
    }

    @Test
    @Transactional
    public void getAllMAdventQuestWorldsBySymbolTypeIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mAdventQuestWorldRepository.saveAndFlush(mAdventQuestWorld);

        // Get all the mAdventQuestWorldList where symbolType greater than or equals to DEFAULT_SYMBOL_TYPE
        defaultMAdventQuestWorldShouldBeFound("symbolType.greaterOrEqualThan=" + DEFAULT_SYMBOL_TYPE);

        // Get all the mAdventQuestWorldList where symbolType greater than or equals to UPDATED_SYMBOL_TYPE
        defaultMAdventQuestWorldShouldNotBeFound("symbolType.greaterOrEqualThan=" + UPDATED_SYMBOL_TYPE);
    }

    @Test
    @Transactional
    public void getAllMAdventQuestWorldsBySymbolTypeIsLessThanSomething() throws Exception {
        // Initialize the database
        mAdventQuestWorldRepository.saveAndFlush(mAdventQuestWorld);

        // Get all the mAdventQuestWorldList where symbolType less than or equals to DEFAULT_SYMBOL_TYPE
        defaultMAdventQuestWorldShouldNotBeFound("symbolType.lessThan=" + DEFAULT_SYMBOL_TYPE);

        // Get all the mAdventQuestWorldList where symbolType less than or equals to UPDATED_SYMBOL_TYPE
        defaultMAdventQuestWorldShouldBeFound("symbolType.lessThan=" + UPDATED_SYMBOL_TYPE);
    }


    @Test
    @Transactional
    public void getAllMAdventQuestWorldsByStageUnlockPatternIsEqualToSomething() throws Exception {
        // Initialize the database
        mAdventQuestWorldRepository.saveAndFlush(mAdventQuestWorld);

        // Get all the mAdventQuestWorldList where stageUnlockPattern equals to DEFAULT_STAGE_UNLOCK_PATTERN
        defaultMAdventQuestWorldShouldBeFound("stageUnlockPattern.equals=" + DEFAULT_STAGE_UNLOCK_PATTERN);

        // Get all the mAdventQuestWorldList where stageUnlockPattern equals to UPDATED_STAGE_UNLOCK_PATTERN
        defaultMAdventQuestWorldShouldNotBeFound("stageUnlockPattern.equals=" + UPDATED_STAGE_UNLOCK_PATTERN);
    }

    @Test
    @Transactional
    public void getAllMAdventQuestWorldsByStageUnlockPatternIsInShouldWork() throws Exception {
        // Initialize the database
        mAdventQuestWorldRepository.saveAndFlush(mAdventQuestWorld);

        // Get all the mAdventQuestWorldList where stageUnlockPattern in DEFAULT_STAGE_UNLOCK_PATTERN or UPDATED_STAGE_UNLOCK_PATTERN
        defaultMAdventQuestWorldShouldBeFound("stageUnlockPattern.in=" + DEFAULT_STAGE_UNLOCK_PATTERN + "," + UPDATED_STAGE_UNLOCK_PATTERN);

        // Get all the mAdventQuestWorldList where stageUnlockPattern equals to UPDATED_STAGE_UNLOCK_PATTERN
        defaultMAdventQuestWorldShouldNotBeFound("stageUnlockPattern.in=" + UPDATED_STAGE_UNLOCK_PATTERN);
    }

    @Test
    @Transactional
    public void getAllMAdventQuestWorldsByStageUnlockPatternIsNullOrNotNull() throws Exception {
        // Initialize the database
        mAdventQuestWorldRepository.saveAndFlush(mAdventQuestWorld);

        // Get all the mAdventQuestWorldList where stageUnlockPattern is not null
        defaultMAdventQuestWorldShouldBeFound("stageUnlockPattern.specified=true");

        // Get all the mAdventQuestWorldList where stageUnlockPattern is null
        defaultMAdventQuestWorldShouldNotBeFound("stageUnlockPattern.specified=false");
    }

    @Test
    @Transactional
    public void getAllMAdventQuestWorldsByStageUnlockPatternIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mAdventQuestWorldRepository.saveAndFlush(mAdventQuestWorld);

        // Get all the mAdventQuestWorldList where stageUnlockPattern greater than or equals to DEFAULT_STAGE_UNLOCK_PATTERN
        defaultMAdventQuestWorldShouldBeFound("stageUnlockPattern.greaterOrEqualThan=" + DEFAULT_STAGE_UNLOCK_PATTERN);

        // Get all the mAdventQuestWorldList where stageUnlockPattern greater than or equals to UPDATED_STAGE_UNLOCK_PATTERN
        defaultMAdventQuestWorldShouldNotBeFound("stageUnlockPattern.greaterOrEqualThan=" + UPDATED_STAGE_UNLOCK_PATTERN);
    }

    @Test
    @Transactional
    public void getAllMAdventQuestWorldsByStageUnlockPatternIsLessThanSomething() throws Exception {
        // Initialize the database
        mAdventQuestWorldRepository.saveAndFlush(mAdventQuestWorld);

        // Get all the mAdventQuestWorldList where stageUnlockPattern less than or equals to DEFAULT_STAGE_UNLOCK_PATTERN
        defaultMAdventQuestWorldShouldNotBeFound("stageUnlockPattern.lessThan=" + DEFAULT_STAGE_UNLOCK_PATTERN);

        // Get all the mAdventQuestWorldList where stageUnlockPattern less than or equals to UPDATED_STAGE_UNLOCK_PATTERN
        defaultMAdventQuestWorldShouldBeFound("stageUnlockPattern.lessThan=" + UPDATED_STAGE_UNLOCK_PATTERN);
    }


    @Test
    @Transactional
    public void getAllMAdventQuestWorldsBySpecialRewardContentTypeIsEqualToSomething() throws Exception {
        // Initialize the database
        mAdventQuestWorldRepository.saveAndFlush(mAdventQuestWorld);

        // Get all the mAdventQuestWorldList where specialRewardContentType equals to DEFAULT_SPECIAL_REWARD_CONTENT_TYPE
        defaultMAdventQuestWorldShouldBeFound("specialRewardContentType.equals=" + DEFAULT_SPECIAL_REWARD_CONTENT_TYPE);

        // Get all the mAdventQuestWorldList where specialRewardContentType equals to UPDATED_SPECIAL_REWARD_CONTENT_TYPE
        defaultMAdventQuestWorldShouldNotBeFound("specialRewardContentType.equals=" + UPDATED_SPECIAL_REWARD_CONTENT_TYPE);
    }

    @Test
    @Transactional
    public void getAllMAdventQuestWorldsBySpecialRewardContentTypeIsInShouldWork() throws Exception {
        // Initialize the database
        mAdventQuestWorldRepository.saveAndFlush(mAdventQuestWorld);

        // Get all the mAdventQuestWorldList where specialRewardContentType in DEFAULT_SPECIAL_REWARD_CONTENT_TYPE or UPDATED_SPECIAL_REWARD_CONTENT_TYPE
        defaultMAdventQuestWorldShouldBeFound("specialRewardContentType.in=" + DEFAULT_SPECIAL_REWARD_CONTENT_TYPE + "," + UPDATED_SPECIAL_REWARD_CONTENT_TYPE);

        // Get all the mAdventQuestWorldList where specialRewardContentType equals to UPDATED_SPECIAL_REWARD_CONTENT_TYPE
        defaultMAdventQuestWorldShouldNotBeFound("specialRewardContentType.in=" + UPDATED_SPECIAL_REWARD_CONTENT_TYPE);
    }

    @Test
    @Transactional
    public void getAllMAdventQuestWorldsBySpecialRewardContentTypeIsNullOrNotNull() throws Exception {
        // Initialize the database
        mAdventQuestWorldRepository.saveAndFlush(mAdventQuestWorld);

        // Get all the mAdventQuestWorldList where specialRewardContentType is not null
        defaultMAdventQuestWorldShouldBeFound("specialRewardContentType.specified=true");

        // Get all the mAdventQuestWorldList where specialRewardContentType is null
        defaultMAdventQuestWorldShouldNotBeFound("specialRewardContentType.specified=false");
    }

    @Test
    @Transactional
    public void getAllMAdventQuestWorldsBySpecialRewardContentTypeIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mAdventQuestWorldRepository.saveAndFlush(mAdventQuestWorld);

        // Get all the mAdventQuestWorldList where specialRewardContentType greater than or equals to DEFAULT_SPECIAL_REWARD_CONTENT_TYPE
        defaultMAdventQuestWorldShouldBeFound("specialRewardContentType.greaterOrEqualThan=" + DEFAULT_SPECIAL_REWARD_CONTENT_TYPE);

        // Get all the mAdventQuestWorldList where specialRewardContentType greater than or equals to UPDATED_SPECIAL_REWARD_CONTENT_TYPE
        defaultMAdventQuestWorldShouldNotBeFound("specialRewardContentType.greaterOrEqualThan=" + UPDATED_SPECIAL_REWARD_CONTENT_TYPE);
    }

    @Test
    @Transactional
    public void getAllMAdventQuestWorldsBySpecialRewardContentTypeIsLessThanSomething() throws Exception {
        // Initialize the database
        mAdventQuestWorldRepository.saveAndFlush(mAdventQuestWorld);

        // Get all the mAdventQuestWorldList where specialRewardContentType less than or equals to DEFAULT_SPECIAL_REWARD_CONTENT_TYPE
        defaultMAdventQuestWorldShouldNotBeFound("specialRewardContentType.lessThan=" + DEFAULT_SPECIAL_REWARD_CONTENT_TYPE);

        // Get all the mAdventQuestWorldList where specialRewardContentType less than or equals to UPDATED_SPECIAL_REWARD_CONTENT_TYPE
        defaultMAdventQuestWorldShouldBeFound("specialRewardContentType.lessThan=" + UPDATED_SPECIAL_REWARD_CONTENT_TYPE);
    }


    @Test
    @Transactional
    public void getAllMAdventQuestWorldsBySpecialRewardContentIdIsEqualToSomething() throws Exception {
        // Initialize the database
        mAdventQuestWorldRepository.saveAndFlush(mAdventQuestWorld);

        // Get all the mAdventQuestWorldList where specialRewardContentId equals to DEFAULT_SPECIAL_REWARD_CONTENT_ID
        defaultMAdventQuestWorldShouldBeFound("specialRewardContentId.equals=" + DEFAULT_SPECIAL_REWARD_CONTENT_ID);

        // Get all the mAdventQuestWorldList where specialRewardContentId equals to UPDATED_SPECIAL_REWARD_CONTENT_ID
        defaultMAdventQuestWorldShouldNotBeFound("specialRewardContentId.equals=" + UPDATED_SPECIAL_REWARD_CONTENT_ID);
    }

    @Test
    @Transactional
    public void getAllMAdventQuestWorldsBySpecialRewardContentIdIsInShouldWork() throws Exception {
        // Initialize the database
        mAdventQuestWorldRepository.saveAndFlush(mAdventQuestWorld);

        // Get all the mAdventQuestWorldList where specialRewardContentId in DEFAULT_SPECIAL_REWARD_CONTENT_ID or UPDATED_SPECIAL_REWARD_CONTENT_ID
        defaultMAdventQuestWorldShouldBeFound("specialRewardContentId.in=" + DEFAULT_SPECIAL_REWARD_CONTENT_ID + "," + UPDATED_SPECIAL_REWARD_CONTENT_ID);

        // Get all the mAdventQuestWorldList where specialRewardContentId equals to UPDATED_SPECIAL_REWARD_CONTENT_ID
        defaultMAdventQuestWorldShouldNotBeFound("specialRewardContentId.in=" + UPDATED_SPECIAL_REWARD_CONTENT_ID);
    }

    @Test
    @Transactional
    public void getAllMAdventQuestWorldsBySpecialRewardContentIdIsNullOrNotNull() throws Exception {
        // Initialize the database
        mAdventQuestWorldRepository.saveAndFlush(mAdventQuestWorld);

        // Get all the mAdventQuestWorldList where specialRewardContentId is not null
        defaultMAdventQuestWorldShouldBeFound("specialRewardContentId.specified=true");

        // Get all the mAdventQuestWorldList where specialRewardContentId is null
        defaultMAdventQuestWorldShouldNotBeFound("specialRewardContentId.specified=false");
    }

    @Test
    @Transactional
    public void getAllMAdventQuestWorldsBySpecialRewardContentIdIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mAdventQuestWorldRepository.saveAndFlush(mAdventQuestWorld);

        // Get all the mAdventQuestWorldList where specialRewardContentId greater than or equals to DEFAULT_SPECIAL_REWARD_CONTENT_ID
        defaultMAdventQuestWorldShouldBeFound("specialRewardContentId.greaterOrEqualThan=" + DEFAULT_SPECIAL_REWARD_CONTENT_ID);

        // Get all the mAdventQuestWorldList where specialRewardContentId greater than or equals to UPDATED_SPECIAL_REWARD_CONTENT_ID
        defaultMAdventQuestWorldShouldNotBeFound("specialRewardContentId.greaterOrEqualThan=" + UPDATED_SPECIAL_REWARD_CONTENT_ID);
    }

    @Test
    @Transactional
    public void getAllMAdventQuestWorldsBySpecialRewardContentIdIsLessThanSomething() throws Exception {
        // Initialize the database
        mAdventQuestWorldRepository.saveAndFlush(mAdventQuestWorld);

        // Get all the mAdventQuestWorldList where specialRewardContentId less than or equals to DEFAULT_SPECIAL_REWARD_CONTENT_ID
        defaultMAdventQuestWorldShouldNotBeFound("specialRewardContentId.lessThan=" + DEFAULT_SPECIAL_REWARD_CONTENT_ID);

        // Get all the mAdventQuestWorldList where specialRewardContentId less than or equals to UPDATED_SPECIAL_REWARD_CONTENT_ID
        defaultMAdventQuestWorldShouldBeFound("specialRewardContentId.lessThan=" + UPDATED_SPECIAL_REWARD_CONTENT_ID);
    }


    @Test
    @Transactional
    public void getAllMAdventQuestWorldsByIsEnableCoopIsEqualToSomething() throws Exception {
        // Initialize the database
        mAdventQuestWorldRepository.saveAndFlush(mAdventQuestWorld);

        // Get all the mAdventQuestWorldList where isEnableCoop equals to DEFAULT_IS_ENABLE_COOP
        defaultMAdventQuestWorldShouldBeFound("isEnableCoop.equals=" + DEFAULT_IS_ENABLE_COOP);

        // Get all the mAdventQuestWorldList where isEnableCoop equals to UPDATED_IS_ENABLE_COOP
        defaultMAdventQuestWorldShouldNotBeFound("isEnableCoop.equals=" + UPDATED_IS_ENABLE_COOP);
    }

    @Test
    @Transactional
    public void getAllMAdventQuestWorldsByIsEnableCoopIsInShouldWork() throws Exception {
        // Initialize the database
        mAdventQuestWorldRepository.saveAndFlush(mAdventQuestWorld);

        // Get all the mAdventQuestWorldList where isEnableCoop in DEFAULT_IS_ENABLE_COOP or UPDATED_IS_ENABLE_COOP
        defaultMAdventQuestWorldShouldBeFound("isEnableCoop.in=" + DEFAULT_IS_ENABLE_COOP + "," + UPDATED_IS_ENABLE_COOP);

        // Get all the mAdventQuestWorldList where isEnableCoop equals to UPDATED_IS_ENABLE_COOP
        defaultMAdventQuestWorldShouldNotBeFound("isEnableCoop.in=" + UPDATED_IS_ENABLE_COOP);
    }

    @Test
    @Transactional
    public void getAllMAdventQuestWorldsByIsEnableCoopIsNullOrNotNull() throws Exception {
        // Initialize the database
        mAdventQuestWorldRepository.saveAndFlush(mAdventQuestWorld);

        // Get all the mAdventQuestWorldList where isEnableCoop is not null
        defaultMAdventQuestWorldShouldBeFound("isEnableCoop.specified=true");

        // Get all the mAdventQuestWorldList where isEnableCoop is null
        defaultMAdventQuestWorldShouldNotBeFound("isEnableCoop.specified=false");
    }

    @Test
    @Transactional
    public void getAllMAdventQuestWorldsByIsEnableCoopIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mAdventQuestWorldRepository.saveAndFlush(mAdventQuestWorld);

        // Get all the mAdventQuestWorldList where isEnableCoop greater than or equals to DEFAULT_IS_ENABLE_COOP
        defaultMAdventQuestWorldShouldBeFound("isEnableCoop.greaterOrEqualThan=" + DEFAULT_IS_ENABLE_COOP);

        // Get all the mAdventQuestWorldList where isEnableCoop greater than or equals to UPDATED_IS_ENABLE_COOP
        defaultMAdventQuestWorldShouldNotBeFound("isEnableCoop.greaterOrEqualThan=" + UPDATED_IS_ENABLE_COOP);
    }

    @Test
    @Transactional
    public void getAllMAdventQuestWorldsByIsEnableCoopIsLessThanSomething() throws Exception {
        // Initialize the database
        mAdventQuestWorldRepository.saveAndFlush(mAdventQuestWorld);

        // Get all the mAdventQuestWorldList where isEnableCoop less than or equals to DEFAULT_IS_ENABLE_COOP
        defaultMAdventQuestWorldShouldNotBeFound("isEnableCoop.lessThan=" + DEFAULT_IS_ENABLE_COOP);

        // Get all the mAdventQuestWorldList where isEnableCoop less than or equals to UPDATED_IS_ENABLE_COOP
        defaultMAdventQuestWorldShouldBeFound("isEnableCoop.lessThan=" + UPDATED_IS_ENABLE_COOP);
    }


    @Test
    @Transactional
    public void getAllMAdventQuestWorldsByMAdventQuestStageIsEqualToSomething() throws Exception {
        // Initialize the database
        MAdventQuestStage mAdventQuestStage = MAdventQuestStageResourceIT.createEntity(em);
        em.persist(mAdventQuestStage);
        em.flush();
        mAdventQuestWorld.addMAdventQuestStage(mAdventQuestStage);
        mAdventQuestWorldRepository.saveAndFlush(mAdventQuestWorld);
        Long mAdventQuestStageId = mAdventQuestStage.getId();

        // Get all the mAdventQuestWorldList where mAdventQuestStage equals to mAdventQuestStageId
        defaultMAdventQuestWorldShouldBeFound("mAdventQuestStageId.equals=" + mAdventQuestStageId);

        // Get all the mAdventQuestWorldList where mAdventQuestStage equals to mAdventQuestStageId + 1
        defaultMAdventQuestWorldShouldNotBeFound("mAdventQuestStageId.equals=" + (mAdventQuestStageId + 1));
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultMAdventQuestWorldShouldBeFound(String filter) throws Exception {
        restMAdventQuestWorldMockMvc.perform(get("/api/m-advent-quest-worlds?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(mAdventQuestWorld.getId().intValue())))
            .andExpect(jsonPath("$.[*].setId").value(hasItem(DEFAULT_SET_ID)))
            .andExpect(jsonPath("$.[*].number").value(hasItem(DEFAULT_NUMBER)))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME.toString())))
            .andExpect(jsonPath("$.[*].symbolType").value(hasItem(DEFAULT_SYMBOL_TYPE)))
            .andExpect(jsonPath("$.[*].imagePath").value(hasItem(DEFAULT_IMAGE_PATH.toString())))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION.toString())))
            .andExpect(jsonPath("$.[*].stageUnlockPattern").value(hasItem(DEFAULT_STAGE_UNLOCK_PATTERN)))
            .andExpect(jsonPath("$.[*].arousalBanner").value(hasItem(DEFAULT_AROUSAL_BANNER.toString())))
            .andExpect(jsonPath("$.[*].specialRewardContentType").value(hasItem(DEFAULT_SPECIAL_REWARD_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].specialRewardContentId").value(hasItem(DEFAULT_SPECIAL_REWARD_CONTENT_ID)))
            .andExpect(jsonPath("$.[*].isEnableCoop").value(hasItem(DEFAULT_IS_ENABLE_COOP)));

        // Check, that the count call also returns 1
        restMAdventQuestWorldMockMvc.perform(get("/api/m-advent-quest-worlds/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultMAdventQuestWorldShouldNotBeFound(String filter) throws Exception {
        restMAdventQuestWorldMockMvc.perform(get("/api/m-advent-quest-worlds?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restMAdventQuestWorldMockMvc.perform(get("/api/m-advent-quest-worlds/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("0"));
    }


    @Test
    @Transactional
    public void getNonExistingMAdventQuestWorld() throws Exception {
        // Get the mAdventQuestWorld
        restMAdventQuestWorldMockMvc.perform(get("/api/m-advent-quest-worlds/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateMAdventQuestWorld() throws Exception {
        // Initialize the database
        mAdventQuestWorldRepository.saveAndFlush(mAdventQuestWorld);

        int databaseSizeBeforeUpdate = mAdventQuestWorldRepository.findAll().size();

        // Update the mAdventQuestWorld
        MAdventQuestWorld updatedMAdventQuestWorld = mAdventQuestWorldRepository.findById(mAdventQuestWorld.getId()).get();
        // Disconnect from session so that the updates on updatedMAdventQuestWorld are not directly saved in db
        em.detach(updatedMAdventQuestWorld);
        updatedMAdventQuestWorld
            .setId(UPDATED_SET_ID)
            .number(UPDATED_NUMBER)
            .name(UPDATED_NAME)
            .symbolType(UPDATED_SYMBOL_TYPE)
            .imagePath(UPDATED_IMAGE_PATH)
            .description(UPDATED_DESCRIPTION)
            .stageUnlockPattern(UPDATED_STAGE_UNLOCK_PATTERN)
            .arousalBanner(UPDATED_AROUSAL_BANNER)
            .specialRewardContentType(UPDATED_SPECIAL_REWARD_CONTENT_TYPE)
            .specialRewardContentId(UPDATED_SPECIAL_REWARD_CONTENT_ID)
            .isEnableCoop(UPDATED_IS_ENABLE_COOP);
        MAdventQuestWorldDTO mAdventQuestWorldDTO = mAdventQuestWorldMapper.toDto(updatedMAdventQuestWorld);

        restMAdventQuestWorldMockMvc.perform(put("/api/m-advent-quest-worlds")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mAdventQuestWorldDTO)))
            .andExpect(status().isOk());

        // Validate the MAdventQuestWorld in the database
        List<MAdventQuestWorld> mAdventQuestWorldList = mAdventQuestWorldRepository.findAll();
        assertThat(mAdventQuestWorldList).hasSize(databaseSizeBeforeUpdate);
        MAdventQuestWorld testMAdventQuestWorld = mAdventQuestWorldList.get(mAdventQuestWorldList.size() - 1);
        assertThat(testMAdventQuestWorld.getSetId()).isEqualTo(UPDATED_SET_ID);
        assertThat(testMAdventQuestWorld.getNumber()).isEqualTo(UPDATED_NUMBER);
        assertThat(testMAdventQuestWorld.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testMAdventQuestWorld.getSymbolType()).isEqualTo(UPDATED_SYMBOL_TYPE);
        assertThat(testMAdventQuestWorld.getImagePath()).isEqualTo(UPDATED_IMAGE_PATH);
        assertThat(testMAdventQuestWorld.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
        assertThat(testMAdventQuestWorld.getStageUnlockPattern()).isEqualTo(UPDATED_STAGE_UNLOCK_PATTERN);
        assertThat(testMAdventQuestWorld.getArousalBanner()).isEqualTo(UPDATED_AROUSAL_BANNER);
        assertThat(testMAdventQuestWorld.getSpecialRewardContentType()).isEqualTo(UPDATED_SPECIAL_REWARD_CONTENT_TYPE);
        assertThat(testMAdventQuestWorld.getSpecialRewardContentId()).isEqualTo(UPDATED_SPECIAL_REWARD_CONTENT_ID);
        assertThat(testMAdventQuestWorld.getIsEnableCoop()).isEqualTo(UPDATED_IS_ENABLE_COOP);
    }

    @Test
    @Transactional
    public void updateNonExistingMAdventQuestWorld() throws Exception {
        int databaseSizeBeforeUpdate = mAdventQuestWorldRepository.findAll().size();

        // Create the MAdventQuestWorld
        MAdventQuestWorldDTO mAdventQuestWorldDTO = mAdventQuestWorldMapper.toDto(mAdventQuestWorld);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restMAdventQuestWorldMockMvc.perform(put("/api/m-advent-quest-worlds")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mAdventQuestWorldDTO)))
            .andExpect(status().isBadRequest());

        // Validate the MAdventQuestWorld in the database
        List<MAdventQuestWorld> mAdventQuestWorldList = mAdventQuestWorldRepository.findAll();
        assertThat(mAdventQuestWorldList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteMAdventQuestWorld() throws Exception {
        // Initialize the database
        mAdventQuestWorldRepository.saveAndFlush(mAdventQuestWorld);

        int databaseSizeBeforeDelete = mAdventQuestWorldRepository.findAll().size();

        // Delete the mAdventQuestWorld
        restMAdventQuestWorldMockMvc.perform(delete("/api/m-advent-quest-worlds/{id}", mAdventQuestWorld.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database is empty
        List<MAdventQuestWorld> mAdventQuestWorldList = mAdventQuestWorldRepository.findAll();
        assertThat(mAdventQuestWorldList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(MAdventQuestWorld.class);
        MAdventQuestWorld mAdventQuestWorld1 = new MAdventQuestWorld();
        mAdventQuestWorld1.setId(1L);
        MAdventQuestWorld mAdventQuestWorld2 = new MAdventQuestWorld();
        mAdventQuestWorld2.setId(mAdventQuestWorld1.getId());
        assertThat(mAdventQuestWorld1).isEqualTo(mAdventQuestWorld2);
        mAdventQuestWorld2.setId(2L);
        assertThat(mAdventQuestWorld1).isNotEqualTo(mAdventQuestWorld2);
        mAdventQuestWorld1.setId(null);
        assertThat(mAdventQuestWorld1).isNotEqualTo(mAdventQuestWorld2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(MAdventQuestWorldDTO.class);
        MAdventQuestWorldDTO mAdventQuestWorldDTO1 = new MAdventQuestWorldDTO();
        mAdventQuestWorldDTO1.setId(1L);
        MAdventQuestWorldDTO mAdventQuestWorldDTO2 = new MAdventQuestWorldDTO();
        assertThat(mAdventQuestWorldDTO1).isNotEqualTo(mAdventQuestWorldDTO2);
        mAdventQuestWorldDTO2.setId(mAdventQuestWorldDTO1.getId());
        assertThat(mAdventQuestWorldDTO1).isEqualTo(mAdventQuestWorldDTO2);
        mAdventQuestWorldDTO2.setId(2L);
        assertThat(mAdventQuestWorldDTO1).isNotEqualTo(mAdventQuestWorldDTO2);
        mAdventQuestWorldDTO1.setId(null);
        assertThat(mAdventQuestWorldDTO1).isNotEqualTo(mAdventQuestWorldDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(mAdventQuestWorldMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(mAdventQuestWorldMapper.fromId(null)).isNull();
    }
}
