package io.shm.tsubasa.web.rest;

import io.shm.tsubasa.TsubasaApp;
import io.shm.tsubasa.domain.MTriggerEffectRange;
import io.shm.tsubasa.domain.MTriggerEffectBase;
import io.shm.tsubasa.repository.MTriggerEffectRangeRepository;
import io.shm.tsubasa.service.MTriggerEffectRangeService;
import io.shm.tsubasa.service.dto.MTriggerEffectRangeDTO;
import io.shm.tsubasa.service.mapper.MTriggerEffectRangeMapper;
import io.shm.tsubasa.web.rest.errors.ExceptionTranslator;
import io.shm.tsubasa.service.dto.MTriggerEffectRangeCriteria;
import io.shm.tsubasa.service.MTriggerEffectRangeQueryService;

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
 * Integration tests for the {@Link MTriggerEffectRangeResource} REST controller.
 */
@SpringBootTest(classes = TsubasaApp.class)
public class MTriggerEffectRangeResourceIT {

    private static final Integer DEFAULT_EFFECT_TYPE = 1;
    private static final Integer UPDATED_EFFECT_TYPE = 2;

    private static final Integer DEFAULT_TARGET_POSITION_GK = 1;
    private static final Integer UPDATED_TARGET_POSITION_GK = 2;

    private static final Integer DEFAULT_TARGET_POSITION_FW = 1;
    private static final Integer UPDATED_TARGET_POSITION_FW = 2;

    private static final Integer DEFAULT_TARGET_POSITION_OMF = 1;
    private static final Integer UPDATED_TARGET_POSITION_OMF = 2;

    private static final Integer DEFAULT_TARGET_POSITION_DMF = 1;
    private static final Integer UPDATED_TARGET_POSITION_DMF = 2;

    private static final Integer DEFAULT_TARGET_POSITION_DF = 1;
    private static final Integer UPDATED_TARGET_POSITION_DF = 2;

    private static final Integer DEFAULT_TARGET_ATTRIBUTE = 1;
    private static final Integer UPDATED_TARGET_ATTRIBUTE = 2;

    private static final Integer DEFAULT_TARGET_NATIONALITY_GROUP_ID = 1;
    private static final Integer UPDATED_TARGET_NATIONALITY_GROUP_ID = 2;

    private static final Integer DEFAULT_TARGET_TEAM_GROUP_ID = 1;
    private static final Integer UPDATED_TARGET_TEAM_GROUP_ID = 2;

    private static final Integer DEFAULT_TARGET_CHARACTER_GROUP_ID = 1;
    private static final Integer UPDATED_TARGET_CHARACTER_GROUP_ID = 2;

    private static final Integer DEFAULT_TARGET_FORMATION_GROUP_ID = 1;
    private static final Integer UPDATED_TARGET_FORMATION_GROUP_ID = 2;

    private static final Integer DEFAULT_TARGET_ACTION_COMMAND = 1;
    private static final Integer UPDATED_TARGET_ACTION_COMMAND = 2;

    @Autowired
    private MTriggerEffectRangeRepository mTriggerEffectRangeRepository;

    @Autowired
    private MTriggerEffectRangeMapper mTriggerEffectRangeMapper;

    @Autowired
    private MTriggerEffectRangeService mTriggerEffectRangeService;

    @Autowired
    private MTriggerEffectRangeQueryService mTriggerEffectRangeQueryService;

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

    private MockMvc restMTriggerEffectRangeMockMvc;

    private MTriggerEffectRange mTriggerEffectRange;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final MTriggerEffectRangeResource mTriggerEffectRangeResource = new MTriggerEffectRangeResource(mTriggerEffectRangeService, mTriggerEffectRangeQueryService);
        this.restMTriggerEffectRangeMockMvc = MockMvcBuilders.standaloneSetup(mTriggerEffectRangeResource)
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
    public static MTriggerEffectRange createEntity(EntityManager em) {
        MTriggerEffectRange mTriggerEffectRange = new MTriggerEffectRange()
            .effectType(DEFAULT_EFFECT_TYPE)
            .targetPositionGk(DEFAULT_TARGET_POSITION_GK)
            .targetPositionFw(DEFAULT_TARGET_POSITION_FW)
            .targetPositionOmf(DEFAULT_TARGET_POSITION_OMF)
            .targetPositionDmf(DEFAULT_TARGET_POSITION_DMF)
            .targetPositionDf(DEFAULT_TARGET_POSITION_DF)
            .targetAttribute(DEFAULT_TARGET_ATTRIBUTE)
            .targetNationalityGroupId(DEFAULT_TARGET_NATIONALITY_GROUP_ID)
            .targetTeamGroupId(DEFAULT_TARGET_TEAM_GROUP_ID)
            .targetCharacterGroupId(DEFAULT_TARGET_CHARACTER_GROUP_ID)
            .targetFormationGroupId(DEFAULT_TARGET_FORMATION_GROUP_ID)
            .targetActionCommand(DEFAULT_TARGET_ACTION_COMMAND);
        return mTriggerEffectRange;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static MTriggerEffectRange createUpdatedEntity(EntityManager em) {
        MTriggerEffectRange mTriggerEffectRange = new MTriggerEffectRange()
            .effectType(UPDATED_EFFECT_TYPE)
            .targetPositionGk(UPDATED_TARGET_POSITION_GK)
            .targetPositionFw(UPDATED_TARGET_POSITION_FW)
            .targetPositionOmf(UPDATED_TARGET_POSITION_OMF)
            .targetPositionDmf(UPDATED_TARGET_POSITION_DMF)
            .targetPositionDf(UPDATED_TARGET_POSITION_DF)
            .targetAttribute(UPDATED_TARGET_ATTRIBUTE)
            .targetNationalityGroupId(UPDATED_TARGET_NATIONALITY_GROUP_ID)
            .targetTeamGroupId(UPDATED_TARGET_TEAM_GROUP_ID)
            .targetCharacterGroupId(UPDATED_TARGET_CHARACTER_GROUP_ID)
            .targetFormationGroupId(UPDATED_TARGET_FORMATION_GROUP_ID)
            .targetActionCommand(UPDATED_TARGET_ACTION_COMMAND);
        return mTriggerEffectRange;
    }

    @BeforeEach
    public void initTest() {
        mTriggerEffectRange = createEntity(em);
    }

    @Test
    @Transactional
    public void createMTriggerEffectRange() throws Exception {
        int databaseSizeBeforeCreate = mTriggerEffectRangeRepository.findAll().size();

        // Create the MTriggerEffectRange
        MTriggerEffectRangeDTO mTriggerEffectRangeDTO = mTriggerEffectRangeMapper.toDto(mTriggerEffectRange);
        restMTriggerEffectRangeMockMvc.perform(post("/api/m-trigger-effect-ranges")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mTriggerEffectRangeDTO)))
            .andExpect(status().isCreated());

        // Validate the MTriggerEffectRange in the database
        List<MTriggerEffectRange> mTriggerEffectRangeList = mTriggerEffectRangeRepository.findAll();
        assertThat(mTriggerEffectRangeList).hasSize(databaseSizeBeforeCreate + 1);
        MTriggerEffectRange testMTriggerEffectRange = mTriggerEffectRangeList.get(mTriggerEffectRangeList.size() - 1);
        assertThat(testMTriggerEffectRange.getEffectType()).isEqualTo(DEFAULT_EFFECT_TYPE);
        assertThat(testMTriggerEffectRange.getTargetPositionGk()).isEqualTo(DEFAULT_TARGET_POSITION_GK);
        assertThat(testMTriggerEffectRange.getTargetPositionFw()).isEqualTo(DEFAULT_TARGET_POSITION_FW);
        assertThat(testMTriggerEffectRange.getTargetPositionOmf()).isEqualTo(DEFAULT_TARGET_POSITION_OMF);
        assertThat(testMTriggerEffectRange.getTargetPositionDmf()).isEqualTo(DEFAULT_TARGET_POSITION_DMF);
        assertThat(testMTriggerEffectRange.getTargetPositionDf()).isEqualTo(DEFAULT_TARGET_POSITION_DF);
        assertThat(testMTriggerEffectRange.getTargetAttribute()).isEqualTo(DEFAULT_TARGET_ATTRIBUTE);
        assertThat(testMTriggerEffectRange.getTargetNationalityGroupId()).isEqualTo(DEFAULT_TARGET_NATIONALITY_GROUP_ID);
        assertThat(testMTriggerEffectRange.getTargetTeamGroupId()).isEqualTo(DEFAULT_TARGET_TEAM_GROUP_ID);
        assertThat(testMTriggerEffectRange.getTargetCharacterGroupId()).isEqualTo(DEFAULT_TARGET_CHARACTER_GROUP_ID);
        assertThat(testMTriggerEffectRange.getTargetFormationGroupId()).isEqualTo(DEFAULT_TARGET_FORMATION_GROUP_ID);
        assertThat(testMTriggerEffectRange.getTargetActionCommand()).isEqualTo(DEFAULT_TARGET_ACTION_COMMAND);
    }

    @Test
    @Transactional
    public void createMTriggerEffectRangeWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = mTriggerEffectRangeRepository.findAll().size();

        // Create the MTriggerEffectRange with an existing ID
        mTriggerEffectRange.setId(1L);
        MTriggerEffectRangeDTO mTriggerEffectRangeDTO = mTriggerEffectRangeMapper.toDto(mTriggerEffectRange);

        // An entity with an existing ID cannot be created, so this API call must fail
        restMTriggerEffectRangeMockMvc.perform(post("/api/m-trigger-effect-ranges")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mTriggerEffectRangeDTO)))
            .andExpect(status().isBadRequest());

        // Validate the MTriggerEffectRange in the database
        List<MTriggerEffectRange> mTriggerEffectRangeList = mTriggerEffectRangeRepository.findAll();
        assertThat(mTriggerEffectRangeList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkEffectTypeIsRequired() throws Exception {
        int databaseSizeBeforeTest = mTriggerEffectRangeRepository.findAll().size();
        // set the field null
        mTriggerEffectRange.setEffectType(null);

        // Create the MTriggerEffectRange, which fails.
        MTriggerEffectRangeDTO mTriggerEffectRangeDTO = mTriggerEffectRangeMapper.toDto(mTriggerEffectRange);

        restMTriggerEffectRangeMockMvc.perform(post("/api/m-trigger-effect-ranges")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mTriggerEffectRangeDTO)))
            .andExpect(status().isBadRequest());

        List<MTriggerEffectRange> mTriggerEffectRangeList = mTriggerEffectRangeRepository.findAll();
        assertThat(mTriggerEffectRangeList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkTargetPositionGkIsRequired() throws Exception {
        int databaseSizeBeforeTest = mTriggerEffectRangeRepository.findAll().size();
        // set the field null
        mTriggerEffectRange.setTargetPositionGk(null);

        // Create the MTriggerEffectRange, which fails.
        MTriggerEffectRangeDTO mTriggerEffectRangeDTO = mTriggerEffectRangeMapper.toDto(mTriggerEffectRange);

        restMTriggerEffectRangeMockMvc.perform(post("/api/m-trigger-effect-ranges")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mTriggerEffectRangeDTO)))
            .andExpect(status().isBadRequest());

        List<MTriggerEffectRange> mTriggerEffectRangeList = mTriggerEffectRangeRepository.findAll();
        assertThat(mTriggerEffectRangeList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkTargetPositionFwIsRequired() throws Exception {
        int databaseSizeBeforeTest = mTriggerEffectRangeRepository.findAll().size();
        // set the field null
        mTriggerEffectRange.setTargetPositionFw(null);

        // Create the MTriggerEffectRange, which fails.
        MTriggerEffectRangeDTO mTriggerEffectRangeDTO = mTriggerEffectRangeMapper.toDto(mTriggerEffectRange);

        restMTriggerEffectRangeMockMvc.perform(post("/api/m-trigger-effect-ranges")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mTriggerEffectRangeDTO)))
            .andExpect(status().isBadRequest());

        List<MTriggerEffectRange> mTriggerEffectRangeList = mTriggerEffectRangeRepository.findAll();
        assertThat(mTriggerEffectRangeList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkTargetPositionOmfIsRequired() throws Exception {
        int databaseSizeBeforeTest = mTriggerEffectRangeRepository.findAll().size();
        // set the field null
        mTriggerEffectRange.setTargetPositionOmf(null);

        // Create the MTriggerEffectRange, which fails.
        MTriggerEffectRangeDTO mTriggerEffectRangeDTO = mTriggerEffectRangeMapper.toDto(mTriggerEffectRange);

        restMTriggerEffectRangeMockMvc.perform(post("/api/m-trigger-effect-ranges")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mTriggerEffectRangeDTO)))
            .andExpect(status().isBadRequest());

        List<MTriggerEffectRange> mTriggerEffectRangeList = mTriggerEffectRangeRepository.findAll();
        assertThat(mTriggerEffectRangeList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkTargetPositionDmfIsRequired() throws Exception {
        int databaseSizeBeforeTest = mTriggerEffectRangeRepository.findAll().size();
        // set the field null
        mTriggerEffectRange.setTargetPositionDmf(null);

        // Create the MTriggerEffectRange, which fails.
        MTriggerEffectRangeDTO mTriggerEffectRangeDTO = mTriggerEffectRangeMapper.toDto(mTriggerEffectRange);

        restMTriggerEffectRangeMockMvc.perform(post("/api/m-trigger-effect-ranges")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mTriggerEffectRangeDTO)))
            .andExpect(status().isBadRequest());

        List<MTriggerEffectRange> mTriggerEffectRangeList = mTriggerEffectRangeRepository.findAll();
        assertThat(mTriggerEffectRangeList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkTargetPositionDfIsRequired() throws Exception {
        int databaseSizeBeforeTest = mTriggerEffectRangeRepository.findAll().size();
        // set the field null
        mTriggerEffectRange.setTargetPositionDf(null);

        // Create the MTriggerEffectRange, which fails.
        MTriggerEffectRangeDTO mTriggerEffectRangeDTO = mTriggerEffectRangeMapper.toDto(mTriggerEffectRange);

        restMTriggerEffectRangeMockMvc.perform(post("/api/m-trigger-effect-ranges")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mTriggerEffectRangeDTO)))
            .andExpect(status().isBadRequest());

        List<MTriggerEffectRange> mTriggerEffectRangeList = mTriggerEffectRangeRepository.findAll();
        assertThat(mTriggerEffectRangeList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllMTriggerEffectRanges() throws Exception {
        // Initialize the database
        mTriggerEffectRangeRepository.saveAndFlush(mTriggerEffectRange);

        // Get all the mTriggerEffectRangeList
        restMTriggerEffectRangeMockMvc.perform(get("/api/m-trigger-effect-ranges?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(mTriggerEffectRange.getId().intValue())))
            .andExpect(jsonPath("$.[*].effectType").value(hasItem(DEFAULT_EFFECT_TYPE)))
            .andExpect(jsonPath("$.[*].targetPositionGk").value(hasItem(DEFAULT_TARGET_POSITION_GK)))
            .andExpect(jsonPath("$.[*].targetPositionFw").value(hasItem(DEFAULT_TARGET_POSITION_FW)))
            .andExpect(jsonPath("$.[*].targetPositionOmf").value(hasItem(DEFAULT_TARGET_POSITION_OMF)))
            .andExpect(jsonPath("$.[*].targetPositionDmf").value(hasItem(DEFAULT_TARGET_POSITION_DMF)))
            .andExpect(jsonPath("$.[*].targetPositionDf").value(hasItem(DEFAULT_TARGET_POSITION_DF)))
            .andExpect(jsonPath("$.[*].targetAttribute").value(hasItem(DEFAULT_TARGET_ATTRIBUTE)))
            .andExpect(jsonPath("$.[*].targetNationalityGroupId").value(hasItem(DEFAULT_TARGET_NATIONALITY_GROUP_ID)))
            .andExpect(jsonPath("$.[*].targetTeamGroupId").value(hasItem(DEFAULT_TARGET_TEAM_GROUP_ID)))
            .andExpect(jsonPath("$.[*].targetCharacterGroupId").value(hasItem(DEFAULT_TARGET_CHARACTER_GROUP_ID)))
            .andExpect(jsonPath("$.[*].targetFormationGroupId").value(hasItem(DEFAULT_TARGET_FORMATION_GROUP_ID)))
            .andExpect(jsonPath("$.[*].targetActionCommand").value(hasItem(DEFAULT_TARGET_ACTION_COMMAND)));
    }
    
    @Test
    @Transactional
    public void getMTriggerEffectRange() throws Exception {
        // Initialize the database
        mTriggerEffectRangeRepository.saveAndFlush(mTriggerEffectRange);

        // Get the mTriggerEffectRange
        restMTriggerEffectRangeMockMvc.perform(get("/api/m-trigger-effect-ranges/{id}", mTriggerEffectRange.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(mTriggerEffectRange.getId().intValue()))
            .andExpect(jsonPath("$.effectType").value(DEFAULT_EFFECT_TYPE))
            .andExpect(jsonPath("$.targetPositionGk").value(DEFAULT_TARGET_POSITION_GK))
            .andExpect(jsonPath("$.targetPositionFw").value(DEFAULT_TARGET_POSITION_FW))
            .andExpect(jsonPath("$.targetPositionOmf").value(DEFAULT_TARGET_POSITION_OMF))
            .andExpect(jsonPath("$.targetPositionDmf").value(DEFAULT_TARGET_POSITION_DMF))
            .andExpect(jsonPath("$.targetPositionDf").value(DEFAULT_TARGET_POSITION_DF))
            .andExpect(jsonPath("$.targetAttribute").value(DEFAULT_TARGET_ATTRIBUTE))
            .andExpect(jsonPath("$.targetNationalityGroupId").value(DEFAULT_TARGET_NATIONALITY_GROUP_ID))
            .andExpect(jsonPath("$.targetTeamGroupId").value(DEFAULT_TARGET_TEAM_GROUP_ID))
            .andExpect(jsonPath("$.targetCharacterGroupId").value(DEFAULT_TARGET_CHARACTER_GROUP_ID))
            .andExpect(jsonPath("$.targetFormationGroupId").value(DEFAULT_TARGET_FORMATION_GROUP_ID))
            .andExpect(jsonPath("$.targetActionCommand").value(DEFAULT_TARGET_ACTION_COMMAND));
    }

    @Test
    @Transactional
    public void getAllMTriggerEffectRangesByEffectTypeIsEqualToSomething() throws Exception {
        // Initialize the database
        mTriggerEffectRangeRepository.saveAndFlush(mTriggerEffectRange);

        // Get all the mTriggerEffectRangeList where effectType equals to DEFAULT_EFFECT_TYPE
        defaultMTriggerEffectRangeShouldBeFound("effectType.equals=" + DEFAULT_EFFECT_TYPE);

        // Get all the mTriggerEffectRangeList where effectType equals to UPDATED_EFFECT_TYPE
        defaultMTriggerEffectRangeShouldNotBeFound("effectType.equals=" + UPDATED_EFFECT_TYPE);
    }

    @Test
    @Transactional
    public void getAllMTriggerEffectRangesByEffectTypeIsInShouldWork() throws Exception {
        // Initialize the database
        mTriggerEffectRangeRepository.saveAndFlush(mTriggerEffectRange);

        // Get all the mTriggerEffectRangeList where effectType in DEFAULT_EFFECT_TYPE or UPDATED_EFFECT_TYPE
        defaultMTriggerEffectRangeShouldBeFound("effectType.in=" + DEFAULT_EFFECT_TYPE + "," + UPDATED_EFFECT_TYPE);

        // Get all the mTriggerEffectRangeList where effectType equals to UPDATED_EFFECT_TYPE
        defaultMTriggerEffectRangeShouldNotBeFound("effectType.in=" + UPDATED_EFFECT_TYPE);
    }

    @Test
    @Transactional
    public void getAllMTriggerEffectRangesByEffectTypeIsNullOrNotNull() throws Exception {
        // Initialize the database
        mTriggerEffectRangeRepository.saveAndFlush(mTriggerEffectRange);

        // Get all the mTriggerEffectRangeList where effectType is not null
        defaultMTriggerEffectRangeShouldBeFound("effectType.specified=true");

        // Get all the mTriggerEffectRangeList where effectType is null
        defaultMTriggerEffectRangeShouldNotBeFound("effectType.specified=false");
    }

    @Test
    @Transactional
    public void getAllMTriggerEffectRangesByEffectTypeIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mTriggerEffectRangeRepository.saveAndFlush(mTriggerEffectRange);

        // Get all the mTriggerEffectRangeList where effectType greater than or equals to DEFAULT_EFFECT_TYPE
        defaultMTriggerEffectRangeShouldBeFound("effectType.greaterOrEqualThan=" + DEFAULT_EFFECT_TYPE);

        // Get all the mTriggerEffectRangeList where effectType greater than or equals to UPDATED_EFFECT_TYPE
        defaultMTriggerEffectRangeShouldNotBeFound("effectType.greaterOrEqualThan=" + UPDATED_EFFECT_TYPE);
    }

    @Test
    @Transactional
    public void getAllMTriggerEffectRangesByEffectTypeIsLessThanSomething() throws Exception {
        // Initialize the database
        mTriggerEffectRangeRepository.saveAndFlush(mTriggerEffectRange);

        // Get all the mTriggerEffectRangeList where effectType less than or equals to DEFAULT_EFFECT_TYPE
        defaultMTriggerEffectRangeShouldNotBeFound("effectType.lessThan=" + DEFAULT_EFFECT_TYPE);

        // Get all the mTriggerEffectRangeList where effectType less than or equals to UPDATED_EFFECT_TYPE
        defaultMTriggerEffectRangeShouldBeFound("effectType.lessThan=" + UPDATED_EFFECT_TYPE);
    }


    @Test
    @Transactional
    public void getAllMTriggerEffectRangesByTargetPositionGkIsEqualToSomething() throws Exception {
        // Initialize the database
        mTriggerEffectRangeRepository.saveAndFlush(mTriggerEffectRange);

        // Get all the mTriggerEffectRangeList where targetPositionGk equals to DEFAULT_TARGET_POSITION_GK
        defaultMTriggerEffectRangeShouldBeFound("targetPositionGk.equals=" + DEFAULT_TARGET_POSITION_GK);

        // Get all the mTriggerEffectRangeList where targetPositionGk equals to UPDATED_TARGET_POSITION_GK
        defaultMTriggerEffectRangeShouldNotBeFound("targetPositionGk.equals=" + UPDATED_TARGET_POSITION_GK);
    }

    @Test
    @Transactional
    public void getAllMTriggerEffectRangesByTargetPositionGkIsInShouldWork() throws Exception {
        // Initialize the database
        mTriggerEffectRangeRepository.saveAndFlush(mTriggerEffectRange);

        // Get all the mTriggerEffectRangeList where targetPositionGk in DEFAULT_TARGET_POSITION_GK or UPDATED_TARGET_POSITION_GK
        defaultMTriggerEffectRangeShouldBeFound("targetPositionGk.in=" + DEFAULT_TARGET_POSITION_GK + "," + UPDATED_TARGET_POSITION_GK);

        // Get all the mTriggerEffectRangeList where targetPositionGk equals to UPDATED_TARGET_POSITION_GK
        defaultMTriggerEffectRangeShouldNotBeFound("targetPositionGk.in=" + UPDATED_TARGET_POSITION_GK);
    }

    @Test
    @Transactional
    public void getAllMTriggerEffectRangesByTargetPositionGkIsNullOrNotNull() throws Exception {
        // Initialize the database
        mTriggerEffectRangeRepository.saveAndFlush(mTriggerEffectRange);

        // Get all the mTriggerEffectRangeList where targetPositionGk is not null
        defaultMTriggerEffectRangeShouldBeFound("targetPositionGk.specified=true");

        // Get all the mTriggerEffectRangeList where targetPositionGk is null
        defaultMTriggerEffectRangeShouldNotBeFound("targetPositionGk.specified=false");
    }

    @Test
    @Transactional
    public void getAllMTriggerEffectRangesByTargetPositionGkIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mTriggerEffectRangeRepository.saveAndFlush(mTriggerEffectRange);

        // Get all the mTriggerEffectRangeList where targetPositionGk greater than or equals to DEFAULT_TARGET_POSITION_GK
        defaultMTriggerEffectRangeShouldBeFound("targetPositionGk.greaterOrEqualThan=" + DEFAULT_TARGET_POSITION_GK);

        // Get all the mTriggerEffectRangeList where targetPositionGk greater than or equals to UPDATED_TARGET_POSITION_GK
        defaultMTriggerEffectRangeShouldNotBeFound("targetPositionGk.greaterOrEqualThan=" + UPDATED_TARGET_POSITION_GK);
    }

    @Test
    @Transactional
    public void getAllMTriggerEffectRangesByTargetPositionGkIsLessThanSomething() throws Exception {
        // Initialize the database
        mTriggerEffectRangeRepository.saveAndFlush(mTriggerEffectRange);

        // Get all the mTriggerEffectRangeList where targetPositionGk less than or equals to DEFAULT_TARGET_POSITION_GK
        defaultMTriggerEffectRangeShouldNotBeFound("targetPositionGk.lessThan=" + DEFAULT_TARGET_POSITION_GK);

        // Get all the mTriggerEffectRangeList where targetPositionGk less than or equals to UPDATED_TARGET_POSITION_GK
        defaultMTriggerEffectRangeShouldBeFound("targetPositionGk.lessThan=" + UPDATED_TARGET_POSITION_GK);
    }


    @Test
    @Transactional
    public void getAllMTriggerEffectRangesByTargetPositionFwIsEqualToSomething() throws Exception {
        // Initialize the database
        mTriggerEffectRangeRepository.saveAndFlush(mTriggerEffectRange);

        // Get all the mTriggerEffectRangeList where targetPositionFw equals to DEFAULT_TARGET_POSITION_FW
        defaultMTriggerEffectRangeShouldBeFound("targetPositionFw.equals=" + DEFAULT_TARGET_POSITION_FW);

        // Get all the mTriggerEffectRangeList where targetPositionFw equals to UPDATED_TARGET_POSITION_FW
        defaultMTriggerEffectRangeShouldNotBeFound("targetPositionFw.equals=" + UPDATED_TARGET_POSITION_FW);
    }

    @Test
    @Transactional
    public void getAllMTriggerEffectRangesByTargetPositionFwIsInShouldWork() throws Exception {
        // Initialize the database
        mTriggerEffectRangeRepository.saveAndFlush(mTriggerEffectRange);

        // Get all the mTriggerEffectRangeList where targetPositionFw in DEFAULT_TARGET_POSITION_FW or UPDATED_TARGET_POSITION_FW
        defaultMTriggerEffectRangeShouldBeFound("targetPositionFw.in=" + DEFAULT_TARGET_POSITION_FW + "," + UPDATED_TARGET_POSITION_FW);

        // Get all the mTriggerEffectRangeList where targetPositionFw equals to UPDATED_TARGET_POSITION_FW
        defaultMTriggerEffectRangeShouldNotBeFound("targetPositionFw.in=" + UPDATED_TARGET_POSITION_FW);
    }

    @Test
    @Transactional
    public void getAllMTriggerEffectRangesByTargetPositionFwIsNullOrNotNull() throws Exception {
        // Initialize the database
        mTriggerEffectRangeRepository.saveAndFlush(mTriggerEffectRange);

        // Get all the mTriggerEffectRangeList where targetPositionFw is not null
        defaultMTriggerEffectRangeShouldBeFound("targetPositionFw.specified=true");

        // Get all the mTriggerEffectRangeList where targetPositionFw is null
        defaultMTriggerEffectRangeShouldNotBeFound("targetPositionFw.specified=false");
    }

    @Test
    @Transactional
    public void getAllMTriggerEffectRangesByTargetPositionFwIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mTriggerEffectRangeRepository.saveAndFlush(mTriggerEffectRange);

        // Get all the mTriggerEffectRangeList where targetPositionFw greater than or equals to DEFAULT_TARGET_POSITION_FW
        defaultMTriggerEffectRangeShouldBeFound("targetPositionFw.greaterOrEqualThan=" + DEFAULT_TARGET_POSITION_FW);

        // Get all the mTriggerEffectRangeList where targetPositionFw greater than or equals to UPDATED_TARGET_POSITION_FW
        defaultMTriggerEffectRangeShouldNotBeFound("targetPositionFw.greaterOrEqualThan=" + UPDATED_TARGET_POSITION_FW);
    }

    @Test
    @Transactional
    public void getAllMTriggerEffectRangesByTargetPositionFwIsLessThanSomething() throws Exception {
        // Initialize the database
        mTriggerEffectRangeRepository.saveAndFlush(mTriggerEffectRange);

        // Get all the mTriggerEffectRangeList where targetPositionFw less than or equals to DEFAULT_TARGET_POSITION_FW
        defaultMTriggerEffectRangeShouldNotBeFound("targetPositionFw.lessThan=" + DEFAULT_TARGET_POSITION_FW);

        // Get all the mTriggerEffectRangeList where targetPositionFw less than or equals to UPDATED_TARGET_POSITION_FW
        defaultMTriggerEffectRangeShouldBeFound("targetPositionFw.lessThan=" + UPDATED_TARGET_POSITION_FW);
    }


    @Test
    @Transactional
    public void getAllMTriggerEffectRangesByTargetPositionOmfIsEqualToSomething() throws Exception {
        // Initialize the database
        mTriggerEffectRangeRepository.saveAndFlush(mTriggerEffectRange);

        // Get all the mTriggerEffectRangeList where targetPositionOmf equals to DEFAULT_TARGET_POSITION_OMF
        defaultMTriggerEffectRangeShouldBeFound("targetPositionOmf.equals=" + DEFAULT_TARGET_POSITION_OMF);

        // Get all the mTriggerEffectRangeList where targetPositionOmf equals to UPDATED_TARGET_POSITION_OMF
        defaultMTriggerEffectRangeShouldNotBeFound("targetPositionOmf.equals=" + UPDATED_TARGET_POSITION_OMF);
    }

    @Test
    @Transactional
    public void getAllMTriggerEffectRangesByTargetPositionOmfIsInShouldWork() throws Exception {
        // Initialize the database
        mTriggerEffectRangeRepository.saveAndFlush(mTriggerEffectRange);

        // Get all the mTriggerEffectRangeList where targetPositionOmf in DEFAULT_TARGET_POSITION_OMF or UPDATED_TARGET_POSITION_OMF
        defaultMTriggerEffectRangeShouldBeFound("targetPositionOmf.in=" + DEFAULT_TARGET_POSITION_OMF + "," + UPDATED_TARGET_POSITION_OMF);

        // Get all the mTriggerEffectRangeList where targetPositionOmf equals to UPDATED_TARGET_POSITION_OMF
        defaultMTriggerEffectRangeShouldNotBeFound("targetPositionOmf.in=" + UPDATED_TARGET_POSITION_OMF);
    }

    @Test
    @Transactional
    public void getAllMTriggerEffectRangesByTargetPositionOmfIsNullOrNotNull() throws Exception {
        // Initialize the database
        mTriggerEffectRangeRepository.saveAndFlush(mTriggerEffectRange);

        // Get all the mTriggerEffectRangeList where targetPositionOmf is not null
        defaultMTriggerEffectRangeShouldBeFound("targetPositionOmf.specified=true");

        // Get all the mTriggerEffectRangeList where targetPositionOmf is null
        defaultMTriggerEffectRangeShouldNotBeFound("targetPositionOmf.specified=false");
    }

    @Test
    @Transactional
    public void getAllMTriggerEffectRangesByTargetPositionOmfIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mTriggerEffectRangeRepository.saveAndFlush(mTriggerEffectRange);

        // Get all the mTriggerEffectRangeList where targetPositionOmf greater than or equals to DEFAULT_TARGET_POSITION_OMF
        defaultMTriggerEffectRangeShouldBeFound("targetPositionOmf.greaterOrEqualThan=" + DEFAULT_TARGET_POSITION_OMF);

        // Get all the mTriggerEffectRangeList where targetPositionOmf greater than or equals to UPDATED_TARGET_POSITION_OMF
        defaultMTriggerEffectRangeShouldNotBeFound("targetPositionOmf.greaterOrEqualThan=" + UPDATED_TARGET_POSITION_OMF);
    }

    @Test
    @Transactional
    public void getAllMTriggerEffectRangesByTargetPositionOmfIsLessThanSomething() throws Exception {
        // Initialize the database
        mTriggerEffectRangeRepository.saveAndFlush(mTriggerEffectRange);

        // Get all the mTriggerEffectRangeList where targetPositionOmf less than or equals to DEFAULT_TARGET_POSITION_OMF
        defaultMTriggerEffectRangeShouldNotBeFound("targetPositionOmf.lessThan=" + DEFAULT_TARGET_POSITION_OMF);

        // Get all the mTriggerEffectRangeList where targetPositionOmf less than or equals to UPDATED_TARGET_POSITION_OMF
        defaultMTriggerEffectRangeShouldBeFound("targetPositionOmf.lessThan=" + UPDATED_TARGET_POSITION_OMF);
    }


    @Test
    @Transactional
    public void getAllMTriggerEffectRangesByTargetPositionDmfIsEqualToSomething() throws Exception {
        // Initialize the database
        mTriggerEffectRangeRepository.saveAndFlush(mTriggerEffectRange);

        // Get all the mTriggerEffectRangeList where targetPositionDmf equals to DEFAULT_TARGET_POSITION_DMF
        defaultMTriggerEffectRangeShouldBeFound("targetPositionDmf.equals=" + DEFAULT_TARGET_POSITION_DMF);

        // Get all the mTriggerEffectRangeList where targetPositionDmf equals to UPDATED_TARGET_POSITION_DMF
        defaultMTriggerEffectRangeShouldNotBeFound("targetPositionDmf.equals=" + UPDATED_TARGET_POSITION_DMF);
    }

    @Test
    @Transactional
    public void getAllMTriggerEffectRangesByTargetPositionDmfIsInShouldWork() throws Exception {
        // Initialize the database
        mTriggerEffectRangeRepository.saveAndFlush(mTriggerEffectRange);

        // Get all the mTriggerEffectRangeList where targetPositionDmf in DEFAULT_TARGET_POSITION_DMF or UPDATED_TARGET_POSITION_DMF
        defaultMTriggerEffectRangeShouldBeFound("targetPositionDmf.in=" + DEFAULT_TARGET_POSITION_DMF + "," + UPDATED_TARGET_POSITION_DMF);

        // Get all the mTriggerEffectRangeList where targetPositionDmf equals to UPDATED_TARGET_POSITION_DMF
        defaultMTriggerEffectRangeShouldNotBeFound("targetPositionDmf.in=" + UPDATED_TARGET_POSITION_DMF);
    }

    @Test
    @Transactional
    public void getAllMTriggerEffectRangesByTargetPositionDmfIsNullOrNotNull() throws Exception {
        // Initialize the database
        mTriggerEffectRangeRepository.saveAndFlush(mTriggerEffectRange);

        // Get all the mTriggerEffectRangeList where targetPositionDmf is not null
        defaultMTriggerEffectRangeShouldBeFound("targetPositionDmf.specified=true");

        // Get all the mTriggerEffectRangeList where targetPositionDmf is null
        defaultMTriggerEffectRangeShouldNotBeFound("targetPositionDmf.specified=false");
    }

    @Test
    @Transactional
    public void getAllMTriggerEffectRangesByTargetPositionDmfIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mTriggerEffectRangeRepository.saveAndFlush(mTriggerEffectRange);

        // Get all the mTriggerEffectRangeList where targetPositionDmf greater than or equals to DEFAULT_TARGET_POSITION_DMF
        defaultMTriggerEffectRangeShouldBeFound("targetPositionDmf.greaterOrEqualThan=" + DEFAULT_TARGET_POSITION_DMF);

        // Get all the mTriggerEffectRangeList where targetPositionDmf greater than or equals to UPDATED_TARGET_POSITION_DMF
        defaultMTriggerEffectRangeShouldNotBeFound("targetPositionDmf.greaterOrEqualThan=" + UPDATED_TARGET_POSITION_DMF);
    }

    @Test
    @Transactional
    public void getAllMTriggerEffectRangesByTargetPositionDmfIsLessThanSomething() throws Exception {
        // Initialize the database
        mTriggerEffectRangeRepository.saveAndFlush(mTriggerEffectRange);

        // Get all the mTriggerEffectRangeList where targetPositionDmf less than or equals to DEFAULT_TARGET_POSITION_DMF
        defaultMTriggerEffectRangeShouldNotBeFound("targetPositionDmf.lessThan=" + DEFAULT_TARGET_POSITION_DMF);

        // Get all the mTriggerEffectRangeList where targetPositionDmf less than or equals to UPDATED_TARGET_POSITION_DMF
        defaultMTriggerEffectRangeShouldBeFound("targetPositionDmf.lessThan=" + UPDATED_TARGET_POSITION_DMF);
    }


    @Test
    @Transactional
    public void getAllMTriggerEffectRangesByTargetPositionDfIsEqualToSomething() throws Exception {
        // Initialize the database
        mTriggerEffectRangeRepository.saveAndFlush(mTriggerEffectRange);

        // Get all the mTriggerEffectRangeList where targetPositionDf equals to DEFAULT_TARGET_POSITION_DF
        defaultMTriggerEffectRangeShouldBeFound("targetPositionDf.equals=" + DEFAULT_TARGET_POSITION_DF);

        // Get all the mTriggerEffectRangeList where targetPositionDf equals to UPDATED_TARGET_POSITION_DF
        defaultMTriggerEffectRangeShouldNotBeFound("targetPositionDf.equals=" + UPDATED_TARGET_POSITION_DF);
    }

    @Test
    @Transactional
    public void getAllMTriggerEffectRangesByTargetPositionDfIsInShouldWork() throws Exception {
        // Initialize the database
        mTriggerEffectRangeRepository.saveAndFlush(mTriggerEffectRange);

        // Get all the mTriggerEffectRangeList where targetPositionDf in DEFAULT_TARGET_POSITION_DF or UPDATED_TARGET_POSITION_DF
        defaultMTriggerEffectRangeShouldBeFound("targetPositionDf.in=" + DEFAULT_TARGET_POSITION_DF + "," + UPDATED_TARGET_POSITION_DF);

        // Get all the mTriggerEffectRangeList where targetPositionDf equals to UPDATED_TARGET_POSITION_DF
        defaultMTriggerEffectRangeShouldNotBeFound("targetPositionDf.in=" + UPDATED_TARGET_POSITION_DF);
    }

    @Test
    @Transactional
    public void getAllMTriggerEffectRangesByTargetPositionDfIsNullOrNotNull() throws Exception {
        // Initialize the database
        mTriggerEffectRangeRepository.saveAndFlush(mTriggerEffectRange);

        // Get all the mTriggerEffectRangeList where targetPositionDf is not null
        defaultMTriggerEffectRangeShouldBeFound("targetPositionDf.specified=true");

        // Get all the mTriggerEffectRangeList where targetPositionDf is null
        defaultMTriggerEffectRangeShouldNotBeFound("targetPositionDf.specified=false");
    }

    @Test
    @Transactional
    public void getAllMTriggerEffectRangesByTargetPositionDfIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mTriggerEffectRangeRepository.saveAndFlush(mTriggerEffectRange);

        // Get all the mTriggerEffectRangeList where targetPositionDf greater than or equals to DEFAULT_TARGET_POSITION_DF
        defaultMTriggerEffectRangeShouldBeFound("targetPositionDf.greaterOrEqualThan=" + DEFAULT_TARGET_POSITION_DF);

        // Get all the mTriggerEffectRangeList where targetPositionDf greater than or equals to UPDATED_TARGET_POSITION_DF
        defaultMTriggerEffectRangeShouldNotBeFound("targetPositionDf.greaterOrEqualThan=" + UPDATED_TARGET_POSITION_DF);
    }

    @Test
    @Transactional
    public void getAllMTriggerEffectRangesByTargetPositionDfIsLessThanSomething() throws Exception {
        // Initialize the database
        mTriggerEffectRangeRepository.saveAndFlush(mTriggerEffectRange);

        // Get all the mTriggerEffectRangeList where targetPositionDf less than or equals to DEFAULT_TARGET_POSITION_DF
        defaultMTriggerEffectRangeShouldNotBeFound("targetPositionDf.lessThan=" + DEFAULT_TARGET_POSITION_DF);

        // Get all the mTriggerEffectRangeList where targetPositionDf less than or equals to UPDATED_TARGET_POSITION_DF
        defaultMTriggerEffectRangeShouldBeFound("targetPositionDf.lessThan=" + UPDATED_TARGET_POSITION_DF);
    }


    @Test
    @Transactional
    public void getAllMTriggerEffectRangesByTargetAttributeIsEqualToSomething() throws Exception {
        // Initialize the database
        mTriggerEffectRangeRepository.saveAndFlush(mTriggerEffectRange);

        // Get all the mTriggerEffectRangeList where targetAttribute equals to DEFAULT_TARGET_ATTRIBUTE
        defaultMTriggerEffectRangeShouldBeFound("targetAttribute.equals=" + DEFAULT_TARGET_ATTRIBUTE);

        // Get all the mTriggerEffectRangeList where targetAttribute equals to UPDATED_TARGET_ATTRIBUTE
        defaultMTriggerEffectRangeShouldNotBeFound("targetAttribute.equals=" + UPDATED_TARGET_ATTRIBUTE);
    }

    @Test
    @Transactional
    public void getAllMTriggerEffectRangesByTargetAttributeIsInShouldWork() throws Exception {
        // Initialize the database
        mTriggerEffectRangeRepository.saveAndFlush(mTriggerEffectRange);

        // Get all the mTriggerEffectRangeList where targetAttribute in DEFAULT_TARGET_ATTRIBUTE or UPDATED_TARGET_ATTRIBUTE
        defaultMTriggerEffectRangeShouldBeFound("targetAttribute.in=" + DEFAULT_TARGET_ATTRIBUTE + "," + UPDATED_TARGET_ATTRIBUTE);

        // Get all the mTriggerEffectRangeList where targetAttribute equals to UPDATED_TARGET_ATTRIBUTE
        defaultMTriggerEffectRangeShouldNotBeFound("targetAttribute.in=" + UPDATED_TARGET_ATTRIBUTE);
    }

    @Test
    @Transactional
    public void getAllMTriggerEffectRangesByTargetAttributeIsNullOrNotNull() throws Exception {
        // Initialize the database
        mTriggerEffectRangeRepository.saveAndFlush(mTriggerEffectRange);

        // Get all the mTriggerEffectRangeList where targetAttribute is not null
        defaultMTriggerEffectRangeShouldBeFound("targetAttribute.specified=true");

        // Get all the mTriggerEffectRangeList where targetAttribute is null
        defaultMTriggerEffectRangeShouldNotBeFound("targetAttribute.specified=false");
    }

    @Test
    @Transactional
    public void getAllMTriggerEffectRangesByTargetAttributeIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mTriggerEffectRangeRepository.saveAndFlush(mTriggerEffectRange);

        // Get all the mTriggerEffectRangeList where targetAttribute greater than or equals to DEFAULT_TARGET_ATTRIBUTE
        defaultMTriggerEffectRangeShouldBeFound("targetAttribute.greaterOrEqualThan=" + DEFAULT_TARGET_ATTRIBUTE);

        // Get all the mTriggerEffectRangeList where targetAttribute greater than or equals to UPDATED_TARGET_ATTRIBUTE
        defaultMTriggerEffectRangeShouldNotBeFound("targetAttribute.greaterOrEqualThan=" + UPDATED_TARGET_ATTRIBUTE);
    }

    @Test
    @Transactional
    public void getAllMTriggerEffectRangesByTargetAttributeIsLessThanSomething() throws Exception {
        // Initialize the database
        mTriggerEffectRangeRepository.saveAndFlush(mTriggerEffectRange);

        // Get all the mTriggerEffectRangeList where targetAttribute less than or equals to DEFAULT_TARGET_ATTRIBUTE
        defaultMTriggerEffectRangeShouldNotBeFound("targetAttribute.lessThan=" + DEFAULT_TARGET_ATTRIBUTE);

        // Get all the mTriggerEffectRangeList where targetAttribute less than or equals to UPDATED_TARGET_ATTRIBUTE
        defaultMTriggerEffectRangeShouldBeFound("targetAttribute.lessThan=" + UPDATED_TARGET_ATTRIBUTE);
    }


    @Test
    @Transactional
    public void getAllMTriggerEffectRangesByTargetNationalityGroupIdIsEqualToSomething() throws Exception {
        // Initialize the database
        mTriggerEffectRangeRepository.saveAndFlush(mTriggerEffectRange);

        // Get all the mTriggerEffectRangeList where targetNationalityGroupId equals to DEFAULT_TARGET_NATIONALITY_GROUP_ID
        defaultMTriggerEffectRangeShouldBeFound("targetNationalityGroupId.equals=" + DEFAULT_TARGET_NATIONALITY_GROUP_ID);

        // Get all the mTriggerEffectRangeList where targetNationalityGroupId equals to UPDATED_TARGET_NATIONALITY_GROUP_ID
        defaultMTriggerEffectRangeShouldNotBeFound("targetNationalityGroupId.equals=" + UPDATED_TARGET_NATIONALITY_GROUP_ID);
    }

    @Test
    @Transactional
    public void getAllMTriggerEffectRangesByTargetNationalityGroupIdIsInShouldWork() throws Exception {
        // Initialize the database
        mTriggerEffectRangeRepository.saveAndFlush(mTriggerEffectRange);

        // Get all the mTriggerEffectRangeList where targetNationalityGroupId in DEFAULT_TARGET_NATIONALITY_GROUP_ID or UPDATED_TARGET_NATIONALITY_GROUP_ID
        defaultMTriggerEffectRangeShouldBeFound("targetNationalityGroupId.in=" + DEFAULT_TARGET_NATIONALITY_GROUP_ID + "," + UPDATED_TARGET_NATIONALITY_GROUP_ID);

        // Get all the mTriggerEffectRangeList where targetNationalityGroupId equals to UPDATED_TARGET_NATIONALITY_GROUP_ID
        defaultMTriggerEffectRangeShouldNotBeFound("targetNationalityGroupId.in=" + UPDATED_TARGET_NATIONALITY_GROUP_ID);
    }

    @Test
    @Transactional
    public void getAllMTriggerEffectRangesByTargetNationalityGroupIdIsNullOrNotNull() throws Exception {
        // Initialize the database
        mTriggerEffectRangeRepository.saveAndFlush(mTriggerEffectRange);

        // Get all the mTriggerEffectRangeList where targetNationalityGroupId is not null
        defaultMTriggerEffectRangeShouldBeFound("targetNationalityGroupId.specified=true");

        // Get all the mTriggerEffectRangeList where targetNationalityGroupId is null
        defaultMTriggerEffectRangeShouldNotBeFound("targetNationalityGroupId.specified=false");
    }

    @Test
    @Transactional
    public void getAllMTriggerEffectRangesByTargetNationalityGroupIdIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mTriggerEffectRangeRepository.saveAndFlush(mTriggerEffectRange);

        // Get all the mTriggerEffectRangeList where targetNationalityGroupId greater than or equals to DEFAULT_TARGET_NATIONALITY_GROUP_ID
        defaultMTriggerEffectRangeShouldBeFound("targetNationalityGroupId.greaterOrEqualThan=" + DEFAULT_TARGET_NATIONALITY_GROUP_ID);

        // Get all the mTriggerEffectRangeList where targetNationalityGroupId greater than or equals to UPDATED_TARGET_NATIONALITY_GROUP_ID
        defaultMTriggerEffectRangeShouldNotBeFound("targetNationalityGroupId.greaterOrEqualThan=" + UPDATED_TARGET_NATIONALITY_GROUP_ID);
    }

    @Test
    @Transactional
    public void getAllMTriggerEffectRangesByTargetNationalityGroupIdIsLessThanSomething() throws Exception {
        // Initialize the database
        mTriggerEffectRangeRepository.saveAndFlush(mTriggerEffectRange);

        // Get all the mTriggerEffectRangeList where targetNationalityGroupId less than or equals to DEFAULT_TARGET_NATIONALITY_GROUP_ID
        defaultMTriggerEffectRangeShouldNotBeFound("targetNationalityGroupId.lessThan=" + DEFAULT_TARGET_NATIONALITY_GROUP_ID);

        // Get all the mTriggerEffectRangeList where targetNationalityGroupId less than or equals to UPDATED_TARGET_NATIONALITY_GROUP_ID
        defaultMTriggerEffectRangeShouldBeFound("targetNationalityGroupId.lessThan=" + UPDATED_TARGET_NATIONALITY_GROUP_ID);
    }


    @Test
    @Transactional
    public void getAllMTriggerEffectRangesByTargetTeamGroupIdIsEqualToSomething() throws Exception {
        // Initialize the database
        mTriggerEffectRangeRepository.saveAndFlush(mTriggerEffectRange);

        // Get all the mTriggerEffectRangeList where targetTeamGroupId equals to DEFAULT_TARGET_TEAM_GROUP_ID
        defaultMTriggerEffectRangeShouldBeFound("targetTeamGroupId.equals=" + DEFAULT_TARGET_TEAM_GROUP_ID);

        // Get all the mTriggerEffectRangeList where targetTeamGroupId equals to UPDATED_TARGET_TEAM_GROUP_ID
        defaultMTriggerEffectRangeShouldNotBeFound("targetTeamGroupId.equals=" + UPDATED_TARGET_TEAM_GROUP_ID);
    }

    @Test
    @Transactional
    public void getAllMTriggerEffectRangesByTargetTeamGroupIdIsInShouldWork() throws Exception {
        // Initialize the database
        mTriggerEffectRangeRepository.saveAndFlush(mTriggerEffectRange);

        // Get all the mTriggerEffectRangeList where targetTeamGroupId in DEFAULT_TARGET_TEAM_GROUP_ID or UPDATED_TARGET_TEAM_GROUP_ID
        defaultMTriggerEffectRangeShouldBeFound("targetTeamGroupId.in=" + DEFAULT_TARGET_TEAM_GROUP_ID + "," + UPDATED_TARGET_TEAM_GROUP_ID);

        // Get all the mTriggerEffectRangeList where targetTeamGroupId equals to UPDATED_TARGET_TEAM_GROUP_ID
        defaultMTriggerEffectRangeShouldNotBeFound("targetTeamGroupId.in=" + UPDATED_TARGET_TEAM_GROUP_ID);
    }

    @Test
    @Transactional
    public void getAllMTriggerEffectRangesByTargetTeamGroupIdIsNullOrNotNull() throws Exception {
        // Initialize the database
        mTriggerEffectRangeRepository.saveAndFlush(mTriggerEffectRange);

        // Get all the mTriggerEffectRangeList where targetTeamGroupId is not null
        defaultMTriggerEffectRangeShouldBeFound("targetTeamGroupId.specified=true");

        // Get all the mTriggerEffectRangeList where targetTeamGroupId is null
        defaultMTriggerEffectRangeShouldNotBeFound("targetTeamGroupId.specified=false");
    }

    @Test
    @Transactional
    public void getAllMTriggerEffectRangesByTargetTeamGroupIdIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mTriggerEffectRangeRepository.saveAndFlush(mTriggerEffectRange);

        // Get all the mTriggerEffectRangeList where targetTeamGroupId greater than or equals to DEFAULT_TARGET_TEAM_GROUP_ID
        defaultMTriggerEffectRangeShouldBeFound("targetTeamGroupId.greaterOrEqualThan=" + DEFAULT_TARGET_TEAM_GROUP_ID);

        // Get all the mTriggerEffectRangeList where targetTeamGroupId greater than or equals to UPDATED_TARGET_TEAM_GROUP_ID
        defaultMTriggerEffectRangeShouldNotBeFound("targetTeamGroupId.greaterOrEqualThan=" + UPDATED_TARGET_TEAM_GROUP_ID);
    }

    @Test
    @Transactional
    public void getAllMTriggerEffectRangesByTargetTeamGroupIdIsLessThanSomething() throws Exception {
        // Initialize the database
        mTriggerEffectRangeRepository.saveAndFlush(mTriggerEffectRange);

        // Get all the mTriggerEffectRangeList where targetTeamGroupId less than or equals to DEFAULT_TARGET_TEAM_GROUP_ID
        defaultMTriggerEffectRangeShouldNotBeFound("targetTeamGroupId.lessThan=" + DEFAULT_TARGET_TEAM_GROUP_ID);

        // Get all the mTriggerEffectRangeList where targetTeamGroupId less than or equals to UPDATED_TARGET_TEAM_GROUP_ID
        defaultMTriggerEffectRangeShouldBeFound("targetTeamGroupId.lessThan=" + UPDATED_TARGET_TEAM_GROUP_ID);
    }


    @Test
    @Transactional
    public void getAllMTriggerEffectRangesByTargetCharacterGroupIdIsEqualToSomething() throws Exception {
        // Initialize the database
        mTriggerEffectRangeRepository.saveAndFlush(mTriggerEffectRange);

        // Get all the mTriggerEffectRangeList where targetCharacterGroupId equals to DEFAULT_TARGET_CHARACTER_GROUP_ID
        defaultMTriggerEffectRangeShouldBeFound("targetCharacterGroupId.equals=" + DEFAULT_TARGET_CHARACTER_GROUP_ID);

        // Get all the mTriggerEffectRangeList where targetCharacterGroupId equals to UPDATED_TARGET_CHARACTER_GROUP_ID
        defaultMTriggerEffectRangeShouldNotBeFound("targetCharacterGroupId.equals=" + UPDATED_TARGET_CHARACTER_GROUP_ID);
    }

    @Test
    @Transactional
    public void getAllMTriggerEffectRangesByTargetCharacterGroupIdIsInShouldWork() throws Exception {
        // Initialize the database
        mTriggerEffectRangeRepository.saveAndFlush(mTriggerEffectRange);

        // Get all the mTriggerEffectRangeList where targetCharacterGroupId in DEFAULT_TARGET_CHARACTER_GROUP_ID or UPDATED_TARGET_CHARACTER_GROUP_ID
        defaultMTriggerEffectRangeShouldBeFound("targetCharacterGroupId.in=" + DEFAULT_TARGET_CHARACTER_GROUP_ID + "," + UPDATED_TARGET_CHARACTER_GROUP_ID);

        // Get all the mTriggerEffectRangeList where targetCharacterGroupId equals to UPDATED_TARGET_CHARACTER_GROUP_ID
        defaultMTriggerEffectRangeShouldNotBeFound("targetCharacterGroupId.in=" + UPDATED_TARGET_CHARACTER_GROUP_ID);
    }

    @Test
    @Transactional
    public void getAllMTriggerEffectRangesByTargetCharacterGroupIdIsNullOrNotNull() throws Exception {
        // Initialize the database
        mTriggerEffectRangeRepository.saveAndFlush(mTriggerEffectRange);

        // Get all the mTriggerEffectRangeList where targetCharacterGroupId is not null
        defaultMTriggerEffectRangeShouldBeFound("targetCharacterGroupId.specified=true");

        // Get all the mTriggerEffectRangeList where targetCharacterGroupId is null
        defaultMTriggerEffectRangeShouldNotBeFound("targetCharacterGroupId.specified=false");
    }

    @Test
    @Transactional
    public void getAllMTriggerEffectRangesByTargetCharacterGroupIdIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mTriggerEffectRangeRepository.saveAndFlush(mTriggerEffectRange);

        // Get all the mTriggerEffectRangeList where targetCharacterGroupId greater than or equals to DEFAULT_TARGET_CHARACTER_GROUP_ID
        defaultMTriggerEffectRangeShouldBeFound("targetCharacterGroupId.greaterOrEqualThan=" + DEFAULT_TARGET_CHARACTER_GROUP_ID);

        // Get all the mTriggerEffectRangeList where targetCharacterGroupId greater than or equals to UPDATED_TARGET_CHARACTER_GROUP_ID
        defaultMTriggerEffectRangeShouldNotBeFound("targetCharacterGroupId.greaterOrEqualThan=" + UPDATED_TARGET_CHARACTER_GROUP_ID);
    }

    @Test
    @Transactional
    public void getAllMTriggerEffectRangesByTargetCharacterGroupIdIsLessThanSomething() throws Exception {
        // Initialize the database
        mTriggerEffectRangeRepository.saveAndFlush(mTriggerEffectRange);

        // Get all the mTriggerEffectRangeList where targetCharacterGroupId less than or equals to DEFAULT_TARGET_CHARACTER_GROUP_ID
        defaultMTriggerEffectRangeShouldNotBeFound("targetCharacterGroupId.lessThan=" + DEFAULT_TARGET_CHARACTER_GROUP_ID);

        // Get all the mTriggerEffectRangeList where targetCharacterGroupId less than or equals to UPDATED_TARGET_CHARACTER_GROUP_ID
        defaultMTriggerEffectRangeShouldBeFound("targetCharacterGroupId.lessThan=" + UPDATED_TARGET_CHARACTER_GROUP_ID);
    }


    @Test
    @Transactional
    public void getAllMTriggerEffectRangesByTargetFormationGroupIdIsEqualToSomething() throws Exception {
        // Initialize the database
        mTriggerEffectRangeRepository.saveAndFlush(mTriggerEffectRange);

        // Get all the mTriggerEffectRangeList where targetFormationGroupId equals to DEFAULT_TARGET_FORMATION_GROUP_ID
        defaultMTriggerEffectRangeShouldBeFound("targetFormationGroupId.equals=" + DEFAULT_TARGET_FORMATION_GROUP_ID);

        // Get all the mTriggerEffectRangeList where targetFormationGroupId equals to UPDATED_TARGET_FORMATION_GROUP_ID
        defaultMTriggerEffectRangeShouldNotBeFound("targetFormationGroupId.equals=" + UPDATED_TARGET_FORMATION_GROUP_ID);
    }

    @Test
    @Transactional
    public void getAllMTriggerEffectRangesByTargetFormationGroupIdIsInShouldWork() throws Exception {
        // Initialize the database
        mTriggerEffectRangeRepository.saveAndFlush(mTriggerEffectRange);

        // Get all the mTriggerEffectRangeList where targetFormationGroupId in DEFAULT_TARGET_FORMATION_GROUP_ID or UPDATED_TARGET_FORMATION_GROUP_ID
        defaultMTriggerEffectRangeShouldBeFound("targetFormationGroupId.in=" + DEFAULT_TARGET_FORMATION_GROUP_ID + "," + UPDATED_TARGET_FORMATION_GROUP_ID);

        // Get all the mTriggerEffectRangeList where targetFormationGroupId equals to UPDATED_TARGET_FORMATION_GROUP_ID
        defaultMTriggerEffectRangeShouldNotBeFound("targetFormationGroupId.in=" + UPDATED_TARGET_FORMATION_GROUP_ID);
    }

    @Test
    @Transactional
    public void getAllMTriggerEffectRangesByTargetFormationGroupIdIsNullOrNotNull() throws Exception {
        // Initialize the database
        mTriggerEffectRangeRepository.saveAndFlush(mTriggerEffectRange);

        // Get all the mTriggerEffectRangeList where targetFormationGroupId is not null
        defaultMTriggerEffectRangeShouldBeFound("targetFormationGroupId.specified=true");

        // Get all the mTriggerEffectRangeList where targetFormationGroupId is null
        defaultMTriggerEffectRangeShouldNotBeFound("targetFormationGroupId.specified=false");
    }

    @Test
    @Transactional
    public void getAllMTriggerEffectRangesByTargetFormationGroupIdIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mTriggerEffectRangeRepository.saveAndFlush(mTriggerEffectRange);

        // Get all the mTriggerEffectRangeList where targetFormationGroupId greater than or equals to DEFAULT_TARGET_FORMATION_GROUP_ID
        defaultMTriggerEffectRangeShouldBeFound("targetFormationGroupId.greaterOrEqualThan=" + DEFAULT_TARGET_FORMATION_GROUP_ID);

        // Get all the mTriggerEffectRangeList where targetFormationGroupId greater than or equals to UPDATED_TARGET_FORMATION_GROUP_ID
        defaultMTriggerEffectRangeShouldNotBeFound("targetFormationGroupId.greaterOrEqualThan=" + UPDATED_TARGET_FORMATION_GROUP_ID);
    }

    @Test
    @Transactional
    public void getAllMTriggerEffectRangesByTargetFormationGroupIdIsLessThanSomething() throws Exception {
        // Initialize the database
        mTriggerEffectRangeRepository.saveAndFlush(mTriggerEffectRange);

        // Get all the mTriggerEffectRangeList where targetFormationGroupId less than or equals to DEFAULT_TARGET_FORMATION_GROUP_ID
        defaultMTriggerEffectRangeShouldNotBeFound("targetFormationGroupId.lessThan=" + DEFAULT_TARGET_FORMATION_GROUP_ID);

        // Get all the mTriggerEffectRangeList where targetFormationGroupId less than or equals to UPDATED_TARGET_FORMATION_GROUP_ID
        defaultMTriggerEffectRangeShouldBeFound("targetFormationGroupId.lessThan=" + UPDATED_TARGET_FORMATION_GROUP_ID);
    }


    @Test
    @Transactional
    public void getAllMTriggerEffectRangesByTargetActionCommandIsEqualToSomething() throws Exception {
        // Initialize the database
        mTriggerEffectRangeRepository.saveAndFlush(mTriggerEffectRange);

        // Get all the mTriggerEffectRangeList where targetActionCommand equals to DEFAULT_TARGET_ACTION_COMMAND
        defaultMTriggerEffectRangeShouldBeFound("targetActionCommand.equals=" + DEFAULT_TARGET_ACTION_COMMAND);

        // Get all the mTriggerEffectRangeList where targetActionCommand equals to UPDATED_TARGET_ACTION_COMMAND
        defaultMTriggerEffectRangeShouldNotBeFound("targetActionCommand.equals=" + UPDATED_TARGET_ACTION_COMMAND);
    }

    @Test
    @Transactional
    public void getAllMTriggerEffectRangesByTargetActionCommandIsInShouldWork() throws Exception {
        // Initialize the database
        mTriggerEffectRangeRepository.saveAndFlush(mTriggerEffectRange);

        // Get all the mTriggerEffectRangeList where targetActionCommand in DEFAULT_TARGET_ACTION_COMMAND or UPDATED_TARGET_ACTION_COMMAND
        defaultMTriggerEffectRangeShouldBeFound("targetActionCommand.in=" + DEFAULT_TARGET_ACTION_COMMAND + "," + UPDATED_TARGET_ACTION_COMMAND);

        // Get all the mTriggerEffectRangeList where targetActionCommand equals to UPDATED_TARGET_ACTION_COMMAND
        defaultMTriggerEffectRangeShouldNotBeFound("targetActionCommand.in=" + UPDATED_TARGET_ACTION_COMMAND);
    }

    @Test
    @Transactional
    public void getAllMTriggerEffectRangesByTargetActionCommandIsNullOrNotNull() throws Exception {
        // Initialize the database
        mTriggerEffectRangeRepository.saveAndFlush(mTriggerEffectRange);

        // Get all the mTriggerEffectRangeList where targetActionCommand is not null
        defaultMTriggerEffectRangeShouldBeFound("targetActionCommand.specified=true");

        // Get all the mTriggerEffectRangeList where targetActionCommand is null
        defaultMTriggerEffectRangeShouldNotBeFound("targetActionCommand.specified=false");
    }

    @Test
    @Transactional
    public void getAllMTriggerEffectRangesByTargetActionCommandIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mTriggerEffectRangeRepository.saveAndFlush(mTriggerEffectRange);

        // Get all the mTriggerEffectRangeList where targetActionCommand greater than or equals to DEFAULT_TARGET_ACTION_COMMAND
        defaultMTriggerEffectRangeShouldBeFound("targetActionCommand.greaterOrEqualThan=" + DEFAULT_TARGET_ACTION_COMMAND);

        // Get all the mTriggerEffectRangeList where targetActionCommand greater than or equals to UPDATED_TARGET_ACTION_COMMAND
        defaultMTriggerEffectRangeShouldNotBeFound("targetActionCommand.greaterOrEqualThan=" + UPDATED_TARGET_ACTION_COMMAND);
    }

    @Test
    @Transactional
    public void getAllMTriggerEffectRangesByTargetActionCommandIsLessThanSomething() throws Exception {
        // Initialize the database
        mTriggerEffectRangeRepository.saveAndFlush(mTriggerEffectRange);

        // Get all the mTriggerEffectRangeList where targetActionCommand less than or equals to DEFAULT_TARGET_ACTION_COMMAND
        defaultMTriggerEffectRangeShouldNotBeFound("targetActionCommand.lessThan=" + DEFAULT_TARGET_ACTION_COMMAND);

        // Get all the mTriggerEffectRangeList where targetActionCommand less than or equals to UPDATED_TARGET_ACTION_COMMAND
        defaultMTriggerEffectRangeShouldBeFound("targetActionCommand.lessThan=" + UPDATED_TARGET_ACTION_COMMAND);
    }


    @Test
    @Transactional
    public void getAllMTriggerEffectRangesByMTriggerEffectBaseIsEqualToSomething() throws Exception {
        // Initialize the database
        MTriggerEffectBase mTriggerEffectBase = MTriggerEffectBaseResourceIT.createEntity(em);
        em.persist(mTriggerEffectBase);
        em.flush();
        mTriggerEffectRange.addMTriggerEffectBase(mTriggerEffectBase);
        mTriggerEffectRangeRepository.saveAndFlush(mTriggerEffectRange);
        Long mTriggerEffectBaseId = mTriggerEffectBase.getId();

        // Get all the mTriggerEffectRangeList where mTriggerEffectBase equals to mTriggerEffectBaseId
        defaultMTriggerEffectRangeShouldBeFound("mTriggerEffectBaseId.equals=" + mTriggerEffectBaseId);

        // Get all the mTriggerEffectRangeList where mTriggerEffectBase equals to mTriggerEffectBaseId + 1
        defaultMTriggerEffectRangeShouldNotBeFound("mTriggerEffectBaseId.equals=" + (mTriggerEffectBaseId + 1));
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultMTriggerEffectRangeShouldBeFound(String filter) throws Exception {
        restMTriggerEffectRangeMockMvc.perform(get("/api/m-trigger-effect-ranges?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(mTriggerEffectRange.getId().intValue())))
            .andExpect(jsonPath("$.[*].effectType").value(hasItem(DEFAULT_EFFECT_TYPE)))
            .andExpect(jsonPath("$.[*].targetPositionGk").value(hasItem(DEFAULT_TARGET_POSITION_GK)))
            .andExpect(jsonPath("$.[*].targetPositionFw").value(hasItem(DEFAULT_TARGET_POSITION_FW)))
            .andExpect(jsonPath("$.[*].targetPositionOmf").value(hasItem(DEFAULT_TARGET_POSITION_OMF)))
            .andExpect(jsonPath("$.[*].targetPositionDmf").value(hasItem(DEFAULT_TARGET_POSITION_DMF)))
            .andExpect(jsonPath("$.[*].targetPositionDf").value(hasItem(DEFAULT_TARGET_POSITION_DF)))
            .andExpect(jsonPath("$.[*].targetAttribute").value(hasItem(DEFAULT_TARGET_ATTRIBUTE)))
            .andExpect(jsonPath("$.[*].targetNationalityGroupId").value(hasItem(DEFAULT_TARGET_NATIONALITY_GROUP_ID)))
            .andExpect(jsonPath("$.[*].targetTeamGroupId").value(hasItem(DEFAULT_TARGET_TEAM_GROUP_ID)))
            .andExpect(jsonPath("$.[*].targetCharacterGroupId").value(hasItem(DEFAULT_TARGET_CHARACTER_GROUP_ID)))
            .andExpect(jsonPath("$.[*].targetFormationGroupId").value(hasItem(DEFAULT_TARGET_FORMATION_GROUP_ID)))
            .andExpect(jsonPath("$.[*].targetActionCommand").value(hasItem(DEFAULT_TARGET_ACTION_COMMAND)));

        // Check, that the count call also returns 1
        restMTriggerEffectRangeMockMvc.perform(get("/api/m-trigger-effect-ranges/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultMTriggerEffectRangeShouldNotBeFound(String filter) throws Exception {
        restMTriggerEffectRangeMockMvc.perform(get("/api/m-trigger-effect-ranges?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restMTriggerEffectRangeMockMvc.perform(get("/api/m-trigger-effect-ranges/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("0"));
    }


    @Test
    @Transactional
    public void getNonExistingMTriggerEffectRange() throws Exception {
        // Get the mTriggerEffectRange
        restMTriggerEffectRangeMockMvc.perform(get("/api/m-trigger-effect-ranges/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateMTriggerEffectRange() throws Exception {
        // Initialize the database
        mTriggerEffectRangeRepository.saveAndFlush(mTriggerEffectRange);

        int databaseSizeBeforeUpdate = mTriggerEffectRangeRepository.findAll().size();

        // Update the mTriggerEffectRange
        MTriggerEffectRange updatedMTriggerEffectRange = mTriggerEffectRangeRepository.findById(mTriggerEffectRange.getId()).get();
        // Disconnect from session so that the updates on updatedMTriggerEffectRange are not directly saved in db
        em.detach(updatedMTriggerEffectRange);
        updatedMTriggerEffectRange
            .effectType(UPDATED_EFFECT_TYPE)
            .targetPositionGk(UPDATED_TARGET_POSITION_GK)
            .targetPositionFw(UPDATED_TARGET_POSITION_FW)
            .targetPositionOmf(UPDATED_TARGET_POSITION_OMF)
            .targetPositionDmf(UPDATED_TARGET_POSITION_DMF)
            .targetPositionDf(UPDATED_TARGET_POSITION_DF)
            .targetAttribute(UPDATED_TARGET_ATTRIBUTE)
            .targetNationalityGroupId(UPDATED_TARGET_NATIONALITY_GROUP_ID)
            .targetTeamGroupId(UPDATED_TARGET_TEAM_GROUP_ID)
            .targetCharacterGroupId(UPDATED_TARGET_CHARACTER_GROUP_ID)
            .targetFormationGroupId(UPDATED_TARGET_FORMATION_GROUP_ID)
            .targetActionCommand(UPDATED_TARGET_ACTION_COMMAND);
        MTriggerEffectRangeDTO mTriggerEffectRangeDTO = mTriggerEffectRangeMapper.toDto(updatedMTriggerEffectRange);

        restMTriggerEffectRangeMockMvc.perform(put("/api/m-trigger-effect-ranges")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mTriggerEffectRangeDTO)))
            .andExpect(status().isOk());

        // Validate the MTriggerEffectRange in the database
        List<MTriggerEffectRange> mTriggerEffectRangeList = mTriggerEffectRangeRepository.findAll();
        assertThat(mTriggerEffectRangeList).hasSize(databaseSizeBeforeUpdate);
        MTriggerEffectRange testMTriggerEffectRange = mTriggerEffectRangeList.get(mTriggerEffectRangeList.size() - 1);
        assertThat(testMTriggerEffectRange.getEffectType()).isEqualTo(UPDATED_EFFECT_TYPE);
        assertThat(testMTriggerEffectRange.getTargetPositionGk()).isEqualTo(UPDATED_TARGET_POSITION_GK);
        assertThat(testMTriggerEffectRange.getTargetPositionFw()).isEqualTo(UPDATED_TARGET_POSITION_FW);
        assertThat(testMTriggerEffectRange.getTargetPositionOmf()).isEqualTo(UPDATED_TARGET_POSITION_OMF);
        assertThat(testMTriggerEffectRange.getTargetPositionDmf()).isEqualTo(UPDATED_TARGET_POSITION_DMF);
        assertThat(testMTriggerEffectRange.getTargetPositionDf()).isEqualTo(UPDATED_TARGET_POSITION_DF);
        assertThat(testMTriggerEffectRange.getTargetAttribute()).isEqualTo(UPDATED_TARGET_ATTRIBUTE);
        assertThat(testMTriggerEffectRange.getTargetNationalityGroupId()).isEqualTo(UPDATED_TARGET_NATIONALITY_GROUP_ID);
        assertThat(testMTriggerEffectRange.getTargetTeamGroupId()).isEqualTo(UPDATED_TARGET_TEAM_GROUP_ID);
        assertThat(testMTriggerEffectRange.getTargetCharacterGroupId()).isEqualTo(UPDATED_TARGET_CHARACTER_GROUP_ID);
        assertThat(testMTriggerEffectRange.getTargetFormationGroupId()).isEqualTo(UPDATED_TARGET_FORMATION_GROUP_ID);
        assertThat(testMTriggerEffectRange.getTargetActionCommand()).isEqualTo(UPDATED_TARGET_ACTION_COMMAND);
    }

    @Test
    @Transactional
    public void updateNonExistingMTriggerEffectRange() throws Exception {
        int databaseSizeBeforeUpdate = mTriggerEffectRangeRepository.findAll().size();

        // Create the MTriggerEffectRange
        MTriggerEffectRangeDTO mTriggerEffectRangeDTO = mTriggerEffectRangeMapper.toDto(mTriggerEffectRange);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restMTriggerEffectRangeMockMvc.perform(put("/api/m-trigger-effect-ranges")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mTriggerEffectRangeDTO)))
            .andExpect(status().isBadRequest());

        // Validate the MTriggerEffectRange in the database
        List<MTriggerEffectRange> mTriggerEffectRangeList = mTriggerEffectRangeRepository.findAll();
        assertThat(mTriggerEffectRangeList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteMTriggerEffectRange() throws Exception {
        // Initialize the database
        mTriggerEffectRangeRepository.saveAndFlush(mTriggerEffectRange);

        int databaseSizeBeforeDelete = mTriggerEffectRangeRepository.findAll().size();

        // Delete the mTriggerEffectRange
        restMTriggerEffectRangeMockMvc.perform(delete("/api/m-trigger-effect-ranges/{id}", mTriggerEffectRange.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database is empty
        List<MTriggerEffectRange> mTriggerEffectRangeList = mTriggerEffectRangeRepository.findAll();
        assertThat(mTriggerEffectRangeList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(MTriggerEffectRange.class);
        MTriggerEffectRange mTriggerEffectRange1 = new MTriggerEffectRange();
        mTriggerEffectRange1.setId(1L);
        MTriggerEffectRange mTriggerEffectRange2 = new MTriggerEffectRange();
        mTriggerEffectRange2.setId(mTriggerEffectRange1.getId());
        assertThat(mTriggerEffectRange1).isEqualTo(mTriggerEffectRange2);
        mTriggerEffectRange2.setId(2L);
        assertThat(mTriggerEffectRange1).isNotEqualTo(mTriggerEffectRange2);
        mTriggerEffectRange1.setId(null);
        assertThat(mTriggerEffectRange1).isNotEqualTo(mTriggerEffectRange2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(MTriggerEffectRangeDTO.class);
        MTriggerEffectRangeDTO mTriggerEffectRangeDTO1 = new MTriggerEffectRangeDTO();
        mTriggerEffectRangeDTO1.setId(1L);
        MTriggerEffectRangeDTO mTriggerEffectRangeDTO2 = new MTriggerEffectRangeDTO();
        assertThat(mTriggerEffectRangeDTO1).isNotEqualTo(mTriggerEffectRangeDTO2);
        mTriggerEffectRangeDTO2.setId(mTriggerEffectRangeDTO1.getId());
        assertThat(mTriggerEffectRangeDTO1).isEqualTo(mTriggerEffectRangeDTO2);
        mTriggerEffectRangeDTO2.setId(2L);
        assertThat(mTriggerEffectRangeDTO1).isNotEqualTo(mTriggerEffectRangeDTO2);
        mTriggerEffectRangeDTO1.setId(null);
        assertThat(mTriggerEffectRangeDTO1).isNotEqualTo(mTriggerEffectRangeDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(mTriggerEffectRangeMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(mTriggerEffectRangeMapper.fromId(null)).isNull();
    }
}
