package io.shm.tsubasa.web.rest;

import io.shm.tsubasa.TsubasaApp;
import io.shm.tsubasa.domain.MQuestGoalReward;
import io.shm.tsubasa.repository.MQuestGoalRewardRepository;
import io.shm.tsubasa.service.MQuestGoalRewardService;
import io.shm.tsubasa.service.dto.MQuestGoalRewardDTO;
import io.shm.tsubasa.service.mapper.MQuestGoalRewardMapper;
import io.shm.tsubasa.web.rest.errors.ExceptionTranslator;
import io.shm.tsubasa.service.dto.MQuestGoalRewardCriteria;
import io.shm.tsubasa.service.MQuestGoalRewardQueryService;

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
import org.springframework.util.Base64Utils;
import org.springframework.validation.Validator;

import javax.persistence.EntityManager;
import java.util.List;

import static io.shm.tsubasa.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the {@Link MQuestGoalRewardResource} REST controller.
 */
@SpringBootTest(classes = TsubasaApp.class)
public class MQuestGoalRewardResourceIT {

    private static final Integer DEFAULT_GROUP_ID = 1;
    private static final Integer UPDATED_GROUP_ID = 2;

    private static final Integer DEFAULT_WEIGHT = 1;
    private static final Integer UPDATED_WEIGHT = 2;

    private static final String DEFAULT_ASSET_NAME = "AAAAAAAAAA";
    private static final String UPDATED_ASSET_NAME = "BBBBBBBBBB";

    private static final Integer DEFAULT_CONTENT_TYPE = 1;
    private static final Integer UPDATED_CONTENT_TYPE = 2;

    private static final Integer DEFAULT_CONTENT_ID = 1;
    private static final Integer UPDATED_CONTENT_ID = 2;

    private static final Integer DEFAULT_CONTENT_AMOUNT = 1;
    private static final Integer UPDATED_CONTENT_AMOUNT = 2;

    @Autowired
    private MQuestGoalRewardRepository mQuestGoalRewardRepository;

    @Autowired
    private MQuestGoalRewardMapper mQuestGoalRewardMapper;

    @Autowired
    private MQuestGoalRewardService mQuestGoalRewardService;

    @Autowired
    private MQuestGoalRewardQueryService mQuestGoalRewardQueryService;

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

    private MockMvc restMQuestGoalRewardMockMvc;

    private MQuestGoalReward mQuestGoalReward;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final MQuestGoalRewardResource mQuestGoalRewardResource = new MQuestGoalRewardResource(mQuestGoalRewardService, mQuestGoalRewardQueryService);
        this.restMQuestGoalRewardMockMvc = MockMvcBuilders.standaloneSetup(mQuestGoalRewardResource)
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
    public static MQuestGoalReward createEntity(EntityManager em) {
        MQuestGoalReward mQuestGoalReward = new MQuestGoalReward()
            .groupId(DEFAULT_GROUP_ID)
            .weight(DEFAULT_WEIGHT)
            .assetName(DEFAULT_ASSET_NAME)
            .contentType(DEFAULT_CONTENT_TYPE)
            .contentId(DEFAULT_CONTENT_ID)
            .contentAmount(DEFAULT_CONTENT_AMOUNT);
        return mQuestGoalReward;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static MQuestGoalReward createUpdatedEntity(EntityManager em) {
        MQuestGoalReward mQuestGoalReward = new MQuestGoalReward()
            .groupId(UPDATED_GROUP_ID)
            .weight(UPDATED_WEIGHT)
            .assetName(UPDATED_ASSET_NAME)
            .contentType(UPDATED_CONTENT_TYPE)
            .contentId(UPDATED_CONTENT_ID)
            .contentAmount(UPDATED_CONTENT_AMOUNT);
        return mQuestGoalReward;
    }

    @BeforeEach
    public void initTest() {
        mQuestGoalReward = createEntity(em);
    }

    @Test
    @Transactional
    public void createMQuestGoalReward() throws Exception {
        int databaseSizeBeforeCreate = mQuestGoalRewardRepository.findAll().size();

        // Create the MQuestGoalReward
        MQuestGoalRewardDTO mQuestGoalRewardDTO = mQuestGoalRewardMapper.toDto(mQuestGoalReward);
        restMQuestGoalRewardMockMvc.perform(post("/api/m-quest-goal-rewards")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mQuestGoalRewardDTO)))
            .andExpect(status().isCreated());

        // Validate the MQuestGoalReward in the database
        List<MQuestGoalReward> mQuestGoalRewardList = mQuestGoalRewardRepository.findAll();
        assertThat(mQuestGoalRewardList).hasSize(databaseSizeBeforeCreate + 1);
        MQuestGoalReward testMQuestGoalReward = mQuestGoalRewardList.get(mQuestGoalRewardList.size() - 1);
        assertThat(testMQuestGoalReward.getGroupId()).isEqualTo(DEFAULT_GROUP_ID);
        assertThat(testMQuestGoalReward.getWeight()).isEqualTo(DEFAULT_WEIGHT);
        assertThat(testMQuestGoalReward.getAssetName()).isEqualTo(DEFAULT_ASSET_NAME);
        assertThat(testMQuestGoalReward.getContentType()).isEqualTo(DEFAULT_CONTENT_TYPE);
        assertThat(testMQuestGoalReward.getContentId()).isEqualTo(DEFAULT_CONTENT_ID);
        assertThat(testMQuestGoalReward.getContentAmount()).isEqualTo(DEFAULT_CONTENT_AMOUNT);
    }

    @Test
    @Transactional
    public void createMQuestGoalRewardWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = mQuestGoalRewardRepository.findAll().size();

        // Create the MQuestGoalReward with an existing ID
        mQuestGoalReward.setId(1L);
        MQuestGoalRewardDTO mQuestGoalRewardDTO = mQuestGoalRewardMapper.toDto(mQuestGoalReward);

        // An entity with an existing ID cannot be created, so this API call must fail
        restMQuestGoalRewardMockMvc.perform(post("/api/m-quest-goal-rewards")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mQuestGoalRewardDTO)))
            .andExpect(status().isBadRequest());

        // Validate the MQuestGoalReward in the database
        List<MQuestGoalReward> mQuestGoalRewardList = mQuestGoalRewardRepository.findAll();
        assertThat(mQuestGoalRewardList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkGroupIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = mQuestGoalRewardRepository.findAll().size();
        // set the field null
        mQuestGoalReward.setGroupId(null);

        // Create the MQuestGoalReward, which fails.
        MQuestGoalRewardDTO mQuestGoalRewardDTO = mQuestGoalRewardMapper.toDto(mQuestGoalReward);

        restMQuestGoalRewardMockMvc.perform(post("/api/m-quest-goal-rewards")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mQuestGoalRewardDTO)))
            .andExpect(status().isBadRequest());

        List<MQuestGoalReward> mQuestGoalRewardList = mQuestGoalRewardRepository.findAll();
        assertThat(mQuestGoalRewardList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkWeightIsRequired() throws Exception {
        int databaseSizeBeforeTest = mQuestGoalRewardRepository.findAll().size();
        // set the field null
        mQuestGoalReward.setWeight(null);

        // Create the MQuestGoalReward, which fails.
        MQuestGoalRewardDTO mQuestGoalRewardDTO = mQuestGoalRewardMapper.toDto(mQuestGoalReward);

        restMQuestGoalRewardMockMvc.perform(post("/api/m-quest-goal-rewards")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mQuestGoalRewardDTO)))
            .andExpect(status().isBadRequest());

        List<MQuestGoalReward> mQuestGoalRewardList = mQuestGoalRewardRepository.findAll();
        assertThat(mQuestGoalRewardList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkContentTypeIsRequired() throws Exception {
        int databaseSizeBeforeTest = mQuestGoalRewardRepository.findAll().size();
        // set the field null
        mQuestGoalReward.setContentType(null);

        // Create the MQuestGoalReward, which fails.
        MQuestGoalRewardDTO mQuestGoalRewardDTO = mQuestGoalRewardMapper.toDto(mQuestGoalReward);

        restMQuestGoalRewardMockMvc.perform(post("/api/m-quest-goal-rewards")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mQuestGoalRewardDTO)))
            .andExpect(status().isBadRequest());

        List<MQuestGoalReward> mQuestGoalRewardList = mQuestGoalRewardRepository.findAll();
        assertThat(mQuestGoalRewardList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkContentAmountIsRequired() throws Exception {
        int databaseSizeBeforeTest = mQuestGoalRewardRepository.findAll().size();
        // set the field null
        mQuestGoalReward.setContentAmount(null);

        // Create the MQuestGoalReward, which fails.
        MQuestGoalRewardDTO mQuestGoalRewardDTO = mQuestGoalRewardMapper.toDto(mQuestGoalReward);

        restMQuestGoalRewardMockMvc.perform(post("/api/m-quest-goal-rewards")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mQuestGoalRewardDTO)))
            .andExpect(status().isBadRequest());

        List<MQuestGoalReward> mQuestGoalRewardList = mQuestGoalRewardRepository.findAll();
        assertThat(mQuestGoalRewardList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllMQuestGoalRewards() throws Exception {
        // Initialize the database
        mQuestGoalRewardRepository.saveAndFlush(mQuestGoalReward);

        // Get all the mQuestGoalRewardList
        restMQuestGoalRewardMockMvc.perform(get("/api/m-quest-goal-rewards?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(mQuestGoalReward.getId().intValue())))
            .andExpect(jsonPath("$.[*].groupId").value(hasItem(DEFAULT_GROUP_ID)))
            .andExpect(jsonPath("$.[*].weight").value(hasItem(DEFAULT_WEIGHT)))
            .andExpect(jsonPath("$.[*].assetName").value(hasItem(DEFAULT_ASSET_NAME.toString())))
            .andExpect(jsonPath("$.[*].contentType").value(hasItem(DEFAULT_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].contentId").value(hasItem(DEFAULT_CONTENT_ID)))
            .andExpect(jsonPath("$.[*].contentAmount").value(hasItem(DEFAULT_CONTENT_AMOUNT)));
    }
    
    @Test
    @Transactional
    public void getMQuestGoalReward() throws Exception {
        // Initialize the database
        mQuestGoalRewardRepository.saveAndFlush(mQuestGoalReward);

        // Get the mQuestGoalReward
        restMQuestGoalRewardMockMvc.perform(get("/api/m-quest-goal-rewards/{id}", mQuestGoalReward.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(mQuestGoalReward.getId().intValue()))
            .andExpect(jsonPath("$.groupId").value(DEFAULT_GROUP_ID))
            .andExpect(jsonPath("$.weight").value(DEFAULT_WEIGHT))
            .andExpect(jsonPath("$.assetName").value(DEFAULT_ASSET_NAME.toString()))
            .andExpect(jsonPath("$.contentType").value(DEFAULT_CONTENT_TYPE))
            .andExpect(jsonPath("$.contentId").value(DEFAULT_CONTENT_ID))
            .andExpect(jsonPath("$.contentAmount").value(DEFAULT_CONTENT_AMOUNT));
    }

    @Test
    @Transactional
    public void getAllMQuestGoalRewardsByGroupIdIsEqualToSomething() throws Exception {
        // Initialize the database
        mQuestGoalRewardRepository.saveAndFlush(mQuestGoalReward);

        // Get all the mQuestGoalRewardList where groupId equals to DEFAULT_GROUP_ID
        defaultMQuestGoalRewardShouldBeFound("groupId.equals=" + DEFAULT_GROUP_ID);

        // Get all the mQuestGoalRewardList where groupId equals to UPDATED_GROUP_ID
        defaultMQuestGoalRewardShouldNotBeFound("groupId.equals=" + UPDATED_GROUP_ID);
    }

    @Test
    @Transactional
    public void getAllMQuestGoalRewardsByGroupIdIsInShouldWork() throws Exception {
        // Initialize the database
        mQuestGoalRewardRepository.saveAndFlush(mQuestGoalReward);

        // Get all the mQuestGoalRewardList where groupId in DEFAULT_GROUP_ID or UPDATED_GROUP_ID
        defaultMQuestGoalRewardShouldBeFound("groupId.in=" + DEFAULT_GROUP_ID + "," + UPDATED_GROUP_ID);

        // Get all the mQuestGoalRewardList where groupId equals to UPDATED_GROUP_ID
        defaultMQuestGoalRewardShouldNotBeFound("groupId.in=" + UPDATED_GROUP_ID);
    }

    @Test
    @Transactional
    public void getAllMQuestGoalRewardsByGroupIdIsNullOrNotNull() throws Exception {
        // Initialize the database
        mQuestGoalRewardRepository.saveAndFlush(mQuestGoalReward);

        // Get all the mQuestGoalRewardList where groupId is not null
        defaultMQuestGoalRewardShouldBeFound("groupId.specified=true");

        // Get all the mQuestGoalRewardList where groupId is null
        defaultMQuestGoalRewardShouldNotBeFound("groupId.specified=false");
    }

    @Test
    @Transactional
    public void getAllMQuestGoalRewardsByGroupIdIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mQuestGoalRewardRepository.saveAndFlush(mQuestGoalReward);

        // Get all the mQuestGoalRewardList where groupId greater than or equals to DEFAULT_GROUP_ID
        defaultMQuestGoalRewardShouldBeFound("groupId.greaterOrEqualThan=" + DEFAULT_GROUP_ID);

        // Get all the mQuestGoalRewardList where groupId greater than or equals to UPDATED_GROUP_ID
        defaultMQuestGoalRewardShouldNotBeFound("groupId.greaterOrEqualThan=" + UPDATED_GROUP_ID);
    }

    @Test
    @Transactional
    public void getAllMQuestGoalRewardsByGroupIdIsLessThanSomething() throws Exception {
        // Initialize the database
        mQuestGoalRewardRepository.saveAndFlush(mQuestGoalReward);

        // Get all the mQuestGoalRewardList where groupId less than or equals to DEFAULT_GROUP_ID
        defaultMQuestGoalRewardShouldNotBeFound("groupId.lessThan=" + DEFAULT_GROUP_ID);

        // Get all the mQuestGoalRewardList where groupId less than or equals to UPDATED_GROUP_ID
        defaultMQuestGoalRewardShouldBeFound("groupId.lessThan=" + UPDATED_GROUP_ID);
    }


    @Test
    @Transactional
    public void getAllMQuestGoalRewardsByWeightIsEqualToSomething() throws Exception {
        // Initialize the database
        mQuestGoalRewardRepository.saveAndFlush(mQuestGoalReward);

        // Get all the mQuestGoalRewardList where weight equals to DEFAULT_WEIGHT
        defaultMQuestGoalRewardShouldBeFound("weight.equals=" + DEFAULT_WEIGHT);

        // Get all the mQuestGoalRewardList where weight equals to UPDATED_WEIGHT
        defaultMQuestGoalRewardShouldNotBeFound("weight.equals=" + UPDATED_WEIGHT);
    }

    @Test
    @Transactional
    public void getAllMQuestGoalRewardsByWeightIsInShouldWork() throws Exception {
        // Initialize the database
        mQuestGoalRewardRepository.saveAndFlush(mQuestGoalReward);

        // Get all the mQuestGoalRewardList where weight in DEFAULT_WEIGHT or UPDATED_WEIGHT
        defaultMQuestGoalRewardShouldBeFound("weight.in=" + DEFAULT_WEIGHT + "," + UPDATED_WEIGHT);

        // Get all the mQuestGoalRewardList where weight equals to UPDATED_WEIGHT
        defaultMQuestGoalRewardShouldNotBeFound("weight.in=" + UPDATED_WEIGHT);
    }

    @Test
    @Transactional
    public void getAllMQuestGoalRewardsByWeightIsNullOrNotNull() throws Exception {
        // Initialize the database
        mQuestGoalRewardRepository.saveAndFlush(mQuestGoalReward);

        // Get all the mQuestGoalRewardList where weight is not null
        defaultMQuestGoalRewardShouldBeFound("weight.specified=true");

        // Get all the mQuestGoalRewardList where weight is null
        defaultMQuestGoalRewardShouldNotBeFound("weight.specified=false");
    }

    @Test
    @Transactional
    public void getAllMQuestGoalRewardsByWeightIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mQuestGoalRewardRepository.saveAndFlush(mQuestGoalReward);

        // Get all the mQuestGoalRewardList where weight greater than or equals to DEFAULT_WEIGHT
        defaultMQuestGoalRewardShouldBeFound("weight.greaterOrEqualThan=" + DEFAULT_WEIGHT);

        // Get all the mQuestGoalRewardList where weight greater than or equals to UPDATED_WEIGHT
        defaultMQuestGoalRewardShouldNotBeFound("weight.greaterOrEqualThan=" + UPDATED_WEIGHT);
    }

    @Test
    @Transactional
    public void getAllMQuestGoalRewardsByWeightIsLessThanSomething() throws Exception {
        // Initialize the database
        mQuestGoalRewardRepository.saveAndFlush(mQuestGoalReward);

        // Get all the mQuestGoalRewardList where weight less than or equals to DEFAULT_WEIGHT
        defaultMQuestGoalRewardShouldNotBeFound("weight.lessThan=" + DEFAULT_WEIGHT);

        // Get all the mQuestGoalRewardList where weight less than or equals to UPDATED_WEIGHT
        defaultMQuestGoalRewardShouldBeFound("weight.lessThan=" + UPDATED_WEIGHT);
    }


    @Test
    @Transactional
    public void getAllMQuestGoalRewardsByContentTypeIsEqualToSomething() throws Exception {
        // Initialize the database
        mQuestGoalRewardRepository.saveAndFlush(mQuestGoalReward);

        // Get all the mQuestGoalRewardList where contentType equals to DEFAULT_CONTENT_TYPE
        defaultMQuestGoalRewardShouldBeFound("contentType.equals=" + DEFAULT_CONTENT_TYPE);

        // Get all the mQuestGoalRewardList where contentType equals to UPDATED_CONTENT_TYPE
        defaultMQuestGoalRewardShouldNotBeFound("contentType.equals=" + UPDATED_CONTENT_TYPE);
    }

    @Test
    @Transactional
    public void getAllMQuestGoalRewardsByContentTypeIsInShouldWork() throws Exception {
        // Initialize the database
        mQuestGoalRewardRepository.saveAndFlush(mQuestGoalReward);

        // Get all the mQuestGoalRewardList where contentType in DEFAULT_CONTENT_TYPE or UPDATED_CONTENT_TYPE
        defaultMQuestGoalRewardShouldBeFound("contentType.in=" + DEFAULT_CONTENT_TYPE + "," + UPDATED_CONTENT_TYPE);

        // Get all the mQuestGoalRewardList where contentType equals to UPDATED_CONTENT_TYPE
        defaultMQuestGoalRewardShouldNotBeFound("contentType.in=" + UPDATED_CONTENT_TYPE);
    }

    @Test
    @Transactional
    public void getAllMQuestGoalRewardsByContentTypeIsNullOrNotNull() throws Exception {
        // Initialize the database
        mQuestGoalRewardRepository.saveAndFlush(mQuestGoalReward);

        // Get all the mQuestGoalRewardList where contentType is not null
        defaultMQuestGoalRewardShouldBeFound("contentType.specified=true");

        // Get all the mQuestGoalRewardList where contentType is null
        defaultMQuestGoalRewardShouldNotBeFound("contentType.specified=false");
    }

    @Test
    @Transactional
    public void getAllMQuestGoalRewardsByContentTypeIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mQuestGoalRewardRepository.saveAndFlush(mQuestGoalReward);

        // Get all the mQuestGoalRewardList where contentType greater than or equals to DEFAULT_CONTENT_TYPE
        defaultMQuestGoalRewardShouldBeFound("contentType.greaterOrEqualThan=" + DEFAULT_CONTENT_TYPE);

        // Get all the mQuestGoalRewardList where contentType greater than or equals to UPDATED_CONTENT_TYPE
        defaultMQuestGoalRewardShouldNotBeFound("contentType.greaterOrEqualThan=" + UPDATED_CONTENT_TYPE);
    }

    @Test
    @Transactional
    public void getAllMQuestGoalRewardsByContentTypeIsLessThanSomething() throws Exception {
        // Initialize the database
        mQuestGoalRewardRepository.saveAndFlush(mQuestGoalReward);

        // Get all the mQuestGoalRewardList where contentType less than or equals to DEFAULT_CONTENT_TYPE
        defaultMQuestGoalRewardShouldNotBeFound("contentType.lessThan=" + DEFAULT_CONTENT_TYPE);

        // Get all the mQuestGoalRewardList where contentType less than or equals to UPDATED_CONTENT_TYPE
        defaultMQuestGoalRewardShouldBeFound("contentType.lessThan=" + UPDATED_CONTENT_TYPE);
    }


    @Test
    @Transactional
    public void getAllMQuestGoalRewardsByContentIdIsEqualToSomething() throws Exception {
        // Initialize the database
        mQuestGoalRewardRepository.saveAndFlush(mQuestGoalReward);

        // Get all the mQuestGoalRewardList where contentId equals to DEFAULT_CONTENT_ID
        defaultMQuestGoalRewardShouldBeFound("contentId.equals=" + DEFAULT_CONTENT_ID);

        // Get all the mQuestGoalRewardList where contentId equals to UPDATED_CONTENT_ID
        defaultMQuestGoalRewardShouldNotBeFound("contentId.equals=" + UPDATED_CONTENT_ID);
    }

    @Test
    @Transactional
    public void getAllMQuestGoalRewardsByContentIdIsInShouldWork() throws Exception {
        // Initialize the database
        mQuestGoalRewardRepository.saveAndFlush(mQuestGoalReward);

        // Get all the mQuestGoalRewardList where contentId in DEFAULT_CONTENT_ID or UPDATED_CONTENT_ID
        defaultMQuestGoalRewardShouldBeFound("contentId.in=" + DEFAULT_CONTENT_ID + "," + UPDATED_CONTENT_ID);

        // Get all the mQuestGoalRewardList where contentId equals to UPDATED_CONTENT_ID
        defaultMQuestGoalRewardShouldNotBeFound("contentId.in=" + UPDATED_CONTENT_ID);
    }

    @Test
    @Transactional
    public void getAllMQuestGoalRewardsByContentIdIsNullOrNotNull() throws Exception {
        // Initialize the database
        mQuestGoalRewardRepository.saveAndFlush(mQuestGoalReward);

        // Get all the mQuestGoalRewardList where contentId is not null
        defaultMQuestGoalRewardShouldBeFound("contentId.specified=true");

        // Get all the mQuestGoalRewardList where contentId is null
        defaultMQuestGoalRewardShouldNotBeFound("contentId.specified=false");
    }

    @Test
    @Transactional
    public void getAllMQuestGoalRewardsByContentIdIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mQuestGoalRewardRepository.saveAndFlush(mQuestGoalReward);

        // Get all the mQuestGoalRewardList where contentId greater than or equals to DEFAULT_CONTENT_ID
        defaultMQuestGoalRewardShouldBeFound("contentId.greaterOrEqualThan=" + DEFAULT_CONTENT_ID);

        // Get all the mQuestGoalRewardList where contentId greater than or equals to UPDATED_CONTENT_ID
        defaultMQuestGoalRewardShouldNotBeFound("contentId.greaterOrEqualThan=" + UPDATED_CONTENT_ID);
    }

    @Test
    @Transactional
    public void getAllMQuestGoalRewardsByContentIdIsLessThanSomething() throws Exception {
        // Initialize the database
        mQuestGoalRewardRepository.saveAndFlush(mQuestGoalReward);

        // Get all the mQuestGoalRewardList where contentId less than or equals to DEFAULT_CONTENT_ID
        defaultMQuestGoalRewardShouldNotBeFound("contentId.lessThan=" + DEFAULT_CONTENT_ID);

        // Get all the mQuestGoalRewardList where contentId less than or equals to UPDATED_CONTENT_ID
        defaultMQuestGoalRewardShouldBeFound("contentId.lessThan=" + UPDATED_CONTENT_ID);
    }


    @Test
    @Transactional
    public void getAllMQuestGoalRewardsByContentAmountIsEqualToSomething() throws Exception {
        // Initialize the database
        mQuestGoalRewardRepository.saveAndFlush(mQuestGoalReward);

        // Get all the mQuestGoalRewardList where contentAmount equals to DEFAULT_CONTENT_AMOUNT
        defaultMQuestGoalRewardShouldBeFound("contentAmount.equals=" + DEFAULT_CONTENT_AMOUNT);

        // Get all the mQuestGoalRewardList where contentAmount equals to UPDATED_CONTENT_AMOUNT
        defaultMQuestGoalRewardShouldNotBeFound("contentAmount.equals=" + UPDATED_CONTENT_AMOUNT);
    }

    @Test
    @Transactional
    public void getAllMQuestGoalRewardsByContentAmountIsInShouldWork() throws Exception {
        // Initialize the database
        mQuestGoalRewardRepository.saveAndFlush(mQuestGoalReward);

        // Get all the mQuestGoalRewardList where contentAmount in DEFAULT_CONTENT_AMOUNT or UPDATED_CONTENT_AMOUNT
        defaultMQuestGoalRewardShouldBeFound("contentAmount.in=" + DEFAULT_CONTENT_AMOUNT + "," + UPDATED_CONTENT_AMOUNT);

        // Get all the mQuestGoalRewardList where contentAmount equals to UPDATED_CONTENT_AMOUNT
        defaultMQuestGoalRewardShouldNotBeFound("contentAmount.in=" + UPDATED_CONTENT_AMOUNT);
    }

    @Test
    @Transactional
    public void getAllMQuestGoalRewardsByContentAmountIsNullOrNotNull() throws Exception {
        // Initialize the database
        mQuestGoalRewardRepository.saveAndFlush(mQuestGoalReward);

        // Get all the mQuestGoalRewardList where contentAmount is not null
        defaultMQuestGoalRewardShouldBeFound("contentAmount.specified=true");

        // Get all the mQuestGoalRewardList where contentAmount is null
        defaultMQuestGoalRewardShouldNotBeFound("contentAmount.specified=false");
    }

    @Test
    @Transactional
    public void getAllMQuestGoalRewardsByContentAmountIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mQuestGoalRewardRepository.saveAndFlush(mQuestGoalReward);

        // Get all the mQuestGoalRewardList where contentAmount greater than or equals to DEFAULT_CONTENT_AMOUNT
        defaultMQuestGoalRewardShouldBeFound("contentAmount.greaterOrEqualThan=" + DEFAULT_CONTENT_AMOUNT);

        // Get all the mQuestGoalRewardList where contentAmount greater than or equals to UPDATED_CONTENT_AMOUNT
        defaultMQuestGoalRewardShouldNotBeFound("contentAmount.greaterOrEqualThan=" + UPDATED_CONTENT_AMOUNT);
    }

    @Test
    @Transactional
    public void getAllMQuestGoalRewardsByContentAmountIsLessThanSomething() throws Exception {
        // Initialize the database
        mQuestGoalRewardRepository.saveAndFlush(mQuestGoalReward);

        // Get all the mQuestGoalRewardList where contentAmount less than or equals to DEFAULT_CONTENT_AMOUNT
        defaultMQuestGoalRewardShouldNotBeFound("contentAmount.lessThan=" + DEFAULT_CONTENT_AMOUNT);

        // Get all the mQuestGoalRewardList where contentAmount less than or equals to UPDATED_CONTENT_AMOUNT
        defaultMQuestGoalRewardShouldBeFound("contentAmount.lessThan=" + UPDATED_CONTENT_AMOUNT);
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultMQuestGoalRewardShouldBeFound(String filter) throws Exception {
        restMQuestGoalRewardMockMvc.perform(get("/api/m-quest-goal-rewards?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(mQuestGoalReward.getId().intValue())))
            .andExpect(jsonPath("$.[*].groupId").value(hasItem(DEFAULT_GROUP_ID)))
            .andExpect(jsonPath("$.[*].weight").value(hasItem(DEFAULT_WEIGHT)))
            .andExpect(jsonPath("$.[*].assetName").value(hasItem(DEFAULT_ASSET_NAME.toString())))
            .andExpect(jsonPath("$.[*].contentType").value(hasItem(DEFAULT_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].contentId").value(hasItem(DEFAULT_CONTENT_ID)))
            .andExpect(jsonPath("$.[*].contentAmount").value(hasItem(DEFAULT_CONTENT_AMOUNT)));

        // Check, that the count call also returns 1
        restMQuestGoalRewardMockMvc.perform(get("/api/m-quest-goal-rewards/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultMQuestGoalRewardShouldNotBeFound(String filter) throws Exception {
        restMQuestGoalRewardMockMvc.perform(get("/api/m-quest-goal-rewards?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restMQuestGoalRewardMockMvc.perform(get("/api/m-quest-goal-rewards/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("0"));
    }


    @Test
    @Transactional
    public void getNonExistingMQuestGoalReward() throws Exception {
        // Get the mQuestGoalReward
        restMQuestGoalRewardMockMvc.perform(get("/api/m-quest-goal-rewards/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateMQuestGoalReward() throws Exception {
        // Initialize the database
        mQuestGoalRewardRepository.saveAndFlush(mQuestGoalReward);

        int databaseSizeBeforeUpdate = mQuestGoalRewardRepository.findAll().size();

        // Update the mQuestGoalReward
        MQuestGoalReward updatedMQuestGoalReward = mQuestGoalRewardRepository.findById(mQuestGoalReward.getId()).get();
        // Disconnect from session so that the updates on updatedMQuestGoalReward are not directly saved in db
        em.detach(updatedMQuestGoalReward);
        updatedMQuestGoalReward
            .groupId(UPDATED_GROUP_ID)
            .weight(UPDATED_WEIGHT)
            .assetName(UPDATED_ASSET_NAME)
            .contentType(UPDATED_CONTENT_TYPE)
            .contentId(UPDATED_CONTENT_ID)
            .contentAmount(UPDATED_CONTENT_AMOUNT);
        MQuestGoalRewardDTO mQuestGoalRewardDTO = mQuestGoalRewardMapper.toDto(updatedMQuestGoalReward);

        restMQuestGoalRewardMockMvc.perform(put("/api/m-quest-goal-rewards")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mQuestGoalRewardDTO)))
            .andExpect(status().isOk());

        // Validate the MQuestGoalReward in the database
        List<MQuestGoalReward> mQuestGoalRewardList = mQuestGoalRewardRepository.findAll();
        assertThat(mQuestGoalRewardList).hasSize(databaseSizeBeforeUpdate);
        MQuestGoalReward testMQuestGoalReward = mQuestGoalRewardList.get(mQuestGoalRewardList.size() - 1);
        assertThat(testMQuestGoalReward.getGroupId()).isEqualTo(UPDATED_GROUP_ID);
        assertThat(testMQuestGoalReward.getWeight()).isEqualTo(UPDATED_WEIGHT);
        assertThat(testMQuestGoalReward.getAssetName()).isEqualTo(UPDATED_ASSET_NAME);
        assertThat(testMQuestGoalReward.getContentType()).isEqualTo(UPDATED_CONTENT_TYPE);
        assertThat(testMQuestGoalReward.getContentId()).isEqualTo(UPDATED_CONTENT_ID);
        assertThat(testMQuestGoalReward.getContentAmount()).isEqualTo(UPDATED_CONTENT_AMOUNT);
    }

    @Test
    @Transactional
    public void updateNonExistingMQuestGoalReward() throws Exception {
        int databaseSizeBeforeUpdate = mQuestGoalRewardRepository.findAll().size();

        // Create the MQuestGoalReward
        MQuestGoalRewardDTO mQuestGoalRewardDTO = mQuestGoalRewardMapper.toDto(mQuestGoalReward);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restMQuestGoalRewardMockMvc.perform(put("/api/m-quest-goal-rewards")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mQuestGoalRewardDTO)))
            .andExpect(status().isBadRequest());

        // Validate the MQuestGoalReward in the database
        List<MQuestGoalReward> mQuestGoalRewardList = mQuestGoalRewardRepository.findAll();
        assertThat(mQuestGoalRewardList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteMQuestGoalReward() throws Exception {
        // Initialize the database
        mQuestGoalRewardRepository.saveAndFlush(mQuestGoalReward);

        int databaseSizeBeforeDelete = mQuestGoalRewardRepository.findAll().size();

        // Delete the mQuestGoalReward
        restMQuestGoalRewardMockMvc.perform(delete("/api/m-quest-goal-rewards/{id}", mQuestGoalReward.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database is empty
        List<MQuestGoalReward> mQuestGoalRewardList = mQuestGoalRewardRepository.findAll();
        assertThat(mQuestGoalRewardList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(MQuestGoalReward.class);
        MQuestGoalReward mQuestGoalReward1 = new MQuestGoalReward();
        mQuestGoalReward1.setId(1L);
        MQuestGoalReward mQuestGoalReward2 = new MQuestGoalReward();
        mQuestGoalReward2.setId(mQuestGoalReward1.getId());
        assertThat(mQuestGoalReward1).isEqualTo(mQuestGoalReward2);
        mQuestGoalReward2.setId(2L);
        assertThat(mQuestGoalReward1).isNotEqualTo(mQuestGoalReward2);
        mQuestGoalReward1.setId(null);
        assertThat(mQuestGoalReward1).isNotEqualTo(mQuestGoalReward2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(MQuestGoalRewardDTO.class);
        MQuestGoalRewardDTO mQuestGoalRewardDTO1 = new MQuestGoalRewardDTO();
        mQuestGoalRewardDTO1.setId(1L);
        MQuestGoalRewardDTO mQuestGoalRewardDTO2 = new MQuestGoalRewardDTO();
        assertThat(mQuestGoalRewardDTO1).isNotEqualTo(mQuestGoalRewardDTO2);
        mQuestGoalRewardDTO2.setId(mQuestGoalRewardDTO1.getId());
        assertThat(mQuestGoalRewardDTO1).isEqualTo(mQuestGoalRewardDTO2);
        mQuestGoalRewardDTO2.setId(2L);
        assertThat(mQuestGoalRewardDTO1).isNotEqualTo(mQuestGoalRewardDTO2);
        mQuestGoalRewardDTO1.setId(null);
        assertThat(mQuestGoalRewardDTO1).isNotEqualTo(mQuestGoalRewardDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(mQuestGoalRewardMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(mQuestGoalRewardMapper.fromId(null)).isNull();
    }
}
