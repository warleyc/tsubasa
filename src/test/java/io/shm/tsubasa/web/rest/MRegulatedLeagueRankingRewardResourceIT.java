package io.shm.tsubasa.web.rest;

import io.shm.tsubasa.TsubasaApp;
import io.shm.tsubasa.domain.MRegulatedLeagueRankingReward;
import io.shm.tsubasa.repository.MRegulatedLeagueRankingRewardRepository;
import io.shm.tsubasa.service.MRegulatedLeagueRankingRewardService;
import io.shm.tsubasa.service.dto.MRegulatedLeagueRankingRewardDTO;
import io.shm.tsubasa.service.mapper.MRegulatedLeagueRankingRewardMapper;
import io.shm.tsubasa.web.rest.errors.ExceptionTranslator;
import io.shm.tsubasa.service.dto.MRegulatedLeagueRankingRewardCriteria;
import io.shm.tsubasa.service.MRegulatedLeagueRankingRewardQueryService;

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
 * Integration tests for the {@Link MRegulatedLeagueRankingRewardResource} REST controller.
 */
@SpringBootTest(classes = TsubasaApp.class)
public class MRegulatedLeagueRankingRewardResourceIT {

    private static final Integer DEFAULT_REGULATED_LEAGUE_ID = 1;
    private static final Integer UPDATED_REGULATED_LEAGUE_ID = 2;

    private static final Integer DEFAULT_LEAGUE_HIERARCHY = 1;
    private static final Integer UPDATED_LEAGUE_HIERARCHY = 2;

    private static final Integer DEFAULT_RANK_TO = 1;
    private static final Integer UPDATED_RANK_TO = 2;

    private static final Integer DEFAULT_REWARD_GROUP_ID = 1;
    private static final Integer UPDATED_REWARD_GROUP_ID = 2;

    @Autowired
    private MRegulatedLeagueRankingRewardRepository mRegulatedLeagueRankingRewardRepository;

    @Autowired
    private MRegulatedLeagueRankingRewardMapper mRegulatedLeagueRankingRewardMapper;

    @Autowired
    private MRegulatedLeagueRankingRewardService mRegulatedLeagueRankingRewardService;

    @Autowired
    private MRegulatedLeagueRankingRewardQueryService mRegulatedLeagueRankingRewardQueryService;

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

    private MockMvc restMRegulatedLeagueRankingRewardMockMvc;

    private MRegulatedLeagueRankingReward mRegulatedLeagueRankingReward;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final MRegulatedLeagueRankingRewardResource mRegulatedLeagueRankingRewardResource = new MRegulatedLeagueRankingRewardResource(mRegulatedLeagueRankingRewardService, mRegulatedLeagueRankingRewardQueryService);
        this.restMRegulatedLeagueRankingRewardMockMvc = MockMvcBuilders.standaloneSetup(mRegulatedLeagueRankingRewardResource)
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
    public static MRegulatedLeagueRankingReward createEntity(EntityManager em) {
        MRegulatedLeagueRankingReward mRegulatedLeagueRankingReward = new MRegulatedLeagueRankingReward()
            .regulatedLeagueId(DEFAULT_REGULATED_LEAGUE_ID)
            .leagueHierarchy(DEFAULT_LEAGUE_HIERARCHY)
            .rankTo(DEFAULT_RANK_TO)
            .rewardGroupId(DEFAULT_REWARD_GROUP_ID);
        return mRegulatedLeagueRankingReward;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static MRegulatedLeagueRankingReward createUpdatedEntity(EntityManager em) {
        MRegulatedLeagueRankingReward mRegulatedLeagueRankingReward = new MRegulatedLeagueRankingReward()
            .regulatedLeagueId(UPDATED_REGULATED_LEAGUE_ID)
            .leagueHierarchy(UPDATED_LEAGUE_HIERARCHY)
            .rankTo(UPDATED_RANK_TO)
            .rewardGroupId(UPDATED_REWARD_GROUP_ID);
        return mRegulatedLeagueRankingReward;
    }

    @BeforeEach
    public void initTest() {
        mRegulatedLeagueRankingReward = createEntity(em);
    }

    @Test
    @Transactional
    public void createMRegulatedLeagueRankingReward() throws Exception {
        int databaseSizeBeforeCreate = mRegulatedLeagueRankingRewardRepository.findAll().size();

        // Create the MRegulatedLeagueRankingReward
        MRegulatedLeagueRankingRewardDTO mRegulatedLeagueRankingRewardDTO = mRegulatedLeagueRankingRewardMapper.toDto(mRegulatedLeagueRankingReward);
        restMRegulatedLeagueRankingRewardMockMvc.perform(post("/api/m-regulated-league-ranking-rewards")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mRegulatedLeagueRankingRewardDTO)))
            .andExpect(status().isCreated());

        // Validate the MRegulatedLeagueRankingReward in the database
        List<MRegulatedLeagueRankingReward> mRegulatedLeagueRankingRewardList = mRegulatedLeagueRankingRewardRepository.findAll();
        assertThat(mRegulatedLeagueRankingRewardList).hasSize(databaseSizeBeforeCreate + 1);
        MRegulatedLeagueRankingReward testMRegulatedLeagueRankingReward = mRegulatedLeagueRankingRewardList.get(mRegulatedLeagueRankingRewardList.size() - 1);
        assertThat(testMRegulatedLeagueRankingReward.getRegulatedLeagueId()).isEqualTo(DEFAULT_REGULATED_LEAGUE_ID);
        assertThat(testMRegulatedLeagueRankingReward.getLeagueHierarchy()).isEqualTo(DEFAULT_LEAGUE_HIERARCHY);
        assertThat(testMRegulatedLeagueRankingReward.getRankTo()).isEqualTo(DEFAULT_RANK_TO);
        assertThat(testMRegulatedLeagueRankingReward.getRewardGroupId()).isEqualTo(DEFAULT_REWARD_GROUP_ID);
    }

    @Test
    @Transactional
    public void createMRegulatedLeagueRankingRewardWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = mRegulatedLeagueRankingRewardRepository.findAll().size();

        // Create the MRegulatedLeagueRankingReward with an existing ID
        mRegulatedLeagueRankingReward.setId(1L);
        MRegulatedLeagueRankingRewardDTO mRegulatedLeagueRankingRewardDTO = mRegulatedLeagueRankingRewardMapper.toDto(mRegulatedLeagueRankingReward);

        // An entity with an existing ID cannot be created, so this API call must fail
        restMRegulatedLeagueRankingRewardMockMvc.perform(post("/api/m-regulated-league-ranking-rewards")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mRegulatedLeagueRankingRewardDTO)))
            .andExpect(status().isBadRequest());

        // Validate the MRegulatedLeagueRankingReward in the database
        List<MRegulatedLeagueRankingReward> mRegulatedLeagueRankingRewardList = mRegulatedLeagueRankingRewardRepository.findAll();
        assertThat(mRegulatedLeagueRankingRewardList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkRegulatedLeagueIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = mRegulatedLeagueRankingRewardRepository.findAll().size();
        // set the field null
        mRegulatedLeagueRankingReward.setRegulatedLeagueId(null);

        // Create the MRegulatedLeagueRankingReward, which fails.
        MRegulatedLeagueRankingRewardDTO mRegulatedLeagueRankingRewardDTO = mRegulatedLeagueRankingRewardMapper.toDto(mRegulatedLeagueRankingReward);

        restMRegulatedLeagueRankingRewardMockMvc.perform(post("/api/m-regulated-league-ranking-rewards")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mRegulatedLeagueRankingRewardDTO)))
            .andExpect(status().isBadRequest());

        List<MRegulatedLeagueRankingReward> mRegulatedLeagueRankingRewardList = mRegulatedLeagueRankingRewardRepository.findAll();
        assertThat(mRegulatedLeagueRankingRewardList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkLeagueHierarchyIsRequired() throws Exception {
        int databaseSizeBeforeTest = mRegulatedLeagueRankingRewardRepository.findAll().size();
        // set the field null
        mRegulatedLeagueRankingReward.setLeagueHierarchy(null);

        // Create the MRegulatedLeagueRankingReward, which fails.
        MRegulatedLeagueRankingRewardDTO mRegulatedLeagueRankingRewardDTO = mRegulatedLeagueRankingRewardMapper.toDto(mRegulatedLeagueRankingReward);

        restMRegulatedLeagueRankingRewardMockMvc.perform(post("/api/m-regulated-league-ranking-rewards")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mRegulatedLeagueRankingRewardDTO)))
            .andExpect(status().isBadRequest());

        List<MRegulatedLeagueRankingReward> mRegulatedLeagueRankingRewardList = mRegulatedLeagueRankingRewardRepository.findAll();
        assertThat(mRegulatedLeagueRankingRewardList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkRankToIsRequired() throws Exception {
        int databaseSizeBeforeTest = mRegulatedLeagueRankingRewardRepository.findAll().size();
        // set the field null
        mRegulatedLeagueRankingReward.setRankTo(null);

        // Create the MRegulatedLeagueRankingReward, which fails.
        MRegulatedLeagueRankingRewardDTO mRegulatedLeagueRankingRewardDTO = mRegulatedLeagueRankingRewardMapper.toDto(mRegulatedLeagueRankingReward);

        restMRegulatedLeagueRankingRewardMockMvc.perform(post("/api/m-regulated-league-ranking-rewards")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mRegulatedLeagueRankingRewardDTO)))
            .andExpect(status().isBadRequest());

        List<MRegulatedLeagueRankingReward> mRegulatedLeagueRankingRewardList = mRegulatedLeagueRankingRewardRepository.findAll();
        assertThat(mRegulatedLeagueRankingRewardList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkRewardGroupIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = mRegulatedLeagueRankingRewardRepository.findAll().size();
        // set the field null
        mRegulatedLeagueRankingReward.setRewardGroupId(null);

        // Create the MRegulatedLeagueRankingReward, which fails.
        MRegulatedLeagueRankingRewardDTO mRegulatedLeagueRankingRewardDTO = mRegulatedLeagueRankingRewardMapper.toDto(mRegulatedLeagueRankingReward);

        restMRegulatedLeagueRankingRewardMockMvc.perform(post("/api/m-regulated-league-ranking-rewards")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mRegulatedLeagueRankingRewardDTO)))
            .andExpect(status().isBadRequest());

        List<MRegulatedLeagueRankingReward> mRegulatedLeagueRankingRewardList = mRegulatedLeagueRankingRewardRepository.findAll();
        assertThat(mRegulatedLeagueRankingRewardList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllMRegulatedLeagueRankingRewards() throws Exception {
        // Initialize the database
        mRegulatedLeagueRankingRewardRepository.saveAndFlush(mRegulatedLeagueRankingReward);

        // Get all the mRegulatedLeagueRankingRewardList
        restMRegulatedLeagueRankingRewardMockMvc.perform(get("/api/m-regulated-league-ranking-rewards?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(mRegulatedLeagueRankingReward.getId().intValue())))
            .andExpect(jsonPath("$.[*].regulatedLeagueId").value(hasItem(DEFAULT_REGULATED_LEAGUE_ID)))
            .andExpect(jsonPath("$.[*].leagueHierarchy").value(hasItem(DEFAULT_LEAGUE_HIERARCHY)))
            .andExpect(jsonPath("$.[*].rankTo").value(hasItem(DEFAULT_RANK_TO)))
            .andExpect(jsonPath("$.[*].rewardGroupId").value(hasItem(DEFAULT_REWARD_GROUP_ID)));
    }
    
    @Test
    @Transactional
    public void getMRegulatedLeagueRankingReward() throws Exception {
        // Initialize the database
        mRegulatedLeagueRankingRewardRepository.saveAndFlush(mRegulatedLeagueRankingReward);

        // Get the mRegulatedLeagueRankingReward
        restMRegulatedLeagueRankingRewardMockMvc.perform(get("/api/m-regulated-league-ranking-rewards/{id}", mRegulatedLeagueRankingReward.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(mRegulatedLeagueRankingReward.getId().intValue()))
            .andExpect(jsonPath("$.regulatedLeagueId").value(DEFAULT_REGULATED_LEAGUE_ID))
            .andExpect(jsonPath("$.leagueHierarchy").value(DEFAULT_LEAGUE_HIERARCHY))
            .andExpect(jsonPath("$.rankTo").value(DEFAULT_RANK_TO))
            .andExpect(jsonPath("$.rewardGroupId").value(DEFAULT_REWARD_GROUP_ID));
    }

    @Test
    @Transactional
    public void getAllMRegulatedLeagueRankingRewardsByRegulatedLeagueIdIsEqualToSomething() throws Exception {
        // Initialize the database
        mRegulatedLeagueRankingRewardRepository.saveAndFlush(mRegulatedLeagueRankingReward);

        // Get all the mRegulatedLeagueRankingRewardList where regulatedLeagueId equals to DEFAULT_REGULATED_LEAGUE_ID
        defaultMRegulatedLeagueRankingRewardShouldBeFound("regulatedLeagueId.equals=" + DEFAULT_REGULATED_LEAGUE_ID);

        // Get all the mRegulatedLeagueRankingRewardList where regulatedLeagueId equals to UPDATED_REGULATED_LEAGUE_ID
        defaultMRegulatedLeagueRankingRewardShouldNotBeFound("regulatedLeagueId.equals=" + UPDATED_REGULATED_LEAGUE_ID);
    }

    @Test
    @Transactional
    public void getAllMRegulatedLeagueRankingRewardsByRegulatedLeagueIdIsInShouldWork() throws Exception {
        // Initialize the database
        mRegulatedLeagueRankingRewardRepository.saveAndFlush(mRegulatedLeagueRankingReward);

        // Get all the mRegulatedLeagueRankingRewardList where regulatedLeagueId in DEFAULT_REGULATED_LEAGUE_ID or UPDATED_REGULATED_LEAGUE_ID
        defaultMRegulatedLeagueRankingRewardShouldBeFound("regulatedLeagueId.in=" + DEFAULT_REGULATED_LEAGUE_ID + "," + UPDATED_REGULATED_LEAGUE_ID);

        // Get all the mRegulatedLeagueRankingRewardList where regulatedLeagueId equals to UPDATED_REGULATED_LEAGUE_ID
        defaultMRegulatedLeagueRankingRewardShouldNotBeFound("regulatedLeagueId.in=" + UPDATED_REGULATED_LEAGUE_ID);
    }

    @Test
    @Transactional
    public void getAllMRegulatedLeagueRankingRewardsByRegulatedLeagueIdIsNullOrNotNull() throws Exception {
        // Initialize the database
        mRegulatedLeagueRankingRewardRepository.saveAndFlush(mRegulatedLeagueRankingReward);

        // Get all the mRegulatedLeagueRankingRewardList where regulatedLeagueId is not null
        defaultMRegulatedLeagueRankingRewardShouldBeFound("regulatedLeagueId.specified=true");

        // Get all the mRegulatedLeagueRankingRewardList where regulatedLeagueId is null
        defaultMRegulatedLeagueRankingRewardShouldNotBeFound("regulatedLeagueId.specified=false");
    }

    @Test
    @Transactional
    public void getAllMRegulatedLeagueRankingRewardsByRegulatedLeagueIdIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mRegulatedLeagueRankingRewardRepository.saveAndFlush(mRegulatedLeagueRankingReward);

        // Get all the mRegulatedLeagueRankingRewardList where regulatedLeagueId greater than or equals to DEFAULT_REGULATED_LEAGUE_ID
        defaultMRegulatedLeagueRankingRewardShouldBeFound("regulatedLeagueId.greaterOrEqualThan=" + DEFAULT_REGULATED_LEAGUE_ID);

        // Get all the mRegulatedLeagueRankingRewardList where regulatedLeagueId greater than or equals to UPDATED_REGULATED_LEAGUE_ID
        defaultMRegulatedLeagueRankingRewardShouldNotBeFound("regulatedLeagueId.greaterOrEqualThan=" + UPDATED_REGULATED_LEAGUE_ID);
    }

    @Test
    @Transactional
    public void getAllMRegulatedLeagueRankingRewardsByRegulatedLeagueIdIsLessThanSomething() throws Exception {
        // Initialize the database
        mRegulatedLeagueRankingRewardRepository.saveAndFlush(mRegulatedLeagueRankingReward);

        // Get all the mRegulatedLeagueRankingRewardList where regulatedLeagueId less than or equals to DEFAULT_REGULATED_LEAGUE_ID
        defaultMRegulatedLeagueRankingRewardShouldNotBeFound("regulatedLeagueId.lessThan=" + DEFAULT_REGULATED_LEAGUE_ID);

        // Get all the mRegulatedLeagueRankingRewardList where regulatedLeagueId less than or equals to UPDATED_REGULATED_LEAGUE_ID
        defaultMRegulatedLeagueRankingRewardShouldBeFound("regulatedLeagueId.lessThan=" + UPDATED_REGULATED_LEAGUE_ID);
    }


    @Test
    @Transactional
    public void getAllMRegulatedLeagueRankingRewardsByLeagueHierarchyIsEqualToSomething() throws Exception {
        // Initialize the database
        mRegulatedLeagueRankingRewardRepository.saveAndFlush(mRegulatedLeagueRankingReward);

        // Get all the mRegulatedLeagueRankingRewardList where leagueHierarchy equals to DEFAULT_LEAGUE_HIERARCHY
        defaultMRegulatedLeagueRankingRewardShouldBeFound("leagueHierarchy.equals=" + DEFAULT_LEAGUE_HIERARCHY);

        // Get all the mRegulatedLeagueRankingRewardList where leagueHierarchy equals to UPDATED_LEAGUE_HIERARCHY
        defaultMRegulatedLeagueRankingRewardShouldNotBeFound("leagueHierarchy.equals=" + UPDATED_LEAGUE_HIERARCHY);
    }

    @Test
    @Transactional
    public void getAllMRegulatedLeagueRankingRewardsByLeagueHierarchyIsInShouldWork() throws Exception {
        // Initialize the database
        mRegulatedLeagueRankingRewardRepository.saveAndFlush(mRegulatedLeagueRankingReward);

        // Get all the mRegulatedLeagueRankingRewardList where leagueHierarchy in DEFAULT_LEAGUE_HIERARCHY or UPDATED_LEAGUE_HIERARCHY
        defaultMRegulatedLeagueRankingRewardShouldBeFound("leagueHierarchy.in=" + DEFAULT_LEAGUE_HIERARCHY + "," + UPDATED_LEAGUE_HIERARCHY);

        // Get all the mRegulatedLeagueRankingRewardList where leagueHierarchy equals to UPDATED_LEAGUE_HIERARCHY
        defaultMRegulatedLeagueRankingRewardShouldNotBeFound("leagueHierarchy.in=" + UPDATED_LEAGUE_HIERARCHY);
    }

    @Test
    @Transactional
    public void getAllMRegulatedLeagueRankingRewardsByLeagueHierarchyIsNullOrNotNull() throws Exception {
        // Initialize the database
        mRegulatedLeagueRankingRewardRepository.saveAndFlush(mRegulatedLeagueRankingReward);

        // Get all the mRegulatedLeagueRankingRewardList where leagueHierarchy is not null
        defaultMRegulatedLeagueRankingRewardShouldBeFound("leagueHierarchy.specified=true");

        // Get all the mRegulatedLeagueRankingRewardList where leagueHierarchy is null
        defaultMRegulatedLeagueRankingRewardShouldNotBeFound("leagueHierarchy.specified=false");
    }

    @Test
    @Transactional
    public void getAllMRegulatedLeagueRankingRewardsByLeagueHierarchyIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mRegulatedLeagueRankingRewardRepository.saveAndFlush(mRegulatedLeagueRankingReward);

        // Get all the mRegulatedLeagueRankingRewardList where leagueHierarchy greater than or equals to DEFAULT_LEAGUE_HIERARCHY
        defaultMRegulatedLeagueRankingRewardShouldBeFound("leagueHierarchy.greaterOrEqualThan=" + DEFAULT_LEAGUE_HIERARCHY);

        // Get all the mRegulatedLeagueRankingRewardList where leagueHierarchy greater than or equals to UPDATED_LEAGUE_HIERARCHY
        defaultMRegulatedLeagueRankingRewardShouldNotBeFound("leagueHierarchy.greaterOrEqualThan=" + UPDATED_LEAGUE_HIERARCHY);
    }

    @Test
    @Transactional
    public void getAllMRegulatedLeagueRankingRewardsByLeagueHierarchyIsLessThanSomething() throws Exception {
        // Initialize the database
        mRegulatedLeagueRankingRewardRepository.saveAndFlush(mRegulatedLeagueRankingReward);

        // Get all the mRegulatedLeagueRankingRewardList where leagueHierarchy less than or equals to DEFAULT_LEAGUE_HIERARCHY
        defaultMRegulatedLeagueRankingRewardShouldNotBeFound("leagueHierarchy.lessThan=" + DEFAULT_LEAGUE_HIERARCHY);

        // Get all the mRegulatedLeagueRankingRewardList where leagueHierarchy less than or equals to UPDATED_LEAGUE_HIERARCHY
        defaultMRegulatedLeagueRankingRewardShouldBeFound("leagueHierarchy.lessThan=" + UPDATED_LEAGUE_HIERARCHY);
    }


    @Test
    @Transactional
    public void getAllMRegulatedLeagueRankingRewardsByRankToIsEqualToSomething() throws Exception {
        // Initialize the database
        mRegulatedLeagueRankingRewardRepository.saveAndFlush(mRegulatedLeagueRankingReward);

        // Get all the mRegulatedLeagueRankingRewardList where rankTo equals to DEFAULT_RANK_TO
        defaultMRegulatedLeagueRankingRewardShouldBeFound("rankTo.equals=" + DEFAULT_RANK_TO);

        // Get all the mRegulatedLeagueRankingRewardList where rankTo equals to UPDATED_RANK_TO
        defaultMRegulatedLeagueRankingRewardShouldNotBeFound("rankTo.equals=" + UPDATED_RANK_TO);
    }

    @Test
    @Transactional
    public void getAllMRegulatedLeagueRankingRewardsByRankToIsInShouldWork() throws Exception {
        // Initialize the database
        mRegulatedLeagueRankingRewardRepository.saveAndFlush(mRegulatedLeagueRankingReward);

        // Get all the mRegulatedLeagueRankingRewardList where rankTo in DEFAULT_RANK_TO or UPDATED_RANK_TO
        defaultMRegulatedLeagueRankingRewardShouldBeFound("rankTo.in=" + DEFAULT_RANK_TO + "," + UPDATED_RANK_TO);

        // Get all the mRegulatedLeagueRankingRewardList where rankTo equals to UPDATED_RANK_TO
        defaultMRegulatedLeagueRankingRewardShouldNotBeFound("rankTo.in=" + UPDATED_RANK_TO);
    }

    @Test
    @Transactional
    public void getAllMRegulatedLeagueRankingRewardsByRankToIsNullOrNotNull() throws Exception {
        // Initialize the database
        mRegulatedLeagueRankingRewardRepository.saveAndFlush(mRegulatedLeagueRankingReward);

        // Get all the mRegulatedLeagueRankingRewardList where rankTo is not null
        defaultMRegulatedLeagueRankingRewardShouldBeFound("rankTo.specified=true");

        // Get all the mRegulatedLeagueRankingRewardList where rankTo is null
        defaultMRegulatedLeagueRankingRewardShouldNotBeFound("rankTo.specified=false");
    }

    @Test
    @Transactional
    public void getAllMRegulatedLeagueRankingRewardsByRankToIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mRegulatedLeagueRankingRewardRepository.saveAndFlush(mRegulatedLeagueRankingReward);

        // Get all the mRegulatedLeagueRankingRewardList where rankTo greater than or equals to DEFAULT_RANK_TO
        defaultMRegulatedLeagueRankingRewardShouldBeFound("rankTo.greaterOrEqualThan=" + DEFAULT_RANK_TO);

        // Get all the mRegulatedLeagueRankingRewardList where rankTo greater than or equals to UPDATED_RANK_TO
        defaultMRegulatedLeagueRankingRewardShouldNotBeFound("rankTo.greaterOrEqualThan=" + UPDATED_RANK_TO);
    }

    @Test
    @Transactional
    public void getAllMRegulatedLeagueRankingRewardsByRankToIsLessThanSomething() throws Exception {
        // Initialize the database
        mRegulatedLeagueRankingRewardRepository.saveAndFlush(mRegulatedLeagueRankingReward);

        // Get all the mRegulatedLeagueRankingRewardList where rankTo less than or equals to DEFAULT_RANK_TO
        defaultMRegulatedLeagueRankingRewardShouldNotBeFound("rankTo.lessThan=" + DEFAULT_RANK_TO);

        // Get all the mRegulatedLeagueRankingRewardList where rankTo less than or equals to UPDATED_RANK_TO
        defaultMRegulatedLeagueRankingRewardShouldBeFound("rankTo.lessThan=" + UPDATED_RANK_TO);
    }


    @Test
    @Transactional
    public void getAllMRegulatedLeagueRankingRewardsByRewardGroupIdIsEqualToSomething() throws Exception {
        // Initialize the database
        mRegulatedLeagueRankingRewardRepository.saveAndFlush(mRegulatedLeagueRankingReward);

        // Get all the mRegulatedLeagueRankingRewardList where rewardGroupId equals to DEFAULT_REWARD_GROUP_ID
        defaultMRegulatedLeagueRankingRewardShouldBeFound("rewardGroupId.equals=" + DEFAULT_REWARD_GROUP_ID);

        // Get all the mRegulatedLeagueRankingRewardList where rewardGroupId equals to UPDATED_REWARD_GROUP_ID
        defaultMRegulatedLeagueRankingRewardShouldNotBeFound("rewardGroupId.equals=" + UPDATED_REWARD_GROUP_ID);
    }

    @Test
    @Transactional
    public void getAllMRegulatedLeagueRankingRewardsByRewardGroupIdIsInShouldWork() throws Exception {
        // Initialize the database
        mRegulatedLeagueRankingRewardRepository.saveAndFlush(mRegulatedLeagueRankingReward);

        // Get all the mRegulatedLeagueRankingRewardList where rewardGroupId in DEFAULT_REWARD_GROUP_ID or UPDATED_REWARD_GROUP_ID
        defaultMRegulatedLeagueRankingRewardShouldBeFound("rewardGroupId.in=" + DEFAULT_REWARD_GROUP_ID + "," + UPDATED_REWARD_GROUP_ID);

        // Get all the mRegulatedLeagueRankingRewardList where rewardGroupId equals to UPDATED_REWARD_GROUP_ID
        defaultMRegulatedLeagueRankingRewardShouldNotBeFound("rewardGroupId.in=" + UPDATED_REWARD_GROUP_ID);
    }

    @Test
    @Transactional
    public void getAllMRegulatedLeagueRankingRewardsByRewardGroupIdIsNullOrNotNull() throws Exception {
        // Initialize the database
        mRegulatedLeagueRankingRewardRepository.saveAndFlush(mRegulatedLeagueRankingReward);

        // Get all the mRegulatedLeagueRankingRewardList where rewardGroupId is not null
        defaultMRegulatedLeagueRankingRewardShouldBeFound("rewardGroupId.specified=true");

        // Get all the mRegulatedLeagueRankingRewardList where rewardGroupId is null
        defaultMRegulatedLeagueRankingRewardShouldNotBeFound("rewardGroupId.specified=false");
    }

    @Test
    @Transactional
    public void getAllMRegulatedLeagueRankingRewardsByRewardGroupIdIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mRegulatedLeagueRankingRewardRepository.saveAndFlush(mRegulatedLeagueRankingReward);

        // Get all the mRegulatedLeagueRankingRewardList where rewardGroupId greater than or equals to DEFAULT_REWARD_GROUP_ID
        defaultMRegulatedLeagueRankingRewardShouldBeFound("rewardGroupId.greaterOrEqualThan=" + DEFAULT_REWARD_GROUP_ID);

        // Get all the mRegulatedLeagueRankingRewardList where rewardGroupId greater than or equals to UPDATED_REWARD_GROUP_ID
        defaultMRegulatedLeagueRankingRewardShouldNotBeFound("rewardGroupId.greaterOrEqualThan=" + UPDATED_REWARD_GROUP_ID);
    }

    @Test
    @Transactional
    public void getAllMRegulatedLeagueRankingRewardsByRewardGroupIdIsLessThanSomething() throws Exception {
        // Initialize the database
        mRegulatedLeagueRankingRewardRepository.saveAndFlush(mRegulatedLeagueRankingReward);

        // Get all the mRegulatedLeagueRankingRewardList where rewardGroupId less than or equals to DEFAULT_REWARD_GROUP_ID
        defaultMRegulatedLeagueRankingRewardShouldNotBeFound("rewardGroupId.lessThan=" + DEFAULT_REWARD_GROUP_ID);

        // Get all the mRegulatedLeagueRankingRewardList where rewardGroupId less than or equals to UPDATED_REWARD_GROUP_ID
        defaultMRegulatedLeagueRankingRewardShouldBeFound("rewardGroupId.lessThan=" + UPDATED_REWARD_GROUP_ID);
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultMRegulatedLeagueRankingRewardShouldBeFound(String filter) throws Exception {
        restMRegulatedLeagueRankingRewardMockMvc.perform(get("/api/m-regulated-league-ranking-rewards?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(mRegulatedLeagueRankingReward.getId().intValue())))
            .andExpect(jsonPath("$.[*].regulatedLeagueId").value(hasItem(DEFAULT_REGULATED_LEAGUE_ID)))
            .andExpect(jsonPath("$.[*].leagueHierarchy").value(hasItem(DEFAULT_LEAGUE_HIERARCHY)))
            .andExpect(jsonPath("$.[*].rankTo").value(hasItem(DEFAULT_RANK_TO)))
            .andExpect(jsonPath("$.[*].rewardGroupId").value(hasItem(DEFAULT_REWARD_GROUP_ID)));

        // Check, that the count call also returns 1
        restMRegulatedLeagueRankingRewardMockMvc.perform(get("/api/m-regulated-league-ranking-rewards/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultMRegulatedLeagueRankingRewardShouldNotBeFound(String filter) throws Exception {
        restMRegulatedLeagueRankingRewardMockMvc.perform(get("/api/m-regulated-league-ranking-rewards?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restMRegulatedLeagueRankingRewardMockMvc.perform(get("/api/m-regulated-league-ranking-rewards/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("0"));
    }


    @Test
    @Transactional
    public void getNonExistingMRegulatedLeagueRankingReward() throws Exception {
        // Get the mRegulatedLeagueRankingReward
        restMRegulatedLeagueRankingRewardMockMvc.perform(get("/api/m-regulated-league-ranking-rewards/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateMRegulatedLeagueRankingReward() throws Exception {
        // Initialize the database
        mRegulatedLeagueRankingRewardRepository.saveAndFlush(mRegulatedLeagueRankingReward);

        int databaseSizeBeforeUpdate = mRegulatedLeagueRankingRewardRepository.findAll().size();

        // Update the mRegulatedLeagueRankingReward
        MRegulatedLeagueRankingReward updatedMRegulatedLeagueRankingReward = mRegulatedLeagueRankingRewardRepository.findById(mRegulatedLeagueRankingReward.getId()).get();
        // Disconnect from session so that the updates on updatedMRegulatedLeagueRankingReward are not directly saved in db
        em.detach(updatedMRegulatedLeagueRankingReward);
        updatedMRegulatedLeagueRankingReward
            .regulatedLeagueId(UPDATED_REGULATED_LEAGUE_ID)
            .leagueHierarchy(UPDATED_LEAGUE_HIERARCHY)
            .rankTo(UPDATED_RANK_TO)
            .rewardGroupId(UPDATED_REWARD_GROUP_ID);
        MRegulatedLeagueRankingRewardDTO mRegulatedLeagueRankingRewardDTO = mRegulatedLeagueRankingRewardMapper.toDto(updatedMRegulatedLeagueRankingReward);

        restMRegulatedLeagueRankingRewardMockMvc.perform(put("/api/m-regulated-league-ranking-rewards")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mRegulatedLeagueRankingRewardDTO)))
            .andExpect(status().isOk());

        // Validate the MRegulatedLeagueRankingReward in the database
        List<MRegulatedLeagueRankingReward> mRegulatedLeagueRankingRewardList = mRegulatedLeagueRankingRewardRepository.findAll();
        assertThat(mRegulatedLeagueRankingRewardList).hasSize(databaseSizeBeforeUpdate);
        MRegulatedLeagueRankingReward testMRegulatedLeagueRankingReward = mRegulatedLeagueRankingRewardList.get(mRegulatedLeagueRankingRewardList.size() - 1);
        assertThat(testMRegulatedLeagueRankingReward.getRegulatedLeagueId()).isEqualTo(UPDATED_REGULATED_LEAGUE_ID);
        assertThat(testMRegulatedLeagueRankingReward.getLeagueHierarchy()).isEqualTo(UPDATED_LEAGUE_HIERARCHY);
        assertThat(testMRegulatedLeagueRankingReward.getRankTo()).isEqualTo(UPDATED_RANK_TO);
        assertThat(testMRegulatedLeagueRankingReward.getRewardGroupId()).isEqualTo(UPDATED_REWARD_GROUP_ID);
    }

    @Test
    @Transactional
    public void updateNonExistingMRegulatedLeagueRankingReward() throws Exception {
        int databaseSizeBeforeUpdate = mRegulatedLeagueRankingRewardRepository.findAll().size();

        // Create the MRegulatedLeagueRankingReward
        MRegulatedLeagueRankingRewardDTO mRegulatedLeagueRankingRewardDTO = mRegulatedLeagueRankingRewardMapper.toDto(mRegulatedLeagueRankingReward);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restMRegulatedLeagueRankingRewardMockMvc.perform(put("/api/m-regulated-league-ranking-rewards")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mRegulatedLeagueRankingRewardDTO)))
            .andExpect(status().isBadRequest());

        // Validate the MRegulatedLeagueRankingReward in the database
        List<MRegulatedLeagueRankingReward> mRegulatedLeagueRankingRewardList = mRegulatedLeagueRankingRewardRepository.findAll();
        assertThat(mRegulatedLeagueRankingRewardList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteMRegulatedLeagueRankingReward() throws Exception {
        // Initialize the database
        mRegulatedLeagueRankingRewardRepository.saveAndFlush(mRegulatedLeagueRankingReward);

        int databaseSizeBeforeDelete = mRegulatedLeagueRankingRewardRepository.findAll().size();

        // Delete the mRegulatedLeagueRankingReward
        restMRegulatedLeagueRankingRewardMockMvc.perform(delete("/api/m-regulated-league-ranking-rewards/{id}", mRegulatedLeagueRankingReward.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database is empty
        List<MRegulatedLeagueRankingReward> mRegulatedLeagueRankingRewardList = mRegulatedLeagueRankingRewardRepository.findAll();
        assertThat(mRegulatedLeagueRankingRewardList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(MRegulatedLeagueRankingReward.class);
        MRegulatedLeagueRankingReward mRegulatedLeagueRankingReward1 = new MRegulatedLeagueRankingReward();
        mRegulatedLeagueRankingReward1.setId(1L);
        MRegulatedLeagueRankingReward mRegulatedLeagueRankingReward2 = new MRegulatedLeagueRankingReward();
        mRegulatedLeagueRankingReward2.setId(mRegulatedLeagueRankingReward1.getId());
        assertThat(mRegulatedLeagueRankingReward1).isEqualTo(mRegulatedLeagueRankingReward2);
        mRegulatedLeagueRankingReward2.setId(2L);
        assertThat(mRegulatedLeagueRankingReward1).isNotEqualTo(mRegulatedLeagueRankingReward2);
        mRegulatedLeagueRankingReward1.setId(null);
        assertThat(mRegulatedLeagueRankingReward1).isNotEqualTo(mRegulatedLeagueRankingReward2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(MRegulatedLeagueRankingRewardDTO.class);
        MRegulatedLeagueRankingRewardDTO mRegulatedLeagueRankingRewardDTO1 = new MRegulatedLeagueRankingRewardDTO();
        mRegulatedLeagueRankingRewardDTO1.setId(1L);
        MRegulatedLeagueRankingRewardDTO mRegulatedLeagueRankingRewardDTO2 = new MRegulatedLeagueRankingRewardDTO();
        assertThat(mRegulatedLeagueRankingRewardDTO1).isNotEqualTo(mRegulatedLeagueRankingRewardDTO2);
        mRegulatedLeagueRankingRewardDTO2.setId(mRegulatedLeagueRankingRewardDTO1.getId());
        assertThat(mRegulatedLeagueRankingRewardDTO1).isEqualTo(mRegulatedLeagueRankingRewardDTO2);
        mRegulatedLeagueRankingRewardDTO2.setId(2L);
        assertThat(mRegulatedLeagueRankingRewardDTO1).isNotEqualTo(mRegulatedLeagueRankingRewardDTO2);
        mRegulatedLeagueRankingRewardDTO1.setId(null);
        assertThat(mRegulatedLeagueRankingRewardDTO1).isNotEqualTo(mRegulatedLeagueRankingRewardDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(mRegulatedLeagueRankingRewardMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(mRegulatedLeagueRankingRewardMapper.fromId(null)).isNull();
    }
}
