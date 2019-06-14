package io.shm.tsubasa.web.rest;

import io.shm.tsubasa.TsubasaApp;
import io.shm.tsubasa.domain.MGuildMission;
import io.shm.tsubasa.domain.MMissionReward;
import io.shm.tsubasa.repository.MGuildMissionRepository;
import io.shm.tsubasa.service.MGuildMissionService;
import io.shm.tsubasa.service.dto.MGuildMissionDTO;
import io.shm.tsubasa.service.mapper.MGuildMissionMapper;
import io.shm.tsubasa.web.rest.errors.ExceptionTranslator;
import io.shm.tsubasa.service.dto.MGuildMissionCriteria;
import io.shm.tsubasa.service.MGuildMissionQueryService;

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
import org.springframework.util.Base64Utils;
import org.springframework.validation.Validator;

import javax.persistence.EntityManager;
import java.util.List;

import static io.shm.tsubasa.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the {@Link MGuildMissionResource} REST controller.
 */
@SpringBootTest(classes = TsubasaApp.class)
public class MGuildMissionResourceIT {

    private static final Integer DEFAULT_TERM = 1;
    private static final Integer UPDATED_TERM = 2;

    private static final String DEFAULT_TITLE = "AAAAAAAAAA";
    private static final String UPDATED_TITLE = "BBBBBBBBBB";

    private static final String DEFAULT_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPTION = "BBBBBBBBBB";

    private static final Integer DEFAULT_MISSION_TYPE = 1;
    private static final Integer UPDATED_MISSION_TYPE = 2;

    private static final Integer DEFAULT_PARAM_1 = 1;
    private static final Integer UPDATED_PARAM_1 = 2;

    private static final Integer DEFAULT_REWARD_ID = 1;
    private static final Integer UPDATED_REWARD_ID = 2;

    private static final Integer DEFAULT_LINK = 1;
    private static final Integer UPDATED_LINK = 2;

    private static final String DEFAULT_SCENE_TRANSITION_PARAM = "AAAAAAAAAA";
    private static final String UPDATED_SCENE_TRANSITION_PARAM = "BBBBBBBBBB";

    private static final Integer DEFAULT_PICKUP = 1;
    private static final Integer UPDATED_PICKUP = 2;

    private static final Integer DEFAULT_TRIGGER_MISSION_ID = 1;
    private static final Integer UPDATED_TRIGGER_MISSION_ID = 2;

    private static final Integer DEFAULT_ORDER_NUM = 1;
    private static final Integer UPDATED_ORDER_NUM = 2;

    @Autowired
    private MGuildMissionRepository mGuildMissionRepository;

    @Autowired
    private MGuildMissionMapper mGuildMissionMapper;

    @Autowired
    private MGuildMissionService mGuildMissionService;

    @Autowired
    private MGuildMissionQueryService mGuildMissionQueryService;

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

    private MockMvc restMGuildMissionMockMvc;

    private MGuildMission mGuildMission;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final MGuildMissionResource mGuildMissionResource = new MGuildMissionResource(mGuildMissionService, mGuildMissionQueryService);
        this.restMGuildMissionMockMvc = MockMvcBuilders.standaloneSetup(mGuildMissionResource)
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
    public static MGuildMission createEntity(EntityManager em) {
        MGuildMission mGuildMission = new MGuildMission()
            .term(DEFAULT_TERM)
            .title(DEFAULT_TITLE)
            .description(DEFAULT_DESCRIPTION)
            .missionType(DEFAULT_MISSION_TYPE)
            .param1(DEFAULT_PARAM_1)
            .rewardId(DEFAULT_REWARD_ID)
            .link(DEFAULT_LINK)
            .sceneTransitionParam(DEFAULT_SCENE_TRANSITION_PARAM)
            .pickup(DEFAULT_PICKUP)
            .triggerMissionId(DEFAULT_TRIGGER_MISSION_ID)
            .orderNum(DEFAULT_ORDER_NUM);
        // Add required entity
        MMissionReward mMissionReward;
        if (TestUtil.findAll(em, MMissionReward.class).isEmpty()) {
            mMissionReward = MMissionRewardResourceIT.createEntity(em);
            em.persist(mMissionReward);
            em.flush();
        } else {
            mMissionReward = TestUtil.findAll(em, MMissionReward.class).get(0);
        }
        mGuildMission.setId(mMissionReward);
        return mGuildMission;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static MGuildMission createUpdatedEntity(EntityManager em) {
        MGuildMission mGuildMission = new MGuildMission()
            .term(UPDATED_TERM)
            .title(UPDATED_TITLE)
            .description(UPDATED_DESCRIPTION)
            .missionType(UPDATED_MISSION_TYPE)
            .param1(UPDATED_PARAM_1)
            .rewardId(UPDATED_REWARD_ID)
            .link(UPDATED_LINK)
            .sceneTransitionParam(UPDATED_SCENE_TRANSITION_PARAM)
            .pickup(UPDATED_PICKUP)
            .triggerMissionId(UPDATED_TRIGGER_MISSION_ID)
            .orderNum(UPDATED_ORDER_NUM);
        // Add required entity
        MMissionReward mMissionReward;
        if (TestUtil.findAll(em, MMissionReward.class).isEmpty()) {
            mMissionReward = MMissionRewardResourceIT.createUpdatedEntity(em);
            em.persist(mMissionReward);
            em.flush();
        } else {
            mMissionReward = TestUtil.findAll(em, MMissionReward.class).get(0);
        }
        mGuildMission.setId(mMissionReward);
        return mGuildMission;
    }

    @BeforeEach
    public void initTest() {
        mGuildMission = createEntity(em);
    }

    @Test
    @Transactional
    public void createMGuildMission() throws Exception {
        int databaseSizeBeforeCreate = mGuildMissionRepository.findAll().size();

        // Create the MGuildMission
        MGuildMissionDTO mGuildMissionDTO = mGuildMissionMapper.toDto(mGuildMission);
        restMGuildMissionMockMvc.perform(post("/api/m-guild-missions")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mGuildMissionDTO)))
            .andExpect(status().isCreated());

        // Validate the MGuildMission in the database
        List<MGuildMission> mGuildMissionList = mGuildMissionRepository.findAll();
        assertThat(mGuildMissionList).hasSize(databaseSizeBeforeCreate + 1);
        MGuildMission testMGuildMission = mGuildMissionList.get(mGuildMissionList.size() - 1);
        assertThat(testMGuildMission.getTerm()).isEqualTo(DEFAULT_TERM);
        assertThat(testMGuildMission.getTitle()).isEqualTo(DEFAULT_TITLE);
        assertThat(testMGuildMission.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
        assertThat(testMGuildMission.getMissionType()).isEqualTo(DEFAULT_MISSION_TYPE);
        assertThat(testMGuildMission.getParam1()).isEqualTo(DEFAULT_PARAM_1);
        assertThat(testMGuildMission.getRewardId()).isEqualTo(DEFAULT_REWARD_ID);
        assertThat(testMGuildMission.getLink()).isEqualTo(DEFAULT_LINK);
        assertThat(testMGuildMission.getSceneTransitionParam()).isEqualTo(DEFAULT_SCENE_TRANSITION_PARAM);
        assertThat(testMGuildMission.getPickup()).isEqualTo(DEFAULT_PICKUP);
        assertThat(testMGuildMission.getTriggerMissionId()).isEqualTo(DEFAULT_TRIGGER_MISSION_ID);
        assertThat(testMGuildMission.getOrderNum()).isEqualTo(DEFAULT_ORDER_NUM);
    }

    @Test
    @Transactional
    public void createMGuildMissionWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = mGuildMissionRepository.findAll().size();

        // Create the MGuildMission with an existing ID
        mGuildMission.setId(1L);
        MGuildMissionDTO mGuildMissionDTO = mGuildMissionMapper.toDto(mGuildMission);

        // An entity with an existing ID cannot be created, so this API call must fail
        restMGuildMissionMockMvc.perform(post("/api/m-guild-missions")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mGuildMissionDTO)))
            .andExpect(status().isBadRequest());

        // Validate the MGuildMission in the database
        List<MGuildMission> mGuildMissionList = mGuildMissionRepository.findAll();
        assertThat(mGuildMissionList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkTermIsRequired() throws Exception {
        int databaseSizeBeforeTest = mGuildMissionRepository.findAll().size();
        // set the field null
        mGuildMission.setTerm(null);

        // Create the MGuildMission, which fails.
        MGuildMissionDTO mGuildMissionDTO = mGuildMissionMapper.toDto(mGuildMission);

        restMGuildMissionMockMvc.perform(post("/api/m-guild-missions")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mGuildMissionDTO)))
            .andExpect(status().isBadRequest());

        List<MGuildMission> mGuildMissionList = mGuildMissionRepository.findAll();
        assertThat(mGuildMissionList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkMissionTypeIsRequired() throws Exception {
        int databaseSizeBeforeTest = mGuildMissionRepository.findAll().size();
        // set the field null
        mGuildMission.setMissionType(null);

        // Create the MGuildMission, which fails.
        MGuildMissionDTO mGuildMissionDTO = mGuildMissionMapper.toDto(mGuildMission);

        restMGuildMissionMockMvc.perform(post("/api/m-guild-missions")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mGuildMissionDTO)))
            .andExpect(status().isBadRequest());

        List<MGuildMission> mGuildMissionList = mGuildMissionRepository.findAll();
        assertThat(mGuildMissionList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkRewardIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = mGuildMissionRepository.findAll().size();
        // set the field null
        mGuildMission.setRewardId(null);

        // Create the MGuildMission, which fails.
        MGuildMissionDTO mGuildMissionDTO = mGuildMissionMapper.toDto(mGuildMission);

        restMGuildMissionMockMvc.perform(post("/api/m-guild-missions")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mGuildMissionDTO)))
            .andExpect(status().isBadRequest());

        List<MGuildMission> mGuildMissionList = mGuildMissionRepository.findAll();
        assertThat(mGuildMissionList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkPickupIsRequired() throws Exception {
        int databaseSizeBeforeTest = mGuildMissionRepository.findAll().size();
        // set the field null
        mGuildMission.setPickup(null);

        // Create the MGuildMission, which fails.
        MGuildMissionDTO mGuildMissionDTO = mGuildMissionMapper.toDto(mGuildMission);

        restMGuildMissionMockMvc.perform(post("/api/m-guild-missions")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mGuildMissionDTO)))
            .andExpect(status().isBadRequest());

        List<MGuildMission> mGuildMissionList = mGuildMissionRepository.findAll();
        assertThat(mGuildMissionList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkOrderNumIsRequired() throws Exception {
        int databaseSizeBeforeTest = mGuildMissionRepository.findAll().size();
        // set the field null
        mGuildMission.setOrderNum(null);

        // Create the MGuildMission, which fails.
        MGuildMissionDTO mGuildMissionDTO = mGuildMissionMapper.toDto(mGuildMission);

        restMGuildMissionMockMvc.perform(post("/api/m-guild-missions")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mGuildMissionDTO)))
            .andExpect(status().isBadRequest());

        List<MGuildMission> mGuildMissionList = mGuildMissionRepository.findAll();
        assertThat(mGuildMissionList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllMGuildMissions() throws Exception {
        // Initialize the database
        mGuildMissionRepository.saveAndFlush(mGuildMission);

        // Get all the mGuildMissionList
        restMGuildMissionMockMvc.perform(get("/api/m-guild-missions?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(mGuildMission.getId().intValue())))
            .andExpect(jsonPath("$.[*].term").value(hasItem(DEFAULT_TERM)))
            .andExpect(jsonPath("$.[*].title").value(hasItem(DEFAULT_TITLE.toString())))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION.toString())))
            .andExpect(jsonPath("$.[*].missionType").value(hasItem(DEFAULT_MISSION_TYPE)))
            .andExpect(jsonPath("$.[*].param1").value(hasItem(DEFAULT_PARAM_1)))
            .andExpect(jsonPath("$.[*].rewardId").value(hasItem(DEFAULT_REWARD_ID)))
            .andExpect(jsonPath("$.[*].link").value(hasItem(DEFAULT_LINK)))
            .andExpect(jsonPath("$.[*].sceneTransitionParam").value(hasItem(DEFAULT_SCENE_TRANSITION_PARAM.toString())))
            .andExpect(jsonPath("$.[*].pickup").value(hasItem(DEFAULT_PICKUP)))
            .andExpect(jsonPath("$.[*].triggerMissionId").value(hasItem(DEFAULT_TRIGGER_MISSION_ID)))
            .andExpect(jsonPath("$.[*].orderNum").value(hasItem(DEFAULT_ORDER_NUM)));
    }
    
    @Test
    @Transactional
    public void getMGuildMission() throws Exception {
        // Initialize the database
        mGuildMissionRepository.saveAndFlush(mGuildMission);

        // Get the mGuildMission
        restMGuildMissionMockMvc.perform(get("/api/m-guild-missions/{id}", mGuildMission.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(mGuildMission.getId().intValue()))
            .andExpect(jsonPath("$.term").value(DEFAULT_TERM))
            .andExpect(jsonPath("$.title").value(DEFAULT_TITLE.toString()))
            .andExpect(jsonPath("$.description").value(DEFAULT_DESCRIPTION.toString()))
            .andExpect(jsonPath("$.missionType").value(DEFAULT_MISSION_TYPE))
            .andExpect(jsonPath("$.param1").value(DEFAULT_PARAM_1))
            .andExpect(jsonPath("$.rewardId").value(DEFAULT_REWARD_ID))
            .andExpect(jsonPath("$.link").value(DEFAULT_LINK))
            .andExpect(jsonPath("$.sceneTransitionParam").value(DEFAULT_SCENE_TRANSITION_PARAM.toString()))
            .andExpect(jsonPath("$.pickup").value(DEFAULT_PICKUP))
            .andExpect(jsonPath("$.triggerMissionId").value(DEFAULT_TRIGGER_MISSION_ID))
            .andExpect(jsonPath("$.orderNum").value(DEFAULT_ORDER_NUM));
    }

    @Test
    @Transactional
    public void getAllMGuildMissionsByTermIsEqualToSomething() throws Exception {
        // Initialize the database
        mGuildMissionRepository.saveAndFlush(mGuildMission);

        // Get all the mGuildMissionList where term equals to DEFAULT_TERM
        defaultMGuildMissionShouldBeFound("term.equals=" + DEFAULT_TERM);

        // Get all the mGuildMissionList where term equals to UPDATED_TERM
        defaultMGuildMissionShouldNotBeFound("term.equals=" + UPDATED_TERM);
    }

    @Test
    @Transactional
    public void getAllMGuildMissionsByTermIsInShouldWork() throws Exception {
        // Initialize the database
        mGuildMissionRepository.saveAndFlush(mGuildMission);

        // Get all the mGuildMissionList where term in DEFAULT_TERM or UPDATED_TERM
        defaultMGuildMissionShouldBeFound("term.in=" + DEFAULT_TERM + "," + UPDATED_TERM);

        // Get all the mGuildMissionList where term equals to UPDATED_TERM
        defaultMGuildMissionShouldNotBeFound("term.in=" + UPDATED_TERM);
    }

    @Test
    @Transactional
    public void getAllMGuildMissionsByTermIsNullOrNotNull() throws Exception {
        // Initialize the database
        mGuildMissionRepository.saveAndFlush(mGuildMission);

        // Get all the mGuildMissionList where term is not null
        defaultMGuildMissionShouldBeFound("term.specified=true");

        // Get all the mGuildMissionList where term is null
        defaultMGuildMissionShouldNotBeFound("term.specified=false");
    }

    @Test
    @Transactional
    public void getAllMGuildMissionsByTermIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mGuildMissionRepository.saveAndFlush(mGuildMission);

        // Get all the mGuildMissionList where term greater than or equals to DEFAULT_TERM
        defaultMGuildMissionShouldBeFound("term.greaterOrEqualThan=" + DEFAULT_TERM);

        // Get all the mGuildMissionList where term greater than or equals to UPDATED_TERM
        defaultMGuildMissionShouldNotBeFound("term.greaterOrEqualThan=" + UPDATED_TERM);
    }

    @Test
    @Transactional
    public void getAllMGuildMissionsByTermIsLessThanSomething() throws Exception {
        // Initialize the database
        mGuildMissionRepository.saveAndFlush(mGuildMission);

        // Get all the mGuildMissionList where term less than or equals to DEFAULT_TERM
        defaultMGuildMissionShouldNotBeFound("term.lessThan=" + DEFAULT_TERM);

        // Get all the mGuildMissionList where term less than or equals to UPDATED_TERM
        defaultMGuildMissionShouldBeFound("term.lessThan=" + UPDATED_TERM);
    }


    @Test
    @Transactional
    public void getAllMGuildMissionsByMissionTypeIsEqualToSomething() throws Exception {
        // Initialize the database
        mGuildMissionRepository.saveAndFlush(mGuildMission);

        // Get all the mGuildMissionList where missionType equals to DEFAULT_MISSION_TYPE
        defaultMGuildMissionShouldBeFound("missionType.equals=" + DEFAULT_MISSION_TYPE);

        // Get all the mGuildMissionList where missionType equals to UPDATED_MISSION_TYPE
        defaultMGuildMissionShouldNotBeFound("missionType.equals=" + UPDATED_MISSION_TYPE);
    }

    @Test
    @Transactional
    public void getAllMGuildMissionsByMissionTypeIsInShouldWork() throws Exception {
        // Initialize the database
        mGuildMissionRepository.saveAndFlush(mGuildMission);

        // Get all the mGuildMissionList where missionType in DEFAULT_MISSION_TYPE or UPDATED_MISSION_TYPE
        defaultMGuildMissionShouldBeFound("missionType.in=" + DEFAULT_MISSION_TYPE + "," + UPDATED_MISSION_TYPE);

        // Get all the mGuildMissionList where missionType equals to UPDATED_MISSION_TYPE
        defaultMGuildMissionShouldNotBeFound("missionType.in=" + UPDATED_MISSION_TYPE);
    }

    @Test
    @Transactional
    public void getAllMGuildMissionsByMissionTypeIsNullOrNotNull() throws Exception {
        // Initialize the database
        mGuildMissionRepository.saveAndFlush(mGuildMission);

        // Get all the mGuildMissionList where missionType is not null
        defaultMGuildMissionShouldBeFound("missionType.specified=true");

        // Get all the mGuildMissionList where missionType is null
        defaultMGuildMissionShouldNotBeFound("missionType.specified=false");
    }

    @Test
    @Transactional
    public void getAllMGuildMissionsByMissionTypeIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mGuildMissionRepository.saveAndFlush(mGuildMission);

        // Get all the mGuildMissionList where missionType greater than or equals to DEFAULT_MISSION_TYPE
        defaultMGuildMissionShouldBeFound("missionType.greaterOrEqualThan=" + DEFAULT_MISSION_TYPE);

        // Get all the mGuildMissionList where missionType greater than or equals to UPDATED_MISSION_TYPE
        defaultMGuildMissionShouldNotBeFound("missionType.greaterOrEqualThan=" + UPDATED_MISSION_TYPE);
    }

    @Test
    @Transactional
    public void getAllMGuildMissionsByMissionTypeIsLessThanSomething() throws Exception {
        // Initialize the database
        mGuildMissionRepository.saveAndFlush(mGuildMission);

        // Get all the mGuildMissionList where missionType less than or equals to DEFAULT_MISSION_TYPE
        defaultMGuildMissionShouldNotBeFound("missionType.lessThan=" + DEFAULT_MISSION_TYPE);

        // Get all the mGuildMissionList where missionType less than or equals to UPDATED_MISSION_TYPE
        defaultMGuildMissionShouldBeFound("missionType.lessThan=" + UPDATED_MISSION_TYPE);
    }


    @Test
    @Transactional
    public void getAllMGuildMissionsByParam1IsEqualToSomething() throws Exception {
        // Initialize the database
        mGuildMissionRepository.saveAndFlush(mGuildMission);

        // Get all the mGuildMissionList where param1 equals to DEFAULT_PARAM_1
        defaultMGuildMissionShouldBeFound("param1.equals=" + DEFAULT_PARAM_1);

        // Get all the mGuildMissionList where param1 equals to UPDATED_PARAM_1
        defaultMGuildMissionShouldNotBeFound("param1.equals=" + UPDATED_PARAM_1);
    }

    @Test
    @Transactional
    public void getAllMGuildMissionsByParam1IsInShouldWork() throws Exception {
        // Initialize the database
        mGuildMissionRepository.saveAndFlush(mGuildMission);

        // Get all the mGuildMissionList where param1 in DEFAULT_PARAM_1 or UPDATED_PARAM_1
        defaultMGuildMissionShouldBeFound("param1.in=" + DEFAULT_PARAM_1 + "," + UPDATED_PARAM_1);

        // Get all the mGuildMissionList where param1 equals to UPDATED_PARAM_1
        defaultMGuildMissionShouldNotBeFound("param1.in=" + UPDATED_PARAM_1);
    }

    @Test
    @Transactional
    public void getAllMGuildMissionsByParam1IsNullOrNotNull() throws Exception {
        // Initialize the database
        mGuildMissionRepository.saveAndFlush(mGuildMission);

        // Get all the mGuildMissionList where param1 is not null
        defaultMGuildMissionShouldBeFound("param1.specified=true");

        // Get all the mGuildMissionList where param1 is null
        defaultMGuildMissionShouldNotBeFound("param1.specified=false");
    }

    @Test
    @Transactional
    public void getAllMGuildMissionsByParam1IsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mGuildMissionRepository.saveAndFlush(mGuildMission);

        // Get all the mGuildMissionList where param1 greater than or equals to DEFAULT_PARAM_1
        defaultMGuildMissionShouldBeFound("param1.greaterOrEqualThan=" + DEFAULT_PARAM_1);

        // Get all the mGuildMissionList where param1 greater than or equals to UPDATED_PARAM_1
        defaultMGuildMissionShouldNotBeFound("param1.greaterOrEqualThan=" + UPDATED_PARAM_1);
    }

    @Test
    @Transactional
    public void getAllMGuildMissionsByParam1IsLessThanSomething() throws Exception {
        // Initialize the database
        mGuildMissionRepository.saveAndFlush(mGuildMission);

        // Get all the mGuildMissionList where param1 less than or equals to DEFAULT_PARAM_1
        defaultMGuildMissionShouldNotBeFound("param1.lessThan=" + DEFAULT_PARAM_1);

        // Get all the mGuildMissionList where param1 less than or equals to UPDATED_PARAM_1
        defaultMGuildMissionShouldBeFound("param1.lessThan=" + UPDATED_PARAM_1);
    }


    @Test
    @Transactional
    public void getAllMGuildMissionsByRewardIdIsEqualToSomething() throws Exception {
        // Initialize the database
        mGuildMissionRepository.saveAndFlush(mGuildMission);

        // Get all the mGuildMissionList where rewardId equals to DEFAULT_REWARD_ID
        defaultMGuildMissionShouldBeFound("rewardId.equals=" + DEFAULT_REWARD_ID);

        // Get all the mGuildMissionList where rewardId equals to UPDATED_REWARD_ID
        defaultMGuildMissionShouldNotBeFound("rewardId.equals=" + UPDATED_REWARD_ID);
    }

    @Test
    @Transactional
    public void getAllMGuildMissionsByRewardIdIsInShouldWork() throws Exception {
        // Initialize the database
        mGuildMissionRepository.saveAndFlush(mGuildMission);

        // Get all the mGuildMissionList where rewardId in DEFAULT_REWARD_ID or UPDATED_REWARD_ID
        defaultMGuildMissionShouldBeFound("rewardId.in=" + DEFAULT_REWARD_ID + "," + UPDATED_REWARD_ID);

        // Get all the mGuildMissionList where rewardId equals to UPDATED_REWARD_ID
        defaultMGuildMissionShouldNotBeFound("rewardId.in=" + UPDATED_REWARD_ID);
    }

    @Test
    @Transactional
    public void getAllMGuildMissionsByRewardIdIsNullOrNotNull() throws Exception {
        // Initialize the database
        mGuildMissionRepository.saveAndFlush(mGuildMission);

        // Get all the mGuildMissionList where rewardId is not null
        defaultMGuildMissionShouldBeFound("rewardId.specified=true");

        // Get all the mGuildMissionList where rewardId is null
        defaultMGuildMissionShouldNotBeFound("rewardId.specified=false");
    }

    @Test
    @Transactional
    public void getAllMGuildMissionsByRewardIdIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mGuildMissionRepository.saveAndFlush(mGuildMission);

        // Get all the mGuildMissionList where rewardId greater than or equals to DEFAULT_REWARD_ID
        defaultMGuildMissionShouldBeFound("rewardId.greaterOrEqualThan=" + DEFAULT_REWARD_ID);

        // Get all the mGuildMissionList where rewardId greater than or equals to UPDATED_REWARD_ID
        defaultMGuildMissionShouldNotBeFound("rewardId.greaterOrEqualThan=" + UPDATED_REWARD_ID);
    }

    @Test
    @Transactional
    public void getAllMGuildMissionsByRewardIdIsLessThanSomething() throws Exception {
        // Initialize the database
        mGuildMissionRepository.saveAndFlush(mGuildMission);

        // Get all the mGuildMissionList where rewardId less than or equals to DEFAULT_REWARD_ID
        defaultMGuildMissionShouldNotBeFound("rewardId.lessThan=" + DEFAULT_REWARD_ID);

        // Get all the mGuildMissionList where rewardId less than or equals to UPDATED_REWARD_ID
        defaultMGuildMissionShouldBeFound("rewardId.lessThan=" + UPDATED_REWARD_ID);
    }


    @Test
    @Transactional
    public void getAllMGuildMissionsByLinkIsEqualToSomething() throws Exception {
        // Initialize the database
        mGuildMissionRepository.saveAndFlush(mGuildMission);

        // Get all the mGuildMissionList where link equals to DEFAULT_LINK
        defaultMGuildMissionShouldBeFound("link.equals=" + DEFAULT_LINK);

        // Get all the mGuildMissionList where link equals to UPDATED_LINK
        defaultMGuildMissionShouldNotBeFound("link.equals=" + UPDATED_LINK);
    }

    @Test
    @Transactional
    public void getAllMGuildMissionsByLinkIsInShouldWork() throws Exception {
        // Initialize the database
        mGuildMissionRepository.saveAndFlush(mGuildMission);

        // Get all the mGuildMissionList where link in DEFAULT_LINK or UPDATED_LINK
        defaultMGuildMissionShouldBeFound("link.in=" + DEFAULT_LINK + "," + UPDATED_LINK);

        // Get all the mGuildMissionList where link equals to UPDATED_LINK
        defaultMGuildMissionShouldNotBeFound("link.in=" + UPDATED_LINK);
    }

    @Test
    @Transactional
    public void getAllMGuildMissionsByLinkIsNullOrNotNull() throws Exception {
        // Initialize the database
        mGuildMissionRepository.saveAndFlush(mGuildMission);

        // Get all the mGuildMissionList where link is not null
        defaultMGuildMissionShouldBeFound("link.specified=true");

        // Get all the mGuildMissionList where link is null
        defaultMGuildMissionShouldNotBeFound("link.specified=false");
    }

    @Test
    @Transactional
    public void getAllMGuildMissionsByLinkIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mGuildMissionRepository.saveAndFlush(mGuildMission);

        // Get all the mGuildMissionList where link greater than or equals to DEFAULT_LINK
        defaultMGuildMissionShouldBeFound("link.greaterOrEqualThan=" + DEFAULT_LINK);

        // Get all the mGuildMissionList where link greater than or equals to UPDATED_LINK
        defaultMGuildMissionShouldNotBeFound("link.greaterOrEqualThan=" + UPDATED_LINK);
    }

    @Test
    @Transactional
    public void getAllMGuildMissionsByLinkIsLessThanSomething() throws Exception {
        // Initialize the database
        mGuildMissionRepository.saveAndFlush(mGuildMission);

        // Get all the mGuildMissionList where link less than or equals to DEFAULT_LINK
        defaultMGuildMissionShouldNotBeFound("link.lessThan=" + DEFAULT_LINK);

        // Get all the mGuildMissionList where link less than or equals to UPDATED_LINK
        defaultMGuildMissionShouldBeFound("link.lessThan=" + UPDATED_LINK);
    }


    @Test
    @Transactional
    public void getAllMGuildMissionsByPickupIsEqualToSomething() throws Exception {
        // Initialize the database
        mGuildMissionRepository.saveAndFlush(mGuildMission);

        // Get all the mGuildMissionList where pickup equals to DEFAULT_PICKUP
        defaultMGuildMissionShouldBeFound("pickup.equals=" + DEFAULT_PICKUP);

        // Get all the mGuildMissionList where pickup equals to UPDATED_PICKUP
        defaultMGuildMissionShouldNotBeFound("pickup.equals=" + UPDATED_PICKUP);
    }

    @Test
    @Transactional
    public void getAllMGuildMissionsByPickupIsInShouldWork() throws Exception {
        // Initialize the database
        mGuildMissionRepository.saveAndFlush(mGuildMission);

        // Get all the mGuildMissionList where pickup in DEFAULT_PICKUP or UPDATED_PICKUP
        defaultMGuildMissionShouldBeFound("pickup.in=" + DEFAULT_PICKUP + "," + UPDATED_PICKUP);

        // Get all the mGuildMissionList where pickup equals to UPDATED_PICKUP
        defaultMGuildMissionShouldNotBeFound("pickup.in=" + UPDATED_PICKUP);
    }

    @Test
    @Transactional
    public void getAllMGuildMissionsByPickupIsNullOrNotNull() throws Exception {
        // Initialize the database
        mGuildMissionRepository.saveAndFlush(mGuildMission);

        // Get all the mGuildMissionList where pickup is not null
        defaultMGuildMissionShouldBeFound("pickup.specified=true");

        // Get all the mGuildMissionList where pickup is null
        defaultMGuildMissionShouldNotBeFound("pickup.specified=false");
    }

    @Test
    @Transactional
    public void getAllMGuildMissionsByPickupIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mGuildMissionRepository.saveAndFlush(mGuildMission);

        // Get all the mGuildMissionList where pickup greater than or equals to DEFAULT_PICKUP
        defaultMGuildMissionShouldBeFound("pickup.greaterOrEqualThan=" + DEFAULT_PICKUP);

        // Get all the mGuildMissionList where pickup greater than or equals to UPDATED_PICKUP
        defaultMGuildMissionShouldNotBeFound("pickup.greaterOrEqualThan=" + UPDATED_PICKUP);
    }

    @Test
    @Transactional
    public void getAllMGuildMissionsByPickupIsLessThanSomething() throws Exception {
        // Initialize the database
        mGuildMissionRepository.saveAndFlush(mGuildMission);

        // Get all the mGuildMissionList where pickup less than or equals to DEFAULT_PICKUP
        defaultMGuildMissionShouldNotBeFound("pickup.lessThan=" + DEFAULT_PICKUP);

        // Get all the mGuildMissionList where pickup less than or equals to UPDATED_PICKUP
        defaultMGuildMissionShouldBeFound("pickup.lessThan=" + UPDATED_PICKUP);
    }


    @Test
    @Transactional
    public void getAllMGuildMissionsByTriggerMissionIdIsEqualToSomething() throws Exception {
        // Initialize the database
        mGuildMissionRepository.saveAndFlush(mGuildMission);

        // Get all the mGuildMissionList where triggerMissionId equals to DEFAULT_TRIGGER_MISSION_ID
        defaultMGuildMissionShouldBeFound("triggerMissionId.equals=" + DEFAULT_TRIGGER_MISSION_ID);

        // Get all the mGuildMissionList where triggerMissionId equals to UPDATED_TRIGGER_MISSION_ID
        defaultMGuildMissionShouldNotBeFound("triggerMissionId.equals=" + UPDATED_TRIGGER_MISSION_ID);
    }

    @Test
    @Transactional
    public void getAllMGuildMissionsByTriggerMissionIdIsInShouldWork() throws Exception {
        // Initialize the database
        mGuildMissionRepository.saveAndFlush(mGuildMission);

        // Get all the mGuildMissionList where triggerMissionId in DEFAULT_TRIGGER_MISSION_ID or UPDATED_TRIGGER_MISSION_ID
        defaultMGuildMissionShouldBeFound("triggerMissionId.in=" + DEFAULT_TRIGGER_MISSION_ID + "," + UPDATED_TRIGGER_MISSION_ID);

        // Get all the mGuildMissionList where triggerMissionId equals to UPDATED_TRIGGER_MISSION_ID
        defaultMGuildMissionShouldNotBeFound("triggerMissionId.in=" + UPDATED_TRIGGER_MISSION_ID);
    }

    @Test
    @Transactional
    public void getAllMGuildMissionsByTriggerMissionIdIsNullOrNotNull() throws Exception {
        // Initialize the database
        mGuildMissionRepository.saveAndFlush(mGuildMission);

        // Get all the mGuildMissionList where triggerMissionId is not null
        defaultMGuildMissionShouldBeFound("triggerMissionId.specified=true");

        // Get all the mGuildMissionList where triggerMissionId is null
        defaultMGuildMissionShouldNotBeFound("triggerMissionId.specified=false");
    }

    @Test
    @Transactional
    public void getAllMGuildMissionsByTriggerMissionIdIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mGuildMissionRepository.saveAndFlush(mGuildMission);

        // Get all the mGuildMissionList where triggerMissionId greater than or equals to DEFAULT_TRIGGER_MISSION_ID
        defaultMGuildMissionShouldBeFound("triggerMissionId.greaterOrEqualThan=" + DEFAULT_TRIGGER_MISSION_ID);

        // Get all the mGuildMissionList where triggerMissionId greater than or equals to UPDATED_TRIGGER_MISSION_ID
        defaultMGuildMissionShouldNotBeFound("triggerMissionId.greaterOrEqualThan=" + UPDATED_TRIGGER_MISSION_ID);
    }

    @Test
    @Transactional
    public void getAllMGuildMissionsByTriggerMissionIdIsLessThanSomething() throws Exception {
        // Initialize the database
        mGuildMissionRepository.saveAndFlush(mGuildMission);

        // Get all the mGuildMissionList where triggerMissionId less than or equals to DEFAULT_TRIGGER_MISSION_ID
        defaultMGuildMissionShouldNotBeFound("triggerMissionId.lessThan=" + DEFAULT_TRIGGER_MISSION_ID);

        // Get all the mGuildMissionList where triggerMissionId less than or equals to UPDATED_TRIGGER_MISSION_ID
        defaultMGuildMissionShouldBeFound("triggerMissionId.lessThan=" + UPDATED_TRIGGER_MISSION_ID);
    }


    @Test
    @Transactional
    public void getAllMGuildMissionsByOrderNumIsEqualToSomething() throws Exception {
        // Initialize the database
        mGuildMissionRepository.saveAndFlush(mGuildMission);

        // Get all the mGuildMissionList where orderNum equals to DEFAULT_ORDER_NUM
        defaultMGuildMissionShouldBeFound("orderNum.equals=" + DEFAULT_ORDER_NUM);

        // Get all the mGuildMissionList where orderNum equals to UPDATED_ORDER_NUM
        defaultMGuildMissionShouldNotBeFound("orderNum.equals=" + UPDATED_ORDER_NUM);
    }

    @Test
    @Transactional
    public void getAllMGuildMissionsByOrderNumIsInShouldWork() throws Exception {
        // Initialize the database
        mGuildMissionRepository.saveAndFlush(mGuildMission);

        // Get all the mGuildMissionList where orderNum in DEFAULT_ORDER_NUM or UPDATED_ORDER_NUM
        defaultMGuildMissionShouldBeFound("orderNum.in=" + DEFAULT_ORDER_NUM + "," + UPDATED_ORDER_NUM);

        // Get all the mGuildMissionList where orderNum equals to UPDATED_ORDER_NUM
        defaultMGuildMissionShouldNotBeFound("orderNum.in=" + UPDATED_ORDER_NUM);
    }

    @Test
    @Transactional
    public void getAllMGuildMissionsByOrderNumIsNullOrNotNull() throws Exception {
        // Initialize the database
        mGuildMissionRepository.saveAndFlush(mGuildMission);

        // Get all the mGuildMissionList where orderNum is not null
        defaultMGuildMissionShouldBeFound("orderNum.specified=true");

        // Get all the mGuildMissionList where orderNum is null
        defaultMGuildMissionShouldNotBeFound("orderNum.specified=false");
    }

    @Test
    @Transactional
    public void getAllMGuildMissionsByOrderNumIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mGuildMissionRepository.saveAndFlush(mGuildMission);

        // Get all the mGuildMissionList where orderNum greater than or equals to DEFAULT_ORDER_NUM
        defaultMGuildMissionShouldBeFound("orderNum.greaterOrEqualThan=" + DEFAULT_ORDER_NUM);

        // Get all the mGuildMissionList where orderNum greater than or equals to UPDATED_ORDER_NUM
        defaultMGuildMissionShouldNotBeFound("orderNum.greaterOrEqualThan=" + UPDATED_ORDER_NUM);
    }

    @Test
    @Transactional
    public void getAllMGuildMissionsByOrderNumIsLessThanSomething() throws Exception {
        // Initialize the database
        mGuildMissionRepository.saveAndFlush(mGuildMission);

        // Get all the mGuildMissionList where orderNum less than or equals to DEFAULT_ORDER_NUM
        defaultMGuildMissionShouldNotBeFound("orderNum.lessThan=" + DEFAULT_ORDER_NUM);

        // Get all the mGuildMissionList where orderNum less than or equals to UPDATED_ORDER_NUM
        defaultMGuildMissionShouldBeFound("orderNum.lessThan=" + UPDATED_ORDER_NUM);
    }


    @Test
    @Transactional
    public void getAllMGuildMissionsByIdIsEqualToSomething() throws Exception {
        // Get already existing entity
        MMissionReward id = mGuildMission.getId();
        mGuildMissionRepository.saveAndFlush(mGuildMission);
        Long idId = id.getId();

        // Get all the mGuildMissionList where id equals to idId
        defaultMGuildMissionShouldBeFound("idId.equals=" + idId);

        // Get all the mGuildMissionList where id equals to idId + 1
        defaultMGuildMissionShouldNotBeFound("idId.equals=" + (idId + 1));
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultMGuildMissionShouldBeFound(String filter) throws Exception {
        restMGuildMissionMockMvc.perform(get("/api/m-guild-missions?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(mGuildMission.getId().intValue())))
            .andExpect(jsonPath("$.[*].term").value(hasItem(DEFAULT_TERM)))
            .andExpect(jsonPath("$.[*].title").value(hasItem(DEFAULT_TITLE.toString())))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION.toString())))
            .andExpect(jsonPath("$.[*].missionType").value(hasItem(DEFAULT_MISSION_TYPE)))
            .andExpect(jsonPath("$.[*].param1").value(hasItem(DEFAULT_PARAM_1)))
            .andExpect(jsonPath("$.[*].rewardId").value(hasItem(DEFAULT_REWARD_ID)))
            .andExpect(jsonPath("$.[*].link").value(hasItem(DEFAULT_LINK)))
            .andExpect(jsonPath("$.[*].sceneTransitionParam").value(hasItem(DEFAULT_SCENE_TRANSITION_PARAM.toString())))
            .andExpect(jsonPath("$.[*].pickup").value(hasItem(DEFAULT_PICKUP)))
            .andExpect(jsonPath("$.[*].triggerMissionId").value(hasItem(DEFAULT_TRIGGER_MISSION_ID)))
            .andExpect(jsonPath("$.[*].orderNum").value(hasItem(DEFAULT_ORDER_NUM)));

        // Check, that the count call also returns 1
        restMGuildMissionMockMvc.perform(get("/api/m-guild-missions/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultMGuildMissionShouldNotBeFound(String filter) throws Exception {
        restMGuildMissionMockMvc.perform(get("/api/m-guild-missions?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restMGuildMissionMockMvc.perform(get("/api/m-guild-missions/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("0"));
    }


    @Test
    @Transactional
    public void getNonExistingMGuildMission() throws Exception {
        // Get the mGuildMission
        restMGuildMissionMockMvc.perform(get("/api/m-guild-missions/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateMGuildMission() throws Exception {
        // Initialize the database
        mGuildMissionRepository.saveAndFlush(mGuildMission);

        int databaseSizeBeforeUpdate = mGuildMissionRepository.findAll().size();

        // Update the mGuildMission
        MGuildMission updatedMGuildMission = mGuildMissionRepository.findById(mGuildMission.getId()).get();
        // Disconnect from session so that the updates on updatedMGuildMission are not directly saved in db
        em.detach(updatedMGuildMission);
        updatedMGuildMission
            .term(UPDATED_TERM)
            .title(UPDATED_TITLE)
            .description(UPDATED_DESCRIPTION)
            .missionType(UPDATED_MISSION_TYPE)
            .param1(UPDATED_PARAM_1)
            .rewardId(UPDATED_REWARD_ID)
            .link(UPDATED_LINK)
            .sceneTransitionParam(UPDATED_SCENE_TRANSITION_PARAM)
            .pickup(UPDATED_PICKUP)
            .triggerMissionId(UPDATED_TRIGGER_MISSION_ID)
            .orderNum(UPDATED_ORDER_NUM);
        MGuildMissionDTO mGuildMissionDTO = mGuildMissionMapper.toDto(updatedMGuildMission);

        restMGuildMissionMockMvc.perform(put("/api/m-guild-missions")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mGuildMissionDTO)))
            .andExpect(status().isOk());

        // Validate the MGuildMission in the database
        List<MGuildMission> mGuildMissionList = mGuildMissionRepository.findAll();
        assertThat(mGuildMissionList).hasSize(databaseSizeBeforeUpdate);
        MGuildMission testMGuildMission = mGuildMissionList.get(mGuildMissionList.size() - 1);
        assertThat(testMGuildMission.getTerm()).isEqualTo(UPDATED_TERM);
        assertThat(testMGuildMission.getTitle()).isEqualTo(UPDATED_TITLE);
        assertThat(testMGuildMission.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
        assertThat(testMGuildMission.getMissionType()).isEqualTo(UPDATED_MISSION_TYPE);
        assertThat(testMGuildMission.getParam1()).isEqualTo(UPDATED_PARAM_1);
        assertThat(testMGuildMission.getRewardId()).isEqualTo(UPDATED_REWARD_ID);
        assertThat(testMGuildMission.getLink()).isEqualTo(UPDATED_LINK);
        assertThat(testMGuildMission.getSceneTransitionParam()).isEqualTo(UPDATED_SCENE_TRANSITION_PARAM);
        assertThat(testMGuildMission.getPickup()).isEqualTo(UPDATED_PICKUP);
        assertThat(testMGuildMission.getTriggerMissionId()).isEqualTo(UPDATED_TRIGGER_MISSION_ID);
        assertThat(testMGuildMission.getOrderNum()).isEqualTo(UPDATED_ORDER_NUM);
    }

    @Test
    @Transactional
    public void updateNonExistingMGuildMission() throws Exception {
        int databaseSizeBeforeUpdate = mGuildMissionRepository.findAll().size();

        // Create the MGuildMission
        MGuildMissionDTO mGuildMissionDTO = mGuildMissionMapper.toDto(mGuildMission);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restMGuildMissionMockMvc.perform(put("/api/m-guild-missions")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mGuildMissionDTO)))
            .andExpect(status().isBadRequest());

        // Validate the MGuildMission in the database
        List<MGuildMission> mGuildMissionList = mGuildMissionRepository.findAll();
        assertThat(mGuildMissionList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteMGuildMission() throws Exception {
        // Initialize the database
        mGuildMissionRepository.saveAndFlush(mGuildMission);

        int databaseSizeBeforeDelete = mGuildMissionRepository.findAll().size();

        // Delete the mGuildMission
        restMGuildMissionMockMvc.perform(delete("/api/m-guild-missions/{id}", mGuildMission.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database is empty
        List<MGuildMission> mGuildMissionList = mGuildMissionRepository.findAll();
        assertThat(mGuildMissionList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(MGuildMission.class);
        MGuildMission mGuildMission1 = new MGuildMission();
        mGuildMission1.setId(1L);
        MGuildMission mGuildMission2 = new MGuildMission();
        mGuildMission2.setId(mGuildMission1.getId());
        assertThat(mGuildMission1).isEqualTo(mGuildMission2);
        mGuildMission2.setId(2L);
        assertThat(mGuildMission1).isNotEqualTo(mGuildMission2);
        mGuildMission1.setId(null);
        assertThat(mGuildMission1).isNotEqualTo(mGuildMission2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(MGuildMissionDTO.class);
        MGuildMissionDTO mGuildMissionDTO1 = new MGuildMissionDTO();
        mGuildMissionDTO1.setId(1L);
        MGuildMissionDTO mGuildMissionDTO2 = new MGuildMissionDTO();
        assertThat(mGuildMissionDTO1).isNotEqualTo(mGuildMissionDTO2);
        mGuildMissionDTO2.setId(mGuildMissionDTO1.getId());
        assertThat(mGuildMissionDTO1).isEqualTo(mGuildMissionDTO2);
        mGuildMissionDTO2.setId(2L);
        assertThat(mGuildMissionDTO1).isNotEqualTo(mGuildMissionDTO2);
        mGuildMissionDTO1.setId(null);
        assertThat(mGuildMissionDTO1).isNotEqualTo(mGuildMissionDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(mGuildMissionMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(mGuildMissionMapper.fromId(null)).isNull();
    }
}
