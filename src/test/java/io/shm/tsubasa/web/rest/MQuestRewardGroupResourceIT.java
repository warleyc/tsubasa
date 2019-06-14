package io.shm.tsubasa.web.rest;

import io.shm.tsubasa.TsubasaApp;
import io.shm.tsubasa.domain.MQuestRewardGroup;
import io.shm.tsubasa.repository.MQuestRewardGroupRepository;
import io.shm.tsubasa.service.MQuestRewardGroupService;
import io.shm.tsubasa.service.dto.MQuestRewardGroupDTO;
import io.shm.tsubasa.service.mapper.MQuestRewardGroupMapper;
import io.shm.tsubasa.web.rest.errors.ExceptionTranslator;
import io.shm.tsubasa.service.dto.MQuestRewardGroupCriteria;
import io.shm.tsubasa.service.MQuestRewardGroupQueryService;

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
 * Integration tests for the {@Link MQuestRewardGroupResource} REST controller.
 */
@SpringBootTest(classes = TsubasaApp.class)
public class MQuestRewardGroupResourceIT {

    private static final Integer DEFAULT_GROUP_ID = 1;
    private static final Integer UPDATED_GROUP_ID = 2;

    private static final Integer DEFAULT_RATE = 1;
    private static final Integer UPDATED_RATE = 2;

    private static final Integer DEFAULT_RANK = 1;
    private static final Integer UPDATED_RANK = 2;

    private static final Integer DEFAULT_CONTENT_TYPE = 1;
    private static final Integer UPDATED_CONTENT_TYPE = 2;

    private static final Integer DEFAULT_CONTENT_ID = 1;
    private static final Integer UPDATED_CONTENT_ID = 2;

    private static final Integer DEFAULT_CONTENT_AMOUNT = 1;
    private static final Integer UPDATED_CONTENT_AMOUNT = 2;

    @Autowired
    private MQuestRewardGroupRepository mQuestRewardGroupRepository;

    @Autowired
    private MQuestRewardGroupMapper mQuestRewardGroupMapper;

    @Autowired
    private MQuestRewardGroupService mQuestRewardGroupService;

    @Autowired
    private MQuestRewardGroupQueryService mQuestRewardGroupQueryService;

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

    private MockMvc restMQuestRewardGroupMockMvc;

    private MQuestRewardGroup mQuestRewardGroup;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final MQuestRewardGroupResource mQuestRewardGroupResource = new MQuestRewardGroupResource(mQuestRewardGroupService, mQuestRewardGroupQueryService);
        this.restMQuestRewardGroupMockMvc = MockMvcBuilders.standaloneSetup(mQuestRewardGroupResource)
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
    public static MQuestRewardGroup createEntity(EntityManager em) {
        MQuestRewardGroup mQuestRewardGroup = new MQuestRewardGroup()
            .groupId(DEFAULT_GROUP_ID)
            .rate(DEFAULT_RATE)
            .rank(DEFAULT_RANK)
            .contentType(DEFAULT_CONTENT_TYPE)
            .contentId(DEFAULT_CONTENT_ID)
            .contentAmount(DEFAULT_CONTENT_AMOUNT);
        return mQuestRewardGroup;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static MQuestRewardGroup createUpdatedEntity(EntityManager em) {
        MQuestRewardGroup mQuestRewardGroup = new MQuestRewardGroup()
            .groupId(UPDATED_GROUP_ID)
            .rate(UPDATED_RATE)
            .rank(UPDATED_RANK)
            .contentType(UPDATED_CONTENT_TYPE)
            .contentId(UPDATED_CONTENT_ID)
            .contentAmount(UPDATED_CONTENT_AMOUNT);
        return mQuestRewardGroup;
    }

    @BeforeEach
    public void initTest() {
        mQuestRewardGroup = createEntity(em);
    }

    @Test
    @Transactional
    public void createMQuestRewardGroup() throws Exception {
        int databaseSizeBeforeCreate = mQuestRewardGroupRepository.findAll().size();

        // Create the MQuestRewardGroup
        MQuestRewardGroupDTO mQuestRewardGroupDTO = mQuestRewardGroupMapper.toDto(mQuestRewardGroup);
        restMQuestRewardGroupMockMvc.perform(post("/api/m-quest-reward-groups")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mQuestRewardGroupDTO)))
            .andExpect(status().isCreated());

        // Validate the MQuestRewardGroup in the database
        List<MQuestRewardGroup> mQuestRewardGroupList = mQuestRewardGroupRepository.findAll();
        assertThat(mQuestRewardGroupList).hasSize(databaseSizeBeforeCreate + 1);
        MQuestRewardGroup testMQuestRewardGroup = mQuestRewardGroupList.get(mQuestRewardGroupList.size() - 1);
        assertThat(testMQuestRewardGroup.getGroupId()).isEqualTo(DEFAULT_GROUP_ID);
        assertThat(testMQuestRewardGroup.getRate()).isEqualTo(DEFAULT_RATE);
        assertThat(testMQuestRewardGroup.getRank()).isEqualTo(DEFAULT_RANK);
        assertThat(testMQuestRewardGroup.getContentType()).isEqualTo(DEFAULT_CONTENT_TYPE);
        assertThat(testMQuestRewardGroup.getContentId()).isEqualTo(DEFAULT_CONTENT_ID);
        assertThat(testMQuestRewardGroup.getContentAmount()).isEqualTo(DEFAULT_CONTENT_AMOUNT);
    }

    @Test
    @Transactional
    public void createMQuestRewardGroupWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = mQuestRewardGroupRepository.findAll().size();

        // Create the MQuestRewardGroup with an existing ID
        mQuestRewardGroup.setId(1L);
        MQuestRewardGroupDTO mQuestRewardGroupDTO = mQuestRewardGroupMapper.toDto(mQuestRewardGroup);

        // An entity with an existing ID cannot be created, so this API call must fail
        restMQuestRewardGroupMockMvc.perform(post("/api/m-quest-reward-groups")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mQuestRewardGroupDTO)))
            .andExpect(status().isBadRequest());

        // Validate the MQuestRewardGroup in the database
        List<MQuestRewardGroup> mQuestRewardGroupList = mQuestRewardGroupRepository.findAll();
        assertThat(mQuestRewardGroupList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkGroupIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = mQuestRewardGroupRepository.findAll().size();
        // set the field null
        mQuestRewardGroup.setGroupId(null);

        // Create the MQuestRewardGroup, which fails.
        MQuestRewardGroupDTO mQuestRewardGroupDTO = mQuestRewardGroupMapper.toDto(mQuestRewardGroup);

        restMQuestRewardGroupMockMvc.perform(post("/api/m-quest-reward-groups")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mQuestRewardGroupDTO)))
            .andExpect(status().isBadRequest());

        List<MQuestRewardGroup> mQuestRewardGroupList = mQuestRewardGroupRepository.findAll();
        assertThat(mQuestRewardGroupList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkRateIsRequired() throws Exception {
        int databaseSizeBeforeTest = mQuestRewardGroupRepository.findAll().size();
        // set the field null
        mQuestRewardGroup.setRate(null);

        // Create the MQuestRewardGroup, which fails.
        MQuestRewardGroupDTO mQuestRewardGroupDTO = mQuestRewardGroupMapper.toDto(mQuestRewardGroup);

        restMQuestRewardGroupMockMvc.perform(post("/api/m-quest-reward-groups")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mQuestRewardGroupDTO)))
            .andExpect(status().isBadRequest());

        List<MQuestRewardGroup> mQuestRewardGroupList = mQuestRewardGroupRepository.findAll();
        assertThat(mQuestRewardGroupList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkRankIsRequired() throws Exception {
        int databaseSizeBeforeTest = mQuestRewardGroupRepository.findAll().size();
        // set the field null
        mQuestRewardGroup.setRank(null);

        // Create the MQuestRewardGroup, which fails.
        MQuestRewardGroupDTO mQuestRewardGroupDTO = mQuestRewardGroupMapper.toDto(mQuestRewardGroup);

        restMQuestRewardGroupMockMvc.perform(post("/api/m-quest-reward-groups")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mQuestRewardGroupDTO)))
            .andExpect(status().isBadRequest());

        List<MQuestRewardGroup> mQuestRewardGroupList = mQuestRewardGroupRepository.findAll();
        assertThat(mQuestRewardGroupList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkContentTypeIsRequired() throws Exception {
        int databaseSizeBeforeTest = mQuestRewardGroupRepository.findAll().size();
        // set the field null
        mQuestRewardGroup.setContentType(null);

        // Create the MQuestRewardGroup, which fails.
        MQuestRewardGroupDTO mQuestRewardGroupDTO = mQuestRewardGroupMapper.toDto(mQuestRewardGroup);

        restMQuestRewardGroupMockMvc.perform(post("/api/m-quest-reward-groups")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mQuestRewardGroupDTO)))
            .andExpect(status().isBadRequest());

        List<MQuestRewardGroup> mQuestRewardGroupList = mQuestRewardGroupRepository.findAll();
        assertThat(mQuestRewardGroupList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkContentAmountIsRequired() throws Exception {
        int databaseSizeBeforeTest = mQuestRewardGroupRepository.findAll().size();
        // set the field null
        mQuestRewardGroup.setContentAmount(null);

        // Create the MQuestRewardGroup, which fails.
        MQuestRewardGroupDTO mQuestRewardGroupDTO = mQuestRewardGroupMapper.toDto(mQuestRewardGroup);

        restMQuestRewardGroupMockMvc.perform(post("/api/m-quest-reward-groups")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mQuestRewardGroupDTO)))
            .andExpect(status().isBadRequest());

        List<MQuestRewardGroup> mQuestRewardGroupList = mQuestRewardGroupRepository.findAll();
        assertThat(mQuestRewardGroupList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllMQuestRewardGroups() throws Exception {
        // Initialize the database
        mQuestRewardGroupRepository.saveAndFlush(mQuestRewardGroup);

        // Get all the mQuestRewardGroupList
        restMQuestRewardGroupMockMvc.perform(get("/api/m-quest-reward-groups?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(mQuestRewardGroup.getId().intValue())))
            .andExpect(jsonPath("$.[*].groupId").value(hasItem(DEFAULT_GROUP_ID)))
            .andExpect(jsonPath("$.[*].rate").value(hasItem(DEFAULT_RATE)))
            .andExpect(jsonPath("$.[*].rank").value(hasItem(DEFAULT_RANK)))
            .andExpect(jsonPath("$.[*].contentType").value(hasItem(DEFAULT_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].contentId").value(hasItem(DEFAULT_CONTENT_ID)))
            .andExpect(jsonPath("$.[*].contentAmount").value(hasItem(DEFAULT_CONTENT_AMOUNT)));
    }
    
    @Test
    @Transactional
    public void getMQuestRewardGroup() throws Exception {
        // Initialize the database
        mQuestRewardGroupRepository.saveAndFlush(mQuestRewardGroup);

        // Get the mQuestRewardGroup
        restMQuestRewardGroupMockMvc.perform(get("/api/m-quest-reward-groups/{id}", mQuestRewardGroup.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(mQuestRewardGroup.getId().intValue()))
            .andExpect(jsonPath("$.groupId").value(DEFAULT_GROUP_ID))
            .andExpect(jsonPath("$.rate").value(DEFAULT_RATE))
            .andExpect(jsonPath("$.rank").value(DEFAULT_RANK))
            .andExpect(jsonPath("$.contentType").value(DEFAULT_CONTENT_TYPE))
            .andExpect(jsonPath("$.contentId").value(DEFAULT_CONTENT_ID))
            .andExpect(jsonPath("$.contentAmount").value(DEFAULT_CONTENT_AMOUNT));
    }

    @Test
    @Transactional
    public void getAllMQuestRewardGroupsByGroupIdIsEqualToSomething() throws Exception {
        // Initialize the database
        mQuestRewardGroupRepository.saveAndFlush(mQuestRewardGroup);

        // Get all the mQuestRewardGroupList where groupId equals to DEFAULT_GROUP_ID
        defaultMQuestRewardGroupShouldBeFound("groupId.equals=" + DEFAULT_GROUP_ID);

        // Get all the mQuestRewardGroupList where groupId equals to UPDATED_GROUP_ID
        defaultMQuestRewardGroupShouldNotBeFound("groupId.equals=" + UPDATED_GROUP_ID);
    }

    @Test
    @Transactional
    public void getAllMQuestRewardGroupsByGroupIdIsInShouldWork() throws Exception {
        // Initialize the database
        mQuestRewardGroupRepository.saveAndFlush(mQuestRewardGroup);

        // Get all the mQuestRewardGroupList where groupId in DEFAULT_GROUP_ID or UPDATED_GROUP_ID
        defaultMQuestRewardGroupShouldBeFound("groupId.in=" + DEFAULT_GROUP_ID + "," + UPDATED_GROUP_ID);

        // Get all the mQuestRewardGroupList where groupId equals to UPDATED_GROUP_ID
        defaultMQuestRewardGroupShouldNotBeFound("groupId.in=" + UPDATED_GROUP_ID);
    }

    @Test
    @Transactional
    public void getAllMQuestRewardGroupsByGroupIdIsNullOrNotNull() throws Exception {
        // Initialize the database
        mQuestRewardGroupRepository.saveAndFlush(mQuestRewardGroup);

        // Get all the mQuestRewardGroupList where groupId is not null
        defaultMQuestRewardGroupShouldBeFound("groupId.specified=true");

        // Get all the mQuestRewardGroupList where groupId is null
        defaultMQuestRewardGroupShouldNotBeFound("groupId.specified=false");
    }

    @Test
    @Transactional
    public void getAllMQuestRewardGroupsByGroupIdIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mQuestRewardGroupRepository.saveAndFlush(mQuestRewardGroup);

        // Get all the mQuestRewardGroupList where groupId greater than or equals to DEFAULT_GROUP_ID
        defaultMQuestRewardGroupShouldBeFound("groupId.greaterOrEqualThan=" + DEFAULT_GROUP_ID);

        // Get all the mQuestRewardGroupList where groupId greater than or equals to UPDATED_GROUP_ID
        defaultMQuestRewardGroupShouldNotBeFound("groupId.greaterOrEqualThan=" + UPDATED_GROUP_ID);
    }

    @Test
    @Transactional
    public void getAllMQuestRewardGroupsByGroupIdIsLessThanSomething() throws Exception {
        // Initialize the database
        mQuestRewardGroupRepository.saveAndFlush(mQuestRewardGroup);

        // Get all the mQuestRewardGroupList where groupId less than or equals to DEFAULT_GROUP_ID
        defaultMQuestRewardGroupShouldNotBeFound("groupId.lessThan=" + DEFAULT_GROUP_ID);

        // Get all the mQuestRewardGroupList where groupId less than or equals to UPDATED_GROUP_ID
        defaultMQuestRewardGroupShouldBeFound("groupId.lessThan=" + UPDATED_GROUP_ID);
    }


    @Test
    @Transactional
    public void getAllMQuestRewardGroupsByRateIsEqualToSomething() throws Exception {
        // Initialize the database
        mQuestRewardGroupRepository.saveAndFlush(mQuestRewardGroup);

        // Get all the mQuestRewardGroupList where rate equals to DEFAULT_RATE
        defaultMQuestRewardGroupShouldBeFound("rate.equals=" + DEFAULT_RATE);

        // Get all the mQuestRewardGroupList where rate equals to UPDATED_RATE
        defaultMQuestRewardGroupShouldNotBeFound("rate.equals=" + UPDATED_RATE);
    }

    @Test
    @Transactional
    public void getAllMQuestRewardGroupsByRateIsInShouldWork() throws Exception {
        // Initialize the database
        mQuestRewardGroupRepository.saveAndFlush(mQuestRewardGroup);

        // Get all the mQuestRewardGroupList where rate in DEFAULT_RATE or UPDATED_RATE
        defaultMQuestRewardGroupShouldBeFound("rate.in=" + DEFAULT_RATE + "," + UPDATED_RATE);

        // Get all the mQuestRewardGroupList where rate equals to UPDATED_RATE
        defaultMQuestRewardGroupShouldNotBeFound("rate.in=" + UPDATED_RATE);
    }

    @Test
    @Transactional
    public void getAllMQuestRewardGroupsByRateIsNullOrNotNull() throws Exception {
        // Initialize the database
        mQuestRewardGroupRepository.saveAndFlush(mQuestRewardGroup);

        // Get all the mQuestRewardGroupList where rate is not null
        defaultMQuestRewardGroupShouldBeFound("rate.specified=true");

        // Get all the mQuestRewardGroupList where rate is null
        defaultMQuestRewardGroupShouldNotBeFound("rate.specified=false");
    }

    @Test
    @Transactional
    public void getAllMQuestRewardGroupsByRateIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mQuestRewardGroupRepository.saveAndFlush(mQuestRewardGroup);

        // Get all the mQuestRewardGroupList where rate greater than or equals to DEFAULT_RATE
        defaultMQuestRewardGroupShouldBeFound("rate.greaterOrEqualThan=" + DEFAULT_RATE);

        // Get all the mQuestRewardGroupList where rate greater than or equals to UPDATED_RATE
        defaultMQuestRewardGroupShouldNotBeFound("rate.greaterOrEqualThan=" + UPDATED_RATE);
    }

    @Test
    @Transactional
    public void getAllMQuestRewardGroupsByRateIsLessThanSomething() throws Exception {
        // Initialize the database
        mQuestRewardGroupRepository.saveAndFlush(mQuestRewardGroup);

        // Get all the mQuestRewardGroupList where rate less than or equals to DEFAULT_RATE
        defaultMQuestRewardGroupShouldNotBeFound("rate.lessThan=" + DEFAULT_RATE);

        // Get all the mQuestRewardGroupList where rate less than or equals to UPDATED_RATE
        defaultMQuestRewardGroupShouldBeFound("rate.lessThan=" + UPDATED_RATE);
    }


    @Test
    @Transactional
    public void getAllMQuestRewardGroupsByRankIsEqualToSomething() throws Exception {
        // Initialize the database
        mQuestRewardGroupRepository.saveAndFlush(mQuestRewardGroup);

        // Get all the mQuestRewardGroupList where rank equals to DEFAULT_RANK
        defaultMQuestRewardGroupShouldBeFound("rank.equals=" + DEFAULT_RANK);

        // Get all the mQuestRewardGroupList where rank equals to UPDATED_RANK
        defaultMQuestRewardGroupShouldNotBeFound("rank.equals=" + UPDATED_RANK);
    }

    @Test
    @Transactional
    public void getAllMQuestRewardGroupsByRankIsInShouldWork() throws Exception {
        // Initialize the database
        mQuestRewardGroupRepository.saveAndFlush(mQuestRewardGroup);

        // Get all the mQuestRewardGroupList where rank in DEFAULT_RANK or UPDATED_RANK
        defaultMQuestRewardGroupShouldBeFound("rank.in=" + DEFAULT_RANK + "," + UPDATED_RANK);

        // Get all the mQuestRewardGroupList where rank equals to UPDATED_RANK
        defaultMQuestRewardGroupShouldNotBeFound("rank.in=" + UPDATED_RANK);
    }

    @Test
    @Transactional
    public void getAllMQuestRewardGroupsByRankIsNullOrNotNull() throws Exception {
        // Initialize the database
        mQuestRewardGroupRepository.saveAndFlush(mQuestRewardGroup);

        // Get all the mQuestRewardGroupList where rank is not null
        defaultMQuestRewardGroupShouldBeFound("rank.specified=true");

        // Get all the mQuestRewardGroupList where rank is null
        defaultMQuestRewardGroupShouldNotBeFound("rank.specified=false");
    }

    @Test
    @Transactional
    public void getAllMQuestRewardGroupsByRankIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mQuestRewardGroupRepository.saveAndFlush(mQuestRewardGroup);

        // Get all the mQuestRewardGroupList where rank greater than or equals to DEFAULT_RANK
        defaultMQuestRewardGroupShouldBeFound("rank.greaterOrEqualThan=" + DEFAULT_RANK);

        // Get all the mQuestRewardGroupList where rank greater than or equals to UPDATED_RANK
        defaultMQuestRewardGroupShouldNotBeFound("rank.greaterOrEqualThan=" + UPDATED_RANK);
    }

    @Test
    @Transactional
    public void getAllMQuestRewardGroupsByRankIsLessThanSomething() throws Exception {
        // Initialize the database
        mQuestRewardGroupRepository.saveAndFlush(mQuestRewardGroup);

        // Get all the mQuestRewardGroupList where rank less than or equals to DEFAULT_RANK
        defaultMQuestRewardGroupShouldNotBeFound("rank.lessThan=" + DEFAULT_RANK);

        // Get all the mQuestRewardGroupList where rank less than or equals to UPDATED_RANK
        defaultMQuestRewardGroupShouldBeFound("rank.lessThan=" + UPDATED_RANK);
    }


    @Test
    @Transactional
    public void getAllMQuestRewardGroupsByContentTypeIsEqualToSomething() throws Exception {
        // Initialize the database
        mQuestRewardGroupRepository.saveAndFlush(mQuestRewardGroup);

        // Get all the mQuestRewardGroupList where contentType equals to DEFAULT_CONTENT_TYPE
        defaultMQuestRewardGroupShouldBeFound("contentType.equals=" + DEFAULT_CONTENT_TYPE);

        // Get all the mQuestRewardGroupList where contentType equals to UPDATED_CONTENT_TYPE
        defaultMQuestRewardGroupShouldNotBeFound("contentType.equals=" + UPDATED_CONTENT_TYPE);
    }

    @Test
    @Transactional
    public void getAllMQuestRewardGroupsByContentTypeIsInShouldWork() throws Exception {
        // Initialize the database
        mQuestRewardGroupRepository.saveAndFlush(mQuestRewardGroup);

        // Get all the mQuestRewardGroupList where contentType in DEFAULT_CONTENT_TYPE or UPDATED_CONTENT_TYPE
        defaultMQuestRewardGroupShouldBeFound("contentType.in=" + DEFAULT_CONTENT_TYPE + "," + UPDATED_CONTENT_TYPE);

        // Get all the mQuestRewardGroupList where contentType equals to UPDATED_CONTENT_TYPE
        defaultMQuestRewardGroupShouldNotBeFound("contentType.in=" + UPDATED_CONTENT_TYPE);
    }

    @Test
    @Transactional
    public void getAllMQuestRewardGroupsByContentTypeIsNullOrNotNull() throws Exception {
        // Initialize the database
        mQuestRewardGroupRepository.saveAndFlush(mQuestRewardGroup);

        // Get all the mQuestRewardGroupList where contentType is not null
        defaultMQuestRewardGroupShouldBeFound("contentType.specified=true");

        // Get all the mQuestRewardGroupList where contentType is null
        defaultMQuestRewardGroupShouldNotBeFound("contentType.specified=false");
    }

    @Test
    @Transactional
    public void getAllMQuestRewardGroupsByContentTypeIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mQuestRewardGroupRepository.saveAndFlush(mQuestRewardGroup);

        // Get all the mQuestRewardGroupList where contentType greater than or equals to DEFAULT_CONTENT_TYPE
        defaultMQuestRewardGroupShouldBeFound("contentType.greaterOrEqualThan=" + DEFAULT_CONTENT_TYPE);

        // Get all the mQuestRewardGroupList where contentType greater than or equals to UPDATED_CONTENT_TYPE
        defaultMQuestRewardGroupShouldNotBeFound("contentType.greaterOrEqualThan=" + UPDATED_CONTENT_TYPE);
    }

    @Test
    @Transactional
    public void getAllMQuestRewardGroupsByContentTypeIsLessThanSomething() throws Exception {
        // Initialize the database
        mQuestRewardGroupRepository.saveAndFlush(mQuestRewardGroup);

        // Get all the mQuestRewardGroupList where contentType less than or equals to DEFAULT_CONTENT_TYPE
        defaultMQuestRewardGroupShouldNotBeFound("contentType.lessThan=" + DEFAULT_CONTENT_TYPE);

        // Get all the mQuestRewardGroupList where contentType less than or equals to UPDATED_CONTENT_TYPE
        defaultMQuestRewardGroupShouldBeFound("contentType.lessThan=" + UPDATED_CONTENT_TYPE);
    }


    @Test
    @Transactional
    public void getAllMQuestRewardGroupsByContentIdIsEqualToSomething() throws Exception {
        // Initialize the database
        mQuestRewardGroupRepository.saveAndFlush(mQuestRewardGroup);

        // Get all the mQuestRewardGroupList where contentId equals to DEFAULT_CONTENT_ID
        defaultMQuestRewardGroupShouldBeFound("contentId.equals=" + DEFAULT_CONTENT_ID);

        // Get all the mQuestRewardGroupList where contentId equals to UPDATED_CONTENT_ID
        defaultMQuestRewardGroupShouldNotBeFound("contentId.equals=" + UPDATED_CONTENT_ID);
    }

    @Test
    @Transactional
    public void getAllMQuestRewardGroupsByContentIdIsInShouldWork() throws Exception {
        // Initialize the database
        mQuestRewardGroupRepository.saveAndFlush(mQuestRewardGroup);

        // Get all the mQuestRewardGroupList where contentId in DEFAULT_CONTENT_ID or UPDATED_CONTENT_ID
        defaultMQuestRewardGroupShouldBeFound("contentId.in=" + DEFAULT_CONTENT_ID + "," + UPDATED_CONTENT_ID);

        // Get all the mQuestRewardGroupList where contentId equals to UPDATED_CONTENT_ID
        defaultMQuestRewardGroupShouldNotBeFound("contentId.in=" + UPDATED_CONTENT_ID);
    }

    @Test
    @Transactional
    public void getAllMQuestRewardGroupsByContentIdIsNullOrNotNull() throws Exception {
        // Initialize the database
        mQuestRewardGroupRepository.saveAndFlush(mQuestRewardGroup);

        // Get all the mQuestRewardGroupList where contentId is not null
        defaultMQuestRewardGroupShouldBeFound("contentId.specified=true");

        // Get all the mQuestRewardGroupList where contentId is null
        defaultMQuestRewardGroupShouldNotBeFound("contentId.specified=false");
    }

    @Test
    @Transactional
    public void getAllMQuestRewardGroupsByContentIdIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mQuestRewardGroupRepository.saveAndFlush(mQuestRewardGroup);

        // Get all the mQuestRewardGroupList where contentId greater than or equals to DEFAULT_CONTENT_ID
        defaultMQuestRewardGroupShouldBeFound("contentId.greaterOrEqualThan=" + DEFAULT_CONTENT_ID);

        // Get all the mQuestRewardGroupList where contentId greater than or equals to UPDATED_CONTENT_ID
        defaultMQuestRewardGroupShouldNotBeFound("contentId.greaterOrEqualThan=" + UPDATED_CONTENT_ID);
    }

    @Test
    @Transactional
    public void getAllMQuestRewardGroupsByContentIdIsLessThanSomething() throws Exception {
        // Initialize the database
        mQuestRewardGroupRepository.saveAndFlush(mQuestRewardGroup);

        // Get all the mQuestRewardGroupList where contentId less than or equals to DEFAULT_CONTENT_ID
        defaultMQuestRewardGroupShouldNotBeFound("contentId.lessThan=" + DEFAULT_CONTENT_ID);

        // Get all the mQuestRewardGroupList where contentId less than or equals to UPDATED_CONTENT_ID
        defaultMQuestRewardGroupShouldBeFound("contentId.lessThan=" + UPDATED_CONTENT_ID);
    }


    @Test
    @Transactional
    public void getAllMQuestRewardGroupsByContentAmountIsEqualToSomething() throws Exception {
        // Initialize the database
        mQuestRewardGroupRepository.saveAndFlush(mQuestRewardGroup);

        // Get all the mQuestRewardGroupList where contentAmount equals to DEFAULT_CONTENT_AMOUNT
        defaultMQuestRewardGroupShouldBeFound("contentAmount.equals=" + DEFAULT_CONTENT_AMOUNT);

        // Get all the mQuestRewardGroupList where contentAmount equals to UPDATED_CONTENT_AMOUNT
        defaultMQuestRewardGroupShouldNotBeFound("contentAmount.equals=" + UPDATED_CONTENT_AMOUNT);
    }

    @Test
    @Transactional
    public void getAllMQuestRewardGroupsByContentAmountIsInShouldWork() throws Exception {
        // Initialize the database
        mQuestRewardGroupRepository.saveAndFlush(mQuestRewardGroup);

        // Get all the mQuestRewardGroupList where contentAmount in DEFAULT_CONTENT_AMOUNT or UPDATED_CONTENT_AMOUNT
        defaultMQuestRewardGroupShouldBeFound("contentAmount.in=" + DEFAULT_CONTENT_AMOUNT + "," + UPDATED_CONTENT_AMOUNT);

        // Get all the mQuestRewardGroupList where contentAmount equals to UPDATED_CONTENT_AMOUNT
        defaultMQuestRewardGroupShouldNotBeFound("contentAmount.in=" + UPDATED_CONTENT_AMOUNT);
    }

    @Test
    @Transactional
    public void getAllMQuestRewardGroupsByContentAmountIsNullOrNotNull() throws Exception {
        // Initialize the database
        mQuestRewardGroupRepository.saveAndFlush(mQuestRewardGroup);

        // Get all the mQuestRewardGroupList where contentAmount is not null
        defaultMQuestRewardGroupShouldBeFound("contentAmount.specified=true");

        // Get all the mQuestRewardGroupList where contentAmount is null
        defaultMQuestRewardGroupShouldNotBeFound("contentAmount.specified=false");
    }

    @Test
    @Transactional
    public void getAllMQuestRewardGroupsByContentAmountIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mQuestRewardGroupRepository.saveAndFlush(mQuestRewardGroup);

        // Get all the mQuestRewardGroupList where contentAmount greater than or equals to DEFAULT_CONTENT_AMOUNT
        defaultMQuestRewardGroupShouldBeFound("contentAmount.greaterOrEqualThan=" + DEFAULT_CONTENT_AMOUNT);

        // Get all the mQuestRewardGroupList where contentAmount greater than or equals to UPDATED_CONTENT_AMOUNT
        defaultMQuestRewardGroupShouldNotBeFound("contentAmount.greaterOrEqualThan=" + UPDATED_CONTENT_AMOUNT);
    }

    @Test
    @Transactional
    public void getAllMQuestRewardGroupsByContentAmountIsLessThanSomething() throws Exception {
        // Initialize the database
        mQuestRewardGroupRepository.saveAndFlush(mQuestRewardGroup);

        // Get all the mQuestRewardGroupList where contentAmount less than or equals to DEFAULT_CONTENT_AMOUNT
        defaultMQuestRewardGroupShouldNotBeFound("contentAmount.lessThan=" + DEFAULT_CONTENT_AMOUNT);

        // Get all the mQuestRewardGroupList where contentAmount less than or equals to UPDATED_CONTENT_AMOUNT
        defaultMQuestRewardGroupShouldBeFound("contentAmount.lessThan=" + UPDATED_CONTENT_AMOUNT);
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultMQuestRewardGroupShouldBeFound(String filter) throws Exception {
        restMQuestRewardGroupMockMvc.perform(get("/api/m-quest-reward-groups?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(mQuestRewardGroup.getId().intValue())))
            .andExpect(jsonPath("$.[*].groupId").value(hasItem(DEFAULT_GROUP_ID)))
            .andExpect(jsonPath("$.[*].rate").value(hasItem(DEFAULT_RATE)))
            .andExpect(jsonPath("$.[*].rank").value(hasItem(DEFAULT_RANK)))
            .andExpect(jsonPath("$.[*].contentType").value(hasItem(DEFAULT_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].contentId").value(hasItem(DEFAULT_CONTENT_ID)))
            .andExpect(jsonPath("$.[*].contentAmount").value(hasItem(DEFAULT_CONTENT_AMOUNT)));

        // Check, that the count call also returns 1
        restMQuestRewardGroupMockMvc.perform(get("/api/m-quest-reward-groups/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultMQuestRewardGroupShouldNotBeFound(String filter) throws Exception {
        restMQuestRewardGroupMockMvc.perform(get("/api/m-quest-reward-groups?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restMQuestRewardGroupMockMvc.perform(get("/api/m-quest-reward-groups/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("0"));
    }


    @Test
    @Transactional
    public void getNonExistingMQuestRewardGroup() throws Exception {
        // Get the mQuestRewardGroup
        restMQuestRewardGroupMockMvc.perform(get("/api/m-quest-reward-groups/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateMQuestRewardGroup() throws Exception {
        // Initialize the database
        mQuestRewardGroupRepository.saveAndFlush(mQuestRewardGroup);

        int databaseSizeBeforeUpdate = mQuestRewardGroupRepository.findAll().size();

        // Update the mQuestRewardGroup
        MQuestRewardGroup updatedMQuestRewardGroup = mQuestRewardGroupRepository.findById(mQuestRewardGroup.getId()).get();
        // Disconnect from session so that the updates on updatedMQuestRewardGroup are not directly saved in db
        em.detach(updatedMQuestRewardGroup);
        updatedMQuestRewardGroup
            .groupId(UPDATED_GROUP_ID)
            .rate(UPDATED_RATE)
            .rank(UPDATED_RANK)
            .contentType(UPDATED_CONTENT_TYPE)
            .contentId(UPDATED_CONTENT_ID)
            .contentAmount(UPDATED_CONTENT_AMOUNT);
        MQuestRewardGroupDTO mQuestRewardGroupDTO = mQuestRewardGroupMapper.toDto(updatedMQuestRewardGroup);

        restMQuestRewardGroupMockMvc.perform(put("/api/m-quest-reward-groups")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mQuestRewardGroupDTO)))
            .andExpect(status().isOk());

        // Validate the MQuestRewardGroup in the database
        List<MQuestRewardGroup> mQuestRewardGroupList = mQuestRewardGroupRepository.findAll();
        assertThat(mQuestRewardGroupList).hasSize(databaseSizeBeforeUpdate);
        MQuestRewardGroup testMQuestRewardGroup = mQuestRewardGroupList.get(mQuestRewardGroupList.size() - 1);
        assertThat(testMQuestRewardGroup.getGroupId()).isEqualTo(UPDATED_GROUP_ID);
        assertThat(testMQuestRewardGroup.getRate()).isEqualTo(UPDATED_RATE);
        assertThat(testMQuestRewardGroup.getRank()).isEqualTo(UPDATED_RANK);
        assertThat(testMQuestRewardGroup.getContentType()).isEqualTo(UPDATED_CONTENT_TYPE);
        assertThat(testMQuestRewardGroup.getContentId()).isEqualTo(UPDATED_CONTENT_ID);
        assertThat(testMQuestRewardGroup.getContentAmount()).isEqualTo(UPDATED_CONTENT_AMOUNT);
    }

    @Test
    @Transactional
    public void updateNonExistingMQuestRewardGroup() throws Exception {
        int databaseSizeBeforeUpdate = mQuestRewardGroupRepository.findAll().size();

        // Create the MQuestRewardGroup
        MQuestRewardGroupDTO mQuestRewardGroupDTO = mQuestRewardGroupMapper.toDto(mQuestRewardGroup);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restMQuestRewardGroupMockMvc.perform(put("/api/m-quest-reward-groups")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mQuestRewardGroupDTO)))
            .andExpect(status().isBadRequest());

        // Validate the MQuestRewardGroup in the database
        List<MQuestRewardGroup> mQuestRewardGroupList = mQuestRewardGroupRepository.findAll();
        assertThat(mQuestRewardGroupList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteMQuestRewardGroup() throws Exception {
        // Initialize the database
        mQuestRewardGroupRepository.saveAndFlush(mQuestRewardGroup);

        int databaseSizeBeforeDelete = mQuestRewardGroupRepository.findAll().size();

        // Delete the mQuestRewardGroup
        restMQuestRewardGroupMockMvc.perform(delete("/api/m-quest-reward-groups/{id}", mQuestRewardGroup.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database is empty
        List<MQuestRewardGroup> mQuestRewardGroupList = mQuestRewardGroupRepository.findAll();
        assertThat(mQuestRewardGroupList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(MQuestRewardGroup.class);
        MQuestRewardGroup mQuestRewardGroup1 = new MQuestRewardGroup();
        mQuestRewardGroup1.setId(1L);
        MQuestRewardGroup mQuestRewardGroup2 = new MQuestRewardGroup();
        mQuestRewardGroup2.setId(mQuestRewardGroup1.getId());
        assertThat(mQuestRewardGroup1).isEqualTo(mQuestRewardGroup2);
        mQuestRewardGroup2.setId(2L);
        assertThat(mQuestRewardGroup1).isNotEqualTo(mQuestRewardGroup2);
        mQuestRewardGroup1.setId(null);
        assertThat(mQuestRewardGroup1).isNotEqualTo(mQuestRewardGroup2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(MQuestRewardGroupDTO.class);
        MQuestRewardGroupDTO mQuestRewardGroupDTO1 = new MQuestRewardGroupDTO();
        mQuestRewardGroupDTO1.setId(1L);
        MQuestRewardGroupDTO mQuestRewardGroupDTO2 = new MQuestRewardGroupDTO();
        assertThat(mQuestRewardGroupDTO1).isNotEqualTo(mQuestRewardGroupDTO2);
        mQuestRewardGroupDTO2.setId(mQuestRewardGroupDTO1.getId());
        assertThat(mQuestRewardGroupDTO1).isEqualTo(mQuestRewardGroupDTO2);
        mQuestRewardGroupDTO2.setId(2L);
        assertThat(mQuestRewardGroupDTO1).isNotEqualTo(mQuestRewardGroupDTO2);
        mQuestRewardGroupDTO1.setId(null);
        assertThat(mQuestRewardGroupDTO1).isNotEqualTo(mQuestRewardGroupDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(mQuestRewardGroupMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(mQuestRewardGroupMapper.fromId(null)).isNull();
    }
}
