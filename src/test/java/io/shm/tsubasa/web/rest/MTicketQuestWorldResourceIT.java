package io.shm.tsubasa.web.rest;

import io.shm.tsubasa.TsubasaApp;
import io.shm.tsubasa.domain.MTicketQuestWorld;
import io.shm.tsubasa.domain.MTicketQuestStage;
import io.shm.tsubasa.repository.MTicketQuestWorldRepository;
import io.shm.tsubasa.service.MTicketQuestWorldService;
import io.shm.tsubasa.service.dto.MTicketQuestWorldDTO;
import io.shm.tsubasa.service.mapper.MTicketQuestWorldMapper;
import io.shm.tsubasa.web.rest.errors.ExceptionTranslator;
import io.shm.tsubasa.service.dto.MTicketQuestWorldCriteria;
import io.shm.tsubasa.service.MTicketQuestWorldQueryService;

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
 * Integration tests for the {@Link MTicketQuestWorldResource} REST controller.
 */
@SpringBootTest(classes = TsubasaApp.class)
public class MTicketQuestWorldResourceIT {

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

    private static final Integer DEFAULT_SPECIAL_REWARD_CONTENT_TYPE = 1;
    private static final Integer UPDATED_SPECIAL_REWARD_CONTENT_TYPE = 2;

    private static final Integer DEFAULT_SPECIAL_REWARD_CONTENT_ID = 1;
    private static final Integer UPDATED_SPECIAL_REWARD_CONTENT_ID = 2;

    private static final Integer DEFAULT_IS_ENABLE_COOP = 1;
    private static final Integer UPDATED_IS_ENABLE_COOP = 2;

    private static final Integer DEFAULT_IS_HIDE_DO_NOT_HAVING_TICKET = 1;
    private static final Integer UPDATED_IS_HIDE_DO_NOT_HAVING_TICKET = 2;

    @Autowired
    private MTicketQuestWorldRepository mTicketQuestWorldRepository;

    @Autowired
    private MTicketQuestWorldMapper mTicketQuestWorldMapper;

    @Autowired
    private MTicketQuestWorldService mTicketQuestWorldService;

    @Autowired
    private MTicketQuestWorldQueryService mTicketQuestWorldQueryService;

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

    private MockMvc restMTicketQuestWorldMockMvc;

    private MTicketQuestWorld mTicketQuestWorld;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final MTicketQuestWorldResource mTicketQuestWorldResource = new MTicketQuestWorldResource(mTicketQuestWorldService, mTicketQuestWorldQueryService);
        this.restMTicketQuestWorldMockMvc = MockMvcBuilders.standaloneSetup(mTicketQuestWorldResource)
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
    public static MTicketQuestWorld createEntity(EntityManager em) {
        MTicketQuestWorld mTicketQuestWorld = new MTicketQuestWorld()
            .setId(DEFAULT_SET_ID)
            .number(DEFAULT_NUMBER)
            .name(DEFAULT_NAME)
            .imagePath(DEFAULT_IMAGE_PATH)
            .description(DEFAULT_DESCRIPTION)
            .stageUnlockPattern(DEFAULT_STAGE_UNLOCK_PATTERN)
            .specialRewardContentType(DEFAULT_SPECIAL_REWARD_CONTENT_TYPE)
            .specialRewardContentId(DEFAULT_SPECIAL_REWARD_CONTENT_ID)
            .isEnableCoop(DEFAULT_IS_ENABLE_COOP)
            .isHideDoNotHavingTicket(DEFAULT_IS_HIDE_DO_NOT_HAVING_TICKET);
        return mTicketQuestWorld;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static MTicketQuestWorld createUpdatedEntity(EntityManager em) {
        MTicketQuestWorld mTicketQuestWorld = new MTicketQuestWorld()
            .setId(UPDATED_SET_ID)
            .number(UPDATED_NUMBER)
            .name(UPDATED_NAME)
            .imagePath(UPDATED_IMAGE_PATH)
            .description(UPDATED_DESCRIPTION)
            .stageUnlockPattern(UPDATED_STAGE_UNLOCK_PATTERN)
            .specialRewardContentType(UPDATED_SPECIAL_REWARD_CONTENT_TYPE)
            .specialRewardContentId(UPDATED_SPECIAL_REWARD_CONTENT_ID)
            .isEnableCoop(UPDATED_IS_ENABLE_COOP)
            .isHideDoNotHavingTicket(UPDATED_IS_HIDE_DO_NOT_HAVING_TICKET);
        return mTicketQuestWorld;
    }

    @BeforeEach
    public void initTest() {
        mTicketQuestWorld = createEntity(em);
    }

    @Test
    @Transactional
    public void createMTicketQuestWorld() throws Exception {
        int databaseSizeBeforeCreate = mTicketQuestWorldRepository.findAll().size();

        // Create the MTicketQuestWorld
        MTicketQuestWorldDTO mTicketQuestWorldDTO = mTicketQuestWorldMapper.toDto(mTicketQuestWorld);
        restMTicketQuestWorldMockMvc.perform(post("/api/m-ticket-quest-worlds")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mTicketQuestWorldDTO)))
            .andExpect(status().isCreated());

        // Validate the MTicketQuestWorld in the database
        List<MTicketQuestWorld> mTicketQuestWorldList = mTicketQuestWorldRepository.findAll();
        assertThat(mTicketQuestWorldList).hasSize(databaseSizeBeforeCreate + 1);
        MTicketQuestWorld testMTicketQuestWorld = mTicketQuestWorldList.get(mTicketQuestWorldList.size() - 1);
        assertThat(testMTicketQuestWorld.getSetId()).isEqualTo(DEFAULT_SET_ID);
        assertThat(testMTicketQuestWorld.getNumber()).isEqualTo(DEFAULT_NUMBER);
        assertThat(testMTicketQuestWorld.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testMTicketQuestWorld.getImagePath()).isEqualTo(DEFAULT_IMAGE_PATH);
        assertThat(testMTicketQuestWorld.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
        assertThat(testMTicketQuestWorld.getStageUnlockPattern()).isEqualTo(DEFAULT_STAGE_UNLOCK_PATTERN);
        assertThat(testMTicketQuestWorld.getSpecialRewardContentType()).isEqualTo(DEFAULT_SPECIAL_REWARD_CONTENT_TYPE);
        assertThat(testMTicketQuestWorld.getSpecialRewardContentId()).isEqualTo(DEFAULT_SPECIAL_REWARD_CONTENT_ID);
        assertThat(testMTicketQuestWorld.getIsEnableCoop()).isEqualTo(DEFAULT_IS_ENABLE_COOP);
        assertThat(testMTicketQuestWorld.getIsHideDoNotHavingTicket()).isEqualTo(DEFAULT_IS_HIDE_DO_NOT_HAVING_TICKET);
    }

    @Test
    @Transactional
    public void createMTicketQuestWorldWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = mTicketQuestWorldRepository.findAll().size();

        // Create the MTicketQuestWorld with an existing ID
        mTicketQuestWorld.setId(1L);
        MTicketQuestWorldDTO mTicketQuestWorldDTO = mTicketQuestWorldMapper.toDto(mTicketQuestWorld);

        // An entity with an existing ID cannot be created, so this API call must fail
        restMTicketQuestWorldMockMvc.perform(post("/api/m-ticket-quest-worlds")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mTicketQuestWorldDTO)))
            .andExpect(status().isBadRequest());

        // Validate the MTicketQuestWorld in the database
        List<MTicketQuestWorld> mTicketQuestWorldList = mTicketQuestWorldRepository.findAll();
        assertThat(mTicketQuestWorldList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkSetIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = mTicketQuestWorldRepository.findAll().size();
        // set the field null
        mTicketQuestWorld.setSetId(null);

        // Create the MTicketQuestWorld, which fails.
        MTicketQuestWorldDTO mTicketQuestWorldDTO = mTicketQuestWorldMapper.toDto(mTicketQuestWorld);

        restMTicketQuestWorldMockMvc.perform(post("/api/m-ticket-quest-worlds")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mTicketQuestWorldDTO)))
            .andExpect(status().isBadRequest());

        List<MTicketQuestWorld> mTicketQuestWorldList = mTicketQuestWorldRepository.findAll();
        assertThat(mTicketQuestWorldList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkNumberIsRequired() throws Exception {
        int databaseSizeBeforeTest = mTicketQuestWorldRepository.findAll().size();
        // set the field null
        mTicketQuestWorld.setNumber(null);

        // Create the MTicketQuestWorld, which fails.
        MTicketQuestWorldDTO mTicketQuestWorldDTO = mTicketQuestWorldMapper.toDto(mTicketQuestWorld);

        restMTicketQuestWorldMockMvc.perform(post("/api/m-ticket-quest-worlds")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mTicketQuestWorldDTO)))
            .andExpect(status().isBadRequest());

        List<MTicketQuestWorld> mTicketQuestWorldList = mTicketQuestWorldRepository.findAll();
        assertThat(mTicketQuestWorldList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkStageUnlockPatternIsRequired() throws Exception {
        int databaseSizeBeforeTest = mTicketQuestWorldRepository.findAll().size();
        // set the field null
        mTicketQuestWorld.setStageUnlockPattern(null);

        // Create the MTicketQuestWorld, which fails.
        MTicketQuestWorldDTO mTicketQuestWorldDTO = mTicketQuestWorldMapper.toDto(mTicketQuestWorld);

        restMTicketQuestWorldMockMvc.perform(post("/api/m-ticket-quest-worlds")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mTicketQuestWorldDTO)))
            .andExpect(status().isBadRequest());

        List<MTicketQuestWorld> mTicketQuestWorldList = mTicketQuestWorldRepository.findAll();
        assertThat(mTicketQuestWorldList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkIsEnableCoopIsRequired() throws Exception {
        int databaseSizeBeforeTest = mTicketQuestWorldRepository.findAll().size();
        // set the field null
        mTicketQuestWorld.setIsEnableCoop(null);

        // Create the MTicketQuestWorld, which fails.
        MTicketQuestWorldDTO mTicketQuestWorldDTO = mTicketQuestWorldMapper.toDto(mTicketQuestWorld);

        restMTicketQuestWorldMockMvc.perform(post("/api/m-ticket-quest-worlds")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mTicketQuestWorldDTO)))
            .andExpect(status().isBadRequest());

        List<MTicketQuestWorld> mTicketQuestWorldList = mTicketQuestWorldRepository.findAll();
        assertThat(mTicketQuestWorldList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkIsHideDoNotHavingTicketIsRequired() throws Exception {
        int databaseSizeBeforeTest = mTicketQuestWorldRepository.findAll().size();
        // set the field null
        mTicketQuestWorld.setIsHideDoNotHavingTicket(null);

        // Create the MTicketQuestWorld, which fails.
        MTicketQuestWorldDTO mTicketQuestWorldDTO = mTicketQuestWorldMapper.toDto(mTicketQuestWorld);

        restMTicketQuestWorldMockMvc.perform(post("/api/m-ticket-quest-worlds")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mTicketQuestWorldDTO)))
            .andExpect(status().isBadRequest());

        List<MTicketQuestWorld> mTicketQuestWorldList = mTicketQuestWorldRepository.findAll();
        assertThat(mTicketQuestWorldList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllMTicketQuestWorlds() throws Exception {
        // Initialize the database
        mTicketQuestWorldRepository.saveAndFlush(mTicketQuestWorld);

        // Get all the mTicketQuestWorldList
        restMTicketQuestWorldMockMvc.perform(get("/api/m-ticket-quest-worlds?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(mTicketQuestWorld.getId().intValue())))
            .andExpect(jsonPath("$.[*].setId").value(hasItem(DEFAULT_SET_ID)))
            .andExpect(jsonPath("$.[*].number").value(hasItem(DEFAULT_NUMBER)))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME.toString())))
            .andExpect(jsonPath("$.[*].imagePath").value(hasItem(DEFAULT_IMAGE_PATH.toString())))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION.toString())))
            .andExpect(jsonPath("$.[*].stageUnlockPattern").value(hasItem(DEFAULT_STAGE_UNLOCK_PATTERN)))
            .andExpect(jsonPath("$.[*].specialRewardContentType").value(hasItem(DEFAULT_SPECIAL_REWARD_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].specialRewardContentId").value(hasItem(DEFAULT_SPECIAL_REWARD_CONTENT_ID)))
            .andExpect(jsonPath("$.[*].isEnableCoop").value(hasItem(DEFAULT_IS_ENABLE_COOP)))
            .andExpect(jsonPath("$.[*].isHideDoNotHavingTicket").value(hasItem(DEFAULT_IS_HIDE_DO_NOT_HAVING_TICKET)));
    }
    
    @Test
    @Transactional
    public void getMTicketQuestWorld() throws Exception {
        // Initialize the database
        mTicketQuestWorldRepository.saveAndFlush(mTicketQuestWorld);

        // Get the mTicketQuestWorld
        restMTicketQuestWorldMockMvc.perform(get("/api/m-ticket-quest-worlds/{id}", mTicketQuestWorld.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(mTicketQuestWorld.getId().intValue()))
            .andExpect(jsonPath("$.setId").value(DEFAULT_SET_ID))
            .andExpect(jsonPath("$.number").value(DEFAULT_NUMBER))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME.toString()))
            .andExpect(jsonPath("$.imagePath").value(DEFAULT_IMAGE_PATH.toString()))
            .andExpect(jsonPath("$.description").value(DEFAULT_DESCRIPTION.toString()))
            .andExpect(jsonPath("$.stageUnlockPattern").value(DEFAULT_STAGE_UNLOCK_PATTERN))
            .andExpect(jsonPath("$.specialRewardContentType").value(DEFAULT_SPECIAL_REWARD_CONTENT_TYPE))
            .andExpect(jsonPath("$.specialRewardContentId").value(DEFAULT_SPECIAL_REWARD_CONTENT_ID))
            .andExpect(jsonPath("$.isEnableCoop").value(DEFAULT_IS_ENABLE_COOP))
            .andExpect(jsonPath("$.isHideDoNotHavingTicket").value(DEFAULT_IS_HIDE_DO_NOT_HAVING_TICKET));
    }

    @Test
    @Transactional
    public void getAllMTicketQuestWorldsBySetIdIsEqualToSomething() throws Exception {
        // Initialize the database
        mTicketQuestWorldRepository.saveAndFlush(mTicketQuestWorld);

        // Get all the mTicketQuestWorldList where setId equals to DEFAULT_SET_ID
        defaultMTicketQuestWorldShouldBeFound("setId.equals=" + DEFAULT_SET_ID);

        // Get all the mTicketQuestWorldList where setId equals to UPDATED_SET_ID
        defaultMTicketQuestWorldShouldNotBeFound("setId.equals=" + UPDATED_SET_ID);
    }

    @Test
    @Transactional
    public void getAllMTicketQuestWorldsBySetIdIsInShouldWork() throws Exception {
        // Initialize the database
        mTicketQuestWorldRepository.saveAndFlush(mTicketQuestWorld);

        // Get all the mTicketQuestWorldList where setId in DEFAULT_SET_ID or UPDATED_SET_ID
        defaultMTicketQuestWorldShouldBeFound("setId.in=" + DEFAULT_SET_ID + "," + UPDATED_SET_ID);

        // Get all the mTicketQuestWorldList where setId equals to UPDATED_SET_ID
        defaultMTicketQuestWorldShouldNotBeFound("setId.in=" + UPDATED_SET_ID);
    }

    @Test
    @Transactional
    public void getAllMTicketQuestWorldsBySetIdIsNullOrNotNull() throws Exception {
        // Initialize the database
        mTicketQuestWorldRepository.saveAndFlush(mTicketQuestWorld);

        // Get all the mTicketQuestWorldList where setId is not null
        defaultMTicketQuestWorldShouldBeFound("setId.specified=true");

        // Get all the mTicketQuestWorldList where setId is null
        defaultMTicketQuestWorldShouldNotBeFound("setId.specified=false");
    }

    @Test
    @Transactional
    public void getAllMTicketQuestWorldsBySetIdIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mTicketQuestWorldRepository.saveAndFlush(mTicketQuestWorld);

        // Get all the mTicketQuestWorldList where setId greater than or equals to DEFAULT_SET_ID
        defaultMTicketQuestWorldShouldBeFound("setId.greaterOrEqualThan=" + DEFAULT_SET_ID);

        // Get all the mTicketQuestWorldList where setId greater than or equals to UPDATED_SET_ID
        defaultMTicketQuestWorldShouldNotBeFound("setId.greaterOrEqualThan=" + UPDATED_SET_ID);
    }

    @Test
    @Transactional
    public void getAllMTicketQuestWorldsBySetIdIsLessThanSomething() throws Exception {
        // Initialize the database
        mTicketQuestWorldRepository.saveAndFlush(mTicketQuestWorld);

        // Get all the mTicketQuestWorldList where setId less than or equals to DEFAULT_SET_ID
        defaultMTicketQuestWorldShouldNotBeFound("setId.lessThan=" + DEFAULT_SET_ID);

        // Get all the mTicketQuestWorldList where setId less than or equals to UPDATED_SET_ID
        defaultMTicketQuestWorldShouldBeFound("setId.lessThan=" + UPDATED_SET_ID);
    }


    @Test
    @Transactional
    public void getAllMTicketQuestWorldsByNumberIsEqualToSomething() throws Exception {
        // Initialize the database
        mTicketQuestWorldRepository.saveAndFlush(mTicketQuestWorld);

        // Get all the mTicketQuestWorldList where number equals to DEFAULT_NUMBER
        defaultMTicketQuestWorldShouldBeFound("number.equals=" + DEFAULT_NUMBER);

        // Get all the mTicketQuestWorldList where number equals to UPDATED_NUMBER
        defaultMTicketQuestWorldShouldNotBeFound("number.equals=" + UPDATED_NUMBER);
    }

    @Test
    @Transactional
    public void getAllMTicketQuestWorldsByNumberIsInShouldWork() throws Exception {
        // Initialize the database
        mTicketQuestWorldRepository.saveAndFlush(mTicketQuestWorld);

        // Get all the mTicketQuestWorldList where number in DEFAULT_NUMBER or UPDATED_NUMBER
        defaultMTicketQuestWorldShouldBeFound("number.in=" + DEFAULT_NUMBER + "," + UPDATED_NUMBER);

        // Get all the mTicketQuestWorldList where number equals to UPDATED_NUMBER
        defaultMTicketQuestWorldShouldNotBeFound("number.in=" + UPDATED_NUMBER);
    }

    @Test
    @Transactional
    public void getAllMTicketQuestWorldsByNumberIsNullOrNotNull() throws Exception {
        // Initialize the database
        mTicketQuestWorldRepository.saveAndFlush(mTicketQuestWorld);

        // Get all the mTicketQuestWorldList where number is not null
        defaultMTicketQuestWorldShouldBeFound("number.specified=true");

        // Get all the mTicketQuestWorldList where number is null
        defaultMTicketQuestWorldShouldNotBeFound("number.specified=false");
    }

    @Test
    @Transactional
    public void getAllMTicketQuestWorldsByNumberIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mTicketQuestWorldRepository.saveAndFlush(mTicketQuestWorld);

        // Get all the mTicketQuestWorldList where number greater than or equals to DEFAULT_NUMBER
        defaultMTicketQuestWorldShouldBeFound("number.greaterOrEqualThan=" + DEFAULT_NUMBER);

        // Get all the mTicketQuestWorldList where number greater than or equals to UPDATED_NUMBER
        defaultMTicketQuestWorldShouldNotBeFound("number.greaterOrEqualThan=" + UPDATED_NUMBER);
    }

    @Test
    @Transactional
    public void getAllMTicketQuestWorldsByNumberIsLessThanSomething() throws Exception {
        // Initialize the database
        mTicketQuestWorldRepository.saveAndFlush(mTicketQuestWorld);

        // Get all the mTicketQuestWorldList where number less than or equals to DEFAULT_NUMBER
        defaultMTicketQuestWorldShouldNotBeFound("number.lessThan=" + DEFAULT_NUMBER);

        // Get all the mTicketQuestWorldList where number less than or equals to UPDATED_NUMBER
        defaultMTicketQuestWorldShouldBeFound("number.lessThan=" + UPDATED_NUMBER);
    }


    @Test
    @Transactional
    public void getAllMTicketQuestWorldsByStageUnlockPatternIsEqualToSomething() throws Exception {
        // Initialize the database
        mTicketQuestWorldRepository.saveAndFlush(mTicketQuestWorld);

        // Get all the mTicketQuestWorldList where stageUnlockPattern equals to DEFAULT_STAGE_UNLOCK_PATTERN
        defaultMTicketQuestWorldShouldBeFound("stageUnlockPattern.equals=" + DEFAULT_STAGE_UNLOCK_PATTERN);

        // Get all the mTicketQuestWorldList where stageUnlockPattern equals to UPDATED_STAGE_UNLOCK_PATTERN
        defaultMTicketQuestWorldShouldNotBeFound("stageUnlockPattern.equals=" + UPDATED_STAGE_UNLOCK_PATTERN);
    }

    @Test
    @Transactional
    public void getAllMTicketQuestWorldsByStageUnlockPatternIsInShouldWork() throws Exception {
        // Initialize the database
        mTicketQuestWorldRepository.saveAndFlush(mTicketQuestWorld);

        // Get all the mTicketQuestWorldList where stageUnlockPattern in DEFAULT_STAGE_UNLOCK_PATTERN or UPDATED_STAGE_UNLOCK_PATTERN
        defaultMTicketQuestWorldShouldBeFound("stageUnlockPattern.in=" + DEFAULT_STAGE_UNLOCK_PATTERN + "," + UPDATED_STAGE_UNLOCK_PATTERN);

        // Get all the mTicketQuestWorldList where stageUnlockPattern equals to UPDATED_STAGE_UNLOCK_PATTERN
        defaultMTicketQuestWorldShouldNotBeFound("stageUnlockPattern.in=" + UPDATED_STAGE_UNLOCK_PATTERN);
    }

    @Test
    @Transactional
    public void getAllMTicketQuestWorldsByStageUnlockPatternIsNullOrNotNull() throws Exception {
        // Initialize the database
        mTicketQuestWorldRepository.saveAndFlush(mTicketQuestWorld);

        // Get all the mTicketQuestWorldList where stageUnlockPattern is not null
        defaultMTicketQuestWorldShouldBeFound("stageUnlockPattern.specified=true");

        // Get all the mTicketQuestWorldList where stageUnlockPattern is null
        defaultMTicketQuestWorldShouldNotBeFound("stageUnlockPattern.specified=false");
    }

    @Test
    @Transactional
    public void getAllMTicketQuestWorldsByStageUnlockPatternIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mTicketQuestWorldRepository.saveAndFlush(mTicketQuestWorld);

        // Get all the mTicketQuestWorldList where stageUnlockPattern greater than or equals to DEFAULT_STAGE_UNLOCK_PATTERN
        defaultMTicketQuestWorldShouldBeFound("stageUnlockPattern.greaterOrEqualThan=" + DEFAULT_STAGE_UNLOCK_PATTERN);

        // Get all the mTicketQuestWorldList where stageUnlockPattern greater than or equals to UPDATED_STAGE_UNLOCK_PATTERN
        defaultMTicketQuestWorldShouldNotBeFound("stageUnlockPattern.greaterOrEqualThan=" + UPDATED_STAGE_UNLOCK_PATTERN);
    }

    @Test
    @Transactional
    public void getAllMTicketQuestWorldsByStageUnlockPatternIsLessThanSomething() throws Exception {
        // Initialize the database
        mTicketQuestWorldRepository.saveAndFlush(mTicketQuestWorld);

        // Get all the mTicketQuestWorldList where stageUnlockPattern less than or equals to DEFAULT_STAGE_UNLOCK_PATTERN
        defaultMTicketQuestWorldShouldNotBeFound("stageUnlockPattern.lessThan=" + DEFAULT_STAGE_UNLOCK_PATTERN);

        // Get all the mTicketQuestWorldList where stageUnlockPattern less than or equals to UPDATED_STAGE_UNLOCK_PATTERN
        defaultMTicketQuestWorldShouldBeFound("stageUnlockPattern.lessThan=" + UPDATED_STAGE_UNLOCK_PATTERN);
    }


    @Test
    @Transactional
    public void getAllMTicketQuestWorldsBySpecialRewardContentTypeIsEqualToSomething() throws Exception {
        // Initialize the database
        mTicketQuestWorldRepository.saveAndFlush(mTicketQuestWorld);

        // Get all the mTicketQuestWorldList where specialRewardContentType equals to DEFAULT_SPECIAL_REWARD_CONTENT_TYPE
        defaultMTicketQuestWorldShouldBeFound("specialRewardContentType.equals=" + DEFAULT_SPECIAL_REWARD_CONTENT_TYPE);

        // Get all the mTicketQuestWorldList where specialRewardContentType equals to UPDATED_SPECIAL_REWARD_CONTENT_TYPE
        defaultMTicketQuestWorldShouldNotBeFound("specialRewardContentType.equals=" + UPDATED_SPECIAL_REWARD_CONTENT_TYPE);
    }

    @Test
    @Transactional
    public void getAllMTicketQuestWorldsBySpecialRewardContentTypeIsInShouldWork() throws Exception {
        // Initialize the database
        mTicketQuestWorldRepository.saveAndFlush(mTicketQuestWorld);

        // Get all the mTicketQuestWorldList where specialRewardContentType in DEFAULT_SPECIAL_REWARD_CONTENT_TYPE or UPDATED_SPECIAL_REWARD_CONTENT_TYPE
        defaultMTicketQuestWorldShouldBeFound("specialRewardContentType.in=" + DEFAULT_SPECIAL_REWARD_CONTENT_TYPE + "," + UPDATED_SPECIAL_REWARD_CONTENT_TYPE);

        // Get all the mTicketQuestWorldList where specialRewardContentType equals to UPDATED_SPECIAL_REWARD_CONTENT_TYPE
        defaultMTicketQuestWorldShouldNotBeFound("specialRewardContentType.in=" + UPDATED_SPECIAL_REWARD_CONTENT_TYPE);
    }

    @Test
    @Transactional
    public void getAllMTicketQuestWorldsBySpecialRewardContentTypeIsNullOrNotNull() throws Exception {
        // Initialize the database
        mTicketQuestWorldRepository.saveAndFlush(mTicketQuestWorld);

        // Get all the mTicketQuestWorldList where specialRewardContentType is not null
        defaultMTicketQuestWorldShouldBeFound("specialRewardContentType.specified=true");

        // Get all the mTicketQuestWorldList where specialRewardContentType is null
        defaultMTicketQuestWorldShouldNotBeFound("specialRewardContentType.specified=false");
    }

    @Test
    @Transactional
    public void getAllMTicketQuestWorldsBySpecialRewardContentTypeIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mTicketQuestWorldRepository.saveAndFlush(mTicketQuestWorld);

        // Get all the mTicketQuestWorldList where specialRewardContentType greater than or equals to DEFAULT_SPECIAL_REWARD_CONTENT_TYPE
        defaultMTicketQuestWorldShouldBeFound("specialRewardContentType.greaterOrEqualThan=" + DEFAULT_SPECIAL_REWARD_CONTENT_TYPE);

        // Get all the mTicketQuestWorldList where specialRewardContentType greater than or equals to UPDATED_SPECIAL_REWARD_CONTENT_TYPE
        defaultMTicketQuestWorldShouldNotBeFound("specialRewardContentType.greaterOrEqualThan=" + UPDATED_SPECIAL_REWARD_CONTENT_TYPE);
    }

    @Test
    @Transactional
    public void getAllMTicketQuestWorldsBySpecialRewardContentTypeIsLessThanSomething() throws Exception {
        // Initialize the database
        mTicketQuestWorldRepository.saveAndFlush(mTicketQuestWorld);

        // Get all the mTicketQuestWorldList where specialRewardContentType less than or equals to DEFAULT_SPECIAL_REWARD_CONTENT_TYPE
        defaultMTicketQuestWorldShouldNotBeFound("specialRewardContentType.lessThan=" + DEFAULT_SPECIAL_REWARD_CONTENT_TYPE);

        // Get all the mTicketQuestWorldList where specialRewardContentType less than or equals to UPDATED_SPECIAL_REWARD_CONTENT_TYPE
        defaultMTicketQuestWorldShouldBeFound("specialRewardContentType.lessThan=" + UPDATED_SPECIAL_REWARD_CONTENT_TYPE);
    }


    @Test
    @Transactional
    public void getAllMTicketQuestWorldsBySpecialRewardContentIdIsEqualToSomething() throws Exception {
        // Initialize the database
        mTicketQuestWorldRepository.saveAndFlush(mTicketQuestWorld);

        // Get all the mTicketQuestWorldList where specialRewardContentId equals to DEFAULT_SPECIAL_REWARD_CONTENT_ID
        defaultMTicketQuestWorldShouldBeFound("specialRewardContentId.equals=" + DEFAULT_SPECIAL_REWARD_CONTENT_ID);

        // Get all the mTicketQuestWorldList where specialRewardContentId equals to UPDATED_SPECIAL_REWARD_CONTENT_ID
        defaultMTicketQuestWorldShouldNotBeFound("specialRewardContentId.equals=" + UPDATED_SPECIAL_REWARD_CONTENT_ID);
    }

    @Test
    @Transactional
    public void getAllMTicketQuestWorldsBySpecialRewardContentIdIsInShouldWork() throws Exception {
        // Initialize the database
        mTicketQuestWorldRepository.saveAndFlush(mTicketQuestWorld);

        // Get all the mTicketQuestWorldList where specialRewardContentId in DEFAULT_SPECIAL_REWARD_CONTENT_ID or UPDATED_SPECIAL_REWARD_CONTENT_ID
        defaultMTicketQuestWorldShouldBeFound("specialRewardContentId.in=" + DEFAULT_SPECIAL_REWARD_CONTENT_ID + "," + UPDATED_SPECIAL_REWARD_CONTENT_ID);

        // Get all the mTicketQuestWorldList where specialRewardContentId equals to UPDATED_SPECIAL_REWARD_CONTENT_ID
        defaultMTicketQuestWorldShouldNotBeFound("specialRewardContentId.in=" + UPDATED_SPECIAL_REWARD_CONTENT_ID);
    }

    @Test
    @Transactional
    public void getAllMTicketQuestWorldsBySpecialRewardContentIdIsNullOrNotNull() throws Exception {
        // Initialize the database
        mTicketQuestWorldRepository.saveAndFlush(mTicketQuestWorld);

        // Get all the mTicketQuestWorldList where specialRewardContentId is not null
        defaultMTicketQuestWorldShouldBeFound("specialRewardContentId.specified=true");

        // Get all the mTicketQuestWorldList where specialRewardContentId is null
        defaultMTicketQuestWorldShouldNotBeFound("specialRewardContentId.specified=false");
    }

    @Test
    @Transactional
    public void getAllMTicketQuestWorldsBySpecialRewardContentIdIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mTicketQuestWorldRepository.saveAndFlush(mTicketQuestWorld);

        // Get all the mTicketQuestWorldList where specialRewardContentId greater than or equals to DEFAULT_SPECIAL_REWARD_CONTENT_ID
        defaultMTicketQuestWorldShouldBeFound("specialRewardContentId.greaterOrEqualThan=" + DEFAULT_SPECIAL_REWARD_CONTENT_ID);

        // Get all the mTicketQuestWorldList where specialRewardContentId greater than or equals to UPDATED_SPECIAL_REWARD_CONTENT_ID
        defaultMTicketQuestWorldShouldNotBeFound("specialRewardContentId.greaterOrEqualThan=" + UPDATED_SPECIAL_REWARD_CONTENT_ID);
    }

    @Test
    @Transactional
    public void getAllMTicketQuestWorldsBySpecialRewardContentIdIsLessThanSomething() throws Exception {
        // Initialize the database
        mTicketQuestWorldRepository.saveAndFlush(mTicketQuestWorld);

        // Get all the mTicketQuestWorldList where specialRewardContentId less than or equals to DEFAULT_SPECIAL_REWARD_CONTENT_ID
        defaultMTicketQuestWorldShouldNotBeFound("specialRewardContentId.lessThan=" + DEFAULT_SPECIAL_REWARD_CONTENT_ID);

        // Get all the mTicketQuestWorldList where specialRewardContentId less than or equals to UPDATED_SPECIAL_REWARD_CONTENT_ID
        defaultMTicketQuestWorldShouldBeFound("specialRewardContentId.lessThan=" + UPDATED_SPECIAL_REWARD_CONTENT_ID);
    }


    @Test
    @Transactional
    public void getAllMTicketQuestWorldsByIsEnableCoopIsEqualToSomething() throws Exception {
        // Initialize the database
        mTicketQuestWorldRepository.saveAndFlush(mTicketQuestWorld);

        // Get all the mTicketQuestWorldList where isEnableCoop equals to DEFAULT_IS_ENABLE_COOP
        defaultMTicketQuestWorldShouldBeFound("isEnableCoop.equals=" + DEFAULT_IS_ENABLE_COOP);

        // Get all the mTicketQuestWorldList where isEnableCoop equals to UPDATED_IS_ENABLE_COOP
        defaultMTicketQuestWorldShouldNotBeFound("isEnableCoop.equals=" + UPDATED_IS_ENABLE_COOP);
    }

    @Test
    @Transactional
    public void getAllMTicketQuestWorldsByIsEnableCoopIsInShouldWork() throws Exception {
        // Initialize the database
        mTicketQuestWorldRepository.saveAndFlush(mTicketQuestWorld);

        // Get all the mTicketQuestWorldList where isEnableCoop in DEFAULT_IS_ENABLE_COOP or UPDATED_IS_ENABLE_COOP
        defaultMTicketQuestWorldShouldBeFound("isEnableCoop.in=" + DEFAULT_IS_ENABLE_COOP + "," + UPDATED_IS_ENABLE_COOP);

        // Get all the mTicketQuestWorldList where isEnableCoop equals to UPDATED_IS_ENABLE_COOP
        defaultMTicketQuestWorldShouldNotBeFound("isEnableCoop.in=" + UPDATED_IS_ENABLE_COOP);
    }

    @Test
    @Transactional
    public void getAllMTicketQuestWorldsByIsEnableCoopIsNullOrNotNull() throws Exception {
        // Initialize the database
        mTicketQuestWorldRepository.saveAndFlush(mTicketQuestWorld);

        // Get all the mTicketQuestWorldList where isEnableCoop is not null
        defaultMTicketQuestWorldShouldBeFound("isEnableCoop.specified=true");

        // Get all the mTicketQuestWorldList where isEnableCoop is null
        defaultMTicketQuestWorldShouldNotBeFound("isEnableCoop.specified=false");
    }

    @Test
    @Transactional
    public void getAllMTicketQuestWorldsByIsEnableCoopIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mTicketQuestWorldRepository.saveAndFlush(mTicketQuestWorld);

        // Get all the mTicketQuestWorldList where isEnableCoop greater than or equals to DEFAULT_IS_ENABLE_COOP
        defaultMTicketQuestWorldShouldBeFound("isEnableCoop.greaterOrEqualThan=" + DEFAULT_IS_ENABLE_COOP);

        // Get all the mTicketQuestWorldList where isEnableCoop greater than or equals to UPDATED_IS_ENABLE_COOP
        defaultMTicketQuestWorldShouldNotBeFound("isEnableCoop.greaterOrEqualThan=" + UPDATED_IS_ENABLE_COOP);
    }

    @Test
    @Transactional
    public void getAllMTicketQuestWorldsByIsEnableCoopIsLessThanSomething() throws Exception {
        // Initialize the database
        mTicketQuestWorldRepository.saveAndFlush(mTicketQuestWorld);

        // Get all the mTicketQuestWorldList where isEnableCoop less than or equals to DEFAULT_IS_ENABLE_COOP
        defaultMTicketQuestWorldShouldNotBeFound("isEnableCoop.lessThan=" + DEFAULT_IS_ENABLE_COOP);

        // Get all the mTicketQuestWorldList where isEnableCoop less than or equals to UPDATED_IS_ENABLE_COOP
        defaultMTicketQuestWorldShouldBeFound("isEnableCoop.lessThan=" + UPDATED_IS_ENABLE_COOP);
    }


    @Test
    @Transactional
    public void getAllMTicketQuestWorldsByIsHideDoNotHavingTicketIsEqualToSomething() throws Exception {
        // Initialize the database
        mTicketQuestWorldRepository.saveAndFlush(mTicketQuestWorld);

        // Get all the mTicketQuestWorldList where isHideDoNotHavingTicket equals to DEFAULT_IS_HIDE_DO_NOT_HAVING_TICKET
        defaultMTicketQuestWorldShouldBeFound("isHideDoNotHavingTicket.equals=" + DEFAULT_IS_HIDE_DO_NOT_HAVING_TICKET);

        // Get all the mTicketQuestWorldList where isHideDoNotHavingTicket equals to UPDATED_IS_HIDE_DO_NOT_HAVING_TICKET
        defaultMTicketQuestWorldShouldNotBeFound("isHideDoNotHavingTicket.equals=" + UPDATED_IS_HIDE_DO_NOT_HAVING_TICKET);
    }

    @Test
    @Transactional
    public void getAllMTicketQuestWorldsByIsHideDoNotHavingTicketIsInShouldWork() throws Exception {
        // Initialize the database
        mTicketQuestWorldRepository.saveAndFlush(mTicketQuestWorld);

        // Get all the mTicketQuestWorldList where isHideDoNotHavingTicket in DEFAULT_IS_HIDE_DO_NOT_HAVING_TICKET or UPDATED_IS_HIDE_DO_NOT_HAVING_TICKET
        defaultMTicketQuestWorldShouldBeFound("isHideDoNotHavingTicket.in=" + DEFAULT_IS_HIDE_DO_NOT_HAVING_TICKET + "," + UPDATED_IS_HIDE_DO_NOT_HAVING_TICKET);

        // Get all the mTicketQuestWorldList where isHideDoNotHavingTicket equals to UPDATED_IS_HIDE_DO_NOT_HAVING_TICKET
        defaultMTicketQuestWorldShouldNotBeFound("isHideDoNotHavingTicket.in=" + UPDATED_IS_HIDE_DO_NOT_HAVING_TICKET);
    }

    @Test
    @Transactional
    public void getAllMTicketQuestWorldsByIsHideDoNotHavingTicketIsNullOrNotNull() throws Exception {
        // Initialize the database
        mTicketQuestWorldRepository.saveAndFlush(mTicketQuestWorld);

        // Get all the mTicketQuestWorldList where isHideDoNotHavingTicket is not null
        defaultMTicketQuestWorldShouldBeFound("isHideDoNotHavingTicket.specified=true");

        // Get all the mTicketQuestWorldList where isHideDoNotHavingTicket is null
        defaultMTicketQuestWorldShouldNotBeFound("isHideDoNotHavingTicket.specified=false");
    }

    @Test
    @Transactional
    public void getAllMTicketQuestWorldsByIsHideDoNotHavingTicketIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mTicketQuestWorldRepository.saveAndFlush(mTicketQuestWorld);

        // Get all the mTicketQuestWorldList where isHideDoNotHavingTicket greater than or equals to DEFAULT_IS_HIDE_DO_NOT_HAVING_TICKET
        defaultMTicketQuestWorldShouldBeFound("isHideDoNotHavingTicket.greaterOrEqualThan=" + DEFAULT_IS_HIDE_DO_NOT_HAVING_TICKET);

        // Get all the mTicketQuestWorldList where isHideDoNotHavingTicket greater than or equals to UPDATED_IS_HIDE_DO_NOT_HAVING_TICKET
        defaultMTicketQuestWorldShouldNotBeFound("isHideDoNotHavingTicket.greaterOrEqualThan=" + UPDATED_IS_HIDE_DO_NOT_HAVING_TICKET);
    }

    @Test
    @Transactional
    public void getAllMTicketQuestWorldsByIsHideDoNotHavingTicketIsLessThanSomething() throws Exception {
        // Initialize the database
        mTicketQuestWorldRepository.saveAndFlush(mTicketQuestWorld);

        // Get all the mTicketQuestWorldList where isHideDoNotHavingTicket less than or equals to DEFAULT_IS_HIDE_DO_NOT_HAVING_TICKET
        defaultMTicketQuestWorldShouldNotBeFound("isHideDoNotHavingTicket.lessThan=" + DEFAULT_IS_HIDE_DO_NOT_HAVING_TICKET);

        // Get all the mTicketQuestWorldList where isHideDoNotHavingTicket less than or equals to UPDATED_IS_HIDE_DO_NOT_HAVING_TICKET
        defaultMTicketQuestWorldShouldBeFound("isHideDoNotHavingTicket.lessThan=" + UPDATED_IS_HIDE_DO_NOT_HAVING_TICKET);
    }


    @Test
    @Transactional
    public void getAllMTicketQuestWorldsByMTicketQuestStageIsEqualToSomething() throws Exception {
        // Initialize the database
        MTicketQuestStage mTicketQuestStage = MTicketQuestStageResourceIT.createEntity(em);
        em.persist(mTicketQuestStage);
        em.flush();
        mTicketQuestWorld.addMTicketQuestStage(mTicketQuestStage);
        mTicketQuestWorldRepository.saveAndFlush(mTicketQuestWorld);
        Long mTicketQuestStageId = mTicketQuestStage.getId();

        // Get all the mTicketQuestWorldList where mTicketQuestStage equals to mTicketQuestStageId
        defaultMTicketQuestWorldShouldBeFound("mTicketQuestStageId.equals=" + mTicketQuestStageId);

        // Get all the mTicketQuestWorldList where mTicketQuestStage equals to mTicketQuestStageId + 1
        defaultMTicketQuestWorldShouldNotBeFound("mTicketQuestStageId.equals=" + (mTicketQuestStageId + 1));
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultMTicketQuestWorldShouldBeFound(String filter) throws Exception {
        restMTicketQuestWorldMockMvc.perform(get("/api/m-ticket-quest-worlds?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(mTicketQuestWorld.getId().intValue())))
            .andExpect(jsonPath("$.[*].setId").value(hasItem(DEFAULT_SET_ID)))
            .andExpect(jsonPath("$.[*].number").value(hasItem(DEFAULT_NUMBER)))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME.toString())))
            .andExpect(jsonPath("$.[*].imagePath").value(hasItem(DEFAULT_IMAGE_PATH.toString())))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION.toString())))
            .andExpect(jsonPath("$.[*].stageUnlockPattern").value(hasItem(DEFAULT_STAGE_UNLOCK_PATTERN)))
            .andExpect(jsonPath("$.[*].specialRewardContentType").value(hasItem(DEFAULT_SPECIAL_REWARD_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].specialRewardContentId").value(hasItem(DEFAULT_SPECIAL_REWARD_CONTENT_ID)))
            .andExpect(jsonPath("$.[*].isEnableCoop").value(hasItem(DEFAULT_IS_ENABLE_COOP)))
            .andExpect(jsonPath("$.[*].isHideDoNotHavingTicket").value(hasItem(DEFAULT_IS_HIDE_DO_NOT_HAVING_TICKET)));

        // Check, that the count call also returns 1
        restMTicketQuestWorldMockMvc.perform(get("/api/m-ticket-quest-worlds/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultMTicketQuestWorldShouldNotBeFound(String filter) throws Exception {
        restMTicketQuestWorldMockMvc.perform(get("/api/m-ticket-quest-worlds?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restMTicketQuestWorldMockMvc.perform(get("/api/m-ticket-quest-worlds/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("0"));
    }


    @Test
    @Transactional
    public void getNonExistingMTicketQuestWorld() throws Exception {
        // Get the mTicketQuestWorld
        restMTicketQuestWorldMockMvc.perform(get("/api/m-ticket-quest-worlds/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateMTicketQuestWorld() throws Exception {
        // Initialize the database
        mTicketQuestWorldRepository.saveAndFlush(mTicketQuestWorld);

        int databaseSizeBeforeUpdate = mTicketQuestWorldRepository.findAll().size();

        // Update the mTicketQuestWorld
        MTicketQuestWorld updatedMTicketQuestWorld = mTicketQuestWorldRepository.findById(mTicketQuestWorld.getId()).get();
        // Disconnect from session so that the updates on updatedMTicketQuestWorld are not directly saved in db
        em.detach(updatedMTicketQuestWorld);
        updatedMTicketQuestWorld
            .setId(UPDATED_SET_ID)
            .number(UPDATED_NUMBER)
            .name(UPDATED_NAME)
            .imagePath(UPDATED_IMAGE_PATH)
            .description(UPDATED_DESCRIPTION)
            .stageUnlockPattern(UPDATED_STAGE_UNLOCK_PATTERN)
            .specialRewardContentType(UPDATED_SPECIAL_REWARD_CONTENT_TYPE)
            .specialRewardContentId(UPDATED_SPECIAL_REWARD_CONTENT_ID)
            .isEnableCoop(UPDATED_IS_ENABLE_COOP)
            .isHideDoNotHavingTicket(UPDATED_IS_HIDE_DO_NOT_HAVING_TICKET);
        MTicketQuestWorldDTO mTicketQuestWorldDTO = mTicketQuestWorldMapper.toDto(updatedMTicketQuestWorld);

        restMTicketQuestWorldMockMvc.perform(put("/api/m-ticket-quest-worlds")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mTicketQuestWorldDTO)))
            .andExpect(status().isOk());

        // Validate the MTicketQuestWorld in the database
        List<MTicketQuestWorld> mTicketQuestWorldList = mTicketQuestWorldRepository.findAll();
        assertThat(mTicketQuestWorldList).hasSize(databaseSizeBeforeUpdate);
        MTicketQuestWorld testMTicketQuestWorld = mTicketQuestWorldList.get(mTicketQuestWorldList.size() - 1);
        assertThat(testMTicketQuestWorld.getSetId()).isEqualTo(UPDATED_SET_ID);
        assertThat(testMTicketQuestWorld.getNumber()).isEqualTo(UPDATED_NUMBER);
        assertThat(testMTicketQuestWorld.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testMTicketQuestWorld.getImagePath()).isEqualTo(UPDATED_IMAGE_PATH);
        assertThat(testMTicketQuestWorld.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
        assertThat(testMTicketQuestWorld.getStageUnlockPattern()).isEqualTo(UPDATED_STAGE_UNLOCK_PATTERN);
        assertThat(testMTicketQuestWorld.getSpecialRewardContentType()).isEqualTo(UPDATED_SPECIAL_REWARD_CONTENT_TYPE);
        assertThat(testMTicketQuestWorld.getSpecialRewardContentId()).isEqualTo(UPDATED_SPECIAL_REWARD_CONTENT_ID);
        assertThat(testMTicketQuestWorld.getIsEnableCoop()).isEqualTo(UPDATED_IS_ENABLE_COOP);
        assertThat(testMTicketQuestWorld.getIsHideDoNotHavingTicket()).isEqualTo(UPDATED_IS_HIDE_DO_NOT_HAVING_TICKET);
    }

    @Test
    @Transactional
    public void updateNonExistingMTicketQuestWorld() throws Exception {
        int databaseSizeBeforeUpdate = mTicketQuestWorldRepository.findAll().size();

        // Create the MTicketQuestWorld
        MTicketQuestWorldDTO mTicketQuestWorldDTO = mTicketQuestWorldMapper.toDto(mTicketQuestWorld);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restMTicketQuestWorldMockMvc.perform(put("/api/m-ticket-quest-worlds")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mTicketQuestWorldDTO)))
            .andExpect(status().isBadRequest());

        // Validate the MTicketQuestWorld in the database
        List<MTicketQuestWorld> mTicketQuestWorldList = mTicketQuestWorldRepository.findAll();
        assertThat(mTicketQuestWorldList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteMTicketQuestWorld() throws Exception {
        // Initialize the database
        mTicketQuestWorldRepository.saveAndFlush(mTicketQuestWorld);

        int databaseSizeBeforeDelete = mTicketQuestWorldRepository.findAll().size();

        // Delete the mTicketQuestWorld
        restMTicketQuestWorldMockMvc.perform(delete("/api/m-ticket-quest-worlds/{id}", mTicketQuestWorld.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database is empty
        List<MTicketQuestWorld> mTicketQuestWorldList = mTicketQuestWorldRepository.findAll();
        assertThat(mTicketQuestWorldList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(MTicketQuestWorld.class);
        MTicketQuestWorld mTicketQuestWorld1 = new MTicketQuestWorld();
        mTicketQuestWorld1.setId(1L);
        MTicketQuestWorld mTicketQuestWorld2 = new MTicketQuestWorld();
        mTicketQuestWorld2.setId(mTicketQuestWorld1.getId());
        assertThat(mTicketQuestWorld1).isEqualTo(mTicketQuestWorld2);
        mTicketQuestWorld2.setId(2L);
        assertThat(mTicketQuestWorld1).isNotEqualTo(mTicketQuestWorld2);
        mTicketQuestWorld1.setId(null);
        assertThat(mTicketQuestWorld1).isNotEqualTo(mTicketQuestWorld2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(MTicketQuestWorldDTO.class);
        MTicketQuestWorldDTO mTicketQuestWorldDTO1 = new MTicketQuestWorldDTO();
        mTicketQuestWorldDTO1.setId(1L);
        MTicketQuestWorldDTO mTicketQuestWorldDTO2 = new MTicketQuestWorldDTO();
        assertThat(mTicketQuestWorldDTO1).isNotEqualTo(mTicketQuestWorldDTO2);
        mTicketQuestWorldDTO2.setId(mTicketQuestWorldDTO1.getId());
        assertThat(mTicketQuestWorldDTO1).isEqualTo(mTicketQuestWorldDTO2);
        mTicketQuestWorldDTO2.setId(2L);
        assertThat(mTicketQuestWorldDTO1).isNotEqualTo(mTicketQuestWorldDTO2);
        mTicketQuestWorldDTO1.setId(null);
        assertThat(mTicketQuestWorldDTO1).isNotEqualTo(mTicketQuestWorldDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(mTicketQuestWorldMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(mTicketQuestWorldMapper.fromId(null)).isNull();
    }
}
