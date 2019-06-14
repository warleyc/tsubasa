package io.shm.tsubasa.web.rest;

import io.shm.tsubasa.TsubasaApp;
import io.shm.tsubasa.domain.MQuestSpecialReward;
import io.shm.tsubasa.repository.MQuestSpecialRewardRepository;
import io.shm.tsubasa.service.MQuestSpecialRewardService;
import io.shm.tsubasa.service.dto.MQuestSpecialRewardDTO;
import io.shm.tsubasa.service.mapper.MQuestSpecialRewardMapper;
import io.shm.tsubasa.web.rest.errors.ExceptionTranslator;
import io.shm.tsubasa.service.dto.MQuestSpecialRewardCriteria;
import io.shm.tsubasa.service.MQuestSpecialRewardQueryService;

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
 * Integration tests for the {@Link MQuestSpecialRewardResource} REST controller.
 */
@SpringBootTest(classes = TsubasaApp.class)
public class MQuestSpecialRewardResourceIT {

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
    private MQuestSpecialRewardRepository mQuestSpecialRewardRepository;

    @Autowired
    private MQuestSpecialRewardMapper mQuestSpecialRewardMapper;

    @Autowired
    private MQuestSpecialRewardService mQuestSpecialRewardService;

    @Autowired
    private MQuestSpecialRewardQueryService mQuestSpecialRewardQueryService;

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

    private MockMvc restMQuestSpecialRewardMockMvc;

    private MQuestSpecialReward mQuestSpecialReward;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final MQuestSpecialRewardResource mQuestSpecialRewardResource = new MQuestSpecialRewardResource(mQuestSpecialRewardService, mQuestSpecialRewardQueryService);
        this.restMQuestSpecialRewardMockMvc = MockMvcBuilders.standaloneSetup(mQuestSpecialRewardResource)
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
    public static MQuestSpecialReward createEntity(EntityManager em) {
        MQuestSpecialReward mQuestSpecialReward = new MQuestSpecialReward()
            .groupId(DEFAULT_GROUP_ID)
            .weight(DEFAULT_WEIGHT)
            .rank(DEFAULT_RANK)
            .contentType(DEFAULT_CONTENT_TYPE)
            .contentId(DEFAULT_CONTENT_ID)
            .contentAmount(DEFAULT_CONTENT_AMOUNT);
        return mQuestSpecialReward;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static MQuestSpecialReward createUpdatedEntity(EntityManager em) {
        MQuestSpecialReward mQuestSpecialReward = new MQuestSpecialReward()
            .groupId(UPDATED_GROUP_ID)
            .weight(UPDATED_WEIGHT)
            .rank(UPDATED_RANK)
            .contentType(UPDATED_CONTENT_TYPE)
            .contentId(UPDATED_CONTENT_ID)
            .contentAmount(UPDATED_CONTENT_AMOUNT);
        return mQuestSpecialReward;
    }

    @BeforeEach
    public void initTest() {
        mQuestSpecialReward = createEntity(em);
    }

    @Test
    @Transactional
    public void createMQuestSpecialReward() throws Exception {
        int databaseSizeBeforeCreate = mQuestSpecialRewardRepository.findAll().size();

        // Create the MQuestSpecialReward
        MQuestSpecialRewardDTO mQuestSpecialRewardDTO = mQuestSpecialRewardMapper.toDto(mQuestSpecialReward);
        restMQuestSpecialRewardMockMvc.perform(post("/api/m-quest-special-rewards")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mQuestSpecialRewardDTO)))
            .andExpect(status().isCreated());

        // Validate the MQuestSpecialReward in the database
        List<MQuestSpecialReward> mQuestSpecialRewardList = mQuestSpecialRewardRepository.findAll();
        assertThat(mQuestSpecialRewardList).hasSize(databaseSizeBeforeCreate + 1);
        MQuestSpecialReward testMQuestSpecialReward = mQuestSpecialRewardList.get(mQuestSpecialRewardList.size() - 1);
        assertThat(testMQuestSpecialReward.getGroupId()).isEqualTo(DEFAULT_GROUP_ID);
        assertThat(testMQuestSpecialReward.getWeight()).isEqualTo(DEFAULT_WEIGHT);
        assertThat(testMQuestSpecialReward.getRank()).isEqualTo(DEFAULT_RANK);
        assertThat(testMQuestSpecialReward.getContentType()).isEqualTo(DEFAULT_CONTENT_TYPE);
        assertThat(testMQuestSpecialReward.getContentId()).isEqualTo(DEFAULT_CONTENT_ID);
        assertThat(testMQuestSpecialReward.getContentAmount()).isEqualTo(DEFAULT_CONTENT_AMOUNT);
    }

    @Test
    @Transactional
    public void createMQuestSpecialRewardWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = mQuestSpecialRewardRepository.findAll().size();

        // Create the MQuestSpecialReward with an existing ID
        mQuestSpecialReward.setId(1L);
        MQuestSpecialRewardDTO mQuestSpecialRewardDTO = mQuestSpecialRewardMapper.toDto(mQuestSpecialReward);

        // An entity with an existing ID cannot be created, so this API call must fail
        restMQuestSpecialRewardMockMvc.perform(post("/api/m-quest-special-rewards")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mQuestSpecialRewardDTO)))
            .andExpect(status().isBadRequest());

        // Validate the MQuestSpecialReward in the database
        List<MQuestSpecialReward> mQuestSpecialRewardList = mQuestSpecialRewardRepository.findAll();
        assertThat(mQuestSpecialRewardList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkGroupIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = mQuestSpecialRewardRepository.findAll().size();
        // set the field null
        mQuestSpecialReward.setGroupId(null);

        // Create the MQuestSpecialReward, which fails.
        MQuestSpecialRewardDTO mQuestSpecialRewardDTO = mQuestSpecialRewardMapper.toDto(mQuestSpecialReward);

        restMQuestSpecialRewardMockMvc.perform(post("/api/m-quest-special-rewards")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mQuestSpecialRewardDTO)))
            .andExpect(status().isBadRequest());

        List<MQuestSpecialReward> mQuestSpecialRewardList = mQuestSpecialRewardRepository.findAll();
        assertThat(mQuestSpecialRewardList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkWeightIsRequired() throws Exception {
        int databaseSizeBeforeTest = mQuestSpecialRewardRepository.findAll().size();
        // set the field null
        mQuestSpecialReward.setWeight(null);

        // Create the MQuestSpecialReward, which fails.
        MQuestSpecialRewardDTO mQuestSpecialRewardDTO = mQuestSpecialRewardMapper.toDto(mQuestSpecialReward);

        restMQuestSpecialRewardMockMvc.perform(post("/api/m-quest-special-rewards")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mQuestSpecialRewardDTO)))
            .andExpect(status().isBadRequest());

        List<MQuestSpecialReward> mQuestSpecialRewardList = mQuestSpecialRewardRepository.findAll();
        assertThat(mQuestSpecialRewardList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkRankIsRequired() throws Exception {
        int databaseSizeBeforeTest = mQuestSpecialRewardRepository.findAll().size();
        // set the field null
        mQuestSpecialReward.setRank(null);

        // Create the MQuestSpecialReward, which fails.
        MQuestSpecialRewardDTO mQuestSpecialRewardDTO = mQuestSpecialRewardMapper.toDto(mQuestSpecialReward);

        restMQuestSpecialRewardMockMvc.perform(post("/api/m-quest-special-rewards")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mQuestSpecialRewardDTO)))
            .andExpect(status().isBadRequest());

        List<MQuestSpecialReward> mQuestSpecialRewardList = mQuestSpecialRewardRepository.findAll();
        assertThat(mQuestSpecialRewardList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkContentTypeIsRequired() throws Exception {
        int databaseSizeBeforeTest = mQuestSpecialRewardRepository.findAll().size();
        // set the field null
        mQuestSpecialReward.setContentType(null);

        // Create the MQuestSpecialReward, which fails.
        MQuestSpecialRewardDTO mQuestSpecialRewardDTO = mQuestSpecialRewardMapper.toDto(mQuestSpecialReward);

        restMQuestSpecialRewardMockMvc.perform(post("/api/m-quest-special-rewards")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mQuestSpecialRewardDTO)))
            .andExpect(status().isBadRequest());

        List<MQuestSpecialReward> mQuestSpecialRewardList = mQuestSpecialRewardRepository.findAll();
        assertThat(mQuestSpecialRewardList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkContentAmountIsRequired() throws Exception {
        int databaseSizeBeforeTest = mQuestSpecialRewardRepository.findAll().size();
        // set the field null
        mQuestSpecialReward.setContentAmount(null);

        // Create the MQuestSpecialReward, which fails.
        MQuestSpecialRewardDTO mQuestSpecialRewardDTO = mQuestSpecialRewardMapper.toDto(mQuestSpecialReward);

        restMQuestSpecialRewardMockMvc.perform(post("/api/m-quest-special-rewards")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mQuestSpecialRewardDTO)))
            .andExpect(status().isBadRequest());

        List<MQuestSpecialReward> mQuestSpecialRewardList = mQuestSpecialRewardRepository.findAll();
        assertThat(mQuestSpecialRewardList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllMQuestSpecialRewards() throws Exception {
        // Initialize the database
        mQuestSpecialRewardRepository.saveAndFlush(mQuestSpecialReward);

        // Get all the mQuestSpecialRewardList
        restMQuestSpecialRewardMockMvc.perform(get("/api/m-quest-special-rewards?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(mQuestSpecialReward.getId().intValue())))
            .andExpect(jsonPath("$.[*].groupId").value(hasItem(DEFAULT_GROUP_ID)))
            .andExpect(jsonPath("$.[*].weight").value(hasItem(DEFAULT_WEIGHT)))
            .andExpect(jsonPath("$.[*].rank").value(hasItem(DEFAULT_RANK)))
            .andExpect(jsonPath("$.[*].contentType").value(hasItem(DEFAULT_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].contentId").value(hasItem(DEFAULT_CONTENT_ID)))
            .andExpect(jsonPath("$.[*].contentAmount").value(hasItem(DEFAULT_CONTENT_AMOUNT)));
    }
    
    @Test
    @Transactional
    public void getMQuestSpecialReward() throws Exception {
        // Initialize the database
        mQuestSpecialRewardRepository.saveAndFlush(mQuestSpecialReward);

        // Get the mQuestSpecialReward
        restMQuestSpecialRewardMockMvc.perform(get("/api/m-quest-special-rewards/{id}", mQuestSpecialReward.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(mQuestSpecialReward.getId().intValue()))
            .andExpect(jsonPath("$.groupId").value(DEFAULT_GROUP_ID))
            .andExpect(jsonPath("$.weight").value(DEFAULT_WEIGHT))
            .andExpect(jsonPath("$.rank").value(DEFAULT_RANK))
            .andExpect(jsonPath("$.contentType").value(DEFAULT_CONTENT_TYPE))
            .andExpect(jsonPath("$.contentId").value(DEFAULT_CONTENT_ID))
            .andExpect(jsonPath("$.contentAmount").value(DEFAULT_CONTENT_AMOUNT));
    }

    @Test
    @Transactional
    public void getAllMQuestSpecialRewardsByGroupIdIsEqualToSomething() throws Exception {
        // Initialize the database
        mQuestSpecialRewardRepository.saveAndFlush(mQuestSpecialReward);

        // Get all the mQuestSpecialRewardList where groupId equals to DEFAULT_GROUP_ID
        defaultMQuestSpecialRewardShouldBeFound("groupId.equals=" + DEFAULT_GROUP_ID);

        // Get all the mQuestSpecialRewardList where groupId equals to UPDATED_GROUP_ID
        defaultMQuestSpecialRewardShouldNotBeFound("groupId.equals=" + UPDATED_GROUP_ID);
    }

    @Test
    @Transactional
    public void getAllMQuestSpecialRewardsByGroupIdIsInShouldWork() throws Exception {
        // Initialize the database
        mQuestSpecialRewardRepository.saveAndFlush(mQuestSpecialReward);

        // Get all the mQuestSpecialRewardList where groupId in DEFAULT_GROUP_ID or UPDATED_GROUP_ID
        defaultMQuestSpecialRewardShouldBeFound("groupId.in=" + DEFAULT_GROUP_ID + "," + UPDATED_GROUP_ID);

        // Get all the mQuestSpecialRewardList where groupId equals to UPDATED_GROUP_ID
        defaultMQuestSpecialRewardShouldNotBeFound("groupId.in=" + UPDATED_GROUP_ID);
    }

    @Test
    @Transactional
    public void getAllMQuestSpecialRewardsByGroupIdIsNullOrNotNull() throws Exception {
        // Initialize the database
        mQuestSpecialRewardRepository.saveAndFlush(mQuestSpecialReward);

        // Get all the mQuestSpecialRewardList where groupId is not null
        defaultMQuestSpecialRewardShouldBeFound("groupId.specified=true");

        // Get all the mQuestSpecialRewardList where groupId is null
        defaultMQuestSpecialRewardShouldNotBeFound("groupId.specified=false");
    }

    @Test
    @Transactional
    public void getAllMQuestSpecialRewardsByGroupIdIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mQuestSpecialRewardRepository.saveAndFlush(mQuestSpecialReward);

        // Get all the mQuestSpecialRewardList where groupId greater than or equals to DEFAULT_GROUP_ID
        defaultMQuestSpecialRewardShouldBeFound("groupId.greaterOrEqualThan=" + DEFAULT_GROUP_ID);

        // Get all the mQuestSpecialRewardList where groupId greater than or equals to UPDATED_GROUP_ID
        defaultMQuestSpecialRewardShouldNotBeFound("groupId.greaterOrEqualThan=" + UPDATED_GROUP_ID);
    }

    @Test
    @Transactional
    public void getAllMQuestSpecialRewardsByGroupIdIsLessThanSomething() throws Exception {
        // Initialize the database
        mQuestSpecialRewardRepository.saveAndFlush(mQuestSpecialReward);

        // Get all the mQuestSpecialRewardList where groupId less than or equals to DEFAULT_GROUP_ID
        defaultMQuestSpecialRewardShouldNotBeFound("groupId.lessThan=" + DEFAULT_GROUP_ID);

        // Get all the mQuestSpecialRewardList where groupId less than or equals to UPDATED_GROUP_ID
        defaultMQuestSpecialRewardShouldBeFound("groupId.lessThan=" + UPDATED_GROUP_ID);
    }


    @Test
    @Transactional
    public void getAllMQuestSpecialRewardsByWeightIsEqualToSomething() throws Exception {
        // Initialize the database
        mQuestSpecialRewardRepository.saveAndFlush(mQuestSpecialReward);

        // Get all the mQuestSpecialRewardList where weight equals to DEFAULT_WEIGHT
        defaultMQuestSpecialRewardShouldBeFound("weight.equals=" + DEFAULT_WEIGHT);

        // Get all the mQuestSpecialRewardList where weight equals to UPDATED_WEIGHT
        defaultMQuestSpecialRewardShouldNotBeFound("weight.equals=" + UPDATED_WEIGHT);
    }

    @Test
    @Transactional
    public void getAllMQuestSpecialRewardsByWeightIsInShouldWork() throws Exception {
        // Initialize the database
        mQuestSpecialRewardRepository.saveAndFlush(mQuestSpecialReward);

        // Get all the mQuestSpecialRewardList where weight in DEFAULT_WEIGHT or UPDATED_WEIGHT
        defaultMQuestSpecialRewardShouldBeFound("weight.in=" + DEFAULT_WEIGHT + "," + UPDATED_WEIGHT);

        // Get all the mQuestSpecialRewardList where weight equals to UPDATED_WEIGHT
        defaultMQuestSpecialRewardShouldNotBeFound("weight.in=" + UPDATED_WEIGHT);
    }

    @Test
    @Transactional
    public void getAllMQuestSpecialRewardsByWeightIsNullOrNotNull() throws Exception {
        // Initialize the database
        mQuestSpecialRewardRepository.saveAndFlush(mQuestSpecialReward);

        // Get all the mQuestSpecialRewardList where weight is not null
        defaultMQuestSpecialRewardShouldBeFound("weight.specified=true");

        // Get all the mQuestSpecialRewardList where weight is null
        defaultMQuestSpecialRewardShouldNotBeFound("weight.specified=false");
    }

    @Test
    @Transactional
    public void getAllMQuestSpecialRewardsByWeightIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mQuestSpecialRewardRepository.saveAndFlush(mQuestSpecialReward);

        // Get all the mQuestSpecialRewardList where weight greater than or equals to DEFAULT_WEIGHT
        defaultMQuestSpecialRewardShouldBeFound("weight.greaterOrEqualThan=" + DEFAULT_WEIGHT);

        // Get all the mQuestSpecialRewardList where weight greater than or equals to UPDATED_WEIGHT
        defaultMQuestSpecialRewardShouldNotBeFound("weight.greaterOrEqualThan=" + UPDATED_WEIGHT);
    }

    @Test
    @Transactional
    public void getAllMQuestSpecialRewardsByWeightIsLessThanSomething() throws Exception {
        // Initialize the database
        mQuestSpecialRewardRepository.saveAndFlush(mQuestSpecialReward);

        // Get all the mQuestSpecialRewardList where weight less than or equals to DEFAULT_WEIGHT
        defaultMQuestSpecialRewardShouldNotBeFound("weight.lessThan=" + DEFAULT_WEIGHT);

        // Get all the mQuestSpecialRewardList where weight less than or equals to UPDATED_WEIGHT
        defaultMQuestSpecialRewardShouldBeFound("weight.lessThan=" + UPDATED_WEIGHT);
    }


    @Test
    @Transactional
    public void getAllMQuestSpecialRewardsByRankIsEqualToSomething() throws Exception {
        // Initialize the database
        mQuestSpecialRewardRepository.saveAndFlush(mQuestSpecialReward);

        // Get all the mQuestSpecialRewardList where rank equals to DEFAULT_RANK
        defaultMQuestSpecialRewardShouldBeFound("rank.equals=" + DEFAULT_RANK);

        // Get all the mQuestSpecialRewardList where rank equals to UPDATED_RANK
        defaultMQuestSpecialRewardShouldNotBeFound("rank.equals=" + UPDATED_RANK);
    }

    @Test
    @Transactional
    public void getAllMQuestSpecialRewardsByRankIsInShouldWork() throws Exception {
        // Initialize the database
        mQuestSpecialRewardRepository.saveAndFlush(mQuestSpecialReward);

        // Get all the mQuestSpecialRewardList where rank in DEFAULT_RANK or UPDATED_RANK
        defaultMQuestSpecialRewardShouldBeFound("rank.in=" + DEFAULT_RANK + "," + UPDATED_RANK);

        // Get all the mQuestSpecialRewardList where rank equals to UPDATED_RANK
        defaultMQuestSpecialRewardShouldNotBeFound("rank.in=" + UPDATED_RANK);
    }

    @Test
    @Transactional
    public void getAllMQuestSpecialRewardsByRankIsNullOrNotNull() throws Exception {
        // Initialize the database
        mQuestSpecialRewardRepository.saveAndFlush(mQuestSpecialReward);

        // Get all the mQuestSpecialRewardList where rank is not null
        defaultMQuestSpecialRewardShouldBeFound("rank.specified=true");

        // Get all the mQuestSpecialRewardList where rank is null
        defaultMQuestSpecialRewardShouldNotBeFound("rank.specified=false");
    }

    @Test
    @Transactional
    public void getAllMQuestSpecialRewardsByRankIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mQuestSpecialRewardRepository.saveAndFlush(mQuestSpecialReward);

        // Get all the mQuestSpecialRewardList where rank greater than or equals to DEFAULT_RANK
        defaultMQuestSpecialRewardShouldBeFound("rank.greaterOrEqualThan=" + DEFAULT_RANK);

        // Get all the mQuestSpecialRewardList where rank greater than or equals to UPDATED_RANK
        defaultMQuestSpecialRewardShouldNotBeFound("rank.greaterOrEqualThan=" + UPDATED_RANK);
    }

    @Test
    @Transactional
    public void getAllMQuestSpecialRewardsByRankIsLessThanSomething() throws Exception {
        // Initialize the database
        mQuestSpecialRewardRepository.saveAndFlush(mQuestSpecialReward);

        // Get all the mQuestSpecialRewardList where rank less than or equals to DEFAULT_RANK
        defaultMQuestSpecialRewardShouldNotBeFound("rank.lessThan=" + DEFAULT_RANK);

        // Get all the mQuestSpecialRewardList where rank less than or equals to UPDATED_RANK
        defaultMQuestSpecialRewardShouldBeFound("rank.lessThan=" + UPDATED_RANK);
    }


    @Test
    @Transactional
    public void getAllMQuestSpecialRewardsByContentTypeIsEqualToSomething() throws Exception {
        // Initialize the database
        mQuestSpecialRewardRepository.saveAndFlush(mQuestSpecialReward);

        // Get all the mQuestSpecialRewardList where contentType equals to DEFAULT_CONTENT_TYPE
        defaultMQuestSpecialRewardShouldBeFound("contentType.equals=" + DEFAULT_CONTENT_TYPE);

        // Get all the mQuestSpecialRewardList where contentType equals to UPDATED_CONTENT_TYPE
        defaultMQuestSpecialRewardShouldNotBeFound("contentType.equals=" + UPDATED_CONTENT_TYPE);
    }

    @Test
    @Transactional
    public void getAllMQuestSpecialRewardsByContentTypeIsInShouldWork() throws Exception {
        // Initialize the database
        mQuestSpecialRewardRepository.saveAndFlush(mQuestSpecialReward);

        // Get all the mQuestSpecialRewardList where contentType in DEFAULT_CONTENT_TYPE or UPDATED_CONTENT_TYPE
        defaultMQuestSpecialRewardShouldBeFound("contentType.in=" + DEFAULT_CONTENT_TYPE + "," + UPDATED_CONTENT_TYPE);

        // Get all the mQuestSpecialRewardList where contentType equals to UPDATED_CONTENT_TYPE
        defaultMQuestSpecialRewardShouldNotBeFound("contentType.in=" + UPDATED_CONTENT_TYPE);
    }

    @Test
    @Transactional
    public void getAllMQuestSpecialRewardsByContentTypeIsNullOrNotNull() throws Exception {
        // Initialize the database
        mQuestSpecialRewardRepository.saveAndFlush(mQuestSpecialReward);

        // Get all the mQuestSpecialRewardList where contentType is not null
        defaultMQuestSpecialRewardShouldBeFound("contentType.specified=true");

        // Get all the mQuestSpecialRewardList where contentType is null
        defaultMQuestSpecialRewardShouldNotBeFound("contentType.specified=false");
    }

    @Test
    @Transactional
    public void getAllMQuestSpecialRewardsByContentTypeIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mQuestSpecialRewardRepository.saveAndFlush(mQuestSpecialReward);

        // Get all the mQuestSpecialRewardList where contentType greater than or equals to DEFAULT_CONTENT_TYPE
        defaultMQuestSpecialRewardShouldBeFound("contentType.greaterOrEqualThan=" + DEFAULT_CONTENT_TYPE);

        // Get all the mQuestSpecialRewardList where contentType greater than or equals to UPDATED_CONTENT_TYPE
        defaultMQuestSpecialRewardShouldNotBeFound("contentType.greaterOrEqualThan=" + UPDATED_CONTENT_TYPE);
    }

    @Test
    @Transactional
    public void getAllMQuestSpecialRewardsByContentTypeIsLessThanSomething() throws Exception {
        // Initialize the database
        mQuestSpecialRewardRepository.saveAndFlush(mQuestSpecialReward);

        // Get all the mQuestSpecialRewardList where contentType less than or equals to DEFAULT_CONTENT_TYPE
        defaultMQuestSpecialRewardShouldNotBeFound("contentType.lessThan=" + DEFAULT_CONTENT_TYPE);

        // Get all the mQuestSpecialRewardList where contentType less than or equals to UPDATED_CONTENT_TYPE
        defaultMQuestSpecialRewardShouldBeFound("contentType.lessThan=" + UPDATED_CONTENT_TYPE);
    }


    @Test
    @Transactional
    public void getAllMQuestSpecialRewardsByContentIdIsEqualToSomething() throws Exception {
        // Initialize the database
        mQuestSpecialRewardRepository.saveAndFlush(mQuestSpecialReward);

        // Get all the mQuestSpecialRewardList where contentId equals to DEFAULT_CONTENT_ID
        defaultMQuestSpecialRewardShouldBeFound("contentId.equals=" + DEFAULT_CONTENT_ID);

        // Get all the mQuestSpecialRewardList where contentId equals to UPDATED_CONTENT_ID
        defaultMQuestSpecialRewardShouldNotBeFound("contentId.equals=" + UPDATED_CONTENT_ID);
    }

    @Test
    @Transactional
    public void getAllMQuestSpecialRewardsByContentIdIsInShouldWork() throws Exception {
        // Initialize the database
        mQuestSpecialRewardRepository.saveAndFlush(mQuestSpecialReward);

        // Get all the mQuestSpecialRewardList where contentId in DEFAULT_CONTENT_ID or UPDATED_CONTENT_ID
        defaultMQuestSpecialRewardShouldBeFound("contentId.in=" + DEFAULT_CONTENT_ID + "," + UPDATED_CONTENT_ID);

        // Get all the mQuestSpecialRewardList where contentId equals to UPDATED_CONTENT_ID
        defaultMQuestSpecialRewardShouldNotBeFound("contentId.in=" + UPDATED_CONTENT_ID);
    }

    @Test
    @Transactional
    public void getAllMQuestSpecialRewardsByContentIdIsNullOrNotNull() throws Exception {
        // Initialize the database
        mQuestSpecialRewardRepository.saveAndFlush(mQuestSpecialReward);

        // Get all the mQuestSpecialRewardList where contentId is not null
        defaultMQuestSpecialRewardShouldBeFound("contentId.specified=true");

        // Get all the mQuestSpecialRewardList where contentId is null
        defaultMQuestSpecialRewardShouldNotBeFound("contentId.specified=false");
    }

    @Test
    @Transactional
    public void getAllMQuestSpecialRewardsByContentIdIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mQuestSpecialRewardRepository.saveAndFlush(mQuestSpecialReward);

        // Get all the mQuestSpecialRewardList where contentId greater than or equals to DEFAULT_CONTENT_ID
        defaultMQuestSpecialRewardShouldBeFound("contentId.greaterOrEqualThan=" + DEFAULT_CONTENT_ID);

        // Get all the mQuestSpecialRewardList where contentId greater than or equals to UPDATED_CONTENT_ID
        defaultMQuestSpecialRewardShouldNotBeFound("contentId.greaterOrEqualThan=" + UPDATED_CONTENT_ID);
    }

    @Test
    @Transactional
    public void getAllMQuestSpecialRewardsByContentIdIsLessThanSomething() throws Exception {
        // Initialize the database
        mQuestSpecialRewardRepository.saveAndFlush(mQuestSpecialReward);

        // Get all the mQuestSpecialRewardList where contentId less than or equals to DEFAULT_CONTENT_ID
        defaultMQuestSpecialRewardShouldNotBeFound("contentId.lessThan=" + DEFAULT_CONTENT_ID);

        // Get all the mQuestSpecialRewardList where contentId less than or equals to UPDATED_CONTENT_ID
        defaultMQuestSpecialRewardShouldBeFound("contentId.lessThan=" + UPDATED_CONTENT_ID);
    }


    @Test
    @Transactional
    public void getAllMQuestSpecialRewardsByContentAmountIsEqualToSomething() throws Exception {
        // Initialize the database
        mQuestSpecialRewardRepository.saveAndFlush(mQuestSpecialReward);

        // Get all the mQuestSpecialRewardList where contentAmount equals to DEFAULT_CONTENT_AMOUNT
        defaultMQuestSpecialRewardShouldBeFound("contentAmount.equals=" + DEFAULT_CONTENT_AMOUNT);

        // Get all the mQuestSpecialRewardList where contentAmount equals to UPDATED_CONTENT_AMOUNT
        defaultMQuestSpecialRewardShouldNotBeFound("contentAmount.equals=" + UPDATED_CONTENT_AMOUNT);
    }

    @Test
    @Transactional
    public void getAllMQuestSpecialRewardsByContentAmountIsInShouldWork() throws Exception {
        // Initialize the database
        mQuestSpecialRewardRepository.saveAndFlush(mQuestSpecialReward);

        // Get all the mQuestSpecialRewardList where contentAmount in DEFAULT_CONTENT_AMOUNT or UPDATED_CONTENT_AMOUNT
        defaultMQuestSpecialRewardShouldBeFound("contentAmount.in=" + DEFAULT_CONTENT_AMOUNT + "," + UPDATED_CONTENT_AMOUNT);

        // Get all the mQuestSpecialRewardList where contentAmount equals to UPDATED_CONTENT_AMOUNT
        defaultMQuestSpecialRewardShouldNotBeFound("contentAmount.in=" + UPDATED_CONTENT_AMOUNT);
    }

    @Test
    @Transactional
    public void getAllMQuestSpecialRewardsByContentAmountIsNullOrNotNull() throws Exception {
        // Initialize the database
        mQuestSpecialRewardRepository.saveAndFlush(mQuestSpecialReward);

        // Get all the mQuestSpecialRewardList where contentAmount is not null
        defaultMQuestSpecialRewardShouldBeFound("contentAmount.specified=true");

        // Get all the mQuestSpecialRewardList where contentAmount is null
        defaultMQuestSpecialRewardShouldNotBeFound("contentAmount.specified=false");
    }

    @Test
    @Transactional
    public void getAllMQuestSpecialRewardsByContentAmountIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mQuestSpecialRewardRepository.saveAndFlush(mQuestSpecialReward);

        // Get all the mQuestSpecialRewardList where contentAmount greater than or equals to DEFAULT_CONTENT_AMOUNT
        defaultMQuestSpecialRewardShouldBeFound("contentAmount.greaterOrEqualThan=" + DEFAULT_CONTENT_AMOUNT);

        // Get all the mQuestSpecialRewardList where contentAmount greater than or equals to UPDATED_CONTENT_AMOUNT
        defaultMQuestSpecialRewardShouldNotBeFound("contentAmount.greaterOrEqualThan=" + UPDATED_CONTENT_AMOUNT);
    }

    @Test
    @Transactional
    public void getAllMQuestSpecialRewardsByContentAmountIsLessThanSomething() throws Exception {
        // Initialize the database
        mQuestSpecialRewardRepository.saveAndFlush(mQuestSpecialReward);

        // Get all the mQuestSpecialRewardList where contentAmount less than or equals to DEFAULT_CONTENT_AMOUNT
        defaultMQuestSpecialRewardShouldNotBeFound("contentAmount.lessThan=" + DEFAULT_CONTENT_AMOUNT);

        // Get all the mQuestSpecialRewardList where contentAmount less than or equals to UPDATED_CONTENT_AMOUNT
        defaultMQuestSpecialRewardShouldBeFound("contentAmount.lessThan=" + UPDATED_CONTENT_AMOUNT);
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultMQuestSpecialRewardShouldBeFound(String filter) throws Exception {
        restMQuestSpecialRewardMockMvc.perform(get("/api/m-quest-special-rewards?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(mQuestSpecialReward.getId().intValue())))
            .andExpect(jsonPath("$.[*].groupId").value(hasItem(DEFAULT_GROUP_ID)))
            .andExpect(jsonPath("$.[*].weight").value(hasItem(DEFAULT_WEIGHT)))
            .andExpect(jsonPath("$.[*].rank").value(hasItem(DEFAULT_RANK)))
            .andExpect(jsonPath("$.[*].contentType").value(hasItem(DEFAULT_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].contentId").value(hasItem(DEFAULT_CONTENT_ID)))
            .andExpect(jsonPath("$.[*].contentAmount").value(hasItem(DEFAULT_CONTENT_AMOUNT)));

        // Check, that the count call also returns 1
        restMQuestSpecialRewardMockMvc.perform(get("/api/m-quest-special-rewards/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultMQuestSpecialRewardShouldNotBeFound(String filter) throws Exception {
        restMQuestSpecialRewardMockMvc.perform(get("/api/m-quest-special-rewards?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restMQuestSpecialRewardMockMvc.perform(get("/api/m-quest-special-rewards/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("0"));
    }


    @Test
    @Transactional
    public void getNonExistingMQuestSpecialReward() throws Exception {
        // Get the mQuestSpecialReward
        restMQuestSpecialRewardMockMvc.perform(get("/api/m-quest-special-rewards/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateMQuestSpecialReward() throws Exception {
        // Initialize the database
        mQuestSpecialRewardRepository.saveAndFlush(mQuestSpecialReward);

        int databaseSizeBeforeUpdate = mQuestSpecialRewardRepository.findAll().size();

        // Update the mQuestSpecialReward
        MQuestSpecialReward updatedMQuestSpecialReward = mQuestSpecialRewardRepository.findById(mQuestSpecialReward.getId()).get();
        // Disconnect from session so that the updates on updatedMQuestSpecialReward are not directly saved in db
        em.detach(updatedMQuestSpecialReward);
        updatedMQuestSpecialReward
            .groupId(UPDATED_GROUP_ID)
            .weight(UPDATED_WEIGHT)
            .rank(UPDATED_RANK)
            .contentType(UPDATED_CONTENT_TYPE)
            .contentId(UPDATED_CONTENT_ID)
            .contentAmount(UPDATED_CONTENT_AMOUNT);
        MQuestSpecialRewardDTO mQuestSpecialRewardDTO = mQuestSpecialRewardMapper.toDto(updatedMQuestSpecialReward);

        restMQuestSpecialRewardMockMvc.perform(put("/api/m-quest-special-rewards")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mQuestSpecialRewardDTO)))
            .andExpect(status().isOk());

        // Validate the MQuestSpecialReward in the database
        List<MQuestSpecialReward> mQuestSpecialRewardList = mQuestSpecialRewardRepository.findAll();
        assertThat(mQuestSpecialRewardList).hasSize(databaseSizeBeforeUpdate);
        MQuestSpecialReward testMQuestSpecialReward = mQuestSpecialRewardList.get(mQuestSpecialRewardList.size() - 1);
        assertThat(testMQuestSpecialReward.getGroupId()).isEqualTo(UPDATED_GROUP_ID);
        assertThat(testMQuestSpecialReward.getWeight()).isEqualTo(UPDATED_WEIGHT);
        assertThat(testMQuestSpecialReward.getRank()).isEqualTo(UPDATED_RANK);
        assertThat(testMQuestSpecialReward.getContentType()).isEqualTo(UPDATED_CONTENT_TYPE);
        assertThat(testMQuestSpecialReward.getContentId()).isEqualTo(UPDATED_CONTENT_ID);
        assertThat(testMQuestSpecialReward.getContentAmount()).isEqualTo(UPDATED_CONTENT_AMOUNT);
    }

    @Test
    @Transactional
    public void updateNonExistingMQuestSpecialReward() throws Exception {
        int databaseSizeBeforeUpdate = mQuestSpecialRewardRepository.findAll().size();

        // Create the MQuestSpecialReward
        MQuestSpecialRewardDTO mQuestSpecialRewardDTO = mQuestSpecialRewardMapper.toDto(mQuestSpecialReward);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restMQuestSpecialRewardMockMvc.perform(put("/api/m-quest-special-rewards")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mQuestSpecialRewardDTO)))
            .andExpect(status().isBadRequest());

        // Validate the MQuestSpecialReward in the database
        List<MQuestSpecialReward> mQuestSpecialRewardList = mQuestSpecialRewardRepository.findAll();
        assertThat(mQuestSpecialRewardList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteMQuestSpecialReward() throws Exception {
        // Initialize the database
        mQuestSpecialRewardRepository.saveAndFlush(mQuestSpecialReward);

        int databaseSizeBeforeDelete = mQuestSpecialRewardRepository.findAll().size();

        // Delete the mQuestSpecialReward
        restMQuestSpecialRewardMockMvc.perform(delete("/api/m-quest-special-rewards/{id}", mQuestSpecialReward.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database is empty
        List<MQuestSpecialReward> mQuestSpecialRewardList = mQuestSpecialRewardRepository.findAll();
        assertThat(mQuestSpecialRewardList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(MQuestSpecialReward.class);
        MQuestSpecialReward mQuestSpecialReward1 = new MQuestSpecialReward();
        mQuestSpecialReward1.setId(1L);
        MQuestSpecialReward mQuestSpecialReward2 = new MQuestSpecialReward();
        mQuestSpecialReward2.setId(mQuestSpecialReward1.getId());
        assertThat(mQuestSpecialReward1).isEqualTo(mQuestSpecialReward2);
        mQuestSpecialReward2.setId(2L);
        assertThat(mQuestSpecialReward1).isNotEqualTo(mQuestSpecialReward2);
        mQuestSpecialReward1.setId(null);
        assertThat(mQuestSpecialReward1).isNotEqualTo(mQuestSpecialReward2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(MQuestSpecialRewardDTO.class);
        MQuestSpecialRewardDTO mQuestSpecialRewardDTO1 = new MQuestSpecialRewardDTO();
        mQuestSpecialRewardDTO1.setId(1L);
        MQuestSpecialRewardDTO mQuestSpecialRewardDTO2 = new MQuestSpecialRewardDTO();
        assertThat(mQuestSpecialRewardDTO1).isNotEqualTo(mQuestSpecialRewardDTO2);
        mQuestSpecialRewardDTO2.setId(mQuestSpecialRewardDTO1.getId());
        assertThat(mQuestSpecialRewardDTO1).isEqualTo(mQuestSpecialRewardDTO2);
        mQuestSpecialRewardDTO2.setId(2L);
        assertThat(mQuestSpecialRewardDTO1).isNotEqualTo(mQuestSpecialRewardDTO2);
        mQuestSpecialRewardDTO1.setId(null);
        assertThat(mQuestSpecialRewardDTO1).isNotEqualTo(mQuestSpecialRewardDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(mQuestSpecialRewardMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(mQuestSpecialRewardMapper.fromId(null)).isNull();
    }
}
