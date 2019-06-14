package io.shm.tsubasa.web.rest;

import io.shm.tsubasa.TsubasaApp;
import io.shm.tsubasa.domain.MQuestStageReward;
import io.shm.tsubasa.repository.MQuestStageRewardRepository;
import io.shm.tsubasa.service.MQuestStageRewardService;
import io.shm.tsubasa.service.dto.MQuestStageRewardDTO;
import io.shm.tsubasa.service.mapper.MQuestStageRewardMapper;
import io.shm.tsubasa.web.rest.errors.ExceptionTranslator;
import io.shm.tsubasa.service.dto.MQuestStageRewardCriteria;
import io.shm.tsubasa.service.MQuestStageRewardQueryService;

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
 * Integration tests for the {@Link MQuestStageRewardResource} REST controller.
 */
@SpringBootTest(classes = TsubasaApp.class)
public class MQuestStageRewardResourceIT {

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

    @Autowired
    private MQuestStageRewardRepository mQuestStageRewardRepository;

    @Autowired
    private MQuestStageRewardMapper mQuestStageRewardMapper;

    @Autowired
    private MQuestStageRewardService mQuestStageRewardService;

    @Autowired
    private MQuestStageRewardQueryService mQuestStageRewardQueryService;

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

    private MockMvc restMQuestStageRewardMockMvc;

    private MQuestStageReward mQuestStageReward;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final MQuestStageRewardResource mQuestStageRewardResource = new MQuestStageRewardResource(mQuestStageRewardService, mQuestStageRewardQueryService);
        this.restMQuestStageRewardMockMvc = MockMvcBuilders.standaloneSetup(mQuestStageRewardResource)
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
    public static MQuestStageReward createEntity(EntityManager em) {
        MQuestStageReward mQuestStageReward = new MQuestStageReward()
            .stageId(DEFAULT_STAGE_ID)
            .exp(DEFAULT_EXP)
            .coin(DEFAULT_COIN)
            .guildPoint(DEFAULT_GUILD_POINT)
            .clearRewardGroupId(DEFAULT_CLEAR_REWARD_GROUP_ID)
            .clearRewardWeightId(DEFAULT_CLEAR_REWARD_WEIGHT_ID)
            .achievementRewardGroupId(DEFAULT_ACHIEVEMENT_REWARD_GROUP_ID)
            .coopGroupId(DEFAULT_COOP_GROUP_ID)
            .specialRewardGroupId(DEFAULT_SPECIAL_REWARD_GROUP_ID)
            .specialRewardAmount(DEFAULT_SPECIAL_REWARD_AMOUNT);
        return mQuestStageReward;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static MQuestStageReward createUpdatedEntity(EntityManager em) {
        MQuestStageReward mQuestStageReward = new MQuestStageReward()
            .stageId(UPDATED_STAGE_ID)
            .exp(UPDATED_EXP)
            .coin(UPDATED_COIN)
            .guildPoint(UPDATED_GUILD_POINT)
            .clearRewardGroupId(UPDATED_CLEAR_REWARD_GROUP_ID)
            .clearRewardWeightId(UPDATED_CLEAR_REWARD_WEIGHT_ID)
            .achievementRewardGroupId(UPDATED_ACHIEVEMENT_REWARD_GROUP_ID)
            .coopGroupId(UPDATED_COOP_GROUP_ID)
            .specialRewardGroupId(UPDATED_SPECIAL_REWARD_GROUP_ID)
            .specialRewardAmount(UPDATED_SPECIAL_REWARD_AMOUNT);
        return mQuestStageReward;
    }

    @BeforeEach
    public void initTest() {
        mQuestStageReward = createEntity(em);
    }

    @Test
    @Transactional
    public void createMQuestStageReward() throws Exception {
        int databaseSizeBeforeCreate = mQuestStageRewardRepository.findAll().size();

        // Create the MQuestStageReward
        MQuestStageRewardDTO mQuestStageRewardDTO = mQuestStageRewardMapper.toDto(mQuestStageReward);
        restMQuestStageRewardMockMvc.perform(post("/api/m-quest-stage-rewards")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mQuestStageRewardDTO)))
            .andExpect(status().isCreated());

        // Validate the MQuestStageReward in the database
        List<MQuestStageReward> mQuestStageRewardList = mQuestStageRewardRepository.findAll();
        assertThat(mQuestStageRewardList).hasSize(databaseSizeBeforeCreate + 1);
        MQuestStageReward testMQuestStageReward = mQuestStageRewardList.get(mQuestStageRewardList.size() - 1);
        assertThat(testMQuestStageReward.getStageId()).isEqualTo(DEFAULT_STAGE_ID);
        assertThat(testMQuestStageReward.getExp()).isEqualTo(DEFAULT_EXP);
        assertThat(testMQuestStageReward.getCoin()).isEqualTo(DEFAULT_COIN);
        assertThat(testMQuestStageReward.getGuildPoint()).isEqualTo(DEFAULT_GUILD_POINT);
        assertThat(testMQuestStageReward.getClearRewardGroupId()).isEqualTo(DEFAULT_CLEAR_REWARD_GROUP_ID);
        assertThat(testMQuestStageReward.getClearRewardWeightId()).isEqualTo(DEFAULT_CLEAR_REWARD_WEIGHT_ID);
        assertThat(testMQuestStageReward.getAchievementRewardGroupId()).isEqualTo(DEFAULT_ACHIEVEMENT_REWARD_GROUP_ID);
        assertThat(testMQuestStageReward.getCoopGroupId()).isEqualTo(DEFAULT_COOP_GROUP_ID);
        assertThat(testMQuestStageReward.getSpecialRewardGroupId()).isEqualTo(DEFAULT_SPECIAL_REWARD_GROUP_ID);
        assertThat(testMQuestStageReward.getSpecialRewardAmount()).isEqualTo(DEFAULT_SPECIAL_REWARD_AMOUNT);
    }

    @Test
    @Transactional
    public void createMQuestStageRewardWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = mQuestStageRewardRepository.findAll().size();

        // Create the MQuestStageReward with an existing ID
        mQuestStageReward.setId(1L);
        MQuestStageRewardDTO mQuestStageRewardDTO = mQuestStageRewardMapper.toDto(mQuestStageReward);

        // An entity with an existing ID cannot be created, so this API call must fail
        restMQuestStageRewardMockMvc.perform(post("/api/m-quest-stage-rewards")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mQuestStageRewardDTO)))
            .andExpect(status().isBadRequest());

        // Validate the MQuestStageReward in the database
        List<MQuestStageReward> mQuestStageRewardList = mQuestStageRewardRepository.findAll();
        assertThat(mQuestStageRewardList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkStageIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = mQuestStageRewardRepository.findAll().size();
        // set the field null
        mQuestStageReward.setStageId(null);

        // Create the MQuestStageReward, which fails.
        MQuestStageRewardDTO mQuestStageRewardDTO = mQuestStageRewardMapper.toDto(mQuestStageReward);

        restMQuestStageRewardMockMvc.perform(post("/api/m-quest-stage-rewards")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mQuestStageRewardDTO)))
            .andExpect(status().isBadRequest());

        List<MQuestStageReward> mQuestStageRewardList = mQuestStageRewardRepository.findAll();
        assertThat(mQuestStageRewardList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkExpIsRequired() throws Exception {
        int databaseSizeBeforeTest = mQuestStageRewardRepository.findAll().size();
        // set the field null
        mQuestStageReward.setExp(null);

        // Create the MQuestStageReward, which fails.
        MQuestStageRewardDTO mQuestStageRewardDTO = mQuestStageRewardMapper.toDto(mQuestStageReward);

        restMQuestStageRewardMockMvc.perform(post("/api/m-quest-stage-rewards")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mQuestStageRewardDTO)))
            .andExpect(status().isBadRequest());

        List<MQuestStageReward> mQuestStageRewardList = mQuestStageRewardRepository.findAll();
        assertThat(mQuestStageRewardList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkCoinIsRequired() throws Exception {
        int databaseSizeBeforeTest = mQuestStageRewardRepository.findAll().size();
        // set the field null
        mQuestStageReward.setCoin(null);

        // Create the MQuestStageReward, which fails.
        MQuestStageRewardDTO mQuestStageRewardDTO = mQuestStageRewardMapper.toDto(mQuestStageReward);

        restMQuestStageRewardMockMvc.perform(post("/api/m-quest-stage-rewards")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mQuestStageRewardDTO)))
            .andExpect(status().isBadRequest());

        List<MQuestStageReward> mQuestStageRewardList = mQuestStageRewardRepository.findAll();
        assertThat(mQuestStageRewardList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkGuildPointIsRequired() throws Exception {
        int databaseSizeBeforeTest = mQuestStageRewardRepository.findAll().size();
        // set the field null
        mQuestStageReward.setGuildPoint(null);

        // Create the MQuestStageReward, which fails.
        MQuestStageRewardDTO mQuestStageRewardDTO = mQuestStageRewardMapper.toDto(mQuestStageReward);

        restMQuestStageRewardMockMvc.perform(post("/api/m-quest-stage-rewards")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mQuestStageRewardDTO)))
            .andExpect(status().isBadRequest());

        List<MQuestStageReward> mQuestStageRewardList = mQuestStageRewardRepository.findAll();
        assertThat(mQuestStageRewardList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkClearRewardGroupIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = mQuestStageRewardRepository.findAll().size();
        // set the field null
        mQuestStageReward.setClearRewardGroupId(null);

        // Create the MQuestStageReward, which fails.
        MQuestStageRewardDTO mQuestStageRewardDTO = mQuestStageRewardMapper.toDto(mQuestStageReward);

        restMQuestStageRewardMockMvc.perform(post("/api/m-quest-stage-rewards")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mQuestStageRewardDTO)))
            .andExpect(status().isBadRequest());

        List<MQuestStageReward> mQuestStageRewardList = mQuestStageRewardRepository.findAll();
        assertThat(mQuestStageRewardList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkAchievementRewardGroupIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = mQuestStageRewardRepository.findAll().size();
        // set the field null
        mQuestStageReward.setAchievementRewardGroupId(null);

        // Create the MQuestStageReward, which fails.
        MQuestStageRewardDTO mQuestStageRewardDTO = mQuestStageRewardMapper.toDto(mQuestStageReward);

        restMQuestStageRewardMockMvc.perform(post("/api/m-quest-stage-rewards")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mQuestStageRewardDTO)))
            .andExpect(status().isBadRequest());

        List<MQuestStageReward> mQuestStageRewardList = mQuestStageRewardRepository.findAll();
        assertThat(mQuestStageRewardList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkCoopGroupIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = mQuestStageRewardRepository.findAll().size();
        // set the field null
        mQuestStageReward.setCoopGroupId(null);

        // Create the MQuestStageReward, which fails.
        MQuestStageRewardDTO mQuestStageRewardDTO = mQuestStageRewardMapper.toDto(mQuestStageReward);

        restMQuestStageRewardMockMvc.perform(post("/api/m-quest-stage-rewards")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mQuestStageRewardDTO)))
            .andExpect(status().isBadRequest());

        List<MQuestStageReward> mQuestStageRewardList = mQuestStageRewardRepository.findAll();
        assertThat(mQuestStageRewardList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkSpecialRewardAmountIsRequired() throws Exception {
        int databaseSizeBeforeTest = mQuestStageRewardRepository.findAll().size();
        // set the field null
        mQuestStageReward.setSpecialRewardAmount(null);

        // Create the MQuestStageReward, which fails.
        MQuestStageRewardDTO mQuestStageRewardDTO = mQuestStageRewardMapper.toDto(mQuestStageReward);

        restMQuestStageRewardMockMvc.perform(post("/api/m-quest-stage-rewards")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mQuestStageRewardDTO)))
            .andExpect(status().isBadRequest());

        List<MQuestStageReward> mQuestStageRewardList = mQuestStageRewardRepository.findAll();
        assertThat(mQuestStageRewardList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllMQuestStageRewards() throws Exception {
        // Initialize the database
        mQuestStageRewardRepository.saveAndFlush(mQuestStageReward);

        // Get all the mQuestStageRewardList
        restMQuestStageRewardMockMvc.perform(get("/api/m-quest-stage-rewards?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(mQuestStageReward.getId().intValue())))
            .andExpect(jsonPath("$.[*].stageId").value(hasItem(DEFAULT_STAGE_ID)))
            .andExpect(jsonPath("$.[*].exp").value(hasItem(DEFAULT_EXP)))
            .andExpect(jsonPath("$.[*].coin").value(hasItem(DEFAULT_COIN)))
            .andExpect(jsonPath("$.[*].guildPoint").value(hasItem(DEFAULT_GUILD_POINT)))
            .andExpect(jsonPath("$.[*].clearRewardGroupId").value(hasItem(DEFAULT_CLEAR_REWARD_GROUP_ID)))
            .andExpect(jsonPath("$.[*].clearRewardWeightId").value(hasItem(DEFAULT_CLEAR_REWARD_WEIGHT_ID)))
            .andExpect(jsonPath("$.[*].achievementRewardGroupId").value(hasItem(DEFAULT_ACHIEVEMENT_REWARD_GROUP_ID)))
            .andExpect(jsonPath("$.[*].coopGroupId").value(hasItem(DEFAULT_COOP_GROUP_ID)))
            .andExpect(jsonPath("$.[*].specialRewardGroupId").value(hasItem(DEFAULT_SPECIAL_REWARD_GROUP_ID)))
            .andExpect(jsonPath("$.[*].specialRewardAmount").value(hasItem(DEFAULT_SPECIAL_REWARD_AMOUNT)));
    }
    
    @Test
    @Transactional
    public void getMQuestStageReward() throws Exception {
        // Initialize the database
        mQuestStageRewardRepository.saveAndFlush(mQuestStageReward);

        // Get the mQuestStageReward
        restMQuestStageRewardMockMvc.perform(get("/api/m-quest-stage-rewards/{id}", mQuestStageReward.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(mQuestStageReward.getId().intValue()))
            .andExpect(jsonPath("$.stageId").value(DEFAULT_STAGE_ID))
            .andExpect(jsonPath("$.exp").value(DEFAULT_EXP))
            .andExpect(jsonPath("$.coin").value(DEFAULT_COIN))
            .andExpect(jsonPath("$.guildPoint").value(DEFAULT_GUILD_POINT))
            .andExpect(jsonPath("$.clearRewardGroupId").value(DEFAULT_CLEAR_REWARD_GROUP_ID))
            .andExpect(jsonPath("$.clearRewardWeightId").value(DEFAULT_CLEAR_REWARD_WEIGHT_ID))
            .andExpect(jsonPath("$.achievementRewardGroupId").value(DEFAULT_ACHIEVEMENT_REWARD_GROUP_ID))
            .andExpect(jsonPath("$.coopGroupId").value(DEFAULT_COOP_GROUP_ID))
            .andExpect(jsonPath("$.specialRewardGroupId").value(DEFAULT_SPECIAL_REWARD_GROUP_ID))
            .andExpect(jsonPath("$.specialRewardAmount").value(DEFAULT_SPECIAL_REWARD_AMOUNT));
    }

    @Test
    @Transactional
    public void getAllMQuestStageRewardsByStageIdIsEqualToSomething() throws Exception {
        // Initialize the database
        mQuestStageRewardRepository.saveAndFlush(mQuestStageReward);

        // Get all the mQuestStageRewardList where stageId equals to DEFAULT_STAGE_ID
        defaultMQuestStageRewardShouldBeFound("stageId.equals=" + DEFAULT_STAGE_ID);

        // Get all the mQuestStageRewardList where stageId equals to UPDATED_STAGE_ID
        defaultMQuestStageRewardShouldNotBeFound("stageId.equals=" + UPDATED_STAGE_ID);
    }

    @Test
    @Transactional
    public void getAllMQuestStageRewardsByStageIdIsInShouldWork() throws Exception {
        // Initialize the database
        mQuestStageRewardRepository.saveAndFlush(mQuestStageReward);

        // Get all the mQuestStageRewardList where stageId in DEFAULT_STAGE_ID or UPDATED_STAGE_ID
        defaultMQuestStageRewardShouldBeFound("stageId.in=" + DEFAULT_STAGE_ID + "," + UPDATED_STAGE_ID);

        // Get all the mQuestStageRewardList where stageId equals to UPDATED_STAGE_ID
        defaultMQuestStageRewardShouldNotBeFound("stageId.in=" + UPDATED_STAGE_ID);
    }

    @Test
    @Transactional
    public void getAllMQuestStageRewardsByStageIdIsNullOrNotNull() throws Exception {
        // Initialize the database
        mQuestStageRewardRepository.saveAndFlush(mQuestStageReward);

        // Get all the mQuestStageRewardList where stageId is not null
        defaultMQuestStageRewardShouldBeFound("stageId.specified=true");

        // Get all the mQuestStageRewardList where stageId is null
        defaultMQuestStageRewardShouldNotBeFound("stageId.specified=false");
    }

    @Test
    @Transactional
    public void getAllMQuestStageRewardsByStageIdIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mQuestStageRewardRepository.saveAndFlush(mQuestStageReward);

        // Get all the mQuestStageRewardList where stageId greater than or equals to DEFAULT_STAGE_ID
        defaultMQuestStageRewardShouldBeFound("stageId.greaterOrEqualThan=" + DEFAULT_STAGE_ID);

        // Get all the mQuestStageRewardList where stageId greater than or equals to UPDATED_STAGE_ID
        defaultMQuestStageRewardShouldNotBeFound("stageId.greaterOrEqualThan=" + UPDATED_STAGE_ID);
    }

    @Test
    @Transactional
    public void getAllMQuestStageRewardsByStageIdIsLessThanSomething() throws Exception {
        // Initialize the database
        mQuestStageRewardRepository.saveAndFlush(mQuestStageReward);

        // Get all the mQuestStageRewardList where stageId less than or equals to DEFAULT_STAGE_ID
        defaultMQuestStageRewardShouldNotBeFound("stageId.lessThan=" + DEFAULT_STAGE_ID);

        // Get all the mQuestStageRewardList where stageId less than or equals to UPDATED_STAGE_ID
        defaultMQuestStageRewardShouldBeFound("stageId.lessThan=" + UPDATED_STAGE_ID);
    }


    @Test
    @Transactional
    public void getAllMQuestStageRewardsByExpIsEqualToSomething() throws Exception {
        // Initialize the database
        mQuestStageRewardRepository.saveAndFlush(mQuestStageReward);

        // Get all the mQuestStageRewardList where exp equals to DEFAULT_EXP
        defaultMQuestStageRewardShouldBeFound("exp.equals=" + DEFAULT_EXP);

        // Get all the mQuestStageRewardList where exp equals to UPDATED_EXP
        defaultMQuestStageRewardShouldNotBeFound("exp.equals=" + UPDATED_EXP);
    }

    @Test
    @Transactional
    public void getAllMQuestStageRewardsByExpIsInShouldWork() throws Exception {
        // Initialize the database
        mQuestStageRewardRepository.saveAndFlush(mQuestStageReward);

        // Get all the mQuestStageRewardList where exp in DEFAULT_EXP or UPDATED_EXP
        defaultMQuestStageRewardShouldBeFound("exp.in=" + DEFAULT_EXP + "," + UPDATED_EXP);

        // Get all the mQuestStageRewardList where exp equals to UPDATED_EXP
        defaultMQuestStageRewardShouldNotBeFound("exp.in=" + UPDATED_EXP);
    }

    @Test
    @Transactional
    public void getAllMQuestStageRewardsByExpIsNullOrNotNull() throws Exception {
        // Initialize the database
        mQuestStageRewardRepository.saveAndFlush(mQuestStageReward);

        // Get all the mQuestStageRewardList where exp is not null
        defaultMQuestStageRewardShouldBeFound("exp.specified=true");

        // Get all the mQuestStageRewardList where exp is null
        defaultMQuestStageRewardShouldNotBeFound("exp.specified=false");
    }

    @Test
    @Transactional
    public void getAllMQuestStageRewardsByExpIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mQuestStageRewardRepository.saveAndFlush(mQuestStageReward);

        // Get all the mQuestStageRewardList where exp greater than or equals to DEFAULT_EXP
        defaultMQuestStageRewardShouldBeFound("exp.greaterOrEqualThan=" + DEFAULT_EXP);

        // Get all the mQuestStageRewardList where exp greater than or equals to UPDATED_EXP
        defaultMQuestStageRewardShouldNotBeFound("exp.greaterOrEqualThan=" + UPDATED_EXP);
    }

    @Test
    @Transactional
    public void getAllMQuestStageRewardsByExpIsLessThanSomething() throws Exception {
        // Initialize the database
        mQuestStageRewardRepository.saveAndFlush(mQuestStageReward);

        // Get all the mQuestStageRewardList where exp less than or equals to DEFAULT_EXP
        defaultMQuestStageRewardShouldNotBeFound("exp.lessThan=" + DEFAULT_EXP);

        // Get all the mQuestStageRewardList where exp less than or equals to UPDATED_EXP
        defaultMQuestStageRewardShouldBeFound("exp.lessThan=" + UPDATED_EXP);
    }


    @Test
    @Transactional
    public void getAllMQuestStageRewardsByCoinIsEqualToSomething() throws Exception {
        // Initialize the database
        mQuestStageRewardRepository.saveAndFlush(mQuestStageReward);

        // Get all the mQuestStageRewardList where coin equals to DEFAULT_COIN
        defaultMQuestStageRewardShouldBeFound("coin.equals=" + DEFAULT_COIN);

        // Get all the mQuestStageRewardList where coin equals to UPDATED_COIN
        defaultMQuestStageRewardShouldNotBeFound("coin.equals=" + UPDATED_COIN);
    }

    @Test
    @Transactional
    public void getAllMQuestStageRewardsByCoinIsInShouldWork() throws Exception {
        // Initialize the database
        mQuestStageRewardRepository.saveAndFlush(mQuestStageReward);

        // Get all the mQuestStageRewardList where coin in DEFAULT_COIN or UPDATED_COIN
        defaultMQuestStageRewardShouldBeFound("coin.in=" + DEFAULT_COIN + "," + UPDATED_COIN);

        // Get all the mQuestStageRewardList where coin equals to UPDATED_COIN
        defaultMQuestStageRewardShouldNotBeFound("coin.in=" + UPDATED_COIN);
    }

    @Test
    @Transactional
    public void getAllMQuestStageRewardsByCoinIsNullOrNotNull() throws Exception {
        // Initialize the database
        mQuestStageRewardRepository.saveAndFlush(mQuestStageReward);

        // Get all the mQuestStageRewardList where coin is not null
        defaultMQuestStageRewardShouldBeFound("coin.specified=true");

        // Get all the mQuestStageRewardList where coin is null
        defaultMQuestStageRewardShouldNotBeFound("coin.specified=false");
    }

    @Test
    @Transactional
    public void getAllMQuestStageRewardsByCoinIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mQuestStageRewardRepository.saveAndFlush(mQuestStageReward);

        // Get all the mQuestStageRewardList where coin greater than or equals to DEFAULT_COIN
        defaultMQuestStageRewardShouldBeFound("coin.greaterOrEqualThan=" + DEFAULT_COIN);

        // Get all the mQuestStageRewardList where coin greater than or equals to UPDATED_COIN
        defaultMQuestStageRewardShouldNotBeFound("coin.greaterOrEqualThan=" + UPDATED_COIN);
    }

    @Test
    @Transactional
    public void getAllMQuestStageRewardsByCoinIsLessThanSomething() throws Exception {
        // Initialize the database
        mQuestStageRewardRepository.saveAndFlush(mQuestStageReward);

        // Get all the mQuestStageRewardList where coin less than or equals to DEFAULT_COIN
        defaultMQuestStageRewardShouldNotBeFound("coin.lessThan=" + DEFAULT_COIN);

        // Get all the mQuestStageRewardList where coin less than or equals to UPDATED_COIN
        defaultMQuestStageRewardShouldBeFound("coin.lessThan=" + UPDATED_COIN);
    }


    @Test
    @Transactional
    public void getAllMQuestStageRewardsByGuildPointIsEqualToSomething() throws Exception {
        // Initialize the database
        mQuestStageRewardRepository.saveAndFlush(mQuestStageReward);

        // Get all the mQuestStageRewardList where guildPoint equals to DEFAULT_GUILD_POINT
        defaultMQuestStageRewardShouldBeFound("guildPoint.equals=" + DEFAULT_GUILD_POINT);

        // Get all the mQuestStageRewardList where guildPoint equals to UPDATED_GUILD_POINT
        defaultMQuestStageRewardShouldNotBeFound("guildPoint.equals=" + UPDATED_GUILD_POINT);
    }

    @Test
    @Transactional
    public void getAllMQuestStageRewardsByGuildPointIsInShouldWork() throws Exception {
        // Initialize the database
        mQuestStageRewardRepository.saveAndFlush(mQuestStageReward);

        // Get all the mQuestStageRewardList where guildPoint in DEFAULT_GUILD_POINT or UPDATED_GUILD_POINT
        defaultMQuestStageRewardShouldBeFound("guildPoint.in=" + DEFAULT_GUILD_POINT + "," + UPDATED_GUILD_POINT);

        // Get all the mQuestStageRewardList where guildPoint equals to UPDATED_GUILD_POINT
        defaultMQuestStageRewardShouldNotBeFound("guildPoint.in=" + UPDATED_GUILD_POINT);
    }

    @Test
    @Transactional
    public void getAllMQuestStageRewardsByGuildPointIsNullOrNotNull() throws Exception {
        // Initialize the database
        mQuestStageRewardRepository.saveAndFlush(mQuestStageReward);

        // Get all the mQuestStageRewardList where guildPoint is not null
        defaultMQuestStageRewardShouldBeFound("guildPoint.specified=true");

        // Get all the mQuestStageRewardList where guildPoint is null
        defaultMQuestStageRewardShouldNotBeFound("guildPoint.specified=false");
    }

    @Test
    @Transactional
    public void getAllMQuestStageRewardsByGuildPointIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mQuestStageRewardRepository.saveAndFlush(mQuestStageReward);

        // Get all the mQuestStageRewardList where guildPoint greater than or equals to DEFAULT_GUILD_POINT
        defaultMQuestStageRewardShouldBeFound("guildPoint.greaterOrEqualThan=" + DEFAULT_GUILD_POINT);

        // Get all the mQuestStageRewardList where guildPoint greater than or equals to UPDATED_GUILD_POINT
        defaultMQuestStageRewardShouldNotBeFound("guildPoint.greaterOrEqualThan=" + UPDATED_GUILD_POINT);
    }

    @Test
    @Transactional
    public void getAllMQuestStageRewardsByGuildPointIsLessThanSomething() throws Exception {
        // Initialize the database
        mQuestStageRewardRepository.saveAndFlush(mQuestStageReward);

        // Get all the mQuestStageRewardList where guildPoint less than or equals to DEFAULT_GUILD_POINT
        defaultMQuestStageRewardShouldNotBeFound("guildPoint.lessThan=" + DEFAULT_GUILD_POINT);

        // Get all the mQuestStageRewardList where guildPoint less than or equals to UPDATED_GUILD_POINT
        defaultMQuestStageRewardShouldBeFound("guildPoint.lessThan=" + UPDATED_GUILD_POINT);
    }


    @Test
    @Transactional
    public void getAllMQuestStageRewardsByClearRewardGroupIdIsEqualToSomething() throws Exception {
        // Initialize the database
        mQuestStageRewardRepository.saveAndFlush(mQuestStageReward);

        // Get all the mQuestStageRewardList where clearRewardGroupId equals to DEFAULT_CLEAR_REWARD_GROUP_ID
        defaultMQuestStageRewardShouldBeFound("clearRewardGroupId.equals=" + DEFAULT_CLEAR_REWARD_GROUP_ID);

        // Get all the mQuestStageRewardList where clearRewardGroupId equals to UPDATED_CLEAR_REWARD_GROUP_ID
        defaultMQuestStageRewardShouldNotBeFound("clearRewardGroupId.equals=" + UPDATED_CLEAR_REWARD_GROUP_ID);
    }

    @Test
    @Transactional
    public void getAllMQuestStageRewardsByClearRewardGroupIdIsInShouldWork() throws Exception {
        // Initialize the database
        mQuestStageRewardRepository.saveAndFlush(mQuestStageReward);

        // Get all the mQuestStageRewardList where clearRewardGroupId in DEFAULT_CLEAR_REWARD_GROUP_ID or UPDATED_CLEAR_REWARD_GROUP_ID
        defaultMQuestStageRewardShouldBeFound("clearRewardGroupId.in=" + DEFAULT_CLEAR_REWARD_GROUP_ID + "," + UPDATED_CLEAR_REWARD_GROUP_ID);

        // Get all the mQuestStageRewardList where clearRewardGroupId equals to UPDATED_CLEAR_REWARD_GROUP_ID
        defaultMQuestStageRewardShouldNotBeFound("clearRewardGroupId.in=" + UPDATED_CLEAR_REWARD_GROUP_ID);
    }

    @Test
    @Transactional
    public void getAllMQuestStageRewardsByClearRewardGroupIdIsNullOrNotNull() throws Exception {
        // Initialize the database
        mQuestStageRewardRepository.saveAndFlush(mQuestStageReward);

        // Get all the mQuestStageRewardList where clearRewardGroupId is not null
        defaultMQuestStageRewardShouldBeFound("clearRewardGroupId.specified=true");

        // Get all the mQuestStageRewardList where clearRewardGroupId is null
        defaultMQuestStageRewardShouldNotBeFound("clearRewardGroupId.specified=false");
    }

    @Test
    @Transactional
    public void getAllMQuestStageRewardsByClearRewardGroupIdIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mQuestStageRewardRepository.saveAndFlush(mQuestStageReward);

        // Get all the mQuestStageRewardList where clearRewardGroupId greater than or equals to DEFAULT_CLEAR_REWARD_GROUP_ID
        defaultMQuestStageRewardShouldBeFound("clearRewardGroupId.greaterOrEqualThan=" + DEFAULT_CLEAR_REWARD_GROUP_ID);

        // Get all the mQuestStageRewardList where clearRewardGroupId greater than or equals to UPDATED_CLEAR_REWARD_GROUP_ID
        defaultMQuestStageRewardShouldNotBeFound("clearRewardGroupId.greaterOrEqualThan=" + UPDATED_CLEAR_REWARD_GROUP_ID);
    }

    @Test
    @Transactional
    public void getAllMQuestStageRewardsByClearRewardGroupIdIsLessThanSomething() throws Exception {
        // Initialize the database
        mQuestStageRewardRepository.saveAndFlush(mQuestStageReward);

        // Get all the mQuestStageRewardList where clearRewardGroupId less than or equals to DEFAULT_CLEAR_REWARD_GROUP_ID
        defaultMQuestStageRewardShouldNotBeFound("clearRewardGroupId.lessThan=" + DEFAULT_CLEAR_REWARD_GROUP_ID);

        // Get all the mQuestStageRewardList where clearRewardGroupId less than or equals to UPDATED_CLEAR_REWARD_GROUP_ID
        defaultMQuestStageRewardShouldBeFound("clearRewardGroupId.lessThan=" + UPDATED_CLEAR_REWARD_GROUP_ID);
    }


    @Test
    @Transactional
    public void getAllMQuestStageRewardsByClearRewardWeightIdIsEqualToSomething() throws Exception {
        // Initialize the database
        mQuestStageRewardRepository.saveAndFlush(mQuestStageReward);

        // Get all the mQuestStageRewardList where clearRewardWeightId equals to DEFAULT_CLEAR_REWARD_WEIGHT_ID
        defaultMQuestStageRewardShouldBeFound("clearRewardWeightId.equals=" + DEFAULT_CLEAR_REWARD_WEIGHT_ID);

        // Get all the mQuestStageRewardList where clearRewardWeightId equals to UPDATED_CLEAR_REWARD_WEIGHT_ID
        defaultMQuestStageRewardShouldNotBeFound("clearRewardWeightId.equals=" + UPDATED_CLEAR_REWARD_WEIGHT_ID);
    }

    @Test
    @Transactional
    public void getAllMQuestStageRewardsByClearRewardWeightIdIsInShouldWork() throws Exception {
        // Initialize the database
        mQuestStageRewardRepository.saveAndFlush(mQuestStageReward);

        // Get all the mQuestStageRewardList where clearRewardWeightId in DEFAULT_CLEAR_REWARD_WEIGHT_ID or UPDATED_CLEAR_REWARD_WEIGHT_ID
        defaultMQuestStageRewardShouldBeFound("clearRewardWeightId.in=" + DEFAULT_CLEAR_REWARD_WEIGHT_ID + "," + UPDATED_CLEAR_REWARD_WEIGHT_ID);

        // Get all the mQuestStageRewardList where clearRewardWeightId equals to UPDATED_CLEAR_REWARD_WEIGHT_ID
        defaultMQuestStageRewardShouldNotBeFound("clearRewardWeightId.in=" + UPDATED_CLEAR_REWARD_WEIGHT_ID);
    }

    @Test
    @Transactional
    public void getAllMQuestStageRewardsByClearRewardWeightIdIsNullOrNotNull() throws Exception {
        // Initialize the database
        mQuestStageRewardRepository.saveAndFlush(mQuestStageReward);

        // Get all the mQuestStageRewardList where clearRewardWeightId is not null
        defaultMQuestStageRewardShouldBeFound("clearRewardWeightId.specified=true");

        // Get all the mQuestStageRewardList where clearRewardWeightId is null
        defaultMQuestStageRewardShouldNotBeFound("clearRewardWeightId.specified=false");
    }

    @Test
    @Transactional
    public void getAllMQuestStageRewardsByClearRewardWeightIdIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mQuestStageRewardRepository.saveAndFlush(mQuestStageReward);

        // Get all the mQuestStageRewardList where clearRewardWeightId greater than or equals to DEFAULT_CLEAR_REWARD_WEIGHT_ID
        defaultMQuestStageRewardShouldBeFound("clearRewardWeightId.greaterOrEqualThan=" + DEFAULT_CLEAR_REWARD_WEIGHT_ID);

        // Get all the mQuestStageRewardList where clearRewardWeightId greater than or equals to UPDATED_CLEAR_REWARD_WEIGHT_ID
        defaultMQuestStageRewardShouldNotBeFound("clearRewardWeightId.greaterOrEqualThan=" + UPDATED_CLEAR_REWARD_WEIGHT_ID);
    }

    @Test
    @Transactional
    public void getAllMQuestStageRewardsByClearRewardWeightIdIsLessThanSomething() throws Exception {
        // Initialize the database
        mQuestStageRewardRepository.saveAndFlush(mQuestStageReward);

        // Get all the mQuestStageRewardList where clearRewardWeightId less than or equals to DEFAULT_CLEAR_REWARD_WEIGHT_ID
        defaultMQuestStageRewardShouldNotBeFound("clearRewardWeightId.lessThan=" + DEFAULT_CLEAR_REWARD_WEIGHT_ID);

        // Get all the mQuestStageRewardList where clearRewardWeightId less than or equals to UPDATED_CLEAR_REWARD_WEIGHT_ID
        defaultMQuestStageRewardShouldBeFound("clearRewardWeightId.lessThan=" + UPDATED_CLEAR_REWARD_WEIGHT_ID);
    }


    @Test
    @Transactional
    public void getAllMQuestStageRewardsByAchievementRewardGroupIdIsEqualToSomething() throws Exception {
        // Initialize the database
        mQuestStageRewardRepository.saveAndFlush(mQuestStageReward);

        // Get all the mQuestStageRewardList where achievementRewardGroupId equals to DEFAULT_ACHIEVEMENT_REWARD_GROUP_ID
        defaultMQuestStageRewardShouldBeFound("achievementRewardGroupId.equals=" + DEFAULT_ACHIEVEMENT_REWARD_GROUP_ID);

        // Get all the mQuestStageRewardList where achievementRewardGroupId equals to UPDATED_ACHIEVEMENT_REWARD_GROUP_ID
        defaultMQuestStageRewardShouldNotBeFound("achievementRewardGroupId.equals=" + UPDATED_ACHIEVEMENT_REWARD_GROUP_ID);
    }

    @Test
    @Transactional
    public void getAllMQuestStageRewardsByAchievementRewardGroupIdIsInShouldWork() throws Exception {
        // Initialize the database
        mQuestStageRewardRepository.saveAndFlush(mQuestStageReward);

        // Get all the mQuestStageRewardList where achievementRewardGroupId in DEFAULT_ACHIEVEMENT_REWARD_GROUP_ID or UPDATED_ACHIEVEMENT_REWARD_GROUP_ID
        defaultMQuestStageRewardShouldBeFound("achievementRewardGroupId.in=" + DEFAULT_ACHIEVEMENT_REWARD_GROUP_ID + "," + UPDATED_ACHIEVEMENT_REWARD_GROUP_ID);

        // Get all the mQuestStageRewardList where achievementRewardGroupId equals to UPDATED_ACHIEVEMENT_REWARD_GROUP_ID
        defaultMQuestStageRewardShouldNotBeFound("achievementRewardGroupId.in=" + UPDATED_ACHIEVEMENT_REWARD_GROUP_ID);
    }

    @Test
    @Transactional
    public void getAllMQuestStageRewardsByAchievementRewardGroupIdIsNullOrNotNull() throws Exception {
        // Initialize the database
        mQuestStageRewardRepository.saveAndFlush(mQuestStageReward);

        // Get all the mQuestStageRewardList where achievementRewardGroupId is not null
        defaultMQuestStageRewardShouldBeFound("achievementRewardGroupId.specified=true");

        // Get all the mQuestStageRewardList where achievementRewardGroupId is null
        defaultMQuestStageRewardShouldNotBeFound("achievementRewardGroupId.specified=false");
    }

    @Test
    @Transactional
    public void getAllMQuestStageRewardsByAchievementRewardGroupIdIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mQuestStageRewardRepository.saveAndFlush(mQuestStageReward);

        // Get all the mQuestStageRewardList where achievementRewardGroupId greater than or equals to DEFAULT_ACHIEVEMENT_REWARD_GROUP_ID
        defaultMQuestStageRewardShouldBeFound("achievementRewardGroupId.greaterOrEqualThan=" + DEFAULT_ACHIEVEMENT_REWARD_GROUP_ID);

        // Get all the mQuestStageRewardList where achievementRewardGroupId greater than or equals to UPDATED_ACHIEVEMENT_REWARD_GROUP_ID
        defaultMQuestStageRewardShouldNotBeFound("achievementRewardGroupId.greaterOrEqualThan=" + UPDATED_ACHIEVEMENT_REWARD_GROUP_ID);
    }

    @Test
    @Transactional
    public void getAllMQuestStageRewardsByAchievementRewardGroupIdIsLessThanSomething() throws Exception {
        // Initialize the database
        mQuestStageRewardRepository.saveAndFlush(mQuestStageReward);

        // Get all the mQuestStageRewardList where achievementRewardGroupId less than or equals to DEFAULT_ACHIEVEMENT_REWARD_GROUP_ID
        defaultMQuestStageRewardShouldNotBeFound("achievementRewardGroupId.lessThan=" + DEFAULT_ACHIEVEMENT_REWARD_GROUP_ID);

        // Get all the mQuestStageRewardList where achievementRewardGroupId less than or equals to UPDATED_ACHIEVEMENT_REWARD_GROUP_ID
        defaultMQuestStageRewardShouldBeFound("achievementRewardGroupId.lessThan=" + UPDATED_ACHIEVEMENT_REWARD_GROUP_ID);
    }


    @Test
    @Transactional
    public void getAllMQuestStageRewardsByCoopGroupIdIsEqualToSomething() throws Exception {
        // Initialize the database
        mQuestStageRewardRepository.saveAndFlush(mQuestStageReward);

        // Get all the mQuestStageRewardList where coopGroupId equals to DEFAULT_COOP_GROUP_ID
        defaultMQuestStageRewardShouldBeFound("coopGroupId.equals=" + DEFAULT_COOP_GROUP_ID);

        // Get all the mQuestStageRewardList where coopGroupId equals to UPDATED_COOP_GROUP_ID
        defaultMQuestStageRewardShouldNotBeFound("coopGroupId.equals=" + UPDATED_COOP_GROUP_ID);
    }

    @Test
    @Transactional
    public void getAllMQuestStageRewardsByCoopGroupIdIsInShouldWork() throws Exception {
        // Initialize the database
        mQuestStageRewardRepository.saveAndFlush(mQuestStageReward);

        // Get all the mQuestStageRewardList where coopGroupId in DEFAULT_COOP_GROUP_ID or UPDATED_COOP_GROUP_ID
        defaultMQuestStageRewardShouldBeFound("coopGroupId.in=" + DEFAULT_COOP_GROUP_ID + "," + UPDATED_COOP_GROUP_ID);

        // Get all the mQuestStageRewardList where coopGroupId equals to UPDATED_COOP_GROUP_ID
        defaultMQuestStageRewardShouldNotBeFound("coopGroupId.in=" + UPDATED_COOP_GROUP_ID);
    }

    @Test
    @Transactional
    public void getAllMQuestStageRewardsByCoopGroupIdIsNullOrNotNull() throws Exception {
        // Initialize the database
        mQuestStageRewardRepository.saveAndFlush(mQuestStageReward);

        // Get all the mQuestStageRewardList where coopGroupId is not null
        defaultMQuestStageRewardShouldBeFound("coopGroupId.specified=true");

        // Get all the mQuestStageRewardList where coopGroupId is null
        defaultMQuestStageRewardShouldNotBeFound("coopGroupId.specified=false");
    }

    @Test
    @Transactional
    public void getAllMQuestStageRewardsByCoopGroupIdIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mQuestStageRewardRepository.saveAndFlush(mQuestStageReward);

        // Get all the mQuestStageRewardList where coopGroupId greater than or equals to DEFAULT_COOP_GROUP_ID
        defaultMQuestStageRewardShouldBeFound("coopGroupId.greaterOrEqualThan=" + DEFAULT_COOP_GROUP_ID);

        // Get all the mQuestStageRewardList where coopGroupId greater than or equals to UPDATED_COOP_GROUP_ID
        defaultMQuestStageRewardShouldNotBeFound("coopGroupId.greaterOrEqualThan=" + UPDATED_COOP_GROUP_ID);
    }

    @Test
    @Transactional
    public void getAllMQuestStageRewardsByCoopGroupIdIsLessThanSomething() throws Exception {
        // Initialize the database
        mQuestStageRewardRepository.saveAndFlush(mQuestStageReward);

        // Get all the mQuestStageRewardList where coopGroupId less than or equals to DEFAULT_COOP_GROUP_ID
        defaultMQuestStageRewardShouldNotBeFound("coopGroupId.lessThan=" + DEFAULT_COOP_GROUP_ID);

        // Get all the mQuestStageRewardList where coopGroupId less than or equals to UPDATED_COOP_GROUP_ID
        defaultMQuestStageRewardShouldBeFound("coopGroupId.lessThan=" + UPDATED_COOP_GROUP_ID);
    }


    @Test
    @Transactional
    public void getAllMQuestStageRewardsBySpecialRewardGroupIdIsEqualToSomething() throws Exception {
        // Initialize the database
        mQuestStageRewardRepository.saveAndFlush(mQuestStageReward);

        // Get all the mQuestStageRewardList where specialRewardGroupId equals to DEFAULT_SPECIAL_REWARD_GROUP_ID
        defaultMQuestStageRewardShouldBeFound("specialRewardGroupId.equals=" + DEFAULT_SPECIAL_REWARD_GROUP_ID);

        // Get all the mQuestStageRewardList where specialRewardGroupId equals to UPDATED_SPECIAL_REWARD_GROUP_ID
        defaultMQuestStageRewardShouldNotBeFound("specialRewardGroupId.equals=" + UPDATED_SPECIAL_REWARD_GROUP_ID);
    }

    @Test
    @Transactional
    public void getAllMQuestStageRewardsBySpecialRewardGroupIdIsInShouldWork() throws Exception {
        // Initialize the database
        mQuestStageRewardRepository.saveAndFlush(mQuestStageReward);

        // Get all the mQuestStageRewardList where specialRewardGroupId in DEFAULT_SPECIAL_REWARD_GROUP_ID or UPDATED_SPECIAL_REWARD_GROUP_ID
        defaultMQuestStageRewardShouldBeFound("specialRewardGroupId.in=" + DEFAULT_SPECIAL_REWARD_GROUP_ID + "," + UPDATED_SPECIAL_REWARD_GROUP_ID);

        // Get all the mQuestStageRewardList where specialRewardGroupId equals to UPDATED_SPECIAL_REWARD_GROUP_ID
        defaultMQuestStageRewardShouldNotBeFound("specialRewardGroupId.in=" + UPDATED_SPECIAL_REWARD_GROUP_ID);
    }

    @Test
    @Transactional
    public void getAllMQuestStageRewardsBySpecialRewardGroupIdIsNullOrNotNull() throws Exception {
        // Initialize the database
        mQuestStageRewardRepository.saveAndFlush(mQuestStageReward);

        // Get all the mQuestStageRewardList where specialRewardGroupId is not null
        defaultMQuestStageRewardShouldBeFound("specialRewardGroupId.specified=true");

        // Get all the mQuestStageRewardList where specialRewardGroupId is null
        defaultMQuestStageRewardShouldNotBeFound("specialRewardGroupId.specified=false");
    }

    @Test
    @Transactional
    public void getAllMQuestStageRewardsBySpecialRewardGroupIdIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mQuestStageRewardRepository.saveAndFlush(mQuestStageReward);

        // Get all the mQuestStageRewardList where specialRewardGroupId greater than or equals to DEFAULT_SPECIAL_REWARD_GROUP_ID
        defaultMQuestStageRewardShouldBeFound("specialRewardGroupId.greaterOrEqualThan=" + DEFAULT_SPECIAL_REWARD_GROUP_ID);

        // Get all the mQuestStageRewardList where specialRewardGroupId greater than or equals to UPDATED_SPECIAL_REWARD_GROUP_ID
        defaultMQuestStageRewardShouldNotBeFound("specialRewardGroupId.greaterOrEqualThan=" + UPDATED_SPECIAL_REWARD_GROUP_ID);
    }

    @Test
    @Transactional
    public void getAllMQuestStageRewardsBySpecialRewardGroupIdIsLessThanSomething() throws Exception {
        // Initialize the database
        mQuestStageRewardRepository.saveAndFlush(mQuestStageReward);

        // Get all the mQuestStageRewardList where specialRewardGroupId less than or equals to DEFAULT_SPECIAL_REWARD_GROUP_ID
        defaultMQuestStageRewardShouldNotBeFound("specialRewardGroupId.lessThan=" + DEFAULT_SPECIAL_REWARD_GROUP_ID);

        // Get all the mQuestStageRewardList where specialRewardGroupId less than or equals to UPDATED_SPECIAL_REWARD_GROUP_ID
        defaultMQuestStageRewardShouldBeFound("specialRewardGroupId.lessThan=" + UPDATED_SPECIAL_REWARD_GROUP_ID);
    }


    @Test
    @Transactional
    public void getAllMQuestStageRewardsBySpecialRewardAmountIsEqualToSomething() throws Exception {
        // Initialize the database
        mQuestStageRewardRepository.saveAndFlush(mQuestStageReward);

        // Get all the mQuestStageRewardList where specialRewardAmount equals to DEFAULT_SPECIAL_REWARD_AMOUNT
        defaultMQuestStageRewardShouldBeFound("specialRewardAmount.equals=" + DEFAULT_SPECIAL_REWARD_AMOUNT);

        // Get all the mQuestStageRewardList where specialRewardAmount equals to UPDATED_SPECIAL_REWARD_AMOUNT
        defaultMQuestStageRewardShouldNotBeFound("specialRewardAmount.equals=" + UPDATED_SPECIAL_REWARD_AMOUNT);
    }

    @Test
    @Transactional
    public void getAllMQuestStageRewardsBySpecialRewardAmountIsInShouldWork() throws Exception {
        // Initialize the database
        mQuestStageRewardRepository.saveAndFlush(mQuestStageReward);

        // Get all the mQuestStageRewardList where specialRewardAmount in DEFAULT_SPECIAL_REWARD_AMOUNT or UPDATED_SPECIAL_REWARD_AMOUNT
        defaultMQuestStageRewardShouldBeFound("specialRewardAmount.in=" + DEFAULT_SPECIAL_REWARD_AMOUNT + "," + UPDATED_SPECIAL_REWARD_AMOUNT);

        // Get all the mQuestStageRewardList where specialRewardAmount equals to UPDATED_SPECIAL_REWARD_AMOUNT
        defaultMQuestStageRewardShouldNotBeFound("specialRewardAmount.in=" + UPDATED_SPECIAL_REWARD_AMOUNT);
    }

    @Test
    @Transactional
    public void getAllMQuestStageRewardsBySpecialRewardAmountIsNullOrNotNull() throws Exception {
        // Initialize the database
        mQuestStageRewardRepository.saveAndFlush(mQuestStageReward);

        // Get all the mQuestStageRewardList where specialRewardAmount is not null
        defaultMQuestStageRewardShouldBeFound("specialRewardAmount.specified=true");

        // Get all the mQuestStageRewardList where specialRewardAmount is null
        defaultMQuestStageRewardShouldNotBeFound("specialRewardAmount.specified=false");
    }

    @Test
    @Transactional
    public void getAllMQuestStageRewardsBySpecialRewardAmountIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mQuestStageRewardRepository.saveAndFlush(mQuestStageReward);

        // Get all the mQuestStageRewardList where specialRewardAmount greater than or equals to DEFAULT_SPECIAL_REWARD_AMOUNT
        defaultMQuestStageRewardShouldBeFound("specialRewardAmount.greaterOrEqualThan=" + DEFAULT_SPECIAL_REWARD_AMOUNT);

        // Get all the mQuestStageRewardList where specialRewardAmount greater than or equals to UPDATED_SPECIAL_REWARD_AMOUNT
        defaultMQuestStageRewardShouldNotBeFound("specialRewardAmount.greaterOrEqualThan=" + UPDATED_SPECIAL_REWARD_AMOUNT);
    }

    @Test
    @Transactional
    public void getAllMQuestStageRewardsBySpecialRewardAmountIsLessThanSomething() throws Exception {
        // Initialize the database
        mQuestStageRewardRepository.saveAndFlush(mQuestStageReward);

        // Get all the mQuestStageRewardList where specialRewardAmount less than or equals to DEFAULT_SPECIAL_REWARD_AMOUNT
        defaultMQuestStageRewardShouldNotBeFound("specialRewardAmount.lessThan=" + DEFAULT_SPECIAL_REWARD_AMOUNT);

        // Get all the mQuestStageRewardList where specialRewardAmount less than or equals to UPDATED_SPECIAL_REWARD_AMOUNT
        defaultMQuestStageRewardShouldBeFound("specialRewardAmount.lessThan=" + UPDATED_SPECIAL_REWARD_AMOUNT);
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultMQuestStageRewardShouldBeFound(String filter) throws Exception {
        restMQuestStageRewardMockMvc.perform(get("/api/m-quest-stage-rewards?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(mQuestStageReward.getId().intValue())))
            .andExpect(jsonPath("$.[*].stageId").value(hasItem(DEFAULT_STAGE_ID)))
            .andExpect(jsonPath("$.[*].exp").value(hasItem(DEFAULT_EXP)))
            .andExpect(jsonPath("$.[*].coin").value(hasItem(DEFAULT_COIN)))
            .andExpect(jsonPath("$.[*].guildPoint").value(hasItem(DEFAULT_GUILD_POINT)))
            .andExpect(jsonPath("$.[*].clearRewardGroupId").value(hasItem(DEFAULT_CLEAR_REWARD_GROUP_ID)))
            .andExpect(jsonPath("$.[*].clearRewardWeightId").value(hasItem(DEFAULT_CLEAR_REWARD_WEIGHT_ID)))
            .andExpect(jsonPath("$.[*].achievementRewardGroupId").value(hasItem(DEFAULT_ACHIEVEMENT_REWARD_GROUP_ID)))
            .andExpect(jsonPath("$.[*].coopGroupId").value(hasItem(DEFAULT_COOP_GROUP_ID)))
            .andExpect(jsonPath("$.[*].specialRewardGroupId").value(hasItem(DEFAULT_SPECIAL_REWARD_GROUP_ID)))
            .andExpect(jsonPath("$.[*].specialRewardAmount").value(hasItem(DEFAULT_SPECIAL_REWARD_AMOUNT)));

        // Check, that the count call also returns 1
        restMQuestStageRewardMockMvc.perform(get("/api/m-quest-stage-rewards/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultMQuestStageRewardShouldNotBeFound(String filter) throws Exception {
        restMQuestStageRewardMockMvc.perform(get("/api/m-quest-stage-rewards?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restMQuestStageRewardMockMvc.perform(get("/api/m-quest-stage-rewards/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("0"));
    }


    @Test
    @Transactional
    public void getNonExistingMQuestStageReward() throws Exception {
        // Get the mQuestStageReward
        restMQuestStageRewardMockMvc.perform(get("/api/m-quest-stage-rewards/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateMQuestStageReward() throws Exception {
        // Initialize the database
        mQuestStageRewardRepository.saveAndFlush(mQuestStageReward);

        int databaseSizeBeforeUpdate = mQuestStageRewardRepository.findAll().size();

        // Update the mQuestStageReward
        MQuestStageReward updatedMQuestStageReward = mQuestStageRewardRepository.findById(mQuestStageReward.getId()).get();
        // Disconnect from session so that the updates on updatedMQuestStageReward are not directly saved in db
        em.detach(updatedMQuestStageReward);
        updatedMQuestStageReward
            .stageId(UPDATED_STAGE_ID)
            .exp(UPDATED_EXP)
            .coin(UPDATED_COIN)
            .guildPoint(UPDATED_GUILD_POINT)
            .clearRewardGroupId(UPDATED_CLEAR_REWARD_GROUP_ID)
            .clearRewardWeightId(UPDATED_CLEAR_REWARD_WEIGHT_ID)
            .achievementRewardGroupId(UPDATED_ACHIEVEMENT_REWARD_GROUP_ID)
            .coopGroupId(UPDATED_COOP_GROUP_ID)
            .specialRewardGroupId(UPDATED_SPECIAL_REWARD_GROUP_ID)
            .specialRewardAmount(UPDATED_SPECIAL_REWARD_AMOUNT);
        MQuestStageRewardDTO mQuestStageRewardDTO = mQuestStageRewardMapper.toDto(updatedMQuestStageReward);

        restMQuestStageRewardMockMvc.perform(put("/api/m-quest-stage-rewards")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mQuestStageRewardDTO)))
            .andExpect(status().isOk());

        // Validate the MQuestStageReward in the database
        List<MQuestStageReward> mQuestStageRewardList = mQuestStageRewardRepository.findAll();
        assertThat(mQuestStageRewardList).hasSize(databaseSizeBeforeUpdate);
        MQuestStageReward testMQuestStageReward = mQuestStageRewardList.get(mQuestStageRewardList.size() - 1);
        assertThat(testMQuestStageReward.getStageId()).isEqualTo(UPDATED_STAGE_ID);
        assertThat(testMQuestStageReward.getExp()).isEqualTo(UPDATED_EXP);
        assertThat(testMQuestStageReward.getCoin()).isEqualTo(UPDATED_COIN);
        assertThat(testMQuestStageReward.getGuildPoint()).isEqualTo(UPDATED_GUILD_POINT);
        assertThat(testMQuestStageReward.getClearRewardGroupId()).isEqualTo(UPDATED_CLEAR_REWARD_GROUP_ID);
        assertThat(testMQuestStageReward.getClearRewardWeightId()).isEqualTo(UPDATED_CLEAR_REWARD_WEIGHT_ID);
        assertThat(testMQuestStageReward.getAchievementRewardGroupId()).isEqualTo(UPDATED_ACHIEVEMENT_REWARD_GROUP_ID);
        assertThat(testMQuestStageReward.getCoopGroupId()).isEqualTo(UPDATED_COOP_GROUP_ID);
        assertThat(testMQuestStageReward.getSpecialRewardGroupId()).isEqualTo(UPDATED_SPECIAL_REWARD_GROUP_ID);
        assertThat(testMQuestStageReward.getSpecialRewardAmount()).isEqualTo(UPDATED_SPECIAL_REWARD_AMOUNT);
    }

    @Test
    @Transactional
    public void updateNonExistingMQuestStageReward() throws Exception {
        int databaseSizeBeforeUpdate = mQuestStageRewardRepository.findAll().size();

        // Create the MQuestStageReward
        MQuestStageRewardDTO mQuestStageRewardDTO = mQuestStageRewardMapper.toDto(mQuestStageReward);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restMQuestStageRewardMockMvc.perform(put("/api/m-quest-stage-rewards")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mQuestStageRewardDTO)))
            .andExpect(status().isBadRequest());

        // Validate the MQuestStageReward in the database
        List<MQuestStageReward> mQuestStageRewardList = mQuestStageRewardRepository.findAll();
        assertThat(mQuestStageRewardList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteMQuestStageReward() throws Exception {
        // Initialize the database
        mQuestStageRewardRepository.saveAndFlush(mQuestStageReward);

        int databaseSizeBeforeDelete = mQuestStageRewardRepository.findAll().size();

        // Delete the mQuestStageReward
        restMQuestStageRewardMockMvc.perform(delete("/api/m-quest-stage-rewards/{id}", mQuestStageReward.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database is empty
        List<MQuestStageReward> mQuestStageRewardList = mQuestStageRewardRepository.findAll();
        assertThat(mQuestStageRewardList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(MQuestStageReward.class);
        MQuestStageReward mQuestStageReward1 = new MQuestStageReward();
        mQuestStageReward1.setId(1L);
        MQuestStageReward mQuestStageReward2 = new MQuestStageReward();
        mQuestStageReward2.setId(mQuestStageReward1.getId());
        assertThat(mQuestStageReward1).isEqualTo(mQuestStageReward2);
        mQuestStageReward2.setId(2L);
        assertThat(mQuestStageReward1).isNotEqualTo(mQuestStageReward2);
        mQuestStageReward1.setId(null);
        assertThat(mQuestStageReward1).isNotEqualTo(mQuestStageReward2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(MQuestStageRewardDTO.class);
        MQuestStageRewardDTO mQuestStageRewardDTO1 = new MQuestStageRewardDTO();
        mQuestStageRewardDTO1.setId(1L);
        MQuestStageRewardDTO mQuestStageRewardDTO2 = new MQuestStageRewardDTO();
        assertThat(mQuestStageRewardDTO1).isNotEqualTo(mQuestStageRewardDTO2);
        mQuestStageRewardDTO2.setId(mQuestStageRewardDTO1.getId());
        assertThat(mQuestStageRewardDTO1).isEqualTo(mQuestStageRewardDTO2);
        mQuestStageRewardDTO2.setId(2L);
        assertThat(mQuestStageRewardDTO1).isNotEqualTo(mQuestStageRewardDTO2);
        mQuestStageRewardDTO1.setId(null);
        assertThat(mQuestStageRewardDTO1).isNotEqualTo(mQuestStageRewardDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(mQuestStageRewardMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(mQuestStageRewardMapper.fromId(null)).isNull();
    }
}
