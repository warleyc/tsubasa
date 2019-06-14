package io.shm.tsubasa.web.rest;

import io.shm.tsubasa.TsubasaApp;
import io.shm.tsubasa.domain.MGuerillaQuestStage;
import io.shm.tsubasa.domain.MGuerillaQuestWorld;
import io.shm.tsubasa.repository.MGuerillaQuestStageRepository;
import io.shm.tsubasa.service.MGuerillaQuestStageService;
import io.shm.tsubasa.service.dto.MGuerillaQuestStageDTO;
import io.shm.tsubasa.service.mapper.MGuerillaQuestStageMapper;
import io.shm.tsubasa.web.rest.errors.ExceptionTranslator;
import io.shm.tsubasa.service.dto.MGuerillaQuestStageCriteria;
import io.shm.tsubasa.service.MGuerillaQuestStageQueryService;

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
 * Integration tests for the {@Link MGuerillaQuestStageResource} REST controller.
 */
@SpringBootTest(classes = TsubasaApp.class)
public class MGuerillaQuestStageResourceIT {

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
    private MGuerillaQuestStageRepository mGuerillaQuestStageRepository;

    @Autowired
    private MGuerillaQuestStageMapper mGuerillaQuestStageMapper;

    @Autowired
    private MGuerillaQuestStageService mGuerillaQuestStageService;

    @Autowired
    private MGuerillaQuestStageQueryService mGuerillaQuestStageQueryService;

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

    private MockMvc restMGuerillaQuestStageMockMvc;

    private MGuerillaQuestStage mGuerillaQuestStage;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final MGuerillaQuestStageResource mGuerillaQuestStageResource = new MGuerillaQuestStageResource(mGuerillaQuestStageService, mGuerillaQuestStageQueryService);
        this.restMGuerillaQuestStageMockMvc = MockMvcBuilders.standaloneSetup(mGuerillaQuestStageResource)
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
    public static MGuerillaQuestStage createEntity(EntityManager em) {
        MGuerillaQuestStage mGuerillaQuestStage = new MGuerillaQuestStage()
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
        MGuerillaQuestWorld mGuerillaQuestWorld;
        if (TestUtil.findAll(em, MGuerillaQuestWorld.class).isEmpty()) {
            mGuerillaQuestWorld = MGuerillaQuestWorldResourceIT.createEntity(em);
            em.persist(mGuerillaQuestWorld);
            em.flush();
        } else {
            mGuerillaQuestWorld = TestUtil.findAll(em, MGuerillaQuestWorld.class).get(0);
        }
        mGuerillaQuestStage.setId(mGuerillaQuestWorld);
        return mGuerillaQuestStage;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static MGuerillaQuestStage createUpdatedEntity(EntityManager em) {
        MGuerillaQuestStage mGuerillaQuestStage = new MGuerillaQuestStage()
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
        MGuerillaQuestWorld mGuerillaQuestWorld;
        if (TestUtil.findAll(em, MGuerillaQuestWorld.class).isEmpty()) {
            mGuerillaQuestWorld = MGuerillaQuestWorldResourceIT.createUpdatedEntity(em);
            em.persist(mGuerillaQuestWorld);
            em.flush();
        } else {
            mGuerillaQuestWorld = TestUtil.findAll(em, MGuerillaQuestWorld.class).get(0);
        }
        mGuerillaQuestStage.setId(mGuerillaQuestWorld);
        return mGuerillaQuestStage;
    }

    @BeforeEach
    public void initTest() {
        mGuerillaQuestStage = createEntity(em);
    }

    @Test
    @Transactional
    public void createMGuerillaQuestStage() throws Exception {
        int databaseSizeBeforeCreate = mGuerillaQuestStageRepository.findAll().size();

        // Create the MGuerillaQuestStage
        MGuerillaQuestStageDTO mGuerillaQuestStageDTO = mGuerillaQuestStageMapper.toDto(mGuerillaQuestStage);
        restMGuerillaQuestStageMockMvc.perform(post("/api/m-guerilla-quest-stages")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mGuerillaQuestStageDTO)))
            .andExpect(status().isCreated());

        // Validate the MGuerillaQuestStage in the database
        List<MGuerillaQuestStage> mGuerillaQuestStageList = mGuerillaQuestStageRepository.findAll();
        assertThat(mGuerillaQuestStageList).hasSize(databaseSizeBeforeCreate + 1);
        MGuerillaQuestStage testMGuerillaQuestStage = mGuerillaQuestStageList.get(mGuerillaQuestStageList.size() - 1);
        assertThat(testMGuerillaQuestStage.getWorldId()).isEqualTo(DEFAULT_WORLD_ID);
        assertThat(testMGuerillaQuestStage.getNumber()).isEqualTo(DEFAULT_NUMBER);
        assertThat(testMGuerillaQuestStage.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testMGuerillaQuestStage.getImagePath()).isEqualTo(DEFAULT_IMAGE_PATH);
        assertThat(testMGuerillaQuestStage.getCharacterThumbnailPath()).isEqualTo(DEFAULT_CHARACTER_THUMBNAIL_PATH);
        assertThat(testMGuerillaQuestStage.getDifficulty()).isEqualTo(DEFAULT_DIFFICULTY);
        assertThat(testMGuerillaQuestStage.getStageUnlockPattern()).isEqualTo(DEFAULT_STAGE_UNLOCK_PATTERN);
        assertThat(testMGuerillaQuestStage.getStoryOnly()).isEqualTo(DEFAULT_STORY_ONLY);
        assertThat(testMGuerillaQuestStage.getFirstHalfNpcDeckId()).isEqualTo(DEFAULT_FIRST_HALF_NPC_DECK_ID);
        assertThat(testMGuerillaQuestStage.getFirstHalfEnvironmentId()).isEqualTo(DEFAULT_FIRST_HALF_ENVIRONMENT_ID);
        assertThat(testMGuerillaQuestStage.getSecondHalfNpcDeckId()).isEqualTo(DEFAULT_SECOND_HALF_NPC_DECK_ID);
        assertThat(testMGuerillaQuestStage.getSecondHalfEnvironmentId()).isEqualTo(DEFAULT_SECOND_HALF_ENVIRONMENT_ID);
        assertThat(testMGuerillaQuestStage.getExtraFirstHalfNpcDeckId()).isEqualTo(DEFAULT_EXTRA_FIRST_HALF_NPC_DECK_ID);
        assertThat(testMGuerillaQuestStage.getExtraSecondHalfNpcDeckId()).isEqualTo(DEFAULT_EXTRA_SECOND_HALF_NPC_DECK_ID);
        assertThat(testMGuerillaQuestStage.getConsumeAp()).isEqualTo(DEFAULT_CONSUME_AP);
        assertThat(testMGuerillaQuestStage.getKickOffType()).isEqualTo(DEFAULT_KICK_OFF_TYPE);
        assertThat(testMGuerillaQuestStage.getMatchMinute()).isEqualTo(DEFAULT_MATCH_MINUTE);
        assertThat(testMGuerillaQuestStage.getEnableExtra()).isEqualTo(DEFAULT_ENABLE_EXTRA);
        assertThat(testMGuerillaQuestStage.getEnablePk()).isEqualTo(DEFAULT_ENABLE_PK);
        assertThat(testMGuerillaQuestStage.getAiLevel()).isEqualTo(DEFAULT_AI_LEVEL);
        assertThat(testMGuerillaQuestStage.getStartAtSecondHalf()).isEqualTo(DEFAULT_START_AT_SECOND_HALF);
        assertThat(testMGuerillaQuestStage.getStartScore()).isEqualTo(DEFAULT_START_SCORE);
        assertThat(testMGuerillaQuestStage.getStartScoreOpponent()).isEqualTo(DEFAULT_START_SCORE_OPPONENT);
        assertThat(testMGuerillaQuestStage.getConditionId()).isEqualTo(DEFAULT_CONDITION_ID);
        assertThat(testMGuerillaQuestStage.getOptionId()).isEqualTo(DEFAULT_OPTION_ID);
        assertThat(testMGuerillaQuestStage.getDeckConditionId()).isEqualTo(DEFAULT_DECK_CONDITION_ID);
    }

    @Test
    @Transactional
    public void createMGuerillaQuestStageWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = mGuerillaQuestStageRepository.findAll().size();

        // Create the MGuerillaQuestStage with an existing ID
        mGuerillaQuestStage.setId(1L);
        MGuerillaQuestStageDTO mGuerillaQuestStageDTO = mGuerillaQuestStageMapper.toDto(mGuerillaQuestStage);

        // An entity with an existing ID cannot be created, so this API call must fail
        restMGuerillaQuestStageMockMvc.perform(post("/api/m-guerilla-quest-stages")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mGuerillaQuestStageDTO)))
            .andExpect(status().isBadRequest());

        // Validate the MGuerillaQuestStage in the database
        List<MGuerillaQuestStage> mGuerillaQuestStageList = mGuerillaQuestStageRepository.findAll();
        assertThat(mGuerillaQuestStageList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkWorldIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = mGuerillaQuestStageRepository.findAll().size();
        // set the field null
        mGuerillaQuestStage.setWorldId(null);

        // Create the MGuerillaQuestStage, which fails.
        MGuerillaQuestStageDTO mGuerillaQuestStageDTO = mGuerillaQuestStageMapper.toDto(mGuerillaQuestStage);

        restMGuerillaQuestStageMockMvc.perform(post("/api/m-guerilla-quest-stages")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mGuerillaQuestStageDTO)))
            .andExpect(status().isBadRequest());

        List<MGuerillaQuestStage> mGuerillaQuestStageList = mGuerillaQuestStageRepository.findAll();
        assertThat(mGuerillaQuestStageList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkNumberIsRequired() throws Exception {
        int databaseSizeBeforeTest = mGuerillaQuestStageRepository.findAll().size();
        // set the field null
        mGuerillaQuestStage.setNumber(null);

        // Create the MGuerillaQuestStage, which fails.
        MGuerillaQuestStageDTO mGuerillaQuestStageDTO = mGuerillaQuestStageMapper.toDto(mGuerillaQuestStage);

        restMGuerillaQuestStageMockMvc.perform(post("/api/m-guerilla-quest-stages")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mGuerillaQuestStageDTO)))
            .andExpect(status().isBadRequest());

        List<MGuerillaQuestStage> mGuerillaQuestStageList = mGuerillaQuestStageRepository.findAll();
        assertThat(mGuerillaQuestStageList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkDifficultyIsRequired() throws Exception {
        int databaseSizeBeforeTest = mGuerillaQuestStageRepository.findAll().size();
        // set the field null
        mGuerillaQuestStage.setDifficulty(null);

        // Create the MGuerillaQuestStage, which fails.
        MGuerillaQuestStageDTO mGuerillaQuestStageDTO = mGuerillaQuestStageMapper.toDto(mGuerillaQuestStage);

        restMGuerillaQuestStageMockMvc.perform(post("/api/m-guerilla-quest-stages")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mGuerillaQuestStageDTO)))
            .andExpect(status().isBadRequest());

        List<MGuerillaQuestStage> mGuerillaQuestStageList = mGuerillaQuestStageRepository.findAll();
        assertThat(mGuerillaQuestStageList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkStageUnlockPatternIsRequired() throws Exception {
        int databaseSizeBeforeTest = mGuerillaQuestStageRepository.findAll().size();
        // set the field null
        mGuerillaQuestStage.setStageUnlockPattern(null);

        // Create the MGuerillaQuestStage, which fails.
        MGuerillaQuestStageDTO mGuerillaQuestStageDTO = mGuerillaQuestStageMapper.toDto(mGuerillaQuestStage);

        restMGuerillaQuestStageMockMvc.perform(post("/api/m-guerilla-quest-stages")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mGuerillaQuestStageDTO)))
            .andExpect(status().isBadRequest());

        List<MGuerillaQuestStage> mGuerillaQuestStageList = mGuerillaQuestStageRepository.findAll();
        assertThat(mGuerillaQuestStageList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkStoryOnlyIsRequired() throws Exception {
        int databaseSizeBeforeTest = mGuerillaQuestStageRepository.findAll().size();
        // set the field null
        mGuerillaQuestStage.setStoryOnly(null);

        // Create the MGuerillaQuestStage, which fails.
        MGuerillaQuestStageDTO mGuerillaQuestStageDTO = mGuerillaQuestStageMapper.toDto(mGuerillaQuestStage);

        restMGuerillaQuestStageMockMvc.perform(post("/api/m-guerilla-quest-stages")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mGuerillaQuestStageDTO)))
            .andExpect(status().isBadRequest());

        List<MGuerillaQuestStage> mGuerillaQuestStageList = mGuerillaQuestStageRepository.findAll();
        assertThat(mGuerillaQuestStageList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkFirstHalfNpcDeckIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = mGuerillaQuestStageRepository.findAll().size();
        // set the field null
        mGuerillaQuestStage.setFirstHalfNpcDeckId(null);

        // Create the MGuerillaQuestStage, which fails.
        MGuerillaQuestStageDTO mGuerillaQuestStageDTO = mGuerillaQuestStageMapper.toDto(mGuerillaQuestStage);

        restMGuerillaQuestStageMockMvc.perform(post("/api/m-guerilla-quest-stages")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mGuerillaQuestStageDTO)))
            .andExpect(status().isBadRequest());

        List<MGuerillaQuestStage> mGuerillaQuestStageList = mGuerillaQuestStageRepository.findAll();
        assertThat(mGuerillaQuestStageList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkFirstHalfEnvironmentIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = mGuerillaQuestStageRepository.findAll().size();
        // set the field null
        mGuerillaQuestStage.setFirstHalfEnvironmentId(null);

        // Create the MGuerillaQuestStage, which fails.
        MGuerillaQuestStageDTO mGuerillaQuestStageDTO = mGuerillaQuestStageMapper.toDto(mGuerillaQuestStage);

        restMGuerillaQuestStageMockMvc.perform(post("/api/m-guerilla-quest-stages")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mGuerillaQuestStageDTO)))
            .andExpect(status().isBadRequest());

        List<MGuerillaQuestStage> mGuerillaQuestStageList = mGuerillaQuestStageRepository.findAll();
        assertThat(mGuerillaQuestStageList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkSecondHalfNpcDeckIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = mGuerillaQuestStageRepository.findAll().size();
        // set the field null
        mGuerillaQuestStage.setSecondHalfNpcDeckId(null);

        // Create the MGuerillaQuestStage, which fails.
        MGuerillaQuestStageDTO mGuerillaQuestStageDTO = mGuerillaQuestStageMapper.toDto(mGuerillaQuestStage);

        restMGuerillaQuestStageMockMvc.perform(post("/api/m-guerilla-quest-stages")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mGuerillaQuestStageDTO)))
            .andExpect(status().isBadRequest());

        List<MGuerillaQuestStage> mGuerillaQuestStageList = mGuerillaQuestStageRepository.findAll();
        assertThat(mGuerillaQuestStageList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkSecondHalfEnvironmentIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = mGuerillaQuestStageRepository.findAll().size();
        // set the field null
        mGuerillaQuestStage.setSecondHalfEnvironmentId(null);

        // Create the MGuerillaQuestStage, which fails.
        MGuerillaQuestStageDTO mGuerillaQuestStageDTO = mGuerillaQuestStageMapper.toDto(mGuerillaQuestStage);

        restMGuerillaQuestStageMockMvc.perform(post("/api/m-guerilla-quest-stages")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mGuerillaQuestStageDTO)))
            .andExpect(status().isBadRequest());

        List<MGuerillaQuestStage> mGuerillaQuestStageList = mGuerillaQuestStageRepository.findAll();
        assertThat(mGuerillaQuestStageList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkExtraFirstHalfNpcDeckIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = mGuerillaQuestStageRepository.findAll().size();
        // set the field null
        mGuerillaQuestStage.setExtraFirstHalfNpcDeckId(null);

        // Create the MGuerillaQuestStage, which fails.
        MGuerillaQuestStageDTO mGuerillaQuestStageDTO = mGuerillaQuestStageMapper.toDto(mGuerillaQuestStage);

        restMGuerillaQuestStageMockMvc.perform(post("/api/m-guerilla-quest-stages")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mGuerillaQuestStageDTO)))
            .andExpect(status().isBadRequest());

        List<MGuerillaQuestStage> mGuerillaQuestStageList = mGuerillaQuestStageRepository.findAll();
        assertThat(mGuerillaQuestStageList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkExtraSecondHalfNpcDeckIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = mGuerillaQuestStageRepository.findAll().size();
        // set the field null
        mGuerillaQuestStage.setExtraSecondHalfNpcDeckId(null);

        // Create the MGuerillaQuestStage, which fails.
        MGuerillaQuestStageDTO mGuerillaQuestStageDTO = mGuerillaQuestStageMapper.toDto(mGuerillaQuestStage);

        restMGuerillaQuestStageMockMvc.perform(post("/api/m-guerilla-quest-stages")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mGuerillaQuestStageDTO)))
            .andExpect(status().isBadRequest());

        List<MGuerillaQuestStage> mGuerillaQuestStageList = mGuerillaQuestStageRepository.findAll();
        assertThat(mGuerillaQuestStageList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkConsumeApIsRequired() throws Exception {
        int databaseSizeBeforeTest = mGuerillaQuestStageRepository.findAll().size();
        // set the field null
        mGuerillaQuestStage.setConsumeAp(null);

        // Create the MGuerillaQuestStage, which fails.
        MGuerillaQuestStageDTO mGuerillaQuestStageDTO = mGuerillaQuestStageMapper.toDto(mGuerillaQuestStage);

        restMGuerillaQuestStageMockMvc.perform(post("/api/m-guerilla-quest-stages")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mGuerillaQuestStageDTO)))
            .andExpect(status().isBadRequest());

        List<MGuerillaQuestStage> mGuerillaQuestStageList = mGuerillaQuestStageRepository.findAll();
        assertThat(mGuerillaQuestStageList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkKickOffTypeIsRequired() throws Exception {
        int databaseSizeBeforeTest = mGuerillaQuestStageRepository.findAll().size();
        // set the field null
        mGuerillaQuestStage.setKickOffType(null);

        // Create the MGuerillaQuestStage, which fails.
        MGuerillaQuestStageDTO mGuerillaQuestStageDTO = mGuerillaQuestStageMapper.toDto(mGuerillaQuestStage);

        restMGuerillaQuestStageMockMvc.perform(post("/api/m-guerilla-quest-stages")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mGuerillaQuestStageDTO)))
            .andExpect(status().isBadRequest());

        List<MGuerillaQuestStage> mGuerillaQuestStageList = mGuerillaQuestStageRepository.findAll();
        assertThat(mGuerillaQuestStageList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkMatchMinuteIsRequired() throws Exception {
        int databaseSizeBeforeTest = mGuerillaQuestStageRepository.findAll().size();
        // set the field null
        mGuerillaQuestStage.setMatchMinute(null);

        // Create the MGuerillaQuestStage, which fails.
        MGuerillaQuestStageDTO mGuerillaQuestStageDTO = mGuerillaQuestStageMapper.toDto(mGuerillaQuestStage);

        restMGuerillaQuestStageMockMvc.perform(post("/api/m-guerilla-quest-stages")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mGuerillaQuestStageDTO)))
            .andExpect(status().isBadRequest());

        List<MGuerillaQuestStage> mGuerillaQuestStageList = mGuerillaQuestStageRepository.findAll();
        assertThat(mGuerillaQuestStageList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkEnableExtraIsRequired() throws Exception {
        int databaseSizeBeforeTest = mGuerillaQuestStageRepository.findAll().size();
        // set the field null
        mGuerillaQuestStage.setEnableExtra(null);

        // Create the MGuerillaQuestStage, which fails.
        MGuerillaQuestStageDTO mGuerillaQuestStageDTO = mGuerillaQuestStageMapper.toDto(mGuerillaQuestStage);

        restMGuerillaQuestStageMockMvc.perform(post("/api/m-guerilla-quest-stages")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mGuerillaQuestStageDTO)))
            .andExpect(status().isBadRequest());

        List<MGuerillaQuestStage> mGuerillaQuestStageList = mGuerillaQuestStageRepository.findAll();
        assertThat(mGuerillaQuestStageList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkEnablePkIsRequired() throws Exception {
        int databaseSizeBeforeTest = mGuerillaQuestStageRepository.findAll().size();
        // set the field null
        mGuerillaQuestStage.setEnablePk(null);

        // Create the MGuerillaQuestStage, which fails.
        MGuerillaQuestStageDTO mGuerillaQuestStageDTO = mGuerillaQuestStageMapper.toDto(mGuerillaQuestStage);

        restMGuerillaQuestStageMockMvc.perform(post("/api/m-guerilla-quest-stages")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mGuerillaQuestStageDTO)))
            .andExpect(status().isBadRequest());

        List<MGuerillaQuestStage> mGuerillaQuestStageList = mGuerillaQuestStageRepository.findAll();
        assertThat(mGuerillaQuestStageList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkAiLevelIsRequired() throws Exception {
        int databaseSizeBeforeTest = mGuerillaQuestStageRepository.findAll().size();
        // set the field null
        mGuerillaQuestStage.setAiLevel(null);

        // Create the MGuerillaQuestStage, which fails.
        MGuerillaQuestStageDTO mGuerillaQuestStageDTO = mGuerillaQuestStageMapper.toDto(mGuerillaQuestStage);

        restMGuerillaQuestStageMockMvc.perform(post("/api/m-guerilla-quest-stages")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mGuerillaQuestStageDTO)))
            .andExpect(status().isBadRequest());

        List<MGuerillaQuestStage> mGuerillaQuestStageList = mGuerillaQuestStageRepository.findAll();
        assertThat(mGuerillaQuestStageList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkStartAtSecondHalfIsRequired() throws Exception {
        int databaseSizeBeforeTest = mGuerillaQuestStageRepository.findAll().size();
        // set the field null
        mGuerillaQuestStage.setStartAtSecondHalf(null);

        // Create the MGuerillaQuestStage, which fails.
        MGuerillaQuestStageDTO mGuerillaQuestStageDTO = mGuerillaQuestStageMapper.toDto(mGuerillaQuestStage);

        restMGuerillaQuestStageMockMvc.perform(post("/api/m-guerilla-quest-stages")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mGuerillaQuestStageDTO)))
            .andExpect(status().isBadRequest());

        List<MGuerillaQuestStage> mGuerillaQuestStageList = mGuerillaQuestStageRepository.findAll();
        assertThat(mGuerillaQuestStageList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkStartScoreIsRequired() throws Exception {
        int databaseSizeBeforeTest = mGuerillaQuestStageRepository.findAll().size();
        // set the field null
        mGuerillaQuestStage.setStartScore(null);

        // Create the MGuerillaQuestStage, which fails.
        MGuerillaQuestStageDTO mGuerillaQuestStageDTO = mGuerillaQuestStageMapper.toDto(mGuerillaQuestStage);

        restMGuerillaQuestStageMockMvc.perform(post("/api/m-guerilla-quest-stages")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mGuerillaQuestStageDTO)))
            .andExpect(status().isBadRequest());

        List<MGuerillaQuestStage> mGuerillaQuestStageList = mGuerillaQuestStageRepository.findAll();
        assertThat(mGuerillaQuestStageList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkStartScoreOpponentIsRequired() throws Exception {
        int databaseSizeBeforeTest = mGuerillaQuestStageRepository.findAll().size();
        // set the field null
        mGuerillaQuestStage.setStartScoreOpponent(null);

        // Create the MGuerillaQuestStage, which fails.
        MGuerillaQuestStageDTO mGuerillaQuestStageDTO = mGuerillaQuestStageMapper.toDto(mGuerillaQuestStage);

        restMGuerillaQuestStageMockMvc.perform(post("/api/m-guerilla-quest-stages")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mGuerillaQuestStageDTO)))
            .andExpect(status().isBadRequest());

        List<MGuerillaQuestStage> mGuerillaQuestStageList = mGuerillaQuestStageRepository.findAll();
        assertThat(mGuerillaQuestStageList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllMGuerillaQuestStages() throws Exception {
        // Initialize the database
        mGuerillaQuestStageRepository.saveAndFlush(mGuerillaQuestStage);

        // Get all the mGuerillaQuestStageList
        restMGuerillaQuestStageMockMvc.perform(get("/api/m-guerilla-quest-stages?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(mGuerillaQuestStage.getId().intValue())))
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
    public void getMGuerillaQuestStage() throws Exception {
        // Initialize the database
        mGuerillaQuestStageRepository.saveAndFlush(mGuerillaQuestStage);

        // Get the mGuerillaQuestStage
        restMGuerillaQuestStageMockMvc.perform(get("/api/m-guerilla-quest-stages/{id}", mGuerillaQuestStage.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(mGuerillaQuestStage.getId().intValue()))
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
    public void getAllMGuerillaQuestStagesByWorldIdIsEqualToSomething() throws Exception {
        // Initialize the database
        mGuerillaQuestStageRepository.saveAndFlush(mGuerillaQuestStage);

        // Get all the mGuerillaQuestStageList where worldId equals to DEFAULT_WORLD_ID
        defaultMGuerillaQuestStageShouldBeFound("worldId.equals=" + DEFAULT_WORLD_ID);

        // Get all the mGuerillaQuestStageList where worldId equals to UPDATED_WORLD_ID
        defaultMGuerillaQuestStageShouldNotBeFound("worldId.equals=" + UPDATED_WORLD_ID);
    }

    @Test
    @Transactional
    public void getAllMGuerillaQuestStagesByWorldIdIsInShouldWork() throws Exception {
        // Initialize the database
        mGuerillaQuestStageRepository.saveAndFlush(mGuerillaQuestStage);

        // Get all the mGuerillaQuestStageList where worldId in DEFAULT_WORLD_ID or UPDATED_WORLD_ID
        defaultMGuerillaQuestStageShouldBeFound("worldId.in=" + DEFAULT_WORLD_ID + "," + UPDATED_WORLD_ID);

        // Get all the mGuerillaQuestStageList where worldId equals to UPDATED_WORLD_ID
        defaultMGuerillaQuestStageShouldNotBeFound("worldId.in=" + UPDATED_WORLD_ID);
    }

    @Test
    @Transactional
    public void getAllMGuerillaQuestStagesByWorldIdIsNullOrNotNull() throws Exception {
        // Initialize the database
        mGuerillaQuestStageRepository.saveAndFlush(mGuerillaQuestStage);

        // Get all the mGuerillaQuestStageList where worldId is not null
        defaultMGuerillaQuestStageShouldBeFound("worldId.specified=true");

        // Get all the mGuerillaQuestStageList where worldId is null
        defaultMGuerillaQuestStageShouldNotBeFound("worldId.specified=false");
    }

    @Test
    @Transactional
    public void getAllMGuerillaQuestStagesByWorldIdIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mGuerillaQuestStageRepository.saveAndFlush(mGuerillaQuestStage);

        // Get all the mGuerillaQuestStageList where worldId greater than or equals to DEFAULT_WORLD_ID
        defaultMGuerillaQuestStageShouldBeFound("worldId.greaterOrEqualThan=" + DEFAULT_WORLD_ID);

        // Get all the mGuerillaQuestStageList where worldId greater than or equals to UPDATED_WORLD_ID
        defaultMGuerillaQuestStageShouldNotBeFound("worldId.greaterOrEqualThan=" + UPDATED_WORLD_ID);
    }

    @Test
    @Transactional
    public void getAllMGuerillaQuestStagesByWorldIdIsLessThanSomething() throws Exception {
        // Initialize the database
        mGuerillaQuestStageRepository.saveAndFlush(mGuerillaQuestStage);

        // Get all the mGuerillaQuestStageList where worldId less than or equals to DEFAULT_WORLD_ID
        defaultMGuerillaQuestStageShouldNotBeFound("worldId.lessThan=" + DEFAULT_WORLD_ID);

        // Get all the mGuerillaQuestStageList where worldId less than or equals to UPDATED_WORLD_ID
        defaultMGuerillaQuestStageShouldBeFound("worldId.lessThan=" + UPDATED_WORLD_ID);
    }


    @Test
    @Transactional
    public void getAllMGuerillaQuestStagesByNumberIsEqualToSomething() throws Exception {
        // Initialize the database
        mGuerillaQuestStageRepository.saveAndFlush(mGuerillaQuestStage);

        // Get all the mGuerillaQuestStageList where number equals to DEFAULT_NUMBER
        defaultMGuerillaQuestStageShouldBeFound("number.equals=" + DEFAULT_NUMBER);

        // Get all the mGuerillaQuestStageList where number equals to UPDATED_NUMBER
        defaultMGuerillaQuestStageShouldNotBeFound("number.equals=" + UPDATED_NUMBER);
    }

    @Test
    @Transactional
    public void getAllMGuerillaQuestStagesByNumberIsInShouldWork() throws Exception {
        // Initialize the database
        mGuerillaQuestStageRepository.saveAndFlush(mGuerillaQuestStage);

        // Get all the mGuerillaQuestStageList where number in DEFAULT_NUMBER or UPDATED_NUMBER
        defaultMGuerillaQuestStageShouldBeFound("number.in=" + DEFAULT_NUMBER + "," + UPDATED_NUMBER);

        // Get all the mGuerillaQuestStageList where number equals to UPDATED_NUMBER
        defaultMGuerillaQuestStageShouldNotBeFound("number.in=" + UPDATED_NUMBER);
    }

    @Test
    @Transactional
    public void getAllMGuerillaQuestStagesByNumberIsNullOrNotNull() throws Exception {
        // Initialize the database
        mGuerillaQuestStageRepository.saveAndFlush(mGuerillaQuestStage);

        // Get all the mGuerillaQuestStageList where number is not null
        defaultMGuerillaQuestStageShouldBeFound("number.specified=true");

        // Get all the mGuerillaQuestStageList where number is null
        defaultMGuerillaQuestStageShouldNotBeFound("number.specified=false");
    }

    @Test
    @Transactional
    public void getAllMGuerillaQuestStagesByNumberIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mGuerillaQuestStageRepository.saveAndFlush(mGuerillaQuestStage);

        // Get all the mGuerillaQuestStageList where number greater than or equals to DEFAULT_NUMBER
        defaultMGuerillaQuestStageShouldBeFound("number.greaterOrEqualThan=" + DEFAULT_NUMBER);

        // Get all the mGuerillaQuestStageList where number greater than or equals to UPDATED_NUMBER
        defaultMGuerillaQuestStageShouldNotBeFound("number.greaterOrEqualThan=" + UPDATED_NUMBER);
    }

    @Test
    @Transactional
    public void getAllMGuerillaQuestStagesByNumberIsLessThanSomething() throws Exception {
        // Initialize the database
        mGuerillaQuestStageRepository.saveAndFlush(mGuerillaQuestStage);

        // Get all the mGuerillaQuestStageList where number less than or equals to DEFAULT_NUMBER
        defaultMGuerillaQuestStageShouldNotBeFound("number.lessThan=" + DEFAULT_NUMBER);

        // Get all the mGuerillaQuestStageList where number less than or equals to UPDATED_NUMBER
        defaultMGuerillaQuestStageShouldBeFound("number.lessThan=" + UPDATED_NUMBER);
    }


    @Test
    @Transactional
    public void getAllMGuerillaQuestStagesByDifficultyIsEqualToSomething() throws Exception {
        // Initialize the database
        mGuerillaQuestStageRepository.saveAndFlush(mGuerillaQuestStage);

        // Get all the mGuerillaQuestStageList where difficulty equals to DEFAULT_DIFFICULTY
        defaultMGuerillaQuestStageShouldBeFound("difficulty.equals=" + DEFAULT_DIFFICULTY);

        // Get all the mGuerillaQuestStageList where difficulty equals to UPDATED_DIFFICULTY
        defaultMGuerillaQuestStageShouldNotBeFound("difficulty.equals=" + UPDATED_DIFFICULTY);
    }

    @Test
    @Transactional
    public void getAllMGuerillaQuestStagesByDifficultyIsInShouldWork() throws Exception {
        // Initialize the database
        mGuerillaQuestStageRepository.saveAndFlush(mGuerillaQuestStage);

        // Get all the mGuerillaQuestStageList where difficulty in DEFAULT_DIFFICULTY or UPDATED_DIFFICULTY
        defaultMGuerillaQuestStageShouldBeFound("difficulty.in=" + DEFAULT_DIFFICULTY + "," + UPDATED_DIFFICULTY);

        // Get all the mGuerillaQuestStageList where difficulty equals to UPDATED_DIFFICULTY
        defaultMGuerillaQuestStageShouldNotBeFound("difficulty.in=" + UPDATED_DIFFICULTY);
    }

    @Test
    @Transactional
    public void getAllMGuerillaQuestStagesByDifficultyIsNullOrNotNull() throws Exception {
        // Initialize the database
        mGuerillaQuestStageRepository.saveAndFlush(mGuerillaQuestStage);

        // Get all the mGuerillaQuestStageList where difficulty is not null
        defaultMGuerillaQuestStageShouldBeFound("difficulty.specified=true");

        // Get all the mGuerillaQuestStageList where difficulty is null
        defaultMGuerillaQuestStageShouldNotBeFound("difficulty.specified=false");
    }

    @Test
    @Transactional
    public void getAllMGuerillaQuestStagesByDifficultyIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mGuerillaQuestStageRepository.saveAndFlush(mGuerillaQuestStage);

        // Get all the mGuerillaQuestStageList where difficulty greater than or equals to DEFAULT_DIFFICULTY
        defaultMGuerillaQuestStageShouldBeFound("difficulty.greaterOrEqualThan=" + DEFAULT_DIFFICULTY);

        // Get all the mGuerillaQuestStageList where difficulty greater than or equals to UPDATED_DIFFICULTY
        defaultMGuerillaQuestStageShouldNotBeFound("difficulty.greaterOrEqualThan=" + UPDATED_DIFFICULTY);
    }

    @Test
    @Transactional
    public void getAllMGuerillaQuestStagesByDifficultyIsLessThanSomething() throws Exception {
        // Initialize the database
        mGuerillaQuestStageRepository.saveAndFlush(mGuerillaQuestStage);

        // Get all the mGuerillaQuestStageList where difficulty less than or equals to DEFAULT_DIFFICULTY
        defaultMGuerillaQuestStageShouldNotBeFound("difficulty.lessThan=" + DEFAULT_DIFFICULTY);

        // Get all the mGuerillaQuestStageList where difficulty less than or equals to UPDATED_DIFFICULTY
        defaultMGuerillaQuestStageShouldBeFound("difficulty.lessThan=" + UPDATED_DIFFICULTY);
    }


    @Test
    @Transactional
    public void getAllMGuerillaQuestStagesByStageUnlockPatternIsEqualToSomething() throws Exception {
        // Initialize the database
        mGuerillaQuestStageRepository.saveAndFlush(mGuerillaQuestStage);

        // Get all the mGuerillaQuestStageList where stageUnlockPattern equals to DEFAULT_STAGE_UNLOCK_PATTERN
        defaultMGuerillaQuestStageShouldBeFound("stageUnlockPattern.equals=" + DEFAULT_STAGE_UNLOCK_PATTERN);

        // Get all the mGuerillaQuestStageList where stageUnlockPattern equals to UPDATED_STAGE_UNLOCK_PATTERN
        defaultMGuerillaQuestStageShouldNotBeFound("stageUnlockPattern.equals=" + UPDATED_STAGE_UNLOCK_PATTERN);
    }

    @Test
    @Transactional
    public void getAllMGuerillaQuestStagesByStageUnlockPatternIsInShouldWork() throws Exception {
        // Initialize the database
        mGuerillaQuestStageRepository.saveAndFlush(mGuerillaQuestStage);

        // Get all the mGuerillaQuestStageList where stageUnlockPattern in DEFAULT_STAGE_UNLOCK_PATTERN or UPDATED_STAGE_UNLOCK_PATTERN
        defaultMGuerillaQuestStageShouldBeFound("stageUnlockPattern.in=" + DEFAULT_STAGE_UNLOCK_PATTERN + "," + UPDATED_STAGE_UNLOCK_PATTERN);

        // Get all the mGuerillaQuestStageList where stageUnlockPattern equals to UPDATED_STAGE_UNLOCK_PATTERN
        defaultMGuerillaQuestStageShouldNotBeFound("stageUnlockPattern.in=" + UPDATED_STAGE_UNLOCK_PATTERN);
    }

    @Test
    @Transactional
    public void getAllMGuerillaQuestStagesByStageUnlockPatternIsNullOrNotNull() throws Exception {
        // Initialize the database
        mGuerillaQuestStageRepository.saveAndFlush(mGuerillaQuestStage);

        // Get all the mGuerillaQuestStageList where stageUnlockPattern is not null
        defaultMGuerillaQuestStageShouldBeFound("stageUnlockPattern.specified=true");

        // Get all the mGuerillaQuestStageList where stageUnlockPattern is null
        defaultMGuerillaQuestStageShouldNotBeFound("stageUnlockPattern.specified=false");
    }

    @Test
    @Transactional
    public void getAllMGuerillaQuestStagesByStageUnlockPatternIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mGuerillaQuestStageRepository.saveAndFlush(mGuerillaQuestStage);

        // Get all the mGuerillaQuestStageList where stageUnlockPattern greater than or equals to DEFAULT_STAGE_UNLOCK_PATTERN
        defaultMGuerillaQuestStageShouldBeFound("stageUnlockPattern.greaterOrEqualThan=" + DEFAULT_STAGE_UNLOCK_PATTERN);

        // Get all the mGuerillaQuestStageList where stageUnlockPattern greater than or equals to UPDATED_STAGE_UNLOCK_PATTERN
        defaultMGuerillaQuestStageShouldNotBeFound("stageUnlockPattern.greaterOrEqualThan=" + UPDATED_STAGE_UNLOCK_PATTERN);
    }

    @Test
    @Transactional
    public void getAllMGuerillaQuestStagesByStageUnlockPatternIsLessThanSomething() throws Exception {
        // Initialize the database
        mGuerillaQuestStageRepository.saveAndFlush(mGuerillaQuestStage);

        // Get all the mGuerillaQuestStageList where stageUnlockPattern less than or equals to DEFAULT_STAGE_UNLOCK_PATTERN
        defaultMGuerillaQuestStageShouldNotBeFound("stageUnlockPattern.lessThan=" + DEFAULT_STAGE_UNLOCK_PATTERN);

        // Get all the mGuerillaQuestStageList where stageUnlockPattern less than or equals to UPDATED_STAGE_UNLOCK_PATTERN
        defaultMGuerillaQuestStageShouldBeFound("stageUnlockPattern.lessThan=" + UPDATED_STAGE_UNLOCK_PATTERN);
    }


    @Test
    @Transactional
    public void getAllMGuerillaQuestStagesByStoryOnlyIsEqualToSomething() throws Exception {
        // Initialize the database
        mGuerillaQuestStageRepository.saveAndFlush(mGuerillaQuestStage);

        // Get all the mGuerillaQuestStageList where storyOnly equals to DEFAULT_STORY_ONLY
        defaultMGuerillaQuestStageShouldBeFound("storyOnly.equals=" + DEFAULT_STORY_ONLY);

        // Get all the mGuerillaQuestStageList where storyOnly equals to UPDATED_STORY_ONLY
        defaultMGuerillaQuestStageShouldNotBeFound("storyOnly.equals=" + UPDATED_STORY_ONLY);
    }

    @Test
    @Transactional
    public void getAllMGuerillaQuestStagesByStoryOnlyIsInShouldWork() throws Exception {
        // Initialize the database
        mGuerillaQuestStageRepository.saveAndFlush(mGuerillaQuestStage);

        // Get all the mGuerillaQuestStageList where storyOnly in DEFAULT_STORY_ONLY or UPDATED_STORY_ONLY
        defaultMGuerillaQuestStageShouldBeFound("storyOnly.in=" + DEFAULT_STORY_ONLY + "," + UPDATED_STORY_ONLY);

        // Get all the mGuerillaQuestStageList where storyOnly equals to UPDATED_STORY_ONLY
        defaultMGuerillaQuestStageShouldNotBeFound("storyOnly.in=" + UPDATED_STORY_ONLY);
    }

    @Test
    @Transactional
    public void getAllMGuerillaQuestStagesByStoryOnlyIsNullOrNotNull() throws Exception {
        // Initialize the database
        mGuerillaQuestStageRepository.saveAndFlush(mGuerillaQuestStage);

        // Get all the mGuerillaQuestStageList where storyOnly is not null
        defaultMGuerillaQuestStageShouldBeFound("storyOnly.specified=true");

        // Get all the mGuerillaQuestStageList where storyOnly is null
        defaultMGuerillaQuestStageShouldNotBeFound("storyOnly.specified=false");
    }

    @Test
    @Transactional
    public void getAllMGuerillaQuestStagesByStoryOnlyIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mGuerillaQuestStageRepository.saveAndFlush(mGuerillaQuestStage);

        // Get all the mGuerillaQuestStageList where storyOnly greater than or equals to DEFAULT_STORY_ONLY
        defaultMGuerillaQuestStageShouldBeFound("storyOnly.greaterOrEqualThan=" + DEFAULT_STORY_ONLY);

        // Get all the mGuerillaQuestStageList where storyOnly greater than or equals to UPDATED_STORY_ONLY
        defaultMGuerillaQuestStageShouldNotBeFound("storyOnly.greaterOrEqualThan=" + UPDATED_STORY_ONLY);
    }

    @Test
    @Transactional
    public void getAllMGuerillaQuestStagesByStoryOnlyIsLessThanSomething() throws Exception {
        // Initialize the database
        mGuerillaQuestStageRepository.saveAndFlush(mGuerillaQuestStage);

        // Get all the mGuerillaQuestStageList where storyOnly less than or equals to DEFAULT_STORY_ONLY
        defaultMGuerillaQuestStageShouldNotBeFound("storyOnly.lessThan=" + DEFAULT_STORY_ONLY);

        // Get all the mGuerillaQuestStageList where storyOnly less than or equals to UPDATED_STORY_ONLY
        defaultMGuerillaQuestStageShouldBeFound("storyOnly.lessThan=" + UPDATED_STORY_ONLY);
    }


    @Test
    @Transactional
    public void getAllMGuerillaQuestStagesByFirstHalfNpcDeckIdIsEqualToSomething() throws Exception {
        // Initialize the database
        mGuerillaQuestStageRepository.saveAndFlush(mGuerillaQuestStage);

        // Get all the mGuerillaQuestStageList where firstHalfNpcDeckId equals to DEFAULT_FIRST_HALF_NPC_DECK_ID
        defaultMGuerillaQuestStageShouldBeFound("firstHalfNpcDeckId.equals=" + DEFAULT_FIRST_HALF_NPC_DECK_ID);

        // Get all the mGuerillaQuestStageList where firstHalfNpcDeckId equals to UPDATED_FIRST_HALF_NPC_DECK_ID
        defaultMGuerillaQuestStageShouldNotBeFound("firstHalfNpcDeckId.equals=" + UPDATED_FIRST_HALF_NPC_DECK_ID);
    }

    @Test
    @Transactional
    public void getAllMGuerillaQuestStagesByFirstHalfNpcDeckIdIsInShouldWork() throws Exception {
        // Initialize the database
        mGuerillaQuestStageRepository.saveAndFlush(mGuerillaQuestStage);

        // Get all the mGuerillaQuestStageList where firstHalfNpcDeckId in DEFAULT_FIRST_HALF_NPC_DECK_ID or UPDATED_FIRST_HALF_NPC_DECK_ID
        defaultMGuerillaQuestStageShouldBeFound("firstHalfNpcDeckId.in=" + DEFAULT_FIRST_HALF_NPC_DECK_ID + "," + UPDATED_FIRST_HALF_NPC_DECK_ID);

        // Get all the mGuerillaQuestStageList where firstHalfNpcDeckId equals to UPDATED_FIRST_HALF_NPC_DECK_ID
        defaultMGuerillaQuestStageShouldNotBeFound("firstHalfNpcDeckId.in=" + UPDATED_FIRST_HALF_NPC_DECK_ID);
    }

    @Test
    @Transactional
    public void getAllMGuerillaQuestStagesByFirstHalfNpcDeckIdIsNullOrNotNull() throws Exception {
        // Initialize the database
        mGuerillaQuestStageRepository.saveAndFlush(mGuerillaQuestStage);

        // Get all the mGuerillaQuestStageList where firstHalfNpcDeckId is not null
        defaultMGuerillaQuestStageShouldBeFound("firstHalfNpcDeckId.specified=true");

        // Get all the mGuerillaQuestStageList where firstHalfNpcDeckId is null
        defaultMGuerillaQuestStageShouldNotBeFound("firstHalfNpcDeckId.specified=false");
    }

    @Test
    @Transactional
    public void getAllMGuerillaQuestStagesByFirstHalfNpcDeckIdIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mGuerillaQuestStageRepository.saveAndFlush(mGuerillaQuestStage);

        // Get all the mGuerillaQuestStageList where firstHalfNpcDeckId greater than or equals to DEFAULT_FIRST_HALF_NPC_DECK_ID
        defaultMGuerillaQuestStageShouldBeFound("firstHalfNpcDeckId.greaterOrEqualThan=" + DEFAULT_FIRST_HALF_NPC_DECK_ID);

        // Get all the mGuerillaQuestStageList where firstHalfNpcDeckId greater than or equals to UPDATED_FIRST_HALF_NPC_DECK_ID
        defaultMGuerillaQuestStageShouldNotBeFound("firstHalfNpcDeckId.greaterOrEqualThan=" + UPDATED_FIRST_HALF_NPC_DECK_ID);
    }

    @Test
    @Transactional
    public void getAllMGuerillaQuestStagesByFirstHalfNpcDeckIdIsLessThanSomething() throws Exception {
        // Initialize the database
        mGuerillaQuestStageRepository.saveAndFlush(mGuerillaQuestStage);

        // Get all the mGuerillaQuestStageList where firstHalfNpcDeckId less than or equals to DEFAULT_FIRST_HALF_NPC_DECK_ID
        defaultMGuerillaQuestStageShouldNotBeFound("firstHalfNpcDeckId.lessThan=" + DEFAULT_FIRST_HALF_NPC_DECK_ID);

        // Get all the mGuerillaQuestStageList where firstHalfNpcDeckId less than or equals to UPDATED_FIRST_HALF_NPC_DECK_ID
        defaultMGuerillaQuestStageShouldBeFound("firstHalfNpcDeckId.lessThan=" + UPDATED_FIRST_HALF_NPC_DECK_ID);
    }


    @Test
    @Transactional
    public void getAllMGuerillaQuestStagesByFirstHalfEnvironmentIdIsEqualToSomething() throws Exception {
        // Initialize the database
        mGuerillaQuestStageRepository.saveAndFlush(mGuerillaQuestStage);

        // Get all the mGuerillaQuestStageList where firstHalfEnvironmentId equals to DEFAULT_FIRST_HALF_ENVIRONMENT_ID
        defaultMGuerillaQuestStageShouldBeFound("firstHalfEnvironmentId.equals=" + DEFAULT_FIRST_HALF_ENVIRONMENT_ID);

        // Get all the mGuerillaQuestStageList where firstHalfEnvironmentId equals to UPDATED_FIRST_HALF_ENVIRONMENT_ID
        defaultMGuerillaQuestStageShouldNotBeFound("firstHalfEnvironmentId.equals=" + UPDATED_FIRST_HALF_ENVIRONMENT_ID);
    }

    @Test
    @Transactional
    public void getAllMGuerillaQuestStagesByFirstHalfEnvironmentIdIsInShouldWork() throws Exception {
        // Initialize the database
        mGuerillaQuestStageRepository.saveAndFlush(mGuerillaQuestStage);

        // Get all the mGuerillaQuestStageList where firstHalfEnvironmentId in DEFAULT_FIRST_HALF_ENVIRONMENT_ID or UPDATED_FIRST_HALF_ENVIRONMENT_ID
        defaultMGuerillaQuestStageShouldBeFound("firstHalfEnvironmentId.in=" + DEFAULT_FIRST_HALF_ENVIRONMENT_ID + "," + UPDATED_FIRST_HALF_ENVIRONMENT_ID);

        // Get all the mGuerillaQuestStageList where firstHalfEnvironmentId equals to UPDATED_FIRST_HALF_ENVIRONMENT_ID
        defaultMGuerillaQuestStageShouldNotBeFound("firstHalfEnvironmentId.in=" + UPDATED_FIRST_HALF_ENVIRONMENT_ID);
    }

    @Test
    @Transactional
    public void getAllMGuerillaQuestStagesByFirstHalfEnvironmentIdIsNullOrNotNull() throws Exception {
        // Initialize the database
        mGuerillaQuestStageRepository.saveAndFlush(mGuerillaQuestStage);

        // Get all the mGuerillaQuestStageList where firstHalfEnvironmentId is not null
        defaultMGuerillaQuestStageShouldBeFound("firstHalfEnvironmentId.specified=true");

        // Get all the mGuerillaQuestStageList where firstHalfEnvironmentId is null
        defaultMGuerillaQuestStageShouldNotBeFound("firstHalfEnvironmentId.specified=false");
    }

    @Test
    @Transactional
    public void getAllMGuerillaQuestStagesByFirstHalfEnvironmentIdIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mGuerillaQuestStageRepository.saveAndFlush(mGuerillaQuestStage);

        // Get all the mGuerillaQuestStageList where firstHalfEnvironmentId greater than or equals to DEFAULT_FIRST_HALF_ENVIRONMENT_ID
        defaultMGuerillaQuestStageShouldBeFound("firstHalfEnvironmentId.greaterOrEqualThan=" + DEFAULT_FIRST_HALF_ENVIRONMENT_ID);

        // Get all the mGuerillaQuestStageList where firstHalfEnvironmentId greater than or equals to UPDATED_FIRST_HALF_ENVIRONMENT_ID
        defaultMGuerillaQuestStageShouldNotBeFound("firstHalfEnvironmentId.greaterOrEqualThan=" + UPDATED_FIRST_HALF_ENVIRONMENT_ID);
    }

    @Test
    @Transactional
    public void getAllMGuerillaQuestStagesByFirstHalfEnvironmentIdIsLessThanSomething() throws Exception {
        // Initialize the database
        mGuerillaQuestStageRepository.saveAndFlush(mGuerillaQuestStage);

        // Get all the mGuerillaQuestStageList where firstHalfEnvironmentId less than or equals to DEFAULT_FIRST_HALF_ENVIRONMENT_ID
        defaultMGuerillaQuestStageShouldNotBeFound("firstHalfEnvironmentId.lessThan=" + DEFAULT_FIRST_HALF_ENVIRONMENT_ID);

        // Get all the mGuerillaQuestStageList where firstHalfEnvironmentId less than or equals to UPDATED_FIRST_HALF_ENVIRONMENT_ID
        defaultMGuerillaQuestStageShouldBeFound("firstHalfEnvironmentId.lessThan=" + UPDATED_FIRST_HALF_ENVIRONMENT_ID);
    }


    @Test
    @Transactional
    public void getAllMGuerillaQuestStagesBySecondHalfNpcDeckIdIsEqualToSomething() throws Exception {
        // Initialize the database
        mGuerillaQuestStageRepository.saveAndFlush(mGuerillaQuestStage);

        // Get all the mGuerillaQuestStageList where secondHalfNpcDeckId equals to DEFAULT_SECOND_HALF_NPC_DECK_ID
        defaultMGuerillaQuestStageShouldBeFound("secondHalfNpcDeckId.equals=" + DEFAULT_SECOND_HALF_NPC_DECK_ID);

        // Get all the mGuerillaQuestStageList where secondHalfNpcDeckId equals to UPDATED_SECOND_HALF_NPC_DECK_ID
        defaultMGuerillaQuestStageShouldNotBeFound("secondHalfNpcDeckId.equals=" + UPDATED_SECOND_HALF_NPC_DECK_ID);
    }

    @Test
    @Transactional
    public void getAllMGuerillaQuestStagesBySecondHalfNpcDeckIdIsInShouldWork() throws Exception {
        // Initialize the database
        mGuerillaQuestStageRepository.saveAndFlush(mGuerillaQuestStage);

        // Get all the mGuerillaQuestStageList where secondHalfNpcDeckId in DEFAULT_SECOND_HALF_NPC_DECK_ID or UPDATED_SECOND_HALF_NPC_DECK_ID
        defaultMGuerillaQuestStageShouldBeFound("secondHalfNpcDeckId.in=" + DEFAULT_SECOND_HALF_NPC_DECK_ID + "," + UPDATED_SECOND_HALF_NPC_DECK_ID);

        // Get all the mGuerillaQuestStageList where secondHalfNpcDeckId equals to UPDATED_SECOND_HALF_NPC_DECK_ID
        defaultMGuerillaQuestStageShouldNotBeFound("secondHalfNpcDeckId.in=" + UPDATED_SECOND_HALF_NPC_DECK_ID);
    }

    @Test
    @Transactional
    public void getAllMGuerillaQuestStagesBySecondHalfNpcDeckIdIsNullOrNotNull() throws Exception {
        // Initialize the database
        mGuerillaQuestStageRepository.saveAndFlush(mGuerillaQuestStage);

        // Get all the mGuerillaQuestStageList where secondHalfNpcDeckId is not null
        defaultMGuerillaQuestStageShouldBeFound("secondHalfNpcDeckId.specified=true");

        // Get all the mGuerillaQuestStageList where secondHalfNpcDeckId is null
        defaultMGuerillaQuestStageShouldNotBeFound("secondHalfNpcDeckId.specified=false");
    }

    @Test
    @Transactional
    public void getAllMGuerillaQuestStagesBySecondHalfNpcDeckIdIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mGuerillaQuestStageRepository.saveAndFlush(mGuerillaQuestStage);

        // Get all the mGuerillaQuestStageList where secondHalfNpcDeckId greater than or equals to DEFAULT_SECOND_HALF_NPC_DECK_ID
        defaultMGuerillaQuestStageShouldBeFound("secondHalfNpcDeckId.greaterOrEqualThan=" + DEFAULT_SECOND_HALF_NPC_DECK_ID);

        // Get all the mGuerillaQuestStageList where secondHalfNpcDeckId greater than or equals to UPDATED_SECOND_HALF_NPC_DECK_ID
        defaultMGuerillaQuestStageShouldNotBeFound("secondHalfNpcDeckId.greaterOrEqualThan=" + UPDATED_SECOND_HALF_NPC_DECK_ID);
    }

    @Test
    @Transactional
    public void getAllMGuerillaQuestStagesBySecondHalfNpcDeckIdIsLessThanSomething() throws Exception {
        // Initialize the database
        mGuerillaQuestStageRepository.saveAndFlush(mGuerillaQuestStage);

        // Get all the mGuerillaQuestStageList where secondHalfNpcDeckId less than or equals to DEFAULT_SECOND_HALF_NPC_DECK_ID
        defaultMGuerillaQuestStageShouldNotBeFound("secondHalfNpcDeckId.lessThan=" + DEFAULT_SECOND_HALF_NPC_DECK_ID);

        // Get all the mGuerillaQuestStageList where secondHalfNpcDeckId less than or equals to UPDATED_SECOND_HALF_NPC_DECK_ID
        defaultMGuerillaQuestStageShouldBeFound("secondHalfNpcDeckId.lessThan=" + UPDATED_SECOND_HALF_NPC_DECK_ID);
    }


    @Test
    @Transactional
    public void getAllMGuerillaQuestStagesBySecondHalfEnvironmentIdIsEqualToSomething() throws Exception {
        // Initialize the database
        mGuerillaQuestStageRepository.saveAndFlush(mGuerillaQuestStage);

        // Get all the mGuerillaQuestStageList where secondHalfEnvironmentId equals to DEFAULT_SECOND_HALF_ENVIRONMENT_ID
        defaultMGuerillaQuestStageShouldBeFound("secondHalfEnvironmentId.equals=" + DEFAULT_SECOND_HALF_ENVIRONMENT_ID);

        // Get all the mGuerillaQuestStageList where secondHalfEnvironmentId equals to UPDATED_SECOND_HALF_ENVIRONMENT_ID
        defaultMGuerillaQuestStageShouldNotBeFound("secondHalfEnvironmentId.equals=" + UPDATED_SECOND_HALF_ENVIRONMENT_ID);
    }

    @Test
    @Transactional
    public void getAllMGuerillaQuestStagesBySecondHalfEnvironmentIdIsInShouldWork() throws Exception {
        // Initialize the database
        mGuerillaQuestStageRepository.saveAndFlush(mGuerillaQuestStage);

        // Get all the mGuerillaQuestStageList where secondHalfEnvironmentId in DEFAULT_SECOND_HALF_ENVIRONMENT_ID or UPDATED_SECOND_HALF_ENVIRONMENT_ID
        defaultMGuerillaQuestStageShouldBeFound("secondHalfEnvironmentId.in=" + DEFAULT_SECOND_HALF_ENVIRONMENT_ID + "," + UPDATED_SECOND_HALF_ENVIRONMENT_ID);

        // Get all the mGuerillaQuestStageList where secondHalfEnvironmentId equals to UPDATED_SECOND_HALF_ENVIRONMENT_ID
        defaultMGuerillaQuestStageShouldNotBeFound("secondHalfEnvironmentId.in=" + UPDATED_SECOND_HALF_ENVIRONMENT_ID);
    }

    @Test
    @Transactional
    public void getAllMGuerillaQuestStagesBySecondHalfEnvironmentIdIsNullOrNotNull() throws Exception {
        // Initialize the database
        mGuerillaQuestStageRepository.saveAndFlush(mGuerillaQuestStage);

        // Get all the mGuerillaQuestStageList where secondHalfEnvironmentId is not null
        defaultMGuerillaQuestStageShouldBeFound("secondHalfEnvironmentId.specified=true");

        // Get all the mGuerillaQuestStageList where secondHalfEnvironmentId is null
        defaultMGuerillaQuestStageShouldNotBeFound("secondHalfEnvironmentId.specified=false");
    }

    @Test
    @Transactional
    public void getAllMGuerillaQuestStagesBySecondHalfEnvironmentIdIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mGuerillaQuestStageRepository.saveAndFlush(mGuerillaQuestStage);

        // Get all the mGuerillaQuestStageList where secondHalfEnvironmentId greater than or equals to DEFAULT_SECOND_HALF_ENVIRONMENT_ID
        defaultMGuerillaQuestStageShouldBeFound("secondHalfEnvironmentId.greaterOrEqualThan=" + DEFAULT_SECOND_HALF_ENVIRONMENT_ID);

        // Get all the mGuerillaQuestStageList where secondHalfEnvironmentId greater than or equals to UPDATED_SECOND_HALF_ENVIRONMENT_ID
        defaultMGuerillaQuestStageShouldNotBeFound("secondHalfEnvironmentId.greaterOrEqualThan=" + UPDATED_SECOND_HALF_ENVIRONMENT_ID);
    }

    @Test
    @Transactional
    public void getAllMGuerillaQuestStagesBySecondHalfEnvironmentIdIsLessThanSomething() throws Exception {
        // Initialize the database
        mGuerillaQuestStageRepository.saveAndFlush(mGuerillaQuestStage);

        // Get all the mGuerillaQuestStageList where secondHalfEnvironmentId less than or equals to DEFAULT_SECOND_HALF_ENVIRONMENT_ID
        defaultMGuerillaQuestStageShouldNotBeFound("secondHalfEnvironmentId.lessThan=" + DEFAULT_SECOND_HALF_ENVIRONMENT_ID);

        // Get all the mGuerillaQuestStageList where secondHalfEnvironmentId less than or equals to UPDATED_SECOND_HALF_ENVIRONMENT_ID
        defaultMGuerillaQuestStageShouldBeFound("secondHalfEnvironmentId.lessThan=" + UPDATED_SECOND_HALF_ENVIRONMENT_ID);
    }


    @Test
    @Transactional
    public void getAllMGuerillaQuestStagesByExtraFirstHalfNpcDeckIdIsEqualToSomething() throws Exception {
        // Initialize the database
        mGuerillaQuestStageRepository.saveAndFlush(mGuerillaQuestStage);

        // Get all the mGuerillaQuestStageList where extraFirstHalfNpcDeckId equals to DEFAULT_EXTRA_FIRST_HALF_NPC_DECK_ID
        defaultMGuerillaQuestStageShouldBeFound("extraFirstHalfNpcDeckId.equals=" + DEFAULT_EXTRA_FIRST_HALF_NPC_DECK_ID);

        // Get all the mGuerillaQuestStageList where extraFirstHalfNpcDeckId equals to UPDATED_EXTRA_FIRST_HALF_NPC_DECK_ID
        defaultMGuerillaQuestStageShouldNotBeFound("extraFirstHalfNpcDeckId.equals=" + UPDATED_EXTRA_FIRST_HALF_NPC_DECK_ID);
    }

    @Test
    @Transactional
    public void getAllMGuerillaQuestStagesByExtraFirstHalfNpcDeckIdIsInShouldWork() throws Exception {
        // Initialize the database
        mGuerillaQuestStageRepository.saveAndFlush(mGuerillaQuestStage);

        // Get all the mGuerillaQuestStageList where extraFirstHalfNpcDeckId in DEFAULT_EXTRA_FIRST_HALF_NPC_DECK_ID or UPDATED_EXTRA_FIRST_HALF_NPC_DECK_ID
        defaultMGuerillaQuestStageShouldBeFound("extraFirstHalfNpcDeckId.in=" + DEFAULT_EXTRA_FIRST_HALF_NPC_DECK_ID + "," + UPDATED_EXTRA_FIRST_HALF_NPC_DECK_ID);

        // Get all the mGuerillaQuestStageList where extraFirstHalfNpcDeckId equals to UPDATED_EXTRA_FIRST_HALF_NPC_DECK_ID
        defaultMGuerillaQuestStageShouldNotBeFound("extraFirstHalfNpcDeckId.in=" + UPDATED_EXTRA_FIRST_HALF_NPC_DECK_ID);
    }

    @Test
    @Transactional
    public void getAllMGuerillaQuestStagesByExtraFirstHalfNpcDeckIdIsNullOrNotNull() throws Exception {
        // Initialize the database
        mGuerillaQuestStageRepository.saveAndFlush(mGuerillaQuestStage);

        // Get all the mGuerillaQuestStageList where extraFirstHalfNpcDeckId is not null
        defaultMGuerillaQuestStageShouldBeFound("extraFirstHalfNpcDeckId.specified=true");

        // Get all the mGuerillaQuestStageList where extraFirstHalfNpcDeckId is null
        defaultMGuerillaQuestStageShouldNotBeFound("extraFirstHalfNpcDeckId.specified=false");
    }

    @Test
    @Transactional
    public void getAllMGuerillaQuestStagesByExtraFirstHalfNpcDeckIdIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mGuerillaQuestStageRepository.saveAndFlush(mGuerillaQuestStage);

        // Get all the mGuerillaQuestStageList where extraFirstHalfNpcDeckId greater than or equals to DEFAULT_EXTRA_FIRST_HALF_NPC_DECK_ID
        defaultMGuerillaQuestStageShouldBeFound("extraFirstHalfNpcDeckId.greaterOrEqualThan=" + DEFAULT_EXTRA_FIRST_HALF_NPC_DECK_ID);

        // Get all the mGuerillaQuestStageList where extraFirstHalfNpcDeckId greater than or equals to UPDATED_EXTRA_FIRST_HALF_NPC_DECK_ID
        defaultMGuerillaQuestStageShouldNotBeFound("extraFirstHalfNpcDeckId.greaterOrEqualThan=" + UPDATED_EXTRA_FIRST_HALF_NPC_DECK_ID);
    }

    @Test
    @Transactional
    public void getAllMGuerillaQuestStagesByExtraFirstHalfNpcDeckIdIsLessThanSomething() throws Exception {
        // Initialize the database
        mGuerillaQuestStageRepository.saveAndFlush(mGuerillaQuestStage);

        // Get all the mGuerillaQuestStageList where extraFirstHalfNpcDeckId less than or equals to DEFAULT_EXTRA_FIRST_HALF_NPC_DECK_ID
        defaultMGuerillaQuestStageShouldNotBeFound("extraFirstHalfNpcDeckId.lessThan=" + DEFAULT_EXTRA_FIRST_HALF_NPC_DECK_ID);

        // Get all the mGuerillaQuestStageList where extraFirstHalfNpcDeckId less than or equals to UPDATED_EXTRA_FIRST_HALF_NPC_DECK_ID
        defaultMGuerillaQuestStageShouldBeFound("extraFirstHalfNpcDeckId.lessThan=" + UPDATED_EXTRA_FIRST_HALF_NPC_DECK_ID);
    }


    @Test
    @Transactional
    public void getAllMGuerillaQuestStagesByExtraSecondHalfNpcDeckIdIsEqualToSomething() throws Exception {
        // Initialize the database
        mGuerillaQuestStageRepository.saveAndFlush(mGuerillaQuestStage);

        // Get all the mGuerillaQuestStageList where extraSecondHalfNpcDeckId equals to DEFAULT_EXTRA_SECOND_HALF_NPC_DECK_ID
        defaultMGuerillaQuestStageShouldBeFound("extraSecondHalfNpcDeckId.equals=" + DEFAULT_EXTRA_SECOND_HALF_NPC_DECK_ID);

        // Get all the mGuerillaQuestStageList where extraSecondHalfNpcDeckId equals to UPDATED_EXTRA_SECOND_HALF_NPC_DECK_ID
        defaultMGuerillaQuestStageShouldNotBeFound("extraSecondHalfNpcDeckId.equals=" + UPDATED_EXTRA_SECOND_HALF_NPC_DECK_ID);
    }

    @Test
    @Transactional
    public void getAllMGuerillaQuestStagesByExtraSecondHalfNpcDeckIdIsInShouldWork() throws Exception {
        // Initialize the database
        mGuerillaQuestStageRepository.saveAndFlush(mGuerillaQuestStage);

        // Get all the mGuerillaQuestStageList where extraSecondHalfNpcDeckId in DEFAULT_EXTRA_SECOND_HALF_NPC_DECK_ID or UPDATED_EXTRA_SECOND_HALF_NPC_DECK_ID
        defaultMGuerillaQuestStageShouldBeFound("extraSecondHalfNpcDeckId.in=" + DEFAULT_EXTRA_SECOND_HALF_NPC_DECK_ID + "," + UPDATED_EXTRA_SECOND_HALF_NPC_DECK_ID);

        // Get all the mGuerillaQuestStageList where extraSecondHalfNpcDeckId equals to UPDATED_EXTRA_SECOND_HALF_NPC_DECK_ID
        defaultMGuerillaQuestStageShouldNotBeFound("extraSecondHalfNpcDeckId.in=" + UPDATED_EXTRA_SECOND_HALF_NPC_DECK_ID);
    }

    @Test
    @Transactional
    public void getAllMGuerillaQuestStagesByExtraSecondHalfNpcDeckIdIsNullOrNotNull() throws Exception {
        // Initialize the database
        mGuerillaQuestStageRepository.saveAndFlush(mGuerillaQuestStage);

        // Get all the mGuerillaQuestStageList where extraSecondHalfNpcDeckId is not null
        defaultMGuerillaQuestStageShouldBeFound("extraSecondHalfNpcDeckId.specified=true");

        // Get all the mGuerillaQuestStageList where extraSecondHalfNpcDeckId is null
        defaultMGuerillaQuestStageShouldNotBeFound("extraSecondHalfNpcDeckId.specified=false");
    }

    @Test
    @Transactional
    public void getAllMGuerillaQuestStagesByExtraSecondHalfNpcDeckIdIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mGuerillaQuestStageRepository.saveAndFlush(mGuerillaQuestStage);

        // Get all the mGuerillaQuestStageList where extraSecondHalfNpcDeckId greater than or equals to DEFAULT_EXTRA_SECOND_HALF_NPC_DECK_ID
        defaultMGuerillaQuestStageShouldBeFound("extraSecondHalfNpcDeckId.greaterOrEqualThan=" + DEFAULT_EXTRA_SECOND_HALF_NPC_DECK_ID);

        // Get all the mGuerillaQuestStageList where extraSecondHalfNpcDeckId greater than or equals to UPDATED_EXTRA_SECOND_HALF_NPC_DECK_ID
        defaultMGuerillaQuestStageShouldNotBeFound("extraSecondHalfNpcDeckId.greaterOrEqualThan=" + UPDATED_EXTRA_SECOND_HALF_NPC_DECK_ID);
    }

    @Test
    @Transactional
    public void getAllMGuerillaQuestStagesByExtraSecondHalfNpcDeckIdIsLessThanSomething() throws Exception {
        // Initialize the database
        mGuerillaQuestStageRepository.saveAndFlush(mGuerillaQuestStage);

        // Get all the mGuerillaQuestStageList where extraSecondHalfNpcDeckId less than or equals to DEFAULT_EXTRA_SECOND_HALF_NPC_DECK_ID
        defaultMGuerillaQuestStageShouldNotBeFound("extraSecondHalfNpcDeckId.lessThan=" + DEFAULT_EXTRA_SECOND_HALF_NPC_DECK_ID);

        // Get all the mGuerillaQuestStageList where extraSecondHalfNpcDeckId less than or equals to UPDATED_EXTRA_SECOND_HALF_NPC_DECK_ID
        defaultMGuerillaQuestStageShouldBeFound("extraSecondHalfNpcDeckId.lessThan=" + UPDATED_EXTRA_SECOND_HALF_NPC_DECK_ID);
    }


    @Test
    @Transactional
    public void getAllMGuerillaQuestStagesByConsumeApIsEqualToSomething() throws Exception {
        // Initialize the database
        mGuerillaQuestStageRepository.saveAndFlush(mGuerillaQuestStage);

        // Get all the mGuerillaQuestStageList where consumeAp equals to DEFAULT_CONSUME_AP
        defaultMGuerillaQuestStageShouldBeFound("consumeAp.equals=" + DEFAULT_CONSUME_AP);

        // Get all the mGuerillaQuestStageList where consumeAp equals to UPDATED_CONSUME_AP
        defaultMGuerillaQuestStageShouldNotBeFound("consumeAp.equals=" + UPDATED_CONSUME_AP);
    }

    @Test
    @Transactional
    public void getAllMGuerillaQuestStagesByConsumeApIsInShouldWork() throws Exception {
        // Initialize the database
        mGuerillaQuestStageRepository.saveAndFlush(mGuerillaQuestStage);

        // Get all the mGuerillaQuestStageList where consumeAp in DEFAULT_CONSUME_AP or UPDATED_CONSUME_AP
        defaultMGuerillaQuestStageShouldBeFound("consumeAp.in=" + DEFAULT_CONSUME_AP + "," + UPDATED_CONSUME_AP);

        // Get all the mGuerillaQuestStageList where consumeAp equals to UPDATED_CONSUME_AP
        defaultMGuerillaQuestStageShouldNotBeFound("consumeAp.in=" + UPDATED_CONSUME_AP);
    }

    @Test
    @Transactional
    public void getAllMGuerillaQuestStagesByConsumeApIsNullOrNotNull() throws Exception {
        // Initialize the database
        mGuerillaQuestStageRepository.saveAndFlush(mGuerillaQuestStage);

        // Get all the mGuerillaQuestStageList where consumeAp is not null
        defaultMGuerillaQuestStageShouldBeFound("consumeAp.specified=true");

        // Get all the mGuerillaQuestStageList where consumeAp is null
        defaultMGuerillaQuestStageShouldNotBeFound("consumeAp.specified=false");
    }

    @Test
    @Transactional
    public void getAllMGuerillaQuestStagesByConsumeApIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mGuerillaQuestStageRepository.saveAndFlush(mGuerillaQuestStage);

        // Get all the mGuerillaQuestStageList where consumeAp greater than or equals to DEFAULT_CONSUME_AP
        defaultMGuerillaQuestStageShouldBeFound("consumeAp.greaterOrEqualThan=" + DEFAULT_CONSUME_AP);

        // Get all the mGuerillaQuestStageList where consumeAp greater than or equals to UPDATED_CONSUME_AP
        defaultMGuerillaQuestStageShouldNotBeFound("consumeAp.greaterOrEqualThan=" + UPDATED_CONSUME_AP);
    }

    @Test
    @Transactional
    public void getAllMGuerillaQuestStagesByConsumeApIsLessThanSomething() throws Exception {
        // Initialize the database
        mGuerillaQuestStageRepository.saveAndFlush(mGuerillaQuestStage);

        // Get all the mGuerillaQuestStageList where consumeAp less than or equals to DEFAULT_CONSUME_AP
        defaultMGuerillaQuestStageShouldNotBeFound("consumeAp.lessThan=" + DEFAULT_CONSUME_AP);

        // Get all the mGuerillaQuestStageList where consumeAp less than or equals to UPDATED_CONSUME_AP
        defaultMGuerillaQuestStageShouldBeFound("consumeAp.lessThan=" + UPDATED_CONSUME_AP);
    }


    @Test
    @Transactional
    public void getAllMGuerillaQuestStagesByKickOffTypeIsEqualToSomething() throws Exception {
        // Initialize the database
        mGuerillaQuestStageRepository.saveAndFlush(mGuerillaQuestStage);

        // Get all the mGuerillaQuestStageList where kickOffType equals to DEFAULT_KICK_OFF_TYPE
        defaultMGuerillaQuestStageShouldBeFound("kickOffType.equals=" + DEFAULT_KICK_OFF_TYPE);

        // Get all the mGuerillaQuestStageList where kickOffType equals to UPDATED_KICK_OFF_TYPE
        defaultMGuerillaQuestStageShouldNotBeFound("kickOffType.equals=" + UPDATED_KICK_OFF_TYPE);
    }

    @Test
    @Transactional
    public void getAllMGuerillaQuestStagesByKickOffTypeIsInShouldWork() throws Exception {
        // Initialize the database
        mGuerillaQuestStageRepository.saveAndFlush(mGuerillaQuestStage);

        // Get all the mGuerillaQuestStageList where kickOffType in DEFAULT_KICK_OFF_TYPE or UPDATED_KICK_OFF_TYPE
        defaultMGuerillaQuestStageShouldBeFound("kickOffType.in=" + DEFAULT_KICK_OFF_TYPE + "," + UPDATED_KICK_OFF_TYPE);

        // Get all the mGuerillaQuestStageList where kickOffType equals to UPDATED_KICK_OFF_TYPE
        defaultMGuerillaQuestStageShouldNotBeFound("kickOffType.in=" + UPDATED_KICK_OFF_TYPE);
    }

    @Test
    @Transactional
    public void getAllMGuerillaQuestStagesByKickOffTypeIsNullOrNotNull() throws Exception {
        // Initialize the database
        mGuerillaQuestStageRepository.saveAndFlush(mGuerillaQuestStage);

        // Get all the mGuerillaQuestStageList where kickOffType is not null
        defaultMGuerillaQuestStageShouldBeFound("kickOffType.specified=true");

        // Get all the mGuerillaQuestStageList where kickOffType is null
        defaultMGuerillaQuestStageShouldNotBeFound("kickOffType.specified=false");
    }

    @Test
    @Transactional
    public void getAllMGuerillaQuestStagesByKickOffTypeIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mGuerillaQuestStageRepository.saveAndFlush(mGuerillaQuestStage);

        // Get all the mGuerillaQuestStageList where kickOffType greater than or equals to DEFAULT_KICK_OFF_TYPE
        defaultMGuerillaQuestStageShouldBeFound("kickOffType.greaterOrEqualThan=" + DEFAULT_KICK_OFF_TYPE);

        // Get all the mGuerillaQuestStageList where kickOffType greater than or equals to UPDATED_KICK_OFF_TYPE
        defaultMGuerillaQuestStageShouldNotBeFound("kickOffType.greaterOrEqualThan=" + UPDATED_KICK_OFF_TYPE);
    }

    @Test
    @Transactional
    public void getAllMGuerillaQuestStagesByKickOffTypeIsLessThanSomething() throws Exception {
        // Initialize the database
        mGuerillaQuestStageRepository.saveAndFlush(mGuerillaQuestStage);

        // Get all the mGuerillaQuestStageList where kickOffType less than or equals to DEFAULT_KICK_OFF_TYPE
        defaultMGuerillaQuestStageShouldNotBeFound("kickOffType.lessThan=" + DEFAULT_KICK_OFF_TYPE);

        // Get all the mGuerillaQuestStageList where kickOffType less than or equals to UPDATED_KICK_OFF_TYPE
        defaultMGuerillaQuestStageShouldBeFound("kickOffType.lessThan=" + UPDATED_KICK_OFF_TYPE);
    }


    @Test
    @Transactional
    public void getAllMGuerillaQuestStagesByMatchMinuteIsEqualToSomething() throws Exception {
        // Initialize the database
        mGuerillaQuestStageRepository.saveAndFlush(mGuerillaQuestStage);

        // Get all the mGuerillaQuestStageList where matchMinute equals to DEFAULT_MATCH_MINUTE
        defaultMGuerillaQuestStageShouldBeFound("matchMinute.equals=" + DEFAULT_MATCH_MINUTE);

        // Get all the mGuerillaQuestStageList where matchMinute equals to UPDATED_MATCH_MINUTE
        defaultMGuerillaQuestStageShouldNotBeFound("matchMinute.equals=" + UPDATED_MATCH_MINUTE);
    }

    @Test
    @Transactional
    public void getAllMGuerillaQuestStagesByMatchMinuteIsInShouldWork() throws Exception {
        // Initialize the database
        mGuerillaQuestStageRepository.saveAndFlush(mGuerillaQuestStage);

        // Get all the mGuerillaQuestStageList where matchMinute in DEFAULT_MATCH_MINUTE or UPDATED_MATCH_MINUTE
        defaultMGuerillaQuestStageShouldBeFound("matchMinute.in=" + DEFAULT_MATCH_MINUTE + "," + UPDATED_MATCH_MINUTE);

        // Get all the mGuerillaQuestStageList where matchMinute equals to UPDATED_MATCH_MINUTE
        defaultMGuerillaQuestStageShouldNotBeFound("matchMinute.in=" + UPDATED_MATCH_MINUTE);
    }

    @Test
    @Transactional
    public void getAllMGuerillaQuestStagesByMatchMinuteIsNullOrNotNull() throws Exception {
        // Initialize the database
        mGuerillaQuestStageRepository.saveAndFlush(mGuerillaQuestStage);

        // Get all the mGuerillaQuestStageList where matchMinute is not null
        defaultMGuerillaQuestStageShouldBeFound("matchMinute.specified=true");

        // Get all the mGuerillaQuestStageList where matchMinute is null
        defaultMGuerillaQuestStageShouldNotBeFound("matchMinute.specified=false");
    }

    @Test
    @Transactional
    public void getAllMGuerillaQuestStagesByMatchMinuteIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mGuerillaQuestStageRepository.saveAndFlush(mGuerillaQuestStage);

        // Get all the mGuerillaQuestStageList where matchMinute greater than or equals to DEFAULT_MATCH_MINUTE
        defaultMGuerillaQuestStageShouldBeFound("matchMinute.greaterOrEqualThan=" + DEFAULT_MATCH_MINUTE);

        // Get all the mGuerillaQuestStageList where matchMinute greater than or equals to UPDATED_MATCH_MINUTE
        defaultMGuerillaQuestStageShouldNotBeFound("matchMinute.greaterOrEqualThan=" + UPDATED_MATCH_MINUTE);
    }

    @Test
    @Transactional
    public void getAllMGuerillaQuestStagesByMatchMinuteIsLessThanSomething() throws Exception {
        // Initialize the database
        mGuerillaQuestStageRepository.saveAndFlush(mGuerillaQuestStage);

        // Get all the mGuerillaQuestStageList where matchMinute less than or equals to DEFAULT_MATCH_MINUTE
        defaultMGuerillaQuestStageShouldNotBeFound("matchMinute.lessThan=" + DEFAULT_MATCH_MINUTE);

        // Get all the mGuerillaQuestStageList where matchMinute less than or equals to UPDATED_MATCH_MINUTE
        defaultMGuerillaQuestStageShouldBeFound("matchMinute.lessThan=" + UPDATED_MATCH_MINUTE);
    }


    @Test
    @Transactional
    public void getAllMGuerillaQuestStagesByEnableExtraIsEqualToSomething() throws Exception {
        // Initialize the database
        mGuerillaQuestStageRepository.saveAndFlush(mGuerillaQuestStage);

        // Get all the mGuerillaQuestStageList where enableExtra equals to DEFAULT_ENABLE_EXTRA
        defaultMGuerillaQuestStageShouldBeFound("enableExtra.equals=" + DEFAULT_ENABLE_EXTRA);

        // Get all the mGuerillaQuestStageList where enableExtra equals to UPDATED_ENABLE_EXTRA
        defaultMGuerillaQuestStageShouldNotBeFound("enableExtra.equals=" + UPDATED_ENABLE_EXTRA);
    }

    @Test
    @Transactional
    public void getAllMGuerillaQuestStagesByEnableExtraIsInShouldWork() throws Exception {
        // Initialize the database
        mGuerillaQuestStageRepository.saveAndFlush(mGuerillaQuestStage);

        // Get all the mGuerillaQuestStageList where enableExtra in DEFAULT_ENABLE_EXTRA or UPDATED_ENABLE_EXTRA
        defaultMGuerillaQuestStageShouldBeFound("enableExtra.in=" + DEFAULT_ENABLE_EXTRA + "," + UPDATED_ENABLE_EXTRA);

        // Get all the mGuerillaQuestStageList where enableExtra equals to UPDATED_ENABLE_EXTRA
        defaultMGuerillaQuestStageShouldNotBeFound("enableExtra.in=" + UPDATED_ENABLE_EXTRA);
    }

    @Test
    @Transactional
    public void getAllMGuerillaQuestStagesByEnableExtraIsNullOrNotNull() throws Exception {
        // Initialize the database
        mGuerillaQuestStageRepository.saveAndFlush(mGuerillaQuestStage);

        // Get all the mGuerillaQuestStageList where enableExtra is not null
        defaultMGuerillaQuestStageShouldBeFound("enableExtra.specified=true");

        // Get all the mGuerillaQuestStageList where enableExtra is null
        defaultMGuerillaQuestStageShouldNotBeFound("enableExtra.specified=false");
    }

    @Test
    @Transactional
    public void getAllMGuerillaQuestStagesByEnableExtraIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mGuerillaQuestStageRepository.saveAndFlush(mGuerillaQuestStage);

        // Get all the mGuerillaQuestStageList where enableExtra greater than or equals to DEFAULT_ENABLE_EXTRA
        defaultMGuerillaQuestStageShouldBeFound("enableExtra.greaterOrEqualThan=" + DEFAULT_ENABLE_EXTRA);

        // Get all the mGuerillaQuestStageList where enableExtra greater than or equals to UPDATED_ENABLE_EXTRA
        defaultMGuerillaQuestStageShouldNotBeFound("enableExtra.greaterOrEqualThan=" + UPDATED_ENABLE_EXTRA);
    }

    @Test
    @Transactional
    public void getAllMGuerillaQuestStagesByEnableExtraIsLessThanSomething() throws Exception {
        // Initialize the database
        mGuerillaQuestStageRepository.saveAndFlush(mGuerillaQuestStage);

        // Get all the mGuerillaQuestStageList where enableExtra less than or equals to DEFAULT_ENABLE_EXTRA
        defaultMGuerillaQuestStageShouldNotBeFound("enableExtra.lessThan=" + DEFAULT_ENABLE_EXTRA);

        // Get all the mGuerillaQuestStageList where enableExtra less than or equals to UPDATED_ENABLE_EXTRA
        defaultMGuerillaQuestStageShouldBeFound("enableExtra.lessThan=" + UPDATED_ENABLE_EXTRA);
    }


    @Test
    @Transactional
    public void getAllMGuerillaQuestStagesByEnablePkIsEqualToSomething() throws Exception {
        // Initialize the database
        mGuerillaQuestStageRepository.saveAndFlush(mGuerillaQuestStage);

        // Get all the mGuerillaQuestStageList where enablePk equals to DEFAULT_ENABLE_PK
        defaultMGuerillaQuestStageShouldBeFound("enablePk.equals=" + DEFAULT_ENABLE_PK);

        // Get all the mGuerillaQuestStageList where enablePk equals to UPDATED_ENABLE_PK
        defaultMGuerillaQuestStageShouldNotBeFound("enablePk.equals=" + UPDATED_ENABLE_PK);
    }

    @Test
    @Transactional
    public void getAllMGuerillaQuestStagesByEnablePkIsInShouldWork() throws Exception {
        // Initialize the database
        mGuerillaQuestStageRepository.saveAndFlush(mGuerillaQuestStage);

        // Get all the mGuerillaQuestStageList where enablePk in DEFAULT_ENABLE_PK or UPDATED_ENABLE_PK
        defaultMGuerillaQuestStageShouldBeFound("enablePk.in=" + DEFAULT_ENABLE_PK + "," + UPDATED_ENABLE_PK);

        // Get all the mGuerillaQuestStageList where enablePk equals to UPDATED_ENABLE_PK
        defaultMGuerillaQuestStageShouldNotBeFound("enablePk.in=" + UPDATED_ENABLE_PK);
    }

    @Test
    @Transactional
    public void getAllMGuerillaQuestStagesByEnablePkIsNullOrNotNull() throws Exception {
        // Initialize the database
        mGuerillaQuestStageRepository.saveAndFlush(mGuerillaQuestStage);

        // Get all the mGuerillaQuestStageList where enablePk is not null
        defaultMGuerillaQuestStageShouldBeFound("enablePk.specified=true");

        // Get all the mGuerillaQuestStageList where enablePk is null
        defaultMGuerillaQuestStageShouldNotBeFound("enablePk.specified=false");
    }

    @Test
    @Transactional
    public void getAllMGuerillaQuestStagesByEnablePkIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mGuerillaQuestStageRepository.saveAndFlush(mGuerillaQuestStage);

        // Get all the mGuerillaQuestStageList where enablePk greater than or equals to DEFAULT_ENABLE_PK
        defaultMGuerillaQuestStageShouldBeFound("enablePk.greaterOrEqualThan=" + DEFAULT_ENABLE_PK);

        // Get all the mGuerillaQuestStageList where enablePk greater than or equals to UPDATED_ENABLE_PK
        defaultMGuerillaQuestStageShouldNotBeFound("enablePk.greaterOrEqualThan=" + UPDATED_ENABLE_PK);
    }

    @Test
    @Transactional
    public void getAllMGuerillaQuestStagesByEnablePkIsLessThanSomething() throws Exception {
        // Initialize the database
        mGuerillaQuestStageRepository.saveAndFlush(mGuerillaQuestStage);

        // Get all the mGuerillaQuestStageList where enablePk less than or equals to DEFAULT_ENABLE_PK
        defaultMGuerillaQuestStageShouldNotBeFound("enablePk.lessThan=" + DEFAULT_ENABLE_PK);

        // Get all the mGuerillaQuestStageList where enablePk less than or equals to UPDATED_ENABLE_PK
        defaultMGuerillaQuestStageShouldBeFound("enablePk.lessThan=" + UPDATED_ENABLE_PK);
    }


    @Test
    @Transactional
    public void getAllMGuerillaQuestStagesByAiLevelIsEqualToSomething() throws Exception {
        // Initialize the database
        mGuerillaQuestStageRepository.saveAndFlush(mGuerillaQuestStage);

        // Get all the mGuerillaQuestStageList where aiLevel equals to DEFAULT_AI_LEVEL
        defaultMGuerillaQuestStageShouldBeFound("aiLevel.equals=" + DEFAULT_AI_LEVEL);

        // Get all the mGuerillaQuestStageList where aiLevel equals to UPDATED_AI_LEVEL
        defaultMGuerillaQuestStageShouldNotBeFound("aiLevel.equals=" + UPDATED_AI_LEVEL);
    }

    @Test
    @Transactional
    public void getAllMGuerillaQuestStagesByAiLevelIsInShouldWork() throws Exception {
        // Initialize the database
        mGuerillaQuestStageRepository.saveAndFlush(mGuerillaQuestStage);

        // Get all the mGuerillaQuestStageList where aiLevel in DEFAULT_AI_LEVEL or UPDATED_AI_LEVEL
        defaultMGuerillaQuestStageShouldBeFound("aiLevel.in=" + DEFAULT_AI_LEVEL + "," + UPDATED_AI_LEVEL);

        // Get all the mGuerillaQuestStageList where aiLevel equals to UPDATED_AI_LEVEL
        defaultMGuerillaQuestStageShouldNotBeFound("aiLevel.in=" + UPDATED_AI_LEVEL);
    }

    @Test
    @Transactional
    public void getAllMGuerillaQuestStagesByAiLevelIsNullOrNotNull() throws Exception {
        // Initialize the database
        mGuerillaQuestStageRepository.saveAndFlush(mGuerillaQuestStage);

        // Get all the mGuerillaQuestStageList where aiLevel is not null
        defaultMGuerillaQuestStageShouldBeFound("aiLevel.specified=true");

        // Get all the mGuerillaQuestStageList where aiLevel is null
        defaultMGuerillaQuestStageShouldNotBeFound("aiLevel.specified=false");
    }

    @Test
    @Transactional
    public void getAllMGuerillaQuestStagesByAiLevelIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mGuerillaQuestStageRepository.saveAndFlush(mGuerillaQuestStage);

        // Get all the mGuerillaQuestStageList where aiLevel greater than or equals to DEFAULT_AI_LEVEL
        defaultMGuerillaQuestStageShouldBeFound("aiLevel.greaterOrEqualThan=" + DEFAULT_AI_LEVEL);

        // Get all the mGuerillaQuestStageList where aiLevel greater than or equals to UPDATED_AI_LEVEL
        defaultMGuerillaQuestStageShouldNotBeFound("aiLevel.greaterOrEqualThan=" + UPDATED_AI_LEVEL);
    }

    @Test
    @Transactional
    public void getAllMGuerillaQuestStagesByAiLevelIsLessThanSomething() throws Exception {
        // Initialize the database
        mGuerillaQuestStageRepository.saveAndFlush(mGuerillaQuestStage);

        // Get all the mGuerillaQuestStageList where aiLevel less than or equals to DEFAULT_AI_LEVEL
        defaultMGuerillaQuestStageShouldNotBeFound("aiLevel.lessThan=" + DEFAULT_AI_LEVEL);

        // Get all the mGuerillaQuestStageList where aiLevel less than or equals to UPDATED_AI_LEVEL
        defaultMGuerillaQuestStageShouldBeFound("aiLevel.lessThan=" + UPDATED_AI_LEVEL);
    }


    @Test
    @Transactional
    public void getAllMGuerillaQuestStagesByStartAtSecondHalfIsEqualToSomething() throws Exception {
        // Initialize the database
        mGuerillaQuestStageRepository.saveAndFlush(mGuerillaQuestStage);

        // Get all the mGuerillaQuestStageList where startAtSecondHalf equals to DEFAULT_START_AT_SECOND_HALF
        defaultMGuerillaQuestStageShouldBeFound("startAtSecondHalf.equals=" + DEFAULT_START_AT_SECOND_HALF);

        // Get all the mGuerillaQuestStageList where startAtSecondHalf equals to UPDATED_START_AT_SECOND_HALF
        defaultMGuerillaQuestStageShouldNotBeFound("startAtSecondHalf.equals=" + UPDATED_START_AT_SECOND_HALF);
    }

    @Test
    @Transactional
    public void getAllMGuerillaQuestStagesByStartAtSecondHalfIsInShouldWork() throws Exception {
        // Initialize the database
        mGuerillaQuestStageRepository.saveAndFlush(mGuerillaQuestStage);

        // Get all the mGuerillaQuestStageList where startAtSecondHalf in DEFAULT_START_AT_SECOND_HALF or UPDATED_START_AT_SECOND_HALF
        defaultMGuerillaQuestStageShouldBeFound("startAtSecondHalf.in=" + DEFAULT_START_AT_SECOND_HALF + "," + UPDATED_START_AT_SECOND_HALF);

        // Get all the mGuerillaQuestStageList where startAtSecondHalf equals to UPDATED_START_AT_SECOND_HALF
        defaultMGuerillaQuestStageShouldNotBeFound("startAtSecondHalf.in=" + UPDATED_START_AT_SECOND_HALF);
    }

    @Test
    @Transactional
    public void getAllMGuerillaQuestStagesByStartAtSecondHalfIsNullOrNotNull() throws Exception {
        // Initialize the database
        mGuerillaQuestStageRepository.saveAndFlush(mGuerillaQuestStage);

        // Get all the mGuerillaQuestStageList where startAtSecondHalf is not null
        defaultMGuerillaQuestStageShouldBeFound("startAtSecondHalf.specified=true");

        // Get all the mGuerillaQuestStageList where startAtSecondHalf is null
        defaultMGuerillaQuestStageShouldNotBeFound("startAtSecondHalf.specified=false");
    }

    @Test
    @Transactional
    public void getAllMGuerillaQuestStagesByStartAtSecondHalfIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mGuerillaQuestStageRepository.saveAndFlush(mGuerillaQuestStage);

        // Get all the mGuerillaQuestStageList where startAtSecondHalf greater than or equals to DEFAULT_START_AT_SECOND_HALF
        defaultMGuerillaQuestStageShouldBeFound("startAtSecondHalf.greaterOrEqualThan=" + DEFAULT_START_AT_SECOND_HALF);

        // Get all the mGuerillaQuestStageList where startAtSecondHalf greater than or equals to UPDATED_START_AT_SECOND_HALF
        defaultMGuerillaQuestStageShouldNotBeFound("startAtSecondHalf.greaterOrEqualThan=" + UPDATED_START_AT_SECOND_HALF);
    }

    @Test
    @Transactional
    public void getAllMGuerillaQuestStagesByStartAtSecondHalfIsLessThanSomething() throws Exception {
        // Initialize the database
        mGuerillaQuestStageRepository.saveAndFlush(mGuerillaQuestStage);

        // Get all the mGuerillaQuestStageList where startAtSecondHalf less than or equals to DEFAULT_START_AT_SECOND_HALF
        defaultMGuerillaQuestStageShouldNotBeFound("startAtSecondHalf.lessThan=" + DEFAULT_START_AT_SECOND_HALF);

        // Get all the mGuerillaQuestStageList where startAtSecondHalf less than or equals to UPDATED_START_AT_SECOND_HALF
        defaultMGuerillaQuestStageShouldBeFound("startAtSecondHalf.lessThan=" + UPDATED_START_AT_SECOND_HALF);
    }


    @Test
    @Transactional
    public void getAllMGuerillaQuestStagesByStartScoreIsEqualToSomething() throws Exception {
        // Initialize the database
        mGuerillaQuestStageRepository.saveAndFlush(mGuerillaQuestStage);

        // Get all the mGuerillaQuestStageList where startScore equals to DEFAULT_START_SCORE
        defaultMGuerillaQuestStageShouldBeFound("startScore.equals=" + DEFAULT_START_SCORE);

        // Get all the mGuerillaQuestStageList where startScore equals to UPDATED_START_SCORE
        defaultMGuerillaQuestStageShouldNotBeFound("startScore.equals=" + UPDATED_START_SCORE);
    }

    @Test
    @Transactional
    public void getAllMGuerillaQuestStagesByStartScoreIsInShouldWork() throws Exception {
        // Initialize the database
        mGuerillaQuestStageRepository.saveAndFlush(mGuerillaQuestStage);

        // Get all the mGuerillaQuestStageList where startScore in DEFAULT_START_SCORE or UPDATED_START_SCORE
        defaultMGuerillaQuestStageShouldBeFound("startScore.in=" + DEFAULT_START_SCORE + "," + UPDATED_START_SCORE);

        // Get all the mGuerillaQuestStageList where startScore equals to UPDATED_START_SCORE
        defaultMGuerillaQuestStageShouldNotBeFound("startScore.in=" + UPDATED_START_SCORE);
    }

    @Test
    @Transactional
    public void getAllMGuerillaQuestStagesByStartScoreIsNullOrNotNull() throws Exception {
        // Initialize the database
        mGuerillaQuestStageRepository.saveAndFlush(mGuerillaQuestStage);

        // Get all the mGuerillaQuestStageList where startScore is not null
        defaultMGuerillaQuestStageShouldBeFound("startScore.specified=true");

        // Get all the mGuerillaQuestStageList where startScore is null
        defaultMGuerillaQuestStageShouldNotBeFound("startScore.specified=false");
    }

    @Test
    @Transactional
    public void getAllMGuerillaQuestStagesByStartScoreIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mGuerillaQuestStageRepository.saveAndFlush(mGuerillaQuestStage);

        // Get all the mGuerillaQuestStageList where startScore greater than or equals to DEFAULT_START_SCORE
        defaultMGuerillaQuestStageShouldBeFound("startScore.greaterOrEqualThan=" + DEFAULT_START_SCORE);

        // Get all the mGuerillaQuestStageList where startScore greater than or equals to UPDATED_START_SCORE
        defaultMGuerillaQuestStageShouldNotBeFound("startScore.greaterOrEqualThan=" + UPDATED_START_SCORE);
    }

    @Test
    @Transactional
    public void getAllMGuerillaQuestStagesByStartScoreIsLessThanSomething() throws Exception {
        // Initialize the database
        mGuerillaQuestStageRepository.saveAndFlush(mGuerillaQuestStage);

        // Get all the mGuerillaQuestStageList where startScore less than or equals to DEFAULT_START_SCORE
        defaultMGuerillaQuestStageShouldNotBeFound("startScore.lessThan=" + DEFAULT_START_SCORE);

        // Get all the mGuerillaQuestStageList where startScore less than or equals to UPDATED_START_SCORE
        defaultMGuerillaQuestStageShouldBeFound("startScore.lessThan=" + UPDATED_START_SCORE);
    }


    @Test
    @Transactional
    public void getAllMGuerillaQuestStagesByStartScoreOpponentIsEqualToSomething() throws Exception {
        // Initialize the database
        mGuerillaQuestStageRepository.saveAndFlush(mGuerillaQuestStage);

        // Get all the mGuerillaQuestStageList where startScoreOpponent equals to DEFAULT_START_SCORE_OPPONENT
        defaultMGuerillaQuestStageShouldBeFound("startScoreOpponent.equals=" + DEFAULT_START_SCORE_OPPONENT);

        // Get all the mGuerillaQuestStageList where startScoreOpponent equals to UPDATED_START_SCORE_OPPONENT
        defaultMGuerillaQuestStageShouldNotBeFound("startScoreOpponent.equals=" + UPDATED_START_SCORE_OPPONENT);
    }

    @Test
    @Transactional
    public void getAllMGuerillaQuestStagesByStartScoreOpponentIsInShouldWork() throws Exception {
        // Initialize the database
        mGuerillaQuestStageRepository.saveAndFlush(mGuerillaQuestStage);

        // Get all the mGuerillaQuestStageList where startScoreOpponent in DEFAULT_START_SCORE_OPPONENT or UPDATED_START_SCORE_OPPONENT
        defaultMGuerillaQuestStageShouldBeFound("startScoreOpponent.in=" + DEFAULT_START_SCORE_OPPONENT + "," + UPDATED_START_SCORE_OPPONENT);

        // Get all the mGuerillaQuestStageList where startScoreOpponent equals to UPDATED_START_SCORE_OPPONENT
        defaultMGuerillaQuestStageShouldNotBeFound("startScoreOpponent.in=" + UPDATED_START_SCORE_OPPONENT);
    }

    @Test
    @Transactional
    public void getAllMGuerillaQuestStagesByStartScoreOpponentIsNullOrNotNull() throws Exception {
        // Initialize the database
        mGuerillaQuestStageRepository.saveAndFlush(mGuerillaQuestStage);

        // Get all the mGuerillaQuestStageList where startScoreOpponent is not null
        defaultMGuerillaQuestStageShouldBeFound("startScoreOpponent.specified=true");

        // Get all the mGuerillaQuestStageList where startScoreOpponent is null
        defaultMGuerillaQuestStageShouldNotBeFound("startScoreOpponent.specified=false");
    }

    @Test
    @Transactional
    public void getAllMGuerillaQuestStagesByStartScoreOpponentIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mGuerillaQuestStageRepository.saveAndFlush(mGuerillaQuestStage);

        // Get all the mGuerillaQuestStageList where startScoreOpponent greater than or equals to DEFAULT_START_SCORE_OPPONENT
        defaultMGuerillaQuestStageShouldBeFound("startScoreOpponent.greaterOrEqualThan=" + DEFAULT_START_SCORE_OPPONENT);

        // Get all the mGuerillaQuestStageList where startScoreOpponent greater than or equals to UPDATED_START_SCORE_OPPONENT
        defaultMGuerillaQuestStageShouldNotBeFound("startScoreOpponent.greaterOrEqualThan=" + UPDATED_START_SCORE_OPPONENT);
    }

    @Test
    @Transactional
    public void getAllMGuerillaQuestStagesByStartScoreOpponentIsLessThanSomething() throws Exception {
        // Initialize the database
        mGuerillaQuestStageRepository.saveAndFlush(mGuerillaQuestStage);

        // Get all the mGuerillaQuestStageList where startScoreOpponent less than or equals to DEFAULT_START_SCORE_OPPONENT
        defaultMGuerillaQuestStageShouldNotBeFound("startScoreOpponent.lessThan=" + DEFAULT_START_SCORE_OPPONENT);

        // Get all the mGuerillaQuestStageList where startScoreOpponent less than or equals to UPDATED_START_SCORE_OPPONENT
        defaultMGuerillaQuestStageShouldBeFound("startScoreOpponent.lessThan=" + UPDATED_START_SCORE_OPPONENT);
    }


    @Test
    @Transactional
    public void getAllMGuerillaQuestStagesByConditionIdIsEqualToSomething() throws Exception {
        // Initialize the database
        mGuerillaQuestStageRepository.saveAndFlush(mGuerillaQuestStage);

        // Get all the mGuerillaQuestStageList where conditionId equals to DEFAULT_CONDITION_ID
        defaultMGuerillaQuestStageShouldBeFound("conditionId.equals=" + DEFAULT_CONDITION_ID);

        // Get all the mGuerillaQuestStageList where conditionId equals to UPDATED_CONDITION_ID
        defaultMGuerillaQuestStageShouldNotBeFound("conditionId.equals=" + UPDATED_CONDITION_ID);
    }

    @Test
    @Transactional
    public void getAllMGuerillaQuestStagesByConditionIdIsInShouldWork() throws Exception {
        // Initialize the database
        mGuerillaQuestStageRepository.saveAndFlush(mGuerillaQuestStage);

        // Get all the mGuerillaQuestStageList where conditionId in DEFAULT_CONDITION_ID or UPDATED_CONDITION_ID
        defaultMGuerillaQuestStageShouldBeFound("conditionId.in=" + DEFAULT_CONDITION_ID + "," + UPDATED_CONDITION_ID);

        // Get all the mGuerillaQuestStageList where conditionId equals to UPDATED_CONDITION_ID
        defaultMGuerillaQuestStageShouldNotBeFound("conditionId.in=" + UPDATED_CONDITION_ID);
    }

    @Test
    @Transactional
    public void getAllMGuerillaQuestStagesByConditionIdIsNullOrNotNull() throws Exception {
        // Initialize the database
        mGuerillaQuestStageRepository.saveAndFlush(mGuerillaQuestStage);

        // Get all the mGuerillaQuestStageList where conditionId is not null
        defaultMGuerillaQuestStageShouldBeFound("conditionId.specified=true");

        // Get all the mGuerillaQuestStageList where conditionId is null
        defaultMGuerillaQuestStageShouldNotBeFound("conditionId.specified=false");
    }

    @Test
    @Transactional
    public void getAllMGuerillaQuestStagesByConditionIdIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mGuerillaQuestStageRepository.saveAndFlush(mGuerillaQuestStage);

        // Get all the mGuerillaQuestStageList where conditionId greater than or equals to DEFAULT_CONDITION_ID
        defaultMGuerillaQuestStageShouldBeFound("conditionId.greaterOrEqualThan=" + DEFAULT_CONDITION_ID);

        // Get all the mGuerillaQuestStageList where conditionId greater than or equals to UPDATED_CONDITION_ID
        defaultMGuerillaQuestStageShouldNotBeFound("conditionId.greaterOrEqualThan=" + UPDATED_CONDITION_ID);
    }

    @Test
    @Transactional
    public void getAllMGuerillaQuestStagesByConditionIdIsLessThanSomething() throws Exception {
        // Initialize the database
        mGuerillaQuestStageRepository.saveAndFlush(mGuerillaQuestStage);

        // Get all the mGuerillaQuestStageList where conditionId less than or equals to DEFAULT_CONDITION_ID
        defaultMGuerillaQuestStageShouldNotBeFound("conditionId.lessThan=" + DEFAULT_CONDITION_ID);

        // Get all the mGuerillaQuestStageList where conditionId less than or equals to UPDATED_CONDITION_ID
        defaultMGuerillaQuestStageShouldBeFound("conditionId.lessThan=" + UPDATED_CONDITION_ID);
    }


    @Test
    @Transactional
    public void getAllMGuerillaQuestStagesByOptionIdIsEqualToSomething() throws Exception {
        // Initialize the database
        mGuerillaQuestStageRepository.saveAndFlush(mGuerillaQuestStage);

        // Get all the mGuerillaQuestStageList where optionId equals to DEFAULT_OPTION_ID
        defaultMGuerillaQuestStageShouldBeFound("optionId.equals=" + DEFAULT_OPTION_ID);

        // Get all the mGuerillaQuestStageList where optionId equals to UPDATED_OPTION_ID
        defaultMGuerillaQuestStageShouldNotBeFound("optionId.equals=" + UPDATED_OPTION_ID);
    }

    @Test
    @Transactional
    public void getAllMGuerillaQuestStagesByOptionIdIsInShouldWork() throws Exception {
        // Initialize the database
        mGuerillaQuestStageRepository.saveAndFlush(mGuerillaQuestStage);

        // Get all the mGuerillaQuestStageList where optionId in DEFAULT_OPTION_ID or UPDATED_OPTION_ID
        defaultMGuerillaQuestStageShouldBeFound("optionId.in=" + DEFAULT_OPTION_ID + "," + UPDATED_OPTION_ID);

        // Get all the mGuerillaQuestStageList where optionId equals to UPDATED_OPTION_ID
        defaultMGuerillaQuestStageShouldNotBeFound("optionId.in=" + UPDATED_OPTION_ID);
    }

    @Test
    @Transactional
    public void getAllMGuerillaQuestStagesByOptionIdIsNullOrNotNull() throws Exception {
        // Initialize the database
        mGuerillaQuestStageRepository.saveAndFlush(mGuerillaQuestStage);

        // Get all the mGuerillaQuestStageList where optionId is not null
        defaultMGuerillaQuestStageShouldBeFound("optionId.specified=true");

        // Get all the mGuerillaQuestStageList where optionId is null
        defaultMGuerillaQuestStageShouldNotBeFound("optionId.specified=false");
    }

    @Test
    @Transactional
    public void getAllMGuerillaQuestStagesByOptionIdIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mGuerillaQuestStageRepository.saveAndFlush(mGuerillaQuestStage);

        // Get all the mGuerillaQuestStageList where optionId greater than or equals to DEFAULT_OPTION_ID
        defaultMGuerillaQuestStageShouldBeFound("optionId.greaterOrEqualThan=" + DEFAULT_OPTION_ID);

        // Get all the mGuerillaQuestStageList where optionId greater than or equals to UPDATED_OPTION_ID
        defaultMGuerillaQuestStageShouldNotBeFound("optionId.greaterOrEqualThan=" + UPDATED_OPTION_ID);
    }

    @Test
    @Transactional
    public void getAllMGuerillaQuestStagesByOptionIdIsLessThanSomething() throws Exception {
        // Initialize the database
        mGuerillaQuestStageRepository.saveAndFlush(mGuerillaQuestStage);

        // Get all the mGuerillaQuestStageList where optionId less than or equals to DEFAULT_OPTION_ID
        defaultMGuerillaQuestStageShouldNotBeFound("optionId.lessThan=" + DEFAULT_OPTION_ID);

        // Get all the mGuerillaQuestStageList where optionId less than or equals to UPDATED_OPTION_ID
        defaultMGuerillaQuestStageShouldBeFound("optionId.lessThan=" + UPDATED_OPTION_ID);
    }


    @Test
    @Transactional
    public void getAllMGuerillaQuestStagesByDeckConditionIdIsEqualToSomething() throws Exception {
        // Initialize the database
        mGuerillaQuestStageRepository.saveAndFlush(mGuerillaQuestStage);

        // Get all the mGuerillaQuestStageList where deckConditionId equals to DEFAULT_DECK_CONDITION_ID
        defaultMGuerillaQuestStageShouldBeFound("deckConditionId.equals=" + DEFAULT_DECK_CONDITION_ID);

        // Get all the mGuerillaQuestStageList where deckConditionId equals to UPDATED_DECK_CONDITION_ID
        defaultMGuerillaQuestStageShouldNotBeFound("deckConditionId.equals=" + UPDATED_DECK_CONDITION_ID);
    }

    @Test
    @Transactional
    public void getAllMGuerillaQuestStagesByDeckConditionIdIsInShouldWork() throws Exception {
        // Initialize the database
        mGuerillaQuestStageRepository.saveAndFlush(mGuerillaQuestStage);

        // Get all the mGuerillaQuestStageList where deckConditionId in DEFAULT_DECK_CONDITION_ID or UPDATED_DECK_CONDITION_ID
        defaultMGuerillaQuestStageShouldBeFound("deckConditionId.in=" + DEFAULT_DECK_CONDITION_ID + "," + UPDATED_DECK_CONDITION_ID);

        // Get all the mGuerillaQuestStageList where deckConditionId equals to UPDATED_DECK_CONDITION_ID
        defaultMGuerillaQuestStageShouldNotBeFound("deckConditionId.in=" + UPDATED_DECK_CONDITION_ID);
    }

    @Test
    @Transactional
    public void getAllMGuerillaQuestStagesByDeckConditionIdIsNullOrNotNull() throws Exception {
        // Initialize the database
        mGuerillaQuestStageRepository.saveAndFlush(mGuerillaQuestStage);

        // Get all the mGuerillaQuestStageList where deckConditionId is not null
        defaultMGuerillaQuestStageShouldBeFound("deckConditionId.specified=true");

        // Get all the mGuerillaQuestStageList where deckConditionId is null
        defaultMGuerillaQuestStageShouldNotBeFound("deckConditionId.specified=false");
    }

    @Test
    @Transactional
    public void getAllMGuerillaQuestStagesByDeckConditionIdIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mGuerillaQuestStageRepository.saveAndFlush(mGuerillaQuestStage);

        // Get all the mGuerillaQuestStageList where deckConditionId greater than or equals to DEFAULT_DECK_CONDITION_ID
        defaultMGuerillaQuestStageShouldBeFound("deckConditionId.greaterOrEqualThan=" + DEFAULT_DECK_CONDITION_ID);

        // Get all the mGuerillaQuestStageList where deckConditionId greater than or equals to UPDATED_DECK_CONDITION_ID
        defaultMGuerillaQuestStageShouldNotBeFound("deckConditionId.greaterOrEqualThan=" + UPDATED_DECK_CONDITION_ID);
    }

    @Test
    @Transactional
    public void getAllMGuerillaQuestStagesByDeckConditionIdIsLessThanSomething() throws Exception {
        // Initialize the database
        mGuerillaQuestStageRepository.saveAndFlush(mGuerillaQuestStage);

        // Get all the mGuerillaQuestStageList where deckConditionId less than or equals to DEFAULT_DECK_CONDITION_ID
        defaultMGuerillaQuestStageShouldNotBeFound("deckConditionId.lessThan=" + DEFAULT_DECK_CONDITION_ID);

        // Get all the mGuerillaQuestStageList where deckConditionId less than or equals to UPDATED_DECK_CONDITION_ID
        defaultMGuerillaQuestStageShouldBeFound("deckConditionId.lessThan=" + UPDATED_DECK_CONDITION_ID);
    }


    @Test
    @Transactional
    public void getAllMGuerillaQuestStagesByIdIsEqualToSomething() throws Exception {
        // Get already existing entity
        MGuerillaQuestWorld id = mGuerillaQuestStage.getId();
        mGuerillaQuestStageRepository.saveAndFlush(mGuerillaQuestStage);
        Long idId = id.getId();

        // Get all the mGuerillaQuestStageList where id equals to idId
        defaultMGuerillaQuestStageShouldBeFound("idId.equals=" + idId);

        // Get all the mGuerillaQuestStageList where id equals to idId + 1
        defaultMGuerillaQuestStageShouldNotBeFound("idId.equals=" + (idId + 1));
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultMGuerillaQuestStageShouldBeFound(String filter) throws Exception {
        restMGuerillaQuestStageMockMvc.perform(get("/api/m-guerilla-quest-stages?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(mGuerillaQuestStage.getId().intValue())))
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
        restMGuerillaQuestStageMockMvc.perform(get("/api/m-guerilla-quest-stages/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultMGuerillaQuestStageShouldNotBeFound(String filter) throws Exception {
        restMGuerillaQuestStageMockMvc.perform(get("/api/m-guerilla-quest-stages?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restMGuerillaQuestStageMockMvc.perform(get("/api/m-guerilla-quest-stages/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("0"));
    }


    @Test
    @Transactional
    public void getNonExistingMGuerillaQuestStage() throws Exception {
        // Get the mGuerillaQuestStage
        restMGuerillaQuestStageMockMvc.perform(get("/api/m-guerilla-quest-stages/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateMGuerillaQuestStage() throws Exception {
        // Initialize the database
        mGuerillaQuestStageRepository.saveAndFlush(mGuerillaQuestStage);

        int databaseSizeBeforeUpdate = mGuerillaQuestStageRepository.findAll().size();

        // Update the mGuerillaQuestStage
        MGuerillaQuestStage updatedMGuerillaQuestStage = mGuerillaQuestStageRepository.findById(mGuerillaQuestStage.getId()).get();
        // Disconnect from session so that the updates on updatedMGuerillaQuestStage are not directly saved in db
        em.detach(updatedMGuerillaQuestStage);
        updatedMGuerillaQuestStage
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
        MGuerillaQuestStageDTO mGuerillaQuestStageDTO = mGuerillaQuestStageMapper.toDto(updatedMGuerillaQuestStage);

        restMGuerillaQuestStageMockMvc.perform(put("/api/m-guerilla-quest-stages")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mGuerillaQuestStageDTO)))
            .andExpect(status().isOk());

        // Validate the MGuerillaQuestStage in the database
        List<MGuerillaQuestStage> mGuerillaQuestStageList = mGuerillaQuestStageRepository.findAll();
        assertThat(mGuerillaQuestStageList).hasSize(databaseSizeBeforeUpdate);
        MGuerillaQuestStage testMGuerillaQuestStage = mGuerillaQuestStageList.get(mGuerillaQuestStageList.size() - 1);
        assertThat(testMGuerillaQuestStage.getWorldId()).isEqualTo(UPDATED_WORLD_ID);
        assertThat(testMGuerillaQuestStage.getNumber()).isEqualTo(UPDATED_NUMBER);
        assertThat(testMGuerillaQuestStage.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testMGuerillaQuestStage.getImagePath()).isEqualTo(UPDATED_IMAGE_PATH);
        assertThat(testMGuerillaQuestStage.getCharacterThumbnailPath()).isEqualTo(UPDATED_CHARACTER_THUMBNAIL_PATH);
        assertThat(testMGuerillaQuestStage.getDifficulty()).isEqualTo(UPDATED_DIFFICULTY);
        assertThat(testMGuerillaQuestStage.getStageUnlockPattern()).isEqualTo(UPDATED_STAGE_UNLOCK_PATTERN);
        assertThat(testMGuerillaQuestStage.getStoryOnly()).isEqualTo(UPDATED_STORY_ONLY);
        assertThat(testMGuerillaQuestStage.getFirstHalfNpcDeckId()).isEqualTo(UPDATED_FIRST_HALF_NPC_DECK_ID);
        assertThat(testMGuerillaQuestStage.getFirstHalfEnvironmentId()).isEqualTo(UPDATED_FIRST_HALF_ENVIRONMENT_ID);
        assertThat(testMGuerillaQuestStage.getSecondHalfNpcDeckId()).isEqualTo(UPDATED_SECOND_HALF_NPC_DECK_ID);
        assertThat(testMGuerillaQuestStage.getSecondHalfEnvironmentId()).isEqualTo(UPDATED_SECOND_HALF_ENVIRONMENT_ID);
        assertThat(testMGuerillaQuestStage.getExtraFirstHalfNpcDeckId()).isEqualTo(UPDATED_EXTRA_FIRST_HALF_NPC_DECK_ID);
        assertThat(testMGuerillaQuestStage.getExtraSecondHalfNpcDeckId()).isEqualTo(UPDATED_EXTRA_SECOND_HALF_NPC_DECK_ID);
        assertThat(testMGuerillaQuestStage.getConsumeAp()).isEqualTo(UPDATED_CONSUME_AP);
        assertThat(testMGuerillaQuestStage.getKickOffType()).isEqualTo(UPDATED_KICK_OFF_TYPE);
        assertThat(testMGuerillaQuestStage.getMatchMinute()).isEqualTo(UPDATED_MATCH_MINUTE);
        assertThat(testMGuerillaQuestStage.getEnableExtra()).isEqualTo(UPDATED_ENABLE_EXTRA);
        assertThat(testMGuerillaQuestStage.getEnablePk()).isEqualTo(UPDATED_ENABLE_PK);
        assertThat(testMGuerillaQuestStage.getAiLevel()).isEqualTo(UPDATED_AI_LEVEL);
        assertThat(testMGuerillaQuestStage.getStartAtSecondHalf()).isEqualTo(UPDATED_START_AT_SECOND_HALF);
        assertThat(testMGuerillaQuestStage.getStartScore()).isEqualTo(UPDATED_START_SCORE);
        assertThat(testMGuerillaQuestStage.getStartScoreOpponent()).isEqualTo(UPDATED_START_SCORE_OPPONENT);
        assertThat(testMGuerillaQuestStage.getConditionId()).isEqualTo(UPDATED_CONDITION_ID);
        assertThat(testMGuerillaQuestStage.getOptionId()).isEqualTo(UPDATED_OPTION_ID);
        assertThat(testMGuerillaQuestStage.getDeckConditionId()).isEqualTo(UPDATED_DECK_CONDITION_ID);
    }

    @Test
    @Transactional
    public void updateNonExistingMGuerillaQuestStage() throws Exception {
        int databaseSizeBeforeUpdate = mGuerillaQuestStageRepository.findAll().size();

        // Create the MGuerillaQuestStage
        MGuerillaQuestStageDTO mGuerillaQuestStageDTO = mGuerillaQuestStageMapper.toDto(mGuerillaQuestStage);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restMGuerillaQuestStageMockMvc.perform(put("/api/m-guerilla-quest-stages")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mGuerillaQuestStageDTO)))
            .andExpect(status().isBadRequest());

        // Validate the MGuerillaQuestStage in the database
        List<MGuerillaQuestStage> mGuerillaQuestStageList = mGuerillaQuestStageRepository.findAll();
        assertThat(mGuerillaQuestStageList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteMGuerillaQuestStage() throws Exception {
        // Initialize the database
        mGuerillaQuestStageRepository.saveAndFlush(mGuerillaQuestStage);

        int databaseSizeBeforeDelete = mGuerillaQuestStageRepository.findAll().size();

        // Delete the mGuerillaQuestStage
        restMGuerillaQuestStageMockMvc.perform(delete("/api/m-guerilla-quest-stages/{id}", mGuerillaQuestStage.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database is empty
        List<MGuerillaQuestStage> mGuerillaQuestStageList = mGuerillaQuestStageRepository.findAll();
        assertThat(mGuerillaQuestStageList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(MGuerillaQuestStage.class);
        MGuerillaQuestStage mGuerillaQuestStage1 = new MGuerillaQuestStage();
        mGuerillaQuestStage1.setId(1L);
        MGuerillaQuestStage mGuerillaQuestStage2 = new MGuerillaQuestStage();
        mGuerillaQuestStage2.setId(mGuerillaQuestStage1.getId());
        assertThat(mGuerillaQuestStage1).isEqualTo(mGuerillaQuestStage2);
        mGuerillaQuestStage2.setId(2L);
        assertThat(mGuerillaQuestStage1).isNotEqualTo(mGuerillaQuestStage2);
        mGuerillaQuestStage1.setId(null);
        assertThat(mGuerillaQuestStage1).isNotEqualTo(mGuerillaQuestStage2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(MGuerillaQuestStageDTO.class);
        MGuerillaQuestStageDTO mGuerillaQuestStageDTO1 = new MGuerillaQuestStageDTO();
        mGuerillaQuestStageDTO1.setId(1L);
        MGuerillaQuestStageDTO mGuerillaQuestStageDTO2 = new MGuerillaQuestStageDTO();
        assertThat(mGuerillaQuestStageDTO1).isNotEqualTo(mGuerillaQuestStageDTO2);
        mGuerillaQuestStageDTO2.setId(mGuerillaQuestStageDTO1.getId());
        assertThat(mGuerillaQuestStageDTO1).isEqualTo(mGuerillaQuestStageDTO2);
        mGuerillaQuestStageDTO2.setId(2L);
        assertThat(mGuerillaQuestStageDTO1).isNotEqualTo(mGuerillaQuestStageDTO2);
        mGuerillaQuestStageDTO1.setId(null);
        assertThat(mGuerillaQuestStageDTO1).isNotEqualTo(mGuerillaQuestStageDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(mGuerillaQuestStageMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(mGuerillaQuestStageMapper.fromId(null)).isNull();
    }
}
