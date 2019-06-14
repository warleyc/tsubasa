package io.shm.tsubasa.web.rest;

import io.shm.tsubasa.TsubasaApp;
import io.shm.tsubasa.domain.MTimeattackRankingReward;
import io.shm.tsubasa.repository.MTimeattackRankingRewardRepository;
import io.shm.tsubasa.service.MTimeattackRankingRewardService;
import io.shm.tsubasa.service.dto.MTimeattackRankingRewardDTO;
import io.shm.tsubasa.service.mapper.MTimeattackRankingRewardMapper;
import io.shm.tsubasa.web.rest.errors.ExceptionTranslator;
import io.shm.tsubasa.service.dto.MTimeattackRankingRewardCriteria;
import io.shm.tsubasa.service.MTimeattackRankingRewardQueryService;

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
 * Integration tests for the {@Link MTimeattackRankingRewardResource} REST controller.
 */
@SpringBootTest(classes = TsubasaApp.class)
public class MTimeattackRankingRewardResourceIT {

    private static final Integer DEFAULT_EVENT_ID = 1;
    private static final Integer UPDATED_EVENT_ID = 2;

    private static final Integer DEFAULT_RANKING_FROM = 1;
    private static final Integer UPDATED_RANKING_FROM = 2;

    private static final Integer DEFAULT_RANKING_TO = 1;
    private static final Integer UPDATED_RANKING_TO = 2;

    private static final Integer DEFAULT_REWARD_GROUP_ID = 1;
    private static final Integer UPDATED_REWARD_GROUP_ID = 2;

    @Autowired
    private MTimeattackRankingRewardRepository mTimeattackRankingRewardRepository;

    @Autowired
    private MTimeattackRankingRewardMapper mTimeattackRankingRewardMapper;

    @Autowired
    private MTimeattackRankingRewardService mTimeattackRankingRewardService;

    @Autowired
    private MTimeattackRankingRewardQueryService mTimeattackRankingRewardQueryService;

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

    private MockMvc restMTimeattackRankingRewardMockMvc;

    private MTimeattackRankingReward mTimeattackRankingReward;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final MTimeattackRankingRewardResource mTimeattackRankingRewardResource = new MTimeattackRankingRewardResource(mTimeattackRankingRewardService, mTimeattackRankingRewardQueryService);
        this.restMTimeattackRankingRewardMockMvc = MockMvcBuilders.standaloneSetup(mTimeattackRankingRewardResource)
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
    public static MTimeattackRankingReward createEntity(EntityManager em) {
        MTimeattackRankingReward mTimeattackRankingReward = new MTimeattackRankingReward()
            .eventId(DEFAULT_EVENT_ID)
            .rankingFrom(DEFAULT_RANKING_FROM)
            .rankingTo(DEFAULT_RANKING_TO)
            .rewardGroupId(DEFAULT_REWARD_GROUP_ID);
        return mTimeattackRankingReward;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static MTimeattackRankingReward createUpdatedEntity(EntityManager em) {
        MTimeattackRankingReward mTimeattackRankingReward = new MTimeattackRankingReward()
            .eventId(UPDATED_EVENT_ID)
            .rankingFrom(UPDATED_RANKING_FROM)
            .rankingTo(UPDATED_RANKING_TO)
            .rewardGroupId(UPDATED_REWARD_GROUP_ID);
        return mTimeattackRankingReward;
    }

    @BeforeEach
    public void initTest() {
        mTimeattackRankingReward = createEntity(em);
    }

    @Test
    @Transactional
    public void createMTimeattackRankingReward() throws Exception {
        int databaseSizeBeforeCreate = mTimeattackRankingRewardRepository.findAll().size();

        // Create the MTimeattackRankingReward
        MTimeattackRankingRewardDTO mTimeattackRankingRewardDTO = mTimeattackRankingRewardMapper.toDto(mTimeattackRankingReward);
        restMTimeattackRankingRewardMockMvc.perform(post("/api/m-timeattack-ranking-rewards")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mTimeattackRankingRewardDTO)))
            .andExpect(status().isCreated());

        // Validate the MTimeattackRankingReward in the database
        List<MTimeattackRankingReward> mTimeattackRankingRewardList = mTimeattackRankingRewardRepository.findAll();
        assertThat(mTimeattackRankingRewardList).hasSize(databaseSizeBeforeCreate + 1);
        MTimeattackRankingReward testMTimeattackRankingReward = mTimeattackRankingRewardList.get(mTimeattackRankingRewardList.size() - 1);
        assertThat(testMTimeattackRankingReward.getEventId()).isEqualTo(DEFAULT_EVENT_ID);
        assertThat(testMTimeattackRankingReward.getRankingFrom()).isEqualTo(DEFAULT_RANKING_FROM);
        assertThat(testMTimeattackRankingReward.getRankingTo()).isEqualTo(DEFAULT_RANKING_TO);
        assertThat(testMTimeattackRankingReward.getRewardGroupId()).isEqualTo(DEFAULT_REWARD_GROUP_ID);
    }

    @Test
    @Transactional
    public void createMTimeattackRankingRewardWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = mTimeattackRankingRewardRepository.findAll().size();

        // Create the MTimeattackRankingReward with an existing ID
        mTimeattackRankingReward.setId(1L);
        MTimeattackRankingRewardDTO mTimeattackRankingRewardDTO = mTimeattackRankingRewardMapper.toDto(mTimeattackRankingReward);

        // An entity with an existing ID cannot be created, so this API call must fail
        restMTimeattackRankingRewardMockMvc.perform(post("/api/m-timeattack-ranking-rewards")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mTimeattackRankingRewardDTO)))
            .andExpect(status().isBadRequest());

        // Validate the MTimeattackRankingReward in the database
        List<MTimeattackRankingReward> mTimeattackRankingRewardList = mTimeattackRankingRewardRepository.findAll();
        assertThat(mTimeattackRankingRewardList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkEventIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = mTimeattackRankingRewardRepository.findAll().size();
        // set the field null
        mTimeattackRankingReward.setEventId(null);

        // Create the MTimeattackRankingReward, which fails.
        MTimeattackRankingRewardDTO mTimeattackRankingRewardDTO = mTimeattackRankingRewardMapper.toDto(mTimeattackRankingReward);

        restMTimeattackRankingRewardMockMvc.perform(post("/api/m-timeattack-ranking-rewards")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mTimeattackRankingRewardDTO)))
            .andExpect(status().isBadRequest());

        List<MTimeattackRankingReward> mTimeattackRankingRewardList = mTimeattackRankingRewardRepository.findAll();
        assertThat(mTimeattackRankingRewardList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkRankingFromIsRequired() throws Exception {
        int databaseSizeBeforeTest = mTimeattackRankingRewardRepository.findAll().size();
        // set the field null
        mTimeattackRankingReward.setRankingFrom(null);

        // Create the MTimeattackRankingReward, which fails.
        MTimeattackRankingRewardDTO mTimeattackRankingRewardDTO = mTimeattackRankingRewardMapper.toDto(mTimeattackRankingReward);

        restMTimeattackRankingRewardMockMvc.perform(post("/api/m-timeattack-ranking-rewards")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mTimeattackRankingRewardDTO)))
            .andExpect(status().isBadRequest());

        List<MTimeattackRankingReward> mTimeattackRankingRewardList = mTimeattackRankingRewardRepository.findAll();
        assertThat(mTimeattackRankingRewardList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkRankingToIsRequired() throws Exception {
        int databaseSizeBeforeTest = mTimeattackRankingRewardRepository.findAll().size();
        // set the field null
        mTimeattackRankingReward.setRankingTo(null);

        // Create the MTimeattackRankingReward, which fails.
        MTimeattackRankingRewardDTO mTimeattackRankingRewardDTO = mTimeattackRankingRewardMapper.toDto(mTimeattackRankingReward);

        restMTimeattackRankingRewardMockMvc.perform(post("/api/m-timeattack-ranking-rewards")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mTimeattackRankingRewardDTO)))
            .andExpect(status().isBadRequest());

        List<MTimeattackRankingReward> mTimeattackRankingRewardList = mTimeattackRankingRewardRepository.findAll();
        assertThat(mTimeattackRankingRewardList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkRewardGroupIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = mTimeattackRankingRewardRepository.findAll().size();
        // set the field null
        mTimeattackRankingReward.setRewardGroupId(null);

        // Create the MTimeattackRankingReward, which fails.
        MTimeattackRankingRewardDTO mTimeattackRankingRewardDTO = mTimeattackRankingRewardMapper.toDto(mTimeattackRankingReward);

        restMTimeattackRankingRewardMockMvc.perform(post("/api/m-timeattack-ranking-rewards")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mTimeattackRankingRewardDTO)))
            .andExpect(status().isBadRequest());

        List<MTimeattackRankingReward> mTimeattackRankingRewardList = mTimeattackRankingRewardRepository.findAll();
        assertThat(mTimeattackRankingRewardList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllMTimeattackRankingRewards() throws Exception {
        // Initialize the database
        mTimeattackRankingRewardRepository.saveAndFlush(mTimeattackRankingReward);

        // Get all the mTimeattackRankingRewardList
        restMTimeattackRankingRewardMockMvc.perform(get("/api/m-timeattack-ranking-rewards?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(mTimeattackRankingReward.getId().intValue())))
            .andExpect(jsonPath("$.[*].eventId").value(hasItem(DEFAULT_EVENT_ID)))
            .andExpect(jsonPath("$.[*].rankingFrom").value(hasItem(DEFAULT_RANKING_FROM)))
            .andExpect(jsonPath("$.[*].rankingTo").value(hasItem(DEFAULT_RANKING_TO)))
            .andExpect(jsonPath("$.[*].rewardGroupId").value(hasItem(DEFAULT_REWARD_GROUP_ID)));
    }
    
    @Test
    @Transactional
    public void getMTimeattackRankingReward() throws Exception {
        // Initialize the database
        mTimeattackRankingRewardRepository.saveAndFlush(mTimeattackRankingReward);

        // Get the mTimeattackRankingReward
        restMTimeattackRankingRewardMockMvc.perform(get("/api/m-timeattack-ranking-rewards/{id}", mTimeattackRankingReward.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(mTimeattackRankingReward.getId().intValue()))
            .andExpect(jsonPath("$.eventId").value(DEFAULT_EVENT_ID))
            .andExpect(jsonPath("$.rankingFrom").value(DEFAULT_RANKING_FROM))
            .andExpect(jsonPath("$.rankingTo").value(DEFAULT_RANKING_TO))
            .andExpect(jsonPath("$.rewardGroupId").value(DEFAULT_REWARD_GROUP_ID));
    }

    @Test
    @Transactional
    public void getAllMTimeattackRankingRewardsByEventIdIsEqualToSomething() throws Exception {
        // Initialize the database
        mTimeattackRankingRewardRepository.saveAndFlush(mTimeattackRankingReward);

        // Get all the mTimeattackRankingRewardList where eventId equals to DEFAULT_EVENT_ID
        defaultMTimeattackRankingRewardShouldBeFound("eventId.equals=" + DEFAULT_EVENT_ID);

        // Get all the mTimeattackRankingRewardList where eventId equals to UPDATED_EVENT_ID
        defaultMTimeattackRankingRewardShouldNotBeFound("eventId.equals=" + UPDATED_EVENT_ID);
    }

    @Test
    @Transactional
    public void getAllMTimeattackRankingRewardsByEventIdIsInShouldWork() throws Exception {
        // Initialize the database
        mTimeattackRankingRewardRepository.saveAndFlush(mTimeattackRankingReward);

        // Get all the mTimeattackRankingRewardList where eventId in DEFAULT_EVENT_ID or UPDATED_EVENT_ID
        defaultMTimeattackRankingRewardShouldBeFound("eventId.in=" + DEFAULT_EVENT_ID + "," + UPDATED_EVENT_ID);

        // Get all the mTimeattackRankingRewardList where eventId equals to UPDATED_EVENT_ID
        defaultMTimeattackRankingRewardShouldNotBeFound("eventId.in=" + UPDATED_EVENT_ID);
    }

    @Test
    @Transactional
    public void getAllMTimeattackRankingRewardsByEventIdIsNullOrNotNull() throws Exception {
        // Initialize the database
        mTimeattackRankingRewardRepository.saveAndFlush(mTimeattackRankingReward);

        // Get all the mTimeattackRankingRewardList where eventId is not null
        defaultMTimeattackRankingRewardShouldBeFound("eventId.specified=true");

        // Get all the mTimeattackRankingRewardList where eventId is null
        defaultMTimeattackRankingRewardShouldNotBeFound("eventId.specified=false");
    }

    @Test
    @Transactional
    public void getAllMTimeattackRankingRewardsByEventIdIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mTimeattackRankingRewardRepository.saveAndFlush(mTimeattackRankingReward);

        // Get all the mTimeattackRankingRewardList where eventId greater than or equals to DEFAULT_EVENT_ID
        defaultMTimeattackRankingRewardShouldBeFound("eventId.greaterOrEqualThan=" + DEFAULT_EVENT_ID);

        // Get all the mTimeattackRankingRewardList where eventId greater than or equals to UPDATED_EVENT_ID
        defaultMTimeattackRankingRewardShouldNotBeFound("eventId.greaterOrEqualThan=" + UPDATED_EVENT_ID);
    }

    @Test
    @Transactional
    public void getAllMTimeattackRankingRewardsByEventIdIsLessThanSomething() throws Exception {
        // Initialize the database
        mTimeattackRankingRewardRepository.saveAndFlush(mTimeattackRankingReward);

        // Get all the mTimeattackRankingRewardList where eventId less than or equals to DEFAULT_EVENT_ID
        defaultMTimeattackRankingRewardShouldNotBeFound("eventId.lessThan=" + DEFAULT_EVENT_ID);

        // Get all the mTimeattackRankingRewardList where eventId less than or equals to UPDATED_EVENT_ID
        defaultMTimeattackRankingRewardShouldBeFound("eventId.lessThan=" + UPDATED_EVENT_ID);
    }


    @Test
    @Transactional
    public void getAllMTimeattackRankingRewardsByRankingFromIsEqualToSomething() throws Exception {
        // Initialize the database
        mTimeattackRankingRewardRepository.saveAndFlush(mTimeattackRankingReward);

        // Get all the mTimeattackRankingRewardList where rankingFrom equals to DEFAULT_RANKING_FROM
        defaultMTimeattackRankingRewardShouldBeFound("rankingFrom.equals=" + DEFAULT_RANKING_FROM);

        // Get all the mTimeattackRankingRewardList where rankingFrom equals to UPDATED_RANKING_FROM
        defaultMTimeattackRankingRewardShouldNotBeFound("rankingFrom.equals=" + UPDATED_RANKING_FROM);
    }

    @Test
    @Transactional
    public void getAllMTimeattackRankingRewardsByRankingFromIsInShouldWork() throws Exception {
        // Initialize the database
        mTimeattackRankingRewardRepository.saveAndFlush(mTimeattackRankingReward);

        // Get all the mTimeattackRankingRewardList where rankingFrom in DEFAULT_RANKING_FROM or UPDATED_RANKING_FROM
        defaultMTimeattackRankingRewardShouldBeFound("rankingFrom.in=" + DEFAULT_RANKING_FROM + "," + UPDATED_RANKING_FROM);

        // Get all the mTimeattackRankingRewardList where rankingFrom equals to UPDATED_RANKING_FROM
        defaultMTimeattackRankingRewardShouldNotBeFound("rankingFrom.in=" + UPDATED_RANKING_FROM);
    }

    @Test
    @Transactional
    public void getAllMTimeattackRankingRewardsByRankingFromIsNullOrNotNull() throws Exception {
        // Initialize the database
        mTimeattackRankingRewardRepository.saveAndFlush(mTimeattackRankingReward);

        // Get all the mTimeattackRankingRewardList where rankingFrom is not null
        defaultMTimeattackRankingRewardShouldBeFound("rankingFrom.specified=true");

        // Get all the mTimeattackRankingRewardList where rankingFrom is null
        defaultMTimeattackRankingRewardShouldNotBeFound("rankingFrom.specified=false");
    }

    @Test
    @Transactional
    public void getAllMTimeattackRankingRewardsByRankingFromIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mTimeattackRankingRewardRepository.saveAndFlush(mTimeattackRankingReward);

        // Get all the mTimeattackRankingRewardList where rankingFrom greater than or equals to DEFAULT_RANKING_FROM
        defaultMTimeattackRankingRewardShouldBeFound("rankingFrom.greaterOrEqualThan=" + DEFAULT_RANKING_FROM);

        // Get all the mTimeattackRankingRewardList where rankingFrom greater than or equals to UPDATED_RANKING_FROM
        defaultMTimeattackRankingRewardShouldNotBeFound("rankingFrom.greaterOrEqualThan=" + UPDATED_RANKING_FROM);
    }

    @Test
    @Transactional
    public void getAllMTimeattackRankingRewardsByRankingFromIsLessThanSomething() throws Exception {
        // Initialize the database
        mTimeattackRankingRewardRepository.saveAndFlush(mTimeattackRankingReward);

        // Get all the mTimeattackRankingRewardList where rankingFrom less than or equals to DEFAULT_RANKING_FROM
        defaultMTimeattackRankingRewardShouldNotBeFound("rankingFrom.lessThan=" + DEFAULT_RANKING_FROM);

        // Get all the mTimeattackRankingRewardList where rankingFrom less than or equals to UPDATED_RANKING_FROM
        defaultMTimeattackRankingRewardShouldBeFound("rankingFrom.lessThan=" + UPDATED_RANKING_FROM);
    }


    @Test
    @Transactional
    public void getAllMTimeattackRankingRewardsByRankingToIsEqualToSomething() throws Exception {
        // Initialize the database
        mTimeattackRankingRewardRepository.saveAndFlush(mTimeattackRankingReward);

        // Get all the mTimeattackRankingRewardList where rankingTo equals to DEFAULT_RANKING_TO
        defaultMTimeattackRankingRewardShouldBeFound("rankingTo.equals=" + DEFAULT_RANKING_TO);

        // Get all the mTimeattackRankingRewardList where rankingTo equals to UPDATED_RANKING_TO
        defaultMTimeattackRankingRewardShouldNotBeFound("rankingTo.equals=" + UPDATED_RANKING_TO);
    }

    @Test
    @Transactional
    public void getAllMTimeattackRankingRewardsByRankingToIsInShouldWork() throws Exception {
        // Initialize the database
        mTimeattackRankingRewardRepository.saveAndFlush(mTimeattackRankingReward);

        // Get all the mTimeattackRankingRewardList where rankingTo in DEFAULT_RANKING_TO or UPDATED_RANKING_TO
        defaultMTimeattackRankingRewardShouldBeFound("rankingTo.in=" + DEFAULT_RANKING_TO + "," + UPDATED_RANKING_TO);

        // Get all the mTimeattackRankingRewardList where rankingTo equals to UPDATED_RANKING_TO
        defaultMTimeattackRankingRewardShouldNotBeFound("rankingTo.in=" + UPDATED_RANKING_TO);
    }

    @Test
    @Transactional
    public void getAllMTimeattackRankingRewardsByRankingToIsNullOrNotNull() throws Exception {
        // Initialize the database
        mTimeattackRankingRewardRepository.saveAndFlush(mTimeattackRankingReward);

        // Get all the mTimeattackRankingRewardList where rankingTo is not null
        defaultMTimeattackRankingRewardShouldBeFound("rankingTo.specified=true");

        // Get all the mTimeattackRankingRewardList where rankingTo is null
        defaultMTimeattackRankingRewardShouldNotBeFound("rankingTo.specified=false");
    }

    @Test
    @Transactional
    public void getAllMTimeattackRankingRewardsByRankingToIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mTimeattackRankingRewardRepository.saveAndFlush(mTimeattackRankingReward);

        // Get all the mTimeattackRankingRewardList where rankingTo greater than or equals to DEFAULT_RANKING_TO
        defaultMTimeattackRankingRewardShouldBeFound("rankingTo.greaterOrEqualThan=" + DEFAULT_RANKING_TO);

        // Get all the mTimeattackRankingRewardList where rankingTo greater than or equals to UPDATED_RANKING_TO
        defaultMTimeattackRankingRewardShouldNotBeFound("rankingTo.greaterOrEqualThan=" + UPDATED_RANKING_TO);
    }

    @Test
    @Transactional
    public void getAllMTimeattackRankingRewardsByRankingToIsLessThanSomething() throws Exception {
        // Initialize the database
        mTimeattackRankingRewardRepository.saveAndFlush(mTimeattackRankingReward);

        // Get all the mTimeattackRankingRewardList where rankingTo less than or equals to DEFAULT_RANKING_TO
        defaultMTimeattackRankingRewardShouldNotBeFound("rankingTo.lessThan=" + DEFAULT_RANKING_TO);

        // Get all the mTimeattackRankingRewardList where rankingTo less than or equals to UPDATED_RANKING_TO
        defaultMTimeattackRankingRewardShouldBeFound("rankingTo.lessThan=" + UPDATED_RANKING_TO);
    }


    @Test
    @Transactional
    public void getAllMTimeattackRankingRewardsByRewardGroupIdIsEqualToSomething() throws Exception {
        // Initialize the database
        mTimeattackRankingRewardRepository.saveAndFlush(mTimeattackRankingReward);

        // Get all the mTimeattackRankingRewardList where rewardGroupId equals to DEFAULT_REWARD_GROUP_ID
        defaultMTimeattackRankingRewardShouldBeFound("rewardGroupId.equals=" + DEFAULT_REWARD_GROUP_ID);

        // Get all the mTimeattackRankingRewardList where rewardGroupId equals to UPDATED_REWARD_GROUP_ID
        defaultMTimeattackRankingRewardShouldNotBeFound("rewardGroupId.equals=" + UPDATED_REWARD_GROUP_ID);
    }

    @Test
    @Transactional
    public void getAllMTimeattackRankingRewardsByRewardGroupIdIsInShouldWork() throws Exception {
        // Initialize the database
        mTimeattackRankingRewardRepository.saveAndFlush(mTimeattackRankingReward);

        // Get all the mTimeattackRankingRewardList where rewardGroupId in DEFAULT_REWARD_GROUP_ID or UPDATED_REWARD_GROUP_ID
        defaultMTimeattackRankingRewardShouldBeFound("rewardGroupId.in=" + DEFAULT_REWARD_GROUP_ID + "," + UPDATED_REWARD_GROUP_ID);

        // Get all the mTimeattackRankingRewardList where rewardGroupId equals to UPDATED_REWARD_GROUP_ID
        defaultMTimeattackRankingRewardShouldNotBeFound("rewardGroupId.in=" + UPDATED_REWARD_GROUP_ID);
    }

    @Test
    @Transactional
    public void getAllMTimeattackRankingRewardsByRewardGroupIdIsNullOrNotNull() throws Exception {
        // Initialize the database
        mTimeattackRankingRewardRepository.saveAndFlush(mTimeattackRankingReward);

        // Get all the mTimeattackRankingRewardList where rewardGroupId is not null
        defaultMTimeattackRankingRewardShouldBeFound("rewardGroupId.specified=true");

        // Get all the mTimeattackRankingRewardList where rewardGroupId is null
        defaultMTimeattackRankingRewardShouldNotBeFound("rewardGroupId.specified=false");
    }

    @Test
    @Transactional
    public void getAllMTimeattackRankingRewardsByRewardGroupIdIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mTimeattackRankingRewardRepository.saveAndFlush(mTimeattackRankingReward);

        // Get all the mTimeattackRankingRewardList where rewardGroupId greater than or equals to DEFAULT_REWARD_GROUP_ID
        defaultMTimeattackRankingRewardShouldBeFound("rewardGroupId.greaterOrEqualThan=" + DEFAULT_REWARD_GROUP_ID);

        // Get all the mTimeattackRankingRewardList where rewardGroupId greater than or equals to UPDATED_REWARD_GROUP_ID
        defaultMTimeattackRankingRewardShouldNotBeFound("rewardGroupId.greaterOrEqualThan=" + UPDATED_REWARD_GROUP_ID);
    }

    @Test
    @Transactional
    public void getAllMTimeattackRankingRewardsByRewardGroupIdIsLessThanSomething() throws Exception {
        // Initialize the database
        mTimeattackRankingRewardRepository.saveAndFlush(mTimeattackRankingReward);

        // Get all the mTimeattackRankingRewardList where rewardGroupId less than or equals to DEFAULT_REWARD_GROUP_ID
        defaultMTimeattackRankingRewardShouldNotBeFound("rewardGroupId.lessThan=" + DEFAULT_REWARD_GROUP_ID);

        // Get all the mTimeattackRankingRewardList where rewardGroupId less than or equals to UPDATED_REWARD_GROUP_ID
        defaultMTimeattackRankingRewardShouldBeFound("rewardGroupId.lessThan=" + UPDATED_REWARD_GROUP_ID);
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultMTimeattackRankingRewardShouldBeFound(String filter) throws Exception {
        restMTimeattackRankingRewardMockMvc.perform(get("/api/m-timeattack-ranking-rewards?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(mTimeattackRankingReward.getId().intValue())))
            .andExpect(jsonPath("$.[*].eventId").value(hasItem(DEFAULT_EVENT_ID)))
            .andExpect(jsonPath("$.[*].rankingFrom").value(hasItem(DEFAULT_RANKING_FROM)))
            .andExpect(jsonPath("$.[*].rankingTo").value(hasItem(DEFAULT_RANKING_TO)))
            .andExpect(jsonPath("$.[*].rewardGroupId").value(hasItem(DEFAULT_REWARD_GROUP_ID)));

        // Check, that the count call also returns 1
        restMTimeattackRankingRewardMockMvc.perform(get("/api/m-timeattack-ranking-rewards/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultMTimeattackRankingRewardShouldNotBeFound(String filter) throws Exception {
        restMTimeattackRankingRewardMockMvc.perform(get("/api/m-timeattack-ranking-rewards?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restMTimeattackRankingRewardMockMvc.perform(get("/api/m-timeattack-ranking-rewards/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("0"));
    }


    @Test
    @Transactional
    public void getNonExistingMTimeattackRankingReward() throws Exception {
        // Get the mTimeattackRankingReward
        restMTimeattackRankingRewardMockMvc.perform(get("/api/m-timeattack-ranking-rewards/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateMTimeattackRankingReward() throws Exception {
        // Initialize the database
        mTimeattackRankingRewardRepository.saveAndFlush(mTimeattackRankingReward);

        int databaseSizeBeforeUpdate = mTimeattackRankingRewardRepository.findAll().size();

        // Update the mTimeattackRankingReward
        MTimeattackRankingReward updatedMTimeattackRankingReward = mTimeattackRankingRewardRepository.findById(mTimeattackRankingReward.getId()).get();
        // Disconnect from session so that the updates on updatedMTimeattackRankingReward are not directly saved in db
        em.detach(updatedMTimeattackRankingReward);
        updatedMTimeattackRankingReward
            .eventId(UPDATED_EVENT_ID)
            .rankingFrom(UPDATED_RANKING_FROM)
            .rankingTo(UPDATED_RANKING_TO)
            .rewardGroupId(UPDATED_REWARD_GROUP_ID);
        MTimeattackRankingRewardDTO mTimeattackRankingRewardDTO = mTimeattackRankingRewardMapper.toDto(updatedMTimeattackRankingReward);

        restMTimeattackRankingRewardMockMvc.perform(put("/api/m-timeattack-ranking-rewards")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mTimeattackRankingRewardDTO)))
            .andExpect(status().isOk());

        // Validate the MTimeattackRankingReward in the database
        List<MTimeattackRankingReward> mTimeattackRankingRewardList = mTimeattackRankingRewardRepository.findAll();
        assertThat(mTimeattackRankingRewardList).hasSize(databaseSizeBeforeUpdate);
        MTimeattackRankingReward testMTimeattackRankingReward = mTimeattackRankingRewardList.get(mTimeattackRankingRewardList.size() - 1);
        assertThat(testMTimeattackRankingReward.getEventId()).isEqualTo(UPDATED_EVENT_ID);
        assertThat(testMTimeattackRankingReward.getRankingFrom()).isEqualTo(UPDATED_RANKING_FROM);
        assertThat(testMTimeattackRankingReward.getRankingTo()).isEqualTo(UPDATED_RANKING_TO);
        assertThat(testMTimeattackRankingReward.getRewardGroupId()).isEqualTo(UPDATED_REWARD_GROUP_ID);
    }

    @Test
    @Transactional
    public void updateNonExistingMTimeattackRankingReward() throws Exception {
        int databaseSizeBeforeUpdate = mTimeattackRankingRewardRepository.findAll().size();

        // Create the MTimeattackRankingReward
        MTimeattackRankingRewardDTO mTimeattackRankingRewardDTO = mTimeattackRankingRewardMapper.toDto(mTimeattackRankingReward);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restMTimeattackRankingRewardMockMvc.perform(put("/api/m-timeattack-ranking-rewards")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mTimeattackRankingRewardDTO)))
            .andExpect(status().isBadRequest());

        // Validate the MTimeattackRankingReward in the database
        List<MTimeattackRankingReward> mTimeattackRankingRewardList = mTimeattackRankingRewardRepository.findAll();
        assertThat(mTimeattackRankingRewardList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteMTimeattackRankingReward() throws Exception {
        // Initialize the database
        mTimeattackRankingRewardRepository.saveAndFlush(mTimeattackRankingReward);

        int databaseSizeBeforeDelete = mTimeattackRankingRewardRepository.findAll().size();

        // Delete the mTimeattackRankingReward
        restMTimeattackRankingRewardMockMvc.perform(delete("/api/m-timeattack-ranking-rewards/{id}", mTimeattackRankingReward.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database is empty
        List<MTimeattackRankingReward> mTimeattackRankingRewardList = mTimeattackRankingRewardRepository.findAll();
        assertThat(mTimeattackRankingRewardList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(MTimeattackRankingReward.class);
        MTimeattackRankingReward mTimeattackRankingReward1 = new MTimeattackRankingReward();
        mTimeattackRankingReward1.setId(1L);
        MTimeattackRankingReward mTimeattackRankingReward2 = new MTimeattackRankingReward();
        mTimeattackRankingReward2.setId(mTimeattackRankingReward1.getId());
        assertThat(mTimeattackRankingReward1).isEqualTo(mTimeattackRankingReward2);
        mTimeattackRankingReward2.setId(2L);
        assertThat(mTimeattackRankingReward1).isNotEqualTo(mTimeattackRankingReward2);
        mTimeattackRankingReward1.setId(null);
        assertThat(mTimeattackRankingReward1).isNotEqualTo(mTimeattackRankingReward2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(MTimeattackRankingRewardDTO.class);
        MTimeattackRankingRewardDTO mTimeattackRankingRewardDTO1 = new MTimeattackRankingRewardDTO();
        mTimeattackRankingRewardDTO1.setId(1L);
        MTimeattackRankingRewardDTO mTimeattackRankingRewardDTO2 = new MTimeattackRankingRewardDTO();
        assertThat(mTimeattackRankingRewardDTO1).isNotEqualTo(mTimeattackRankingRewardDTO2);
        mTimeattackRankingRewardDTO2.setId(mTimeattackRankingRewardDTO1.getId());
        assertThat(mTimeattackRankingRewardDTO1).isEqualTo(mTimeattackRankingRewardDTO2);
        mTimeattackRankingRewardDTO2.setId(2L);
        assertThat(mTimeattackRankingRewardDTO1).isNotEqualTo(mTimeattackRankingRewardDTO2);
        mTimeattackRankingRewardDTO1.setId(null);
        assertThat(mTimeattackRankingRewardDTO1).isNotEqualTo(mTimeattackRankingRewardDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(mTimeattackRankingRewardMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(mTimeattackRankingRewardMapper.fromId(null)).isNull();
    }
}
