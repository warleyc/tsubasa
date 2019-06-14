package io.shm.tsubasa.web.rest;

import io.shm.tsubasa.TsubasaApp;
import io.shm.tsubasa.domain.MGuerillaQuestStageReward;
import io.shm.tsubasa.repository.MGuerillaQuestStageRewardRepository;
import io.shm.tsubasa.service.MGuerillaQuestStageRewardService;
import io.shm.tsubasa.service.dto.MGuerillaQuestStageRewardDTO;
import io.shm.tsubasa.service.mapper.MGuerillaQuestStageRewardMapper;
import io.shm.tsubasa.web.rest.errors.ExceptionTranslator;
import io.shm.tsubasa.service.dto.MGuerillaQuestStageRewardCriteria;
import io.shm.tsubasa.service.MGuerillaQuestStageRewardQueryService;

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
 * Integration tests for the {@Link MGuerillaQuestStageRewardResource} REST controller.
 */
@SpringBootTest(classes = TsubasaApp.class)
public class MGuerillaQuestStageRewardResourceIT {

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
    private MGuerillaQuestStageRewardRepository mGuerillaQuestStageRewardRepository;

    @Autowired
    private MGuerillaQuestStageRewardMapper mGuerillaQuestStageRewardMapper;

    @Autowired
    private MGuerillaQuestStageRewardService mGuerillaQuestStageRewardService;

    @Autowired
    private MGuerillaQuestStageRewardQueryService mGuerillaQuestStageRewardQueryService;

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

    private MockMvc restMGuerillaQuestStageRewardMockMvc;

    private MGuerillaQuestStageReward mGuerillaQuestStageReward;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final MGuerillaQuestStageRewardResource mGuerillaQuestStageRewardResource = new MGuerillaQuestStageRewardResource(mGuerillaQuestStageRewardService, mGuerillaQuestStageRewardQueryService);
        this.restMGuerillaQuestStageRewardMockMvc = MockMvcBuilders.standaloneSetup(mGuerillaQuestStageRewardResource)
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
    public static MGuerillaQuestStageReward createEntity(EntityManager em) {
        MGuerillaQuestStageReward mGuerillaQuestStageReward = new MGuerillaQuestStageReward()
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
        return mGuerillaQuestStageReward;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static MGuerillaQuestStageReward createUpdatedEntity(EntityManager em) {
        MGuerillaQuestStageReward mGuerillaQuestStageReward = new MGuerillaQuestStageReward()
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
        return mGuerillaQuestStageReward;
    }

    @BeforeEach
    public void initTest() {
        mGuerillaQuestStageReward = createEntity(em);
    }

    @Test
    @Transactional
    public void createMGuerillaQuestStageReward() throws Exception {
        int databaseSizeBeforeCreate = mGuerillaQuestStageRewardRepository.findAll().size();

        // Create the MGuerillaQuestStageReward
        MGuerillaQuestStageRewardDTO mGuerillaQuestStageRewardDTO = mGuerillaQuestStageRewardMapper.toDto(mGuerillaQuestStageReward);
        restMGuerillaQuestStageRewardMockMvc.perform(post("/api/m-guerilla-quest-stage-rewards")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mGuerillaQuestStageRewardDTO)))
            .andExpect(status().isCreated());

        // Validate the MGuerillaQuestStageReward in the database
        List<MGuerillaQuestStageReward> mGuerillaQuestStageRewardList = mGuerillaQuestStageRewardRepository.findAll();
        assertThat(mGuerillaQuestStageRewardList).hasSize(databaseSizeBeforeCreate + 1);
        MGuerillaQuestStageReward testMGuerillaQuestStageReward = mGuerillaQuestStageRewardList.get(mGuerillaQuestStageRewardList.size() - 1);
        assertThat(testMGuerillaQuestStageReward.getStageId()).isEqualTo(DEFAULT_STAGE_ID);
        assertThat(testMGuerillaQuestStageReward.getExp()).isEqualTo(DEFAULT_EXP);
        assertThat(testMGuerillaQuestStageReward.getCoin()).isEqualTo(DEFAULT_COIN);
        assertThat(testMGuerillaQuestStageReward.getGuildPoint()).isEqualTo(DEFAULT_GUILD_POINT);
        assertThat(testMGuerillaQuestStageReward.getClearRewardGroupId()).isEqualTo(DEFAULT_CLEAR_REWARD_GROUP_ID);
        assertThat(testMGuerillaQuestStageReward.getClearRewardWeightId()).isEqualTo(DEFAULT_CLEAR_REWARD_WEIGHT_ID);
        assertThat(testMGuerillaQuestStageReward.getAchievementRewardGroupId()).isEqualTo(DEFAULT_ACHIEVEMENT_REWARD_GROUP_ID);
        assertThat(testMGuerillaQuestStageReward.getCoopGroupId()).isEqualTo(DEFAULT_COOP_GROUP_ID);
        assertThat(testMGuerillaQuestStageReward.getSpecialRewardGroupId()).isEqualTo(DEFAULT_SPECIAL_REWARD_GROUP_ID);
        assertThat(testMGuerillaQuestStageReward.getSpecialRewardAmount()).isEqualTo(DEFAULT_SPECIAL_REWARD_AMOUNT);
        assertThat(testMGuerillaQuestStageReward.getGoalRewardGroupId()).isEqualTo(DEFAULT_GOAL_REWARD_GROUP_ID);
    }

    @Test
    @Transactional
    public void createMGuerillaQuestStageRewardWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = mGuerillaQuestStageRewardRepository.findAll().size();

        // Create the MGuerillaQuestStageReward with an existing ID
        mGuerillaQuestStageReward.setId(1L);
        MGuerillaQuestStageRewardDTO mGuerillaQuestStageRewardDTO = mGuerillaQuestStageRewardMapper.toDto(mGuerillaQuestStageReward);

        // An entity with an existing ID cannot be created, so this API call must fail
        restMGuerillaQuestStageRewardMockMvc.perform(post("/api/m-guerilla-quest-stage-rewards")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mGuerillaQuestStageRewardDTO)))
            .andExpect(status().isBadRequest());

        // Validate the MGuerillaQuestStageReward in the database
        List<MGuerillaQuestStageReward> mGuerillaQuestStageRewardList = mGuerillaQuestStageRewardRepository.findAll();
        assertThat(mGuerillaQuestStageRewardList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkStageIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = mGuerillaQuestStageRewardRepository.findAll().size();
        // set the field null
        mGuerillaQuestStageReward.setStageId(null);

        // Create the MGuerillaQuestStageReward, which fails.
        MGuerillaQuestStageRewardDTO mGuerillaQuestStageRewardDTO = mGuerillaQuestStageRewardMapper.toDto(mGuerillaQuestStageReward);

        restMGuerillaQuestStageRewardMockMvc.perform(post("/api/m-guerilla-quest-stage-rewards")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mGuerillaQuestStageRewardDTO)))
            .andExpect(status().isBadRequest());

        List<MGuerillaQuestStageReward> mGuerillaQuestStageRewardList = mGuerillaQuestStageRewardRepository.findAll();
        assertThat(mGuerillaQuestStageRewardList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkExpIsRequired() throws Exception {
        int databaseSizeBeforeTest = mGuerillaQuestStageRewardRepository.findAll().size();
        // set the field null
        mGuerillaQuestStageReward.setExp(null);

        // Create the MGuerillaQuestStageReward, which fails.
        MGuerillaQuestStageRewardDTO mGuerillaQuestStageRewardDTO = mGuerillaQuestStageRewardMapper.toDto(mGuerillaQuestStageReward);

        restMGuerillaQuestStageRewardMockMvc.perform(post("/api/m-guerilla-quest-stage-rewards")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mGuerillaQuestStageRewardDTO)))
            .andExpect(status().isBadRequest());

        List<MGuerillaQuestStageReward> mGuerillaQuestStageRewardList = mGuerillaQuestStageRewardRepository.findAll();
        assertThat(mGuerillaQuestStageRewardList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkCoinIsRequired() throws Exception {
        int databaseSizeBeforeTest = mGuerillaQuestStageRewardRepository.findAll().size();
        // set the field null
        mGuerillaQuestStageReward.setCoin(null);

        // Create the MGuerillaQuestStageReward, which fails.
        MGuerillaQuestStageRewardDTO mGuerillaQuestStageRewardDTO = mGuerillaQuestStageRewardMapper.toDto(mGuerillaQuestStageReward);

        restMGuerillaQuestStageRewardMockMvc.perform(post("/api/m-guerilla-quest-stage-rewards")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mGuerillaQuestStageRewardDTO)))
            .andExpect(status().isBadRequest());

        List<MGuerillaQuestStageReward> mGuerillaQuestStageRewardList = mGuerillaQuestStageRewardRepository.findAll();
        assertThat(mGuerillaQuestStageRewardList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkGuildPointIsRequired() throws Exception {
        int databaseSizeBeforeTest = mGuerillaQuestStageRewardRepository.findAll().size();
        // set the field null
        mGuerillaQuestStageReward.setGuildPoint(null);

        // Create the MGuerillaQuestStageReward, which fails.
        MGuerillaQuestStageRewardDTO mGuerillaQuestStageRewardDTO = mGuerillaQuestStageRewardMapper.toDto(mGuerillaQuestStageReward);

        restMGuerillaQuestStageRewardMockMvc.perform(post("/api/m-guerilla-quest-stage-rewards")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mGuerillaQuestStageRewardDTO)))
            .andExpect(status().isBadRequest());

        List<MGuerillaQuestStageReward> mGuerillaQuestStageRewardList = mGuerillaQuestStageRewardRepository.findAll();
        assertThat(mGuerillaQuestStageRewardList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkClearRewardGroupIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = mGuerillaQuestStageRewardRepository.findAll().size();
        // set the field null
        mGuerillaQuestStageReward.setClearRewardGroupId(null);

        // Create the MGuerillaQuestStageReward, which fails.
        MGuerillaQuestStageRewardDTO mGuerillaQuestStageRewardDTO = mGuerillaQuestStageRewardMapper.toDto(mGuerillaQuestStageReward);

        restMGuerillaQuestStageRewardMockMvc.perform(post("/api/m-guerilla-quest-stage-rewards")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mGuerillaQuestStageRewardDTO)))
            .andExpect(status().isBadRequest());

        List<MGuerillaQuestStageReward> mGuerillaQuestStageRewardList = mGuerillaQuestStageRewardRepository.findAll();
        assertThat(mGuerillaQuestStageRewardList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkClearRewardWeightIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = mGuerillaQuestStageRewardRepository.findAll().size();
        // set the field null
        mGuerillaQuestStageReward.setClearRewardWeightId(null);

        // Create the MGuerillaQuestStageReward, which fails.
        MGuerillaQuestStageRewardDTO mGuerillaQuestStageRewardDTO = mGuerillaQuestStageRewardMapper.toDto(mGuerillaQuestStageReward);

        restMGuerillaQuestStageRewardMockMvc.perform(post("/api/m-guerilla-quest-stage-rewards")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mGuerillaQuestStageRewardDTO)))
            .andExpect(status().isBadRequest());

        List<MGuerillaQuestStageReward> mGuerillaQuestStageRewardList = mGuerillaQuestStageRewardRepository.findAll();
        assertThat(mGuerillaQuestStageRewardList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkAchievementRewardGroupIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = mGuerillaQuestStageRewardRepository.findAll().size();
        // set the field null
        mGuerillaQuestStageReward.setAchievementRewardGroupId(null);

        // Create the MGuerillaQuestStageReward, which fails.
        MGuerillaQuestStageRewardDTO mGuerillaQuestStageRewardDTO = mGuerillaQuestStageRewardMapper.toDto(mGuerillaQuestStageReward);

        restMGuerillaQuestStageRewardMockMvc.perform(post("/api/m-guerilla-quest-stage-rewards")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mGuerillaQuestStageRewardDTO)))
            .andExpect(status().isBadRequest());

        List<MGuerillaQuestStageReward> mGuerillaQuestStageRewardList = mGuerillaQuestStageRewardRepository.findAll();
        assertThat(mGuerillaQuestStageRewardList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkCoopGroupIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = mGuerillaQuestStageRewardRepository.findAll().size();
        // set the field null
        mGuerillaQuestStageReward.setCoopGroupId(null);

        // Create the MGuerillaQuestStageReward, which fails.
        MGuerillaQuestStageRewardDTO mGuerillaQuestStageRewardDTO = mGuerillaQuestStageRewardMapper.toDto(mGuerillaQuestStageReward);

        restMGuerillaQuestStageRewardMockMvc.perform(post("/api/m-guerilla-quest-stage-rewards")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mGuerillaQuestStageRewardDTO)))
            .andExpect(status().isBadRequest());

        List<MGuerillaQuestStageReward> mGuerillaQuestStageRewardList = mGuerillaQuestStageRewardRepository.findAll();
        assertThat(mGuerillaQuestStageRewardList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkSpecialRewardAmountIsRequired() throws Exception {
        int databaseSizeBeforeTest = mGuerillaQuestStageRewardRepository.findAll().size();
        // set the field null
        mGuerillaQuestStageReward.setSpecialRewardAmount(null);

        // Create the MGuerillaQuestStageReward, which fails.
        MGuerillaQuestStageRewardDTO mGuerillaQuestStageRewardDTO = mGuerillaQuestStageRewardMapper.toDto(mGuerillaQuestStageReward);

        restMGuerillaQuestStageRewardMockMvc.perform(post("/api/m-guerilla-quest-stage-rewards")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mGuerillaQuestStageRewardDTO)))
            .andExpect(status().isBadRequest());

        List<MGuerillaQuestStageReward> mGuerillaQuestStageRewardList = mGuerillaQuestStageRewardRepository.findAll();
        assertThat(mGuerillaQuestStageRewardList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllMGuerillaQuestStageRewards() throws Exception {
        // Initialize the database
        mGuerillaQuestStageRewardRepository.saveAndFlush(mGuerillaQuestStageReward);

        // Get all the mGuerillaQuestStageRewardList
        restMGuerillaQuestStageRewardMockMvc.perform(get("/api/m-guerilla-quest-stage-rewards?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(mGuerillaQuestStageReward.getId().intValue())))
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
    public void getMGuerillaQuestStageReward() throws Exception {
        // Initialize the database
        mGuerillaQuestStageRewardRepository.saveAndFlush(mGuerillaQuestStageReward);

        // Get the mGuerillaQuestStageReward
        restMGuerillaQuestStageRewardMockMvc.perform(get("/api/m-guerilla-quest-stage-rewards/{id}", mGuerillaQuestStageReward.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(mGuerillaQuestStageReward.getId().intValue()))
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
    public void getAllMGuerillaQuestStageRewardsByStageIdIsEqualToSomething() throws Exception {
        // Initialize the database
        mGuerillaQuestStageRewardRepository.saveAndFlush(mGuerillaQuestStageReward);

        // Get all the mGuerillaQuestStageRewardList where stageId equals to DEFAULT_STAGE_ID
        defaultMGuerillaQuestStageRewardShouldBeFound("stageId.equals=" + DEFAULT_STAGE_ID);

        // Get all the mGuerillaQuestStageRewardList where stageId equals to UPDATED_STAGE_ID
        defaultMGuerillaQuestStageRewardShouldNotBeFound("stageId.equals=" + UPDATED_STAGE_ID);
    }

    @Test
    @Transactional
    public void getAllMGuerillaQuestStageRewardsByStageIdIsInShouldWork() throws Exception {
        // Initialize the database
        mGuerillaQuestStageRewardRepository.saveAndFlush(mGuerillaQuestStageReward);

        // Get all the mGuerillaQuestStageRewardList where stageId in DEFAULT_STAGE_ID or UPDATED_STAGE_ID
        defaultMGuerillaQuestStageRewardShouldBeFound("stageId.in=" + DEFAULT_STAGE_ID + "," + UPDATED_STAGE_ID);

        // Get all the mGuerillaQuestStageRewardList where stageId equals to UPDATED_STAGE_ID
        defaultMGuerillaQuestStageRewardShouldNotBeFound("stageId.in=" + UPDATED_STAGE_ID);
    }

    @Test
    @Transactional
    public void getAllMGuerillaQuestStageRewardsByStageIdIsNullOrNotNull() throws Exception {
        // Initialize the database
        mGuerillaQuestStageRewardRepository.saveAndFlush(mGuerillaQuestStageReward);

        // Get all the mGuerillaQuestStageRewardList where stageId is not null
        defaultMGuerillaQuestStageRewardShouldBeFound("stageId.specified=true");

        // Get all the mGuerillaQuestStageRewardList where stageId is null
        defaultMGuerillaQuestStageRewardShouldNotBeFound("stageId.specified=false");
    }

    @Test
    @Transactional
    public void getAllMGuerillaQuestStageRewardsByStageIdIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mGuerillaQuestStageRewardRepository.saveAndFlush(mGuerillaQuestStageReward);

        // Get all the mGuerillaQuestStageRewardList where stageId greater than or equals to DEFAULT_STAGE_ID
        defaultMGuerillaQuestStageRewardShouldBeFound("stageId.greaterOrEqualThan=" + DEFAULT_STAGE_ID);

        // Get all the mGuerillaQuestStageRewardList where stageId greater than or equals to UPDATED_STAGE_ID
        defaultMGuerillaQuestStageRewardShouldNotBeFound("stageId.greaterOrEqualThan=" + UPDATED_STAGE_ID);
    }

    @Test
    @Transactional
    public void getAllMGuerillaQuestStageRewardsByStageIdIsLessThanSomething() throws Exception {
        // Initialize the database
        mGuerillaQuestStageRewardRepository.saveAndFlush(mGuerillaQuestStageReward);

        // Get all the mGuerillaQuestStageRewardList where stageId less than or equals to DEFAULT_STAGE_ID
        defaultMGuerillaQuestStageRewardShouldNotBeFound("stageId.lessThan=" + DEFAULT_STAGE_ID);

        // Get all the mGuerillaQuestStageRewardList where stageId less than or equals to UPDATED_STAGE_ID
        defaultMGuerillaQuestStageRewardShouldBeFound("stageId.lessThan=" + UPDATED_STAGE_ID);
    }


    @Test
    @Transactional
    public void getAllMGuerillaQuestStageRewardsByExpIsEqualToSomething() throws Exception {
        // Initialize the database
        mGuerillaQuestStageRewardRepository.saveAndFlush(mGuerillaQuestStageReward);

        // Get all the mGuerillaQuestStageRewardList where exp equals to DEFAULT_EXP
        defaultMGuerillaQuestStageRewardShouldBeFound("exp.equals=" + DEFAULT_EXP);

        // Get all the mGuerillaQuestStageRewardList where exp equals to UPDATED_EXP
        defaultMGuerillaQuestStageRewardShouldNotBeFound("exp.equals=" + UPDATED_EXP);
    }

    @Test
    @Transactional
    public void getAllMGuerillaQuestStageRewardsByExpIsInShouldWork() throws Exception {
        // Initialize the database
        mGuerillaQuestStageRewardRepository.saveAndFlush(mGuerillaQuestStageReward);

        // Get all the mGuerillaQuestStageRewardList where exp in DEFAULT_EXP or UPDATED_EXP
        defaultMGuerillaQuestStageRewardShouldBeFound("exp.in=" + DEFAULT_EXP + "," + UPDATED_EXP);

        // Get all the mGuerillaQuestStageRewardList where exp equals to UPDATED_EXP
        defaultMGuerillaQuestStageRewardShouldNotBeFound("exp.in=" + UPDATED_EXP);
    }

    @Test
    @Transactional
    public void getAllMGuerillaQuestStageRewardsByExpIsNullOrNotNull() throws Exception {
        // Initialize the database
        mGuerillaQuestStageRewardRepository.saveAndFlush(mGuerillaQuestStageReward);

        // Get all the mGuerillaQuestStageRewardList where exp is not null
        defaultMGuerillaQuestStageRewardShouldBeFound("exp.specified=true");

        // Get all the mGuerillaQuestStageRewardList where exp is null
        defaultMGuerillaQuestStageRewardShouldNotBeFound("exp.specified=false");
    }

    @Test
    @Transactional
    public void getAllMGuerillaQuestStageRewardsByExpIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mGuerillaQuestStageRewardRepository.saveAndFlush(mGuerillaQuestStageReward);

        // Get all the mGuerillaQuestStageRewardList where exp greater than or equals to DEFAULT_EXP
        defaultMGuerillaQuestStageRewardShouldBeFound("exp.greaterOrEqualThan=" + DEFAULT_EXP);

        // Get all the mGuerillaQuestStageRewardList where exp greater than or equals to UPDATED_EXP
        defaultMGuerillaQuestStageRewardShouldNotBeFound("exp.greaterOrEqualThan=" + UPDATED_EXP);
    }

    @Test
    @Transactional
    public void getAllMGuerillaQuestStageRewardsByExpIsLessThanSomething() throws Exception {
        // Initialize the database
        mGuerillaQuestStageRewardRepository.saveAndFlush(mGuerillaQuestStageReward);

        // Get all the mGuerillaQuestStageRewardList where exp less than or equals to DEFAULT_EXP
        defaultMGuerillaQuestStageRewardShouldNotBeFound("exp.lessThan=" + DEFAULT_EXP);

        // Get all the mGuerillaQuestStageRewardList where exp less than or equals to UPDATED_EXP
        defaultMGuerillaQuestStageRewardShouldBeFound("exp.lessThan=" + UPDATED_EXP);
    }


    @Test
    @Transactional
    public void getAllMGuerillaQuestStageRewardsByCoinIsEqualToSomething() throws Exception {
        // Initialize the database
        mGuerillaQuestStageRewardRepository.saveAndFlush(mGuerillaQuestStageReward);

        // Get all the mGuerillaQuestStageRewardList where coin equals to DEFAULT_COIN
        defaultMGuerillaQuestStageRewardShouldBeFound("coin.equals=" + DEFAULT_COIN);

        // Get all the mGuerillaQuestStageRewardList where coin equals to UPDATED_COIN
        defaultMGuerillaQuestStageRewardShouldNotBeFound("coin.equals=" + UPDATED_COIN);
    }

    @Test
    @Transactional
    public void getAllMGuerillaQuestStageRewardsByCoinIsInShouldWork() throws Exception {
        // Initialize the database
        mGuerillaQuestStageRewardRepository.saveAndFlush(mGuerillaQuestStageReward);

        // Get all the mGuerillaQuestStageRewardList where coin in DEFAULT_COIN or UPDATED_COIN
        defaultMGuerillaQuestStageRewardShouldBeFound("coin.in=" + DEFAULT_COIN + "," + UPDATED_COIN);

        // Get all the mGuerillaQuestStageRewardList where coin equals to UPDATED_COIN
        defaultMGuerillaQuestStageRewardShouldNotBeFound("coin.in=" + UPDATED_COIN);
    }

    @Test
    @Transactional
    public void getAllMGuerillaQuestStageRewardsByCoinIsNullOrNotNull() throws Exception {
        // Initialize the database
        mGuerillaQuestStageRewardRepository.saveAndFlush(mGuerillaQuestStageReward);

        // Get all the mGuerillaQuestStageRewardList where coin is not null
        defaultMGuerillaQuestStageRewardShouldBeFound("coin.specified=true");

        // Get all the mGuerillaQuestStageRewardList where coin is null
        defaultMGuerillaQuestStageRewardShouldNotBeFound("coin.specified=false");
    }

    @Test
    @Transactional
    public void getAllMGuerillaQuestStageRewardsByCoinIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mGuerillaQuestStageRewardRepository.saveAndFlush(mGuerillaQuestStageReward);

        // Get all the mGuerillaQuestStageRewardList where coin greater than or equals to DEFAULT_COIN
        defaultMGuerillaQuestStageRewardShouldBeFound("coin.greaterOrEqualThan=" + DEFAULT_COIN);

        // Get all the mGuerillaQuestStageRewardList where coin greater than or equals to UPDATED_COIN
        defaultMGuerillaQuestStageRewardShouldNotBeFound("coin.greaterOrEqualThan=" + UPDATED_COIN);
    }

    @Test
    @Transactional
    public void getAllMGuerillaQuestStageRewardsByCoinIsLessThanSomething() throws Exception {
        // Initialize the database
        mGuerillaQuestStageRewardRepository.saveAndFlush(mGuerillaQuestStageReward);

        // Get all the mGuerillaQuestStageRewardList where coin less than or equals to DEFAULT_COIN
        defaultMGuerillaQuestStageRewardShouldNotBeFound("coin.lessThan=" + DEFAULT_COIN);

        // Get all the mGuerillaQuestStageRewardList where coin less than or equals to UPDATED_COIN
        defaultMGuerillaQuestStageRewardShouldBeFound("coin.lessThan=" + UPDATED_COIN);
    }


    @Test
    @Transactional
    public void getAllMGuerillaQuestStageRewardsByGuildPointIsEqualToSomething() throws Exception {
        // Initialize the database
        mGuerillaQuestStageRewardRepository.saveAndFlush(mGuerillaQuestStageReward);

        // Get all the mGuerillaQuestStageRewardList where guildPoint equals to DEFAULT_GUILD_POINT
        defaultMGuerillaQuestStageRewardShouldBeFound("guildPoint.equals=" + DEFAULT_GUILD_POINT);

        // Get all the mGuerillaQuestStageRewardList where guildPoint equals to UPDATED_GUILD_POINT
        defaultMGuerillaQuestStageRewardShouldNotBeFound("guildPoint.equals=" + UPDATED_GUILD_POINT);
    }

    @Test
    @Transactional
    public void getAllMGuerillaQuestStageRewardsByGuildPointIsInShouldWork() throws Exception {
        // Initialize the database
        mGuerillaQuestStageRewardRepository.saveAndFlush(mGuerillaQuestStageReward);

        // Get all the mGuerillaQuestStageRewardList where guildPoint in DEFAULT_GUILD_POINT or UPDATED_GUILD_POINT
        defaultMGuerillaQuestStageRewardShouldBeFound("guildPoint.in=" + DEFAULT_GUILD_POINT + "," + UPDATED_GUILD_POINT);

        // Get all the mGuerillaQuestStageRewardList where guildPoint equals to UPDATED_GUILD_POINT
        defaultMGuerillaQuestStageRewardShouldNotBeFound("guildPoint.in=" + UPDATED_GUILD_POINT);
    }

    @Test
    @Transactional
    public void getAllMGuerillaQuestStageRewardsByGuildPointIsNullOrNotNull() throws Exception {
        // Initialize the database
        mGuerillaQuestStageRewardRepository.saveAndFlush(mGuerillaQuestStageReward);

        // Get all the mGuerillaQuestStageRewardList where guildPoint is not null
        defaultMGuerillaQuestStageRewardShouldBeFound("guildPoint.specified=true");

        // Get all the mGuerillaQuestStageRewardList where guildPoint is null
        defaultMGuerillaQuestStageRewardShouldNotBeFound("guildPoint.specified=false");
    }

    @Test
    @Transactional
    public void getAllMGuerillaQuestStageRewardsByGuildPointIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mGuerillaQuestStageRewardRepository.saveAndFlush(mGuerillaQuestStageReward);

        // Get all the mGuerillaQuestStageRewardList where guildPoint greater than or equals to DEFAULT_GUILD_POINT
        defaultMGuerillaQuestStageRewardShouldBeFound("guildPoint.greaterOrEqualThan=" + DEFAULT_GUILD_POINT);

        // Get all the mGuerillaQuestStageRewardList where guildPoint greater than or equals to UPDATED_GUILD_POINT
        defaultMGuerillaQuestStageRewardShouldNotBeFound("guildPoint.greaterOrEqualThan=" + UPDATED_GUILD_POINT);
    }

    @Test
    @Transactional
    public void getAllMGuerillaQuestStageRewardsByGuildPointIsLessThanSomething() throws Exception {
        // Initialize the database
        mGuerillaQuestStageRewardRepository.saveAndFlush(mGuerillaQuestStageReward);

        // Get all the mGuerillaQuestStageRewardList where guildPoint less than or equals to DEFAULT_GUILD_POINT
        defaultMGuerillaQuestStageRewardShouldNotBeFound("guildPoint.lessThan=" + DEFAULT_GUILD_POINT);

        // Get all the mGuerillaQuestStageRewardList where guildPoint less than or equals to UPDATED_GUILD_POINT
        defaultMGuerillaQuestStageRewardShouldBeFound("guildPoint.lessThan=" + UPDATED_GUILD_POINT);
    }


    @Test
    @Transactional
    public void getAllMGuerillaQuestStageRewardsByClearRewardGroupIdIsEqualToSomething() throws Exception {
        // Initialize the database
        mGuerillaQuestStageRewardRepository.saveAndFlush(mGuerillaQuestStageReward);

        // Get all the mGuerillaQuestStageRewardList where clearRewardGroupId equals to DEFAULT_CLEAR_REWARD_GROUP_ID
        defaultMGuerillaQuestStageRewardShouldBeFound("clearRewardGroupId.equals=" + DEFAULT_CLEAR_REWARD_GROUP_ID);

        // Get all the mGuerillaQuestStageRewardList where clearRewardGroupId equals to UPDATED_CLEAR_REWARD_GROUP_ID
        defaultMGuerillaQuestStageRewardShouldNotBeFound("clearRewardGroupId.equals=" + UPDATED_CLEAR_REWARD_GROUP_ID);
    }

    @Test
    @Transactional
    public void getAllMGuerillaQuestStageRewardsByClearRewardGroupIdIsInShouldWork() throws Exception {
        // Initialize the database
        mGuerillaQuestStageRewardRepository.saveAndFlush(mGuerillaQuestStageReward);

        // Get all the mGuerillaQuestStageRewardList where clearRewardGroupId in DEFAULT_CLEAR_REWARD_GROUP_ID or UPDATED_CLEAR_REWARD_GROUP_ID
        defaultMGuerillaQuestStageRewardShouldBeFound("clearRewardGroupId.in=" + DEFAULT_CLEAR_REWARD_GROUP_ID + "," + UPDATED_CLEAR_REWARD_GROUP_ID);

        // Get all the mGuerillaQuestStageRewardList where clearRewardGroupId equals to UPDATED_CLEAR_REWARD_GROUP_ID
        defaultMGuerillaQuestStageRewardShouldNotBeFound("clearRewardGroupId.in=" + UPDATED_CLEAR_REWARD_GROUP_ID);
    }

    @Test
    @Transactional
    public void getAllMGuerillaQuestStageRewardsByClearRewardGroupIdIsNullOrNotNull() throws Exception {
        // Initialize the database
        mGuerillaQuestStageRewardRepository.saveAndFlush(mGuerillaQuestStageReward);

        // Get all the mGuerillaQuestStageRewardList where clearRewardGroupId is not null
        defaultMGuerillaQuestStageRewardShouldBeFound("clearRewardGroupId.specified=true");

        // Get all the mGuerillaQuestStageRewardList where clearRewardGroupId is null
        defaultMGuerillaQuestStageRewardShouldNotBeFound("clearRewardGroupId.specified=false");
    }

    @Test
    @Transactional
    public void getAllMGuerillaQuestStageRewardsByClearRewardGroupIdIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mGuerillaQuestStageRewardRepository.saveAndFlush(mGuerillaQuestStageReward);

        // Get all the mGuerillaQuestStageRewardList where clearRewardGroupId greater than or equals to DEFAULT_CLEAR_REWARD_GROUP_ID
        defaultMGuerillaQuestStageRewardShouldBeFound("clearRewardGroupId.greaterOrEqualThan=" + DEFAULT_CLEAR_REWARD_GROUP_ID);

        // Get all the mGuerillaQuestStageRewardList where clearRewardGroupId greater than or equals to UPDATED_CLEAR_REWARD_GROUP_ID
        defaultMGuerillaQuestStageRewardShouldNotBeFound("clearRewardGroupId.greaterOrEqualThan=" + UPDATED_CLEAR_REWARD_GROUP_ID);
    }

    @Test
    @Transactional
    public void getAllMGuerillaQuestStageRewardsByClearRewardGroupIdIsLessThanSomething() throws Exception {
        // Initialize the database
        mGuerillaQuestStageRewardRepository.saveAndFlush(mGuerillaQuestStageReward);

        // Get all the mGuerillaQuestStageRewardList where clearRewardGroupId less than or equals to DEFAULT_CLEAR_REWARD_GROUP_ID
        defaultMGuerillaQuestStageRewardShouldNotBeFound("clearRewardGroupId.lessThan=" + DEFAULT_CLEAR_REWARD_GROUP_ID);

        // Get all the mGuerillaQuestStageRewardList where clearRewardGroupId less than or equals to UPDATED_CLEAR_REWARD_GROUP_ID
        defaultMGuerillaQuestStageRewardShouldBeFound("clearRewardGroupId.lessThan=" + UPDATED_CLEAR_REWARD_GROUP_ID);
    }


    @Test
    @Transactional
    public void getAllMGuerillaQuestStageRewardsByClearRewardWeightIdIsEqualToSomething() throws Exception {
        // Initialize the database
        mGuerillaQuestStageRewardRepository.saveAndFlush(mGuerillaQuestStageReward);

        // Get all the mGuerillaQuestStageRewardList where clearRewardWeightId equals to DEFAULT_CLEAR_REWARD_WEIGHT_ID
        defaultMGuerillaQuestStageRewardShouldBeFound("clearRewardWeightId.equals=" + DEFAULT_CLEAR_REWARD_WEIGHT_ID);

        // Get all the mGuerillaQuestStageRewardList where clearRewardWeightId equals to UPDATED_CLEAR_REWARD_WEIGHT_ID
        defaultMGuerillaQuestStageRewardShouldNotBeFound("clearRewardWeightId.equals=" + UPDATED_CLEAR_REWARD_WEIGHT_ID);
    }

    @Test
    @Transactional
    public void getAllMGuerillaQuestStageRewardsByClearRewardWeightIdIsInShouldWork() throws Exception {
        // Initialize the database
        mGuerillaQuestStageRewardRepository.saveAndFlush(mGuerillaQuestStageReward);

        // Get all the mGuerillaQuestStageRewardList where clearRewardWeightId in DEFAULT_CLEAR_REWARD_WEIGHT_ID or UPDATED_CLEAR_REWARD_WEIGHT_ID
        defaultMGuerillaQuestStageRewardShouldBeFound("clearRewardWeightId.in=" + DEFAULT_CLEAR_REWARD_WEIGHT_ID + "," + UPDATED_CLEAR_REWARD_WEIGHT_ID);

        // Get all the mGuerillaQuestStageRewardList where clearRewardWeightId equals to UPDATED_CLEAR_REWARD_WEIGHT_ID
        defaultMGuerillaQuestStageRewardShouldNotBeFound("clearRewardWeightId.in=" + UPDATED_CLEAR_REWARD_WEIGHT_ID);
    }

    @Test
    @Transactional
    public void getAllMGuerillaQuestStageRewardsByClearRewardWeightIdIsNullOrNotNull() throws Exception {
        // Initialize the database
        mGuerillaQuestStageRewardRepository.saveAndFlush(mGuerillaQuestStageReward);

        // Get all the mGuerillaQuestStageRewardList where clearRewardWeightId is not null
        defaultMGuerillaQuestStageRewardShouldBeFound("clearRewardWeightId.specified=true");

        // Get all the mGuerillaQuestStageRewardList where clearRewardWeightId is null
        defaultMGuerillaQuestStageRewardShouldNotBeFound("clearRewardWeightId.specified=false");
    }

    @Test
    @Transactional
    public void getAllMGuerillaQuestStageRewardsByClearRewardWeightIdIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mGuerillaQuestStageRewardRepository.saveAndFlush(mGuerillaQuestStageReward);

        // Get all the mGuerillaQuestStageRewardList where clearRewardWeightId greater than or equals to DEFAULT_CLEAR_REWARD_WEIGHT_ID
        defaultMGuerillaQuestStageRewardShouldBeFound("clearRewardWeightId.greaterOrEqualThan=" + DEFAULT_CLEAR_REWARD_WEIGHT_ID);

        // Get all the mGuerillaQuestStageRewardList where clearRewardWeightId greater than or equals to UPDATED_CLEAR_REWARD_WEIGHT_ID
        defaultMGuerillaQuestStageRewardShouldNotBeFound("clearRewardWeightId.greaterOrEqualThan=" + UPDATED_CLEAR_REWARD_WEIGHT_ID);
    }

    @Test
    @Transactional
    public void getAllMGuerillaQuestStageRewardsByClearRewardWeightIdIsLessThanSomething() throws Exception {
        // Initialize the database
        mGuerillaQuestStageRewardRepository.saveAndFlush(mGuerillaQuestStageReward);

        // Get all the mGuerillaQuestStageRewardList where clearRewardWeightId less than or equals to DEFAULT_CLEAR_REWARD_WEIGHT_ID
        defaultMGuerillaQuestStageRewardShouldNotBeFound("clearRewardWeightId.lessThan=" + DEFAULT_CLEAR_REWARD_WEIGHT_ID);

        // Get all the mGuerillaQuestStageRewardList where clearRewardWeightId less than or equals to UPDATED_CLEAR_REWARD_WEIGHT_ID
        defaultMGuerillaQuestStageRewardShouldBeFound("clearRewardWeightId.lessThan=" + UPDATED_CLEAR_REWARD_WEIGHT_ID);
    }


    @Test
    @Transactional
    public void getAllMGuerillaQuestStageRewardsByAchievementRewardGroupIdIsEqualToSomething() throws Exception {
        // Initialize the database
        mGuerillaQuestStageRewardRepository.saveAndFlush(mGuerillaQuestStageReward);

        // Get all the mGuerillaQuestStageRewardList where achievementRewardGroupId equals to DEFAULT_ACHIEVEMENT_REWARD_GROUP_ID
        defaultMGuerillaQuestStageRewardShouldBeFound("achievementRewardGroupId.equals=" + DEFAULT_ACHIEVEMENT_REWARD_GROUP_ID);

        // Get all the mGuerillaQuestStageRewardList where achievementRewardGroupId equals to UPDATED_ACHIEVEMENT_REWARD_GROUP_ID
        defaultMGuerillaQuestStageRewardShouldNotBeFound("achievementRewardGroupId.equals=" + UPDATED_ACHIEVEMENT_REWARD_GROUP_ID);
    }

    @Test
    @Transactional
    public void getAllMGuerillaQuestStageRewardsByAchievementRewardGroupIdIsInShouldWork() throws Exception {
        // Initialize the database
        mGuerillaQuestStageRewardRepository.saveAndFlush(mGuerillaQuestStageReward);

        // Get all the mGuerillaQuestStageRewardList where achievementRewardGroupId in DEFAULT_ACHIEVEMENT_REWARD_GROUP_ID or UPDATED_ACHIEVEMENT_REWARD_GROUP_ID
        defaultMGuerillaQuestStageRewardShouldBeFound("achievementRewardGroupId.in=" + DEFAULT_ACHIEVEMENT_REWARD_GROUP_ID + "," + UPDATED_ACHIEVEMENT_REWARD_GROUP_ID);

        // Get all the mGuerillaQuestStageRewardList where achievementRewardGroupId equals to UPDATED_ACHIEVEMENT_REWARD_GROUP_ID
        defaultMGuerillaQuestStageRewardShouldNotBeFound("achievementRewardGroupId.in=" + UPDATED_ACHIEVEMENT_REWARD_GROUP_ID);
    }

    @Test
    @Transactional
    public void getAllMGuerillaQuestStageRewardsByAchievementRewardGroupIdIsNullOrNotNull() throws Exception {
        // Initialize the database
        mGuerillaQuestStageRewardRepository.saveAndFlush(mGuerillaQuestStageReward);

        // Get all the mGuerillaQuestStageRewardList where achievementRewardGroupId is not null
        defaultMGuerillaQuestStageRewardShouldBeFound("achievementRewardGroupId.specified=true");

        // Get all the mGuerillaQuestStageRewardList where achievementRewardGroupId is null
        defaultMGuerillaQuestStageRewardShouldNotBeFound("achievementRewardGroupId.specified=false");
    }

    @Test
    @Transactional
    public void getAllMGuerillaQuestStageRewardsByAchievementRewardGroupIdIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mGuerillaQuestStageRewardRepository.saveAndFlush(mGuerillaQuestStageReward);

        // Get all the mGuerillaQuestStageRewardList where achievementRewardGroupId greater than or equals to DEFAULT_ACHIEVEMENT_REWARD_GROUP_ID
        defaultMGuerillaQuestStageRewardShouldBeFound("achievementRewardGroupId.greaterOrEqualThan=" + DEFAULT_ACHIEVEMENT_REWARD_GROUP_ID);

        // Get all the mGuerillaQuestStageRewardList where achievementRewardGroupId greater than or equals to UPDATED_ACHIEVEMENT_REWARD_GROUP_ID
        defaultMGuerillaQuestStageRewardShouldNotBeFound("achievementRewardGroupId.greaterOrEqualThan=" + UPDATED_ACHIEVEMENT_REWARD_GROUP_ID);
    }

    @Test
    @Transactional
    public void getAllMGuerillaQuestStageRewardsByAchievementRewardGroupIdIsLessThanSomething() throws Exception {
        // Initialize the database
        mGuerillaQuestStageRewardRepository.saveAndFlush(mGuerillaQuestStageReward);

        // Get all the mGuerillaQuestStageRewardList where achievementRewardGroupId less than or equals to DEFAULT_ACHIEVEMENT_REWARD_GROUP_ID
        defaultMGuerillaQuestStageRewardShouldNotBeFound("achievementRewardGroupId.lessThan=" + DEFAULT_ACHIEVEMENT_REWARD_GROUP_ID);

        // Get all the mGuerillaQuestStageRewardList where achievementRewardGroupId less than or equals to UPDATED_ACHIEVEMENT_REWARD_GROUP_ID
        defaultMGuerillaQuestStageRewardShouldBeFound("achievementRewardGroupId.lessThan=" + UPDATED_ACHIEVEMENT_REWARD_GROUP_ID);
    }


    @Test
    @Transactional
    public void getAllMGuerillaQuestStageRewardsByCoopGroupIdIsEqualToSomething() throws Exception {
        // Initialize the database
        mGuerillaQuestStageRewardRepository.saveAndFlush(mGuerillaQuestStageReward);

        // Get all the mGuerillaQuestStageRewardList where coopGroupId equals to DEFAULT_COOP_GROUP_ID
        defaultMGuerillaQuestStageRewardShouldBeFound("coopGroupId.equals=" + DEFAULT_COOP_GROUP_ID);

        // Get all the mGuerillaQuestStageRewardList where coopGroupId equals to UPDATED_COOP_GROUP_ID
        defaultMGuerillaQuestStageRewardShouldNotBeFound("coopGroupId.equals=" + UPDATED_COOP_GROUP_ID);
    }

    @Test
    @Transactional
    public void getAllMGuerillaQuestStageRewardsByCoopGroupIdIsInShouldWork() throws Exception {
        // Initialize the database
        mGuerillaQuestStageRewardRepository.saveAndFlush(mGuerillaQuestStageReward);

        // Get all the mGuerillaQuestStageRewardList where coopGroupId in DEFAULT_COOP_GROUP_ID or UPDATED_COOP_GROUP_ID
        defaultMGuerillaQuestStageRewardShouldBeFound("coopGroupId.in=" + DEFAULT_COOP_GROUP_ID + "," + UPDATED_COOP_GROUP_ID);

        // Get all the mGuerillaQuestStageRewardList where coopGroupId equals to UPDATED_COOP_GROUP_ID
        defaultMGuerillaQuestStageRewardShouldNotBeFound("coopGroupId.in=" + UPDATED_COOP_GROUP_ID);
    }

    @Test
    @Transactional
    public void getAllMGuerillaQuestStageRewardsByCoopGroupIdIsNullOrNotNull() throws Exception {
        // Initialize the database
        mGuerillaQuestStageRewardRepository.saveAndFlush(mGuerillaQuestStageReward);

        // Get all the mGuerillaQuestStageRewardList where coopGroupId is not null
        defaultMGuerillaQuestStageRewardShouldBeFound("coopGroupId.specified=true");

        // Get all the mGuerillaQuestStageRewardList where coopGroupId is null
        defaultMGuerillaQuestStageRewardShouldNotBeFound("coopGroupId.specified=false");
    }

    @Test
    @Transactional
    public void getAllMGuerillaQuestStageRewardsByCoopGroupIdIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mGuerillaQuestStageRewardRepository.saveAndFlush(mGuerillaQuestStageReward);

        // Get all the mGuerillaQuestStageRewardList where coopGroupId greater than or equals to DEFAULT_COOP_GROUP_ID
        defaultMGuerillaQuestStageRewardShouldBeFound("coopGroupId.greaterOrEqualThan=" + DEFAULT_COOP_GROUP_ID);

        // Get all the mGuerillaQuestStageRewardList where coopGroupId greater than or equals to UPDATED_COOP_GROUP_ID
        defaultMGuerillaQuestStageRewardShouldNotBeFound("coopGroupId.greaterOrEqualThan=" + UPDATED_COOP_GROUP_ID);
    }

    @Test
    @Transactional
    public void getAllMGuerillaQuestStageRewardsByCoopGroupIdIsLessThanSomething() throws Exception {
        // Initialize the database
        mGuerillaQuestStageRewardRepository.saveAndFlush(mGuerillaQuestStageReward);

        // Get all the mGuerillaQuestStageRewardList where coopGroupId less than or equals to DEFAULT_COOP_GROUP_ID
        defaultMGuerillaQuestStageRewardShouldNotBeFound("coopGroupId.lessThan=" + DEFAULT_COOP_GROUP_ID);

        // Get all the mGuerillaQuestStageRewardList where coopGroupId less than or equals to UPDATED_COOP_GROUP_ID
        defaultMGuerillaQuestStageRewardShouldBeFound("coopGroupId.lessThan=" + UPDATED_COOP_GROUP_ID);
    }


    @Test
    @Transactional
    public void getAllMGuerillaQuestStageRewardsBySpecialRewardGroupIdIsEqualToSomething() throws Exception {
        // Initialize the database
        mGuerillaQuestStageRewardRepository.saveAndFlush(mGuerillaQuestStageReward);

        // Get all the mGuerillaQuestStageRewardList where specialRewardGroupId equals to DEFAULT_SPECIAL_REWARD_GROUP_ID
        defaultMGuerillaQuestStageRewardShouldBeFound("specialRewardGroupId.equals=" + DEFAULT_SPECIAL_REWARD_GROUP_ID);

        // Get all the mGuerillaQuestStageRewardList where specialRewardGroupId equals to UPDATED_SPECIAL_REWARD_GROUP_ID
        defaultMGuerillaQuestStageRewardShouldNotBeFound("specialRewardGroupId.equals=" + UPDATED_SPECIAL_REWARD_GROUP_ID);
    }

    @Test
    @Transactional
    public void getAllMGuerillaQuestStageRewardsBySpecialRewardGroupIdIsInShouldWork() throws Exception {
        // Initialize the database
        mGuerillaQuestStageRewardRepository.saveAndFlush(mGuerillaQuestStageReward);

        // Get all the mGuerillaQuestStageRewardList where specialRewardGroupId in DEFAULT_SPECIAL_REWARD_GROUP_ID or UPDATED_SPECIAL_REWARD_GROUP_ID
        defaultMGuerillaQuestStageRewardShouldBeFound("specialRewardGroupId.in=" + DEFAULT_SPECIAL_REWARD_GROUP_ID + "," + UPDATED_SPECIAL_REWARD_GROUP_ID);

        // Get all the mGuerillaQuestStageRewardList where specialRewardGroupId equals to UPDATED_SPECIAL_REWARD_GROUP_ID
        defaultMGuerillaQuestStageRewardShouldNotBeFound("specialRewardGroupId.in=" + UPDATED_SPECIAL_REWARD_GROUP_ID);
    }

    @Test
    @Transactional
    public void getAllMGuerillaQuestStageRewardsBySpecialRewardGroupIdIsNullOrNotNull() throws Exception {
        // Initialize the database
        mGuerillaQuestStageRewardRepository.saveAndFlush(mGuerillaQuestStageReward);

        // Get all the mGuerillaQuestStageRewardList where specialRewardGroupId is not null
        defaultMGuerillaQuestStageRewardShouldBeFound("specialRewardGroupId.specified=true");

        // Get all the mGuerillaQuestStageRewardList where specialRewardGroupId is null
        defaultMGuerillaQuestStageRewardShouldNotBeFound("specialRewardGroupId.specified=false");
    }

    @Test
    @Transactional
    public void getAllMGuerillaQuestStageRewardsBySpecialRewardGroupIdIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mGuerillaQuestStageRewardRepository.saveAndFlush(mGuerillaQuestStageReward);

        // Get all the mGuerillaQuestStageRewardList where specialRewardGroupId greater than or equals to DEFAULT_SPECIAL_REWARD_GROUP_ID
        defaultMGuerillaQuestStageRewardShouldBeFound("specialRewardGroupId.greaterOrEqualThan=" + DEFAULT_SPECIAL_REWARD_GROUP_ID);

        // Get all the mGuerillaQuestStageRewardList where specialRewardGroupId greater than or equals to UPDATED_SPECIAL_REWARD_GROUP_ID
        defaultMGuerillaQuestStageRewardShouldNotBeFound("specialRewardGroupId.greaterOrEqualThan=" + UPDATED_SPECIAL_REWARD_GROUP_ID);
    }

    @Test
    @Transactional
    public void getAllMGuerillaQuestStageRewardsBySpecialRewardGroupIdIsLessThanSomething() throws Exception {
        // Initialize the database
        mGuerillaQuestStageRewardRepository.saveAndFlush(mGuerillaQuestStageReward);

        // Get all the mGuerillaQuestStageRewardList where specialRewardGroupId less than or equals to DEFAULT_SPECIAL_REWARD_GROUP_ID
        defaultMGuerillaQuestStageRewardShouldNotBeFound("specialRewardGroupId.lessThan=" + DEFAULT_SPECIAL_REWARD_GROUP_ID);

        // Get all the mGuerillaQuestStageRewardList where specialRewardGroupId less than or equals to UPDATED_SPECIAL_REWARD_GROUP_ID
        defaultMGuerillaQuestStageRewardShouldBeFound("specialRewardGroupId.lessThan=" + UPDATED_SPECIAL_REWARD_GROUP_ID);
    }


    @Test
    @Transactional
    public void getAllMGuerillaQuestStageRewardsBySpecialRewardAmountIsEqualToSomething() throws Exception {
        // Initialize the database
        mGuerillaQuestStageRewardRepository.saveAndFlush(mGuerillaQuestStageReward);

        // Get all the mGuerillaQuestStageRewardList where specialRewardAmount equals to DEFAULT_SPECIAL_REWARD_AMOUNT
        defaultMGuerillaQuestStageRewardShouldBeFound("specialRewardAmount.equals=" + DEFAULT_SPECIAL_REWARD_AMOUNT);

        // Get all the mGuerillaQuestStageRewardList where specialRewardAmount equals to UPDATED_SPECIAL_REWARD_AMOUNT
        defaultMGuerillaQuestStageRewardShouldNotBeFound("specialRewardAmount.equals=" + UPDATED_SPECIAL_REWARD_AMOUNT);
    }

    @Test
    @Transactional
    public void getAllMGuerillaQuestStageRewardsBySpecialRewardAmountIsInShouldWork() throws Exception {
        // Initialize the database
        mGuerillaQuestStageRewardRepository.saveAndFlush(mGuerillaQuestStageReward);

        // Get all the mGuerillaQuestStageRewardList where specialRewardAmount in DEFAULT_SPECIAL_REWARD_AMOUNT or UPDATED_SPECIAL_REWARD_AMOUNT
        defaultMGuerillaQuestStageRewardShouldBeFound("specialRewardAmount.in=" + DEFAULT_SPECIAL_REWARD_AMOUNT + "," + UPDATED_SPECIAL_REWARD_AMOUNT);

        // Get all the mGuerillaQuestStageRewardList where specialRewardAmount equals to UPDATED_SPECIAL_REWARD_AMOUNT
        defaultMGuerillaQuestStageRewardShouldNotBeFound("specialRewardAmount.in=" + UPDATED_SPECIAL_REWARD_AMOUNT);
    }

    @Test
    @Transactional
    public void getAllMGuerillaQuestStageRewardsBySpecialRewardAmountIsNullOrNotNull() throws Exception {
        // Initialize the database
        mGuerillaQuestStageRewardRepository.saveAndFlush(mGuerillaQuestStageReward);

        // Get all the mGuerillaQuestStageRewardList where specialRewardAmount is not null
        defaultMGuerillaQuestStageRewardShouldBeFound("specialRewardAmount.specified=true");

        // Get all the mGuerillaQuestStageRewardList where specialRewardAmount is null
        defaultMGuerillaQuestStageRewardShouldNotBeFound("specialRewardAmount.specified=false");
    }

    @Test
    @Transactional
    public void getAllMGuerillaQuestStageRewardsBySpecialRewardAmountIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mGuerillaQuestStageRewardRepository.saveAndFlush(mGuerillaQuestStageReward);

        // Get all the mGuerillaQuestStageRewardList where specialRewardAmount greater than or equals to DEFAULT_SPECIAL_REWARD_AMOUNT
        defaultMGuerillaQuestStageRewardShouldBeFound("specialRewardAmount.greaterOrEqualThan=" + DEFAULT_SPECIAL_REWARD_AMOUNT);

        // Get all the mGuerillaQuestStageRewardList where specialRewardAmount greater than or equals to UPDATED_SPECIAL_REWARD_AMOUNT
        defaultMGuerillaQuestStageRewardShouldNotBeFound("specialRewardAmount.greaterOrEqualThan=" + UPDATED_SPECIAL_REWARD_AMOUNT);
    }

    @Test
    @Transactional
    public void getAllMGuerillaQuestStageRewardsBySpecialRewardAmountIsLessThanSomething() throws Exception {
        // Initialize the database
        mGuerillaQuestStageRewardRepository.saveAndFlush(mGuerillaQuestStageReward);

        // Get all the mGuerillaQuestStageRewardList where specialRewardAmount less than or equals to DEFAULT_SPECIAL_REWARD_AMOUNT
        defaultMGuerillaQuestStageRewardShouldNotBeFound("specialRewardAmount.lessThan=" + DEFAULT_SPECIAL_REWARD_AMOUNT);

        // Get all the mGuerillaQuestStageRewardList where specialRewardAmount less than or equals to UPDATED_SPECIAL_REWARD_AMOUNT
        defaultMGuerillaQuestStageRewardShouldBeFound("specialRewardAmount.lessThan=" + UPDATED_SPECIAL_REWARD_AMOUNT);
    }


    @Test
    @Transactional
    public void getAllMGuerillaQuestStageRewardsByGoalRewardGroupIdIsEqualToSomething() throws Exception {
        // Initialize the database
        mGuerillaQuestStageRewardRepository.saveAndFlush(mGuerillaQuestStageReward);

        // Get all the mGuerillaQuestStageRewardList where goalRewardGroupId equals to DEFAULT_GOAL_REWARD_GROUP_ID
        defaultMGuerillaQuestStageRewardShouldBeFound("goalRewardGroupId.equals=" + DEFAULT_GOAL_REWARD_GROUP_ID);

        // Get all the mGuerillaQuestStageRewardList where goalRewardGroupId equals to UPDATED_GOAL_REWARD_GROUP_ID
        defaultMGuerillaQuestStageRewardShouldNotBeFound("goalRewardGroupId.equals=" + UPDATED_GOAL_REWARD_GROUP_ID);
    }

    @Test
    @Transactional
    public void getAllMGuerillaQuestStageRewardsByGoalRewardGroupIdIsInShouldWork() throws Exception {
        // Initialize the database
        mGuerillaQuestStageRewardRepository.saveAndFlush(mGuerillaQuestStageReward);

        // Get all the mGuerillaQuestStageRewardList where goalRewardGroupId in DEFAULT_GOAL_REWARD_GROUP_ID or UPDATED_GOAL_REWARD_GROUP_ID
        defaultMGuerillaQuestStageRewardShouldBeFound("goalRewardGroupId.in=" + DEFAULT_GOAL_REWARD_GROUP_ID + "," + UPDATED_GOAL_REWARD_GROUP_ID);

        // Get all the mGuerillaQuestStageRewardList where goalRewardGroupId equals to UPDATED_GOAL_REWARD_GROUP_ID
        defaultMGuerillaQuestStageRewardShouldNotBeFound("goalRewardGroupId.in=" + UPDATED_GOAL_REWARD_GROUP_ID);
    }

    @Test
    @Transactional
    public void getAllMGuerillaQuestStageRewardsByGoalRewardGroupIdIsNullOrNotNull() throws Exception {
        // Initialize the database
        mGuerillaQuestStageRewardRepository.saveAndFlush(mGuerillaQuestStageReward);

        // Get all the mGuerillaQuestStageRewardList where goalRewardGroupId is not null
        defaultMGuerillaQuestStageRewardShouldBeFound("goalRewardGroupId.specified=true");

        // Get all the mGuerillaQuestStageRewardList where goalRewardGroupId is null
        defaultMGuerillaQuestStageRewardShouldNotBeFound("goalRewardGroupId.specified=false");
    }

    @Test
    @Transactional
    public void getAllMGuerillaQuestStageRewardsByGoalRewardGroupIdIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mGuerillaQuestStageRewardRepository.saveAndFlush(mGuerillaQuestStageReward);

        // Get all the mGuerillaQuestStageRewardList where goalRewardGroupId greater than or equals to DEFAULT_GOAL_REWARD_GROUP_ID
        defaultMGuerillaQuestStageRewardShouldBeFound("goalRewardGroupId.greaterOrEqualThan=" + DEFAULT_GOAL_REWARD_GROUP_ID);

        // Get all the mGuerillaQuestStageRewardList where goalRewardGroupId greater than or equals to UPDATED_GOAL_REWARD_GROUP_ID
        defaultMGuerillaQuestStageRewardShouldNotBeFound("goalRewardGroupId.greaterOrEqualThan=" + UPDATED_GOAL_REWARD_GROUP_ID);
    }

    @Test
    @Transactional
    public void getAllMGuerillaQuestStageRewardsByGoalRewardGroupIdIsLessThanSomething() throws Exception {
        // Initialize the database
        mGuerillaQuestStageRewardRepository.saveAndFlush(mGuerillaQuestStageReward);

        // Get all the mGuerillaQuestStageRewardList where goalRewardGroupId less than or equals to DEFAULT_GOAL_REWARD_GROUP_ID
        defaultMGuerillaQuestStageRewardShouldNotBeFound("goalRewardGroupId.lessThan=" + DEFAULT_GOAL_REWARD_GROUP_ID);

        // Get all the mGuerillaQuestStageRewardList where goalRewardGroupId less than or equals to UPDATED_GOAL_REWARD_GROUP_ID
        defaultMGuerillaQuestStageRewardShouldBeFound("goalRewardGroupId.lessThan=" + UPDATED_GOAL_REWARD_GROUP_ID);
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultMGuerillaQuestStageRewardShouldBeFound(String filter) throws Exception {
        restMGuerillaQuestStageRewardMockMvc.perform(get("/api/m-guerilla-quest-stage-rewards?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(mGuerillaQuestStageReward.getId().intValue())))
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
        restMGuerillaQuestStageRewardMockMvc.perform(get("/api/m-guerilla-quest-stage-rewards/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultMGuerillaQuestStageRewardShouldNotBeFound(String filter) throws Exception {
        restMGuerillaQuestStageRewardMockMvc.perform(get("/api/m-guerilla-quest-stage-rewards?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restMGuerillaQuestStageRewardMockMvc.perform(get("/api/m-guerilla-quest-stage-rewards/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("0"));
    }


    @Test
    @Transactional
    public void getNonExistingMGuerillaQuestStageReward() throws Exception {
        // Get the mGuerillaQuestStageReward
        restMGuerillaQuestStageRewardMockMvc.perform(get("/api/m-guerilla-quest-stage-rewards/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateMGuerillaQuestStageReward() throws Exception {
        // Initialize the database
        mGuerillaQuestStageRewardRepository.saveAndFlush(mGuerillaQuestStageReward);

        int databaseSizeBeforeUpdate = mGuerillaQuestStageRewardRepository.findAll().size();

        // Update the mGuerillaQuestStageReward
        MGuerillaQuestStageReward updatedMGuerillaQuestStageReward = mGuerillaQuestStageRewardRepository.findById(mGuerillaQuestStageReward.getId()).get();
        // Disconnect from session so that the updates on updatedMGuerillaQuestStageReward are not directly saved in db
        em.detach(updatedMGuerillaQuestStageReward);
        updatedMGuerillaQuestStageReward
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
        MGuerillaQuestStageRewardDTO mGuerillaQuestStageRewardDTO = mGuerillaQuestStageRewardMapper.toDto(updatedMGuerillaQuestStageReward);

        restMGuerillaQuestStageRewardMockMvc.perform(put("/api/m-guerilla-quest-stage-rewards")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mGuerillaQuestStageRewardDTO)))
            .andExpect(status().isOk());

        // Validate the MGuerillaQuestStageReward in the database
        List<MGuerillaQuestStageReward> mGuerillaQuestStageRewardList = mGuerillaQuestStageRewardRepository.findAll();
        assertThat(mGuerillaQuestStageRewardList).hasSize(databaseSizeBeforeUpdate);
        MGuerillaQuestStageReward testMGuerillaQuestStageReward = mGuerillaQuestStageRewardList.get(mGuerillaQuestStageRewardList.size() - 1);
        assertThat(testMGuerillaQuestStageReward.getStageId()).isEqualTo(UPDATED_STAGE_ID);
        assertThat(testMGuerillaQuestStageReward.getExp()).isEqualTo(UPDATED_EXP);
        assertThat(testMGuerillaQuestStageReward.getCoin()).isEqualTo(UPDATED_COIN);
        assertThat(testMGuerillaQuestStageReward.getGuildPoint()).isEqualTo(UPDATED_GUILD_POINT);
        assertThat(testMGuerillaQuestStageReward.getClearRewardGroupId()).isEqualTo(UPDATED_CLEAR_REWARD_GROUP_ID);
        assertThat(testMGuerillaQuestStageReward.getClearRewardWeightId()).isEqualTo(UPDATED_CLEAR_REWARD_WEIGHT_ID);
        assertThat(testMGuerillaQuestStageReward.getAchievementRewardGroupId()).isEqualTo(UPDATED_ACHIEVEMENT_REWARD_GROUP_ID);
        assertThat(testMGuerillaQuestStageReward.getCoopGroupId()).isEqualTo(UPDATED_COOP_GROUP_ID);
        assertThat(testMGuerillaQuestStageReward.getSpecialRewardGroupId()).isEqualTo(UPDATED_SPECIAL_REWARD_GROUP_ID);
        assertThat(testMGuerillaQuestStageReward.getSpecialRewardAmount()).isEqualTo(UPDATED_SPECIAL_REWARD_AMOUNT);
        assertThat(testMGuerillaQuestStageReward.getGoalRewardGroupId()).isEqualTo(UPDATED_GOAL_REWARD_GROUP_ID);
    }

    @Test
    @Transactional
    public void updateNonExistingMGuerillaQuestStageReward() throws Exception {
        int databaseSizeBeforeUpdate = mGuerillaQuestStageRewardRepository.findAll().size();

        // Create the MGuerillaQuestStageReward
        MGuerillaQuestStageRewardDTO mGuerillaQuestStageRewardDTO = mGuerillaQuestStageRewardMapper.toDto(mGuerillaQuestStageReward);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restMGuerillaQuestStageRewardMockMvc.perform(put("/api/m-guerilla-quest-stage-rewards")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mGuerillaQuestStageRewardDTO)))
            .andExpect(status().isBadRequest());

        // Validate the MGuerillaQuestStageReward in the database
        List<MGuerillaQuestStageReward> mGuerillaQuestStageRewardList = mGuerillaQuestStageRewardRepository.findAll();
        assertThat(mGuerillaQuestStageRewardList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteMGuerillaQuestStageReward() throws Exception {
        // Initialize the database
        mGuerillaQuestStageRewardRepository.saveAndFlush(mGuerillaQuestStageReward);

        int databaseSizeBeforeDelete = mGuerillaQuestStageRewardRepository.findAll().size();

        // Delete the mGuerillaQuestStageReward
        restMGuerillaQuestStageRewardMockMvc.perform(delete("/api/m-guerilla-quest-stage-rewards/{id}", mGuerillaQuestStageReward.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database is empty
        List<MGuerillaQuestStageReward> mGuerillaQuestStageRewardList = mGuerillaQuestStageRewardRepository.findAll();
        assertThat(mGuerillaQuestStageRewardList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(MGuerillaQuestStageReward.class);
        MGuerillaQuestStageReward mGuerillaQuestStageReward1 = new MGuerillaQuestStageReward();
        mGuerillaQuestStageReward1.setId(1L);
        MGuerillaQuestStageReward mGuerillaQuestStageReward2 = new MGuerillaQuestStageReward();
        mGuerillaQuestStageReward2.setId(mGuerillaQuestStageReward1.getId());
        assertThat(mGuerillaQuestStageReward1).isEqualTo(mGuerillaQuestStageReward2);
        mGuerillaQuestStageReward2.setId(2L);
        assertThat(mGuerillaQuestStageReward1).isNotEqualTo(mGuerillaQuestStageReward2);
        mGuerillaQuestStageReward1.setId(null);
        assertThat(mGuerillaQuestStageReward1).isNotEqualTo(mGuerillaQuestStageReward2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(MGuerillaQuestStageRewardDTO.class);
        MGuerillaQuestStageRewardDTO mGuerillaQuestStageRewardDTO1 = new MGuerillaQuestStageRewardDTO();
        mGuerillaQuestStageRewardDTO1.setId(1L);
        MGuerillaQuestStageRewardDTO mGuerillaQuestStageRewardDTO2 = new MGuerillaQuestStageRewardDTO();
        assertThat(mGuerillaQuestStageRewardDTO1).isNotEqualTo(mGuerillaQuestStageRewardDTO2);
        mGuerillaQuestStageRewardDTO2.setId(mGuerillaQuestStageRewardDTO1.getId());
        assertThat(mGuerillaQuestStageRewardDTO1).isEqualTo(mGuerillaQuestStageRewardDTO2);
        mGuerillaQuestStageRewardDTO2.setId(2L);
        assertThat(mGuerillaQuestStageRewardDTO1).isNotEqualTo(mGuerillaQuestStageRewardDTO2);
        mGuerillaQuestStageRewardDTO1.setId(null);
        assertThat(mGuerillaQuestStageRewardDTO1).isNotEqualTo(mGuerillaQuestStageRewardDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(mGuerillaQuestStageRewardMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(mGuerillaQuestStageRewardMapper.fromId(null)).isNull();
    }
}
