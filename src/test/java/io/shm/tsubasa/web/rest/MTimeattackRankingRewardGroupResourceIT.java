package io.shm.tsubasa.web.rest;

import io.shm.tsubasa.TsubasaApp;
import io.shm.tsubasa.domain.MTimeattackRankingRewardGroup;
import io.shm.tsubasa.repository.MTimeattackRankingRewardGroupRepository;
import io.shm.tsubasa.service.MTimeattackRankingRewardGroupService;
import io.shm.tsubasa.service.dto.MTimeattackRankingRewardGroupDTO;
import io.shm.tsubasa.service.mapper.MTimeattackRankingRewardGroupMapper;
import io.shm.tsubasa.web.rest.errors.ExceptionTranslator;
import io.shm.tsubasa.service.dto.MTimeattackRankingRewardGroupCriteria;
import io.shm.tsubasa.service.MTimeattackRankingRewardGroupQueryService;

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
 * Integration tests for the {@Link MTimeattackRankingRewardGroupResource} REST controller.
 */
@SpringBootTest(classes = TsubasaApp.class)
public class MTimeattackRankingRewardGroupResourceIT {

    private static final Integer DEFAULT_GROUP_ID = 1;
    private static final Integer UPDATED_GROUP_ID = 2;

    private static final Integer DEFAULT_CONTENT_TYPE = 1;
    private static final Integer UPDATED_CONTENT_TYPE = 2;

    private static final Integer DEFAULT_CONTENT_ID = 1;
    private static final Integer UPDATED_CONTENT_ID = 2;

    private static final Integer DEFAULT_CONTENT_AMOUNT = 1;
    private static final Integer UPDATED_CONTENT_AMOUNT = 2;

    @Autowired
    private MTimeattackRankingRewardGroupRepository mTimeattackRankingRewardGroupRepository;

    @Autowired
    private MTimeattackRankingRewardGroupMapper mTimeattackRankingRewardGroupMapper;

    @Autowired
    private MTimeattackRankingRewardGroupService mTimeattackRankingRewardGroupService;

    @Autowired
    private MTimeattackRankingRewardGroupQueryService mTimeattackRankingRewardGroupQueryService;

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

    private MockMvc restMTimeattackRankingRewardGroupMockMvc;

    private MTimeattackRankingRewardGroup mTimeattackRankingRewardGroup;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final MTimeattackRankingRewardGroupResource mTimeattackRankingRewardGroupResource = new MTimeattackRankingRewardGroupResource(mTimeattackRankingRewardGroupService, mTimeattackRankingRewardGroupQueryService);
        this.restMTimeattackRankingRewardGroupMockMvc = MockMvcBuilders.standaloneSetup(mTimeattackRankingRewardGroupResource)
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
    public static MTimeattackRankingRewardGroup createEntity(EntityManager em) {
        MTimeattackRankingRewardGroup mTimeattackRankingRewardGroup = new MTimeattackRankingRewardGroup()
            .groupId(DEFAULT_GROUP_ID)
            .contentType(DEFAULT_CONTENT_TYPE)
            .contentId(DEFAULT_CONTENT_ID)
            .contentAmount(DEFAULT_CONTENT_AMOUNT);
        return mTimeattackRankingRewardGroup;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static MTimeattackRankingRewardGroup createUpdatedEntity(EntityManager em) {
        MTimeattackRankingRewardGroup mTimeattackRankingRewardGroup = new MTimeattackRankingRewardGroup()
            .groupId(UPDATED_GROUP_ID)
            .contentType(UPDATED_CONTENT_TYPE)
            .contentId(UPDATED_CONTENT_ID)
            .contentAmount(UPDATED_CONTENT_AMOUNT);
        return mTimeattackRankingRewardGroup;
    }

    @BeforeEach
    public void initTest() {
        mTimeattackRankingRewardGroup = createEntity(em);
    }

    @Test
    @Transactional
    public void createMTimeattackRankingRewardGroup() throws Exception {
        int databaseSizeBeforeCreate = mTimeattackRankingRewardGroupRepository.findAll().size();

        // Create the MTimeattackRankingRewardGroup
        MTimeattackRankingRewardGroupDTO mTimeattackRankingRewardGroupDTO = mTimeattackRankingRewardGroupMapper.toDto(mTimeattackRankingRewardGroup);
        restMTimeattackRankingRewardGroupMockMvc.perform(post("/api/m-timeattack-ranking-reward-groups")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mTimeattackRankingRewardGroupDTO)))
            .andExpect(status().isCreated());

        // Validate the MTimeattackRankingRewardGroup in the database
        List<MTimeattackRankingRewardGroup> mTimeattackRankingRewardGroupList = mTimeattackRankingRewardGroupRepository.findAll();
        assertThat(mTimeattackRankingRewardGroupList).hasSize(databaseSizeBeforeCreate + 1);
        MTimeattackRankingRewardGroup testMTimeattackRankingRewardGroup = mTimeattackRankingRewardGroupList.get(mTimeattackRankingRewardGroupList.size() - 1);
        assertThat(testMTimeattackRankingRewardGroup.getGroupId()).isEqualTo(DEFAULT_GROUP_ID);
        assertThat(testMTimeattackRankingRewardGroup.getContentType()).isEqualTo(DEFAULT_CONTENT_TYPE);
        assertThat(testMTimeattackRankingRewardGroup.getContentId()).isEqualTo(DEFAULT_CONTENT_ID);
        assertThat(testMTimeattackRankingRewardGroup.getContentAmount()).isEqualTo(DEFAULT_CONTENT_AMOUNT);
    }

    @Test
    @Transactional
    public void createMTimeattackRankingRewardGroupWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = mTimeattackRankingRewardGroupRepository.findAll().size();

        // Create the MTimeattackRankingRewardGroup with an existing ID
        mTimeattackRankingRewardGroup.setId(1L);
        MTimeattackRankingRewardGroupDTO mTimeattackRankingRewardGroupDTO = mTimeattackRankingRewardGroupMapper.toDto(mTimeattackRankingRewardGroup);

        // An entity with an existing ID cannot be created, so this API call must fail
        restMTimeattackRankingRewardGroupMockMvc.perform(post("/api/m-timeattack-ranking-reward-groups")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mTimeattackRankingRewardGroupDTO)))
            .andExpect(status().isBadRequest());

        // Validate the MTimeattackRankingRewardGroup in the database
        List<MTimeattackRankingRewardGroup> mTimeattackRankingRewardGroupList = mTimeattackRankingRewardGroupRepository.findAll();
        assertThat(mTimeattackRankingRewardGroupList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkGroupIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = mTimeattackRankingRewardGroupRepository.findAll().size();
        // set the field null
        mTimeattackRankingRewardGroup.setGroupId(null);

        // Create the MTimeattackRankingRewardGroup, which fails.
        MTimeattackRankingRewardGroupDTO mTimeattackRankingRewardGroupDTO = mTimeattackRankingRewardGroupMapper.toDto(mTimeattackRankingRewardGroup);

        restMTimeattackRankingRewardGroupMockMvc.perform(post("/api/m-timeattack-ranking-reward-groups")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mTimeattackRankingRewardGroupDTO)))
            .andExpect(status().isBadRequest());

        List<MTimeattackRankingRewardGroup> mTimeattackRankingRewardGroupList = mTimeattackRankingRewardGroupRepository.findAll();
        assertThat(mTimeattackRankingRewardGroupList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkContentTypeIsRequired() throws Exception {
        int databaseSizeBeforeTest = mTimeattackRankingRewardGroupRepository.findAll().size();
        // set the field null
        mTimeattackRankingRewardGroup.setContentType(null);

        // Create the MTimeattackRankingRewardGroup, which fails.
        MTimeattackRankingRewardGroupDTO mTimeattackRankingRewardGroupDTO = mTimeattackRankingRewardGroupMapper.toDto(mTimeattackRankingRewardGroup);

        restMTimeattackRankingRewardGroupMockMvc.perform(post("/api/m-timeattack-ranking-reward-groups")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mTimeattackRankingRewardGroupDTO)))
            .andExpect(status().isBadRequest());

        List<MTimeattackRankingRewardGroup> mTimeattackRankingRewardGroupList = mTimeattackRankingRewardGroupRepository.findAll();
        assertThat(mTimeattackRankingRewardGroupList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkContentAmountIsRequired() throws Exception {
        int databaseSizeBeforeTest = mTimeattackRankingRewardGroupRepository.findAll().size();
        // set the field null
        mTimeattackRankingRewardGroup.setContentAmount(null);

        // Create the MTimeattackRankingRewardGroup, which fails.
        MTimeattackRankingRewardGroupDTO mTimeattackRankingRewardGroupDTO = mTimeattackRankingRewardGroupMapper.toDto(mTimeattackRankingRewardGroup);

        restMTimeattackRankingRewardGroupMockMvc.perform(post("/api/m-timeattack-ranking-reward-groups")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mTimeattackRankingRewardGroupDTO)))
            .andExpect(status().isBadRequest());

        List<MTimeattackRankingRewardGroup> mTimeattackRankingRewardGroupList = mTimeattackRankingRewardGroupRepository.findAll();
        assertThat(mTimeattackRankingRewardGroupList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllMTimeattackRankingRewardGroups() throws Exception {
        // Initialize the database
        mTimeattackRankingRewardGroupRepository.saveAndFlush(mTimeattackRankingRewardGroup);

        // Get all the mTimeattackRankingRewardGroupList
        restMTimeattackRankingRewardGroupMockMvc.perform(get("/api/m-timeattack-ranking-reward-groups?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(mTimeattackRankingRewardGroup.getId().intValue())))
            .andExpect(jsonPath("$.[*].groupId").value(hasItem(DEFAULT_GROUP_ID)))
            .andExpect(jsonPath("$.[*].contentType").value(hasItem(DEFAULT_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].contentId").value(hasItem(DEFAULT_CONTENT_ID)))
            .andExpect(jsonPath("$.[*].contentAmount").value(hasItem(DEFAULT_CONTENT_AMOUNT)));
    }
    
    @Test
    @Transactional
    public void getMTimeattackRankingRewardGroup() throws Exception {
        // Initialize the database
        mTimeattackRankingRewardGroupRepository.saveAndFlush(mTimeattackRankingRewardGroup);

        // Get the mTimeattackRankingRewardGroup
        restMTimeattackRankingRewardGroupMockMvc.perform(get("/api/m-timeattack-ranking-reward-groups/{id}", mTimeattackRankingRewardGroup.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(mTimeattackRankingRewardGroup.getId().intValue()))
            .andExpect(jsonPath("$.groupId").value(DEFAULT_GROUP_ID))
            .andExpect(jsonPath("$.contentType").value(DEFAULT_CONTENT_TYPE))
            .andExpect(jsonPath("$.contentId").value(DEFAULT_CONTENT_ID))
            .andExpect(jsonPath("$.contentAmount").value(DEFAULT_CONTENT_AMOUNT));
    }

    @Test
    @Transactional
    public void getAllMTimeattackRankingRewardGroupsByGroupIdIsEqualToSomething() throws Exception {
        // Initialize the database
        mTimeattackRankingRewardGroupRepository.saveAndFlush(mTimeattackRankingRewardGroup);

        // Get all the mTimeattackRankingRewardGroupList where groupId equals to DEFAULT_GROUP_ID
        defaultMTimeattackRankingRewardGroupShouldBeFound("groupId.equals=" + DEFAULT_GROUP_ID);

        // Get all the mTimeattackRankingRewardGroupList where groupId equals to UPDATED_GROUP_ID
        defaultMTimeattackRankingRewardGroupShouldNotBeFound("groupId.equals=" + UPDATED_GROUP_ID);
    }

    @Test
    @Transactional
    public void getAllMTimeattackRankingRewardGroupsByGroupIdIsInShouldWork() throws Exception {
        // Initialize the database
        mTimeattackRankingRewardGroupRepository.saveAndFlush(mTimeattackRankingRewardGroup);

        // Get all the mTimeattackRankingRewardGroupList where groupId in DEFAULT_GROUP_ID or UPDATED_GROUP_ID
        defaultMTimeattackRankingRewardGroupShouldBeFound("groupId.in=" + DEFAULT_GROUP_ID + "," + UPDATED_GROUP_ID);

        // Get all the mTimeattackRankingRewardGroupList where groupId equals to UPDATED_GROUP_ID
        defaultMTimeattackRankingRewardGroupShouldNotBeFound("groupId.in=" + UPDATED_GROUP_ID);
    }

    @Test
    @Transactional
    public void getAllMTimeattackRankingRewardGroupsByGroupIdIsNullOrNotNull() throws Exception {
        // Initialize the database
        mTimeattackRankingRewardGroupRepository.saveAndFlush(mTimeattackRankingRewardGroup);

        // Get all the mTimeattackRankingRewardGroupList where groupId is not null
        defaultMTimeattackRankingRewardGroupShouldBeFound("groupId.specified=true");

        // Get all the mTimeattackRankingRewardGroupList where groupId is null
        defaultMTimeattackRankingRewardGroupShouldNotBeFound("groupId.specified=false");
    }

    @Test
    @Transactional
    public void getAllMTimeattackRankingRewardGroupsByGroupIdIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mTimeattackRankingRewardGroupRepository.saveAndFlush(mTimeattackRankingRewardGroup);

        // Get all the mTimeattackRankingRewardGroupList where groupId greater than or equals to DEFAULT_GROUP_ID
        defaultMTimeattackRankingRewardGroupShouldBeFound("groupId.greaterOrEqualThan=" + DEFAULT_GROUP_ID);

        // Get all the mTimeattackRankingRewardGroupList where groupId greater than or equals to UPDATED_GROUP_ID
        defaultMTimeattackRankingRewardGroupShouldNotBeFound("groupId.greaterOrEqualThan=" + UPDATED_GROUP_ID);
    }

    @Test
    @Transactional
    public void getAllMTimeattackRankingRewardGroupsByGroupIdIsLessThanSomething() throws Exception {
        // Initialize the database
        mTimeattackRankingRewardGroupRepository.saveAndFlush(mTimeattackRankingRewardGroup);

        // Get all the mTimeattackRankingRewardGroupList where groupId less than or equals to DEFAULT_GROUP_ID
        defaultMTimeattackRankingRewardGroupShouldNotBeFound("groupId.lessThan=" + DEFAULT_GROUP_ID);

        // Get all the mTimeattackRankingRewardGroupList where groupId less than or equals to UPDATED_GROUP_ID
        defaultMTimeattackRankingRewardGroupShouldBeFound("groupId.lessThan=" + UPDATED_GROUP_ID);
    }


    @Test
    @Transactional
    public void getAllMTimeattackRankingRewardGroupsByContentTypeIsEqualToSomething() throws Exception {
        // Initialize the database
        mTimeattackRankingRewardGroupRepository.saveAndFlush(mTimeattackRankingRewardGroup);

        // Get all the mTimeattackRankingRewardGroupList where contentType equals to DEFAULT_CONTENT_TYPE
        defaultMTimeattackRankingRewardGroupShouldBeFound("contentType.equals=" + DEFAULT_CONTENT_TYPE);

        // Get all the mTimeattackRankingRewardGroupList where contentType equals to UPDATED_CONTENT_TYPE
        defaultMTimeattackRankingRewardGroupShouldNotBeFound("contentType.equals=" + UPDATED_CONTENT_TYPE);
    }

    @Test
    @Transactional
    public void getAllMTimeattackRankingRewardGroupsByContentTypeIsInShouldWork() throws Exception {
        // Initialize the database
        mTimeattackRankingRewardGroupRepository.saveAndFlush(mTimeattackRankingRewardGroup);

        // Get all the mTimeattackRankingRewardGroupList where contentType in DEFAULT_CONTENT_TYPE or UPDATED_CONTENT_TYPE
        defaultMTimeattackRankingRewardGroupShouldBeFound("contentType.in=" + DEFAULT_CONTENT_TYPE + "," + UPDATED_CONTENT_TYPE);

        // Get all the mTimeattackRankingRewardGroupList where contentType equals to UPDATED_CONTENT_TYPE
        defaultMTimeattackRankingRewardGroupShouldNotBeFound("contentType.in=" + UPDATED_CONTENT_TYPE);
    }

    @Test
    @Transactional
    public void getAllMTimeattackRankingRewardGroupsByContentTypeIsNullOrNotNull() throws Exception {
        // Initialize the database
        mTimeattackRankingRewardGroupRepository.saveAndFlush(mTimeattackRankingRewardGroup);

        // Get all the mTimeattackRankingRewardGroupList where contentType is not null
        defaultMTimeattackRankingRewardGroupShouldBeFound("contentType.specified=true");

        // Get all the mTimeattackRankingRewardGroupList where contentType is null
        defaultMTimeattackRankingRewardGroupShouldNotBeFound("contentType.specified=false");
    }

    @Test
    @Transactional
    public void getAllMTimeattackRankingRewardGroupsByContentTypeIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mTimeattackRankingRewardGroupRepository.saveAndFlush(mTimeattackRankingRewardGroup);

        // Get all the mTimeattackRankingRewardGroupList where contentType greater than or equals to DEFAULT_CONTENT_TYPE
        defaultMTimeattackRankingRewardGroupShouldBeFound("contentType.greaterOrEqualThan=" + DEFAULT_CONTENT_TYPE);

        // Get all the mTimeattackRankingRewardGroupList where contentType greater than or equals to UPDATED_CONTENT_TYPE
        defaultMTimeattackRankingRewardGroupShouldNotBeFound("contentType.greaterOrEqualThan=" + UPDATED_CONTENT_TYPE);
    }

    @Test
    @Transactional
    public void getAllMTimeattackRankingRewardGroupsByContentTypeIsLessThanSomething() throws Exception {
        // Initialize the database
        mTimeattackRankingRewardGroupRepository.saveAndFlush(mTimeattackRankingRewardGroup);

        // Get all the mTimeattackRankingRewardGroupList where contentType less than or equals to DEFAULT_CONTENT_TYPE
        defaultMTimeattackRankingRewardGroupShouldNotBeFound("contentType.lessThan=" + DEFAULT_CONTENT_TYPE);

        // Get all the mTimeattackRankingRewardGroupList where contentType less than or equals to UPDATED_CONTENT_TYPE
        defaultMTimeattackRankingRewardGroupShouldBeFound("contentType.lessThan=" + UPDATED_CONTENT_TYPE);
    }


    @Test
    @Transactional
    public void getAllMTimeattackRankingRewardGroupsByContentIdIsEqualToSomething() throws Exception {
        // Initialize the database
        mTimeattackRankingRewardGroupRepository.saveAndFlush(mTimeattackRankingRewardGroup);

        // Get all the mTimeattackRankingRewardGroupList where contentId equals to DEFAULT_CONTENT_ID
        defaultMTimeattackRankingRewardGroupShouldBeFound("contentId.equals=" + DEFAULT_CONTENT_ID);

        // Get all the mTimeattackRankingRewardGroupList where contentId equals to UPDATED_CONTENT_ID
        defaultMTimeattackRankingRewardGroupShouldNotBeFound("contentId.equals=" + UPDATED_CONTENT_ID);
    }

    @Test
    @Transactional
    public void getAllMTimeattackRankingRewardGroupsByContentIdIsInShouldWork() throws Exception {
        // Initialize the database
        mTimeattackRankingRewardGroupRepository.saveAndFlush(mTimeattackRankingRewardGroup);

        // Get all the mTimeattackRankingRewardGroupList where contentId in DEFAULT_CONTENT_ID or UPDATED_CONTENT_ID
        defaultMTimeattackRankingRewardGroupShouldBeFound("contentId.in=" + DEFAULT_CONTENT_ID + "," + UPDATED_CONTENT_ID);

        // Get all the mTimeattackRankingRewardGroupList where contentId equals to UPDATED_CONTENT_ID
        defaultMTimeattackRankingRewardGroupShouldNotBeFound("contentId.in=" + UPDATED_CONTENT_ID);
    }

    @Test
    @Transactional
    public void getAllMTimeattackRankingRewardGroupsByContentIdIsNullOrNotNull() throws Exception {
        // Initialize the database
        mTimeattackRankingRewardGroupRepository.saveAndFlush(mTimeattackRankingRewardGroup);

        // Get all the mTimeattackRankingRewardGroupList where contentId is not null
        defaultMTimeattackRankingRewardGroupShouldBeFound("contentId.specified=true");

        // Get all the mTimeattackRankingRewardGroupList where contentId is null
        defaultMTimeattackRankingRewardGroupShouldNotBeFound("contentId.specified=false");
    }

    @Test
    @Transactional
    public void getAllMTimeattackRankingRewardGroupsByContentIdIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mTimeattackRankingRewardGroupRepository.saveAndFlush(mTimeattackRankingRewardGroup);

        // Get all the mTimeattackRankingRewardGroupList where contentId greater than or equals to DEFAULT_CONTENT_ID
        defaultMTimeattackRankingRewardGroupShouldBeFound("contentId.greaterOrEqualThan=" + DEFAULT_CONTENT_ID);

        // Get all the mTimeattackRankingRewardGroupList where contentId greater than or equals to UPDATED_CONTENT_ID
        defaultMTimeattackRankingRewardGroupShouldNotBeFound("contentId.greaterOrEqualThan=" + UPDATED_CONTENT_ID);
    }

    @Test
    @Transactional
    public void getAllMTimeattackRankingRewardGroupsByContentIdIsLessThanSomething() throws Exception {
        // Initialize the database
        mTimeattackRankingRewardGroupRepository.saveAndFlush(mTimeattackRankingRewardGroup);

        // Get all the mTimeattackRankingRewardGroupList where contentId less than or equals to DEFAULT_CONTENT_ID
        defaultMTimeattackRankingRewardGroupShouldNotBeFound("contentId.lessThan=" + DEFAULT_CONTENT_ID);

        // Get all the mTimeattackRankingRewardGroupList where contentId less than or equals to UPDATED_CONTENT_ID
        defaultMTimeattackRankingRewardGroupShouldBeFound("contentId.lessThan=" + UPDATED_CONTENT_ID);
    }


    @Test
    @Transactional
    public void getAllMTimeattackRankingRewardGroupsByContentAmountIsEqualToSomething() throws Exception {
        // Initialize the database
        mTimeattackRankingRewardGroupRepository.saveAndFlush(mTimeattackRankingRewardGroup);

        // Get all the mTimeattackRankingRewardGroupList where contentAmount equals to DEFAULT_CONTENT_AMOUNT
        defaultMTimeattackRankingRewardGroupShouldBeFound("contentAmount.equals=" + DEFAULT_CONTENT_AMOUNT);

        // Get all the mTimeattackRankingRewardGroupList where contentAmount equals to UPDATED_CONTENT_AMOUNT
        defaultMTimeattackRankingRewardGroupShouldNotBeFound("contentAmount.equals=" + UPDATED_CONTENT_AMOUNT);
    }

    @Test
    @Transactional
    public void getAllMTimeattackRankingRewardGroupsByContentAmountIsInShouldWork() throws Exception {
        // Initialize the database
        mTimeattackRankingRewardGroupRepository.saveAndFlush(mTimeattackRankingRewardGroup);

        // Get all the mTimeattackRankingRewardGroupList where contentAmount in DEFAULT_CONTENT_AMOUNT or UPDATED_CONTENT_AMOUNT
        defaultMTimeattackRankingRewardGroupShouldBeFound("contentAmount.in=" + DEFAULT_CONTENT_AMOUNT + "," + UPDATED_CONTENT_AMOUNT);

        // Get all the mTimeattackRankingRewardGroupList where contentAmount equals to UPDATED_CONTENT_AMOUNT
        defaultMTimeattackRankingRewardGroupShouldNotBeFound("contentAmount.in=" + UPDATED_CONTENT_AMOUNT);
    }

    @Test
    @Transactional
    public void getAllMTimeattackRankingRewardGroupsByContentAmountIsNullOrNotNull() throws Exception {
        // Initialize the database
        mTimeattackRankingRewardGroupRepository.saveAndFlush(mTimeattackRankingRewardGroup);

        // Get all the mTimeattackRankingRewardGroupList where contentAmount is not null
        defaultMTimeattackRankingRewardGroupShouldBeFound("contentAmount.specified=true");

        // Get all the mTimeattackRankingRewardGroupList where contentAmount is null
        defaultMTimeattackRankingRewardGroupShouldNotBeFound("contentAmount.specified=false");
    }

    @Test
    @Transactional
    public void getAllMTimeattackRankingRewardGroupsByContentAmountIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mTimeattackRankingRewardGroupRepository.saveAndFlush(mTimeattackRankingRewardGroup);

        // Get all the mTimeattackRankingRewardGroupList where contentAmount greater than or equals to DEFAULT_CONTENT_AMOUNT
        defaultMTimeattackRankingRewardGroupShouldBeFound("contentAmount.greaterOrEqualThan=" + DEFAULT_CONTENT_AMOUNT);

        // Get all the mTimeattackRankingRewardGroupList where contentAmount greater than or equals to UPDATED_CONTENT_AMOUNT
        defaultMTimeattackRankingRewardGroupShouldNotBeFound("contentAmount.greaterOrEqualThan=" + UPDATED_CONTENT_AMOUNT);
    }

    @Test
    @Transactional
    public void getAllMTimeattackRankingRewardGroupsByContentAmountIsLessThanSomething() throws Exception {
        // Initialize the database
        mTimeattackRankingRewardGroupRepository.saveAndFlush(mTimeattackRankingRewardGroup);

        // Get all the mTimeattackRankingRewardGroupList where contentAmount less than or equals to DEFAULT_CONTENT_AMOUNT
        defaultMTimeattackRankingRewardGroupShouldNotBeFound("contentAmount.lessThan=" + DEFAULT_CONTENT_AMOUNT);

        // Get all the mTimeattackRankingRewardGroupList where contentAmount less than or equals to UPDATED_CONTENT_AMOUNT
        defaultMTimeattackRankingRewardGroupShouldBeFound("contentAmount.lessThan=" + UPDATED_CONTENT_AMOUNT);
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultMTimeattackRankingRewardGroupShouldBeFound(String filter) throws Exception {
        restMTimeattackRankingRewardGroupMockMvc.perform(get("/api/m-timeattack-ranking-reward-groups?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(mTimeattackRankingRewardGroup.getId().intValue())))
            .andExpect(jsonPath("$.[*].groupId").value(hasItem(DEFAULT_GROUP_ID)))
            .andExpect(jsonPath("$.[*].contentType").value(hasItem(DEFAULT_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].contentId").value(hasItem(DEFAULT_CONTENT_ID)))
            .andExpect(jsonPath("$.[*].contentAmount").value(hasItem(DEFAULT_CONTENT_AMOUNT)));

        // Check, that the count call also returns 1
        restMTimeattackRankingRewardGroupMockMvc.perform(get("/api/m-timeattack-ranking-reward-groups/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultMTimeattackRankingRewardGroupShouldNotBeFound(String filter) throws Exception {
        restMTimeattackRankingRewardGroupMockMvc.perform(get("/api/m-timeattack-ranking-reward-groups?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restMTimeattackRankingRewardGroupMockMvc.perform(get("/api/m-timeattack-ranking-reward-groups/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("0"));
    }


    @Test
    @Transactional
    public void getNonExistingMTimeattackRankingRewardGroup() throws Exception {
        // Get the mTimeattackRankingRewardGroup
        restMTimeattackRankingRewardGroupMockMvc.perform(get("/api/m-timeattack-ranking-reward-groups/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateMTimeattackRankingRewardGroup() throws Exception {
        // Initialize the database
        mTimeattackRankingRewardGroupRepository.saveAndFlush(mTimeattackRankingRewardGroup);

        int databaseSizeBeforeUpdate = mTimeattackRankingRewardGroupRepository.findAll().size();

        // Update the mTimeattackRankingRewardGroup
        MTimeattackRankingRewardGroup updatedMTimeattackRankingRewardGroup = mTimeattackRankingRewardGroupRepository.findById(mTimeattackRankingRewardGroup.getId()).get();
        // Disconnect from session so that the updates on updatedMTimeattackRankingRewardGroup are not directly saved in db
        em.detach(updatedMTimeattackRankingRewardGroup);
        updatedMTimeattackRankingRewardGroup
            .groupId(UPDATED_GROUP_ID)
            .contentType(UPDATED_CONTENT_TYPE)
            .contentId(UPDATED_CONTENT_ID)
            .contentAmount(UPDATED_CONTENT_AMOUNT);
        MTimeattackRankingRewardGroupDTO mTimeattackRankingRewardGroupDTO = mTimeattackRankingRewardGroupMapper.toDto(updatedMTimeattackRankingRewardGroup);

        restMTimeattackRankingRewardGroupMockMvc.perform(put("/api/m-timeattack-ranking-reward-groups")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mTimeattackRankingRewardGroupDTO)))
            .andExpect(status().isOk());

        // Validate the MTimeattackRankingRewardGroup in the database
        List<MTimeattackRankingRewardGroup> mTimeattackRankingRewardGroupList = mTimeattackRankingRewardGroupRepository.findAll();
        assertThat(mTimeattackRankingRewardGroupList).hasSize(databaseSizeBeforeUpdate);
        MTimeattackRankingRewardGroup testMTimeattackRankingRewardGroup = mTimeattackRankingRewardGroupList.get(mTimeattackRankingRewardGroupList.size() - 1);
        assertThat(testMTimeattackRankingRewardGroup.getGroupId()).isEqualTo(UPDATED_GROUP_ID);
        assertThat(testMTimeattackRankingRewardGroup.getContentType()).isEqualTo(UPDATED_CONTENT_TYPE);
        assertThat(testMTimeattackRankingRewardGroup.getContentId()).isEqualTo(UPDATED_CONTENT_ID);
        assertThat(testMTimeattackRankingRewardGroup.getContentAmount()).isEqualTo(UPDATED_CONTENT_AMOUNT);
    }

    @Test
    @Transactional
    public void updateNonExistingMTimeattackRankingRewardGroup() throws Exception {
        int databaseSizeBeforeUpdate = mTimeattackRankingRewardGroupRepository.findAll().size();

        // Create the MTimeattackRankingRewardGroup
        MTimeattackRankingRewardGroupDTO mTimeattackRankingRewardGroupDTO = mTimeattackRankingRewardGroupMapper.toDto(mTimeattackRankingRewardGroup);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restMTimeattackRankingRewardGroupMockMvc.perform(put("/api/m-timeattack-ranking-reward-groups")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mTimeattackRankingRewardGroupDTO)))
            .andExpect(status().isBadRequest());

        // Validate the MTimeattackRankingRewardGroup in the database
        List<MTimeattackRankingRewardGroup> mTimeattackRankingRewardGroupList = mTimeattackRankingRewardGroupRepository.findAll();
        assertThat(mTimeattackRankingRewardGroupList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteMTimeattackRankingRewardGroup() throws Exception {
        // Initialize the database
        mTimeattackRankingRewardGroupRepository.saveAndFlush(mTimeattackRankingRewardGroup);

        int databaseSizeBeforeDelete = mTimeattackRankingRewardGroupRepository.findAll().size();

        // Delete the mTimeattackRankingRewardGroup
        restMTimeattackRankingRewardGroupMockMvc.perform(delete("/api/m-timeattack-ranking-reward-groups/{id}", mTimeattackRankingRewardGroup.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database is empty
        List<MTimeattackRankingRewardGroup> mTimeattackRankingRewardGroupList = mTimeattackRankingRewardGroupRepository.findAll();
        assertThat(mTimeattackRankingRewardGroupList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(MTimeattackRankingRewardGroup.class);
        MTimeattackRankingRewardGroup mTimeattackRankingRewardGroup1 = new MTimeattackRankingRewardGroup();
        mTimeattackRankingRewardGroup1.setId(1L);
        MTimeattackRankingRewardGroup mTimeattackRankingRewardGroup2 = new MTimeattackRankingRewardGroup();
        mTimeattackRankingRewardGroup2.setId(mTimeattackRankingRewardGroup1.getId());
        assertThat(mTimeattackRankingRewardGroup1).isEqualTo(mTimeattackRankingRewardGroup2);
        mTimeattackRankingRewardGroup2.setId(2L);
        assertThat(mTimeattackRankingRewardGroup1).isNotEqualTo(mTimeattackRankingRewardGroup2);
        mTimeattackRankingRewardGroup1.setId(null);
        assertThat(mTimeattackRankingRewardGroup1).isNotEqualTo(mTimeattackRankingRewardGroup2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(MTimeattackRankingRewardGroupDTO.class);
        MTimeattackRankingRewardGroupDTO mTimeattackRankingRewardGroupDTO1 = new MTimeattackRankingRewardGroupDTO();
        mTimeattackRankingRewardGroupDTO1.setId(1L);
        MTimeattackRankingRewardGroupDTO mTimeattackRankingRewardGroupDTO2 = new MTimeattackRankingRewardGroupDTO();
        assertThat(mTimeattackRankingRewardGroupDTO1).isNotEqualTo(mTimeattackRankingRewardGroupDTO2);
        mTimeattackRankingRewardGroupDTO2.setId(mTimeattackRankingRewardGroupDTO1.getId());
        assertThat(mTimeattackRankingRewardGroupDTO1).isEqualTo(mTimeattackRankingRewardGroupDTO2);
        mTimeattackRankingRewardGroupDTO2.setId(2L);
        assertThat(mTimeattackRankingRewardGroupDTO1).isNotEqualTo(mTimeattackRankingRewardGroupDTO2);
        mTimeattackRankingRewardGroupDTO1.setId(null);
        assertThat(mTimeattackRankingRewardGroupDTO1).isNotEqualTo(mTimeattackRankingRewardGroupDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(mTimeattackRankingRewardGroupMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(mTimeattackRankingRewardGroupMapper.fromId(null)).isNull();
    }
}
