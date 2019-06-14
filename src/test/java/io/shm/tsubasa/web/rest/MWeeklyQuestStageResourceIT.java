package io.shm.tsubasa.web.rest;

import io.shm.tsubasa.TsubasaApp;
import io.shm.tsubasa.domain.MWeeklyQuestStage;
import io.shm.tsubasa.domain.MWeeklyQuestWorld;
import io.shm.tsubasa.repository.MWeeklyQuestStageRepository;
import io.shm.tsubasa.service.MWeeklyQuestStageService;
import io.shm.tsubasa.service.dto.MWeeklyQuestStageDTO;
import io.shm.tsubasa.service.mapper.MWeeklyQuestStageMapper;
import io.shm.tsubasa.web.rest.errors.ExceptionTranslator;
import io.shm.tsubasa.service.dto.MWeeklyQuestStageCriteria;
import io.shm.tsubasa.service.MWeeklyQuestStageQueryService;

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
 * Integration tests for the {@Link MWeeklyQuestStageResource} REST controller.
 */
@SpringBootTest(classes = TsubasaApp.class)
public class MWeeklyQuestStageResourceIT {

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
    private MWeeklyQuestStageRepository mWeeklyQuestStageRepository;

    @Autowired
    private MWeeklyQuestStageMapper mWeeklyQuestStageMapper;

    @Autowired
    private MWeeklyQuestStageService mWeeklyQuestStageService;

    @Autowired
    private MWeeklyQuestStageQueryService mWeeklyQuestStageQueryService;

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

    private MockMvc restMWeeklyQuestStageMockMvc;

    private MWeeklyQuestStage mWeeklyQuestStage;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final MWeeklyQuestStageResource mWeeklyQuestStageResource = new MWeeklyQuestStageResource(mWeeklyQuestStageService, mWeeklyQuestStageQueryService);
        this.restMWeeklyQuestStageMockMvc = MockMvcBuilders.standaloneSetup(mWeeklyQuestStageResource)
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
    public static MWeeklyQuestStage createEntity(EntityManager em) {
        MWeeklyQuestStage mWeeklyQuestStage = new MWeeklyQuestStage()
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
        MWeeklyQuestWorld mWeeklyQuestWorld;
        if (TestUtil.findAll(em, MWeeklyQuestWorld.class).isEmpty()) {
            mWeeklyQuestWorld = MWeeklyQuestWorldResourceIT.createEntity(em);
            em.persist(mWeeklyQuestWorld);
            em.flush();
        } else {
            mWeeklyQuestWorld = TestUtil.findAll(em, MWeeklyQuestWorld.class).get(0);
        }
        mWeeklyQuestStage.setMweeklyquestworld(mWeeklyQuestWorld);
        return mWeeklyQuestStage;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static MWeeklyQuestStage createUpdatedEntity(EntityManager em) {
        MWeeklyQuestStage mWeeklyQuestStage = new MWeeklyQuestStage()
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
        MWeeklyQuestWorld mWeeklyQuestWorld;
        if (TestUtil.findAll(em, MWeeklyQuestWorld.class).isEmpty()) {
            mWeeklyQuestWorld = MWeeklyQuestWorldResourceIT.createUpdatedEntity(em);
            em.persist(mWeeklyQuestWorld);
            em.flush();
        } else {
            mWeeklyQuestWorld = TestUtil.findAll(em, MWeeklyQuestWorld.class).get(0);
        }
        mWeeklyQuestStage.setMweeklyquestworld(mWeeklyQuestWorld);
        return mWeeklyQuestStage;
    }

    @BeforeEach
    public void initTest() {
        mWeeklyQuestStage = createEntity(em);
    }

    @Test
    @Transactional
    public void createMWeeklyQuestStage() throws Exception {
        int databaseSizeBeforeCreate = mWeeklyQuestStageRepository.findAll().size();

        // Create the MWeeklyQuestStage
        MWeeklyQuestStageDTO mWeeklyQuestStageDTO = mWeeklyQuestStageMapper.toDto(mWeeklyQuestStage);
        restMWeeklyQuestStageMockMvc.perform(post("/api/m-weekly-quest-stages")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mWeeklyQuestStageDTO)))
            .andExpect(status().isCreated());

        // Validate the MWeeklyQuestStage in the database
        List<MWeeklyQuestStage> mWeeklyQuestStageList = mWeeklyQuestStageRepository.findAll();
        assertThat(mWeeklyQuestStageList).hasSize(databaseSizeBeforeCreate + 1);
        MWeeklyQuestStage testMWeeklyQuestStage = mWeeklyQuestStageList.get(mWeeklyQuestStageList.size() - 1);
        assertThat(testMWeeklyQuestStage.getWorldId()).isEqualTo(DEFAULT_WORLD_ID);
        assertThat(testMWeeklyQuestStage.getNumber()).isEqualTo(DEFAULT_NUMBER);
        assertThat(testMWeeklyQuestStage.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testMWeeklyQuestStage.getImagePath()).isEqualTo(DEFAULT_IMAGE_PATH);
        assertThat(testMWeeklyQuestStage.getCharacterThumbnailPath()).isEqualTo(DEFAULT_CHARACTER_THUMBNAIL_PATH);
        assertThat(testMWeeklyQuestStage.getDifficulty()).isEqualTo(DEFAULT_DIFFICULTY);
        assertThat(testMWeeklyQuestStage.getStageUnlockPattern()).isEqualTo(DEFAULT_STAGE_UNLOCK_PATTERN);
        assertThat(testMWeeklyQuestStage.getStoryOnly()).isEqualTo(DEFAULT_STORY_ONLY);
        assertThat(testMWeeklyQuestStage.getFirstHalfNpcDeckId()).isEqualTo(DEFAULT_FIRST_HALF_NPC_DECK_ID);
        assertThat(testMWeeklyQuestStage.getFirstHalfEnvironmentId()).isEqualTo(DEFAULT_FIRST_HALF_ENVIRONMENT_ID);
        assertThat(testMWeeklyQuestStage.getSecondHalfNpcDeckId()).isEqualTo(DEFAULT_SECOND_HALF_NPC_DECK_ID);
        assertThat(testMWeeklyQuestStage.getSecondHalfEnvironmentId()).isEqualTo(DEFAULT_SECOND_HALF_ENVIRONMENT_ID);
        assertThat(testMWeeklyQuestStage.getExtraFirstHalfNpcDeckId()).isEqualTo(DEFAULT_EXTRA_FIRST_HALF_NPC_DECK_ID);
        assertThat(testMWeeklyQuestStage.getExtraSecondHalfNpcDeckId()).isEqualTo(DEFAULT_EXTRA_SECOND_HALF_NPC_DECK_ID);
        assertThat(testMWeeklyQuestStage.getConsumeAp()).isEqualTo(DEFAULT_CONSUME_AP);
        assertThat(testMWeeklyQuestStage.getKickOffType()).isEqualTo(DEFAULT_KICK_OFF_TYPE);
        assertThat(testMWeeklyQuestStage.getMatchMinute()).isEqualTo(DEFAULT_MATCH_MINUTE);
        assertThat(testMWeeklyQuestStage.getEnableExtra()).isEqualTo(DEFAULT_ENABLE_EXTRA);
        assertThat(testMWeeklyQuestStage.getEnablePk()).isEqualTo(DEFAULT_ENABLE_PK);
        assertThat(testMWeeklyQuestStage.getAiLevel()).isEqualTo(DEFAULT_AI_LEVEL);
        assertThat(testMWeeklyQuestStage.getStartAtSecondHalf()).isEqualTo(DEFAULT_START_AT_SECOND_HALF);
        assertThat(testMWeeklyQuestStage.getStartScore()).isEqualTo(DEFAULT_START_SCORE);
        assertThat(testMWeeklyQuestStage.getStartScoreOpponent()).isEqualTo(DEFAULT_START_SCORE_OPPONENT);
        assertThat(testMWeeklyQuestStage.getConditionId()).isEqualTo(DEFAULT_CONDITION_ID);
        assertThat(testMWeeklyQuestStage.getOptionId()).isEqualTo(DEFAULT_OPTION_ID);
        assertThat(testMWeeklyQuestStage.getDeckConditionId()).isEqualTo(DEFAULT_DECK_CONDITION_ID);
    }

    @Test
    @Transactional
    public void createMWeeklyQuestStageWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = mWeeklyQuestStageRepository.findAll().size();

        // Create the MWeeklyQuestStage with an existing ID
        mWeeklyQuestStage.setId(1L);
        MWeeklyQuestStageDTO mWeeklyQuestStageDTO = mWeeklyQuestStageMapper.toDto(mWeeklyQuestStage);

        // An entity with an existing ID cannot be created, so this API call must fail
        restMWeeklyQuestStageMockMvc.perform(post("/api/m-weekly-quest-stages")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mWeeklyQuestStageDTO)))
            .andExpect(status().isBadRequest());

        // Validate the MWeeklyQuestStage in the database
        List<MWeeklyQuestStage> mWeeklyQuestStageList = mWeeklyQuestStageRepository.findAll();
        assertThat(mWeeklyQuestStageList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkWorldIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = mWeeklyQuestStageRepository.findAll().size();
        // set the field null
        mWeeklyQuestStage.setWorldId(null);

        // Create the MWeeklyQuestStage, which fails.
        MWeeklyQuestStageDTO mWeeklyQuestStageDTO = mWeeklyQuestStageMapper.toDto(mWeeklyQuestStage);

        restMWeeklyQuestStageMockMvc.perform(post("/api/m-weekly-quest-stages")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mWeeklyQuestStageDTO)))
            .andExpect(status().isBadRequest());

        List<MWeeklyQuestStage> mWeeklyQuestStageList = mWeeklyQuestStageRepository.findAll();
        assertThat(mWeeklyQuestStageList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkNumberIsRequired() throws Exception {
        int databaseSizeBeforeTest = mWeeklyQuestStageRepository.findAll().size();
        // set the field null
        mWeeklyQuestStage.setNumber(null);

        // Create the MWeeklyQuestStage, which fails.
        MWeeklyQuestStageDTO mWeeklyQuestStageDTO = mWeeklyQuestStageMapper.toDto(mWeeklyQuestStage);

        restMWeeklyQuestStageMockMvc.perform(post("/api/m-weekly-quest-stages")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mWeeklyQuestStageDTO)))
            .andExpect(status().isBadRequest());

        List<MWeeklyQuestStage> mWeeklyQuestStageList = mWeeklyQuestStageRepository.findAll();
        assertThat(mWeeklyQuestStageList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkDifficultyIsRequired() throws Exception {
        int databaseSizeBeforeTest = mWeeklyQuestStageRepository.findAll().size();
        // set the field null
        mWeeklyQuestStage.setDifficulty(null);

        // Create the MWeeklyQuestStage, which fails.
        MWeeklyQuestStageDTO mWeeklyQuestStageDTO = mWeeklyQuestStageMapper.toDto(mWeeklyQuestStage);

        restMWeeklyQuestStageMockMvc.perform(post("/api/m-weekly-quest-stages")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mWeeklyQuestStageDTO)))
            .andExpect(status().isBadRequest());

        List<MWeeklyQuestStage> mWeeklyQuestStageList = mWeeklyQuestStageRepository.findAll();
        assertThat(mWeeklyQuestStageList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkStageUnlockPatternIsRequired() throws Exception {
        int databaseSizeBeforeTest = mWeeklyQuestStageRepository.findAll().size();
        // set the field null
        mWeeklyQuestStage.setStageUnlockPattern(null);

        // Create the MWeeklyQuestStage, which fails.
        MWeeklyQuestStageDTO mWeeklyQuestStageDTO = mWeeklyQuestStageMapper.toDto(mWeeklyQuestStage);

        restMWeeklyQuestStageMockMvc.perform(post("/api/m-weekly-quest-stages")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mWeeklyQuestStageDTO)))
            .andExpect(status().isBadRequest());

        List<MWeeklyQuestStage> mWeeklyQuestStageList = mWeeklyQuestStageRepository.findAll();
        assertThat(mWeeklyQuestStageList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkStoryOnlyIsRequired() throws Exception {
        int databaseSizeBeforeTest = mWeeklyQuestStageRepository.findAll().size();
        // set the field null
        mWeeklyQuestStage.setStoryOnly(null);

        // Create the MWeeklyQuestStage, which fails.
        MWeeklyQuestStageDTO mWeeklyQuestStageDTO = mWeeklyQuestStageMapper.toDto(mWeeklyQuestStage);

        restMWeeklyQuestStageMockMvc.perform(post("/api/m-weekly-quest-stages")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mWeeklyQuestStageDTO)))
            .andExpect(status().isBadRequest());

        List<MWeeklyQuestStage> mWeeklyQuestStageList = mWeeklyQuestStageRepository.findAll();
        assertThat(mWeeklyQuestStageList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkFirstHalfNpcDeckIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = mWeeklyQuestStageRepository.findAll().size();
        // set the field null
        mWeeklyQuestStage.setFirstHalfNpcDeckId(null);

        // Create the MWeeklyQuestStage, which fails.
        MWeeklyQuestStageDTO mWeeklyQuestStageDTO = mWeeklyQuestStageMapper.toDto(mWeeklyQuestStage);

        restMWeeklyQuestStageMockMvc.perform(post("/api/m-weekly-quest-stages")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mWeeklyQuestStageDTO)))
            .andExpect(status().isBadRequest());

        List<MWeeklyQuestStage> mWeeklyQuestStageList = mWeeklyQuestStageRepository.findAll();
        assertThat(mWeeklyQuestStageList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkFirstHalfEnvironmentIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = mWeeklyQuestStageRepository.findAll().size();
        // set the field null
        mWeeklyQuestStage.setFirstHalfEnvironmentId(null);

        // Create the MWeeklyQuestStage, which fails.
        MWeeklyQuestStageDTO mWeeklyQuestStageDTO = mWeeklyQuestStageMapper.toDto(mWeeklyQuestStage);

        restMWeeklyQuestStageMockMvc.perform(post("/api/m-weekly-quest-stages")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mWeeklyQuestStageDTO)))
            .andExpect(status().isBadRequest());

        List<MWeeklyQuestStage> mWeeklyQuestStageList = mWeeklyQuestStageRepository.findAll();
        assertThat(mWeeklyQuestStageList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkSecondHalfNpcDeckIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = mWeeklyQuestStageRepository.findAll().size();
        // set the field null
        mWeeklyQuestStage.setSecondHalfNpcDeckId(null);

        // Create the MWeeklyQuestStage, which fails.
        MWeeklyQuestStageDTO mWeeklyQuestStageDTO = mWeeklyQuestStageMapper.toDto(mWeeklyQuestStage);

        restMWeeklyQuestStageMockMvc.perform(post("/api/m-weekly-quest-stages")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mWeeklyQuestStageDTO)))
            .andExpect(status().isBadRequest());

        List<MWeeklyQuestStage> mWeeklyQuestStageList = mWeeklyQuestStageRepository.findAll();
        assertThat(mWeeklyQuestStageList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkSecondHalfEnvironmentIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = mWeeklyQuestStageRepository.findAll().size();
        // set the field null
        mWeeklyQuestStage.setSecondHalfEnvironmentId(null);

        // Create the MWeeklyQuestStage, which fails.
        MWeeklyQuestStageDTO mWeeklyQuestStageDTO = mWeeklyQuestStageMapper.toDto(mWeeklyQuestStage);

        restMWeeklyQuestStageMockMvc.perform(post("/api/m-weekly-quest-stages")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mWeeklyQuestStageDTO)))
            .andExpect(status().isBadRequest());

        List<MWeeklyQuestStage> mWeeklyQuestStageList = mWeeklyQuestStageRepository.findAll();
        assertThat(mWeeklyQuestStageList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkExtraFirstHalfNpcDeckIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = mWeeklyQuestStageRepository.findAll().size();
        // set the field null
        mWeeklyQuestStage.setExtraFirstHalfNpcDeckId(null);

        // Create the MWeeklyQuestStage, which fails.
        MWeeklyQuestStageDTO mWeeklyQuestStageDTO = mWeeklyQuestStageMapper.toDto(mWeeklyQuestStage);

        restMWeeklyQuestStageMockMvc.perform(post("/api/m-weekly-quest-stages")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mWeeklyQuestStageDTO)))
            .andExpect(status().isBadRequest());

        List<MWeeklyQuestStage> mWeeklyQuestStageList = mWeeklyQuestStageRepository.findAll();
        assertThat(mWeeklyQuestStageList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkExtraSecondHalfNpcDeckIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = mWeeklyQuestStageRepository.findAll().size();
        // set the field null
        mWeeklyQuestStage.setExtraSecondHalfNpcDeckId(null);

        // Create the MWeeklyQuestStage, which fails.
        MWeeklyQuestStageDTO mWeeklyQuestStageDTO = mWeeklyQuestStageMapper.toDto(mWeeklyQuestStage);

        restMWeeklyQuestStageMockMvc.perform(post("/api/m-weekly-quest-stages")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mWeeklyQuestStageDTO)))
            .andExpect(status().isBadRequest());

        List<MWeeklyQuestStage> mWeeklyQuestStageList = mWeeklyQuestStageRepository.findAll();
        assertThat(mWeeklyQuestStageList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkConsumeApIsRequired() throws Exception {
        int databaseSizeBeforeTest = mWeeklyQuestStageRepository.findAll().size();
        // set the field null
        mWeeklyQuestStage.setConsumeAp(null);

        // Create the MWeeklyQuestStage, which fails.
        MWeeklyQuestStageDTO mWeeklyQuestStageDTO = mWeeklyQuestStageMapper.toDto(mWeeklyQuestStage);

        restMWeeklyQuestStageMockMvc.perform(post("/api/m-weekly-quest-stages")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mWeeklyQuestStageDTO)))
            .andExpect(status().isBadRequest());

        List<MWeeklyQuestStage> mWeeklyQuestStageList = mWeeklyQuestStageRepository.findAll();
        assertThat(mWeeklyQuestStageList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkKickOffTypeIsRequired() throws Exception {
        int databaseSizeBeforeTest = mWeeklyQuestStageRepository.findAll().size();
        // set the field null
        mWeeklyQuestStage.setKickOffType(null);

        // Create the MWeeklyQuestStage, which fails.
        MWeeklyQuestStageDTO mWeeklyQuestStageDTO = mWeeklyQuestStageMapper.toDto(mWeeklyQuestStage);

        restMWeeklyQuestStageMockMvc.perform(post("/api/m-weekly-quest-stages")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mWeeklyQuestStageDTO)))
            .andExpect(status().isBadRequest());

        List<MWeeklyQuestStage> mWeeklyQuestStageList = mWeeklyQuestStageRepository.findAll();
        assertThat(mWeeklyQuestStageList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkMatchMinuteIsRequired() throws Exception {
        int databaseSizeBeforeTest = mWeeklyQuestStageRepository.findAll().size();
        // set the field null
        mWeeklyQuestStage.setMatchMinute(null);

        // Create the MWeeklyQuestStage, which fails.
        MWeeklyQuestStageDTO mWeeklyQuestStageDTO = mWeeklyQuestStageMapper.toDto(mWeeklyQuestStage);

        restMWeeklyQuestStageMockMvc.perform(post("/api/m-weekly-quest-stages")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mWeeklyQuestStageDTO)))
            .andExpect(status().isBadRequest());

        List<MWeeklyQuestStage> mWeeklyQuestStageList = mWeeklyQuestStageRepository.findAll();
        assertThat(mWeeklyQuestStageList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkEnableExtraIsRequired() throws Exception {
        int databaseSizeBeforeTest = mWeeklyQuestStageRepository.findAll().size();
        // set the field null
        mWeeklyQuestStage.setEnableExtra(null);

        // Create the MWeeklyQuestStage, which fails.
        MWeeklyQuestStageDTO mWeeklyQuestStageDTO = mWeeklyQuestStageMapper.toDto(mWeeklyQuestStage);

        restMWeeklyQuestStageMockMvc.perform(post("/api/m-weekly-quest-stages")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mWeeklyQuestStageDTO)))
            .andExpect(status().isBadRequest());

        List<MWeeklyQuestStage> mWeeklyQuestStageList = mWeeklyQuestStageRepository.findAll();
        assertThat(mWeeklyQuestStageList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkEnablePkIsRequired() throws Exception {
        int databaseSizeBeforeTest = mWeeklyQuestStageRepository.findAll().size();
        // set the field null
        mWeeklyQuestStage.setEnablePk(null);

        // Create the MWeeklyQuestStage, which fails.
        MWeeklyQuestStageDTO mWeeklyQuestStageDTO = mWeeklyQuestStageMapper.toDto(mWeeklyQuestStage);

        restMWeeklyQuestStageMockMvc.perform(post("/api/m-weekly-quest-stages")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mWeeklyQuestStageDTO)))
            .andExpect(status().isBadRequest());

        List<MWeeklyQuestStage> mWeeklyQuestStageList = mWeeklyQuestStageRepository.findAll();
        assertThat(mWeeklyQuestStageList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkAiLevelIsRequired() throws Exception {
        int databaseSizeBeforeTest = mWeeklyQuestStageRepository.findAll().size();
        // set the field null
        mWeeklyQuestStage.setAiLevel(null);

        // Create the MWeeklyQuestStage, which fails.
        MWeeklyQuestStageDTO mWeeklyQuestStageDTO = mWeeklyQuestStageMapper.toDto(mWeeklyQuestStage);

        restMWeeklyQuestStageMockMvc.perform(post("/api/m-weekly-quest-stages")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mWeeklyQuestStageDTO)))
            .andExpect(status().isBadRequest());

        List<MWeeklyQuestStage> mWeeklyQuestStageList = mWeeklyQuestStageRepository.findAll();
        assertThat(mWeeklyQuestStageList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkStartAtSecondHalfIsRequired() throws Exception {
        int databaseSizeBeforeTest = mWeeklyQuestStageRepository.findAll().size();
        // set the field null
        mWeeklyQuestStage.setStartAtSecondHalf(null);

        // Create the MWeeklyQuestStage, which fails.
        MWeeklyQuestStageDTO mWeeklyQuestStageDTO = mWeeklyQuestStageMapper.toDto(mWeeklyQuestStage);

        restMWeeklyQuestStageMockMvc.perform(post("/api/m-weekly-quest-stages")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mWeeklyQuestStageDTO)))
            .andExpect(status().isBadRequest());

        List<MWeeklyQuestStage> mWeeklyQuestStageList = mWeeklyQuestStageRepository.findAll();
        assertThat(mWeeklyQuestStageList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkStartScoreIsRequired() throws Exception {
        int databaseSizeBeforeTest = mWeeklyQuestStageRepository.findAll().size();
        // set the field null
        mWeeklyQuestStage.setStartScore(null);

        // Create the MWeeklyQuestStage, which fails.
        MWeeklyQuestStageDTO mWeeklyQuestStageDTO = mWeeklyQuestStageMapper.toDto(mWeeklyQuestStage);

        restMWeeklyQuestStageMockMvc.perform(post("/api/m-weekly-quest-stages")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mWeeklyQuestStageDTO)))
            .andExpect(status().isBadRequest());

        List<MWeeklyQuestStage> mWeeklyQuestStageList = mWeeklyQuestStageRepository.findAll();
        assertThat(mWeeklyQuestStageList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkStartScoreOpponentIsRequired() throws Exception {
        int databaseSizeBeforeTest = mWeeklyQuestStageRepository.findAll().size();
        // set the field null
        mWeeklyQuestStage.setStartScoreOpponent(null);

        // Create the MWeeklyQuestStage, which fails.
        MWeeklyQuestStageDTO mWeeklyQuestStageDTO = mWeeklyQuestStageMapper.toDto(mWeeklyQuestStage);

        restMWeeklyQuestStageMockMvc.perform(post("/api/m-weekly-quest-stages")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mWeeklyQuestStageDTO)))
            .andExpect(status().isBadRequest());

        List<MWeeklyQuestStage> mWeeklyQuestStageList = mWeeklyQuestStageRepository.findAll();
        assertThat(mWeeklyQuestStageList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllMWeeklyQuestStages() throws Exception {
        // Initialize the database
        mWeeklyQuestStageRepository.saveAndFlush(mWeeklyQuestStage);

        // Get all the mWeeklyQuestStageList
        restMWeeklyQuestStageMockMvc.perform(get("/api/m-weekly-quest-stages?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(mWeeklyQuestStage.getId().intValue())))
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
    public void getMWeeklyQuestStage() throws Exception {
        // Initialize the database
        mWeeklyQuestStageRepository.saveAndFlush(mWeeklyQuestStage);

        // Get the mWeeklyQuestStage
        restMWeeklyQuestStageMockMvc.perform(get("/api/m-weekly-quest-stages/{id}", mWeeklyQuestStage.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(mWeeklyQuestStage.getId().intValue()))
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
    public void getAllMWeeklyQuestStagesByWorldIdIsEqualToSomething() throws Exception {
        // Initialize the database
        mWeeklyQuestStageRepository.saveAndFlush(mWeeklyQuestStage);

        // Get all the mWeeklyQuestStageList where worldId equals to DEFAULT_WORLD_ID
        defaultMWeeklyQuestStageShouldBeFound("worldId.equals=" + DEFAULT_WORLD_ID);

        // Get all the mWeeklyQuestStageList where worldId equals to UPDATED_WORLD_ID
        defaultMWeeklyQuestStageShouldNotBeFound("worldId.equals=" + UPDATED_WORLD_ID);
    }

    @Test
    @Transactional
    public void getAllMWeeklyQuestStagesByWorldIdIsInShouldWork() throws Exception {
        // Initialize the database
        mWeeklyQuestStageRepository.saveAndFlush(mWeeklyQuestStage);

        // Get all the mWeeklyQuestStageList where worldId in DEFAULT_WORLD_ID or UPDATED_WORLD_ID
        defaultMWeeklyQuestStageShouldBeFound("worldId.in=" + DEFAULT_WORLD_ID + "," + UPDATED_WORLD_ID);

        // Get all the mWeeklyQuestStageList where worldId equals to UPDATED_WORLD_ID
        defaultMWeeklyQuestStageShouldNotBeFound("worldId.in=" + UPDATED_WORLD_ID);
    }

    @Test
    @Transactional
    public void getAllMWeeklyQuestStagesByWorldIdIsNullOrNotNull() throws Exception {
        // Initialize the database
        mWeeklyQuestStageRepository.saveAndFlush(mWeeklyQuestStage);

        // Get all the mWeeklyQuestStageList where worldId is not null
        defaultMWeeklyQuestStageShouldBeFound("worldId.specified=true");

        // Get all the mWeeklyQuestStageList where worldId is null
        defaultMWeeklyQuestStageShouldNotBeFound("worldId.specified=false");
    }

    @Test
    @Transactional
    public void getAllMWeeklyQuestStagesByWorldIdIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mWeeklyQuestStageRepository.saveAndFlush(mWeeklyQuestStage);

        // Get all the mWeeklyQuestStageList where worldId greater than or equals to DEFAULT_WORLD_ID
        defaultMWeeklyQuestStageShouldBeFound("worldId.greaterOrEqualThan=" + DEFAULT_WORLD_ID);

        // Get all the mWeeklyQuestStageList where worldId greater than or equals to UPDATED_WORLD_ID
        defaultMWeeklyQuestStageShouldNotBeFound("worldId.greaterOrEqualThan=" + UPDATED_WORLD_ID);
    }

    @Test
    @Transactional
    public void getAllMWeeklyQuestStagesByWorldIdIsLessThanSomething() throws Exception {
        // Initialize the database
        mWeeklyQuestStageRepository.saveAndFlush(mWeeklyQuestStage);

        // Get all the mWeeklyQuestStageList where worldId less than or equals to DEFAULT_WORLD_ID
        defaultMWeeklyQuestStageShouldNotBeFound("worldId.lessThan=" + DEFAULT_WORLD_ID);

        // Get all the mWeeklyQuestStageList where worldId less than or equals to UPDATED_WORLD_ID
        defaultMWeeklyQuestStageShouldBeFound("worldId.lessThan=" + UPDATED_WORLD_ID);
    }


    @Test
    @Transactional
    public void getAllMWeeklyQuestStagesByNumberIsEqualToSomething() throws Exception {
        // Initialize the database
        mWeeklyQuestStageRepository.saveAndFlush(mWeeklyQuestStage);

        // Get all the mWeeklyQuestStageList where number equals to DEFAULT_NUMBER
        defaultMWeeklyQuestStageShouldBeFound("number.equals=" + DEFAULT_NUMBER);

        // Get all the mWeeklyQuestStageList where number equals to UPDATED_NUMBER
        defaultMWeeklyQuestStageShouldNotBeFound("number.equals=" + UPDATED_NUMBER);
    }

    @Test
    @Transactional
    public void getAllMWeeklyQuestStagesByNumberIsInShouldWork() throws Exception {
        // Initialize the database
        mWeeklyQuestStageRepository.saveAndFlush(mWeeklyQuestStage);

        // Get all the mWeeklyQuestStageList where number in DEFAULT_NUMBER or UPDATED_NUMBER
        defaultMWeeklyQuestStageShouldBeFound("number.in=" + DEFAULT_NUMBER + "," + UPDATED_NUMBER);

        // Get all the mWeeklyQuestStageList where number equals to UPDATED_NUMBER
        defaultMWeeklyQuestStageShouldNotBeFound("number.in=" + UPDATED_NUMBER);
    }

    @Test
    @Transactional
    public void getAllMWeeklyQuestStagesByNumberIsNullOrNotNull() throws Exception {
        // Initialize the database
        mWeeklyQuestStageRepository.saveAndFlush(mWeeklyQuestStage);

        // Get all the mWeeklyQuestStageList where number is not null
        defaultMWeeklyQuestStageShouldBeFound("number.specified=true");

        // Get all the mWeeklyQuestStageList where number is null
        defaultMWeeklyQuestStageShouldNotBeFound("number.specified=false");
    }

    @Test
    @Transactional
    public void getAllMWeeklyQuestStagesByNumberIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mWeeklyQuestStageRepository.saveAndFlush(mWeeklyQuestStage);

        // Get all the mWeeklyQuestStageList where number greater than or equals to DEFAULT_NUMBER
        defaultMWeeklyQuestStageShouldBeFound("number.greaterOrEqualThan=" + DEFAULT_NUMBER);

        // Get all the mWeeklyQuestStageList where number greater than or equals to UPDATED_NUMBER
        defaultMWeeklyQuestStageShouldNotBeFound("number.greaterOrEqualThan=" + UPDATED_NUMBER);
    }

    @Test
    @Transactional
    public void getAllMWeeklyQuestStagesByNumberIsLessThanSomething() throws Exception {
        // Initialize the database
        mWeeklyQuestStageRepository.saveAndFlush(mWeeklyQuestStage);

        // Get all the mWeeklyQuestStageList where number less than or equals to DEFAULT_NUMBER
        defaultMWeeklyQuestStageShouldNotBeFound("number.lessThan=" + DEFAULT_NUMBER);

        // Get all the mWeeklyQuestStageList where number less than or equals to UPDATED_NUMBER
        defaultMWeeklyQuestStageShouldBeFound("number.lessThan=" + UPDATED_NUMBER);
    }


    @Test
    @Transactional
    public void getAllMWeeklyQuestStagesByDifficultyIsEqualToSomething() throws Exception {
        // Initialize the database
        mWeeklyQuestStageRepository.saveAndFlush(mWeeklyQuestStage);

        // Get all the mWeeklyQuestStageList where difficulty equals to DEFAULT_DIFFICULTY
        defaultMWeeklyQuestStageShouldBeFound("difficulty.equals=" + DEFAULT_DIFFICULTY);

        // Get all the mWeeklyQuestStageList where difficulty equals to UPDATED_DIFFICULTY
        defaultMWeeklyQuestStageShouldNotBeFound("difficulty.equals=" + UPDATED_DIFFICULTY);
    }

    @Test
    @Transactional
    public void getAllMWeeklyQuestStagesByDifficultyIsInShouldWork() throws Exception {
        // Initialize the database
        mWeeklyQuestStageRepository.saveAndFlush(mWeeklyQuestStage);

        // Get all the mWeeklyQuestStageList where difficulty in DEFAULT_DIFFICULTY or UPDATED_DIFFICULTY
        defaultMWeeklyQuestStageShouldBeFound("difficulty.in=" + DEFAULT_DIFFICULTY + "," + UPDATED_DIFFICULTY);

        // Get all the mWeeklyQuestStageList where difficulty equals to UPDATED_DIFFICULTY
        defaultMWeeklyQuestStageShouldNotBeFound("difficulty.in=" + UPDATED_DIFFICULTY);
    }

    @Test
    @Transactional
    public void getAllMWeeklyQuestStagesByDifficultyIsNullOrNotNull() throws Exception {
        // Initialize the database
        mWeeklyQuestStageRepository.saveAndFlush(mWeeklyQuestStage);

        // Get all the mWeeklyQuestStageList where difficulty is not null
        defaultMWeeklyQuestStageShouldBeFound("difficulty.specified=true");

        // Get all the mWeeklyQuestStageList where difficulty is null
        defaultMWeeklyQuestStageShouldNotBeFound("difficulty.specified=false");
    }

    @Test
    @Transactional
    public void getAllMWeeklyQuestStagesByDifficultyIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mWeeklyQuestStageRepository.saveAndFlush(mWeeklyQuestStage);

        // Get all the mWeeklyQuestStageList where difficulty greater than or equals to DEFAULT_DIFFICULTY
        defaultMWeeklyQuestStageShouldBeFound("difficulty.greaterOrEqualThan=" + DEFAULT_DIFFICULTY);

        // Get all the mWeeklyQuestStageList where difficulty greater than or equals to UPDATED_DIFFICULTY
        defaultMWeeklyQuestStageShouldNotBeFound("difficulty.greaterOrEqualThan=" + UPDATED_DIFFICULTY);
    }

    @Test
    @Transactional
    public void getAllMWeeklyQuestStagesByDifficultyIsLessThanSomething() throws Exception {
        // Initialize the database
        mWeeklyQuestStageRepository.saveAndFlush(mWeeklyQuestStage);

        // Get all the mWeeklyQuestStageList where difficulty less than or equals to DEFAULT_DIFFICULTY
        defaultMWeeklyQuestStageShouldNotBeFound("difficulty.lessThan=" + DEFAULT_DIFFICULTY);

        // Get all the mWeeklyQuestStageList where difficulty less than or equals to UPDATED_DIFFICULTY
        defaultMWeeklyQuestStageShouldBeFound("difficulty.lessThan=" + UPDATED_DIFFICULTY);
    }


    @Test
    @Transactional
    public void getAllMWeeklyQuestStagesByStageUnlockPatternIsEqualToSomething() throws Exception {
        // Initialize the database
        mWeeklyQuestStageRepository.saveAndFlush(mWeeklyQuestStage);

        // Get all the mWeeklyQuestStageList where stageUnlockPattern equals to DEFAULT_STAGE_UNLOCK_PATTERN
        defaultMWeeklyQuestStageShouldBeFound("stageUnlockPattern.equals=" + DEFAULT_STAGE_UNLOCK_PATTERN);

        // Get all the mWeeklyQuestStageList where stageUnlockPattern equals to UPDATED_STAGE_UNLOCK_PATTERN
        defaultMWeeklyQuestStageShouldNotBeFound("stageUnlockPattern.equals=" + UPDATED_STAGE_UNLOCK_PATTERN);
    }

    @Test
    @Transactional
    public void getAllMWeeklyQuestStagesByStageUnlockPatternIsInShouldWork() throws Exception {
        // Initialize the database
        mWeeklyQuestStageRepository.saveAndFlush(mWeeklyQuestStage);

        // Get all the mWeeklyQuestStageList where stageUnlockPattern in DEFAULT_STAGE_UNLOCK_PATTERN or UPDATED_STAGE_UNLOCK_PATTERN
        defaultMWeeklyQuestStageShouldBeFound("stageUnlockPattern.in=" + DEFAULT_STAGE_UNLOCK_PATTERN + "," + UPDATED_STAGE_UNLOCK_PATTERN);

        // Get all the mWeeklyQuestStageList where stageUnlockPattern equals to UPDATED_STAGE_UNLOCK_PATTERN
        defaultMWeeklyQuestStageShouldNotBeFound("stageUnlockPattern.in=" + UPDATED_STAGE_UNLOCK_PATTERN);
    }

    @Test
    @Transactional
    public void getAllMWeeklyQuestStagesByStageUnlockPatternIsNullOrNotNull() throws Exception {
        // Initialize the database
        mWeeklyQuestStageRepository.saveAndFlush(mWeeklyQuestStage);

        // Get all the mWeeklyQuestStageList where stageUnlockPattern is not null
        defaultMWeeklyQuestStageShouldBeFound("stageUnlockPattern.specified=true");

        // Get all the mWeeklyQuestStageList where stageUnlockPattern is null
        defaultMWeeklyQuestStageShouldNotBeFound("stageUnlockPattern.specified=false");
    }

    @Test
    @Transactional
    public void getAllMWeeklyQuestStagesByStageUnlockPatternIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mWeeklyQuestStageRepository.saveAndFlush(mWeeklyQuestStage);

        // Get all the mWeeklyQuestStageList where stageUnlockPattern greater than or equals to DEFAULT_STAGE_UNLOCK_PATTERN
        defaultMWeeklyQuestStageShouldBeFound("stageUnlockPattern.greaterOrEqualThan=" + DEFAULT_STAGE_UNLOCK_PATTERN);

        // Get all the mWeeklyQuestStageList where stageUnlockPattern greater than or equals to UPDATED_STAGE_UNLOCK_PATTERN
        defaultMWeeklyQuestStageShouldNotBeFound("stageUnlockPattern.greaterOrEqualThan=" + UPDATED_STAGE_UNLOCK_PATTERN);
    }

    @Test
    @Transactional
    public void getAllMWeeklyQuestStagesByStageUnlockPatternIsLessThanSomething() throws Exception {
        // Initialize the database
        mWeeklyQuestStageRepository.saveAndFlush(mWeeklyQuestStage);

        // Get all the mWeeklyQuestStageList where stageUnlockPattern less than or equals to DEFAULT_STAGE_UNLOCK_PATTERN
        defaultMWeeklyQuestStageShouldNotBeFound("stageUnlockPattern.lessThan=" + DEFAULT_STAGE_UNLOCK_PATTERN);

        // Get all the mWeeklyQuestStageList where stageUnlockPattern less than or equals to UPDATED_STAGE_UNLOCK_PATTERN
        defaultMWeeklyQuestStageShouldBeFound("stageUnlockPattern.lessThan=" + UPDATED_STAGE_UNLOCK_PATTERN);
    }


    @Test
    @Transactional
    public void getAllMWeeklyQuestStagesByStoryOnlyIsEqualToSomething() throws Exception {
        // Initialize the database
        mWeeklyQuestStageRepository.saveAndFlush(mWeeklyQuestStage);

        // Get all the mWeeklyQuestStageList where storyOnly equals to DEFAULT_STORY_ONLY
        defaultMWeeklyQuestStageShouldBeFound("storyOnly.equals=" + DEFAULT_STORY_ONLY);

        // Get all the mWeeklyQuestStageList where storyOnly equals to UPDATED_STORY_ONLY
        defaultMWeeklyQuestStageShouldNotBeFound("storyOnly.equals=" + UPDATED_STORY_ONLY);
    }

    @Test
    @Transactional
    public void getAllMWeeklyQuestStagesByStoryOnlyIsInShouldWork() throws Exception {
        // Initialize the database
        mWeeklyQuestStageRepository.saveAndFlush(mWeeklyQuestStage);

        // Get all the mWeeklyQuestStageList where storyOnly in DEFAULT_STORY_ONLY or UPDATED_STORY_ONLY
        defaultMWeeklyQuestStageShouldBeFound("storyOnly.in=" + DEFAULT_STORY_ONLY + "," + UPDATED_STORY_ONLY);

        // Get all the mWeeklyQuestStageList where storyOnly equals to UPDATED_STORY_ONLY
        defaultMWeeklyQuestStageShouldNotBeFound("storyOnly.in=" + UPDATED_STORY_ONLY);
    }

    @Test
    @Transactional
    public void getAllMWeeklyQuestStagesByStoryOnlyIsNullOrNotNull() throws Exception {
        // Initialize the database
        mWeeklyQuestStageRepository.saveAndFlush(mWeeklyQuestStage);

        // Get all the mWeeklyQuestStageList where storyOnly is not null
        defaultMWeeklyQuestStageShouldBeFound("storyOnly.specified=true");

        // Get all the mWeeklyQuestStageList where storyOnly is null
        defaultMWeeklyQuestStageShouldNotBeFound("storyOnly.specified=false");
    }

    @Test
    @Transactional
    public void getAllMWeeklyQuestStagesByStoryOnlyIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mWeeklyQuestStageRepository.saveAndFlush(mWeeklyQuestStage);

        // Get all the mWeeklyQuestStageList where storyOnly greater than or equals to DEFAULT_STORY_ONLY
        defaultMWeeklyQuestStageShouldBeFound("storyOnly.greaterOrEqualThan=" + DEFAULT_STORY_ONLY);

        // Get all the mWeeklyQuestStageList where storyOnly greater than or equals to UPDATED_STORY_ONLY
        defaultMWeeklyQuestStageShouldNotBeFound("storyOnly.greaterOrEqualThan=" + UPDATED_STORY_ONLY);
    }

    @Test
    @Transactional
    public void getAllMWeeklyQuestStagesByStoryOnlyIsLessThanSomething() throws Exception {
        // Initialize the database
        mWeeklyQuestStageRepository.saveAndFlush(mWeeklyQuestStage);

        // Get all the mWeeklyQuestStageList where storyOnly less than or equals to DEFAULT_STORY_ONLY
        defaultMWeeklyQuestStageShouldNotBeFound("storyOnly.lessThan=" + DEFAULT_STORY_ONLY);

        // Get all the mWeeklyQuestStageList where storyOnly less than or equals to UPDATED_STORY_ONLY
        defaultMWeeklyQuestStageShouldBeFound("storyOnly.lessThan=" + UPDATED_STORY_ONLY);
    }


    @Test
    @Transactional
    public void getAllMWeeklyQuestStagesByFirstHalfNpcDeckIdIsEqualToSomething() throws Exception {
        // Initialize the database
        mWeeklyQuestStageRepository.saveAndFlush(mWeeklyQuestStage);

        // Get all the mWeeklyQuestStageList where firstHalfNpcDeckId equals to DEFAULT_FIRST_HALF_NPC_DECK_ID
        defaultMWeeklyQuestStageShouldBeFound("firstHalfNpcDeckId.equals=" + DEFAULT_FIRST_HALF_NPC_DECK_ID);

        // Get all the mWeeklyQuestStageList where firstHalfNpcDeckId equals to UPDATED_FIRST_HALF_NPC_DECK_ID
        defaultMWeeklyQuestStageShouldNotBeFound("firstHalfNpcDeckId.equals=" + UPDATED_FIRST_HALF_NPC_DECK_ID);
    }

    @Test
    @Transactional
    public void getAllMWeeklyQuestStagesByFirstHalfNpcDeckIdIsInShouldWork() throws Exception {
        // Initialize the database
        mWeeklyQuestStageRepository.saveAndFlush(mWeeklyQuestStage);

        // Get all the mWeeklyQuestStageList where firstHalfNpcDeckId in DEFAULT_FIRST_HALF_NPC_DECK_ID or UPDATED_FIRST_HALF_NPC_DECK_ID
        defaultMWeeklyQuestStageShouldBeFound("firstHalfNpcDeckId.in=" + DEFAULT_FIRST_HALF_NPC_DECK_ID + "," + UPDATED_FIRST_HALF_NPC_DECK_ID);

        // Get all the mWeeklyQuestStageList where firstHalfNpcDeckId equals to UPDATED_FIRST_HALF_NPC_DECK_ID
        defaultMWeeklyQuestStageShouldNotBeFound("firstHalfNpcDeckId.in=" + UPDATED_FIRST_HALF_NPC_DECK_ID);
    }

    @Test
    @Transactional
    public void getAllMWeeklyQuestStagesByFirstHalfNpcDeckIdIsNullOrNotNull() throws Exception {
        // Initialize the database
        mWeeklyQuestStageRepository.saveAndFlush(mWeeklyQuestStage);

        // Get all the mWeeklyQuestStageList where firstHalfNpcDeckId is not null
        defaultMWeeklyQuestStageShouldBeFound("firstHalfNpcDeckId.specified=true");

        // Get all the mWeeklyQuestStageList where firstHalfNpcDeckId is null
        defaultMWeeklyQuestStageShouldNotBeFound("firstHalfNpcDeckId.specified=false");
    }

    @Test
    @Transactional
    public void getAllMWeeklyQuestStagesByFirstHalfNpcDeckIdIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mWeeklyQuestStageRepository.saveAndFlush(mWeeklyQuestStage);

        // Get all the mWeeklyQuestStageList where firstHalfNpcDeckId greater than or equals to DEFAULT_FIRST_HALF_NPC_DECK_ID
        defaultMWeeklyQuestStageShouldBeFound("firstHalfNpcDeckId.greaterOrEqualThan=" + DEFAULT_FIRST_HALF_NPC_DECK_ID);

        // Get all the mWeeklyQuestStageList where firstHalfNpcDeckId greater than or equals to UPDATED_FIRST_HALF_NPC_DECK_ID
        defaultMWeeklyQuestStageShouldNotBeFound("firstHalfNpcDeckId.greaterOrEqualThan=" + UPDATED_FIRST_HALF_NPC_DECK_ID);
    }

    @Test
    @Transactional
    public void getAllMWeeklyQuestStagesByFirstHalfNpcDeckIdIsLessThanSomething() throws Exception {
        // Initialize the database
        mWeeklyQuestStageRepository.saveAndFlush(mWeeklyQuestStage);

        // Get all the mWeeklyQuestStageList where firstHalfNpcDeckId less than or equals to DEFAULT_FIRST_HALF_NPC_DECK_ID
        defaultMWeeklyQuestStageShouldNotBeFound("firstHalfNpcDeckId.lessThan=" + DEFAULT_FIRST_HALF_NPC_DECK_ID);

        // Get all the mWeeklyQuestStageList where firstHalfNpcDeckId less than or equals to UPDATED_FIRST_HALF_NPC_DECK_ID
        defaultMWeeklyQuestStageShouldBeFound("firstHalfNpcDeckId.lessThan=" + UPDATED_FIRST_HALF_NPC_DECK_ID);
    }


    @Test
    @Transactional
    public void getAllMWeeklyQuestStagesByFirstHalfEnvironmentIdIsEqualToSomething() throws Exception {
        // Initialize the database
        mWeeklyQuestStageRepository.saveAndFlush(mWeeklyQuestStage);

        // Get all the mWeeklyQuestStageList where firstHalfEnvironmentId equals to DEFAULT_FIRST_HALF_ENVIRONMENT_ID
        defaultMWeeklyQuestStageShouldBeFound("firstHalfEnvironmentId.equals=" + DEFAULT_FIRST_HALF_ENVIRONMENT_ID);

        // Get all the mWeeklyQuestStageList where firstHalfEnvironmentId equals to UPDATED_FIRST_HALF_ENVIRONMENT_ID
        defaultMWeeklyQuestStageShouldNotBeFound("firstHalfEnvironmentId.equals=" + UPDATED_FIRST_HALF_ENVIRONMENT_ID);
    }

    @Test
    @Transactional
    public void getAllMWeeklyQuestStagesByFirstHalfEnvironmentIdIsInShouldWork() throws Exception {
        // Initialize the database
        mWeeklyQuestStageRepository.saveAndFlush(mWeeklyQuestStage);

        // Get all the mWeeklyQuestStageList where firstHalfEnvironmentId in DEFAULT_FIRST_HALF_ENVIRONMENT_ID or UPDATED_FIRST_HALF_ENVIRONMENT_ID
        defaultMWeeklyQuestStageShouldBeFound("firstHalfEnvironmentId.in=" + DEFAULT_FIRST_HALF_ENVIRONMENT_ID + "," + UPDATED_FIRST_HALF_ENVIRONMENT_ID);

        // Get all the mWeeklyQuestStageList where firstHalfEnvironmentId equals to UPDATED_FIRST_HALF_ENVIRONMENT_ID
        defaultMWeeklyQuestStageShouldNotBeFound("firstHalfEnvironmentId.in=" + UPDATED_FIRST_HALF_ENVIRONMENT_ID);
    }

    @Test
    @Transactional
    public void getAllMWeeklyQuestStagesByFirstHalfEnvironmentIdIsNullOrNotNull() throws Exception {
        // Initialize the database
        mWeeklyQuestStageRepository.saveAndFlush(mWeeklyQuestStage);

        // Get all the mWeeklyQuestStageList where firstHalfEnvironmentId is not null
        defaultMWeeklyQuestStageShouldBeFound("firstHalfEnvironmentId.specified=true");

        // Get all the mWeeklyQuestStageList where firstHalfEnvironmentId is null
        defaultMWeeklyQuestStageShouldNotBeFound("firstHalfEnvironmentId.specified=false");
    }

    @Test
    @Transactional
    public void getAllMWeeklyQuestStagesByFirstHalfEnvironmentIdIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mWeeklyQuestStageRepository.saveAndFlush(mWeeklyQuestStage);

        // Get all the mWeeklyQuestStageList where firstHalfEnvironmentId greater than or equals to DEFAULT_FIRST_HALF_ENVIRONMENT_ID
        defaultMWeeklyQuestStageShouldBeFound("firstHalfEnvironmentId.greaterOrEqualThan=" + DEFAULT_FIRST_HALF_ENVIRONMENT_ID);

        // Get all the mWeeklyQuestStageList where firstHalfEnvironmentId greater than or equals to UPDATED_FIRST_HALF_ENVIRONMENT_ID
        defaultMWeeklyQuestStageShouldNotBeFound("firstHalfEnvironmentId.greaterOrEqualThan=" + UPDATED_FIRST_HALF_ENVIRONMENT_ID);
    }

    @Test
    @Transactional
    public void getAllMWeeklyQuestStagesByFirstHalfEnvironmentIdIsLessThanSomething() throws Exception {
        // Initialize the database
        mWeeklyQuestStageRepository.saveAndFlush(mWeeklyQuestStage);

        // Get all the mWeeklyQuestStageList where firstHalfEnvironmentId less than or equals to DEFAULT_FIRST_HALF_ENVIRONMENT_ID
        defaultMWeeklyQuestStageShouldNotBeFound("firstHalfEnvironmentId.lessThan=" + DEFAULT_FIRST_HALF_ENVIRONMENT_ID);

        // Get all the mWeeklyQuestStageList where firstHalfEnvironmentId less than or equals to UPDATED_FIRST_HALF_ENVIRONMENT_ID
        defaultMWeeklyQuestStageShouldBeFound("firstHalfEnvironmentId.lessThan=" + UPDATED_FIRST_HALF_ENVIRONMENT_ID);
    }


    @Test
    @Transactional
    public void getAllMWeeklyQuestStagesBySecondHalfNpcDeckIdIsEqualToSomething() throws Exception {
        // Initialize the database
        mWeeklyQuestStageRepository.saveAndFlush(mWeeklyQuestStage);

        // Get all the mWeeklyQuestStageList where secondHalfNpcDeckId equals to DEFAULT_SECOND_HALF_NPC_DECK_ID
        defaultMWeeklyQuestStageShouldBeFound("secondHalfNpcDeckId.equals=" + DEFAULT_SECOND_HALF_NPC_DECK_ID);

        // Get all the mWeeklyQuestStageList where secondHalfNpcDeckId equals to UPDATED_SECOND_HALF_NPC_DECK_ID
        defaultMWeeklyQuestStageShouldNotBeFound("secondHalfNpcDeckId.equals=" + UPDATED_SECOND_HALF_NPC_DECK_ID);
    }

    @Test
    @Transactional
    public void getAllMWeeklyQuestStagesBySecondHalfNpcDeckIdIsInShouldWork() throws Exception {
        // Initialize the database
        mWeeklyQuestStageRepository.saveAndFlush(mWeeklyQuestStage);

        // Get all the mWeeklyQuestStageList where secondHalfNpcDeckId in DEFAULT_SECOND_HALF_NPC_DECK_ID or UPDATED_SECOND_HALF_NPC_DECK_ID
        defaultMWeeklyQuestStageShouldBeFound("secondHalfNpcDeckId.in=" + DEFAULT_SECOND_HALF_NPC_DECK_ID + "," + UPDATED_SECOND_HALF_NPC_DECK_ID);

        // Get all the mWeeklyQuestStageList where secondHalfNpcDeckId equals to UPDATED_SECOND_HALF_NPC_DECK_ID
        defaultMWeeklyQuestStageShouldNotBeFound("secondHalfNpcDeckId.in=" + UPDATED_SECOND_HALF_NPC_DECK_ID);
    }

    @Test
    @Transactional
    public void getAllMWeeklyQuestStagesBySecondHalfNpcDeckIdIsNullOrNotNull() throws Exception {
        // Initialize the database
        mWeeklyQuestStageRepository.saveAndFlush(mWeeklyQuestStage);

        // Get all the mWeeklyQuestStageList where secondHalfNpcDeckId is not null
        defaultMWeeklyQuestStageShouldBeFound("secondHalfNpcDeckId.specified=true");

        // Get all the mWeeklyQuestStageList where secondHalfNpcDeckId is null
        defaultMWeeklyQuestStageShouldNotBeFound("secondHalfNpcDeckId.specified=false");
    }

    @Test
    @Transactional
    public void getAllMWeeklyQuestStagesBySecondHalfNpcDeckIdIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mWeeklyQuestStageRepository.saveAndFlush(mWeeklyQuestStage);

        // Get all the mWeeklyQuestStageList where secondHalfNpcDeckId greater than or equals to DEFAULT_SECOND_HALF_NPC_DECK_ID
        defaultMWeeklyQuestStageShouldBeFound("secondHalfNpcDeckId.greaterOrEqualThan=" + DEFAULT_SECOND_HALF_NPC_DECK_ID);

        // Get all the mWeeklyQuestStageList where secondHalfNpcDeckId greater than or equals to UPDATED_SECOND_HALF_NPC_DECK_ID
        defaultMWeeklyQuestStageShouldNotBeFound("secondHalfNpcDeckId.greaterOrEqualThan=" + UPDATED_SECOND_HALF_NPC_DECK_ID);
    }

    @Test
    @Transactional
    public void getAllMWeeklyQuestStagesBySecondHalfNpcDeckIdIsLessThanSomething() throws Exception {
        // Initialize the database
        mWeeklyQuestStageRepository.saveAndFlush(mWeeklyQuestStage);

        // Get all the mWeeklyQuestStageList where secondHalfNpcDeckId less than or equals to DEFAULT_SECOND_HALF_NPC_DECK_ID
        defaultMWeeklyQuestStageShouldNotBeFound("secondHalfNpcDeckId.lessThan=" + DEFAULT_SECOND_HALF_NPC_DECK_ID);

        // Get all the mWeeklyQuestStageList where secondHalfNpcDeckId less than or equals to UPDATED_SECOND_HALF_NPC_DECK_ID
        defaultMWeeklyQuestStageShouldBeFound("secondHalfNpcDeckId.lessThan=" + UPDATED_SECOND_HALF_NPC_DECK_ID);
    }


    @Test
    @Transactional
    public void getAllMWeeklyQuestStagesBySecondHalfEnvironmentIdIsEqualToSomething() throws Exception {
        // Initialize the database
        mWeeklyQuestStageRepository.saveAndFlush(mWeeklyQuestStage);

        // Get all the mWeeklyQuestStageList where secondHalfEnvironmentId equals to DEFAULT_SECOND_HALF_ENVIRONMENT_ID
        defaultMWeeklyQuestStageShouldBeFound("secondHalfEnvironmentId.equals=" + DEFAULT_SECOND_HALF_ENVIRONMENT_ID);

        // Get all the mWeeklyQuestStageList where secondHalfEnvironmentId equals to UPDATED_SECOND_HALF_ENVIRONMENT_ID
        defaultMWeeklyQuestStageShouldNotBeFound("secondHalfEnvironmentId.equals=" + UPDATED_SECOND_HALF_ENVIRONMENT_ID);
    }

    @Test
    @Transactional
    public void getAllMWeeklyQuestStagesBySecondHalfEnvironmentIdIsInShouldWork() throws Exception {
        // Initialize the database
        mWeeklyQuestStageRepository.saveAndFlush(mWeeklyQuestStage);

        // Get all the mWeeklyQuestStageList where secondHalfEnvironmentId in DEFAULT_SECOND_HALF_ENVIRONMENT_ID or UPDATED_SECOND_HALF_ENVIRONMENT_ID
        defaultMWeeklyQuestStageShouldBeFound("secondHalfEnvironmentId.in=" + DEFAULT_SECOND_HALF_ENVIRONMENT_ID + "," + UPDATED_SECOND_HALF_ENVIRONMENT_ID);

        // Get all the mWeeklyQuestStageList where secondHalfEnvironmentId equals to UPDATED_SECOND_HALF_ENVIRONMENT_ID
        defaultMWeeklyQuestStageShouldNotBeFound("secondHalfEnvironmentId.in=" + UPDATED_SECOND_HALF_ENVIRONMENT_ID);
    }

    @Test
    @Transactional
    public void getAllMWeeklyQuestStagesBySecondHalfEnvironmentIdIsNullOrNotNull() throws Exception {
        // Initialize the database
        mWeeklyQuestStageRepository.saveAndFlush(mWeeklyQuestStage);

        // Get all the mWeeklyQuestStageList where secondHalfEnvironmentId is not null
        defaultMWeeklyQuestStageShouldBeFound("secondHalfEnvironmentId.specified=true");

        // Get all the mWeeklyQuestStageList where secondHalfEnvironmentId is null
        defaultMWeeklyQuestStageShouldNotBeFound("secondHalfEnvironmentId.specified=false");
    }

    @Test
    @Transactional
    public void getAllMWeeklyQuestStagesBySecondHalfEnvironmentIdIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mWeeklyQuestStageRepository.saveAndFlush(mWeeklyQuestStage);

        // Get all the mWeeklyQuestStageList where secondHalfEnvironmentId greater than or equals to DEFAULT_SECOND_HALF_ENVIRONMENT_ID
        defaultMWeeklyQuestStageShouldBeFound("secondHalfEnvironmentId.greaterOrEqualThan=" + DEFAULT_SECOND_HALF_ENVIRONMENT_ID);

        // Get all the mWeeklyQuestStageList where secondHalfEnvironmentId greater than or equals to UPDATED_SECOND_HALF_ENVIRONMENT_ID
        defaultMWeeklyQuestStageShouldNotBeFound("secondHalfEnvironmentId.greaterOrEqualThan=" + UPDATED_SECOND_HALF_ENVIRONMENT_ID);
    }

    @Test
    @Transactional
    public void getAllMWeeklyQuestStagesBySecondHalfEnvironmentIdIsLessThanSomething() throws Exception {
        // Initialize the database
        mWeeklyQuestStageRepository.saveAndFlush(mWeeklyQuestStage);

        // Get all the mWeeklyQuestStageList where secondHalfEnvironmentId less than or equals to DEFAULT_SECOND_HALF_ENVIRONMENT_ID
        defaultMWeeklyQuestStageShouldNotBeFound("secondHalfEnvironmentId.lessThan=" + DEFAULT_SECOND_HALF_ENVIRONMENT_ID);

        // Get all the mWeeklyQuestStageList where secondHalfEnvironmentId less than or equals to UPDATED_SECOND_HALF_ENVIRONMENT_ID
        defaultMWeeklyQuestStageShouldBeFound("secondHalfEnvironmentId.lessThan=" + UPDATED_SECOND_HALF_ENVIRONMENT_ID);
    }


    @Test
    @Transactional
    public void getAllMWeeklyQuestStagesByExtraFirstHalfNpcDeckIdIsEqualToSomething() throws Exception {
        // Initialize the database
        mWeeklyQuestStageRepository.saveAndFlush(mWeeklyQuestStage);

        // Get all the mWeeklyQuestStageList where extraFirstHalfNpcDeckId equals to DEFAULT_EXTRA_FIRST_HALF_NPC_DECK_ID
        defaultMWeeklyQuestStageShouldBeFound("extraFirstHalfNpcDeckId.equals=" + DEFAULT_EXTRA_FIRST_HALF_NPC_DECK_ID);

        // Get all the mWeeklyQuestStageList where extraFirstHalfNpcDeckId equals to UPDATED_EXTRA_FIRST_HALF_NPC_DECK_ID
        defaultMWeeklyQuestStageShouldNotBeFound("extraFirstHalfNpcDeckId.equals=" + UPDATED_EXTRA_FIRST_HALF_NPC_DECK_ID);
    }

    @Test
    @Transactional
    public void getAllMWeeklyQuestStagesByExtraFirstHalfNpcDeckIdIsInShouldWork() throws Exception {
        // Initialize the database
        mWeeklyQuestStageRepository.saveAndFlush(mWeeklyQuestStage);

        // Get all the mWeeklyQuestStageList where extraFirstHalfNpcDeckId in DEFAULT_EXTRA_FIRST_HALF_NPC_DECK_ID or UPDATED_EXTRA_FIRST_HALF_NPC_DECK_ID
        defaultMWeeklyQuestStageShouldBeFound("extraFirstHalfNpcDeckId.in=" + DEFAULT_EXTRA_FIRST_HALF_NPC_DECK_ID + "," + UPDATED_EXTRA_FIRST_HALF_NPC_DECK_ID);

        // Get all the mWeeklyQuestStageList where extraFirstHalfNpcDeckId equals to UPDATED_EXTRA_FIRST_HALF_NPC_DECK_ID
        defaultMWeeklyQuestStageShouldNotBeFound("extraFirstHalfNpcDeckId.in=" + UPDATED_EXTRA_FIRST_HALF_NPC_DECK_ID);
    }

    @Test
    @Transactional
    public void getAllMWeeklyQuestStagesByExtraFirstHalfNpcDeckIdIsNullOrNotNull() throws Exception {
        // Initialize the database
        mWeeklyQuestStageRepository.saveAndFlush(mWeeklyQuestStage);

        // Get all the mWeeklyQuestStageList where extraFirstHalfNpcDeckId is not null
        defaultMWeeklyQuestStageShouldBeFound("extraFirstHalfNpcDeckId.specified=true");

        // Get all the mWeeklyQuestStageList where extraFirstHalfNpcDeckId is null
        defaultMWeeklyQuestStageShouldNotBeFound("extraFirstHalfNpcDeckId.specified=false");
    }

    @Test
    @Transactional
    public void getAllMWeeklyQuestStagesByExtraFirstHalfNpcDeckIdIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mWeeklyQuestStageRepository.saveAndFlush(mWeeklyQuestStage);

        // Get all the mWeeklyQuestStageList where extraFirstHalfNpcDeckId greater than or equals to DEFAULT_EXTRA_FIRST_HALF_NPC_DECK_ID
        defaultMWeeklyQuestStageShouldBeFound("extraFirstHalfNpcDeckId.greaterOrEqualThan=" + DEFAULT_EXTRA_FIRST_HALF_NPC_DECK_ID);

        // Get all the mWeeklyQuestStageList where extraFirstHalfNpcDeckId greater than or equals to UPDATED_EXTRA_FIRST_HALF_NPC_DECK_ID
        defaultMWeeklyQuestStageShouldNotBeFound("extraFirstHalfNpcDeckId.greaterOrEqualThan=" + UPDATED_EXTRA_FIRST_HALF_NPC_DECK_ID);
    }

    @Test
    @Transactional
    public void getAllMWeeklyQuestStagesByExtraFirstHalfNpcDeckIdIsLessThanSomething() throws Exception {
        // Initialize the database
        mWeeklyQuestStageRepository.saveAndFlush(mWeeklyQuestStage);

        // Get all the mWeeklyQuestStageList where extraFirstHalfNpcDeckId less than or equals to DEFAULT_EXTRA_FIRST_HALF_NPC_DECK_ID
        defaultMWeeklyQuestStageShouldNotBeFound("extraFirstHalfNpcDeckId.lessThan=" + DEFAULT_EXTRA_FIRST_HALF_NPC_DECK_ID);

        // Get all the mWeeklyQuestStageList where extraFirstHalfNpcDeckId less than or equals to UPDATED_EXTRA_FIRST_HALF_NPC_DECK_ID
        defaultMWeeklyQuestStageShouldBeFound("extraFirstHalfNpcDeckId.lessThan=" + UPDATED_EXTRA_FIRST_HALF_NPC_DECK_ID);
    }


    @Test
    @Transactional
    public void getAllMWeeklyQuestStagesByExtraSecondHalfNpcDeckIdIsEqualToSomething() throws Exception {
        // Initialize the database
        mWeeklyQuestStageRepository.saveAndFlush(mWeeklyQuestStage);

        // Get all the mWeeklyQuestStageList where extraSecondHalfNpcDeckId equals to DEFAULT_EXTRA_SECOND_HALF_NPC_DECK_ID
        defaultMWeeklyQuestStageShouldBeFound("extraSecondHalfNpcDeckId.equals=" + DEFAULT_EXTRA_SECOND_HALF_NPC_DECK_ID);

        // Get all the mWeeklyQuestStageList where extraSecondHalfNpcDeckId equals to UPDATED_EXTRA_SECOND_HALF_NPC_DECK_ID
        defaultMWeeklyQuestStageShouldNotBeFound("extraSecondHalfNpcDeckId.equals=" + UPDATED_EXTRA_SECOND_HALF_NPC_DECK_ID);
    }

    @Test
    @Transactional
    public void getAllMWeeklyQuestStagesByExtraSecondHalfNpcDeckIdIsInShouldWork() throws Exception {
        // Initialize the database
        mWeeklyQuestStageRepository.saveAndFlush(mWeeklyQuestStage);

        // Get all the mWeeklyQuestStageList where extraSecondHalfNpcDeckId in DEFAULT_EXTRA_SECOND_HALF_NPC_DECK_ID or UPDATED_EXTRA_SECOND_HALF_NPC_DECK_ID
        defaultMWeeklyQuestStageShouldBeFound("extraSecondHalfNpcDeckId.in=" + DEFAULT_EXTRA_SECOND_HALF_NPC_DECK_ID + "," + UPDATED_EXTRA_SECOND_HALF_NPC_DECK_ID);

        // Get all the mWeeklyQuestStageList where extraSecondHalfNpcDeckId equals to UPDATED_EXTRA_SECOND_HALF_NPC_DECK_ID
        defaultMWeeklyQuestStageShouldNotBeFound("extraSecondHalfNpcDeckId.in=" + UPDATED_EXTRA_SECOND_HALF_NPC_DECK_ID);
    }

    @Test
    @Transactional
    public void getAllMWeeklyQuestStagesByExtraSecondHalfNpcDeckIdIsNullOrNotNull() throws Exception {
        // Initialize the database
        mWeeklyQuestStageRepository.saveAndFlush(mWeeklyQuestStage);

        // Get all the mWeeklyQuestStageList where extraSecondHalfNpcDeckId is not null
        defaultMWeeklyQuestStageShouldBeFound("extraSecondHalfNpcDeckId.specified=true");

        // Get all the mWeeklyQuestStageList where extraSecondHalfNpcDeckId is null
        defaultMWeeklyQuestStageShouldNotBeFound("extraSecondHalfNpcDeckId.specified=false");
    }

    @Test
    @Transactional
    public void getAllMWeeklyQuestStagesByExtraSecondHalfNpcDeckIdIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mWeeklyQuestStageRepository.saveAndFlush(mWeeklyQuestStage);

        // Get all the mWeeklyQuestStageList where extraSecondHalfNpcDeckId greater than or equals to DEFAULT_EXTRA_SECOND_HALF_NPC_DECK_ID
        defaultMWeeklyQuestStageShouldBeFound("extraSecondHalfNpcDeckId.greaterOrEqualThan=" + DEFAULT_EXTRA_SECOND_HALF_NPC_DECK_ID);

        // Get all the mWeeklyQuestStageList where extraSecondHalfNpcDeckId greater than or equals to UPDATED_EXTRA_SECOND_HALF_NPC_DECK_ID
        defaultMWeeklyQuestStageShouldNotBeFound("extraSecondHalfNpcDeckId.greaterOrEqualThan=" + UPDATED_EXTRA_SECOND_HALF_NPC_DECK_ID);
    }

    @Test
    @Transactional
    public void getAllMWeeklyQuestStagesByExtraSecondHalfNpcDeckIdIsLessThanSomething() throws Exception {
        // Initialize the database
        mWeeklyQuestStageRepository.saveAndFlush(mWeeklyQuestStage);

        // Get all the mWeeklyQuestStageList where extraSecondHalfNpcDeckId less than or equals to DEFAULT_EXTRA_SECOND_HALF_NPC_DECK_ID
        defaultMWeeklyQuestStageShouldNotBeFound("extraSecondHalfNpcDeckId.lessThan=" + DEFAULT_EXTRA_SECOND_HALF_NPC_DECK_ID);

        // Get all the mWeeklyQuestStageList where extraSecondHalfNpcDeckId less than or equals to UPDATED_EXTRA_SECOND_HALF_NPC_DECK_ID
        defaultMWeeklyQuestStageShouldBeFound("extraSecondHalfNpcDeckId.lessThan=" + UPDATED_EXTRA_SECOND_HALF_NPC_DECK_ID);
    }


    @Test
    @Transactional
    public void getAllMWeeklyQuestStagesByConsumeApIsEqualToSomething() throws Exception {
        // Initialize the database
        mWeeklyQuestStageRepository.saveAndFlush(mWeeklyQuestStage);

        // Get all the mWeeklyQuestStageList where consumeAp equals to DEFAULT_CONSUME_AP
        defaultMWeeklyQuestStageShouldBeFound("consumeAp.equals=" + DEFAULT_CONSUME_AP);

        // Get all the mWeeklyQuestStageList where consumeAp equals to UPDATED_CONSUME_AP
        defaultMWeeklyQuestStageShouldNotBeFound("consumeAp.equals=" + UPDATED_CONSUME_AP);
    }

    @Test
    @Transactional
    public void getAllMWeeklyQuestStagesByConsumeApIsInShouldWork() throws Exception {
        // Initialize the database
        mWeeklyQuestStageRepository.saveAndFlush(mWeeklyQuestStage);

        // Get all the mWeeklyQuestStageList where consumeAp in DEFAULT_CONSUME_AP or UPDATED_CONSUME_AP
        defaultMWeeklyQuestStageShouldBeFound("consumeAp.in=" + DEFAULT_CONSUME_AP + "," + UPDATED_CONSUME_AP);

        // Get all the mWeeklyQuestStageList where consumeAp equals to UPDATED_CONSUME_AP
        defaultMWeeklyQuestStageShouldNotBeFound("consumeAp.in=" + UPDATED_CONSUME_AP);
    }

    @Test
    @Transactional
    public void getAllMWeeklyQuestStagesByConsumeApIsNullOrNotNull() throws Exception {
        // Initialize the database
        mWeeklyQuestStageRepository.saveAndFlush(mWeeklyQuestStage);

        // Get all the mWeeklyQuestStageList where consumeAp is not null
        defaultMWeeklyQuestStageShouldBeFound("consumeAp.specified=true");

        // Get all the mWeeklyQuestStageList where consumeAp is null
        defaultMWeeklyQuestStageShouldNotBeFound("consumeAp.specified=false");
    }

    @Test
    @Transactional
    public void getAllMWeeklyQuestStagesByConsumeApIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mWeeklyQuestStageRepository.saveAndFlush(mWeeklyQuestStage);

        // Get all the mWeeklyQuestStageList where consumeAp greater than or equals to DEFAULT_CONSUME_AP
        defaultMWeeklyQuestStageShouldBeFound("consumeAp.greaterOrEqualThan=" + DEFAULT_CONSUME_AP);

        // Get all the mWeeklyQuestStageList where consumeAp greater than or equals to UPDATED_CONSUME_AP
        defaultMWeeklyQuestStageShouldNotBeFound("consumeAp.greaterOrEqualThan=" + UPDATED_CONSUME_AP);
    }

    @Test
    @Transactional
    public void getAllMWeeklyQuestStagesByConsumeApIsLessThanSomething() throws Exception {
        // Initialize the database
        mWeeklyQuestStageRepository.saveAndFlush(mWeeklyQuestStage);

        // Get all the mWeeklyQuestStageList where consumeAp less than or equals to DEFAULT_CONSUME_AP
        defaultMWeeklyQuestStageShouldNotBeFound("consumeAp.lessThan=" + DEFAULT_CONSUME_AP);

        // Get all the mWeeklyQuestStageList where consumeAp less than or equals to UPDATED_CONSUME_AP
        defaultMWeeklyQuestStageShouldBeFound("consumeAp.lessThan=" + UPDATED_CONSUME_AP);
    }


    @Test
    @Transactional
    public void getAllMWeeklyQuestStagesByKickOffTypeIsEqualToSomething() throws Exception {
        // Initialize the database
        mWeeklyQuestStageRepository.saveAndFlush(mWeeklyQuestStage);

        // Get all the mWeeklyQuestStageList where kickOffType equals to DEFAULT_KICK_OFF_TYPE
        defaultMWeeklyQuestStageShouldBeFound("kickOffType.equals=" + DEFAULT_KICK_OFF_TYPE);

        // Get all the mWeeklyQuestStageList where kickOffType equals to UPDATED_KICK_OFF_TYPE
        defaultMWeeklyQuestStageShouldNotBeFound("kickOffType.equals=" + UPDATED_KICK_OFF_TYPE);
    }

    @Test
    @Transactional
    public void getAllMWeeklyQuestStagesByKickOffTypeIsInShouldWork() throws Exception {
        // Initialize the database
        mWeeklyQuestStageRepository.saveAndFlush(mWeeklyQuestStage);

        // Get all the mWeeklyQuestStageList where kickOffType in DEFAULT_KICK_OFF_TYPE or UPDATED_KICK_OFF_TYPE
        defaultMWeeklyQuestStageShouldBeFound("kickOffType.in=" + DEFAULT_KICK_OFF_TYPE + "," + UPDATED_KICK_OFF_TYPE);

        // Get all the mWeeklyQuestStageList where kickOffType equals to UPDATED_KICK_OFF_TYPE
        defaultMWeeklyQuestStageShouldNotBeFound("kickOffType.in=" + UPDATED_KICK_OFF_TYPE);
    }

    @Test
    @Transactional
    public void getAllMWeeklyQuestStagesByKickOffTypeIsNullOrNotNull() throws Exception {
        // Initialize the database
        mWeeklyQuestStageRepository.saveAndFlush(mWeeklyQuestStage);

        // Get all the mWeeklyQuestStageList where kickOffType is not null
        defaultMWeeklyQuestStageShouldBeFound("kickOffType.specified=true");

        // Get all the mWeeklyQuestStageList where kickOffType is null
        defaultMWeeklyQuestStageShouldNotBeFound("kickOffType.specified=false");
    }

    @Test
    @Transactional
    public void getAllMWeeklyQuestStagesByKickOffTypeIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mWeeklyQuestStageRepository.saveAndFlush(mWeeklyQuestStage);

        // Get all the mWeeklyQuestStageList where kickOffType greater than or equals to DEFAULT_KICK_OFF_TYPE
        defaultMWeeklyQuestStageShouldBeFound("kickOffType.greaterOrEqualThan=" + DEFAULT_KICK_OFF_TYPE);

        // Get all the mWeeklyQuestStageList where kickOffType greater than or equals to UPDATED_KICK_OFF_TYPE
        defaultMWeeklyQuestStageShouldNotBeFound("kickOffType.greaterOrEqualThan=" + UPDATED_KICK_OFF_TYPE);
    }

    @Test
    @Transactional
    public void getAllMWeeklyQuestStagesByKickOffTypeIsLessThanSomething() throws Exception {
        // Initialize the database
        mWeeklyQuestStageRepository.saveAndFlush(mWeeklyQuestStage);

        // Get all the mWeeklyQuestStageList where kickOffType less than or equals to DEFAULT_KICK_OFF_TYPE
        defaultMWeeklyQuestStageShouldNotBeFound("kickOffType.lessThan=" + DEFAULT_KICK_OFF_TYPE);

        // Get all the mWeeklyQuestStageList where kickOffType less than or equals to UPDATED_KICK_OFF_TYPE
        defaultMWeeklyQuestStageShouldBeFound("kickOffType.lessThan=" + UPDATED_KICK_OFF_TYPE);
    }


    @Test
    @Transactional
    public void getAllMWeeklyQuestStagesByMatchMinuteIsEqualToSomething() throws Exception {
        // Initialize the database
        mWeeklyQuestStageRepository.saveAndFlush(mWeeklyQuestStage);

        // Get all the mWeeklyQuestStageList where matchMinute equals to DEFAULT_MATCH_MINUTE
        defaultMWeeklyQuestStageShouldBeFound("matchMinute.equals=" + DEFAULT_MATCH_MINUTE);

        // Get all the mWeeklyQuestStageList where matchMinute equals to UPDATED_MATCH_MINUTE
        defaultMWeeklyQuestStageShouldNotBeFound("matchMinute.equals=" + UPDATED_MATCH_MINUTE);
    }

    @Test
    @Transactional
    public void getAllMWeeklyQuestStagesByMatchMinuteIsInShouldWork() throws Exception {
        // Initialize the database
        mWeeklyQuestStageRepository.saveAndFlush(mWeeklyQuestStage);

        // Get all the mWeeklyQuestStageList where matchMinute in DEFAULT_MATCH_MINUTE or UPDATED_MATCH_MINUTE
        defaultMWeeklyQuestStageShouldBeFound("matchMinute.in=" + DEFAULT_MATCH_MINUTE + "," + UPDATED_MATCH_MINUTE);

        // Get all the mWeeklyQuestStageList where matchMinute equals to UPDATED_MATCH_MINUTE
        defaultMWeeklyQuestStageShouldNotBeFound("matchMinute.in=" + UPDATED_MATCH_MINUTE);
    }

    @Test
    @Transactional
    public void getAllMWeeklyQuestStagesByMatchMinuteIsNullOrNotNull() throws Exception {
        // Initialize the database
        mWeeklyQuestStageRepository.saveAndFlush(mWeeklyQuestStage);

        // Get all the mWeeklyQuestStageList where matchMinute is not null
        defaultMWeeklyQuestStageShouldBeFound("matchMinute.specified=true");

        // Get all the mWeeklyQuestStageList where matchMinute is null
        defaultMWeeklyQuestStageShouldNotBeFound("matchMinute.specified=false");
    }

    @Test
    @Transactional
    public void getAllMWeeklyQuestStagesByMatchMinuteIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mWeeklyQuestStageRepository.saveAndFlush(mWeeklyQuestStage);

        // Get all the mWeeklyQuestStageList where matchMinute greater than or equals to DEFAULT_MATCH_MINUTE
        defaultMWeeklyQuestStageShouldBeFound("matchMinute.greaterOrEqualThan=" + DEFAULT_MATCH_MINUTE);

        // Get all the mWeeklyQuestStageList where matchMinute greater than or equals to UPDATED_MATCH_MINUTE
        defaultMWeeklyQuestStageShouldNotBeFound("matchMinute.greaterOrEqualThan=" + UPDATED_MATCH_MINUTE);
    }

    @Test
    @Transactional
    public void getAllMWeeklyQuestStagesByMatchMinuteIsLessThanSomething() throws Exception {
        // Initialize the database
        mWeeklyQuestStageRepository.saveAndFlush(mWeeklyQuestStage);

        // Get all the mWeeklyQuestStageList where matchMinute less than or equals to DEFAULT_MATCH_MINUTE
        defaultMWeeklyQuestStageShouldNotBeFound("matchMinute.lessThan=" + DEFAULT_MATCH_MINUTE);

        // Get all the mWeeklyQuestStageList where matchMinute less than or equals to UPDATED_MATCH_MINUTE
        defaultMWeeklyQuestStageShouldBeFound("matchMinute.lessThan=" + UPDATED_MATCH_MINUTE);
    }


    @Test
    @Transactional
    public void getAllMWeeklyQuestStagesByEnableExtraIsEqualToSomething() throws Exception {
        // Initialize the database
        mWeeklyQuestStageRepository.saveAndFlush(mWeeklyQuestStage);

        // Get all the mWeeklyQuestStageList where enableExtra equals to DEFAULT_ENABLE_EXTRA
        defaultMWeeklyQuestStageShouldBeFound("enableExtra.equals=" + DEFAULT_ENABLE_EXTRA);

        // Get all the mWeeklyQuestStageList where enableExtra equals to UPDATED_ENABLE_EXTRA
        defaultMWeeklyQuestStageShouldNotBeFound("enableExtra.equals=" + UPDATED_ENABLE_EXTRA);
    }

    @Test
    @Transactional
    public void getAllMWeeklyQuestStagesByEnableExtraIsInShouldWork() throws Exception {
        // Initialize the database
        mWeeklyQuestStageRepository.saveAndFlush(mWeeklyQuestStage);

        // Get all the mWeeklyQuestStageList where enableExtra in DEFAULT_ENABLE_EXTRA or UPDATED_ENABLE_EXTRA
        defaultMWeeklyQuestStageShouldBeFound("enableExtra.in=" + DEFAULT_ENABLE_EXTRA + "," + UPDATED_ENABLE_EXTRA);

        // Get all the mWeeklyQuestStageList where enableExtra equals to UPDATED_ENABLE_EXTRA
        defaultMWeeklyQuestStageShouldNotBeFound("enableExtra.in=" + UPDATED_ENABLE_EXTRA);
    }

    @Test
    @Transactional
    public void getAllMWeeklyQuestStagesByEnableExtraIsNullOrNotNull() throws Exception {
        // Initialize the database
        mWeeklyQuestStageRepository.saveAndFlush(mWeeklyQuestStage);

        // Get all the mWeeklyQuestStageList where enableExtra is not null
        defaultMWeeklyQuestStageShouldBeFound("enableExtra.specified=true");

        // Get all the mWeeklyQuestStageList where enableExtra is null
        defaultMWeeklyQuestStageShouldNotBeFound("enableExtra.specified=false");
    }

    @Test
    @Transactional
    public void getAllMWeeklyQuestStagesByEnableExtraIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mWeeklyQuestStageRepository.saveAndFlush(mWeeklyQuestStage);

        // Get all the mWeeklyQuestStageList where enableExtra greater than or equals to DEFAULT_ENABLE_EXTRA
        defaultMWeeklyQuestStageShouldBeFound("enableExtra.greaterOrEqualThan=" + DEFAULT_ENABLE_EXTRA);

        // Get all the mWeeklyQuestStageList where enableExtra greater than or equals to UPDATED_ENABLE_EXTRA
        defaultMWeeklyQuestStageShouldNotBeFound("enableExtra.greaterOrEqualThan=" + UPDATED_ENABLE_EXTRA);
    }

    @Test
    @Transactional
    public void getAllMWeeklyQuestStagesByEnableExtraIsLessThanSomething() throws Exception {
        // Initialize the database
        mWeeklyQuestStageRepository.saveAndFlush(mWeeklyQuestStage);

        // Get all the mWeeklyQuestStageList where enableExtra less than or equals to DEFAULT_ENABLE_EXTRA
        defaultMWeeklyQuestStageShouldNotBeFound("enableExtra.lessThan=" + DEFAULT_ENABLE_EXTRA);

        // Get all the mWeeklyQuestStageList where enableExtra less than or equals to UPDATED_ENABLE_EXTRA
        defaultMWeeklyQuestStageShouldBeFound("enableExtra.lessThan=" + UPDATED_ENABLE_EXTRA);
    }


    @Test
    @Transactional
    public void getAllMWeeklyQuestStagesByEnablePkIsEqualToSomething() throws Exception {
        // Initialize the database
        mWeeklyQuestStageRepository.saveAndFlush(mWeeklyQuestStage);

        // Get all the mWeeklyQuestStageList where enablePk equals to DEFAULT_ENABLE_PK
        defaultMWeeklyQuestStageShouldBeFound("enablePk.equals=" + DEFAULT_ENABLE_PK);

        // Get all the mWeeklyQuestStageList where enablePk equals to UPDATED_ENABLE_PK
        defaultMWeeklyQuestStageShouldNotBeFound("enablePk.equals=" + UPDATED_ENABLE_PK);
    }

    @Test
    @Transactional
    public void getAllMWeeklyQuestStagesByEnablePkIsInShouldWork() throws Exception {
        // Initialize the database
        mWeeklyQuestStageRepository.saveAndFlush(mWeeklyQuestStage);

        // Get all the mWeeklyQuestStageList where enablePk in DEFAULT_ENABLE_PK or UPDATED_ENABLE_PK
        defaultMWeeklyQuestStageShouldBeFound("enablePk.in=" + DEFAULT_ENABLE_PK + "," + UPDATED_ENABLE_PK);

        // Get all the mWeeklyQuestStageList where enablePk equals to UPDATED_ENABLE_PK
        defaultMWeeklyQuestStageShouldNotBeFound("enablePk.in=" + UPDATED_ENABLE_PK);
    }

    @Test
    @Transactional
    public void getAllMWeeklyQuestStagesByEnablePkIsNullOrNotNull() throws Exception {
        // Initialize the database
        mWeeklyQuestStageRepository.saveAndFlush(mWeeklyQuestStage);

        // Get all the mWeeklyQuestStageList where enablePk is not null
        defaultMWeeklyQuestStageShouldBeFound("enablePk.specified=true");

        // Get all the mWeeklyQuestStageList where enablePk is null
        defaultMWeeklyQuestStageShouldNotBeFound("enablePk.specified=false");
    }

    @Test
    @Transactional
    public void getAllMWeeklyQuestStagesByEnablePkIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mWeeklyQuestStageRepository.saveAndFlush(mWeeklyQuestStage);

        // Get all the mWeeklyQuestStageList where enablePk greater than or equals to DEFAULT_ENABLE_PK
        defaultMWeeklyQuestStageShouldBeFound("enablePk.greaterOrEqualThan=" + DEFAULT_ENABLE_PK);

        // Get all the mWeeklyQuestStageList where enablePk greater than or equals to UPDATED_ENABLE_PK
        defaultMWeeklyQuestStageShouldNotBeFound("enablePk.greaterOrEqualThan=" + UPDATED_ENABLE_PK);
    }

    @Test
    @Transactional
    public void getAllMWeeklyQuestStagesByEnablePkIsLessThanSomething() throws Exception {
        // Initialize the database
        mWeeklyQuestStageRepository.saveAndFlush(mWeeklyQuestStage);

        // Get all the mWeeklyQuestStageList where enablePk less than or equals to DEFAULT_ENABLE_PK
        defaultMWeeklyQuestStageShouldNotBeFound("enablePk.lessThan=" + DEFAULT_ENABLE_PK);

        // Get all the mWeeklyQuestStageList where enablePk less than or equals to UPDATED_ENABLE_PK
        defaultMWeeklyQuestStageShouldBeFound("enablePk.lessThan=" + UPDATED_ENABLE_PK);
    }


    @Test
    @Transactional
    public void getAllMWeeklyQuestStagesByAiLevelIsEqualToSomething() throws Exception {
        // Initialize the database
        mWeeklyQuestStageRepository.saveAndFlush(mWeeklyQuestStage);

        // Get all the mWeeklyQuestStageList where aiLevel equals to DEFAULT_AI_LEVEL
        defaultMWeeklyQuestStageShouldBeFound("aiLevel.equals=" + DEFAULT_AI_LEVEL);

        // Get all the mWeeklyQuestStageList where aiLevel equals to UPDATED_AI_LEVEL
        defaultMWeeklyQuestStageShouldNotBeFound("aiLevel.equals=" + UPDATED_AI_LEVEL);
    }

    @Test
    @Transactional
    public void getAllMWeeklyQuestStagesByAiLevelIsInShouldWork() throws Exception {
        // Initialize the database
        mWeeklyQuestStageRepository.saveAndFlush(mWeeklyQuestStage);

        // Get all the mWeeklyQuestStageList where aiLevel in DEFAULT_AI_LEVEL or UPDATED_AI_LEVEL
        defaultMWeeklyQuestStageShouldBeFound("aiLevel.in=" + DEFAULT_AI_LEVEL + "," + UPDATED_AI_LEVEL);

        // Get all the mWeeklyQuestStageList where aiLevel equals to UPDATED_AI_LEVEL
        defaultMWeeklyQuestStageShouldNotBeFound("aiLevel.in=" + UPDATED_AI_LEVEL);
    }

    @Test
    @Transactional
    public void getAllMWeeklyQuestStagesByAiLevelIsNullOrNotNull() throws Exception {
        // Initialize the database
        mWeeklyQuestStageRepository.saveAndFlush(mWeeklyQuestStage);

        // Get all the mWeeklyQuestStageList where aiLevel is not null
        defaultMWeeklyQuestStageShouldBeFound("aiLevel.specified=true");

        // Get all the mWeeklyQuestStageList where aiLevel is null
        defaultMWeeklyQuestStageShouldNotBeFound("aiLevel.specified=false");
    }

    @Test
    @Transactional
    public void getAllMWeeklyQuestStagesByAiLevelIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mWeeklyQuestStageRepository.saveAndFlush(mWeeklyQuestStage);

        // Get all the mWeeklyQuestStageList where aiLevel greater than or equals to DEFAULT_AI_LEVEL
        defaultMWeeklyQuestStageShouldBeFound("aiLevel.greaterOrEqualThan=" + DEFAULT_AI_LEVEL);

        // Get all the mWeeklyQuestStageList where aiLevel greater than or equals to UPDATED_AI_LEVEL
        defaultMWeeklyQuestStageShouldNotBeFound("aiLevel.greaterOrEqualThan=" + UPDATED_AI_LEVEL);
    }

    @Test
    @Transactional
    public void getAllMWeeklyQuestStagesByAiLevelIsLessThanSomething() throws Exception {
        // Initialize the database
        mWeeklyQuestStageRepository.saveAndFlush(mWeeklyQuestStage);

        // Get all the mWeeklyQuestStageList where aiLevel less than or equals to DEFAULT_AI_LEVEL
        defaultMWeeklyQuestStageShouldNotBeFound("aiLevel.lessThan=" + DEFAULT_AI_LEVEL);

        // Get all the mWeeklyQuestStageList where aiLevel less than or equals to UPDATED_AI_LEVEL
        defaultMWeeklyQuestStageShouldBeFound("aiLevel.lessThan=" + UPDATED_AI_LEVEL);
    }


    @Test
    @Transactional
    public void getAllMWeeklyQuestStagesByStartAtSecondHalfIsEqualToSomething() throws Exception {
        // Initialize the database
        mWeeklyQuestStageRepository.saveAndFlush(mWeeklyQuestStage);

        // Get all the mWeeklyQuestStageList where startAtSecondHalf equals to DEFAULT_START_AT_SECOND_HALF
        defaultMWeeklyQuestStageShouldBeFound("startAtSecondHalf.equals=" + DEFAULT_START_AT_SECOND_HALF);

        // Get all the mWeeklyQuestStageList where startAtSecondHalf equals to UPDATED_START_AT_SECOND_HALF
        defaultMWeeklyQuestStageShouldNotBeFound("startAtSecondHalf.equals=" + UPDATED_START_AT_SECOND_HALF);
    }

    @Test
    @Transactional
    public void getAllMWeeklyQuestStagesByStartAtSecondHalfIsInShouldWork() throws Exception {
        // Initialize the database
        mWeeklyQuestStageRepository.saveAndFlush(mWeeklyQuestStage);

        // Get all the mWeeklyQuestStageList where startAtSecondHalf in DEFAULT_START_AT_SECOND_HALF or UPDATED_START_AT_SECOND_HALF
        defaultMWeeklyQuestStageShouldBeFound("startAtSecondHalf.in=" + DEFAULT_START_AT_SECOND_HALF + "," + UPDATED_START_AT_SECOND_HALF);

        // Get all the mWeeklyQuestStageList where startAtSecondHalf equals to UPDATED_START_AT_SECOND_HALF
        defaultMWeeklyQuestStageShouldNotBeFound("startAtSecondHalf.in=" + UPDATED_START_AT_SECOND_HALF);
    }

    @Test
    @Transactional
    public void getAllMWeeklyQuestStagesByStartAtSecondHalfIsNullOrNotNull() throws Exception {
        // Initialize the database
        mWeeklyQuestStageRepository.saveAndFlush(mWeeklyQuestStage);

        // Get all the mWeeklyQuestStageList where startAtSecondHalf is not null
        defaultMWeeklyQuestStageShouldBeFound("startAtSecondHalf.specified=true");

        // Get all the mWeeklyQuestStageList where startAtSecondHalf is null
        defaultMWeeklyQuestStageShouldNotBeFound("startAtSecondHalf.specified=false");
    }

    @Test
    @Transactional
    public void getAllMWeeklyQuestStagesByStartAtSecondHalfIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mWeeklyQuestStageRepository.saveAndFlush(mWeeklyQuestStage);

        // Get all the mWeeklyQuestStageList where startAtSecondHalf greater than or equals to DEFAULT_START_AT_SECOND_HALF
        defaultMWeeklyQuestStageShouldBeFound("startAtSecondHalf.greaterOrEqualThan=" + DEFAULT_START_AT_SECOND_HALF);

        // Get all the mWeeklyQuestStageList where startAtSecondHalf greater than or equals to UPDATED_START_AT_SECOND_HALF
        defaultMWeeklyQuestStageShouldNotBeFound("startAtSecondHalf.greaterOrEqualThan=" + UPDATED_START_AT_SECOND_HALF);
    }

    @Test
    @Transactional
    public void getAllMWeeklyQuestStagesByStartAtSecondHalfIsLessThanSomething() throws Exception {
        // Initialize the database
        mWeeklyQuestStageRepository.saveAndFlush(mWeeklyQuestStage);

        // Get all the mWeeklyQuestStageList where startAtSecondHalf less than or equals to DEFAULT_START_AT_SECOND_HALF
        defaultMWeeklyQuestStageShouldNotBeFound("startAtSecondHalf.lessThan=" + DEFAULT_START_AT_SECOND_HALF);

        // Get all the mWeeklyQuestStageList where startAtSecondHalf less than or equals to UPDATED_START_AT_SECOND_HALF
        defaultMWeeklyQuestStageShouldBeFound("startAtSecondHalf.lessThan=" + UPDATED_START_AT_SECOND_HALF);
    }


    @Test
    @Transactional
    public void getAllMWeeklyQuestStagesByStartScoreIsEqualToSomething() throws Exception {
        // Initialize the database
        mWeeklyQuestStageRepository.saveAndFlush(mWeeklyQuestStage);

        // Get all the mWeeklyQuestStageList where startScore equals to DEFAULT_START_SCORE
        defaultMWeeklyQuestStageShouldBeFound("startScore.equals=" + DEFAULT_START_SCORE);

        // Get all the mWeeklyQuestStageList where startScore equals to UPDATED_START_SCORE
        defaultMWeeklyQuestStageShouldNotBeFound("startScore.equals=" + UPDATED_START_SCORE);
    }

    @Test
    @Transactional
    public void getAllMWeeklyQuestStagesByStartScoreIsInShouldWork() throws Exception {
        // Initialize the database
        mWeeklyQuestStageRepository.saveAndFlush(mWeeklyQuestStage);

        // Get all the mWeeklyQuestStageList where startScore in DEFAULT_START_SCORE or UPDATED_START_SCORE
        defaultMWeeklyQuestStageShouldBeFound("startScore.in=" + DEFAULT_START_SCORE + "," + UPDATED_START_SCORE);

        // Get all the mWeeklyQuestStageList where startScore equals to UPDATED_START_SCORE
        defaultMWeeklyQuestStageShouldNotBeFound("startScore.in=" + UPDATED_START_SCORE);
    }

    @Test
    @Transactional
    public void getAllMWeeklyQuestStagesByStartScoreIsNullOrNotNull() throws Exception {
        // Initialize the database
        mWeeklyQuestStageRepository.saveAndFlush(mWeeklyQuestStage);

        // Get all the mWeeklyQuestStageList where startScore is not null
        defaultMWeeklyQuestStageShouldBeFound("startScore.specified=true");

        // Get all the mWeeklyQuestStageList where startScore is null
        defaultMWeeklyQuestStageShouldNotBeFound("startScore.specified=false");
    }

    @Test
    @Transactional
    public void getAllMWeeklyQuestStagesByStartScoreIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mWeeklyQuestStageRepository.saveAndFlush(mWeeklyQuestStage);

        // Get all the mWeeklyQuestStageList where startScore greater than or equals to DEFAULT_START_SCORE
        defaultMWeeklyQuestStageShouldBeFound("startScore.greaterOrEqualThan=" + DEFAULT_START_SCORE);

        // Get all the mWeeklyQuestStageList where startScore greater than or equals to UPDATED_START_SCORE
        defaultMWeeklyQuestStageShouldNotBeFound("startScore.greaterOrEqualThan=" + UPDATED_START_SCORE);
    }

    @Test
    @Transactional
    public void getAllMWeeklyQuestStagesByStartScoreIsLessThanSomething() throws Exception {
        // Initialize the database
        mWeeklyQuestStageRepository.saveAndFlush(mWeeklyQuestStage);

        // Get all the mWeeklyQuestStageList where startScore less than or equals to DEFAULT_START_SCORE
        defaultMWeeklyQuestStageShouldNotBeFound("startScore.lessThan=" + DEFAULT_START_SCORE);

        // Get all the mWeeklyQuestStageList where startScore less than or equals to UPDATED_START_SCORE
        defaultMWeeklyQuestStageShouldBeFound("startScore.lessThan=" + UPDATED_START_SCORE);
    }


    @Test
    @Transactional
    public void getAllMWeeklyQuestStagesByStartScoreOpponentIsEqualToSomething() throws Exception {
        // Initialize the database
        mWeeklyQuestStageRepository.saveAndFlush(mWeeklyQuestStage);

        // Get all the mWeeklyQuestStageList where startScoreOpponent equals to DEFAULT_START_SCORE_OPPONENT
        defaultMWeeklyQuestStageShouldBeFound("startScoreOpponent.equals=" + DEFAULT_START_SCORE_OPPONENT);

        // Get all the mWeeklyQuestStageList where startScoreOpponent equals to UPDATED_START_SCORE_OPPONENT
        defaultMWeeklyQuestStageShouldNotBeFound("startScoreOpponent.equals=" + UPDATED_START_SCORE_OPPONENT);
    }

    @Test
    @Transactional
    public void getAllMWeeklyQuestStagesByStartScoreOpponentIsInShouldWork() throws Exception {
        // Initialize the database
        mWeeklyQuestStageRepository.saveAndFlush(mWeeklyQuestStage);

        // Get all the mWeeklyQuestStageList where startScoreOpponent in DEFAULT_START_SCORE_OPPONENT or UPDATED_START_SCORE_OPPONENT
        defaultMWeeklyQuestStageShouldBeFound("startScoreOpponent.in=" + DEFAULT_START_SCORE_OPPONENT + "," + UPDATED_START_SCORE_OPPONENT);

        // Get all the mWeeklyQuestStageList where startScoreOpponent equals to UPDATED_START_SCORE_OPPONENT
        defaultMWeeklyQuestStageShouldNotBeFound("startScoreOpponent.in=" + UPDATED_START_SCORE_OPPONENT);
    }

    @Test
    @Transactional
    public void getAllMWeeklyQuestStagesByStartScoreOpponentIsNullOrNotNull() throws Exception {
        // Initialize the database
        mWeeklyQuestStageRepository.saveAndFlush(mWeeklyQuestStage);

        // Get all the mWeeklyQuestStageList where startScoreOpponent is not null
        defaultMWeeklyQuestStageShouldBeFound("startScoreOpponent.specified=true");

        // Get all the mWeeklyQuestStageList where startScoreOpponent is null
        defaultMWeeklyQuestStageShouldNotBeFound("startScoreOpponent.specified=false");
    }

    @Test
    @Transactional
    public void getAllMWeeklyQuestStagesByStartScoreOpponentIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mWeeklyQuestStageRepository.saveAndFlush(mWeeklyQuestStage);

        // Get all the mWeeklyQuestStageList where startScoreOpponent greater than or equals to DEFAULT_START_SCORE_OPPONENT
        defaultMWeeklyQuestStageShouldBeFound("startScoreOpponent.greaterOrEqualThan=" + DEFAULT_START_SCORE_OPPONENT);

        // Get all the mWeeklyQuestStageList where startScoreOpponent greater than or equals to UPDATED_START_SCORE_OPPONENT
        defaultMWeeklyQuestStageShouldNotBeFound("startScoreOpponent.greaterOrEqualThan=" + UPDATED_START_SCORE_OPPONENT);
    }

    @Test
    @Transactional
    public void getAllMWeeklyQuestStagesByStartScoreOpponentIsLessThanSomething() throws Exception {
        // Initialize the database
        mWeeklyQuestStageRepository.saveAndFlush(mWeeklyQuestStage);

        // Get all the mWeeklyQuestStageList where startScoreOpponent less than or equals to DEFAULT_START_SCORE_OPPONENT
        defaultMWeeklyQuestStageShouldNotBeFound("startScoreOpponent.lessThan=" + DEFAULT_START_SCORE_OPPONENT);

        // Get all the mWeeklyQuestStageList where startScoreOpponent less than or equals to UPDATED_START_SCORE_OPPONENT
        defaultMWeeklyQuestStageShouldBeFound("startScoreOpponent.lessThan=" + UPDATED_START_SCORE_OPPONENT);
    }


    @Test
    @Transactional
    public void getAllMWeeklyQuestStagesByConditionIdIsEqualToSomething() throws Exception {
        // Initialize the database
        mWeeklyQuestStageRepository.saveAndFlush(mWeeklyQuestStage);

        // Get all the mWeeklyQuestStageList where conditionId equals to DEFAULT_CONDITION_ID
        defaultMWeeklyQuestStageShouldBeFound("conditionId.equals=" + DEFAULT_CONDITION_ID);

        // Get all the mWeeklyQuestStageList where conditionId equals to UPDATED_CONDITION_ID
        defaultMWeeklyQuestStageShouldNotBeFound("conditionId.equals=" + UPDATED_CONDITION_ID);
    }

    @Test
    @Transactional
    public void getAllMWeeklyQuestStagesByConditionIdIsInShouldWork() throws Exception {
        // Initialize the database
        mWeeklyQuestStageRepository.saveAndFlush(mWeeklyQuestStage);

        // Get all the mWeeklyQuestStageList where conditionId in DEFAULT_CONDITION_ID or UPDATED_CONDITION_ID
        defaultMWeeklyQuestStageShouldBeFound("conditionId.in=" + DEFAULT_CONDITION_ID + "," + UPDATED_CONDITION_ID);

        // Get all the mWeeklyQuestStageList where conditionId equals to UPDATED_CONDITION_ID
        defaultMWeeklyQuestStageShouldNotBeFound("conditionId.in=" + UPDATED_CONDITION_ID);
    }

    @Test
    @Transactional
    public void getAllMWeeklyQuestStagesByConditionIdIsNullOrNotNull() throws Exception {
        // Initialize the database
        mWeeklyQuestStageRepository.saveAndFlush(mWeeklyQuestStage);

        // Get all the mWeeklyQuestStageList where conditionId is not null
        defaultMWeeklyQuestStageShouldBeFound("conditionId.specified=true");

        // Get all the mWeeklyQuestStageList where conditionId is null
        defaultMWeeklyQuestStageShouldNotBeFound("conditionId.specified=false");
    }

    @Test
    @Transactional
    public void getAllMWeeklyQuestStagesByConditionIdIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mWeeklyQuestStageRepository.saveAndFlush(mWeeklyQuestStage);

        // Get all the mWeeklyQuestStageList where conditionId greater than or equals to DEFAULT_CONDITION_ID
        defaultMWeeklyQuestStageShouldBeFound("conditionId.greaterOrEqualThan=" + DEFAULT_CONDITION_ID);

        // Get all the mWeeklyQuestStageList where conditionId greater than or equals to UPDATED_CONDITION_ID
        defaultMWeeklyQuestStageShouldNotBeFound("conditionId.greaterOrEqualThan=" + UPDATED_CONDITION_ID);
    }

    @Test
    @Transactional
    public void getAllMWeeklyQuestStagesByConditionIdIsLessThanSomething() throws Exception {
        // Initialize the database
        mWeeklyQuestStageRepository.saveAndFlush(mWeeklyQuestStage);

        // Get all the mWeeklyQuestStageList where conditionId less than or equals to DEFAULT_CONDITION_ID
        defaultMWeeklyQuestStageShouldNotBeFound("conditionId.lessThan=" + DEFAULT_CONDITION_ID);

        // Get all the mWeeklyQuestStageList where conditionId less than or equals to UPDATED_CONDITION_ID
        defaultMWeeklyQuestStageShouldBeFound("conditionId.lessThan=" + UPDATED_CONDITION_ID);
    }


    @Test
    @Transactional
    public void getAllMWeeklyQuestStagesByOptionIdIsEqualToSomething() throws Exception {
        // Initialize the database
        mWeeklyQuestStageRepository.saveAndFlush(mWeeklyQuestStage);

        // Get all the mWeeklyQuestStageList where optionId equals to DEFAULT_OPTION_ID
        defaultMWeeklyQuestStageShouldBeFound("optionId.equals=" + DEFAULT_OPTION_ID);

        // Get all the mWeeklyQuestStageList where optionId equals to UPDATED_OPTION_ID
        defaultMWeeklyQuestStageShouldNotBeFound("optionId.equals=" + UPDATED_OPTION_ID);
    }

    @Test
    @Transactional
    public void getAllMWeeklyQuestStagesByOptionIdIsInShouldWork() throws Exception {
        // Initialize the database
        mWeeklyQuestStageRepository.saveAndFlush(mWeeklyQuestStage);

        // Get all the mWeeklyQuestStageList where optionId in DEFAULT_OPTION_ID or UPDATED_OPTION_ID
        defaultMWeeklyQuestStageShouldBeFound("optionId.in=" + DEFAULT_OPTION_ID + "," + UPDATED_OPTION_ID);

        // Get all the mWeeklyQuestStageList where optionId equals to UPDATED_OPTION_ID
        defaultMWeeklyQuestStageShouldNotBeFound("optionId.in=" + UPDATED_OPTION_ID);
    }

    @Test
    @Transactional
    public void getAllMWeeklyQuestStagesByOptionIdIsNullOrNotNull() throws Exception {
        // Initialize the database
        mWeeklyQuestStageRepository.saveAndFlush(mWeeklyQuestStage);

        // Get all the mWeeklyQuestStageList where optionId is not null
        defaultMWeeklyQuestStageShouldBeFound("optionId.specified=true");

        // Get all the mWeeklyQuestStageList where optionId is null
        defaultMWeeklyQuestStageShouldNotBeFound("optionId.specified=false");
    }

    @Test
    @Transactional
    public void getAllMWeeklyQuestStagesByOptionIdIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mWeeklyQuestStageRepository.saveAndFlush(mWeeklyQuestStage);

        // Get all the mWeeklyQuestStageList where optionId greater than or equals to DEFAULT_OPTION_ID
        defaultMWeeklyQuestStageShouldBeFound("optionId.greaterOrEqualThan=" + DEFAULT_OPTION_ID);

        // Get all the mWeeklyQuestStageList where optionId greater than or equals to UPDATED_OPTION_ID
        defaultMWeeklyQuestStageShouldNotBeFound("optionId.greaterOrEqualThan=" + UPDATED_OPTION_ID);
    }

    @Test
    @Transactional
    public void getAllMWeeklyQuestStagesByOptionIdIsLessThanSomething() throws Exception {
        // Initialize the database
        mWeeklyQuestStageRepository.saveAndFlush(mWeeklyQuestStage);

        // Get all the mWeeklyQuestStageList where optionId less than or equals to DEFAULT_OPTION_ID
        defaultMWeeklyQuestStageShouldNotBeFound("optionId.lessThan=" + DEFAULT_OPTION_ID);

        // Get all the mWeeklyQuestStageList where optionId less than or equals to UPDATED_OPTION_ID
        defaultMWeeklyQuestStageShouldBeFound("optionId.lessThan=" + UPDATED_OPTION_ID);
    }


    @Test
    @Transactional
    public void getAllMWeeklyQuestStagesByDeckConditionIdIsEqualToSomething() throws Exception {
        // Initialize the database
        mWeeklyQuestStageRepository.saveAndFlush(mWeeklyQuestStage);

        // Get all the mWeeklyQuestStageList where deckConditionId equals to DEFAULT_DECK_CONDITION_ID
        defaultMWeeklyQuestStageShouldBeFound("deckConditionId.equals=" + DEFAULT_DECK_CONDITION_ID);

        // Get all the mWeeklyQuestStageList where deckConditionId equals to UPDATED_DECK_CONDITION_ID
        defaultMWeeklyQuestStageShouldNotBeFound("deckConditionId.equals=" + UPDATED_DECK_CONDITION_ID);
    }

    @Test
    @Transactional
    public void getAllMWeeklyQuestStagesByDeckConditionIdIsInShouldWork() throws Exception {
        // Initialize the database
        mWeeklyQuestStageRepository.saveAndFlush(mWeeklyQuestStage);

        // Get all the mWeeklyQuestStageList where deckConditionId in DEFAULT_DECK_CONDITION_ID or UPDATED_DECK_CONDITION_ID
        defaultMWeeklyQuestStageShouldBeFound("deckConditionId.in=" + DEFAULT_DECK_CONDITION_ID + "," + UPDATED_DECK_CONDITION_ID);

        // Get all the mWeeklyQuestStageList where deckConditionId equals to UPDATED_DECK_CONDITION_ID
        defaultMWeeklyQuestStageShouldNotBeFound("deckConditionId.in=" + UPDATED_DECK_CONDITION_ID);
    }

    @Test
    @Transactional
    public void getAllMWeeklyQuestStagesByDeckConditionIdIsNullOrNotNull() throws Exception {
        // Initialize the database
        mWeeklyQuestStageRepository.saveAndFlush(mWeeklyQuestStage);

        // Get all the mWeeklyQuestStageList where deckConditionId is not null
        defaultMWeeklyQuestStageShouldBeFound("deckConditionId.specified=true");

        // Get all the mWeeklyQuestStageList where deckConditionId is null
        defaultMWeeklyQuestStageShouldNotBeFound("deckConditionId.specified=false");
    }

    @Test
    @Transactional
    public void getAllMWeeklyQuestStagesByDeckConditionIdIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mWeeklyQuestStageRepository.saveAndFlush(mWeeklyQuestStage);

        // Get all the mWeeklyQuestStageList where deckConditionId greater than or equals to DEFAULT_DECK_CONDITION_ID
        defaultMWeeklyQuestStageShouldBeFound("deckConditionId.greaterOrEqualThan=" + DEFAULT_DECK_CONDITION_ID);

        // Get all the mWeeklyQuestStageList where deckConditionId greater than or equals to UPDATED_DECK_CONDITION_ID
        defaultMWeeklyQuestStageShouldNotBeFound("deckConditionId.greaterOrEqualThan=" + UPDATED_DECK_CONDITION_ID);
    }

    @Test
    @Transactional
    public void getAllMWeeklyQuestStagesByDeckConditionIdIsLessThanSomething() throws Exception {
        // Initialize the database
        mWeeklyQuestStageRepository.saveAndFlush(mWeeklyQuestStage);

        // Get all the mWeeklyQuestStageList where deckConditionId less than or equals to DEFAULT_DECK_CONDITION_ID
        defaultMWeeklyQuestStageShouldNotBeFound("deckConditionId.lessThan=" + DEFAULT_DECK_CONDITION_ID);

        // Get all the mWeeklyQuestStageList where deckConditionId less than or equals to UPDATED_DECK_CONDITION_ID
        defaultMWeeklyQuestStageShouldBeFound("deckConditionId.lessThan=" + UPDATED_DECK_CONDITION_ID);
    }


    @Test
    @Transactional
    public void getAllMWeeklyQuestStagesByMweeklyquestworldIsEqualToSomething() throws Exception {
        // Get already existing entity
        MWeeklyQuestWorld mweeklyquestworld = mWeeklyQuestStage.getMweeklyquestworld();
        mWeeklyQuestStageRepository.saveAndFlush(mWeeklyQuestStage);
        Long mweeklyquestworldId = mweeklyquestworld.getId();

        // Get all the mWeeklyQuestStageList where mweeklyquestworld equals to mweeklyquestworldId
        defaultMWeeklyQuestStageShouldBeFound("mweeklyquestworldId.equals=" + mweeklyquestworldId);

        // Get all the mWeeklyQuestStageList where mweeklyquestworld equals to mweeklyquestworldId + 1
        defaultMWeeklyQuestStageShouldNotBeFound("mweeklyquestworldId.equals=" + (mweeklyquestworldId + 1));
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultMWeeklyQuestStageShouldBeFound(String filter) throws Exception {
        restMWeeklyQuestStageMockMvc.perform(get("/api/m-weekly-quest-stages?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(mWeeklyQuestStage.getId().intValue())))
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
        restMWeeklyQuestStageMockMvc.perform(get("/api/m-weekly-quest-stages/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultMWeeklyQuestStageShouldNotBeFound(String filter) throws Exception {
        restMWeeklyQuestStageMockMvc.perform(get("/api/m-weekly-quest-stages?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restMWeeklyQuestStageMockMvc.perform(get("/api/m-weekly-quest-stages/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("0"));
    }


    @Test
    @Transactional
    public void getNonExistingMWeeklyQuestStage() throws Exception {
        // Get the mWeeklyQuestStage
        restMWeeklyQuestStageMockMvc.perform(get("/api/m-weekly-quest-stages/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateMWeeklyQuestStage() throws Exception {
        // Initialize the database
        mWeeklyQuestStageRepository.saveAndFlush(mWeeklyQuestStage);

        int databaseSizeBeforeUpdate = mWeeklyQuestStageRepository.findAll().size();

        // Update the mWeeklyQuestStage
        MWeeklyQuestStage updatedMWeeklyQuestStage = mWeeklyQuestStageRepository.findById(mWeeklyQuestStage.getId()).get();
        // Disconnect from session so that the updates on updatedMWeeklyQuestStage are not directly saved in db
        em.detach(updatedMWeeklyQuestStage);
        updatedMWeeklyQuestStage
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
        MWeeklyQuestStageDTO mWeeklyQuestStageDTO = mWeeklyQuestStageMapper.toDto(updatedMWeeklyQuestStage);

        restMWeeklyQuestStageMockMvc.perform(put("/api/m-weekly-quest-stages")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mWeeklyQuestStageDTO)))
            .andExpect(status().isOk());

        // Validate the MWeeklyQuestStage in the database
        List<MWeeklyQuestStage> mWeeklyQuestStageList = mWeeklyQuestStageRepository.findAll();
        assertThat(mWeeklyQuestStageList).hasSize(databaseSizeBeforeUpdate);
        MWeeklyQuestStage testMWeeklyQuestStage = mWeeklyQuestStageList.get(mWeeklyQuestStageList.size() - 1);
        assertThat(testMWeeklyQuestStage.getWorldId()).isEqualTo(UPDATED_WORLD_ID);
        assertThat(testMWeeklyQuestStage.getNumber()).isEqualTo(UPDATED_NUMBER);
        assertThat(testMWeeklyQuestStage.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testMWeeklyQuestStage.getImagePath()).isEqualTo(UPDATED_IMAGE_PATH);
        assertThat(testMWeeklyQuestStage.getCharacterThumbnailPath()).isEqualTo(UPDATED_CHARACTER_THUMBNAIL_PATH);
        assertThat(testMWeeklyQuestStage.getDifficulty()).isEqualTo(UPDATED_DIFFICULTY);
        assertThat(testMWeeklyQuestStage.getStageUnlockPattern()).isEqualTo(UPDATED_STAGE_UNLOCK_PATTERN);
        assertThat(testMWeeklyQuestStage.getStoryOnly()).isEqualTo(UPDATED_STORY_ONLY);
        assertThat(testMWeeklyQuestStage.getFirstHalfNpcDeckId()).isEqualTo(UPDATED_FIRST_HALF_NPC_DECK_ID);
        assertThat(testMWeeklyQuestStage.getFirstHalfEnvironmentId()).isEqualTo(UPDATED_FIRST_HALF_ENVIRONMENT_ID);
        assertThat(testMWeeklyQuestStage.getSecondHalfNpcDeckId()).isEqualTo(UPDATED_SECOND_HALF_NPC_DECK_ID);
        assertThat(testMWeeklyQuestStage.getSecondHalfEnvironmentId()).isEqualTo(UPDATED_SECOND_HALF_ENVIRONMENT_ID);
        assertThat(testMWeeklyQuestStage.getExtraFirstHalfNpcDeckId()).isEqualTo(UPDATED_EXTRA_FIRST_HALF_NPC_DECK_ID);
        assertThat(testMWeeklyQuestStage.getExtraSecondHalfNpcDeckId()).isEqualTo(UPDATED_EXTRA_SECOND_HALF_NPC_DECK_ID);
        assertThat(testMWeeklyQuestStage.getConsumeAp()).isEqualTo(UPDATED_CONSUME_AP);
        assertThat(testMWeeklyQuestStage.getKickOffType()).isEqualTo(UPDATED_KICK_OFF_TYPE);
        assertThat(testMWeeklyQuestStage.getMatchMinute()).isEqualTo(UPDATED_MATCH_MINUTE);
        assertThat(testMWeeklyQuestStage.getEnableExtra()).isEqualTo(UPDATED_ENABLE_EXTRA);
        assertThat(testMWeeklyQuestStage.getEnablePk()).isEqualTo(UPDATED_ENABLE_PK);
        assertThat(testMWeeklyQuestStage.getAiLevel()).isEqualTo(UPDATED_AI_LEVEL);
        assertThat(testMWeeklyQuestStage.getStartAtSecondHalf()).isEqualTo(UPDATED_START_AT_SECOND_HALF);
        assertThat(testMWeeklyQuestStage.getStartScore()).isEqualTo(UPDATED_START_SCORE);
        assertThat(testMWeeklyQuestStage.getStartScoreOpponent()).isEqualTo(UPDATED_START_SCORE_OPPONENT);
        assertThat(testMWeeklyQuestStage.getConditionId()).isEqualTo(UPDATED_CONDITION_ID);
        assertThat(testMWeeklyQuestStage.getOptionId()).isEqualTo(UPDATED_OPTION_ID);
        assertThat(testMWeeklyQuestStage.getDeckConditionId()).isEqualTo(UPDATED_DECK_CONDITION_ID);
    }

    @Test
    @Transactional
    public void updateNonExistingMWeeklyQuestStage() throws Exception {
        int databaseSizeBeforeUpdate = mWeeklyQuestStageRepository.findAll().size();

        // Create the MWeeklyQuestStage
        MWeeklyQuestStageDTO mWeeklyQuestStageDTO = mWeeklyQuestStageMapper.toDto(mWeeklyQuestStage);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restMWeeklyQuestStageMockMvc.perform(put("/api/m-weekly-quest-stages")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mWeeklyQuestStageDTO)))
            .andExpect(status().isBadRequest());

        // Validate the MWeeklyQuestStage in the database
        List<MWeeklyQuestStage> mWeeklyQuestStageList = mWeeklyQuestStageRepository.findAll();
        assertThat(mWeeklyQuestStageList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteMWeeklyQuestStage() throws Exception {
        // Initialize the database
        mWeeklyQuestStageRepository.saveAndFlush(mWeeklyQuestStage);

        int databaseSizeBeforeDelete = mWeeklyQuestStageRepository.findAll().size();

        // Delete the mWeeklyQuestStage
        restMWeeklyQuestStageMockMvc.perform(delete("/api/m-weekly-quest-stages/{id}", mWeeklyQuestStage.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database is empty
        List<MWeeklyQuestStage> mWeeklyQuestStageList = mWeeklyQuestStageRepository.findAll();
        assertThat(mWeeklyQuestStageList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(MWeeklyQuestStage.class);
        MWeeklyQuestStage mWeeklyQuestStage1 = new MWeeklyQuestStage();
        mWeeklyQuestStage1.setId(1L);
        MWeeklyQuestStage mWeeklyQuestStage2 = new MWeeklyQuestStage();
        mWeeklyQuestStage2.setId(mWeeklyQuestStage1.getId());
        assertThat(mWeeklyQuestStage1).isEqualTo(mWeeklyQuestStage2);
        mWeeklyQuestStage2.setId(2L);
        assertThat(mWeeklyQuestStage1).isNotEqualTo(mWeeklyQuestStage2);
        mWeeklyQuestStage1.setId(null);
        assertThat(mWeeklyQuestStage1).isNotEqualTo(mWeeklyQuestStage2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(MWeeklyQuestStageDTO.class);
        MWeeklyQuestStageDTO mWeeklyQuestStageDTO1 = new MWeeklyQuestStageDTO();
        mWeeklyQuestStageDTO1.setId(1L);
        MWeeklyQuestStageDTO mWeeklyQuestStageDTO2 = new MWeeklyQuestStageDTO();
        assertThat(mWeeklyQuestStageDTO1).isNotEqualTo(mWeeklyQuestStageDTO2);
        mWeeklyQuestStageDTO2.setId(mWeeklyQuestStageDTO1.getId());
        assertThat(mWeeklyQuestStageDTO1).isEqualTo(mWeeklyQuestStageDTO2);
        mWeeklyQuestStageDTO2.setId(2L);
        assertThat(mWeeklyQuestStageDTO1).isNotEqualTo(mWeeklyQuestStageDTO2);
        mWeeklyQuestStageDTO1.setId(null);
        assertThat(mWeeklyQuestStageDTO1).isNotEqualTo(mWeeklyQuestStageDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(mWeeklyQuestStageMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(mWeeklyQuestStageMapper.fromId(null)).isNull();
    }
}
