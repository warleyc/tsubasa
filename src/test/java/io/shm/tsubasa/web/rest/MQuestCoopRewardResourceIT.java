package io.shm.tsubasa.web.rest;

import io.shm.tsubasa.TsubasaApp;
import io.shm.tsubasa.domain.MQuestCoopReward;
import io.shm.tsubasa.repository.MQuestCoopRewardRepository;
import io.shm.tsubasa.service.MQuestCoopRewardService;
import io.shm.tsubasa.service.dto.MQuestCoopRewardDTO;
import io.shm.tsubasa.service.mapper.MQuestCoopRewardMapper;
import io.shm.tsubasa.web.rest.errors.ExceptionTranslator;
import io.shm.tsubasa.service.dto.MQuestCoopRewardCriteria;
import io.shm.tsubasa.service.MQuestCoopRewardQueryService;

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
 * Integration tests for the {@Link MQuestCoopRewardResource} REST controller.
 */
@SpringBootTest(classes = TsubasaApp.class)
public class MQuestCoopRewardResourceIT {

    private static final Integer DEFAULT_GROUP_ID = 1;
    private static final Integer UPDATED_GROUP_ID = 2;

    private static final Integer DEFAULT_WEIGHT = 1;
    private static final Integer UPDATED_WEIGHT = 2;

    private static final Integer DEFAULT_RANK = 1;
    private static final Integer UPDATED_RANK = 2;

    private static final Integer DEFAULT_CONTENT_TYPE = 1;
    private static final Integer UPDATED_CONTENT_TYPE = 2;

    private static final Integer DEFAULT_CONTENT_ID = 1;
    private static final Integer UPDATED_CONTENT_ID = 2;

    private static final Integer DEFAULT_CONTENT_AMOUNT = 1;
    private static final Integer UPDATED_CONTENT_AMOUNT = 2;

    @Autowired
    private MQuestCoopRewardRepository mQuestCoopRewardRepository;

    @Autowired
    private MQuestCoopRewardMapper mQuestCoopRewardMapper;

    @Autowired
    private MQuestCoopRewardService mQuestCoopRewardService;

    @Autowired
    private MQuestCoopRewardQueryService mQuestCoopRewardQueryService;

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

    private MockMvc restMQuestCoopRewardMockMvc;

    private MQuestCoopReward mQuestCoopReward;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final MQuestCoopRewardResource mQuestCoopRewardResource = new MQuestCoopRewardResource(mQuestCoopRewardService, mQuestCoopRewardQueryService);
        this.restMQuestCoopRewardMockMvc = MockMvcBuilders.standaloneSetup(mQuestCoopRewardResource)
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
    public static MQuestCoopReward createEntity(EntityManager em) {
        MQuestCoopReward mQuestCoopReward = new MQuestCoopReward()
            .groupId(DEFAULT_GROUP_ID)
            .weight(DEFAULT_WEIGHT)
            .rank(DEFAULT_RANK)
            .contentType(DEFAULT_CONTENT_TYPE)
            .contentId(DEFAULT_CONTENT_ID)
            .contentAmount(DEFAULT_CONTENT_AMOUNT);
        return mQuestCoopReward;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static MQuestCoopReward createUpdatedEntity(EntityManager em) {
        MQuestCoopReward mQuestCoopReward = new MQuestCoopReward()
            .groupId(UPDATED_GROUP_ID)
            .weight(UPDATED_WEIGHT)
            .rank(UPDATED_RANK)
            .contentType(UPDATED_CONTENT_TYPE)
            .contentId(UPDATED_CONTENT_ID)
            .contentAmount(UPDATED_CONTENT_AMOUNT);
        return mQuestCoopReward;
    }

    @BeforeEach
    public void initTest() {
        mQuestCoopReward = createEntity(em);
    }

    @Test
    @Transactional
    public void createMQuestCoopReward() throws Exception {
        int databaseSizeBeforeCreate = mQuestCoopRewardRepository.findAll().size();

        // Create the MQuestCoopReward
        MQuestCoopRewardDTO mQuestCoopRewardDTO = mQuestCoopRewardMapper.toDto(mQuestCoopReward);
        restMQuestCoopRewardMockMvc.perform(post("/api/m-quest-coop-rewards")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mQuestCoopRewardDTO)))
            .andExpect(status().isCreated());

        // Validate the MQuestCoopReward in the database
        List<MQuestCoopReward> mQuestCoopRewardList = mQuestCoopRewardRepository.findAll();
        assertThat(mQuestCoopRewardList).hasSize(databaseSizeBeforeCreate + 1);
        MQuestCoopReward testMQuestCoopReward = mQuestCoopRewardList.get(mQuestCoopRewardList.size() - 1);
        assertThat(testMQuestCoopReward.getGroupId()).isEqualTo(DEFAULT_GROUP_ID);
        assertThat(testMQuestCoopReward.getWeight()).isEqualTo(DEFAULT_WEIGHT);
        assertThat(testMQuestCoopReward.getRank()).isEqualTo(DEFAULT_RANK);
        assertThat(testMQuestCoopReward.getContentType()).isEqualTo(DEFAULT_CONTENT_TYPE);
        assertThat(testMQuestCoopReward.getContentId()).isEqualTo(DEFAULT_CONTENT_ID);
        assertThat(testMQuestCoopReward.getContentAmount()).isEqualTo(DEFAULT_CONTENT_AMOUNT);
    }

    @Test
    @Transactional
    public void createMQuestCoopRewardWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = mQuestCoopRewardRepository.findAll().size();

        // Create the MQuestCoopReward with an existing ID
        mQuestCoopReward.setId(1L);
        MQuestCoopRewardDTO mQuestCoopRewardDTO = mQuestCoopRewardMapper.toDto(mQuestCoopReward);

        // An entity with an existing ID cannot be created, so this API call must fail
        restMQuestCoopRewardMockMvc.perform(post("/api/m-quest-coop-rewards")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mQuestCoopRewardDTO)))
            .andExpect(status().isBadRequest());

        // Validate the MQuestCoopReward in the database
        List<MQuestCoopReward> mQuestCoopRewardList = mQuestCoopRewardRepository.findAll();
        assertThat(mQuestCoopRewardList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkGroupIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = mQuestCoopRewardRepository.findAll().size();
        // set the field null
        mQuestCoopReward.setGroupId(null);

        // Create the MQuestCoopReward, which fails.
        MQuestCoopRewardDTO mQuestCoopRewardDTO = mQuestCoopRewardMapper.toDto(mQuestCoopReward);

        restMQuestCoopRewardMockMvc.perform(post("/api/m-quest-coop-rewards")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mQuestCoopRewardDTO)))
            .andExpect(status().isBadRequest());

        List<MQuestCoopReward> mQuestCoopRewardList = mQuestCoopRewardRepository.findAll();
        assertThat(mQuestCoopRewardList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkWeightIsRequired() throws Exception {
        int databaseSizeBeforeTest = mQuestCoopRewardRepository.findAll().size();
        // set the field null
        mQuestCoopReward.setWeight(null);

        // Create the MQuestCoopReward, which fails.
        MQuestCoopRewardDTO mQuestCoopRewardDTO = mQuestCoopRewardMapper.toDto(mQuestCoopReward);

        restMQuestCoopRewardMockMvc.perform(post("/api/m-quest-coop-rewards")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mQuestCoopRewardDTO)))
            .andExpect(status().isBadRequest());

        List<MQuestCoopReward> mQuestCoopRewardList = mQuestCoopRewardRepository.findAll();
        assertThat(mQuestCoopRewardList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkRankIsRequired() throws Exception {
        int databaseSizeBeforeTest = mQuestCoopRewardRepository.findAll().size();
        // set the field null
        mQuestCoopReward.setRank(null);

        // Create the MQuestCoopReward, which fails.
        MQuestCoopRewardDTO mQuestCoopRewardDTO = mQuestCoopRewardMapper.toDto(mQuestCoopReward);

        restMQuestCoopRewardMockMvc.perform(post("/api/m-quest-coop-rewards")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mQuestCoopRewardDTO)))
            .andExpect(status().isBadRequest());

        List<MQuestCoopReward> mQuestCoopRewardList = mQuestCoopRewardRepository.findAll();
        assertThat(mQuestCoopRewardList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkContentTypeIsRequired() throws Exception {
        int databaseSizeBeforeTest = mQuestCoopRewardRepository.findAll().size();
        // set the field null
        mQuestCoopReward.setContentType(null);

        // Create the MQuestCoopReward, which fails.
        MQuestCoopRewardDTO mQuestCoopRewardDTO = mQuestCoopRewardMapper.toDto(mQuestCoopReward);

        restMQuestCoopRewardMockMvc.perform(post("/api/m-quest-coop-rewards")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mQuestCoopRewardDTO)))
            .andExpect(status().isBadRequest());

        List<MQuestCoopReward> mQuestCoopRewardList = mQuestCoopRewardRepository.findAll();
        assertThat(mQuestCoopRewardList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkContentAmountIsRequired() throws Exception {
        int databaseSizeBeforeTest = mQuestCoopRewardRepository.findAll().size();
        // set the field null
        mQuestCoopReward.setContentAmount(null);

        // Create the MQuestCoopReward, which fails.
        MQuestCoopRewardDTO mQuestCoopRewardDTO = mQuestCoopRewardMapper.toDto(mQuestCoopReward);

        restMQuestCoopRewardMockMvc.perform(post("/api/m-quest-coop-rewards")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mQuestCoopRewardDTO)))
            .andExpect(status().isBadRequest());

        List<MQuestCoopReward> mQuestCoopRewardList = mQuestCoopRewardRepository.findAll();
        assertThat(mQuestCoopRewardList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllMQuestCoopRewards() throws Exception {
        // Initialize the database
        mQuestCoopRewardRepository.saveAndFlush(mQuestCoopReward);

        // Get all the mQuestCoopRewardList
        restMQuestCoopRewardMockMvc.perform(get("/api/m-quest-coop-rewards?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(mQuestCoopReward.getId().intValue())))
            .andExpect(jsonPath("$.[*].groupId").value(hasItem(DEFAULT_GROUP_ID)))
            .andExpect(jsonPath("$.[*].weight").value(hasItem(DEFAULT_WEIGHT)))
            .andExpect(jsonPath("$.[*].rank").value(hasItem(DEFAULT_RANK)))
            .andExpect(jsonPath("$.[*].contentType").value(hasItem(DEFAULT_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].contentId").value(hasItem(DEFAULT_CONTENT_ID)))
            .andExpect(jsonPath("$.[*].contentAmount").value(hasItem(DEFAULT_CONTENT_AMOUNT)));
    }
    
    @Test
    @Transactional
    public void getMQuestCoopReward() throws Exception {
        // Initialize the database
        mQuestCoopRewardRepository.saveAndFlush(mQuestCoopReward);

        // Get the mQuestCoopReward
        restMQuestCoopRewardMockMvc.perform(get("/api/m-quest-coop-rewards/{id}", mQuestCoopReward.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(mQuestCoopReward.getId().intValue()))
            .andExpect(jsonPath("$.groupId").value(DEFAULT_GROUP_ID))
            .andExpect(jsonPath("$.weight").value(DEFAULT_WEIGHT))
            .andExpect(jsonPath("$.rank").value(DEFAULT_RANK))
            .andExpect(jsonPath("$.contentType").value(DEFAULT_CONTENT_TYPE))
            .andExpect(jsonPath("$.contentId").value(DEFAULT_CONTENT_ID))
            .andExpect(jsonPath("$.contentAmount").value(DEFAULT_CONTENT_AMOUNT));
    }

    @Test
    @Transactional
    public void getAllMQuestCoopRewardsByGroupIdIsEqualToSomething() throws Exception {
        // Initialize the database
        mQuestCoopRewardRepository.saveAndFlush(mQuestCoopReward);

        // Get all the mQuestCoopRewardList where groupId equals to DEFAULT_GROUP_ID
        defaultMQuestCoopRewardShouldBeFound("groupId.equals=" + DEFAULT_GROUP_ID);

        // Get all the mQuestCoopRewardList where groupId equals to UPDATED_GROUP_ID
        defaultMQuestCoopRewardShouldNotBeFound("groupId.equals=" + UPDATED_GROUP_ID);
    }

    @Test
    @Transactional
    public void getAllMQuestCoopRewardsByGroupIdIsInShouldWork() throws Exception {
        // Initialize the database
        mQuestCoopRewardRepository.saveAndFlush(mQuestCoopReward);

        // Get all the mQuestCoopRewardList where groupId in DEFAULT_GROUP_ID or UPDATED_GROUP_ID
        defaultMQuestCoopRewardShouldBeFound("groupId.in=" + DEFAULT_GROUP_ID + "," + UPDATED_GROUP_ID);

        // Get all the mQuestCoopRewardList where groupId equals to UPDATED_GROUP_ID
        defaultMQuestCoopRewardShouldNotBeFound("groupId.in=" + UPDATED_GROUP_ID);
    }

    @Test
    @Transactional
    public void getAllMQuestCoopRewardsByGroupIdIsNullOrNotNull() throws Exception {
        // Initialize the database
        mQuestCoopRewardRepository.saveAndFlush(mQuestCoopReward);

        // Get all the mQuestCoopRewardList where groupId is not null
        defaultMQuestCoopRewardShouldBeFound("groupId.specified=true");

        // Get all the mQuestCoopRewardList where groupId is null
        defaultMQuestCoopRewardShouldNotBeFound("groupId.specified=false");
    }

    @Test
    @Transactional
    public void getAllMQuestCoopRewardsByGroupIdIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mQuestCoopRewardRepository.saveAndFlush(mQuestCoopReward);

        // Get all the mQuestCoopRewardList where groupId greater than or equals to DEFAULT_GROUP_ID
        defaultMQuestCoopRewardShouldBeFound("groupId.greaterOrEqualThan=" + DEFAULT_GROUP_ID);

        // Get all the mQuestCoopRewardList where groupId greater than or equals to UPDATED_GROUP_ID
        defaultMQuestCoopRewardShouldNotBeFound("groupId.greaterOrEqualThan=" + UPDATED_GROUP_ID);
    }

    @Test
    @Transactional
    public void getAllMQuestCoopRewardsByGroupIdIsLessThanSomething() throws Exception {
        // Initialize the database
        mQuestCoopRewardRepository.saveAndFlush(mQuestCoopReward);

        // Get all the mQuestCoopRewardList where groupId less than or equals to DEFAULT_GROUP_ID
        defaultMQuestCoopRewardShouldNotBeFound("groupId.lessThan=" + DEFAULT_GROUP_ID);

        // Get all the mQuestCoopRewardList where groupId less than or equals to UPDATED_GROUP_ID
        defaultMQuestCoopRewardShouldBeFound("groupId.lessThan=" + UPDATED_GROUP_ID);
    }


    @Test
    @Transactional
    public void getAllMQuestCoopRewardsByWeightIsEqualToSomething() throws Exception {
        // Initialize the database
        mQuestCoopRewardRepository.saveAndFlush(mQuestCoopReward);

        // Get all the mQuestCoopRewardList where weight equals to DEFAULT_WEIGHT
        defaultMQuestCoopRewardShouldBeFound("weight.equals=" + DEFAULT_WEIGHT);

        // Get all the mQuestCoopRewardList where weight equals to UPDATED_WEIGHT
        defaultMQuestCoopRewardShouldNotBeFound("weight.equals=" + UPDATED_WEIGHT);
    }

    @Test
    @Transactional
    public void getAllMQuestCoopRewardsByWeightIsInShouldWork() throws Exception {
        // Initialize the database
        mQuestCoopRewardRepository.saveAndFlush(mQuestCoopReward);

        // Get all the mQuestCoopRewardList where weight in DEFAULT_WEIGHT or UPDATED_WEIGHT
        defaultMQuestCoopRewardShouldBeFound("weight.in=" + DEFAULT_WEIGHT + "," + UPDATED_WEIGHT);

        // Get all the mQuestCoopRewardList where weight equals to UPDATED_WEIGHT
        defaultMQuestCoopRewardShouldNotBeFound("weight.in=" + UPDATED_WEIGHT);
    }

    @Test
    @Transactional
    public void getAllMQuestCoopRewardsByWeightIsNullOrNotNull() throws Exception {
        // Initialize the database
        mQuestCoopRewardRepository.saveAndFlush(mQuestCoopReward);

        // Get all the mQuestCoopRewardList where weight is not null
        defaultMQuestCoopRewardShouldBeFound("weight.specified=true");

        // Get all the mQuestCoopRewardList where weight is null
        defaultMQuestCoopRewardShouldNotBeFound("weight.specified=false");
    }

    @Test
    @Transactional
    public void getAllMQuestCoopRewardsByWeightIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mQuestCoopRewardRepository.saveAndFlush(mQuestCoopReward);

        // Get all the mQuestCoopRewardList where weight greater than or equals to DEFAULT_WEIGHT
        defaultMQuestCoopRewardShouldBeFound("weight.greaterOrEqualThan=" + DEFAULT_WEIGHT);

        // Get all the mQuestCoopRewardList where weight greater than or equals to UPDATED_WEIGHT
        defaultMQuestCoopRewardShouldNotBeFound("weight.greaterOrEqualThan=" + UPDATED_WEIGHT);
    }

    @Test
    @Transactional
    public void getAllMQuestCoopRewardsByWeightIsLessThanSomething() throws Exception {
        // Initialize the database
        mQuestCoopRewardRepository.saveAndFlush(mQuestCoopReward);

        // Get all the mQuestCoopRewardList where weight less than or equals to DEFAULT_WEIGHT
        defaultMQuestCoopRewardShouldNotBeFound("weight.lessThan=" + DEFAULT_WEIGHT);

        // Get all the mQuestCoopRewardList where weight less than or equals to UPDATED_WEIGHT
        defaultMQuestCoopRewardShouldBeFound("weight.lessThan=" + UPDATED_WEIGHT);
    }


    @Test
    @Transactional
    public void getAllMQuestCoopRewardsByRankIsEqualToSomething() throws Exception {
        // Initialize the database
        mQuestCoopRewardRepository.saveAndFlush(mQuestCoopReward);

        // Get all the mQuestCoopRewardList where rank equals to DEFAULT_RANK
        defaultMQuestCoopRewardShouldBeFound("rank.equals=" + DEFAULT_RANK);

        // Get all the mQuestCoopRewardList where rank equals to UPDATED_RANK
        defaultMQuestCoopRewardShouldNotBeFound("rank.equals=" + UPDATED_RANK);
    }

    @Test
    @Transactional
    public void getAllMQuestCoopRewardsByRankIsInShouldWork() throws Exception {
        // Initialize the database
        mQuestCoopRewardRepository.saveAndFlush(mQuestCoopReward);

        // Get all the mQuestCoopRewardList where rank in DEFAULT_RANK or UPDATED_RANK
        defaultMQuestCoopRewardShouldBeFound("rank.in=" + DEFAULT_RANK + "," + UPDATED_RANK);

        // Get all the mQuestCoopRewardList where rank equals to UPDATED_RANK
        defaultMQuestCoopRewardShouldNotBeFound("rank.in=" + UPDATED_RANK);
    }

    @Test
    @Transactional
    public void getAllMQuestCoopRewardsByRankIsNullOrNotNull() throws Exception {
        // Initialize the database
        mQuestCoopRewardRepository.saveAndFlush(mQuestCoopReward);

        // Get all the mQuestCoopRewardList where rank is not null
        defaultMQuestCoopRewardShouldBeFound("rank.specified=true");

        // Get all the mQuestCoopRewardList where rank is null
        defaultMQuestCoopRewardShouldNotBeFound("rank.specified=false");
    }

    @Test
    @Transactional
    public void getAllMQuestCoopRewardsByRankIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mQuestCoopRewardRepository.saveAndFlush(mQuestCoopReward);

        // Get all the mQuestCoopRewardList where rank greater than or equals to DEFAULT_RANK
        defaultMQuestCoopRewardShouldBeFound("rank.greaterOrEqualThan=" + DEFAULT_RANK);

        // Get all the mQuestCoopRewardList where rank greater than or equals to UPDATED_RANK
        defaultMQuestCoopRewardShouldNotBeFound("rank.greaterOrEqualThan=" + UPDATED_RANK);
    }

    @Test
    @Transactional
    public void getAllMQuestCoopRewardsByRankIsLessThanSomething() throws Exception {
        // Initialize the database
        mQuestCoopRewardRepository.saveAndFlush(mQuestCoopReward);

        // Get all the mQuestCoopRewardList where rank less than or equals to DEFAULT_RANK
        defaultMQuestCoopRewardShouldNotBeFound("rank.lessThan=" + DEFAULT_RANK);

        // Get all the mQuestCoopRewardList where rank less than or equals to UPDATED_RANK
        defaultMQuestCoopRewardShouldBeFound("rank.lessThan=" + UPDATED_RANK);
    }


    @Test
    @Transactional
    public void getAllMQuestCoopRewardsByContentTypeIsEqualToSomething() throws Exception {
        // Initialize the database
        mQuestCoopRewardRepository.saveAndFlush(mQuestCoopReward);

        // Get all the mQuestCoopRewardList where contentType equals to DEFAULT_CONTENT_TYPE
        defaultMQuestCoopRewardShouldBeFound("contentType.equals=" + DEFAULT_CONTENT_TYPE);

        // Get all the mQuestCoopRewardList where contentType equals to UPDATED_CONTENT_TYPE
        defaultMQuestCoopRewardShouldNotBeFound("contentType.equals=" + UPDATED_CONTENT_TYPE);
    }

    @Test
    @Transactional
    public void getAllMQuestCoopRewardsByContentTypeIsInShouldWork() throws Exception {
        // Initialize the database
        mQuestCoopRewardRepository.saveAndFlush(mQuestCoopReward);

        // Get all the mQuestCoopRewardList where contentType in DEFAULT_CONTENT_TYPE or UPDATED_CONTENT_TYPE
        defaultMQuestCoopRewardShouldBeFound("contentType.in=" + DEFAULT_CONTENT_TYPE + "," + UPDATED_CONTENT_TYPE);

        // Get all the mQuestCoopRewardList where contentType equals to UPDATED_CONTENT_TYPE
        defaultMQuestCoopRewardShouldNotBeFound("contentType.in=" + UPDATED_CONTENT_TYPE);
    }

    @Test
    @Transactional
    public void getAllMQuestCoopRewardsByContentTypeIsNullOrNotNull() throws Exception {
        // Initialize the database
        mQuestCoopRewardRepository.saveAndFlush(mQuestCoopReward);

        // Get all the mQuestCoopRewardList where contentType is not null
        defaultMQuestCoopRewardShouldBeFound("contentType.specified=true");

        // Get all the mQuestCoopRewardList where contentType is null
        defaultMQuestCoopRewardShouldNotBeFound("contentType.specified=false");
    }

    @Test
    @Transactional
    public void getAllMQuestCoopRewardsByContentTypeIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mQuestCoopRewardRepository.saveAndFlush(mQuestCoopReward);

        // Get all the mQuestCoopRewardList where contentType greater than or equals to DEFAULT_CONTENT_TYPE
        defaultMQuestCoopRewardShouldBeFound("contentType.greaterOrEqualThan=" + DEFAULT_CONTENT_TYPE);

        // Get all the mQuestCoopRewardList where contentType greater than or equals to UPDATED_CONTENT_TYPE
        defaultMQuestCoopRewardShouldNotBeFound("contentType.greaterOrEqualThan=" + UPDATED_CONTENT_TYPE);
    }

    @Test
    @Transactional
    public void getAllMQuestCoopRewardsByContentTypeIsLessThanSomething() throws Exception {
        // Initialize the database
        mQuestCoopRewardRepository.saveAndFlush(mQuestCoopReward);

        // Get all the mQuestCoopRewardList where contentType less than or equals to DEFAULT_CONTENT_TYPE
        defaultMQuestCoopRewardShouldNotBeFound("contentType.lessThan=" + DEFAULT_CONTENT_TYPE);

        // Get all the mQuestCoopRewardList where contentType less than or equals to UPDATED_CONTENT_TYPE
        defaultMQuestCoopRewardShouldBeFound("contentType.lessThan=" + UPDATED_CONTENT_TYPE);
    }


    @Test
    @Transactional
    public void getAllMQuestCoopRewardsByContentIdIsEqualToSomething() throws Exception {
        // Initialize the database
        mQuestCoopRewardRepository.saveAndFlush(mQuestCoopReward);

        // Get all the mQuestCoopRewardList where contentId equals to DEFAULT_CONTENT_ID
        defaultMQuestCoopRewardShouldBeFound("contentId.equals=" + DEFAULT_CONTENT_ID);

        // Get all the mQuestCoopRewardList where contentId equals to UPDATED_CONTENT_ID
        defaultMQuestCoopRewardShouldNotBeFound("contentId.equals=" + UPDATED_CONTENT_ID);
    }

    @Test
    @Transactional
    public void getAllMQuestCoopRewardsByContentIdIsInShouldWork() throws Exception {
        // Initialize the database
        mQuestCoopRewardRepository.saveAndFlush(mQuestCoopReward);

        // Get all the mQuestCoopRewardList where contentId in DEFAULT_CONTENT_ID or UPDATED_CONTENT_ID
        defaultMQuestCoopRewardShouldBeFound("contentId.in=" + DEFAULT_CONTENT_ID + "," + UPDATED_CONTENT_ID);

        // Get all the mQuestCoopRewardList where contentId equals to UPDATED_CONTENT_ID
        defaultMQuestCoopRewardShouldNotBeFound("contentId.in=" + UPDATED_CONTENT_ID);
    }

    @Test
    @Transactional
    public void getAllMQuestCoopRewardsByContentIdIsNullOrNotNull() throws Exception {
        // Initialize the database
        mQuestCoopRewardRepository.saveAndFlush(mQuestCoopReward);

        // Get all the mQuestCoopRewardList where contentId is not null
        defaultMQuestCoopRewardShouldBeFound("contentId.specified=true");

        // Get all the mQuestCoopRewardList where contentId is null
        defaultMQuestCoopRewardShouldNotBeFound("contentId.specified=false");
    }

    @Test
    @Transactional
    public void getAllMQuestCoopRewardsByContentIdIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mQuestCoopRewardRepository.saveAndFlush(mQuestCoopReward);

        // Get all the mQuestCoopRewardList where contentId greater than or equals to DEFAULT_CONTENT_ID
        defaultMQuestCoopRewardShouldBeFound("contentId.greaterOrEqualThan=" + DEFAULT_CONTENT_ID);

        // Get all the mQuestCoopRewardList where contentId greater than or equals to UPDATED_CONTENT_ID
        defaultMQuestCoopRewardShouldNotBeFound("contentId.greaterOrEqualThan=" + UPDATED_CONTENT_ID);
    }

    @Test
    @Transactional
    public void getAllMQuestCoopRewardsByContentIdIsLessThanSomething() throws Exception {
        // Initialize the database
        mQuestCoopRewardRepository.saveAndFlush(mQuestCoopReward);

        // Get all the mQuestCoopRewardList where contentId less than or equals to DEFAULT_CONTENT_ID
        defaultMQuestCoopRewardShouldNotBeFound("contentId.lessThan=" + DEFAULT_CONTENT_ID);

        // Get all the mQuestCoopRewardList where contentId less than or equals to UPDATED_CONTENT_ID
        defaultMQuestCoopRewardShouldBeFound("contentId.lessThan=" + UPDATED_CONTENT_ID);
    }


    @Test
    @Transactional
    public void getAllMQuestCoopRewardsByContentAmountIsEqualToSomething() throws Exception {
        // Initialize the database
        mQuestCoopRewardRepository.saveAndFlush(mQuestCoopReward);

        // Get all the mQuestCoopRewardList where contentAmount equals to DEFAULT_CONTENT_AMOUNT
        defaultMQuestCoopRewardShouldBeFound("contentAmount.equals=" + DEFAULT_CONTENT_AMOUNT);

        // Get all the mQuestCoopRewardList where contentAmount equals to UPDATED_CONTENT_AMOUNT
        defaultMQuestCoopRewardShouldNotBeFound("contentAmount.equals=" + UPDATED_CONTENT_AMOUNT);
    }

    @Test
    @Transactional
    public void getAllMQuestCoopRewardsByContentAmountIsInShouldWork() throws Exception {
        // Initialize the database
        mQuestCoopRewardRepository.saveAndFlush(mQuestCoopReward);

        // Get all the mQuestCoopRewardList where contentAmount in DEFAULT_CONTENT_AMOUNT or UPDATED_CONTENT_AMOUNT
        defaultMQuestCoopRewardShouldBeFound("contentAmount.in=" + DEFAULT_CONTENT_AMOUNT + "," + UPDATED_CONTENT_AMOUNT);

        // Get all the mQuestCoopRewardList where contentAmount equals to UPDATED_CONTENT_AMOUNT
        defaultMQuestCoopRewardShouldNotBeFound("contentAmount.in=" + UPDATED_CONTENT_AMOUNT);
    }

    @Test
    @Transactional
    public void getAllMQuestCoopRewardsByContentAmountIsNullOrNotNull() throws Exception {
        // Initialize the database
        mQuestCoopRewardRepository.saveAndFlush(mQuestCoopReward);

        // Get all the mQuestCoopRewardList where contentAmount is not null
        defaultMQuestCoopRewardShouldBeFound("contentAmount.specified=true");

        // Get all the mQuestCoopRewardList where contentAmount is null
        defaultMQuestCoopRewardShouldNotBeFound("contentAmount.specified=false");
    }

    @Test
    @Transactional
    public void getAllMQuestCoopRewardsByContentAmountIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mQuestCoopRewardRepository.saveAndFlush(mQuestCoopReward);

        // Get all the mQuestCoopRewardList where contentAmount greater than or equals to DEFAULT_CONTENT_AMOUNT
        defaultMQuestCoopRewardShouldBeFound("contentAmount.greaterOrEqualThan=" + DEFAULT_CONTENT_AMOUNT);

        // Get all the mQuestCoopRewardList where contentAmount greater than or equals to UPDATED_CONTENT_AMOUNT
        defaultMQuestCoopRewardShouldNotBeFound("contentAmount.greaterOrEqualThan=" + UPDATED_CONTENT_AMOUNT);
    }

    @Test
    @Transactional
    public void getAllMQuestCoopRewardsByContentAmountIsLessThanSomething() throws Exception {
        // Initialize the database
        mQuestCoopRewardRepository.saveAndFlush(mQuestCoopReward);

        // Get all the mQuestCoopRewardList where contentAmount less than or equals to DEFAULT_CONTENT_AMOUNT
        defaultMQuestCoopRewardShouldNotBeFound("contentAmount.lessThan=" + DEFAULT_CONTENT_AMOUNT);

        // Get all the mQuestCoopRewardList where contentAmount less than or equals to UPDATED_CONTENT_AMOUNT
        defaultMQuestCoopRewardShouldBeFound("contentAmount.lessThan=" + UPDATED_CONTENT_AMOUNT);
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultMQuestCoopRewardShouldBeFound(String filter) throws Exception {
        restMQuestCoopRewardMockMvc.perform(get("/api/m-quest-coop-rewards?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(mQuestCoopReward.getId().intValue())))
            .andExpect(jsonPath("$.[*].groupId").value(hasItem(DEFAULT_GROUP_ID)))
            .andExpect(jsonPath("$.[*].weight").value(hasItem(DEFAULT_WEIGHT)))
            .andExpect(jsonPath("$.[*].rank").value(hasItem(DEFAULT_RANK)))
            .andExpect(jsonPath("$.[*].contentType").value(hasItem(DEFAULT_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].contentId").value(hasItem(DEFAULT_CONTENT_ID)))
            .andExpect(jsonPath("$.[*].contentAmount").value(hasItem(DEFAULT_CONTENT_AMOUNT)));

        // Check, that the count call also returns 1
        restMQuestCoopRewardMockMvc.perform(get("/api/m-quest-coop-rewards/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultMQuestCoopRewardShouldNotBeFound(String filter) throws Exception {
        restMQuestCoopRewardMockMvc.perform(get("/api/m-quest-coop-rewards?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restMQuestCoopRewardMockMvc.perform(get("/api/m-quest-coop-rewards/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("0"));
    }


    @Test
    @Transactional
    public void getNonExistingMQuestCoopReward() throws Exception {
        // Get the mQuestCoopReward
        restMQuestCoopRewardMockMvc.perform(get("/api/m-quest-coop-rewards/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateMQuestCoopReward() throws Exception {
        // Initialize the database
        mQuestCoopRewardRepository.saveAndFlush(mQuestCoopReward);

        int databaseSizeBeforeUpdate = mQuestCoopRewardRepository.findAll().size();

        // Update the mQuestCoopReward
        MQuestCoopReward updatedMQuestCoopReward = mQuestCoopRewardRepository.findById(mQuestCoopReward.getId()).get();
        // Disconnect from session so that the updates on updatedMQuestCoopReward are not directly saved in db
        em.detach(updatedMQuestCoopReward);
        updatedMQuestCoopReward
            .groupId(UPDATED_GROUP_ID)
            .weight(UPDATED_WEIGHT)
            .rank(UPDATED_RANK)
            .contentType(UPDATED_CONTENT_TYPE)
            .contentId(UPDATED_CONTENT_ID)
            .contentAmount(UPDATED_CONTENT_AMOUNT);
        MQuestCoopRewardDTO mQuestCoopRewardDTO = mQuestCoopRewardMapper.toDto(updatedMQuestCoopReward);

        restMQuestCoopRewardMockMvc.perform(put("/api/m-quest-coop-rewards")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mQuestCoopRewardDTO)))
            .andExpect(status().isOk());

        // Validate the MQuestCoopReward in the database
        List<MQuestCoopReward> mQuestCoopRewardList = mQuestCoopRewardRepository.findAll();
        assertThat(mQuestCoopRewardList).hasSize(databaseSizeBeforeUpdate);
        MQuestCoopReward testMQuestCoopReward = mQuestCoopRewardList.get(mQuestCoopRewardList.size() - 1);
        assertThat(testMQuestCoopReward.getGroupId()).isEqualTo(UPDATED_GROUP_ID);
        assertThat(testMQuestCoopReward.getWeight()).isEqualTo(UPDATED_WEIGHT);
        assertThat(testMQuestCoopReward.getRank()).isEqualTo(UPDATED_RANK);
        assertThat(testMQuestCoopReward.getContentType()).isEqualTo(UPDATED_CONTENT_TYPE);
        assertThat(testMQuestCoopReward.getContentId()).isEqualTo(UPDATED_CONTENT_ID);
        assertThat(testMQuestCoopReward.getContentAmount()).isEqualTo(UPDATED_CONTENT_AMOUNT);
    }

    @Test
    @Transactional
    public void updateNonExistingMQuestCoopReward() throws Exception {
        int databaseSizeBeforeUpdate = mQuestCoopRewardRepository.findAll().size();

        // Create the MQuestCoopReward
        MQuestCoopRewardDTO mQuestCoopRewardDTO = mQuestCoopRewardMapper.toDto(mQuestCoopReward);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restMQuestCoopRewardMockMvc.perform(put("/api/m-quest-coop-rewards")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mQuestCoopRewardDTO)))
            .andExpect(status().isBadRequest());

        // Validate the MQuestCoopReward in the database
        List<MQuestCoopReward> mQuestCoopRewardList = mQuestCoopRewardRepository.findAll();
        assertThat(mQuestCoopRewardList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteMQuestCoopReward() throws Exception {
        // Initialize the database
        mQuestCoopRewardRepository.saveAndFlush(mQuestCoopReward);

        int databaseSizeBeforeDelete = mQuestCoopRewardRepository.findAll().size();

        // Delete the mQuestCoopReward
        restMQuestCoopRewardMockMvc.perform(delete("/api/m-quest-coop-rewards/{id}", mQuestCoopReward.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database is empty
        List<MQuestCoopReward> mQuestCoopRewardList = mQuestCoopRewardRepository.findAll();
        assertThat(mQuestCoopRewardList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(MQuestCoopReward.class);
        MQuestCoopReward mQuestCoopReward1 = new MQuestCoopReward();
        mQuestCoopReward1.setId(1L);
        MQuestCoopReward mQuestCoopReward2 = new MQuestCoopReward();
        mQuestCoopReward2.setId(mQuestCoopReward1.getId());
        assertThat(mQuestCoopReward1).isEqualTo(mQuestCoopReward2);
        mQuestCoopReward2.setId(2L);
        assertThat(mQuestCoopReward1).isNotEqualTo(mQuestCoopReward2);
        mQuestCoopReward1.setId(null);
        assertThat(mQuestCoopReward1).isNotEqualTo(mQuestCoopReward2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(MQuestCoopRewardDTO.class);
        MQuestCoopRewardDTO mQuestCoopRewardDTO1 = new MQuestCoopRewardDTO();
        mQuestCoopRewardDTO1.setId(1L);
        MQuestCoopRewardDTO mQuestCoopRewardDTO2 = new MQuestCoopRewardDTO();
        assertThat(mQuestCoopRewardDTO1).isNotEqualTo(mQuestCoopRewardDTO2);
        mQuestCoopRewardDTO2.setId(mQuestCoopRewardDTO1.getId());
        assertThat(mQuestCoopRewardDTO1).isEqualTo(mQuestCoopRewardDTO2);
        mQuestCoopRewardDTO2.setId(2L);
        assertThat(mQuestCoopRewardDTO1).isNotEqualTo(mQuestCoopRewardDTO2);
        mQuestCoopRewardDTO1.setId(null);
        assertThat(mQuestCoopRewardDTO1).isNotEqualTo(mQuestCoopRewardDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(mQuestCoopRewardMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(mQuestCoopRewardMapper.fromId(null)).isNull();
    }
}
