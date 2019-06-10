package io.shm.tsubasa.web.rest;

import io.shm.tsubasa.TsubasaApp;
import io.shm.tsubasa.domain.MChallengeQuestAchievementReward;
import io.shm.tsubasa.repository.MChallengeQuestAchievementRewardRepository;
import io.shm.tsubasa.service.MChallengeQuestAchievementRewardService;
import io.shm.tsubasa.service.dto.MChallengeQuestAchievementRewardDTO;
import io.shm.tsubasa.service.mapper.MChallengeQuestAchievementRewardMapper;
import io.shm.tsubasa.web.rest.errors.ExceptionTranslator;
import io.shm.tsubasa.service.dto.MChallengeQuestAchievementRewardCriteria;
import io.shm.tsubasa.service.MChallengeQuestAchievementRewardQueryService;

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
 * Integration tests for the {@Link MChallengeQuestAchievementRewardResource} REST controller.
 */
@SpringBootTest(classes = TsubasaApp.class)
public class MChallengeQuestAchievementRewardResourceIT {

    private static final Integer DEFAULT_WORLD_ID = 1;
    private static final Integer UPDATED_WORLD_ID = 2;

    private static final Integer DEFAULT_STAGE_ID = 1;
    private static final Integer UPDATED_STAGE_ID = 2;

    private static final Integer DEFAULT_REWARD_GROUP_ID = 1;
    private static final Integer UPDATED_REWARD_GROUP_ID = 2;

    @Autowired
    private MChallengeQuestAchievementRewardRepository mChallengeQuestAchievementRewardRepository;

    @Autowired
    private MChallengeQuestAchievementRewardMapper mChallengeQuestAchievementRewardMapper;

    @Autowired
    private MChallengeQuestAchievementRewardService mChallengeQuestAchievementRewardService;

    @Autowired
    private MChallengeQuestAchievementRewardQueryService mChallengeQuestAchievementRewardQueryService;

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

    private MockMvc restMChallengeQuestAchievementRewardMockMvc;

    private MChallengeQuestAchievementReward mChallengeQuestAchievementReward;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final MChallengeQuestAchievementRewardResource mChallengeQuestAchievementRewardResource = new MChallengeQuestAchievementRewardResource(mChallengeQuestAchievementRewardService, mChallengeQuestAchievementRewardQueryService);
        this.restMChallengeQuestAchievementRewardMockMvc = MockMvcBuilders.standaloneSetup(mChallengeQuestAchievementRewardResource)
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
    public static MChallengeQuestAchievementReward createEntity(EntityManager em) {
        MChallengeQuestAchievementReward mChallengeQuestAchievementReward = new MChallengeQuestAchievementReward()
            .worldId(DEFAULT_WORLD_ID)
            .stageId(DEFAULT_STAGE_ID)
            .rewardGroupId(DEFAULT_REWARD_GROUP_ID);
        return mChallengeQuestAchievementReward;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static MChallengeQuestAchievementReward createUpdatedEntity(EntityManager em) {
        MChallengeQuestAchievementReward mChallengeQuestAchievementReward = new MChallengeQuestAchievementReward()
            .worldId(UPDATED_WORLD_ID)
            .stageId(UPDATED_STAGE_ID)
            .rewardGroupId(UPDATED_REWARD_GROUP_ID);
        return mChallengeQuestAchievementReward;
    }

    @BeforeEach
    public void initTest() {
        mChallengeQuestAchievementReward = createEntity(em);
    }

    @Test
    @Transactional
    public void createMChallengeQuestAchievementReward() throws Exception {
        int databaseSizeBeforeCreate = mChallengeQuestAchievementRewardRepository.findAll().size();

        // Create the MChallengeQuestAchievementReward
        MChallengeQuestAchievementRewardDTO mChallengeQuestAchievementRewardDTO = mChallengeQuestAchievementRewardMapper.toDto(mChallengeQuestAchievementReward);
        restMChallengeQuestAchievementRewardMockMvc.perform(post("/api/m-challenge-quest-achievement-rewards")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mChallengeQuestAchievementRewardDTO)))
            .andExpect(status().isCreated());

        // Validate the MChallengeQuestAchievementReward in the database
        List<MChallengeQuestAchievementReward> mChallengeQuestAchievementRewardList = mChallengeQuestAchievementRewardRepository.findAll();
        assertThat(mChallengeQuestAchievementRewardList).hasSize(databaseSizeBeforeCreate + 1);
        MChallengeQuestAchievementReward testMChallengeQuestAchievementReward = mChallengeQuestAchievementRewardList.get(mChallengeQuestAchievementRewardList.size() - 1);
        assertThat(testMChallengeQuestAchievementReward.getWorldId()).isEqualTo(DEFAULT_WORLD_ID);
        assertThat(testMChallengeQuestAchievementReward.getStageId()).isEqualTo(DEFAULT_STAGE_ID);
        assertThat(testMChallengeQuestAchievementReward.getRewardGroupId()).isEqualTo(DEFAULT_REWARD_GROUP_ID);
    }

    @Test
    @Transactional
    public void createMChallengeQuestAchievementRewardWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = mChallengeQuestAchievementRewardRepository.findAll().size();

        // Create the MChallengeQuestAchievementReward with an existing ID
        mChallengeQuestAchievementReward.setId(1L);
        MChallengeQuestAchievementRewardDTO mChallengeQuestAchievementRewardDTO = mChallengeQuestAchievementRewardMapper.toDto(mChallengeQuestAchievementReward);

        // An entity with an existing ID cannot be created, so this API call must fail
        restMChallengeQuestAchievementRewardMockMvc.perform(post("/api/m-challenge-quest-achievement-rewards")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mChallengeQuestAchievementRewardDTO)))
            .andExpect(status().isBadRequest());

        // Validate the MChallengeQuestAchievementReward in the database
        List<MChallengeQuestAchievementReward> mChallengeQuestAchievementRewardList = mChallengeQuestAchievementRewardRepository.findAll();
        assertThat(mChallengeQuestAchievementRewardList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkWorldIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = mChallengeQuestAchievementRewardRepository.findAll().size();
        // set the field null
        mChallengeQuestAchievementReward.setWorldId(null);

        // Create the MChallengeQuestAchievementReward, which fails.
        MChallengeQuestAchievementRewardDTO mChallengeQuestAchievementRewardDTO = mChallengeQuestAchievementRewardMapper.toDto(mChallengeQuestAchievementReward);

        restMChallengeQuestAchievementRewardMockMvc.perform(post("/api/m-challenge-quest-achievement-rewards")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mChallengeQuestAchievementRewardDTO)))
            .andExpect(status().isBadRequest());

        List<MChallengeQuestAchievementReward> mChallengeQuestAchievementRewardList = mChallengeQuestAchievementRewardRepository.findAll();
        assertThat(mChallengeQuestAchievementRewardList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkStageIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = mChallengeQuestAchievementRewardRepository.findAll().size();
        // set the field null
        mChallengeQuestAchievementReward.setStageId(null);

        // Create the MChallengeQuestAchievementReward, which fails.
        MChallengeQuestAchievementRewardDTO mChallengeQuestAchievementRewardDTO = mChallengeQuestAchievementRewardMapper.toDto(mChallengeQuestAchievementReward);

        restMChallengeQuestAchievementRewardMockMvc.perform(post("/api/m-challenge-quest-achievement-rewards")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mChallengeQuestAchievementRewardDTO)))
            .andExpect(status().isBadRequest());

        List<MChallengeQuestAchievementReward> mChallengeQuestAchievementRewardList = mChallengeQuestAchievementRewardRepository.findAll();
        assertThat(mChallengeQuestAchievementRewardList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkRewardGroupIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = mChallengeQuestAchievementRewardRepository.findAll().size();
        // set the field null
        mChallengeQuestAchievementReward.setRewardGroupId(null);

        // Create the MChallengeQuestAchievementReward, which fails.
        MChallengeQuestAchievementRewardDTO mChallengeQuestAchievementRewardDTO = mChallengeQuestAchievementRewardMapper.toDto(mChallengeQuestAchievementReward);

        restMChallengeQuestAchievementRewardMockMvc.perform(post("/api/m-challenge-quest-achievement-rewards")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mChallengeQuestAchievementRewardDTO)))
            .andExpect(status().isBadRequest());

        List<MChallengeQuestAchievementReward> mChallengeQuestAchievementRewardList = mChallengeQuestAchievementRewardRepository.findAll();
        assertThat(mChallengeQuestAchievementRewardList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllMChallengeQuestAchievementRewards() throws Exception {
        // Initialize the database
        mChallengeQuestAchievementRewardRepository.saveAndFlush(mChallengeQuestAchievementReward);

        // Get all the mChallengeQuestAchievementRewardList
        restMChallengeQuestAchievementRewardMockMvc.perform(get("/api/m-challenge-quest-achievement-rewards?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(mChallengeQuestAchievementReward.getId().intValue())))
            .andExpect(jsonPath("$.[*].worldId").value(hasItem(DEFAULT_WORLD_ID)))
            .andExpect(jsonPath("$.[*].stageId").value(hasItem(DEFAULT_STAGE_ID)))
            .andExpect(jsonPath("$.[*].rewardGroupId").value(hasItem(DEFAULT_REWARD_GROUP_ID)));
    }
    
    @Test
    @Transactional
    public void getMChallengeQuestAchievementReward() throws Exception {
        // Initialize the database
        mChallengeQuestAchievementRewardRepository.saveAndFlush(mChallengeQuestAchievementReward);

        // Get the mChallengeQuestAchievementReward
        restMChallengeQuestAchievementRewardMockMvc.perform(get("/api/m-challenge-quest-achievement-rewards/{id}", mChallengeQuestAchievementReward.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(mChallengeQuestAchievementReward.getId().intValue()))
            .andExpect(jsonPath("$.worldId").value(DEFAULT_WORLD_ID))
            .andExpect(jsonPath("$.stageId").value(DEFAULT_STAGE_ID))
            .andExpect(jsonPath("$.rewardGroupId").value(DEFAULT_REWARD_GROUP_ID));
    }

    @Test
    @Transactional
    public void getAllMChallengeQuestAchievementRewardsByWorldIdIsEqualToSomething() throws Exception {
        // Initialize the database
        mChallengeQuestAchievementRewardRepository.saveAndFlush(mChallengeQuestAchievementReward);

        // Get all the mChallengeQuestAchievementRewardList where worldId equals to DEFAULT_WORLD_ID
        defaultMChallengeQuestAchievementRewardShouldBeFound("worldId.equals=" + DEFAULT_WORLD_ID);

        // Get all the mChallengeQuestAchievementRewardList where worldId equals to UPDATED_WORLD_ID
        defaultMChallengeQuestAchievementRewardShouldNotBeFound("worldId.equals=" + UPDATED_WORLD_ID);
    }

    @Test
    @Transactional
    public void getAllMChallengeQuestAchievementRewardsByWorldIdIsInShouldWork() throws Exception {
        // Initialize the database
        mChallengeQuestAchievementRewardRepository.saveAndFlush(mChallengeQuestAchievementReward);

        // Get all the mChallengeQuestAchievementRewardList where worldId in DEFAULT_WORLD_ID or UPDATED_WORLD_ID
        defaultMChallengeQuestAchievementRewardShouldBeFound("worldId.in=" + DEFAULT_WORLD_ID + "," + UPDATED_WORLD_ID);

        // Get all the mChallengeQuestAchievementRewardList where worldId equals to UPDATED_WORLD_ID
        defaultMChallengeQuestAchievementRewardShouldNotBeFound("worldId.in=" + UPDATED_WORLD_ID);
    }

    @Test
    @Transactional
    public void getAllMChallengeQuestAchievementRewardsByWorldIdIsNullOrNotNull() throws Exception {
        // Initialize the database
        mChallengeQuestAchievementRewardRepository.saveAndFlush(mChallengeQuestAchievementReward);

        // Get all the mChallengeQuestAchievementRewardList where worldId is not null
        defaultMChallengeQuestAchievementRewardShouldBeFound("worldId.specified=true");

        // Get all the mChallengeQuestAchievementRewardList where worldId is null
        defaultMChallengeQuestAchievementRewardShouldNotBeFound("worldId.specified=false");
    }

    @Test
    @Transactional
    public void getAllMChallengeQuestAchievementRewardsByWorldIdIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mChallengeQuestAchievementRewardRepository.saveAndFlush(mChallengeQuestAchievementReward);

        // Get all the mChallengeQuestAchievementRewardList where worldId greater than or equals to DEFAULT_WORLD_ID
        defaultMChallengeQuestAchievementRewardShouldBeFound("worldId.greaterOrEqualThan=" + DEFAULT_WORLD_ID);

        // Get all the mChallengeQuestAchievementRewardList where worldId greater than or equals to UPDATED_WORLD_ID
        defaultMChallengeQuestAchievementRewardShouldNotBeFound("worldId.greaterOrEqualThan=" + UPDATED_WORLD_ID);
    }

    @Test
    @Transactional
    public void getAllMChallengeQuestAchievementRewardsByWorldIdIsLessThanSomething() throws Exception {
        // Initialize the database
        mChallengeQuestAchievementRewardRepository.saveAndFlush(mChallengeQuestAchievementReward);

        // Get all the mChallengeQuestAchievementRewardList where worldId less than or equals to DEFAULT_WORLD_ID
        defaultMChallengeQuestAchievementRewardShouldNotBeFound("worldId.lessThan=" + DEFAULT_WORLD_ID);

        // Get all the mChallengeQuestAchievementRewardList where worldId less than or equals to UPDATED_WORLD_ID
        defaultMChallengeQuestAchievementRewardShouldBeFound("worldId.lessThan=" + UPDATED_WORLD_ID);
    }


    @Test
    @Transactional
    public void getAllMChallengeQuestAchievementRewardsByStageIdIsEqualToSomething() throws Exception {
        // Initialize the database
        mChallengeQuestAchievementRewardRepository.saveAndFlush(mChallengeQuestAchievementReward);

        // Get all the mChallengeQuestAchievementRewardList where stageId equals to DEFAULT_STAGE_ID
        defaultMChallengeQuestAchievementRewardShouldBeFound("stageId.equals=" + DEFAULT_STAGE_ID);

        // Get all the mChallengeQuestAchievementRewardList where stageId equals to UPDATED_STAGE_ID
        defaultMChallengeQuestAchievementRewardShouldNotBeFound("stageId.equals=" + UPDATED_STAGE_ID);
    }

    @Test
    @Transactional
    public void getAllMChallengeQuestAchievementRewardsByStageIdIsInShouldWork() throws Exception {
        // Initialize the database
        mChallengeQuestAchievementRewardRepository.saveAndFlush(mChallengeQuestAchievementReward);

        // Get all the mChallengeQuestAchievementRewardList where stageId in DEFAULT_STAGE_ID or UPDATED_STAGE_ID
        defaultMChallengeQuestAchievementRewardShouldBeFound("stageId.in=" + DEFAULT_STAGE_ID + "," + UPDATED_STAGE_ID);

        // Get all the mChallengeQuestAchievementRewardList where stageId equals to UPDATED_STAGE_ID
        defaultMChallengeQuestAchievementRewardShouldNotBeFound("stageId.in=" + UPDATED_STAGE_ID);
    }

    @Test
    @Transactional
    public void getAllMChallengeQuestAchievementRewardsByStageIdIsNullOrNotNull() throws Exception {
        // Initialize the database
        mChallengeQuestAchievementRewardRepository.saveAndFlush(mChallengeQuestAchievementReward);

        // Get all the mChallengeQuestAchievementRewardList where stageId is not null
        defaultMChallengeQuestAchievementRewardShouldBeFound("stageId.specified=true");

        // Get all the mChallengeQuestAchievementRewardList where stageId is null
        defaultMChallengeQuestAchievementRewardShouldNotBeFound("stageId.specified=false");
    }

    @Test
    @Transactional
    public void getAllMChallengeQuestAchievementRewardsByStageIdIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mChallengeQuestAchievementRewardRepository.saveAndFlush(mChallengeQuestAchievementReward);

        // Get all the mChallengeQuestAchievementRewardList where stageId greater than or equals to DEFAULT_STAGE_ID
        defaultMChallengeQuestAchievementRewardShouldBeFound("stageId.greaterOrEqualThan=" + DEFAULT_STAGE_ID);

        // Get all the mChallengeQuestAchievementRewardList where stageId greater than or equals to UPDATED_STAGE_ID
        defaultMChallengeQuestAchievementRewardShouldNotBeFound("stageId.greaterOrEqualThan=" + UPDATED_STAGE_ID);
    }

    @Test
    @Transactional
    public void getAllMChallengeQuestAchievementRewardsByStageIdIsLessThanSomething() throws Exception {
        // Initialize the database
        mChallengeQuestAchievementRewardRepository.saveAndFlush(mChallengeQuestAchievementReward);

        // Get all the mChallengeQuestAchievementRewardList where stageId less than or equals to DEFAULT_STAGE_ID
        defaultMChallengeQuestAchievementRewardShouldNotBeFound("stageId.lessThan=" + DEFAULT_STAGE_ID);

        // Get all the mChallengeQuestAchievementRewardList where stageId less than or equals to UPDATED_STAGE_ID
        defaultMChallengeQuestAchievementRewardShouldBeFound("stageId.lessThan=" + UPDATED_STAGE_ID);
    }


    @Test
    @Transactional
    public void getAllMChallengeQuestAchievementRewardsByRewardGroupIdIsEqualToSomething() throws Exception {
        // Initialize the database
        mChallengeQuestAchievementRewardRepository.saveAndFlush(mChallengeQuestAchievementReward);

        // Get all the mChallengeQuestAchievementRewardList where rewardGroupId equals to DEFAULT_REWARD_GROUP_ID
        defaultMChallengeQuestAchievementRewardShouldBeFound("rewardGroupId.equals=" + DEFAULT_REWARD_GROUP_ID);

        // Get all the mChallengeQuestAchievementRewardList where rewardGroupId equals to UPDATED_REWARD_GROUP_ID
        defaultMChallengeQuestAchievementRewardShouldNotBeFound("rewardGroupId.equals=" + UPDATED_REWARD_GROUP_ID);
    }

    @Test
    @Transactional
    public void getAllMChallengeQuestAchievementRewardsByRewardGroupIdIsInShouldWork() throws Exception {
        // Initialize the database
        mChallengeQuestAchievementRewardRepository.saveAndFlush(mChallengeQuestAchievementReward);

        // Get all the mChallengeQuestAchievementRewardList where rewardGroupId in DEFAULT_REWARD_GROUP_ID or UPDATED_REWARD_GROUP_ID
        defaultMChallengeQuestAchievementRewardShouldBeFound("rewardGroupId.in=" + DEFAULT_REWARD_GROUP_ID + "," + UPDATED_REWARD_GROUP_ID);

        // Get all the mChallengeQuestAchievementRewardList where rewardGroupId equals to UPDATED_REWARD_GROUP_ID
        defaultMChallengeQuestAchievementRewardShouldNotBeFound("rewardGroupId.in=" + UPDATED_REWARD_GROUP_ID);
    }

    @Test
    @Transactional
    public void getAllMChallengeQuestAchievementRewardsByRewardGroupIdIsNullOrNotNull() throws Exception {
        // Initialize the database
        mChallengeQuestAchievementRewardRepository.saveAndFlush(mChallengeQuestAchievementReward);

        // Get all the mChallengeQuestAchievementRewardList where rewardGroupId is not null
        defaultMChallengeQuestAchievementRewardShouldBeFound("rewardGroupId.specified=true");

        // Get all the mChallengeQuestAchievementRewardList where rewardGroupId is null
        defaultMChallengeQuestAchievementRewardShouldNotBeFound("rewardGroupId.specified=false");
    }

    @Test
    @Transactional
    public void getAllMChallengeQuestAchievementRewardsByRewardGroupIdIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mChallengeQuestAchievementRewardRepository.saveAndFlush(mChallengeQuestAchievementReward);

        // Get all the mChallengeQuestAchievementRewardList where rewardGroupId greater than or equals to DEFAULT_REWARD_GROUP_ID
        defaultMChallengeQuestAchievementRewardShouldBeFound("rewardGroupId.greaterOrEqualThan=" + DEFAULT_REWARD_GROUP_ID);

        // Get all the mChallengeQuestAchievementRewardList where rewardGroupId greater than or equals to UPDATED_REWARD_GROUP_ID
        defaultMChallengeQuestAchievementRewardShouldNotBeFound("rewardGroupId.greaterOrEqualThan=" + UPDATED_REWARD_GROUP_ID);
    }

    @Test
    @Transactional
    public void getAllMChallengeQuestAchievementRewardsByRewardGroupIdIsLessThanSomething() throws Exception {
        // Initialize the database
        mChallengeQuestAchievementRewardRepository.saveAndFlush(mChallengeQuestAchievementReward);

        // Get all the mChallengeQuestAchievementRewardList where rewardGroupId less than or equals to DEFAULT_REWARD_GROUP_ID
        defaultMChallengeQuestAchievementRewardShouldNotBeFound("rewardGroupId.lessThan=" + DEFAULT_REWARD_GROUP_ID);

        // Get all the mChallengeQuestAchievementRewardList where rewardGroupId less than or equals to UPDATED_REWARD_GROUP_ID
        defaultMChallengeQuestAchievementRewardShouldBeFound("rewardGroupId.lessThan=" + UPDATED_REWARD_GROUP_ID);
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultMChallengeQuestAchievementRewardShouldBeFound(String filter) throws Exception {
        restMChallengeQuestAchievementRewardMockMvc.perform(get("/api/m-challenge-quest-achievement-rewards?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(mChallengeQuestAchievementReward.getId().intValue())))
            .andExpect(jsonPath("$.[*].worldId").value(hasItem(DEFAULT_WORLD_ID)))
            .andExpect(jsonPath("$.[*].stageId").value(hasItem(DEFAULT_STAGE_ID)))
            .andExpect(jsonPath("$.[*].rewardGroupId").value(hasItem(DEFAULT_REWARD_GROUP_ID)));

        // Check, that the count call also returns 1
        restMChallengeQuestAchievementRewardMockMvc.perform(get("/api/m-challenge-quest-achievement-rewards/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultMChallengeQuestAchievementRewardShouldNotBeFound(String filter) throws Exception {
        restMChallengeQuestAchievementRewardMockMvc.perform(get("/api/m-challenge-quest-achievement-rewards?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restMChallengeQuestAchievementRewardMockMvc.perform(get("/api/m-challenge-quest-achievement-rewards/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("0"));
    }


    @Test
    @Transactional
    public void getNonExistingMChallengeQuestAchievementReward() throws Exception {
        // Get the mChallengeQuestAchievementReward
        restMChallengeQuestAchievementRewardMockMvc.perform(get("/api/m-challenge-quest-achievement-rewards/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateMChallengeQuestAchievementReward() throws Exception {
        // Initialize the database
        mChallengeQuestAchievementRewardRepository.saveAndFlush(mChallengeQuestAchievementReward);

        int databaseSizeBeforeUpdate = mChallengeQuestAchievementRewardRepository.findAll().size();

        // Update the mChallengeQuestAchievementReward
        MChallengeQuestAchievementReward updatedMChallengeQuestAchievementReward = mChallengeQuestAchievementRewardRepository.findById(mChallengeQuestAchievementReward.getId()).get();
        // Disconnect from session so that the updates on updatedMChallengeQuestAchievementReward are not directly saved in db
        em.detach(updatedMChallengeQuestAchievementReward);
        updatedMChallengeQuestAchievementReward
            .worldId(UPDATED_WORLD_ID)
            .stageId(UPDATED_STAGE_ID)
            .rewardGroupId(UPDATED_REWARD_GROUP_ID);
        MChallengeQuestAchievementRewardDTO mChallengeQuestAchievementRewardDTO = mChallengeQuestAchievementRewardMapper.toDto(updatedMChallengeQuestAchievementReward);

        restMChallengeQuestAchievementRewardMockMvc.perform(put("/api/m-challenge-quest-achievement-rewards")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mChallengeQuestAchievementRewardDTO)))
            .andExpect(status().isOk());

        // Validate the MChallengeQuestAchievementReward in the database
        List<MChallengeQuestAchievementReward> mChallengeQuestAchievementRewardList = mChallengeQuestAchievementRewardRepository.findAll();
        assertThat(mChallengeQuestAchievementRewardList).hasSize(databaseSizeBeforeUpdate);
        MChallengeQuestAchievementReward testMChallengeQuestAchievementReward = mChallengeQuestAchievementRewardList.get(mChallengeQuestAchievementRewardList.size() - 1);
        assertThat(testMChallengeQuestAchievementReward.getWorldId()).isEqualTo(UPDATED_WORLD_ID);
        assertThat(testMChallengeQuestAchievementReward.getStageId()).isEqualTo(UPDATED_STAGE_ID);
        assertThat(testMChallengeQuestAchievementReward.getRewardGroupId()).isEqualTo(UPDATED_REWARD_GROUP_ID);
    }

    @Test
    @Transactional
    public void updateNonExistingMChallengeQuestAchievementReward() throws Exception {
        int databaseSizeBeforeUpdate = mChallengeQuestAchievementRewardRepository.findAll().size();

        // Create the MChallengeQuestAchievementReward
        MChallengeQuestAchievementRewardDTO mChallengeQuestAchievementRewardDTO = mChallengeQuestAchievementRewardMapper.toDto(mChallengeQuestAchievementReward);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restMChallengeQuestAchievementRewardMockMvc.perform(put("/api/m-challenge-quest-achievement-rewards")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mChallengeQuestAchievementRewardDTO)))
            .andExpect(status().isBadRequest());

        // Validate the MChallengeQuestAchievementReward in the database
        List<MChallengeQuestAchievementReward> mChallengeQuestAchievementRewardList = mChallengeQuestAchievementRewardRepository.findAll();
        assertThat(mChallengeQuestAchievementRewardList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteMChallengeQuestAchievementReward() throws Exception {
        // Initialize the database
        mChallengeQuestAchievementRewardRepository.saveAndFlush(mChallengeQuestAchievementReward);

        int databaseSizeBeforeDelete = mChallengeQuestAchievementRewardRepository.findAll().size();

        // Delete the mChallengeQuestAchievementReward
        restMChallengeQuestAchievementRewardMockMvc.perform(delete("/api/m-challenge-quest-achievement-rewards/{id}", mChallengeQuestAchievementReward.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database is empty
        List<MChallengeQuestAchievementReward> mChallengeQuestAchievementRewardList = mChallengeQuestAchievementRewardRepository.findAll();
        assertThat(mChallengeQuestAchievementRewardList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(MChallengeQuestAchievementReward.class);
        MChallengeQuestAchievementReward mChallengeQuestAchievementReward1 = new MChallengeQuestAchievementReward();
        mChallengeQuestAchievementReward1.setId(1L);
        MChallengeQuestAchievementReward mChallengeQuestAchievementReward2 = new MChallengeQuestAchievementReward();
        mChallengeQuestAchievementReward2.setId(mChallengeQuestAchievementReward1.getId());
        assertThat(mChallengeQuestAchievementReward1).isEqualTo(mChallengeQuestAchievementReward2);
        mChallengeQuestAchievementReward2.setId(2L);
        assertThat(mChallengeQuestAchievementReward1).isNotEqualTo(mChallengeQuestAchievementReward2);
        mChallengeQuestAchievementReward1.setId(null);
        assertThat(mChallengeQuestAchievementReward1).isNotEqualTo(mChallengeQuestAchievementReward2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(MChallengeQuestAchievementRewardDTO.class);
        MChallengeQuestAchievementRewardDTO mChallengeQuestAchievementRewardDTO1 = new MChallengeQuestAchievementRewardDTO();
        mChallengeQuestAchievementRewardDTO1.setId(1L);
        MChallengeQuestAchievementRewardDTO mChallengeQuestAchievementRewardDTO2 = new MChallengeQuestAchievementRewardDTO();
        assertThat(mChallengeQuestAchievementRewardDTO1).isNotEqualTo(mChallengeQuestAchievementRewardDTO2);
        mChallengeQuestAchievementRewardDTO2.setId(mChallengeQuestAchievementRewardDTO1.getId());
        assertThat(mChallengeQuestAchievementRewardDTO1).isEqualTo(mChallengeQuestAchievementRewardDTO2);
        mChallengeQuestAchievementRewardDTO2.setId(2L);
        assertThat(mChallengeQuestAchievementRewardDTO1).isNotEqualTo(mChallengeQuestAchievementRewardDTO2);
        mChallengeQuestAchievementRewardDTO1.setId(null);
        assertThat(mChallengeQuestAchievementRewardDTO1).isNotEqualTo(mChallengeQuestAchievementRewardDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(mChallengeQuestAchievementRewardMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(mChallengeQuestAchievementRewardMapper.fromId(null)).isNull();
    }
}
