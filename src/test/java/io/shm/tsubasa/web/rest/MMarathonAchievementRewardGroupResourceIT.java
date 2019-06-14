package io.shm.tsubasa.web.rest;

import io.shm.tsubasa.TsubasaApp;
import io.shm.tsubasa.domain.MMarathonAchievementRewardGroup;
import io.shm.tsubasa.repository.MMarathonAchievementRewardGroupRepository;
import io.shm.tsubasa.service.MMarathonAchievementRewardGroupService;
import io.shm.tsubasa.service.dto.MMarathonAchievementRewardGroupDTO;
import io.shm.tsubasa.service.mapper.MMarathonAchievementRewardGroupMapper;
import io.shm.tsubasa.web.rest.errors.ExceptionTranslator;
import io.shm.tsubasa.service.dto.MMarathonAchievementRewardGroupCriteria;
import io.shm.tsubasa.service.MMarathonAchievementRewardGroupQueryService;

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
 * Integration tests for the {@Link MMarathonAchievementRewardGroupResource} REST controller.
 */
@SpringBootTest(classes = TsubasaApp.class)
public class MMarathonAchievementRewardGroupResourceIT {

    private static final Integer DEFAULT_GROUP_ID = 1;
    private static final Integer UPDATED_GROUP_ID = 2;

    private static final Integer DEFAULT_CONTENT_TYPE = 1;
    private static final Integer UPDATED_CONTENT_TYPE = 2;

    private static final Integer DEFAULT_CONTENT_ID = 1;
    private static final Integer UPDATED_CONTENT_ID = 2;

    private static final Integer DEFAULT_CONTENT_AMOUNT = 1;
    private static final Integer UPDATED_CONTENT_AMOUNT = 2;

    @Autowired
    private MMarathonAchievementRewardGroupRepository mMarathonAchievementRewardGroupRepository;

    @Autowired
    private MMarathonAchievementRewardGroupMapper mMarathonAchievementRewardGroupMapper;

    @Autowired
    private MMarathonAchievementRewardGroupService mMarathonAchievementRewardGroupService;

    @Autowired
    private MMarathonAchievementRewardGroupQueryService mMarathonAchievementRewardGroupQueryService;

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

    private MockMvc restMMarathonAchievementRewardGroupMockMvc;

    private MMarathonAchievementRewardGroup mMarathonAchievementRewardGroup;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final MMarathonAchievementRewardGroupResource mMarathonAchievementRewardGroupResource = new MMarathonAchievementRewardGroupResource(mMarathonAchievementRewardGroupService, mMarathonAchievementRewardGroupQueryService);
        this.restMMarathonAchievementRewardGroupMockMvc = MockMvcBuilders.standaloneSetup(mMarathonAchievementRewardGroupResource)
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
    public static MMarathonAchievementRewardGroup createEntity(EntityManager em) {
        MMarathonAchievementRewardGroup mMarathonAchievementRewardGroup = new MMarathonAchievementRewardGroup()
            .groupId(DEFAULT_GROUP_ID)
            .contentType(DEFAULT_CONTENT_TYPE)
            .contentId(DEFAULT_CONTENT_ID)
            .contentAmount(DEFAULT_CONTENT_AMOUNT);
        return mMarathonAchievementRewardGroup;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static MMarathonAchievementRewardGroup createUpdatedEntity(EntityManager em) {
        MMarathonAchievementRewardGroup mMarathonAchievementRewardGroup = new MMarathonAchievementRewardGroup()
            .groupId(UPDATED_GROUP_ID)
            .contentType(UPDATED_CONTENT_TYPE)
            .contentId(UPDATED_CONTENT_ID)
            .contentAmount(UPDATED_CONTENT_AMOUNT);
        return mMarathonAchievementRewardGroup;
    }

    @BeforeEach
    public void initTest() {
        mMarathonAchievementRewardGroup = createEntity(em);
    }

    @Test
    @Transactional
    public void createMMarathonAchievementRewardGroup() throws Exception {
        int databaseSizeBeforeCreate = mMarathonAchievementRewardGroupRepository.findAll().size();

        // Create the MMarathonAchievementRewardGroup
        MMarathonAchievementRewardGroupDTO mMarathonAchievementRewardGroupDTO = mMarathonAchievementRewardGroupMapper.toDto(mMarathonAchievementRewardGroup);
        restMMarathonAchievementRewardGroupMockMvc.perform(post("/api/m-marathon-achievement-reward-groups")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mMarathonAchievementRewardGroupDTO)))
            .andExpect(status().isCreated());

        // Validate the MMarathonAchievementRewardGroup in the database
        List<MMarathonAchievementRewardGroup> mMarathonAchievementRewardGroupList = mMarathonAchievementRewardGroupRepository.findAll();
        assertThat(mMarathonAchievementRewardGroupList).hasSize(databaseSizeBeforeCreate + 1);
        MMarathonAchievementRewardGroup testMMarathonAchievementRewardGroup = mMarathonAchievementRewardGroupList.get(mMarathonAchievementRewardGroupList.size() - 1);
        assertThat(testMMarathonAchievementRewardGroup.getGroupId()).isEqualTo(DEFAULT_GROUP_ID);
        assertThat(testMMarathonAchievementRewardGroup.getContentType()).isEqualTo(DEFAULT_CONTENT_TYPE);
        assertThat(testMMarathonAchievementRewardGroup.getContentId()).isEqualTo(DEFAULT_CONTENT_ID);
        assertThat(testMMarathonAchievementRewardGroup.getContentAmount()).isEqualTo(DEFAULT_CONTENT_AMOUNT);
    }

    @Test
    @Transactional
    public void createMMarathonAchievementRewardGroupWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = mMarathonAchievementRewardGroupRepository.findAll().size();

        // Create the MMarathonAchievementRewardGroup with an existing ID
        mMarathonAchievementRewardGroup.setId(1L);
        MMarathonAchievementRewardGroupDTO mMarathonAchievementRewardGroupDTO = mMarathonAchievementRewardGroupMapper.toDto(mMarathonAchievementRewardGroup);

        // An entity with an existing ID cannot be created, so this API call must fail
        restMMarathonAchievementRewardGroupMockMvc.perform(post("/api/m-marathon-achievement-reward-groups")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mMarathonAchievementRewardGroupDTO)))
            .andExpect(status().isBadRequest());

        // Validate the MMarathonAchievementRewardGroup in the database
        List<MMarathonAchievementRewardGroup> mMarathonAchievementRewardGroupList = mMarathonAchievementRewardGroupRepository.findAll();
        assertThat(mMarathonAchievementRewardGroupList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkGroupIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = mMarathonAchievementRewardGroupRepository.findAll().size();
        // set the field null
        mMarathonAchievementRewardGroup.setGroupId(null);

        // Create the MMarathonAchievementRewardGroup, which fails.
        MMarathonAchievementRewardGroupDTO mMarathonAchievementRewardGroupDTO = mMarathonAchievementRewardGroupMapper.toDto(mMarathonAchievementRewardGroup);

        restMMarathonAchievementRewardGroupMockMvc.perform(post("/api/m-marathon-achievement-reward-groups")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mMarathonAchievementRewardGroupDTO)))
            .andExpect(status().isBadRequest());

        List<MMarathonAchievementRewardGroup> mMarathonAchievementRewardGroupList = mMarathonAchievementRewardGroupRepository.findAll();
        assertThat(mMarathonAchievementRewardGroupList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkContentTypeIsRequired() throws Exception {
        int databaseSizeBeforeTest = mMarathonAchievementRewardGroupRepository.findAll().size();
        // set the field null
        mMarathonAchievementRewardGroup.setContentType(null);

        // Create the MMarathonAchievementRewardGroup, which fails.
        MMarathonAchievementRewardGroupDTO mMarathonAchievementRewardGroupDTO = mMarathonAchievementRewardGroupMapper.toDto(mMarathonAchievementRewardGroup);

        restMMarathonAchievementRewardGroupMockMvc.perform(post("/api/m-marathon-achievement-reward-groups")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mMarathonAchievementRewardGroupDTO)))
            .andExpect(status().isBadRequest());

        List<MMarathonAchievementRewardGroup> mMarathonAchievementRewardGroupList = mMarathonAchievementRewardGroupRepository.findAll();
        assertThat(mMarathonAchievementRewardGroupList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkContentAmountIsRequired() throws Exception {
        int databaseSizeBeforeTest = mMarathonAchievementRewardGroupRepository.findAll().size();
        // set the field null
        mMarathonAchievementRewardGroup.setContentAmount(null);

        // Create the MMarathonAchievementRewardGroup, which fails.
        MMarathonAchievementRewardGroupDTO mMarathonAchievementRewardGroupDTO = mMarathonAchievementRewardGroupMapper.toDto(mMarathonAchievementRewardGroup);

        restMMarathonAchievementRewardGroupMockMvc.perform(post("/api/m-marathon-achievement-reward-groups")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mMarathonAchievementRewardGroupDTO)))
            .andExpect(status().isBadRequest());

        List<MMarathonAchievementRewardGroup> mMarathonAchievementRewardGroupList = mMarathonAchievementRewardGroupRepository.findAll();
        assertThat(mMarathonAchievementRewardGroupList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllMMarathonAchievementRewardGroups() throws Exception {
        // Initialize the database
        mMarathonAchievementRewardGroupRepository.saveAndFlush(mMarathonAchievementRewardGroup);

        // Get all the mMarathonAchievementRewardGroupList
        restMMarathonAchievementRewardGroupMockMvc.perform(get("/api/m-marathon-achievement-reward-groups?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(mMarathonAchievementRewardGroup.getId().intValue())))
            .andExpect(jsonPath("$.[*].groupId").value(hasItem(DEFAULT_GROUP_ID)))
            .andExpect(jsonPath("$.[*].contentType").value(hasItem(DEFAULT_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].contentId").value(hasItem(DEFAULT_CONTENT_ID)))
            .andExpect(jsonPath("$.[*].contentAmount").value(hasItem(DEFAULT_CONTENT_AMOUNT)));
    }
    
    @Test
    @Transactional
    public void getMMarathonAchievementRewardGroup() throws Exception {
        // Initialize the database
        mMarathonAchievementRewardGroupRepository.saveAndFlush(mMarathonAchievementRewardGroup);

        // Get the mMarathonAchievementRewardGroup
        restMMarathonAchievementRewardGroupMockMvc.perform(get("/api/m-marathon-achievement-reward-groups/{id}", mMarathonAchievementRewardGroup.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(mMarathonAchievementRewardGroup.getId().intValue()))
            .andExpect(jsonPath("$.groupId").value(DEFAULT_GROUP_ID))
            .andExpect(jsonPath("$.contentType").value(DEFAULT_CONTENT_TYPE))
            .andExpect(jsonPath("$.contentId").value(DEFAULT_CONTENT_ID))
            .andExpect(jsonPath("$.contentAmount").value(DEFAULT_CONTENT_AMOUNT));
    }

    @Test
    @Transactional
    public void getAllMMarathonAchievementRewardGroupsByGroupIdIsEqualToSomething() throws Exception {
        // Initialize the database
        mMarathonAchievementRewardGroupRepository.saveAndFlush(mMarathonAchievementRewardGroup);

        // Get all the mMarathonAchievementRewardGroupList where groupId equals to DEFAULT_GROUP_ID
        defaultMMarathonAchievementRewardGroupShouldBeFound("groupId.equals=" + DEFAULT_GROUP_ID);

        // Get all the mMarathonAchievementRewardGroupList where groupId equals to UPDATED_GROUP_ID
        defaultMMarathonAchievementRewardGroupShouldNotBeFound("groupId.equals=" + UPDATED_GROUP_ID);
    }

    @Test
    @Transactional
    public void getAllMMarathonAchievementRewardGroupsByGroupIdIsInShouldWork() throws Exception {
        // Initialize the database
        mMarathonAchievementRewardGroupRepository.saveAndFlush(mMarathonAchievementRewardGroup);

        // Get all the mMarathonAchievementRewardGroupList where groupId in DEFAULT_GROUP_ID or UPDATED_GROUP_ID
        defaultMMarathonAchievementRewardGroupShouldBeFound("groupId.in=" + DEFAULT_GROUP_ID + "," + UPDATED_GROUP_ID);

        // Get all the mMarathonAchievementRewardGroupList where groupId equals to UPDATED_GROUP_ID
        defaultMMarathonAchievementRewardGroupShouldNotBeFound("groupId.in=" + UPDATED_GROUP_ID);
    }

    @Test
    @Transactional
    public void getAllMMarathonAchievementRewardGroupsByGroupIdIsNullOrNotNull() throws Exception {
        // Initialize the database
        mMarathonAchievementRewardGroupRepository.saveAndFlush(mMarathonAchievementRewardGroup);

        // Get all the mMarathonAchievementRewardGroupList where groupId is not null
        defaultMMarathonAchievementRewardGroupShouldBeFound("groupId.specified=true");

        // Get all the mMarathonAchievementRewardGroupList where groupId is null
        defaultMMarathonAchievementRewardGroupShouldNotBeFound("groupId.specified=false");
    }

    @Test
    @Transactional
    public void getAllMMarathonAchievementRewardGroupsByGroupIdIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mMarathonAchievementRewardGroupRepository.saveAndFlush(mMarathonAchievementRewardGroup);

        // Get all the mMarathonAchievementRewardGroupList where groupId greater than or equals to DEFAULT_GROUP_ID
        defaultMMarathonAchievementRewardGroupShouldBeFound("groupId.greaterOrEqualThan=" + DEFAULT_GROUP_ID);

        // Get all the mMarathonAchievementRewardGroupList where groupId greater than or equals to UPDATED_GROUP_ID
        defaultMMarathonAchievementRewardGroupShouldNotBeFound("groupId.greaterOrEqualThan=" + UPDATED_GROUP_ID);
    }

    @Test
    @Transactional
    public void getAllMMarathonAchievementRewardGroupsByGroupIdIsLessThanSomething() throws Exception {
        // Initialize the database
        mMarathonAchievementRewardGroupRepository.saveAndFlush(mMarathonAchievementRewardGroup);

        // Get all the mMarathonAchievementRewardGroupList where groupId less than or equals to DEFAULT_GROUP_ID
        defaultMMarathonAchievementRewardGroupShouldNotBeFound("groupId.lessThan=" + DEFAULT_GROUP_ID);

        // Get all the mMarathonAchievementRewardGroupList where groupId less than or equals to UPDATED_GROUP_ID
        defaultMMarathonAchievementRewardGroupShouldBeFound("groupId.lessThan=" + UPDATED_GROUP_ID);
    }


    @Test
    @Transactional
    public void getAllMMarathonAchievementRewardGroupsByContentTypeIsEqualToSomething() throws Exception {
        // Initialize the database
        mMarathonAchievementRewardGroupRepository.saveAndFlush(mMarathonAchievementRewardGroup);

        // Get all the mMarathonAchievementRewardGroupList where contentType equals to DEFAULT_CONTENT_TYPE
        defaultMMarathonAchievementRewardGroupShouldBeFound("contentType.equals=" + DEFAULT_CONTENT_TYPE);

        // Get all the mMarathonAchievementRewardGroupList where contentType equals to UPDATED_CONTENT_TYPE
        defaultMMarathonAchievementRewardGroupShouldNotBeFound("contentType.equals=" + UPDATED_CONTENT_TYPE);
    }

    @Test
    @Transactional
    public void getAllMMarathonAchievementRewardGroupsByContentTypeIsInShouldWork() throws Exception {
        // Initialize the database
        mMarathonAchievementRewardGroupRepository.saveAndFlush(mMarathonAchievementRewardGroup);

        // Get all the mMarathonAchievementRewardGroupList where contentType in DEFAULT_CONTENT_TYPE or UPDATED_CONTENT_TYPE
        defaultMMarathonAchievementRewardGroupShouldBeFound("contentType.in=" + DEFAULT_CONTENT_TYPE + "," + UPDATED_CONTENT_TYPE);

        // Get all the mMarathonAchievementRewardGroupList where contentType equals to UPDATED_CONTENT_TYPE
        defaultMMarathonAchievementRewardGroupShouldNotBeFound("contentType.in=" + UPDATED_CONTENT_TYPE);
    }

    @Test
    @Transactional
    public void getAllMMarathonAchievementRewardGroupsByContentTypeIsNullOrNotNull() throws Exception {
        // Initialize the database
        mMarathonAchievementRewardGroupRepository.saveAndFlush(mMarathonAchievementRewardGroup);

        // Get all the mMarathonAchievementRewardGroupList where contentType is not null
        defaultMMarathonAchievementRewardGroupShouldBeFound("contentType.specified=true");

        // Get all the mMarathonAchievementRewardGroupList where contentType is null
        defaultMMarathonAchievementRewardGroupShouldNotBeFound("contentType.specified=false");
    }

    @Test
    @Transactional
    public void getAllMMarathonAchievementRewardGroupsByContentTypeIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mMarathonAchievementRewardGroupRepository.saveAndFlush(mMarathonAchievementRewardGroup);

        // Get all the mMarathonAchievementRewardGroupList where contentType greater than or equals to DEFAULT_CONTENT_TYPE
        defaultMMarathonAchievementRewardGroupShouldBeFound("contentType.greaterOrEqualThan=" + DEFAULT_CONTENT_TYPE);

        // Get all the mMarathonAchievementRewardGroupList where contentType greater than or equals to UPDATED_CONTENT_TYPE
        defaultMMarathonAchievementRewardGroupShouldNotBeFound("contentType.greaterOrEqualThan=" + UPDATED_CONTENT_TYPE);
    }

    @Test
    @Transactional
    public void getAllMMarathonAchievementRewardGroupsByContentTypeIsLessThanSomething() throws Exception {
        // Initialize the database
        mMarathonAchievementRewardGroupRepository.saveAndFlush(mMarathonAchievementRewardGroup);

        // Get all the mMarathonAchievementRewardGroupList where contentType less than or equals to DEFAULT_CONTENT_TYPE
        defaultMMarathonAchievementRewardGroupShouldNotBeFound("contentType.lessThan=" + DEFAULT_CONTENT_TYPE);

        // Get all the mMarathonAchievementRewardGroupList where contentType less than or equals to UPDATED_CONTENT_TYPE
        defaultMMarathonAchievementRewardGroupShouldBeFound("contentType.lessThan=" + UPDATED_CONTENT_TYPE);
    }


    @Test
    @Transactional
    public void getAllMMarathonAchievementRewardGroupsByContentIdIsEqualToSomething() throws Exception {
        // Initialize the database
        mMarathonAchievementRewardGroupRepository.saveAndFlush(mMarathonAchievementRewardGroup);

        // Get all the mMarathonAchievementRewardGroupList where contentId equals to DEFAULT_CONTENT_ID
        defaultMMarathonAchievementRewardGroupShouldBeFound("contentId.equals=" + DEFAULT_CONTENT_ID);

        // Get all the mMarathonAchievementRewardGroupList where contentId equals to UPDATED_CONTENT_ID
        defaultMMarathonAchievementRewardGroupShouldNotBeFound("contentId.equals=" + UPDATED_CONTENT_ID);
    }

    @Test
    @Transactional
    public void getAllMMarathonAchievementRewardGroupsByContentIdIsInShouldWork() throws Exception {
        // Initialize the database
        mMarathonAchievementRewardGroupRepository.saveAndFlush(mMarathonAchievementRewardGroup);

        // Get all the mMarathonAchievementRewardGroupList where contentId in DEFAULT_CONTENT_ID or UPDATED_CONTENT_ID
        defaultMMarathonAchievementRewardGroupShouldBeFound("contentId.in=" + DEFAULT_CONTENT_ID + "," + UPDATED_CONTENT_ID);

        // Get all the mMarathonAchievementRewardGroupList where contentId equals to UPDATED_CONTENT_ID
        defaultMMarathonAchievementRewardGroupShouldNotBeFound("contentId.in=" + UPDATED_CONTENT_ID);
    }

    @Test
    @Transactional
    public void getAllMMarathonAchievementRewardGroupsByContentIdIsNullOrNotNull() throws Exception {
        // Initialize the database
        mMarathonAchievementRewardGroupRepository.saveAndFlush(mMarathonAchievementRewardGroup);

        // Get all the mMarathonAchievementRewardGroupList where contentId is not null
        defaultMMarathonAchievementRewardGroupShouldBeFound("contentId.specified=true");

        // Get all the mMarathonAchievementRewardGroupList where contentId is null
        defaultMMarathonAchievementRewardGroupShouldNotBeFound("contentId.specified=false");
    }

    @Test
    @Transactional
    public void getAllMMarathonAchievementRewardGroupsByContentIdIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mMarathonAchievementRewardGroupRepository.saveAndFlush(mMarathonAchievementRewardGroup);

        // Get all the mMarathonAchievementRewardGroupList where contentId greater than or equals to DEFAULT_CONTENT_ID
        defaultMMarathonAchievementRewardGroupShouldBeFound("contentId.greaterOrEqualThan=" + DEFAULT_CONTENT_ID);

        // Get all the mMarathonAchievementRewardGroupList where contentId greater than or equals to UPDATED_CONTENT_ID
        defaultMMarathonAchievementRewardGroupShouldNotBeFound("contentId.greaterOrEqualThan=" + UPDATED_CONTENT_ID);
    }

    @Test
    @Transactional
    public void getAllMMarathonAchievementRewardGroupsByContentIdIsLessThanSomething() throws Exception {
        // Initialize the database
        mMarathonAchievementRewardGroupRepository.saveAndFlush(mMarathonAchievementRewardGroup);

        // Get all the mMarathonAchievementRewardGroupList where contentId less than or equals to DEFAULT_CONTENT_ID
        defaultMMarathonAchievementRewardGroupShouldNotBeFound("contentId.lessThan=" + DEFAULT_CONTENT_ID);

        // Get all the mMarathonAchievementRewardGroupList where contentId less than or equals to UPDATED_CONTENT_ID
        defaultMMarathonAchievementRewardGroupShouldBeFound("contentId.lessThan=" + UPDATED_CONTENT_ID);
    }


    @Test
    @Transactional
    public void getAllMMarathonAchievementRewardGroupsByContentAmountIsEqualToSomething() throws Exception {
        // Initialize the database
        mMarathonAchievementRewardGroupRepository.saveAndFlush(mMarathonAchievementRewardGroup);

        // Get all the mMarathonAchievementRewardGroupList where contentAmount equals to DEFAULT_CONTENT_AMOUNT
        defaultMMarathonAchievementRewardGroupShouldBeFound("contentAmount.equals=" + DEFAULT_CONTENT_AMOUNT);

        // Get all the mMarathonAchievementRewardGroupList where contentAmount equals to UPDATED_CONTENT_AMOUNT
        defaultMMarathonAchievementRewardGroupShouldNotBeFound("contentAmount.equals=" + UPDATED_CONTENT_AMOUNT);
    }

    @Test
    @Transactional
    public void getAllMMarathonAchievementRewardGroupsByContentAmountIsInShouldWork() throws Exception {
        // Initialize the database
        mMarathonAchievementRewardGroupRepository.saveAndFlush(mMarathonAchievementRewardGroup);

        // Get all the mMarathonAchievementRewardGroupList where contentAmount in DEFAULT_CONTENT_AMOUNT or UPDATED_CONTENT_AMOUNT
        defaultMMarathonAchievementRewardGroupShouldBeFound("contentAmount.in=" + DEFAULT_CONTENT_AMOUNT + "," + UPDATED_CONTENT_AMOUNT);

        // Get all the mMarathonAchievementRewardGroupList where contentAmount equals to UPDATED_CONTENT_AMOUNT
        defaultMMarathonAchievementRewardGroupShouldNotBeFound("contentAmount.in=" + UPDATED_CONTENT_AMOUNT);
    }

    @Test
    @Transactional
    public void getAllMMarathonAchievementRewardGroupsByContentAmountIsNullOrNotNull() throws Exception {
        // Initialize the database
        mMarathonAchievementRewardGroupRepository.saveAndFlush(mMarathonAchievementRewardGroup);

        // Get all the mMarathonAchievementRewardGroupList where contentAmount is not null
        defaultMMarathonAchievementRewardGroupShouldBeFound("contentAmount.specified=true");

        // Get all the mMarathonAchievementRewardGroupList where contentAmount is null
        defaultMMarathonAchievementRewardGroupShouldNotBeFound("contentAmount.specified=false");
    }

    @Test
    @Transactional
    public void getAllMMarathonAchievementRewardGroupsByContentAmountIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mMarathonAchievementRewardGroupRepository.saveAndFlush(mMarathonAchievementRewardGroup);

        // Get all the mMarathonAchievementRewardGroupList where contentAmount greater than or equals to DEFAULT_CONTENT_AMOUNT
        defaultMMarathonAchievementRewardGroupShouldBeFound("contentAmount.greaterOrEqualThan=" + DEFAULT_CONTENT_AMOUNT);

        // Get all the mMarathonAchievementRewardGroupList where contentAmount greater than or equals to UPDATED_CONTENT_AMOUNT
        defaultMMarathonAchievementRewardGroupShouldNotBeFound("contentAmount.greaterOrEqualThan=" + UPDATED_CONTENT_AMOUNT);
    }

    @Test
    @Transactional
    public void getAllMMarathonAchievementRewardGroupsByContentAmountIsLessThanSomething() throws Exception {
        // Initialize the database
        mMarathonAchievementRewardGroupRepository.saveAndFlush(mMarathonAchievementRewardGroup);

        // Get all the mMarathonAchievementRewardGroupList where contentAmount less than or equals to DEFAULT_CONTENT_AMOUNT
        defaultMMarathonAchievementRewardGroupShouldNotBeFound("contentAmount.lessThan=" + DEFAULT_CONTENT_AMOUNT);

        // Get all the mMarathonAchievementRewardGroupList where contentAmount less than or equals to UPDATED_CONTENT_AMOUNT
        defaultMMarathonAchievementRewardGroupShouldBeFound("contentAmount.lessThan=" + UPDATED_CONTENT_AMOUNT);
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultMMarathonAchievementRewardGroupShouldBeFound(String filter) throws Exception {
        restMMarathonAchievementRewardGroupMockMvc.perform(get("/api/m-marathon-achievement-reward-groups?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(mMarathonAchievementRewardGroup.getId().intValue())))
            .andExpect(jsonPath("$.[*].groupId").value(hasItem(DEFAULT_GROUP_ID)))
            .andExpect(jsonPath("$.[*].contentType").value(hasItem(DEFAULT_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].contentId").value(hasItem(DEFAULT_CONTENT_ID)))
            .andExpect(jsonPath("$.[*].contentAmount").value(hasItem(DEFAULT_CONTENT_AMOUNT)));

        // Check, that the count call also returns 1
        restMMarathonAchievementRewardGroupMockMvc.perform(get("/api/m-marathon-achievement-reward-groups/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultMMarathonAchievementRewardGroupShouldNotBeFound(String filter) throws Exception {
        restMMarathonAchievementRewardGroupMockMvc.perform(get("/api/m-marathon-achievement-reward-groups?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restMMarathonAchievementRewardGroupMockMvc.perform(get("/api/m-marathon-achievement-reward-groups/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("0"));
    }


    @Test
    @Transactional
    public void getNonExistingMMarathonAchievementRewardGroup() throws Exception {
        // Get the mMarathonAchievementRewardGroup
        restMMarathonAchievementRewardGroupMockMvc.perform(get("/api/m-marathon-achievement-reward-groups/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateMMarathonAchievementRewardGroup() throws Exception {
        // Initialize the database
        mMarathonAchievementRewardGroupRepository.saveAndFlush(mMarathonAchievementRewardGroup);

        int databaseSizeBeforeUpdate = mMarathonAchievementRewardGroupRepository.findAll().size();

        // Update the mMarathonAchievementRewardGroup
        MMarathonAchievementRewardGroup updatedMMarathonAchievementRewardGroup = mMarathonAchievementRewardGroupRepository.findById(mMarathonAchievementRewardGroup.getId()).get();
        // Disconnect from session so that the updates on updatedMMarathonAchievementRewardGroup are not directly saved in db
        em.detach(updatedMMarathonAchievementRewardGroup);
        updatedMMarathonAchievementRewardGroup
            .groupId(UPDATED_GROUP_ID)
            .contentType(UPDATED_CONTENT_TYPE)
            .contentId(UPDATED_CONTENT_ID)
            .contentAmount(UPDATED_CONTENT_AMOUNT);
        MMarathonAchievementRewardGroupDTO mMarathonAchievementRewardGroupDTO = mMarathonAchievementRewardGroupMapper.toDto(updatedMMarathonAchievementRewardGroup);

        restMMarathonAchievementRewardGroupMockMvc.perform(put("/api/m-marathon-achievement-reward-groups")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mMarathonAchievementRewardGroupDTO)))
            .andExpect(status().isOk());

        // Validate the MMarathonAchievementRewardGroup in the database
        List<MMarathonAchievementRewardGroup> mMarathonAchievementRewardGroupList = mMarathonAchievementRewardGroupRepository.findAll();
        assertThat(mMarathonAchievementRewardGroupList).hasSize(databaseSizeBeforeUpdate);
        MMarathonAchievementRewardGroup testMMarathonAchievementRewardGroup = mMarathonAchievementRewardGroupList.get(mMarathonAchievementRewardGroupList.size() - 1);
        assertThat(testMMarathonAchievementRewardGroup.getGroupId()).isEqualTo(UPDATED_GROUP_ID);
        assertThat(testMMarathonAchievementRewardGroup.getContentType()).isEqualTo(UPDATED_CONTENT_TYPE);
        assertThat(testMMarathonAchievementRewardGroup.getContentId()).isEqualTo(UPDATED_CONTENT_ID);
        assertThat(testMMarathonAchievementRewardGroup.getContentAmount()).isEqualTo(UPDATED_CONTENT_AMOUNT);
    }

    @Test
    @Transactional
    public void updateNonExistingMMarathonAchievementRewardGroup() throws Exception {
        int databaseSizeBeforeUpdate = mMarathonAchievementRewardGroupRepository.findAll().size();

        // Create the MMarathonAchievementRewardGroup
        MMarathonAchievementRewardGroupDTO mMarathonAchievementRewardGroupDTO = mMarathonAchievementRewardGroupMapper.toDto(mMarathonAchievementRewardGroup);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restMMarathonAchievementRewardGroupMockMvc.perform(put("/api/m-marathon-achievement-reward-groups")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mMarathonAchievementRewardGroupDTO)))
            .andExpect(status().isBadRequest());

        // Validate the MMarathonAchievementRewardGroup in the database
        List<MMarathonAchievementRewardGroup> mMarathonAchievementRewardGroupList = mMarathonAchievementRewardGroupRepository.findAll();
        assertThat(mMarathonAchievementRewardGroupList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteMMarathonAchievementRewardGroup() throws Exception {
        // Initialize the database
        mMarathonAchievementRewardGroupRepository.saveAndFlush(mMarathonAchievementRewardGroup);

        int databaseSizeBeforeDelete = mMarathonAchievementRewardGroupRepository.findAll().size();

        // Delete the mMarathonAchievementRewardGroup
        restMMarathonAchievementRewardGroupMockMvc.perform(delete("/api/m-marathon-achievement-reward-groups/{id}", mMarathonAchievementRewardGroup.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database is empty
        List<MMarathonAchievementRewardGroup> mMarathonAchievementRewardGroupList = mMarathonAchievementRewardGroupRepository.findAll();
        assertThat(mMarathonAchievementRewardGroupList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(MMarathonAchievementRewardGroup.class);
        MMarathonAchievementRewardGroup mMarathonAchievementRewardGroup1 = new MMarathonAchievementRewardGroup();
        mMarathonAchievementRewardGroup1.setId(1L);
        MMarathonAchievementRewardGroup mMarathonAchievementRewardGroup2 = new MMarathonAchievementRewardGroup();
        mMarathonAchievementRewardGroup2.setId(mMarathonAchievementRewardGroup1.getId());
        assertThat(mMarathonAchievementRewardGroup1).isEqualTo(mMarathonAchievementRewardGroup2);
        mMarathonAchievementRewardGroup2.setId(2L);
        assertThat(mMarathonAchievementRewardGroup1).isNotEqualTo(mMarathonAchievementRewardGroup2);
        mMarathonAchievementRewardGroup1.setId(null);
        assertThat(mMarathonAchievementRewardGroup1).isNotEqualTo(mMarathonAchievementRewardGroup2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(MMarathonAchievementRewardGroupDTO.class);
        MMarathonAchievementRewardGroupDTO mMarathonAchievementRewardGroupDTO1 = new MMarathonAchievementRewardGroupDTO();
        mMarathonAchievementRewardGroupDTO1.setId(1L);
        MMarathonAchievementRewardGroupDTO mMarathonAchievementRewardGroupDTO2 = new MMarathonAchievementRewardGroupDTO();
        assertThat(mMarathonAchievementRewardGroupDTO1).isNotEqualTo(mMarathonAchievementRewardGroupDTO2);
        mMarathonAchievementRewardGroupDTO2.setId(mMarathonAchievementRewardGroupDTO1.getId());
        assertThat(mMarathonAchievementRewardGroupDTO1).isEqualTo(mMarathonAchievementRewardGroupDTO2);
        mMarathonAchievementRewardGroupDTO2.setId(2L);
        assertThat(mMarathonAchievementRewardGroupDTO1).isNotEqualTo(mMarathonAchievementRewardGroupDTO2);
        mMarathonAchievementRewardGroupDTO1.setId(null);
        assertThat(mMarathonAchievementRewardGroupDTO1).isNotEqualTo(mMarathonAchievementRewardGroupDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(mMarathonAchievementRewardGroupMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(mMarathonAchievementRewardGroupMapper.fromId(null)).isNull();
    }
}
