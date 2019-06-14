package io.shm.tsubasa.web.rest;

import io.shm.tsubasa.TsubasaApp;
import io.shm.tsubasa.domain.MLuckWeeklyQuestStageReward;
import io.shm.tsubasa.repository.MLuckWeeklyQuestStageRewardRepository;
import io.shm.tsubasa.service.MLuckWeeklyQuestStageRewardService;
import io.shm.tsubasa.service.dto.MLuckWeeklyQuestStageRewardDTO;
import io.shm.tsubasa.service.mapper.MLuckWeeklyQuestStageRewardMapper;
import io.shm.tsubasa.web.rest.errors.ExceptionTranslator;
import io.shm.tsubasa.service.dto.MLuckWeeklyQuestStageRewardCriteria;
import io.shm.tsubasa.service.MLuckWeeklyQuestStageRewardQueryService;

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
 * Integration tests for the {@Link MLuckWeeklyQuestStageRewardResource} REST controller.
 */
@SpringBootTest(classes = TsubasaApp.class)
public class MLuckWeeklyQuestStageRewardResourceIT {

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
    private MLuckWeeklyQuestStageRewardRepository mLuckWeeklyQuestStageRewardRepository;

    @Autowired
    private MLuckWeeklyQuestStageRewardMapper mLuckWeeklyQuestStageRewardMapper;

    @Autowired
    private MLuckWeeklyQuestStageRewardService mLuckWeeklyQuestStageRewardService;

    @Autowired
    private MLuckWeeklyQuestStageRewardQueryService mLuckWeeklyQuestStageRewardQueryService;

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

    private MockMvc restMLuckWeeklyQuestStageRewardMockMvc;

    private MLuckWeeklyQuestStageReward mLuckWeeklyQuestStageReward;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final MLuckWeeklyQuestStageRewardResource mLuckWeeklyQuestStageRewardResource = new MLuckWeeklyQuestStageRewardResource(mLuckWeeklyQuestStageRewardService, mLuckWeeklyQuestStageRewardQueryService);
        this.restMLuckWeeklyQuestStageRewardMockMvc = MockMvcBuilders.standaloneSetup(mLuckWeeklyQuestStageRewardResource)
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
    public static MLuckWeeklyQuestStageReward createEntity(EntityManager em) {
        MLuckWeeklyQuestStageReward mLuckWeeklyQuestStageReward = new MLuckWeeklyQuestStageReward()
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
        return mLuckWeeklyQuestStageReward;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static MLuckWeeklyQuestStageReward createUpdatedEntity(EntityManager em) {
        MLuckWeeklyQuestStageReward mLuckWeeklyQuestStageReward = new MLuckWeeklyQuestStageReward()
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
        return mLuckWeeklyQuestStageReward;
    }

    @BeforeEach
    public void initTest() {
        mLuckWeeklyQuestStageReward = createEntity(em);
    }

    @Test
    @Transactional
    public void createMLuckWeeklyQuestStageReward() throws Exception {
        int databaseSizeBeforeCreate = mLuckWeeklyQuestStageRewardRepository.findAll().size();

        // Create the MLuckWeeklyQuestStageReward
        MLuckWeeklyQuestStageRewardDTO mLuckWeeklyQuestStageRewardDTO = mLuckWeeklyQuestStageRewardMapper.toDto(mLuckWeeklyQuestStageReward);
        restMLuckWeeklyQuestStageRewardMockMvc.perform(post("/api/m-luck-weekly-quest-stage-rewards")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mLuckWeeklyQuestStageRewardDTO)))
            .andExpect(status().isCreated());

        // Validate the MLuckWeeklyQuestStageReward in the database
        List<MLuckWeeklyQuestStageReward> mLuckWeeklyQuestStageRewardList = mLuckWeeklyQuestStageRewardRepository.findAll();
        assertThat(mLuckWeeklyQuestStageRewardList).hasSize(databaseSizeBeforeCreate + 1);
        MLuckWeeklyQuestStageReward testMLuckWeeklyQuestStageReward = mLuckWeeklyQuestStageRewardList.get(mLuckWeeklyQuestStageRewardList.size() - 1);
        assertThat(testMLuckWeeklyQuestStageReward.getStageId()).isEqualTo(DEFAULT_STAGE_ID);
        assertThat(testMLuckWeeklyQuestStageReward.getExp()).isEqualTo(DEFAULT_EXP);
        assertThat(testMLuckWeeklyQuestStageReward.getCoin()).isEqualTo(DEFAULT_COIN);
        assertThat(testMLuckWeeklyQuestStageReward.getGuildPoint()).isEqualTo(DEFAULT_GUILD_POINT);
        assertThat(testMLuckWeeklyQuestStageReward.getClearRewardGroupId()).isEqualTo(DEFAULT_CLEAR_REWARD_GROUP_ID);
        assertThat(testMLuckWeeklyQuestStageReward.getClearRewardWeightId()).isEqualTo(DEFAULT_CLEAR_REWARD_WEIGHT_ID);
        assertThat(testMLuckWeeklyQuestStageReward.getAchievementRewardGroupId()).isEqualTo(DEFAULT_ACHIEVEMENT_REWARD_GROUP_ID);
        assertThat(testMLuckWeeklyQuestStageReward.getCoopGroupId()).isEqualTo(DEFAULT_COOP_GROUP_ID);
        assertThat(testMLuckWeeklyQuestStageReward.getSpecialRewardGroupId()).isEqualTo(DEFAULT_SPECIAL_REWARD_GROUP_ID);
        assertThat(testMLuckWeeklyQuestStageReward.getSpecialRewardAmount()).isEqualTo(DEFAULT_SPECIAL_REWARD_AMOUNT);
        assertThat(testMLuckWeeklyQuestStageReward.getGoalRewardGroupId()).isEqualTo(DEFAULT_GOAL_REWARD_GROUP_ID);
    }

    @Test
    @Transactional
    public void createMLuckWeeklyQuestStageRewardWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = mLuckWeeklyQuestStageRewardRepository.findAll().size();

        // Create the MLuckWeeklyQuestStageReward with an existing ID
        mLuckWeeklyQuestStageReward.setId(1L);
        MLuckWeeklyQuestStageRewardDTO mLuckWeeklyQuestStageRewardDTO = mLuckWeeklyQuestStageRewardMapper.toDto(mLuckWeeklyQuestStageReward);

        // An entity with an existing ID cannot be created, so this API call must fail
        restMLuckWeeklyQuestStageRewardMockMvc.perform(post("/api/m-luck-weekly-quest-stage-rewards")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mLuckWeeklyQuestStageRewardDTO)))
            .andExpect(status().isBadRequest());

        // Validate the MLuckWeeklyQuestStageReward in the database
        List<MLuckWeeklyQuestStageReward> mLuckWeeklyQuestStageRewardList = mLuckWeeklyQuestStageRewardRepository.findAll();
        assertThat(mLuckWeeklyQuestStageRewardList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkStageIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = mLuckWeeklyQuestStageRewardRepository.findAll().size();
        // set the field null
        mLuckWeeklyQuestStageReward.setStageId(null);

        // Create the MLuckWeeklyQuestStageReward, which fails.
        MLuckWeeklyQuestStageRewardDTO mLuckWeeklyQuestStageRewardDTO = mLuckWeeklyQuestStageRewardMapper.toDto(mLuckWeeklyQuestStageReward);

        restMLuckWeeklyQuestStageRewardMockMvc.perform(post("/api/m-luck-weekly-quest-stage-rewards")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mLuckWeeklyQuestStageRewardDTO)))
            .andExpect(status().isBadRequest());

        List<MLuckWeeklyQuestStageReward> mLuckWeeklyQuestStageRewardList = mLuckWeeklyQuestStageRewardRepository.findAll();
        assertThat(mLuckWeeklyQuestStageRewardList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkExpIsRequired() throws Exception {
        int databaseSizeBeforeTest = mLuckWeeklyQuestStageRewardRepository.findAll().size();
        // set the field null
        mLuckWeeklyQuestStageReward.setExp(null);

        // Create the MLuckWeeklyQuestStageReward, which fails.
        MLuckWeeklyQuestStageRewardDTO mLuckWeeklyQuestStageRewardDTO = mLuckWeeklyQuestStageRewardMapper.toDto(mLuckWeeklyQuestStageReward);

        restMLuckWeeklyQuestStageRewardMockMvc.perform(post("/api/m-luck-weekly-quest-stage-rewards")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mLuckWeeklyQuestStageRewardDTO)))
            .andExpect(status().isBadRequest());

        List<MLuckWeeklyQuestStageReward> mLuckWeeklyQuestStageRewardList = mLuckWeeklyQuestStageRewardRepository.findAll();
        assertThat(mLuckWeeklyQuestStageRewardList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkCoinIsRequired() throws Exception {
        int databaseSizeBeforeTest = mLuckWeeklyQuestStageRewardRepository.findAll().size();
        // set the field null
        mLuckWeeklyQuestStageReward.setCoin(null);

        // Create the MLuckWeeklyQuestStageReward, which fails.
        MLuckWeeklyQuestStageRewardDTO mLuckWeeklyQuestStageRewardDTO = mLuckWeeklyQuestStageRewardMapper.toDto(mLuckWeeklyQuestStageReward);

        restMLuckWeeklyQuestStageRewardMockMvc.perform(post("/api/m-luck-weekly-quest-stage-rewards")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mLuckWeeklyQuestStageRewardDTO)))
            .andExpect(status().isBadRequest());

        List<MLuckWeeklyQuestStageReward> mLuckWeeklyQuestStageRewardList = mLuckWeeklyQuestStageRewardRepository.findAll();
        assertThat(mLuckWeeklyQuestStageRewardList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkGuildPointIsRequired() throws Exception {
        int databaseSizeBeforeTest = mLuckWeeklyQuestStageRewardRepository.findAll().size();
        // set the field null
        mLuckWeeklyQuestStageReward.setGuildPoint(null);

        // Create the MLuckWeeklyQuestStageReward, which fails.
        MLuckWeeklyQuestStageRewardDTO mLuckWeeklyQuestStageRewardDTO = mLuckWeeklyQuestStageRewardMapper.toDto(mLuckWeeklyQuestStageReward);

        restMLuckWeeklyQuestStageRewardMockMvc.perform(post("/api/m-luck-weekly-quest-stage-rewards")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mLuckWeeklyQuestStageRewardDTO)))
            .andExpect(status().isBadRequest());

        List<MLuckWeeklyQuestStageReward> mLuckWeeklyQuestStageRewardList = mLuckWeeklyQuestStageRewardRepository.findAll();
        assertThat(mLuckWeeklyQuestStageRewardList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkClearRewardGroupIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = mLuckWeeklyQuestStageRewardRepository.findAll().size();
        // set the field null
        mLuckWeeklyQuestStageReward.setClearRewardGroupId(null);

        // Create the MLuckWeeklyQuestStageReward, which fails.
        MLuckWeeklyQuestStageRewardDTO mLuckWeeklyQuestStageRewardDTO = mLuckWeeklyQuestStageRewardMapper.toDto(mLuckWeeklyQuestStageReward);

        restMLuckWeeklyQuestStageRewardMockMvc.perform(post("/api/m-luck-weekly-quest-stage-rewards")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mLuckWeeklyQuestStageRewardDTO)))
            .andExpect(status().isBadRequest());

        List<MLuckWeeklyQuestStageReward> mLuckWeeklyQuestStageRewardList = mLuckWeeklyQuestStageRewardRepository.findAll();
        assertThat(mLuckWeeklyQuestStageRewardList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkClearRewardWeightIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = mLuckWeeklyQuestStageRewardRepository.findAll().size();
        // set the field null
        mLuckWeeklyQuestStageReward.setClearRewardWeightId(null);

        // Create the MLuckWeeklyQuestStageReward, which fails.
        MLuckWeeklyQuestStageRewardDTO mLuckWeeklyQuestStageRewardDTO = mLuckWeeklyQuestStageRewardMapper.toDto(mLuckWeeklyQuestStageReward);

        restMLuckWeeklyQuestStageRewardMockMvc.perform(post("/api/m-luck-weekly-quest-stage-rewards")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mLuckWeeklyQuestStageRewardDTO)))
            .andExpect(status().isBadRequest());

        List<MLuckWeeklyQuestStageReward> mLuckWeeklyQuestStageRewardList = mLuckWeeklyQuestStageRewardRepository.findAll();
        assertThat(mLuckWeeklyQuestStageRewardList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkAchievementRewardGroupIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = mLuckWeeklyQuestStageRewardRepository.findAll().size();
        // set the field null
        mLuckWeeklyQuestStageReward.setAchievementRewardGroupId(null);

        // Create the MLuckWeeklyQuestStageReward, which fails.
        MLuckWeeklyQuestStageRewardDTO mLuckWeeklyQuestStageRewardDTO = mLuckWeeklyQuestStageRewardMapper.toDto(mLuckWeeklyQuestStageReward);

        restMLuckWeeklyQuestStageRewardMockMvc.perform(post("/api/m-luck-weekly-quest-stage-rewards")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mLuckWeeklyQuestStageRewardDTO)))
            .andExpect(status().isBadRequest());

        List<MLuckWeeklyQuestStageReward> mLuckWeeklyQuestStageRewardList = mLuckWeeklyQuestStageRewardRepository.findAll();
        assertThat(mLuckWeeklyQuestStageRewardList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkCoopGroupIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = mLuckWeeklyQuestStageRewardRepository.findAll().size();
        // set the field null
        mLuckWeeklyQuestStageReward.setCoopGroupId(null);

        // Create the MLuckWeeklyQuestStageReward, which fails.
        MLuckWeeklyQuestStageRewardDTO mLuckWeeklyQuestStageRewardDTO = mLuckWeeklyQuestStageRewardMapper.toDto(mLuckWeeklyQuestStageReward);

        restMLuckWeeklyQuestStageRewardMockMvc.perform(post("/api/m-luck-weekly-quest-stage-rewards")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mLuckWeeklyQuestStageRewardDTO)))
            .andExpect(status().isBadRequest());

        List<MLuckWeeklyQuestStageReward> mLuckWeeklyQuestStageRewardList = mLuckWeeklyQuestStageRewardRepository.findAll();
        assertThat(mLuckWeeklyQuestStageRewardList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkSpecialRewardAmountIsRequired() throws Exception {
        int databaseSizeBeforeTest = mLuckWeeklyQuestStageRewardRepository.findAll().size();
        // set the field null
        mLuckWeeklyQuestStageReward.setSpecialRewardAmount(null);

        // Create the MLuckWeeklyQuestStageReward, which fails.
        MLuckWeeklyQuestStageRewardDTO mLuckWeeklyQuestStageRewardDTO = mLuckWeeklyQuestStageRewardMapper.toDto(mLuckWeeklyQuestStageReward);

        restMLuckWeeklyQuestStageRewardMockMvc.perform(post("/api/m-luck-weekly-quest-stage-rewards")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mLuckWeeklyQuestStageRewardDTO)))
            .andExpect(status().isBadRequest());

        List<MLuckWeeklyQuestStageReward> mLuckWeeklyQuestStageRewardList = mLuckWeeklyQuestStageRewardRepository.findAll();
        assertThat(mLuckWeeklyQuestStageRewardList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllMLuckWeeklyQuestStageRewards() throws Exception {
        // Initialize the database
        mLuckWeeklyQuestStageRewardRepository.saveAndFlush(mLuckWeeklyQuestStageReward);

        // Get all the mLuckWeeklyQuestStageRewardList
        restMLuckWeeklyQuestStageRewardMockMvc.perform(get("/api/m-luck-weekly-quest-stage-rewards?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(mLuckWeeklyQuestStageReward.getId().intValue())))
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
    public void getMLuckWeeklyQuestStageReward() throws Exception {
        // Initialize the database
        mLuckWeeklyQuestStageRewardRepository.saveAndFlush(mLuckWeeklyQuestStageReward);

        // Get the mLuckWeeklyQuestStageReward
        restMLuckWeeklyQuestStageRewardMockMvc.perform(get("/api/m-luck-weekly-quest-stage-rewards/{id}", mLuckWeeklyQuestStageReward.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(mLuckWeeklyQuestStageReward.getId().intValue()))
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
    public void getAllMLuckWeeklyQuestStageRewardsByStageIdIsEqualToSomething() throws Exception {
        // Initialize the database
        mLuckWeeklyQuestStageRewardRepository.saveAndFlush(mLuckWeeklyQuestStageReward);

        // Get all the mLuckWeeklyQuestStageRewardList where stageId equals to DEFAULT_STAGE_ID
        defaultMLuckWeeklyQuestStageRewardShouldBeFound("stageId.equals=" + DEFAULT_STAGE_ID);

        // Get all the mLuckWeeklyQuestStageRewardList where stageId equals to UPDATED_STAGE_ID
        defaultMLuckWeeklyQuestStageRewardShouldNotBeFound("stageId.equals=" + UPDATED_STAGE_ID);
    }

    @Test
    @Transactional
    public void getAllMLuckWeeklyQuestStageRewardsByStageIdIsInShouldWork() throws Exception {
        // Initialize the database
        mLuckWeeklyQuestStageRewardRepository.saveAndFlush(mLuckWeeklyQuestStageReward);

        // Get all the mLuckWeeklyQuestStageRewardList where stageId in DEFAULT_STAGE_ID or UPDATED_STAGE_ID
        defaultMLuckWeeklyQuestStageRewardShouldBeFound("stageId.in=" + DEFAULT_STAGE_ID + "," + UPDATED_STAGE_ID);

        // Get all the mLuckWeeklyQuestStageRewardList where stageId equals to UPDATED_STAGE_ID
        defaultMLuckWeeklyQuestStageRewardShouldNotBeFound("stageId.in=" + UPDATED_STAGE_ID);
    }

    @Test
    @Transactional
    public void getAllMLuckWeeklyQuestStageRewardsByStageIdIsNullOrNotNull() throws Exception {
        // Initialize the database
        mLuckWeeklyQuestStageRewardRepository.saveAndFlush(mLuckWeeklyQuestStageReward);

        // Get all the mLuckWeeklyQuestStageRewardList where stageId is not null
        defaultMLuckWeeklyQuestStageRewardShouldBeFound("stageId.specified=true");

        // Get all the mLuckWeeklyQuestStageRewardList where stageId is null
        defaultMLuckWeeklyQuestStageRewardShouldNotBeFound("stageId.specified=false");
    }

    @Test
    @Transactional
    public void getAllMLuckWeeklyQuestStageRewardsByStageIdIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mLuckWeeklyQuestStageRewardRepository.saveAndFlush(mLuckWeeklyQuestStageReward);

        // Get all the mLuckWeeklyQuestStageRewardList where stageId greater than or equals to DEFAULT_STAGE_ID
        defaultMLuckWeeklyQuestStageRewardShouldBeFound("stageId.greaterOrEqualThan=" + DEFAULT_STAGE_ID);

        // Get all the mLuckWeeklyQuestStageRewardList where stageId greater than or equals to UPDATED_STAGE_ID
        defaultMLuckWeeklyQuestStageRewardShouldNotBeFound("stageId.greaterOrEqualThan=" + UPDATED_STAGE_ID);
    }

    @Test
    @Transactional
    public void getAllMLuckWeeklyQuestStageRewardsByStageIdIsLessThanSomething() throws Exception {
        // Initialize the database
        mLuckWeeklyQuestStageRewardRepository.saveAndFlush(mLuckWeeklyQuestStageReward);

        // Get all the mLuckWeeklyQuestStageRewardList where stageId less than or equals to DEFAULT_STAGE_ID
        defaultMLuckWeeklyQuestStageRewardShouldNotBeFound("stageId.lessThan=" + DEFAULT_STAGE_ID);

        // Get all the mLuckWeeklyQuestStageRewardList where stageId less than or equals to UPDATED_STAGE_ID
        defaultMLuckWeeklyQuestStageRewardShouldBeFound("stageId.lessThan=" + UPDATED_STAGE_ID);
    }


    @Test
    @Transactional
    public void getAllMLuckWeeklyQuestStageRewardsByExpIsEqualToSomething() throws Exception {
        // Initialize the database
        mLuckWeeklyQuestStageRewardRepository.saveAndFlush(mLuckWeeklyQuestStageReward);

        // Get all the mLuckWeeklyQuestStageRewardList where exp equals to DEFAULT_EXP
        defaultMLuckWeeklyQuestStageRewardShouldBeFound("exp.equals=" + DEFAULT_EXP);

        // Get all the mLuckWeeklyQuestStageRewardList where exp equals to UPDATED_EXP
        defaultMLuckWeeklyQuestStageRewardShouldNotBeFound("exp.equals=" + UPDATED_EXP);
    }

    @Test
    @Transactional
    public void getAllMLuckWeeklyQuestStageRewardsByExpIsInShouldWork() throws Exception {
        // Initialize the database
        mLuckWeeklyQuestStageRewardRepository.saveAndFlush(mLuckWeeklyQuestStageReward);

        // Get all the mLuckWeeklyQuestStageRewardList where exp in DEFAULT_EXP or UPDATED_EXP
        defaultMLuckWeeklyQuestStageRewardShouldBeFound("exp.in=" + DEFAULT_EXP + "," + UPDATED_EXP);

        // Get all the mLuckWeeklyQuestStageRewardList where exp equals to UPDATED_EXP
        defaultMLuckWeeklyQuestStageRewardShouldNotBeFound("exp.in=" + UPDATED_EXP);
    }

    @Test
    @Transactional
    public void getAllMLuckWeeklyQuestStageRewardsByExpIsNullOrNotNull() throws Exception {
        // Initialize the database
        mLuckWeeklyQuestStageRewardRepository.saveAndFlush(mLuckWeeklyQuestStageReward);

        // Get all the mLuckWeeklyQuestStageRewardList where exp is not null
        defaultMLuckWeeklyQuestStageRewardShouldBeFound("exp.specified=true");

        // Get all the mLuckWeeklyQuestStageRewardList where exp is null
        defaultMLuckWeeklyQuestStageRewardShouldNotBeFound("exp.specified=false");
    }

    @Test
    @Transactional
    public void getAllMLuckWeeklyQuestStageRewardsByExpIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mLuckWeeklyQuestStageRewardRepository.saveAndFlush(mLuckWeeklyQuestStageReward);

        // Get all the mLuckWeeklyQuestStageRewardList where exp greater than or equals to DEFAULT_EXP
        defaultMLuckWeeklyQuestStageRewardShouldBeFound("exp.greaterOrEqualThan=" + DEFAULT_EXP);

        // Get all the mLuckWeeklyQuestStageRewardList where exp greater than or equals to UPDATED_EXP
        defaultMLuckWeeklyQuestStageRewardShouldNotBeFound("exp.greaterOrEqualThan=" + UPDATED_EXP);
    }

    @Test
    @Transactional
    public void getAllMLuckWeeklyQuestStageRewardsByExpIsLessThanSomething() throws Exception {
        // Initialize the database
        mLuckWeeklyQuestStageRewardRepository.saveAndFlush(mLuckWeeklyQuestStageReward);

        // Get all the mLuckWeeklyQuestStageRewardList where exp less than or equals to DEFAULT_EXP
        defaultMLuckWeeklyQuestStageRewardShouldNotBeFound("exp.lessThan=" + DEFAULT_EXP);

        // Get all the mLuckWeeklyQuestStageRewardList where exp less than or equals to UPDATED_EXP
        defaultMLuckWeeklyQuestStageRewardShouldBeFound("exp.lessThan=" + UPDATED_EXP);
    }


    @Test
    @Transactional
    public void getAllMLuckWeeklyQuestStageRewardsByCoinIsEqualToSomething() throws Exception {
        // Initialize the database
        mLuckWeeklyQuestStageRewardRepository.saveAndFlush(mLuckWeeklyQuestStageReward);

        // Get all the mLuckWeeklyQuestStageRewardList where coin equals to DEFAULT_COIN
        defaultMLuckWeeklyQuestStageRewardShouldBeFound("coin.equals=" + DEFAULT_COIN);

        // Get all the mLuckWeeklyQuestStageRewardList where coin equals to UPDATED_COIN
        defaultMLuckWeeklyQuestStageRewardShouldNotBeFound("coin.equals=" + UPDATED_COIN);
    }

    @Test
    @Transactional
    public void getAllMLuckWeeklyQuestStageRewardsByCoinIsInShouldWork() throws Exception {
        // Initialize the database
        mLuckWeeklyQuestStageRewardRepository.saveAndFlush(mLuckWeeklyQuestStageReward);

        // Get all the mLuckWeeklyQuestStageRewardList where coin in DEFAULT_COIN or UPDATED_COIN
        defaultMLuckWeeklyQuestStageRewardShouldBeFound("coin.in=" + DEFAULT_COIN + "," + UPDATED_COIN);

        // Get all the mLuckWeeklyQuestStageRewardList where coin equals to UPDATED_COIN
        defaultMLuckWeeklyQuestStageRewardShouldNotBeFound("coin.in=" + UPDATED_COIN);
    }

    @Test
    @Transactional
    public void getAllMLuckWeeklyQuestStageRewardsByCoinIsNullOrNotNull() throws Exception {
        // Initialize the database
        mLuckWeeklyQuestStageRewardRepository.saveAndFlush(mLuckWeeklyQuestStageReward);

        // Get all the mLuckWeeklyQuestStageRewardList where coin is not null
        defaultMLuckWeeklyQuestStageRewardShouldBeFound("coin.specified=true");

        // Get all the mLuckWeeklyQuestStageRewardList where coin is null
        defaultMLuckWeeklyQuestStageRewardShouldNotBeFound("coin.specified=false");
    }

    @Test
    @Transactional
    public void getAllMLuckWeeklyQuestStageRewardsByCoinIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mLuckWeeklyQuestStageRewardRepository.saveAndFlush(mLuckWeeklyQuestStageReward);

        // Get all the mLuckWeeklyQuestStageRewardList where coin greater than or equals to DEFAULT_COIN
        defaultMLuckWeeklyQuestStageRewardShouldBeFound("coin.greaterOrEqualThan=" + DEFAULT_COIN);

        // Get all the mLuckWeeklyQuestStageRewardList where coin greater than or equals to UPDATED_COIN
        defaultMLuckWeeklyQuestStageRewardShouldNotBeFound("coin.greaterOrEqualThan=" + UPDATED_COIN);
    }

    @Test
    @Transactional
    public void getAllMLuckWeeklyQuestStageRewardsByCoinIsLessThanSomething() throws Exception {
        // Initialize the database
        mLuckWeeklyQuestStageRewardRepository.saveAndFlush(mLuckWeeklyQuestStageReward);

        // Get all the mLuckWeeklyQuestStageRewardList where coin less than or equals to DEFAULT_COIN
        defaultMLuckWeeklyQuestStageRewardShouldNotBeFound("coin.lessThan=" + DEFAULT_COIN);

        // Get all the mLuckWeeklyQuestStageRewardList where coin less than or equals to UPDATED_COIN
        defaultMLuckWeeklyQuestStageRewardShouldBeFound("coin.lessThan=" + UPDATED_COIN);
    }


    @Test
    @Transactional
    public void getAllMLuckWeeklyQuestStageRewardsByGuildPointIsEqualToSomething() throws Exception {
        // Initialize the database
        mLuckWeeklyQuestStageRewardRepository.saveAndFlush(mLuckWeeklyQuestStageReward);

        // Get all the mLuckWeeklyQuestStageRewardList where guildPoint equals to DEFAULT_GUILD_POINT
        defaultMLuckWeeklyQuestStageRewardShouldBeFound("guildPoint.equals=" + DEFAULT_GUILD_POINT);

        // Get all the mLuckWeeklyQuestStageRewardList where guildPoint equals to UPDATED_GUILD_POINT
        defaultMLuckWeeklyQuestStageRewardShouldNotBeFound("guildPoint.equals=" + UPDATED_GUILD_POINT);
    }

    @Test
    @Transactional
    public void getAllMLuckWeeklyQuestStageRewardsByGuildPointIsInShouldWork() throws Exception {
        // Initialize the database
        mLuckWeeklyQuestStageRewardRepository.saveAndFlush(mLuckWeeklyQuestStageReward);

        // Get all the mLuckWeeklyQuestStageRewardList where guildPoint in DEFAULT_GUILD_POINT or UPDATED_GUILD_POINT
        defaultMLuckWeeklyQuestStageRewardShouldBeFound("guildPoint.in=" + DEFAULT_GUILD_POINT + "," + UPDATED_GUILD_POINT);

        // Get all the mLuckWeeklyQuestStageRewardList where guildPoint equals to UPDATED_GUILD_POINT
        defaultMLuckWeeklyQuestStageRewardShouldNotBeFound("guildPoint.in=" + UPDATED_GUILD_POINT);
    }

    @Test
    @Transactional
    public void getAllMLuckWeeklyQuestStageRewardsByGuildPointIsNullOrNotNull() throws Exception {
        // Initialize the database
        mLuckWeeklyQuestStageRewardRepository.saveAndFlush(mLuckWeeklyQuestStageReward);

        // Get all the mLuckWeeklyQuestStageRewardList where guildPoint is not null
        defaultMLuckWeeklyQuestStageRewardShouldBeFound("guildPoint.specified=true");

        // Get all the mLuckWeeklyQuestStageRewardList where guildPoint is null
        defaultMLuckWeeklyQuestStageRewardShouldNotBeFound("guildPoint.specified=false");
    }

    @Test
    @Transactional
    public void getAllMLuckWeeklyQuestStageRewardsByGuildPointIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mLuckWeeklyQuestStageRewardRepository.saveAndFlush(mLuckWeeklyQuestStageReward);

        // Get all the mLuckWeeklyQuestStageRewardList where guildPoint greater than or equals to DEFAULT_GUILD_POINT
        defaultMLuckWeeklyQuestStageRewardShouldBeFound("guildPoint.greaterOrEqualThan=" + DEFAULT_GUILD_POINT);

        // Get all the mLuckWeeklyQuestStageRewardList where guildPoint greater than or equals to UPDATED_GUILD_POINT
        defaultMLuckWeeklyQuestStageRewardShouldNotBeFound("guildPoint.greaterOrEqualThan=" + UPDATED_GUILD_POINT);
    }

    @Test
    @Transactional
    public void getAllMLuckWeeklyQuestStageRewardsByGuildPointIsLessThanSomething() throws Exception {
        // Initialize the database
        mLuckWeeklyQuestStageRewardRepository.saveAndFlush(mLuckWeeklyQuestStageReward);

        // Get all the mLuckWeeklyQuestStageRewardList where guildPoint less than or equals to DEFAULT_GUILD_POINT
        defaultMLuckWeeklyQuestStageRewardShouldNotBeFound("guildPoint.lessThan=" + DEFAULT_GUILD_POINT);

        // Get all the mLuckWeeklyQuestStageRewardList where guildPoint less than or equals to UPDATED_GUILD_POINT
        defaultMLuckWeeklyQuestStageRewardShouldBeFound("guildPoint.lessThan=" + UPDATED_GUILD_POINT);
    }


    @Test
    @Transactional
    public void getAllMLuckWeeklyQuestStageRewardsByClearRewardGroupIdIsEqualToSomething() throws Exception {
        // Initialize the database
        mLuckWeeklyQuestStageRewardRepository.saveAndFlush(mLuckWeeklyQuestStageReward);

        // Get all the mLuckWeeklyQuestStageRewardList where clearRewardGroupId equals to DEFAULT_CLEAR_REWARD_GROUP_ID
        defaultMLuckWeeklyQuestStageRewardShouldBeFound("clearRewardGroupId.equals=" + DEFAULT_CLEAR_REWARD_GROUP_ID);

        // Get all the mLuckWeeklyQuestStageRewardList where clearRewardGroupId equals to UPDATED_CLEAR_REWARD_GROUP_ID
        defaultMLuckWeeklyQuestStageRewardShouldNotBeFound("clearRewardGroupId.equals=" + UPDATED_CLEAR_REWARD_GROUP_ID);
    }

    @Test
    @Transactional
    public void getAllMLuckWeeklyQuestStageRewardsByClearRewardGroupIdIsInShouldWork() throws Exception {
        // Initialize the database
        mLuckWeeklyQuestStageRewardRepository.saveAndFlush(mLuckWeeklyQuestStageReward);

        // Get all the mLuckWeeklyQuestStageRewardList where clearRewardGroupId in DEFAULT_CLEAR_REWARD_GROUP_ID or UPDATED_CLEAR_REWARD_GROUP_ID
        defaultMLuckWeeklyQuestStageRewardShouldBeFound("clearRewardGroupId.in=" + DEFAULT_CLEAR_REWARD_GROUP_ID + "," + UPDATED_CLEAR_REWARD_GROUP_ID);

        // Get all the mLuckWeeklyQuestStageRewardList where clearRewardGroupId equals to UPDATED_CLEAR_REWARD_GROUP_ID
        defaultMLuckWeeklyQuestStageRewardShouldNotBeFound("clearRewardGroupId.in=" + UPDATED_CLEAR_REWARD_GROUP_ID);
    }

    @Test
    @Transactional
    public void getAllMLuckWeeklyQuestStageRewardsByClearRewardGroupIdIsNullOrNotNull() throws Exception {
        // Initialize the database
        mLuckWeeklyQuestStageRewardRepository.saveAndFlush(mLuckWeeklyQuestStageReward);

        // Get all the mLuckWeeklyQuestStageRewardList where clearRewardGroupId is not null
        defaultMLuckWeeklyQuestStageRewardShouldBeFound("clearRewardGroupId.specified=true");

        // Get all the mLuckWeeklyQuestStageRewardList where clearRewardGroupId is null
        defaultMLuckWeeklyQuestStageRewardShouldNotBeFound("clearRewardGroupId.specified=false");
    }

    @Test
    @Transactional
    public void getAllMLuckWeeklyQuestStageRewardsByClearRewardGroupIdIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mLuckWeeklyQuestStageRewardRepository.saveAndFlush(mLuckWeeklyQuestStageReward);

        // Get all the mLuckWeeklyQuestStageRewardList where clearRewardGroupId greater than or equals to DEFAULT_CLEAR_REWARD_GROUP_ID
        defaultMLuckWeeklyQuestStageRewardShouldBeFound("clearRewardGroupId.greaterOrEqualThan=" + DEFAULT_CLEAR_REWARD_GROUP_ID);

        // Get all the mLuckWeeklyQuestStageRewardList where clearRewardGroupId greater than or equals to UPDATED_CLEAR_REWARD_GROUP_ID
        defaultMLuckWeeklyQuestStageRewardShouldNotBeFound("clearRewardGroupId.greaterOrEqualThan=" + UPDATED_CLEAR_REWARD_GROUP_ID);
    }

    @Test
    @Transactional
    public void getAllMLuckWeeklyQuestStageRewardsByClearRewardGroupIdIsLessThanSomething() throws Exception {
        // Initialize the database
        mLuckWeeklyQuestStageRewardRepository.saveAndFlush(mLuckWeeklyQuestStageReward);

        // Get all the mLuckWeeklyQuestStageRewardList where clearRewardGroupId less than or equals to DEFAULT_CLEAR_REWARD_GROUP_ID
        defaultMLuckWeeklyQuestStageRewardShouldNotBeFound("clearRewardGroupId.lessThan=" + DEFAULT_CLEAR_REWARD_GROUP_ID);

        // Get all the mLuckWeeklyQuestStageRewardList where clearRewardGroupId less than or equals to UPDATED_CLEAR_REWARD_GROUP_ID
        defaultMLuckWeeklyQuestStageRewardShouldBeFound("clearRewardGroupId.lessThan=" + UPDATED_CLEAR_REWARD_GROUP_ID);
    }


    @Test
    @Transactional
    public void getAllMLuckWeeklyQuestStageRewardsByClearRewardWeightIdIsEqualToSomething() throws Exception {
        // Initialize the database
        mLuckWeeklyQuestStageRewardRepository.saveAndFlush(mLuckWeeklyQuestStageReward);

        // Get all the mLuckWeeklyQuestStageRewardList where clearRewardWeightId equals to DEFAULT_CLEAR_REWARD_WEIGHT_ID
        defaultMLuckWeeklyQuestStageRewardShouldBeFound("clearRewardWeightId.equals=" + DEFAULT_CLEAR_REWARD_WEIGHT_ID);

        // Get all the mLuckWeeklyQuestStageRewardList where clearRewardWeightId equals to UPDATED_CLEAR_REWARD_WEIGHT_ID
        defaultMLuckWeeklyQuestStageRewardShouldNotBeFound("clearRewardWeightId.equals=" + UPDATED_CLEAR_REWARD_WEIGHT_ID);
    }

    @Test
    @Transactional
    public void getAllMLuckWeeklyQuestStageRewardsByClearRewardWeightIdIsInShouldWork() throws Exception {
        // Initialize the database
        mLuckWeeklyQuestStageRewardRepository.saveAndFlush(mLuckWeeklyQuestStageReward);

        // Get all the mLuckWeeklyQuestStageRewardList where clearRewardWeightId in DEFAULT_CLEAR_REWARD_WEIGHT_ID or UPDATED_CLEAR_REWARD_WEIGHT_ID
        defaultMLuckWeeklyQuestStageRewardShouldBeFound("clearRewardWeightId.in=" + DEFAULT_CLEAR_REWARD_WEIGHT_ID + "," + UPDATED_CLEAR_REWARD_WEIGHT_ID);

        // Get all the mLuckWeeklyQuestStageRewardList where clearRewardWeightId equals to UPDATED_CLEAR_REWARD_WEIGHT_ID
        defaultMLuckWeeklyQuestStageRewardShouldNotBeFound("clearRewardWeightId.in=" + UPDATED_CLEAR_REWARD_WEIGHT_ID);
    }

    @Test
    @Transactional
    public void getAllMLuckWeeklyQuestStageRewardsByClearRewardWeightIdIsNullOrNotNull() throws Exception {
        // Initialize the database
        mLuckWeeklyQuestStageRewardRepository.saveAndFlush(mLuckWeeklyQuestStageReward);

        // Get all the mLuckWeeklyQuestStageRewardList where clearRewardWeightId is not null
        defaultMLuckWeeklyQuestStageRewardShouldBeFound("clearRewardWeightId.specified=true");

        // Get all the mLuckWeeklyQuestStageRewardList where clearRewardWeightId is null
        defaultMLuckWeeklyQuestStageRewardShouldNotBeFound("clearRewardWeightId.specified=false");
    }

    @Test
    @Transactional
    public void getAllMLuckWeeklyQuestStageRewardsByClearRewardWeightIdIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mLuckWeeklyQuestStageRewardRepository.saveAndFlush(mLuckWeeklyQuestStageReward);

        // Get all the mLuckWeeklyQuestStageRewardList where clearRewardWeightId greater than or equals to DEFAULT_CLEAR_REWARD_WEIGHT_ID
        defaultMLuckWeeklyQuestStageRewardShouldBeFound("clearRewardWeightId.greaterOrEqualThan=" + DEFAULT_CLEAR_REWARD_WEIGHT_ID);

        // Get all the mLuckWeeklyQuestStageRewardList where clearRewardWeightId greater than or equals to UPDATED_CLEAR_REWARD_WEIGHT_ID
        defaultMLuckWeeklyQuestStageRewardShouldNotBeFound("clearRewardWeightId.greaterOrEqualThan=" + UPDATED_CLEAR_REWARD_WEIGHT_ID);
    }

    @Test
    @Transactional
    public void getAllMLuckWeeklyQuestStageRewardsByClearRewardWeightIdIsLessThanSomething() throws Exception {
        // Initialize the database
        mLuckWeeklyQuestStageRewardRepository.saveAndFlush(mLuckWeeklyQuestStageReward);

        // Get all the mLuckWeeklyQuestStageRewardList where clearRewardWeightId less than or equals to DEFAULT_CLEAR_REWARD_WEIGHT_ID
        defaultMLuckWeeklyQuestStageRewardShouldNotBeFound("clearRewardWeightId.lessThan=" + DEFAULT_CLEAR_REWARD_WEIGHT_ID);

        // Get all the mLuckWeeklyQuestStageRewardList where clearRewardWeightId less than or equals to UPDATED_CLEAR_REWARD_WEIGHT_ID
        defaultMLuckWeeklyQuestStageRewardShouldBeFound("clearRewardWeightId.lessThan=" + UPDATED_CLEAR_REWARD_WEIGHT_ID);
    }


    @Test
    @Transactional
    public void getAllMLuckWeeklyQuestStageRewardsByAchievementRewardGroupIdIsEqualToSomething() throws Exception {
        // Initialize the database
        mLuckWeeklyQuestStageRewardRepository.saveAndFlush(mLuckWeeklyQuestStageReward);

        // Get all the mLuckWeeklyQuestStageRewardList where achievementRewardGroupId equals to DEFAULT_ACHIEVEMENT_REWARD_GROUP_ID
        defaultMLuckWeeklyQuestStageRewardShouldBeFound("achievementRewardGroupId.equals=" + DEFAULT_ACHIEVEMENT_REWARD_GROUP_ID);

        // Get all the mLuckWeeklyQuestStageRewardList where achievementRewardGroupId equals to UPDATED_ACHIEVEMENT_REWARD_GROUP_ID
        defaultMLuckWeeklyQuestStageRewardShouldNotBeFound("achievementRewardGroupId.equals=" + UPDATED_ACHIEVEMENT_REWARD_GROUP_ID);
    }

    @Test
    @Transactional
    public void getAllMLuckWeeklyQuestStageRewardsByAchievementRewardGroupIdIsInShouldWork() throws Exception {
        // Initialize the database
        mLuckWeeklyQuestStageRewardRepository.saveAndFlush(mLuckWeeklyQuestStageReward);

        // Get all the mLuckWeeklyQuestStageRewardList where achievementRewardGroupId in DEFAULT_ACHIEVEMENT_REWARD_GROUP_ID or UPDATED_ACHIEVEMENT_REWARD_GROUP_ID
        defaultMLuckWeeklyQuestStageRewardShouldBeFound("achievementRewardGroupId.in=" + DEFAULT_ACHIEVEMENT_REWARD_GROUP_ID + "," + UPDATED_ACHIEVEMENT_REWARD_GROUP_ID);

        // Get all the mLuckWeeklyQuestStageRewardList where achievementRewardGroupId equals to UPDATED_ACHIEVEMENT_REWARD_GROUP_ID
        defaultMLuckWeeklyQuestStageRewardShouldNotBeFound("achievementRewardGroupId.in=" + UPDATED_ACHIEVEMENT_REWARD_GROUP_ID);
    }

    @Test
    @Transactional
    public void getAllMLuckWeeklyQuestStageRewardsByAchievementRewardGroupIdIsNullOrNotNull() throws Exception {
        // Initialize the database
        mLuckWeeklyQuestStageRewardRepository.saveAndFlush(mLuckWeeklyQuestStageReward);

        // Get all the mLuckWeeklyQuestStageRewardList where achievementRewardGroupId is not null
        defaultMLuckWeeklyQuestStageRewardShouldBeFound("achievementRewardGroupId.specified=true");

        // Get all the mLuckWeeklyQuestStageRewardList where achievementRewardGroupId is null
        defaultMLuckWeeklyQuestStageRewardShouldNotBeFound("achievementRewardGroupId.specified=false");
    }

    @Test
    @Transactional
    public void getAllMLuckWeeklyQuestStageRewardsByAchievementRewardGroupIdIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mLuckWeeklyQuestStageRewardRepository.saveAndFlush(mLuckWeeklyQuestStageReward);

        // Get all the mLuckWeeklyQuestStageRewardList where achievementRewardGroupId greater than or equals to DEFAULT_ACHIEVEMENT_REWARD_GROUP_ID
        defaultMLuckWeeklyQuestStageRewardShouldBeFound("achievementRewardGroupId.greaterOrEqualThan=" + DEFAULT_ACHIEVEMENT_REWARD_GROUP_ID);

        // Get all the mLuckWeeklyQuestStageRewardList where achievementRewardGroupId greater than or equals to UPDATED_ACHIEVEMENT_REWARD_GROUP_ID
        defaultMLuckWeeklyQuestStageRewardShouldNotBeFound("achievementRewardGroupId.greaterOrEqualThan=" + UPDATED_ACHIEVEMENT_REWARD_GROUP_ID);
    }

    @Test
    @Transactional
    public void getAllMLuckWeeklyQuestStageRewardsByAchievementRewardGroupIdIsLessThanSomething() throws Exception {
        // Initialize the database
        mLuckWeeklyQuestStageRewardRepository.saveAndFlush(mLuckWeeklyQuestStageReward);

        // Get all the mLuckWeeklyQuestStageRewardList where achievementRewardGroupId less than or equals to DEFAULT_ACHIEVEMENT_REWARD_GROUP_ID
        defaultMLuckWeeklyQuestStageRewardShouldNotBeFound("achievementRewardGroupId.lessThan=" + DEFAULT_ACHIEVEMENT_REWARD_GROUP_ID);

        // Get all the mLuckWeeklyQuestStageRewardList where achievementRewardGroupId less than or equals to UPDATED_ACHIEVEMENT_REWARD_GROUP_ID
        defaultMLuckWeeklyQuestStageRewardShouldBeFound("achievementRewardGroupId.lessThan=" + UPDATED_ACHIEVEMENT_REWARD_GROUP_ID);
    }


    @Test
    @Transactional
    public void getAllMLuckWeeklyQuestStageRewardsByCoopGroupIdIsEqualToSomething() throws Exception {
        // Initialize the database
        mLuckWeeklyQuestStageRewardRepository.saveAndFlush(mLuckWeeklyQuestStageReward);

        // Get all the mLuckWeeklyQuestStageRewardList where coopGroupId equals to DEFAULT_COOP_GROUP_ID
        defaultMLuckWeeklyQuestStageRewardShouldBeFound("coopGroupId.equals=" + DEFAULT_COOP_GROUP_ID);

        // Get all the mLuckWeeklyQuestStageRewardList where coopGroupId equals to UPDATED_COOP_GROUP_ID
        defaultMLuckWeeklyQuestStageRewardShouldNotBeFound("coopGroupId.equals=" + UPDATED_COOP_GROUP_ID);
    }

    @Test
    @Transactional
    public void getAllMLuckWeeklyQuestStageRewardsByCoopGroupIdIsInShouldWork() throws Exception {
        // Initialize the database
        mLuckWeeklyQuestStageRewardRepository.saveAndFlush(mLuckWeeklyQuestStageReward);

        // Get all the mLuckWeeklyQuestStageRewardList where coopGroupId in DEFAULT_COOP_GROUP_ID or UPDATED_COOP_GROUP_ID
        defaultMLuckWeeklyQuestStageRewardShouldBeFound("coopGroupId.in=" + DEFAULT_COOP_GROUP_ID + "," + UPDATED_COOP_GROUP_ID);

        // Get all the mLuckWeeklyQuestStageRewardList where coopGroupId equals to UPDATED_COOP_GROUP_ID
        defaultMLuckWeeklyQuestStageRewardShouldNotBeFound("coopGroupId.in=" + UPDATED_COOP_GROUP_ID);
    }

    @Test
    @Transactional
    public void getAllMLuckWeeklyQuestStageRewardsByCoopGroupIdIsNullOrNotNull() throws Exception {
        // Initialize the database
        mLuckWeeklyQuestStageRewardRepository.saveAndFlush(mLuckWeeklyQuestStageReward);

        // Get all the mLuckWeeklyQuestStageRewardList where coopGroupId is not null
        defaultMLuckWeeklyQuestStageRewardShouldBeFound("coopGroupId.specified=true");

        // Get all the mLuckWeeklyQuestStageRewardList where coopGroupId is null
        defaultMLuckWeeklyQuestStageRewardShouldNotBeFound("coopGroupId.specified=false");
    }

    @Test
    @Transactional
    public void getAllMLuckWeeklyQuestStageRewardsByCoopGroupIdIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mLuckWeeklyQuestStageRewardRepository.saveAndFlush(mLuckWeeklyQuestStageReward);

        // Get all the mLuckWeeklyQuestStageRewardList where coopGroupId greater than or equals to DEFAULT_COOP_GROUP_ID
        defaultMLuckWeeklyQuestStageRewardShouldBeFound("coopGroupId.greaterOrEqualThan=" + DEFAULT_COOP_GROUP_ID);

        // Get all the mLuckWeeklyQuestStageRewardList where coopGroupId greater than or equals to UPDATED_COOP_GROUP_ID
        defaultMLuckWeeklyQuestStageRewardShouldNotBeFound("coopGroupId.greaterOrEqualThan=" + UPDATED_COOP_GROUP_ID);
    }

    @Test
    @Transactional
    public void getAllMLuckWeeklyQuestStageRewardsByCoopGroupIdIsLessThanSomething() throws Exception {
        // Initialize the database
        mLuckWeeklyQuestStageRewardRepository.saveAndFlush(mLuckWeeklyQuestStageReward);

        // Get all the mLuckWeeklyQuestStageRewardList where coopGroupId less than or equals to DEFAULT_COOP_GROUP_ID
        defaultMLuckWeeklyQuestStageRewardShouldNotBeFound("coopGroupId.lessThan=" + DEFAULT_COOP_GROUP_ID);

        // Get all the mLuckWeeklyQuestStageRewardList where coopGroupId less than or equals to UPDATED_COOP_GROUP_ID
        defaultMLuckWeeklyQuestStageRewardShouldBeFound("coopGroupId.lessThan=" + UPDATED_COOP_GROUP_ID);
    }


    @Test
    @Transactional
    public void getAllMLuckWeeklyQuestStageRewardsBySpecialRewardGroupIdIsEqualToSomething() throws Exception {
        // Initialize the database
        mLuckWeeklyQuestStageRewardRepository.saveAndFlush(mLuckWeeklyQuestStageReward);

        // Get all the mLuckWeeklyQuestStageRewardList where specialRewardGroupId equals to DEFAULT_SPECIAL_REWARD_GROUP_ID
        defaultMLuckWeeklyQuestStageRewardShouldBeFound("specialRewardGroupId.equals=" + DEFAULT_SPECIAL_REWARD_GROUP_ID);

        // Get all the mLuckWeeklyQuestStageRewardList where specialRewardGroupId equals to UPDATED_SPECIAL_REWARD_GROUP_ID
        defaultMLuckWeeklyQuestStageRewardShouldNotBeFound("specialRewardGroupId.equals=" + UPDATED_SPECIAL_REWARD_GROUP_ID);
    }

    @Test
    @Transactional
    public void getAllMLuckWeeklyQuestStageRewardsBySpecialRewardGroupIdIsInShouldWork() throws Exception {
        // Initialize the database
        mLuckWeeklyQuestStageRewardRepository.saveAndFlush(mLuckWeeklyQuestStageReward);

        // Get all the mLuckWeeklyQuestStageRewardList where specialRewardGroupId in DEFAULT_SPECIAL_REWARD_GROUP_ID or UPDATED_SPECIAL_REWARD_GROUP_ID
        defaultMLuckWeeklyQuestStageRewardShouldBeFound("specialRewardGroupId.in=" + DEFAULT_SPECIAL_REWARD_GROUP_ID + "," + UPDATED_SPECIAL_REWARD_GROUP_ID);

        // Get all the mLuckWeeklyQuestStageRewardList where specialRewardGroupId equals to UPDATED_SPECIAL_REWARD_GROUP_ID
        defaultMLuckWeeklyQuestStageRewardShouldNotBeFound("specialRewardGroupId.in=" + UPDATED_SPECIAL_REWARD_GROUP_ID);
    }

    @Test
    @Transactional
    public void getAllMLuckWeeklyQuestStageRewardsBySpecialRewardGroupIdIsNullOrNotNull() throws Exception {
        // Initialize the database
        mLuckWeeklyQuestStageRewardRepository.saveAndFlush(mLuckWeeklyQuestStageReward);

        // Get all the mLuckWeeklyQuestStageRewardList where specialRewardGroupId is not null
        defaultMLuckWeeklyQuestStageRewardShouldBeFound("specialRewardGroupId.specified=true");

        // Get all the mLuckWeeklyQuestStageRewardList where specialRewardGroupId is null
        defaultMLuckWeeklyQuestStageRewardShouldNotBeFound("specialRewardGroupId.specified=false");
    }

    @Test
    @Transactional
    public void getAllMLuckWeeklyQuestStageRewardsBySpecialRewardGroupIdIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mLuckWeeklyQuestStageRewardRepository.saveAndFlush(mLuckWeeklyQuestStageReward);

        // Get all the mLuckWeeklyQuestStageRewardList where specialRewardGroupId greater than or equals to DEFAULT_SPECIAL_REWARD_GROUP_ID
        defaultMLuckWeeklyQuestStageRewardShouldBeFound("specialRewardGroupId.greaterOrEqualThan=" + DEFAULT_SPECIAL_REWARD_GROUP_ID);

        // Get all the mLuckWeeklyQuestStageRewardList where specialRewardGroupId greater than or equals to UPDATED_SPECIAL_REWARD_GROUP_ID
        defaultMLuckWeeklyQuestStageRewardShouldNotBeFound("specialRewardGroupId.greaterOrEqualThan=" + UPDATED_SPECIAL_REWARD_GROUP_ID);
    }

    @Test
    @Transactional
    public void getAllMLuckWeeklyQuestStageRewardsBySpecialRewardGroupIdIsLessThanSomething() throws Exception {
        // Initialize the database
        mLuckWeeklyQuestStageRewardRepository.saveAndFlush(mLuckWeeklyQuestStageReward);

        // Get all the mLuckWeeklyQuestStageRewardList where specialRewardGroupId less than or equals to DEFAULT_SPECIAL_REWARD_GROUP_ID
        defaultMLuckWeeklyQuestStageRewardShouldNotBeFound("specialRewardGroupId.lessThan=" + DEFAULT_SPECIAL_REWARD_GROUP_ID);

        // Get all the mLuckWeeklyQuestStageRewardList where specialRewardGroupId less than or equals to UPDATED_SPECIAL_REWARD_GROUP_ID
        defaultMLuckWeeklyQuestStageRewardShouldBeFound("specialRewardGroupId.lessThan=" + UPDATED_SPECIAL_REWARD_GROUP_ID);
    }


    @Test
    @Transactional
    public void getAllMLuckWeeklyQuestStageRewardsBySpecialRewardAmountIsEqualToSomething() throws Exception {
        // Initialize the database
        mLuckWeeklyQuestStageRewardRepository.saveAndFlush(mLuckWeeklyQuestStageReward);

        // Get all the mLuckWeeklyQuestStageRewardList where specialRewardAmount equals to DEFAULT_SPECIAL_REWARD_AMOUNT
        defaultMLuckWeeklyQuestStageRewardShouldBeFound("specialRewardAmount.equals=" + DEFAULT_SPECIAL_REWARD_AMOUNT);

        // Get all the mLuckWeeklyQuestStageRewardList where specialRewardAmount equals to UPDATED_SPECIAL_REWARD_AMOUNT
        defaultMLuckWeeklyQuestStageRewardShouldNotBeFound("specialRewardAmount.equals=" + UPDATED_SPECIAL_REWARD_AMOUNT);
    }

    @Test
    @Transactional
    public void getAllMLuckWeeklyQuestStageRewardsBySpecialRewardAmountIsInShouldWork() throws Exception {
        // Initialize the database
        mLuckWeeklyQuestStageRewardRepository.saveAndFlush(mLuckWeeklyQuestStageReward);

        // Get all the mLuckWeeklyQuestStageRewardList where specialRewardAmount in DEFAULT_SPECIAL_REWARD_AMOUNT or UPDATED_SPECIAL_REWARD_AMOUNT
        defaultMLuckWeeklyQuestStageRewardShouldBeFound("specialRewardAmount.in=" + DEFAULT_SPECIAL_REWARD_AMOUNT + "," + UPDATED_SPECIAL_REWARD_AMOUNT);

        // Get all the mLuckWeeklyQuestStageRewardList where specialRewardAmount equals to UPDATED_SPECIAL_REWARD_AMOUNT
        defaultMLuckWeeklyQuestStageRewardShouldNotBeFound("specialRewardAmount.in=" + UPDATED_SPECIAL_REWARD_AMOUNT);
    }

    @Test
    @Transactional
    public void getAllMLuckWeeklyQuestStageRewardsBySpecialRewardAmountIsNullOrNotNull() throws Exception {
        // Initialize the database
        mLuckWeeklyQuestStageRewardRepository.saveAndFlush(mLuckWeeklyQuestStageReward);

        // Get all the mLuckWeeklyQuestStageRewardList where specialRewardAmount is not null
        defaultMLuckWeeklyQuestStageRewardShouldBeFound("specialRewardAmount.specified=true");

        // Get all the mLuckWeeklyQuestStageRewardList where specialRewardAmount is null
        defaultMLuckWeeklyQuestStageRewardShouldNotBeFound("specialRewardAmount.specified=false");
    }

    @Test
    @Transactional
    public void getAllMLuckWeeklyQuestStageRewardsBySpecialRewardAmountIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mLuckWeeklyQuestStageRewardRepository.saveAndFlush(mLuckWeeklyQuestStageReward);

        // Get all the mLuckWeeklyQuestStageRewardList where specialRewardAmount greater than or equals to DEFAULT_SPECIAL_REWARD_AMOUNT
        defaultMLuckWeeklyQuestStageRewardShouldBeFound("specialRewardAmount.greaterOrEqualThan=" + DEFAULT_SPECIAL_REWARD_AMOUNT);

        // Get all the mLuckWeeklyQuestStageRewardList where specialRewardAmount greater than or equals to UPDATED_SPECIAL_REWARD_AMOUNT
        defaultMLuckWeeklyQuestStageRewardShouldNotBeFound("specialRewardAmount.greaterOrEqualThan=" + UPDATED_SPECIAL_REWARD_AMOUNT);
    }

    @Test
    @Transactional
    public void getAllMLuckWeeklyQuestStageRewardsBySpecialRewardAmountIsLessThanSomething() throws Exception {
        // Initialize the database
        mLuckWeeklyQuestStageRewardRepository.saveAndFlush(mLuckWeeklyQuestStageReward);

        // Get all the mLuckWeeklyQuestStageRewardList where specialRewardAmount less than or equals to DEFAULT_SPECIAL_REWARD_AMOUNT
        defaultMLuckWeeklyQuestStageRewardShouldNotBeFound("specialRewardAmount.lessThan=" + DEFAULT_SPECIAL_REWARD_AMOUNT);

        // Get all the mLuckWeeklyQuestStageRewardList where specialRewardAmount less than or equals to UPDATED_SPECIAL_REWARD_AMOUNT
        defaultMLuckWeeklyQuestStageRewardShouldBeFound("specialRewardAmount.lessThan=" + UPDATED_SPECIAL_REWARD_AMOUNT);
    }


    @Test
    @Transactional
    public void getAllMLuckWeeklyQuestStageRewardsByGoalRewardGroupIdIsEqualToSomething() throws Exception {
        // Initialize the database
        mLuckWeeklyQuestStageRewardRepository.saveAndFlush(mLuckWeeklyQuestStageReward);

        // Get all the mLuckWeeklyQuestStageRewardList where goalRewardGroupId equals to DEFAULT_GOAL_REWARD_GROUP_ID
        defaultMLuckWeeklyQuestStageRewardShouldBeFound("goalRewardGroupId.equals=" + DEFAULT_GOAL_REWARD_GROUP_ID);

        // Get all the mLuckWeeklyQuestStageRewardList where goalRewardGroupId equals to UPDATED_GOAL_REWARD_GROUP_ID
        defaultMLuckWeeklyQuestStageRewardShouldNotBeFound("goalRewardGroupId.equals=" + UPDATED_GOAL_REWARD_GROUP_ID);
    }

    @Test
    @Transactional
    public void getAllMLuckWeeklyQuestStageRewardsByGoalRewardGroupIdIsInShouldWork() throws Exception {
        // Initialize the database
        mLuckWeeklyQuestStageRewardRepository.saveAndFlush(mLuckWeeklyQuestStageReward);

        // Get all the mLuckWeeklyQuestStageRewardList where goalRewardGroupId in DEFAULT_GOAL_REWARD_GROUP_ID or UPDATED_GOAL_REWARD_GROUP_ID
        defaultMLuckWeeklyQuestStageRewardShouldBeFound("goalRewardGroupId.in=" + DEFAULT_GOAL_REWARD_GROUP_ID + "," + UPDATED_GOAL_REWARD_GROUP_ID);

        // Get all the mLuckWeeklyQuestStageRewardList where goalRewardGroupId equals to UPDATED_GOAL_REWARD_GROUP_ID
        defaultMLuckWeeklyQuestStageRewardShouldNotBeFound("goalRewardGroupId.in=" + UPDATED_GOAL_REWARD_GROUP_ID);
    }

    @Test
    @Transactional
    public void getAllMLuckWeeklyQuestStageRewardsByGoalRewardGroupIdIsNullOrNotNull() throws Exception {
        // Initialize the database
        mLuckWeeklyQuestStageRewardRepository.saveAndFlush(mLuckWeeklyQuestStageReward);

        // Get all the mLuckWeeklyQuestStageRewardList where goalRewardGroupId is not null
        defaultMLuckWeeklyQuestStageRewardShouldBeFound("goalRewardGroupId.specified=true");

        // Get all the mLuckWeeklyQuestStageRewardList where goalRewardGroupId is null
        defaultMLuckWeeklyQuestStageRewardShouldNotBeFound("goalRewardGroupId.specified=false");
    }

    @Test
    @Transactional
    public void getAllMLuckWeeklyQuestStageRewardsByGoalRewardGroupIdIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mLuckWeeklyQuestStageRewardRepository.saveAndFlush(mLuckWeeklyQuestStageReward);

        // Get all the mLuckWeeklyQuestStageRewardList where goalRewardGroupId greater than or equals to DEFAULT_GOAL_REWARD_GROUP_ID
        defaultMLuckWeeklyQuestStageRewardShouldBeFound("goalRewardGroupId.greaterOrEqualThan=" + DEFAULT_GOAL_REWARD_GROUP_ID);

        // Get all the mLuckWeeklyQuestStageRewardList where goalRewardGroupId greater than or equals to UPDATED_GOAL_REWARD_GROUP_ID
        defaultMLuckWeeklyQuestStageRewardShouldNotBeFound("goalRewardGroupId.greaterOrEqualThan=" + UPDATED_GOAL_REWARD_GROUP_ID);
    }

    @Test
    @Transactional
    public void getAllMLuckWeeklyQuestStageRewardsByGoalRewardGroupIdIsLessThanSomething() throws Exception {
        // Initialize the database
        mLuckWeeklyQuestStageRewardRepository.saveAndFlush(mLuckWeeklyQuestStageReward);

        // Get all the mLuckWeeklyQuestStageRewardList where goalRewardGroupId less than or equals to DEFAULT_GOAL_REWARD_GROUP_ID
        defaultMLuckWeeklyQuestStageRewardShouldNotBeFound("goalRewardGroupId.lessThan=" + DEFAULT_GOAL_REWARD_GROUP_ID);

        // Get all the mLuckWeeklyQuestStageRewardList where goalRewardGroupId less than or equals to UPDATED_GOAL_REWARD_GROUP_ID
        defaultMLuckWeeklyQuestStageRewardShouldBeFound("goalRewardGroupId.lessThan=" + UPDATED_GOAL_REWARD_GROUP_ID);
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultMLuckWeeklyQuestStageRewardShouldBeFound(String filter) throws Exception {
        restMLuckWeeklyQuestStageRewardMockMvc.perform(get("/api/m-luck-weekly-quest-stage-rewards?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(mLuckWeeklyQuestStageReward.getId().intValue())))
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
        restMLuckWeeklyQuestStageRewardMockMvc.perform(get("/api/m-luck-weekly-quest-stage-rewards/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultMLuckWeeklyQuestStageRewardShouldNotBeFound(String filter) throws Exception {
        restMLuckWeeklyQuestStageRewardMockMvc.perform(get("/api/m-luck-weekly-quest-stage-rewards?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restMLuckWeeklyQuestStageRewardMockMvc.perform(get("/api/m-luck-weekly-quest-stage-rewards/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("0"));
    }


    @Test
    @Transactional
    public void getNonExistingMLuckWeeklyQuestStageReward() throws Exception {
        // Get the mLuckWeeklyQuestStageReward
        restMLuckWeeklyQuestStageRewardMockMvc.perform(get("/api/m-luck-weekly-quest-stage-rewards/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateMLuckWeeklyQuestStageReward() throws Exception {
        // Initialize the database
        mLuckWeeklyQuestStageRewardRepository.saveAndFlush(mLuckWeeklyQuestStageReward);

        int databaseSizeBeforeUpdate = mLuckWeeklyQuestStageRewardRepository.findAll().size();

        // Update the mLuckWeeklyQuestStageReward
        MLuckWeeklyQuestStageReward updatedMLuckWeeklyQuestStageReward = mLuckWeeklyQuestStageRewardRepository.findById(mLuckWeeklyQuestStageReward.getId()).get();
        // Disconnect from session so that the updates on updatedMLuckWeeklyQuestStageReward are not directly saved in db
        em.detach(updatedMLuckWeeklyQuestStageReward);
        updatedMLuckWeeklyQuestStageReward
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
        MLuckWeeklyQuestStageRewardDTO mLuckWeeklyQuestStageRewardDTO = mLuckWeeklyQuestStageRewardMapper.toDto(updatedMLuckWeeklyQuestStageReward);

        restMLuckWeeklyQuestStageRewardMockMvc.perform(put("/api/m-luck-weekly-quest-stage-rewards")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mLuckWeeklyQuestStageRewardDTO)))
            .andExpect(status().isOk());

        // Validate the MLuckWeeklyQuestStageReward in the database
        List<MLuckWeeklyQuestStageReward> mLuckWeeklyQuestStageRewardList = mLuckWeeklyQuestStageRewardRepository.findAll();
        assertThat(mLuckWeeklyQuestStageRewardList).hasSize(databaseSizeBeforeUpdate);
        MLuckWeeklyQuestStageReward testMLuckWeeklyQuestStageReward = mLuckWeeklyQuestStageRewardList.get(mLuckWeeklyQuestStageRewardList.size() - 1);
        assertThat(testMLuckWeeklyQuestStageReward.getStageId()).isEqualTo(UPDATED_STAGE_ID);
        assertThat(testMLuckWeeklyQuestStageReward.getExp()).isEqualTo(UPDATED_EXP);
        assertThat(testMLuckWeeklyQuestStageReward.getCoin()).isEqualTo(UPDATED_COIN);
        assertThat(testMLuckWeeklyQuestStageReward.getGuildPoint()).isEqualTo(UPDATED_GUILD_POINT);
        assertThat(testMLuckWeeklyQuestStageReward.getClearRewardGroupId()).isEqualTo(UPDATED_CLEAR_REWARD_GROUP_ID);
        assertThat(testMLuckWeeklyQuestStageReward.getClearRewardWeightId()).isEqualTo(UPDATED_CLEAR_REWARD_WEIGHT_ID);
        assertThat(testMLuckWeeklyQuestStageReward.getAchievementRewardGroupId()).isEqualTo(UPDATED_ACHIEVEMENT_REWARD_GROUP_ID);
        assertThat(testMLuckWeeklyQuestStageReward.getCoopGroupId()).isEqualTo(UPDATED_COOP_GROUP_ID);
        assertThat(testMLuckWeeklyQuestStageReward.getSpecialRewardGroupId()).isEqualTo(UPDATED_SPECIAL_REWARD_GROUP_ID);
        assertThat(testMLuckWeeklyQuestStageReward.getSpecialRewardAmount()).isEqualTo(UPDATED_SPECIAL_REWARD_AMOUNT);
        assertThat(testMLuckWeeklyQuestStageReward.getGoalRewardGroupId()).isEqualTo(UPDATED_GOAL_REWARD_GROUP_ID);
    }

    @Test
    @Transactional
    public void updateNonExistingMLuckWeeklyQuestStageReward() throws Exception {
        int databaseSizeBeforeUpdate = mLuckWeeklyQuestStageRewardRepository.findAll().size();

        // Create the MLuckWeeklyQuestStageReward
        MLuckWeeklyQuestStageRewardDTO mLuckWeeklyQuestStageRewardDTO = mLuckWeeklyQuestStageRewardMapper.toDto(mLuckWeeklyQuestStageReward);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restMLuckWeeklyQuestStageRewardMockMvc.perform(put("/api/m-luck-weekly-quest-stage-rewards")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mLuckWeeklyQuestStageRewardDTO)))
            .andExpect(status().isBadRequest());

        // Validate the MLuckWeeklyQuestStageReward in the database
        List<MLuckWeeklyQuestStageReward> mLuckWeeklyQuestStageRewardList = mLuckWeeklyQuestStageRewardRepository.findAll();
        assertThat(mLuckWeeklyQuestStageRewardList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteMLuckWeeklyQuestStageReward() throws Exception {
        // Initialize the database
        mLuckWeeklyQuestStageRewardRepository.saveAndFlush(mLuckWeeklyQuestStageReward);

        int databaseSizeBeforeDelete = mLuckWeeklyQuestStageRewardRepository.findAll().size();

        // Delete the mLuckWeeklyQuestStageReward
        restMLuckWeeklyQuestStageRewardMockMvc.perform(delete("/api/m-luck-weekly-quest-stage-rewards/{id}", mLuckWeeklyQuestStageReward.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database is empty
        List<MLuckWeeklyQuestStageReward> mLuckWeeklyQuestStageRewardList = mLuckWeeklyQuestStageRewardRepository.findAll();
        assertThat(mLuckWeeklyQuestStageRewardList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(MLuckWeeklyQuestStageReward.class);
        MLuckWeeklyQuestStageReward mLuckWeeklyQuestStageReward1 = new MLuckWeeklyQuestStageReward();
        mLuckWeeklyQuestStageReward1.setId(1L);
        MLuckWeeklyQuestStageReward mLuckWeeklyQuestStageReward2 = new MLuckWeeklyQuestStageReward();
        mLuckWeeklyQuestStageReward2.setId(mLuckWeeklyQuestStageReward1.getId());
        assertThat(mLuckWeeklyQuestStageReward1).isEqualTo(mLuckWeeklyQuestStageReward2);
        mLuckWeeklyQuestStageReward2.setId(2L);
        assertThat(mLuckWeeklyQuestStageReward1).isNotEqualTo(mLuckWeeklyQuestStageReward2);
        mLuckWeeklyQuestStageReward1.setId(null);
        assertThat(mLuckWeeklyQuestStageReward1).isNotEqualTo(mLuckWeeklyQuestStageReward2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(MLuckWeeklyQuestStageRewardDTO.class);
        MLuckWeeklyQuestStageRewardDTO mLuckWeeklyQuestStageRewardDTO1 = new MLuckWeeklyQuestStageRewardDTO();
        mLuckWeeklyQuestStageRewardDTO1.setId(1L);
        MLuckWeeklyQuestStageRewardDTO mLuckWeeklyQuestStageRewardDTO2 = new MLuckWeeklyQuestStageRewardDTO();
        assertThat(mLuckWeeklyQuestStageRewardDTO1).isNotEqualTo(mLuckWeeklyQuestStageRewardDTO2);
        mLuckWeeklyQuestStageRewardDTO2.setId(mLuckWeeklyQuestStageRewardDTO1.getId());
        assertThat(mLuckWeeklyQuestStageRewardDTO1).isEqualTo(mLuckWeeklyQuestStageRewardDTO2);
        mLuckWeeklyQuestStageRewardDTO2.setId(2L);
        assertThat(mLuckWeeklyQuestStageRewardDTO1).isNotEqualTo(mLuckWeeklyQuestStageRewardDTO2);
        mLuckWeeklyQuestStageRewardDTO1.setId(null);
        assertThat(mLuckWeeklyQuestStageRewardDTO1).isNotEqualTo(mLuckWeeklyQuestStageRewardDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(mLuckWeeklyQuestStageRewardMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(mLuckWeeklyQuestStageRewardMapper.fromId(null)).isNull();
    }
}
