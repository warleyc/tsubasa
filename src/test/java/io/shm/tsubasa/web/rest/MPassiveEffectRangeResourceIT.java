package io.shm.tsubasa.web.rest;

import io.shm.tsubasa.TsubasaApp;
import io.shm.tsubasa.domain.MPassiveEffectRange;
import io.shm.tsubasa.domain.MFormation;
import io.shm.tsubasa.domain.MMatchOption;
import io.shm.tsubasa.domain.MTeamEffectBase;
import io.shm.tsubasa.repository.MPassiveEffectRangeRepository;
import io.shm.tsubasa.service.MPassiveEffectRangeService;
import io.shm.tsubasa.service.dto.MPassiveEffectRangeDTO;
import io.shm.tsubasa.service.mapper.MPassiveEffectRangeMapper;
import io.shm.tsubasa.web.rest.errors.ExceptionTranslator;
import io.shm.tsubasa.service.dto.MPassiveEffectRangeCriteria;
import io.shm.tsubasa.service.MPassiveEffectRangeQueryService;

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
 * Integration tests for the {@Link MPassiveEffectRangeResource} REST controller.
 */
@SpringBootTest(classes = TsubasaApp.class)
public class MPassiveEffectRangeResourceIT {

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final Integer DEFAULT_EFFECT_PARAM_TYPE = 1;
    private static final Integer UPDATED_EFFECT_PARAM_TYPE = 2;

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

    private static final Integer DEFAULT_TARGET_PLAYABLE_CARD_GROUP_ID = 1;
    private static final Integer UPDATED_TARGET_PLAYABLE_CARD_GROUP_ID = 2;

    private static final String DEFAULT_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPTION = "BBBBBBBBBB";

    @Autowired
    private MPassiveEffectRangeRepository mPassiveEffectRangeRepository;

    @Autowired
    private MPassiveEffectRangeMapper mPassiveEffectRangeMapper;

    @Autowired
    private MPassiveEffectRangeService mPassiveEffectRangeService;

    @Autowired
    private MPassiveEffectRangeQueryService mPassiveEffectRangeQueryService;

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

    private MockMvc restMPassiveEffectRangeMockMvc;

    private MPassiveEffectRange mPassiveEffectRange;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final MPassiveEffectRangeResource mPassiveEffectRangeResource = new MPassiveEffectRangeResource(mPassiveEffectRangeService, mPassiveEffectRangeQueryService);
        this.restMPassiveEffectRangeMockMvc = MockMvcBuilders.standaloneSetup(mPassiveEffectRangeResource)
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
    public static MPassiveEffectRange createEntity(EntityManager em) {
        MPassiveEffectRange mPassiveEffectRange = new MPassiveEffectRange()
            .name(DEFAULT_NAME)
            .effectParamType(DEFAULT_EFFECT_PARAM_TYPE)
            .targetPositionGk(DEFAULT_TARGET_POSITION_GK)
            .targetPositionFw(DEFAULT_TARGET_POSITION_FW)
            .targetPositionOmf(DEFAULT_TARGET_POSITION_OMF)
            .targetPositionDmf(DEFAULT_TARGET_POSITION_DMF)
            .targetPositionDf(DEFAULT_TARGET_POSITION_DF)
            .targetAttribute(DEFAULT_TARGET_ATTRIBUTE)
            .targetNationalityGroupId(DEFAULT_TARGET_NATIONALITY_GROUP_ID)
            .targetTeamGroupId(DEFAULT_TARGET_TEAM_GROUP_ID)
            .targetPlayableCardGroupId(DEFAULT_TARGET_PLAYABLE_CARD_GROUP_ID)
            .description(DEFAULT_DESCRIPTION);
        return mPassiveEffectRange;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static MPassiveEffectRange createUpdatedEntity(EntityManager em) {
        MPassiveEffectRange mPassiveEffectRange = new MPassiveEffectRange()
            .name(UPDATED_NAME)
            .effectParamType(UPDATED_EFFECT_PARAM_TYPE)
            .targetPositionGk(UPDATED_TARGET_POSITION_GK)
            .targetPositionFw(UPDATED_TARGET_POSITION_FW)
            .targetPositionOmf(UPDATED_TARGET_POSITION_OMF)
            .targetPositionDmf(UPDATED_TARGET_POSITION_DMF)
            .targetPositionDf(UPDATED_TARGET_POSITION_DF)
            .targetAttribute(UPDATED_TARGET_ATTRIBUTE)
            .targetNationalityGroupId(UPDATED_TARGET_NATIONALITY_GROUP_ID)
            .targetTeamGroupId(UPDATED_TARGET_TEAM_GROUP_ID)
            .targetPlayableCardGroupId(UPDATED_TARGET_PLAYABLE_CARD_GROUP_ID)
            .description(UPDATED_DESCRIPTION);
        return mPassiveEffectRange;
    }

    @BeforeEach
    public void initTest() {
        mPassiveEffectRange = createEntity(em);
    }

    @Test
    @Transactional
    public void createMPassiveEffectRange() throws Exception {
        int databaseSizeBeforeCreate = mPassiveEffectRangeRepository.findAll().size();

        // Create the MPassiveEffectRange
        MPassiveEffectRangeDTO mPassiveEffectRangeDTO = mPassiveEffectRangeMapper.toDto(mPassiveEffectRange);
        restMPassiveEffectRangeMockMvc.perform(post("/api/m-passive-effect-ranges")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mPassiveEffectRangeDTO)))
            .andExpect(status().isCreated());

        // Validate the MPassiveEffectRange in the database
        List<MPassiveEffectRange> mPassiveEffectRangeList = mPassiveEffectRangeRepository.findAll();
        assertThat(mPassiveEffectRangeList).hasSize(databaseSizeBeforeCreate + 1);
        MPassiveEffectRange testMPassiveEffectRange = mPassiveEffectRangeList.get(mPassiveEffectRangeList.size() - 1);
        assertThat(testMPassiveEffectRange.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testMPassiveEffectRange.getEffectParamType()).isEqualTo(DEFAULT_EFFECT_PARAM_TYPE);
        assertThat(testMPassiveEffectRange.getTargetPositionGk()).isEqualTo(DEFAULT_TARGET_POSITION_GK);
        assertThat(testMPassiveEffectRange.getTargetPositionFw()).isEqualTo(DEFAULT_TARGET_POSITION_FW);
        assertThat(testMPassiveEffectRange.getTargetPositionOmf()).isEqualTo(DEFAULT_TARGET_POSITION_OMF);
        assertThat(testMPassiveEffectRange.getTargetPositionDmf()).isEqualTo(DEFAULT_TARGET_POSITION_DMF);
        assertThat(testMPassiveEffectRange.getTargetPositionDf()).isEqualTo(DEFAULT_TARGET_POSITION_DF);
        assertThat(testMPassiveEffectRange.getTargetAttribute()).isEqualTo(DEFAULT_TARGET_ATTRIBUTE);
        assertThat(testMPassiveEffectRange.getTargetNationalityGroupId()).isEqualTo(DEFAULT_TARGET_NATIONALITY_GROUP_ID);
        assertThat(testMPassiveEffectRange.getTargetTeamGroupId()).isEqualTo(DEFAULT_TARGET_TEAM_GROUP_ID);
        assertThat(testMPassiveEffectRange.getTargetPlayableCardGroupId()).isEqualTo(DEFAULT_TARGET_PLAYABLE_CARD_GROUP_ID);
        assertThat(testMPassiveEffectRange.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
    }

    @Test
    @Transactional
    public void createMPassiveEffectRangeWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = mPassiveEffectRangeRepository.findAll().size();

        // Create the MPassiveEffectRange with an existing ID
        mPassiveEffectRange.setId(1L);
        MPassiveEffectRangeDTO mPassiveEffectRangeDTO = mPassiveEffectRangeMapper.toDto(mPassiveEffectRange);

        // An entity with an existing ID cannot be created, so this API call must fail
        restMPassiveEffectRangeMockMvc.perform(post("/api/m-passive-effect-ranges")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mPassiveEffectRangeDTO)))
            .andExpect(status().isBadRequest());

        // Validate the MPassiveEffectRange in the database
        List<MPassiveEffectRange> mPassiveEffectRangeList = mPassiveEffectRangeRepository.findAll();
        assertThat(mPassiveEffectRangeList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkEffectParamTypeIsRequired() throws Exception {
        int databaseSizeBeforeTest = mPassiveEffectRangeRepository.findAll().size();
        // set the field null
        mPassiveEffectRange.setEffectParamType(null);

        // Create the MPassiveEffectRange, which fails.
        MPassiveEffectRangeDTO mPassiveEffectRangeDTO = mPassiveEffectRangeMapper.toDto(mPassiveEffectRange);

        restMPassiveEffectRangeMockMvc.perform(post("/api/m-passive-effect-ranges")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mPassiveEffectRangeDTO)))
            .andExpect(status().isBadRequest());

        List<MPassiveEffectRange> mPassiveEffectRangeList = mPassiveEffectRangeRepository.findAll();
        assertThat(mPassiveEffectRangeList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkTargetPositionGkIsRequired() throws Exception {
        int databaseSizeBeforeTest = mPassiveEffectRangeRepository.findAll().size();
        // set the field null
        mPassiveEffectRange.setTargetPositionGk(null);

        // Create the MPassiveEffectRange, which fails.
        MPassiveEffectRangeDTO mPassiveEffectRangeDTO = mPassiveEffectRangeMapper.toDto(mPassiveEffectRange);

        restMPassiveEffectRangeMockMvc.perform(post("/api/m-passive-effect-ranges")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mPassiveEffectRangeDTO)))
            .andExpect(status().isBadRequest());

        List<MPassiveEffectRange> mPassiveEffectRangeList = mPassiveEffectRangeRepository.findAll();
        assertThat(mPassiveEffectRangeList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkTargetPositionFwIsRequired() throws Exception {
        int databaseSizeBeforeTest = mPassiveEffectRangeRepository.findAll().size();
        // set the field null
        mPassiveEffectRange.setTargetPositionFw(null);

        // Create the MPassiveEffectRange, which fails.
        MPassiveEffectRangeDTO mPassiveEffectRangeDTO = mPassiveEffectRangeMapper.toDto(mPassiveEffectRange);

        restMPassiveEffectRangeMockMvc.perform(post("/api/m-passive-effect-ranges")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mPassiveEffectRangeDTO)))
            .andExpect(status().isBadRequest());

        List<MPassiveEffectRange> mPassiveEffectRangeList = mPassiveEffectRangeRepository.findAll();
        assertThat(mPassiveEffectRangeList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkTargetPositionOmfIsRequired() throws Exception {
        int databaseSizeBeforeTest = mPassiveEffectRangeRepository.findAll().size();
        // set the field null
        mPassiveEffectRange.setTargetPositionOmf(null);

        // Create the MPassiveEffectRange, which fails.
        MPassiveEffectRangeDTO mPassiveEffectRangeDTO = mPassiveEffectRangeMapper.toDto(mPassiveEffectRange);

        restMPassiveEffectRangeMockMvc.perform(post("/api/m-passive-effect-ranges")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mPassiveEffectRangeDTO)))
            .andExpect(status().isBadRequest());

        List<MPassiveEffectRange> mPassiveEffectRangeList = mPassiveEffectRangeRepository.findAll();
        assertThat(mPassiveEffectRangeList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkTargetPositionDmfIsRequired() throws Exception {
        int databaseSizeBeforeTest = mPassiveEffectRangeRepository.findAll().size();
        // set the field null
        mPassiveEffectRange.setTargetPositionDmf(null);

        // Create the MPassiveEffectRange, which fails.
        MPassiveEffectRangeDTO mPassiveEffectRangeDTO = mPassiveEffectRangeMapper.toDto(mPassiveEffectRange);

        restMPassiveEffectRangeMockMvc.perform(post("/api/m-passive-effect-ranges")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mPassiveEffectRangeDTO)))
            .andExpect(status().isBadRequest());

        List<MPassiveEffectRange> mPassiveEffectRangeList = mPassiveEffectRangeRepository.findAll();
        assertThat(mPassiveEffectRangeList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkTargetPositionDfIsRequired() throws Exception {
        int databaseSizeBeforeTest = mPassiveEffectRangeRepository.findAll().size();
        // set the field null
        mPassiveEffectRange.setTargetPositionDf(null);

        // Create the MPassiveEffectRange, which fails.
        MPassiveEffectRangeDTO mPassiveEffectRangeDTO = mPassiveEffectRangeMapper.toDto(mPassiveEffectRange);

        restMPassiveEffectRangeMockMvc.perform(post("/api/m-passive-effect-ranges")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mPassiveEffectRangeDTO)))
            .andExpect(status().isBadRequest());

        List<MPassiveEffectRange> mPassiveEffectRangeList = mPassiveEffectRangeRepository.findAll();
        assertThat(mPassiveEffectRangeList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkTargetAttributeIsRequired() throws Exception {
        int databaseSizeBeforeTest = mPassiveEffectRangeRepository.findAll().size();
        // set the field null
        mPassiveEffectRange.setTargetAttribute(null);

        // Create the MPassiveEffectRange, which fails.
        MPassiveEffectRangeDTO mPassiveEffectRangeDTO = mPassiveEffectRangeMapper.toDto(mPassiveEffectRange);

        restMPassiveEffectRangeMockMvc.perform(post("/api/m-passive-effect-ranges")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mPassiveEffectRangeDTO)))
            .andExpect(status().isBadRequest());

        List<MPassiveEffectRange> mPassiveEffectRangeList = mPassiveEffectRangeRepository.findAll();
        assertThat(mPassiveEffectRangeList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllMPassiveEffectRanges() throws Exception {
        // Initialize the database
        mPassiveEffectRangeRepository.saveAndFlush(mPassiveEffectRange);

        // Get all the mPassiveEffectRangeList
        restMPassiveEffectRangeMockMvc.perform(get("/api/m-passive-effect-ranges?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(mPassiveEffectRange.getId().intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME.toString())))
            .andExpect(jsonPath("$.[*].effectParamType").value(hasItem(DEFAULT_EFFECT_PARAM_TYPE)))
            .andExpect(jsonPath("$.[*].targetPositionGk").value(hasItem(DEFAULT_TARGET_POSITION_GK)))
            .andExpect(jsonPath("$.[*].targetPositionFw").value(hasItem(DEFAULT_TARGET_POSITION_FW)))
            .andExpect(jsonPath("$.[*].targetPositionOmf").value(hasItem(DEFAULT_TARGET_POSITION_OMF)))
            .andExpect(jsonPath("$.[*].targetPositionDmf").value(hasItem(DEFAULT_TARGET_POSITION_DMF)))
            .andExpect(jsonPath("$.[*].targetPositionDf").value(hasItem(DEFAULT_TARGET_POSITION_DF)))
            .andExpect(jsonPath("$.[*].targetAttribute").value(hasItem(DEFAULT_TARGET_ATTRIBUTE)))
            .andExpect(jsonPath("$.[*].targetNationalityGroupId").value(hasItem(DEFAULT_TARGET_NATIONALITY_GROUP_ID)))
            .andExpect(jsonPath("$.[*].targetTeamGroupId").value(hasItem(DEFAULT_TARGET_TEAM_GROUP_ID)))
            .andExpect(jsonPath("$.[*].targetPlayableCardGroupId").value(hasItem(DEFAULT_TARGET_PLAYABLE_CARD_GROUP_ID)))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION.toString())));
    }
    
    @Test
    @Transactional
    public void getMPassiveEffectRange() throws Exception {
        // Initialize the database
        mPassiveEffectRangeRepository.saveAndFlush(mPassiveEffectRange);

        // Get the mPassiveEffectRange
        restMPassiveEffectRangeMockMvc.perform(get("/api/m-passive-effect-ranges/{id}", mPassiveEffectRange.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(mPassiveEffectRange.getId().intValue()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME.toString()))
            .andExpect(jsonPath("$.effectParamType").value(DEFAULT_EFFECT_PARAM_TYPE))
            .andExpect(jsonPath("$.targetPositionGk").value(DEFAULT_TARGET_POSITION_GK))
            .andExpect(jsonPath("$.targetPositionFw").value(DEFAULT_TARGET_POSITION_FW))
            .andExpect(jsonPath("$.targetPositionOmf").value(DEFAULT_TARGET_POSITION_OMF))
            .andExpect(jsonPath("$.targetPositionDmf").value(DEFAULT_TARGET_POSITION_DMF))
            .andExpect(jsonPath("$.targetPositionDf").value(DEFAULT_TARGET_POSITION_DF))
            .andExpect(jsonPath("$.targetAttribute").value(DEFAULT_TARGET_ATTRIBUTE))
            .andExpect(jsonPath("$.targetNationalityGroupId").value(DEFAULT_TARGET_NATIONALITY_GROUP_ID))
            .andExpect(jsonPath("$.targetTeamGroupId").value(DEFAULT_TARGET_TEAM_GROUP_ID))
            .andExpect(jsonPath("$.targetPlayableCardGroupId").value(DEFAULT_TARGET_PLAYABLE_CARD_GROUP_ID))
            .andExpect(jsonPath("$.description").value(DEFAULT_DESCRIPTION.toString()));
    }

    @Test
    @Transactional
    public void getAllMPassiveEffectRangesByEffectParamTypeIsEqualToSomething() throws Exception {
        // Initialize the database
        mPassiveEffectRangeRepository.saveAndFlush(mPassiveEffectRange);

        // Get all the mPassiveEffectRangeList where effectParamType equals to DEFAULT_EFFECT_PARAM_TYPE
        defaultMPassiveEffectRangeShouldBeFound("effectParamType.equals=" + DEFAULT_EFFECT_PARAM_TYPE);

        // Get all the mPassiveEffectRangeList where effectParamType equals to UPDATED_EFFECT_PARAM_TYPE
        defaultMPassiveEffectRangeShouldNotBeFound("effectParamType.equals=" + UPDATED_EFFECT_PARAM_TYPE);
    }

    @Test
    @Transactional
    public void getAllMPassiveEffectRangesByEffectParamTypeIsInShouldWork() throws Exception {
        // Initialize the database
        mPassiveEffectRangeRepository.saveAndFlush(mPassiveEffectRange);

        // Get all the mPassiveEffectRangeList where effectParamType in DEFAULT_EFFECT_PARAM_TYPE or UPDATED_EFFECT_PARAM_TYPE
        defaultMPassiveEffectRangeShouldBeFound("effectParamType.in=" + DEFAULT_EFFECT_PARAM_TYPE + "," + UPDATED_EFFECT_PARAM_TYPE);

        // Get all the mPassiveEffectRangeList where effectParamType equals to UPDATED_EFFECT_PARAM_TYPE
        defaultMPassiveEffectRangeShouldNotBeFound("effectParamType.in=" + UPDATED_EFFECT_PARAM_TYPE);
    }

    @Test
    @Transactional
    public void getAllMPassiveEffectRangesByEffectParamTypeIsNullOrNotNull() throws Exception {
        // Initialize the database
        mPassiveEffectRangeRepository.saveAndFlush(mPassiveEffectRange);

        // Get all the mPassiveEffectRangeList where effectParamType is not null
        defaultMPassiveEffectRangeShouldBeFound("effectParamType.specified=true");

        // Get all the mPassiveEffectRangeList where effectParamType is null
        defaultMPassiveEffectRangeShouldNotBeFound("effectParamType.specified=false");
    }

    @Test
    @Transactional
    public void getAllMPassiveEffectRangesByEffectParamTypeIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mPassiveEffectRangeRepository.saveAndFlush(mPassiveEffectRange);

        // Get all the mPassiveEffectRangeList where effectParamType greater than or equals to DEFAULT_EFFECT_PARAM_TYPE
        defaultMPassiveEffectRangeShouldBeFound("effectParamType.greaterOrEqualThan=" + DEFAULT_EFFECT_PARAM_TYPE);

        // Get all the mPassiveEffectRangeList where effectParamType greater than or equals to UPDATED_EFFECT_PARAM_TYPE
        defaultMPassiveEffectRangeShouldNotBeFound("effectParamType.greaterOrEqualThan=" + UPDATED_EFFECT_PARAM_TYPE);
    }

    @Test
    @Transactional
    public void getAllMPassiveEffectRangesByEffectParamTypeIsLessThanSomething() throws Exception {
        // Initialize the database
        mPassiveEffectRangeRepository.saveAndFlush(mPassiveEffectRange);

        // Get all the mPassiveEffectRangeList where effectParamType less than or equals to DEFAULT_EFFECT_PARAM_TYPE
        defaultMPassiveEffectRangeShouldNotBeFound("effectParamType.lessThan=" + DEFAULT_EFFECT_PARAM_TYPE);

        // Get all the mPassiveEffectRangeList where effectParamType less than or equals to UPDATED_EFFECT_PARAM_TYPE
        defaultMPassiveEffectRangeShouldBeFound("effectParamType.lessThan=" + UPDATED_EFFECT_PARAM_TYPE);
    }


    @Test
    @Transactional
    public void getAllMPassiveEffectRangesByTargetPositionGkIsEqualToSomething() throws Exception {
        // Initialize the database
        mPassiveEffectRangeRepository.saveAndFlush(mPassiveEffectRange);

        // Get all the mPassiveEffectRangeList where targetPositionGk equals to DEFAULT_TARGET_POSITION_GK
        defaultMPassiveEffectRangeShouldBeFound("targetPositionGk.equals=" + DEFAULT_TARGET_POSITION_GK);

        // Get all the mPassiveEffectRangeList where targetPositionGk equals to UPDATED_TARGET_POSITION_GK
        defaultMPassiveEffectRangeShouldNotBeFound("targetPositionGk.equals=" + UPDATED_TARGET_POSITION_GK);
    }

    @Test
    @Transactional
    public void getAllMPassiveEffectRangesByTargetPositionGkIsInShouldWork() throws Exception {
        // Initialize the database
        mPassiveEffectRangeRepository.saveAndFlush(mPassiveEffectRange);

        // Get all the mPassiveEffectRangeList where targetPositionGk in DEFAULT_TARGET_POSITION_GK or UPDATED_TARGET_POSITION_GK
        defaultMPassiveEffectRangeShouldBeFound("targetPositionGk.in=" + DEFAULT_TARGET_POSITION_GK + "," + UPDATED_TARGET_POSITION_GK);

        // Get all the mPassiveEffectRangeList where targetPositionGk equals to UPDATED_TARGET_POSITION_GK
        defaultMPassiveEffectRangeShouldNotBeFound("targetPositionGk.in=" + UPDATED_TARGET_POSITION_GK);
    }

    @Test
    @Transactional
    public void getAllMPassiveEffectRangesByTargetPositionGkIsNullOrNotNull() throws Exception {
        // Initialize the database
        mPassiveEffectRangeRepository.saveAndFlush(mPassiveEffectRange);

        // Get all the mPassiveEffectRangeList where targetPositionGk is not null
        defaultMPassiveEffectRangeShouldBeFound("targetPositionGk.specified=true");

        // Get all the mPassiveEffectRangeList where targetPositionGk is null
        defaultMPassiveEffectRangeShouldNotBeFound("targetPositionGk.specified=false");
    }

    @Test
    @Transactional
    public void getAllMPassiveEffectRangesByTargetPositionGkIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mPassiveEffectRangeRepository.saveAndFlush(mPassiveEffectRange);

        // Get all the mPassiveEffectRangeList where targetPositionGk greater than or equals to DEFAULT_TARGET_POSITION_GK
        defaultMPassiveEffectRangeShouldBeFound("targetPositionGk.greaterOrEqualThan=" + DEFAULT_TARGET_POSITION_GK);

        // Get all the mPassiveEffectRangeList where targetPositionGk greater than or equals to UPDATED_TARGET_POSITION_GK
        defaultMPassiveEffectRangeShouldNotBeFound("targetPositionGk.greaterOrEqualThan=" + UPDATED_TARGET_POSITION_GK);
    }

    @Test
    @Transactional
    public void getAllMPassiveEffectRangesByTargetPositionGkIsLessThanSomething() throws Exception {
        // Initialize the database
        mPassiveEffectRangeRepository.saveAndFlush(mPassiveEffectRange);

        // Get all the mPassiveEffectRangeList where targetPositionGk less than or equals to DEFAULT_TARGET_POSITION_GK
        defaultMPassiveEffectRangeShouldNotBeFound("targetPositionGk.lessThan=" + DEFAULT_TARGET_POSITION_GK);

        // Get all the mPassiveEffectRangeList where targetPositionGk less than or equals to UPDATED_TARGET_POSITION_GK
        defaultMPassiveEffectRangeShouldBeFound("targetPositionGk.lessThan=" + UPDATED_TARGET_POSITION_GK);
    }


    @Test
    @Transactional
    public void getAllMPassiveEffectRangesByTargetPositionFwIsEqualToSomething() throws Exception {
        // Initialize the database
        mPassiveEffectRangeRepository.saveAndFlush(mPassiveEffectRange);

        // Get all the mPassiveEffectRangeList where targetPositionFw equals to DEFAULT_TARGET_POSITION_FW
        defaultMPassiveEffectRangeShouldBeFound("targetPositionFw.equals=" + DEFAULT_TARGET_POSITION_FW);

        // Get all the mPassiveEffectRangeList where targetPositionFw equals to UPDATED_TARGET_POSITION_FW
        defaultMPassiveEffectRangeShouldNotBeFound("targetPositionFw.equals=" + UPDATED_TARGET_POSITION_FW);
    }

    @Test
    @Transactional
    public void getAllMPassiveEffectRangesByTargetPositionFwIsInShouldWork() throws Exception {
        // Initialize the database
        mPassiveEffectRangeRepository.saveAndFlush(mPassiveEffectRange);

        // Get all the mPassiveEffectRangeList where targetPositionFw in DEFAULT_TARGET_POSITION_FW or UPDATED_TARGET_POSITION_FW
        defaultMPassiveEffectRangeShouldBeFound("targetPositionFw.in=" + DEFAULT_TARGET_POSITION_FW + "," + UPDATED_TARGET_POSITION_FW);

        // Get all the mPassiveEffectRangeList where targetPositionFw equals to UPDATED_TARGET_POSITION_FW
        defaultMPassiveEffectRangeShouldNotBeFound("targetPositionFw.in=" + UPDATED_TARGET_POSITION_FW);
    }

    @Test
    @Transactional
    public void getAllMPassiveEffectRangesByTargetPositionFwIsNullOrNotNull() throws Exception {
        // Initialize the database
        mPassiveEffectRangeRepository.saveAndFlush(mPassiveEffectRange);

        // Get all the mPassiveEffectRangeList where targetPositionFw is not null
        defaultMPassiveEffectRangeShouldBeFound("targetPositionFw.specified=true");

        // Get all the mPassiveEffectRangeList where targetPositionFw is null
        defaultMPassiveEffectRangeShouldNotBeFound("targetPositionFw.specified=false");
    }

    @Test
    @Transactional
    public void getAllMPassiveEffectRangesByTargetPositionFwIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mPassiveEffectRangeRepository.saveAndFlush(mPassiveEffectRange);

        // Get all the mPassiveEffectRangeList where targetPositionFw greater than or equals to DEFAULT_TARGET_POSITION_FW
        defaultMPassiveEffectRangeShouldBeFound("targetPositionFw.greaterOrEqualThan=" + DEFAULT_TARGET_POSITION_FW);

        // Get all the mPassiveEffectRangeList where targetPositionFw greater than or equals to UPDATED_TARGET_POSITION_FW
        defaultMPassiveEffectRangeShouldNotBeFound("targetPositionFw.greaterOrEqualThan=" + UPDATED_TARGET_POSITION_FW);
    }

    @Test
    @Transactional
    public void getAllMPassiveEffectRangesByTargetPositionFwIsLessThanSomething() throws Exception {
        // Initialize the database
        mPassiveEffectRangeRepository.saveAndFlush(mPassiveEffectRange);

        // Get all the mPassiveEffectRangeList where targetPositionFw less than or equals to DEFAULT_TARGET_POSITION_FW
        defaultMPassiveEffectRangeShouldNotBeFound("targetPositionFw.lessThan=" + DEFAULT_TARGET_POSITION_FW);

        // Get all the mPassiveEffectRangeList where targetPositionFw less than or equals to UPDATED_TARGET_POSITION_FW
        defaultMPassiveEffectRangeShouldBeFound("targetPositionFw.lessThan=" + UPDATED_TARGET_POSITION_FW);
    }


    @Test
    @Transactional
    public void getAllMPassiveEffectRangesByTargetPositionOmfIsEqualToSomething() throws Exception {
        // Initialize the database
        mPassiveEffectRangeRepository.saveAndFlush(mPassiveEffectRange);

        // Get all the mPassiveEffectRangeList where targetPositionOmf equals to DEFAULT_TARGET_POSITION_OMF
        defaultMPassiveEffectRangeShouldBeFound("targetPositionOmf.equals=" + DEFAULT_TARGET_POSITION_OMF);

        // Get all the mPassiveEffectRangeList where targetPositionOmf equals to UPDATED_TARGET_POSITION_OMF
        defaultMPassiveEffectRangeShouldNotBeFound("targetPositionOmf.equals=" + UPDATED_TARGET_POSITION_OMF);
    }

    @Test
    @Transactional
    public void getAllMPassiveEffectRangesByTargetPositionOmfIsInShouldWork() throws Exception {
        // Initialize the database
        mPassiveEffectRangeRepository.saveAndFlush(mPassiveEffectRange);

        // Get all the mPassiveEffectRangeList where targetPositionOmf in DEFAULT_TARGET_POSITION_OMF or UPDATED_TARGET_POSITION_OMF
        defaultMPassiveEffectRangeShouldBeFound("targetPositionOmf.in=" + DEFAULT_TARGET_POSITION_OMF + "," + UPDATED_TARGET_POSITION_OMF);

        // Get all the mPassiveEffectRangeList where targetPositionOmf equals to UPDATED_TARGET_POSITION_OMF
        defaultMPassiveEffectRangeShouldNotBeFound("targetPositionOmf.in=" + UPDATED_TARGET_POSITION_OMF);
    }

    @Test
    @Transactional
    public void getAllMPassiveEffectRangesByTargetPositionOmfIsNullOrNotNull() throws Exception {
        // Initialize the database
        mPassiveEffectRangeRepository.saveAndFlush(mPassiveEffectRange);

        // Get all the mPassiveEffectRangeList where targetPositionOmf is not null
        defaultMPassiveEffectRangeShouldBeFound("targetPositionOmf.specified=true");

        // Get all the mPassiveEffectRangeList where targetPositionOmf is null
        defaultMPassiveEffectRangeShouldNotBeFound("targetPositionOmf.specified=false");
    }

    @Test
    @Transactional
    public void getAllMPassiveEffectRangesByTargetPositionOmfIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mPassiveEffectRangeRepository.saveAndFlush(mPassiveEffectRange);

        // Get all the mPassiveEffectRangeList where targetPositionOmf greater than or equals to DEFAULT_TARGET_POSITION_OMF
        defaultMPassiveEffectRangeShouldBeFound("targetPositionOmf.greaterOrEqualThan=" + DEFAULT_TARGET_POSITION_OMF);

        // Get all the mPassiveEffectRangeList where targetPositionOmf greater than or equals to UPDATED_TARGET_POSITION_OMF
        defaultMPassiveEffectRangeShouldNotBeFound("targetPositionOmf.greaterOrEqualThan=" + UPDATED_TARGET_POSITION_OMF);
    }

    @Test
    @Transactional
    public void getAllMPassiveEffectRangesByTargetPositionOmfIsLessThanSomething() throws Exception {
        // Initialize the database
        mPassiveEffectRangeRepository.saveAndFlush(mPassiveEffectRange);

        // Get all the mPassiveEffectRangeList where targetPositionOmf less than or equals to DEFAULT_TARGET_POSITION_OMF
        defaultMPassiveEffectRangeShouldNotBeFound("targetPositionOmf.lessThan=" + DEFAULT_TARGET_POSITION_OMF);

        // Get all the mPassiveEffectRangeList where targetPositionOmf less than or equals to UPDATED_TARGET_POSITION_OMF
        defaultMPassiveEffectRangeShouldBeFound("targetPositionOmf.lessThan=" + UPDATED_TARGET_POSITION_OMF);
    }


    @Test
    @Transactional
    public void getAllMPassiveEffectRangesByTargetPositionDmfIsEqualToSomething() throws Exception {
        // Initialize the database
        mPassiveEffectRangeRepository.saveAndFlush(mPassiveEffectRange);

        // Get all the mPassiveEffectRangeList where targetPositionDmf equals to DEFAULT_TARGET_POSITION_DMF
        defaultMPassiveEffectRangeShouldBeFound("targetPositionDmf.equals=" + DEFAULT_TARGET_POSITION_DMF);

        // Get all the mPassiveEffectRangeList where targetPositionDmf equals to UPDATED_TARGET_POSITION_DMF
        defaultMPassiveEffectRangeShouldNotBeFound("targetPositionDmf.equals=" + UPDATED_TARGET_POSITION_DMF);
    }

    @Test
    @Transactional
    public void getAllMPassiveEffectRangesByTargetPositionDmfIsInShouldWork() throws Exception {
        // Initialize the database
        mPassiveEffectRangeRepository.saveAndFlush(mPassiveEffectRange);

        // Get all the mPassiveEffectRangeList where targetPositionDmf in DEFAULT_TARGET_POSITION_DMF or UPDATED_TARGET_POSITION_DMF
        defaultMPassiveEffectRangeShouldBeFound("targetPositionDmf.in=" + DEFAULT_TARGET_POSITION_DMF + "," + UPDATED_TARGET_POSITION_DMF);

        // Get all the mPassiveEffectRangeList where targetPositionDmf equals to UPDATED_TARGET_POSITION_DMF
        defaultMPassiveEffectRangeShouldNotBeFound("targetPositionDmf.in=" + UPDATED_TARGET_POSITION_DMF);
    }

    @Test
    @Transactional
    public void getAllMPassiveEffectRangesByTargetPositionDmfIsNullOrNotNull() throws Exception {
        // Initialize the database
        mPassiveEffectRangeRepository.saveAndFlush(mPassiveEffectRange);

        // Get all the mPassiveEffectRangeList where targetPositionDmf is not null
        defaultMPassiveEffectRangeShouldBeFound("targetPositionDmf.specified=true");

        // Get all the mPassiveEffectRangeList where targetPositionDmf is null
        defaultMPassiveEffectRangeShouldNotBeFound("targetPositionDmf.specified=false");
    }

    @Test
    @Transactional
    public void getAllMPassiveEffectRangesByTargetPositionDmfIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mPassiveEffectRangeRepository.saveAndFlush(mPassiveEffectRange);

        // Get all the mPassiveEffectRangeList where targetPositionDmf greater than or equals to DEFAULT_TARGET_POSITION_DMF
        defaultMPassiveEffectRangeShouldBeFound("targetPositionDmf.greaterOrEqualThan=" + DEFAULT_TARGET_POSITION_DMF);

        // Get all the mPassiveEffectRangeList where targetPositionDmf greater than or equals to UPDATED_TARGET_POSITION_DMF
        defaultMPassiveEffectRangeShouldNotBeFound("targetPositionDmf.greaterOrEqualThan=" + UPDATED_TARGET_POSITION_DMF);
    }

    @Test
    @Transactional
    public void getAllMPassiveEffectRangesByTargetPositionDmfIsLessThanSomething() throws Exception {
        // Initialize the database
        mPassiveEffectRangeRepository.saveAndFlush(mPassiveEffectRange);

        // Get all the mPassiveEffectRangeList where targetPositionDmf less than or equals to DEFAULT_TARGET_POSITION_DMF
        defaultMPassiveEffectRangeShouldNotBeFound("targetPositionDmf.lessThan=" + DEFAULT_TARGET_POSITION_DMF);

        // Get all the mPassiveEffectRangeList where targetPositionDmf less than or equals to UPDATED_TARGET_POSITION_DMF
        defaultMPassiveEffectRangeShouldBeFound("targetPositionDmf.lessThan=" + UPDATED_TARGET_POSITION_DMF);
    }


    @Test
    @Transactional
    public void getAllMPassiveEffectRangesByTargetPositionDfIsEqualToSomething() throws Exception {
        // Initialize the database
        mPassiveEffectRangeRepository.saveAndFlush(mPassiveEffectRange);

        // Get all the mPassiveEffectRangeList where targetPositionDf equals to DEFAULT_TARGET_POSITION_DF
        defaultMPassiveEffectRangeShouldBeFound("targetPositionDf.equals=" + DEFAULT_TARGET_POSITION_DF);

        // Get all the mPassiveEffectRangeList where targetPositionDf equals to UPDATED_TARGET_POSITION_DF
        defaultMPassiveEffectRangeShouldNotBeFound("targetPositionDf.equals=" + UPDATED_TARGET_POSITION_DF);
    }

    @Test
    @Transactional
    public void getAllMPassiveEffectRangesByTargetPositionDfIsInShouldWork() throws Exception {
        // Initialize the database
        mPassiveEffectRangeRepository.saveAndFlush(mPassiveEffectRange);

        // Get all the mPassiveEffectRangeList where targetPositionDf in DEFAULT_TARGET_POSITION_DF or UPDATED_TARGET_POSITION_DF
        defaultMPassiveEffectRangeShouldBeFound("targetPositionDf.in=" + DEFAULT_TARGET_POSITION_DF + "," + UPDATED_TARGET_POSITION_DF);

        // Get all the mPassiveEffectRangeList where targetPositionDf equals to UPDATED_TARGET_POSITION_DF
        defaultMPassiveEffectRangeShouldNotBeFound("targetPositionDf.in=" + UPDATED_TARGET_POSITION_DF);
    }

    @Test
    @Transactional
    public void getAllMPassiveEffectRangesByTargetPositionDfIsNullOrNotNull() throws Exception {
        // Initialize the database
        mPassiveEffectRangeRepository.saveAndFlush(mPassiveEffectRange);

        // Get all the mPassiveEffectRangeList where targetPositionDf is not null
        defaultMPassiveEffectRangeShouldBeFound("targetPositionDf.specified=true");

        // Get all the mPassiveEffectRangeList where targetPositionDf is null
        defaultMPassiveEffectRangeShouldNotBeFound("targetPositionDf.specified=false");
    }

    @Test
    @Transactional
    public void getAllMPassiveEffectRangesByTargetPositionDfIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mPassiveEffectRangeRepository.saveAndFlush(mPassiveEffectRange);

        // Get all the mPassiveEffectRangeList where targetPositionDf greater than or equals to DEFAULT_TARGET_POSITION_DF
        defaultMPassiveEffectRangeShouldBeFound("targetPositionDf.greaterOrEqualThan=" + DEFAULT_TARGET_POSITION_DF);

        // Get all the mPassiveEffectRangeList where targetPositionDf greater than or equals to UPDATED_TARGET_POSITION_DF
        defaultMPassiveEffectRangeShouldNotBeFound("targetPositionDf.greaterOrEqualThan=" + UPDATED_TARGET_POSITION_DF);
    }

    @Test
    @Transactional
    public void getAllMPassiveEffectRangesByTargetPositionDfIsLessThanSomething() throws Exception {
        // Initialize the database
        mPassiveEffectRangeRepository.saveAndFlush(mPassiveEffectRange);

        // Get all the mPassiveEffectRangeList where targetPositionDf less than or equals to DEFAULT_TARGET_POSITION_DF
        defaultMPassiveEffectRangeShouldNotBeFound("targetPositionDf.lessThan=" + DEFAULT_TARGET_POSITION_DF);

        // Get all the mPassiveEffectRangeList where targetPositionDf less than or equals to UPDATED_TARGET_POSITION_DF
        defaultMPassiveEffectRangeShouldBeFound("targetPositionDf.lessThan=" + UPDATED_TARGET_POSITION_DF);
    }


    @Test
    @Transactional
    public void getAllMPassiveEffectRangesByTargetAttributeIsEqualToSomething() throws Exception {
        // Initialize the database
        mPassiveEffectRangeRepository.saveAndFlush(mPassiveEffectRange);

        // Get all the mPassiveEffectRangeList where targetAttribute equals to DEFAULT_TARGET_ATTRIBUTE
        defaultMPassiveEffectRangeShouldBeFound("targetAttribute.equals=" + DEFAULT_TARGET_ATTRIBUTE);

        // Get all the mPassiveEffectRangeList where targetAttribute equals to UPDATED_TARGET_ATTRIBUTE
        defaultMPassiveEffectRangeShouldNotBeFound("targetAttribute.equals=" + UPDATED_TARGET_ATTRIBUTE);
    }

    @Test
    @Transactional
    public void getAllMPassiveEffectRangesByTargetAttributeIsInShouldWork() throws Exception {
        // Initialize the database
        mPassiveEffectRangeRepository.saveAndFlush(mPassiveEffectRange);

        // Get all the mPassiveEffectRangeList where targetAttribute in DEFAULT_TARGET_ATTRIBUTE or UPDATED_TARGET_ATTRIBUTE
        defaultMPassiveEffectRangeShouldBeFound("targetAttribute.in=" + DEFAULT_TARGET_ATTRIBUTE + "," + UPDATED_TARGET_ATTRIBUTE);

        // Get all the mPassiveEffectRangeList where targetAttribute equals to UPDATED_TARGET_ATTRIBUTE
        defaultMPassiveEffectRangeShouldNotBeFound("targetAttribute.in=" + UPDATED_TARGET_ATTRIBUTE);
    }

    @Test
    @Transactional
    public void getAllMPassiveEffectRangesByTargetAttributeIsNullOrNotNull() throws Exception {
        // Initialize the database
        mPassiveEffectRangeRepository.saveAndFlush(mPassiveEffectRange);

        // Get all the mPassiveEffectRangeList where targetAttribute is not null
        defaultMPassiveEffectRangeShouldBeFound("targetAttribute.specified=true");

        // Get all the mPassiveEffectRangeList where targetAttribute is null
        defaultMPassiveEffectRangeShouldNotBeFound("targetAttribute.specified=false");
    }

    @Test
    @Transactional
    public void getAllMPassiveEffectRangesByTargetAttributeIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mPassiveEffectRangeRepository.saveAndFlush(mPassiveEffectRange);

        // Get all the mPassiveEffectRangeList where targetAttribute greater than or equals to DEFAULT_TARGET_ATTRIBUTE
        defaultMPassiveEffectRangeShouldBeFound("targetAttribute.greaterOrEqualThan=" + DEFAULT_TARGET_ATTRIBUTE);

        // Get all the mPassiveEffectRangeList where targetAttribute greater than or equals to UPDATED_TARGET_ATTRIBUTE
        defaultMPassiveEffectRangeShouldNotBeFound("targetAttribute.greaterOrEqualThan=" + UPDATED_TARGET_ATTRIBUTE);
    }

    @Test
    @Transactional
    public void getAllMPassiveEffectRangesByTargetAttributeIsLessThanSomething() throws Exception {
        // Initialize the database
        mPassiveEffectRangeRepository.saveAndFlush(mPassiveEffectRange);

        // Get all the mPassiveEffectRangeList where targetAttribute less than or equals to DEFAULT_TARGET_ATTRIBUTE
        defaultMPassiveEffectRangeShouldNotBeFound("targetAttribute.lessThan=" + DEFAULT_TARGET_ATTRIBUTE);

        // Get all the mPassiveEffectRangeList where targetAttribute less than or equals to UPDATED_TARGET_ATTRIBUTE
        defaultMPassiveEffectRangeShouldBeFound("targetAttribute.lessThan=" + UPDATED_TARGET_ATTRIBUTE);
    }


    @Test
    @Transactional
    public void getAllMPassiveEffectRangesByTargetNationalityGroupIdIsEqualToSomething() throws Exception {
        // Initialize the database
        mPassiveEffectRangeRepository.saveAndFlush(mPassiveEffectRange);

        // Get all the mPassiveEffectRangeList where targetNationalityGroupId equals to DEFAULT_TARGET_NATIONALITY_GROUP_ID
        defaultMPassiveEffectRangeShouldBeFound("targetNationalityGroupId.equals=" + DEFAULT_TARGET_NATIONALITY_GROUP_ID);

        // Get all the mPassiveEffectRangeList where targetNationalityGroupId equals to UPDATED_TARGET_NATIONALITY_GROUP_ID
        defaultMPassiveEffectRangeShouldNotBeFound("targetNationalityGroupId.equals=" + UPDATED_TARGET_NATIONALITY_GROUP_ID);
    }

    @Test
    @Transactional
    public void getAllMPassiveEffectRangesByTargetNationalityGroupIdIsInShouldWork() throws Exception {
        // Initialize the database
        mPassiveEffectRangeRepository.saveAndFlush(mPassiveEffectRange);

        // Get all the mPassiveEffectRangeList where targetNationalityGroupId in DEFAULT_TARGET_NATIONALITY_GROUP_ID or UPDATED_TARGET_NATIONALITY_GROUP_ID
        defaultMPassiveEffectRangeShouldBeFound("targetNationalityGroupId.in=" + DEFAULT_TARGET_NATIONALITY_GROUP_ID + "," + UPDATED_TARGET_NATIONALITY_GROUP_ID);

        // Get all the mPassiveEffectRangeList where targetNationalityGroupId equals to UPDATED_TARGET_NATIONALITY_GROUP_ID
        defaultMPassiveEffectRangeShouldNotBeFound("targetNationalityGroupId.in=" + UPDATED_TARGET_NATIONALITY_GROUP_ID);
    }

    @Test
    @Transactional
    public void getAllMPassiveEffectRangesByTargetNationalityGroupIdIsNullOrNotNull() throws Exception {
        // Initialize the database
        mPassiveEffectRangeRepository.saveAndFlush(mPassiveEffectRange);

        // Get all the mPassiveEffectRangeList where targetNationalityGroupId is not null
        defaultMPassiveEffectRangeShouldBeFound("targetNationalityGroupId.specified=true");

        // Get all the mPassiveEffectRangeList where targetNationalityGroupId is null
        defaultMPassiveEffectRangeShouldNotBeFound("targetNationalityGroupId.specified=false");
    }

    @Test
    @Transactional
    public void getAllMPassiveEffectRangesByTargetNationalityGroupIdIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mPassiveEffectRangeRepository.saveAndFlush(mPassiveEffectRange);

        // Get all the mPassiveEffectRangeList where targetNationalityGroupId greater than or equals to DEFAULT_TARGET_NATIONALITY_GROUP_ID
        defaultMPassiveEffectRangeShouldBeFound("targetNationalityGroupId.greaterOrEqualThan=" + DEFAULT_TARGET_NATIONALITY_GROUP_ID);

        // Get all the mPassiveEffectRangeList where targetNationalityGroupId greater than or equals to UPDATED_TARGET_NATIONALITY_GROUP_ID
        defaultMPassiveEffectRangeShouldNotBeFound("targetNationalityGroupId.greaterOrEqualThan=" + UPDATED_TARGET_NATIONALITY_GROUP_ID);
    }

    @Test
    @Transactional
    public void getAllMPassiveEffectRangesByTargetNationalityGroupIdIsLessThanSomething() throws Exception {
        // Initialize the database
        mPassiveEffectRangeRepository.saveAndFlush(mPassiveEffectRange);

        // Get all the mPassiveEffectRangeList where targetNationalityGroupId less than or equals to DEFAULT_TARGET_NATIONALITY_GROUP_ID
        defaultMPassiveEffectRangeShouldNotBeFound("targetNationalityGroupId.lessThan=" + DEFAULT_TARGET_NATIONALITY_GROUP_ID);

        // Get all the mPassiveEffectRangeList where targetNationalityGroupId less than or equals to UPDATED_TARGET_NATIONALITY_GROUP_ID
        defaultMPassiveEffectRangeShouldBeFound("targetNationalityGroupId.lessThan=" + UPDATED_TARGET_NATIONALITY_GROUP_ID);
    }


    @Test
    @Transactional
    public void getAllMPassiveEffectRangesByTargetTeamGroupIdIsEqualToSomething() throws Exception {
        // Initialize the database
        mPassiveEffectRangeRepository.saveAndFlush(mPassiveEffectRange);

        // Get all the mPassiveEffectRangeList where targetTeamGroupId equals to DEFAULT_TARGET_TEAM_GROUP_ID
        defaultMPassiveEffectRangeShouldBeFound("targetTeamGroupId.equals=" + DEFAULT_TARGET_TEAM_GROUP_ID);

        // Get all the mPassiveEffectRangeList where targetTeamGroupId equals to UPDATED_TARGET_TEAM_GROUP_ID
        defaultMPassiveEffectRangeShouldNotBeFound("targetTeamGroupId.equals=" + UPDATED_TARGET_TEAM_GROUP_ID);
    }

    @Test
    @Transactional
    public void getAllMPassiveEffectRangesByTargetTeamGroupIdIsInShouldWork() throws Exception {
        // Initialize the database
        mPassiveEffectRangeRepository.saveAndFlush(mPassiveEffectRange);

        // Get all the mPassiveEffectRangeList where targetTeamGroupId in DEFAULT_TARGET_TEAM_GROUP_ID or UPDATED_TARGET_TEAM_GROUP_ID
        defaultMPassiveEffectRangeShouldBeFound("targetTeamGroupId.in=" + DEFAULT_TARGET_TEAM_GROUP_ID + "," + UPDATED_TARGET_TEAM_GROUP_ID);

        // Get all the mPassiveEffectRangeList where targetTeamGroupId equals to UPDATED_TARGET_TEAM_GROUP_ID
        defaultMPassiveEffectRangeShouldNotBeFound("targetTeamGroupId.in=" + UPDATED_TARGET_TEAM_GROUP_ID);
    }

    @Test
    @Transactional
    public void getAllMPassiveEffectRangesByTargetTeamGroupIdIsNullOrNotNull() throws Exception {
        // Initialize the database
        mPassiveEffectRangeRepository.saveAndFlush(mPassiveEffectRange);

        // Get all the mPassiveEffectRangeList where targetTeamGroupId is not null
        defaultMPassiveEffectRangeShouldBeFound("targetTeamGroupId.specified=true");

        // Get all the mPassiveEffectRangeList where targetTeamGroupId is null
        defaultMPassiveEffectRangeShouldNotBeFound("targetTeamGroupId.specified=false");
    }

    @Test
    @Transactional
    public void getAllMPassiveEffectRangesByTargetTeamGroupIdIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mPassiveEffectRangeRepository.saveAndFlush(mPassiveEffectRange);

        // Get all the mPassiveEffectRangeList where targetTeamGroupId greater than or equals to DEFAULT_TARGET_TEAM_GROUP_ID
        defaultMPassiveEffectRangeShouldBeFound("targetTeamGroupId.greaterOrEqualThan=" + DEFAULT_TARGET_TEAM_GROUP_ID);

        // Get all the mPassiveEffectRangeList where targetTeamGroupId greater than or equals to UPDATED_TARGET_TEAM_GROUP_ID
        defaultMPassiveEffectRangeShouldNotBeFound("targetTeamGroupId.greaterOrEqualThan=" + UPDATED_TARGET_TEAM_GROUP_ID);
    }

    @Test
    @Transactional
    public void getAllMPassiveEffectRangesByTargetTeamGroupIdIsLessThanSomething() throws Exception {
        // Initialize the database
        mPassiveEffectRangeRepository.saveAndFlush(mPassiveEffectRange);

        // Get all the mPassiveEffectRangeList where targetTeamGroupId less than or equals to DEFAULT_TARGET_TEAM_GROUP_ID
        defaultMPassiveEffectRangeShouldNotBeFound("targetTeamGroupId.lessThan=" + DEFAULT_TARGET_TEAM_GROUP_ID);

        // Get all the mPassiveEffectRangeList where targetTeamGroupId less than or equals to UPDATED_TARGET_TEAM_GROUP_ID
        defaultMPassiveEffectRangeShouldBeFound("targetTeamGroupId.lessThan=" + UPDATED_TARGET_TEAM_GROUP_ID);
    }


    @Test
    @Transactional
    public void getAllMPassiveEffectRangesByTargetPlayableCardGroupIdIsEqualToSomething() throws Exception {
        // Initialize the database
        mPassiveEffectRangeRepository.saveAndFlush(mPassiveEffectRange);

        // Get all the mPassiveEffectRangeList where targetPlayableCardGroupId equals to DEFAULT_TARGET_PLAYABLE_CARD_GROUP_ID
        defaultMPassiveEffectRangeShouldBeFound("targetPlayableCardGroupId.equals=" + DEFAULT_TARGET_PLAYABLE_CARD_GROUP_ID);

        // Get all the mPassiveEffectRangeList where targetPlayableCardGroupId equals to UPDATED_TARGET_PLAYABLE_CARD_GROUP_ID
        defaultMPassiveEffectRangeShouldNotBeFound("targetPlayableCardGroupId.equals=" + UPDATED_TARGET_PLAYABLE_CARD_GROUP_ID);
    }

    @Test
    @Transactional
    public void getAllMPassiveEffectRangesByTargetPlayableCardGroupIdIsInShouldWork() throws Exception {
        // Initialize the database
        mPassiveEffectRangeRepository.saveAndFlush(mPassiveEffectRange);

        // Get all the mPassiveEffectRangeList where targetPlayableCardGroupId in DEFAULT_TARGET_PLAYABLE_CARD_GROUP_ID or UPDATED_TARGET_PLAYABLE_CARD_GROUP_ID
        defaultMPassiveEffectRangeShouldBeFound("targetPlayableCardGroupId.in=" + DEFAULT_TARGET_PLAYABLE_CARD_GROUP_ID + "," + UPDATED_TARGET_PLAYABLE_CARD_GROUP_ID);

        // Get all the mPassiveEffectRangeList where targetPlayableCardGroupId equals to UPDATED_TARGET_PLAYABLE_CARD_GROUP_ID
        defaultMPassiveEffectRangeShouldNotBeFound("targetPlayableCardGroupId.in=" + UPDATED_TARGET_PLAYABLE_CARD_GROUP_ID);
    }

    @Test
    @Transactional
    public void getAllMPassiveEffectRangesByTargetPlayableCardGroupIdIsNullOrNotNull() throws Exception {
        // Initialize the database
        mPassiveEffectRangeRepository.saveAndFlush(mPassiveEffectRange);

        // Get all the mPassiveEffectRangeList where targetPlayableCardGroupId is not null
        defaultMPassiveEffectRangeShouldBeFound("targetPlayableCardGroupId.specified=true");

        // Get all the mPassiveEffectRangeList where targetPlayableCardGroupId is null
        defaultMPassiveEffectRangeShouldNotBeFound("targetPlayableCardGroupId.specified=false");
    }

    @Test
    @Transactional
    public void getAllMPassiveEffectRangesByTargetPlayableCardGroupIdIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mPassiveEffectRangeRepository.saveAndFlush(mPassiveEffectRange);

        // Get all the mPassiveEffectRangeList where targetPlayableCardGroupId greater than or equals to DEFAULT_TARGET_PLAYABLE_CARD_GROUP_ID
        defaultMPassiveEffectRangeShouldBeFound("targetPlayableCardGroupId.greaterOrEqualThan=" + DEFAULT_TARGET_PLAYABLE_CARD_GROUP_ID);

        // Get all the mPassiveEffectRangeList where targetPlayableCardGroupId greater than or equals to UPDATED_TARGET_PLAYABLE_CARD_GROUP_ID
        defaultMPassiveEffectRangeShouldNotBeFound("targetPlayableCardGroupId.greaterOrEqualThan=" + UPDATED_TARGET_PLAYABLE_CARD_GROUP_ID);
    }

    @Test
    @Transactional
    public void getAllMPassiveEffectRangesByTargetPlayableCardGroupIdIsLessThanSomething() throws Exception {
        // Initialize the database
        mPassiveEffectRangeRepository.saveAndFlush(mPassiveEffectRange);

        // Get all the mPassiveEffectRangeList where targetPlayableCardGroupId less than or equals to DEFAULT_TARGET_PLAYABLE_CARD_GROUP_ID
        defaultMPassiveEffectRangeShouldNotBeFound("targetPlayableCardGroupId.lessThan=" + DEFAULT_TARGET_PLAYABLE_CARD_GROUP_ID);

        // Get all the mPassiveEffectRangeList where targetPlayableCardGroupId less than or equals to UPDATED_TARGET_PLAYABLE_CARD_GROUP_ID
        defaultMPassiveEffectRangeShouldBeFound("targetPlayableCardGroupId.lessThan=" + UPDATED_TARGET_PLAYABLE_CARD_GROUP_ID);
    }


    @Test
    @Transactional
    public void getAllMPassiveEffectRangesByMFormationIsEqualToSomething() throws Exception {
        // Initialize the database
        MFormation mFormation = MFormationResourceIT.createEntity(em);
        em.persist(mFormation);
        em.flush();
        mPassiveEffectRange.addMFormation(mFormation);
        mPassiveEffectRangeRepository.saveAndFlush(mPassiveEffectRange);
        Long mFormationId = mFormation.getId();

        // Get all the mPassiveEffectRangeList where mFormation equals to mFormationId
        defaultMPassiveEffectRangeShouldBeFound("mFormationId.equals=" + mFormationId);

        // Get all the mPassiveEffectRangeList where mFormation equals to mFormationId + 1
        defaultMPassiveEffectRangeShouldNotBeFound("mFormationId.equals=" + (mFormationId + 1));
    }


    @Test
    @Transactional
    public void getAllMPassiveEffectRangesByMMatchOptionIsEqualToSomething() throws Exception {
        // Initialize the database
        MMatchOption mMatchOption = MMatchOptionResourceIT.createEntity(em);
        em.persist(mMatchOption);
        em.flush();
        mPassiveEffectRange.addMMatchOption(mMatchOption);
        mPassiveEffectRangeRepository.saveAndFlush(mPassiveEffectRange);
        Long mMatchOptionId = mMatchOption.getId();

        // Get all the mPassiveEffectRangeList where mMatchOption equals to mMatchOptionId
        defaultMPassiveEffectRangeShouldBeFound("mMatchOptionId.equals=" + mMatchOptionId);

        // Get all the mPassiveEffectRangeList where mMatchOption equals to mMatchOptionId + 1
        defaultMPassiveEffectRangeShouldNotBeFound("mMatchOptionId.equals=" + (mMatchOptionId + 1));
    }


    @Test
    @Transactional
    public void getAllMPassiveEffectRangesByMTeamEffectBaseIsEqualToSomething() throws Exception {
        // Initialize the database
        MTeamEffectBase mTeamEffectBase = MTeamEffectBaseResourceIT.createEntity(em);
        em.persist(mTeamEffectBase);
        em.flush();
        mPassiveEffectRange.addMTeamEffectBase(mTeamEffectBase);
        mPassiveEffectRangeRepository.saveAndFlush(mPassiveEffectRange);
        Long mTeamEffectBaseId = mTeamEffectBase.getId();

        // Get all the mPassiveEffectRangeList where mTeamEffectBase equals to mTeamEffectBaseId
        defaultMPassiveEffectRangeShouldBeFound("mTeamEffectBaseId.equals=" + mTeamEffectBaseId);

        // Get all the mPassiveEffectRangeList where mTeamEffectBase equals to mTeamEffectBaseId + 1
        defaultMPassiveEffectRangeShouldNotBeFound("mTeamEffectBaseId.equals=" + (mTeamEffectBaseId + 1));
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultMPassiveEffectRangeShouldBeFound(String filter) throws Exception {
        restMPassiveEffectRangeMockMvc.perform(get("/api/m-passive-effect-ranges?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(mPassiveEffectRange.getId().intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME.toString())))
            .andExpect(jsonPath("$.[*].effectParamType").value(hasItem(DEFAULT_EFFECT_PARAM_TYPE)))
            .andExpect(jsonPath("$.[*].targetPositionGk").value(hasItem(DEFAULT_TARGET_POSITION_GK)))
            .andExpect(jsonPath("$.[*].targetPositionFw").value(hasItem(DEFAULT_TARGET_POSITION_FW)))
            .andExpect(jsonPath("$.[*].targetPositionOmf").value(hasItem(DEFAULT_TARGET_POSITION_OMF)))
            .andExpect(jsonPath("$.[*].targetPositionDmf").value(hasItem(DEFAULT_TARGET_POSITION_DMF)))
            .andExpect(jsonPath("$.[*].targetPositionDf").value(hasItem(DEFAULT_TARGET_POSITION_DF)))
            .andExpect(jsonPath("$.[*].targetAttribute").value(hasItem(DEFAULT_TARGET_ATTRIBUTE)))
            .andExpect(jsonPath("$.[*].targetNationalityGroupId").value(hasItem(DEFAULT_TARGET_NATIONALITY_GROUP_ID)))
            .andExpect(jsonPath("$.[*].targetTeamGroupId").value(hasItem(DEFAULT_TARGET_TEAM_GROUP_ID)))
            .andExpect(jsonPath("$.[*].targetPlayableCardGroupId").value(hasItem(DEFAULT_TARGET_PLAYABLE_CARD_GROUP_ID)))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION.toString())));

        // Check, that the count call also returns 1
        restMPassiveEffectRangeMockMvc.perform(get("/api/m-passive-effect-ranges/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultMPassiveEffectRangeShouldNotBeFound(String filter) throws Exception {
        restMPassiveEffectRangeMockMvc.perform(get("/api/m-passive-effect-ranges?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restMPassiveEffectRangeMockMvc.perform(get("/api/m-passive-effect-ranges/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("0"));
    }


    @Test
    @Transactional
    public void getNonExistingMPassiveEffectRange() throws Exception {
        // Get the mPassiveEffectRange
        restMPassiveEffectRangeMockMvc.perform(get("/api/m-passive-effect-ranges/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateMPassiveEffectRange() throws Exception {
        // Initialize the database
        mPassiveEffectRangeRepository.saveAndFlush(mPassiveEffectRange);

        int databaseSizeBeforeUpdate = mPassiveEffectRangeRepository.findAll().size();

        // Update the mPassiveEffectRange
        MPassiveEffectRange updatedMPassiveEffectRange = mPassiveEffectRangeRepository.findById(mPassiveEffectRange.getId()).get();
        // Disconnect from session so that the updates on updatedMPassiveEffectRange are not directly saved in db
        em.detach(updatedMPassiveEffectRange);
        updatedMPassiveEffectRange
            .name(UPDATED_NAME)
            .effectParamType(UPDATED_EFFECT_PARAM_TYPE)
            .targetPositionGk(UPDATED_TARGET_POSITION_GK)
            .targetPositionFw(UPDATED_TARGET_POSITION_FW)
            .targetPositionOmf(UPDATED_TARGET_POSITION_OMF)
            .targetPositionDmf(UPDATED_TARGET_POSITION_DMF)
            .targetPositionDf(UPDATED_TARGET_POSITION_DF)
            .targetAttribute(UPDATED_TARGET_ATTRIBUTE)
            .targetNationalityGroupId(UPDATED_TARGET_NATIONALITY_GROUP_ID)
            .targetTeamGroupId(UPDATED_TARGET_TEAM_GROUP_ID)
            .targetPlayableCardGroupId(UPDATED_TARGET_PLAYABLE_CARD_GROUP_ID)
            .description(UPDATED_DESCRIPTION);
        MPassiveEffectRangeDTO mPassiveEffectRangeDTO = mPassiveEffectRangeMapper.toDto(updatedMPassiveEffectRange);

        restMPassiveEffectRangeMockMvc.perform(put("/api/m-passive-effect-ranges")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mPassiveEffectRangeDTO)))
            .andExpect(status().isOk());

        // Validate the MPassiveEffectRange in the database
        List<MPassiveEffectRange> mPassiveEffectRangeList = mPassiveEffectRangeRepository.findAll();
        assertThat(mPassiveEffectRangeList).hasSize(databaseSizeBeforeUpdate);
        MPassiveEffectRange testMPassiveEffectRange = mPassiveEffectRangeList.get(mPassiveEffectRangeList.size() - 1);
        assertThat(testMPassiveEffectRange.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testMPassiveEffectRange.getEffectParamType()).isEqualTo(UPDATED_EFFECT_PARAM_TYPE);
        assertThat(testMPassiveEffectRange.getTargetPositionGk()).isEqualTo(UPDATED_TARGET_POSITION_GK);
        assertThat(testMPassiveEffectRange.getTargetPositionFw()).isEqualTo(UPDATED_TARGET_POSITION_FW);
        assertThat(testMPassiveEffectRange.getTargetPositionOmf()).isEqualTo(UPDATED_TARGET_POSITION_OMF);
        assertThat(testMPassiveEffectRange.getTargetPositionDmf()).isEqualTo(UPDATED_TARGET_POSITION_DMF);
        assertThat(testMPassiveEffectRange.getTargetPositionDf()).isEqualTo(UPDATED_TARGET_POSITION_DF);
        assertThat(testMPassiveEffectRange.getTargetAttribute()).isEqualTo(UPDATED_TARGET_ATTRIBUTE);
        assertThat(testMPassiveEffectRange.getTargetNationalityGroupId()).isEqualTo(UPDATED_TARGET_NATIONALITY_GROUP_ID);
        assertThat(testMPassiveEffectRange.getTargetTeamGroupId()).isEqualTo(UPDATED_TARGET_TEAM_GROUP_ID);
        assertThat(testMPassiveEffectRange.getTargetPlayableCardGroupId()).isEqualTo(UPDATED_TARGET_PLAYABLE_CARD_GROUP_ID);
        assertThat(testMPassiveEffectRange.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
    }

    @Test
    @Transactional
    public void updateNonExistingMPassiveEffectRange() throws Exception {
        int databaseSizeBeforeUpdate = mPassiveEffectRangeRepository.findAll().size();

        // Create the MPassiveEffectRange
        MPassiveEffectRangeDTO mPassiveEffectRangeDTO = mPassiveEffectRangeMapper.toDto(mPassiveEffectRange);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restMPassiveEffectRangeMockMvc.perform(put("/api/m-passive-effect-ranges")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mPassiveEffectRangeDTO)))
            .andExpect(status().isBadRequest());

        // Validate the MPassiveEffectRange in the database
        List<MPassiveEffectRange> mPassiveEffectRangeList = mPassiveEffectRangeRepository.findAll();
        assertThat(mPassiveEffectRangeList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteMPassiveEffectRange() throws Exception {
        // Initialize the database
        mPassiveEffectRangeRepository.saveAndFlush(mPassiveEffectRange);

        int databaseSizeBeforeDelete = mPassiveEffectRangeRepository.findAll().size();

        // Delete the mPassiveEffectRange
        restMPassiveEffectRangeMockMvc.perform(delete("/api/m-passive-effect-ranges/{id}", mPassiveEffectRange.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database is empty
        List<MPassiveEffectRange> mPassiveEffectRangeList = mPassiveEffectRangeRepository.findAll();
        assertThat(mPassiveEffectRangeList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(MPassiveEffectRange.class);
        MPassiveEffectRange mPassiveEffectRange1 = new MPassiveEffectRange();
        mPassiveEffectRange1.setId(1L);
        MPassiveEffectRange mPassiveEffectRange2 = new MPassiveEffectRange();
        mPassiveEffectRange2.setId(mPassiveEffectRange1.getId());
        assertThat(mPassiveEffectRange1).isEqualTo(mPassiveEffectRange2);
        mPassiveEffectRange2.setId(2L);
        assertThat(mPassiveEffectRange1).isNotEqualTo(mPassiveEffectRange2);
        mPassiveEffectRange1.setId(null);
        assertThat(mPassiveEffectRange1).isNotEqualTo(mPassiveEffectRange2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(MPassiveEffectRangeDTO.class);
        MPassiveEffectRangeDTO mPassiveEffectRangeDTO1 = new MPassiveEffectRangeDTO();
        mPassiveEffectRangeDTO1.setId(1L);
        MPassiveEffectRangeDTO mPassiveEffectRangeDTO2 = new MPassiveEffectRangeDTO();
        assertThat(mPassiveEffectRangeDTO1).isNotEqualTo(mPassiveEffectRangeDTO2);
        mPassiveEffectRangeDTO2.setId(mPassiveEffectRangeDTO1.getId());
        assertThat(mPassiveEffectRangeDTO1).isEqualTo(mPassiveEffectRangeDTO2);
        mPassiveEffectRangeDTO2.setId(2L);
        assertThat(mPassiveEffectRangeDTO1).isNotEqualTo(mPassiveEffectRangeDTO2);
        mPassiveEffectRangeDTO1.setId(null);
        assertThat(mPassiveEffectRangeDTO1).isNotEqualTo(mPassiveEffectRangeDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(mPassiveEffectRangeMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(mPassiveEffectRangeMapper.fromId(null)).isNull();
    }
}
