package io.shm.tsubasa.web.rest;

import io.shm.tsubasa.TsubasaApp;
import io.shm.tsubasa.domain.MSituation;
import io.shm.tsubasa.repository.MSituationRepository;
import io.shm.tsubasa.service.MSituationService;
import io.shm.tsubasa.service.dto.MSituationDTO;
import io.shm.tsubasa.service.mapper.MSituationMapper;
import io.shm.tsubasa.web.rest.errors.ExceptionTranslator;
import io.shm.tsubasa.service.dto.MSituationCriteria;
import io.shm.tsubasa.service.MSituationQueryService;

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
 * Integration tests for the {@Link MSituationResource} REST controller.
 */
@SpringBootTest(classes = TsubasaApp.class)
public class MSituationResourceIT {

    private static final Integer DEFAULT_KICKOFF = 1;
    private static final Integer UPDATED_KICKOFF = 2;

    private static final Integer DEFAULT_PENALTY_KICK = 1;
    private static final Integer UPDATED_PENALTY_KICK = 2;

    private static final Integer DEFAULT_MATCH_INTERVAL = 1;
    private static final Integer UPDATED_MATCH_INTERVAL = 2;

    private static final Integer DEFAULT_OUT_OF_PLAY = 1;
    private static final Integer UPDATED_OUT_OF_PLAY = 2;

    private static final Integer DEFAULT_FOUL = 1;
    private static final Integer UPDATED_FOUL = 2;

    private static final Integer DEFAULT_GOAL = 1;
    private static final Integer UPDATED_GOAL = 2;

    private static final Integer DEFAULT_SCORE = 1;
    private static final Integer UPDATED_SCORE = 2;

    private static final Integer DEFAULT_TIME = 1;
    private static final Integer UPDATED_TIME = 2;

    @Autowired
    private MSituationRepository mSituationRepository;

    @Autowired
    private MSituationMapper mSituationMapper;

    @Autowired
    private MSituationService mSituationService;

    @Autowired
    private MSituationQueryService mSituationQueryService;

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

    private MockMvc restMSituationMockMvc;

    private MSituation mSituation;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final MSituationResource mSituationResource = new MSituationResource(mSituationService, mSituationQueryService);
        this.restMSituationMockMvc = MockMvcBuilders.standaloneSetup(mSituationResource)
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
    public static MSituation createEntity(EntityManager em) {
        MSituation mSituation = new MSituation()
            .kickoff(DEFAULT_KICKOFF)
            .penaltyKick(DEFAULT_PENALTY_KICK)
            .matchInterval(DEFAULT_MATCH_INTERVAL)
            .outOfPlay(DEFAULT_OUT_OF_PLAY)
            .foul(DEFAULT_FOUL)
            .goal(DEFAULT_GOAL)
            .score(DEFAULT_SCORE)
            .time(DEFAULT_TIME);
        return mSituation;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static MSituation createUpdatedEntity(EntityManager em) {
        MSituation mSituation = new MSituation()
            .kickoff(UPDATED_KICKOFF)
            .penaltyKick(UPDATED_PENALTY_KICK)
            .matchInterval(UPDATED_MATCH_INTERVAL)
            .outOfPlay(UPDATED_OUT_OF_PLAY)
            .foul(UPDATED_FOUL)
            .goal(UPDATED_GOAL)
            .score(UPDATED_SCORE)
            .time(UPDATED_TIME);
        return mSituation;
    }

    @BeforeEach
    public void initTest() {
        mSituation = createEntity(em);
    }

    @Test
    @Transactional
    public void createMSituation() throws Exception {
        int databaseSizeBeforeCreate = mSituationRepository.findAll().size();

        // Create the MSituation
        MSituationDTO mSituationDTO = mSituationMapper.toDto(mSituation);
        restMSituationMockMvc.perform(post("/api/m-situations")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mSituationDTO)))
            .andExpect(status().isCreated());

        // Validate the MSituation in the database
        List<MSituation> mSituationList = mSituationRepository.findAll();
        assertThat(mSituationList).hasSize(databaseSizeBeforeCreate + 1);
        MSituation testMSituation = mSituationList.get(mSituationList.size() - 1);
        assertThat(testMSituation.getKickoff()).isEqualTo(DEFAULT_KICKOFF);
        assertThat(testMSituation.getPenaltyKick()).isEqualTo(DEFAULT_PENALTY_KICK);
        assertThat(testMSituation.getMatchInterval()).isEqualTo(DEFAULT_MATCH_INTERVAL);
        assertThat(testMSituation.getOutOfPlay()).isEqualTo(DEFAULT_OUT_OF_PLAY);
        assertThat(testMSituation.getFoul()).isEqualTo(DEFAULT_FOUL);
        assertThat(testMSituation.getGoal()).isEqualTo(DEFAULT_GOAL);
        assertThat(testMSituation.getScore()).isEqualTo(DEFAULT_SCORE);
        assertThat(testMSituation.getTime()).isEqualTo(DEFAULT_TIME);
    }

    @Test
    @Transactional
    public void createMSituationWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = mSituationRepository.findAll().size();

        // Create the MSituation with an existing ID
        mSituation.setId(1L);
        MSituationDTO mSituationDTO = mSituationMapper.toDto(mSituation);

        // An entity with an existing ID cannot be created, so this API call must fail
        restMSituationMockMvc.perform(post("/api/m-situations")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mSituationDTO)))
            .andExpect(status().isBadRequest());

        // Validate the MSituation in the database
        List<MSituation> mSituationList = mSituationRepository.findAll();
        assertThat(mSituationList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkKickoffIsRequired() throws Exception {
        int databaseSizeBeforeTest = mSituationRepository.findAll().size();
        // set the field null
        mSituation.setKickoff(null);

        // Create the MSituation, which fails.
        MSituationDTO mSituationDTO = mSituationMapper.toDto(mSituation);

        restMSituationMockMvc.perform(post("/api/m-situations")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mSituationDTO)))
            .andExpect(status().isBadRequest());

        List<MSituation> mSituationList = mSituationRepository.findAll();
        assertThat(mSituationList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkPenaltyKickIsRequired() throws Exception {
        int databaseSizeBeforeTest = mSituationRepository.findAll().size();
        // set the field null
        mSituation.setPenaltyKick(null);

        // Create the MSituation, which fails.
        MSituationDTO mSituationDTO = mSituationMapper.toDto(mSituation);

        restMSituationMockMvc.perform(post("/api/m-situations")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mSituationDTO)))
            .andExpect(status().isBadRequest());

        List<MSituation> mSituationList = mSituationRepository.findAll();
        assertThat(mSituationList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkMatchIntervalIsRequired() throws Exception {
        int databaseSizeBeforeTest = mSituationRepository.findAll().size();
        // set the field null
        mSituation.setMatchInterval(null);

        // Create the MSituation, which fails.
        MSituationDTO mSituationDTO = mSituationMapper.toDto(mSituation);

        restMSituationMockMvc.perform(post("/api/m-situations")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mSituationDTO)))
            .andExpect(status().isBadRequest());

        List<MSituation> mSituationList = mSituationRepository.findAll();
        assertThat(mSituationList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkOutOfPlayIsRequired() throws Exception {
        int databaseSizeBeforeTest = mSituationRepository.findAll().size();
        // set the field null
        mSituation.setOutOfPlay(null);

        // Create the MSituation, which fails.
        MSituationDTO mSituationDTO = mSituationMapper.toDto(mSituation);

        restMSituationMockMvc.perform(post("/api/m-situations")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mSituationDTO)))
            .andExpect(status().isBadRequest());

        List<MSituation> mSituationList = mSituationRepository.findAll();
        assertThat(mSituationList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkFoulIsRequired() throws Exception {
        int databaseSizeBeforeTest = mSituationRepository.findAll().size();
        // set the field null
        mSituation.setFoul(null);

        // Create the MSituation, which fails.
        MSituationDTO mSituationDTO = mSituationMapper.toDto(mSituation);

        restMSituationMockMvc.perform(post("/api/m-situations")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mSituationDTO)))
            .andExpect(status().isBadRequest());

        List<MSituation> mSituationList = mSituationRepository.findAll();
        assertThat(mSituationList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkGoalIsRequired() throws Exception {
        int databaseSizeBeforeTest = mSituationRepository.findAll().size();
        // set the field null
        mSituation.setGoal(null);

        // Create the MSituation, which fails.
        MSituationDTO mSituationDTO = mSituationMapper.toDto(mSituation);

        restMSituationMockMvc.perform(post("/api/m-situations")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mSituationDTO)))
            .andExpect(status().isBadRequest());

        List<MSituation> mSituationList = mSituationRepository.findAll();
        assertThat(mSituationList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkScoreIsRequired() throws Exception {
        int databaseSizeBeforeTest = mSituationRepository.findAll().size();
        // set the field null
        mSituation.setScore(null);

        // Create the MSituation, which fails.
        MSituationDTO mSituationDTO = mSituationMapper.toDto(mSituation);

        restMSituationMockMvc.perform(post("/api/m-situations")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mSituationDTO)))
            .andExpect(status().isBadRequest());

        List<MSituation> mSituationList = mSituationRepository.findAll();
        assertThat(mSituationList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkTimeIsRequired() throws Exception {
        int databaseSizeBeforeTest = mSituationRepository.findAll().size();
        // set the field null
        mSituation.setTime(null);

        // Create the MSituation, which fails.
        MSituationDTO mSituationDTO = mSituationMapper.toDto(mSituation);

        restMSituationMockMvc.perform(post("/api/m-situations")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mSituationDTO)))
            .andExpect(status().isBadRequest());

        List<MSituation> mSituationList = mSituationRepository.findAll();
        assertThat(mSituationList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllMSituations() throws Exception {
        // Initialize the database
        mSituationRepository.saveAndFlush(mSituation);

        // Get all the mSituationList
        restMSituationMockMvc.perform(get("/api/m-situations?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(mSituation.getId().intValue())))
            .andExpect(jsonPath("$.[*].kickoff").value(hasItem(DEFAULT_KICKOFF)))
            .andExpect(jsonPath("$.[*].penaltyKick").value(hasItem(DEFAULT_PENALTY_KICK)))
            .andExpect(jsonPath("$.[*].matchInterval").value(hasItem(DEFAULT_MATCH_INTERVAL)))
            .andExpect(jsonPath("$.[*].outOfPlay").value(hasItem(DEFAULT_OUT_OF_PLAY)))
            .andExpect(jsonPath("$.[*].foul").value(hasItem(DEFAULT_FOUL)))
            .andExpect(jsonPath("$.[*].goal").value(hasItem(DEFAULT_GOAL)))
            .andExpect(jsonPath("$.[*].score").value(hasItem(DEFAULT_SCORE)))
            .andExpect(jsonPath("$.[*].time").value(hasItem(DEFAULT_TIME)));
    }
    
    @Test
    @Transactional
    public void getMSituation() throws Exception {
        // Initialize the database
        mSituationRepository.saveAndFlush(mSituation);

        // Get the mSituation
        restMSituationMockMvc.perform(get("/api/m-situations/{id}", mSituation.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(mSituation.getId().intValue()))
            .andExpect(jsonPath("$.kickoff").value(DEFAULT_KICKOFF))
            .andExpect(jsonPath("$.penaltyKick").value(DEFAULT_PENALTY_KICK))
            .andExpect(jsonPath("$.matchInterval").value(DEFAULT_MATCH_INTERVAL))
            .andExpect(jsonPath("$.outOfPlay").value(DEFAULT_OUT_OF_PLAY))
            .andExpect(jsonPath("$.foul").value(DEFAULT_FOUL))
            .andExpect(jsonPath("$.goal").value(DEFAULT_GOAL))
            .andExpect(jsonPath("$.score").value(DEFAULT_SCORE))
            .andExpect(jsonPath("$.time").value(DEFAULT_TIME));
    }

    @Test
    @Transactional
    public void getAllMSituationsByKickoffIsEqualToSomething() throws Exception {
        // Initialize the database
        mSituationRepository.saveAndFlush(mSituation);

        // Get all the mSituationList where kickoff equals to DEFAULT_KICKOFF
        defaultMSituationShouldBeFound("kickoff.equals=" + DEFAULT_KICKOFF);

        // Get all the mSituationList where kickoff equals to UPDATED_KICKOFF
        defaultMSituationShouldNotBeFound("kickoff.equals=" + UPDATED_KICKOFF);
    }

    @Test
    @Transactional
    public void getAllMSituationsByKickoffIsInShouldWork() throws Exception {
        // Initialize the database
        mSituationRepository.saveAndFlush(mSituation);

        // Get all the mSituationList where kickoff in DEFAULT_KICKOFF or UPDATED_KICKOFF
        defaultMSituationShouldBeFound("kickoff.in=" + DEFAULT_KICKOFF + "," + UPDATED_KICKOFF);

        // Get all the mSituationList where kickoff equals to UPDATED_KICKOFF
        defaultMSituationShouldNotBeFound("kickoff.in=" + UPDATED_KICKOFF);
    }

    @Test
    @Transactional
    public void getAllMSituationsByKickoffIsNullOrNotNull() throws Exception {
        // Initialize the database
        mSituationRepository.saveAndFlush(mSituation);

        // Get all the mSituationList where kickoff is not null
        defaultMSituationShouldBeFound("kickoff.specified=true");

        // Get all the mSituationList where kickoff is null
        defaultMSituationShouldNotBeFound("kickoff.specified=false");
    }

    @Test
    @Transactional
    public void getAllMSituationsByKickoffIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mSituationRepository.saveAndFlush(mSituation);

        // Get all the mSituationList where kickoff greater than or equals to DEFAULT_KICKOFF
        defaultMSituationShouldBeFound("kickoff.greaterOrEqualThan=" + DEFAULT_KICKOFF);

        // Get all the mSituationList where kickoff greater than or equals to UPDATED_KICKOFF
        defaultMSituationShouldNotBeFound("kickoff.greaterOrEqualThan=" + UPDATED_KICKOFF);
    }

    @Test
    @Transactional
    public void getAllMSituationsByKickoffIsLessThanSomething() throws Exception {
        // Initialize the database
        mSituationRepository.saveAndFlush(mSituation);

        // Get all the mSituationList where kickoff less than or equals to DEFAULT_KICKOFF
        defaultMSituationShouldNotBeFound("kickoff.lessThan=" + DEFAULT_KICKOFF);

        // Get all the mSituationList where kickoff less than or equals to UPDATED_KICKOFF
        defaultMSituationShouldBeFound("kickoff.lessThan=" + UPDATED_KICKOFF);
    }


    @Test
    @Transactional
    public void getAllMSituationsByPenaltyKickIsEqualToSomething() throws Exception {
        // Initialize the database
        mSituationRepository.saveAndFlush(mSituation);

        // Get all the mSituationList where penaltyKick equals to DEFAULT_PENALTY_KICK
        defaultMSituationShouldBeFound("penaltyKick.equals=" + DEFAULT_PENALTY_KICK);

        // Get all the mSituationList where penaltyKick equals to UPDATED_PENALTY_KICK
        defaultMSituationShouldNotBeFound("penaltyKick.equals=" + UPDATED_PENALTY_KICK);
    }

    @Test
    @Transactional
    public void getAllMSituationsByPenaltyKickIsInShouldWork() throws Exception {
        // Initialize the database
        mSituationRepository.saveAndFlush(mSituation);

        // Get all the mSituationList where penaltyKick in DEFAULT_PENALTY_KICK or UPDATED_PENALTY_KICK
        defaultMSituationShouldBeFound("penaltyKick.in=" + DEFAULT_PENALTY_KICK + "," + UPDATED_PENALTY_KICK);

        // Get all the mSituationList where penaltyKick equals to UPDATED_PENALTY_KICK
        defaultMSituationShouldNotBeFound("penaltyKick.in=" + UPDATED_PENALTY_KICK);
    }

    @Test
    @Transactional
    public void getAllMSituationsByPenaltyKickIsNullOrNotNull() throws Exception {
        // Initialize the database
        mSituationRepository.saveAndFlush(mSituation);

        // Get all the mSituationList where penaltyKick is not null
        defaultMSituationShouldBeFound("penaltyKick.specified=true");

        // Get all the mSituationList where penaltyKick is null
        defaultMSituationShouldNotBeFound("penaltyKick.specified=false");
    }

    @Test
    @Transactional
    public void getAllMSituationsByPenaltyKickIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mSituationRepository.saveAndFlush(mSituation);

        // Get all the mSituationList where penaltyKick greater than or equals to DEFAULT_PENALTY_KICK
        defaultMSituationShouldBeFound("penaltyKick.greaterOrEqualThan=" + DEFAULT_PENALTY_KICK);

        // Get all the mSituationList where penaltyKick greater than or equals to UPDATED_PENALTY_KICK
        defaultMSituationShouldNotBeFound("penaltyKick.greaterOrEqualThan=" + UPDATED_PENALTY_KICK);
    }

    @Test
    @Transactional
    public void getAllMSituationsByPenaltyKickIsLessThanSomething() throws Exception {
        // Initialize the database
        mSituationRepository.saveAndFlush(mSituation);

        // Get all the mSituationList where penaltyKick less than or equals to DEFAULT_PENALTY_KICK
        defaultMSituationShouldNotBeFound("penaltyKick.lessThan=" + DEFAULT_PENALTY_KICK);

        // Get all the mSituationList where penaltyKick less than or equals to UPDATED_PENALTY_KICK
        defaultMSituationShouldBeFound("penaltyKick.lessThan=" + UPDATED_PENALTY_KICK);
    }


    @Test
    @Transactional
    public void getAllMSituationsByMatchIntervalIsEqualToSomething() throws Exception {
        // Initialize the database
        mSituationRepository.saveAndFlush(mSituation);

        // Get all the mSituationList where matchInterval equals to DEFAULT_MATCH_INTERVAL
        defaultMSituationShouldBeFound("matchInterval.equals=" + DEFAULT_MATCH_INTERVAL);

        // Get all the mSituationList where matchInterval equals to UPDATED_MATCH_INTERVAL
        defaultMSituationShouldNotBeFound("matchInterval.equals=" + UPDATED_MATCH_INTERVAL);
    }

    @Test
    @Transactional
    public void getAllMSituationsByMatchIntervalIsInShouldWork() throws Exception {
        // Initialize the database
        mSituationRepository.saveAndFlush(mSituation);

        // Get all the mSituationList where matchInterval in DEFAULT_MATCH_INTERVAL or UPDATED_MATCH_INTERVAL
        defaultMSituationShouldBeFound("matchInterval.in=" + DEFAULT_MATCH_INTERVAL + "," + UPDATED_MATCH_INTERVAL);

        // Get all the mSituationList where matchInterval equals to UPDATED_MATCH_INTERVAL
        defaultMSituationShouldNotBeFound("matchInterval.in=" + UPDATED_MATCH_INTERVAL);
    }

    @Test
    @Transactional
    public void getAllMSituationsByMatchIntervalIsNullOrNotNull() throws Exception {
        // Initialize the database
        mSituationRepository.saveAndFlush(mSituation);

        // Get all the mSituationList where matchInterval is not null
        defaultMSituationShouldBeFound("matchInterval.specified=true");

        // Get all the mSituationList where matchInterval is null
        defaultMSituationShouldNotBeFound("matchInterval.specified=false");
    }

    @Test
    @Transactional
    public void getAllMSituationsByMatchIntervalIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mSituationRepository.saveAndFlush(mSituation);

        // Get all the mSituationList where matchInterval greater than or equals to DEFAULT_MATCH_INTERVAL
        defaultMSituationShouldBeFound("matchInterval.greaterOrEqualThan=" + DEFAULT_MATCH_INTERVAL);

        // Get all the mSituationList where matchInterval greater than or equals to UPDATED_MATCH_INTERVAL
        defaultMSituationShouldNotBeFound("matchInterval.greaterOrEqualThan=" + UPDATED_MATCH_INTERVAL);
    }

    @Test
    @Transactional
    public void getAllMSituationsByMatchIntervalIsLessThanSomething() throws Exception {
        // Initialize the database
        mSituationRepository.saveAndFlush(mSituation);

        // Get all the mSituationList where matchInterval less than or equals to DEFAULT_MATCH_INTERVAL
        defaultMSituationShouldNotBeFound("matchInterval.lessThan=" + DEFAULT_MATCH_INTERVAL);

        // Get all the mSituationList where matchInterval less than or equals to UPDATED_MATCH_INTERVAL
        defaultMSituationShouldBeFound("matchInterval.lessThan=" + UPDATED_MATCH_INTERVAL);
    }


    @Test
    @Transactional
    public void getAllMSituationsByOutOfPlayIsEqualToSomething() throws Exception {
        // Initialize the database
        mSituationRepository.saveAndFlush(mSituation);

        // Get all the mSituationList where outOfPlay equals to DEFAULT_OUT_OF_PLAY
        defaultMSituationShouldBeFound("outOfPlay.equals=" + DEFAULT_OUT_OF_PLAY);

        // Get all the mSituationList where outOfPlay equals to UPDATED_OUT_OF_PLAY
        defaultMSituationShouldNotBeFound("outOfPlay.equals=" + UPDATED_OUT_OF_PLAY);
    }

    @Test
    @Transactional
    public void getAllMSituationsByOutOfPlayIsInShouldWork() throws Exception {
        // Initialize the database
        mSituationRepository.saveAndFlush(mSituation);

        // Get all the mSituationList where outOfPlay in DEFAULT_OUT_OF_PLAY or UPDATED_OUT_OF_PLAY
        defaultMSituationShouldBeFound("outOfPlay.in=" + DEFAULT_OUT_OF_PLAY + "," + UPDATED_OUT_OF_PLAY);

        // Get all the mSituationList where outOfPlay equals to UPDATED_OUT_OF_PLAY
        defaultMSituationShouldNotBeFound("outOfPlay.in=" + UPDATED_OUT_OF_PLAY);
    }

    @Test
    @Transactional
    public void getAllMSituationsByOutOfPlayIsNullOrNotNull() throws Exception {
        // Initialize the database
        mSituationRepository.saveAndFlush(mSituation);

        // Get all the mSituationList where outOfPlay is not null
        defaultMSituationShouldBeFound("outOfPlay.specified=true");

        // Get all the mSituationList where outOfPlay is null
        defaultMSituationShouldNotBeFound("outOfPlay.specified=false");
    }

    @Test
    @Transactional
    public void getAllMSituationsByOutOfPlayIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mSituationRepository.saveAndFlush(mSituation);

        // Get all the mSituationList where outOfPlay greater than or equals to DEFAULT_OUT_OF_PLAY
        defaultMSituationShouldBeFound("outOfPlay.greaterOrEqualThan=" + DEFAULT_OUT_OF_PLAY);

        // Get all the mSituationList where outOfPlay greater than or equals to UPDATED_OUT_OF_PLAY
        defaultMSituationShouldNotBeFound("outOfPlay.greaterOrEqualThan=" + UPDATED_OUT_OF_PLAY);
    }

    @Test
    @Transactional
    public void getAllMSituationsByOutOfPlayIsLessThanSomething() throws Exception {
        // Initialize the database
        mSituationRepository.saveAndFlush(mSituation);

        // Get all the mSituationList where outOfPlay less than or equals to DEFAULT_OUT_OF_PLAY
        defaultMSituationShouldNotBeFound("outOfPlay.lessThan=" + DEFAULT_OUT_OF_PLAY);

        // Get all the mSituationList where outOfPlay less than or equals to UPDATED_OUT_OF_PLAY
        defaultMSituationShouldBeFound("outOfPlay.lessThan=" + UPDATED_OUT_OF_PLAY);
    }


    @Test
    @Transactional
    public void getAllMSituationsByFoulIsEqualToSomething() throws Exception {
        // Initialize the database
        mSituationRepository.saveAndFlush(mSituation);

        // Get all the mSituationList where foul equals to DEFAULT_FOUL
        defaultMSituationShouldBeFound("foul.equals=" + DEFAULT_FOUL);

        // Get all the mSituationList where foul equals to UPDATED_FOUL
        defaultMSituationShouldNotBeFound("foul.equals=" + UPDATED_FOUL);
    }

    @Test
    @Transactional
    public void getAllMSituationsByFoulIsInShouldWork() throws Exception {
        // Initialize the database
        mSituationRepository.saveAndFlush(mSituation);

        // Get all the mSituationList where foul in DEFAULT_FOUL or UPDATED_FOUL
        defaultMSituationShouldBeFound("foul.in=" + DEFAULT_FOUL + "," + UPDATED_FOUL);

        // Get all the mSituationList where foul equals to UPDATED_FOUL
        defaultMSituationShouldNotBeFound("foul.in=" + UPDATED_FOUL);
    }

    @Test
    @Transactional
    public void getAllMSituationsByFoulIsNullOrNotNull() throws Exception {
        // Initialize the database
        mSituationRepository.saveAndFlush(mSituation);

        // Get all the mSituationList where foul is not null
        defaultMSituationShouldBeFound("foul.specified=true");

        // Get all the mSituationList where foul is null
        defaultMSituationShouldNotBeFound("foul.specified=false");
    }

    @Test
    @Transactional
    public void getAllMSituationsByFoulIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mSituationRepository.saveAndFlush(mSituation);

        // Get all the mSituationList where foul greater than or equals to DEFAULT_FOUL
        defaultMSituationShouldBeFound("foul.greaterOrEqualThan=" + DEFAULT_FOUL);

        // Get all the mSituationList where foul greater than or equals to UPDATED_FOUL
        defaultMSituationShouldNotBeFound("foul.greaterOrEqualThan=" + UPDATED_FOUL);
    }

    @Test
    @Transactional
    public void getAllMSituationsByFoulIsLessThanSomething() throws Exception {
        // Initialize the database
        mSituationRepository.saveAndFlush(mSituation);

        // Get all the mSituationList where foul less than or equals to DEFAULT_FOUL
        defaultMSituationShouldNotBeFound("foul.lessThan=" + DEFAULT_FOUL);

        // Get all the mSituationList where foul less than or equals to UPDATED_FOUL
        defaultMSituationShouldBeFound("foul.lessThan=" + UPDATED_FOUL);
    }


    @Test
    @Transactional
    public void getAllMSituationsByGoalIsEqualToSomething() throws Exception {
        // Initialize the database
        mSituationRepository.saveAndFlush(mSituation);

        // Get all the mSituationList where goal equals to DEFAULT_GOAL
        defaultMSituationShouldBeFound("goal.equals=" + DEFAULT_GOAL);

        // Get all the mSituationList where goal equals to UPDATED_GOAL
        defaultMSituationShouldNotBeFound("goal.equals=" + UPDATED_GOAL);
    }

    @Test
    @Transactional
    public void getAllMSituationsByGoalIsInShouldWork() throws Exception {
        // Initialize the database
        mSituationRepository.saveAndFlush(mSituation);

        // Get all the mSituationList where goal in DEFAULT_GOAL or UPDATED_GOAL
        defaultMSituationShouldBeFound("goal.in=" + DEFAULT_GOAL + "," + UPDATED_GOAL);

        // Get all the mSituationList where goal equals to UPDATED_GOAL
        defaultMSituationShouldNotBeFound("goal.in=" + UPDATED_GOAL);
    }

    @Test
    @Transactional
    public void getAllMSituationsByGoalIsNullOrNotNull() throws Exception {
        // Initialize the database
        mSituationRepository.saveAndFlush(mSituation);

        // Get all the mSituationList where goal is not null
        defaultMSituationShouldBeFound("goal.specified=true");

        // Get all the mSituationList where goal is null
        defaultMSituationShouldNotBeFound("goal.specified=false");
    }

    @Test
    @Transactional
    public void getAllMSituationsByGoalIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mSituationRepository.saveAndFlush(mSituation);

        // Get all the mSituationList where goal greater than or equals to DEFAULT_GOAL
        defaultMSituationShouldBeFound("goal.greaterOrEqualThan=" + DEFAULT_GOAL);

        // Get all the mSituationList where goal greater than or equals to UPDATED_GOAL
        defaultMSituationShouldNotBeFound("goal.greaterOrEqualThan=" + UPDATED_GOAL);
    }

    @Test
    @Transactional
    public void getAllMSituationsByGoalIsLessThanSomething() throws Exception {
        // Initialize the database
        mSituationRepository.saveAndFlush(mSituation);

        // Get all the mSituationList where goal less than or equals to DEFAULT_GOAL
        defaultMSituationShouldNotBeFound("goal.lessThan=" + DEFAULT_GOAL);

        // Get all the mSituationList where goal less than or equals to UPDATED_GOAL
        defaultMSituationShouldBeFound("goal.lessThan=" + UPDATED_GOAL);
    }


    @Test
    @Transactional
    public void getAllMSituationsByScoreIsEqualToSomething() throws Exception {
        // Initialize the database
        mSituationRepository.saveAndFlush(mSituation);

        // Get all the mSituationList where score equals to DEFAULT_SCORE
        defaultMSituationShouldBeFound("score.equals=" + DEFAULT_SCORE);

        // Get all the mSituationList where score equals to UPDATED_SCORE
        defaultMSituationShouldNotBeFound("score.equals=" + UPDATED_SCORE);
    }

    @Test
    @Transactional
    public void getAllMSituationsByScoreIsInShouldWork() throws Exception {
        // Initialize the database
        mSituationRepository.saveAndFlush(mSituation);

        // Get all the mSituationList where score in DEFAULT_SCORE or UPDATED_SCORE
        defaultMSituationShouldBeFound("score.in=" + DEFAULT_SCORE + "," + UPDATED_SCORE);

        // Get all the mSituationList where score equals to UPDATED_SCORE
        defaultMSituationShouldNotBeFound("score.in=" + UPDATED_SCORE);
    }

    @Test
    @Transactional
    public void getAllMSituationsByScoreIsNullOrNotNull() throws Exception {
        // Initialize the database
        mSituationRepository.saveAndFlush(mSituation);

        // Get all the mSituationList where score is not null
        defaultMSituationShouldBeFound("score.specified=true");

        // Get all the mSituationList where score is null
        defaultMSituationShouldNotBeFound("score.specified=false");
    }

    @Test
    @Transactional
    public void getAllMSituationsByScoreIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mSituationRepository.saveAndFlush(mSituation);

        // Get all the mSituationList where score greater than or equals to DEFAULT_SCORE
        defaultMSituationShouldBeFound("score.greaterOrEqualThan=" + DEFAULT_SCORE);

        // Get all the mSituationList where score greater than or equals to UPDATED_SCORE
        defaultMSituationShouldNotBeFound("score.greaterOrEqualThan=" + UPDATED_SCORE);
    }

    @Test
    @Transactional
    public void getAllMSituationsByScoreIsLessThanSomething() throws Exception {
        // Initialize the database
        mSituationRepository.saveAndFlush(mSituation);

        // Get all the mSituationList where score less than or equals to DEFAULT_SCORE
        defaultMSituationShouldNotBeFound("score.lessThan=" + DEFAULT_SCORE);

        // Get all the mSituationList where score less than or equals to UPDATED_SCORE
        defaultMSituationShouldBeFound("score.lessThan=" + UPDATED_SCORE);
    }


    @Test
    @Transactional
    public void getAllMSituationsByTimeIsEqualToSomething() throws Exception {
        // Initialize the database
        mSituationRepository.saveAndFlush(mSituation);

        // Get all the mSituationList where time equals to DEFAULT_TIME
        defaultMSituationShouldBeFound("time.equals=" + DEFAULT_TIME);

        // Get all the mSituationList where time equals to UPDATED_TIME
        defaultMSituationShouldNotBeFound("time.equals=" + UPDATED_TIME);
    }

    @Test
    @Transactional
    public void getAllMSituationsByTimeIsInShouldWork() throws Exception {
        // Initialize the database
        mSituationRepository.saveAndFlush(mSituation);

        // Get all the mSituationList where time in DEFAULT_TIME or UPDATED_TIME
        defaultMSituationShouldBeFound("time.in=" + DEFAULT_TIME + "," + UPDATED_TIME);

        // Get all the mSituationList where time equals to UPDATED_TIME
        defaultMSituationShouldNotBeFound("time.in=" + UPDATED_TIME);
    }

    @Test
    @Transactional
    public void getAllMSituationsByTimeIsNullOrNotNull() throws Exception {
        // Initialize the database
        mSituationRepository.saveAndFlush(mSituation);

        // Get all the mSituationList where time is not null
        defaultMSituationShouldBeFound("time.specified=true");

        // Get all the mSituationList where time is null
        defaultMSituationShouldNotBeFound("time.specified=false");
    }

    @Test
    @Transactional
    public void getAllMSituationsByTimeIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mSituationRepository.saveAndFlush(mSituation);

        // Get all the mSituationList where time greater than or equals to DEFAULT_TIME
        defaultMSituationShouldBeFound("time.greaterOrEqualThan=" + DEFAULT_TIME);

        // Get all the mSituationList where time greater than or equals to UPDATED_TIME
        defaultMSituationShouldNotBeFound("time.greaterOrEqualThan=" + UPDATED_TIME);
    }

    @Test
    @Transactional
    public void getAllMSituationsByTimeIsLessThanSomething() throws Exception {
        // Initialize the database
        mSituationRepository.saveAndFlush(mSituation);

        // Get all the mSituationList where time less than or equals to DEFAULT_TIME
        defaultMSituationShouldNotBeFound("time.lessThan=" + DEFAULT_TIME);

        // Get all the mSituationList where time less than or equals to UPDATED_TIME
        defaultMSituationShouldBeFound("time.lessThan=" + UPDATED_TIME);
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultMSituationShouldBeFound(String filter) throws Exception {
        restMSituationMockMvc.perform(get("/api/m-situations?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(mSituation.getId().intValue())))
            .andExpect(jsonPath("$.[*].kickoff").value(hasItem(DEFAULT_KICKOFF)))
            .andExpect(jsonPath("$.[*].penaltyKick").value(hasItem(DEFAULT_PENALTY_KICK)))
            .andExpect(jsonPath("$.[*].matchInterval").value(hasItem(DEFAULT_MATCH_INTERVAL)))
            .andExpect(jsonPath("$.[*].outOfPlay").value(hasItem(DEFAULT_OUT_OF_PLAY)))
            .andExpect(jsonPath("$.[*].foul").value(hasItem(DEFAULT_FOUL)))
            .andExpect(jsonPath("$.[*].goal").value(hasItem(DEFAULT_GOAL)))
            .andExpect(jsonPath("$.[*].score").value(hasItem(DEFAULT_SCORE)))
            .andExpect(jsonPath("$.[*].time").value(hasItem(DEFAULT_TIME)));

        // Check, that the count call also returns 1
        restMSituationMockMvc.perform(get("/api/m-situations/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultMSituationShouldNotBeFound(String filter) throws Exception {
        restMSituationMockMvc.perform(get("/api/m-situations?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restMSituationMockMvc.perform(get("/api/m-situations/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("0"));
    }


    @Test
    @Transactional
    public void getNonExistingMSituation() throws Exception {
        // Get the mSituation
        restMSituationMockMvc.perform(get("/api/m-situations/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateMSituation() throws Exception {
        // Initialize the database
        mSituationRepository.saveAndFlush(mSituation);

        int databaseSizeBeforeUpdate = mSituationRepository.findAll().size();

        // Update the mSituation
        MSituation updatedMSituation = mSituationRepository.findById(mSituation.getId()).get();
        // Disconnect from session so that the updates on updatedMSituation are not directly saved in db
        em.detach(updatedMSituation);
        updatedMSituation
            .kickoff(UPDATED_KICKOFF)
            .penaltyKick(UPDATED_PENALTY_KICK)
            .matchInterval(UPDATED_MATCH_INTERVAL)
            .outOfPlay(UPDATED_OUT_OF_PLAY)
            .foul(UPDATED_FOUL)
            .goal(UPDATED_GOAL)
            .score(UPDATED_SCORE)
            .time(UPDATED_TIME);
        MSituationDTO mSituationDTO = mSituationMapper.toDto(updatedMSituation);

        restMSituationMockMvc.perform(put("/api/m-situations")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mSituationDTO)))
            .andExpect(status().isOk());

        // Validate the MSituation in the database
        List<MSituation> mSituationList = mSituationRepository.findAll();
        assertThat(mSituationList).hasSize(databaseSizeBeforeUpdate);
        MSituation testMSituation = mSituationList.get(mSituationList.size() - 1);
        assertThat(testMSituation.getKickoff()).isEqualTo(UPDATED_KICKOFF);
        assertThat(testMSituation.getPenaltyKick()).isEqualTo(UPDATED_PENALTY_KICK);
        assertThat(testMSituation.getMatchInterval()).isEqualTo(UPDATED_MATCH_INTERVAL);
        assertThat(testMSituation.getOutOfPlay()).isEqualTo(UPDATED_OUT_OF_PLAY);
        assertThat(testMSituation.getFoul()).isEqualTo(UPDATED_FOUL);
        assertThat(testMSituation.getGoal()).isEqualTo(UPDATED_GOAL);
        assertThat(testMSituation.getScore()).isEqualTo(UPDATED_SCORE);
        assertThat(testMSituation.getTime()).isEqualTo(UPDATED_TIME);
    }

    @Test
    @Transactional
    public void updateNonExistingMSituation() throws Exception {
        int databaseSizeBeforeUpdate = mSituationRepository.findAll().size();

        // Create the MSituation
        MSituationDTO mSituationDTO = mSituationMapper.toDto(mSituation);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restMSituationMockMvc.perform(put("/api/m-situations")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mSituationDTO)))
            .andExpect(status().isBadRequest());

        // Validate the MSituation in the database
        List<MSituation> mSituationList = mSituationRepository.findAll();
        assertThat(mSituationList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteMSituation() throws Exception {
        // Initialize the database
        mSituationRepository.saveAndFlush(mSituation);

        int databaseSizeBeforeDelete = mSituationRepository.findAll().size();

        // Delete the mSituation
        restMSituationMockMvc.perform(delete("/api/m-situations/{id}", mSituation.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database is empty
        List<MSituation> mSituationList = mSituationRepository.findAll();
        assertThat(mSituationList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(MSituation.class);
        MSituation mSituation1 = new MSituation();
        mSituation1.setId(1L);
        MSituation mSituation2 = new MSituation();
        mSituation2.setId(mSituation1.getId());
        assertThat(mSituation1).isEqualTo(mSituation2);
        mSituation2.setId(2L);
        assertThat(mSituation1).isNotEqualTo(mSituation2);
        mSituation1.setId(null);
        assertThat(mSituation1).isNotEqualTo(mSituation2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(MSituationDTO.class);
        MSituationDTO mSituationDTO1 = new MSituationDTO();
        mSituationDTO1.setId(1L);
        MSituationDTO mSituationDTO2 = new MSituationDTO();
        assertThat(mSituationDTO1).isNotEqualTo(mSituationDTO2);
        mSituationDTO2.setId(mSituationDTO1.getId());
        assertThat(mSituationDTO1).isEqualTo(mSituationDTO2);
        mSituationDTO2.setId(2L);
        assertThat(mSituationDTO1).isNotEqualTo(mSituationDTO2);
        mSituationDTO1.setId(null);
        assertThat(mSituationDTO1).isNotEqualTo(mSituationDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(mSituationMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(mSituationMapper.fromId(null)).isNull();
    }
}
