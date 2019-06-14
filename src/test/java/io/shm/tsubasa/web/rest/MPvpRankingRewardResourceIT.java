package io.shm.tsubasa.web.rest;

import io.shm.tsubasa.TsubasaApp;
import io.shm.tsubasa.domain.MPvpRankingReward;
import io.shm.tsubasa.domain.MPvpWave;
import io.shm.tsubasa.repository.MPvpRankingRewardRepository;
import io.shm.tsubasa.service.MPvpRankingRewardService;
import io.shm.tsubasa.service.dto.MPvpRankingRewardDTO;
import io.shm.tsubasa.service.mapper.MPvpRankingRewardMapper;
import io.shm.tsubasa.web.rest.errors.ExceptionTranslator;
import io.shm.tsubasa.service.dto.MPvpRankingRewardCriteria;
import io.shm.tsubasa.service.MPvpRankingRewardQueryService;

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
 * Integration tests for the {@Link MPvpRankingRewardResource} REST controller.
 */
@SpringBootTest(classes = TsubasaApp.class)
public class MPvpRankingRewardResourceIT {

    private static final Integer DEFAULT_WAVE_ID = 1;
    private static final Integer UPDATED_WAVE_ID = 2;

    private static final Integer DEFAULT_DIFFICULTY = 1;
    private static final Integer UPDATED_DIFFICULTY = 2;

    private static final Integer DEFAULT_RANKING_FROM = 1;
    private static final Integer UPDATED_RANKING_FROM = 2;

    private static final Integer DEFAULT_RANKING_TO = 1;
    private static final Integer UPDATED_RANKING_TO = 2;

    private static final Integer DEFAULT_REWARD_GROUP_ID = 1;
    private static final Integer UPDATED_REWARD_GROUP_ID = 2;

    @Autowired
    private MPvpRankingRewardRepository mPvpRankingRewardRepository;

    @Autowired
    private MPvpRankingRewardMapper mPvpRankingRewardMapper;

    @Autowired
    private MPvpRankingRewardService mPvpRankingRewardService;

    @Autowired
    private MPvpRankingRewardQueryService mPvpRankingRewardQueryService;

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

    private MockMvc restMPvpRankingRewardMockMvc;

    private MPvpRankingReward mPvpRankingReward;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final MPvpRankingRewardResource mPvpRankingRewardResource = new MPvpRankingRewardResource(mPvpRankingRewardService, mPvpRankingRewardQueryService);
        this.restMPvpRankingRewardMockMvc = MockMvcBuilders.standaloneSetup(mPvpRankingRewardResource)
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
    public static MPvpRankingReward createEntity(EntityManager em) {
        MPvpRankingReward mPvpRankingReward = new MPvpRankingReward()
            .waveId(DEFAULT_WAVE_ID)
            .difficulty(DEFAULT_DIFFICULTY)
            .rankingFrom(DEFAULT_RANKING_FROM)
            .rankingTo(DEFAULT_RANKING_TO)
            .rewardGroupId(DEFAULT_REWARD_GROUP_ID);
        // Add required entity
        MPvpWave mPvpWave;
        if (TestUtil.findAll(em, MPvpWave.class).isEmpty()) {
            mPvpWave = MPvpWaveResourceIT.createEntity(em);
            em.persist(mPvpWave);
            em.flush();
        } else {
            mPvpWave = TestUtil.findAll(em, MPvpWave.class).get(0);
        }
        mPvpRankingReward.setId(mPvpWave);
        return mPvpRankingReward;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static MPvpRankingReward createUpdatedEntity(EntityManager em) {
        MPvpRankingReward mPvpRankingReward = new MPvpRankingReward()
            .waveId(UPDATED_WAVE_ID)
            .difficulty(UPDATED_DIFFICULTY)
            .rankingFrom(UPDATED_RANKING_FROM)
            .rankingTo(UPDATED_RANKING_TO)
            .rewardGroupId(UPDATED_REWARD_GROUP_ID);
        // Add required entity
        MPvpWave mPvpWave;
        if (TestUtil.findAll(em, MPvpWave.class).isEmpty()) {
            mPvpWave = MPvpWaveResourceIT.createUpdatedEntity(em);
            em.persist(mPvpWave);
            em.flush();
        } else {
            mPvpWave = TestUtil.findAll(em, MPvpWave.class).get(0);
        }
        mPvpRankingReward.setId(mPvpWave);
        return mPvpRankingReward;
    }

    @BeforeEach
    public void initTest() {
        mPvpRankingReward = createEntity(em);
    }

    @Test
    @Transactional
    public void createMPvpRankingReward() throws Exception {
        int databaseSizeBeforeCreate = mPvpRankingRewardRepository.findAll().size();

        // Create the MPvpRankingReward
        MPvpRankingRewardDTO mPvpRankingRewardDTO = mPvpRankingRewardMapper.toDto(mPvpRankingReward);
        restMPvpRankingRewardMockMvc.perform(post("/api/m-pvp-ranking-rewards")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mPvpRankingRewardDTO)))
            .andExpect(status().isCreated());

        // Validate the MPvpRankingReward in the database
        List<MPvpRankingReward> mPvpRankingRewardList = mPvpRankingRewardRepository.findAll();
        assertThat(mPvpRankingRewardList).hasSize(databaseSizeBeforeCreate + 1);
        MPvpRankingReward testMPvpRankingReward = mPvpRankingRewardList.get(mPvpRankingRewardList.size() - 1);
        assertThat(testMPvpRankingReward.getWaveId()).isEqualTo(DEFAULT_WAVE_ID);
        assertThat(testMPvpRankingReward.getDifficulty()).isEqualTo(DEFAULT_DIFFICULTY);
        assertThat(testMPvpRankingReward.getRankingFrom()).isEqualTo(DEFAULT_RANKING_FROM);
        assertThat(testMPvpRankingReward.getRankingTo()).isEqualTo(DEFAULT_RANKING_TO);
        assertThat(testMPvpRankingReward.getRewardGroupId()).isEqualTo(DEFAULT_REWARD_GROUP_ID);
    }

    @Test
    @Transactional
    public void createMPvpRankingRewardWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = mPvpRankingRewardRepository.findAll().size();

        // Create the MPvpRankingReward with an existing ID
        mPvpRankingReward.setId(1L);
        MPvpRankingRewardDTO mPvpRankingRewardDTO = mPvpRankingRewardMapper.toDto(mPvpRankingReward);

        // An entity with an existing ID cannot be created, so this API call must fail
        restMPvpRankingRewardMockMvc.perform(post("/api/m-pvp-ranking-rewards")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mPvpRankingRewardDTO)))
            .andExpect(status().isBadRequest());

        // Validate the MPvpRankingReward in the database
        List<MPvpRankingReward> mPvpRankingRewardList = mPvpRankingRewardRepository.findAll();
        assertThat(mPvpRankingRewardList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkWaveIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = mPvpRankingRewardRepository.findAll().size();
        // set the field null
        mPvpRankingReward.setWaveId(null);

        // Create the MPvpRankingReward, which fails.
        MPvpRankingRewardDTO mPvpRankingRewardDTO = mPvpRankingRewardMapper.toDto(mPvpRankingReward);

        restMPvpRankingRewardMockMvc.perform(post("/api/m-pvp-ranking-rewards")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mPvpRankingRewardDTO)))
            .andExpect(status().isBadRequest());

        List<MPvpRankingReward> mPvpRankingRewardList = mPvpRankingRewardRepository.findAll();
        assertThat(mPvpRankingRewardList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkDifficultyIsRequired() throws Exception {
        int databaseSizeBeforeTest = mPvpRankingRewardRepository.findAll().size();
        // set the field null
        mPvpRankingReward.setDifficulty(null);

        // Create the MPvpRankingReward, which fails.
        MPvpRankingRewardDTO mPvpRankingRewardDTO = mPvpRankingRewardMapper.toDto(mPvpRankingReward);

        restMPvpRankingRewardMockMvc.perform(post("/api/m-pvp-ranking-rewards")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mPvpRankingRewardDTO)))
            .andExpect(status().isBadRequest());

        List<MPvpRankingReward> mPvpRankingRewardList = mPvpRankingRewardRepository.findAll();
        assertThat(mPvpRankingRewardList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkRankingFromIsRequired() throws Exception {
        int databaseSizeBeforeTest = mPvpRankingRewardRepository.findAll().size();
        // set the field null
        mPvpRankingReward.setRankingFrom(null);

        // Create the MPvpRankingReward, which fails.
        MPvpRankingRewardDTO mPvpRankingRewardDTO = mPvpRankingRewardMapper.toDto(mPvpRankingReward);

        restMPvpRankingRewardMockMvc.perform(post("/api/m-pvp-ranking-rewards")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mPvpRankingRewardDTO)))
            .andExpect(status().isBadRequest());

        List<MPvpRankingReward> mPvpRankingRewardList = mPvpRankingRewardRepository.findAll();
        assertThat(mPvpRankingRewardList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkRankingToIsRequired() throws Exception {
        int databaseSizeBeforeTest = mPvpRankingRewardRepository.findAll().size();
        // set the field null
        mPvpRankingReward.setRankingTo(null);

        // Create the MPvpRankingReward, which fails.
        MPvpRankingRewardDTO mPvpRankingRewardDTO = mPvpRankingRewardMapper.toDto(mPvpRankingReward);

        restMPvpRankingRewardMockMvc.perform(post("/api/m-pvp-ranking-rewards")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mPvpRankingRewardDTO)))
            .andExpect(status().isBadRequest());

        List<MPvpRankingReward> mPvpRankingRewardList = mPvpRankingRewardRepository.findAll();
        assertThat(mPvpRankingRewardList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkRewardGroupIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = mPvpRankingRewardRepository.findAll().size();
        // set the field null
        mPvpRankingReward.setRewardGroupId(null);

        // Create the MPvpRankingReward, which fails.
        MPvpRankingRewardDTO mPvpRankingRewardDTO = mPvpRankingRewardMapper.toDto(mPvpRankingReward);

        restMPvpRankingRewardMockMvc.perform(post("/api/m-pvp-ranking-rewards")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mPvpRankingRewardDTO)))
            .andExpect(status().isBadRequest());

        List<MPvpRankingReward> mPvpRankingRewardList = mPvpRankingRewardRepository.findAll();
        assertThat(mPvpRankingRewardList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllMPvpRankingRewards() throws Exception {
        // Initialize the database
        mPvpRankingRewardRepository.saveAndFlush(mPvpRankingReward);

        // Get all the mPvpRankingRewardList
        restMPvpRankingRewardMockMvc.perform(get("/api/m-pvp-ranking-rewards?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(mPvpRankingReward.getId().intValue())))
            .andExpect(jsonPath("$.[*].waveId").value(hasItem(DEFAULT_WAVE_ID)))
            .andExpect(jsonPath("$.[*].difficulty").value(hasItem(DEFAULT_DIFFICULTY)))
            .andExpect(jsonPath("$.[*].rankingFrom").value(hasItem(DEFAULT_RANKING_FROM)))
            .andExpect(jsonPath("$.[*].rankingTo").value(hasItem(DEFAULT_RANKING_TO)))
            .andExpect(jsonPath("$.[*].rewardGroupId").value(hasItem(DEFAULT_REWARD_GROUP_ID)));
    }
    
    @Test
    @Transactional
    public void getMPvpRankingReward() throws Exception {
        // Initialize the database
        mPvpRankingRewardRepository.saveAndFlush(mPvpRankingReward);

        // Get the mPvpRankingReward
        restMPvpRankingRewardMockMvc.perform(get("/api/m-pvp-ranking-rewards/{id}", mPvpRankingReward.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(mPvpRankingReward.getId().intValue()))
            .andExpect(jsonPath("$.waveId").value(DEFAULT_WAVE_ID))
            .andExpect(jsonPath("$.difficulty").value(DEFAULT_DIFFICULTY))
            .andExpect(jsonPath("$.rankingFrom").value(DEFAULT_RANKING_FROM))
            .andExpect(jsonPath("$.rankingTo").value(DEFAULT_RANKING_TO))
            .andExpect(jsonPath("$.rewardGroupId").value(DEFAULT_REWARD_GROUP_ID));
    }

    @Test
    @Transactional
    public void getAllMPvpRankingRewardsByWaveIdIsEqualToSomething() throws Exception {
        // Initialize the database
        mPvpRankingRewardRepository.saveAndFlush(mPvpRankingReward);

        // Get all the mPvpRankingRewardList where waveId equals to DEFAULT_WAVE_ID
        defaultMPvpRankingRewardShouldBeFound("waveId.equals=" + DEFAULT_WAVE_ID);

        // Get all the mPvpRankingRewardList where waveId equals to UPDATED_WAVE_ID
        defaultMPvpRankingRewardShouldNotBeFound("waveId.equals=" + UPDATED_WAVE_ID);
    }

    @Test
    @Transactional
    public void getAllMPvpRankingRewardsByWaveIdIsInShouldWork() throws Exception {
        // Initialize the database
        mPvpRankingRewardRepository.saveAndFlush(mPvpRankingReward);

        // Get all the mPvpRankingRewardList where waveId in DEFAULT_WAVE_ID or UPDATED_WAVE_ID
        defaultMPvpRankingRewardShouldBeFound("waveId.in=" + DEFAULT_WAVE_ID + "," + UPDATED_WAVE_ID);

        // Get all the mPvpRankingRewardList where waveId equals to UPDATED_WAVE_ID
        defaultMPvpRankingRewardShouldNotBeFound("waveId.in=" + UPDATED_WAVE_ID);
    }

    @Test
    @Transactional
    public void getAllMPvpRankingRewardsByWaveIdIsNullOrNotNull() throws Exception {
        // Initialize the database
        mPvpRankingRewardRepository.saveAndFlush(mPvpRankingReward);

        // Get all the mPvpRankingRewardList where waveId is not null
        defaultMPvpRankingRewardShouldBeFound("waveId.specified=true");

        // Get all the mPvpRankingRewardList where waveId is null
        defaultMPvpRankingRewardShouldNotBeFound("waveId.specified=false");
    }

    @Test
    @Transactional
    public void getAllMPvpRankingRewardsByWaveIdIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mPvpRankingRewardRepository.saveAndFlush(mPvpRankingReward);

        // Get all the mPvpRankingRewardList where waveId greater than or equals to DEFAULT_WAVE_ID
        defaultMPvpRankingRewardShouldBeFound("waveId.greaterOrEqualThan=" + DEFAULT_WAVE_ID);

        // Get all the mPvpRankingRewardList where waveId greater than or equals to UPDATED_WAVE_ID
        defaultMPvpRankingRewardShouldNotBeFound("waveId.greaterOrEqualThan=" + UPDATED_WAVE_ID);
    }

    @Test
    @Transactional
    public void getAllMPvpRankingRewardsByWaveIdIsLessThanSomething() throws Exception {
        // Initialize the database
        mPvpRankingRewardRepository.saveAndFlush(mPvpRankingReward);

        // Get all the mPvpRankingRewardList where waveId less than or equals to DEFAULT_WAVE_ID
        defaultMPvpRankingRewardShouldNotBeFound("waveId.lessThan=" + DEFAULT_WAVE_ID);

        // Get all the mPvpRankingRewardList where waveId less than or equals to UPDATED_WAVE_ID
        defaultMPvpRankingRewardShouldBeFound("waveId.lessThan=" + UPDATED_WAVE_ID);
    }


    @Test
    @Transactional
    public void getAllMPvpRankingRewardsByDifficultyIsEqualToSomething() throws Exception {
        // Initialize the database
        mPvpRankingRewardRepository.saveAndFlush(mPvpRankingReward);

        // Get all the mPvpRankingRewardList where difficulty equals to DEFAULT_DIFFICULTY
        defaultMPvpRankingRewardShouldBeFound("difficulty.equals=" + DEFAULT_DIFFICULTY);

        // Get all the mPvpRankingRewardList where difficulty equals to UPDATED_DIFFICULTY
        defaultMPvpRankingRewardShouldNotBeFound("difficulty.equals=" + UPDATED_DIFFICULTY);
    }

    @Test
    @Transactional
    public void getAllMPvpRankingRewardsByDifficultyIsInShouldWork() throws Exception {
        // Initialize the database
        mPvpRankingRewardRepository.saveAndFlush(mPvpRankingReward);

        // Get all the mPvpRankingRewardList where difficulty in DEFAULT_DIFFICULTY or UPDATED_DIFFICULTY
        defaultMPvpRankingRewardShouldBeFound("difficulty.in=" + DEFAULT_DIFFICULTY + "," + UPDATED_DIFFICULTY);

        // Get all the mPvpRankingRewardList where difficulty equals to UPDATED_DIFFICULTY
        defaultMPvpRankingRewardShouldNotBeFound("difficulty.in=" + UPDATED_DIFFICULTY);
    }

    @Test
    @Transactional
    public void getAllMPvpRankingRewardsByDifficultyIsNullOrNotNull() throws Exception {
        // Initialize the database
        mPvpRankingRewardRepository.saveAndFlush(mPvpRankingReward);

        // Get all the mPvpRankingRewardList where difficulty is not null
        defaultMPvpRankingRewardShouldBeFound("difficulty.specified=true");

        // Get all the mPvpRankingRewardList where difficulty is null
        defaultMPvpRankingRewardShouldNotBeFound("difficulty.specified=false");
    }

    @Test
    @Transactional
    public void getAllMPvpRankingRewardsByDifficultyIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mPvpRankingRewardRepository.saveAndFlush(mPvpRankingReward);

        // Get all the mPvpRankingRewardList where difficulty greater than or equals to DEFAULT_DIFFICULTY
        defaultMPvpRankingRewardShouldBeFound("difficulty.greaterOrEqualThan=" + DEFAULT_DIFFICULTY);

        // Get all the mPvpRankingRewardList where difficulty greater than or equals to UPDATED_DIFFICULTY
        defaultMPvpRankingRewardShouldNotBeFound("difficulty.greaterOrEqualThan=" + UPDATED_DIFFICULTY);
    }

    @Test
    @Transactional
    public void getAllMPvpRankingRewardsByDifficultyIsLessThanSomething() throws Exception {
        // Initialize the database
        mPvpRankingRewardRepository.saveAndFlush(mPvpRankingReward);

        // Get all the mPvpRankingRewardList where difficulty less than or equals to DEFAULT_DIFFICULTY
        defaultMPvpRankingRewardShouldNotBeFound("difficulty.lessThan=" + DEFAULT_DIFFICULTY);

        // Get all the mPvpRankingRewardList where difficulty less than or equals to UPDATED_DIFFICULTY
        defaultMPvpRankingRewardShouldBeFound("difficulty.lessThan=" + UPDATED_DIFFICULTY);
    }


    @Test
    @Transactional
    public void getAllMPvpRankingRewardsByRankingFromIsEqualToSomething() throws Exception {
        // Initialize the database
        mPvpRankingRewardRepository.saveAndFlush(mPvpRankingReward);

        // Get all the mPvpRankingRewardList where rankingFrom equals to DEFAULT_RANKING_FROM
        defaultMPvpRankingRewardShouldBeFound("rankingFrom.equals=" + DEFAULT_RANKING_FROM);

        // Get all the mPvpRankingRewardList where rankingFrom equals to UPDATED_RANKING_FROM
        defaultMPvpRankingRewardShouldNotBeFound("rankingFrom.equals=" + UPDATED_RANKING_FROM);
    }

    @Test
    @Transactional
    public void getAllMPvpRankingRewardsByRankingFromIsInShouldWork() throws Exception {
        // Initialize the database
        mPvpRankingRewardRepository.saveAndFlush(mPvpRankingReward);

        // Get all the mPvpRankingRewardList where rankingFrom in DEFAULT_RANKING_FROM or UPDATED_RANKING_FROM
        defaultMPvpRankingRewardShouldBeFound("rankingFrom.in=" + DEFAULT_RANKING_FROM + "," + UPDATED_RANKING_FROM);

        // Get all the mPvpRankingRewardList where rankingFrom equals to UPDATED_RANKING_FROM
        defaultMPvpRankingRewardShouldNotBeFound("rankingFrom.in=" + UPDATED_RANKING_FROM);
    }

    @Test
    @Transactional
    public void getAllMPvpRankingRewardsByRankingFromIsNullOrNotNull() throws Exception {
        // Initialize the database
        mPvpRankingRewardRepository.saveAndFlush(mPvpRankingReward);

        // Get all the mPvpRankingRewardList where rankingFrom is not null
        defaultMPvpRankingRewardShouldBeFound("rankingFrom.specified=true");

        // Get all the mPvpRankingRewardList where rankingFrom is null
        defaultMPvpRankingRewardShouldNotBeFound("rankingFrom.specified=false");
    }

    @Test
    @Transactional
    public void getAllMPvpRankingRewardsByRankingFromIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mPvpRankingRewardRepository.saveAndFlush(mPvpRankingReward);

        // Get all the mPvpRankingRewardList where rankingFrom greater than or equals to DEFAULT_RANKING_FROM
        defaultMPvpRankingRewardShouldBeFound("rankingFrom.greaterOrEqualThan=" + DEFAULT_RANKING_FROM);

        // Get all the mPvpRankingRewardList where rankingFrom greater than or equals to UPDATED_RANKING_FROM
        defaultMPvpRankingRewardShouldNotBeFound("rankingFrom.greaterOrEqualThan=" + UPDATED_RANKING_FROM);
    }

    @Test
    @Transactional
    public void getAllMPvpRankingRewardsByRankingFromIsLessThanSomething() throws Exception {
        // Initialize the database
        mPvpRankingRewardRepository.saveAndFlush(mPvpRankingReward);

        // Get all the mPvpRankingRewardList where rankingFrom less than or equals to DEFAULT_RANKING_FROM
        defaultMPvpRankingRewardShouldNotBeFound("rankingFrom.lessThan=" + DEFAULT_RANKING_FROM);

        // Get all the mPvpRankingRewardList where rankingFrom less than or equals to UPDATED_RANKING_FROM
        defaultMPvpRankingRewardShouldBeFound("rankingFrom.lessThan=" + UPDATED_RANKING_FROM);
    }


    @Test
    @Transactional
    public void getAllMPvpRankingRewardsByRankingToIsEqualToSomething() throws Exception {
        // Initialize the database
        mPvpRankingRewardRepository.saveAndFlush(mPvpRankingReward);

        // Get all the mPvpRankingRewardList where rankingTo equals to DEFAULT_RANKING_TO
        defaultMPvpRankingRewardShouldBeFound("rankingTo.equals=" + DEFAULT_RANKING_TO);

        // Get all the mPvpRankingRewardList where rankingTo equals to UPDATED_RANKING_TO
        defaultMPvpRankingRewardShouldNotBeFound("rankingTo.equals=" + UPDATED_RANKING_TO);
    }

    @Test
    @Transactional
    public void getAllMPvpRankingRewardsByRankingToIsInShouldWork() throws Exception {
        // Initialize the database
        mPvpRankingRewardRepository.saveAndFlush(mPvpRankingReward);

        // Get all the mPvpRankingRewardList where rankingTo in DEFAULT_RANKING_TO or UPDATED_RANKING_TO
        defaultMPvpRankingRewardShouldBeFound("rankingTo.in=" + DEFAULT_RANKING_TO + "," + UPDATED_RANKING_TO);

        // Get all the mPvpRankingRewardList where rankingTo equals to UPDATED_RANKING_TO
        defaultMPvpRankingRewardShouldNotBeFound("rankingTo.in=" + UPDATED_RANKING_TO);
    }

    @Test
    @Transactional
    public void getAllMPvpRankingRewardsByRankingToIsNullOrNotNull() throws Exception {
        // Initialize the database
        mPvpRankingRewardRepository.saveAndFlush(mPvpRankingReward);

        // Get all the mPvpRankingRewardList where rankingTo is not null
        defaultMPvpRankingRewardShouldBeFound("rankingTo.specified=true");

        // Get all the mPvpRankingRewardList where rankingTo is null
        defaultMPvpRankingRewardShouldNotBeFound("rankingTo.specified=false");
    }

    @Test
    @Transactional
    public void getAllMPvpRankingRewardsByRankingToIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mPvpRankingRewardRepository.saveAndFlush(mPvpRankingReward);

        // Get all the mPvpRankingRewardList where rankingTo greater than or equals to DEFAULT_RANKING_TO
        defaultMPvpRankingRewardShouldBeFound("rankingTo.greaterOrEqualThan=" + DEFAULT_RANKING_TO);

        // Get all the mPvpRankingRewardList where rankingTo greater than or equals to UPDATED_RANKING_TO
        defaultMPvpRankingRewardShouldNotBeFound("rankingTo.greaterOrEqualThan=" + UPDATED_RANKING_TO);
    }

    @Test
    @Transactional
    public void getAllMPvpRankingRewardsByRankingToIsLessThanSomething() throws Exception {
        // Initialize the database
        mPvpRankingRewardRepository.saveAndFlush(mPvpRankingReward);

        // Get all the mPvpRankingRewardList where rankingTo less than or equals to DEFAULT_RANKING_TO
        defaultMPvpRankingRewardShouldNotBeFound("rankingTo.lessThan=" + DEFAULT_RANKING_TO);

        // Get all the mPvpRankingRewardList where rankingTo less than or equals to UPDATED_RANKING_TO
        defaultMPvpRankingRewardShouldBeFound("rankingTo.lessThan=" + UPDATED_RANKING_TO);
    }


    @Test
    @Transactional
    public void getAllMPvpRankingRewardsByRewardGroupIdIsEqualToSomething() throws Exception {
        // Initialize the database
        mPvpRankingRewardRepository.saveAndFlush(mPvpRankingReward);

        // Get all the mPvpRankingRewardList where rewardGroupId equals to DEFAULT_REWARD_GROUP_ID
        defaultMPvpRankingRewardShouldBeFound("rewardGroupId.equals=" + DEFAULT_REWARD_GROUP_ID);

        // Get all the mPvpRankingRewardList where rewardGroupId equals to UPDATED_REWARD_GROUP_ID
        defaultMPvpRankingRewardShouldNotBeFound("rewardGroupId.equals=" + UPDATED_REWARD_GROUP_ID);
    }

    @Test
    @Transactional
    public void getAllMPvpRankingRewardsByRewardGroupIdIsInShouldWork() throws Exception {
        // Initialize the database
        mPvpRankingRewardRepository.saveAndFlush(mPvpRankingReward);

        // Get all the mPvpRankingRewardList where rewardGroupId in DEFAULT_REWARD_GROUP_ID or UPDATED_REWARD_GROUP_ID
        defaultMPvpRankingRewardShouldBeFound("rewardGroupId.in=" + DEFAULT_REWARD_GROUP_ID + "," + UPDATED_REWARD_GROUP_ID);

        // Get all the mPvpRankingRewardList where rewardGroupId equals to UPDATED_REWARD_GROUP_ID
        defaultMPvpRankingRewardShouldNotBeFound("rewardGroupId.in=" + UPDATED_REWARD_GROUP_ID);
    }

    @Test
    @Transactional
    public void getAllMPvpRankingRewardsByRewardGroupIdIsNullOrNotNull() throws Exception {
        // Initialize the database
        mPvpRankingRewardRepository.saveAndFlush(mPvpRankingReward);

        // Get all the mPvpRankingRewardList where rewardGroupId is not null
        defaultMPvpRankingRewardShouldBeFound("rewardGroupId.specified=true");

        // Get all the mPvpRankingRewardList where rewardGroupId is null
        defaultMPvpRankingRewardShouldNotBeFound("rewardGroupId.specified=false");
    }

    @Test
    @Transactional
    public void getAllMPvpRankingRewardsByRewardGroupIdIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mPvpRankingRewardRepository.saveAndFlush(mPvpRankingReward);

        // Get all the mPvpRankingRewardList where rewardGroupId greater than or equals to DEFAULT_REWARD_GROUP_ID
        defaultMPvpRankingRewardShouldBeFound("rewardGroupId.greaterOrEqualThan=" + DEFAULT_REWARD_GROUP_ID);

        // Get all the mPvpRankingRewardList where rewardGroupId greater than or equals to UPDATED_REWARD_GROUP_ID
        defaultMPvpRankingRewardShouldNotBeFound("rewardGroupId.greaterOrEqualThan=" + UPDATED_REWARD_GROUP_ID);
    }

    @Test
    @Transactional
    public void getAllMPvpRankingRewardsByRewardGroupIdIsLessThanSomething() throws Exception {
        // Initialize the database
        mPvpRankingRewardRepository.saveAndFlush(mPvpRankingReward);

        // Get all the mPvpRankingRewardList where rewardGroupId less than or equals to DEFAULT_REWARD_GROUP_ID
        defaultMPvpRankingRewardShouldNotBeFound("rewardGroupId.lessThan=" + DEFAULT_REWARD_GROUP_ID);

        // Get all the mPvpRankingRewardList where rewardGroupId less than or equals to UPDATED_REWARD_GROUP_ID
        defaultMPvpRankingRewardShouldBeFound("rewardGroupId.lessThan=" + UPDATED_REWARD_GROUP_ID);
    }


    @Test
    @Transactional
    public void getAllMPvpRankingRewardsByIdIsEqualToSomething() throws Exception {
        // Get already existing entity
        MPvpWave id = mPvpRankingReward.getId();
        mPvpRankingRewardRepository.saveAndFlush(mPvpRankingReward);
        Long idId = id.getId();

        // Get all the mPvpRankingRewardList where id equals to idId
        defaultMPvpRankingRewardShouldBeFound("idId.equals=" + idId);

        // Get all the mPvpRankingRewardList where id equals to idId + 1
        defaultMPvpRankingRewardShouldNotBeFound("idId.equals=" + (idId + 1));
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultMPvpRankingRewardShouldBeFound(String filter) throws Exception {
        restMPvpRankingRewardMockMvc.perform(get("/api/m-pvp-ranking-rewards?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(mPvpRankingReward.getId().intValue())))
            .andExpect(jsonPath("$.[*].waveId").value(hasItem(DEFAULT_WAVE_ID)))
            .andExpect(jsonPath("$.[*].difficulty").value(hasItem(DEFAULT_DIFFICULTY)))
            .andExpect(jsonPath("$.[*].rankingFrom").value(hasItem(DEFAULT_RANKING_FROM)))
            .andExpect(jsonPath("$.[*].rankingTo").value(hasItem(DEFAULT_RANKING_TO)))
            .andExpect(jsonPath("$.[*].rewardGroupId").value(hasItem(DEFAULT_REWARD_GROUP_ID)));

        // Check, that the count call also returns 1
        restMPvpRankingRewardMockMvc.perform(get("/api/m-pvp-ranking-rewards/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultMPvpRankingRewardShouldNotBeFound(String filter) throws Exception {
        restMPvpRankingRewardMockMvc.perform(get("/api/m-pvp-ranking-rewards?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restMPvpRankingRewardMockMvc.perform(get("/api/m-pvp-ranking-rewards/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("0"));
    }


    @Test
    @Transactional
    public void getNonExistingMPvpRankingReward() throws Exception {
        // Get the mPvpRankingReward
        restMPvpRankingRewardMockMvc.perform(get("/api/m-pvp-ranking-rewards/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateMPvpRankingReward() throws Exception {
        // Initialize the database
        mPvpRankingRewardRepository.saveAndFlush(mPvpRankingReward);

        int databaseSizeBeforeUpdate = mPvpRankingRewardRepository.findAll().size();

        // Update the mPvpRankingReward
        MPvpRankingReward updatedMPvpRankingReward = mPvpRankingRewardRepository.findById(mPvpRankingReward.getId()).get();
        // Disconnect from session so that the updates on updatedMPvpRankingReward are not directly saved in db
        em.detach(updatedMPvpRankingReward);
        updatedMPvpRankingReward
            .waveId(UPDATED_WAVE_ID)
            .difficulty(UPDATED_DIFFICULTY)
            .rankingFrom(UPDATED_RANKING_FROM)
            .rankingTo(UPDATED_RANKING_TO)
            .rewardGroupId(UPDATED_REWARD_GROUP_ID);
        MPvpRankingRewardDTO mPvpRankingRewardDTO = mPvpRankingRewardMapper.toDto(updatedMPvpRankingReward);

        restMPvpRankingRewardMockMvc.perform(put("/api/m-pvp-ranking-rewards")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mPvpRankingRewardDTO)))
            .andExpect(status().isOk());

        // Validate the MPvpRankingReward in the database
        List<MPvpRankingReward> mPvpRankingRewardList = mPvpRankingRewardRepository.findAll();
        assertThat(mPvpRankingRewardList).hasSize(databaseSizeBeforeUpdate);
        MPvpRankingReward testMPvpRankingReward = mPvpRankingRewardList.get(mPvpRankingRewardList.size() - 1);
        assertThat(testMPvpRankingReward.getWaveId()).isEqualTo(UPDATED_WAVE_ID);
        assertThat(testMPvpRankingReward.getDifficulty()).isEqualTo(UPDATED_DIFFICULTY);
        assertThat(testMPvpRankingReward.getRankingFrom()).isEqualTo(UPDATED_RANKING_FROM);
        assertThat(testMPvpRankingReward.getRankingTo()).isEqualTo(UPDATED_RANKING_TO);
        assertThat(testMPvpRankingReward.getRewardGroupId()).isEqualTo(UPDATED_REWARD_GROUP_ID);
    }

    @Test
    @Transactional
    public void updateNonExistingMPvpRankingReward() throws Exception {
        int databaseSizeBeforeUpdate = mPvpRankingRewardRepository.findAll().size();

        // Create the MPvpRankingReward
        MPvpRankingRewardDTO mPvpRankingRewardDTO = mPvpRankingRewardMapper.toDto(mPvpRankingReward);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restMPvpRankingRewardMockMvc.perform(put("/api/m-pvp-ranking-rewards")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mPvpRankingRewardDTO)))
            .andExpect(status().isBadRequest());

        // Validate the MPvpRankingReward in the database
        List<MPvpRankingReward> mPvpRankingRewardList = mPvpRankingRewardRepository.findAll();
        assertThat(mPvpRankingRewardList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteMPvpRankingReward() throws Exception {
        // Initialize the database
        mPvpRankingRewardRepository.saveAndFlush(mPvpRankingReward);

        int databaseSizeBeforeDelete = mPvpRankingRewardRepository.findAll().size();

        // Delete the mPvpRankingReward
        restMPvpRankingRewardMockMvc.perform(delete("/api/m-pvp-ranking-rewards/{id}", mPvpRankingReward.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database is empty
        List<MPvpRankingReward> mPvpRankingRewardList = mPvpRankingRewardRepository.findAll();
        assertThat(mPvpRankingRewardList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(MPvpRankingReward.class);
        MPvpRankingReward mPvpRankingReward1 = new MPvpRankingReward();
        mPvpRankingReward1.setId(1L);
        MPvpRankingReward mPvpRankingReward2 = new MPvpRankingReward();
        mPvpRankingReward2.setId(mPvpRankingReward1.getId());
        assertThat(mPvpRankingReward1).isEqualTo(mPvpRankingReward2);
        mPvpRankingReward2.setId(2L);
        assertThat(mPvpRankingReward1).isNotEqualTo(mPvpRankingReward2);
        mPvpRankingReward1.setId(null);
        assertThat(mPvpRankingReward1).isNotEqualTo(mPvpRankingReward2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(MPvpRankingRewardDTO.class);
        MPvpRankingRewardDTO mPvpRankingRewardDTO1 = new MPvpRankingRewardDTO();
        mPvpRankingRewardDTO1.setId(1L);
        MPvpRankingRewardDTO mPvpRankingRewardDTO2 = new MPvpRankingRewardDTO();
        assertThat(mPvpRankingRewardDTO1).isNotEqualTo(mPvpRankingRewardDTO2);
        mPvpRankingRewardDTO2.setId(mPvpRankingRewardDTO1.getId());
        assertThat(mPvpRankingRewardDTO1).isEqualTo(mPvpRankingRewardDTO2);
        mPvpRankingRewardDTO2.setId(2L);
        assertThat(mPvpRankingRewardDTO1).isNotEqualTo(mPvpRankingRewardDTO2);
        mPvpRankingRewardDTO1.setId(null);
        assertThat(mPvpRankingRewardDTO1).isNotEqualTo(mPvpRankingRewardDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(mPvpRankingRewardMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(mPvpRankingRewardMapper.fromId(null)).isNull();
    }
}
