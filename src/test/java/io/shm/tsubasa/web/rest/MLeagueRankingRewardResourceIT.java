package io.shm.tsubasa.web.rest;

import io.shm.tsubasa.TsubasaApp;
import io.shm.tsubasa.domain.MLeagueRankingReward;
import io.shm.tsubasa.repository.MLeagueRankingRewardRepository;
import io.shm.tsubasa.service.MLeagueRankingRewardService;
import io.shm.tsubasa.service.dto.MLeagueRankingRewardDTO;
import io.shm.tsubasa.service.mapper.MLeagueRankingRewardMapper;
import io.shm.tsubasa.web.rest.errors.ExceptionTranslator;
import io.shm.tsubasa.service.dto.MLeagueRankingRewardCriteria;
import io.shm.tsubasa.service.MLeagueRankingRewardQueryService;

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
 * Integration tests for the {@Link MLeagueRankingRewardResource} REST controller.
 */
@SpringBootTest(classes = TsubasaApp.class)
public class MLeagueRankingRewardResourceIT {

    private static final Integer DEFAULT_LEAGUE_HIERARCHY = 1;
    private static final Integer UPDATED_LEAGUE_HIERARCHY = 2;

    private static final Integer DEFAULT_RANK_TO = 1;
    private static final Integer UPDATED_RANK_TO = 2;

    private static final Integer DEFAULT_REWARD_GROUP_ID = 1;
    private static final Integer UPDATED_REWARD_GROUP_ID = 2;

    private static final Integer DEFAULT_START_AT = 1;
    private static final Integer UPDATED_START_AT = 2;

    private static final Integer DEFAULT_END_AT = 1;
    private static final Integer UPDATED_END_AT = 2;

    @Autowired
    private MLeagueRankingRewardRepository mLeagueRankingRewardRepository;

    @Autowired
    private MLeagueRankingRewardMapper mLeagueRankingRewardMapper;

    @Autowired
    private MLeagueRankingRewardService mLeagueRankingRewardService;

    @Autowired
    private MLeagueRankingRewardQueryService mLeagueRankingRewardQueryService;

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

    private MockMvc restMLeagueRankingRewardMockMvc;

    private MLeagueRankingReward mLeagueRankingReward;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final MLeagueRankingRewardResource mLeagueRankingRewardResource = new MLeagueRankingRewardResource(mLeagueRankingRewardService, mLeagueRankingRewardQueryService);
        this.restMLeagueRankingRewardMockMvc = MockMvcBuilders.standaloneSetup(mLeagueRankingRewardResource)
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
    public static MLeagueRankingReward createEntity(EntityManager em) {
        MLeagueRankingReward mLeagueRankingReward = new MLeagueRankingReward()
            .leagueHierarchy(DEFAULT_LEAGUE_HIERARCHY)
            .rankTo(DEFAULT_RANK_TO)
            .rewardGroupId(DEFAULT_REWARD_GROUP_ID)
            .startAt(DEFAULT_START_AT)
            .endAt(DEFAULT_END_AT);
        return mLeagueRankingReward;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static MLeagueRankingReward createUpdatedEntity(EntityManager em) {
        MLeagueRankingReward mLeagueRankingReward = new MLeagueRankingReward()
            .leagueHierarchy(UPDATED_LEAGUE_HIERARCHY)
            .rankTo(UPDATED_RANK_TO)
            .rewardGroupId(UPDATED_REWARD_GROUP_ID)
            .startAt(UPDATED_START_AT)
            .endAt(UPDATED_END_AT);
        return mLeagueRankingReward;
    }

    @BeforeEach
    public void initTest() {
        mLeagueRankingReward = createEntity(em);
    }

    @Test
    @Transactional
    public void createMLeagueRankingReward() throws Exception {
        int databaseSizeBeforeCreate = mLeagueRankingRewardRepository.findAll().size();

        // Create the MLeagueRankingReward
        MLeagueRankingRewardDTO mLeagueRankingRewardDTO = mLeagueRankingRewardMapper.toDto(mLeagueRankingReward);
        restMLeagueRankingRewardMockMvc.perform(post("/api/m-league-ranking-rewards")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mLeagueRankingRewardDTO)))
            .andExpect(status().isCreated());

        // Validate the MLeagueRankingReward in the database
        List<MLeagueRankingReward> mLeagueRankingRewardList = mLeagueRankingRewardRepository.findAll();
        assertThat(mLeagueRankingRewardList).hasSize(databaseSizeBeforeCreate + 1);
        MLeagueRankingReward testMLeagueRankingReward = mLeagueRankingRewardList.get(mLeagueRankingRewardList.size() - 1);
        assertThat(testMLeagueRankingReward.getLeagueHierarchy()).isEqualTo(DEFAULT_LEAGUE_HIERARCHY);
        assertThat(testMLeagueRankingReward.getRankTo()).isEqualTo(DEFAULT_RANK_TO);
        assertThat(testMLeagueRankingReward.getRewardGroupId()).isEqualTo(DEFAULT_REWARD_GROUP_ID);
        assertThat(testMLeagueRankingReward.getStartAt()).isEqualTo(DEFAULT_START_AT);
        assertThat(testMLeagueRankingReward.getEndAt()).isEqualTo(DEFAULT_END_AT);
    }

    @Test
    @Transactional
    public void createMLeagueRankingRewardWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = mLeagueRankingRewardRepository.findAll().size();

        // Create the MLeagueRankingReward with an existing ID
        mLeagueRankingReward.setId(1L);
        MLeagueRankingRewardDTO mLeagueRankingRewardDTO = mLeagueRankingRewardMapper.toDto(mLeagueRankingReward);

        // An entity with an existing ID cannot be created, so this API call must fail
        restMLeagueRankingRewardMockMvc.perform(post("/api/m-league-ranking-rewards")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mLeagueRankingRewardDTO)))
            .andExpect(status().isBadRequest());

        // Validate the MLeagueRankingReward in the database
        List<MLeagueRankingReward> mLeagueRankingRewardList = mLeagueRankingRewardRepository.findAll();
        assertThat(mLeagueRankingRewardList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkLeagueHierarchyIsRequired() throws Exception {
        int databaseSizeBeforeTest = mLeagueRankingRewardRepository.findAll().size();
        // set the field null
        mLeagueRankingReward.setLeagueHierarchy(null);

        // Create the MLeagueRankingReward, which fails.
        MLeagueRankingRewardDTO mLeagueRankingRewardDTO = mLeagueRankingRewardMapper.toDto(mLeagueRankingReward);

        restMLeagueRankingRewardMockMvc.perform(post("/api/m-league-ranking-rewards")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mLeagueRankingRewardDTO)))
            .andExpect(status().isBadRequest());

        List<MLeagueRankingReward> mLeagueRankingRewardList = mLeagueRankingRewardRepository.findAll();
        assertThat(mLeagueRankingRewardList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkRankToIsRequired() throws Exception {
        int databaseSizeBeforeTest = mLeagueRankingRewardRepository.findAll().size();
        // set the field null
        mLeagueRankingReward.setRankTo(null);

        // Create the MLeagueRankingReward, which fails.
        MLeagueRankingRewardDTO mLeagueRankingRewardDTO = mLeagueRankingRewardMapper.toDto(mLeagueRankingReward);

        restMLeagueRankingRewardMockMvc.perform(post("/api/m-league-ranking-rewards")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mLeagueRankingRewardDTO)))
            .andExpect(status().isBadRequest());

        List<MLeagueRankingReward> mLeagueRankingRewardList = mLeagueRankingRewardRepository.findAll();
        assertThat(mLeagueRankingRewardList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkRewardGroupIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = mLeagueRankingRewardRepository.findAll().size();
        // set the field null
        mLeagueRankingReward.setRewardGroupId(null);

        // Create the MLeagueRankingReward, which fails.
        MLeagueRankingRewardDTO mLeagueRankingRewardDTO = mLeagueRankingRewardMapper.toDto(mLeagueRankingReward);

        restMLeagueRankingRewardMockMvc.perform(post("/api/m-league-ranking-rewards")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mLeagueRankingRewardDTO)))
            .andExpect(status().isBadRequest());

        List<MLeagueRankingReward> mLeagueRankingRewardList = mLeagueRankingRewardRepository.findAll();
        assertThat(mLeagueRankingRewardList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllMLeagueRankingRewards() throws Exception {
        // Initialize the database
        mLeagueRankingRewardRepository.saveAndFlush(mLeagueRankingReward);

        // Get all the mLeagueRankingRewardList
        restMLeagueRankingRewardMockMvc.perform(get("/api/m-league-ranking-rewards?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(mLeagueRankingReward.getId().intValue())))
            .andExpect(jsonPath("$.[*].leagueHierarchy").value(hasItem(DEFAULT_LEAGUE_HIERARCHY)))
            .andExpect(jsonPath("$.[*].rankTo").value(hasItem(DEFAULT_RANK_TO)))
            .andExpect(jsonPath("$.[*].rewardGroupId").value(hasItem(DEFAULT_REWARD_GROUP_ID)))
            .andExpect(jsonPath("$.[*].startAt").value(hasItem(DEFAULT_START_AT)))
            .andExpect(jsonPath("$.[*].endAt").value(hasItem(DEFAULT_END_AT)));
    }
    
    @Test
    @Transactional
    public void getMLeagueRankingReward() throws Exception {
        // Initialize the database
        mLeagueRankingRewardRepository.saveAndFlush(mLeagueRankingReward);

        // Get the mLeagueRankingReward
        restMLeagueRankingRewardMockMvc.perform(get("/api/m-league-ranking-rewards/{id}", mLeagueRankingReward.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(mLeagueRankingReward.getId().intValue()))
            .andExpect(jsonPath("$.leagueHierarchy").value(DEFAULT_LEAGUE_HIERARCHY))
            .andExpect(jsonPath("$.rankTo").value(DEFAULT_RANK_TO))
            .andExpect(jsonPath("$.rewardGroupId").value(DEFAULT_REWARD_GROUP_ID))
            .andExpect(jsonPath("$.startAt").value(DEFAULT_START_AT))
            .andExpect(jsonPath("$.endAt").value(DEFAULT_END_AT));
    }

    @Test
    @Transactional
    public void getAllMLeagueRankingRewardsByLeagueHierarchyIsEqualToSomething() throws Exception {
        // Initialize the database
        mLeagueRankingRewardRepository.saveAndFlush(mLeagueRankingReward);

        // Get all the mLeagueRankingRewardList where leagueHierarchy equals to DEFAULT_LEAGUE_HIERARCHY
        defaultMLeagueRankingRewardShouldBeFound("leagueHierarchy.equals=" + DEFAULT_LEAGUE_HIERARCHY);

        // Get all the mLeagueRankingRewardList where leagueHierarchy equals to UPDATED_LEAGUE_HIERARCHY
        defaultMLeagueRankingRewardShouldNotBeFound("leagueHierarchy.equals=" + UPDATED_LEAGUE_HIERARCHY);
    }

    @Test
    @Transactional
    public void getAllMLeagueRankingRewardsByLeagueHierarchyIsInShouldWork() throws Exception {
        // Initialize the database
        mLeagueRankingRewardRepository.saveAndFlush(mLeagueRankingReward);

        // Get all the mLeagueRankingRewardList where leagueHierarchy in DEFAULT_LEAGUE_HIERARCHY or UPDATED_LEAGUE_HIERARCHY
        defaultMLeagueRankingRewardShouldBeFound("leagueHierarchy.in=" + DEFAULT_LEAGUE_HIERARCHY + "," + UPDATED_LEAGUE_HIERARCHY);

        // Get all the mLeagueRankingRewardList where leagueHierarchy equals to UPDATED_LEAGUE_HIERARCHY
        defaultMLeagueRankingRewardShouldNotBeFound("leagueHierarchy.in=" + UPDATED_LEAGUE_HIERARCHY);
    }

    @Test
    @Transactional
    public void getAllMLeagueRankingRewardsByLeagueHierarchyIsNullOrNotNull() throws Exception {
        // Initialize the database
        mLeagueRankingRewardRepository.saveAndFlush(mLeagueRankingReward);

        // Get all the mLeagueRankingRewardList where leagueHierarchy is not null
        defaultMLeagueRankingRewardShouldBeFound("leagueHierarchy.specified=true");

        // Get all the mLeagueRankingRewardList where leagueHierarchy is null
        defaultMLeagueRankingRewardShouldNotBeFound("leagueHierarchy.specified=false");
    }

    @Test
    @Transactional
    public void getAllMLeagueRankingRewardsByLeagueHierarchyIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mLeagueRankingRewardRepository.saveAndFlush(mLeagueRankingReward);

        // Get all the mLeagueRankingRewardList where leagueHierarchy greater than or equals to DEFAULT_LEAGUE_HIERARCHY
        defaultMLeagueRankingRewardShouldBeFound("leagueHierarchy.greaterOrEqualThan=" + DEFAULT_LEAGUE_HIERARCHY);

        // Get all the mLeagueRankingRewardList where leagueHierarchy greater than or equals to UPDATED_LEAGUE_HIERARCHY
        defaultMLeagueRankingRewardShouldNotBeFound("leagueHierarchy.greaterOrEqualThan=" + UPDATED_LEAGUE_HIERARCHY);
    }

    @Test
    @Transactional
    public void getAllMLeagueRankingRewardsByLeagueHierarchyIsLessThanSomething() throws Exception {
        // Initialize the database
        mLeagueRankingRewardRepository.saveAndFlush(mLeagueRankingReward);

        // Get all the mLeagueRankingRewardList where leagueHierarchy less than or equals to DEFAULT_LEAGUE_HIERARCHY
        defaultMLeagueRankingRewardShouldNotBeFound("leagueHierarchy.lessThan=" + DEFAULT_LEAGUE_HIERARCHY);

        // Get all the mLeagueRankingRewardList where leagueHierarchy less than or equals to UPDATED_LEAGUE_HIERARCHY
        defaultMLeagueRankingRewardShouldBeFound("leagueHierarchy.lessThan=" + UPDATED_LEAGUE_HIERARCHY);
    }


    @Test
    @Transactional
    public void getAllMLeagueRankingRewardsByRankToIsEqualToSomething() throws Exception {
        // Initialize the database
        mLeagueRankingRewardRepository.saveAndFlush(mLeagueRankingReward);

        // Get all the mLeagueRankingRewardList where rankTo equals to DEFAULT_RANK_TO
        defaultMLeagueRankingRewardShouldBeFound("rankTo.equals=" + DEFAULT_RANK_TO);

        // Get all the mLeagueRankingRewardList where rankTo equals to UPDATED_RANK_TO
        defaultMLeagueRankingRewardShouldNotBeFound("rankTo.equals=" + UPDATED_RANK_TO);
    }

    @Test
    @Transactional
    public void getAllMLeagueRankingRewardsByRankToIsInShouldWork() throws Exception {
        // Initialize the database
        mLeagueRankingRewardRepository.saveAndFlush(mLeagueRankingReward);

        // Get all the mLeagueRankingRewardList where rankTo in DEFAULT_RANK_TO or UPDATED_RANK_TO
        defaultMLeagueRankingRewardShouldBeFound("rankTo.in=" + DEFAULT_RANK_TO + "," + UPDATED_RANK_TO);

        // Get all the mLeagueRankingRewardList where rankTo equals to UPDATED_RANK_TO
        defaultMLeagueRankingRewardShouldNotBeFound("rankTo.in=" + UPDATED_RANK_TO);
    }

    @Test
    @Transactional
    public void getAllMLeagueRankingRewardsByRankToIsNullOrNotNull() throws Exception {
        // Initialize the database
        mLeagueRankingRewardRepository.saveAndFlush(mLeagueRankingReward);

        // Get all the mLeagueRankingRewardList where rankTo is not null
        defaultMLeagueRankingRewardShouldBeFound("rankTo.specified=true");

        // Get all the mLeagueRankingRewardList where rankTo is null
        defaultMLeagueRankingRewardShouldNotBeFound("rankTo.specified=false");
    }

    @Test
    @Transactional
    public void getAllMLeagueRankingRewardsByRankToIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mLeagueRankingRewardRepository.saveAndFlush(mLeagueRankingReward);

        // Get all the mLeagueRankingRewardList where rankTo greater than or equals to DEFAULT_RANK_TO
        defaultMLeagueRankingRewardShouldBeFound("rankTo.greaterOrEqualThan=" + DEFAULT_RANK_TO);

        // Get all the mLeagueRankingRewardList where rankTo greater than or equals to UPDATED_RANK_TO
        defaultMLeagueRankingRewardShouldNotBeFound("rankTo.greaterOrEqualThan=" + UPDATED_RANK_TO);
    }

    @Test
    @Transactional
    public void getAllMLeagueRankingRewardsByRankToIsLessThanSomething() throws Exception {
        // Initialize the database
        mLeagueRankingRewardRepository.saveAndFlush(mLeagueRankingReward);

        // Get all the mLeagueRankingRewardList where rankTo less than or equals to DEFAULT_RANK_TO
        defaultMLeagueRankingRewardShouldNotBeFound("rankTo.lessThan=" + DEFAULT_RANK_TO);

        // Get all the mLeagueRankingRewardList where rankTo less than or equals to UPDATED_RANK_TO
        defaultMLeagueRankingRewardShouldBeFound("rankTo.lessThan=" + UPDATED_RANK_TO);
    }


    @Test
    @Transactional
    public void getAllMLeagueRankingRewardsByRewardGroupIdIsEqualToSomething() throws Exception {
        // Initialize the database
        mLeagueRankingRewardRepository.saveAndFlush(mLeagueRankingReward);

        // Get all the mLeagueRankingRewardList where rewardGroupId equals to DEFAULT_REWARD_GROUP_ID
        defaultMLeagueRankingRewardShouldBeFound("rewardGroupId.equals=" + DEFAULT_REWARD_GROUP_ID);

        // Get all the mLeagueRankingRewardList where rewardGroupId equals to UPDATED_REWARD_GROUP_ID
        defaultMLeagueRankingRewardShouldNotBeFound("rewardGroupId.equals=" + UPDATED_REWARD_GROUP_ID);
    }

    @Test
    @Transactional
    public void getAllMLeagueRankingRewardsByRewardGroupIdIsInShouldWork() throws Exception {
        // Initialize the database
        mLeagueRankingRewardRepository.saveAndFlush(mLeagueRankingReward);

        // Get all the mLeagueRankingRewardList where rewardGroupId in DEFAULT_REWARD_GROUP_ID or UPDATED_REWARD_GROUP_ID
        defaultMLeagueRankingRewardShouldBeFound("rewardGroupId.in=" + DEFAULT_REWARD_GROUP_ID + "," + UPDATED_REWARD_GROUP_ID);

        // Get all the mLeagueRankingRewardList where rewardGroupId equals to UPDATED_REWARD_GROUP_ID
        defaultMLeagueRankingRewardShouldNotBeFound("rewardGroupId.in=" + UPDATED_REWARD_GROUP_ID);
    }

    @Test
    @Transactional
    public void getAllMLeagueRankingRewardsByRewardGroupIdIsNullOrNotNull() throws Exception {
        // Initialize the database
        mLeagueRankingRewardRepository.saveAndFlush(mLeagueRankingReward);

        // Get all the mLeagueRankingRewardList where rewardGroupId is not null
        defaultMLeagueRankingRewardShouldBeFound("rewardGroupId.specified=true");

        // Get all the mLeagueRankingRewardList where rewardGroupId is null
        defaultMLeagueRankingRewardShouldNotBeFound("rewardGroupId.specified=false");
    }

    @Test
    @Transactional
    public void getAllMLeagueRankingRewardsByRewardGroupIdIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mLeagueRankingRewardRepository.saveAndFlush(mLeagueRankingReward);

        // Get all the mLeagueRankingRewardList where rewardGroupId greater than or equals to DEFAULT_REWARD_GROUP_ID
        defaultMLeagueRankingRewardShouldBeFound("rewardGroupId.greaterOrEqualThan=" + DEFAULT_REWARD_GROUP_ID);

        // Get all the mLeagueRankingRewardList where rewardGroupId greater than or equals to UPDATED_REWARD_GROUP_ID
        defaultMLeagueRankingRewardShouldNotBeFound("rewardGroupId.greaterOrEqualThan=" + UPDATED_REWARD_GROUP_ID);
    }

    @Test
    @Transactional
    public void getAllMLeagueRankingRewardsByRewardGroupIdIsLessThanSomething() throws Exception {
        // Initialize the database
        mLeagueRankingRewardRepository.saveAndFlush(mLeagueRankingReward);

        // Get all the mLeagueRankingRewardList where rewardGroupId less than or equals to DEFAULT_REWARD_GROUP_ID
        defaultMLeagueRankingRewardShouldNotBeFound("rewardGroupId.lessThan=" + DEFAULT_REWARD_GROUP_ID);

        // Get all the mLeagueRankingRewardList where rewardGroupId less than or equals to UPDATED_REWARD_GROUP_ID
        defaultMLeagueRankingRewardShouldBeFound("rewardGroupId.lessThan=" + UPDATED_REWARD_GROUP_ID);
    }


    @Test
    @Transactional
    public void getAllMLeagueRankingRewardsByStartAtIsEqualToSomething() throws Exception {
        // Initialize the database
        mLeagueRankingRewardRepository.saveAndFlush(mLeagueRankingReward);

        // Get all the mLeagueRankingRewardList where startAt equals to DEFAULT_START_AT
        defaultMLeagueRankingRewardShouldBeFound("startAt.equals=" + DEFAULT_START_AT);

        // Get all the mLeagueRankingRewardList where startAt equals to UPDATED_START_AT
        defaultMLeagueRankingRewardShouldNotBeFound("startAt.equals=" + UPDATED_START_AT);
    }

    @Test
    @Transactional
    public void getAllMLeagueRankingRewardsByStartAtIsInShouldWork() throws Exception {
        // Initialize the database
        mLeagueRankingRewardRepository.saveAndFlush(mLeagueRankingReward);

        // Get all the mLeagueRankingRewardList where startAt in DEFAULT_START_AT or UPDATED_START_AT
        defaultMLeagueRankingRewardShouldBeFound("startAt.in=" + DEFAULT_START_AT + "," + UPDATED_START_AT);

        // Get all the mLeagueRankingRewardList where startAt equals to UPDATED_START_AT
        defaultMLeagueRankingRewardShouldNotBeFound("startAt.in=" + UPDATED_START_AT);
    }

    @Test
    @Transactional
    public void getAllMLeagueRankingRewardsByStartAtIsNullOrNotNull() throws Exception {
        // Initialize the database
        mLeagueRankingRewardRepository.saveAndFlush(mLeagueRankingReward);

        // Get all the mLeagueRankingRewardList where startAt is not null
        defaultMLeagueRankingRewardShouldBeFound("startAt.specified=true");

        // Get all the mLeagueRankingRewardList where startAt is null
        defaultMLeagueRankingRewardShouldNotBeFound("startAt.specified=false");
    }

    @Test
    @Transactional
    public void getAllMLeagueRankingRewardsByStartAtIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mLeagueRankingRewardRepository.saveAndFlush(mLeagueRankingReward);

        // Get all the mLeagueRankingRewardList where startAt greater than or equals to DEFAULT_START_AT
        defaultMLeagueRankingRewardShouldBeFound("startAt.greaterOrEqualThan=" + DEFAULT_START_AT);

        // Get all the mLeagueRankingRewardList where startAt greater than or equals to UPDATED_START_AT
        defaultMLeagueRankingRewardShouldNotBeFound("startAt.greaterOrEqualThan=" + UPDATED_START_AT);
    }

    @Test
    @Transactional
    public void getAllMLeagueRankingRewardsByStartAtIsLessThanSomething() throws Exception {
        // Initialize the database
        mLeagueRankingRewardRepository.saveAndFlush(mLeagueRankingReward);

        // Get all the mLeagueRankingRewardList where startAt less than or equals to DEFAULT_START_AT
        defaultMLeagueRankingRewardShouldNotBeFound("startAt.lessThan=" + DEFAULT_START_AT);

        // Get all the mLeagueRankingRewardList where startAt less than or equals to UPDATED_START_AT
        defaultMLeagueRankingRewardShouldBeFound("startAt.lessThan=" + UPDATED_START_AT);
    }


    @Test
    @Transactional
    public void getAllMLeagueRankingRewardsByEndAtIsEqualToSomething() throws Exception {
        // Initialize the database
        mLeagueRankingRewardRepository.saveAndFlush(mLeagueRankingReward);

        // Get all the mLeagueRankingRewardList where endAt equals to DEFAULT_END_AT
        defaultMLeagueRankingRewardShouldBeFound("endAt.equals=" + DEFAULT_END_AT);

        // Get all the mLeagueRankingRewardList where endAt equals to UPDATED_END_AT
        defaultMLeagueRankingRewardShouldNotBeFound("endAt.equals=" + UPDATED_END_AT);
    }

    @Test
    @Transactional
    public void getAllMLeagueRankingRewardsByEndAtIsInShouldWork() throws Exception {
        // Initialize the database
        mLeagueRankingRewardRepository.saveAndFlush(mLeagueRankingReward);

        // Get all the mLeagueRankingRewardList where endAt in DEFAULT_END_AT or UPDATED_END_AT
        defaultMLeagueRankingRewardShouldBeFound("endAt.in=" + DEFAULT_END_AT + "," + UPDATED_END_AT);

        // Get all the mLeagueRankingRewardList where endAt equals to UPDATED_END_AT
        defaultMLeagueRankingRewardShouldNotBeFound("endAt.in=" + UPDATED_END_AT);
    }

    @Test
    @Transactional
    public void getAllMLeagueRankingRewardsByEndAtIsNullOrNotNull() throws Exception {
        // Initialize the database
        mLeagueRankingRewardRepository.saveAndFlush(mLeagueRankingReward);

        // Get all the mLeagueRankingRewardList where endAt is not null
        defaultMLeagueRankingRewardShouldBeFound("endAt.specified=true");

        // Get all the mLeagueRankingRewardList where endAt is null
        defaultMLeagueRankingRewardShouldNotBeFound("endAt.specified=false");
    }

    @Test
    @Transactional
    public void getAllMLeagueRankingRewardsByEndAtIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mLeagueRankingRewardRepository.saveAndFlush(mLeagueRankingReward);

        // Get all the mLeagueRankingRewardList where endAt greater than or equals to DEFAULT_END_AT
        defaultMLeagueRankingRewardShouldBeFound("endAt.greaterOrEqualThan=" + DEFAULT_END_AT);

        // Get all the mLeagueRankingRewardList where endAt greater than or equals to UPDATED_END_AT
        defaultMLeagueRankingRewardShouldNotBeFound("endAt.greaterOrEqualThan=" + UPDATED_END_AT);
    }

    @Test
    @Transactional
    public void getAllMLeagueRankingRewardsByEndAtIsLessThanSomething() throws Exception {
        // Initialize the database
        mLeagueRankingRewardRepository.saveAndFlush(mLeagueRankingReward);

        // Get all the mLeagueRankingRewardList where endAt less than or equals to DEFAULT_END_AT
        defaultMLeagueRankingRewardShouldNotBeFound("endAt.lessThan=" + DEFAULT_END_AT);

        // Get all the mLeagueRankingRewardList where endAt less than or equals to UPDATED_END_AT
        defaultMLeagueRankingRewardShouldBeFound("endAt.lessThan=" + UPDATED_END_AT);
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultMLeagueRankingRewardShouldBeFound(String filter) throws Exception {
        restMLeagueRankingRewardMockMvc.perform(get("/api/m-league-ranking-rewards?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(mLeagueRankingReward.getId().intValue())))
            .andExpect(jsonPath("$.[*].leagueHierarchy").value(hasItem(DEFAULT_LEAGUE_HIERARCHY)))
            .andExpect(jsonPath("$.[*].rankTo").value(hasItem(DEFAULT_RANK_TO)))
            .andExpect(jsonPath("$.[*].rewardGroupId").value(hasItem(DEFAULT_REWARD_GROUP_ID)))
            .andExpect(jsonPath("$.[*].startAt").value(hasItem(DEFAULT_START_AT)))
            .andExpect(jsonPath("$.[*].endAt").value(hasItem(DEFAULT_END_AT)));

        // Check, that the count call also returns 1
        restMLeagueRankingRewardMockMvc.perform(get("/api/m-league-ranking-rewards/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultMLeagueRankingRewardShouldNotBeFound(String filter) throws Exception {
        restMLeagueRankingRewardMockMvc.perform(get("/api/m-league-ranking-rewards?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restMLeagueRankingRewardMockMvc.perform(get("/api/m-league-ranking-rewards/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("0"));
    }


    @Test
    @Transactional
    public void getNonExistingMLeagueRankingReward() throws Exception {
        // Get the mLeagueRankingReward
        restMLeagueRankingRewardMockMvc.perform(get("/api/m-league-ranking-rewards/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateMLeagueRankingReward() throws Exception {
        // Initialize the database
        mLeagueRankingRewardRepository.saveAndFlush(mLeagueRankingReward);

        int databaseSizeBeforeUpdate = mLeagueRankingRewardRepository.findAll().size();

        // Update the mLeagueRankingReward
        MLeagueRankingReward updatedMLeagueRankingReward = mLeagueRankingRewardRepository.findById(mLeagueRankingReward.getId()).get();
        // Disconnect from session so that the updates on updatedMLeagueRankingReward are not directly saved in db
        em.detach(updatedMLeagueRankingReward);
        updatedMLeagueRankingReward
            .leagueHierarchy(UPDATED_LEAGUE_HIERARCHY)
            .rankTo(UPDATED_RANK_TO)
            .rewardGroupId(UPDATED_REWARD_GROUP_ID)
            .startAt(UPDATED_START_AT)
            .endAt(UPDATED_END_AT);
        MLeagueRankingRewardDTO mLeagueRankingRewardDTO = mLeagueRankingRewardMapper.toDto(updatedMLeagueRankingReward);

        restMLeagueRankingRewardMockMvc.perform(put("/api/m-league-ranking-rewards")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mLeagueRankingRewardDTO)))
            .andExpect(status().isOk());

        // Validate the MLeagueRankingReward in the database
        List<MLeagueRankingReward> mLeagueRankingRewardList = mLeagueRankingRewardRepository.findAll();
        assertThat(mLeagueRankingRewardList).hasSize(databaseSizeBeforeUpdate);
        MLeagueRankingReward testMLeagueRankingReward = mLeagueRankingRewardList.get(mLeagueRankingRewardList.size() - 1);
        assertThat(testMLeagueRankingReward.getLeagueHierarchy()).isEqualTo(UPDATED_LEAGUE_HIERARCHY);
        assertThat(testMLeagueRankingReward.getRankTo()).isEqualTo(UPDATED_RANK_TO);
        assertThat(testMLeagueRankingReward.getRewardGroupId()).isEqualTo(UPDATED_REWARD_GROUP_ID);
        assertThat(testMLeagueRankingReward.getStartAt()).isEqualTo(UPDATED_START_AT);
        assertThat(testMLeagueRankingReward.getEndAt()).isEqualTo(UPDATED_END_AT);
    }

    @Test
    @Transactional
    public void updateNonExistingMLeagueRankingReward() throws Exception {
        int databaseSizeBeforeUpdate = mLeagueRankingRewardRepository.findAll().size();

        // Create the MLeagueRankingReward
        MLeagueRankingRewardDTO mLeagueRankingRewardDTO = mLeagueRankingRewardMapper.toDto(mLeagueRankingReward);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restMLeagueRankingRewardMockMvc.perform(put("/api/m-league-ranking-rewards")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mLeagueRankingRewardDTO)))
            .andExpect(status().isBadRequest());

        // Validate the MLeagueRankingReward in the database
        List<MLeagueRankingReward> mLeagueRankingRewardList = mLeagueRankingRewardRepository.findAll();
        assertThat(mLeagueRankingRewardList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteMLeagueRankingReward() throws Exception {
        // Initialize the database
        mLeagueRankingRewardRepository.saveAndFlush(mLeagueRankingReward);

        int databaseSizeBeforeDelete = mLeagueRankingRewardRepository.findAll().size();

        // Delete the mLeagueRankingReward
        restMLeagueRankingRewardMockMvc.perform(delete("/api/m-league-ranking-rewards/{id}", mLeagueRankingReward.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database is empty
        List<MLeagueRankingReward> mLeagueRankingRewardList = mLeagueRankingRewardRepository.findAll();
        assertThat(mLeagueRankingRewardList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(MLeagueRankingReward.class);
        MLeagueRankingReward mLeagueRankingReward1 = new MLeagueRankingReward();
        mLeagueRankingReward1.setId(1L);
        MLeagueRankingReward mLeagueRankingReward2 = new MLeagueRankingReward();
        mLeagueRankingReward2.setId(mLeagueRankingReward1.getId());
        assertThat(mLeagueRankingReward1).isEqualTo(mLeagueRankingReward2);
        mLeagueRankingReward2.setId(2L);
        assertThat(mLeagueRankingReward1).isNotEqualTo(mLeagueRankingReward2);
        mLeagueRankingReward1.setId(null);
        assertThat(mLeagueRankingReward1).isNotEqualTo(mLeagueRankingReward2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(MLeagueRankingRewardDTO.class);
        MLeagueRankingRewardDTO mLeagueRankingRewardDTO1 = new MLeagueRankingRewardDTO();
        mLeagueRankingRewardDTO1.setId(1L);
        MLeagueRankingRewardDTO mLeagueRankingRewardDTO2 = new MLeagueRankingRewardDTO();
        assertThat(mLeagueRankingRewardDTO1).isNotEqualTo(mLeagueRankingRewardDTO2);
        mLeagueRankingRewardDTO2.setId(mLeagueRankingRewardDTO1.getId());
        assertThat(mLeagueRankingRewardDTO1).isEqualTo(mLeagueRankingRewardDTO2);
        mLeagueRankingRewardDTO2.setId(2L);
        assertThat(mLeagueRankingRewardDTO1).isNotEqualTo(mLeagueRankingRewardDTO2);
        mLeagueRankingRewardDTO1.setId(null);
        assertThat(mLeagueRankingRewardDTO1).isNotEqualTo(mLeagueRankingRewardDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(mLeagueRankingRewardMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(mLeagueRankingRewardMapper.fromId(null)).isNull();
    }
}
