package io.shm.tsubasa.web.rest;

import io.shm.tsubasa.TsubasaApp;
import io.shm.tsubasa.domain.MLuckWeeklyQuestStage;
import io.shm.tsubasa.domain.MLuckWeeklyQuestWorld;
import io.shm.tsubasa.repository.MLuckWeeklyQuestStageRepository;
import io.shm.tsubasa.service.MLuckWeeklyQuestStageService;
import io.shm.tsubasa.service.dto.MLuckWeeklyQuestStageDTO;
import io.shm.tsubasa.service.mapper.MLuckWeeklyQuestStageMapper;
import io.shm.tsubasa.web.rest.errors.ExceptionTranslator;
import io.shm.tsubasa.service.dto.MLuckWeeklyQuestStageCriteria;
import io.shm.tsubasa.service.MLuckWeeklyQuestStageQueryService;

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
 * Integration tests for the {@Link MLuckWeeklyQuestStageResource} REST controller.
 */
@SpringBootTest(classes = TsubasaApp.class)
public class MLuckWeeklyQuestStageResourceIT {

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

    private static final Integer DEFAULT_EXTRA_SECOND_HALF_NPC_DECK_ID = 1;
    private static final Integer UPDATED_EXTRA_SECOND_HALF_NPC_DECK_ID = 2;

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

    private static final Integer DEFAULT_LUCK_ID = 1;
    private static final Integer UPDATED_LUCK_ID = 2;

    @Autowired
    private MLuckWeeklyQuestStageRepository mLuckWeeklyQuestStageRepository;

    @Autowired
    private MLuckWeeklyQuestStageMapper mLuckWeeklyQuestStageMapper;

    @Autowired
    private MLuckWeeklyQuestStageService mLuckWeeklyQuestStageService;

    @Autowired
    private MLuckWeeklyQuestStageQueryService mLuckWeeklyQuestStageQueryService;

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

    private MockMvc restMLuckWeeklyQuestStageMockMvc;

    private MLuckWeeklyQuestStage mLuckWeeklyQuestStage;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final MLuckWeeklyQuestStageResource mLuckWeeklyQuestStageResource = new MLuckWeeklyQuestStageResource(mLuckWeeklyQuestStageService, mLuckWeeklyQuestStageQueryService);
        this.restMLuckWeeklyQuestStageMockMvc = MockMvcBuilders.standaloneSetup(mLuckWeeklyQuestStageResource)
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
    public static MLuckWeeklyQuestStage createEntity(EntityManager em) {
        MLuckWeeklyQuestStage mLuckWeeklyQuestStage = new MLuckWeeklyQuestStage()
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
            .extraSecondHalfNpcDeckId(DEFAULT_EXTRA_SECOND_HALF_NPC_DECK_ID)
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
            .deckConditionId(DEFAULT_DECK_CONDITION_ID)
            .luckId(DEFAULT_LUCK_ID);
        // Add required entity
        MLuckWeeklyQuestWorld mLuckWeeklyQuestWorld;
        if (TestUtil.findAll(em, MLuckWeeklyQuestWorld.class).isEmpty()) {
            mLuckWeeklyQuestWorld = MLuckWeeklyQuestWorldResourceIT.createEntity(em);
            em.persist(mLuckWeeklyQuestWorld);
            em.flush();
        } else {
            mLuckWeeklyQuestWorld = TestUtil.findAll(em, MLuckWeeklyQuestWorld.class).get(0);
        }
        mLuckWeeklyQuestStage.setId(mLuckWeeklyQuestWorld);
        return mLuckWeeklyQuestStage;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static MLuckWeeklyQuestStage createUpdatedEntity(EntityManager em) {
        MLuckWeeklyQuestStage mLuckWeeklyQuestStage = new MLuckWeeklyQuestStage()
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
            .extraSecondHalfNpcDeckId(UPDATED_EXTRA_SECOND_HALF_NPC_DECK_ID)
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
            .deckConditionId(UPDATED_DECK_CONDITION_ID)
            .luckId(UPDATED_LUCK_ID);
        // Add required entity
        MLuckWeeklyQuestWorld mLuckWeeklyQuestWorld;
        if (TestUtil.findAll(em, MLuckWeeklyQuestWorld.class).isEmpty()) {
            mLuckWeeklyQuestWorld = MLuckWeeklyQuestWorldResourceIT.createUpdatedEntity(em);
            em.persist(mLuckWeeklyQuestWorld);
            em.flush();
        } else {
            mLuckWeeklyQuestWorld = TestUtil.findAll(em, MLuckWeeklyQuestWorld.class).get(0);
        }
        mLuckWeeklyQuestStage.setId(mLuckWeeklyQuestWorld);
        return mLuckWeeklyQuestStage;
    }

    @BeforeEach
    public void initTest() {
        mLuckWeeklyQuestStage = createEntity(em);
    }

    @Test
    @Transactional
    public void createMLuckWeeklyQuestStage() throws Exception {
        int databaseSizeBeforeCreate = mLuckWeeklyQuestStageRepository.findAll().size();

        // Create the MLuckWeeklyQuestStage
        MLuckWeeklyQuestStageDTO mLuckWeeklyQuestStageDTO = mLuckWeeklyQuestStageMapper.toDto(mLuckWeeklyQuestStage);
        restMLuckWeeklyQuestStageMockMvc.perform(post("/api/m-luck-weekly-quest-stages")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mLuckWeeklyQuestStageDTO)))
            .andExpect(status().isCreated());

        // Validate the MLuckWeeklyQuestStage in the database
        List<MLuckWeeklyQuestStage> mLuckWeeklyQuestStageList = mLuckWeeklyQuestStageRepository.findAll();
        assertThat(mLuckWeeklyQuestStageList).hasSize(databaseSizeBeforeCreate + 1);
        MLuckWeeklyQuestStage testMLuckWeeklyQuestStage = mLuckWeeklyQuestStageList.get(mLuckWeeklyQuestStageList.size() - 1);
        assertThat(testMLuckWeeklyQuestStage.getWorldId()).isEqualTo(DEFAULT_WORLD_ID);
        assertThat(testMLuckWeeklyQuestStage.getNumber()).isEqualTo(DEFAULT_NUMBER);
        assertThat(testMLuckWeeklyQuestStage.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testMLuckWeeklyQuestStage.getImagePath()).isEqualTo(DEFAULT_IMAGE_PATH);
        assertThat(testMLuckWeeklyQuestStage.getCharacterThumbnailPath()).isEqualTo(DEFAULT_CHARACTER_THUMBNAIL_PATH);
        assertThat(testMLuckWeeklyQuestStage.getDifficulty()).isEqualTo(DEFAULT_DIFFICULTY);
        assertThat(testMLuckWeeklyQuestStage.getStageUnlockPattern()).isEqualTo(DEFAULT_STAGE_UNLOCK_PATTERN);
        assertThat(testMLuckWeeklyQuestStage.getStoryOnly()).isEqualTo(DEFAULT_STORY_ONLY);
        assertThat(testMLuckWeeklyQuestStage.getFirstHalfNpcDeckId()).isEqualTo(DEFAULT_FIRST_HALF_NPC_DECK_ID);
        assertThat(testMLuckWeeklyQuestStage.getFirstHalfEnvironmentId()).isEqualTo(DEFAULT_FIRST_HALF_ENVIRONMENT_ID);
        assertThat(testMLuckWeeklyQuestStage.getSecondHalfNpcDeckId()).isEqualTo(DEFAULT_SECOND_HALF_NPC_DECK_ID);
        assertThat(testMLuckWeeklyQuestStage.getSecondHalfEnvironmentId()).isEqualTo(DEFAULT_SECOND_HALF_ENVIRONMENT_ID);
        assertThat(testMLuckWeeklyQuestStage.getExtraFirstHalfNpcDeckId()).isEqualTo(DEFAULT_EXTRA_FIRST_HALF_NPC_DECK_ID);
        assertThat(testMLuckWeeklyQuestStage.getExtraSecondHalfNpcDeckId()).isEqualTo(DEFAULT_EXTRA_SECOND_HALF_NPC_DECK_ID);
        assertThat(testMLuckWeeklyQuestStage.getConsumeAp()).isEqualTo(DEFAULT_CONSUME_AP);
        assertThat(testMLuckWeeklyQuestStage.getKickOffType()).isEqualTo(DEFAULT_KICK_OFF_TYPE);
        assertThat(testMLuckWeeklyQuestStage.getMatchMinute()).isEqualTo(DEFAULT_MATCH_MINUTE);
        assertThat(testMLuckWeeklyQuestStage.getEnableExtra()).isEqualTo(DEFAULT_ENABLE_EXTRA);
        assertThat(testMLuckWeeklyQuestStage.getEnablePk()).isEqualTo(DEFAULT_ENABLE_PK);
        assertThat(testMLuckWeeklyQuestStage.getAiLevel()).isEqualTo(DEFAULT_AI_LEVEL);
        assertThat(testMLuckWeeklyQuestStage.getStartAtSecondHalf()).isEqualTo(DEFAULT_START_AT_SECOND_HALF);
        assertThat(testMLuckWeeklyQuestStage.getStartScore()).isEqualTo(DEFAULT_START_SCORE);
        assertThat(testMLuckWeeklyQuestStage.getStartScoreOpponent()).isEqualTo(DEFAULT_START_SCORE_OPPONENT);
        assertThat(testMLuckWeeklyQuestStage.getConditionId()).isEqualTo(DEFAULT_CONDITION_ID);
        assertThat(testMLuckWeeklyQuestStage.getOptionId()).isEqualTo(DEFAULT_OPTION_ID);
        assertThat(testMLuckWeeklyQuestStage.getDeckConditionId()).isEqualTo(DEFAULT_DECK_CONDITION_ID);
        assertThat(testMLuckWeeklyQuestStage.getLuckId()).isEqualTo(DEFAULT_LUCK_ID);
    }

    @Test
    @Transactional
    public void createMLuckWeeklyQuestStageWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = mLuckWeeklyQuestStageRepository.findAll().size();

        // Create the MLuckWeeklyQuestStage with an existing ID
        mLuckWeeklyQuestStage.setId(1L);
        MLuckWeeklyQuestStageDTO mLuckWeeklyQuestStageDTO = mLuckWeeklyQuestStageMapper.toDto(mLuckWeeklyQuestStage);

        // An entity with an existing ID cannot be created, so this API call must fail
        restMLuckWeeklyQuestStageMockMvc.perform(post("/api/m-luck-weekly-quest-stages")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mLuckWeeklyQuestStageDTO)))
            .andExpect(status().isBadRequest());

        // Validate the MLuckWeeklyQuestStage in the database
        List<MLuckWeeklyQuestStage> mLuckWeeklyQuestStageList = mLuckWeeklyQuestStageRepository.findAll();
        assertThat(mLuckWeeklyQuestStageList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkWorldIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = mLuckWeeklyQuestStageRepository.findAll().size();
        // set the field null
        mLuckWeeklyQuestStage.setWorldId(null);

        // Create the MLuckWeeklyQuestStage, which fails.
        MLuckWeeklyQuestStageDTO mLuckWeeklyQuestStageDTO = mLuckWeeklyQuestStageMapper.toDto(mLuckWeeklyQuestStage);

        restMLuckWeeklyQuestStageMockMvc.perform(post("/api/m-luck-weekly-quest-stages")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mLuckWeeklyQuestStageDTO)))
            .andExpect(status().isBadRequest());

        List<MLuckWeeklyQuestStage> mLuckWeeklyQuestStageList = mLuckWeeklyQuestStageRepository.findAll();
        assertThat(mLuckWeeklyQuestStageList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkNumberIsRequired() throws Exception {
        int databaseSizeBeforeTest = mLuckWeeklyQuestStageRepository.findAll().size();
        // set the field null
        mLuckWeeklyQuestStage.setNumber(null);

        // Create the MLuckWeeklyQuestStage, which fails.
        MLuckWeeklyQuestStageDTO mLuckWeeklyQuestStageDTO = mLuckWeeklyQuestStageMapper.toDto(mLuckWeeklyQuestStage);

        restMLuckWeeklyQuestStageMockMvc.perform(post("/api/m-luck-weekly-quest-stages")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mLuckWeeklyQuestStageDTO)))
            .andExpect(status().isBadRequest());

        List<MLuckWeeklyQuestStage> mLuckWeeklyQuestStageList = mLuckWeeklyQuestStageRepository.findAll();
        assertThat(mLuckWeeklyQuestStageList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkDifficultyIsRequired() throws Exception {
        int databaseSizeBeforeTest = mLuckWeeklyQuestStageRepository.findAll().size();
        // set the field null
        mLuckWeeklyQuestStage.setDifficulty(null);

        // Create the MLuckWeeklyQuestStage, which fails.
        MLuckWeeklyQuestStageDTO mLuckWeeklyQuestStageDTO = mLuckWeeklyQuestStageMapper.toDto(mLuckWeeklyQuestStage);

        restMLuckWeeklyQuestStageMockMvc.perform(post("/api/m-luck-weekly-quest-stages")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mLuckWeeklyQuestStageDTO)))
            .andExpect(status().isBadRequest());

        List<MLuckWeeklyQuestStage> mLuckWeeklyQuestStageList = mLuckWeeklyQuestStageRepository.findAll();
        assertThat(mLuckWeeklyQuestStageList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkStageUnlockPatternIsRequired() throws Exception {
        int databaseSizeBeforeTest = mLuckWeeklyQuestStageRepository.findAll().size();
        // set the field null
        mLuckWeeklyQuestStage.setStageUnlockPattern(null);

        // Create the MLuckWeeklyQuestStage, which fails.
        MLuckWeeklyQuestStageDTO mLuckWeeklyQuestStageDTO = mLuckWeeklyQuestStageMapper.toDto(mLuckWeeklyQuestStage);

        restMLuckWeeklyQuestStageMockMvc.perform(post("/api/m-luck-weekly-quest-stages")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mLuckWeeklyQuestStageDTO)))
            .andExpect(status().isBadRequest());

        List<MLuckWeeklyQuestStage> mLuckWeeklyQuestStageList = mLuckWeeklyQuestStageRepository.findAll();
        assertThat(mLuckWeeklyQuestStageList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkStoryOnlyIsRequired() throws Exception {
        int databaseSizeBeforeTest = mLuckWeeklyQuestStageRepository.findAll().size();
        // set the field null
        mLuckWeeklyQuestStage.setStoryOnly(null);

        // Create the MLuckWeeklyQuestStage, which fails.
        MLuckWeeklyQuestStageDTO mLuckWeeklyQuestStageDTO = mLuckWeeklyQuestStageMapper.toDto(mLuckWeeklyQuestStage);

        restMLuckWeeklyQuestStageMockMvc.perform(post("/api/m-luck-weekly-quest-stages")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mLuckWeeklyQuestStageDTO)))
            .andExpect(status().isBadRequest());

        List<MLuckWeeklyQuestStage> mLuckWeeklyQuestStageList = mLuckWeeklyQuestStageRepository.findAll();
        assertThat(mLuckWeeklyQuestStageList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkFirstHalfNpcDeckIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = mLuckWeeklyQuestStageRepository.findAll().size();
        // set the field null
        mLuckWeeklyQuestStage.setFirstHalfNpcDeckId(null);

        // Create the MLuckWeeklyQuestStage, which fails.
        MLuckWeeklyQuestStageDTO mLuckWeeklyQuestStageDTO = mLuckWeeklyQuestStageMapper.toDto(mLuckWeeklyQuestStage);

        restMLuckWeeklyQuestStageMockMvc.perform(post("/api/m-luck-weekly-quest-stages")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mLuckWeeklyQuestStageDTO)))
            .andExpect(status().isBadRequest());

        List<MLuckWeeklyQuestStage> mLuckWeeklyQuestStageList = mLuckWeeklyQuestStageRepository.findAll();
        assertThat(mLuckWeeklyQuestStageList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkFirstHalfEnvironmentIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = mLuckWeeklyQuestStageRepository.findAll().size();
        // set the field null
        mLuckWeeklyQuestStage.setFirstHalfEnvironmentId(null);

        // Create the MLuckWeeklyQuestStage, which fails.
        MLuckWeeklyQuestStageDTO mLuckWeeklyQuestStageDTO = mLuckWeeklyQuestStageMapper.toDto(mLuckWeeklyQuestStage);

        restMLuckWeeklyQuestStageMockMvc.perform(post("/api/m-luck-weekly-quest-stages")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mLuckWeeklyQuestStageDTO)))
            .andExpect(status().isBadRequest());

        List<MLuckWeeklyQuestStage> mLuckWeeklyQuestStageList = mLuckWeeklyQuestStageRepository.findAll();
        assertThat(mLuckWeeklyQuestStageList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkSecondHalfNpcDeckIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = mLuckWeeklyQuestStageRepository.findAll().size();
        // set the field null
        mLuckWeeklyQuestStage.setSecondHalfNpcDeckId(null);

        // Create the MLuckWeeklyQuestStage, which fails.
        MLuckWeeklyQuestStageDTO mLuckWeeklyQuestStageDTO = mLuckWeeklyQuestStageMapper.toDto(mLuckWeeklyQuestStage);

        restMLuckWeeklyQuestStageMockMvc.perform(post("/api/m-luck-weekly-quest-stages")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mLuckWeeklyQuestStageDTO)))
            .andExpect(status().isBadRequest());

        List<MLuckWeeklyQuestStage> mLuckWeeklyQuestStageList = mLuckWeeklyQuestStageRepository.findAll();
        assertThat(mLuckWeeklyQuestStageList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkSecondHalfEnvironmentIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = mLuckWeeklyQuestStageRepository.findAll().size();
        // set the field null
        mLuckWeeklyQuestStage.setSecondHalfEnvironmentId(null);

        // Create the MLuckWeeklyQuestStage, which fails.
        MLuckWeeklyQuestStageDTO mLuckWeeklyQuestStageDTO = mLuckWeeklyQuestStageMapper.toDto(mLuckWeeklyQuestStage);

        restMLuckWeeklyQuestStageMockMvc.perform(post("/api/m-luck-weekly-quest-stages")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mLuckWeeklyQuestStageDTO)))
            .andExpect(status().isBadRequest());

        List<MLuckWeeklyQuestStage> mLuckWeeklyQuestStageList = mLuckWeeklyQuestStageRepository.findAll();
        assertThat(mLuckWeeklyQuestStageList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkExtraFirstHalfNpcDeckIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = mLuckWeeklyQuestStageRepository.findAll().size();
        // set the field null
        mLuckWeeklyQuestStage.setExtraFirstHalfNpcDeckId(null);

        // Create the MLuckWeeklyQuestStage, which fails.
        MLuckWeeklyQuestStageDTO mLuckWeeklyQuestStageDTO = mLuckWeeklyQuestStageMapper.toDto(mLuckWeeklyQuestStage);

        restMLuckWeeklyQuestStageMockMvc.perform(post("/api/m-luck-weekly-quest-stages")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mLuckWeeklyQuestStageDTO)))
            .andExpect(status().isBadRequest());

        List<MLuckWeeklyQuestStage> mLuckWeeklyQuestStageList = mLuckWeeklyQuestStageRepository.findAll();
        assertThat(mLuckWeeklyQuestStageList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkExtraSecondHalfNpcDeckIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = mLuckWeeklyQuestStageRepository.findAll().size();
        // set the field null
        mLuckWeeklyQuestStage.setExtraSecondHalfNpcDeckId(null);

        // Create the MLuckWeeklyQuestStage, which fails.
        MLuckWeeklyQuestStageDTO mLuckWeeklyQuestStageDTO = mLuckWeeklyQuestStageMapper.toDto(mLuckWeeklyQuestStage);

        restMLuckWeeklyQuestStageMockMvc.perform(post("/api/m-luck-weekly-quest-stages")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mLuckWeeklyQuestStageDTO)))
            .andExpect(status().isBadRequest());

        List<MLuckWeeklyQuestStage> mLuckWeeklyQuestStageList = mLuckWeeklyQuestStageRepository.findAll();
        assertThat(mLuckWeeklyQuestStageList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkConsumeApIsRequired() throws Exception {
        int databaseSizeBeforeTest = mLuckWeeklyQuestStageRepository.findAll().size();
        // set the field null
        mLuckWeeklyQuestStage.setConsumeAp(null);

        // Create the MLuckWeeklyQuestStage, which fails.
        MLuckWeeklyQuestStageDTO mLuckWeeklyQuestStageDTO = mLuckWeeklyQuestStageMapper.toDto(mLuckWeeklyQuestStage);

        restMLuckWeeklyQuestStageMockMvc.perform(post("/api/m-luck-weekly-quest-stages")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mLuckWeeklyQuestStageDTO)))
            .andExpect(status().isBadRequest());

        List<MLuckWeeklyQuestStage> mLuckWeeklyQuestStageList = mLuckWeeklyQuestStageRepository.findAll();
        assertThat(mLuckWeeklyQuestStageList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkKickOffTypeIsRequired() throws Exception {
        int databaseSizeBeforeTest = mLuckWeeklyQuestStageRepository.findAll().size();
        // set the field null
        mLuckWeeklyQuestStage.setKickOffType(null);

        // Create the MLuckWeeklyQuestStage, which fails.
        MLuckWeeklyQuestStageDTO mLuckWeeklyQuestStageDTO = mLuckWeeklyQuestStageMapper.toDto(mLuckWeeklyQuestStage);

        restMLuckWeeklyQuestStageMockMvc.perform(post("/api/m-luck-weekly-quest-stages")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mLuckWeeklyQuestStageDTO)))
            .andExpect(status().isBadRequest());

        List<MLuckWeeklyQuestStage> mLuckWeeklyQuestStageList = mLuckWeeklyQuestStageRepository.findAll();
        assertThat(mLuckWeeklyQuestStageList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkMatchMinuteIsRequired() throws Exception {
        int databaseSizeBeforeTest = mLuckWeeklyQuestStageRepository.findAll().size();
        // set the field null
        mLuckWeeklyQuestStage.setMatchMinute(null);

        // Create the MLuckWeeklyQuestStage, which fails.
        MLuckWeeklyQuestStageDTO mLuckWeeklyQuestStageDTO = mLuckWeeklyQuestStageMapper.toDto(mLuckWeeklyQuestStage);

        restMLuckWeeklyQuestStageMockMvc.perform(post("/api/m-luck-weekly-quest-stages")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mLuckWeeklyQuestStageDTO)))
            .andExpect(status().isBadRequest());

        List<MLuckWeeklyQuestStage> mLuckWeeklyQuestStageList = mLuckWeeklyQuestStageRepository.findAll();
        assertThat(mLuckWeeklyQuestStageList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkEnableExtraIsRequired() throws Exception {
        int databaseSizeBeforeTest = mLuckWeeklyQuestStageRepository.findAll().size();
        // set the field null
        mLuckWeeklyQuestStage.setEnableExtra(null);

        // Create the MLuckWeeklyQuestStage, which fails.
        MLuckWeeklyQuestStageDTO mLuckWeeklyQuestStageDTO = mLuckWeeklyQuestStageMapper.toDto(mLuckWeeklyQuestStage);

        restMLuckWeeklyQuestStageMockMvc.perform(post("/api/m-luck-weekly-quest-stages")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mLuckWeeklyQuestStageDTO)))
            .andExpect(status().isBadRequest());

        List<MLuckWeeklyQuestStage> mLuckWeeklyQuestStageList = mLuckWeeklyQuestStageRepository.findAll();
        assertThat(mLuckWeeklyQuestStageList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkEnablePkIsRequired() throws Exception {
        int databaseSizeBeforeTest = mLuckWeeklyQuestStageRepository.findAll().size();
        // set the field null
        mLuckWeeklyQuestStage.setEnablePk(null);

        // Create the MLuckWeeklyQuestStage, which fails.
        MLuckWeeklyQuestStageDTO mLuckWeeklyQuestStageDTO = mLuckWeeklyQuestStageMapper.toDto(mLuckWeeklyQuestStage);

        restMLuckWeeklyQuestStageMockMvc.perform(post("/api/m-luck-weekly-quest-stages")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mLuckWeeklyQuestStageDTO)))
            .andExpect(status().isBadRequest());

        List<MLuckWeeklyQuestStage> mLuckWeeklyQuestStageList = mLuckWeeklyQuestStageRepository.findAll();
        assertThat(mLuckWeeklyQuestStageList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkAiLevelIsRequired() throws Exception {
        int databaseSizeBeforeTest = mLuckWeeklyQuestStageRepository.findAll().size();
        // set the field null
        mLuckWeeklyQuestStage.setAiLevel(null);

        // Create the MLuckWeeklyQuestStage, which fails.
        MLuckWeeklyQuestStageDTO mLuckWeeklyQuestStageDTO = mLuckWeeklyQuestStageMapper.toDto(mLuckWeeklyQuestStage);

        restMLuckWeeklyQuestStageMockMvc.perform(post("/api/m-luck-weekly-quest-stages")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mLuckWeeklyQuestStageDTO)))
            .andExpect(status().isBadRequest());

        List<MLuckWeeklyQuestStage> mLuckWeeklyQuestStageList = mLuckWeeklyQuestStageRepository.findAll();
        assertThat(mLuckWeeklyQuestStageList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkStartAtSecondHalfIsRequired() throws Exception {
        int databaseSizeBeforeTest = mLuckWeeklyQuestStageRepository.findAll().size();
        // set the field null
        mLuckWeeklyQuestStage.setStartAtSecondHalf(null);

        // Create the MLuckWeeklyQuestStage, which fails.
        MLuckWeeklyQuestStageDTO mLuckWeeklyQuestStageDTO = mLuckWeeklyQuestStageMapper.toDto(mLuckWeeklyQuestStage);

        restMLuckWeeklyQuestStageMockMvc.perform(post("/api/m-luck-weekly-quest-stages")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mLuckWeeklyQuestStageDTO)))
            .andExpect(status().isBadRequest());

        List<MLuckWeeklyQuestStage> mLuckWeeklyQuestStageList = mLuckWeeklyQuestStageRepository.findAll();
        assertThat(mLuckWeeklyQuestStageList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkStartScoreIsRequired() throws Exception {
        int databaseSizeBeforeTest = mLuckWeeklyQuestStageRepository.findAll().size();
        // set the field null
        mLuckWeeklyQuestStage.setStartScore(null);

        // Create the MLuckWeeklyQuestStage, which fails.
        MLuckWeeklyQuestStageDTO mLuckWeeklyQuestStageDTO = mLuckWeeklyQuestStageMapper.toDto(mLuckWeeklyQuestStage);

        restMLuckWeeklyQuestStageMockMvc.perform(post("/api/m-luck-weekly-quest-stages")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mLuckWeeklyQuestStageDTO)))
            .andExpect(status().isBadRequest());

        List<MLuckWeeklyQuestStage> mLuckWeeklyQuestStageList = mLuckWeeklyQuestStageRepository.findAll();
        assertThat(mLuckWeeklyQuestStageList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkStartScoreOpponentIsRequired() throws Exception {
        int databaseSizeBeforeTest = mLuckWeeklyQuestStageRepository.findAll().size();
        // set the field null
        mLuckWeeklyQuestStage.setStartScoreOpponent(null);

        // Create the MLuckWeeklyQuestStage, which fails.
        MLuckWeeklyQuestStageDTO mLuckWeeklyQuestStageDTO = mLuckWeeklyQuestStageMapper.toDto(mLuckWeeklyQuestStage);

        restMLuckWeeklyQuestStageMockMvc.perform(post("/api/m-luck-weekly-quest-stages")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mLuckWeeklyQuestStageDTO)))
            .andExpect(status().isBadRequest());

        List<MLuckWeeklyQuestStage> mLuckWeeklyQuestStageList = mLuckWeeklyQuestStageRepository.findAll();
        assertThat(mLuckWeeklyQuestStageList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllMLuckWeeklyQuestStages() throws Exception {
        // Initialize the database
        mLuckWeeklyQuestStageRepository.saveAndFlush(mLuckWeeklyQuestStage);

        // Get all the mLuckWeeklyQuestStageList
        restMLuckWeeklyQuestStageMockMvc.perform(get("/api/m-luck-weekly-quest-stages?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(mLuckWeeklyQuestStage.getId().intValue())))
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
            .andExpect(jsonPath("$.[*].extraSecondHalfNpcDeckId").value(hasItem(DEFAULT_EXTRA_SECOND_HALF_NPC_DECK_ID)))
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
            .andExpect(jsonPath("$.[*].deckConditionId").value(hasItem(DEFAULT_DECK_CONDITION_ID)))
            .andExpect(jsonPath("$.[*].luckId").value(hasItem(DEFAULT_LUCK_ID)));
    }
    
    @Test
    @Transactional
    public void getMLuckWeeklyQuestStage() throws Exception {
        // Initialize the database
        mLuckWeeklyQuestStageRepository.saveAndFlush(mLuckWeeklyQuestStage);

        // Get the mLuckWeeklyQuestStage
        restMLuckWeeklyQuestStageMockMvc.perform(get("/api/m-luck-weekly-quest-stages/{id}", mLuckWeeklyQuestStage.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(mLuckWeeklyQuestStage.getId().intValue()))
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
            .andExpect(jsonPath("$.extraSecondHalfNpcDeckId").value(DEFAULT_EXTRA_SECOND_HALF_NPC_DECK_ID))
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
            .andExpect(jsonPath("$.deckConditionId").value(DEFAULT_DECK_CONDITION_ID))
            .andExpect(jsonPath("$.luckId").value(DEFAULT_LUCK_ID));
    }

    @Test
    @Transactional
    public void getAllMLuckWeeklyQuestStagesByWorldIdIsEqualToSomething() throws Exception {
        // Initialize the database
        mLuckWeeklyQuestStageRepository.saveAndFlush(mLuckWeeklyQuestStage);

        // Get all the mLuckWeeklyQuestStageList where worldId equals to DEFAULT_WORLD_ID
        defaultMLuckWeeklyQuestStageShouldBeFound("worldId.equals=" + DEFAULT_WORLD_ID);

        // Get all the mLuckWeeklyQuestStageList where worldId equals to UPDATED_WORLD_ID
        defaultMLuckWeeklyQuestStageShouldNotBeFound("worldId.equals=" + UPDATED_WORLD_ID);
    }

    @Test
    @Transactional
    public void getAllMLuckWeeklyQuestStagesByWorldIdIsInShouldWork() throws Exception {
        // Initialize the database
        mLuckWeeklyQuestStageRepository.saveAndFlush(mLuckWeeklyQuestStage);

        // Get all the mLuckWeeklyQuestStageList where worldId in DEFAULT_WORLD_ID or UPDATED_WORLD_ID
        defaultMLuckWeeklyQuestStageShouldBeFound("worldId.in=" + DEFAULT_WORLD_ID + "," + UPDATED_WORLD_ID);

        // Get all the mLuckWeeklyQuestStageList where worldId equals to UPDATED_WORLD_ID
        defaultMLuckWeeklyQuestStageShouldNotBeFound("worldId.in=" + UPDATED_WORLD_ID);
    }

    @Test
    @Transactional
    public void getAllMLuckWeeklyQuestStagesByWorldIdIsNullOrNotNull() throws Exception {
        // Initialize the database
        mLuckWeeklyQuestStageRepository.saveAndFlush(mLuckWeeklyQuestStage);

        // Get all the mLuckWeeklyQuestStageList where worldId is not null
        defaultMLuckWeeklyQuestStageShouldBeFound("worldId.specified=true");

        // Get all the mLuckWeeklyQuestStageList where worldId is null
        defaultMLuckWeeklyQuestStageShouldNotBeFound("worldId.specified=false");
    }

    @Test
    @Transactional
    public void getAllMLuckWeeklyQuestStagesByWorldIdIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mLuckWeeklyQuestStageRepository.saveAndFlush(mLuckWeeklyQuestStage);

        // Get all the mLuckWeeklyQuestStageList where worldId greater than or equals to DEFAULT_WORLD_ID
        defaultMLuckWeeklyQuestStageShouldBeFound("worldId.greaterOrEqualThan=" + DEFAULT_WORLD_ID);

        // Get all the mLuckWeeklyQuestStageList where worldId greater than or equals to UPDATED_WORLD_ID
        defaultMLuckWeeklyQuestStageShouldNotBeFound("worldId.greaterOrEqualThan=" + UPDATED_WORLD_ID);
    }

    @Test
    @Transactional
    public void getAllMLuckWeeklyQuestStagesByWorldIdIsLessThanSomething() throws Exception {
        // Initialize the database
        mLuckWeeklyQuestStageRepository.saveAndFlush(mLuckWeeklyQuestStage);

        // Get all the mLuckWeeklyQuestStageList where worldId less than or equals to DEFAULT_WORLD_ID
        defaultMLuckWeeklyQuestStageShouldNotBeFound("worldId.lessThan=" + DEFAULT_WORLD_ID);

        // Get all the mLuckWeeklyQuestStageList where worldId less than or equals to UPDATED_WORLD_ID
        defaultMLuckWeeklyQuestStageShouldBeFound("worldId.lessThan=" + UPDATED_WORLD_ID);
    }


    @Test
    @Transactional
    public void getAllMLuckWeeklyQuestStagesByNumberIsEqualToSomething() throws Exception {
        // Initialize the database
        mLuckWeeklyQuestStageRepository.saveAndFlush(mLuckWeeklyQuestStage);

        // Get all the mLuckWeeklyQuestStageList where number equals to DEFAULT_NUMBER
        defaultMLuckWeeklyQuestStageShouldBeFound("number.equals=" + DEFAULT_NUMBER);

        // Get all the mLuckWeeklyQuestStageList where number equals to UPDATED_NUMBER
        defaultMLuckWeeklyQuestStageShouldNotBeFound("number.equals=" + UPDATED_NUMBER);
    }

    @Test
    @Transactional
    public void getAllMLuckWeeklyQuestStagesByNumberIsInShouldWork() throws Exception {
        // Initialize the database
        mLuckWeeklyQuestStageRepository.saveAndFlush(mLuckWeeklyQuestStage);

        // Get all the mLuckWeeklyQuestStageList where number in DEFAULT_NUMBER or UPDATED_NUMBER
        defaultMLuckWeeklyQuestStageShouldBeFound("number.in=" + DEFAULT_NUMBER + "," + UPDATED_NUMBER);

        // Get all the mLuckWeeklyQuestStageList where number equals to UPDATED_NUMBER
        defaultMLuckWeeklyQuestStageShouldNotBeFound("number.in=" + UPDATED_NUMBER);
    }

    @Test
    @Transactional
    public void getAllMLuckWeeklyQuestStagesByNumberIsNullOrNotNull() throws Exception {
        // Initialize the database
        mLuckWeeklyQuestStageRepository.saveAndFlush(mLuckWeeklyQuestStage);

        // Get all the mLuckWeeklyQuestStageList where number is not null
        defaultMLuckWeeklyQuestStageShouldBeFound("number.specified=true");

        // Get all the mLuckWeeklyQuestStageList where number is null
        defaultMLuckWeeklyQuestStageShouldNotBeFound("number.specified=false");
    }

    @Test
    @Transactional
    public void getAllMLuckWeeklyQuestStagesByNumberIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mLuckWeeklyQuestStageRepository.saveAndFlush(mLuckWeeklyQuestStage);

        // Get all the mLuckWeeklyQuestStageList where number greater than or equals to DEFAULT_NUMBER
        defaultMLuckWeeklyQuestStageShouldBeFound("number.greaterOrEqualThan=" + DEFAULT_NUMBER);

        // Get all the mLuckWeeklyQuestStageList where number greater than or equals to UPDATED_NUMBER
        defaultMLuckWeeklyQuestStageShouldNotBeFound("number.greaterOrEqualThan=" + UPDATED_NUMBER);
    }

    @Test
    @Transactional
    public void getAllMLuckWeeklyQuestStagesByNumberIsLessThanSomething() throws Exception {
        // Initialize the database
        mLuckWeeklyQuestStageRepository.saveAndFlush(mLuckWeeklyQuestStage);

        // Get all the mLuckWeeklyQuestStageList where number less than or equals to DEFAULT_NUMBER
        defaultMLuckWeeklyQuestStageShouldNotBeFound("number.lessThan=" + DEFAULT_NUMBER);

        // Get all the mLuckWeeklyQuestStageList where number less than or equals to UPDATED_NUMBER
        defaultMLuckWeeklyQuestStageShouldBeFound("number.lessThan=" + UPDATED_NUMBER);
    }


    @Test
    @Transactional
    public void getAllMLuckWeeklyQuestStagesByDifficultyIsEqualToSomething() throws Exception {
        // Initialize the database
        mLuckWeeklyQuestStageRepository.saveAndFlush(mLuckWeeklyQuestStage);

        // Get all the mLuckWeeklyQuestStageList where difficulty equals to DEFAULT_DIFFICULTY
        defaultMLuckWeeklyQuestStageShouldBeFound("difficulty.equals=" + DEFAULT_DIFFICULTY);

        // Get all the mLuckWeeklyQuestStageList where difficulty equals to UPDATED_DIFFICULTY
        defaultMLuckWeeklyQuestStageShouldNotBeFound("difficulty.equals=" + UPDATED_DIFFICULTY);
    }

    @Test
    @Transactional
    public void getAllMLuckWeeklyQuestStagesByDifficultyIsInShouldWork() throws Exception {
        // Initialize the database
        mLuckWeeklyQuestStageRepository.saveAndFlush(mLuckWeeklyQuestStage);

        // Get all the mLuckWeeklyQuestStageList where difficulty in DEFAULT_DIFFICULTY or UPDATED_DIFFICULTY
        defaultMLuckWeeklyQuestStageShouldBeFound("difficulty.in=" + DEFAULT_DIFFICULTY + "," + UPDATED_DIFFICULTY);

        // Get all the mLuckWeeklyQuestStageList where difficulty equals to UPDATED_DIFFICULTY
        defaultMLuckWeeklyQuestStageShouldNotBeFound("difficulty.in=" + UPDATED_DIFFICULTY);
    }

    @Test
    @Transactional
    public void getAllMLuckWeeklyQuestStagesByDifficultyIsNullOrNotNull() throws Exception {
        // Initialize the database
        mLuckWeeklyQuestStageRepository.saveAndFlush(mLuckWeeklyQuestStage);

        // Get all the mLuckWeeklyQuestStageList where difficulty is not null
        defaultMLuckWeeklyQuestStageShouldBeFound("difficulty.specified=true");

        // Get all the mLuckWeeklyQuestStageList where difficulty is null
        defaultMLuckWeeklyQuestStageShouldNotBeFound("difficulty.specified=false");
    }

    @Test
    @Transactional
    public void getAllMLuckWeeklyQuestStagesByDifficultyIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mLuckWeeklyQuestStageRepository.saveAndFlush(mLuckWeeklyQuestStage);

        // Get all the mLuckWeeklyQuestStageList where difficulty greater than or equals to DEFAULT_DIFFICULTY
        defaultMLuckWeeklyQuestStageShouldBeFound("difficulty.greaterOrEqualThan=" + DEFAULT_DIFFICULTY);

        // Get all the mLuckWeeklyQuestStageList where difficulty greater than or equals to UPDATED_DIFFICULTY
        defaultMLuckWeeklyQuestStageShouldNotBeFound("difficulty.greaterOrEqualThan=" + UPDATED_DIFFICULTY);
    }

    @Test
    @Transactional
    public void getAllMLuckWeeklyQuestStagesByDifficultyIsLessThanSomething() throws Exception {
        // Initialize the database
        mLuckWeeklyQuestStageRepository.saveAndFlush(mLuckWeeklyQuestStage);

        // Get all the mLuckWeeklyQuestStageList where difficulty less than or equals to DEFAULT_DIFFICULTY
        defaultMLuckWeeklyQuestStageShouldNotBeFound("difficulty.lessThan=" + DEFAULT_DIFFICULTY);

        // Get all the mLuckWeeklyQuestStageList where difficulty less than or equals to UPDATED_DIFFICULTY
        defaultMLuckWeeklyQuestStageShouldBeFound("difficulty.lessThan=" + UPDATED_DIFFICULTY);
    }


    @Test
    @Transactional
    public void getAllMLuckWeeklyQuestStagesByStageUnlockPatternIsEqualToSomething() throws Exception {
        // Initialize the database
        mLuckWeeklyQuestStageRepository.saveAndFlush(mLuckWeeklyQuestStage);

        // Get all the mLuckWeeklyQuestStageList where stageUnlockPattern equals to DEFAULT_STAGE_UNLOCK_PATTERN
        defaultMLuckWeeklyQuestStageShouldBeFound("stageUnlockPattern.equals=" + DEFAULT_STAGE_UNLOCK_PATTERN);

        // Get all the mLuckWeeklyQuestStageList where stageUnlockPattern equals to UPDATED_STAGE_UNLOCK_PATTERN
        defaultMLuckWeeklyQuestStageShouldNotBeFound("stageUnlockPattern.equals=" + UPDATED_STAGE_UNLOCK_PATTERN);
    }

    @Test
    @Transactional
    public void getAllMLuckWeeklyQuestStagesByStageUnlockPatternIsInShouldWork() throws Exception {
        // Initialize the database
        mLuckWeeklyQuestStageRepository.saveAndFlush(mLuckWeeklyQuestStage);

        // Get all the mLuckWeeklyQuestStageList where stageUnlockPattern in DEFAULT_STAGE_UNLOCK_PATTERN or UPDATED_STAGE_UNLOCK_PATTERN
        defaultMLuckWeeklyQuestStageShouldBeFound("stageUnlockPattern.in=" + DEFAULT_STAGE_UNLOCK_PATTERN + "," + UPDATED_STAGE_UNLOCK_PATTERN);

        // Get all the mLuckWeeklyQuestStageList where stageUnlockPattern equals to UPDATED_STAGE_UNLOCK_PATTERN
        defaultMLuckWeeklyQuestStageShouldNotBeFound("stageUnlockPattern.in=" + UPDATED_STAGE_UNLOCK_PATTERN);
    }

    @Test
    @Transactional
    public void getAllMLuckWeeklyQuestStagesByStageUnlockPatternIsNullOrNotNull() throws Exception {
        // Initialize the database
        mLuckWeeklyQuestStageRepository.saveAndFlush(mLuckWeeklyQuestStage);

        // Get all the mLuckWeeklyQuestStageList where stageUnlockPattern is not null
        defaultMLuckWeeklyQuestStageShouldBeFound("stageUnlockPattern.specified=true");

        // Get all the mLuckWeeklyQuestStageList where stageUnlockPattern is null
        defaultMLuckWeeklyQuestStageShouldNotBeFound("stageUnlockPattern.specified=false");
    }

    @Test
    @Transactional
    public void getAllMLuckWeeklyQuestStagesByStageUnlockPatternIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mLuckWeeklyQuestStageRepository.saveAndFlush(mLuckWeeklyQuestStage);

        // Get all the mLuckWeeklyQuestStageList where stageUnlockPattern greater than or equals to DEFAULT_STAGE_UNLOCK_PATTERN
        defaultMLuckWeeklyQuestStageShouldBeFound("stageUnlockPattern.greaterOrEqualThan=" + DEFAULT_STAGE_UNLOCK_PATTERN);

        // Get all the mLuckWeeklyQuestStageList where stageUnlockPattern greater than or equals to UPDATED_STAGE_UNLOCK_PATTERN
        defaultMLuckWeeklyQuestStageShouldNotBeFound("stageUnlockPattern.greaterOrEqualThan=" + UPDATED_STAGE_UNLOCK_PATTERN);
    }

    @Test
    @Transactional
    public void getAllMLuckWeeklyQuestStagesByStageUnlockPatternIsLessThanSomething() throws Exception {
        // Initialize the database
        mLuckWeeklyQuestStageRepository.saveAndFlush(mLuckWeeklyQuestStage);

        // Get all the mLuckWeeklyQuestStageList where stageUnlockPattern less than or equals to DEFAULT_STAGE_UNLOCK_PATTERN
        defaultMLuckWeeklyQuestStageShouldNotBeFound("stageUnlockPattern.lessThan=" + DEFAULT_STAGE_UNLOCK_PATTERN);

        // Get all the mLuckWeeklyQuestStageList where stageUnlockPattern less than or equals to UPDATED_STAGE_UNLOCK_PATTERN
        defaultMLuckWeeklyQuestStageShouldBeFound("stageUnlockPattern.lessThan=" + UPDATED_STAGE_UNLOCK_PATTERN);
    }


    @Test
    @Transactional
    public void getAllMLuckWeeklyQuestStagesByStoryOnlyIsEqualToSomething() throws Exception {
        // Initialize the database
        mLuckWeeklyQuestStageRepository.saveAndFlush(mLuckWeeklyQuestStage);

        // Get all the mLuckWeeklyQuestStageList where storyOnly equals to DEFAULT_STORY_ONLY
        defaultMLuckWeeklyQuestStageShouldBeFound("storyOnly.equals=" + DEFAULT_STORY_ONLY);

        // Get all the mLuckWeeklyQuestStageList where storyOnly equals to UPDATED_STORY_ONLY
        defaultMLuckWeeklyQuestStageShouldNotBeFound("storyOnly.equals=" + UPDATED_STORY_ONLY);
    }

    @Test
    @Transactional
    public void getAllMLuckWeeklyQuestStagesByStoryOnlyIsInShouldWork() throws Exception {
        // Initialize the database
        mLuckWeeklyQuestStageRepository.saveAndFlush(mLuckWeeklyQuestStage);

        // Get all the mLuckWeeklyQuestStageList where storyOnly in DEFAULT_STORY_ONLY or UPDATED_STORY_ONLY
        defaultMLuckWeeklyQuestStageShouldBeFound("storyOnly.in=" + DEFAULT_STORY_ONLY + "," + UPDATED_STORY_ONLY);

        // Get all the mLuckWeeklyQuestStageList where storyOnly equals to UPDATED_STORY_ONLY
        defaultMLuckWeeklyQuestStageShouldNotBeFound("storyOnly.in=" + UPDATED_STORY_ONLY);
    }

    @Test
    @Transactional
    public void getAllMLuckWeeklyQuestStagesByStoryOnlyIsNullOrNotNull() throws Exception {
        // Initialize the database
        mLuckWeeklyQuestStageRepository.saveAndFlush(mLuckWeeklyQuestStage);

        // Get all the mLuckWeeklyQuestStageList where storyOnly is not null
        defaultMLuckWeeklyQuestStageShouldBeFound("storyOnly.specified=true");

        // Get all the mLuckWeeklyQuestStageList where storyOnly is null
        defaultMLuckWeeklyQuestStageShouldNotBeFound("storyOnly.specified=false");
    }

    @Test
    @Transactional
    public void getAllMLuckWeeklyQuestStagesByStoryOnlyIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mLuckWeeklyQuestStageRepository.saveAndFlush(mLuckWeeklyQuestStage);

        // Get all the mLuckWeeklyQuestStageList where storyOnly greater than or equals to DEFAULT_STORY_ONLY
        defaultMLuckWeeklyQuestStageShouldBeFound("storyOnly.greaterOrEqualThan=" + DEFAULT_STORY_ONLY);

        // Get all the mLuckWeeklyQuestStageList where storyOnly greater than or equals to UPDATED_STORY_ONLY
        defaultMLuckWeeklyQuestStageShouldNotBeFound("storyOnly.greaterOrEqualThan=" + UPDATED_STORY_ONLY);
    }

    @Test
    @Transactional
    public void getAllMLuckWeeklyQuestStagesByStoryOnlyIsLessThanSomething() throws Exception {
        // Initialize the database
        mLuckWeeklyQuestStageRepository.saveAndFlush(mLuckWeeklyQuestStage);

        // Get all the mLuckWeeklyQuestStageList where storyOnly less than or equals to DEFAULT_STORY_ONLY
        defaultMLuckWeeklyQuestStageShouldNotBeFound("storyOnly.lessThan=" + DEFAULT_STORY_ONLY);

        // Get all the mLuckWeeklyQuestStageList where storyOnly less than or equals to UPDATED_STORY_ONLY
        defaultMLuckWeeklyQuestStageShouldBeFound("storyOnly.lessThan=" + UPDATED_STORY_ONLY);
    }


    @Test
    @Transactional
    public void getAllMLuckWeeklyQuestStagesByFirstHalfNpcDeckIdIsEqualToSomething() throws Exception {
        // Initialize the database
        mLuckWeeklyQuestStageRepository.saveAndFlush(mLuckWeeklyQuestStage);

        // Get all the mLuckWeeklyQuestStageList where firstHalfNpcDeckId equals to DEFAULT_FIRST_HALF_NPC_DECK_ID
        defaultMLuckWeeklyQuestStageShouldBeFound("firstHalfNpcDeckId.equals=" + DEFAULT_FIRST_HALF_NPC_DECK_ID);

        // Get all the mLuckWeeklyQuestStageList where firstHalfNpcDeckId equals to UPDATED_FIRST_HALF_NPC_DECK_ID
        defaultMLuckWeeklyQuestStageShouldNotBeFound("firstHalfNpcDeckId.equals=" + UPDATED_FIRST_HALF_NPC_DECK_ID);
    }

    @Test
    @Transactional
    public void getAllMLuckWeeklyQuestStagesByFirstHalfNpcDeckIdIsInShouldWork() throws Exception {
        // Initialize the database
        mLuckWeeklyQuestStageRepository.saveAndFlush(mLuckWeeklyQuestStage);

        // Get all the mLuckWeeklyQuestStageList where firstHalfNpcDeckId in DEFAULT_FIRST_HALF_NPC_DECK_ID or UPDATED_FIRST_HALF_NPC_DECK_ID
        defaultMLuckWeeklyQuestStageShouldBeFound("firstHalfNpcDeckId.in=" + DEFAULT_FIRST_HALF_NPC_DECK_ID + "," + UPDATED_FIRST_HALF_NPC_DECK_ID);

        // Get all the mLuckWeeklyQuestStageList where firstHalfNpcDeckId equals to UPDATED_FIRST_HALF_NPC_DECK_ID
        defaultMLuckWeeklyQuestStageShouldNotBeFound("firstHalfNpcDeckId.in=" + UPDATED_FIRST_HALF_NPC_DECK_ID);
    }

    @Test
    @Transactional
    public void getAllMLuckWeeklyQuestStagesByFirstHalfNpcDeckIdIsNullOrNotNull() throws Exception {
        // Initialize the database
        mLuckWeeklyQuestStageRepository.saveAndFlush(mLuckWeeklyQuestStage);

        // Get all the mLuckWeeklyQuestStageList where firstHalfNpcDeckId is not null
        defaultMLuckWeeklyQuestStageShouldBeFound("firstHalfNpcDeckId.specified=true");

        // Get all the mLuckWeeklyQuestStageList where firstHalfNpcDeckId is null
        defaultMLuckWeeklyQuestStageShouldNotBeFound("firstHalfNpcDeckId.specified=false");
    }

    @Test
    @Transactional
    public void getAllMLuckWeeklyQuestStagesByFirstHalfNpcDeckIdIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mLuckWeeklyQuestStageRepository.saveAndFlush(mLuckWeeklyQuestStage);

        // Get all the mLuckWeeklyQuestStageList where firstHalfNpcDeckId greater than or equals to DEFAULT_FIRST_HALF_NPC_DECK_ID
        defaultMLuckWeeklyQuestStageShouldBeFound("firstHalfNpcDeckId.greaterOrEqualThan=" + DEFAULT_FIRST_HALF_NPC_DECK_ID);

        // Get all the mLuckWeeklyQuestStageList where firstHalfNpcDeckId greater than or equals to UPDATED_FIRST_HALF_NPC_DECK_ID
        defaultMLuckWeeklyQuestStageShouldNotBeFound("firstHalfNpcDeckId.greaterOrEqualThan=" + UPDATED_FIRST_HALF_NPC_DECK_ID);
    }

    @Test
    @Transactional
    public void getAllMLuckWeeklyQuestStagesByFirstHalfNpcDeckIdIsLessThanSomething() throws Exception {
        // Initialize the database
        mLuckWeeklyQuestStageRepository.saveAndFlush(mLuckWeeklyQuestStage);

        // Get all the mLuckWeeklyQuestStageList where firstHalfNpcDeckId less than or equals to DEFAULT_FIRST_HALF_NPC_DECK_ID
        defaultMLuckWeeklyQuestStageShouldNotBeFound("firstHalfNpcDeckId.lessThan=" + DEFAULT_FIRST_HALF_NPC_DECK_ID);

        // Get all the mLuckWeeklyQuestStageList where firstHalfNpcDeckId less than or equals to UPDATED_FIRST_HALF_NPC_DECK_ID
        defaultMLuckWeeklyQuestStageShouldBeFound("firstHalfNpcDeckId.lessThan=" + UPDATED_FIRST_HALF_NPC_DECK_ID);
    }


    @Test
    @Transactional
    public void getAllMLuckWeeklyQuestStagesByFirstHalfEnvironmentIdIsEqualToSomething() throws Exception {
        // Initialize the database
        mLuckWeeklyQuestStageRepository.saveAndFlush(mLuckWeeklyQuestStage);

        // Get all the mLuckWeeklyQuestStageList where firstHalfEnvironmentId equals to DEFAULT_FIRST_HALF_ENVIRONMENT_ID
        defaultMLuckWeeklyQuestStageShouldBeFound("firstHalfEnvironmentId.equals=" + DEFAULT_FIRST_HALF_ENVIRONMENT_ID);

        // Get all the mLuckWeeklyQuestStageList where firstHalfEnvironmentId equals to UPDATED_FIRST_HALF_ENVIRONMENT_ID
        defaultMLuckWeeklyQuestStageShouldNotBeFound("firstHalfEnvironmentId.equals=" + UPDATED_FIRST_HALF_ENVIRONMENT_ID);
    }

    @Test
    @Transactional
    public void getAllMLuckWeeklyQuestStagesByFirstHalfEnvironmentIdIsInShouldWork() throws Exception {
        // Initialize the database
        mLuckWeeklyQuestStageRepository.saveAndFlush(mLuckWeeklyQuestStage);

        // Get all the mLuckWeeklyQuestStageList where firstHalfEnvironmentId in DEFAULT_FIRST_HALF_ENVIRONMENT_ID or UPDATED_FIRST_HALF_ENVIRONMENT_ID
        defaultMLuckWeeklyQuestStageShouldBeFound("firstHalfEnvironmentId.in=" + DEFAULT_FIRST_HALF_ENVIRONMENT_ID + "," + UPDATED_FIRST_HALF_ENVIRONMENT_ID);

        // Get all the mLuckWeeklyQuestStageList where firstHalfEnvironmentId equals to UPDATED_FIRST_HALF_ENVIRONMENT_ID
        defaultMLuckWeeklyQuestStageShouldNotBeFound("firstHalfEnvironmentId.in=" + UPDATED_FIRST_HALF_ENVIRONMENT_ID);
    }

    @Test
    @Transactional
    public void getAllMLuckWeeklyQuestStagesByFirstHalfEnvironmentIdIsNullOrNotNull() throws Exception {
        // Initialize the database
        mLuckWeeklyQuestStageRepository.saveAndFlush(mLuckWeeklyQuestStage);

        // Get all the mLuckWeeklyQuestStageList where firstHalfEnvironmentId is not null
        defaultMLuckWeeklyQuestStageShouldBeFound("firstHalfEnvironmentId.specified=true");

        // Get all the mLuckWeeklyQuestStageList where firstHalfEnvironmentId is null
        defaultMLuckWeeklyQuestStageShouldNotBeFound("firstHalfEnvironmentId.specified=false");
    }

    @Test
    @Transactional
    public void getAllMLuckWeeklyQuestStagesByFirstHalfEnvironmentIdIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mLuckWeeklyQuestStageRepository.saveAndFlush(mLuckWeeklyQuestStage);

        // Get all the mLuckWeeklyQuestStageList where firstHalfEnvironmentId greater than or equals to DEFAULT_FIRST_HALF_ENVIRONMENT_ID
        defaultMLuckWeeklyQuestStageShouldBeFound("firstHalfEnvironmentId.greaterOrEqualThan=" + DEFAULT_FIRST_HALF_ENVIRONMENT_ID);

        // Get all the mLuckWeeklyQuestStageList where firstHalfEnvironmentId greater than or equals to UPDATED_FIRST_HALF_ENVIRONMENT_ID
        defaultMLuckWeeklyQuestStageShouldNotBeFound("firstHalfEnvironmentId.greaterOrEqualThan=" + UPDATED_FIRST_HALF_ENVIRONMENT_ID);
    }

    @Test
    @Transactional
    public void getAllMLuckWeeklyQuestStagesByFirstHalfEnvironmentIdIsLessThanSomething() throws Exception {
        // Initialize the database
        mLuckWeeklyQuestStageRepository.saveAndFlush(mLuckWeeklyQuestStage);

        // Get all the mLuckWeeklyQuestStageList where firstHalfEnvironmentId less than or equals to DEFAULT_FIRST_HALF_ENVIRONMENT_ID
        defaultMLuckWeeklyQuestStageShouldNotBeFound("firstHalfEnvironmentId.lessThan=" + DEFAULT_FIRST_HALF_ENVIRONMENT_ID);

        // Get all the mLuckWeeklyQuestStageList where firstHalfEnvironmentId less than or equals to UPDATED_FIRST_HALF_ENVIRONMENT_ID
        defaultMLuckWeeklyQuestStageShouldBeFound("firstHalfEnvironmentId.lessThan=" + UPDATED_FIRST_HALF_ENVIRONMENT_ID);
    }


    @Test
    @Transactional
    public void getAllMLuckWeeklyQuestStagesBySecondHalfNpcDeckIdIsEqualToSomething() throws Exception {
        // Initialize the database
        mLuckWeeklyQuestStageRepository.saveAndFlush(mLuckWeeklyQuestStage);

        // Get all the mLuckWeeklyQuestStageList where secondHalfNpcDeckId equals to DEFAULT_SECOND_HALF_NPC_DECK_ID
        defaultMLuckWeeklyQuestStageShouldBeFound("secondHalfNpcDeckId.equals=" + DEFAULT_SECOND_HALF_NPC_DECK_ID);

        // Get all the mLuckWeeklyQuestStageList where secondHalfNpcDeckId equals to UPDATED_SECOND_HALF_NPC_DECK_ID
        defaultMLuckWeeklyQuestStageShouldNotBeFound("secondHalfNpcDeckId.equals=" + UPDATED_SECOND_HALF_NPC_DECK_ID);
    }

    @Test
    @Transactional
    public void getAllMLuckWeeklyQuestStagesBySecondHalfNpcDeckIdIsInShouldWork() throws Exception {
        // Initialize the database
        mLuckWeeklyQuestStageRepository.saveAndFlush(mLuckWeeklyQuestStage);

        // Get all the mLuckWeeklyQuestStageList where secondHalfNpcDeckId in DEFAULT_SECOND_HALF_NPC_DECK_ID or UPDATED_SECOND_HALF_NPC_DECK_ID
        defaultMLuckWeeklyQuestStageShouldBeFound("secondHalfNpcDeckId.in=" + DEFAULT_SECOND_HALF_NPC_DECK_ID + "," + UPDATED_SECOND_HALF_NPC_DECK_ID);

        // Get all the mLuckWeeklyQuestStageList where secondHalfNpcDeckId equals to UPDATED_SECOND_HALF_NPC_DECK_ID
        defaultMLuckWeeklyQuestStageShouldNotBeFound("secondHalfNpcDeckId.in=" + UPDATED_SECOND_HALF_NPC_DECK_ID);
    }

    @Test
    @Transactional
    public void getAllMLuckWeeklyQuestStagesBySecondHalfNpcDeckIdIsNullOrNotNull() throws Exception {
        // Initialize the database
        mLuckWeeklyQuestStageRepository.saveAndFlush(mLuckWeeklyQuestStage);

        // Get all the mLuckWeeklyQuestStageList where secondHalfNpcDeckId is not null
        defaultMLuckWeeklyQuestStageShouldBeFound("secondHalfNpcDeckId.specified=true");

        // Get all the mLuckWeeklyQuestStageList where secondHalfNpcDeckId is null
        defaultMLuckWeeklyQuestStageShouldNotBeFound("secondHalfNpcDeckId.specified=false");
    }

    @Test
    @Transactional
    public void getAllMLuckWeeklyQuestStagesBySecondHalfNpcDeckIdIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mLuckWeeklyQuestStageRepository.saveAndFlush(mLuckWeeklyQuestStage);

        // Get all the mLuckWeeklyQuestStageList where secondHalfNpcDeckId greater than or equals to DEFAULT_SECOND_HALF_NPC_DECK_ID
        defaultMLuckWeeklyQuestStageShouldBeFound("secondHalfNpcDeckId.greaterOrEqualThan=" + DEFAULT_SECOND_HALF_NPC_DECK_ID);

        // Get all the mLuckWeeklyQuestStageList where secondHalfNpcDeckId greater than or equals to UPDATED_SECOND_HALF_NPC_DECK_ID
        defaultMLuckWeeklyQuestStageShouldNotBeFound("secondHalfNpcDeckId.greaterOrEqualThan=" + UPDATED_SECOND_HALF_NPC_DECK_ID);
    }

    @Test
    @Transactional
    public void getAllMLuckWeeklyQuestStagesBySecondHalfNpcDeckIdIsLessThanSomething() throws Exception {
        // Initialize the database
        mLuckWeeklyQuestStageRepository.saveAndFlush(mLuckWeeklyQuestStage);

        // Get all the mLuckWeeklyQuestStageList where secondHalfNpcDeckId less than or equals to DEFAULT_SECOND_HALF_NPC_DECK_ID
        defaultMLuckWeeklyQuestStageShouldNotBeFound("secondHalfNpcDeckId.lessThan=" + DEFAULT_SECOND_HALF_NPC_DECK_ID);

        // Get all the mLuckWeeklyQuestStageList where secondHalfNpcDeckId less than or equals to UPDATED_SECOND_HALF_NPC_DECK_ID
        defaultMLuckWeeklyQuestStageShouldBeFound("secondHalfNpcDeckId.lessThan=" + UPDATED_SECOND_HALF_NPC_DECK_ID);
    }


    @Test
    @Transactional
    public void getAllMLuckWeeklyQuestStagesBySecondHalfEnvironmentIdIsEqualToSomething() throws Exception {
        // Initialize the database
        mLuckWeeklyQuestStageRepository.saveAndFlush(mLuckWeeklyQuestStage);

        // Get all the mLuckWeeklyQuestStageList where secondHalfEnvironmentId equals to DEFAULT_SECOND_HALF_ENVIRONMENT_ID
        defaultMLuckWeeklyQuestStageShouldBeFound("secondHalfEnvironmentId.equals=" + DEFAULT_SECOND_HALF_ENVIRONMENT_ID);

        // Get all the mLuckWeeklyQuestStageList where secondHalfEnvironmentId equals to UPDATED_SECOND_HALF_ENVIRONMENT_ID
        defaultMLuckWeeklyQuestStageShouldNotBeFound("secondHalfEnvironmentId.equals=" + UPDATED_SECOND_HALF_ENVIRONMENT_ID);
    }

    @Test
    @Transactional
    public void getAllMLuckWeeklyQuestStagesBySecondHalfEnvironmentIdIsInShouldWork() throws Exception {
        // Initialize the database
        mLuckWeeklyQuestStageRepository.saveAndFlush(mLuckWeeklyQuestStage);

        // Get all the mLuckWeeklyQuestStageList where secondHalfEnvironmentId in DEFAULT_SECOND_HALF_ENVIRONMENT_ID or UPDATED_SECOND_HALF_ENVIRONMENT_ID
        defaultMLuckWeeklyQuestStageShouldBeFound("secondHalfEnvironmentId.in=" + DEFAULT_SECOND_HALF_ENVIRONMENT_ID + "," + UPDATED_SECOND_HALF_ENVIRONMENT_ID);

        // Get all the mLuckWeeklyQuestStageList where secondHalfEnvironmentId equals to UPDATED_SECOND_HALF_ENVIRONMENT_ID
        defaultMLuckWeeklyQuestStageShouldNotBeFound("secondHalfEnvironmentId.in=" + UPDATED_SECOND_HALF_ENVIRONMENT_ID);
    }

    @Test
    @Transactional
    public void getAllMLuckWeeklyQuestStagesBySecondHalfEnvironmentIdIsNullOrNotNull() throws Exception {
        // Initialize the database
        mLuckWeeklyQuestStageRepository.saveAndFlush(mLuckWeeklyQuestStage);

        // Get all the mLuckWeeklyQuestStageList where secondHalfEnvironmentId is not null
        defaultMLuckWeeklyQuestStageShouldBeFound("secondHalfEnvironmentId.specified=true");

        // Get all the mLuckWeeklyQuestStageList where secondHalfEnvironmentId is null
        defaultMLuckWeeklyQuestStageShouldNotBeFound("secondHalfEnvironmentId.specified=false");
    }

    @Test
    @Transactional
    public void getAllMLuckWeeklyQuestStagesBySecondHalfEnvironmentIdIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mLuckWeeklyQuestStageRepository.saveAndFlush(mLuckWeeklyQuestStage);

        // Get all the mLuckWeeklyQuestStageList where secondHalfEnvironmentId greater than or equals to DEFAULT_SECOND_HALF_ENVIRONMENT_ID
        defaultMLuckWeeklyQuestStageShouldBeFound("secondHalfEnvironmentId.greaterOrEqualThan=" + DEFAULT_SECOND_HALF_ENVIRONMENT_ID);

        // Get all the mLuckWeeklyQuestStageList where secondHalfEnvironmentId greater than or equals to UPDATED_SECOND_HALF_ENVIRONMENT_ID
        defaultMLuckWeeklyQuestStageShouldNotBeFound("secondHalfEnvironmentId.greaterOrEqualThan=" + UPDATED_SECOND_HALF_ENVIRONMENT_ID);
    }

    @Test
    @Transactional
    public void getAllMLuckWeeklyQuestStagesBySecondHalfEnvironmentIdIsLessThanSomething() throws Exception {
        // Initialize the database
        mLuckWeeklyQuestStageRepository.saveAndFlush(mLuckWeeklyQuestStage);

        // Get all the mLuckWeeklyQuestStageList where secondHalfEnvironmentId less than or equals to DEFAULT_SECOND_HALF_ENVIRONMENT_ID
        defaultMLuckWeeklyQuestStageShouldNotBeFound("secondHalfEnvironmentId.lessThan=" + DEFAULT_SECOND_HALF_ENVIRONMENT_ID);

        // Get all the mLuckWeeklyQuestStageList where secondHalfEnvironmentId less than or equals to UPDATED_SECOND_HALF_ENVIRONMENT_ID
        defaultMLuckWeeklyQuestStageShouldBeFound("secondHalfEnvironmentId.lessThan=" + UPDATED_SECOND_HALF_ENVIRONMENT_ID);
    }


    @Test
    @Transactional
    public void getAllMLuckWeeklyQuestStagesByExtraFirstHalfNpcDeckIdIsEqualToSomething() throws Exception {
        // Initialize the database
        mLuckWeeklyQuestStageRepository.saveAndFlush(mLuckWeeklyQuestStage);

        // Get all the mLuckWeeklyQuestStageList where extraFirstHalfNpcDeckId equals to DEFAULT_EXTRA_FIRST_HALF_NPC_DECK_ID
        defaultMLuckWeeklyQuestStageShouldBeFound("extraFirstHalfNpcDeckId.equals=" + DEFAULT_EXTRA_FIRST_HALF_NPC_DECK_ID);

        // Get all the mLuckWeeklyQuestStageList where extraFirstHalfNpcDeckId equals to UPDATED_EXTRA_FIRST_HALF_NPC_DECK_ID
        defaultMLuckWeeklyQuestStageShouldNotBeFound("extraFirstHalfNpcDeckId.equals=" + UPDATED_EXTRA_FIRST_HALF_NPC_DECK_ID);
    }

    @Test
    @Transactional
    public void getAllMLuckWeeklyQuestStagesByExtraFirstHalfNpcDeckIdIsInShouldWork() throws Exception {
        // Initialize the database
        mLuckWeeklyQuestStageRepository.saveAndFlush(mLuckWeeklyQuestStage);

        // Get all the mLuckWeeklyQuestStageList where extraFirstHalfNpcDeckId in DEFAULT_EXTRA_FIRST_HALF_NPC_DECK_ID or UPDATED_EXTRA_FIRST_HALF_NPC_DECK_ID
        defaultMLuckWeeklyQuestStageShouldBeFound("extraFirstHalfNpcDeckId.in=" + DEFAULT_EXTRA_FIRST_HALF_NPC_DECK_ID + "," + UPDATED_EXTRA_FIRST_HALF_NPC_DECK_ID);

        // Get all the mLuckWeeklyQuestStageList where extraFirstHalfNpcDeckId equals to UPDATED_EXTRA_FIRST_HALF_NPC_DECK_ID
        defaultMLuckWeeklyQuestStageShouldNotBeFound("extraFirstHalfNpcDeckId.in=" + UPDATED_EXTRA_FIRST_HALF_NPC_DECK_ID);
    }

    @Test
    @Transactional
    public void getAllMLuckWeeklyQuestStagesByExtraFirstHalfNpcDeckIdIsNullOrNotNull() throws Exception {
        // Initialize the database
        mLuckWeeklyQuestStageRepository.saveAndFlush(mLuckWeeklyQuestStage);

        // Get all the mLuckWeeklyQuestStageList where extraFirstHalfNpcDeckId is not null
        defaultMLuckWeeklyQuestStageShouldBeFound("extraFirstHalfNpcDeckId.specified=true");

        // Get all the mLuckWeeklyQuestStageList where extraFirstHalfNpcDeckId is null
        defaultMLuckWeeklyQuestStageShouldNotBeFound("extraFirstHalfNpcDeckId.specified=false");
    }

    @Test
    @Transactional
    public void getAllMLuckWeeklyQuestStagesByExtraFirstHalfNpcDeckIdIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mLuckWeeklyQuestStageRepository.saveAndFlush(mLuckWeeklyQuestStage);

        // Get all the mLuckWeeklyQuestStageList where extraFirstHalfNpcDeckId greater than or equals to DEFAULT_EXTRA_FIRST_HALF_NPC_DECK_ID
        defaultMLuckWeeklyQuestStageShouldBeFound("extraFirstHalfNpcDeckId.greaterOrEqualThan=" + DEFAULT_EXTRA_FIRST_HALF_NPC_DECK_ID);

        // Get all the mLuckWeeklyQuestStageList where extraFirstHalfNpcDeckId greater than or equals to UPDATED_EXTRA_FIRST_HALF_NPC_DECK_ID
        defaultMLuckWeeklyQuestStageShouldNotBeFound("extraFirstHalfNpcDeckId.greaterOrEqualThan=" + UPDATED_EXTRA_FIRST_HALF_NPC_DECK_ID);
    }

    @Test
    @Transactional
    public void getAllMLuckWeeklyQuestStagesByExtraFirstHalfNpcDeckIdIsLessThanSomething() throws Exception {
        // Initialize the database
        mLuckWeeklyQuestStageRepository.saveAndFlush(mLuckWeeklyQuestStage);

        // Get all the mLuckWeeklyQuestStageList where extraFirstHalfNpcDeckId less than or equals to DEFAULT_EXTRA_FIRST_HALF_NPC_DECK_ID
        defaultMLuckWeeklyQuestStageShouldNotBeFound("extraFirstHalfNpcDeckId.lessThan=" + DEFAULT_EXTRA_FIRST_HALF_NPC_DECK_ID);

        // Get all the mLuckWeeklyQuestStageList where extraFirstHalfNpcDeckId less than or equals to UPDATED_EXTRA_FIRST_HALF_NPC_DECK_ID
        defaultMLuckWeeklyQuestStageShouldBeFound("extraFirstHalfNpcDeckId.lessThan=" + UPDATED_EXTRA_FIRST_HALF_NPC_DECK_ID);
    }


    @Test
    @Transactional
    public void getAllMLuckWeeklyQuestStagesByExtraSecondHalfNpcDeckIdIsEqualToSomething() throws Exception {
        // Initialize the database
        mLuckWeeklyQuestStageRepository.saveAndFlush(mLuckWeeklyQuestStage);

        // Get all the mLuckWeeklyQuestStageList where extraSecondHalfNpcDeckId equals to DEFAULT_EXTRA_SECOND_HALF_NPC_DECK_ID
        defaultMLuckWeeklyQuestStageShouldBeFound("extraSecondHalfNpcDeckId.equals=" + DEFAULT_EXTRA_SECOND_HALF_NPC_DECK_ID);

        // Get all the mLuckWeeklyQuestStageList where extraSecondHalfNpcDeckId equals to UPDATED_EXTRA_SECOND_HALF_NPC_DECK_ID
        defaultMLuckWeeklyQuestStageShouldNotBeFound("extraSecondHalfNpcDeckId.equals=" + UPDATED_EXTRA_SECOND_HALF_NPC_DECK_ID);
    }

    @Test
    @Transactional
    public void getAllMLuckWeeklyQuestStagesByExtraSecondHalfNpcDeckIdIsInShouldWork() throws Exception {
        // Initialize the database
        mLuckWeeklyQuestStageRepository.saveAndFlush(mLuckWeeklyQuestStage);

        // Get all the mLuckWeeklyQuestStageList where extraSecondHalfNpcDeckId in DEFAULT_EXTRA_SECOND_HALF_NPC_DECK_ID or UPDATED_EXTRA_SECOND_HALF_NPC_DECK_ID
        defaultMLuckWeeklyQuestStageShouldBeFound("extraSecondHalfNpcDeckId.in=" + DEFAULT_EXTRA_SECOND_HALF_NPC_DECK_ID + "," + UPDATED_EXTRA_SECOND_HALF_NPC_DECK_ID);

        // Get all the mLuckWeeklyQuestStageList where extraSecondHalfNpcDeckId equals to UPDATED_EXTRA_SECOND_HALF_NPC_DECK_ID
        defaultMLuckWeeklyQuestStageShouldNotBeFound("extraSecondHalfNpcDeckId.in=" + UPDATED_EXTRA_SECOND_HALF_NPC_DECK_ID);
    }

    @Test
    @Transactional
    public void getAllMLuckWeeklyQuestStagesByExtraSecondHalfNpcDeckIdIsNullOrNotNull() throws Exception {
        // Initialize the database
        mLuckWeeklyQuestStageRepository.saveAndFlush(mLuckWeeklyQuestStage);

        // Get all the mLuckWeeklyQuestStageList where extraSecondHalfNpcDeckId is not null
        defaultMLuckWeeklyQuestStageShouldBeFound("extraSecondHalfNpcDeckId.specified=true");

        // Get all the mLuckWeeklyQuestStageList where extraSecondHalfNpcDeckId is null
        defaultMLuckWeeklyQuestStageShouldNotBeFound("extraSecondHalfNpcDeckId.specified=false");
    }

    @Test
    @Transactional
    public void getAllMLuckWeeklyQuestStagesByExtraSecondHalfNpcDeckIdIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mLuckWeeklyQuestStageRepository.saveAndFlush(mLuckWeeklyQuestStage);

        // Get all the mLuckWeeklyQuestStageList where extraSecondHalfNpcDeckId greater than or equals to DEFAULT_EXTRA_SECOND_HALF_NPC_DECK_ID
        defaultMLuckWeeklyQuestStageShouldBeFound("extraSecondHalfNpcDeckId.greaterOrEqualThan=" + DEFAULT_EXTRA_SECOND_HALF_NPC_DECK_ID);

        // Get all the mLuckWeeklyQuestStageList where extraSecondHalfNpcDeckId greater than or equals to UPDATED_EXTRA_SECOND_HALF_NPC_DECK_ID
        defaultMLuckWeeklyQuestStageShouldNotBeFound("extraSecondHalfNpcDeckId.greaterOrEqualThan=" + UPDATED_EXTRA_SECOND_HALF_NPC_DECK_ID);
    }

    @Test
    @Transactional
    public void getAllMLuckWeeklyQuestStagesByExtraSecondHalfNpcDeckIdIsLessThanSomething() throws Exception {
        // Initialize the database
        mLuckWeeklyQuestStageRepository.saveAndFlush(mLuckWeeklyQuestStage);

        // Get all the mLuckWeeklyQuestStageList where extraSecondHalfNpcDeckId less than or equals to DEFAULT_EXTRA_SECOND_HALF_NPC_DECK_ID
        defaultMLuckWeeklyQuestStageShouldNotBeFound("extraSecondHalfNpcDeckId.lessThan=" + DEFAULT_EXTRA_SECOND_HALF_NPC_DECK_ID);

        // Get all the mLuckWeeklyQuestStageList where extraSecondHalfNpcDeckId less than or equals to UPDATED_EXTRA_SECOND_HALF_NPC_DECK_ID
        defaultMLuckWeeklyQuestStageShouldBeFound("extraSecondHalfNpcDeckId.lessThan=" + UPDATED_EXTRA_SECOND_HALF_NPC_DECK_ID);
    }


    @Test
    @Transactional
    public void getAllMLuckWeeklyQuestStagesByConsumeApIsEqualToSomething() throws Exception {
        // Initialize the database
        mLuckWeeklyQuestStageRepository.saveAndFlush(mLuckWeeklyQuestStage);

        // Get all the mLuckWeeklyQuestStageList where consumeAp equals to DEFAULT_CONSUME_AP
        defaultMLuckWeeklyQuestStageShouldBeFound("consumeAp.equals=" + DEFAULT_CONSUME_AP);

        // Get all the mLuckWeeklyQuestStageList where consumeAp equals to UPDATED_CONSUME_AP
        defaultMLuckWeeklyQuestStageShouldNotBeFound("consumeAp.equals=" + UPDATED_CONSUME_AP);
    }

    @Test
    @Transactional
    public void getAllMLuckWeeklyQuestStagesByConsumeApIsInShouldWork() throws Exception {
        // Initialize the database
        mLuckWeeklyQuestStageRepository.saveAndFlush(mLuckWeeklyQuestStage);

        // Get all the mLuckWeeklyQuestStageList where consumeAp in DEFAULT_CONSUME_AP or UPDATED_CONSUME_AP
        defaultMLuckWeeklyQuestStageShouldBeFound("consumeAp.in=" + DEFAULT_CONSUME_AP + "," + UPDATED_CONSUME_AP);

        // Get all the mLuckWeeklyQuestStageList where consumeAp equals to UPDATED_CONSUME_AP
        defaultMLuckWeeklyQuestStageShouldNotBeFound("consumeAp.in=" + UPDATED_CONSUME_AP);
    }

    @Test
    @Transactional
    public void getAllMLuckWeeklyQuestStagesByConsumeApIsNullOrNotNull() throws Exception {
        // Initialize the database
        mLuckWeeklyQuestStageRepository.saveAndFlush(mLuckWeeklyQuestStage);

        // Get all the mLuckWeeklyQuestStageList where consumeAp is not null
        defaultMLuckWeeklyQuestStageShouldBeFound("consumeAp.specified=true");

        // Get all the mLuckWeeklyQuestStageList where consumeAp is null
        defaultMLuckWeeklyQuestStageShouldNotBeFound("consumeAp.specified=false");
    }

    @Test
    @Transactional
    public void getAllMLuckWeeklyQuestStagesByConsumeApIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mLuckWeeklyQuestStageRepository.saveAndFlush(mLuckWeeklyQuestStage);

        // Get all the mLuckWeeklyQuestStageList where consumeAp greater than or equals to DEFAULT_CONSUME_AP
        defaultMLuckWeeklyQuestStageShouldBeFound("consumeAp.greaterOrEqualThan=" + DEFAULT_CONSUME_AP);

        // Get all the mLuckWeeklyQuestStageList where consumeAp greater than or equals to UPDATED_CONSUME_AP
        defaultMLuckWeeklyQuestStageShouldNotBeFound("consumeAp.greaterOrEqualThan=" + UPDATED_CONSUME_AP);
    }

    @Test
    @Transactional
    public void getAllMLuckWeeklyQuestStagesByConsumeApIsLessThanSomething() throws Exception {
        // Initialize the database
        mLuckWeeklyQuestStageRepository.saveAndFlush(mLuckWeeklyQuestStage);

        // Get all the mLuckWeeklyQuestStageList where consumeAp less than or equals to DEFAULT_CONSUME_AP
        defaultMLuckWeeklyQuestStageShouldNotBeFound("consumeAp.lessThan=" + DEFAULT_CONSUME_AP);

        // Get all the mLuckWeeklyQuestStageList where consumeAp less than or equals to UPDATED_CONSUME_AP
        defaultMLuckWeeklyQuestStageShouldBeFound("consumeAp.lessThan=" + UPDATED_CONSUME_AP);
    }


    @Test
    @Transactional
    public void getAllMLuckWeeklyQuestStagesByKickOffTypeIsEqualToSomething() throws Exception {
        // Initialize the database
        mLuckWeeklyQuestStageRepository.saveAndFlush(mLuckWeeklyQuestStage);

        // Get all the mLuckWeeklyQuestStageList where kickOffType equals to DEFAULT_KICK_OFF_TYPE
        defaultMLuckWeeklyQuestStageShouldBeFound("kickOffType.equals=" + DEFAULT_KICK_OFF_TYPE);

        // Get all the mLuckWeeklyQuestStageList where kickOffType equals to UPDATED_KICK_OFF_TYPE
        defaultMLuckWeeklyQuestStageShouldNotBeFound("kickOffType.equals=" + UPDATED_KICK_OFF_TYPE);
    }

    @Test
    @Transactional
    public void getAllMLuckWeeklyQuestStagesByKickOffTypeIsInShouldWork() throws Exception {
        // Initialize the database
        mLuckWeeklyQuestStageRepository.saveAndFlush(mLuckWeeklyQuestStage);

        // Get all the mLuckWeeklyQuestStageList where kickOffType in DEFAULT_KICK_OFF_TYPE or UPDATED_KICK_OFF_TYPE
        defaultMLuckWeeklyQuestStageShouldBeFound("kickOffType.in=" + DEFAULT_KICK_OFF_TYPE + "," + UPDATED_KICK_OFF_TYPE);

        // Get all the mLuckWeeklyQuestStageList where kickOffType equals to UPDATED_KICK_OFF_TYPE
        defaultMLuckWeeklyQuestStageShouldNotBeFound("kickOffType.in=" + UPDATED_KICK_OFF_TYPE);
    }

    @Test
    @Transactional
    public void getAllMLuckWeeklyQuestStagesByKickOffTypeIsNullOrNotNull() throws Exception {
        // Initialize the database
        mLuckWeeklyQuestStageRepository.saveAndFlush(mLuckWeeklyQuestStage);

        // Get all the mLuckWeeklyQuestStageList where kickOffType is not null
        defaultMLuckWeeklyQuestStageShouldBeFound("kickOffType.specified=true");

        // Get all the mLuckWeeklyQuestStageList where kickOffType is null
        defaultMLuckWeeklyQuestStageShouldNotBeFound("kickOffType.specified=false");
    }

    @Test
    @Transactional
    public void getAllMLuckWeeklyQuestStagesByKickOffTypeIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mLuckWeeklyQuestStageRepository.saveAndFlush(mLuckWeeklyQuestStage);

        // Get all the mLuckWeeklyQuestStageList where kickOffType greater than or equals to DEFAULT_KICK_OFF_TYPE
        defaultMLuckWeeklyQuestStageShouldBeFound("kickOffType.greaterOrEqualThan=" + DEFAULT_KICK_OFF_TYPE);

        // Get all the mLuckWeeklyQuestStageList where kickOffType greater than or equals to UPDATED_KICK_OFF_TYPE
        defaultMLuckWeeklyQuestStageShouldNotBeFound("kickOffType.greaterOrEqualThan=" + UPDATED_KICK_OFF_TYPE);
    }

    @Test
    @Transactional
    public void getAllMLuckWeeklyQuestStagesByKickOffTypeIsLessThanSomething() throws Exception {
        // Initialize the database
        mLuckWeeklyQuestStageRepository.saveAndFlush(mLuckWeeklyQuestStage);

        // Get all the mLuckWeeklyQuestStageList where kickOffType less than or equals to DEFAULT_KICK_OFF_TYPE
        defaultMLuckWeeklyQuestStageShouldNotBeFound("kickOffType.lessThan=" + DEFAULT_KICK_OFF_TYPE);

        // Get all the mLuckWeeklyQuestStageList where kickOffType less than or equals to UPDATED_KICK_OFF_TYPE
        defaultMLuckWeeklyQuestStageShouldBeFound("kickOffType.lessThan=" + UPDATED_KICK_OFF_TYPE);
    }


    @Test
    @Transactional
    public void getAllMLuckWeeklyQuestStagesByMatchMinuteIsEqualToSomething() throws Exception {
        // Initialize the database
        mLuckWeeklyQuestStageRepository.saveAndFlush(mLuckWeeklyQuestStage);

        // Get all the mLuckWeeklyQuestStageList where matchMinute equals to DEFAULT_MATCH_MINUTE
        defaultMLuckWeeklyQuestStageShouldBeFound("matchMinute.equals=" + DEFAULT_MATCH_MINUTE);

        // Get all the mLuckWeeklyQuestStageList where matchMinute equals to UPDATED_MATCH_MINUTE
        defaultMLuckWeeklyQuestStageShouldNotBeFound("matchMinute.equals=" + UPDATED_MATCH_MINUTE);
    }

    @Test
    @Transactional
    public void getAllMLuckWeeklyQuestStagesByMatchMinuteIsInShouldWork() throws Exception {
        // Initialize the database
        mLuckWeeklyQuestStageRepository.saveAndFlush(mLuckWeeklyQuestStage);

        // Get all the mLuckWeeklyQuestStageList where matchMinute in DEFAULT_MATCH_MINUTE or UPDATED_MATCH_MINUTE
        defaultMLuckWeeklyQuestStageShouldBeFound("matchMinute.in=" + DEFAULT_MATCH_MINUTE + "," + UPDATED_MATCH_MINUTE);

        // Get all the mLuckWeeklyQuestStageList where matchMinute equals to UPDATED_MATCH_MINUTE
        defaultMLuckWeeklyQuestStageShouldNotBeFound("matchMinute.in=" + UPDATED_MATCH_MINUTE);
    }

    @Test
    @Transactional
    public void getAllMLuckWeeklyQuestStagesByMatchMinuteIsNullOrNotNull() throws Exception {
        // Initialize the database
        mLuckWeeklyQuestStageRepository.saveAndFlush(mLuckWeeklyQuestStage);

        // Get all the mLuckWeeklyQuestStageList where matchMinute is not null
        defaultMLuckWeeklyQuestStageShouldBeFound("matchMinute.specified=true");

        // Get all the mLuckWeeklyQuestStageList where matchMinute is null
        defaultMLuckWeeklyQuestStageShouldNotBeFound("matchMinute.specified=false");
    }

    @Test
    @Transactional
    public void getAllMLuckWeeklyQuestStagesByMatchMinuteIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mLuckWeeklyQuestStageRepository.saveAndFlush(mLuckWeeklyQuestStage);

        // Get all the mLuckWeeklyQuestStageList where matchMinute greater than or equals to DEFAULT_MATCH_MINUTE
        defaultMLuckWeeklyQuestStageShouldBeFound("matchMinute.greaterOrEqualThan=" + DEFAULT_MATCH_MINUTE);

        // Get all the mLuckWeeklyQuestStageList where matchMinute greater than or equals to UPDATED_MATCH_MINUTE
        defaultMLuckWeeklyQuestStageShouldNotBeFound("matchMinute.greaterOrEqualThan=" + UPDATED_MATCH_MINUTE);
    }

    @Test
    @Transactional
    public void getAllMLuckWeeklyQuestStagesByMatchMinuteIsLessThanSomething() throws Exception {
        // Initialize the database
        mLuckWeeklyQuestStageRepository.saveAndFlush(mLuckWeeklyQuestStage);

        // Get all the mLuckWeeklyQuestStageList where matchMinute less than or equals to DEFAULT_MATCH_MINUTE
        defaultMLuckWeeklyQuestStageShouldNotBeFound("matchMinute.lessThan=" + DEFAULT_MATCH_MINUTE);

        // Get all the mLuckWeeklyQuestStageList where matchMinute less than or equals to UPDATED_MATCH_MINUTE
        defaultMLuckWeeklyQuestStageShouldBeFound("matchMinute.lessThan=" + UPDATED_MATCH_MINUTE);
    }


    @Test
    @Transactional
    public void getAllMLuckWeeklyQuestStagesByEnableExtraIsEqualToSomething() throws Exception {
        // Initialize the database
        mLuckWeeklyQuestStageRepository.saveAndFlush(mLuckWeeklyQuestStage);

        // Get all the mLuckWeeklyQuestStageList where enableExtra equals to DEFAULT_ENABLE_EXTRA
        defaultMLuckWeeklyQuestStageShouldBeFound("enableExtra.equals=" + DEFAULT_ENABLE_EXTRA);

        // Get all the mLuckWeeklyQuestStageList where enableExtra equals to UPDATED_ENABLE_EXTRA
        defaultMLuckWeeklyQuestStageShouldNotBeFound("enableExtra.equals=" + UPDATED_ENABLE_EXTRA);
    }

    @Test
    @Transactional
    public void getAllMLuckWeeklyQuestStagesByEnableExtraIsInShouldWork() throws Exception {
        // Initialize the database
        mLuckWeeklyQuestStageRepository.saveAndFlush(mLuckWeeklyQuestStage);

        // Get all the mLuckWeeklyQuestStageList where enableExtra in DEFAULT_ENABLE_EXTRA or UPDATED_ENABLE_EXTRA
        defaultMLuckWeeklyQuestStageShouldBeFound("enableExtra.in=" + DEFAULT_ENABLE_EXTRA + "," + UPDATED_ENABLE_EXTRA);

        // Get all the mLuckWeeklyQuestStageList where enableExtra equals to UPDATED_ENABLE_EXTRA
        defaultMLuckWeeklyQuestStageShouldNotBeFound("enableExtra.in=" + UPDATED_ENABLE_EXTRA);
    }

    @Test
    @Transactional
    public void getAllMLuckWeeklyQuestStagesByEnableExtraIsNullOrNotNull() throws Exception {
        // Initialize the database
        mLuckWeeklyQuestStageRepository.saveAndFlush(mLuckWeeklyQuestStage);

        // Get all the mLuckWeeklyQuestStageList where enableExtra is not null
        defaultMLuckWeeklyQuestStageShouldBeFound("enableExtra.specified=true");

        // Get all the mLuckWeeklyQuestStageList where enableExtra is null
        defaultMLuckWeeklyQuestStageShouldNotBeFound("enableExtra.specified=false");
    }

    @Test
    @Transactional
    public void getAllMLuckWeeklyQuestStagesByEnableExtraIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mLuckWeeklyQuestStageRepository.saveAndFlush(mLuckWeeklyQuestStage);

        // Get all the mLuckWeeklyQuestStageList where enableExtra greater than or equals to DEFAULT_ENABLE_EXTRA
        defaultMLuckWeeklyQuestStageShouldBeFound("enableExtra.greaterOrEqualThan=" + DEFAULT_ENABLE_EXTRA);

        // Get all the mLuckWeeklyQuestStageList where enableExtra greater than or equals to UPDATED_ENABLE_EXTRA
        defaultMLuckWeeklyQuestStageShouldNotBeFound("enableExtra.greaterOrEqualThan=" + UPDATED_ENABLE_EXTRA);
    }

    @Test
    @Transactional
    public void getAllMLuckWeeklyQuestStagesByEnableExtraIsLessThanSomething() throws Exception {
        // Initialize the database
        mLuckWeeklyQuestStageRepository.saveAndFlush(mLuckWeeklyQuestStage);

        // Get all the mLuckWeeklyQuestStageList where enableExtra less than or equals to DEFAULT_ENABLE_EXTRA
        defaultMLuckWeeklyQuestStageShouldNotBeFound("enableExtra.lessThan=" + DEFAULT_ENABLE_EXTRA);

        // Get all the mLuckWeeklyQuestStageList where enableExtra less than or equals to UPDATED_ENABLE_EXTRA
        defaultMLuckWeeklyQuestStageShouldBeFound("enableExtra.lessThan=" + UPDATED_ENABLE_EXTRA);
    }


    @Test
    @Transactional
    public void getAllMLuckWeeklyQuestStagesByEnablePkIsEqualToSomething() throws Exception {
        // Initialize the database
        mLuckWeeklyQuestStageRepository.saveAndFlush(mLuckWeeklyQuestStage);

        // Get all the mLuckWeeklyQuestStageList where enablePk equals to DEFAULT_ENABLE_PK
        defaultMLuckWeeklyQuestStageShouldBeFound("enablePk.equals=" + DEFAULT_ENABLE_PK);

        // Get all the mLuckWeeklyQuestStageList where enablePk equals to UPDATED_ENABLE_PK
        defaultMLuckWeeklyQuestStageShouldNotBeFound("enablePk.equals=" + UPDATED_ENABLE_PK);
    }

    @Test
    @Transactional
    public void getAllMLuckWeeklyQuestStagesByEnablePkIsInShouldWork() throws Exception {
        // Initialize the database
        mLuckWeeklyQuestStageRepository.saveAndFlush(mLuckWeeklyQuestStage);

        // Get all the mLuckWeeklyQuestStageList where enablePk in DEFAULT_ENABLE_PK or UPDATED_ENABLE_PK
        defaultMLuckWeeklyQuestStageShouldBeFound("enablePk.in=" + DEFAULT_ENABLE_PK + "," + UPDATED_ENABLE_PK);

        // Get all the mLuckWeeklyQuestStageList where enablePk equals to UPDATED_ENABLE_PK
        defaultMLuckWeeklyQuestStageShouldNotBeFound("enablePk.in=" + UPDATED_ENABLE_PK);
    }

    @Test
    @Transactional
    public void getAllMLuckWeeklyQuestStagesByEnablePkIsNullOrNotNull() throws Exception {
        // Initialize the database
        mLuckWeeklyQuestStageRepository.saveAndFlush(mLuckWeeklyQuestStage);

        // Get all the mLuckWeeklyQuestStageList where enablePk is not null
        defaultMLuckWeeklyQuestStageShouldBeFound("enablePk.specified=true");

        // Get all the mLuckWeeklyQuestStageList where enablePk is null
        defaultMLuckWeeklyQuestStageShouldNotBeFound("enablePk.specified=false");
    }

    @Test
    @Transactional
    public void getAllMLuckWeeklyQuestStagesByEnablePkIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mLuckWeeklyQuestStageRepository.saveAndFlush(mLuckWeeklyQuestStage);

        // Get all the mLuckWeeklyQuestStageList where enablePk greater than or equals to DEFAULT_ENABLE_PK
        defaultMLuckWeeklyQuestStageShouldBeFound("enablePk.greaterOrEqualThan=" + DEFAULT_ENABLE_PK);

        // Get all the mLuckWeeklyQuestStageList where enablePk greater than or equals to UPDATED_ENABLE_PK
        defaultMLuckWeeklyQuestStageShouldNotBeFound("enablePk.greaterOrEqualThan=" + UPDATED_ENABLE_PK);
    }

    @Test
    @Transactional
    public void getAllMLuckWeeklyQuestStagesByEnablePkIsLessThanSomething() throws Exception {
        // Initialize the database
        mLuckWeeklyQuestStageRepository.saveAndFlush(mLuckWeeklyQuestStage);

        // Get all the mLuckWeeklyQuestStageList where enablePk less than or equals to DEFAULT_ENABLE_PK
        defaultMLuckWeeklyQuestStageShouldNotBeFound("enablePk.lessThan=" + DEFAULT_ENABLE_PK);

        // Get all the mLuckWeeklyQuestStageList where enablePk less than or equals to UPDATED_ENABLE_PK
        defaultMLuckWeeklyQuestStageShouldBeFound("enablePk.lessThan=" + UPDATED_ENABLE_PK);
    }


    @Test
    @Transactional
    public void getAllMLuckWeeklyQuestStagesByAiLevelIsEqualToSomething() throws Exception {
        // Initialize the database
        mLuckWeeklyQuestStageRepository.saveAndFlush(mLuckWeeklyQuestStage);

        // Get all the mLuckWeeklyQuestStageList where aiLevel equals to DEFAULT_AI_LEVEL
        defaultMLuckWeeklyQuestStageShouldBeFound("aiLevel.equals=" + DEFAULT_AI_LEVEL);

        // Get all the mLuckWeeklyQuestStageList where aiLevel equals to UPDATED_AI_LEVEL
        defaultMLuckWeeklyQuestStageShouldNotBeFound("aiLevel.equals=" + UPDATED_AI_LEVEL);
    }

    @Test
    @Transactional
    public void getAllMLuckWeeklyQuestStagesByAiLevelIsInShouldWork() throws Exception {
        // Initialize the database
        mLuckWeeklyQuestStageRepository.saveAndFlush(mLuckWeeklyQuestStage);

        // Get all the mLuckWeeklyQuestStageList where aiLevel in DEFAULT_AI_LEVEL or UPDATED_AI_LEVEL
        defaultMLuckWeeklyQuestStageShouldBeFound("aiLevel.in=" + DEFAULT_AI_LEVEL + "," + UPDATED_AI_LEVEL);

        // Get all the mLuckWeeklyQuestStageList where aiLevel equals to UPDATED_AI_LEVEL
        defaultMLuckWeeklyQuestStageShouldNotBeFound("aiLevel.in=" + UPDATED_AI_LEVEL);
    }

    @Test
    @Transactional
    public void getAllMLuckWeeklyQuestStagesByAiLevelIsNullOrNotNull() throws Exception {
        // Initialize the database
        mLuckWeeklyQuestStageRepository.saveAndFlush(mLuckWeeklyQuestStage);

        // Get all the mLuckWeeklyQuestStageList where aiLevel is not null
        defaultMLuckWeeklyQuestStageShouldBeFound("aiLevel.specified=true");

        // Get all the mLuckWeeklyQuestStageList where aiLevel is null
        defaultMLuckWeeklyQuestStageShouldNotBeFound("aiLevel.specified=false");
    }

    @Test
    @Transactional
    public void getAllMLuckWeeklyQuestStagesByAiLevelIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mLuckWeeklyQuestStageRepository.saveAndFlush(mLuckWeeklyQuestStage);

        // Get all the mLuckWeeklyQuestStageList where aiLevel greater than or equals to DEFAULT_AI_LEVEL
        defaultMLuckWeeklyQuestStageShouldBeFound("aiLevel.greaterOrEqualThan=" + DEFAULT_AI_LEVEL);

        // Get all the mLuckWeeklyQuestStageList where aiLevel greater than or equals to UPDATED_AI_LEVEL
        defaultMLuckWeeklyQuestStageShouldNotBeFound("aiLevel.greaterOrEqualThan=" + UPDATED_AI_LEVEL);
    }

    @Test
    @Transactional
    public void getAllMLuckWeeklyQuestStagesByAiLevelIsLessThanSomething() throws Exception {
        // Initialize the database
        mLuckWeeklyQuestStageRepository.saveAndFlush(mLuckWeeklyQuestStage);

        // Get all the mLuckWeeklyQuestStageList where aiLevel less than or equals to DEFAULT_AI_LEVEL
        defaultMLuckWeeklyQuestStageShouldNotBeFound("aiLevel.lessThan=" + DEFAULT_AI_LEVEL);

        // Get all the mLuckWeeklyQuestStageList where aiLevel less than or equals to UPDATED_AI_LEVEL
        defaultMLuckWeeklyQuestStageShouldBeFound("aiLevel.lessThan=" + UPDATED_AI_LEVEL);
    }


    @Test
    @Transactional
    public void getAllMLuckWeeklyQuestStagesByStartAtSecondHalfIsEqualToSomething() throws Exception {
        // Initialize the database
        mLuckWeeklyQuestStageRepository.saveAndFlush(mLuckWeeklyQuestStage);

        // Get all the mLuckWeeklyQuestStageList where startAtSecondHalf equals to DEFAULT_START_AT_SECOND_HALF
        defaultMLuckWeeklyQuestStageShouldBeFound("startAtSecondHalf.equals=" + DEFAULT_START_AT_SECOND_HALF);

        // Get all the mLuckWeeklyQuestStageList where startAtSecondHalf equals to UPDATED_START_AT_SECOND_HALF
        defaultMLuckWeeklyQuestStageShouldNotBeFound("startAtSecondHalf.equals=" + UPDATED_START_AT_SECOND_HALF);
    }

    @Test
    @Transactional
    public void getAllMLuckWeeklyQuestStagesByStartAtSecondHalfIsInShouldWork() throws Exception {
        // Initialize the database
        mLuckWeeklyQuestStageRepository.saveAndFlush(mLuckWeeklyQuestStage);

        // Get all the mLuckWeeklyQuestStageList where startAtSecondHalf in DEFAULT_START_AT_SECOND_HALF or UPDATED_START_AT_SECOND_HALF
        defaultMLuckWeeklyQuestStageShouldBeFound("startAtSecondHalf.in=" + DEFAULT_START_AT_SECOND_HALF + "," + UPDATED_START_AT_SECOND_HALF);

        // Get all the mLuckWeeklyQuestStageList where startAtSecondHalf equals to UPDATED_START_AT_SECOND_HALF
        defaultMLuckWeeklyQuestStageShouldNotBeFound("startAtSecondHalf.in=" + UPDATED_START_AT_SECOND_HALF);
    }

    @Test
    @Transactional
    public void getAllMLuckWeeklyQuestStagesByStartAtSecondHalfIsNullOrNotNull() throws Exception {
        // Initialize the database
        mLuckWeeklyQuestStageRepository.saveAndFlush(mLuckWeeklyQuestStage);

        // Get all the mLuckWeeklyQuestStageList where startAtSecondHalf is not null
        defaultMLuckWeeklyQuestStageShouldBeFound("startAtSecondHalf.specified=true");

        // Get all the mLuckWeeklyQuestStageList where startAtSecondHalf is null
        defaultMLuckWeeklyQuestStageShouldNotBeFound("startAtSecondHalf.specified=false");
    }

    @Test
    @Transactional
    public void getAllMLuckWeeklyQuestStagesByStartAtSecondHalfIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mLuckWeeklyQuestStageRepository.saveAndFlush(mLuckWeeklyQuestStage);

        // Get all the mLuckWeeklyQuestStageList where startAtSecondHalf greater than or equals to DEFAULT_START_AT_SECOND_HALF
        defaultMLuckWeeklyQuestStageShouldBeFound("startAtSecondHalf.greaterOrEqualThan=" + DEFAULT_START_AT_SECOND_HALF);

        // Get all the mLuckWeeklyQuestStageList where startAtSecondHalf greater than or equals to UPDATED_START_AT_SECOND_HALF
        defaultMLuckWeeklyQuestStageShouldNotBeFound("startAtSecondHalf.greaterOrEqualThan=" + UPDATED_START_AT_SECOND_HALF);
    }

    @Test
    @Transactional
    public void getAllMLuckWeeklyQuestStagesByStartAtSecondHalfIsLessThanSomething() throws Exception {
        // Initialize the database
        mLuckWeeklyQuestStageRepository.saveAndFlush(mLuckWeeklyQuestStage);

        // Get all the mLuckWeeklyQuestStageList where startAtSecondHalf less than or equals to DEFAULT_START_AT_SECOND_HALF
        defaultMLuckWeeklyQuestStageShouldNotBeFound("startAtSecondHalf.lessThan=" + DEFAULT_START_AT_SECOND_HALF);

        // Get all the mLuckWeeklyQuestStageList where startAtSecondHalf less than or equals to UPDATED_START_AT_SECOND_HALF
        defaultMLuckWeeklyQuestStageShouldBeFound("startAtSecondHalf.lessThan=" + UPDATED_START_AT_SECOND_HALF);
    }


    @Test
    @Transactional
    public void getAllMLuckWeeklyQuestStagesByStartScoreIsEqualToSomething() throws Exception {
        // Initialize the database
        mLuckWeeklyQuestStageRepository.saveAndFlush(mLuckWeeklyQuestStage);

        // Get all the mLuckWeeklyQuestStageList where startScore equals to DEFAULT_START_SCORE
        defaultMLuckWeeklyQuestStageShouldBeFound("startScore.equals=" + DEFAULT_START_SCORE);

        // Get all the mLuckWeeklyQuestStageList where startScore equals to UPDATED_START_SCORE
        defaultMLuckWeeklyQuestStageShouldNotBeFound("startScore.equals=" + UPDATED_START_SCORE);
    }

    @Test
    @Transactional
    public void getAllMLuckWeeklyQuestStagesByStartScoreIsInShouldWork() throws Exception {
        // Initialize the database
        mLuckWeeklyQuestStageRepository.saveAndFlush(mLuckWeeklyQuestStage);

        // Get all the mLuckWeeklyQuestStageList where startScore in DEFAULT_START_SCORE or UPDATED_START_SCORE
        defaultMLuckWeeklyQuestStageShouldBeFound("startScore.in=" + DEFAULT_START_SCORE + "," + UPDATED_START_SCORE);

        // Get all the mLuckWeeklyQuestStageList where startScore equals to UPDATED_START_SCORE
        defaultMLuckWeeklyQuestStageShouldNotBeFound("startScore.in=" + UPDATED_START_SCORE);
    }

    @Test
    @Transactional
    public void getAllMLuckWeeklyQuestStagesByStartScoreIsNullOrNotNull() throws Exception {
        // Initialize the database
        mLuckWeeklyQuestStageRepository.saveAndFlush(mLuckWeeklyQuestStage);

        // Get all the mLuckWeeklyQuestStageList where startScore is not null
        defaultMLuckWeeklyQuestStageShouldBeFound("startScore.specified=true");

        // Get all the mLuckWeeklyQuestStageList where startScore is null
        defaultMLuckWeeklyQuestStageShouldNotBeFound("startScore.specified=false");
    }

    @Test
    @Transactional
    public void getAllMLuckWeeklyQuestStagesByStartScoreIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mLuckWeeklyQuestStageRepository.saveAndFlush(mLuckWeeklyQuestStage);

        // Get all the mLuckWeeklyQuestStageList where startScore greater than or equals to DEFAULT_START_SCORE
        defaultMLuckWeeklyQuestStageShouldBeFound("startScore.greaterOrEqualThan=" + DEFAULT_START_SCORE);

        // Get all the mLuckWeeklyQuestStageList where startScore greater than or equals to UPDATED_START_SCORE
        defaultMLuckWeeklyQuestStageShouldNotBeFound("startScore.greaterOrEqualThan=" + UPDATED_START_SCORE);
    }

    @Test
    @Transactional
    public void getAllMLuckWeeklyQuestStagesByStartScoreIsLessThanSomething() throws Exception {
        // Initialize the database
        mLuckWeeklyQuestStageRepository.saveAndFlush(mLuckWeeklyQuestStage);

        // Get all the mLuckWeeklyQuestStageList where startScore less than or equals to DEFAULT_START_SCORE
        defaultMLuckWeeklyQuestStageShouldNotBeFound("startScore.lessThan=" + DEFAULT_START_SCORE);

        // Get all the mLuckWeeklyQuestStageList where startScore less than or equals to UPDATED_START_SCORE
        defaultMLuckWeeklyQuestStageShouldBeFound("startScore.lessThan=" + UPDATED_START_SCORE);
    }


    @Test
    @Transactional
    public void getAllMLuckWeeklyQuestStagesByStartScoreOpponentIsEqualToSomething() throws Exception {
        // Initialize the database
        mLuckWeeklyQuestStageRepository.saveAndFlush(mLuckWeeklyQuestStage);

        // Get all the mLuckWeeklyQuestStageList where startScoreOpponent equals to DEFAULT_START_SCORE_OPPONENT
        defaultMLuckWeeklyQuestStageShouldBeFound("startScoreOpponent.equals=" + DEFAULT_START_SCORE_OPPONENT);

        // Get all the mLuckWeeklyQuestStageList where startScoreOpponent equals to UPDATED_START_SCORE_OPPONENT
        defaultMLuckWeeklyQuestStageShouldNotBeFound("startScoreOpponent.equals=" + UPDATED_START_SCORE_OPPONENT);
    }

    @Test
    @Transactional
    public void getAllMLuckWeeklyQuestStagesByStartScoreOpponentIsInShouldWork() throws Exception {
        // Initialize the database
        mLuckWeeklyQuestStageRepository.saveAndFlush(mLuckWeeklyQuestStage);

        // Get all the mLuckWeeklyQuestStageList where startScoreOpponent in DEFAULT_START_SCORE_OPPONENT or UPDATED_START_SCORE_OPPONENT
        defaultMLuckWeeklyQuestStageShouldBeFound("startScoreOpponent.in=" + DEFAULT_START_SCORE_OPPONENT + "," + UPDATED_START_SCORE_OPPONENT);

        // Get all the mLuckWeeklyQuestStageList where startScoreOpponent equals to UPDATED_START_SCORE_OPPONENT
        defaultMLuckWeeklyQuestStageShouldNotBeFound("startScoreOpponent.in=" + UPDATED_START_SCORE_OPPONENT);
    }

    @Test
    @Transactional
    public void getAllMLuckWeeklyQuestStagesByStartScoreOpponentIsNullOrNotNull() throws Exception {
        // Initialize the database
        mLuckWeeklyQuestStageRepository.saveAndFlush(mLuckWeeklyQuestStage);

        // Get all the mLuckWeeklyQuestStageList where startScoreOpponent is not null
        defaultMLuckWeeklyQuestStageShouldBeFound("startScoreOpponent.specified=true");

        // Get all the mLuckWeeklyQuestStageList where startScoreOpponent is null
        defaultMLuckWeeklyQuestStageShouldNotBeFound("startScoreOpponent.specified=false");
    }

    @Test
    @Transactional
    public void getAllMLuckWeeklyQuestStagesByStartScoreOpponentIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mLuckWeeklyQuestStageRepository.saveAndFlush(mLuckWeeklyQuestStage);

        // Get all the mLuckWeeklyQuestStageList where startScoreOpponent greater than or equals to DEFAULT_START_SCORE_OPPONENT
        defaultMLuckWeeklyQuestStageShouldBeFound("startScoreOpponent.greaterOrEqualThan=" + DEFAULT_START_SCORE_OPPONENT);

        // Get all the mLuckWeeklyQuestStageList where startScoreOpponent greater than or equals to UPDATED_START_SCORE_OPPONENT
        defaultMLuckWeeklyQuestStageShouldNotBeFound("startScoreOpponent.greaterOrEqualThan=" + UPDATED_START_SCORE_OPPONENT);
    }

    @Test
    @Transactional
    public void getAllMLuckWeeklyQuestStagesByStartScoreOpponentIsLessThanSomething() throws Exception {
        // Initialize the database
        mLuckWeeklyQuestStageRepository.saveAndFlush(mLuckWeeklyQuestStage);

        // Get all the mLuckWeeklyQuestStageList where startScoreOpponent less than or equals to DEFAULT_START_SCORE_OPPONENT
        defaultMLuckWeeklyQuestStageShouldNotBeFound("startScoreOpponent.lessThan=" + DEFAULT_START_SCORE_OPPONENT);

        // Get all the mLuckWeeklyQuestStageList where startScoreOpponent less than or equals to UPDATED_START_SCORE_OPPONENT
        defaultMLuckWeeklyQuestStageShouldBeFound("startScoreOpponent.lessThan=" + UPDATED_START_SCORE_OPPONENT);
    }


    @Test
    @Transactional
    public void getAllMLuckWeeklyQuestStagesByConditionIdIsEqualToSomething() throws Exception {
        // Initialize the database
        mLuckWeeklyQuestStageRepository.saveAndFlush(mLuckWeeklyQuestStage);

        // Get all the mLuckWeeklyQuestStageList where conditionId equals to DEFAULT_CONDITION_ID
        defaultMLuckWeeklyQuestStageShouldBeFound("conditionId.equals=" + DEFAULT_CONDITION_ID);

        // Get all the mLuckWeeklyQuestStageList where conditionId equals to UPDATED_CONDITION_ID
        defaultMLuckWeeklyQuestStageShouldNotBeFound("conditionId.equals=" + UPDATED_CONDITION_ID);
    }

    @Test
    @Transactional
    public void getAllMLuckWeeklyQuestStagesByConditionIdIsInShouldWork() throws Exception {
        // Initialize the database
        mLuckWeeklyQuestStageRepository.saveAndFlush(mLuckWeeklyQuestStage);

        // Get all the mLuckWeeklyQuestStageList where conditionId in DEFAULT_CONDITION_ID or UPDATED_CONDITION_ID
        defaultMLuckWeeklyQuestStageShouldBeFound("conditionId.in=" + DEFAULT_CONDITION_ID + "," + UPDATED_CONDITION_ID);

        // Get all the mLuckWeeklyQuestStageList where conditionId equals to UPDATED_CONDITION_ID
        defaultMLuckWeeklyQuestStageShouldNotBeFound("conditionId.in=" + UPDATED_CONDITION_ID);
    }

    @Test
    @Transactional
    public void getAllMLuckWeeklyQuestStagesByConditionIdIsNullOrNotNull() throws Exception {
        // Initialize the database
        mLuckWeeklyQuestStageRepository.saveAndFlush(mLuckWeeklyQuestStage);

        // Get all the mLuckWeeklyQuestStageList where conditionId is not null
        defaultMLuckWeeklyQuestStageShouldBeFound("conditionId.specified=true");

        // Get all the mLuckWeeklyQuestStageList where conditionId is null
        defaultMLuckWeeklyQuestStageShouldNotBeFound("conditionId.specified=false");
    }

    @Test
    @Transactional
    public void getAllMLuckWeeklyQuestStagesByConditionIdIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mLuckWeeklyQuestStageRepository.saveAndFlush(mLuckWeeklyQuestStage);

        // Get all the mLuckWeeklyQuestStageList where conditionId greater than or equals to DEFAULT_CONDITION_ID
        defaultMLuckWeeklyQuestStageShouldBeFound("conditionId.greaterOrEqualThan=" + DEFAULT_CONDITION_ID);

        // Get all the mLuckWeeklyQuestStageList where conditionId greater than or equals to UPDATED_CONDITION_ID
        defaultMLuckWeeklyQuestStageShouldNotBeFound("conditionId.greaterOrEqualThan=" + UPDATED_CONDITION_ID);
    }

    @Test
    @Transactional
    public void getAllMLuckWeeklyQuestStagesByConditionIdIsLessThanSomething() throws Exception {
        // Initialize the database
        mLuckWeeklyQuestStageRepository.saveAndFlush(mLuckWeeklyQuestStage);

        // Get all the mLuckWeeklyQuestStageList where conditionId less than or equals to DEFAULT_CONDITION_ID
        defaultMLuckWeeklyQuestStageShouldNotBeFound("conditionId.lessThan=" + DEFAULT_CONDITION_ID);

        // Get all the mLuckWeeklyQuestStageList where conditionId less than or equals to UPDATED_CONDITION_ID
        defaultMLuckWeeklyQuestStageShouldBeFound("conditionId.lessThan=" + UPDATED_CONDITION_ID);
    }


    @Test
    @Transactional
    public void getAllMLuckWeeklyQuestStagesByOptionIdIsEqualToSomething() throws Exception {
        // Initialize the database
        mLuckWeeklyQuestStageRepository.saveAndFlush(mLuckWeeklyQuestStage);

        // Get all the mLuckWeeklyQuestStageList where optionId equals to DEFAULT_OPTION_ID
        defaultMLuckWeeklyQuestStageShouldBeFound("optionId.equals=" + DEFAULT_OPTION_ID);

        // Get all the mLuckWeeklyQuestStageList where optionId equals to UPDATED_OPTION_ID
        defaultMLuckWeeklyQuestStageShouldNotBeFound("optionId.equals=" + UPDATED_OPTION_ID);
    }

    @Test
    @Transactional
    public void getAllMLuckWeeklyQuestStagesByOptionIdIsInShouldWork() throws Exception {
        // Initialize the database
        mLuckWeeklyQuestStageRepository.saveAndFlush(mLuckWeeklyQuestStage);

        // Get all the mLuckWeeklyQuestStageList where optionId in DEFAULT_OPTION_ID or UPDATED_OPTION_ID
        defaultMLuckWeeklyQuestStageShouldBeFound("optionId.in=" + DEFAULT_OPTION_ID + "," + UPDATED_OPTION_ID);

        // Get all the mLuckWeeklyQuestStageList where optionId equals to UPDATED_OPTION_ID
        defaultMLuckWeeklyQuestStageShouldNotBeFound("optionId.in=" + UPDATED_OPTION_ID);
    }

    @Test
    @Transactional
    public void getAllMLuckWeeklyQuestStagesByOptionIdIsNullOrNotNull() throws Exception {
        // Initialize the database
        mLuckWeeklyQuestStageRepository.saveAndFlush(mLuckWeeklyQuestStage);

        // Get all the mLuckWeeklyQuestStageList where optionId is not null
        defaultMLuckWeeklyQuestStageShouldBeFound("optionId.specified=true");

        // Get all the mLuckWeeklyQuestStageList where optionId is null
        defaultMLuckWeeklyQuestStageShouldNotBeFound("optionId.specified=false");
    }

    @Test
    @Transactional
    public void getAllMLuckWeeklyQuestStagesByOptionIdIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mLuckWeeklyQuestStageRepository.saveAndFlush(mLuckWeeklyQuestStage);

        // Get all the mLuckWeeklyQuestStageList where optionId greater than or equals to DEFAULT_OPTION_ID
        defaultMLuckWeeklyQuestStageShouldBeFound("optionId.greaterOrEqualThan=" + DEFAULT_OPTION_ID);

        // Get all the mLuckWeeklyQuestStageList where optionId greater than or equals to UPDATED_OPTION_ID
        defaultMLuckWeeklyQuestStageShouldNotBeFound("optionId.greaterOrEqualThan=" + UPDATED_OPTION_ID);
    }

    @Test
    @Transactional
    public void getAllMLuckWeeklyQuestStagesByOptionIdIsLessThanSomething() throws Exception {
        // Initialize the database
        mLuckWeeklyQuestStageRepository.saveAndFlush(mLuckWeeklyQuestStage);

        // Get all the mLuckWeeklyQuestStageList where optionId less than or equals to DEFAULT_OPTION_ID
        defaultMLuckWeeklyQuestStageShouldNotBeFound("optionId.lessThan=" + DEFAULT_OPTION_ID);

        // Get all the mLuckWeeklyQuestStageList where optionId less than or equals to UPDATED_OPTION_ID
        defaultMLuckWeeklyQuestStageShouldBeFound("optionId.lessThan=" + UPDATED_OPTION_ID);
    }


    @Test
    @Transactional
    public void getAllMLuckWeeklyQuestStagesByDeckConditionIdIsEqualToSomething() throws Exception {
        // Initialize the database
        mLuckWeeklyQuestStageRepository.saveAndFlush(mLuckWeeklyQuestStage);

        // Get all the mLuckWeeklyQuestStageList where deckConditionId equals to DEFAULT_DECK_CONDITION_ID
        defaultMLuckWeeklyQuestStageShouldBeFound("deckConditionId.equals=" + DEFAULT_DECK_CONDITION_ID);

        // Get all the mLuckWeeklyQuestStageList where deckConditionId equals to UPDATED_DECK_CONDITION_ID
        defaultMLuckWeeklyQuestStageShouldNotBeFound("deckConditionId.equals=" + UPDATED_DECK_CONDITION_ID);
    }

    @Test
    @Transactional
    public void getAllMLuckWeeklyQuestStagesByDeckConditionIdIsInShouldWork() throws Exception {
        // Initialize the database
        mLuckWeeklyQuestStageRepository.saveAndFlush(mLuckWeeklyQuestStage);

        // Get all the mLuckWeeklyQuestStageList where deckConditionId in DEFAULT_DECK_CONDITION_ID or UPDATED_DECK_CONDITION_ID
        defaultMLuckWeeklyQuestStageShouldBeFound("deckConditionId.in=" + DEFAULT_DECK_CONDITION_ID + "," + UPDATED_DECK_CONDITION_ID);

        // Get all the mLuckWeeklyQuestStageList where deckConditionId equals to UPDATED_DECK_CONDITION_ID
        defaultMLuckWeeklyQuestStageShouldNotBeFound("deckConditionId.in=" + UPDATED_DECK_CONDITION_ID);
    }

    @Test
    @Transactional
    public void getAllMLuckWeeklyQuestStagesByDeckConditionIdIsNullOrNotNull() throws Exception {
        // Initialize the database
        mLuckWeeklyQuestStageRepository.saveAndFlush(mLuckWeeklyQuestStage);

        // Get all the mLuckWeeklyQuestStageList where deckConditionId is not null
        defaultMLuckWeeklyQuestStageShouldBeFound("deckConditionId.specified=true");

        // Get all the mLuckWeeklyQuestStageList where deckConditionId is null
        defaultMLuckWeeklyQuestStageShouldNotBeFound("deckConditionId.specified=false");
    }

    @Test
    @Transactional
    public void getAllMLuckWeeklyQuestStagesByDeckConditionIdIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mLuckWeeklyQuestStageRepository.saveAndFlush(mLuckWeeklyQuestStage);

        // Get all the mLuckWeeklyQuestStageList where deckConditionId greater than or equals to DEFAULT_DECK_CONDITION_ID
        defaultMLuckWeeklyQuestStageShouldBeFound("deckConditionId.greaterOrEqualThan=" + DEFAULT_DECK_CONDITION_ID);

        // Get all the mLuckWeeklyQuestStageList where deckConditionId greater than or equals to UPDATED_DECK_CONDITION_ID
        defaultMLuckWeeklyQuestStageShouldNotBeFound("deckConditionId.greaterOrEqualThan=" + UPDATED_DECK_CONDITION_ID);
    }

    @Test
    @Transactional
    public void getAllMLuckWeeklyQuestStagesByDeckConditionIdIsLessThanSomething() throws Exception {
        // Initialize the database
        mLuckWeeklyQuestStageRepository.saveAndFlush(mLuckWeeklyQuestStage);

        // Get all the mLuckWeeklyQuestStageList where deckConditionId less than or equals to DEFAULT_DECK_CONDITION_ID
        defaultMLuckWeeklyQuestStageShouldNotBeFound("deckConditionId.lessThan=" + DEFAULT_DECK_CONDITION_ID);

        // Get all the mLuckWeeklyQuestStageList where deckConditionId less than or equals to UPDATED_DECK_CONDITION_ID
        defaultMLuckWeeklyQuestStageShouldBeFound("deckConditionId.lessThan=" + UPDATED_DECK_CONDITION_ID);
    }


    @Test
    @Transactional
    public void getAllMLuckWeeklyQuestStagesByLuckIdIsEqualToSomething() throws Exception {
        // Initialize the database
        mLuckWeeklyQuestStageRepository.saveAndFlush(mLuckWeeklyQuestStage);

        // Get all the mLuckWeeklyQuestStageList where luckId equals to DEFAULT_LUCK_ID
        defaultMLuckWeeklyQuestStageShouldBeFound("luckId.equals=" + DEFAULT_LUCK_ID);

        // Get all the mLuckWeeklyQuestStageList where luckId equals to UPDATED_LUCK_ID
        defaultMLuckWeeklyQuestStageShouldNotBeFound("luckId.equals=" + UPDATED_LUCK_ID);
    }

    @Test
    @Transactional
    public void getAllMLuckWeeklyQuestStagesByLuckIdIsInShouldWork() throws Exception {
        // Initialize the database
        mLuckWeeklyQuestStageRepository.saveAndFlush(mLuckWeeklyQuestStage);

        // Get all the mLuckWeeklyQuestStageList where luckId in DEFAULT_LUCK_ID or UPDATED_LUCK_ID
        defaultMLuckWeeklyQuestStageShouldBeFound("luckId.in=" + DEFAULT_LUCK_ID + "," + UPDATED_LUCK_ID);

        // Get all the mLuckWeeklyQuestStageList where luckId equals to UPDATED_LUCK_ID
        defaultMLuckWeeklyQuestStageShouldNotBeFound("luckId.in=" + UPDATED_LUCK_ID);
    }

    @Test
    @Transactional
    public void getAllMLuckWeeklyQuestStagesByLuckIdIsNullOrNotNull() throws Exception {
        // Initialize the database
        mLuckWeeklyQuestStageRepository.saveAndFlush(mLuckWeeklyQuestStage);

        // Get all the mLuckWeeklyQuestStageList where luckId is not null
        defaultMLuckWeeklyQuestStageShouldBeFound("luckId.specified=true");

        // Get all the mLuckWeeklyQuestStageList where luckId is null
        defaultMLuckWeeklyQuestStageShouldNotBeFound("luckId.specified=false");
    }

    @Test
    @Transactional
    public void getAllMLuckWeeklyQuestStagesByLuckIdIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mLuckWeeklyQuestStageRepository.saveAndFlush(mLuckWeeklyQuestStage);

        // Get all the mLuckWeeklyQuestStageList where luckId greater than or equals to DEFAULT_LUCK_ID
        defaultMLuckWeeklyQuestStageShouldBeFound("luckId.greaterOrEqualThan=" + DEFAULT_LUCK_ID);

        // Get all the mLuckWeeklyQuestStageList where luckId greater than or equals to UPDATED_LUCK_ID
        defaultMLuckWeeklyQuestStageShouldNotBeFound("luckId.greaterOrEqualThan=" + UPDATED_LUCK_ID);
    }

    @Test
    @Transactional
    public void getAllMLuckWeeklyQuestStagesByLuckIdIsLessThanSomething() throws Exception {
        // Initialize the database
        mLuckWeeklyQuestStageRepository.saveAndFlush(mLuckWeeklyQuestStage);

        // Get all the mLuckWeeklyQuestStageList where luckId less than or equals to DEFAULT_LUCK_ID
        defaultMLuckWeeklyQuestStageShouldNotBeFound("luckId.lessThan=" + DEFAULT_LUCK_ID);

        // Get all the mLuckWeeklyQuestStageList where luckId less than or equals to UPDATED_LUCK_ID
        defaultMLuckWeeklyQuestStageShouldBeFound("luckId.lessThan=" + UPDATED_LUCK_ID);
    }


    @Test
    @Transactional
    public void getAllMLuckWeeklyQuestStagesByIdIsEqualToSomething() throws Exception {
        // Get already existing entity
        MLuckWeeklyQuestWorld id = mLuckWeeklyQuestStage.getId();
        mLuckWeeklyQuestStageRepository.saveAndFlush(mLuckWeeklyQuestStage);
        Long idId = id.getId();

        // Get all the mLuckWeeklyQuestStageList where id equals to idId
        defaultMLuckWeeklyQuestStageShouldBeFound("idId.equals=" + idId);

        // Get all the mLuckWeeklyQuestStageList where id equals to idId + 1
        defaultMLuckWeeklyQuestStageShouldNotBeFound("idId.equals=" + (idId + 1));
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultMLuckWeeklyQuestStageShouldBeFound(String filter) throws Exception {
        restMLuckWeeklyQuestStageMockMvc.perform(get("/api/m-luck-weekly-quest-stages?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(mLuckWeeklyQuestStage.getId().intValue())))
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
            .andExpect(jsonPath("$.[*].extraSecondHalfNpcDeckId").value(hasItem(DEFAULT_EXTRA_SECOND_HALF_NPC_DECK_ID)))
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
            .andExpect(jsonPath("$.[*].deckConditionId").value(hasItem(DEFAULT_DECK_CONDITION_ID)))
            .andExpect(jsonPath("$.[*].luckId").value(hasItem(DEFAULT_LUCK_ID)));

        // Check, that the count call also returns 1
        restMLuckWeeklyQuestStageMockMvc.perform(get("/api/m-luck-weekly-quest-stages/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultMLuckWeeklyQuestStageShouldNotBeFound(String filter) throws Exception {
        restMLuckWeeklyQuestStageMockMvc.perform(get("/api/m-luck-weekly-quest-stages?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restMLuckWeeklyQuestStageMockMvc.perform(get("/api/m-luck-weekly-quest-stages/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("0"));
    }


    @Test
    @Transactional
    public void getNonExistingMLuckWeeklyQuestStage() throws Exception {
        // Get the mLuckWeeklyQuestStage
        restMLuckWeeklyQuestStageMockMvc.perform(get("/api/m-luck-weekly-quest-stages/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateMLuckWeeklyQuestStage() throws Exception {
        // Initialize the database
        mLuckWeeklyQuestStageRepository.saveAndFlush(mLuckWeeklyQuestStage);

        int databaseSizeBeforeUpdate = mLuckWeeklyQuestStageRepository.findAll().size();

        // Update the mLuckWeeklyQuestStage
        MLuckWeeklyQuestStage updatedMLuckWeeklyQuestStage = mLuckWeeklyQuestStageRepository.findById(mLuckWeeklyQuestStage.getId()).get();
        // Disconnect from session so that the updates on updatedMLuckWeeklyQuestStage are not directly saved in db
        em.detach(updatedMLuckWeeklyQuestStage);
        updatedMLuckWeeklyQuestStage
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
            .extraSecondHalfNpcDeckId(UPDATED_EXTRA_SECOND_HALF_NPC_DECK_ID)
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
            .deckConditionId(UPDATED_DECK_CONDITION_ID)
            .luckId(UPDATED_LUCK_ID);
        MLuckWeeklyQuestStageDTO mLuckWeeklyQuestStageDTO = mLuckWeeklyQuestStageMapper.toDto(updatedMLuckWeeklyQuestStage);

        restMLuckWeeklyQuestStageMockMvc.perform(put("/api/m-luck-weekly-quest-stages")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mLuckWeeklyQuestStageDTO)))
            .andExpect(status().isOk());

        // Validate the MLuckWeeklyQuestStage in the database
        List<MLuckWeeklyQuestStage> mLuckWeeklyQuestStageList = mLuckWeeklyQuestStageRepository.findAll();
        assertThat(mLuckWeeklyQuestStageList).hasSize(databaseSizeBeforeUpdate);
        MLuckWeeklyQuestStage testMLuckWeeklyQuestStage = mLuckWeeklyQuestStageList.get(mLuckWeeklyQuestStageList.size() - 1);
        assertThat(testMLuckWeeklyQuestStage.getWorldId()).isEqualTo(UPDATED_WORLD_ID);
        assertThat(testMLuckWeeklyQuestStage.getNumber()).isEqualTo(UPDATED_NUMBER);
        assertThat(testMLuckWeeklyQuestStage.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testMLuckWeeklyQuestStage.getImagePath()).isEqualTo(UPDATED_IMAGE_PATH);
        assertThat(testMLuckWeeklyQuestStage.getCharacterThumbnailPath()).isEqualTo(UPDATED_CHARACTER_THUMBNAIL_PATH);
        assertThat(testMLuckWeeklyQuestStage.getDifficulty()).isEqualTo(UPDATED_DIFFICULTY);
        assertThat(testMLuckWeeklyQuestStage.getStageUnlockPattern()).isEqualTo(UPDATED_STAGE_UNLOCK_PATTERN);
        assertThat(testMLuckWeeklyQuestStage.getStoryOnly()).isEqualTo(UPDATED_STORY_ONLY);
        assertThat(testMLuckWeeklyQuestStage.getFirstHalfNpcDeckId()).isEqualTo(UPDATED_FIRST_HALF_NPC_DECK_ID);
        assertThat(testMLuckWeeklyQuestStage.getFirstHalfEnvironmentId()).isEqualTo(UPDATED_FIRST_HALF_ENVIRONMENT_ID);
        assertThat(testMLuckWeeklyQuestStage.getSecondHalfNpcDeckId()).isEqualTo(UPDATED_SECOND_HALF_NPC_DECK_ID);
        assertThat(testMLuckWeeklyQuestStage.getSecondHalfEnvironmentId()).isEqualTo(UPDATED_SECOND_HALF_ENVIRONMENT_ID);
        assertThat(testMLuckWeeklyQuestStage.getExtraFirstHalfNpcDeckId()).isEqualTo(UPDATED_EXTRA_FIRST_HALF_NPC_DECK_ID);
        assertThat(testMLuckWeeklyQuestStage.getExtraSecondHalfNpcDeckId()).isEqualTo(UPDATED_EXTRA_SECOND_HALF_NPC_DECK_ID);
        assertThat(testMLuckWeeklyQuestStage.getConsumeAp()).isEqualTo(UPDATED_CONSUME_AP);
        assertThat(testMLuckWeeklyQuestStage.getKickOffType()).isEqualTo(UPDATED_KICK_OFF_TYPE);
        assertThat(testMLuckWeeklyQuestStage.getMatchMinute()).isEqualTo(UPDATED_MATCH_MINUTE);
        assertThat(testMLuckWeeklyQuestStage.getEnableExtra()).isEqualTo(UPDATED_ENABLE_EXTRA);
        assertThat(testMLuckWeeklyQuestStage.getEnablePk()).isEqualTo(UPDATED_ENABLE_PK);
        assertThat(testMLuckWeeklyQuestStage.getAiLevel()).isEqualTo(UPDATED_AI_LEVEL);
        assertThat(testMLuckWeeklyQuestStage.getStartAtSecondHalf()).isEqualTo(UPDATED_START_AT_SECOND_HALF);
        assertThat(testMLuckWeeklyQuestStage.getStartScore()).isEqualTo(UPDATED_START_SCORE);
        assertThat(testMLuckWeeklyQuestStage.getStartScoreOpponent()).isEqualTo(UPDATED_START_SCORE_OPPONENT);
        assertThat(testMLuckWeeklyQuestStage.getConditionId()).isEqualTo(UPDATED_CONDITION_ID);
        assertThat(testMLuckWeeklyQuestStage.getOptionId()).isEqualTo(UPDATED_OPTION_ID);
        assertThat(testMLuckWeeklyQuestStage.getDeckConditionId()).isEqualTo(UPDATED_DECK_CONDITION_ID);
        assertThat(testMLuckWeeklyQuestStage.getLuckId()).isEqualTo(UPDATED_LUCK_ID);
    }

    @Test
    @Transactional
    public void updateNonExistingMLuckWeeklyQuestStage() throws Exception {
        int databaseSizeBeforeUpdate = mLuckWeeklyQuestStageRepository.findAll().size();

        // Create the MLuckWeeklyQuestStage
        MLuckWeeklyQuestStageDTO mLuckWeeklyQuestStageDTO = mLuckWeeklyQuestStageMapper.toDto(mLuckWeeklyQuestStage);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restMLuckWeeklyQuestStageMockMvc.perform(put("/api/m-luck-weekly-quest-stages")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mLuckWeeklyQuestStageDTO)))
            .andExpect(status().isBadRequest());

        // Validate the MLuckWeeklyQuestStage in the database
        List<MLuckWeeklyQuestStage> mLuckWeeklyQuestStageList = mLuckWeeklyQuestStageRepository.findAll();
        assertThat(mLuckWeeklyQuestStageList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteMLuckWeeklyQuestStage() throws Exception {
        // Initialize the database
        mLuckWeeklyQuestStageRepository.saveAndFlush(mLuckWeeklyQuestStage);

        int databaseSizeBeforeDelete = mLuckWeeklyQuestStageRepository.findAll().size();

        // Delete the mLuckWeeklyQuestStage
        restMLuckWeeklyQuestStageMockMvc.perform(delete("/api/m-luck-weekly-quest-stages/{id}", mLuckWeeklyQuestStage.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database is empty
        List<MLuckWeeklyQuestStage> mLuckWeeklyQuestStageList = mLuckWeeklyQuestStageRepository.findAll();
        assertThat(mLuckWeeklyQuestStageList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(MLuckWeeklyQuestStage.class);
        MLuckWeeklyQuestStage mLuckWeeklyQuestStage1 = new MLuckWeeklyQuestStage();
        mLuckWeeklyQuestStage1.setId(1L);
        MLuckWeeklyQuestStage mLuckWeeklyQuestStage2 = new MLuckWeeklyQuestStage();
        mLuckWeeklyQuestStage2.setId(mLuckWeeklyQuestStage1.getId());
        assertThat(mLuckWeeklyQuestStage1).isEqualTo(mLuckWeeklyQuestStage2);
        mLuckWeeklyQuestStage2.setId(2L);
        assertThat(mLuckWeeklyQuestStage1).isNotEqualTo(mLuckWeeklyQuestStage2);
        mLuckWeeklyQuestStage1.setId(null);
        assertThat(mLuckWeeklyQuestStage1).isNotEqualTo(mLuckWeeklyQuestStage2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(MLuckWeeklyQuestStageDTO.class);
        MLuckWeeklyQuestStageDTO mLuckWeeklyQuestStageDTO1 = new MLuckWeeklyQuestStageDTO();
        mLuckWeeklyQuestStageDTO1.setId(1L);
        MLuckWeeklyQuestStageDTO mLuckWeeklyQuestStageDTO2 = new MLuckWeeklyQuestStageDTO();
        assertThat(mLuckWeeklyQuestStageDTO1).isNotEqualTo(mLuckWeeklyQuestStageDTO2);
        mLuckWeeklyQuestStageDTO2.setId(mLuckWeeklyQuestStageDTO1.getId());
        assertThat(mLuckWeeklyQuestStageDTO1).isEqualTo(mLuckWeeklyQuestStageDTO2);
        mLuckWeeklyQuestStageDTO2.setId(2L);
        assertThat(mLuckWeeklyQuestStageDTO1).isNotEqualTo(mLuckWeeklyQuestStageDTO2);
        mLuckWeeklyQuestStageDTO1.setId(null);
        assertThat(mLuckWeeklyQuestStageDTO1).isNotEqualTo(mLuckWeeklyQuestStageDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(mLuckWeeklyQuestStageMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(mLuckWeeklyQuestStageMapper.fromId(null)).isNull();
    }
}
