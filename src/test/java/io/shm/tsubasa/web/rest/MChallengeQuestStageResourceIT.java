package io.shm.tsubasa.web.rest;

import io.shm.tsubasa.TsubasaApp;
import io.shm.tsubasa.domain.MChallengeQuestStage;
import io.shm.tsubasa.domain.MChallengeQuestWorld;
import io.shm.tsubasa.repository.MChallengeQuestStageRepository;
import io.shm.tsubasa.service.MChallengeQuestStageService;
import io.shm.tsubasa.service.dto.MChallengeQuestStageDTO;
import io.shm.tsubasa.service.mapper.MChallengeQuestStageMapper;
import io.shm.tsubasa.web.rest.errors.ExceptionTranslator;
import io.shm.tsubasa.service.dto.MChallengeQuestStageCriteria;
import io.shm.tsubasa.service.MChallengeQuestStageQueryService;

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
 * Integration tests for the {@Link MChallengeQuestStageResource} REST controller.
 */
@SpringBootTest(classes = TsubasaApp.class)
public class MChallengeQuestStageResourceIT {

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

    private static final String DEFAULT_SHORT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_SHORT_NAME = "BBBBBBBBBB";

    private static final Integer DEFAULT_SKIP_CHECK_POINT = 1;
    private static final Integer UPDATED_SKIP_CHECK_POINT = 2;

    private static final Integer DEFAULT_ROAD_NUMBER = 1;
    private static final Integer UPDATED_ROAD_NUMBER = 2;

    @Autowired
    private MChallengeQuestStageRepository mChallengeQuestStageRepository;

    @Autowired
    private MChallengeQuestStageMapper mChallengeQuestStageMapper;

    @Autowired
    private MChallengeQuestStageService mChallengeQuestStageService;

    @Autowired
    private MChallengeQuestStageQueryService mChallengeQuestStageQueryService;

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

    private MockMvc restMChallengeQuestStageMockMvc;

    private MChallengeQuestStage mChallengeQuestStage;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final MChallengeQuestStageResource mChallengeQuestStageResource = new MChallengeQuestStageResource(mChallengeQuestStageService, mChallengeQuestStageQueryService);
        this.restMChallengeQuestStageMockMvc = MockMvcBuilders.standaloneSetup(mChallengeQuestStageResource)
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
    public static MChallengeQuestStage createEntity(EntityManager em) {
        MChallengeQuestStage mChallengeQuestStage = new MChallengeQuestStage()
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
            .deckConditionId(DEFAULT_DECK_CONDITION_ID)
            .shortName(DEFAULT_SHORT_NAME)
            .skipCheckPoint(DEFAULT_SKIP_CHECK_POINT)
            .roadNumber(DEFAULT_ROAD_NUMBER);
        // Add required entity
        MChallengeQuestWorld mChallengeQuestWorld;
        if (TestUtil.findAll(em, MChallengeQuestWorld.class).isEmpty()) {
            mChallengeQuestWorld = MChallengeQuestWorldResourceIT.createEntity(em);
            em.persist(mChallengeQuestWorld);
            em.flush();
        } else {
            mChallengeQuestWorld = TestUtil.findAll(em, MChallengeQuestWorld.class).get(0);
        }
        mChallengeQuestStage.setMchallengequestworld(mChallengeQuestWorld);
        return mChallengeQuestStage;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static MChallengeQuestStage createUpdatedEntity(EntityManager em) {
        MChallengeQuestStage mChallengeQuestStage = new MChallengeQuestStage()
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
            .deckConditionId(UPDATED_DECK_CONDITION_ID)
            .shortName(UPDATED_SHORT_NAME)
            .skipCheckPoint(UPDATED_SKIP_CHECK_POINT)
            .roadNumber(UPDATED_ROAD_NUMBER);
        // Add required entity
        MChallengeQuestWorld mChallengeQuestWorld;
        if (TestUtil.findAll(em, MChallengeQuestWorld.class).isEmpty()) {
            mChallengeQuestWorld = MChallengeQuestWorldResourceIT.createUpdatedEntity(em);
            em.persist(mChallengeQuestWorld);
            em.flush();
        } else {
            mChallengeQuestWorld = TestUtil.findAll(em, MChallengeQuestWorld.class).get(0);
        }
        mChallengeQuestStage.setMchallengequestworld(mChallengeQuestWorld);
        return mChallengeQuestStage;
    }

    @BeforeEach
    public void initTest() {
        mChallengeQuestStage = createEntity(em);
    }

    @Test
    @Transactional
    public void createMChallengeQuestStage() throws Exception {
        int databaseSizeBeforeCreate = mChallengeQuestStageRepository.findAll().size();

        // Create the MChallengeQuestStage
        MChallengeQuestStageDTO mChallengeQuestStageDTO = mChallengeQuestStageMapper.toDto(mChallengeQuestStage);
        restMChallengeQuestStageMockMvc.perform(post("/api/m-challenge-quest-stages")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mChallengeQuestStageDTO)))
            .andExpect(status().isCreated());

        // Validate the MChallengeQuestStage in the database
        List<MChallengeQuestStage> mChallengeQuestStageList = mChallengeQuestStageRepository.findAll();
        assertThat(mChallengeQuestStageList).hasSize(databaseSizeBeforeCreate + 1);
        MChallengeQuestStage testMChallengeQuestStage = mChallengeQuestStageList.get(mChallengeQuestStageList.size() - 1);
        assertThat(testMChallengeQuestStage.getWorldId()).isEqualTo(DEFAULT_WORLD_ID);
        assertThat(testMChallengeQuestStage.getNumber()).isEqualTo(DEFAULT_NUMBER);
        assertThat(testMChallengeQuestStage.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testMChallengeQuestStage.getImagePath()).isEqualTo(DEFAULT_IMAGE_PATH);
        assertThat(testMChallengeQuestStage.getCharacterThumbnailPath()).isEqualTo(DEFAULT_CHARACTER_THUMBNAIL_PATH);
        assertThat(testMChallengeQuestStage.getDifficulty()).isEqualTo(DEFAULT_DIFFICULTY);
        assertThat(testMChallengeQuestStage.getStageUnlockPattern()).isEqualTo(DEFAULT_STAGE_UNLOCK_PATTERN);
        assertThat(testMChallengeQuestStage.getStoryOnly()).isEqualTo(DEFAULT_STORY_ONLY);
        assertThat(testMChallengeQuestStage.getFirstHalfNpcDeckId()).isEqualTo(DEFAULT_FIRST_HALF_NPC_DECK_ID);
        assertThat(testMChallengeQuestStage.getFirstHalfEnvironmentId()).isEqualTo(DEFAULT_FIRST_HALF_ENVIRONMENT_ID);
        assertThat(testMChallengeQuestStage.getSecondHalfNpcDeckId()).isEqualTo(DEFAULT_SECOND_HALF_NPC_DECK_ID);
        assertThat(testMChallengeQuestStage.getSecondHalfEnvironmentId()).isEqualTo(DEFAULT_SECOND_HALF_ENVIRONMENT_ID);
        assertThat(testMChallengeQuestStage.getExtraFirstHalfNpcDeckId()).isEqualTo(DEFAULT_EXTRA_FIRST_HALF_NPC_DECK_ID);
        assertThat(testMChallengeQuestStage.getConsumeAp()).isEqualTo(DEFAULT_CONSUME_AP);
        assertThat(testMChallengeQuestStage.getKickOffType()).isEqualTo(DEFAULT_KICK_OFF_TYPE);
        assertThat(testMChallengeQuestStage.getMatchMinute()).isEqualTo(DEFAULT_MATCH_MINUTE);
        assertThat(testMChallengeQuestStage.getEnableExtra()).isEqualTo(DEFAULT_ENABLE_EXTRA);
        assertThat(testMChallengeQuestStage.getEnablePk()).isEqualTo(DEFAULT_ENABLE_PK);
        assertThat(testMChallengeQuestStage.getAiLevel()).isEqualTo(DEFAULT_AI_LEVEL);
        assertThat(testMChallengeQuestStage.getStartAtSecondHalf()).isEqualTo(DEFAULT_START_AT_SECOND_HALF);
        assertThat(testMChallengeQuestStage.getStartScore()).isEqualTo(DEFAULT_START_SCORE);
        assertThat(testMChallengeQuestStage.getStartScoreOpponent()).isEqualTo(DEFAULT_START_SCORE_OPPONENT);
        assertThat(testMChallengeQuestStage.getConditionId()).isEqualTo(DEFAULT_CONDITION_ID);
        assertThat(testMChallengeQuestStage.getOptionId()).isEqualTo(DEFAULT_OPTION_ID);
        assertThat(testMChallengeQuestStage.getDeckConditionId()).isEqualTo(DEFAULT_DECK_CONDITION_ID);
        assertThat(testMChallengeQuestStage.getShortName()).isEqualTo(DEFAULT_SHORT_NAME);
        assertThat(testMChallengeQuestStage.getSkipCheckPoint()).isEqualTo(DEFAULT_SKIP_CHECK_POINT);
        assertThat(testMChallengeQuestStage.getRoadNumber()).isEqualTo(DEFAULT_ROAD_NUMBER);
    }

    @Test
    @Transactional
    public void createMChallengeQuestStageWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = mChallengeQuestStageRepository.findAll().size();

        // Create the MChallengeQuestStage with an existing ID
        mChallengeQuestStage.setId(1L);
        MChallengeQuestStageDTO mChallengeQuestStageDTO = mChallengeQuestStageMapper.toDto(mChallengeQuestStage);

        // An entity with an existing ID cannot be created, so this API call must fail
        restMChallengeQuestStageMockMvc.perform(post("/api/m-challenge-quest-stages")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mChallengeQuestStageDTO)))
            .andExpect(status().isBadRequest());

        // Validate the MChallengeQuestStage in the database
        List<MChallengeQuestStage> mChallengeQuestStageList = mChallengeQuestStageRepository.findAll();
        assertThat(mChallengeQuestStageList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkWorldIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = mChallengeQuestStageRepository.findAll().size();
        // set the field null
        mChallengeQuestStage.setWorldId(null);

        // Create the MChallengeQuestStage, which fails.
        MChallengeQuestStageDTO mChallengeQuestStageDTO = mChallengeQuestStageMapper.toDto(mChallengeQuestStage);

        restMChallengeQuestStageMockMvc.perform(post("/api/m-challenge-quest-stages")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mChallengeQuestStageDTO)))
            .andExpect(status().isBadRequest());

        List<MChallengeQuestStage> mChallengeQuestStageList = mChallengeQuestStageRepository.findAll();
        assertThat(mChallengeQuestStageList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkNumberIsRequired() throws Exception {
        int databaseSizeBeforeTest = mChallengeQuestStageRepository.findAll().size();
        // set the field null
        mChallengeQuestStage.setNumber(null);

        // Create the MChallengeQuestStage, which fails.
        MChallengeQuestStageDTO mChallengeQuestStageDTO = mChallengeQuestStageMapper.toDto(mChallengeQuestStage);

        restMChallengeQuestStageMockMvc.perform(post("/api/m-challenge-quest-stages")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mChallengeQuestStageDTO)))
            .andExpect(status().isBadRequest());

        List<MChallengeQuestStage> mChallengeQuestStageList = mChallengeQuestStageRepository.findAll();
        assertThat(mChallengeQuestStageList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkDifficultyIsRequired() throws Exception {
        int databaseSizeBeforeTest = mChallengeQuestStageRepository.findAll().size();
        // set the field null
        mChallengeQuestStage.setDifficulty(null);

        // Create the MChallengeQuestStage, which fails.
        MChallengeQuestStageDTO mChallengeQuestStageDTO = mChallengeQuestStageMapper.toDto(mChallengeQuestStage);

        restMChallengeQuestStageMockMvc.perform(post("/api/m-challenge-quest-stages")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mChallengeQuestStageDTO)))
            .andExpect(status().isBadRequest());

        List<MChallengeQuestStage> mChallengeQuestStageList = mChallengeQuestStageRepository.findAll();
        assertThat(mChallengeQuestStageList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkStageUnlockPatternIsRequired() throws Exception {
        int databaseSizeBeforeTest = mChallengeQuestStageRepository.findAll().size();
        // set the field null
        mChallengeQuestStage.setStageUnlockPattern(null);

        // Create the MChallengeQuestStage, which fails.
        MChallengeQuestStageDTO mChallengeQuestStageDTO = mChallengeQuestStageMapper.toDto(mChallengeQuestStage);

        restMChallengeQuestStageMockMvc.perform(post("/api/m-challenge-quest-stages")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mChallengeQuestStageDTO)))
            .andExpect(status().isBadRequest());

        List<MChallengeQuestStage> mChallengeQuestStageList = mChallengeQuestStageRepository.findAll();
        assertThat(mChallengeQuestStageList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkStoryOnlyIsRequired() throws Exception {
        int databaseSizeBeforeTest = mChallengeQuestStageRepository.findAll().size();
        // set the field null
        mChallengeQuestStage.setStoryOnly(null);

        // Create the MChallengeQuestStage, which fails.
        MChallengeQuestStageDTO mChallengeQuestStageDTO = mChallengeQuestStageMapper.toDto(mChallengeQuestStage);

        restMChallengeQuestStageMockMvc.perform(post("/api/m-challenge-quest-stages")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mChallengeQuestStageDTO)))
            .andExpect(status().isBadRequest());

        List<MChallengeQuestStage> mChallengeQuestStageList = mChallengeQuestStageRepository.findAll();
        assertThat(mChallengeQuestStageList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkFirstHalfNpcDeckIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = mChallengeQuestStageRepository.findAll().size();
        // set the field null
        mChallengeQuestStage.setFirstHalfNpcDeckId(null);

        // Create the MChallengeQuestStage, which fails.
        MChallengeQuestStageDTO mChallengeQuestStageDTO = mChallengeQuestStageMapper.toDto(mChallengeQuestStage);

        restMChallengeQuestStageMockMvc.perform(post("/api/m-challenge-quest-stages")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mChallengeQuestStageDTO)))
            .andExpect(status().isBadRequest());

        List<MChallengeQuestStage> mChallengeQuestStageList = mChallengeQuestStageRepository.findAll();
        assertThat(mChallengeQuestStageList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkFirstHalfEnvironmentIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = mChallengeQuestStageRepository.findAll().size();
        // set the field null
        mChallengeQuestStage.setFirstHalfEnvironmentId(null);

        // Create the MChallengeQuestStage, which fails.
        MChallengeQuestStageDTO mChallengeQuestStageDTO = mChallengeQuestStageMapper.toDto(mChallengeQuestStage);

        restMChallengeQuestStageMockMvc.perform(post("/api/m-challenge-quest-stages")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mChallengeQuestStageDTO)))
            .andExpect(status().isBadRequest());

        List<MChallengeQuestStage> mChallengeQuestStageList = mChallengeQuestStageRepository.findAll();
        assertThat(mChallengeQuestStageList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkSecondHalfNpcDeckIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = mChallengeQuestStageRepository.findAll().size();
        // set the field null
        mChallengeQuestStage.setSecondHalfNpcDeckId(null);

        // Create the MChallengeQuestStage, which fails.
        MChallengeQuestStageDTO mChallengeQuestStageDTO = mChallengeQuestStageMapper.toDto(mChallengeQuestStage);

        restMChallengeQuestStageMockMvc.perform(post("/api/m-challenge-quest-stages")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mChallengeQuestStageDTO)))
            .andExpect(status().isBadRequest());

        List<MChallengeQuestStage> mChallengeQuestStageList = mChallengeQuestStageRepository.findAll();
        assertThat(mChallengeQuestStageList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkSecondHalfEnvironmentIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = mChallengeQuestStageRepository.findAll().size();
        // set the field null
        mChallengeQuestStage.setSecondHalfEnvironmentId(null);

        // Create the MChallengeQuestStage, which fails.
        MChallengeQuestStageDTO mChallengeQuestStageDTO = mChallengeQuestStageMapper.toDto(mChallengeQuestStage);

        restMChallengeQuestStageMockMvc.perform(post("/api/m-challenge-quest-stages")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mChallengeQuestStageDTO)))
            .andExpect(status().isBadRequest());

        List<MChallengeQuestStage> mChallengeQuestStageList = mChallengeQuestStageRepository.findAll();
        assertThat(mChallengeQuestStageList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkExtraFirstHalfNpcDeckIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = mChallengeQuestStageRepository.findAll().size();
        // set the field null
        mChallengeQuestStage.setExtraFirstHalfNpcDeckId(null);

        // Create the MChallengeQuestStage, which fails.
        MChallengeQuestStageDTO mChallengeQuestStageDTO = mChallengeQuestStageMapper.toDto(mChallengeQuestStage);

        restMChallengeQuestStageMockMvc.perform(post("/api/m-challenge-quest-stages")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mChallengeQuestStageDTO)))
            .andExpect(status().isBadRequest());

        List<MChallengeQuestStage> mChallengeQuestStageList = mChallengeQuestStageRepository.findAll();
        assertThat(mChallengeQuestStageList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkConsumeApIsRequired() throws Exception {
        int databaseSizeBeforeTest = mChallengeQuestStageRepository.findAll().size();
        // set the field null
        mChallengeQuestStage.setConsumeAp(null);

        // Create the MChallengeQuestStage, which fails.
        MChallengeQuestStageDTO mChallengeQuestStageDTO = mChallengeQuestStageMapper.toDto(mChallengeQuestStage);

        restMChallengeQuestStageMockMvc.perform(post("/api/m-challenge-quest-stages")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mChallengeQuestStageDTO)))
            .andExpect(status().isBadRequest());

        List<MChallengeQuestStage> mChallengeQuestStageList = mChallengeQuestStageRepository.findAll();
        assertThat(mChallengeQuestStageList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkKickOffTypeIsRequired() throws Exception {
        int databaseSizeBeforeTest = mChallengeQuestStageRepository.findAll().size();
        // set the field null
        mChallengeQuestStage.setKickOffType(null);

        // Create the MChallengeQuestStage, which fails.
        MChallengeQuestStageDTO mChallengeQuestStageDTO = mChallengeQuestStageMapper.toDto(mChallengeQuestStage);

        restMChallengeQuestStageMockMvc.perform(post("/api/m-challenge-quest-stages")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mChallengeQuestStageDTO)))
            .andExpect(status().isBadRequest());

        List<MChallengeQuestStage> mChallengeQuestStageList = mChallengeQuestStageRepository.findAll();
        assertThat(mChallengeQuestStageList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkMatchMinuteIsRequired() throws Exception {
        int databaseSizeBeforeTest = mChallengeQuestStageRepository.findAll().size();
        // set the field null
        mChallengeQuestStage.setMatchMinute(null);

        // Create the MChallengeQuestStage, which fails.
        MChallengeQuestStageDTO mChallengeQuestStageDTO = mChallengeQuestStageMapper.toDto(mChallengeQuestStage);

        restMChallengeQuestStageMockMvc.perform(post("/api/m-challenge-quest-stages")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mChallengeQuestStageDTO)))
            .andExpect(status().isBadRequest());

        List<MChallengeQuestStage> mChallengeQuestStageList = mChallengeQuestStageRepository.findAll();
        assertThat(mChallengeQuestStageList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkEnableExtraIsRequired() throws Exception {
        int databaseSizeBeforeTest = mChallengeQuestStageRepository.findAll().size();
        // set the field null
        mChallengeQuestStage.setEnableExtra(null);

        // Create the MChallengeQuestStage, which fails.
        MChallengeQuestStageDTO mChallengeQuestStageDTO = mChallengeQuestStageMapper.toDto(mChallengeQuestStage);

        restMChallengeQuestStageMockMvc.perform(post("/api/m-challenge-quest-stages")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mChallengeQuestStageDTO)))
            .andExpect(status().isBadRequest());

        List<MChallengeQuestStage> mChallengeQuestStageList = mChallengeQuestStageRepository.findAll();
        assertThat(mChallengeQuestStageList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkEnablePkIsRequired() throws Exception {
        int databaseSizeBeforeTest = mChallengeQuestStageRepository.findAll().size();
        // set the field null
        mChallengeQuestStage.setEnablePk(null);

        // Create the MChallengeQuestStage, which fails.
        MChallengeQuestStageDTO mChallengeQuestStageDTO = mChallengeQuestStageMapper.toDto(mChallengeQuestStage);

        restMChallengeQuestStageMockMvc.perform(post("/api/m-challenge-quest-stages")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mChallengeQuestStageDTO)))
            .andExpect(status().isBadRequest());

        List<MChallengeQuestStage> mChallengeQuestStageList = mChallengeQuestStageRepository.findAll();
        assertThat(mChallengeQuestStageList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkAiLevelIsRequired() throws Exception {
        int databaseSizeBeforeTest = mChallengeQuestStageRepository.findAll().size();
        // set the field null
        mChallengeQuestStage.setAiLevel(null);

        // Create the MChallengeQuestStage, which fails.
        MChallengeQuestStageDTO mChallengeQuestStageDTO = mChallengeQuestStageMapper.toDto(mChallengeQuestStage);

        restMChallengeQuestStageMockMvc.perform(post("/api/m-challenge-quest-stages")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mChallengeQuestStageDTO)))
            .andExpect(status().isBadRequest());

        List<MChallengeQuestStage> mChallengeQuestStageList = mChallengeQuestStageRepository.findAll();
        assertThat(mChallengeQuestStageList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkStartAtSecondHalfIsRequired() throws Exception {
        int databaseSizeBeforeTest = mChallengeQuestStageRepository.findAll().size();
        // set the field null
        mChallengeQuestStage.setStartAtSecondHalf(null);

        // Create the MChallengeQuestStage, which fails.
        MChallengeQuestStageDTO mChallengeQuestStageDTO = mChallengeQuestStageMapper.toDto(mChallengeQuestStage);

        restMChallengeQuestStageMockMvc.perform(post("/api/m-challenge-quest-stages")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mChallengeQuestStageDTO)))
            .andExpect(status().isBadRequest());

        List<MChallengeQuestStage> mChallengeQuestStageList = mChallengeQuestStageRepository.findAll();
        assertThat(mChallengeQuestStageList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkStartScoreIsRequired() throws Exception {
        int databaseSizeBeforeTest = mChallengeQuestStageRepository.findAll().size();
        // set the field null
        mChallengeQuestStage.setStartScore(null);

        // Create the MChallengeQuestStage, which fails.
        MChallengeQuestStageDTO mChallengeQuestStageDTO = mChallengeQuestStageMapper.toDto(mChallengeQuestStage);

        restMChallengeQuestStageMockMvc.perform(post("/api/m-challenge-quest-stages")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mChallengeQuestStageDTO)))
            .andExpect(status().isBadRequest());

        List<MChallengeQuestStage> mChallengeQuestStageList = mChallengeQuestStageRepository.findAll();
        assertThat(mChallengeQuestStageList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkStartScoreOpponentIsRequired() throws Exception {
        int databaseSizeBeforeTest = mChallengeQuestStageRepository.findAll().size();
        // set the field null
        mChallengeQuestStage.setStartScoreOpponent(null);

        // Create the MChallengeQuestStage, which fails.
        MChallengeQuestStageDTO mChallengeQuestStageDTO = mChallengeQuestStageMapper.toDto(mChallengeQuestStage);

        restMChallengeQuestStageMockMvc.perform(post("/api/m-challenge-quest-stages")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mChallengeQuestStageDTO)))
            .andExpect(status().isBadRequest());

        List<MChallengeQuestStage> mChallengeQuestStageList = mChallengeQuestStageRepository.findAll();
        assertThat(mChallengeQuestStageList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkSkipCheckPointIsRequired() throws Exception {
        int databaseSizeBeforeTest = mChallengeQuestStageRepository.findAll().size();
        // set the field null
        mChallengeQuestStage.setSkipCheckPoint(null);

        // Create the MChallengeQuestStage, which fails.
        MChallengeQuestStageDTO mChallengeQuestStageDTO = mChallengeQuestStageMapper.toDto(mChallengeQuestStage);

        restMChallengeQuestStageMockMvc.perform(post("/api/m-challenge-quest-stages")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mChallengeQuestStageDTO)))
            .andExpect(status().isBadRequest());

        List<MChallengeQuestStage> mChallengeQuestStageList = mChallengeQuestStageRepository.findAll();
        assertThat(mChallengeQuestStageList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkRoadNumberIsRequired() throws Exception {
        int databaseSizeBeforeTest = mChallengeQuestStageRepository.findAll().size();
        // set the field null
        mChallengeQuestStage.setRoadNumber(null);

        // Create the MChallengeQuestStage, which fails.
        MChallengeQuestStageDTO mChallengeQuestStageDTO = mChallengeQuestStageMapper.toDto(mChallengeQuestStage);

        restMChallengeQuestStageMockMvc.perform(post("/api/m-challenge-quest-stages")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mChallengeQuestStageDTO)))
            .andExpect(status().isBadRequest());

        List<MChallengeQuestStage> mChallengeQuestStageList = mChallengeQuestStageRepository.findAll();
        assertThat(mChallengeQuestStageList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllMChallengeQuestStages() throws Exception {
        // Initialize the database
        mChallengeQuestStageRepository.saveAndFlush(mChallengeQuestStage);

        // Get all the mChallengeQuestStageList
        restMChallengeQuestStageMockMvc.perform(get("/api/m-challenge-quest-stages?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(mChallengeQuestStage.getId().intValue())))
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
            .andExpect(jsonPath("$.[*].deckConditionId").value(hasItem(DEFAULT_DECK_CONDITION_ID)))
            .andExpect(jsonPath("$.[*].shortName").value(hasItem(DEFAULT_SHORT_NAME.toString())))
            .andExpect(jsonPath("$.[*].skipCheckPoint").value(hasItem(DEFAULT_SKIP_CHECK_POINT)))
            .andExpect(jsonPath("$.[*].roadNumber").value(hasItem(DEFAULT_ROAD_NUMBER)));
    }
    
    @Test
    @Transactional
    public void getMChallengeQuestStage() throws Exception {
        // Initialize the database
        mChallengeQuestStageRepository.saveAndFlush(mChallengeQuestStage);

        // Get the mChallengeQuestStage
        restMChallengeQuestStageMockMvc.perform(get("/api/m-challenge-quest-stages/{id}", mChallengeQuestStage.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(mChallengeQuestStage.getId().intValue()))
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
            .andExpect(jsonPath("$.deckConditionId").value(DEFAULT_DECK_CONDITION_ID))
            .andExpect(jsonPath("$.shortName").value(DEFAULT_SHORT_NAME.toString()))
            .andExpect(jsonPath("$.skipCheckPoint").value(DEFAULT_SKIP_CHECK_POINT))
            .andExpect(jsonPath("$.roadNumber").value(DEFAULT_ROAD_NUMBER));
    }

    @Test
    @Transactional
    public void getAllMChallengeQuestStagesByWorldIdIsEqualToSomething() throws Exception {
        // Initialize the database
        mChallengeQuestStageRepository.saveAndFlush(mChallengeQuestStage);

        // Get all the mChallengeQuestStageList where worldId equals to DEFAULT_WORLD_ID
        defaultMChallengeQuestStageShouldBeFound("worldId.equals=" + DEFAULT_WORLD_ID);

        // Get all the mChallengeQuestStageList where worldId equals to UPDATED_WORLD_ID
        defaultMChallengeQuestStageShouldNotBeFound("worldId.equals=" + UPDATED_WORLD_ID);
    }

    @Test
    @Transactional
    public void getAllMChallengeQuestStagesByWorldIdIsInShouldWork() throws Exception {
        // Initialize the database
        mChallengeQuestStageRepository.saveAndFlush(mChallengeQuestStage);

        // Get all the mChallengeQuestStageList where worldId in DEFAULT_WORLD_ID or UPDATED_WORLD_ID
        defaultMChallengeQuestStageShouldBeFound("worldId.in=" + DEFAULT_WORLD_ID + "," + UPDATED_WORLD_ID);

        // Get all the mChallengeQuestStageList where worldId equals to UPDATED_WORLD_ID
        defaultMChallengeQuestStageShouldNotBeFound("worldId.in=" + UPDATED_WORLD_ID);
    }

    @Test
    @Transactional
    public void getAllMChallengeQuestStagesByWorldIdIsNullOrNotNull() throws Exception {
        // Initialize the database
        mChallengeQuestStageRepository.saveAndFlush(mChallengeQuestStage);

        // Get all the mChallengeQuestStageList where worldId is not null
        defaultMChallengeQuestStageShouldBeFound("worldId.specified=true");

        // Get all the mChallengeQuestStageList where worldId is null
        defaultMChallengeQuestStageShouldNotBeFound("worldId.specified=false");
    }

    @Test
    @Transactional
    public void getAllMChallengeQuestStagesByWorldIdIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mChallengeQuestStageRepository.saveAndFlush(mChallengeQuestStage);

        // Get all the mChallengeQuestStageList where worldId greater than or equals to DEFAULT_WORLD_ID
        defaultMChallengeQuestStageShouldBeFound("worldId.greaterOrEqualThan=" + DEFAULT_WORLD_ID);

        // Get all the mChallengeQuestStageList where worldId greater than or equals to UPDATED_WORLD_ID
        defaultMChallengeQuestStageShouldNotBeFound("worldId.greaterOrEqualThan=" + UPDATED_WORLD_ID);
    }

    @Test
    @Transactional
    public void getAllMChallengeQuestStagesByWorldIdIsLessThanSomething() throws Exception {
        // Initialize the database
        mChallengeQuestStageRepository.saveAndFlush(mChallengeQuestStage);

        // Get all the mChallengeQuestStageList where worldId less than or equals to DEFAULT_WORLD_ID
        defaultMChallengeQuestStageShouldNotBeFound("worldId.lessThan=" + DEFAULT_WORLD_ID);

        // Get all the mChallengeQuestStageList where worldId less than or equals to UPDATED_WORLD_ID
        defaultMChallengeQuestStageShouldBeFound("worldId.lessThan=" + UPDATED_WORLD_ID);
    }


    @Test
    @Transactional
    public void getAllMChallengeQuestStagesByNumberIsEqualToSomething() throws Exception {
        // Initialize the database
        mChallengeQuestStageRepository.saveAndFlush(mChallengeQuestStage);

        // Get all the mChallengeQuestStageList where number equals to DEFAULT_NUMBER
        defaultMChallengeQuestStageShouldBeFound("number.equals=" + DEFAULT_NUMBER);

        // Get all the mChallengeQuestStageList where number equals to UPDATED_NUMBER
        defaultMChallengeQuestStageShouldNotBeFound("number.equals=" + UPDATED_NUMBER);
    }

    @Test
    @Transactional
    public void getAllMChallengeQuestStagesByNumberIsInShouldWork() throws Exception {
        // Initialize the database
        mChallengeQuestStageRepository.saveAndFlush(mChallengeQuestStage);

        // Get all the mChallengeQuestStageList where number in DEFAULT_NUMBER or UPDATED_NUMBER
        defaultMChallengeQuestStageShouldBeFound("number.in=" + DEFAULT_NUMBER + "," + UPDATED_NUMBER);

        // Get all the mChallengeQuestStageList where number equals to UPDATED_NUMBER
        defaultMChallengeQuestStageShouldNotBeFound("number.in=" + UPDATED_NUMBER);
    }

    @Test
    @Transactional
    public void getAllMChallengeQuestStagesByNumberIsNullOrNotNull() throws Exception {
        // Initialize the database
        mChallengeQuestStageRepository.saveAndFlush(mChallengeQuestStage);

        // Get all the mChallengeQuestStageList where number is not null
        defaultMChallengeQuestStageShouldBeFound("number.specified=true");

        // Get all the mChallengeQuestStageList where number is null
        defaultMChallengeQuestStageShouldNotBeFound("number.specified=false");
    }

    @Test
    @Transactional
    public void getAllMChallengeQuestStagesByNumberIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mChallengeQuestStageRepository.saveAndFlush(mChallengeQuestStage);

        // Get all the mChallengeQuestStageList where number greater than or equals to DEFAULT_NUMBER
        defaultMChallengeQuestStageShouldBeFound("number.greaterOrEqualThan=" + DEFAULT_NUMBER);

        // Get all the mChallengeQuestStageList where number greater than or equals to UPDATED_NUMBER
        defaultMChallengeQuestStageShouldNotBeFound("number.greaterOrEqualThan=" + UPDATED_NUMBER);
    }

    @Test
    @Transactional
    public void getAllMChallengeQuestStagesByNumberIsLessThanSomething() throws Exception {
        // Initialize the database
        mChallengeQuestStageRepository.saveAndFlush(mChallengeQuestStage);

        // Get all the mChallengeQuestStageList where number less than or equals to DEFAULT_NUMBER
        defaultMChallengeQuestStageShouldNotBeFound("number.lessThan=" + DEFAULT_NUMBER);

        // Get all the mChallengeQuestStageList where number less than or equals to UPDATED_NUMBER
        defaultMChallengeQuestStageShouldBeFound("number.lessThan=" + UPDATED_NUMBER);
    }


    @Test
    @Transactional
    public void getAllMChallengeQuestStagesByDifficultyIsEqualToSomething() throws Exception {
        // Initialize the database
        mChallengeQuestStageRepository.saveAndFlush(mChallengeQuestStage);

        // Get all the mChallengeQuestStageList where difficulty equals to DEFAULT_DIFFICULTY
        defaultMChallengeQuestStageShouldBeFound("difficulty.equals=" + DEFAULT_DIFFICULTY);

        // Get all the mChallengeQuestStageList where difficulty equals to UPDATED_DIFFICULTY
        defaultMChallengeQuestStageShouldNotBeFound("difficulty.equals=" + UPDATED_DIFFICULTY);
    }

    @Test
    @Transactional
    public void getAllMChallengeQuestStagesByDifficultyIsInShouldWork() throws Exception {
        // Initialize the database
        mChallengeQuestStageRepository.saveAndFlush(mChallengeQuestStage);

        // Get all the mChallengeQuestStageList where difficulty in DEFAULT_DIFFICULTY or UPDATED_DIFFICULTY
        defaultMChallengeQuestStageShouldBeFound("difficulty.in=" + DEFAULT_DIFFICULTY + "," + UPDATED_DIFFICULTY);

        // Get all the mChallengeQuestStageList where difficulty equals to UPDATED_DIFFICULTY
        defaultMChallengeQuestStageShouldNotBeFound("difficulty.in=" + UPDATED_DIFFICULTY);
    }

    @Test
    @Transactional
    public void getAllMChallengeQuestStagesByDifficultyIsNullOrNotNull() throws Exception {
        // Initialize the database
        mChallengeQuestStageRepository.saveAndFlush(mChallengeQuestStage);

        // Get all the mChallengeQuestStageList where difficulty is not null
        defaultMChallengeQuestStageShouldBeFound("difficulty.specified=true");

        // Get all the mChallengeQuestStageList where difficulty is null
        defaultMChallengeQuestStageShouldNotBeFound("difficulty.specified=false");
    }

    @Test
    @Transactional
    public void getAllMChallengeQuestStagesByDifficultyIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mChallengeQuestStageRepository.saveAndFlush(mChallengeQuestStage);

        // Get all the mChallengeQuestStageList where difficulty greater than or equals to DEFAULT_DIFFICULTY
        defaultMChallengeQuestStageShouldBeFound("difficulty.greaterOrEqualThan=" + DEFAULT_DIFFICULTY);

        // Get all the mChallengeQuestStageList where difficulty greater than or equals to UPDATED_DIFFICULTY
        defaultMChallengeQuestStageShouldNotBeFound("difficulty.greaterOrEqualThan=" + UPDATED_DIFFICULTY);
    }

    @Test
    @Transactional
    public void getAllMChallengeQuestStagesByDifficultyIsLessThanSomething() throws Exception {
        // Initialize the database
        mChallengeQuestStageRepository.saveAndFlush(mChallengeQuestStage);

        // Get all the mChallengeQuestStageList where difficulty less than or equals to DEFAULT_DIFFICULTY
        defaultMChallengeQuestStageShouldNotBeFound("difficulty.lessThan=" + DEFAULT_DIFFICULTY);

        // Get all the mChallengeQuestStageList where difficulty less than or equals to UPDATED_DIFFICULTY
        defaultMChallengeQuestStageShouldBeFound("difficulty.lessThan=" + UPDATED_DIFFICULTY);
    }


    @Test
    @Transactional
    public void getAllMChallengeQuestStagesByStageUnlockPatternIsEqualToSomething() throws Exception {
        // Initialize the database
        mChallengeQuestStageRepository.saveAndFlush(mChallengeQuestStage);

        // Get all the mChallengeQuestStageList where stageUnlockPattern equals to DEFAULT_STAGE_UNLOCK_PATTERN
        defaultMChallengeQuestStageShouldBeFound("stageUnlockPattern.equals=" + DEFAULT_STAGE_UNLOCK_PATTERN);

        // Get all the mChallengeQuestStageList where stageUnlockPattern equals to UPDATED_STAGE_UNLOCK_PATTERN
        defaultMChallengeQuestStageShouldNotBeFound("stageUnlockPattern.equals=" + UPDATED_STAGE_UNLOCK_PATTERN);
    }

    @Test
    @Transactional
    public void getAllMChallengeQuestStagesByStageUnlockPatternIsInShouldWork() throws Exception {
        // Initialize the database
        mChallengeQuestStageRepository.saveAndFlush(mChallengeQuestStage);

        // Get all the mChallengeQuestStageList where stageUnlockPattern in DEFAULT_STAGE_UNLOCK_PATTERN or UPDATED_STAGE_UNLOCK_PATTERN
        defaultMChallengeQuestStageShouldBeFound("stageUnlockPattern.in=" + DEFAULT_STAGE_UNLOCK_PATTERN + "," + UPDATED_STAGE_UNLOCK_PATTERN);

        // Get all the mChallengeQuestStageList where stageUnlockPattern equals to UPDATED_STAGE_UNLOCK_PATTERN
        defaultMChallengeQuestStageShouldNotBeFound("stageUnlockPattern.in=" + UPDATED_STAGE_UNLOCK_PATTERN);
    }

    @Test
    @Transactional
    public void getAllMChallengeQuestStagesByStageUnlockPatternIsNullOrNotNull() throws Exception {
        // Initialize the database
        mChallengeQuestStageRepository.saveAndFlush(mChallengeQuestStage);

        // Get all the mChallengeQuestStageList where stageUnlockPattern is not null
        defaultMChallengeQuestStageShouldBeFound("stageUnlockPattern.specified=true");

        // Get all the mChallengeQuestStageList where stageUnlockPattern is null
        defaultMChallengeQuestStageShouldNotBeFound("stageUnlockPattern.specified=false");
    }

    @Test
    @Transactional
    public void getAllMChallengeQuestStagesByStageUnlockPatternIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mChallengeQuestStageRepository.saveAndFlush(mChallengeQuestStage);

        // Get all the mChallengeQuestStageList where stageUnlockPattern greater than or equals to DEFAULT_STAGE_UNLOCK_PATTERN
        defaultMChallengeQuestStageShouldBeFound("stageUnlockPattern.greaterOrEqualThan=" + DEFAULT_STAGE_UNLOCK_PATTERN);

        // Get all the mChallengeQuestStageList where stageUnlockPattern greater than or equals to UPDATED_STAGE_UNLOCK_PATTERN
        defaultMChallengeQuestStageShouldNotBeFound("stageUnlockPattern.greaterOrEqualThan=" + UPDATED_STAGE_UNLOCK_PATTERN);
    }

    @Test
    @Transactional
    public void getAllMChallengeQuestStagesByStageUnlockPatternIsLessThanSomething() throws Exception {
        // Initialize the database
        mChallengeQuestStageRepository.saveAndFlush(mChallengeQuestStage);

        // Get all the mChallengeQuestStageList where stageUnlockPattern less than or equals to DEFAULT_STAGE_UNLOCK_PATTERN
        defaultMChallengeQuestStageShouldNotBeFound("stageUnlockPattern.lessThan=" + DEFAULT_STAGE_UNLOCK_PATTERN);

        // Get all the mChallengeQuestStageList where stageUnlockPattern less than or equals to UPDATED_STAGE_UNLOCK_PATTERN
        defaultMChallengeQuestStageShouldBeFound("stageUnlockPattern.lessThan=" + UPDATED_STAGE_UNLOCK_PATTERN);
    }


    @Test
    @Transactional
    public void getAllMChallengeQuestStagesByStoryOnlyIsEqualToSomething() throws Exception {
        // Initialize the database
        mChallengeQuestStageRepository.saveAndFlush(mChallengeQuestStage);

        // Get all the mChallengeQuestStageList where storyOnly equals to DEFAULT_STORY_ONLY
        defaultMChallengeQuestStageShouldBeFound("storyOnly.equals=" + DEFAULT_STORY_ONLY);

        // Get all the mChallengeQuestStageList where storyOnly equals to UPDATED_STORY_ONLY
        defaultMChallengeQuestStageShouldNotBeFound("storyOnly.equals=" + UPDATED_STORY_ONLY);
    }

    @Test
    @Transactional
    public void getAllMChallengeQuestStagesByStoryOnlyIsInShouldWork() throws Exception {
        // Initialize the database
        mChallengeQuestStageRepository.saveAndFlush(mChallengeQuestStage);

        // Get all the mChallengeQuestStageList where storyOnly in DEFAULT_STORY_ONLY or UPDATED_STORY_ONLY
        defaultMChallengeQuestStageShouldBeFound("storyOnly.in=" + DEFAULT_STORY_ONLY + "," + UPDATED_STORY_ONLY);

        // Get all the mChallengeQuestStageList where storyOnly equals to UPDATED_STORY_ONLY
        defaultMChallengeQuestStageShouldNotBeFound("storyOnly.in=" + UPDATED_STORY_ONLY);
    }

    @Test
    @Transactional
    public void getAllMChallengeQuestStagesByStoryOnlyIsNullOrNotNull() throws Exception {
        // Initialize the database
        mChallengeQuestStageRepository.saveAndFlush(mChallengeQuestStage);

        // Get all the mChallengeQuestStageList where storyOnly is not null
        defaultMChallengeQuestStageShouldBeFound("storyOnly.specified=true");

        // Get all the mChallengeQuestStageList where storyOnly is null
        defaultMChallengeQuestStageShouldNotBeFound("storyOnly.specified=false");
    }

    @Test
    @Transactional
    public void getAllMChallengeQuestStagesByStoryOnlyIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mChallengeQuestStageRepository.saveAndFlush(mChallengeQuestStage);

        // Get all the mChallengeQuestStageList where storyOnly greater than or equals to DEFAULT_STORY_ONLY
        defaultMChallengeQuestStageShouldBeFound("storyOnly.greaterOrEqualThan=" + DEFAULT_STORY_ONLY);

        // Get all the mChallengeQuestStageList where storyOnly greater than or equals to UPDATED_STORY_ONLY
        defaultMChallengeQuestStageShouldNotBeFound("storyOnly.greaterOrEqualThan=" + UPDATED_STORY_ONLY);
    }

    @Test
    @Transactional
    public void getAllMChallengeQuestStagesByStoryOnlyIsLessThanSomething() throws Exception {
        // Initialize the database
        mChallengeQuestStageRepository.saveAndFlush(mChallengeQuestStage);

        // Get all the mChallengeQuestStageList where storyOnly less than or equals to DEFAULT_STORY_ONLY
        defaultMChallengeQuestStageShouldNotBeFound("storyOnly.lessThan=" + DEFAULT_STORY_ONLY);

        // Get all the mChallengeQuestStageList where storyOnly less than or equals to UPDATED_STORY_ONLY
        defaultMChallengeQuestStageShouldBeFound("storyOnly.lessThan=" + UPDATED_STORY_ONLY);
    }


    @Test
    @Transactional
    public void getAllMChallengeQuestStagesByFirstHalfNpcDeckIdIsEqualToSomething() throws Exception {
        // Initialize the database
        mChallengeQuestStageRepository.saveAndFlush(mChallengeQuestStage);

        // Get all the mChallengeQuestStageList where firstHalfNpcDeckId equals to DEFAULT_FIRST_HALF_NPC_DECK_ID
        defaultMChallengeQuestStageShouldBeFound("firstHalfNpcDeckId.equals=" + DEFAULT_FIRST_HALF_NPC_DECK_ID);

        // Get all the mChallengeQuestStageList where firstHalfNpcDeckId equals to UPDATED_FIRST_HALF_NPC_DECK_ID
        defaultMChallengeQuestStageShouldNotBeFound("firstHalfNpcDeckId.equals=" + UPDATED_FIRST_HALF_NPC_DECK_ID);
    }

    @Test
    @Transactional
    public void getAllMChallengeQuestStagesByFirstHalfNpcDeckIdIsInShouldWork() throws Exception {
        // Initialize the database
        mChallengeQuestStageRepository.saveAndFlush(mChallengeQuestStage);

        // Get all the mChallengeQuestStageList where firstHalfNpcDeckId in DEFAULT_FIRST_HALF_NPC_DECK_ID or UPDATED_FIRST_HALF_NPC_DECK_ID
        defaultMChallengeQuestStageShouldBeFound("firstHalfNpcDeckId.in=" + DEFAULT_FIRST_HALF_NPC_DECK_ID + "," + UPDATED_FIRST_HALF_NPC_DECK_ID);

        // Get all the mChallengeQuestStageList where firstHalfNpcDeckId equals to UPDATED_FIRST_HALF_NPC_DECK_ID
        defaultMChallengeQuestStageShouldNotBeFound("firstHalfNpcDeckId.in=" + UPDATED_FIRST_HALF_NPC_DECK_ID);
    }

    @Test
    @Transactional
    public void getAllMChallengeQuestStagesByFirstHalfNpcDeckIdIsNullOrNotNull() throws Exception {
        // Initialize the database
        mChallengeQuestStageRepository.saveAndFlush(mChallengeQuestStage);

        // Get all the mChallengeQuestStageList where firstHalfNpcDeckId is not null
        defaultMChallengeQuestStageShouldBeFound("firstHalfNpcDeckId.specified=true");

        // Get all the mChallengeQuestStageList where firstHalfNpcDeckId is null
        defaultMChallengeQuestStageShouldNotBeFound("firstHalfNpcDeckId.specified=false");
    }

    @Test
    @Transactional
    public void getAllMChallengeQuestStagesByFirstHalfNpcDeckIdIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mChallengeQuestStageRepository.saveAndFlush(mChallengeQuestStage);

        // Get all the mChallengeQuestStageList where firstHalfNpcDeckId greater than or equals to DEFAULT_FIRST_HALF_NPC_DECK_ID
        defaultMChallengeQuestStageShouldBeFound("firstHalfNpcDeckId.greaterOrEqualThan=" + DEFAULT_FIRST_HALF_NPC_DECK_ID);

        // Get all the mChallengeQuestStageList where firstHalfNpcDeckId greater than or equals to UPDATED_FIRST_HALF_NPC_DECK_ID
        defaultMChallengeQuestStageShouldNotBeFound("firstHalfNpcDeckId.greaterOrEqualThan=" + UPDATED_FIRST_HALF_NPC_DECK_ID);
    }

    @Test
    @Transactional
    public void getAllMChallengeQuestStagesByFirstHalfNpcDeckIdIsLessThanSomething() throws Exception {
        // Initialize the database
        mChallengeQuestStageRepository.saveAndFlush(mChallengeQuestStage);

        // Get all the mChallengeQuestStageList where firstHalfNpcDeckId less than or equals to DEFAULT_FIRST_HALF_NPC_DECK_ID
        defaultMChallengeQuestStageShouldNotBeFound("firstHalfNpcDeckId.lessThan=" + DEFAULT_FIRST_HALF_NPC_DECK_ID);

        // Get all the mChallengeQuestStageList where firstHalfNpcDeckId less than or equals to UPDATED_FIRST_HALF_NPC_DECK_ID
        defaultMChallengeQuestStageShouldBeFound("firstHalfNpcDeckId.lessThan=" + UPDATED_FIRST_HALF_NPC_DECK_ID);
    }


    @Test
    @Transactional
    public void getAllMChallengeQuestStagesByFirstHalfEnvironmentIdIsEqualToSomething() throws Exception {
        // Initialize the database
        mChallengeQuestStageRepository.saveAndFlush(mChallengeQuestStage);

        // Get all the mChallengeQuestStageList where firstHalfEnvironmentId equals to DEFAULT_FIRST_HALF_ENVIRONMENT_ID
        defaultMChallengeQuestStageShouldBeFound("firstHalfEnvironmentId.equals=" + DEFAULT_FIRST_HALF_ENVIRONMENT_ID);

        // Get all the mChallengeQuestStageList where firstHalfEnvironmentId equals to UPDATED_FIRST_HALF_ENVIRONMENT_ID
        defaultMChallengeQuestStageShouldNotBeFound("firstHalfEnvironmentId.equals=" + UPDATED_FIRST_HALF_ENVIRONMENT_ID);
    }

    @Test
    @Transactional
    public void getAllMChallengeQuestStagesByFirstHalfEnvironmentIdIsInShouldWork() throws Exception {
        // Initialize the database
        mChallengeQuestStageRepository.saveAndFlush(mChallengeQuestStage);

        // Get all the mChallengeQuestStageList where firstHalfEnvironmentId in DEFAULT_FIRST_HALF_ENVIRONMENT_ID or UPDATED_FIRST_HALF_ENVIRONMENT_ID
        defaultMChallengeQuestStageShouldBeFound("firstHalfEnvironmentId.in=" + DEFAULT_FIRST_HALF_ENVIRONMENT_ID + "," + UPDATED_FIRST_HALF_ENVIRONMENT_ID);

        // Get all the mChallengeQuestStageList where firstHalfEnvironmentId equals to UPDATED_FIRST_HALF_ENVIRONMENT_ID
        defaultMChallengeQuestStageShouldNotBeFound("firstHalfEnvironmentId.in=" + UPDATED_FIRST_HALF_ENVIRONMENT_ID);
    }

    @Test
    @Transactional
    public void getAllMChallengeQuestStagesByFirstHalfEnvironmentIdIsNullOrNotNull() throws Exception {
        // Initialize the database
        mChallengeQuestStageRepository.saveAndFlush(mChallengeQuestStage);

        // Get all the mChallengeQuestStageList where firstHalfEnvironmentId is not null
        defaultMChallengeQuestStageShouldBeFound("firstHalfEnvironmentId.specified=true");

        // Get all the mChallengeQuestStageList where firstHalfEnvironmentId is null
        defaultMChallengeQuestStageShouldNotBeFound("firstHalfEnvironmentId.specified=false");
    }

    @Test
    @Transactional
    public void getAllMChallengeQuestStagesByFirstHalfEnvironmentIdIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mChallengeQuestStageRepository.saveAndFlush(mChallengeQuestStage);

        // Get all the mChallengeQuestStageList where firstHalfEnvironmentId greater than or equals to DEFAULT_FIRST_HALF_ENVIRONMENT_ID
        defaultMChallengeQuestStageShouldBeFound("firstHalfEnvironmentId.greaterOrEqualThan=" + DEFAULT_FIRST_HALF_ENVIRONMENT_ID);

        // Get all the mChallengeQuestStageList where firstHalfEnvironmentId greater than or equals to UPDATED_FIRST_HALF_ENVIRONMENT_ID
        defaultMChallengeQuestStageShouldNotBeFound("firstHalfEnvironmentId.greaterOrEqualThan=" + UPDATED_FIRST_HALF_ENVIRONMENT_ID);
    }

    @Test
    @Transactional
    public void getAllMChallengeQuestStagesByFirstHalfEnvironmentIdIsLessThanSomething() throws Exception {
        // Initialize the database
        mChallengeQuestStageRepository.saveAndFlush(mChallengeQuestStage);

        // Get all the mChallengeQuestStageList where firstHalfEnvironmentId less than or equals to DEFAULT_FIRST_HALF_ENVIRONMENT_ID
        defaultMChallengeQuestStageShouldNotBeFound("firstHalfEnvironmentId.lessThan=" + DEFAULT_FIRST_HALF_ENVIRONMENT_ID);

        // Get all the mChallengeQuestStageList where firstHalfEnvironmentId less than or equals to UPDATED_FIRST_HALF_ENVIRONMENT_ID
        defaultMChallengeQuestStageShouldBeFound("firstHalfEnvironmentId.lessThan=" + UPDATED_FIRST_HALF_ENVIRONMENT_ID);
    }


    @Test
    @Transactional
    public void getAllMChallengeQuestStagesBySecondHalfNpcDeckIdIsEqualToSomething() throws Exception {
        // Initialize the database
        mChallengeQuestStageRepository.saveAndFlush(mChallengeQuestStage);

        // Get all the mChallengeQuestStageList where secondHalfNpcDeckId equals to DEFAULT_SECOND_HALF_NPC_DECK_ID
        defaultMChallengeQuestStageShouldBeFound("secondHalfNpcDeckId.equals=" + DEFAULT_SECOND_HALF_NPC_DECK_ID);

        // Get all the mChallengeQuestStageList where secondHalfNpcDeckId equals to UPDATED_SECOND_HALF_NPC_DECK_ID
        defaultMChallengeQuestStageShouldNotBeFound("secondHalfNpcDeckId.equals=" + UPDATED_SECOND_HALF_NPC_DECK_ID);
    }

    @Test
    @Transactional
    public void getAllMChallengeQuestStagesBySecondHalfNpcDeckIdIsInShouldWork() throws Exception {
        // Initialize the database
        mChallengeQuestStageRepository.saveAndFlush(mChallengeQuestStage);

        // Get all the mChallengeQuestStageList where secondHalfNpcDeckId in DEFAULT_SECOND_HALF_NPC_DECK_ID or UPDATED_SECOND_HALF_NPC_DECK_ID
        defaultMChallengeQuestStageShouldBeFound("secondHalfNpcDeckId.in=" + DEFAULT_SECOND_HALF_NPC_DECK_ID + "," + UPDATED_SECOND_HALF_NPC_DECK_ID);

        // Get all the mChallengeQuestStageList where secondHalfNpcDeckId equals to UPDATED_SECOND_HALF_NPC_DECK_ID
        defaultMChallengeQuestStageShouldNotBeFound("secondHalfNpcDeckId.in=" + UPDATED_SECOND_HALF_NPC_DECK_ID);
    }

    @Test
    @Transactional
    public void getAllMChallengeQuestStagesBySecondHalfNpcDeckIdIsNullOrNotNull() throws Exception {
        // Initialize the database
        mChallengeQuestStageRepository.saveAndFlush(mChallengeQuestStage);

        // Get all the mChallengeQuestStageList where secondHalfNpcDeckId is not null
        defaultMChallengeQuestStageShouldBeFound("secondHalfNpcDeckId.specified=true");

        // Get all the mChallengeQuestStageList where secondHalfNpcDeckId is null
        defaultMChallengeQuestStageShouldNotBeFound("secondHalfNpcDeckId.specified=false");
    }

    @Test
    @Transactional
    public void getAllMChallengeQuestStagesBySecondHalfNpcDeckIdIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mChallengeQuestStageRepository.saveAndFlush(mChallengeQuestStage);

        // Get all the mChallengeQuestStageList where secondHalfNpcDeckId greater than or equals to DEFAULT_SECOND_HALF_NPC_DECK_ID
        defaultMChallengeQuestStageShouldBeFound("secondHalfNpcDeckId.greaterOrEqualThan=" + DEFAULT_SECOND_HALF_NPC_DECK_ID);

        // Get all the mChallengeQuestStageList where secondHalfNpcDeckId greater than or equals to UPDATED_SECOND_HALF_NPC_DECK_ID
        defaultMChallengeQuestStageShouldNotBeFound("secondHalfNpcDeckId.greaterOrEqualThan=" + UPDATED_SECOND_HALF_NPC_DECK_ID);
    }

    @Test
    @Transactional
    public void getAllMChallengeQuestStagesBySecondHalfNpcDeckIdIsLessThanSomething() throws Exception {
        // Initialize the database
        mChallengeQuestStageRepository.saveAndFlush(mChallengeQuestStage);

        // Get all the mChallengeQuestStageList where secondHalfNpcDeckId less than or equals to DEFAULT_SECOND_HALF_NPC_DECK_ID
        defaultMChallengeQuestStageShouldNotBeFound("secondHalfNpcDeckId.lessThan=" + DEFAULT_SECOND_HALF_NPC_DECK_ID);

        // Get all the mChallengeQuestStageList where secondHalfNpcDeckId less than or equals to UPDATED_SECOND_HALF_NPC_DECK_ID
        defaultMChallengeQuestStageShouldBeFound("secondHalfNpcDeckId.lessThan=" + UPDATED_SECOND_HALF_NPC_DECK_ID);
    }


    @Test
    @Transactional
    public void getAllMChallengeQuestStagesBySecondHalfEnvironmentIdIsEqualToSomething() throws Exception {
        // Initialize the database
        mChallengeQuestStageRepository.saveAndFlush(mChallengeQuestStage);

        // Get all the mChallengeQuestStageList where secondHalfEnvironmentId equals to DEFAULT_SECOND_HALF_ENVIRONMENT_ID
        defaultMChallengeQuestStageShouldBeFound("secondHalfEnvironmentId.equals=" + DEFAULT_SECOND_HALF_ENVIRONMENT_ID);

        // Get all the mChallengeQuestStageList where secondHalfEnvironmentId equals to UPDATED_SECOND_HALF_ENVIRONMENT_ID
        defaultMChallengeQuestStageShouldNotBeFound("secondHalfEnvironmentId.equals=" + UPDATED_SECOND_HALF_ENVIRONMENT_ID);
    }

    @Test
    @Transactional
    public void getAllMChallengeQuestStagesBySecondHalfEnvironmentIdIsInShouldWork() throws Exception {
        // Initialize the database
        mChallengeQuestStageRepository.saveAndFlush(mChallengeQuestStage);

        // Get all the mChallengeQuestStageList where secondHalfEnvironmentId in DEFAULT_SECOND_HALF_ENVIRONMENT_ID or UPDATED_SECOND_HALF_ENVIRONMENT_ID
        defaultMChallengeQuestStageShouldBeFound("secondHalfEnvironmentId.in=" + DEFAULT_SECOND_HALF_ENVIRONMENT_ID + "," + UPDATED_SECOND_HALF_ENVIRONMENT_ID);

        // Get all the mChallengeQuestStageList where secondHalfEnvironmentId equals to UPDATED_SECOND_HALF_ENVIRONMENT_ID
        defaultMChallengeQuestStageShouldNotBeFound("secondHalfEnvironmentId.in=" + UPDATED_SECOND_HALF_ENVIRONMENT_ID);
    }

    @Test
    @Transactional
    public void getAllMChallengeQuestStagesBySecondHalfEnvironmentIdIsNullOrNotNull() throws Exception {
        // Initialize the database
        mChallengeQuestStageRepository.saveAndFlush(mChallengeQuestStage);

        // Get all the mChallengeQuestStageList where secondHalfEnvironmentId is not null
        defaultMChallengeQuestStageShouldBeFound("secondHalfEnvironmentId.specified=true");

        // Get all the mChallengeQuestStageList where secondHalfEnvironmentId is null
        defaultMChallengeQuestStageShouldNotBeFound("secondHalfEnvironmentId.specified=false");
    }

    @Test
    @Transactional
    public void getAllMChallengeQuestStagesBySecondHalfEnvironmentIdIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mChallengeQuestStageRepository.saveAndFlush(mChallengeQuestStage);

        // Get all the mChallengeQuestStageList where secondHalfEnvironmentId greater than or equals to DEFAULT_SECOND_HALF_ENVIRONMENT_ID
        defaultMChallengeQuestStageShouldBeFound("secondHalfEnvironmentId.greaterOrEqualThan=" + DEFAULT_SECOND_HALF_ENVIRONMENT_ID);

        // Get all the mChallengeQuestStageList where secondHalfEnvironmentId greater than or equals to UPDATED_SECOND_HALF_ENVIRONMENT_ID
        defaultMChallengeQuestStageShouldNotBeFound("secondHalfEnvironmentId.greaterOrEqualThan=" + UPDATED_SECOND_HALF_ENVIRONMENT_ID);
    }

    @Test
    @Transactional
    public void getAllMChallengeQuestStagesBySecondHalfEnvironmentIdIsLessThanSomething() throws Exception {
        // Initialize the database
        mChallengeQuestStageRepository.saveAndFlush(mChallengeQuestStage);

        // Get all the mChallengeQuestStageList where secondHalfEnvironmentId less than or equals to DEFAULT_SECOND_HALF_ENVIRONMENT_ID
        defaultMChallengeQuestStageShouldNotBeFound("secondHalfEnvironmentId.lessThan=" + DEFAULT_SECOND_HALF_ENVIRONMENT_ID);

        // Get all the mChallengeQuestStageList where secondHalfEnvironmentId less than or equals to UPDATED_SECOND_HALF_ENVIRONMENT_ID
        defaultMChallengeQuestStageShouldBeFound("secondHalfEnvironmentId.lessThan=" + UPDATED_SECOND_HALF_ENVIRONMENT_ID);
    }


    @Test
    @Transactional
    public void getAllMChallengeQuestStagesByExtraFirstHalfNpcDeckIdIsEqualToSomething() throws Exception {
        // Initialize the database
        mChallengeQuestStageRepository.saveAndFlush(mChallengeQuestStage);

        // Get all the mChallengeQuestStageList where extraFirstHalfNpcDeckId equals to DEFAULT_EXTRA_FIRST_HALF_NPC_DECK_ID
        defaultMChallengeQuestStageShouldBeFound("extraFirstHalfNpcDeckId.equals=" + DEFAULT_EXTRA_FIRST_HALF_NPC_DECK_ID);

        // Get all the mChallengeQuestStageList where extraFirstHalfNpcDeckId equals to UPDATED_EXTRA_FIRST_HALF_NPC_DECK_ID
        defaultMChallengeQuestStageShouldNotBeFound("extraFirstHalfNpcDeckId.equals=" + UPDATED_EXTRA_FIRST_HALF_NPC_DECK_ID);
    }

    @Test
    @Transactional
    public void getAllMChallengeQuestStagesByExtraFirstHalfNpcDeckIdIsInShouldWork() throws Exception {
        // Initialize the database
        mChallengeQuestStageRepository.saveAndFlush(mChallengeQuestStage);

        // Get all the mChallengeQuestStageList where extraFirstHalfNpcDeckId in DEFAULT_EXTRA_FIRST_HALF_NPC_DECK_ID or UPDATED_EXTRA_FIRST_HALF_NPC_DECK_ID
        defaultMChallengeQuestStageShouldBeFound("extraFirstHalfNpcDeckId.in=" + DEFAULT_EXTRA_FIRST_HALF_NPC_DECK_ID + "," + UPDATED_EXTRA_FIRST_HALF_NPC_DECK_ID);

        // Get all the mChallengeQuestStageList where extraFirstHalfNpcDeckId equals to UPDATED_EXTRA_FIRST_HALF_NPC_DECK_ID
        defaultMChallengeQuestStageShouldNotBeFound("extraFirstHalfNpcDeckId.in=" + UPDATED_EXTRA_FIRST_HALF_NPC_DECK_ID);
    }

    @Test
    @Transactional
    public void getAllMChallengeQuestStagesByExtraFirstHalfNpcDeckIdIsNullOrNotNull() throws Exception {
        // Initialize the database
        mChallengeQuestStageRepository.saveAndFlush(mChallengeQuestStage);

        // Get all the mChallengeQuestStageList where extraFirstHalfNpcDeckId is not null
        defaultMChallengeQuestStageShouldBeFound("extraFirstHalfNpcDeckId.specified=true");

        // Get all the mChallengeQuestStageList where extraFirstHalfNpcDeckId is null
        defaultMChallengeQuestStageShouldNotBeFound("extraFirstHalfNpcDeckId.specified=false");
    }

    @Test
    @Transactional
    public void getAllMChallengeQuestStagesByExtraFirstHalfNpcDeckIdIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mChallengeQuestStageRepository.saveAndFlush(mChallengeQuestStage);

        // Get all the mChallengeQuestStageList where extraFirstHalfNpcDeckId greater than or equals to DEFAULT_EXTRA_FIRST_HALF_NPC_DECK_ID
        defaultMChallengeQuestStageShouldBeFound("extraFirstHalfNpcDeckId.greaterOrEqualThan=" + DEFAULT_EXTRA_FIRST_HALF_NPC_DECK_ID);

        // Get all the mChallengeQuestStageList where extraFirstHalfNpcDeckId greater than or equals to UPDATED_EXTRA_FIRST_HALF_NPC_DECK_ID
        defaultMChallengeQuestStageShouldNotBeFound("extraFirstHalfNpcDeckId.greaterOrEqualThan=" + UPDATED_EXTRA_FIRST_HALF_NPC_DECK_ID);
    }

    @Test
    @Transactional
    public void getAllMChallengeQuestStagesByExtraFirstHalfNpcDeckIdIsLessThanSomething() throws Exception {
        // Initialize the database
        mChallengeQuestStageRepository.saveAndFlush(mChallengeQuestStage);

        // Get all the mChallengeQuestStageList where extraFirstHalfNpcDeckId less than or equals to DEFAULT_EXTRA_FIRST_HALF_NPC_DECK_ID
        defaultMChallengeQuestStageShouldNotBeFound("extraFirstHalfNpcDeckId.lessThan=" + DEFAULT_EXTRA_FIRST_HALF_NPC_DECK_ID);

        // Get all the mChallengeQuestStageList where extraFirstHalfNpcDeckId less than or equals to UPDATED_EXTRA_FIRST_HALF_NPC_DECK_ID
        defaultMChallengeQuestStageShouldBeFound("extraFirstHalfNpcDeckId.lessThan=" + UPDATED_EXTRA_FIRST_HALF_NPC_DECK_ID);
    }


    @Test
    @Transactional
    public void getAllMChallengeQuestStagesByConsumeApIsEqualToSomething() throws Exception {
        // Initialize the database
        mChallengeQuestStageRepository.saveAndFlush(mChallengeQuestStage);

        // Get all the mChallengeQuestStageList where consumeAp equals to DEFAULT_CONSUME_AP
        defaultMChallengeQuestStageShouldBeFound("consumeAp.equals=" + DEFAULT_CONSUME_AP);

        // Get all the mChallengeQuestStageList where consumeAp equals to UPDATED_CONSUME_AP
        defaultMChallengeQuestStageShouldNotBeFound("consumeAp.equals=" + UPDATED_CONSUME_AP);
    }

    @Test
    @Transactional
    public void getAllMChallengeQuestStagesByConsumeApIsInShouldWork() throws Exception {
        // Initialize the database
        mChallengeQuestStageRepository.saveAndFlush(mChallengeQuestStage);

        // Get all the mChallengeQuestStageList where consumeAp in DEFAULT_CONSUME_AP or UPDATED_CONSUME_AP
        defaultMChallengeQuestStageShouldBeFound("consumeAp.in=" + DEFAULT_CONSUME_AP + "," + UPDATED_CONSUME_AP);

        // Get all the mChallengeQuestStageList where consumeAp equals to UPDATED_CONSUME_AP
        defaultMChallengeQuestStageShouldNotBeFound("consumeAp.in=" + UPDATED_CONSUME_AP);
    }

    @Test
    @Transactional
    public void getAllMChallengeQuestStagesByConsumeApIsNullOrNotNull() throws Exception {
        // Initialize the database
        mChallengeQuestStageRepository.saveAndFlush(mChallengeQuestStage);

        // Get all the mChallengeQuestStageList where consumeAp is not null
        defaultMChallengeQuestStageShouldBeFound("consumeAp.specified=true");

        // Get all the mChallengeQuestStageList where consumeAp is null
        defaultMChallengeQuestStageShouldNotBeFound("consumeAp.specified=false");
    }

    @Test
    @Transactional
    public void getAllMChallengeQuestStagesByConsumeApIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mChallengeQuestStageRepository.saveAndFlush(mChallengeQuestStage);

        // Get all the mChallengeQuestStageList where consumeAp greater than or equals to DEFAULT_CONSUME_AP
        defaultMChallengeQuestStageShouldBeFound("consumeAp.greaterOrEqualThan=" + DEFAULT_CONSUME_AP);

        // Get all the mChallengeQuestStageList where consumeAp greater than or equals to UPDATED_CONSUME_AP
        defaultMChallengeQuestStageShouldNotBeFound("consumeAp.greaterOrEqualThan=" + UPDATED_CONSUME_AP);
    }

    @Test
    @Transactional
    public void getAllMChallengeQuestStagesByConsumeApIsLessThanSomething() throws Exception {
        // Initialize the database
        mChallengeQuestStageRepository.saveAndFlush(mChallengeQuestStage);

        // Get all the mChallengeQuestStageList where consumeAp less than or equals to DEFAULT_CONSUME_AP
        defaultMChallengeQuestStageShouldNotBeFound("consumeAp.lessThan=" + DEFAULT_CONSUME_AP);

        // Get all the mChallengeQuestStageList where consumeAp less than or equals to UPDATED_CONSUME_AP
        defaultMChallengeQuestStageShouldBeFound("consumeAp.lessThan=" + UPDATED_CONSUME_AP);
    }


    @Test
    @Transactional
    public void getAllMChallengeQuestStagesByKickOffTypeIsEqualToSomething() throws Exception {
        // Initialize the database
        mChallengeQuestStageRepository.saveAndFlush(mChallengeQuestStage);

        // Get all the mChallengeQuestStageList where kickOffType equals to DEFAULT_KICK_OFF_TYPE
        defaultMChallengeQuestStageShouldBeFound("kickOffType.equals=" + DEFAULT_KICK_OFF_TYPE);

        // Get all the mChallengeQuestStageList where kickOffType equals to UPDATED_KICK_OFF_TYPE
        defaultMChallengeQuestStageShouldNotBeFound("kickOffType.equals=" + UPDATED_KICK_OFF_TYPE);
    }

    @Test
    @Transactional
    public void getAllMChallengeQuestStagesByKickOffTypeIsInShouldWork() throws Exception {
        // Initialize the database
        mChallengeQuestStageRepository.saveAndFlush(mChallengeQuestStage);

        // Get all the mChallengeQuestStageList where kickOffType in DEFAULT_KICK_OFF_TYPE or UPDATED_KICK_OFF_TYPE
        defaultMChallengeQuestStageShouldBeFound("kickOffType.in=" + DEFAULT_KICK_OFF_TYPE + "," + UPDATED_KICK_OFF_TYPE);

        // Get all the mChallengeQuestStageList where kickOffType equals to UPDATED_KICK_OFF_TYPE
        defaultMChallengeQuestStageShouldNotBeFound("kickOffType.in=" + UPDATED_KICK_OFF_TYPE);
    }

    @Test
    @Transactional
    public void getAllMChallengeQuestStagesByKickOffTypeIsNullOrNotNull() throws Exception {
        // Initialize the database
        mChallengeQuestStageRepository.saveAndFlush(mChallengeQuestStage);

        // Get all the mChallengeQuestStageList where kickOffType is not null
        defaultMChallengeQuestStageShouldBeFound("kickOffType.specified=true");

        // Get all the mChallengeQuestStageList where kickOffType is null
        defaultMChallengeQuestStageShouldNotBeFound("kickOffType.specified=false");
    }

    @Test
    @Transactional
    public void getAllMChallengeQuestStagesByKickOffTypeIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mChallengeQuestStageRepository.saveAndFlush(mChallengeQuestStage);

        // Get all the mChallengeQuestStageList where kickOffType greater than or equals to DEFAULT_KICK_OFF_TYPE
        defaultMChallengeQuestStageShouldBeFound("kickOffType.greaterOrEqualThan=" + DEFAULT_KICK_OFF_TYPE);

        // Get all the mChallengeQuestStageList where kickOffType greater than or equals to UPDATED_KICK_OFF_TYPE
        defaultMChallengeQuestStageShouldNotBeFound("kickOffType.greaterOrEqualThan=" + UPDATED_KICK_OFF_TYPE);
    }

    @Test
    @Transactional
    public void getAllMChallengeQuestStagesByKickOffTypeIsLessThanSomething() throws Exception {
        // Initialize the database
        mChallengeQuestStageRepository.saveAndFlush(mChallengeQuestStage);

        // Get all the mChallengeQuestStageList where kickOffType less than or equals to DEFAULT_KICK_OFF_TYPE
        defaultMChallengeQuestStageShouldNotBeFound("kickOffType.lessThan=" + DEFAULT_KICK_OFF_TYPE);

        // Get all the mChallengeQuestStageList where kickOffType less than or equals to UPDATED_KICK_OFF_TYPE
        defaultMChallengeQuestStageShouldBeFound("kickOffType.lessThan=" + UPDATED_KICK_OFF_TYPE);
    }


    @Test
    @Transactional
    public void getAllMChallengeQuestStagesByMatchMinuteIsEqualToSomething() throws Exception {
        // Initialize the database
        mChallengeQuestStageRepository.saveAndFlush(mChallengeQuestStage);

        // Get all the mChallengeQuestStageList where matchMinute equals to DEFAULT_MATCH_MINUTE
        defaultMChallengeQuestStageShouldBeFound("matchMinute.equals=" + DEFAULT_MATCH_MINUTE);

        // Get all the mChallengeQuestStageList where matchMinute equals to UPDATED_MATCH_MINUTE
        defaultMChallengeQuestStageShouldNotBeFound("matchMinute.equals=" + UPDATED_MATCH_MINUTE);
    }

    @Test
    @Transactional
    public void getAllMChallengeQuestStagesByMatchMinuteIsInShouldWork() throws Exception {
        // Initialize the database
        mChallengeQuestStageRepository.saveAndFlush(mChallengeQuestStage);

        // Get all the mChallengeQuestStageList where matchMinute in DEFAULT_MATCH_MINUTE or UPDATED_MATCH_MINUTE
        defaultMChallengeQuestStageShouldBeFound("matchMinute.in=" + DEFAULT_MATCH_MINUTE + "," + UPDATED_MATCH_MINUTE);

        // Get all the mChallengeQuestStageList where matchMinute equals to UPDATED_MATCH_MINUTE
        defaultMChallengeQuestStageShouldNotBeFound("matchMinute.in=" + UPDATED_MATCH_MINUTE);
    }

    @Test
    @Transactional
    public void getAllMChallengeQuestStagesByMatchMinuteIsNullOrNotNull() throws Exception {
        // Initialize the database
        mChallengeQuestStageRepository.saveAndFlush(mChallengeQuestStage);

        // Get all the mChallengeQuestStageList where matchMinute is not null
        defaultMChallengeQuestStageShouldBeFound("matchMinute.specified=true");

        // Get all the mChallengeQuestStageList where matchMinute is null
        defaultMChallengeQuestStageShouldNotBeFound("matchMinute.specified=false");
    }

    @Test
    @Transactional
    public void getAllMChallengeQuestStagesByMatchMinuteIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mChallengeQuestStageRepository.saveAndFlush(mChallengeQuestStage);

        // Get all the mChallengeQuestStageList where matchMinute greater than or equals to DEFAULT_MATCH_MINUTE
        defaultMChallengeQuestStageShouldBeFound("matchMinute.greaterOrEqualThan=" + DEFAULT_MATCH_MINUTE);

        // Get all the mChallengeQuestStageList where matchMinute greater than or equals to UPDATED_MATCH_MINUTE
        defaultMChallengeQuestStageShouldNotBeFound("matchMinute.greaterOrEqualThan=" + UPDATED_MATCH_MINUTE);
    }

    @Test
    @Transactional
    public void getAllMChallengeQuestStagesByMatchMinuteIsLessThanSomething() throws Exception {
        // Initialize the database
        mChallengeQuestStageRepository.saveAndFlush(mChallengeQuestStage);

        // Get all the mChallengeQuestStageList where matchMinute less than or equals to DEFAULT_MATCH_MINUTE
        defaultMChallengeQuestStageShouldNotBeFound("matchMinute.lessThan=" + DEFAULT_MATCH_MINUTE);

        // Get all the mChallengeQuestStageList where matchMinute less than or equals to UPDATED_MATCH_MINUTE
        defaultMChallengeQuestStageShouldBeFound("matchMinute.lessThan=" + UPDATED_MATCH_MINUTE);
    }


    @Test
    @Transactional
    public void getAllMChallengeQuestStagesByEnableExtraIsEqualToSomething() throws Exception {
        // Initialize the database
        mChallengeQuestStageRepository.saveAndFlush(mChallengeQuestStage);

        // Get all the mChallengeQuestStageList where enableExtra equals to DEFAULT_ENABLE_EXTRA
        defaultMChallengeQuestStageShouldBeFound("enableExtra.equals=" + DEFAULT_ENABLE_EXTRA);

        // Get all the mChallengeQuestStageList where enableExtra equals to UPDATED_ENABLE_EXTRA
        defaultMChallengeQuestStageShouldNotBeFound("enableExtra.equals=" + UPDATED_ENABLE_EXTRA);
    }

    @Test
    @Transactional
    public void getAllMChallengeQuestStagesByEnableExtraIsInShouldWork() throws Exception {
        // Initialize the database
        mChallengeQuestStageRepository.saveAndFlush(mChallengeQuestStage);

        // Get all the mChallengeQuestStageList where enableExtra in DEFAULT_ENABLE_EXTRA or UPDATED_ENABLE_EXTRA
        defaultMChallengeQuestStageShouldBeFound("enableExtra.in=" + DEFAULT_ENABLE_EXTRA + "," + UPDATED_ENABLE_EXTRA);

        // Get all the mChallengeQuestStageList where enableExtra equals to UPDATED_ENABLE_EXTRA
        defaultMChallengeQuestStageShouldNotBeFound("enableExtra.in=" + UPDATED_ENABLE_EXTRA);
    }

    @Test
    @Transactional
    public void getAllMChallengeQuestStagesByEnableExtraIsNullOrNotNull() throws Exception {
        // Initialize the database
        mChallengeQuestStageRepository.saveAndFlush(mChallengeQuestStage);

        // Get all the mChallengeQuestStageList where enableExtra is not null
        defaultMChallengeQuestStageShouldBeFound("enableExtra.specified=true");

        // Get all the mChallengeQuestStageList where enableExtra is null
        defaultMChallengeQuestStageShouldNotBeFound("enableExtra.specified=false");
    }

    @Test
    @Transactional
    public void getAllMChallengeQuestStagesByEnableExtraIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mChallengeQuestStageRepository.saveAndFlush(mChallengeQuestStage);

        // Get all the mChallengeQuestStageList where enableExtra greater than or equals to DEFAULT_ENABLE_EXTRA
        defaultMChallengeQuestStageShouldBeFound("enableExtra.greaterOrEqualThan=" + DEFAULT_ENABLE_EXTRA);

        // Get all the mChallengeQuestStageList where enableExtra greater than or equals to UPDATED_ENABLE_EXTRA
        defaultMChallengeQuestStageShouldNotBeFound("enableExtra.greaterOrEqualThan=" + UPDATED_ENABLE_EXTRA);
    }

    @Test
    @Transactional
    public void getAllMChallengeQuestStagesByEnableExtraIsLessThanSomething() throws Exception {
        // Initialize the database
        mChallengeQuestStageRepository.saveAndFlush(mChallengeQuestStage);

        // Get all the mChallengeQuestStageList where enableExtra less than or equals to DEFAULT_ENABLE_EXTRA
        defaultMChallengeQuestStageShouldNotBeFound("enableExtra.lessThan=" + DEFAULT_ENABLE_EXTRA);

        // Get all the mChallengeQuestStageList where enableExtra less than or equals to UPDATED_ENABLE_EXTRA
        defaultMChallengeQuestStageShouldBeFound("enableExtra.lessThan=" + UPDATED_ENABLE_EXTRA);
    }


    @Test
    @Transactional
    public void getAllMChallengeQuestStagesByEnablePkIsEqualToSomething() throws Exception {
        // Initialize the database
        mChallengeQuestStageRepository.saveAndFlush(mChallengeQuestStage);

        // Get all the mChallengeQuestStageList where enablePk equals to DEFAULT_ENABLE_PK
        defaultMChallengeQuestStageShouldBeFound("enablePk.equals=" + DEFAULT_ENABLE_PK);

        // Get all the mChallengeQuestStageList where enablePk equals to UPDATED_ENABLE_PK
        defaultMChallengeQuestStageShouldNotBeFound("enablePk.equals=" + UPDATED_ENABLE_PK);
    }

    @Test
    @Transactional
    public void getAllMChallengeQuestStagesByEnablePkIsInShouldWork() throws Exception {
        // Initialize the database
        mChallengeQuestStageRepository.saveAndFlush(mChallengeQuestStage);

        // Get all the mChallengeQuestStageList where enablePk in DEFAULT_ENABLE_PK or UPDATED_ENABLE_PK
        defaultMChallengeQuestStageShouldBeFound("enablePk.in=" + DEFAULT_ENABLE_PK + "," + UPDATED_ENABLE_PK);

        // Get all the mChallengeQuestStageList where enablePk equals to UPDATED_ENABLE_PK
        defaultMChallengeQuestStageShouldNotBeFound("enablePk.in=" + UPDATED_ENABLE_PK);
    }

    @Test
    @Transactional
    public void getAllMChallengeQuestStagesByEnablePkIsNullOrNotNull() throws Exception {
        // Initialize the database
        mChallengeQuestStageRepository.saveAndFlush(mChallengeQuestStage);

        // Get all the mChallengeQuestStageList where enablePk is not null
        defaultMChallengeQuestStageShouldBeFound("enablePk.specified=true");

        // Get all the mChallengeQuestStageList where enablePk is null
        defaultMChallengeQuestStageShouldNotBeFound("enablePk.specified=false");
    }

    @Test
    @Transactional
    public void getAllMChallengeQuestStagesByEnablePkIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mChallengeQuestStageRepository.saveAndFlush(mChallengeQuestStage);

        // Get all the mChallengeQuestStageList where enablePk greater than or equals to DEFAULT_ENABLE_PK
        defaultMChallengeQuestStageShouldBeFound("enablePk.greaterOrEqualThan=" + DEFAULT_ENABLE_PK);

        // Get all the mChallengeQuestStageList where enablePk greater than or equals to UPDATED_ENABLE_PK
        defaultMChallengeQuestStageShouldNotBeFound("enablePk.greaterOrEqualThan=" + UPDATED_ENABLE_PK);
    }

    @Test
    @Transactional
    public void getAllMChallengeQuestStagesByEnablePkIsLessThanSomething() throws Exception {
        // Initialize the database
        mChallengeQuestStageRepository.saveAndFlush(mChallengeQuestStage);

        // Get all the mChallengeQuestStageList where enablePk less than or equals to DEFAULT_ENABLE_PK
        defaultMChallengeQuestStageShouldNotBeFound("enablePk.lessThan=" + DEFAULT_ENABLE_PK);

        // Get all the mChallengeQuestStageList where enablePk less than or equals to UPDATED_ENABLE_PK
        defaultMChallengeQuestStageShouldBeFound("enablePk.lessThan=" + UPDATED_ENABLE_PK);
    }


    @Test
    @Transactional
    public void getAllMChallengeQuestStagesByAiLevelIsEqualToSomething() throws Exception {
        // Initialize the database
        mChallengeQuestStageRepository.saveAndFlush(mChallengeQuestStage);

        // Get all the mChallengeQuestStageList where aiLevel equals to DEFAULT_AI_LEVEL
        defaultMChallengeQuestStageShouldBeFound("aiLevel.equals=" + DEFAULT_AI_LEVEL);

        // Get all the mChallengeQuestStageList where aiLevel equals to UPDATED_AI_LEVEL
        defaultMChallengeQuestStageShouldNotBeFound("aiLevel.equals=" + UPDATED_AI_LEVEL);
    }

    @Test
    @Transactional
    public void getAllMChallengeQuestStagesByAiLevelIsInShouldWork() throws Exception {
        // Initialize the database
        mChallengeQuestStageRepository.saveAndFlush(mChallengeQuestStage);

        // Get all the mChallengeQuestStageList where aiLevel in DEFAULT_AI_LEVEL or UPDATED_AI_LEVEL
        defaultMChallengeQuestStageShouldBeFound("aiLevel.in=" + DEFAULT_AI_LEVEL + "," + UPDATED_AI_LEVEL);

        // Get all the mChallengeQuestStageList where aiLevel equals to UPDATED_AI_LEVEL
        defaultMChallengeQuestStageShouldNotBeFound("aiLevel.in=" + UPDATED_AI_LEVEL);
    }

    @Test
    @Transactional
    public void getAllMChallengeQuestStagesByAiLevelIsNullOrNotNull() throws Exception {
        // Initialize the database
        mChallengeQuestStageRepository.saveAndFlush(mChallengeQuestStage);

        // Get all the mChallengeQuestStageList where aiLevel is not null
        defaultMChallengeQuestStageShouldBeFound("aiLevel.specified=true");

        // Get all the mChallengeQuestStageList where aiLevel is null
        defaultMChallengeQuestStageShouldNotBeFound("aiLevel.specified=false");
    }

    @Test
    @Transactional
    public void getAllMChallengeQuestStagesByAiLevelIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mChallengeQuestStageRepository.saveAndFlush(mChallengeQuestStage);

        // Get all the mChallengeQuestStageList where aiLevel greater than or equals to DEFAULT_AI_LEVEL
        defaultMChallengeQuestStageShouldBeFound("aiLevel.greaterOrEqualThan=" + DEFAULT_AI_LEVEL);

        // Get all the mChallengeQuestStageList where aiLevel greater than or equals to UPDATED_AI_LEVEL
        defaultMChallengeQuestStageShouldNotBeFound("aiLevel.greaterOrEqualThan=" + UPDATED_AI_LEVEL);
    }

    @Test
    @Transactional
    public void getAllMChallengeQuestStagesByAiLevelIsLessThanSomething() throws Exception {
        // Initialize the database
        mChallengeQuestStageRepository.saveAndFlush(mChallengeQuestStage);

        // Get all the mChallengeQuestStageList where aiLevel less than or equals to DEFAULT_AI_LEVEL
        defaultMChallengeQuestStageShouldNotBeFound("aiLevel.lessThan=" + DEFAULT_AI_LEVEL);

        // Get all the mChallengeQuestStageList where aiLevel less than or equals to UPDATED_AI_LEVEL
        defaultMChallengeQuestStageShouldBeFound("aiLevel.lessThan=" + UPDATED_AI_LEVEL);
    }


    @Test
    @Transactional
    public void getAllMChallengeQuestStagesByStartAtSecondHalfIsEqualToSomething() throws Exception {
        // Initialize the database
        mChallengeQuestStageRepository.saveAndFlush(mChallengeQuestStage);

        // Get all the mChallengeQuestStageList where startAtSecondHalf equals to DEFAULT_START_AT_SECOND_HALF
        defaultMChallengeQuestStageShouldBeFound("startAtSecondHalf.equals=" + DEFAULT_START_AT_SECOND_HALF);

        // Get all the mChallengeQuestStageList where startAtSecondHalf equals to UPDATED_START_AT_SECOND_HALF
        defaultMChallengeQuestStageShouldNotBeFound("startAtSecondHalf.equals=" + UPDATED_START_AT_SECOND_HALF);
    }

    @Test
    @Transactional
    public void getAllMChallengeQuestStagesByStartAtSecondHalfIsInShouldWork() throws Exception {
        // Initialize the database
        mChallengeQuestStageRepository.saveAndFlush(mChallengeQuestStage);

        // Get all the mChallengeQuestStageList where startAtSecondHalf in DEFAULT_START_AT_SECOND_HALF or UPDATED_START_AT_SECOND_HALF
        defaultMChallengeQuestStageShouldBeFound("startAtSecondHalf.in=" + DEFAULT_START_AT_SECOND_HALF + "," + UPDATED_START_AT_SECOND_HALF);

        // Get all the mChallengeQuestStageList where startAtSecondHalf equals to UPDATED_START_AT_SECOND_HALF
        defaultMChallengeQuestStageShouldNotBeFound("startAtSecondHalf.in=" + UPDATED_START_AT_SECOND_HALF);
    }

    @Test
    @Transactional
    public void getAllMChallengeQuestStagesByStartAtSecondHalfIsNullOrNotNull() throws Exception {
        // Initialize the database
        mChallengeQuestStageRepository.saveAndFlush(mChallengeQuestStage);

        // Get all the mChallengeQuestStageList where startAtSecondHalf is not null
        defaultMChallengeQuestStageShouldBeFound("startAtSecondHalf.specified=true");

        // Get all the mChallengeQuestStageList where startAtSecondHalf is null
        defaultMChallengeQuestStageShouldNotBeFound("startAtSecondHalf.specified=false");
    }

    @Test
    @Transactional
    public void getAllMChallengeQuestStagesByStartAtSecondHalfIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mChallengeQuestStageRepository.saveAndFlush(mChallengeQuestStage);

        // Get all the mChallengeQuestStageList where startAtSecondHalf greater than or equals to DEFAULT_START_AT_SECOND_HALF
        defaultMChallengeQuestStageShouldBeFound("startAtSecondHalf.greaterOrEqualThan=" + DEFAULT_START_AT_SECOND_HALF);

        // Get all the mChallengeQuestStageList where startAtSecondHalf greater than or equals to UPDATED_START_AT_SECOND_HALF
        defaultMChallengeQuestStageShouldNotBeFound("startAtSecondHalf.greaterOrEqualThan=" + UPDATED_START_AT_SECOND_HALF);
    }

    @Test
    @Transactional
    public void getAllMChallengeQuestStagesByStartAtSecondHalfIsLessThanSomething() throws Exception {
        // Initialize the database
        mChallengeQuestStageRepository.saveAndFlush(mChallengeQuestStage);

        // Get all the mChallengeQuestStageList where startAtSecondHalf less than or equals to DEFAULT_START_AT_SECOND_HALF
        defaultMChallengeQuestStageShouldNotBeFound("startAtSecondHalf.lessThan=" + DEFAULT_START_AT_SECOND_HALF);

        // Get all the mChallengeQuestStageList where startAtSecondHalf less than or equals to UPDATED_START_AT_SECOND_HALF
        defaultMChallengeQuestStageShouldBeFound("startAtSecondHalf.lessThan=" + UPDATED_START_AT_SECOND_HALF);
    }


    @Test
    @Transactional
    public void getAllMChallengeQuestStagesByStartScoreIsEqualToSomething() throws Exception {
        // Initialize the database
        mChallengeQuestStageRepository.saveAndFlush(mChallengeQuestStage);

        // Get all the mChallengeQuestStageList where startScore equals to DEFAULT_START_SCORE
        defaultMChallengeQuestStageShouldBeFound("startScore.equals=" + DEFAULT_START_SCORE);

        // Get all the mChallengeQuestStageList where startScore equals to UPDATED_START_SCORE
        defaultMChallengeQuestStageShouldNotBeFound("startScore.equals=" + UPDATED_START_SCORE);
    }

    @Test
    @Transactional
    public void getAllMChallengeQuestStagesByStartScoreIsInShouldWork() throws Exception {
        // Initialize the database
        mChallengeQuestStageRepository.saveAndFlush(mChallengeQuestStage);

        // Get all the mChallengeQuestStageList where startScore in DEFAULT_START_SCORE or UPDATED_START_SCORE
        defaultMChallengeQuestStageShouldBeFound("startScore.in=" + DEFAULT_START_SCORE + "," + UPDATED_START_SCORE);

        // Get all the mChallengeQuestStageList where startScore equals to UPDATED_START_SCORE
        defaultMChallengeQuestStageShouldNotBeFound("startScore.in=" + UPDATED_START_SCORE);
    }

    @Test
    @Transactional
    public void getAllMChallengeQuestStagesByStartScoreIsNullOrNotNull() throws Exception {
        // Initialize the database
        mChallengeQuestStageRepository.saveAndFlush(mChallengeQuestStage);

        // Get all the mChallengeQuestStageList where startScore is not null
        defaultMChallengeQuestStageShouldBeFound("startScore.specified=true");

        // Get all the mChallengeQuestStageList where startScore is null
        defaultMChallengeQuestStageShouldNotBeFound("startScore.specified=false");
    }

    @Test
    @Transactional
    public void getAllMChallengeQuestStagesByStartScoreIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mChallengeQuestStageRepository.saveAndFlush(mChallengeQuestStage);

        // Get all the mChallengeQuestStageList where startScore greater than or equals to DEFAULT_START_SCORE
        defaultMChallengeQuestStageShouldBeFound("startScore.greaterOrEqualThan=" + DEFAULT_START_SCORE);

        // Get all the mChallengeQuestStageList where startScore greater than or equals to UPDATED_START_SCORE
        defaultMChallengeQuestStageShouldNotBeFound("startScore.greaterOrEqualThan=" + UPDATED_START_SCORE);
    }

    @Test
    @Transactional
    public void getAllMChallengeQuestStagesByStartScoreIsLessThanSomething() throws Exception {
        // Initialize the database
        mChallengeQuestStageRepository.saveAndFlush(mChallengeQuestStage);

        // Get all the mChallengeQuestStageList where startScore less than or equals to DEFAULT_START_SCORE
        defaultMChallengeQuestStageShouldNotBeFound("startScore.lessThan=" + DEFAULT_START_SCORE);

        // Get all the mChallengeQuestStageList where startScore less than or equals to UPDATED_START_SCORE
        defaultMChallengeQuestStageShouldBeFound("startScore.lessThan=" + UPDATED_START_SCORE);
    }


    @Test
    @Transactional
    public void getAllMChallengeQuestStagesByStartScoreOpponentIsEqualToSomething() throws Exception {
        // Initialize the database
        mChallengeQuestStageRepository.saveAndFlush(mChallengeQuestStage);

        // Get all the mChallengeQuestStageList where startScoreOpponent equals to DEFAULT_START_SCORE_OPPONENT
        defaultMChallengeQuestStageShouldBeFound("startScoreOpponent.equals=" + DEFAULT_START_SCORE_OPPONENT);

        // Get all the mChallengeQuestStageList where startScoreOpponent equals to UPDATED_START_SCORE_OPPONENT
        defaultMChallengeQuestStageShouldNotBeFound("startScoreOpponent.equals=" + UPDATED_START_SCORE_OPPONENT);
    }

    @Test
    @Transactional
    public void getAllMChallengeQuestStagesByStartScoreOpponentIsInShouldWork() throws Exception {
        // Initialize the database
        mChallengeQuestStageRepository.saveAndFlush(mChallengeQuestStage);

        // Get all the mChallengeQuestStageList where startScoreOpponent in DEFAULT_START_SCORE_OPPONENT or UPDATED_START_SCORE_OPPONENT
        defaultMChallengeQuestStageShouldBeFound("startScoreOpponent.in=" + DEFAULT_START_SCORE_OPPONENT + "," + UPDATED_START_SCORE_OPPONENT);

        // Get all the mChallengeQuestStageList where startScoreOpponent equals to UPDATED_START_SCORE_OPPONENT
        defaultMChallengeQuestStageShouldNotBeFound("startScoreOpponent.in=" + UPDATED_START_SCORE_OPPONENT);
    }

    @Test
    @Transactional
    public void getAllMChallengeQuestStagesByStartScoreOpponentIsNullOrNotNull() throws Exception {
        // Initialize the database
        mChallengeQuestStageRepository.saveAndFlush(mChallengeQuestStage);

        // Get all the mChallengeQuestStageList where startScoreOpponent is not null
        defaultMChallengeQuestStageShouldBeFound("startScoreOpponent.specified=true");

        // Get all the mChallengeQuestStageList where startScoreOpponent is null
        defaultMChallengeQuestStageShouldNotBeFound("startScoreOpponent.specified=false");
    }

    @Test
    @Transactional
    public void getAllMChallengeQuestStagesByStartScoreOpponentIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mChallengeQuestStageRepository.saveAndFlush(mChallengeQuestStage);

        // Get all the mChallengeQuestStageList where startScoreOpponent greater than or equals to DEFAULT_START_SCORE_OPPONENT
        defaultMChallengeQuestStageShouldBeFound("startScoreOpponent.greaterOrEqualThan=" + DEFAULT_START_SCORE_OPPONENT);

        // Get all the mChallengeQuestStageList where startScoreOpponent greater than or equals to UPDATED_START_SCORE_OPPONENT
        defaultMChallengeQuestStageShouldNotBeFound("startScoreOpponent.greaterOrEqualThan=" + UPDATED_START_SCORE_OPPONENT);
    }

    @Test
    @Transactional
    public void getAllMChallengeQuestStagesByStartScoreOpponentIsLessThanSomething() throws Exception {
        // Initialize the database
        mChallengeQuestStageRepository.saveAndFlush(mChallengeQuestStage);

        // Get all the mChallengeQuestStageList where startScoreOpponent less than or equals to DEFAULT_START_SCORE_OPPONENT
        defaultMChallengeQuestStageShouldNotBeFound("startScoreOpponent.lessThan=" + DEFAULT_START_SCORE_OPPONENT);

        // Get all the mChallengeQuestStageList where startScoreOpponent less than or equals to UPDATED_START_SCORE_OPPONENT
        defaultMChallengeQuestStageShouldBeFound("startScoreOpponent.lessThan=" + UPDATED_START_SCORE_OPPONENT);
    }


    @Test
    @Transactional
    public void getAllMChallengeQuestStagesByConditionIdIsEqualToSomething() throws Exception {
        // Initialize the database
        mChallengeQuestStageRepository.saveAndFlush(mChallengeQuestStage);

        // Get all the mChallengeQuestStageList where conditionId equals to DEFAULT_CONDITION_ID
        defaultMChallengeQuestStageShouldBeFound("conditionId.equals=" + DEFAULT_CONDITION_ID);

        // Get all the mChallengeQuestStageList where conditionId equals to UPDATED_CONDITION_ID
        defaultMChallengeQuestStageShouldNotBeFound("conditionId.equals=" + UPDATED_CONDITION_ID);
    }

    @Test
    @Transactional
    public void getAllMChallengeQuestStagesByConditionIdIsInShouldWork() throws Exception {
        // Initialize the database
        mChallengeQuestStageRepository.saveAndFlush(mChallengeQuestStage);

        // Get all the mChallengeQuestStageList where conditionId in DEFAULT_CONDITION_ID or UPDATED_CONDITION_ID
        defaultMChallengeQuestStageShouldBeFound("conditionId.in=" + DEFAULT_CONDITION_ID + "," + UPDATED_CONDITION_ID);

        // Get all the mChallengeQuestStageList where conditionId equals to UPDATED_CONDITION_ID
        defaultMChallengeQuestStageShouldNotBeFound("conditionId.in=" + UPDATED_CONDITION_ID);
    }

    @Test
    @Transactional
    public void getAllMChallengeQuestStagesByConditionIdIsNullOrNotNull() throws Exception {
        // Initialize the database
        mChallengeQuestStageRepository.saveAndFlush(mChallengeQuestStage);

        // Get all the mChallengeQuestStageList where conditionId is not null
        defaultMChallengeQuestStageShouldBeFound("conditionId.specified=true");

        // Get all the mChallengeQuestStageList where conditionId is null
        defaultMChallengeQuestStageShouldNotBeFound("conditionId.specified=false");
    }

    @Test
    @Transactional
    public void getAllMChallengeQuestStagesByConditionIdIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mChallengeQuestStageRepository.saveAndFlush(mChallengeQuestStage);

        // Get all the mChallengeQuestStageList where conditionId greater than or equals to DEFAULT_CONDITION_ID
        defaultMChallengeQuestStageShouldBeFound("conditionId.greaterOrEqualThan=" + DEFAULT_CONDITION_ID);

        // Get all the mChallengeQuestStageList where conditionId greater than or equals to UPDATED_CONDITION_ID
        defaultMChallengeQuestStageShouldNotBeFound("conditionId.greaterOrEqualThan=" + UPDATED_CONDITION_ID);
    }

    @Test
    @Transactional
    public void getAllMChallengeQuestStagesByConditionIdIsLessThanSomething() throws Exception {
        // Initialize the database
        mChallengeQuestStageRepository.saveAndFlush(mChallengeQuestStage);

        // Get all the mChallengeQuestStageList where conditionId less than or equals to DEFAULT_CONDITION_ID
        defaultMChallengeQuestStageShouldNotBeFound("conditionId.lessThan=" + DEFAULT_CONDITION_ID);

        // Get all the mChallengeQuestStageList where conditionId less than or equals to UPDATED_CONDITION_ID
        defaultMChallengeQuestStageShouldBeFound("conditionId.lessThan=" + UPDATED_CONDITION_ID);
    }


    @Test
    @Transactional
    public void getAllMChallengeQuestStagesByOptionIdIsEqualToSomething() throws Exception {
        // Initialize the database
        mChallengeQuestStageRepository.saveAndFlush(mChallengeQuestStage);

        // Get all the mChallengeQuestStageList where optionId equals to DEFAULT_OPTION_ID
        defaultMChallengeQuestStageShouldBeFound("optionId.equals=" + DEFAULT_OPTION_ID);

        // Get all the mChallengeQuestStageList where optionId equals to UPDATED_OPTION_ID
        defaultMChallengeQuestStageShouldNotBeFound("optionId.equals=" + UPDATED_OPTION_ID);
    }

    @Test
    @Transactional
    public void getAllMChallengeQuestStagesByOptionIdIsInShouldWork() throws Exception {
        // Initialize the database
        mChallengeQuestStageRepository.saveAndFlush(mChallengeQuestStage);

        // Get all the mChallengeQuestStageList where optionId in DEFAULT_OPTION_ID or UPDATED_OPTION_ID
        defaultMChallengeQuestStageShouldBeFound("optionId.in=" + DEFAULT_OPTION_ID + "," + UPDATED_OPTION_ID);

        // Get all the mChallengeQuestStageList where optionId equals to UPDATED_OPTION_ID
        defaultMChallengeQuestStageShouldNotBeFound("optionId.in=" + UPDATED_OPTION_ID);
    }

    @Test
    @Transactional
    public void getAllMChallengeQuestStagesByOptionIdIsNullOrNotNull() throws Exception {
        // Initialize the database
        mChallengeQuestStageRepository.saveAndFlush(mChallengeQuestStage);

        // Get all the mChallengeQuestStageList where optionId is not null
        defaultMChallengeQuestStageShouldBeFound("optionId.specified=true");

        // Get all the mChallengeQuestStageList where optionId is null
        defaultMChallengeQuestStageShouldNotBeFound("optionId.specified=false");
    }

    @Test
    @Transactional
    public void getAllMChallengeQuestStagesByOptionIdIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mChallengeQuestStageRepository.saveAndFlush(mChallengeQuestStage);

        // Get all the mChallengeQuestStageList where optionId greater than or equals to DEFAULT_OPTION_ID
        defaultMChallengeQuestStageShouldBeFound("optionId.greaterOrEqualThan=" + DEFAULT_OPTION_ID);

        // Get all the mChallengeQuestStageList where optionId greater than or equals to UPDATED_OPTION_ID
        defaultMChallengeQuestStageShouldNotBeFound("optionId.greaterOrEqualThan=" + UPDATED_OPTION_ID);
    }

    @Test
    @Transactional
    public void getAllMChallengeQuestStagesByOptionIdIsLessThanSomething() throws Exception {
        // Initialize the database
        mChallengeQuestStageRepository.saveAndFlush(mChallengeQuestStage);

        // Get all the mChallengeQuestStageList where optionId less than or equals to DEFAULT_OPTION_ID
        defaultMChallengeQuestStageShouldNotBeFound("optionId.lessThan=" + DEFAULT_OPTION_ID);

        // Get all the mChallengeQuestStageList where optionId less than or equals to UPDATED_OPTION_ID
        defaultMChallengeQuestStageShouldBeFound("optionId.lessThan=" + UPDATED_OPTION_ID);
    }


    @Test
    @Transactional
    public void getAllMChallengeQuestStagesByDeckConditionIdIsEqualToSomething() throws Exception {
        // Initialize the database
        mChallengeQuestStageRepository.saveAndFlush(mChallengeQuestStage);

        // Get all the mChallengeQuestStageList where deckConditionId equals to DEFAULT_DECK_CONDITION_ID
        defaultMChallengeQuestStageShouldBeFound("deckConditionId.equals=" + DEFAULT_DECK_CONDITION_ID);

        // Get all the mChallengeQuestStageList where deckConditionId equals to UPDATED_DECK_CONDITION_ID
        defaultMChallengeQuestStageShouldNotBeFound("deckConditionId.equals=" + UPDATED_DECK_CONDITION_ID);
    }

    @Test
    @Transactional
    public void getAllMChallengeQuestStagesByDeckConditionIdIsInShouldWork() throws Exception {
        // Initialize the database
        mChallengeQuestStageRepository.saveAndFlush(mChallengeQuestStage);

        // Get all the mChallengeQuestStageList where deckConditionId in DEFAULT_DECK_CONDITION_ID or UPDATED_DECK_CONDITION_ID
        defaultMChallengeQuestStageShouldBeFound("deckConditionId.in=" + DEFAULT_DECK_CONDITION_ID + "," + UPDATED_DECK_CONDITION_ID);

        // Get all the mChallengeQuestStageList where deckConditionId equals to UPDATED_DECK_CONDITION_ID
        defaultMChallengeQuestStageShouldNotBeFound("deckConditionId.in=" + UPDATED_DECK_CONDITION_ID);
    }

    @Test
    @Transactional
    public void getAllMChallengeQuestStagesByDeckConditionIdIsNullOrNotNull() throws Exception {
        // Initialize the database
        mChallengeQuestStageRepository.saveAndFlush(mChallengeQuestStage);

        // Get all the mChallengeQuestStageList where deckConditionId is not null
        defaultMChallengeQuestStageShouldBeFound("deckConditionId.specified=true");

        // Get all the mChallengeQuestStageList where deckConditionId is null
        defaultMChallengeQuestStageShouldNotBeFound("deckConditionId.specified=false");
    }

    @Test
    @Transactional
    public void getAllMChallengeQuestStagesByDeckConditionIdIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mChallengeQuestStageRepository.saveAndFlush(mChallengeQuestStage);

        // Get all the mChallengeQuestStageList where deckConditionId greater than or equals to DEFAULT_DECK_CONDITION_ID
        defaultMChallengeQuestStageShouldBeFound("deckConditionId.greaterOrEqualThan=" + DEFAULT_DECK_CONDITION_ID);

        // Get all the mChallengeQuestStageList where deckConditionId greater than or equals to UPDATED_DECK_CONDITION_ID
        defaultMChallengeQuestStageShouldNotBeFound("deckConditionId.greaterOrEqualThan=" + UPDATED_DECK_CONDITION_ID);
    }

    @Test
    @Transactional
    public void getAllMChallengeQuestStagesByDeckConditionIdIsLessThanSomething() throws Exception {
        // Initialize the database
        mChallengeQuestStageRepository.saveAndFlush(mChallengeQuestStage);

        // Get all the mChallengeQuestStageList where deckConditionId less than or equals to DEFAULT_DECK_CONDITION_ID
        defaultMChallengeQuestStageShouldNotBeFound("deckConditionId.lessThan=" + DEFAULT_DECK_CONDITION_ID);

        // Get all the mChallengeQuestStageList where deckConditionId less than or equals to UPDATED_DECK_CONDITION_ID
        defaultMChallengeQuestStageShouldBeFound("deckConditionId.lessThan=" + UPDATED_DECK_CONDITION_ID);
    }


    @Test
    @Transactional
    public void getAllMChallengeQuestStagesBySkipCheckPointIsEqualToSomething() throws Exception {
        // Initialize the database
        mChallengeQuestStageRepository.saveAndFlush(mChallengeQuestStage);

        // Get all the mChallengeQuestStageList where skipCheckPoint equals to DEFAULT_SKIP_CHECK_POINT
        defaultMChallengeQuestStageShouldBeFound("skipCheckPoint.equals=" + DEFAULT_SKIP_CHECK_POINT);

        // Get all the mChallengeQuestStageList where skipCheckPoint equals to UPDATED_SKIP_CHECK_POINT
        defaultMChallengeQuestStageShouldNotBeFound("skipCheckPoint.equals=" + UPDATED_SKIP_CHECK_POINT);
    }

    @Test
    @Transactional
    public void getAllMChallengeQuestStagesBySkipCheckPointIsInShouldWork() throws Exception {
        // Initialize the database
        mChallengeQuestStageRepository.saveAndFlush(mChallengeQuestStage);

        // Get all the mChallengeQuestStageList where skipCheckPoint in DEFAULT_SKIP_CHECK_POINT or UPDATED_SKIP_CHECK_POINT
        defaultMChallengeQuestStageShouldBeFound("skipCheckPoint.in=" + DEFAULT_SKIP_CHECK_POINT + "," + UPDATED_SKIP_CHECK_POINT);

        // Get all the mChallengeQuestStageList where skipCheckPoint equals to UPDATED_SKIP_CHECK_POINT
        defaultMChallengeQuestStageShouldNotBeFound("skipCheckPoint.in=" + UPDATED_SKIP_CHECK_POINT);
    }

    @Test
    @Transactional
    public void getAllMChallengeQuestStagesBySkipCheckPointIsNullOrNotNull() throws Exception {
        // Initialize the database
        mChallengeQuestStageRepository.saveAndFlush(mChallengeQuestStage);

        // Get all the mChallengeQuestStageList where skipCheckPoint is not null
        defaultMChallengeQuestStageShouldBeFound("skipCheckPoint.specified=true");

        // Get all the mChallengeQuestStageList where skipCheckPoint is null
        defaultMChallengeQuestStageShouldNotBeFound("skipCheckPoint.specified=false");
    }

    @Test
    @Transactional
    public void getAllMChallengeQuestStagesBySkipCheckPointIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mChallengeQuestStageRepository.saveAndFlush(mChallengeQuestStage);

        // Get all the mChallengeQuestStageList where skipCheckPoint greater than or equals to DEFAULT_SKIP_CHECK_POINT
        defaultMChallengeQuestStageShouldBeFound("skipCheckPoint.greaterOrEqualThan=" + DEFAULT_SKIP_CHECK_POINT);

        // Get all the mChallengeQuestStageList where skipCheckPoint greater than or equals to UPDATED_SKIP_CHECK_POINT
        defaultMChallengeQuestStageShouldNotBeFound("skipCheckPoint.greaterOrEqualThan=" + UPDATED_SKIP_CHECK_POINT);
    }

    @Test
    @Transactional
    public void getAllMChallengeQuestStagesBySkipCheckPointIsLessThanSomething() throws Exception {
        // Initialize the database
        mChallengeQuestStageRepository.saveAndFlush(mChallengeQuestStage);

        // Get all the mChallengeQuestStageList where skipCheckPoint less than or equals to DEFAULT_SKIP_CHECK_POINT
        defaultMChallengeQuestStageShouldNotBeFound("skipCheckPoint.lessThan=" + DEFAULT_SKIP_CHECK_POINT);

        // Get all the mChallengeQuestStageList where skipCheckPoint less than or equals to UPDATED_SKIP_CHECK_POINT
        defaultMChallengeQuestStageShouldBeFound("skipCheckPoint.lessThan=" + UPDATED_SKIP_CHECK_POINT);
    }


    @Test
    @Transactional
    public void getAllMChallengeQuestStagesByRoadNumberIsEqualToSomething() throws Exception {
        // Initialize the database
        mChallengeQuestStageRepository.saveAndFlush(mChallengeQuestStage);

        // Get all the mChallengeQuestStageList where roadNumber equals to DEFAULT_ROAD_NUMBER
        defaultMChallengeQuestStageShouldBeFound("roadNumber.equals=" + DEFAULT_ROAD_NUMBER);

        // Get all the mChallengeQuestStageList where roadNumber equals to UPDATED_ROAD_NUMBER
        defaultMChallengeQuestStageShouldNotBeFound("roadNumber.equals=" + UPDATED_ROAD_NUMBER);
    }

    @Test
    @Transactional
    public void getAllMChallengeQuestStagesByRoadNumberIsInShouldWork() throws Exception {
        // Initialize the database
        mChallengeQuestStageRepository.saveAndFlush(mChallengeQuestStage);

        // Get all the mChallengeQuestStageList where roadNumber in DEFAULT_ROAD_NUMBER or UPDATED_ROAD_NUMBER
        defaultMChallengeQuestStageShouldBeFound("roadNumber.in=" + DEFAULT_ROAD_NUMBER + "," + UPDATED_ROAD_NUMBER);

        // Get all the mChallengeQuestStageList where roadNumber equals to UPDATED_ROAD_NUMBER
        defaultMChallengeQuestStageShouldNotBeFound("roadNumber.in=" + UPDATED_ROAD_NUMBER);
    }

    @Test
    @Transactional
    public void getAllMChallengeQuestStagesByRoadNumberIsNullOrNotNull() throws Exception {
        // Initialize the database
        mChallengeQuestStageRepository.saveAndFlush(mChallengeQuestStage);

        // Get all the mChallengeQuestStageList where roadNumber is not null
        defaultMChallengeQuestStageShouldBeFound("roadNumber.specified=true");

        // Get all the mChallengeQuestStageList where roadNumber is null
        defaultMChallengeQuestStageShouldNotBeFound("roadNumber.specified=false");
    }

    @Test
    @Transactional
    public void getAllMChallengeQuestStagesByRoadNumberIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mChallengeQuestStageRepository.saveAndFlush(mChallengeQuestStage);

        // Get all the mChallengeQuestStageList where roadNumber greater than or equals to DEFAULT_ROAD_NUMBER
        defaultMChallengeQuestStageShouldBeFound("roadNumber.greaterOrEqualThan=" + DEFAULT_ROAD_NUMBER);

        // Get all the mChallengeQuestStageList where roadNumber greater than or equals to UPDATED_ROAD_NUMBER
        defaultMChallengeQuestStageShouldNotBeFound("roadNumber.greaterOrEqualThan=" + UPDATED_ROAD_NUMBER);
    }

    @Test
    @Transactional
    public void getAllMChallengeQuestStagesByRoadNumberIsLessThanSomething() throws Exception {
        // Initialize the database
        mChallengeQuestStageRepository.saveAndFlush(mChallengeQuestStage);

        // Get all the mChallengeQuestStageList where roadNumber less than or equals to DEFAULT_ROAD_NUMBER
        defaultMChallengeQuestStageShouldNotBeFound("roadNumber.lessThan=" + DEFAULT_ROAD_NUMBER);

        // Get all the mChallengeQuestStageList where roadNumber less than or equals to UPDATED_ROAD_NUMBER
        defaultMChallengeQuestStageShouldBeFound("roadNumber.lessThan=" + UPDATED_ROAD_NUMBER);
    }


    @Test
    @Transactional
    public void getAllMChallengeQuestStagesByMchallengequestworldIsEqualToSomething() throws Exception {
        // Get already existing entity
        MChallengeQuestWorld mchallengequestworld = mChallengeQuestStage.getMchallengequestworld();
        mChallengeQuestStageRepository.saveAndFlush(mChallengeQuestStage);
        Long mchallengequestworldId = mchallengequestworld.getId();

        // Get all the mChallengeQuestStageList where mchallengequestworld equals to mchallengequestworldId
        defaultMChallengeQuestStageShouldBeFound("mchallengequestworldId.equals=" + mchallengequestworldId);

        // Get all the mChallengeQuestStageList where mchallengequestworld equals to mchallengequestworldId + 1
        defaultMChallengeQuestStageShouldNotBeFound("mchallengequestworldId.equals=" + (mchallengequestworldId + 1));
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultMChallengeQuestStageShouldBeFound(String filter) throws Exception {
        restMChallengeQuestStageMockMvc.perform(get("/api/m-challenge-quest-stages?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(mChallengeQuestStage.getId().intValue())))
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
            .andExpect(jsonPath("$.[*].deckConditionId").value(hasItem(DEFAULT_DECK_CONDITION_ID)))
            .andExpect(jsonPath("$.[*].shortName").value(hasItem(DEFAULT_SHORT_NAME.toString())))
            .andExpect(jsonPath("$.[*].skipCheckPoint").value(hasItem(DEFAULT_SKIP_CHECK_POINT)))
            .andExpect(jsonPath("$.[*].roadNumber").value(hasItem(DEFAULT_ROAD_NUMBER)));

        // Check, that the count call also returns 1
        restMChallengeQuestStageMockMvc.perform(get("/api/m-challenge-quest-stages/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultMChallengeQuestStageShouldNotBeFound(String filter) throws Exception {
        restMChallengeQuestStageMockMvc.perform(get("/api/m-challenge-quest-stages?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restMChallengeQuestStageMockMvc.perform(get("/api/m-challenge-quest-stages/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("0"));
    }


    @Test
    @Transactional
    public void getNonExistingMChallengeQuestStage() throws Exception {
        // Get the mChallengeQuestStage
        restMChallengeQuestStageMockMvc.perform(get("/api/m-challenge-quest-stages/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateMChallengeQuestStage() throws Exception {
        // Initialize the database
        mChallengeQuestStageRepository.saveAndFlush(mChallengeQuestStage);

        int databaseSizeBeforeUpdate = mChallengeQuestStageRepository.findAll().size();

        // Update the mChallengeQuestStage
        MChallengeQuestStage updatedMChallengeQuestStage = mChallengeQuestStageRepository.findById(mChallengeQuestStage.getId()).get();
        // Disconnect from session so that the updates on updatedMChallengeQuestStage are not directly saved in db
        em.detach(updatedMChallengeQuestStage);
        updatedMChallengeQuestStage
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
            .deckConditionId(UPDATED_DECK_CONDITION_ID)
            .shortName(UPDATED_SHORT_NAME)
            .skipCheckPoint(UPDATED_SKIP_CHECK_POINT)
            .roadNumber(UPDATED_ROAD_NUMBER);
        MChallengeQuestStageDTO mChallengeQuestStageDTO = mChallengeQuestStageMapper.toDto(updatedMChallengeQuestStage);

        restMChallengeQuestStageMockMvc.perform(put("/api/m-challenge-quest-stages")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mChallengeQuestStageDTO)))
            .andExpect(status().isOk());

        // Validate the MChallengeQuestStage in the database
        List<MChallengeQuestStage> mChallengeQuestStageList = mChallengeQuestStageRepository.findAll();
        assertThat(mChallengeQuestStageList).hasSize(databaseSizeBeforeUpdate);
        MChallengeQuestStage testMChallengeQuestStage = mChallengeQuestStageList.get(mChallengeQuestStageList.size() - 1);
        assertThat(testMChallengeQuestStage.getWorldId()).isEqualTo(UPDATED_WORLD_ID);
        assertThat(testMChallengeQuestStage.getNumber()).isEqualTo(UPDATED_NUMBER);
        assertThat(testMChallengeQuestStage.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testMChallengeQuestStage.getImagePath()).isEqualTo(UPDATED_IMAGE_PATH);
        assertThat(testMChallengeQuestStage.getCharacterThumbnailPath()).isEqualTo(UPDATED_CHARACTER_THUMBNAIL_PATH);
        assertThat(testMChallengeQuestStage.getDifficulty()).isEqualTo(UPDATED_DIFFICULTY);
        assertThat(testMChallengeQuestStage.getStageUnlockPattern()).isEqualTo(UPDATED_STAGE_UNLOCK_PATTERN);
        assertThat(testMChallengeQuestStage.getStoryOnly()).isEqualTo(UPDATED_STORY_ONLY);
        assertThat(testMChallengeQuestStage.getFirstHalfNpcDeckId()).isEqualTo(UPDATED_FIRST_HALF_NPC_DECK_ID);
        assertThat(testMChallengeQuestStage.getFirstHalfEnvironmentId()).isEqualTo(UPDATED_FIRST_HALF_ENVIRONMENT_ID);
        assertThat(testMChallengeQuestStage.getSecondHalfNpcDeckId()).isEqualTo(UPDATED_SECOND_HALF_NPC_DECK_ID);
        assertThat(testMChallengeQuestStage.getSecondHalfEnvironmentId()).isEqualTo(UPDATED_SECOND_HALF_ENVIRONMENT_ID);
        assertThat(testMChallengeQuestStage.getExtraFirstHalfNpcDeckId()).isEqualTo(UPDATED_EXTRA_FIRST_HALF_NPC_DECK_ID);
        assertThat(testMChallengeQuestStage.getConsumeAp()).isEqualTo(UPDATED_CONSUME_AP);
        assertThat(testMChallengeQuestStage.getKickOffType()).isEqualTo(UPDATED_KICK_OFF_TYPE);
        assertThat(testMChallengeQuestStage.getMatchMinute()).isEqualTo(UPDATED_MATCH_MINUTE);
        assertThat(testMChallengeQuestStage.getEnableExtra()).isEqualTo(UPDATED_ENABLE_EXTRA);
        assertThat(testMChallengeQuestStage.getEnablePk()).isEqualTo(UPDATED_ENABLE_PK);
        assertThat(testMChallengeQuestStage.getAiLevel()).isEqualTo(UPDATED_AI_LEVEL);
        assertThat(testMChallengeQuestStage.getStartAtSecondHalf()).isEqualTo(UPDATED_START_AT_SECOND_HALF);
        assertThat(testMChallengeQuestStage.getStartScore()).isEqualTo(UPDATED_START_SCORE);
        assertThat(testMChallengeQuestStage.getStartScoreOpponent()).isEqualTo(UPDATED_START_SCORE_OPPONENT);
        assertThat(testMChallengeQuestStage.getConditionId()).isEqualTo(UPDATED_CONDITION_ID);
        assertThat(testMChallengeQuestStage.getOptionId()).isEqualTo(UPDATED_OPTION_ID);
        assertThat(testMChallengeQuestStage.getDeckConditionId()).isEqualTo(UPDATED_DECK_CONDITION_ID);
        assertThat(testMChallengeQuestStage.getShortName()).isEqualTo(UPDATED_SHORT_NAME);
        assertThat(testMChallengeQuestStage.getSkipCheckPoint()).isEqualTo(UPDATED_SKIP_CHECK_POINT);
        assertThat(testMChallengeQuestStage.getRoadNumber()).isEqualTo(UPDATED_ROAD_NUMBER);
    }

    @Test
    @Transactional
    public void updateNonExistingMChallengeQuestStage() throws Exception {
        int databaseSizeBeforeUpdate = mChallengeQuestStageRepository.findAll().size();

        // Create the MChallengeQuestStage
        MChallengeQuestStageDTO mChallengeQuestStageDTO = mChallengeQuestStageMapper.toDto(mChallengeQuestStage);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restMChallengeQuestStageMockMvc.perform(put("/api/m-challenge-quest-stages")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mChallengeQuestStageDTO)))
            .andExpect(status().isBadRequest());

        // Validate the MChallengeQuestStage in the database
        List<MChallengeQuestStage> mChallengeQuestStageList = mChallengeQuestStageRepository.findAll();
        assertThat(mChallengeQuestStageList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteMChallengeQuestStage() throws Exception {
        // Initialize the database
        mChallengeQuestStageRepository.saveAndFlush(mChallengeQuestStage);

        int databaseSizeBeforeDelete = mChallengeQuestStageRepository.findAll().size();

        // Delete the mChallengeQuestStage
        restMChallengeQuestStageMockMvc.perform(delete("/api/m-challenge-quest-stages/{id}", mChallengeQuestStage.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database is empty
        List<MChallengeQuestStage> mChallengeQuestStageList = mChallengeQuestStageRepository.findAll();
        assertThat(mChallengeQuestStageList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(MChallengeQuestStage.class);
        MChallengeQuestStage mChallengeQuestStage1 = new MChallengeQuestStage();
        mChallengeQuestStage1.setId(1L);
        MChallengeQuestStage mChallengeQuestStage2 = new MChallengeQuestStage();
        mChallengeQuestStage2.setId(mChallengeQuestStage1.getId());
        assertThat(mChallengeQuestStage1).isEqualTo(mChallengeQuestStage2);
        mChallengeQuestStage2.setId(2L);
        assertThat(mChallengeQuestStage1).isNotEqualTo(mChallengeQuestStage2);
        mChallengeQuestStage1.setId(null);
        assertThat(mChallengeQuestStage1).isNotEqualTo(mChallengeQuestStage2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(MChallengeQuestStageDTO.class);
        MChallengeQuestStageDTO mChallengeQuestStageDTO1 = new MChallengeQuestStageDTO();
        mChallengeQuestStageDTO1.setId(1L);
        MChallengeQuestStageDTO mChallengeQuestStageDTO2 = new MChallengeQuestStageDTO();
        assertThat(mChallengeQuestStageDTO1).isNotEqualTo(mChallengeQuestStageDTO2);
        mChallengeQuestStageDTO2.setId(mChallengeQuestStageDTO1.getId());
        assertThat(mChallengeQuestStageDTO1).isEqualTo(mChallengeQuestStageDTO2);
        mChallengeQuestStageDTO2.setId(2L);
        assertThat(mChallengeQuestStageDTO1).isNotEqualTo(mChallengeQuestStageDTO2);
        mChallengeQuestStageDTO1.setId(null);
        assertThat(mChallengeQuestStageDTO1).isNotEqualTo(mChallengeQuestStageDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(mChallengeQuestStageMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(mChallengeQuestStageMapper.fromId(null)).isNull();
    }
}
