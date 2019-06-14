package io.shm.tsubasa.web.rest;

import io.shm.tsubasa.TsubasaApp;
import io.shm.tsubasa.domain.MTimeattackQuestStageReward;
import io.shm.tsubasa.repository.MTimeattackQuestStageRewardRepository;
import io.shm.tsubasa.service.MTimeattackQuestStageRewardService;
import io.shm.tsubasa.service.dto.MTimeattackQuestStageRewardDTO;
import io.shm.tsubasa.service.mapper.MTimeattackQuestStageRewardMapper;
import io.shm.tsubasa.web.rest.errors.ExceptionTranslator;
import io.shm.tsubasa.service.dto.MTimeattackQuestStageRewardCriteria;
import io.shm.tsubasa.service.MTimeattackQuestStageRewardQueryService;

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
 * Integration tests for the {@Link MTimeattackQuestStageRewardResource} REST controller.
 */
@SpringBootTest(classes = TsubasaApp.class)
public class MTimeattackQuestStageRewardResourceIT {

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
    private MTimeattackQuestStageRewardRepository mTimeattackQuestStageRewardRepository;

    @Autowired
    private MTimeattackQuestStageRewardMapper mTimeattackQuestStageRewardMapper;

    @Autowired
    private MTimeattackQuestStageRewardService mTimeattackQuestStageRewardService;

    @Autowired
    private MTimeattackQuestStageRewardQueryService mTimeattackQuestStageRewardQueryService;

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

    private MockMvc restMTimeattackQuestStageRewardMockMvc;

    private MTimeattackQuestStageReward mTimeattackQuestStageReward;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final MTimeattackQuestStageRewardResource mTimeattackQuestStageRewardResource = new MTimeattackQuestStageRewardResource(mTimeattackQuestStageRewardService, mTimeattackQuestStageRewardQueryService);
        this.restMTimeattackQuestStageRewardMockMvc = MockMvcBuilders.standaloneSetup(mTimeattackQuestStageRewardResource)
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
    public static MTimeattackQuestStageReward createEntity(EntityManager em) {
        MTimeattackQuestStageReward mTimeattackQuestStageReward = new MTimeattackQuestStageReward()
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
        return mTimeattackQuestStageReward;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static MTimeattackQuestStageReward createUpdatedEntity(EntityManager em) {
        MTimeattackQuestStageReward mTimeattackQuestStageReward = new MTimeattackQuestStageReward()
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
        return mTimeattackQuestStageReward;
    }

    @BeforeEach
    public void initTest() {
        mTimeattackQuestStageReward = createEntity(em);
    }

    @Test
    @Transactional
    public void createMTimeattackQuestStageReward() throws Exception {
        int databaseSizeBeforeCreate = mTimeattackQuestStageRewardRepository.findAll().size();

        // Create the MTimeattackQuestStageReward
        MTimeattackQuestStageRewardDTO mTimeattackQuestStageRewardDTO = mTimeattackQuestStageRewardMapper.toDto(mTimeattackQuestStageReward);
        restMTimeattackQuestStageRewardMockMvc.perform(post("/api/m-timeattack-quest-stage-rewards")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mTimeattackQuestStageRewardDTO)))
            .andExpect(status().isCreated());

        // Validate the MTimeattackQuestStageReward in the database
        List<MTimeattackQuestStageReward> mTimeattackQuestStageRewardList = mTimeattackQuestStageRewardRepository.findAll();
        assertThat(mTimeattackQuestStageRewardList).hasSize(databaseSizeBeforeCreate + 1);
        MTimeattackQuestStageReward testMTimeattackQuestStageReward = mTimeattackQuestStageRewardList.get(mTimeattackQuestStageRewardList.size() - 1);
        assertThat(testMTimeattackQuestStageReward.getStageId()).isEqualTo(DEFAULT_STAGE_ID);
        assertThat(testMTimeattackQuestStageReward.getExp()).isEqualTo(DEFAULT_EXP);
        assertThat(testMTimeattackQuestStageReward.getCoin()).isEqualTo(DEFAULT_COIN);
        assertThat(testMTimeattackQuestStageReward.getGuildPoint()).isEqualTo(DEFAULT_GUILD_POINT);
        assertThat(testMTimeattackQuestStageReward.getClearRewardGroupId()).isEqualTo(DEFAULT_CLEAR_REWARD_GROUP_ID);
        assertThat(testMTimeattackQuestStageReward.getClearRewardWeightId()).isEqualTo(DEFAULT_CLEAR_REWARD_WEIGHT_ID);
        assertThat(testMTimeattackQuestStageReward.getAchievementRewardGroupId()).isEqualTo(DEFAULT_ACHIEVEMENT_REWARD_GROUP_ID);
        assertThat(testMTimeattackQuestStageReward.getCoopGroupId()).isEqualTo(DEFAULT_COOP_GROUP_ID);
        assertThat(testMTimeattackQuestStageReward.getSpecialRewardGroupId()).isEqualTo(DEFAULT_SPECIAL_REWARD_GROUP_ID);
        assertThat(testMTimeattackQuestStageReward.getSpecialRewardAmount()).isEqualTo(DEFAULT_SPECIAL_REWARD_AMOUNT);
        assertThat(testMTimeattackQuestStageReward.getGoalRewardGroupId()).isEqualTo(DEFAULT_GOAL_REWARD_GROUP_ID);
    }

    @Test
    @Transactional
    public void createMTimeattackQuestStageRewardWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = mTimeattackQuestStageRewardRepository.findAll().size();

        // Create the MTimeattackQuestStageReward with an existing ID
        mTimeattackQuestStageReward.setId(1L);
        MTimeattackQuestStageRewardDTO mTimeattackQuestStageRewardDTO = mTimeattackQuestStageRewardMapper.toDto(mTimeattackQuestStageReward);

        // An entity with an existing ID cannot be created, so this API call must fail
        restMTimeattackQuestStageRewardMockMvc.perform(post("/api/m-timeattack-quest-stage-rewards")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mTimeattackQuestStageRewardDTO)))
            .andExpect(status().isBadRequest());

        // Validate the MTimeattackQuestStageReward in the database
        List<MTimeattackQuestStageReward> mTimeattackQuestStageRewardList = mTimeattackQuestStageRewardRepository.findAll();
        assertThat(mTimeattackQuestStageRewardList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkStageIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = mTimeattackQuestStageRewardRepository.findAll().size();
        // set the field null
        mTimeattackQuestStageReward.setStageId(null);

        // Create the MTimeattackQuestStageReward, which fails.
        MTimeattackQuestStageRewardDTO mTimeattackQuestStageRewardDTO = mTimeattackQuestStageRewardMapper.toDto(mTimeattackQuestStageReward);

        restMTimeattackQuestStageRewardMockMvc.perform(post("/api/m-timeattack-quest-stage-rewards")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mTimeattackQuestStageRewardDTO)))
            .andExpect(status().isBadRequest());

        List<MTimeattackQuestStageReward> mTimeattackQuestStageRewardList = mTimeattackQuestStageRewardRepository.findAll();
        assertThat(mTimeattackQuestStageRewardList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkExpIsRequired() throws Exception {
        int databaseSizeBeforeTest = mTimeattackQuestStageRewardRepository.findAll().size();
        // set the field null
        mTimeattackQuestStageReward.setExp(null);

        // Create the MTimeattackQuestStageReward, which fails.
        MTimeattackQuestStageRewardDTO mTimeattackQuestStageRewardDTO = mTimeattackQuestStageRewardMapper.toDto(mTimeattackQuestStageReward);

        restMTimeattackQuestStageRewardMockMvc.perform(post("/api/m-timeattack-quest-stage-rewards")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mTimeattackQuestStageRewardDTO)))
            .andExpect(status().isBadRequest());

        List<MTimeattackQuestStageReward> mTimeattackQuestStageRewardList = mTimeattackQuestStageRewardRepository.findAll();
        assertThat(mTimeattackQuestStageRewardList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkCoinIsRequired() throws Exception {
        int databaseSizeBeforeTest = mTimeattackQuestStageRewardRepository.findAll().size();
        // set the field null
        mTimeattackQuestStageReward.setCoin(null);

        // Create the MTimeattackQuestStageReward, which fails.
        MTimeattackQuestStageRewardDTO mTimeattackQuestStageRewardDTO = mTimeattackQuestStageRewardMapper.toDto(mTimeattackQuestStageReward);

        restMTimeattackQuestStageRewardMockMvc.perform(post("/api/m-timeattack-quest-stage-rewards")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mTimeattackQuestStageRewardDTO)))
            .andExpect(status().isBadRequest());

        List<MTimeattackQuestStageReward> mTimeattackQuestStageRewardList = mTimeattackQuestStageRewardRepository.findAll();
        assertThat(mTimeattackQuestStageRewardList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkGuildPointIsRequired() throws Exception {
        int databaseSizeBeforeTest = mTimeattackQuestStageRewardRepository.findAll().size();
        // set the field null
        mTimeattackQuestStageReward.setGuildPoint(null);

        // Create the MTimeattackQuestStageReward, which fails.
        MTimeattackQuestStageRewardDTO mTimeattackQuestStageRewardDTO = mTimeattackQuestStageRewardMapper.toDto(mTimeattackQuestStageReward);

        restMTimeattackQuestStageRewardMockMvc.perform(post("/api/m-timeattack-quest-stage-rewards")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mTimeattackQuestStageRewardDTO)))
            .andExpect(status().isBadRequest());

        List<MTimeattackQuestStageReward> mTimeattackQuestStageRewardList = mTimeattackQuestStageRewardRepository.findAll();
        assertThat(mTimeattackQuestStageRewardList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkClearRewardGroupIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = mTimeattackQuestStageRewardRepository.findAll().size();
        // set the field null
        mTimeattackQuestStageReward.setClearRewardGroupId(null);

        // Create the MTimeattackQuestStageReward, which fails.
        MTimeattackQuestStageRewardDTO mTimeattackQuestStageRewardDTO = mTimeattackQuestStageRewardMapper.toDto(mTimeattackQuestStageReward);

        restMTimeattackQuestStageRewardMockMvc.perform(post("/api/m-timeattack-quest-stage-rewards")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mTimeattackQuestStageRewardDTO)))
            .andExpect(status().isBadRequest());

        List<MTimeattackQuestStageReward> mTimeattackQuestStageRewardList = mTimeattackQuestStageRewardRepository.findAll();
        assertThat(mTimeattackQuestStageRewardList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkClearRewardWeightIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = mTimeattackQuestStageRewardRepository.findAll().size();
        // set the field null
        mTimeattackQuestStageReward.setClearRewardWeightId(null);

        // Create the MTimeattackQuestStageReward, which fails.
        MTimeattackQuestStageRewardDTO mTimeattackQuestStageRewardDTO = mTimeattackQuestStageRewardMapper.toDto(mTimeattackQuestStageReward);

        restMTimeattackQuestStageRewardMockMvc.perform(post("/api/m-timeattack-quest-stage-rewards")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mTimeattackQuestStageRewardDTO)))
            .andExpect(status().isBadRequest());

        List<MTimeattackQuestStageReward> mTimeattackQuestStageRewardList = mTimeattackQuestStageRewardRepository.findAll();
        assertThat(mTimeattackQuestStageRewardList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkAchievementRewardGroupIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = mTimeattackQuestStageRewardRepository.findAll().size();
        // set the field null
        mTimeattackQuestStageReward.setAchievementRewardGroupId(null);

        // Create the MTimeattackQuestStageReward, which fails.
        MTimeattackQuestStageRewardDTO mTimeattackQuestStageRewardDTO = mTimeattackQuestStageRewardMapper.toDto(mTimeattackQuestStageReward);

        restMTimeattackQuestStageRewardMockMvc.perform(post("/api/m-timeattack-quest-stage-rewards")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mTimeattackQuestStageRewardDTO)))
            .andExpect(status().isBadRequest());

        List<MTimeattackQuestStageReward> mTimeattackQuestStageRewardList = mTimeattackQuestStageRewardRepository.findAll();
        assertThat(mTimeattackQuestStageRewardList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkCoopGroupIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = mTimeattackQuestStageRewardRepository.findAll().size();
        // set the field null
        mTimeattackQuestStageReward.setCoopGroupId(null);

        // Create the MTimeattackQuestStageReward, which fails.
        MTimeattackQuestStageRewardDTO mTimeattackQuestStageRewardDTO = mTimeattackQuestStageRewardMapper.toDto(mTimeattackQuestStageReward);

        restMTimeattackQuestStageRewardMockMvc.perform(post("/api/m-timeattack-quest-stage-rewards")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mTimeattackQuestStageRewardDTO)))
            .andExpect(status().isBadRequest());

        List<MTimeattackQuestStageReward> mTimeattackQuestStageRewardList = mTimeattackQuestStageRewardRepository.findAll();
        assertThat(mTimeattackQuestStageRewardList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkSpecialRewardAmountIsRequired() throws Exception {
        int databaseSizeBeforeTest = mTimeattackQuestStageRewardRepository.findAll().size();
        // set the field null
        mTimeattackQuestStageReward.setSpecialRewardAmount(null);

        // Create the MTimeattackQuestStageReward, which fails.
        MTimeattackQuestStageRewardDTO mTimeattackQuestStageRewardDTO = mTimeattackQuestStageRewardMapper.toDto(mTimeattackQuestStageReward);

        restMTimeattackQuestStageRewardMockMvc.perform(post("/api/m-timeattack-quest-stage-rewards")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mTimeattackQuestStageRewardDTO)))
            .andExpect(status().isBadRequest());

        List<MTimeattackQuestStageReward> mTimeattackQuestStageRewardList = mTimeattackQuestStageRewardRepository.findAll();
        assertThat(mTimeattackQuestStageRewardList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllMTimeattackQuestStageRewards() throws Exception {
        // Initialize the database
        mTimeattackQuestStageRewardRepository.saveAndFlush(mTimeattackQuestStageReward);

        // Get all the mTimeattackQuestStageRewardList
        restMTimeattackQuestStageRewardMockMvc.perform(get("/api/m-timeattack-quest-stage-rewards?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(mTimeattackQuestStageReward.getId().intValue())))
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
    public void getMTimeattackQuestStageReward() throws Exception {
        // Initialize the database
        mTimeattackQuestStageRewardRepository.saveAndFlush(mTimeattackQuestStageReward);

        // Get the mTimeattackQuestStageReward
        restMTimeattackQuestStageRewardMockMvc.perform(get("/api/m-timeattack-quest-stage-rewards/{id}", mTimeattackQuestStageReward.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(mTimeattackQuestStageReward.getId().intValue()))
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
    public void getAllMTimeattackQuestStageRewardsByStageIdIsEqualToSomething() throws Exception {
        // Initialize the database
        mTimeattackQuestStageRewardRepository.saveAndFlush(mTimeattackQuestStageReward);

        // Get all the mTimeattackQuestStageRewardList where stageId equals to DEFAULT_STAGE_ID
        defaultMTimeattackQuestStageRewardShouldBeFound("stageId.equals=" + DEFAULT_STAGE_ID);

        // Get all the mTimeattackQuestStageRewardList where stageId equals to UPDATED_STAGE_ID
        defaultMTimeattackQuestStageRewardShouldNotBeFound("stageId.equals=" + UPDATED_STAGE_ID);
    }

    @Test
    @Transactional
    public void getAllMTimeattackQuestStageRewardsByStageIdIsInShouldWork() throws Exception {
        // Initialize the database
        mTimeattackQuestStageRewardRepository.saveAndFlush(mTimeattackQuestStageReward);

        // Get all the mTimeattackQuestStageRewardList where stageId in DEFAULT_STAGE_ID or UPDATED_STAGE_ID
        defaultMTimeattackQuestStageRewardShouldBeFound("stageId.in=" + DEFAULT_STAGE_ID + "," + UPDATED_STAGE_ID);

        // Get all the mTimeattackQuestStageRewardList where stageId equals to UPDATED_STAGE_ID
        defaultMTimeattackQuestStageRewardShouldNotBeFound("stageId.in=" + UPDATED_STAGE_ID);
    }

    @Test
    @Transactional
    public void getAllMTimeattackQuestStageRewardsByStageIdIsNullOrNotNull() throws Exception {
        // Initialize the database
        mTimeattackQuestStageRewardRepository.saveAndFlush(mTimeattackQuestStageReward);

        // Get all the mTimeattackQuestStageRewardList where stageId is not null
        defaultMTimeattackQuestStageRewardShouldBeFound("stageId.specified=true");

        // Get all the mTimeattackQuestStageRewardList where stageId is null
        defaultMTimeattackQuestStageRewardShouldNotBeFound("stageId.specified=false");
    }

    @Test
    @Transactional
    public void getAllMTimeattackQuestStageRewardsByStageIdIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mTimeattackQuestStageRewardRepository.saveAndFlush(mTimeattackQuestStageReward);

        // Get all the mTimeattackQuestStageRewardList where stageId greater than or equals to DEFAULT_STAGE_ID
        defaultMTimeattackQuestStageRewardShouldBeFound("stageId.greaterOrEqualThan=" + DEFAULT_STAGE_ID);

        // Get all the mTimeattackQuestStageRewardList where stageId greater than or equals to UPDATED_STAGE_ID
        defaultMTimeattackQuestStageRewardShouldNotBeFound("stageId.greaterOrEqualThan=" + UPDATED_STAGE_ID);
    }

    @Test
    @Transactional
    public void getAllMTimeattackQuestStageRewardsByStageIdIsLessThanSomething() throws Exception {
        // Initialize the database
        mTimeattackQuestStageRewardRepository.saveAndFlush(mTimeattackQuestStageReward);

        // Get all the mTimeattackQuestStageRewardList where stageId less than or equals to DEFAULT_STAGE_ID
        defaultMTimeattackQuestStageRewardShouldNotBeFound("stageId.lessThan=" + DEFAULT_STAGE_ID);

        // Get all the mTimeattackQuestStageRewardList where stageId less than or equals to UPDATED_STAGE_ID
        defaultMTimeattackQuestStageRewardShouldBeFound("stageId.lessThan=" + UPDATED_STAGE_ID);
    }


    @Test
    @Transactional
    public void getAllMTimeattackQuestStageRewardsByExpIsEqualToSomething() throws Exception {
        // Initialize the database
        mTimeattackQuestStageRewardRepository.saveAndFlush(mTimeattackQuestStageReward);

        // Get all the mTimeattackQuestStageRewardList where exp equals to DEFAULT_EXP
        defaultMTimeattackQuestStageRewardShouldBeFound("exp.equals=" + DEFAULT_EXP);

        // Get all the mTimeattackQuestStageRewardList where exp equals to UPDATED_EXP
        defaultMTimeattackQuestStageRewardShouldNotBeFound("exp.equals=" + UPDATED_EXP);
    }

    @Test
    @Transactional
    public void getAllMTimeattackQuestStageRewardsByExpIsInShouldWork() throws Exception {
        // Initialize the database
        mTimeattackQuestStageRewardRepository.saveAndFlush(mTimeattackQuestStageReward);

        // Get all the mTimeattackQuestStageRewardList where exp in DEFAULT_EXP or UPDATED_EXP
        defaultMTimeattackQuestStageRewardShouldBeFound("exp.in=" + DEFAULT_EXP + "," + UPDATED_EXP);

        // Get all the mTimeattackQuestStageRewardList where exp equals to UPDATED_EXP
        defaultMTimeattackQuestStageRewardShouldNotBeFound("exp.in=" + UPDATED_EXP);
    }

    @Test
    @Transactional
    public void getAllMTimeattackQuestStageRewardsByExpIsNullOrNotNull() throws Exception {
        // Initialize the database
        mTimeattackQuestStageRewardRepository.saveAndFlush(mTimeattackQuestStageReward);

        // Get all the mTimeattackQuestStageRewardList where exp is not null
        defaultMTimeattackQuestStageRewardShouldBeFound("exp.specified=true");

        // Get all the mTimeattackQuestStageRewardList where exp is null
        defaultMTimeattackQuestStageRewardShouldNotBeFound("exp.specified=false");
    }

    @Test
    @Transactional
    public void getAllMTimeattackQuestStageRewardsByExpIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mTimeattackQuestStageRewardRepository.saveAndFlush(mTimeattackQuestStageReward);

        // Get all the mTimeattackQuestStageRewardList where exp greater than or equals to DEFAULT_EXP
        defaultMTimeattackQuestStageRewardShouldBeFound("exp.greaterOrEqualThan=" + DEFAULT_EXP);

        // Get all the mTimeattackQuestStageRewardList where exp greater than or equals to UPDATED_EXP
        defaultMTimeattackQuestStageRewardShouldNotBeFound("exp.greaterOrEqualThan=" + UPDATED_EXP);
    }

    @Test
    @Transactional
    public void getAllMTimeattackQuestStageRewardsByExpIsLessThanSomething() throws Exception {
        // Initialize the database
        mTimeattackQuestStageRewardRepository.saveAndFlush(mTimeattackQuestStageReward);

        // Get all the mTimeattackQuestStageRewardList where exp less than or equals to DEFAULT_EXP
        defaultMTimeattackQuestStageRewardShouldNotBeFound("exp.lessThan=" + DEFAULT_EXP);

        // Get all the mTimeattackQuestStageRewardList where exp less than or equals to UPDATED_EXP
        defaultMTimeattackQuestStageRewardShouldBeFound("exp.lessThan=" + UPDATED_EXP);
    }


    @Test
    @Transactional
    public void getAllMTimeattackQuestStageRewardsByCoinIsEqualToSomething() throws Exception {
        // Initialize the database
        mTimeattackQuestStageRewardRepository.saveAndFlush(mTimeattackQuestStageReward);

        // Get all the mTimeattackQuestStageRewardList where coin equals to DEFAULT_COIN
        defaultMTimeattackQuestStageRewardShouldBeFound("coin.equals=" + DEFAULT_COIN);

        // Get all the mTimeattackQuestStageRewardList where coin equals to UPDATED_COIN
        defaultMTimeattackQuestStageRewardShouldNotBeFound("coin.equals=" + UPDATED_COIN);
    }

    @Test
    @Transactional
    public void getAllMTimeattackQuestStageRewardsByCoinIsInShouldWork() throws Exception {
        // Initialize the database
        mTimeattackQuestStageRewardRepository.saveAndFlush(mTimeattackQuestStageReward);

        // Get all the mTimeattackQuestStageRewardList where coin in DEFAULT_COIN or UPDATED_COIN
        defaultMTimeattackQuestStageRewardShouldBeFound("coin.in=" + DEFAULT_COIN + "," + UPDATED_COIN);

        // Get all the mTimeattackQuestStageRewardList where coin equals to UPDATED_COIN
        defaultMTimeattackQuestStageRewardShouldNotBeFound("coin.in=" + UPDATED_COIN);
    }

    @Test
    @Transactional
    public void getAllMTimeattackQuestStageRewardsByCoinIsNullOrNotNull() throws Exception {
        // Initialize the database
        mTimeattackQuestStageRewardRepository.saveAndFlush(mTimeattackQuestStageReward);

        // Get all the mTimeattackQuestStageRewardList where coin is not null
        defaultMTimeattackQuestStageRewardShouldBeFound("coin.specified=true");

        // Get all the mTimeattackQuestStageRewardList where coin is null
        defaultMTimeattackQuestStageRewardShouldNotBeFound("coin.specified=false");
    }

    @Test
    @Transactional
    public void getAllMTimeattackQuestStageRewardsByCoinIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mTimeattackQuestStageRewardRepository.saveAndFlush(mTimeattackQuestStageReward);

        // Get all the mTimeattackQuestStageRewardList where coin greater than or equals to DEFAULT_COIN
        defaultMTimeattackQuestStageRewardShouldBeFound("coin.greaterOrEqualThan=" + DEFAULT_COIN);

        // Get all the mTimeattackQuestStageRewardList where coin greater than or equals to UPDATED_COIN
        defaultMTimeattackQuestStageRewardShouldNotBeFound("coin.greaterOrEqualThan=" + UPDATED_COIN);
    }

    @Test
    @Transactional
    public void getAllMTimeattackQuestStageRewardsByCoinIsLessThanSomething() throws Exception {
        // Initialize the database
        mTimeattackQuestStageRewardRepository.saveAndFlush(mTimeattackQuestStageReward);

        // Get all the mTimeattackQuestStageRewardList where coin less than or equals to DEFAULT_COIN
        defaultMTimeattackQuestStageRewardShouldNotBeFound("coin.lessThan=" + DEFAULT_COIN);

        // Get all the mTimeattackQuestStageRewardList where coin less than or equals to UPDATED_COIN
        defaultMTimeattackQuestStageRewardShouldBeFound("coin.lessThan=" + UPDATED_COIN);
    }


    @Test
    @Transactional
    public void getAllMTimeattackQuestStageRewardsByGuildPointIsEqualToSomething() throws Exception {
        // Initialize the database
        mTimeattackQuestStageRewardRepository.saveAndFlush(mTimeattackQuestStageReward);

        // Get all the mTimeattackQuestStageRewardList where guildPoint equals to DEFAULT_GUILD_POINT
        defaultMTimeattackQuestStageRewardShouldBeFound("guildPoint.equals=" + DEFAULT_GUILD_POINT);

        // Get all the mTimeattackQuestStageRewardList where guildPoint equals to UPDATED_GUILD_POINT
        defaultMTimeattackQuestStageRewardShouldNotBeFound("guildPoint.equals=" + UPDATED_GUILD_POINT);
    }

    @Test
    @Transactional
    public void getAllMTimeattackQuestStageRewardsByGuildPointIsInShouldWork() throws Exception {
        // Initialize the database
        mTimeattackQuestStageRewardRepository.saveAndFlush(mTimeattackQuestStageReward);

        // Get all the mTimeattackQuestStageRewardList where guildPoint in DEFAULT_GUILD_POINT or UPDATED_GUILD_POINT
        defaultMTimeattackQuestStageRewardShouldBeFound("guildPoint.in=" + DEFAULT_GUILD_POINT + "," + UPDATED_GUILD_POINT);

        // Get all the mTimeattackQuestStageRewardList where guildPoint equals to UPDATED_GUILD_POINT
        defaultMTimeattackQuestStageRewardShouldNotBeFound("guildPoint.in=" + UPDATED_GUILD_POINT);
    }

    @Test
    @Transactional
    public void getAllMTimeattackQuestStageRewardsByGuildPointIsNullOrNotNull() throws Exception {
        // Initialize the database
        mTimeattackQuestStageRewardRepository.saveAndFlush(mTimeattackQuestStageReward);

        // Get all the mTimeattackQuestStageRewardList where guildPoint is not null
        defaultMTimeattackQuestStageRewardShouldBeFound("guildPoint.specified=true");

        // Get all the mTimeattackQuestStageRewardList where guildPoint is null
        defaultMTimeattackQuestStageRewardShouldNotBeFound("guildPoint.specified=false");
    }

    @Test
    @Transactional
    public void getAllMTimeattackQuestStageRewardsByGuildPointIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mTimeattackQuestStageRewardRepository.saveAndFlush(mTimeattackQuestStageReward);

        // Get all the mTimeattackQuestStageRewardList where guildPoint greater than or equals to DEFAULT_GUILD_POINT
        defaultMTimeattackQuestStageRewardShouldBeFound("guildPoint.greaterOrEqualThan=" + DEFAULT_GUILD_POINT);

        // Get all the mTimeattackQuestStageRewardList where guildPoint greater than or equals to UPDATED_GUILD_POINT
        defaultMTimeattackQuestStageRewardShouldNotBeFound("guildPoint.greaterOrEqualThan=" + UPDATED_GUILD_POINT);
    }

    @Test
    @Transactional
    public void getAllMTimeattackQuestStageRewardsByGuildPointIsLessThanSomething() throws Exception {
        // Initialize the database
        mTimeattackQuestStageRewardRepository.saveAndFlush(mTimeattackQuestStageReward);

        // Get all the mTimeattackQuestStageRewardList where guildPoint less than or equals to DEFAULT_GUILD_POINT
        defaultMTimeattackQuestStageRewardShouldNotBeFound("guildPoint.lessThan=" + DEFAULT_GUILD_POINT);

        // Get all the mTimeattackQuestStageRewardList where guildPoint less than or equals to UPDATED_GUILD_POINT
        defaultMTimeattackQuestStageRewardShouldBeFound("guildPoint.lessThan=" + UPDATED_GUILD_POINT);
    }


    @Test
    @Transactional
    public void getAllMTimeattackQuestStageRewardsByClearRewardGroupIdIsEqualToSomething() throws Exception {
        // Initialize the database
        mTimeattackQuestStageRewardRepository.saveAndFlush(mTimeattackQuestStageReward);

        // Get all the mTimeattackQuestStageRewardList where clearRewardGroupId equals to DEFAULT_CLEAR_REWARD_GROUP_ID
        defaultMTimeattackQuestStageRewardShouldBeFound("clearRewardGroupId.equals=" + DEFAULT_CLEAR_REWARD_GROUP_ID);

        // Get all the mTimeattackQuestStageRewardList where clearRewardGroupId equals to UPDATED_CLEAR_REWARD_GROUP_ID
        defaultMTimeattackQuestStageRewardShouldNotBeFound("clearRewardGroupId.equals=" + UPDATED_CLEAR_REWARD_GROUP_ID);
    }

    @Test
    @Transactional
    public void getAllMTimeattackQuestStageRewardsByClearRewardGroupIdIsInShouldWork() throws Exception {
        // Initialize the database
        mTimeattackQuestStageRewardRepository.saveAndFlush(mTimeattackQuestStageReward);

        // Get all the mTimeattackQuestStageRewardList where clearRewardGroupId in DEFAULT_CLEAR_REWARD_GROUP_ID or UPDATED_CLEAR_REWARD_GROUP_ID
        defaultMTimeattackQuestStageRewardShouldBeFound("clearRewardGroupId.in=" + DEFAULT_CLEAR_REWARD_GROUP_ID + "," + UPDATED_CLEAR_REWARD_GROUP_ID);

        // Get all the mTimeattackQuestStageRewardList where clearRewardGroupId equals to UPDATED_CLEAR_REWARD_GROUP_ID
        defaultMTimeattackQuestStageRewardShouldNotBeFound("clearRewardGroupId.in=" + UPDATED_CLEAR_REWARD_GROUP_ID);
    }

    @Test
    @Transactional
    public void getAllMTimeattackQuestStageRewardsByClearRewardGroupIdIsNullOrNotNull() throws Exception {
        // Initialize the database
        mTimeattackQuestStageRewardRepository.saveAndFlush(mTimeattackQuestStageReward);

        // Get all the mTimeattackQuestStageRewardList where clearRewardGroupId is not null
        defaultMTimeattackQuestStageRewardShouldBeFound("clearRewardGroupId.specified=true");

        // Get all the mTimeattackQuestStageRewardList where clearRewardGroupId is null
        defaultMTimeattackQuestStageRewardShouldNotBeFound("clearRewardGroupId.specified=false");
    }

    @Test
    @Transactional
    public void getAllMTimeattackQuestStageRewardsByClearRewardGroupIdIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mTimeattackQuestStageRewardRepository.saveAndFlush(mTimeattackQuestStageReward);

        // Get all the mTimeattackQuestStageRewardList where clearRewardGroupId greater than or equals to DEFAULT_CLEAR_REWARD_GROUP_ID
        defaultMTimeattackQuestStageRewardShouldBeFound("clearRewardGroupId.greaterOrEqualThan=" + DEFAULT_CLEAR_REWARD_GROUP_ID);

        // Get all the mTimeattackQuestStageRewardList where clearRewardGroupId greater than or equals to UPDATED_CLEAR_REWARD_GROUP_ID
        defaultMTimeattackQuestStageRewardShouldNotBeFound("clearRewardGroupId.greaterOrEqualThan=" + UPDATED_CLEAR_REWARD_GROUP_ID);
    }

    @Test
    @Transactional
    public void getAllMTimeattackQuestStageRewardsByClearRewardGroupIdIsLessThanSomething() throws Exception {
        // Initialize the database
        mTimeattackQuestStageRewardRepository.saveAndFlush(mTimeattackQuestStageReward);

        // Get all the mTimeattackQuestStageRewardList where clearRewardGroupId less than or equals to DEFAULT_CLEAR_REWARD_GROUP_ID
        defaultMTimeattackQuestStageRewardShouldNotBeFound("clearRewardGroupId.lessThan=" + DEFAULT_CLEAR_REWARD_GROUP_ID);

        // Get all the mTimeattackQuestStageRewardList where clearRewardGroupId less than or equals to UPDATED_CLEAR_REWARD_GROUP_ID
        defaultMTimeattackQuestStageRewardShouldBeFound("clearRewardGroupId.lessThan=" + UPDATED_CLEAR_REWARD_GROUP_ID);
    }


    @Test
    @Transactional
    public void getAllMTimeattackQuestStageRewardsByClearRewardWeightIdIsEqualToSomething() throws Exception {
        // Initialize the database
        mTimeattackQuestStageRewardRepository.saveAndFlush(mTimeattackQuestStageReward);

        // Get all the mTimeattackQuestStageRewardList where clearRewardWeightId equals to DEFAULT_CLEAR_REWARD_WEIGHT_ID
        defaultMTimeattackQuestStageRewardShouldBeFound("clearRewardWeightId.equals=" + DEFAULT_CLEAR_REWARD_WEIGHT_ID);

        // Get all the mTimeattackQuestStageRewardList where clearRewardWeightId equals to UPDATED_CLEAR_REWARD_WEIGHT_ID
        defaultMTimeattackQuestStageRewardShouldNotBeFound("clearRewardWeightId.equals=" + UPDATED_CLEAR_REWARD_WEIGHT_ID);
    }

    @Test
    @Transactional
    public void getAllMTimeattackQuestStageRewardsByClearRewardWeightIdIsInShouldWork() throws Exception {
        // Initialize the database
        mTimeattackQuestStageRewardRepository.saveAndFlush(mTimeattackQuestStageReward);

        // Get all the mTimeattackQuestStageRewardList where clearRewardWeightId in DEFAULT_CLEAR_REWARD_WEIGHT_ID or UPDATED_CLEAR_REWARD_WEIGHT_ID
        defaultMTimeattackQuestStageRewardShouldBeFound("clearRewardWeightId.in=" + DEFAULT_CLEAR_REWARD_WEIGHT_ID + "," + UPDATED_CLEAR_REWARD_WEIGHT_ID);

        // Get all the mTimeattackQuestStageRewardList where clearRewardWeightId equals to UPDATED_CLEAR_REWARD_WEIGHT_ID
        defaultMTimeattackQuestStageRewardShouldNotBeFound("clearRewardWeightId.in=" + UPDATED_CLEAR_REWARD_WEIGHT_ID);
    }

    @Test
    @Transactional
    public void getAllMTimeattackQuestStageRewardsByClearRewardWeightIdIsNullOrNotNull() throws Exception {
        // Initialize the database
        mTimeattackQuestStageRewardRepository.saveAndFlush(mTimeattackQuestStageReward);

        // Get all the mTimeattackQuestStageRewardList where clearRewardWeightId is not null
        defaultMTimeattackQuestStageRewardShouldBeFound("clearRewardWeightId.specified=true");

        // Get all the mTimeattackQuestStageRewardList where clearRewardWeightId is null
        defaultMTimeattackQuestStageRewardShouldNotBeFound("clearRewardWeightId.specified=false");
    }

    @Test
    @Transactional
    public void getAllMTimeattackQuestStageRewardsByClearRewardWeightIdIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mTimeattackQuestStageRewardRepository.saveAndFlush(mTimeattackQuestStageReward);

        // Get all the mTimeattackQuestStageRewardList where clearRewardWeightId greater than or equals to DEFAULT_CLEAR_REWARD_WEIGHT_ID
        defaultMTimeattackQuestStageRewardShouldBeFound("clearRewardWeightId.greaterOrEqualThan=" + DEFAULT_CLEAR_REWARD_WEIGHT_ID);

        // Get all the mTimeattackQuestStageRewardList where clearRewardWeightId greater than or equals to UPDATED_CLEAR_REWARD_WEIGHT_ID
        defaultMTimeattackQuestStageRewardShouldNotBeFound("clearRewardWeightId.greaterOrEqualThan=" + UPDATED_CLEAR_REWARD_WEIGHT_ID);
    }

    @Test
    @Transactional
    public void getAllMTimeattackQuestStageRewardsByClearRewardWeightIdIsLessThanSomething() throws Exception {
        // Initialize the database
        mTimeattackQuestStageRewardRepository.saveAndFlush(mTimeattackQuestStageReward);

        // Get all the mTimeattackQuestStageRewardList where clearRewardWeightId less than or equals to DEFAULT_CLEAR_REWARD_WEIGHT_ID
        defaultMTimeattackQuestStageRewardShouldNotBeFound("clearRewardWeightId.lessThan=" + DEFAULT_CLEAR_REWARD_WEIGHT_ID);

        // Get all the mTimeattackQuestStageRewardList where clearRewardWeightId less than or equals to UPDATED_CLEAR_REWARD_WEIGHT_ID
        defaultMTimeattackQuestStageRewardShouldBeFound("clearRewardWeightId.lessThan=" + UPDATED_CLEAR_REWARD_WEIGHT_ID);
    }


    @Test
    @Transactional
    public void getAllMTimeattackQuestStageRewardsByAchievementRewardGroupIdIsEqualToSomething() throws Exception {
        // Initialize the database
        mTimeattackQuestStageRewardRepository.saveAndFlush(mTimeattackQuestStageReward);

        // Get all the mTimeattackQuestStageRewardList where achievementRewardGroupId equals to DEFAULT_ACHIEVEMENT_REWARD_GROUP_ID
        defaultMTimeattackQuestStageRewardShouldBeFound("achievementRewardGroupId.equals=" + DEFAULT_ACHIEVEMENT_REWARD_GROUP_ID);

        // Get all the mTimeattackQuestStageRewardList where achievementRewardGroupId equals to UPDATED_ACHIEVEMENT_REWARD_GROUP_ID
        defaultMTimeattackQuestStageRewardShouldNotBeFound("achievementRewardGroupId.equals=" + UPDATED_ACHIEVEMENT_REWARD_GROUP_ID);
    }

    @Test
    @Transactional
    public void getAllMTimeattackQuestStageRewardsByAchievementRewardGroupIdIsInShouldWork() throws Exception {
        // Initialize the database
        mTimeattackQuestStageRewardRepository.saveAndFlush(mTimeattackQuestStageReward);

        // Get all the mTimeattackQuestStageRewardList where achievementRewardGroupId in DEFAULT_ACHIEVEMENT_REWARD_GROUP_ID or UPDATED_ACHIEVEMENT_REWARD_GROUP_ID
        defaultMTimeattackQuestStageRewardShouldBeFound("achievementRewardGroupId.in=" + DEFAULT_ACHIEVEMENT_REWARD_GROUP_ID + "," + UPDATED_ACHIEVEMENT_REWARD_GROUP_ID);

        // Get all the mTimeattackQuestStageRewardList where achievementRewardGroupId equals to UPDATED_ACHIEVEMENT_REWARD_GROUP_ID
        defaultMTimeattackQuestStageRewardShouldNotBeFound("achievementRewardGroupId.in=" + UPDATED_ACHIEVEMENT_REWARD_GROUP_ID);
    }

    @Test
    @Transactional
    public void getAllMTimeattackQuestStageRewardsByAchievementRewardGroupIdIsNullOrNotNull() throws Exception {
        // Initialize the database
        mTimeattackQuestStageRewardRepository.saveAndFlush(mTimeattackQuestStageReward);

        // Get all the mTimeattackQuestStageRewardList where achievementRewardGroupId is not null
        defaultMTimeattackQuestStageRewardShouldBeFound("achievementRewardGroupId.specified=true");

        // Get all the mTimeattackQuestStageRewardList where achievementRewardGroupId is null
        defaultMTimeattackQuestStageRewardShouldNotBeFound("achievementRewardGroupId.specified=false");
    }

    @Test
    @Transactional
    public void getAllMTimeattackQuestStageRewardsByAchievementRewardGroupIdIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mTimeattackQuestStageRewardRepository.saveAndFlush(mTimeattackQuestStageReward);

        // Get all the mTimeattackQuestStageRewardList where achievementRewardGroupId greater than or equals to DEFAULT_ACHIEVEMENT_REWARD_GROUP_ID
        defaultMTimeattackQuestStageRewardShouldBeFound("achievementRewardGroupId.greaterOrEqualThan=" + DEFAULT_ACHIEVEMENT_REWARD_GROUP_ID);

        // Get all the mTimeattackQuestStageRewardList where achievementRewardGroupId greater than or equals to UPDATED_ACHIEVEMENT_REWARD_GROUP_ID
        defaultMTimeattackQuestStageRewardShouldNotBeFound("achievementRewardGroupId.greaterOrEqualThan=" + UPDATED_ACHIEVEMENT_REWARD_GROUP_ID);
    }

    @Test
    @Transactional
    public void getAllMTimeattackQuestStageRewardsByAchievementRewardGroupIdIsLessThanSomething() throws Exception {
        // Initialize the database
        mTimeattackQuestStageRewardRepository.saveAndFlush(mTimeattackQuestStageReward);

        // Get all the mTimeattackQuestStageRewardList where achievementRewardGroupId less than or equals to DEFAULT_ACHIEVEMENT_REWARD_GROUP_ID
        defaultMTimeattackQuestStageRewardShouldNotBeFound("achievementRewardGroupId.lessThan=" + DEFAULT_ACHIEVEMENT_REWARD_GROUP_ID);

        // Get all the mTimeattackQuestStageRewardList where achievementRewardGroupId less than or equals to UPDATED_ACHIEVEMENT_REWARD_GROUP_ID
        defaultMTimeattackQuestStageRewardShouldBeFound("achievementRewardGroupId.lessThan=" + UPDATED_ACHIEVEMENT_REWARD_GROUP_ID);
    }


    @Test
    @Transactional
    public void getAllMTimeattackQuestStageRewardsByCoopGroupIdIsEqualToSomething() throws Exception {
        // Initialize the database
        mTimeattackQuestStageRewardRepository.saveAndFlush(mTimeattackQuestStageReward);

        // Get all the mTimeattackQuestStageRewardList where coopGroupId equals to DEFAULT_COOP_GROUP_ID
        defaultMTimeattackQuestStageRewardShouldBeFound("coopGroupId.equals=" + DEFAULT_COOP_GROUP_ID);

        // Get all the mTimeattackQuestStageRewardList where coopGroupId equals to UPDATED_COOP_GROUP_ID
        defaultMTimeattackQuestStageRewardShouldNotBeFound("coopGroupId.equals=" + UPDATED_COOP_GROUP_ID);
    }

    @Test
    @Transactional
    public void getAllMTimeattackQuestStageRewardsByCoopGroupIdIsInShouldWork() throws Exception {
        // Initialize the database
        mTimeattackQuestStageRewardRepository.saveAndFlush(mTimeattackQuestStageReward);

        // Get all the mTimeattackQuestStageRewardList where coopGroupId in DEFAULT_COOP_GROUP_ID or UPDATED_COOP_GROUP_ID
        defaultMTimeattackQuestStageRewardShouldBeFound("coopGroupId.in=" + DEFAULT_COOP_GROUP_ID + "," + UPDATED_COOP_GROUP_ID);

        // Get all the mTimeattackQuestStageRewardList where coopGroupId equals to UPDATED_COOP_GROUP_ID
        defaultMTimeattackQuestStageRewardShouldNotBeFound("coopGroupId.in=" + UPDATED_COOP_GROUP_ID);
    }

    @Test
    @Transactional
    public void getAllMTimeattackQuestStageRewardsByCoopGroupIdIsNullOrNotNull() throws Exception {
        // Initialize the database
        mTimeattackQuestStageRewardRepository.saveAndFlush(mTimeattackQuestStageReward);

        // Get all the mTimeattackQuestStageRewardList where coopGroupId is not null
        defaultMTimeattackQuestStageRewardShouldBeFound("coopGroupId.specified=true");

        // Get all the mTimeattackQuestStageRewardList where coopGroupId is null
        defaultMTimeattackQuestStageRewardShouldNotBeFound("coopGroupId.specified=false");
    }

    @Test
    @Transactional
    public void getAllMTimeattackQuestStageRewardsByCoopGroupIdIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mTimeattackQuestStageRewardRepository.saveAndFlush(mTimeattackQuestStageReward);

        // Get all the mTimeattackQuestStageRewardList where coopGroupId greater than or equals to DEFAULT_COOP_GROUP_ID
        defaultMTimeattackQuestStageRewardShouldBeFound("coopGroupId.greaterOrEqualThan=" + DEFAULT_COOP_GROUP_ID);

        // Get all the mTimeattackQuestStageRewardList where coopGroupId greater than or equals to UPDATED_COOP_GROUP_ID
        defaultMTimeattackQuestStageRewardShouldNotBeFound("coopGroupId.greaterOrEqualThan=" + UPDATED_COOP_GROUP_ID);
    }

    @Test
    @Transactional
    public void getAllMTimeattackQuestStageRewardsByCoopGroupIdIsLessThanSomething() throws Exception {
        // Initialize the database
        mTimeattackQuestStageRewardRepository.saveAndFlush(mTimeattackQuestStageReward);

        // Get all the mTimeattackQuestStageRewardList where coopGroupId less than or equals to DEFAULT_COOP_GROUP_ID
        defaultMTimeattackQuestStageRewardShouldNotBeFound("coopGroupId.lessThan=" + DEFAULT_COOP_GROUP_ID);

        // Get all the mTimeattackQuestStageRewardList where coopGroupId less than or equals to UPDATED_COOP_GROUP_ID
        defaultMTimeattackQuestStageRewardShouldBeFound("coopGroupId.lessThan=" + UPDATED_COOP_GROUP_ID);
    }


    @Test
    @Transactional
    public void getAllMTimeattackQuestStageRewardsBySpecialRewardGroupIdIsEqualToSomething() throws Exception {
        // Initialize the database
        mTimeattackQuestStageRewardRepository.saveAndFlush(mTimeattackQuestStageReward);

        // Get all the mTimeattackQuestStageRewardList where specialRewardGroupId equals to DEFAULT_SPECIAL_REWARD_GROUP_ID
        defaultMTimeattackQuestStageRewardShouldBeFound("specialRewardGroupId.equals=" + DEFAULT_SPECIAL_REWARD_GROUP_ID);

        // Get all the mTimeattackQuestStageRewardList where specialRewardGroupId equals to UPDATED_SPECIAL_REWARD_GROUP_ID
        defaultMTimeattackQuestStageRewardShouldNotBeFound("specialRewardGroupId.equals=" + UPDATED_SPECIAL_REWARD_GROUP_ID);
    }

    @Test
    @Transactional
    public void getAllMTimeattackQuestStageRewardsBySpecialRewardGroupIdIsInShouldWork() throws Exception {
        // Initialize the database
        mTimeattackQuestStageRewardRepository.saveAndFlush(mTimeattackQuestStageReward);

        // Get all the mTimeattackQuestStageRewardList where specialRewardGroupId in DEFAULT_SPECIAL_REWARD_GROUP_ID or UPDATED_SPECIAL_REWARD_GROUP_ID
        defaultMTimeattackQuestStageRewardShouldBeFound("specialRewardGroupId.in=" + DEFAULT_SPECIAL_REWARD_GROUP_ID + "," + UPDATED_SPECIAL_REWARD_GROUP_ID);

        // Get all the mTimeattackQuestStageRewardList where specialRewardGroupId equals to UPDATED_SPECIAL_REWARD_GROUP_ID
        defaultMTimeattackQuestStageRewardShouldNotBeFound("specialRewardGroupId.in=" + UPDATED_SPECIAL_REWARD_GROUP_ID);
    }

    @Test
    @Transactional
    public void getAllMTimeattackQuestStageRewardsBySpecialRewardGroupIdIsNullOrNotNull() throws Exception {
        // Initialize the database
        mTimeattackQuestStageRewardRepository.saveAndFlush(mTimeattackQuestStageReward);

        // Get all the mTimeattackQuestStageRewardList where specialRewardGroupId is not null
        defaultMTimeattackQuestStageRewardShouldBeFound("specialRewardGroupId.specified=true");

        // Get all the mTimeattackQuestStageRewardList where specialRewardGroupId is null
        defaultMTimeattackQuestStageRewardShouldNotBeFound("specialRewardGroupId.specified=false");
    }

    @Test
    @Transactional
    public void getAllMTimeattackQuestStageRewardsBySpecialRewardGroupIdIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mTimeattackQuestStageRewardRepository.saveAndFlush(mTimeattackQuestStageReward);

        // Get all the mTimeattackQuestStageRewardList where specialRewardGroupId greater than or equals to DEFAULT_SPECIAL_REWARD_GROUP_ID
        defaultMTimeattackQuestStageRewardShouldBeFound("specialRewardGroupId.greaterOrEqualThan=" + DEFAULT_SPECIAL_REWARD_GROUP_ID);

        // Get all the mTimeattackQuestStageRewardList where specialRewardGroupId greater than or equals to UPDATED_SPECIAL_REWARD_GROUP_ID
        defaultMTimeattackQuestStageRewardShouldNotBeFound("specialRewardGroupId.greaterOrEqualThan=" + UPDATED_SPECIAL_REWARD_GROUP_ID);
    }

    @Test
    @Transactional
    public void getAllMTimeattackQuestStageRewardsBySpecialRewardGroupIdIsLessThanSomething() throws Exception {
        // Initialize the database
        mTimeattackQuestStageRewardRepository.saveAndFlush(mTimeattackQuestStageReward);

        // Get all the mTimeattackQuestStageRewardList where specialRewardGroupId less than or equals to DEFAULT_SPECIAL_REWARD_GROUP_ID
        defaultMTimeattackQuestStageRewardShouldNotBeFound("specialRewardGroupId.lessThan=" + DEFAULT_SPECIAL_REWARD_GROUP_ID);

        // Get all the mTimeattackQuestStageRewardList where specialRewardGroupId less than or equals to UPDATED_SPECIAL_REWARD_GROUP_ID
        defaultMTimeattackQuestStageRewardShouldBeFound("specialRewardGroupId.lessThan=" + UPDATED_SPECIAL_REWARD_GROUP_ID);
    }


    @Test
    @Transactional
    public void getAllMTimeattackQuestStageRewardsBySpecialRewardAmountIsEqualToSomething() throws Exception {
        // Initialize the database
        mTimeattackQuestStageRewardRepository.saveAndFlush(mTimeattackQuestStageReward);

        // Get all the mTimeattackQuestStageRewardList where specialRewardAmount equals to DEFAULT_SPECIAL_REWARD_AMOUNT
        defaultMTimeattackQuestStageRewardShouldBeFound("specialRewardAmount.equals=" + DEFAULT_SPECIAL_REWARD_AMOUNT);

        // Get all the mTimeattackQuestStageRewardList where specialRewardAmount equals to UPDATED_SPECIAL_REWARD_AMOUNT
        defaultMTimeattackQuestStageRewardShouldNotBeFound("specialRewardAmount.equals=" + UPDATED_SPECIAL_REWARD_AMOUNT);
    }

    @Test
    @Transactional
    public void getAllMTimeattackQuestStageRewardsBySpecialRewardAmountIsInShouldWork() throws Exception {
        // Initialize the database
        mTimeattackQuestStageRewardRepository.saveAndFlush(mTimeattackQuestStageReward);

        // Get all the mTimeattackQuestStageRewardList where specialRewardAmount in DEFAULT_SPECIAL_REWARD_AMOUNT or UPDATED_SPECIAL_REWARD_AMOUNT
        defaultMTimeattackQuestStageRewardShouldBeFound("specialRewardAmount.in=" + DEFAULT_SPECIAL_REWARD_AMOUNT + "," + UPDATED_SPECIAL_REWARD_AMOUNT);

        // Get all the mTimeattackQuestStageRewardList where specialRewardAmount equals to UPDATED_SPECIAL_REWARD_AMOUNT
        defaultMTimeattackQuestStageRewardShouldNotBeFound("specialRewardAmount.in=" + UPDATED_SPECIAL_REWARD_AMOUNT);
    }

    @Test
    @Transactional
    public void getAllMTimeattackQuestStageRewardsBySpecialRewardAmountIsNullOrNotNull() throws Exception {
        // Initialize the database
        mTimeattackQuestStageRewardRepository.saveAndFlush(mTimeattackQuestStageReward);

        // Get all the mTimeattackQuestStageRewardList where specialRewardAmount is not null
        defaultMTimeattackQuestStageRewardShouldBeFound("specialRewardAmount.specified=true");

        // Get all the mTimeattackQuestStageRewardList where specialRewardAmount is null
        defaultMTimeattackQuestStageRewardShouldNotBeFound("specialRewardAmount.specified=false");
    }

    @Test
    @Transactional
    public void getAllMTimeattackQuestStageRewardsBySpecialRewardAmountIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mTimeattackQuestStageRewardRepository.saveAndFlush(mTimeattackQuestStageReward);

        // Get all the mTimeattackQuestStageRewardList where specialRewardAmount greater than or equals to DEFAULT_SPECIAL_REWARD_AMOUNT
        defaultMTimeattackQuestStageRewardShouldBeFound("specialRewardAmount.greaterOrEqualThan=" + DEFAULT_SPECIAL_REWARD_AMOUNT);

        // Get all the mTimeattackQuestStageRewardList where specialRewardAmount greater than or equals to UPDATED_SPECIAL_REWARD_AMOUNT
        defaultMTimeattackQuestStageRewardShouldNotBeFound("specialRewardAmount.greaterOrEqualThan=" + UPDATED_SPECIAL_REWARD_AMOUNT);
    }

    @Test
    @Transactional
    public void getAllMTimeattackQuestStageRewardsBySpecialRewardAmountIsLessThanSomething() throws Exception {
        // Initialize the database
        mTimeattackQuestStageRewardRepository.saveAndFlush(mTimeattackQuestStageReward);

        // Get all the mTimeattackQuestStageRewardList where specialRewardAmount less than or equals to DEFAULT_SPECIAL_REWARD_AMOUNT
        defaultMTimeattackQuestStageRewardShouldNotBeFound("specialRewardAmount.lessThan=" + DEFAULT_SPECIAL_REWARD_AMOUNT);

        // Get all the mTimeattackQuestStageRewardList where specialRewardAmount less than or equals to UPDATED_SPECIAL_REWARD_AMOUNT
        defaultMTimeattackQuestStageRewardShouldBeFound("specialRewardAmount.lessThan=" + UPDATED_SPECIAL_REWARD_AMOUNT);
    }


    @Test
    @Transactional
    public void getAllMTimeattackQuestStageRewardsByGoalRewardGroupIdIsEqualToSomething() throws Exception {
        // Initialize the database
        mTimeattackQuestStageRewardRepository.saveAndFlush(mTimeattackQuestStageReward);

        // Get all the mTimeattackQuestStageRewardList where goalRewardGroupId equals to DEFAULT_GOAL_REWARD_GROUP_ID
        defaultMTimeattackQuestStageRewardShouldBeFound("goalRewardGroupId.equals=" + DEFAULT_GOAL_REWARD_GROUP_ID);

        // Get all the mTimeattackQuestStageRewardList where goalRewardGroupId equals to UPDATED_GOAL_REWARD_GROUP_ID
        defaultMTimeattackQuestStageRewardShouldNotBeFound("goalRewardGroupId.equals=" + UPDATED_GOAL_REWARD_GROUP_ID);
    }

    @Test
    @Transactional
    public void getAllMTimeattackQuestStageRewardsByGoalRewardGroupIdIsInShouldWork() throws Exception {
        // Initialize the database
        mTimeattackQuestStageRewardRepository.saveAndFlush(mTimeattackQuestStageReward);

        // Get all the mTimeattackQuestStageRewardList where goalRewardGroupId in DEFAULT_GOAL_REWARD_GROUP_ID or UPDATED_GOAL_REWARD_GROUP_ID
        defaultMTimeattackQuestStageRewardShouldBeFound("goalRewardGroupId.in=" + DEFAULT_GOAL_REWARD_GROUP_ID + "," + UPDATED_GOAL_REWARD_GROUP_ID);

        // Get all the mTimeattackQuestStageRewardList where goalRewardGroupId equals to UPDATED_GOAL_REWARD_GROUP_ID
        defaultMTimeattackQuestStageRewardShouldNotBeFound("goalRewardGroupId.in=" + UPDATED_GOAL_REWARD_GROUP_ID);
    }

    @Test
    @Transactional
    public void getAllMTimeattackQuestStageRewardsByGoalRewardGroupIdIsNullOrNotNull() throws Exception {
        // Initialize the database
        mTimeattackQuestStageRewardRepository.saveAndFlush(mTimeattackQuestStageReward);

        // Get all the mTimeattackQuestStageRewardList where goalRewardGroupId is not null
        defaultMTimeattackQuestStageRewardShouldBeFound("goalRewardGroupId.specified=true");

        // Get all the mTimeattackQuestStageRewardList where goalRewardGroupId is null
        defaultMTimeattackQuestStageRewardShouldNotBeFound("goalRewardGroupId.specified=false");
    }

    @Test
    @Transactional
    public void getAllMTimeattackQuestStageRewardsByGoalRewardGroupIdIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mTimeattackQuestStageRewardRepository.saveAndFlush(mTimeattackQuestStageReward);

        // Get all the mTimeattackQuestStageRewardList where goalRewardGroupId greater than or equals to DEFAULT_GOAL_REWARD_GROUP_ID
        defaultMTimeattackQuestStageRewardShouldBeFound("goalRewardGroupId.greaterOrEqualThan=" + DEFAULT_GOAL_REWARD_GROUP_ID);

        // Get all the mTimeattackQuestStageRewardList where goalRewardGroupId greater than or equals to UPDATED_GOAL_REWARD_GROUP_ID
        defaultMTimeattackQuestStageRewardShouldNotBeFound("goalRewardGroupId.greaterOrEqualThan=" + UPDATED_GOAL_REWARD_GROUP_ID);
    }

    @Test
    @Transactional
    public void getAllMTimeattackQuestStageRewardsByGoalRewardGroupIdIsLessThanSomething() throws Exception {
        // Initialize the database
        mTimeattackQuestStageRewardRepository.saveAndFlush(mTimeattackQuestStageReward);

        // Get all the mTimeattackQuestStageRewardList where goalRewardGroupId less than or equals to DEFAULT_GOAL_REWARD_GROUP_ID
        defaultMTimeattackQuestStageRewardShouldNotBeFound("goalRewardGroupId.lessThan=" + DEFAULT_GOAL_REWARD_GROUP_ID);

        // Get all the mTimeattackQuestStageRewardList where goalRewardGroupId less than or equals to UPDATED_GOAL_REWARD_GROUP_ID
        defaultMTimeattackQuestStageRewardShouldBeFound("goalRewardGroupId.lessThan=" + UPDATED_GOAL_REWARD_GROUP_ID);
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultMTimeattackQuestStageRewardShouldBeFound(String filter) throws Exception {
        restMTimeattackQuestStageRewardMockMvc.perform(get("/api/m-timeattack-quest-stage-rewards?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(mTimeattackQuestStageReward.getId().intValue())))
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
        restMTimeattackQuestStageRewardMockMvc.perform(get("/api/m-timeattack-quest-stage-rewards/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultMTimeattackQuestStageRewardShouldNotBeFound(String filter) throws Exception {
        restMTimeattackQuestStageRewardMockMvc.perform(get("/api/m-timeattack-quest-stage-rewards?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restMTimeattackQuestStageRewardMockMvc.perform(get("/api/m-timeattack-quest-stage-rewards/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("0"));
    }


    @Test
    @Transactional
    public void getNonExistingMTimeattackQuestStageReward() throws Exception {
        // Get the mTimeattackQuestStageReward
        restMTimeattackQuestStageRewardMockMvc.perform(get("/api/m-timeattack-quest-stage-rewards/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateMTimeattackQuestStageReward() throws Exception {
        // Initialize the database
        mTimeattackQuestStageRewardRepository.saveAndFlush(mTimeattackQuestStageReward);

        int databaseSizeBeforeUpdate = mTimeattackQuestStageRewardRepository.findAll().size();

        // Update the mTimeattackQuestStageReward
        MTimeattackQuestStageReward updatedMTimeattackQuestStageReward = mTimeattackQuestStageRewardRepository.findById(mTimeattackQuestStageReward.getId()).get();
        // Disconnect from session so that the updates on updatedMTimeattackQuestStageReward are not directly saved in db
        em.detach(updatedMTimeattackQuestStageReward);
        updatedMTimeattackQuestStageReward
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
        MTimeattackQuestStageRewardDTO mTimeattackQuestStageRewardDTO = mTimeattackQuestStageRewardMapper.toDto(updatedMTimeattackQuestStageReward);

        restMTimeattackQuestStageRewardMockMvc.perform(put("/api/m-timeattack-quest-stage-rewards")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mTimeattackQuestStageRewardDTO)))
            .andExpect(status().isOk());

        // Validate the MTimeattackQuestStageReward in the database
        List<MTimeattackQuestStageReward> mTimeattackQuestStageRewardList = mTimeattackQuestStageRewardRepository.findAll();
        assertThat(mTimeattackQuestStageRewardList).hasSize(databaseSizeBeforeUpdate);
        MTimeattackQuestStageReward testMTimeattackQuestStageReward = mTimeattackQuestStageRewardList.get(mTimeattackQuestStageRewardList.size() - 1);
        assertThat(testMTimeattackQuestStageReward.getStageId()).isEqualTo(UPDATED_STAGE_ID);
        assertThat(testMTimeattackQuestStageReward.getExp()).isEqualTo(UPDATED_EXP);
        assertThat(testMTimeattackQuestStageReward.getCoin()).isEqualTo(UPDATED_COIN);
        assertThat(testMTimeattackQuestStageReward.getGuildPoint()).isEqualTo(UPDATED_GUILD_POINT);
        assertThat(testMTimeattackQuestStageReward.getClearRewardGroupId()).isEqualTo(UPDATED_CLEAR_REWARD_GROUP_ID);
        assertThat(testMTimeattackQuestStageReward.getClearRewardWeightId()).isEqualTo(UPDATED_CLEAR_REWARD_WEIGHT_ID);
        assertThat(testMTimeattackQuestStageReward.getAchievementRewardGroupId()).isEqualTo(UPDATED_ACHIEVEMENT_REWARD_GROUP_ID);
        assertThat(testMTimeattackQuestStageReward.getCoopGroupId()).isEqualTo(UPDATED_COOP_GROUP_ID);
        assertThat(testMTimeattackQuestStageReward.getSpecialRewardGroupId()).isEqualTo(UPDATED_SPECIAL_REWARD_GROUP_ID);
        assertThat(testMTimeattackQuestStageReward.getSpecialRewardAmount()).isEqualTo(UPDATED_SPECIAL_REWARD_AMOUNT);
        assertThat(testMTimeattackQuestStageReward.getGoalRewardGroupId()).isEqualTo(UPDATED_GOAL_REWARD_GROUP_ID);
    }

    @Test
    @Transactional
    public void updateNonExistingMTimeattackQuestStageReward() throws Exception {
        int databaseSizeBeforeUpdate = mTimeattackQuestStageRewardRepository.findAll().size();

        // Create the MTimeattackQuestStageReward
        MTimeattackQuestStageRewardDTO mTimeattackQuestStageRewardDTO = mTimeattackQuestStageRewardMapper.toDto(mTimeattackQuestStageReward);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restMTimeattackQuestStageRewardMockMvc.perform(put("/api/m-timeattack-quest-stage-rewards")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mTimeattackQuestStageRewardDTO)))
            .andExpect(status().isBadRequest());

        // Validate the MTimeattackQuestStageReward in the database
        List<MTimeattackQuestStageReward> mTimeattackQuestStageRewardList = mTimeattackQuestStageRewardRepository.findAll();
        assertThat(mTimeattackQuestStageRewardList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteMTimeattackQuestStageReward() throws Exception {
        // Initialize the database
        mTimeattackQuestStageRewardRepository.saveAndFlush(mTimeattackQuestStageReward);

        int databaseSizeBeforeDelete = mTimeattackQuestStageRewardRepository.findAll().size();

        // Delete the mTimeattackQuestStageReward
        restMTimeattackQuestStageRewardMockMvc.perform(delete("/api/m-timeattack-quest-stage-rewards/{id}", mTimeattackQuestStageReward.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database is empty
        List<MTimeattackQuestStageReward> mTimeattackQuestStageRewardList = mTimeattackQuestStageRewardRepository.findAll();
        assertThat(mTimeattackQuestStageRewardList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(MTimeattackQuestStageReward.class);
        MTimeattackQuestStageReward mTimeattackQuestStageReward1 = new MTimeattackQuestStageReward();
        mTimeattackQuestStageReward1.setId(1L);
        MTimeattackQuestStageReward mTimeattackQuestStageReward2 = new MTimeattackQuestStageReward();
        mTimeattackQuestStageReward2.setId(mTimeattackQuestStageReward1.getId());
        assertThat(mTimeattackQuestStageReward1).isEqualTo(mTimeattackQuestStageReward2);
        mTimeattackQuestStageReward2.setId(2L);
        assertThat(mTimeattackQuestStageReward1).isNotEqualTo(mTimeattackQuestStageReward2);
        mTimeattackQuestStageReward1.setId(null);
        assertThat(mTimeattackQuestStageReward1).isNotEqualTo(mTimeattackQuestStageReward2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(MTimeattackQuestStageRewardDTO.class);
        MTimeattackQuestStageRewardDTO mTimeattackQuestStageRewardDTO1 = new MTimeattackQuestStageRewardDTO();
        mTimeattackQuestStageRewardDTO1.setId(1L);
        MTimeattackQuestStageRewardDTO mTimeattackQuestStageRewardDTO2 = new MTimeattackQuestStageRewardDTO();
        assertThat(mTimeattackQuestStageRewardDTO1).isNotEqualTo(mTimeattackQuestStageRewardDTO2);
        mTimeattackQuestStageRewardDTO2.setId(mTimeattackQuestStageRewardDTO1.getId());
        assertThat(mTimeattackQuestStageRewardDTO1).isEqualTo(mTimeattackQuestStageRewardDTO2);
        mTimeattackQuestStageRewardDTO2.setId(2L);
        assertThat(mTimeattackQuestStageRewardDTO1).isNotEqualTo(mTimeattackQuestStageRewardDTO2);
        mTimeattackQuestStageRewardDTO1.setId(null);
        assertThat(mTimeattackQuestStageRewardDTO1).isNotEqualTo(mTimeattackQuestStageRewardDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(mTimeattackQuestStageRewardMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(mTimeattackQuestStageRewardMapper.fromId(null)).isNull();
    }
}
