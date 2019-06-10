package io.shm.tsubasa.web.rest;

import io.shm.tsubasa.TsubasaApp;
import io.shm.tsubasa.domain.MAdventQuestStage;
import io.shm.tsubasa.domain.MAdventQuestWorld;
import io.shm.tsubasa.repository.MAdventQuestStageRepository;
import io.shm.tsubasa.service.MAdventQuestStageService;
import io.shm.tsubasa.service.dto.MAdventQuestStageDTO;
import io.shm.tsubasa.service.mapper.MAdventQuestStageMapper;
import io.shm.tsubasa.web.rest.errors.ExceptionTranslator;
import io.shm.tsubasa.service.dto.MAdventQuestStageCriteria;
import io.shm.tsubasa.service.MAdventQuestStageQueryService;

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
 * Integration tests for the {@Link MAdventQuestStageResource} REST controller.
 */
@SpringBootTest(classes = TsubasaApp.class)
public class MAdventQuestStageResourceIT {

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

    @Autowired
    private MAdventQuestStageRepository mAdventQuestStageRepository;

    @Autowired
    private MAdventQuestStageMapper mAdventQuestStageMapper;

    @Autowired
    private MAdventQuestStageService mAdventQuestStageService;

    @Autowired
    private MAdventQuestStageQueryService mAdventQuestStageQueryService;

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

    private MockMvc restMAdventQuestStageMockMvc;

    private MAdventQuestStage mAdventQuestStage;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final MAdventQuestStageResource mAdventQuestStageResource = new MAdventQuestStageResource(mAdventQuestStageService, mAdventQuestStageQueryService);
        this.restMAdventQuestStageMockMvc = MockMvcBuilders.standaloneSetup(mAdventQuestStageResource)
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
    public static MAdventQuestStage createEntity(EntityManager em) {
        MAdventQuestStage mAdventQuestStage = new MAdventQuestStage()
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
            .deckConditionId(DEFAULT_DECK_CONDITION_ID);
        // Add required entity
        MAdventQuestWorld mAdventQuestWorld;
        if (TestUtil.findAll(em, MAdventQuestWorld.class).isEmpty()) {
            mAdventQuestWorld = MAdventQuestWorldResourceIT.createEntity(em);
            em.persist(mAdventQuestWorld);
            em.flush();
        } else {
            mAdventQuestWorld = TestUtil.findAll(em, MAdventQuestWorld.class).get(0);
        }
        mAdventQuestStage.setId(mAdventQuestWorld);
        return mAdventQuestStage;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static MAdventQuestStage createUpdatedEntity(EntityManager em) {
        MAdventQuestStage mAdventQuestStage = new MAdventQuestStage()
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
            .deckConditionId(UPDATED_DECK_CONDITION_ID);
        // Add required entity
        MAdventQuestWorld mAdventQuestWorld;
        if (TestUtil.findAll(em, MAdventQuestWorld.class).isEmpty()) {
            mAdventQuestWorld = MAdventQuestWorldResourceIT.createUpdatedEntity(em);
            em.persist(mAdventQuestWorld);
            em.flush();
        } else {
            mAdventQuestWorld = TestUtil.findAll(em, MAdventQuestWorld.class).get(0);
        }
        mAdventQuestStage.setId(mAdventQuestWorld);
        return mAdventQuestStage;
    }

    @BeforeEach
    public void initTest() {
        mAdventQuestStage = createEntity(em);
    }

    @Test
    @Transactional
    public void createMAdventQuestStage() throws Exception {
        int databaseSizeBeforeCreate = mAdventQuestStageRepository.findAll().size();

        // Create the MAdventQuestStage
        MAdventQuestStageDTO mAdventQuestStageDTO = mAdventQuestStageMapper.toDto(mAdventQuestStage);
        restMAdventQuestStageMockMvc.perform(post("/api/m-advent-quest-stages")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mAdventQuestStageDTO)))
            .andExpect(status().isCreated());

        // Validate the MAdventQuestStage in the database
        List<MAdventQuestStage> mAdventQuestStageList = mAdventQuestStageRepository.findAll();
        assertThat(mAdventQuestStageList).hasSize(databaseSizeBeforeCreate + 1);
        MAdventQuestStage testMAdventQuestStage = mAdventQuestStageList.get(mAdventQuestStageList.size() - 1);
        assertThat(testMAdventQuestStage.getWorldId()).isEqualTo(DEFAULT_WORLD_ID);
        assertThat(testMAdventQuestStage.getNumber()).isEqualTo(DEFAULT_NUMBER);
        assertThat(testMAdventQuestStage.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testMAdventQuestStage.getImagePath()).isEqualTo(DEFAULT_IMAGE_PATH);
        assertThat(testMAdventQuestStage.getCharacterThumbnailPath()).isEqualTo(DEFAULT_CHARACTER_THUMBNAIL_PATH);
        assertThat(testMAdventQuestStage.getDifficulty()).isEqualTo(DEFAULT_DIFFICULTY);
        assertThat(testMAdventQuestStage.getStageUnlockPattern()).isEqualTo(DEFAULT_STAGE_UNLOCK_PATTERN);
        assertThat(testMAdventQuestStage.getStoryOnly()).isEqualTo(DEFAULT_STORY_ONLY);
        assertThat(testMAdventQuestStage.getFirstHalfNpcDeckId()).isEqualTo(DEFAULT_FIRST_HALF_NPC_DECK_ID);
        assertThat(testMAdventQuestStage.getFirstHalfEnvironmentId()).isEqualTo(DEFAULT_FIRST_HALF_ENVIRONMENT_ID);
        assertThat(testMAdventQuestStage.getSecondHalfNpcDeckId()).isEqualTo(DEFAULT_SECOND_HALF_NPC_DECK_ID);
        assertThat(testMAdventQuestStage.getSecondHalfEnvironmentId()).isEqualTo(DEFAULT_SECOND_HALF_ENVIRONMENT_ID);
        assertThat(testMAdventQuestStage.getExtraFirstHalfNpcDeckId()).isEqualTo(DEFAULT_EXTRA_FIRST_HALF_NPC_DECK_ID);
        assertThat(testMAdventQuestStage.getExtraSecondHalfNpcDeckId()).isEqualTo(DEFAULT_EXTRA_SECOND_HALF_NPC_DECK_ID);
        assertThat(testMAdventQuestStage.getConsumeAp()).isEqualTo(DEFAULT_CONSUME_AP);
        assertThat(testMAdventQuestStage.getKickOffType()).isEqualTo(DEFAULT_KICK_OFF_TYPE);
        assertThat(testMAdventQuestStage.getMatchMinute()).isEqualTo(DEFAULT_MATCH_MINUTE);
        assertThat(testMAdventQuestStage.getEnableExtra()).isEqualTo(DEFAULT_ENABLE_EXTRA);
        assertThat(testMAdventQuestStage.getEnablePk()).isEqualTo(DEFAULT_ENABLE_PK);
        assertThat(testMAdventQuestStage.getAiLevel()).isEqualTo(DEFAULT_AI_LEVEL);
        assertThat(testMAdventQuestStage.getStartAtSecondHalf()).isEqualTo(DEFAULT_START_AT_SECOND_HALF);
        assertThat(testMAdventQuestStage.getStartScore()).isEqualTo(DEFAULT_START_SCORE);
        assertThat(testMAdventQuestStage.getStartScoreOpponent()).isEqualTo(DEFAULT_START_SCORE_OPPONENT);
        assertThat(testMAdventQuestStage.getConditionId()).isEqualTo(DEFAULT_CONDITION_ID);
        assertThat(testMAdventQuestStage.getOptionId()).isEqualTo(DEFAULT_OPTION_ID);
        assertThat(testMAdventQuestStage.getDeckConditionId()).isEqualTo(DEFAULT_DECK_CONDITION_ID);
    }

    @Test
    @Transactional
    public void createMAdventQuestStageWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = mAdventQuestStageRepository.findAll().size();

        // Create the MAdventQuestStage with an existing ID
        mAdventQuestStage.setId(1L);
        MAdventQuestStageDTO mAdventQuestStageDTO = mAdventQuestStageMapper.toDto(mAdventQuestStage);

        // An entity with an existing ID cannot be created, so this API call must fail
        restMAdventQuestStageMockMvc.perform(post("/api/m-advent-quest-stages")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mAdventQuestStageDTO)))
            .andExpect(status().isBadRequest());

        // Validate the MAdventQuestStage in the database
        List<MAdventQuestStage> mAdventQuestStageList = mAdventQuestStageRepository.findAll();
        assertThat(mAdventQuestStageList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkWorldIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = mAdventQuestStageRepository.findAll().size();
        // set the field null
        mAdventQuestStage.setWorldId(null);

        // Create the MAdventQuestStage, which fails.
        MAdventQuestStageDTO mAdventQuestStageDTO = mAdventQuestStageMapper.toDto(mAdventQuestStage);

        restMAdventQuestStageMockMvc.perform(post("/api/m-advent-quest-stages")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mAdventQuestStageDTO)))
            .andExpect(status().isBadRequest());

        List<MAdventQuestStage> mAdventQuestStageList = mAdventQuestStageRepository.findAll();
        assertThat(mAdventQuestStageList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkNumberIsRequired() throws Exception {
        int databaseSizeBeforeTest = mAdventQuestStageRepository.findAll().size();
        // set the field null
        mAdventQuestStage.setNumber(null);

        // Create the MAdventQuestStage, which fails.
        MAdventQuestStageDTO mAdventQuestStageDTO = mAdventQuestStageMapper.toDto(mAdventQuestStage);

        restMAdventQuestStageMockMvc.perform(post("/api/m-advent-quest-stages")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mAdventQuestStageDTO)))
            .andExpect(status().isBadRequest());

        List<MAdventQuestStage> mAdventQuestStageList = mAdventQuestStageRepository.findAll();
        assertThat(mAdventQuestStageList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkDifficultyIsRequired() throws Exception {
        int databaseSizeBeforeTest = mAdventQuestStageRepository.findAll().size();
        // set the field null
        mAdventQuestStage.setDifficulty(null);

        // Create the MAdventQuestStage, which fails.
        MAdventQuestStageDTO mAdventQuestStageDTO = mAdventQuestStageMapper.toDto(mAdventQuestStage);

        restMAdventQuestStageMockMvc.perform(post("/api/m-advent-quest-stages")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mAdventQuestStageDTO)))
            .andExpect(status().isBadRequest());

        List<MAdventQuestStage> mAdventQuestStageList = mAdventQuestStageRepository.findAll();
        assertThat(mAdventQuestStageList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkStageUnlockPatternIsRequired() throws Exception {
        int databaseSizeBeforeTest = mAdventQuestStageRepository.findAll().size();
        // set the field null
        mAdventQuestStage.setStageUnlockPattern(null);

        // Create the MAdventQuestStage, which fails.
        MAdventQuestStageDTO mAdventQuestStageDTO = mAdventQuestStageMapper.toDto(mAdventQuestStage);

        restMAdventQuestStageMockMvc.perform(post("/api/m-advent-quest-stages")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mAdventQuestStageDTO)))
            .andExpect(status().isBadRequest());

        List<MAdventQuestStage> mAdventQuestStageList = mAdventQuestStageRepository.findAll();
        assertThat(mAdventQuestStageList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkStoryOnlyIsRequired() throws Exception {
        int databaseSizeBeforeTest = mAdventQuestStageRepository.findAll().size();
        // set the field null
        mAdventQuestStage.setStoryOnly(null);

        // Create the MAdventQuestStage, which fails.
        MAdventQuestStageDTO mAdventQuestStageDTO = mAdventQuestStageMapper.toDto(mAdventQuestStage);

        restMAdventQuestStageMockMvc.perform(post("/api/m-advent-quest-stages")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mAdventQuestStageDTO)))
            .andExpect(status().isBadRequest());

        List<MAdventQuestStage> mAdventQuestStageList = mAdventQuestStageRepository.findAll();
        assertThat(mAdventQuestStageList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkFirstHalfNpcDeckIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = mAdventQuestStageRepository.findAll().size();
        // set the field null
        mAdventQuestStage.setFirstHalfNpcDeckId(null);

        // Create the MAdventQuestStage, which fails.
        MAdventQuestStageDTO mAdventQuestStageDTO = mAdventQuestStageMapper.toDto(mAdventQuestStage);

        restMAdventQuestStageMockMvc.perform(post("/api/m-advent-quest-stages")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mAdventQuestStageDTO)))
            .andExpect(status().isBadRequest());

        List<MAdventQuestStage> mAdventQuestStageList = mAdventQuestStageRepository.findAll();
        assertThat(mAdventQuestStageList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkFirstHalfEnvironmentIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = mAdventQuestStageRepository.findAll().size();
        // set the field null
        mAdventQuestStage.setFirstHalfEnvironmentId(null);

        // Create the MAdventQuestStage, which fails.
        MAdventQuestStageDTO mAdventQuestStageDTO = mAdventQuestStageMapper.toDto(mAdventQuestStage);

        restMAdventQuestStageMockMvc.perform(post("/api/m-advent-quest-stages")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mAdventQuestStageDTO)))
            .andExpect(status().isBadRequest());

        List<MAdventQuestStage> mAdventQuestStageList = mAdventQuestStageRepository.findAll();
        assertThat(mAdventQuestStageList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkSecondHalfNpcDeckIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = mAdventQuestStageRepository.findAll().size();
        // set the field null
        mAdventQuestStage.setSecondHalfNpcDeckId(null);

        // Create the MAdventQuestStage, which fails.
        MAdventQuestStageDTO mAdventQuestStageDTO = mAdventQuestStageMapper.toDto(mAdventQuestStage);

        restMAdventQuestStageMockMvc.perform(post("/api/m-advent-quest-stages")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mAdventQuestStageDTO)))
            .andExpect(status().isBadRequest());

        List<MAdventQuestStage> mAdventQuestStageList = mAdventQuestStageRepository.findAll();
        assertThat(mAdventQuestStageList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkSecondHalfEnvironmentIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = mAdventQuestStageRepository.findAll().size();
        // set the field null
        mAdventQuestStage.setSecondHalfEnvironmentId(null);

        // Create the MAdventQuestStage, which fails.
        MAdventQuestStageDTO mAdventQuestStageDTO = mAdventQuestStageMapper.toDto(mAdventQuestStage);

        restMAdventQuestStageMockMvc.perform(post("/api/m-advent-quest-stages")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mAdventQuestStageDTO)))
            .andExpect(status().isBadRequest());

        List<MAdventQuestStage> mAdventQuestStageList = mAdventQuestStageRepository.findAll();
        assertThat(mAdventQuestStageList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkExtraFirstHalfNpcDeckIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = mAdventQuestStageRepository.findAll().size();
        // set the field null
        mAdventQuestStage.setExtraFirstHalfNpcDeckId(null);

        // Create the MAdventQuestStage, which fails.
        MAdventQuestStageDTO mAdventQuestStageDTO = mAdventQuestStageMapper.toDto(mAdventQuestStage);

        restMAdventQuestStageMockMvc.perform(post("/api/m-advent-quest-stages")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mAdventQuestStageDTO)))
            .andExpect(status().isBadRequest());

        List<MAdventQuestStage> mAdventQuestStageList = mAdventQuestStageRepository.findAll();
        assertThat(mAdventQuestStageList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkExtraSecondHalfNpcDeckIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = mAdventQuestStageRepository.findAll().size();
        // set the field null
        mAdventQuestStage.setExtraSecondHalfNpcDeckId(null);

        // Create the MAdventQuestStage, which fails.
        MAdventQuestStageDTO mAdventQuestStageDTO = mAdventQuestStageMapper.toDto(mAdventQuestStage);

        restMAdventQuestStageMockMvc.perform(post("/api/m-advent-quest-stages")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mAdventQuestStageDTO)))
            .andExpect(status().isBadRequest());

        List<MAdventQuestStage> mAdventQuestStageList = mAdventQuestStageRepository.findAll();
        assertThat(mAdventQuestStageList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkConsumeApIsRequired() throws Exception {
        int databaseSizeBeforeTest = mAdventQuestStageRepository.findAll().size();
        // set the field null
        mAdventQuestStage.setConsumeAp(null);

        // Create the MAdventQuestStage, which fails.
        MAdventQuestStageDTO mAdventQuestStageDTO = mAdventQuestStageMapper.toDto(mAdventQuestStage);

        restMAdventQuestStageMockMvc.perform(post("/api/m-advent-quest-stages")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mAdventQuestStageDTO)))
            .andExpect(status().isBadRequest());

        List<MAdventQuestStage> mAdventQuestStageList = mAdventQuestStageRepository.findAll();
        assertThat(mAdventQuestStageList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkKickOffTypeIsRequired() throws Exception {
        int databaseSizeBeforeTest = mAdventQuestStageRepository.findAll().size();
        // set the field null
        mAdventQuestStage.setKickOffType(null);

        // Create the MAdventQuestStage, which fails.
        MAdventQuestStageDTO mAdventQuestStageDTO = mAdventQuestStageMapper.toDto(mAdventQuestStage);

        restMAdventQuestStageMockMvc.perform(post("/api/m-advent-quest-stages")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mAdventQuestStageDTO)))
            .andExpect(status().isBadRequest());

        List<MAdventQuestStage> mAdventQuestStageList = mAdventQuestStageRepository.findAll();
        assertThat(mAdventQuestStageList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkMatchMinuteIsRequired() throws Exception {
        int databaseSizeBeforeTest = mAdventQuestStageRepository.findAll().size();
        // set the field null
        mAdventQuestStage.setMatchMinute(null);

        // Create the MAdventQuestStage, which fails.
        MAdventQuestStageDTO mAdventQuestStageDTO = mAdventQuestStageMapper.toDto(mAdventQuestStage);

        restMAdventQuestStageMockMvc.perform(post("/api/m-advent-quest-stages")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mAdventQuestStageDTO)))
            .andExpect(status().isBadRequest());

        List<MAdventQuestStage> mAdventQuestStageList = mAdventQuestStageRepository.findAll();
        assertThat(mAdventQuestStageList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkEnableExtraIsRequired() throws Exception {
        int databaseSizeBeforeTest = mAdventQuestStageRepository.findAll().size();
        // set the field null
        mAdventQuestStage.setEnableExtra(null);

        // Create the MAdventQuestStage, which fails.
        MAdventQuestStageDTO mAdventQuestStageDTO = mAdventQuestStageMapper.toDto(mAdventQuestStage);

        restMAdventQuestStageMockMvc.perform(post("/api/m-advent-quest-stages")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mAdventQuestStageDTO)))
            .andExpect(status().isBadRequest());

        List<MAdventQuestStage> mAdventQuestStageList = mAdventQuestStageRepository.findAll();
        assertThat(mAdventQuestStageList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkEnablePkIsRequired() throws Exception {
        int databaseSizeBeforeTest = mAdventQuestStageRepository.findAll().size();
        // set the field null
        mAdventQuestStage.setEnablePk(null);

        // Create the MAdventQuestStage, which fails.
        MAdventQuestStageDTO mAdventQuestStageDTO = mAdventQuestStageMapper.toDto(mAdventQuestStage);

        restMAdventQuestStageMockMvc.perform(post("/api/m-advent-quest-stages")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mAdventQuestStageDTO)))
            .andExpect(status().isBadRequest());

        List<MAdventQuestStage> mAdventQuestStageList = mAdventQuestStageRepository.findAll();
        assertThat(mAdventQuestStageList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkAiLevelIsRequired() throws Exception {
        int databaseSizeBeforeTest = mAdventQuestStageRepository.findAll().size();
        // set the field null
        mAdventQuestStage.setAiLevel(null);

        // Create the MAdventQuestStage, which fails.
        MAdventQuestStageDTO mAdventQuestStageDTO = mAdventQuestStageMapper.toDto(mAdventQuestStage);

        restMAdventQuestStageMockMvc.perform(post("/api/m-advent-quest-stages")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mAdventQuestStageDTO)))
            .andExpect(status().isBadRequest());

        List<MAdventQuestStage> mAdventQuestStageList = mAdventQuestStageRepository.findAll();
        assertThat(mAdventQuestStageList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkStartAtSecondHalfIsRequired() throws Exception {
        int databaseSizeBeforeTest = mAdventQuestStageRepository.findAll().size();
        // set the field null
        mAdventQuestStage.setStartAtSecondHalf(null);

        // Create the MAdventQuestStage, which fails.
        MAdventQuestStageDTO mAdventQuestStageDTO = mAdventQuestStageMapper.toDto(mAdventQuestStage);

        restMAdventQuestStageMockMvc.perform(post("/api/m-advent-quest-stages")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mAdventQuestStageDTO)))
            .andExpect(status().isBadRequest());

        List<MAdventQuestStage> mAdventQuestStageList = mAdventQuestStageRepository.findAll();
        assertThat(mAdventQuestStageList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkStartScoreIsRequired() throws Exception {
        int databaseSizeBeforeTest = mAdventQuestStageRepository.findAll().size();
        // set the field null
        mAdventQuestStage.setStartScore(null);

        // Create the MAdventQuestStage, which fails.
        MAdventQuestStageDTO mAdventQuestStageDTO = mAdventQuestStageMapper.toDto(mAdventQuestStage);

        restMAdventQuestStageMockMvc.perform(post("/api/m-advent-quest-stages")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mAdventQuestStageDTO)))
            .andExpect(status().isBadRequest());

        List<MAdventQuestStage> mAdventQuestStageList = mAdventQuestStageRepository.findAll();
        assertThat(mAdventQuestStageList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkStartScoreOpponentIsRequired() throws Exception {
        int databaseSizeBeforeTest = mAdventQuestStageRepository.findAll().size();
        // set the field null
        mAdventQuestStage.setStartScoreOpponent(null);

        // Create the MAdventQuestStage, which fails.
        MAdventQuestStageDTO mAdventQuestStageDTO = mAdventQuestStageMapper.toDto(mAdventQuestStage);

        restMAdventQuestStageMockMvc.perform(post("/api/m-advent-quest-stages")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mAdventQuestStageDTO)))
            .andExpect(status().isBadRequest());

        List<MAdventQuestStage> mAdventQuestStageList = mAdventQuestStageRepository.findAll();
        assertThat(mAdventQuestStageList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllMAdventQuestStages() throws Exception {
        // Initialize the database
        mAdventQuestStageRepository.saveAndFlush(mAdventQuestStage);

        // Get all the mAdventQuestStageList
        restMAdventQuestStageMockMvc.perform(get("/api/m-advent-quest-stages?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(mAdventQuestStage.getId().intValue())))
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
            .andExpect(jsonPath("$.[*].deckConditionId").value(hasItem(DEFAULT_DECK_CONDITION_ID)));
    }
    
    @Test
    @Transactional
    public void getMAdventQuestStage() throws Exception {
        // Initialize the database
        mAdventQuestStageRepository.saveAndFlush(mAdventQuestStage);

        // Get the mAdventQuestStage
        restMAdventQuestStageMockMvc.perform(get("/api/m-advent-quest-stages/{id}", mAdventQuestStage.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(mAdventQuestStage.getId().intValue()))
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
            .andExpect(jsonPath("$.deckConditionId").value(DEFAULT_DECK_CONDITION_ID));
    }

    @Test
    @Transactional
    public void getAllMAdventQuestStagesByWorldIdIsEqualToSomething() throws Exception {
        // Initialize the database
        mAdventQuestStageRepository.saveAndFlush(mAdventQuestStage);

        // Get all the mAdventQuestStageList where worldId equals to DEFAULT_WORLD_ID
        defaultMAdventQuestStageShouldBeFound("worldId.equals=" + DEFAULT_WORLD_ID);

        // Get all the mAdventQuestStageList where worldId equals to UPDATED_WORLD_ID
        defaultMAdventQuestStageShouldNotBeFound("worldId.equals=" + UPDATED_WORLD_ID);
    }

    @Test
    @Transactional
    public void getAllMAdventQuestStagesByWorldIdIsInShouldWork() throws Exception {
        // Initialize the database
        mAdventQuestStageRepository.saveAndFlush(mAdventQuestStage);

        // Get all the mAdventQuestStageList where worldId in DEFAULT_WORLD_ID or UPDATED_WORLD_ID
        defaultMAdventQuestStageShouldBeFound("worldId.in=" + DEFAULT_WORLD_ID + "," + UPDATED_WORLD_ID);

        // Get all the mAdventQuestStageList where worldId equals to UPDATED_WORLD_ID
        defaultMAdventQuestStageShouldNotBeFound("worldId.in=" + UPDATED_WORLD_ID);
    }

    @Test
    @Transactional
    public void getAllMAdventQuestStagesByWorldIdIsNullOrNotNull() throws Exception {
        // Initialize the database
        mAdventQuestStageRepository.saveAndFlush(mAdventQuestStage);

        // Get all the mAdventQuestStageList where worldId is not null
        defaultMAdventQuestStageShouldBeFound("worldId.specified=true");

        // Get all the mAdventQuestStageList where worldId is null
        defaultMAdventQuestStageShouldNotBeFound("worldId.specified=false");
    }

    @Test
    @Transactional
    public void getAllMAdventQuestStagesByWorldIdIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mAdventQuestStageRepository.saveAndFlush(mAdventQuestStage);

        // Get all the mAdventQuestStageList where worldId greater than or equals to DEFAULT_WORLD_ID
        defaultMAdventQuestStageShouldBeFound("worldId.greaterOrEqualThan=" + DEFAULT_WORLD_ID);

        // Get all the mAdventQuestStageList where worldId greater than or equals to UPDATED_WORLD_ID
        defaultMAdventQuestStageShouldNotBeFound("worldId.greaterOrEqualThan=" + UPDATED_WORLD_ID);
    }

    @Test
    @Transactional
    public void getAllMAdventQuestStagesByWorldIdIsLessThanSomething() throws Exception {
        // Initialize the database
        mAdventQuestStageRepository.saveAndFlush(mAdventQuestStage);

        // Get all the mAdventQuestStageList where worldId less than or equals to DEFAULT_WORLD_ID
        defaultMAdventQuestStageShouldNotBeFound("worldId.lessThan=" + DEFAULT_WORLD_ID);

        // Get all the mAdventQuestStageList where worldId less than or equals to UPDATED_WORLD_ID
        defaultMAdventQuestStageShouldBeFound("worldId.lessThan=" + UPDATED_WORLD_ID);
    }


    @Test
    @Transactional
    public void getAllMAdventQuestStagesByNumberIsEqualToSomething() throws Exception {
        // Initialize the database
        mAdventQuestStageRepository.saveAndFlush(mAdventQuestStage);

        // Get all the mAdventQuestStageList where number equals to DEFAULT_NUMBER
        defaultMAdventQuestStageShouldBeFound("number.equals=" + DEFAULT_NUMBER);

        // Get all the mAdventQuestStageList where number equals to UPDATED_NUMBER
        defaultMAdventQuestStageShouldNotBeFound("number.equals=" + UPDATED_NUMBER);
    }

    @Test
    @Transactional
    public void getAllMAdventQuestStagesByNumberIsInShouldWork() throws Exception {
        // Initialize the database
        mAdventQuestStageRepository.saveAndFlush(mAdventQuestStage);

        // Get all the mAdventQuestStageList where number in DEFAULT_NUMBER or UPDATED_NUMBER
        defaultMAdventQuestStageShouldBeFound("number.in=" + DEFAULT_NUMBER + "," + UPDATED_NUMBER);

        // Get all the mAdventQuestStageList where number equals to UPDATED_NUMBER
        defaultMAdventQuestStageShouldNotBeFound("number.in=" + UPDATED_NUMBER);
    }

    @Test
    @Transactional
    public void getAllMAdventQuestStagesByNumberIsNullOrNotNull() throws Exception {
        // Initialize the database
        mAdventQuestStageRepository.saveAndFlush(mAdventQuestStage);

        // Get all the mAdventQuestStageList where number is not null
        defaultMAdventQuestStageShouldBeFound("number.specified=true");

        // Get all the mAdventQuestStageList where number is null
        defaultMAdventQuestStageShouldNotBeFound("number.specified=false");
    }

    @Test
    @Transactional
    public void getAllMAdventQuestStagesByNumberIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mAdventQuestStageRepository.saveAndFlush(mAdventQuestStage);

        // Get all the mAdventQuestStageList where number greater than or equals to DEFAULT_NUMBER
        defaultMAdventQuestStageShouldBeFound("number.greaterOrEqualThan=" + DEFAULT_NUMBER);

        // Get all the mAdventQuestStageList where number greater than or equals to UPDATED_NUMBER
        defaultMAdventQuestStageShouldNotBeFound("number.greaterOrEqualThan=" + UPDATED_NUMBER);
    }

    @Test
    @Transactional
    public void getAllMAdventQuestStagesByNumberIsLessThanSomething() throws Exception {
        // Initialize the database
        mAdventQuestStageRepository.saveAndFlush(mAdventQuestStage);

        // Get all the mAdventQuestStageList where number less than or equals to DEFAULT_NUMBER
        defaultMAdventQuestStageShouldNotBeFound("number.lessThan=" + DEFAULT_NUMBER);

        // Get all the mAdventQuestStageList where number less than or equals to UPDATED_NUMBER
        defaultMAdventQuestStageShouldBeFound("number.lessThan=" + UPDATED_NUMBER);
    }


    @Test
    @Transactional
    public void getAllMAdventQuestStagesByDifficultyIsEqualToSomething() throws Exception {
        // Initialize the database
        mAdventQuestStageRepository.saveAndFlush(mAdventQuestStage);

        // Get all the mAdventQuestStageList where difficulty equals to DEFAULT_DIFFICULTY
        defaultMAdventQuestStageShouldBeFound("difficulty.equals=" + DEFAULT_DIFFICULTY);

        // Get all the mAdventQuestStageList where difficulty equals to UPDATED_DIFFICULTY
        defaultMAdventQuestStageShouldNotBeFound("difficulty.equals=" + UPDATED_DIFFICULTY);
    }

    @Test
    @Transactional
    public void getAllMAdventQuestStagesByDifficultyIsInShouldWork() throws Exception {
        // Initialize the database
        mAdventQuestStageRepository.saveAndFlush(mAdventQuestStage);

        // Get all the mAdventQuestStageList where difficulty in DEFAULT_DIFFICULTY or UPDATED_DIFFICULTY
        defaultMAdventQuestStageShouldBeFound("difficulty.in=" + DEFAULT_DIFFICULTY + "," + UPDATED_DIFFICULTY);

        // Get all the mAdventQuestStageList where difficulty equals to UPDATED_DIFFICULTY
        defaultMAdventQuestStageShouldNotBeFound("difficulty.in=" + UPDATED_DIFFICULTY);
    }

    @Test
    @Transactional
    public void getAllMAdventQuestStagesByDifficultyIsNullOrNotNull() throws Exception {
        // Initialize the database
        mAdventQuestStageRepository.saveAndFlush(mAdventQuestStage);

        // Get all the mAdventQuestStageList where difficulty is not null
        defaultMAdventQuestStageShouldBeFound("difficulty.specified=true");

        // Get all the mAdventQuestStageList where difficulty is null
        defaultMAdventQuestStageShouldNotBeFound("difficulty.specified=false");
    }

    @Test
    @Transactional
    public void getAllMAdventQuestStagesByDifficultyIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mAdventQuestStageRepository.saveAndFlush(mAdventQuestStage);

        // Get all the mAdventQuestStageList where difficulty greater than or equals to DEFAULT_DIFFICULTY
        defaultMAdventQuestStageShouldBeFound("difficulty.greaterOrEqualThan=" + DEFAULT_DIFFICULTY);

        // Get all the mAdventQuestStageList where difficulty greater than or equals to UPDATED_DIFFICULTY
        defaultMAdventQuestStageShouldNotBeFound("difficulty.greaterOrEqualThan=" + UPDATED_DIFFICULTY);
    }

    @Test
    @Transactional
    public void getAllMAdventQuestStagesByDifficultyIsLessThanSomething() throws Exception {
        // Initialize the database
        mAdventQuestStageRepository.saveAndFlush(mAdventQuestStage);

        // Get all the mAdventQuestStageList where difficulty less than or equals to DEFAULT_DIFFICULTY
        defaultMAdventQuestStageShouldNotBeFound("difficulty.lessThan=" + DEFAULT_DIFFICULTY);

        // Get all the mAdventQuestStageList where difficulty less than or equals to UPDATED_DIFFICULTY
        defaultMAdventQuestStageShouldBeFound("difficulty.lessThan=" + UPDATED_DIFFICULTY);
    }


    @Test
    @Transactional
    public void getAllMAdventQuestStagesByStageUnlockPatternIsEqualToSomething() throws Exception {
        // Initialize the database
        mAdventQuestStageRepository.saveAndFlush(mAdventQuestStage);

        // Get all the mAdventQuestStageList where stageUnlockPattern equals to DEFAULT_STAGE_UNLOCK_PATTERN
        defaultMAdventQuestStageShouldBeFound("stageUnlockPattern.equals=" + DEFAULT_STAGE_UNLOCK_PATTERN);

        // Get all the mAdventQuestStageList where stageUnlockPattern equals to UPDATED_STAGE_UNLOCK_PATTERN
        defaultMAdventQuestStageShouldNotBeFound("stageUnlockPattern.equals=" + UPDATED_STAGE_UNLOCK_PATTERN);
    }

    @Test
    @Transactional
    public void getAllMAdventQuestStagesByStageUnlockPatternIsInShouldWork() throws Exception {
        // Initialize the database
        mAdventQuestStageRepository.saveAndFlush(mAdventQuestStage);

        // Get all the mAdventQuestStageList where stageUnlockPattern in DEFAULT_STAGE_UNLOCK_PATTERN or UPDATED_STAGE_UNLOCK_PATTERN
        defaultMAdventQuestStageShouldBeFound("stageUnlockPattern.in=" + DEFAULT_STAGE_UNLOCK_PATTERN + "," + UPDATED_STAGE_UNLOCK_PATTERN);

        // Get all the mAdventQuestStageList where stageUnlockPattern equals to UPDATED_STAGE_UNLOCK_PATTERN
        defaultMAdventQuestStageShouldNotBeFound("stageUnlockPattern.in=" + UPDATED_STAGE_UNLOCK_PATTERN);
    }

    @Test
    @Transactional
    public void getAllMAdventQuestStagesByStageUnlockPatternIsNullOrNotNull() throws Exception {
        // Initialize the database
        mAdventQuestStageRepository.saveAndFlush(mAdventQuestStage);

        // Get all the mAdventQuestStageList where stageUnlockPattern is not null
        defaultMAdventQuestStageShouldBeFound("stageUnlockPattern.specified=true");

        // Get all the mAdventQuestStageList where stageUnlockPattern is null
        defaultMAdventQuestStageShouldNotBeFound("stageUnlockPattern.specified=false");
    }

    @Test
    @Transactional
    public void getAllMAdventQuestStagesByStageUnlockPatternIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mAdventQuestStageRepository.saveAndFlush(mAdventQuestStage);

        // Get all the mAdventQuestStageList where stageUnlockPattern greater than or equals to DEFAULT_STAGE_UNLOCK_PATTERN
        defaultMAdventQuestStageShouldBeFound("stageUnlockPattern.greaterOrEqualThan=" + DEFAULT_STAGE_UNLOCK_PATTERN);

        // Get all the mAdventQuestStageList where stageUnlockPattern greater than or equals to UPDATED_STAGE_UNLOCK_PATTERN
        defaultMAdventQuestStageShouldNotBeFound("stageUnlockPattern.greaterOrEqualThan=" + UPDATED_STAGE_UNLOCK_PATTERN);
    }

    @Test
    @Transactional
    public void getAllMAdventQuestStagesByStageUnlockPatternIsLessThanSomething() throws Exception {
        // Initialize the database
        mAdventQuestStageRepository.saveAndFlush(mAdventQuestStage);

        // Get all the mAdventQuestStageList where stageUnlockPattern less than or equals to DEFAULT_STAGE_UNLOCK_PATTERN
        defaultMAdventQuestStageShouldNotBeFound("stageUnlockPattern.lessThan=" + DEFAULT_STAGE_UNLOCK_PATTERN);

        // Get all the mAdventQuestStageList where stageUnlockPattern less than or equals to UPDATED_STAGE_UNLOCK_PATTERN
        defaultMAdventQuestStageShouldBeFound("stageUnlockPattern.lessThan=" + UPDATED_STAGE_UNLOCK_PATTERN);
    }


    @Test
    @Transactional
    public void getAllMAdventQuestStagesByStoryOnlyIsEqualToSomething() throws Exception {
        // Initialize the database
        mAdventQuestStageRepository.saveAndFlush(mAdventQuestStage);

        // Get all the mAdventQuestStageList where storyOnly equals to DEFAULT_STORY_ONLY
        defaultMAdventQuestStageShouldBeFound("storyOnly.equals=" + DEFAULT_STORY_ONLY);

        // Get all the mAdventQuestStageList where storyOnly equals to UPDATED_STORY_ONLY
        defaultMAdventQuestStageShouldNotBeFound("storyOnly.equals=" + UPDATED_STORY_ONLY);
    }

    @Test
    @Transactional
    public void getAllMAdventQuestStagesByStoryOnlyIsInShouldWork() throws Exception {
        // Initialize the database
        mAdventQuestStageRepository.saveAndFlush(mAdventQuestStage);

        // Get all the mAdventQuestStageList where storyOnly in DEFAULT_STORY_ONLY or UPDATED_STORY_ONLY
        defaultMAdventQuestStageShouldBeFound("storyOnly.in=" + DEFAULT_STORY_ONLY + "," + UPDATED_STORY_ONLY);

        // Get all the mAdventQuestStageList where storyOnly equals to UPDATED_STORY_ONLY
        defaultMAdventQuestStageShouldNotBeFound("storyOnly.in=" + UPDATED_STORY_ONLY);
    }

    @Test
    @Transactional
    public void getAllMAdventQuestStagesByStoryOnlyIsNullOrNotNull() throws Exception {
        // Initialize the database
        mAdventQuestStageRepository.saveAndFlush(mAdventQuestStage);

        // Get all the mAdventQuestStageList where storyOnly is not null
        defaultMAdventQuestStageShouldBeFound("storyOnly.specified=true");

        // Get all the mAdventQuestStageList where storyOnly is null
        defaultMAdventQuestStageShouldNotBeFound("storyOnly.specified=false");
    }

    @Test
    @Transactional
    public void getAllMAdventQuestStagesByStoryOnlyIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mAdventQuestStageRepository.saveAndFlush(mAdventQuestStage);

        // Get all the mAdventQuestStageList where storyOnly greater than or equals to DEFAULT_STORY_ONLY
        defaultMAdventQuestStageShouldBeFound("storyOnly.greaterOrEqualThan=" + DEFAULT_STORY_ONLY);

        // Get all the mAdventQuestStageList where storyOnly greater than or equals to UPDATED_STORY_ONLY
        defaultMAdventQuestStageShouldNotBeFound("storyOnly.greaterOrEqualThan=" + UPDATED_STORY_ONLY);
    }

    @Test
    @Transactional
    public void getAllMAdventQuestStagesByStoryOnlyIsLessThanSomething() throws Exception {
        // Initialize the database
        mAdventQuestStageRepository.saveAndFlush(mAdventQuestStage);

        // Get all the mAdventQuestStageList where storyOnly less than or equals to DEFAULT_STORY_ONLY
        defaultMAdventQuestStageShouldNotBeFound("storyOnly.lessThan=" + DEFAULT_STORY_ONLY);

        // Get all the mAdventQuestStageList where storyOnly less than or equals to UPDATED_STORY_ONLY
        defaultMAdventQuestStageShouldBeFound("storyOnly.lessThan=" + UPDATED_STORY_ONLY);
    }


    @Test
    @Transactional
    public void getAllMAdventQuestStagesByFirstHalfNpcDeckIdIsEqualToSomething() throws Exception {
        // Initialize the database
        mAdventQuestStageRepository.saveAndFlush(mAdventQuestStage);

        // Get all the mAdventQuestStageList where firstHalfNpcDeckId equals to DEFAULT_FIRST_HALF_NPC_DECK_ID
        defaultMAdventQuestStageShouldBeFound("firstHalfNpcDeckId.equals=" + DEFAULT_FIRST_HALF_NPC_DECK_ID);

        // Get all the mAdventQuestStageList where firstHalfNpcDeckId equals to UPDATED_FIRST_HALF_NPC_DECK_ID
        defaultMAdventQuestStageShouldNotBeFound("firstHalfNpcDeckId.equals=" + UPDATED_FIRST_HALF_NPC_DECK_ID);
    }

    @Test
    @Transactional
    public void getAllMAdventQuestStagesByFirstHalfNpcDeckIdIsInShouldWork() throws Exception {
        // Initialize the database
        mAdventQuestStageRepository.saveAndFlush(mAdventQuestStage);

        // Get all the mAdventQuestStageList where firstHalfNpcDeckId in DEFAULT_FIRST_HALF_NPC_DECK_ID or UPDATED_FIRST_HALF_NPC_DECK_ID
        defaultMAdventQuestStageShouldBeFound("firstHalfNpcDeckId.in=" + DEFAULT_FIRST_HALF_NPC_DECK_ID + "," + UPDATED_FIRST_HALF_NPC_DECK_ID);

        // Get all the mAdventQuestStageList where firstHalfNpcDeckId equals to UPDATED_FIRST_HALF_NPC_DECK_ID
        defaultMAdventQuestStageShouldNotBeFound("firstHalfNpcDeckId.in=" + UPDATED_FIRST_HALF_NPC_DECK_ID);
    }

    @Test
    @Transactional
    public void getAllMAdventQuestStagesByFirstHalfNpcDeckIdIsNullOrNotNull() throws Exception {
        // Initialize the database
        mAdventQuestStageRepository.saveAndFlush(mAdventQuestStage);

        // Get all the mAdventQuestStageList where firstHalfNpcDeckId is not null
        defaultMAdventQuestStageShouldBeFound("firstHalfNpcDeckId.specified=true");

        // Get all the mAdventQuestStageList where firstHalfNpcDeckId is null
        defaultMAdventQuestStageShouldNotBeFound("firstHalfNpcDeckId.specified=false");
    }

    @Test
    @Transactional
    public void getAllMAdventQuestStagesByFirstHalfNpcDeckIdIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mAdventQuestStageRepository.saveAndFlush(mAdventQuestStage);

        // Get all the mAdventQuestStageList where firstHalfNpcDeckId greater than or equals to DEFAULT_FIRST_HALF_NPC_DECK_ID
        defaultMAdventQuestStageShouldBeFound("firstHalfNpcDeckId.greaterOrEqualThan=" + DEFAULT_FIRST_HALF_NPC_DECK_ID);

        // Get all the mAdventQuestStageList where firstHalfNpcDeckId greater than or equals to UPDATED_FIRST_HALF_NPC_DECK_ID
        defaultMAdventQuestStageShouldNotBeFound("firstHalfNpcDeckId.greaterOrEqualThan=" + UPDATED_FIRST_HALF_NPC_DECK_ID);
    }

    @Test
    @Transactional
    public void getAllMAdventQuestStagesByFirstHalfNpcDeckIdIsLessThanSomething() throws Exception {
        // Initialize the database
        mAdventQuestStageRepository.saveAndFlush(mAdventQuestStage);

        // Get all the mAdventQuestStageList where firstHalfNpcDeckId less than or equals to DEFAULT_FIRST_HALF_NPC_DECK_ID
        defaultMAdventQuestStageShouldNotBeFound("firstHalfNpcDeckId.lessThan=" + DEFAULT_FIRST_HALF_NPC_DECK_ID);

        // Get all the mAdventQuestStageList where firstHalfNpcDeckId less than or equals to UPDATED_FIRST_HALF_NPC_DECK_ID
        defaultMAdventQuestStageShouldBeFound("firstHalfNpcDeckId.lessThan=" + UPDATED_FIRST_HALF_NPC_DECK_ID);
    }


    @Test
    @Transactional
    public void getAllMAdventQuestStagesByFirstHalfEnvironmentIdIsEqualToSomething() throws Exception {
        // Initialize the database
        mAdventQuestStageRepository.saveAndFlush(mAdventQuestStage);

        // Get all the mAdventQuestStageList where firstHalfEnvironmentId equals to DEFAULT_FIRST_HALF_ENVIRONMENT_ID
        defaultMAdventQuestStageShouldBeFound("firstHalfEnvironmentId.equals=" + DEFAULT_FIRST_HALF_ENVIRONMENT_ID);

        // Get all the mAdventQuestStageList where firstHalfEnvironmentId equals to UPDATED_FIRST_HALF_ENVIRONMENT_ID
        defaultMAdventQuestStageShouldNotBeFound("firstHalfEnvironmentId.equals=" + UPDATED_FIRST_HALF_ENVIRONMENT_ID);
    }

    @Test
    @Transactional
    public void getAllMAdventQuestStagesByFirstHalfEnvironmentIdIsInShouldWork() throws Exception {
        // Initialize the database
        mAdventQuestStageRepository.saveAndFlush(mAdventQuestStage);

        // Get all the mAdventQuestStageList where firstHalfEnvironmentId in DEFAULT_FIRST_HALF_ENVIRONMENT_ID or UPDATED_FIRST_HALF_ENVIRONMENT_ID
        defaultMAdventQuestStageShouldBeFound("firstHalfEnvironmentId.in=" + DEFAULT_FIRST_HALF_ENVIRONMENT_ID + "," + UPDATED_FIRST_HALF_ENVIRONMENT_ID);

        // Get all the mAdventQuestStageList where firstHalfEnvironmentId equals to UPDATED_FIRST_HALF_ENVIRONMENT_ID
        defaultMAdventQuestStageShouldNotBeFound("firstHalfEnvironmentId.in=" + UPDATED_FIRST_HALF_ENVIRONMENT_ID);
    }

    @Test
    @Transactional
    public void getAllMAdventQuestStagesByFirstHalfEnvironmentIdIsNullOrNotNull() throws Exception {
        // Initialize the database
        mAdventQuestStageRepository.saveAndFlush(mAdventQuestStage);

        // Get all the mAdventQuestStageList where firstHalfEnvironmentId is not null
        defaultMAdventQuestStageShouldBeFound("firstHalfEnvironmentId.specified=true");

        // Get all the mAdventQuestStageList where firstHalfEnvironmentId is null
        defaultMAdventQuestStageShouldNotBeFound("firstHalfEnvironmentId.specified=false");
    }

    @Test
    @Transactional
    public void getAllMAdventQuestStagesByFirstHalfEnvironmentIdIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mAdventQuestStageRepository.saveAndFlush(mAdventQuestStage);

        // Get all the mAdventQuestStageList where firstHalfEnvironmentId greater than or equals to DEFAULT_FIRST_HALF_ENVIRONMENT_ID
        defaultMAdventQuestStageShouldBeFound("firstHalfEnvironmentId.greaterOrEqualThan=" + DEFAULT_FIRST_HALF_ENVIRONMENT_ID);

        // Get all the mAdventQuestStageList where firstHalfEnvironmentId greater than or equals to UPDATED_FIRST_HALF_ENVIRONMENT_ID
        defaultMAdventQuestStageShouldNotBeFound("firstHalfEnvironmentId.greaterOrEqualThan=" + UPDATED_FIRST_HALF_ENVIRONMENT_ID);
    }

    @Test
    @Transactional
    public void getAllMAdventQuestStagesByFirstHalfEnvironmentIdIsLessThanSomething() throws Exception {
        // Initialize the database
        mAdventQuestStageRepository.saveAndFlush(mAdventQuestStage);

        // Get all the mAdventQuestStageList where firstHalfEnvironmentId less than or equals to DEFAULT_FIRST_HALF_ENVIRONMENT_ID
        defaultMAdventQuestStageShouldNotBeFound("firstHalfEnvironmentId.lessThan=" + DEFAULT_FIRST_HALF_ENVIRONMENT_ID);

        // Get all the mAdventQuestStageList where firstHalfEnvironmentId less than or equals to UPDATED_FIRST_HALF_ENVIRONMENT_ID
        defaultMAdventQuestStageShouldBeFound("firstHalfEnvironmentId.lessThan=" + UPDATED_FIRST_HALF_ENVIRONMENT_ID);
    }


    @Test
    @Transactional
    public void getAllMAdventQuestStagesBySecondHalfNpcDeckIdIsEqualToSomething() throws Exception {
        // Initialize the database
        mAdventQuestStageRepository.saveAndFlush(mAdventQuestStage);

        // Get all the mAdventQuestStageList where secondHalfNpcDeckId equals to DEFAULT_SECOND_HALF_NPC_DECK_ID
        defaultMAdventQuestStageShouldBeFound("secondHalfNpcDeckId.equals=" + DEFAULT_SECOND_HALF_NPC_DECK_ID);

        // Get all the mAdventQuestStageList where secondHalfNpcDeckId equals to UPDATED_SECOND_HALF_NPC_DECK_ID
        defaultMAdventQuestStageShouldNotBeFound("secondHalfNpcDeckId.equals=" + UPDATED_SECOND_HALF_NPC_DECK_ID);
    }

    @Test
    @Transactional
    public void getAllMAdventQuestStagesBySecondHalfNpcDeckIdIsInShouldWork() throws Exception {
        // Initialize the database
        mAdventQuestStageRepository.saveAndFlush(mAdventQuestStage);

        // Get all the mAdventQuestStageList where secondHalfNpcDeckId in DEFAULT_SECOND_HALF_NPC_DECK_ID or UPDATED_SECOND_HALF_NPC_DECK_ID
        defaultMAdventQuestStageShouldBeFound("secondHalfNpcDeckId.in=" + DEFAULT_SECOND_HALF_NPC_DECK_ID + "," + UPDATED_SECOND_HALF_NPC_DECK_ID);

        // Get all the mAdventQuestStageList where secondHalfNpcDeckId equals to UPDATED_SECOND_HALF_NPC_DECK_ID
        defaultMAdventQuestStageShouldNotBeFound("secondHalfNpcDeckId.in=" + UPDATED_SECOND_HALF_NPC_DECK_ID);
    }

    @Test
    @Transactional
    public void getAllMAdventQuestStagesBySecondHalfNpcDeckIdIsNullOrNotNull() throws Exception {
        // Initialize the database
        mAdventQuestStageRepository.saveAndFlush(mAdventQuestStage);

        // Get all the mAdventQuestStageList where secondHalfNpcDeckId is not null
        defaultMAdventQuestStageShouldBeFound("secondHalfNpcDeckId.specified=true");

        // Get all the mAdventQuestStageList where secondHalfNpcDeckId is null
        defaultMAdventQuestStageShouldNotBeFound("secondHalfNpcDeckId.specified=false");
    }

    @Test
    @Transactional
    public void getAllMAdventQuestStagesBySecondHalfNpcDeckIdIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mAdventQuestStageRepository.saveAndFlush(mAdventQuestStage);

        // Get all the mAdventQuestStageList where secondHalfNpcDeckId greater than or equals to DEFAULT_SECOND_HALF_NPC_DECK_ID
        defaultMAdventQuestStageShouldBeFound("secondHalfNpcDeckId.greaterOrEqualThan=" + DEFAULT_SECOND_HALF_NPC_DECK_ID);

        // Get all the mAdventQuestStageList where secondHalfNpcDeckId greater than or equals to UPDATED_SECOND_HALF_NPC_DECK_ID
        defaultMAdventQuestStageShouldNotBeFound("secondHalfNpcDeckId.greaterOrEqualThan=" + UPDATED_SECOND_HALF_NPC_DECK_ID);
    }

    @Test
    @Transactional
    public void getAllMAdventQuestStagesBySecondHalfNpcDeckIdIsLessThanSomething() throws Exception {
        // Initialize the database
        mAdventQuestStageRepository.saveAndFlush(mAdventQuestStage);

        // Get all the mAdventQuestStageList where secondHalfNpcDeckId less than or equals to DEFAULT_SECOND_HALF_NPC_DECK_ID
        defaultMAdventQuestStageShouldNotBeFound("secondHalfNpcDeckId.lessThan=" + DEFAULT_SECOND_HALF_NPC_DECK_ID);

        // Get all the mAdventQuestStageList where secondHalfNpcDeckId less than or equals to UPDATED_SECOND_HALF_NPC_DECK_ID
        defaultMAdventQuestStageShouldBeFound("secondHalfNpcDeckId.lessThan=" + UPDATED_SECOND_HALF_NPC_DECK_ID);
    }


    @Test
    @Transactional
    public void getAllMAdventQuestStagesBySecondHalfEnvironmentIdIsEqualToSomething() throws Exception {
        // Initialize the database
        mAdventQuestStageRepository.saveAndFlush(mAdventQuestStage);

        // Get all the mAdventQuestStageList where secondHalfEnvironmentId equals to DEFAULT_SECOND_HALF_ENVIRONMENT_ID
        defaultMAdventQuestStageShouldBeFound("secondHalfEnvironmentId.equals=" + DEFAULT_SECOND_HALF_ENVIRONMENT_ID);

        // Get all the mAdventQuestStageList where secondHalfEnvironmentId equals to UPDATED_SECOND_HALF_ENVIRONMENT_ID
        defaultMAdventQuestStageShouldNotBeFound("secondHalfEnvironmentId.equals=" + UPDATED_SECOND_HALF_ENVIRONMENT_ID);
    }

    @Test
    @Transactional
    public void getAllMAdventQuestStagesBySecondHalfEnvironmentIdIsInShouldWork() throws Exception {
        // Initialize the database
        mAdventQuestStageRepository.saveAndFlush(mAdventQuestStage);

        // Get all the mAdventQuestStageList where secondHalfEnvironmentId in DEFAULT_SECOND_HALF_ENVIRONMENT_ID or UPDATED_SECOND_HALF_ENVIRONMENT_ID
        defaultMAdventQuestStageShouldBeFound("secondHalfEnvironmentId.in=" + DEFAULT_SECOND_HALF_ENVIRONMENT_ID + "," + UPDATED_SECOND_HALF_ENVIRONMENT_ID);

        // Get all the mAdventQuestStageList where secondHalfEnvironmentId equals to UPDATED_SECOND_HALF_ENVIRONMENT_ID
        defaultMAdventQuestStageShouldNotBeFound("secondHalfEnvironmentId.in=" + UPDATED_SECOND_HALF_ENVIRONMENT_ID);
    }

    @Test
    @Transactional
    public void getAllMAdventQuestStagesBySecondHalfEnvironmentIdIsNullOrNotNull() throws Exception {
        // Initialize the database
        mAdventQuestStageRepository.saveAndFlush(mAdventQuestStage);

        // Get all the mAdventQuestStageList where secondHalfEnvironmentId is not null
        defaultMAdventQuestStageShouldBeFound("secondHalfEnvironmentId.specified=true");

        // Get all the mAdventQuestStageList where secondHalfEnvironmentId is null
        defaultMAdventQuestStageShouldNotBeFound("secondHalfEnvironmentId.specified=false");
    }

    @Test
    @Transactional
    public void getAllMAdventQuestStagesBySecondHalfEnvironmentIdIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mAdventQuestStageRepository.saveAndFlush(mAdventQuestStage);

        // Get all the mAdventQuestStageList where secondHalfEnvironmentId greater than or equals to DEFAULT_SECOND_HALF_ENVIRONMENT_ID
        defaultMAdventQuestStageShouldBeFound("secondHalfEnvironmentId.greaterOrEqualThan=" + DEFAULT_SECOND_HALF_ENVIRONMENT_ID);

        // Get all the mAdventQuestStageList where secondHalfEnvironmentId greater than or equals to UPDATED_SECOND_HALF_ENVIRONMENT_ID
        defaultMAdventQuestStageShouldNotBeFound("secondHalfEnvironmentId.greaterOrEqualThan=" + UPDATED_SECOND_HALF_ENVIRONMENT_ID);
    }

    @Test
    @Transactional
    public void getAllMAdventQuestStagesBySecondHalfEnvironmentIdIsLessThanSomething() throws Exception {
        // Initialize the database
        mAdventQuestStageRepository.saveAndFlush(mAdventQuestStage);

        // Get all the mAdventQuestStageList where secondHalfEnvironmentId less than or equals to DEFAULT_SECOND_HALF_ENVIRONMENT_ID
        defaultMAdventQuestStageShouldNotBeFound("secondHalfEnvironmentId.lessThan=" + DEFAULT_SECOND_HALF_ENVIRONMENT_ID);

        // Get all the mAdventQuestStageList where secondHalfEnvironmentId less than or equals to UPDATED_SECOND_HALF_ENVIRONMENT_ID
        defaultMAdventQuestStageShouldBeFound("secondHalfEnvironmentId.lessThan=" + UPDATED_SECOND_HALF_ENVIRONMENT_ID);
    }


    @Test
    @Transactional
    public void getAllMAdventQuestStagesByExtraFirstHalfNpcDeckIdIsEqualToSomething() throws Exception {
        // Initialize the database
        mAdventQuestStageRepository.saveAndFlush(mAdventQuestStage);

        // Get all the mAdventQuestStageList where extraFirstHalfNpcDeckId equals to DEFAULT_EXTRA_FIRST_HALF_NPC_DECK_ID
        defaultMAdventQuestStageShouldBeFound("extraFirstHalfNpcDeckId.equals=" + DEFAULT_EXTRA_FIRST_HALF_NPC_DECK_ID);

        // Get all the mAdventQuestStageList where extraFirstHalfNpcDeckId equals to UPDATED_EXTRA_FIRST_HALF_NPC_DECK_ID
        defaultMAdventQuestStageShouldNotBeFound("extraFirstHalfNpcDeckId.equals=" + UPDATED_EXTRA_FIRST_HALF_NPC_DECK_ID);
    }

    @Test
    @Transactional
    public void getAllMAdventQuestStagesByExtraFirstHalfNpcDeckIdIsInShouldWork() throws Exception {
        // Initialize the database
        mAdventQuestStageRepository.saveAndFlush(mAdventQuestStage);

        // Get all the mAdventQuestStageList where extraFirstHalfNpcDeckId in DEFAULT_EXTRA_FIRST_HALF_NPC_DECK_ID or UPDATED_EXTRA_FIRST_HALF_NPC_DECK_ID
        defaultMAdventQuestStageShouldBeFound("extraFirstHalfNpcDeckId.in=" + DEFAULT_EXTRA_FIRST_HALF_NPC_DECK_ID + "," + UPDATED_EXTRA_FIRST_HALF_NPC_DECK_ID);

        // Get all the mAdventQuestStageList where extraFirstHalfNpcDeckId equals to UPDATED_EXTRA_FIRST_HALF_NPC_DECK_ID
        defaultMAdventQuestStageShouldNotBeFound("extraFirstHalfNpcDeckId.in=" + UPDATED_EXTRA_FIRST_HALF_NPC_DECK_ID);
    }

    @Test
    @Transactional
    public void getAllMAdventQuestStagesByExtraFirstHalfNpcDeckIdIsNullOrNotNull() throws Exception {
        // Initialize the database
        mAdventQuestStageRepository.saveAndFlush(mAdventQuestStage);

        // Get all the mAdventQuestStageList where extraFirstHalfNpcDeckId is not null
        defaultMAdventQuestStageShouldBeFound("extraFirstHalfNpcDeckId.specified=true");

        // Get all the mAdventQuestStageList where extraFirstHalfNpcDeckId is null
        defaultMAdventQuestStageShouldNotBeFound("extraFirstHalfNpcDeckId.specified=false");
    }

    @Test
    @Transactional
    public void getAllMAdventQuestStagesByExtraFirstHalfNpcDeckIdIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mAdventQuestStageRepository.saveAndFlush(mAdventQuestStage);

        // Get all the mAdventQuestStageList where extraFirstHalfNpcDeckId greater than or equals to DEFAULT_EXTRA_FIRST_HALF_NPC_DECK_ID
        defaultMAdventQuestStageShouldBeFound("extraFirstHalfNpcDeckId.greaterOrEqualThan=" + DEFAULT_EXTRA_FIRST_HALF_NPC_DECK_ID);

        // Get all the mAdventQuestStageList where extraFirstHalfNpcDeckId greater than or equals to UPDATED_EXTRA_FIRST_HALF_NPC_DECK_ID
        defaultMAdventQuestStageShouldNotBeFound("extraFirstHalfNpcDeckId.greaterOrEqualThan=" + UPDATED_EXTRA_FIRST_HALF_NPC_DECK_ID);
    }

    @Test
    @Transactional
    public void getAllMAdventQuestStagesByExtraFirstHalfNpcDeckIdIsLessThanSomething() throws Exception {
        // Initialize the database
        mAdventQuestStageRepository.saveAndFlush(mAdventQuestStage);

        // Get all the mAdventQuestStageList where extraFirstHalfNpcDeckId less than or equals to DEFAULT_EXTRA_FIRST_HALF_NPC_DECK_ID
        defaultMAdventQuestStageShouldNotBeFound("extraFirstHalfNpcDeckId.lessThan=" + DEFAULT_EXTRA_FIRST_HALF_NPC_DECK_ID);

        // Get all the mAdventQuestStageList where extraFirstHalfNpcDeckId less than or equals to UPDATED_EXTRA_FIRST_HALF_NPC_DECK_ID
        defaultMAdventQuestStageShouldBeFound("extraFirstHalfNpcDeckId.lessThan=" + UPDATED_EXTRA_FIRST_HALF_NPC_DECK_ID);
    }


    @Test
    @Transactional
    public void getAllMAdventQuestStagesByExtraSecondHalfNpcDeckIdIsEqualToSomething() throws Exception {
        // Initialize the database
        mAdventQuestStageRepository.saveAndFlush(mAdventQuestStage);

        // Get all the mAdventQuestStageList where extraSecondHalfNpcDeckId equals to DEFAULT_EXTRA_SECOND_HALF_NPC_DECK_ID
        defaultMAdventQuestStageShouldBeFound("extraSecondHalfNpcDeckId.equals=" + DEFAULT_EXTRA_SECOND_HALF_NPC_DECK_ID);

        // Get all the mAdventQuestStageList where extraSecondHalfNpcDeckId equals to UPDATED_EXTRA_SECOND_HALF_NPC_DECK_ID
        defaultMAdventQuestStageShouldNotBeFound("extraSecondHalfNpcDeckId.equals=" + UPDATED_EXTRA_SECOND_HALF_NPC_DECK_ID);
    }

    @Test
    @Transactional
    public void getAllMAdventQuestStagesByExtraSecondHalfNpcDeckIdIsInShouldWork() throws Exception {
        // Initialize the database
        mAdventQuestStageRepository.saveAndFlush(mAdventQuestStage);

        // Get all the mAdventQuestStageList where extraSecondHalfNpcDeckId in DEFAULT_EXTRA_SECOND_HALF_NPC_DECK_ID or UPDATED_EXTRA_SECOND_HALF_NPC_DECK_ID
        defaultMAdventQuestStageShouldBeFound("extraSecondHalfNpcDeckId.in=" + DEFAULT_EXTRA_SECOND_HALF_NPC_DECK_ID + "," + UPDATED_EXTRA_SECOND_HALF_NPC_DECK_ID);

        // Get all the mAdventQuestStageList where extraSecondHalfNpcDeckId equals to UPDATED_EXTRA_SECOND_HALF_NPC_DECK_ID
        defaultMAdventQuestStageShouldNotBeFound("extraSecondHalfNpcDeckId.in=" + UPDATED_EXTRA_SECOND_HALF_NPC_DECK_ID);
    }

    @Test
    @Transactional
    public void getAllMAdventQuestStagesByExtraSecondHalfNpcDeckIdIsNullOrNotNull() throws Exception {
        // Initialize the database
        mAdventQuestStageRepository.saveAndFlush(mAdventQuestStage);

        // Get all the mAdventQuestStageList where extraSecondHalfNpcDeckId is not null
        defaultMAdventQuestStageShouldBeFound("extraSecondHalfNpcDeckId.specified=true");

        // Get all the mAdventQuestStageList where extraSecondHalfNpcDeckId is null
        defaultMAdventQuestStageShouldNotBeFound("extraSecondHalfNpcDeckId.specified=false");
    }

    @Test
    @Transactional
    public void getAllMAdventQuestStagesByExtraSecondHalfNpcDeckIdIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mAdventQuestStageRepository.saveAndFlush(mAdventQuestStage);

        // Get all the mAdventQuestStageList where extraSecondHalfNpcDeckId greater than or equals to DEFAULT_EXTRA_SECOND_HALF_NPC_DECK_ID
        defaultMAdventQuestStageShouldBeFound("extraSecondHalfNpcDeckId.greaterOrEqualThan=" + DEFAULT_EXTRA_SECOND_HALF_NPC_DECK_ID);

        // Get all the mAdventQuestStageList where extraSecondHalfNpcDeckId greater than or equals to UPDATED_EXTRA_SECOND_HALF_NPC_DECK_ID
        defaultMAdventQuestStageShouldNotBeFound("extraSecondHalfNpcDeckId.greaterOrEqualThan=" + UPDATED_EXTRA_SECOND_HALF_NPC_DECK_ID);
    }

    @Test
    @Transactional
    public void getAllMAdventQuestStagesByExtraSecondHalfNpcDeckIdIsLessThanSomething() throws Exception {
        // Initialize the database
        mAdventQuestStageRepository.saveAndFlush(mAdventQuestStage);

        // Get all the mAdventQuestStageList where extraSecondHalfNpcDeckId less than or equals to DEFAULT_EXTRA_SECOND_HALF_NPC_DECK_ID
        defaultMAdventQuestStageShouldNotBeFound("extraSecondHalfNpcDeckId.lessThan=" + DEFAULT_EXTRA_SECOND_HALF_NPC_DECK_ID);

        // Get all the mAdventQuestStageList where extraSecondHalfNpcDeckId less than or equals to UPDATED_EXTRA_SECOND_HALF_NPC_DECK_ID
        defaultMAdventQuestStageShouldBeFound("extraSecondHalfNpcDeckId.lessThan=" + UPDATED_EXTRA_SECOND_HALF_NPC_DECK_ID);
    }


    @Test
    @Transactional
    public void getAllMAdventQuestStagesByConsumeApIsEqualToSomething() throws Exception {
        // Initialize the database
        mAdventQuestStageRepository.saveAndFlush(mAdventQuestStage);

        // Get all the mAdventQuestStageList where consumeAp equals to DEFAULT_CONSUME_AP
        defaultMAdventQuestStageShouldBeFound("consumeAp.equals=" + DEFAULT_CONSUME_AP);

        // Get all the mAdventQuestStageList where consumeAp equals to UPDATED_CONSUME_AP
        defaultMAdventQuestStageShouldNotBeFound("consumeAp.equals=" + UPDATED_CONSUME_AP);
    }

    @Test
    @Transactional
    public void getAllMAdventQuestStagesByConsumeApIsInShouldWork() throws Exception {
        // Initialize the database
        mAdventQuestStageRepository.saveAndFlush(mAdventQuestStage);

        // Get all the mAdventQuestStageList where consumeAp in DEFAULT_CONSUME_AP or UPDATED_CONSUME_AP
        defaultMAdventQuestStageShouldBeFound("consumeAp.in=" + DEFAULT_CONSUME_AP + "," + UPDATED_CONSUME_AP);

        // Get all the mAdventQuestStageList where consumeAp equals to UPDATED_CONSUME_AP
        defaultMAdventQuestStageShouldNotBeFound("consumeAp.in=" + UPDATED_CONSUME_AP);
    }

    @Test
    @Transactional
    public void getAllMAdventQuestStagesByConsumeApIsNullOrNotNull() throws Exception {
        // Initialize the database
        mAdventQuestStageRepository.saveAndFlush(mAdventQuestStage);

        // Get all the mAdventQuestStageList where consumeAp is not null
        defaultMAdventQuestStageShouldBeFound("consumeAp.specified=true");

        // Get all the mAdventQuestStageList where consumeAp is null
        defaultMAdventQuestStageShouldNotBeFound("consumeAp.specified=false");
    }

    @Test
    @Transactional
    public void getAllMAdventQuestStagesByConsumeApIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mAdventQuestStageRepository.saveAndFlush(mAdventQuestStage);

        // Get all the mAdventQuestStageList where consumeAp greater than or equals to DEFAULT_CONSUME_AP
        defaultMAdventQuestStageShouldBeFound("consumeAp.greaterOrEqualThan=" + DEFAULT_CONSUME_AP);

        // Get all the mAdventQuestStageList where consumeAp greater than or equals to UPDATED_CONSUME_AP
        defaultMAdventQuestStageShouldNotBeFound("consumeAp.greaterOrEqualThan=" + UPDATED_CONSUME_AP);
    }

    @Test
    @Transactional
    public void getAllMAdventQuestStagesByConsumeApIsLessThanSomething() throws Exception {
        // Initialize the database
        mAdventQuestStageRepository.saveAndFlush(mAdventQuestStage);

        // Get all the mAdventQuestStageList where consumeAp less than or equals to DEFAULT_CONSUME_AP
        defaultMAdventQuestStageShouldNotBeFound("consumeAp.lessThan=" + DEFAULT_CONSUME_AP);

        // Get all the mAdventQuestStageList where consumeAp less than or equals to UPDATED_CONSUME_AP
        defaultMAdventQuestStageShouldBeFound("consumeAp.lessThan=" + UPDATED_CONSUME_AP);
    }


    @Test
    @Transactional
    public void getAllMAdventQuestStagesByKickOffTypeIsEqualToSomething() throws Exception {
        // Initialize the database
        mAdventQuestStageRepository.saveAndFlush(mAdventQuestStage);

        // Get all the mAdventQuestStageList where kickOffType equals to DEFAULT_KICK_OFF_TYPE
        defaultMAdventQuestStageShouldBeFound("kickOffType.equals=" + DEFAULT_KICK_OFF_TYPE);

        // Get all the mAdventQuestStageList where kickOffType equals to UPDATED_KICK_OFF_TYPE
        defaultMAdventQuestStageShouldNotBeFound("kickOffType.equals=" + UPDATED_KICK_OFF_TYPE);
    }

    @Test
    @Transactional
    public void getAllMAdventQuestStagesByKickOffTypeIsInShouldWork() throws Exception {
        // Initialize the database
        mAdventQuestStageRepository.saveAndFlush(mAdventQuestStage);

        // Get all the mAdventQuestStageList where kickOffType in DEFAULT_KICK_OFF_TYPE or UPDATED_KICK_OFF_TYPE
        defaultMAdventQuestStageShouldBeFound("kickOffType.in=" + DEFAULT_KICK_OFF_TYPE + "," + UPDATED_KICK_OFF_TYPE);

        // Get all the mAdventQuestStageList where kickOffType equals to UPDATED_KICK_OFF_TYPE
        defaultMAdventQuestStageShouldNotBeFound("kickOffType.in=" + UPDATED_KICK_OFF_TYPE);
    }

    @Test
    @Transactional
    public void getAllMAdventQuestStagesByKickOffTypeIsNullOrNotNull() throws Exception {
        // Initialize the database
        mAdventQuestStageRepository.saveAndFlush(mAdventQuestStage);

        // Get all the mAdventQuestStageList where kickOffType is not null
        defaultMAdventQuestStageShouldBeFound("kickOffType.specified=true");

        // Get all the mAdventQuestStageList where kickOffType is null
        defaultMAdventQuestStageShouldNotBeFound("kickOffType.specified=false");
    }

    @Test
    @Transactional
    public void getAllMAdventQuestStagesByKickOffTypeIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mAdventQuestStageRepository.saveAndFlush(mAdventQuestStage);

        // Get all the mAdventQuestStageList where kickOffType greater than or equals to DEFAULT_KICK_OFF_TYPE
        defaultMAdventQuestStageShouldBeFound("kickOffType.greaterOrEqualThan=" + DEFAULT_KICK_OFF_TYPE);

        // Get all the mAdventQuestStageList where kickOffType greater than or equals to UPDATED_KICK_OFF_TYPE
        defaultMAdventQuestStageShouldNotBeFound("kickOffType.greaterOrEqualThan=" + UPDATED_KICK_OFF_TYPE);
    }

    @Test
    @Transactional
    public void getAllMAdventQuestStagesByKickOffTypeIsLessThanSomething() throws Exception {
        // Initialize the database
        mAdventQuestStageRepository.saveAndFlush(mAdventQuestStage);

        // Get all the mAdventQuestStageList where kickOffType less than or equals to DEFAULT_KICK_OFF_TYPE
        defaultMAdventQuestStageShouldNotBeFound("kickOffType.lessThan=" + DEFAULT_KICK_OFF_TYPE);

        // Get all the mAdventQuestStageList where kickOffType less than or equals to UPDATED_KICK_OFF_TYPE
        defaultMAdventQuestStageShouldBeFound("kickOffType.lessThan=" + UPDATED_KICK_OFF_TYPE);
    }


    @Test
    @Transactional
    public void getAllMAdventQuestStagesByMatchMinuteIsEqualToSomething() throws Exception {
        // Initialize the database
        mAdventQuestStageRepository.saveAndFlush(mAdventQuestStage);

        // Get all the mAdventQuestStageList where matchMinute equals to DEFAULT_MATCH_MINUTE
        defaultMAdventQuestStageShouldBeFound("matchMinute.equals=" + DEFAULT_MATCH_MINUTE);

        // Get all the mAdventQuestStageList where matchMinute equals to UPDATED_MATCH_MINUTE
        defaultMAdventQuestStageShouldNotBeFound("matchMinute.equals=" + UPDATED_MATCH_MINUTE);
    }

    @Test
    @Transactional
    public void getAllMAdventQuestStagesByMatchMinuteIsInShouldWork() throws Exception {
        // Initialize the database
        mAdventQuestStageRepository.saveAndFlush(mAdventQuestStage);

        // Get all the mAdventQuestStageList where matchMinute in DEFAULT_MATCH_MINUTE or UPDATED_MATCH_MINUTE
        defaultMAdventQuestStageShouldBeFound("matchMinute.in=" + DEFAULT_MATCH_MINUTE + "," + UPDATED_MATCH_MINUTE);

        // Get all the mAdventQuestStageList where matchMinute equals to UPDATED_MATCH_MINUTE
        defaultMAdventQuestStageShouldNotBeFound("matchMinute.in=" + UPDATED_MATCH_MINUTE);
    }

    @Test
    @Transactional
    public void getAllMAdventQuestStagesByMatchMinuteIsNullOrNotNull() throws Exception {
        // Initialize the database
        mAdventQuestStageRepository.saveAndFlush(mAdventQuestStage);

        // Get all the mAdventQuestStageList where matchMinute is not null
        defaultMAdventQuestStageShouldBeFound("matchMinute.specified=true");

        // Get all the mAdventQuestStageList where matchMinute is null
        defaultMAdventQuestStageShouldNotBeFound("matchMinute.specified=false");
    }

    @Test
    @Transactional
    public void getAllMAdventQuestStagesByMatchMinuteIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mAdventQuestStageRepository.saveAndFlush(mAdventQuestStage);

        // Get all the mAdventQuestStageList where matchMinute greater than or equals to DEFAULT_MATCH_MINUTE
        defaultMAdventQuestStageShouldBeFound("matchMinute.greaterOrEqualThan=" + DEFAULT_MATCH_MINUTE);

        // Get all the mAdventQuestStageList where matchMinute greater than or equals to UPDATED_MATCH_MINUTE
        defaultMAdventQuestStageShouldNotBeFound("matchMinute.greaterOrEqualThan=" + UPDATED_MATCH_MINUTE);
    }

    @Test
    @Transactional
    public void getAllMAdventQuestStagesByMatchMinuteIsLessThanSomething() throws Exception {
        // Initialize the database
        mAdventQuestStageRepository.saveAndFlush(mAdventQuestStage);

        // Get all the mAdventQuestStageList where matchMinute less than or equals to DEFAULT_MATCH_MINUTE
        defaultMAdventQuestStageShouldNotBeFound("matchMinute.lessThan=" + DEFAULT_MATCH_MINUTE);

        // Get all the mAdventQuestStageList where matchMinute less than or equals to UPDATED_MATCH_MINUTE
        defaultMAdventQuestStageShouldBeFound("matchMinute.lessThan=" + UPDATED_MATCH_MINUTE);
    }


    @Test
    @Transactional
    public void getAllMAdventQuestStagesByEnableExtraIsEqualToSomething() throws Exception {
        // Initialize the database
        mAdventQuestStageRepository.saveAndFlush(mAdventQuestStage);

        // Get all the mAdventQuestStageList where enableExtra equals to DEFAULT_ENABLE_EXTRA
        defaultMAdventQuestStageShouldBeFound("enableExtra.equals=" + DEFAULT_ENABLE_EXTRA);

        // Get all the mAdventQuestStageList where enableExtra equals to UPDATED_ENABLE_EXTRA
        defaultMAdventQuestStageShouldNotBeFound("enableExtra.equals=" + UPDATED_ENABLE_EXTRA);
    }

    @Test
    @Transactional
    public void getAllMAdventQuestStagesByEnableExtraIsInShouldWork() throws Exception {
        // Initialize the database
        mAdventQuestStageRepository.saveAndFlush(mAdventQuestStage);

        // Get all the mAdventQuestStageList where enableExtra in DEFAULT_ENABLE_EXTRA or UPDATED_ENABLE_EXTRA
        defaultMAdventQuestStageShouldBeFound("enableExtra.in=" + DEFAULT_ENABLE_EXTRA + "," + UPDATED_ENABLE_EXTRA);

        // Get all the mAdventQuestStageList where enableExtra equals to UPDATED_ENABLE_EXTRA
        defaultMAdventQuestStageShouldNotBeFound("enableExtra.in=" + UPDATED_ENABLE_EXTRA);
    }

    @Test
    @Transactional
    public void getAllMAdventQuestStagesByEnableExtraIsNullOrNotNull() throws Exception {
        // Initialize the database
        mAdventQuestStageRepository.saveAndFlush(mAdventQuestStage);

        // Get all the mAdventQuestStageList where enableExtra is not null
        defaultMAdventQuestStageShouldBeFound("enableExtra.specified=true");

        // Get all the mAdventQuestStageList where enableExtra is null
        defaultMAdventQuestStageShouldNotBeFound("enableExtra.specified=false");
    }

    @Test
    @Transactional
    public void getAllMAdventQuestStagesByEnableExtraIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mAdventQuestStageRepository.saveAndFlush(mAdventQuestStage);

        // Get all the mAdventQuestStageList where enableExtra greater than or equals to DEFAULT_ENABLE_EXTRA
        defaultMAdventQuestStageShouldBeFound("enableExtra.greaterOrEqualThan=" + DEFAULT_ENABLE_EXTRA);

        // Get all the mAdventQuestStageList where enableExtra greater than or equals to UPDATED_ENABLE_EXTRA
        defaultMAdventQuestStageShouldNotBeFound("enableExtra.greaterOrEqualThan=" + UPDATED_ENABLE_EXTRA);
    }

    @Test
    @Transactional
    public void getAllMAdventQuestStagesByEnableExtraIsLessThanSomething() throws Exception {
        // Initialize the database
        mAdventQuestStageRepository.saveAndFlush(mAdventQuestStage);

        // Get all the mAdventQuestStageList where enableExtra less than or equals to DEFAULT_ENABLE_EXTRA
        defaultMAdventQuestStageShouldNotBeFound("enableExtra.lessThan=" + DEFAULT_ENABLE_EXTRA);

        // Get all the mAdventQuestStageList where enableExtra less than or equals to UPDATED_ENABLE_EXTRA
        defaultMAdventQuestStageShouldBeFound("enableExtra.lessThan=" + UPDATED_ENABLE_EXTRA);
    }


    @Test
    @Transactional
    public void getAllMAdventQuestStagesByEnablePkIsEqualToSomething() throws Exception {
        // Initialize the database
        mAdventQuestStageRepository.saveAndFlush(mAdventQuestStage);

        // Get all the mAdventQuestStageList where enablePk equals to DEFAULT_ENABLE_PK
        defaultMAdventQuestStageShouldBeFound("enablePk.equals=" + DEFAULT_ENABLE_PK);

        // Get all the mAdventQuestStageList where enablePk equals to UPDATED_ENABLE_PK
        defaultMAdventQuestStageShouldNotBeFound("enablePk.equals=" + UPDATED_ENABLE_PK);
    }

    @Test
    @Transactional
    public void getAllMAdventQuestStagesByEnablePkIsInShouldWork() throws Exception {
        // Initialize the database
        mAdventQuestStageRepository.saveAndFlush(mAdventQuestStage);

        // Get all the mAdventQuestStageList where enablePk in DEFAULT_ENABLE_PK or UPDATED_ENABLE_PK
        defaultMAdventQuestStageShouldBeFound("enablePk.in=" + DEFAULT_ENABLE_PK + "," + UPDATED_ENABLE_PK);

        // Get all the mAdventQuestStageList where enablePk equals to UPDATED_ENABLE_PK
        defaultMAdventQuestStageShouldNotBeFound("enablePk.in=" + UPDATED_ENABLE_PK);
    }

    @Test
    @Transactional
    public void getAllMAdventQuestStagesByEnablePkIsNullOrNotNull() throws Exception {
        // Initialize the database
        mAdventQuestStageRepository.saveAndFlush(mAdventQuestStage);

        // Get all the mAdventQuestStageList where enablePk is not null
        defaultMAdventQuestStageShouldBeFound("enablePk.specified=true");

        // Get all the mAdventQuestStageList where enablePk is null
        defaultMAdventQuestStageShouldNotBeFound("enablePk.specified=false");
    }

    @Test
    @Transactional
    public void getAllMAdventQuestStagesByEnablePkIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mAdventQuestStageRepository.saveAndFlush(mAdventQuestStage);

        // Get all the mAdventQuestStageList where enablePk greater than or equals to DEFAULT_ENABLE_PK
        defaultMAdventQuestStageShouldBeFound("enablePk.greaterOrEqualThan=" + DEFAULT_ENABLE_PK);

        // Get all the mAdventQuestStageList where enablePk greater than or equals to UPDATED_ENABLE_PK
        defaultMAdventQuestStageShouldNotBeFound("enablePk.greaterOrEqualThan=" + UPDATED_ENABLE_PK);
    }

    @Test
    @Transactional
    public void getAllMAdventQuestStagesByEnablePkIsLessThanSomething() throws Exception {
        // Initialize the database
        mAdventQuestStageRepository.saveAndFlush(mAdventQuestStage);

        // Get all the mAdventQuestStageList where enablePk less than or equals to DEFAULT_ENABLE_PK
        defaultMAdventQuestStageShouldNotBeFound("enablePk.lessThan=" + DEFAULT_ENABLE_PK);

        // Get all the mAdventQuestStageList where enablePk less than or equals to UPDATED_ENABLE_PK
        defaultMAdventQuestStageShouldBeFound("enablePk.lessThan=" + UPDATED_ENABLE_PK);
    }


    @Test
    @Transactional
    public void getAllMAdventQuestStagesByAiLevelIsEqualToSomething() throws Exception {
        // Initialize the database
        mAdventQuestStageRepository.saveAndFlush(mAdventQuestStage);

        // Get all the mAdventQuestStageList where aiLevel equals to DEFAULT_AI_LEVEL
        defaultMAdventQuestStageShouldBeFound("aiLevel.equals=" + DEFAULT_AI_LEVEL);

        // Get all the mAdventQuestStageList where aiLevel equals to UPDATED_AI_LEVEL
        defaultMAdventQuestStageShouldNotBeFound("aiLevel.equals=" + UPDATED_AI_LEVEL);
    }

    @Test
    @Transactional
    public void getAllMAdventQuestStagesByAiLevelIsInShouldWork() throws Exception {
        // Initialize the database
        mAdventQuestStageRepository.saveAndFlush(mAdventQuestStage);

        // Get all the mAdventQuestStageList where aiLevel in DEFAULT_AI_LEVEL or UPDATED_AI_LEVEL
        defaultMAdventQuestStageShouldBeFound("aiLevel.in=" + DEFAULT_AI_LEVEL + "," + UPDATED_AI_LEVEL);

        // Get all the mAdventQuestStageList where aiLevel equals to UPDATED_AI_LEVEL
        defaultMAdventQuestStageShouldNotBeFound("aiLevel.in=" + UPDATED_AI_LEVEL);
    }

    @Test
    @Transactional
    public void getAllMAdventQuestStagesByAiLevelIsNullOrNotNull() throws Exception {
        // Initialize the database
        mAdventQuestStageRepository.saveAndFlush(mAdventQuestStage);

        // Get all the mAdventQuestStageList where aiLevel is not null
        defaultMAdventQuestStageShouldBeFound("aiLevel.specified=true");

        // Get all the mAdventQuestStageList where aiLevel is null
        defaultMAdventQuestStageShouldNotBeFound("aiLevel.specified=false");
    }

    @Test
    @Transactional
    public void getAllMAdventQuestStagesByAiLevelIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mAdventQuestStageRepository.saveAndFlush(mAdventQuestStage);

        // Get all the mAdventQuestStageList where aiLevel greater than or equals to DEFAULT_AI_LEVEL
        defaultMAdventQuestStageShouldBeFound("aiLevel.greaterOrEqualThan=" + DEFAULT_AI_LEVEL);

        // Get all the mAdventQuestStageList where aiLevel greater than or equals to UPDATED_AI_LEVEL
        defaultMAdventQuestStageShouldNotBeFound("aiLevel.greaterOrEqualThan=" + UPDATED_AI_LEVEL);
    }

    @Test
    @Transactional
    public void getAllMAdventQuestStagesByAiLevelIsLessThanSomething() throws Exception {
        // Initialize the database
        mAdventQuestStageRepository.saveAndFlush(mAdventQuestStage);

        // Get all the mAdventQuestStageList where aiLevel less than or equals to DEFAULT_AI_LEVEL
        defaultMAdventQuestStageShouldNotBeFound("aiLevel.lessThan=" + DEFAULT_AI_LEVEL);

        // Get all the mAdventQuestStageList where aiLevel less than or equals to UPDATED_AI_LEVEL
        defaultMAdventQuestStageShouldBeFound("aiLevel.lessThan=" + UPDATED_AI_LEVEL);
    }


    @Test
    @Transactional
    public void getAllMAdventQuestStagesByStartAtSecondHalfIsEqualToSomething() throws Exception {
        // Initialize the database
        mAdventQuestStageRepository.saveAndFlush(mAdventQuestStage);

        // Get all the mAdventQuestStageList where startAtSecondHalf equals to DEFAULT_START_AT_SECOND_HALF
        defaultMAdventQuestStageShouldBeFound("startAtSecondHalf.equals=" + DEFAULT_START_AT_SECOND_HALF);

        // Get all the mAdventQuestStageList where startAtSecondHalf equals to UPDATED_START_AT_SECOND_HALF
        defaultMAdventQuestStageShouldNotBeFound("startAtSecondHalf.equals=" + UPDATED_START_AT_SECOND_HALF);
    }

    @Test
    @Transactional
    public void getAllMAdventQuestStagesByStartAtSecondHalfIsInShouldWork() throws Exception {
        // Initialize the database
        mAdventQuestStageRepository.saveAndFlush(mAdventQuestStage);

        // Get all the mAdventQuestStageList where startAtSecondHalf in DEFAULT_START_AT_SECOND_HALF or UPDATED_START_AT_SECOND_HALF
        defaultMAdventQuestStageShouldBeFound("startAtSecondHalf.in=" + DEFAULT_START_AT_SECOND_HALF + "," + UPDATED_START_AT_SECOND_HALF);

        // Get all the mAdventQuestStageList where startAtSecondHalf equals to UPDATED_START_AT_SECOND_HALF
        defaultMAdventQuestStageShouldNotBeFound("startAtSecondHalf.in=" + UPDATED_START_AT_SECOND_HALF);
    }

    @Test
    @Transactional
    public void getAllMAdventQuestStagesByStartAtSecondHalfIsNullOrNotNull() throws Exception {
        // Initialize the database
        mAdventQuestStageRepository.saveAndFlush(mAdventQuestStage);

        // Get all the mAdventQuestStageList where startAtSecondHalf is not null
        defaultMAdventQuestStageShouldBeFound("startAtSecondHalf.specified=true");

        // Get all the mAdventQuestStageList where startAtSecondHalf is null
        defaultMAdventQuestStageShouldNotBeFound("startAtSecondHalf.specified=false");
    }

    @Test
    @Transactional
    public void getAllMAdventQuestStagesByStartAtSecondHalfIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mAdventQuestStageRepository.saveAndFlush(mAdventQuestStage);

        // Get all the mAdventQuestStageList where startAtSecondHalf greater than or equals to DEFAULT_START_AT_SECOND_HALF
        defaultMAdventQuestStageShouldBeFound("startAtSecondHalf.greaterOrEqualThan=" + DEFAULT_START_AT_SECOND_HALF);

        // Get all the mAdventQuestStageList where startAtSecondHalf greater than or equals to UPDATED_START_AT_SECOND_HALF
        defaultMAdventQuestStageShouldNotBeFound("startAtSecondHalf.greaterOrEqualThan=" + UPDATED_START_AT_SECOND_HALF);
    }

    @Test
    @Transactional
    public void getAllMAdventQuestStagesByStartAtSecondHalfIsLessThanSomething() throws Exception {
        // Initialize the database
        mAdventQuestStageRepository.saveAndFlush(mAdventQuestStage);

        // Get all the mAdventQuestStageList where startAtSecondHalf less than or equals to DEFAULT_START_AT_SECOND_HALF
        defaultMAdventQuestStageShouldNotBeFound("startAtSecondHalf.lessThan=" + DEFAULT_START_AT_SECOND_HALF);

        // Get all the mAdventQuestStageList where startAtSecondHalf less than or equals to UPDATED_START_AT_SECOND_HALF
        defaultMAdventQuestStageShouldBeFound("startAtSecondHalf.lessThan=" + UPDATED_START_AT_SECOND_HALF);
    }


    @Test
    @Transactional
    public void getAllMAdventQuestStagesByStartScoreIsEqualToSomething() throws Exception {
        // Initialize the database
        mAdventQuestStageRepository.saveAndFlush(mAdventQuestStage);

        // Get all the mAdventQuestStageList where startScore equals to DEFAULT_START_SCORE
        defaultMAdventQuestStageShouldBeFound("startScore.equals=" + DEFAULT_START_SCORE);

        // Get all the mAdventQuestStageList where startScore equals to UPDATED_START_SCORE
        defaultMAdventQuestStageShouldNotBeFound("startScore.equals=" + UPDATED_START_SCORE);
    }

    @Test
    @Transactional
    public void getAllMAdventQuestStagesByStartScoreIsInShouldWork() throws Exception {
        // Initialize the database
        mAdventQuestStageRepository.saveAndFlush(mAdventQuestStage);

        // Get all the mAdventQuestStageList where startScore in DEFAULT_START_SCORE or UPDATED_START_SCORE
        defaultMAdventQuestStageShouldBeFound("startScore.in=" + DEFAULT_START_SCORE + "," + UPDATED_START_SCORE);

        // Get all the mAdventQuestStageList where startScore equals to UPDATED_START_SCORE
        defaultMAdventQuestStageShouldNotBeFound("startScore.in=" + UPDATED_START_SCORE);
    }

    @Test
    @Transactional
    public void getAllMAdventQuestStagesByStartScoreIsNullOrNotNull() throws Exception {
        // Initialize the database
        mAdventQuestStageRepository.saveAndFlush(mAdventQuestStage);

        // Get all the mAdventQuestStageList where startScore is not null
        defaultMAdventQuestStageShouldBeFound("startScore.specified=true");

        // Get all the mAdventQuestStageList where startScore is null
        defaultMAdventQuestStageShouldNotBeFound("startScore.specified=false");
    }

    @Test
    @Transactional
    public void getAllMAdventQuestStagesByStartScoreIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mAdventQuestStageRepository.saveAndFlush(mAdventQuestStage);

        // Get all the mAdventQuestStageList where startScore greater than or equals to DEFAULT_START_SCORE
        defaultMAdventQuestStageShouldBeFound("startScore.greaterOrEqualThan=" + DEFAULT_START_SCORE);

        // Get all the mAdventQuestStageList where startScore greater than or equals to UPDATED_START_SCORE
        defaultMAdventQuestStageShouldNotBeFound("startScore.greaterOrEqualThan=" + UPDATED_START_SCORE);
    }

    @Test
    @Transactional
    public void getAllMAdventQuestStagesByStartScoreIsLessThanSomething() throws Exception {
        // Initialize the database
        mAdventQuestStageRepository.saveAndFlush(mAdventQuestStage);

        // Get all the mAdventQuestStageList where startScore less than or equals to DEFAULT_START_SCORE
        defaultMAdventQuestStageShouldNotBeFound("startScore.lessThan=" + DEFAULT_START_SCORE);

        // Get all the mAdventQuestStageList where startScore less than or equals to UPDATED_START_SCORE
        defaultMAdventQuestStageShouldBeFound("startScore.lessThan=" + UPDATED_START_SCORE);
    }


    @Test
    @Transactional
    public void getAllMAdventQuestStagesByStartScoreOpponentIsEqualToSomething() throws Exception {
        // Initialize the database
        mAdventQuestStageRepository.saveAndFlush(mAdventQuestStage);

        // Get all the mAdventQuestStageList where startScoreOpponent equals to DEFAULT_START_SCORE_OPPONENT
        defaultMAdventQuestStageShouldBeFound("startScoreOpponent.equals=" + DEFAULT_START_SCORE_OPPONENT);

        // Get all the mAdventQuestStageList where startScoreOpponent equals to UPDATED_START_SCORE_OPPONENT
        defaultMAdventQuestStageShouldNotBeFound("startScoreOpponent.equals=" + UPDATED_START_SCORE_OPPONENT);
    }

    @Test
    @Transactional
    public void getAllMAdventQuestStagesByStartScoreOpponentIsInShouldWork() throws Exception {
        // Initialize the database
        mAdventQuestStageRepository.saveAndFlush(mAdventQuestStage);

        // Get all the mAdventQuestStageList where startScoreOpponent in DEFAULT_START_SCORE_OPPONENT or UPDATED_START_SCORE_OPPONENT
        defaultMAdventQuestStageShouldBeFound("startScoreOpponent.in=" + DEFAULT_START_SCORE_OPPONENT + "," + UPDATED_START_SCORE_OPPONENT);

        // Get all the mAdventQuestStageList where startScoreOpponent equals to UPDATED_START_SCORE_OPPONENT
        defaultMAdventQuestStageShouldNotBeFound("startScoreOpponent.in=" + UPDATED_START_SCORE_OPPONENT);
    }

    @Test
    @Transactional
    public void getAllMAdventQuestStagesByStartScoreOpponentIsNullOrNotNull() throws Exception {
        // Initialize the database
        mAdventQuestStageRepository.saveAndFlush(mAdventQuestStage);

        // Get all the mAdventQuestStageList where startScoreOpponent is not null
        defaultMAdventQuestStageShouldBeFound("startScoreOpponent.specified=true");

        // Get all the mAdventQuestStageList where startScoreOpponent is null
        defaultMAdventQuestStageShouldNotBeFound("startScoreOpponent.specified=false");
    }

    @Test
    @Transactional
    public void getAllMAdventQuestStagesByStartScoreOpponentIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mAdventQuestStageRepository.saveAndFlush(mAdventQuestStage);

        // Get all the mAdventQuestStageList where startScoreOpponent greater than or equals to DEFAULT_START_SCORE_OPPONENT
        defaultMAdventQuestStageShouldBeFound("startScoreOpponent.greaterOrEqualThan=" + DEFAULT_START_SCORE_OPPONENT);

        // Get all the mAdventQuestStageList where startScoreOpponent greater than or equals to UPDATED_START_SCORE_OPPONENT
        defaultMAdventQuestStageShouldNotBeFound("startScoreOpponent.greaterOrEqualThan=" + UPDATED_START_SCORE_OPPONENT);
    }

    @Test
    @Transactional
    public void getAllMAdventQuestStagesByStartScoreOpponentIsLessThanSomething() throws Exception {
        // Initialize the database
        mAdventQuestStageRepository.saveAndFlush(mAdventQuestStage);

        // Get all the mAdventQuestStageList where startScoreOpponent less than or equals to DEFAULT_START_SCORE_OPPONENT
        defaultMAdventQuestStageShouldNotBeFound("startScoreOpponent.lessThan=" + DEFAULT_START_SCORE_OPPONENT);

        // Get all the mAdventQuestStageList where startScoreOpponent less than or equals to UPDATED_START_SCORE_OPPONENT
        defaultMAdventQuestStageShouldBeFound("startScoreOpponent.lessThan=" + UPDATED_START_SCORE_OPPONENT);
    }


    @Test
    @Transactional
    public void getAllMAdventQuestStagesByConditionIdIsEqualToSomething() throws Exception {
        // Initialize the database
        mAdventQuestStageRepository.saveAndFlush(mAdventQuestStage);

        // Get all the mAdventQuestStageList where conditionId equals to DEFAULT_CONDITION_ID
        defaultMAdventQuestStageShouldBeFound("conditionId.equals=" + DEFAULT_CONDITION_ID);

        // Get all the mAdventQuestStageList where conditionId equals to UPDATED_CONDITION_ID
        defaultMAdventQuestStageShouldNotBeFound("conditionId.equals=" + UPDATED_CONDITION_ID);
    }

    @Test
    @Transactional
    public void getAllMAdventQuestStagesByConditionIdIsInShouldWork() throws Exception {
        // Initialize the database
        mAdventQuestStageRepository.saveAndFlush(mAdventQuestStage);

        // Get all the mAdventQuestStageList where conditionId in DEFAULT_CONDITION_ID or UPDATED_CONDITION_ID
        defaultMAdventQuestStageShouldBeFound("conditionId.in=" + DEFAULT_CONDITION_ID + "," + UPDATED_CONDITION_ID);

        // Get all the mAdventQuestStageList where conditionId equals to UPDATED_CONDITION_ID
        defaultMAdventQuestStageShouldNotBeFound("conditionId.in=" + UPDATED_CONDITION_ID);
    }

    @Test
    @Transactional
    public void getAllMAdventQuestStagesByConditionIdIsNullOrNotNull() throws Exception {
        // Initialize the database
        mAdventQuestStageRepository.saveAndFlush(mAdventQuestStage);

        // Get all the mAdventQuestStageList where conditionId is not null
        defaultMAdventQuestStageShouldBeFound("conditionId.specified=true");

        // Get all the mAdventQuestStageList where conditionId is null
        defaultMAdventQuestStageShouldNotBeFound("conditionId.specified=false");
    }

    @Test
    @Transactional
    public void getAllMAdventQuestStagesByConditionIdIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mAdventQuestStageRepository.saveAndFlush(mAdventQuestStage);

        // Get all the mAdventQuestStageList where conditionId greater than or equals to DEFAULT_CONDITION_ID
        defaultMAdventQuestStageShouldBeFound("conditionId.greaterOrEqualThan=" + DEFAULT_CONDITION_ID);

        // Get all the mAdventQuestStageList where conditionId greater than or equals to UPDATED_CONDITION_ID
        defaultMAdventQuestStageShouldNotBeFound("conditionId.greaterOrEqualThan=" + UPDATED_CONDITION_ID);
    }

    @Test
    @Transactional
    public void getAllMAdventQuestStagesByConditionIdIsLessThanSomething() throws Exception {
        // Initialize the database
        mAdventQuestStageRepository.saveAndFlush(mAdventQuestStage);

        // Get all the mAdventQuestStageList where conditionId less than or equals to DEFAULT_CONDITION_ID
        defaultMAdventQuestStageShouldNotBeFound("conditionId.lessThan=" + DEFAULT_CONDITION_ID);

        // Get all the mAdventQuestStageList where conditionId less than or equals to UPDATED_CONDITION_ID
        defaultMAdventQuestStageShouldBeFound("conditionId.lessThan=" + UPDATED_CONDITION_ID);
    }


    @Test
    @Transactional
    public void getAllMAdventQuestStagesByOptionIdIsEqualToSomething() throws Exception {
        // Initialize the database
        mAdventQuestStageRepository.saveAndFlush(mAdventQuestStage);

        // Get all the mAdventQuestStageList where optionId equals to DEFAULT_OPTION_ID
        defaultMAdventQuestStageShouldBeFound("optionId.equals=" + DEFAULT_OPTION_ID);

        // Get all the mAdventQuestStageList where optionId equals to UPDATED_OPTION_ID
        defaultMAdventQuestStageShouldNotBeFound("optionId.equals=" + UPDATED_OPTION_ID);
    }

    @Test
    @Transactional
    public void getAllMAdventQuestStagesByOptionIdIsInShouldWork() throws Exception {
        // Initialize the database
        mAdventQuestStageRepository.saveAndFlush(mAdventQuestStage);

        // Get all the mAdventQuestStageList where optionId in DEFAULT_OPTION_ID or UPDATED_OPTION_ID
        defaultMAdventQuestStageShouldBeFound("optionId.in=" + DEFAULT_OPTION_ID + "," + UPDATED_OPTION_ID);

        // Get all the mAdventQuestStageList where optionId equals to UPDATED_OPTION_ID
        defaultMAdventQuestStageShouldNotBeFound("optionId.in=" + UPDATED_OPTION_ID);
    }

    @Test
    @Transactional
    public void getAllMAdventQuestStagesByOptionIdIsNullOrNotNull() throws Exception {
        // Initialize the database
        mAdventQuestStageRepository.saveAndFlush(mAdventQuestStage);

        // Get all the mAdventQuestStageList where optionId is not null
        defaultMAdventQuestStageShouldBeFound("optionId.specified=true");

        // Get all the mAdventQuestStageList where optionId is null
        defaultMAdventQuestStageShouldNotBeFound("optionId.specified=false");
    }

    @Test
    @Transactional
    public void getAllMAdventQuestStagesByOptionIdIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mAdventQuestStageRepository.saveAndFlush(mAdventQuestStage);

        // Get all the mAdventQuestStageList where optionId greater than or equals to DEFAULT_OPTION_ID
        defaultMAdventQuestStageShouldBeFound("optionId.greaterOrEqualThan=" + DEFAULT_OPTION_ID);

        // Get all the mAdventQuestStageList where optionId greater than or equals to UPDATED_OPTION_ID
        defaultMAdventQuestStageShouldNotBeFound("optionId.greaterOrEqualThan=" + UPDATED_OPTION_ID);
    }

    @Test
    @Transactional
    public void getAllMAdventQuestStagesByOptionIdIsLessThanSomething() throws Exception {
        // Initialize the database
        mAdventQuestStageRepository.saveAndFlush(mAdventQuestStage);

        // Get all the mAdventQuestStageList where optionId less than or equals to DEFAULT_OPTION_ID
        defaultMAdventQuestStageShouldNotBeFound("optionId.lessThan=" + DEFAULT_OPTION_ID);

        // Get all the mAdventQuestStageList where optionId less than or equals to UPDATED_OPTION_ID
        defaultMAdventQuestStageShouldBeFound("optionId.lessThan=" + UPDATED_OPTION_ID);
    }


    @Test
    @Transactional
    public void getAllMAdventQuestStagesByDeckConditionIdIsEqualToSomething() throws Exception {
        // Initialize the database
        mAdventQuestStageRepository.saveAndFlush(mAdventQuestStage);

        // Get all the mAdventQuestStageList where deckConditionId equals to DEFAULT_DECK_CONDITION_ID
        defaultMAdventQuestStageShouldBeFound("deckConditionId.equals=" + DEFAULT_DECK_CONDITION_ID);

        // Get all the mAdventQuestStageList where deckConditionId equals to UPDATED_DECK_CONDITION_ID
        defaultMAdventQuestStageShouldNotBeFound("deckConditionId.equals=" + UPDATED_DECK_CONDITION_ID);
    }

    @Test
    @Transactional
    public void getAllMAdventQuestStagesByDeckConditionIdIsInShouldWork() throws Exception {
        // Initialize the database
        mAdventQuestStageRepository.saveAndFlush(mAdventQuestStage);

        // Get all the mAdventQuestStageList where deckConditionId in DEFAULT_DECK_CONDITION_ID or UPDATED_DECK_CONDITION_ID
        defaultMAdventQuestStageShouldBeFound("deckConditionId.in=" + DEFAULT_DECK_CONDITION_ID + "," + UPDATED_DECK_CONDITION_ID);

        // Get all the mAdventQuestStageList where deckConditionId equals to UPDATED_DECK_CONDITION_ID
        defaultMAdventQuestStageShouldNotBeFound("deckConditionId.in=" + UPDATED_DECK_CONDITION_ID);
    }

    @Test
    @Transactional
    public void getAllMAdventQuestStagesByDeckConditionIdIsNullOrNotNull() throws Exception {
        // Initialize the database
        mAdventQuestStageRepository.saveAndFlush(mAdventQuestStage);

        // Get all the mAdventQuestStageList where deckConditionId is not null
        defaultMAdventQuestStageShouldBeFound("deckConditionId.specified=true");

        // Get all the mAdventQuestStageList where deckConditionId is null
        defaultMAdventQuestStageShouldNotBeFound("deckConditionId.specified=false");
    }

    @Test
    @Transactional
    public void getAllMAdventQuestStagesByDeckConditionIdIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mAdventQuestStageRepository.saveAndFlush(mAdventQuestStage);

        // Get all the mAdventQuestStageList where deckConditionId greater than or equals to DEFAULT_DECK_CONDITION_ID
        defaultMAdventQuestStageShouldBeFound("deckConditionId.greaterOrEqualThan=" + DEFAULT_DECK_CONDITION_ID);

        // Get all the mAdventQuestStageList where deckConditionId greater than or equals to UPDATED_DECK_CONDITION_ID
        defaultMAdventQuestStageShouldNotBeFound("deckConditionId.greaterOrEqualThan=" + UPDATED_DECK_CONDITION_ID);
    }

    @Test
    @Transactional
    public void getAllMAdventQuestStagesByDeckConditionIdIsLessThanSomething() throws Exception {
        // Initialize the database
        mAdventQuestStageRepository.saveAndFlush(mAdventQuestStage);

        // Get all the mAdventQuestStageList where deckConditionId less than or equals to DEFAULT_DECK_CONDITION_ID
        defaultMAdventQuestStageShouldNotBeFound("deckConditionId.lessThan=" + DEFAULT_DECK_CONDITION_ID);

        // Get all the mAdventQuestStageList where deckConditionId less than or equals to UPDATED_DECK_CONDITION_ID
        defaultMAdventQuestStageShouldBeFound("deckConditionId.lessThan=" + UPDATED_DECK_CONDITION_ID);
    }


    @Test
    @Transactional
    public void getAllMAdventQuestStagesByIdIsEqualToSomething() throws Exception {
        // Get already existing entity
        MAdventQuestWorld id = mAdventQuestStage.getId();
        mAdventQuestStageRepository.saveAndFlush(mAdventQuestStage);
        Long idId = id.getId();

        // Get all the mAdventQuestStageList where id equals to idId
        defaultMAdventQuestStageShouldBeFound("idId.equals=" + idId);

        // Get all the mAdventQuestStageList where id equals to idId + 1
        defaultMAdventQuestStageShouldNotBeFound("idId.equals=" + (idId + 1));
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultMAdventQuestStageShouldBeFound(String filter) throws Exception {
        restMAdventQuestStageMockMvc.perform(get("/api/m-advent-quest-stages?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(mAdventQuestStage.getId().intValue())))
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
            .andExpect(jsonPath("$.[*].deckConditionId").value(hasItem(DEFAULT_DECK_CONDITION_ID)));

        // Check, that the count call also returns 1
        restMAdventQuestStageMockMvc.perform(get("/api/m-advent-quest-stages/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultMAdventQuestStageShouldNotBeFound(String filter) throws Exception {
        restMAdventQuestStageMockMvc.perform(get("/api/m-advent-quest-stages?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restMAdventQuestStageMockMvc.perform(get("/api/m-advent-quest-stages/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("0"));
    }


    @Test
    @Transactional
    public void getNonExistingMAdventQuestStage() throws Exception {
        // Get the mAdventQuestStage
        restMAdventQuestStageMockMvc.perform(get("/api/m-advent-quest-stages/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateMAdventQuestStage() throws Exception {
        // Initialize the database
        mAdventQuestStageRepository.saveAndFlush(mAdventQuestStage);

        int databaseSizeBeforeUpdate = mAdventQuestStageRepository.findAll().size();

        // Update the mAdventQuestStage
        MAdventQuestStage updatedMAdventQuestStage = mAdventQuestStageRepository.findById(mAdventQuestStage.getId()).get();
        // Disconnect from session so that the updates on updatedMAdventQuestStage are not directly saved in db
        em.detach(updatedMAdventQuestStage);
        updatedMAdventQuestStage
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
            .deckConditionId(UPDATED_DECK_CONDITION_ID);
        MAdventQuestStageDTO mAdventQuestStageDTO = mAdventQuestStageMapper.toDto(updatedMAdventQuestStage);

        restMAdventQuestStageMockMvc.perform(put("/api/m-advent-quest-stages")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mAdventQuestStageDTO)))
            .andExpect(status().isOk());

        // Validate the MAdventQuestStage in the database
        List<MAdventQuestStage> mAdventQuestStageList = mAdventQuestStageRepository.findAll();
        assertThat(mAdventQuestStageList).hasSize(databaseSizeBeforeUpdate);
        MAdventQuestStage testMAdventQuestStage = mAdventQuestStageList.get(mAdventQuestStageList.size() - 1);
        assertThat(testMAdventQuestStage.getWorldId()).isEqualTo(UPDATED_WORLD_ID);
        assertThat(testMAdventQuestStage.getNumber()).isEqualTo(UPDATED_NUMBER);
        assertThat(testMAdventQuestStage.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testMAdventQuestStage.getImagePath()).isEqualTo(UPDATED_IMAGE_PATH);
        assertThat(testMAdventQuestStage.getCharacterThumbnailPath()).isEqualTo(UPDATED_CHARACTER_THUMBNAIL_PATH);
        assertThat(testMAdventQuestStage.getDifficulty()).isEqualTo(UPDATED_DIFFICULTY);
        assertThat(testMAdventQuestStage.getStageUnlockPattern()).isEqualTo(UPDATED_STAGE_UNLOCK_PATTERN);
        assertThat(testMAdventQuestStage.getStoryOnly()).isEqualTo(UPDATED_STORY_ONLY);
        assertThat(testMAdventQuestStage.getFirstHalfNpcDeckId()).isEqualTo(UPDATED_FIRST_HALF_NPC_DECK_ID);
        assertThat(testMAdventQuestStage.getFirstHalfEnvironmentId()).isEqualTo(UPDATED_FIRST_HALF_ENVIRONMENT_ID);
        assertThat(testMAdventQuestStage.getSecondHalfNpcDeckId()).isEqualTo(UPDATED_SECOND_HALF_NPC_DECK_ID);
        assertThat(testMAdventQuestStage.getSecondHalfEnvironmentId()).isEqualTo(UPDATED_SECOND_HALF_ENVIRONMENT_ID);
        assertThat(testMAdventQuestStage.getExtraFirstHalfNpcDeckId()).isEqualTo(UPDATED_EXTRA_FIRST_HALF_NPC_DECK_ID);
        assertThat(testMAdventQuestStage.getExtraSecondHalfNpcDeckId()).isEqualTo(UPDATED_EXTRA_SECOND_HALF_NPC_DECK_ID);
        assertThat(testMAdventQuestStage.getConsumeAp()).isEqualTo(UPDATED_CONSUME_AP);
        assertThat(testMAdventQuestStage.getKickOffType()).isEqualTo(UPDATED_KICK_OFF_TYPE);
        assertThat(testMAdventQuestStage.getMatchMinute()).isEqualTo(UPDATED_MATCH_MINUTE);
        assertThat(testMAdventQuestStage.getEnableExtra()).isEqualTo(UPDATED_ENABLE_EXTRA);
        assertThat(testMAdventQuestStage.getEnablePk()).isEqualTo(UPDATED_ENABLE_PK);
        assertThat(testMAdventQuestStage.getAiLevel()).isEqualTo(UPDATED_AI_LEVEL);
        assertThat(testMAdventQuestStage.getStartAtSecondHalf()).isEqualTo(UPDATED_START_AT_SECOND_HALF);
        assertThat(testMAdventQuestStage.getStartScore()).isEqualTo(UPDATED_START_SCORE);
        assertThat(testMAdventQuestStage.getStartScoreOpponent()).isEqualTo(UPDATED_START_SCORE_OPPONENT);
        assertThat(testMAdventQuestStage.getConditionId()).isEqualTo(UPDATED_CONDITION_ID);
        assertThat(testMAdventQuestStage.getOptionId()).isEqualTo(UPDATED_OPTION_ID);
        assertThat(testMAdventQuestStage.getDeckConditionId()).isEqualTo(UPDATED_DECK_CONDITION_ID);
    }

    @Test
    @Transactional
    public void updateNonExistingMAdventQuestStage() throws Exception {
        int databaseSizeBeforeUpdate = mAdventQuestStageRepository.findAll().size();

        // Create the MAdventQuestStage
        MAdventQuestStageDTO mAdventQuestStageDTO = mAdventQuestStageMapper.toDto(mAdventQuestStage);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restMAdventQuestStageMockMvc.perform(put("/api/m-advent-quest-stages")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mAdventQuestStageDTO)))
            .andExpect(status().isBadRequest());

        // Validate the MAdventQuestStage in the database
        List<MAdventQuestStage> mAdventQuestStageList = mAdventQuestStageRepository.findAll();
        assertThat(mAdventQuestStageList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteMAdventQuestStage() throws Exception {
        // Initialize the database
        mAdventQuestStageRepository.saveAndFlush(mAdventQuestStage);

        int databaseSizeBeforeDelete = mAdventQuestStageRepository.findAll().size();

        // Delete the mAdventQuestStage
        restMAdventQuestStageMockMvc.perform(delete("/api/m-advent-quest-stages/{id}", mAdventQuestStage.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database is empty
        List<MAdventQuestStage> mAdventQuestStageList = mAdventQuestStageRepository.findAll();
        assertThat(mAdventQuestStageList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(MAdventQuestStage.class);
        MAdventQuestStage mAdventQuestStage1 = new MAdventQuestStage();
        mAdventQuestStage1.setId(1L);
        MAdventQuestStage mAdventQuestStage2 = new MAdventQuestStage();
        mAdventQuestStage2.setId(mAdventQuestStage1.getId());
        assertThat(mAdventQuestStage1).isEqualTo(mAdventQuestStage2);
        mAdventQuestStage2.setId(2L);
        assertThat(mAdventQuestStage1).isNotEqualTo(mAdventQuestStage2);
        mAdventQuestStage1.setId(null);
        assertThat(mAdventQuestStage1).isNotEqualTo(mAdventQuestStage2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(MAdventQuestStageDTO.class);
        MAdventQuestStageDTO mAdventQuestStageDTO1 = new MAdventQuestStageDTO();
        mAdventQuestStageDTO1.setId(1L);
        MAdventQuestStageDTO mAdventQuestStageDTO2 = new MAdventQuestStageDTO();
        assertThat(mAdventQuestStageDTO1).isNotEqualTo(mAdventQuestStageDTO2);
        mAdventQuestStageDTO2.setId(mAdventQuestStageDTO1.getId());
        assertThat(mAdventQuestStageDTO1).isEqualTo(mAdventQuestStageDTO2);
        mAdventQuestStageDTO2.setId(2L);
        assertThat(mAdventQuestStageDTO1).isNotEqualTo(mAdventQuestStageDTO2);
        mAdventQuestStageDTO1.setId(null);
        assertThat(mAdventQuestStageDTO1).isNotEqualTo(mAdventQuestStageDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(mAdventQuestStageMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(mAdventQuestStageMapper.fromId(null)).isNull();
    }
}
