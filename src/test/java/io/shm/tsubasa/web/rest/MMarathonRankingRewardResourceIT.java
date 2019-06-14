package io.shm.tsubasa.web.rest;

import io.shm.tsubasa.TsubasaApp;
import io.shm.tsubasa.domain.MMarathonRankingReward;
import io.shm.tsubasa.repository.MMarathonRankingRewardRepository;
import io.shm.tsubasa.service.MMarathonRankingRewardService;
import io.shm.tsubasa.service.dto.MMarathonRankingRewardDTO;
import io.shm.tsubasa.service.mapper.MMarathonRankingRewardMapper;
import io.shm.tsubasa.web.rest.errors.ExceptionTranslator;
import io.shm.tsubasa.service.dto.MMarathonRankingRewardCriteria;
import io.shm.tsubasa.service.MMarathonRankingRewardQueryService;

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
 * Integration tests for the {@Link MMarathonRankingRewardResource} REST controller.
 */
@SpringBootTest(classes = TsubasaApp.class)
public class MMarathonRankingRewardResourceIT {

    private static final Integer DEFAULT_EVENT_ID = 1;
    private static final Integer UPDATED_EVENT_ID = 2;

    private static final Integer DEFAULT_RANKING_FROM = 1;
    private static final Integer UPDATED_RANKING_FROM = 2;

    private static final Integer DEFAULT_RANKING_TO = 1;
    private static final Integer UPDATED_RANKING_TO = 2;

    private static final Integer DEFAULT_REWARD_GROUP_ID = 1;
    private static final Integer UPDATED_REWARD_GROUP_ID = 2;

    @Autowired
    private MMarathonRankingRewardRepository mMarathonRankingRewardRepository;

    @Autowired
    private MMarathonRankingRewardMapper mMarathonRankingRewardMapper;

    @Autowired
    private MMarathonRankingRewardService mMarathonRankingRewardService;

    @Autowired
    private MMarathonRankingRewardQueryService mMarathonRankingRewardQueryService;

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

    private MockMvc restMMarathonRankingRewardMockMvc;

    private MMarathonRankingReward mMarathonRankingReward;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final MMarathonRankingRewardResource mMarathonRankingRewardResource = new MMarathonRankingRewardResource(mMarathonRankingRewardService, mMarathonRankingRewardQueryService);
        this.restMMarathonRankingRewardMockMvc = MockMvcBuilders.standaloneSetup(mMarathonRankingRewardResource)
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
    public static MMarathonRankingReward createEntity(EntityManager em) {
        MMarathonRankingReward mMarathonRankingReward = new MMarathonRankingReward()
            .eventId(DEFAULT_EVENT_ID)
            .rankingFrom(DEFAULT_RANKING_FROM)
            .rankingTo(DEFAULT_RANKING_TO)
            .rewardGroupId(DEFAULT_REWARD_GROUP_ID);
        return mMarathonRankingReward;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static MMarathonRankingReward createUpdatedEntity(EntityManager em) {
        MMarathonRankingReward mMarathonRankingReward = new MMarathonRankingReward()
            .eventId(UPDATED_EVENT_ID)
            .rankingFrom(UPDATED_RANKING_FROM)
            .rankingTo(UPDATED_RANKING_TO)
            .rewardGroupId(UPDATED_REWARD_GROUP_ID);
        return mMarathonRankingReward;
    }

    @BeforeEach
    public void initTest() {
        mMarathonRankingReward = createEntity(em);
    }

    @Test
    @Transactional
    public void createMMarathonRankingReward() throws Exception {
        int databaseSizeBeforeCreate = mMarathonRankingRewardRepository.findAll().size();

        // Create the MMarathonRankingReward
        MMarathonRankingRewardDTO mMarathonRankingRewardDTO = mMarathonRankingRewardMapper.toDto(mMarathonRankingReward);
        restMMarathonRankingRewardMockMvc.perform(post("/api/m-marathon-ranking-rewards")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mMarathonRankingRewardDTO)))
            .andExpect(status().isCreated());

        // Validate the MMarathonRankingReward in the database
        List<MMarathonRankingReward> mMarathonRankingRewardList = mMarathonRankingRewardRepository.findAll();
        assertThat(mMarathonRankingRewardList).hasSize(databaseSizeBeforeCreate + 1);
        MMarathonRankingReward testMMarathonRankingReward = mMarathonRankingRewardList.get(mMarathonRankingRewardList.size() - 1);
        assertThat(testMMarathonRankingReward.getEventId()).isEqualTo(DEFAULT_EVENT_ID);
        assertThat(testMMarathonRankingReward.getRankingFrom()).isEqualTo(DEFAULT_RANKING_FROM);
        assertThat(testMMarathonRankingReward.getRankingTo()).isEqualTo(DEFAULT_RANKING_TO);
        assertThat(testMMarathonRankingReward.getRewardGroupId()).isEqualTo(DEFAULT_REWARD_GROUP_ID);
    }

    @Test
    @Transactional
    public void createMMarathonRankingRewardWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = mMarathonRankingRewardRepository.findAll().size();

        // Create the MMarathonRankingReward with an existing ID
        mMarathonRankingReward.setId(1L);
        MMarathonRankingRewardDTO mMarathonRankingRewardDTO = mMarathonRankingRewardMapper.toDto(mMarathonRankingReward);

        // An entity with an existing ID cannot be created, so this API call must fail
        restMMarathonRankingRewardMockMvc.perform(post("/api/m-marathon-ranking-rewards")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mMarathonRankingRewardDTO)))
            .andExpect(status().isBadRequest());

        // Validate the MMarathonRankingReward in the database
        List<MMarathonRankingReward> mMarathonRankingRewardList = mMarathonRankingRewardRepository.findAll();
        assertThat(mMarathonRankingRewardList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkEventIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = mMarathonRankingRewardRepository.findAll().size();
        // set the field null
        mMarathonRankingReward.setEventId(null);

        // Create the MMarathonRankingReward, which fails.
        MMarathonRankingRewardDTO mMarathonRankingRewardDTO = mMarathonRankingRewardMapper.toDto(mMarathonRankingReward);

        restMMarathonRankingRewardMockMvc.perform(post("/api/m-marathon-ranking-rewards")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mMarathonRankingRewardDTO)))
            .andExpect(status().isBadRequest());

        List<MMarathonRankingReward> mMarathonRankingRewardList = mMarathonRankingRewardRepository.findAll();
        assertThat(mMarathonRankingRewardList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkRankingFromIsRequired() throws Exception {
        int databaseSizeBeforeTest = mMarathonRankingRewardRepository.findAll().size();
        // set the field null
        mMarathonRankingReward.setRankingFrom(null);

        // Create the MMarathonRankingReward, which fails.
        MMarathonRankingRewardDTO mMarathonRankingRewardDTO = mMarathonRankingRewardMapper.toDto(mMarathonRankingReward);

        restMMarathonRankingRewardMockMvc.perform(post("/api/m-marathon-ranking-rewards")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mMarathonRankingRewardDTO)))
            .andExpect(status().isBadRequest());

        List<MMarathonRankingReward> mMarathonRankingRewardList = mMarathonRankingRewardRepository.findAll();
        assertThat(mMarathonRankingRewardList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkRankingToIsRequired() throws Exception {
        int databaseSizeBeforeTest = mMarathonRankingRewardRepository.findAll().size();
        // set the field null
        mMarathonRankingReward.setRankingTo(null);

        // Create the MMarathonRankingReward, which fails.
        MMarathonRankingRewardDTO mMarathonRankingRewardDTO = mMarathonRankingRewardMapper.toDto(mMarathonRankingReward);

        restMMarathonRankingRewardMockMvc.perform(post("/api/m-marathon-ranking-rewards")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mMarathonRankingRewardDTO)))
            .andExpect(status().isBadRequest());

        List<MMarathonRankingReward> mMarathonRankingRewardList = mMarathonRankingRewardRepository.findAll();
        assertThat(mMarathonRankingRewardList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkRewardGroupIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = mMarathonRankingRewardRepository.findAll().size();
        // set the field null
        mMarathonRankingReward.setRewardGroupId(null);

        // Create the MMarathonRankingReward, which fails.
        MMarathonRankingRewardDTO mMarathonRankingRewardDTO = mMarathonRankingRewardMapper.toDto(mMarathonRankingReward);

        restMMarathonRankingRewardMockMvc.perform(post("/api/m-marathon-ranking-rewards")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mMarathonRankingRewardDTO)))
            .andExpect(status().isBadRequest());

        List<MMarathonRankingReward> mMarathonRankingRewardList = mMarathonRankingRewardRepository.findAll();
        assertThat(mMarathonRankingRewardList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllMMarathonRankingRewards() throws Exception {
        // Initialize the database
        mMarathonRankingRewardRepository.saveAndFlush(mMarathonRankingReward);

        // Get all the mMarathonRankingRewardList
        restMMarathonRankingRewardMockMvc.perform(get("/api/m-marathon-ranking-rewards?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(mMarathonRankingReward.getId().intValue())))
            .andExpect(jsonPath("$.[*].eventId").value(hasItem(DEFAULT_EVENT_ID)))
            .andExpect(jsonPath("$.[*].rankingFrom").value(hasItem(DEFAULT_RANKING_FROM)))
            .andExpect(jsonPath("$.[*].rankingTo").value(hasItem(DEFAULT_RANKING_TO)))
            .andExpect(jsonPath("$.[*].rewardGroupId").value(hasItem(DEFAULT_REWARD_GROUP_ID)));
    }
    
    @Test
    @Transactional
    public void getMMarathonRankingReward() throws Exception {
        // Initialize the database
        mMarathonRankingRewardRepository.saveAndFlush(mMarathonRankingReward);

        // Get the mMarathonRankingReward
        restMMarathonRankingRewardMockMvc.perform(get("/api/m-marathon-ranking-rewards/{id}", mMarathonRankingReward.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(mMarathonRankingReward.getId().intValue()))
            .andExpect(jsonPath("$.eventId").value(DEFAULT_EVENT_ID))
            .andExpect(jsonPath("$.rankingFrom").value(DEFAULT_RANKING_FROM))
            .andExpect(jsonPath("$.rankingTo").value(DEFAULT_RANKING_TO))
            .andExpect(jsonPath("$.rewardGroupId").value(DEFAULT_REWARD_GROUP_ID));
    }

    @Test
    @Transactional
    public void getAllMMarathonRankingRewardsByEventIdIsEqualToSomething() throws Exception {
        // Initialize the database
        mMarathonRankingRewardRepository.saveAndFlush(mMarathonRankingReward);

        // Get all the mMarathonRankingRewardList where eventId equals to DEFAULT_EVENT_ID
        defaultMMarathonRankingRewardShouldBeFound("eventId.equals=" + DEFAULT_EVENT_ID);

        // Get all the mMarathonRankingRewardList where eventId equals to UPDATED_EVENT_ID
        defaultMMarathonRankingRewardShouldNotBeFound("eventId.equals=" + UPDATED_EVENT_ID);
    }

    @Test
    @Transactional
    public void getAllMMarathonRankingRewardsByEventIdIsInShouldWork() throws Exception {
        // Initialize the database
        mMarathonRankingRewardRepository.saveAndFlush(mMarathonRankingReward);

        // Get all the mMarathonRankingRewardList where eventId in DEFAULT_EVENT_ID or UPDATED_EVENT_ID
        defaultMMarathonRankingRewardShouldBeFound("eventId.in=" + DEFAULT_EVENT_ID + "," + UPDATED_EVENT_ID);

        // Get all the mMarathonRankingRewardList where eventId equals to UPDATED_EVENT_ID
        defaultMMarathonRankingRewardShouldNotBeFound("eventId.in=" + UPDATED_EVENT_ID);
    }

    @Test
    @Transactional
    public void getAllMMarathonRankingRewardsByEventIdIsNullOrNotNull() throws Exception {
        // Initialize the database
        mMarathonRankingRewardRepository.saveAndFlush(mMarathonRankingReward);

        // Get all the mMarathonRankingRewardList where eventId is not null
        defaultMMarathonRankingRewardShouldBeFound("eventId.specified=true");

        // Get all the mMarathonRankingRewardList where eventId is null
        defaultMMarathonRankingRewardShouldNotBeFound("eventId.specified=false");
    }

    @Test
    @Transactional
    public void getAllMMarathonRankingRewardsByEventIdIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mMarathonRankingRewardRepository.saveAndFlush(mMarathonRankingReward);

        // Get all the mMarathonRankingRewardList where eventId greater than or equals to DEFAULT_EVENT_ID
        defaultMMarathonRankingRewardShouldBeFound("eventId.greaterOrEqualThan=" + DEFAULT_EVENT_ID);

        // Get all the mMarathonRankingRewardList where eventId greater than or equals to UPDATED_EVENT_ID
        defaultMMarathonRankingRewardShouldNotBeFound("eventId.greaterOrEqualThan=" + UPDATED_EVENT_ID);
    }

    @Test
    @Transactional
    public void getAllMMarathonRankingRewardsByEventIdIsLessThanSomething() throws Exception {
        // Initialize the database
        mMarathonRankingRewardRepository.saveAndFlush(mMarathonRankingReward);

        // Get all the mMarathonRankingRewardList where eventId less than or equals to DEFAULT_EVENT_ID
        defaultMMarathonRankingRewardShouldNotBeFound("eventId.lessThan=" + DEFAULT_EVENT_ID);

        // Get all the mMarathonRankingRewardList where eventId less than or equals to UPDATED_EVENT_ID
        defaultMMarathonRankingRewardShouldBeFound("eventId.lessThan=" + UPDATED_EVENT_ID);
    }


    @Test
    @Transactional
    public void getAllMMarathonRankingRewardsByRankingFromIsEqualToSomething() throws Exception {
        // Initialize the database
        mMarathonRankingRewardRepository.saveAndFlush(mMarathonRankingReward);

        // Get all the mMarathonRankingRewardList where rankingFrom equals to DEFAULT_RANKING_FROM
        defaultMMarathonRankingRewardShouldBeFound("rankingFrom.equals=" + DEFAULT_RANKING_FROM);

        // Get all the mMarathonRankingRewardList where rankingFrom equals to UPDATED_RANKING_FROM
        defaultMMarathonRankingRewardShouldNotBeFound("rankingFrom.equals=" + UPDATED_RANKING_FROM);
    }

    @Test
    @Transactional
    public void getAllMMarathonRankingRewardsByRankingFromIsInShouldWork() throws Exception {
        // Initialize the database
        mMarathonRankingRewardRepository.saveAndFlush(mMarathonRankingReward);

        // Get all the mMarathonRankingRewardList where rankingFrom in DEFAULT_RANKING_FROM or UPDATED_RANKING_FROM
        defaultMMarathonRankingRewardShouldBeFound("rankingFrom.in=" + DEFAULT_RANKING_FROM + "," + UPDATED_RANKING_FROM);

        // Get all the mMarathonRankingRewardList where rankingFrom equals to UPDATED_RANKING_FROM
        defaultMMarathonRankingRewardShouldNotBeFound("rankingFrom.in=" + UPDATED_RANKING_FROM);
    }

    @Test
    @Transactional
    public void getAllMMarathonRankingRewardsByRankingFromIsNullOrNotNull() throws Exception {
        // Initialize the database
        mMarathonRankingRewardRepository.saveAndFlush(mMarathonRankingReward);

        // Get all the mMarathonRankingRewardList where rankingFrom is not null
        defaultMMarathonRankingRewardShouldBeFound("rankingFrom.specified=true");

        // Get all the mMarathonRankingRewardList where rankingFrom is null
        defaultMMarathonRankingRewardShouldNotBeFound("rankingFrom.specified=false");
    }

    @Test
    @Transactional
    public void getAllMMarathonRankingRewardsByRankingFromIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mMarathonRankingRewardRepository.saveAndFlush(mMarathonRankingReward);

        // Get all the mMarathonRankingRewardList where rankingFrom greater than or equals to DEFAULT_RANKING_FROM
        defaultMMarathonRankingRewardShouldBeFound("rankingFrom.greaterOrEqualThan=" + DEFAULT_RANKING_FROM);

        // Get all the mMarathonRankingRewardList where rankingFrom greater than or equals to UPDATED_RANKING_FROM
        defaultMMarathonRankingRewardShouldNotBeFound("rankingFrom.greaterOrEqualThan=" + UPDATED_RANKING_FROM);
    }

    @Test
    @Transactional
    public void getAllMMarathonRankingRewardsByRankingFromIsLessThanSomething() throws Exception {
        // Initialize the database
        mMarathonRankingRewardRepository.saveAndFlush(mMarathonRankingReward);

        // Get all the mMarathonRankingRewardList where rankingFrom less than or equals to DEFAULT_RANKING_FROM
        defaultMMarathonRankingRewardShouldNotBeFound("rankingFrom.lessThan=" + DEFAULT_RANKING_FROM);

        // Get all the mMarathonRankingRewardList where rankingFrom less than or equals to UPDATED_RANKING_FROM
        defaultMMarathonRankingRewardShouldBeFound("rankingFrom.lessThan=" + UPDATED_RANKING_FROM);
    }


    @Test
    @Transactional
    public void getAllMMarathonRankingRewardsByRankingToIsEqualToSomething() throws Exception {
        // Initialize the database
        mMarathonRankingRewardRepository.saveAndFlush(mMarathonRankingReward);

        // Get all the mMarathonRankingRewardList where rankingTo equals to DEFAULT_RANKING_TO
        defaultMMarathonRankingRewardShouldBeFound("rankingTo.equals=" + DEFAULT_RANKING_TO);

        // Get all the mMarathonRankingRewardList where rankingTo equals to UPDATED_RANKING_TO
        defaultMMarathonRankingRewardShouldNotBeFound("rankingTo.equals=" + UPDATED_RANKING_TO);
    }

    @Test
    @Transactional
    public void getAllMMarathonRankingRewardsByRankingToIsInShouldWork() throws Exception {
        // Initialize the database
        mMarathonRankingRewardRepository.saveAndFlush(mMarathonRankingReward);

        // Get all the mMarathonRankingRewardList where rankingTo in DEFAULT_RANKING_TO or UPDATED_RANKING_TO
        defaultMMarathonRankingRewardShouldBeFound("rankingTo.in=" + DEFAULT_RANKING_TO + "," + UPDATED_RANKING_TO);

        // Get all the mMarathonRankingRewardList where rankingTo equals to UPDATED_RANKING_TO
        defaultMMarathonRankingRewardShouldNotBeFound("rankingTo.in=" + UPDATED_RANKING_TO);
    }

    @Test
    @Transactional
    public void getAllMMarathonRankingRewardsByRankingToIsNullOrNotNull() throws Exception {
        // Initialize the database
        mMarathonRankingRewardRepository.saveAndFlush(mMarathonRankingReward);

        // Get all the mMarathonRankingRewardList where rankingTo is not null
        defaultMMarathonRankingRewardShouldBeFound("rankingTo.specified=true");

        // Get all the mMarathonRankingRewardList where rankingTo is null
        defaultMMarathonRankingRewardShouldNotBeFound("rankingTo.specified=false");
    }

    @Test
    @Transactional
    public void getAllMMarathonRankingRewardsByRankingToIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mMarathonRankingRewardRepository.saveAndFlush(mMarathonRankingReward);

        // Get all the mMarathonRankingRewardList where rankingTo greater than or equals to DEFAULT_RANKING_TO
        defaultMMarathonRankingRewardShouldBeFound("rankingTo.greaterOrEqualThan=" + DEFAULT_RANKING_TO);

        // Get all the mMarathonRankingRewardList where rankingTo greater than or equals to UPDATED_RANKING_TO
        defaultMMarathonRankingRewardShouldNotBeFound("rankingTo.greaterOrEqualThan=" + UPDATED_RANKING_TO);
    }

    @Test
    @Transactional
    public void getAllMMarathonRankingRewardsByRankingToIsLessThanSomething() throws Exception {
        // Initialize the database
        mMarathonRankingRewardRepository.saveAndFlush(mMarathonRankingReward);

        // Get all the mMarathonRankingRewardList where rankingTo less than or equals to DEFAULT_RANKING_TO
        defaultMMarathonRankingRewardShouldNotBeFound("rankingTo.lessThan=" + DEFAULT_RANKING_TO);

        // Get all the mMarathonRankingRewardList where rankingTo less than or equals to UPDATED_RANKING_TO
        defaultMMarathonRankingRewardShouldBeFound("rankingTo.lessThan=" + UPDATED_RANKING_TO);
    }


    @Test
    @Transactional
    public void getAllMMarathonRankingRewardsByRewardGroupIdIsEqualToSomething() throws Exception {
        // Initialize the database
        mMarathonRankingRewardRepository.saveAndFlush(mMarathonRankingReward);

        // Get all the mMarathonRankingRewardList where rewardGroupId equals to DEFAULT_REWARD_GROUP_ID
        defaultMMarathonRankingRewardShouldBeFound("rewardGroupId.equals=" + DEFAULT_REWARD_GROUP_ID);

        // Get all the mMarathonRankingRewardList where rewardGroupId equals to UPDATED_REWARD_GROUP_ID
        defaultMMarathonRankingRewardShouldNotBeFound("rewardGroupId.equals=" + UPDATED_REWARD_GROUP_ID);
    }

    @Test
    @Transactional
    public void getAllMMarathonRankingRewardsByRewardGroupIdIsInShouldWork() throws Exception {
        // Initialize the database
        mMarathonRankingRewardRepository.saveAndFlush(mMarathonRankingReward);

        // Get all the mMarathonRankingRewardList where rewardGroupId in DEFAULT_REWARD_GROUP_ID or UPDATED_REWARD_GROUP_ID
        defaultMMarathonRankingRewardShouldBeFound("rewardGroupId.in=" + DEFAULT_REWARD_GROUP_ID + "," + UPDATED_REWARD_GROUP_ID);

        // Get all the mMarathonRankingRewardList where rewardGroupId equals to UPDATED_REWARD_GROUP_ID
        defaultMMarathonRankingRewardShouldNotBeFound("rewardGroupId.in=" + UPDATED_REWARD_GROUP_ID);
    }

    @Test
    @Transactional
    public void getAllMMarathonRankingRewardsByRewardGroupIdIsNullOrNotNull() throws Exception {
        // Initialize the database
        mMarathonRankingRewardRepository.saveAndFlush(mMarathonRankingReward);

        // Get all the mMarathonRankingRewardList where rewardGroupId is not null
        defaultMMarathonRankingRewardShouldBeFound("rewardGroupId.specified=true");

        // Get all the mMarathonRankingRewardList where rewardGroupId is null
        defaultMMarathonRankingRewardShouldNotBeFound("rewardGroupId.specified=false");
    }

    @Test
    @Transactional
    public void getAllMMarathonRankingRewardsByRewardGroupIdIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mMarathonRankingRewardRepository.saveAndFlush(mMarathonRankingReward);

        // Get all the mMarathonRankingRewardList where rewardGroupId greater than or equals to DEFAULT_REWARD_GROUP_ID
        defaultMMarathonRankingRewardShouldBeFound("rewardGroupId.greaterOrEqualThan=" + DEFAULT_REWARD_GROUP_ID);

        // Get all the mMarathonRankingRewardList where rewardGroupId greater than or equals to UPDATED_REWARD_GROUP_ID
        defaultMMarathonRankingRewardShouldNotBeFound("rewardGroupId.greaterOrEqualThan=" + UPDATED_REWARD_GROUP_ID);
    }

    @Test
    @Transactional
    public void getAllMMarathonRankingRewardsByRewardGroupIdIsLessThanSomething() throws Exception {
        // Initialize the database
        mMarathonRankingRewardRepository.saveAndFlush(mMarathonRankingReward);

        // Get all the mMarathonRankingRewardList where rewardGroupId less than or equals to DEFAULT_REWARD_GROUP_ID
        defaultMMarathonRankingRewardShouldNotBeFound("rewardGroupId.lessThan=" + DEFAULT_REWARD_GROUP_ID);

        // Get all the mMarathonRankingRewardList where rewardGroupId less than or equals to UPDATED_REWARD_GROUP_ID
        defaultMMarathonRankingRewardShouldBeFound("rewardGroupId.lessThan=" + UPDATED_REWARD_GROUP_ID);
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultMMarathonRankingRewardShouldBeFound(String filter) throws Exception {
        restMMarathonRankingRewardMockMvc.perform(get("/api/m-marathon-ranking-rewards?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(mMarathonRankingReward.getId().intValue())))
            .andExpect(jsonPath("$.[*].eventId").value(hasItem(DEFAULT_EVENT_ID)))
            .andExpect(jsonPath("$.[*].rankingFrom").value(hasItem(DEFAULT_RANKING_FROM)))
            .andExpect(jsonPath("$.[*].rankingTo").value(hasItem(DEFAULT_RANKING_TO)))
            .andExpect(jsonPath("$.[*].rewardGroupId").value(hasItem(DEFAULT_REWARD_GROUP_ID)));

        // Check, that the count call also returns 1
        restMMarathonRankingRewardMockMvc.perform(get("/api/m-marathon-ranking-rewards/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultMMarathonRankingRewardShouldNotBeFound(String filter) throws Exception {
        restMMarathonRankingRewardMockMvc.perform(get("/api/m-marathon-ranking-rewards?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restMMarathonRankingRewardMockMvc.perform(get("/api/m-marathon-ranking-rewards/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("0"));
    }


    @Test
    @Transactional
    public void getNonExistingMMarathonRankingReward() throws Exception {
        // Get the mMarathonRankingReward
        restMMarathonRankingRewardMockMvc.perform(get("/api/m-marathon-ranking-rewards/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateMMarathonRankingReward() throws Exception {
        // Initialize the database
        mMarathonRankingRewardRepository.saveAndFlush(mMarathonRankingReward);

        int databaseSizeBeforeUpdate = mMarathonRankingRewardRepository.findAll().size();

        // Update the mMarathonRankingReward
        MMarathonRankingReward updatedMMarathonRankingReward = mMarathonRankingRewardRepository.findById(mMarathonRankingReward.getId()).get();
        // Disconnect from session so that the updates on updatedMMarathonRankingReward are not directly saved in db
        em.detach(updatedMMarathonRankingReward);
        updatedMMarathonRankingReward
            .eventId(UPDATED_EVENT_ID)
            .rankingFrom(UPDATED_RANKING_FROM)
            .rankingTo(UPDATED_RANKING_TO)
            .rewardGroupId(UPDATED_REWARD_GROUP_ID);
        MMarathonRankingRewardDTO mMarathonRankingRewardDTO = mMarathonRankingRewardMapper.toDto(updatedMMarathonRankingReward);

        restMMarathonRankingRewardMockMvc.perform(put("/api/m-marathon-ranking-rewards")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mMarathonRankingRewardDTO)))
            .andExpect(status().isOk());

        // Validate the MMarathonRankingReward in the database
        List<MMarathonRankingReward> mMarathonRankingRewardList = mMarathonRankingRewardRepository.findAll();
        assertThat(mMarathonRankingRewardList).hasSize(databaseSizeBeforeUpdate);
        MMarathonRankingReward testMMarathonRankingReward = mMarathonRankingRewardList.get(mMarathonRankingRewardList.size() - 1);
        assertThat(testMMarathonRankingReward.getEventId()).isEqualTo(UPDATED_EVENT_ID);
        assertThat(testMMarathonRankingReward.getRankingFrom()).isEqualTo(UPDATED_RANKING_FROM);
        assertThat(testMMarathonRankingReward.getRankingTo()).isEqualTo(UPDATED_RANKING_TO);
        assertThat(testMMarathonRankingReward.getRewardGroupId()).isEqualTo(UPDATED_REWARD_GROUP_ID);
    }

    @Test
    @Transactional
    public void updateNonExistingMMarathonRankingReward() throws Exception {
        int databaseSizeBeforeUpdate = mMarathonRankingRewardRepository.findAll().size();

        // Create the MMarathonRankingReward
        MMarathonRankingRewardDTO mMarathonRankingRewardDTO = mMarathonRankingRewardMapper.toDto(mMarathonRankingReward);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restMMarathonRankingRewardMockMvc.perform(put("/api/m-marathon-ranking-rewards")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mMarathonRankingRewardDTO)))
            .andExpect(status().isBadRequest());

        // Validate the MMarathonRankingReward in the database
        List<MMarathonRankingReward> mMarathonRankingRewardList = mMarathonRankingRewardRepository.findAll();
        assertThat(mMarathonRankingRewardList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteMMarathonRankingReward() throws Exception {
        // Initialize the database
        mMarathonRankingRewardRepository.saveAndFlush(mMarathonRankingReward);

        int databaseSizeBeforeDelete = mMarathonRankingRewardRepository.findAll().size();

        // Delete the mMarathonRankingReward
        restMMarathonRankingRewardMockMvc.perform(delete("/api/m-marathon-ranking-rewards/{id}", mMarathonRankingReward.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database is empty
        List<MMarathonRankingReward> mMarathonRankingRewardList = mMarathonRankingRewardRepository.findAll();
        assertThat(mMarathonRankingRewardList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(MMarathonRankingReward.class);
        MMarathonRankingReward mMarathonRankingReward1 = new MMarathonRankingReward();
        mMarathonRankingReward1.setId(1L);
        MMarathonRankingReward mMarathonRankingReward2 = new MMarathonRankingReward();
        mMarathonRankingReward2.setId(mMarathonRankingReward1.getId());
        assertThat(mMarathonRankingReward1).isEqualTo(mMarathonRankingReward2);
        mMarathonRankingReward2.setId(2L);
        assertThat(mMarathonRankingReward1).isNotEqualTo(mMarathonRankingReward2);
        mMarathonRankingReward1.setId(null);
        assertThat(mMarathonRankingReward1).isNotEqualTo(mMarathonRankingReward2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(MMarathonRankingRewardDTO.class);
        MMarathonRankingRewardDTO mMarathonRankingRewardDTO1 = new MMarathonRankingRewardDTO();
        mMarathonRankingRewardDTO1.setId(1L);
        MMarathonRankingRewardDTO mMarathonRankingRewardDTO2 = new MMarathonRankingRewardDTO();
        assertThat(mMarathonRankingRewardDTO1).isNotEqualTo(mMarathonRankingRewardDTO2);
        mMarathonRankingRewardDTO2.setId(mMarathonRankingRewardDTO1.getId());
        assertThat(mMarathonRankingRewardDTO1).isEqualTo(mMarathonRankingRewardDTO2);
        mMarathonRankingRewardDTO2.setId(2L);
        assertThat(mMarathonRankingRewardDTO1).isNotEqualTo(mMarathonRankingRewardDTO2);
        mMarathonRankingRewardDTO1.setId(null);
        assertThat(mMarathonRankingRewardDTO1).isNotEqualTo(mMarathonRankingRewardDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(mMarathonRankingRewardMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(mMarathonRankingRewardMapper.fromId(null)).isNull();
    }
}
