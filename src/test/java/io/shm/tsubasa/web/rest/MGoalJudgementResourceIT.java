package io.shm.tsubasa.web.rest;

import io.shm.tsubasa.TsubasaApp;
import io.shm.tsubasa.domain.MGoalJudgement;
import io.shm.tsubasa.repository.MGoalJudgementRepository;
import io.shm.tsubasa.service.MGoalJudgementService;
import io.shm.tsubasa.service.dto.MGoalJudgementDTO;
import io.shm.tsubasa.service.mapper.MGoalJudgementMapper;
import io.shm.tsubasa.web.rest.errors.ExceptionTranslator;
import io.shm.tsubasa.service.dto.MGoalJudgementCriteria;
import io.shm.tsubasa.service.MGoalJudgementQueryService;

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
 * Integration tests for the {@Link MGoalJudgementResource} REST controller.
 */
@SpringBootTest(classes = TsubasaApp.class)
public class MGoalJudgementResourceIT {

    private static final Integer DEFAULT_JUDGEMENT_ID = 1;
    private static final Integer UPDATED_JUDGEMENT_ID = 2;

    private static final Integer DEFAULT_ENCOUNTERS_TYPE = 1;
    private static final Integer UPDATED_ENCOUNTERS_TYPE = 2;

    private static final Integer DEFAULT_SUCCESS_RATE = 1;
    private static final Integer UPDATED_SUCCESS_RATE = 2;

    private static final Integer DEFAULT_GOAL_POST_RATE = 1;
    private static final Integer UPDATED_GOAL_POST_RATE = 2;

    private static final Integer DEFAULT_BALL_PUSH_RATE = 1;
    private static final Integer UPDATED_BALL_PUSH_RATE = 2;

    private static final Integer DEFAULT_CLEAR_RATE = 1;
    private static final Integer UPDATED_CLEAR_RATE = 2;

    @Autowired
    private MGoalJudgementRepository mGoalJudgementRepository;

    @Autowired
    private MGoalJudgementMapper mGoalJudgementMapper;

    @Autowired
    private MGoalJudgementService mGoalJudgementService;

    @Autowired
    private MGoalJudgementQueryService mGoalJudgementQueryService;

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

    private MockMvc restMGoalJudgementMockMvc;

    private MGoalJudgement mGoalJudgement;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final MGoalJudgementResource mGoalJudgementResource = new MGoalJudgementResource(mGoalJudgementService, mGoalJudgementQueryService);
        this.restMGoalJudgementMockMvc = MockMvcBuilders.standaloneSetup(mGoalJudgementResource)
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
    public static MGoalJudgement createEntity(EntityManager em) {
        MGoalJudgement mGoalJudgement = new MGoalJudgement()
            .judgementId(DEFAULT_JUDGEMENT_ID)
            .encountersType(DEFAULT_ENCOUNTERS_TYPE)
            .successRate(DEFAULT_SUCCESS_RATE)
            .goalPostRate(DEFAULT_GOAL_POST_RATE)
            .ballPushRate(DEFAULT_BALL_PUSH_RATE)
            .clearRate(DEFAULT_CLEAR_RATE);
        return mGoalJudgement;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static MGoalJudgement createUpdatedEntity(EntityManager em) {
        MGoalJudgement mGoalJudgement = new MGoalJudgement()
            .judgementId(UPDATED_JUDGEMENT_ID)
            .encountersType(UPDATED_ENCOUNTERS_TYPE)
            .successRate(UPDATED_SUCCESS_RATE)
            .goalPostRate(UPDATED_GOAL_POST_RATE)
            .ballPushRate(UPDATED_BALL_PUSH_RATE)
            .clearRate(UPDATED_CLEAR_RATE);
        return mGoalJudgement;
    }

    @BeforeEach
    public void initTest() {
        mGoalJudgement = createEntity(em);
    }

    @Test
    @Transactional
    public void createMGoalJudgement() throws Exception {
        int databaseSizeBeforeCreate = mGoalJudgementRepository.findAll().size();

        // Create the MGoalJudgement
        MGoalJudgementDTO mGoalJudgementDTO = mGoalJudgementMapper.toDto(mGoalJudgement);
        restMGoalJudgementMockMvc.perform(post("/api/m-goal-judgements")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mGoalJudgementDTO)))
            .andExpect(status().isCreated());

        // Validate the MGoalJudgement in the database
        List<MGoalJudgement> mGoalJudgementList = mGoalJudgementRepository.findAll();
        assertThat(mGoalJudgementList).hasSize(databaseSizeBeforeCreate + 1);
        MGoalJudgement testMGoalJudgement = mGoalJudgementList.get(mGoalJudgementList.size() - 1);
        assertThat(testMGoalJudgement.getJudgementId()).isEqualTo(DEFAULT_JUDGEMENT_ID);
        assertThat(testMGoalJudgement.getEncountersType()).isEqualTo(DEFAULT_ENCOUNTERS_TYPE);
        assertThat(testMGoalJudgement.getSuccessRate()).isEqualTo(DEFAULT_SUCCESS_RATE);
        assertThat(testMGoalJudgement.getGoalPostRate()).isEqualTo(DEFAULT_GOAL_POST_RATE);
        assertThat(testMGoalJudgement.getBallPushRate()).isEqualTo(DEFAULT_BALL_PUSH_RATE);
        assertThat(testMGoalJudgement.getClearRate()).isEqualTo(DEFAULT_CLEAR_RATE);
    }

    @Test
    @Transactional
    public void createMGoalJudgementWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = mGoalJudgementRepository.findAll().size();

        // Create the MGoalJudgement with an existing ID
        mGoalJudgement.setId(1L);
        MGoalJudgementDTO mGoalJudgementDTO = mGoalJudgementMapper.toDto(mGoalJudgement);

        // An entity with an existing ID cannot be created, so this API call must fail
        restMGoalJudgementMockMvc.perform(post("/api/m-goal-judgements")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mGoalJudgementDTO)))
            .andExpect(status().isBadRequest());

        // Validate the MGoalJudgement in the database
        List<MGoalJudgement> mGoalJudgementList = mGoalJudgementRepository.findAll();
        assertThat(mGoalJudgementList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkJudgementIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = mGoalJudgementRepository.findAll().size();
        // set the field null
        mGoalJudgement.setJudgementId(null);

        // Create the MGoalJudgement, which fails.
        MGoalJudgementDTO mGoalJudgementDTO = mGoalJudgementMapper.toDto(mGoalJudgement);

        restMGoalJudgementMockMvc.perform(post("/api/m-goal-judgements")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mGoalJudgementDTO)))
            .andExpect(status().isBadRequest());

        List<MGoalJudgement> mGoalJudgementList = mGoalJudgementRepository.findAll();
        assertThat(mGoalJudgementList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkEncountersTypeIsRequired() throws Exception {
        int databaseSizeBeforeTest = mGoalJudgementRepository.findAll().size();
        // set the field null
        mGoalJudgement.setEncountersType(null);

        // Create the MGoalJudgement, which fails.
        MGoalJudgementDTO mGoalJudgementDTO = mGoalJudgementMapper.toDto(mGoalJudgement);

        restMGoalJudgementMockMvc.perform(post("/api/m-goal-judgements")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mGoalJudgementDTO)))
            .andExpect(status().isBadRequest());

        List<MGoalJudgement> mGoalJudgementList = mGoalJudgementRepository.findAll();
        assertThat(mGoalJudgementList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkSuccessRateIsRequired() throws Exception {
        int databaseSizeBeforeTest = mGoalJudgementRepository.findAll().size();
        // set the field null
        mGoalJudgement.setSuccessRate(null);

        // Create the MGoalJudgement, which fails.
        MGoalJudgementDTO mGoalJudgementDTO = mGoalJudgementMapper.toDto(mGoalJudgement);

        restMGoalJudgementMockMvc.perform(post("/api/m-goal-judgements")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mGoalJudgementDTO)))
            .andExpect(status().isBadRequest());

        List<MGoalJudgement> mGoalJudgementList = mGoalJudgementRepository.findAll();
        assertThat(mGoalJudgementList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkGoalPostRateIsRequired() throws Exception {
        int databaseSizeBeforeTest = mGoalJudgementRepository.findAll().size();
        // set the field null
        mGoalJudgement.setGoalPostRate(null);

        // Create the MGoalJudgement, which fails.
        MGoalJudgementDTO mGoalJudgementDTO = mGoalJudgementMapper.toDto(mGoalJudgement);

        restMGoalJudgementMockMvc.perform(post("/api/m-goal-judgements")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mGoalJudgementDTO)))
            .andExpect(status().isBadRequest());

        List<MGoalJudgement> mGoalJudgementList = mGoalJudgementRepository.findAll();
        assertThat(mGoalJudgementList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkBallPushRateIsRequired() throws Exception {
        int databaseSizeBeforeTest = mGoalJudgementRepository.findAll().size();
        // set the field null
        mGoalJudgement.setBallPushRate(null);

        // Create the MGoalJudgement, which fails.
        MGoalJudgementDTO mGoalJudgementDTO = mGoalJudgementMapper.toDto(mGoalJudgement);

        restMGoalJudgementMockMvc.perform(post("/api/m-goal-judgements")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mGoalJudgementDTO)))
            .andExpect(status().isBadRequest());

        List<MGoalJudgement> mGoalJudgementList = mGoalJudgementRepository.findAll();
        assertThat(mGoalJudgementList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkClearRateIsRequired() throws Exception {
        int databaseSizeBeforeTest = mGoalJudgementRepository.findAll().size();
        // set the field null
        mGoalJudgement.setClearRate(null);

        // Create the MGoalJudgement, which fails.
        MGoalJudgementDTO mGoalJudgementDTO = mGoalJudgementMapper.toDto(mGoalJudgement);

        restMGoalJudgementMockMvc.perform(post("/api/m-goal-judgements")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mGoalJudgementDTO)))
            .andExpect(status().isBadRequest());

        List<MGoalJudgement> mGoalJudgementList = mGoalJudgementRepository.findAll();
        assertThat(mGoalJudgementList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllMGoalJudgements() throws Exception {
        // Initialize the database
        mGoalJudgementRepository.saveAndFlush(mGoalJudgement);

        // Get all the mGoalJudgementList
        restMGoalJudgementMockMvc.perform(get("/api/m-goal-judgements?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(mGoalJudgement.getId().intValue())))
            .andExpect(jsonPath("$.[*].judgementId").value(hasItem(DEFAULT_JUDGEMENT_ID)))
            .andExpect(jsonPath("$.[*].encountersType").value(hasItem(DEFAULT_ENCOUNTERS_TYPE)))
            .andExpect(jsonPath("$.[*].successRate").value(hasItem(DEFAULT_SUCCESS_RATE)))
            .andExpect(jsonPath("$.[*].goalPostRate").value(hasItem(DEFAULT_GOAL_POST_RATE)))
            .andExpect(jsonPath("$.[*].ballPushRate").value(hasItem(DEFAULT_BALL_PUSH_RATE)))
            .andExpect(jsonPath("$.[*].clearRate").value(hasItem(DEFAULT_CLEAR_RATE)));
    }
    
    @Test
    @Transactional
    public void getMGoalJudgement() throws Exception {
        // Initialize the database
        mGoalJudgementRepository.saveAndFlush(mGoalJudgement);

        // Get the mGoalJudgement
        restMGoalJudgementMockMvc.perform(get("/api/m-goal-judgements/{id}", mGoalJudgement.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(mGoalJudgement.getId().intValue()))
            .andExpect(jsonPath("$.judgementId").value(DEFAULT_JUDGEMENT_ID))
            .andExpect(jsonPath("$.encountersType").value(DEFAULT_ENCOUNTERS_TYPE))
            .andExpect(jsonPath("$.successRate").value(DEFAULT_SUCCESS_RATE))
            .andExpect(jsonPath("$.goalPostRate").value(DEFAULT_GOAL_POST_RATE))
            .andExpect(jsonPath("$.ballPushRate").value(DEFAULT_BALL_PUSH_RATE))
            .andExpect(jsonPath("$.clearRate").value(DEFAULT_CLEAR_RATE));
    }

    @Test
    @Transactional
    public void getAllMGoalJudgementsByJudgementIdIsEqualToSomething() throws Exception {
        // Initialize the database
        mGoalJudgementRepository.saveAndFlush(mGoalJudgement);

        // Get all the mGoalJudgementList where judgementId equals to DEFAULT_JUDGEMENT_ID
        defaultMGoalJudgementShouldBeFound("judgementId.equals=" + DEFAULT_JUDGEMENT_ID);

        // Get all the mGoalJudgementList where judgementId equals to UPDATED_JUDGEMENT_ID
        defaultMGoalJudgementShouldNotBeFound("judgementId.equals=" + UPDATED_JUDGEMENT_ID);
    }

    @Test
    @Transactional
    public void getAllMGoalJudgementsByJudgementIdIsInShouldWork() throws Exception {
        // Initialize the database
        mGoalJudgementRepository.saveAndFlush(mGoalJudgement);

        // Get all the mGoalJudgementList where judgementId in DEFAULT_JUDGEMENT_ID or UPDATED_JUDGEMENT_ID
        defaultMGoalJudgementShouldBeFound("judgementId.in=" + DEFAULT_JUDGEMENT_ID + "," + UPDATED_JUDGEMENT_ID);

        // Get all the mGoalJudgementList where judgementId equals to UPDATED_JUDGEMENT_ID
        defaultMGoalJudgementShouldNotBeFound("judgementId.in=" + UPDATED_JUDGEMENT_ID);
    }

    @Test
    @Transactional
    public void getAllMGoalJudgementsByJudgementIdIsNullOrNotNull() throws Exception {
        // Initialize the database
        mGoalJudgementRepository.saveAndFlush(mGoalJudgement);

        // Get all the mGoalJudgementList where judgementId is not null
        defaultMGoalJudgementShouldBeFound("judgementId.specified=true");

        // Get all the mGoalJudgementList where judgementId is null
        defaultMGoalJudgementShouldNotBeFound("judgementId.specified=false");
    }

    @Test
    @Transactional
    public void getAllMGoalJudgementsByJudgementIdIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mGoalJudgementRepository.saveAndFlush(mGoalJudgement);

        // Get all the mGoalJudgementList where judgementId greater than or equals to DEFAULT_JUDGEMENT_ID
        defaultMGoalJudgementShouldBeFound("judgementId.greaterOrEqualThan=" + DEFAULT_JUDGEMENT_ID);

        // Get all the mGoalJudgementList where judgementId greater than or equals to UPDATED_JUDGEMENT_ID
        defaultMGoalJudgementShouldNotBeFound("judgementId.greaterOrEqualThan=" + UPDATED_JUDGEMENT_ID);
    }

    @Test
    @Transactional
    public void getAllMGoalJudgementsByJudgementIdIsLessThanSomething() throws Exception {
        // Initialize the database
        mGoalJudgementRepository.saveAndFlush(mGoalJudgement);

        // Get all the mGoalJudgementList where judgementId less than or equals to DEFAULT_JUDGEMENT_ID
        defaultMGoalJudgementShouldNotBeFound("judgementId.lessThan=" + DEFAULT_JUDGEMENT_ID);

        // Get all the mGoalJudgementList where judgementId less than or equals to UPDATED_JUDGEMENT_ID
        defaultMGoalJudgementShouldBeFound("judgementId.lessThan=" + UPDATED_JUDGEMENT_ID);
    }


    @Test
    @Transactional
    public void getAllMGoalJudgementsByEncountersTypeIsEqualToSomething() throws Exception {
        // Initialize the database
        mGoalJudgementRepository.saveAndFlush(mGoalJudgement);

        // Get all the mGoalJudgementList where encountersType equals to DEFAULT_ENCOUNTERS_TYPE
        defaultMGoalJudgementShouldBeFound("encountersType.equals=" + DEFAULT_ENCOUNTERS_TYPE);

        // Get all the mGoalJudgementList where encountersType equals to UPDATED_ENCOUNTERS_TYPE
        defaultMGoalJudgementShouldNotBeFound("encountersType.equals=" + UPDATED_ENCOUNTERS_TYPE);
    }

    @Test
    @Transactional
    public void getAllMGoalJudgementsByEncountersTypeIsInShouldWork() throws Exception {
        // Initialize the database
        mGoalJudgementRepository.saveAndFlush(mGoalJudgement);

        // Get all the mGoalJudgementList where encountersType in DEFAULT_ENCOUNTERS_TYPE or UPDATED_ENCOUNTERS_TYPE
        defaultMGoalJudgementShouldBeFound("encountersType.in=" + DEFAULT_ENCOUNTERS_TYPE + "," + UPDATED_ENCOUNTERS_TYPE);

        // Get all the mGoalJudgementList where encountersType equals to UPDATED_ENCOUNTERS_TYPE
        defaultMGoalJudgementShouldNotBeFound("encountersType.in=" + UPDATED_ENCOUNTERS_TYPE);
    }

    @Test
    @Transactional
    public void getAllMGoalJudgementsByEncountersTypeIsNullOrNotNull() throws Exception {
        // Initialize the database
        mGoalJudgementRepository.saveAndFlush(mGoalJudgement);

        // Get all the mGoalJudgementList where encountersType is not null
        defaultMGoalJudgementShouldBeFound("encountersType.specified=true");

        // Get all the mGoalJudgementList where encountersType is null
        defaultMGoalJudgementShouldNotBeFound("encountersType.specified=false");
    }

    @Test
    @Transactional
    public void getAllMGoalJudgementsByEncountersTypeIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mGoalJudgementRepository.saveAndFlush(mGoalJudgement);

        // Get all the mGoalJudgementList where encountersType greater than or equals to DEFAULT_ENCOUNTERS_TYPE
        defaultMGoalJudgementShouldBeFound("encountersType.greaterOrEqualThan=" + DEFAULT_ENCOUNTERS_TYPE);

        // Get all the mGoalJudgementList where encountersType greater than or equals to UPDATED_ENCOUNTERS_TYPE
        defaultMGoalJudgementShouldNotBeFound("encountersType.greaterOrEqualThan=" + UPDATED_ENCOUNTERS_TYPE);
    }

    @Test
    @Transactional
    public void getAllMGoalJudgementsByEncountersTypeIsLessThanSomething() throws Exception {
        // Initialize the database
        mGoalJudgementRepository.saveAndFlush(mGoalJudgement);

        // Get all the mGoalJudgementList where encountersType less than or equals to DEFAULT_ENCOUNTERS_TYPE
        defaultMGoalJudgementShouldNotBeFound("encountersType.lessThan=" + DEFAULT_ENCOUNTERS_TYPE);

        // Get all the mGoalJudgementList where encountersType less than or equals to UPDATED_ENCOUNTERS_TYPE
        defaultMGoalJudgementShouldBeFound("encountersType.lessThan=" + UPDATED_ENCOUNTERS_TYPE);
    }


    @Test
    @Transactional
    public void getAllMGoalJudgementsBySuccessRateIsEqualToSomething() throws Exception {
        // Initialize the database
        mGoalJudgementRepository.saveAndFlush(mGoalJudgement);

        // Get all the mGoalJudgementList where successRate equals to DEFAULT_SUCCESS_RATE
        defaultMGoalJudgementShouldBeFound("successRate.equals=" + DEFAULT_SUCCESS_RATE);

        // Get all the mGoalJudgementList where successRate equals to UPDATED_SUCCESS_RATE
        defaultMGoalJudgementShouldNotBeFound("successRate.equals=" + UPDATED_SUCCESS_RATE);
    }

    @Test
    @Transactional
    public void getAllMGoalJudgementsBySuccessRateIsInShouldWork() throws Exception {
        // Initialize the database
        mGoalJudgementRepository.saveAndFlush(mGoalJudgement);

        // Get all the mGoalJudgementList where successRate in DEFAULT_SUCCESS_RATE or UPDATED_SUCCESS_RATE
        defaultMGoalJudgementShouldBeFound("successRate.in=" + DEFAULT_SUCCESS_RATE + "," + UPDATED_SUCCESS_RATE);

        // Get all the mGoalJudgementList where successRate equals to UPDATED_SUCCESS_RATE
        defaultMGoalJudgementShouldNotBeFound("successRate.in=" + UPDATED_SUCCESS_RATE);
    }

    @Test
    @Transactional
    public void getAllMGoalJudgementsBySuccessRateIsNullOrNotNull() throws Exception {
        // Initialize the database
        mGoalJudgementRepository.saveAndFlush(mGoalJudgement);

        // Get all the mGoalJudgementList where successRate is not null
        defaultMGoalJudgementShouldBeFound("successRate.specified=true");

        // Get all the mGoalJudgementList where successRate is null
        defaultMGoalJudgementShouldNotBeFound("successRate.specified=false");
    }

    @Test
    @Transactional
    public void getAllMGoalJudgementsBySuccessRateIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mGoalJudgementRepository.saveAndFlush(mGoalJudgement);

        // Get all the mGoalJudgementList where successRate greater than or equals to DEFAULT_SUCCESS_RATE
        defaultMGoalJudgementShouldBeFound("successRate.greaterOrEqualThan=" + DEFAULT_SUCCESS_RATE);

        // Get all the mGoalJudgementList where successRate greater than or equals to UPDATED_SUCCESS_RATE
        defaultMGoalJudgementShouldNotBeFound("successRate.greaterOrEqualThan=" + UPDATED_SUCCESS_RATE);
    }

    @Test
    @Transactional
    public void getAllMGoalJudgementsBySuccessRateIsLessThanSomething() throws Exception {
        // Initialize the database
        mGoalJudgementRepository.saveAndFlush(mGoalJudgement);

        // Get all the mGoalJudgementList where successRate less than or equals to DEFAULT_SUCCESS_RATE
        defaultMGoalJudgementShouldNotBeFound("successRate.lessThan=" + DEFAULT_SUCCESS_RATE);

        // Get all the mGoalJudgementList where successRate less than or equals to UPDATED_SUCCESS_RATE
        defaultMGoalJudgementShouldBeFound("successRate.lessThan=" + UPDATED_SUCCESS_RATE);
    }


    @Test
    @Transactional
    public void getAllMGoalJudgementsByGoalPostRateIsEqualToSomething() throws Exception {
        // Initialize the database
        mGoalJudgementRepository.saveAndFlush(mGoalJudgement);

        // Get all the mGoalJudgementList where goalPostRate equals to DEFAULT_GOAL_POST_RATE
        defaultMGoalJudgementShouldBeFound("goalPostRate.equals=" + DEFAULT_GOAL_POST_RATE);

        // Get all the mGoalJudgementList where goalPostRate equals to UPDATED_GOAL_POST_RATE
        defaultMGoalJudgementShouldNotBeFound("goalPostRate.equals=" + UPDATED_GOAL_POST_RATE);
    }

    @Test
    @Transactional
    public void getAllMGoalJudgementsByGoalPostRateIsInShouldWork() throws Exception {
        // Initialize the database
        mGoalJudgementRepository.saveAndFlush(mGoalJudgement);

        // Get all the mGoalJudgementList where goalPostRate in DEFAULT_GOAL_POST_RATE or UPDATED_GOAL_POST_RATE
        defaultMGoalJudgementShouldBeFound("goalPostRate.in=" + DEFAULT_GOAL_POST_RATE + "," + UPDATED_GOAL_POST_RATE);

        // Get all the mGoalJudgementList where goalPostRate equals to UPDATED_GOAL_POST_RATE
        defaultMGoalJudgementShouldNotBeFound("goalPostRate.in=" + UPDATED_GOAL_POST_RATE);
    }

    @Test
    @Transactional
    public void getAllMGoalJudgementsByGoalPostRateIsNullOrNotNull() throws Exception {
        // Initialize the database
        mGoalJudgementRepository.saveAndFlush(mGoalJudgement);

        // Get all the mGoalJudgementList where goalPostRate is not null
        defaultMGoalJudgementShouldBeFound("goalPostRate.specified=true");

        // Get all the mGoalJudgementList where goalPostRate is null
        defaultMGoalJudgementShouldNotBeFound("goalPostRate.specified=false");
    }

    @Test
    @Transactional
    public void getAllMGoalJudgementsByGoalPostRateIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mGoalJudgementRepository.saveAndFlush(mGoalJudgement);

        // Get all the mGoalJudgementList where goalPostRate greater than or equals to DEFAULT_GOAL_POST_RATE
        defaultMGoalJudgementShouldBeFound("goalPostRate.greaterOrEqualThan=" + DEFAULT_GOAL_POST_RATE);

        // Get all the mGoalJudgementList where goalPostRate greater than or equals to UPDATED_GOAL_POST_RATE
        defaultMGoalJudgementShouldNotBeFound("goalPostRate.greaterOrEqualThan=" + UPDATED_GOAL_POST_RATE);
    }

    @Test
    @Transactional
    public void getAllMGoalJudgementsByGoalPostRateIsLessThanSomething() throws Exception {
        // Initialize the database
        mGoalJudgementRepository.saveAndFlush(mGoalJudgement);

        // Get all the mGoalJudgementList where goalPostRate less than or equals to DEFAULT_GOAL_POST_RATE
        defaultMGoalJudgementShouldNotBeFound("goalPostRate.lessThan=" + DEFAULT_GOAL_POST_RATE);

        // Get all the mGoalJudgementList where goalPostRate less than or equals to UPDATED_GOAL_POST_RATE
        defaultMGoalJudgementShouldBeFound("goalPostRate.lessThan=" + UPDATED_GOAL_POST_RATE);
    }


    @Test
    @Transactional
    public void getAllMGoalJudgementsByBallPushRateIsEqualToSomething() throws Exception {
        // Initialize the database
        mGoalJudgementRepository.saveAndFlush(mGoalJudgement);

        // Get all the mGoalJudgementList where ballPushRate equals to DEFAULT_BALL_PUSH_RATE
        defaultMGoalJudgementShouldBeFound("ballPushRate.equals=" + DEFAULT_BALL_PUSH_RATE);

        // Get all the mGoalJudgementList where ballPushRate equals to UPDATED_BALL_PUSH_RATE
        defaultMGoalJudgementShouldNotBeFound("ballPushRate.equals=" + UPDATED_BALL_PUSH_RATE);
    }

    @Test
    @Transactional
    public void getAllMGoalJudgementsByBallPushRateIsInShouldWork() throws Exception {
        // Initialize the database
        mGoalJudgementRepository.saveAndFlush(mGoalJudgement);

        // Get all the mGoalJudgementList where ballPushRate in DEFAULT_BALL_PUSH_RATE or UPDATED_BALL_PUSH_RATE
        defaultMGoalJudgementShouldBeFound("ballPushRate.in=" + DEFAULT_BALL_PUSH_RATE + "," + UPDATED_BALL_PUSH_RATE);

        // Get all the mGoalJudgementList where ballPushRate equals to UPDATED_BALL_PUSH_RATE
        defaultMGoalJudgementShouldNotBeFound("ballPushRate.in=" + UPDATED_BALL_PUSH_RATE);
    }

    @Test
    @Transactional
    public void getAllMGoalJudgementsByBallPushRateIsNullOrNotNull() throws Exception {
        // Initialize the database
        mGoalJudgementRepository.saveAndFlush(mGoalJudgement);

        // Get all the mGoalJudgementList where ballPushRate is not null
        defaultMGoalJudgementShouldBeFound("ballPushRate.specified=true");

        // Get all the mGoalJudgementList where ballPushRate is null
        defaultMGoalJudgementShouldNotBeFound("ballPushRate.specified=false");
    }

    @Test
    @Transactional
    public void getAllMGoalJudgementsByBallPushRateIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mGoalJudgementRepository.saveAndFlush(mGoalJudgement);

        // Get all the mGoalJudgementList where ballPushRate greater than or equals to DEFAULT_BALL_PUSH_RATE
        defaultMGoalJudgementShouldBeFound("ballPushRate.greaterOrEqualThan=" + DEFAULT_BALL_PUSH_RATE);

        // Get all the mGoalJudgementList where ballPushRate greater than or equals to UPDATED_BALL_PUSH_RATE
        defaultMGoalJudgementShouldNotBeFound("ballPushRate.greaterOrEqualThan=" + UPDATED_BALL_PUSH_RATE);
    }

    @Test
    @Transactional
    public void getAllMGoalJudgementsByBallPushRateIsLessThanSomething() throws Exception {
        // Initialize the database
        mGoalJudgementRepository.saveAndFlush(mGoalJudgement);

        // Get all the mGoalJudgementList where ballPushRate less than or equals to DEFAULT_BALL_PUSH_RATE
        defaultMGoalJudgementShouldNotBeFound("ballPushRate.lessThan=" + DEFAULT_BALL_PUSH_RATE);

        // Get all the mGoalJudgementList where ballPushRate less than or equals to UPDATED_BALL_PUSH_RATE
        defaultMGoalJudgementShouldBeFound("ballPushRate.lessThan=" + UPDATED_BALL_PUSH_RATE);
    }


    @Test
    @Transactional
    public void getAllMGoalJudgementsByClearRateIsEqualToSomething() throws Exception {
        // Initialize the database
        mGoalJudgementRepository.saveAndFlush(mGoalJudgement);

        // Get all the mGoalJudgementList where clearRate equals to DEFAULT_CLEAR_RATE
        defaultMGoalJudgementShouldBeFound("clearRate.equals=" + DEFAULT_CLEAR_RATE);

        // Get all the mGoalJudgementList where clearRate equals to UPDATED_CLEAR_RATE
        defaultMGoalJudgementShouldNotBeFound("clearRate.equals=" + UPDATED_CLEAR_RATE);
    }

    @Test
    @Transactional
    public void getAllMGoalJudgementsByClearRateIsInShouldWork() throws Exception {
        // Initialize the database
        mGoalJudgementRepository.saveAndFlush(mGoalJudgement);

        // Get all the mGoalJudgementList where clearRate in DEFAULT_CLEAR_RATE or UPDATED_CLEAR_RATE
        defaultMGoalJudgementShouldBeFound("clearRate.in=" + DEFAULT_CLEAR_RATE + "," + UPDATED_CLEAR_RATE);

        // Get all the mGoalJudgementList where clearRate equals to UPDATED_CLEAR_RATE
        defaultMGoalJudgementShouldNotBeFound("clearRate.in=" + UPDATED_CLEAR_RATE);
    }

    @Test
    @Transactional
    public void getAllMGoalJudgementsByClearRateIsNullOrNotNull() throws Exception {
        // Initialize the database
        mGoalJudgementRepository.saveAndFlush(mGoalJudgement);

        // Get all the mGoalJudgementList where clearRate is not null
        defaultMGoalJudgementShouldBeFound("clearRate.specified=true");

        // Get all the mGoalJudgementList where clearRate is null
        defaultMGoalJudgementShouldNotBeFound("clearRate.specified=false");
    }

    @Test
    @Transactional
    public void getAllMGoalJudgementsByClearRateIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mGoalJudgementRepository.saveAndFlush(mGoalJudgement);

        // Get all the mGoalJudgementList where clearRate greater than or equals to DEFAULT_CLEAR_RATE
        defaultMGoalJudgementShouldBeFound("clearRate.greaterOrEqualThan=" + DEFAULT_CLEAR_RATE);

        // Get all the mGoalJudgementList where clearRate greater than or equals to UPDATED_CLEAR_RATE
        defaultMGoalJudgementShouldNotBeFound("clearRate.greaterOrEqualThan=" + UPDATED_CLEAR_RATE);
    }

    @Test
    @Transactional
    public void getAllMGoalJudgementsByClearRateIsLessThanSomething() throws Exception {
        // Initialize the database
        mGoalJudgementRepository.saveAndFlush(mGoalJudgement);

        // Get all the mGoalJudgementList where clearRate less than or equals to DEFAULT_CLEAR_RATE
        defaultMGoalJudgementShouldNotBeFound("clearRate.lessThan=" + DEFAULT_CLEAR_RATE);

        // Get all the mGoalJudgementList where clearRate less than or equals to UPDATED_CLEAR_RATE
        defaultMGoalJudgementShouldBeFound("clearRate.lessThan=" + UPDATED_CLEAR_RATE);
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultMGoalJudgementShouldBeFound(String filter) throws Exception {
        restMGoalJudgementMockMvc.perform(get("/api/m-goal-judgements?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(mGoalJudgement.getId().intValue())))
            .andExpect(jsonPath("$.[*].judgementId").value(hasItem(DEFAULT_JUDGEMENT_ID)))
            .andExpect(jsonPath("$.[*].encountersType").value(hasItem(DEFAULT_ENCOUNTERS_TYPE)))
            .andExpect(jsonPath("$.[*].successRate").value(hasItem(DEFAULT_SUCCESS_RATE)))
            .andExpect(jsonPath("$.[*].goalPostRate").value(hasItem(DEFAULT_GOAL_POST_RATE)))
            .andExpect(jsonPath("$.[*].ballPushRate").value(hasItem(DEFAULT_BALL_PUSH_RATE)))
            .andExpect(jsonPath("$.[*].clearRate").value(hasItem(DEFAULT_CLEAR_RATE)));

        // Check, that the count call also returns 1
        restMGoalJudgementMockMvc.perform(get("/api/m-goal-judgements/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultMGoalJudgementShouldNotBeFound(String filter) throws Exception {
        restMGoalJudgementMockMvc.perform(get("/api/m-goal-judgements?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restMGoalJudgementMockMvc.perform(get("/api/m-goal-judgements/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("0"));
    }


    @Test
    @Transactional
    public void getNonExistingMGoalJudgement() throws Exception {
        // Get the mGoalJudgement
        restMGoalJudgementMockMvc.perform(get("/api/m-goal-judgements/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateMGoalJudgement() throws Exception {
        // Initialize the database
        mGoalJudgementRepository.saveAndFlush(mGoalJudgement);

        int databaseSizeBeforeUpdate = mGoalJudgementRepository.findAll().size();

        // Update the mGoalJudgement
        MGoalJudgement updatedMGoalJudgement = mGoalJudgementRepository.findById(mGoalJudgement.getId()).get();
        // Disconnect from session so that the updates on updatedMGoalJudgement are not directly saved in db
        em.detach(updatedMGoalJudgement);
        updatedMGoalJudgement
            .judgementId(UPDATED_JUDGEMENT_ID)
            .encountersType(UPDATED_ENCOUNTERS_TYPE)
            .successRate(UPDATED_SUCCESS_RATE)
            .goalPostRate(UPDATED_GOAL_POST_RATE)
            .ballPushRate(UPDATED_BALL_PUSH_RATE)
            .clearRate(UPDATED_CLEAR_RATE);
        MGoalJudgementDTO mGoalJudgementDTO = mGoalJudgementMapper.toDto(updatedMGoalJudgement);

        restMGoalJudgementMockMvc.perform(put("/api/m-goal-judgements")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mGoalJudgementDTO)))
            .andExpect(status().isOk());

        // Validate the MGoalJudgement in the database
        List<MGoalJudgement> mGoalJudgementList = mGoalJudgementRepository.findAll();
        assertThat(mGoalJudgementList).hasSize(databaseSizeBeforeUpdate);
        MGoalJudgement testMGoalJudgement = mGoalJudgementList.get(mGoalJudgementList.size() - 1);
        assertThat(testMGoalJudgement.getJudgementId()).isEqualTo(UPDATED_JUDGEMENT_ID);
        assertThat(testMGoalJudgement.getEncountersType()).isEqualTo(UPDATED_ENCOUNTERS_TYPE);
        assertThat(testMGoalJudgement.getSuccessRate()).isEqualTo(UPDATED_SUCCESS_RATE);
        assertThat(testMGoalJudgement.getGoalPostRate()).isEqualTo(UPDATED_GOAL_POST_RATE);
        assertThat(testMGoalJudgement.getBallPushRate()).isEqualTo(UPDATED_BALL_PUSH_RATE);
        assertThat(testMGoalJudgement.getClearRate()).isEqualTo(UPDATED_CLEAR_RATE);
    }

    @Test
    @Transactional
    public void updateNonExistingMGoalJudgement() throws Exception {
        int databaseSizeBeforeUpdate = mGoalJudgementRepository.findAll().size();

        // Create the MGoalJudgement
        MGoalJudgementDTO mGoalJudgementDTO = mGoalJudgementMapper.toDto(mGoalJudgement);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restMGoalJudgementMockMvc.perform(put("/api/m-goal-judgements")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mGoalJudgementDTO)))
            .andExpect(status().isBadRequest());

        // Validate the MGoalJudgement in the database
        List<MGoalJudgement> mGoalJudgementList = mGoalJudgementRepository.findAll();
        assertThat(mGoalJudgementList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteMGoalJudgement() throws Exception {
        // Initialize the database
        mGoalJudgementRepository.saveAndFlush(mGoalJudgement);

        int databaseSizeBeforeDelete = mGoalJudgementRepository.findAll().size();

        // Delete the mGoalJudgement
        restMGoalJudgementMockMvc.perform(delete("/api/m-goal-judgements/{id}", mGoalJudgement.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database is empty
        List<MGoalJudgement> mGoalJudgementList = mGoalJudgementRepository.findAll();
        assertThat(mGoalJudgementList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(MGoalJudgement.class);
        MGoalJudgement mGoalJudgement1 = new MGoalJudgement();
        mGoalJudgement1.setId(1L);
        MGoalJudgement mGoalJudgement2 = new MGoalJudgement();
        mGoalJudgement2.setId(mGoalJudgement1.getId());
        assertThat(mGoalJudgement1).isEqualTo(mGoalJudgement2);
        mGoalJudgement2.setId(2L);
        assertThat(mGoalJudgement1).isNotEqualTo(mGoalJudgement2);
        mGoalJudgement1.setId(null);
        assertThat(mGoalJudgement1).isNotEqualTo(mGoalJudgement2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(MGoalJudgementDTO.class);
        MGoalJudgementDTO mGoalJudgementDTO1 = new MGoalJudgementDTO();
        mGoalJudgementDTO1.setId(1L);
        MGoalJudgementDTO mGoalJudgementDTO2 = new MGoalJudgementDTO();
        assertThat(mGoalJudgementDTO1).isNotEqualTo(mGoalJudgementDTO2);
        mGoalJudgementDTO2.setId(mGoalJudgementDTO1.getId());
        assertThat(mGoalJudgementDTO1).isEqualTo(mGoalJudgementDTO2);
        mGoalJudgementDTO2.setId(2L);
        assertThat(mGoalJudgementDTO1).isNotEqualTo(mGoalJudgementDTO2);
        mGoalJudgementDTO1.setId(null);
        assertThat(mGoalJudgementDTO1).isNotEqualTo(mGoalJudgementDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(mGoalJudgementMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(mGoalJudgementMapper.fromId(null)).isNull();
    }
}
