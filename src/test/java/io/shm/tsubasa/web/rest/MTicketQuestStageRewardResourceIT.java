package io.shm.tsubasa.web.rest;

import io.shm.tsubasa.TsubasaApp;
import io.shm.tsubasa.domain.MTicketQuestStageReward;
import io.shm.tsubasa.repository.MTicketQuestStageRewardRepository;
import io.shm.tsubasa.service.MTicketQuestStageRewardService;
import io.shm.tsubasa.service.dto.MTicketQuestStageRewardDTO;
import io.shm.tsubasa.service.mapper.MTicketQuestStageRewardMapper;
import io.shm.tsubasa.web.rest.errors.ExceptionTranslator;
import io.shm.tsubasa.service.dto.MTicketQuestStageRewardCriteria;
import io.shm.tsubasa.service.MTicketQuestStageRewardQueryService;

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
 * Integration tests for the {@Link MTicketQuestStageRewardResource} REST controller.
 */
@SpringBootTest(classes = TsubasaApp.class)
public class MTicketQuestStageRewardResourceIT {

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
    private MTicketQuestStageRewardRepository mTicketQuestStageRewardRepository;

    @Autowired
    private MTicketQuestStageRewardMapper mTicketQuestStageRewardMapper;

    @Autowired
    private MTicketQuestStageRewardService mTicketQuestStageRewardService;

    @Autowired
    private MTicketQuestStageRewardQueryService mTicketQuestStageRewardQueryService;

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

    private MockMvc restMTicketQuestStageRewardMockMvc;

    private MTicketQuestStageReward mTicketQuestStageReward;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final MTicketQuestStageRewardResource mTicketQuestStageRewardResource = new MTicketQuestStageRewardResource(mTicketQuestStageRewardService, mTicketQuestStageRewardQueryService);
        this.restMTicketQuestStageRewardMockMvc = MockMvcBuilders.standaloneSetup(mTicketQuestStageRewardResource)
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
    public static MTicketQuestStageReward createEntity(EntityManager em) {
        MTicketQuestStageReward mTicketQuestStageReward = new MTicketQuestStageReward()
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
        return mTicketQuestStageReward;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static MTicketQuestStageReward createUpdatedEntity(EntityManager em) {
        MTicketQuestStageReward mTicketQuestStageReward = new MTicketQuestStageReward()
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
        return mTicketQuestStageReward;
    }

    @BeforeEach
    public void initTest() {
        mTicketQuestStageReward = createEntity(em);
    }

    @Test
    @Transactional
    public void createMTicketQuestStageReward() throws Exception {
        int databaseSizeBeforeCreate = mTicketQuestStageRewardRepository.findAll().size();

        // Create the MTicketQuestStageReward
        MTicketQuestStageRewardDTO mTicketQuestStageRewardDTO = mTicketQuestStageRewardMapper.toDto(mTicketQuestStageReward);
        restMTicketQuestStageRewardMockMvc.perform(post("/api/m-ticket-quest-stage-rewards")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mTicketQuestStageRewardDTO)))
            .andExpect(status().isCreated());

        // Validate the MTicketQuestStageReward in the database
        List<MTicketQuestStageReward> mTicketQuestStageRewardList = mTicketQuestStageRewardRepository.findAll();
        assertThat(mTicketQuestStageRewardList).hasSize(databaseSizeBeforeCreate + 1);
        MTicketQuestStageReward testMTicketQuestStageReward = mTicketQuestStageRewardList.get(mTicketQuestStageRewardList.size() - 1);
        assertThat(testMTicketQuestStageReward.getStageId()).isEqualTo(DEFAULT_STAGE_ID);
        assertThat(testMTicketQuestStageReward.getExp()).isEqualTo(DEFAULT_EXP);
        assertThat(testMTicketQuestStageReward.getCoin()).isEqualTo(DEFAULT_COIN);
        assertThat(testMTicketQuestStageReward.getGuildPoint()).isEqualTo(DEFAULT_GUILD_POINT);
        assertThat(testMTicketQuestStageReward.getClearRewardGroupId()).isEqualTo(DEFAULT_CLEAR_REWARD_GROUP_ID);
        assertThat(testMTicketQuestStageReward.getClearRewardWeightId()).isEqualTo(DEFAULT_CLEAR_REWARD_WEIGHT_ID);
        assertThat(testMTicketQuestStageReward.getAchievementRewardGroupId()).isEqualTo(DEFAULT_ACHIEVEMENT_REWARD_GROUP_ID);
        assertThat(testMTicketQuestStageReward.getCoopGroupId()).isEqualTo(DEFAULT_COOP_GROUP_ID);
        assertThat(testMTicketQuestStageReward.getSpecialRewardGroupId()).isEqualTo(DEFAULT_SPECIAL_REWARD_GROUP_ID);
        assertThat(testMTicketQuestStageReward.getSpecialRewardAmount()).isEqualTo(DEFAULT_SPECIAL_REWARD_AMOUNT);
        assertThat(testMTicketQuestStageReward.getGoalRewardGroupId()).isEqualTo(DEFAULT_GOAL_REWARD_GROUP_ID);
    }

    @Test
    @Transactional
    public void createMTicketQuestStageRewardWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = mTicketQuestStageRewardRepository.findAll().size();

        // Create the MTicketQuestStageReward with an existing ID
        mTicketQuestStageReward.setId(1L);
        MTicketQuestStageRewardDTO mTicketQuestStageRewardDTO = mTicketQuestStageRewardMapper.toDto(mTicketQuestStageReward);

        // An entity with an existing ID cannot be created, so this API call must fail
        restMTicketQuestStageRewardMockMvc.perform(post("/api/m-ticket-quest-stage-rewards")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mTicketQuestStageRewardDTO)))
            .andExpect(status().isBadRequest());

        // Validate the MTicketQuestStageReward in the database
        List<MTicketQuestStageReward> mTicketQuestStageRewardList = mTicketQuestStageRewardRepository.findAll();
        assertThat(mTicketQuestStageRewardList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkStageIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = mTicketQuestStageRewardRepository.findAll().size();
        // set the field null
        mTicketQuestStageReward.setStageId(null);

        // Create the MTicketQuestStageReward, which fails.
        MTicketQuestStageRewardDTO mTicketQuestStageRewardDTO = mTicketQuestStageRewardMapper.toDto(mTicketQuestStageReward);

        restMTicketQuestStageRewardMockMvc.perform(post("/api/m-ticket-quest-stage-rewards")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mTicketQuestStageRewardDTO)))
            .andExpect(status().isBadRequest());

        List<MTicketQuestStageReward> mTicketQuestStageRewardList = mTicketQuestStageRewardRepository.findAll();
        assertThat(mTicketQuestStageRewardList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkExpIsRequired() throws Exception {
        int databaseSizeBeforeTest = mTicketQuestStageRewardRepository.findAll().size();
        // set the field null
        mTicketQuestStageReward.setExp(null);

        // Create the MTicketQuestStageReward, which fails.
        MTicketQuestStageRewardDTO mTicketQuestStageRewardDTO = mTicketQuestStageRewardMapper.toDto(mTicketQuestStageReward);

        restMTicketQuestStageRewardMockMvc.perform(post("/api/m-ticket-quest-stage-rewards")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mTicketQuestStageRewardDTO)))
            .andExpect(status().isBadRequest());

        List<MTicketQuestStageReward> mTicketQuestStageRewardList = mTicketQuestStageRewardRepository.findAll();
        assertThat(mTicketQuestStageRewardList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkCoinIsRequired() throws Exception {
        int databaseSizeBeforeTest = mTicketQuestStageRewardRepository.findAll().size();
        // set the field null
        mTicketQuestStageReward.setCoin(null);

        // Create the MTicketQuestStageReward, which fails.
        MTicketQuestStageRewardDTO mTicketQuestStageRewardDTO = mTicketQuestStageRewardMapper.toDto(mTicketQuestStageReward);

        restMTicketQuestStageRewardMockMvc.perform(post("/api/m-ticket-quest-stage-rewards")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mTicketQuestStageRewardDTO)))
            .andExpect(status().isBadRequest());

        List<MTicketQuestStageReward> mTicketQuestStageRewardList = mTicketQuestStageRewardRepository.findAll();
        assertThat(mTicketQuestStageRewardList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkGuildPointIsRequired() throws Exception {
        int databaseSizeBeforeTest = mTicketQuestStageRewardRepository.findAll().size();
        // set the field null
        mTicketQuestStageReward.setGuildPoint(null);

        // Create the MTicketQuestStageReward, which fails.
        MTicketQuestStageRewardDTO mTicketQuestStageRewardDTO = mTicketQuestStageRewardMapper.toDto(mTicketQuestStageReward);

        restMTicketQuestStageRewardMockMvc.perform(post("/api/m-ticket-quest-stage-rewards")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mTicketQuestStageRewardDTO)))
            .andExpect(status().isBadRequest());

        List<MTicketQuestStageReward> mTicketQuestStageRewardList = mTicketQuestStageRewardRepository.findAll();
        assertThat(mTicketQuestStageRewardList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkClearRewardGroupIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = mTicketQuestStageRewardRepository.findAll().size();
        // set the field null
        mTicketQuestStageReward.setClearRewardGroupId(null);

        // Create the MTicketQuestStageReward, which fails.
        MTicketQuestStageRewardDTO mTicketQuestStageRewardDTO = mTicketQuestStageRewardMapper.toDto(mTicketQuestStageReward);

        restMTicketQuestStageRewardMockMvc.perform(post("/api/m-ticket-quest-stage-rewards")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mTicketQuestStageRewardDTO)))
            .andExpect(status().isBadRequest());

        List<MTicketQuestStageReward> mTicketQuestStageRewardList = mTicketQuestStageRewardRepository.findAll();
        assertThat(mTicketQuestStageRewardList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkClearRewardWeightIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = mTicketQuestStageRewardRepository.findAll().size();
        // set the field null
        mTicketQuestStageReward.setClearRewardWeightId(null);

        // Create the MTicketQuestStageReward, which fails.
        MTicketQuestStageRewardDTO mTicketQuestStageRewardDTO = mTicketQuestStageRewardMapper.toDto(mTicketQuestStageReward);

        restMTicketQuestStageRewardMockMvc.perform(post("/api/m-ticket-quest-stage-rewards")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mTicketQuestStageRewardDTO)))
            .andExpect(status().isBadRequest());

        List<MTicketQuestStageReward> mTicketQuestStageRewardList = mTicketQuestStageRewardRepository.findAll();
        assertThat(mTicketQuestStageRewardList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkAchievementRewardGroupIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = mTicketQuestStageRewardRepository.findAll().size();
        // set the field null
        mTicketQuestStageReward.setAchievementRewardGroupId(null);

        // Create the MTicketQuestStageReward, which fails.
        MTicketQuestStageRewardDTO mTicketQuestStageRewardDTO = mTicketQuestStageRewardMapper.toDto(mTicketQuestStageReward);

        restMTicketQuestStageRewardMockMvc.perform(post("/api/m-ticket-quest-stage-rewards")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mTicketQuestStageRewardDTO)))
            .andExpect(status().isBadRequest());

        List<MTicketQuestStageReward> mTicketQuestStageRewardList = mTicketQuestStageRewardRepository.findAll();
        assertThat(mTicketQuestStageRewardList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkCoopGroupIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = mTicketQuestStageRewardRepository.findAll().size();
        // set the field null
        mTicketQuestStageReward.setCoopGroupId(null);

        // Create the MTicketQuestStageReward, which fails.
        MTicketQuestStageRewardDTO mTicketQuestStageRewardDTO = mTicketQuestStageRewardMapper.toDto(mTicketQuestStageReward);

        restMTicketQuestStageRewardMockMvc.perform(post("/api/m-ticket-quest-stage-rewards")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mTicketQuestStageRewardDTO)))
            .andExpect(status().isBadRequest());

        List<MTicketQuestStageReward> mTicketQuestStageRewardList = mTicketQuestStageRewardRepository.findAll();
        assertThat(mTicketQuestStageRewardList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkSpecialRewardAmountIsRequired() throws Exception {
        int databaseSizeBeforeTest = mTicketQuestStageRewardRepository.findAll().size();
        // set the field null
        mTicketQuestStageReward.setSpecialRewardAmount(null);

        // Create the MTicketQuestStageReward, which fails.
        MTicketQuestStageRewardDTO mTicketQuestStageRewardDTO = mTicketQuestStageRewardMapper.toDto(mTicketQuestStageReward);

        restMTicketQuestStageRewardMockMvc.perform(post("/api/m-ticket-quest-stage-rewards")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mTicketQuestStageRewardDTO)))
            .andExpect(status().isBadRequest());

        List<MTicketQuestStageReward> mTicketQuestStageRewardList = mTicketQuestStageRewardRepository.findAll();
        assertThat(mTicketQuestStageRewardList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllMTicketQuestStageRewards() throws Exception {
        // Initialize the database
        mTicketQuestStageRewardRepository.saveAndFlush(mTicketQuestStageReward);

        // Get all the mTicketQuestStageRewardList
        restMTicketQuestStageRewardMockMvc.perform(get("/api/m-ticket-quest-stage-rewards?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(mTicketQuestStageReward.getId().intValue())))
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
    public void getMTicketQuestStageReward() throws Exception {
        // Initialize the database
        mTicketQuestStageRewardRepository.saveAndFlush(mTicketQuestStageReward);

        // Get the mTicketQuestStageReward
        restMTicketQuestStageRewardMockMvc.perform(get("/api/m-ticket-quest-stage-rewards/{id}", mTicketQuestStageReward.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(mTicketQuestStageReward.getId().intValue()))
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
    public void getAllMTicketQuestStageRewardsByStageIdIsEqualToSomething() throws Exception {
        // Initialize the database
        mTicketQuestStageRewardRepository.saveAndFlush(mTicketQuestStageReward);

        // Get all the mTicketQuestStageRewardList where stageId equals to DEFAULT_STAGE_ID
        defaultMTicketQuestStageRewardShouldBeFound("stageId.equals=" + DEFAULT_STAGE_ID);

        // Get all the mTicketQuestStageRewardList where stageId equals to UPDATED_STAGE_ID
        defaultMTicketQuestStageRewardShouldNotBeFound("stageId.equals=" + UPDATED_STAGE_ID);
    }

    @Test
    @Transactional
    public void getAllMTicketQuestStageRewardsByStageIdIsInShouldWork() throws Exception {
        // Initialize the database
        mTicketQuestStageRewardRepository.saveAndFlush(mTicketQuestStageReward);

        // Get all the mTicketQuestStageRewardList where stageId in DEFAULT_STAGE_ID or UPDATED_STAGE_ID
        defaultMTicketQuestStageRewardShouldBeFound("stageId.in=" + DEFAULT_STAGE_ID + "," + UPDATED_STAGE_ID);

        // Get all the mTicketQuestStageRewardList where stageId equals to UPDATED_STAGE_ID
        defaultMTicketQuestStageRewardShouldNotBeFound("stageId.in=" + UPDATED_STAGE_ID);
    }

    @Test
    @Transactional
    public void getAllMTicketQuestStageRewardsByStageIdIsNullOrNotNull() throws Exception {
        // Initialize the database
        mTicketQuestStageRewardRepository.saveAndFlush(mTicketQuestStageReward);

        // Get all the mTicketQuestStageRewardList where stageId is not null
        defaultMTicketQuestStageRewardShouldBeFound("stageId.specified=true");

        // Get all the mTicketQuestStageRewardList where stageId is null
        defaultMTicketQuestStageRewardShouldNotBeFound("stageId.specified=false");
    }

    @Test
    @Transactional
    public void getAllMTicketQuestStageRewardsByStageIdIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mTicketQuestStageRewardRepository.saveAndFlush(mTicketQuestStageReward);

        // Get all the mTicketQuestStageRewardList where stageId greater than or equals to DEFAULT_STAGE_ID
        defaultMTicketQuestStageRewardShouldBeFound("stageId.greaterOrEqualThan=" + DEFAULT_STAGE_ID);

        // Get all the mTicketQuestStageRewardList where stageId greater than or equals to UPDATED_STAGE_ID
        defaultMTicketQuestStageRewardShouldNotBeFound("stageId.greaterOrEqualThan=" + UPDATED_STAGE_ID);
    }

    @Test
    @Transactional
    public void getAllMTicketQuestStageRewardsByStageIdIsLessThanSomething() throws Exception {
        // Initialize the database
        mTicketQuestStageRewardRepository.saveAndFlush(mTicketQuestStageReward);

        // Get all the mTicketQuestStageRewardList where stageId less than or equals to DEFAULT_STAGE_ID
        defaultMTicketQuestStageRewardShouldNotBeFound("stageId.lessThan=" + DEFAULT_STAGE_ID);

        // Get all the mTicketQuestStageRewardList where stageId less than or equals to UPDATED_STAGE_ID
        defaultMTicketQuestStageRewardShouldBeFound("stageId.lessThan=" + UPDATED_STAGE_ID);
    }


    @Test
    @Transactional
    public void getAllMTicketQuestStageRewardsByExpIsEqualToSomething() throws Exception {
        // Initialize the database
        mTicketQuestStageRewardRepository.saveAndFlush(mTicketQuestStageReward);

        // Get all the mTicketQuestStageRewardList where exp equals to DEFAULT_EXP
        defaultMTicketQuestStageRewardShouldBeFound("exp.equals=" + DEFAULT_EXP);

        // Get all the mTicketQuestStageRewardList where exp equals to UPDATED_EXP
        defaultMTicketQuestStageRewardShouldNotBeFound("exp.equals=" + UPDATED_EXP);
    }

    @Test
    @Transactional
    public void getAllMTicketQuestStageRewardsByExpIsInShouldWork() throws Exception {
        // Initialize the database
        mTicketQuestStageRewardRepository.saveAndFlush(mTicketQuestStageReward);

        // Get all the mTicketQuestStageRewardList where exp in DEFAULT_EXP or UPDATED_EXP
        defaultMTicketQuestStageRewardShouldBeFound("exp.in=" + DEFAULT_EXP + "," + UPDATED_EXP);

        // Get all the mTicketQuestStageRewardList where exp equals to UPDATED_EXP
        defaultMTicketQuestStageRewardShouldNotBeFound("exp.in=" + UPDATED_EXP);
    }

    @Test
    @Transactional
    public void getAllMTicketQuestStageRewardsByExpIsNullOrNotNull() throws Exception {
        // Initialize the database
        mTicketQuestStageRewardRepository.saveAndFlush(mTicketQuestStageReward);

        // Get all the mTicketQuestStageRewardList where exp is not null
        defaultMTicketQuestStageRewardShouldBeFound("exp.specified=true");

        // Get all the mTicketQuestStageRewardList where exp is null
        defaultMTicketQuestStageRewardShouldNotBeFound("exp.specified=false");
    }

    @Test
    @Transactional
    public void getAllMTicketQuestStageRewardsByExpIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mTicketQuestStageRewardRepository.saveAndFlush(mTicketQuestStageReward);

        // Get all the mTicketQuestStageRewardList where exp greater than or equals to DEFAULT_EXP
        defaultMTicketQuestStageRewardShouldBeFound("exp.greaterOrEqualThan=" + DEFAULT_EXP);

        // Get all the mTicketQuestStageRewardList where exp greater than or equals to UPDATED_EXP
        defaultMTicketQuestStageRewardShouldNotBeFound("exp.greaterOrEqualThan=" + UPDATED_EXP);
    }

    @Test
    @Transactional
    public void getAllMTicketQuestStageRewardsByExpIsLessThanSomething() throws Exception {
        // Initialize the database
        mTicketQuestStageRewardRepository.saveAndFlush(mTicketQuestStageReward);

        // Get all the mTicketQuestStageRewardList where exp less than or equals to DEFAULT_EXP
        defaultMTicketQuestStageRewardShouldNotBeFound("exp.lessThan=" + DEFAULT_EXP);

        // Get all the mTicketQuestStageRewardList where exp less than or equals to UPDATED_EXP
        defaultMTicketQuestStageRewardShouldBeFound("exp.lessThan=" + UPDATED_EXP);
    }


    @Test
    @Transactional
    public void getAllMTicketQuestStageRewardsByCoinIsEqualToSomething() throws Exception {
        // Initialize the database
        mTicketQuestStageRewardRepository.saveAndFlush(mTicketQuestStageReward);

        // Get all the mTicketQuestStageRewardList where coin equals to DEFAULT_COIN
        defaultMTicketQuestStageRewardShouldBeFound("coin.equals=" + DEFAULT_COIN);

        // Get all the mTicketQuestStageRewardList where coin equals to UPDATED_COIN
        defaultMTicketQuestStageRewardShouldNotBeFound("coin.equals=" + UPDATED_COIN);
    }

    @Test
    @Transactional
    public void getAllMTicketQuestStageRewardsByCoinIsInShouldWork() throws Exception {
        // Initialize the database
        mTicketQuestStageRewardRepository.saveAndFlush(mTicketQuestStageReward);

        // Get all the mTicketQuestStageRewardList where coin in DEFAULT_COIN or UPDATED_COIN
        defaultMTicketQuestStageRewardShouldBeFound("coin.in=" + DEFAULT_COIN + "," + UPDATED_COIN);

        // Get all the mTicketQuestStageRewardList where coin equals to UPDATED_COIN
        defaultMTicketQuestStageRewardShouldNotBeFound("coin.in=" + UPDATED_COIN);
    }

    @Test
    @Transactional
    public void getAllMTicketQuestStageRewardsByCoinIsNullOrNotNull() throws Exception {
        // Initialize the database
        mTicketQuestStageRewardRepository.saveAndFlush(mTicketQuestStageReward);

        // Get all the mTicketQuestStageRewardList where coin is not null
        defaultMTicketQuestStageRewardShouldBeFound("coin.specified=true");

        // Get all the mTicketQuestStageRewardList where coin is null
        defaultMTicketQuestStageRewardShouldNotBeFound("coin.specified=false");
    }

    @Test
    @Transactional
    public void getAllMTicketQuestStageRewardsByCoinIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mTicketQuestStageRewardRepository.saveAndFlush(mTicketQuestStageReward);

        // Get all the mTicketQuestStageRewardList where coin greater than or equals to DEFAULT_COIN
        defaultMTicketQuestStageRewardShouldBeFound("coin.greaterOrEqualThan=" + DEFAULT_COIN);

        // Get all the mTicketQuestStageRewardList where coin greater than or equals to UPDATED_COIN
        defaultMTicketQuestStageRewardShouldNotBeFound("coin.greaterOrEqualThan=" + UPDATED_COIN);
    }

    @Test
    @Transactional
    public void getAllMTicketQuestStageRewardsByCoinIsLessThanSomething() throws Exception {
        // Initialize the database
        mTicketQuestStageRewardRepository.saveAndFlush(mTicketQuestStageReward);

        // Get all the mTicketQuestStageRewardList where coin less than or equals to DEFAULT_COIN
        defaultMTicketQuestStageRewardShouldNotBeFound("coin.lessThan=" + DEFAULT_COIN);

        // Get all the mTicketQuestStageRewardList where coin less than or equals to UPDATED_COIN
        defaultMTicketQuestStageRewardShouldBeFound("coin.lessThan=" + UPDATED_COIN);
    }


    @Test
    @Transactional
    public void getAllMTicketQuestStageRewardsByGuildPointIsEqualToSomething() throws Exception {
        // Initialize the database
        mTicketQuestStageRewardRepository.saveAndFlush(mTicketQuestStageReward);

        // Get all the mTicketQuestStageRewardList where guildPoint equals to DEFAULT_GUILD_POINT
        defaultMTicketQuestStageRewardShouldBeFound("guildPoint.equals=" + DEFAULT_GUILD_POINT);

        // Get all the mTicketQuestStageRewardList where guildPoint equals to UPDATED_GUILD_POINT
        defaultMTicketQuestStageRewardShouldNotBeFound("guildPoint.equals=" + UPDATED_GUILD_POINT);
    }

    @Test
    @Transactional
    public void getAllMTicketQuestStageRewardsByGuildPointIsInShouldWork() throws Exception {
        // Initialize the database
        mTicketQuestStageRewardRepository.saveAndFlush(mTicketQuestStageReward);

        // Get all the mTicketQuestStageRewardList where guildPoint in DEFAULT_GUILD_POINT or UPDATED_GUILD_POINT
        defaultMTicketQuestStageRewardShouldBeFound("guildPoint.in=" + DEFAULT_GUILD_POINT + "," + UPDATED_GUILD_POINT);

        // Get all the mTicketQuestStageRewardList where guildPoint equals to UPDATED_GUILD_POINT
        defaultMTicketQuestStageRewardShouldNotBeFound("guildPoint.in=" + UPDATED_GUILD_POINT);
    }

    @Test
    @Transactional
    public void getAllMTicketQuestStageRewardsByGuildPointIsNullOrNotNull() throws Exception {
        // Initialize the database
        mTicketQuestStageRewardRepository.saveAndFlush(mTicketQuestStageReward);

        // Get all the mTicketQuestStageRewardList where guildPoint is not null
        defaultMTicketQuestStageRewardShouldBeFound("guildPoint.specified=true");

        // Get all the mTicketQuestStageRewardList where guildPoint is null
        defaultMTicketQuestStageRewardShouldNotBeFound("guildPoint.specified=false");
    }

    @Test
    @Transactional
    public void getAllMTicketQuestStageRewardsByGuildPointIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mTicketQuestStageRewardRepository.saveAndFlush(mTicketQuestStageReward);

        // Get all the mTicketQuestStageRewardList where guildPoint greater than or equals to DEFAULT_GUILD_POINT
        defaultMTicketQuestStageRewardShouldBeFound("guildPoint.greaterOrEqualThan=" + DEFAULT_GUILD_POINT);

        // Get all the mTicketQuestStageRewardList where guildPoint greater than or equals to UPDATED_GUILD_POINT
        defaultMTicketQuestStageRewardShouldNotBeFound("guildPoint.greaterOrEqualThan=" + UPDATED_GUILD_POINT);
    }

    @Test
    @Transactional
    public void getAllMTicketQuestStageRewardsByGuildPointIsLessThanSomething() throws Exception {
        // Initialize the database
        mTicketQuestStageRewardRepository.saveAndFlush(mTicketQuestStageReward);

        // Get all the mTicketQuestStageRewardList where guildPoint less than or equals to DEFAULT_GUILD_POINT
        defaultMTicketQuestStageRewardShouldNotBeFound("guildPoint.lessThan=" + DEFAULT_GUILD_POINT);

        // Get all the mTicketQuestStageRewardList where guildPoint less than or equals to UPDATED_GUILD_POINT
        defaultMTicketQuestStageRewardShouldBeFound("guildPoint.lessThan=" + UPDATED_GUILD_POINT);
    }


    @Test
    @Transactional
    public void getAllMTicketQuestStageRewardsByClearRewardGroupIdIsEqualToSomething() throws Exception {
        // Initialize the database
        mTicketQuestStageRewardRepository.saveAndFlush(mTicketQuestStageReward);

        // Get all the mTicketQuestStageRewardList where clearRewardGroupId equals to DEFAULT_CLEAR_REWARD_GROUP_ID
        defaultMTicketQuestStageRewardShouldBeFound("clearRewardGroupId.equals=" + DEFAULT_CLEAR_REWARD_GROUP_ID);

        // Get all the mTicketQuestStageRewardList where clearRewardGroupId equals to UPDATED_CLEAR_REWARD_GROUP_ID
        defaultMTicketQuestStageRewardShouldNotBeFound("clearRewardGroupId.equals=" + UPDATED_CLEAR_REWARD_GROUP_ID);
    }

    @Test
    @Transactional
    public void getAllMTicketQuestStageRewardsByClearRewardGroupIdIsInShouldWork() throws Exception {
        // Initialize the database
        mTicketQuestStageRewardRepository.saveAndFlush(mTicketQuestStageReward);

        // Get all the mTicketQuestStageRewardList where clearRewardGroupId in DEFAULT_CLEAR_REWARD_GROUP_ID or UPDATED_CLEAR_REWARD_GROUP_ID
        defaultMTicketQuestStageRewardShouldBeFound("clearRewardGroupId.in=" + DEFAULT_CLEAR_REWARD_GROUP_ID + "," + UPDATED_CLEAR_REWARD_GROUP_ID);

        // Get all the mTicketQuestStageRewardList where clearRewardGroupId equals to UPDATED_CLEAR_REWARD_GROUP_ID
        defaultMTicketQuestStageRewardShouldNotBeFound("clearRewardGroupId.in=" + UPDATED_CLEAR_REWARD_GROUP_ID);
    }

    @Test
    @Transactional
    public void getAllMTicketQuestStageRewardsByClearRewardGroupIdIsNullOrNotNull() throws Exception {
        // Initialize the database
        mTicketQuestStageRewardRepository.saveAndFlush(mTicketQuestStageReward);

        // Get all the mTicketQuestStageRewardList where clearRewardGroupId is not null
        defaultMTicketQuestStageRewardShouldBeFound("clearRewardGroupId.specified=true");

        // Get all the mTicketQuestStageRewardList where clearRewardGroupId is null
        defaultMTicketQuestStageRewardShouldNotBeFound("clearRewardGroupId.specified=false");
    }

    @Test
    @Transactional
    public void getAllMTicketQuestStageRewardsByClearRewardGroupIdIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mTicketQuestStageRewardRepository.saveAndFlush(mTicketQuestStageReward);

        // Get all the mTicketQuestStageRewardList where clearRewardGroupId greater than or equals to DEFAULT_CLEAR_REWARD_GROUP_ID
        defaultMTicketQuestStageRewardShouldBeFound("clearRewardGroupId.greaterOrEqualThan=" + DEFAULT_CLEAR_REWARD_GROUP_ID);

        // Get all the mTicketQuestStageRewardList where clearRewardGroupId greater than or equals to UPDATED_CLEAR_REWARD_GROUP_ID
        defaultMTicketQuestStageRewardShouldNotBeFound("clearRewardGroupId.greaterOrEqualThan=" + UPDATED_CLEAR_REWARD_GROUP_ID);
    }

    @Test
    @Transactional
    public void getAllMTicketQuestStageRewardsByClearRewardGroupIdIsLessThanSomething() throws Exception {
        // Initialize the database
        mTicketQuestStageRewardRepository.saveAndFlush(mTicketQuestStageReward);

        // Get all the mTicketQuestStageRewardList where clearRewardGroupId less than or equals to DEFAULT_CLEAR_REWARD_GROUP_ID
        defaultMTicketQuestStageRewardShouldNotBeFound("clearRewardGroupId.lessThan=" + DEFAULT_CLEAR_REWARD_GROUP_ID);

        // Get all the mTicketQuestStageRewardList where clearRewardGroupId less than or equals to UPDATED_CLEAR_REWARD_GROUP_ID
        defaultMTicketQuestStageRewardShouldBeFound("clearRewardGroupId.lessThan=" + UPDATED_CLEAR_REWARD_GROUP_ID);
    }


    @Test
    @Transactional
    public void getAllMTicketQuestStageRewardsByClearRewardWeightIdIsEqualToSomething() throws Exception {
        // Initialize the database
        mTicketQuestStageRewardRepository.saveAndFlush(mTicketQuestStageReward);

        // Get all the mTicketQuestStageRewardList where clearRewardWeightId equals to DEFAULT_CLEAR_REWARD_WEIGHT_ID
        defaultMTicketQuestStageRewardShouldBeFound("clearRewardWeightId.equals=" + DEFAULT_CLEAR_REWARD_WEIGHT_ID);

        // Get all the mTicketQuestStageRewardList where clearRewardWeightId equals to UPDATED_CLEAR_REWARD_WEIGHT_ID
        defaultMTicketQuestStageRewardShouldNotBeFound("clearRewardWeightId.equals=" + UPDATED_CLEAR_REWARD_WEIGHT_ID);
    }

    @Test
    @Transactional
    public void getAllMTicketQuestStageRewardsByClearRewardWeightIdIsInShouldWork() throws Exception {
        // Initialize the database
        mTicketQuestStageRewardRepository.saveAndFlush(mTicketQuestStageReward);

        // Get all the mTicketQuestStageRewardList where clearRewardWeightId in DEFAULT_CLEAR_REWARD_WEIGHT_ID or UPDATED_CLEAR_REWARD_WEIGHT_ID
        defaultMTicketQuestStageRewardShouldBeFound("clearRewardWeightId.in=" + DEFAULT_CLEAR_REWARD_WEIGHT_ID + "," + UPDATED_CLEAR_REWARD_WEIGHT_ID);

        // Get all the mTicketQuestStageRewardList where clearRewardWeightId equals to UPDATED_CLEAR_REWARD_WEIGHT_ID
        defaultMTicketQuestStageRewardShouldNotBeFound("clearRewardWeightId.in=" + UPDATED_CLEAR_REWARD_WEIGHT_ID);
    }

    @Test
    @Transactional
    public void getAllMTicketQuestStageRewardsByClearRewardWeightIdIsNullOrNotNull() throws Exception {
        // Initialize the database
        mTicketQuestStageRewardRepository.saveAndFlush(mTicketQuestStageReward);

        // Get all the mTicketQuestStageRewardList where clearRewardWeightId is not null
        defaultMTicketQuestStageRewardShouldBeFound("clearRewardWeightId.specified=true");

        // Get all the mTicketQuestStageRewardList where clearRewardWeightId is null
        defaultMTicketQuestStageRewardShouldNotBeFound("clearRewardWeightId.specified=false");
    }

    @Test
    @Transactional
    public void getAllMTicketQuestStageRewardsByClearRewardWeightIdIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mTicketQuestStageRewardRepository.saveAndFlush(mTicketQuestStageReward);

        // Get all the mTicketQuestStageRewardList where clearRewardWeightId greater than or equals to DEFAULT_CLEAR_REWARD_WEIGHT_ID
        defaultMTicketQuestStageRewardShouldBeFound("clearRewardWeightId.greaterOrEqualThan=" + DEFAULT_CLEAR_REWARD_WEIGHT_ID);

        // Get all the mTicketQuestStageRewardList where clearRewardWeightId greater than or equals to UPDATED_CLEAR_REWARD_WEIGHT_ID
        defaultMTicketQuestStageRewardShouldNotBeFound("clearRewardWeightId.greaterOrEqualThan=" + UPDATED_CLEAR_REWARD_WEIGHT_ID);
    }

    @Test
    @Transactional
    public void getAllMTicketQuestStageRewardsByClearRewardWeightIdIsLessThanSomething() throws Exception {
        // Initialize the database
        mTicketQuestStageRewardRepository.saveAndFlush(mTicketQuestStageReward);

        // Get all the mTicketQuestStageRewardList where clearRewardWeightId less than or equals to DEFAULT_CLEAR_REWARD_WEIGHT_ID
        defaultMTicketQuestStageRewardShouldNotBeFound("clearRewardWeightId.lessThan=" + DEFAULT_CLEAR_REWARD_WEIGHT_ID);

        // Get all the mTicketQuestStageRewardList where clearRewardWeightId less than or equals to UPDATED_CLEAR_REWARD_WEIGHT_ID
        defaultMTicketQuestStageRewardShouldBeFound("clearRewardWeightId.lessThan=" + UPDATED_CLEAR_REWARD_WEIGHT_ID);
    }


    @Test
    @Transactional
    public void getAllMTicketQuestStageRewardsByAchievementRewardGroupIdIsEqualToSomething() throws Exception {
        // Initialize the database
        mTicketQuestStageRewardRepository.saveAndFlush(mTicketQuestStageReward);

        // Get all the mTicketQuestStageRewardList where achievementRewardGroupId equals to DEFAULT_ACHIEVEMENT_REWARD_GROUP_ID
        defaultMTicketQuestStageRewardShouldBeFound("achievementRewardGroupId.equals=" + DEFAULT_ACHIEVEMENT_REWARD_GROUP_ID);

        // Get all the mTicketQuestStageRewardList where achievementRewardGroupId equals to UPDATED_ACHIEVEMENT_REWARD_GROUP_ID
        defaultMTicketQuestStageRewardShouldNotBeFound("achievementRewardGroupId.equals=" + UPDATED_ACHIEVEMENT_REWARD_GROUP_ID);
    }

    @Test
    @Transactional
    public void getAllMTicketQuestStageRewardsByAchievementRewardGroupIdIsInShouldWork() throws Exception {
        // Initialize the database
        mTicketQuestStageRewardRepository.saveAndFlush(mTicketQuestStageReward);

        // Get all the mTicketQuestStageRewardList where achievementRewardGroupId in DEFAULT_ACHIEVEMENT_REWARD_GROUP_ID or UPDATED_ACHIEVEMENT_REWARD_GROUP_ID
        defaultMTicketQuestStageRewardShouldBeFound("achievementRewardGroupId.in=" + DEFAULT_ACHIEVEMENT_REWARD_GROUP_ID + "," + UPDATED_ACHIEVEMENT_REWARD_GROUP_ID);

        // Get all the mTicketQuestStageRewardList where achievementRewardGroupId equals to UPDATED_ACHIEVEMENT_REWARD_GROUP_ID
        defaultMTicketQuestStageRewardShouldNotBeFound("achievementRewardGroupId.in=" + UPDATED_ACHIEVEMENT_REWARD_GROUP_ID);
    }

    @Test
    @Transactional
    public void getAllMTicketQuestStageRewardsByAchievementRewardGroupIdIsNullOrNotNull() throws Exception {
        // Initialize the database
        mTicketQuestStageRewardRepository.saveAndFlush(mTicketQuestStageReward);

        // Get all the mTicketQuestStageRewardList where achievementRewardGroupId is not null
        defaultMTicketQuestStageRewardShouldBeFound("achievementRewardGroupId.specified=true");

        // Get all the mTicketQuestStageRewardList where achievementRewardGroupId is null
        defaultMTicketQuestStageRewardShouldNotBeFound("achievementRewardGroupId.specified=false");
    }

    @Test
    @Transactional
    public void getAllMTicketQuestStageRewardsByAchievementRewardGroupIdIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mTicketQuestStageRewardRepository.saveAndFlush(mTicketQuestStageReward);

        // Get all the mTicketQuestStageRewardList where achievementRewardGroupId greater than or equals to DEFAULT_ACHIEVEMENT_REWARD_GROUP_ID
        defaultMTicketQuestStageRewardShouldBeFound("achievementRewardGroupId.greaterOrEqualThan=" + DEFAULT_ACHIEVEMENT_REWARD_GROUP_ID);

        // Get all the mTicketQuestStageRewardList where achievementRewardGroupId greater than or equals to UPDATED_ACHIEVEMENT_REWARD_GROUP_ID
        defaultMTicketQuestStageRewardShouldNotBeFound("achievementRewardGroupId.greaterOrEqualThan=" + UPDATED_ACHIEVEMENT_REWARD_GROUP_ID);
    }

    @Test
    @Transactional
    public void getAllMTicketQuestStageRewardsByAchievementRewardGroupIdIsLessThanSomething() throws Exception {
        // Initialize the database
        mTicketQuestStageRewardRepository.saveAndFlush(mTicketQuestStageReward);

        // Get all the mTicketQuestStageRewardList where achievementRewardGroupId less than or equals to DEFAULT_ACHIEVEMENT_REWARD_GROUP_ID
        defaultMTicketQuestStageRewardShouldNotBeFound("achievementRewardGroupId.lessThan=" + DEFAULT_ACHIEVEMENT_REWARD_GROUP_ID);

        // Get all the mTicketQuestStageRewardList where achievementRewardGroupId less than or equals to UPDATED_ACHIEVEMENT_REWARD_GROUP_ID
        defaultMTicketQuestStageRewardShouldBeFound("achievementRewardGroupId.lessThan=" + UPDATED_ACHIEVEMENT_REWARD_GROUP_ID);
    }


    @Test
    @Transactional
    public void getAllMTicketQuestStageRewardsByCoopGroupIdIsEqualToSomething() throws Exception {
        // Initialize the database
        mTicketQuestStageRewardRepository.saveAndFlush(mTicketQuestStageReward);

        // Get all the mTicketQuestStageRewardList where coopGroupId equals to DEFAULT_COOP_GROUP_ID
        defaultMTicketQuestStageRewardShouldBeFound("coopGroupId.equals=" + DEFAULT_COOP_GROUP_ID);

        // Get all the mTicketQuestStageRewardList where coopGroupId equals to UPDATED_COOP_GROUP_ID
        defaultMTicketQuestStageRewardShouldNotBeFound("coopGroupId.equals=" + UPDATED_COOP_GROUP_ID);
    }

    @Test
    @Transactional
    public void getAllMTicketQuestStageRewardsByCoopGroupIdIsInShouldWork() throws Exception {
        // Initialize the database
        mTicketQuestStageRewardRepository.saveAndFlush(mTicketQuestStageReward);

        // Get all the mTicketQuestStageRewardList where coopGroupId in DEFAULT_COOP_GROUP_ID or UPDATED_COOP_GROUP_ID
        defaultMTicketQuestStageRewardShouldBeFound("coopGroupId.in=" + DEFAULT_COOP_GROUP_ID + "," + UPDATED_COOP_GROUP_ID);

        // Get all the mTicketQuestStageRewardList where coopGroupId equals to UPDATED_COOP_GROUP_ID
        defaultMTicketQuestStageRewardShouldNotBeFound("coopGroupId.in=" + UPDATED_COOP_GROUP_ID);
    }

    @Test
    @Transactional
    public void getAllMTicketQuestStageRewardsByCoopGroupIdIsNullOrNotNull() throws Exception {
        // Initialize the database
        mTicketQuestStageRewardRepository.saveAndFlush(mTicketQuestStageReward);

        // Get all the mTicketQuestStageRewardList where coopGroupId is not null
        defaultMTicketQuestStageRewardShouldBeFound("coopGroupId.specified=true");

        // Get all the mTicketQuestStageRewardList where coopGroupId is null
        defaultMTicketQuestStageRewardShouldNotBeFound("coopGroupId.specified=false");
    }

    @Test
    @Transactional
    public void getAllMTicketQuestStageRewardsByCoopGroupIdIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mTicketQuestStageRewardRepository.saveAndFlush(mTicketQuestStageReward);

        // Get all the mTicketQuestStageRewardList where coopGroupId greater than or equals to DEFAULT_COOP_GROUP_ID
        defaultMTicketQuestStageRewardShouldBeFound("coopGroupId.greaterOrEqualThan=" + DEFAULT_COOP_GROUP_ID);

        // Get all the mTicketQuestStageRewardList where coopGroupId greater than or equals to UPDATED_COOP_GROUP_ID
        defaultMTicketQuestStageRewardShouldNotBeFound("coopGroupId.greaterOrEqualThan=" + UPDATED_COOP_GROUP_ID);
    }

    @Test
    @Transactional
    public void getAllMTicketQuestStageRewardsByCoopGroupIdIsLessThanSomething() throws Exception {
        // Initialize the database
        mTicketQuestStageRewardRepository.saveAndFlush(mTicketQuestStageReward);

        // Get all the mTicketQuestStageRewardList where coopGroupId less than or equals to DEFAULT_COOP_GROUP_ID
        defaultMTicketQuestStageRewardShouldNotBeFound("coopGroupId.lessThan=" + DEFAULT_COOP_GROUP_ID);

        // Get all the mTicketQuestStageRewardList where coopGroupId less than or equals to UPDATED_COOP_GROUP_ID
        defaultMTicketQuestStageRewardShouldBeFound("coopGroupId.lessThan=" + UPDATED_COOP_GROUP_ID);
    }


    @Test
    @Transactional
    public void getAllMTicketQuestStageRewardsBySpecialRewardGroupIdIsEqualToSomething() throws Exception {
        // Initialize the database
        mTicketQuestStageRewardRepository.saveAndFlush(mTicketQuestStageReward);

        // Get all the mTicketQuestStageRewardList where specialRewardGroupId equals to DEFAULT_SPECIAL_REWARD_GROUP_ID
        defaultMTicketQuestStageRewardShouldBeFound("specialRewardGroupId.equals=" + DEFAULT_SPECIAL_REWARD_GROUP_ID);

        // Get all the mTicketQuestStageRewardList where specialRewardGroupId equals to UPDATED_SPECIAL_REWARD_GROUP_ID
        defaultMTicketQuestStageRewardShouldNotBeFound("specialRewardGroupId.equals=" + UPDATED_SPECIAL_REWARD_GROUP_ID);
    }

    @Test
    @Transactional
    public void getAllMTicketQuestStageRewardsBySpecialRewardGroupIdIsInShouldWork() throws Exception {
        // Initialize the database
        mTicketQuestStageRewardRepository.saveAndFlush(mTicketQuestStageReward);

        // Get all the mTicketQuestStageRewardList where specialRewardGroupId in DEFAULT_SPECIAL_REWARD_GROUP_ID or UPDATED_SPECIAL_REWARD_GROUP_ID
        defaultMTicketQuestStageRewardShouldBeFound("specialRewardGroupId.in=" + DEFAULT_SPECIAL_REWARD_GROUP_ID + "," + UPDATED_SPECIAL_REWARD_GROUP_ID);

        // Get all the mTicketQuestStageRewardList where specialRewardGroupId equals to UPDATED_SPECIAL_REWARD_GROUP_ID
        defaultMTicketQuestStageRewardShouldNotBeFound("specialRewardGroupId.in=" + UPDATED_SPECIAL_REWARD_GROUP_ID);
    }

    @Test
    @Transactional
    public void getAllMTicketQuestStageRewardsBySpecialRewardGroupIdIsNullOrNotNull() throws Exception {
        // Initialize the database
        mTicketQuestStageRewardRepository.saveAndFlush(mTicketQuestStageReward);

        // Get all the mTicketQuestStageRewardList where specialRewardGroupId is not null
        defaultMTicketQuestStageRewardShouldBeFound("specialRewardGroupId.specified=true");

        // Get all the mTicketQuestStageRewardList where specialRewardGroupId is null
        defaultMTicketQuestStageRewardShouldNotBeFound("specialRewardGroupId.specified=false");
    }

    @Test
    @Transactional
    public void getAllMTicketQuestStageRewardsBySpecialRewardGroupIdIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mTicketQuestStageRewardRepository.saveAndFlush(mTicketQuestStageReward);

        // Get all the mTicketQuestStageRewardList where specialRewardGroupId greater than or equals to DEFAULT_SPECIAL_REWARD_GROUP_ID
        defaultMTicketQuestStageRewardShouldBeFound("specialRewardGroupId.greaterOrEqualThan=" + DEFAULT_SPECIAL_REWARD_GROUP_ID);

        // Get all the mTicketQuestStageRewardList where specialRewardGroupId greater than or equals to UPDATED_SPECIAL_REWARD_GROUP_ID
        defaultMTicketQuestStageRewardShouldNotBeFound("specialRewardGroupId.greaterOrEqualThan=" + UPDATED_SPECIAL_REWARD_GROUP_ID);
    }

    @Test
    @Transactional
    public void getAllMTicketQuestStageRewardsBySpecialRewardGroupIdIsLessThanSomething() throws Exception {
        // Initialize the database
        mTicketQuestStageRewardRepository.saveAndFlush(mTicketQuestStageReward);

        // Get all the mTicketQuestStageRewardList where specialRewardGroupId less than or equals to DEFAULT_SPECIAL_REWARD_GROUP_ID
        defaultMTicketQuestStageRewardShouldNotBeFound("specialRewardGroupId.lessThan=" + DEFAULT_SPECIAL_REWARD_GROUP_ID);

        // Get all the mTicketQuestStageRewardList where specialRewardGroupId less than or equals to UPDATED_SPECIAL_REWARD_GROUP_ID
        defaultMTicketQuestStageRewardShouldBeFound("specialRewardGroupId.lessThan=" + UPDATED_SPECIAL_REWARD_GROUP_ID);
    }


    @Test
    @Transactional
    public void getAllMTicketQuestStageRewardsBySpecialRewardAmountIsEqualToSomething() throws Exception {
        // Initialize the database
        mTicketQuestStageRewardRepository.saveAndFlush(mTicketQuestStageReward);

        // Get all the mTicketQuestStageRewardList where specialRewardAmount equals to DEFAULT_SPECIAL_REWARD_AMOUNT
        defaultMTicketQuestStageRewardShouldBeFound("specialRewardAmount.equals=" + DEFAULT_SPECIAL_REWARD_AMOUNT);

        // Get all the mTicketQuestStageRewardList where specialRewardAmount equals to UPDATED_SPECIAL_REWARD_AMOUNT
        defaultMTicketQuestStageRewardShouldNotBeFound("specialRewardAmount.equals=" + UPDATED_SPECIAL_REWARD_AMOUNT);
    }

    @Test
    @Transactional
    public void getAllMTicketQuestStageRewardsBySpecialRewardAmountIsInShouldWork() throws Exception {
        // Initialize the database
        mTicketQuestStageRewardRepository.saveAndFlush(mTicketQuestStageReward);

        // Get all the mTicketQuestStageRewardList where specialRewardAmount in DEFAULT_SPECIAL_REWARD_AMOUNT or UPDATED_SPECIAL_REWARD_AMOUNT
        defaultMTicketQuestStageRewardShouldBeFound("specialRewardAmount.in=" + DEFAULT_SPECIAL_REWARD_AMOUNT + "," + UPDATED_SPECIAL_REWARD_AMOUNT);

        // Get all the mTicketQuestStageRewardList where specialRewardAmount equals to UPDATED_SPECIAL_REWARD_AMOUNT
        defaultMTicketQuestStageRewardShouldNotBeFound("specialRewardAmount.in=" + UPDATED_SPECIAL_REWARD_AMOUNT);
    }

    @Test
    @Transactional
    public void getAllMTicketQuestStageRewardsBySpecialRewardAmountIsNullOrNotNull() throws Exception {
        // Initialize the database
        mTicketQuestStageRewardRepository.saveAndFlush(mTicketQuestStageReward);

        // Get all the mTicketQuestStageRewardList where specialRewardAmount is not null
        defaultMTicketQuestStageRewardShouldBeFound("specialRewardAmount.specified=true");

        // Get all the mTicketQuestStageRewardList where specialRewardAmount is null
        defaultMTicketQuestStageRewardShouldNotBeFound("specialRewardAmount.specified=false");
    }

    @Test
    @Transactional
    public void getAllMTicketQuestStageRewardsBySpecialRewardAmountIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mTicketQuestStageRewardRepository.saveAndFlush(mTicketQuestStageReward);

        // Get all the mTicketQuestStageRewardList where specialRewardAmount greater than or equals to DEFAULT_SPECIAL_REWARD_AMOUNT
        defaultMTicketQuestStageRewardShouldBeFound("specialRewardAmount.greaterOrEqualThan=" + DEFAULT_SPECIAL_REWARD_AMOUNT);

        // Get all the mTicketQuestStageRewardList where specialRewardAmount greater than or equals to UPDATED_SPECIAL_REWARD_AMOUNT
        defaultMTicketQuestStageRewardShouldNotBeFound("specialRewardAmount.greaterOrEqualThan=" + UPDATED_SPECIAL_REWARD_AMOUNT);
    }

    @Test
    @Transactional
    public void getAllMTicketQuestStageRewardsBySpecialRewardAmountIsLessThanSomething() throws Exception {
        // Initialize the database
        mTicketQuestStageRewardRepository.saveAndFlush(mTicketQuestStageReward);

        // Get all the mTicketQuestStageRewardList where specialRewardAmount less than or equals to DEFAULT_SPECIAL_REWARD_AMOUNT
        defaultMTicketQuestStageRewardShouldNotBeFound("specialRewardAmount.lessThan=" + DEFAULT_SPECIAL_REWARD_AMOUNT);

        // Get all the mTicketQuestStageRewardList where specialRewardAmount less than or equals to UPDATED_SPECIAL_REWARD_AMOUNT
        defaultMTicketQuestStageRewardShouldBeFound("specialRewardAmount.lessThan=" + UPDATED_SPECIAL_REWARD_AMOUNT);
    }


    @Test
    @Transactional
    public void getAllMTicketQuestStageRewardsByGoalRewardGroupIdIsEqualToSomething() throws Exception {
        // Initialize the database
        mTicketQuestStageRewardRepository.saveAndFlush(mTicketQuestStageReward);

        // Get all the mTicketQuestStageRewardList where goalRewardGroupId equals to DEFAULT_GOAL_REWARD_GROUP_ID
        defaultMTicketQuestStageRewardShouldBeFound("goalRewardGroupId.equals=" + DEFAULT_GOAL_REWARD_GROUP_ID);

        // Get all the mTicketQuestStageRewardList where goalRewardGroupId equals to UPDATED_GOAL_REWARD_GROUP_ID
        defaultMTicketQuestStageRewardShouldNotBeFound("goalRewardGroupId.equals=" + UPDATED_GOAL_REWARD_GROUP_ID);
    }

    @Test
    @Transactional
    public void getAllMTicketQuestStageRewardsByGoalRewardGroupIdIsInShouldWork() throws Exception {
        // Initialize the database
        mTicketQuestStageRewardRepository.saveAndFlush(mTicketQuestStageReward);

        // Get all the mTicketQuestStageRewardList where goalRewardGroupId in DEFAULT_GOAL_REWARD_GROUP_ID or UPDATED_GOAL_REWARD_GROUP_ID
        defaultMTicketQuestStageRewardShouldBeFound("goalRewardGroupId.in=" + DEFAULT_GOAL_REWARD_GROUP_ID + "," + UPDATED_GOAL_REWARD_GROUP_ID);

        // Get all the mTicketQuestStageRewardList where goalRewardGroupId equals to UPDATED_GOAL_REWARD_GROUP_ID
        defaultMTicketQuestStageRewardShouldNotBeFound("goalRewardGroupId.in=" + UPDATED_GOAL_REWARD_GROUP_ID);
    }

    @Test
    @Transactional
    public void getAllMTicketQuestStageRewardsByGoalRewardGroupIdIsNullOrNotNull() throws Exception {
        // Initialize the database
        mTicketQuestStageRewardRepository.saveAndFlush(mTicketQuestStageReward);

        // Get all the mTicketQuestStageRewardList where goalRewardGroupId is not null
        defaultMTicketQuestStageRewardShouldBeFound("goalRewardGroupId.specified=true");

        // Get all the mTicketQuestStageRewardList where goalRewardGroupId is null
        defaultMTicketQuestStageRewardShouldNotBeFound("goalRewardGroupId.specified=false");
    }

    @Test
    @Transactional
    public void getAllMTicketQuestStageRewardsByGoalRewardGroupIdIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mTicketQuestStageRewardRepository.saveAndFlush(mTicketQuestStageReward);

        // Get all the mTicketQuestStageRewardList where goalRewardGroupId greater than or equals to DEFAULT_GOAL_REWARD_GROUP_ID
        defaultMTicketQuestStageRewardShouldBeFound("goalRewardGroupId.greaterOrEqualThan=" + DEFAULT_GOAL_REWARD_GROUP_ID);

        // Get all the mTicketQuestStageRewardList where goalRewardGroupId greater than or equals to UPDATED_GOAL_REWARD_GROUP_ID
        defaultMTicketQuestStageRewardShouldNotBeFound("goalRewardGroupId.greaterOrEqualThan=" + UPDATED_GOAL_REWARD_GROUP_ID);
    }

    @Test
    @Transactional
    public void getAllMTicketQuestStageRewardsByGoalRewardGroupIdIsLessThanSomething() throws Exception {
        // Initialize the database
        mTicketQuestStageRewardRepository.saveAndFlush(mTicketQuestStageReward);

        // Get all the mTicketQuestStageRewardList where goalRewardGroupId less than or equals to DEFAULT_GOAL_REWARD_GROUP_ID
        defaultMTicketQuestStageRewardShouldNotBeFound("goalRewardGroupId.lessThan=" + DEFAULT_GOAL_REWARD_GROUP_ID);

        // Get all the mTicketQuestStageRewardList where goalRewardGroupId less than or equals to UPDATED_GOAL_REWARD_GROUP_ID
        defaultMTicketQuestStageRewardShouldBeFound("goalRewardGroupId.lessThan=" + UPDATED_GOAL_REWARD_GROUP_ID);
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultMTicketQuestStageRewardShouldBeFound(String filter) throws Exception {
        restMTicketQuestStageRewardMockMvc.perform(get("/api/m-ticket-quest-stage-rewards?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(mTicketQuestStageReward.getId().intValue())))
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
        restMTicketQuestStageRewardMockMvc.perform(get("/api/m-ticket-quest-stage-rewards/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultMTicketQuestStageRewardShouldNotBeFound(String filter) throws Exception {
        restMTicketQuestStageRewardMockMvc.perform(get("/api/m-ticket-quest-stage-rewards?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restMTicketQuestStageRewardMockMvc.perform(get("/api/m-ticket-quest-stage-rewards/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("0"));
    }


    @Test
    @Transactional
    public void getNonExistingMTicketQuestStageReward() throws Exception {
        // Get the mTicketQuestStageReward
        restMTicketQuestStageRewardMockMvc.perform(get("/api/m-ticket-quest-stage-rewards/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateMTicketQuestStageReward() throws Exception {
        // Initialize the database
        mTicketQuestStageRewardRepository.saveAndFlush(mTicketQuestStageReward);

        int databaseSizeBeforeUpdate = mTicketQuestStageRewardRepository.findAll().size();

        // Update the mTicketQuestStageReward
        MTicketQuestStageReward updatedMTicketQuestStageReward = mTicketQuestStageRewardRepository.findById(mTicketQuestStageReward.getId()).get();
        // Disconnect from session so that the updates on updatedMTicketQuestStageReward are not directly saved in db
        em.detach(updatedMTicketQuestStageReward);
        updatedMTicketQuestStageReward
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
        MTicketQuestStageRewardDTO mTicketQuestStageRewardDTO = mTicketQuestStageRewardMapper.toDto(updatedMTicketQuestStageReward);

        restMTicketQuestStageRewardMockMvc.perform(put("/api/m-ticket-quest-stage-rewards")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mTicketQuestStageRewardDTO)))
            .andExpect(status().isOk());

        // Validate the MTicketQuestStageReward in the database
        List<MTicketQuestStageReward> mTicketQuestStageRewardList = mTicketQuestStageRewardRepository.findAll();
        assertThat(mTicketQuestStageRewardList).hasSize(databaseSizeBeforeUpdate);
        MTicketQuestStageReward testMTicketQuestStageReward = mTicketQuestStageRewardList.get(mTicketQuestStageRewardList.size() - 1);
        assertThat(testMTicketQuestStageReward.getStageId()).isEqualTo(UPDATED_STAGE_ID);
        assertThat(testMTicketQuestStageReward.getExp()).isEqualTo(UPDATED_EXP);
        assertThat(testMTicketQuestStageReward.getCoin()).isEqualTo(UPDATED_COIN);
        assertThat(testMTicketQuestStageReward.getGuildPoint()).isEqualTo(UPDATED_GUILD_POINT);
        assertThat(testMTicketQuestStageReward.getClearRewardGroupId()).isEqualTo(UPDATED_CLEAR_REWARD_GROUP_ID);
        assertThat(testMTicketQuestStageReward.getClearRewardWeightId()).isEqualTo(UPDATED_CLEAR_REWARD_WEIGHT_ID);
        assertThat(testMTicketQuestStageReward.getAchievementRewardGroupId()).isEqualTo(UPDATED_ACHIEVEMENT_REWARD_GROUP_ID);
        assertThat(testMTicketQuestStageReward.getCoopGroupId()).isEqualTo(UPDATED_COOP_GROUP_ID);
        assertThat(testMTicketQuestStageReward.getSpecialRewardGroupId()).isEqualTo(UPDATED_SPECIAL_REWARD_GROUP_ID);
        assertThat(testMTicketQuestStageReward.getSpecialRewardAmount()).isEqualTo(UPDATED_SPECIAL_REWARD_AMOUNT);
        assertThat(testMTicketQuestStageReward.getGoalRewardGroupId()).isEqualTo(UPDATED_GOAL_REWARD_GROUP_ID);
    }

    @Test
    @Transactional
    public void updateNonExistingMTicketQuestStageReward() throws Exception {
        int databaseSizeBeforeUpdate = mTicketQuestStageRewardRepository.findAll().size();

        // Create the MTicketQuestStageReward
        MTicketQuestStageRewardDTO mTicketQuestStageRewardDTO = mTicketQuestStageRewardMapper.toDto(mTicketQuestStageReward);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restMTicketQuestStageRewardMockMvc.perform(put("/api/m-ticket-quest-stage-rewards")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mTicketQuestStageRewardDTO)))
            .andExpect(status().isBadRequest());

        // Validate the MTicketQuestStageReward in the database
        List<MTicketQuestStageReward> mTicketQuestStageRewardList = mTicketQuestStageRewardRepository.findAll();
        assertThat(mTicketQuestStageRewardList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteMTicketQuestStageReward() throws Exception {
        // Initialize the database
        mTicketQuestStageRewardRepository.saveAndFlush(mTicketQuestStageReward);

        int databaseSizeBeforeDelete = mTicketQuestStageRewardRepository.findAll().size();

        // Delete the mTicketQuestStageReward
        restMTicketQuestStageRewardMockMvc.perform(delete("/api/m-ticket-quest-stage-rewards/{id}", mTicketQuestStageReward.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database is empty
        List<MTicketQuestStageReward> mTicketQuestStageRewardList = mTicketQuestStageRewardRepository.findAll();
        assertThat(mTicketQuestStageRewardList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(MTicketQuestStageReward.class);
        MTicketQuestStageReward mTicketQuestStageReward1 = new MTicketQuestStageReward();
        mTicketQuestStageReward1.setId(1L);
        MTicketQuestStageReward mTicketQuestStageReward2 = new MTicketQuestStageReward();
        mTicketQuestStageReward2.setId(mTicketQuestStageReward1.getId());
        assertThat(mTicketQuestStageReward1).isEqualTo(mTicketQuestStageReward2);
        mTicketQuestStageReward2.setId(2L);
        assertThat(mTicketQuestStageReward1).isNotEqualTo(mTicketQuestStageReward2);
        mTicketQuestStageReward1.setId(null);
        assertThat(mTicketQuestStageReward1).isNotEqualTo(mTicketQuestStageReward2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(MTicketQuestStageRewardDTO.class);
        MTicketQuestStageRewardDTO mTicketQuestStageRewardDTO1 = new MTicketQuestStageRewardDTO();
        mTicketQuestStageRewardDTO1.setId(1L);
        MTicketQuestStageRewardDTO mTicketQuestStageRewardDTO2 = new MTicketQuestStageRewardDTO();
        assertThat(mTicketQuestStageRewardDTO1).isNotEqualTo(mTicketQuestStageRewardDTO2);
        mTicketQuestStageRewardDTO2.setId(mTicketQuestStageRewardDTO1.getId());
        assertThat(mTicketQuestStageRewardDTO1).isEqualTo(mTicketQuestStageRewardDTO2);
        mTicketQuestStageRewardDTO2.setId(2L);
        assertThat(mTicketQuestStageRewardDTO1).isNotEqualTo(mTicketQuestStageRewardDTO2);
        mTicketQuestStageRewardDTO1.setId(null);
        assertThat(mTicketQuestStageRewardDTO1).isNotEqualTo(mTicketQuestStageRewardDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(mTicketQuestStageRewardMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(mTicketQuestStageRewardMapper.fromId(null)).isNull();
    }
}
