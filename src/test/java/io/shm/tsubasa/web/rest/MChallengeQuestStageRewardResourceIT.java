package io.shm.tsubasa.web.rest;

import io.shm.tsubasa.TsubasaApp;
import io.shm.tsubasa.domain.MChallengeQuestStageReward;
import io.shm.tsubasa.repository.MChallengeQuestStageRewardRepository;
import io.shm.tsubasa.service.MChallengeQuestStageRewardService;
import io.shm.tsubasa.service.dto.MChallengeQuestStageRewardDTO;
import io.shm.tsubasa.service.mapper.MChallengeQuestStageRewardMapper;
import io.shm.tsubasa.web.rest.errors.ExceptionTranslator;
import io.shm.tsubasa.service.dto.MChallengeQuestStageRewardCriteria;
import io.shm.tsubasa.service.MChallengeQuestStageRewardQueryService;

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
 * Integration tests for the {@Link MChallengeQuestStageRewardResource} REST controller.
 */
@SpringBootTest(classes = TsubasaApp.class)
public class MChallengeQuestStageRewardResourceIT {

    private static final Integer DEFAULT_STAGE_ID = 1;
    private static final Integer UPDATED_STAGE_ID = 2;

    private static final Integer DEFAULT_EXP = 1;
    private static final Integer UPDATED_EXP = 2;

    private static final Integer DEFAULT_COIN = 1;
    private static final Integer UPDATED_COIN = 2;

    private static final Integer DEFAULT_GUILD_POINT = 1;
    private static final Integer UPDATED_GUILD_POINT = 2;

    private static final Integer DEFAULT_CLEAR_REWARD_GROUP_ID = 1;
    private static final Integer UPDATED_CLEAR_REWARD_GROUP_ID = 2;

    private static final Integer DEFAULT_CLEAR_REWARD_WEIGHT_ID = 1;
    private static final Integer UPDATED_CLEAR_REWARD_WEIGHT_ID = 2;

    private static final Integer DEFAULT_ACHIEVEMENT_REWARD_GROUP_ID = 1;
    private static final Integer UPDATED_ACHIEVEMENT_REWARD_GROUP_ID = 2;

    private static final Integer DEFAULT_COOP_GROUP_ID = 1;
    private static final Integer UPDATED_COOP_GROUP_ID = 2;

    private static final Integer DEFAULT_SPECIAL_REWARD_GROUP_ID = 1;
    private static final Integer UPDATED_SPECIAL_REWARD_GROUP_ID = 2;

    private static final Integer DEFAULT_SPECIAL_REWARD_AMOUNT = 1;
    private static final Integer UPDATED_SPECIAL_REWARD_AMOUNT = 2;

    private static final Integer DEFAULT_GOAL_REWARD_GROUP_ID = 1;
    private static final Integer UPDATED_GOAL_REWARD_GROUP_ID = 2;

    @Autowired
    private MChallengeQuestStageRewardRepository mChallengeQuestStageRewardRepository;

    @Autowired
    private MChallengeQuestStageRewardMapper mChallengeQuestStageRewardMapper;

    @Autowired
    private MChallengeQuestStageRewardService mChallengeQuestStageRewardService;

    @Autowired
    private MChallengeQuestStageRewardQueryService mChallengeQuestStageRewardQueryService;

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

    private MockMvc restMChallengeQuestStageRewardMockMvc;

    private MChallengeQuestStageReward mChallengeQuestStageReward;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final MChallengeQuestStageRewardResource mChallengeQuestStageRewardResource = new MChallengeQuestStageRewardResource(mChallengeQuestStageRewardService, mChallengeQuestStageRewardQueryService);
        this.restMChallengeQuestStageRewardMockMvc = MockMvcBuilders.standaloneSetup(mChallengeQuestStageRewardResource)
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
    public static MChallengeQuestStageReward createEntity(EntityManager em) {
        MChallengeQuestStageReward mChallengeQuestStageReward = new MChallengeQuestStageReward()
            .stageId(DEFAULT_STAGE_ID)
            .exp(DEFAULT_EXP)
            .coin(DEFAULT_COIN)
            .guildPoint(DEFAULT_GUILD_POINT)
            .clearRewardGroupId(DEFAULT_CLEAR_REWARD_GROUP_ID)
            .clearRewardWeightId(DEFAULT_CLEAR_REWARD_WEIGHT_ID)
            .achievementRewardGroupId(DEFAULT_ACHIEVEMENT_REWARD_GROUP_ID)
            .coopGroupId(DEFAULT_COOP_GROUP_ID)
            .specialRewardGroupId(DEFAULT_SPECIAL_REWARD_GROUP_ID)
            .specialRewardAmount(DEFAULT_SPECIAL_REWARD_AMOUNT)
            .goalRewardGroupId(DEFAULT_GOAL_REWARD_GROUP_ID);
        return mChallengeQuestStageReward;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static MChallengeQuestStageReward createUpdatedEntity(EntityManager em) {
        MChallengeQuestStageReward mChallengeQuestStageReward = new MChallengeQuestStageReward()
            .stageId(UPDATED_STAGE_ID)
            .exp(UPDATED_EXP)
            .coin(UPDATED_COIN)
            .guildPoint(UPDATED_GUILD_POINT)
            .clearRewardGroupId(UPDATED_CLEAR_REWARD_GROUP_ID)
            .clearRewardWeightId(UPDATED_CLEAR_REWARD_WEIGHT_ID)
            .achievementRewardGroupId(UPDATED_ACHIEVEMENT_REWARD_GROUP_ID)
            .coopGroupId(UPDATED_COOP_GROUP_ID)
            .specialRewardGroupId(UPDATED_SPECIAL_REWARD_GROUP_ID)
            .specialRewardAmount(UPDATED_SPECIAL_REWARD_AMOUNT)
            .goalRewardGroupId(UPDATED_GOAL_REWARD_GROUP_ID);
        return mChallengeQuestStageReward;
    }

    @BeforeEach
    public void initTest() {
        mChallengeQuestStageReward = createEntity(em);
    }

    @Test
    @Transactional
    public void createMChallengeQuestStageReward() throws Exception {
        int databaseSizeBeforeCreate = mChallengeQuestStageRewardRepository.findAll().size();

        // Create the MChallengeQuestStageReward
        MChallengeQuestStageRewardDTO mChallengeQuestStageRewardDTO = mChallengeQuestStageRewardMapper.toDto(mChallengeQuestStageReward);
        restMChallengeQuestStageRewardMockMvc.perform(post("/api/m-challenge-quest-stage-rewards")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mChallengeQuestStageRewardDTO)))
            .andExpect(status().isCreated());

        // Validate the MChallengeQuestStageReward in the database
        List<MChallengeQuestStageReward> mChallengeQuestStageRewardList = mChallengeQuestStageRewardRepository.findAll();
        assertThat(mChallengeQuestStageRewardList).hasSize(databaseSizeBeforeCreate + 1);
        MChallengeQuestStageReward testMChallengeQuestStageReward = mChallengeQuestStageRewardList.get(mChallengeQuestStageRewardList.size() - 1);
        assertThat(testMChallengeQuestStageReward.getStageId()).isEqualTo(DEFAULT_STAGE_ID);
        assertThat(testMChallengeQuestStageReward.getExp()).isEqualTo(DEFAULT_EXP);
        assertThat(testMChallengeQuestStageReward.getCoin()).isEqualTo(DEFAULT_COIN);
        assertThat(testMChallengeQuestStageReward.getGuildPoint()).isEqualTo(DEFAULT_GUILD_POINT);
        assertThat(testMChallengeQuestStageReward.getClearRewardGroupId()).isEqualTo(DEFAULT_CLEAR_REWARD_GROUP_ID);
        assertThat(testMChallengeQuestStageReward.getClearRewardWeightId()).isEqualTo(DEFAULT_CLEAR_REWARD_WEIGHT_ID);
        assertThat(testMChallengeQuestStageReward.getAchievementRewardGroupId()).isEqualTo(DEFAULT_ACHIEVEMENT_REWARD_GROUP_ID);
        assertThat(testMChallengeQuestStageReward.getCoopGroupId()).isEqualTo(DEFAULT_COOP_GROUP_ID);
        assertThat(testMChallengeQuestStageReward.getSpecialRewardGroupId()).isEqualTo(DEFAULT_SPECIAL_REWARD_GROUP_ID);
        assertThat(testMChallengeQuestStageReward.getSpecialRewardAmount()).isEqualTo(DEFAULT_SPECIAL_REWARD_AMOUNT);
        assertThat(testMChallengeQuestStageReward.getGoalRewardGroupId()).isEqualTo(DEFAULT_GOAL_REWARD_GROUP_ID);
    }

    @Test
    @Transactional
    public void createMChallengeQuestStageRewardWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = mChallengeQuestStageRewardRepository.findAll().size();

        // Create the MChallengeQuestStageReward with an existing ID
        mChallengeQuestStageReward.setId(1L);
        MChallengeQuestStageRewardDTO mChallengeQuestStageRewardDTO = mChallengeQuestStageRewardMapper.toDto(mChallengeQuestStageReward);

        // An entity with an existing ID cannot be created, so this API call must fail
        restMChallengeQuestStageRewardMockMvc.perform(post("/api/m-challenge-quest-stage-rewards")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mChallengeQuestStageRewardDTO)))
            .andExpect(status().isBadRequest());

        // Validate the MChallengeQuestStageReward in the database
        List<MChallengeQuestStageReward> mChallengeQuestStageRewardList = mChallengeQuestStageRewardRepository.findAll();
        assertThat(mChallengeQuestStageRewardList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkStageIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = mChallengeQuestStageRewardRepository.findAll().size();
        // set the field null
        mChallengeQuestStageReward.setStageId(null);

        // Create the MChallengeQuestStageReward, which fails.
        MChallengeQuestStageRewardDTO mChallengeQuestStageRewardDTO = mChallengeQuestStageRewardMapper.toDto(mChallengeQuestStageReward);

        restMChallengeQuestStageRewardMockMvc.perform(post("/api/m-challenge-quest-stage-rewards")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mChallengeQuestStageRewardDTO)))
            .andExpect(status().isBadRequest());

        List<MChallengeQuestStageReward> mChallengeQuestStageRewardList = mChallengeQuestStageRewardRepository.findAll();
        assertThat(mChallengeQuestStageRewardList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkExpIsRequired() throws Exception {
        int databaseSizeBeforeTest = mChallengeQuestStageRewardRepository.findAll().size();
        // set the field null
        mChallengeQuestStageReward.setExp(null);

        // Create the MChallengeQuestStageReward, which fails.
        MChallengeQuestStageRewardDTO mChallengeQuestStageRewardDTO = mChallengeQuestStageRewardMapper.toDto(mChallengeQuestStageReward);

        restMChallengeQuestStageRewardMockMvc.perform(post("/api/m-challenge-quest-stage-rewards")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mChallengeQuestStageRewardDTO)))
            .andExpect(status().isBadRequest());

        List<MChallengeQuestStageReward> mChallengeQuestStageRewardList = mChallengeQuestStageRewardRepository.findAll();
        assertThat(mChallengeQuestStageRewardList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkCoinIsRequired() throws Exception {
        int databaseSizeBeforeTest = mChallengeQuestStageRewardRepository.findAll().size();
        // set the field null
        mChallengeQuestStageReward.setCoin(null);

        // Create the MChallengeQuestStageReward, which fails.
        MChallengeQuestStageRewardDTO mChallengeQuestStageRewardDTO = mChallengeQuestStageRewardMapper.toDto(mChallengeQuestStageReward);

        restMChallengeQuestStageRewardMockMvc.perform(post("/api/m-challenge-quest-stage-rewards")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mChallengeQuestStageRewardDTO)))
            .andExpect(status().isBadRequest());

        List<MChallengeQuestStageReward> mChallengeQuestStageRewardList = mChallengeQuestStageRewardRepository.findAll();
        assertThat(mChallengeQuestStageRewardList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkGuildPointIsRequired() throws Exception {
        int databaseSizeBeforeTest = mChallengeQuestStageRewardRepository.findAll().size();
        // set the field null
        mChallengeQuestStageReward.setGuildPoint(null);

        // Create the MChallengeQuestStageReward, which fails.
        MChallengeQuestStageRewardDTO mChallengeQuestStageRewardDTO = mChallengeQuestStageRewardMapper.toDto(mChallengeQuestStageReward);

        restMChallengeQuestStageRewardMockMvc.perform(post("/api/m-challenge-quest-stage-rewards")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mChallengeQuestStageRewardDTO)))
            .andExpect(status().isBadRequest());

        List<MChallengeQuestStageReward> mChallengeQuestStageRewardList = mChallengeQuestStageRewardRepository.findAll();
        assertThat(mChallengeQuestStageRewardList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkClearRewardGroupIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = mChallengeQuestStageRewardRepository.findAll().size();
        // set the field null
        mChallengeQuestStageReward.setClearRewardGroupId(null);

        // Create the MChallengeQuestStageReward, which fails.
        MChallengeQuestStageRewardDTO mChallengeQuestStageRewardDTO = mChallengeQuestStageRewardMapper.toDto(mChallengeQuestStageReward);

        restMChallengeQuestStageRewardMockMvc.perform(post("/api/m-challenge-quest-stage-rewards")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mChallengeQuestStageRewardDTO)))
            .andExpect(status().isBadRequest());

        List<MChallengeQuestStageReward> mChallengeQuestStageRewardList = mChallengeQuestStageRewardRepository.findAll();
        assertThat(mChallengeQuestStageRewardList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkClearRewardWeightIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = mChallengeQuestStageRewardRepository.findAll().size();
        // set the field null
        mChallengeQuestStageReward.setClearRewardWeightId(null);

        // Create the MChallengeQuestStageReward, which fails.
        MChallengeQuestStageRewardDTO mChallengeQuestStageRewardDTO = mChallengeQuestStageRewardMapper.toDto(mChallengeQuestStageReward);

        restMChallengeQuestStageRewardMockMvc.perform(post("/api/m-challenge-quest-stage-rewards")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mChallengeQuestStageRewardDTO)))
            .andExpect(status().isBadRequest());

        List<MChallengeQuestStageReward> mChallengeQuestStageRewardList = mChallengeQuestStageRewardRepository.findAll();
        assertThat(mChallengeQuestStageRewardList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkAchievementRewardGroupIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = mChallengeQuestStageRewardRepository.findAll().size();
        // set the field null
        mChallengeQuestStageReward.setAchievementRewardGroupId(null);

        // Create the MChallengeQuestStageReward, which fails.
        MChallengeQuestStageRewardDTO mChallengeQuestStageRewardDTO = mChallengeQuestStageRewardMapper.toDto(mChallengeQuestStageReward);

        restMChallengeQuestStageRewardMockMvc.perform(post("/api/m-challenge-quest-stage-rewards")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mChallengeQuestStageRewardDTO)))
            .andExpect(status().isBadRequest());

        List<MChallengeQuestStageReward> mChallengeQuestStageRewardList = mChallengeQuestStageRewardRepository.findAll();
        assertThat(mChallengeQuestStageRewardList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkCoopGroupIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = mChallengeQuestStageRewardRepository.findAll().size();
        // set the field null
        mChallengeQuestStageReward.setCoopGroupId(null);

        // Create the MChallengeQuestStageReward, which fails.
        MChallengeQuestStageRewardDTO mChallengeQuestStageRewardDTO = mChallengeQuestStageRewardMapper.toDto(mChallengeQuestStageReward);

        restMChallengeQuestStageRewardMockMvc.perform(post("/api/m-challenge-quest-stage-rewards")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mChallengeQuestStageRewardDTO)))
            .andExpect(status().isBadRequest());

        List<MChallengeQuestStageReward> mChallengeQuestStageRewardList = mChallengeQuestStageRewardRepository.findAll();
        assertThat(mChallengeQuestStageRewardList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkSpecialRewardAmountIsRequired() throws Exception {
        int databaseSizeBeforeTest = mChallengeQuestStageRewardRepository.findAll().size();
        // set the field null
        mChallengeQuestStageReward.setSpecialRewardAmount(null);

        // Create the MChallengeQuestStageReward, which fails.
        MChallengeQuestStageRewardDTO mChallengeQuestStageRewardDTO = mChallengeQuestStageRewardMapper.toDto(mChallengeQuestStageReward);

        restMChallengeQuestStageRewardMockMvc.perform(post("/api/m-challenge-quest-stage-rewards")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mChallengeQuestStageRewardDTO)))
            .andExpect(status().isBadRequest());

        List<MChallengeQuestStageReward> mChallengeQuestStageRewardList = mChallengeQuestStageRewardRepository.findAll();
        assertThat(mChallengeQuestStageRewardList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllMChallengeQuestStageRewards() throws Exception {
        // Initialize the database
        mChallengeQuestStageRewardRepository.saveAndFlush(mChallengeQuestStageReward);

        // Get all the mChallengeQuestStageRewardList
        restMChallengeQuestStageRewardMockMvc.perform(get("/api/m-challenge-quest-stage-rewards?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(mChallengeQuestStageReward.getId().intValue())))
            .andExpect(jsonPath("$.[*].stageId").value(hasItem(DEFAULT_STAGE_ID)))
            .andExpect(jsonPath("$.[*].exp").value(hasItem(DEFAULT_EXP)))
            .andExpect(jsonPath("$.[*].coin").value(hasItem(DEFAULT_COIN)))
            .andExpect(jsonPath("$.[*].guildPoint").value(hasItem(DEFAULT_GUILD_POINT)))
            .andExpect(jsonPath("$.[*].clearRewardGroupId").value(hasItem(DEFAULT_CLEAR_REWARD_GROUP_ID)))
            .andExpect(jsonPath("$.[*].clearRewardWeightId").value(hasItem(DEFAULT_CLEAR_REWARD_WEIGHT_ID)))
            .andExpect(jsonPath("$.[*].achievementRewardGroupId").value(hasItem(DEFAULT_ACHIEVEMENT_REWARD_GROUP_ID)))
            .andExpect(jsonPath("$.[*].coopGroupId").value(hasItem(DEFAULT_COOP_GROUP_ID)))
            .andExpect(jsonPath("$.[*].specialRewardGroupId").value(hasItem(DEFAULT_SPECIAL_REWARD_GROUP_ID)))
            .andExpect(jsonPath("$.[*].specialRewardAmount").value(hasItem(DEFAULT_SPECIAL_REWARD_AMOUNT)))
            .andExpect(jsonPath("$.[*].goalRewardGroupId").value(hasItem(DEFAULT_GOAL_REWARD_GROUP_ID)));
    }
    
    @Test
    @Transactional
    public void getMChallengeQuestStageReward() throws Exception {
        // Initialize the database
        mChallengeQuestStageRewardRepository.saveAndFlush(mChallengeQuestStageReward);

        // Get the mChallengeQuestStageReward
        restMChallengeQuestStageRewardMockMvc.perform(get("/api/m-challenge-quest-stage-rewards/{id}", mChallengeQuestStageReward.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(mChallengeQuestStageReward.getId().intValue()))
            .andExpect(jsonPath("$.stageId").value(DEFAULT_STAGE_ID))
            .andExpect(jsonPath("$.exp").value(DEFAULT_EXP))
            .andExpect(jsonPath("$.coin").value(DEFAULT_COIN))
            .andExpect(jsonPath("$.guildPoint").value(DEFAULT_GUILD_POINT))
            .andExpect(jsonPath("$.clearRewardGroupId").value(DEFAULT_CLEAR_REWARD_GROUP_ID))
            .andExpect(jsonPath("$.clearRewardWeightId").value(DEFAULT_CLEAR_REWARD_WEIGHT_ID))
            .andExpect(jsonPath("$.achievementRewardGroupId").value(DEFAULT_ACHIEVEMENT_REWARD_GROUP_ID))
            .andExpect(jsonPath("$.coopGroupId").value(DEFAULT_COOP_GROUP_ID))
            .andExpect(jsonPath("$.specialRewardGroupId").value(DEFAULT_SPECIAL_REWARD_GROUP_ID))
            .andExpect(jsonPath("$.specialRewardAmount").value(DEFAULT_SPECIAL_REWARD_AMOUNT))
            .andExpect(jsonPath("$.goalRewardGroupId").value(DEFAULT_GOAL_REWARD_GROUP_ID));
    }

    @Test
    @Transactional
    public void getAllMChallengeQuestStageRewardsByStageIdIsEqualToSomething() throws Exception {
        // Initialize the database
        mChallengeQuestStageRewardRepository.saveAndFlush(mChallengeQuestStageReward);

        // Get all the mChallengeQuestStageRewardList where stageId equals to DEFAULT_STAGE_ID
        defaultMChallengeQuestStageRewardShouldBeFound("stageId.equals=" + DEFAULT_STAGE_ID);

        // Get all the mChallengeQuestStageRewardList where stageId equals to UPDATED_STAGE_ID
        defaultMChallengeQuestStageRewardShouldNotBeFound("stageId.equals=" + UPDATED_STAGE_ID);
    }

    @Test
    @Transactional
    public void getAllMChallengeQuestStageRewardsByStageIdIsInShouldWork() throws Exception {
        // Initialize the database
        mChallengeQuestStageRewardRepository.saveAndFlush(mChallengeQuestStageReward);

        // Get all the mChallengeQuestStageRewardList where stageId in DEFAULT_STAGE_ID or UPDATED_STAGE_ID
        defaultMChallengeQuestStageRewardShouldBeFound("stageId.in=" + DEFAULT_STAGE_ID + "," + UPDATED_STAGE_ID);

        // Get all the mChallengeQuestStageRewardList where stageId equals to UPDATED_STAGE_ID
        defaultMChallengeQuestStageRewardShouldNotBeFound("stageId.in=" + UPDATED_STAGE_ID);
    }

    @Test
    @Transactional
    public void getAllMChallengeQuestStageRewardsByStageIdIsNullOrNotNull() throws Exception {
        // Initialize the database
        mChallengeQuestStageRewardRepository.saveAndFlush(mChallengeQuestStageReward);

        // Get all the mChallengeQuestStageRewardList where stageId is not null
        defaultMChallengeQuestStageRewardShouldBeFound("stageId.specified=true");

        // Get all the mChallengeQuestStageRewardList where stageId is null
        defaultMChallengeQuestStageRewardShouldNotBeFound("stageId.specified=false");
    }

    @Test
    @Transactional
    public void getAllMChallengeQuestStageRewardsByStageIdIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mChallengeQuestStageRewardRepository.saveAndFlush(mChallengeQuestStageReward);

        // Get all the mChallengeQuestStageRewardList where stageId greater than or equals to DEFAULT_STAGE_ID
        defaultMChallengeQuestStageRewardShouldBeFound("stageId.greaterOrEqualThan=" + DEFAULT_STAGE_ID);

        // Get all the mChallengeQuestStageRewardList where stageId greater than or equals to UPDATED_STAGE_ID
        defaultMChallengeQuestStageRewardShouldNotBeFound("stageId.greaterOrEqualThan=" + UPDATED_STAGE_ID);
    }

    @Test
    @Transactional
    public void getAllMChallengeQuestStageRewardsByStageIdIsLessThanSomething() throws Exception {
        // Initialize the database
        mChallengeQuestStageRewardRepository.saveAndFlush(mChallengeQuestStageReward);

        // Get all the mChallengeQuestStageRewardList where stageId less than or equals to DEFAULT_STAGE_ID
        defaultMChallengeQuestStageRewardShouldNotBeFound("stageId.lessThan=" + DEFAULT_STAGE_ID);

        // Get all the mChallengeQuestStageRewardList where stageId less than or equals to UPDATED_STAGE_ID
        defaultMChallengeQuestStageRewardShouldBeFound("stageId.lessThan=" + UPDATED_STAGE_ID);
    }


    @Test
    @Transactional
    public void getAllMChallengeQuestStageRewardsByExpIsEqualToSomething() throws Exception {
        // Initialize the database
        mChallengeQuestStageRewardRepository.saveAndFlush(mChallengeQuestStageReward);

        // Get all the mChallengeQuestStageRewardList where exp equals to DEFAULT_EXP
        defaultMChallengeQuestStageRewardShouldBeFound("exp.equals=" + DEFAULT_EXP);

        // Get all the mChallengeQuestStageRewardList where exp equals to UPDATED_EXP
        defaultMChallengeQuestStageRewardShouldNotBeFound("exp.equals=" + UPDATED_EXP);
    }

    @Test
    @Transactional
    public void getAllMChallengeQuestStageRewardsByExpIsInShouldWork() throws Exception {
        // Initialize the database
        mChallengeQuestStageRewardRepository.saveAndFlush(mChallengeQuestStageReward);

        // Get all the mChallengeQuestStageRewardList where exp in DEFAULT_EXP or UPDATED_EXP
        defaultMChallengeQuestStageRewardShouldBeFound("exp.in=" + DEFAULT_EXP + "," + UPDATED_EXP);

        // Get all the mChallengeQuestStageRewardList where exp equals to UPDATED_EXP
        defaultMChallengeQuestStageRewardShouldNotBeFound("exp.in=" + UPDATED_EXP);
    }

    @Test
    @Transactional
    public void getAllMChallengeQuestStageRewardsByExpIsNullOrNotNull() throws Exception {
        // Initialize the database
        mChallengeQuestStageRewardRepository.saveAndFlush(mChallengeQuestStageReward);

        // Get all the mChallengeQuestStageRewardList where exp is not null
        defaultMChallengeQuestStageRewardShouldBeFound("exp.specified=true");

        // Get all the mChallengeQuestStageRewardList where exp is null
        defaultMChallengeQuestStageRewardShouldNotBeFound("exp.specified=false");
    }

    @Test
    @Transactional
    public void getAllMChallengeQuestStageRewardsByExpIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mChallengeQuestStageRewardRepository.saveAndFlush(mChallengeQuestStageReward);

        // Get all the mChallengeQuestStageRewardList where exp greater than or equals to DEFAULT_EXP
        defaultMChallengeQuestStageRewardShouldBeFound("exp.greaterOrEqualThan=" + DEFAULT_EXP);

        // Get all the mChallengeQuestStageRewardList where exp greater than or equals to UPDATED_EXP
        defaultMChallengeQuestStageRewardShouldNotBeFound("exp.greaterOrEqualThan=" + UPDATED_EXP);
    }

    @Test
    @Transactional
    public void getAllMChallengeQuestStageRewardsByExpIsLessThanSomething() throws Exception {
        // Initialize the database
        mChallengeQuestStageRewardRepository.saveAndFlush(mChallengeQuestStageReward);

        // Get all the mChallengeQuestStageRewardList where exp less than or equals to DEFAULT_EXP
        defaultMChallengeQuestStageRewardShouldNotBeFound("exp.lessThan=" + DEFAULT_EXP);

        // Get all the mChallengeQuestStageRewardList where exp less than or equals to UPDATED_EXP
        defaultMChallengeQuestStageRewardShouldBeFound("exp.lessThan=" + UPDATED_EXP);
    }


    @Test
    @Transactional
    public void getAllMChallengeQuestStageRewardsByCoinIsEqualToSomething() throws Exception {
        // Initialize the database
        mChallengeQuestStageRewardRepository.saveAndFlush(mChallengeQuestStageReward);

        // Get all the mChallengeQuestStageRewardList where coin equals to DEFAULT_COIN
        defaultMChallengeQuestStageRewardShouldBeFound("coin.equals=" + DEFAULT_COIN);

        // Get all the mChallengeQuestStageRewardList where coin equals to UPDATED_COIN
        defaultMChallengeQuestStageRewardShouldNotBeFound("coin.equals=" + UPDATED_COIN);
    }

    @Test
    @Transactional
    public void getAllMChallengeQuestStageRewardsByCoinIsInShouldWork() throws Exception {
        // Initialize the database
        mChallengeQuestStageRewardRepository.saveAndFlush(mChallengeQuestStageReward);

        // Get all the mChallengeQuestStageRewardList where coin in DEFAULT_COIN or UPDATED_COIN
        defaultMChallengeQuestStageRewardShouldBeFound("coin.in=" + DEFAULT_COIN + "," + UPDATED_COIN);

        // Get all the mChallengeQuestStageRewardList where coin equals to UPDATED_COIN
        defaultMChallengeQuestStageRewardShouldNotBeFound("coin.in=" + UPDATED_COIN);
    }

    @Test
    @Transactional
    public void getAllMChallengeQuestStageRewardsByCoinIsNullOrNotNull() throws Exception {
        // Initialize the database
        mChallengeQuestStageRewardRepository.saveAndFlush(mChallengeQuestStageReward);

        // Get all the mChallengeQuestStageRewardList where coin is not null
        defaultMChallengeQuestStageRewardShouldBeFound("coin.specified=true");

        // Get all the mChallengeQuestStageRewardList where coin is null
        defaultMChallengeQuestStageRewardShouldNotBeFound("coin.specified=false");
    }

    @Test
    @Transactional
    public void getAllMChallengeQuestStageRewardsByCoinIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mChallengeQuestStageRewardRepository.saveAndFlush(mChallengeQuestStageReward);

        // Get all the mChallengeQuestStageRewardList where coin greater than or equals to DEFAULT_COIN
        defaultMChallengeQuestStageRewardShouldBeFound("coin.greaterOrEqualThan=" + DEFAULT_COIN);

        // Get all the mChallengeQuestStageRewardList where coin greater than or equals to UPDATED_COIN
        defaultMChallengeQuestStageRewardShouldNotBeFound("coin.greaterOrEqualThan=" + UPDATED_COIN);
    }

    @Test
    @Transactional
    public void getAllMChallengeQuestStageRewardsByCoinIsLessThanSomething() throws Exception {
        // Initialize the database
        mChallengeQuestStageRewardRepository.saveAndFlush(mChallengeQuestStageReward);

        // Get all the mChallengeQuestStageRewardList where coin less than or equals to DEFAULT_COIN
        defaultMChallengeQuestStageRewardShouldNotBeFound("coin.lessThan=" + DEFAULT_COIN);

        // Get all the mChallengeQuestStageRewardList where coin less than or equals to UPDATED_COIN
        defaultMChallengeQuestStageRewardShouldBeFound("coin.lessThan=" + UPDATED_COIN);
    }


    @Test
    @Transactional
    public void getAllMChallengeQuestStageRewardsByGuildPointIsEqualToSomething() throws Exception {
        // Initialize the database
        mChallengeQuestStageRewardRepository.saveAndFlush(mChallengeQuestStageReward);

        // Get all the mChallengeQuestStageRewardList where guildPoint equals to DEFAULT_GUILD_POINT
        defaultMChallengeQuestStageRewardShouldBeFound("guildPoint.equals=" + DEFAULT_GUILD_POINT);

        // Get all the mChallengeQuestStageRewardList where guildPoint equals to UPDATED_GUILD_POINT
        defaultMChallengeQuestStageRewardShouldNotBeFound("guildPoint.equals=" + UPDATED_GUILD_POINT);
    }

    @Test
    @Transactional
    public void getAllMChallengeQuestStageRewardsByGuildPointIsInShouldWork() throws Exception {
        // Initialize the database
        mChallengeQuestStageRewardRepository.saveAndFlush(mChallengeQuestStageReward);

        // Get all the mChallengeQuestStageRewardList where guildPoint in DEFAULT_GUILD_POINT or UPDATED_GUILD_POINT
        defaultMChallengeQuestStageRewardShouldBeFound("guildPoint.in=" + DEFAULT_GUILD_POINT + "," + UPDATED_GUILD_POINT);

        // Get all the mChallengeQuestStageRewardList where guildPoint equals to UPDATED_GUILD_POINT
        defaultMChallengeQuestStageRewardShouldNotBeFound("guildPoint.in=" + UPDATED_GUILD_POINT);
    }

    @Test
    @Transactional
    public void getAllMChallengeQuestStageRewardsByGuildPointIsNullOrNotNull() throws Exception {
        // Initialize the database
        mChallengeQuestStageRewardRepository.saveAndFlush(mChallengeQuestStageReward);

        // Get all the mChallengeQuestStageRewardList where guildPoint is not null
        defaultMChallengeQuestStageRewardShouldBeFound("guildPoint.specified=true");

        // Get all the mChallengeQuestStageRewardList where guildPoint is null
        defaultMChallengeQuestStageRewardShouldNotBeFound("guildPoint.specified=false");
    }

    @Test
    @Transactional
    public void getAllMChallengeQuestStageRewardsByGuildPointIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mChallengeQuestStageRewardRepository.saveAndFlush(mChallengeQuestStageReward);

        // Get all the mChallengeQuestStageRewardList where guildPoint greater than or equals to DEFAULT_GUILD_POINT
        defaultMChallengeQuestStageRewardShouldBeFound("guildPoint.greaterOrEqualThan=" + DEFAULT_GUILD_POINT);

        // Get all the mChallengeQuestStageRewardList where guildPoint greater than or equals to UPDATED_GUILD_POINT
        defaultMChallengeQuestStageRewardShouldNotBeFound("guildPoint.greaterOrEqualThan=" + UPDATED_GUILD_POINT);
    }

    @Test
    @Transactional
    public void getAllMChallengeQuestStageRewardsByGuildPointIsLessThanSomething() throws Exception {
        // Initialize the database
        mChallengeQuestStageRewardRepository.saveAndFlush(mChallengeQuestStageReward);

        // Get all the mChallengeQuestStageRewardList where guildPoint less than or equals to DEFAULT_GUILD_POINT
        defaultMChallengeQuestStageRewardShouldNotBeFound("guildPoint.lessThan=" + DEFAULT_GUILD_POINT);

        // Get all the mChallengeQuestStageRewardList where guildPoint less than or equals to UPDATED_GUILD_POINT
        defaultMChallengeQuestStageRewardShouldBeFound("guildPoint.lessThan=" + UPDATED_GUILD_POINT);
    }


    @Test
    @Transactional
    public void getAllMChallengeQuestStageRewardsByClearRewardGroupIdIsEqualToSomething() throws Exception {
        // Initialize the database
        mChallengeQuestStageRewardRepository.saveAndFlush(mChallengeQuestStageReward);

        // Get all the mChallengeQuestStageRewardList where clearRewardGroupId equals to DEFAULT_CLEAR_REWARD_GROUP_ID
        defaultMChallengeQuestStageRewardShouldBeFound("clearRewardGroupId.equals=" + DEFAULT_CLEAR_REWARD_GROUP_ID);

        // Get all the mChallengeQuestStageRewardList where clearRewardGroupId equals to UPDATED_CLEAR_REWARD_GROUP_ID
        defaultMChallengeQuestStageRewardShouldNotBeFound("clearRewardGroupId.equals=" + UPDATED_CLEAR_REWARD_GROUP_ID);
    }

    @Test
    @Transactional
    public void getAllMChallengeQuestStageRewardsByClearRewardGroupIdIsInShouldWork() throws Exception {
        // Initialize the database
        mChallengeQuestStageRewardRepository.saveAndFlush(mChallengeQuestStageReward);

        // Get all the mChallengeQuestStageRewardList where clearRewardGroupId in DEFAULT_CLEAR_REWARD_GROUP_ID or UPDATED_CLEAR_REWARD_GROUP_ID
        defaultMChallengeQuestStageRewardShouldBeFound("clearRewardGroupId.in=" + DEFAULT_CLEAR_REWARD_GROUP_ID + "," + UPDATED_CLEAR_REWARD_GROUP_ID);

        // Get all the mChallengeQuestStageRewardList where clearRewardGroupId equals to UPDATED_CLEAR_REWARD_GROUP_ID
        defaultMChallengeQuestStageRewardShouldNotBeFound("clearRewardGroupId.in=" + UPDATED_CLEAR_REWARD_GROUP_ID);
    }

    @Test
    @Transactional
    public void getAllMChallengeQuestStageRewardsByClearRewardGroupIdIsNullOrNotNull() throws Exception {
        // Initialize the database
        mChallengeQuestStageRewardRepository.saveAndFlush(mChallengeQuestStageReward);

        // Get all the mChallengeQuestStageRewardList where clearRewardGroupId is not null
        defaultMChallengeQuestStageRewardShouldBeFound("clearRewardGroupId.specified=true");

        // Get all the mChallengeQuestStageRewardList where clearRewardGroupId is null
        defaultMChallengeQuestStageRewardShouldNotBeFound("clearRewardGroupId.specified=false");
    }

    @Test
    @Transactional
    public void getAllMChallengeQuestStageRewardsByClearRewardGroupIdIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mChallengeQuestStageRewardRepository.saveAndFlush(mChallengeQuestStageReward);

        // Get all the mChallengeQuestStageRewardList where clearRewardGroupId greater than or equals to DEFAULT_CLEAR_REWARD_GROUP_ID
        defaultMChallengeQuestStageRewardShouldBeFound("clearRewardGroupId.greaterOrEqualThan=" + DEFAULT_CLEAR_REWARD_GROUP_ID);

        // Get all the mChallengeQuestStageRewardList where clearRewardGroupId greater than or equals to UPDATED_CLEAR_REWARD_GROUP_ID
        defaultMChallengeQuestStageRewardShouldNotBeFound("clearRewardGroupId.greaterOrEqualThan=" + UPDATED_CLEAR_REWARD_GROUP_ID);
    }

    @Test
    @Transactional
    public void getAllMChallengeQuestStageRewardsByClearRewardGroupIdIsLessThanSomething() throws Exception {
        // Initialize the database
        mChallengeQuestStageRewardRepository.saveAndFlush(mChallengeQuestStageReward);

        // Get all the mChallengeQuestStageRewardList where clearRewardGroupId less than or equals to DEFAULT_CLEAR_REWARD_GROUP_ID
        defaultMChallengeQuestStageRewardShouldNotBeFound("clearRewardGroupId.lessThan=" + DEFAULT_CLEAR_REWARD_GROUP_ID);

        // Get all the mChallengeQuestStageRewardList where clearRewardGroupId less than or equals to UPDATED_CLEAR_REWARD_GROUP_ID
        defaultMChallengeQuestStageRewardShouldBeFound("clearRewardGroupId.lessThan=" + UPDATED_CLEAR_REWARD_GROUP_ID);
    }


    @Test
    @Transactional
    public void getAllMChallengeQuestStageRewardsByClearRewardWeightIdIsEqualToSomething() throws Exception {
        // Initialize the database
        mChallengeQuestStageRewardRepository.saveAndFlush(mChallengeQuestStageReward);

        // Get all the mChallengeQuestStageRewardList where clearRewardWeightId equals to DEFAULT_CLEAR_REWARD_WEIGHT_ID
        defaultMChallengeQuestStageRewardShouldBeFound("clearRewardWeightId.equals=" + DEFAULT_CLEAR_REWARD_WEIGHT_ID);

        // Get all the mChallengeQuestStageRewardList where clearRewardWeightId equals to UPDATED_CLEAR_REWARD_WEIGHT_ID
        defaultMChallengeQuestStageRewardShouldNotBeFound("clearRewardWeightId.equals=" + UPDATED_CLEAR_REWARD_WEIGHT_ID);
    }

    @Test
    @Transactional
    public void getAllMChallengeQuestStageRewardsByClearRewardWeightIdIsInShouldWork() throws Exception {
        // Initialize the database
        mChallengeQuestStageRewardRepository.saveAndFlush(mChallengeQuestStageReward);

        // Get all the mChallengeQuestStageRewardList where clearRewardWeightId in DEFAULT_CLEAR_REWARD_WEIGHT_ID or UPDATED_CLEAR_REWARD_WEIGHT_ID
        defaultMChallengeQuestStageRewardShouldBeFound("clearRewardWeightId.in=" + DEFAULT_CLEAR_REWARD_WEIGHT_ID + "," + UPDATED_CLEAR_REWARD_WEIGHT_ID);

        // Get all the mChallengeQuestStageRewardList where clearRewardWeightId equals to UPDATED_CLEAR_REWARD_WEIGHT_ID
        defaultMChallengeQuestStageRewardShouldNotBeFound("clearRewardWeightId.in=" + UPDATED_CLEAR_REWARD_WEIGHT_ID);
    }

    @Test
    @Transactional
    public void getAllMChallengeQuestStageRewardsByClearRewardWeightIdIsNullOrNotNull() throws Exception {
        // Initialize the database
        mChallengeQuestStageRewardRepository.saveAndFlush(mChallengeQuestStageReward);

        // Get all the mChallengeQuestStageRewardList where clearRewardWeightId is not null
        defaultMChallengeQuestStageRewardShouldBeFound("clearRewardWeightId.specified=true");

        // Get all the mChallengeQuestStageRewardList where clearRewardWeightId is null
        defaultMChallengeQuestStageRewardShouldNotBeFound("clearRewardWeightId.specified=false");
    }

    @Test
    @Transactional
    public void getAllMChallengeQuestStageRewardsByClearRewardWeightIdIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mChallengeQuestStageRewardRepository.saveAndFlush(mChallengeQuestStageReward);

        // Get all the mChallengeQuestStageRewardList where clearRewardWeightId greater than or equals to DEFAULT_CLEAR_REWARD_WEIGHT_ID
        defaultMChallengeQuestStageRewardShouldBeFound("clearRewardWeightId.greaterOrEqualThan=" + DEFAULT_CLEAR_REWARD_WEIGHT_ID);

        // Get all the mChallengeQuestStageRewardList where clearRewardWeightId greater than or equals to UPDATED_CLEAR_REWARD_WEIGHT_ID
        defaultMChallengeQuestStageRewardShouldNotBeFound("clearRewardWeightId.greaterOrEqualThan=" + UPDATED_CLEAR_REWARD_WEIGHT_ID);
    }

    @Test
    @Transactional
    public void getAllMChallengeQuestStageRewardsByClearRewardWeightIdIsLessThanSomething() throws Exception {
        // Initialize the database
        mChallengeQuestStageRewardRepository.saveAndFlush(mChallengeQuestStageReward);

        // Get all the mChallengeQuestStageRewardList where clearRewardWeightId less than or equals to DEFAULT_CLEAR_REWARD_WEIGHT_ID
        defaultMChallengeQuestStageRewardShouldNotBeFound("clearRewardWeightId.lessThan=" + DEFAULT_CLEAR_REWARD_WEIGHT_ID);

        // Get all the mChallengeQuestStageRewardList where clearRewardWeightId less than or equals to UPDATED_CLEAR_REWARD_WEIGHT_ID
        defaultMChallengeQuestStageRewardShouldBeFound("clearRewardWeightId.lessThan=" + UPDATED_CLEAR_REWARD_WEIGHT_ID);
    }


    @Test
    @Transactional
    public void getAllMChallengeQuestStageRewardsByAchievementRewardGroupIdIsEqualToSomething() throws Exception {
        // Initialize the database
        mChallengeQuestStageRewardRepository.saveAndFlush(mChallengeQuestStageReward);

        // Get all the mChallengeQuestStageRewardList where achievementRewardGroupId equals to DEFAULT_ACHIEVEMENT_REWARD_GROUP_ID
        defaultMChallengeQuestStageRewardShouldBeFound("achievementRewardGroupId.equals=" + DEFAULT_ACHIEVEMENT_REWARD_GROUP_ID);

        // Get all the mChallengeQuestStageRewardList where achievementRewardGroupId equals to UPDATED_ACHIEVEMENT_REWARD_GROUP_ID
        defaultMChallengeQuestStageRewardShouldNotBeFound("achievementRewardGroupId.equals=" + UPDATED_ACHIEVEMENT_REWARD_GROUP_ID);
    }

    @Test
    @Transactional
    public void getAllMChallengeQuestStageRewardsByAchievementRewardGroupIdIsInShouldWork() throws Exception {
        // Initialize the database
        mChallengeQuestStageRewardRepository.saveAndFlush(mChallengeQuestStageReward);

        // Get all the mChallengeQuestStageRewardList where achievementRewardGroupId in DEFAULT_ACHIEVEMENT_REWARD_GROUP_ID or UPDATED_ACHIEVEMENT_REWARD_GROUP_ID
        defaultMChallengeQuestStageRewardShouldBeFound("achievementRewardGroupId.in=" + DEFAULT_ACHIEVEMENT_REWARD_GROUP_ID + "," + UPDATED_ACHIEVEMENT_REWARD_GROUP_ID);

        // Get all the mChallengeQuestStageRewardList where achievementRewardGroupId equals to UPDATED_ACHIEVEMENT_REWARD_GROUP_ID
        defaultMChallengeQuestStageRewardShouldNotBeFound("achievementRewardGroupId.in=" + UPDATED_ACHIEVEMENT_REWARD_GROUP_ID);
    }

    @Test
    @Transactional
    public void getAllMChallengeQuestStageRewardsByAchievementRewardGroupIdIsNullOrNotNull() throws Exception {
        // Initialize the database
        mChallengeQuestStageRewardRepository.saveAndFlush(mChallengeQuestStageReward);

        // Get all the mChallengeQuestStageRewardList where achievementRewardGroupId is not null
        defaultMChallengeQuestStageRewardShouldBeFound("achievementRewardGroupId.specified=true");

        // Get all the mChallengeQuestStageRewardList where achievementRewardGroupId is null
        defaultMChallengeQuestStageRewardShouldNotBeFound("achievementRewardGroupId.specified=false");
    }

    @Test
    @Transactional
    public void getAllMChallengeQuestStageRewardsByAchievementRewardGroupIdIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mChallengeQuestStageRewardRepository.saveAndFlush(mChallengeQuestStageReward);

        // Get all the mChallengeQuestStageRewardList where achievementRewardGroupId greater than or equals to DEFAULT_ACHIEVEMENT_REWARD_GROUP_ID
        defaultMChallengeQuestStageRewardShouldBeFound("achievementRewardGroupId.greaterOrEqualThan=" + DEFAULT_ACHIEVEMENT_REWARD_GROUP_ID);

        // Get all the mChallengeQuestStageRewardList where achievementRewardGroupId greater than or equals to UPDATED_ACHIEVEMENT_REWARD_GROUP_ID
        defaultMChallengeQuestStageRewardShouldNotBeFound("achievementRewardGroupId.greaterOrEqualThan=" + UPDATED_ACHIEVEMENT_REWARD_GROUP_ID);
    }

    @Test
    @Transactional
    public void getAllMChallengeQuestStageRewardsByAchievementRewardGroupIdIsLessThanSomething() throws Exception {
        // Initialize the database
        mChallengeQuestStageRewardRepository.saveAndFlush(mChallengeQuestStageReward);

        // Get all the mChallengeQuestStageRewardList where achievementRewardGroupId less than or equals to DEFAULT_ACHIEVEMENT_REWARD_GROUP_ID
        defaultMChallengeQuestStageRewardShouldNotBeFound("achievementRewardGroupId.lessThan=" + DEFAULT_ACHIEVEMENT_REWARD_GROUP_ID);

        // Get all the mChallengeQuestStageRewardList where achievementRewardGroupId less than or equals to UPDATED_ACHIEVEMENT_REWARD_GROUP_ID
        defaultMChallengeQuestStageRewardShouldBeFound("achievementRewardGroupId.lessThan=" + UPDATED_ACHIEVEMENT_REWARD_GROUP_ID);
    }


    @Test
    @Transactional
    public void getAllMChallengeQuestStageRewardsByCoopGroupIdIsEqualToSomething() throws Exception {
        // Initialize the database
        mChallengeQuestStageRewardRepository.saveAndFlush(mChallengeQuestStageReward);

        // Get all the mChallengeQuestStageRewardList where coopGroupId equals to DEFAULT_COOP_GROUP_ID
        defaultMChallengeQuestStageRewardShouldBeFound("coopGroupId.equals=" + DEFAULT_COOP_GROUP_ID);

        // Get all the mChallengeQuestStageRewardList where coopGroupId equals to UPDATED_COOP_GROUP_ID
        defaultMChallengeQuestStageRewardShouldNotBeFound("coopGroupId.equals=" + UPDATED_COOP_GROUP_ID);
    }

    @Test
    @Transactional
    public void getAllMChallengeQuestStageRewardsByCoopGroupIdIsInShouldWork() throws Exception {
        // Initialize the database
        mChallengeQuestStageRewardRepository.saveAndFlush(mChallengeQuestStageReward);

        // Get all the mChallengeQuestStageRewardList where coopGroupId in DEFAULT_COOP_GROUP_ID or UPDATED_COOP_GROUP_ID
        defaultMChallengeQuestStageRewardShouldBeFound("coopGroupId.in=" + DEFAULT_COOP_GROUP_ID + "," + UPDATED_COOP_GROUP_ID);

        // Get all the mChallengeQuestStageRewardList where coopGroupId equals to UPDATED_COOP_GROUP_ID
        defaultMChallengeQuestStageRewardShouldNotBeFound("coopGroupId.in=" + UPDATED_COOP_GROUP_ID);
    }

    @Test
    @Transactional
    public void getAllMChallengeQuestStageRewardsByCoopGroupIdIsNullOrNotNull() throws Exception {
        // Initialize the database
        mChallengeQuestStageRewardRepository.saveAndFlush(mChallengeQuestStageReward);

        // Get all the mChallengeQuestStageRewardList where coopGroupId is not null
        defaultMChallengeQuestStageRewardShouldBeFound("coopGroupId.specified=true");

        // Get all the mChallengeQuestStageRewardList where coopGroupId is null
        defaultMChallengeQuestStageRewardShouldNotBeFound("coopGroupId.specified=false");
    }

    @Test
    @Transactional
    public void getAllMChallengeQuestStageRewardsByCoopGroupIdIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mChallengeQuestStageRewardRepository.saveAndFlush(mChallengeQuestStageReward);

        // Get all the mChallengeQuestStageRewardList where coopGroupId greater than or equals to DEFAULT_COOP_GROUP_ID
        defaultMChallengeQuestStageRewardShouldBeFound("coopGroupId.greaterOrEqualThan=" + DEFAULT_COOP_GROUP_ID);

        // Get all the mChallengeQuestStageRewardList where coopGroupId greater than or equals to UPDATED_COOP_GROUP_ID
        defaultMChallengeQuestStageRewardShouldNotBeFound("coopGroupId.greaterOrEqualThan=" + UPDATED_COOP_GROUP_ID);
    }

    @Test
    @Transactional
    public void getAllMChallengeQuestStageRewardsByCoopGroupIdIsLessThanSomething() throws Exception {
        // Initialize the database
        mChallengeQuestStageRewardRepository.saveAndFlush(mChallengeQuestStageReward);

        // Get all the mChallengeQuestStageRewardList where coopGroupId less than or equals to DEFAULT_COOP_GROUP_ID
        defaultMChallengeQuestStageRewardShouldNotBeFound("coopGroupId.lessThan=" + DEFAULT_COOP_GROUP_ID);

        // Get all the mChallengeQuestStageRewardList where coopGroupId less than or equals to UPDATED_COOP_GROUP_ID
        defaultMChallengeQuestStageRewardShouldBeFound("coopGroupId.lessThan=" + UPDATED_COOP_GROUP_ID);
    }


    @Test
    @Transactional
    public void getAllMChallengeQuestStageRewardsBySpecialRewardGroupIdIsEqualToSomething() throws Exception {
        // Initialize the database
        mChallengeQuestStageRewardRepository.saveAndFlush(mChallengeQuestStageReward);

        // Get all the mChallengeQuestStageRewardList where specialRewardGroupId equals to DEFAULT_SPECIAL_REWARD_GROUP_ID
        defaultMChallengeQuestStageRewardShouldBeFound("specialRewardGroupId.equals=" + DEFAULT_SPECIAL_REWARD_GROUP_ID);

        // Get all the mChallengeQuestStageRewardList where specialRewardGroupId equals to UPDATED_SPECIAL_REWARD_GROUP_ID
        defaultMChallengeQuestStageRewardShouldNotBeFound("specialRewardGroupId.equals=" + UPDATED_SPECIAL_REWARD_GROUP_ID);
    }

    @Test
    @Transactional
    public void getAllMChallengeQuestStageRewardsBySpecialRewardGroupIdIsInShouldWork() throws Exception {
        // Initialize the database
        mChallengeQuestStageRewardRepository.saveAndFlush(mChallengeQuestStageReward);

        // Get all the mChallengeQuestStageRewardList where specialRewardGroupId in DEFAULT_SPECIAL_REWARD_GROUP_ID or UPDATED_SPECIAL_REWARD_GROUP_ID
        defaultMChallengeQuestStageRewardShouldBeFound("specialRewardGroupId.in=" + DEFAULT_SPECIAL_REWARD_GROUP_ID + "," + UPDATED_SPECIAL_REWARD_GROUP_ID);

        // Get all the mChallengeQuestStageRewardList where specialRewardGroupId equals to UPDATED_SPECIAL_REWARD_GROUP_ID
        defaultMChallengeQuestStageRewardShouldNotBeFound("specialRewardGroupId.in=" + UPDATED_SPECIAL_REWARD_GROUP_ID);
    }

    @Test
    @Transactional
    public void getAllMChallengeQuestStageRewardsBySpecialRewardGroupIdIsNullOrNotNull() throws Exception {
        // Initialize the database
        mChallengeQuestStageRewardRepository.saveAndFlush(mChallengeQuestStageReward);

        // Get all the mChallengeQuestStageRewardList where specialRewardGroupId is not null
        defaultMChallengeQuestStageRewardShouldBeFound("specialRewardGroupId.specified=true");

        // Get all the mChallengeQuestStageRewardList where specialRewardGroupId is null
        defaultMChallengeQuestStageRewardShouldNotBeFound("specialRewardGroupId.specified=false");
    }

    @Test
    @Transactional
    public void getAllMChallengeQuestStageRewardsBySpecialRewardGroupIdIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mChallengeQuestStageRewardRepository.saveAndFlush(mChallengeQuestStageReward);

        // Get all the mChallengeQuestStageRewardList where specialRewardGroupId greater than or equals to DEFAULT_SPECIAL_REWARD_GROUP_ID
        defaultMChallengeQuestStageRewardShouldBeFound("specialRewardGroupId.greaterOrEqualThan=" + DEFAULT_SPECIAL_REWARD_GROUP_ID);

        // Get all the mChallengeQuestStageRewardList where specialRewardGroupId greater than or equals to UPDATED_SPECIAL_REWARD_GROUP_ID
        defaultMChallengeQuestStageRewardShouldNotBeFound("specialRewardGroupId.greaterOrEqualThan=" + UPDATED_SPECIAL_REWARD_GROUP_ID);
    }

    @Test
    @Transactional
    public void getAllMChallengeQuestStageRewardsBySpecialRewardGroupIdIsLessThanSomething() throws Exception {
        // Initialize the database
        mChallengeQuestStageRewardRepository.saveAndFlush(mChallengeQuestStageReward);

        // Get all the mChallengeQuestStageRewardList where specialRewardGroupId less than or equals to DEFAULT_SPECIAL_REWARD_GROUP_ID
        defaultMChallengeQuestStageRewardShouldNotBeFound("specialRewardGroupId.lessThan=" + DEFAULT_SPECIAL_REWARD_GROUP_ID);

        // Get all the mChallengeQuestStageRewardList where specialRewardGroupId less than or equals to UPDATED_SPECIAL_REWARD_GROUP_ID
        defaultMChallengeQuestStageRewardShouldBeFound("specialRewardGroupId.lessThan=" + UPDATED_SPECIAL_REWARD_GROUP_ID);
    }


    @Test
    @Transactional
    public void getAllMChallengeQuestStageRewardsBySpecialRewardAmountIsEqualToSomething() throws Exception {
        // Initialize the database
        mChallengeQuestStageRewardRepository.saveAndFlush(mChallengeQuestStageReward);

        // Get all the mChallengeQuestStageRewardList where specialRewardAmount equals to DEFAULT_SPECIAL_REWARD_AMOUNT
        defaultMChallengeQuestStageRewardShouldBeFound("specialRewardAmount.equals=" + DEFAULT_SPECIAL_REWARD_AMOUNT);

        // Get all the mChallengeQuestStageRewardList where specialRewardAmount equals to UPDATED_SPECIAL_REWARD_AMOUNT
        defaultMChallengeQuestStageRewardShouldNotBeFound("specialRewardAmount.equals=" + UPDATED_SPECIAL_REWARD_AMOUNT);
    }

    @Test
    @Transactional
    public void getAllMChallengeQuestStageRewardsBySpecialRewardAmountIsInShouldWork() throws Exception {
        // Initialize the database
        mChallengeQuestStageRewardRepository.saveAndFlush(mChallengeQuestStageReward);

        // Get all the mChallengeQuestStageRewardList where specialRewardAmount in DEFAULT_SPECIAL_REWARD_AMOUNT or UPDATED_SPECIAL_REWARD_AMOUNT
        defaultMChallengeQuestStageRewardShouldBeFound("specialRewardAmount.in=" + DEFAULT_SPECIAL_REWARD_AMOUNT + "," + UPDATED_SPECIAL_REWARD_AMOUNT);

        // Get all the mChallengeQuestStageRewardList where specialRewardAmount equals to UPDATED_SPECIAL_REWARD_AMOUNT
        defaultMChallengeQuestStageRewardShouldNotBeFound("specialRewardAmount.in=" + UPDATED_SPECIAL_REWARD_AMOUNT);
    }

    @Test
    @Transactional
    public void getAllMChallengeQuestStageRewardsBySpecialRewardAmountIsNullOrNotNull() throws Exception {
        // Initialize the database
        mChallengeQuestStageRewardRepository.saveAndFlush(mChallengeQuestStageReward);

        // Get all the mChallengeQuestStageRewardList where specialRewardAmount is not null
        defaultMChallengeQuestStageRewardShouldBeFound("specialRewardAmount.specified=true");

        // Get all the mChallengeQuestStageRewardList where specialRewardAmount is null
        defaultMChallengeQuestStageRewardShouldNotBeFound("specialRewardAmount.specified=false");
    }

    @Test
    @Transactional
    public void getAllMChallengeQuestStageRewardsBySpecialRewardAmountIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mChallengeQuestStageRewardRepository.saveAndFlush(mChallengeQuestStageReward);

        // Get all the mChallengeQuestStageRewardList where specialRewardAmount greater than or equals to DEFAULT_SPECIAL_REWARD_AMOUNT
        defaultMChallengeQuestStageRewardShouldBeFound("specialRewardAmount.greaterOrEqualThan=" + DEFAULT_SPECIAL_REWARD_AMOUNT);

        // Get all the mChallengeQuestStageRewardList where specialRewardAmount greater than or equals to UPDATED_SPECIAL_REWARD_AMOUNT
        defaultMChallengeQuestStageRewardShouldNotBeFound("specialRewardAmount.greaterOrEqualThan=" + UPDATED_SPECIAL_REWARD_AMOUNT);
    }

    @Test
    @Transactional
    public void getAllMChallengeQuestStageRewardsBySpecialRewardAmountIsLessThanSomething() throws Exception {
        // Initialize the database
        mChallengeQuestStageRewardRepository.saveAndFlush(mChallengeQuestStageReward);

        // Get all the mChallengeQuestStageRewardList where specialRewardAmount less than or equals to DEFAULT_SPECIAL_REWARD_AMOUNT
        defaultMChallengeQuestStageRewardShouldNotBeFound("specialRewardAmount.lessThan=" + DEFAULT_SPECIAL_REWARD_AMOUNT);

        // Get all the mChallengeQuestStageRewardList where specialRewardAmount less than or equals to UPDATED_SPECIAL_REWARD_AMOUNT
        defaultMChallengeQuestStageRewardShouldBeFound("specialRewardAmount.lessThan=" + UPDATED_SPECIAL_REWARD_AMOUNT);
    }


    @Test
    @Transactional
    public void getAllMChallengeQuestStageRewardsByGoalRewardGroupIdIsEqualToSomething() throws Exception {
        // Initialize the database
        mChallengeQuestStageRewardRepository.saveAndFlush(mChallengeQuestStageReward);

        // Get all the mChallengeQuestStageRewardList where goalRewardGroupId equals to DEFAULT_GOAL_REWARD_GROUP_ID
        defaultMChallengeQuestStageRewardShouldBeFound("goalRewardGroupId.equals=" + DEFAULT_GOAL_REWARD_GROUP_ID);

        // Get all the mChallengeQuestStageRewardList where goalRewardGroupId equals to UPDATED_GOAL_REWARD_GROUP_ID
        defaultMChallengeQuestStageRewardShouldNotBeFound("goalRewardGroupId.equals=" + UPDATED_GOAL_REWARD_GROUP_ID);
    }

    @Test
    @Transactional
    public void getAllMChallengeQuestStageRewardsByGoalRewardGroupIdIsInShouldWork() throws Exception {
        // Initialize the database
        mChallengeQuestStageRewardRepository.saveAndFlush(mChallengeQuestStageReward);

        // Get all the mChallengeQuestStageRewardList where goalRewardGroupId in DEFAULT_GOAL_REWARD_GROUP_ID or UPDATED_GOAL_REWARD_GROUP_ID
        defaultMChallengeQuestStageRewardShouldBeFound("goalRewardGroupId.in=" + DEFAULT_GOAL_REWARD_GROUP_ID + "," + UPDATED_GOAL_REWARD_GROUP_ID);

        // Get all the mChallengeQuestStageRewardList where goalRewardGroupId equals to UPDATED_GOAL_REWARD_GROUP_ID
        defaultMChallengeQuestStageRewardShouldNotBeFound("goalRewardGroupId.in=" + UPDATED_GOAL_REWARD_GROUP_ID);
    }

    @Test
    @Transactional
    public void getAllMChallengeQuestStageRewardsByGoalRewardGroupIdIsNullOrNotNull() throws Exception {
        // Initialize the database
        mChallengeQuestStageRewardRepository.saveAndFlush(mChallengeQuestStageReward);

        // Get all the mChallengeQuestStageRewardList where goalRewardGroupId is not null
        defaultMChallengeQuestStageRewardShouldBeFound("goalRewardGroupId.specified=true");

        // Get all the mChallengeQuestStageRewardList where goalRewardGroupId is null
        defaultMChallengeQuestStageRewardShouldNotBeFound("goalRewardGroupId.specified=false");
    }

    @Test
    @Transactional
    public void getAllMChallengeQuestStageRewardsByGoalRewardGroupIdIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mChallengeQuestStageRewardRepository.saveAndFlush(mChallengeQuestStageReward);

        // Get all the mChallengeQuestStageRewardList where goalRewardGroupId greater than or equals to DEFAULT_GOAL_REWARD_GROUP_ID
        defaultMChallengeQuestStageRewardShouldBeFound("goalRewardGroupId.greaterOrEqualThan=" + DEFAULT_GOAL_REWARD_GROUP_ID);

        // Get all the mChallengeQuestStageRewardList where goalRewardGroupId greater than or equals to UPDATED_GOAL_REWARD_GROUP_ID
        defaultMChallengeQuestStageRewardShouldNotBeFound("goalRewardGroupId.greaterOrEqualThan=" + UPDATED_GOAL_REWARD_GROUP_ID);
    }

    @Test
    @Transactional
    public void getAllMChallengeQuestStageRewardsByGoalRewardGroupIdIsLessThanSomething() throws Exception {
        // Initialize the database
        mChallengeQuestStageRewardRepository.saveAndFlush(mChallengeQuestStageReward);

        // Get all the mChallengeQuestStageRewardList where goalRewardGroupId less than or equals to DEFAULT_GOAL_REWARD_GROUP_ID
        defaultMChallengeQuestStageRewardShouldNotBeFound("goalRewardGroupId.lessThan=" + DEFAULT_GOAL_REWARD_GROUP_ID);

        // Get all the mChallengeQuestStageRewardList where goalRewardGroupId less than or equals to UPDATED_GOAL_REWARD_GROUP_ID
        defaultMChallengeQuestStageRewardShouldBeFound("goalRewardGroupId.lessThan=" + UPDATED_GOAL_REWARD_GROUP_ID);
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultMChallengeQuestStageRewardShouldBeFound(String filter) throws Exception {
        restMChallengeQuestStageRewardMockMvc.perform(get("/api/m-challenge-quest-stage-rewards?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(mChallengeQuestStageReward.getId().intValue())))
            .andExpect(jsonPath("$.[*].stageId").value(hasItem(DEFAULT_STAGE_ID)))
            .andExpect(jsonPath("$.[*].exp").value(hasItem(DEFAULT_EXP)))
            .andExpect(jsonPath("$.[*].coin").value(hasItem(DEFAULT_COIN)))
            .andExpect(jsonPath("$.[*].guildPoint").value(hasItem(DEFAULT_GUILD_POINT)))
            .andExpect(jsonPath("$.[*].clearRewardGroupId").value(hasItem(DEFAULT_CLEAR_REWARD_GROUP_ID)))
            .andExpect(jsonPath("$.[*].clearRewardWeightId").value(hasItem(DEFAULT_CLEAR_REWARD_WEIGHT_ID)))
            .andExpect(jsonPath("$.[*].achievementRewardGroupId").value(hasItem(DEFAULT_ACHIEVEMENT_REWARD_GROUP_ID)))
            .andExpect(jsonPath("$.[*].coopGroupId").value(hasItem(DEFAULT_COOP_GROUP_ID)))
            .andExpect(jsonPath("$.[*].specialRewardGroupId").value(hasItem(DEFAULT_SPECIAL_REWARD_GROUP_ID)))
            .andExpect(jsonPath("$.[*].specialRewardAmount").value(hasItem(DEFAULT_SPECIAL_REWARD_AMOUNT)))
            .andExpect(jsonPath("$.[*].goalRewardGroupId").value(hasItem(DEFAULT_GOAL_REWARD_GROUP_ID)));

        // Check, that the count call also returns 1
        restMChallengeQuestStageRewardMockMvc.perform(get("/api/m-challenge-quest-stage-rewards/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultMChallengeQuestStageRewardShouldNotBeFound(String filter) throws Exception {
        restMChallengeQuestStageRewardMockMvc.perform(get("/api/m-challenge-quest-stage-rewards?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restMChallengeQuestStageRewardMockMvc.perform(get("/api/m-challenge-quest-stage-rewards/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("0"));
    }


    @Test
    @Transactional
    public void getNonExistingMChallengeQuestStageReward() throws Exception {
        // Get the mChallengeQuestStageReward
        restMChallengeQuestStageRewardMockMvc.perform(get("/api/m-challenge-quest-stage-rewards/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateMChallengeQuestStageReward() throws Exception {
        // Initialize the database
        mChallengeQuestStageRewardRepository.saveAndFlush(mChallengeQuestStageReward);

        int databaseSizeBeforeUpdate = mChallengeQuestStageRewardRepository.findAll().size();

        // Update the mChallengeQuestStageReward
        MChallengeQuestStageReward updatedMChallengeQuestStageReward = mChallengeQuestStageRewardRepository.findById(mChallengeQuestStageReward.getId()).get();
        // Disconnect from session so that the updates on updatedMChallengeQuestStageReward are not directly saved in db
        em.detach(updatedMChallengeQuestStageReward);
        updatedMChallengeQuestStageReward
            .stageId(UPDATED_STAGE_ID)
            .exp(UPDATED_EXP)
            .coin(UPDATED_COIN)
            .guildPoint(UPDATED_GUILD_POINT)
            .clearRewardGroupId(UPDATED_CLEAR_REWARD_GROUP_ID)
            .clearRewardWeightId(UPDATED_CLEAR_REWARD_WEIGHT_ID)
            .achievementRewardGroupId(UPDATED_ACHIEVEMENT_REWARD_GROUP_ID)
            .coopGroupId(UPDATED_COOP_GROUP_ID)
            .specialRewardGroupId(UPDATED_SPECIAL_REWARD_GROUP_ID)
            .specialRewardAmount(UPDATED_SPECIAL_REWARD_AMOUNT)
            .goalRewardGroupId(UPDATED_GOAL_REWARD_GROUP_ID);
        MChallengeQuestStageRewardDTO mChallengeQuestStageRewardDTO = mChallengeQuestStageRewardMapper.toDto(updatedMChallengeQuestStageReward);

        restMChallengeQuestStageRewardMockMvc.perform(put("/api/m-challenge-quest-stage-rewards")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mChallengeQuestStageRewardDTO)))
            .andExpect(status().isOk());

        // Validate the MChallengeQuestStageReward in the database
        List<MChallengeQuestStageReward> mChallengeQuestStageRewardList = mChallengeQuestStageRewardRepository.findAll();
        assertThat(mChallengeQuestStageRewardList).hasSize(databaseSizeBeforeUpdate);
        MChallengeQuestStageReward testMChallengeQuestStageReward = mChallengeQuestStageRewardList.get(mChallengeQuestStageRewardList.size() - 1);
        assertThat(testMChallengeQuestStageReward.getStageId()).isEqualTo(UPDATED_STAGE_ID);
        assertThat(testMChallengeQuestStageReward.getExp()).isEqualTo(UPDATED_EXP);
        assertThat(testMChallengeQuestStageReward.getCoin()).isEqualTo(UPDATED_COIN);
        assertThat(testMChallengeQuestStageReward.getGuildPoint()).isEqualTo(UPDATED_GUILD_POINT);
        assertThat(testMChallengeQuestStageReward.getClearRewardGroupId()).isEqualTo(UPDATED_CLEAR_REWARD_GROUP_ID);
        assertThat(testMChallengeQuestStageReward.getClearRewardWeightId()).isEqualTo(UPDATED_CLEAR_REWARD_WEIGHT_ID);
        assertThat(testMChallengeQuestStageReward.getAchievementRewardGroupId()).isEqualTo(UPDATED_ACHIEVEMENT_REWARD_GROUP_ID);
        assertThat(testMChallengeQuestStageReward.getCoopGroupId()).isEqualTo(UPDATED_COOP_GROUP_ID);
        assertThat(testMChallengeQuestStageReward.getSpecialRewardGroupId()).isEqualTo(UPDATED_SPECIAL_REWARD_GROUP_ID);
        assertThat(testMChallengeQuestStageReward.getSpecialRewardAmount()).isEqualTo(UPDATED_SPECIAL_REWARD_AMOUNT);
        assertThat(testMChallengeQuestStageReward.getGoalRewardGroupId()).isEqualTo(UPDATED_GOAL_REWARD_GROUP_ID);
    }

    @Test
    @Transactional
    public void updateNonExistingMChallengeQuestStageReward() throws Exception {
        int databaseSizeBeforeUpdate = mChallengeQuestStageRewardRepository.findAll().size();

        // Create the MChallengeQuestStageReward
        MChallengeQuestStageRewardDTO mChallengeQuestStageRewardDTO = mChallengeQuestStageRewardMapper.toDto(mChallengeQuestStageReward);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restMChallengeQuestStageRewardMockMvc.perform(put("/api/m-challenge-quest-stage-rewards")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mChallengeQuestStageRewardDTO)))
            .andExpect(status().isBadRequest());

        // Validate the MChallengeQuestStageReward in the database
        List<MChallengeQuestStageReward> mChallengeQuestStageRewardList = mChallengeQuestStageRewardRepository.findAll();
        assertThat(mChallengeQuestStageRewardList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteMChallengeQuestStageReward() throws Exception {
        // Initialize the database
        mChallengeQuestStageRewardRepository.saveAndFlush(mChallengeQuestStageReward);

        int databaseSizeBeforeDelete = mChallengeQuestStageRewardRepository.findAll().size();

        // Delete the mChallengeQuestStageReward
        restMChallengeQuestStageRewardMockMvc.perform(delete("/api/m-challenge-quest-stage-rewards/{id}", mChallengeQuestStageReward.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database is empty
        List<MChallengeQuestStageReward> mChallengeQuestStageRewardList = mChallengeQuestStageRewardRepository.findAll();
        assertThat(mChallengeQuestStageRewardList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(MChallengeQuestStageReward.class);
        MChallengeQuestStageReward mChallengeQuestStageReward1 = new MChallengeQuestStageReward();
        mChallengeQuestStageReward1.setId(1L);
        MChallengeQuestStageReward mChallengeQuestStageReward2 = new MChallengeQuestStageReward();
        mChallengeQuestStageReward2.setId(mChallengeQuestStageReward1.getId());
        assertThat(mChallengeQuestStageReward1).isEqualTo(mChallengeQuestStageReward2);
        mChallengeQuestStageReward2.setId(2L);
        assertThat(mChallengeQuestStageReward1).isNotEqualTo(mChallengeQuestStageReward2);
        mChallengeQuestStageReward1.setId(null);
        assertThat(mChallengeQuestStageReward1).isNotEqualTo(mChallengeQuestStageReward2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(MChallengeQuestStageRewardDTO.class);
        MChallengeQuestStageRewardDTO mChallengeQuestStageRewardDTO1 = new MChallengeQuestStageRewardDTO();
        mChallengeQuestStageRewardDTO1.setId(1L);
        MChallengeQuestStageRewardDTO mChallengeQuestStageRewardDTO2 = new MChallengeQuestStageRewardDTO();
        assertThat(mChallengeQuestStageRewardDTO1).isNotEqualTo(mChallengeQuestStageRewardDTO2);
        mChallengeQuestStageRewardDTO2.setId(mChallengeQuestStageRewardDTO1.getId());
        assertThat(mChallengeQuestStageRewardDTO1).isEqualTo(mChallengeQuestStageRewardDTO2);
        mChallengeQuestStageRewardDTO2.setId(2L);
        assertThat(mChallengeQuestStageRewardDTO1).isNotEqualTo(mChallengeQuestStageRewardDTO2);
        mChallengeQuestStageRewardDTO1.setId(null);
        assertThat(mChallengeQuestStageRewardDTO1).isNotEqualTo(mChallengeQuestStageRewardDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(mChallengeQuestStageRewardMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(mChallengeQuestStageRewardMapper.fromId(null)).isNull();
    }
}
