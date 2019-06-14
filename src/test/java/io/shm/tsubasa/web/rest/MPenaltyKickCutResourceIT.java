package io.shm.tsubasa.web.rest;

import io.shm.tsubasa.TsubasaApp;
import io.shm.tsubasa.domain.MPenaltyKickCut;
import io.shm.tsubasa.repository.MPenaltyKickCutRepository;
import io.shm.tsubasa.service.MPenaltyKickCutService;
import io.shm.tsubasa.service.dto.MPenaltyKickCutDTO;
import io.shm.tsubasa.service.mapper.MPenaltyKickCutMapper;
import io.shm.tsubasa.web.rest.errors.ExceptionTranslator;
import io.shm.tsubasa.service.dto.MPenaltyKickCutCriteria;
import io.shm.tsubasa.service.MPenaltyKickCutQueryService;

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
 * Integration tests for the {@Link MPenaltyKickCutResource} REST controller.
 */
@SpringBootTest(classes = TsubasaApp.class)
public class MPenaltyKickCutResourceIT {

    private static final Integer DEFAULT_KICKER_COURSE = 1;
    private static final Integer UPDATED_KICKER_COURSE = 2;

    private static final Integer DEFAULT_KEEPER_COURSE = 1;
    private static final Integer UPDATED_KEEPER_COURSE = 2;

    private static final Integer DEFAULT_KEEPER_COMMAND = 1;
    private static final Integer UPDATED_KEEPER_COMMAND = 2;

    private static final Integer DEFAULT_RESULT_TYPE = 1;
    private static final Integer UPDATED_RESULT_TYPE = 2;

    private static final String DEFAULT_OFFENSE_SEQUENCE_TEXT = "AAAAAAAAAA";
    private static final String UPDATED_OFFENSE_SEQUENCE_TEXT = "BBBBBBBBBB";

    private static final String DEFAULT_DEFENSE_SEQUENCE_TEXT = "AAAAAAAAAA";
    private static final String UPDATED_DEFENSE_SEQUENCE_TEXT = "BBBBBBBBBB";

    @Autowired
    private MPenaltyKickCutRepository mPenaltyKickCutRepository;

    @Autowired
    private MPenaltyKickCutMapper mPenaltyKickCutMapper;

    @Autowired
    private MPenaltyKickCutService mPenaltyKickCutService;

    @Autowired
    private MPenaltyKickCutQueryService mPenaltyKickCutQueryService;

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

    private MockMvc restMPenaltyKickCutMockMvc;

    private MPenaltyKickCut mPenaltyKickCut;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final MPenaltyKickCutResource mPenaltyKickCutResource = new MPenaltyKickCutResource(mPenaltyKickCutService, mPenaltyKickCutQueryService);
        this.restMPenaltyKickCutMockMvc = MockMvcBuilders.standaloneSetup(mPenaltyKickCutResource)
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
    public static MPenaltyKickCut createEntity(EntityManager em) {
        MPenaltyKickCut mPenaltyKickCut = new MPenaltyKickCut()
            .kickerCourse(DEFAULT_KICKER_COURSE)
            .keeperCourse(DEFAULT_KEEPER_COURSE)
            .keeperCommand(DEFAULT_KEEPER_COMMAND)
            .resultType(DEFAULT_RESULT_TYPE)
            .offenseSequenceText(DEFAULT_OFFENSE_SEQUENCE_TEXT)
            .defenseSequenceText(DEFAULT_DEFENSE_SEQUENCE_TEXT);
        return mPenaltyKickCut;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static MPenaltyKickCut createUpdatedEntity(EntityManager em) {
        MPenaltyKickCut mPenaltyKickCut = new MPenaltyKickCut()
            .kickerCourse(UPDATED_KICKER_COURSE)
            .keeperCourse(UPDATED_KEEPER_COURSE)
            .keeperCommand(UPDATED_KEEPER_COMMAND)
            .resultType(UPDATED_RESULT_TYPE)
            .offenseSequenceText(UPDATED_OFFENSE_SEQUENCE_TEXT)
            .defenseSequenceText(UPDATED_DEFENSE_SEQUENCE_TEXT);
        return mPenaltyKickCut;
    }

    @BeforeEach
    public void initTest() {
        mPenaltyKickCut = createEntity(em);
    }

    @Test
    @Transactional
    public void createMPenaltyKickCut() throws Exception {
        int databaseSizeBeforeCreate = mPenaltyKickCutRepository.findAll().size();

        // Create the MPenaltyKickCut
        MPenaltyKickCutDTO mPenaltyKickCutDTO = mPenaltyKickCutMapper.toDto(mPenaltyKickCut);
        restMPenaltyKickCutMockMvc.perform(post("/api/m-penalty-kick-cuts")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mPenaltyKickCutDTO)))
            .andExpect(status().isCreated());

        // Validate the MPenaltyKickCut in the database
        List<MPenaltyKickCut> mPenaltyKickCutList = mPenaltyKickCutRepository.findAll();
        assertThat(mPenaltyKickCutList).hasSize(databaseSizeBeforeCreate + 1);
        MPenaltyKickCut testMPenaltyKickCut = mPenaltyKickCutList.get(mPenaltyKickCutList.size() - 1);
        assertThat(testMPenaltyKickCut.getKickerCourse()).isEqualTo(DEFAULT_KICKER_COURSE);
        assertThat(testMPenaltyKickCut.getKeeperCourse()).isEqualTo(DEFAULT_KEEPER_COURSE);
        assertThat(testMPenaltyKickCut.getKeeperCommand()).isEqualTo(DEFAULT_KEEPER_COMMAND);
        assertThat(testMPenaltyKickCut.getResultType()).isEqualTo(DEFAULT_RESULT_TYPE);
        assertThat(testMPenaltyKickCut.getOffenseSequenceText()).isEqualTo(DEFAULT_OFFENSE_SEQUENCE_TEXT);
        assertThat(testMPenaltyKickCut.getDefenseSequenceText()).isEqualTo(DEFAULT_DEFENSE_SEQUENCE_TEXT);
    }

    @Test
    @Transactional
    public void createMPenaltyKickCutWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = mPenaltyKickCutRepository.findAll().size();

        // Create the MPenaltyKickCut with an existing ID
        mPenaltyKickCut.setId(1L);
        MPenaltyKickCutDTO mPenaltyKickCutDTO = mPenaltyKickCutMapper.toDto(mPenaltyKickCut);

        // An entity with an existing ID cannot be created, so this API call must fail
        restMPenaltyKickCutMockMvc.perform(post("/api/m-penalty-kick-cuts")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mPenaltyKickCutDTO)))
            .andExpect(status().isBadRequest());

        // Validate the MPenaltyKickCut in the database
        List<MPenaltyKickCut> mPenaltyKickCutList = mPenaltyKickCutRepository.findAll();
        assertThat(mPenaltyKickCutList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkKickerCourseIsRequired() throws Exception {
        int databaseSizeBeforeTest = mPenaltyKickCutRepository.findAll().size();
        // set the field null
        mPenaltyKickCut.setKickerCourse(null);

        // Create the MPenaltyKickCut, which fails.
        MPenaltyKickCutDTO mPenaltyKickCutDTO = mPenaltyKickCutMapper.toDto(mPenaltyKickCut);

        restMPenaltyKickCutMockMvc.perform(post("/api/m-penalty-kick-cuts")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mPenaltyKickCutDTO)))
            .andExpect(status().isBadRequest());

        List<MPenaltyKickCut> mPenaltyKickCutList = mPenaltyKickCutRepository.findAll();
        assertThat(mPenaltyKickCutList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkKeeperCourseIsRequired() throws Exception {
        int databaseSizeBeforeTest = mPenaltyKickCutRepository.findAll().size();
        // set the field null
        mPenaltyKickCut.setKeeperCourse(null);

        // Create the MPenaltyKickCut, which fails.
        MPenaltyKickCutDTO mPenaltyKickCutDTO = mPenaltyKickCutMapper.toDto(mPenaltyKickCut);

        restMPenaltyKickCutMockMvc.perform(post("/api/m-penalty-kick-cuts")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mPenaltyKickCutDTO)))
            .andExpect(status().isBadRequest());

        List<MPenaltyKickCut> mPenaltyKickCutList = mPenaltyKickCutRepository.findAll();
        assertThat(mPenaltyKickCutList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkKeeperCommandIsRequired() throws Exception {
        int databaseSizeBeforeTest = mPenaltyKickCutRepository.findAll().size();
        // set the field null
        mPenaltyKickCut.setKeeperCommand(null);

        // Create the MPenaltyKickCut, which fails.
        MPenaltyKickCutDTO mPenaltyKickCutDTO = mPenaltyKickCutMapper.toDto(mPenaltyKickCut);

        restMPenaltyKickCutMockMvc.perform(post("/api/m-penalty-kick-cuts")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mPenaltyKickCutDTO)))
            .andExpect(status().isBadRequest());

        List<MPenaltyKickCut> mPenaltyKickCutList = mPenaltyKickCutRepository.findAll();
        assertThat(mPenaltyKickCutList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkResultTypeIsRequired() throws Exception {
        int databaseSizeBeforeTest = mPenaltyKickCutRepository.findAll().size();
        // set the field null
        mPenaltyKickCut.setResultType(null);

        // Create the MPenaltyKickCut, which fails.
        MPenaltyKickCutDTO mPenaltyKickCutDTO = mPenaltyKickCutMapper.toDto(mPenaltyKickCut);

        restMPenaltyKickCutMockMvc.perform(post("/api/m-penalty-kick-cuts")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mPenaltyKickCutDTO)))
            .andExpect(status().isBadRequest());

        List<MPenaltyKickCut> mPenaltyKickCutList = mPenaltyKickCutRepository.findAll();
        assertThat(mPenaltyKickCutList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllMPenaltyKickCuts() throws Exception {
        // Initialize the database
        mPenaltyKickCutRepository.saveAndFlush(mPenaltyKickCut);

        // Get all the mPenaltyKickCutList
        restMPenaltyKickCutMockMvc.perform(get("/api/m-penalty-kick-cuts?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(mPenaltyKickCut.getId().intValue())))
            .andExpect(jsonPath("$.[*].kickerCourse").value(hasItem(DEFAULT_KICKER_COURSE)))
            .andExpect(jsonPath("$.[*].keeperCourse").value(hasItem(DEFAULT_KEEPER_COURSE)))
            .andExpect(jsonPath("$.[*].keeperCommand").value(hasItem(DEFAULT_KEEPER_COMMAND)))
            .andExpect(jsonPath("$.[*].resultType").value(hasItem(DEFAULT_RESULT_TYPE)))
            .andExpect(jsonPath("$.[*].offenseSequenceText").value(hasItem(DEFAULT_OFFENSE_SEQUENCE_TEXT.toString())))
            .andExpect(jsonPath("$.[*].defenseSequenceText").value(hasItem(DEFAULT_DEFENSE_SEQUENCE_TEXT.toString())));
    }
    
    @Test
    @Transactional
    public void getMPenaltyKickCut() throws Exception {
        // Initialize the database
        mPenaltyKickCutRepository.saveAndFlush(mPenaltyKickCut);

        // Get the mPenaltyKickCut
        restMPenaltyKickCutMockMvc.perform(get("/api/m-penalty-kick-cuts/{id}", mPenaltyKickCut.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(mPenaltyKickCut.getId().intValue()))
            .andExpect(jsonPath("$.kickerCourse").value(DEFAULT_KICKER_COURSE))
            .andExpect(jsonPath("$.keeperCourse").value(DEFAULT_KEEPER_COURSE))
            .andExpect(jsonPath("$.keeperCommand").value(DEFAULT_KEEPER_COMMAND))
            .andExpect(jsonPath("$.resultType").value(DEFAULT_RESULT_TYPE))
            .andExpect(jsonPath("$.offenseSequenceText").value(DEFAULT_OFFENSE_SEQUENCE_TEXT.toString()))
            .andExpect(jsonPath("$.defenseSequenceText").value(DEFAULT_DEFENSE_SEQUENCE_TEXT.toString()));
    }

    @Test
    @Transactional
    public void getAllMPenaltyKickCutsByKickerCourseIsEqualToSomething() throws Exception {
        // Initialize the database
        mPenaltyKickCutRepository.saveAndFlush(mPenaltyKickCut);

        // Get all the mPenaltyKickCutList where kickerCourse equals to DEFAULT_KICKER_COURSE
        defaultMPenaltyKickCutShouldBeFound("kickerCourse.equals=" + DEFAULT_KICKER_COURSE);

        // Get all the mPenaltyKickCutList where kickerCourse equals to UPDATED_KICKER_COURSE
        defaultMPenaltyKickCutShouldNotBeFound("kickerCourse.equals=" + UPDATED_KICKER_COURSE);
    }

    @Test
    @Transactional
    public void getAllMPenaltyKickCutsByKickerCourseIsInShouldWork() throws Exception {
        // Initialize the database
        mPenaltyKickCutRepository.saveAndFlush(mPenaltyKickCut);

        // Get all the mPenaltyKickCutList where kickerCourse in DEFAULT_KICKER_COURSE or UPDATED_KICKER_COURSE
        defaultMPenaltyKickCutShouldBeFound("kickerCourse.in=" + DEFAULT_KICKER_COURSE + "," + UPDATED_KICKER_COURSE);

        // Get all the mPenaltyKickCutList where kickerCourse equals to UPDATED_KICKER_COURSE
        defaultMPenaltyKickCutShouldNotBeFound("kickerCourse.in=" + UPDATED_KICKER_COURSE);
    }

    @Test
    @Transactional
    public void getAllMPenaltyKickCutsByKickerCourseIsNullOrNotNull() throws Exception {
        // Initialize the database
        mPenaltyKickCutRepository.saveAndFlush(mPenaltyKickCut);

        // Get all the mPenaltyKickCutList where kickerCourse is not null
        defaultMPenaltyKickCutShouldBeFound("kickerCourse.specified=true");

        // Get all the mPenaltyKickCutList where kickerCourse is null
        defaultMPenaltyKickCutShouldNotBeFound("kickerCourse.specified=false");
    }

    @Test
    @Transactional
    public void getAllMPenaltyKickCutsByKickerCourseIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mPenaltyKickCutRepository.saveAndFlush(mPenaltyKickCut);

        // Get all the mPenaltyKickCutList where kickerCourse greater than or equals to DEFAULT_KICKER_COURSE
        defaultMPenaltyKickCutShouldBeFound("kickerCourse.greaterOrEqualThan=" + DEFAULT_KICKER_COURSE);

        // Get all the mPenaltyKickCutList where kickerCourse greater than or equals to UPDATED_KICKER_COURSE
        defaultMPenaltyKickCutShouldNotBeFound("kickerCourse.greaterOrEqualThan=" + UPDATED_KICKER_COURSE);
    }

    @Test
    @Transactional
    public void getAllMPenaltyKickCutsByKickerCourseIsLessThanSomething() throws Exception {
        // Initialize the database
        mPenaltyKickCutRepository.saveAndFlush(mPenaltyKickCut);

        // Get all the mPenaltyKickCutList where kickerCourse less than or equals to DEFAULT_KICKER_COURSE
        defaultMPenaltyKickCutShouldNotBeFound("kickerCourse.lessThan=" + DEFAULT_KICKER_COURSE);

        // Get all the mPenaltyKickCutList where kickerCourse less than or equals to UPDATED_KICKER_COURSE
        defaultMPenaltyKickCutShouldBeFound("kickerCourse.lessThan=" + UPDATED_KICKER_COURSE);
    }


    @Test
    @Transactional
    public void getAllMPenaltyKickCutsByKeeperCourseIsEqualToSomething() throws Exception {
        // Initialize the database
        mPenaltyKickCutRepository.saveAndFlush(mPenaltyKickCut);

        // Get all the mPenaltyKickCutList where keeperCourse equals to DEFAULT_KEEPER_COURSE
        defaultMPenaltyKickCutShouldBeFound("keeperCourse.equals=" + DEFAULT_KEEPER_COURSE);

        // Get all the mPenaltyKickCutList where keeperCourse equals to UPDATED_KEEPER_COURSE
        defaultMPenaltyKickCutShouldNotBeFound("keeperCourse.equals=" + UPDATED_KEEPER_COURSE);
    }

    @Test
    @Transactional
    public void getAllMPenaltyKickCutsByKeeperCourseIsInShouldWork() throws Exception {
        // Initialize the database
        mPenaltyKickCutRepository.saveAndFlush(mPenaltyKickCut);

        // Get all the mPenaltyKickCutList where keeperCourse in DEFAULT_KEEPER_COURSE or UPDATED_KEEPER_COURSE
        defaultMPenaltyKickCutShouldBeFound("keeperCourse.in=" + DEFAULT_KEEPER_COURSE + "," + UPDATED_KEEPER_COURSE);

        // Get all the mPenaltyKickCutList where keeperCourse equals to UPDATED_KEEPER_COURSE
        defaultMPenaltyKickCutShouldNotBeFound("keeperCourse.in=" + UPDATED_KEEPER_COURSE);
    }

    @Test
    @Transactional
    public void getAllMPenaltyKickCutsByKeeperCourseIsNullOrNotNull() throws Exception {
        // Initialize the database
        mPenaltyKickCutRepository.saveAndFlush(mPenaltyKickCut);

        // Get all the mPenaltyKickCutList where keeperCourse is not null
        defaultMPenaltyKickCutShouldBeFound("keeperCourse.specified=true");

        // Get all the mPenaltyKickCutList where keeperCourse is null
        defaultMPenaltyKickCutShouldNotBeFound("keeperCourse.specified=false");
    }

    @Test
    @Transactional
    public void getAllMPenaltyKickCutsByKeeperCourseIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mPenaltyKickCutRepository.saveAndFlush(mPenaltyKickCut);

        // Get all the mPenaltyKickCutList where keeperCourse greater than or equals to DEFAULT_KEEPER_COURSE
        defaultMPenaltyKickCutShouldBeFound("keeperCourse.greaterOrEqualThan=" + DEFAULT_KEEPER_COURSE);

        // Get all the mPenaltyKickCutList where keeperCourse greater than or equals to UPDATED_KEEPER_COURSE
        defaultMPenaltyKickCutShouldNotBeFound("keeperCourse.greaterOrEqualThan=" + UPDATED_KEEPER_COURSE);
    }

    @Test
    @Transactional
    public void getAllMPenaltyKickCutsByKeeperCourseIsLessThanSomething() throws Exception {
        // Initialize the database
        mPenaltyKickCutRepository.saveAndFlush(mPenaltyKickCut);

        // Get all the mPenaltyKickCutList where keeperCourse less than or equals to DEFAULT_KEEPER_COURSE
        defaultMPenaltyKickCutShouldNotBeFound("keeperCourse.lessThan=" + DEFAULT_KEEPER_COURSE);

        // Get all the mPenaltyKickCutList where keeperCourse less than or equals to UPDATED_KEEPER_COURSE
        defaultMPenaltyKickCutShouldBeFound("keeperCourse.lessThan=" + UPDATED_KEEPER_COURSE);
    }


    @Test
    @Transactional
    public void getAllMPenaltyKickCutsByKeeperCommandIsEqualToSomething() throws Exception {
        // Initialize the database
        mPenaltyKickCutRepository.saveAndFlush(mPenaltyKickCut);

        // Get all the mPenaltyKickCutList where keeperCommand equals to DEFAULT_KEEPER_COMMAND
        defaultMPenaltyKickCutShouldBeFound("keeperCommand.equals=" + DEFAULT_KEEPER_COMMAND);

        // Get all the mPenaltyKickCutList where keeperCommand equals to UPDATED_KEEPER_COMMAND
        defaultMPenaltyKickCutShouldNotBeFound("keeperCommand.equals=" + UPDATED_KEEPER_COMMAND);
    }

    @Test
    @Transactional
    public void getAllMPenaltyKickCutsByKeeperCommandIsInShouldWork() throws Exception {
        // Initialize the database
        mPenaltyKickCutRepository.saveAndFlush(mPenaltyKickCut);

        // Get all the mPenaltyKickCutList where keeperCommand in DEFAULT_KEEPER_COMMAND or UPDATED_KEEPER_COMMAND
        defaultMPenaltyKickCutShouldBeFound("keeperCommand.in=" + DEFAULT_KEEPER_COMMAND + "," + UPDATED_KEEPER_COMMAND);

        // Get all the mPenaltyKickCutList where keeperCommand equals to UPDATED_KEEPER_COMMAND
        defaultMPenaltyKickCutShouldNotBeFound("keeperCommand.in=" + UPDATED_KEEPER_COMMAND);
    }

    @Test
    @Transactional
    public void getAllMPenaltyKickCutsByKeeperCommandIsNullOrNotNull() throws Exception {
        // Initialize the database
        mPenaltyKickCutRepository.saveAndFlush(mPenaltyKickCut);

        // Get all the mPenaltyKickCutList where keeperCommand is not null
        defaultMPenaltyKickCutShouldBeFound("keeperCommand.specified=true");

        // Get all the mPenaltyKickCutList where keeperCommand is null
        defaultMPenaltyKickCutShouldNotBeFound("keeperCommand.specified=false");
    }

    @Test
    @Transactional
    public void getAllMPenaltyKickCutsByKeeperCommandIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mPenaltyKickCutRepository.saveAndFlush(mPenaltyKickCut);

        // Get all the mPenaltyKickCutList where keeperCommand greater than or equals to DEFAULT_KEEPER_COMMAND
        defaultMPenaltyKickCutShouldBeFound("keeperCommand.greaterOrEqualThan=" + DEFAULT_KEEPER_COMMAND);

        // Get all the mPenaltyKickCutList where keeperCommand greater than or equals to UPDATED_KEEPER_COMMAND
        defaultMPenaltyKickCutShouldNotBeFound("keeperCommand.greaterOrEqualThan=" + UPDATED_KEEPER_COMMAND);
    }

    @Test
    @Transactional
    public void getAllMPenaltyKickCutsByKeeperCommandIsLessThanSomething() throws Exception {
        // Initialize the database
        mPenaltyKickCutRepository.saveAndFlush(mPenaltyKickCut);

        // Get all the mPenaltyKickCutList where keeperCommand less than or equals to DEFAULT_KEEPER_COMMAND
        defaultMPenaltyKickCutShouldNotBeFound("keeperCommand.lessThan=" + DEFAULT_KEEPER_COMMAND);

        // Get all the mPenaltyKickCutList where keeperCommand less than or equals to UPDATED_KEEPER_COMMAND
        defaultMPenaltyKickCutShouldBeFound("keeperCommand.lessThan=" + UPDATED_KEEPER_COMMAND);
    }


    @Test
    @Transactional
    public void getAllMPenaltyKickCutsByResultTypeIsEqualToSomething() throws Exception {
        // Initialize the database
        mPenaltyKickCutRepository.saveAndFlush(mPenaltyKickCut);

        // Get all the mPenaltyKickCutList where resultType equals to DEFAULT_RESULT_TYPE
        defaultMPenaltyKickCutShouldBeFound("resultType.equals=" + DEFAULT_RESULT_TYPE);

        // Get all the mPenaltyKickCutList where resultType equals to UPDATED_RESULT_TYPE
        defaultMPenaltyKickCutShouldNotBeFound("resultType.equals=" + UPDATED_RESULT_TYPE);
    }

    @Test
    @Transactional
    public void getAllMPenaltyKickCutsByResultTypeIsInShouldWork() throws Exception {
        // Initialize the database
        mPenaltyKickCutRepository.saveAndFlush(mPenaltyKickCut);

        // Get all the mPenaltyKickCutList where resultType in DEFAULT_RESULT_TYPE or UPDATED_RESULT_TYPE
        defaultMPenaltyKickCutShouldBeFound("resultType.in=" + DEFAULT_RESULT_TYPE + "," + UPDATED_RESULT_TYPE);

        // Get all the mPenaltyKickCutList where resultType equals to UPDATED_RESULT_TYPE
        defaultMPenaltyKickCutShouldNotBeFound("resultType.in=" + UPDATED_RESULT_TYPE);
    }

    @Test
    @Transactional
    public void getAllMPenaltyKickCutsByResultTypeIsNullOrNotNull() throws Exception {
        // Initialize the database
        mPenaltyKickCutRepository.saveAndFlush(mPenaltyKickCut);

        // Get all the mPenaltyKickCutList where resultType is not null
        defaultMPenaltyKickCutShouldBeFound("resultType.specified=true");

        // Get all the mPenaltyKickCutList where resultType is null
        defaultMPenaltyKickCutShouldNotBeFound("resultType.specified=false");
    }

    @Test
    @Transactional
    public void getAllMPenaltyKickCutsByResultTypeIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mPenaltyKickCutRepository.saveAndFlush(mPenaltyKickCut);

        // Get all the mPenaltyKickCutList where resultType greater than or equals to DEFAULT_RESULT_TYPE
        defaultMPenaltyKickCutShouldBeFound("resultType.greaterOrEqualThan=" + DEFAULT_RESULT_TYPE);

        // Get all the mPenaltyKickCutList where resultType greater than or equals to UPDATED_RESULT_TYPE
        defaultMPenaltyKickCutShouldNotBeFound("resultType.greaterOrEqualThan=" + UPDATED_RESULT_TYPE);
    }

    @Test
    @Transactional
    public void getAllMPenaltyKickCutsByResultTypeIsLessThanSomething() throws Exception {
        // Initialize the database
        mPenaltyKickCutRepository.saveAndFlush(mPenaltyKickCut);

        // Get all the mPenaltyKickCutList where resultType less than or equals to DEFAULT_RESULT_TYPE
        defaultMPenaltyKickCutShouldNotBeFound("resultType.lessThan=" + DEFAULT_RESULT_TYPE);

        // Get all the mPenaltyKickCutList where resultType less than or equals to UPDATED_RESULT_TYPE
        defaultMPenaltyKickCutShouldBeFound("resultType.lessThan=" + UPDATED_RESULT_TYPE);
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultMPenaltyKickCutShouldBeFound(String filter) throws Exception {
        restMPenaltyKickCutMockMvc.perform(get("/api/m-penalty-kick-cuts?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(mPenaltyKickCut.getId().intValue())))
            .andExpect(jsonPath("$.[*].kickerCourse").value(hasItem(DEFAULT_KICKER_COURSE)))
            .andExpect(jsonPath("$.[*].keeperCourse").value(hasItem(DEFAULT_KEEPER_COURSE)))
            .andExpect(jsonPath("$.[*].keeperCommand").value(hasItem(DEFAULT_KEEPER_COMMAND)))
            .andExpect(jsonPath("$.[*].resultType").value(hasItem(DEFAULT_RESULT_TYPE)))
            .andExpect(jsonPath("$.[*].offenseSequenceText").value(hasItem(DEFAULT_OFFENSE_SEQUENCE_TEXT.toString())))
            .andExpect(jsonPath("$.[*].defenseSequenceText").value(hasItem(DEFAULT_DEFENSE_SEQUENCE_TEXT.toString())));

        // Check, that the count call also returns 1
        restMPenaltyKickCutMockMvc.perform(get("/api/m-penalty-kick-cuts/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultMPenaltyKickCutShouldNotBeFound(String filter) throws Exception {
        restMPenaltyKickCutMockMvc.perform(get("/api/m-penalty-kick-cuts?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restMPenaltyKickCutMockMvc.perform(get("/api/m-penalty-kick-cuts/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("0"));
    }


    @Test
    @Transactional
    public void getNonExistingMPenaltyKickCut() throws Exception {
        // Get the mPenaltyKickCut
        restMPenaltyKickCutMockMvc.perform(get("/api/m-penalty-kick-cuts/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateMPenaltyKickCut() throws Exception {
        // Initialize the database
        mPenaltyKickCutRepository.saveAndFlush(mPenaltyKickCut);

        int databaseSizeBeforeUpdate = mPenaltyKickCutRepository.findAll().size();

        // Update the mPenaltyKickCut
        MPenaltyKickCut updatedMPenaltyKickCut = mPenaltyKickCutRepository.findById(mPenaltyKickCut.getId()).get();
        // Disconnect from session so that the updates on updatedMPenaltyKickCut are not directly saved in db
        em.detach(updatedMPenaltyKickCut);
        updatedMPenaltyKickCut
            .kickerCourse(UPDATED_KICKER_COURSE)
            .keeperCourse(UPDATED_KEEPER_COURSE)
            .keeperCommand(UPDATED_KEEPER_COMMAND)
            .resultType(UPDATED_RESULT_TYPE)
            .offenseSequenceText(UPDATED_OFFENSE_SEQUENCE_TEXT)
            .defenseSequenceText(UPDATED_DEFENSE_SEQUENCE_TEXT);
        MPenaltyKickCutDTO mPenaltyKickCutDTO = mPenaltyKickCutMapper.toDto(updatedMPenaltyKickCut);

        restMPenaltyKickCutMockMvc.perform(put("/api/m-penalty-kick-cuts")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mPenaltyKickCutDTO)))
            .andExpect(status().isOk());

        // Validate the MPenaltyKickCut in the database
        List<MPenaltyKickCut> mPenaltyKickCutList = mPenaltyKickCutRepository.findAll();
        assertThat(mPenaltyKickCutList).hasSize(databaseSizeBeforeUpdate);
        MPenaltyKickCut testMPenaltyKickCut = mPenaltyKickCutList.get(mPenaltyKickCutList.size() - 1);
        assertThat(testMPenaltyKickCut.getKickerCourse()).isEqualTo(UPDATED_KICKER_COURSE);
        assertThat(testMPenaltyKickCut.getKeeperCourse()).isEqualTo(UPDATED_KEEPER_COURSE);
        assertThat(testMPenaltyKickCut.getKeeperCommand()).isEqualTo(UPDATED_KEEPER_COMMAND);
        assertThat(testMPenaltyKickCut.getResultType()).isEqualTo(UPDATED_RESULT_TYPE);
        assertThat(testMPenaltyKickCut.getOffenseSequenceText()).isEqualTo(UPDATED_OFFENSE_SEQUENCE_TEXT);
        assertThat(testMPenaltyKickCut.getDefenseSequenceText()).isEqualTo(UPDATED_DEFENSE_SEQUENCE_TEXT);
    }

    @Test
    @Transactional
    public void updateNonExistingMPenaltyKickCut() throws Exception {
        int databaseSizeBeforeUpdate = mPenaltyKickCutRepository.findAll().size();

        // Create the MPenaltyKickCut
        MPenaltyKickCutDTO mPenaltyKickCutDTO = mPenaltyKickCutMapper.toDto(mPenaltyKickCut);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restMPenaltyKickCutMockMvc.perform(put("/api/m-penalty-kick-cuts")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mPenaltyKickCutDTO)))
            .andExpect(status().isBadRequest());

        // Validate the MPenaltyKickCut in the database
        List<MPenaltyKickCut> mPenaltyKickCutList = mPenaltyKickCutRepository.findAll();
        assertThat(mPenaltyKickCutList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteMPenaltyKickCut() throws Exception {
        // Initialize the database
        mPenaltyKickCutRepository.saveAndFlush(mPenaltyKickCut);

        int databaseSizeBeforeDelete = mPenaltyKickCutRepository.findAll().size();

        // Delete the mPenaltyKickCut
        restMPenaltyKickCutMockMvc.perform(delete("/api/m-penalty-kick-cuts/{id}", mPenaltyKickCut.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database is empty
        List<MPenaltyKickCut> mPenaltyKickCutList = mPenaltyKickCutRepository.findAll();
        assertThat(mPenaltyKickCutList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(MPenaltyKickCut.class);
        MPenaltyKickCut mPenaltyKickCut1 = new MPenaltyKickCut();
        mPenaltyKickCut1.setId(1L);
        MPenaltyKickCut mPenaltyKickCut2 = new MPenaltyKickCut();
        mPenaltyKickCut2.setId(mPenaltyKickCut1.getId());
        assertThat(mPenaltyKickCut1).isEqualTo(mPenaltyKickCut2);
        mPenaltyKickCut2.setId(2L);
        assertThat(mPenaltyKickCut1).isNotEqualTo(mPenaltyKickCut2);
        mPenaltyKickCut1.setId(null);
        assertThat(mPenaltyKickCut1).isNotEqualTo(mPenaltyKickCut2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(MPenaltyKickCutDTO.class);
        MPenaltyKickCutDTO mPenaltyKickCutDTO1 = new MPenaltyKickCutDTO();
        mPenaltyKickCutDTO1.setId(1L);
        MPenaltyKickCutDTO mPenaltyKickCutDTO2 = new MPenaltyKickCutDTO();
        assertThat(mPenaltyKickCutDTO1).isNotEqualTo(mPenaltyKickCutDTO2);
        mPenaltyKickCutDTO2.setId(mPenaltyKickCutDTO1.getId());
        assertThat(mPenaltyKickCutDTO1).isEqualTo(mPenaltyKickCutDTO2);
        mPenaltyKickCutDTO2.setId(2L);
        assertThat(mPenaltyKickCutDTO1).isNotEqualTo(mPenaltyKickCutDTO2);
        mPenaltyKickCutDTO1.setId(null);
        assertThat(mPenaltyKickCutDTO1).isNotEqualTo(mPenaltyKickCutDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(mPenaltyKickCutMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(mPenaltyKickCutMapper.fromId(null)).isNull();
    }
}
