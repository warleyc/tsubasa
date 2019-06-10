package io.shm.tsubasa.web.rest;

import io.shm.tsubasa.TsubasaApp;
import io.shm.tsubasa.domain.MEncountersCut;
import io.shm.tsubasa.repository.MEncountersCutRepository;
import io.shm.tsubasa.service.MEncountersCutService;
import io.shm.tsubasa.service.dto.MEncountersCutDTO;
import io.shm.tsubasa.service.mapper.MEncountersCutMapper;
import io.shm.tsubasa.web.rest.errors.ExceptionTranslator;
import io.shm.tsubasa.service.dto.MEncountersCutCriteria;
import io.shm.tsubasa.service.MEncountersCutQueryService;

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
 * Integration tests for the {@Link MEncountersCutResource} REST controller.
 */
@SpringBootTest(classes = TsubasaApp.class)
public class MEncountersCutResourceIT {

    private static final Integer DEFAULT_CONDITION = 1;
    private static final Integer UPDATED_CONDITION = 2;

    private static final Integer DEFAULT_BALL_FLOAT_CONDITION = 1;
    private static final Integer UPDATED_BALL_FLOAT_CONDITION = 2;

    private static final Integer DEFAULT_COMMAND_1_TYPE = 1;
    private static final Integer UPDATED_COMMAND_1_TYPE = 2;

    private static final Integer DEFAULT_COMMAND_1_IS_SKILL = 1;
    private static final Integer UPDATED_COMMAND_1_IS_SKILL = 2;

    private static final Integer DEFAULT_COMMAND_2_TYPE = 1;
    private static final Integer UPDATED_COMMAND_2_TYPE = 2;

    private static final Integer DEFAULT_COMMAND_2_IS_SKILL = 1;
    private static final Integer UPDATED_COMMAND_2_IS_SKILL = 2;

    private static final Integer DEFAULT_RESULT = 1;
    private static final Integer UPDATED_RESULT = 2;

    private static final Integer DEFAULT_SHOOT_RESULT = 1;
    private static final Integer UPDATED_SHOOT_RESULT = 2;

    private static final Integer DEFAULT_IS_THROWN = 1;
    private static final Integer UPDATED_IS_THROWN = 2;

    private static final String DEFAULT_OFFENSE_SEQUENCE_TEXT = "AAAAAAAAAA";
    private static final String UPDATED_OFFENSE_SEQUENCE_TEXT = "BBBBBBBBBB";

    private static final String DEFAULT_DEFENSE_SEQUENCE_TEXT = "AAAAAAAAAA";
    private static final String UPDATED_DEFENSE_SEQUENCE_TEXT = "BBBBBBBBBB";

    @Autowired
    private MEncountersCutRepository mEncountersCutRepository;

    @Autowired
    private MEncountersCutMapper mEncountersCutMapper;

    @Autowired
    private MEncountersCutService mEncountersCutService;

    @Autowired
    private MEncountersCutQueryService mEncountersCutQueryService;

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

    private MockMvc restMEncountersCutMockMvc;

    private MEncountersCut mEncountersCut;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final MEncountersCutResource mEncountersCutResource = new MEncountersCutResource(mEncountersCutService, mEncountersCutQueryService);
        this.restMEncountersCutMockMvc = MockMvcBuilders.standaloneSetup(mEncountersCutResource)
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
    public static MEncountersCut createEntity(EntityManager em) {
        MEncountersCut mEncountersCut = new MEncountersCut()
            .condition(DEFAULT_CONDITION)
            .ballFloatCondition(DEFAULT_BALL_FLOAT_CONDITION)
            .command1Type(DEFAULT_COMMAND_1_TYPE)
            .command1IsSkill(DEFAULT_COMMAND_1_IS_SKILL)
            .command2Type(DEFAULT_COMMAND_2_TYPE)
            .command2IsSkill(DEFAULT_COMMAND_2_IS_SKILL)
            .result(DEFAULT_RESULT)
            .shootResult(DEFAULT_SHOOT_RESULT)
            .isThrown(DEFAULT_IS_THROWN)
            .offenseSequenceText(DEFAULT_OFFENSE_SEQUENCE_TEXT)
            .defenseSequenceText(DEFAULT_DEFENSE_SEQUENCE_TEXT);
        return mEncountersCut;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static MEncountersCut createUpdatedEntity(EntityManager em) {
        MEncountersCut mEncountersCut = new MEncountersCut()
            .condition(UPDATED_CONDITION)
            .ballFloatCondition(UPDATED_BALL_FLOAT_CONDITION)
            .command1Type(UPDATED_COMMAND_1_TYPE)
            .command1IsSkill(UPDATED_COMMAND_1_IS_SKILL)
            .command2Type(UPDATED_COMMAND_2_TYPE)
            .command2IsSkill(UPDATED_COMMAND_2_IS_SKILL)
            .result(UPDATED_RESULT)
            .shootResult(UPDATED_SHOOT_RESULT)
            .isThrown(UPDATED_IS_THROWN)
            .offenseSequenceText(UPDATED_OFFENSE_SEQUENCE_TEXT)
            .defenseSequenceText(UPDATED_DEFENSE_SEQUENCE_TEXT);
        return mEncountersCut;
    }

    @BeforeEach
    public void initTest() {
        mEncountersCut = createEntity(em);
    }

    @Test
    @Transactional
    public void createMEncountersCut() throws Exception {
        int databaseSizeBeforeCreate = mEncountersCutRepository.findAll().size();

        // Create the MEncountersCut
        MEncountersCutDTO mEncountersCutDTO = mEncountersCutMapper.toDto(mEncountersCut);
        restMEncountersCutMockMvc.perform(post("/api/m-encounters-cuts")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mEncountersCutDTO)))
            .andExpect(status().isCreated());

        // Validate the MEncountersCut in the database
        List<MEncountersCut> mEncountersCutList = mEncountersCutRepository.findAll();
        assertThat(mEncountersCutList).hasSize(databaseSizeBeforeCreate + 1);
        MEncountersCut testMEncountersCut = mEncountersCutList.get(mEncountersCutList.size() - 1);
        assertThat(testMEncountersCut.getCondition()).isEqualTo(DEFAULT_CONDITION);
        assertThat(testMEncountersCut.getBallFloatCondition()).isEqualTo(DEFAULT_BALL_FLOAT_CONDITION);
        assertThat(testMEncountersCut.getCommand1Type()).isEqualTo(DEFAULT_COMMAND_1_TYPE);
        assertThat(testMEncountersCut.getCommand1IsSkill()).isEqualTo(DEFAULT_COMMAND_1_IS_SKILL);
        assertThat(testMEncountersCut.getCommand2Type()).isEqualTo(DEFAULT_COMMAND_2_TYPE);
        assertThat(testMEncountersCut.getCommand2IsSkill()).isEqualTo(DEFAULT_COMMAND_2_IS_SKILL);
        assertThat(testMEncountersCut.getResult()).isEqualTo(DEFAULT_RESULT);
        assertThat(testMEncountersCut.getShootResult()).isEqualTo(DEFAULT_SHOOT_RESULT);
        assertThat(testMEncountersCut.getIsThrown()).isEqualTo(DEFAULT_IS_THROWN);
        assertThat(testMEncountersCut.getOffenseSequenceText()).isEqualTo(DEFAULT_OFFENSE_SEQUENCE_TEXT);
        assertThat(testMEncountersCut.getDefenseSequenceText()).isEqualTo(DEFAULT_DEFENSE_SEQUENCE_TEXT);
    }

    @Test
    @Transactional
    public void createMEncountersCutWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = mEncountersCutRepository.findAll().size();

        // Create the MEncountersCut with an existing ID
        mEncountersCut.setId(1L);
        MEncountersCutDTO mEncountersCutDTO = mEncountersCutMapper.toDto(mEncountersCut);

        // An entity with an existing ID cannot be created, so this API call must fail
        restMEncountersCutMockMvc.perform(post("/api/m-encounters-cuts")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mEncountersCutDTO)))
            .andExpect(status().isBadRequest());

        // Validate the MEncountersCut in the database
        List<MEncountersCut> mEncountersCutList = mEncountersCutRepository.findAll();
        assertThat(mEncountersCutList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkConditionIsRequired() throws Exception {
        int databaseSizeBeforeTest = mEncountersCutRepository.findAll().size();
        // set the field null
        mEncountersCut.setCondition(null);

        // Create the MEncountersCut, which fails.
        MEncountersCutDTO mEncountersCutDTO = mEncountersCutMapper.toDto(mEncountersCut);

        restMEncountersCutMockMvc.perform(post("/api/m-encounters-cuts")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mEncountersCutDTO)))
            .andExpect(status().isBadRequest());

        List<MEncountersCut> mEncountersCutList = mEncountersCutRepository.findAll();
        assertThat(mEncountersCutList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkBallFloatConditionIsRequired() throws Exception {
        int databaseSizeBeforeTest = mEncountersCutRepository.findAll().size();
        // set the field null
        mEncountersCut.setBallFloatCondition(null);

        // Create the MEncountersCut, which fails.
        MEncountersCutDTO mEncountersCutDTO = mEncountersCutMapper.toDto(mEncountersCut);

        restMEncountersCutMockMvc.perform(post("/api/m-encounters-cuts")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mEncountersCutDTO)))
            .andExpect(status().isBadRequest());

        List<MEncountersCut> mEncountersCutList = mEncountersCutRepository.findAll();
        assertThat(mEncountersCutList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkCommand1TypeIsRequired() throws Exception {
        int databaseSizeBeforeTest = mEncountersCutRepository.findAll().size();
        // set the field null
        mEncountersCut.setCommand1Type(null);

        // Create the MEncountersCut, which fails.
        MEncountersCutDTO mEncountersCutDTO = mEncountersCutMapper.toDto(mEncountersCut);

        restMEncountersCutMockMvc.perform(post("/api/m-encounters-cuts")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mEncountersCutDTO)))
            .andExpect(status().isBadRequest());

        List<MEncountersCut> mEncountersCutList = mEncountersCutRepository.findAll();
        assertThat(mEncountersCutList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkCommand1IsSkillIsRequired() throws Exception {
        int databaseSizeBeforeTest = mEncountersCutRepository.findAll().size();
        // set the field null
        mEncountersCut.setCommand1IsSkill(null);

        // Create the MEncountersCut, which fails.
        MEncountersCutDTO mEncountersCutDTO = mEncountersCutMapper.toDto(mEncountersCut);

        restMEncountersCutMockMvc.perform(post("/api/m-encounters-cuts")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mEncountersCutDTO)))
            .andExpect(status().isBadRequest());

        List<MEncountersCut> mEncountersCutList = mEncountersCutRepository.findAll();
        assertThat(mEncountersCutList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkCommand2TypeIsRequired() throws Exception {
        int databaseSizeBeforeTest = mEncountersCutRepository.findAll().size();
        // set the field null
        mEncountersCut.setCommand2Type(null);

        // Create the MEncountersCut, which fails.
        MEncountersCutDTO mEncountersCutDTO = mEncountersCutMapper.toDto(mEncountersCut);

        restMEncountersCutMockMvc.perform(post("/api/m-encounters-cuts")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mEncountersCutDTO)))
            .andExpect(status().isBadRequest());

        List<MEncountersCut> mEncountersCutList = mEncountersCutRepository.findAll();
        assertThat(mEncountersCutList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkCommand2IsSkillIsRequired() throws Exception {
        int databaseSizeBeforeTest = mEncountersCutRepository.findAll().size();
        // set the field null
        mEncountersCut.setCommand2IsSkill(null);

        // Create the MEncountersCut, which fails.
        MEncountersCutDTO mEncountersCutDTO = mEncountersCutMapper.toDto(mEncountersCut);

        restMEncountersCutMockMvc.perform(post("/api/m-encounters-cuts")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mEncountersCutDTO)))
            .andExpect(status().isBadRequest());

        List<MEncountersCut> mEncountersCutList = mEncountersCutRepository.findAll();
        assertThat(mEncountersCutList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkResultIsRequired() throws Exception {
        int databaseSizeBeforeTest = mEncountersCutRepository.findAll().size();
        // set the field null
        mEncountersCut.setResult(null);

        // Create the MEncountersCut, which fails.
        MEncountersCutDTO mEncountersCutDTO = mEncountersCutMapper.toDto(mEncountersCut);

        restMEncountersCutMockMvc.perform(post("/api/m-encounters-cuts")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mEncountersCutDTO)))
            .andExpect(status().isBadRequest());

        List<MEncountersCut> mEncountersCutList = mEncountersCutRepository.findAll();
        assertThat(mEncountersCutList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkShootResultIsRequired() throws Exception {
        int databaseSizeBeforeTest = mEncountersCutRepository.findAll().size();
        // set the field null
        mEncountersCut.setShootResult(null);

        // Create the MEncountersCut, which fails.
        MEncountersCutDTO mEncountersCutDTO = mEncountersCutMapper.toDto(mEncountersCut);

        restMEncountersCutMockMvc.perform(post("/api/m-encounters-cuts")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mEncountersCutDTO)))
            .andExpect(status().isBadRequest());

        List<MEncountersCut> mEncountersCutList = mEncountersCutRepository.findAll();
        assertThat(mEncountersCutList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkIsThrownIsRequired() throws Exception {
        int databaseSizeBeforeTest = mEncountersCutRepository.findAll().size();
        // set the field null
        mEncountersCut.setIsThrown(null);

        // Create the MEncountersCut, which fails.
        MEncountersCutDTO mEncountersCutDTO = mEncountersCutMapper.toDto(mEncountersCut);

        restMEncountersCutMockMvc.perform(post("/api/m-encounters-cuts")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mEncountersCutDTO)))
            .andExpect(status().isBadRequest());

        List<MEncountersCut> mEncountersCutList = mEncountersCutRepository.findAll();
        assertThat(mEncountersCutList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllMEncountersCuts() throws Exception {
        // Initialize the database
        mEncountersCutRepository.saveAndFlush(mEncountersCut);

        // Get all the mEncountersCutList
        restMEncountersCutMockMvc.perform(get("/api/m-encounters-cuts?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(mEncountersCut.getId().intValue())))
            .andExpect(jsonPath("$.[*].condition").value(hasItem(DEFAULT_CONDITION)))
            .andExpect(jsonPath("$.[*].ballFloatCondition").value(hasItem(DEFAULT_BALL_FLOAT_CONDITION)))
            .andExpect(jsonPath("$.[*].command1Type").value(hasItem(DEFAULT_COMMAND_1_TYPE)))
            .andExpect(jsonPath("$.[*].command1IsSkill").value(hasItem(DEFAULT_COMMAND_1_IS_SKILL)))
            .andExpect(jsonPath("$.[*].command2Type").value(hasItem(DEFAULT_COMMAND_2_TYPE)))
            .andExpect(jsonPath("$.[*].command2IsSkill").value(hasItem(DEFAULT_COMMAND_2_IS_SKILL)))
            .andExpect(jsonPath("$.[*].result").value(hasItem(DEFAULT_RESULT)))
            .andExpect(jsonPath("$.[*].shootResult").value(hasItem(DEFAULT_SHOOT_RESULT)))
            .andExpect(jsonPath("$.[*].isThrown").value(hasItem(DEFAULT_IS_THROWN)))
            .andExpect(jsonPath("$.[*].offenseSequenceText").value(hasItem(DEFAULT_OFFENSE_SEQUENCE_TEXT.toString())))
            .andExpect(jsonPath("$.[*].defenseSequenceText").value(hasItem(DEFAULT_DEFENSE_SEQUENCE_TEXT.toString())));
    }
    
    @Test
    @Transactional
    public void getMEncountersCut() throws Exception {
        // Initialize the database
        mEncountersCutRepository.saveAndFlush(mEncountersCut);

        // Get the mEncountersCut
        restMEncountersCutMockMvc.perform(get("/api/m-encounters-cuts/{id}", mEncountersCut.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(mEncountersCut.getId().intValue()))
            .andExpect(jsonPath("$.condition").value(DEFAULT_CONDITION))
            .andExpect(jsonPath("$.ballFloatCondition").value(DEFAULT_BALL_FLOAT_CONDITION))
            .andExpect(jsonPath("$.command1Type").value(DEFAULT_COMMAND_1_TYPE))
            .andExpect(jsonPath("$.command1IsSkill").value(DEFAULT_COMMAND_1_IS_SKILL))
            .andExpect(jsonPath("$.command2Type").value(DEFAULT_COMMAND_2_TYPE))
            .andExpect(jsonPath("$.command2IsSkill").value(DEFAULT_COMMAND_2_IS_SKILL))
            .andExpect(jsonPath("$.result").value(DEFAULT_RESULT))
            .andExpect(jsonPath("$.shootResult").value(DEFAULT_SHOOT_RESULT))
            .andExpect(jsonPath("$.isThrown").value(DEFAULT_IS_THROWN))
            .andExpect(jsonPath("$.offenseSequenceText").value(DEFAULT_OFFENSE_SEQUENCE_TEXT.toString()))
            .andExpect(jsonPath("$.defenseSequenceText").value(DEFAULT_DEFENSE_SEQUENCE_TEXT.toString()));
    }

    @Test
    @Transactional
    public void getAllMEncountersCutsByConditionIsEqualToSomething() throws Exception {
        // Initialize the database
        mEncountersCutRepository.saveAndFlush(mEncountersCut);

        // Get all the mEncountersCutList where condition equals to DEFAULT_CONDITION
        defaultMEncountersCutShouldBeFound("condition.equals=" + DEFAULT_CONDITION);

        // Get all the mEncountersCutList where condition equals to UPDATED_CONDITION
        defaultMEncountersCutShouldNotBeFound("condition.equals=" + UPDATED_CONDITION);
    }

    @Test
    @Transactional
    public void getAllMEncountersCutsByConditionIsInShouldWork() throws Exception {
        // Initialize the database
        mEncountersCutRepository.saveAndFlush(mEncountersCut);

        // Get all the mEncountersCutList where condition in DEFAULT_CONDITION or UPDATED_CONDITION
        defaultMEncountersCutShouldBeFound("condition.in=" + DEFAULT_CONDITION + "," + UPDATED_CONDITION);

        // Get all the mEncountersCutList where condition equals to UPDATED_CONDITION
        defaultMEncountersCutShouldNotBeFound("condition.in=" + UPDATED_CONDITION);
    }

    @Test
    @Transactional
    public void getAllMEncountersCutsByConditionIsNullOrNotNull() throws Exception {
        // Initialize the database
        mEncountersCutRepository.saveAndFlush(mEncountersCut);

        // Get all the mEncountersCutList where condition is not null
        defaultMEncountersCutShouldBeFound("condition.specified=true");

        // Get all the mEncountersCutList where condition is null
        defaultMEncountersCutShouldNotBeFound("condition.specified=false");
    }

    @Test
    @Transactional
    public void getAllMEncountersCutsByConditionIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mEncountersCutRepository.saveAndFlush(mEncountersCut);

        // Get all the mEncountersCutList where condition greater than or equals to DEFAULT_CONDITION
        defaultMEncountersCutShouldBeFound("condition.greaterOrEqualThan=" + DEFAULT_CONDITION);

        // Get all the mEncountersCutList where condition greater than or equals to UPDATED_CONDITION
        defaultMEncountersCutShouldNotBeFound("condition.greaterOrEqualThan=" + UPDATED_CONDITION);
    }

    @Test
    @Transactional
    public void getAllMEncountersCutsByConditionIsLessThanSomething() throws Exception {
        // Initialize the database
        mEncountersCutRepository.saveAndFlush(mEncountersCut);

        // Get all the mEncountersCutList where condition less than or equals to DEFAULT_CONDITION
        defaultMEncountersCutShouldNotBeFound("condition.lessThan=" + DEFAULT_CONDITION);

        // Get all the mEncountersCutList where condition less than or equals to UPDATED_CONDITION
        defaultMEncountersCutShouldBeFound("condition.lessThan=" + UPDATED_CONDITION);
    }


    @Test
    @Transactional
    public void getAllMEncountersCutsByBallFloatConditionIsEqualToSomething() throws Exception {
        // Initialize the database
        mEncountersCutRepository.saveAndFlush(mEncountersCut);

        // Get all the mEncountersCutList where ballFloatCondition equals to DEFAULT_BALL_FLOAT_CONDITION
        defaultMEncountersCutShouldBeFound("ballFloatCondition.equals=" + DEFAULT_BALL_FLOAT_CONDITION);

        // Get all the mEncountersCutList where ballFloatCondition equals to UPDATED_BALL_FLOAT_CONDITION
        defaultMEncountersCutShouldNotBeFound("ballFloatCondition.equals=" + UPDATED_BALL_FLOAT_CONDITION);
    }

    @Test
    @Transactional
    public void getAllMEncountersCutsByBallFloatConditionIsInShouldWork() throws Exception {
        // Initialize the database
        mEncountersCutRepository.saveAndFlush(mEncountersCut);

        // Get all the mEncountersCutList where ballFloatCondition in DEFAULT_BALL_FLOAT_CONDITION or UPDATED_BALL_FLOAT_CONDITION
        defaultMEncountersCutShouldBeFound("ballFloatCondition.in=" + DEFAULT_BALL_FLOAT_CONDITION + "," + UPDATED_BALL_FLOAT_CONDITION);

        // Get all the mEncountersCutList where ballFloatCondition equals to UPDATED_BALL_FLOAT_CONDITION
        defaultMEncountersCutShouldNotBeFound("ballFloatCondition.in=" + UPDATED_BALL_FLOAT_CONDITION);
    }

    @Test
    @Transactional
    public void getAllMEncountersCutsByBallFloatConditionIsNullOrNotNull() throws Exception {
        // Initialize the database
        mEncountersCutRepository.saveAndFlush(mEncountersCut);

        // Get all the mEncountersCutList where ballFloatCondition is not null
        defaultMEncountersCutShouldBeFound("ballFloatCondition.specified=true");

        // Get all the mEncountersCutList where ballFloatCondition is null
        defaultMEncountersCutShouldNotBeFound("ballFloatCondition.specified=false");
    }

    @Test
    @Transactional
    public void getAllMEncountersCutsByBallFloatConditionIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mEncountersCutRepository.saveAndFlush(mEncountersCut);

        // Get all the mEncountersCutList where ballFloatCondition greater than or equals to DEFAULT_BALL_FLOAT_CONDITION
        defaultMEncountersCutShouldBeFound("ballFloatCondition.greaterOrEqualThan=" + DEFAULT_BALL_FLOAT_CONDITION);

        // Get all the mEncountersCutList where ballFloatCondition greater than or equals to UPDATED_BALL_FLOAT_CONDITION
        defaultMEncountersCutShouldNotBeFound("ballFloatCondition.greaterOrEqualThan=" + UPDATED_BALL_FLOAT_CONDITION);
    }

    @Test
    @Transactional
    public void getAllMEncountersCutsByBallFloatConditionIsLessThanSomething() throws Exception {
        // Initialize the database
        mEncountersCutRepository.saveAndFlush(mEncountersCut);

        // Get all the mEncountersCutList where ballFloatCondition less than or equals to DEFAULT_BALL_FLOAT_CONDITION
        defaultMEncountersCutShouldNotBeFound("ballFloatCondition.lessThan=" + DEFAULT_BALL_FLOAT_CONDITION);

        // Get all the mEncountersCutList where ballFloatCondition less than or equals to UPDATED_BALL_FLOAT_CONDITION
        defaultMEncountersCutShouldBeFound("ballFloatCondition.lessThan=" + UPDATED_BALL_FLOAT_CONDITION);
    }


    @Test
    @Transactional
    public void getAllMEncountersCutsByCommand1TypeIsEqualToSomething() throws Exception {
        // Initialize the database
        mEncountersCutRepository.saveAndFlush(mEncountersCut);

        // Get all the mEncountersCutList where command1Type equals to DEFAULT_COMMAND_1_TYPE
        defaultMEncountersCutShouldBeFound("command1Type.equals=" + DEFAULT_COMMAND_1_TYPE);

        // Get all the mEncountersCutList where command1Type equals to UPDATED_COMMAND_1_TYPE
        defaultMEncountersCutShouldNotBeFound("command1Type.equals=" + UPDATED_COMMAND_1_TYPE);
    }

    @Test
    @Transactional
    public void getAllMEncountersCutsByCommand1TypeIsInShouldWork() throws Exception {
        // Initialize the database
        mEncountersCutRepository.saveAndFlush(mEncountersCut);

        // Get all the mEncountersCutList where command1Type in DEFAULT_COMMAND_1_TYPE or UPDATED_COMMAND_1_TYPE
        defaultMEncountersCutShouldBeFound("command1Type.in=" + DEFAULT_COMMAND_1_TYPE + "," + UPDATED_COMMAND_1_TYPE);

        // Get all the mEncountersCutList where command1Type equals to UPDATED_COMMAND_1_TYPE
        defaultMEncountersCutShouldNotBeFound("command1Type.in=" + UPDATED_COMMAND_1_TYPE);
    }

    @Test
    @Transactional
    public void getAllMEncountersCutsByCommand1TypeIsNullOrNotNull() throws Exception {
        // Initialize the database
        mEncountersCutRepository.saveAndFlush(mEncountersCut);

        // Get all the mEncountersCutList where command1Type is not null
        defaultMEncountersCutShouldBeFound("command1Type.specified=true");

        // Get all the mEncountersCutList where command1Type is null
        defaultMEncountersCutShouldNotBeFound("command1Type.specified=false");
    }

    @Test
    @Transactional
    public void getAllMEncountersCutsByCommand1TypeIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mEncountersCutRepository.saveAndFlush(mEncountersCut);

        // Get all the mEncountersCutList where command1Type greater than or equals to DEFAULT_COMMAND_1_TYPE
        defaultMEncountersCutShouldBeFound("command1Type.greaterOrEqualThan=" + DEFAULT_COMMAND_1_TYPE);

        // Get all the mEncountersCutList where command1Type greater than or equals to UPDATED_COMMAND_1_TYPE
        defaultMEncountersCutShouldNotBeFound("command1Type.greaterOrEqualThan=" + UPDATED_COMMAND_1_TYPE);
    }

    @Test
    @Transactional
    public void getAllMEncountersCutsByCommand1TypeIsLessThanSomething() throws Exception {
        // Initialize the database
        mEncountersCutRepository.saveAndFlush(mEncountersCut);

        // Get all the mEncountersCutList where command1Type less than or equals to DEFAULT_COMMAND_1_TYPE
        defaultMEncountersCutShouldNotBeFound("command1Type.lessThan=" + DEFAULT_COMMAND_1_TYPE);

        // Get all the mEncountersCutList where command1Type less than or equals to UPDATED_COMMAND_1_TYPE
        defaultMEncountersCutShouldBeFound("command1Type.lessThan=" + UPDATED_COMMAND_1_TYPE);
    }


    @Test
    @Transactional
    public void getAllMEncountersCutsByCommand1IsSkillIsEqualToSomething() throws Exception {
        // Initialize the database
        mEncountersCutRepository.saveAndFlush(mEncountersCut);

        // Get all the mEncountersCutList where command1IsSkill equals to DEFAULT_COMMAND_1_IS_SKILL
        defaultMEncountersCutShouldBeFound("command1IsSkill.equals=" + DEFAULT_COMMAND_1_IS_SKILL);

        // Get all the mEncountersCutList where command1IsSkill equals to UPDATED_COMMAND_1_IS_SKILL
        defaultMEncountersCutShouldNotBeFound("command1IsSkill.equals=" + UPDATED_COMMAND_1_IS_SKILL);
    }

    @Test
    @Transactional
    public void getAllMEncountersCutsByCommand1IsSkillIsInShouldWork() throws Exception {
        // Initialize the database
        mEncountersCutRepository.saveAndFlush(mEncountersCut);

        // Get all the mEncountersCutList where command1IsSkill in DEFAULT_COMMAND_1_IS_SKILL or UPDATED_COMMAND_1_IS_SKILL
        defaultMEncountersCutShouldBeFound("command1IsSkill.in=" + DEFAULT_COMMAND_1_IS_SKILL + "," + UPDATED_COMMAND_1_IS_SKILL);

        // Get all the mEncountersCutList where command1IsSkill equals to UPDATED_COMMAND_1_IS_SKILL
        defaultMEncountersCutShouldNotBeFound("command1IsSkill.in=" + UPDATED_COMMAND_1_IS_SKILL);
    }

    @Test
    @Transactional
    public void getAllMEncountersCutsByCommand1IsSkillIsNullOrNotNull() throws Exception {
        // Initialize the database
        mEncountersCutRepository.saveAndFlush(mEncountersCut);

        // Get all the mEncountersCutList where command1IsSkill is not null
        defaultMEncountersCutShouldBeFound("command1IsSkill.specified=true");

        // Get all the mEncountersCutList where command1IsSkill is null
        defaultMEncountersCutShouldNotBeFound("command1IsSkill.specified=false");
    }

    @Test
    @Transactional
    public void getAllMEncountersCutsByCommand1IsSkillIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mEncountersCutRepository.saveAndFlush(mEncountersCut);

        // Get all the mEncountersCutList where command1IsSkill greater than or equals to DEFAULT_COMMAND_1_IS_SKILL
        defaultMEncountersCutShouldBeFound("command1IsSkill.greaterOrEqualThan=" + DEFAULT_COMMAND_1_IS_SKILL);

        // Get all the mEncountersCutList where command1IsSkill greater than or equals to UPDATED_COMMAND_1_IS_SKILL
        defaultMEncountersCutShouldNotBeFound("command1IsSkill.greaterOrEqualThan=" + UPDATED_COMMAND_1_IS_SKILL);
    }

    @Test
    @Transactional
    public void getAllMEncountersCutsByCommand1IsSkillIsLessThanSomething() throws Exception {
        // Initialize the database
        mEncountersCutRepository.saveAndFlush(mEncountersCut);

        // Get all the mEncountersCutList where command1IsSkill less than or equals to DEFAULT_COMMAND_1_IS_SKILL
        defaultMEncountersCutShouldNotBeFound("command1IsSkill.lessThan=" + DEFAULT_COMMAND_1_IS_SKILL);

        // Get all the mEncountersCutList where command1IsSkill less than or equals to UPDATED_COMMAND_1_IS_SKILL
        defaultMEncountersCutShouldBeFound("command1IsSkill.lessThan=" + UPDATED_COMMAND_1_IS_SKILL);
    }


    @Test
    @Transactional
    public void getAllMEncountersCutsByCommand2TypeIsEqualToSomething() throws Exception {
        // Initialize the database
        mEncountersCutRepository.saveAndFlush(mEncountersCut);

        // Get all the mEncountersCutList where command2Type equals to DEFAULT_COMMAND_2_TYPE
        defaultMEncountersCutShouldBeFound("command2Type.equals=" + DEFAULT_COMMAND_2_TYPE);

        // Get all the mEncountersCutList where command2Type equals to UPDATED_COMMAND_2_TYPE
        defaultMEncountersCutShouldNotBeFound("command2Type.equals=" + UPDATED_COMMAND_2_TYPE);
    }

    @Test
    @Transactional
    public void getAllMEncountersCutsByCommand2TypeIsInShouldWork() throws Exception {
        // Initialize the database
        mEncountersCutRepository.saveAndFlush(mEncountersCut);

        // Get all the mEncountersCutList where command2Type in DEFAULT_COMMAND_2_TYPE or UPDATED_COMMAND_2_TYPE
        defaultMEncountersCutShouldBeFound("command2Type.in=" + DEFAULT_COMMAND_2_TYPE + "," + UPDATED_COMMAND_2_TYPE);

        // Get all the mEncountersCutList where command2Type equals to UPDATED_COMMAND_2_TYPE
        defaultMEncountersCutShouldNotBeFound("command2Type.in=" + UPDATED_COMMAND_2_TYPE);
    }

    @Test
    @Transactional
    public void getAllMEncountersCutsByCommand2TypeIsNullOrNotNull() throws Exception {
        // Initialize the database
        mEncountersCutRepository.saveAndFlush(mEncountersCut);

        // Get all the mEncountersCutList where command2Type is not null
        defaultMEncountersCutShouldBeFound("command2Type.specified=true");

        // Get all the mEncountersCutList where command2Type is null
        defaultMEncountersCutShouldNotBeFound("command2Type.specified=false");
    }

    @Test
    @Transactional
    public void getAllMEncountersCutsByCommand2TypeIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mEncountersCutRepository.saveAndFlush(mEncountersCut);

        // Get all the mEncountersCutList where command2Type greater than or equals to DEFAULT_COMMAND_2_TYPE
        defaultMEncountersCutShouldBeFound("command2Type.greaterOrEqualThan=" + DEFAULT_COMMAND_2_TYPE);

        // Get all the mEncountersCutList where command2Type greater than or equals to UPDATED_COMMAND_2_TYPE
        defaultMEncountersCutShouldNotBeFound("command2Type.greaterOrEqualThan=" + UPDATED_COMMAND_2_TYPE);
    }

    @Test
    @Transactional
    public void getAllMEncountersCutsByCommand2TypeIsLessThanSomething() throws Exception {
        // Initialize the database
        mEncountersCutRepository.saveAndFlush(mEncountersCut);

        // Get all the mEncountersCutList where command2Type less than or equals to DEFAULT_COMMAND_2_TYPE
        defaultMEncountersCutShouldNotBeFound("command2Type.lessThan=" + DEFAULT_COMMAND_2_TYPE);

        // Get all the mEncountersCutList where command2Type less than or equals to UPDATED_COMMAND_2_TYPE
        defaultMEncountersCutShouldBeFound("command2Type.lessThan=" + UPDATED_COMMAND_2_TYPE);
    }


    @Test
    @Transactional
    public void getAllMEncountersCutsByCommand2IsSkillIsEqualToSomething() throws Exception {
        // Initialize the database
        mEncountersCutRepository.saveAndFlush(mEncountersCut);

        // Get all the mEncountersCutList where command2IsSkill equals to DEFAULT_COMMAND_2_IS_SKILL
        defaultMEncountersCutShouldBeFound("command2IsSkill.equals=" + DEFAULT_COMMAND_2_IS_SKILL);

        // Get all the mEncountersCutList where command2IsSkill equals to UPDATED_COMMAND_2_IS_SKILL
        defaultMEncountersCutShouldNotBeFound("command2IsSkill.equals=" + UPDATED_COMMAND_2_IS_SKILL);
    }

    @Test
    @Transactional
    public void getAllMEncountersCutsByCommand2IsSkillIsInShouldWork() throws Exception {
        // Initialize the database
        mEncountersCutRepository.saveAndFlush(mEncountersCut);

        // Get all the mEncountersCutList where command2IsSkill in DEFAULT_COMMAND_2_IS_SKILL or UPDATED_COMMAND_2_IS_SKILL
        defaultMEncountersCutShouldBeFound("command2IsSkill.in=" + DEFAULT_COMMAND_2_IS_SKILL + "," + UPDATED_COMMAND_2_IS_SKILL);

        // Get all the mEncountersCutList where command2IsSkill equals to UPDATED_COMMAND_2_IS_SKILL
        defaultMEncountersCutShouldNotBeFound("command2IsSkill.in=" + UPDATED_COMMAND_2_IS_SKILL);
    }

    @Test
    @Transactional
    public void getAllMEncountersCutsByCommand2IsSkillIsNullOrNotNull() throws Exception {
        // Initialize the database
        mEncountersCutRepository.saveAndFlush(mEncountersCut);

        // Get all the mEncountersCutList where command2IsSkill is not null
        defaultMEncountersCutShouldBeFound("command2IsSkill.specified=true");

        // Get all the mEncountersCutList where command2IsSkill is null
        defaultMEncountersCutShouldNotBeFound("command2IsSkill.specified=false");
    }

    @Test
    @Transactional
    public void getAllMEncountersCutsByCommand2IsSkillIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mEncountersCutRepository.saveAndFlush(mEncountersCut);

        // Get all the mEncountersCutList where command2IsSkill greater than or equals to DEFAULT_COMMAND_2_IS_SKILL
        defaultMEncountersCutShouldBeFound("command2IsSkill.greaterOrEqualThan=" + DEFAULT_COMMAND_2_IS_SKILL);

        // Get all the mEncountersCutList where command2IsSkill greater than or equals to UPDATED_COMMAND_2_IS_SKILL
        defaultMEncountersCutShouldNotBeFound("command2IsSkill.greaterOrEqualThan=" + UPDATED_COMMAND_2_IS_SKILL);
    }

    @Test
    @Transactional
    public void getAllMEncountersCutsByCommand2IsSkillIsLessThanSomething() throws Exception {
        // Initialize the database
        mEncountersCutRepository.saveAndFlush(mEncountersCut);

        // Get all the mEncountersCutList where command2IsSkill less than or equals to DEFAULT_COMMAND_2_IS_SKILL
        defaultMEncountersCutShouldNotBeFound("command2IsSkill.lessThan=" + DEFAULT_COMMAND_2_IS_SKILL);

        // Get all the mEncountersCutList where command2IsSkill less than or equals to UPDATED_COMMAND_2_IS_SKILL
        defaultMEncountersCutShouldBeFound("command2IsSkill.lessThan=" + UPDATED_COMMAND_2_IS_SKILL);
    }


    @Test
    @Transactional
    public void getAllMEncountersCutsByResultIsEqualToSomething() throws Exception {
        // Initialize the database
        mEncountersCutRepository.saveAndFlush(mEncountersCut);

        // Get all the mEncountersCutList where result equals to DEFAULT_RESULT
        defaultMEncountersCutShouldBeFound("result.equals=" + DEFAULT_RESULT);

        // Get all the mEncountersCutList where result equals to UPDATED_RESULT
        defaultMEncountersCutShouldNotBeFound("result.equals=" + UPDATED_RESULT);
    }

    @Test
    @Transactional
    public void getAllMEncountersCutsByResultIsInShouldWork() throws Exception {
        // Initialize the database
        mEncountersCutRepository.saveAndFlush(mEncountersCut);

        // Get all the mEncountersCutList where result in DEFAULT_RESULT or UPDATED_RESULT
        defaultMEncountersCutShouldBeFound("result.in=" + DEFAULT_RESULT + "," + UPDATED_RESULT);

        // Get all the mEncountersCutList where result equals to UPDATED_RESULT
        defaultMEncountersCutShouldNotBeFound("result.in=" + UPDATED_RESULT);
    }

    @Test
    @Transactional
    public void getAllMEncountersCutsByResultIsNullOrNotNull() throws Exception {
        // Initialize the database
        mEncountersCutRepository.saveAndFlush(mEncountersCut);

        // Get all the mEncountersCutList where result is not null
        defaultMEncountersCutShouldBeFound("result.specified=true");

        // Get all the mEncountersCutList where result is null
        defaultMEncountersCutShouldNotBeFound("result.specified=false");
    }

    @Test
    @Transactional
    public void getAllMEncountersCutsByResultIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mEncountersCutRepository.saveAndFlush(mEncountersCut);

        // Get all the mEncountersCutList where result greater than or equals to DEFAULT_RESULT
        defaultMEncountersCutShouldBeFound("result.greaterOrEqualThan=" + DEFAULT_RESULT);

        // Get all the mEncountersCutList where result greater than or equals to UPDATED_RESULT
        defaultMEncountersCutShouldNotBeFound("result.greaterOrEqualThan=" + UPDATED_RESULT);
    }

    @Test
    @Transactional
    public void getAllMEncountersCutsByResultIsLessThanSomething() throws Exception {
        // Initialize the database
        mEncountersCutRepository.saveAndFlush(mEncountersCut);

        // Get all the mEncountersCutList where result less than or equals to DEFAULT_RESULT
        defaultMEncountersCutShouldNotBeFound("result.lessThan=" + DEFAULT_RESULT);

        // Get all the mEncountersCutList where result less than or equals to UPDATED_RESULT
        defaultMEncountersCutShouldBeFound("result.lessThan=" + UPDATED_RESULT);
    }


    @Test
    @Transactional
    public void getAllMEncountersCutsByShootResultIsEqualToSomething() throws Exception {
        // Initialize the database
        mEncountersCutRepository.saveAndFlush(mEncountersCut);

        // Get all the mEncountersCutList where shootResult equals to DEFAULT_SHOOT_RESULT
        defaultMEncountersCutShouldBeFound("shootResult.equals=" + DEFAULT_SHOOT_RESULT);

        // Get all the mEncountersCutList where shootResult equals to UPDATED_SHOOT_RESULT
        defaultMEncountersCutShouldNotBeFound("shootResult.equals=" + UPDATED_SHOOT_RESULT);
    }

    @Test
    @Transactional
    public void getAllMEncountersCutsByShootResultIsInShouldWork() throws Exception {
        // Initialize the database
        mEncountersCutRepository.saveAndFlush(mEncountersCut);

        // Get all the mEncountersCutList where shootResult in DEFAULT_SHOOT_RESULT or UPDATED_SHOOT_RESULT
        defaultMEncountersCutShouldBeFound("shootResult.in=" + DEFAULT_SHOOT_RESULT + "," + UPDATED_SHOOT_RESULT);

        // Get all the mEncountersCutList where shootResult equals to UPDATED_SHOOT_RESULT
        defaultMEncountersCutShouldNotBeFound("shootResult.in=" + UPDATED_SHOOT_RESULT);
    }

    @Test
    @Transactional
    public void getAllMEncountersCutsByShootResultIsNullOrNotNull() throws Exception {
        // Initialize the database
        mEncountersCutRepository.saveAndFlush(mEncountersCut);

        // Get all the mEncountersCutList where shootResult is not null
        defaultMEncountersCutShouldBeFound("shootResult.specified=true");

        // Get all the mEncountersCutList where shootResult is null
        defaultMEncountersCutShouldNotBeFound("shootResult.specified=false");
    }

    @Test
    @Transactional
    public void getAllMEncountersCutsByShootResultIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mEncountersCutRepository.saveAndFlush(mEncountersCut);

        // Get all the mEncountersCutList where shootResult greater than or equals to DEFAULT_SHOOT_RESULT
        defaultMEncountersCutShouldBeFound("shootResult.greaterOrEqualThan=" + DEFAULT_SHOOT_RESULT);

        // Get all the mEncountersCutList where shootResult greater than or equals to UPDATED_SHOOT_RESULT
        defaultMEncountersCutShouldNotBeFound("shootResult.greaterOrEqualThan=" + UPDATED_SHOOT_RESULT);
    }

    @Test
    @Transactional
    public void getAllMEncountersCutsByShootResultIsLessThanSomething() throws Exception {
        // Initialize the database
        mEncountersCutRepository.saveAndFlush(mEncountersCut);

        // Get all the mEncountersCutList where shootResult less than or equals to DEFAULT_SHOOT_RESULT
        defaultMEncountersCutShouldNotBeFound("shootResult.lessThan=" + DEFAULT_SHOOT_RESULT);

        // Get all the mEncountersCutList where shootResult less than or equals to UPDATED_SHOOT_RESULT
        defaultMEncountersCutShouldBeFound("shootResult.lessThan=" + UPDATED_SHOOT_RESULT);
    }


    @Test
    @Transactional
    public void getAllMEncountersCutsByIsThrownIsEqualToSomething() throws Exception {
        // Initialize the database
        mEncountersCutRepository.saveAndFlush(mEncountersCut);

        // Get all the mEncountersCutList where isThrown equals to DEFAULT_IS_THROWN
        defaultMEncountersCutShouldBeFound("isThrown.equals=" + DEFAULT_IS_THROWN);

        // Get all the mEncountersCutList where isThrown equals to UPDATED_IS_THROWN
        defaultMEncountersCutShouldNotBeFound("isThrown.equals=" + UPDATED_IS_THROWN);
    }

    @Test
    @Transactional
    public void getAllMEncountersCutsByIsThrownIsInShouldWork() throws Exception {
        // Initialize the database
        mEncountersCutRepository.saveAndFlush(mEncountersCut);

        // Get all the mEncountersCutList where isThrown in DEFAULT_IS_THROWN or UPDATED_IS_THROWN
        defaultMEncountersCutShouldBeFound("isThrown.in=" + DEFAULT_IS_THROWN + "," + UPDATED_IS_THROWN);

        // Get all the mEncountersCutList where isThrown equals to UPDATED_IS_THROWN
        defaultMEncountersCutShouldNotBeFound("isThrown.in=" + UPDATED_IS_THROWN);
    }

    @Test
    @Transactional
    public void getAllMEncountersCutsByIsThrownIsNullOrNotNull() throws Exception {
        // Initialize the database
        mEncountersCutRepository.saveAndFlush(mEncountersCut);

        // Get all the mEncountersCutList where isThrown is not null
        defaultMEncountersCutShouldBeFound("isThrown.specified=true");

        // Get all the mEncountersCutList where isThrown is null
        defaultMEncountersCutShouldNotBeFound("isThrown.specified=false");
    }

    @Test
    @Transactional
    public void getAllMEncountersCutsByIsThrownIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mEncountersCutRepository.saveAndFlush(mEncountersCut);

        // Get all the mEncountersCutList where isThrown greater than or equals to DEFAULT_IS_THROWN
        defaultMEncountersCutShouldBeFound("isThrown.greaterOrEqualThan=" + DEFAULT_IS_THROWN);

        // Get all the mEncountersCutList where isThrown greater than or equals to UPDATED_IS_THROWN
        defaultMEncountersCutShouldNotBeFound("isThrown.greaterOrEqualThan=" + UPDATED_IS_THROWN);
    }

    @Test
    @Transactional
    public void getAllMEncountersCutsByIsThrownIsLessThanSomething() throws Exception {
        // Initialize the database
        mEncountersCutRepository.saveAndFlush(mEncountersCut);

        // Get all the mEncountersCutList where isThrown less than or equals to DEFAULT_IS_THROWN
        defaultMEncountersCutShouldNotBeFound("isThrown.lessThan=" + DEFAULT_IS_THROWN);

        // Get all the mEncountersCutList where isThrown less than or equals to UPDATED_IS_THROWN
        defaultMEncountersCutShouldBeFound("isThrown.lessThan=" + UPDATED_IS_THROWN);
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultMEncountersCutShouldBeFound(String filter) throws Exception {
        restMEncountersCutMockMvc.perform(get("/api/m-encounters-cuts?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(mEncountersCut.getId().intValue())))
            .andExpect(jsonPath("$.[*].condition").value(hasItem(DEFAULT_CONDITION)))
            .andExpect(jsonPath("$.[*].ballFloatCondition").value(hasItem(DEFAULT_BALL_FLOAT_CONDITION)))
            .andExpect(jsonPath("$.[*].command1Type").value(hasItem(DEFAULT_COMMAND_1_TYPE)))
            .andExpect(jsonPath("$.[*].command1IsSkill").value(hasItem(DEFAULT_COMMAND_1_IS_SKILL)))
            .andExpect(jsonPath("$.[*].command2Type").value(hasItem(DEFAULT_COMMAND_2_TYPE)))
            .andExpect(jsonPath("$.[*].command2IsSkill").value(hasItem(DEFAULT_COMMAND_2_IS_SKILL)))
            .andExpect(jsonPath("$.[*].result").value(hasItem(DEFAULT_RESULT)))
            .andExpect(jsonPath("$.[*].shootResult").value(hasItem(DEFAULT_SHOOT_RESULT)))
            .andExpect(jsonPath("$.[*].isThrown").value(hasItem(DEFAULT_IS_THROWN)))
            .andExpect(jsonPath("$.[*].offenseSequenceText").value(hasItem(DEFAULT_OFFENSE_SEQUENCE_TEXT.toString())))
            .andExpect(jsonPath("$.[*].defenseSequenceText").value(hasItem(DEFAULT_DEFENSE_SEQUENCE_TEXT.toString())));

        // Check, that the count call also returns 1
        restMEncountersCutMockMvc.perform(get("/api/m-encounters-cuts/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultMEncountersCutShouldNotBeFound(String filter) throws Exception {
        restMEncountersCutMockMvc.perform(get("/api/m-encounters-cuts?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restMEncountersCutMockMvc.perform(get("/api/m-encounters-cuts/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("0"));
    }


    @Test
    @Transactional
    public void getNonExistingMEncountersCut() throws Exception {
        // Get the mEncountersCut
        restMEncountersCutMockMvc.perform(get("/api/m-encounters-cuts/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateMEncountersCut() throws Exception {
        // Initialize the database
        mEncountersCutRepository.saveAndFlush(mEncountersCut);

        int databaseSizeBeforeUpdate = mEncountersCutRepository.findAll().size();

        // Update the mEncountersCut
        MEncountersCut updatedMEncountersCut = mEncountersCutRepository.findById(mEncountersCut.getId()).get();
        // Disconnect from session so that the updates on updatedMEncountersCut are not directly saved in db
        em.detach(updatedMEncountersCut);
        updatedMEncountersCut
            .condition(UPDATED_CONDITION)
            .ballFloatCondition(UPDATED_BALL_FLOAT_CONDITION)
            .command1Type(UPDATED_COMMAND_1_TYPE)
            .command1IsSkill(UPDATED_COMMAND_1_IS_SKILL)
            .command2Type(UPDATED_COMMAND_2_TYPE)
            .command2IsSkill(UPDATED_COMMAND_2_IS_SKILL)
            .result(UPDATED_RESULT)
            .shootResult(UPDATED_SHOOT_RESULT)
            .isThrown(UPDATED_IS_THROWN)
            .offenseSequenceText(UPDATED_OFFENSE_SEQUENCE_TEXT)
            .defenseSequenceText(UPDATED_DEFENSE_SEQUENCE_TEXT);
        MEncountersCutDTO mEncountersCutDTO = mEncountersCutMapper.toDto(updatedMEncountersCut);

        restMEncountersCutMockMvc.perform(put("/api/m-encounters-cuts")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mEncountersCutDTO)))
            .andExpect(status().isOk());

        // Validate the MEncountersCut in the database
        List<MEncountersCut> mEncountersCutList = mEncountersCutRepository.findAll();
        assertThat(mEncountersCutList).hasSize(databaseSizeBeforeUpdate);
        MEncountersCut testMEncountersCut = mEncountersCutList.get(mEncountersCutList.size() - 1);
        assertThat(testMEncountersCut.getCondition()).isEqualTo(UPDATED_CONDITION);
        assertThat(testMEncountersCut.getBallFloatCondition()).isEqualTo(UPDATED_BALL_FLOAT_CONDITION);
        assertThat(testMEncountersCut.getCommand1Type()).isEqualTo(UPDATED_COMMAND_1_TYPE);
        assertThat(testMEncountersCut.getCommand1IsSkill()).isEqualTo(UPDATED_COMMAND_1_IS_SKILL);
        assertThat(testMEncountersCut.getCommand2Type()).isEqualTo(UPDATED_COMMAND_2_TYPE);
        assertThat(testMEncountersCut.getCommand2IsSkill()).isEqualTo(UPDATED_COMMAND_2_IS_SKILL);
        assertThat(testMEncountersCut.getResult()).isEqualTo(UPDATED_RESULT);
        assertThat(testMEncountersCut.getShootResult()).isEqualTo(UPDATED_SHOOT_RESULT);
        assertThat(testMEncountersCut.getIsThrown()).isEqualTo(UPDATED_IS_THROWN);
        assertThat(testMEncountersCut.getOffenseSequenceText()).isEqualTo(UPDATED_OFFENSE_SEQUENCE_TEXT);
        assertThat(testMEncountersCut.getDefenseSequenceText()).isEqualTo(UPDATED_DEFENSE_SEQUENCE_TEXT);
    }

    @Test
    @Transactional
    public void updateNonExistingMEncountersCut() throws Exception {
        int databaseSizeBeforeUpdate = mEncountersCutRepository.findAll().size();

        // Create the MEncountersCut
        MEncountersCutDTO mEncountersCutDTO = mEncountersCutMapper.toDto(mEncountersCut);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restMEncountersCutMockMvc.perform(put("/api/m-encounters-cuts")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mEncountersCutDTO)))
            .andExpect(status().isBadRequest());

        // Validate the MEncountersCut in the database
        List<MEncountersCut> mEncountersCutList = mEncountersCutRepository.findAll();
        assertThat(mEncountersCutList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteMEncountersCut() throws Exception {
        // Initialize the database
        mEncountersCutRepository.saveAndFlush(mEncountersCut);

        int databaseSizeBeforeDelete = mEncountersCutRepository.findAll().size();

        // Delete the mEncountersCut
        restMEncountersCutMockMvc.perform(delete("/api/m-encounters-cuts/{id}", mEncountersCut.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database is empty
        List<MEncountersCut> mEncountersCutList = mEncountersCutRepository.findAll();
        assertThat(mEncountersCutList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(MEncountersCut.class);
        MEncountersCut mEncountersCut1 = new MEncountersCut();
        mEncountersCut1.setId(1L);
        MEncountersCut mEncountersCut2 = new MEncountersCut();
        mEncountersCut2.setId(mEncountersCut1.getId());
        assertThat(mEncountersCut1).isEqualTo(mEncountersCut2);
        mEncountersCut2.setId(2L);
        assertThat(mEncountersCut1).isNotEqualTo(mEncountersCut2);
        mEncountersCut1.setId(null);
        assertThat(mEncountersCut1).isNotEqualTo(mEncountersCut2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(MEncountersCutDTO.class);
        MEncountersCutDTO mEncountersCutDTO1 = new MEncountersCutDTO();
        mEncountersCutDTO1.setId(1L);
        MEncountersCutDTO mEncountersCutDTO2 = new MEncountersCutDTO();
        assertThat(mEncountersCutDTO1).isNotEqualTo(mEncountersCutDTO2);
        mEncountersCutDTO2.setId(mEncountersCutDTO1.getId());
        assertThat(mEncountersCutDTO1).isEqualTo(mEncountersCutDTO2);
        mEncountersCutDTO2.setId(2L);
        assertThat(mEncountersCutDTO1).isNotEqualTo(mEncountersCutDTO2);
        mEncountersCutDTO1.setId(null);
        assertThat(mEncountersCutDTO1).isNotEqualTo(mEncountersCutDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(mEncountersCutMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(mEncountersCutMapper.fromId(null)).isNull();
    }
}
