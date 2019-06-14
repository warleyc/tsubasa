package io.shm.tsubasa.web.rest;

import io.shm.tsubasa.TsubasaApp;
import io.shm.tsubasa.domain.MWeeklyQuestStageReward;
import io.shm.tsubasa.repository.MWeeklyQuestStageRewardRepository;
import io.shm.tsubasa.service.MWeeklyQuestStageRewardService;
import io.shm.tsubasa.service.dto.MWeeklyQuestStageRewardDTO;
import io.shm.tsubasa.service.mapper.MWeeklyQuestStageRewardMapper;
import io.shm.tsubasa.web.rest.errors.ExceptionTranslator;
import io.shm.tsubasa.service.dto.MWeeklyQuestStageRewardCriteria;
import io.shm.tsubasa.service.MWeeklyQuestStageRewardQueryService;

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
 * Integration tests for the {@Link MWeeklyQuestStageRewardResource} REST controller.
 */
@SpringBootTest(classes = TsubasaApp.class)
public class MWeeklyQuestStageRewardResourceIT {

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
    private MWeeklyQuestStageRewardRepository mWeeklyQuestStageRewardRepository;

    @Autowired
    private MWeeklyQuestStageRewardMapper mWeeklyQuestStageRewardMapper;

    @Autowired
    private MWeeklyQuestStageRewardService mWeeklyQuestStageRewardService;

    @Autowired
    private MWeeklyQuestStageRewardQueryService mWeeklyQuestStageRewardQueryService;

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

    private MockMvc restMWeeklyQuestStageRewardMockMvc;

    private MWeeklyQuestStageReward mWeeklyQuestStageReward;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final MWeeklyQuestStageRewardResource mWeeklyQuestStageRewardResource = new MWeeklyQuestStageRewardResource(mWeeklyQuestStageRewardService, mWeeklyQuestStageRewardQueryService);
        this.restMWeeklyQuestStageRewardMockMvc = MockMvcBuilders.standaloneSetup(mWeeklyQuestStageRewardResource)
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
    public static MWeeklyQuestStageReward createEntity(EntityManager em) {
        MWeeklyQuestStageReward mWeeklyQuestStageReward = new MWeeklyQuestStageReward()
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
        return mWeeklyQuestStageReward;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static MWeeklyQuestStageReward createUpdatedEntity(EntityManager em) {
        MWeeklyQuestStageReward mWeeklyQuestStageReward = new MWeeklyQuestStageReward()
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
        return mWeeklyQuestStageReward;
    }

    @BeforeEach
    public void initTest() {
        mWeeklyQuestStageReward = createEntity(em);
    }

    @Test
    @Transactional
    public void createMWeeklyQuestStageReward() throws Exception {
        int databaseSizeBeforeCreate = mWeeklyQuestStageRewardRepository.findAll().size();

        // Create the MWeeklyQuestStageReward
        MWeeklyQuestStageRewardDTO mWeeklyQuestStageRewardDTO = mWeeklyQuestStageRewardMapper.toDto(mWeeklyQuestStageReward);
        restMWeeklyQuestStageRewardMockMvc.perform(post("/api/m-weekly-quest-stage-rewards")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mWeeklyQuestStageRewardDTO)))
            .andExpect(status().isCreated());

        // Validate the MWeeklyQuestStageReward in the database
        List<MWeeklyQuestStageReward> mWeeklyQuestStageRewardList = mWeeklyQuestStageRewardRepository.findAll();
        assertThat(mWeeklyQuestStageRewardList).hasSize(databaseSizeBeforeCreate + 1);
        MWeeklyQuestStageReward testMWeeklyQuestStageReward = mWeeklyQuestStageRewardList.get(mWeeklyQuestStageRewardList.size() - 1);
        assertThat(testMWeeklyQuestStageReward.getStageId()).isEqualTo(DEFAULT_STAGE_ID);
        assertThat(testMWeeklyQuestStageReward.getExp()).isEqualTo(DEFAULT_EXP);
        assertThat(testMWeeklyQuestStageReward.getCoin()).isEqualTo(DEFAULT_COIN);
        assertThat(testMWeeklyQuestStageReward.getGuildPoint()).isEqualTo(DEFAULT_GUILD_POINT);
        assertThat(testMWeeklyQuestStageReward.getClearRewardGroupId()).isEqualTo(DEFAULT_CLEAR_REWARD_GROUP_ID);
        assertThat(testMWeeklyQuestStageReward.getClearRewardWeightId()).isEqualTo(DEFAULT_CLEAR_REWARD_WEIGHT_ID);
        assertThat(testMWeeklyQuestStageReward.getAchievementRewardGroupId()).isEqualTo(DEFAULT_ACHIEVEMENT_REWARD_GROUP_ID);
        assertThat(testMWeeklyQuestStageReward.getCoopGroupId()).isEqualTo(DEFAULT_COOP_GROUP_ID);
        assertThat(testMWeeklyQuestStageReward.getSpecialRewardGroupId()).isEqualTo(DEFAULT_SPECIAL_REWARD_GROUP_ID);
        assertThat(testMWeeklyQuestStageReward.getSpecialRewardAmount()).isEqualTo(DEFAULT_SPECIAL_REWARD_AMOUNT);
        assertThat(testMWeeklyQuestStageReward.getGoalRewardGroupId()).isEqualTo(DEFAULT_GOAL_REWARD_GROUP_ID);
    }

    @Test
    @Transactional
    public void createMWeeklyQuestStageRewardWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = mWeeklyQuestStageRewardRepository.findAll().size();

        // Create the MWeeklyQuestStageReward with an existing ID
        mWeeklyQuestStageReward.setId(1L);
        MWeeklyQuestStageRewardDTO mWeeklyQuestStageRewardDTO = mWeeklyQuestStageRewardMapper.toDto(mWeeklyQuestStageReward);

        // An entity with an existing ID cannot be created, so this API call must fail
        restMWeeklyQuestStageRewardMockMvc.perform(post("/api/m-weekly-quest-stage-rewards")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mWeeklyQuestStageRewardDTO)))
            .andExpect(status().isBadRequest());

        // Validate the MWeeklyQuestStageReward in the database
        List<MWeeklyQuestStageReward> mWeeklyQuestStageRewardList = mWeeklyQuestStageRewardRepository.findAll();
        assertThat(mWeeklyQuestStageRewardList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkStageIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = mWeeklyQuestStageRewardRepository.findAll().size();
        // set the field null
        mWeeklyQuestStageReward.setStageId(null);

        // Create the MWeeklyQuestStageReward, which fails.
        MWeeklyQuestStageRewardDTO mWeeklyQuestStageRewardDTO = mWeeklyQuestStageRewardMapper.toDto(mWeeklyQuestStageReward);

        restMWeeklyQuestStageRewardMockMvc.perform(post("/api/m-weekly-quest-stage-rewards")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mWeeklyQuestStageRewardDTO)))
            .andExpect(status().isBadRequest());

        List<MWeeklyQuestStageReward> mWeeklyQuestStageRewardList = mWeeklyQuestStageRewardRepository.findAll();
        assertThat(mWeeklyQuestStageRewardList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkExpIsRequired() throws Exception {
        int databaseSizeBeforeTest = mWeeklyQuestStageRewardRepository.findAll().size();
        // set the field null
        mWeeklyQuestStageReward.setExp(null);

        // Create the MWeeklyQuestStageReward, which fails.
        MWeeklyQuestStageRewardDTO mWeeklyQuestStageRewardDTO = mWeeklyQuestStageRewardMapper.toDto(mWeeklyQuestStageReward);

        restMWeeklyQuestStageRewardMockMvc.perform(post("/api/m-weekly-quest-stage-rewards")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mWeeklyQuestStageRewardDTO)))
            .andExpect(status().isBadRequest());

        List<MWeeklyQuestStageReward> mWeeklyQuestStageRewardList = mWeeklyQuestStageRewardRepository.findAll();
        assertThat(mWeeklyQuestStageRewardList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkCoinIsRequired() throws Exception {
        int databaseSizeBeforeTest = mWeeklyQuestStageRewardRepository.findAll().size();
        // set the field null
        mWeeklyQuestStageReward.setCoin(null);

        // Create the MWeeklyQuestStageReward, which fails.
        MWeeklyQuestStageRewardDTO mWeeklyQuestStageRewardDTO = mWeeklyQuestStageRewardMapper.toDto(mWeeklyQuestStageReward);

        restMWeeklyQuestStageRewardMockMvc.perform(post("/api/m-weekly-quest-stage-rewards")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mWeeklyQuestStageRewardDTO)))
            .andExpect(status().isBadRequest());

        List<MWeeklyQuestStageReward> mWeeklyQuestStageRewardList = mWeeklyQuestStageRewardRepository.findAll();
        assertThat(mWeeklyQuestStageRewardList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkGuildPointIsRequired() throws Exception {
        int databaseSizeBeforeTest = mWeeklyQuestStageRewardRepository.findAll().size();
        // set the field null
        mWeeklyQuestStageReward.setGuildPoint(null);

        // Create the MWeeklyQuestStageReward, which fails.
        MWeeklyQuestStageRewardDTO mWeeklyQuestStageRewardDTO = mWeeklyQuestStageRewardMapper.toDto(mWeeklyQuestStageReward);

        restMWeeklyQuestStageRewardMockMvc.perform(post("/api/m-weekly-quest-stage-rewards")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mWeeklyQuestStageRewardDTO)))
            .andExpect(status().isBadRequest());

        List<MWeeklyQuestStageReward> mWeeklyQuestStageRewardList = mWeeklyQuestStageRewardRepository.findAll();
        assertThat(mWeeklyQuestStageRewardList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkClearRewardGroupIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = mWeeklyQuestStageRewardRepository.findAll().size();
        // set the field null
        mWeeklyQuestStageReward.setClearRewardGroupId(null);

        // Create the MWeeklyQuestStageReward, which fails.
        MWeeklyQuestStageRewardDTO mWeeklyQuestStageRewardDTO = mWeeklyQuestStageRewardMapper.toDto(mWeeklyQuestStageReward);

        restMWeeklyQuestStageRewardMockMvc.perform(post("/api/m-weekly-quest-stage-rewards")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mWeeklyQuestStageRewardDTO)))
            .andExpect(status().isBadRequest());

        List<MWeeklyQuestStageReward> mWeeklyQuestStageRewardList = mWeeklyQuestStageRewardRepository.findAll();
        assertThat(mWeeklyQuestStageRewardList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkClearRewardWeightIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = mWeeklyQuestStageRewardRepository.findAll().size();
        // set the field null
        mWeeklyQuestStageReward.setClearRewardWeightId(null);

        // Create the MWeeklyQuestStageReward, which fails.
        MWeeklyQuestStageRewardDTO mWeeklyQuestStageRewardDTO = mWeeklyQuestStageRewardMapper.toDto(mWeeklyQuestStageReward);

        restMWeeklyQuestStageRewardMockMvc.perform(post("/api/m-weekly-quest-stage-rewards")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mWeeklyQuestStageRewardDTO)))
            .andExpect(status().isBadRequest());

        List<MWeeklyQuestStageReward> mWeeklyQuestStageRewardList = mWeeklyQuestStageRewardRepository.findAll();
        assertThat(mWeeklyQuestStageRewardList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkAchievementRewardGroupIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = mWeeklyQuestStageRewardRepository.findAll().size();
        // set the field null
        mWeeklyQuestStageReward.setAchievementRewardGroupId(null);

        // Create the MWeeklyQuestStageReward, which fails.
        MWeeklyQuestStageRewardDTO mWeeklyQuestStageRewardDTO = mWeeklyQuestStageRewardMapper.toDto(mWeeklyQuestStageReward);

        restMWeeklyQuestStageRewardMockMvc.perform(post("/api/m-weekly-quest-stage-rewards")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mWeeklyQuestStageRewardDTO)))
            .andExpect(status().isBadRequest());

        List<MWeeklyQuestStageReward> mWeeklyQuestStageRewardList = mWeeklyQuestStageRewardRepository.findAll();
        assertThat(mWeeklyQuestStageRewardList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkCoopGroupIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = mWeeklyQuestStageRewardRepository.findAll().size();
        // set the field null
        mWeeklyQuestStageReward.setCoopGroupId(null);

        // Create the MWeeklyQuestStageReward, which fails.
        MWeeklyQuestStageRewardDTO mWeeklyQuestStageRewardDTO = mWeeklyQuestStageRewardMapper.toDto(mWeeklyQuestStageReward);

        restMWeeklyQuestStageRewardMockMvc.perform(post("/api/m-weekly-quest-stage-rewards")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mWeeklyQuestStageRewardDTO)))
            .andExpect(status().isBadRequest());

        List<MWeeklyQuestStageReward> mWeeklyQuestStageRewardList = mWeeklyQuestStageRewardRepository.findAll();
        assertThat(mWeeklyQuestStageRewardList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkSpecialRewardAmountIsRequired() throws Exception {
        int databaseSizeBeforeTest = mWeeklyQuestStageRewardRepository.findAll().size();
        // set the field null
        mWeeklyQuestStageReward.setSpecialRewardAmount(null);

        // Create the MWeeklyQuestStageReward, which fails.
        MWeeklyQuestStageRewardDTO mWeeklyQuestStageRewardDTO = mWeeklyQuestStageRewardMapper.toDto(mWeeklyQuestStageReward);

        restMWeeklyQuestStageRewardMockMvc.perform(post("/api/m-weekly-quest-stage-rewards")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mWeeklyQuestStageRewardDTO)))
            .andExpect(status().isBadRequest());

        List<MWeeklyQuestStageReward> mWeeklyQuestStageRewardList = mWeeklyQuestStageRewardRepository.findAll();
        assertThat(mWeeklyQuestStageRewardList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllMWeeklyQuestStageRewards() throws Exception {
        // Initialize the database
        mWeeklyQuestStageRewardRepository.saveAndFlush(mWeeklyQuestStageReward);

        // Get all the mWeeklyQuestStageRewardList
        restMWeeklyQuestStageRewardMockMvc.perform(get("/api/m-weekly-quest-stage-rewards?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(mWeeklyQuestStageReward.getId().intValue())))
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
    public void getMWeeklyQuestStageReward() throws Exception {
        // Initialize the database
        mWeeklyQuestStageRewardRepository.saveAndFlush(mWeeklyQuestStageReward);

        // Get the mWeeklyQuestStageReward
        restMWeeklyQuestStageRewardMockMvc.perform(get("/api/m-weekly-quest-stage-rewards/{id}", mWeeklyQuestStageReward.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(mWeeklyQuestStageReward.getId().intValue()))
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
    public void getAllMWeeklyQuestStageRewardsByStageIdIsEqualToSomething() throws Exception {
        // Initialize the database
        mWeeklyQuestStageRewardRepository.saveAndFlush(mWeeklyQuestStageReward);

        // Get all the mWeeklyQuestStageRewardList where stageId equals to DEFAULT_STAGE_ID
        defaultMWeeklyQuestStageRewardShouldBeFound("stageId.equals=" + DEFAULT_STAGE_ID);

        // Get all the mWeeklyQuestStageRewardList where stageId equals to UPDATED_STAGE_ID
        defaultMWeeklyQuestStageRewardShouldNotBeFound("stageId.equals=" + UPDATED_STAGE_ID);
    }

    @Test
    @Transactional
    public void getAllMWeeklyQuestStageRewardsByStageIdIsInShouldWork() throws Exception {
        // Initialize the database
        mWeeklyQuestStageRewardRepository.saveAndFlush(mWeeklyQuestStageReward);

        // Get all the mWeeklyQuestStageRewardList where stageId in DEFAULT_STAGE_ID or UPDATED_STAGE_ID
        defaultMWeeklyQuestStageRewardShouldBeFound("stageId.in=" + DEFAULT_STAGE_ID + "," + UPDATED_STAGE_ID);

        // Get all the mWeeklyQuestStageRewardList where stageId equals to UPDATED_STAGE_ID
        defaultMWeeklyQuestStageRewardShouldNotBeFound("stageId.in=" + UPDATED_STAGE_ID);
    }

    @Test
    @Transactional
    public void getAllMWeeklyQuestStageRewardsByStageIdIsNullOrNotNull() throws Exception {
        // Initialize the database
        mWeeklyQuestStageRewardRepository.saveAndFlush(mWeeklyQuestStageReward);

        // Get all the mWeeklyQuestStageRewardList where stageId is not null
        defaultMWeeklyQuestStageRewardShouldBeFound("stageId.specified=true");

        // Get all the mWeeklyQuestStageRewardList where stageId is null
        defaultMWeeklyQuestStageRewardShouldNotBeFound("stageId.specified=false");
    }

    @Test
    @Transactional
    public void getAllMWeeklyQuestStageRewardsByStageIdIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mWeeklyQuestStageRewardRepository.saveAndFlush(mWeeklyQuestStageReward);

        // Get all the mWeeklyQuestStageRewardList where stageId greater than or equals to DEFAULT_STAGE_ID
        defaultMWeeklyQuestStageRewardShouldBeFound("stageId.greaterOrEqualThan=" + DEFAULT_STAGE_ID);

        // Get all the mWeeklyQuestStageRewardList where stageId greater than or equals to UPDATED_STAGE_ID
        defaultMWeeklyQuestStageRewardShouldNotBeFound("stageId.greaterOrEqualThan=" + UPDATED_STAGE_ID);
    }

    @Test
    @Transactional
    public void getAllMWeeklyQuestStageRewardsByStageIdIsLessThanSomething() throws Exception {
        // Initialize the database
        mWeeklyQuestStageRewardRepository.saveAndFlush(mWeeklyQuestStageReward);

        // Get all the mWeeklyQuestStageRewardList where stageId less than or equals to DEFAULT_STAGE_ID
        defaultMWeeklyQuestStageRewardShouldNotBeFound("stageId.lessThan=" + DEFAULT_STAGE_ID);

        // Get all the mWeeklyQuestStageRewardList where stageId less than or equals to UPDATED_STAGE_ID
        defaultMWeeklyQuestStageRewardShouldBeFound("stageId.lessThan=" + UPDATED_STAGE_ID);
    }


    @Test
    @Transactional
    public void getAllMWeeklyQuestStageRewardsByExpIsEqualToSomething() throws Exception {
        // Initialize the database
        mWeeklyQuestStageRewardRepository.saveAndFlush(mWeeklyQuestStageReward);

        // Get all the mWeeklyQuestStageRewardList where exp equals to DEFAULT_EXP
        defaultMWeeklyQuestStageRewardShouldBeFound("exp.equals=" + DEFAULT_EXP);

        // Get all the mWeeklyQuestStageRewardList where exp equals to UPDATED_EXP
        defaultMWeeklyQuestStageRewardShouldNotBeFound("exp.equals=" + UPDATED_EXP);
    }

    @Test
    @Transactional
    public void getAllMWeeklyQuestStageRewardsByExpIsInShouldWork() throws Exception {
        // Initialize the database
        mWeeklyQuestStageRewardRepository.saveAndFlush(mWeeklyQuestStageReward);

        // Get all the mWeeklyQuestStageRewardList where exp in DEFAULT_EXP or UPDATED_EXP
        defaultMWeeklyQuestStageRewardShouldBeFound("exp.in=" + DEFAULT_EXP + "," + UPDATED_EXP);

        // Get all the mWeeklyQuestStageRewardList where exp equals to UPDATED_EXP
        defaultMWeeklyQuestStageRewardShouldNotBeFound("exp.in=" + UPDATED_EXP);
    }

    @Test
    @Transactional
    public void getAllMWeeklyQuestStageRewardsByExpIsNullOrNotNull() throws Exception {
        // Initialize the database
        mWeeklyQuestStageRewardRepository.saveAndFlush(mWeeklyQuestStageReward);

        // Get all the mWeeklyQuestStageRewardList where exp is not null
        defaultMWeeklyQuestStageRewardShouldBeFound("exp.specified=true");

        // Get all the mWeeklyQuestStageRewardList where exp is null
        defaultMWeeklyQuestStageRewardShouldNotBeFound("exp.specified=false");
    }

    @Test
    @Transactional
    public void getAllMWeeklyQuestStageRewardsByExpIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mWeeklyQuestStageRewardRepository.saveAndFlush(mWeeklyQuestStageReward);

        // Get all the mWeeklyQuestStageRewardList where exp greater than or equals to DEFAULT_EXP
        defaultMWeeklyQuestStageRewardShouldBeFound("exp.greaterOrEqualThan=" + DEFAULT_EXP);

        // Get all the mWeeklyQuestStageRewardList where exp greater than or equals to UPDATED_EXP
        defaultMWeeklyQuestStageRewardShouldNotBeFound("exp.greaterOrEqualThan=" + UPDATED_EXP);
    }

    @Test
    @Transactional
    public void getAllMWeeklyQuestStageRewardsByExpIsLessThanSomething() throws Exception {
        // Initialize the database
        mWeeklyQuestStageRewardRepository.saveAndFlush(mWeeklyQuestStageReward);

        // Get all the mWeeklyQuestStageRewardList where exp less than or equals to DEFAULT_EXP
        defaultMWeeklyQuestStageRewardShouldNotBeFound("exp.lessThan=" + DEFAULT_EXP);

        // Get all the mWeeklyQuestStageRewardList where exp less than or equals to UPDATED_EXP
        defaultMWeeklyQuestStageRewardShouldBeFound("exp.lessThan=" + UPDATED_EXP);
    }


    @Test
    @Transactional
    public void getAllMWeeklyQuestStageRewardsByCoinIsEqualToSomething() throws Exception {
        // Initialize the database
        mWeeklyQuestStageRewardRepository.saveAndFlush(mWeeklyQuestStageReward);

        // Get all the mWeeklyQuestStageRewardList where coin equals to DEFAULT_COIN
        defaultMWeeklyQuestStageRewardShouldBeFound("coin.equals=" + DEFAULT_COIN);

        // Get all the mWeeklyQuestStageRewardList where coin equals to UPDATED_COIN
        defaultMWeeklyQuestStageRewardShouldNotBeFound("coin.equals=" + UPDATED_COIN);
    }

    @Test
    @Transactional
    public void getAllMWeeklyQuestStageRewardsByCoinIsInShouldWork() throws Exception {
        // Initialize the database
        mWeeklyQuestStageRewardRepository.saveAndFlush(mWeeklyQuestStageReward);

        // Get all the mWeeklyQuestStageRewardList where coin in DEFAULT_COIN or UPDATED_COIN
        defaultMWeeklyQuestStageRewardShouldBeFound("coin.in=" + DEFAULT_COIN + "," + UPDATED_COIN);

        // Get all the mWeeklyQuestStageRewardList where coin equals to UPDATED_COIN
        defaultMWeeklyQuestStageRewardShouldNotBeFound("coin.in=" + UPDATED_COIN);
    }

    @Test
    @Transactional
    public void getAllMWeeklyQuestStageRewardsByCoinIsNullOrNotNull() throws Exception {
        // Initialize the database
        mWeeklyQuestStageRewardRepository.saveAndFlush(mWeeklyQuestStageReward);

        // Get all the mWeeklyQuestStageRewardList where coin is not null
        defaultMWeeklyQuestStageRewardShouldBeFound("coin.specified=true");

        // Get all the mWeeklyQuestStageRewardList where coin is null
        defaultMWeeklyQuestStageRewardShouldNotBeFound("coin.specified=false");
    }

    @Test
    @Transactional
    public void getAllMWeeklyQuestStageRewardsByCoinIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mWeeklyQuestStageRewardRepository.saveAndFlush(mWeeklyQuestStageReward);

        // Get all the mWeeklyQuestStageRewardList where coin greater than or equals to DEFAULT_COIN
        defaultMWeeklyQuestStageRewardShouldBeFound("coin.greaterOrEqualThan=" + DEFAULT_COIN);

        // Get all the mWeeklyQuestStageRewardList where coin greater than or equals to UPDATED_COIN
        defaultMWeeklyQuestStageRewardShouldNotBeFound("coin.greaterOrEqualThan=" + UPDATED_COIN);
    }

    @Test
    @Transactional
    public void getAllMWeeklyQuestStageRewardsByCoinIsLessThanSomething() throws Exception {
        // Initialize the database
        mWeeklyQuestStageRewardRepository.saveAndFlush(mWeeklyQuestStageReward);

        // Get all the mWeeklyQuestStageRewardList where coin less than or equals to DEFAULT_COIN
        defaultMWeeklyQuestStageRewardShouldNotBeFound("coin.lessThan=" + DEFAULT_COIN);

        // Get all the mWeeklyQuestStageRewardList where coin less than or equals to UPDATED_COIN
        defaultMWeeklyQuestStageRewardShouldBeFound("coin.lessThan=" + UPDATED_COIN);
    }


    @Test
    @Transactional
    public void getAllMWeeklyQuestStageRewardsByGuildPointIsEqualToSomething() throws Exception {
        // Initialize the database
        mWeeklyQuestStageRewardRepository.saveAndFlush(mWeeklyQuestStageReward);

        // Get all the mWeeklyQuestStageRewardList where guildPoint equals to DEFAULT_GUILD_POINT
        defaultMWeeklyQuestStageRewardShouldBeFound("guildPoint.equals=" + DEFAULT_GUILD_POINT);

        // Get all the mWeeklyQuestStageRewardList where guildPoint equals to UPDATED_GUILD_POINT
        defaultMWeeklyQuestStageRewardShouldNotBeFound("guildPoint.equals=" + UPDATED_GUILD_POINT);
    }

    @Test
    @Transactional
    public void getAllMWeeklyQuestStageRewardsByGuildPointIsInShouldWork() throws Exception {
        // Initialize the database
        mWeeklyQuestStageRewardRepository.saveAndFlush(mWeeklyQuestStageReward);

        // Get all the mWeeklyQuestStageRewardList where guildPoint in DEFAULT_GUILD_POINT or UPDATED_GUILD_POINT
        defaultMWeeklyQuestStageRewardShouldBeFound("guildPoint.in=" + DEFAULT_GUILD_POINT + "," + UPDATED_GUILD_POINT);

        // Get all the mWeeklyQuestStageRewardList where guildPoint equals to UPDATED_GUILD_POINT
        defaultMWeeklyQuestStageRewardShouldNotBeFound("guildPoint.in=" + UPDATED_GUILD_POINT);
    }

    @Test
    @Transactional
    public void getAllMWeeklyQuestStageRewardsByGuildPointIsNullOrNotNull() throws Exception {
        // Initialize the database
        mWeeklyQuestStageRewardRepository.saveAndFlush(mWeeklyQuestStageReward);

        // Get all the mWeeklyQuestStageRewardList where guildPoint is not null
        defaultMWeeklyQuestStageRewardShouldBeFound("guildPoint.specified=true");

        // Get all the mWeeklyQuestStageRewardList where guildPoint is null
        defaultMWeeklyQuestStageRewardShouldNotBeFound("guildPoint.specified=false");
    }

    @Test
    @Transactional
    public void getAllMWeeklyQuestStageRewardsByGuildPointIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mWeeklyQuestStageRewardRepository.saveAndFlush(mWeeklyQuestStageReward);

        // Get all the mWeeklyQuestStageRewardList where guildPoint greater than or equals to DEFAULT_GUILD_POINT
        defaultMWeeklyQuestStageRewardShouldBeFound("guildPoint.greaterOrEqualThan=" + DEFAULT_GUILD_POINT);

        // Get all the mWeeklyQuestStageRewardList where guildPoint greater than or equals to UPDATED_GUILD_POINT
        defaultMWeeklyQuestStageRewardShouldNotBeFound("guildPoint.greaterOrEqualThan=" + UPDATED_GUILD_POINT);
    }

    @Test
    @Transactional
    public void getAllMWeeklyQuestStageRewardsByGuildPointIsLessThanSomething() throws Exception {
        // Initialize the database
        mWeeklyQuestStageRewardRepository.saveAndFlush(mWeeklyQuestStageReward);

        // Get all the mWeeklyQuestStageRewardList where guildPoint less than or equals to DEFAULT_GUILD_POINT
        defaultMWeeklyQuestStageRewardShouldNotBeFound("guildPoint.lessThan=" + DEFAULT_GUILD_POINT);

        // Get all the mWeeklyQuestStageRewardList where guildPoint less than or equals to UPDATED_GUILD_POINT
        defaultMWeeklyQuestStageRewardShouldBeFound("guildPoint.lessThan=" + UPDATED_GUILD_POINT);
    }


    @Test
    @Transactional
    public void getAllMWeeklyQuestStageRewardsByClearRewardGroupIdIsEqualToSomething() throws Exception {
        // Initialize the database
        mWeeklyQuestStageRewardRepository.saveAndFlush(mWeeklyQuestStageReward);

        // Get all the mWeeklyQuestStageRewardList where clearRewardGroupId equals to DEFAULT_CLEAR_REWARD_GROUP_ID
        defaultMWeeklyQuestStageRewardShouldBeFound("clearRewardGroupId.equals=" + DEFAULT_CLEAR_REWARD_GROUP_ID);

        // Get all the mWeeklyQuestStageRewardList where clearRewardGroupId equals to UPDATED_CLEAR_REWARD_GROUP_ID
        defaultMWeeklyQuestStageRewardShouldNotBeFound("clearRewardGroupId.equals=" + UPDATED_CLEAR_REWARD_GROUP_ID);
    }

    @Test
    @Transactional
    public void getAllMWeeklyQuestStageRewardsByClearRewardGroupIdIsInShouldWork() throws Exception {
        // Initialize the database
        mWeeklyQuestStageRewardRepository.saveAndFlush(mWeeklyQuestStageReward);

        // Get all the mWeeklyQuestStageRewardList where clearRewardGroupId in DEFAULT_CLEAR_REWARD_GROUP_ID or UPDATED_CLEAR_REWARD_GROUP_ID
        defaultMWeeklyQuestStageRewardShouldBeFound("clearRewardGroupId.in=" + DEFAULT_CLEAR_REWARD_GROUP_ID + "," + UPDATED_CLEAR_REWARD_GROUP_ID);

        // Get all the mWeeklyQuestStageRewardList where clearRewardGroupId equals to UPDATED_CLEAR_REWARD_GROUP_ID
        defaultMWeeklyQuestStageRewardShouldNotBeFound("clearRewardGroupId.in=" + UPDATED_CLEAR_REWARD_GROUP_ID);
    }

    @Test
    @Transactional
    public void getAllMWeeklyQuestStageRewardsByClearRewardGroupIdIsNullOrNotNull() throws Exception {
        // Initialize the database
        mWeeklyQuestStageRewardRepository.saveAndFlush(mWeeklyQuestStageReward);

        // Get all the mWeeklyQuestStageRewardList where clearRewardGroupId is not null
        defaultMWeeklyQuestStageRewardShouldBeFound("clearRewardGroupId.specified=true");

        // Get all the mWeeklyQuestStageRewardList where clearRewardGroupId is null
        defaultMWeeklyQuestStageRewardShouldNotBeFound("clearRewardGroupId.specified=false");
    }

    @Test
    @Transactional
    public void getAllMWeeklyQuestStageRewardsByClearRewardGroupIdIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mWeeklyQuestStageRewardRepository.saveAndFlush(mWeeklyQuestStageReward);

        // Get all the mWeeklyQuestStageRewardList where clearRewardGroupId greater than or equals to DEFAULT_CLEAR_REWARD_GROUP_ID
        defaultMWeeklyQuestStageRewardShouldBeFound("clearRewardGroupId.greaterOrEqualThan=" + DEFAULT_CLEAR_REWARD_GROUP_ID);

        // Get all the mWeeklyQuestStageRewardList where clearRewardGroupId greater than or equals to UPDATED_CLEAR_REWARD_GROUP_ID
        defaultMWeeklyQuestStageRewardShouldNotBeFound("clearRewardGroupId.greaterOrEqualThan=" + UPDATED_CLEAR_REWARD_GROUP_ID);
    }

    @Test
    @Transactional
    public void getAllMWeeklyQuestStageRewardsByClearRewardGroupIdIsLessThanSomething() throws Exception {
        // Initialize the database
        mWeeklyQuestStageRewardRepository.saveAndFlush(mWeeklyQuestStageReward);

        // Get all the mWeeklyQuestStageRewardList where clearRewardGroupId less than or equals to DEFAULT_CLEAR_REWARD_GROUP_ID
        defaultMWeeklyQuestStageRewardShouldNotBeFound("clearRewardGroupId.lessThan=" + DEFAULT_CLEAR_REWARD_GROUP_ID);

        // Get all the mWeeklyQuestStageRewardList where clearRewardGroupId less than or equals to UPDATED_CLEAR_REWARD_GROUP_ID
        defaultMWeeklyQuestStageRewardShouldBeFound("clearRewardGroupId.lessThan=" + UPDATED_CLEAR_REWARD_GROUP_ID);
    }


    @Test
    @Transactional
    public void getAllMWeeklyQuestStageRewardsByClearRewardWeightIdIsEqualToSomething() throws Exception {
        // Initialize the database
        mWeeklyQuestStageRewardRepository.saveAndFlush(mWeeklyQuestStageReward);

        // Get all the mWeeklyQuestStageRewardList where clearRewardWeightId equals to DEFAULT_CLEAR_REWARD_WEIGHT_ID
        defaultMWeeklyQuestStageRewardShouldBeFound("clearRewardWeightId.equals=" + DEFAULT_CLEAR_REWARD_WEIGHT_ID);

        // Get all the mWeeklyQuestStageRewardList where clearRewardWeightId equals to UPDATED_CLEAR_REWARD_WEIGHT_ID
        defaultMWeeklyQuestStageRewardShouldNotBeFound("clearRewardWeightId.equals=" + UPDATED_CLEAR_REWARD_WEIGHT_ID);
    }

    @Test
    @Transactional
    public void getAllMWeeklyQuestStageRewardsByClearRewardWeightIdIsInShouldWork() throws Exception {
        // Initialize the database
        mWeeklyQuestStageRewardRepository.saveAndFlush(mWeeklyQuestStageReward);

        // Get all the mWeeklyQuestStageRewardList where clearRewardWeightId in DEFAULT_CLEAR_REWARD_WEIGHT_ID or UPDATED_CLEAR_REWARD_WEIGHT_ID
        defaultMWeeklyQuestStageRewardShouldBeFound("clearRewardWeightId.in=" + DEFAULT_CLEAR_REWARD_WEIGHT_ID + "," + UPDATED_CLEAR_REWARD_WEIGHT_ID);

        // Get all the mWeeklyQuestStageRewardList where clearRewardWeightId equals to UPDATED_CLEAR_REWARD_WEIGHT_ID
        defaultMWeeklyQuestStageRewardShouldNotBeFound("clearRewardWeightId.in=" + UPDATED_CLEAR_REWARD_WEIGHT_ID);
    }

    @Test
    @Transactional
    public void getAllMWeeklyQuestStageRewardsByClearRewardWeightIdIsNullOrNotNull() throws Exception {
        // Initialize the database
        mWeeklyQuestStageRewardRepository.saveAndFlush(mWeeklyQuestStageReward);

        // Get all the mWeeklyQuestStageRewardList where clearRewardWeightId is not null
        defaultMWeeklyQuestStageRewardShouldBeFound("clearRewardWeightId.specified=true");

        // Get all the mWeeklyQuestStageRewardList where clearRewardWeightId is null
        defaultMWeeklyQuestStageRewardShouldNotBeFound("clearRewardWeightId.specified=false");
    }

    @Test
    @Transactional
    public void getAllMWeeklyQuestStageRewardsByClearRewardWeightIdIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mWeeklyQuestStageRewardRepository.saveAndFlush(mWeeklyQuestStageReward);

        // Get all the mWeeklyQuestStageRewardList where clearRewardWeightId greater than or equals to DEFAULT_CLEAR_REWARD_WEIGHT_ID
        defaultMWeeklyQuestStageRewardShouldBeFound("clearRewardWeightId.greaterOrEqualThan=" + DEFAULT_CLEAR_REWARD_WEIGHT_ID);

        // Get all the mWeeklyQuestStageRewardList where clearRewardWeightId greater than or equals to UPDATED_CLEAR_REWARD_WEIGHT_ID
        defaultMWeeklyQuestStageRewardShouldNotBeFound("clearRewardWeightId.greaterOrEqualThan=" + UPDATED_CLEAR_REWARD_WEIGHT_ID);
    }

    @Test
    @Transactional
    public void getAllMWeeklyQuestStageRewardsByClearRewardWeightIdIsLessThanSomething() throws Exception {
        // Initialize the database
        mWeeklyQuestStageRewardRepository.saveAndFlush(mWeeklyQuestStageReward);

        // Get all the mWeeklyQuestStageRewardList where clearRewardWeightId less than or equals to DEFAULT_CLEAR_REWARD_WEIGHT_ID
        defaultMWeeklyQuestStageRewardShouldNotBeFound("clearRewardWeightId.lessThan=" + DEFAULT_CLEAR_REWARD_WEIGHT_ID);

        // Get all the mWeeklyQuestStageRewardList where clearRewardWeightId less than or equals to UPDATED_CLEAR_REWARD_WEIGHT_ID
        defaultMWeeklyQuestStageRewardShouldBeFound("clearRewardWeightId.lessThan=" + UPDATED_CLEAR_REWARD_WEIGHT_ID);
    }


    @Test
    @Transactional
    public void getAllMWeeklyQuestStageRewardsByAchievementRewardGroupIdIsEqualToSomething() throws Exception {
        // Initialize the database
        mWeeklyQuestStageRewardRepository.saveAndFlush(mWeeklyQuestStageReward);

        // Get all the mWeeklyQuestStageRewardList where achievementRewardGroupId equals to DEFAULT_ACHIEVEMENT_REWARD_GROUP_ID
        defaultMWeeklyQuestStageRewardShouldBeFound("achievementRewardGroupId.equals=" + DEFAULT_ACHIEVEMENT_REWARD_GROUP_ID);

        // Get all the mWeeklyQuestStageRewardList where achievementRewardGroupId equals to UPDATED_ACHIEVEMENT_REWARD_GROUP_ID
        defaultMWeeklyQuestStageRewardShouldNotBeFound("achievementRewardGroupId.equals=" + UPDATED_ACHIEVEMENT_REWARD_GROUP_ID);
    }

    @Test
    @Transactional
    public void getAllMWeeklyQuestStageRewardsByAchievementRewardGroupIdIsInShouldWork() throws Exception {
        // Initialize the database
        mWeeklyQuestStageRewardRepository.saveAndFlush(mWeeklyQuestStageReward);

        // Get all the mWeeklyQuestStageRewardList where achievementRewardGroupId in DEFAULT_ACHIEVEMENT_REWARD_GROUP_ID or UPDATED_ACHIEVEMENT_REWARD_GROUP_ID
        defaultMWeeklyQuestStageRewardShouldBeFound("achievementRewardGroupId.in=" + DEFAULT_ACHIEVEMENT_REWARD_GROUP_ID + "," + UPDATED_ACHIEVEMENT_REWARD_GROUP_ID);

        // Get all the mWeeklyQuestStageRewardList where achievementRewardGroupId equals to UPDATED_ACHIEVEMENT_REWARD_GROUP_ID
        defaultMWeeklyQuestStageRewardShouldNotBeFound("achievementRewardGroupId.in=" + UPDATED_ACHIEVEMENT_REWARD_GROUP_ID);
    }

    @Test
    @Transactional
    public void getAllMWeeklyQuestStageRewardsByAchievementRewardGroupIdIsNullOrNotNull() throws Exception {
        // Initialize the database
        mWeeklyQuestStageRewardRepository.saveAndFlush(mWeeklyQuestStageReward);

        // Get all the mWeeklyQuestStageRewardList where achievementRewardGroupId is not null
        defaultMWeeklyQuestStageRewardShouldBeFound("achievementRewardGroupId.specified=true");

        // Get all the mWeeklyQuestStageRewardList where achievementRewardGroupId is null
        defaultMWeeklyQuestStageRewardShouldNotBeFound("achievementRewardGroupId.specified=false");
    }

    @Test
    @Transactional
    public void getAllMWeeklyQuestStageRewardsByAchievementRewardGroupIdIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mWeeklyQuestStageRewardRepository.saveAndFlush(mWeeklyQuestStageReward);

        // Get all the mWeeklyQuestStageRewardList where achievementRewardGroupId greater than or equals to DEFAULT_ACHIEVEMENT_REWARD_GROUP_ID
        defaultMWeeklyQuestStageRewardShouldBeFound("achievementRewardGroupId.greaterOrEqualThan=" + DEFAULT_ACHIEVEMENT_REWARD_GROUP_ID);

        // Get all the mWeeklyQuestStageRewardList where achievementRewardGroupId greater than or equals to UPDATED_ACHIEVEMENT_REWARD_GROUP_ID
        defaultMWeeklyQuestStageRewardShouldNotBeFound("achievementRewardGroupId.greaterOrEqualThan=" + UPDATED_ACHIEVEMENT_REWARD_GROUP_ID);
    }

    @Test
    @Transactional
    public void getAllMWeeklyQuestStageRewardsByAchievementRewardGroupIdIsLessThanSomething() throws Exception {
        // Initialize the database
        mWeeklyQuestStageRewardRepository.saveAndFlush(mWeeklyQuestStageReward);

        // Get all the mWeeklyQuestStageRewardList where achievementRewardGroupId less than or equals to DEFAULT_ACHIEVEMENT_REWARD_GROUP_ID
        defaultMWeeklyQuestStageRewardShouldNotBeFound("achievementRewardGroupId.lessThan=" + DEFAULT_ACHIEVEMENT_REWARD_GROUP_ID);

        // Get all the mWeeklyQuestStageRewardList where achievementRewardGroupId less than or equals to UPDATED_ACHIEVEMENT_REWARD_GROUP_ID
        defaultMWeeklyQuestStageRewardShouldBeFound("achievementRewardGroupId.lessThan=" + UPDATED_ACHIEVEMENT_REWARD_GROUP_ID);
    }


    @Test
    @Transactional
    public void getAllMWeeklyQuestStageRewardsByCoopGroupIdIsEqualToSomething() throws Exception {
        // Initialize the database
        mWeeklyQuestStageRewardRepository.saveAndFlush(mWeeklyQuestStageReward);

        // Get all the mWeeklyQuestStageRewardList where coopGroupId equals to DEFAULT_COOP_GROUP_ID
        defaultMWeeklyQuestStageRewardShouldBeFound("coopGroupId.equals=" + DEFAULT_COOP_GROUP_ID);

        // Get all the mWeeklyQuestStageRewardList where coopGroupId equals to UPDATED_COOP_GROUP_ID
        defaultMWeeklyQuestStageRewardShouldNotBeFound("coopGroupId.equals=" + UPDATED_COOP_GROUP_ID);
    }

    @Test
    @Transactional
    public void getAllMWeeklyQuestStageRewardsByCoopGroupIdIsInShouldWork() throws Exception {
        // Initialize the database
        mWeeklyQuestStageRewardRepository.saveAndFlush(mWeeklyQuestStageReward);

        // Get all the mWeeklyQuestStageRewardList where coopGroupId in DEFAULT_COOP_GROUP_ID or UPDATED_COOP_GROUP_ID
        defaultMWeeklyQuestStageRewardShouldBeFound("coopGroupId.in=" + DEFAULT_COOP_GROUP_ID + "," + UPDATED_COOP_GROUP_ID);

        // Get all the mWeeklyQuestStageRewardList where coopGroupId equals to UPDATED_COOP_GROUP_ID
        defaultMWeeklyQuestStageRewardShouldNotBeFound("coopGroupId.in=" + UPDATED_COOP_GROUP_ID);
    }

    @Test
    @Transactional
    public void getAllMWeeklyQuestStageRewardsByCoopGroupIdIsNullOrNotNull() throws Exception {
        // Initialize the database
        mWeeklyQuestStageRewardRepository.saveAndFlush(mWeeklyQuestStageReward);

        // Get all the mWeeklyQuestStageRewardList where coopGroupId is not null
        defaultMWeeklyQuestStageRewardShouldBeFound("coopGroupId.specified=true");

        // Get all the mWeeklyQuestStageRewardList where coopGroupId is null
        defaultMWeeklyQuestStageRewardShouldNotBeFound("coopGroupId.specified=false");
    }

    @Test
    @Transactional
    public void getAllMWeeklyQuestStageRewardsByCoopGroupIdIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mWeeklyQuestStageRewardRepository.saveAndFlush(mWeeklyQuestStageReward);

        // Get all the mWeeklyQuestStageRewardList where coopGroupId greater than or equals to DEFAULT_COOP_GROUP_ID
        defaultMWeeklyQuestStageRewardShouldBeFound("coopGroupId.greaterOrEqualThan=" + DEFAULT_COOP_GROUP_ID);

        // Get all the mWeeklyQuestStageRewardList where coopGroupId greater than or equals to UPDATED_COOP_GROUP_ID
        defaultMWeeklyQuestStageRewardShouldNotBeFound("coopGroupId.greaterOrEqualThan=" + UPDATED_COOP_GROUP_ID);
    }

    @Test
    @Transactional
    public void getAllMWeeklyQuestStageRewardsByCoopGroupIdIsLessThanSomething() throws Exception {
        // Initialize the database
        mWeeklyQuestStageRewardRepository.saveAndFlush(mWeeklyQuestStageReward);

        // Get all the mWeeklyQuestStageRewardList where coopGroupId less than or equals to DEFAULT_COOP_GROUP_ID
        defaultMWeeklyQuestStageRewardShouldNotBeFound("coopGroupId.lessThan=" + DEFAULT_COOP_GROUP_ID);

        // Get all the mWeeklyQuestStageRewardList where coopGroupId less than or equals to UPDATED_COOP_GROUP_ID
        defaultMWeeklyQuestStageRewardShouldBeFound("coopGroupId.lessThan=" + UPDATED_COOP_GROUP_ID);
    }


    @Test
    @Transactional
    public void getAllMWeeklyQuestStageRewardsBySpecialRewardGroupIdIsEqualToSomething() throws Exception {
        // Initialize the database
        mWeeklyQuestStageRewardRepository.saveAndFlush(mWeeklyQuestStageReward);

        // Get all the mWeeklyQuestStageRewardList where specialRewardGroupId equals to DEFAULT_SPECIAL_REWARD_GROUP_ID
        defaultMWeeklyQuestStageRewardShouldBeFound("specialRewardGroupId.equals=" + DEFAULT_SPECIAL_REWARD_GROUP_ID);

        // Get all the mWeeklyQuestStageRewardList where specialRewardGroupId equals to UPDATED_SPECIAL_REWARD_GROUP_ID
        defaultMWeeklyQuestStageRewardShouldNotBeFound("specialRewardGroupId.equals=" + UPDATED_SPECIAL_REWARD_GROUP_ID);
    }

    @Test
    @Transactional
    public void getAllMWeeklyQuestStageRewardsBySpecialRewardGroupIdIsInShouldWork() throws Exception {
        // Initialize the database
        mWeeklyQuestStageRewardRepository.saveAndFlush(mWeeklyQuestStageReward);

        // Get all the mWeeklyQuestStageRewardList where specialRewardGroupId in DEFAULT_SPECIAL_REWARD_GROUP_ID or UPDATED_SPECIAL_REWARD_GROUP_ID
        defaultMWeeklyQuestStageRewardShouldBeFound("specialRewardGroupId.in=" + DEFAULT_SPECIAL_REWARD_GROUP_ID + "," + UPDATED_SPECIAL_REWARD_GROUP_ID);

        // Get all the mWeeklyQuestStageRewardList where specialRewardGroupId equals to UPDATED_SPECIAL_REWARD_GROUP_ID
        defaultMWeeklyQuestStageRewardShouldNotBeFound("specialRewardGroupId.in=" + UPDATED_SPECIAL_REWARD_GROUP_ID);
    }

    @Test
    @Transactional
    public void getAllMWeeklyQuestStageRewardsBySpecialRewardGroupIdIsNullOrNotNull() throws Exception {
        // Initialize the database
        mWeeklyQuestStageRewardRepository.saveAndFlush(mWeeklyQuestStageReward);

        // Get all the mWeeklyQuestStageRewardList where specialRewardGroupId is not null
        defaultMWeeklyQuestStageRewardShouldBeFound("specialRewardGroupId.specified=true");

        // Get all the mWeeklyQuestStageRewardList where specialRewardGroupId is null
        defaultMWeeklyQuestStageRewardShouldNotBeFound("specialRewardGroupId.specified=false");
    }

    @Test
    @Transactional
    public void getAllMWeeklyQuestStageRewardsBySpecialRewardGroupIdIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mWeeklyQuestStageRewardRepository.saveAndFlush(mWeeklyQuestStageReward);

        // Get all the mWeeklyQuestStageRewardList where specialRewardGroupId greater than or equals to DEFAULT_SPECIAL_REWARD_GROUP_ID
        defaultMWeeklyQuestStageRewardShouldBeFound("specialRewardGroupId.greaterOrEqualThan=" + DEFAULT_SPECIAL_REWARD_GROUP_ID);

        // Get all the mWeeklyQuestStageRewardList where specialRewardGroupId greater than or equals to UPDATED_SPECIAL_REWARD_GROUP_ID
        defaultMWeeklyQuestStageRewardShouldNotBeFound("specialRewardGroupId.greaterOrEqualThan=" + UPDATED_SPECIAL_REWARD_GROUP_ID);
    }

    @Test
    @Transactional
    public void getAllMWeeklyQuestStageRewardsBySpecialRewardGroupIdIsLessThanSomething() throws Exception {
        // Initialize the database
        mWeeklyQuestStageRewardRepository.saveAndFlush(mWeeklyQuestStageReward);

        // Get all the mWeeklyQuestStageRewardList where specialRewardGroupId less than or equals to DEFAULT_SPECIAL_REWARD_GROUP_ID
        defaultMWeeklyQuestStageRewardShouldNotBeFound("specialRewardGroupId.lessThan=" + DEFAULT_SPECIAL_REWARD_GROUP_ID);

        // Get all the mWeeklyQuestStageRewardList where specialRewardGroupId less than or equals to UPDATED_SPECIAL_REWARD_GROUP_ID
        defaultMWeeklyQuestStageRewardShouldBeFound("specialRewardGroupId.lessThan=" + UPDATED_SPECIAL_REWARD_GROUP_ID);
    }


    @Test
    @Transactional
    public void getAllMWeeklyQuestStageRewardsBySpecialRewardAmountIsEqualToSomething() throws Exception {
        // Initialize the database
        mWeeklyQuestStageRewardRepository.saveAndFlush(mWeeklyQuestStageReward);

        // Get all the mWeeklyQuestStageRewardList where specialRewardAmount equals to DEFAULT_SPECIAL_REWARD_AMOUNT
        defaultMWeeklyQuestStageRewardShouldBeFound("specialRewardAmount.equals=" + DEFAULT_SPECIAL_REWARD_AMOUNT);

        // Get all the mWeeklyQuestStageRewardList where specialRewardAmount equals to UPDATED_SPECIAL_REWARD_AMOUNT
        defaultMWeeklyQuestStageRewardShouldNotBeFound("specialRewardAmount.equals=" + UPDATED_SPECIAL_REWARD_AMOUNT);
    }

    @Test
    @Transactional
    public void getAllMWeeklyQuestStageRewardsBySpecialRewardAmountIsInShouldWork() throws Exception {
        // Initialize the database
        mWeeklyQuestStageRewardRepository.saveAndFlush(mWeeklyQuestStageReward);

        // Get all the mWeeklyQuestStageRewardList where specialRewardAmount in DEFAULT_SPECIAL_REWARD_AMOUNT or UPDATED_SPECIAL_REWARD_AMOUNT
        defaultMWeeklyQuestStageRewardShouldBeFound("specialRewardAmount.in=" + DEFAULT_SPECIAL_REWARD_AMOUNT + "," + UPDATED_SPECIAL_REWARD_AMOUNT);

        // Get all the mWeeklyQuestStageRewardList where specialRewardAmount equals to UPDATED_SPECIAL_REWARD_AMOUNT
        defaultMWeeklyQuestStageRewardShouldNotBeFound("specialRewardAmount.in=" + UPDATED_SPECIAL_REWARD_AMOUNT);
    }

    @Test
    @Transactional
    public void getAllMWeeklyQuestStageRewardsBySpecialRewardAmountIsNullOrNotNull() throws Exception {
        // Initialize the database
        mWeeklyQuestStageRewardRepository.saveAndFlush(mWeeklyQuestStageReward);

        // Get all the mWeeklyQuestStageRewardList where specialRewardAmount is not null
        defaultMWeeklyQuestStageRewardShouldBeFound("specialRewardAmount.specified=true");

        // Get all the mWeeklyQuestStageRewardList where specialRewardAmount is null
        defaultMWeeklyQuestStageRewardShouldNotBeFound("specialRewardAmount.specified=false");
    }

    @Test
    @Transactional
    public void getAllMWeeklyQuestStageRewardsBySpecialRewardAmountIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mWeeklyQuestStageRewardRepository.saveAndFlush(mWeeklyQuestStageReward);

        // Get all the mWeeklyQuestStageRewardList where specialRewardAmount greater than or equals to DEFAULT_SPECIAL_REWARD_AMOUNT
        defaultMWeeklyQuestStageRewardShouldBeFound("specialRewardAmount.greaterOrEqualThan=" + DEFAULT_SPECIAL_REWARD_AMOUNT);

        // Get all the mWeeklyQuestStageRewardList where specialRewardAmount greater than or equals to UPDATED_SPECIAL_REWARD_AMOUNT
        defaultMWeeklyQuestStageRewardShouldNotBeFound("specialRewardAmount.greaterOrEqualThan=" + UPDATED_SPECIAL_REWARD_AMOUNT);
    }

    @Test
    @Transactional
    public void getAllMWeeklyQuestStageRewardsBySpecialRewardAmountIsLessThanSomething() throws Exception {
        // Initialize the database
        mWeeklyQuestStageRewardRepository.saveAndFlush(mWeeklyQuestStageReward);

        // Get all the mWeeklyQuestStageRewardList where specialRewardAmount less than or equals to DEFAULT_SPECIAL_REWARD_AMOUNT
        defaultMWeeklyQuestStageRewardShouldNotBeFound("specialRewardAmount.lessThan=" + DEFAULT_SPECIAL_REWARD_AMOUNT);

        // Get all the mWeeklyQuestStageRewardList where specialRewardAmount less than or equals to UPDATED_SPECIAL_REWARD_AMOUNT
        defaultMWeeklyQuestStageRewardShouldBeFound("specialRewardAmount.lessThan=" + UPDATED_SPECIAL_REWARD_AMOUNT);
    }


    @Test
    @Transactional
    public void getAllMWeeklyQuestStageRewardsByGoalRewardGroupIdIsEqualToSomething() throws Exception {
        // Initialize the database
        mWeeklyQuestStageRewardRepository.saveAndFlush(mWeeklyQuestStageReward);

        // Get all the mWeeklyQuestStageRewardList where goalRewardGroupId equals to DEFAULT_GOAL_REWARD_GROUP_ID
        defaultMWeeklyQuestStageRewardShouldBeFound("goalRewardGroupId.equals=" + DEFAULT_GOAL_REWARD_GROUP_ID);

        // Get all the mWeeklyQuestStageRewardList where goalRewardGroupId equals to UPDATED_GOAL_REWARD_GROUP_ID
        defaultMWeeklyQuestStageRewardShouldNotBeFound("goalRewardGroupId.equals=" + UPDATED_GOAL_REWARD_GROUP_ID);
    }

    @Test
    @Transactional
    public void getAllMWeeklyQuestStageRewardsByGoalRewardGroupIdIsInShouldWork() throws Exception {
        // Initialize the database
        mWeeklyQuestStageRewardRepository.saveAndFlush(mWeeklyQuestStageReward);

        // Get all the mWeeklyQuestStageRewardList where goalRewardGroupId in DEFAULT_GOAL_REWARD_GROUP_ID or UPDATED_GOAL_REWARD_GROUP_ID
        defaultMWeeklyQuestStageRewardShouldBeFound("goalRewardGroupId.in=" + DEFAULT_GOAL_REWARD_GROUP_ID + "," + UPDATED_GOAL_REWARD_GROUP_ID);

        // Get all the mWeeklyQuestStageRewardList where goalRewardGroupId equals to UPDATED_GOAL_REWARD_GROUP_ID
        defaultMWeeklyQuestStageRewardShouldNotBeFound("goalRewardGroupId.in=" + UPDATED_GOAL_REWARD_GROUP_ID);
    }

    @Test
    @Transactional
    public void getAllMWeeklyQuestStageRewardsByGoalRewardGroupIdIsNullOrNotNull() throws Exception {
        // Initialize the database
        mWeeklyQuestStageRewardRepository.saveAndFlush(mWeeklyQuestStageReward);

        // Get all the mWeeklyQuestStageRewardList where goalRewardGroupId is not null
        defaultMWeeklyQuestStageRewardShouldBeFound("goalRewardGroupId.specified=true");

        // Get all the mWeeklyQuestStageRewardList where goalRewardGroupId is null
        defaultMWeeklyQuestStageRewardShouldNotBeFound("goalRewardGroupId.specified=false");
    }

    @Test
    @Transactional
    public void getAllMWeeklyQuestStageRewardsByGoalRewardGroupIdIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mWeeklyQuestStageRewardRepository.saveAndFlush(mWeeklyQuestStageReward);

        // Get all the mWeeklyQuestStageRewardList where goalRewardGroupId greater than or equals to DEFAULT_GOAL_REWARD_GROUP_ID
        defaultMWeeklyQuestStageRewardShouldBeFound("goalRewardGroupId.greaterOrEqualThan=" + DEFAULT_GOAL_REWARD_GROUP_ID);

        // Get all the mWeeklyQuestStageRewardList where goalRewardGroupId greater than or equals to UPDATED_GOAL_REWARD_GROUP_ID
        defaultMWeeklyQuestStageRewardShouldNotBeFound("goalRewardGroupId.greaterOrEqualThan=" + UPDATED_GOAL_REWARD_GROUP_ID);
    }

    @Test
    @Transactional
    public void getAllMWeeklyQuestStageRewardsByGoalRewardGroupIdIsLessThanSomething() throws Exception {
        // Initialize the database
        mWeeklyQuestStageRewardRepository.saveAndFlush(mWeeklyQuestStageReward);

        // Get all the mWeeklyQuestStageRewardList where goalRewardGroupId less than or equals to DEFAULT_GOAL_REWARD_GROUP_ID
        defaultMWeeklyQuestStageRewardShouldNotBeFound("goalRewardGroupId.lessThan=" + DEFAULT_GOAL_REWARD_GROUP_ID);

        // Get all the mWeeklyQuestStageRewardList where goalRewardGroupId less than or equals to UPDATED_GOAL_REWARD_GROUP_ID
        defaultMWeeklyQuestStageRewardShouldBeFound("goalRewardGroupId.lessThan=" + UPDATED_GOAL_REWARD_GROUP_ID);
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultMWeeklyQuestStageRewardShouldBeFound(String filter) throws Exception {
        restMWeeklyQuestStageRewardMockMvc.perform(get("/api/m-weekly-quest-stage-rewards?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(mWeeklyQuestStageReward.getId().intValue())))
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
        restMWeeklyQuestStageRewardMockMvc.perform(get("/api/m-weekly-quest-stage-rewards/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultMWeeklyQuestStageRewardShouldNotBeFound(String filter) throws Exception {
        restMWeeklyQuestStageRewardMockMvc.perform(get("/api/m-weekly-quest-stage-rewards?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restMWeeklyQuestStageRewardMockMvc.perform(get("/api/m-weekly-quest-stage-rewards/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("0"));
    }


    @Test
    @Transactional
    public void getNonExistingMWeeklyQuestStageReward() throws Exception {
        // Get the mWeeklyQuestStageReward
        restMWeeklyQuestStageRewardMockMvc.perform(get("/api/m-weekly-quest-stage-rewards/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateMWeeklyQuestStageReward() throws Exception {
        // Initialize the database
        mWeeklyQuestStageRewardRepository.saveAndFlush(mWeeklyQuestStageReward);

        int databaseSizeBeforeUpdate = mWeeklyQuestStageRewardRepository.findAll().size();

        // Update the mWeeklyQuestStageReward
        MWeeklyQuestStageReward updatedMWeeklyQuestStageReward = mWeeklyQuestStageRewardRepository.findById(mWeeklyQuestStageReward.getId()).get();
        // Disconnect from session so that the updates on updatedMWeeklyQuestStageReward are not directly saved in db
        em.detach(updatedMWeeklyQuestStageReward);
        updatedMWeeklyQuestStageReward
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
        MWeeklyQuestStageRewardDTO mWeeklyQuestStageRewardDTO = mWeeklyQuestStageRewardMapper.toDto(updatedMWeeklyQuestStageReward);

        restMWeeklyQuestStageRewardMockMvc.perform(put("/api/m-weekly-quest-stage-rewards")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mWeeklyQuestStageRewardDTO)))
            .andExpect(status().isOk());

        // Validate the MWeeklyQuestStageReward in the database
        List<MWeeklyQuestStageReward> mWeeklyQuestStageRewardList = mWeeklyQuestStageRewardRepository.findAll();
        assertThat(mWeeklyQuestStageRewardList).hasSize(databaseSizeBeforeUpdate);
        MWeeklyQuestStageReward testMWeeklyQuestStageReward = mWeeklyQuestStageRewardList.get(mWeeklyQuestStageRewardList.size() - 1);
        assertThat(testMWeeklyQuestStageReward.getStageId()).isEqualTo(UPDATED_STAGE_ID);
        assertThat(testMWeeklyQuestStageReward.getExp()).isEqualTo(UPDATED_EXP);
        assertThat(testMWeeklyQuestStageReward.getCoin()).isEqualTo(UPDATED_COIN);
        assertThat(testMWeeklyQuestStageReward.getGuildPoint()).isEqualTo(UPDATED_GUILD_POINT);
        assertThat(testMWeeklyQuestStageReward.getClearRewardGroupId()).isEqualTo(UPDATED_CLEAR_REWARD_GROUP_ID);
        assertThat(testMWeeklyQuestStageReward.getClearRewardWeightId()).isEqualTo(UPDATED_CLEAR_REWARD_WEIGHT_ID);
        assertThat(testMWeeklyQuestStageReward.getAchievementRewardGroupId()).isEqualTo(UPDATED_ACHIEVEMENT_REWARD_GROUP_ID);
        assertThat(testMWeeklyQuestStageReward.getCoopGroupId()).isEqualTo(UPDATED_COOP_GROUP_ID);
        assertThat(testMWeeklyQuestStageReward.getSpecialRewardGroupId()).isEqualTo(UPDATED_SPECIAL_REWARD_GROUP_ID);
        assertThat(testMWeeklyQuestStageReward.getSpecialRewardAmount()).isEqualTo(UPDATED_SPECIAL_REWARD_AMOUNT);
        assertThat(testMWeeklyQuestStageReward.getGoalRewardGroupId()).isEqualTo(UPDATED_GOAL_REWARD_GROUP_ID);
    }

    @Test
    @Transactional
    public void updateNonExistingMWeeklyQuestStageReward() throws Exception {
        int databaseSizeBeforeUpdate = mWeeklyQuestStageRewardRepository.findAll().size();

        // Create the MWeeklyQuestStageReward
        MWeeklyQuestStageRewardDTO mWeeklyQuestStageRewardDTO = mWeeklyQuestStageRewardMapper.toDto(mWeeklyQuestStageReward);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restMWeeklyQuestStageRewardMockMvc.perform(put("/api/m-weekly-quest-stage-rewards")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mWeeklyQuestStageRewardDTO)))
            .andExpect(status().isBadRequest());

        // Validate the MWeeklyQuestStageReward in the database
        List<MWeeklyQuestStageReward> mWeeklyQuestStageRewardList = mWeeklyQuestStageRewardRepository.findAll();
        assertThat(mWeeklyQuestStageRewardList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteMWeeklyQuestStageReward() throws Exception {
        // Initialize the database
        mWeeklyQuestStageRewardRepository.saveAndFlush(mWeeklyQuestStageReward);

        int databaseSizeBeforeDelete = mWeeklyQuestStageRewardRepository.findAll().size();

        // Delete the mWeeklyQuestStageReward
        restMWeeklyQuestStageRewardMockMvc.perform(delete("/api/m-weekly-quest-stage-rewards/{id}", mWeeklyQuestStageReward.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database is empty
        List<MWeeklyQuestStageReward> mWeeklyQuestStageRewardList = mWeeklyQuestStageRewardRepository.findAll();
        assertThat(mWeeklyQuestStageRewardList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(MWeeklyQuestStageReward.class);
        MWeeklyQuestStageReward mWeeklyQuestStageReward1 = new MWeeklyQuestStageReward();
        mWeeklyQuestStageReward1.setId(1L);
        MWeeklyQuestStageReward mWeeklyQuestStageReward2 = new MWeeklyQuestStageReward();
        mWeeklyQuestStageReward2.setId(mWeeklyQuestStageReward1.getId());
        assertThat(mWeeklyQuestStageReward1).isEqualTo(mWeeklyQuestStageReward2);
        mWeeklyQuestStageReward2.setId(2L);
        assertThat(mWeeklyQuestStageReward1).isNotEqualTo(mWeeklyQuestStageReward2);
        mWeeklyQuestStageReward1.setId(null);
        assertThat(mWeeklyQuestStageReward1).isNotEqualTo(mWeeklyQuestStageReward2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(MWeeklyQuestStageRewardDTO.class);
        MWeeklyQuestStageRewardDTO mWeeklyQuestStageRewardDTO1 = new MWeeklyQuestStageRewardDTO();
        mWeeklyQuestStageRewardDTO1.setId(1L);
        MWeeklyQuestStageRewardDTO mWeeklyQuestStageRewardDTO2 = new MWeeklyQuestStageRewardDTO();
        assertThat(mWeeklyQuestStageRewardDTO1).isNotEqualTo(mWeeklyQuestStageRewardDTO2);
        mWeeklyQuestStageRewardDTO2.setId(mWeeklyQuestStageRewardDTO1.getId());
        assertThat(mWeeklyQuestStageRewardDTO1).isEqualTo(mWeeklyQuestStageRewardDTO2);
        mWeeklyQuestStageRewardDTO2.setId(2L);
        assertThat(mWeeklyQuestStageRewardDTO1).isNotEqualTo(mWeeklyQuestStageRewardDTO2);
        mWeeklyQuestStageRewardDTO1.setId(null);
        assertThat(mWeeklyQuestStageRewardDTO1).isNotEqualTo(mWeeklyQuestStageRewardDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(mWeeklyQuestStageRewardMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(mWeeklyQuestStageRewardMapper.fromId(null)).isNull();
    }
}
