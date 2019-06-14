package io.shm.tsubasa.web.rest;

import io.shm.tsubasa.TsubasaApp;
import io.shm.tsubasa.domain.MPvpRankingRewardGroup;
import io.shm.tsubasa.repository.MPvpRankingRewardGroupRepository;
import io.shm.tsubasa.service.MPvpRankingRewardGroupService;
import io.shm.tsubasa.service.dto.MPvpRankingRewardGroupDTO;
import io.shm.tsubasa.service.mapper.MPvpRankingRewardGroupMapper;
import io.shm.tsubasa.web.rest.errors.ExceptionTranslator;
import io.shm.tsubasa.service.dto.MPvpRankingRewardGroupCriteria;
import io.shm.tsubasa.service.MPvpRankingRewardGroupQueryService;

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
 * Integration tests for the {@Link MPvpRankingRewardGroupResource} REST controller.
 */
@SpringBootTest(classes = TsubasaApp.class)
public class MPvpRankingRewardGroupResourceIT {

    private static final Integer DEFAULT_GROUP_ID = 1;
    private static final Integer UPDATED_GROUP_ID = 2;

    private static final Integer DEFAULT_CONTENT_TYPE = 1;
    private static final Integer UPDATED_CONTENT_TYPE = 2;

    private static final Integer DEFAULT_CONTENT_ID = 1;
    private static final Integer UPDATED_CONTENT_ID = 2;

    private static final Integer DEFAULT_CONTENT_AMOUNT = 1;
    private static final Integer UPDATED_CONTENT_AMOUNT = 2;

    @Autowired
    private MPvpRankingRewardGroupRepository mPvpRankingRewardGroupRepository;

    @Autowired
    private MPvpRankingRewardGroupMapper mPvpRankingRewardGroupMapper;

    @Autowired
    private MPvpRankingRewardGroupService mPvpRankingRewardGroupService;

    @Autowired
    private MPvpRankingRewardGroupQueryService mPvpRankingRewardGroupQueryService;

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

    private MockMvc restMPvpRankingRewardGroupMockMvc;

    private MPvpRankingRewardGroup mPvpRankingRewardGroup;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final MPvpRankingRewardGroupResource mPvpRankingRewardGroupResource = new MPvpRankingRewardGroupResource(mPvpRankingRewardGroupService, mPvpRankingRewardGroupQueryService);
        this.restMPvpRankingRewardGroupMockMvc = MockMvcBuilders.standaloneSetup(mPvpRankingRewardGroupResource)
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
    public static MPvpRankingRewardGroup createEntity(EntityManager em) {
        MPvpRankingRewardGroup mPvpRankingRewardGroup = new MPvpRankingRewardGroup()
            .groupId(DEFAULT_GROUP_ID)
            .contentType(DEFAULT_CONTENT_TYPE)
            .contentId(DEFAULT_CONTENT_ID)
            .contentAmount(DEFAULT_CONTENT_AMOUNT);
        return mPvpRankingRewardGroup;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static MPvpRankingRewardGroup createUpdatedEntity(EntityManager em) {
        MPvpRankingRewardGroup mPvpRankingRewardGroup = new MPvpRankingRewardGroup()
            .groupId(UPDATED_GROUP_ID)
            .contentType(UPDATED_CONTENT_TYPE)
            .contentId(UPDATED_CONTENT_ID)
            .contentAmount(UPDATED_CONTENT_AMOUNT);
        return mPvpRankingRewardGroup;
    }

    @BeforeEach
    public void initTest() {
        mPvpRankingRewardGroup = createEntity(em);
    }

    @Test
    @Transactional
    public void createMPvpRankingRewardGroup() throws Exception {
        int databaseSizeBeforeCreate = mPvpRankingRewardGroupRepository.findAll().size();

        // Create the MPvpRankingRewardGroup
        MPvpRankingRewardGroupDTO mPvpRankingRewardGroupDTO = mPvpRankingRewardGroupMapper.toDto(mPvpRankingRewardGroup);
        restMPvpRankingRewardGroupMockMvc.perform(post("/api/m-pvp-ranking-reward-groups")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mPvpRankingRewardGroupDTO)))
            .andExpect(status().isCreated());

        // Validate the MPvpRankingRewardGroup in the database
        List<MPvpRankingRewardGroup> mPvpRankingRewardGroupList = mPvpRankingRewardGroupRepository.findAll();
        assertThat(mPvpRankingRewardGroupList).hasSize(databaseSizeBeforeCreate + 1);
        MPvpRankingRewardGroup testMPvpRankingRewardGroup = mPvpRankingRewardGroupList.get(mPvpRankingRewardGroupList.size() - 1);
        assertThat(testMPvpRankingRewardGroup.getGroupId()).isEqualTo(DEFAULT_GROUP_ID);
        assertThat(testMPvpRankingRewardGroup.getContentType()).isEqualTo(DEFAULT_CONTENT_TYPE);
        assertThat(testMPvpRankingRewardGroup.getContentId()).isEqualTo(DEFAULT_CONTENT_ID);
        assertThat(testMPvpRankingRewardGroup.getContentAmount()).isEqualTo(DEFAULT_CONTENT_AMOUNT);
    }

    @Test
    @Transactional
    public void createMPvpRankingRewardGroupWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = mPvpRankingRewardGroupRepository.findAll().size();

        // Create the MPvpRankingRewardGroup with an existing ID
        mPvpRankingRewardGroup.setId(1L);
        MPvpRankingRewardGroupDTO mPvpRankingRewardGroupDTO = mPvpRankingRewardGroupMapper.toDto(mPvpRankingRewardGroup);

        // An entity with an existing ID cannot be created, so this API call must fail
        restMPvpRankingRewardGroupMockMvc.perform(post("/api/m-pvp-ranking-reward-groups")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mPvpRankingRewardGroupDTO)))
            .andExpect(status().isBadRequest());

        // Validate the MPvpRankingRewardGroup in the database
        List<MPvpRankingRewardGroup> mPvpRankingRewardGroupList = mPvpRankingRewardGroupRepository.findAll();
        assertThat(mPvpRankingRewardGroupList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkGroupIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = mPvpRankingRewardGroupRepository.findAll().size();
        // set the field null
        mPvpRankingRewardGroup.setGroupId(null);

        // Create the MPvpRankingRewardGroup, which fails.
        MPvpRankingRewardGroupDTO mPvpRankingRewardGroupDTO = mPvpRankingRewardGroupMapper.toDto(mPvpRankingRewardGroup);

        restMPvpRankingRewardGroupMockMvc.perform(post("/api/m-pvp-ranking-reward-groups")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mPvpRankingRewardGroupDTO)))
            .andExpect(status().isBadRequest());

        List<MPvpRankingRewardGroup> mPvpRankingRewardGroupList = mPvpRankingRewardGroupRepository.findAll();
        assertThat(mPvpRankingRewardGroupList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkContentTypeIsRequired() throws Exception {
        int databaseSizeBeforeTest = mPvpRankingRewardGroupRepository.findAll().size();
        // set the field null
        mPvpRankingRewardGroup.setContentType(null);

        // Create the MPvpRankingRewardGroup, which fails.
        MPvpRankingRewardGroupDTO mPvpRankingRewardGroupDTO = mPvpRankingRewardGroupMapper.toDto(mPvpRankingRewardGroup);

        restMPvpRankingRewardGroupMockMvc.perform(post("/api/m-pvp-ranking-reward-groups")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mPvpRankingRewardGroupDTO)))
            .andExpect(status().isBadRequest());

        List<MPvpRankingRewardGroup> mPvpRankingRewardGroupList = mPvpRankingRewardGroupRepository.findAll();
        assertThat(mPvpRankingRewardGroupList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkContentAmountIsRequired() throws Exception {
        int databaseSizeBeforeTest = mPvpRankingRewardGroupRepository.findAll().size();
        // set the field null
        mPvpRankingRewardGroup.setContentAmount(null);

        // Create the MPvpRankingRewardGroup, which fails.
        MPvpRankingRewardGroupDTO mPvpRankingRewardGroupDTO = mPvpRankingRewardGroupMapper.toDto(mPvpRankingRewardGroup);

        restMPvpRankingRewardGroupMockMvc.perform(post("/api/m-pvp-ranking-reward-groups")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mPvpRankingRewardGroupDTO)))
            .andExpect(status().isBadRequest());

        List<MPvpRankingRewardGroup> mPvpRankingRewardGroupList = mPvpRankingRewardGroupRepository.findAll();
        assertThat(mPvpRankingRewardGroupList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllMPvpRankingRewardGroups() throws Exception {
        // Initialize the database
        mPvpRankingRewardGroupRepository.saveAndFlush(mPvpRankingRewardGroup);

        // Get all the mPvpRankingRewardGroupList
        restMPvpRankingRewardGroupMockMvc.perform(get("/api/m-pvp-ranking-reward-groups?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(mPvpRankingRewardGroup.getId().intValue())))
            .andExpect(jsonPath("$.[*].groupId").value(hasItem(DEFAULT_GROUP_ID)))
            .andExpect(jsonPath("$.[*].contentType").value(hasItem(DEFAULT_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].contentId").value(hasItem(DEFAULT_CONTENT_ID)))
            .andExpect(jsonPath("$.[*].contentAmount").value(hasItem(DEFAULT_CONTENT_AMOUNT)));
    }
    
    @Test
    @Transactional
    public void getMPvpRankingRewardGroup() throws Exception {
        // Initialize the database
        mPvpRankingRewardGroupRepository.saveAndFlush(mPvpRankingRewardGroup);

        // Get the mPvpRankingRewardGroup
        restMPvpRankingRewardGroupMockMvc.perform(get("/api/m-pvp-ranking-reward-groups/{id}", mPvpRankingRewardGroup.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(mPvpRankingRewardGroup.getId().intValue()))
            .andExpect(jsonPath("$.groupId").value(DEFAULT_GROUP_ID))
            .andExpect(jsonPath("$.contentType").value(DEFAULT_CONTENT_TYPE))
            .andExpect(jsonPath("$.contentId").value(DEFAULT_CONTENT_ID))
            .andExpect(jsonPath("$.contentAmount").value(DEFAULT_CONTENT_AMOUNT));
    }

    @Test
    @Transactional
    public void getAllMPvpRankingRewardGroupsByGroupIdIsEqualToSomething() throws Exception {
        // Initialize the database
        mPvpRankingRewardGroupRepository.saveAndFlush(mPvpRankingRewardGroup);

        // Get all the mPvpRankingRewardGroupList where groupId equals to DEFAULT_GROUP_ID
        defaultMPvpRankingRewardGroupShouldBeFound("groupId.equals=" + DEFAULT_GROUP_ID);

        // Get all the mPvpRankingRewardGroupList where groupId equals to UPDATED_GROUP_ID
        defaultMPvpRankingRewardGroupShouldNotBeFound("groupId.equals=" + UPDATED_GROUP_ID);
    }

    @Test
    @Transactional
    public void getAllMPvpRankingRewardGroupsByGroupIdIsInShouldWork() throws Exception {
        // Initialize the database
        mPvpRankingRewardGroupRepository.saveAndFlush(mPvpRankingRewardGroup);

        // Get all the mPvpRankingRewardGroupList where groupId in DEFAULT_GROUP_ID or UPDATED_GROUP_ID
        defaultMPvpRankingRewardGroupShouldBeFound("groupId.in=" + DEFAULT_GROUP_ID + "," + UPDATED_GROUP_ID);

        // Get all the mPvpRankingRewardGroupList where groupId equals to UPDATED_GROUP_ID
        defaultMPvpRankingRewardGroupShouldNotBeFound("groupId.in=" + UPDATED_GROUP_ID);
    }

    @Test
    @Transactional
    public void getAllMPvpRankingRewardGroupsByGroupIdIsNullOrNotNull() throws Exception {
        // Initialize the database
        mPvpRankingRewardGroupRepository.saveAndFlush(mPvpRankingRewardGroup);

        // Get all the mPvpRankingRewardGroupList where groupId is not null
        defaultMPvpRankingRewardGroupShouldBeFound("groupId.specified=true");

        // Get all the mPvpRankingRewardGroupList where groupId is null
        defaultMPvpRankingRewardGroupShouldNotBeFound("groupId.specified=false");
    }

    @Test
    @Transactional
    public void getAllMPvpRankingRewardGroupsByGroupIdIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mPvpRankingRewardGroupRepository.saveAndFlush(mPvpRankingRewardGroup);

        // Get all the mPvpRankingRewardGroupList where groupId greater than or equals to DEFAULT_GROUP_ID
        defaultMPvpRankingRewardGroupShouldBeFound("groupId.greaterOrEqualThan=" + DEFAULT_GROUP_ID);

        // Get all the mPvpRankingRewardGroupList where groupId greater than or equals to UPDATED_GROUP_ID
        defaultMPvpRankingRewardGroupShouldNotBeFound("groupId.greaterOrEqualThan=" + UPDATED_GROUP_ID);
    }

    @Test
    @Transactional
    public void getAllMPvpRankingRewardGroupsByGroupIdIsLessThanSomething() throws Exception {
        // Initialize the database
        mPvpRankingRewardGroupRepository.saveAndFlush(mPvpRankingRewardGroup);

        // Get all the mPvpRankingRewardGroupList where groupId less than or equals to DEFAULT_GROUP_ID
        defaultMPvpRankingRewardGroupShouldNotBeFound("groupId.lessThan=" + DEFAULT_GROUP_ID);

        // Get all the mPvpRankingRewardGroupList where groupId less than or equals to UPDATED_GROUP_ID
        defaultMPvpRankingRewardGroupShouldBeFound("groupId.lessThan=" + UPDATED_GROUP_ID);
    }


    @Test
    @Transactional
    public void getAllMPvpRankingRewardGroupsByContentTypeIsEqualToSomething() throws Exception {
        // Initialize the database
        mPvpRankingRewardGroupRepository.saveAndFlush(mPvpRankingRewardGroup);

        // Get all the mPvpRankingRewardGroupList where contentType equals to DEFAULT_CONTENT_TYPE
        defaultMPvpRankingRewardGroupShouldBeFound("contentType.equals=" + DEFAULT_CONTENT_TYPE);

        // Get all the mPvpRankingRewardGroupList where contentType equals to UPDATED_CONTENT_TYPE
        defaultMPvpRankingRewardGroupShouldNotBeFound("contentType.equals=" + UPDATED_CONTENT_TYPE);
    }

    @Test
    @Transactional
    public void getAllMPvpRankingRewardGroupsByContentTypeIsInShouldWork() throws Exception {
        // Initialize the database
        mPvpRankingRewardGroupRepository.saveAndFlush(mPvpRankingRewardGroup);

        // Get all the mPvpRankingRewardGroupList where contentType in DEFAULT_CONTENT_TYPE or UPDATED_CONTENT_TYPE
        defaultMPvpRankingRewardGroupShouldBeFound("contentType.in=" + DEFAULT_CONTENT_TYPE + "," + UPDATED_CONTENT_TYPE);

        // Get all the mPvpRankingRewardGroupList where contentType equals to UPDATED_CONTENT_TYPE
        defaultMPvpRankingRewardGroupShouldNotBeFound("contentType.in=" + UPDATED_CONTENT_TYPE);
    }

    @Test
    @Transactional
    public void getAllMPvpRankingRewardGroupsByContentTypeIsNullOrNotNull() throws Exception {
        // Initialize the database
        mPvpRankingRewardGroupRepository.saveAndFlush(mPvpRankingRewardGroup);

        // Get all the mPvpRankingRewardGroupList where contentType is not null
        defaultMPvpRankingRewardGroupShouldBeFound("contentType.specified=true");

        // Get all the mPvpRankingRewardGroupList where contentType is null
        defaultMPvpRankingRewardGroupShouldNotBeFound("contentType.specified=false");
    }

    @Test
    @Transactional
    public void getAllMPvpRankingRewardGroupsByContentTypeIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mPvpRankingRewardGroupRepository.saveAndFlush(mPvpRankingRewardGroup);

        // Get all the mPvpRankingRewardGroupList where contentType greater than or equals to DEFAULT_CONTENT_TYPE
        defaultMPvpRankingRewardGroupShouldBeFound("contentType.greaterOrEqualThan=" + DEFAULT_CONTENT_TYPE);

        // Get all the mPvpRankingRewardGroupList where contentType greater than or equals to UPDATED_CONTENT_TYPE
        defaultMPvpRankingRewardGroupShouldNotBeFound("contentType.greaterOrEqualThan=" + UPDATED_CONTENT_TYPE);
    }

    @Test
    @Transactional
    public void getAllMPvpRankingRewardGroupsByContentTypeIsLessThanSomething() throws Exception {
        // Initialize the database
        mPvpRankingRewardGroupRepository.saveAndFlush(mPvpRankingRewardGroup);

        // Get all the mPvpRankingRewardGroupList where contentType less than or equals to DEFAULT_CONTENT_TYPE
        defaultMPvpRankingRewardGroupShouldNotBeFound("contentType.lessThan=" + DEFAULT_CONTENT_TYPE);

        // Get all the mPvpRankingRewardGroupList where contentType less than or equals to UPDATED_CONTENT_TYPE
        defaultMPvpRankingRewardGroupShouldBeFound("contentType.lessThan=" + UPDATED_CONTENT_TYPE);
    }


    @Test
    @Transactional
    public void getAllMPvpRankingRewardGroupsByContentIdIsEqualToSomething() throws Exception {
        // Initialize the database
        mPvpRankingRewardGroupRepository.saveAndFlush(mPvpRankingRewardGroup);

        // Get all the mPvpRankingRewardGroupList where contentId equals to DEFAULT_CONTENT_ID
        defaultMPvpRankingRewardGroupShouldBeFound("contentId.equals=" + DEFAULT_CONTENT_ID);

        // Get all the mPvpRankingRewardGroupList where contentId equals to UPDATED_CONTENT_ID
        defaultMPvpRankingRewardGroupShouldNotBeFound("contentId.equals=" + UPDATED_CONTENT_ID);
    }

    @Test
    @Transactional
    public void getAllMPvpRankingRewardGroupsByContentIdIsInShouldWork() throws Exception {
        // Initialize the database
        mPvpRankingRewardGroupRepository.saveAndFlush(mPvpRankingRewardGroup);

        // Get all the mPvpRankingRewardGroupList where contentId in DEFAULT_CONTENT_ID or UPDATED_CONTENT_ID
        defaultMPvpRankingRewardGroupShouldBeFound("contentId.in=" + DEFAULT_CONTENT_ID + "," + UPDATED_CONTENT_ID);

        // Get all the mPvpRankingRewardGroupList where contentId equals to UPDATED_CONTENT_ID
        defaultMPvpRankingRewardGroupShouldNotBeFound("contentId.in=" + UPDATED_CONTENT_ID);
    }

    @Test
    @Transactional
    public void getAllMPvpRankingRewardGroupsByContentIdIsNullOrNotNull() throws Exception {
        // Initialize the database
        mPvpRankingRewardGroupRepository.saveAndFlush(mPvpRankingRewardGroup);

        // Get all the mPvpRankingRewardGroupList where contentId is not null
        defaultMPvpRankingRewardGroupShouldBeFound("contentId.specified=true");

        // Get all the mPvpRankingRewardGroupList where contentId is null
        defaultMPvpRankingRewardGroupShouldNotBeFound("contentId.specified=false");
    }

    @Test
    @Transactional
    public void getAllMPvpRankingRewardGroupsByContentIdIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mPvpRankingRewardGroupRepository.saveAndFlush(mPvpRankingRewardGroup);

        // Get all the mPvpRankingRewardGroupList where contentId greater than or equals to DEFAULT_CONTENT_ID
        defaultMPvpRankingRewardGroupShouldBeFound("contentId.greaterOrEqualThan=" + DEFAULT_CONTENT_ID);

        // Get all the mPvpRankingRewardGroupList where contentId greater than or equals to UPDATED_CONTENT_ID
        defaultMPvpRankingRewardGroupShouldNotBeFound("contentId.greaterOrEqualThan=" + UPDATED_CONTENT_ID);
    }

    @Test
    @Transactional
    public void getAllMPvpRankingRewardGroupsByContentIdIsLessThanSomething() throws Exception {
        // Initialize the database
        mPvpRankingRewardGroupRepository.saveAndFlush(mPvpRankingRewardGroup);

        // Get all the mPvpRankingRewardGroupList where contentId less than or equals to DEFAULT_CONTENT_ID
        defaultMPvpRankingRewardGroupShouldNotBeFound("contentId.lessThan=" + DEFAULT_CONTENT_ID);

        // Get all the mPvpRankingRewardGroupList where contentId less than or equals to UPDATED_CONTENT_ID
        defaultMPvpRankingRewardGroupShouldBeFound("contentId.lessThan=" + UPDATED_CONTENT_ID);
    }


    @Test
    @Transactional
    public void getAllMPvpRankingRewardGroupsByContentAmountIsEqualToSomething() throws Exception {
        // Initialize the database
        mPvpRankingRewardGroupRepository.saveAndFlush(mPvpRankingRewardGroup);

        // Get all the mPvpRankingRewardGroupList where contentAmount equals to DEFAULT_CONTENT_AMOUNT
        defaultMPvpRankingRewardGroupShouldBeFound("contentAmount.equals=" + DEFAULT_CONTENT_AMOUNT);

        // Get all the mPvpRankingRewardGroupList where contentAmount equals to UPDATED_CONTENT_AMOUNT
        defaultMPvpRankingRewardGroupShouldNotBeFound("contentAmount.equals=" + UPDATED_CONTENT_AMOUNT);
    }

    @Test
    @Transactional
    public void getAllMPvpRankingRewardGroupsByContentAmountIsInShouldWork() throws Exception {
        // Initialize the database
        mPvpRankingRewardGroupRepository.saveAndFlush(mPvpRankingRewardGroup);

        // Get all the mPvpRankingRewardGroupList where contentAmount in DEFAULT_CONTENT_AMOUNT or UPDATED_CONTENT_AMOUNT
        defaultMPvpRankingRewardGroupShouldBeFound("contentAmount.in=" + DEFAULT_CONTENT_AMOUNT + "," + UPDATED_CONTENT_AMOUNT);

        // Get all the mPvpRankingRewardGroupList where contentAmount equals to UPDATED_CONTENT_AMOUNT
        defaultMPvpRankingRewardGroupShouldNotBeFound("contentAmount.in=" + UPDATED_CONTENT_AMOUNT);
    }

    @Test
    @Transactional
    public void getAllMPvpRankingRewardGroupsByContentAmountIsNullOrNotNull() throws Exception {
        // Initialize the database
        mPvpRankingRewardGroupRepository.saveAndFlush(mPvpRankingRewardGroup);

        // Get all the mPvpRankingRewardGroupList where contentAmount is not null
        defaultMPvpRankingRewardGroupShouldBeFound("contentAmount.specified=true");

        // Get all the mPvpRankingRewardGroupList where contentAmount is null
        defaultMPvpRankingRewardGroupShouldNotBeFound("contentAmount.specified=false");
    }

    @Test
    @Transactional
    public void getAllMPvpRankingRewardGroupsByContentAmountIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mPvpRankingRewardGroupRepository.saveAndFlush(mPvpRankingRewardGroup);

        // Get all the mPvpRankingRewardGroupList where contentAmount greater than or equals to DEFAULT_CONTENT_AMOUNT
        defaultMPvpRankingRewardGroupShouldBeFound("contentAmount.greaterOrEqualThan=" + DEFAULT_CONTENT_AMOUNT);

        // Get all the mPvpRankingRewardGroupList where contentAmount greater than or equals to UPDATED_CONTENT_AMOUNT
        defaultMPvpRankingRewardGroupShouldNotBeFound("contentAmount.greaterOrEqualThan=" + UPDATED_CONTENT_AMOUNT);
    }

    @Test
    @Transactional
    public void getAllMPvpRankingRewardGroupsByContentAmountIsLessThanSomething() throws Exception {
        // Initialize the database
        mPvpRankingRewardGroupRepository.saveAndFlush(mPvpRankingRewardGroup);

        // Get all the mPvpRankingRewardGroupList where contentAmount less than or equals to DEFAULT_CONTENT_AMOUNT
        defaultMPvpRankingRewardGroupShouldNotBeFound("contentAmount.lessThan=" + DEFAULT_CONTENT_AMOUNT);

        // Get all the mPvpRankingRewardGroupList where contentAmount less than or equals to UPDATED_CONTENT_AMOUNT
        defaultMPvpRankingRewardGroupShouldBeFound("contentAmount.lessThan=" + UPDATED_CONTENT_AMOUNT);
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultMPvpRankingRewardGroupShouldBeFound(String filter) throws Exception {
        restMPvpRankingRewardGroupMockMvc.perform(get("/api/m-pvp-ranking-reward-groups?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(mPvpRankingRewardGroup.getId().intValue())))
            .andExpect(jsonPath("$.[*].groupId").value(hasItem(DEFAULT_GROUP_ID)))
            .andExpect(jsonPath("$.[*].contentType").value(hasItem(DEFAULT_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].contentId").value(hasItem(DEFAULT_CONTENT_ID)))
            .andExpect(jsonPath("$.[*].contentAmount").value(hasItem(DEFAULT_CONTENT_AMOUNT)));

        // Check, that the count call also returns 1
        restMPvpRankingRewardGroupMockMvc.perform(get("/api/m-pvp-ranking-reward-groups/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultMPvpRankingRewardGroupShouldNotBeFound(String filter) throws Exception {
        restMPvpRankingRewardGroupMockMvc.perform(get("/api/m-pvp-ranking-reward-groups?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restMPvpRankingRewardGroupMockMvc.perform(get("/api/m-pvp-ranking-reward-groups/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("0"));
    }


    @Test
    @Transactional
    public void getNonExistingMPvpRankingRewardGroup() throws Exception {
        // Get the mPvpRankingRewardGroup
        restMPvpRankingRewardGroupMockMvc.perform(get("/api/m-pvp-ranking-reward-groups/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateMPvpRankingRewardGroup() throws Exception {
        // Initialize the database
        mPvpRankingRewardGroupRepository.saveAndFlush(mPvpRankingRewardGroup);

        int databaseSizeBeforeUpdate = mPvpRankingRewardGroupRepository.findAll().size();

        // Update the mPvpRankingRewardGroup
        MPvpRankingRewardGroup updatedMPvpRankingRewardGroup = mPvpRankingRewardGroupRepository.findById(mPvpRankingRewardGroup.getId()).get();
        // Disconnect from session so that the updates on updatedMPvpRankingRewardGroup are not directly saved in db
        em.detach(updatedMPvpRankingRewardGroup);
        updatedMPvpRankingRewardGroup
            .groupId(UPDATED_GROUP_ID)
            .contentType(UPDATED_CONTENT_TYPE)
            .contentId(UPDATED_CONTENT_ID)
            .contentAmount(UPDATED_CONTENT_AMOUNT);
        MPvpRankingRewardGroupDTO mPvpRankingRewardGroupDTO = mPvpRankingRewardGroupMapper.toDto(updatedMPvpRankingRewardGroup);

        restMPvpRankingRewardGroupMockMvc.perform(put("/api/m-pvp-ranking-reward-groups")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mPvpRankingRewardGroupDTO)))
            .andExpect(status().isOk());

        // Validate the MPvpRankingRewardGroup in the database
        List<MPvpRankingRewardGroup> mPvpRankingRewardGroupList = mPvpRankingRewardGroupRepository.findAll();
        assertThat(mPvpRankingRewardGroupList).hasSize(databaseSizeBeforeUpdate);
        MPvpRankingRewardGroup testMPvpRankingRewardGroup = mPvpRankingRewardGroupList.get(mPvpRankingRewardGroupList.size() - 1);
        assertThat(testMPvpRankingRewardGroup.getGroupId()).isEqualTo(UPDATED_GROUP_ID);
        assertThat(testMPvpRankingRewardGroup.getContentType()).isEqualTo(UPDATED_CONTENT_TYPE);
        assertThat(testMPvpRankingRewardGroup.getContentId()).isEqualTo(UPDATED_CONTENT_ID);
        assertThat(testMPvpRankingRewardGroup.getContentAmount()).isEqualTo(UPDATED_CONTENT_AMOUNT);
    }

    @Test
    @Transactional
    public void updateNonExistingMPvpRankingRewardGroup() throws Exception {
        int databaseSizeBeforeUpdate = mPvpRankingRewardGroupRepository.findAll().size();

        // Create the MPvpRankingRewardGroup
        MPvpRankingRewardGroupDTO mPvpRankingRewardGroupDTO = mPvpRankingRewardGroupMapper.toDto(mPvpRankingRewardGroup);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restMPvpRankingRewardGroupMockMvc.perform(put("/api/m-pvp-ranking-reward-groups")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mPvpRankingRewardGroupDTO)))
            .andExpect(status().isBadRequest());

        // Validate the MPvpRankingRewardGroup in the database
        List<MPvpRankingRewardGroup> mPvpRankingRewardGroupList = mPvpRankingRewardGroupRepository.findAll();
        assertThat(mPvpRankingRewardGroupList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteMPvpRankingRewardGroup() throws Exception {
        // Initialize the database
        mPvpRankingRewardGroupRepository.saveAndFlush(mPvpRankingRewardGroup);

        int databaseSizeBeforeDelete = mPvpRankingRewardGroupRepository.findAll().size();

        // Delete the mPvpRankingRewardGroup
        restMPvpRankingRewardGroupMockMvc.perform(delete("/api/m-pvp-ranking-reward-groups/{id}", mPvpRankingRewardGroup.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database is empty
        List<MPvpRankingRewardGroup> mPvpRankingRewardGroupList = mPvpRankingRewardGroupRepository.findAll();
        assertThat(mPvpRankingRewardGroupList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(MPvpRankingRewardGroup.class);
        MPvpRankingRewardGroup mPvpRankingRewardGroup1 = new MPvpRankingRewardGroup();
        mPvpRankingRewardGroup1.setId(1L);
        MPvpRankingRewardGroup mPvpRankingRewardGroup2 = new MPvpRankingRewardGroup();
        mPvpRankingRewardGroup2.setId(mPvpRankingRewardGroup1.getId());
        assertThat(mPvpRankingRewardGroup1).isEqualTo(mPvpRankingRewardGroup2);
        mPvpRankingRewardGroup2.setId(2L);
        assertThat(mPvpRankingRewardGroup1).isNotEqualTo(mPvpRankingRewardGroup2);
        mPvpRankingRewardGroup1.setId(null);
        assertThat(mPvpRankingRewardGroup1).isNotEqualTo(mPvpRankingRewardGroup2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(MPvpRankingRewardGroupDTO.class);
        MPvpRankingRewardGroupDTO mPvpRankingRewardGroupDTO1 = new MPvpRankingRewardGroupDTO();
        mPvpRankingRewardGroupDTO1.setId(1L);
        MPvpRankingRewardGroupDTO mPvpRankingRewardGroupDTO2 = new MPvpRankingRewardGroupDTO();
        assertThat(mPvpRankingRewardGroupDTO1).isNotEqualTo(mPvpRankingRewardGroupDTO2);
        mPvpRankingRewardGroupDTO2.setId(mPvpRankingRewardGroupDTO1.getId());
        assertThat(mPvpRankingRewardGroupDTO1).isEqualTo(mPvpRankingRewardGroupDTO2);
        mPvpRankingRewardGroupDTO2.setId(2L);
        assertThat(mPvpRankingRewardGroupDTO1).isNotEqualTo(mPvpRankingRewardGroupDTO2);
        mPvpRankingRewardGroupDTO1.setId(null);
        assertThat(mPvpRankingRewardGroupDTO1).isNotEqualTo(mPvpRankingRewardGroupDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(mPvpRankingRewardGroupMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(mPvpRankingRewardGroupMapper.fromId(null)).isNull();
    }
}
