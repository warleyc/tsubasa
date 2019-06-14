package io.shm.tsubasa.web.rest;

import io.shm.tsubasa.TsubasaApp;
import io.shm.tsubasa.domain.MLeagueRankingRewardGroup;
import io.shm.tsubasa.repository.MLeagueRankingRewardGroupRepository;
import io.shm.tsubasa.service.MLeagueRankingRewardGroupService;
import io.shm.tsubasa.service.dto.MLeagueRankingRewardGroupDTO;
import io.shm.tsubasa.service.mapper.MLeagueRankingRewardGroupMapper;
import io.shm.tsubasa.web.rest.errors.ExceptionTranslator;
import io.shm.tsubasa.service.dto.MLeagueRankingRewardGroupCriteria;
import io.shm.tsubasa.service.MLeagueRankingRewardGroupQueryService;

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
 * Integration tests for the {@Link MLeagueRankingRewardGroupResource} REST controller.
 */
@SpringBootTest(classes = TsubasaApp.class)
public class MLeagueRankingRewardGroupResourceIT {

    private static final Integer DEFAULT_GROUP_ID = 1;
    private static final Integer UPDATED_GROUP_ID = 2;

    private static final Integer DEFAULT_CONTENT_TYPE = 1;
    private static final Integer UPDATED_CONTENT_TYPE = 2;

    private static final Integer DEFAULT_CONTENT_ID = 1;
    private static final Integer UPDATED_CONTENT_ID = 2;

    private static final Integer DEFAULT_CONTENT_AMOUNT = 1;
    private static final Integer UPDATED_CONTENT_AMOUNT = 2;

    @Autowired
    private MLeagueRankingRewardGroupRepository mLeagueRankingRewardGroupRepository;

    @Autowired
    private MLeagueRankingRewardGroupMapper mLeagueRankingRewardGroupMapper;

    @Autowired
    private MLeagueRankingRewardGroupService mLeagueRankingRewardGroupService;

    @Autowired
    private MLeagueRankingRewardGroupQueryService mLeagueRankingRewardGroupQueryService;

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

    private MockMvc restMLeagueRankingRewardGroupMockMvc;

    private MLeagueRankingRewardGroup mLeagueRankingRewardGroup;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final MLeagueRankingRewardGroupResource mLeagueRankingRewardGroupResource = new MLeagueRankingRewardGroupResource(mLeagueRankingRewardGroupService, mLeagueRankingRewardGroupQueryService);
        this.restMLeagueRankingRewardGroupMockMvc = MockMvcBuilders.standaloneSetup(mLeagueRankingRewardGroupResource)
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
    public static MLeagueRankingRewardGroup createEntity(EntityManager em) {
        MLeagueRankingRewardGroup mLeagueRankingRewardGroup = new MLeagueRankingRewardGroup()
            .groupId(DEFAULT_GROUP_ID)
            .contentType(DEFAULT_CONTENT_TYPE)
            .contentId(DEFAULT_CONTENT_ID)
            .contentAmount(DEFAULT_CONTENT_AMOUNT);
        return mLeagueRankingRewardGroup;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static MLeagueRankingRewardGroup createUpdatedEntity(EntityManager em) {
        MLeagueRankingRewardGroup mLeagueRankingRewardGroup = new MLeagueRankingRewardGroup()
            .groupId(UPDATED_GROUP_ID)
            .contentType(UPDATED_CONTENT_TYPE)
            .contentId(UPDATED_CONTENT_ID)
            .contentAmount(UPDATED_CONTENT_AMOUNT);
        return mLeagueRankingRewardGroup;
    }

    @BeforeEach
    public void initTest() {
        mLeagueRankingRewardGroup = createEntity(em);
    }

    @Test
    @Transactional
    public void createMLeagueRankingRewardGroup() throws Exception {
        int databaseSizeBeforeCreate = mLeagueRankingRewardGroupRepository.findAll().size();

        // Create the MLeagueRankingRewardGroup
        MLeagueRankingRewardGroupDTO mLeagueRankingRewardGroupDTO = mLeagueRankingRewardGroupMapper.toDto(mLeagueRankingRewardGroup);
        restMLeagueRankingRewardGroupMockMvc.perform(post("/api/m-league-ranking-reward-groups")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mLeagueRankingRewardGroupDTO)))
            .andExpect(status().isCreated());

        // Validate the MLeagueRankingRewardGroup in the database
        List<MLeagueRankingRewardGroup> mLeagueRankingRewardGroupList = mLeagueRankingRewardGroupRepository.findAll();
        assertThat(mLeagueRankingRewardGroupList).hasSize(databaseSizeBeforeCreate + 1);
        MLeagueRankingRewardGroup testMLeagueRankingRewardGroup = mLeagueRankingRewardGroupList.get(mLeagueRankingRewardGroupList.size() - 1);
        assertThat(testMLeagueRankingRewardGroup.getGroupId()).isEqualTo(DEFAULT_GROUP_ID);
        assertThat(testMLeagueRankingRewardGroup.getContentType()).isEqualTo(DEFAULT_CONTENT_TYPE);
        assertThat(testMLeagueRankingRewardGroup.getContentId()).isEqualTo(DEFAULT_CONTENT_ID);
        assertThat(testMLeagueRankingRewardGroup.getContentAmount()).isEqualTo(DEFAULT_CONTENT_AMOUNT);
    }

    @Test
    @Transactional
    public void createMLeagueRankingRewardGroupWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = mLeagueRankingRewardGroupRepository.findAll().size();

        // Create the MLeagueRankingRewardGroup with an existing ID
        mLeagueRankingRewardGroup.setId(1L);
        MLeagueRankingRewardGroupDTO mLeagueRankingRewardGroupDTO = mLeagueRankingRewardGroupMapper.toDto(mLeagueRankingRewardGroup);

        // An entity with an existing ID cannot be created, so this API call must fail
        restMLeagueRankingRewardGroupMockMvc.perform(post("/api/m-league-ranking-reward-groups")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mLeagueRankingRewardGroupDTO)))
            .andExpect(status().isBadRequest());

        // Validate the MLeagueRankingRewardGroup in the database
        List<MLeagueRankingRewardGroup> mLeagueRankingRewardGroupList = mLeagueRankingRewardGroupRepository.findAll();
        assertThat(mLeagueRankingRewardGroupList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkGroupIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = mLeagueRankingRewardGroupRepository.findAll().size();
        // set the field null
        mLeagueRankingRewardGroup.setGroupId(null);

        // Create the MLeagueRankingRewardGroup, which fails.
        MLeagueRankingRewardGroupDTO mLeagueRankingRewardGroupDTO = mLeagueRankingRewardGroupMapper.toDto(mLeagueRankingRewardGroup);

        restMLeagueRankingRewardGroupMockMvc.perform(post("/api/m-league-ranking-reward-groups")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mLeagueRankingRewardGroupDTO)))
            .andExpect(status().isBadRequest());

        List<MLeagueRankingRewardGroup> mLeagueRankingRewardGroupList = mLeagueRankingRewardGroupRepository.findAll();
        assertThat(mLeagueRankingRewardGroupList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkContentTypeIsRequired() throws Exception {
        int databaseSizeBeforeTest = mLeagueRankingRewardGroupRepository.findAll().size();
        // set the field null
        mLeagueRankingRewardGroup.setContentType(null);

        // Create the MLeagueRankingRewardGroup, which fails.
        MLeagueRankingRewardGroupDTO mLeagueRankingRewardGroupDTO = mLeagueRankingRewardGroupMapper.toDto(mLeagueRankingRewardGroup);

        restMLeagueRankingRewardGroupMockMvc.perform(post("/api/m-league-ranking-reward-groups")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mLeagueRankingRewardGroupDTO)))
            .andExpect(status().isBadRequest());

        List<MLeagueRankingRewardGroup> mLeagueRankingRewardGroupList = mLeagueRankingRewardGroupRepository.findAll();
        assertThat(mLeagueRankingRewardGroupList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkContentAmountIsRequired() throws Exception {
        int databaseSizeBeforeTest = mLeagueRankingRewardGroupRepository.findAll().size();
        // set the field null
        mLeagueRankingRewardGroup.setContentAmount(null);

        // Create the MLeagueRankingRewardGroup, which fails.
        MLeagueRankingRewardGroupDTO mLeagueRankingRewardGroupDTO = mLeagueRankingRewardGroupMapper.toDto(mLeagueRankingRewardGroup);

        restMLeagueRankingRewardGroupMockMvc.perform(post("/api/m-league-ranking-reward-groups")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mLeagueRankingRewardGroupDTO)))
            .andExpect(status().isBadRequest());

        List<MLeagueRankingRewardGroup> mLeagueRankingRewardGroupList = mLeagueRankingRewardGroupRepository.findAll();
        assertThat(mLeagueRankingRewardGroupList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllMLeagueRankingRewardGroups() throws Exception {
        // Initialize the database
        mLeagueRankingRewardGroupRepository.saveAndFlush(mLeagueRankingRewardGroup);

        // Get all the mLeagueRankingRewardGroupList
        restMLeagueRankingRewardGroupMockMvc.perform(get("/api/m-league-ranking-reward-groups?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(mLeagueRankingRewardGroup.getId().intValue())))
            .andExpect(jsonPath("$.[*].groupId").value(hasItem(DEFAULT_GROUP_ID)))
            .andExpect(jsonPath("$.[*].contentType").value(hasItem(DEFAULT_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].contentId").value(hasItem(DEFAULT_CONTENT_ID)))
            .andExpect(jsonPath("$.[*].contentAmount").value(hasItem(DEFAULT_CONTENT_AMOUNT)));
    }
    
    @Test
    @Transactional
    public void getMLeagueRankingRewardGroup() throws Exception {
        // Initialize the database
        mLeagueRankingRewardGroupRepository.saveAndFlush(mLeagueRankingRewardGroup);

        // Get the mLeagueRankingRewardGroup
        restMLeagueRankingRewardGroupMockMvc.perform(get("/api/m-league-ranking-reward-groups/{id}", mLeagueRankingRewardGroup.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(mLeagueRankingRewardGroup.getId().intValue()))
            .andExpect(jsonPath("$.groupId").value(DEFAULT_GROUP_ID))
            .andExpect(jsonPath("$.contentType").value(DEFAULT_CONTENT_TYPE))
            .andExpect(jsonPath("$.contentId").value(DEFAULT_CONTENT_ID))
            .andExpect(jsonPath("$.contentAmount").value(DEFAULT_CONTENT_AMOUNT));
    }

    @Test
    @Transactional
    public void getAllMLeagueRankingRewardGroupsByGroupIdIsEqualToSomething() throws Exception {
        // Initialize the database
        mLeagueRankingRewardGroupRepository.saveAndFlush(mLeagueRankingRewardGroup);

        // Get all the mLeagueRankingRewardGroupList where groupId equals to DEFAULT_GROUP_ID
        defaultMLeagueRankingRewardGroupShouldBeFound("groupId.equals=" + DEFAULT_GROUP_ID);

        // Get all the mLeagueRankingRewardGroupList where groupId equals to UPDATED_GROUP_ID
        defaultMLeagueRankingRewardGroupShouldNotBeFound("groupId.equals=" + UPDATED_GROUP_ID);
    }

    @Test
    @Transactional
    public void getAllMLeagueRankingRewardGroupsByGroupIdIsInShouldWork() throws Exception {
        // Initialize the database
        mLeagueRankingRewardGroupRepository.saveAndFlush(mLeagueRankingRewardGroup);

        // Get all the mLeagueRankingRewardGroupList where groupId in DEFAULT_GROUP_ID or UPDATED_GROUP_ID
        defaultMLeagueRankingRewardGroupShouldBeFound("groupId.in=" + DEFAULT_GROUP_ID + "," + UPDATED_GROUP_ID);

        // Get all the mLeagueRankingRewardGroupList where groupId equals to UPDATED_GROUP_ID
        defaultMLeagueRankingRewardGroupShouldNotBeFound("groupId.in=" + UPDATED_GROUP_ID);
    }

    @Test
    @Transactional
    public void getAllMLeagueRankingRewardGroupsByGroupIdIsNullOrNotNull() throws Exception {
        // Initialize the database
        mLeagueRankingRewardGroupRepository.saveAndFlush(mLeagueRankingRewardGroup);

        // Get all the mLeagueRankingRewardGroupList where groupId is not null
        defaultMLeagueRankingRewardGroupShouldBeFound("groupId.specified=true");

        // Get all the mLeagueRankingRewardGroupList where groupId is null
        defaultMLeagueRankingRewardGroupShouldNotBeFound("groupId.specified=false");
    }

    @Test
    @Transactional
    public void getAllMLeagueRankingRewardGroupsByGroupIdIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mLeagueRankingRewardGroupRepository.saveAndFlush(mLeagueRankingRewardGroup);

        // Get all the mLeagueRankingRewardGroupList where groupId greater than or equals to DEFAULT_GROUP_ID
        defaultMLeagueRankingRewardGroupShouldBeFound("groupId.greaterOrEqualThan=" + DEFAULT_GROUP_ID);

        // Get all the mLeagueRankingRewardGroupList where groupId greater than or equals to UPDATED_GROUP_ID
        defaultMLeagueRankingRewardGroupShouldNotBeFound("groupId.greaterOrEqualThan=" + UPDATED_GROUP_ID);
    }

    @Test
    @Transactional
    public void getAllMLeagueRankingRewardGroupsByGroupIdIsLessThanSomething() throws Exception {
        // Initialize the database
        mLeagueRankingRewardGroupRepository.saveAndFlush(mLeagueRankingRewardGroup);

        // Get all the mLeagueRankingRewardGroupList where groupId less than or equals to DEFAULT_GROUP_ID
        defaultMLeagueRankingRewardGroupShouldNotBeFound("groupId.lessThan=" + DEFAULT_GROUP_ID);

        // Get all the mLeagueRankingRewardGroupList where groupId less than or equals to UPDATED_GROUP_ID
        defaultMLeagueRankingRewardGroupShouldBeFound("groupId.lessThan=" + UPDATED_GROUP_ID);
    }


    @Test
    @Transactional
    public void getAllMLeagueRankingRewardGroupsByContentTypeIsEqualToSomething() throws Exception {
        // Initialize the database
        mLeagueRankingRewardGroupRepository.saveAndFlush(mLeagueRankingRewardGroup);

        // Get all the mLeagueRankingRewardGroupList where contentType equals to DEFAULT_CONTENT_TYPE
        defaultMLeagueRankingRewardGroupShouldBeFound("contentType.equals=" + DEFAULT_CONTENT_TYPE);

        // Get all the mLeagueRankingRewardGroupList where contentType equals to UPDATED_CONTENT_TYPE
        defaultMLeagueRankingRewardGroupShouldNotBeFound("contentType.equals=" + UPDATED_CONTENT_TYPE);
    }

    @Test
    @Transactional
    public void getAllMLeagueRankingRewardGroupsByContentTypeIsInShouldWork() throws Exception {
        // Initialize the database
        mLeagueRankingRewardGroupRepository.saveAndFlush(mLeagueRankingRewardGroup);

        // Get all the mLeagueRankingRewardGroupList where contentType in DEFAULT_CONTENT_TYPE or UPDATED_CONTENT_TYPE
        defaultMLeagueRankingRewardGroupShouldBeFound("contentType.in=" + DEFAULT_CONTENT_TYPE + "," + UPDATED_CONTENT_TYPE);

        // Get all the mLeagueRankingRewardGroupList where contentType equals to UPDATED_CONTENT_TYPE
        defaultMLeagueRankingRewardGroupShouldNotBeFound("contentType.in=" + UPDATED_CONTENT_TYPE);
    }

    @Test
    @Transactional
    public void getAllMLeagueRankingRewardGroupsByContentTypeIsNullOrNotNull() throws Exception {
        // Initialize the database
        mLeagueRankingRewardGroupRepository.saveAndFlush(mLeagueRankingRewardGroup);

        // Get all the mLeagueRankingRewardGroupList where contentType is not null
        defaultMLeagueRankingRewardGroupShouldBeFound("contentType.specified=true");

        // Get all the mLeagueRankingRewardGroupList where contentType is null
        defaultMLeagueRankingRewardGroupShouldNotBeFound("contentType.specified=false");
    }

    @Test
    @Transactional
    public void getAllMLeagueRankingRewardGroupsByContentTypeIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mLeagueRankingRewardGroupRepository.saveAndFlush(mLeagueRankingRewardGroup);

        // Get all the mLeagueRankingRewardGroupList where contentType greater than or equals to DEFAULT_CONTENT_TYPE
        defaultMLeagueRankingRewardGroupShouldBeFound("contentType.greaterOrEqualThan=" + DEFAULT_CONTENT_TYPE);

        // Get all the mLeagueRankingRewardGroupList where contentType greater than or equals to UPDATED_CONTENT_TYPE
        defaultMLeagueRankingRewardGroupShouldNotBeFound("contentType.greaterOrEqualThan=" + UPDATED_CONTENT_TYPE);
    }

    @Test
    @Transactional
    public void getAllMLeagueRankingRewardGroupsByContentTypeIsLessThanSomething() throws Exception {
        // Initialize the database
        mLeagueRankingRewardGroupRepository.saveAndFlush(mLeagueRankingRewardGroup);

        // Get all the mLeagueRankingRewardGroupList where contentType less than or equals to DEFAULT_CONTENT_TYPE
        defaultMLeagueRankingRewardGroupShouldNotBeFound("contentType.lessThan=" + DEFAULT_CONTENT_TYPE);

        // Get all the mLeagueRankingRewardGroupList where contentType less than or equals to UPDATED_CONTENT_TYPE
        defaultMLeagueRankingRewardGroupShouldBeFound("contentType.lessThan=" + UPDATED_CONTENT_TYPE);
    }


    @Test
    @Transactional
    public void getAllMLeagueRankingRewardGroupsByContentIdIsEqualToSomething() throws Exception {
        // Initialize the database
        mLeagueRankingRewardGroupRepository.saveAndFlush(mLeagueRankingRewardGroup);

        // Get all the mLeagueRankingRewardGroupList where contentId equals to DEFAULT_CONTENT_ID
        defaultMLeagueRankingRewardGroupShouldBeFound("contentId.equals=" + DEFAULT_CONTENT_ID);

        // Get all the mLeagueRankingRewardGroupList where contentId equals to UPDATED_CONTENT_ID
        defaultMLeagueRankingRewardGroupShouldNotBeFound("contentId.equals=" + UPDATED_CONTENT_ID);
    }

    @Test
    @Transactional
    public void getAllMLeagueRankingRewardGroupsByContentIdIsInShouldWork() throws Exception {
        // Initialize the database
        mLeagueRankingRewardGroupRepository.saveAndFlush(mLeagueRankingRewardGroup);

        // Get all the mLeagueRankingRewardGroupList where contentId in DEFAULT_CONTENT_ID or UPDATED_CONTENT_ID
        defaultMLeagueRankingRewardGroupShouldBeFound("contentId.in=" + DEFAULT_CONTENT_ID + "," + UPDATED_CONTENT_ID);

        // Get all the mLeagueRankingRewardGroupList where contentId equals to UPDATED_CONTENT_ID
        defaultMLeagueRankingRewardGroupShouldNotBeFound("contentId.in=" + UPDATED_CONTENT_ID);
    }

    @Test
    @Transactional
    public void getAllMLeagueRankingRewardGroupsByContentIdIsNullOrNotNull() throws Exception {
        // Initialize the database
        mLeagueRankingRewardGroupRepository.saveAndFlush(mLeagueRankingRewardGroup);

        // Get all the mLeagueRankingRewardGroupList where contentId is not null
        defaultMLeagueRankingRewardGroupShouldBeFound("contentId.specified=true");

        // Get all the mLeagueRankingRewardGroupList where contentId is null
        defaultMLeagueRankingRewardGroupShouldNotBeFound("contentId.specified=false");
    }

    @Test
    @Transactional
    public void getAllMLeagueRankingRewardGroupsByContentIdIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mLeagueRankingRewardGroupRepository.saveAndFlush(mLeagueRankingRewardGroup);

        // Get all the mLeagueRankingRewardGroupList where contentId greater than or equals to DEFAULT_CONTENT_ID
        defaultMLeagueRankingRewardGroupShouldBeFound("contentId.greaterOrEqualThan=" + DEFAULT_CONTENT_ID);

        // Get all the mLeagueRankingRewardGroupList where contentId greater than or equals to UPDATED_CONTENT_ID
        defaultMLeagueRankingRewardGroupShouldNotBeFound("contentId.greaterOrEqualThan=" + UPDATED_CONTENT_ID);
    }

    @Test
    @Transactional
    public void getAllMLeagueRankingRewardGroupsByContentIdIsLessThanSomething() throws Exception {
        // Initialize the database
        mLeagueRankingRewardGroupRepository.saveAndFlush(mLeagueRankingRewardGroup);

        // Get all the mLeagueRankingRewardGroupList where contentId less than or equals to DEFAULT_CONTENT_ID
        defaultMLeagueRankingRewardGroupShouldNotBeFound("contentId.lessThan=" + DEFAULT_CONTENT_ID);

        // Get all the mLeagueRankingRewardGroupList where contentId less than or equals to UPDATED_CONTENT_ID
        defaultMLeagueRankingRewardGroupShouldBeFound("contentId.lessThan=" + UPDATED_CONTENT_ID);
    }


    @Test
    @Transactional
    public void getAllMLeagueRankingRewardGroupsByContentAmountIsEqualToSomething() throws Exception {
        // Initialize the database
        mLeagueRankingRewardGroupRepository.saveAndFlush(mLeagueRankingRewardGroup);

        // Get all the mLeagueRankingRewardGroupList where contentAmount equals to DEFAULT_CONTENT_AMOUNT
        defaultMLeagueRankingRewardGroupShouldBeFound("contentAmount.equals=" + DEFAULT_CONTENT_AMOUNT);

        // Get all the mLeagueRankingRewardGroupList where contentAmount equals to UPDATED_CONTENT_AMOUNT
        defaultMLeagueRankingRewardGroupShouldNotBeFound("contentAmount.equals=" + UPDATED_CONTENT_AMOUNT);
    }

    @Test
    @Transactional
    public void getAllMLeagueRankingRewardGroupsByContentAmountIsInShouldWork() throws Exception {
        // Initialize the database
        mLeagueRankingRewardGroupRepository.saveAndFlush(mLeagueRankingRewardGroup);

        // Get all the mLeagueRankingRewardGroupList where contentAmount in DEFAULT_CONTENT_AMOUNT or UPDATED_CONTENT_AMOUNT
        defaultMLeagueRankingRewardGroupShouldBeFound("contentAmount.in=" + DEFAULT_CONTENT_AMOUNT + "," + UPDATED_CONTENT_AMOUNT);

        // Get all the mLeagueRankingRewardGroupList where contentAmount equals to UPDATED_CONTENT_AMOUNT
        defaultMLeagueRankingRewardGroupShouldNotBeFound("contentAmount.in=" + UPDATED_CONTENT_AMOUNT);
    }

    @Test
    @Transactional
    public void getAllMLeagueRankingRewardGroupsByContentAmountIsNullOrNotNull() throws Exception {
        // Initialize the database
        mLeagueRankingRewardGroupRepository.saveAndFlush(mLeagueRankingRewardGroup);

        // Get all the mLeagueRankingRewardGroupList where contentAmount is not null
        defaultMLeagueRankingRewardGroupShouldBeFound("contentAmount.specified=true");

        // Get all the mLeagueRankingRewardGroupList where contentAmount is null
        defaultMLeagueRankingRewardGroupShouldNotBeFound("contentAmount.specified=false");
    }

    @Test
    @Transactional
    public void getAllMLeagueRankingRewardGroupsByContentAmountIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mLeagueRankingRewardGroupRepository.saveAndFlush(mLeagueRankingRewardGroup);

        // Get all the mLeagueRankingRewardGroupList where contentAmount greater than or equals to DEFAULT_CONTENT_AMOUNT
        defaultMLeagueRankingRewardGroupShouldBeFound("contentAmount.greaterOrEqualThan=" + DEFAULT_CONTENT_AMOUNT);

        // Get all the mLeagueRankingRewardGroupList where contentAmount greater than or equals to UPDATED_CONTENT_AMOUNT
        defaultMLeagueRankingRewardGroupShouldNotBeFound("contentAmount.greaterOrEqualThan=" + UPDATED_CONTENT_AMOUNT);
    }

    @Test
    @Transactional
    public void getAllMLeagueRankingRewardGroupsByContentAmountIsLessThanSomething() throws Exception {
        // Initialize the database
        mLeagueRankingRewardGroupRepository.saveAndFlush(mLeagueRankingRewardGroup);

        // Get all the mLeagueRankingRewardGroupList where contentAmount less than or equals to DEFAULT_CONTENT_AMOUNT
        defaultMLeagueRankingRewardGroupShouldNotBeFound("contentAmount.lessThan=" + DEFAULT_CONTENT_AMOUNT);

        // Get all the mLeagueRankingRewardGroupList where contentAmount less than or equals to UPDATED_CONTENT_AMOUNT
        defaultMLeagueRankingRewardGroupShouldBeFound("contentAmount.lessThan=" + UPDATED_CONTENT_AMOUNT);
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultMLeagueRankingRewardGroupShouldBeFound(String filter) throws Exception {
        restMLeagueRankingRewardGroupMockMvc.perform(get("/api/m-league-ranking-reward-groups?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(mLeagueRankingRewardGroup.getId().intValue())))
            .andExpect(jsonPath("$.[*].groupId").value(hasItem(DEFAULT_GROUP_ID)))
            .andExpect(jsonPath("$.[*].contentType").value(hasItem(DEFAULT_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].contentId").value(hasItem(DEFAULT_CONTENT_ID)))
            .andExpect(jsonPath("$.[*].contentAmount").value(hasItem(DEFAULT_CONTENT_AMOUNT)));

        // Check, that the count call also returns 1
        restMLeagueRankingRewardGroupMockMvc.perform(get("/api/m-league-ranking-reward-groups/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultMLeagueRankingRewardGroupShouldNotBeFound(String filter) throws Exception {
        restMLeagueRankingRewardGroupMockMvc.perform(get("/api/m-league-ranking-reward-groups?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restMLeagueRankingRewardGroupMockMvc.perform(get("/api/m-league-ranking-reward-groups/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("0"));
    }


    @Test
    @Transactional
    public void getNonExistingMLeagueRankingRewardGroup() throws Exception {
        // Get the mLeagueRankingRewardGroup
        restMLeagueRankingRewardGroupMockMvc.perform(get("/api/m-league-ranking-reward-groups/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateMLeagueRankingRewardGroup() throws Exception {
        // Initialize the database
        mLeagueRankingRewardGroupRepository.saveAndFlush(mLeagueRankingRewardGroup);

        int databaseSizeBeforeUpdate = mLeagueRankingRewardGroupRepository.findAll().size();

        // Update the mLeagueRankingRewardGroup
        MLeagueRankingRewardGroup updatedMLeagueRankingRewardGroup = mLeagueRankingRewardGroupRepository.findById(mLeagueRankingRewardGroup.getId()).get();
        // Disconnect from session so that the updates on updatedMLeagueRankingRewardGroup are not directly saved in db
        em.detach(updatedMLeagueRankingRewardGroup);
        updatedMLeagueRankingRewardGroup
            .groupId(UPDATED_GROUP_ID)
            .contentType(UPDATED_CONTENT_TYPE)
            .contentId(UPDATED_CONTENT_ID)
            .contentAmount(UPDATED_CONTENT_AMOUNT);
        MLeagueRankingRewardGroupDTO mLeagueRankingRewardGroupDTO = mLeagueRankingRewardGroupMapper.toDto(updatedMLeagueRankingRewardGroup);

        restMLeagueRankingRewardGroupMockMvc.perform(put("/api/m-league-ranking-reward-groups")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mLeagueRankingRewardGroupDTO)))
            .andExpect(status().isOk());

        // Validate the MLeagueRankingRewardGroup in the database
        List<MLeagueRankingRewardGroup> mLeagueRankingRewardGroupList = mLeagueRankingRewardGroupRepository.findAll();
        assertThat(mLeagueRankingRewardGroupList).hasSize(databaseSizeBeforeUpdate);
        MLeagueRankingRewardGroup testMLeagueRankingRewardGroup = mLeagueRankingRewardGroupList.get(mLeagueRankingRewardGroupList.size() - 1);
        assertThat(testMLeagueRankingRewardGroup.getGroupId()).isEqualTo(UPDATED_GROUP_ID);
        assertThat(testMLeagueRankingRewardGroup.getContentType()).isEqualTo(UPDATED_CONTENT_TYPE);
        assertThat(testMLeagueRankingRewardGroup.getContentId()).isEqualTo(UPDATED_CONTENT_ID);
        assertThat(testMLeagueRankingRewardGroup.getContentAmount()).isEqualTo(UPDATED_CONTENT_AMOUNT);
    }

    @Test
    @Transactional
    public void updateNonExistingMLeagueRankingRewardGroup() throws Exception {
        int databaseSizeBeforeUpdate = mLeagueRankingRewardGroupRepository.findAll().size();

        // Create the MLeagueRankingRewardGroup
        MLeagueRankingRewardGroupDTO mLeagueRankingRewardGroupDTO = mLeagueRankingRewardGroupMapper.toDto(mLeagueRankingRewardGroup);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restMLeagueRankingRewardGroupMockMvc.perform(put("/api/m-league-ranking-reward-groups")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mLeagueRankingRewardGroupDTO)))
            .andExpect(status().isBadRequest());

        // Validate the MLeagueRankingRewardGroup in the database
        List<MLeagueRankingRewardGroup> mLeagueRankingRewardGroupList = mLeagueRankingRewardGroupRepository.findAll();
        assertThat(mLeagueRankingRewardGroupList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteMLeagueRankingRewardGroup() throws Exception {
        // Initialize the database
        mLeagueRankingRewardGroupRepository.saveAndFlush(mLeagueRankingRewardGroup);

        int databaseSizeBeforeDelete = mLeagueRankingRewardGroupRepository.findAll().size();

        // Delete the mLeagueRankingRewardGroup
        restMLeagueRankingRewardGroupMockMvc.perform(delete("/api/m-league-ranking-reward-groups/{id}", mLeagueRankingRewardGroup.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database is empty
        List<MLeagueRankingRewardGroup> mLeagueRankingRewardGroupList = mLeagueRankingRewardGroupRepository.findAll();
        assertThat(mLeagueRankingRewardGroupList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(MLeagueRankingRewardGroup.class);
        MLeagueRankingRewardGroup mLeagueRankingRewardGroup1 = new MLeagueRankingRewardGroup();
        mLeagueRankingRewardGroup1.setId(1L);
        MLeagueRankingRewardGroup mLeagueRankingRewardGroup2 = new MLeagueRankingRewardGroup();
        mLeagueRankingRewardGroup2.setId(mLeagueRankingRewardGroup1.getId());
        assertThat(mLeagueRankingRewardGroup1).isEqualTo(mLeagueRankingRewardGroup2);
        mLeagueRankingRewardGroup2.setId(2L);
        assertThat(mLeagueRankingRewardGroup1).isNotEqualTo(mLeagueRankingRewardGroup2);
        mLeagueRankingRewardGroup1.setId(null);
        assertThat(mLeagueRankingRewardGroup1).isNotEqualTo(mLeagueRankingRewardGroup2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(MLeagueRankingRewardGroupDTO.class);
        MLeagueRankingRewardGroupDTO mLeagueRankingRewardGroupDTO1 = new MLeagueRankingRewardGroupDTO();
        mLeagueRankingRewardGroupDTO1.setId(1L);
        MLeagueRankingRewardGroupDTO mLeagueRankingRewardGroupDTO2 = new MLeagueRankingRewardGroupDTO();
        assertThat(mLeagueRankingRewardGroupDTO1).isNotEqualTo(mLeagueRankingRewardGroupDTO2);
        mLeagueRankingRewardGroupDTO2.setId(mLeagueRankingRewardGroupDTO1.getId());
        assertThat(mLeagueRankingRewardGroupDTO1).isEqualTo(mLeagueRankingRewardGroupDTO2);
        mLeagueRankingRewardGroupDTO2.setId(2L);
        assertThat(mLeagueRankingRewardGroupDTO1).isNotEqualTo(mLeagueRankingRewardGroupDTO2);
        mLeagueRankingRewardGroupDTO1.setId(null);
        assertThat(mLeagueRankingRewardGroupDTO1).isNotEqualTo(mLeagueRankingRewardGroupDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(mLeagueRankingRewardGroupMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(mLeagueRankingRewardGroupMapper.fromId(null)).isNull();
    }
}
