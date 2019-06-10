package io.shm.tsubasa.web.rest;

import io.shm.tsubasa.TsubasaApp;
import io.shm.tsubasa.domain.MEncountersCommandBranch;
import io.shm.tsubasa.repository.MEncountersCommandBranchRepository;
import io.shm.tsubasa.service.MEncountersCommandBranchService;
import io.shm.tsubasa.service.dto.MEncountersCommandBranchDTO;
import io.shm.tsubasa.service.mapper.MEncountersCommandBranchMapper;
import io.shm.tsubasa.web.rest.errors.ExceptionTranslator;
import io.shm.tsubasa.service.dto.MEncountersCommandBranchCriteria;
import io.shm.tsubasa.service.MEncountersCommandBranchQueryService;

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
 * Integration tests for the {@Link MEncountersCommandBranchResource} REST controller.
 */
@SpringBootTest(classes = TsubasaApp.class)
public class MEncountersCommandBranchResourceIT {

    private static final Integer DEFAULT_BALL_FLOAT_CONDITION = 1;
    private static final Integer UPDATED_BALL_FLOAT_CONDITION = 2;

    private static final Integer DEFAULT_CONDITION = 1;
    private static final Integer UPDATED_CONDITION = 2;

    private static final Integer DEFAULT_ENCOUNTERS_TYPE = 1;
    private static final Integer UPDATED_ENCOUNTERS_TYPE = 2;

    private static final Integer DEFAULT_IS_SUCCESS = 1;
    private static final Integer UPDATED_IS_SUCCESS = 2;

    private static final Integer DEFAULT_COMMAND_TYPE = 1;
    private static final Integer UPDATED_COMMAND_TYPE = 2;

    private static final Integer DEFAULT_SUCCESS_RATE = 1;
    private static final Integer UPDATED_SUCCESS_RATE = 2;

    private static final Integer DEFAULT_LOOSE_BALL_RATE = 1;
    private static final Integer UPDATED_LOOSE_BALL_RATE = 2;

    private static final Integer DEFAULT_TOUCH_LIGHTLY_RATE = 1;
    private static final Integer UPDATED_TOUCH_LIGHTLY_RATE = 2;

    private static final Integer DEFAULT_POST_RATE = 1;
    private static final Integer UPDATED_POST_RATE = 2;

    @Autowired
    private MEncountersCommandBranchRepository mEncountersCommandBranchRepository;

    @Autowired
    private MEncountersCommandBranchMapper mEncountersCommandBranchMapper;

    @Autowired
    private MEncountersCommandBranchService mEncountersCommandBranchService;

    @Autowired
    private MEncountersCommandBranchQueryService mEncountersCommandBranchQueryService;

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

    private MockMvc restMEncountersCommandBranchMockMvc;

    private MEncountersCommandBranch mEncountersCommandBranch;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final MEncountersCommandBranchResource mEncountersCommandBranchResource = new MEncountersCommandBranchResource(mEncountersCommandBranchService, mEncountersCommandBranchQueryService);
        this.restMEncountersCommandBranchMockMvc = MockMvcBuilders.standaloneSetup(mEncountersCommandBranchResource)
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
    public static MEncountersCommandBranch createEntity(EntityManager em) {
        MEncountersCommandBranch mEncountersCommandBranch = new MEncountersCommandBranch()
            .ballFloatCondition(DEFAULT_BALL_FLOAT_CONDITION)
            .condition(DEFAULT_CONDITION)
            .encountersType(DEFAULT_ENCOUNTERS_TYPE)
            .isSuccess(DEFAULT_IS_SUCCESS)
            .commandType(DEFAULT_COMMAND_TYPE)
            .successRate(DEFAULT_SUCCESS_RATE)
            .looseBallRate(DEFAULT_LOOSE_BALL_RATE)
            .touchLightlyRate(DEFAULT_TOUCH_LIGHTLY_RATE)
            .postRate(DEFAULT_POST_RATE);
        return mEncountersCommandBranch;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static MEncountersCommandBranch createUpdatedEntity(EntityManager em) {
        MEncountersCommandBranch mEncountersCommandBranch = new MEncountersCommandBranch()
            .ballFloatCondition(UPDATED_BALL_FLOAT_CONDITION)
            .condition(UPDATED_CONDITION)
            .encountersType(UPDATED_ENCOUNTERS_TYPE)
            .isSuccess(UPDATED_IS_SUCCESS)
            .commandType(UPDATED_COMMAND_TYPE)
            .successRate(UPDATED_SUCCESS_RATE)
            .looseBallRate(UPDATED_LOOSE_BALL_RATE)
            .touchLightlyRate(UPDATED_TOUCH_LIGHTLY_RATE)
            .postRate(UPDATED_POST_RATE);
        return mEncountersCommandBranch;
    }

    @BeforeEach
    public void initTest() {
        mEncountersCommandBranch = createEntity(em);
    }

    @Test
    @Transactional
    public void createMEncountersCommandBranch() throws Exception {
        int databaseSizeBeforeCreate = mEncountersCommandBranchRepository.findAll().size();

        // Create the MEncountersCommandBranch
        MEncountersCommandBranchDTO mEncountersCommandBranchDTO = mEncountersCommandBranchMapper.toDto(mEncountersCommandBranch);
        restMEncountersCommandBranchMockMvc.perform(post("/api/m-encounters-command-branches")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mEncountersCommandBranchDTO)))
            .andExpect(status().isCreated());

        // Validate the MEncountersCommandBranch in the database
        List<MEncountersCommandBranch> mEncountersCommandBranchList = mEncountersCommandBranchRepository.findAll();
        assertThat(mEncountersCommandBranchList).hasSize(databaseSizeBeforeCreate + 1);
        MEncountersCommandBranch testMEncountersCommandBranch = mEncountersCommandBranchList.get(mEncountersCommandBranchList.size() - 1);
        assertThat(testMEncountersCommandBranch.getBallFloatCondition()).isEqualTo(DEFAULT_BALL_FLOAT_CONDITION);
        assertThat(testMEncountersCommandBranch.getCondition()).isEqualTo(DEFAULT_CONDITION);
        assertThat(testMEncountersCommandBranch.getEncountersType()).isEqualTo(DEFAULT_ENCOUNTERS_TYPE);
        assertThat(testMEncountersCommandBranch.getIsSuccess()).isEqualTo(DEFAULT_IS_SUCCESS);
        assertThat(testMEncountersCommandBranch.getCommandType()).isEqualTo(DEFAULT_COMMAND_TYPE);
        assertThat(testMEncountersCommandBranch.getSuccessRate()).isEqualTo(DEFAULT_SUCCESS_RATE);
        assertThat(testMEncountersCommandBranch.getLooseBallRate()).isEqualTo(DEFAULT_LOOSE_BALL_RATE);
        assertThat(testMEncountersCommandBranch.getTouchLightlyRate()).isEqualTo(DEFAULT_TOUCH_LIGHTLY_RATE);
        assertThat(testMEncountersCommandBranch.getPostRate()).isEqualTo(DEFAULT_POST_RATE);
    }

    @Test
    @Transactional
    public void createMEncountersCommandBranchWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = mEncountersCommandBranchRepository.findAll().size();

        // Create the MEncountersCommandBranch with an existing ID
        mEncountersCommandBranch.setId(1L);
        MEncountersCommandBranchDTO mEncountersCommandBranchDTO = mEncountersCommandBranchMapper.toDto(mEncountersCommandBranch);

        // An entity with an existing ID cannot be created, so this API call must fail
        restMEncountersCommandBranchMockMvc.perform(post("/api/m-encounters-command-branches")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mEncountersCommandBranchDTO)))
            .andExpect(status().isBadRequest());

        // Validate the MEncountersCommandBranch in the database
        List<MEncountersCommandBranch> mEncountersCommandBranchList = mEncountersCommandBranchRepository.findAll();
        assertThat(mEncountersCommandBranchList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkBallFloatConditionIsRequired() throws Exception {
        int databaseSizeBeforeTest = mEncountersCommandBranchRepository.findAll().size();
        // set the field null
        mEncountersCommandBranch.setBallFloatCondition(null);

        // Create the MEncountersCommandBranch, which fails.
        MEncountersCommandBranchDTO mEncountersCommandBranchDTO = mEncountersCommandBranchMapper.toDto(mEncountersCommandBranch);

        restMEncountersCommandBranchMockMvc.perform(post("/api/m-encounters-command-branches")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mEncountersCommandBranchDTO)))
            .andExpect(status().isBadRequest());

        List<MEncountersCommandBranch> mEncountersCommandBranchList = mEncountersCommandBranchRepository.findAll();
        assertThat(mEncountersCommandBranchList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkConditionIsRequired() throws Exception {
        int databaseSizeBeforeTest = mEncountersCommandBranchRepository.findAll().size();
        // set the field null
        mEncountersCommandBranch.setCondition(null);

        // Create the MEncountersCommandBranch, which fails.
        MEncountersCommandBranchDTO mEncountersCommandBranchDTO = mEncountersCommandBranchMapper.toDto(mEncountersCommandBranch);

        restMEncountersCommandBranchMockMvc.perform(post("/api/m-encounters-command-branches")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mEncountersCommandBranchDTO)))
            .andExpect(status().isBadRequest());

        List<MEncountersCommandBranch> mEncountersCommandBranchList = mEncountersCommandBranchRepository.findAll();
        assertThat(mEncountersCommandBranchList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkEncountersTypeIsRequired() throws Exception {
        int databaseSizeBeforeTest = mEncountersCommandBranchRepository.findAll().size();
        // set the field null
        mEncountersCommandBranch.setEncountersType(null);

        // Create the MEncountersCommandBranch, which fails.
        MEncountersCommandBranchDTO mEncountersCommandBranchDTO = mEncountersCommandBranchMapper.toDto(mEncountersCommandBranch);

        restMEncountersCommandBranchMockMvc.perform(post("/api/m-encounters-command-branches")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mEncountersCommandBranchDTO)))
            .andExpect(status().isBadRequest());

        List<MEncountersCommandBranch> mEncountersCommandBranchList = mEncountersCommandBranchRepository.findAll();
        assertThat(mEncountersCommandBranchList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkIsSuccessIsRequired() throws Exception {
        int databaseSizeBeforeTest = mEncountersCommandBranchRepository.findAll().size();
        // set the field null
        mEncountersCommandBranch.setIsSuccess(null);

        // Create the MEncountersCommandBranch, which fails.
        MEncountersCommandBranchDTO mEncountersCommandBranchDTO = mEncountersCommandBranchMapper.toDto(mEncountersCommandBranch);

        restMEncountersCommandBranchMockMvc.perform(post("/api/m-encounters-command-branches")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mEncountersCommandBranchDTO)))
            .andExpect(status().isBadRequest());

        List<MEncountersCommandBranch> mEncountersCommandBranchList = mEncountersCommandBranchRepository.findAll();
        assertThat(mEncountersCommandBranchList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkCommandTypeIsRequired() throws Exception {
        int databaseSizeBeforeTest = mEncountersCommandBranchRepository.findAll().size();
        // set the field null
        mEncountersCommandBranch.setCommandType(null);

        // Create the MEncountersCommandBranch, which fails.
        MEncountersCommandBranchDTO mEncountersCommandBranchDTO = mEncountersCommandBranchMapper.toDto(mEncountersCommandBranch);

        restMEncountersCommandBranchMockMvc.perform(post("/api/m-encounters-command-branches")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mEncountersCommandBranchDTO)))
            .andExpect(status().isBadRequest());

        List<MEncountersCommandBranch> mEncountersCommandBranchList = mEncountersCommandBranchRepository.findAll();
        assertThat(mEncountersCommandBranchList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkSuccessRateIsRequired() throws Exception {
        int databaseSizeBeforeTest = mEncountersCommandBranchRepository.findAll().size();
        // set the field null
        mEncountersCommandBranch.setSuccessRate(null);

        // Create the MEncountersCommandBranch, which fails.
        MEncountersCommandBranchDTO mEncountersCommandBranchDTO = mEncountersCommandBranchMapper.toDto(mEncountersCommandBranch);

        restMEncountersCommandBranchMockMvc.perform(post("/api/m-encounters-command-branches")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mEncountersCommandBranchDTO)))
            .andExpect(status().isBadRequest());

        List<MEncountersCommandBranch> mEncountersCommandBranchList = mEncountersCommandBranchRepository.findAll();
        assertThat(mEncountersCommandBranchList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkLooseBallRateIsRequired() throws Exception {
        int databaseSizeBeforeTest = mEncountersCommandBranchRepository.findAll().size();
        // set the field null
        mEncountersCommandBranch.setLooseBallRate(null);

        // Create the MEncountersCommandBranch, which fails.
        MEncountersCommandBranchDTO mEncountersCommandBranchDTO = mEncountersCommandBranchMapper.toDto(mEncountersCommandBranch);

        restMEncountersCommandBranchMockMvc.perform(post("/api/m-encounters-command-branches")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mEncountersCommandBranchDTO)))
            .andExpect(status().isBadRequest());

        List<MEncountersCommandBranch> mEncountersCommandBranchList = mEncountersCommandBranchRepository.findAll();
        assertThat(mEncountersCommandBranchList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkTouchLightlyRateIsRequired() throws Exception {
        int databaseSizeBeforeTest = mEncountersCommandBranchRepository.findAll().size();
        // set the field null
        mEncountersCommandBranch.setTouchLightlyRate(null);

        // Create the MEncountersCommandBranch, which fails.
        MEncountersCommandBranchDTO mEncountersCommandBranchDTO = mEncountersCommandBranchMapper.toDto(mEncountersCommandBranch);

        restMEncountersCommandBranchMockMvc.perform(post("/api/m-encounters-command-branches")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mEncountersCommandBranchDTO)))
            .andExpect(status().isBadRequest());

        List<MEncountersCommandBranch> mEncountersCommandBranchList = mEncountersCommandBranchRepository.findAll();
        assertThat(mEncountersCommandBranchList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkPostRateIsRequired() throws Exception {
        int databaseSizeBeforeTest = mEncountersCommandBranchRepository.findAll().size();
        // set the field null
        mEncountersCommandBranch.setPostRate(null);

        // Create the MEncountersCommandBranch, which fails.
        MEncountersCommandBranchDTO mEncountersCommandBranchDTO = mEncountersCommandBranchMapper.toDto(mEncountersCommandBranch);

        restMEncountersCommandBranchMockMvc.perform(post("/api/m-encounters-command-branches")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mEncountersCommandBranchDTO)))
            .andExpect(status().isBadRequest());

        List<MEncountersCommandBranch> mEncountersCommandBranchList = mEncountersCommandBranchRepository.findAll();
        assertThat(mEncountersCommandBranchList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllMEncountersCommandBranches() throws Exception {
        // Initialize the database
        mEncountersCommandBranchRepository.saveAndFlush(mEncountersCommandBranch);

        // Get all the mEncountersCommandBranchList
        restMEncountersCommandBranchMockMvc.perform(get("/api/m-encounters-command-branches?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(mEncountersCommandBranch.getId().intValue())))
            .andExpect(jsonPath("$.[*].ballFloatCondition").value(hasItem(DEFAULT_BALL_FLOAT_CONDITION)))
            .andExpect(jsonPath("$.[*].condition").value(hasItem(DEFAULT_CONDITION)))
            .andExpect(jsonPath("$.[*].encountersType").value(hasItem(DEFAULT_ENCOUNTERS_TYPE)))
            .andExpect(jsonPath("$.[*].isSuccess").value(hasItem(DEFAULT_IS_SUCCESS)))
            .andExpect(jsonPath("$.[*].commandType").value(hasItem(DEFAULT_COMMAND_TYPE)))
            .andExpect(jsonPath("$.[*].successRate").value(hasItem(DEFAULT_SUCCESS_RATE)))
            .andExpect(jsonPath("$.[*].looseBallRate").value(hasItem(DEFAULT_LOOSE_BALL_RATE)))
            .andExpect(jsonPath("$.[*].touchLightlyRate").value(hasItem(DEFAULT_TOUCH_LIGHTLY_RATE)))
            .andExpect(jsonPath("$.[*].postRate").value(hasItem(DEFAULT_POST_RATE)));
    }
    
    @Test
    @Transactional
    public void getMEncountersCommandBranch() throws Exception {
        // Initialize the database
        mEncountersCommandBranchRepository.saveAndFlush(mEncountersCommandBranch);

        // Get the mEncountersCommandBranch
        restMEncountersCommandBranchMockMvc.perform(get("/api/m-encounters-command-branches/{id}", mEncountersCommandBranch.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(mEncountersCommandBranch.getId().intValue()))
            .andExpect(jsonPath("$.ballFloatCondition").value(DEFAULT_BALL_FLOAT_CONDITION))
            .andExpect(jsonPath("$.condition").value(DEFAULT_CONDITION))
            .andExpect(jsonPath("$.encountersType").value(DEFAULT_ENCOUNTERS_TYPE))
            .andExpect(jsonPath("$.isSuccess").value(DEFAULT_IS_SUCCESS))
            .andExpect(jsonPath("$.commandType").value(DEFAULT_COMMAND_TYPE))
            .andExpect(jsonPath("$.successRate").value(DEFAULT_SUCCESS_RATE))
            .andExpect(jsonPath("$.looseBallRate").value(DEFAULT_LOOSE_BALL_RATE))
            .andExpect(jsonPath("$.touchLightlyRate").value(DEFAULT_TOUCH_LIGHTLY_RATE))
            .andExpect(jsonPath("$.postRate").value(DEFAULT_POST_RATE));
    }

    @Test
    @Transactional
    public void getAllMEncountersCommandBranchesByBallFloatConditionIsEqualToSomething() throws Exception {
        // Initialize the database
        mEncountersCommandBranchRepository.saveAndFlush(mEncountersCommandBranch);

        // Get all the mEncountersCommandBranchList where ballFloatCondition equals to DEFAULT_BALL_FLOAT_CONDITION
        defaultMEncountersCommandBranchShouldBeFound("ballFloatCondition.equals=" + DEFAULT_BALL_FLOAT_CONDITION);

        // Get all the mEncountersCommandBranchList where ballFloatCondition equals to UPDATED_BALL_FLOAT_CONDITION
        defaultMEncountersCommandBranchShouldNotBeFound("ballFloatCondition.equals=" + UPDATED_BALL_FLOAT_CONDITION);
    }

    @Test
    @Transactional
    public void getAllMEncountersCommandBranchesByBallFloatConditionIsInShouldWork() throws Exception {
        // Initialize the database
        mEncountersCommandBranchRepository.saveAndFlush(mEncountersCommandBranch);

        // Get all the mEncountersCommandBranchList where ballFloatCondition in DEFAULT_BALL_FLOAT_CONDITION or UPDATED_BALL_FLOAT_CONDITION
        defaultMEncountersCommandBranchShouldBeFound("ballFloatCondition.in=" + DEFAULT_BALL_FLOAT_CONDITION + "," + UPDATED_BALL_FLOAT_CONDITION);

        // Get all the mEncountersCommandBranchList where ballFloatCondition equals to UPDATED_BALL_FLOAT_CONDITION
        defaultMEncountersCommandBranchShouldNotBeFound("ballFloatCondition.in=" + UPDATED_BALL_FLOAT_CONDITION);
    }

    @Test
    @Transactional
    public void getAllMEncountersCommandBranchesByBallFloatConditionIsNullOrNotNull() throws Exception {
        // Initialize the database
        mEncountersCommandBranchRepository.saveAndFlush(mEncountersCommandBranch);

        // Get all the mEncountersCommandBranchList where ballFloatCondition is not null
        defaultMEncountersCommandBranchShouldBeFound("ballFloatCondition.specified=true");

        // Get all the mEncountersCommandBranchList where ballFloatCondition is null
        defaultMEncountersCommandBranchShouldNotBeFound("ballFloatCondition.specified=false");
    }

    @Test
    @Transactional
    public void getAllMEncountersCommandBranchesByBallFloatConditionIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mEncountersCommandBranchRepository.saveAndFlush(mEncountersCommandBranch);

        // Get all the mEncountersCommandBranchList where ballFloatCondition greater than or equals to DEFAULT_BALL_FLOAT_CONDITION
        defaultMEncountersCommandBranchShouldBeFound("ballFloatCondition.greaterOrEqualThan=" + DEFAULT_BALL_FLOAT_CONDITION);

        // Get all the mEncountersCommandBranchList where ballFloatCondition greater than or equals to UPDATED_BALL_FLOAT_CONDITION
        defaultMEncountersCommandBranchShouldNotBeFound("ballFloatCondition.greaterOrEqualThan=" + UPDATED_BALL_FLOAT_CONDITION);
    }

    @Test
    @Transactional
    public void getAllMEncountersCommandBranchesByBallFloatConditionIsLessThanSomething() throws Exception {
        // Initialize the database
        mEncountersCommandBranchRepository.saveAndFlush(mEncountersCommandBranch);

        // Get all the mEncountersCommandBranchList where ballFloatCondition less than or equals to DEFAULT_BALL_FLOAT_CONDITION
        defaultMEncountersCommandBranchShouldNotBeFound("ballFloatCondition.lessThan=" + DEFAULT_BALL_FLOAT_CONDITION);

        // Get all the mEncountersCommandBranchList where ballFloatCondition less than or equals to UPDATED_BALL_FLOAT_CONDITION
        defaultMEncountersCommandBranchShouldBeFound("ballFloatCondition.lessThan=" + UPDATED_BALL_FLOAT_CONDITION);
    }


    @Test
    @Transactional
    public void getAllMEncountersCommandBranchesByConditionIsEqualToSomething() throws Exception {
        // Initialize the database
        mEncountersCommandBranchRepository.saveAndFlush(mEncountersCommandBranch);

        // Get all the mEncountersCommandBranchList where condition equals to DEFAULT_CONDITION
        defaultMEncountersCommandBranchShouldBeFound("condition.equals=" + DEFAULT_CONDITION);

        // Get all the mEncountersCommandBranchList where condition equals to UPDATED_CONDITION
        defaultMEncountersCommandBranchShouldNotBeFound("condition.equals=" + UPDATED_CONDITION);
    }

    @Test
    @Transactional
    public void getAllMEncountersCommandBranchesByConditionIsInShouldWork() throws Exception {
        // Initialize the database
        mEncountersCommandBranchRepository.saveAndFlush(mEncountersCommandBranch);

        // Get all the mEncountersCommandBranchList where condition in DEFAULT_CONDITION or UPDATED_CONDITION
        defaultMEncountersCommandBranchShouldBeFound("condition.in=" + DEFAULT_CONDITION + "," + UPDATED_CONDITION);

        // Get all the mEncountersCommandBranchList where condition equals to UPDATED_CONDITION
        defaultMEncountersCommandBranchShouldNotBeFound("condition.in=" + UPDATED_CONDITION);
    }

    @Test
    @Transactional
    public void getAllMEncountersCommandBranchesByConditionIsNullOrNotNull() throws Exception {
        // Initialize the database
        mEncountersCommandBranchRepository.saveAndFlush(mEncountersCommandBranch);

        // Get all the mEncountersCommandBranchList where condition is not null
        defaultMEncountersCommandBranchShouldBeFound("condition.specified=true");

        // Get all the mEncountersCommandBranchList where condition is null
        defaultMEncountersCommandBranchShouldNotBeFound("condition.specified=false");
    }

    @Test
    @Transactional
    public void getAllMEncountersCommandBranchesByConditionIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mEncountersCommandBranchRepository.saveAndFlush(mEncountersCommandBranch);

        // Get all the mEncountersCommandBranchList where condition greater than or equals to DEFAULT_CONDITION
        defaultMEncountersCommandBranchShouldBeFound("condition.greaterOrEqualThan=" + DEFAULT_CONDITION);

        // Get all the mEncountersCommandBranchList where condition greater than or equals to UPDATED_CONDITION
        defaultMEncountersCommandBranchShouldNotBeFound("condition.greaterOrEqualThan=" + UPDATED_CONDITION);
    }

    @Test
    @Transactional
    public void getAllMEncountersCommandBranchesByConditionIsLessThanSomething() throws Exception {
        // Initialize the database
        mEncountersCommandBranchRepository.saveAndFlush(mEncountersCommandBranch);

        // Get all the mEncountersCommandBranchList where condition less than or equals to DEFAULT_CONDITION
        defaultMEncountersCommandBranchShouldNotBeFound("condition.lessThan=" + DEFAULT_CONDITION);

        // Get all the mEncountersCommandBranchList where condition less than or equals to UPDATED_CONDITION
        defaultMEncountersCommandBranchShouldBeFound("condition.lessThan=" + UPDATED_CONDITION);
    }


    @Test
    @Transactional
    public void getAllMEncountersCommandBranchesByEncountersTypeIsEqualToSomething() throws Exception {
        // Initialize the database
        mEncountersCommandBranchRepository.saveAndFlush(mEncountersCommandBranch);

        // Get all the mEncountersCommandBranchList where encountersType equals to DEFAULT_ENCOUNTERS_TYPE
        defaultMEncountersCommandBranchShouldBeFound("encountersType.equals=" + DEFAULT_ENCOUNTERS_TYPE);

        // Get all the mEncountersCommandBranchList where encountersType equals to UPDATED_ENCOUNTERS_TYPE
        defaultMEncountersCommandBranchShouldNotBeFound("encountersType.equals=" + UPDATED_ENCOUNTERS_TYPE);
    }

    @Test
    @Transactional
    public void getAllMEncountersCommandBranchesByEncountersTypeIsInShouldWork() throws Exception {
        // Initialize the database
        mEncountersCommandBranchRepository.saveAndFlush(mEncountersCommandBranch);

        // Get all the mEncountersCommandBranchList where encountersType in DEFAULT_ENCOUNTERS_TYPE or UPDATED_ENCOUNTERS_TYPE
        defaultMEncountersCommandBranchShouldBeFound("encountersType.in=" + DEFAULT_ENCOUNTERS_TYPE + "," + UPDATED_ENCOUNTERS_TYPE);

        // Get all the mEncountersCommandBranchList where encountersType equals to UPDATED_ENCOUNTERS_TYPE
        defaultMEncountersCommandBranchShouldNotBeFound("encountersType.in=" + UPDATED_ENCOUNTERS_TYPE);
    }

    @Test
    @Transactional
    public void getAllMEncountersCommandBranchesByEncountersTypeIsNullOrNotNull() throws Exception {
        // Initialize the database
        mEncountersCommandBranchRepository.saveAndFlush(mEncountersCommandBranch);

        // Get all the mEncountersCommandBranchList where encountersType is not null
        defaultMEncountersCommandBranchShouldBeFound("encountersType.specified=true");

        // Get all the mEncountersCommandBranchList where encountersType is null
        defaultMEncountersCommandBranchShouldNotBeFound("encountersType.specified=false");
    }

    @Test
    @Transactional
    public void getAllMEncountersCommandBranchesByEncountersTypeIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mEncountersCommandBranchRepository.saveAndFlush(mEncountersCommandBranch);

        // Get all the mEncountersCommandBranchList where encountersType greater than or equals to DEFAULT_ENCOUNTERS_TYPE
        defaultMEncountersCommandBranchShouldBeFound("encountersType.greaterOrEqualThan=" + DEFAULT_ENCOUNTERS_TYPE);

        // Get all the mEncountersCommandBranchList where encountersType greater than or equals to UPDATED_ENCOUNTERS_TYPE
        defaultMEncountersCommandBranchShouldNotBeFound("encountersType.greaterOrEqualThan=" + UPDATED_ENCOUNTERS_TYPE);
    }

    @Test
    @Transactional
    public void getAllMEncountersCommandBranchesByEncountersTypeIsLessThanSomething() throws Exception {
        // Initialize the database
        mEncountersCommandBranchRepository.saveAndFlush(mEncountersCommandBranch);

        // Get all the mEncountersCommandBranchList where encountersType less than or equals to DEFAULT_ENCOUNTERS_TYPE
        defaultMEncountersCommandBranchShouldNotBeFound("encountersType.lessThan=" + DEFAULT_ENCOUNTERS_TYPE);

        // Get all the mEncountersCommandBranchList where encountersType less than or equals to UPDATED_ENCOUNTERS_TYPE
        defaultMEncountersCommandBranchShouldBeFound("encountersType.lessThan=" + UPDATED_ENCOUNTERS_TYPE);
    }


    @Test
    @Transactional
    public void getAllMEncountersCommandBranchesByIsSuccessIsEqualToSomething() throws Exception {
        // Initialize the database
        mEncountersCommandBranchRepository.saveAndFlush(mEncountersCommandBranch);

        // Get all the mEncountersCommandBranchList where isSuccess equals to DEFAULT_IS_SUCCESS
        defaultMEncountersCommandBranchShouldBeFound("isSuccess.equals=" + DEFAULT_IS_SUCCESS);

        // Get all the mEncountersCommandBranchList where isSuccess equals to UPDATED_IS_SUCCESS
        defaultMEncountersCommandBranchShouldNotBeFound("isSuccess.equals=" + UPDATED_IS_SUCCESS);
    }

    @Test
    @Transactional
    public void getAllMEncountersCommandBranchesByIsSuccessIsInShouldWork() throws Exception {
        // Initialize the database
        mEncountersCommandBranchRepository.saveAndFlush(mEncountersCommandBranch);

        // Get all the mEncountersCommandBranchList where isSuccess in DEFAULT_IS_SUCCESS or UPDATED_IS_SUCCESS
        defaultMEncountersCommandBranchShouldBeFound("isSuccess.in=" + DEFAULT_IS_SUCCESS + "," + UPDATED_IS_SUCCESS);

        // Get all the mEncountersCommandBranchList where isSuccess equals to UPDATED_IS_SUCCESS
        defaultMEncountersCommandBranchShouldNotBeFound("isSuccess.in=" + UPDATED_IS_SUCCESS);
    }

    @Test
    @Transactional
    public void getAllMEncountersCommandBranchesByIsSuccessIsNullOrNotNull() throws Exception {
        // Initialize the database
        mEncountersCommandBranchRepository.saveAndFlush(mEncountersCommandBranch);

        // Get all the mEncountersCommandBranchList where isSuccess is not null
        defaultMEncountersCommandBranchShouldBeFound("isSuccess.specified=true");

        // Get all the mEncountersCommandBranchList where isSuccess is null
        defaultMEncountersCommandBranchShouldNotBeFound("isSuccess.specified=false");
    }

    @Test
    @Transactional
    public void getAllMEncountersCommandBranchesByIsSuccessIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mEncountersCommandBranchRepository.saveAndFlush(mEncountersCommandBranch);

        // Get all the mEncountersCommandBranchList where isSuccess greater than or equals to DEFAULT_IS_SUCCESS
        defaultMEncountersCommandBranchShouldBeFound("isSuccess.greaterOrEqualThan=" + DEFAULT_IS_SUCCESS);

        // Get all the mEncountersCommandBranchList where isSuccess greater than or equals to UPDATED_IS_SUCCESS
        defaultMEncountersCommandBranchShouldNotBeFound("isSuccess.greaterOrEqualThan=" + UPDATED_IS_SUCCESS);
    }

    @Test
    @Transactional
    public void getAllMEncountersCommandBranchesByIsSuccessIsLessThanSomething() throws Exception {
        // Initialize the database
        mEncountersCommandBranchRepository.saveAndFlush(mEncountersCommandBranch);

        // Get all the mEncountersCommandBranchList where isSuccess less than or equals to DEFAULT_IS_SUCCESS
        defaultMEncountersCommandBranchShouldNotBeFound("isSuccess.lessThan=" + DEFAULT_IS_SUCCESS);

        // Get all the mEncountersCommandBranchList where isSuccess less than or equals to UPDATED_IS_SUCCESS
        defaultMEncountersCommandBranchShouldBeFound("isSuccess.lessThan=" + UPDATED_IS_SUCCESS);
    }


    @Test
    @Transactional
    public void getAllMEncountersCommandBranchesByCommandTypeIsEqualToSomething() throws Exception {
        // Initialize the database
        mEncountersCommandBranchRepository.saveAndFlush(mEncountersCommandBranch);

        // Get all the mEncountersCommandBranchList where commandType equals to DEFAULT_COMMAND_TYPE
        defaultMEncountersCommandBranchShouldBeFound("commandType.equals=" + DEFAULT_COMMAND_TYPE);

        // Get all the mEncountersCommandBranchList where commandType equals to UPDATED_COMMAND_TYPE
        defaultMEncountersCommandBranchShouldNotBeFound("commandType.equals=" + UPDATED_COMMAND_TYPE);
    }

    @Test
    @Transactional
    public void getAllMEncountersCommandBranchesByCommandTypeIsInShouldWork() throws Exception {
        // Initialize the database
        mEncountersCommandBranchRepository.saveAndFlush(mEncountersCommandBranch);

        // Get all the mEncountersCommandBranchList where commandType in DEFAULT_COMMAND_TYPE or UPDATED_COMMAND_TYPE
        defaultMEncountersCommandBranchShouldBeFound("commandType.in=" + DEFAULT_COMMAND_TYPE + "," + UPDATED_COMMAND_TYPE);

        // Get all the mEncountersCommandBranchList where commandType equals to UPDATED_COMMAND_TYPE
        defaultMEncountersCommandBranchShouldNotBeFound("commandType.in=" + UPDATED_COMMAND_TYPE);
    }

    @Test
    @Transactional
    public void getAllMEncountersCommandBranchesByCommandTypeIsNullOrNotNull() throws Exception {
        // Initialize the database
        mEncountersCommandBranchRepository.saveAndFlush(mEncountersCommandBranch);

        // Get all the mEncountersCommandBranchList where commandType is not null
        defaultMEncountersCommandBranchShouldBeFound("commandType.specified=true");

        // Get all the mEncountersCommandBranchList where commandType is null
        defaultMEncountersCommandBranchShouldNotBeFound("commandType.specified=false");
    }

    @Test
    @Transactional
    public void getAllMEncountersCommandBranchesByCommandTypeIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mEncountersCommandBranchRepository.saveAndFlush(mEncountersCommandBranch);

        // Get all the mEncountersCommandBranchList where commandType greater than or equals to DEFAULT_COMMAND_TYPE
        defaultMEncountersCommandBranchShouldBeFound("commandType.greaterOrEqualThan=" + DEFAULT_COMMAND_TYPE);

        // Get all the mEncountersCommandBranchList where commandType greater than or equals to UPDATED_COMMAND_TYPE
        defaultMEncountersCommandBranchShouldNotBeFound("commandType.greaterOrEqualThan=" + UPDATED_COMMAND_TYPE);
    }

    @Test
    @Transactional
    public void getAllMEncountersCommandBranchesByCommandTypeIsLessThanSomething() throws Exception {
        // Initialize the database
        mEncountersCommandBranchRepository.saveAndFlush(mEncountersCommandBranch);

        // Get all the mEncountersCommandBranchList where commandType less than or equals to DEFAULT_COMMAND_TYPE
        defaultMEncountersCommandBranchShouldNotBeFound("commandType.lessThan=" + DEFAULT_COMMAND_TYPE);

        // Get all the mEncountersCommandBranchList where commandType less than or equals to UPDATED_COMMAND_TYPE
        defaultMEncountersCommandBranchShouldBeFound("commandType.lessThan=" + UPDATED_COMMAND_TYPE);
    }


    @Test
    @Transactional
    public void getAllMEncountersCommandBranchesBySuccessRateIsEqualToSomething() throws Exception {
        // Initialize the database
        mEncountersCommandBranchRepository.saveAndFlush(mEncountersCommandBranch);

        // Get all the mEncountersCommandBranchList where successRate equals to DEFAULT_SUCCESS_RATE
        defaultMEncountersCommandBranchShouldBeFound("successRate.equals=" + DEFAULT_SUCCESS_RATE);

        // Get all the mEncountersCommandBranchList where successRate equals to UPDATED_SUCCESS_RATE
        defaultMEncountersCommandBranchShouldNotBeFound("successRate.equals=" + UPDATED_SUCCESS_RATE);
    }

    @Test
    @Transactional
    public void getAllMEncountersCommandBranchesBySuccessRateIsInShouldWork() throws Exception {
        // Initialize the database
        mEncountersCommandBranchRepository.saveAndFlush(mEncountersCommandBranch);

        // Get all the mEncountersCommandBranchList where successRate in DEFAULT_SUCCESS_RATE or UPDATED_SUCCESS_RATE
        defaultMEncountersCommandBranchShouldBeFound("successRate.in=" + DEFAULT_SUCCESS_RATE + "," + UPDATED_SUCCESS_RATE);

        // Get all the mEncountersCommandBranchList where successRate equals to UPDATED_SUCCESS_RATE
        defaultMEncountersCommandBranchShouldNotBeFound("successRate.in=" + UPDATED_SUCCESS_RATE);
    }

    @Test
    @Transactional
    public void getAllMEncountersCommandBranchesBySuccessRateIsNullOrNotNull() throws Exception {
        // Initialize the database
        mEncountersCommandBranchRepository.saveAndFlush(mEncountersCommandBranch);

        // Get all the mEncountersCommandBranchList where successRate is not null
        defaultMEncountersCommandBranchShouldBeFound("successRate.specified=true");

        // Get all the mEncountersCommandBranchList where successRate is null
        defaultMEncountersCommandBranchShouldNotBeFound("successRate.specified=false");
    }

    @Test
    @Transactional
    public void getAllMEncountersCommandBranchesBySuccessRateIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mEncountersCommandBranchRepository.saveAndFlush(mEncountersCommandBranch);

        // Get all the mEncountersCommandBranchList where successRate greater than or equals to DEFAULT_SUCCESS_RATE
        defaultMEncountersCommandBranchShouldBeFound("successRate.greaterOrEqualThan=" + DEFAULT_SUCCESS_RATE);

        // Get all the mEncountersCommandBranchList where successRate greater than or equals to UPDATED_SUCCESS_RATE
        defaultMEncountersCommandBranchShouldNotBeFound("successRate.greaterOrEqualThan=" + UPDATED_SUCCESS_RATE);
    }

    @Test
    @Transactional
    public void getAllMEncountersCommandBranchesBySuccessRateIsLessThanSomething() throws Exception {
        // Initialize the database
        mEncountersCommandBranchRepository.saveAndFlush(mEncountersCommandBranch);

        // Get all the mEncountersCommandBranchList where successRate less than or equals to DEFAULT_SUCCESS_RATE
        defaultMEncountersCommandBranchShouldNotBeFound("successRate.lessThan=" + DEFAULT_SUCCESS_RATE);

        // Get all the mEncountersCommandBranchList where successRate less than or equals to UPDATED_SUCCESS_RATE
        defaultMEncountersCommandBranchShouldBeFound("successRate.lessThan=" + UPDATED_SUCCESS_RATE);
    }


    @Test
    @Transactional
    public void getAllMEncountersCommandBranchesByLooseBallRateIsEqualToSomething() throws Exception {
        // Initialize the database
        mEncountersCommandBranchRepository.saveAndFlush(mEncountersCommandBranch);

        // Get all the mEncountersCommandBranchList where looseBallRate equals to DEFAULT_LOOSE_BALL_RATE
        defaultMEncountersCommandBranchShouldBeFound("looseBallRate.equals=" + DEFAULT_LOOSE_BALL_RATE);

        // Get all the mEncountersCommandBranchList where looseBallRate equals to UPDATED_LOOSE_BALL_RATE
        defaultMEncountersCommandBranchShouldNotBeFound("looseBallRate.equals=" + UPDATED_LOOSE_BALL_RATE);
    }

    @Test
    @Transactional
    public void getAllMEncountersCommandBranchesByLooseBallRateIsInShouldWork() throws Exception {
        // Initialize the database
        mEncountersCommandBranchRepository.saveAndFlush(mEncountersCommandBranch);

        // Get all the mEncountersCommandBranchList where looseBallRate in DEFAULT_LOOSE_BALL_RATE or UPDATED_LOOSE_BALL_RATE
        defaultMEncountersCommandBranchShouldBeFound("looseBallRate.in=" + DEFAULT_LOOSE_BALL_RATE + "," + UPDATED_LOOSE_BALL_RATE);

        // Get all the mEncountersCommandBranchList where looseBallRate equals to UPDATED_LOOSE_BALL_RATE
        defaultMEncountersCommandBranchShouldNotBeFound("looseBallRate.in=" + UPDATED_LOOSE_BALL_RATE);
    }

    @Test
    @Transactional
    public void getAllMEncountersCommandBranchesByLooseBallRateIsNullOrNotNull() throws Exception {
        // Initialize the database
        mEncountersCommandBranchRepository.saveAndFlush(mEncountersCommandBranch);

        // Get all the mEncountersCommandBranchList where looseBallRate is not null
        defaultMEncountersCommandBranchShouldBeFound("looseBallRate.specified=true");

        // Get all the mEncountersCommandBranchList where looseBallRate is null
        defaultMEncountersCommandBranchShouldNotBeFound("looseBallRate.specified=false");
    }

    @Test
    @Transactional
    public void getAllMEncountersCommandBranchesByLooseBallRateIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mEncountersCommandBranchRepository.saveAndFlush(mEncountersCommandBranch);

        // Get all the mEncountersCommandBranchList where looseBallRate greater than or equals to DEFAULT_LOOSE_BALL_RATE
        defaultMEncountersCommandBranchShouldBeFound("looseBallRate.greaterOrEqualThan=" + DEFAULT_LOOSE_BALL_RATE);

        // Get all the mEncountersCommandBranchList where looseBallRate greater than or equals to UPDATED_LOOSE_BALL_RATE
        defaultMEncountersCommandBranchShouldNotBeFound("looseBallRate.greaterOrEqualThan=" + UPDATED_LOOSE_BALL_RATE);
    }

    @Test
    @Transactional
    public void getAllMEncountersCommandBranchesByLooseBallRateIsLessThanSomething() throws Exception {
        // Initialize the database
        mEncountersCommandBranchRepository.saveAndFlush(mEncountersCommandBranch);

        // Get all the mEncountersCommandBranchList where looseBallRate less than or equals to DEFAULT_LOOSE_BALL_RATE
        defaultMEncountersCommandBranchShouldNotBeFound("looseBallRate.lessThan=" + DEFAULT_LOOSE_BALL_RATE);

        // Get all the mEncountersCommandBranchList where looseBallRate less than or equals to UPDATED_LOOSE_BALL_RATE
        defaultMEncountersCommandBranchShouldBeFound("looseBallRate.lessThan=" + UPDATED_LOOSE_BALL_RATE);
    }


    @Test
    @Transactional
    public void getAllMEncountersCommandBranchesByTouchLightlyRateIsEqualToSomething() throws Exception {
        // Initialize the database
        mEncountersCommandBranchRepository.saveAndFlush(mEncountersCommandBranch);

        // Get all the mEncountersCommandBranchList where touchLightlyRate equals to DEFAULT_TOUCH_LIGHTLY_RATE
        defaultMEncountersCommandBranchShouldBeFound("touchLightlyRate.equals=" + DEFAULT_TOUCH_LIGHTLY_RATE);

        // Get all the mEncountersCommandBranchList where touchLightlyRate equals to UPDATED_TOUCH_LIGHTLY_RATE
        defaultMEncountersCommandBranchShouldNotBeFound("touchLightlyRate.equals=" + UPDATED_TOUCH_LIGHTLY_RATE);
    }

    @Test
    @Transactional
    public void getAllMEncountersCommandBranchesByTouchLightlyRateIsInShouldWork() throws Exception {
        // Initialize the database
        mEncountersCommandBranchRepository.saveAndFlush(mEncountersCommandBranch);

        // Get all the mEncountersCommandBranchList where touchLightlyRate in DEFAULT_TOUCH_LIGHTLY_RATE or UPDATED_TOUCH_LIGHTLY_RATE
        defaultMEncountersCommandBranchShouldBeFound("touchLightlyRate.in=" + DEFAULT_TOUCH_LIGHTLY_RATE + "," + UPDATED_TOUCH_LIGHTLY_RATE);

        // Get all the mEncountersCommandBranchList where touchLightlyRate equals to UPDATED_TOUCH_LIGHTLY_RATE
        defaultMEncountersCommandBranchShouldNotBeFound("touchLightlyRate.in=" + UPDATED_TOUCH_LIGHTLY_RATE);
    }

    @Test
    @Transactional
    public void getAllMEncountersCommandBranchesByTouchLightlyRateIsNullOrNotNull() throws Exception {
        // Initialize the database
        mEncountersCommandBranchRepository.saveAndFlush(mEncountersCommandBranch);

        // Get all the mEncountersCommandBranchList where touchLightlyRate is not null
        defaultMEncountersCommandBranchShouldBeFound("touchLightlyRate.specified=true");

        // Get all the mEncountersCommandBranchList where touchLightlyRate is null
        defaultMEncountersCommandBranchShouldNotBeFound("touchLightlyRate.specified=false");
    }

    @Test
    @Transactional
    public void getAllMEncountersCommandBranchesByTouchLightlyRateIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mEncountersCommandBranchRepository.saveAndFlush(mEncountersCommandBranch);

        // Get all the mEncountersCommandBranchList where touchLightlyRate greater than or equals to DEFAULT_TOUCH_LIGHTLY_RATE
        defaultMEncountersCommandBranchShouldBeFound("touchLightlyRate.greaterOrEqualThan=" + DEFAULT_TOUCH_LIGHTLY_RATE);

        // Get all the mEncountersCommandBranchList where touchLightlyRate greater than or equals to UPDATED_TOUCH_LIGHTLY_RATE
        defaultMEncountersCommandBranchShouldNotBeFound("touchLightlyRate.greaterOrEqualThan=" + UPDATED_TOUCH_LIGHTLY_RATE);
    }

    @Test
    @Transactional
    public void getAllMEncountersCommandBranchesByTouchLightlyRateIsLessThanSomething() throws Exception {
        // Initialize the database
        mEncountersCommandBranchRepository.saveAndFlush(mEncountersCommandBranch);

        // Get all the mEncountersCommandBranchList where touchLightlyRate less than or equals to DEFAULT_TOUCH_LIGHTLY_RATE
        defaultMEncountersCommandBranchShouldNotBeFound("touchLightlyRate.lessThan=" + DEFAULT_TOUCH_LIGHTLY_RATE);

        // Get all the mEncountersCommandBranchList where touchLightlyRate less than or equals to UPDATED_TOUCH_LIGHTLY_RATE
        defaultMEncountersCommandBranchShouldBeFound("touchLightlyRate.lessThan=" + UPDATED_TOUCH_LIGHTLY_RATE);
    }


    @Test
    @Transactional
    public void getAllMEncountersCommandBranchesByPostRateIsEqualToSomething() throws Exception {
        // Initialize the database
        mEncountersCommandBranchRepository.saveAndFlush(mEncountersCommandBranch);

        // Get all the mEncountersCommandBranchList where postRate equals to DEFAULT_POST_RATE
        defaultMEncountersCommandBranchShouldBeFound("postRate.equals=" + DEFAULT_POST_RATE);

        // Get all the mEncountersCommandBranchList where postRate equals to UPDATED_POST_RATE
        defaultMEncountersCommandBranchShouldNotBeFound("postRate.equals=" + UPDATED_POST_RATE);
    }

    @Test
    @Transactional
    public void getAllMEncountersCommandBranchesByPostRateIsInShouldWork() throws Exception {
        // Initialize the database
        mEncountersCommandBranchRepository.saveAndFlush(mEncountersCommandBranch);

        // Get all the mEncountersCommandBranchList where postRate in DEFAULT_POST_RATE or UPDATED_POST_RATE
        defaultMEncountersCommandBranchShouldBeFound("postRate.in=" + DEFAULT_POST_RATE + "," + UPDATED_POST_RATE);

        // Get all the mEncountersCommandBranchList where postRate equals to UPDATED_POST_RATE
        defaultMEncountersCommandBranchShouldNotBeFound("postRate.in=" + UPDATED_POST_RATE);
    }

    @Test
    @Transactional
    public void getAllMEncountersCommandBranchesByPostRateIsNullOrNotNull() throws Exception {
        // Initialize the database
        mEncountersCommandBranchRepository.saveAndFlush(mEncountersCommandBranch);

        // Get all the mEncountersCommandBranchList where postRate is not null
        defaultMEncountersCommandBranchShouldBeFound("postRate.specified=true");

        // Get all the mEncountersCommandBranchList where postRate is null
        defaultMEncountersCommandBranchShouldNotBeFound("postRate.specified=false");
    }

    @Test
    @Transactional
    public void getAllMEncountersCommandBranchesByPostRateIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mEncountersCommandBranchRepository.saveAndFlush(mEncountersCommandBranch);

        // Get all the mEncountersCommandBranchList where postRate greater than or equals to DEFAULT_POST_RATE
        defaultMEncountersCommandBranchShouldBeFound("postRate.greaterOrEqualThan=" + DEFAULT_POST_RATE);

        // Get all the mEncountersCommandBranchList where postRate greater than or equals to UPDATED_POST_RATE
        defaultMEncountersCommandBranchShouldNotBeFound("postRate.greaterOrEqualThan=" + UPDATED_POST_RATE);
    }

    @Test
    @Transactional
    public void getAllMEncountersCommandBranchesByPostRateIsLessThanSomething() throws Exception {
        // Initialize the database
        mEncountersCommandBranchRepository.saveAndFlush(mEncountersCommandBranch);

        // Get all the mEncountersCommandBranchList where postRate less than or equals to DEFAULT_POST_RATE
        defaultMEncountersCommandBranchShouldNotBeFound("postRate.lessThan=" + DEFAULT_POST_RATE);

        // Get all the mEncountersCommandBranchList where postRate less than or equals to UPDATED_POST_RATE
        defaultMEncountersCommandBranchShouldBeFound("postRate.lessThan=" + UPDATED_POST_RATE);
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultMEncountersCommandBranchShouldBeFound(String filter) throws Exception {
        restMEncountersCommandBranchMockMvc.perform(get("/api/m-encounters-command-branches?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(mEncountersCommandBranch.getId().intValue())))
            .andExpect(jsonPath("$.[*].ballFloatCondition").value(hasItem(DEFAULT_BALL_FLOAT_CONDITION)))
            .andExpect(jsonPath("$.[*].condition").value(hasItem(DEFAULT_CONDITION)))
            .andExpect(jsonPath("$.[*].encountersType").value(hasItem(DEFAULT_ENCOUNTERS_TYPE)))
            .andExpect(jsonPath("$.[*].isSuccess").value(hasItem(DEFAULT_IS_SUCCESS)))
            .andExpect(jsonPath("$.[*].commandType").value(hasItem(DEFAULT_COMMAND_TYPE)))
            .andExpect(jsonPath("$.[*].successRate").value(hasItem(DEFAULT_SUCCESS_RATE)))
            .andExpect(jsonPath("$.[*].looseBallRate").value(hasItem(DEFAULT_LOOSE_BALL_RATE)))
            .andExpect(jsonPath("$.[*].touchLightlyRate").value(hasItem(DEFAULT_TOUCH_LIGHTLY_RATE)))
            .andExpect(jsonPath("$.[*].postRate").value(hasItem(DEFAULT_POST_RATE)));

        // Check, that the count call also returns 1
        restMEncountersCommandBranchMockMvc.perform(get("/api/m-encounters-command-branches/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultMEncountersCommandBranchShouldNotBeFound(String filter) throws Exception {
        restMEncountersCommandBranchMockMvc.perform(get("/api/m-encounters-command-branches?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restMEncountersCommandBranchMockMvc.perform(get("/api/m-encounters-command-branches/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("0"));
    }


    @Test
    @Transactional
    public void getNonExistingMEncountersCommandBranch() throws Exception {
        // Get the mEncountersCommandBranch
        restMEncountersCommandBranchMockMvc.perform(get("/api/m-encounters-command-branches/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateMEncountersCommandBranch() throws Exception {
        // Initialize the database
        mEncountersCommandBranchRepository.saveAndFlush(mEncountersCommandBranch);

        int databaseSizeBeforeUpdate = mEncountersCommandBranchRepository.findAll().size();

        // Update the mEncountersCommandBranch
        MEncountersCommandBranch updatedMEncountersCommandBranch = mEncountersCommandBranchRepository.findById(mEncountersCommandBranch.getId()).get();
        // Disconnect from session so that the updates on updatedMEncountersCommandBranch are not directly saved in db
        em.detach(updatedMEncountersCommandBranch);
        updatedMEncountersCommandBranch
            .ballFloatCondition(UPDATED_BALL_FLOAT_CONDITION)
            .condition(UPDATED_CONDITION)
            .encountersType(UPDATED_ENCOUNTERS_TYPE)
            .isSuccess(UPDATED_IS_SUCCESS)
            .commandType(UPDATED_COMMAND_TYPE)
            .successRate(UPDATED_SUCCESS_RATE)
            .looseBallRate(UPDATED_LOOSE_BALL_RATE)
            .touchLightlyRate(UPDATED_TOUCH_LIGHTLY_RATE)
            .postRate(UPDATED_POST_RATE);
        MEncountersCommandBranchDTO mEncountersCommandBranchDTO = mEncountersCommandBranchMapper.toDto(updatedMEncountersCommandBranch);

        restMEncountersCommandBranchMockMvc.perform(put("/api/m-encounters-command-branches")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mEncountersCommandBranchDTO)))
            .andExpect(status().isOk());

        // Validate the MEncountersCommandBranch in the database
        List<MEncountersCommandBranch> mEncountersCommandBranchList = mEncountersCommandBranchRepository.findAll();
        assertThat(mEncountersCommandBranchList).hasSize(databaseSizeBeforeUpdate);
        MEncountersCommandBranch testMEncountersCommandBranch = mEncountersCommandBranchList.get(mEncountersCommandBranchList.size() - 1);
        assertThat(testMEncountersCommandBranch.getBallFloatCondition()).isEqualTo(UPDATED_BALL_FLOAT_CONDITION);
        assertThat(testMEncountersCommandBranch.getCondition()).isEqualTo(UPDATED_CONDITION);
        assertThat(testMEncountersCommandBranch.getEncountersType()).isEqualTo(UPDATED_ENCOUNTERS_TYPE);
        assertThat(testMEncountersCommandBranch.getIsSuccess()).isEqualTo(UPDATED_IS_SUCCESS);
        assertThat(testMEncountersCommandBranch.getCommandType()).isEqualTo(UPDATED_COMMAND_TYPE);
        assertThat(testMEncountersCommandBranch.getSuccessRate()).isEqualTo(UPDATED_SUCCESS_RATE);
        assertThat(testMEncountersCommandBranch.getLooseBallRate()).isEqualTo(UPDATED_LOOSE_BALL_RATE);
        assertThat(testMEncountersCommandBranch.getTouchLightlyRate()).isEqualTo(UPDATED_TOUCH_LIGHTLY_RATE);
        assertThat(testMEncountersCommandBranch.getPostRate()).isEqualTo(UPDATED_POST_RATE);
    }

    @Test
    @Transactional
    public void updateNonExistingMEncountersCommandBranch() throws Exception {
        int databaseSizeBeforeUpdate = mEncountersCommandBranchRepository.findAll().size();

        // Create the MEncountersCommandBranch
        MEncountersCommandBranchDTO mEncountersCommandBranchDTO = mEncountersCommandBranchMapper.toDto(mEncountersCommandBranch);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restMEncountersCommandBranchMockMvc.perform(put("/api/m-encounters-command-branches")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mEncountersCommandBranchDTO)))
            .andExpect(status().isBadRequest());

        // Validate the MEncountersCommandBranch in the database
        List<MEncountersCommandBranch> mEncountersCommandBranchList = mEncountersCommandBranchRepository.findAll();
        assertThat(mEncountersCommandBranchList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteMEncountersCommandBranch() throws Exception {
        // Initialize the database
        mEncountersCommandBranchRepository.saveAndFlush(mEncountersCommandBranch);

        int databaseSizeBeforeDelete = mEncountersCommandBranchRepository.findAll().size();

        // Delete the mEncountersCommandBranch
        restMEncountersCommandBranchMockMvc.perform(delete("/api/m-encounters-command-branches/{id}", mEncountersCommandBranch.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database is empty
        List<MEncountersCommandBranch> mEncountersCommandBranchList = mEncountersCommandBranchRepository.findAll();
        assertThat(mEncountersCommandBranchList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(MEncountersCommandBranch.class);
        MEncountersCommandBranch mEncountersCommandBranch1 = new MEncountersCommandBranch();
        mEncountersCommandBranch1.setId(1L);
        MEncountersCommandBranch mEncountersCommandBranch2 = new MEncountersCommandBranch();
        mEncountersCommandBranch2.setId(mEncountersCommandBranch1.getId());
        assertThat(mEncountersCommandBranch1).isEqualTo(mEncountersCommandBranch2);
        mEncountersCommandBranch2.setId(2L);
        assertThat(mEncountersCommandBranch1).isNotEqualTo(mEncountersCommandBranch2);
        mEncountersCommandBranch1.setId(null);
        assertThat(mEncountersCommandBranch1).isNotEqualTo(mEncountersCommandBranch2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(MEncountersCommandBranchDTO.class);
        MEncountersCommandBranchDTO mEncountersCommandBranchDTO1 = new MEncountersCommandBranchDTO();
        mEncountersCommandBranchDTO1.setId(1L);
        MEncountersCommandBranchDTO mEncountersCommandBranchDTO2 = new MEncountersCommandBranchDTO();
        assertThat(mEncountersCommandBranchDTO1).isNotEqualTo(mEncountersCommandBranchDTO2);
        mEncountersCommandBranchDTO2.setId(mEncountersCommandBranchDTO1.getId());
        assertThat(mEncountersCommandBranchDTO1).isEqualTo(mEncountersCommandBranchDTO2);
        mEncountersCommandBranchDTO2.setId(2L);
        assertThat(mEncountersCommandBranchDTO1).isNotEqualTo(mEncountersCommandBranchDTO2);
        mEncountersCommandBranchDTO1.setId(null);
        assertThat(mEncountersCommandBranchDTO1).isNotEqualTo(mEncountersCommandBranchDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(mEncountersCommandBranchMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(mEncountersCommandBranchMapper.fromId(null)).isNull();
    }
}
