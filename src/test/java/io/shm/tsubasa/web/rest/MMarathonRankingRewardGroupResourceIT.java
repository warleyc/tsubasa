package io.shm.tsubasa.web.rest;

import io.shm.tsubasa.TsubasaApp;
import io.shm.tsubasa.domain.MMarathonRankingRewardGroup;
import io.shm.tsubasa.repository.MMarathonRankingRewardGroupRepository;
import io.shm.tsubasa.service.MMarathonRankingRewardGroupService;
import io.shm.tsubasa.service.dto.MMarathonRankingRewardGroupDTO;
import io.shm.tsubasa.service.mapper.MMarathonRankingRewardGroupMapper;
import io.shm.tsubasa.web.rest.errors.ExceptionTranslator;
import io.shm.tsubasa.service.dto.MMarathonRankingRewardGroupCriteria;
import io.shm.tsubasa.service.MMarathonRankingRewardGroupQueryService;

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
 * Integration tests for the {@Link MMarathonRankingRewardGroupResource} REST controller.
 */
@SpringBootTest(classes = TsubasaApp.class)
public class MMarathonRankingRewardGroupResourceIT {

    private static final Integer DEFAULT_GROUP_ID = 1;
    private static final Integer UPDATED_GROUP_ID = 2;

    private static final Integer DEFAULT_CONTENT_TYPE = 1;
    private static final Integer UPDATED_CONTENT_TYPE = 2;

    private static final Integer DEFAULT_CONTENT_ID = 1;
    private static final Integer UPDATED_CONTENT_ID = 2;

    private static final Integer DEFAULT_CONTENT_AMOUNT = 1;
    private static final Integer UPDATED_CONTENT_AMOUNT = 2;

    @Autowired
    private MMarathonRankingRewardGroupRepository mMarathonRankingRewardGroupRepository;

    @Autowired
    private MMarathonRankingRewardGroupMapper mMarathonRankingRewardGroupMapper;

    @Autowired
    private MMarathonRankingRewardGroupService mMarathonRankingRewardGroupService;

    @Autowired
    private MMarathonRankingRewardGroupQueryService mMarathonRankingRewardGroupQueryService;

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

    private MockMvc restMMarathonRankingRewardGroupMockMvc;

    private MMarathonRankingRewardGroup mMarathonRankingRewardGroup;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final MMarathonRankingRewardGroupResource mMarathonRankingRewardGroupResource = new MMarathonRankingRewardGroupResource(mMarathonRankingRewardGroupService, mMarathonRankingRewardGroupQueryService);
        this.restMMarathonRankingRewardGroupMockMvc = MockMvcBuilders.standaloneSetup(mMarathonRankingRewardGroupResource)
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
    public static MMarathonRankingRewardGroup createEntity(EntityManager em) {
        MMarathonRankingRewardGroup mMarathonRankingRewardGroup = new MMarathonRankingRewardGroup()
            .groupId(DEFAULT_GROUP_ID)
            .contentType(DEFAULT_CONTENT_TYPE)
            .contentId(DEFAULT_CONTENT_ID)
            .contentAmount(DEFAULT_CONTENT_AMOUNT);
        return mMarathonRankingRewardGroup;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static MMarathonRankingRewardGroup createUpdatedEntity(EntityManager em) {
        MMarathonRankingRewardGroup mMarathonRankingRewardGroup = new MMarathonRankingRewardGroup()
            .groupId(UPDATED_GROUP_ID)
            .contentType(UPDATED_CONTENT_TYPE)
            .contentId(UPDATED_CONTENT_ID)
            .contentAmount(UPDATED_CONTENT_AMOUNT);
        return mMarathonRankingRewardGroup;
    }

    @BeforeEach
    public void initTest() {
        mMarathonRankingRewardGroup = createEntity(em);
    }

    @Test
    @Transactional
    public void createMMarathonRankingRewardGroup() throws Exception {
        int databaseSizeBeforeCreate = mMarathonRankingRewardGroupRepository.findAll().size();

        // Create the MMarathonRankingRewardGroup
        MMarathonRankingRewardGroupDTO mMarathonRankingRewardGroupDTO = mMarathonRankingRewardGroupMapper.toDto(mMarathonRankingRewardGroup);
        restMMarathonRankingRewardGroupMockMvc.perform(post("/api/m-marathon-ranking-reward-groups")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mMarathonRankingRewardGroupDTO)))
            .andExpect(status().isCreated());

        // Validate the MMarathonRankingRewardGroup in the database
        List<MMarathonRankingRewardGroup> mMarathonRankingRewardGroupList = mMarathonRankingRewardGroupRepository.findAll();
        assertThat(mMarathonRankingRewardGroupList).hasSize(databaseSizeBeforeCreate + 1);
        MMarathonRankingRewardGroup testMMarathonRankingRewardGroup = mMarathonRankingRewardGroupList.get(mMarathonRankingRewardGroupList.size() - 1);
        assertThat(testMMarathonRankingRewardGroup.getGroupId()).isEqualTo(DEFAULT_GROUP_ID);
        assertThat(testMMarathonRankingRewardGroup.getContentType()).isEqualTo(DEFAULT_CONTENT_TYPE);
        assertThat(testMMarathonRankingRewardGroup.getContentId()).isEqualTo(DEFAULT_CONTENT_ID);
        assertThat(testMMarathonRankingRewardGroup.getContentAmount()).isEqualTo(DEFAULT_CONTENT_AMOUNT);
    }

    @Test
    @Transactional
    public void createMMarathonRankingRewardGroupWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = mMarathonRankingRewardGroupRepository.findAll().size();

        // Create the MMarathonRankingRewardGroup with an existing ID
        mMarathonRankingRewardGroup.setId(1L);
        MMarathonRankingRewardGroupDTO mMarathonRankingRewardGroupDTO = mMarathonRankingRewardGroupMapper.toDto(mMarathonRankingRewardGroup);

        // An entity with an existing ID cannot be created, so this API call must fail
        restMMarathonRankingRewardGroupMockMvc.perform(post("/api/m-marathon-ranking-reward-groups")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mMarathonRankingRewardGroupDTO)))
            .andExpect(status().isBadRequest());

        // Validate the MMarathonRankingRewardGroup in the database
        List<MMarathonRankingRewardGroup> mMarathonRankingRewardGroupList = mMarathonRankingRewardGroupRepository.findAll();
        assertThat(mMarathonRankingRewardGroupList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkGroupIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = mMarathonRankingRewardGroupRepository.findAll().size();
        // set the field null
        mMarathonRankingRewardGroup.setGroupId(null);

        // Create the MMarathonRankingRewardGroup, which fails.
        MMarathonRankingRewardGroupDTO mMarathonRankingRewardGroupDTO = mMarathonRankingRewardGroupMapper.toDto(mMarathonRankingRewardGroup);

        restMMarathonRankingRewardGroupMockMvc.perform(post("/api/m-marathon-ranking-reward-groups")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mMarathonRankingRewardGroupDTO)))
            .andExpect(status().isBadRequest());

        List<MMarathonRankingRewardGroup> mMarathonRankingRewardGroupList = mMarathonRankingRewardGroupRepository.findAll();
        assertThat(mMarathonRankingRewardGroupList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkContentTypeIsRequired() throws Exception {
        int databaseSizeBeforeTest = mMarathonRankingRewardGroupRepository.findAll().size();
        // set the field null
        mMarathonRankingRewardGroup.setContentType(null);

        // Create the MMarathonRankingRewardGroup, which fails.
        MMarathonRankingRewardGroupDTO mMarathonRankingRewardGroupDTO = mMarathonRankingRewardGroupMapper.toDto(mMarathonRankingRewardGroup);

        restMMarathonRankingRewardGroupMockMvc.perform(post("/api/m-marathon-ranking-reward-groups")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mMarathonRankingRewardGroupDTO)))
            .andExpect(status().isBadRequest());

        List<MMarathonRankingRewardGroup> mMarathonRankingRewardGroupList = mMarathonRankingRewardGroupRepository.findAll();
        assertThat(mMarathonRankingRewardGroupList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkContentAmountIsRequired() throws Exception {
        int databaseSizeBeforeTest = mMarathonRankingRewardGroupRepository.findAll().size();
        // set the field null
        mMarathonRankingRewardGroup.setContentAmount(null);

        // Create the MMarathonRankingRewardGroup, which fails.
        MMarathonRankingRewardGroupDTO mMarathonRankingRewardGroupDTO = mMarathonRankingRewardGroupMapper.toDto(mMarathonRankingRewardGroup);

        restMMarathonRankingRewardGroupMockMvc.perform(post("/api/m-marathon-ranking-reward-groups")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mMarathonRankingRewardGroupDTO)))
            .andExpect(status().isBadRequest());

        List<MMarathonRankingRewardGroup> mMarathonRankingRewardGroupList = mMarathonRankingRewardGroupRepository.findAll();
        assertThat(mMarathonRankingRewardGroupList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllMMarathonRankingRewardGroups() throws Exception {
        // Initialize the database
        mMarathonRankingRewardGroupRepository.saveAndFlush(mMarathonRankingRewardGroup);

        // Get all the mMarathonRankingRewardGroupList
        restMMarathonRankingRewardGroupMockMvc.perform(get("/api/m-marathon-ranking-reward-groups?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(mMarathonRankingRewardGroup.getId().intValue())))
            .andExpect(jsonPath("$.[*].groupId").value(hasItem(DEFAULT_GROUP_ID)))
            .andExpect(jsonPath("$.[*].contentType").value(hasItem(DEFAULT_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].contentId").value(hasItem(DEFAULT_CONTENT_ID)))
            .andExpect(jsonPath("$.[*].contentAmount").value(hasItem(DEFAULT_CONTENT_AMOUNT)));
    }
    
    @Test
    @Transactional
    public void getMMarathonRankingRewardGroup() throws Exception {
        // Initialize the database
        mMarathonRankingRewardGroupRepository.saveAndFlush(mMarathonRankingRewardGroup);

        // Get the mMarathonRankingRewardGroup
        restMMarathonRankingRewardGroupMockMvc.perform(get("/api/m-marathon-ranking-reward-groups/{id}", mMarathonRankingRewardGroup.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(mMarathonRankingRewardGroup.getId().intValue()))
            .andExpect(jsonPath("$.groupId").value(DEFAULT_GROUP_ID))
            .andExpect(jsonPath("$.contentType").value(DEFAULT_CONTENT_TYPE))
            .andExpect(jsonPath("$.contentId").value(DEFAULT_CONTENT_ID))
            .andExpect(jsonPath("$.contentAmount").value(DEFAULT_CONTENT_AMOUNT));
    }

    @Test
    @Transactional
    public void getAllMMarathonRankingRewardGroupsByGroupIdIsEqualToSomething() throws Exception {
        // Initialize the database
        mMarathonRankingRewardGroupRepository.saveAndFlush(mMarathonRankingRewardGroup);

        // Get all the mMarathonRankingRewardGroupList where groupId equals to DEFAULT_GROUP_ID
        defaultMMarathonRankingRewardGroupShouldBeFound("groupId.equals=" + DEFAULT_GROUP_ID);

        // Get all the mMarathonRankingRewardGroupList where groupId equals to UPDATED_GROUP_ID
        defaultMMarathonRankingRewardGroupShouldNotBeFound("groupId.equals=" + UPDATED_GROUP_ID);
    }

    @Test
    @Transactional
    public void getAllMMarathonRankingRewardGroupsByGroupIdIsInShouldWork() throws Exception {
        // Initialize the database
        mMarathonRankingRewardGroupRepository.saveAndFlush(mMarathonRankingRewardGroup);

        // Get all the mMarathonRankingRewardGroupList where groupId in DEFAULT_GROUP_ID or UPDATED_GROUP_ID
        defaultMMarathonRankingRewardGroupShouldBeFound("groupId.in=" + DEFAULT_GROUP_ID + "," + UPDATED_GROUP_ID);

        // Get all the mMarathonRankingRewardGroupList where groupId equals to UPDATED_GROUP_ID
        defaultMMarathonRankingRewardGroupShouldNotBeFound("groupId.in=" + UPDATED_GROUP_ID);
    }

    @Test
    @Transactional
    public void getAllMMarathonRankingRewardGroupsByGroupIdIsNullOrNotNull() throws Exception {
        // Initialize the database
        mMarathonRankingRewardGroupRepository.saveAndFlush(mMarathonRankingRewardGroup);

        // Get all the mMarathonRankingRewardGroupList where groupId is not null
        defaultMMarathonRankingRewardGroupShouldBeFound("groupId.specified=true");

        // Get all the mMarathonRankingRewardGroupList where groupId is null
        defaultMMarathonRankingRewardGroupShouldNotBeFound("groupId.specified=false");
    }

    @Test
    @Transactional
    public void getAllMMarathonRankingRewardGroupsByGroupIdIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mMarathonRankingRewardGroupRepository.saveAndFlush(mMarathonRankingRewardGroup);

        // Get all the mMarathonRankingRewardGroupList where groupId greater than or equals to DEFAULT_GROUP_ID
        defaultMMarathonRankingRewardGroupShouldBeFound("groupId.greaterOrEqualThan=" + DEFAULT_GROUP_ID);

        // Get all the mMarathonRankingRewardGroupList where groupId greater than or equals to UPDATED_GROUP_ID
        defaultMMarathonRankingRewardGroupShouldNotBeFound("groupId.greaterOrEqualThan=" + UPDATED_GROUP_ID);
    }

    @Test
    @Transactional
    public void getAllMMarathonRankingRewardGroupsByGroupIdIsLessThanSomething() throws Exception {
        // Initialize the database
        mMarathonRankingRewardGroupRepository.saveAndFlush(mMarathonRankingRewardGroup);

        // Get all the mMarathonRankingRewardGroupList where groupId less than or equals to DEFAULT_GROUP_ID
        defaultMMarathonRankingRewardGroupShouldNotBeFound("groupId.lessThan=" + DEFAULT_GROUP_ID);

        // Get all the mMarathonRankingRewardGroupList where groupId less than or equals to UPDATED_GROUP_ID
        defaultMMarathonRankingRewardGroupShouldBeFound("groupId.lessThan=" + UPDATED_GROUP_ID);
    }


    @Test
    @Transactional
    public void getAllMMarathonRankingRewardGroupsByContentTypeIsEqualToSomething() throws Exception {
        // Initialize the database
        mMarathonRankingRewardGroupRepository.saveAndFlush(mMarathonRankingRewardGroup);

        // Get all the mMarathonRankingRewardGroupList where contentType equals to DEFAULT_CONTENT_TYPE
        defaultMMarathonRankingRewardGroupShouldBeFound("contentType.equals=" + DEFAULT_CONTENT_TYPE);

        // Get all the mMarathonRankingRewardGroupList where contentType equals to UPDATED_CONTENT_TYPE
        defaultMMarathonRankingRewardGroupShouldNotBeFound("contentType.equals=" + UPDATED_CONTENT_TYPE);
    }

    @Test
    @Transactional
    public void getAllMMarathonRankingRewardGroupsByContentTypeIsInShouldWork() throws Exception {
        // Initialize the database
        mMarathonRankingRewardGroupRepository.saveAndFlush(mMarathonRankingRewardGroup);

        // Get all the mMarathonRankingRewardGroupList where contentType in DEFAULT_CONTENT_TYPE or UPDATED_CONTENT_TYPE
        defaultMMarathonRankingRewardGroupShouldBeFound("contentType.in=" + DEFAULT_CONTENT_TYPE + "," + UPDATED_CONTENT_TYPE);

        // Get all the mMarathonRankingRewardGroupList where contentType equals to UPDATED_CONTENT_TYPE
        defaultMMarathonRankingRewardGroupShouldNotBeFound("contentType.in=" + UPDATED_CONTENT_TYPE);
    }

    @Test
    @Transactional
    public void getAllMMarathonRankingRewardGroupsByContentTypeIsNullOrNotNull() throws Exception {
        // Initialize the database
        mMarathonRankingRewardGroupRepository.saveAndFlush(mMarathonRankingRewardGroup);

        // Get all the mMarathonRankingRewardGroupList where contentType is not null
        defaultMMarathonRankingRewardGroupShouldBeFound("contentType.specified=true");

        // Get all the mMarathonRankingRewardGroupList where contentType is null
        defaultMMarathonRankingRewardGroupShouldNotBeFound("contentType.specified=false");
    }

    @Test
    @Transactional
    public void getAllMMarathonRankingRewardGroupsByContentTypeIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mMarathonRankingRewardGroupRepository.saveAndFlush(mMarathonRankingRewardGroup);

        // Get all the mMarathonRankingRewardGroupList where contentType greater than or equals to DEFAULT_CONTENT_TYPE
        defaultMMarathonRankingRewardGroupShouldBeFound("contentType.greaterOrEqualThan=" + DEFAULT_CONTENT_TYPE);

        // Get all the mMarathonRankingRewardGroupList where contentType greater than or equals to UPDATED_CONTENT_TYPE
        defaultMMarathonRankingRewardGroupShouldNotBeFound("contentType.greaterOrEqualThan=" + UPDATED_CONTENT_TYPE);
    }

    @Test
    @Transactional
    public void getAllMMarathonRankingRewardGroupsByContentTypeIsLessThanSomething() throws Exception {
        // Initialize the database
        mMarathonRankingRewardGroupRepository.saveAndFlush(mMarathonRankingRewardGroup);

        // Get all the mMarathonRankingRewardGroupList where contentType less than or equals to DEFAULT_CONTENT_TYPE
        defaultMMarathonRankingRewardGroupShouldNotBeFound("contentType.lessThan=" + DEFAULT_CONTENT_TYPE);

        // Get all the mMarathonRankingRewardGroupList where contentType less than or equals to UPDATED_CONTENT_TYPE
        defaultMMarathonRankingRewardGroupShouldBeFound("contentType.lessThan=" + UPDATED_CONTENT_TYPE);
    }


    @Test
    @Transactional
    public void getAllMMarathonRankingRewardGroupsByContentIdIsEqualToSomething() throws Exception {
        // Initialize the database
        mMarathonRankingRewardGroupRepository.saveAndFlush(mMarathonRankingRewardGroup);

        // Get all the mMarathonRankingRewardGroupList where contentId equals to DEFAULT_CONTENT_ID
        defaultMMarathonRankingRewardGroupShouldBeFound("contentId.equals=" + DEFAULT_CONTENT_ID);

        // Get all the mMarathonRankingRewardGroupList where contentId equals to UPDATED_CONTENT_ID
        defaultMMarathonRankingRewardGroupShouldNotBeFound("contentId.equals=" + UPDATED_CONTENT_ID);
    }

    @Test
    @Transactional
    public void getAllMMarathonRankingRewardGroupsByContentIdIsInShouldWork() throws Exception {
        // Initialize the database
        mMarathonRankingRewardGroupRepository.saveAndFlush(mMarathonRankingRewardGroup);

        // Get all the mMarathonRankingRewardGroupList where contentId in DEFAULT_CONTENT_ID or UPDATED_CONTENT_ID
        defaultMMarathonRankingRewardGroupShouldBeFound("contentId.in=" + DEFAULT_CONTENT_ID + "," + UPDATED_CONTENT_ID);

        // Get all the mMarathonRankingRewardGroupList where contentId equals to UPDATED_CONTENT_ID
        defaultMMarathonRankingRewardGroupShouldNotBeFound("contentId.in=" + UPDATED_CONTENT_ID);
    }

    @Test
    @Transactional
    public void getAllMMarathonRankingRewardGroupsByContentIdIsNullOrNotNull() throws Exception {
        // Initialize the database
        mMarathonRankingRewardGroupRepository.saveAndFlush(mMarathonRankingRewardGroup);

        // Get all the mMarathonRankingRewardGroupList where contentId is not null
        defaultMMarathonRankingRewardGroupShouldBeFound("contentId.specified=true");

        // Get all the mMarathonRankingRewardGroupList where contentId is null
        defaultMMarathonRankingRewardGroupShouldNotBeFound("contentId.specified=false");
    }

    @Test
    @Transactional
    public void getAllMMarathonRankingRewardGroupsByContentIdIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mMarathonRankingRewardGroupRepository.saveAndFlush(mMarathonRankingRewardGroup);

        // Get all the mMarathonRankingRewardGroupList where contentId greater than or equals to DEFAULT_CONTENT_ID
        defaultMMarathonRankingRewardGroupShouldBeFound("contentId.greaterOrEqualThan=" + DEFAULT_CONTENT_ID);

        // Get all the mMarathonRankingRewardGroupList where contentId greater than or equals to UPDATED_CONTENT_ID
        defaultMMarathonRankingRewardGroupShouldNotBeFound("contentId.greaterOrEqualThan=" + UPDATED_CONTENT_ID);
    }

    @Test
    @Transactional
    public void getAllMMarathonRankingRewardGroupsByContentIdIsLessThanSomething() throws Exception {
        // Initialize the database
        mMarathonRankingRewardGroupRepository.saveAndFlush(mMarathonRankingRewardGroup);

        // Get all the mMarathonRankingRewardGroupList where contentId less than or equals to DEFAULT_CONTENT_ID
        defaultMMarathonRankingRewardGroupShouldNotBeFound("contentId.lessThan=" + DEFAULT_CONTENT_ID);

        // Get all the mMarathonRankingRewardGroupList where contentId less than or equals to UPDATED_CONTENT_ID
        defaultMMarathonRankingRewardGroupShouldBeFound("contentId.lessThan=" + UPDATED_CONTENT_ID);
    }


    @Test
    @Transactional
    public void getAllMMarathonRankingRewardGroupsByContentAmountIsEqualToSomething() throws Exception {
        // Initialize the database
        mMarathonRankingRewardGroupRepository.saveAndFlush(mMarathonRankingRewardGroup);

        // Get all the mMarathonRankingRewardGroupList where contentAmount equals to DEFAULT_CONTENT_AMOUNT
        defaultMMarathonRankingRewardGroupShouldBeFound("contentAmount.equals=" + DEFAULT_CONTENT_AMOUNT);

        // Get all the mMarathonRankingRewardGroupList where contentAmount equals to UPDATED_CONTENT_AMOUNT
        defaultMMarathonRankingRewardGroupShouldNotBeFound("contentAmount.equals=" + UPDATED_CONTENT_AMOUNT);
    }

    @Test
    @Transactional
    public void getAllMMarathonRankingRewardGroupsByContentAmountIsInShouldWork() throws Exception {
        // Initialize the database
        mMarathonRankingRewardGroupRepository.saveAndFlush(mMarathonRankingRewardGroup);

        // Get all the mMarathonRankingRewardGroupList where contentAmount in DEFAULT_CONTENT_AMOUNT or UPDATED_CONTENT_AMOUNT
        defaultMMarathonRankingRewardGroupShouldBeFound("contentAmount.in=" + DEFAULT_CONTENT_AMOUNT + "," + UPDATED_CONTENT_AMOUNT);

        // Get all the mMarathonRankingRewardGroupList where contentAmount equals to UPDATED_CONTENT_AMOUNT
        defaultMMarathonRankingRewardGroupShouldNotBeFound("contentAmount.in=" + UPDATED_CONTENT_AMOUNT);
    }

    @Test
    @Transactional
    public void getAllMMarathonRankingRewardGroupsByContentAmountIsNullOrNotNull() throws Exception {
        // Initialize the database
        mMarathonRankingRewardGroupRepository.saveAndFlush(mMarathonRankingRewardGroup);

        // Get all the mMarathonRankingRewardGroupList where contentAmount is not null
        defaultMMarathonRankingRewardGroupShouldBeFound("contentAmount.specified=true");

        // Get all the mMarathonRankingRewardGroupList where contentAmount is null
        defaultMMarathonRankingRewardGroupShouldNotBeFound("contentAmount.specified=false");
    }

    @Test
    @Transactional
    public void getAllMMarathonRankingRewardGroupsByContentAmountIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mMarathonRankingRewardGroupRepository.saveAndFlush(mMarathonRankingRewardGroup);

        // Get all the mMarathonRankingRewardGroupList where contentAmount greater than or equals to DEFAULT_CONTENT_AMOUNT
        defaultMMarathonRankingRewardGroupShouldBeFound("contentAmount.greaterOrEqualThan=" + DEFAULT_CONTENT_AMOUNT);

        // Get all the mMarathonRankingRewardGroupList where contentAmount greater than or equals to UPDATED_CONTENT_AMOUNT
        defaultMMarathonRankingRewardGroupShouldNotBeFound("contentAmount.greaterOrEqualThan=" + UPDATED_CONTENT_AMOUNT);
    }

    @Test
    @Transactional
    public void getAllMMarathonRankingRewardGroupsByContentAmountIsLessThanSomething() throws Exception {
        // Initialize the database
        mMarathonRankingRewardGroupRepository.saveAndFlush(mMarathonRankingRewardGroup);

        // Get all the mMarathonRankingRewardGroupList where contentAmount less than or equals to DEFAULT_CONTENT_AMOUNT
        defaultMMarathonRankingRewardGroupShouldNotBeFound("contentAmount.lessThan=" + DEFAULT_CONTENT_AMOUNT);

        // Get all the mMarathonRankingRewardGroupList where contentAmount less than or equals to UPDATED_CONTENT_AMOUNT
        defaultMMarathonRankingRewardGroupShouldBeFound("contentAmount.lessThan=" + UPDATED_CONTENT_AMOUNT);
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultMMarathonRankingRewardGroupShouldBeFound(String filter) throws Exception {
        restMMarathonRankingRewardGroupMockMvc.perform(get("/api/m-marathon-ranking-reward-groups?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(mMarathonRankingRewardGroup.getId().intValue())))
            .andExpect(jsonPath("$.[*].groupId").value(hasItem(DEFAULT_GROUP_ID)))
            .andExpect(jsonPath("$.[*].contentType").value(hasItem(DEFAULT_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].contentId").value(hasItem(DEFAULT_CONTENT_ID)))
            .andExpect(jsonPath("$.[*].contentAmount").value(hasItem(DEFAULT_CONTENT_AMOUNT)));

        // Check, that the count call also returns 1
        restMMarathonRankingRewardGroupMockMvc.perform(get("/api/m-marathon-ranking-reward-groups/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultMMarathonRankingRewardGroupShouldNotBeFound(String filter) throws Exception {
        restMMarathonRankingRewardGroupMockMvc.perform(get("/api/m-marathon-ranking-reward-groups?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restMMarathonRankingRewardGroupMockMvc.perform(get("/api/m-marathon-ranking-reward-groups/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("0"));
    }


    @Test
    @Transactional
    public void getNonExistingMMarathonRankingRewardGroup() throws Exception {
        // Get the mMarathonRankingRewardGroup
        restMMarathonRankingRewardGroupMockMvc.perform(get("/api/m-marathon-ranking-reward-groups/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateMMarathonRankingRewardGroup() throws Exception {
        // Initialize the database
        mMarathonRankingRewardGroupRepository.saveAndFlush(mMarathonRankingRewardGroup);

        int databaseSizeBeforeUpdate = mMarathonRankingRewardGroupRepository.findAll().size();

        // Update the mMarathonRankingRewardGroup
        MMarathonRankingRewardGroup updatedMMarathonRankingRewardGroup = mMarathonRankingRewardGroupRepository.findById(mMarathonRankingRewardGroup.getId()).get();
        // Disconnect from session so that the updates on updatedMMarathonRankingRewardGroup are not directly saved in db
        em.detach(updatedMMarathonRankingRewardGroup);
        updatedMMarathonRankingRewardGroup
            .groupId(UPDATED_GROUP_ID)
            .contentType(UPDATED_CONTENT_TYPE)
            .contentId(UPDATED_CONTENT_ID)
            .contentAmount(UPDATED_CONTENT_AMOUNT);
        MMarathonRankingRewardGroupDTO mMarathonRankingRewardGroupDTO = mMarathonRankingRewardGroupMapper.toDto(updatedMMarathonRankingRewardGroup);

        restMMarathonRankingRewardGroupMockMvc.perform(put("/api/m-marathon-ranking-reward-groups")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mMarathonRankingRewardGroupDTO)))
            .andExpect(status().isOk());

        // Validate the MMarathonRankingRewardGroup in the database
        List<MMarathonRankingRewardGroup> mMarathonRankingRewardGroupList = mMarathonRankingRewardGroupRepository.findAll();
        assertThat(mMarathonRankingRewardGroupList).hasSize(databaseSizeBeforeUpdate);
        MMarathonRankingRewardGroup testMMarathonRankingRewardGroup = mMarathonRankingRewardGroupList.get(mMarathonRankingRewardGroupList.size() - 1);
        assertThat(testMMarathonRankingRewardGroup.getGroupId()).isEqualTo(UPDATED_GROUP_ID);
        assertThat(testMMarathonRankingRewardGroup.getContentType()).isEqualTo(UPDATED_CONTENT_TYPE);
        assertThat(testMMarathonRankingRewardGroup.getContentId()).isEqualTo(UPDATED_CONTENT_ID);
        assertThat(testMMarathonRankingRewardGroup.getContentAmount()).isEqualTo(UPDATED_CONTENT_AMOUNT);
    }

    @Test
    @Transactional
    public void updateNonExistingMMarathonRankingRewardGroup() throws Exception {
        int databaseSizeBeforeUpdate = mMarathonRankingRewardGroupRepository.findAll().size();

        // Create the MMarathonRankingRewardGroup
        MMarathonRankingRewardGroupDTO mMarathonRankingRewardGroupDTO = mMarathonRankingRewardGroupMapper.toDto(mMarathonRankingRewardGroup);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restMMarathonRankingRewardGroupMockMvc.perform(put("/api/m-marathon-ranking-reward-groups")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mMarathonRankingRewardGroupDTO)))
            .andExpect(status().isBadRequest());

        // Validate the MMarathonRankingRewardGroup in the database
        List<MMarathonRankingRewardGroup> mMarathonRankingRewardGroupList = mMarathonRankingRewardGroupRepository.findAll();
        assertThat(mMarathonRankingRewardGroupList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteMMarathonRankingRewardGroup() throws Exception {
        // Initialize the database
        mMarathonRankingRewardGroupRepository.saveAndFlush(mMarathonRankingRewardGroup);

        int databaseSizeBeforeDelete = mMarathonRankingRewardGroupRepository.findAll().size();

        // Delete the mMarathonRankingRewardGroup
        restMMarathonRankingRewardGroupMockMvc.perform(delete("/api/m-marathon-ranking-reward-groups/{id}", mMarathonRankingRewardGroup.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database is empty
        List<MMarathonRankingRewardGroup> mMarathonRankingRewardGroupList = mMarathonRankingRewardGroupRepository.findAll();
        assertThat(mMarathonRankingRewardGroupList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(MMarathonRankingRewardGroup.class);
        MMarathonRankingRewardGroup mMarathonRankingRewardGroup1 = new MMarathonRankingRewardGroup();
        mMarathonRankingRewardGroup1.setId(1L);
        MMarathonRankingRewardGroup mMarathonRankingRewardGroup2 = new MMarathonRankingRewardGroup();
        mMarathonRankingRewardGroup2.setId(mMarathonRankingRewardGroup1.getId());
        assertThat(mMarathonRankingRewardGroup1).isEqualTo(mMarathonRankingRewardGroup2);
        mMarathonRankingRewardGroup2.setId(2L);
        assertThat(mMarathonRankingRewardGroup1).isNotEqualTo(mMarathonRankingRewardGroup2);
        mMarathonRankingRewardGroup1.setId(null);
        assertThat(mMarathonRankingRewardGroup1).isNotEqualTo(mMarathonRankingRewardGroup2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(MMarathonRankingRewardGroupDTO.class);
        MMarathonRankingRewardGroupDTO mMarathonRankingRewardGroupDTO1 = new MMarathonRankingRewardGroupDTO();
        mMarathonRankingRewardGroupDTO1.setId(1L);
        MMarathonRankingRewardGroupDTO mMarathonRankingRewardGroupDTO2 = new MMarathonRankingRewardGroupDTO();
        assertThat(mMarathonRankingRewardGroupDTO1).isNotEqualTo(mMarathonRankingRewardGroupDTO2);
        mMarathonRankingRewardGroupDTO2.setId(mMarathonRankingRewardGroupDTO1.getId());
        assertThat(mMarathonRankingRewardGroupDTO1).isEqualTo(mMarathonRankingRewardGroupDTO2);
        mMarathonRankingRewardGroupDTO2.setId(2L);
        assertThat(mMarathonRankingRewardGroupDTO1).isNotEqualTo(mMarathonRankingRewardGroupDTO2);
        mMarathonRankingRewardGroupDTO1.setId(null);
        assertThat(mMarathonRankingRewardGroupDTO1).isNotEqualTo(mMarathonRankingRewardGroupDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(mMarathonRankingRewardGroupMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(mMarathonRankingRewardGroupMapper.fromId(null)).isNull();
    }
}
