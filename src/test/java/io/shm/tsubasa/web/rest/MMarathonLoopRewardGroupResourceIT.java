package io.shm.tsubasa.web.rest;

import io.shm.tsubasa.TsubasaApp;
import io.shm.tsubasa.domain.MMarathonLoopRewardGroup;
import io.shm.tsubasa.repository.MMarathonLoopRewardGroupRepository;
import io.shm.tsubasa.service.MMarathonLoopRewardGroupService;
import io.shm.tsubasa.service.dto.MMarathonLoopRewardGroupDTO;
import io.shm.tsubasa.service.mapper.MMarathonLoopRewardGroupMapper;
import io.shm.tsubasa.web.rest.errors.ExceptionTranslator;
import io.shm.tsubasa.service.dto.MMarathonLoopRewardGroupCriteria;
import io.shm.tsubasa.service.MMarathonLoopRewardGroupQueryService;

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
 * Integration tests for the {@Link MMarathonLoopRewardGroupResource} REST controller.
 */
@SpringBootTest(classes = TsubasaApp.class)
public class MMarathonLoopRewardGroupResourceIT {

    private static final Integer DEFAULT_EVENT_ID = 1;
    private static final Integer UPDATED_EVENT_ID = 2;

    private static final Integer DEFAULT_CONTENT_TYPE = 1;
    private static final Integer UPDATED_CONTENT_TYPE = 2;

    private static final Integer DEFAULT_CONTENT_ID = 1;
    private static final Integer UPDATED_CONTENT_ID = 2;

    private static final Integer DEFAULT_CONTENT_AMOUNT = 1;
    private static final Integer UPDATED_CONTENT_AMOUNT = 2;

    private static final Integer DEFAULT_WEIGHT = 1;
    private static final Integer UPDATED_WEIGHT = 2;

    @Autowired
    private MMarathonLoopRewardGroupRepository mMarathonLoopRewardGroupRepository;

    @Autowired
    private MMarathonLoopRewardGroupMapper mMarathonLoopRewardGroupMapper;

    @Autowired
    private MMarathonLoopRewardGroupService mMarathonLoopRewardGroupService;

    @Autowired
    private MMarathonLoopRewardGroupQueryService mMarathonLoopRewardGroupQueryService;

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

    private MockMvc restMMarathonLoopRewardGroupMockMvc;

    private MMarathonLoopRewardGroup mMarathonLoopRewardGroup;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final MMarathonLoopRewardGroupResource mMarathonLoopRewardGroupResource = new MMarathonLoopRewardGroupResource(mMarathonLoopRewardGroupService, mMarathonLoopRewardGroupQueryService);
        this.restMMarathonLoopRewardGroupMockMvc = MockMvcBuilders.standaloneSetup(mMarathonLoopRewardGroupResource)
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
    public static MMarathonLoopRewardGroup createEntity(EntityManager em) {
        MMarathonLoopRewardGroup mMarathonLoopRewardGroup = new MMarathonLoopRewardGroup()
            .eventId(DEFAULT_EVENT_ID)
            .contentType(DEFAULT_CONTENT_TYPE)
            .contentId(DEFAULT_CONTENT_ID)
            .contentAmount(DEFAULT_CONTENT_AMOUNT)
            .weight(DEFAULT_WEIGHT);
        return mMarathonLoopRewardGroup;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static MMarathonLoopRewardGroup createUpdatedEntity(EntityManager em) {
        MMarathonLoopRewardGroup mMarathonLoopRewardGroup = new MMarathonLoopRewardGroup()
            .eventId(UPDATED_EVENT_ID)
            .contentType(UPDATED_CONTENT_TYPE)
            .contentId(UPDATED_CONTENT_ID)
            .contentAmount(UPDATED_CONTENT_AMOUNT)
            .weight(UPDATED_WEIGHT);
        return mMarathonLoopRewardGroup;
    }

    @BeforeEach
    public void initTest() {
        mMarathonLoopRewardGroup = createEntity(em);
    }

    @Test
    @Transactional
    public void createMMarathonLoopRewardGroup() throws Exception {
        int databaseSizeBeforeCreate = mMarathonLoopRewardGroupRepository.findAll().size();

        // Create the MMarathonLoopRewardGroup
        MMarathonLoopRewardGroupDTO mMarathonLoopRewardGroupDTO = mMarathonLoopRewardGroupMapper.toDto(mMarathonLoopRewardGroup);
        restMMarathonLoopRewardGroupMockMvc.perform(post("/api/m-marathon-loop-reward-groups")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mMarathonLoopRewardGroupDTO)))
            .andExpect(status().isCreated());

        // Validate the MMarathonLoopRewardGroup in the database
        List<MMarathonLoopRewardGroup> mMarathonLoopRewardGroupList = mMarathonLoopRewardGroupRepository.findAll();
        assertThat(mMarathonLoopRewardGroupList).hasSize(databaseSizeBeforeCreate + 1);
        MMarathonLoopRewardGroup testMMarathonLoopRewardGroup = mMarathonLoopRewardGroupList.get(mMarathonLoopRewardGroupList.size() - 1);
        assertThat(testMMarathonLoopRewardGroup.getEventId()).isEqualTo(DEFAULT_EVENT_ID);
        assertThat(testMMarathonLoopRewardGroup.getContentType()).isEqualTo(DEFAULT_CONTENT_TYPE);
        assertThat(testMMarathonLoopRewardGroup.getContentId()).isEqualTo(DEFAULT_CONTENT_ID);
        assertThat(testMMarathonLoopRewardGroup.getContentAmount()).isEqualTo(DEFAULT_CONTENT_AMOUNT);
        assertThat(testMMarathonLoopRewardGroup.getWeight()).isEqualTo(DEFAULT_WEIGHT);
    }

    @Test
    @Transactional
    public void createMMarathonLoopRewardGroupWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = mMarathonLoopRewardGroupRepository.findAll().size();

        // Create the MMarathonLoopRewardGroup with an existing ID
        mMarathonLoopRewardGroup.setId(1L);
        MMarathonLoopRewardGroupDTO mMarathonLoopRewardGroupDTO = mMarathonLoopRewardGroupMapper.toDto(mMarathonLoopRewardGroup);

        // An entity with an existing ID cannot be created, so this API call must fail
        restMMarathonLoopRewardGroupMockMvc.perform(post("/api/m-marathon-loop-reward-groups")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mMarathonLoopRewardGroupDTO)))
            .andExpect(status().isBadRequest());

        // Validate the MMarathonLoopRewardGroup in the database
        List<MMarathonLoopRewardGroup> mMarathonLoopRewardGroupList = mMarathonLoopRewardGroupRepository.findAll();
        assertThat(mMarathonLoopRewardGroupList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkEventIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = mMarathonLoopRewardGroupRepository.findAll().size();
        // set the field null
        mMarathonLoopRewardGroup.setEventId(null);

        // Create the MMarathonLoopRewardGroup, which fails.
        MMarathonLoopRewardGroupDTO mMarathonLoopRewardGroupDTO = mMarathonLoopRewardGroupMapper.toDto(mMarathonLoopRewardGroup);

        restMMarathonLoopRewardGroupMockMvc.perform(post("/api/m-marathon-loop-reward-groups")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mMarathonLoopRewardGroupDTO)))
            .andExpect(status().isBadRequest());

        List<MMarathonLoopRewardGroup> mMarathonLoopRewardGroupList = mMarathonLoopRewardGroupRepository.findAll();
        assertThat(mMarathonLoopRewardGroupList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkContentTypeIsRequired() throws Exception {
        int databaseSizeBeforeTest = mMarathonLoopRewardGroupRepository.findAll().size();
        // set the field null
        mMarathonLoopRewardGroup.setContentType(null);

        // Create the MMarathonLoopRewardGroup, which fails.
        MMarathonLoopRewardGroupDTO mMarathonLoopRewardGroupDTO = mMarathonLoopRewardGroupMapper.toDto(mMarathonLoopRewardGroup);

        restMMarathonLoopRewardGroupMockMvc.perform(post("/api/m-marathon-loop-reward-groups")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mMarathonLoopRewardGroupDTO)))
            .andExpect(status().isBadRequest());

        List<MMarathonLoopRewardGroup> mMarathonLoopRewardGroupList = mMarathonLoopRewardGroupRepository.findAll();
        assertThat(mMarathonLoopRewardGroupList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkContentAmountIsRequired() throws Exception {
        int databaseSizeBeforeTest = mMarathonLoopRewardGroupRepository.findAll().size();
        // set the field null
        mMarathonLoopRewardGroup.setContentAmount(null);

        // Create the MMarathonLoopRewardGroup, which fails.
        MMarathonLoopRewardGroupDTO mMarathonLoopRewardGroupDTO = mMarathonLoopRewardGroupMapper.toDto(mMarathonLoopRewardGroup);

        restMMarathonLoopRewardGroupMockMvc.perform(post("/api/m-marathon-loop-reward-groups")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mMarathonLoopRewardGroupDTO)))
            .andExpect(status().isBadRequest());

        List<MMarathonLoopRewardGroup> mMarathonLoopRewardGroupList = mMarathonLoopRewardGroupRepository.findAll();
        assertThat(mMarathonLoopRewardGroupList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkWeightIsRequired() throws Exception {
        int databaseSizeBeforeTest = mMarathonLoopRewardGroupRepository.findAll().size();
        // set the field null
        mMarathonLoopRewardGroup.setWeight(null);

        // Create the MMarathonLoopRewardGroup, which fails.
        MMarathonLoopRewardGroupDTO mMarathonLoopRewardGroupDTO = mMarathonLoopRewardGroupMapper.toDto(mMarathonLoopRewardGroup);

        restMMarathonLoopRewardGroupMockMvc.perform(post("/api/m-marathon-loop-reward-groups")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mMarathonLoopRewardGroupDTO)))
            .andExpect(status().isBadRequest());

        List<MMarathonLoopRewardGroup> mMarathonLoopRewardGroupList = mMarathonLoopRewardGroupRepository.findAll();
        assertThat(mMarathonLoopRewardGroupList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllMMarathonLoopRewardGroups() throws Exception {
        // Initialize the database
        mMarathonLoopRewardGroupRepository.saveAndFlush(mMarathonLoopRewardGroup);

        // Get all the mMarathonLoopRewardGroupList
        restMMarathonLoopRewardGroupMockMvc.perform(get("/api/m-marathon-loop-reward-groups?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(mMarathonLoopRewardGroup.getId().intValue())))
            .andExpect(jsonPath("$.[*].eventId").value(hasItem(DEFAULT_EVENT_ID)))
            .andExpect(jsonPath("$.[*].contentType").value(hasItem(DEFAULT_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].contentId").value(hasItem(DEFAULT_CONTENT_ID)))
            .andExpect(jsonPath("$.[*].contentAmount").value(hasItem(DEFAULT_CONTENT_AMOUNT)))
            .andExpect(jsonPath("$.[*].weight").value(hasItem(DEFAULT_WEIGHT)));
    }
    
    @Test
    @Transactional
    public void getMMarathonLoopRewardGroup() throws Exception {
        // Initialize the database
        mMarathonLoopRewardGroupRepository.saveAndFlush(mMarathonLoopRewardGroup);

        // Get the mMarathonLoopRewardGroup
        restMMarathonLoopRewardGroupMockMvc.perform(get("/api/m-marathon-loop-reward-groups/{id}", mMarathonLoopRewardGroup.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(mMarathonLoopRewardGroup.getId().intValue()))
            .andExpect(jsonPath("$.eventId").value(DEFAULT_EVENT_ID))
            .andExpect(jsonPath("$.contentType").value(DEFAULT_CONTENT_TYPE))
            .andExpect(jsonPath("$.contentId").value(DEFAULT_CONTENT_ID))
            .andExpect(jsonPath("$.contentAmount").value(DEFAULT_CONTENT_AMOUNT))
            .andExpect(jsonPath("$.weight").value(DEFAULT_WEIGHT));
    }

    @Test
    @Transactional
    public void getAllMMarathonLoopRewardGroupsByEventIdIsEqualToSomething() throws Exception {
        // Initialize the database
        mMarathonLoopRewardGroupRepository.saveAndFlush(mMarathonLoopRewardGroup);

        // Get all the mMarathonLoopRewardGroupList where eventId equals to DEFAULT_EVENT_ID
        defaultMMarathonLoopRewardGroupShouldBeFound("eventId.equals=" + DEFAULT_EVENT_ID);

        // Get all the mMarathonLoopRewardGroupList where eventId equals to UPDATED_EVENT_ID
        defaultMMarathonLoopRewardGroupShouldNotBeFound("eventId.equals=" + UPDATED_EVENT_ID);
    }

    @Test
    @Transactional
    public void getAllMMarathonLoopRewardGroupsByEventIdIsInShouldWork() throws Exception {
        // Initialize the database
        mMarathonLoopRewardGroupRepository.saveAndFlush(mMarathonLoopRewardGroup);

        // Get all the mMarathonLoopRewardGroupList where eventId in DEFAULT_EVENT_ID or UPDATED_EVENT_ID
        defaultMMarathonLoopRewardGroupShouldBeFound("eventId.in=" + DEFAULT_EVENT_ID + "," + UPDATED_EVENT_ID);

        // Get all the mMarathonLoopRewardGroupList where eventId equals to UPDATED_EVENT_ID
        defaultMMarathonLoopRewardGroupShouldNotBeFound("eventId.in=" + UPDATED_EVENT_ID);
    }

    @Test
    @Transactional
    public void getAllMMarathonLoopRewardGroupsByEventIdIsNullOrNotNull() throws Exception {
        // Initialize the database
        mMarathonLoopRewardGroupRepository.saveAndFlush(mMarathonLoopRewardGroup);

        // Get all the mMarathonLoopRewardGroupList where eventId is not null
        defaultMMarathonLoopRewardGroupShouldBeFound("eventId.specified=true");

        // Get all the mMarathonLoopRewardGroupList where eventId is null
        defaultMMarathonLoopRewardGroupShouldNotBeFound("eventId.specified=false");
    }

    @Test
    @Transactional
    public void getAllMMarathonLoopRewardGroupsByEventIdIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mMarathonLoopRewardGroupRepository.saveAndFlush(mMarathonLoopRewardGroup);

        // Get all the mMarathonLoopRewardGroupList where eventId greater than or equals to DEFAULT_EVENT_ID
        defaultMMarathonLoopRewardGroupShouldBeFound("eventId.greaterOrEqualThan=" + DEFAULT_EVENT_ID);

        // Get all the mMarathonLoopRewardGroupList where eventId greater than or equals to UPDATED_EVENT_ID
        defaultMMarathonLoopRewardGroupShouldNotBeFound("eventId.greaterOrEqualThan=" + UPDATED_EVENT_ID);
    }

    @Test
    @Transactional
    public void getAllMMarathonLoopRewardGroupsByEventIdIsLessThanSomething() throws Exception {
        // Initialize the database
        mMarathonLoopRewardGroupRepository.saveAndFlush(mMarathonLoopRewardGroup);

        // Get all the mMarathonLoopRewardGroupList where eventId less than or equals to DEFAULT_EVENT_ID
        defaultMMarathonLoopRewardGroupShouldNotBeFound("eventId.lessThan=" + DEFAULT_EVENT_ID);

        // Get all the mMarathonLoopRewardGroupList where eventId less than or equals to UPDATED_EVENT_ID
        defaultMMarathonLoopRewardGroupShouldBeFound("eventId.lessThan=" + UPDATED_EVENT_ID);
    }


    @Test
    @Transactional
    public void getAllMMarathonLoopRewardGroupsByContentTypeIsEqualToSomething() throws Exception {
        // Initialize the database
        mMarathonLoopRewardGroupRepository.saveAndFlush(mMarathonLoopRewardGroup);

        // Get all the mMarathonLoopRewardGroupList where contentType equals to DEFAULT_CONTENT_TYPE
        defaultMMarathonLoopRewardGroupShouldBeFound("contentType.equals=" + DEFAULT_CONTENT_TYPE);

        // Get all the mMarathonLoopRewardGroupList where contentType equals to UPDATED_CONTENT_TYPE
        defaultMMarathonLoopRewardGroupShouldNotBeFound("contentType.equals=" + UPDATED_CONTENT_TYPE);
    }

    @Test
    @Transactional
    public void getAllMMarathonLoopRewardGroupsByContentTypeIsInShouldWork() throws Exception {
        // Initialize the database
        mMarathonLoopRewardGroupRepository.saveAndFlush(mMarathonLoopRewardGroup);

        // Get all the mMarathonLoopRewardGroupList where contentType in DEFAULT_CONTENT_TYPE or UPDATED_CONTENT_TYPE
        defaultMMarathonLoopRewardGroupShouldBeFound("contentType.in=" + DEFAULT_CONTENT_TYPE + "," + UPDATED_CONTENT_TYPE);

        // Get all the mMarathonLoopRewardGroupList where contentType equals to UPDATED_CONTENT_TYPE
        defaultMMarathonLoopRewardGroupShouldNotBeFound("contentType.in=" + UPDATED_CONTENT_TYPE);
    }

    @Test
    @Transactional
    public void getAllMMarathonLoopRewardGroupsByContentTypeIsNullOrNotNull() throws Exception {
        // Initialize the database
        mMarathonLoopRewardGroupRepository.saveAndFlush(mMarathonLoopRewardGroup);

        // Get all the mMarathonLoopRewardGroupList where contentType is not null
        defaultMMarathonLoopRewardGroupShouldBeFound("contentType.specified=true");

        // Get all the mMarathonLoopRewardGroupList where contentType is null
        defaultMMarathonLoopRewardGroupShouldNotBeFound("contentType.specified=false");
    }

    @Test
    @Transactional
    public void getAllMMarathonLoopRewardGroupsByContentTypeIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mMarathonLoopRewardGroupRepository.saveAndFlush(mMarathonLoopRewardGroup);

        // Get all the mMarathonLoopRewardGroupList where contentType greater than or equals to DEFAULT_CONTENT_TYPE
        defaultMMarathonLoopRewardGroupShouldBeFound("contentType.greaterOrEqualThan=" + DEFAULT_CONTENT_TYPE);

        // Get all the mMarathonLoopRewardGroupList where contentType greater than or equals to UPDATED_CONTENT_TYPE
        defaultMMarathonLoopRewardGroupShouldNotBeFound("contentType.greaterOrEqualThan=" + UPDATED_CONTENT_TYPE);
    }

    @Test
    @Transactional
    public void getAllMMarathonLoopRewardGroupsByContentTypeIsLessThanSomething() throws Exception {
        // Initialize the database
        mMarathonLoopRewardGroupRepository.saveAndFlush(mMarathonLoopRewardGroup);

        // Get all the mMarathonLoopRewardGroupList where contentType less than or equals to DEFAULT_CONTENT_TYPE
        defaultMMarathonLoopRewardGroupShouldNotBeFound("contentType.lessThan=" + DEFAULT_CONTENT_TYPE);

        // Get all the mMarathonLoopRewardGroupList where contentType less than or equals to UPDATED_CONTENT_TYPE
        defaultMMarathonLoopRewardGroupShouldBeFound("contentType.lessThan=" + UPDATED_CONTENT_TYPE);
    }


    @Test
    @Transactional
    public void getAllMMarathonLoopRewardGroupsByContentIdIsEqualToSomething() throws Exception {
        // Initialize the database
        mMarathonLoopRewardGroupRepository.saveAndFlush(mMarathonLoopRewardGroup);

        // Get all the mMarathonLoopRewardGroupList where contentId equals to DEFAULT_CONTENT_ID
        defaultMMarathonLoopRewardGroupShouldBeFound("contentId.equals=" + DEFAULT_CONTENT_ID);

        // Get all the mMarathonLoopRewardGroupList where contentId equals to UPDATED_CONTENT_ID
        defaultMMarathonLoopRewardGroupShouldNotBeFound("contentId.equals=" + UPDATED_CONTENT_ID);
    }

    @Test
    @Transactional
    public void getAllMMarathonLoopRewardGroupsByContentIdIsInShouldWork() throws Exception {
        // Initialize the database
        mMarathonLoopRewardGroupRepository.saveAndFlush(mMarathonLoopRewardGroup);

        // Get all the mMarathonLoopRewardGroupList where contentId in DEFAULT_CONTENT_ID or UPDATED_CONTENT_ID
        defaultMMarathonLoopRewardGroupShouldBeFound("contentId.in=" + DEFAULT_CONTENT_ID + "," + UPDATED_CONTENT_ID);

        // Get all the mMarathonLoopRewardGroupList where contentId equals to UPDATED_CONTENT_ID
        defaultMMarathonLoopRewardGroupShouldNotBeFound("contentId.in=" + UPDATED_CONTENT_ID);
    }

    @Test
    @Transactional
    public void getAllMMarathonLoopRewardGroupsByContentIdIsNullOrNotNull() throws Exception {
        // Initialize the database
        mMarathonLoopRewardGroupRepository.saveAndFlush(mMarathonLoopRewardGroup);

        // Get all the mMarathonLoopRewardGroupList where contentId is not null
        defaultMMarathonLoopRewardGroupShouldBeFound("contentId.specified=true");

        // Get all the mMarathonLoopRewardGroupList where contentId is null
        defaultMMarathonLoopRewardGroupShouldNotBeFound("contentId.specified=false");
    }

    @Test
    @Transactional
    public void getAllMMarathonLoopRewardGroupsByContentIdIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mMarathonLoopRewardGroupRepository.saveAndFlush(mMarathonLoopRewardGroup);

        // Get all the mMarathonLoopRewardGroupList where contentId greater than or equals to DEFAULT_CONTENT_ID
        defaultMMarathonLoopRewardGroupShouldBeFound("contentId.greaterOrEqualThan=" + DEFAULT_CONTENT_ID);

        // Get all the mMarathonLoopRewardGroupList where contentId greater than or equals to UPDATED_CONTENT_ID
        defaultMMarathonLoopRewardGroupShouldNotBeFound("contentId.greaterOrEqualThan=" + UPDATED_CONTENT_ID);
    }

    @Test
    @Transactional
    public void getAllMMarathonLoopRewardGroupsByContentIdIsLessThanSomething() throws Exception {
        // Initialize the database
        mMarathonLoopRewardGroupRepository.saveAndFlush(mMarathonLoopRewardGroup);

        // Get all the mMarathonLoopRewardGroupList where contentId less than or equals to DEFAULT_CONTENT_ID
        defaultMMarathonLoopRewardGroupShouldNotBeFound("contentId.lessThan=" + DEFAULT_CONTENT_ID);

        // Get all the mMarathonLoopRewardGroupList where contentId less than or equals to UPDATED_CONTENT_ID
        defaultMMarathonLoopRewardGroupShouldBeFound("contentId.lessThan=" + UPDATED_CONTENT_ID);
    }


    @Test
    @Transactional
    public void getAllMMarathonLoopRewardGroupsByContentAmountIsEqualToSomething() throws Exception {
        // Initialize the database
        mMarathonLoopRewardGroupRepository.saveAndFlush(mMarathonLoopRewardGroup);

        // Get all the mMarathonLoopRewardGroupList where contentAmount equals to DEFAULT_CONTENT_AMOUNT
        defaultMMarathonLoopRewardGroupShouldBeFound("contentAmount.equals=" + DEFAULT_CONTENT_AMOUNT);

        // Get all the mMarathonLoopRewardGroupList where contentAmount equals to UPDATED_CONTENT_AMOUNT
        defaultMMarathonLoopRewardGroupShouldNotBeFound("contentAmount.equals=" + UPDATED_CONTENT_AMOUNT);
    }

    @Test
    @Transactional
    public void getAllMMarathonLoopRewardGroupsByContentAmountIsInShouldWork() throws Exception {
        // Initialize the database
        mMarathonLoopRewardGroupRepository.saveAndFlush(mMarathonLoopRewardGroup);

        // Get all the mMarathonLoopRewardGroupList where contentAmount in DEFAULT_CONTENT_AMOUNT or UPDATED_CONTENT_AMOUNT
        defaultMMarathonLoopRewardGroupShouldBeFound("contentAmount.in=" + DEFAULT_CONTENT_AMOUNT + "," + UPDATED_CONTENT_AMOUNT);

        // Get all the mMarathonLoopRewardGroupList where contentAmount equals to UPDATED_CONTENT_AMOUNT
        defaultMMarathonLoopRewardGroupShouldNotBeFound("contentAmount.in=" + UPDATED_CONTENT_AMOUNT);
    }

    @Test
    @Transactional
    public void getAllMMarathonLoopRewardGroupsByContentAmountIsNullOrNotNull() throws Exception {
        // Initialize the database
        mMarathonLoopRewardGroupRepository.saveAndFlush(mMarathonLoopRewardGroup);

        // Get all the mMarathonLoopRewardGroupList where contentAmount is not null
        defaultMMarathonLoopRewardGroupShouldBeFound("contentAmount.specified=true");

        // Get all the mMarathonLoopRewardGroupList where contentAmount is null
        defaultMMarathonLoopRewardGroupShouldNotBeFound("contentAmount.specified=false");
    }

    @Test
    @Transactional
    public void getAllMMarathonLoopRewardGroupsByContentAmountIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mMarathonLoopRewardGroupRepository.saveAndFlush(mMarathonLoopRewardGroup);

        // Get all the mMarathonLoopRewardGroupList where contentAmount greater than or equals to DEFAULT_CONTENT_AMOUNT
        defaultMMarathonLoopRewardGroupShouldBeFound("contentAmount.greaterOrEqualThan=" + DEFAULT_CONTENT_AMOUNT);

        // Get all the mMarathonLoopRewardGroupList where contentAmount greater than or equals to UPDATED_CONTENT_AMOUNT
        defaultMMarathonLoopRewardGroupShouldNotBeFound("contentAmount.greaterOrEqualThan=" + UPDATED_CONTENT_AMOUNT);
    }

    @Test
    @Transactional
    public void getAllMMarathonLoopRewardGroupsByContentAmountIsLessThanSomething() throws Exception {
        // Initialize the database
        mMarathonLoopRewardGroupRepository.saveAndFlush(mMarathonLoopRewardGroup);

        // Get all the mMarathonLoopRewardGroupList where contentAmount less than or equals to DEFAULT_CONTENT_AMOUNT
        defaultMMarathonLoopRewardGroupShouldNotBeFound("contentAmount.lessThan=" + DEFAULT_CONTENT_AMOUNT);

        // Get all the mMarathonLoopRewardGroupList where contentAmount less than or equals to UPDATED_CONTENT_AMOUNT
        defaultMMarathonLoopRewardGroupShouldBeFound("contentAmount.lessThan=" + UPDATED_CONTENT_AMOUNT);
    }


    @Test
    @Transactional
    public void getAllMMarathonLoopRewardGroupsByWeightIsEqualToSomething() throws Exception {
        // Initialize the database
        mMarathonLoopRewardGroupRepository.saveAndFlush(mMarathonLoopRewardGroup);

        // Get all the mMarathonLoopRewardGroupList where weight equals to DEFAULT_WEIGHT
        defaultMMarathonLoopRewardGroupShouldBeFound("weight.equals=" + DEFAULT_WEIGHT);

        // Get all the mMarathonLoopRewardGroupList where weight equals to UPDATED_WEIGHT
        defaultMMarathonLoopRewardGroupShouldNotBeFound("weight.equals=" + UPDATED_WEIGHT);
    }

    @Test
    @Transactional
    public void getAllMMarathonLoopRewardGroupsByWeightIsInShouldWork() throws Exception {
        // Initialize the database
        mMarathonLoopRewardGroupRepository.saveAndFlush(mMarathonLoopRewardGroup);

        // Get all the mMarathonLoopRewardGroupList where weight in DEFAULT_WEIGHT or UPDATED_WEIGHT
        defaultMMarathonLoopRewardGroupShouldBeFound("weight.in=" + DEFAULT_WEIGHT + "," + UPDATED_WEIGHT);

        // Get all the mMarathonLoopRewardGroupList where weight equals to UPDATED_WEIGHT
        defaultMMarathonLoopRewardGroupShouldNotBeFound("weight.in=" + UPDATED_WEIGHT);
    }

    @Test
    @Transactional
    public void getAllMMarathonLoopRewardGroupsByWeightIsNullOrNotNull() throws Exception {
        // Initialize the database
        mMarathonLoopRewardGroupRepository.saveAndFlush(mMarathonLoopRewardGroup);

        // Get all the mMarathonLoopRewardGroupList where weight is not null
        defaultMMarathonLoopRewardGroupShouldBeFound("weight.specified=true");

        // Get all the mMarathonLoopRewardGroupList where weight is null
        defaultMMarathonLoopRewardGroupShouldNotBeFound("weight.specified=false");
    }

    @Test
    @Transactional
    public void getAllMMarathonLoopRewardGroupsByWeightIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mMarathonLoopRewardGroupRepository.saveAndFlush(mMarathonLoopRewardGroup);

        // Get all the mMarathonLoopRewardGroupList where weight greater than or equals to DEFAULT_WEIGHT
        defaultMMarathonLoopRewardGroupShouldBeFound("weight.greaterOrEqualThan=" + DEFAULT_WEIGHT);

        // Get all the mMarathonLoopRewardGroupList where weight greater than or equals to UPDATED_WEIGHT
        defaultMMarathonLoopRewardGroupShouldNotBeFound("weight.greaterOrEqualThan=" + UPDATED_WEIGHT);
    }

    @Test
    @Transactional
    public void getAllMMarathonLoopRewardGroupsByWeightIsLessThanSomething() throws Exception {
        // Initialize the database
        mMarathonLoopRewardGroupRepository.saveAndFlush(mMarathonLoopRewardGroup);

        // Get all the mMarathonLoopRewardGroupList where weight less than or equals to DEFAULT_WEIGHT
        defaultMMarathonLoopRewardGroupShouldNotBeFound("weight.lessThan=" + DEFAULT_WEIGHT);

        // Get all the mMarathonLoopRewardGroupList where weight less than or equals to UPDATED_WEIGHT
        defaultMMarathonLoopRewardGroupShouldBeFound("weight.lessThan=" + UPDATED_WEIGHT);
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultMMarathonLoopRewardGroupShouldBeFound(String filter) throws Exception {
        restMMarathonLoopRewardGroupMockMvc.perform(get("/api/m-marathon-loop-reward-groups?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(mMarathonLoopRewardGroup.getId().intValue())))
            .andExpect(jsonPath("$.[*].eventId").value(hasItem(DEFAULT_EVENT_ID)))
            .andExpect(jsonPath("$.[*].contentType").value(hasItem(DEFAULT_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].contentId").value(hasItem(DEFAULT_CONTENT_ID)))
            .andExpect(jsonPath("$.[*].contentAmount").value(hasItem(DEFAULT_CONTENT_AMOUNT)))
            .andExpect(jsonPath("$.[*].weight").value(hasItem(DEFAULT_WEIGHT)));

        // Check, that the count call also returns 1
        restMMarathonLoopRewardGroupMockMvc.perform(get("/api/m-marathon-loop-reward-groups/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultMMarathonLoopRewardGroupShouldNotBeFound(String filter) throws Exception {
        restMMarathonLoopRewardGroupMockMvc.perform(get("/api/m-marathon-loop-reward-groups?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restMMarathonLoopRewardGroupMockMvc.perform(get("/api/m-marathon-loop-reward-groups/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("0"));
    }


    @Test
    @Transactional
    public void getNonExistingMMarathonLoopRewardGroup() throws Exception {
        // Get the mMarathonLoopRewardGroup
        restMMarathonLoopRewardGroupMockMvc.perform(get("/api/m-marathon-loop-reward-groups/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateMMarathonLoopRewardGroup() throws Exception {
        // Initialize the database
        mMarathonLoopRewardGroupRepository.saveAndFlush(mMarathonLoopRewardGroup);

        int databaseSizeBeforeUpdate = mMarathonLoopRewardGroupRepository.findAll().size();

        // Update the mMarathonLoopRewardGroup
        MMarathonLoopRewardGroup updatedMMarathonLoopRewardGroup = mMarathonLoopRewardGroupRepository.findById(mMarathonLoopRewardGroup.getId()).get();
        // Disconnect from session so that the updates on updatedMMarathonLoopRewardGroup are not directly saved in db
        em.detach(updatedMMarathonLoopRewardGroup);
        updatedMMarathonLoopRewardGroup
            .eventId(UPDATED_EVENT_ID)
            .contentType(UPDATED_CONTENT_TYPE)
            .contentId(UPDATED_CONTENT_ID)
            .contentAmount(UPDATED_CONTENT_AMOUNT)
            .weight(UPDATED_WEIGHT);
        MMarathonLoopRewardGroupDTO mMarathonLoopRewardGroupDTO = mMarathonLoopRewardGroupMapper.toDto(updatedMMarathonLoopRewardGroup);

        restMMarathonLoopRewardGroupMockMvc.perform(put("/api/m-marathon-loop-reward-groups")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mMarathonLoopRewardGroupDTO)))
            .andExpect(status().isOk());

        // Validate the MMarathonLoopRewardGroup in the database
        List<MMarathonLoopRewardGroup> mMarathonLoopRewardGroupList = mMarathonLoopRewardGroupRepository.findAll();
        assertThat(mMarathonLoopRewardGroupList).hasSize(databaseSizeBeforeUpdate);
        MMarathonLoopRewardGroup testMMarathonLoopRewardGroup = mMarathonLoopRewardGroupList.get(mMarathonLoopRewardGroupList.size() - 1);
        assertThat(testMMarathonLoopRewardGroup.getEventId()).isEqualTo(UPDATED_EVENT_ID);
        assertThat(testMMarathonLoopRewardGroup.getContentType()).isEqualTo(UPDATED_CONTENT_TYPE);
        assertThat(testMMarathonLoopRewardGroup.getContentId()).isEqualTo(UPDATED_CONTENT_ID);
        assertThat(testMMarathonLoopRewardGroup.getContentAmount()).isEqualTo(UPDATED_CONTENT_AMOUNT);
        assertThat(testMMarathonLoopRewardGroup.getWeight()).isEqualTo(UPDATED_WEIGHT);
    }

    @Test
    @Transactional
    public void updateNonExistingMMarathonLoopRewardGroup() throws Exception {
        int databaseSizeBeforeUpdate = mMarathonLoopRewardGroupRepository.findAll().size();

        // Create the MMarathonLoopRewardGroup
        MMarathonLoopRewardGroupDTO mMarathonLoopRewardGroupDTO = mMarathonLoopRewardGroupMapper.toDto(mMarathonLoopRewardGroup);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restMMarathonLoopRewardGroupMockMvc.perform(put("/api/m-marathon-loop-reward-groups")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mMarathonLoopRewardGroupDTO)))
            .andExpect(status().isBadRequest());

        // Validate the MMarathonLoopRewardGroup in the database
        List<MMarathonLoopRewardGroup> mMarathonLoopRewardGroupList = mMarathonLoopRewardGroupRepository.findAll();
        assertThat(mMarathonLoopRewardGroupList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteMMarathonLoopRewardGroup() throws Exception {
        // Initialize the database
        mMarathonLoopRewardGroupRepository.saveAndFlush(mMarathonLoopRewardGroup);

        int databaseSizeBeforeDelete = mMarathonLoopRewardGroupRepository.findAll().size();

        // Delete the mMarathonLoopRewardGroup
        restMMarathonLoopRewardGroupMockMvc.perform(delete("/api/m-marathon-loop-reward-groups/{id}", mMarathonLoopRewardGroup.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database is empty
        List<MMarathonLoopRewardGroup> mMarathonLoopRewardGroupList = mMarathonLoopRewardGroupRepository.findAll();
        assertThat(mMarathonLoopRewardGroupList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(MMarathonLoopRewardGroup.class);
        MMarathonLoopRewardGroup mMarathonLoopRewardGroup1 = new MMarathonLoopRewardGroup();
        mMarathonLoopRewardGroup1.setId(1L);
        MMarathonLoopRewardGroup mMarathonLoopRewardGroup2 = new MMarathonLoopRewardGroup();
        mMarathonLoopRewardGroup2.setId(mMarathonLoopRewardGroup1.getId());
        assertThat(mMarathonLoopRewardGroup1).isEqualTo(mMarathonLoopRewardGroup2);
        mMarathonLoopRewardGroup2.setId(2L);
        assertThat(mMarathonLoopRewardGroup1).isNotEqualTo(mMarathonLoopRewardGroup2);
        mMarathonLoopRewardGroup1.setId(null);
        assertThat(mMarathonLoopRewardGroup1).isNotEqualTo(mMarathonLoopRewardGroup2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(MMarathonLoopRewardGroupDTO.class);
        MMarathonLoopRewardGroupDTO mMarathonLoopRewardGroupDTO1 = new MMarathonLoopRewardGroupDTO();
        mMarathonLoopRewardGroupDTO1.setId(1L);
        MMarathonLoopRewardGroupDTO mMarathonLoopRewardGroupDTO2 = new MMarathonLoopRewardGroupDTO();
        assertThat(mMarathonLoopRewardGroupDTO1).isNotEqualTo(mMarathonLoopRewardGroupDTO2);
        mMarathonLoopRewardGroupDTO2.setId(mMarathonLoopRewardGroupDTO1.getId());
        assertThat(mMarathonLoopRewardGroupDTO1).isEqualTo(mMarathonLoopRewardGroupDTO2);
        mMarathonLoopRewardGroupDTO2.setId(2L);
        assertThat(mMarathonLoopRewardGroupDTO1).isNotEqualTo(mMarathonLoopRewardGroupDTO2);
        mMarathonLoopRewardGroupDTO1.setId(null);
        assertThat(mMarathonLoopRewardGroupDTO1).isNotEqualTo(mMarathonLoopRewardGroupDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(mMarathonLoopRewardGroupMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(mMarathonLoopRewardGroupMapper.fromId(null)).isNull();
    }
}
