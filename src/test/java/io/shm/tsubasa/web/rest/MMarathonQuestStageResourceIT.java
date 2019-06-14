package io.shm.tsubasa.web.rest;

import io.shm.tsubasa.TsubasaApp;
import io.shm.tsubasa.domain.MMarathonQuestStage;
import io.shm.tsubasa.domain.MMarathonQuestWorld;
import io.shm.tsubasa.repository.MMarathonQuestStageRepository;
import io.shm.tsubasa.service.MMarathonQuestStageService;
import io.shm.tsubasa.service.dto.MMarathonQuestStageDTO;
import io.shm.tsubasa.service.mapper.MMarathonQuestStageMapper;
import io.shm.tsubasa.web.rest.errors.ExceptionTranslator;
import io.shm.tsubasa.service.dto.MMarathonQuestStageCriteria;
import io.shm.tsubasa.service.MMarathonQuestStageQueryService;

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
 * Integration tests for the {@Link MMarathonQuestStageResource} REST controller.
 */
@SpringBootTest(classes = TsubasaApp.class)
public class MMarathonQuestStageResourceIT {

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
    private MMarathonQuestStageRepository mMarathonQuestStageRepository;

    @Autowired
    private MMarathonQuestStageMapper mMarathonQuestStageMapper;

    @Autowired
    private MMarathonQuestStageService mMarathonQuestStageService;

    @Autowired
    private MMarathonQuestStageQueryService mMarathonQuestStageQueryService;

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

    private MockMvc restMMarathonQuestStageMockMvc;

    private MMarathonQuestStage mMarathonQuestStage;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final MMarathonQuestStageResource mMarathonQuestStageResource = new MMarathonQuestStageResource(mMarathonQuestStageService, mMarathonQuestStageQueryService);
        this.restMMarathonQuestStageMockMvc = MockMvcBuilders.standaloneSetup(mMarathonQuestStageResource)
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
    public static MMarathonQuestStage createEntity(EntityManager em) {
        MMarathonQuestStage mMarathonQuestStage = new MMarathonQuestStage()
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
        MMarathonQuestWorld mMarathonQuestWorld;
        if (TestUtil.findAll(em, MMarathonQuestWorld.class).isEmpty()) {
            mMarathonQuestWorld = MMarathonQuestWorldResourceIT.createEntity(em);
            em.persist(mMarathonQuestWorld);
            em.flush();
        } else {
            mMarathonQuestWorld = TestUtil.findAll(em, MMarathonQuestWorld.class).get(0);
        }
        mMarathonQuestStage.setId(mMarathonQuestWorld);
        return mMarathonQuestStage;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static MMarathonQuestStage createUpdatedEntity(EntityManager em) {
        MMarathonQuestStage mMarathonQuestStage = new MMarathonQuestStage()
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
        MMarathonQuestWorld mMarathonQuestWorld;
        if (TestUtil.findAll(em, MMarathonQuestWorld.class).isEmpty()) {
            mMarathonQuestWorld = MMarathonQuestWorldResourceIT.createUpdatedEntity(em);
            em.persist(mMarathonQuestWorld);
            em.flush();
        } else {
            mMarathonQuestWorld = TestUtil.findAll(em, MMarathonQuestWorld.class).get(0);
        }
        mMarathonQuestStage.setId(mMarathonQuestWorld);
        return mMarathonQuestStage;
    }

    @BeforeEach
    public void initTest() {
        mMarathonQuestStage = createEntity(em);
    }

    @Test
    @Transactional
    public void createMMarathonQuestStage() throws Exception {
        int databaseSizeBeforeCreate = mMarathonQuestStageRepository.findAll().size();

        // Create the MMarathonQuestStage
        MMarathonQuestStageDTO mMarathonQuestStageDTO = mMarathonQuestStageMapper.toDto(mMarathonQuestStage);
        restMMarathonQuestStageMockMvc.perform(post("/api/m-marathon-quest-stages")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mMarathonQuestStageDTO)))
            .andExpect(status().isCreated());

        // Validate the MMarathonQuestStage in the database
        List<MMarathonQuestStage> mMarathonQuestStageList = mMarathonQuestStageRepository.findAll();
        assertThat(mMarathonQuestStageList).hasSize(databaseSizeBeforeCreate + 1);
        MMarathonQuestStage testMMarathonQuestStage = mMarathonQuestStageList.get(mMarathonQuestStageList.size() - 1);
        assertThat(testMMarathonQuestStage.getWorldId()).isEqualTo(DEFAULT_WORLD_ID);
        assertThat(testMMarathonQuestStage.getNumber()).isEqualTo(DEFAULT_NUMBER);
        assertThat(testMMarathonQuestStage.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testMMarathonQuestStage.getImagePath()).isEqualTo(DEFAULT_IMAGE_PATH);
        assertThat(testMMarathonQuestStage.getCharacterThumbnailPath()).isEqualTo(DEFAULT_CHARACTER_THUMBNAIL_PATH);
        assertThat(testMMarathonQuestStage.getDifficulty()).isEqualTo(DEFAULT_DIFFICULTY);
        assertThat(testMMarathonQuestStage.getStageUnlockPattern()).isEqualTo(DEFAULT_STAGE_UNLOCK_PATTERN);
        assertThat(testMMarathonQuestStage.getStoryOnly()).isEqualTo(DEFAULT_STORY_ONLY);
        assertThat(testMMarathonQuestStage.getFirstHalfNpcDeckId()).isEqualTo(DEFAULT_FIRST_HALF_NPC_DECK_ID);
        assertThat(testMMarathonQuestStage.getFirstHalfEnvironmentId()).isEqualTo(DEFAULT_FIRST_HALF_ENVIRONMENT_ID);
        assertThat(testMMarathonQuestStage.getSecondHalfNpcDeckId()).isEqualTo(DEFAULT_SECOND_HALF_NPC_DECK_ID);
        assertThat(testMMarathonQuestStage.getSecondHalfEnvironmentId()).isEqualTo(DEFAULT_SECOND_HALF_ENVIRONMENT_ID);
        assertThat(testMMarathonQuestStage.getExtraFirstHalfNpcDeckId()).isEqualTo(DEFAULT_EXTRA_FIRST_HALF_NPC_DECK_ID);
        assertThat(testMMarathonQuestStage.getExtraSecondHalfNpcDeckId()).isEqualTo(DEFAULT_EXTRA_SECOND_HALF_NPC_DECK_ID);
        assertThat(testMMarathonQuestStage.getConsumeAp()).isEqualTo(DEFAULT_CONSUME_AP);
        assertThat(testMMarathonQuestStage.getKickOffType()).isEqualTo(DEFAULT_KICK_OFF_TYPE);
        assertThat(testMMarathonQuestStage.getMatchMinute()).isEqualTo(DEFAULT_MATCH_MINUTE);
        assertThat(testMMarathonQuestStage.getEnableExtra()).isEqualTo(DEFAULT_ENABLE_EXTRA);
        assertThat(testMMarathonQuestStage.getEnablePk()).isEqualTo(DEFAULT_ENABLE_PK);
        assertThat(testMMarathonQuestStage.getAiLevel()).isEqualTo(DEFAULT_AI_LEVEL);
        assertThat(testMMarathonQuestStage.getStartAtSecondHalf()).isEqualTo(DEFAULT_START_AT_SECOND_HALF);
        assertThat(testMMarathonQuestStage.getStartScore()).isEqualTo(DEFAULT_START_SCORE);
        assertThat(testMMarathonQuestStage.getStartScoreOpponent()).isEqualTo(DEFAULT_START_SCORE_OPPONENT);
        assertThat(testMMarathonQuestStage.getConditionId()).isEqualTo(DEFAULT_CONDITION_ID);
        assertThat(testMMarathonQuestStage.getOptionId()).isEqualTo(DEFAULT_OPTION_ID);
        assertThat(testMMarathonQuestStage.getDeckConditionId()).isEqualTo(DEFAULT_DECK_CONDITION_ID);
    }

    @Test
    @Transactional
    public void createMMarathonQuestStageWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = mMarathonQuestStageRepository.findAll().size();

        // Create the MMarathonQuestStage with an existing ID
        mMarathonQuestStage.setId(1L);
        MMarathonQuestStageDTO mMarathonQuestStageDTO = mMarathonQuestStageMapper.toDto(mMarathonQuestStage);

        // An entity with an existing ID cannot be created, so this API call must fail
        restMMarathonQuestStageMockMvc.perform(post("/api/m-marathon-quest-stages")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mMarathonQuestStageDTO)))
            .andExpect(status().isBadRequest());

        // Validate the MMarathonQuestStage in the database
        List<MMarathonQuestStage> mMarathonQuestStageList = mMarathonQuestStageRepository.findAll();
        assertThat(mMarathonQuestStageList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkWorldIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = mMarathonQuestStageRepository.findAll().size();
        // set the field null
        mMarathonQuestStage.setWorldId(null);

        // Create the MMarathonQuestStage, which fails.
        MMarathonQuestStageDTO mMarathonQuestStageDTO = mMarathonQuestStageMapper.toDto(mMarathonQuestStage);

        restMMarathonQuestStageMockMvc.perform(post("/api/m-marathon-quest-stages")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mMarathonQuestStageDTO)))
            .andExpect(status().isBadRequest());

        List<MMarathonQuestStage> mMarathonQuestStageList = mMarathonQuestStageRepository.findAll();
        assertThat(mMarathonQuestStageList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkNumberIsRequired() throws Exception {
        int databaseSizeBeforeTest = mMarathonQuestStageRepository.findAll().size();
        // set the field null
        mMarathonQuestStage.setNumber(null);

        // Create the MMarathonQuestStage, which fails.
        MMarathonQuestStageDTO mMarathonQuestStageDTO = mMarathonQuestStageMapper.toDto(mMarathonQuestStage);

        restMMarathonQuestStageMockMvc.perform(post("/api/m-marathon-quest-stages")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mMarathonQuestStageDTO)))
            .andExpect(status().isBadRequest());

        List<MMarathonQuestStage> mMarathonQuestStageList = mMarathonQuestStageRepository.findAll();
        assertThat(mMarathonQuestStageList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkDifficultyIsRequired() throws Exception {
        int databaseSizeBeforeTest = mMarathonQuestStageRepository.findAll().size();
        // set the field null
        mMarathonQuestStage.setDifficulty(null);

        // Create the MMarathonQuestStage, which fails.
        MMarathonQuestStageDTO mMarathonQuestStageDTO = mMarathonQuestStageMapper.toDto(mMarathonQuestStage);

        restMMarathonQuestStageMockMvc.perform(post("/api/m-marathon-quest-stages")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mMarathonQuestStageDTO)))
            .andExpect(status().isBadRequest());

        List<MMarathonQuestStage> mMarathonQuestStageList = mMarathonQuestStageRepository.findAll();
        assertThat(mMarathonQuestStageList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkStageUnlockPatternIsRequired() throws Exception {
        int databaseSizeBeforeTest = mMarathonQuestStageRepository.findAll().size();
        // set the field null
        mMarathonQuestStage.setStageUnlockPattern(null);

        // Create the MMarathonQuestStage, which fails.
        MMarathonQuestStageDTO mMarathonQuestStageDTO = mMarathonQuestStageMapper.toDto(mMarathonQuestStage);

        restMMarathonQuestStageMockMvc.perform(post("/api/m-marathon-quest-stages")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mMarathonQuestStageDTO)))
            .andExpect(status().isBadRequest());

        List<MMarathonQuestStage> mMarathonQuestStageList = mMarathonQuestStageRepository.findAll();
        assertThat(mMarathonQuestStageList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkStoryOnlyIsRequired() throws Exception {
        int databaseSizeBeforeTest = mMarathonQuestStageRepository.findAll().size();
        // set the field null
        mMarathonQuestStage.setStoryOnly(null);

        // Create the MMarathonQuestStage, which fails.
        MMarathonQuestStageDTO mMarathonQuestStageDTO = mMarathonQuestStageMapper.toDto(mMarathonQuestStage);

        restMMarathonQuestStageMockMvc.perform(post("/api/m-marathon-quest-stages")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mMarathonQuestStageDTO)))
            .andExpect(status().isBadRequest());

        List<MMarathonQuestStage> mMarathonQuestStageList = mMarathonQuestStageRepository.findAll();
        assertThat(mMarathonQuestStageList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkFirstHalfNpcDeckIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = mMarathonQuestStageRepository.findAll().size();
        // set the field null
        mMarathonQuestStage.setFirstHalfNpcDeckId(null);

        // Create the MMarathonQuestStage, which fails.
        MMarathonQuestStageDTO mMarathonQuestStageDTO = mMarathonQuestStageMapper.toDto(mMarathonQuestStage);

        restMMarathonQuestStageMockMvc.perform(post("/api/m-marathon-quest-stages")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mMarathonQuestStageDTO)))
            .andExpect(status().isBadRequest());

        List<MMarathonQuestStage> mMarathonQuestStageList = mMarathonQuestStageRepository.findAll();
        assertThat(mMarathonQuestStageList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkFirstHalfEnvironmentIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = mMarathonQuestStageRepository.findAll().size();
        // set the field null
        mMarathonQuestStage.setFirstHalfEnvironmentId(null);

        // Create the MMarathonQuestStage, which fails.
        MMarathonQuestStageDTO mMarathonQuestStageDTO = mMarathonQuestStageMapper.toDto(mMarathonQuestStage);

        restMMarathonQuestStageMockMvc.perform(post("/api/m-marathon-quest-stages")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mMarathonQuestStageDTO)))
            .andExpect(status().isBadRequest());

        List<MMarathonQuestStage> mMarathonQuestStageList = mMarathonQuestStageRepository.findAll();
        assertThat(mMarathonQuestStageList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkSecondHalfNpcDeckIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = mMarathonQuestStageRepository.findAll().size();
        // set the field null
        mMarathonQuestStage.setSecondHalfNpcDeckId(null);

        // Create the MMarathonQuestStage, which fails.
        MMarathonQuestStageDTO mMarathonQuestStageDTO = mMarathonQuestStageMapper.toDto(mMarathonQuestStage);

        restMMarathonQuestStageMockMvc.perform(post("/api/m-marathon-quest-stages")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mMarathonQuestStageDTO)))
            .andExpect(status().isBadRequest());

        List<MMarathonQuestStage> mMarathonQuestStageList = mMarathonQuestStageRepository.findAll();
        assertThat(mMarathonQuestStageList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkSecondHalfEnvironmentIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = mMarathonQuestStageRepository.findAll().size();
        // set the field null
        mMarathonQuestStage.setSecondHalfEnvironmentId(null);

        // Create the MMarathonQuestStage, which fails.
        MMarathonQuestStageDTO mMarathonQuestStageDTO = mMarathonQuestStageMapper.toDto(mMarathonQuestStage);

        restMMarathonQuestStageMockMvc.perform(post("/api/m-marathon-quest-stages")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mMarathonQuestStageDTO)))
            .andExpect(status().isBadRequest());

        List<MMarathonQuestStage> mMarathonQuestStageList = mMarathonQuestStageRepository.findAll();
        assertThat(mMarathonQuestStageList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkExtraFirstHalfNpcDeckIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = mMarathonQuestStageRepository.findAll().size();
        // set the field null
        mMarathonQuestStage.setExtraFirstHalfNpcDeckId(null);

        // Create the MMarathonQuestStage, which fails.
        MMarathonQuestStageDTO mMarathonQuestStageDTO = mMarathonQuestStageMapper.toDto(mMarathonQuestStage);

        restMMarathonQuestStageMockMvc.perform(post("/api/m-marathon-quest-stages")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mMarathonQuestStageDTO)))
            .andExpect(status().isBadRequest());

        List<MMarathonQuestStage> mMarathonQuestStageList = mMarathonQuestStageRepository.findAll();
        assertThat(mMarathonQuestStageList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkExtraSecondHalfNpcDeckIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = mMarathonQuestStageRepository.findAll().size();
        // set the field null
        mMarathonQuestStage.setExtraSecondHalfNpcDeckId(null);

        // Create the MMarathonQuestStage, which fails.
        MMarathonQuestStageDTO mMarathonQuestStageDTO = mMarathonQuestStageMapper.toDto(mMarathonQuestStage);

        restMMarathonQuestStageMockMvc.perform(post("/api/m-marathon-quest-stages")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mMarathonQuestStageDTO)))
            .andExpect(status().isBadRequest());

        List<MMarathonQuestStage> mMarathonQuestStageList = mMarathonQuestStageRepository.findAll();
        assertThat(mMarathonQuestStageList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkConsumeApIsRequired() throws Exception {
        int databaseSizeBeforeTest = mMarathonQuestStageRepository.findAll().size();
        // set the field null
        mMarathonQuestStage.setConsumeAp(null);

        // Create the MMarathonQuestStage, which fails.
        MMarathonQuestStageDTO mMarathonQuestStageDTO = mMarathonQuestStageMapper.toDto(mMarathonQuestStage);

        restMMarathonQuestStageMockMvc.perform(post("/api/m-marathon-quest-stages")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mMarathonQuestStageDTO)))
            .andExpect(status().isBadRequest());

        List<MMarathonQuestStage> mMarathonQuestStageList = mMarathonQuestStageRepository.findAll();
        assertThat(mMarathonQuestStageList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkKickOffTypeIsRequired() throws Exception {
        int databaseSizeBeforeTest = mMarathonQuestStageRepository.findAll().size();
        // set the field null
        mMarathonQuestStage.setKickOffType(null);

        // Create the MMarathonQuestStage, which fails.
        MMarathonQuestStageDTO mMarathonQuestStageDTO = mMarathonQuestStageMapper.toDto(mMarathonQuestStage);

        restMMarathonQuestStageMockMvc.perform(post("/api/m-marathon-quest-stages")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mMarathonQuestStageDTO)))
            .andExpect(status().isBadRequest());

        List<MMarathonQuestStage> mMarathonQuestStageList = mMarathonQuestStageRepository.findAll();
        assertThat(mMarathonQuestStageList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkMatchMinuteIsRequired() throws Exception {
        int databaseSizeBeforeTest = mMarathonQuestStageRepository.findAll().size();
        // set the field null
        mMarathonQuestStage.setMatchMinute(null);

        // Create the MMarathonQuestStage, which fails.
        MMarathonQuestStageDTO mMarathonQuestStageDTO = mMarathonQuestStageMapper.toDto(mMarathonQuestStage);

        restMMarathonQuestStageMockMvc.perform(post("/api/m-marathon-quest-stages")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mMarathonQuestStageDTO)))
            .andExpect(status().isBadRequest());

        List<MMarathonQuestStage> mMarathonQuestStageList = mMarathonQuestStageRepository.findAll();
        assertThat(mMarathonQuestStageList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkEnableExtraIsRequired() throws Exception {
        int databaseSizeBeforeTest = mMarathonQuestStageRepository.findAll().size();
        // set the field null
        mMarathonQuestStage.setEnableExtra(null);

        // Create the MMarathonQuestStage, which fails.
        MMarathonQuestStageDTO mMarathonQuestStageDTO = mMarathonQuestStageMapper.toDto(mMarathonQuestStage);

        restMMarathonQuestStageMockMvc.perform(post("/api/m-marathon-quest-stages")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mMarathonQuestStageDTO)))
            .andExpect(status().isBadRequest());

        List<MMarathonQuestStage> mMarathonQuestStageList = mMarathonQuestStageRepository.findAll();
        assertThat(mMarathonQuestStageList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkEnablePkIsRequired() throws Exception {
        int databaseSizeBeforeTest = mMarathonQuestStageRepository.findAll().size();
        // set the field null
        mMarathonQuestStage.setEnablePk(null);

        // Create the MMarathonQuestStage, which fails.
        MMarathonQuestStageDTO mMarathonQuestStageDTO = mMarathonQuestStageMapper.toDto(mMarathonQuestStage);

        restMMarathonQuestStageMockMvc.perform(post("/api/m-marathon-quest-stages")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mMarathonQuestStageDTO)))
            .andExpect(status().isBadRequest());

        List<MMarathonQuestStage> mMarathonQuestStageList = mMarathonQuestStageRepository.findAll();
        assertThat(mMarathonQuestStageList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkAiLevelIsRequired() throws Exception {
        int databaseSizeBeforeTest = mMarathonQuestStageRepository.findAll().size();
        // set the field null
        mMarathonQuestStage.setAiLevel(null);

        // Create the MMarathonQuestStage, which fails.
        MMarathonQuestStageDTO mMarathonQuestStageDTO = mMarathonQuestStageMapper.toDto(mMarathonQuestStage);

        restMMarathonQuestStageMockMvc.perform(post("/api/m-marathon-quest-stages")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mMarathonQuestStageDTO)))
            .andExpect(status().isBadRequest());

        List<MMarathonQuestStage> mMarathonQuestStageList = mMarathonQuestStageRepository.findAll();
        assertThat(mMarathonQuestStageList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkStartAtSecondHalfIsRequired() throws Exception {
        int databaseSizeBeforeTest = mMarathonQuestStageRepository.findAll().size();
        // set the field null
        mMarathonQuestStage.setStartAtSecondHalf(null);

        // Create the MMarathonQuestStage, which fails.
        MMarathonQuestStageDTO mMarathonQuestStageDTO = mMarathonQuestStageMapper.toDto(mMarathonQuestStage);

        restMMarathonQuestStageMockMvc.perform(post("/api/m-marathon-quest-stages")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mMarathonQuestStageDTO)))
            .andExpect(status().isBadRequest());

        List<MMarathonQuestStage> mMarathonQuestStageList = mMarathonQuestStageRepository.findAll();
        assertThat(mMarathonQuestStageList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkStartScoreIsRequired() throws Exception {
        int databaseSizeBeforeTest = mMarathonQuestStageRepository.findAll().size();
        // set the field null
        mMarathonQuestStage.setStartScore(null);

        // Create the MMarathonQuestStage, which fails.
        MMarathonQuestStageDTO mMarathonQuestStageDTO = mMarathonQuestStageMapper.toDto(mMarathonQuestStage);

        restMMarathonQuestStageMockMvc.perform(post("/api/m-marathon-quest-stages")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mMarathonQuestStageDTO)))
            .andExpect(status().isBadRequest());

        List<MMarathonQuestStage> mMarathonQuestStageList = mMarathonQuestStageRepository.findAll();
        assertThat(mMarathonQuestStageList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkStartScoreOpponentIsRequired() throws Exception {
        int databaseSizeBeforeTest = mMarathonQuestStageRepository.findAll().size();
        // set the field null
        mMarathonQuestStage.setStartScoreOpponent(null);

        // Create the MMarathonQuestStage, which fails.
        MMarathonQuestStageDTO mMarathonQuestStageDTO = mMarathonQuestStageMapper.toDto(mMarathonQuestStage);

        restMMarathonQuestStageMockMvc.perform(post("/api/m-marathon-quest-stages")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mMarathonQuestStageDTO)))
            .andExpect(status().isBadRequest());

        List<MMarathonQuestStage> mMarathonQuestStageList = mMarathonQuestStageRepository.findAll();
        assertThat(mMarathonQuestStageList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllMMarathonQuestStages() throws Exception {
        // Initialize the database
        mMarathonQuestStageRepository.saveAndFlush(mMarathonQuestStage);

        // Get all the mMarathonQuestStageList
        restMMarathonQuestStageMockMvc.perform(get("/api/m-marathon-quest-stages?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(mMarathonQuestStage.getId().intValue())))
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
    public void getMMarathonQuestStage() throws Exception {
        // Initialize the database
        mMarathonQuestStageRepository.saveAndFlush(mMarathonQuestStage);

        // Get the mMarathonQuestStage
        restMMarathonQuestStageMockMvc.perform(get("/api/m-marathon-quest-stages/{id}", mMarathonQuestStage.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(mMarathonQuestStage.getId().intValue()))
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
    public void getAllMMarathonQuestStagesByWorldIdIsEqualToSomething() throws Exception {
        // Initialize the database
        mMarathonQuestStageRepository.saveAndFlush(mMarathonQuestStage);

        // Get all the mMarathonQuestStageList where worldId equals to DEFAULT_WORLD_ID
        defaultMMarathonQuestStageShouldBeFound("worldId.equals=" + DEFAULT_WORLD_ID);

        // Get all the mMarathonQuestStageList where worldId equals to UPDATED_WORLD_ID
        defaultMMarathonQuestStageShouldNotBeFound("worldId.equals=" + UPDATED_WORLD_ID);
    }

    @Test
    @Transactional
    public void getAllMMarathonQuestStagesByWorldIdIsInShouldWork() throws Exception {
        // Initialize the database
        mMarathonQuestStageRepository.saveAndFlush(mMarathonQuestStage);

        // Get all the mMarathonQuestStageList where worldId in DEFAULT_WORLD_ID or UPDATED_WORLD_ID
        defaultMMarathonQuestStageShouldBeFound("worldId.in=" + DEFAULT_WORLD_ID + "," + UPDATED_WORLD_ID);

        // Get all the mMarathonQuestStageList where worldId equals to UPDATED_WORLD_ID
        defaultMMarathonQuestStageShouldNotBeFound("worldId.in=" + UPDATED_WORLD_ID);
    }

    @Test
    @Transactional
    public void getAllMMarathonQuestStagesByWorldIdIsNullOrNotNull() throws Exception {
        // Initialize the database
        mMarathonQuestStageRepository.saveAndFlush(mMarathonQuestStage);

        // Get all the mMarathonQuestStageList where worldId is not null
        defaultMMarathonQuestStageShouldBeFound("worldId.specified=true");

        // Get all the mMarathonQuestStageList where worldId is null
        defaultMMarathonQuestStageShouldNotBeFound("worldId.specified=false");
    }

    @Test
    @Transactional
    public void getAllMMarathonQuestStagesByWorldIdIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mMarathonQuestStageRepository.saveAndFlush(mMarathonQuestStage);

        // Get all the mMarathonQuestStageList where worldId greater than or equals to DEFAULT_WORLD_ID
        defaultMMarathonQuestStageShouldBeFound("worldId.greaterOrEqualThan=" + DEFAULT_WORLD_ID);

        // Get all the mMarathonQuestStageList where worldId greater than or equals to UPDATED_WORLD_ID
        defaultMMarathonQuestStageShouldNotBeFound("worldId.greaterOrEqualThan=" + UPDATED_WORLD_ID);
    }

    @Test
    @Transactional
    public void getAllMMarathonQuestStagesByWorldIdIsLessThanSomething() throws Exception {
        // Initialize the database
        mMarathonQuestStageRepository.saveAndFlush(mMarathonQuestStage);

        // Get all the mMarathonQuestStageList where worldId less than or equals to DEFAULT_WORLD_ID
        defaultMMarathonQuestStageShouldNotBeFound("worldId.lessThan=" + DEFAULT_WORLD_ID);

        // Get all the mMarathonQuestStageList where worldId less than or equals to UPDATED_WORLD_ID
        defaultMMarathonQuestStageShouldBeFound("worldId.lessThan=" + UPDATED_WORLD_ID);
    }


    @Test
    @Transactional
    public void getAllMMarathonQuestStagesByNumberIsEqualToSomething() throws Exception {
        // Initialize the database
        mMarathonQuestStageRepository.saveAndFlush(mMarathonQuestStage);

        // Get all the mMarathonQuestStageList where number equals to DEFAULT_NUMBER
        defaultMMarathonQuestStageShouldBeFound("number.equals=" + DEFAULT_NUMBER);

        // Get all the mMarathonQuestStageList where number equals to UPDATED_NUMBER
        defaultMMarathonQuestStageShouldNotBeFound("number.equals=" + UPDATED_NUMBER);
    }

    @Test
    @Transactional
    public void getAllMMarathonQuestStagesByNumberIsInShouldWork() throws Exception {
        // Initialize the database
        mMarathonQuestStageRepository.saveAndFlush(mMarathonQuestStage);

        // Get all the mMarathonQuestStageList where number in DEFAULT_NUMBER or UPDATED_NUMBER
        defaultMMarathonQuestStageShouldBeFound("number.in=" + DEFAULT_NUMBER + "," + UPDATED_NUMBER);

        // Get all the mMarathonQuestStageList where number equals to UPDATED_NUMBER
        defaultMMarathonQuestStageShouldNotBeFound("number.in=" + UPDATED_NUMBER);
    }

    @Test
    @Transactional
    public void getAllMMarathonQuestStagesByNumberIsNullOrNotNull() throws Exception {
        // Initialize the database
        mMarathonQuestStageRepository.saveAndFlush(mMarathonQuestStage);

        // Get all the mMarathonQuestStageList where number is not null
        defaultMMarathonQuestStageShouldBeFound("number.specified=true");

        // Get all the mMarathonQuestStageList where number is null
        defaultMMarathonQuestStageShouldNotBeFound("number.specified=false");
    }

    @Test
    @Transactional
    public void getAllMMarathonQuestStagesByNumberIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mMarathonQuestStageRepository.saveAndFlush(mMarathonQuestStage);

        // Get all the mMarathonQuestStageList where number greater than or equals to DEFAULT_NUMBER
        defaultMMarathonQuestStageShouldBeFound("number.greaterOrEqualThan=" + DEFAULT_NUMBER);

        // Get all the mMarathonQuestStageList where number greater than or equals to UPDATED_NUMBER
        defaultMMarathonQuestStageShouldNotBeFound("number.greaterOrEqualThan=" + UPDATED_NUMBER);
    }

    @Test
    @Transactional
    public void getAllMMarathonQuestStagesByNumberIsLessThanSomething() throws Exception {
        // Initialize the database
        mMarathonQuestStageRepository.saveAndFlush(mMarathonQuestStage);

        // Get all the mMarathonQuestStageList where number less than or equals to DEFAULT_NUMBER
        defaultMMarathonQuestStageShouldNotBeFound("number.lessThan=" + DEFAULT_NUMBER);

        // Get all the mMarathonQuestStageList where number less than or equals to UPDATED_NUMBER
        defaultMMarathonQuestStageShouldBeFound("number.lessThan=" + UPDATED_NUMBER);
    }


    @Test
    @Transactional
    public void getAllMMarathonQuestStagesByDifficultyIsEqualToSomething() throws Exception {
        // Initialize the database
        mMarathonQuestStageRepository.saveAndFlush(mMarathonQuestStage);

        // Get all the mMarathonQuestStageList where difficulty equals to DEFAULT_DIFFICULTY
        defaultMMarathonQuestStageShouldBeFound("difficulty.equals=" + DEFAULT_DIFFICULTY);

        // Get all the mMarathonQuestStageList where difficulty equals to UPDATED_DIFFICULTY
        defaultMMarathonQuestStageShouldNotBeFound("difficulty.equals=" + UPDATED_DIFFICULTY);
    }

    @Test
    @Transactional
    public void getAllMMarathonQuestStagesByDifficultyIsInShouldWork() throws Exception {
        // Initialize the database
        mMarathonQuestStageRepository.saveAndFlush(mMarathonQuestStage);

        // Get all the mMarathonQuestStageList where difficulty in DEFAULT_DIFFICULTY or UPDATED_DIFFICULTY
        defaultMMarathonQuestStageShouldBeFound("difficulty.in=" + DEFAULT_DIFFICULTY + "," + UPDATED_DIFFICULTY);

        // Get all the mMarathonQuestStageList where difficulty equals to UPDATED_DIFFICULTY
        defaultMMarathonQuestStageShouldNotBeFound("difficulty.in=" + UPDATED_DIFFICULTY);
    }

    @Test
    @Transactional
    public void getAllMMarathonQuestStagesByDifficultyIsNullOrNotNull() throws Exception {
        // Initialize the database
        mMarathonQuestStageRepository.saveAndFlush(mMarathonQuestStage);

        // Get all the mMarathonQuestStageList where difficulty is not null
        defaultMMarathonQuestStageShouldBeFound("difficulty.specified=true");

        // Get all the mMarathonQuestStageList where difficulty is null
        defaultMMarathonQuestStageShouldNotBeFound("difficulty.specified=false");
    }

    @Test
    @Transactional
    public void getAllMMarathonQuestStagesByDifficultyIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mMarathonQuestStageRepository.saveAndFlush(mMarathonQuestStage);

        // Get all the mMarathonQuestStageList where difficulty greater than or equals to DEFAULT_DIFFICULTY
        defaultMMarathonQuestStageShouldBeFound("difficulty.greaterOrEqualThan=" + DEFAULT_DIFFICULTY);

        // Get all the mMarathonQuestStageList where difficulty greater than or equals to UPDATED_DIFFICULTY
        defaultMMarathonQuestStageShouldNotBeFound("difficulty.greaterOrEqualThan=" + UPDATED_DIFFICULTY);
    }

    @Test
    @Transactional
    public void getAllMMarathonQuestStagesByDifficultyIsLessThanSomething() throws Exception {
        // Initialize the database
        mMarathonQuestStageRepository.saveAndFlush(mMarathonQuestStage);

        // Get all the mMarathonQuestStageList where difficulty less than or equals to DEFAULT_DIFFICULTY
        defaultMMarathonQuestStageShouldNotBeFound("difficulty.lessThan=" + DEFAULT_DIFFICULTY);

        // Get all the mMarathonQuestStageList where difficulty less than or equals to UPDATED_DIFFICULTY
        defaultMMarathonQuestStageShouldBeFound("difficulty.lessThan=" + UPDATED_DIFFICULTY);
    }


    @Test
    @Transactional
    public void getAllMMarathonQuestStagesByStageUnlockPatternIsEqualToSomething() throws Exception {
        // Initialize the database
        mMarathonQuestStageRepository.saveAndFlush(mMarathonQuestStage);

        // Get all the mMarathonQuestStageList where stageUnlockPattern equals to DEFAULT_STAGE_UNLOCK_PATTERN
        defaultMMarathonQuestStageShouldBeFound("stageUnlockPattern.equals=" + DEFAULT_STAGE_UNLOCK_PATTERN);

        // Get all the mMarathonQuestStageList where stageUnlockPattern equals to UPDATED_STAGE_UNLOCK_PATTERN
        defaultMMarathonQuestStageShouldNotBeFound("stageUnlockPattern.equals=" + UPDATED_STAGE_UNLOCK_PATTERN);
    }

    @Test
    @Transactional
    public void getAllMMarathonQuestStagesByStageUnlockPatternIsInShouldWork() throws Exception {
        // Initialize the database
        mMarathonQuestStageRepository.saveAndFlush(mMarathonQuestStage);

        // Get all the mMarathonQuestStageList where stageUnlockPattern in DEFAULT_STAGE_UNLOCK_PATTERN or UPDATED_STAGE_UNLOCK_PATTERN
        defaultMMarathonQuestStageShouldBeFound("stageUnlockPattern.in=" + DEFAULT_STAGE_UNLOCK_PATTERN + "," + UPDATED_STAGE_UNLOCK_PATTERN);

        // Get all the mMarathonQuestStageList where stageUnlockPattern equals to UPDATED_STAGE_UNLOCK_PATTERN
        defaultMMarathonQuestStageShouldNotBeFound("stageUnlockPattern.in=" + UPDATED_STAGE_UNLOCK_PATTERN);
    }

    @Test
    @Transactional
    public void getAllMMarathonQuestStagesByStageUnlockPatternIsNullOrNotNull() throws Exception {
        // Initialize the database
        mMarathonQuestStageRepository.saveAndFlush(mMarathonQuestStage);

        // Get all the mMarathonQuestStageList where stageUnlockPattern is not null
        defaultMMarathonQuestStageShouldBeFound("stageUnlockPattern.specified=true");

        // Get all the mMarathonQuestStageList where stageUnlockPattern is null
        defaultMMarathonQuestStageShouldNotBeFound("stageUnlockPattern.specified=false");
    }

    @Test
    @Transactional
    public void getAllMMarathonQuestStagesByStageUnlockPatternIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mMarathonQuestStageRepository.saveAndFlush(mMarathonQuestStage);

        // Get all the mMarathonQuestStageList where stageUnlockPattern greater than or equals to DEFAULT_STAGE_UNLOCK_PATTERN
        defaultMMarathonQuestStageShouldBeFound("stageUnlockPattern.greaterOrEqualThan=" + DEFAULT_STAGE_UNLOCK_PATTERN);

        // Get all the mMarathonQuestStageList where stageUnlockPattern greater than or equals to UPDATED_STAGE_UNLOCK_PATTERN
        defaultMMarathonQuestStageShouldNotBeFound("stageUnlockPattern.greaterOrEqualThan=" + UPDATED_STAGE_UNLOCK_PATTERN);
    }

    @Test
    @Transactional
    public void getAllMMarathonQuestStagesByStageUnlockPatternIsLessThanSomething() throws Exception {
        // Initialize the database
        mMarathonQuestStageRepository.saveAndFlush(mMarathonQuestStage);

        // Get all the mMarathonQuestStageList where stageUnlockPattern less than or equals to DEFAULT_STAGE_UNLOCK_PATTERN
        defaultMMarathonQuestStageShouldNotBeFound("stageUnlockPattern.lessThan=" + DEFAULT_STAGE_UNLOCK_PATTERN);

        // Get all the mMarathonQuestStageList where stageUnlockPattern less than or equals to UPDATED_STAGE_UNLOCK_PATTERN
        defaultMMarathonQuestStageShouldBeFound("stageUnlockPattern.lessThan=" + UPDATED_STAGE_UNLOCK_PATTERN);
    }


    @Test
    @Transactional
    public void getAllMMarathonQuestStagesByStoryOnlyIsEqualToSomething() throws Exception {
        // Initialize the database
        mMarathonQuestStageRepository.saveAndFlush(mMarathonQuestStage);

        // Get all the mMarathonQuestStageList where storyOnly equals to DEFAULT_STORY_ONLY
        defaultMMarathonQuestStageShouldBeFound("storyOnly.equals=" + DEFAULT_STORY_ONLY);

        // Get all the mMarathonQuestStageList where storyOnly equals to UPDATED_STORY_ONLY
        defaultMMarathonQuestStageShouldNotBeFound("storyOnly.equals=" + UPDATED_STORY_ONLY);
    }

    @Test
    @Transactional
    public void getAllMMarathonQuestStagesByStoryOnlyIsInShouldWork() throws Exception {
        // Initialize the database
        mMarathonQuestStageRepository.saveAndFlush(mMarathonQuestStage);

        // Get all the mMarathonQuestStageList where storyOnly in DEFAULT_STORY_ONLY or UPDATED_STORY_ONLY
        defaultMMarathonQuestStageShouldBeFound("storyOnly.in=" + DEFAULT_STORY_ONLY + "," + UPDATED_STORY_ONLY);

        // Get all the mMarathonQuestStageList where storyOnly equals to UPDATED_STORY_ONLY
        defaultMMarathonQuestStageShouldNotBeFound("storyOnly.in=" + UPDATED_STORY_ONLY);
    }

    @Test
    @Transactional
    public void getAllMMarathonQuestStagesByStoryOnlyIsNullOrNotNull() throws Exception {
        // Initialize the database
        mMarathonQuestStageRepository.saveAndFlush(mMarathonQuestStage);

        // Get all the mMarathonQuestStageList where storyOnly is not null
        defaultMMarathonQuestStageShouldBeFound("storyOnly.specified=true");

        // Get all the mMarathonQuestStageList where storyOnly is null
        defaultMMarathonQuestStageShouldNotBeFound("storyOnly.specified=false");
    }

    @Test
    @Transactional
    public void getAllMMarathonQuestStagesByStoryOnlyIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mMarathonQuestStageRepository.saveAndFlush(mMarathonQuestStage);

        // Get all the mMarathonQuestStageList where storyOnly greater than or equals to DEFAULT_STORY_ONLY
        defaultMMarathonQuestStageShouldBeFound("storyOnly.greaterOrEqualThan=" + DEFAULT_STORY_ONLY);

        // Get all the mMarathonQuestStageList where storyOnly greater than or equals to UPDATED_STORY_ONLY
        defaultMMarathonQuestStageShouldNotBeFound("storyOnly.greaterOrEqualThan=" + UPDATED_STORY_ONLY);
    }

    @Test
    @Transactional
    public void getAllMMarathonQuestStagesByStoryOnlyIsLessThanSomething() throws Exception {
        // Initialize the database
        mMarathonQuestStageRepository.saveAndFlush(mMarathonQuestStage);

        // Get all the mMarathonQuestStageList where storyOnly less than or equals to DEFAULT_STORY_ONLY
        defaultMMarathonQuestStageShouldNotBeFound("storyOnly.lessThan=" + DEFAULT_STORY_ONLY);

        // Get all the mMarathonQuestStageList where storyOnly less than or equals to UPDATED_STORY_ONLY
        defaultMMarathonQuestStageShouldBeFound("storyOnly.lessThan=" + UPDATED_STORY_ONLY);
    }


    @Test
    @Transactional
    public void getAllMMarathonQuestStagesByFirstHalfNpcDeckIdIsEqualToSomething() throws Exception {
        // Initialize the database
        mMarathonQuestStageRepository.saveAndFlush(mMarathonQuestStage);

        // Get all the mMarathonQuestStageList where firstHalfNpcDeckId equals to DEFAULT_FIRST_HALF_NPC_DECK_ID
        defaultMMarathonQuestStageShouldBeFound("firstHalfNpcDeckId.equals=" + DEFAULT_FIRST_HALF_NPC_DECK_ID);

        // Get all the mMarathonQuestStageList where firstHalfNpcDeckId equals to UPDATED_FIRST_HALF_NPC_DECK_ID
        defaultMMarathonQuestStageShouldNotBeFound("firstHalfNpcDeckId.equals=" + UPDATED_FIRST_HALF_NPC_DECK_ID);
    }

    @Test
    @Transactional
    public void getAllMMarathonQuestStagesByFirstHalfNpcDeckIdIsInShouldWork() throws Exception {
        // Initialize the database
        mMarathonQuestStageRepository.saveAndFlush(mMarathonQuestStage);

        // Get all the mMarathonQuestStageList where firstHalfNpcDeckId in DEFAULT_FIRST_HALF_NPC_DECK_ID or UPDATED_FIRST_HALF_NPC_DECK_ID
        defaultMMarathonQuestStageShouldBeFound("firstHalfNpcDeckId.in=" + DEFAULT_FIRST_HALF_NPC_DECK_ID + "," + UPDATED_FIRST_HALF_NPC_DECK_ID);

        // Get all the mMarathonQuestStageList where firstHalfNpcDeckId equals to UPDATED_FIRST_HALF_NPC_DECK_ID
        defaultMMarathonQuestStageShouldNotBeFound("firstHalfNpcDeckId.in=" + UPDATED_FIRST_HALF_NPC_DECK_ID);
    }

    @Test
    @Transactional
    public void getAllMMarathonQuestStagesByFirstHalfNpcDeckIdIsNullOrNotNull() throws Exception {
        // Initialize the database
        mMarathonQuestStageRepository.saveAndFlush(mMarathonQuestStage);

        // Get all the mMarathonQuestStageList where firstHalfNpcDeckId is not null
        defaultMMarathonQuestStageShouldBeFound("firstHalfNpcDeckId.specified=true");

        // Get all the mMarathonQuestStageList where firstHalfNpcDeckId is null
        defaultMMarathonQuestStageShouldNotBeFound("firstHalfNpcDeckId.specified=false");
    }

    @Test
    @Transactional
    public void getAllMMarathonQuestStagesByFirstHalfNpcDeckIdIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mMarathonQuestStageRepository.saveAndFlush(mMarathonQuestStage);

        // Get all the mMarathonQuestStageList where firstHalfNpcDeckId greater than or equals to DEFAULT_FIRST_HALF_NPC_DECK_ID
        defaultMMarathonQuestStageShouldBeFound("firstHalfNpcDeckId.greaterOrEqualThan=" + DEFAULT_FIRST_HALF_NPC_DECK_ID);

        // Get all the mMarathonQuestStageList where firstHalfNpcDeckId greater than or equals to UPDATED_FIRST_HALF_NPC_DECK_ID
        defaultMMarathonQuestStageShouldNotBeFound("firstHalfNpcDeckId.greaterOrEqualThan=" + UPDATED_FIRST_HALF_NPC_DECK_ID);
    }

    @Test
    @Transactional
    public void getAllMMarathonQuestStagesByFirstHalfNpcDeckIdIsLessThanSomething() throws Exception {
        // Initialize the database
        mMarathonQuestStageRepository.saveAndFlush(mMarathonQuestStage);

        // Get all the mMarathonQuestStageList where firstHalfNpcDeckId less than or equals to DEFAULT_FIRST_HALF_NPC_DECK_ID
        defaultMMarathonQuestStageShouldNotBeFound("firstHalfNpcDeckId.lessThan=" + DEFAULT_FIRST_HALF_NPC_DECK_ID);

        // Get all the mMarathonQuestStageList where firstHalfNpcDeckId less than or equals to UPDATED_FIRST_HALF_NPC_DECK_ID
        defaultMMarathonQuestStageShouldBeFound("firstHalfNpcDeckId.lessThan=" + UPDATED_FIRST_HALF_NPC_DECK_ID);
    }


    @Test
    @Transactional
    public void getAllMMarathonQuestStagesByFirstHalfEnvironmentIdIsEqualToSomething() throws Exception {
        // Initialize the database
        mMarathonQuestStageRepository.saveAndFlush(mMarathonQuestStage);

        // Get all the mMarathonQuestStageList where firstHalfEnvironmentId equals to DEFAULT_FIRST_HALF_ENVIRONMENT_ID
        defaultMMarathonQuestStageShouldBeFound("firstHalfEnvironmentId.equals=" + DEFAULT_FIRST_HALF_ENVIRONMENT_ID);

        // Get all the mMarathonQuestStageList where firstHalfEnvironmentId equals to UPDATED_FIRST_HALF_ENVIRONMENT_ID
        defaultMMarathonQuestStageShouldNotBeFound("firstHalfEnvironmentId.equals=" + UPDATED_FIRST_HALF_ENVIRONMENT_ID);
    }

    @Test
    @Transactional
    public void getAllMMarathonQuestStagesByFirstHalfEnvironmentIdIsInShouldWork() throws Exception {
        // Initialize the database
        mMarathonQuestStageRepository.saveAndFlush(mMarathonQuestStage);

        // Get all the mMarathonQuestStageList where firstHalfEnvironmentId in DEFAULT_FIRST_HALF_ENVIRONMENT_ID or UPDATED_FIRST_HALF_ENVIRONMENT_ID
        defaultMMarathonQuestStageShouldBeFound("firstHalfEnvironmentId.in=" + DEFAULT_FIRST_HALF_ENVIRONMENT_ID + "," + UPDATED_FIRST_HALF_ENVIRONMENT_ID);

        // Get all the mMarathonQuestStageList where firstHalfEnvironmentId equals to UPDATED_FIRST_HALF_ENVIRONMENT_ID
        defaultMMarathonQuestStageShouldNotBeFound("firstHalfEnvironmentId.in=" + UPDATED_FIRST_HALF_ENVIRONMENT_ID);
    }

    @Test
    @Transactional
    public void getAllMMarathonQuestStagesByFirstHalfEnvironmentIdIsNullOrNotNull() throws Exception {
        // Initialize the database
        mMarathonQuestStageRepository.saveAndFlush(mMarathonQuestStage);

        // Get all the mMarathonQuestStageList where firstHalfEnvironmentId is not null
        defaultMMarathonQuestStageShouldBeFound("firstHalfEnvironmentId.specified=true");

        // Get all the mMarathonQuestStageList where firstHalfEnvironmentId is null
        defaultMMarathonQuestStageShouldNotBeFound("firstHalfEnvironmentId.specified=false");
    }

    @Test
    @Transactional
    public void getAllMMarathonQuestStagesByFirstHalfEnvironmentIdIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mMarathonQuestStageRepository.saveAndFlush(mMarathonQuestStage);

        // Get all the mMarathonQuestStageList where firstHalfEnvironmentId greater than or equals to DEFAULT_FIRST_HALF_ENVIRONMENT_ID
        defaultMMarathonQuestStageShouldBeFound("firstHalfEnvironmentId.greaterOrEqualThan=" + DEFAULT_FIRST_HALF_ENVIRONMENT_ID);

        // Get all the mMarathonQuestStageList where firstHalfEnvironmentId greater than or equals to UPDATED_FIRST_HALF_ENVIRONMENT_ID
        defaultMMarathonQuestStageShouldNotBeFound("firstHalfEnvironmentId.greaterOrEqualThan=" + UPDATED_FIRST_HALF_ENVIRONMENT_ID);
    }

    @Test
    @Transactional
    public void getAllMMarathonQuestStagesByFirstHalfEnvironmentIdIsLessThanSomething() throws Exception {
        // Initialize the database
        mMarathonQuestStageRepository.saveAndFlush(mMarathonQuestStage);

        // Get all the mMarathonQuestStageList where firstHalfEnvironmentId less than or equals to DEFAULT_FIRST_HALF_ENVIRONMENT_ID
        defaultMMarathonQuestStageShouldNotBeFound("firstHalfEnvironmentId.lessThan=" + DEFAULT_FIRST_HALF_ENVIRONMENT_ID);

        // Get all the mMarathonQuestStageList where firstHalfEnvironmentId less than or equals to UPDATED_FIRST_HALF_ENVIRONMENT_ID
        defaultMMarathonQuestStageShouldBeFound("firstHalfEnvironmentId.lessThan=" + UPDATED_FIRST_HALF_ENVIRONMENT_ID);
    }


    @Test
    @Transactional
    public void getAllMMarathonQuestStagesBySecondHalfNpcDeckIdIsEqualToSomething() throws Exception {
        // Initialize the database
        mMarathonQuestStageRepository.saveAndFlush(mMarathonQuestStage);

        // Get all the mMarathonQuestStageList where secondHalfNpcDeckId equals to DEFAULT_SECOND_HALF_NPC_DECK_ID
        defaultMMarathonQuestStageShouldBeFound("secondHalfNpcDeckId.equals=" + DEFAULT_SECOND_HALF_NPC_DECK_ID);

        // Get all the mMarathonQuestStageList where secondHalfNpcDeckId equals to UPDATED_SECOND_HALF_NPC_DECK_ID
        defaultMMarathonQuestStageShouldNotBeFound("secondHalfNpcDeckId.equals=" + UPDATED_SECOND_HALF_NPC_DECK_ID);
    }

    @Test
    @Transactional
    public void getAllMMarathonQuestStagesBySecondHalfNpcDeckIdIsInShouldWork() throws Exception {
        // Initialize the database
        mMarathonQuestStageRepository.saveAndFlush(mMarathonQuestStage);

        // Get all the mMarathonQuestStageList where secondHalfNpcDeckId in DEFAULT_SECOND_HALF_NPC_DECK_ID or UPDATED_SECOND_HALF_NPC_DECK_ID
        defaultMMarathonQuestStageShouldBeFound("secondHalfNpcDeckId.in=" + DEFAULT_SECOND_HALF_NPC_DECK_ID + "," + UPDATED_SECOND_HALF_NPC_DECK_ID);

        // Get all the mMarathonQuestStageList where secondHalfNpcDeckId equals to UPDATED_SECOND_HALF_NPC_DECK_ID
        defaultMMarathonQuestStageShouldNotBeFound("secondHalfNpcDeckId.in=" + UPDATED_SECOND_HALF_NPC_DECK_ID);
    }

    @Test
    @Transactional
    public void getAllMMarathonQuestStagesBySecondHalfNpcDeckIdIsNullOrNotNull() throws Exception {
        // Initialize the database
        mMarathonQuestStageRepository.saveAndFlush(mMarathonQuestStage);

        // Get all the mMarathonQuestStageList where secondHalfNpcDeckId is not null
        defaultMMarathonQuestStageShouldBeFound("secondHalfNpcDeckId.specified=true");

        // Get all the mMarathonQuestStageList where secondHalfNpcDeckId is null
        defaultMMarathonQuestStageShouldNotBeFound("secondHalfNpcDeckId.specified=false");
    }

    @Test
    @Transactional
    public void getAllMMarathonQuestStagesBySecondHalfNpcDeckIdIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mMarathonQuestStageRepository.saveAndFlush(mMarathonQuestStage);

        // Get all the mMarathonQuestStageList where secondHalfNpcDeckId greater than or equals to DEFAULT_SECOND_HALF_NPC_DECK_ID
        defaultMMarathonQuestStageShouldBeFound("secondHalfNpcDeckId.greaterOrEqualThan=" + DEFAULT_SECOND_HALF_NPC_DECK_ID);

        // Get all the mMarathonQuestStageList where secondHalfNpcDeckId greater than or equals to UPDATED_SECOND_HALF_NPC_DECK_ID
        defaultMMarathonQuestStageShouldNotBeFound("secondHalfNpcDeckId.greaterOrEqualThan=" + UPDATED_SECOND_HALF_NPC_DECK_ID);
    }

    @Test
    @Transactional
    public void getAllMMarathonQuestStagesBySecondHalfNpcDeckIdIsLessThanSomething() throws Exception {
        // Initialize the database
        mMarathonQuestStageRepository.saveAndFlush(mMarathonQuestStage);

        // Get all the mMarathonQuestStageList where secondHalfNpcDeckId less than or equals to DEFAULT_SECOND_HALF_NPC_DECK_ID
        defaultMMarathonQuestStageShouldNotBeFound("secondHalfNpcDeckId.lessThan=" + DEFAULT_SECOND_HALF_NPC_DECK_ID);

        // Get all the mMarathonQuestStageList where secondHalfNpcDeckId less than or equals to UPDATED_SECOND_HALF_NPC_DECK_ID
        defaultMMarathonQuestStageShouldBeFound("secondHalfNpcDeckId.lessThan=" + UPDATED_SECOND_HALF_NPC_DECK_ID);
    }


    @Test
    @Transactional
    public void getAllMMarathonQuestStagesBySecondHalfEnvironmentIdIsEqualToSomething() throws Exception {
        // Initialize the database
        mMarathonQuestStageRepository.saveAndFlush(mMarathonQuestStage);

        // Get all the mMarathonQuestStageList where secondHalfEnvironmentId equals to DEFAULT_SECOND_HALF_ENVIRONMENT_ID
        defaultMMarathonQuestStageShouldBeFound("secondHalfEnvironmentId.equals=" + DEFAULT_SECOND_HALF_ENVIRONMENT_ID);

        // Get all the mMarathonQuestStageList where secondHalfEnvironmentId equals to UPDATED_SECOND_HALF_ENVIRONMENT_ID
        defaultMMarathonQuestStageShouldNotBeFound("secondHalfEnvironmentId.equals=" + UPDATED_SECOND_HALF_ENVIRONMENT_ID);
    }

    @Test
    @Transactional
    public void getAllMMarathonQuestStagesBySecondHalfEnvironmentIdIsInShouldWork() throws Exception {
        // Initialize the database
        mMarathonQuestStageRepository.saveAndFlush(mMarathonQuestStage);

        // Get all the mMarathonQuestStageList where secondHalfEnvironmentId in DEFAULT_SECOND_HALF_ENVIRONMENT_ID or UPDATED_SECOND_HALF_ENVIRONMENT_ID
        defaultMMarathonQuestStageShouldBeFound("secondHalfEnvironmentId.in=" + DEFAULT_SECOND_HALF_ENVIRONMENT_ID + "," + UPDATED_SECOND_HALF_ENVIRONMENT_ID);

        // Get all the mMarathonQuestStageList where secondHalfEnvironmentId equals to UPDATED_SECOND_HALF_ENVIRONMENT_ID
        defaultMMarathonQuestStageShouldNotBeFound("secondHalfEnvironmentId.in=" + UPDATED_SECOND_HALF_ENVIRONMENT_ID);
    }

    @Test
    @Transactional
    public void getAllMMarathonQuestStagesBySecondHalfEnvironmentIdIsNullOrNotNull() throws Exception {
        // Initialize the database
        mMarathonQuestStageRepository.saveAndFlush(mMarathonQuestStage);

        // Get all the mMarathonQuestStageList where secondHalfEnvironmentId is not null
        defaultMMarathonQuestStageShouldBeFound("secondHalfEnvironmentId.specified=true");

        // Get all the mMarathonQuestStageList where secondHalfEnvironmentId is null
        defaultMMarathonQuestStageShouldNotBeFound("secondHalfEnvironmentId.specified=false");
    }

    @Test
    @Transactional
    public void getAllMMarathonQuestStagesBySecondHalfEnvironmentIdIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mMarathonQuestStageRepository.saveAndFlush(mMarathonQuestStage);

        // Get all the mMarathonQuestStageList where secondHalfEnvironmentId greater than or equals to DEFAULT_SECOND_HALF_ENVIRONMENT_ID
        defaultMMarathonQuestStageShouldBeFound("secondHalfEnvironmentId.greaterOrEqualThan=" + DEFAULT_SECOND_HALF_ENVIRONMENT_ID);

        // Get all the mMarathonQuestStageList where secondHalfEnvironmentId greater than or equals to UPDATED_SECOND_HALF_ENVIRONMENT_ID
        defaultMMarathonQuestStageShouldNotBeFound("secondHalfEnvironmentId.greaterOrEqualThan=" + UPDATED_SECOND_HALF_ENVIRONMENT_ID);
    }

    @Test
    @Transactional
    public void getAllMMarathonQuestStagesBySecondHalfEnvironmentIdIsLessThanSomething() throws Exception {
        // Initialize the database
        mMarathonQuestStageRepository.saveAndFlush(mMarathonQuestStage);

        // Get all the mMarathonQuestStageList where secondHalfEnvironmentId less than or equals to DEFAULT_SECOND_HALF_ENVIRONMENT_ID
        defaultMMarathonQuestStageShouldNotBeFound("secondHalfEnvironmentId.lessThan=" + DEFAULT_SECOND_HALF_ENVIRONMENT_ID);

        // Get all the mMarathonQuestStageList where secondHalfEnvironmentId less than or equals to UPDATED_SECOND_HALF_ENVIRONMENT_ID
        defaultMMarathonQuestStageShouldBeFound("secondHalfEnvironmentId.lessThan=" + UPDATED_SECOND_HALF_ENVIRONMENT_ID);
    }


    @Test
    @Transactional
    public void getAllMMarathonQuestStagesByExtraFirstHalfNpcDeckIdIsEqualToSomething() throws Exception {
        // Initialize the database
        mMarathonQuestStageRepository.saveAndFlush(mMarathonQuestStage);

        // Get all the mMarathonQuestStageList where extraFirstHalfNpcDeckId equals to DEFAULT_EXTRA_FIRST_HALF_NPC_DECK_ID
        defaultMMarathonQuestStageShouldBeFound("extraFirstHalfNpcDeckId.equals=" + DEFAULT_EXTRA_FIRST_HALF_NPC_DECK_ID);

        // Get all the mMarathonQuestStageList where extraFirstHalfNpcDeckId equals to UPDATED_EXTRA_FIRST_HALF_NPC_DECK_ID
        defaultMMarathonQuestStageShouldNotBeFound("extraFirstHalfNpcDeckId.equals=" + UPDATED_EXTRA_FIRST_HALF_NPC_DECK_ID);
    }

    @Test
    @Transactional
    public void getAllMMarathonQuestStagesByExtraFirstHalfNpcDeckIdIsInShouldWork() throws Exception {
        // Initialize the database
        mMarathonQuestStageRepository.saveAndFlush(mMarathonQuestStage);

        // Get all the mMarathonQuestStageList where extraFirstHalfNpcDeckId in DEFAULT_EXTRA_FIRST_HALF_NPC_DECK_ID or UPDATED_EXTRA_FIRST_HALF_NPC_DECK_ID
        defaultMMarathonQuestStageShouldBeFound("extraFirstHalfNpcDeckId.in=" + DEFAULT_EXTRA_FIRST_HALF_NPC_DECK_ID + "," + UPDATED_EXTRA_FIRST_HALF_NPC_DECK_ID);

        // Get all the mMarathonQuestStageList where extraFirstHalfNpcDeckId equals to UPDATED_EXTRA_FIRST_HALF_NPC_DECK_ID
        defaultMMarathonQuestStageShouldNotBeFound("extraFirstHalfNpcDeckId.in=" + UPDATED_EXTRA_FIRST_HALF_NPC_DECK_ID);
    }

    @Test
    @Transactional
    public void getAllMMarathonQuestStagesByExtraFirstHalfNpcDeckIdIsNullOrNotNull() throws Exception {
        // Initialize the database
        mMarathonQuestStageRepository.saveAndFlush(mMarathonQuestStage);

        // Get all the mMarathonQuestStageList where extraFirstHalfNpcDeckId is not null
        defaultMMarathonQuestStageShouldBeFound("extraFirstHalfNpcDeckId.specified=true");

        // Get all the mMarathonQuestStageList where extraFirstHalfNpcDeckId is null
        defaultMMarathonQuestStageShouldNotBeFound("extraFirstHalfNpcDeckId.specified=false");
    }

    @Test
    @Transactional
    public void getAllMMarathonQuestStagesByExtraFirstHalfNpcDeckIdIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mMarathonQuestStageRepository.saveAndFlush(mMarathonQuestStage);

        // Get all the mMarathonQuestStageList where extraFirstHalfNpcDeckId greater than or equals to DEFAULT_EXTRA_FIRST_HALF_NPC_DECK_ID
        defaultMMarathonQuestStageShouldBeFound("extraFirstHalfNpcDeckId.greaterOrEqualThan=" + DEFAULT_EXTRA_FIRST_HALF_NPC_DECK_ID);

        // Get all the mMarathonQuestStageList where extraFirstHalfNpcDeckId greater than or equals to UPDATED_EXTRA_FIRST_HALF_NPC_DECK_ID
        defaultMMarathonQuestStageShouldNotBeFound("extraFirstHalfNpcDeckId.greaterOrEqualThan=" + UPDATED_EXTRA_FIRST_HALF_NPC_DECK_ID);
    }

    @Test
    @Transactional
    public void getAllMMarathonQuestStagesByExtraFirstHalfNpcDeckIdIsLessThanSomething() throws Exception {
        // Initialize the database
        mMarathonQuestStageRepository.saveAndFlush(mMarathonQuestStage);

        // Get all the mMarathonQuestStageList where extraFirstHalfNpcDeckId less than or equals to DEFAULT_EXTRA_FIRST_HALF_NPC_DECK_ID
        defaultMMarathonQuestStageShouldNotBeFound("extraFirstHalfNpcDeckId.lessThan=" + DEFAULT_EXTRA_FIRST_HALF_NPC_DECK_ID);

        // Get all the mMarathonQuestStageList where extraFirstHalfNpcDeckId less than or equals to UPDATED_EXTRA_FIRST_HALF_NPC_DECK_ID
        defaultMMarathonQuestStageShouldBeFound("extraFirstHalfNpcDeckId.lessThan=" + UPDATED_EXTRA_FIRST_HALF_NPC_DECK_ID);
    }


    @Test
    @Transactional
    public void getAllMMarathonQuestStagesByExtraSecondHalfNpcDeckIdIsEqualToSomething() throws Exception {
        // Initialize the database
        mMarathonQuestStageRepository.saveAndFlush(mMarathonQuestStage);

        // Get all the mMarathonQuestStageList where extraSecondHalfNpcDeckId equals to DEFAULT_EXTRA_SECOND_HALF_NPC_DECK_ID
        defaultMMarathonQuestStageShouldBeFound("extraSecondHalfNpcDeckId.equals=" + DEFAULT_EXTRA_SECOND_HALF_NPC_DECK_ID);

        // Get all the mMarathonQuestStageList where extraSecondHalfNpcDeckId equals to UPDATED_EXTRA_SECOND_HALF_NPC_DECK_ID
        defaultMMarathonQuestStageShouldNotBeFound("extraSecondHalfNpcDeckId.equals=" + UPDATED_EXTRA_SECOND_HALF_NPC_DECK_ID);
    }

    @Test
    @Transactional
    public void getAllMMarathonQuestStagesByExtraSecondHalfNpcDeckIdIsInShouldWork() throws Exception {
        // Initialize the database
        mMarathonQuestStageRepository.saveAndFlush(mMarathonQuestStage);

        // Get all the mMarathonQuestStageList where extraSecondHalfNpcDeckId in DEFAULT_EXTRA_SECOND_HALF_NPC_DECK_ID or UPDATED_EXTRA_SECOND_HALF_NPC_DECK_ID
        defaultMMarathonQuestStageShouldBeFound("extraSecondHalfNpcDeckId.in=" + DEFAULT_EXTRA_SECOND_HALF_NPC_DECK_ID + "," + UPDATED_EXTRA_SECOND_HALF_NPC_DECK_ID);

        // Get all the mMarathonQuestStageList where extraSecondHalfNpcDeckId equals to UPDATED_EXTRA_SECOND_HALF_NPC_DECK_ID
        defaultMMarathonQuestStageShouldNotBeFound("extraSecondHalfNpcDeckId.in=" + UPDATED_EXTRA_SECOND_HALF_NPC_DECK_ID);
    }

    @Test
    @Transactional
    public void getAllMMarathonQuestStagesByExtraSecondHalfNpcDeckIdIsNullOrNotNull() throws Exception {
        // Initialize the database
        mMarathonQuestStageRepository.saveAndFlush(mMarathonQuestStage);

        // Get all the mMarathonQuestStageList where extraSecondHalfNpcDeckId is not null
        defaultMMarathonQuestStageShouldBeFound("extraSecondHalfNpcDeckId.specified=true");

        // Get all the mMarathonQuestStageList where extraSecondHalfNpcDeckId is null
        defaultMMarathonQuestStageShouldNotBeFound("extraSecondHalfNpcDeckId.specified=false");
    }

    @Test
    @Transactional
    public void getAllMMarathonQuestStagesByExtraSecondHalfNpcDeckIdIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mMarathonQuestStageRepository.saveAndFlush(mMarathonQuestStage);

        // Get all the mMarathonQuestStageList where extraSecondHalfNpcDeckId greater than or equals to DEFAULT_EXTRA_SECOND_HALF_NPC_DECK_ID
        defaultMMarathonQuestStageShouldBeFound("extraSecondHalfNpcDeckId.greaterOrEqualThan=" + DEFAULT_EXTRA_SECOND_HALF_NPC_DECK_ID);

        // Get all the mMarathonQuestStageList where extraSecondHalfNpcDeckId greater than or equals to UPDATED_EXTRA_SECOND_HALF_NPC_DECK_ID
        defaultMMarathonQuestStageShouldNotBeFound("extraSecondHalfNpcDeckId.greaterOrEqualThan=" + UPDATED_EXTRA_SECOND_HALF_NPC_DECK_ID);
    }

    @Test
    @Transactional
    public void getAllMMarathonQuestStagesByExtraSecondHalfNpcDeckIdIsLessThanSomething() throws Exception {
        // Initialize the database
        mMarathonQuestStageRepository.saveAndFlush(mMarathonQuestStage);

        // Get all the mMarathonQuestStageList where extraSecondHalfNpcDeckId less than or equals to DEFAULT_EXTRA_SECOND_HALF_NPC_DECK_ID
        defaultMMarathonQuestStageShouldNotBeFound("extraSecondHalfNpcDeckId.lessThan=" + DEFAULT_EXTRA_SECOND_HALF_NPC_DECK_ID);

        // Get all the mMarathonQuestStageList where extraSecondHalfNpcDeckId less than or equals to UPDATED_EXTRA_SECOND_HALF_NPC_DECK_ID
        defaultMMarathonQuestStageShouldBeFound("extraSecondHalfNpcDeckId.lessThan=" + UPDATED_EXTRA_SECOND_HALF_NPC_DECK_ID);
    }


    @Test
    @Transactional
    public void getAllMMarathonQuestStagesByConsumeApIsEqualToSomething() throws Exception {
        // Initialize the database
        mMarathonQuestStageRepository.saveAndFlush(mMarathonQuestStage);

        // Get all the mMarathonQuestStageList where consumeAp equals to DEFAULT_CONSUME_AP
        defaultMMarathonQuestStageShouldBeFound("consumeAp.equals=" + DEFAULT_CONSUME_AP);

        // Get all the mMarathonQuestStageList where consumeAp equals to UPDATED_CONSUME_AP
        defaultMMarathonQuestStageShouldNotBeFound("consumeAp.equals=" + UPDATED_CONSUME_AP);
    }

    @Test
    @Transactional
    public void getAllMMarathonQuestStagesByConsumeApIsInShouldWork() throws Exception {
        // Initialize the database
        mMarathonQuestStageRepository.saveAndFlush(mMarathonQuestStage);

        // Get all the mMarathonQuestStageList where consumeAp in DEFAULT_CONSUME_AP or UPDATED_CONSUME_AP
        defaultMMarathonQuestStageShouldBeFound("consumeAp.in=" + DEFAULT_CONSUME_AP + "," + UPDATED_CONSUME_AP);

        // Get all the mMarathonQuestStageList where consumeAp equals to UPDATED_CONSUME_AP
        defaultMMarathonQuestStageShouldNotBeFound("consumeAp.in=" + UPDATED_CONSUME_AP);
    }

    @Test
    @Transactional
    public void getAllMMarathonQuestStagesByConsumeApIsNullOrNotNull() throws Exception {
        // Initialize the database
        mMarathonQuestStageRepository.saveAndFlush(mMarathonQuestStage);

        // Get all the mMarathonQuestStageList where consumeAp is not null
        defaultMMarathonQuestStageShouldBeFound("consumeAp.specified=true");

        // Get all the mMarathonQuestStageList where consumeAp is null
        defaultMMarathonQuestStageShouldNotBeFound("consumeAp.specified=false");
    }

    @Test
    @Transactional
    public void getAllMMarathonQuestStagesByConsumeApIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mMarathonQuestStageRepository.saveAndFlush(mMarathonQuestStage);

        // Get all the mMarathonQuestStageList where consumeAp greater than or equals to DEFAULT_CONSUME_AP
        defaultMMarathonQuestStageShouldBeFound("consumeAp.greaterOrEqualThan=" + DEFAULT_CONSUME_AP);

        // Get all the mMarathonQuestStageList where consumeAp greater than or equals to UPDATED_CONSUME_AP
        defaultMMarathonQuestStageShouldNotBeFound("consumeAp.greaterOrEqualThan=" + UPDATED_CONSUME_AP);
    }

    @Test
    @Transactional
    public void getAllMMarathonQuestStagesByConsumeApIsLessThanSomething() throws Exception {
        // Initialize the database
        mMarathonQuestStageRepository.saveAndFlush(mMarathonQuestStage);

        // Get all the mMarathonQuestStageList where consumeAp less than or equals to DEFAULT_CONSUME_AP
        defaultMMarathonQuestStageShouldNotBeFound("consumeAp.lessThan=" + DEFAULT_CONSUME_AP);

        // Get all the mMarathonQuestStageList where consumeAp less than or equals to UPDATED_CONSUME_AP
        defaultMMarathonQuestStageShouldBeFound("consumeAp.lessThan=" + UPDATED_CONSUME_AP);
    }


    @Test
    @Transactional
    public void getAllMMarathonQuestStagesByKickOffTypeIsEqualToSomething() throws Exception {
        // Initialize the database
        mMarathonQuestStageRepository.saveAndFlush(mMarathonQuestStage);

        // Get all the mMarathonQuestStageList where kickOffType equals to DEFAULT_KICK_OFF_TYPE
        defaultMMarathonQuestStageShouldBeFound("kickOffType.equals=" + DEFAULT_KICK_OFF_TYPE);

        // Get all the mMarathonQuestStageList where kickOffType equals to UPDATED_KICK_OFF_TYPE
        defaultMMarathonQuestStageShouldNotBeFound("kickOffType.equals=" + UPDATED_KICK_OFF_TYPE);
    }

    @Test
    @Transactional
    public void getAllMMarathonQuestStagesByKickOffTypeIsInShouldWork() throws Exception {
        // Initialize the database
        mMarathonQuestStageRepository.saveAndFlush(mMarathonQuestStage);

        // Get all the mMarathonQuestStageList where kickOffType in DEFAULT_KICK_OFF_TYPE or UPDATED_KICK_OFF_TYPE
        defaultMMarathonQuestStageShouldBeFound("kickOffType.in=" + DEFAULT_KICK_OFF_TYPE + "," + UPDATED_KICK_OFF_TYPE);

        // Get all the mMarathonQuestStageList where kickOffType equals to UPDATED_KICK_OFF_TYPE
        defaultMMarathonQuestStageShouldNotBeFound("kickOffType.in=" + UPDATED_KICK_OFF_TYPE);
    }

    @Test
    @Transactional
    public void getAllMMarathonQuestStagesByKickOffTypeIsNullOrNotNull() throws Exception {
        // Initialize the database
        mMarathonQuestStageRepository.saveAndFlush(mMarathonQuestStage);

        // Get all the mMarathonQuestStageList where kickOffType is not null
        defaultMMarathonQuestStageShouldBeFound("kickOffType.specified=true");

        // Get all the mMarathonQuestStageList where kickOffType is null
        defaultMMarathonQuestStageShouldNotBeFound("kickOffType.specified=false");
    }

    @Test
    @Transactional
    public void getAllMMarathonQuestStagesByKickOffTypeIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mMarathonQuestStageRepository.saveAndFlush(mMarathonQuestStage);

        // Get all the mMarathonQuestStageList where kickOffType greater than or equals to DEFAULT_KICK_OFF_TYPE
        defaultMMarathonQuestStageShouldBeFound("kickOffType.greaterOrEqualThan=" + DEFAULT_KICK_OFF_TYPE);

        // Get all the mMarathonQuestStageList where kickOffType greater than or equals to UPDATED_KICK_OFF_TYPE
        defaultMMarathonQuestStageShouldNotBeFound("kickOffType.greaterOrEqualThan=" + UPDATED_KICK_OFF_TYPE);
    }

    @Test
    @Transactional
    public void getAllMMarathonQuestStagesByKickOffTypeIsLessThanSomething() throws Exception {
        // Initialize the database
        mMarathonQuestStageRepository.saveAndFlush(mMarathonQuestStage);

        // Get all the mMarathonQuestStageList where kickOffType less than or equals to DEFAULT_KICK_OFF_TYPE
        defaultMMarathonQuestStageShouldNotBeFound("kickOffType.lessThan=" + DEFAULT_KICK_OFF_TYPE);

        // Get all the mMarathonQuestStageList where kickOffType less than or equals to UPDATED_KICK_OFF_TYPE
        defaultMMarathonQuestStageShouldBeFound("kickOffType.lessThan=" + UPDATED_KICK_OFF_TYPE);
    }


    @Test
    @Transactional
    public void getAllMMarathonQuestStagesByMatchMinuteIsEqualToSomething() throws Exception {
        // Initialize the database
        mMarathonQuestStageRepository.saveAndFlush(mMarathonQuestStage);

        // Get all the mMarathonQuestStageList where matchMinute equals to DEFAULT_MATCH_MINUTE
        defaultMMarathonQuestStageShouldBeFound("matchMinute.equals=" + DEFAULT_MATCH_MINUTE);

        // Get all the mMarathonQuestStageList where matchMinute equals to UPDATED_MATCH_MINUTE
        defaultMMarathonQuestStageShouldNotBeFound("matchMinute.equals=" + UPDATED_MATCH_MINUTE);
    }

    @Test
    @Transactional
    public void getAllMMarathonQuestStagesByMatchMinuteIsInShouldWork() throws Exception {
        // Initialize the database
        mMarathonQuestStageRepository.saveAndFlush(mMarathonQuestStage);

        // Get all the mMarathonQuestStageList where matchMinute in DEFAULT_MATCH_MINUTE or UPDATED_MATCH_MINUTE
        defaultMMarathonQuestStageShouldBeFound("matchMinute.in=" + DEFAULT_MATCH_MINUTE + "," + UPDATED_MATCH_MINUTE);

        // Get all the mMarathonQuestStageList where matchMinute equals to UPDATED_MATCH_MINUTE
        defaultMMarathonQuestStageShouldNotBeFound("matchMinute.in=" + UPDATED_MATCH_MINUTE);
    }

    @Test
    @Transactional
    public void getAllMMarathonQuestStagesByMatchMinuteIsNullOrNotNull() throws Exception {
        // Initialize the database
        mMarathonQuestStageRepository.saveAndFlush(mMarathonQuestStage);

        // Get all the mMarathonQuestStageList where matchMinute is not null
        defaultMMarathonQuestStageShouldBeFound("matchMinute.specified=true");

        // Get all the mMarathonQuestStageList where matchMinute is null
        defaultMMarathonQuestStageShouldNotBeFound("matchMinute.specified=false");
    }

    @Test
    @Transactional
    public void getAllMMarathonQuestStagesByMatchMinuteIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mMarathonQuestStageRepository.saveAndFlush(mMarathonQuestStage);

        // Get all the mMarathonQuestStageList where matchMinute greater than or equals to DEFAULT_MATCH_MINUTE
        defaultMMarathonQuestStageShouldBeFound("matchMinute.greaterOrEqualThan=" + DEFAULT_MATCH_MINUTE);

        // Get all the mMarathonQuestStageList where matchMinute greater than or equals to UPDATED_MATCH_MINUTE
        defaultMMarathonQuestStageShouldNotBeFound("matchMinute.greaterOrEqualThan=" + UPDATED_MATCH_MINUTE);
    }

    @Test
    @Transactional
    public void getAllMMarathonQuestStagesByMatchMinuteIsLessThanSomething() throws Exception {
        // Initialize the database
        mMarathonQuestStageRepository.saveAndFlush(mMarathonQuestStage);

        // Get all the mMarathonQuestStageList where matchMinute less than or equals to DEFAULT_MATCH_MINUTE
        defaultMMarathonQuestStageShouldNotBeFound("matchMinute.lessThan=" + DEFAULT_MATCH_MINUTE);

        // Get all the mMarathonQuestStageList where matchMinute less than or equals to UPDATED_MATCH_MINUTE
        defaultMMarathonQuestStageShouldBeFound("matchMinute.lessThan=" + UPDATED_MATCH_MINUTE);
    }


    @Test
    @Transactional
    public void getAllMMarathonQuestStagesByEnableExtraIsEqualToSomething() throws Exception {
        // Initialize the database
        mMarathonQuestStageRepository.saveAndFlush(mMarathonQuestStage);

        // Get all the mMarathonQuestStageList where enableExtra equals to DEFAULT_ENABLE_EXTRA
        defaultMMarathonQuestStageShouldBeFound("enableExtra.equals=" + DEFAULT_ENABLE_EXTRA);

        // Get all the mMarathonQuestStageList where enableExtra equals to UPDATED_ENABLE_EXTRA
        defaultMMarathonQuestStageShouldNotBeFound("enableExtra.equals=" + UPDATED_ENABLE_EXTRA);
    }

    @Test
    @Transactional
    public void getAllMMarathonQuestStagesByEnableExtraIsInShouldWork() throws Exception {
        // Initialize the database
        mMarathonQuestStageRepository.saveAndFlush(mMarathonQuestStage);

        // Get all the mMarathonQuestStageList where enableExtra in DEFAULT_ENABLE_EXTRA or UPDATED_ENABLE_EXTRA
        defaultMMarathonQuestStageShouldBeFound("enableExtra.in=" + DEFAULT_ENABLE_EXTRA + "," + UPDATED_ENABLE_EXTRA);

        // Get all the mMarathonQuestStageList where enableExtra equals to UPDATED_ENABLE_EXTRA
        defaultMMarathonQuestStageShouldNotBeFound("enableExtra.in=" + UPDATED_ENABLE_EXTRA);
    }

    @Test
    @Transactional
    public void getAllMMarathonQuestStagesByEnableExtraIsNullOrNotNull() throws Exception {
        // Initialize the database
        mMarathonQuestStageRepository.saveAndFlush(mMarathonQuestStage);

        // Get all the mMarathonQuestStageList where enableExtra is not null
        defaultMMarathonQuestStageShouldBeFound("enableExtra.specified=true");

        // Get all the mMarathonQuestStageList where enableExtra is null
        defaultMMarathonQuestStageShouldNotBeFound("enableExtra.specified=false");
    }

    @Test
    @Transactional
    public void getAllMMarathonQuestStagesByEnableExtraIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mMarathonQuestStageRepository.saveAndFlush(mMarathonQuestStage);

        // Get all the mMarathonQuestStageList where enableExtra greater than or equals to DEFAULT_ENABLE_EXTRA
        defaultMMarathonQuestStageShouldBeFound("enableExtra.greaterOrEqualThan=" + DEFAULT_ENABLE_EXTRA);

        // Get all the mMarathonQuestStageList where enableExtra greater than or equals to UPDATED_ENABLE_EXTRA
        defaultMMarathonQuestStageShouldNotBeFound("enableExtra.greaterOrEqualThan=" + UPDATED_ENABLE_EXTRA);
    }

    @Test
    @Transactional
    public void getAllMMarathonQuestStagesByEnableExtraIsLessThanSomething() throws Exception {
        // Initialize the database
        mMarathonQuestStageRepository.saveAndFlush(mMarathonQuestStage);

        // Get all the mMarathonQuestStageList where enableExtra less than or equals to DEFAULT_ENABLE_EXTRA
        defaultMMarathonQuestStageShouldNotBeFound("enableExtra.lessThan=" + DEFAULT_ENABLE_EXTRA);

        // Get all the mMarathonQuestStageList where enableExtra less than or equals to UPDATED_ENABLE_EXTRA
        defaultMMarathonQuestStageShouldBeFound("enableExtra.lessThan=" + UPDATED_ENABLE_EXTRA);
    }


    @Test
    @Transactional
    public void getAllMMarathonQuestStagesByEnablePkIsEqualToSomething() throws Exception {
        // Initialize the database
        mMarathonQuestStageRepository.saveAndFlush(mMarathonQuestStage);

        // Get all the mMarathonQuestStageList where enablePk equals to DEFAULT_ENABLE_PK
        defaultMMarathonQuestStageShouldBeFound("enablePk.equals=" + DEFAULT_ENABLE_PK);

        // Get all the mMarathonQuestStageList where enablePk equals to UPDATED_ENABLE_PK
        defaultMMarathonQuestStageShouldNotBeFound("enablePk.equals=" + UPDATED_ENABLE_PK);
    }

    @Test
    @Transactional
    public void getAllMMarathonQuestStagesByEnablePkIsInShouldWork() throws Exception {
        // Initialize the database
        mMarathonQuestStageRepository.saveAndFlush(mMarathonQuestStage);

        // Get all the mMarathonQuestStageList where enablePk in DEFAULT_ENABLE_PK or UPDATED_ENABLE_PK
        defaultMMarathonQuestStageShouldBeFound("enablePk.in=" + DEFAULT_ENABLE_PK + "," + UPDATED_ENABLE_PK);

        // Get all the mMarathonQuestStageList where enablePk equals to UPDATED_ENABLE_PK
        defaultMMarathonQuestStageShouldNotBeFound("enablePk.in=" + UPDATED_ENABLE_PK);
    }

    @Test
    @Transactional
    public void getAllMMarathonQuestStagesByEnablePkIsNullOrNotNull() throws Exception {
        // Initialize the database
        mMarathonQuestStageRepository.saveAndFlush(mMarathonQuestStage);

        // Get all the mMarathonQuestStageList where enablePk is not null
        defaultMMarathonQuestStageShouldBeFound("enablePk.specified=true");

        // Get all the mMarathonQuestStageList where enablePk is null
        defaultMMarathonQuestStageShouldNotBeFound("enablePk.specified=false");
    }

    @Test
    @Transactional
    public void getAllMMarathonQuestStagesByEnablePkIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mMarathonQuestStageRepository.saveAndFlush(mMarathonQuestStage);

        // Get all the mMarathonQuestStageList where enablePk greater than or equals to DEFAULT_ENABLE_PK
        defaultMMarathonQuestStageShouldBeFound("enablePk.greaterOrEqualThan=" + DEFAULT_ENABLE_PK);

        // Get all the mMarathonQuestStageList where enablePk greater than or equals to UPDATED_ENABLE_PK
        defaultMMarathonQuestStageShouldNotBeFound("enablePk.greaterOrEqualThan=" + UPDATED_ENABLE_PK);
    }

    @Test
    @Transactional
    public void getAllMMarathonQuestStagesByEnablePkIsLessThanSomething() throws Exception {
        // Initialize the database
        mMarathonQuestStageRepository.saveAndFlush(mMarathonQuestStage);

        // Get all the mMarathonQuestStageList where enablePk less than or equals to DEFAULT_ENABLE_PK
        defaultMMarathonQuestStageShouldNotBeFound("enablePk.lessThan=" + DEFAULT_ENABLE_PK);

        // Get all the mMarathonQuestStageList where enablePk less than or equals to UPDATED_ENABLE_PK
        defaultMMarathonQuestStageShouldBeFound("enablePk.lessThan=" + UPDATED_ENABLE_PK);
    }


    @Test
    @Transactional
    public void getAllMMarathonQuestStagesByAiLevelIsEqualToSomething() throws Exception {
        // Initialize the database
        mMarathonQuestStageRepository.saveAndFlush(mMarathonQuestStage);

        // Get all the mMarathonQuestStageList where aiLevel equals to DEFAULT_AI_LEVEL
        defaultMMarathonQuestStageShouldBeFound("aiLevel.equals=" + DEFAULT_AI_LEVEL);

        // Get all the mMarathonQuestStageList where aiLevel equals to UPDATED_AI_LEVEL
        defaultMMarathonQuestStageShouldNotBeFound("aiLevel.equals=" + UPDATED_AI_LEVEL);
    }

    @Test
    @Transactional
    public void getAllMMarathonQuestStagesByAiLevelIsInShouldWork() throws Exception {
        // Initialize the database
        mMarathonQuestStageRepository.saveAndFlush(mMarathonQuestStage);

        // Get all the mMarathonQuestStageList where aiLevel in DEFAULT_AI_LEVEL or UPDATED_AI_LEVEL
        defaultMMarathonQuestStageShouldBeFound("aiLevel.in=" + DEFAULT_AI_LEVEL + "," + UPDATED_AI_LEVEL);

        // Get all the mMarathonQuestStageList where aiLevel equals to UPDATED_AI_LEVEL
        defaultMMarathonQuestStageShouldNotBeFound("aiLevel.in=" + UPDATED_AI_LEVEL);
    }

    @Test
    @Transactional
    public void getAllMMarathonQuestStagesByAiLevelIsNullOrNotNull() throws Exception {
        // Initialize the database
        mMarathonQuestStageRepository.saveAndFlush(mMarathonQuestStage);

        // Get all the mMarathonQuestStageList where aiLevel is not null
        defaultMMarathonQuestStageShouldBeFound("aiLevel.specified=true");

        // Get all the mMarathonQuestStageList where aiLevel is null
        defaultMMarathonQuestStageShouldNotBeFound("aiLevel.specified=false");
    }

    @Test
    @Transactional
    public void getAllMMarathonQuestStagesByAiLevelIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mMarathonQuestStageRepository.saveAndFlush(mMarathonQuestStage);

        // Get all the mMarathonQuestStageList where aiLevel greater than or equals to DEFAULT_AI_LEVEL
        defaultMMarathonQuestStageShouldBeFound("aiLevel.greaterOrEqualThan=" + DEFAULT_AI_LEVEL);

        // Get all the mMarathonQuestStageList where aiLevel greater than or equals to UPDATED_AI_LEVEL
        defaultMMarathonQuestStageShouldNotBeFound("aiLevel.greaterOrEqualThan=" + UPDATED_AI_LEVEL);
    }

    @Test
    @Transactional
    public void getAllMMarathonQuestStagesByAiLevelIsLessThanSomething() throws Exception {
        // Initialize the database
        mMarathonQuestStageRepository.saveAndFlush(mMarathonQuestStage);

        // Get all the mMarathonQuestStageList where aiLevel less than or equals to DEFAULT_AI_LEVEL
        defaultMMarathonQuestStageShouldNotBeFound("aiLevel.lessThan=" + DEFAULT_AI_LEVEL);

        // Get all the mMarathonQuestStageList where aiLevel less than or equals to UPDATED_AI_LEVEL
        defaultMMarathonQuestStageShouldBeFound("aiLevel.lessThan=" + UPDATED_AI_LEVEL);
    }


    @Test
    @Transactional
    public void getAllMMarathonQuestStagesByStartAtSecondHalfIsEqualToSomething() throws Exception {
        // Initialize the database
        mMarathonQuestStageRepository.saveAndFlush(mMarathonQuestStage);

        // Get all the mMarathonQuestStageList where startAtSecondHalf equals to DEFAULT_START_AT_SECOND_HALF
        defaultMMarathonQuestStageShouldBeFound("startAtSecondHalf.equals=" + DEFAULT_START_AT_SECOND_HALF);

        // Get all the mMarathonQuestStageList where startAtSecondHalf equals to UPDATED_START_AT_SECOND_HALF
        defaultMMarathonQuestStageShouldNotBeFound("startAtSecondHalf.equals=" + UPDATED_START_AT_SECOND_HALF);
    }

    @Test
    @Transactional
    public void getAllMMarathonQuestStagesByStartAtSecondHalfIsInShouldWork() throws Exception {
        // Initialize the database
        mMarathonQuestStageRepository.saveAndFlush(mMarathonQuestStage);

        // Get all the mMarathonQuestStageList where startAtSecondHalf in DEFAULT_START_AT_SECOND_HALF or UPDATED_START_AT_SECOND_HALF
        defaultMMarathonQuestStageShouldBeFound("startAtSecondHalf.in=" + DEFAULT_START_AT_SECOND_HALF + "," + UPDATED_START_AT_SECOND_HALF);

        // Get all the mMarathonQuestStageList where startAtSecondHalf equals to UPDATED_START_AT_SECOND_HALF
        defaultMMarathonQuestStageShouldNotBeFound("startAtSecondHalf.in=" + UPDATED_START_AT_SECOND_HALF);
    }

    @Test
    @Transactional
    public void getAllMMarathonQuestStagesByStartAtSecondHalfIsNullOrNotNull() throws Exception {
        // Initialize the database
        mMarathonQuestStageRepository.saveAndFlush(mMarathonQuestStage);

        // Get all the mMarathonQuestStageList where startAtSecondHalf is not null
        defaultMMarathonQuestStageShouldBeFound("startAtSecondHalf.specified=true");

        // Get all the mMarathonQuestStageList where startAtSecondHalf is null
        defaultMMarathonQuestStageShouldNotBeFound("startAtSecondHalf.specified=false");
    }

    @Test
    @Transactional
    public void getAllMMarathonQuestStagesByStartAtSecondHalfIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mMarathonQuestStageRepository.saveAndFlush(mMarathonQuestStage);

        // Get all the mMarathonQuestStageList where startAtSecondHalf greater than or equals to DEFAULT_START_AT_SECOND_HALF
        defaultMMarathonQuestStageShouldBeFound("startAtSecondHalf.greaterOrEqualThan=" + DEFAULT_START_AT_SECOND_HALF);

        // Get all the mMarathonQuestStageList where startAtSecondHalf greater than or equals to UPDATED_START_AT_SECOND_HALF
        defaultMMarathonQuestStageShouldNotBeFound("startAtSecondHalf.greaterOrEqualThan=" + UPDATED_START_AT_SECOND_HALF);
    }

    @Test
    @Transactional
    public void getAllMMarathonQuestStagesByStartAtSecondHalfIsLessThanSomething() throws Exception {
        // Initialize the database
        mMarathonQuestStageRepository.saveAndFlush(mMarathonQuestStage);

        // Get all the mMarathonQuestStageList where startAtSecondHalf less than or equals to DEFAULT_START_AT_SECOND_HALF
        defaultMMarathonQuestStageShouldNotBeFound("startAtSecondHalf.lessThan=" + DEFAULT_START_AT_SECOND_HALF);

        // Get all the mMarathonQuestStageList where startAtSecondHalf less than or equals to UPDATED_START_AT_SECOND_HALF
        defaultMMarathonQuestStageShouldBeFound("startAtSecondHalf.lessThan=" + UPDATED_START_AT_SECOND_HALF);
    }


    @Test
    @Transactional
    public void getAllMMarathonQuestStagesByStartScoreIsEqualToSomething() throws Exception {
        // Initialize the database
        mMarathonQuestStageRepository.saveAndFlush(mMarathonQuestStage);

        // Get all the mMarathonQuestStageList where startScore equals to DEFAULT_START_SCORE
        defaultMMarathonQuestStageShouldBeFound("startScore.equals=" + DEFAULT_START_SCORE);

        // Get all the mMarathonQuestStageList where startScore equals to UPDATED_START_SCORE
        defaultMMarathonQuestStageShouldNotBeFound("startScore.equals=" + UPDATED_START_SCORE);
    }

    @Test
    @Transactional
    public void getAllMMarathonQuestStagesByStartScoreIsInShouldWork() throws Exception {
        // Initialize the database
        mMarathonQuestStageRepository.saveAndFlush(mMarathonQuestStage);

        // Get all the mMarathonQuestStageList where startScore in DEFAULT_START_SCORE or UPDATED_START_SCORE
        defaultMMarathonQuestStageShouldBeFound("startScore.in=" + DEFAULT_START_SCORE + "," + UPDATED_START_SCORE);

        // Get all the mMarathonQuestStageList where startScore equals to UPDATED_START_SCORE
        defaultMMarathonQuestStageShouldNotBeFound("startScore.in=" + UPDATED_START_SCORE);
    }

    @Test
    @Transactional
    public void getAllMMarathonQuestStagesByStartScoreIsNullOrNotNull() throws Exception {
        // Initialize the database
        mMarathonQuestStageRepository.saveAndFlush(mMarathonQuestStage);

        // Get all the mMarathonQuestStageList where startScore is not null
        defaultMMarathonQuestStageShouldBeFound("startScore.specified=true");

        // Get all the mMarathonQuestStageList where startScore is null
        defaultMMarathonQuestStageShouldNotBeFound("startScore.specified=false");
    }

    @Test
    @Transactional
    public void getAllMMarathonQuestStagesByStartScoreIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mMarathonQuestStageRepository.saveAndFlush(mMarathonQuestStage);

        // Get all the mMarathonQuestStageList where startScore greater than or equals to DEFAULT_START_SCORE
        defaultMMarathonQuestStageShouldBeFound("startScore.greaterOrEqualThan=" + DEFAULT_START_SCORE);

        // Get all the mMarathonQuestStageList where startScore greater than or equals to UPDATED_START_SCORE
        defaultMMarathonQuestStageShouldNotBeFound("startScore.greaterOrEqualThan=" + UPDATED_START_SCORE);
    }

    @Test
    @Transactional
    public void getAllMMarathonQuestStagesByStartScoreIsLessThanSomething() throws Exception {
        // Initialize the database
        mMarathonQuestStageRepository.saveAndFlush(mMarathonQuestStage);

        // Get all the mMarathonQuestStageList where startScore less than or equals to DEFAULT_START_SCORE
        defaultMMarathonQuestStageShouldNotBeFound("startScore.lessThan=" + DEFAULT_START_SCORE);

        // Get all the mMarathonQuestStageList where startScore less than or equals to UPDATED_START_SCORE
        defaultMMarathonQuestStageShouldBeFound("startScore.lessThan=" + UPDATED_START_SCORE);
    }


    @Test
    @Transactional
    public void getAllMMarathonQuestStagesByStartScoreOpponentIsEqualToSomething() throws Exception {
        // Initialize the database
        mMarathonQuestStageRepository.saveAndFlush(mMarathonQuestStage);

        // Get all the mMarathonQuestStageList where startScoreOpponent equals to DEFAULT_START_SCORE_OPPONENT
        defaultMMarathonQuestStageShouldBeFound("startScoreOpponent.equals=" + DEFAULT_START_SCORE_OPPONENT);

        // Get all the mMarathonQuestStageList where startScoreOpponent equals to UPDATED_START_SCORE_OPPONENT
        defaultMMarathonQuestStageShouldNotBeFound("startScoreOpponent.equals=" + UPDATED_START_SCORE_OPPONENT);
    }

    @Test
    @Transactional
    public void getAllMMarathonQuestStagesByStartScoreOpponentIsInShouldWork() throws Exception {
        // Initialize the database
        mMarathonQuestStageRepository.saveAndFlush(mMarathonQuestStage);

        // Get all the mMarathonQuestStageList where startScoreOpponent in DEFAULT_START_SCORE_OPPONENT or UPDATED_START_SCORE_OPPONENT
        defaultMMarathonQuestStageShouldBeFound("startScoreOpponent.in=" + DEFAULT_START_SCORE_OPPONENT + "," + UPDATED_START_SCORE_OPPONENT);

        // Get all the mMarathonQuestStageList where startScoreOpponent equals to UPDATED_START_SCORE_OPPONENT
        defaultMMarathonQuestStageShouldNotBeFound("startScoreOpponent.in=" + UPDATED_START_SCORE_OPPONENT);
    }

    @Test
    @Transactional
    public void getAllMMarathonQuestStagesByStartScoreOpponentIsNullOrNotNull() throws Exception {
        // Initialize the database
        mMarathonQuestStageRepository.saveAndFlush(mMarathonQuestStage);

        // Get all the mMarathonQuestStageList where startScoreOpponent is not null
        defaultMMarathonQuestStageShouldBeFound("startScoreOpponent.specified=true");

        // Get all the mMarathonQuestStageList where startScoreOpponent is null
        defaultMMarathonQuestStageShouldNotBeFound("startScoreOpponent.specified=false");
    }

    @Test
    @Transactional
    public void getAllMMarathonQuestStagesByStartScoreOpponentIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mMarathonQuestStageRepository.saveAndFlush(mMarathonQuestStage);

        // Get all the mMarathonQuestStageList where startScoreOpponent greater than or equals to DEFAULT_START_SCORE_OPPONENT
        defaultMMarathonQuestStageShouldBeFound("startScoreOpponent.greaterOrEqualThan=" + DEFAULT_START_SCORE_OPPONENT);

        // Get all the mMarathonQuestStageList where startScoreOpponent greater than or equals to UPDATED_START_SCORE_OPPONENT
        defaultMMarathonQuestStageShouldNotBeFound("startScoreOpponent.greaterOrEqualThan=" + UPDATED_START_SCORE_OPPONENT);
    }

    @Test
    @Transactional
    public void getAllMMarathonQuestStagesByStartScoreOpponentIsLessThanSomething() throws Exception {
        // Initialize the database
        mMarathonQuestStageRepository.saveAndFlush(mMarathonQuestStage);

        // Get all the mMarathonQuestStageList where startScoreOpponent less than or equals to DEFAULT_START_SCORE_OPPONENT
        defaultMMarathonQuestStageShouldNotBeFound("startScoreOpponent.lessThan=" + DEFAULT_START_SCORE_OPPONENT);

        // Get all the mMarathonQuestStageList where startScoreOpponent less than or equals to UPDATED_START_SCORE_OPPONENT
        defaultMMarathonQuestStageShouldBeFound("startScoreOpponent.lessThan=" + UPDATED_START_SCORE_OPPONENT);
    }


    @Test
    @Transactional
    public void getAllMMarathonQuestStagesByConditionIdIsEqualToSomething() throws Exception {
        // Initialize the database
        mMarathonQuestStageRepository.saveAndFlush(mMarathonQuestStage);

        // Get all the mMarathonQuestStageList where conditionId equals to DEFAULT_CONDITION_ID
        defaultMMarathonQuestStageShouldBeFound("conditionId.equals=" + DEFAULT_CONDITION_ID);

        // Get all the mMarathonQuestStageList where conditionId equals to UPDATED_CONDITION_ID
        defaultMMarathonQuestStageShouldNotBeFound("conditionId.equals=" + UPDATED_CONDITION_ID);
    }

    @Test
    @Transactional
    public void getAllMMarathonQuestStagesByConditionIdIsInShouldWork() throws Exception {
        // Initialize the database
        mMarathonQuestStageRepository.saveAndFlush(mMarathonQuestStage);

        // Get all the mMarathonQuestStageList where conditionId in DEFAULT_CONDITION_ID or UPDATED_CONDITION_ID
        defaultMMarathonQuestStageShouldBeFound("conditionId.in=" + DEFAULT_CONDITION_ID + "," + UPDATED_CONDITION_ID);

        // Get all the mMarathonQuestStageList where conditionId equals to UPDATED_CONDITION_ID
        defaultMMarathonQuestStageShouldNotBeFound("conditionId.in=" + UPDATED_CONDITION_ID);
    }

    @Test
    @Transactional
    public void getAllMMarathonQuestStagesByConditionIdIsNullOrNotNull() throws Exception {
        // Initialize the database
        mMarathonQuestStageRepository.saveAndFlush(mMarathonQuestStage);

        // Get all the mMarathonQuestStageList where conditionId is not null
        defaultMMarathonQuestStageShouldBeFound("conditionId.specified=true");

        // Get all the mMarathonQuestStageList where conditionId is null
        defaultMMarathonQuestStageShouldNotBeFound("conditionId.specified=false");
    }

    @Test
    @Transactional
    public void getAllMMarathonQuestStagesByConditionIdIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mMarathonQuestStageRepository.saveAndFlush(mMarathonQuestStage);

        // Get all the mMarathonQuestStageList where conditionId greater than or equals to DEFAULT_CONDITION_ID
        defaultMMarathonQuestStageShouldBeFound("conditionId.greaterOrEqualThan=" + DEFAULT_CONDITION_ID);

        // Get all the mMarathonQuestStageList where conditionId greater than or equals to UPDATED_CONDITION_ID
        defaultMMarathonQuestStageShouldNotBeFound("conditionId.greaterOrEqualThan=" + UPDATED_CONDITION_ID);
    }

    @Test
    @Transactional
    public void getAllMMarathonQuestStagesByConditionIdIsLessThanSomething() throws Exception {
        // Initialize the database
        mMarathonQuestStageRepository.saveAndFlush(mMarathonQuestStage);

        // Get all the mMarathonQuestStageList where conditionId less than or equals to DEFAULT_CONDITION_ID
        defaultMMarathonQuestStageShouldNotBeFound("conditionId.lessThan=" + DEFAULT_CONDITION_ID);

        // Get all the mMarathonQuestStageList where conditionId less than or equals to UPDATED_CONDITION_ID
        defaultMMarathonQuestStageShouldBeFound("conditionId.lessThan=" + UPDATED_CONDITION_ID);
    }


    @Test
    @Transactional
    public void getAllMMarathonQuestStagesByOptionIdIsEqualToSomething() throws Exception {
        // Initialize the database
        mMarathonQuestStageRepository.saveAndFlush(mMarathonQuestStage);

        // Get all the mMarathonQuestStageList where optionId equals to DEFAULT_OPTION_ID
        defaultMMarathonQuestStageShouldBeFound("optionId.equals=" + DEFAULT_OPTION_ID);

        // Get all the mMarathonQuestStageList where optionId equals to UPDATED_OPTION_ID
        defaultMMarathonQuestStageShouldNotBeFound("optionId.equals=" + UPDATED_OPTION_ID);
    }

    @Test
    @Transactional
    public void getAllMMarathonQuestStagesByOptionIdIsInShouldWork() throws Exception {
        // Initialize the database
        mMarathonQuestStageRepository.saveAndFlush(mMarathonQuestStage);

        // Get all the mMarathonQuestStageList where optionId in DEFAULT_OPTION_ID or UPDATED_OPTION_ID
        defaultMMarathonQuestStageShouldBeFound("optionId.in=" + DEFAULT_OPTION_ID + "," + UPDATED_OPTION_ID);

        // Get all the mMarathonQuestStageList where optionId equals to UPDATED_OPTION_ID
        defaultMMarathonQuestStageShouldNotBeFound("optionId.in=" + UPDATED_OPTION_ID);
    }

    @Test
    @Transactional
    public void getAllMMarathonQuestStagesByOptionIdIsNullOrNotNull() throws Exception {
        // Initialize the database
        mMarathonQuestStageRepository.saveAndFlush(mMarathonQuestStage);

        // Get all the mMarathonQuestStageList where optionId is not null
        defaultMMarathonQuestStageShouldBeFound("optionId.specified=true");

        // Get all the mMarathonQuestStageList where optionId is null
        defaultMMarathonQuestStageShouldNotBeFound("optionId.specified=false");
    }

    @Test
    @Transactional
    public void getAllMMarathonQuestStagesByOptionIdIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mMarathonQuestStageRepository.saveAndFlush(mMarathonQuestStage);

        // Get all the mMarathonQuestStageList where optionId greater than or equals to DEFAULT_OPTION_ID
        defaultMMarathonQuestStageShouldBeFound("optionId.greaterOrEqualThan=" + DEFAULT_OPTION_ID);

        // Get all the mMarathonQuestStageList where optionId greater than or equals to UPDATED_OPTION_ID
        defaultMMarathonQuestStageShouldNotBeFound("optionId.greaterOrEqualThan=" + UPDATED_OPTION_ID);
    }

    @Test
    @Transactional
    public void getAllMMarathonQuestStagesByOptionIdIsLessThanSomething() throws Exception {
        // Initialize the database
        mMarathonQuestStageRepository.saveAndFlush(mMarathonQuestStage);

        // Get all the mMarathonQuestStageList where optionId less than or equals to DEFAULT_OPTION_ID
        defaultMMarathonQuestStageShouldNotBeFound("optionId.lessThan=" + DEFAULT_OPTION_ID);

        // Get all the mMarathonQuestStageList where optionId less than or equals to UPDATED_OPTION_ID
        defaultMMarathonQuestStageShouldBeFound("optionId.lessThan=" + UPDATED_OPTION_ID);
    }


    @Test
    @Transactional
    public void getAllMMarathonQuestStagesByDeckConditionIdIsEqualToSomething() throws Exception {
        // Initialize the database
        mMarathonQuestStageRepository.saveAndFlush(mMarathonQuestStage);

        // Get all the mMarathonQuestStageList where deckConditionId equals to DEFAULT_DECK_CONDITION_ID
        defaultMMarathonQuestStageShouldBeFound("deckConditionId.equals=" + DEFAULT_DECK_CONDITION_ID);

        // Get all the mMarathonQuestStageList where deckConditionId equals to UPDATED_DECK_CONDITION_ID
        defaultMMarathonQuestStageShouldNotBeFound("deckConditionId.equals=" + UPDATED_DECK_CONDITION_ID);
    }

    @Test
    @Transactional
    public void getAllMMarathonQuestStagesByDeckConditionIdIsInShouldWork() throws Exception {
        // Initialize the database
        mMarathonQuestStageRepository.saveAndFlush(mMarathonQuestStage);

        // Get all the mMarathonQuestStageList where deckConditionId in DEFAULT_DECK_CONDITION_ID or UPDATED_DECK_CONDITION_ID
        defaultMMarathonQuestStageShouldBeFound("deckConditionId.in=" + DEFAULT_DECK_CONDITION_ID + "," + UPDATED_DECK_CONDITION_ID);

        // Get all the mMarathonQuestStageList where deckConditionId equals to UPDATED_DECK_CONDITION_ID
        defaultMMarathonQuestStageShouldNotBeFound("deckConditionId.in=" + UPDATED_DECK_CONDITION_ID);
    }

    @Test
    @Transactional
    public void getAllMMarathonQuestStagesByDeckConditionIdIsNullOrNotNull() throws Exception {
        // Initialize the database
        mMarathonQuestStageRepository.saveAndFlush(mMarathonQuestStage);

        // Get all the mMarathonQuestStageList where deckConditionId is not null
        defaultMMarathonQuestStageShouldBeFound("deckConditionId.specified=true");

        // Get all the mMarathonQuestStageList where deckConditionId is null
        defaultMMarathonQuestStageShouldNotBeFound("deckConditionId.specified=false");
    }

    @Test
    @Transactional
    public void getAllMMarathonQuestStagesByDeckConditionIdIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mMarathonQuestStageRepository.saveAndFlush(mMarathonQuestStage);

        // Get all the mMarathonQuestStageList where deckConditionId greater than or equals to DEFAULT_DECK_CONDITION_ID
        defaultMMarathonQuestStageShouldBeFound("deckConditionId.greaterOrEqualThan=" + DEFAULT_DECK_CONDITION_ID);

        // Get all the mMarathonQuestStageList where deckConditionId greater than or equals to UPDATED_DECK_CONDITION_ID
        defaultMMarathonQuestStageShouldNotBeFound("deckConditionId.greaterOrEqualThan=" + UPDATED_DECK_CONDITION_ID);
    }

    @Test
    @Transactional
    public void getAllMMarathonQuestStagesByDeckConditionIdIsLessThanSomething() throws Exception {
        // Initialize the database
        mMarathonQuestStageRepository.saveAndFlush(mMarathonQuestStage);

        // Get all the mMarathonQuestStageList where deckConditionId less than or equals to DEFAULT_DECK_CONDITION_ID
        defaultMMarathonQuestStageShouldNotBeFound("deckConditionId.lessThan=" + DEFAULT_DECK_CONDITION_ID);

        // Get all the mMarathonQuestStageList where deckConditionId less than or equals to UPDATED_DECK_CONDITION_ID
        defaultMMarathonQuestStageShouldBeFound("deckConditionId.lessThan=" + UPDATED_DECK_CONDITION_ID);
    }


    @Test
    @Transactional
    public void getAllMMarathonQuestStagesByIdIsEqualToSomething() throws Exception {
        // Get already existing entity
        MMarathonQuestWorld id = mMarathonQuestStage.getId();
        mMarathonQuestStageRepository.saveAndFlush(mMarathonQuestStage);
        Long idId = id.getId();

        // Get all the mMarathonQuestStageList where id equals to idId
        defaultMMarathonQuestStageShouldBeFound("idId.equals=" + idId);

        // Get all the mMarathonQuestStageList where id equals to idId + 1
        defaultMMarathonQuestStageShouldNotBeFound("idId.equals=" + (idId + 1));
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultMMarathonQuestStageShouldBeFound(String filter) throws Exception {
        restMMarathonQuestStageMockMvc.perform(get("/api/m-marathon-quest-stages?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(mMarathonQuestStage.getId().intValue())))
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
        restMMarathonQuestStageMockMvc.perform(get("/api/m-marathon-quest-stages/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultMMarathonQuestStageShouldNotBeFound(String filter) throws Exception {
        restMMarathonQuestStageMockMvc.perform(get("/api/m-marathon-quest-stages?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restMMarathonQuestStageMockMvc.perform(get("/api/m-marathon-quest-stages/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("0"));
    }


    @Test
    @Transactional
    public void getNonExistingMMarathonQuestStage() throws Exception {
        // Get the mMarathonQuestStage
        restMMarathonQuestStageMockMvc.perform(get("/api/m-marathon-quest-stages/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateMMarathonQuestStage() throws Exception {
        // Initialize the database
        mMarathonQuestStageRepository.saveAndFlush(mMarathonQuestStage);

        int databaseSizeBeforeUpdate = mMarathonQuestStageRepository.findAll().size();

        // Update the mMarathonQuestStage
        MMarathonQuestStage updatedMMarathonQuestStage = mMarathonQuestStageRepository.findById(mMarathonQuestStage.getId()).get();
        // Disconnect from session so that the updates on updatedMMarathonQuestStage are not directly saved in db
        em.detach(updatedMMarathonQuestStage);
        updatedMMarathonQuestStage
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
        MMarathonQuestStageDTO mMarathonQuestStageDTO = mMarathonQuestStageMapper.toDto(updatedMMarathonQuestStage);

        restMMarathonQuestStageMockMvc.perform(put("/api/m-marathon-quest-stages")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mMarathonQuestStageDTO)))
            .andExpect(status().isOk());

        // Validate the MMarathonQuestStage in the database
        List<MMarathonQuestStage> mMarathonQuestStageList = mMarathonQuestStageRepository.findAll();
        assertThat(mMarathonQuestStageList).hasSize(databaseSizeBeforeUpdate);
        MMarathonQuestStage testMMarathonQuestStage = mMarathonQuestStageList.get(mMarathonQuestStageList.size() - 1);
        assertThat(testMMarathonQuestStage.getWorldId()).isEqualTo(UPDATED_WORLD_ID);
        assertThat(testMMarathonQuestStage.getNumber()).isEqualTo(UPDATED_NUMBER);
        assertThat(testMMarathonQuestStage.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testMMarathonQuestStage.getImagePath()).isEqualTo(UPDATED_IMAGE_PATH);
        assertThat(testMMarathonQuestStage.getCharacterThumbnailPath()).isEqualTo(UPDATED_CHARACTER_THUMBNAIL_PATH);
        assertThat(testMMarathonQuestStage.getDifficulty()).isEqualTo(UPDATED_DIFFICULTY);
        assertThat(testMMarathonQuestStage.getStageUnlockPattern()).isEqualTo(UPDATED_STAGE_UNLOCK_PATTERN);
        assertThat(testMMarathonQuestStage.getStoryOnly()).isEqualTo(UPDATED_STORY_ONLY);
        assertThat(testMMarathonQuestStage.getFirstHalfNpcDeckId()).isEqualTo(UPDATED_FIRST_HALF_NPC_DECK_ID);
        assertThat(testMMarathonQuestStage.getFirstHalfEnvironmentId()).isEqualTo(UPDATED_FIRST_HALF_ENVIRONMENT_ID);
        assertThat(testMMarathonQuestStage.getSecondHalfNpcDeckId()).isEqualTo(UPDATED_SECOND_HALF_NPC_DECK_ID);
        assertThat(testMMarathonQuestStage.getSecondHalfEnvironmentId()).isEqualTo(UPDATED_SECOND_HALF_ENVIRONMENT_ID);
        assertThat(testMMarathonQuestStage.getExtraFirstHalfNpcDeckId()).isEqualTo(UPDATED_EXTRA_FIRST_HALF_NPC_DECK_ID);
        assertThat(testMMarathonQuestStage.getExtraSecondHalfNpcDeckId()).isEqualTo(UPDATED_EXTRA_SECOND_HALF_NPC_DECK_ID);
        assertThat(testMMarathonQuestStage.getConsumeAp()).isEqualTo(UPDATED_CONSUME_AP);
        assertThat(testMMarathonQuestStage.getKickOffType()).isEqualTo(UPDATED_KICK_OFF_TYPE);
        assertThat(testMMarathonQuestStage.getMatchMinute()).isEqualTo(UPDATED_MATCH_MINUTE);
        assertThat(testMMarathonQuestStage.getEnableExtra()).isEqualTo(UPDATED_ENABLE_EXTRA);
        assertThat(testMMarathonQuestStage.getEnablePk()).isEqualTo(UPDATED_ENABLE_PK);
        assertThat(testMMarathonQuestStage.getAiLevel()).isEqualTo(UPDATED_AI_LEVEL);
        assertThat(testMMarathonQuestStage.getStartAtSecondHalf()).isEqualTo(UPDATED_START_AT_SECOND_HALF);
        assertThat(testMMarathonQuestStage.getStartScore()).isEqualTo(UPDATED_START_SCORE);
        assertThat(testMMarathonQuestStage.getStartScoreOpponent()).isEqualTo(UPDATED_START_SCORE_OPPONENT);
        assertThat(testMMarathonQuestStage.getConditionId()).isEqualTo(UPDATED_CONDITION_ID);
        assertThat(testMMarathonQuestStage.getOptionId()).isEqualTo(UPDATED_OPTION_ID);
        assertThat(testMMarathonQuestStage.getDeckConditionId()).isEqualTo(UPDATED_DECK_CONDITION_ID);
    }

    @Test
    @Transactional
    public void updateNonExistingMMarathonQuestStage() throws Exception {
        int databaseSizeBeforeUpdate = mMarathonQuestStageRepository.findAll().size();

        // Create the MMarathonQuestStage
        MMarathonQuestStageDTO mMarathonQuestStageDTO = mMarathonQuestStageMapper.toDto(mMarathonQuestStage);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restMMarathonQuestStageMockMvc.perform(put("/api/m-marathon-quest-stages")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mMarathonQuestStageDTO)))
            .andExpect(status().isBadRequest());

        // Validate the MMarathonQuestStage in the database
        List<MMarathonQuestStage> mMarathonQuestStageList = mMarathonQuestStageRepository.findAll();
        assertThat(mMarathonQuestStageList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteMMarathonQuestStage() throws Exception {
        // Initialize the database
        mMarathonQuestStageRepository.saveAndFlush(mMarathonQuestStage);

        int databaseSizeBeforeDelete = mMarathonQuestStageRepository.findAll().size();

        // Delete the mMarathonQuestStage
        restMMarathonQuestStageMockMvc.perform(delete("/api/m-marathon-quest-stages/{id}", mMarathonQuestStage.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database is empty
        List<MMarathonQuestStage> mMarathonQuestStageList = mMarathonQuestStageRepository.findAll();
        assertThat(mMarathonQuestStageList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(MMarathonQuestStage.class);
        MMarathonQuestStage mMarathonQuestStage1 = new MMarathonQuestStage();
        mMarathonQuestStage1.setId(1L);
        MMarathonQuestStage mMarathonQuestStage2 = new MMarathonQuestStage();
        mMarathonQuestStage2.setId(mMarathonQuestStage1.getId());
        assertThat(mMarathonQuestStage1).isEqualTo(mMarathonQuestStage2);
        mMarathonQuestStage2.setId(2L);
        assertThat(mMarathonQuestStage1).isNotEqualTo(mMarathonQuestStage2);
        mMarathonQuestStage1.setId(null);
        assertThat(mMarathonQuestStage1).isNotEqualTo(mMarathonQuestStage2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(MMarathonQuestStageDTO.class);
        MMarathonQuestStageDTO mMarathonQuestStageDTO1 = new MMarathonQuestStageDTO();
        mMarathonQuestStageDTO1.setId(1L);
        MMarathonQuestStageDTO mMarathonQuestStageDTO2 = new MMarathonQuestStageDTO();
        assertThat(mMarathonQuestStageDTO1).isNotEqualTo(mMarathonQuestStageDTO2);
        mMarathonQuestStageDTO2.setId(mMarathonQuestStageDTO1.getId());
        assertThat(mMarathonQuestStageDTO1).isEqualTo(mMarathonQuestStageDTO2);
        mMarathonQuestStageDTO2.setId(2L);
        assertThat(mMarathonQuestStageDTO1).isNotEqualTo(mMarathonQuestStageDTO2);
        mMarathonQuestStageDTO1.setId(null);
        assertThat(mMarathonQuestStageDTO1).isNotEqualTo(mMarathonQuestStageDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(mMarathonQuestStageMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(mMarathonQuestStageMapper.fromId(null)).isNull();
    }
}
