package io.shm.tsubasa.web.rest;

import io.shm.tsubasa.TsubasaApp;
import io.shm.tsubasa.domain.MMarathonQuestStageReward;
import io.shm.tsubasa.repository.MMarathonQuestStageRewardRepository;
import io.shm.tsubasa.service.MMarathonQuestStageRewardService;
import io.shm.tsubasa.service.dto.MMarathonQuestStageRewardDTO;
import io.shm.tsubasa.service.mapper.MMarathonQuestStageRewardMapper;
import io.shm.tsubasa.web.rest.errors.ExceptionTranslator;
import io.shm.tsubasa.service.dto.MMarathonQuestStageRewardCriteria;
import io.shm.tsubasa.service.MMarathonQuestStageRewardQueryService;

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
 * Integration tests for the {@Link MMarathonQuestStageRewardResource} REST controller.
 */
@SpringBootTest(classes = TsubasaApp.class)
public class MMarathonQuestStageRewardResourceIT {

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
    private MMarathonQuestStageRewardRepository mMarathonQuestStageRewardRepository;

    @Autowired
    private MMarathonQuestStageRewardMapper mMarathonQuestStageRewardMapper;

    @Autowired
    private MMarathonQuestStageRewardService mMarathonQuestStageRewardService;

    @Autowired
    private MMarathonQuestStageRewardQueryService mMarathonQuestStageRewardQueryService;

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

    private MockMvc restMMarathonQuestStageRewardMockMvc;

    private MMarathonQuestStageReward mMarathonQuestStageReward;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final MMarathonQuestStageRewardResource mMarathonQuestStageRewardResource = new MMarathonQuestStageRewardResource(mMarathonQuestStageRewardService, mMarathonQuestStageRewardQueryService);
        this.restMMarathonQuestStageRewardMockMvc = MockMvcBuilders.standaloneSetup(mMarathonQuestStageRewardResource)
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
    public static MMarathonQuestStageReward createEntity(EntityManager em) {
        MMarathonQuestStageReward mMarathonQuestStageReward = new MMarathonQuestStageReward()
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
        return mMarathonQuestStageReward;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static MMarathonQuestStageReward createUpdatedEntity(EntityManager em) {
        MMarathonQuestStageReward mMarathonQuestStageReward = new MMarathonQuestStageReward()
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
        return mMarathonQuestStageReward;
    }

    @BeforeEach
    public void initTest() {
        mMarathonQuestStageReward = createEntity(em);
    }

    @Test
    @Transactional
    public void createMMarathonQuestStageReward() throws Exception {
        int databaseSizeBeforeCreate = mMarathonQuestStageRewardRepository.findAll().size();

        // Create the MMarathonQuestStageReward
        MMarathonQuestStageRewardDTO mMarathonQuestStageRewardDTO = mMarathonQuestStageRewardMapper.toDto(mMarathonQuestStageReward);
        restMMarathonQuestStageRewardMockMvc.perform(post("/api/m-marathon-quest-stage-rewards")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mMarathonQuestStageRewardDTO)))
            .andExpect(status().isCreated());

        // Validate the MMarathonQuestStageReward in the database
        List<MMarathonQuestStageReward> mMarathonQuestStageRewardList = mMarathonQuestStageRewardRepository.findAll();
        assertThat(mMarathonQuestStageRewardList).hasSize(databaseSizeBeforeCreate + 1);
        MMarathonQuestStageReward testMMarathonQuestStageReward = mMarathonQuestStageRewardList.get(mMarathonQuestStageRewardList.size() - 1);
        assertThat(testMMarathonQuestStageReward.getStageId()).isEqualTo(DEFAULT_STAGE_ID);
        assertThat(testMMarathonQuestStageReward.getExp()).isEqualTo(DEFAULT_EXP);
        assertThat(testMMarathonQuestStageReward.getCoin()).isEqualTo(DEFAULT_COIN);
        assertThat(testMMarathonQuestStageReward.getGuildPoint()).isEqualTo(DEFAULT_GUILD_POINT);
        assertThat(testMMarathonQuestStageReward.getClearRewardGroupId()).isEqualTo(DEFAULT_CLEAR_REWARD_GROUP_ID);
        assertThat(testMMarathonQuestStageReward.getClearRewardWeightId()).isEqualTo(DEFAULT_CLEAR_REWARD_WEIGHT_ID);
        assertThat(testMMarathonQuestStageReward.getAchievementRewardGroupId()).isEqualTo(DEFAULT_ACHIEVEMENT_REWARD_GROUP_ID);
        assertThat(testMMarathonQuestStageReward.getCoopGroupId()).isEqualTo(DEFAULT_COOP_GROUP_ID);
        assertThat(testMMarathonQuestStageReward.getSpecialRewardGroupId()).isEqualTo(DEFAULT_SPECIAL_REWARD_GROUP_ID);
        assertThat(testMMarathonQuestStageReward.getSpecialRewardAmount()).isEqualTo(DEFAULT_SPECIAL_REWARD_AMOUNT);
        assertThat(testMMarathonQuestStageReward.getGoalRewardGroupId()).isEqualTo(DEFAULT_GOAL_REWARD_GROUP_ID);
    }

    @Test
    @Transactional
    public void createMMarathonQuestStageRewardWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = mMarathonQuestStageRewardRepository.findAll().size();

        // Create the MMarathonQuestStageReward with an existing ID
        mMarathonQuestStageReward.setId(1L);
        MMarathonQuestStageRewardDTO mMarathonQuestStageRewardDTO = mMarathonQuestStageRewardMapper.toDto(mMarathonQuestStageReward);

        // An entity with an existing ID cannot be created, so this API call must fail
        restMMarathonQuestStageRewardMockMvc.perform(post("/api/m-marathon-quest-stage-rewards")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mMarathonQuestStageRewardDTO)))
            .andExpect(status().isBadRequest());

        // Validate the MMarathonQuestStageReward in the database
        List<MMarathonQuestStageReward> mMarathonQuestStageRewardList = mMarathonQuestStageRewardRepository.findAll();
        assertThat(mMarathonQuestStageRewardList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkStageIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = mMarathonQuestStageRewardRepository.findAll().size();
        // set the field null
        mMarathonQuestStageReward.setStageId(null);

        // Create the MMarathonQuestStageReward, which fails.
        MMarathonQuestStageRewardDTO mMarathonQuestStageRewardDTO = mMarathonQuestStageRewardMapper.toDto(mMarathonQuestStageReward);

        restMMarathonQuestStageRewardMockMvc.perform(post("/api/m-marathon-quest-stage-rewards")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mMarathonQuestStageRewardDTO)))
            .andExpect(status().isBadRequest());

        List<MMarathonQuestStageReward> mMarathonQuestStageRewardList = mMarathonQuestStageRewardRepository.findAll();
        assertThat(mMarathonQuestStageRewardList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkExpIsRequired() throws Exception {
        int databaseSizeBeforeTest = mMarathonQuestStageRewardRepository.findAll().size();
        // set the field null
        mMarathonQuestStageReward.setExp(null);

        // Create the MMarathonQuestStageReward, which fails.
        MMarathonQuestStageRewardDTO mMarathonQuestStageRewardDTO = mMarathonQuestStageRewardMapper.toDto(mMarathonQuestStageReward);

        restMMarathonQuestStageRewardMockMvc.perform(post("/api/m-marathon-quest-stage-rewards")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mMarathonQuestStageRewardDTO)))
            .andExpect(status().isBadRequest());

        List<MMarathonQuestStageReward> mMarathonQuestStageRewardList = mMarathonQuestStageRewardRepository.findAll();
        assertThat(mMarathonQuestStageRewardList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkCoinIsRequired() throws Exception {
        int databaseSizeBeforeTest = mMarathonQuestStageRewardRepository.findAll().size();
        // set the field null
        mMarathonQuestStageReward.setCoin(null);

        // Create the MMarathonQuestStageReward, which fails.
        MMarathonQuestStageRewardDTO mMarathonQuestStageRewardDTO = mMarathonQuestStageRewardMapper.toDto(mMarathonQuestStageReward);

        restMMarathonQuestStageRewardMockMvc.perform(post("/api/m-marathon-quest-stage-rewards")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mMarathonQuestStageRewardDTO)))
            .andExpect(status().isBadRequest());

        List<MMarathonQuestStageReward> mMarathonQuestStageRewardList = mMarathonQuestStageRewardRepository.findAll();
        assertThat(mMarathonQuestStageRewardList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkGuildPointIsRequired() throws Exception {
        int databaseSizeBeforeTest = mMarathonQuestStageRewardRepository.findAll().size();
        // set the field null
        mMarathonQuestStageReward.setGuildPoint(null);

        // Create the MMarathonQuestStageReward, which fails.
        MMarathonQuestStageRewardDTO mMarathonQuestStageRewardDTO = mMarathonQuestStageRewardMapper.toDto(mMarathonQuestStageReward);

        restMMarathonQuestStageRewardMockMvc.perform(post("/api/m-marathon-quest-stage-rewards")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mMarathonQuestStageRewardDTO)))
            .andExpect(status().isBadRequest());

        List<MMarathonQuestStageReward> mMarathonQuestStageRewardList = mMarathonQuestStageRewardRepository.findAll();
        assertThat(mMarathonQuestStageRewardList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkClearRewardGroupIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = mMarathonQuestStageRewardRepository.findAll().size();
        // set the field null
        mMarathonQuestStageReward.setClearRewardGroupId(null);

        // Create the MMarathonQuestStageReward, which fails.
        MMarathonQuestStageRewardDTO mMarathonQuestStageRewardDTO = mMarathonQuestStageRewardMapper.toDto(mMarathonQuestStageReward);

        restMMarathonQuestStageRewardMockMvc.perform(post("/api/m-marathon-quest-stage-rewards")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mMarathonQuestStageRewardDTO)))
            .andExpect(status().isBadRequest());

        List<MMarathonQuestStageReward> mMarathonQuestStageRewardList = mMarathonQuestStageRewardRepository.findAll();
        assertThat(mMarathonQuestStageRewardList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkClearRewardWeightIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = mMarathonQuestStageRewardRepository.findAll().size();
        // set the field null
        mMarathonQuestStageReward.setClearRewardWeightId(null);

        // Create the MMarathonQuestStageReward, which fails.
        MMarathonQuestStageRewardDTO mMarathonQuestStageRewardDTO = mMarathonQuestStageRewardMapper.toDto(mMarathonQuestStageReward);

        restMMarathonQuestStageRewardMockMvc.perform(post("/api/m-marathon-quest-stage-rewards")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mMarathonQuestStageRewardDTO)))
            .andExpect(status().isBadRequest());

        List<MMarathonQuestStageReward> mMarathonQuestStageRewardList = mMarathonQuestStageRewardRepository.findAll();
        assertThat(mMarathonQuestStageRewardList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkAchievementRewardGroupIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = mMarathonQuestStageRewardRepository.findAll().size();
        // set the field null
        mMarathonQuestStageReward.setAchievementRewardGroupId(null);

        // Create the MMarathonQuestStageReward, which fails.
        MMarathonQuestStageRewardDTO mMarathonQuestStageRewardDTO = mMarathonQuestStageRewardMapper.toDto(mMarathonQuestStageReward);

        restMMarathonQuestStageRewardMockMvc.perform(post("/api/m-marathon-quest-stage-rewards")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mMarathonQuestStageRewardDTO)))
            .andExpect(status().isBadRequest());

        List<MMarathonQuestStageReward> mMarathonQuestStageRewardList = mMarathonQuestStageRewardRepository.findAll();
        assertThat(mMarathonQuestStageRewardList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkCoopGroupIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = mMarathonQuestStageRewardRepository.findAll().size();
        // set the field null
        mMarathonQuestStageReward.setCoopGroupId(null);

        // Create the MMarathonQuestStageReward, which fails.
        MMarathonQuestStageRewardDTO mMarathonQuestStageRewardDTO = mMarathonQuestStageRewardMapper.toDto(mMarathonQuestStageReward);

        restMMarathonQuestStageRewardMockMvc.perform(post("/api/m-marathon-quest-stage-rewards")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mMarathonQuestStageRewardDTO)))
            .andExpect(status().isBadRequest());

        List<MMarathonQuestStageReward> mMarathonQuestStageRewardList = mMarathonQuestStageRewardRepository.findAll();
        assertThat(mMarathonQuestStageRewardList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkSpecialRewardAmountIsRequired() throws Exception {
        int databaseSizeBeforeTest = mMarathonQuestStageRewardRepository.findAll().size();
        // set the field null
        mMarathonQuestStageReward.setSpecialRewardAmount(null);

        // Create the MMarathonQuestStageReward, which fails.
        MMarathonQuestStageRewardDTO mMarathonQuestStageRewardDTO = mMarathonQuestStageRewardMapper.toDto(mMarathonQuestStageReward);

        restMMarathonQuestStageRewardMockMvc.perform(post("/api/m-marathon-quest-stage-rewards")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mMarathonQuestStageRewardDTO)))
            .andExpect(status().isBadRequest());

        List<MMarathonQuestStageReward> mMarathonQuestStageRewardList = mMarathonQuestStageRewardRepository.findAll();
        assertThat(mMarathonQuestStageRewardList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllMMarathonQuestStageRewards() throws Exception {
        // Initialize the database
        mMarathonQuestStageRewardRepository.saveAndFlush(mMarathonQuestStageReward);

        // Get all the mMarathonQuestStageRewardList
        restMMarathonQuestStageRewardMockMvc.perform(get("/api/m-marathon-quest-stage-rewards?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(mMarathonQuestStageReward.getId().intValue())))
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
    public void getMMarathonQuestStageReward() throws Exception {
        // Initialize the database
        mMarathonQuestStageRewardRepository.saveAndFlush(mMarathonQuestStageReward);

        // Get the mMarathonQuestStageReward
        restMMarathonQuestStageRewardMockMvc.perform(get("/api/m-marathon-quest-stage-rewards/{id}", mMarathonQuestStageReward.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(mMarathonQuestStageReward.getId().intValue()))
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
    public void getAllMMarathonQuestStageRewardsByStageIdIsEqualToSomething() throws Exception {
        // Initialize the database
        mMarathonQuestStageRewardRepository.saveAndFlush(mMarathonQuestStageReward);

        // Get all the mMarathonQuestStageRewardList where stageId equals to DEFAULT_STAGE_ID
        defaultMMarathonQuestStageRewardShouldBeFound("stageId.equals=" + DEFAULT_STAGE_ID);

        // Get all the mMarathonQuestStageRewardList where stageId equals to UPDATED_STAGE_ID
        defaultMMarathonQuestStageRewardShouldNotBeFound("stageId.equals=" + UPDATED_STAGE_ID);
    }

    @Test
    @Transactional
    public void getAllMMarathonQuestStageRewardsByStageIdIsInShouldWork() throws Exception {
        // Initialize the database
        mMarathonQuestStageRewardRepository.saveAndFlush(mMarathonQuestStageReward);

        // Get all the mMarathonQuestStageRewardList where stageId in DEFAULT_STAGE_ID or UPDATED_STAGE_ID
        defaultMMarathonQuestStageRewardShouldBeFound("stageId.in=" + DEFAULT_STAGE_ID + "," + UPDATED_STAGE_ID);

        // Get all the mMarathonQuestStageRewardList where stageId equals to UPDATED_STAGE_ID
        defaultMMarathonQuestStageRewardShouldNotBeFound("stageId.in=" + UPDATED_STAGE_ID);
    }

    @Test
    @Transactional
    public void getAllMMarathonQuestStageRewardsByStageIdIsNullOrNotNull() throws Exception {
        // Initialize the database
        mMarathonQuestStageRewardRepository.saveAndFlush(mMarathonQuestStageReward);

        // Get all the mMarathonQuestStageRewardList where stageId is not null
        defaultMMarathonQuestStageRewardShouldBeFound("stageId.specified=true");

        // Get all the mMarathonQuestStageRewardList where stageId is null
        defaultMMarathonQuestStageRewardShouldNotBeFound("stageId.specified=false");
    }

    @Test
    @Transactional
    public void getAllMMarathonQuestStageRewardsByStageIdIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mMarathonQuestStageRewardRepository.saveAndFlush(mMarathonQuestStageReward);

        // Get all the mMarathonQuestStageRewardList where stageId greater than or equals to DEFAULT_STAGE_ID
        defaultMMarathonQuestStageRewardShouldBeFound("stageId.greaterOrEqualThan=" + DEFAULT_STAGE_ID);

        // Get all the mMarathonQuestStageRewardList where stageId greater than or equals to UPDATED_STAGE_ID
        defaultMMarathonQuestStageRewardShouldNotBeFound("stageId.greaterOrEqualThan=" + UPDATED_STAGE_ID);
    }

    @Test
    @Transactional
    public void getAllMMarathonQuestStageRewardsByStageIdIsLessThanSomething() throws Exception {
        // Initialize the database
        mMarathonQuestStageRewardRepository.saveAndFlush(mMarathonQuestStageReward);

        // Get all the mMarathonQuestStageRewardList where stageId less than or equals to DEFAULT_STAGE_ID
        defaultMMarathonQuestStageRewardShouldNotBeFound("stageId.lessThan=" + DEFAULT_STAGE_ID);

        // Get all the mMarathonQuestStageRewardList where stageId less than or equals to UPDATED_STAGE_ID
        defaultMMarathonQuestStageRewardShouldBeFound("stageId.lessThan=" + UPDATED_STAGE_ID);
    }


    @Test
    @Transactional
    public void getAllMMarathonQuestStageRewardsByExpIsEqualToSomething() throws Exception {
        // Initialize the database
        mMarathonQuestStageRewardRepository.saveAndFlush(mMarathonQuestStageReward);

        // Get all the mMarathonQuestStageRewardList where exp equals to DEFAULT_EXP
        defaultMMarathonQuestStageRewardShouldBeFound("exp.equals=" + DEFAULT_EXP);

        // Get all the mMarathonQuestStageRewardList where exp equals to UPDATED_EXP
        defaultMMarathonQuestStageRewardShouldNotBeFound("exp.equals=" + UPDATED_EXP);
    }

    @Test
    @Transactional
    public void getAllMMarathonQuestStageRewardsByExpIsInShouldWork() throws Exception {
        // Initialize the database
        mMarathonQuestStageRewardRepository.saveAndFlush(mMarathonQuestStageReward);

        // Get all the mMarathonQuestStageRewardList where exp in DEFAULT_EXP or UPDATED_EXP
        defaultMMarathonQuestStageRewardShouldBeFound("exp.in=" + DEFAULT_EXP + "," + UPDATED_EXP);

        // Get all the mMarathonQuestStageRewardList where exp equals to UPDATED_EXP
        defaultMMarathonQuestStageRewardShouldNotBeFound("exp.in=" + UPDATED_EXP);
    }

    @Test
    @Transactional
    public void getAllMMarathonQuestStageRewardsByExpIsNullOrNotNull() throws Exception {
        // Initialize the database
        mMarathonQuestStageRewardRepository.saveAndFlush(mMarathonQuestStageReward);

        // Get all the mMarathonQuestStageRewardList where exp is not null
        defaultMMarathonQuestStageRewardShouldBeFound("exp.specified=true");

        // Get all the mMarathonQuestStageRewardList where exp is null
        defaultMMarathonQuestStageRewardShouldNotBeFound("exp.specified=false");
    }

    @Test
    @Transactional
    public void getAllMMarathonQuestStageRewardsByExpIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mMarathonQuestStageRewardRepository.saveAndFlush(mMarathonQuestStageReward);

        // Get all the mMarathonQuestStageRewardList where exp greater than or equals to DEFAULT_EXP
        defaultMMarathonQuestStageRewardShouldBeFound("exp.greaterOrEqualThan=" + DEFAULT_EXP);

        // Get all the mMarathonQuestStageRewardList where exp greater than or equals to UPDATED_EXP
        defaultMMarathonQuestStageRewardShouldNotBeFound("exp.greaterOrEqualThan=" + UPDATED_EXP);
    }

    @Test
    @Transactional
    public void getAllMMarathonQuestStageRewardsByExpIsLessThanSomething() throws Exception {
        // Initialize the database
        mMarathonQuestStageRewardRepository.saveAndFlush(mMarathonQuestStageReward);

        // Get all the mMarathonQuestStageRewardList where exp less than or equals to DEFAULT_EXP
        defaultMMarathonQuestStageRewardShouldNotBeFound("exp.lessThan=" + DEFAULT_EXP);

        // Get all the mMarathonQuestStageRewardList where exp less than or equals to UPDATED_EXP
        defaultMMarathonQuestStageRewardShouldBeFound("exp.lessThan=" + UPDATED_EXP);
    }


    @Test
    @Transactional
    public void getAllMMarathonQuestStageRewardsByCoinIsEqualToSomething() throws Exception {
        // Initialize the database
        mMarathonQuestStageRewardRepository.saveAndFlush(mMarathonQuestStageReward);

        // Get all the mMarathonQuestStageRewardList where coin equals to DEFAULT_COIN
        defaultMMarathonQuestStageRewardShouldBeFound("coin.equals=" + DEFAULT_COIN);

        // Get all the mMarathonQuestStageRewardList where coin equals to UPDATED_COIN
        defaultMMarathonQuestStageRewardShouldNotBeFound("coin.equals=" + UPDATED_COIN);
    }

    @Test
    @Transactional
    public void getAllMMarathonQuestStageRewardsByCoinIsInShouldWork() throws Exception {
        // Initialize the database
        mMarathonQuestStageRewardRepository.saveAndFlush(mMarathonQuestStageReward);

        // Get all the mMarathonQuestStageRewardList where coin in DEFAULT_COIN or UPDATED_COIN
        defaultMMarathonQuestStageRewardShouldBeFound("coin.in=" + DEFAULT_COIN + "," + UPDATED_COIN);

        // Get all the mMarathonQuestStageRewardList where coin equals to UPDATED_COIN
        defaultMMarathonQuestStageRewardShouldNotBeFound("coin.in=" + UPDATED_COIN);
    }

    @Test
    @Transactional
    public void getAllMMarathonQuestStageRewardsByCoinIsNullOrNotNull() throws Exception {
        // Initialize the database
        mMarathonQuestStageRewardRepository.saveAndFlush(mMarathonQuestStageReward);

        // Get all the mMarathonQuestStageRewardList where coin is not null
        defaultMMarathonQuestStageRewardShouldBeFound("coin.specified=true");

        // Get all the mMarathonQuestStageRewardList where coin is null
        defaultMMarathonQuestStageRewardShouldNotBeFound("coin.specified=false");
    }

    @Test
    @Transactional
    public void getAllMMarathonQuestStageRewardsByCoinIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mMarathonQuestStageRewardRepository.saveAndFlush(mMarathonQuestStageReward);

        // Get all the mMarathonQuestStageRewardList where coin greater than or equals to DEFAULT_COIN
        defaultMMarathonQuestStageRewardShouldBeFound("coin.greaterOrEqualThan=" + DEFAULT_COIN);

        // Get all the mMarathonQuestStageRewardList where coin greater than or equals to UPDATED_COIN
        defaultMMarathonQuestStageRewardShouldNotBeFound("coin.greaterOrEqualThan=" + UPDATED_COIN);
    }

    @Test
    @Transactional
    public void getAllMMarathonQuestStageRewardsByCoinIsLessThanSomething() throws Exception {
        // Initialize the database
        mMarathonQuestStageRewardRepository.saveAndFlush(mMarathonQuestStageReward);

        // Get all the mMarathonQuestStageRewardList where coin less than or equals to DEFAULT_COIN
        defaultMMarathonQuestStageRewardShouldNotBeFound("coin.lessThan=" + DEFAULT_COIN);

        // Get all the mMarathonQuestStageRewardList where coin less than or equals to UPDATED_COIN
        defaultMMarathonQuestStageRewardShouldBeFound("coin.lessThan=" + UPDATED_COIN);
    }


    @Test
    @Transactional
    public void getAllMMarathonQuestStageRewardsByGuildPointIsEqualToSomething() throws Exception {
        // Initialize the database
        mMarathonQuestStageRewardRepository.saveAndFlush(mMarathonQuestStageReward);

        // Get all the mMarathonQuestStageRewardList where guildPoint equals to DEFAULT_GUILD_POINT
        defaultMMarathonQuestStageRewardShouldBeFound("guildPoint.equals=" + DEFAULT_GUILD_POINT);

        // Get all the mMarathonQuestStageRewardList where guildPoint equals to UPDATED_GUILD_POINT
        defaultMMarathonQuestStageRewardShouldNotBeFound("guildPoint.equals=" + UPDATED_GUILD_POINT);
    }

    @Test
    @Transactional
    public void getAllMMarathonQuestStageRewardsByGuildPointIsInShouldWork() throws Exception {
        // Initialize the database
        mMarathonQuestStageRewardRepository.saveAndFlush(mMarathonQuestStageReward);

        // Get all the mMarathonQuestStageRewardList where guildPoint in DEFAULT_GUILD_POINT or UPDATED_GUILD_POINT
        defaultMMarathonQuestStageRewardShouldBeFound("guildPoint.in=" + DEFAULT_GUILD_POINT + "," + UPDATED_GUILD_POINT);

        // Get all the mMarathonQuestStageRewardList where guildPoint equals to UPDATED_GUILD_POINT
        defaultMMarathonQuestStageRewardShouldNotBeFound("guildPoint.in=" + UPDATED_GUILD_POINT);
    }

    @Test
    @Transactional
    public void getAllMMarathonQuestStageRewardsByGuildPointIsNullOrNotNull() throws Exception {
        // Initialize the database
        mMarathonQuestStageRewardRepository.saveAndFlush(mMarathonQuestStageReward);

        // Get all the mMarathonQuestStageRewardList where guildPoint is not null
        defaultMMarathonQuestStageRewardShouldBeFound("guildPoint.specified=true");

        // Get all the mMarathonQuestStageRewardList where guildPoint is null
        defaultMMarathonQuestStageRewardShouldNotBeFound("guildPoint.specified=false");
    }

    @Test
    @Transactional
    public void getAllMMarathonQuestStageRewardsByGuildPointIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mMarathonQuestStageRewardRepository.saveAndFlush(mMarathonQuestStageReward);

        // Get all the mMarathonQuestStageRewardList where guildPoint greater than or equals to DEFAULT_GUILD_POINT
        defaultMMarathonQuestStageRewardShouldBeFound("guildPoint.greaterOrEqualThan=" + DEFAULT_GUILD_POINT);

        // Get all the mMarathonQuestStageRewardList where guildPoint greater than or equals to UPDATED_GUILD_POINT
        defaultMMarathonQuestStageRewardShouldNotBeFound("guildPoint.greaterOrEqualThan=" + UPDATED_GUILD_POINT);
    }

    @Test
    @Transactional
    public void getAllMMarathonQuestStageRewardsByGuildPointIsLessThanSomething() throws Exception {
        // Initialize the database
        mMarathonQuestStageRewardRepository.saveAndFlush(mMarathonQuestStageReward);

        // Get all the mMarathonQuestStageRewardList where guildPoint less than or equals to DEFAULT_GUILD_POINT
        defaultMMarathonQuestStageRewardShouldNotBeFound("guildPoint.lessThan=" + DEFAULT_GUILD_POINT);

        // Get all the mMarathonQuestStageRewardList where guildPoint less than or equals to UPDATED_GUILD_POINT
        defaultMMarathonQuestStageRewardShouldBeFound("guildPoint.lessThan=" + UPDATED_GUILD_POINT);
    }


    @Test
    @Transactional
    public void getAllMMarathonQuestStageRewardsByClearRewardGroupIdIsEqualToSomething() throws Exception {
        // Initialize the database
        mMarathonQuestStageRewardRepository.saveAndFlush(mMarathonQuestStageReward);

        // Get all the mMarathonQuestStageRewardList where clearRewardGroupId equals to DEFAULT_CLEAR_REWARD_GROUP_ID
        defaultMMarathonQuestStageRewardShouldBeFound("clearRewardGroupId.equals=" + DEFAULT_CLEAR_REWARD_GROUP_ID);

        // Get all the mMarathonQuestStageRewardList where clearRewardGroupId equals to UPDATED_CLEAR_REWARD_GROUP_ID
        defaultMMarathonQuestStageRewardShouldNotBeFound("clearRewardGroupId.equals=" + UPDATED_CLEAR_REWARD_GROUP_ID);
    }

    @Test
    @Transactional
    public void getAllMMarathonQuestStageRewardsByClearRewardGroupIdIsInShouldWork() throws Exception {
        // Initialize the database
        mMarathonQuestStageRewardRepository.saveAndFlush(mMarathonQuestStageReward);

        // Get all the mMarathonQuestStageRewardList where clearRewardGroupId in DEFAULT_CLEAR_REWARD_GROUP_ID or UPDATED_CLEAR_REWARD_GROUP_ID
        defaultMMarathonQuestStageRewardShouldBeFound("clearRewardGroupId.in=" + DEFAULT_CLEAR_REWARD_GROUP_ID + "," + UPDATED_CLEAR_REWARD_GROUP_ID);

        // Get all the mMarathonQuestStageRewardList where clearRewardGroupId equals to UPDATED_CLEAR_REWARD_GROUP_ID
        defaultMMarathonQuestStageRewardShouldNotBeFound("clearRewardGroupId.in=" + UPDATED_CLEAR_REWARD_GROUP_ID);
    }

    @Test
    @Transactional
    public void getAllMMarathonQuestStageRewardsByClearRewardGroupIdIsNullOrNotNull() throws Exception {
        // Initialize the database
        mMarathonQuestStageRewardRepository.saveAndFlush(mMarathonQuestStageReward);

        // Get all the mMarathonQuestStageRewardList where clearRewardGroupId is not null
        defaultMMarathonQuestStageRewardShouldBeFound("clearRewardGroupId.specified=true");

        // Get all the mMarathonQuestStageRewardList where clearRewardGroupId is null
        defaultMMarathonQuestStageRewardShouldNotBeFound("clearRewardGroupId.specified=false");
    }

    @Test
    @Transactional
    public void getAllMMarathonQuestStageRewardsByClearRewardGroupIdIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mMarathonQuestStageRewardRepository.saveAndFlush(mMarathonQuestStageReward);

        // Get all the mMarathonQuestStageRewardList where clearRewardGroupId greater than or equals to DEFAULT_CLEAR_REWARD_GROUP_ID
        defaultMMarathonQuestStageRewardShouldBeFound("clearRewardGroupId.greaterOrEqualThan=" + DEFAULT_CLEAR_REWARD_GROUP_ID);

        // Get all the mMarathonQuestStageRewardList where clearRewardGroupId greater than or equals to UPDATED_CLEAR_REWARD_GROUP_ID
        defaultMMarathonQuestStageRewardShouldNotBeFound("clearRewardGroupId.greaterOrEqualThan=" + UPDATED_CLEAR_REWARD_GROUP_ID);
    }

    @Test
    @Transactional
    public void getAllMMarathonQuestStageRewardsByClearRewardGroupIdIsLessThanSomething() throws Exception {
        // Initialize the database
        mMarathonQuestStageRewardRepository.saveAndFlush(mMarathonQuestStageReward);

        // Get all the mMarathonQuestStageRewardList where clearRewardGroupId less than or equals to DEFAULT_CLEAR_REWARD_GROUP_ID
        defaultMMarathonQuestStageRewardShouldNotBeFound("clearRewardGroupId.lessThan=" + DEFAULT_CLEAR_REWARD_GROUP_ID);

        // Get all the mMarathonQuestStageRewardList where clearRewardGroupId less than or equals to UPDATED_CLEAR_REWARD_GROUP_ID
        defaultMMarathonQuestStageRewardShouldBeFound("clearRewardGroupId.lessThan=" + UPDATED_CLEAR_REWARD_GROUP_ID);
    }


    @Test
    @Transactional
    public void getAllMMarathonQuestStageRewardsByClearRewardWeightIdIsEqualToSomething() throws Exception {
        // Initialize the database
        mMarathonQuestStageRewardRepository.saveAndFlush(mMarathonQuestStageReward);

        // Get all the mMarathonQuestStageRewardList where clearRewardWeightId equals to DEFAULT_CLEAR_REWARD_WEIGHT_ID
        defaultMMarathonQuestStageRewardShouldBeFound("clearRewardWeightId.equals=" + DEFAULT_CLEAR_REWARD_WEIGHT_ID);

        // Get all the mMarathonQuestStageRewardList where clearRewardWeightId equals to UPDATED_CLEAR_REWARD_WEIGHT_ID
        defaultMMarathonQuestStageRewardShouldNotBeFound("clearRewardWeightId.equals=" + UPDATED_CLEAR_REWARD_WEIGHT_ID);
    }

    @Test
    @Transactional
    public void getAllMMarathonQuestStageRewardsByClearRewardWeightIdIsInShouldWork() throws Exception {
        // Initialize the database
        mMarathonQuestStageRewardRepository.saveAndFlush(mMarathonQuestStageReward);

        // Get all the mMarathonQuestStageRewardList where clearRewardWeightId in DEFAULT_CLEAR_REWARD_WEIGHT_ID or UPDATED_CLEAR_REWARD_WEIGHT_ID
        defaultMMarathonQuestStageRewardShouldBeFound("clearRewardWeightId.in=" + DEFAULT_CLEAR_REWARD_WEIGHT_ID + "," + UPDATED_CLEAR_REWARD_WEIGHT_ID);

        // Get all the mMarathonQuestStageRewardList where clearRewardWeightId equals to UPDATED_CLEAR_REWARD_WEIGHT_ID
        defaultMMarathonQuestStageRewardShouldNotBeFound("clearRewardWeightId.in=" + UPDATED_CLEAR_REWARD_WEIGHT_ID);
    }

    @Test
    @Transactional
    public void getAllMMarathonQuestStageRewardsByClearRewardWeightIdIsNullOrNotNull() throws Exception {
        // Initialize the database
        mMarathonQuestStageRewardRepository.saveAndFlush(mMarathonQuestStageReward);

        // Get all the mMarathonQuestStageRewardList where clearRewardWeightId is not null
        defaultMMarathonQuestStageRewardShouldBeFound("clearRewardWeightId.specified=true");

        // Get all the mMarathonQuestStageRewardList where clearRewardWeightId is null
        defaultMMarathonQuestStageRewardShouldNotBeFound("clearRewardWeightId.specified=false");
    }

    @Test
    @Transactional
    public void getAllMMarathonQuestStageRewardsByClearRewardWeightIdIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mMarathonQuestStageRewardRepository.saveAndFlush(mMarathonQuestStageReward);

        // Get all the mMarathonQuestStageRewardList where clearRewardWeightId greater than or equals to DEFAULT_CLEAR_REWARD_WEIGHT_ID
        defaultMMarathonQuestStageRewardShouldBeFound("clearRewardWeightId.greaterOrEqualThan=" + DEFAULT_CLEAR_REWARD_WEIGHT_ID);

        // Get all the mMarathonQuestStageRewardList where clearRewardWeightId greater than or equals to UPDATED_CLEAR_REWARD_WEIGHT_ID
        defaultMMarathonQuestStageRewardShouldNotBeFound("clearRewardWeightId.greaterOrEqualThan=" + UPDATED_CLEAR_REWARD_WEIGHT_ID);
    }

    @Test
    @Transactional
    public void getAllMMarathonQuestStageRewardsByClearRewardWeightIdIsLessThanSomething() throws Exception {
        // Initialize the database
        mMarathonQuestStageRewardRepository.saveAndFlush(mMarathonQuestStageReward);

        // Get all the mMarathonQuestStageRewardList where clearRewardWeightId less than or equals to DEFAULT_CLEAR_REWARD_WEIGHT_ID
        defaultMMarathonQuestStageRewardShouldNotBeFound("clearRewardWeightId.lessThan=" + DEFAULT_CLEAR_REWARD_WEIGHT_ID);

        // Get all the mMarathonQuestStageRewardList where clearRewardWeightId less than or equals to UPDATED_CLEAR_REWARD_WEIGHT_ID
        defaultMMarathonQuestStageRewardShouldBeFound("clearRewardWeightId.lessThan=" + UPDATED_CLEAR_REWARD_WEIGHT_ID);
    }


    @Test
    @Transactional
    public void getAllMMarathonQuestStageRewardsByAchievementRewardGroupIdIsEqualToSomething() throws Exception {
        // Initialize the database
        mMarathonQuestStageRewardRepository.saveAndFlush(mMarathonQuestStageReward);

        // Get all the mMarathonQuestStageRewardList where achievementRewardGroupId equals to DEFAULT_ACHIEVEMENT_REWARD_GROUP_ID
        defaultMMarathonQuestStageRewardShouldBeFound("achievementRewardGroupId.equals=" + DEFAULT_ACHIEVEMENT_REWARD_GROUP_ID);

        // Get all the mMarathonQuestStageRewardList where achievementRewardGroupId equals to UPDATED_ACHIEVEMENT_REWARD_GROUP_ID
        defaultMMarathonQuestStageRewardShouldNotBeFound("achievementRewardGroupId.equals=" + UPDATED_ACHIEVEMENT_REWARD_GROUP_ID);
    }

    @Test
    @Transactional
    public void getAllMMarathonQuestStageRewardsByAchievementRewardGroupIdIsInShouldWork() throws Exception {
        // Initialize the database
        mMarathonQuestStageRewardRepository.saveAndFlush(mMarathonQuestStageReward);

        // Get all the mMarathonQuestStageRewardList where achievementRewardGroupId in DEFAULT_ACHIEVEMENT_REWARD_GROUP_ID or UPDATED_ACHIEVEMENT_REWARD_GROUP_ID
        defaultMMarathonQuestStageRewardShouldBeFound("achievementRewardGroupId.in=" + DEFAULT_ACHIEVEMENT_REWARD_GROUP_ID + "," + UPDATED_ACHIEVEMENT_REWARD_GROUP_ID);

        // Get all the mMarathonQuestStageRewardList where achievementRewardGroupId equals to UPDATED_ACHIEVEMENT_REWARD_GROUP_ID
        defaultMMarathonQuestStageRewardShouldNotBeFound("achievementRewardGroupId.in=" + UPDATED_ACHIEVEMENT_REWARD_GROUP_ID);
    }

    @Test
    @Transactional
    public void getAllMMarathonQuestStageRewardsByAchievementRewardGroupIdIsNullOrNotNull() throws Exception {
        // Initialize the database
        mMarathonQuestStageRewardRepository.saveAndFlush(mMarathonQuestStageReward);

        // Get all the mMarathonQuestStageRewardList where achievementRewardGroupId is not null
        defaultMMarathonQuestStageRewardShouldBeFound("achievementRewardGroupId.specified=true");

        // Get all the mMarathonQuestStageRewardList where achievementRewardGroupId is null
        defaultMMarathonQuestStageRewardShouldNotBeFound("achievementRewardGroupId.specified=false");
    }

    @Test
    @Transactional
    public void getAllMMarathonQuestStageRewardsByAchievementRewardGroupIdIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mMarathonQuestStageRewardRepository.saveAndFlush(mMarathonQuestStageReward);

        // Get all the mMarathonQuestStageRewardList where achievementRewardGroupId greater than or equals to DEFAULT_ACHIEVEMENT_REWARD_GROUP_ID
        defaultMMarathonQuestStageRewardShouldBeFound("achievementRewardGroupId.greaterOrEqualThan=" + DEFAULT_ACHIEVEMENT_REWARD_GROUP_ID);

        // Get all the mMarathonQuestStageRewardList where achievementRewardGroupId greater than or equals to UPDATED_ACHIEVEMENT_REWARD_GROUP_ID
        defaultMMarathonQuestStageRewardShouldNotBeFound("achievementRewardGroupId.greaterOrEqualThan=" + UPDATED_ACHIEVEMENT_REWARD_GROUP_ID);
    }

    @Test
    @Transactional
    public void getAllMMarathonQuestStageRewardsByAchievementRewardGroupIdIsLessThanSomething() throws Exception {
        // Initialize the database
        mMarathonQuestStageRewardRepository.saveAndFlush(mMarathonQuestStageReward);

        // Get all the mMarathonQuestStageRewardList where achievementRewardGroupId less than or equals to DEFAULT_ACHIEVEMENT_REWARD_GROUP_ID
        defaultMMarathonQuestStageRewardShouldNotBeFound("achievementRewardGroupId.lessThan=" + DEFAULT_ACHIEVEMENT_REWARD_GROUP_ID);

        // Get all the mMarathonQuestStageRewardList where achievementRewardGroupId less than or equals to UPDATED_ACHIEVEMENT_REWARD_GROUP_ID
        defaultMMarathonQuestStageRewardShouldBeFound("achievementRewardGroupId.lessThan=" + UPDATED_ACHIEVEMENT_REWARD_GROUP_ID);
    }


    @Test
    @Transactional
    public void getAllMMarathonQuestStageRewardsByCoopGroupIdIsEqualToSomething() throws Exception {
        // Initialize the database
        mMarathonQuestStageRewardRepository.saveAndFlush(mMarathonQuestStageReward);

        // Get all the mMarathonQuestStageRewardList where coopGroupId equals to DEFAULT_COOP_GROUP_ID
        defaultMMarathonQuestStageRewardShouldBeFound("coopGroupId.equals=" + DEFAULT_COOP_GROUP_ID);

        // Get all the mMarathonQuestStageRewardList where coopGroupId equals to UPDATED_COOP_GROUP_ID
        defaultMMarathonQuestStageRewardShouldNotBeFound("coopGroupId.equals=" + UPDATED_COOP_GROUP_ID);
    }

    @Test
    @Transactional
    public void getAllMMarathonQuestStageRewardsByCoopGroupIdIsInShouldWork() throws Exception {
        // Initialize the database
        mMarathonQuestStageRewardRepository.saveAndFlush(mMarathonQuestStageReward);

        // Get all the mMarathonQuestStageRewardList where coopGroupId in DEFAULT_COOP_GROUP_ID or UPDATED_COOP_GROUP_ID
        defaultMMarathonQuestStageRewardShouldBeFound("coopGroupId.in=" + DEFAULT_COOP_GROUP_ID + "," + UPDATED_COOP_GROUP_ID);

        // Get all the mMarathonQuestStageRewardList where coopGroupId equals to UPDATED_COOP_GROUP_ID
        defaultMMarathonQuestStageRewardShouldNotBeFound("coopGroupId.in=" + UPDATED_COOP_GROUP_ID);
    }

    @Test
    @Transactional
    public void getAllMMarathonQuestStageRewardsByCoopGroupIdIsNullOrNotNull() throws Exception {
        // Initialize the database
        mMarathonQuestStageRewardRepository.saveAndFlush(mMarathonQuestStageReward);

        // Get all the mMarathonQuestStageRewardList where coopGroupId is not null
        defaultMMarathonQuestStageRewardShouldBeFound("coopGroupId.specified=true");

        // Get all the mMarathonQuestStageRewardList where coopGroupId is null
        defaultMMarathonQuestStageRewardShouldNotBeFound("coopGroupId.specified=false");
    }

    @Test
    @Transactional
    public void getAllMMarathonQuestStageRewardsByCoopGroupIdIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mMarathonQuestStageRewardRepository.saveAndFlush(mMarathonQuestStageReward);

        // Get all the mMarathonQuestStageRewardList where coopGroupId greater than or equals to DEFAULT_COOP_GROUP_ID
        defaultMMarathonQuestStageRewardShouldBeFound("coopGroupId.greaterOrEqualThan=" + DEFAULT_COOP_GROUP_ID);

        // Get all the mMarathonQuestStageRewardList where coopGroupId greater than or equals to UPDATED_COOP_GROUP_ID
        defaultMMarathonQuestStageRewardShouldNotBeFound("coopGroupId.greaterOrEqualThan=" + UPDATED_COOP_GROUP_ID);
    }

    @Test
    @Transactional
    public void getAllMMarathonQuestStageRewardsByCoopGroupIdIsLessThanSomething() throws Exception {
        // Initialize the database
        mMarathonQuestStageRewardRepository.saveAndFlush(mMarathonQuestStageReward);

        // Get all the mMarathonQuestStageRewardList where coopGroupId less than or equals to DEFAULT_COOP_GROUP_ID
        defaultMMarathonQuestStageRewardShouldNotBeFound("coopGroupId.lessThan=" + DEFAULT_COOP_GROUP_ID);

        // Get all the mMarathonQuestStageRewardList where coopGroupId less than or equals to UPDATED_COOP_GROUP_ID
        defaultMMarathonQuestStageRewardShouldBeFound("coopGroupId.lessThan=" + UPDATED_COOP_GROUP_ID);
    }


    @Test
    @Transactional
    public void getAllMMarathonQuestStageRewardsBySpecialRewardGroupIdIsEqualToSomething() throws Exception {
        // Initialize the database
        mMarathonQuestStageRewardRepository.saveAndFlush(mMarathonQuestStageReward);

        // Get all the mMarathonQuestStageRewardList where specialRewardGroupId equals to DEFAULT_SPECIAL_REWARD_GROUP_ID
        defaultMMarathonQuestStageRewardShouldBeFound("specialRewardGroupId.equals=" + DEFAULT_SPECIAL_REWARD_GROUP_ID);

        // Get all the mMarathonQuestStageRewardList where specialRewardGroupId equals to UPDATED_SPECIAL_REWARD_GROUP_ID
        defaultMMarathonQuestStageRewardShouldNotBeFound("specialRewardGroupId.equals=" + UPDATED_SPECIAL_REWARD_GROUP_ID);
    }

    @Test
    @Transactional
    public void getAllMMarathonQuestStageRewardsBySpecialRewardGroupIdIsInShouldWork() throws Exception {
        // Initialize the database
        mMarathonQuestStageRewardRepository.saveAndFlush(mMarathonQuestStageReward);

        // Get all the mMarathonQuestStageRewardList where specialRewardGroupId in DEFAULT_SPECIAL_REWARD_GROUP_ID or UPDATED_SPECIAL_REWARD_GROUP_ID
        defaultMMarathonQuestStageRewardShouldBeFound("specialRewardGroupId.in=" + DEFAULT_SPECIAL_REWARD_GROUP_ID + "," + UPDATED_SPECIAL_REWARD_GROUP_ID);

        // Get all the mMarathonQuestStageRewardList where specialRewardGroupId equals to UPDATED_SPECIAL_REWARD_GROUP_ID
        defaultMMarathonQuestStageRewardShouldNotBeFound("specialRewardGroupId.in=" + UPDATED_SPECIAL_REWARD_GROUP_ID);
    }

    @Test
    @Transactional
    public void getAllMMarathonQuestStageRewardsBySpecialRewardGroupIdIsNullOrNotNull() throws Exception {
        // Initialize the database
        mMarathonQuestStageRewardRepository.saveAndFlush(mMarathonQuestStageReward);

        // Get all the mMarathonQuestStageRewardList where specialRewardGroupId is not null
        defaultMMarathonQuestStageRewardShouldBeFound("specialRewardGroupId.specified=true");

        // Get all the mMarathonQuestStageRewardList where specialRewardGroupId is null
        defaultMMarathonQuestStageRewardShouldNotBeFound("specialRewardGroupId.specified=false");
    }

    @Test
    @Transactional
    public void getAllMMarathonQuestStageRewardsBySpecialRewardGroupIdIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mMarathonQuestStageRewardRepository.saveAndFlush(mMarathonQuestStageReward);

        // Get all the mMarathonQuestStageRewardList where specialRewardGroupId greater than or equals to DEFAULT_SPECIAL_REWARD_GROUP_ID
        defaultMMarathonQuestStageRewardShouldBeFound("specialRewardGroupId.greaterOrEqualThan=" + DEFAULT_SPECIAL_REWARD_GROUP_ID);

        // Get all the mMarathonQuestStageRewardList where specialRewardGroupId greater than or equals to UPDATED_SPECIAL_REWARD_GROUP_ID
        defaultMMarathonQuestStageRewardShouldNotBeFound("specialRewardGroupId.greaterOrEqualThan=" + UPDATED_SPECIAL_REWARD_GROUP_ID);
    }

    @Test
    @Transactional
    public void getAllMMarathonQuestStageRewardsBySpecialRewardGroupIdIsLessThanSomething() throws Exception {
        // Initialize the database
        mMarathonQuestStageRewardRepository.saveAndFlush(mMarathonQuestStageReward);

        // Get all the mMarathonQuestStageRewardList where specialRewardGroupId less than or equals to DEFAULT_SPECIAL_REWARD_GROUP_ID
        defaultMMarathonQuestStageRewardShouldNotBeFound("specialRewardGroupId.lessThan=" + DEFAULT_SPECIAL_REWARD_GROUP_ID);

        // Get all the mMarathonQuestStageRewardList where specialRewardGroupId less than or equals to UPDATED_SPECIAL_REWARD_GROUP_ID
        defaultMMarathonQuestStageRewardShouldBeFound("specialRewardGroupId.lessThan=" + UPDATED_SPECIAL_REWARD_GROUP_ID);
    }


    @Test
    @Transactional
    public void getAllMMarathonQuestStageRewardsBySpecialRewardAmountIsEqualToSomething() throws Exception {
        // Initialize the database
        mMarathonQuestStageRewardRepository.saveAndFlush(mMarathonQuestStageReward);

        // Get all the mMarathonQuestStageRewardList where specialRewardAmount equals to DEFAULT_SPECIAL_REWARD_AMOUNT
        defaultMMarathonQuestStageRewardShouldBeFound("specialRewardAmount.equals=" + DEFAULT_SPECIAL_REWARD_AMOUNT);

        // Get all the mMarathonQuestStageRewardList where specialRewardAmount equals to UPDATED_SPECIAL_REWARD_AMOUNT
        defaultMMarathonQuestStageRewardShouldNotBeFound("specialRewardAmount.equals=" + UPDATED_SPECIAL_REWARD_AMOUNT);
    }

    @Test
    @Transactional
    public void getAllMMarathonQuestStageRewardsBySpecialRewardAmountIsInShouldWork() throws Exception {
        // Initialize the database
        mMarathonQuestStageRewardRepository.saveAndFlush(mMarathonQuestStageReward);

        // Get all the mMarathonQuestStageRewardList where specialRewardAmount in DEFAULT_SPECIAL_REWARD_AMOUNT or UPDATED_SPECIAL_REWARD_AMOUNT
        defaultMMarathonQuestStageRewardShouldBeFound("specialRewardAmount.in=" + DEFAULT_SPECIAL_REWARD_AMOUNT + "," + UPDATED_SPECIAL_REWARD_AMOUNT);

        // Get all the mMarathonQuestStageRewardList where specialRewardAmount equals to UPDATED_SPECIAL_REWARD_AMOUNT
        defaultMMarathonQuestStageRewardShouldNotBeFound("specialRewardAmount.in=" + UPDATED_SPECIAL_REWARD_AMOUNT);
    }

    @Test
    @Transactional
    public void getAllMMarathonQuestStageRewardsBySpecialRewardAmountIsNullOrNotNull() throws Exception {
        // Initialize the database
        mMarathonQuestStageRewardRepository.saveAndFlush(mMarathonQuestStageReward);

        // Get all the mMarathonQuestStageRewardList where specialRewardAmount is not null
        defaultMMarathonQuestStageRewardShouldBeFound("specialRewardAmount.specified=true");

        // Get all the mMarathonQuestStageRewardList where specialRewardAmount is null
        defaultMMarathonQuestStageRewardShouldNotBeFound("specialRewardAmount.specified=false");
    }

    @Test
    @Transactional
    public void getAllMMarathonQuestStageRewardsBySpecialRewardAmountIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mMarathonQuestStageRewardRepository.saveAndFlush(mMarathonQuestStageReward);

        // Get all the mMarathonQuestStageRewardList where specialRewardAmount greater than or equals to DEFAULT_SPECIAL_REWARD_AMOUNT
        defaultMMarathonQuestStageRewardShouldBeFound("specialRewardAmount.greaterOrEqualThan=" + DEFAULT_SPECIAL_REWARD_AMOUNT);

        // Get all the mMarathonQuestStageRewardList where specialRewardAmount greater than or equals to UPDATED_SPECIAL_REWARD_AMOUNT
        defaultMMarathonQuestStageRewardShouldNotBeFound("specialRewardAmount.greaterOrEqualThan=" + UPDATED_SPECIAL_REWARD_AMOUNT);
    }

    @Test
    @Transactional
    public void getAllMMarathonQuestStageRewardsBySpecialRewardAmountIsLessThanSomething() throws Exception {
        // Initialize the database
        mMarathonQuestStageRewardRepository.saveAndFlush(mMarathonQuestStageReward);

        // Get all the mMarathonQuestStageRewardList where specialRewardAmount less than or equals to DEFAULT_SPECIAL_REWARD_AMOUNT
        defaultMMarathonQuestStageRewardShouldNotBeFound("specialRewardAmount.lessThan=" + DEFAULT_SPECIAL_REWARD_AMOUNT);

        // Get all the mMarathonQuestStageRewardList where specialRewardAmount less than or equals to UPDATED_SPECIAL_REWARD_AMOUNT
        defaultMMarathonQuestStageRewardShouldBeFound("specialRewardAmount.lessThan=" + UPDATED_SPECIAL_REWARD_AMOUNT);
    }


    @Test
    @Transactional
    public void getAllMMarathonQuestStageRewardsByGoalRewardGroupIdIsEqualToSomething() throws Exception {
        // Initialize the database
        mMarathonQuestStageRewardRepository.saveAndFlush(mMarathonQuestStageReward);

        // Get all the mMarathonQuestStageRewardList where goalRewardGroupId equals to DEFAULT_GOAL_REWARD_GROUP_ID
        defaultMMarathonQuestStageRewardShouldBeFound("goalRewardGroupId.equals=" + DEFAULT_GOAL_REWARD_GROUP_ID);

        // Get all the mMarathonQuestStageRewardList where goalRewardGroupId equals to UPDATED_GOAL_REWARD_GROUP_ID
        defaultMMarathonQuestStageRewardShouldNotBeFound("goalRewardGroupId.equals=" + UPDATED_GOAL_REWARD_GROUP_ID);
    }

    @Test
    @Transactional
    public void getAllMMarathonQuestStageRewardsByGoalRewardGroupIdIsInShouldWork() throws Exception {
        // Initialize the database
        mMarathonQuestStageRewardRepository.saveAndFlush(mMarathonQuestStageReward);

        // Get all the mMarathonQuestStageRewardList where goalRewardGroupId in DEFAULT_GOAL_REWARD_GROUP_ID or UPDATED_GOAL_REWARD_GROUP_ID
        defaultMMarathonQuestStageRewardShouldBeFound("goalRewardGroupId.in=" + DEFAULT_GOAL_REWARD_GROUP_ID + "," + UPDATED_GOAL_REWARD_GROUP_ID);

        // Get all the mMarathonQuestStageRewardList where goalRewardGroupId equals to UPDATED_GOAL_REWARD_GROUP_ID
        defaultMMarathonQuestStageRewardShouldNotBeFound("goalRewardGroupId.in=" + UPDATED_GOAL_REWARD_GROUP_ID);
    }

    @Test
    @Transactional
    public void getAllMMarathonQuestStageRewardsByGoalRewardGroupIdIsNullOrNotNull() throws Exception {
        // Initialize the database
        mMarathonQuestStageRewardRepository.saveAndFlush(mMarathonQuestStageReward);

        // Get all the mMarathonQuestStageRewardList where goalRewardGroupId is not null
        defaultMMarathonQuestStageRewardShouldBeFound("goalRewardGroupId.specified=true");

        // Get all the mMarathonQuestStageRewardList where goalRewardGroupId is null
        defaultMMarathonQuestStageRewardShouldNotBeFound("goalRewardGroupId.specified=false");
    }

    @Test
    @Transactional
    public void getAllMMarathonQuestStageRewardsByGoalRewardGroupIdIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mMarathonQuestStageRewardRepository.saveAndFlush(mMarathonQuestStageReward);

        // Get all the mMarathonQuestStageRewardList where goalRewardGroupId greater than or equals to DEFAULT_GOAL_REWARD_GROUP_ID
        defaultMMarathonQuestStageRewardShouldBeFound("goalRewardGroupId.greaterOrEqualThan=" + DEFAULT_GOAL_REWARD_GROUP_ID);

        // Get all the mMarathonQuestStageRewardList where goalRewardGroupId greater than or equals to UPDATED_GOAL_REWARD_GROUP_ID
        defaultMMarathonQuestStageRewardShouldNotBeFound("goalRewardGroupId.greaterOrEqualThan=" + UPDATED_GOAL_REWARD_GROUP_ID);
    }

    @Test
    @Transactional
    public void getAllMMarathonQuestStageRewardsByGoalRewardGroupIdIsLessThanSomething() throws Exception {
        // Initialize the database
        mMarathonQuestStageRewardRepository.saveAndFlush(mMarathonQuestStageReward);

        // Get all the mMarathonQuestStageRewardList where goalRewardGroupId less than or equals to DEFAULT_GOAL_REWARD_GROUP_ID
        defaultMMarathonQuestStageRewardShouldNotBeFound("goalRewardGroupId.lessThan=" + DEFAULT_GOAL_REWARD_GROUP_ID);

        // Get all the mMarathonQuestStageRewardList where goalRewardGroupId less than or equals to UPDATED_GOAL_REWARD_GROUP_ID
        defaultMMarathonQuestStageRewardShouldBeFound("goalRewardGroupId.lessThan=" + UPDATED_GOAL_REWARD_GROUP_ID);
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultMMarathonQuestStageRewardShouldBeFound(String filter) throws Exception {
        restMMarathonQuestStageRewardMockMvc.perform(get("/api/m-marathon-quest-stage-rewards?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(mMarathonQuestStageReward.getId().intValue())))
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
        restMMarathonQuestStageRewardMockMvc.perform(get("/api/m-marathon-quest-stage-rewards/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultMMarathonQuestStageRewardShouldNotBeFound(String filter) throws Exception {
        restMMarathonQuestStageRewardMockMvc.perform(get("/api/m-marathon-quest-stage-rewards?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restMMarathonQuestStageRewardMockMvc.perform(get("/api/m-marathon-quest-stage-rewards/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("0"));
    }


    @Test
    @Transactional
    public void getNonExistingMMarathonQuestStageReward() throws Exception {
        // Get the mMarathonQuestStageReward
        restMMarathonQuestStageRewardMockMvc.perform(get("/api/m-marathon-quest-stage-rewards/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateMMarathonQuestStageReward() throws Exception {
        // Initialize the database
        mMarathonQuestStageRewardRepository.saveAndFlush(mMarathonQuestStageReward);

        int databaseSizeBeforeUpdate = mMarathonQuestStageRewardRepository.findAll().size();

        // Update the mMarathonQuestStageReward
        MMarathonQuestStageReward updatedMMarathonQuestStageReward = mMarathonQuestStageRewardRepository.findById(mMarathonQuestStageReward.getId()).get();
        // Disconnect from session so that the updates on updatedMMarathonQuestStageReward are not directly saved in db
        em.detach(updatedMMarathonQuestStageReward);
        updatedMMarathonQuestStageReward
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
        MMarathonQuestStageRewardDTO mMarathonQuestStageRewardDTO = mMarathonQuestStageRewardMapper.toDto(updatedMMarathonQuestStageReward);

        restMMarathonQuestStageRewardMockMvc.perform(put("/api/m-marathon-quest-stage-rewards")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mMarathonQuestStageRewardDTO)))
            .andExpect(status().isOk());

        // Validate the MMarathonQuestStageReward in the database
        List<MMarathonQuestStageReward> mMarathonQuestStageRewardList = mMarathonQuestStageRewardRepository.findAll();
        assertThat(mMarathonQuestStageRewardList).hasSize(databaseSizeBeforeUpdate);
        MMarathonQuestStageReward testMMarathonQuestStageReward = mMarathonQuestStageRewardList.get(mMarathonQuestStageRewardList.size() - 1);
        assertThat(testMMarathonQuestStageReward.getStageId()).isEqualTo(UPDATED_STAGE_ID);
        assertThat(testMMarathonQuestStageReward.getExp()).isEqualTo(UPDATED_EXP);
        assertThat(testMMarathonQuestStageReward.getCoin()).isEqualTo(UPDATED_COIN);
        assertThat(testMMarathonQuestStageReward.getGuildPoint()).isEqualTo(UPDATED_GUILD_POINT);
        assertThat(testMMarathonQuestStageReward.getClearRewardGroupId()).isEqualTo(UPDATED_CLEAR_REWARD_GROUP_ID);
        assertThat(testMMarathonQuestStageReward.getClearRewardWeightId()).isEqualTo(UPDATED_CLEAR_REWARD_WEIGHT_ID);
        assertThat(testMMarathonQuestStageReward.getAchievementRewardGroupId()).isEqualTo(UPDATED_ACHIEVEMENT_REWARD_GROUP_ID);
        assertThat(testMMarathonQuestStageReward.getCoopGroupId()).isEqualTo(UPDATED_COOP_GROUP_ID);
        assertThat(testMMarathonQuestStageReward.getSpecialRewardGroupId()).isEqualTo(UPDATED_SPECIAL_REWARD_GROUP_ID);
        assertThat(testMMarathonQuestStageReward.getSpecialRewardAmount()).isEqualTo(UPDATED_SPECIAL_REWARD_AMOUNT);
        assertThat(testMMarathonQuestStageReward.getGoalRewardGroupId()).isEqualTo(UPDATED_GOAL_REWARD_GROUP_ID);
    }

    @Test
    @Transactional
    public void updateNonExistingMMarathonQuestStageReward() throws Exception {
        int databaseSizeBeforeUpdate = mMarathonQuestStageRewardRepository.findAll().size();

        // Create the MMarathonQuestStageReward
        MMarathonQuestStageRewardDTO mMarathonQuestStageRewardDTO = mMarathonQuestStageRewardMapper.toDto(mMarathonQuestStageReward);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restMMarathonQuestStageRewardMockMvc.perform(put("/api/m-marathon-quest-stage-rewards")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mMarathonQuestStageRewardDTO)))
            .andExpect(status().isBadRequest());

        // Validate the MMarathonQuestStageReward in the database
        List<MMarathonQuestStageReward> mMarathonQuestStageRewardList = mMarathonQuestStageRewardRepository.findAll();
        assertThat(mMarathonQuestStageRewardList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteMMarathonQuestStageReward() throws Exception {
        // Initialize the database
        mMarathonQuestStageRewardRepository.saveAndFlush(mMarathonQuestStageReward);

        int databaseSizeBeforeDelete = mMarathonQuestStageRewardRepository.findAll().size();

        // Delete the mMarathonQuestStageReward
        restMMarathonQuestStageRewardMockMvc.perform(delete("/api/m-marathon-quest-stage-rewards/{id}", mMarathonQuestStageReward.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database is empty
        List<MMarathonQuestStageReward> mMarathonQuestStageRewardList = mMarathonQuestStageRewardRepository.findAll();
        assertThat(mMarathonQuestStageRewardList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(MMarathonQuestStageReward.class);
        MMarathonQuestStageReward mMarathonQuestStageReward1 = new MMarathonQuestStageReward();
        mMarathonQuestStageReward1.setId(1L);
        MMarathonQuestStageReward mMarathonQuestStageReward2 = new MMarathonQuestStageReward();
        mMarathonQuestStageReward2.setId(mMarathonQuestStageReward1.getId());
        assertThat(mMarathonQuestStageReward1).isEqualTo(mMarathonQuestStageReward2);
        mMarathonQuestStageReward2.setId(2L);
        assertThat(mMarathonQuestStageReward1).isNotEqualTo(mMarathonQuestStageReward2);
        mMarathonQuestStageReward1.setId(null);
        assertThat(mMarathonQuestStageReward1).isNotEqualTo(mMarathonQuestStageReward2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(MMarathonQuestStageRewardDTO.class);
        MMarathonQuestStageRewardDTO mMarathonQuestStageRewardDTO1 = new MMarathonQuestStageRewardDTO();
        mMarathonQuestStageRewardDTO1.setId(1L);
        MMarathonQuestStageRewardDTO mMarathonQuestStageRewardDTO2 = new MMarathonQuestStageRewardDTO();
        assertThat(mMarathonQuestStageRewardDTO1).isNotEqualTo(mMarathonQuestStageRewardDTO2);
        mMarathonQuestStageRewardDTO2.setId(mMarathonQuestStageRewardDTO1.getId());
        assertThat(mMarathonQuestStageRewardDTO1).isEqualTo(mMarathonQuestStageRewardDTO2);
        mMarathonQuestStageRewardDTO2.setId(2L);
        assertThat(mMarathonQuestStageRewardDTO1).isNotEqualTo(mMarathonQuestStageRewardDTO2);
        mMarathonQuestStageRewardDTO1.setId(null);
        assertThat(mMarathonQuestStageRewardDTO1).isNotEqualTo(mMarathonQuestStageRewardDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(mMarathonQuestStageRewardMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(mMarathonQuestStageRewardMapper.fromId(null)).isNull();
    }
}
