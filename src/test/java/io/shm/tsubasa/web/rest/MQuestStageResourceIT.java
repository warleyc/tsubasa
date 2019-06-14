package io.shm.tsubasa.web.rest;

import io.shm.tsubasa.TsubasaApp;
import io.shm.tsubasa.domain.MQuestStage;
import io.shm.tsubasa.domain.MQuestWorld;
import io.shm.tsubasa.repository.MQuestStageRepository;
import io.shm.tsubasa.service.MQuestStageService;
import io.shm.tsubasa.service.dto.MQuestStageDTO;
import io.shm.tsubasa.service.mapper.MQuestStageMapper;
import io.shm.tsubasa.web.rest.errors.ExceptionTranslator;
import io.shm.tsubasa.service.dto.MQuestStageCriteria;
import io.shm.tsubasa.service.MQuestStageQueryService;

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
 * Integration tests for the {@Link MQuestStageResource} REST controller.
 */
@SpringBootTest(classes = TsubasaApp.class)
public class MQuestStageResourceIT {

    private static final Integer DEFAULT_WORLD_ID = 1;
    private static final Integer UPDATED_WORLD_ID = 2;

    private static final Integer DEFAULT_NUMBER = 1;
    private static final Integer UPDATED_NUMBER = 2;

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_IMAGE_PATH = "AAAAAAAAAA";
    private static final String UPDATED_IMAGE_PATH = "BBBBBBBBBB";

    private static final String DEFAULT_CHARACTER_THUMBNAIL_PATH = "AAAAAAAAAA";
    private static final String UPDATED_CHARACTER_THUMBNAIL_PATH = "BBBBBBBBBB";

    private static final Integer DEFAULT_DIFFICULTY = 1;
    private static final Integer UPDATED_DIFFICULTY = 2;

    private static final Integer DEFAULT_STAGE_UNLOCK_PATTERN = 1;
    private static final Integer UPDATED_STAGE_UNLOCK_PATTERN = 2;

    private static final Integer DEFAULT_STORY_ONLY = 1;
    private static final Integer UPDATED_STORY_ONLY = 2;

    private static final Integer DEFAULT_FIRST_HALF_NPC_DECK_ID = 1;
    private static final Integer UPDATED_FIRST_HALF_NPC_DECK_ID = 2;

    private static final Integer DEFAULT_FIRST_HALF_ENVIRONMENT_ID = 1;
    private static final Integer UPDATED_FIRST_HALF_ENVIRONMENT_ID = 2;

    private static final Integer DEFAULT_SECOND_HALF_NPC_DECK_ID = 1;
    private static final Integer UPDATED_SECOND_HALF_NPC_DECK_ID = 2;

    private static final Integer DEFAULT_SECOND_HALF_ENVIRONMENT_ID = 1;
    private static final Integer UPDATED_SECOND_HALF_ENVIRONMENT_ID = 2;

    private static final Integer DEFAULT_EXTRA_FIRST_HALF_NPC_DECK_ID = 1;
    private static final Integer UPDATED_EXTRA_FIRST_HALF_NPC_DECK_ID = 2;

    private static final Integer DEFAULT_CONSUME_AP = 1;
    private static final Integer UPDATED_CONSUME_AP = 2;

    private static final Integer DEFAULT_KICK_OFF_TYPE = 1;
    private static final Integer UPDATED_KICK_OFF_TYPE = 2;

    private static final Integer DEFAULT_MATCH_MINUTE = 1;
    private static final Integer UPDATED_MATCH_MINUTE = 2;

    private static final Integer DEFAULT_ENABLE_EXTRA = 1;
    private static final Integer UPDATED_ENABLE_EXTRA = 2;

    private static final Integer DEFAULT_ENABLE_PK = 1;
    private static final Integer UPDATED_ENABLE_PK = 2;

    private static final Integer DEFAULT_AI_LEVEL = 1;
    private static final Integer UPDATED_AI_LEVEL = 2;

    private static final Integer DEFAULT_START_AT_SECOND_HALF = 1;
    private static final Integer UPDATED_START_AT_SECOND_HALF = 2;

    private static final Integer DEFAULT_START_SCORE = 1;
    private static final Integer UPDATED_START_SCORE = 2;

    private static final Integer DEFAULT_START_SCORE_OPPONENT = 1;
    private static final Integer UPDATED_START_SCORE_OPPONENT = 2;

    private static final Integer DEFAULT_CONDITION_ID = 1;
    private static final Integer UPDATED_CONDITION_ID = 2;

    private static final Integer DEFAULT_OPTION_ID = 1;
    private static final Integer UPDATED_OPTION_ID = 2;

    private static final Integer DEFAULT_DECK_CONDITION_ID = 1;
    private static final Integer UPDATED_DECK_CONDITION_ID = 2;

    @Autowired
    private MQuestStageRepository mQuestStageRepository;

    @Autowired
    private MQuestStageMapper mQuestStageMapper;

    @Autowired
    private MQuestStageService mQuestStageService;

    @Autowired
    private MQuestStageQueryService mQuestStageQueryService;

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

    private MockMvc restMQuestStageMockMvc;

    private MQuestStage mQuestStage;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final MQuestStageResource mQuestStageResource = new MQuestStageResource(mQuestStageService, mQuestStageQueryService);
        this.restMQuestStageMockMvc = MockMvcBuilders.standaloneSetup(mQuestStageResource)
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
    public static MQuestStage createEntity(EntityManager em) {
        MQuestStage mQuestStage = new MQuestStage()
            .worldId(DEFAULT_WORLD_ID)
            .number(DEFAULT_NUMBER)
            .name(DEFAULT_NAME)
            .imagePath(DEFAULT_IMAGE_PATH)
            .characterThumbnailPath(DEFAULT_CHARACTER_THUMBNAIL_PATH)
            .difficulty(DEFAULT_DIFFICULTY)
            .stageUnlockPattern(DEFAULT_STAGE_UNLOCK_PATTERN)
            .storyOnly(DEFAULT_STORY_ONLY)
            .firstHalfNpcDeckId(DEFAULT_FIRST_HALF_NPC_DECK_ID)
            .firstHalfEnvironmentId(DEFAULT_FIRST_HALF_ENVIRONMENT_ID)
            .secondHalfNpcDeckId(DEFAULT_SECOND_HALF_NPC_DECK_ID)
            .secondHalfEnvironmentId(DEFAULT_SECOND_HALF_ENVIRONMENT_ID)
            .extraFirstHalfNpcDeckId(DEFAULT_EXTRA_FIRST_HALF_NPC_DECK_ID)
            .consumeAp(DEFAULT_CONSUME_AP)
            .kickOffType(DEFAULT_KICK_OFF_TYPE)
            .matchMinute(DEFAULT_MATCH_MINUTE)
            .enableExtra(DEFAULT_ENABLE_EXTRA)
            .enablePk(DEFAULT_ENABLE_PK)
            .aiLevel(DEFAULT_AI_LEVEL)
            .startAtSecondHalf(DEFAULT_START_AT_SECOND_HALF)
            .startScore(DEFAULT_START_SCORE)
            .startScoreOpponent(DEFAULT_START_SCORE_OPPONENT)
            .conditionId(DEFAULT_CONDITION_ID)
            .optionId(DEFAULT_OPTION_ID)
            .deckConditionId(DEFAULT_DECK_CONDITION_ID);
        // Add required entity
        MQuestWorld mQuestWorld;
        if (TestUtil.findAll(em, MQuestWorld.class).isEmpty()) {
            mQuestWorld = MQuestWorldResourceIT.createEntity(em);
            em.persist(mQuestWorld);
            em.flush();
        } else {
            mQuestWorld = TestUtil.findAll(em, MQuestWorld.class).get(0);
        }
        mQuestStage.setId(mQuestWorld);
        return mQuestStage;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static MQuestStage createUpdatedEntity(EntityManager em) {
        MQuestStage mQuestStage = new MQuestStage()
            .worldId(UPDATED_WORLD_ID)
            .number(UPDATED_NUMBER)
            .name(UPDATED_NAME)
            .imagePath(UPDATED_IMAGE_PATH)
            .characterThumbnailPath(UPDATED_CHARACTER_THUMBNAIL_PATH)
            .difficulty(UPDATED_DIFFICULTY)
            .stageUnlockPattern(UPDATED_STAGE_UNLOCK_PATTERN)
            .storyOnly(UPDATED_STORY_ONLY)
            .firstHalfNpcDeckId(UPDATED_FIRST_HALF_NPC_DECK_ID)
            .firstHalfEnvironmentId(UPDATED_FIRST_HALF_ENVIRONMENT_ID)
            .secondHalfNpcDeckId(UPDATED_SECOND_HALF_NPC_DECK_ID)
            .secondHalfEnvironmentId(UPDATED_SECOND_HALF_ENVIRONMENT_ID)
            .extraFirstHalfNpcDeckId(UPDATED_EXTRA_FIRST_HALF_NPC_DECK_ID)
            .consumeAp(UPDATED_CONSUME_AP)
            .kickOffType(UPDATED_KICK_OFF_TYPE)
            .matchMinute(UPDATED_MATCH_MINUTE)
            .enableExtra(UPDATED_ENABLE_EXTRA)
            .enablePk(UPDATED_ENABLE_PK)
            .aiLevel(UPDATED_AI_LEVEL)
            .startAtSecondHalf(UPDATED_START_AT_SECOND_HALF)
            .startScore(UPDATED_START_SCORE)
            .startScoreOpponent(UPDATED_START_SCORE_OPPONENT)
            .conditionId(UPDATED_CONDITION_ID)
            .optionId(UPDATED_OPTION_ID)
            .deckConditionId(UPDATED_DECK_CONDITION_ID);
        // Add required entity
        MQuestWorld mQuestWorld;
        if (TestUtil.findAll(em, MQuestWorld.class).isEmpty()) {
            mQuestWorld = MQuestWorldResourceIT.createUpdatedEntity(em);
            em.persist(mQuestWorld);
            em.flush();
        } else {
            mQuestWorld = TestUtil.findAll(em, MQuestWorld.class).get(0);
        }
        mQuestStage.setId(mQuestWorld);
        return mQuestStage;
    }

    @BeforeEach
    public void initTest() {
        mQuestStage = createEntity(em);
    }

    @Test
    @Transactional
    public void createMQuestStage() throws Exception {
        int databaseSizeBeforeCreate = mQuestStageRepository.findAll().size();

        // Create the MQuestStage
        MQuestStageDTO mQuestStageDTO = mQuestStageMapper.toDto(mQuestStage);
        restMQuestStageMockMvc.perform(post("/api/m-quest-stages")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mQuestStageDTO)))
            .andExpect(status().isCreated());

        // Validate the MQuestStage in the database
        List<MQuestStage> mQuestStageList = mQuestStageRepository.findAll();
        assertThat(mQuestStageList).hasSize(databaseSizeBeforeCreate + 1);
        MQuestStage testMQuestStage = mQuestStageList.get(mQuestStageList.size() - 1);
        assertThat(testMQuestStage.getWorldId()).isEqualTo(DEFAULT_WORLD_ID);
        assertThat(testMQuestStage.getNumber()).isEqualTo(DEFAULT_NUMBER);
        assertThat(testMQuestStage.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testMQuestStage.getImagePath()).isEqualTo(DEFAULT_IMAGE_PATH);
        assertThat(testMQuestStage.getCharacterThumbnailPath()).isEqualTo(DEFAULT_CHARACTER_THUMBNAIL_PATH);
        assertThat(testMQuestStage.getDifficulty()).isEqualTo(DEFAULT_DIFFICULTY);
        assertThat(testMQuestStage.getStageUnlockPattern()).isEqualTo(DEFAULT_STAGE_UNLOCK_PATTERN);
        assertThat(testMQuestStage.getStoryOnly()).isEqualTo(DEFAULT_STORY_ONLY);
        assertThat(testMQuestStage.getFirstHalfNpcDeckId()).isEqualTo(DEFAULT_FIRST_HALF_NPC_DECK_ID);
        assertThat(testMQuestStage.getFirstHalfEnvironmentId()).isEqualTo(DEFAULT_FIRST_HALF_ENVIRONMENT_ID);
        assertThat(testMQuestStage.getSecondHalfNpcDeckId()).isEqualTo(DEFAULT_SECOND_HALF_NPC_DECK_ID);
        assertThat(testMQuestStage.getSecondHalfEnvironmentId()).isEqualTo(DEFAULT_SECOND_HALF_ENVIRONMENT_ID);
        assertThat(testMQuestStage.getExtraFirstHalfNpcDeckId()).isEqualTo(DEFAULT_EXTRA_FIRST_HALF_NPC_DECK_ID);
        assertThat(testMQuestStage.getConsumeAp()).isEqualTo(DEFAULT_CONSUME_AP);
        assertThat(testMQuestStage.getKickOffType()).isEqualTo(DEFAULT_KICK_OFF_TYPE);
        assertThat(testMQuestStage.getMatchMinute()).isEqualTo(DEFAULT_MATCH_MINUTE);
        assertThat(testMQuestStage.getEnableExtra()).isEqualTo(DEFAULT_ENABLE_EXTRA);
        assertThat(testMQuestStage.getEnablePk()).isEqualTo(DEFAULT_ENABLE_PK);
        assertThat(testMQuestStage.getAiLevel()).isEqualTo(DEFAULT_AI_LEVEL);
        assertThat(testMQuestStage.getStartAtSecondHalf()).isEqualTo(DEFAULT_START_AT_SECOND_HALF);
        assertThat(testMQuestStage.getStartScore()).isEqualTo(DEFAULT_START_SCORE);
        assertThat(testMQuestStage.getStartScoreOpponent()).isEqualTo(DEFAULT_START_SCORE_OPPONENT);
        assertThat(testMQuestStage.getConditionId()).isEqualTo(DEFAULT_CONDITION_ID);
        assertThat(testMQuestStage.getOptionId()).isEqualTo(DEFAULT_OPTION_ID);
        assertThat(testMQuestStage.getDeckConditionId()).isEqualTo(DEFAULT_DECK_CONDITION_ID);
    }

    @Test
    @Transactional
    public void createMQuestStageWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = mQuestStageRepository.findAll().size();

        // Create the MQuestStage with an existing ID
        mQuestStage.setId(1L);
        MQuestStageDTO mQuestStageDTO = mQuestStageMapper.toDto(mQuestStage);

        // An entity with an existing ID cannot be created, so this API call must fail
        restMQuestStageMockMvc.perform(post("/api/m-quest-stages")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mQuestStageDTO)))
            .andExpect(status().isBadRequest());

        // Validate the MQuestStage in the database
        List<MQuestStage> mQuestStageList = mQuestStageRepository.findAll();
        assertThat(mQuestStageList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkWorldIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = mQuestStageRepository.findAll().size();
        // set the field null
        mQuestStage.setWorldId(null);

        // Create the MQuestStage, which fails.
        MQuestStageDTO mQuestStageDTO = mQuestStageMapper.toDto(mQuestStage);

        restMQuestStageMockMvc.perform(post("/api/m-quest-stages")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mQuestStageDTO)))
            .andExpect(status().isBadRequest());

        List<MQuestStage> mQuestStageList = mQuestStageRepository.findAll();
        assertThat(mQuestStageList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkNumberIsRequired() throws Exception {
        int databaseSizeBeforeTest = mQuestStageRepository.findAll().size();
        // set the field null
        mQuestStage.setNumber(null);

        // Create the MQuestStage, which fails.
        MQuestStageDTO mQuestStageDTO = mQuestStageMapper.toDto(mQuestStage);

        restMQuestStageMockMvc.perform(post("/api/m-quest-stages")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mQuestStageDTO)))
            .andExpect(status().isBadRequest());

        List<MQuestStage> mQuestStageList = mQuestStageRepository.findAll();
        assertThat(mQuestStageList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkDifficultyIsRequired() throws Exception {
        int databaseSizeBeforeTest = mQuestStageRepository.findAll().size();
        // set the field null
        mQuestStage.setDifficulty(null);

        // Create the MQuestStage, which fails.
        MQuestStageDTO mQuestStageDTO = mQuestStageMapper.toDto(mQuestStage);

        restMQuestStageMockMvc.perform(post("/api/m-quest-stages")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mQuestStageDTO)))
            .andExpect(status().isBadRequest());

        List<MQuestStage> mQuestStageList = mQuestStageRepository.findAll();
        assertThat(mQuestStageList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkStageUnlockPatternIsRequired() throws Exception {
        int databaseSizeBeforeTest = mQuestStageRepository.findAll().size();
        // set the field null
        mQuestStage.setStageUnlockPattern(null);

        // Create the MQuestStage, which fails.
        MQuestStageDTO mQuestStageDTO = mQuestStageMapper.toDto(mQuestStage);

        restMQuestStageMockMvc.perform(post("/api/m-quest-stages")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mQuestStageDTO)))
            .andExpect(status().isBadRequest());

        List<MQuestStage> mQuestStageList = mQuestStageRepository.findAll();
        assertThat(mQuestStageList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkStoryOnlyIsRequired() throws Exception {
        int databaseSizeBeforeTest = mQuestStageRepository.findAll().size();
        // set the field null
        mQuestStage.setStoryOnly(null);

        // Create the MQuestStage, which fails.
        MQuestStageDTO mQuestStageDTO = mQuestStageMapper.toDto(mQuestStage);

        restMQuestStageMockMvc.perform(post("/api/m-quest-stages")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mQuestStageDTO)))
            .andExpect(status().isBadRequest());

        List<MQuestStage> mQuestStageList = mQuestStageRepository.findAll();
        assertThat(mQuestStageList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkFirstHalfNpcDeckIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = mQuestStageRepository.findAll().size();
        // set the field null
        mQuestStage.setFirstHalfNpcDeckId(null);

        // Create the MQuestStage, which fails.
        MQuestStageDTO mQuestStageDTO = mQuestStageMapper.toDto(mQuestStage);

        restMQuestStageMockMvc.perform(post("/api/m-quest-stages")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mQuestStageDTO)))
            .andExpect(status().isBadRequest());

        List<MQuestStage> mQuestStageList = mQuestStageRepository.findAll();
        assertThat(mQuestStageList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkFirstHalfEnvironmentIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = mQuestStageRepository.findAll().size();
        // set the field null
        mQuestStage.setFirstHalfEnvironmentId(null);

        // Create the MQuestStage, which fails.
        MQuestStageDTO mQuestStageDTO = mQuestStageMapper.toDto(mQuestStage);

        restMQuestStageMockMvc.perform(post("/api/m-quest-stages")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mQuestStageDTO)))
            .andExpect(status().isBadRequest());

        List<MQuestStage> mQuestStageList = mQuestStageRepository.findAll();
        assertThat(mQuestStageList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkSecondHalfNpcDeckIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = mQuestStageRepository.findAll().size();
        // set the field null
        mQuestStage.setSecondHalfNpcDeckId(null);

        // Create the MQuestStage, which fails.
        MQuestStageDTO mQuestStageDTO = mQuestStageMapper.toDto(mQuestStage);

        restMQuestStageMockMvc.perform(post("/api/m-quest-stages")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mQuestStageDTO)))
            .andExpect(status().isBadRequest());

        List<MQuestStage> mQuestStageList = mQuestStageRepository.findAll();
        assertThat(mQuestStageList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkSecondHalfEnvironmentIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = mQuestStageRepository.findAll().size();
        // set the field null
        mQuestStage.setSecondHalfEnvironmentId(null);

        // Create the MQuestStage, which fails.
        MQuestStageDTO mQuestStageDTO = mQuestStageMapper.toDto(mQuestStage);

        restMQuestStageMockMvc.perform(post("/api/m-quest-stages")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mQuestStageDTO)))
            .andExpect(status().isBadRequest());

        List<MQuestStage> mQuestStageList = mQuestStageRepository.findAll();
        assertThat(mQuestStageList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkExtraFirstHalfNpcDeckIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = mQuestStageRepository.findAll().size();
        // set the field null
        mQuestStage.setExtraFirstHalfNpcDeckId(null);

        // Create the MQuestStage, which fails.
        MQuestStageDTO mQuestStageDTO = mQuestStageMapper.toDto(mQuestStage);

        restMQuestStageMockMvc.perform(post("/api/m-quest-stages")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mQuestStageDTO)))
            .andExpect(status().isBadRequest());

        List<MQuestStage> mQuestStageList = mQuestStageRepository.findAll();
        assertThat(mQuestStageList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkConsumeApIsRequired() throws Exception {
        int databaseSizeBeforeTest = mQuestStageRepository.findAll().size();
        // set the field null
        mQuestStage.setConsumeAp(null);

        // Create the MQuestStage, which fails.
        MQuestStageDTO mQuestStageDTO = mQuestStageMapper.toDto(mQuestStage);

        restMQuestStageMockMvc.perform(post("/api/m-quest-stages")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mQuestStageDTO)))
            .andExpect(status().isBadRequest());

        List<MQuestStage> mQuestStageList = mQuestStageRepository.findAll();
        assertThat(mQuestStageList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkKickOffTypeIsRequired() throws Exception {
        int databaseSizeBeforeTest = mQuestStageRepository.findAll().size();
        // set the field null
        mQuestStage.setKickOffType(null);

        // Create the MQuestStage, which fails.
        MQuestStageDTO mQuestStageDTO = mQuestStageMapper.toDto(mQuestStage);

        restMQuestStageMockMvc.perform(post("/api/m-quest-stages")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mQuestStageDTO)))
            .andExpect(status().isBadRequest());

        List<MQuestStage> mQuestStageList = mQuestStageRepository.findAll();
        assertThat(mQuestStageList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkMatchMinuteIsRequired() throws Exception {
        int databaseSizeBeforeTest = mQuestStageRepository.findAll().size();
        // set the field null
        mQuestStage.setMatchMinute(null);

        // Create the MQuestStage, which fails.
        MQuestStageDTO mQuestStageDTO = mQuestStageMapper.toDto(mQuestStage);

        restMQuestStageMockMvc.perform(post("/api/m-quest-stages")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mQuestStageDTO)))
            .andExpect(status().isBadRequest());

        List<MQuestStage> mQuestStageList = mQuestStageRepository.findAll();
        assertThat(mQuestStageList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkEnableExtraIsRequired() throws Exception {
        int databaseSizeBeforeTest = mQuestStageRepository.findAll().size();
        // set the field null
        mQuestStage.setEnableExtra(null);

        // Create the MQuestStage, which fails.
        MQuestStageDTO mQuestStageDTO = mQuestStageMapper.toDto(mQuestStage);

        restMQuestStageMockMvc.perform(post("/api/m-quest-stages")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mQuestStageDTO)))
            .andExpect(status().isBadRequest());

        List<MQuestStage> mQuestStageList = mQuestStageRepository.findAll();
        assertThat(mQuestStageList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkEnablePkIsRequired() throws Exception {
        int databaseSizeBeforeTest = mQuestStageRepository.findAll().size();
        // set the field null
        mQuestStage.setEnablePk(null);

        // Create the MQuestStage, which fails.
        MQuestStageDTO mQuestStageDTO = mQuestStageMapper.toDto(mQuestStage);

        restMQuestStageMockMvc.perform(post("/api/m-quest-stages")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mQuestStageDTO)))
            .andExpect(status().isBadRequest());

        List<MQuestStage> mQuestStageList = mQuestStageRepository.findAll();
        assertThat(mQuestStageList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkAiLevelIsRequired() throws Exception {
        int databaseSizeBeforeTest = mQuestStageRepository.findAll().size();
        // set the field null
        mQuestStage.setAiLevel(null);

        // Create the MQuestStage, which fails.
        MQuestStageDTO mQuestStageDTO = mQuestStageMapper.toDto(mQuestStage);

        restMQuestStageMockMvc.perform(post("/api/m-quest-stages")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mQuestStageDTO)))
            .andExpect(status().isBadRequest());

        List<MQuestStage> mQuestStageList = mQuestStageRepository.findAll();
        assertThat(mQuestStageList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkStartAtSecondHalfIsRequired() throws Exception {
        int databaseSizeBeforeTest = mQuestStageRepository.findAll().size();
        // set the field null
        mQuestStage.setStartAtSecondHalf(null);

        // Create the MQuestStage, which fails.
        MQuestStageDTO mQuestStageDTO = mQuestStageMapper.toDto(mQuestStage);

        restMQuestStageMockMvc.perform(post("/api/m-quest-stages")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mQuestStageDTO)))
            .andExpect(status().isBadRequest());

        List<MQuestStage> mQuestStageList = mQuestStageRepository.findAll();
        assertThat(mQuestStageList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkStartScoreIsRequired() throws Exception {
        int databaseSizeBeforeTest = mQuestStageRepository.findAll().size();
        // set the field null
        mQuestStage.setStartScore(null);

        // Create the MQuestStage, which fails.
        MQuestStageDTO mQuestStageDTO = mQuestStageMapper.toDto(mQuestStage);

        restMQuestStageMockMvc.perform(post("/api/m-quest-stages")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mQuestStageDTO)))
            .andExpect(status().isBadRequest());

        List<MQuestStage> mQuestStageList = mQuestStageRepository.findAll();
        assertThat(mQuestStageList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkStartScoreOpponentIsRequired() throws Exception {
        int databaseSizeBeforeTest = mQuestStageRepository.findAll().size();
        // set the field null
        mQuestStage.setStartScoreOpponent(null);

        // Create the MQuestStage, which fails.
        MQuestStageDTO mQuestStageDTO = mQuestStageMapper.toDto(mQuestStage);

        restMQuestStageMockMvc.perform(post("/api/m-quest-stages")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mQuestStageDTO)))
            .andExpect(status().isBadRequest());

        List<MQuestStage> mQuestStageList = mQuestStageRepository.findAll();
        assertThat(mQuestStageList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllMQuestStages() throws Exception {
        // Initialize the database
        mQuestStageRepository.saveAndFlush(mQuestStage);

        // Get all the mQuestStageList
        restMQuestStageMockMvc.perform(get("/api/m-quest-stages?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(mQuestStage.getId().intValue())))
            .andExpect(jsonPath("$.[*].worldId").value(hasItem(DEFAULT_WORLD_ID)))
            .andExpect(jsonPath("$.[*].number").value(hasItem(DEFAULT_NUMBER)))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME.toString())))
            .andExpect(jsonPath("$.[*].imagePath").value(hasItem(DEFAULT_IMAGE_PATH.toString())))
            .andExpect(jsonPath("$.[*].characterThumbnailPath").value(hasItem(DEFAULT_CHARACTER_THUMBNAIL_PATH.toString())))
            .andExpect(jsonPath("$.[*].difficulty").value(hasItem(DEFAULT_DIFFICULTY)))
            .andExpect(jsonPath("$.[*].stageUnlockPattern").value(hasItem(DEFAULT_STAGE_UNLOCK_PATTERN)))
            .andExpect(jsonPath("$.[*].storyOnly").value(hasItem(DEFAULT_STORY_ONLY)))
            .andExpect(jsonPath("$.[*].firstHalfNpcDeckId").value(hasItem(DEFAULT_FIRST_HALF_NPC_DECK_ID)))
            .andExpect(jsonPath("$.[*].firstHalfEnvironmentId").value(hasItem(DEFAULT_FIRST_HALF_ENVIRONMENT_ID)))
            .andExpect(jsonPath("$.[*].secondHalfNpcDeckId").value(hasItem(DEFAULT_SECOND_HALF_NPC_DECK_ID)))
            .andExpect(jsonPath("$.[*].secondHalfEnvironmentId").value(hasItem(DEFAULT_SECOND_HALF_ENVIRONMENT_ID)))
            .andExpect(jsonPath("$.[*].extraFirstHalfNpcDeckId").value(hasItem(DEFAULT_EXTRA_FIRST_HALF_NPC_DECK_ID)))
            .andExpect(jsonPath("$.[*].consumeAp").value(hasItem(DEFAULT_CONSUME_AP)))
            .andExpect(jsonPath("$.[*].kickOffType").value(hasItem(DEFAULT_KICK_OFF_TYPE)))
            .andExpect(jsonPath("$.[*].matchMinute").value(hasItem(DEFAULT_MATCH_MINUTE)))
            .andExpect(jsonPath("$.[*].enableExtra").value(hasItem(DEFAULT_ENABLE_EXTRA)))
            .andExpect(jsonPath("$.[*].enablePk").value(hasItem(DEFAULT_ENABLE_PK)))
            .andExpect(jsonPath("$.[*].aiLevel").value(hasItem(DEFAULT_AI_LEVEL)))
            .andExpect(jsonPath("$.[*].startAtSecondHalf").value(hasItem(DEFAULT_START_AT_SECOND_HALF)))
            .andExpect(jsonPath("$.[*].startScore").value(hasItem(DEFAULT_START_SCORE)))
            .andExpect(jsonPath("$.[*].startScoreOpponent").value(hasItem(DEFAULT_START_SCORE_OPPONENT)))
            .andExpect(jsonPath("$.[*].conditionId").value(hasItem(DEFAULT_CONDITION_ID)))
            .andExpect(jsonPath("$.[*].optionId").value(hasItem(DEFAULT_OPTION_ID)))
            .andExpect(jsonPath("$.[*].deckConditionId").value(hasItem(DEFAULT_DECK_CONDITION_ID)));
    }
    
    @Test
    @Transactional
    public void getMQuestStage() throws Exception {
        // Initialize the database
        mQuestStageRepository.saveAndFlush(mQuestStage);

        // Get the mQuestStage
        restMQuestStageMockMvc.perform(get("/api/m-quest-stages/{id}", mQuestStage.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(mQuestStage.getId().intValue()))
            .andExpect(jsonPath("$.worldId").value(DEFAULT_WORLD_ID))
            .andExpect(jsonPath("$.number").value(DEFAULT_NUMBER))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME.toString()))
            .andExpect(jsonPath("$.imagePath").value(DEFAULT_IMAGE_PATH.toString()))
            .andExpect(jsonPath("$.characterThumbnailPath").value(DEFAULT_CHARACTER_THUMBNAIL_PATH.toString()))
            .andExpect(jsonPath("$.difficulty").value(DEFAULT_DIFFICULTY))
            .andExpect(jsonPath("$.stageUnlockPattern").value(DEFAULT_STAGE_UNLOCK_PATTERN))
            .andExpect(jsonPath("$.storyOnly").value(DEFAULT_STORY_ONLY))
            .andExpect(jsonPath("$.firstHalfNpcDeckId").value(DEFAULT_FIRST_HALF_NPC_DECK_ID))
            .andExpect(jsonPath("$.firstHalfEnvironmentId").value(DEFAULT_FIRST_HALF_ENVIRONMENT_ID))
            .andExpect(jsonPath("$.secondHalfNpcDeckId").value(DEFAULT_SECOND_HALF_NPC_DECK_ID))
            .andExpect(jsonPath("$.secondHalfEnvironmentId").value(DEFAULT_SECOND_HALF_ENVIRONMENT_ID))
            .andExpect(jsonPath("$.extraFirstHalfNpcDeckId").value(DEFAULT_EXTRA_FIRST_HALF_NPC_DECK_ID))
            .andExpect(jsonPath("$.consumeAp").value(DEFAULT_CONSUME_AP))
            .andExpect(jsonPath("$.kickOffType").value(DEFAULT_KICK_OFF_TYPE))
            .andExpect(jsonPath("$.matchMinute").value(DEFAULT_MATCH_MINUTE))
            .andExpect(jsonPath("$.enableExtra").value(DEFAULT_ENABLE_EXTRA))
            .andExpect(jsonPath("$.enablePk").value(DEFAULT_ENABLE_PK))
            .andExpect(jsonPath("$.aiLevel").value(DEFAULT_AI_LEVEL))
            .andExpect(jsonPath("$.startAtSecondHalf").value(DEFAULT_START_AT_SECOND_HALF))
            .andExpect(jsonPath("$.startScore").value(DEFAULT_START_SCORE))
            .andExpect(jsonPath("$.startScoreOpponent").value(DEFAULT_START_SCORE_OPPONENT))
            .andExpect(jsonPath("$.conditionId").value(DEFAULT_CONDITION_ID))
            .andExpect(jsonPath("$.optionId").value(DEFAULT_OPTION_ID))
            .andExpect(jsonPath("$.deckConditionId").value(DEFAULT_DECK_CONDITION_ID));
    }

    @Test
    @Transactional
    public void getAllMQuestStagesByWorldIdIsEqualToSomething() throws Exception {
        // Initialize the database
        mQuestStageRepository.saveAndFlush(mQuestStage);

        // Get all the mQuestStageList where worldId equals to DEFAULT_WORLD_ID
        defaultMQuestStageShouldBeFound("worldId.equals=" + DEFAULT_WORLD_ID);

        // Get all the mQuestStageList where worldId equals to UPDATED_WORLD_ID
        defaultMQuestStageShouldNotBeFound("worldId.equals=" + UPDATED_WORLD_ID);
    }

    @Test
    @Transactional
    public void getAllMQuestStagesByWorldIdIsInShouldWork() throws Exception {
        // Initialize the database
        mQuestStageRepository.saveAndFlush(mQuestStage);

        // Get all the mQuestStageList where worldId in DEFAULT_WORLD_ID or UPDATED_WORLD_ID
        defaultMQuestStageShouldBeFound("worldId.in=" + DEFAULT_WORLD_ID + "," + UPDATED_WORLD_ID);

        // Get all the mQuestStageList where worldId equals to UPDATED_WORLD_ID
        defaultMQuestStageShouldNotBeFound("worldId.in=" + UPDATED_WORLD_ID);
    }

    @Test
    @Transactional
    public void getAllMQuestStagesByWorldIdIsNullOrNotNull() throws Exception {
        // Initialize the database
        mQuestStageRepository.saveAndFlush(mQuestStage);

        // Get all the mQuestStageList where worldId is not null
        defaultMQuestStageShouldBeFound("worldId.specified=true");

        // Get all the mQuestStageList where worldId is null
        defaultMQuestStageShouldNotBeFound("worldId.specified=false");
    }

    @Test
    @Transactional
    public void getAllMQuestStagesByWorldIdIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mQuestStageRepository.saveAndFlush(mQuestStage);

        // Get all the mQuestStageList where worldId greater than or equals to DEFAULT_WORLD_ID
        defaultMQuestStageShouldBeFound("worldId.greaterOrEqualThan=" + DEFAULT_WORLD_ID);

        // Get all the mQuestStageList where worldId greater than or equals to UPDATED_WORLD_ID
        defaultMQuestStageShouldNotBeFound("worldId.greaterOrEqualThan=" + UPDATED_WORLD_ID);
    }

    @Test
    @Transactional
    public void getAllMQuestStagesByWorldIdIsLessThanSomething() throws Exception {
        // Initialize the database
        mQuestStageRepository.saveAndFlush(mQuestStage);

        // Get all the mQuestStageList where worldId less than or equals to DEFAULT_WORLD_ID
        defaultMQuestStageShouldNotBeFound("worldId.lessThan=" + DEFAULT_WORLD_ID);

        // Get all the mQuestStageList where worldId less than or equals to UPDATED_WORLD_ID
        defaultMQuestStageShouldBeFound("worldId.lessThan=" + UPDATED_WORLD_ID);
    }


    @Test
    @Transactional
    public void getAllMQuestStagesByNumberIsEqualToSomething() throws Exception {
        // Initialize the database
        mQuestStageRepository.saveAndFlush(mQuestStage);

        // Get all the mQuestStageList where number equals to DEFAULT_NUMBER
        defaultMQuestStageShouldBeFound("number.equals=" + DEFAULT_NUMBER);

        // Get all the mQuestStageList where number equals to UPDATED_NUMBER
        defaultMQuestStageShouldNotBeFound("number.equals=" + UPDATED_NUMBER);
    }

    @Test
    @Transactional
    public void getAllMQuestStagesByNumberIsInShouldWork() throws Exception {
        // Initialize the database
        mQuestStageRepository.saveAndFlush(mQuestStage);

        // Get all the mQuestStageList where number in DEFAULT_NUMBER or UPDATED_NUMBER
        defaultMQuestStageShouldBeFound("number.in=" + DEFAULT_NUMBER + "," + UPDATED_NUMBER);

        // Get all the mQuestStageList where number equals to UPDATED_NUMBER
        defaultMQuestStageShouldNotBeFound("number.in=" + UPDATED_NUMBER);
    }

    @Test
    @Transactional
    public void getAllMQuestStagesByNumberIsNullOrNotNull() throws Exception {
        // Initialize the database
        mQuestStageRepository.saveAndFlush(mQuestStage);

        // Get all the mQuestStageList where number is not null
        defaultMQuestStageShouldBeFound("number.specified=true");

        // Get all the mQuestStageList where number is null
        defaultMQuestStageShouldNotBeFound("number.specified=false");
    }

    @Test
    @Transactional
    public void getAllMQuestStagesByNumberIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mQuestStageRepository.saveAndFlush(mQuestStage);

        // Get all the mQuestStageList where number greater than or equals to DEFAULT_NUMBER
        defaultMQuestStageShouldBeFound("number.greaterOrEqualThan=" + DEFAULT_NUMBER);

        // Get all the mQuestStageList where number greater than or equals to UPDATED_NUMBER
        defaultMQuestStageShouldNotBeFound("number.greaterOrEqualThan=" + UPDATED_NUMBER);
    }

    @Test
    @Transactional
    public void getAllMQuestStagesByNumberIsLessThanSomething() throws Exception {
        // Initialize the database
        mQuestStageRepository.saveAndFlush(mQuestStage);

        // Get all the mQuestStageList where number less than or equals to DEFAULT_NUMBER
        defaultMQuestStageShouldNotBeFound("number.lessThan=" + DEFAULT_NUMBER);

        // Get all the mQuestStageList where number less than or equals to UPDATED_NUMBER
        defaultMQuestStageShouldBeFound("number.lessThan=" + UPDATED_NUMBER);
    }


    @Test
    @Transactional
    public void getAllMQuestStagesByDifficultyIsEqualToSomething() throws Exception {
        // Initialize the database
        mQuestStageRepository.saveAndFlush(mQuestStage);

        // Get all the mQuestStageList where difficulty equals to DEFAULT_DIFFICULTY
        defaultMQuestStageShouldBeFound("difficulty.equals=" + DEFAULT_DIFFICULTY);

        // Get all the mQuestStageList where difficulty equals to UPDATED_DIFFICULTY
        defaultMQuestStageShouldNotBeFound("difficulty.equals=" + UPDATED_DIFFICULTY);
    }

    @Test
    @Transactional
    public void getAllMQuestStagesByDifficultyIsInShouldWork() throws Exception {
        // Initialize the database
        mQuestStageRepository.saveAndFlush(mQuestStage);

        // Get all the mQuestStageList where difficulty in DEFAULT_DIFFICULTY or UPDATED_DIFFICULTY
        defaultMQuestStageShouldBeFound("difficulty.in=" + DEFAULT_DIFFICULTY + "," + UPDATED_DIFFICULTY);

        // Get all the mQuestStageList where difficulty equals to UPDATED_DIFFICULTY
        defaultMQuestStageShouldNotBeFound("difficulty.in=" + UPDATED_DIFFICULTY);
    }

    @Test
    @Transactional
    public void getAllMQuestStagesByDifficultyIsNullOrNotNull() throws Exception {
        // Initialize the database
        mQuestStageRepository.saveAndFlush(mQuestStage);

        // Get all the mQuestStageList where difficulty is not null
        defaultMQuestStageShouldBeFound("difficulty.specified=true");

        // Get all the mQuestStageList where difficulty is null
        defaultMQuestStageShouldNotBeFound("difficulty.specified=false");
    }

    @Test
    @Transactional
    public void getAllMQuestStagesByDifficultyIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mQuestStageRepository.saveAndFlush(mQuestStage);

        // Get all the mQuestStageList where difficulty greater than or equals to DEFAULT_DIFFICULTY
        defaultMQuestStageShouldBeFound("difficulty.greaterOrEqualThan=" + DEFAULT_DIFFICULTY);

        // Get all the mQuestStageList where difficulty greater than or equals to UPDATED_DIFFICULTY
        defaultMQuestStageShouldNotBeFound("difficulty.greaterOrEqualThan=" + UPDATED_DIFFICULTY);
    }

    @Test
    @Transactional
    public void getAllMQuestStagesByDifficultyIsLessThanSomething() throws Exception {
        // Initialize the database
        mQuestStageRepository.saveAndFlush(mQuestStage);

        // Get all the mQuestStageList where difficulty less than or equals to DEFAULT_DIFFICULTY
        defaultMQuestStageShouldNotBeFound("difficulty.lessThan=" + DEFAULT_DIFFICULTY);

        // Get all the mQuestStageList where difficulty less than or equals to UPDATED_DIFFICULTY
        defaultMQuestStageShouldBeFound("difficulty.lessThan=" + UPDATED_DIFFICULTY);
    }


    @Test
    @Transactional
    public void getAllMQuestStagesByStageUnlockPatternIsEqualToSomething() throws Exception {
        // Initialize the database
        mQuestStageRepository.saveAndFlush(mQuestStage);

        // Get all the mQuestStageList where stageUnlockPattern equals to DEFAULT_STAGE_UNLOCK_PATTERN
        defaultMQuestStageShouldBeFound("stageUnlockPattern.equals=" + DEFAULT_STAGE_UNLOCK_PATTERN);

        // Get all the mQuestStageList where stageUnlockPattern equals to UPDATED_STAGE_UNLOCK_PATTERN
        defaultMQuestStageShouldNotBeFound("stageUnlockPattern.equals=" + UPDATED_STAGE_UNLOCK_PATTERN);
    }

    @Test
    @Transactional
    public void getAllMQuestStagesByStageUnlockPatternIsInShouldWork() throws Exception {
        // Initialize the database
        mQuestStageRepository.saveAndFlush(mQuestStage);

        // Get all the mQuestStageList where stageUnlockPattern in DEFAULT_STAGE_UNLOCK_PATTERN or UPDATED_STAGE_UNLOCK_PATTERN
        defaultMQuestStageShouldBeFound("stageUnlockPattern.in=" + DEFAULT_STAGE_UNLOCK_PATTERN + "," + UPDATED_STAGE_UNLOCK_PATTERN);

        // Get all the mQuestStageList where stageUnlockPattern equals to UPDATED_STAGE_UNLOCK_PATTERN
        defaultMQuestStageShouldNotBeFound("stageUnlockPattern.in=" + UPDATED_STAGE_UNLOCK_PATTERN);
    }

    @Test
    @Transactional
    public void getAllMQuestStagesByStageUnlockPatternIsNullOrNotNull() throws Exception {
        // Initialize the database
        mQuestStageRepository.saveAndFlush(mQuestStage);

        // Get all the mQuestStageList where stageUnlockPattern is not null
        defaultMQuestStageShouldBeFound("stageUnlockPattern.specified=true");

        // Get all the mQuestStageList where stageUnlockPattern is null
        defaultMQuestStageShouldNotBeFound("stageUnlockPattern.specified=false");
    }

    @Test
    @Transactional
    public void getAllMQuestStagesByStageUnlockPatternIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mQuestStageRepository.saveAndFlush(mQuestStage);

        // Get all the mQuestStageList where stageUnlockPattern greater than or equals to DEFAULT_STAGE_UNLOCK_PATTERN
        defaultMQuestStageShouldBeFound("stageUnlockPattern.greaterOrEqualThan=" + DEFAULT_STAGE_UNLOCK_PATTERN);

        // Get all the mQuestStageList where stageUnlockPattern greater than or equals to UPDATED_STAGE_UNLOCK_PATTERN
        defaultMQuestStageShouldNotBeFound("stageUnlockPattern.greaterOrEqualThan=" + UPDATED_STAGE_UNLOCK_PATTERN);
    }

    @Test
    @Transactional
    public void getAllMQuestStagesByStageUnlockPatternIsLessThanSomething() throws Exception {
        // Initialize the database
        mQuestStageRepository.saveAndFlush(mQuestStage);

        // Get all the mQuestStageList where stageUnlockPattern less than or equals to DEFAULT_STAGE_UNLOCK_PATTERN
        defaultMQuestStageShouldNotBeFound("stageUnlockPattern.lessThan=" + DEFAULT_STAGE_UNLOCK_PATTERN);

        // Get all the mQuestStageList where stageUnlockPattern less than or equals to UPDATED_STAGE_UNLOCK_PATTERN
        defaultMQuestStageShouldBeFound("stageUnlockPattern.lessThan=" + UPDATED_STAGE_UNLOCK_PATTERN);
    }


    @Test
    @Transactional
    public void getAllMQuestStagesByStoryOnlyIsEqualToSomething() throws Exception {
        // Initialize the database
        mQuestStageRepository.saveAndFlush(mQuestStage);

        // Get all the mQuestStageList where storyOnly equals to DEFAULT_STORY_ONLY
        defaultMQuestStageShouldBeFound("storyOnly.equals=" + DEFAULT_STORY_ONLY);

        // Get all the mQuestStageList where storyOnly equals to UPDATED_STORY_ONLY
        defaultMQuestStageShouldNotBeFound("storyOnly.equals=" + UPDATED_STORY_ONLY);
    }

    @Test
    @Transactional
    public void getAllMQuestStagesByStoryOnlyIsInShouldWork() throws Exception {
        // Initialize the database
        mQuestStageRepository.saveAndFlush(mQuestStage);

        // Get all the mQuestStageList where storyOnly in DEFAULT_STORY_ONLY or UPDATED_STORY_ONLY
        defaultMQuestStageShouldBeFound("storyOnly.in=" + DEFAULT_STORY_ONLY + "," + UPDATED_STORY_ONLY);

        // Get all the mQuestStageList where storyOnly equals to UPDATED_STORY_ONLY
        defaultMQuestStageShouldNotBeFound("storyOnly.in=" + UPDATED_STORY_ONLY);
    }

    @Test
    @Transactional
    public void getAllMQuestStagesByStoryOnlyIsNullOrNotNull() throws Exception {
        // Initialize the database
        mQuestStageRepository.saveAndFlush(mQuestStage);

        // Get all the mQuestStageList where storyOnly is not null
        defaultMQuestStageShouldBeFound("storyOnly.specified=true");

        // Get all the mQuestStageList where storyOnly is null
        defaultMQuestStageShouldNotBeFound("storyOnly.specified=false");
    }

    @Test
    @Transactional
    public void getAllMQuestStagesByStoryOnlyIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mQuestStageRepository.saveAndFlush(mQuestStage);

        // Get all the mQuestStageList where storyOnly greater than or equals to DEFAULT_STORY_ONLY
        defaultMQuestStageShouldBeFound("storyOnly.greaterOrEqualThan=" + DEFAULT_STORY_ONLY);

        // Get all the mQuestStageList where storyOnly greater than or equals to UPDATED_STORY_ONLY
        defaultMQuestStageShouldNotBeFound("storyOnly.greaterOrEqualThan=" + UPDATED_STORY_ONLY);
    }

    @Test
    @Transactional
    public void getAllMQuestStagesByStoryOnlyIsLessThanSomething() throws Exception {
        // Initialize the database
        mQuestStageRepository.saveAndFlush(mQuestStage);

        // Get all the mQuestStageList where storyOnly less than or equals to DEFAULT_STORY_ONLY
        defaultMQuestStageShouldNotBeFound("storyOnly.lessThan=" + DEFAULT_STORY_ONLY);

        // Get all the mQuestStageList where storyOnly less than or equals to UPDATED_STORY_ONLY
        defaultMQuestStageShouldBeFound("storyOnly.lessThan=" + UPDATED_STORY_ONLY);
    }


    @Test
    @Transactional
    public void getAllMQuestStagesByFirstHalfNpcDeckIdIsEqualToSomething() throws Exception {
        // Initialize the database
        mQuestStageRepository.saveAndFlush(mQuestStage);

        // Get all the mQuestStageList where firstHalfNpcDeckId equals to DEFAULT_FIRST_HALF_NPC_DECK_ID
        defaultMQuestStageShouldBeFound("firstHalfNpcDeckId.equals=" + DEFAULT_FIRST_HALF_NPC_DECK_ID);

        // Get all the mQuestStageList where firstHalfNpcDeckId equals to UPDATED_FIRST_HALF_NPC_DECK_ID
        defaultMQuestStageShouldNotBeFound("firstHalfNpcDeckId.equals=" + UPDATED_FIRST_HALF_NPC_DECK_ID);
    }

    @Test
    @Transactional
    public void getAllMQuestStagesByFirstHalfNpcDeckIdIsInShouldWork() throws Exception {
        // Initialize the database
        mQuestStageRepository.saveAndFlush(mQuestStage);

        // Get all the mQuestStageList where firstHalfNpcDeckId in DEFAULT_FIRST_HALF_NPC_DECK_ID or UPDATED_FIRST_HALF_NPC_DECK_ID
        defaultMQuestStageShouldBeFound("firstHalfNpcDeckId.in=" + DEFAULT_FIRST_HALF_NPC_DECK_ID + "," + UPDATED_FIRST_HALF_NPC_DECK_ID);

        // Get all the mQuestStageList where firstHalfNpcDeckId equals to UPDATED_FIRST_HALF_NPC_DECK_ID
        defaultMQuestStageShouldNotBeFound("firstHalfNpcDeckId.in=" + UPDATED_FIRST_HALF_NPC_DECK_ID);
    }

    @Test
    @Transactional
    public void getAllMQuestStagesByFirstHalfNpcDeckIdIsNullOrNotNull() throws Exception {
        // Initialize the database
        mQuestStageRepository.saveAndFlush(mQuestStage);

        // Get all the mQuestStageList where firstHalfNpcDeckId is not null
        defaultMQuestStageShouldBeFound("firstHalfNpcDeckId.specified=true");

        // Get all the mQuestStageList where firstHalfNpcDeckId is null
        defaultMQuestStageShouldNotBeFound("firstHalfNpcDeckId.specified=false");
    }

    @Test
    @Transactional
    public void getAllMQuestStagesByFirstHalfNpcDeckIdIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mQuestStageRepository.saveAndFlush(mQuestStage);

        // Get all the mQuestStageList where firstHalfNpcDeckId greater than or equals to DEFAULT_FIRST_HALF_NPC_DECK_ID
        defaultMQuestStageShouldBeFound("firstHalfNpcDeckId.greaterOrEqualThan=" + DEFAULT_FIRST_HALF_NPC_DECK_ID);

        // Get all the mQuestStageList where firstHalfNpcDeckId greater than or equals to UPDATED_FIRST_HALF_NPC_DECK_ID
        defaultMQuestStageShouldNotBeFound("firstHalfNpcDeckId.greaterOrEqualThan=" + UPDATED_FIRST_HALF_NPC_DECK_ID);
    }

    @Test
    @Transactional
    public void getAllMQuestStagesByFirstHalfNpcDeckIdIsLessThanSomething() throws Exception {
        // Initialize the database
        mQuestStageRepository.saveAndFlush(mQuestStage);

        // Get all the mQuestStageList where firstHalfNpcDeckId less than or equals to DEFAULT_FIRST_HALF_NPC_DECK_ID
        defaultMQuestStageShouldNotBeFound("firstHalfNpcDeckId.lessThan=" + DEFAULT_FIRST_HALF_NPC_DECK_ID);

        // Get all the mQuestStageList where firstHalfNpcDeckId less than or equals to UPDATED_FIRST_HALF_NPC_DECK_ID
        defaultMQuestStageShouldBeFound("firstHalfNpcDeckId.lessThan=" + UPDATED_FIRST_HALF_NPC_DECK_ID);
    }


    @Test
    @Transactional
    public void getAllMQuestStagesByFirstHalfEnvironmentIdIsEqualToSomething() throws Exception {
        // Initialize the database
        mQuestStageRepository.saveAndFlush(mQuestStage);

        // Get all the mQuestStageList where firstHalfEnvironmentId equals to DEFAULT_FIRST_HALF_ENVIRONMENT_ID
        defaultMQuestStageShouldBeFound("firstHalfEnvironmentId.equals=" + DEFAULT_FIRST_HALF_ENVIRONMENT_ID);

        // Get all the mQuestStageList where firstHalfEnvironmentId equals to UPDATED_FIRST_HALF_ENVIRONMENT_ID
        defaultMQuestStageShouldNotBeFound("firstHalfEnvironmentId.equals=" + UPDATED_FIRST_HALF_ENVIRONMENT_ID);
    }

    @Test
    @Transactional
    public void getAllMQuestStagesByFirstHalfEnvironmentIdIsInShouldWork() throws Exception {
        // Initialize the database
        mQuestStageRepository.saveAndFlush(mQuestStage);

        // Get all the mQuestStageList where firstHalfEnvironmentId in DEFAULT_FIRST_HALF_ENVIRONMENT_ID or UPDATED_FIRST_HALF_ENVIRONMENT_ID
        defaultMQuestStageShouldBeFound("firstHalfEnvironmentId.in=" + DEFAULT_FIRST_HALF_ENVIRONMENT_ID + "," + UPDATED_FIRST_HALF_ENVIRONMENT_ID);

        // Get all the mQuestStageList where firstHalfEnvironmentId equals to UPDATED_FIRST_HALF_ENVIRONMENT_ID
        defaultMQuestStageShouldNotBeFound("firstHalfEnvironmentId.in=" + UPDATED_FIRST_HALF_ENVIRONMENT_ID);
    }

    @Test
    @Transactional
    public void getAllMQuestStagesByFirstHalfEnvironmentIdIsNullOrNotNull() throws Exception {
        // Initialize the database
        mQuestStageRepository.saveAndFlush(mQuestStage);

        // Get all the mQuestStageList where firstHalfEnvironmentId is not null
        defaultMQuestStageShouldBeFound("firstHalfEnvironmentId.specified=true");

        // Get all the mQuestStageList where firstHalfEnvironmentId is null
        defaultMQuestStageShouldNotBeFound("firstHalfEnvironmentId.specified=false");
    }

    @Test
    @Transactional
    public void getAllMQuestStagesByFirstHalfEnvironmentIdIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mQuestStageRepository.saveAndFlush(mQuestStage);

        // Get all the mQuestStageList where firstHalfEnvironmentId greater than or equals to DEFAULT_FIRST_HALF_ENVIRONMENT_ID
        defaultMQuestStageShouldBeFound("firstHalfEnvironmentId.greaterOrEqualThan=" + DEFAULT_FIRST_HALF_ENVIRONMENT_ID);

        // Get all the mQuestStageList where firstHalfEnvironmentId greater than or equals to UPDATED_FIRST_HALF_ENVIRONMENT_ID
        defaultMQuestStageShouldNotBeFound("firstHalfEnvironmentId.greaterOrEqualThan=" + UPDATED_FIRST_HALF_ENVIRONMENT_ID);
    }

    @Test
    @Transactional
    public void getAllMQuestStagesByFirstHalfEnvironmentIdIsLessThanSomething() throws Exception {
        // Initialize the database
        mQuestStageRepository.saveAndFlush(mQuestStage);

        // Get all the mQuestStageList where firstHalfEnvironmentId less than or equals to DEFAULT_FIRST_HALF_ENVIRONMENT_ID
        defaultMQuestStageShouldNotBeFound("firstHalfEnvironmentId.lessThan=" + DEFAULT_FIRST_HALF_ENVIRONMENT_ID);

        // Get all the mQuestStageList where firstHalfEnvironmentId less than or equals to UPDATED_FIRST_HALF_ENVIRONMENT_ID
        defaultMQuestStageShouldBeFound("firstHalfEnvironmentId.lessThan=" + UPDATED_FIRST_HALF_ENVIRONMENT_ID);
    }


    @Test
    @Transactional
    public void getAllMQuestStagesBySecondHalfNpcDeckIdIsEqualToSomething() throws Exception {
        // Initialize the database
        mQuestStageRepository.saveAndFlush(mQuestStage);

        // Get all the mQuestStageList where secondHalfNpcDeckId equals to DEFAULT_SECOND_HALF_NPC_DECK_ID
        defaultMQuestStageShouldBeFound("secondHalfNpcDeckId.equals=" + DEFAULT_SECOND_HALF_NPC_DECK_ID);

        // Get all the mQuestStageList where secondHalfNpcDeckId equals to UPDATED_SECOND_HALF_NPC_DECK_ID
        defaultMQuestStageShouldNotBeFound("secondHalfNpcDeckId.equals=" + UPDATED_SECOND_HALF_NPC_DECK_ID);
    }

    @Test
    @Transactional
    public void getAllMQuestStagesBySecondHalfNpcDeckIdIsInShouldWork() throws Exception {
        // Initialize the database
        mQuestStageRepository.saveAndFlush(mQuestStage);

        // Get all the mQuestStageList where secondHalfNpcDeckId in DEFAULT_SECOND_HALF_NPC_DECK_ID or UPDATED_SECOND_HALF_NPC_DECK_ID
        defaultMQuestStageShouldBeFound("secondHalfNpcDeckId.in=" + DEFAULT_SECOND_HALF_NPC_DECK_ID + "," + UPDATED_SECOND_HALF_NPC_DECK_ID);

        // Get all the mQuestStageList where secondHalfNpcDeckId equals to UPDATED_SECOND_HALF_NPC_DECK_ID
        defaultMQuestStageShouldNotBeFound("secondHalfNpcDeckId.in=" + UPDATED_SECOND_HALF_NPC_DECK_ID);
    }

    @Test
    @Transactional
    public void getAllMQuestStagesBySecondHalfNpcDeckIdIsNullOrNotNull() throws Exception {
        // Initialize the database
        mQuestStageRepository.saveAndFlush(mQuestStage);

        // Get all the mQuestStageList where secondHalfNpcDeckId is not null
        defaultMQuestStageShouldBeFound("secondHalfNpcDeckId.specified=true");

        // Get all the mQuestStageList where secondHalfNpcDeckId is null
        defaultMQuestStageShouldNotBeFound("secondHalfNpcDeckId.specified=false");
    }

    @Test
    @Transactional
    public void getAllMQuestStagesBySecondHalfNpcDeckIdIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mQuestStageRepository.saveAndFlush(mQuestStage);

        // Get all the mQuestStageList where secondHalfNpcDeckId greater than or equals to DEFAULT_SECOND_HALF_NPC_DECK_ID
        defaultMQuestStageShouldBeFound("secondHalfNpcDeckId.greaterOrEqualThan=" + DEFAULT_SECOND_HALF_NPC_DECK_ID);

        // Get all the mQuestStageList where secondHalfNpcDeckId greater than or equals to UPDATED_SECOND_HALF_NPC_DECK_ID
        defaultMQuestStageShouldNotBeFound("secondHalfNpcDeckId.greaterOrEqualThan=" + UPDATED_SECOND_HALF_NPC_DECK_ID);
    }

    @Test
    @Transactional
    public void getAllMQuestStagesBySecondHalfNpcDeckIdIsLessThanSomething() throws Exception {
        // Initialize the database
        mQuestStageRepository.saveAndFlush(mQuestStage);

        // Get all the mQuestStageList where secondHalfNpcDeckId less than or equals to DEFAULT_SECOND_HALF_NPC_DECK_ID
        defaultMQuestStageShouldNotBeFound("secondHalfNpcDeckId.lessThan=" + DEFAULT_SECOND_HALF_NPC_DECK_ID);

        // Get all the mQuestStageList where secondHalfNpcDeckId less than or equals to UPDATED_SECOND_HALF_NPC_DECK_ID
        defaultMQuestStageShouldBeFound("secondHalfNpcDeckId.lessThan=" + UPDATED_SECOND_HALF_NPC_DECK_ID);
    }


    @Test
    @Transactional
    public void getAllMQuestStagesBySecondHalfEnvironmentIdIsEqualToSomething() throws Exception {
        // Initialize the database
        mQuestStageRepository.saveAndFlush(mQuestStage);

        // Get all the mQuestStageList where secondHalfEnvironmentId equals to DEFAULT_SECOND_HALF_ENVIRONMENT_ID
        defaultMQuestStageShouldBeFound("secondHalfEnvironmentId.equals=" + DEFAULT_SECOND_HALF_ENVIRONMENT_ID);

        // Get all the mQuestStageList where secondHalfEnvironmentId equals to UPDATED_SECOND_HALF_ENVIRONMENT_ID
        defaultMQuestStageShouldNotBeFound("secondHalfEnvironmentId.equals=" + UPDATED_SECOND_HALF_ENVIRONMENT_ID);
    }

    @Test
    @Transactional
    public void getAllMQuestStagesBySecondHalfEnvironmentIdIsInShouldWork() throws Exception {
        // Initialize the database
        mQuestStageRepository.saveAndFlush(mQuestStage);

        // Get all the mQuestStageList where secondHalfEnvironmentId in DEFAULT_SECOND_HALF_ENVIRONMENT_ID or UPDATED_SECOND_HALF_ENVIRONMENT_ID
        defaultMQuestStageShouldBeFound("secondHalfEnvironmentId.in=" + DEFAULT_SECOND_HALF_ENVIRONMENT_ID + "," + UPDATED_SECOND_HALF_ENVIRONMENT_ID);

        // Get all the mQuestStageList where secondHalfEnvironmentId equals to UPDATED_SECOND_HALF_ENVIRONMENT_ID
        defaultMQuestStageShouldNotBeFound("secondHalfEnvironmentId.in=" + UPDATED_SECOND_HALF_ENVIRONMENT_ID);
    }

    @Test
    @Transactional
    public void getAllMQuestStagesBySecondHalfEnvironmentIdIsNullOrNotNull() throws Exception {
        // Initialize the database
        mQuestStageRepository.saveAndFlush(mQuestStage);

        // Get all the mQuestStageList where secondHalfEnvironmentId is not null
        defaultMQuestStageShouldBeFound("secondHalfEnvironmentId.specified=true");

        // Get all the mQuestStageList where secondHalfEnvironmentId is null
        defaultMQuestStageShouldNotBeFound("secondHalfEnvironmentId.specified=false");
    }

    @Test
    @Transactional
    public void getAllMQuestStagesBySecondHalfEnvironmentIdIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mQuestStageRepository.saveAndFlush(mQuestStage);

        // Get all the mQuestStageList where secondHalfEnvironmentId greater than or equals to DEFAULT_SECOND_HALF_ENVIRONMENT_ID
        defaultMQuestStageShouldBeFound("secondHalfEnvironmentId.greaterOrEqualThan=" + DEFAULT_SECOND_HALF_ENVIRONMENT_ID);

        // Get all the mQuestStageList where secondHalfEnvironmentId greater than or equals to UPDATED_SECOND_HALF_ENVIRONMENT_ID
        defaultMQuestStageShouldNotBeFound("secondHalfEnvironmentId.greaterOrEqualThan=" + UPDATED_SECOND_HALF_ENVIRONMENT_ID);
    }

    @Test
    @Transactional
    public void getAllMQuestStagesBySecondHalfEnvironmentIdIsLessThanSomething() throws Exception {
        // Initialize the database
        mQuestStageRepository.saveAndFlush(mQuestStage);

        // Get all the mQuestStageList where secondHalfEnvironmentId less than or equals to DEFAULT_SECOND_HALF_ENVIRONMENT_ID
        defaultMQuestStageShouldNotBeFound("secondHalfEnvironmentId.lessThan=" + DEFAULT_SECOND_HALF_ENVIRONMENT_ID);

        // Get all the mQuestStageList where secondHalfEnvironmentId less than or equals to UPDATED_SECOND_HALF_ENVIRONMENT_ID
        defaultMQuestStageShouldBeFound("secondHalfEnvironmentId.lessThan=" + UPDATED_SECOND_HALF_ENVIRONMENT_ID);
    }


    @Test
    @Transactional
    public void getAllMQuestStagesByExtraFirstHalfNpcDeckIdIsEqualToSomething() throws Exception {
        // Initialize the database
        mQuestStageRepository.saveAndFlush(mQuestStage);

        // Get all the mQuestStageList where extraFirstHalfNpcDeckId equals to DEFAULT_EXTRA_FIRST_HALF_NPC_DECK_ID
        defaultMQuestStageShouldBeFound("extraFirstHalfNpcDeckId.equals=" + DEFAULT_EXTRA_FIRST_HALF_NPC_DECK_ID);

        // Get all the mQuestStageList where extraFirstHalfNpcDeckId equals to UPDATED_EXTRA_FIRST_HALF_NPC_DECK_ID
        defaultMQuestStageShouldNotBeFound("extraFirstHalfNpcDeckId.equals=" + UPDATED_EXTRA_FIRST_HALF_NPC_DECK_ID);
    }

    @Test
    @Transactional
    public void getAllMQuestStagesByExtraFirstHalfNpcDeckIdIsInShouldWork() throws Exception {
        // Initialize the database
        mQuestStageRepository.saveAndFlush(mQuestStage);

        // Get all the mQuestStageList where extraFirstHalfNpcDeckId in DEFAULT_EXTRA_FIRST_HALF_NPC_DECK_ID or UPDATED_EXTRA_FIRST_HALF_NPC_DECK_ID
        defaultMQuestStageShouldBeFound("extraFirstHalfNpcDeckId.in=" + DEFAULT_EXTRA_FIRST_HALF_NPC_DECK_ID + "," + UPDATED_EXTRA_FIRST_HALF_NPC_DECK_ID);

        // Get all the mQuestStageList where extraFirstHalfNpcDeckId equals to UPDATED_EXTRA_FIRST_HALF_NPC_DECK_ID
        defaultMQuestStageShouldNotBeFound("extraFirstHalfNpcDeckId.in=" + UPDATED_EXTRA_FIRST_HALF_NPC_DECK_ID);
    }

    @Test
    @Transactional
    public void getAllMQuestStagesByExtraFirstHalfNpcDeckIdIsNullOrNotNull() throws Exception {
        // Initialize the database
        mQuestStageRepository.saveAndFlush(mQuestStage);

        // Get all the mQuestStageList where extraFirstHalfNpcDeckId is not null
        defaultMQuestStageShouldBeFound("extraFirstHalfNpcDeckId.specified=true");

        // Get all the mQuestStageList where extraFirstHalfNpcDeckId is null
        defaultMQuestStageShouldNotBeFound("extraFirstHalfNpcDeckId.specified=false");
    }

    @Test
    @Transactional
    public void getAllMQuestStagesByExtraFirstHalfNpcDeckIdIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mQuestStageRepository.saveAndFlush(mQuestStage);

        // Get all the mQuestStageList where extraFirstHalfNpcDeckId greater than or equals to DEFAULT_EXTRA_FIRST_HALF_NPC_DECK_ID
        defaultMQuestStageShouldBeFound("extraFirstHalfNpcDeckId.greaterOrEqualThan=" + DEFAULT_EXTRA_FIRST_HALF_NPC_DECK_ID);

        // Get all the mQuestStageList where extraFirstHalfNpcDeckId greater than or equals to UPDATED_EXTRA_FIRST_HALF_NPC_DECK_ID
        defaultMQuestStageShouldNotBeFound("extraFirstHalfNpcDeckId.greaterOrEqualThan=" + UPDATED_EXTRA_FIRST_HALF_NPC_DECK_ID);
    }

    @Test
    @Transactional
    public void getAllMQuestStagesByExtraFirstHalfNpcDeckIdIsLessThanSomething() throws Exception {
        // Initialize the database
        mQuestStageRepository.saveAndFlush(mQuestStage);

        // Get all the mQuestStageList where extraFirstHalfNpcDeckId less than or equals to DEFAULT_EXTRA_FIRST_HALF_NPC_DECK_ID
        defaultMQuestStageShouldNotBeFound("extraFirstHalfNpcDeckId.lessThan=" + DEFAULT_EXTRA_FIRST_HALF_NPC_DECK_ID);

        // Get all the mQuestStageList where extraFirstHalfNpcDeckId less than or equals to UPDATED_EXTRA_FIRST_HALF_NPC_DECK_ID
        defaultMQuestStageShouldBeFound("extraFirstHalfNpcDeckId.lessThan=" + UPDATED_EXTRA_FIRST_HALF_NPC_DECK_ID);
    }


    @Test
    @Transactional
    public void getAllMQuestStagesByConsumeApIsEqualToSomething() throws Exception {
        // Initialize the database
        mQuestStageRepository.saveAndFlush(mQuestStage);

        // Get all the mQuestStageList where consumeAp equals to DEFAULT_CONSUME_AP
        defaultMQuestStageShouldBeFound("consumeAp.equals=" + DEFAULT_CONSUME_AP);

        // Get all the mQuestStageList where consumeAp equals to UPDATED_CONSUME_AP
        defaultMQuestStageShouldNotBeFound("consumeAp.equals=" + UPDATED_CONSUME_AP);
    }

    @Test
    @Transactional
    public void getAllMQuestStagesByConsumeApIsInShouldWork() throws Exception {
        // Initialize the database
        mQuestStageRepository.saveAndFlush(mQuestStage);

        // Get all the mQuestStageList where consumeAp in DEFAULT_CONSUME_AP or UPDATED_CONSUME_AP
        defaultMQuestStageShouldBeFound("consumeAp.in=" + DEFAULT_CONSUME_AP + "," + UPDATED_CONSUME_AP);

        // Get all the mQuestStageList where consumeAp equals to UPDATED_CONSUME_AP
        defaultMQuestStageShouldNotBeFound("consumeAp.in=" + UPDATED_CONSUME_AP);
    }

    @Test
    @Transactional
    public void getAllMQuestStagesByConsumeApIsNullOrNotNull() throws Exception {
        // Initialize the database
        mQuestStageRepository.saveAndFlush(mQuestStage);

        // Get all the mQuestStageList where consumeAp is not null
        defaultMQuestStageShouldBeFound("consumeAp.specified=true");

        // Get all the mQuestStageList where consumeAp is null
        defaultMQuestStageShouldNotBeFound("consumeAp.specified=false");
    }

    @Test
    @Transactional
    public void getAllMQuestStagesByConsumeApIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mQuestStageRepository.saveAndFlush(mQuestStage);

        // Get all the mQuestStageList where consumeAp greater than or equals to DEFAULT_CONSUME_AP
        defaultMQuestStageShouldBeFound("consumeAp.greaterOrEqualThan=" + DEFAULT_CONSUME_AP);

        // Get all the mQuestStageList where consumeAp greater than or equals to UPDATED_CONSUME_AP
        defaultMQuestStageShouldNotBeFound("consumeAp.greaterOrEqualThan=" + UPDATED_CONSUME_AP);
    }

    @Test
    @Transactional
    public void getAllMQuestStagesByConsumeApIsLessThanSomething() throws Exception {
        // Initialize the database
        mQuestStageRepository.saveAndFlush(mQuestStage);

        // Get all the mQuestStageList where consumeAp less than or equals to DEFAULT_CONSUME_AP
        defaultMQuestStageShouldNotBeFound("consumeAp.lessThan=" + DEFAULT_CONSUME_AP);

        // Get all the mQuestStageList where consumeAp less than or equals to UPDATED_CONSUME_AP
        defaultMQuestStageShouldBeFound("consumeAp.lessThan=" + UPDATED_CONSUME_AP);
    }


    @Test
    @Transactional
    public void getAllMQuestStagesByKickOffTypeIsEqualToSomething() throws Exception {
        // Initialize the database
        mQuestStageRepository.saveAndFlush(mQuestStage);

        // Get all the mQuestStageList where kickOffType equals to DEFAULT_KICK_OFF_TYPE
        defaultMQuestStageShouldBeFound("kickOffType.equals=" + DEFAULT_KICK_OFF_TYPE);

        // Get all the mQuestStageList where kickOffType equals to UPDATED_KICK_OFF_TYPE
        defaultMQuestStageShouldNotBeFound("kickOffType.equals=" + UPDATED_KICK_OFF_TYPE);
    }

    @Test
    @Transactional
    public void getAllMQuestStagesByKickOffTypeIsInShouldWork() throws Exception {
        // Initialize the database
        mQuestStageRepository.saveAndFlush(mQuestStage);

        // Get all the mQuestStageList where kickOffType in DEFAULT_KICK_OFF_TYPE or UPDATED_KICK_OFF_TYPE
        defaultMQuestStageShouldBeFound("kickOffType.in=" + DEFAULT_KICK_OFF_TYPE + "," + UPDATED_KICK_OFF_TYPE);

        // Get all the mQuestStageList where kickOffType equals to UPDATED_KICK_OFF_TYPE
        defaultMQuestStageShouldNotBeFound("kickOffType.in=" + UPDATED_KICK_OFF_TYPE);
    }

    @Test
    @Transactional
    public void getAllMQuestStagesByKickOffTypeIsNullOrNotNull() throws Exception {
        // Initialize the database
        mQuestStageRepository.saveAndFlush(mQuestStage);

        // Get all the mQuestStageList where kickOffType is not null
        defaultMQuestStageShouldBeFound("kickOffType.specified=true");

        // Get all the mQuestStageList where kickOffType is null
        defaultMQuestStageShouldNotBeFound("kickOffType.specified=false");
    }

    @Test
    @Transactional
    public void getAllMQuestStagesByKickOffTypeIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mQuestStageRepository.saveAndFlush(mQuestStage);

        // Get all the mQuestStageList where kickOffType greater than or equals to DEFAULT_KICK_OFF_TYPE
        defaultMQuestStageShouldBeFound("kickOffType.greaterOrEqualThan=" + DEFAULT_KICK_OFF_TYPE);

        // Get all the mQuestStageList where kickOffType greater than or equals to UPDATED_KICK_OFF_TYPE
        defaultMQuestStageShouldNotBeFound("kickOffType.greaterOrEqualThan=" + UPDATED_KICK_OFF_TYPE);
    }

    @Test
    @Transactional
    public void getAllMQuestStagesByKickOffTypeIsLessThanSomething() throws Exception {
        // Initialize the database
        mQuestStageRepository.saveAndFlush(mQuestStage);

        // Get all the mQuestStageList where kickOffType less than or equals to DEFAULT_KICK_OFF_TYPE
        defaultMQuestStageShouldNotBeFound("kickOffType.lessThan=" + DEFAULT_KICK_OFF_TYPE);

        // Get all the mQuestStageList where kickOffType less than or equals to UPDATED_KICK_OFF_TYPE
        defaultMQuestStageShouldBeFound("kickOffType.lessThan=" + UPDATED_KICK_OFF_TYPE);
    }


    @Test
    @Transactional
    public void getAllMQuestStagesByMatchMinuteIsEqualToSomething() throws Exception {
        // Initialize the database
        mQuestStageRepository.saveAndFlush(mQuestStage);

        // Get all the mQuestStageList where matchMinute equals to DEFAULT_MATCH_MINUTE
        defaultMQuestStageShouldBeFound("matchMinute.equals=" + DEFAULT_MATCH_MINUTE);

        // Get all the mQuestStageList where matchMinute equals to UPDATED_MATCH_MINUTE
        defaultMQuestStageShouldNotBeFound("matchMinute.equals=" + UPDATED_MATCH_MINUTE);
    }

    @Test
    @Transactional
    public void getAllMQuestStagesByMatchMinuteIsInShouldWork() throws Exception {
        // Initialize the database
        mQuestStageRepository.saveAndFlush(mQuestStage);

        // Get all the mQuestStageList where matchMinute in DEFAULT_MATCH_MINUTE or UPDATED_MATCH_MINUTE
        defaultMQuestStageShouldBeFound("matchMinute.in=" + DEFAULT_MATCH_MINUTE + "," + UPDATED_MATCH_MINUTE);

        // Get all the mQuestStageList where matchMinute equals to UPDATED_MATCH_MINUTE
        defaultMQuestStageShouldNotBeFound("matchMinute.in=" + UPDATED_MATCH_MINUTE);
    }

    @Test
    @Transactional
    public void getAllMQuestStagesByMatchMinuteIsNullOrNotNull() throws Exception {
        // Initialize the database
        mQuestStageRepository.saveAndFlush(mQuestStage);

        // Get all the mQuestStageList where matchMinute is not null
        defaultMQuestStageShouldBeFound("matchMinute.specified=true");

        // Get all the mQuestStageList where matchMinute is null
        defaultMQuestStageShouldNotBeFound("matchMinute.specified=false");
    }

    @Test
    @Transactional
    public void getAllMQuestStagesByMatchMinuteIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mQuestStageRepository.saveAndFlush(mQuestStage);

        // Get all the mQuestStageList where matchMinute greater than or equals to DEFAULT_MATCH_MINUTE
        defaultMQuestStageShouldBeFound("matchMinute.greaterOrEqualThan=" + DEFAULT_MATCH_MINUTE);

        // Get all the mQuestStageList where matchMinute greater than or equals to UPDATED_MATCH_MINUTE
        defaultMQuestStageShouldNotBeFound("matchMinute.greaterOrEqualThan=" + UPDATED_MATCH_MINUTE);
    }

    @Test
    @Transactional
    public void getAllMQuestStagesByMatchMinuteIsLessThanSomething() throws Exception {
        // Initialize the database
        mQuestStageRepository.saveAndFlush(mQuestStage);

        // Get all the mQuestStageList where matchMinute less than or equals to DEFAULT_MATCH_MINUTE
        defaultMQuestStageShouldNotBeFound("matchMinute.lessThan=" + DEFAULT_MATCH_MINUTE);

        // Get all the mQuestStageList where matchMinute less than or equals to UPDATED_MATCH_MINUTE
        defaultMQuestStageShouldBeFound("matchMinute.lessThan=" + UPDATED_MATCH_MINUTE);
    }


    @Test
    @Transactional
    public void getAllMQuestStagesByEnableExtraIsEqualToSomething() throws Exception {
        // Initialize the database
        mQuestStageRepository.saveAndFlush(mQuestStage);

        // Get all the mQuestStageList where enableExtra equals to DEFAULT_ENABLE_EXTRA
        defaultMQuestStageShouldBeFound("enableExtra.equals=" + DEFAULT_ENABLE_EXTRA);

        // Get all the mQuestStageList where enableExtra equals to UPDATED_ENABLE_EXTRA
        defaultMQuestStageShouldNotBeFound("enableExtra.equals=" + UPDATED_ENABLE_EXTRA);
    }

    @Test
    @Transactional
    public void getAllMQuestStagesByEnableExtraIsInShouldWork() throws Exception {
        // Initialize the database
        mQuestStageRepository.saveAndFlush(mQuestStage);

        // Get all the mQuestStageList where enableExtra in DEFAULT_ENABLE_EXTRA or UPDATED_ENABLE_EXTRA
        defaultMQuestStageShouldBeFound("enableExtra.in=" + DEFAULT_ENABLE_EXTRA + "," + UPDATED_ENABLE_EXTRA);

        // Get all the mQuestStageList where enableExtra equals to UPDATED_ENABLE_EXTRA
        defaultMQuestStageShouldNotBeFound("enableExtra.in=" + UPDATED_ENABLE_EXTRA);
    }

    @Test
    @Transactional
    public void getAllMQuestStagesByEnableExtraIsNullOrNotNull() throws Exception {
        // Initialize the database
        mQuestStageRepository.saveAndFlush(mQuestStage);

        // Get all the mQuestStageList where enableExtra is not null
        defaultMQuestStageShouldBeFound("enableExtra.specified=true");

        // Get all the mQuestStageList where enableExtra is null
        defaultMQuestStageShouldNotBeFound("enableExtra.specified=false");
    }

    @Test
    @Transactional
    public void getAllMQuestStagesByEnableExtraIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mQuestStageRepository.saveAndFlush(mQuestStage);

        // Get all the mQuestStageList where enableExtra greater than or equals to DEFAULT_ENABLE_EXTRA
        defaultMQuestStageShouldBeFound("enableExtra.greaterOrEqualThan=" + DEFAULT_ENABLE_EXTRA);

        // Get all the mQuestStageList where enableExtra greater than or equals to UPDATED_ENABLE_EXTRA
        defaultMQuestStageShouldNotBeFound("enableExtra.greaterOrEqualThan=" + UPDATED_ENABLE_EXTRA);
    }

    @Test
    @Transactional
    public void getAllMQuestStagesByEnableExtraIsLessThanSomething() throws Exception {
        // Initialize the database
        mQuestStageRepository.saveAndFlush(mQuestStage);

        // Get all the mQuestStageList where enableExtra less than or equals to DEFAULT_ENABLE_EXTRA
        defaultMQuestStageShouldNotBeFound("enableExtra.lessThan=" + DEFAULT_ENABLE_EXTRA);

        // Get all the mQuestStageList where enableExtra less than or equals to UPDATED_ENABLE_EXTRA
        defaultMQuestStageShouldBeFound("enableExtra.lessThan=" + UPDATED_ENABLE_EXTRA);
    }


    @Test
    @Transactional
    public void getAllMQuestStagesByEnablePkIsEqualToSomething() throws Exception {
        // Initialize the database
        mQuestStageRepository.saveAndFlush(mQuestStage);

        // Get all the mQuestStageList where enablePk equals to DEFAULT_ENABLE_PK
        defaultMQuestStageShouldBeFound("enablePk.equals=" + DEFAULT_ENABLE_PK);

        // Get all the mQuestStageList where enablePk equals to UPDATED_ENABLE_PK
        defaultMQuestStageShouldNotBeFound("enablePk.equals=" + UPDATED_ENABLE_PK);
    }

    @Test
    @Transactional
    public void getAllMQuestStagesByEnablePkIsInShouldWork() throws Exception {
        // Initialize the database
        mQuestStageRepository.saveAndFlush(mQuestStage);

        // Get all the mQuestStageList where enablePk in DEFAULT_ENABLE_PK or UPDATED_ENABLE_PK
        defaultMQuestStageShouldBeFound("enablePk.in=" + DEFAULT_ENABLE_PK + "," + UPDATED_ENABLE_PK);

        // Get all the mQuestStageList where enablePk equals to UPDATED_ENABLE_PK
        defaultMQuestStageShouldNotBeFound("enablePk.in=" + UPDATED_ENABLE_PK);
    }

    @Test
    @Transactional
    public void getAllMQuestStagesByEnablePkIsNullOrNotNull() throws Exception {
        // Initialize the database
        mQuestStageRepository.saveAndFlush(mQuestStage);

        // Get all the mQuestStageList where enablePk is not null
        defaultMQuestStageShouldBeFound("enablePk.specified=true");

        // Get all the mQuestStageList where enablePk is null
        defaultMQuestStageShouldNotBeFound("enablePk.specified=false");
    }

    @Test
    @Transactional
    public void getAllMQuestStagesByEnablePkIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mQuestStageRepository.saveAndFlush(mQuestStage);

        // Get all the mQuestStageList where enablePk greater than or equals to DEFAULT_ENABLE_PK
        defaultMQuestStageShouldBeFound("enablePk.greaterOrEqualThan=" + DEFAULT_ENABLE_PK);

        // Get all the mQuestStageList where enablePk greater than or equals to UPDATED_ENABLE_PK
        defaultMQuestStageShouldNotBeFound("enablePk.greaterOrEqualThan=" + UPDATED_ENABLE_PK);
    }

    @Test
    @Transactional
    public void getAllMQuestStagesByEnablePkIsLessThanSomething() throws Exception {
        // Initialize the database
        mQuestStageRepository.saveAndFlush(mQuestStage);

        // Get all the mQuestStageList where enablePk less than or equals to DEFAULT_ENABLE_PK
        defaultMQuestStageShouldNotBeFound("enablePk.lessThan=" + DEFAULT_ENABLE_PK);

        // Get all the mQuestStageList where enablePk less than or equals to UPDATED_ENABLE_PK
        defaultMQuestStageShouldBeFound("enablePk.lessThan=" + UPDATED_ENABLE_PK);
    }


    @Test
    @Transactional
    public void getAllMQuestStagesByAiLevelIsEqualToSomething() throws Exception {
        // Initialize the database
        mQuestStageRepository.saveAndFlush(mQuestStage);

        // Get all the mQuestStageList where aiLevel equals to DEFAULT_AI_LEVEL
        defaultMQuestStageShouldBeFound("aiLevel.equals=" + DEFAULT_AI_LEVEL);

        // Get all the mQuestStageList where aiLevel equals to UPDATED_AI_LEVEL
        defaultMQuestStageShouldNotBeFound("aiLevel.equals=" + UPDATED_AI_LEVEL);
    }

    @Test
    @Transactional
    public void getAllMQuestStagesByAiLevelIsInShouldWork() throws Exception {
        // Initialize the database
        mQuestStageRepository.saveAndFlush(mQuestStage);

        // Get all the mQuestStageList where aiLevel in DEFAULT_AI_LEVEL or UPDATED_AI_LEVEL
        defaultMQuestStageShouldBeFound("aiLevel.in=" + DEFAULT_AI_LEVEL + "," + UPDATED_AI_LEVEL);

        // Get all the mQuestStageList where aiLevel equals to UPDATED_AI_LEVEL
        defaultMQuestStageShouldNotBeFound("aiLevel.in=" + UPDATED_AI_LEVEL);
    }

    @Test
    @Transactional
    public void getAllMQuestStagesByAiLevelIsNullOrNotNull() throws Exception {
        // Initialize the database
        mQuestStageRepository.saveAndFlush(mQuestStage);

        // Get all the mQuestStageList where aiLevel is not null
        defaultMQuestStageShouldBeFound("aiLevel.specified=true");

        // Get all the mQuestStageList where aiLevel is null
        defaultMQuestStageShouldNotBeFound("aiLevel.specified=false");
    }

    @Test
    @Transactional
    public void getAllMQuestStagesByAiLevelIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mQuestStageRepository.saveAndFlush(mQuestStage);

        // Get all the mQuestStageList where aiLevel greater than or equals to DEFAULT_AI_LEVEL
        defaultMQuestStageShouldBeFound("aiLevel.greaterOrEqualThan=" + DEFAULT_AI_LEVEL);

        // Get all the mQuestStageList where aiLevel greater than or equals to UPDATED_AI_LEVEL
        defaultMQuestStageShouldNotBeFound("aiLevel.greaterOrEqualThan=" + UPDATED_AI_LEVEL);
    }

    @Test
    @Transactional
    public void getAllMQuestStagesByAiLevelIsLessThanSomething() throws Exception {
        // Initialize the database
        mQuestStageRepository.saveAndFlush(mQuestStage);

        // Get all the mQuestStageList where aiLevel less than or equals to DEFAULT_AI_LEVEL
        defaultMQuestStageShouldNotBeFound("aiLevel.lessThan=" + DEFAULT_AI_LEVEL);

        // Get all the mQuestStageList where aiLevel less than or equals to UPDATED_AI_LEVEL
        defaultMQuestStageShouldBeFound("aiLevel.lessThan=" + UPDATED_AI_LEVEL);
    }


    @Test
    @Transactional
    public void getAllMQuestStagesByStartAtSecondHalfIsEqualToSomething() throws Exception {
        // Initialize the database
        mQuestStageRepository.saveAndFlush(mQuestStage);

        // Get all the mQuestStageList where startAtSecondHalf equals to DEFAULT_START_AT_SECOND_HALF
        defaultMQuestStageShouldBeFound("startAtSecondHalf.equals=" + DEFAULT_START_AT_SECOND_HALF);

        // Get all the mQuestStageList where startAtSecondHalf equals to UPDATED_START_AT_SECOND_HALF
        defaultMQuestStageShouldNotBeFound("startAtSecondHalf.equals=" + UPDATED_START_AT_SECOND_HALF);
    }

    @Test
    @Transactional
    public void getAllMQuestStagesByStartAtSecondHalfIsInShouldWork() throws Exception {
        // Initialize the database
        mQuestStageRepository.saveAndFlush(mQuestStage);

        // Get all the mQuestStageList where startAtSecondHalf in DEFAULT_START_AT_SECOND_HALF or UPDATED_START_AT_SECOND_HALF
        defaultMQuestStageShouldBeFound("startAtSecondHalf.in=" + DEFAULT_START_AT_SECOND_HALF + "," + UPDATED_START_AT_SECOND_HALF);

        // Get all the mQuestStageList where startAtSecondHalf equals to UPDATED_START_AT_SECOND_HALF
        defaultMQuestStageShouldNotBeFound("startAtSecondHalf.in=" + UPDATED_START_AT_SECOND_HALF);
    }

    @Test
    @Transactional
    public void getAllMQuestStagesByStartAtSecondHalfIsNullOrNotNull() throws Exception {
        // Initialize the database
        mQuestStageRepository.saveAndFlush(mQuestStage);

        // Get all the mQuestStageList where startAtSecondHalf is not null
        defaultMQuestStageShouldBeFound("startAtSecondHalf.specified=true");

        // Get all the mQuestStageList where startAtSecondHalf is null
        defaultMQuestStageShouldNotBeFound("startAtSecondHalf.specified=false");
    }

    @Test
    @Transactional
    public void getAllMQuestStagesByStartAtSecondHalfIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mQuestStageRepository.saveAndFlush(mQuestStage);

        // Get all the mQuestStageList where startAtSecondHalf greater than or equals to DEFAULT_START_AT_SECOND_HALF
        defaultMQuestStageShouldBeFound("startAtSecondHalf.greaterOrEqualThan=" + DEFAULT_START_AT_SECOND_HALF);

        // Get all the mQuestStageList where startAtSecondHalf greater than or equals to UPDATED_START_AT_SECOND_HALF
        defaultMQuestStageShouldNotBeFound("startAtSecondHalf.greaterOrEqualThan=" + UPDATED_START_AT_SECOND_HALF);
    }

    @Test
    @Transactional
    public void getAllMQuestStagesByStartAtSecondHalfIsLessThanSomething() throws Exception {
        // Initialize the database
        mQuestStageRepository.saveAndFlush(mQuestStage);

        // Get all the mQuestStageList where startAtSecondHalf less than or equals to DEFAULT_START_AT_SECOND_HALF
        defaultMQuestStageShouldNotBeFound("startAtSecondHalf.lessThan=" + DEFAULT_START_AT_SECOND_HALF);

        // Get all the mQuestStageList where startAtSecondHalf less than or equals to UPDATED_START_AT_SECOND_HALF
        defaultMQuestStageShouldBeFound("startAtSecondHalf.lessThan=" + UPDATED_START_AT_SECOND_HALF);
    }


    @Test
    @Transactional
    public void getAllMQuestStagesByStartScoreIsEqualToSomething() throws Exception {
        // Initialize the database
        mQuestStageRepository.saveAndFlush(mQuestStage);

        // Get all the mQuestStageList where startScore equals to DEFAULT_START_SCORE
        defaultMQuestStageShouldBeFound("startScore.equals=" + DEFAULT_START_SCORE);

        // Get all the mQuestStageList where startScore equals to UPDATED_START_SCORE
        defaultMQuestStageShouldNotBeFound("startScore.equals=" + UPDATED_START_SCORE);
    }

    @Test
    @Transactional
    public void getAllMQuestStagesByStartScoreIsInShouldWork() throws Exception {
        // Initialize the database
        mQuestStageRepository.saveAndFlush(mQuestStage);

        // Get all the mQuestStageList where startScore in DEFAULT_START_SCORE or UPDATED_START_SCORE
        defaultMQuestStageShouldBeFound("startScore.in=" + DEFAULT_START_SCORE + "," + UPDATED_START_SCORE);

        // Get all the mQuestStageList where startScore equals to UPDATED_START_SCORE
        defaultMQuestStageShouldNotBeFound("startScore.in=" + UPDATED_START_SCORE);
    }

    @Test
    @Transactional
    public void getAllMQuestStagesByStartScoreIsNullOrNotNull() throws Exception {
        // Initialize the database
        mQuestStageRepository.saveAndFlush(mQuestStage);

        // Get all the mQuestStageList where startScore is not null
        defaultMQuestStageShouldBeFound("startScore.specified=true");

        // Get all the mQuestStageList where startScore is null
        defaultMQuestStageShouldNotBeFound("startScore.specified=false");
    }

    @Test
    @Transactional
    public void getAllMQuestStagesByStartScoreIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mQuestStageRepository.saveAndFlush(mQuestStage);

        // Get all the mQuestStageList where startScore greater than or equals to DEFAULT_START_SCORE
        defaultMQuestStageShouldBeFound("startScore.greaterOrEqualThan=" + DEFAULT_START_SCORE);

        // Get all the mQuestStageList where startScore greater than or equals to UPDATED_START_SCORE
        defaultMQuestStageShouldNotBeFound("startScore.greaterOrEqualThan=" + UPDATED_START_SCORE);
    }

    @Test
    @Transactional
    public void getAllMQuestStagesByStartScoreIsLessThanSomething() throws Exception {
        // Initialize the database
        mQuestStageRepository.saveAndFlush(mQuestStage);

        // Get all the mQuestStageList where startScore less than or equals to DEFAULT_START_SCORE
        defaultMQuestStageShouldNotBeFound("startScore.lessThan=" + DEFAULT_START_SCORE);

        // Get all the mQuestStageList where startScore less than or equals to UPDATED_START_SCORE
        defaultMQuestStageShouldBeFound("startScore.lessThan=" + UPDATED_START_SCORE);
    }


    @Test
    @Transactional
    public void getAllMQuestStagesByStartScoreOpponentIsEqualToSomething() throws Exception {
        // Initialize the database
        mQuestStageRepository.saveAndFlush(mQuestStage);

        // Get all the mQuestStageList where startScoreOpponent equals to DEFAULT_START_SCORE_OPPONENT
        defaultMQuestStageShouldBeFound("startScoreOpponent.equals=" + DEFAULT_START_SCORE_OPPONENT);

        // Get all the mQuestStageList where startScoreOpponent equals to UPDATED_START_SCORE_OPPONENT
        defaultMQuestStageShouldNotBeFound("startScoreOpponent.equals=" + UPDATED_START_SCORE_OPPONENT);
    }

    @Test
    @Transactional
    public void getAllMQuestStagesByStartScoreOpponentIsInShouldWork() throws Exception {
        // Initialize the database
        mQuestStageRepository.saveAndFlush(mQuestStage);

        // Get all the mQuestStageList where startScoreOpponent in DEFAULT_START_SCORE_OPPONENT or UPDATED_START_SCORE_OPPONENT
        defaultMQuestStageShouldBeFound("startScoreOpponent.in=" + DEFAULT_START_SCORE_OPPONENT + "," + UPDATED_START_SCORE_OPPONENT);

        // Get all the mQuestStageList where startScoreOpponent equals to UPDATED_START_SCORE_OPPONENT
        defaultMQuestStageShouldNotBeFound("startScoreOpponent.in=" + UPDATED_START_SCORE_OPPONENT);
    }

    @Test
    @Transactional
    public void getAllMQuestStagesByStartScoreOpponentIsNullOrNotNull() throws Exception {
        // Initialize the database
        mQuestStageRepository.saveAndFlush(mQuestStage);

        // Get all the mQuestStageList where startScoreOpponent is not null
        defaultMQuestStageShouldBeFound("startScoreOpponent.specified=true");

        // Get all the mQuestStageList where startScoreOpponent is null
        defaultMQuestStageShouldNotBeFound("startScoreOpponent.specified=false");
    }

    @Test
    @Transactional
    public void getAllMQuestStagesByStartScoreOpponentIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mQuestStageRepository.saveAndFlush(mQuestStage);

        // Get all the mQuestStageList where startScoreOpponent greater than or equals to DEFAULT_START_SCORE_OPPONENT
        defaultMQuestStageShouldBeFound("startScoreOpponent.greaterOrEqualThan=" + DEFAULT_START_SCORE_OPPONENT);

        // Get all the mQuestStageList where startScoreOpponent greater than or equals to UPDATED_START_SCORE_OPPONENT
        defaultMQuestStageShouldNotBeFound("startScoreOpponent.greaterOrEqualThan=" + UPDATED_START_SCORE_OPPONENT);
    }

    @Test
    @Transactional
    public void getAllMQuestStagesByStartScoreOpponentIsLessThanSomething() throws Exception {
        // Initialize the database
        mQuestStageRepository.saveAndFlush(mQuestStage);

        // Get all the mQuestStageList where startScoreOpponent less than or equals to DEFAULT_START_SCORE_OPPONENT
        defaultMQuestStageShouldNotBeFound("startScoreOpponent.lessThan=" + DEFAULT_START_SCORE_OPPONENT);

        // Get all the mQuestStageList where startScoreOpponent less than or equals to UPDATED_START_SCORE_OPPONENT
        defaultMQuestStageShouldBeFound("startScoreOpponent.lessThan=" + UPDATED_START_SCORE_OPPONENT);
    }


    @Test
    @Transactional
    public void getAllMQuestStagesByConditionIdIsEqualToSomething() throws Exception {
        // Initialize the database
        mQuestStageRepository.saveAndFlush(mQuestStage);

        // Get all the mQuestStageList where conditionId equals to DEFAULT_CONDITION_ID
        defaultMQuestStageShouldBeFound("conditionId.equals=" + DEFAULT_CONDITION_ID);

        // Get all the mQuestStageList where conditionId equals to UPDATED_CONDITION_ID
        defaultMQuestStageShouldNotBeFound("conditionId.equals=" + UPDATED_CONDITION_ID);
    }

    @Test
    @Transactional
    public void getAllMQuestStagesByConditionIdIsInShouldWork() throws Exception {
        // Initialize the database
        mQuestStageRepository.saveAndFlush(mQuestStage);

        // Get all the mQuestStageList where conditionId in DEFAULT_CONDITION_ID or UPDATED_CONDITION_ID
        defaultMQuestStageShouldBeFound("conditionId.in=" + DEFAULT_CONDITION_ID + "," + UPDATED_CONDITION_ID);

        // Get all the mQuestStageList where conditionId equals to UPDATED_CONDITION_ID
        defaultMQuestStageShouldNotBeFound("conditionId.in=" + UPDATED_CONDITION_ID);
    }

    @Test
    @Transactional
    public void getAllMQuestStagesByConditionIdIsNullOrNotNull() throws Exception {
        // Initialize the database
        mQuestStageRepository.saveAndFlush(mQuestStage);

        // Get all the mQuestStageList where conditionId is not null
        defaultMQuestStageShouldBeFound("conditionId.specified=true");

        // Get all the mQuestStageList where conditionId is null
        defaultMQuestStageShouldNotBeFound("conditionId.specified=false");
    }

    @Test
    @Transactional
    public void getAllMQuestStagesByConditionIdIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mQuestStageRepository.saveAndFlush(mQuestStage);

        // Get all the mQuestStageList where conditionId greater than or equals to DEFAULT_CONDITION_ID
        defaultMQuestStageShouldBeFound("conditionId.greaterOrEqualThan=" + DEFAULT_CONDITION_ID);

        // Get all the mQuestStageList where conditionId greater than or equals to UPDATED_CONDITION_ID
        defaultMQuestStageShouldNotBeFound("conditionId.greaterOrEqualThan=" + UPDATED_CONDITION_ID);
    }

    @Test
    @Transactional
    public void getAllMQuestStagesByConditionIdIsLessThanSomething() throws Exception {
        // Initialize the database
        mQuestStageRepository.saveAndFlush(mQuestStage);

        // Get all the mQuestStageList where conditionId less than or equals to DEFAULT_CONDITION_ID
        defaultMQuestStageShouldNotBeFound("conditionId.lessThan=" + DEFAULT_CONDITION_ID);

        // Get all the mQuestStageList where conditionId less than or equals to UPDATED_CONDITION_ID
        defaultMQuestStageShouldBeFound("conditionId.lessThan=" + UPDATED_CONDITION_ID);
    }


    @Test
    @Transactional
    public void getAllMQuestStagesByOptionIdIsEqualToSomething() throws Exception {
        // Initialize the database
        mQuestStageRepository.saveAndFlush(mQuestStage);

        // Get all the mQuestStageList where optionId equals to DEFAULT_OPTION_ID
        defaultMQuestStageShouldBeFound("optionId.equals=" + DEFAULT_OPTION_ID);

        // Get all the mQuestStageList where optionId equals to UPDATED_OPTION_ID
        defaultMQuestStageShouldNotBeFound("optionId.equals=" + UPDATED_OPTION_ID);
    }

    @Test
    @Transactional
    public void getAllMQuestStagesByOptionIdIsInShouldWork() throws Exception {
        // Initialize the database
        mQuestStageRepository.saveAndFlush(mQuestStage);

        // Get all the mQuestStageList where optionId in DEFAULT_OPTION_ID or UPDATED_OPTION_ID
        defaultMQuestStageShouldBeFound("optionId.in=" + DEFAULT_OPTION_ID + "," + UPDATED_OPTION_ID);

        // Get all the mQuestStageList where optionId equals to UPDATED_OPTION_ID
        defaultMQuestStageShouldNotBeFound("optionId.in=" + UPDATED_OPTION_ID);
    }

    @Test
    @Transactional
    public void getAllMQuestStagesByOptionIdIsNullOrNotNull() throws Exception {
        // Initialize the database
        mQuestStageRepository.saveAndFlush(mQuestStage);

        // Get all the mQuestStageList where optionId is not null
        defaultMQuestStageShouldBeFound("optionId.specified=true");

        // Get all the mQuestStageList where optionId is null
        defaultMQuestStageShouldNotBeFound("optionId.specified=false");
    }

    @Test
    @Transactional
    public void getAllMQuestStagesByOptionIdIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mQuestStageRepository.saveAndFlush(mQuestStage);

        // Get all the mQuestStageList where optionId greater than or equals to DEFAULT_OPTION_ID
        defaultMQuestStageShouldBeFound("optionId.greaterOrEqualThan=" + DEFAULT_OPTION_ID);

        // Get all the mQuestStageList where optionId greater than or equals to UPDATED_OPTION_ID
        defaultMQuestStageShouldNotBeFound("optionId.greaterOrEqualThan=" + UPDATED_OPTION_ID);
    }

    @Test
    @Transactional
    public void getAllMQuestStagesByOptionIdIsLessThanSomething() throws Exception {
        // Initialize the database
        mQuestStageRepository.saveAndFlush(mQuestStage);

        // Get all the mQuestStageList where optionId less than or equals to DEFAULT_OPTION_ID
        defaultMQuestStageShouldNotBeFound("optionId.lessThan=" + DEFAULT_OPTION_ID);

        // Get all the mQuestStageList where optionId less than or equals to UPDATED_OPTION_ID
        defaultMQuestStageShouldBeFound("optionId.lessThan=" + UPDATED_OPTION_ID);
    }


    @Test
    @Transactional
    public void getAllMQuestStagesByDeckConditionIdIsEqualToSomething() throws Exception {
        // Initialize the database
        mQuestStageRepository.saveAndFlush(mQuestStage);

        // Get all the mQuestStageList where deckConditionId equals to DEFAULT_DECK_CONDITION_ID
        defaultMQuestStageShouldBeFound("deckConditionId.equals=" + DEFAULT_DECK_CONDITION_ID);

        // Get all the mQuestStageList where deckConditionId equals to UPDATED_DECK_CONDITION_ID
        defaultMQuestStageShouldNotBeFound("deckConditionId.equals=" + UPDATED_DECK_CONDITION_ID);
    }

    @Test
    @Transactional
    public void getAllMQuestStagesByDeckConditionIdIsInShouldWork() throws Exception {
        // Initialize the database
        mQuestStageRepository.saveAndFlush(mQuestStage);

        // Get all the mQuestStageList where deckConditionId in DEFAULT_DECK_CONDITION_ID or UPDATED_DECK_CONDITION_ID
        defaultMQuestStageShouldBeFound("deckConditionId.in=" + DEFAULT_DECK_CONDITION_ID + "," + UPDATED_DECK_CONDITION_ID);

        // Get all the mQuestStageList where deckConditionId equals to UPDATED_DECK_CONDITION_ID
        defaultMQuestStageShouldNotBeFound("deckConditionId.in=" + UPDATED_DECK_CONDITION_ID);
    }

    @Test
    @Transactional
    public void getAllMQuestStagesByDeckConditionIdIsNullOrNotNull() throws Exception {
        // Initialize the database
        mQuestStageRepository.saveAndFlush(mQuestStage);

        // Get all the mQuestStageList where deckConditionId is not null
        defaultMQuestStageShouldBeFound("deckConditionId.specified=true");

        // Get all the mQuestStageList where deckConditionId is null
        defaultMQuestStageShouldNotBeFound("deckConditionId.specified=false");
    }

    @Test
    @Transactional
    public void getAllMQuestStagesByDeckConditionIdIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mQuestStageRepository.saveAndFlush(mQuestStage);

        // Get all the mQuestStageList where deckConditionId greater than or equals to DEFAULT_DECK_CONDITION_ID
        defaultMQuestStageShouldBeFound("deckConditionId.greaterOrEqualThan=" + DEFAULT_DECK_CONDITION_ID);

        // Get all the mQuestStageList where deckConditionId greater than or equals to UPDATED_DECK_CONDITION_ID
        defaultMQuestStageShouldNotBeFound("deckConditionId.greaterOrEqualThan=" + UPDATED_DECK_CONDITION_ID);
    }

    @Test
    @Transactional
    public void getAllMQuestStagesByDeckConditionIdIsLessThanSomething() throws Exception {
        // Initialize the database
        mQuestStageRepository.saveAndFlush(mQuestStage);

        // Get all the mQuestStageList where deckConditionId less than or equals to DEFAULT_DECK_CONDITION_ID
        defaultMQuestStageShouldNotBeFound("deckConditionId.lessThan=" + DEFAULT_DECK_CONDITION_ID);

        // Get all the mQuestStageList where deckConditionId less than or equals to UPDATED_DECK_CONDITION_ID
        defaultMQuestStageShouldBeFound("deckConditionId.lessThan=" + UPDATED_DECK_CONDITION_ID);
    }


    @Test
    @Transactional
    public void getAllMQuestStagesByIdIsEqualToSomething() throws Exception {
        // Get already existing entity
        MQuestWorld id = mQuestStage.getId();
        mQuestStageRepository.saveAndFlush(mQuestStage);
        Long idId = id.getId();

        // Get all the mQuestStageList where id equals to idId
        defaultMQuestStageShouldBeFound("idId.equals=" + idId);

        // Get all the mQuestStageList where id equals to idId + 1
        defaultMQuestStageShouldNotBeFound("idId.equals=" + (idId + 1));
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultMQuestStageShouldBeFound(String filter) throws Exception {
        restMQuestStageMockMvc.perform(get("/api/m-quest-stages?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(mQuestStage.getId().intValue())))
            .andExpect(jsonPath("$.[*].worldId").value(hasItem(DEFAULT_WORLD_ID)))
            .andExpect(jsonPath("$.[*].number").value(hasItem(DEFAULT_NUMBER)))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME.toString())))
            .andExpect(jsonPath("$.[*].imagePath").value(hasItem(DEFAULT_IMAGE_PATH.toString())))
            .andExpect(jsonPath("$.[*].characterThumbnailPath").value(hasItem(DEFAULT_CHARACTER_THUMBNAIL_PATH.toString())))
            .andExpect(jsonPath("$.[*].difficulty").value(hasItem(DEFAULT_DIFFICULTY)))
            .andExpect(jsonPath("$.[*].stageUnlockPattern").value(hasItem(DEFAULT_STAGE_UNLOCK_PATTERN)))
            .andExpect(jsonPath("$.[*].storyOnly").value(hasItem(DEFAULT_STORY_ONLY)))
            .andExpect(jsonPath("$.[*].firstHalfNpcDeckId").value(hasItem(DEFAULT_FIRST_HALF_NPC_DECK_ID)))
            .andExpect(jsonPath("$.[*].firstHalfEnvironmentId").value(hasItem(DEFAULT_FIRST_HALF_ENVIRONMENT_ID)))
            .andExpect(jsonPath("$.[*].secondHalfNpcDeckId").value(hasItem(DEFAULT_SECOND_HALF_NPC_DECK_ID)))
            .andExpect(jsonPath("$.[*].secondHalfEnvironmentId").value(hasItem(DEFAULT_SECOND_HALF_ENVIRONMENT_ID)))
            .andExpect(jsonPath("$.[*].extraFirstHalfNpcDeckId").value(hasItem(DEFAULT_EXTRA_FIRST_HALF_NPC_DECK_ID)))
            .andExpect(jsonPath("$.[*].consumeAp").value(hasItem(DEFAULT_CONSUME_AP)))
            .andExpect(jsonPath("$.[*].kickOffType").value(hasItem(DEFAULT_KICK_OFF_TYPE)))
            .andExpect(jsonPath("$.[*].matchMinute").value(hasItem(DEFAULT_MATCH_MINUTE)))
            .andExpect(jsonPath("$.[*].enableExtra").value(hasItem(DEFAULT_ENABLE_EXTRA)))
            .andExpect(jsonPath("$.[*].enablePk").value(hasItem(DEFAULT_ENABLE_PK)))
            .andExpect(jsonPath("$.[*].aiLevel").value(hasItem(DEFAULT_AI_LEVEL)))
            .andExpect(jsonPath("$.[*].startAtSecondHalf").value(hasItem(DEFAULT_START_AT_SECOND_HALF)))
            .andExpect(jsonPath("$.[*].startScore").value(hasItem(DEFAULT_START_SCORE)))
            .andExpect(jsonPath("$.[*].startScoreOpponent").value(hasItem(DEFAULT_START_SCORE_OPPONENT)))
            .andExpect(jsonPath("$.[*].conditionId").value(hasItem(DEFAULT_CONDITION_ID)))
            .andExpect(jsonPath("$.[*].optionId").value(hasItem(DEFAULT_OPTION_ID)))
            .andExpect(jsonPath("$.[*].deckConditionId").value(hasItem(DEFAULT_DECK_CONDITION_ID)));

        // Check, that the count call also returns 1
        restMQuestStageMockMvc.perform(get("/api/m-quest-stages/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultMQuestStageShouldNotBeFound(String filter) throws Exception {
        restMQuestStageMockMvc.perform(get("/api/m-quest-stages?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restMQuestStageMockMvc.perform(get("/api/m-quest-stages/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("0"));
    }


    @Test
    @Transactional
    public void getNonExistingMQuestStage() throws Exception {
        // Get the mQuestStage
        restMQuestStageMockMvc.perform(get("/api/m-quest-stages/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateMQuestStage() throws Exception {
        // Initialize the database
        mQuestStageRepository.saveAndFlush(mQuestStage);

        int databaseSizeBeforeUpdate = mQuestStageRepository.findAll().size();

        // Update the mQuestStage
        MQuestStage updatedMQuestStage = mQuestStageRepository.findById(mQuestStage.getId()).get();
        // Disconnect from session so that the updates on updatedMQuestStage are not directly saved in db
        em.detach(updatedMQuestStage);
        updatedMQuestStage
            .worldId(UPDATED_WORLD_ID)
            .number(UPDATED_NUMBER)
            .name(UPDATED_NAME)
            .imagePath(UPDATED_IMAGE_PATH)
            .characterThumbnailPath(UPDATED_CHARACTER_THUMBNAIL_PATH)
            .difficulty(UPDATED_DIFFICULTY)
            .stageUnlockPattern(UPDATED_STAGE_UNLOCK_PATTERN)
            .storyOnly(UPDATED_STORY_ONLY)
            .firstHalfNpcDeckId(UPDATED_FIRST_HALF_NPC_DECK_ID)
            .firstHalfEnvironmentId(UPDATED_FIRST_HALF_ENVIRONMENT_ID)
            .secondHalfNpcDeckId(UPDATED_SECOND_HALF_NPC_DECK_ID)
            .secondHalfEnvironmentId(UPDATED_SECOND_HALF_ENVIRONMENT_ID)
            .extraFirstHalfNpcDeckId(UPDATED_EXTRA_FIRST_HALF_NPC_DECK_ID)
            .consumeAp(UPDATED_CONSUME_AP)
            .kickOffType(UPDATED_KICK_OFF_TYPE)
            .matchMinute(UPDATED_MATCH_MINUTE)
            .enableExtra(UPDATED_ENABLE_EXTRA)
            .enablePk(UPDATED_ENABLE_PK)
            .aiLevel(UPDATED_AI_LEVEL)
            .startAtSecondHalf(UPDATED_START_AT_SECOND_HALF)
            .startScore(UPDATED_START_SCORE)
            .startScoreOpponent(UPDATED_START_SCORE_OPPONENT)
            .conditionId(UPDATED_CONDITION_ID)
            .optionId(UPDATED_OPTION_ID)
            .deckConditionId(UPDATED_DECK_CONDITION_ID);
        MQuestStageDTO mQuestStageDTO = mQuestStageMapper.toDto(updatedMQuestStage);

        restMQuestStageMockMvc.perform(put("/api/m-quest-stages")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mQuestStageDTO)))
            .andExpect(status().isOk());

        // Validate the MQuestStage in the database
        List<MQuestStage> mQuestStageList = mQuestStageRepository.findAll();
        assertThat(mQuestStageList).hasSize(databaseSizeBeforeUpdate);
        MQuestStage testMQuestStage = mQuestStageList.get(mQuestStageList.size() - 1);
        assertThat(testMQuestStage.getWorldId()).isEqualTo(UPDATED_WORLD_ID);
        assertThat(testMQuestStage.getNumber()).isEqualTo(UPDATED_NUMBER);
        assertThat(testMQuestStage.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testMQuestStage.getImagePath()).isEqualTo(UPDATED_IMAGE_PATH);
        assertThat(testMQuestStage.getCharacterThumbnailPath()).isEqualTo(UPDATED_CHARACTER_THUMBNAIL_PATH);
        assertThat(testMQuestStage.getDifficulty()).isEqualTo(UPDATED_DIFFICULTY);
        assertThat(testMQuestStage.getStageUnlockPattern()).isEqualTo(UPDATED_STAGE_UNLOCK_PATTERN);
        assertThat(testMQuestStage.getStoryOnly()).isEqualTo(UPDATED_STORY_ONLY);
        assertThat(testMQuestStage.getFirstHalfNpcDeckId()).isEqualTo(UPDATED_FIRST_HALF_NPC_DECK_ID);
        assertThat(testMQuestStage.getFirstHalfEnvironmentId()).isEqualTo(UPDATED_FIRST_HALF_ENVIRONMENT_ID);
        assertThat(testMQuestStage.getSecondHalfNpcDeckId()).isEqualTo(UPDATED_SECOND_HALF_NPC_DECK_ID);
        assertThat(testMQuestStage.getSecondHalfEnvironmentId()).isEqualTo(UPDATED_SECOND_HALF_ENVIRONMENT_ID);
        assertThat(testMQuestStage.getExtraFirstHalfNpcDeckId()).isEqualTo(UPDATED_EXTRA_FIRST_HALF_NPC_DECK_ID);
        assertThat(testMQuestStage.getConsumeAp()).isEqualTo(UPDATED_CONSUME_AP);
        assertThat(testMQuestStage.getKickOffType()).isEqualTo(UPDATED_KICK_OFF_TYPE);
        assertThat(testMQuestStage.getMatchMinute()).isEqualTo(UPDATED_MATCH_MINUTE);
        assertThat(testMQuestStage.getEnableExtra()).isEqualTo(UPDATED_ENABLE_EXTRA);
        assertThat(testMQuestStage.getEnablePk()).isEqualTo(UPDATED_ENABLE_PK);
        assertThat(testMQuestStage.getAiLevel()).isEqualTo(UPDATED_AI_LEVEL);
        assertThat(testMQuestStage.getStartAtSecondHalf()).isEqualTo(UPDATED_START_AT_SECOND_HALF);
        assertThat(testMQuestStage.getStartScore()).isEqualTo(UPDATED_START_SCORE);
        assertThat(testMQuestStage.getStartScoreOpponent()).isEqualTo(UPDATED_START_SCORE_OPPONENT);
        assertThat(testMQuestStage.getConditionId()).isEqualTo(UPDATED_CONDITION_ID);
        assertThat(testMQuestStage.getOptionId()).isEqualTo(UPDATED_OPTION_ID);
        assertThat(testMQuestStage.getDeckConditionId()).isEqualTo(UPDATED_DECK_CONDITION_ID);
    }

    @Test
    @Transactional
    public void updateNonExistingMQuestStage() throws Exception {
        int databaseSizeBeforeUpdate = mQuestStageRepository.findAll().size();

        // Create the MQuestStage
        MQuestStageDTO mQuestStageDTO = mQuestStageMapper.toDto(mQuestStage);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restMQuestStageMockMvc.perform(put("/api/m-quest-stages")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mQuestStageDTO)))
            .andExpect(status().isBadRequest());

        // Validate the MQuestStage in the database
        List<MQuestStage> mQuestStageList = mQuestStageRepository.findAll();
        assertThat(mQuestStageList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteMQuestStage() throws Exception {
        // Initialize the database
        mQuestStageRepository.saveAndFlush(mQuestStage);

        int databaseSizeBeforeDelete = mQuestStageRepository.findAll().size();

        // Delete the mQuestStage
        restMQuestStageMockMvc.perform(delete("/api/m-quest-stages/{id}", mQuestStage.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database is empty
        List<MQuestStage> mQuestStageList = mQuestStageRepository.findAll();
        assertThat(mQuestStageList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(MQuestStage.class);
        MQuestStage mQuestStage1 = new MQuestStage();
        mQuestStage1.setId(1L);
        MQuestStage mQuestStage2 = new MQuestStage();
        mQuestStage2.setId(mQuestStage1.getId());
        assertThat(mQuestStage1).isEqualTo(mQuestStage2);
        mQuestStage2.setId(2L);
        assertThat(mQuestStage1).isNotEqualTo(mQuestStage2);
        mQuestStage1.setId(null);
        assertThat(mQuestStage1).isNotEqualTo(mQuestStage2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(MQuestStageDTO.class);
        MQuestStageDTO mQuestStageDTO1 = new MQuestStageDTO();
        mQuestStageDTO1.setId(1L);
        MQuestStageDTO mQuestStageDTO2 = new MQuestStageDTO();
        assertThat(mQuestStageDTO1).isNotEqualTo(mQuestStageDTO2);
        mQuestStageDTO2.setId(mQuestStageDTO1.getId());
        assertThat(mQuestStageDTO1).isEqualTo(mQuestStageDTO2);
        mQuestStageDTO2.setId(2L);
        assertThat(mQuestStageDTO1).isNotEqualTo(mQuestStageDTO2);
        mQuestStageDTO1.setId(null);
        assertThat(mQuestStageDTO1).isNotEqualTo(mQuestStageDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(mQuestStageMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(mQuestStageMapper.fromId(null)).isNull();
    }
}
