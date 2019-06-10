package io.shm.tsubasa.web.rest;

import io.shm.tsubasa.TsubasaApp;
import io.shm.tsubasa.domain.MAction;
import io.shm.tsubasa.domain.MTargetActionGroup;
import io.shm.tsubasa.repository.MActionRepository;
import io.shm.tsubasa.service.MActionService;
import io.shm.tsubasa.service.dto.MActionDTO;
import io.shm.tsubasa.service.mapper.MActionMapper;
import io.shm.tsubasa.web.rest.errors.ExceptionTranslator;
import io.shm.tsubasa.service.dto.MActionCriteria;
import io.shm.tsubasa.service.MActionQueryService;

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
 * Integration tests for the {@Link MActionResource} REST controller.
 */
@SpringBootTest(classes = TsubasaApp.class)
public class MActionResourceIT {

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_SHORT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_SHORT_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_NAME_RUBY = "AAAAAAAAAA";
    private static final String UPDATED_NAME_RUBY = "BBBBBBBBBB";

    private static final String DEFAULT_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPTION = "BBBBBBBBBB";

    private static final String DEFAULT_EFFECT_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_EFFECT_DESCRIPTION = "BBBBBBBBBB";

    private static final Integer DEFAULT_INITIAL_COMMAND = 1;
    private static final Integer UPDATED_INITIAL_COMMAND = 2;

    private static final Integer DEFAULT_RARITY = 1;
    private static final Integer UPDATED_RARITY = 2;

    private static final Integer DEFAULT_COMMAND_TYPE = 1;
    private static final Integer UPDATED_COMMAND_TYPE = 2;

    private static final Integer DEFAULT_BALL_CONDITION_GROUND = 1;
    private static final Integer UPDATED_BALL_CONDITION_GROUND = 2;

    private static final Integer DEFAULT_BALL_CONDITION_LOW = 1;
    private static final Integer UPDATED_BALL_CONDITION_LOW = 2;

    private static final Integer DEFAULT_BALL_CONDITION_HIGH = 1;
    private static final Integer UPDATED_BALL_CONDITION_HIGH = 2;

    private static final Integer DEFAULT_STAMINA_LV_MIN = 1;
    private static final Integer UPDATED_STAMINA_LV_MIN = 2;

    private static final Integer DEFAULT_STAMINA_LV_MAX = 1;
    private static final Integer UPDATED_STAMINA_LV_MAX = 2;

    private static final Integer DEFAULT_POWER_LV_MIN = 1;
    private static final Integer UPDATED_POWER_LV_MIN = 2;

    private static final Integer DEFAULT_POWER_LV_MAX = 1;
    private static final Integer UPDATED_POWER_LV_MAX = 2;

    private static final Integer DEFAULT_BLOW_OFF_COUNT = 1;
    private static final Integer UPDATED_BLOW_OFF_COUNT = 2;

    private static final Integer DEFAULT_M_SHOOT_ID = 1;
    private static final Integer UPDATED_M_SHOOT_ID = 2;

    private static final Integer DEFAULT_FOUL_RATE = 1;
    private static final Integer UPDATED_FOUL_RATE = 2;

    private static final Integer DEFAULT_DISTANCE_DECAY_TYPE = 1;
    private static final Integer UPDATED_DISTANCE_DECAY_TYPE = 2;

    private static final Integer DEFAULT_ACTIVATE_CHARACTER_COUNT = 1;
    private static final Integer UPDATED_ACTIVATE_CHARACTER_COUNT = 2;

    private static final Integer DEFAULT_ACTION_CUT_ID = 1;
    private static final Integer UPDATED_ACTION_CUT_ID = 2;

    private static final Integer DEFAULT_POWERUP_GROUP = 1;
    private static final Integer UPDATED_POWERUP_GROUP = 2;

    @Autowired
    private MActionRepository mActionRepository;

    @Autowired
    private MActionMapper mActionMapper;

    @Autowired
    private MActionService mActionService;

    @Autowired
    private MActionQueryService mActionQueryService;

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

    private MockMvc restMActionMockMvc;

    private MAction mAction;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final MActionResource mActionResource = new MActionResource(mActionService, mActionQueryService);
        this.restMActionMockMvc = MockMvcBuilders.standaloneSetup(mActionResource)
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
    public static MAction createEntity(EntityManager em) {
        MAction mAction = new MAction()
            .name(DEFAULT_NAME)
            .shortName(DEFAULT_SHORT_NAME)
            .nameRuby(DEFAULT_NAME_RUBY)
            .description(DEFAULT_DESCRIPTION)
            .effectDescription(DEFAULT_EFFECT_DESCRIPTION)
            .initialCommand(DEFAULT_INITIAL_COMMAND)
            .rarity(DEFAULT_RARITY)
            .commandType(DEFAULT_COMMAND_TYPE)
            .ballConditionGround(DEFAULT_BALL_CONDITION_GROUND)
            .ballConditionLow(DEFAULT_BALL_CONDITION_LOW)
            .ballConditionHigh(DEFAULT_BALL_CONDITION_HIGH)
            .staminaLvMin(DEFAULT_STAMINA_LV_MIN)
            .staminaLvMax(DEFAULT_STAMINA_LV_MAX)
            .powerLvMin(DEFAULT_POWER_LV_MIN)
            .powerLvMax(DEFAULT_POWER_LV_MAX)
            .blowOffCount(DEFAULT_BLOW_OFF_COUNT)
            .mShootId(DEFAULT_M_SHOOT_ID)
            .foulRate(DEFAULT_FOUL_RATE)
            .distanceDecayType(DEFAULT_DISTANCE_DECAY_TYPE)
            .activateCharacterCount(DEFAULT_ACTIVATE_CHARACTER_COUNT)
            .actionCutId(DEFAULT_ACTION_CUT_ID)
            .powerupGroup(DEFAULT_POWERUP_GROUP);
        return mAction;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static MAction createUpdatedEntity(EntityManager em) {
        MAction mAction = new MAction()
            .name(UPDATED_NAME)
            .shortName(UPDATED_SHORT_NAME)
            .nameRuby(UPDATED_NAME_RUBY)
            .description(UPDATED_DESCRIPTION)
            .effectDescription(UPDATED_EFFECT_DESCRIPTION)
            .initialCommand(UPDATED_INITIAL_COMMAND)
            .rarity(UPDATED_RARITY)
            .commandType(UPDATED_COMMAND_TYPE)
            .ballConditionGround(UPDATED_BALL_CONDITION_GROUND)
            .ballConditionLow(UPDATED_BALL_CONDITION_LOW)
            .ballConditionHigh(UPDATED_BALL_CONDITION_HIGH)
            .staminaLvMin(UPDATED_STAMINA_LV_MIN)
            .staminaLvMax(UPDATED_STAMINA_LV_MAX)
            .powerLvMin(UPDATED_POWER_LV_MIN)
            .powerLvMax(UPDATED_POWER_LV_MAX)
            .blowOffCount(UPDATED_BLOW_OFF_COUNT)
            .mShootId(UPDATED_M_SHOOT_ID)
            .foulRate(UPDATED_FOUL_RATE)
            .distanceDecayType(UPDATED_DISTANCE_DECAY_TYPE)
            .activateCharacterCount(UPDATED_ACTIVATE_CHARACTER_COUNT)
            .actionCutId(UPDATED_ACTION_CUT_ID)
            .powerupGroup(UPDATED_POWERUP_GROUP);
        return mAction;
    }

    @BeforeEach
    public void initTest() {
        mAction = createEntity(em);
    }

    @Test
    @Transactional
    public void createMAction() throws Exception {
        int databaseSizeBeforeCreate = mActionRepository.findAll().size();

        // Create the MAction
        MActionDTO mActionDTO = mActionMapper.toDto(mAction);
        restMActionMockMvc.perform(post("/api/m-actions")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mActionDTO)))
            .andExpect(status().isCreated());

        // Validate the MAction in the database
        List<MAction> mActionList = mActionRepository.findAll();
        assertThat(mActionList).hasSize(databaseSizeBeforeCreate + 1);
        MAction testMAction = mActionList.get(mActionList.size() - 1);
        assertThat(testMAction.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testMAction.getShortName()).isEqualTo(DEFAULT_SHORT_NAME);
        assertThat(testMAction.getNameRuby()).isEqualTo(DEFAULT_NAME_RUBY);
        assertThat(testMAction.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
        assertThat(testMAction.getEffectDescription()).isEqualTo(DEFAULT_EFFECT_DESCRIPTION);
        assertThat(testMAction.getInitialCommand()).isEqualTo(DEFAULT_INITIAL_COMMAND);
        assertThat(testMAction.getRarity()).isEqualTo(DEFAULT_RARITY);
        assertThat(testMAction.getCommandType()).isEqualTo(DEFAULT_COMMAND_TYPE);
        assertThat(testMAction.getBallConditionGround()).isEqualTo(DEFAULT_BALL_CONDITION_GROUND);
        assertThat(testMAction.getBallConditionLow()).isEqualTo(DEFAULT_BALL_CONDITION_LOW);
        assertThat(testMAction.getBallConditionHigh()).isEqualTo(DEFAULT_BALL_CONDITION_HIGH);
        assertThat(testMAction.getStaminaLvMin()).isEqualTo(DEFAULT_STAMINA_LV_MIN);
        assertThat(testMAction.getStaminaLvMax()).isEqualTo(DEFAULT_STAMINA_LV_MAX);
        assertThat(testMAction.getPowerLvMin()).isEqualTo(DEFAULT_POWER_LV_MIN);
        assertThat(testMAction.getPowerLvMax()).isEqualTo(DEFAULT_POWER_LV_MAX);
        assertThat(testMAction.getBlowOffCount()).isEqualTo(DEFAULT_BLOW_OFF_COUNT);
        assertThat(testMAction.getmShootId()).isEqualTo(DEFAULT_M_SHOOT_ID);
        assertThat(testMAction.getFoulRate()).isEqualTo(DEFAULT_FOUL_RATE);
        assertThat(testMAction.getDistanceDecayType()).isEqualTo(DEFAULT_DISTANCE_DECAY_TYPE);
        assertThat(testMAction.getActivateCharacterCount()).isEqualTo(DEFAULT_ACTIVATE_CHARACTER_COUNT);
        assertThat(testMAction.getActionCutId()).isEqualTo(DEFAULT_ACTION_CUT_ID);
        assertThat(testMAction.getPowerupGroup()).isEqualTo(DEFAULT_POWERUP_GROUP);
    }

    @Test
    @Transactional
    public void createMActionWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = mActionRepository.findAll().size();

        // Create the MAction with an existing ID
        mAction.setId(1L);
        MActionDTO mActionDTO = mActionMapper.toDto(mAction);

        // An entity with an existing ID cannot be created, so this API call must fail
        restMActionMockMvc.perform(post("/api/m-actions")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mActionDTO)))
            .andExpect(status().isBadRequest());

        // Validate the MAction in the database
        List<MAction> mActionList = mActionRepository.findAll();
        assertThat(mActionList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkInitialCommandIsRequired() throws Exception {
        int databaseSizeBeforeTest = mActionRepository.findAll().size();
        // set the field null
        mAction.setInitialCommand(null);

        // Create the MAction, which fails.
        MActionDTO mActionDTO = mActionMapper.toDto(mAction);

        restMActionMockMvc.perform(post("/api/m-actions")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mActionDTO)))
            .andExpect(status().isBadRequest());

        List<MAction> mActionList = mActionRepository.findAll();
        assertThat(mActionList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkRarityIsRequired() throws Exception {
        int databaseSizeBeforeTest = mActionRepository.findAll().size();
        // set the field null
        mAction.setRarity(null);

        // Create the MAction, which fails.
        MActionDTO mActionDTO = mActionMapper.toDto(mAction);

        restMActionMockMvc.perform(post("/api/m-actions")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mActionDTO)))
            .andExpect(status().isBadRequest());

        List<MAction> mActionList = mActionRepository.findAll();
        assertThat(mActionList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkCommandTypeIsRequired() throws Exception {
        int databaseSizeBeforeTest = mActionRepository.findAll().size();
        // set the field null
        mAction.setCommandType(null);

        // Create the MAction, which fails.
        MActionDTO mActionDTO = mActionMapper.toDto(mAction);

        restMActionMockMvc.perform(post("/api/m-actions")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mActionDTO)))
            .andExpect(status().isBadRequest());

        List<MAction> mActionList = mActionRepository.findAll();
        assertThat(mActionList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkBallConditionGroundIsRequired() throws Exception {
        int databaseSizeBeforeTest = mActionRepository.findAll().size();
        // set the field null
        mAction.setBallConditionGround(null);

        // Create the MAction, which fails.
        MActionDTO mActionDTO = mActionMapper.toDto(mAction);

        restMActionMockMvc.perform(post("/api/m-actions")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mActionDTO)))
            .andExpect(status().isBadRequest());

        List<MAction> mActionList = mActionRepository.findAll();
        assertThat(mActionList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkBallConditionLowIsRequired() throws Exception {
        int databaseSizeBeforeTest = mActionRepository.findAll().size();
        // set the field null
        mAction.setBallConditionLow(null);

        // Create the MAction, which fails.
        MActionDTO mActionDTO = mActionMapper.toDto(mAction);

        restMActionMockMvc.perform(post("/api/m-actions")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mActionDTO)))
            .andExpect(status().isBadRequest());

        List<MAction> mActionList = mActionRepository.findAll();
        assertThat(mActionList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkBallConditionHighIsRequired() throws Exception {
        int databaseSizeBeforeTest = mActionRepository.findAll().size();
        // set the field null
        mAction.setBallConditionHigh(null);

        // Create the MAction, which fails.
        MActionDTO mActionDTO = mActionMapper.toDto(mAction);

        restMActionMockMvc.perform(post("/api/m-actions")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mActionDTO)))
            .andExpect(status().isBadRequest());

        List<MAction> mActionList = mActionRepository.findAll();
        assertThat(mActionList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkStaminaLvMinIsRequired() throws Exception {
        int databaseSizeBeforeTest = mActionRepository.findAll().size();
        // set the field null
        mAction.setStaminaLvMin(null);

        // Create the MAction, which fails.
        MActionDTO mActionDTO = mActionMapper.toDto(mAction);

        restMActionMockMvc.perform(post("/api/m-actions")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mActionDTO)))
            .andExpect(status().isBadRequest());

        List<MAction> mActionList = mActionRepository.findAll();
        assertThat(mActionList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkStaminaLvMaxIsRequired() throws Exception {
        int databaseSizeBeforeTest = mActionRepository.findAll().size();
        // set the field null
        mAction.setStaminaLvMax(null);

        // Create the MAction, which fails.
        MActionDTO mActionDTO = mActionMapper.toDto(mAction);

        restMActionMockMvc.perform(post("/api/m-actions")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mActionDTO)))
            .andExpect(status().isBadRequest());

        List<MAction> mActionList = mActionRepository.findAll();
        assertThat(mActionList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkPowerLvMinIsRequired() throws Exception {
        int databaseSizeBeforeTest = mActionRepository.findAll().size();
        // set the field null
        mAction.setPowerLvMin(null);

        // Create the MAction, which fails.
        MActionDTO mActionDTO = mActionMapper.toDto(mAction);

        restMActionMockMvc.perform(post("/api/m-actions")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mActionDTO)))
            .andExpect(status().isBadRequest());

        List<MAction> mActionList = mActionRepository.findAll();
        assertThat(mActionList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkPowerLvMaxIsRequired() throws Exception {
        int databaseSizeBeforeTest = mActionRepository.findAll().size();
        // set the field null
        mAction.setPowerLvMax(null);

        // Create the MAction, which fails.
        MActionDTO mActionDTO = mActionMapper.toDto(mAction);

        restMActionMockMvc.perform(post("/api/m-actions")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mActionDTO)))
            .andExpect(status().isBadRequest());

        List<MAction> mActionList = mActionRepository.findAll();
        assertThat(mActionList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkBlowOffCountIsRequired() throws Exception {
        int databaseSizeBeforeTest = mActionRepository.findAll().size();
        // set the field null
        mAction.setBlowOffCount(null);

        // Create the MAction, which fails.
        MActionDTO mActionDTO = mActionMapper.toDto(mAction);

        restMActionMockMvc.perform(post("/api/m-actions")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mActionDTO)))
            .andExpect(status().isBadRequest());

        List<MAction> mActionList = mActionRepository.findAll();
        assertThat(mActionList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkFoulRateIsRequired() throws Exception {
        int databaseSizeBeforeTest = mActionRepository.findAll().size();
        // set the field null
        mAction.setFoulRate(null);

        // Create the MAction, which fails.
        MActionDTO mActionDTO = mActionMapper.toDto(mAction);

        restMActionMockMvc.perform(post("/api/m-actions")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mActionDTO)))
            .andExpect(status().isBadRequest());

        List<MAction> mActionList = mActionRepository.findAll();
        assertThat(mActionList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkDistanceDecayTypeIsRequired() throws Exception {
        int databaseSizeBeforeTest = mActionRepository.findAll().size();
        // set the field null
        mAction.setDistanceDecayType(null);

        // Create the MAction, which fails.
        MActionDTO mActionDTO = mActionMapper.toDto(mAction);

        restMActionMockMvc.perform(post("/api/m-actions")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mActionDTO)))
            .andExpect(status().isBadRequest());

        List<MAction> mActionList = mActionRepository.findAll();
        assertThat(mActionList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkActivateCharacterCountIsRequired() throws Exception {
        int databaseSizeBeforeTest = mActionRepository.findAll().size();
        // set the field null
        mAction.setActivateCharacterCount(null);

        // Create the MAction, which fails.
        MActionDTO mActionDTO = mActionMapper.toDto(mAction);

        restMActionMockMvc.perform(post("/api/m-actions")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mActionDTO)))
            .andExpect(status().isBadRequest());

        List<MAction> mActionList = mActionRepository.findAll();
        assertThat(mActionList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkActionCutIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = mActionRepository.findAll().size();
        // set the field null
        mAction.setActionCutId(null);

        // Create the MAction, which fails.
        MActionDTO mActionDTO = mActionMapper.toDto(mAction);

        restMActionMockMvc.perform(post("/api/m-actions")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mActionDTO)))
            .andExpect(status().isBadRequest());

        List<MAction> mActionList = mActionRepository.findAll();
        assertThat(mActionList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllMActions() throws Exception {
        // Initialize the database
        mActionRepository.saveAndFlush(mAction);

        // Get all the mActionList
        restMActionMockMvc.perform(get("/api/m-actions?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(mAction.getId().intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME.toString())))
            .andExpect(jsonPath("$.[*].shortName").value(hasItem(DEFAULT_SHORT_NAME.toString())))
            .andExpect(jsonPath("$.[*].nameRuby").value(hasItem(DEFAULT_NAME_RUBY.toString())))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION.toString())))
            .andExpect(jsonPath("$.[*].effectDescription").value(hasItem(DEFAULT_EFFECT_DESCRIPTION.toString())))
            .andExpect(jsonPath("$.[*].initialCommand").value(hasItem(DEFAULT_INITIAL_COMMAND)))
            .andExpect(jsonPath("$.[*].rarity").value(hasItem(DEFAULT_RARITY)))
            .andExpect(jsonPath("$.[*].commandType").value(hasItem(DEFAULT_COMMAND_TYPE)))
            .andExpect(jsonPath("$.[*].ballConditionGround").value(hasItem(DEFAULT_BALL_CONDITION_GROUND)))
            .andExpect(jsonPath("$.[*].ballConditionLow").value(hasItem(DEFAULT_BALL_CONDITION_LOW)))
            .andExpect(jsonPath("$.[*].ballConditionHigh").value(hasItem(DEFAULT_BALL_CONDITION_HIGH)))
            .andExpect(jsonPath("$.[*].staminaLvMin").value(hasItem(DEFAULT_STAMINA_LV_MIN)))
            .andExpect(jsonPath("$.[*].staminaLvMax").value(hasItem(DEFAULT_STAMINA_LV_MAX)))
            .andExpect(jsonPath("$.[*].powerLvMin").value(hasItem(DEFAULT_POWER_LV_MIN)))
            .andExpect(jsonPath("$.[*].powerLvMax").value(hasItem(DEFAULT_POWER_LV_MAX)))
            .andExpect(jsonPath("$.[*].blowOffCount").value(hasItem(DEFAULT_BLOW_OFF_COUNT)))
            .andExpect(jsonPath("$.[*].mShootId").value(hasItem(DEFAULT_M_SHOOT_ID)))
            .andExpect(jsonPath("$.[*].foulRate").value(hasItem(DEFAULT_FOUL_RATE)))
            .andExpect(jsonPath("$.[*].distanceDecayType").value(hasItem(DEFAULT_DISTANCE_DECAY_TYPE)))
            .andExpect(jsonPath("$.[*].activateCharacterCount").value(hasItem(DEFAULT_ACTIVATE_CHARACTER_COUNT)))
            .andExpect(jsonPath("$.[*].actionCutId").value(hasItem(DEFAULT_ACTION_CUT_ID)))
            .andExpect(jsonPath("$.[*].powerupGroup").value(hasItem(DEFAULT_POWERUP_GROUP)));
    }
    
    @Test
    @Transactional
    public void getMAction() throws Exception {
        // Initialize the database
        mActionRepository.saveAndFlush(mAction);

        // Get the mAction
        restMActionMockMvc.perform(get("/api/m-actions/{id}", mAction.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(mAction.getId().intValue()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME.toString()))
            .andExpect(jsonPath("$.shortName").value(DEFAULT_SHORT_NAME.toString()))
            .andExpect(jsonPath("$.nameRuby").value(DEFAULT_NAME_RUBY.toString()))
            .andExpect(jsonPath("$.description").value(DEFAULT_DESCRIPTION.toString()))
            .andExpect(jsonPath("$.effectDescription").value(DEFAULT_EFFECT_DESCRIPTION.toString()))
            .andExpect(jsonPath("$.initialCommand").value(DEFAULT_INITIAL_COMMAND))
            .andExpect(jsonPath("$.rarity").value(DEFAULT_RARITY))
            .andExpect(jsonPath("$.commandType").value(DEFAULT_COMMAND_TYPE))
            .andExpect(jsonPath("$.ballConditionGround").value(DEFAULT_BALL_CONDITION_GROUND))
            .andExpect(jsonPath("$.ballConditionLow").value(DEFAULT_BALL_CONDITION_LOW))
            .andExpect(jsonPath("$.ballConditionHigh").value(DEFAULT_BALL_CONDITION_HIGH))
            .andExpect(jsonPath("$.staminaLvMin").value(DEFAULT_STAMINA_LV_MIN))
            .andExpect(jsonPath("$.staminaLvMax").value(DEFAULT_STAMINA_LV_MAX))
            .andExpect(jsonPath("$.powerLvMin").value(DEFAULT_POWER_LV_MIN))
            .andExpect(jsonPath("$.powerLvMax").value(DEFAULT_POWER_LV_MAX))
            .andExpect(jsonPath("$.blowOffCount").value(DEFAULT_BLOW_OFF_COUNT))
            .andExpect(jsonPath("$.mShootId").value(DEFAULT_M_SHOOT_ID))
            .andExpect(jsonPath("$.foulRate").value(DEFAULT_FOUL_RATE))
            .andExpect(jsonPath("$.distanceDecayType").value(DEFAULT_DISTANCE_DECAY_TYPE))
            .andExpect(jsonPath("$.activateCharacterCount").value(DEFAULT_ACTIVATE_CHARACTER_COUNT))
            .andExpect(jsonPath("$.actionCutId").value(DEFAULT_ACTION_CUT_ID))
            .andExpect(jsonPath("$.powerupGroup").value(DEFAULT_POWERUP_GROUP));
    }

    @Test
    @Transactional
    public void getAllMActionsByInitialCommandIsEqualToSomething() throws Exception {
        // Initialize the database
        mActionRepository.saveAndFlush(mAction);

        // Get all the mActionList where initialCommand equals to DEFAULT_INITIAL_COMMAND
        defaultMActionShouldBeFound("initialCommand.equals=" + DEFAULT_INITIAL_COMMAND);

        // Get all the mActionList where initialCommand equals to UPDATED_INITIAL_COMMAND
        defaultMActionShouldNotBeFound("initialCommand.equals=" + UPDATED_INITIAL_COMMAND);
    }

    @Test
    @Transactional
    public void getAllMActionsByInitialCommandIsInShouldWork() throws Exception {
        // Initialize the database
        mActionRepository.saveAndFlush(mAction);

        // Get all the mActionList where initialCommand in DEFAULT_INITIAL_COMMAND or UPDATED_INITIAL_COMMAND
        defaultMActionShouldBeFound("initialCommand.in=" + DEFAULT_INITIAL_COMMAND + "," + UPDATED_INITIAL_COMMAND);

        // Get all the mActionList where initialCommand equals to UPDATED_INITIAL_COMMAND
        defaultMActionShouldNotBeFound("initialCommand.in=" + UPDATED_INITIAL_COMMAND);
    }

    @Test
    @Transactional
    public void getAllMActionsByInitialCommandIsNullOrNotNull() throws Exception {
        // Initialize the database
        mActionRepository.saveAndFlush(mAction);

        // Get all the mActionList where initialCommand is not null
        defaultMActionShouldBeFound("initialCommand.specified=true");

        // Get all the mActionList where initialCommand is null
        defaultMActionShouldNotBeFound("initialCommand.specified=false");
    }

    @Test
    @Transactional
    public void getAllMActionsByInitialCommandIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mActionRepository.saveAndFlush(mAction);

        // Get all the mActionList where initialCommand greater than or equals to DEFAULT_INITIAL_COMMAND
        defaultMActionShouldBeFound("initialCommand.greaterOrEqualThan=" + DEFAULT_INITIAL_COMMAND);

        // Get all the mActionList where initialCommand greater than or equals to UPDATED_INITIAL_COMMAND
        defaultMActionShouldNotBeFound("initialCommand.greaterOrEqualThan=" + UPDATED_INITIAL_COMMAND);
    }

    @Test
    @Transactional
    public void getAllMActionsByInitialCommandIsLessThanSomething() throws Exception {
        // Initialize the database
        mActionRepository.saveAndFlush(mAction);

        // Get all the mActionList where initialCommand less than or equals to DEFAULT_INITIAL_COMMAND
        defaultMActionShouldNotBeFound("initialCommand.lessThan=" + DEFAULT_INITIAL_COMMAND);

        // Get all the mActionList where initialCommand less than or equals to UPDATED_INITIAL_COMMAND
        defaultMActionShouldBeFound("initialCommand.lessThan=" + UPDATED_INITIAL_COMMAND);
    }


    @Test
    @Transactional
    public void getAllMActionsByRarityIsEqualToSomething() throws Exception {
        // Initialize the database
        mActionRepository.saveAndFlush(mAction);

        // Get all the mActionList where rarity equals to DEFAULT_RARITY
        defaultMActionShouldBeFound("rarity.equals=" + DEFAULT_RARITY);

        // Get all the mActionList where rarity equals to UPDATED_RARITY
        defaultMActionShouldNotBeFound("rarity.equals=" + UPDATED_RARITY);
    }

    @Test
    @Transactional
    public void getAllMActionsByRarityIsInShouldWork() throws Exception {
        // Initialize the database
        mActionRepository.saveAndFlush(mAction);

        // Get all the mActionList where rarity in DEFAULT_RARITY or UPDATED_RARITY
        defaultMActionShouldBeFound("rarity.in=" + DEFAULT_RARITY + "," + UPDATED_RARITY);

        // Get all the mActionList where rarity equals to UPDATED_RARITY
        defaultMActionShouldNotBeFound("rarity.in=" + UPDATED_RARITY);
    }

    @Test
    @Transactional
    public void getAllMActionsByRarityIsNullOrNotNull() throws Exception {
        // Initialize the database
        mActionRepository.saveAndFlush(mAction);

        // Get all the mActionList where rarity is not null
        defaultMActionShouldBeFound("rarity.specified=true");

        // Get all the mActionList where rarity is null
        defaultMActionShouldNotBeFound("rarity.specified=false");
    }

    @Test
    @Transactional
    public void getAllMActionsByRarityIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mActionRepository.saveAndFlush(mAction);

        // Get all the mActionList where rarity greater than or equals to DEFAULT_RARITY
        defaultMActionShouldBeFound("rarity.greaterOrEqualThan=" + DEFAULT_RARITY);

        // Get all the mActionList where rarity greater than or equals to UPDATED_RARITY
        defaultMActionShouldNotBeFound("rarity.greaterOrEqualThan=" + UPDATED_RARITY);
    }

    @Test
    @Transactional
    public void getAllMActionsByRarityIsLessThanSomething() throws Exception {
        // Initialize the database
        mActionRepository.saveAndFlush(mAction);

        // Get all the mActionList where rarity less than or equals to DEFAULT_RARITY
        defaultMActionShouldNotBeFound("rarity.lessThan=" + DEFAULT_RARITY);

        // Get all the mActionList where rarity less than or equals to UPDATED_RARITY
        defaultMActionShouldBeFound("rarity.lessThan=" + UPDATED_RARITY);
    }


    @Test
    @Transactional
    public void getAllMActionsByCommandTypeIsEqualToSomething() throws Exception {
        // Initialize the database
        mActionRepository.saveAndFlush(mAction);

        // Get all the mActionList where commandType equals to DEFAULT_COMMAND_TYPE
        defaultMActionShouldBeFound("commandType.equals=" + DEFAULT_COMMAND_TYPE);

        // Get all the mActionList where commandType equals to UPDATED_COMMAND_TYPE
        defaultMActionShouldNotBeFound("commandType.equals=" + UPDATED_COMMAND_TYPE);
    }

    @Test
    @Transactional
    public void getAllMActionsByCommandTypeIsInShouldWork() throws Exception {
        // Initialize the database
        mActionRepository.saveAndFlush(mAction);

        // Get all the mActionList where commandType in DEFAULT_COMMAND_TYPE or UPDATED_COMMAND_TYPE
        defaultMActionShouldBeFound("commandType.in=" + DEFAULT_COMMAND_TYPE + "," + UPDATED_COMMAND_TYPE);

        // Get all the mActionList where commandType equals to UPDATED_COMMAND_TYPE
        defaultMActionShouldNotBeFound("commandType.in=" + UPDATED_COMMAND_TYPE);
    }

    @Test
    @Transactional
    public void getAllMActionsByCommandTypeIsNullOrNotNull() throws Exception {
        // Initialize the database
        mActionRepository.saveAndFlush(mAction);

        // Get all the mActionList where commandType is not null
        defaultMActionShouldBeFound("commandType.specified=true");

        // Get all the mActionList where commandType is null
        defaultMActionShouldNotBeFound("commandType.specified=false");
    }

    @Test
    @Transactional
    public void getAllMActionsByCommandTypeIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mActionRepository.saveAndFlush(mAction);

        // Get all the mActionList where commandType greater than or equals to DEFAULT_COMMAND_TYPE
        defaultMActionShouldBeFound("commandType.greaterOrEqualThan=" + DEFAULT_COMMAND_TYPE);

        // Get all the mActionList where commandType greater than or equals to UPDATED_COMMAND_TYPE
        defaultMActionShouldNotBeFound("commandType.greaterOrEqualThan=" + UPDATED_COMMAND_TYPE);
    }

    @Test
    @Transactional
    public void getAllMActionsByCommandTypeIsLessThanSomething() throws Exception {
        // Initialize the database
        mActionRepository.saveAndFlush(mAction);

        // Get all the mActionList where commandType less than or equals to DEFAULT_COMMAND_TYPE
        defaultMActionShouldNotBeFound("commandType.lessThan=" + DEFAULT_COMMAND_TYPE);

        // Get all the mActionList where commandType less than or equals to UPDATED_COMMAND_TYPE
        defaultMActionShouldBeFound("commandType.lessThan=" + UPDATED_COMMAND_TYPE);
    }


    @Test
    @Transactional
    public void getAllMActionsByBallConditionGroundIsEqualToSomething() throws Exception {
        // Initialize the database
        mActionRepository.saveAndFlush(mAction);

        // Get all the mActionList where ballConditionGround equals to DEFAULT_BALL_CONDITION_GROUND
        defaultMActionShouldBeFound("ballConditionGround.equals=" + DEFAULT_BALL_CONDITION_GROUND);

        // Get all the mActionList where ballConditionGround equals to UPDATED_BALL_CONDITION_GROUND
        defaultMActionShouldNotBeFound("ballConditionGround.equals=" + UPDATED_BALL_CONDITION_GROUND);
    }

    @Test
    @Transactional
    public void getAllMActionsByBallConditionGroundIsInShouldWork() throws Exception {
        // Initialize the database
        mActionRepository.saveAndFlush(mAction);

        // Get all the mActionList where ballConditionGround in DEFAULT_BALL_CONDITION_GROUND or UPDATED_BALL_CONDITION_GROUND
        defaultMActionShouldBeFound("ballConditionGround.in=" + DEFAULT_BALL_CONDITION_GROUND + "," + UPDATED_BALL_CONDITION_GROUND);

        // Get all the mActionList where ballConditionGround equals to UPDATED_BALL_CONDITION_GROUND
        defaultMActionShouldNotBeFound("ballConditionGround.in=" + UPDATED_BALL_CONDITION_GROUND);
    }

    @Test
    @Transactional
    public void getAllMActionsByBallConditionGroundIsNullOrNotNull() throws Exception {
        // Initialize the database
        mActionRepository.saveAndFlush(mAction);

        // Get all the mActionList where ballConditionGround is not null
        defaultMActionShouldBeFound("ballConditionGround.specified=true");

        // Get all the mActionList where ballConditionGround is null
        defaultMActionShouldNotBeFound("ballConditionGround.specified=false");
    }

    @Test
    @Transactional
    public void getAllMActionsByBallConditionGroundIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mActionRepository.saveAndFlush(mAction);

        // Get all the mActionList where ballConditionGround greater than or equals to DEFAULT_BALL_CONDITION_GROUND
        defaultMActionShouldBeFound("ballConditionGround.greaterOrEqualThan=" + DEFAULT_BALL_CONDITION_GROUND);

        // Get all the mActionList where ballConditionGround greater than or equals to UPDATED_BALL_CONDITION_GROUND
        defaultMActionShouldNotBeFound("ballConditionGround.greaterOrEqualThan=" + UPDATED_BALL_CONDITION_GROUND);
    }

    @Test
    @Transactional
    public void getAllMActionsByBallConditionGroundIsLessThanSomething() throws Exception {
        // Initialize the database
        mActionRepository.saveAndFlush(mAction);

        // Get all the mActionList where ballConditionGround less than or equals to DEFAULT_BALL_CONDITION_GROUND
        defaultMActionShouldNotBeFound("ballConditionGround.lessThan=" + DEFAULT_BALL_CONDITION_GROUND);

        // Get all the mActionList where ballConditionGround less than or equals to UPDATED_BALL_CONDITION_GROUND
        defaultMActionShouldBeFound("ballConditionGround.lessThan=" + UPDATED_BALL_CONDITION_GROUND);
    }


    @Test
    @Transactional
    public void getAllMActionsByBallConditionLowIsEqualToSomething() throws Exception {
        // Initialize the database
        mActionRepository.saveAndFlush(mAction);

        // Get all the mActionList where ballConditionLow equals to DEFAULT_BALL_CONDITION_LOW
        defaultMActionShouldBeFound("ballConditionLow.equals=" + DEFAULT_BALL_CONDITION_LOW);

        // Get all the mActionList where ballConditionLow equals to UPDATED_BALL_CONDITION_LOW
        defaultMActionShouldNotBeFound("ballConditionLow.equals=" + UPDATED_BALL_CONDITION_LOW);
    }

    @Test
    @Transactional
    public void getAllMActionsByBallConditionLowIsInShouldWork() throws Exception {
        // Initialize the database
        mActionRepository.saveAndFlush(mAction);

        // Get all the mActionList where ballConditionLow in DEFAULT_BALL_CONDITION_LOW or UPDATED_BALL_CONDITION_LOW
        defaultMActionShouldBeFound("ballConditionLow.in=" + DEFAULT_BALL_CONDITION_LOW + "," + UPDATED_BALL_CONDITION_LOW);

        // Get all the mActionList where ballConditionLow equals to UPDATED_BALL_CONDITION_LOW
        defaultMActionShouldNotBeFound("ballConditionLow.in=" + UPDATED_BALL_CONDITION_LOW);
    }

    @Test
    @Transactional
    public void getAllMActionsByBallConditionLowIsNullOrNotNull() throws Exception {
        // Initialize the database
        mActionRepository.saveAndFlush(mAction);

        // Get all the mActionList where ballConditionLow is not null
        defaultMActionShouldBeFound("ballConditionLow.specified=true");

        // Get all the mActionList where ballConditionLow is null
        defaultMActionShouldNotBeFound("ballConditionLow.specified=false");
    }

    @Test
    @Transactional
    public void getAllMActionsByBallConditionLowIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mActionRepository.saveAndFlush(mAction);

        // Get all the mActionList where ballConditionLow greater than or equals to DEFAULT_BALL_CONDITION_LOW
        defaultMActionShouldBeFound("ballConditionLow.greaterOrEqualThan=" + DEFAULT_BALL_CONDITION_LOW);

        // Get all the mActionList where ballConditionLow greater than or equals to UPDATED_BALL_CONDITION_LOW
        defaultMActionShouldNotBeFound("ballConditionLow.greaterOrEqualThan=" + UPDATED_BALL_CONDITION_LOW);
    }

    @Test
    @Transactional
    public void getAllMActionsByBallConditionLowIsLessThanSomething() throws Exception {
        // Initialize the database
        mActionRepository.saveAndFlush(mAction);

        // Get all the mActionList where ballConditionLow less than or equals to DEFAULT_BALL_CONDITION_LOW
        defaultMActionShouldNotBeFound("ballConditionLow.lessThan=" + DEFAULT_BALL_CONDITION_LOW);

        // Get all the mActionList where ballConditionLow less than or equals to UPDATED_BALL_CONDITION_LOW
        defaultMActionShouldBeFound("ballConditionLow.lessThan=" + UPDATED_BALL_CONDITION_LOW);
    }


    @Test
    @Transactional
    public void getAllMActionsByBallConditionHighIsEqualToSomething() throws Exception {
        // Initialize the database
        mActionRepository.saveAndFlush(mAction);

        // Get all the mActionList where ballConditionHigh equals to DEFAULT_BALL_CONDITION_HIGH
        defaultMActionShouldBeFound("ballConditionHigh.equals=" + DEFAULT_BALL_CONDITION_HIGH);

        // Get all the mActionList where ballConditionHigh equals to UPDATED_BALL_CONDITION_HIGH
        defaultMActionShouldNotBeFound("ballConditionHigh.equals=" + UPDATED_BALL_CONDITION_HIGH);
    }

    @Test
    @Transactional
    public void getAllMActionsByBallConditionHighIsInShouldWork() throws Exception {
        // Initialize the database
        mActionRepository.saveAndFlush(mAction);

        // Get all the mActionList where ballConditionHigh in DEFAULT_BALL_CONDITION_HIGH or UPDATED_BALL_CONDITION_HIGH
        defaultMActionShouldBeFound("ballConditionHigh.in=" + DEFAULT_BALL_CONDITION_HIGH + "," + UPDATED_BALL_CONDITION_HIGH);

        // Get all the mActionList where ballConditionHigh equals to UPDATED_BALL_CONDITION_HIGH
        defaultMActionShouldNotBeFound("ballConditionHigh.in=" + UPDATED_BALL_CONDITION_HIGH);
    }

    @Test
    @Transactional
    public void getAllMActionsByBallConditionHighIsNullOrNotNull() throws Exception {
        // Initialize the database
        mActionRepository.saveAndFlush(mAction);

        // Get all the mActionList where ballConditionHigh is not null
        defaultMActionShouldBeFound("ballConditionHigh.specified=true");

        // Get all the mActionList where ballConditionHigh is null
        defaultMActionShouldNotBeFound("ballConditionHigh.specified=false");
    }

    @Test
    @Transactional
    public void getAllMActionsByBallConditionHighIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mActionRepository.saveAndFlush(mAction);

        // Get all the mActionList where ballConditionHigh greater than or equals to DEFAULT_BALL_CONDITION_HIGH
        defaultMActionShouldBeFound("ballConditionHigh.greaterOrEqualThan=" + DEFAULT_BALL_CONDITION_HIGH);

        // Get all the mActionList where ballConditionHigh greater than or equals to UPDATED_BALL_CONDITION_HIGH
        defaultMActionShouldNotBeFound("ballConditionHigh.greaterOrEqualThan=" + UPDATED_BALL_CONDITION_HIGH);
    }

    @Test
    @Transactional
    public void getAllMActionsByBallConditionHighIsLessThanSomething() throws Exception {
        // Initialize the database
        mActionRepository.saveAndFlush(mAction);

        // Get all the mActionList where ballConditionHigh less than or equals to DEFAULT_BALL_CONDITION_HIGH
        defaultMActionShouldNotBeFound("ballConditionHigh.lessThan=" + DEFAULT_BALL_CONDITION_HIGH);

        // Get all the mActionList where ballConditionHigh less than or equals to UPDATED_BALL_CONDITION_HIGH
        defaultMActionShouldBeFound("ballConditionHigh.lessThan=" + UPDATED_BALL_CONDITION_HIGH);
    }


    @Test
    @Transactional
    public void getAllMActionsByStaminaLvMinIsEqualToSomething() throws Exception {
        // Initialize the database
        mActionRepository.saveAndFlush(mAction);

        // Get all the mActionList where staminaLvMin equals to DEFAULT_STAMINA_LV_MIN
        defaultMActionShouldBeFound("staminaLvMin.equals=" + DEFAULT_STAMINA_LV_MIN);

        // Get all the mActionList where staminaLvMin equals to UPDATED_STAMINA_LV_MIN
        defaultMActionShouldNotBeFound("staminaLvMin.equals=" + UPDATED_STAMINA_LV_MIN);
    }

    @Test
    @Transactional
    public void getAllMActionsByStaminaLvMinIsInShouldWork() throws Exception {
        // Initialize the database
        mActionRepository.saveAndFlush(mAction);

        // Get all the mActionList where staminaLvMin in DEFAULT_STAMINA_LV_MIN or UPDATED_STAMINA_LV_MIN
        defaultMActionShouldBeFound("staminaLvMin.in=" + DEFAULT_STAMINA_LV_MIN + "," + UPDATED_STAMINA_LV_MIN);

        // Get all the mActionList where staminaLvMin equals to UPDATED_STAMINA_LV_MIN
        defaultMActionShouldNotBeFound("staminaLvMin.in=" + UPDATED_STAMINA_LV_MIN);
    }

    @Test
    @Transactional
    public void getAllMActionsByStaminaLvMinIsNullOrNotNull() throws Exception {
        // Initialize the database
        mActionRepository.saveAndFlush(mAction);

        // Get all the mActionList where staminaLvMin is not null
        defaultMActionShouldBeFound("staminaLvMin.specified=true");

        // Get all the mActionList where staminaLvMin is null
        defaultMActionShouldNotBeFound("staminaLvMin.specified=false");
    }

    @Test
    @Transactional
    public void getAllMActionsByStaminaLvMinIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mActionRepository.saveAndFlush(mAction);

        // Get all the mActionList where staminaLvMin greater than or equals to DEFAULT_STAMINA_LV_MIN
        defaultMActionShouldBeFound("staminaLvMin.greaterOrEqualThan=" + DEFAULT_STAMINA_LV_MIN);

        // Get all the mActionList where staminaLvMin greater than or equals to UPDATED_STAMINA_LV_MIN
        defaultMActionShouldNotBeFound("staminaLvMin.greaterOrEqualThan=" + UPDATED_STAMINA_LV_MIN);
    }

    @Test
    @Transactional
    public void getAllMActionsByStaminaLvMinIsLessThanSomething() throws Exception {
        // Initialize the database
        mActionRepository.saveAndFlush(mAction);

        // Get all the mActionList where staminaLvMin less than or equals to DEFAULT_STAMINA_LV_MIN
        defaultMActionShouldNotBeFound("staminaLvMin.lessThan=" + DEFAULT_STAMINA_LV_MIN);

        // Get all the mActionList where staminaLvMin less than or equals to UPDATED_STAMINA_LV_MIN
        defaultMActionShouldBeFound("staminaLvMin.lessThan=" + UPDATED_STAMINA_LV_MIN);
    }


    @Test
    @Transactional
    public void getAllMActionsByStaminaLvMaxIsEqualToSomething() throws Exception {
        // Initialize the database
        mActionRepository.saveAndFlush(mAction);

        // Get all the mActionList where staminaLvMax equals to DEFAULT_STAMINA_LV_MAX
        defaultMActionShouldBeFound("staminaLvMax.equals=" + DEFAULT_STAMINA_LV_MAX);

        // Get all the mActionList where staminaLvMax equals to UPDATED_STAMINA_LV_MAX
        defaultMActionShouldNotBeFound("staminaLvMax.equals=" + UPDATED_STAMINA_LV_MAX);
    }

    @Test
    @Transactional
    public void getAllMActionsByStaminaLvMaxIsInShouldWork() throws Exception {
        // Initialize the database
        mActionRepository.saveAndFlush(mAction);

        // Get all the mActionList where staminaLvMax in DEFAULT_STAMINA_LV_MAX or UPDATED_STAMINA_LV_MAX
        defaultMActionShouldBeFound("staminaLvMax.in=" + DEFAULT_STAMINA_LV_MAX + "," + UPDATED_STAMINA_LV_MAX);

        // Get all the mActionList where staminaLvMax equals to UPDATED_STAMINA_LV_MAX
        defaultMActionShouldNotBeFound("staminaLvMax.in=" + UPDATED_STAMINA_LV_MAX);
    }

    @Test
    @Transactional
    public void getAllMActionsByStaminaLvMaxIsNullOrNotNull() throws Exception {
        // Initialize the database
        mActionRepository.saveAndFlush(mAction);

        // Get all the mActionList where staminaLvMax is not null
        defaultMActionShouldBeFound("staminaLvMax.specified=true");

        // Get all the mActionList where staminaLvMax is null
        defaultMActionShouldNotBeFound("staminaLvMax.specified=false");
    }

    @Test
    @Transactional
    public void getAllMActionsByStaminaLvMaxIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mActionRepository.saveAndFlush(mAction);

        // Get all the mActionList where staminaLvMax greater than or equals to DEFAULT_STAMINA_LV_MAX
        defaultMActionShouldBeFound("staminaLvMax.greaterOrEqualThan=" + DEFAULT_STAMINA_LV_MAX);

        // Get all the mActionList where staminaLvMax greater than or equals to UPDATED_STAMINA_LV_MAX
        defaultMActionShouldNotBeFound("staminaLvMax.greaterOrEqualThan=" + UPDATED_STAMINA_LV_MAX);
    }

    @Test
    @Transactional
    public void getAllMActionsByStaminaLvMaxIsLessThanSomething() throws Exception {
        // Initialize the database
        mActionRepository.saveAndFlush(mAction);

        // Get all the mActionList where staminaLvMax less than or equals to DEFAULT_STAMINA_LV_MAX
        defaultMActionShouldNotBeFound("staminaLvMax.lessThan=" + DEFAULT_STAMINA_LV_MAX);

        // Get all the mActionList where staminaLvMax less than or equals to UPDATED_STAMINA_LV_MAX
        defaultMActionShouldBeFound("staminaLvMax.lessThan=" + UPDATED_STAMINA_LV_MAX);
    }


    @Test
    @Transactional
    public void getAllMActionsByPowerLvMinIsEqualToSomething() throws Exception {
        // Initialize the database
        mActionRepository.saveAndFlush(mAction);

        // Get all the mActionList where powerLvMin equals to DEFAULT_POWER_LV_MIN
        defaultMActionShouldBeFound("powerLvMin.equals=" + DEFAULT_POWER_LV_MIN);

        // Get all the mActionList where powerLvMin equals to UPDATED_POWER_LV_MIN
        defaultMActionShouldNotBeFound("powerLvMin.equals=" + UPDATED_POWER_LV_MIN);
    }

    @Test
    @Transactional
    public void getAllMActionsByPowerLvMinIsInShouldWork() throws Exception {
        // Initialize the database
        mActionRepository.saveAndFlush(mAction);

        // Get all the mActionList where powerLvMin in DEFAULT_POWER_LV_MIN or UPDATED_POWER_LV_MIN
        defaultMActionShouldBeFound("powerLvMin.in=" + DEFAULT_POWER_LV_MIN + "," + UPDATED_POWER_LV_MIN);

        // Get all the mActionList where powerLvMin equals to UPDATED_POWER_LV_MIN
        defaultMActionShouldNotBeFound("powerLvMin.in=" + UPDATED_POWER_LV_MIN);
    }

    @Test
    @Transactional
    public void getAllMActionsByPowerLvMinIsNullOrNotNull() throws Exception {
        // Initialize the database
        mActionRepository.saveAndFlush(mAction);

        // Get all the mActionList where powerLvMin is not null
        defaultMActionShouldBeFound("powerLvMin.specified=true");

        // Get all the mActionList where powerLvMin is null
        defaultMActionShouldNotBeFound("powerLvMin.specified=false");
    }

    @Test
    @Transactional
    public void getAllMActionsByPowerLvMinIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mActionRepository.saveAndFlush(mAction);

        // Get all the mActionList where powerLvMin greater than or equals to DEFAULT_POWER_LV_MIN
        defaultMActionShouldBeFound("powerLvMin.greaterOrEqualThan=" + DEFAULT_POWER_LV_MIN);

        // Get all the mActionList where powerLvMin greater than or equals to UPDATED_POWER_LV_MIN
        defaultMActionShouldNotBeFound("powerLvMin.greaterOrEqualThan=" + UPDATED_POWER_LV_MIN);
    }

    @Test
    @Transactional
    public void getAllMActionsByPowerLvMinIsLessThanSomething() throws Exception {
        // Initialize the database
        mActionRepository.saveAndFlush(mAction);

        // Get all the mActionList where powerLvMin less than or equals to DEFAULT_POWER_LV_MIN
        defaultMActionShouldNotBeFound("powerLvMin.lessThan=" + DEFAULT_POWER_LV_MIN);

        // Get all the mActionList where powerLvMin less than or equals to UPDATED_POWER_LV_MIN
        defaultMActionShouldBeFound("powerLvMin.lessThan=" + UPDATED_POWER_LV_MIN);
    }


    @Test
    @Transactional
    public void getAllMActionsByPowerLvMaxIsEqualToSomething() throws Exception {
        // Initialize the database
        mActionRepository.saveAndFlush(mAction);

        // Get all the mActionList where powerLvMax equals to DEFAULT_POWER_LV_MAX
        defaultMActionShouldBeFound("powerLvMax.equals=" + DEFAULT_POWER_LV_MAX);

        // Get all the mActionList where powerLvMax equals to UPDATED_POWER_LV_MAX
        defaultMActionShouldNotBeFound("powerLvMax.equals=" + UPDATED_POWER_LV_MAX);
    }

    @Test
    @Transactional
    public void getAllMActionsByPowerLvMaxIsInShouldWork() throws Exception {
        // Initialize the database
        mActionRepository.saveAndFlush(mAction);

        // Get all the mActionList where powerLvMax in DEFAULT_POWER_LV_MAX or UPDATED_POWER_LV_MAX
        defaultMActionShouldBeFound("powerLvMax.in=" + DEFAULT_POWER_LV_MAX + "," + UPDATED_POWER_LV_MAX);

        // Get all the mActionList where powerLvMax equals to UPDATED_POWER_LV_MAX
        defaultMActionShouldNotBeFound("powerLvMax.in=" + UPDATED_POWER_LV_MAX);
    }

    @Test
    @Transactional
    public void getAllMActionsByPowerLvMaxIsNullOrNotNull() throws Exception {
        // Initialize the database
        mActionRepository.saveAndFlush(mAction);

        // Get all the mActionList where powerLvMax is not null
        defaultMActionShouldBeFound("powerLvMax.specified=true");

        // Get all the mActionList where powerLvMax is null
        defaultMActionShouldNotBeFound("powerLvMax.specified=false");
    }

    @Test
    @Transactional
    public void getAllMActionsByPowerLvMaxIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mActionRepository.saveAndFlush(mAction);

        // Get all the mActionList where powerLvMax greater than or equals to DEFAULT_POWER_LV_MAX
        defaultMActionShouldBeFound("powerLvMax.greaterOrEqualThan=" + DEFAULT_POWER_LV_MAX);

        // Get all the mActionList where powerLvMax greater than or equals to UPDATED_POWER_LV_MAX
        defaultMActionShouldNotBeFound("powerLvMax.greaterOrEqualThan=" + UPDATED_POWER_LV_MAX);
    }

    @Test
    @Transactional
    public void getAllMActionsByPowerLvMaxIsLessThanSomething() throws Exception {
        // Initialize the database
        mActionRepository.saveAndFlush(mAction);

        // Get all the mActionList where powerLvMax less than or equals to DEFAULT_POWER_LV_MAX
        defaultMActionShouldNotBeFound("powerLvMax.lessThan=" + DEFAULT_POWER_LV_MAX);

        // Get all the mActionList where powerLvMax less than or equals to UPDATED_POWER_LV_MAX
        defaultMActionShouldBeFound("powerLvMax.lessThan=" + UPDATED_POWER_LV_MAX);
    }


    @Test
    @Transactional
    public void getAllMActionsByBlowOffCountIsEqualToSomething() throws Exception {
        // Initialize the database
        mActionRepository.saveAndFlush(mAction);

        // Get all the mActionList where blowOffCount equals to DEFAULT_BLOW_OFF_COUNT
        defaultMActionShouldBeFound("blowOffCount.equals=" + DEFAULT_BLOW_OFF_COUNT);

        // Get all the mActionList where blowOffCount equals to UPDATED_BLOW_OFF_COUNT
        defaultMActionShouldNotBeFound("blowOffCount.equals=" + UPDATED_BLOW_OFF_COUNT);
    }

    @Test
    @Transactional
    public void getAllMActionsByBlowOffCountIsInShouldWork() throws Exception {
        // Initialize the database
        mActionRepository.saveAndFlush(mAction);

        // Get all the mActionList where blowOffCount in DEFAULT_BLOW_OFF_COUNT or UPDATED_BLOW_OFF_COUNT
        defaultMActionShouldBeFound("blowOffCount.in=" + DEFAULT_BLOW_OFF_COUNT + "," + UPDATED_BLOW_OFF_COUNT);

        // Get all the mActionList where blowOffCount equals to UPDATED_BLOW_OFF_COUNT
        defaultMActionShouldNotBeFound("blowOffCount.in=" + UPDATED_BLOW_OFF_COUNT);
    }

    @Test
    @Transactional
    public void getAllMActionsByBlowOffCountIsNullOrNotNull() throws Exception {
        // Initialize the database
        mActionRepository.saveAndFlush(mAction);

        // Get all the mActionList where blowOffCount is not null
        defaultMActionShouldBeFound("blowOffCount.specified=true");

        // Get all the mActionList where blowOffCount is null
        defaultMActionShouldNotBeFound("blowOffCount.specified=false");
    }

    @Test
    @Transactional
    public void getAllMActionsByBlowOffCountIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mActionRepository.saveAndFlush(mAction);

        // Get all the mActionList where blowOffCount greater than or equals to DEFAULT_BLOW_OFF_COUNT
        defaultMActionShouldBeFound("blowOffCount.greaterOrEqualThan=" + DEFAULT_BLOW_OFF_COUNT);

        // Get all the mActionList where blowOffCount greater than or equals to UPDATED_BLOW_OFF_COUNT
        defaultMActionShouldNotBeFound("blowOffCount.greaterOrEqualThan=" + UPDATED_BLOW_OFF_COUNT);
    }

    @Test
    @Transactional
    public void getAllMActionsByBlowOffCountIsLessThanSomething() throws Exception {
        // Initialize the database
        mActionRepository.saveAndFlush(mAction);

        // Get all the mActionList where blowOffCount less than or equals to DEFAULT_BLOW_OFF_COUNT
        defaultMActionShouldNotBeFound("blowOffCount.lessThan=" + DEFAULT_BLOW_OFF_COUNT);

        // Get all the mActionList where blowOffCount less than or equals to UPDATED_BLOW_OFF_COUNT
        defaultMActionShouldBeFound("blowOffCount.lessThan=" + UPDATED_BLOW_OFF_COUNT);
    }


    @Test
    @Transactional
    public void getAllMActionsBymShootIdIsEqualToSomething() throws Exception {
        // Initialize the database
        mActionRepository.saveAndFlush(mAction);

        // Get all the mActionList where mShootId equals to DEFAULT_M_SHOOT_ID
        defaultMActionShouldBeFound("mShootId.equals=" + DEFAULT_M_SHOOT_ID);

        // Get all the mActionList where mShootId equals to UPDATED_M_SHOOT_ID
        defaultMActionShouldNotBeFound("mShootId.equals=" + UPDATED_M_SHOOT_ID);
    }

    @Test
    @Transactional
    public void getAllMActionsBymShootIdIsInShouldWork() throws Exception {
        // Initialize the database
        mActionRepository.saveAndFlush(mAction);

        // Get all the mActionList where mShootId in DEFAULT_M_SHOOT_ID or UPDATED_M_SHOOT_ID
        defaultMActionShouldBeFound("mShootId.in=" + DEFAULT_M_SHOOT_ID + "," + UPDATED_M_SHOOT_ID);

        // Get all the mActionList where mShootId equals to UPDATED_M_SHOOT_ID
        defaultMActionShouldNotBeFound("mShootId.in=" + UPDATED_M_SHOOT_ID);
    }

    @Test
    @Transactional
    public void getAllMActionsBymShootIdIsNullOrNotNull() throws Exception {
        // Initialize the database
        mActionRepository.saveAndFlush(mAction);

        // Get all the mActionList where mShootId is not null
        defaultMActionShouldBeFound("mShootId.specified=true");

        // Get all the mActionList where mShootId is null
        defaultMActionShouldNotBeFound("mShootId.specified=false");
    }

    @Test
    @Transactional
    public void getAllMActionsBymShootIdIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mActionRepository.saveAndFlush(mAction);

        // Get all the mActionList where mShootId greater than or equals to DEFAULT_M_SHOOT_ID
        defaultMActionShouldBeFound("mShootId.greaterOrEqualThan=" + DEFAULT_M_SHOOT_ID);

        // Get all the mActionList where mShootId greater than or equals to UPDATED_M_SHOOT_ID
        defaultMActionShouldNotBeFound("mShootId.greaterOrEqualThan=" + UPDATED_M_SHOOT_ID);
    }

    @Test
    @Transactional
    public void getAllMActionsBymShootIdIsLessThanSomething() throws Exception {
        // Initialize the database
        mActionRepository.saveAndFlush(mAction);

        // Get all the mActionList where mShootId less than or equals to DEFAULT_M_SHOOT_ID
        defaultMActionShouldNotBeFound("mShootId.lessThan=" + DEFAULT_M_SHOOT_ID);

        // Get all the mActionList where mShootId less than or equals to UPDATED_M_SHOOT_ID
        defaultMActionShouldBeFound("mShootId.lessThan=" + UPDATED_M_SHOOT_ID);
    }


    @Test
    @Transactional
    public void getAllMActionsByFoulRateIsEqualToSomething() throws Exception {
        // Initialize the database
        mActionRepository.saveAndFlush(mAction);

        // Get all the mActionList where foulRate equals to DEFAULT_FOUL_RATE
        defaultMActionShouldBeFound("foulRate.equals=" + DEFAULT_FOUL_RATE);

        // Get all the mActionList where foulRate equals to UPDATED_FOUL_RATE
        defaultMActionShouldNotBeFound("foulRate.equals=" + UPDATED_FOUL_RATE);
    }

    @Test
    @Transactional
    public void getAllMActionsByFoulRateIsInShouldWork() throws Exception {
        // Initialize the database
        mActionRepository.saveAndFlush(mAction);

        // Get all the mActionList where foulRate in DEFAULT_FOUL_RATE or UPDATED_FOUL_RATE
        defaultMActionShouldBeFound("foulRate.in=" + DEFAULT_FOUL_RATE + "," + UPDATED_FOUL_RATE);

        // Get all the mActionList where foulRate equals to UPDATED_FOUL_RATE
        defaultMActionShouldNotBeFound("foulRate.in=" + UPDATED_FOUL_RATE);
    }

    @Test
    @Transactional
    public void getAllMActionsByFoulRateIsNullOrNotNull() throws Exception {
        // Initialize the database
        mActionRepository.saveAndFlush(mAction);

        // Get all the mActionList where foulRate is not null
        defaultMActionShouldBeFound("foulRate.specified=true");

        // Get all the mActionList where foulRate is null
        defaultMActionShouldNotBeFound("foulRate.specified=false");
    }

    @Test
    @Transactional
    public void getAllMActionsByFoulRateIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mActionRepository.saveAndFlush(mAction);

        // Get all the mActionList where foulRate greater than or equals to DEFAULT_FOUL_RATE
        defaultMActionShouldBeFound("foulRate.greaterOrEqualThan=" + DEFAULT_FOUL_RATE);

        // Get all the mActionList where foulRate greater than or equals to UPDATED_FOUL_RATE
        defaultMActionShouldNotBeFound("foulRate.greaterOrEqualThan=" + UPDATED_FOUL_RATE);
    }

    @Test
    @Transactional
    public void getAllMActionsByFoulRateIsLessThanSomething() throws Exception {
        // Initialize the database
        mActionRepository.saveAndFlush(mAction);

        // Get all the mActionList where foulRate less than or equals to DEFAULT_FOUL_RATE
        defaultMActionShouldNotBeFound("foulRate.lessThan=" + DEFAULT_FOUL_RATE);

        // Get all the mActionList where foulRate less than or equals to UPDATED_FOUL_RATE
        defaultMActionShouldBeFound("foulRate.lessThan=" + UPDATED_FOUL_RATE);
    }


    @Test
    @Transactional
    public void getAllMActionsByDistanceDecayTypeIsEqualToSomething() throws Exception {
        // Initialize the database
        mActionRepository.saveAndFlush(mAction);

        // Get all the mActionList where distanceDecayType equals to DEFAULT_DISTANCE_DECAY_TYPE
        defaultMActionShouldBeFound("distanceDecayType.equals=" + DEFAULT_DISTANCE_DECAY_TYPE);

        // Get all the mActionList where distanceDecayType equals to UPDATED_DISTANCE_DECAY_TYPE
        defaultMActionShouldNotBeFound("distanceDecayType.equals=" + UPDATED_DISTANCE_DECAY_TYPE);
    }

    @Test
    @Transactional
    public void getAllMActionsByDistanceDecayTypeIsInShouldWork() throws Exception {
        // Initialize the database
        mActionRepository.saveAndFlush(mAction);

        // Get all the mActionList where distanceDecayType in DEFAULT_DISTANCE_DECAY_TYPE or UPDATED_DISTANCE_DECAY_TYPE
        defaultMActionShouldBeFound("distanceDecayType.in=" + DEFAULT_DISTANCE_DECAY_TYPE + "," + UPDATED_DISTANCE_DECAY_TYPE);

        // Get all the mActionList where distanceDecayType equals to UPDATED_DISTANCE_DECAY_TYPE
        defaultMActionShouldNotBeFound("distanceDecayType.in=" + UPDATED_DISTANCE_DECAY_TYPE);
    }

    @Test
    @Transactional
    public void getAllMActionsByDistanceDecayTypeIsNullOrNotNull() throws Exception {
        // Initialize the database
        mActionRepository.saveAndFlush(mAction);

        // Get all the mActionList where distanceDecayType is not null
        defaultMActionShouldBeFound("distanceDecayType.specified=true");

        // Get all the mActionList where distanceDecayType is null
        defaultMActionShouldNotBeFound("distanceDecayType.specified=false");
    }

    @Test
    @Transactional
    public void getAllMActionsByDistanceDecayTypeIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mActionRepository.saveAndFlush(mAction);

        // Get all the mActionList where distanceDecayType greater than or equals to DEFAULT_DISTANCE_DECAY_TYPE
        defaultMActionShouldBeFound("distanceDecayType.greaterOrEqualThan=" + DEFAULT_DISTANCE_DECAY_TYPE);

        // Get all the mActionList where distanceDecayType greater than or equals to UPDATED_DISTANCE_DECAY_TYPE
        defaultMActionShouldNotBeFound("distanceDecayType.greaterOrEqualThan=" + UPDATED_DISTANCE_DECAY_TYPE);
    }

    @Test
    @Transactional
    public void getAllMActionsByDistanceDecayTypeIsLessThanSomething() throws Exception {
        // Initialize the database
        mActionRepository.saveAndFlush(mAction);

        // Get all the mActionList where distanceDecayType less than or equals to DEFAULT_DISTANCE_DECAY_TYPE
        defaultMActionShouldNotBeFound("distanceDecayType.lessThan=" + DEFAULT_DISTANCE_DECAY_TYPE);

        // Get all the mActionList where distanceDecayType less than or equals to UPDATED_DISTANCE_DECAY_TYPE
        defaultMActionShouldBeFound("distanceDecayType.lessThan=" + UPDATED_DISTANCE_DECAY_TYPE);
    }


    @Test
    @Transactional
    public void getAllMActionsByActivateCharacterCountIsEqualToSomething() throws Exception {
        // Initialize the database
        mActionRepository.saveAndFlush(mAction);

        // Get all the mActionList where activateCharacterCount equals to DEFAULT_ACTIVATE_CHARACTER_COUNT
        defaultMActionShouldBeFound("activateCharacterCount.equals=" + DEFAULT_ACTIVATE_CHARACTER_COUNT);

        // Get all the mActionList where activateCharacterCount equals to UPDATED_ACTIVATE_CHARACTER_COUNT
        defaultMActionShouldNotBeFound("activateCharacterCount.equals=" + UPDATED_ACTIVATE_CHARACTER_COUNT);
    }

    @Test
    @Transactional
    public void getAllMActionsByActivateCharacterCountIsInShouldWork() throws Exception {
        // Initialize the database
        mActionRepository.saveAndFlush(mAction);

        // Get all the mActionList where activateCharacterCount in DEFAULT_ACTIVATE_CHARACTER_COUNT or UPDATED_ACTIVATE_CHARACTER_COUNT
        defaultMActionShouldBeFound("activateCharacterCount.in=" + DEFAULT_ACTIVATE_CHARACTER_COUNT + "," + UPDATED_ACTIVATE_CHARACTER_COUNT);

        // Get all the mActionList where activateCharacterCount equals to UPDATED_ACTIVATE_CHARACTER_COUNT
        defaultMActionShouldNotBeFound("activateCharacterCount.in=" + UPDATED_ACTIVATE_CHARACTER_COUNT);
    }

    @Test
    @Transactional
    public void getAllMActionsByActivateCharacterCountIsNullOrNotNull() throws Exception {
        // Initialize the database
        mActionRepository.saveAndFlush(mAction);

        // Get all the mActionList where activateCharacterCount is not null
        defaultMActionShouldBeFound("activateCharacterCount.specified=true");

        // Get all the mActionList where activateCharacterCount is null
        defaultMActionShouldNotBeFound("activateCharacterCount.specified=false");
    }

    @Test
    @Transactional
    public void getAllMActionsByActivateCharacterCountIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mActionRepository.saveAndFlush(mAction);

        // Get all the mActionList where activateCharacterCount greater than or equals to DEFAULT_ACTIVATE_CHARACTER_COUNT
        defaultMActionShouldBeFound("activateCharacterCount.greaterOrEqualThan=" + DEFAULT_ACTIVATE_CHARACTER_COUNT);

        // Get all the mActionList where activateCharacterCount greater than or equals to UPDATED_ACTIVATE_CHARACTER_COUNT
        defaultMActionShouldNotBeFound("activateCharacterCount.greaterOrEqualThan=" + UPDATED_ACTIVATE_CHARACTER_COUNT);
    }

    @Test
    @Transactional
    public void getAllMActionsByActivateCharacterCountIsLessThanSomething() throws Exception {
        // Initialize the database
        mActionRepository.saveAndFlush(mAction);

        // Get all the mActionList where activateCharacterCount less than or equals to DEFAULT_ACTIVATE_CHARACTER_COUNT
        defaultMActionShouldNotBeFound("activateCharacterCount.lessThan=" + DEFAULT_ACTIVATE_CHARACTER_COUNT);

        // Get all the mActionList where activateCharacterCount less than or equals to UPDATED_ACTIVATE_CHARACTER_COUNT
        defaultMActionShouldBeFound("activateCharacterCount.lessThan=" + UPDATED_ACTIVATE_CHARACTER_COUNT);
    }


    @Test
    @Transactional
    public void getAllMActionsByActionCutIdIsEqualToSomething() throws Exception {
        // Initialize the database
        mActionRepository.saveAndFlush(mAction);

        // Get all the mActionList where actionCutId equals to DEFAULT_ACTION_CUT_ID
        defaultMActionShouldBeFound("actionCutId.equals=" + DEFAULT_ACTION_CUT_ID);

        // Get all the mActionList where actionCutId equals to UPDATED_ACTION_CUT_ID
        defaultMActionShouldNotBeFound("actionCutId.equals=" + UPDATED_ACTION_CUT_ID);
    }

    @Test
    @Transactional
    public void getAllMActionsByActionCutIdIsInShouldWork() throws Exception {
        // Initialize the database
        mActionRepository.saveAndFlush(mAction);

        // Get all the mActionList where actionCutId in DEFAULT_ACTION_CUT_ID or UPDATED_ACTION_CUT_ID
        defaultMActionShouldBeFound("actionCutId.in=" + DEFAULT_ACTION_CUT_ID + "," + UPDATED_ACTION_CUT_ID);

        // Get all the mActionList where actionCutId equals to UPDATED_ACTION_CUT_ID
        defaultMActionShouldNotBeFound("actionCutId.in=" + UPDATED_ACTION_CUT_ID);
    }

    @Test
    @Transactional
    public void getAllMActionsByActionCutIdIsNullOrNotNull() throws Exception {
        // Initialize the database
        mActionRepository.saveAndFlush(mAction);

        // Get all the mActionList where actionCutId is not null
        defaultMActionShouldBeFound("actionCutId.specified=true");

        // Get all the mActionList where actionCutId is null
        defaultMActionShouldNotBeFound("actionCutId.specified=false");
    }

    @Test
    @Transactional
    public void getAllMActionsByActionCutIdIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mActionRepository.saveAndFlush(mAction);

        // Get all the mActionList where actionCutId greater than or equals to DEFAULT_ACTION_CUT_ID
        defaultMActionShouldBeFound("actionCutId.greaterOrEqualThan=" + DEFAULT_ACTION_CUT_ID);

        // Get all the mActionList where actionCutId greater than or equals to UPDATED_ACTION_CUT_ID
        defaultMActionShouldNotBeFound("actionCutId.greaterOrEqualThan=" + UPDATED_ACTION_CUT_ID);
    }

    @Test
    @Transactional
    public void getAllMActionsByActionCutIdIsLessThanSomething() throws Exception {
        // Initialize the database
        mActionRepository.saveAndFlush(mAction);

        // Get all the mActionList where actionCutId less than or equals to DEFAULT_ACTION_CUT_ID
        defaultMActionShouldNotBeFound("actionCutId.lessThan=" + DEFAULT_ACTION_CUT_ID);

        // Get all the mActionList where actionCutId less than or equals to UPDATED_ACTION_CUT_ID
        defaultMActionShouldBeFound("actionCutId.lessThan=" + UPDATED_ACTION_CUT_ID);
    }


    @Test
    @Transactional
    public void getAllMActionsByPowerupGroupIsEqualToSomething() throws Exception {
        // Initialize the database
        mActionRepository.saveAndFlush(mAction);

        // Get all the mActionList where powerupGroup equals to DEFAULT_POWERUP_GROUP
        defaultMActionShouldBeFound("powerupGroup.equals=" + DEFAULT_POWERUP_GROUP);

        // Get all the mActionList where powerupGroup equals to UPDATED_POWERUP_GROUP
        defaultMActionShouldNotBeFound("powerupGroup.equals=" + UPDATED_POWERUP_GROUP);
    }

    @Test
    @Transactional
    public void getAllMActionsByPowerupGroupIsInShouldWork() throws Exception {
        // Initialize the database
        mActionRepository.saveAndFlush(mAction);

        // Get all the mActionList where powerupGroup in DEFAULT_POWERUP_GROUP or UPDATED_POWERUP_GROUP
        defaultMActionShouldBeFound("powerupGroup.in=" + DEFAULT_POWERUP_GROUP + "," + UPDATED_POWERUP_GROUP);

        // Get all the mActionList where powerupGroup equals to UPDATED_POWERUP_GROUP
        defaultMActionShouldNotBeFound("powerupGroup.in=" + UPDATED_POWERUP_GROUP);
    }

    @Test
    @Transactional
    public void getAllMActionsByPowerupGroupIsNullOrNotNull() throws Exception {
        // Initialize the database
        mActionRepository.saveAndFlush(mAction);

        // Get all the mActionList where powerupGroup is not null
        defaultMActionShouldBeFound("powerupGroup.specified=true");

        // Get all the mActionList where powerupGroup is null
        defaultMActionShouldNotBeFound("powerupGroup.specified=false");
    }

    @Test
    @Transactional
    public void getAllMActionsByPowerupGroupIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mActionRepository.saveAndFlush(mAction);

        // Get all the mActionList where powerupGroup greater than or equals to DEFAULT_POWERUP_GROUP
        defaultMActionShouldBeFound("powerupGroup.greaterOrEqualThan=" + DEFAULT_POWERUP_GROUP);

        // Get all the mActionList where powerupGroup greater than or equals to UPDATED_POWERUP_GROUP
        defaultMActionShouldNotBeFound("powerupGroup.greaterOrEqualThan=" + UPDATED_POWERUP_GROUP);
    }

    @Test
    @Transactional
    public void getAllMActionsByPowerupGroupIsLessThanSomething() throws Exception {
        // Initialize the database
        mActionRepository.saveAndFlush(mAction);

        // Get all the mActionList where powerupGroup less than or equals to DEFAULT_POWERUP_GROUP
        defaultMActionShouldNotBeFound("powerupGroup.lessThan=" + DEFAULT_POWERUP_GROUP);

        // Get all the mActionList where powerupGroup less than or equals to UPDATED_POWERUP_GROUP
        defaultMActionShouldBeFound("powerupGroup.lessThan=" + UPDATED_POWERUP_GROUP);
    }


    @Test
    @Transactional
    public void getAllMActionsByMTargetActionGroupIsEqualToSomething() throws Exception {
        // Initialize the database
        MTargetActionGroup mTargetActionGroup = MTargetActionGroupResourceIT.createEntity(em);
        em.persist(mTargetActionGroup);
        em.flush();
        mAction.addMTargetActionGroup(mTargetActionGroup);
        mActionRepository.saveAndFlush(mAction);
        Long mTargetActionGroupId = mTargetActionGroup.getId();

        // Get all the mActionList where mTargetActionGroup equals to mTargetActionGroupId
        defaultMActionShouldBeFound("mTargetActionGroupId.equals=" + mTargetActionGroupId);

        // Get all the mActionList where mTargetActionGroup equals to mTargetActionGroupId + 1
        defaultMActionShouldNotBeFound("mTargetActionGroupId.equals=" + (mTargetActionGroupId + 1));
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultMActionShouldBeFound(String filter) throws Exception {
        restMActionMockMvc.perform(get("/api/m-actions?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(mAction.getId().intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME.toString())))
            .andExpect(jsonPath("$.[*].shortName").value(hasItem(DEFAULT_SHORT_NAME.toString())))
            .andExpect(jsonPath("$.[*].nameRuby").value(hasItem(DEFAULT_NAME_RUBY.toString())))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION.toString())))
            .andExpect(jsonPath("$.[*].effectDescription").value(hasItem(DEFAULT_EFFECT_DESCRIPTION.toString())))
            .andExpect(jsonPath("$.[*].initialCommand").value(hasItem(DEFAULT_INITIAL_COMMAND)))
            .andExpect(jsonPath("$.[*].rarity").value(hasItem(DEFAULT_RARITY)))
            .andExpect(jsonPath("$.[*].commandType").value(hasItem(DEFAULT_COMMAND_TYPE)))
            .andExpect(jsonPath("$.[*].ballConditionGround").value(hasItem(DEFAULT_BALL_CONDITION_GROUND)))
            .andExpect(jsonPath("$.[*].ballConditionLow").value(hasItem(DEFAULT_BALL_CONDITION_LOW)))
            .andExpect(jsonPath("$.[*].ballConditionHigh").value(hasItem(DEFAULT_BALL_CONDITION_HIGH)))
            .andExpect(jsonPath("$.[*].staminaLvMin").value(hasItem(DEFAULT_STAMINA_LV_MIN)))
            .andExpect(jsonPath("$.[*].staminaLvMax").value(hasItem(DEFAULT_STAMINA_LV_MAX)))
            .andExpect(jsonPath("$.[*].powerLvMin").value(hasItem(DEFAULT_POWER_LV_MIN)))
            .andExpect(jsonPath("$.[*].powerLvMax").value(hasItem(DEFAULT_POWER_LV_MAX)))
            .andExpect(jsonPath("$.[*].blowOffCount").value(hasItem(DEFAULT_BLOW_OFF_COUNT)))
            .andExpect(jsonPath("$.[*].mShootId").value(hasItem(DEFAULT_M_SHOOT_ID)))
            .andExpect(jsonPath("$.[*].foulRate").value(hasItem(DEFAULT_FOUL_RATE)))
            .andExpect(jsonPath("$.[*].distanceDecayType").value(hasItem(DEFAULT_DISTANCE_DECAY_TYPE)))
            .andExpect(jsonPath("$.[*].activateCharacterCount").value(hasItem(DEFAULT_ACTIVATE_CHARACTER_COUNT)))
            .andExpect(jsonPath("$.[*].actionCutId").value(hasItem(DEFAULT_ACTION_CUT_ID)))
            .andExpect(jsonPath("$.[*].powerupGroup").value(hasItem(DEFAULT_POWERUP_GROUP)));

        // Check, that the count call also returns 1
        restMActionMockMvc.perform(get("/api/m-actions/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultMActionShouldNotBeFound(String filter) throws Exception {
        restMActionMockMvc.perform(get("/api/m-actions?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restMActionMockMvc.perform(get("/api/m-actions/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("0"));
    }


    @Test
    @Transactional
    public void getNonExistingMAction() throws Exception {
        // Get the mAction
        restMActionMockMvc.perform(get("/api/m-actions/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateMAction() throws Exception {
        // Initialize the database
        mActionRepository.saveAndFlush(mAction);

        int databaseSizeBeforeUpdate = mActionRepository.findAll().size();

        // Update the mAction
        MAction updatedMAction = mActionRepository.findById(mAction.getId()).get();
        // Disconnect from session so that the updates on updatedMAction are not directly saved in db
        em.detach(updatedMAction);
        updatedMAction
            .name(UPDATED_NAME)
            .shortName(UPDATED_SHORT_NAME)
            .nameRuby(UPDATED_NAME_RUBY)
            .description(UPDATED_DESCRIPTION)
            .effectDescription(UPDATED_EFFECT_DESCRIPTION)
            .initialCommand(UPDATED_INITIAL_COMMAND)
            .rarity(UPDATED_RARITY)
            .commandType(UPDATED_COMMAND_TYPE)
            .ballConditionGround(UPDATED_BALL_CONDITION_GROUND)
            .ballConditionLow(UPDATED_BALL_CONDITION_LOW)
            .ballConditionHigh(UPDATED_BALL_CONDITION_HIGH)
            .staminaLvMin(UPDATED_STAMINA_LV_MIN)
            .staminaLvMax(UPDATED_STAMINA_LV_MAX)
            .powerLvMin(UPDATED_POWER_LV_MIN)
            .powerLvMax(UPDATED_POWER_LV_MAX)
            .blowOffCount(UPDATED_BLOW_OFF_COUNT)
            .mShootId(UPDATED_M_SHOOT_ID)
            .foulRate(UPDATED_FOUL_RATE)
            .distanceDecayType(UPDATED_DISTANCE_DECAY_TYPE)
            .activateCharacterCount(UPDATED_ACTIVATE_CHARACTER_COUNT)
            .actionCutId(UPDATED_ACTION_CUT_ID)
            .powerupGroup(UPDATED_POWERUP_GROUP);
        MActionDTO mActionDTO = mActionMapper.toDto(updatedMAction);

        restMActionMockMvc.perform(put("/api/m-actions")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mActionDTO)))
            .andExpect(status().isOk());

        // Validate the MAction in the database
        List<MAction> mActionList = mActionRepository.findAll();
        assertThat(mActionList).hasSize(databaseSizeBeforeUpdate);
        MAction testMAction = mActionList.get(mActionList.size() - 1);
        assertThat(testMAction.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testMAction.getShortName()).isEqualTo(UPDATED_SHORT_NAME);
        assertThat(testMAction.getNameRuby()).isEqualTo(UPDATED_NAME_RUBY);
        assertThat(testMAction.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
        assertThat(testMAction.getEffectDescription()).isEqualTo(UPDATED_EFFECT_DESCRIPTION);
        assertThat(testMAction.getInitialCommand()).isEqualTo(UPDATED_INITIAL_COMMAND);
        assertThat(testMAction.getRarity()).isEqualTo(UPDATED_RARITY);
        assertThat(testMAction.getCommandType()).isEqualTo(UPDATED_COMMAND_TYPE);
        assertThat(testMAction.getBallConditionGround()).isEqualTo(UPDATED_BALL_CONDITION_GROUND);
        assertThat(testMAction.getBallConditionLow()).isEqualTo(UPDATED_BALL_CONDITION_LOW);
        assertThat(testMAction.getBallConditionHigh()).isEqualTo(UPDATED_BALL_CONDITION_HIGH);
        assertThat(testMAction.getStaminaLvMin()).isEqualTo(UPDATED_STAMINA_LV_MIN);
        assertThat(testMAction.getStaminaLvMax()).isEqualTo(UPDATED_STAMINA_LV_MAX);
        assertThat(testMAction.getPowerLvMin()).isEqualTo(UPDATED_POWER_LV_MIN);
        assertThat(testMAction.getPowerLvMax()).isEqualTo(UPDATED_POWER_LV_MAX);
        assertThat(testMAction.getBlowOffCount()).isEqualTo(UPDATED_BLOW_OFF_COUNT);
        assertThat(testMAction.getmShootId()).isEqualTo(UPDATED_M_SHOOT_ID);
        assertThat(testMAction.getFoulRate()).isEqualTo(UPDATED_FOUL_RATE);
        assertThat(testMAction.getDistanceDecayType()).isEqualTo(UPDATED_DISTANCE_DECAY_TYPE);
        assertThat(testMAction.getActivateCharacterCount()).isEqualTo(UPDATED_ACTIVATE_CHARACTER_COUNT);
        assertThat(testMAction.getActionCutId()).isEqualTo(UPDATED_ACTION_CUT_ID);
        assertThat(testMAction.getPowerupGroup()).isEqualTo(UPDATED_POWERUP_GROUP);
    }

    @Test
    @Transactional
    public void updateNonExistingMAction() throws Exception {
        int databaseSizeBeforeUpdate = mActionRepository.findAll().size();

        // Create the MAction
        MActionDTO mActionDTO = mActionMapper.toDto(mAction);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restMActionMockMvc.perform(put("/api/m-actions")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mActionDTO)))
            .andExpect(status().isBadRequest());

        // Validate the MAction in the database
        List<MAction> mActionList = mActionRepository.findAll();
        assertThat(mActionList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteMAction() throws Exception {
        // Initialize the database
        mActionRepository.saveAndFlush(mAction);

        int databaseSizeBeforeDelete = mActionRepository.findAll().size();

        // Delete the mAction
        restMActionMockMvc.perform(delete("/api/m-actions/{id}", mAction.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database is empty
        List<MAction> mActionList = mActionRepository.findAll();
        assertThat(mActionList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(MAction.class);
        MAction mAction1 = new MAction();
        mAction1.setId(1L);
        MAction mAction2 = new MAction();
        mAction2.setId(mAction1.getId());
        assertThat(mAction1).isEqualTo(mAction2);
        mAction2.setId(2L);
        assertThat(mAction1).isNotEqualTo(mAction2);
        mAction1.setId(null);
        assertThat(mAction1).isNotEqualTo(mAction2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(MActionDTO.class);
        MActionDTO mActionDTO1 = new MActionDTO();
        mActionDTO1.setId(1L);
        MActionDTO mActionDTO2 = new MActionDTO();
        assertThat(mActionDTO1).isNotEqualTo(mActionDTO2);
        mActionDTO2.setId(mActionDTO1.getId());
        assertThat(mActionDTO1).isEqualTo(mActionDTO2);
        mActionDTO2.setId(2L);
        assertThat(mActionDTO1).isNotEqualTo(mActionDTO2);
        mActionDTO1.setId(null);
        assertThat(mActionDTO1).isNotEqualTo(mActionDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(mActionMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(mActionMapper.fromId(null)).isNull();
    }
}
