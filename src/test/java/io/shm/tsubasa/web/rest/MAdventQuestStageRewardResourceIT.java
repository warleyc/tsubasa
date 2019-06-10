package io.shm.tsubasa.web.rest;

import io.shm.tsubasa.TsubasaApp;
import io.shm.tsubasa.domain.MAdventQuestStageReward;
import io.shm.tsubasa.repository.MAdventQuestStageRewardRepository;
import io.shm.tsubasa.service.MAdventQuestStageRewardService;
import io.shm.tsubasa.service.dto.MAdventQuestStageRewardDTO;
import io.shm.tsubasa.service.mapper.MAdventQuestStageRewardMapper;
import io.shm.tsubasa.web.rest.errors.ExceptionTranslator;
import io.shm.tsubasa.service.dto.MAdventQuestStageRewardCriteria;
import io.shm.tsubasa.service.MAdventQuestStageRewardQueryService;

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
 * Integration tests for the {@Link MAdventQuestStageRewardResource} REST controller.
 */
@SpringBootTest(classes = TsubasaApp.class)
public class MAdventQuestStageRewardResourceIT {

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
    private MAdventQuestStageRewardRepository mAdventQuestStageRewardRepository;

    @Autowired
    private MAdventQuestStageRewardMapper mAdventQuestStageRewardMapper;

    @Autowired
    private MAdventQuestStageRewardService mAdventQuestStageRewardService;

    @Autowired
    private MAdventQuestStageRewardQueryService mAdventQuestStageRewardQueryService;

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

    private MockMvc restMAdventQuestStageRewardMockMvc;

    private MAdventQuestStageReward mAdventQuestStageReward;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final MAdventQuestStageRewardResource mAdventQuestStageRewardResource = new MAdventQuestStageRewardResource(mAdventQuestStageRewardService, mAdventQuestStageRewardQueryService);
        this.restMAdventQuestStageRewardMockMvc = MockMvcBuilders.standaloneSetup(mAdventQuestStageRewardResource)
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
    public static MAdventQuestStageReward createEntity(EntityManager em) {
        MAdventQuestStageReward mAdventQuestStageReward = new MAdventQuestStageReward()
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
        return mAdventQuestStageReward;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static MAdventQuestStageReward createUpdatedEntity(EntityManager em) {
        MAdventQuestStageReward mAdventQuestStageReward = new MAdventQuestStageReward()
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
        return mAdventQuestStageReward;
    }

    @BeforeEach
    public void initTest() {
        mAdventQuestStageReward = createEntity(em);
    }

    @Test
    @Transactional
    public void createMAdventQuestStageReward() throws Exception {
        int databaseSizeBeforeCreate = mAdventQuestStageRewardRepository.findAll().size();

        // Create the MAdventQuestStageReward
        MAdventQuestStageRewardDTO mAdventQuestStageRewardDTO = mAdventQuestStageRewardMapper.toDto(mAdventQuestStageReward);
        restMAdventQuestStageRewardMockMvc.perform(post("/api/m-advent-quest-stage-rewards")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mAdventQuestStageRewardDTO)))
            .andExpect(status().isCreated());

        // Validate the MAdventQuestStageReward in the database
        List<MAdventQuestStageReward> mAdventQuestStageRewardList = mAdventQuestStageRewardRepository.findAll();
        assertThat(mAdventQuestStageRewardList).hasSize(databaseSizeBeforeCreate + 1);
        MAdventQuestStageReward testMAdventQuestStageReward = mAdventQuestStageRewardList.get(mAdventQuestStageRewardList.size() - 1);
        assertThat(testMAdventQuestStageReward.getStageId()).isEqualTo(DEFAULT_STAGE_ID);
        assertThat(testMAdventQuestStageReward.getExp()).isEqualTo(DEFAULT_EXP);
        assertThat(testMAdventQuestStageReward.getCoin()).isEqualTo(DEFAULT_COIN);
        assertThat(testMAdventQuestStageReward.getGuildPoint()).isEqualTo(DEFAULT_GUILD_POINT);
        assertThat(testMAdventQuestStageReward.getClearRewardGroupId()).isEqualTo(DEFAULT_CLEAR_REWARD_GROUP_ID);
        assertThat(testMAdventQuestStageReward.getClearRewardWeightId()).isEqualTo(DEFAULT_CLEAR_REWARD_WEIGHT_ID);
        assertThat(testMAdventQuestStageReward.getAchievementRewardGroupId()).isEqualTo(DEFAULT_ACHIEVEMENT_REWARD_GROUP_ID);
        assertThat(testMAdventQuestStageReward.getCoopGroupId()).isEqualTo(DEFAULT_COOP_GROUP_ID);
        assertThat(testMAdventQuestStageReward.getSpecialRewardGroupId()).isEqualTo(DEFAULT_SPECIAL_REWARD_GROUP_ID);
        assertThat(testMAdventQuestStageReward.getSpecialRewardAmount()).isEqualTo(DEFAULT_SPECIAL_REWARD_AMOUNT);
        assertThat(testMAdventQuestStageReward.getGoalRewardGroupId()).isEqualTo(DEFAULT_GOAL_REWARD_GROUP_ID);
    }

    @Test
    @Transactional
    public void createMAdventQuestStageRewardWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = mAdventQuestStageRewardRepository.findAll().size();

        // Create the MAdventQuestStageReward with an existing ID
        mAdventQuestStageReward.setId(1L);
        MAdventQuestStageRewardDTO mAdventQuestStageRewardDTO = mAdventQuestStageRewardMapper.toDto(mAdventQuestStageReward);

        // An entity with an existing ID cannot be created, so this API call must fail
        restMAdventQuestStageRewardMockMvc.perform(post("/api/m-advent-quest-stage-rewards")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mAdventQuestStageRewardDTO)))
            .andExpect(status().isBadRequest());

        // Validate the MAdventQuestStageReward in the database
        List<MAdventQuestStageReward> mAdventQuestStageRewardList = mAdventQuestStageRewardRepository.findAll();
        assertThat(mAdventQuestStageRewardList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkStageIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = mAdventQuestStageRewardRepository.findAll().size();
        // set the field null
        mAdventQuestStageReward.setStageId(null);

        // Create the MAdventQuestStageReward, which fails.
        MAdventQuestStageRewardDTO mAdventQuestStageRewardDTO = mAdventQuestStageRewardMapper.toDto(mAdventQuestStageReward);

        restMAdventQuestStageRewardMockMvc.perform(post("/api/m-advent-quest-stage-rewards")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mAdventQuestStageRewardDTO)))
            .andExpect(status().isBadRequest());

        List<MAdventQuestStageReward> mAdventQuestStageRewardList = mAdventQuestStageRewardRepository.findAll();
        assertThat(mAdventQuestStageRewardList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkExpIsRequired() throws Exception {
        int databaseSizeBeforeTest = mAdventQuestStageRewardRepository.findAll().size();
        // set the field null
        mAdventQuestStageReward.setExp(null);

        // Create the MAdventQuestStageReward, which fails.
        MAdventQuestStageRewardDTO mAdventQuestStageRewardDTO = mAdventQuestStageRewardMapper.toDto(mAdventQuestStageReward);

        restMAdventQuestStageRewardMockMvc.perform(post("/api/m-advent-quest-stage-rewards")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mAdventQuestStageRewardDTO)))
            .andExpect(status().isBadRequest());

        List<MAdventQuestStageReward> mAdventQuestStageRewardList = mAdventQuestStageRewardRepository.findAll();
        assertThat(mAdventQuestStageRewardList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkCoinIsRequired() throws Exception {
        int databaseSizeBeforeTest = mAdventQuestStageRewardRepository.findAll().size();
        // set the field null
        mAdventQuestStageReward.setCoin(null);

        // Create the MAdventQuestStageReward, which fails.
        MAdventQuestStageRewardDTO mAdventQuestStageRewardDTO = mAdventQuestStageRewardMapper.toDto(mAdventQuestStageReward);

        restMAdventQuestStageRewardMockMvc.perform(post("/api/m-advent-quest-stage-rewards")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mAdventQuestStageRewardDTO)))
            .andExpect(status().isBadRequest());

        List<MAdventQuestStageReward> mAdventQuestStageRewardList = mAdventQuestStageRewardRepository.findAll();
        assertThat(mAdventQuestStageRewardList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkGuildPointIsRequired() throws Exception {
        int databaseSizeBeforeTest = mAdventQuestStageRewardRepository.findAll().size();
        // set the field null
        mAdventQuestStageReward.setGuildPoint(null);

        // Create the MAdventQuestStageReward, which fails.
        MAdventQuestStageRewardDTO mAdventQuestStageRewardDTO = mAdventQuestStageRewardMapper.toDto(mAdventQuestStageReward);

        restMAdventQuestStageRewardMockMvc.perform(post("/api/m-advent-quest-stage-rewards")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mAdventQuestStageRewardDTO)))
            .andExpect(status().isBadRequest());

        List<MAdventQuestStageReward> mAdventQuestStageRewardList = mAdventQuestStageRewardRepository.findAll();
        assertThat(mAdventQuestStageRewardList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkClearRewardGroupIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = mAdventQuestStageRewardRepository.findAll().size();
        // set the field null
        mAdventQuestStageReward.setClearRewardGroupId(null);

        // Create the MAdventQuestStageReward, which fails.
        MAdventQuestStageRewardDTO mAdventQuestStageRewardDTO = mAdventQuestStageRewardMapper.toDto(mAdventQuestStageReward);

        restMAdventQuestStageRewardMockMvc.perform(post("/api/m-advent-quest-stage-rewards")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mAdventQuestStageRewardDTO)))
            .andExpect(status().isBadRequest());

        List<MAdventQuestStageReward> mAdventQuestStageRewardList = mAdventQuestStageRewardRepository.findAll();
        assertThat(mAdventQuestStageRewardList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkClearRewardWeightIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = mAdventQuestStageRewardRepository.findAll().size();
        // set the field null
        mAdventQuestStageReward.setClearRewardWeightId(null);

        // Create the MAdventQuestStageReward, which fails.
        MAdventQuestStageRewardDTO mAdventQuestStageRewardDTO = mAdventQuestStageRewardMapper.toDto(mAdventQuestStageReward);

        restMAdventQuestStageRewardMockMvc.perform(post("/api/m-advent-quest-stage-rewards")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mAdventQuestStageRewardDTO)))
            .andExpect(status().isBadRequest());

        List<MAdventQuestStageReward> mAdventQuestStageRewardList = mAdventQuestStageRewardRepository.findAll();
        assertThat(mAdventQuestStageRewardList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkAchievementRewardGroupIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = mAdventQuestStageRewardRepository.findAll().size();
        // set the field null
        mAdventQuestStageReward.setAchievementRewardGroupId(null);

        // Create the MAdventQuestStageReward, which fails.
        MAdventQuestStageRewardDTO mAdventQuestStageRewardDTO = mAdventQuestStageRewardMapper.toDto(mAdventQuestStageReward);

        restMAdventQuestStageRewardMockMvc.perform(post("/api/m-advent-quest-stage-rewards")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mAdventQuestStageRewardDTO)))
            .andExpect(status().isBadRequest());

        List<MAdventQuestStageReward> mAdventQuestStageRewardList = mAdventQuestStageRewardRepository.findAll();
        assertThat(mAdventQuestStageRewardList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkCoopGroupIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = mAdventQuestStageRewardRepository.findAll().size();
        // set the field null
        mAdventQuestStageReward.setCoopGroupId(null);

        // Create the MAdventQuestStageReward, which fails.
        MAdventQuestStageRewardDTO mAdventQuestStageRewardDTO = mAdventQuestStageRewardMapper.toDto(mAdventQuestStageReward);

        restMAdventQuestStageRewardMockMvc.perform(post("/api/m-advent-quest-stage-rewards")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mAdventQuestStageRewardDTO)))
            .andExpect(status().isBadRequest());

        List<MAdventQuestStageReward> mAdventQuestStageRewardList = mAdventQuestStageRewardRepository.findAll();
        assertThat(mAdventQuestStageRewardList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkSpecialRewardAmountIsRequired() throws Exception {
        int databaseSizeBeforeTest = mAdventQuestStageRewardRepository.findAll().size();
        // set the field null
        mAdventQuestStageReward.setSpecialRewardAmount(null);

        // Create the MAdventQuestStageReward, which fails.
        MAdventQuestStageRewardDTO mAdventQuestStageRewardDTO = mAdventQuestStageRewardMapper.toDto(mAdventQuestStageReward);

        restMAdventQuestStageRewardMockMvc.perform(post("/api/m-advent-quest-stage-rewards")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mAdventQuestStageRewardDTO)))
            .andExpect(status().isBadRequest());

        List<MAdventQuestStageReward> mAdventQuestStageRewardList = mAdventQuestStageRewardRepository.findAll();
        assertThat(mAdventQuestStageRewardList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllMAdventQuestStageRewards() throws Exception {
        // Initialize the database
        mAdventQuestStageRewardRepository.saveAndFlush(mAdventQuestStageReward);

        // Get all the mAdventQuestStageRewardList
        restMAdventQuestStageRewardMockMvc.perform(get("/api/m-advent-quest-stage-rewards?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(mAdventQuestStageReward.getId().intValue())))
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
    public void getMAdventQuestStageReward() throws Exception {
        // Initialize the database
        mAdventQuestStageRewardRepository.saveAndFlush(mAdventQuestStageReward);

        // Get the mAdventQuestStageReward
        restMAdventQuestStageRewardMockMvc.perform(get("/api/m-advent-quest-stage-rewards/{id}", mAdventQuestStageReward.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(mAdventQuestStageReward.getId().intValue()))
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
    public void getAllMAdventQuestStageRewardsByStageIdIsEqualToSomething() throws Exception {
        // Initialize the database
        mAdventQuestStageRewardRepository.saveAndFlush(mAdventQuestStageReward);

        // Get all the mAdventQuestStageRewardList where stageId equals to DEFAULT_STAGE_ID
        defaultMAdventQuestStageRewardShouldBeFound("stageId.equals=" + DEFAULT_STAGE_ID);

        // Get all the mAdventQuestStageRewardList where stageId equals to UPDATED_STAGE_ID
        defaultMAdventQuestStageRewardShouldNotBeFound("stageId.equals=" + UPDATED_STAGE_ID);
    }

    @Test
    @Transactional
    public void getAllMAdventQuestStageRewardsByStageIdIsInShouldWork() throws Exception {
        // Initialize the database
        mAdventQuestStageRewardRepository.saveAndFlush(mAdventQuestStageReward);

        // Get all the mAdventQuestStageRewardList where stageId in DEFAULT_STAGE_ID or UPDATED_STAGE_ID
        defaultMAdventQuestStageRewardShouldBeFound("stageId.in=" + DEFAULT_STAGE_ID + "," + UPDATED_STAGE_ID);

        // Get all the mAdventQuestStageRewardList where stageId equals to UPDATED_STAGE_ID
        defaultMAdventQuestStageRewardShouldNotBeFound("stageId.in=" + UPDATED_STAGE_ID);
    }

    @Test
    @Transactional
    public void getAllMAdventQuestStageRewardsByStageIdIsNullOrNotNull() throws Exception {
        // Initialize the database
        mAdventQuestStageRewardRepository.saveAndFlush(mAdventQuestStageReward);

        // Get all the mAdventQuestStageRewardList where stageId is not null
        defaultMAdventQuestStageRewardShouldBeFound("stageId.specified=true");

        // Get all the mAdventQuestStageRewardList where stageId is null
        defaultMAdventQuestStageRewardShouldNotBeFound("stageId.specified=false");
    }

    @Test
    @Transactional
    public void getAllMAdventQuestStageRewardsByStageIdIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mAdventQuestStageRewardRepository.saveAndFlush(mAdventQuestStageReward);

        // Get all the mAdventQuestStageRewardList where stageId greater than or equals to DEFAULT_STAGE_ID
        defaultMAdventQuestStageRewardShouldBeFound("stageId.greaterOrEqualThan=" + DEFAULT_STAGE_ID);

        // Get all the mAdventQuestStageRewardList where stageId greater than or equals to UPDATED_STAGE_ID
        defaultMAdventQuestStageRewardShouldNotBeFound("stageId.greaterOrEqualThan=" + UPDATED_STAGE_ID);
    }

    @Test
    @Transactional
    public void getAllMAdventQuestStageRewardsByStageIdIsLessThanSomething() throws Exception {
        // Initialize the database
        mAdventQuestStageRewardRepository.saveAndFlush(mAdventQuestStageReward);

        // Get all the mAdventQuestStageRewardList where stageId less than or equals to DEFAULT_STAGE_ID
        defaultMAdventQuestStageRewardShouldNotBeFound("stageId.lessThan=" + DEFAULT_STAGE_ID);

        // Get all the mAdventQuestStageRewardList where stageId less than or equals to UPDATED_STAGE_ID
        defaultMAdventQuestStageRewardShouldBeFound("stageId.lessThan=" + UPDATED_STAGE_ID);
    }


    @Test
    @Transactional
    public void getAllMAdventQuestStageRewardsByExpIsEqualToSomething() throws Exception {
        // Initialize the database
        mAdventQuestStageRewardRepository.saveAndFlush(mAdventQuestStageReward);

        // Get all the mAdventQuestStageRewardList where exp equals to DEFAULT_EXP
        defaultMAdventQuestStageRewardShouldBeFound("exp.equals=" + DEFAULT_EXP);

        // Get all the mAdventQuestStageRewardList where exp equals to UPDATED_EXP
        defaultMAdventQuestStageRewardShouldNotBeFound("exp.equals=" + UPDATED_EXP);
    }

    @Test
    @Transactional
    public void getAllMAdventQuestStageRewardsByExpIsInShouldWork() throws Exception {
        // Initialize the database
        mAdventQuestStageRewardRepository.saveAndFlush(mAdventQuestStageReward);

        // Get all the mAdventQuestStageRewardList where exp in DEFAULT_EXP or UPDATED_EXP
        defaultMAdventQuestStageRewardShouldBeFound("exp.in=" + DEFAULT_EXP + "," + UPDATED_EXP);

        // Get all the mAdventQuestStageRewardList where exp equals to UPDATED_EXP
        defaultMAdventQuestStageRewardShouldNotBeFound("exp.in=" + UPDATED_EXP);
    }

    @Test
    @Transactional
    public void getAllMAdventQuestStageRewardsByExpIsNullOrNotNull() throws Exception {
        // Initialize the database
        mAdventQuestStageRewardRepository.saveAndFlush(mAdventQuestStageReward);

        // Get all the mAdventQuestStageRewardList where exp is not null
        defaultMAdventQuestStageRewardShouldBeFound("exp.specified=true");

        // Get all the mAdventQuestStageRewardList where exp is null
        defaultMAdventQuestStageRewardShouldNotBeFound("exp.specified=false");
    }

    @Test
    @Transactional
    public void getAllMAdventQuestStageRewardsByExpIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mAdventQuestStageRewardRepository.saveAndFlush(mAdventQuestStageReward);

        // Get all the mAdventQuestStageRewardList where exp greater than or equals to DEFAULT_EXP
        defaultMAdventQuestStageRewardShouldBeFound("exp.greaterOrEqualThan=" + DEFAULT_EXP);

        // Get all the mAdventQuestStageRewardList where exp greater than or equals to UPDATED_EXP
        defaultMAdventQuestStageRewardShouldNotBeFound("exp.greaterOrEqualThan=" + UPDATED_EXP);
    }

    @Test
    @Transactional
    public void getAllMAdventQuestStageRewardsByExpIsLessThanSomething() throws Exception {
        // Initialize the database
        mAdventQuestStageRewardRepository.saveAndFlush(mAdventQuestStageReward);

        // Get all the mAdventQuestStageRewardList where exp less than or equals to DEFAULT_EXP
        defaultMAdventQuestStageRewardShouldNotBeFound("exp.lessThan=" + DEFAULT_EXP);

        // Get all the mAdventQuestStageRewardList where exp less than or equals to UPDATED_EXP
        defaultMAdventQuestStageRewardShouldBeFound("exp.lessThan=" + UPDATED_EXP);
    }


    @Test
    @Transactional
    public void getAllMAdventQuestStageRewardsByCoinIsEqualToSomething() throws Exception {
        // Initialize the database
        mAdventQuestStageRewardRepository.saveAndFlush(mAdventQuestStageReward);

        // Get all the mAdventQuestStageRewardList where coin equals to DEFAULT_COIN
        defaultMAdventQuestStageRewardShouldBeFound("coin.equals=" + DEFAULT_COIN);

        // Get all the mAdventQuestStageRewardList where coin equals to UPDATED_COIN
        defaultMAdventQuestStageRewardShouldNotBeFound("coin.equals=" + UPDATED_COIN);
    }

    @Test
    @Transactional
    public void getAllMAdventQuestStageRewardsByCoinIsInShouldWork() throws Exception {
        // Initialize the database
        mAdventQuestStageRewardRepository.saveAndFlush(mAdventQuestStageReward);

        // Get all the mAdventQuestStageRewardList where coin in DEFAULT_COIN or UPDATED_COIN
        defaultMAdventQuestStageRewardShouldBeFound("coin.in=" + DEFAULT_COIN + "," + UPDATED_COIN);

        // Get all the mAdventQuestStageRewardList where coin equals to UPDATED_COIN
        defaultMAdventQuestStageRewardShouldNotBeFound("coin.in=" + UPDATED_COIN);
    }

    @Test
    @Transactional
    public void getAllMAdventQuestStageRewardsByCoinIsNullOrNotNull() throws Exception {
        // Initialize the database
        mAdventQuestStageRewardRepository.saveAndFlush(mAdventQuestStageReward);

        // Get all the mAdventQuestStageRewardList where coin is not null
        defaultMAdventQuestStageRewardShouldBeFound("coin.specified=true");

        // Get all the mAdventQuestStageRewardList where coin is null
        defaultMAdventQuestStageRewardShouldNotBeFound("coin.specified=false");
    }

    @Test
    @Transactional
    public void getAllMAdventQuestStageRewardsByCoinIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mAdventQuestStageRewardRepository.saveAndFlush(mAdventQuestStageReward);

        // Get all the mAdventQuestStageRewardList where coin greater than or equals to DEFAULT_COIN
        defaultMAdventQuestStageRewardShouldBeFound("coin.greaterOrEqualThan=" + DEFAULT_COIN);

        // Get all the mAdventQuestStageRewardList where coin greater than or equals to UPDATED_COIN
        defaultMAdventQuestStageRewardShouldNotBeFound("coin.greaterOrEqualThan=" + UPDATED_COIN);
    }

    @Test
    @Transactional
    public void getAllMAdventQuestStageRewardsByCoinIsLessThanSomething() throws Exception {
        // Initialize the database
        mAdventQuestStageRewardRepository.saveAndFlush(mAdventQuestStageReward);

        // Get all the mAdventQuestStageRewardList where coin less than or equals to DEFAULT_COIN
        defaultMAdventQuestStageRewardShouldNotBeFound("coin.lessThan=" + DEFAULT_COIN);

        // Get all the mAdventQuestStageRewardList where coin less than or equals to UPDATED_COIN
        defaultMAdventQuestStageRewardShouldBeFound("coin.lessThan=" + UPDATED_COIN);
    }


    @Test
    @Transactional
    public void getAllMAdventQuestStageRewardsByGuildPointIsEqualToSomething() throws Exception {
        // Initialize the database
        mAdventQuestStageRewardRepository.saveAndFlush(mAdventQuestStageReward);

        // Get all the mAdventQuestStageRewardList where guildPoint equals to DEFAULT_GUILD_POINT
        defaultMAdventQuestStageRewardShouldBeFound("guildPoint.equals=" + DEFAULT_GUILD_POINT);

        // Get all the mAdventQuestStageRewardList where guildPoint equals to UPDATED_GUILD_POINT
        defaultMAdventQuestStageRewardShouldNotBeFound("guildPoint.equals=" + UPDATED_GUILD_POINT);
    }

    @Test
    @Transactional
    public void getAllMAdventQuestStageRewardsByGuildPointIsInShouldWork() throws Exception {
        // Initialize the database
        mAdventQuestStageRewardRepository.saveAndFlush(mAdventQuestStageReward);

        // Get all the mAdventQuestStageRewardList where guildPoint in DEFAULT_GUILD_POINT or UPDATED_GUILD_POINT
        defaultMAdventQuestStageRewardShouldBeFound("guildPoint.in=" + DEFAULT_GUILD_POINT + "," + UPDATED_GUILD_POINT);

        // Get all the mAdventQuestStageRewardList where guildPoint equals to UPDATED_GUILD_POINT
        defaultMAdventQuestStageRewardShouldNotBeFound("guildPoint.in=" + UPDATED_GUILD_POINT);
    }

    @Test
    @Transactional
    public void getAllMAdventQuestStageRewardsByGuildPointIsNullOrNotNull() throws Exception {
        // Initialize the database
        mAdventQuestStageRewardRepository.saveAndFlush(mAdventQuestStageReward);

        // Get all the mAdventQuestStageRewardList where guildPoint is not null
        defaultMAdventQuestStageRewardShouldBeFound("guildPoint.specified=true");

        // Get all the mAdventQuestStageRewardList where guildPoint is null
        defaultMAdventQuestStageRewardShouldNotBeFound("guildPoint.specified=false");
    }

    @Test
    @Transactional
    public void getAllMAdventQuestStageRewardsByGuildPointIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mAdventQuestStageRewardRepository.saveAndFlush(mAdventQuestStageReward);

        // Get all the mAdventQuestStageRewardList where guildPoint greater than or equals to DEFAULT_GUILD_POINT
        defaultMAdventQuestStageRewardShouldBeFound("guildPoint.greaterOrEqualThan=" + DEFAULT_GUILD_POINT);

        // Get all the mAdventQuestStageRewardList where guildPoint greater than or equals to UPDATED_GUILD_POINT
        defaultMAdventQuestStageRewardShouldNotBeFound("guildPoint.greaterOrEqualThan=" + UPDATED_GUILD_POINT);
    }

    @Test
    @Transactional
    public void getAllMAdventQuestStageRewardsByGuildPointIsLessThanSomething() throws Exception {
        // Initialize the database
        mAdventQuestStageRewardRepository.saveAndFlush(mAdventQuestStageReward);

        // Get all the mAdventQuestStageRewardList where guildPoint less than or equals to DEFAULT_GUILD_POINT
        defaultMAdventQuestStageRewardShouldNotBeFound("guildPoint.lessThan=" + DEFAULT_GUILD_POINT);

        // Get all the mAdventQuestStageRewardList where guildPoint less than or equals to UPDATED_GUILD_POINT
        defaultMAdventQuestStageRewardShouldBeFound("guildPoint.lessThan=" + UPDATED_GUILD_POINT);
    }


    @Test
    @Transactional
    public void getAllMAdventQuestStageRewardsByClearRewardGroupIdIsEqualToSomething() throws Exception {
        // Initialize the database
        mAdventQuestStageRewardRepository.saveAndFlush(mAdventQuestStageReward);

        // Get all the mAdventQuestStageRewardList where clearRewardGroupId equals to DEFAULT_CLEAR_REWARD_GROUP_ID
        defaultMAdventQuestStageRewardShouldBeFound("clearRewardGroupId.equals=" + DEFAULT_CLEAR_REWARD_GROUP_ID);

        // Get all the mAdventQuestStageRewardList where clearRewardGroupId equals to UPDATED_CLEAR_REWARD_GROUP_ID
        defaultMAdventQuestStageRewardShouldNotBeFound("clearRewardGroupId.equals=" + UPDATED_CLEAR_REWARD_GROUP_ID);
    }

    @Test
    @Transactional
    public void getAllMAdventQuestStageRewardsByClearRewardGroupIdIsInShouldWork() throws Exception {
        // Initialize the database
        mAdventQuestStageRewardRepository.saveAndFlush(mAdventQuestStageReward);

        // Get all the mAdventQuestStageRewardList where clearRewardGroupId in DEFAULT_CLEAR_REWARD_GROUP_ID or UPDATED_CLEAR_REWARD_GROUP_ID
        defaultMAdventQuestStageRewardShouldBeFound("clearRewardGroupId.in=" + DEFAULT_CLEAR_REWARD_GROUP_ID + "," + UPDATED_CLEAR_REWARD_GROUP_ID);

        // Get all the mAdventQuestStageRewardList where clearRewardGroupId equals to UPDATED_CLEAR_REWARD_GROUP_ID
        defaultMAdventQuestStageRewardShouldNotBeFound("clearRewardGroupId.in=" + UPDATED_CLEAR_REWARD_GROUP_ID);
    }

    @Test
    @Transactional
    public void getAllMAdventQuestStageRewardsByClearRewardGroupIdIsNullOrNotNull() throws Exception {
        // Initialize the database
        mAdventQuestStageRewardRepository.saveAndFlush(mAdventQuestStageReward);

        // Get all the mAdventQuestStageRewardList where clearRewardGroupId is not null
        defaultMAdventQuestStageRewardShouldBeFound("clearRewardGroupId.specified=true");

        // Get all the mAdventQuestStageRewardList where clearRewardGroupId is null
        defaultMAdventQuestStageRewardShouldNotBeFound("clearRewardGroupId.specified=false");
    }

    @Test
    @Transactional
    public void getAllMAdventQuestStageRewardsByClearRewardGroupIdIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mAdventQuestStageRewardRepository.saveAndFlush(mAdventQuestStageReward);

        // Get all the mAdventQuestStageRewardList where clearRewardGroupId greater than or equals to DEFAULT_CLEAR_REWARD_GROUP_ID
        defaultMAdventQuestStageRewardShouldBeFound("clearRewardGroupId.greaterOrEqualThan=" + DEFAULT_CLEAR_REWARD_GROUP_ID);

        // Get all the mAdventQuestStageRewardList where clearRewardGroupId greater than or equals to UPDATED_CLEAR_REWARD_GROUP_ID
        defaultMAdventQuestStageRewardShouldNotBeFound("clearRewardGroupId.greaterOrEqualThan=" + UPDATED_CLEAR_REWARD_GROUP_ID);
    }

    @Test
    @Transactional
    public void getAllMAdventQuestStageRewardsByClearRewardGroupIdIsLessThanSomething() throws Exception {
        // Initialize the database
        mAdventQuestStageRewardRepository.saveAndFlush(mAdventQuestStageReward);

        // Get all the mAdventQuestStageRewardList where clearRewardGroupId less than or equals to DEFAULT_CLEAR_REWARD_GROUP_ID
        defaultMAdventQuestStageRewardShouldNotBeFound("clearRewardGroupId.lessThan=" + DEFAULT_CLEAR_REWARD_GROUP_ID);

        // Get all the mAdventQuestStageRewardList where clearRewardGroupId less than or equals to UPDATED_CLEAR_REWARD_GROUP_ID
        defaultMAdventQuestStageRewardShouldBeFound("clearRewardGroupId.lessThan=" + UPDATED_CLEAR_REWARD_GROUP_ID);
    }


    @Test
    @Transactional
    public void getAllMAdventQuestStageRewardsByClearRewardWeightIdIsEqualToSomething() throws Exception {
        // Initialize the database
        mAdventQuestStageRewardRepository.saveAndFlush(mAdventQuestStageReward);

        // Get all the mAdventQuestStageRewardList where clearRewardWeightId equals to DEFAULT_CLEAR_REWARD_WEIGHT_ID
        defaultMAdventQuestStageRewardShouldBeFound("clearRewardWeightId.equals=" + DEFAULT_CLEAR_REWARD_WEIGHT_ID);

        // Get all the mAdventQuestStageRewardList where clearRewardWeightId equals to UPDATED_CLEAR_REWARD_WEIGHT_ID
        defaultMAdventQuestStageRewardShouldNotBeFound("clearRewardWeightId.equals=" + UPDATED_CLEAR_REWARD_WEIGHT_ID);
    }

    @Test
    @Transactional
    public void getAllMAdventQuestStageRewardsByClearRewardWeightIdIsInShouldWork() throws Exception {
        // Initialize the database
        mAdventQuestStageRewardRepository.saveAndFlush(mAdventQuestStageReward);

        // Get all the mAdventQuestStageRewardList where clearRewardWeightId in DEFAULT_CLEAR_REWARD_WEIGHT_ID or UPDATED_CLEAR_REWARD_WEIGHT_ID
        defaultMAdventQuestStageRewardShouldBeFound("clearRewardWeightId.in=" + DEFAULT_CLEAR_REWARD_WEIGHT_ID + "," + UPDATED_CLEAR_REWARD_WEIGHT_ID);

        // Get all the mAdventQuestStageRewardList where clearRewardWeightId equals to UPDATED_CLEAR_REWARD_WEIGHT_ID
        defaultMAdventQuestStageRewardShouldNotBeFound("clearRewardWeightId.in=" + UPDATED_CLEAR_REWARD_WEIGHT_ID);
    }

    @Test
    @Transactional
    public void getAllMAdventQuestStageRewardsByClearRewardWeightIdIsNullOrNotNull() throws Exception {
        // Initialize the database
        mAdventQuestStageRewardRepository.saveAndFlush(mAdventQuestStageReward);

        // Get all the mAdventQuestStageRewardList where clearRewardWeightId is not null
        defaultMAdventQuestStageRewardShouldBeFound("clearRewardWeightId.specified=true");

        // Get all the mAdventQuestStageRewardList where clearRewardWeightId is null
        defaultMAdventQuestStageRewardShouldNotBeFound("clearRewardWeightId.specified=false");
    }

    @Test
    @Transactional
    public void getAllMAdventQuestStageRewardsByClearRewardWeightIdIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mAdventQuestStageRewardRepository.saveAndFlush(mAdventQuestStageReward);

        // Get all the mAdventQuestStageRewardList where clearRewardWeightId greater than or equals to DEFAULT_CLEAR_REWARD_WEIGHT_ID
        defaultMAdventQuestStageRewardShouldBeFound("clearRewardWeightId.greaterOrEqualThan=" + DEFAULT_CLEAR_REWARD_WEIGHT_ID);

        // Get all the mAdventQuestStageRewardList where clearRewardWeightId greater than or equals to UPDATED_CLEAR_REWARD_WEIGHT_ID
        defaultMAdventQuestStageRewardShouldNotBeFound("clearRewardWeightId.greaterOrEqualThan=" + UPDATED_CLEAR_REWARD_WEIGHT_ID);
    }

    @Test
    @Transactional
    public void getAllMAdventQuestStageRewardsByClearRewardWeightIdIsLessThanSomething() throws Exception {
        // Initialize the database
        mAdventQuestStageRewardRepository.saveAndFlush(mAdventQuestStageReward);

        // Get all the mAdventQuestStageRewardList where clearRewardWeightId less than or equals to DEFAULT_CLEAR_REWARD_WEIGHT_ID
        defaultMAdventQuestStageRewardShouldNotBeFound("clearRewardWeightId.lessThan=" + DEFAULT_CLEAR_REWARD_WEIGHT_ID);

        // Get all the mAdventQuestStageRewardList where clearRewardWeightId less than or equals to UPDATED_CLEAR_REWARD_WEIGHT_ID
        defaultMAdventQuestStageRewardShouldBeFound("clearRewardWeightId.lessThan=" + UPDATED_CLEAR_REWARD_WEIGHT_ID);
    }


    @Test
    @Transactional
    public void getAllMAdventQuestStageRewardsByAchievementRewardGroupIdIsEqualToSomething() throws Exception {
        // Initialize the database
        mAdventQuestStageRewardRepository.saveAndFlush(mAdventQuestStageReward);

        // Get all the mAdventQuestStageRewardList where achievementRewardGroupId equals to DEFAULT_ACHIEVEMENT_REWARD_GROUP_ID
        defaultMAdventQuestStageRewardShouldBeFound("achievementRewardGroupId.equals=" + DEFAULT_ACHIEVEMENT_REWARD_GROUP_ID);

        // Get all the mAdventQuestStageRewardList where achievementRewardGroupId equals to UPDATED_ACHIEVEMENT_REWARD_GROUP_ID
        defaultMAdventQuestStageRewardShouldNotBeFound("achievementRewardGroupId.equals=" + UPDATED_ACHIEVEMENT_REWARD_GROUP_ID);
    }

    @Test
    @Transactional
    public void getAllMAdventQuestStageRewardsByAchievementRewardGroupIdIsInShouldWork() throws Exception {
        // Initialize the database
        mAdventQuestStageRewardRepository.saveAndFlush(mAdventQuestStageReward);

        // Get all the mAdventQuestStageRewardList where achievementRewardGroupId in DEFAULT_ACHIEVEMENT_REWARD_GROUP_ID or UPDATED_ACHIEVEMENT_REWARD_GROUP_ID
        defaultMAdventQuestStageRewardShouldBeFound("achievementRewardGroupId.in=" + DEFAULT_ACHIEVEMENT_REWARD_GROUP_ID + "," + UPDATED_ACHIEVEMENT_REWARD_GROUP_ID);

        // Get all the mAdventQuestStageRewardList where achievementRewardGroupId equals to UPDATED_ACHIEVEMENT_REWARD_GROUP_ID
        defaultMAdventQuestStageRewardShouldNotBeFound("achievementRewardGroupId.in=" + UPDATED_ACHIEVEMENT_REWARD_GROUP_ID);
    }

    @Test
    @Transactional
    public void getAllMAdventQuestStageRewardsByAchievementRewardGroupIdIsNullOrNotNull() throws Exception {
        // Initialize the database
        mAdventQuestStageRewardRepository.saveAndFlush(mAdventQuestStageReward);

        // Get all the mAdventQuestStageRewardList where achievementRewardGroupId is not null
        defaultMAdventQuestStageRewardShouldBeFound("achievementRewardGroupId.specified=true");

        // Get all the mAdventQuestStageRewardList where achievementRewardGroupId is null
        defaultMAdventQuestStageRewardShouldNotBeFound("achievementRewardGroupId.specified=false");
    }

    @Test
    @Transactional
    public void getAllMAdventQuestStageRewardsByAchievementRewardGroupIdIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mAdventQuestStageRewardRepository.saveAndFlush(mAdventQuestStageReward);

        // Get all the mAdventQuestStageRewardList where achievementRewardGroupId greater than or equals to DEFAULT_ACHIEVEMENT_REWARD_GROUP_ID
        defaultMAdventQuestStageRewardShouldBeFound("achievementRewardGroupId.greaterOrEqualThan=" + DEFAULT_ACHIEVEMENT_REWARD_GROUP_ID);

        // Get all the mAdventQuestStageRewardList where achievementRewardGroupId greater than or equals to UPDATED_ACHIEVEMENT_REWARD_GROUP_ID
        defaultMAdventQuestStageRewardShouldNotBeFound("achievementRewardGroupId.greaterOrEqualThan=" + UPDATED_ACHIEVEMENT_REWARD_GROUP_ID);
    }

    @Test
    @Transactional
    public void getAllMAdventQuestStageRewardsByAchievementRewardGroupIdIsLessThanSomething() throws Exception {
        // Initialize the database
        mAdventQuestStageRewardRepository.saveAndFlush(mAdventQuestStageReward);

        // Get all the mAdventQuestStageRewardList where achievementRewardGroupId less than or equals to DEFAULT_ACHIEVEMENT_REWARD_GROUP_ID
        defaultMAdventQuestStageRewardShouldNotBeFound("achievementRewardGroupId.lessThan=" + DEFAULT_ACHIEVEMENT_REWARD_GROUP_ID);

        // Get all the mAdventQuestStageRewardList where achievementRewardGroupId less than or equals to UPDATED_ACHIEVEMENT_REWARD_GROUP_ID
        defaultMAdventQuestStageRewardShouldBeFound("achievementRewardGroupId.lessThan=" + UPDATED_ACHIEVEMENT_REWARD_GROUP_ID);
    }


    @Test
    @Transactional
    public void getAllMAdventQuestStageRewardsByCoopGroupIdIsEqualToSomething() throws Exception {
        // Initialize the database
        mAdventQuestStageRewardRepository.saveAndFlush(mAdventQuestStageReward);

        // Get all the mAdventQuestStageRewardList where coopGroupId equals to DEFAULT_COOP_GROUP_ID
        defaultMAdventQuestStageRewardShouldBeFound("coopGroupId.equals=" + DEFAULT_COOP_GROUP_ID);

        // Get all the mAdventQuestStageRewardList where coopGroupId equals to UPDATED_COOP_GROUP_ID
        defaultMAdventQuestStageRewardShouldNotBeFound("coopGroupId.equals=" + UPDATED_COOP_GROUP_ID);
    }

    @Test
    @Transactional
    public void getAllMAdventQuestStageRewardsByCoopGroupIdIsInShouldWork() throws Exception {
        // Initialize the database
        mAdventQuestStageRewardRepository.saveAndFlush(mAdventQuestStageReward);

        // Get all the mAdventQuestStageRewardList where coopGroupId in DEFAULT_COOP_GROUP_ID or UPDATED_COOP_GROUP_ID
        defaultMAdventQuestStageRewardShouldBeFound("coopGroupId.in=" + DEFAULT_COOP_GROUP_ID + "," + UPDATED_COOP_GROUP_ID);

        // Get all the mAdventQuestStageRewardList where coopGroupId equals to UPDATED_COOP_GROUP_ID
        defaultMAdventQuestStageRewardShouldNotBeFound("coopGroupId.in=" + UPDATED_COOP_GROUP_ID);
    }

    @Test
    @Transactional
    public void getAllMAdventQuestStageRewardsByCoopGroupIdIsNullOrNotNull() throws Exception {
        // Initialize the database
        mAdventQuestStageRewardRepository.saveAndFlush(mAdventQuestStageReward);

        // Get all the mAdventQuestStageRewardList where coopGroupId is not null
        defaultMAdventQuestStageRewardShouldBeFound("coopGroupId.specified=true");

        // Get all the mAdventQuestStageRewardList where coopGroupId is null
        defaultMAdventQuestStageRewardShouldNotBeFound("coopGroupId.specified=false");
    }

    @Test
    @Transactional
    public void getAllMAdventQuestStageRewardsByCoopGroupIdIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mAdventQuestStageRewardRepository.saveAndFlush(mAdventQuestStageReward);

        // Get all the mAdventQuestStageRewardList where coopGroupId greater than or equals to DEFAULT_COOP_GROUP_ID
        defaultMAdventQuestStageRewardShouldBeFound("coopGroupId.greaterOrEqualThan=" + DEFAULT_COOP_GROUP_ID);

        // Get all the mAdventQuestStageRewardList where coopGroupId greater than or equals to UPDATED_COOP_GROUP_ID
        defaultMAdventQuestStageRewardShouldNotBeFound("coopGroupId.greaterOrEqualThan=" + UPDATED_COOP_GROUP_ID);
    }

    @Test
    @Transactional
    public void getAllMAdventQuestStageRewardsByCoopGroupIdIsLessThanSomething() throws Exception {
        // Initialize the database
        mAdventQuestStageRewardRepository.saveAndFlush(mAdventQuestStageReward);

        // Get all the mAdventQuestStageRewardList where coopGroupId less than or equals to DEFAULT_COOP_GROUP_ID
        defaultMAdventQuestStageRewardShouldNotBeFound("coopGroupId.lessThan=" + DEFAULT_COOP_GROUP_ID);

        // Get all the mAdventQuestStageRewardList where coopGroupId less than or equals to UPDATED_COOP_GROUP_ID
        defaultMAdventQuestStageRewardShouldBeFound("coopGroupId.lessThan=" + UPDATED_COOP_GROUP_ID);
    }


    @Test
    @Transactional
    public void getAllMAdventQuestStageRewardsBySpecialRewardGroupIdIsEqualToSomething() throws Exception {
        // Initialize the database
        mAdventQuestStageRewardRepository.saveAndFlush(mAdventQuestStageReward);

        // Get all the mAdventQuestStageRewardList where specialRewardGroupId equals to DEFAULT_SPECIAL_REWARD_GROUP_ID
        defaultMAdventQuestStageRewardShouldBeFound("specialRewardGroupId.equals=" + DEFAULT_SPECIAL_REWARD_GROUP_ID);

        // Get all the mAdventQuestStageRewardList where specialRewardGroupId equals to UPDATED_SPECIAL_REWARD_GROUP_ID
        defaultMAdventQuestStageRewardShouldNotBeFound("specialRewardGroupId.equals=" + UPDATED_SPECIAL_REWARD_GROUP_ID);
    }

    @Test
    @Transactional
    public void getAllMAdventQuestStageRewardsBySpecialRewardGroupIdIsInShouldWork() throws Exception {
        // Initialize the database
        mAdventQuestStageRewardRepository.saveAndFlush(mAdventQuestStageReward);

        // Get all the mAdventQuestStageRewardList where specialRewardGroupId in DEFAULT_SPECIAL_REWARD_GROUP_ID or UPDATED_SPECIAL_REWARD_GROUP_ID
        defaultMAdventQuestStageRewardShouldBeFound("specialRewardGroupId.in=" + DEFAULT_SPECIAL_REWARD_GROUP_ID + "," + UPDATED_SPECIAL_REWARD_GROUP_ID);

        // Get all the mAdventQuestStageRewardList where specialRewardGroupId equals to UPDATED_SPECIAL_REWARD_GROUP_ID
        defaultMAdventQuestStageRewardShouldNotBeFound("specialRewardGroupId.in=" + UPDATED_SPECIAL_REWARD_GROUP_ID);
    }

    @Test
    @Transactional
    public void getAllMAdventQuestStageRewardsBySpecialRewardGroupIdIsNullOrNotNull() throws Exception {
        // Initialize the database
        mAdventQuestStageRewardRepository.saveAndFlush(mAdventQuestStageReward);

        // Get all the mAdventQuestStageRewardList where specialRewardGroupId is not null
        defaultMAdventQuestStageRewardShouldBeFound("specialRewardGroupId.specified=true");

        // Get all the mAdventQuestStageRewardList where specialRewardGroupId is null
        defaultMAdventQuestStageRewardShouldNotBeFound("specialRewardGroupId.specified=false");
    }

    @Test
    @Transactional
    public void getAllMAdventQuestStageRewardsBySpecialRewardGroupIdIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mAdventQuestStageRewardRepository.saveAndFlush(mAdventQuestStageReward);

        // Get all the mAdventQuestStageRewardList where specialRewardGroupId greater than or equals to DEFAULT_SPECIAL_REWARD_GROUP_ID
        defaultMAdventQuestStageRewardShouldBeFound("specialRewardGroupId.greaterOrEqualThan=" + DEFAULT_SPECIAL_REWARD_GROUP_ID);

        // Get all the mAdventQuestStageRewardList where specialRewardGroupId greater than or equals to UPDATED_SPECIAL_REWARD_GROUP_ID
        defaultMAdventQuestStageRewardShouldNotBeFound("specialRewardGroupId.greaterOrEqualThan=" + UPDATED_SPECIAL_REWARD_GROUP_ID);
    }

    @Test
    @Transactional
    public void getAllMAdventQuestStageRewardsBySpecialRewardGroupIdIsLessThanSomething() throws Exception {
        // Initialize the database
        mAdventQuestStageRewardRepository.saveAndFlush(mAdventQuestStageReward);

        // Get all the mAdventQuestStageRewardList where specialRewardGroupId less than or equals to DEFAULT_SPECIAL_REWARD_GROUP_ID
        defaultMAdventQuestStageRewardShouldNotBeFound("specialRewardGroupId.lessThan=" + DEFAULT_SPECIAL_REWARD_GROUP_ID);

        // Get all the mAdventQuestStageRewardList where specialRewardGroupId less than or equals to UPDATED_SPECIAL_REWARD_GROUP_ID
        defaultMAdventQuestStageRewardShouldBeFound("specialRewardGroupId.lessThan=" + UPDATED_SPECIAL_REWARD_GROUP_ID);
    }


    @Test
    @Transactional
    public void getAllMAdventQuestStageRewardsBySpecialRewardAmountIsEqualToSomething() throws Exception {
        // Initialize the database
        mAdventQuestStageRewardRepository.saveAndFlush(mAdventQuestStageReward);

        // Get all the mAdventQuestStageRewardList where specialRewardAmount equals to DEFAULT_SPECIAL_REWARD_AMOUNT
        defaultMAdventQuestStageRewardShouldBeFound("specialRewardAmount.equals=" + DEFAULT_SPECIAL_REWARD_AMOUNT);

        // Get all the mAdventQuestStageRewardList where specialRewardAmount equals to UPDATED_SPECIAL_REWARD_AMOUNT
        defaultMAdventQuestStageRewardShouldNotBeFound("specialRewardAmount.equals=" + UPDATED_SPECIAL_REWARD_AMOUNT);
    }

    @Test
    @Transactional
    public void getAllMAdventQuestStageRewardsBySpecialRewardAmountIsInShouldWork() throws Exception {
        // Initialize the database
        mAdventQuestStageRewardRepository.saveAndFlush(mAdventQuestStageReward);

        // Get all the mAdventQuestStageRewardList where specialRewardAmount in DEFAULT_SPECIAL_REWARD_AMOUNT or UPDATED_SPECIAL_REWARD_AMOUNT
        defaultMAdventQuestStageRewardShouldBeFound("specialRewardAmount.in=" + DEFAULT_SPECIAL_REWARD_AMOUNT + "," + UPDATED_SPECIAL_REWARD_AMOUNT);

        // Get all the mAdventQuestStageRewardList where specialRewardAmount equals to UPDATED_SPECIAL_REWARD_AMOUNT
        defaultMAdventQuestStageRewardShouldNotBeFound("specialRewardAmount.in=" + UPDATED_SPECIAL_REWARD_AMOUNT);
    }

    @Test
    @Transactional
    public void getAllMAdventQuestStageRewardsBySpecialRewardAmountIsNullOrNotNull() throws Exception {
        // Initialize the database
        mAdventQuestStageRewardRepository.saveAndFlush(mAdventQuestStageReward);

        // Get all the mAdventQuestStageRewardList where specialRewardAmount is not null
        defaultMAdventQuestStageRewardShouldBeFound("specialRewardAmount.specified=true");

        // Get all the mAdventQuestStageRewardList where specialRewardAmount is null
        defaultMAdventQuestStageRewardShouldNotBeFound("specialRewardAmount.specified=false");
    }

    @Test
    @Transactional
    public void getAllMAdventQuestStageRewardsBySpecialRewardAmountIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mAdventQuestStageRewardRepository.saveAndFlush(mAdventQuestStageReward);

        // Get all the mAdventQuestStageRewardList where specialRewardAmount greater than or equals to DEFAULT_SPECIAL_REWARD_AMOUNT
        defaultMAdventQuestStageRewardShouldBeFound("specialRewardAmount.greaterOrEqualThan=" + DEFAULT_SPECIAL_REWARD_AMOUNT);

        // Get all the mAdventQuestStageRewardList where specialRewardAmount greater than or equals to UPDATED_SPECIAL_REWARD_AMOUNT
        defaultMAdventQuestStageRewardShouldNotBeFound("specialRewardAmount.greaterOrEqualThan=" + UPDATED_SPECIAL_REWARD_AMOUNT);
    }

    @Test
    @Transactional
    public void getAllMAdventQuestStageRewardsBySpecialRewardAmountIsLessThanSomething() throws Exception {
        // Initialize the database
        mAdventQuestStageRewardRepository.saveAndFlush(mAdventQuestStageReward);

        // Get all the mAdventQuestStageRewardList where specialRewardAmount less than or equals to DEFAULT_SPECIAL_REWARD_AMOUNT
        defaultMAdventQuestStageRewardShouldNotBeFound("specialRewardAmount.lessThan=" + DEFAULT_SPECIAL_REWARD_AMOUNT);

        // Get all the mAdventQuestStageRewardList where specialRewardAmount less than or equals to UPDATED_SPECIAL_REWARD_AMOUNT
        defaultMAdventQuestStageRewardShouldBeFound("specialRewardAmount.lessThan=" + UPDATED_SPECIAL_REWARD_AMOUNT);
    }


    @Test
    @Transactional
    public void getAllMAdventQuestStageRewardsByGoalRewardGroupIdIsEqualToSomething() throws Exception {
        // Initialize the database
        mAdventQuestStageRewardRepository.saveAndFlush(mAdventQuestStageReward);

        // Get all the mAdventQuestStageRewardList where goalRewardGroupId equals to DEFAULT_GOAL_REWARD_GROUP_ID
        defaultMAdventQuestStageRewardShouldBeFound("goalRewardGroupId.equals=" + DEFAULT_GOAL_REWARD_GROUP_ID);

        // Get all the mAdventQuestStageRewardList where goalRewardGroupId equals to UPDATED_GOAL_REWARD_GROUP_ID
        defaultMAdventQuestStageRewardShouldNotBeFound("goalRewardGroupId.equals=" + UPDATED_GOAL_REWARD_GROUP_ID);
    }

    @Test
    @Transactional
    public void getAllMAdventQuestStageRewardsByGoalRewardGroupIdIsInShouldWork() throws Exception {
        // Initialize the database
        mAdventQuestStageRewardRepository.saveAndFlush(mAdventQuestStageReward);

        // Get all the mAdventQuestStageRewardList where goalRewardGroupId in DEFAULT_GOAL_REWARD_GROUP_ID or UPDATED_GOAL_REWARD_GROUP_ID
        defaultMAdventQuestStageRewardShouldBeFound("goalRewardGroupId.in=" + DEFAULT_GOAL_REWARD_GROUP_ID + "," + UPDATED_GOAL_REWARD_GROUP_ID);

        // Get all the mAdventQuestStageRewardList where goalRewardGroupId equals to UPDATED_GOAL_REWARD_GROUP_ID
        defaultMAdventQuestStageRewardShouldNotBeFound("goalRewardGroupId.in=" + UPDATED_GOAL_REWARD_GROUP_ID);
    }

    @Test
    @Transactional
    public void getAllMAdventQuestStageRewardsByGoalRewardGroupIdIsNullOrNotNull() throws Exception {
        // Initialize the database
        mAdventQuestStageRewardRepository.saveAndFlush(mAdventQuestStageReward);

        // Get all the mAdventQuestStageRewardList where goalRewardGroupId is not null
        defaultMAdventQuestStageRewardShouldBeFound("goalRewardGroupId.specified=true");

        // Get all the mAdventQuestStageRewardList where goalRewardGroupId is null
        defaultMAdventQuestStageRewardShouldNotBeFound("goalRewardGroupId.specified=false");
    }

    @Test
    @Transactional
    public void getAllMAdventQuestStageRewardsByGoalRewardGroupIdIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mAdventQuestStageRewardRepository.saveAndFlush(mAdventQuestStageReward);

        // Get all the mAdventQuestStageRewardList where goalRewardGroupId greater than or equals to DEFAULT_GOAL_REWARD_GROUP_ID
        defaultMAdventQuestStageRewardShouldBeFound("goalRewardGroupId.greaterOrEqualThan=" + DEFAULT_GOAL_REWARD_GROUP_ID);

        // Get all the mAdventQuestStageRewardList where goalRewardGroupId greater than or equals to UPDATED_GOAL_REWARD_GROUP_ID
        defaultMAdventQuestStageRewardShouldNotBeFound("goalRewardGroupId.greaterOrEqualThan=" + UPDATED_GOAL_REWARD_GROUP_ID);
    }

    @Test
    @Transactional
    public void getAllMAdventQuestStageRewardsByGoalRewardGroupIdIsLessThanSomething() throws Exception {
        // Initialize the database
        mAdventQuestStageRewardRepository.saveAndFlush(mAdventQuestStageReward);

        // Get all the mAdventQuestStageRewardList where goalRewardGroupId less than or equals to DEFAULT_GOAL_REWARD_GROUP_ID
        defaultMAdventQuestStageRewardShouldNotBeFound("goalRewardGroupId.lessThan=" + DEFAULT_GOAL_REWARD_GROUP_ID);

        // Get all the mAdventQuestStageRewardList where goalRewardGroupId less than or equals to UPDATED_GOAL_REWARD_GROUP_ID
        defaultMAdventQuestStageRewardShouldBeFound("goalRewardGroupId.lessThan=" + UPDATED_GOAL_REWARD_GROUP_ID);
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultMAdventQuestStageRewardShouldBeFound(String filter) throws Exception {
        restMAdventQuestStageRewardMockMvc.perform(get("/api/m-advent-quest-stage-rewards?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(mAdventQuestStageReward.getId().intValue())))
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
        restMAdventQuestStageRewardMockMvc.perform(get("/api/m-advent-quest-stage-rewards/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultMAdventQuestStageRewardShouldNotBeFound(String filter) throws Exception {
        restMAdventQuestStageRewardMockMvc.perform(get("/api/m-advent-quest-stage-rewards?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restMAdventQuestStageRewardMockMvc.perform(get("/api/m-advent-quest-stage-rewards/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("0"));
    }


    @Test
    @Transactional
    public void getNonExistingMAdventQuestStageReward() throws Exception {
        // Get the mAdventQuestStageReward
        restMAdventQuestStageRewardMockMvc.perform(get("/api/m-advent-quest-stage-rewards/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateMAdventQuestStageReward() throws Exception {
        // Initialize the database
        mAdventQuestStageRewardRepository.saveAndFlush(mAdventQuestStageReward);

        int databaseSizeBeforeUpdate = mAdventQuestStageRewardRepository.findAll().size();

        // Update the mAdventQuestStageReward
        MAdventQuestStageReward updatedMAdventQuestStageReward = mAdventQuestStageRewardRepository.findById(mAdventQuestStageReward.getId()).get();
        // Disconnect from session so that the updates on updatedMAdventQuestStageReward are not directly saved in db
        em.detach(updatedMAdventQuestStageReward);
        updatedMAdventQuestStageReward
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
        MAdventQuestStageRewardDTO mAdventQuestStageRewardDTO = mAdventQuestStageRewardMapper.toDto(updatedMAdventQuestStageReward);

        restMAdventQuestStageRewardMockMvc.perform(put("/api/m-advent-quest-stage-rewards")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mAdventQuestStageRewardDTO)))
            .andExpect(status().isOk());

        // Validate the MAdventQuestStageReward in the database
        List<MAdventQuestStageReward> mAdventQuestStageRewardList = mAdventQuestStageRewardRepository.findAll();
        assertThat(mAdventQuestStageRewardList).hasSize(databaseSizeBeforeUpdate);
        MAdventQuestStageReward testMAdventQuestStageReward = mAdventQuestStageRewardList.get(mAdventQuestStageRewardList.size() - 1);
        assertThat(testMAdventQuestStageReward.getStageId()).isEqualTo(UPDATED_STAGE_ID);
        assertThat(testMAdventQuestStageReward.getExp()).isEqualTo(UPDATED_EXP);
        assertThat(testMAdventQuestStageReward.getCoin()).isEqualTo(UPDATED_COIN);
        assertThat(testMAdventQuestStageReward.getGuildPoint()).isEqualTo(UPDATED_GUILD_POINT);
        assertThat(testMAdventQuestStageReward.getClearRewardGroupId()).isEqualTo(UPDATED_CLEAR_REWARD_GROUP_ID);
        assertThat(testMAdventQuestStageReward.getClearRewardWeightId()).isEqualTo(UPDATED_CLEAR_REWARD_WEIGHT_ID);
        assertThat(testMAdventQuestStageReward.getAchievementRewardGroupId()).isEqualTo(UPDATED_ACHIEVEMENT_REWARD_GROUP_ID);
        assertThat(testMAdventQuestStageReward.getCoopGroupId()).isEqualTo(UPDATED_COOP_GROUP_ID);
        assertThat(testMAdventQuestStageReward.getSpecialRewardGroupId()).isEqualTo(UPDATED_SPECIAL_REWARD_GROUP_ID);
        assertThat(testMAdventQuestStageReward.getSpecialRewardAmount()).isEqualTo(UPDATED_SPECIAL_REWARD_AMOUNT);
        assertThat(testMAdventQuestStageReward.getGoalRewardGroupId()).isEqualTo(UPDATED_GOAL_REWARD_GROUP_ID);
    }

    @Test
    @Transactional
    public void updateNonExistingMAdventQuestStageReward() throws Exception {
        int databaseSizeBeforeUpdate = mAdventQuestStageRewardRepository.findAll().size();

        // Create the MAdventQuestStageReward
        MAdventQuestStageRewardDTO mAdventQuestStageRewardDTO = mAdventQuestStageRewardMapper.toDto(mAdventQuestStageReward);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restMAdventQuestStageRewardMockMvc.perform(put("/api/m-advent-quest-stage-rewards")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mAdventQuestStageRewardDTO)))
            .andExpect(status().isBadRequest());

        // Validate the MAdventQuestStageReward in the database
        List<MAdventQuestStageReward> mAdventQuestStageRewardList = mAdventQuestStageRewardRepository.findAll();
        assertThat(mAdventQuestStageRewardList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteMAdventQuestStageReward() throws Exception {
        // Initialize the database
        mAdventQuestStageRewardRepository.saveAndFlush(mAdventQuestStageReward);

        int databaseSizeBeforeDelete = mAdventQuestStageRewardRepository.findAll().size();

        // Delete the mAdventQuestStageReward
        restMAdventQuestStageRewardMockMvc.perform(delete("/api/m-advent-quest-stage-rewards/{id}", mAdventQuestStageReward.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database is empty
        List<MAdventQuestStageReward> mAdventQuestStageRewardList = mAdventQuestStageRewardRepository.findAll();
        assertThat(mAdventQuestStageRewardList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(MAdventQuestStageReward.class);
        MAdventQuestStageReward mAdventQuestStageReward1 = new MAdventQuestStageReward();
        mAdventQuestStageReward1.setId(1L);
        MAdventQuestStageReward mAdventQuestStageReward2 = new MAdventQuestStageReward();
        mAdventQuestStageReward2.setId(mAdventQuestStageReward1.getId());
        assertThat(mAdventQuestStageReward1).isEqualTo(mAdventQuestStageReward2);
        mAdventQuestStageReward2.setId(2L);
        assertThat(mAdventQuestStageReward1).isNotEqualTo(mAdventQuestStageReward2);
        mAdventQuestStageReward1.setId(null);
        assertThat(mAdventQuestStageReward1).isNotEqualTo(mAdventQuestStageReward2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(MAdventQuestStageRewardDTO.class);
        MAdventQuestStageRewardDTO mAdventQuestStageRewardDTO1 = new MAdventQuestStageRewardDTO();
        mAdventQuestStageRewardDTO1.setId(1L);
        MAdventQuestStageRewardDTO mAdventQuestStageRewardDTO2 = new MAdventQuestStageRewardDTO();
        assertThat(mAdventQuestStageRewardDTO1).isNotEqualTo(mAdventQuestStageRewardDTO2);
        mAdventQuestStageRewardDTO2.setId(mAdventQuestStageRewardDTO1.getId());
        assertThat(mAdventQuestStageRewardDTO1).isEqualTo(mAdventQuestStageRewardDTO2);
        mAdventQuestStageRewardDTO2.setId(2L);
        assertThat(mAdventQuestStageRewardDTO1).isNotEqualTo(mAdventQuestStageRewardDTO2);
        mAdventQuestStageRewardDTO1.setId(null);
        assertThat(mAdventQuestStageRewardDTO1).isNotEqualTo(mAdventQuestStageRewardDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(mAdventQuestStageRewardMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(mAdventQuestStageRewardMapper.fromId(null)).isNull();
    }
}
