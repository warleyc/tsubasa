package io.shm.tsubasa.web.rest;

import io.shm.tsubasa.TsubasaApp;
import io.shm.tsubasa.domain.MQuestAchievementGroup;
import io.shm.tsubasa.repository.MQuestAchievementGroupRepository;
import io.shm.tsubasa.service.MQuestAchievementGroupService;
import io.shm.tsubasa.service.dto.MQuestAchievementGroupDTO;
import io.shm.tsubasa.service.mapper.MQuestAchievementGroupMapper;
import io.shm.tsubasa.web.rest.errors.ExceptionTranslator;
import io.shm.tsubasa.service.dto.MQuestAchievementGroupCriteria;
import io.shm.tsubasa.service.MQuestAchievementGroupQueryService;

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
 * Integration tests for the {@Link MQuestAchievementGroupResource} REST controller.
 */
@SpringBootTest(classes = TsubasaApp.class)
public class MQuestAchievementGroupResourceIT {

    private static final Integer DEFAULT_GROUP_ID = 1;
    private static final Integer UPDATED_GROUP_ID = 2;

    private static final Integer DEFAULT_ACHIEVEMENT_TYPE = 1;
    private static final Integer UPDATED_ACHIEVEMENT_TYPE = 2;

    private static final Integer DEFAULT_RANK = 1;
    private static final Integer UPDATED_RANK = 2;

    private static final Integer DEFAULT_WEIGHT = 1;
    private static final Integer UPDATED_WEIGHT = 2;

    private static final Integer DEFAULT_CONTENT_TYPE = 1;
    private static final Integer UPDATED_CONTENT_TYPE = 2;

    private static final Integer DEFAULT_CONTENT_ID = 1;
    private static final Integer UPDATED_CONTENT_ID = 2;

    private static final Integer DEFAULT_CONTENT_AMOUNT = 1;
    private static final Integer UPDATED_CONTENT_AMOUNT = 2;

    @Autowired
    private MQuestAchievementGroupRepository mQuestAchievementGroupRepository;

    @Autowired
    private MQuestAchievementGroupMapper mQuestAchievementGroupMapper;

    @Autowired
    private MQuestAchievementGroupService mQuestAchievementGroupService;

    @Autowired
    private MQuestAchievementGroupQueryService mQuestAchievementGroupQueryService;

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

    private MockMvc restMQuestAchievementGroupMockMvc;

    private MQuestAchievementGroup mQuestAchievementGroup;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final MQuestAchievementGroupResource mQuestAchievementGroupResource = new MQuestAchievementGroupResource(mQuestAchievementGroupService, mQuestAchievementGroupQueryService);
        this.restMQuestAchievementGroupMockMvc = MockMvcBuilders.standaloneSetup(mQuestAchievementGroupResource)
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
    public static MQuestAchievementGroup createEntity(EntityManager em) {
        MQuestAchievementGroup mQuestAchievementGroup = new MQuestAchievementGroup()
            .groupId(DEFAULT_GROUP_ID)
            .achievementType(DEFAULT_ACHIEVEMENT_TYPE)
            .rank(DEFAULT_RANK)
            .weight(DEFAULT_WEIGHT)
            .contentType(DEFAULT_CONTENT_TYPE)
            .contentId(DEFAULT_CONTENT_ID)
            .contentAmount(DEFAULT_CONTENT_AMOUNT);
        return mQuestAchievementGroup;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static MQuestAchievementGroup createUpdatedEntity(EntityManager em) {
        MQuestAchievementGroup mQuestAchievementGroup = new MQuestAchievementGroup()
            .groupId(UPDATED_GROUP_ID)
            .achievementType(UPDATED_ACHIEVEMENT_TYPE)
            .rank(UPDATED_RANK)
            .weight(UPDATED_WEIGHT)
            .contentType(UPDATED_CONTENT_TYPE)
            .contentId(UPDATED_CONTENT_ID)
            .contentAmount(UPDATED_CONTENT_AMOUNT);
        return mQuestAchievementGroup;
    }

    @BeforeEach
    public void initTest() {
        mQuestAchievementGroup = createEntity(em);
    }

    @Test
    @Transactional
    public void createMQuestAchievementGroup() throws Exception {
        int databaseSizeBeforeCreate = mQuestAchievementGroupRepository.findAll().size();

        // Create the MQuestAchievementGroup
        MQuestAchievementGroupDTO mQuestAchievementGroupDTO = mQuestAchievementGroupMapper.toDto(mQuestAchievementGroup);
        restMQuestAchievementGroupMockMvc.perform(post("/api/m-quest-achievement-groups")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mQuestAchievementGroupDTO)))
            .andExpect(status().isCreated());

        // Validate the MQuestAchievementGroup in the database
        List<MQuestAchievementGroup> mQuestAchievementGroupList = mQuestAchievementGroupRepository.findAll();
        assertThat(mQuestAchievementGroupList).hasSize(databaseSizeBeforeCreate + 1);
        MQuestAchievementGroup testMQuestAchievementGroup = mQuestAchievementGroupList.get(mQuestAchievementGroupList.size() - 1);
        assertThat(testMQuestAchievementGroup.getGroupId()).isEqualTo(DEFAULT_GROUP_ID);
        assertThat(testMQuestAchievementGroup.getAchievementType()).isEqualTo(DEFAULT_ACHIEVEMENT_TYPE);
        assertThat(testMQuestAchievementGroup.getRank()).isEqualTo(DEFAULT_RANK);
        assertThat(testMQuestAchievementGroup.getWeight()).isEqualTo(DEFAULT_WEIGHT);
        assertThat(testMQuestAchievementGroup.getContentType()).isEqualTo(DEFAULT_CONTENT_TYPE);
        assertThat(testMQuestAchievementGroup.getContentId()).isEqualTo(DEFAULT_CONTENT_ID);
        assertThat(testMQuestAchievementGroup.getContentAmount()).isEqualTo(DEFAULT_CONTENT_AMOUNT);
    }

    @Test
    @Transactional
    public void createMQuestAchievementGroupWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = mQuestAchievementGroupRepository.findAll().size();

        // Create the MQuestAchievementGroup with an existing ID
        mQuestAchievementGroup.setId(1L);
        MQuestAchievementGroupDTO mQuestAchievementGroupDTO = mQuestAchievementGroupMapper.toDto(mQuestAchievementGroup);

        // An entity with an existing ID cannot be created, so this API call must fail
        restMQuestAchievementGroupMockMvc.perform(post("/api/m-quest-achievement-groups")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mQuestAchievementGroupDTO)))
            .andExpect(status().isBadRequest());

        // Validate the MQuestAchievementGroup in the database
        List<MQuestAchievementGroup> mQuestAchievementGroupList = mQuestAchievementGroupRepository.findAll();
        assertThat(mQuestAchievementGroupList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkGroupIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = mQuestAchievementGroupRepository.findAll().size();
        // set the field null
        mQuestAchievementGroup.setGroupId(null);

        // Create the MQuestAchievementGroup, which fails.
        MQuestAchievementGroupDTO mQuestAchievementGroupDTO = mQuestAchievementGroupMapper.toDto(mQuestAchievementGroup);

        restMQuestAchievementGroupMockMvc.perform(post("/api/m-quest-achievement-groups")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mQuestAchievementGroupDTO)))
            .andExpect(status().isBadRequest());

        List<MQuestAchievementGroup> mQuestAchievementGroupList = mQuestAchievementGroupRepository.findAll();
        assertThat(mQuestAchievementGroupList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkAchievementTypeIsRequired() throws Exception {
        int databaseSizeBeforeTest = mQuestAchievementGroupRepository.findAll().size();
        // set the field null
        mQuestAchievementGroup.setAchievementType(null);

        // Create the MQuestAchievementGroup, which fails.
        MQuestAchievementGroupDTO mQuestAchievementGroupDTO = mQuestAchievementGroupMapper.toDto(mQuestAchievementGroup);

        restMQuestAchievementGroupMockMvc.perform(post("/api/m-quest-achievement-groups")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mQuestAchievementGroupDTO)))
            .andExpect(status().isBadRequest());

        List<MQuestAchievementGroup> mQuestAchievementGroupList = mQuestAchievementGroupRepository.findAll();
        assertThat(mQuestAchievementGroupList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkRankIsRequired() throws Exception {
        int databaseSizeBeforeTest = mQuestAchievementGroupRepository.findAll().size();
        // set the field null
        mQuestAchievementGroup.setRank(null);

        // Create the MQuestAchievementGroup, which fails.
        MQuestAchievementGroupDTO mQuestAchievementGroupDTO = mQuestAchievementGroupMapper.toDto(mQuestAchievementGroup);

        restMQuestAchievementGroupMockMvc.perform(post("/api/m-quest-achievement-groups")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mQuestAchievementGroupDTO)))
            .andExpect(status().isBadRequest());

        List<MQuestAchievementGroup> mQuestAchievementGroupList = mQuestAchievementGroupRepository.findAll();
        assertThat(mQuestAchievementGroupList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkWeightIsRequired() throws Exception {
        int databaseSizeBeforeTest = mQuestAchievementGroupRepository.findAll().size();
        // set the field null
        mQuestAchievementGroup.setWeight(null);

        // Create the MQuestAchievementGroup, which fails.
        MQuestAchievementGroupDTO mQuestAchievementGroupDTO = mQuestAchievementGroupMapper.toDto(mQuestAchievementGroup);

        restMQuestAchievementGroupMockMvc.perform(post("/api/m-quest-achievement-groups")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mQuestAchievementGroupDTO)))
            .andExpect(status().isBadRequest());

        List<MQuestAchievementGroup> mQuestAchievementGroupList = mQuestAchievementGroupRepository.findAll();
        assertThat(mQuestAchievementGroupList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkContentTypeIsRequired() throws Exception {
        int databaseSizeBeforeTest = mQuestAchievementGroupRepository.findAll().size();
        // set the field null
        mQuestAchievementGroup.setContentType(null);

        // Create the MQuestAchievementGroup, which fails.
        MQuestAchievementGroupDTO mQuestAchievementGroupDTO = mQuestAchievementGroupMapper.toDto(mQuestAchievementGroup);

        restMQuestAchievementGroupMockMvc.perform(post("/api/m-quest-achievement-groups")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mQuestAchievementGroupDTO)))
            .andExpect(status().isBadRequest());

        List<MQuestAchievementGroup> mQuestAchievementGroupList = mQuestAchievementGroupRepository.findAll();
        assertThat(mQuestAchievementGroupList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkContentAmountIsRequired() throws Exception {
        int databaseSizeBeforeTest = mQuestAchievementGroupRepository.findAll().size();
        // set the field null
        mQuestAchievementGroup.setContentAmount(null);

        // Create the MQuestAchievementGroup, which fails.
        MQuestAchievementGroupDTO mQuestAchievementGroupDTO = mQuestAchievementGroupMapper.toDto(mQuestAchievementGroup);

        restMQuestAchievementGroupMockMvc.perform(post("/api/m-quest-achievement-groups")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mQuestAchievementGroupDTO)))
            .andExpect(status().isBadRequest());

        List<MQuestAchievementGroup> mQuestAchievementGroupList = mQuestAchievementGroupRepository.findAll();
        assertThat(mQuestAchievementGroupList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllMQuestAchievementGroups() throws Exception {
        // Initialize the database
        mQuestAchievementGroupRepository.saveAndFlush(mQuestAchievementGroup);

        // Get all the mQuestAchievementGroupList
        restMQuestAchievementGroupMockMvc.perform(get("/api/m-quest-achievement-groups?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(mQuestAchievementGroup.getId().intValue())))
            .andExpect(jsonPath("$.[*].groupId").value(hasItem(DEFAULT_GROUP_ID)))
            .andExpect(jsonPath("$.[*].achievementType").value(hasItem(DEFAULT_ACHIEVEMENT_TYPE)))
            .andExpect(jsonPath("$.[*].rank").value(hasItem(DEFAULT_RANK)))
            .andExpect(jsonPath("$.[*].weight").value(hasItem(DEFAULT_WEIGHT)))
            .andExpect(jsonPath("$.[*].contentType").value(hasItem(DEFAULT_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].contentId").value(hasItem(DEFAULT_CONTENT_ID)))
            .andExpect(jsonPath("$.[*].contentAmount").value(hasItem(DEFAULT_CONTENT_AMOUNT)));
    }
    
    @Test
    @Transactional
    public void getMQuestAchievementGroup() throws Exception {
        // Initialize the database
        mQuestAchievementGroupRepository.saveAndFlush(mQuestAchievementGroup);

        // Get the mQuestAchievementGroup
        restMQuestAchievementGroupMockMvc.perform(get("/api/m-quest-achievement-groups/{id}", mQuestAchievementGroup.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(mQuestAchievementGroup.getId().intValue()))
            .andExpect(jsonPath("$.groupId").value(DEFAULT_GROUP_ID))
            .andExpect(jsonPath("$.achievementType").value(DEFAULT_ACHIEVEMENT_TYPE))
            .andExpect(jsonPath("$.rank").value(DEFAULT_RANK))
            .andExpect(jsonPath("$.weight").value(DEFAULT_WEIGHT))
            .andExpect(jsonPath("$.contentType").value(DEFAULT_CONTENT_TYPE))
            .andExpect(jsonPath("$.contentId").value(DEFAULT_CONTENT_ID))
            .andExpect(jsonPath("$.contentAmount").value(DEFAULT_CONTENT_AMOUNT));
    }

    @Test
    @Transactional
    public void getAllMQuestAchievementGroupsByGroupIdIsEqualToSomething() throws Exception {
        // Initialize the database
        mQuestAchievementGroupRepository.saveAndFlush(mQuestAchievementGroup);

        // Get all the mQuestAchievementGroupList where groupId equals to DEFAULT_GROUP_ID
        defaultMQuestAchievementGroupShouldBeFound("groupId.equals=" + DEFAULT_GROUP_ID);

        // Get all the mQuestAchievementGroupList where groupId equals to UPDATED_GROUP_ID
        defaultMQuestAchievementGroupShouldNotBeFound("groupId.equals=" + UPDATED_GROUP_ID);
    }

    @Test
    @Transactional
    public void getAllMQuestAchievementGroupsByGroupIdIsInShouldWork() throws Exception {
        // Initialize the database
        mQuestAchievementGroupRepository.saveAndFlush(mQuestAchievementGroup);

        // Get all the mQuestAchievementGroupList where groupId in DEFAULT_GROUP_ID or UPDATED_GROUP_ID
        defaultMQuestAchievementGroupShouldBeFound("groupId.in=" + DEFAULT_GROUP_ID + "," + UPDATED_GROUP_ID);

        // Get all the mQuestAchievementGroupList where groupId equals to UPDATED_GROUP_ID
        defaultMQuestAchievementGroupShouldNotBeFound("groupId.in=" + UPDATED_GROUP_ID);
    }

    @Test
    @Transactional
    public void getAllMQuestAchievementGroupsByGroupIdIsNullOrNotNull() throws Exception {
        // Initialize the database
        mQuestAchievementGroupRepository.saveAndFlush(mQuestAchievementGroup);

        // Get all the mQuestAchievementGroupList where groupId is not null
        defaultMQuestAchievementGroupShouldBeFound("groupId.specified=true");

        // Get all the mQuestAchievementGroupList where groupId is null
        defaultMQuestAchievementGroupShouldNotBeFound("groupId.specified=false");
    }

    @Test
    @Transactional
    public void getAllMQuestAchievementGroupsByGroupIdIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mQuestAchievementGroupRepository.saveAndFlush(mQuestAchievementGroup);

        // Get all the mQuestAchievementGroupList where groupId greater than or equals to DEFAULT_GROUP_ID
        defaultMQuestAchievementGroupShouldBeFound("groupId.greaterOrEqualThan=" + DEFAULT_GROUP_ID);

        // Get all the mQuestAchievementGroupList where groupId greater than or equals to UPDATED_GROUP_ID
        defaultMQuestAchievementGroupShouldNotBeFound("groupId.greaterOrEqualThan=" + UPDATED_GROUP_ID);
    }

    @Test
    @Transactional
    public void getAllMQuestAchievementGroupsByGroupIdIsLessThanSomething() throws Exception {
        // Initialize the database
        mQuestAchievementGroupRepository.saveAndFlush(mQuestAchievementGroup);

        // Get all the mQuestAchievementGroupList where groupId less than or equals to DEFAULT_GROUP_ID
        defaultMQuestAchievementGroupShouldNotBeFound("groupId.lessThan=" + DEFAULT_GROUP_ID);

        // Get all the mQuestAchievementGroupList where groupId less than or equals to UPDATED_GROUP_ID
        defaultMQuestAchievementGroupShouldBeFound("groupId.lessThan=" + UPDATED_GROUP_ID);
    }


    @Test
    @Transactional
    public void getAllMQuestAchievementGroupsByAchievementTypeIsEqualToSomething() throws Exception {
        // Initialize the database
        mQuestAchievementGroupRepository.saveAndFlush(mQuestAchievementGroup);

        // Get all the mQuestAchievementGroupList where achievementType equals to DEFAULT_ACHIEVEMENT_TYPE
        defaultMQuestAchievementGroupShouldBeFound("achievementType.equals=" + DEFAULT_ACHIEVEMENT_TYPE);

        // Get all the mQuestAchievementGroupList where achievementType equals to UPDATED_ACHIEVEMENT_TYPE
        defaultMQuestAchievementGroupShouldNotBeFound("achievementType.equals=" + UPDATED_ACHIEVEMENT_TYPE);
    }

    @Test
    @Transactional
    public void getAllMQuestAchievementGroupsByAchievementTypeIsInShouldWork() throws Exception {
        // Initialize the database
        mQuestAchievementGroupRepository.saveAndFlush(mQuestAchievementGroup);

        // Get all the mQuestAchievementGroupList where achievementType in DEFAULT_ACHIEVEMENT_TYPE or UPDATED_ACHIEVEMENT_TYPE
        defaultMQuestAchievementGroupShouldBeFound("achievementType.in=" + DEFAULT_ACHIEVEMENT_TYPE + "," + UPDATED_ACHIEVEMENT_TYPE);

        // Get all the mQuestAchievementGroupList where achievementType equals to UPDATED_ACHIEVEMENT_TYPE
        defaultMQuestAchievementGroupShouldNotBeFound("achievementType.in=" + UPDATED_ACHIEVEMENT_TYPE);
    }

    @Test
    @Transactional
    public void getAllMQuestAchievementGroupsByAchievementTypeIsNullOrNotNull() throws Exception {
        // Initialize the database
        mQuestAchievementGroupRepository.saveAndFlush(mQuestAchievementGroup);

        // Get all the mQuestAchievementGroupList where achievementType is not null
        defaultMQuestAchievementGroupShouldBeFound("achievementType.specified=true");

        // Get all the mQuestAchievementGroupList where achievementType is null
        defaultMQuestAchievementGroupShouldNotBeFound("achievementType.specified=false");
    }

    @Test
    @Transactional
    public void getAllMQuestAchievementGroupsByAchievementTypeIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mQuestAchievementGroupRepository.saveAndFlush(mQuestAchievementGroup);

        // Get all the mQuestAchievementGroupList where achievementType greater than or equals to DEFAULT_ACHIEVEMENT_TYPE
        defaultMQuestAchievementGroupShouldBeFound("achievementType.greaterOrEqualThan=" + DEFAULT_ACHIEVEMENT_TYPE);

        // Get all the mQuestAchievementGroupList where achievementType greater than or equals to UPDATED_ACHIEVEMENT_TYPE
        defaultMQuestAchievementGroupShouldNotBeFound("achievementType.greaterOrEqualThan=" + UPDATED_ACHIEVEMENT_TYPE);
    }

    @Test
    @Transactional
    public void getAllMQuestAchievementGroupsByAchievementTypeIsLessThanSomething() throws Exception {
        // Initialize the database
        mQuestAchievementGroupRepository.saveAndFlush(mQuestAchievementGroup);

        // Get all the mQuestAchievementGroupList where achievementType less than or equals to DEFAULT_ACHIEVEMENT_TYPE
        defaultMQuestAchievementGroupShouldNotBeFound("achievementType.lessThan=" + DEFAULT_ACHIEVEMENT_TYPE);

        // Get all the mQuestAchievementGroupList where achievementType less than or equals to UPDATED_ACHIEVEMENT_TYPE
        defaultMQuestAchievementGroupShouldBeFound("achievementType.lessThan=" + UPDATED_ACHIEVEMENT_TYPE);
    }


    @Test
    @Transactional
    public void getAllMQuestAchievementGroupsByRankIsEqualToSomething() throws Exception {
        // Initialize the database
        mQuestAchievementGroupRepository.saveAndFlush(mQuestAchievementGroup);

        // Get all the mQuestAchievementGroupList where rank equals to DEFAULT_RANK
        defaultMQuestAchievementGroupShouldBeFound("rank.equals=" + DEFAULT_RANK);

        // Get all the mQuestAchievementGroupList where rank equals to UPDATED_RANK
        defaultMQuestAchievementGroupShouldNotBeFound("rank.equals=" + UPDATED_RANK);
    }

    @Test
    @Transactional
    public void getAllMQuestAchievementGroupsByRankIsInShouldWork() throws Exception {
        // Initialize the database
        mQuestAchievementGroupRepository.saveAndFlush(mQuestAchievementGroup);

        // Get all the mQuestAchievementGroupList where rank in DEFAULT_RANK or UPDATED_RANK
        defaultMQuestAchievementGroupShouldBeFound("rank.in=" + DEFAULT_RANK + "," + UPDATED_RANK);

        // Get all the mQuestAchievementGroupList where rank equals to UPDATED_RANK
        defaultMQuestAchievementGroupShouldNotBeFound("rank.in=" + UPDATED_RANK);
    }

    @Test
    @Transactional
    public void getAllMQuestAchievementGroupsByRankIsNullOrNotNull() throws Exception {
        // Initialize the database
        mQuestAchievementGroupRepository.saveAndFlush(mQuestAchievementGroup);

        // Get all the mQuestAchievementGroupList where rank is not null
        defaultMQuestAchievementGroupShouldBeFound("rank.specified=true");

        // Get all the mQuestAchievementGroupList where rank is null
        defaultMQuestAchievementGroupShouldNotBeFound("rank.specified=false");
    }

    @Test
    @Transactional
    public void getAllMQuestAchievementGroupsByRankIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mQuestAchievementGroupRepository.saveAndFlush(mQuestAchievementGroup);

        // Get all the mQuestAchievementGroupList where rank greater than or equals to DEFAULT_RANK
        defaultMQuestAchievementGroupShouldBeFound("rank.greaterOrEqualThan=" + DEFAULT_RANK);

        // Get all the mQuestAchievementGroupList where rank greater than or equals to UPDATED_RANK
        defaultMQuestAchievementGroupShouldNotBeFound("rank.greaterOrEqualThan=" + UPDATED_RANK);
    }

    @Test
    @Transactional
    public void getAllMQuestAchievementGroupsByRankIsLessThanSomething() throws Exception {
        // Initialize the database
        mQuestAchievementGroupRepository.saveAndFlush(mQuestAchievementGroup);

        // Get all the mQuestAchievementGroupList where rank less than or equals to DEFAULT_RANK
        defaultMQuestAchievementGroupShouldNotBeFound("rank.lessThan=" + DEFAULT_RANK);

        // Get all the mQuestAchievementGroupList where rank less than or equals to UPDATED_RANK
        defaultMQuestAchievementGroupShouldBeFound("rank.lessThan=" + UPDATED_RANK);
    }


    @Test
    @Transactional
    public void getAllMQuestAchievementGroupsByWeightIsEqualToSomething() throws Exception {
        // Initialize the database
        mQuestAchievementGroupRepository.saveAndFlush(mQuestAchievementGroup);

        // Get all the mQuestAchievementGroupList where weight equals to DEFAULT_WEIGHT
        defaultMQuestAchievementGroupShouldBeFound("weight.equals=" + DEFAULT_WEIGHT);

        // Get all the mQuestAchievementGroupList where weight equals to UPDATED_WEIGHT
        defaultMQuestAchievementGroupShouldNotBeFound("weight.equals=" + UPDATED_WEIGHT);
    }

    @Test
    @Transactional
    public void getAllMQuestAchievementGroupsByWeightIsInShouldWork() throws Exception {
        // Initialize the database
        mQuestAchievementGroupRepository.saveAndFlush(mQuestAchievementGroup);

        // Get all the mQuestAchievementGroupList where weight in DEFAULT_WEIGHT or UPDATED_WEIGHT
        defaultMQuestAchievementGroupShouldBeFound("weight.in=" + DEFAULT_WEIGHT + "," + UPDATED_WEIGHT);

        // Get all the mQuestAchievementGroupList where weight equals to UPDATED_WEIGHT
        defaultMQuestAchievementGroupShouldNotBeFound("weight.in=" + UPDATED_WEIGHT);
    }

    @Test
    @Transactional
    public void getAllMQuestAchievementGroupsByWeightIsNullOrNotNull() throws Exception {
        // Initialize the database
        mQuestAchievementGroupRepository.saveAndFlush(mQuestAchievementGroup);

        // Get all the mQuestAchievementGroupList where weight is not null
        defaultMQuestAchievementGroupShouldBeFound("weight.specified=true");

        // Get all the mQuestAchievementGroupList where weight is null
        defaultMQuestAchievementGroupShouldNotBeFound("weight.specified=false");
    }

    @Test
    @Transactional
    public void getAllMQuestAchievementGroupsByWeightIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mQuestAchievementGroupRepository.saveAndFlush(mQuestAchievementGroup);

        // Get all the mQuestAchievementGroupList where weight greater than or equals to DEFAULT_WEIGHT
        defaultMQuestAchievementGroupShouldBeFound("weight.greaterOrEqualThan=" + DEFAULT_WEIGHT);

        // Get all the mQuestAchievementGroupList where weight greater than or equals to UPDATED_WEIGHT
        defaultMQuestAchievementGroupShouldNotBeFound("weight.greaterOrEqualThan=" + UPDATED_WEIGHT);
    }

    @Test
    @Transactional
    public void getAllMQuestAchievementGroupsByWeightIsLessThanSomething() throws Exception {
        // Initialize the database
        mQuestAchievementGroupRepository.saveAndFlush(mQuestAchievementGroup);

        // Get all the mQuestAchievementGroupList where weight less than or equals to DEFAULT_WEIGHT
        defaultMQuestAchievementGroupShouldNotBeFound("weight.lessThan=" + DEFAULT_WEIGHT);

        // Get all the mQuestAchievementGroupList where weight less than or equals to UPDATED_WEIGHT
        defaultMQuestAchievementGroupShouldBeFound("weight.lessThan=" + UPDATED_WEIGHT);
    }


    @Test
    @Transactional
    public void getAllMQuestAchievementGroupsByContentTypeIsEqualToSomething() throws Exception {
        // Initialize the database
        mQuestAchievementGroupRepository.saveAndFlush(mQuestAchievementGroup);

        // Get all the mQuestAchievementGroupList where contentType equals to DEFAULT_CONTENT_TYPE
        defaultMQuestAchievementGroupShouldBeFound("contentType.equals=" + DEFAULT_CONTENT_TYPE);

        // Get all the mQuestAchievementGroupList where contentType equals to UPDATED_CONTENT_TYPE
        defaultMQuestAchievementGroupShouldNotBeFound("contentType.equals=" + UPDATED_CONTENT_TYPE);
    }

    @Test
    @Transactional
    public void getAllMQuestAchievementGroupsByContentTypeIsInShouldWork() throws Exception {
        // Initialize the database
        mQuestAchievementGroupRepository.saveAndFlush(mQuestAchievementGroup);

        // Get all the mQuestAchievementGroupList where contentType in DEFAULT_CONTENT_TYPE or UPDATED_CONTENT_TYPE
        defaultMQuestAchievementGroupShouldBeFound("contentType.in=" + DEFAULT_CONTENT_TYPE + "," + UPDATED_CONTENT_TYPE);

        // Get all the mQuestAchievementGroupList where contentType equals to UPDATED_CONTENT_TYPE
        defaultMQuestAchievementGroupShouldNotBeFound("contentType.in=" + UPDATED_CONTENT_TYPE);
    }

    @Test
    @Transactional
    public void getAllMQuestAchievementGroupsByContentTypeIsNullOrNotNull() throws Exception {
        // Initialize the database
        mQuestAchievementGroupRepository.saveAndFlush(mQuestAchievementGroup);

        // Get all the mQuestAchievementGroupList where contentType is not null
        defaultMQuestAchievementGroupShouldBeFound("contentType.specified=true");

        // Get all the mQuestAchievementGroupList where contentType is null
        defaultMQuestAchievementGroupShouldNotBeFound("contentType.specified=false");
    }

    @Test
    @Transactional
    public void getAllMQuestAchievementGroupsByContentTypeIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mQuestAchievementGroupRepository.saveAndFlush(mQuestAchievementGroup);

        // Get all the mQuestAchievementGroupList where contentType greater than or equals to DEFAULT_CONTENT_TYPE
        defaultMQuestAchievementGroupShouldBeFound("contentType.greaterOrEqualThan=" + DEFAULT_CONTENT_TYPE);

        // Get all the mQuestAchievementGroupList where contentType greater than or equals to UPDATED_CONTENT_TYPE
        defaultMQuestAchievementGroupShouldNotBeFound("contentType.greaterOrEqualThan=" + UPDATED_CONTENT_TYPE);
    }

    @Test
    @Transactional
    public void getAllMQuestAchievementGroupsByContentTypeIsLessThanSomething() throws Exception {
        // Initialize the database
        mQuestAchievementGroupRepository.saveAndFlush(mQuestAchievementGroup);

        // Get all the mQuestAchievementGroupList where contentType less than or equals to DEFAULT_CONTENT_TYPE
        defaultMQuestAchievementGroupShouldNotBeFound("contentType.lessThan=" + DEFAULT_CONTENT_TYPE);

        // Get all the mQuestAchievementGroupList where contentType less than or equals to UPDATED_CONTENT_TYPE
        defaultMQuestAchievementGroupShouldBeFound("contentType.lessThan=" + UPDATED_CONTENT_TYPE);
    }


    @Test
    @Transactional
    public void getAllMQuestAchievementGroupsByContentIdIsEqualToSomething() throws Exception {
        // Initialize the database
        mQuestAchievementGroupRepository.saveAndFlush(mQuestAchievementGroup);

        // Get all the mQuestAchievementGroupList where contentId equals to DEFAULT_CONTENT_ID
        defaultMQuestAchievementGroupShouldBeFound("contentId.equals=" + DEFAULT_CONTENT_ID);

        // Get all the mQuestAchievementGroupList where contentId equals to UPDATED_CONTENT_ID
        defaultMQuestAchievementGroupShouldNotBeFound("contentId.equals=" + UPDATED_CONTENT_ID);
    }

    @Test
    @Transactional
    public void getAllMQuestAchievementGroupsByContentIdIsInShouldWork() throws Exception {
        // Initialize the database
        mQuestAchievementGroupRepository.saveAndFlush(mQuestAchievementGroup);

        // Get all the mQuestAchievementGroupList where contentId in DEFAULT_CONTENT_ID or UPDATED_CONTENT_ID
        defaultMQuestAchievementGroupShouldBeFound("contentId.in=" + DEFAULT_CONTENT_ID + "," + UPDATED_CONTENT_ID);

        // Get all the mQuestAchievementGroupList where contentId equals to UPDATED_CONTENT_ID
        defaultMQuestAchievementGroupShouldNotBeFound("contentId.in=" + UPDATED_CONTENT_ID);
    }

    @Test
    @Transactional
    public void getAllMQuestAchievementGroupsByContentIdIsNullOrNotNull() throws Exception {
        // Initialize the database
        mQuestAchievementGroupRepository.saveAndFlush(mQuestAchievementGroup);

        // Get all the mQuestAchievementGroupList where contentId is not null
        defaultMQuestAchievementGroupShouldBeFound("contentId.specified=true");

        // Get all the mQuestAchievementGroupList where contentId is null
        defaultMQuestAchievementGroupShouldNotBeFound("contentId.specified=false");
    }

    @Test
    @Transactional
    public void getAllMQuestAchievementGroupsByContentIdIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mQuestAchievementGroupRepository.saveAndFlush(mQuestAchievementGroup);

        // Get all the mQuestAchievementGroupList where contentId greater than or equals to DEFAULT_CONTENT_ID
        defaultMQuestAchievementGroupShouldBeFound("contentId.greaterOrEqualThan=" + DEFAULT_CONTENT_ID);

        // Get all the mQuestAchievementGroupList where contentId greater than or equals to UPDATED_CONTENT_ID
        defaultMQuestAchievementGroupShouldNotBeFound("contentId.greaterOrEqualThan=" + UPDATED_CONTENT_ID);
    }

    @Test
    @Transactional
    public void getAllMQuestAchievementGroupsByContentIdIsLessThanSomething() throws Exception {
        // Initialize the database
        mQuestAchievementGroupRepository.saveAndFlush(mQuestAchievementGroup);

        // Get all the mQuestAchievementGroupList where contentId less than or equals to DEFAULT_CONTENT_ID
        defaultMQuestAchievementGroupShouldNotBeFound("contentId.lessThan=" + DEFAULT_CONTENT_ID);

        // Get all the mQuestAchievementGroupList where contentId less than or equals to UPDATED_CONTENT_ID
        defaultMQuestAchievementGroupShouldBeFound("contentId.lessThan=" + UPDATED_CONTENT_ID);
    }


    @Test
    @Transactional
    public void getAllMQuestAchievementGroupsByContentAmountIsEqualToSomething() throws Exception {
        // Initialize the database
        mQuestAchievementGroupRepository.saveAndFlush(mQuestAchievementGroup);

        // Get all the mQuestAchievementGroupList where contentAmount equals to DEFAULT_CONTENT_AMOUNT
        defaultMQuestAchievementGroupShouldBeFound("contentAmount.equals=" + DEFAULT_CONTENT_AMOUNT);

        // Get all the mQuestAchievementGroupList where contentAmount equals to UPDATED_CONTENT_AMOUNT
        defaultMQuestAchievementGroupShouldNotBeFound("contentAmount.equals=" + UPDATED_CONTENT_AMOUNT);
    }

    @Test
    @Transactional
    public void getAllMQuestAchievementGroupsByContentAmountIsInShouldWork() throws Exception {
        // Initialize the database
        mQuestAchievementGroupRepository.saveAndFlush(mQuestAchievementGroup);

        // Get all the mQuestAchievementGroupList where contentAmount in DEFAULT_CONTENT_AMOUNT or UPDATED_CONTENT_AMOUNT
        defaultMQuestAchievementGroupShouldBeFound("contentAmount.in=" + DEFAULT_CONTENT_AMOUNT + "," + UPDATED_CONTENT_AMOUNT);

        // Get all the mQuestAchievementGroupList where contentAmount equals to UPDATED_CONTENT_AMOUNT
        defaultMQuestAchievementGroupShouldNotBeFound("contentAmount.in=" + UPDATED_CONTENT_AMOUNT);
    }

    @Test
    @Transactional
    public void getAllMQuestAchievementGroupsByContentAmountIsNullOrNotNull() throws Exception {
        // Initialize the database
        mQuestAchievementGroupRepository.saveAndFlush(mQuestAchievementGroup);

        // Get all the mQuestAchievementGroupList where contentAmount is not null
        defaultMQuestAchievementGroupShouldBeFound("contentAmount.specified=true");

        // Get all the mQuestAchievementGroupList where contentAmount is null
        defaultMQuestAchievementGroupShouldNotBeFound("contentAmount.specified=false");
    }

    @Test
    @Transactional
    public void getAllMQuestAchievementGroupsByContentAmountIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mQuestAchievementGroupRepository.saveAndFlush(mQuestAchievementGroup);

        // Get all the mQuestAchievementGroupList where contentAmount greater than or equals to DEFAULT_CONTENT_AMOUNT
        defaultMQuestAchievementGroupShouldBeFound("contentAmount.greaterOrEqualThan=" + DEFAULT_CONTENT_AMOUNT);

        // Get all the mQuestAchievementGroupList where contentAmount greater than or equals to UPDATED_CONTENT_AMOUNT
        defaultMQuestAchievementGroupShouldNotBeFound("contentAmount.greaterOrEqualThan=" + UPDATED_CONTENT_AMOUNT);
    }

    @Test
    @Transactional
    public void getAllMQuestAchievementGroupsByContentAmountIsLessThanSomething() throws Exception {
        // Initialize the database
        mQuestAchievementGroupRepository.saveAndFlush(mQuestAchievementGroup);

        // Get all the mQuestAchievementGroupList where contentAmount less than or equals to DEFAULT_CONTENT_AMOUNT
        defaultMQuestAchievementGroupShouldNotBeFound("contentAmount.lessThan=" + DEFAULT_CONTENT_AMOUNT);

        // Get all the mQuestAchievementGroupList where contentAmount less than or equals to UPDATED_CONTENT_AMOUNT
        defaultMQuestAchievementGroupShouldBeFound("contentAmount.lessThan=" + UPDATED_CONTENT_AMOUNT);
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultMQuestAchievementGroupShouldBeFound(String filter) throws Exception {
        restMQuestAchievementGroupMockMvc.perform(get("/api/m-quest-achievement-groups?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(mQuestAchievementGroup.getId().intValue())))
            .andExpect(jsonPath("$.[*].groupId").value(hasItem(DEFAULT_GROUP_ID)))
            .andExpect(jsonPath("$.[*].achievementType").value(hasItem(DEFAULT_ACHIEVEMENT_TYPE)))
            .andExpect(jsonPath("$.[*].rank").value(hasItem(DEFAULT_RANK)))
            .andExpect(jsonPath("$.[*].weight").value(hasItem(DEFAULT_WEIGHT)))
            .andExpect(jsonPath("$.[*].contentType").value(hasItem(DEFAULT_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].contentId").value(hasItem(DEFAULT_CONTENT_ID)))
            .andExpect(jsonPath("$.[*].contentAmount").value(hasItem(DEFAULT_CONTENT_AMOUNT)));

        // Check, that the count call also returns 1
        restMQuestAchievementGroupMockMvc.perform(get("/api/m-quest-achievement-groups/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultMQuestAchievementGroupShouldNotBeFound(String filter) throws Exception {
        restMQuestAchievementGroupMockMvc.perform(get("/api/m-quest-achievement-groups?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restMQuestAchievementGroupMockMvc.perform(get("/api/m-quest-achievement-groups/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("0"));
    }


    @Test
    @Transactional
    public void getNonExistingMQuestAchievementGroup() throws Exception {
        // Get the mQuestAchievementGroup
        restMQuestAchievementGroupMockMvc.perform(get("/api/m-quest-achievement-groups/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateMQuestAchievementGroup() throws Exception {
        // Initialize the database
        mQuestAchievementGroupRepository.saveAndFlush(mQuestAchievementGroup);

        int databaseSizeBeforeUpdate = mQuestAchievementGroupRepository.findAll().size();

        // Update the mQuestAchievementGroup
        MQuestAchievementGroup updatedMQuestAchievementGroup = mQuestAchievementGroupRepository.findById(mQuestAchievementGroup.getId()).get();
        // Disconnect from session so that the updates on updatedMQuestAchievementGroup are not directly saved in db
        em.detach(updatedMQuestAchievementGroup);
        updatedMQuestAchievementGroup
            .groupId(UPDATED_GROUP_ID)
            .achievementType(UPDATED_ACHIEVEMENT_TYPE)
            .rank(UPDATED_RANK)
            .weight(UPDATED_WEIGHT)
            .contentType(UPDATED_CONTENT_TYPE)
            .contentId(UPDATED_CONTENT_ID)
            .contentAmount(UPDATED_CONTENT_AMOUNT);
        MQuestAchievementGroupDTO mQuestAchievementGroupDTO = mQuestAchievementGroupMapper.toDto(updatedMQuestAchievementGroup);

        restMQuestAchievementGroupMockMvc.perform(put("/api/m-quest-achievement-groups")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mQuestAchievementGroupDTO)))
            .andExpect(status().isOk());

        // Validate the MQuestAchievementGroup in the database
        List<MQuestAchievementGroup> mQuestAchievementGroupList = mQuestAchievementGroupRepository.findAll();
        assertThat(mQuestAchievementGroupList).hasSize(databaseSizeBeforeUpdate);
        MQuestAchievementGroup testMQuestAchievementGroup = mQuestAchievementGroupList.get(mQuestAchievementGroupList.size() - 1);
        assertThat(testMQuestAchievementGroup.getGroupId()).isEqualTo(UPDATED_GROUP_ID);
        assertThat(testMQuestAchievementGroup.getAchievementType()).isEqualTo(UPDATED_ACHIEVEMENT_TYPE);
        assertThat(testMQuestAchievementGroup.getRank()).isEqualTo(UPDATED_RANK);
        assertThat(testMQuestAchievementGroup.getWeight()).isEqualTo(UPDATED_WEIGHT);
        assertThat(testMQuestAchievementGroup.getContentType()).isEqualTo(UPDATED_CONTENT_TYPE);
        assertThat(testMQuestAchievementGroup.getContentId()).isEqualTo(UPDATED_CONTENT_ID);
        assertThat(testMQuestAchievementGroup.getContentAmount()).isEqualTo(UPDATED_CONTENT_AMOUNT);
    }

    @Test
    @Transactional
    public void updateNonExistingMQuestAchievementGroup() throws Exception {
        int databaseSizeBeforeUpdate = mQuestAchievementGroupRepository.findAll().size();

        // Create the MQuestAchievementGroup
        MQuestAchievementGroupDTO mQuestAchievementGroupDTO = mQuestAchievementGroupMapper.toDto(mQuestAchievementGroup);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restMQuestAchievementGroupMockMvc.perform(put("/api/m-quest-achievement-groups")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mQuestAchievementGroupDTO)))
            .andExpect(status().isBadRequest());

        // Validate the MQuestAchievementGroup in the database
        List<MQuestAchievementGroup> mQuestAchievementGroupList = mQuestAchievementGroupRepository.findAll();
        assertThat(mQuestAchievementGroupList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteMQuestAchievementGroup() throws Exception {
        // Initialize the database
        mQuestAchievementGroupRepository.saveAndFlush(mQuestAchievementGroup);

        int databaseSizeBeforeDelete = mQuestAchievementGroupRepository.findAll().size();

        // Delete the mQuestAchievementGroup
        restMQuestAchievementGroupMockMvc.perform(delete("/api/m-quest-achievement-groups/{id}", mQuestAchievementGroup.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database is empty
        List<MQuestAchievementGroup> mQuestAchievementGroupList = mQuestAchievementGroupRepository.findAll();
        assertThat(mQuestAchievementGroupList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(MQuestAchievementGroup.class);
        MQuestAchievementGroup mQuestAchievementGroup1 = new MQuestAchievementGroup();
        mQuestAchievementGroup1.setId(1L);
        MQuestAchievementGroup mQuestAchievementGroup2 = new MQuestAchievementGroup();
        mQuestAchievementGroup2.setId(mQuestAchievementGroup1.getId());
        assertThat(mQuestAchievementGroup1).isEqualTo(mQuestAchievementGroup2);
        mQuestAchievementGroup2.setId(2L);
        assertThat(mQuestAchievementGroup1).isNotEqualTo(mQuestAchievementGroup2);
        mQuestAchievementGroup1.setId(null);
        assertThat(mQuestAchievementGroup1).isNotEqualTo(mQuestAchievementGroup2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(MQuestAchievementGroupDTO.class);
        MQuestAchievementGroupDTO mQuestAchievementGroupDTO1 = new MQuestAchievementGroupDTO();
        mQuestAchievementGroupDTO1.setId(1L);
        MQuestAchievementGroupDTO mQuestAchievementGroupDTO2 = new MQuestAchievementGroupDTO();
        assertThat(mQuestAchievementGroupDTO1).isNotEqualTo(mQuestAchievementGroupDTO2);
        mQuestAchievementGroupDTO2.setId(mQuestAchievementGroupDTO1.getId());
        assertThat(mQuestAchievementGroupDTO1).isEqualTo(mQuestAchievementGroupDTO2);
        mQuestAchievementGroupDTO2.setId(2L);
        assertThat(mQuestAchievementGroupDTO1).isNotEqualTo(mQuestAchievementGroupDTO2);
        mQuestAchievementGroupDTO1.setId(null);
        assertThat(mQuestAchievementGroupDTO1).isNotEqualTo(mQuestAchievementGroupDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(mQuestAchievementGroupMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(mQuestAchievementGroupMapper.fromId(null)).isNull();
    }
}
