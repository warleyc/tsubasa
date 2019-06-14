package io.shm.tsubasa.web.rest;

import io.shm.tsubasa.TsubasaApp;
import io.shm.tsubasa.domain.MGuerillaQuestWorld;
import io.shm.tsubasa.domain.MGuerillaQuestStage;
import io.shm.tsubasa.repository.MGuerillaQuestWorldRepository;
import io.shm.tsubasa.service.MGuerillaQuestWorldService;
import io.shm.tsubasa.service.dto.MGuerillaQuestWorldDTO;
import io.shm.tsubasa.service.mapper.MGuerillaQuestWorldMapper;
import io.shm.tsubasa.web.rest.errors.ExceptionTranslator;
import io.shm.tsubasa.service.dto.MGuerillaQuestWorldCriteria;
import io.shm.tsubasa.service.MGuerillaQuestWorldQueryService;

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
 * Integration tests for the {@Link MGuerillaQuestWorldResource} REST controller.
 */
@SpringBootTest(classes = TsubasaApp.class)
public class MGuerillaQuestWorldResourceIT {

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
    private MGuerillaQuestWorldRepository mGuerillaQuestWorldRepository;

    @Autowired
    private MGuerillaQuestWorldMapper mGuerillaQuestWorldMapper;

    @Autowired
    private MGuerillaQuestWorldService mGuerillaQuestWorldService;

    @Autowired
    private MGuerillaQuestWorldQueryService mGuerillaQuestWorldQueryService;

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

    private MockMvc restMGuerillaQuestWorldMockMvc;

    private MGuerillaQuestWorld mGuerillaQuestWorld;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final MGuerillaQuestWorldResource mGuerillaQuestWorldResource = new MGuerillaQuestWorldResource(mGuerillaQuestWorldService, mGuerillaQuestWorldQueryService);
        this.restMGuerillaQuestWorldMockMvc = MockMvcBuilders.standaloneSetup(mGuerillaQuestWorldResource)
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
    public static MGuerillaQuestWorld createEntity(EntityManager em) {
        MGuerillaQuestWorld mGuerillaQuestWorld = new MGuerillaQuestWorld()
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
        return mGuerillaQuestWorld;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static MGuerillaQuestWorld createUpdatedEntity(EntityManager em) {
        MGuerillaQuestWorld mGuerillaQuestWorld = new MGuerillaQuestWorld()
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
        return mGuerillaQuestWorld;
    }

    @BeforeEach
    public void initTest() {
        mGuerillaQuestWorld = createEntity(em);
    }

    @Test
    @Transactional
    public void createMGuerillaQuestWorld() throws Exception {
        int databaseSizeBeforeCreate = mGuerillaQuestWorldRepository.findAll().size();

        // Create the MGuerillaQuestWorld
        MGuerillaQuestWorldDTO mGuerillaQuestWorldDTO = mGuerillaQuestWorldMapper.toDto(mGuerillaQuestWorld);
        restMGuerillaQuestWorldMockMvc.perform(post("/api/m-guerilla-quest-worlds")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mGuerillaQuestWorldDTO)))
            .andExpect(status().isCreated());

        // Validate the MGuerillaQuestWorld in the database
        List<MGuerillaQuestWorld> mGuerillaQuestWorldList = mGuerillaQuestWorldRepository.findAll();
        assertThat(mGuerillaQuestWorldList).hasSize(databaseSizeBeforeCreate + 1);
        MGuerillaQuestWorld testMGuerillaQuestWorld = mGuerillaQuestWorldList.get(mGuerillaQuestWorldList.size() - 1);
        assertThat(testMGuerillaQuestWorld.getSetId()).isEqualTo(DEFAULT_SET_ID);
        assertThat(testMGuerillaQuestWorld.getNumber()).isEqualTo(DEFAULT_NUMBER);
        assertThat(testMGuerillaQuestWorld.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testMGuerillaQuestWorld.getImagePath()).isEqualTo(DEFAULT_IMAGE_PATH);
        assertThat(testMGuerillaQuestWorld.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
        assertThat(testMGuerillaQuestWorld.getStageUnlockPattern()).isEqualTo(DEFAULT_STAGE_UNLOCK_PATTERN);
        assertThat(testMGuerillaQuestWorld.getArousalBanner()).isEqualTo(DEFAULT_AROUSAL_BANNER);
        assertThat(testMGuerillaQuestWorld.getSpecialRewardContentType()).isEqualTo(DEFAULT_SPECIAL_REWARD_CONTENT_TYPE);
        assertThat(testMGuerillaQuestWorld.getSpecialRewardContentId()).isEqualTo(DEFAULT_SPECIAL_REWARD_CONTENT_ID);
        assertThat(testMGuerillaQuestWorld.getIsEnableCoop()).isEqualTo(DEFAULT_IS_ENABLE_COOP);
    }

    @Test
    @Transactional
    public void createMGuerillaQuestWorldWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = mGuerillaQuestWorldRepository.findAll().size();

        // Create the MGuerillaQuestWorld with an existing ID
        mGuerillaQuestWorld.setId(1L);
        MGuerillaQuestWorldDTO mGuerillaQuestWorldDTO = mGuerillaQuestWorldMapper.toDto(mGuerillaQuestWorld);

        // An entity with an existing ID cannot be created, so this API call must fail
        restMGuerillaQuestWorldMockMvc.perform(post("/api/m-guerilla-quest-worlds")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mGuerillaQuestWorldDTO)))
            .andExpect(status().isBadRequest());

        // Validate the MGuerillaQuestWorld in the database
        List<MGuerillaQuestWorld> mGuerillaQuestWorldList = mGuerillaQuestWorldRepository.findAll();
        assertThat(mGuerillaQuestWorldList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkSetIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = mGuerillaQuestWorldRepository.findAll().size();
        // set the field null
        mGuerillaQuestWorld.setSetId(null);

        // Create the MGuerillaQuestWorld, which fails.
        MGuerillaQuestWorldDTO mGuerillaQuestWorldDTO = mGuerillaQuestWorldMapper.toDto(mGuerillaQuestWorld);

        restMGuerillaQuestWorldMockMvc.perform(post("/api/m-guerilla-quest-worlds")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mGuerillaQuestWorldDTO)))
            .andExpect(status().isBadRequest());

        List<MGuerillaQuestWorld> mGuerillaQuestWorldList = mGuerillaQuestWorldRepository.findAll();
        assertThat(mGuerillaQuestWorldList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkNumberIsRequired() throws Exception {
        int databaseSizeBeforeTest = mGuerillaQuestWorldRepository.findAll().size();
        // set the field null
        mGuerillaQuestWorld.setNumber(null);

        // Create the MGuerillaQuestWorld, which fails.
        MGuerillaQuestWorldDTO mGuerillaQuestWorldDTO = mGuerillaQuestWorldMapper.toDto(mGuerillaQuestWorld);

        restMGuerillaQuestWorldMockMvc.perform(post("/api/m-guerilla-quest-worlds")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mGuerillaQuestWorldDTO)))
            .andExpect(status().isBadRequest());

        List<MGuerillaQuestWorld> mGuerillaQuestWorldList = mGuerillaQuestWorldRepository.findAll();
        assertThat(mGuerillaQuestWorldList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkStageUnlockPatternIsRequired() throws Exception {
        int databaseSizeBeforeTest = mGuerillaQuestWorldRepository.findAll().size();
        // set the field null
        mGuerillaQuestWorld.setStageUnlockPattern(null);

        // Create the MGuerillaQuestWorld, which fails.
        MGuerillaQuestWorldDTO mGuerillaQuestWorldDTO = mGuerillaQuestWorldMapper.toDto(mGuerillaQuestWorld);

        restMGuerillaQuestWorldMockMvc.perform(post("/api/m-guerilla-quest-worlds")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mGuerillaQuestWorldDTO)))
            .andExpect(status().isBadRequest());

        List<MGuerillaQuestWorld> mGuerillaQuestWorldList = mGuerillaQuestWorldRepository.findAll();
        assertThat(mGuerillaQuestWorldList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkIsEnableCoopIsRequired() throws Exception {
        int databaseSizeBeforeTest = mGuerillaQuestWorldRepository.findAll().size();
        // set the field null
        mGuerillaQuestWorld.setIsEnableCoop(null);

        // Create the MGuerillaQuestWorld, which fails.
        MGuerillaQuestWorldDTO mGuerillaQuestWorldDTO = mGuerillaQuestWorldMapper.toDto(mGuerillaQuestWorld);

        restMGuerillaQuestWorldMockMvc.perform(post("/api/m-guerilla-quest-worlds")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mGuerillaQuestWorldDTO)))
            .andExpect(status().isBadRequest());

        List<MGuerillaQuestWorld> mGuerillaQuestWorldList = mGuerillaQuestWorldRepository.findAll();
        assertThat(mGuerillaQuestWorldList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllMGuerillaQuestWorlds() throws Exception {
        // Initialize the database
        mGuerillaQuestWorldRepository.saveAndFlush(mGuerillaQuestWorld);

        // Get all the mGuerillaQuestWorldList
        restMGuerillaQuestWorldMockMvc.perform(get("/api/m-guerilla-quest-worlds?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(mGuerillaQuestWorld.getId().intValue())))
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
    public void getMGuerillaQuestWorld() throws Exception {
        // Initialize the database
        mGuerillaQuestWorldRepository.saveAndFlush(mGuerillaQuestWorld);

        // Get the mGuerillaQuestWorld
        restMGuerillaQuestWorldMockMvc.perform(get("/api/m-guerilla-quest-worlds/{id}", mGuerillaQuestWorld.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(mGuerillaQuestWorld.getId().intValue()))
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
    public void getAllMGuerillaQuestWorldsBySetIdIsEqualToSomething() throws Exception {
        // Initialize the database
        mGuerillaQuestWorldRepository.saveAndFlush(mGuerillaQuestWorld);

        // Get all the mGuerillaQuestWorldList where setId equals to DEFAULT_SET_ID
        defaultMGuerillaQuestWorldShouldBeFound("setId.equals=" + DEFAULT_SET_ID);

        // Get all the mGuerillaQuestWorldList where setId equals to UPDATED_SET_ID
        defaultMGuerillaQuestWorldShouldNotBeFound("setId.equals=" + UPDATED_SET_ID);
    }

    @Test
    @Transactional
    public void getAllMGuerillaQuestWorldsBySetIdIsInShouldWork() throws Exception {
        // Initialize the database
        mGuerillaQuestWorldRepository.saveAndFlush(mGuerillaQuestWorld);

        // Get all the mGuerillaQuestWorldList where setId in DEFAULT_SET_ID or UPDATED_SET_ID
        defaultMGuerillaQuestWorldShouldBeFound("setId.in=" + DEFAULT_SET_ID + "," + UPDATED_SET_ID);

        // Get all the mGuerillaQuestWorldList where setId equals to UPDATED_SET_ID
        defaultMGuerillaQuestWorldShouldNotBeFound("setId.in=" + UPDATED_SET_ID);
    }

    @Test
    @Transactional
    public void getAllMGuerillaQuestWorldsBySetIdIsNullOrNotNull() throws Exception {
        // Initialize the database
        mGuerillaQuestWorldRepository.saveAndFlush(mGuerillaQuestWorld);

        // Get all the mGuerillaQuestWorldList where setId is not null
        defaultMGuerillaQuestWorldShouldBeFound("setId.specified=true");

        // Get all the mGuerillaQuestWorldList where setId is null
        defaultMGuerillaQuestWorldShouldNotBeFound("setId.specified=false");
    }

    @Test
    @Transactional
    public void getAllMGuerillaQuestWorldsBySetIdIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mGuerillaQuestWorldRepository.saveAndFlush(mGuerillaQuestWorld);

        // Get all the mGuerillaQuestWorldList where setId greater than or equals to DEFAULT_SET_ID
        defaultMGuerillaQuestWorldShouldBeFound("setId.greaterOrEqualThan=" + DEFAULT_SET_ID);

        // Get all the mGuerillaQuestWorldList where setId greater than or equals to UPDATED_SET_ID
        defaultMGuerillaQuestWorldShouldNotBeFound("setId.greaterOrEqualThan=" + UPDATED_SET_ID);
    }

    @Test
    @Transactional
    public void getAllMGuerillaQuestWorldsBySetIdIsLessThanSomething() throws Exception {
        // Initialize the database
        mGuerillaQuestWorldRepository.saveAndFlush(mGuerillaQuestWorld);

        // Get all the mGuerillaQuestWorldList where setId less than or equals to DEFAULT_SET_ID
        defaultMGuerillaQuestWorldShouldNotBeFound("setId.lessThan=" + DEFAULT_SET_ID);

        // Get all the mGuerillaQuestWorldList where setId less than or equals to UPDATED_SET_ID
        defaultMGuerillaQuestWorldShouldBeFound("setId.lessThan=" + UPDATED_SET_ID);
    }


    @Test
    @Transactional
    public void getAllMGuerillaQuestWorldsByNumberIsEqualToSomething() throws Exception {
        // Initialize the database
        mGuerillaQuestWorldRepository.saveAndFlush(mGuerillaQuestWorld);

        // Get all the mGuerillaQuestWorldList where number equals to DEFAULT_NUMBER
        defaultMGuerillaQuestWorldShouldBeFound("number.equals=" + DEFAULT_NUMBER);

        // Get all the mGuerillaQuestWorldList where number equals to UPDATED_NUMBER
        defaultMGuerillaQuestWorldShouldNotBeFound("number.equals=" + UPDATED_NUMBER);
    }

    @Test
    @Transactional
    public void getAllMGuerillaQuestWorldsByNumberIsInShouldWork() throws Exception {
        // Initialize the database
        mGuerillaQuestWorldRepository.saveAndFlush(mGuerillaQuestWorld);

        // Get all the mGuerillaQuestWorldList where number in DEFAULT_NUMBER or UPDATED_NUMBER
        defaultMGuerillaQuestWorldShouldBeFound("number.in=" + DEFAULT_NUMBER + "," + UPDATED_NUMBER);

        // Get all the mGuerillaQuestWorldList where number equals to UPDATED_NUMBER
        defaultMGuerillaQuestWorldShouldNotBeFound("number.in=" + UPDATED_NUMBER);
    }

    @Test
    @Transactional
    public void getAllMGuerillaQuestWorldsByNumberIsNullOrNotNull() throws Exception {
        // Initialize the database
        mGuerillaQuestWorldRepository.saveAndFlush(mGuerillaQuestWorld);

        // Get all the mGuerillaQuestWorldList where number is not null
        defaultMGuerillaQuestWorldShouldBeFound("number.specified=true");

        // Get all the mGuerillaQuestWorldList where number is null
        defaultMGuerillaQuestWorldShouldNotBeFound("number.specified=false");
    }

    @Test
    @Transactional
    public void getAllMGuerillaQuestWorldsByNumberIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mGuerillaQuestWorldRepository.saveAndFlush(mGuerillaQuestWorld);

        // Get all the mGuerillaQuestWorldList where number greater than or equals to DEFAULT_NUMBER
        defaultMGuerillaQuestWorldShouldBeFound("number.greaterOrEqualThan=" + DEFAULT_NUMBER);

        // Get all the mGuerillaQuestWorldList where number greater than or equals to UPDATED_NUMBER
        defaultMGuerillaQuestWorldShouldNotBeFound("number.greaterOrEqualThan=" + UPDATED_NUMBER);
    }

    @Test
    @Transactional
    public void getAllMGuerillaQuestWorldsByNumberIsLessThanSomething() throws Exception {
        // Initialize the database
        mGuerillaQuestWorldRepository.saveAndFlush(mGuerillaQuestWorld);

        // Get all the mGuerillaQuestWorldList where number less than or equals to DEFAULT_NUMBER
        defaultMGuerillaQuestWorldShouldNotBeFound("number.lessThan=" + DEFAULT_NUMBER);

        // Get all the mGuerillaQuestWorldList where number less than or equals to UPDATED_NUMBER
        defaultMGuerillaQuestWorldShouldBeFound("number.lessThan=" + UPDATED_NUMBER);
    }


    @Test
    @Transactional
    public void getAllMGuerillaQuestWorldsByStageUnlockPatternIsEqualToSomething() throws Exception {
        // Initialize the database
        mGuerillaQuestWorldRepository.saveAndFlush(mGuerillaQuestWorld);

        // Get all the mGuerillaQuestWorldList where stageUnlockPattern equals to DEFAULT_STAGE_UNLOCK_PATTERN
        defaultMGuerillaQuestWorldShouldBeFound("stageUnlockPattern.equals=" + DEFAULT_STAGE_UNLOCK_PATTERN);

        // Get all the mGuerillaQuestWorldList where stageUnlockPattern equals to UPDATED_STAGE_UNLOCK_PATTERN
        defaultMGuerillaQuestWorldShouldNotBeFound("stageUnlockPattern.equals=" + UPDATED_STAGE_UNLOCK_PATTERN);
    }

    @Test
    @Transactional
    public void getAllMGuerillaQuestWorldsByStageUnlockPatternIsInShouldWork() throws Exception {
        // Initialize the database
        mGuerillaQuestWorldRepository.saveAndFlush(mGuerillaQuestWorld);

        // Get all the mGuerillaQuestWorldList where stageUnlockPattern in DEFAULT_STAGE_UNLOCK_PATTERN or UPDATED_STAGE_UNLOCK_PATTERN
        defaultMGuerillaQuestWorldShouldBeFound("stageUnlockPattern.in=" + DEFAULT_STAGE_UNLOCK_PATTERN + "," + UPDATED_STAGE_UNLOCK_PATTERN);

        // Get all the mGuerillaQuestWorldList where stageUnlockPattern equals to UPDATED_STAGE_UNLOCK_PATTERN
        defaultMGuerillaQuestWorldShouldNotBeFound("stageUnlockPattern.in=" + UPDATED_STAGE_UNLOCK_PATTERN);
    }

    @Test
    @Transactional
    public void getAllMGuerillaQuestWorldsByStageUnlockPatternIsNullOrNotNull() throws Exception {
        // Initialize the database
        mGuerillaQuestWorldRepository.saveAndFlush(mGuerillaQuestWorld);

        // Get all the mGuerillaQuestWorldList where stageUnlockPattern is not null
        defaultMGuerillaQuestWorldShouldBeFound("stageUnlockPattern.specified=true");

        // Get all the mGuerillaQuestWorldList where stageUnlockPattern is null
        defaultMGuerillaQuestWorldShouldNotBeFound("stageUnlockPattern.specified=false");
    }

    @Test
    @Transactional
    public void getAllMGuerillaQuestWorldsByStageUnlockPatternIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mGuerillaQuestWorldRepository.saveAndFlush(mGuerillaQuestWorld);

        // Get all the mGuerillaQuestWorldList where stageUnlockPattern greater than or equals to DEFAULT_STAGE_UNLOCK_PATTERN
        defaultMGuerillaQuestWorldShouldBeFound("stageUnlockPattern.greaterOrEqualThan=" + DEFAULT_STAGE_UNLOCK_PATTERN);

        // Get all the mGuerillaQuestWorldList where stageUnlockPattern greater than or equals to UPDATED_STAGE_UNLOCK_PATTERN
        defaultMGuerillaQuestWorldShouldNotBeFound("stageUnlockPattern.greaterOrEqualThan=" + UPDATED_STAGE_UNLOCK_PATTERN);
    }

    @Test
    @Transactional
    public void getAllMGuerillaQuestWorldsByStageUnlockPatternIsLessThanSomething() throws Exception {
        // Initialize the database
        mGuerillaQuestWorldRepository.saveAndFlush(mGuerillaQuestWorld);

        // Get all the mGuerillaQuestWorldList where stageUnlockPattern less than or equals to DEFAULT_STAGE_UNLOCK_PATTERN
        defaultMGuerillaQuestWorldShouldNotBeFound("stageUnlockPattern.lessThan=" + DEFAULT_STAGE_UNLOCK_PATTERN);

        // Get all the mGuerillaQuestWorldList where stageUnlockPattern less than or equals to UPDATED_STAGE_UNLOCK_PATTERN
        defaultMGuerillaQuestWorldShouldBeFound("stageUnlockPattern.lessThan=" + UPDATED_STAGE_UNLOCK_PATTERN);
    }


    @Test
    @Transactional
    public void getAllMGuerillaQuestWorldsBySpecialRewardContentTypeIsEqualToSomething() throws Exception {
        // Initialize the database
        mGuerillaQuestWorldRepository.saveAndFlush(mGuerillaQuestWorld);

        // Get all the mGuerillaQuestWorldList where specialRewardContentType equals to DEFAULT_SPECIAL_REWARD_CONTENT_TYPE
        defaultMGuerillaQuestWorldShouldBeFound("specialRewardContentType.equals=" + DEFAULT_SPECIAL_REWARD_CONTENT_TYPE);

        // Get all the mGuerillaQuestWorldList where specialRewardContentType equals to UPDATED_SPECIAL_REWARD_CONTENT_TYPE
        defaultMGuerillaQuestWorldShouldNotBeFound("specialRewardContentType.equals=" + UPDATED_SPECIAL_REWARD_CONTENT_TYPE);
    }

    @Test
    @Transactional
    public void getAllMGuerillaQuestWorldsBySpecialRewardContentTypeIsInShouldWork() throws Exception {
        // Initialize the database
        mGuerillaQuestWorldRepository.saveAndFlush(mGuerillaQuestWorld);

        // Get all the mGuerillaQuestWorldList where specialRewardContentType in DEFAULT_SPECIAL_REWARD_CONTENT_TYPE or UPDATED_SPECIAL_REWARD_CONTENT_TYPE
        defaultMGuerillaQuestWorldShouldBeFound("specialRewardContentType.in=" + DEFAULT_SPECIAL_REWARD_CONTENT_TYPE + "," + UPDATED_SPECIAL_REWARD_CONTENT_TYPE);

        // Get all the mGuerillaQuestWorldList where specialRewardContentType equals to UPDATED_SPECIAL_REWARD_CONTENT_TYPE
        defaultMGuerillaQuestWorldShouldNotBeFound("specialRewardContentType.in=" + UPDATED_SPECIAL_REWARD_CONTENT_TYPE);
    }

    @Test
    @Transactional
    public void getAllMGuerillaQuestWorldsBySpecialRewardContentTypeIsNullOrNotNull() throws Exception {
        // Initialize the database
        mGuerillaQuestWorldRepository.saveAndFlush(mGuerillaQuestWorld);

        // Get all the mGuerillaQuestWorldList where specialRewardContentType is not null
        defaultMGuerillaQuestWorldShouldBeFound("specialRewardContentType.specified=true");

        // Get all the mGuerillaQuestWorldList where specialRewardContentType is null
        defaultMGuerillaQuestWorldShouldNotBeFound("specialRewardContentType.specified=false");
    }

    @Test
    @Transactional
    public void getAllMGuerillaQuestWorldsBySpecialRewardContentTypeIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mGuerillaQuestWorldRepository.saveAndFlush(mGuerillaQuestWorld);

        // Get all the mGuerillaQuestWorldList where specialRewardContentType greater than or equals to DEFAULT_SPECIAL_REWARD_CONTENT_TYPE
        defaultMGuerillaQuestWorldShouldBeFound("specialRewardContentType.greaterOrEqualThan=" + DEFAULT_SPECIAL_REWARD_CONTENT_TYPE);

        // Get all the mGuerillaQuestWorldList where specialRewardContentType greater than or equals to UPDATED_SPECIAL_REWARD_CONTENT_TYPE
        defaultMGuerillaQuestWorldShouldNotBeFound("specialRewardContentType.greaterOrEqualThan=" + UPDATED_SPECIAL_REWARD_CONTENT_TYPE);
    }

    @Test
    @Transactional
    public void getAllMGuerillaQuestWorldsBySpecialRewardContentTypeIsLessThanSomething() throws Exception {
        // Initialize the database
        mGuerillaQuestWorldRepository.saveAndFlush(mGuerillaQuestWorld);

        // Get all the mGuerillaQuestWorldList where specialRewardContentType less than or equals to DEFAULT_SPECIAL_REWARD_CONTENT_TYPE
        defaultMGuerillaQuestWorldShouldNotBeFound("specialRewardContentType.lessThan=" + DEFAULT_SPECIAL_REWARD_CONTENT_TYPE);

        // Get all the mGuerillaQuestWorldList where specialRewardContentType less than or equals to UPDATED_SPECIAL_REWARD_CONTENT_TYPE
        defaultMGuerillaQuestWorldShouldBeFound("specialRewardContentType.lessThan=" + UPDATED_SPECIAL_REWARD_CONTENT_TYPE);
    }


    @Test
    @Transactional
    public void getAllMGuerillaQuestWorldsBySpecialRewardContentIdIsEqualToSomething() throws Exception {
        // Initialize the database
        mGuerillaQuestWorldRepository.saveAndFlush(mGuerillaQuestWorld);

        // Get all the mGuerillaQuestWorldList where specialRewardContentId equals to DEFAULT_SPECIAL_REWARD_CONTENT_ID
        defaultMGuerillaQuestWorldShouldBeFound("specialRewardContentId.equals=" + DEFAULT_SPECIAL_REWARD_CONTENT_ID);

        // Get all the mGuerillaQuestWorldList where specialRewardContentId equals to UPDATED_SPECIAL_REWARD_CONTENT_ID
        defaultMGuerillaQuestWorldShouldNotBeFound("specialRewardContentId.equals=" + UPDATED_SPECIAL_REWARD_CONTENT_ID);
    }

    @Test
    @Transactional
    public void getAllMGuerillaQuestWorldsBySpecialRewardContentIdIsInShouldWork() throws Exception {
        // Initialize the database
        mGuerillaQuestWorldRepository.saveAndFlush(mGuerillaQuestWorld);

        // Get all the mGuerillaQuestWorldList where specialRewardContentId in DEFAULT_SPECIAL_REWARD_CONTENT_ID or UPDATED_SPECIAL_REWARD_CONTENT_ID
        defaultMGuerillaQuestWorldShouldBeFound("specialRewardContentId.in=" + DEFAULT_SPECIAL_REWARD_CONTENT_ID + "," + UPDATED_SPECIAL_REWARD_CONTENT_ID);

        // Get all the mGuerillaQuestWorldList where specialRewardContentId equals to UPDATED_SPECIAL_REWARD_CONTENT_ID
        defaultMGuerillaQuestWorldShouldNotBeFound("specialRewardContentId.in=" + UPDATED_SPECIAL_REWARD_CONTENT_ID);
    }

    @Test
    @Transactional
    public void getAllMGuerillaQuestWorldsBySpecialRewardContentIdIsNullOrNotNull() throws Exception {
        // Initialize the database
        mGuerillaQuestWorldRepository.saveAndFlush(mGuerillaQuestWorld);

        // Get all the mGuerillaQuestWorldList where specialRewardContentId is not null
        defaultMGuerillaQuestWorldShouldBeFound("specialRewardContentId.specified=true");

        // Get all the mGuerillaQuestWorldList where specialRewardContentId is null
        defaultMGuerillaQuestWorldShouldNotBeFound("specialRewardContentId.specified=false");
    }

    @Test
    @Transactional
    public void getAllMGuerillaQuestWorldsBySpecialRewardContentIdIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mGuerillaQuestWorldRepository.saveAndFlush(mGuerillaQuestWorld);

        // Get all the mGuerillaQuestWorldList where specialRewardContentId greater than or equals to DEFAULT_SPECIAL_REWARD_CONTENT_ID
        defaultMGuerillaQuestWorldShouldBeFound("specialRewardContentId.greaterOrEqualThan=" + DEFAULT_SPECIAL_REWARD_CONTENT_ID);

        // Get all the mGuerillaQuestWorldList where specialRewardContentId greater than or equals to UPDATED_SPECIAL_REWARD_CONTENT_ID
        defaultMGuerillaQuestWorldShouldNotBeFound("specialRewardContentId.greaterOrEqualThan=" + UPDATED_SPECIAL_REWARD_CONTENT_ID);
    }

    @Test
    @Transactional
    public void getAllMGuerillaQuestWorldsBySpecialRewardContentIdIsLessThanSomething() throws Exception {
        // Initialize the database
        mGuerillaQuestWorldRepository.saveAndFlush(mGuerillaQuestWorld);

        // Get all the mGuerillaQuestWorldList where specialRewardContentId less than or equals to DEFAULT_SPECIAL_REWARD_CONTENT_ID
        defaultMGuerillaQuestWorldShouldNotBeFound("specialRewardContentId.lessThan=" + DEFAULT_SPECIAL_REWARD_CONTENT_ID);

        // Get all the mGuerillaQuestWorldList where specialRewardContentId less than or equals to UPDATED_SPECIAL_REWARD_CONTENT_ID
        defaultMGuerillaQuestWorldShouldBeFound("specialRewardContentId.lessThan=" + UPDATED_SPECIAL_REWARD_CONTENT_ID);
    }


    @Test
    @Transactional
    public void getAllMGuerillaQuestWorldsByIsEnableCoopIsEqualToSomething() throws Exception {
        // Initialize the database
        mGuerillaQuestWorldRepository.saveAndFlush(mGuerillaQuestWorld);

        // Get all the mGuerillaQuestWorldList where isEnableCoop equals to DEFAULT_IS_ENABLE_COOP
        defaultMGuerillaQuestWorldShouldBeFound("isEnableCoop.equals=" + DEFAULT_IS_ENABLE_COOP);

        // Get all the mGuerillaQuestWorldList where isEnableCoop equals to UPDATED_IS_ENABLE_COOP
        defaultMGuerillaQuestWorldShouldNotBeFound("isEnableCoop.equals=" + UPDATED_IS_ENABLE_COOP);
    }

    @Test
    @Transactional
    public void getAllMGuerillaQuestWorldsByIsEnableCoopIsInShouldWork() throws Exception {
        // Initialize the database
        mGuerillaQuestWorldRepository.saveAndFlush(mGuerillaQuestWorld);

        // Get all the mGuerillaQuestWorldList where isEnableCoop in DEFAULT_IS_ENABLE_COOP or UPDATED_IS_ENABLE_COOP
        defaultMGuerillaQuestWorldShouldBeFound("isEnableCoop.in=" + DEFAULT_IS_ENABLE_COOP + "," + UPDATED_IS_ENABLE_COOP);

        // Get all the mGuerillaQuestWorldList where isEnableCoop equals to UPDATED_IS_ENABLE_COOP
        defaultMGuerillaQuestWorldShouldNotBeFound("isEnableCoop.in=" + UPDATED_IS_ENABLE_COOP);
    }

    @Test
    @Transactional
    public void getAllMGuerillaQuestWorldsByIsEnableCoopIsNullOrNotNull() throws Exception {
        // Initialize the database
        mGuerillaQuestWorldRepository.saveAndFlush(mGuerillaQuestWorld);

        // Get all the mGuerillaQuestWorldList where isEnableCoop is not null
        defaultMGuerillaQuestWorldShouldBeFound("isEnableCoop.specified=true");

        // Get all the mGuerillaQuestWorldList where isEnableCoop is null
        defaultMGuerillaQuestWorldShouldNotBeFound("isEnableCoop.specified=false");
    }

    @Test
    @Transactional
    public void getAllMGuerillaQuestWorldsByIsEnableCoopIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mGuerillaQuestWorldRepository.saveAndFlush(mGuerillaQuestWorld);

        // Get all the mGuerillaQuestWorldList where isEnableCoop greater than or equals to DEFAULT_IS_ENABLE_COOP
        defaultMGuerillaQuestWorldShouldBeFound("isEnableCoop.greaterOrEqualThan=" + DEFAULT_IS_ENABLE_COOP);

        // Get all the mGuerillaQuestWorldList where isEnableCoop greater than or equals to UPDATED_IS_ENABLE_COOP
        defaultMGuerillaQuestWorldShouldNotBeFound("isEnableCoop.greaterOrEqualThan=" + UPDATED_IS_ENABLE_COOP);
    }

    @Test
    @Transactional
    public void getAllMGuerillaQuestWorldsByIsEnableCoopIsLessThanSomething() throws Exception {
        // Initialize the database
        mGuerillaQuestWorldRepository.saveAndFlush(mGuerillaQuestWorld);

        // Get all the mGuerillaQuestWorldList where isEnableCoop less than or equals to DEFAULT_IS_ENABLE_COOP
        defaultMGuerillaQuestWorldShouldNotBeFound("isEnableCoop.lessThan=" + DEFAULT_IS_ENABLE_COOP);

        // Get all the mGuerillaQuestWorldList where isEnableCoop less than or equals to UPDATED_IS_ENABLE_COOP
        defaultMGuerillaQuestWorldShouldBeFound("isEnableCoop.lessThan=" + UPDATED_IS_ENABLE_COOP);
    }


    @Test
    @Transactional
    public void getAllMGuerillaQuestWorldsByMGuerillaQuestStageIsEqualToSomething() throws Exception {
        // Initialize the database
        MGuerillaQuestStage mGuerillaQuestStage = MGuerillaQuestStageResourceIT.createEntity(em);
        em.persist(mGuerillaQuestStage);
        em.flush();
        mGuerillaQuestWorld.addMGuerillaQuestStage(mGuerillaQuestStage);
        mGuerillaQuestWorldRepository.saveAndFlush(mGuerillaQuestWorld);
        Long mGuerillaQuestStageId = mGuerillaQuestStage.getId();

        // Get all the mGuerillaQuestWorldList where mGuerillaQuestStage equals to mGuerillaQuestStageId
        defaultMGuerillaQuestWorldShouldBeFound("mGuerillaQuestStageId.equals=" + mGuerillaQuestStageId);

        // Get all the mGuerillaQuestWorldList where mGuerillaQuestStage equals to mGuerillaQuestStageId + 1
        defaultMGuerillaQuestWorldShouldNotBeFound("mGuerillaQuestStageId.equals=" + (mGuerillaQuestStageId + 1));
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultMGuerillaQuestWorldShouldBeFound(String filter) throws Exception {
        restMGuerillaQuestWorldMockMvc.perform(get("/api/m-guerilla-quest-worlds?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(mGuerillaQuestWorld.getId().intValue())))
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
        restMGuerillaQuestWorldMockMvc.perform(get("/api/m-guerilla-quest-worlds/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultMGuerillaQuestWorldShouldNotBeFound(String filter) throws Exception {
        restMGuerillaQuestWorldMockMvc.perform(get("/api/m-guerilla-quest-worlds?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restMGuerillaQuestWorldMockMvc.perform(get("/api/m-guerilla-quest-worlds/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("0"));
    }


    @Test
    @Transactional
    public void getNonExistingMGuerillaQuestWorld() throws Exception {
        // Get the mGuerillaQuestWorld
        restMGuerillaQuestWorldMockMvc.perform(get("/api/m-guerilla-quest-worlds/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateMGuerillaQuestWorld() throws Exception {
        // Initialize the database
        mGuerillaQuestWorldRepository.saveAndFlush(mGuerillaQuestWorld);

        int databaseSizeBeforeUpdate = mGuerillaQuestWorldRepository.findAll().size();

        // Update the mGuerillaQuestWorld
        MGuerillaQuestWorld updatedMGuerillaQuestWorld = mGuerillaQuestWorldRepository.findById(mGuerillaQuestWorld.getId()).get();
        // Disconnect from session so that the updates on updatedMGuerillaQuestWorld are not directly saved in db
        em.detach(updatedMGuerillaQuestWorld);
        updatedMGuerillaQuestWorld
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
        MGuerillaQuestWorldDTO mGuerillaQuestWorldDTO = mGuerillaQuestWorldMapper.toDto(updatedMGuerillaQuestWorld);

        restMGuerillaQuestWorldMockMvc.perform(put("/api/m-guerilla-quest-worlds")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mGuerillaQuestWorldDTO)))
            .andExpect(status().isOk());

        // Validate the MGuerillaQuestWorld in the database
        List<MGuerillaQuestWorld> mGuerillaQuestWorldList = mGuerillaQuestWorldRepository.findAll();
        assertThat(mGuerillaQuestWorldList).hasSize(databaseSizeBeforeUpdate);
        MGuerillaQuestWorld testMGuerillaQuestWorld = mGuerillaQuestWorldList.get(mGuerillaQuestWorldList.size() - 1);
        assertThat(testMGuerillaQuestWorld.getSetId()).isEqualTo(UPDATED_SET_ID);
        assertThat(testMGuerillaQuestWorld.getNumber()).isEqualTo(UPDATED_NUMBER);
        assertThat(testMGuerillaQuestWorld.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testMGuerillaQuestWorld.getImagePath()).isEqualTo(UPDATED_IMAGE_PATH);
        assertThat(testMGuerillaQuestWorld.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
        assertThat(testMGuerillaQuestWorld.getStageUnlockPattern()).isEqualTo(UPDATED_STAGE_UNLOCK_PATTERN);
        assertThat(testMGuerillaQuestWorld.getArousalBanner()).isEqualTo(UPDATED_AROUSAL_BANNER);
        assertThat(testMGuerillaQuestWorld.getSpecialRewardContentType()).isEqualTo(UPDATED_SPECIAL_REWARD_CONTENT_TYPE);
        assertThat(testMGuerillaQuestWorld.getSpecialRewardContentId()).isEqualTo(UPDATED_SPECIAL_REWARD_CONTENT_ID);
        assertThat(testMGuerillaQuestWorld.getIsEnableCoop()).isEqualTo(UPDATED_IS_ENABLE_COOP);
    }

    @Test
    @Transactional
    public void updateNonExistingMGuerillaQuestWorld() throws Exception {
        int databaseSizeBeforeUpdate = mGuerillaQuestWorldRepository.findAll().size();

        // Create the MGuerillaQuestWorld
        MGuerillaQuestWorldDTO mGuerillaQuestWorldDTO = mGuerillaQuestWorldMapper.toDto(mGuerillaQuestWorld);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restMGuerillaQuestWorldMockMvc.perform(put("/api/m-guerilla-quest-worlds")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mGuerillaQuestWorldDTO)))
            .andExpect(status().isBadRequest());

        // Validate the MGuerillaQuestWorld in the database
        List<MGuerillaQuestWorld> mGuerillaQuestWorldList = mGuerillaQuestWorldRepository.findAll();
        assertThat(mGuerillaQuestWorldList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteMGuerillaQuestWorld() throws Exception {
        // Initialize the database
        mGuerillaQuestWorldRepository.saveAndFlush(mGuerillaQuestWorld);

        int databaseSizeBeforeDelete = mGuerillaQuestWorldRepository.findAll().size();

        // Delete the mGuerillaQuestWorld
        restMGuerillaQuestWorldMockMvc.perform(delete("/api/m-guerilla-quest-worlds/{id}", mGuerillaQuestWorld.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database is empty
        List<MGuerillaQuestWorld> mGuerillaQuestWorldList = mGuerillaQuestWorldRepository.findAll();
        assertThat(mGuerillaQuestWorldList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(MGuerillaQuestWorld.class);
        MGuerillaQuestWorld mGuerillaQuestWorld1 = new MGuerillaQuestWorld();
        mGuerillaQuestWorld1.setId(1L);
        MGuerillaQuestWorld mGuerillaQuestWorld2 = new MGuerillaQuestWorld();
        mGuerillaQuestWorld2.setId(mGuerillaQuestWorld1.getId());
        assertThat(mGuerillaQuestWorld1).isEqualTo(mGuerillaQuestWorld2);
        mGuerillaQuestWorld2.setId(2L);
        assertThat(mGuerillaQuestWorld1).isNotEqualTo(mGuerillaQuestWorld2);
        mGuerillaQuestWorld1.setId(null);
        assertThat(mGuerillaQuestWorld1).isNotEqualTo(mGuerillaQuestWorld2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(MGuerillaQuestWorldDTO.class);
        MGuerillaQuestWorldDTO mGuerillaQuestWorldDTO1 = new MGuerillaQuestWorldDTO();
        mGuerillaQuestWorldDTO1.setId(1L);
        MGuerillaQuestWorldDTO mGuerillaQuestWorldDTO2 = new MGuerillaQuestWorldDTO();
        assertThat(mGuerillaQuestWorldDTO1).isNotEqualTo(mGuerillaQuestWorldDTO2);
        mGuerillaQuestWorldDTO2.setId(mGuerillaQuestWorldDTO1.getId());
        assertThat(mGuerillaQuestWorldDTO1).isEqualTo(mGuerillaQuestWorldDTO2);
        mGuerillaQuestWorldDTO2.setId(2L);
        assertThat(mGuerillaQuestWorldDTO1).isNotEqualTo(mGuerillaQuestWorldDTO2);
        mGuerillaQuestWorldDTO1.setId(null);
        assertThat(mGuerillaQuestWorldDTO1).isNotEqualTo(mGuerillaQuestWorldDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(mGuerillaQuestWorldMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(mGuerillaQuestWorldMapper.fromId(null)).isNull();
    }
}
