package io.shm.tsubasa.web.rest;

import io.shm.tsubasa.TsubasaApp;
import io.shm.tsubasa.domain.MMarathonAchievementReward;
import io.shm.tsubasa.repository.MMarathonAchievementRewardRepository;
import io.shm.tsubasa.service.MMarathonAchievementRewardService;
import io.shm.tsubasa.service.dto.MMarathonAchievementRewardDTO;
import io.shm.tsubasa.service.mapper.MMarathonAchievementRewardMapper;
import io.shm.tsubasa.web.rest.errors.ExceptionTranslator;
import io.shm.tsubasa.service.dto.MMarathonAchievementRewardCriteria;
import io.shm.tsubasa.service.MMarathonAchievementRewardQueryService;

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
 * Integration tests for the {@Link MMarathonAchievementRewardResource} REST controller.
 */
@SpringBootTest(classes = TsubasaApp.class)
public class MMarathonAchievementRewardResourceIT {

    private static final Integer DEFAULT_EVENT_ID = 1;
    private static final Integer UPDATED_EVENT_ID = 2;

    private static final Integer DEFAULT_EVENT_POINT = 1;
    private static final Integer UPDATED_EVENT_POINT = 2;

    private static final Integer DEFAULT_REWARD_GROUP_ID = 1;
    private static final Integer UPDATED_REWARD_GROUP_ID = 2;

    @Autowired
    private MMarathonAchievementRewardRepository mMarathonAchievementRewardRepository;

    @Autowired
    private MMarathonAchievementRewardMapper mMarathonAchievementRewardMapper;

    @Autowired
    private MMarathonAchievementRewardService mMarathonAchievementRewardService;

    @Autowired
    private MMarathonAchievementRewardQueryService mMarathonAchievementRewardQueryService;

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

    private MockMvc restMMarathonAchievementRewardMockMvc;

    private MMarathonAchievementReward mMarathonAchievementReward;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final MMarathonAchievementRewardResource mMarathonAchievementRewardResource = new MMarathonAchievementRewardResource(mMarathonAchievementRewardService, mMarathonAchievementRewardQueryService);
        this.restMMarathonAchievementRewardMockMvc = MockMvcBuilders.standaloneSetup(mMarathonAchievementRewardResource)
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
    public static MMarathonAchievementReward createEntity(EntityManager em) {
        MMarathonAchievementReward mMarathonAchievementReward = new MMarathonAchievementReward()
            .eventId(DEFAULT_EVENT_ID)
            .eventPoint(DEFAULT_EVENT_POINT)
            .rewardGroupId(DEFAULT_REWARD_GROUP_ID);
        return mMarathonAchievementReward;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static MMarathonAchievementReward createUpdatedEntity(EntityManager em) {
        MMarathonAchievementReward mMarathonAchievementReward = new MMarathonAchievementReward()
            .eventId(UPDATED_EVENT_ID)
            .eventPoint(UPDATED_EVENT_POINT)
            .rewardGroupId(UPDATED_REWARD_GROUP_ID);
        return mMarathonAchievementReward;
    }

    @BeforeEach
    public void initTest() {
        mMarathonAchievementReward = createEntity(em);
    }

    @Test
    @Transactional
    public void createMMarathonAchievementReward() throws Exception {
        int databaseSizeBeforeCreate = mMarathonAchievementRewardRepository.findAll().size();

        // Create the MMarathonAchievementReward
        MMarathonAchievementRewardDTO mMarathonAchievementRewardDTO = mMarathonAchievementRewardMapper.toDto(mMarathonAchievementReward);
        restMMarathonAchievementRewardMockMvc.perform(post("/api/m-marathon-achievement-rewards")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mMarathonAchievementRewardDTO)))
            .andExpect(status().isCreated());

        // Validate the MMarathonAchievementReward in the database
        List<MMarathonAchievementReward> mMarathonAchievementRewardList = mMarathonAchievementRewardRepository.findAll();
        assertThat(mMarathonAchievementRewardList).hasSize(databaseSizeBeforeCreate + 1);
        MMarathonAchievementReward testMMarathonAchievementReward = mMarathonAchievementRewardList.get(mMarathonAchievementRewardList.size() - 1);
        assertThat(testMMarathonAchievementReward.getEventId()).isEqualTo(DEFAULT_EVENT_ID);
        assertThat(testMMarathonAchievementReward.getEventPoint()).isEqualTo(DEFAULT_EVENT_POINT);
        assertThat(testMMarathonAchievementReward.getRewardGroupId()).isEqualTo(DEFAULT_REWARD_GROUP_ID);
    }

    @Test
    @Transactional
    public void createMMarathonAchievementRewardWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = mMarathonAchievementRewardRepository.findAll().size();

        // Create the MMarathonAchievementReward with an existing ID
        mMarathonAchievementReward.setId(1L);
        MMarathonAchievementRewardDTO mMarathonAchievementRewardDTO = mMarathonAchievementRewardMapper.toDto(mMarathonAchievementReward);

        // An entity with an existing ID cannot be created, so this API call must fail
        restMMarathonAchievementRewardMockMvc.perform(post("/api/m-marathon-achievement-rewards")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mMarathonAchievementRewardDTO)))
            .andExpect(status().isBadRequest());

        // Validate the MMarathonAchievementReward in the database
        List<MMarathonAchievementReward> mMarathonAchievementRewardList = mMarathonAchievementRewardRepository.findAll();
        assertThat(mMarathonAchievementRewardList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkEventIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = mMarathonAchievementRewardRepository.findAll().size();
        // set the field null
        mMarathonAchievementReward.setEventId(null);

        // Create the MMarathonAchievementReward, which fails.
        MMarathonAchievementRewardDTO mMarathonAchievementRewardDTO = mMarathonAchievementRewardMapper.toDto(mMarathonAchievementReward);

        restMMarathonAchievementRewardMockMvc.perform(post("/api/m-marathon-achievement-rewards")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mMarathonAchievementRewardDTO)))
            .andExpect(status().isBadRequest());

        List<MMarathonAchievementReward> mMarathonAchievementRewardList = mMarathonAchievementRewardRepository.findAll();
        assertThat(mMarathonAchievementRewardList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkEventPointIsRequired() throws Exception {
        int databaseSizeBeforeTest = mMarathonAchievementRewardRepository.findAll().size();
        // set the field null
        mMarathonAchievementReward.setEventPoint(null);

        // Create the MMarathonAchievementReward, which fails.
        MMarathonAchievementRewardDTO mMarathonAchievementRewardDTO = mMarathonAchievementRewardMapper.toDto(mMarathonAchievementReward);

        restMMarathonAchievementRewardMockMvc.perform(post("/api/m-marathon-achievement-rewards")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mMarathonAchievementRewardDTO)))
            .andExpect(status().isBadRequest());

        List<MMarathonAchievementReward> mMarathonAchievementRewardList = mMarathonAchievementRewardRepository.findAll();
        assertThat(mMarathonAchievementRewardList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkRewardGroupIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = mMarathonAchievementRewardRepository.findAll().size();
        // set the field null
        mMarathonAchievementReward.setRewardGroupId(null);

        // Create the MMarathonAchievementReward, which fails.
        MMarathonAchievementRewardDTO mMarathonAchievementRewardDTO = mMarathonAchievementRewardMapper.toDto(mMarathonAchievementReward);

        restMMarathonAchievementRewardMockMvc.perform(post("/api/m-marathon-achievement-rewards")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mMarathonAchievementRewardDTO)))
            .andExpect(status().isBadRequest());

        List<MMarathonAchievementReward> mMarathonAchievementRewardList = mMarathonAchievementRewardRepository.findAll();
        assertThat(mMarathonAchievementRewardList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllMMarathonAchievementRewards() throws Exception {
        // Initialize the database
        mMarathonAchievementRewardRepository.saveAndFlush(mMarathonAchievementReward);

        // Get all the mMarathonAchievementRewardList
        restMMarathonAchievementRewardMockMvc.perform(get("/api/m-marathon-achievement-rewards?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(mMarathonAchievementReward.getId().intValue())))
            .andExpect(jsonPath("$.[*].eventId").value(hasItem(DEFAULT_EVENT_ID)))
            .andExpect(jsonPath("$.[*].eventPoint").value(hasItem(DEFAULT_EVENT_POINT)))
            .andExpect(jsonPath("$.[*].rewardGroupId").value(hasItem(DEFAULT_REWARD_GROUP_ID)));
    }
    
    @Test
    @Transactional
    public void getMMarathonAchievementReward() throws Exception {
        // Initialize the database
        mMarathonAchievementRewardRepository.saveAndFlush(mMarathonAchievementReward);

        // Get the mMarathonAchievementReward
        restMMarathonAchievementRewardMockMvc.perform(get("/api/m-marathon-achievement-rewards/{id}", mMarathonAchievementReward.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(mMarathonAchievementReward.getId().intValue()))
            .andExpect(jsonPath("$.eventId").value(DEFAULT_EVENT_ID))
            .andExpect(jsonPath("$.eventPoint").value(DEFAULT_EVENT_POINT))
            .andExpect(jsonPath("$.rewardGroupId").value(DEFAULT_REWARD_GROUP_ID));
    }

    @Test
    @Transactional
    public void getAllMMarathonAchievementRewardsByEventIdIsEqualToSomething() throws Exception {
        // Initialize the database
        mMarathonAchievementRewardRepository.saveAndFlush(mMarathonAchievementReward);

        // Get all the mMarathonAchievementRewardList where eventId equals to DEFAULT_EVENT_ID
        defaultMMarathonAchievementRewardShouldBeFound("eventId.equals=" + DEFAULT_EVENT_ID);

        // Get all the mMarathonAchievementRewardList where eventId equals to UPDATED_EVENT_ID
        defaultMMarathonAchievementRewardShouldNotBeFound("eventId.equals=" + UPDATED_EVENT_ID);
    }

    @Test
    @Transactional
    public void getAllMMarathonAchievementRewardsByEventIdIsInShouldWork() throws Exception {
        // Initialize the database
        mMarathonAchievementRewardRepository.saveAndFlush(mMarathonAchievementReward);

        // Get all the mMarathonAchievementRewardList where eventId in DEFAULT_EVENT_ID or UPDATED_EVENT_ID
        defaultMMarathonAchievementRewardShouldBeFound("eventId.in=" + DEFAULT_EVENT_ID + "," + UPDATED_EVENT_ID);

        // Get all the mMarathonAchievementRewardList where eventId equals to UPDATED_EVENT_ID
        defaultMMarathonAchievementRewardShouldNotBeFound("eventId.in=" + UPDATED_EVENT_ID);
    }

    @Test
    @Transactional
    public void getAllMMarathonAchievementRewardsByEventIdIsNullOrNotNull() throws Exception {
        // Initialize the database
        mMarathonAchievementRewardRepository.saveAndFlush(mMarathonAchievementReward);

        // Get all the mMarathonAchievementRewardList where eventId is not null
        defaultMMarathonAchievementRewardShouldBeFound("eventId.specified=true");

        // Get all the mMarathonAchievementRewardList where eventId is null
        defaultMMarathonAchievementRewardShouldNotBeFound("eventId.specified=false");
    }

    @Test
    @Transactional
    public void getAllMMarathonAchievementRewardsByEventIdIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mMarathonAchievementRewardRepository.saveAndFlush(mMarathonAchievementReward);

        // Get all the mMarathonAchievementRewardList where eventId greater than or equals to DEFAULT_EVENT_ID
        defaultMMarathonAchievementRewardShouldBeFound("eventId.greaterOrEqualThan=" + DEFAULT_EVENT_ID);

        // Get all the mMarathonAchievementRewardList where eventId greater than or equals to UPDATED_EVENT_ID
        defaultMMarathonAchievementRewardShouldNotBeFound("eventId.greaterOrEqualThan=" + UPDATED_EVENT_ID);
    }

    @Test
    @Transactional
    public void getAllMMarathonAchievementRewardsByEventIdIsLessThanSomething() throws Exception {
        // Initialize the database
        mMarathonAchievementRewardRepository.saveAndFlush(mMarathonAchievementReward);

        // Get all the mMarathonAchievementRewardList where eventId less than or equals to DEFAULT_EVENT_ID
        defaultMMarathonAchievementRewardShouldNotBeFound("eventId.lessThan=" + DEFAULT_EVENT_ID);

        // Get all the mMarathonAchievementRewardList where eventId less than or equals to UPDATED_EVENT_ID
        defaultMMarathonAchievementRewardShouldBeFound("eventId.lessThan=" + UPDATED_EVENT_ID);
    }


    @Test
    @Transactional
    public void getAllMMarathonAchievementRewardsByEventPointIsEqualToSomething() throws Exception {
        // Initialize the database
        mMarathonAchievementRewardRepository.saveAndFlush(mMarathonAchievementReward);

        // Get all the mMarathonAchievementRewardList where eventPoint equals to DEFAULT_EVENT_POINT
        defaultMMarathonAchievementRewardShouldBeFound("eventPoint.equals=" + DEFAULT_EVENT_POINT);

        // Get all the mMarathonAchievementRewardList where eventPoint equals to UPDATED_EVENT_POINT
        defaultMMarathonAchievementRewardShouldNotBeFound("eventPoint.equals=" + UPDATED_EVENT_POINT);
    }

    @Test
    @Transactional
    public void getAllMMarathonAchievementRewardsByEventPointIsInShouldWork() throws Exception {
        // Initialize the database
        mMarathonAchievementRewardRepository.saveAndFlush(mMarathonAchievementReward);

        // Get all the mMarathonAchievementRewardList where eventPoint in DEFAULT_EVENT_POINT or UPDATED_EVENT_POINT
        defaultMMarathonAchievementRewardShouldBeFound("eventPoint.in=" + DEFAULT_EVENT_POINT + "," + UPDATED_EVENT_POINT);

        // Get all the mMarathonAchievementRewardList where eventPoint equals to UPDATED_EVENT_POINT
        defaultMMarathonAchievementRewardShouldNotBeFound("eventPoint.in=" + UPDATED_EVENT_POINT);
    }

    @Test
    @Transactional
    public void getAllMMarathonAchievementRewardsByEventPointIsNullOrNotNull() throws Exception {
        // Initialize the database
        mMarathonAchievementRewardRepository.saveAndFlush(mMarathonAchievementReward);

        // Get all the mMarathonAchievementRewardList where eventPoint is not null
        defaultMMarathonAchievementRewardShouldBeFound("eventPoint.specified=true");

        // Get all the mMarathonAchievementRewardList where eventPoint is null
        defaultMMarathonAchievementRewardShouldNotBeFound("eventPoint.specified=false");
    }

    @Test
    @Transactional
    public void getAllMMarathonAchievementRewardsByEventPointIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mMarathonAchievementRewardRepository.saveAndFlush(mMarathonAchievementReward);

        // Get all the mMarathonAchievementRewardList where eventPoint greater than or equals to DEFAULT_EVENT_POINT
        defaultMMarathonAchievementRewardShouldBeFound("eventPoint.greaterOrEqualThan=" + DEFAULT_EVENT_POINT);

        // Get all the mMarathonAchievementRewardList where eventPoint greater than or equals to UPDATED_EVENT_POINT
        defaultMMarathonAchievementRewardShouldNotBeFound("eventPoint.greaterOrEqualThan=" + UPDATED_EVENT_POINT);
    }

    @Test
    @Transactional
    public void getAllMMarathonAchievementRewardsByEventPointIsLessThanSomething() throws Exception {
        // Initialize the database
        mMarathonAchievementRewardRepository.saveAndFlush(mMarathonAchievementReward);

        // Get all the mMarathonAchievementRewardList where eventPoint less than or equals to DEFAULT_EVENT_POINT
        defaultMMarathonAchievementRewardShouldNotBeFound("eventPoint.lessThan=" + DEFAULT_EVENT_POINT);

        // Get all the mMarathonAchievementRewardList where eventPoint less than or equals to UPDATED_EVENT_POINT
        defaultMMarathonAchievementRewardShouldBeFound("eventPoint.lessThan=" + UPDATED_EVENT_POINT);
    }


    @Test
    @Transactional
    public void getAllMMarathonAchievementRewardsByRewardGroupIdIsEqualToSomething() throws Exception {
        // Initialize the database
        mMarathonAchievementRewardRepository.saveAndFlush(mMarathonAchievementReward);

        // Get all the mMarathonAchievementRewardList where rewardGroupId equals to DEFAULT_REWARD_GROUP_ID
        defaultMMarathonAchievementRewardShouldBeFound("rewardGroupId.equals=" + DEFAULT_REWARD_GROUP_ID);

        // Get all the mMarathonAchievementRewardList where rewardGroupId equals to UPDATED_REWARD_GROUP_ID
        defaultMMarathonAchievementRewardShouldNotBeFound("rewardGroupId.equals=" + UPDATED_REWARD_GROUP_ID);
    }

    @Test
    @Transactional
    public void getAllMMarathonAchievementRewardsByRewardGroupIdIsInShouldWork() throws Exception {
        // Initialize the database
        mMarathonAchievementRewardRepository.saveAndFlush(mMarathonAchievementReward);

        // Get all the mMarathonAchievementRewardList where rewardGroupId in DEFAULT_REWARD_GROUP_ID or UPDATED_REWARD_GROUP_ID
        defaultMMarathonAchievementRewardShouldBeFound("rewardGroupId.in=" + DEFAULT_REWARD_GROUP_ID + "," + UPDATED_REWARD_GROUP_ID);

        // Get all the mMarathonAchievementRewardList where rewardGroupId equals to UPDATED_REWARD_GROUP_ID
        defaultMMarathonAchievementRewardShouldNotBeFound("rewardGroupId.in=" + UPDATED_REWARD_GROUP_ID);
    }

    @Test
    @Transactional
    public void getAllMMarathonAchievementRewardsByRewardGroupIdIsNullOrNotNull() throws Exception {
        // Initialize the database
        mMarathonAchievementRewardRepository.saveAndFlush(mMarathonAchievementReward);

        // Get all the mMarathonAchievementRewardList where rewardGroupId is not null
        defaultMMarathonAchievementRewardShouldBeFound("rewardGroupId.specified=true");

        // Get all the mMarathonAchievementRewardList where rewardGroupId is null
        defaultMMarathonAchievementRewardShouldNotBeFound("rewardGroupId.specified=false");
    }

    @Test
    @Transactional
    public void getAllMMarathonAchievementRewardsByRewardGroupIdIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mMarathonAchievementRewardRepository.saveAndFlush(mMarathonAchievementReward);

        // Get all the mMarathonAchievementRewardList where rewardGroupId greater than or equals to DEFAULT_REWARD_GROUP_ID
        defaultMMarathonAchievementRewardShouldBeFound("rewardGroupId.greaterOrEqualThan=" + DEFAULT_REWARD_GROUP_ID);

        // Get all the mMarathonAchievementRewardList where rewardGroupId greater than or equals to UPDATED_REWARD_GROUP_ID
        defaultMMarathonAchievementRewardShouldNotBeFound("rewardGroupId.greaterOrEqualThan=" + UPDATED_REWARD_GROUP_ID);
    }

    @Test
    @Transactional
    public void getAllMMarathonAchievementRewardsByRewardGroupIdIsLessThanSomething() throws Exception {
        // Initialize the database
        mMarathonAchievementRewardRepository.saveAndFlush(mMarathonAchievementReward);

        // Get all the mMarathonAchievementRewardList where rewardGroupId less than or equals to DEFAULT_REWARD_GROUP_ID
        defaultMMarathonAchievementRewardShouldNotBeFound("rewardGroupId.lessThan=" + DEFAULT_REWARD_GROUP_ID);

        // Get all the mMarathonAchievementRewardList where rewardGroupId less than or equals to UPDATED_REWARD_GROUP_ID
        defaultMMarathonAchievementRewardShouldBeFound("rewardGroupId.lessThan=" + UPDATED_REWARD_GROUP_ID);
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultMMarathonAchievementRewardShouldBeFound(String filter) throws Exception {
        restMMarathonAchievementRewardMockMvc.perform(get("/api/m-marathon-achievement-rewards?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(mMarathonAchievementReward.getId().intValue())))
            .andExpect(jsonPath("$.[*].eventId").value(hasItem(DEFAULT_EVENT_ID)))
            .andExpect(jsonPath("$.[*].eventPoint").value(hasItem(DEFAULT_EVENT_POINT)))
            .andExpect(jsonPath("$.[*].rewardGroupId").value(hasItem(DEFAULT_REWARD_GROUP_ID)));

        // Check, that the count call also returns 1
        restMMarathonAchievementRewardMockMvc.perform(get("/api/m-marathon-achievement-rewards/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultMMarathonAchievementRewardShouldNotBeFound(String filter) throws Exception {
        restMMarathonAchievementRewardMockMvc.perform(get("/api/m-marathon-achievement-rewards?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restMMarathonAchievementRewardMockMvc.perform(get("/api/m-marathon-achievement-rewards/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("0"));
    }


    @Test
    @Transactional
    public void getNonExistingMMarathonAchievementReward() throws Exception {
        // Get the mMarathonAchievementReward
        restMMarathonAchievementRewardMockMvc.perform(get("/api/m-marathon-achievement-rewards/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateMMarathonAchievementReward() throws Exception {
        // Initialize the database
        mMarathonAchievementRewardRepository.saveAndFlush(mMarathonAchievementReward);

        int databaseSizeBeforeUpdate = mMarathonAchievementRewardRepository.findAll().size();

        // Update the mMarathonAchievementReward
        MMarathonAchievementReward updatedMMarathonAchievementReward = mMarathonAchievementRewardRepository.findById(mMarathonAchievementReward.getId()).get();
        // Disconnect from session so that the updates on updatedMMarathonAchievementReward are not directly saved in db
        em.detach(updatedMMarathonAchievementReward);
        updatedMMarathonAchievementReward
            .eventId(UPDATED_EVENT_ID)
            .eventPoint(UPDATED_EVENT_POINT)
            .rewardGroupId(UPDATED_REWARD_GROUP_ID);
        MMarathonAchievementRewardDTO mMarathonAchievementRewardDTO = mMarathonAchievementRewardMapper.toDto(updatedMMarathonAchievementReward);

        restMMarathonAchievementRewardMockMvc.perform(put("/api/m-marathon-achievement-rewards")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mMarathonAchievementRewardDTO)))
            .andExpect(status().isOk());

        // Validate the MMarathonAchievementReward in the database
        List<MMarathonAchievementReward> mMarathonAchievementRewardList = mMarathonAchievementRewardRepository.findAll();
        assertThat(mMarathonAchievementRewardList).hasSize(databaseSizeBeforeUpdate);
        MMarathonAchievementReward testMMarathonAchievementReward = mMarathonAchievementRewardList.get(mMarathonAchievementRewardList.size() - 1);
        assertThat(testMMarathonAchievementReward.getEventId()).isEqualTo(UPDATED_EVENT_ID);
        assertThat(testMMarathonAchievementReward.getEventPoint()).isEqualTo(UPDATED_EVENT_POINT);
        assertThat(testMMarathonAchievementReward.getRewardGroupId()).isEqualTo(UPDATED_REWARD_GROUP_ID);
    }

    @Test
    @Transactional
    public void updateNonExistingMMarathonAchievementReward() throws Exception {
        int databaseSizeBeforeUpdate = mMarathonAchievementRewardRepository.findAll().size();

        // Create the MMarathonAchievementReward
        MMarathonAchievementRewardDTO mMarathonAchievementRewardDTO = mMarathonAchievementRewardMapper.toDto(mMarathonAchievementReward);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restMMarathonAchievementRewardMockMvc.perform(put("/api/m-marathon-achievement-rewards")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mMarathonAchievementRewardDTO)))
            .andExpect(status().isBadRequest());

        // Validate the MMarathonAchievementReward in the database
        List<MMarathonAchievementReward> mMarathonAchievementRewardList = mMarathonAchievementRewardRepository.findAll();
        assertThat(mMarathonAchievementRewardList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteMMarathonAchievementReward() throws Exception {
        // Initialize the database
        mMarathonAchievementRewardRepository.saveAndFlush(mMarathonAchievementReward);

        int databaseSizeBeforeDelete = mMarathonAchievementRewardRepository.findAll().size();

        // Delete the mMarathonAchievementReward
        restMMarathonAchievementRewardMockMvc.perform(delete("/api/m-marathon-achievement-rewards/{id}", mMarathonAchievementReward.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database is empty
        List<MMarathonAchievementReward> mMarathonAchievementRewardList = mMarathonAchievementRewardRepository.findAll();
        assertThat(mMarathonAchievementRewardList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(MMarathonAchievementReward.class);
        MMarathonAchievementReward mMarathonAchievementReward1 = new MMarathonAchievementReward();
        mMarathonAchievementReward1.setId(1L);
        MMarathonAchievementReward mMarathonAchievementReward2 = new MMarathonAchievementReward();
        mMarathonAchievementReward2.setId(mMarathonAchievementReward1.getId());
        assertThat(mMarathonAchievementReward1).isEqualTo(mMarathonAchievementReward2);
        mMarathonAchievementReward2.setId(2L);
        assertThat(mMarathonAchievementReward1).isNotEqualTo(mMarathonAchievementReward2);
        mMarathonAchievementReward1.setId(null);
        assertThat(mMarathonAchievementReward1).isNotEqualTo(mMarathonAchievementReward2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(MMarathonAchievementRewardDTO.class);
        MMarathonAchievementRewardDTO mMarathonAchievementRewardDTO1 = new MMarathonAchievementRewardDTO();
        mMarathonAchievementRewardDTO1.setId(1L);
        MMarathonAchievementRewardDTO mMarathonAchievementRewardDTO2 = new MMarathonAchievementRewardDTO();
        assertThat(mMarathonAchievementRewardDTO1).isNotEqualTo(mMarathonAchievementRewardDTO2);
        mMarathonAchievementRewardDTO2.setId(mMarathonAchievementRewardDTO1.getId());
        assertThat(mMarathonAchievementRewardDTO1).isEqualTo(mMarathonAchievementRewardDTO2);
        mMarathonAchievementRewardDTO2.setId(2L);
        assertThat(mMarathonAchievementRewardDTO1).isNotEqualTo(mMarathonAchievementRewardDTO2);
        mMarathonAchievementRewardDTO1.setId(null);
        assertThat(mMarathonAchievementRewardDTO1).isNotEqualTo(mMarathonAchievementRewardDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(mMarathonAchievementRewardMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(mMarathonAchievementRewardMapper.fromId(null)).isNull();
    }
}
