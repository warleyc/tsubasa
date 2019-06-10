package io.shm.tsubasa.web.rest;

import io.shm.tsubasa.TsubasaApp;
import io.shm.tsubasa.domain.MChallengeQuestAchievementRewardGroup;
import io.shm.tsubasa.repository.MChallengeQuestAchievementRewardGroupRepository;
import io.shm.tsubasa.service.MChallengeQuestAchievementRewardGroupService;
import io.shm.tsubasa.service.dto.MChallengeQuestAchievementRewardGroupDTO;
import io.shm.tsubasa.service.mapper.MChallengeQuestAchievementRewardGroupMapper;
import io.shm.tsubasa.web.rest.errors.ExceptionTranslator;
import io.shm.tsubasa.service.dto.MChallengeQuestAchievementRewardGroupCriteria;
import io.shm.tsubasa.service.MChallengeQuestAchievementRewardGroupQueryService;

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
 * Integration tests for the {@Link MChallengeQuestAchievementRewardGroupResource} REST controller.
 */
@SpringBootTest(classes = TsubasaApp.class)
public class MChallengeQuestAchievementRewardGroupResourceIT {

    private static final Integer DEFAULT_GROUP_ID = 1;
    private static final Integer UPDATED_GROUP_ID = 2;

    private static final Integer DEFAULT_CONTENT_TYPE = 1;
    private static final Integer UPDATED_CONTENT_TYPE = 2;

    private static final Integer DEFAULT_CONTENT_ID = 1;
    private static final Integer UPDATED_CONTENT_ID = 2;

    private static final Integer DEFAULT_CONTENT_AMOUNT = 1;
    private static final Integer UPDATED_CONTENT_AMOUNT = 2;

    @Autowired
    private MChallengeQuestAchievementRewardGroupRepository mChallengeQuestAchievementRewardGroupRepository;

    @Autowired
    private MChallengeQuestAchievementRewardGroupMapper mChallengeQuestAchievementRewardGroupMapper;

    @Autowired
    private MChallengeQuestAchievementRewardGroupService mChallengeQuestAchievementRewardGroupService;

    @Autowired
    private MChallengeQuestAchievementRewardGroupQueryService mChallengeQuestAchievementRewardGroupQueryService;

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

    private MockMvc restMChallengeQuestAchievementRewardGroupMockMvc;

    private MChallengeQuestAchievementRewardGroup mChallengeQuestAchievementRewardGroup;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final MChallengeQuestAchievementRewardGroupResource mChallengeQuestAchievementRewardGroupResource = new MChallengeQuestAchievementRewardGroupResource(mChallengeQuestAchievementRewardGroupService, mChallengeQuestAchievementRewardGroupQueryService);
        this.restMChallengeQuestAchievementRewardGroupMockMvc = MockMvcBuilders.standaloneSetup(mChallengeQuestAchievementRewardGroupResource)
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
    public static MChallengeQuestAchievementRewardGroup createEntity(EntityManager em) {
        MChallengeQuestAchievementRewardGroup mChallengeQuestAchievementRewardGroup = new MChallengeQuestAchievementRewardGroup()
            .groupId(DEFAULT_GROUP_ID)
            .contentType(DEFAULT_CONTENT_TYPE)
            .contentId(DEFAULT_CONTENT_ID)
            .contentAmount(DEFAULT_CONTENT_AMOUNT);
        return mChallengeQuestAchievementRewardGroup;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static MChallengeQuestAchievementRewardGroup createUpdatedEntity(EntityManager em) {
        MChallengeQuestAchievementRewardGroup mChallengeQuestAchievementRewardGroup = new MChallengeQuestAchievementRewardGroup()
            .groupId(UPDATED_GROUP_ID)
            .contentType(UPDATED_CONTENT_TYPE)
            .contentId(UPDATED_CONTENT_ID)
            .contentAmount(UPDATED_CONTENT_AMOUNT);
        return mChallengeQuestAchievementRewardGroup;
    }

    @BeforeEach
    public void initTest() {
        mChallengeQuestAchievementRewardGroup = createEntity(em);
    }

    @Test
    @Transactional
    public void createMChallengeQuestAchievementRewardGroup() throws Exception {
        int databaseSizeBeforeCreate = mChallengeQuestAchievementRewardGroupRepository.findAll().size();

        // Create the MChallengeQuestAchievementRewardGroup
        MChallengeQuestAchievementRewardGroupDTO mChallengeQuestAchievementRewardGroupDTO = mChallengeQuestAchievementRewardGroupMapper.toDto(mChallengeQuestAchievementRewardGroup);
        restMChallengeQuestAchievementRewardGroupMockMvc.perform(post("/api/m-challenge-quest-achievement-reward-groups")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mChallengeQuestAchievementRewardGroupDTO)))
            .andExpect(status().isCreated());

        // Validate the MChallengeQuestAchievementRewardGroup in the database
        List<MChallengeQuestAchievementRewardGroup> mChallengeQuestAchievementRewardGroupList = mChallengeQuestAchievementRewardGroupRepository.findAll();
        assertThat(mChallengeQuestAchievementRewardGroupList).hasSize(databaseSizeBeforeCreate + 1);
        MChallengeQuestAchievementRewardGroup testMChallengeQuestAchievementRewardGroup = mChallengeQuestAchievementRewardGroupList.get(mChallengeQuestAchievementRewardGroupList.size() - 1);
        assertThat(testMChallengeQuestAchievementRewardGroup.getGroupId()).isEqualTo(DEFAULT_GROUP_ID);
        assertThat(testMChallengeQuestAchievementRewardGroup.getContentType()).isEqualTo(DEFAULT_CONTENT_TYPE);
        assertThat(testMChallengeQuestAchievementRewardGroup.getContentId()).isEqualTo(DEFAULT_CONTENT_ID);
        assertThat(testMChallengeQuestAchievementRewardGroup.getContentAmount()).isEqualTo(DEFAULT_CONTENT_AMOUNT);
    }

    @Test
    @Transactional
    public void createMChallengeQuestAchievementRewardGroupWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = mChallengeQuestAchievementRewardGroupRepository.findAll().size();

        // Create the MChallengeQuestAchievementRewardGroup with an existing ID
        mChallengeQuestAchievementRewardGroup.setId(1L);
        MChallengeQuestAchievementRewardGroupDTO mChallengeQuestAchievementRewardGroupDTO = mChallengeQuestAchievementRewardGroupMapper.toDto(mChallengeQuestAchievementRewardGroup);

        // An entity with an existing ID cannot be created, so this API call must fail
        restMChallengeQuestAchievementRewardGroupMockMvc.perform(post("/api/m-challenge-quest-achievement-reward-groups")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mChallengeQuestAchievementRewardGroupDTO)))
            .andExpect(status().isBadRequest());

        // Validate the MChallengeQuestAchievementRewardGroup in the database
        List<MChallengeQuestAchievementRewardGroup> mChallengeQuestAchievementRewardGroupList = mChallengeQuestAchievementRewardGroupRepository.findAll();
        assertThat(mChallengeQuestAchievementRewardGroupList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkGroupIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = mChallengeQuestAchievementRewardGroupRepository.findAll().size();
        // set the field null
        mChallengeQuestAchievementRewardGroup.setGroupId(null);

        // Create the MChallengeQuestAchievementRewardGroup, which fails.
        MChallengeQuestAchievementRewardGroupDTO mChallengeQuestAchievementRewardGroupDTO = mChallengeQuestAchievementRewardGroupMapper.toDto(mChallengeQuestAchievementRewardGroup);

        restMChallengeQuestAchievementRewardGroupMockMvc.perform(post("/api/m-challenge-quest-achievement-reward-groups")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mChallengeQuestAchievementRewardGroupDTO)))
            .andExpect(status().isBadRequest());

        List<MChallengeQuestAchievementRewardGroup> mChallengeQuestAchievementRewardGroupList = mChallengeQuestAchievementRewardGroupRepository.findAll();
        assertThat(mChallengeQuestAchievementRewardGroupList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkContentTypeIsRequired() throws Exception {
        int databaseSizeBeforeTest = mChallengeQuestAchievementRewardGroupRepository.findAll().size();
        // set the field null
        mChallengeQuestAchievementRewardGroup.setContentType(null);

        // Create the MChallengeQuestAchievementRewardGroup, which fails.
        MChallengeQuestAchievementRewardGroupDTO mChallengeQuestAchievementRewardGroupDTO = mChallengeQuestAchievementRewardGroupMapper.toDto(mChallengeQuestAchievementRewardGroup);

        restMChallengeQuestAchievementRewardGroupMockMvc.perform(post("/api/m-challenge-quest-achievement-reward-groups")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mChallengeQuestAchievementRewardGroupDTO)))
            .andExpect(status().isBadRequest());

        List<MChallengeQuestAchievementRewardGroup> mChallengeQuestAchievementRewardGroupList = mChallengeQuestAchievementRewardGroupRepository.findAll();
        assertThat(mChallengeQuestAchievementRewardGroupList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkContentAmountIsRequired() throws Exception {
        int databaseSizeBeforeTest = mChallengeQuestAchievementRewardGroupRepository.findAll().size();
        // set the field null
        mChallengeQuestAchievementRewardGroup.setContentAmount(null);

        // Create the MChallengeQuestAchievementRewardGroup, which fails.
        MChallengeQuestAchievementRewardGroupDTO mChallengeQuestAchievementRewardGroupDTO = mChallengeQuestAchievementRewardGroupMapper.toDto(mChallengeQuestAchievementRewardGroup);

        restMChallengeQuestAchievementRewardGroupMockMvc.perform(post("/api/m-challenge-quest-achievement-reward-groups")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mChallengeQuestAchievementRewardGroupDTO)))
            .andExpect(status().isBadRequest());

        List<MChallengeQuestAchievementRewardGroup> mChallengeQuestAchievementRewardGroupList = mChallengeQuestAchievementRewardGroupRepository.findAll();
        assertThat(mChallengeQuestAchievementRewardGroupList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllMChallengeQuestAchievementRewardGroups() throws Exception {
        // Initialize the database
        mChallengeQuestAchievementRewardGroupRepository.saveAndFlush(mChallengeQuestAchievementRewardGroup);

        // Get all the mChallengeQuestAchievementRewardGroupList
        restMChallengeQuestAchievementRewardGroupMockMvc.perform(get("/api/m-challenge-quest-achievement-reward-groups?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(mChallengeQuestAchievementRewardGroup.getId().intValue())))
            .andExpect(jsonPath("$.[*].groupId").value(hasItem(DEFAULT_GROUP_ID)))
            .andExpect(jsonPath("$.[*].contentType").value(hasItem(DEFAULT_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].contentId").value(hasItem(DEFAULT_CONTENT_ID)))
            .andExpect(jsonPath("$.[*].contentAmount").value(hasItem(DEFAULT_CONTENT_AMOUNT)));
    }
    
    @Test
    @Transactional
    public void getMChallengeQuestAchievementRewardGroup() throws Exception {
        // Initialize the database
        mChallengeQuestAchievementRewardGroupRepository.saveAndFlush(mChallengeQuestAchievementRewardGroup);

        // Get the mChallengeQuestAchievementRewardGroup
        restMChallengeQuestAchievementRewardGroupMockMvc.perform(get("/api/m-challenge-quest-achievement-reward-groups/{id}", mChallengeQuestAchievementRewardGroup.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(mChallengeQuestAchievementRewardGroup.getId().intValue()))
            .andExpect(jsonPath("$.groupId").value(DEFAULT_GROUP_ID))
            .andExpect(jsonPath("$.contentType").value(DEFAULT_CONTENT_TYPE))
            .andExpect(jsonPath("$.contentId").value(DEFAULT_CONTENT_ID))
            .andExpect(jsonPath("$.contentAmount").value(DEFAULT_CONTENT_AMOUNT));
    }

    @Test
    @Transactional
    public void getAllMChallengeQuestAchievementRewardGroupsByGroupIdIsEqualToSomething() throws Exception {
        // Initialize the database
        mChallengeQuestAchievementRewardGroupRepository.saveAndFlush(mChallengeQuestAchievementRewardGroup);

        // Get all the mChallengeQuestAchievementRewardGroupList where groupId equals to DEFAULT_GROUP_ID
        defaultMChallengeQuestAchievementRewardGroupShouldBeFound("groupId.equals=" + DEFAULT_GROUP_ID);

        // Get all the mChallengeQuestAchievementRewardGroupList where groupId equals to UPDATED_GROUP_ID
        defaultMChallengeQuestAchievementRewardGroupShouldNotBeFound("groupId.equals=" + UPDATED_GROUP_ID);
    }

    @Test
    @Transactional
    public void getAllMChallengeQuestAchievementRewardGroupsByGroupIdIsInShouldWork() throws Exception {
        // Initialize the database
        mChallengeQuestAchievementRewardGroupRepository.saveAndFlush(mChallengeQuestAchievementRewardGroup);

        // Get all the mChallengeQuestAchievementRewardGroupList where groupId in DEFAULT_GROUP_ID or UPDATED_GROUP_ID
        defaultMChallengeQuestAchievementRewardGroupShouldBeFound("groupId.in=" + DEFAULT_GROUP_ID + "," + UPDATED_GROUP_ID);

        // Get all the mChallengeQuestAchievementRewardGroupList where groupId equals to UPDATED_GROUP_ID
        defaultMChallengeQuestAchievementRewardGroupShouldNotBeFound("groupId.in=" + UPDATED_GROUP_ID);
    }

    @Test
    @Transactional
    public void getAllMChallengeQuestAchievementRewardGroupsByGroupIdIsNullOrNotNull() throws Exception {
        // Initialize the database
        mChallengeQuestAchievementRewardGroupRepository.saveAndFlush(mChallengeQuestAchievementRewardGroup);

        // Get all the mChallengeQuestAchievementRewardGroupList where groupId is not null
        defaultMChallengeQuestAchievementRewardGroupShouldBeFound("groupId.specified=true");

        // Get all the mChallengeQuestAchievementRewardGroupList where groupId is null
        defaultMChallengeQuestAchievementRewardGroupShouldNotBeFound("groupId.specified=false");
    }

    @Test
    @Transactional
    public void getAllMChallengeQuestAchievementRewardGroupsByGroupIdIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mChallengeQuestAchievementRewardGroupRepository.saveAndFlush(mChallengeQuestAchievementRewardGroup);

        // Get all the mChallengeQuestAchievementRewardGroupList where groupId greater than or equals to DEFAULT_GROUP_ID
        defaultMChallengeQuestAchievementRewardGroupShouldBeFound("groupId.greaterOrEqualThan=" + DEFAULT_GROUP_ID);

        // Get all the mChallengeQuestAchievementRewardGroupList where groupId greater than or equals to UPDATED_GROUP_ID
        defaultMChallengeQuestAchievementRewardGroupShouldNotBeFound("groupId.greaterOrEqualThan=" + UPDATED_GROUP_ID);
    }

    @Test
    @Transactional
    public void getAllMChallengeQuestAchievementRewardGroupsByGroupIdIsLessThanSomething() throws Exception {
        // Initialize the database
        mChallengeQuestAchievementRewardGroupRepository.saveAndFlush(mChallengeQuestAchievementRewardGroup);

        // Get all the mChallengeQuestAchievementRewardGroupList where groupId less than or equals to DEFAULT_GROUP_ID
        defaultMChallengeQuestAchievementRewardGroupShouldNotBeFound("groupId.lessThan=" + DEFAULT_GROUP_ID);

        // Get all the mChallengeQuestAchievementRewardGroupList where groupId less than or equals to UPDATED_GROUP_ID
        defaultMChallengeQuestAchievementRewardGroupShouldBeFound("groupId.lessThan=" + UPDATED_GROUP_ID);
    }


    @Test
    @Transactional
    public void getAllMChallengeQuestAchievementRewardGroupsByContentTypeIsEqualToSomething() throws Exception {
        // Initialize the database
        mChallengeQuestAchievementRewardGroupRepository.saveAndFlush(mChallengeQuestAchievementRewardGroup);

        // Get all the mChallengeQuestAchievementRewardGroupList where contentType equals to DEFAULT_CONTENT_TYPE
        defaultMChallengeQuestAchievementRewardGroupShouldBeFound("contentType.equals=" + DEFAULT_CONTENT_TYPE);

        // Get all the mChallengeQuestAchievementRewardGroupList where contentType equals to UPDATED_CONTENT_TYPE
        defaultMChallengeQuestAchievementRewardGroupShouldNotBeFound("contentType.equals=" + UPDATED_CONTENT_TYPE);
    }

    @Test
    @Transactional
    public void getAllMChallengeQuestAchievementRewardGroupsByContentTypeIsInShouldWork() throws Exception {
        // Initialize the database
        mChallengeQuestAchievementRewardGroupRepository.saveAndFlush(mChallengeQuestAchievementRewardGroup);

        // Get all the mChallengeQuestAchievementRewardGroupList where contentType in DEFAULT_CONTENT_TYPE or UPDATED_CONTENT_TYPE
        defaultMChallengeQuestAchievementRewardGroupShouldBeFound("contentType.in=" + DEFAULT_CONTENT_TYPE + "," + UPDATED_CONTENT_TYPE);

        // Get all the mChallengeQuestAchievementRewardGroupList where contentType equals to UPDATED_CONTENT_TYPE
        defaultMChallengeQuestAchievementRewardGroupShouldNotBeFound("contentType.in=" + UPDATED_CONTENT_TYPE);
    }

    @Test
    @Transactional
    public void getAllMChallengeQuestAchievementRewardGroupsByContentTypeIsNullOrNotNull() throws Exception {
        // Initialize the database
        mChallengeQuestAchievementRewardGroupRepository.saveAndFlush(mChallengeQuestAchievementRewardGroup);

        // Get all the mChallengeQuestAchievementRewardGroupList where contentType is not null
        defaultMChallengeQuestAchievementRewardGroupShouldBeFound("contentType.specified=true");

        // Get all the mChallengeQuestAchievementRewardGroupList where contentType is null
        defaultMChallengeQuestAchievementRewardGroupShouldNotBeFound("contentType.specified=false");
    }

    @Test
    @Transactional
    public void getAllMChallengeQuestAchievementRewardGroupsByContentTypeIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mChallengeQuestAchievementRewardGroupRepository.saveAndFlush(mChallengeQuestAchievementRewardGroup);

        // Get all the mChallengeQuestAchievementRewardGroupList where contentType greater than or equals to DEFAULT_CONTENT_TYPE
        defaultMChallengeQuestAchievementRewardGroupShouldBeFound("contentType.greaterOrEqualThan=" + DEFAULT_CONTENT_TYPE);

        // Get all the mChallengeQuestAchievementRewardGroupList where contentType greater than or equals to UPDATED_CONTENT_TYPE
        defaultMChallengeQuestAchievementRewardGroupShouldNotBeFound("contentType.greaterOrEqualThan=" + UPDATED_CONTENT_TYPE);
    }

    @Test
    @Transactional
    public void getAllMChallengeQuestAchievementRewardGroupsByContentTypeIsLessThanSomething() throws Exception {
        // Initialize the database
        mChallengeQuestAchievementRewardGroupRepository.saveAndFlush(mChallengeQuestAchievementRewardGroup);

        // Get all the mChallengeQuestAchievementRewardGroupList where contentType less than or equals to DEFAULT_CONTENT_TYPE
        defaultMChallengeQuestAchievementRewardGroupShouldNotBeFound("contentType.lessThan=" + DEFAULT_CONTENT_TYPE);

        // Get all the mChallengeQuestAchievementRewardGroupList where contentType less than or equals to UPDATED_CONTENT_TYPE
        defaultMChallengeQuestAchievementRewardGroupShouldBeFound("contentType.lessThan=" + UPDATED_CONTENT_TYPE);
    }


    @Test
    @Transactional
    public void getAllMChallengeQuestAchievementRewardGroupsByContentIdIsEqualToSomething() throws Exception {
        // Initialize the database
        mChallengeQuestAchievementRewardGroupRepository.saveAndFlush(mChallengeQuestAchievementRewardGroup);

        // Get all the mChallengeQuestAchievementRewardGroupList where contentId equals to DEFAULT_CONTENT_ID
        defaultMChallengeQuestAchievementRewardGroupShouldBeFound("contentId.equals=" + DEFAULT_CONTENT_ID);

        // Get all the mChallengeQuestAchievementRewardGroupList where contentId equals to UPDATED_CONTENT_ID
        defaultMChallengeQuestAchievementRewardGroupShouldNotBeFound("contentId.equals=" + UPDATED_CONTENT_ID);
    }

    @Test
    @Transactional
    public void getAllMChallengeQuestAchievementRewardGroupsByContentIdIsInShouldWork() throws Exception {
        // Initialize the database
        mChallengeQuestAchievementRewardGroupRepository.saveAndFlush(mChallengeQuestAchievementRewardGroup);

        // Get all the mChallengeQuestAchievementRewardGroupList where contentId in DEFAULT_CONTENT_ID or UPDATED_CONTENT_ID
        defaultMChallengeQuestAchievementRewardGroupShouldBeFound("contentId.in=" + DEFAULT_CONTENT_ID + "," + UPDATED_CONTENT_ID);

        // Get all the mChallengeQuestAchievementRewardGroupList where contentId equals to UPDATED_CONTENT_ID
        defaultMChallengeQuestAchievementRewardGroupShouldNotBeFound("contentId.in=" + UPDATED_CONTENT_ID);
    }

    @Test
    @Transactional
    public void getAllMChallengeQuestAchievementRewardGroupsByContentIdIsNullOrNotNull() throws Exception {
        // Initialize the database
        mChallengeQuestAchievementRewardGroupRepository.saveAndFlush(mChallengeQuestAchievementRewardGroup);

        // Get all the mChallengeQuestAchievementRewardGroupList where contentId is not null
        defaultMChallengeQuestAchievementRewardGroupShouldBeFound("contentId.specified=true");

        // Get all the mChallengeQuestAchievementRewardGroupList where contentId is null
        defaultMChallengeQuestAchievementRewardGroupShouldNotBeFound("contentId.specified=false");
    }

    @Test
    @Transactional
    public void getAllMChallengeQuestAchievementRewardGroupsByContentIdIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mChallengeQuestAchievementRewardGroupRepository.saveAndFlush(mChallengeQuestAchievementRewardGroup);

        // Get all the mChallengeQuestAchievementRewardGroupList where contentId greater than or equals to DEFAULT_CONTENT_ID
        defaultMChallengeQuestAchievementRewardGroupShouldBeFound("contentId.greaterOrEqualThan=" + DEFAULT_CONTENT_ID);

        // Get all the mChallengeQuestAchievementRewardGroupList where contentId greater than or equals to UPDATED_CONTENT_ID
        defaultMChallengeQuestAchievementRewardGroupShouldNotBeFound("contentId.greaterOrEqualThan=" + UPDATED_CONTENT_ID);
    }

    @Test
    @Transactional
    public void getAllMChallengeQuestAchievementRewardGroupsByContentIdIsLessThanSomething() throws Exception {
        // Initialize the database
        mChallengeQuestAchievementRewardGroupRepository.saveAndFlush(mChallengeQuestAchievementRewardGroup);

        // Get all the mChallengeQuestAchievementRewardGroupList where contentId less than or equals to DEFAULT_CONTENT_ID
        defaultMChallengeQuestAchievementRewardGroupShouldNotBeFound("contentId.lessThan=" + DEFAULT_CONTENT_ID);

        // Get all the mChallengeQuestAchievementRewardGroupList where contentId less than or equals to UPDATED_CONTENT_ID
        defaultMChallengeQuestAchievementRewardGroupShouldBeFound("contentId.lessThan=" + UPDATED_CONTENT_ID);
    }


    @Test
    @Transactional
    public void getAllMChallengeQuestAchievementRewardGroupsByContentAmountIsEqualToSomething() throws Exception {
        // Initialize the database
        mChallengeQuestAchievementRewardGroupRepository.saveAndFlush(mChallengeQuestAchievementRewardGroup);

        // Get all the mChallengeQuestAchievementRewardGroupList where contentAmount equals to DEFAULT_CONTENT_AMOUNT
        defaultMChallengeQuestAchievementRewardGroupShouldBeFound("contentAmount.equals=" + DEFAULT_CONTENT_AMOUNT);

        // Get all the mChallengeQuestAchievementRewardGroupList where contentAmount equals to UPDATED_CONTENT_AMOUNT
        defaultMChallengeQuestAchievementRewardGroupShouldNotBeFound("contentAmount.equals=" + UPDATED_CONTENT_AMOUNT);
    }

    @Test
    @Transactional
    public void getAllMChallengeQuestAchievementRewardGroupsByContentAmountIsInShouldWork() throws Exception {
        // Initialize the database
        mChallengeQuestAchievementRewardGroupRepository.saveAndFlush(mChallengeQuestAchievementRewardGroup);

        // Get all the mChallengeQuestAchievementRewardGroupList where contentAmount in DEFAULT_CONTENT_AMOUNT or UPDATED_CONTENT_AMOUNT
        defaultMChallengeQuestAchievementRewardGroupShouldBeFound("contentAmount.in=" + DEFAULT_CONTENT_AMOUNT + "," + UPDATED_CONTENT_AMOUNT);

        // Get all the mChallengeQuestAchievementRewardGroupList where contentAmount equals to UPDATED_CONTENT_AMOUNT
        defaultMChallengeQuestAchievementRewardGroupShouldNotBeFound("contentAmount.in=" + UPDATED_CONTENT_AMOUNT);
    }

    @Test
    @Transactional
    public void getAllMChallengeQuestAchievementRewardGroupsByContentAmountIsNullOrNotNull() throws Exception {
        // Initialize the database
        mChallengeQuestAchievementRewardGroupRepository.saveAndFlush(mChallengeQuestAchievementRewardGroup);

        // Get all the mChallengeQuestAchievementRewardGroupList where contentAmount is not null
        defaultMChallengeQuestAchievementRewardGroupShouldBeFound("contentAmount.specified=true");

        // Get all the mChallengeQuestAchievementRewardGroupList where contentAmount is null
        defaultMChallengeQuestAchievementRewardGroupShouldNotBeFound("contentAmount.specified=false");
    }

    @Test
    @Transactional
    public void getAllMChallengeQuestAchievementRewardGroupsByContentAmountIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mChallengeQuestAchievementRewardGroupRepository.saveAndFlush(mChallengeQuestAchievementRewardGroup);

        // Get all the mChallengeQuestAchievementRewardGroupList where contentAmount greater than or equals to DEFAULT_CONTENT_AMOUNT
        defaultMChallengeQuestAchievementRewardGroupShouldBeFound("contentAmount.greaterOrEqualThan=" + DEFAULT_CONTENT_AMOUNT);

        // Get all the mChallengeQuestAchievementRewardGroupList where contentAmount greater than or equals to UPDATED_CONTENT_AMOUNT
        defaultMChallengeQuestAchievementRewardGroupShouldNotBeFound("contentAmount.greaterOrEqualThan=" + UPDATED_CONTENT_AMOUNT);
    }

    @Test
    @Transactional
    public void getAllMChallengeQuestAchievementRewardGroupsByContentAmountIsLessThanSomething() throws Exception {
        // Initialize the database
        mChallengeQuestAchievementRewardGroupRepository.saveAndFlush(mChallengeQuestAchievementRewardGroup);

        // Get all the mChallengeQuestAchievementRewardGroupList where contentAmount less than or equals to DEFAULT_CONTENT_AMOUNT
        defaultMChallengeQuestAchievementRewardGroupShouldNotBeFound("contentAmount.lessThan=" + DEFAULT_CONTENT_AMOUNT);

        // Get all the mChallengeQuestAchievementRewardGroupList where contentAmount less than or equals to UPDATED_CONTENT_AMOUNT
        defaultMChallengeQuestAchievementRewardGroupShouldBeFound("contentAmount.lessThan=" + UPDATED_CONTENT_AMOUNT);
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultMChallengeQuestAchievementRewardGroupShouldBeFound(String filter) throws Exception {
        restMChallengeQuestAchievementRewardGroupMockMvc.perform(get("/api/m-challenge-quest-achievement-reward-groups?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(mChallengeQuestAchievementRewardGroup.getId().intValue())))
            .andExpect(jsonPath("$.[*].groupId").value(hasItem(DEFAULT_GROUP_ID)))
            .andExpect(jsonPath("$.[*].contentType").value(hasItem(DEFAULT_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].contentId").value(hasItem(DEFAULT_CONTENT_ID)))
            .andExpect(jsonPath("$.[*].contentAmount").value(hasItem(DEFAULT_CONTENT_AMOUNT)));

        // Check, that the count call also returns 1
        restMChallengeQuestAchievementRewardGroupMockMvc.perform(get("/api/m-challenge-quest-achievement-reward-groups/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultMChallengeQuestAchievementRewardGroupShouldNotBeFound(String filter) throws Exception {
        restMChallengeQuestAchievementRewardGroupMockMvc.perform(get("/api/m-challenge-quest-achievement-reward-groups?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restMChallengeQuestAchievementRewardGroupMockMvc.perform(get("/api/m-challenge-quest-achievement-reward-groups/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("0"));
    }


    @Test
    @Transactional
    public void getNonExistingMChallengeQuestAchievementRewardGroup() throws Exception {
        // Get the mChallengeQuestAchievementRewardGroup
        restMChallengeQuestAchievementRewardGroupMockMvc.perform(get("/api/m-challenge-quest-achievement-reward-groups/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateMChallengeQuestAchievementRewardGroup() throws Exception {
        // Initialize the database
        mChallengeQuestAchievementRewardGroupRepository.saveAndFlush(mChallengeQuestAchievementRewardGroup);

        int databaseSizeBeforeUpdate = mChallengeQuestAchievementRewardGroupRepository.findAll().size();

        // Update the mChallengeQuestAchievementRewardGroup
        MChallengeQuestAchievementRewardGroup updatedMChallengeQuestAchievementRewardGroup = mChallengeQuestAchievementRewardGroupRepository.findById(mChallengeQuestAchievementRewardGroup.getId()).get();
        // Disconnect from session so that the updates on updatedMChallengeQuestAchievementRewardGroup are not directly saved in db
        em.detach(updatedMChallengeQuestAchievementRewardGroup);
        updatedMChallengeQuestAchievementRewardGroup
            .groupId(UPDATED_GROUP_ID)
            .contentType(UPDATED_CONTENT_TYPE)
            .contentId(UPDATED_CONTENT_ID)
            .contentAmount(UPDATED_CONTENT_AMOUNT);
        MChallengeQuestAchievementRewardGroupDTO mChallengeQuestAchievementRewardGroupDTO = mChallengeQuestAchievementRewardGroupMapper.toDto(updatedMChallengeQuestAchievementRewardGroup);

        restMChallengeQuestAchievementRewardGroupMockMvc.perform(put("/api/m-challenge-quest-achievement-reward-groups")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mChallengeQuestAchievementRewardGroupDTO)))
            .andExpect(status().isOk());

        // Validate the MChallengeQuestAchievementRewardGroup in the database
        List<MChallengeQuestAchievementRewardGroup> mChallengeQuestAchievementRewardGroupList = mChallengeQuestAchievementRewardGroupRepository.findAll();
        assertThat(mChallengeQuestAchievementRewardGroupList).hasSize(databaseSizeBeforeUpdate);
        MChallengeQuestAchievementRewardGroup testMChallengeQuestAchievementRewardGroup = mChallengeQuestAchievementRewardGroupList.get(mChallengeQuestAchievementRewardGroupList.size() - 1);
        assertThat(testMChallengeQuestAchievementRewardGroup.getGroupId()).isEqualTo(UPDATED_GROUP_ID);
        assertThat(testMChallengeQuestAchievementRewardGroup.getContentType()).isEqualTo(UPDATED_CONTENT_TYPE);
        assertThat(testMChallengeQuestAchievementRewardGroup.getContentId()).isEqualTo(UPDATED_CONTENT_ID);
        assertThat(testMChallengeQuestAchievementRewardGroup.getContentAmount()).isEqualTo(UPDATED_CONTENT_AMOUNT);
    }

    @Test
    @Transactional
    public void updateNonExistingMChallengeQuestAchievementRewardGroup() throws Exception {
        int databaseSizeBeforeUpdate = mChallengeQuestAchievementRewardGroupRepository.findAll().size();

        // Create the MChallengeQuestAchievementRewardGroup
        MChallengeQuestAchievementRewardGroupDTO mChallengeQuestAchievementRewardGroupDTO = mChallengeQuestAchievementRewardGroupMapper.toDto(mChallengeQuestAchievementRewardGroup);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restMChallengeQuestAchievementRewardGroupMockMvc.perform(put("/api/m-challenge-quest-achievement-reward-groups")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mChallengeQuestAchievementRewardGroupDTO)))
            .andExpect(status().isBadRequest());

        // Validate the MChallengeQuestAchievementRewardGroup in the database
        List<MChallengeQuestAchievementRewardGroup> mChallengeQuestAchievementRewardGroupList = mChallengeQuestAchievementRewardGroupRepository.findAll();
        assertThat(mChallengeQuestAchievementRewardGroupList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteMChallengeQuestAchievementRewardGroup() throws Exception {
        // Initialize the database
        mChallengeQuestAchievementRewardGroupRepository.saveAndFlush(mChallengeQuestAchievementRewardGroup);

        int databaseSizeBeforeDelete = mChallengeQuestAchievementRewardGroupRepository.findAll().size();

        // Delete the mChallengeQuestAchievementRewardGroup
        restMChallengeQuestAchievementRewardGroupMockMvc.perform(delete("/api/m-challenge-quest-achievement-reward-groups/{id}", mChallengeQuestAchievementRewardGroup.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database is empty
        List<MChallengeQuestAchievementRewardGroup> mChallengeQuestAchievementRewardGroupList = mChallengeQuestAchievementRewardGroupRepository.findAll();
        assertThat(mChallengeQuestAchievementRewardGroupList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(MChallengeQuestAchievementRewardGroup.class);
        MChallengeQuestAchievementRewardGroup mChallengeQuestAchievementRewardGroup1 = new MChallengeQuestAchievementRewardGroup();
        mChallengeQuestAchievementRewardGroup1.setId(1L);
        MChallengeQuestAchievementRewardGroup mChallengeQuestAchievementRewardGroup2 = new MChallengeQuestAchievementRewardGroup();
        mChallengeQuestAchievementRewardGroup2.setId(mChallengeQuestAchievementRewardGroup1.getId());
        assertThat(mChallengeQuestAchievementRewardGroup1).isEqualTo(mChallengeQuestAchievementRewardGroup2);
        mChallengeQuestAchievementRewardGroup2.setId(2L);
        assertThat(mChallengeQuestAchievementRewardGroup1).isNotEqualTo(mChallengeQuestAchievementRewardGroup2);
        mChallengeQuestAchievementRewardGroup1.setId(null);
        assertThat(mChallengeQuestAchievementRewardGroup1).isNotEqualTo(mChallengeQuestAchievementRewardGroup2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(MChallengeQuestAchievementRewardGroupDTO.class);
        MChallengeQuestAchievementRewardGroupDTO mChallengeQuestAchievementRewardGroupDTO1 = new MChallengeQuestAchievementRewardGroupDTO();
        mChallengeQuestAchievementRewardGroupDTO1.setId(1L);
        MChallengeQuestAchievementRewardGroupDTO mChallengeQuestAchievementRewardGroupDTO2 = new MChallengeQuestAchievementRewardGroupDTO();
        assertThat(mChallengeQuestAchievementRewardGroupDTO1).isNotEqualTo(mChallengeQuestAchievementRewardGroupDTO2);
        mChallengeQuestAchievementRewardGroupDTO2.setId(mChallengeQuestAchievementRewardGroupDTO1.getId());
        assertThat(mChallengeQuestAchievementRewardGroupDTO1).isEqualTo(mChallengeQuestAchievementRewardGroupDTO2);
        mChallengeQuestAchievementRewardGroupDTO2.setId(2L);
        assertThat(mChallengeQuestAchievementRewardGroupDTO1).isNotEqualTo(mChallengeQuestAchievementRewardGroupDTO2);
        mChallengeQuestAchievementRewardGroupDTO1.setId(null);
        assertThat(mChallengeQuestAchievementRewardGroupDTO1).isNotEqualTo(mChallengeQuestAchievementRewardGroupDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(mChallengeQuestAchievementRewardGroupMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(mChallengeQuestAchievementRewardGroupMapper.fromId(null)).isNull();
    }
}
