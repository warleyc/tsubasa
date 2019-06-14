package io.shm.tsubasa.web.rest;

import io.shm.tsubasa.TsubasaApp;
import io.shm.tsubasa.domain.MMission;
import io.shm.tsubasa.domain.MMissionReward;
import io.shm.tsubasa.domain.MAchievement;
import io.shm.tsubasa.repository.MMissionRepository;
import io.shm.tsubasa.service.MMissionService;
import io.shm.tsubasa.service.dto.MMissionDTO;
import io.shm.tsubasa.service.mapper.MMissionMapper;
import io.shm.tsubasa.web.rest.errors.ExceptionTranslator;
import io.shm.tsubasa.service.dto.MMissionCriteria;
import io.shm.tsubasa.service.MMissionQueryService;

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
 * Integration tests for the {@Link MMissionResource} REST controller.
 */
@SpringBootTest(classes = TsubasaApp.class)
public class MMissionResourceIT {

    private static final Integer DEFAULT_TERM = 1;
    private static final Integer UPDATED_TERM = 2;

    private static final Integer DEFAULT_ROUND_NUM = 1;
    private static final Integer UPDATED_ROUND_NUM = 2;

    private static final String DEFAULT_TITLE = "AAAAAAAAAA";
    private static final String UPDATED_TITLE = "BBBBBBBBBB";

    private static final String DEFAULT_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPTION = "BBBBBBBBBB";

    private static final Integer DEFAULT_MISSION_TYPE = 1;
    private static final Integer UPDATED_MISSION_TYPE = 2;

    private static final Integer DEFAULT_ABSOLUTE = 1;
    private static final Integer UPDATED_ABSOLUTE = 2;

    private static final Integer DEFAULT_PARAM_1 = 1;
    private static final Integer UPDATED_PARAM_1 = 2;

    private static final Integer DEFAULT_PARAM_2 = 1;
    private static final Integer UPDATED_PARAM_2 = 2;

    private static final Integer DEFAULT_PARAM_3 = 1;
    private static final Integer UPDATED_PARAM_3 = 2;

    private static final Integer DEFAULT_PARAM_4 = 1;
    private static final Integer UPDATED_PARAM_4 = 2;

    private static final Integer DEFAULT_PARAM_5 = 1;
    private static final Integer UPDATED_PARAM_5 = 2;

    private static final Integer DEFAULT_TRIGGER = 1;
    private static final Integer UPDATED_TRIGGER = 2;

    private static final Integer DEFAULT_TRIGGER_CONDITION = 1;
    private static final Integer UPDATED_TRIGGER_CONDITION = 2;

    private static final Integer DEFAULT_REWARD_ID = 1;
    private static final Integer UPDATED_REWARD_ID = 2;

    private static final Integer DEFAULT_START_AT = 1;
    private static final Integer UPDATED_START_AT = 2;

    private static final Integer DEFAULT_END_AT = 1;
    private static final Integer UPDATED_END_AT = 2;

    private static final Integer DEFAULT_LINK = 1;
    private static final Integer UPDATED_LINK = 2;

    private static final String DEFAULT_SCENE_TRANSITION_PARAM = "AAAAAAAAAA";
    private static final String UPDATED_SCENE_TRANSITION_PARAM = "BBBBBBBBBB";

    private static final Integer DEFAULT_PICKUP = 1;
    private static final Integer UPDATED_PICKUP = 2;

    private static final Integer DEFAULT_ORDER_NUM = 1;
    private static final Integer UPDATED_ORDER_NUM = 2;

    @Autowired
    private MMissionRepository mMissionRepository;

    @Autowired
    private MMissionMapper mMissionMapper;

    @Autowired
    private MMissionService mMissionService;

    @Autowired
    private MMissionQueryService mMissionQueryService;

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

    private MockMvc restMMissionMockMvc;

    private MMission mMission;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final MMissionResource mMissionResource = new MMissionResource(mMissionService, mMissionQueryService);
        this.restMMissionMockMvc = MockMvcBuilders.standaloneSetup(mMissionResource)
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
    public static MMission createEntity(EntityManager em) {
        MMission mMission = new MMission()
            .term(DEFAULT_TERM)
            .roundNum(DEFAULT_ROUND_NUM)
            .title(DEFAULT_TITLE)
            .description(DEFAULT_DESCRIPTION)
            .missionType(DEFAULT_MISSION_TYPE)
            .absolute(DEFAULT_ABSOLUTE)
            .param1(DEFAULT_PARAM_1)
            .param2(DEFAULT_PARAM_2)
            .param3(DEFAULT_PARAM_3)
            .param4(DEFAULT_PARAM_4)
            .param5(DEFAULT_PARAM_5)
            .trigger(DEFAULT_TRIGGER)
            .triggerCondition(DEFAULT_TRIGGER_CONDITION)
            .rewardId(DEFAULT_REWARD_ID)
            .startAt(DEFAULT_START_AT)
            .endAt(DEFAULT_END_AT)
            .link(DEFAULT_LINK)
            .sceneTransitionParam(DEFAULT_SCENE_TRANSITION_PARAM)
            .pickup(DEFAULT_PICKUP)
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
        mMission.setMmissionreward(mMissionReward);
        return mMission;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static MMission createUpdatedEntity(EntityManager em) {
        MMission mMission = new MMission()
            .term(UPDATED_TERM)
            .roundNum(UPDATED_ROUND_NUM)
            .title(UPDATED_TITLE)
            .description(UPDATED_DESCRIPTION)
            .missionType(UPDATED_MISSION_TYPE)
            .absolute(UPDATED_ABSOLUTE)
            .param1(UPDATED_PARAM_1)
            .param2(UPDATED_PARAM_2)
            .param3(UPDATED_PARAM_3)
            .param4(UPDATED_PARAM_4)
            .param5(UPDATED_PARAM_5)
            .trigger(UPDATED_TRIGGER)
            .triggerCondition(UPDATED_TRIGGER_CONDITION)
            .rewardId(UPDATED_REWARD_ID)
            .startAt(UPDATED_START_AT)
            .endAt(UPDATED_END_AT)
            .link(UPDATED_LINK)
            .sceneTransitionParam(UPDATED_SCENE_TRANSITION_PARAM)
            .pickup(UPDATED_PICKUP)
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
        mMission.setMmissionreward(mMissionReward);
        return mMission;
    }

    @BeforeEach
    public void initTest() {
        mMission = createEntity(em);
    }

    @Test
    @Transactional
    public void createMMission() throws Exception {
        int databaseSizeBeforeCreate = mMissionRepository.findAll().size();

        // Create the MMission
        MMissionDTO mMissionDTO = mMissionMapper.toDto(mMission);
        restMMissionMockMvc.perform(post("/api/m-missions")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mMissionDTO)))
            .andExpect(status().isCreated());

        // Validate the MMission in the database
        List<MMission> mMissionList = mMissionRepository.findAll();
        assertThat(mMissionList).hasSize(databaseSizeBeforeCreate + 1);
        MMission testMMission = mMissionList.get(mMissionList.size() - 1);
        assertThat(testMMission.getTerm()).isEqualTo(DEFAULT_TERM);
        assertThat(testMMission.getRoundNum()).isEqualTo(DEFAULT_ROUND_NUM);
        assertThat(testMMission.getTitle()).isEqualTo(DEFAULT_TITLE);
        assertThat(testMMission.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
        assertThat(testMMission.getMissionType()).isEqualTo(DEFAULT_MISSION_TYPE);
        assertThat(testMMission.getAbsolute()).isEqualTo(DEFAULT_ABSOLUTE);
        assertThat(testMMission.getParam1()).isEqualTo(DEFAULT_PARAM_1);
        assertThat(testMMission.getParam2()).isEqualTo(DEFAULT_PARAM_2);
        assertThat(testMMission.getParam3()).isEqualTo(DEFAULT_PARAM_3);
        assertThat(testMMission.getParam4()).isEqualTo(DEFAULT_PARAM_4);
        assertThat(testMMission.getParam5()).isEqualTo(DEFAULT_PARAM_5);
        assertThat(testMMission.getTrigger()).isEqualTo(DEFAULT_TRIGGER);
        assertThat(testMMission.getTriggerCondition()).isEqualTo(DEFAULT_TRIGGER_CONDITION);
        assertThat(testMMission.getRewardId()).isEqualTo(DEFAULT_REWARD_ID);
        assertThat(testMMission.getStartAt()).isEqualTo(DEFAULT_START_AT);
        assertThat(testMMission.getEndAt()).isEqualTo(DEFAULT_END_AT);
        assertThat(testMMission.getLink()).isEqualTo(DEFAULT_LINK);
        assertThat(testMMission.getSceneTransitionParam()).isEqualTo(DEFAULT_SCENE_TRANSITION_PARAM);
        assertThat(testMMission.getPickup()).isEqualTo(DEFAULT_PICKUP);
        assertThat(testMMission.getOrderNum()).isEqualTo(DEFAULT_ORDER_NUM);
    }

    @Test
    @Transactional
    public void createMMissionWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = mMissionRepository.findAll().size();

        // Create the MMission with an existing ID
        mMission.setId(1L);
        MMissionDTO mMissionDTO = mMissionMapper.toDto(mMission);

        // An entity with an existing ID cannot be created, so this API call must fail
        restMMissionMockMvc.perform(post("/api/m-missions")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mMissionDTO)))
            .andExpect(status().isBadRequest());

        // Validate the MMission in the database
        List<MMission> mMissionList = mMissionRepository.findAll();
        assertThat(mMissionList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkTermIsRequired() throws Exception {
        int databaseSizeBeforeTest = mMissionRepository.findAll().size();
        // set the field null
        mMission.setTerm(null);

        // Create the MMission, which fails.
        MMissionDTO mMissionDTO = mMissionMapper.toDto(mMission);

        restMMissionMockMvc.perform(post("/api/m-missions")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mMissionDTO)))
            .andExpect(status().isBadRequest());

        List<MMission> mMissionList = mMissionRepository.findAll();
        assertThat(mMissionList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkMissionTypeIsRequired() throws Exception {
        int databaseSizeBeforeTest = mMissionRepository.findAll().size();
        // set the field null
        mMission.setMissionType(null);

        // Create the MMission, which fails.
        MMissionDTO mMissionDTO = mMissionMapper.toDto(mMission);

        restMMissionMockMvc.perform(post("/api/m-missions")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mMissionDTO)))
            .andExpect(status().isBadRequest());

        List<MMission> mMissionList = mMissionRepository.findAll();
        assertThat(mMissionList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkAbsoluteIsRequired() throws Exception {
        int databaseSizeBeforeTest = mMissionRepository.findAll().size();
        // set the field null
        mMission.setAbsolute(null);

        // Create the MMission, which fails.
        MMissionDTO mMissionDTO = mMissionMapper.toDto(mMission);

        restMMissionMockMvc.perform(post("/api/m-missions")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mMissionDTO)))
            .andExpect(status().isBadRequest());

        List<MMission> mMissionList = mMissionRepository.findAll();
        assertThat(mMissionList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkTriggerIsRequired() throws Exception {
        int databaseSizeBeforeTest = mMissionRepository.findAll().size();
        // set the field null
        mMission.setTrigger(null);

        // Create the MMission, which fails.
        MMissionDTO mMissionDTO = mMissionMapper.toDto(mMission);

        restMMissionMockMvc.perform(post("/api/m-missions")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mMissionDTO)))
            .andExpect(status().isBadRequest());

        List<MMission> mMissionList = mMissionRepository.findAll();
        assertThat(mMissionList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkTriggerConditionIsRequired() throws Exception {
        int databaseSizeBeforeTest = mMissionRepository.findAll().size();
        // set the field null
        mMission.setTriggerCondition(null);

        // Create the MMission, which fails.
        MMissionDTO mMissionDTO = mMissionMapper.toDto(mMission);

        restMMissionMockMvc.perform(post("/api/m-missions")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mMissionDTO)))
            .andExpect(status().isBadRequest());

        List<MMission> mMissionList = mMissionRepository.findAll();
        assertThat(mMissionList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkRewardIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = mMissionRepository.findAll().size();
        // set the field null
        mMission.setRewardId(null);

        // Create the MMission, which fails.
        MMissionDTO mMissionDTO = mMissionMapper.toDto(mMission);

        restMMissionMockMvc.perform(post("/api/m-missions")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mMissionDTO)))
            .andExpect(status().isBadRequest());

        List<MMission> mMissionList = mMissionRepository.findAll();
        assertThat(mMissionList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkStartAtIsRequired() throws Exception {
        int databaseSizeBeforeTest = mMissionRepository.findAll().size();
        // set the field null
        mMission.setStartAt(null);

        // Create the MMission, which fails.
        MMissionDTO mMissionDTO = mMissionMapper.toDto(mMission);

        restMMissionMockMvc.perform(post("/api/m-missions")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mMissionDTO)))
            .andExpect(status().isBadRequest());

        List<MMission> mMissionList = mMissionRepository.findAll();
        assertThat(mMissionList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkPickupIsRequired() throws Exception {
        int databaseSizeBeforeTest = mMissionRepository.findAll().size();
        // set the field null
        mMission.setPickup(null);

        // Create the MMission, which fails.
        MMissionDTO mMissionDTO = mMissionMapper.toDto(mMission);

        restMMissionMockMvc.perform(post("/api/m-missions")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mMissionDTO)))
            .andExpect(status().isBadRequest());

        List<MMission> mMissionList = mMissionRepository.findAll();
        assertThat(mMissionList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkOrderNumIsRequired() throws Exception {
        int databaseSizeBeforeTest = mMissionRepository.findAll().size();
        // set the field null
        mMission.setOrderNum(null);

        // Create the MMission, which fails.
        MMissionDTO mMissionDTO = mMissionMapper.toDto(mMission);

        restMMissionMockMvc.perform(post("/api/m-missions")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mMissionDTO)))
            .andExpect(status().isBadRequest());

        List<MMission> mMissionList = mMissionRepository.findAll();
        assertThat(mMissionList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllMMissions() throws Exception {
        // Initialize the database
        mMissionRepository.saveAndFlush(mMission);

        // Get all the mMissionList
        restMMissionMockMvc.perform(get("/api/m-missions?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(mMission.getId().intValue())))
            .andExpect(jsonPath("$.[*].term").value(hasItem(DEFAULT_TERM)))
            .andExpect(jsonPath("$.[*].roundNum").value(hasItem(DEFAULT_ROUND_NUM)))
            .andExpect(jsonPath("$.[*].title").value(hasItem(DEFAULT_TITLE.toString())))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION.toString())))
            .andExpect(jsonPath("$.[*].missionType").value(hasItem(DEFAULT_MISSION_TYPE)))
            .andExpect(jsonPath("$.[*].absolute").value(hasItem(DEFAULT_ABSOLUTE)))
            .andExpect(jsonPath("$.[*].param1").value(hasItem(DEFAULT_PARAM_1)))
            .andExpect(jsonPath("$.[*].param2").value(hasItem(DEFAULT_PARAM_2)))
            .andExpect(jsonPath("$.[*].param3").value(hasItem(DEFAULT_PARAM_3)))
            .andExpect(jsonPath("$.[*].param4").value(hasItem(DEFAULT_PARAM_4)))
            .andExpect(jsonPath("$.[*].param5").value(hasItem(DEFAULT_PARAM_5)))
            .andExpect(jsonPath("$.[*].trigger").value(hasItem(DEFAULT_TRIGGER)))
            .andExpect(jsonPath("$.[*].triggerCondition").value(hasItem(DEFAULT_TRIGGER_CONDITION)))
            .andExpect(jsonPath("$.[*].rewardId").value(hasItem(DEFAULT_REWARD_ID)))
            .andExpect(jsonPath("$.[*].startAt").value(hasItem(DEFAULT_START_AT)))
            .andExpect(jsonPath("$.[*].endAt").value(hasItem(DEFAULT_END_AT)))
            .andExpect(jsonPath("$.[*].link").value(hasItem(DEFAULT_LINK)))
            .andExpect(jsonPath("$.[*].sceneTransitionParam").value(hasItem(DEFAULT_SCENE_TRANSITION_PARAM.toString())))
            .andExpect(jsonPath("$.[*].pickup").value(hasItem(DEFAULT_PICKUP)))
            .andExpect(jsonPath("$.[*].orderNum").value(hasItem(DEFAULT_ORDER_NUM)));
    }
    
    @Test
    @Transactional
    public void getMMission() throws Exception {
        // Initialize the database
        mMissionRepository.saveAndFlush(mMission);

        // Get the mMission
        restMMissionMockMvc.perform(get("/api/m-missions/{id}", mMission.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(mMission.getId().intValue()))
            .andExpect(jsonPath("$.term").value(DEFAULT_TERM))
            .andExpect(jsonPath("$.roundNum").value(DEFAULT_ROUND_NUM))
            .andExpect(jsonPath("$.title").value(DEFAULT_TITLE.toString()))
            .andExpect(jsonPath("$.description").value(DEFAULT_DESCRIPTION.toString()))
            .andExpect(jsonPath("$.missionType").value(DEFAULT_MISSION_TYPE))
            .andExpect(jsonPath("$.absolute").value(DEFAULT_ABSOLUTE))
            .andExpect(jsonPath("$.param1").value(DEFAULT_PARAM_1))
            .andExpect(jsonPath("$.param2").value(DEFAULT_PARAM_2))
            .andExpect(jsonPath("$.param3").value(DEFAULT_PARAM_3))
            .andExpect(jsonPath("$.param4").value(DEFAULT_PARAM_4))
            .andExpect(jsonPath("$.param5").value(DEFAULT_PARAM_5))
            .andExpect(jsonPath("$.trigger").value(DEFAULT_TRIGGER))
            .andExpect(jsonPath("$.triggerCondition").value(DEFAULT_TRIGGER_CONDITION))
            .andExpect(jsonPath("$.rewardId").value(DEFAULT_REWARD_ID))
            .andExpect(jsonPath("$.startAt").value(DEFAULT_START_AT))
            .andExpect(jsonPath("$.endAt").value(DEFAULT_END_AT))
            .andExpect(jsonPath("$.link").value(DEFAULT_LINK))
            .andExpect(jsonPath("$.sceneTransitionParam").value(DEFAULT_SCENE_TRANSITION_PARAM.toString()))
            .andExpect(jsonPath("$.pickup").value(DEFAULT_PICKUP))
            .andExpect(jsonPath("$.orderNum").value(DEFAULT_ORDER_NUM));
    }

    @Test
    @Transactional
    public void getAllMMissionsByTermIsEqualToSomething() throws Exception {
        // Initialize the database
        mMissionRepository.saveAndFlush(mMission);

        // Get all the mMissionList where term equals to DEFAULT_TERM
        defaultMMissionShouldBeFound("term.equals=" + DEFAULT_TERM);

        // Get all the mMissionList where term equals to UPDATED_TERM
        defaultMMissionShouldNotBeFound("term.equals=" + UPDATED_TERM);
    }

    @Test
    @Transactional
    public void getAllMMissionsByTermIsInShouldWork() throws Exception {
        // Initialize the database
        mMissionRepository.saveAndFlush(mMission);

        // Get all the mMissionList where term in DEFAULT_TERM or UPDATED_TERM
        defaultMMissionShouldBeFound("term.in=" + DEFAULT_TERM + "," + UPDATED_TERM);

        // Get all the mMissionList where term equals to UPDATED_TERM
        defaultMMissionShouldNotBeFound("term.in=" + UPDATED_TERM);
    }

    @Test
    @Transactional
    public void getAllMMissionsByTermIsNullOrNotNull() throws Exception {
        // Initialize the database
        mMissionRepository.saveAndFlush(mMission);

        // Get all the mMissionList where term is not null
        defaultMMissionShouldBeFound("term.specified=true");

        // Get all the mMissionList where term is null
        defaultMMissionShouldNotBeFound("term.specified=false");
    }

    @Test
    @Transactional
    public void getAllMMissionsByTermIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mMissionRepository.saveAndFlush(mMission);

        // Get all the mMissionList where term greater than or equals to DEFAULT_TERM
        defaultMMissionShouldBeFound("term.greaterOrEqualThan=" + DEFAULT_TERM);

        // Get all the mMissionList where term greater than or equals to UPDATED_TERM
        defaultMMissionShouldNotBeFound("term.greaterOrEqualThan=" + UPDATED_TERM);
    }

    @Test
    @Transactional
    public void getAllMMissionsByTermIsLessThanSomething() throws Exception {
        // Initialize the database
        mMissionRepository.saveAndFlush(mMission);

        // Get all the mMissionList where term less than or equals to DEFAULT_TERM
        defaultMMissionShouldNotBeFound("term.lessThan=" + DEFAULT_TERM);

        // Get all the mMissionList where term less than or equals to UPDATED_TERM
        defaultMMissionShouldBeFound("term.lessThan=" + UPDATED_TERM);
    }


    @Test
    @Transactional
    public void getAllMMissionsByRoundNumIsEqualToSomething() throws Exception {
        // Initialize the database
        mMissionRepository.saveAndFlush(mMission);

        // Get all the mMissionList where roundNum equals to DEFAULT_ROUND_NUM
        defaultMMissionShouldBeFound("roundNum.equals=" + DEFAULT_ROUND_NUM);

        // Get all the mMissionList where roundNum equals to UPDATED_ROUND_NUM
        defaultMMissionShouldNotBeFound("roundNum.equals=" + UPDATED_ROUND_NUM);
    }

    @Test
    @Transactional
    public void getAllMMissionsByRoundNumIsInShouldWork() throws Exception {
        // Initialize the database
        mMissionRepository.saveAndFlush(mMission);

        // Get all the mMissionList where roundNum in DEFAULT_ROUND_NUM or UPDATED_ROUND_NUM
        defaultMMissionShouldBeFound("roundNum.in=" + DEFAULT_ROUND_NUM + "," + UPDATED_ROUND_NUM);

        // Get all the mMissionList where roundNum equals to UPDATED_ROUND_NUM
        defaultMMissionShouldNotBeFound("roundNum.in=" + UPDATED_ROUND_NUM);
    }

    @Test
    @Transactional
    public void getAllMMissionsByRoundNumIsNullOrNotNull() throws Exception {
        // Initialize the database
        mMissionRepository.saveAndFlush(mMission);

        // Get all the mMissionList where roundNum is not null
        defaultMMissionShouldBeFound("roundNum.specified=true");

        // Get all the mMissionList where roundNum is null
        defaultMMissionShouldNotBeFound("roundNum.specified=false");
    }

    @Test
    @Transactional
    public void getAllMMissionsByRoundNumIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mMissionRepository.saveAndFlush(mMission);

        // Get all the mMissionList where roundNum greater than or equals to DEFAULT_ROUND_NUM
        defaultMMissionShouldBeFound("roundNum.greaterOrEqualThan=" + DEFAULT_ROUND_NUM);

        // Get all the mMissionList where roundNum greater than or equals to UPDATED_ROUND_NUM
        defaultMMissionShouldNotBeFound("roundNum.greaterOrEqualThan=" + UPDATED_ROUND_NUM);
    }

    @Test
    @Transactional
    public void getAllMMissionsByRoundNumIsLessThanSomething() throws Exception {
        // Initialize the database
        mMissionRepository.saveAndFlush(mMission);

        // Get all the mMissionList where roundNum less than or equals to DEFAULT_ROUND_NUM
        defaultMMissionShouldNotBeFound("roundNum.lessThan=" + DEFAULT_ROUND_NUM);

        // Get all the mMissionList where roundNum less than or equals to UPDATED_ROUND_NUM
        defaultMMissionShouldBeFound("roundNum.lessThan=" + UPDATED_ROUND_NUM);
    }


    @Test
    @Transactional
    public void getAllMMissionsByMissionTypeIsEqualToSomething() throws Exception {
        // Initialize the database
        mMissionRepository.saveAndFlush(mMission);

        // Get all the mMissionList where missionType equals to DEFAULT_MISSION_TYPE
        defaultMMissionShouldBeFound("missionType.equals=" + DEFAULT_MISSION_TYPE);

        // Get all the mMissionList where missionType equals to UPDATED_MISSION_TYPE
        defaultMMissionShouldNotBeFound("missionType.equals=" + UPDATED_MISSION_TYPE);
    }

    @Test
    @Transactional
    public void getAllMMissionsByMissionTypeIsInShouldWork() throws Exception {
        // Initialize the database
        mMissionRepository.saveAndFlush(mMission);

        // Get all the mMissionList where missionType in DEFAULT_MISSION_TYPE or UPDATED_MISSION_TYPE
        defaultMMissionShouldBeFound("missionType.in=" + DEFAULT_MISSION_TYPE + "," + UPDATED_MISSION_TYPE);

        // Get all the mMissionList where missionType equals to UPDATED_MISSION_TYPE
        defaultMMissionShouldNotBeFound("missionType.in=" + UPDATED_MISSION_TYPE);
    }

    @Test
    @Transactional
    public void getAllMMissionsByMissionTypeIsNullOrNotNull() throws Exception {
        // Initialize the database
        mMissionRepository.saveAndFlush(mMission);

        // Get all the mMissionList where missionType is not null
        defaultMMissionShouldBeFound("missionType.specified=true");

        // Get all the mMissionList where missionType is null
        defaultMMissionShouldNotBeFound("missionType.specified=false");
    }

    @Test
    @Transactional
    public void getAllMMissionsByMissionTypeIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mMissionRepository.saveAndFlush(mMission);

        // Get all the mMissionList where missionType greater than or equals to DEFAULT_MISSION_TYPE
        defaultMMissionShouldBeFound("missionType.greaterOrEqualThan=" + DEFAULT_MISSION_TYPE);

        // Get all the mMissionList where missionType greater than or equals to UPDATED_MISSION_TYPE
        defaultMMissionShouldNotBeFound("missionType.greaterOrEqualThan=" + UPDATED_MISSION_TYPE);
    }

    @Test
    @Transactional
    public void getAllMMissionsByMissionTypeIsLessThanSomething() throws Exception {
        // Initialize the database
        mMissionRepository.saveAndFlush(mMission);

        // Get all the mMissionList where missionType less than or equals to DEFAULT_MISSION_TYPE
        defaultMMissionShouldNotBeFound("missionType.lessThan=" + DEFAULT_MISSION_TYPE);

        // Get all the mMissionList where missionType less than or equals to UPDATED_MISSION_TYPE
        defaultMMissionShouldBeFound("missionType.lessThan=" + UPDATED_MISSION_TYPE);
    }


    @Test
    @Transactional
    public void getAllMMissionsByAbsoluteIsEqualToSomething() throws Exception {
        // Initialize the database
        mMissionRepository.saveAndFlush(mMission);

        // Get all the mMissionList where absolute equals to DEFAULT_ABSOLUTE
        defaultMMissionShouldBeFound("absolute.equals=" + DEFAULT_ABSOLUTE);

        // Get all the mMissionList where absolute equals to UPDATED_ABSOLUTE
        defaultMMissionShouldNotBeFound("absolute.equals=" + UPDATED_ABSOLUTE);
    }

    @Test
    @Transactional
    public void getAllMMissionsByAbsoluteIsInShouldWork() throws Exception {
        // Initialize the database
        mMissionRepository.saveAndFlush(mMission);

        // Get all the mMissionList where absolute in DEFAULT_ABSOLUTE or UPDATED_ABSOLUTE
        defaultMMissionShouldBeFound("absolute.in=" + DEFAULT_ABSOLUTE + "," + UPDATED_ABSOLUTE);

        // Get all the mMissionList where absolute equals to UPDATED_ABSOLUTE
        defaultMMissionShouldNotBeFound("absolute.in=" + UPDATED_ABSOLUTE);
    }

    @Test
    @Transactional
    public void getAllMMissionsByAbsoluteIsNullOrNotNull() throws Exception {
        // Initialize the database
        mMissionRepository.saveAndFlush(mMission);

        // Get all the mMissionList where absolute is not null
        defaultMMissionShouldBeFound("absolute.specified=true");

        // Get all the mMissionList where absolute is null
        defaultMMissionShouldNotBeFound("absolute.specified=false");
    }

    @Test
    @Transactional
    public void getAllMMissionsByAbsoluteIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mMissionRepository.saveAndFlush(mMission);

        // Get all the mMissionList where absolute greater than or equals to DEFAULT_ABSOLUTE
        defaultMMissionShouldBeFound("absolute.greaterOrEqualThan=" + DEFAULT_ABSOLUTE);

        // Get all the mMissionList where absolute greater than or equals to UPDATED_ABSOLUTE
        defaultMMissionShouldNotBeFound("absolute.greaterOrEqualThan=" + UPDATED_ABSOLUTE);
    }

    @Test
    @Transactional
    public void getAllMMissionsByAbsoluteIsLessThanSomething() throws Exception {
        // Initialize the database
        mMissionRepository.saveAndFlush(mMission);

        // Get all the mMissionList where absolute less than or equals to DEFAULT_ABSOLUTE
        defaultMMissionShouldNotBeFound("absolute.lessThan=" + DEFAULT_ABSOLUTE);

        // Get all the mMissionList where absolute less than or equals to UPDATED_ABSOLUTE
        defaultMMissionShouldBeFound("absolute.lessThan=" + UPDATED_ABSOLUTE);
    }


    @Test
    @Transactional
    public void getAllMMissionsByParam1IsEqualToSomething() throws Exception {
        // Initialize the database
        mMissionRepository.saveAndFlush(mMission);

        // Get all the mMissionList where param1 equals to DEFAULT_PARAM_1
        defaultMMissionShouldBeFound("param1.equals=" + DEFAULT_PARAM_1);

        // Get all the mMissionList where param1 equals to UPDATED_PARAM_1
        defaultMMissionShouldNotBeFound("param1.equals=" + UPDATED_PARAM_1);
    }

    @Test
    @Transactional
    public void getAllMMissionsByParam1IsInShouldWork() throws Exception {
        // Initialize the database
        mMissionRepository.saveAndFlush(mMission);

        // Get all the mMissionList where param1 in DEFAULT_PARAM_1 or UPDATED_PARAM_1
        defaultMMissionShouldBeFound("param1.in=" + DEFAULT_PARAM_1 + "," + UPDATED_PARAM_1);

        // Get all the mMissionList where param1 equals to UPDATED_PARAM_1
        defaultMMissionShouldNotBeFound("param1.in=" + UPDATED_PARAM_1);
    }

    @Test
    @Transactional
    public void getAllMMissionsByParam1IsNullOrNotNull() throws Exception {
        // Initialize the database
        mMissionRepository.saveAndFlush(mMission);

        // Get all the mMissionList where param1 is not null
        defaultMMissionShouldBeFound("param1.specified=true");

        // Get all the mMissionList where param1 is null
        defaultMMissionShouldNotBeFound("param1.specified=false");
    }

    @Test
    @Transactional
    public void getAllMMissionsByParam1IsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mMissionRepository.saveAndFlush(mMission);

        // Get all the mMissionList where param1 greater than or equals to DEFAULT_PARAM_1
        defaultMMissionShouldBeFound("param1.greaterOrEqualThan=" + DEFAULT_PARAM_1);

        // Get all the mMissionList where param1 greater than or equals to UPDATED_PARAM_1
        defaultMMissionShouldNotBeFound("param1.greaterOrEqualThan=" + UPDATED_PARAM_1);
    }

    @Test
    @Transactional
    public void getAllMMissionsByParam1IsLessThanSomething() throws Exception {
        // Initialize the database
        mMissionRepository.saveAndFlush(mMission);

        // Get all the mMissionList where param1 less than or equals to DEFAULT_PARAM_1
        defaultMMissionShouldNotBeFound("param1.lessThan=" + DEFAULT_PARAM_1);

        // Get all the mMissionList where param1 less than or equals to UPDATED_PARAM_1
        defaultMMissionShouldBeFound("param1.lessThan=" + UPDATED_PARAM_1);
    }


    @Test
    @Transactional
    public void getAllMMissionsByParam2IsEqualToSomething() throws Exception {
        // Initialize the database
        mMissionRepository.saveAndFlush(mMission);

        // Get all the mMissionList where param2 equals to DEFAULT_PARAM_2
        defaultMMissionShouldBeFound("param2.equals=" + DEFAULT_PARAM_2);

        // Get all the mMissionList where param2 equals to UPDATED_PARAM_2
        defaultMMissionShouldNotBeFound("param2.equals=" + UPDATED_PARAM_2);
    }

    @Test
    @Transactional
    public void getAllMMissionsByParam2IsInShouldWork() throws Exception {
        // Initialize the database
        mMissionRepository.saveAndFlush(mMission);

        // Get all the mMissionList where param2 in DEFAULT_PARAM_2 or UPDATED_PARAM_2
        defaultMMissionShouldBeFound("param2.in=" + DEFAULT_PARAM_2 + "," + UPDATED_PARAM_2);

        // Get all the mMissionList where param2 equals to UPDATED_PARAM_2
        defaultMMissionShouldNotBeFound("param2.in=" + UPDATED_PARAM_2);
    }

    @Test
    @Transactional
    public void getAllMMissionsByParam2IsNullOrNotNull() throws Exception {
        // Initialize the database
        mMissionRepository.saveAndFlush(mMission);

        // Get all the mMissionList where param2 is not null
        defaultMMissionShouldBeFound("param2.specified=true");

        // Get all the mMissionList where param2 is null
        defaultMMissionShouldNotBeFound("param2.specified=false");
    }

    @Test
    @Transactional
    public void getAllMMissionsByParam2IsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mMissionRepository.saveAndFlush(mMission);

        // Get all the mMissionList where param2 greater than or equals to DEFAULT_PARAM_2
        defaultMMissionShouldBeFound("param2.greaterOrEqualThan=" + DEFAULT_PARAM_2);

        // Get all the mMissionList where param2 greater than or equals to UPDATED_PARAM_2
        defaultMMissionShouldNotBeFound("param2.greaterOrEqualThan=" + UPDATED_PARAM_2);
    }

    @Test
    @Transactional
    public void getAllMMissionsByParam2IsLessThanSomething() throws Exception {
        // Initialize the database
        mMissionRepository.saveAndFlush(mMission);

        // Get all the mMissionList where param2 less than or equals to DEFAULT_PARAM_2
        defaultMMissionShouldNotBeFound("param2.lessThan=" + DEFAULT_PARAM_2);

        // Get all the mMissionList where param2 less than or equals to UPDATED_PARAM_2
        defaultMMissionShouldBeFound("param2.lessThan=" + UPDATED_PARAM_2);
    }


    @Test
    @Transactional
    public void getAllMMissionsByParam3IsEqualToSomething() throws Exception {
        // Initialize the database
        mMissionRepository.saveAndFlush(mMission);

        // Get all the mMissionList where param3 equals to DEFAULT_PARAM_3
        defaultMMissionShouldBeFound("param3.equals=" + DEFAULT_PARAM_3);

        // Get all the mMissionList where param3 equals to UPDATED_PARAM_3
        defaultMMissionShouldNotBeFound("param3.equals=" + UPDATED_PARAM_3);
    }

    @Test
    @Transactional
    public void getAllMMissionsByParam3IsInShouldWork() throws Exception {
        // Initialize the database
        mMissionRepository.saveAndFlush(mMission);

        // Get all the mMissionList where param3 in DEFAULT_PARAM_3 or UPDATED_PARAM_3
        defaultMMissionShouldBeFound("param3.in=" + DEFAULT_PARAM_3 + "," + UPDATED_PARAM_3);

        // Get all the mMissionList where param3 equals to UPDATED_PARAM_3
        defaultMMissionShouldNotBeFound("param3.in=" + UPDATED_PARAM_3);
    }

    @Test
    @Transactional
    public void getAllMMissionsByParam3IsNullOrNotNull() throws Exception {
        // Initialize the database
        mMissionRepository.saveAndFlush(mMission);

        // Get all the mMissionList where param3 is not null
        defaultMMissionShouldBeFound("param3.specified=true");

        // Get all the mMissionList where param3 is null
        defaultMMissionShouldNotBeFound("param3.specified=false");
    }

    @Test
    @Transactional
    public void getAllMMissionsByParam3IsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mMissionRepository.saveAndFlush(mMission);

        // Get all the mMissionList where param3 greater than or equals to DEFAULT_PARAM_3
        defaultMMissionShouldBeFound("param3.greaterOrEqualThan=" + DEFAULT_PARAM_3);

        // Get all the mMissionList where param3 greater than or equals to UPDATED_PARAM_3
        defaultMMissionShouldNotBeFound("param3.greaterOrEqualThan=" + UPDATED_PARAM_3);
    }

    @Test
    @Transactional
    public void getAllMMissionsByParam3IsLessThanSomething() throws Exception {
        // Initialize the database
        mMissionRepository.saveAndFlush(mMission);

        // Get all the mMissionList where param3 less than or equals to DEFAULT_PARAM_3
        defaultMMissionShouldNotBeFound("param3.lessThan=" + DEFAULT_PARAM_3);

        // Get all the mMissionList where param3 less than or equals to UPDATED_PARAM_3
        defaultMMissionShouldBeFound("param3.lessThan=" + UPDATED_PARAM_3);
    }


    @Test
    @Transactional
    public void getAllMMissionsByParam4IsEqualToSomething() throws Exception {
        // Initialize the database
        mMissionRepository.saveAndFlush(mMission);

        // Get all the mMissionList where param4 equals to DEFAULT_PARAM_4
        defaultMMissionShouldBeFound("param4.equals=" + DEFAULT_PARAM_4);

        // Get all the mMissionList where param4 equals to UPDATED_PARAM_4
        defaultMMissionShouldNotBeFound("param4.equals=" + UPDATED_PARAM_4);
    }

    @Test
    @Transactional
    public void getAllMMissionsByParam4IsInShouldWork() throws Exception {
        // Initialize the database
        mMissionRepository.saveAndFlush(mMission);

        // Get all the mMissionList where param4 in DEFAULT_PARAM_4 or UPDATED_PARAM_4
        defaultMMissionShouldBeFound("param4.in=" + DEFAULT_PARAM_4 + "," + UPDATED_PARAM_4);

        // Get all the mMissionList where param4 equals to UPDATED_PARAM_4
        defaultMMissionShouldNotBeFound("param4.in=" + UPDATED_PARAM_4);
    }

    @Test
    @Transactional
    public void getAllMMissionsByParam4IsNullOrNotNull() throws Exception {
        // Initialize the database
        mMissionRepository.saveAndFlush(mMission);

        // Get all the mMissionList where param4 is not null
        defaultMMissionShouldBeFound("param4.specified=true");

        // Get all the mMissionList where param4 is null
        defaultMMissionShouldNotBeFound("param4.specified=false");
    }

    @Test
    @Transactional
    public void getAllMMissionsByParam4IsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mMissionRepository.saveAndFlush(mMission);

        // Get all the mMissionList where param4 greater than or equals to DEFAULT_PARAM_4
        defaultMMissionShouldBeFound("param4.greaterOrEqualThan=" + DEFAULT_PARAM_4);

        // Get all the mMissionList where param4 greater than or equals to UPDATED_PARAM_4
        defaultMMissionShouldNotBeFound("param4.greaterOrEqualThan=" + UPDATED_PARAM_4);
    }

    @Test
    @Transactional
    public void getAllMMissionsByParam4IsLessThanSomething() throws Exception {
        // Initialize the database
        mMissionRepository.saveAndFlush(mMission);

        // Get all the mMissionList where param4 less than or equals to DEFAULT_PARAM_4
        defaultMMissionShouldNotBeFound("param4.lessThan=" + DEFAULT_PARAM_4);

        // Get all the mMissionList where param4 less than or equals to UPDATED_PARAM_4
        defaultMMissionShouldBeFound("param4.lessThan=" + UPDATED_PARAM_4);
    }


    @Test
    @Transactional
    public void getAllMMissionsByParam5IsEqualToSomething() throws Exception {
        // Initialize the database
        mMissionRepository.saveAndFlush(mMission);

        // Get all the mMissionList where param5 equals to DEFAULT_PARAM_5
        defaultMMissionShouldBeFound("param5.equals=" + DEFAULT_PARAM_5);

        // Get all the mMissionList where param5 equals to UPDATED_PARAM_5
        defaultMMissionShouldNotBeFound("param5.equals=" + UPDATED_PARAM_5);
    }

    @Test
    @Transactional
    public void getAllMMissionsByParam5IsInShouldWork() throws Exception {
        // Initialize the database
        mMissionRepository.saveAndFlush(mMission);

        // Get all the mMissionList where param5 in DEFAULT_PARAM_5 or UPDATED_PARAM_5
        defaultMMissionShouldBeFound("param5.in=" + DEFAULT_PARAM_5 + "," + UPDATED_PARAM_5);

        // Get all the mMissionList where param5 equals to UPDATED_PARAM_5
        defaultMMissionShouldNotBeFound("param5.in=" + UPDATED_PARAM_5);
    }

    @Test
    @Transactional
    public void getAllMMissionsByParam5IsNullOrNotNull() throws Exception {
        // Initialize the database
        mMissionRepository.saveAndFlush(mMission);

        // Get all the mMissionList where param5 is not null
        defaultMMissionShouldBeFound("param5.specified=true");

        // Get all the mMissionList where param5 is null
        defaultMMissionShouldNotBeFound("param5.specified=false");
    }

    @Test
    @Transactional
    public void getAllMMissionsByParam5IsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mMissionRepository.saveAndFlush(mMission);

        // Get all the mMissionList where param5 greater than or equals to DEFAULT_PARAM_5
        defaultMMissionShouldBeFound("param5.greaterOrEqualThan=" + DEFAULT_PARAM_5);

        // Get all the mMissionList where param5 greater than or equals to UPDATED_PARAM_5
        defaultMMissionShouldNotBeFound("param5.greaterOrEqualThan=" + UPDATED_PARAM_5);
    }

    @Test
    @Transactional
    public void getAllMMissionsByParam5IsLessThanSomething() throws Exception {
        // Initialize the database
        mMissionRepository.saveAndFlush(mMission);

        // Get all the mMissionList where param5 less than or equals to DEFAULT_PARAM_5
        defaultMMissionShouldNotBeFound("param5.lessThan=" + DEFAULT_PARAM_5);

        // Get all the mMissionList where param5 less than or equals to UPDATED_PARAM_5
        defaultMMissionShouldBeFound("param5.lessThan=" + UPDATED_PARAM_5);
    }


    @Test
    @Transactional
    public void getAllMMissionsByTriggerIsEqualToSomething() throws Exception {
        // Initialize the database
        mMissionRepository.saveAndFlush(mMission);

        // Get all the mMissionList where trigger equals to DEFAULT_TRIGGER
        defaultMMissionShouldBeFound("trigger.equals=" + DEFAULT_TRIGGER);

        // Get all the mMissionList where trigger equals to UPDATED_TRIGGER
        defaultMMissionShouldNotBeFound("trigger.equals=" + UPDATED_TRIGGER);
    }

    @Test
    @Transactional
    public void getAllMMissionsByTriggerIsInShouldWork() throws Exception {
        // Initialize the database
        mMissionRepository.saveAndFlush(mMission);

        // Get all the mMissionList where trigger in DEFAULT_TRIGGER or UPDATED_TRIGGER
        defaultMMissionShouldBeFound("trigger.in=" + DEFAULT_TRIGGER + "," + UPDATED_TRIGGER);

        // Get all the mMissionList where trigger equals to UPDATED_TRIGGER
        defaultMMissionShouldNotBeFound("trigger.in=" + UPDATED_TRIGGER);
    }

    @Test
    @Transactional
    public void getAllMMissionsByTriggerIsNullOrNotNull() throws Exception {
        // Initialize the database
        mMissionRepository.saveAndFlush(mMission);

        // Get all the mMissionList where trigger is not null
        defaultMMissionShouldBeFound("trigger.specified=true");

        // Get all the mMissionList where trigger is null
        defaultMMissionShouldNotBeFound("trigger.specified=false");
    }

    @Test
    @Transactional
    public void getAllMMissionsByTriggerIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mMissionRepository.saveAndFlush(mMission);

        // Get all the mMissionList where trigger greater than or equals to DEFAULT_TRIGGER
        defaultMMissionShouldBeFound("trigger.greaterOrEqualThan=" + DEFAULT_TRIGGER);

        // Get all the mMissionList where trigger greater than or equals to UPDATED_TRIGGER
        defaultMMissionShouldNotBeFound("trigger.greaterOrEqualThan=" + UPDATED_TRIGGER);
    }

    @Test
    @Transactional
    public void getAllMMissionsByTriggerIsLessThanSomething() throws Exception {
        // Initialize the database
        mMissionRepository.saveAndFlush(mMission);

        // Get all the mMissionList where trigger less than or equals to DEFAULT_TRIGGER
        defaultMMissionShouldNotBeFound("trigger.lessThan=" + DEFAULT_TRIGGER);

        // Get all the mMissionList where trigger less than or equals to UPDATED_TRIGGER
        defaultMMissionShouldBeFound("trigger.lessThan=" + UPDATED_TRIGGER);
    }


    @Test
    @Transactional
    public void getAllMMissionsByTriggerConditionIsEqualToSomething() throws Exception {
        // Initialize the database
        mMissionRepository.saveAndFlush(mMission);

        // Get all the mMissionList where triggerCondition equals to DEFAULT_TRIGGER_CONDITION
        defaultMMissionShouldBeFound("triggerCondition.equals=" + DEFAULT_TRIGGER_CONDITION);

        // Get all the mMissionList where triggerCondition equals to UPDATED_TRIGGER_CONDITION
        defaultMMissionShouldNotBeFound("triggerCondition.equals=" + UPDATED_TRIGGER_CONDITION);
    }

    @Test
    @Transactional
    public void getAllMMissionsByTriggerConditionIsInShouldWork() throws Exception {
        // Initialize the database
        mMissionRepository.saveAndFlush(mMission);

        // Get all the mMissionList where triggerCondition in DEFAULT_TRIGGER_CONDITION or UPDATED_TRIGGER_CONDITION
        defaultMMissionShouldBeFound("triggerCondition.in=" + DEFAULT_TRIGGER_CONDITION + "," + UPDATED_TRIGGER_CONDITION);

        // Get all the mMissionList where triggerCondition equals to UPDATED_TRIGGER_CONDITION
        defaultMMissionShouldNotBeFound("triggerCondition.in=" + UPDATED_TRIGGER_CONDITION);
    }

    @Test
    @Transactional
    public void getAllMMissionsByTriggerConditionIsNullOrNotNull() throws Exception {
        // Initialize the database
        mMissionRepository.saveAndFlush(mMission);

        // Get all the mMissionList where triggerCondition is not null
        defaultMMissionShouldBeFound("triggerCondition.specified=true");

        // Get all the mMissionList where triggerCondition is null
        defaultMMissionShouldNotBeFound("triggerCondition.specified=false");
    }

    @Test
    @Transactional
    public void getAllMMissionsByTriggerConditionIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mMissionRepository.saveAndFlush(mMission);

        // Get all the mMissionList where triggerCondition greater than or equals to DEFAULT_TRIGGER_CONDITION
        defaultMMissionShouldBeFound("triggerCondition.greaterOrEqualThan=" + DEFAULT_TRIGGER_CONDITION);

        // Get all the mMissionList where triggerCondition greater than or equals to UPDATED_TRIGGER_CONDITION
        defaultMMissionShouldNotBeFound("triggerCondition.greaterOrEqualThan=" + UPDATED_TRIGGER_CONDITION);
    }

    @Test
    @Transactional
    public void getAllMMissionsByTriggerConditionIsLessThanSomething() throws Exception {
        // Initialize the database
        mMissionRepository.saveAndFlush(mMission);

        // Get all the mMissionList where triggerCondition less than or equals to DEFAULT_TRIGGER_CONDITION
        defaultMMissionShouldNotBeFound("triggerCondition.lessThan=" + DEFAULT_TRIGGER_CONDITION);

        // Get all the mMissionList where triggerCondition less than or equals to UPDATED_TRIGGER_CONDITION
        defaultMMissionShouldBeFound("triggerCondition.lessThan=" + UPDATED_TRIGGER_CONDITION);
    }


    @Test
    @Transactional
    public void getAllMMissionsByRewardIdIsEqualToSomething() throws Exception {
        // Initialize the database
        mMissionRepository.saveAndFlush(mMission);

        // Get all the mMissionList where rewardId equals to DEFAULT_REWARD_ID
        defaultMMissionShouldBeFound("rewardId.equals=" + DEFAULT_REWARD_ID);

        // Get all the mMissionList where rewardId equals to UPDATED_REWARD_ID
        defaultMMissionShouldNotBeFound("rewardId.equals=" + UPDATED_REWARD_ID);
    }

    @Test
    @Transactional
    public void getAllMMissionsByRewardIdIsInShouldWork() throws Exception {
        // Initialize the database
        mMissionRepository.saveAndFlush(mMission);

        // Get all the mMissionList where rewardId in DEFAULT_REWARD_ID or UPDATED_REWARD_ID
        defaultMMissionShouldBeFound("rewardId.in=" + DEFAULT_REWARD_ID + "," + UPDATED_REWARD_ID);

        // Get all the mMissionList where rewardId equals to UPDATED_REWARD_ID
        defaultMMissionShouldNotBeFound("rewardId.in=" + UPDATED_REWARD_ID);
    }

    @Test
    @Transactional
    public void getAllMMissionsByRewardIdIsNullOrNotNull() throws Exception {
        // Initialize the database
        mMissionRepository.saveAndFlush(mMission);

        // Get all the mMissionList where rewardId is not null
        defaultMMissionShouldBeFound("rewardId.specified=true");

        // Get all the mMissionList where rewardId is null
        defaultMMissionShouldNotBeFound("rewardId.specified=false");
    }

    @Test
    @Transactional
    public void getAllMMissionsByRewardIdIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mMissionRepository.saveAndFlush(mMission);

        // Get all the mMissionList where rewardId greater than or equals to DEFAULT_REWARD_ID
        defaultMMissionShouldBeFound("rewardId.greaterOrEqualThan=" + DEFAULT_REWARD_ID);

        // Get all the mMissionList where rewardId greater than or equals to UPDATED_REWARD_ID
        defaultMMissionShouldNotBeFound("rewardId.greaterOrEqualThan=" + UPDATED_REWARD_ID);
    }

    @Test
    @Transactional
    public void getAllMMissionsByRewardIdIsLessThanSomething() throws Exception {
        // Initialize the database
        mMissionRepository.saveAndFlush(mMission);

        // Get all the mMissionList where rewardId less than or equals to DEFAULT_REWARD_ID
        defaultMMissionShouldNotBeFound("rewardId.lessThan=" + DEFAULT_REWARD_ID);

        // Get all the mMissionList where rewardId less than or equals to UPDATED_REWARD_ID
        defaultMMissionShouldBeFound("rewardId.lessThan=" + UPDATED_REWARD_ID);
    }


    @Test
    @Transactional
    public void getAllMMissionsByStartAtIsEqualToSomething() throws Exception {
        // Initialize the database
        mMissionRepository.saveAndFlush(mMission);

        // Get all the mMissionList where startAt equals to DEFAULT_START_AT
        defaultMMissionShouldBeFound("startAt.equals=" + DEFAULT_START_AT);

        // Get all the mMissionList where startAt equals to UPDATED_START_AT
        defaultMMissionShouldNotBeFound("startAt.equals=" + UPDATED_START_AT);
    }

    @Test
    @Transactional
    public void getAllMMissionsByStartAtIsInShouldWork() throws Exception {
        // Initialize the database
        mMissionRepository.saveAndFlush(mMission);

        // Get all the mMissionList where startAt in DEFAULT_START_AT or UPDATED_START_AT
        defaultMMissionShouldBeFound("startAt.in=" + DEFAULT_START_AT + "," + UPDATED_START_AT);

        // Get all the mMissionList where startAt equals to UPDATED_START_AT
        defaultMMissionShouldNotBeFound("startAt.in=" + UPDATED_START_AT);
    }

    @Test
    @Transactional
    public void getAllMMissionsByStartAtIsNullOrNotNull() throws Exception {
        // Initialize the database
        mMissionRepository.saveAndFlush(mMission);

        // Get all the mMissionList where startAt is not null
        defaultMMissionShouldBeFound("startAt.specified=true");

        // Get all the mMissionList where startAt is null
        defaultMMissionShouldNotBeFound("startAt.specified=false");
    }

    @Test
    @Transactional
    public void getAllMMissionsByStartAtIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mMissionRepository.saveAndFlush(mMission);

        // Get all the mMissionList where startAt greater than or equals to DEFAULT_START_AT
        defaultMMissionShouldBeFound("startAt.greaterOrEqualThan=" + DEFAULT_START_AT);

        // Get all the mMissionList where startAt greater than or equals to UPDATED_START_AT
        defaultMMissionShouldNotBeFound("startAt.greaterOrEqualThan=" + UPDATED_START_AT);
    }

    @Test
    @Transactional
    public void getAllMMissionsByStartAtIsLessThanSomething() throws Exception {
        // Initialize the database
        mMissionRepository.saveAndFlush(mMission);

        // Get all the mMissionList where startAt less than or equals to DEFAULT_START_AT
        defaultMMissionShouldNotBeFound("startAt.lessThan=" + DEFAULT_START_AT);

        // Get all the mMissionList where startAt less than or equals to UPDATED_START_AT
        defaultMMissionShouldBeFound("startAt.lessThan=" + UPDATED_START_AT);
    }


    @Test
    @Transactional
    public void getAllMMissionsByEndAtIsEqualToSomething() throws Exception {
        // Initialize the database
        mMissionRepository.saveAndFlush(mMission);

        // Get all the mMissionList where endAt equals to DEFAULT_END_AT
        defaultMMissionShouldBeFound("endAt.equals=" + DEFAULT_END_AT);

        // Get all the mMissionList where endAt equals to UPDATED_END_AT
        defaultMMissionShouldNotBeFound("endAt.equals=" + UPDATED_END_AT);
    }

    @Test
    @Transactional
    public void getAllMMissionsByEndAtIsInShouldWork() throws Exception {
        // Initialize the database
        mMissionRepository.saveAndFlush(mMission);

        // Get all the mMissionList where endAt in DEFAULT_END_AT or UPDATED_END_AT
        defaultMMissionShouldBeFound("endAt.in=" + DEFAULT_END_AT + "," + UPDATED_END_AT);

        // Get all the mMissionList where endAt equals to UPDATED_END_AT
        defaultMMissionShouldNotBeFound("endAt.in=" + UPDATED_END_AT);
    }

    @Test
    @Transactional
    public void getAllMMissionsByEndAtIsNullOrNotNull() throws Exception {
        // Initialize the database
        mMissionRepository.saveAndFlush(mMission);

        // Get all the mMissionList where endAt is not null
        defaultMMissionShouldBeFound("endAt.specified=true");

        // Get all the mMissionList where endAt is null
        defaultMMissionShouldNotBeFound("endAt.specified=false");
    }

    @Test
    @Transactional
    public void getAllMMissionsByEndAtIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mMissionRepository.saveAndFlush(mMission);

        // Get all the mMissionList where endAt greater than or equals to DEFAULT_END_AT
        defaultMMissionShouldBeFound("endAt.greaterOrEqualThan=" + DEFAULT_END_AT);

        // Get all the mMissionList where endAt greater than or equals to UPDATED_END_AT
        defaultMMissionShouldNotBeFound("endAt.greaterOrEqualThan=" + UPDATED_END_AT);
    }

    @Test
    @Transactional
    public void getAllMMissionsByEndAtIsLessThanSomething() throws Exception {
        // Initialize the database
        mMissionRepository.saveAndFlush(mMission);

        // Get all the mMissionList where endAt less than or equals to DEFAULT_END_AT
        defaultMMissionShouldNotBeFound("endAt.lessThan=" + DEFAULT_END_AT);

        // Get all the mMissionList where endAt less than or equals to UPDATED_END_AT
        defaultMMissionShouldBeFound("endAt.lessThan=" + UPDATED_END_AT);
    }


    @Test
    @Transactional
    public void getAllMMissionsByLinkIsEqualToSomething() throws Exception {
        // Initialize the database
        mMissionRepository.saveAndFlush(mMission);

        // Get all the mMissionList where link equals to DEFAULT_LINK
        defaultMMissionShouldBeFound("link.equals=" + DEFAULT_LINK);

        // Get all the mMissionList where link equals to UPDATED_LINK
        defaultMMissionShouldNotBeFound("link.equals=" + UPDATED_LINK);
    }

    @Test
    @Transactional
    public void getAllMMissionsByLinkIsInShouldWork() throws Exception {
        // Initialize the database
        mMissionRepository.saveAndFlush(mMission);

        // Get all the mMissionList where link in DEFAULT_LINK or UPDATED_LINK
        defaultMMissionShouldBeFound("link.in=" + DEFAULT_LINK + "," + UPDATED_LINK);

        // Get all the mMissionList where link equals to UPDATED_LINK
        defaultMMissionShouldNotBeFound("link.in=" + UPDATED_LINK);
    }

    @Test
    @Transactional
    public void getAllMMissionsByLinkIsNullOrNotNull() throws Exception {
        // Initialize the database
        mMissionRepository.saveAndFlush(mMission);

        // Get all the mMissionList where link is not null
        defaultMMissionShouldBeFound("link.specified=true");

        // Get all the mMissionList where link is null
        defaultMMissionShouldNotBeFound("link.specified=false");
    }

    @Test
    @Transactional
    public void getAllMMissionsByLinkIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mMissionRepository.saveAndFlush(mMission);

        // Get all the mMissionList where link greater than or equals to DEFAULT_LINK
        defaultMMissionShouldBeFound("link.greaterOrEqualThan=" + DEFAULT_LINK);

        // Get all the mMissionList where link greater than or equals to UPDATED_LINK
        defaultMMissionShouldNotBeFound("link.greaterOrEqualThan=" + UPDATED_LINK);
    }

    @Test
    @Transactional
    public void getAllMMissionsByLinkIsLessThanSomething() throws Exception {
        // Initialize the database
        mMissionRepository.saveAndFlush(mMission);

        // Get all the mMissionList where link less than or equals to DEFAULT_LINK
        defaultMMissionShouldNotBeFound("link.lessThan=" + DEFAULT_LINK);

        // Get all the mMissionList where link less than or equals to UPDATED_LINK
        defaultMMissionShouldBeFound("link.lessThan=" + UPDATED_LINK);
    }


    @Test
    @Transactional
    public void getAllMMissionsByPickupIsEqualToSomething() throws Exception {
        // Initialize the database
        mMissionRepository.saveAndFlush(mMission);

        // Get all the mMissionList where pickup equals to DEFAULT_PICKUP
        defaultMMissionShouldBeFound("pickup.equals=" + DEFAULT_PICKUP);

        // Get all the mMissionList where pickup equals to UPDATED_PICKUP
        defaultMMissionShouldNotBeFound("pickup.equals=" + UPDATED_PICKUP);
    }

    @Test
    @Transactional
    public void getAllMMissionsByPickupIsInShouldWork() throws Exception {
        // Initialize the database
        mMissionRepository.saveAndFlush(mMission);

        // Get all the mMissionList where pickup in DEFAULT_PICKUP or UPDATED_PICKUP
        defaultMMissionShouldBeFound("pickup.in=" + DEFAULT_PICKUP + "," + UPDATED_PICKUP);

        // Get all the mMissionList where pickup equals to UPDATED_PICKUP
        defaultMMissionShouldNotBeFound("pickup.in=" + UPDATED_PICKUP);
    }

    @Test
    @Transactional
    public void getAllMMissionsByPickupIsNullOrNotNull() throws Exception {
        // Initialize the database
        mMissionRepository.saveAndFlush(mMission);

        // Get all the mMissionList where pickup is not null
        defaultMMissionShouldBeFound("pickup.specified=true");

        // Get all the mMissionList where pickup is null
        defaultMMissionShouldNotBeFound("pickup.specified=false");
    }

    @Test
    @Transactional
    public void getAllMMissionsByPickupIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mMissionRepository.saveAndFlush(mMission);

        // Get all the mMissionList where pickup greater than or equals to DEFAULT_PICKUP
        defaultMMissionShouldBeFound("pickup.greaterOrEqualThan=" + DEFAULT_PICKUP);

        // Get all the mMissionList where pickup greater than or equals to UPDATED_PICKUP
        defaultMMissionShouldNotBeFound("pickup.greaterOrEqualThan=" + UPDATED_PICKUP);
    }

    @Test
    @Transactional
    public void getAllMMissionsByPickupIsLessThanSomething() throws Exception {
        // Initialize the database
        mMissionRepository.saveAndFlush(mMission);

        // Get all the mMissionList where pickup less than or equals to DEFAULT_PICKUP
        defaultMMissionShouldNotBeFound("pickup.lessThan=" + DEFAULT_PICKUP);

        // Get all the mMissionList where pickup less than or equals to UPDATED_PICKUP
        defaultMMissionShouldBeFound("pickup.lessThan=" + UPDATED_PICKUP);
    }


    @Test
    @Transactional
    public void getAllMMissionsByOrderNumIsEqualToSomething() throws Exception {
        // Initialize the database
        mMissionRepository.saveAndFlush(mMission);

        // Get all the mMissionList where orderNum equals to DEFAULT_ORDER_NUM
        defaultMMissionShouldBeFound("orderNum.equals=" + DEFAULT_ORDER_NUM);

        // Get all the mMissionList where orderNum equals to UPDATED_ORDER_NUM
        defaultMMissionShouldNotBeFound("orderNum.equals=" + UPDATED_ORDER_NUM);
    }

    @Test
    @Transactional
    public void getAllMMissionsByOrderNumIsInShouldWork() throws Exception {
        // Initialize the database
        mMissionRepository.saveAndFlush(mMission);

        // Get all the mMissionList where orderNum in DEFAULT_ORDER_NUM or UPDATED_ORDER_NUM
        defaultMMissionShouldBeFound("orderNum.in=" + DEFAULT_ORDER_NUM + "," + UPDATED_ORDER_NUM);

        // Get all the mMissionList where orderNum equals to UPDATED_ORDER_NUM
        defaultMMissionShouldNotBeFound("orderNum.in=" + UPDATED_ORDER_NUM);
    }

    @Test
    @Transactional
    public void getAllMMissionsByOrderNumIsNullOrNotNull() throws Exception {
        // Initialize the database
        mMissionRepository.saveAndFlush(mMission);

        // Get all the mMissionList where orderNum is not null
        defaultMMissionShouldBeFound("orderNum.specified=true");

        // Get all the mMissionList where orderNum is null
        defaultMMissionShouldNotBeFound("orderNum.specified=false");
    }

    @Test
    @Transactional
    public void getAllMMissionsByOrderNumIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mMissionRepository.saveAndFlush(mMission);

        // Get all the mMissionList where orderNum greater than or equals to DEFAULT_ORDER_NUM
        defaultMMissionShouldBeFound("orderNum.greaterOrEqualThan=" + DEFAULT_ORDER_NUM);

        // Get all the mMissionList where orderNum greater than or equals to UPDATED_ORDER_NUM
        defaultMMissionShouldNotBeFound("orderNum.greaterOrEqualThan=" + UPDATED_ORDER_NUM);
    }

    @Test
    @Transactional
    public void getAllMMissionsByOrderNumIsLessThanSomething() throws Exception {
        // Initialize the database
        mMissionRepository.saveAndFlush(mMission);

        // Get all the mMissionList where orderNum less than or equals to DEFAULT_ORDER_NUM
        defaultMMissionShouldNotBeFound("orderNum.lessThan=" + DEFAULT_ORDER_NUM);

        // Get all the mMissionList where orderNum less than or equals to UPDATED_ORDER_NUM
        defaultMMissionShouldBeFound("orderNum.lessThan=" + UPDATED_ORDER_NUM);
    }


    @Test
    @Transactional
    public void getAllMMissionsByMmissionrewardIsEqualToSomething() throws Exception {
        // Get already existing entity
        MMissionReward mmissionreward = mMission.getMmissionreward();
        mMissionRepository.saveAndFlush(mMission);
        Long mmissionrewardId = mmissionreward.getId();

        // Get all the mMissionList where mmissionreward equals to mmissionrewardId
        defaultMMissionShouldBeFound("mmissionrewardId.equals=" + mmissionrewardId);

        // Get all the mMissionList where mmissionreward equals to mmissionrewardId + 1
        defaultMMissionShouldNotBeFound("mmissionrewardId.equals=" + (mmissionrewardId + 1));
    }


    @Test
    @Transactional
    public void getAllMMissionsByMAchievementIsEqualToSomething() throws Exception {
        // Initialize the database
        MAchievement mAchievement = MAchievementResourceIT.createEntity(em);
        em.persist(mAchievement);
        em.flush();
        mMission.addMAchievement(mAchievement);
        mMissionRepository.saveAndFlush(mMission);
        Long mAchievementId = mAchievement.getId();

        // Get all the mMissionList where mAchievement equals to mAchievementId
        defaultMMissionShouldBeFound("mAchievementId.equals=" + mAchievementId);

        // Get all the mMissionList where mAchievement equals to mAchievementId + 1
        defaultMMissionShouldNotBeFound("mAchievementId.equals=" + (mAchievementId + 1));
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultMMissionShouldBeFound(String filter) throws Exception {
        restMMissionMockMvc.perform(get("/api/m-missions?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(mMission.getId().intValue())))
            .andExpect(jsonPath("$.[*].term").value(hasItem(DEFAULT_TERM)))
            .andExpect(jsonPath("$.[*].roundNum").value(hasItem(DEFAULT_ROUND_NUM)))
            .andExpect(jsonPath("$.[*].title").value(hasItem(DEFAULT_TITLE.toString())))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION.toString())))
            .andExpect(jsonPath("$.[*].missionType").value(hasItem(DEFAULT_MISSION_TYPE)))
            .andExpect(jsonPath("$.[*].absolute").value(hasItem(DEFAULT_ABSOLUTE)))
            .andExpect(jsonPath("$.[*].param1").value(hasItem(DEFAULT_PARAM_1)))
            .andExpect(jsonPath("$.[*].param2").value(hasItem(DEFAULT_PARAM_2)))
            .andExpect(jsonPath("$.[*].param3").value(hasItem(DEFAULT_PARAM_3)))
            .andExpect(jsonPath("$.[*].param4").value(hasItem(DEFAULT_PARAM_4)))
            .andExpect(jsonPath("$.[*].param5").value(hasItem(DEFAULT_PARAM_5)))
            .andExpect(jsonPath("$.[*].trigger").value(hasItem(DEFAULT_TRIGGER)))
            .andExpect(jsonPath("$.[*].triggerCondition").value(hasItem(DEFAULT_TRIGGER_CONDITION)))
            .andExpect(jsonPath("$.[*].rewardId").value(hasItem(DEFAULT_REWARD_ID)))
            .andExpect(jsonPath("$.[*].startAt").value(hasItem(DEFAULT_START_AT)))
            .andExpect(jsonPath("$.[*].endAt").value(hasItem(DEFAULT_END_AT)))
            .andExpect(jsonPath("$.[*].link").value(hasItem(DEFAULT_LINK)))
            .andExpect(jsonPath("$.[*].sceneTransitionParam").value(hasItem(DEFAULT_SCENE_TRANSITION_PARAM.toString())))
            .andExpect(jsonPath("$.[*].pickup").value(hasItem(DEFAULT_PICKUP)))
            .andExpect(jsonPath("$.[*].orderNum").value(hasItem(DEFAULT_ORDER_NUM)));

        // Check, that the count call also returns 1
        restMMissionMockMvc.perform(get("/api/m-missions/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultMMissionShouldNotBeFound(String filter) throws Exception {
        restMMissionMockMvc.perform(get("/api/m-missions?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restMMissionMockMvc.perform(get("/api/m-missions/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("0"));
    }


    @Test
    @Transactional
    public void getNonExistingMMission() throws Exception {
        // Get the mMission
        restMMissionMockMvc.perform(get("/api/m-missions/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateMMission() throws Exception {
        // Initialize the database
        mMissionRepository.saveAndFlush(mMission);

        int databaseSizeBeforeUpdate = mMissionRepository.findAll().size();

        // Update the mMission
        MMission updatedMMission = mMissionRepository.findById(mMission.getId()).get();
        // Disconnect from session so that the updates on updatedMMission are not directly saved in db
        em.detach(updatedMMission);
        updatedMMission
            .term(UPDATED_TERM)
            .roundNum(UPDATED_ROUND_NUM)
            .title(UPDATED_TITLE)
            .description(UPDATED_DESCRIPTION)
            .missionType(UPDATED_MISSION_TYPE)
            .absolute(UPDATED_ABSOLUTE)
            .param1(UPDATED_PARAM_1)
            .param2(UPDATED_PARAM_2)
            .param3(UPDATED_PARAM_3)
            .param4(UPDATED_PARAM_4)
            .param5(UPDATED_PARAM_5)
            .trigger(UPDATED_TRIGGER)
            .triggerCondition(UPDATED_TRIGGER_CONDITION)
            .rewardId(UPDATED_REWARD_ID)
            .startAt(UPDATED_START_AT)
            .endAt(UPDATED_END_AT)
            .link(UPDATED_LINK)
            .sceneTransitionParam(UPDATED_SCENE_TRANSITION_PARAM)
            .pickup(UPDATED_PICKUP)
            .orderNum(UPDATED_ORDER_NUM);
        MMissionDTO mMissionDTO = mMissionMapper.toDto(updatedMMission);

        restMMissionMockMvc.perform(put("/api/m-missions")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mMissionDTO)))
            .andExpect(status().isOk());

        // Validate the MMission in the database
        List<MMission> mMissionList = mMissionRepository.findAll();
        assertThat(mMissionList).hasSize(databaseSizeBeforeUpdate);
        MMission testMMission = mMissionList.get(mMissionList.size() - 1);
        assertThat(testMMission.getTerm()).isEqualTo(UPDATED_TERM);
        assertThat(testMMission.getRoundNum()).isEqualTo(UPDATED_ROUND_NUM);
        assertThat(testMMission.getTitle()).isEqualTo(UPDATED_TITLE);
        assertThat(testMMission.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
        assertThat(testMMission.getMissionType()).isEqualTo(UPDATED_MISSION_TYPE);
        assertThat(testMMission.getAbsolute()).isEqualTo(UPDATED_ABSOLUTE);
        assertThat(testMMission.getParam1()).isEqualTo(UPDATED_PARAM_1);
        assertThat(testMMission.getParam2()).isEqualTo(UPDATED_PARAM_2);
        assertThat(testMMission.getParam3()).isEqualTo(UPDATED_PARAM_3);
        assertThat(testMMission.getParam4()).isEqualTo(UPDATED_PARAM_4);
        assertThat(testMMission.getParam5()).isEqualTo(UPDATED_PARAM_5);
        assertThat(testMMission.getTrigger()).isEqualTo(UPDATED_TRIGGER);
        assertThat(testMMission.getTriggerCondition()).isEqualTo(UPDATED_TRIGGER_CONDITION);
        assertThat(testMMission.getRewardId()).isEqualTo(UPDATED_REWARD_ID);
        assertThat(testMMission.getStartAt()).isEqualTo(UPDATED_START_AT);
        assertThat(testMMission.getEndAt()).isEqualTo(UPDATED_END_AT);
        assertThat(testMMission.getLink()).isEqualTo(UPDATED_LINK);
        assertThat(testMMission.getSceneTransitionParam()).isEqualTo(UPDATED_SCENE_TRANSITION_PARAM);
        assertThat(testMMission.getPickup()).isEqualTo(UPDATED_PICKUP);
        assertThat(testMMission.getOrderNum()).isEqualTo(UPDATED_ORDER_NUM);
    }

    @Test
    @Transactional
    public void updateNonExistingMMission() throws Exception {
        int databaseSizeBeforeUpdate = mMissionRepository.findAll().size();

        // Create the MMission
        MMissionDTO mMissionDTO = mMissionMapper.toDto(mMission);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restMMissionMockMvc.perform(put("/api/m-missions")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mMissionDTO)))
            .andExpect(status().isBadRequest());

        // Validate the MMission in the database
        List<MMission> mMissionList = mMissionRepository.findAll();
        assertThat(mMissionList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteMMission() throws Exception {
        // Initialize the database
        mMissionRepository.saveAndFlush(mMission);

        int databaseSizeBeforeDelete = mMissionRepository.findAll().size();

        // Delete the mMission
        restMMissionMockMvc.perform(delete("/api/m-missions/{id}", mMission.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database is empty
        List<MMission> mMissionList = mMissionRepository.findAll();
        assertThat(mMissionList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(MMission.class);
        MMission mMission1 = new MMission();
        mMission1.setId(1L);
        MMission mMission2 = new MMission();
        mMission2.setId(mMission1.getId());
        assertThat(mMission1).isEqualTo(mMission2);
        mMission2.setId(2L);
        assertThat(mMission1).isNotEqualTo(mMission2);
        mMission1.setId(null);
        assertThat(mMission1).isNotEqualTo(mMission2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(MMissionDTO.class);
        MMissionDTO mMissionDTO1 = new MMissionDTO();
        mMissionDTO1.setId(1L);
        MMissionDTO mMissionDTO2 = new MMissionDTO();
        assertThat(mMissionDTO1).isNotEqualTo(mMissionDTO2);
        mMissionDTO2.setId(mMissionDTO1.getId());
        assertThat(mMissionDTO1).isEqualTo(mMissionDTO2);
        mMissionDTO2.setId(2L);
        assertThat(mMissionDTO1).isNotEqualTo(mMissionDTO2);
        mMissionDTO1.setId(null);
        assertThat(mMissionDTO1).isNotEqualTo(mMissionDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(mMissionMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(mMissionMapper.fromId(null)).isNull();
    }
}
