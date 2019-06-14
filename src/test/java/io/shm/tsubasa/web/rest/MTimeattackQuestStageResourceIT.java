package io.shm.tsubasa.web.rest;

import io.shm.tsubasa.TsubasaApp;
import io.shm.tsubasa.domain.MTimeattackQuestStage;
import io.shm.tsubasa.domain.MTimeattackQuestWorld;
import io.shm.tsubasa.repository.MTimeattackQuestStageRepository;
import io.shm.tsubasa.service.MTimeattackQuestStageService;
import io.shm.tsubasa.service.dto.MTimeattackQuestStageDTO;
import io.shm.tsubasa.service.mapper.MTimeattackQuestStageMapper;
import io.shm.tsubasa.web.rest.errors.ExceptionTranslator;
import io.shm.tsubasa.service.dto.MTimeattackQuestStageCriteria;
import io.shm.tsubasa.service.MTimeattackQuestStageQueryService;

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
 * Integration tests for the {@Link MTimeattackQuestStageResource} REST controller.
 */
@SpringBootTest(classes = TsubasaApp.class)
public class MTimeattackQuestStageResourceIT {

    private static final Integer DEFAULT_WORLD_ID = 1;
    private static final Integer UPDATED_WORLD_ID = 2;

    private static final Integer DEFAULT_NUMBER = 1;
    private static final Integer UPDATED_NUMBER = 2;

    private static final Integer DEFAULT_START_AT = 1;
    private static final Integer UPDATED_START_AT = 2;

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_KICKOFF_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_KICKOFF_DESCRIPTION = "BBBBBBBBBB";

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

    private static final Integer DEFAULT_TICKET_ID = 1;
    private static final Integer UPDATED_TICKET_ID = 2;

    private static final Integer DEFAULT_TICKET_AMOUNT = 1;
    private static final Integer UPDATED_TICKET_AMOUNT = 2;

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

    @Autowired
    private MTimeattackQuestStageRepository mTimeattackQuestStageRepository;

    @Autowired
    private MTimeattackQuestStageMapper mTimeattackQuestStageMapper;

    @Autowired
    private MTimeattackQuestStageService mTimeattackQuestStageService;

    @Autowired
    private MTimeattackQuestStageQueryService mTimeattackQuestStageQueryService;

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

    private MockMvc restMTimeattackQuestStageMockMvc;

    private MTimeattackQuestStage mTimeattackQuestStage;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final MTimeattackQuestStageResource mTimeattackQuestStageResource = new MTimeattackQuestStageResource(mTimeattackQuestStageService, mTimeattackQuestStageQueryService);
        this.restMTimeattackQuestStageMockMvc = MockMvcBuilders.standaloneSetup(mTimeattackQuestStageResource)
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
    public static MTimeattackQuestStage createEntity(EntityManager em) {
        MTimeattackQuestStage mTimeattackQuestStage = new MTimeattackQuestStage()
            .worldId(DEFAULT_WORLD_ID)
            .number(DEFAULT_NUMBER)
            .startAt(DEFAULT_START_AT)
            .name(DEFAULT_NAME)
            .kickoffDescription(DEFAULT_KICKOFF_DESCRIPTION)
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
            .ticketId(DEFAULT_TICKET_ID)
            .ticketAmount(DEFAULT_TICKET_AMOUNT)
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
            .shortName(DEFAULT_SHORT_NAME);
        // Add required entity
        MTimeattackQuestWorld mTimeattackQuestWorld;
        if (TestUtil.findAll(em, MTimeattackQuestWorld.class).isEmpty()) {
            mTimeattackQuestWorld = MTimeattackQuestWorldResourceIT.createEntity(em);
            em.persist(mTimeattackQuestWorld);
            em.flush();
        } else {
            mTimeattackQuestWorld = TestUtil.findAll(em, MTimeattackQuestWorld.class).get(0);
        }
        mTimeattackQuestStage.setId(mTimeattackQuestWorld);
        return mTimeattackQuestStage;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static MTimeattackQuestStage createUpdatedEntity(EntityManager em) {
        MTimeattackQuestStage mTimeattackQuestStage = new MTimeattackQuestStage()
            .worldId(UPDATED_WORLD_ID)
            .number(UPDATED_NUMBER)
            .startAt(UPDATED_START_AT)
            .name(UPDATED_NAME)
            .kickoffDescription(UPDATED_KICKOFF_DESCRIPTION)
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
            .ticketId(UPDATED_TICKET_ID)
            .ticketAmount(UPDATED_TICKET_AMOUNT)
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
            .shortName(UPDATED_SHORT_NAME);
        // Add required entity
        MTimeattackQuestWorld mTimeattackQuestWorld;
        if (TestUtil.findAll(em, MTimeattackQuestWorld.class).isEmpty()) {
            mTimeattackQuestWorld = MTimeattackQuestWorldResourceIT.createUpdatedEntity(em);
            em.persist(mTimeattackQuestWorld);
            em.flush();
        } else {
            mTimeattackQuestWorld = TestUtil.findAll(em, MTimeattackQuestWorld.class).get(0);
        }
        mTimeattackQuestStage.setId(mTimeattackQuestWorld);
        return mTimeattackQuestStage;
    }

    @BeforeEach
    public void initTest() {
        mTimeattackQuestStage = createEntity(em);
    }

    @Test
    @Transactional
    public void createMTimeattackQuestStage() throws Exception {
        int databaseSizeBeforeCreate = mTimeattackQuestStageRepository.findAll().size();

        // Create the MTimeattackQuestStage
        MTimeattackQuestStageDTO mTimeattackQuestStageDTO = mTimeattackQuestStageMapper.toDto(mTimeattackQuestStage);
        restMTimeattackQuestStageMockMvc.perform(post("/api/m-timeattack-quest-stages")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mTimeattackQuestStageDTO)))
            .andExpect(status().isCreated());

        // Validate the MTimeattackQuestStage in the database
        List<MTimeattackQuestStage> mTimeattackQuestStageList = mTimeattackQuestStageRepository.findAll();
        assertThat(mTimeattackQuestStageList).hasSize(databaseSizeBeforeCreate + 1);
        MTimeattackQuestStage testMTimeattackQuestStage = mTimeattackQuestStageList.get(mTimeattackQuestStageList.size() - 1);
        assertThat(testMTimeattackQuestStage.getWorldId()).isEqualTo(DEFAULT_WORLD_ID);
        assertThat(testMTimeattackQuestStage.getNumber()).isEqualTo(DEFAULT_NUMBER);
        assertThat(testMTimeattackQuestStage.getStartAt()).isEqualTo(DEFAULT_START_AT);
        assertThat(testMTimeattackQuestStage.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testMTimeattackQuestStage.getKickoffDescription()).isEqualTo(DEFAULT_KICKOFF_DESCRIPTION);
        assertThat(testMTimeattackQuestStage.getImagePath()).isEqualTo(DEFAULT_IMAGE_PATH);
        assertThat(testMTimeattackQuestStage.getCharacterThumbnailPath()).isEqualTo(DEFAULT_CHARACTER_THUMBNAIL_PATH);
        assertThat(testMTimeattackQuestStage.getDifficulty()).isEqualTo(DEFAULT_DIFFICULTY);
        assertThat(testMTimeattackQuestStage.getStageUnlockPattern()).isEqualTo(DEFAULT_STAGE_UNLOCK_PATTERN);
        assertThat(testMTimeattackQuestStage.getStoryOnly()).isEqualTo(DEFAULT_STORY_ONLY);
        assertThat(testMTimeattackQuestStage.getFirstHalfNpcDeckId()).isEqualTo(DEFAULT_FIRST_HALF_NPC_DECK_ID);
        assertThat(testMTimeattackQuestStage.getFirstHalfEnvironmentId()).isEqualTo(DEFAULT_FIRST_HALF_ENVIRONMENT_ID);
        assertThat(testMTimeattackQuestStage.getSecondHalfNpcDeckId()).isEqualTo(DEFAULT_SECOND_HALF_NPC_DECK_ID);
        assertThat(testMTimeattackQuestStage.getSecondHalfEnvironmentId()).isEqualTo(DEFAULT_SECOND_HALF_ENVIRONMENT_ID);
        assertThat(testMTimeattackQuestStage.getExtraFirstHalfNpcDeckId()).isEqualTo(DEFAULT_EXTRA_FIRST_HALF_NPC_DECK_ID);
        assertThat(testMTimeattackQuestStage.getConsumeAp()).isEqualTo(DEFAULT_CONSUME_AP);
        assertThat(testMTimeattackQuestStage.getTicketId()).isEqualTo(DEFAULT_TICKET_ID);
        assertThat(testMTimeattackQuestStage.getTicketAmount()).isEqualTo(DEFAULT_TICKET_AMOUNT);
        assertThat(testMTimeattackQuestStage.getKickOffType()).isEqualTo(DEFAULT_KICK_OFF_TYPE);
        assertThat(testMTimeattackQuestStage.getMatchMinute()).isEqualTo(DEFAULT_MATCH_MINUTE);
        assertThat(testMTimeattackQuestStage.getEnableExtra()).isEqualTo(DEFAULT_ENABLE_EXTRA);
        assertThat(testMTimeattackQuestStage.getEnablePk()).isEqualTo(DEFAULT_ENABLE_PK);
        assertThat(testMTimeattackQuestStage.getAiLevel()).isEqualTo(DEFAULT_AI_LEVEL);
        assertThat(testMTimeattackQuestStage.getStartAtSecondHalf()).isEqualTo(DEFAULT_START_AT_SECOND_HALF);
        assertThat(testMTimeattackQuestStage.getStartScore()).isEqualTo(DEFAULT_START_SCORE);
        assertThat(testMTimeattackQuestStage.getStartScoreOpponent()).isEqualTo(DEFAULT_START_SCORE_OPPONENT);
        assertThat(testMTimeattackQuestStage.getConditionId()).isEqualTo(DEFAULT_CONDITION_ID);
        assertThat(testMTimeattackQuestStage.getOptionId()).isEqualTo(DEFAULT_OPTION_ID);
        assertThat(testMTimeattackQuestStage.getDeckConditionId()).isEqualTo(DEFAULT_DECK_CONDITION_ID);
        assertThat(testMTimeattackQuestStage.getShortName()).isEqualTo(DEFAULT_SHORT_NAME);
    }

    @Test
    @Transactional
    public void createMTimeattackQuestStageWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = mTimeattackQuestStageRepository.findAll().size();

        // Create the MTimeattackQuestStage with an existing ID
        mTimeattackQuestStage.setId(1L);
        MTimeattackQuestStageDTO mTimeattackQuestStageDTO = mTimeattackQuestStageMapper.toDto(mTimeattackQuestStage);

        // An entity with an existing ID cannot be created, so this API call must fail
        restMTimeattackQuestStageMockMvc.perform(post("/api/m-timeattack-quest-stages")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mTimeattackQuestStageDTO)))
            .andExpect(status().isBadRequest());

        // Validate the MTimeattackQuestStage in the database
        List<MTimeattackQuestStage> mTimeattackQuestStageList = mTimeattackQuestStageRepository.findAll();
        assertThat(mTimeattackQuestStageList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkWorldIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = mTimeattackQuestStageRepository.findAll().size();
        // set the field null
        mTimeattackQuestStage.setWorldId(null);

        // Create the MTimeattackQuestStage, which fails.
        MTimeattackQuestStageDTO mTimeattackQuestStageDTO = mTimeattackQuestStageMapper.toDto(mTimeattackQuestStage);

        restMTimeattackQuestStageMockMvc.perform(post("/api/m-timeattack-quest-stages")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mTimeattackQuestStageDTO)))
            .andExpect(status().isBadRequest());

        List<MTimeattackQuestStage> mTimeattackQuestStageList = mTimeattackQuestStageRepository.findAll();
        assertThat(mTimeattackQuestStageList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkNumberIsRequired() throws Exception {
        int databaseSizeBeforeTest = mTimeattackQuestStageRepository.findAll().size();
        // set the field null
        mTimeattackQuestStage.setNumber(null);

        // Create the MTimeattackQuestStage, which fails.
        MTimeattackQuestStageDTO mTimeattackQuestStageDTO = mTimeattackQuestStageMapper.toDto(mTimeattackQuestStage);

        restMTimeattackQuestStageMockMvc.perform(post("/api/m-timeattack-quest-stages")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mTimeattackQuestStageDTO)))
            .andExpect(status().isBadRequest());

        List<MTimeattackQuestStage> mTimeattackQuestStageList = mTimeattackQuestStageRepository.findAll();
        assertThat(mTimeattackQuestStageList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkStartAtIsRequired() throws Exception {
        int databaseSizeBeforeTest = mTimeattackQuestStageRepository.findAll().size();
        // set the field null
        mTimeattackQuestStage.setStartAt(null);

        // Create the MTimeattackQuestStage, which fails.
        MTimeattackQuestStageDTO mTimeattackQuestStageDTO = mTimeattackQuestStageMapper.toDto(mTimeattackQuestStage);

        restMTimeattackQuestStageMockMvc.perform(post("/api/m-timeattack-quest-stages")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mTimeattackQuestStageDTO)))
            .andExpect(status().isBadRequest());

        List<MTimeattackQuestStage> mTimeattackQuestStageList = mTimeattackQuestStageRepository.findAll();
        assertThat(mTimeattackQuestStageList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkDifficultyIsRequired() throws Exception {
        int databaseSizeBeforeTest = mTimeattackQuestStageRepository.findAll().size();
        // set the field null
        mTimeattackQuestStage.setDifficulty(null);

        // Create the MTimeattackQuestStage, which fails.
        MTimeattackQuestStageDTO mTimeattackQuestStageDTO = mTimeattackQuestStageMapper.toDto(mTimeattackQuestStage);

        restMTimeattackQuestStageMockMvc.perform(post("/api/m-timeattack-quest-stages")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mTimeattackQuestStageDTO)))
            .andExpect(status().isBadRequest());

        List<MTimeattackQuestStage> mTimeattackQuestStageList = mTimeattackQuestStageRepository.findAll();
        assertThat(mTimeattackQuestStageList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkStageUnlockPatternIsRequired() throws Exception {
        int databaseSizeBeforeTest = mTimeattackQuestStageRepository.findAll().size();
        // set the field null
        mTimeattackQuestStage.setStageUnlockPattern(null);

        // Create the MTimeattackQuestStage, which fails.
        MTimeattackQuestStageDTO mTimeattackQuestStageDTO = mTimeattackQuestStageMapper.toDto(mTimeattackQuestStage);

        restMTimeattackQuestStageMockMvc.perform(post("/api/m-timeattack-quest-stages")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mTimeattackQuestStageDTO)))
            .andExpect(status().isBadRequest());

        List<MTimeattackQuestStage> mTimeattackQuestStageList = mTimeattackQuestStageRepository.findAll();
        assertThat(mTimeattackQuestStageList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkStoryOnlyIsRequired() throws Exception {
        int databaseSizeBeforeTest = mTimeattackQuestStageRepository.findAll().size();
        // set the field null
        mTimeattackQuestStage.setStoryOnly(null);

        // Create the MTimeattackQuestStage, which fails.
        MTimeattackQuestStageDTO mTimeattackQuestStageDTO = mTimeattackQuestStageMapper.toDto(mTimeattackQuestStage);

        restMTimeattackQuestStageMockMvc.perform(post("/api/m-timeattack-quest-stages")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mTimeattackQuestStageDTO)))
            .andExpect(status().isBadRequest());

        List<MTimeattackQuestStage> mTimeattackQuestStageList = mTimeattackQuestStageRepository.findAll();
        assertThat(mTimeattackQuestStageList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkFirstHalfNpcDeckIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = mTimeattackQuestStageRepository.findAll().size();
        // set the field null
        mTimeattackQuestStage.setFirstHalfNpcDeckId(null);

        // Create the MTimeattackQuestStage, which fails.
        MTimeattackQuestStageDTO mTimeattackQuestStageDTO = mTimeattackQuestStageMapper.toDto(mTimeattackQuestStage);

        restMTimeattackQuestStageMockMvc.perform(post("/api/m-timeattack-quest-stages")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mTimeattackQuestStageDTO)))
            .andExpect(status().isBadRequest());

        List<MTimeattackQuestStage> mTimeattackQuestStageList = mTimeattackQuestStageRepository.findAll();
        assertThat(mTimeattackQuestStageList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkFirstHalfEnvironmentIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = mTimeattackQuestStageRepository.findAll().size();
        // set the field null
        mTimeattackQuestStage.setFirstHalfEnvironmentId(null);

        // Create the MTimeattackQuestStage, which fails.
        MTimeattackQuestStageDTO mTimeattackQuestStageDTO = mTimeattackQuestStageMapper.toDto(mTimeattackQuestStage);

        restMTimeattackQuestStageMockMvc.perform(post("/api/m-timeattack-quest-stages")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mTimeattackQuestStageDTO)))
            .andExpect(status().isBadRequest());

        List<MTimeattackQuestStage> mTimeattackQuestStageList = mTimeattackQuestStageRepository.findAll();
        assertThat(mTimeattackQuestStageList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkSecondHalfNpcDeckIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = mTimeattackQuestStageRepository.findAll().size();
        // set the field null
        mTimeattackQuestStage.setSecondHalfNpcDeckId(null);

        // Create the MTimeattackQuestStage, which fails.
        MTimeattackQuestStageDTO mTimeattackQuestStageDTO = mTimeattackQuestStageMapper.toDto(mTimeattackQuestStage);

        restMTimeattackQuestStageMockMvc.perform(post("/api/m-timeattack-quest-stages")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mTimeattackQuestStageDTO)))
            .andExpect(status().isBadRequest());

        List<MTimeattackQuestStage> mTimeattackQuestStageList = mTimeattackQuestStageRepository.findAll();
        assertThat(mTimeattackQuestStageList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkSecondHalfEnvironmentIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = mTimeattackQuestStageRepository.findAll().size();
        // set the field null
        mTimeattackQuestStage.setSecondHalfEnvironmentId(null);

        // Create the MTimeattackQuestStage, which fails.
        MTimeattackQuestStageDTO mTimeattackQuestStageDTO = mTimeattackQuestStageMapper.toDto(mTimeattackQuestStage);

        restMTimeattackQuestStageMockMvc.perform(post("/api/m-timeattack-quest-stages")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mTimeattackQuestStageDTO)))
            .andExpect(status().isBadRequest());

        List<MTimeattackQuestStage> mTimeattackQuestStageList = mTimeattackQuestStageRepository.findAll();
        assertThat(mTimeattackQuestStageList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkExtraFirstHalfNpcDeckIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = mTimeattackQuestStageRepository.findAll().size();
        // set the field null
        mTimeattackQuestStage.setExtraFirstHalfNpcDeckId(null);

        // Create the MTimeattackQuestStage, which fails.
        MTimeattackQuestStageDTO mTimeattackQuestStageDTO = mTimeattackQuestStageMapper.toDto(mTimeattackQuestStage);

        restMTimeattackQuestStageMockMvc.perform(post("/api/m-timeattack-quest-stages")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mTimeattackQuestStageDTO)))
            .andExpect(status().isBadRequest());

        List<MTimeattackQuestStage> mTimeattackQuestStageList = mTimeattackQuestStageRepository.findAll();
        assertThat(mTimeattackQuestStageList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkConsumeApIsRequired() throws Exception {
        int databaseSizeBeforeTest = mTimeattackQuestStageRepository.findAll().size();
        // set the field null
        mTimeattackQuestStage.setConsumeAp(null);

        // Create the MTimeattackQuestStage, which fails.
        MTimeattackQuestStageDTO mTimeattackQuestStageDTO = mTimeattackQuestStageMapper.toDto(mTimeattackQuestStage);

        restMTimeattackQuestStageMockMvc.perform(post("/api/m-timeattack-quest-stages")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mTimeattackQuestStageDTO)))
            .andExpect(status().isBadRequest());

        List<MTimeattackQuestStage> mTimeattackQuestStageList = mTimeattackQuestStageRepository.findAll();
        assertThat(mTimeattackQuestStageList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkTicketAmountIsRequired() throws Exception {
        int databaseSizeBeforeTest = mTimeattackQuestStageRepository.findAll().size();
        // set the field null
        mTimeattackQuestStage.setTicketAmount(null);

        // Create the MTimeattackQuestStage, which fails.
        MTimeattackQuestStageDTO mTimeattackQuestStageDTO = mTimeattackQuestStageMapper.toDto(mTimeattackQuestStage);

        restMTimeattackQuestStageMockMvc.perform(post("/api/m-timeattack-quest-stages")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mTimeattackQuestStageDTO)))
            .andExpect(status().isBadRequest());

        List<MTimeattackQuestStage> mTimeattackQuestStageList = mTimeattackQuestStageRepository.findAll();
        assertThat(mTimeattackQuestStageList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkKickOffTypeIsRequired() throws Exception {
        int databaseSizeBeforeTest = mTimeattackQuestStageRepository.findAll().size();
        // set the field null
        mTimeattackQuestStage.setKickOffType(null);

        // Create the MTimeattackQuestStage, which fails.
        MTimeattackQuestStageDTO mTimeattackQuestStageDTO = mTimeattackQuestStageMapper.toDto(mTimeattackQuestStage);

        restMTimeattackQuestStageMockMvc.perform(post("/api/m-timeattack-quest-stages")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mTimeattackQuestStageDTO)))
            .andExpect(status().isBadRequest());

        List<MTimeattackQuestStage> mTimeattackQuestStageList = mTimeattackQuestStageRepository.findAll();
        assertThat(mTimeattackQuestStageList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkMatchMinuteIsRequired() throws Exception {
        int databaseSizeBeforeTest = mTimeattackQuestStageRepository.findAll().size();
        // set the field null
        mTimeattackQuestStage.setMatchMinute(null);

        // Create the MTimeattackQuestStage, which fails.
        MTimeattackQuestStageDTO mTimeattackQuestStageDTO = mTimeattackQuestStageMapper.toDto(mTimeattackQuestStage);

        restMTimeattackQuestStageMockMvc.perform(post("/api/m-timeattack-quest-stages")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mTimeattackQuestStageDTO)))
            .andExpect(status().isBadRequest());

        List<MTimeattackQuestStage> mTimeattackQuestStageList = mTimeattackQuestStageRepository.findAll();
        assertThat(mTimeattackQuestStageList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkEnableExtraIsRequired() throws Exception {
        int databaseSizeBeforeTest = mTimeattackQuestStageRepository.findAll().size();
        // set the field null
        mTimeattackQuestStage.setEnableExtra(null);

        // Create the MTimeattackQuestStage, which fails.
        MTimeattackQuestStageDTO mTimeattackQuestStageDTO = mTimeattackQuestStageMapper.toDto(mTimeattackQuestStage);

        restMTimeattackQuestStageMockMvc.perform(post("/api/m-timeattack-quest-stages")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mTimeattackQuestStageDTO)))
            .andExpect(status().isBadRequest());

        List<MTimeattackQuestStage> mTimeattackQuestStageList = mTimeattackQuestStageRepository.findAll();
        assertThat(mTimeattackQuestStageList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkEnablePkIsRequired() throws Exception {
        int databaseSizeBeforeTest = mTimeattackQuestStageRepository.findAll().size();
        // set the field null
        mTimeattackQuestStage.setEnablePk(null);

        // Create the MTimeattackQuestStage, which fails.
        MTimeattackQuestStageDTO mTimeattackQuestStageDTO = mTimeattackQuestStageMapper.toDto(mTimeattackQuestStage);

        restMTimeattackQuestStageMockMvc.perform(post("/api/m-timeattack-quest-stages")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mTimeattackQuestStageDTO)))
            .andExpect(status().isBadRequest());

        List<MTimeattackQuestStage> mTimeattackQuestStageList = mTimeattackQuestStageRepository.findAll();
        assertThat(mTimeattackQuestStageList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkAiLevelIsRequired() throws Exception {
        int databaseSizeBeforeTest = mTimeattackQuestStageRepository.findAll().size();
        // set the field null
        mTimeattackQuestStage.setAiLevel(null);

        // Create the MTimeattackQuestStage, which fails.
        MTimeattackQuestStageDTO mTimeattackQuestStageDTO = mTimeattackQuestStageMapper.toDto(mTimeattackQuestStage);

        restMTimeattackQuestStageMockMvc.perform(post("/api/m-timeattack-quest-stages")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mTimeattackQuestStageDTO)))
            .andExpect(status().isBadRequest());

        List<MTimeattackQuestStage> mTimeattackQuestStageList = mTimeattackQuestStageRepository.findAll();
        assertThat(mTimeattackQuestStageList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkStartAtSecondHalfIsRequired() throws Exception {
        int databaseSizeBeforeTest = mTimeattackQuestStageRepository.findAll().size();
        // set the field null
        mTimeattackQuestStage.setStartAtSecondHalf(null);

        // Create the MTimeattackQuestStage, which fails.
        MTimeattackQuestStageDTO mTimeattackQuestStageDTO = mTimeattackQuestStageMapper.toDto(mTimeattackQuestStage);

        restMTimeattackQuestStageMockMvc.perform(post("/api/m-timeattack-quest-stages")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mTimeattackQuestStageDTO)))
            .andExpect(status().isBadRequest());

        List<MTimeattackQuestStage> mTimeattackQuestStageList = mTimeattackQuestStageRepository.findAll();
        assertThat(mTimeattackQuestStageList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkStartScoreIsRequired() throws Exception {
        int databaseSizeBeforeTest = mTimeattackQuestStageRepository.findAll().size();
        // set the field null
        mTimeattackQuestStage.setStartScore(null);

        // Create the MTimeattackQuestStage, which fails.
        MTimeattackQuestStageDTO mTimeattackQuestStageDTO = mTimeattackQuestStageMapper.toDto(mTimeattackQuestStage);

        restMTimeattackQuestStageMockMvc.perform(post("/api/m-timeattack-quest-stages")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mTimeattackQuestStageDTO)))
            .andExpect(status().isBadRequest());

        List<MTimeattackQuestStage> mTimeattackQuestStageList = mTimeattackQuestStageRepository.findAll();
        assertThat(mTimeattackQuestStageList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkStartScoreOpponentIsRequired() throws Exception {
        int databaseSizeBeforeTest = mTimeattackQuestStageRepository.findAll().size();
        // set the field null
        mTimeattackQuestStage.setStartScoreOpponent(null);

        // Create the MTimeattackQuestStage, which fails.
        MTimeattackQuestStageDTO mTimeattackQuestStageDTO = mTimeattackQuestStageMapper.toDto(mTimeattackQuestStage);

        restMTimeattackQuestStageMockMvc.perform(post("/api/m-timeattack-quest-stages")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mTimeattackQuestStageDTO)))
            .andExpect(status().isBadRequest());

        List<MTimeattackQuestStage> mTimeattackQuestStageList = mTimeattackQuestStageRepository.findAll();
        assertThat(mTimeattackQuestStageList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllMTimeattackQuestStages() throws Exception {
        // Initialize the database
        mTimeattackQuestStageRepository.saveAndFlush(mTimeattackQuestStage);

        // Get all the mTimeattackQuestStageList
        restMTimeattackQuestStageMockMvc.perform(get("/api/m-timeattack-quest-stages?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(mTimeattackQuestStage.getId().intValue())))
            .andExpect(jsonPath("$.[*].worldId").value(hasItem(DEFAULT_WORLD_ID)))
            .andExpect(jsonPath("$.[*].number").value(hasItem(DEFAULT_NUMBER)))
            .andExpect(jsonPath("$.[*].startAt").value(hasItem(DEFAULT_START_AT)))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME.toString())))
            .andExpect(jsonPath("$.[*].kickoffDescription").value(hasItem(DEFAULT_KICKOFF_DESCRIPTION.toString())))
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
            .andExpect(jsonPath("$.[*].ticketId").value(hasItem(DEFAULT_TICKET_ID)))
            .andExpect(jsonPath("$.[*].ticketAmount").value(hasItem(DEFAULT_TICKET_AMOUNT)))
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
            .andExpect(jsonPath("$.[*].shortName").value(hasItem(DEFAULT_SHORT_NAME.toString())));
    }
    
    @Test
    @Transactional
    public void getMTimeattackQuestStage() throws Exception {
        // Initialize the database
        mTimeattackQuestStageRepository.saveAndFlush(mTimeattackQuestStage);

        // Get the mTimeattackQuestStage
        restMTimeattackQuestStageMockMvc.perform(get("/api/m-timeattack-quest-stages/{id}", mTimeattackQuestStage.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(mTimeattackQuestStage.getId().intValue()))
            .andExpect(jsonPath("$.worldId").value(DEFAULT_WORLD_ID))
            .andExpect(jsonPath("$.number").value(DEFAULT_NUMBER))
            .andExpect(jsonPath("$.startAt").value(DEFAULT_START_AT))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME.toString()))
            .andExpect(jsonPath("$.kickoffDescription").value(DEFAULT_KICKOFF_DESCRIPTION.toString()))
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
            .andExpect(jsonPath("$.ticketId").value(DEFAULT_TICKET_ID))
            .andExpect(jsonPath("$.ticketAmount").value(DEFAULT_TICKET_AMOUNT))
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
            .andExpect(jsonPath("$.shortName").value(DEFAULT_SHORT_NAME.toString()));
    }

    @Test
    @Transactional
    public void getAllMTimeattackQuestStagesByWorldIdIsEqualToSomething() throws Exception {
        // Initialize the database
        mTimeattackQuestStageRepository.saveAndFlush(mTimeattackQuestStage);

        // Get all the mTimeattackQuestStageList where worldId equals to DEFAULT_WORLD_ID
        defaultMTimeattackQuestStageShouldBeFound("worldId.equals=" + DEFAULT_WORLD_ID);

        // Get all the mTimeattackQuestStageList where worldId equals to UPDATED_WORLD_ID
        defaultMTimeattackQuestStageShouldNotBeFound("worldId.equals=" + UPDATED_WORLD_ID);
    }

    @Test
    @Transactional
    public void getAllMTimeattackQuestStagesByWorldIdIsInShouldWork() throws Exception {
        // Initialize the database
        mTimeattackQuestStageRepository.saveAndFlush(mTimeattackQuestStage);

        // Get all the mTimeattackQuestStageList where worldId in DEFAULT_WORLD_ID or UPDATED_WORLD_ID
        defaultMTimeattackQuestStageShouldBeFound("worldId.in=" + DEFAULT_WORLD_ID + "," + UPDATED_WORLD_ID);

        // Get all the mTimeattackQuestStageList where worldId equals to UPDATED_WORLD_ID
        defaultMTimeattackQuestStageShouldNotBeFound("worldId.in=" + UPDATED_WORLD_ID);
    }

    @Test
    @Transactional
    public void getAllMTimeattackQuestStagesByWorldIdIsNullOrNotNull() throws Exception {
        // Initialize the database
        mTimeattackQuestStageRepository.saveAndFlush(mTimeattackQuestStage);

        // Get all the mTimeattackQuestStageList where worldId is not null
        defaultMTimeattackQuestStageShouldBeFound("worldId.specified=true");

        // Get all the mTimeattackQuestStageList where worldId is null
        defaultMTimeattackQuestStageShouldNotBeFound("worldId.specified=false");
    }

    @Test
    @Transactional
    public void getAllMTimeattackQuestStagesByWorldIdIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mTimeattackQuestStageRepository.saveAndFlush(mTimeattackQuestStage);

        // Get all the mTimeattackQuestStageList where worldId greater than or equals to DEFAULT_WORLD_ID
        defaultMTimeattackQuestStageShouldBeFound("worldId.greaterOrEqualThan=" + DEFAULT_WORLD_ID);

        // Get all the mTimeattackQuestStageList where worldId greater than or equals to UPDATED_WORLD_ID
        defaultMTimeattackQuestStageShouldNotBeFound("worldId.greaterOrEqualThan=" + UPDATED_WORLD_ID);
    }

    @Test
    @Transactional
    public void getAllMTimeattackQuestStagesByWorldIdIsLessThanSomething() throws Exception {
        // Initialize the database
        mTimeattackQuestStageRepository.saveAndFlush(mTimeattackQuestStage);

        // Get all the mTimeattackQuestStageList where worldId less than or equals to DEFAULT_WORLD_ID
        defaultMTimeattackQuestStageShouldNotBeFound("worldId.lessThan=" + DEFAULT_WORLD_ID);

        // Get all the mTimeattackQuestStageList where worldId less than or equals to UPDATED_WORLD_ID
        defaultMTimeattackQuestStageShouldBeFound("worldId.lessThan=" + UPDATED_WORLD_ID);
    }


    @Test
    @Transactional
    public void getAllMTimeattackQuestStagesByNumberIsEqualToSomething() throws Exception {
        // Initialize the database
        mTimeattackQuestStageRepository.saveAndFlush(mTimeattackQuestStage);

        // Get all the mTimeattackQuestStageList where number equals to DEFAULT_NUMBER
        defaultMTimeattackQuestStageShouldBeFound("number.equals=" + DEFAULT_NUMBER);

        // Get all the mTimeattackQuestStageList where number equals to UPDATED_NUMBER
        defaultMTimeattackQuestStageShouldNotBeFound("number.equals=" + UPDATED_NUMBER);
    }

    @Test
    @Transactional
    public void getAllMTimeattackQuestStagesByNumberIsInShouldWork() throws Exception {
        // Initialize the database
        mTimeattackQuestStageRepository.saveAndFlush(mTimeattackQuestStage);

        // Get all the mTimeattackQuestStageList where number in DEFAULT_NUMBER or UPDATED_NUMBER
        defaultMTimeattackQuestStageShouldBeFound("number.in=" + DEFAULT_NUMBER + "," + UPDATED_NUMBER);

        // Get all the mTimeattackQuestStageList where number equals to UPDATED_NUMBER
        defaultMTimeattackQuestStageShouldNotBeFound("number.in=" + UPDATED_NUMBER);
    }

    @Test
    @Transactional
    public void getAllMTimeattackQuestStagesByNumberIsNullOrNotNull() throws Exception {
        // Initialize the database
        mTimeattackQuestStageRepository.saveAndFlush(mTimeattackQuestStage);

        // Get all the mTimeattackQuestStageList where number is not null
        defaultMTimeattackQuestStageShouldBeFound("number.specified=true");

        // Get all the mTimeattackQuestStageList where number is null
        defaultMTimeattackQuestStageShouldNotBeFound("number.specified=false");
    }

    @Test
    @Transactional
    public void getAllMTimeattackQuestStagesByNumberIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mTimeattackQuestStageRepository.saveAndFlush(mTimeattackQuestStage);

        // Get all the mTimeattackQuestStageList where number greater than or equals to DEFAULT_NUMBER
        defaultMTimeattackQuestStageShouldBeFound("number.greaterOrEqualThan=" + DEFAULT_NUMBER);

        // Get all the mTimeattackQuestStageList where number greater than or equals to UPDATED_NUMBER
        defaultMTimeattackQuestStageShouldNotBeFound("number.greaterOrEqualThan=" + UPDATED_NUMBER);
    }

    @Test
    @Transactional
    public void getAllMTimeattackQuestStagesByNumberIsLessThanSomething() throws Exception {
        // Initialize the database
        mTimeattackQuestStageRepository.saveAndFlush(mTimeattackQuestStage);

        // Get all the mTimeattackQuestStageList where number less than or equals to DEFAULT_NUMBER
        defaultMTimeattackQuestStageShouldNotBeFound("number.lessThan=" + DEFAULT_NUMBER);

        // Get all the mTimeattackQuestStageList where number less than or equals to UPDATED_NUMBER
        defaultMTimeattackQuestStageShouldBeFound("number.lessThan=" + UPDATED_NUMBER);
    }


    @Test
    @Transactional
    public void getAllMTimeattackQuestStagesByStartAtIsEqualToSomething() throws Exception {
        // Initialize the database
        mTimeattackQuestStageRepository.saveAndFlush(mTimeattackQuestStage);

        // Get all the mTimeattackQuestStageList where startAt equals to DEFAULT_START_AT
        defaultMTimeattackQuestStageShouldBeFound("startAt.equals=" + DEFAULT_START_AT);

        // Get all the mTimeattackQuestStageList where startAt equals to UPDATED_START_AT
        defaultMTimeattackQuestStageShouldNotBeFound("startAt.equals=" + UPDATED_START_AT);
    }

    @Test
    @Transactional
    public void getAllMTimeattackQuestStagesByStartAtIsInShouldWork() throws Exception {
        // Initialize the database
        mTimeattackQuestStageRepository.saveAndFlush(mTimeattackQuestStage);

        // Get all the mTimeattackQuestStageList where startAt in DEFAULT_START_AT or UPDATED_START_AT
        defaultMTimeattackQuestStageShouldBeFound("startAt.in=" + DEFAULT_START_AT + "," + UPDATED_START_AT);

        // Get all the mTimeattackQuestStageList where startAt equals to UPDATED_START_AT
        defaultMTimeattackQuestStageShouldNotBeFound("startAt.in=" + UPDATED_START_AT);
    }

    @Test
    @Transactional
    public void getAllMTimeattackQuestStagesByStartAtIsNullOrNotNull() throws Exception {
        // Initialize the database
        mTimeattackQuestStageRepository.saveAndFlush(mTimeattackQuestStage);

        // Get all the mTimeattackQuestStageList where startAt is not null
        defaultMTimeattackQuestStageShouldBeFound("startAt.specified=true");

        // Get all the mTimeattackQuestStageList where startAt is null
        defaultMTimeattackQuestStageShouldNotBeFound("startAt.specified=false");
    }

    @Test
    @Transactional
    public void getAllMTimeattackQuestStagesByStartAtIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mTimeattackQuestStageRepository.saveAndFlush(mTimeattackQuestStage);

        // Get all the mTimeattackQuestStageList where startAt greater than or equals to DEFAULT_START_AT
        defaultMTimeattackQuestStageShouldBeFound("startAt.greaterOrEqualThan=" + DEFAULT_START_AT);

        // Get all the mTimeattackQuestStageList where startAt greater than or equals to UPDATED_START_AT
        defaultMTimeattackQuestStageShouldNotBeFound("startAt.greaterOrEqualThan=" + UPDATED_START_AT);
    }

    @Test
    @Transactional
    public void getAllMTimeattackQuestStagesByStartAtIsLessThanSomething() throws Exception {
        // Initialize the database
        mTimeattackQuestStageRepository.saveAndFlush(mTimeattackQuestStage);

        // Get all the mTimeattackQuestStageList where startAt less than or equals to DEFAULT_START_AT
        defaultMTimeattackQuestStageShouldNotBeFound("startAt.lessThan=" + DEFAULT_START_AT);

        // Get all the mTimeattackQuestStageList where startAt less than or equals to UPDATED_START_AT
        defaultMTimeattackQuestStageShouldBeFound("startAt.lessThan=" + UPDATED_START_AT);
    }


    @Test
    @Transactional
    public void getAllMTimeattackQuestStagesByDifficultyIsEqualToSomething() throws Exception {
        // Initialize the database
        mTimeattackQuestStageRepository.saveAndFlush(mTimeattackQuestStage);

        // Get all the mTimeattackQuestStageList where difficulty equals to DEFAULT_DIFFICULTY
        defaultMTimeattackQuestStageShouldBeFound("difficulty.equals=" + DEFAULT_DIFFICULTY);

        // Get all the mTimeattackQuestStageList where difficulty equals to UPDATED_DIFFICULTY
        defaultMTimeattackQuestStageShouldNotBeFound("difficulty.equals=" + UPDATED_DIFFICULTY);
    }

    @Test
    @Transactional
    public void getAllMTimeattackQuestStagesByDifficultyIsInShouldWork() throws Exception {
        // Initialize the database
        mTimeattackQuestStageRepository.saveAndFlush(mTimeattackQuestStage);

        // Get all the mTimeattackQuestStageList where difficulty in DEFAULT_DIFFICULTY or UPDATED_DIFFICULTY
        defaultMTimeattackQuestStageShouldBeFound("difficulty.in=" + DEFAULT_DIFFICULTY + "," + UPDATED_DIFFICULTY);

        // Get all the mTimeattackQuestStageList where difficulty equals to UPDATED_DIFFICULTY
        defaultMTimeattackQuestStageShouldNotBeFound("difficulty.in=" + UPDATED_DIFFICULTY);
    }

    @Test
    @Transactional
    public void getAllMTimeattackQuestStagesByDifficultyIsNullOrNotNull() throws Exception {
        // Initialize the database
        mTimeattackQuestStageRepository.saveAndFlush(mTimeattackQuestStage);

        // Get all the mTimeattackQuestStageList where difficulty is not null
        defaultMTimeattackQuestStageShouldBeFound("difficulty.specified=true");

        // Get all the mTimeattackQuestStageList where difficulty is null
        defaultMTimeattackQuestStageShouldNotBeFound("difficulty.specified=false");
    }

    @Test
    @Transactional
    public void getAllMTimeattackQuestStagesByDifficultyIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mTimeattackQuestStageRepository.saveAndFlush(mTimeattackQuestStage);

        // Get all the mTimeattackQuestStageList where difficulty greater than or equals to DEFAULT_DIFFICULTY
        defaultMTimeattackQuestStageShouldBeFound("difficulty.greaterOrEqualThan=" + DEFAULT_DIFFICULTY);

        // Get all the mTimeattackQuestStageList where difficulty greater than or equals to UPDATED_DIFFICULTY
        defaultMTimeattackQuestStageShouldNotBeFound("difficulty.greaterOrEqualThan=" + UPDATED_DIFFICULTY);
    }

    @Test
    @Transactional
    public void getAllMTimeattackQuestStagesByDifficultyIsLessThanSomething() throws Exception {
        // Initialize the database
        mTimeattackQuestStageRepository.saveAndFlush(mTimeattackQuestStage);

        // Get all the mTimeattackQuestStageList where difficulty less than or equals to DEFAULT_DIFFICULTY
        defaultMTimeattackQuestStageShouldNotBeFound("difficulty.lessThan=" + DEFAULT_DIFFICULTY);

        // Get all the mTimeattackQuestStageList where difficulty less than or equals to UPDATED_DIFFICULTY
        defaultMTimeattackQuestStageShouldBeFound("difficulty.lessThan=" + UPDATED_DIFFICULTY);
    }


    @Test
    @Transactional
    public void getAllMTimeattackQuestStagesByStageUnlockPatternIsEqualToSomething() throws Exception {
        // Initialize the database
        mTimeattackQuestStageRepository.saveAndFlush(mTimeattackQuestStage);

        // Get all the mTimeattackQuestStageList where stageUnlockPattern equals to DEFAULT_STAGE_UNLOCK_PATTERN
        defaultMTimeattackQuestStageShouldBeFound("stageUnlockPattern.equals=" + DEFAULT_STAGE_UNLOCK_PATTERN);

        // Get all the mTimeattackQuestStageList where stageUnlockPattern equals to UPDATED_STAGE_UNLOCK_PATTERN
        defaultMTimeattackQuestStageShouldNotBeFound("stageUnlockPattern.equals=" + UPDATED_STAGE_UNLOCK_PATTERN);
    }

    @Test
    @Transactional
    public void getAllMTimeattackQuestStagesByStageUnlockPatternIsInShouldWork() throws Exception {
        // Initialize the database
        mTimeattackQuestStageRepository.saveAndFlush(mTimeattackQuestStage);

        // Get all the mTimeattackQuestStageList where stageUnlockPattern in DEFAULT_STAGE_UNLOCK_PATTERN or UPDATED_STAGE_UNLOCK_PATTERN
        defaultMTimeattackQuestStageShouldBeFound("stageUnlockPattern.in=" + DEFAULT_STAGE_UNLOCK_PATTERN + "," + UPDATED_STAGE_UNLOCK_PATTERN);

        // Get all the mTimeattackQuestStageList where stageUnlockPattern equals to UPDATED_STAGE_UNLOCK_PATTERN
        defaultMTimeattackQuestStageShouldNotBeFound("stageUnlockPattern.in=" + UPDATED_STAGE_UNLOCK_PATTERN);
    }

    @Test
    @Transactional
    public void getAllMTimeattackQuestStagesByStageUnlockPatternIsNullOrNotNull() throws Exception {
        // Initialize the database
        mTimeattackQuestStageRepository.saveAndFlush(mTimeattackQuestStage);

        // Get all the mTimeattackQuestStageList where stageUnlockPattern is not null
        defaultMTimeattackQuestStageShouldBeFound("stageUnlockPattern.specified=true");

        // Get all the mTimeattackQuestStageList where stageUnlockPattern is null
        defaultMTimeattackQuestStageShouldNotBeFound("stageUnlockPattern.specified=false");
    }

    @Test
    @Transactional
    public void getAllMTimeattackQuestStagesByStageUnlockPatternIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mTimeattackQuestStageRepository.saveAndFlush(mTimeattackQuestStage);

        // Get all the mTimeattackQuestStageList where stageUnlockPattern greater than or equals to DEFAULT_STAGE_UNLOCK_PATTERN
        defaultMTimeattackQuestStageShouldBeFound("stageUnlockPattern.greaterOrEqualThan=" + DEFAULT_STAGE_UNLOCK_PATTERN);

        // Get all the mTimeattackQuestStageList where stageUnlockPattern greater than or equals to UPDATED_STAGE_UNLOCK_PATTERN
        defaultMTimeattackQuestStageShouldNotBeFound("stageUnlockPattern.greaterOrEqualThan=" + UPDATED_STAGE_UNLOCK_PATTERN);
    }

    @Test
    @Transactional
    public void getAllMTimeattackQuestStagesByStageUnlockPatternIsLessThanSomething() throws Exception {
        // Initialize the database
        mTimeattackQuestStageRepository.saveAndFlush(mTimeattackQuestStage);

        // Get all the mTimeattackQuestStageList where stageUnlockPattern less than or equals to DEFAULT_STAGE_UNLOCK_PATTERN
        defaultMTimeattackQuestStageShouldNotBeFound("stageUnlockPattern.lessThan=" + DEFAULT_STAGE_UNLOCK_PATTERN);

        // Get all the mTimeattackQuestStageList where stageUnlockPattern less than or equals to UPDATED_STAGE_UNLOCK_PATTERN
        defaultMTimeattackQuestStageShouldBeFound("stageUnlockPattern.lessThan=" + UPDATED_STAGE_UNLOCK_PATTERN);
    }


    @Test
    @Transactional
    public void getAllMTimeattackQuestStagesByStoryOnlyIsEqualToSomething() throws Exception {
        // Initialize the database
        mTimeattackQuestStageRepository.saveAndFlush(mTimeattackQuestStage);

        // Get all the mTimeattackQuestStageList where storyOnly equals to DEFAULT_STORY_ONLY
        defaultMTimeattackQuestStageShouldBeFound("storyOnly.equals=" + DEFAULT_STORY_ONLY);

        // Get all the mTimeattackQuestStageList where storyOnly equals to UPDATED_STORY_ONLY
        defaultMTimeattackQuestStageShouldNotBeFound("storyOnly.equals=" + UPDATED_STORY_ONLY);
    }

    @Test
    @Transactional
    public void getAllMTimeattackQuestStagesByStoryOnlyIsInShouldWork() throws Exception {
        // Initialize the database
        mTimeattackQuestStageRepository.saveAndFlush(mTimeattackQuestStage);

        // Get all the mTimeattackQuestStageList where storyOnly in DEFAULT_STORY_ONLY or UPDATED_STORY_ONLY
        defaultMTimeattackQuestStageShouldBeFound("storyOnly.in=" + DEFAULT_STORY_ONLY + "," + UPDATED_STORY_ONLY);

        // Get all the mTimeattackQuestStageList where storyOnly equals to UPDATED_STORY_ONLY
        defaultMTimeattackQuestStageShouldNotBeFound("storyOnly.in=" + UPDATED_STORY_ONLY);
    }

    @Test
    @Transactional
    public void getAllMTimeattackQuestStagesByStoryOnlyIsNullOrNotNull() throws Exception {
        // Initialize the database
        mTimeattackQuestStageRepository.saveAndFlush(mTimeattackQuestStage);

        // Get all the mTimeattackQuestStageList where storyOnly is not null
        defaultMTimeattackQuestStageShouldBeFound("storyOnly.specified=true");

        // Get all the mTimeattackQuestStageList where storyOnly is null
        defaultMTimeattackQuestStageShouldNotBeFound("storyOnly.specified=false");
    }

    @Test
    @Transactional
    public void getAllMTimeattackQuestStagesByStoryOnlyIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mTimeattackQuestStageRepository.saveAndFlush(mTimeattackQuestStage);

        // Get all the mTimeattackQuestStageList where storyOnly greater than or equals to DEFAULT_STORY_ONLY
        defaultMTimeattackQuestStageShouldBeFound("storyOnly.greaterOrEqualThan=" + DEFAULT_STORY_ONLY);

        // Get all the mTimeattackQuestStageList where storyOnly greater than or equals to UPDATED_STORY_ONLY
        defaultMTimeattackQuestStageShouldNotBeFound("storyOnly.greaterOrEqualThan=" + UPDATED_STORY_ONLY);
    }

    @Test
    @Transactional
    public void getAllMTimeattackQuestStagesByStoryOnlyIsLessThanSomething() throws Exception {
        // Initialize the database
        mTimeattackQuestStageRepository.saveAndFlush(mTimeattackQuestStage);

        // Get all the mTimeattackQuestStageList where storyOnly less than or equals to DEFAULT_STORY_ONLY
        defaultMTimeattackQuestStageShouldNotBeFound("storyOnly.lessThan=" + DEFAULT_STORY_ONLY);

        // Get all the mTimeattackQuestStageList where storyOnly less than or equals to UPDATED_STORY_ONLY
        defaultMTimeattackQuestStageShouldBeFound("storyOnly.lessThan=" + UPDATED_STORY_ONLY);
    }


    @Test
    @Transactional
    public void getAllMTimeattackQuestStagesByFirstHalfNpcDeckIdIsEqualToSomething() throws Exception {
        // Initialize the database
        mTimeattackQuestStageRepository.saveAndFlush(mTimeattackQuestStage);

        // Get all the mTimeattackQuestStageList where firstHalfNpcDeckId equals to DEFAULT_FIRST_HALF_NPC_DECK_ID
        defaultMTimeattackQuestStageShouldBeFound("firstHalfNpcDeckId.equals=" + DEFAULT_FIRST_HALF_NPC_DECK_ID);

        // Get all the mTimeattackQuestStageList where firstHalfNpcDeckId equals to UPDATED_FIRST_HALF_NPC_DECK_ID
        defaultMTimeattackQuestStageShouldNotBeFound("firstHalfNpcDeckId.equals=" + UPDATED_FIRST_HALF_NPC_DECK_ID);
    }

    @Test
    @Transactional
    public void getAllMTimeattackQuestStagesByFirstHalfNpcDeckIdIsInShouldWork() throws Exception {
        // Initialize the database
        mTimeattackQuestStageRepository.saveAndFlush(mTimeattackQuestStage);

        // Get all the mTimeattackQuestStageList where firstHalfNpcDeckId in DEFAULT_FIRST_HALF_NPC_DECK_ID or UPDATED_FIRST_HALF_NPC_DECK_ID
        defaultMTimeattackQuestStageShouldBeFound("firstHalfNpcDeckId.in=" + DEFAULT_FIRST_HALF_NPC_DECK_ID + "," + UPDATED_FIRST_HALF_NPC_DECK_ID);

        // Get all the mTimeattackQuestStageList where firstHalfNpcDeckId equals to UPDATED_FIRST_HALF_NPC_DECK_ID
        defaultMTimeattackQuestStageShouldNotBeFound("firstHalfNpcDeckId.in=" + UPDATED_FIRST_HALF_NPC_DECK_ID);
    }

    @Test
    @Transactional
    public void getAllMTimeattackQuestStagesByFirstHalfNpcDeckIdIsNullOrNotNull() throws Exception {
        // Initialize the database
        mTimeattackQuestStageRepository.saveAndFlush(mTimeattackQuestStage);

        // Get all the mTimeattackQuestStageList where firstHalfNpcDeckId is not null
        defaultMTimeattackQuestStageShouldBeFound("firstHalfNpcDeckId.specified=true");

        // Get all the mTimeattackQuestStageList where firstHalfNpcDeckId is null
        defaultMTimeattackQuestStageShouldNotBeFound("firstHalfNpcDeckId.specified=false");
    }

    @Test
    @Transactional
    public void getAllMTimeattackQuestStagesByFirstHalfNpcDeckIdIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mTimeattackQuestStageRepository.saveAndFlush(mTimeattackQuestStage);

        // Get all the mTimeattackQuestStageList where firstHalfNpcDeckId greater than or equals to DEFAULT_FIRST_HALF_NPC_DECK_ID
        defaultMTimeattackQuestStageShouldBeFound("firstHalfNpcDeckId.greaterOrEqualThan=" + DEFAULT_FIRST_HALF_NPC_DECK_ID);

        // Get all the mTimeattackQuestStageList where firstHalfNpcDeckId greater than or equals to UPDATED_FIRST_HALF_NPC_DECK_ID
        defaultMTimeattackQuestStageShouldNotBeFound("firstHalfNpcDeckId.greaterOrEqualThan=" + UPDATED_FIRST_HALF_NPC_DECK_ID);
    }

    @Test
    @Transactional
    public void getAllMTimeattackQuestStagesByFirstHalfNpcDeckIdIsLessThanSomething() throws Exception {
        // Initialize the database
        mTimeattackQuestStageRepository.saveAndFlush(mTimeattackQuestStage);

        // Get all the mTimeattackQuestStageList where firstHalfNpcDeckId less than or equals to DEFAULT_FIRST_HALF_NPC_DECK_ID
        defaultMTimeattackQuestStageShouldNotBeFound("firstHalfNpcDeckId.lessThan=" + DEFAULT_FIRST_HALF_NPC_DECK_ID);

        // Get all the mTimeattackQuestStageList where firstHalfNpcDeckId less than or equals to UPDATED_FIRST_HALF_NPC_DECK_ID
        defaultMTimeattackQuestStageShouldBeFound("firstHalfNpcDeckId.lessThan=" + UPDATED_FIRST_HALF_NPC_DECK_ID);
    }


    @Test
    @Transactional
    public void getAllMTimeattackQuestStagesByFirstHalfEnvironmentIdIsEqualToSomething() throws Exception {
        // Initialize the database
        mTimeattackQuestStageRepository.saveAndFlush(mTimeattackQuestStage);

        // Get all the mTimeattackQuestStageList where firstHalfEnvironmentId equals to DEFAULT_FIRST_HALF_ENVIRONMENT_ID
        defaultMTimeattackQuestStageShouldBeFound("firstHalfEnvironmentId.equals=" + DEFAULT_FIRST_HALF_ENVIRONMENT_ID);

        // Get all the mTimeattackQuestStageList where firstHalfEnvironmentId equals to UPDATED_FIRST_HALF_ENVIRONMENT_ID
        defaultMTimeattackQuestStageShouldNotBeFound("firstHalfEnvironmentId.equals=" + UPDATED_FIRST_HALF_ENVIRONMENT_ID);
    }

    @Test
    @Transactional
    public void getAllMTimeattackQuestStagesByFirstHalfEnvironmentIdIsInShouldWork() throws Exception {
        // Initialize the database
        mTimeattackQuestStageRepository.saveAndFlush(mTimeattackQuestStage);

        // Get all the mTimeattackQuestStageList where firstHalfEnvironmentId in DEFAULT_FIRST_HALF_ENVIRONMENT_ID or UPDATED_FIRST_HALF_ENVIRONMENT_ID
        defaultMTimeattackQuestStageShouldBeFound("firstHalfEnvironmentId.in=" + DEFAULT_FIRST_HALF_ENVIRONMENT_ID + "," + UPDATED_FIRST_HALF_ENVIRONMENT_ID);

        // Get all the mTimeattackQuestStageList where firstHalfEnvironmentId equals to UPDATED_FIRST_HALF_ENVIRONMENT_ID
        defaultMTimeattackQuestStageShouldNotBeFound("firstHalfEnvironmentId.in=" + UPDATED_FIRST_HALF_ENVIRONMENT_ID);
    }

    @Test
    @Transactional
    public void getAllMTimeattackQuestStagesByFirstHalfEnvironmentIdIsNullOrNotNull() throws Exception {
        // Initialize the database
        mTimeattackQuestStageRepository.saveAndFlush(mTimeattackQuestStage);

        // Get all the mTimeattackQuestStageList where firstHalfEnvironmentId is not null
        defaultMTimeattackQuestStageShouldBeFound("firstHalfEnvironmentId.specified=true");

        // Get all the mTimeattackQuestStageList where firstHalfEnvironmentId is null
        defaultMTimeattackQuestStageShouldNotBeFound("firstHalfEnvironmentId.specified=false");
    }

    @Test
    @Transactional
    public void getAllMTimeattackQuestStagesByFirstHalfEnvironmentIdIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mTimeattackQuestStageRepository.saveAndFlush(mTimeattackQuestStage);

        // Get all the mTimeattackQuestStageList where firstHalfEnvironmentId greater than or equals to DEFAULT_FIRST_HALF_ENVIRONMENT_ID
        defaultMTimeattackQuestStageShouldBeFound("firstHalfEnvironmentId.greaterOrEqualThan=" + DEFAULT_FIRST_HALF_ENVIRONMENT_ID);

        // Get all the mTimeattackQuestStageList where firstHalfEnvironmentId greater than or equals to UPDATED_FIRST_HALF_ENVIRONMENT_ID
        defaultMTimeattackQuestStageShouldNotBeFound("firstHalfEnvironmentId.greaterOrEqualThan=" + UPDATED_FIRST_HALF_ENVIRONMENT_ID);
    }

    @Test
    @Transactional
    public void getAllMTimeattackQuestStagesByFirstHalfEnvironmentIdIsLessThanSomething() throws Exception {
        // Initialize the database
        mTimeattackQuestStageRepository.saveAndFlush(mTimeattackQuestStage);

        // Get all the mTimeattackQuestStageList where firstHalfEnvironmentId less than or equals to DEFAULT_FIRST_HALF_ENVIRONMENT_ID
        defaultMTimeattackQuestStageShouldNotBeFound("firstHalfEnvironmentId.lessThan=" + DEFAULT_FIRST_HALF_ENVIRONMENT_ID);

        // Get all the mTimeattackQuestStageList where firstHalfEnvironmentId less than or equals to UPDATED_FIRST_HALF_ENVIRONMENT_ID
        defaultMTimeattackQuestStageShouldBeFound("firstHalfEnvironmentId.lessThan=" + UPDATED_FIRST_HALF_ENVIRONMENT_ID);
    }


    @Test
    @Transactional
    public void getAllMTimeattackQuestStagesBySecondHalfNpcDeckIdIsEqualToSomething() throws Exception {
        // Initialize the database
        mTimeattackQuestStageRepository.saveAndFlush(mTimeattackQuestStage);

        // Get all the mTimeattackQuestStageList where secondHalfNpcDeckId equals to DEFAULT_SECOND_HALF_NPC_DECK_ID
        defaultMTimeattackQuestStageShouldBeFound("secondHalfNpcDeckId.equals=" + DEFAULT_SECOND_HALF_NPC_DECK_ID);

        // Get all the mTimeattackQuestStageList where secondHalfNpcDeckId equals to UPDATED_SECOND_HALF_NPC_DECK_ID
        defaultMTimeattackQuestStageShouldNotBeFound("secondHalfNpcDeckId.equals=" + UPDATED_SECOND_HALF_NPC_DECK_ID);
    }

    @Test
    @Transactional
    public void getAllMTimeattackQuestStagesBySecondHalfNpcDeckIdIsInShouldWork() throws Exception {
        // Initialize the database
        mTimeattackQuestStageRepository.saveAndFlush(mTimeattackQuestStage);

        // Get all the mTimeattackQuestStageList where secondHalfNpcDeckId in DEFAULT_SECOND_HALF_NPC_DECK_ID or UPDATED_SECOND_HALF_NPC_DECK_ID
        defaultMTimeattackQuestStageShouldBeFound("secondHalfNpcDeckId.in=" + DEFAULT_SECOND_HALF_NPC_DECK_ID + "," + UPDATED_SECOND_HALF_NPC_DECK_ID);

        // Get all the mTimeattackQuestStageList where secondHalfNpcDeckId equals to UPDATED_SECOND_HALF_NPC_DECK_ID
        defaultMTimeattackQuestStageShouldNotBeFound("secondHalfNpcDeckId.in=" + UPDATED_SECOND_HALF_NPC_DECK_ID);
    }

    @Test
    @Transactional
    public void getAllMTimeattackQuestStagesBySecondHalfNpcDeckIdIsNullOrNotNull() throws Exception {
        // Initialize the database
        mTimeattackQuestStageRepository.saveAndFlush(mTimeattackQuestStage);

        // Get all the mTimeattackQuestStageList where secondHalfNpcDeckId is not null
        defaultMTimeattackQuestStageShouldBeFound("secondHalfNpcDeckId.specified=true");

        // Get all the mTimeattackQuestStageList where secondHalfNpcDeckId is null
        defaultMTimeattackQuestStageShouldNotBeFound("secondHalfNpcDeckId.specified=false");
    }

    @Test
    @Transactional
    public void getAllMTimeattackQuestStagesBySecondHalfNpcDeckIdIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mTimeattackQuestStageRepository.saveAndFlush(mTimeattackQuestStage);

        // Get all the mTimeattackQuestStageList where secondHalfNpcDeckId greater than or equals to DEFAULT_SECOND_HALF_NPC_DECK_ID
        defaultMTimeattackQuestStageShouldBeFound("secondHalfNpcDeckId.greaterOrEqualThan=" + DEFAULT_SECOND_HALF_NPC_DECK_ID);

        // Get all the mTimeattackQuestStageList where secondHalfNpcDeckId greater than or equals to UPDATED_SECOND_HALF_NPC_DECK_ID
        defaultMTimeattackQuestStageShouldNotBeFound("secondHalfNpcDeckId.greaterOrEqualThan=" + UPDATED_SECOND_HALF_NPC_DECK_ID);
    }

    @Test
    @Transactional
    public void getAllMTimeattackQuestStagesBySecondHalfNpcDeckIdIsLessThanSomething() throws Exception {
        // Initialize the database
        mTimeattackQuestStageRepository.saveAndFlush(mTimeattackQuestStage);

        // Get all the mTimeattackQuestStageList where secondHalfNpcDeckId less than or equals to DEFAULT_SECOND_HALF_NPC_DECK_ID
        defaultMTimeattackQuestStageShouldNotBeFound("secondHalfNpcDeckId.lessThan=" + DEFAULT_SECOND_HALF_NPC_DECK_ID);

        // Get all the mTimeattackQuestStageList where secondHalfNpcDeckId less than or equals to UPDATED_SECOND_HALF_NPC_DECK_ID
        defaultMTimeattackQuestStageShouldBeFound("secondHalfNpcDeckId.lessThan=" + UPDATED_SECOND_HALF_NPC_DECK_ID);
    }


    @Test
    @Transactional
    public void getAllMTimeattackQuestStagesBySecondHalfEnvironmentIdIsEqualToSomething() throws Exception {
        // Initialize the database
        mTimeattackQuestStageRepository.saveAndFlush(mTimeattackQuestStage);

        // Get all the mTimeattackQuestStageList where secondHalfEnvironmentId equals to DEFAULT_SECOND_HALF_ENVIRONMENT_ID
        defaultMTimeattackQuestStageShouldBeFound("secondHalfEnvironmentId.equals=" + DEFAULT_SECOND_HALF_ENVIRONMENT_ID);

        // Get all the mTimeattackQuestStageList where secondHalfEnvironmentId equals to UPDATED_SECOND_HALF_ENVIRONMENT_ID
        defaultMTimeattackQuestStageShouldNotBeFound("secondHalfEnvironmentId.equals=" + UPDATED_SECOND_HALF_ENVIRONMENT_ID);
    }

    @Test
    @Transactional
    public void getAllMTimeattackQuestStagesBySecondHalfEnvironmentIdIsInShouldWork() throws Exception {
        // Initialize the database
        mTimeattackQuestStageRepository.saveAndFlush(mTimeattackQuestStage);

        // Get all the mTimeattackQuestStageList where secondHalfEnvironmentId in DEFAULT_SECOND_HALF_ENVIRONMENT_ID or UPDATED_SECOND_HALF_ENVIRONMENT_ID
        defaultMTimeattackQuestStageShouldBeFound("secondHalfEnvironmentId.in=" + DEFAULT_SECOND_HALF_ENVIRONMENT_ID + "," + UPDATED_SECOND_HALF_ENVIRONMENT_ID);

        // Get all the mTimeattackQuestStageList where secondHalfEnvironmentId equals to UPDATED_SECOND_HALF_ENVIRONMENT_ID
        defaultMTimeattackQuestStageShouldNotBeFound("secondHalfEnvironmentId.in=" + UPDATED_SECOND_HALF_ENVIRONMENT_ID);
    }

    @Test
    @Transactional
    public void getAllMTimeattackQuestStagesBySecondHalfEnvironmentIdIsNullOrNotNull() throws Exception {
        // Initialize the database
        mTimeattackQuestStageRepository.saveAndFlush(mTimeattackQuestStage);

        // Get all the mTimeattackQuestStageList where secondHalfEnvironmentId is not null
        defaultMTimeattackQuestStageShouldBeFound("secondHalfEnvironmentId.specified=true");

        // Get all the mTimeattackQuestStageList where secondHalfEnvironmentId is null
        defaultMTimeattackQuestStageShouldNotBeFound("secondHalfEnvironmentId.specified=false");
    }

    @Test
    @Transactional
    public void getAllMTimeattackQuestStagesBySecondHalfEnvironmentIdIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mTimeattackQuestStageRepository.saveAndFlush(mTimeattackQuestStage);

        // Get all the mTimeattackQuestStageList where secondHalfEnvironmentId greater than or equals to DEFAULT_SECOND_HALF_ENVIRONMENT_ID
        defaultMTimeattackQuestStageShouldBeFound("secondHalfEnvironmentId.greaterOrEqualThan=" + DEFAULT_SECOND_HALF_ENVIRONMENT_ID);

        // Get all the mTimeattackQuestStageList where secondHalfEnvironmentId greater than or equals to UPDATED_SECOND_HALF_ENVIRONMENT_ID
        defaultMTimeattackQuestStageShouldNotBeFound("secondHalfEnvironmentId.greaterOrEqualThan=" + UPDATED_SECOND_HALF_ENVIRONMENT_ID);
    }

    @Test
    @Transactional
    public void getAllMTimeattackQuestStagesBySecondHalfEnvironmentIdIsLessThanSomething() throws Exception {
        // Initialize the database
        mTimeattackQuestStageRepository.saveAndFlush(mTimeattackQuestStage);

        // Get all the mTimeattackQuestStageList where secondHalfEnvironmentId less than or equals to DEFAULT_SECOND_HALF_ENVIRONMENT_ID
        defaultMTimeattackQuestStageShouldNotBeFound("secondHalfEnvironmentId.lessThan=" + DEFAULT_SECOND_HALF_ENVIRONMENT_ID);

        // Get all the mTimeattackQuestStageList where secondHalfEnvironmentId less than or equals to UPDATED_SECOND_HALF_ENVIRONMENT_ID
        defaultMTimeattackQuestStageShouldBeFound("secondHalfEnvironmentId.lessThan=" + UPDATED_SECOND_HALF_ENVIRONMENT_ID);
    }


    @Test
    @Transactional
    public void getAllMTimeattackQuestStagesByExtraFirstHalfNpcDeckIdIsEqualToSomething() throws Exception {
        // Initialize the database
        mTimeattackQuestStageRepository.saveAndFlush(mTimeattackQuestStage);

        // Get all the mTimeattackQuestStageList where extraFirstHalfNpcDeckId equals to DEFAULT_EXTRA_FIRST_HALF_NPC_DECK_ID
        defaultMTimeattackQuestStageShouldBeFound("extraFirstHalfNpcDeckId.equals=" + DEFAULT_EXTRA_FIRST_HALF_NPC_DECK_ID);

        // Get all the mTimeattackQuestStageList where extraFirstHalfNpcDeckId equals to UPDATED_EXTRA_FIRST_HALF_NPC_DECK_ID
        defaultMTimeattackQuestStageShouldNotBeFound("extraFirstHalfNpcDeckId.equals=" + UPDATED_EXTRA_FIRST_HALF_NPC_DECK_ID);
    }

    @Test
    @Transactional
    public void getAllMTimeattackQuestStagesByExtraFirstHalfNpcDeckIdIsInShouldWork() throws Exception {
        // Initialize the database
        mTimeattackQuestStageRepository.saveAndFlush(mTimeattackQuestStage);

        // Get all the mTimeattackQuestStageList where extraFirstHalfNpcDeckId in DEFAULT_EXTRA_FIRST_HALF_NPC_DECK_ID or UPDATED_EXTRA_FIRST_HALF_NPC_DECK_ID
        defaultMTimeattackQuestStageShouldBeFound("extraFirstHalfNpcDeckId.in=" + DEFAULT_EXTRA_FIRST_HALF_NPC_DECK_ID + "," + UPDATED_EXTRA_FIRST_HALF_NPC_DECK_ID);

        // Get all the mTimeattackQuestStageList where extraFirstHalfNpcDeckId equals to UPDATED_EXTRA_FIRST_HALF_NPC_DECK_ID
        defaultMTimeattackQuestStageShouldNotBeFound("extraFirstHalfNpcDeckId.in=" + UPDATED_EXTRA_FIRST_HALF_NPC_DECK_ID);
    }

    @Test
    @Transactional
    public void getAllMTimeattackQuestStagesByExtraFirstHalfNpcDeckIdIsNullOrNotNull() throws Exception {
        // Initialize the database
        mTimeattackQuestStageRepository.saveAndFlush(mTimeattackQuestStage);

        // Get all the mTimeattackQuestStageList where extraFirstHalfNpcDeckId is not null
        defaultMTimeattackQuestStageShouldBeFound("extraFirstHalfNpcDeckId.specified=true");

        // Get all the mTimeattackQuestStageList where extraFirstHalfNpcDeckId is null
        defaultMTimeattackQuestStageShouldNotBeFound("extraFirstHalfNpcDeckId.specified=false");
    }

    @Test
    @Transactional
    public void getAllMTimeattackQuestStagesByExtraFirstHalfNpcDeckIdIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mTimeattackQuestStageRepository.saveAndFlush(mTimeattackQuestStage);

        // Get all the mTimeattackQuestStageList where extraFirstHalfNpcDeckId greater than or equals to DEFAULT_EXTRA_FIRST_HALF_NPC_DECK_ID
        defaultMTimeattackQuestStageShouldBeFound("extraFirstHalfNpcDeckId.greaterOrEqualThan=" + DEFAULT_EXTRA_FIRST_HALF_NPC_DECK_ID);

        // Get all the mTimeattackQuestStageList where extraFirstHalfNpcDeckId greater than or equals to UPDATED_EXTRA_FIRST_HALF_NPC_DECK_ID
        defaultMTimeattackQuestStageShouldNotBeFound("extraFirstHalfNpcDeckId.greaterOrEqualThan=" + UPDATED_EXTRA_FIRST_HALF_NPC_DECK_ID);
    }

    @Test
    @Transactional
    public void getAllMTimeattackQuestStagesByExtraFirstHalfNpcDeckIdIsLessThanSomething() throws Exception {
        // Initialize the database
        mTimeattackQuestStageRepository.saveAndFlush(mTimeattackQuestStage);

        // Get all the mTimeattackQuestStageList where extraFirstHalfNpcDeckId less than or equals to DEFAULT_EXTRA_FIRST_HALF_NPC_DECK_ID
        defaultMTimeattackQuestStageShouldNotBeFound("extraFirstHalfNpcDeckId.lessThan=" + DEFAULT_EXTRA_FIRST_HALF_NPC_DECK_ID);

        // Get all the mTimeattackQuestStageList where extraFirstHalfNpcDeckId less than or equals to UPDATED_EXTRA_FIRST_HALF_NPC_DECK_ID
        defaultMTimeattackQuestStageShouldBeFound("extraFirstHalfNpcDeckId.lessThan=" + UPDATED_EXTRA_FIRST_HALF_NPC_DECK_ID);
    }


    @Test
    @Transactional
    public void getAllMTimeattackQuestStagesByConsumeApIsEqualToSomething() throws Exception {
        // Initialize the database
        mTimeattackQuestStageRepository.saveAndFlush(mTimeattackQuestStage);

        // Get all the mTimeattackQuestStageList where consumeAp equals to DEFAULT_CONSUME_AP
        defaultMTimeattackQuestStageShouldBeFound("consumeAp.equals=" + DEFAULT_CONSUME_AP);

        // Get all the mTimeattackQuestStageList where consumeAp equals to UPDATED_CONSUME_AP
        defaultMTimeattackQuestStageShouldNotBeFound("consumeAp.equals=" + UPDATED_CONSUME_AP);
    }

    @Test
    @Transactional
    public void getAllMTimeattackQuestStagesByConsumeApIsInShouldWork() throws Exception {
        // Initialize the database
        mTimeattackQuestStageRepository.saveAndFlush(mTimeattackQuestStage);

        // Get all the mTimeattackQuestStageList where consumeAp in DEFAULT_CONSUME_AP or UPDATED_CONSUME_AP
        defaultMTimeattackQuestStageShouldBeFound("consumeAp.in=" + DEFAULT_CONSUME_AP + "," + UPDATED_CONSUME_AP);

        // Get all the mTimeattackQuestStageList where consumeAp equals to UPDATED_CONSUME_AP
        defaultMTimeattackQuestStageShouldNotBeFound("consumeAp.in=" + UPDATED_CONSUME_AP);
    }

    @Test
    @Transactional
    public void getAllMTimeattackQuestStagesByConsumeApIsNullOrNotNull() throws Exception {
        // Initialize the database
        mTimeattackQuestStageRepository.saveAndFlush(mTimeattackQuestStage);

        // Get all the mTimeattackQuestStageList where consumeAp is not null
        defaultMTimeattackQuestStageShouldBeFound("consumeAp.specified=true");

        // Get all the mTimeattackQuestStageList where consumeAp is null
        defaultMTimeattackQuestStageShouldNotBeFound("consumeAp.specified=false");
    }

    @Test
    @Transactional
    public void getAllMTimeattackQuestStagesByConsumeApIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mTimeattackQuestStageRepository.saveAndFlush(mTimeattackQuestStage);

        // Get all the mTimeattackQuestStageList where consumeAp greater than or equals to DEFAULT_CONSUME_AP
        defaultMTimeattackQuestStageShouldBeFound("consumeAp.greaterOrEqualThan=" + DEFAULT_CONSUME_AP);

        // Get all the mTimeattackQuestStageList where consumeAp greater than or equals to UPDATED_CONSUME_AP
        defaultMTimeattackQuestStageShouldNotBeFound("consumeAp.greaterOrEqualThan=" + UPDATED_CONSUME_AP);
    }

    @Test
    @Transactional
    public void getAllMTimeattackQuestStagesByConsumeApIsLessThanSomething() throws Exception {
        // Initialize the database
        mTimeattackQuestStageRepository.saveAndFlush(mTimeattackQuestStage);

        // Get all the mTimeattackQuestStageList where consumeAp less than or equals to DEFAULT_CONSUME_AP
        defaultMTimeattackQuestStageShouldNotBeFound("consumeAp.lessThan=" + DEFAULT_CONSUME_AP);

        // Get all the mTimeattackQuestStageList where consumeAp less than or equals to UPDATED_CONSUME_AP
        defaultMTimeattackQuestStageShouldBeFound("consumeAp.lessThan=" + UPDATED_CONSUME_AP);
    }


    @Test
    @Transactional
    public void getAllMTimeattackQuestStagesByTicketIdIsEqualToSomething() throws Exception {
        // Initialize the database
        mTimeattackQuestStageRepository.saveAndFlush(mTimeattackQuestStage);

        // Get all the mTimeattackQuestStageList where ticketId equals to DEFAULT_TICKET_ID
        defaultMTimeattackQuestStageShouldBeFound("ticketId.equals=" + DEFAULT_TICKET_ID);

        // Get all the mTimeattackQuestStageList where ticketId equals to UPDATED_TICKET_ID
        defaultMTimeattackQuestStageShouldNotBeFound("ticketId.equals=" + UPDATED_TICKET_ID);
    }

    @Test
    @Transactional
    public void getAllMTimeattackQuestStagesByTicketIdIsInShouldWork() throws Exception {
        // Initialize the database
        mTimeattackQuestStageRepository.saveAndFlush(mTimeattackQuestStage);

        // Get all the mTimeattackQuestStageList where ticketId in DEFAULT_TICKET_ID or UPDATED_TICKET_ID
        defaultMTimeattackQuestStageShouldBeFound("ticketId.in=" + DEFAULT_TICKET_ID + "," + UPDATED_TICKET_ID);

        // Get all the mTimeattackQuestStageList where ticketId equals to UPDATED_TICKET_ID
        defaultMTimeattackQuestStageShouldNotBeFound("ticketId.in=" + UPDATED_TICKET_ID);
    }

    @Test
    @Transactional
    public void getAllMTimeattackQuestStagesByTicketIdIsNullOrNotNull() throws Exception {
        // Initialize the database
        mTimeattackQuestStageRepository.saveAndFlush(mTimeattackQuestStage);

        // Get all the mTimeattackQuestStageList where ticketId is not null
        defaultMTimeattackQuestStageShouldBeFound("ticketId.specified=true");

        // Get all the mTimeattackQuestStageList where ticketId is null
        defaultMTimeattackQuestStageShouldNotBeFound("ticketId.specified=false");
    }

    @Test
    @Transactional
    public void getAllMTimeattackQuestStagesByTicketIdIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mTimeattackQuestStageRepository.saveAndFlush(mTimeattackQuestStage);

        // Get all the mTimeattackQuestStageList where ticketId greater than or equals to DEFAULT_TICKET_ID
        defaultMTimeattackQuestStageShouldBeFound("ticketId.greaterOrEqualThan=" + DEFAULT_TICKET_ID);

        // Get all the mTimeattackQuestStageList where ticketId greater than or equals to UPDATED_TICKET_ID
        defaultMTimeattackQuestStageShouldNotBeFound("ticketId.greaterOrEqualThan=" + UPDATED_TICKET_ID);
    }

    @Test
    @Transactional
    public void getAllMTimeattackQuestStagesByTicketIdIsLessThanSomething() throws Exception {
        // Initialize the database
        mTimeattackQuestStageRepository.saveAndFlush(mTimeattackQuestStage);

        // Get all the mTimeattackQuestStageList where ticketId less than or equals to DEFAULT_TICKET_ID
        defaultMTimeattackQuestStageShouldNotBeFound("ticketId.lessThan=" + DEFAULT_TICKET_ID);

        // Get all the mTimeattackQuestStageList where ticketId less than or equals to UPDATED_TICKET_ID
        defaultMTimeattackQuestStageShouldBeFound("ticketId.lessThan=" + UPDATED_TICKET_ID);
    }


    @Test
    @Transactional
    public void getAllMTimeattackQuestStagesByTicketAmountIsEqualToSomething() throws Exception {
        // Initialize the database
        mTimeattackQuestStageRepository.saveAndFlush(mTimeattackQuestStage);

        // Get all the mTimeattackQuestStageList where ticketAmount equals to DEFAULT_TICKET_AMOUNT
        defaultMTimeattackQuestStageShouldBeFound("ticketAmount.equals=" + DEFAULT_TICKET_AMOUNT);

        // Get all the mTimeattackQuestStageList where ticketAmount equals to UPDATED_TICKET_AMOUNT
        defaultMTimeattackQuestStageShouldNotBeFound("ticketAmount.equals=" + UPDATED_TICKET_AMOUNT);
    }

    @Test
    @Transactional
    public void getAllMTimeattackQuestStagesByTicketAmountIsInShouldWork() throws Exception {
        // Initialize the database
        mTimeattackQuestStageRepository.saveAndFlush(mTimeattackQuestStage);

        // Get all the mTimeattackQuestStageList where ticketAmount in DEFAULT_TICKET_AMOUNT or UPDATED_TICKET_AMOUNT
        defaultMTimeattackQuestStageShouldBeFound("ticketAmount.in=" + DEFAULT_TICKET_AMOUNT + "," + UPDATED_TICKET_AMOUNT);

        // Get all the mTimeattackQuestStageList where ticketAmount equals to UPDATED_TICKET_AMOUNT
        defaultMTimeattackQuestStageShouldNotBeFound("ticketAmount.in=" + UPDATED_TICKET_AMOUNT);
    }

    @Test
    @Transactional
    public void getAllMTimeattackQuestStagesByTicketAmountIsNullOrNotNull() throws Exception {
        // Initialize the database
        mTimeattackQuestStageRepository.saveAndFlush(mTimeattackQuestStage);

        // Get all the mTimeattackQuestStageList where ticketAmount is not null
        defaultMTimeattackQuestStageShouldBeFound("ticketAmount.specified=true");

        // Get all the mTimeattackQuestStageList where ticketAmount is null
        defaultMTimeattackQuestStageShouldNotBeFound("ticketAmount.specified=false");
    }

    @Test
    @Transactional
    public void getAllMTimeattackQuestStagesByTicketAmountIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mTimeattackQuestStageRepository.saveAndFlush(mTimeattackQuestStage);

        // Get all the mTimeattackQuestStageList where ticketAmount greater than or equals to DEFAULT_TICKET_AMOUNT
        defaultMTimeattackQuestStageShouldBeFound("ticketAmount.greaterOrEqualThan=" + DEFAULT_TICKET_AMOUNT);

        // Get all the mTimeattackQuestStageList where ticketAmount greater than or equals to UPDATED_TICKET_AMOUNT
        defaultMTimeattackQuestStageShouldNotBeFound("ticketAmount.greaterOrEqualThan=" + UPDATED_TICKET_AMOUNT);
    }

    @Test
    @Transactional
    public void getAllMTimeattackQuestStagesByTicketAmountIsLessThanSomething() throws Exception {
        // Initialize the database
        mTimeattackQuestStageRepository.saveAndFlush(mTimeattackQuestStage);

        // Get all the mTimeattackQuestStageList where ticketAmount less than or equals to DEFAULT_TICKET_AMOUNT
        defaultMTimeattackQuestStageShouldNotBeFound("ticketAmount.lessThan=" + DEFAULT_TICKET_AMOUNT);

        // Get all the mTimeattackQuestStageList where ticketAmount less than or equals to UPDATED_TICKET_AMOUNT
        defaultMTimeattackQuestStageShouldBeFound("ticketAmount.lessThan=" + UPDATED_TICKET_AMOUNT);
    }


    @Test
    @Transactional
    public void getAllMTimeattackQuestStagesByKickOffTypeIsEqualToSomething() throws Exception {
        // Initialize the database
        mTimeattackQuestStageRepository.saveAndFlush(mTimeattackQuestStage);

        // Get all the mTimeattackQuestStageList where kickOffType equals to DEFAULT_KICK_OFF_TYPE
        defaultMTimeattackQuestStageShouldBeFound("kickOffType.equals=" + DEFAULT_KICK_OFF_TYPE);

        // Get all the mTimeattackQuestStageList where kickOffType equals to UPDATED_KICK_OFF_TYPE
        defaultMTimeattackQuestStageShouldNotBeFound("kickOffType.equals=" + UPDATED_KICK_OFF_TYPE);
    }

    @Test
    @Transactional
    public void getAllMTimeattackQuestStagesByKickOffTypeIsInShouldWork() throws Exception {
        // Initialize the database
        mTimeattackQuestStageRepository.saveAndFlush(mTimeattackQuestStage);

        // Get all the mTimeattackQuestStageList where kickOffType in DEFAULT_KICK_OFF_TYPE or UPDATED_KICK_OFF_TYPE
        defaultMTimeattackQuestStageShouldBeFound("kickOffType.in=" + DEFAULT_KICK_OFF_TYPE + "," + UPDATED_KICK_OFF_TYPE);

        // Get all the mTimeattackQuestStageList where kickOffType equals to UPDATED_KICK_OFF_TYPE
        defaultMTimeattackQuestStageShouldNotBeFound("kickOffType.in=" + UPDATED_KICK_OFF_TYPE);
    }

    @Test
    @Transactional
    public void getAllMTimeattackQuestStagesByKickOffTypeIsNullOrNotNull() throws Exception {
        // Initialize the database
        mTimeattackQuestStageRepository.saveAndFlush(mTimeattackQuestStage);

        // Get all the mTimeattackQuestStageList where kickOffType is not null
        defaultMTimeattackQuestStageShouldBeFound("kickOffType.specified=true");

        // Get all the mTimeattackQuestStageList where kickOffType is null
        defaultMTimeattackQuestStageShouldNotBeFound("kickOffType.specified=false");
    }

    @Test
    @Transactional
    public void getAllMTimeattackQuestStagesByKickOffTypeIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mTimeattackQuestStageRepository.saveAndFlush(mTimeattackQuestStage);

        // Get all the mTimeattackQuestStageList where kickOffType greater than or equals to DEFAULT_KICK_OFF_TYPE
        defaultMTimeattackQuestStageShouldBeFound("kickOffType.greaterOrEqualThan=" + DEFAULT_KICK_OFF_TYPE);

        // Get all the mTimeattackQuestStageList where kickOffType greater than or equals to UPDATED_KICK_OFF_TYPE
        defaultMTimeattackQuestStageShouldNotBeFound("kickOffType.greaterOrEqualThan=" + UPDATED_KICK_OFF_TYPE);
    }

    @Test
    @Transactional
    public void getAllMTimeattackQuestStagesByKickOffTypeIsLessThanSomething() throws Exception {
        // Initialize the database
        mTimeattackQuestStageRepository.saveAndFlush(mTimeattackQuestStage);

        // Get all the mTimeattackQuestStageList where kickOffType less than or equals to DEFAULT_KICK_OFF_TYPE
        defaultMTimeattackQuestStageShouldNotBeFound("kickOffType.lessThan=" + DEFAULT_KICK_OFF_TYPE);

        // Get all the mTimeattackQuestStageList where kickOffType less than or equals to UPDATED_KICK_OFF_TYPE
        defaultMTimeattackQuestStageShouldBeFound("kickOffType.lessThan=" + UPDATED_KICK_OFF_TYPE);
    }


    @Test
    @Transactional
    public void getAllMTimeattackQuestStagesByMatchMinuteIsEqualToSomething() throws Exception {
        // Initialize the database
        mTimeattackQuestStageRepository.saveAndFlush(mTimeattackQuestStage);

        // Get all the mTimeattackQuestStageList where matchMinute equals to DEFAULT_MATCH_MINUTE
        defaultMTimeattackQuestStageShouldBeFound("matchMinute.equals=" + DEFAULT_MATCH_MINUTE);

        // Get all the mTimeattackQuestStageList where matchMinute equals to UPDATED_MATCH_MINUTE
        defaultMTimeattackQuestStageShouldNotBeFound("matchMinute.equals=" + UPDATED_MATCH_MINUTE);
    }

    @Test
    @Transactional
    public void getAllMTimeattackQuestStagesByMatchMinuteIsInShouldWork() throws Exception {
        // Initialize the database
        mTimeattackQuestStageRepository.saveAndFlush(mTimeattackQuestStage);

        // Get all the mTimeattackQuestStageList where matchMinute in DEFAULT_MATCH_MINUTE or UPDATED_MATCH_MINUTE
        defaultMTimeattackQuestStageShouldBeFound("matchMinute.in=" + DEFAULT_MATCH_MINUTE + "," + UPDATED_MATCH_MINUTE);

        // Get all the mTimeattackQuestStageList where matchMinute equals to UPDATED_MATCH_MINUTE
        defaultMTimeattackQuestStageShouldNotBeFound("matchMinute.in=" + UPDATED_MATCH_MINUTE);
    }

    @Test
    @Transactional
    public void getAllMTimeattackQuestStagesByMatchMinuteIsNullOrNotNull() throws Exception {
        // Initialize the database
        mTimeattackQuestStageRepository.saveAndFlush(mTimeattackQuestStage);

        // Get all the mTimeattackQuestStageList where matchMinute is not null
        defaultMTimeattackQuestStageShouldBeFound("matchMinute.specified=true");

        // Get all the mTimeattackQuestStageList where matchMinute is null
        defaultMTimeattackQuestStageShouldNotBeFound("matchMinute.specified=false");
    }

    @Test
    @Transactional
    public void getAllMTimeattackQuestStagesByMatchMinuteIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mTimeattackQuestStageRepository.saveAndFlush(mTimeattackQuestStage);

        // Get all the mTimeattackQuestStageList where matchMinute greater than or equals to DEFAULT_MATCH_MINUTE
        defaultMTimeattackQuestStageShouldBeFound("matchMinute.greaterOrEqualThan=" + DEFAULT_MATCH_MINUTE);

        // Get all the mTimeattackQuestStageList where matchMinute greater than or equals to UPDATED_MATCH_MINUTE
        defaultMTimeattackQuestStageShouldNotBeFound("matchMinute.greaterOrEqualThan=" + UPDATED_MATCH_MINUTE);
    }

    @Test
    @Transactional
    public void getAllMTimeattackQuestStagesByMatchMinuteIsLessThanSomething() throws Exception {
        // Initialize the database
        mTimeattackQuestStageRepository.saveAndFlush(mTimeattackQuestStage);

        // Get all the mTimeattackQuestStageList where matchMinute less than or equals to DEFAULT_MATCH_MINUTE
        defaultMTimeattackQuestStageShouldNotBeFound("matchMinute.lessThan=" + DEFAULT_MATCH_MINUTE);

        // Get all the mTimeattackQuestStageList where matchMinute less than or equals to UPDATED_MATCH_MINUTE
        defaultMTimeattackQuestStageShouldBeFound("matchMinute.lessThan=" + UPDATED_MATCH_MINUTE);
    }


    @Test
    @Transactional
    public void getAllMTimeattackQuestStagesByEnableExtraIsEqualToSomething() throws Exception {
        // Initialize the database
        mTimeattackQuestStageRepository.saveAndFlush(mTimeattackQuestStage);

        // Get all the mTimeattackQuestStageList where enableExtra equals to DEFAULT_ENABLE_EXTRA
        defaultMTimeattackQuestStageShouldBeFound("enableExtra.equals=" + DEFAULT_ENABLE_EXTRA);

        // Get all the mTimeattackQuestStageList where enableExtra equals to UPDATED_ENABLE_EXTRA
        defaultMTimeattackQuestStageShouldNotBeFound("enableExtra.equals=" + UPDATED_ENABLE_EXTRA);
    }

    @Test
    @Transactional
    public void getAllMTimeattackQuestStagesByEnableExtraIsInShouldWork() throws Exception {
        // Initialize the database
        mTimeattackQuestStageRepository.saveAndFlush(mTimeattackQuestStage);

        // Get all the mTimeattackQuestStageList where enableExtra in DEFAULT_ENABLE_EXTRA or UPDATED_ENABLE_EXTRA
        defaultMTimeattackQuestStageShouldBeFound("enableExtra.in=" + DEFAULT_ENABLE_EXTRA + "," + UPDATED_ENABLE_EXTRA);

        // Get all the mTimeattackQuestStageList where enableExtra equals to UPDATED_ENABLE_EXTRA
        defaultMTimeattackQuestStageShouldNotBeFound("enableExtra.in=" + UPDATED_ENABLE_EXTRA);
    }

    @Test
    @Transactional
    public void getAllMTimeattackQuestStagesByEnableExtraIsNullOrNotNull() throws Exception {
        // Initialize the database
        mTimeattackQuestStageRepository.saveAndFlush(mTimeattackQuestStage);

        // Get all the mTimeattackQuestStageList where enableExtra is not null
        defaultMTimeattackQuestStageShouldBeFound("enableExtra.specified=true");

        // Get all the mTimeattackQuestStageList where enableExtra is null
        defaultMTimeattackQuestStageShouldNotBeFound("enableExtra.specified=false");
    }

    @Test
    @Transactional
    public void getAllMTimeattackQuestStagesByEnableExtraIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mTimeattackQuestStageRepository.saveAndFlush(mTimeattackQuestStage);

        // Get all the mTimeattackQuestStageList where enableExtra greater than or equals to DEFAULT_ENABLE_EXTRA
        defaultMTimeattackQuestStageShouldBeFound("enableExtra.greaterOrEqualThan=" + DEFAULT_ENABLE_EXTRA);

        // Get all the mTimeattackQuestStageList where enableExtra greater than or equals to UPDATED_ENABLE_EXTRA
        defaultMTimeattackQuestStageShouldNotBeFound("enableExtra.greaterOrEqualThan=" + UPDATED_ENABLE_EXTRA);
    }

    @Test
    @Transactional
    public void getAllMTimeattackQuestStagesByEnableExtraIsLessThanSomething() throws Exception {
        // Initialize the database
        mTimeattackQuestStageRepository.saveAndFlush(mTimeattackQuestStage);

        // Get all the mTimeattackQuestStageList where enableExtra less than or equals to DEFAULT_ENABLE_EXTRA
        defaultMTimeattackQuestStageShouldNotBeFound("enableExtra.lessThan=" + DEFAULT_ENABLE_EXTRA);

        // Get all the mTimeattackQuestStageList where enableExtra less than or equals to UPDATED_ENABLE_EXTRA
        defaultMTimeattackQuestStageShouldBeFound("enableExtra.lessThan=" + UPDATED_ENABLE_EXTRA);
    }


    @Test
    @Transactional
    public void getAllMTimeattackQuestStagesByEnablePkIsEqualToSomething() throws Exception {
        // Initialize the database
        mTimeattackQuestStageRepository.saveAndFlush(mTimeattackQuestStage);

        // Get all the mTimeattackQuestStageList where enablePk equals to DEFAULT_ENABLE_PK
        defaultMTimeattackQuestStageShouldBeFound("enablePk.equals=" + DEFAULT_ENABLE_PK);

        // Get all the mTimeattackQuestStageList where enablePk equals to UPDATED_ENABLE_PK
        defaultMTimeattackQuestStageShouldNotBeFound("enablePk.equals=" + UPDATED_ENABLE_PK);
    }

    @Test
    @Transactional
    public void getAllMTimeattackQuestStagesByEnablePkIsInShouldWork() throws Exception {
        // Initialize the database
        mTimeattackQuestStageRepository.saveAndFlush(mTimeattackQuestStage);

        // Get all the mTimeattackQuestStageList where enablePk in DEFAULT_ENABLE_PK or UPDATED_ENABLE_PK
        defaultMTimeattackQuestStageShouldBeFound("enablePk.in=" + DEFAULT_ENABLE_PK + "," + UPDATED_ENABLE_PK);

        // Get all the mTimeattackQuestStageList where enablePk equals to UPDATED_ENABLE_PK
        defaultMTimeattackQuestStageShouldNotBeFound("enablePk.in=" + UPDATED_ENABLE_PK);
    }

    @Test
    @Transactional
    public void getAllMTimeattackQuestStagesByEnablePkIsNullOrNotNull() throws Exception {
        // Initialize the database
        mTimeattackQuestStageRepository.saveAndFlush(mTimeattackQuestStage);

        // Get all the mTimeattackQuestStageList where enablePk is not null
        defaultMTimeattackQuestStageShouldBeFound("enablePk.specified=true");

        // Get all the mTimeattackQuestStageList where enablePk is null
        defaultMTimeattackQuestStageShouldNotBeFound("enablePk.specified=false");
    }

    @Test
    @Transactional
    public void getAllMTimeattackQuestStagesByEnablePkIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mTimeattackQuestStageRepository.saveAndFlush(mTimeattackQuestStage);

        // Get all the mTimeattackQuestStageList where enablePk greater than or equals to DEFAULT_ENABLE_PK
        defaultMTimeattackQuestStageShouldBeFound("enablePk.greaterOrEqualThan=" + DEFAULT_ENABLE_PK);

        // Get all the mTimeattackQuestStageList where enablePk greater than or equals to UPDATED_ENABLE_PK
        defaultMTimeattackQuestStageShouldNotBeFound("enablePk.greaterOrEqualThan=" + UPDATED_ENABLE_PK);
    }

    @Test
    @Transactional
    public void getAllMTimeattackQuestStagesByEnablePkIsLessThanSomething() throws Exception {
        // Initialize the database
        mTimeattackQuestStageRepository.saveAndFlush(mTimeattackQuestStage);

        // Get all the mTimeattackQuestStageList where enablePk less than or equals to DEFAULT_ENABLE_PK
        defaultMTimeattackQuestStageShouldNotBeFound("enablePk.lessThan=" + DEFAULT_ENABLE_PK);

        // Get all the mTimeattackQuestStageList where enablePk less than or equals to UPDATED_ENABLE_PK
        defaultMTimeattackQuestStageShouldBeFound("enablePk.lessThan=" + UPDATED_ENABLE_PK);
    }


    @Test
    @Transactional
    public void getAllMTimeattackQuestStagesByAiLevelIsEqualToSomething() throws Exception {
        // Initialize the database
        mTimeattackQuestStageRepository.saveAndFlush(mTimeattackQuestStage);

        // Get all the mTimeattackQuestStageList where aiLevel equals to DEFAULT_AI_LEVEL
        defaultMTimeattackQuestStageShouldBeFound("aiLevel.equals=" + DEFAULT_AI_LEVEL);

        // Get all the mTimeattackQuestStageList where aiLevel equals to UPDATED_AI_LEVEL
        defaultMTimeattackQuestStageShouldNotBeFound("aiLevel.equals=" + UPDATED_AI_LEVEL);
    }

    @Test
    @Transactional
    public void getAllMTimeattackQuestStagesByAiLevelIsInShouldWork() throws Exception {
        // Initialize the database
        mTimeattackQuestStageRepository.saveAndFlush(mTimeattackQuestStage);

        // Get all the mTimeattackQuestStageList where aiLevel in DEFAULT_AI_LEVEL or UPDATED_AI_LEVEL
        defaultMTimeattackQuestStageShouldBeFound("aiLevel.in=" + DEFAULT_AI_LEVEL + "," + UPDATED_AI_LEVEL);

        // Get all the mTimeattackQuestStageList where aiLevel equals to UPDATED_AI_LEVEL
        defaultMTimeattackQuestStageShouldNotBeFound("aiLevel.in=" + UPDATED_AI_LEVEL);
    }

    @Test
    @Transactional
    public void getAllMTimeattackQuestStagesByAiLevelIsNullOrNotNull() throws Exception {
        // Initialize the database
        mTimeattackQuestStageRepository.saveAndFlush(mTimeattackQuestStage);

        // Get all the mTimeattackQuestStageList where aiLevel is not null
        defaultMTimeattackQuestStageShouldBeFound("aiLevel.specified=true");

        // Get all the mTimeattackQuestStageList where aiLevel is null
        defaultMTimeattackQuestStageShouldNotBeFound("aiLevel.specified=false");
    }

    @Test
    @Transactional
    public void getAllMTimeattackQuestStagesByAiLevelIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mTimeattackQuestStageRepository.saveAndFlush(mTimeattackQuestStage);

        // Get all the mTimeattackQuestStageList where aiLevel greater than or equals to DEFAULT_AI_LEVEL
        defaultMTimeattackQuestStageShouldBeFound("aiLevel.greaterOrEqualThan=" + DEFAULT_AI_LEVEL);

        // Get all the mTimeattackQuestStageList where aiLevel greater than or equals to UPDATED_AI_LEVEL
        defaultMTimeattackQuestStageShouldNotBeFound("aiLevel.greaterOrEqualThan=" + UPDATED_AI_LEVEL);
    }

    @Test
    @Transactional
    public void getAllMTimeattackQuestStagesByAiLevelIsLessThanSomething() throws Exception {
        // Initialize the database
        mTimeattackQuestStageRepository.saveAndFlush(mTimeattackQuestStage);

        // Get all the mTimeattackQuestStageList where aiLevel less than or equals to DEFAULT_AI_LEVEL
        defaultMTimeattackQuestStageShouldNotBeFound("aiLevel.lessThan=" + DEFAULT_AI_LEVEL);

        // Get all the mTimeattackQuestStageList where aiLevel less than or equals to UPDATED_AI_LEVEL
        defaultMTimeattackQuestStageShouldBeFound("aiLevel.lessThan=" + UPDATED_AI_LEVEL);
    }


    @Test
    @Transactional
    public void getAllMTimeattackQuestStagesByStartAtSecondHalfIsEqualToSomething() throws Exception {
        // Initialize the database
        mTimeattackQuestStageRepository.saveAndFlush(mTimeattackQuestStage);

        // Get all the mTimeattackQuestStageList where startAtSecondHalf equals to DEFAULT_START_AT_SECOND_HALF
        defaultMTimeattackQuestStageShouldBeFound("startAtSecondHalf.equals=" + DEFAULT_START_AT_SECOND_HALF);

        // Get all the mTimeattackQuestStageList where startAtSecondHalf equals to UPDATED_START_AT_SECOND_HALF
        defaultMTimeattackQuestStageShouldNotBeFound("startAtSecondHalf.equals=" + UPDATED_START_AT_SECOND_HALF);
    }

    @Test
    @Transactional
    public void getAllMTimeattackQuestStagesByStartAtSecondHalfIsInShouldWork() throws Exception {
        // Initialize the database
        mTimeattackQuestStageRepository.saveAndFlush(mTimeattackQuestStage);

        // Get all the mTimeattackQuestStageList where startAtSecondHalf in DEFAULT_START_AT_SECOND_HALF or UPDATED_START_AT_SECOND_HALF
        defaultMTimeattackQuestStageShouldBeFound("startAtSecondHalf.in=" + DEFAULT_START_AT_SECOND_HALF + "," + UPDATED_START_AT_SECOND_HALF);

        // Get all the mTimeattackQuestStageList where startAtSecondHalf equals to UPDATED_START_AT_SECOND_HALF
        defaultMTimeattackQuestStageShouldNotBeFound("startAtSecondHalf.in=" + UPDATED_START_AT_SECOND_HALF);
    }

    @Test
    @Transactional
    public void getAllMTimeattackQuestStagesByStartAtSecondHalfIsNullOrNotNull() throws Exception {
        // Initialize the database
        mTimeattackQuestStageRepository.saveAndFlush(mTimeattackQuestStage);

        // Get all the mTimeattackQuestStageList where startAtSecondHalf is not null
        defaultMTimeattackQuestStageShouldBeFound("startAtSecondHalf.specified=true");

        // Get all the mTimeattackQuestStageList where startAtSecondHalf is null
        defaultMTimeattackQuestStageShouldNotBeFound("startAtSecondHalf.specified=false");
    }

    @Test
    @Transactional
    public void getAllMTimeattackQuestStagesByStartAtSecondHalfIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mTimeattackQuestStageRepository.saveAndFlush(mTimeattackQuestStage);

        // Get all the mTimeattackQuestStageList where startAtSecondHalf greater than or equals to DEFAULT_START_AT_SECOND_HALF
        defaultMTimeattackQuestStageShouldBeFound("startAtSecondHalf.greaterOrEqualThan=" + DEFAULT_START_AT_SECOND_HALF);

        // Get all the mTimeattackQuestStageList where startAtSecondHalf greater than or equals to UPDATED_START_AT_SECOND_HALF
        defaultMTimeattackQuestStageShouldNotBeFound("startAtSecondHalf.greaterOrEqualThan=" + UPDATED_START_AT_SECOND_HALF);
    }

    @Test
    @Transactional
    public void getAllMTimeattackQuestStagesByStartAtSecondHalfIsLessThanSomething() throws Exception {
        // Initialize the database
        mTimeattackQuestStageRepository.saveAndFlush(mTimeattackQuestStage);

        // Get all the mTimeattackQuestStageList where startAtSecondHalf less than or equals to DEFAULT_START_AT_SECOND_HALF
        defaultMTimeattackQuestStageShouldNotBeFound("startAtSecondHalf.lessThan=" + DEFAULT_START_AT_SECOND_HALF);

        // Get all the mTimeattackQuestStageList where startAtSecondHalf less than or equals to UPDATED_START_AT_SECOND_HALF
        defaultMTimeattackQuestStageShouldBeFound("startAtSecondHalf.lessThan=" + UPDATED_START_AT_SECOND_HALF);
    }


    @Test
    @Transactional
    public void getAllMTimeattackQuestStagesByStartScoreIsEqualToSomething() throws Exception {
        // Initialize the database
        mTimeattackQuestStageRepository.saveAndFlush(mTimeattackQuestStage);

        // Get all the mTimeattackQuestStageList where startScore equals to DEFAULT_START_SCORE
        defaultMTimeattackQuestStageShouldBeFound("startScore.equals=" + DEFAULT_START_SCORE);

        // Get all the mTimeattackQuestStageList where startScore equals to UPDATED_START_SCORE
        defaultMTimeattackQuestStageShouldNotBeFound("startScore.equals=" + UPDATED_START_SCORE);
    }

    @Test
    @Transactional
    public void getAllMTimeattackQuestStagesByStartScoreIsInShouldWork() throws Exception {
        // Initialize the database
        mTimeattackQuestStageRepository.saveAndFlush(mTimeattackQuestStage);

        // Get all the mTimeattackQuestStageList where startScore in DEFAULT_START_SCORE or UPDATED_START_SCORE
        defaultMTimeattackQuestStageShouldBeFound("startScore.in=" + DEFAULT_START_SCORE + "," + UPDATED_START_SCORE);

        // Get all the mTimeattackQuestStageList where startScore equals to UPDATED_START_SCORE
        defaultMTimeattackQuestStageShouldNotBeFound("startScore.in=" + UPDATED_START_SCORE);
    }

    @Test
    @Transactional
    public void getAllMTimeattackQuestStagesByStartScoreIsNullOrNotNull() throws Exception {
        // Initialize the database
        mTimeattackQuestStageRepository.saveAndFlush(mTimeattackQuestStage);

        // Get all the mTimeattackQuestStageList where startScore is not null
        defaultMTimeattackQuestStageShouldBeFound("startScore.specified=true");

        // Get all the mTimeattackQuestStageList where startScore is null
        defaultMTimeattackQuestStageShouldNotBeFound("startScore.specified=false");
    }

    @Test
    @Transactional
    public void getAllMTimeattackQuestStagesByStartScoreIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mTimeattackQuestStageRepository.saveAndFlush(mTimeattackQuestStage);

        // Get all the mTimeattackQuestStageList where startScore greater than or equals to DEFAULT_START_SCORE
        defaultMTimeattackQuestStageShouldBeFound("startScore.greaterOrEqualThan=" + DEFAULT_START_SCORE);

        // Get all the mTimeattackQuestStageList where startScore greater than or equals to UPDATED_START_SCORE
        defaultMTimeattackQuestStageShouldNotBeFound("startScore.greaterOrEqualThan=" + UPDATED_START_SCORE);
    }

    @Test
    @Transactional
    public void getAllMTimeattackQuestStagesByStartScoreIsLessThanSomething() throws Exception {
        // Initialize the database
        mTimeattackQuestStageRepository.saveAndFlush(mTimeattackQuestStage);

        // Get all the mTimeattackQuestStageList where startScore less than or equals to DEFAULT_START_SCORE
        defaultMTimeattackQuestStageShouldNotBeFound("startScore.lessThan=" + DEFAULT_START_SCORE);

        // Get all the mTimeattackQuestStageList where startScore less than or equals to UPDATED_START_SCORE
        defaultMTimeattackQuestStageShouldBeFound("startScore.lessThan=" + UPDATED_START_SCORE);
    }


    @Test
    @Transactional
    public void getAllMTimeattackQuestStagesByStartScoreOpponentIsEqualToSomething() throws Exception {
        // Initialize the database
        mTimeattackQuestStageRepository.saveAndFlush(mTimeattackQuestStage);

        // Get all the mTimeattackQuestStageList where startScoreOpponent equals to DEFAULT_START_SCORE_OPPONENT
        defaultMTimeattackQuestStageShouldBeFound("startScoreOpponent.equals=" + DEFAULT_START_SCORE_OPPONENT);

        // Get all the mTimeattackQuestStageList where startScoreOpponent equals to UPDATED_START_SCORE_OPPONENT
        defaultMTimeattackQuestStageShouldNotBeFound("startScoreOpponent.equals=" + UPDATED_START_SCORE_OPPONENT);
    }

    @Test
    @Transactional
    public void getAllMTimeattackQuestStagesByStartScoreOpponentIsInShouldWork() throws Exception {
        // Initialize the database
        mTimeattackQuestStageRepository.saveAndFlush(mTimeattackQuestStage);

        // Get all the mTimeattackQuestStageList where startScoreOpponent in DEFAULT_START_SCORE_OPPONENT or UPDATED_START_SCORE_OPPONENT
        defaultMTimeattackQuestStageShouldBeFound("startScoreOpponent.in=" + DEFAULT_START_SCORE_OPPONENT + "," + UPDATED_START_SCORE_OPPONENT);

        // Get all the mTimeattackQuestStageList where startScoreOpponent equals to UPDATED_START_SCORE_OPPONENT
        defaultMTimeattackQuestStageShouldNotBeFound("startScoreOpponent.in=" + UPDATED_START_SCORE_OPPONENT);
    }

    @Test
    @Transactional
    public void getAllMTimeattackQuestStagesByStartScoreOpponentIsNullOrNotNull() throws Exception {
        // Initialize the database
        mTimeattackQuestStageRepository.saveAndFlush(mTimeattackQuestStage);

        // Get all the mTimeattackQuestStageList where startScoreOpponent is not null
        defaultMTimeattackQuestStageShouldBeFound("startScoreOpponent.specified=true");

        // Get all the mTimeattackQuestStageList where startScoreOpponent is null
        defaultMTimeattackQuestStageShouldNotBeFound("startScoreOpponent.specified=false");
    }

    @Test
    @Transactional
    public void getAllMTimeattackQuestStagesByStartScoreOpponentIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mTimeattackQuestStageRepository.saveAndFlush(mTimeattackQuestStage);

        // Get all the mTimeattackQuestStageList where startScoreOpponent greater than or equals to DEFAULT_START_SCORE_OPPONENT
        defaultMTimeattackQuestStageShouldBeFound("startScoreOpponent.greaterOrEqualThan=" + DEFAULT_START_SCORE_OPPONENT);

        // Get all the mTimeattackQuestStageList where startScoreOpponent greater than or equals to UPDATED_START_SCORE_OPPONENT
        defaultMTimeattackQuestStageShouldNotBeFound("startScoreOpponent.greaterOrEqualThan=" + UPDATED_START_SCORE_OPPONENT);
    }

    @Test
    @Transactional
    public void getAllMTimeattackQuestStagesByStartScoreOpponentIsLessThanSomething() throws Exception {
        // Initialize the database
        mTimeattackQuestStageRepository.saveAndFlush(mTimeattackQuestStage);

        // Get all the mTimeattackQuestStageList where startScoreOpponent less than or equals to DEFAULT_START_SCORE_OPPONENT
        defaultMTimeattackQuestStageShouldNotBeFound("startScoreOpponent.lessThan=" + DEFAULT_START_SCORE_OPPONENT);

        // Get all the mTimeattackQuestStageList where startScoreOpponent less than or equals to UPDATED_START_SCORE_OPPONENT
        defaultMTimeattackQuestStageShouldBeFound("startScoreOpponent.lessThan=" + UPDATED_START_SCORE_OPPONENT);
    }


    @Test
    @Transactional
    public void getAllMTimeattackQuestStagesByConditionIdIsEqualToSomething() throws Exception {
        // Initialize the database
        mTimeattackQuestStageRepository.saveAndFlush(mTimeattackQuestStage);

        // Get all the mTimeattackQuestStageList where conditionId equals to DEFAULT_CONDITION_ID
        defaultMTimeattackQuestStageShouldBeFound("conditionId.equals=" + DEFAULT_CONDITION_ID);

        // Get all the mTimeattackQuestStageList where conditionId equals to UPDATED_CONDITION_ID
        defaultMTimeattackQuestStageShouldNotBeFound("conditionId.equals=" + UPDATED_CONDITION_ID);
    }

    @Test
    @Transactional
    public void getAllMTimeattackQuestStagesByConditionIdIsInShouldWork() throws Exception {
        // Initialize the database
        mTimeattackQuestStageRepository.saveAndFlush(mTimeattackQuestStage);

        // Get all the mTimeattackQuestStageList where conditionId in DEFAULT_CONDITION_ID or UPDATED_CONDITION_ID
        defaultMTimeattackQuestStageShouldBeFound("conditionId.in=" + DEFAULT_CONDITION_ID + "," + UPDATED_CONDITION_ID);

        // Get all the mTimeattackQuestStageList where conditionId equals to UPDATED_CONDITION_ID
        defaultMTimeattackQuestStageShouldNotBeFound("conditionId.in=" + UPDATED_CONDITION_ID);
    }

    @Test
    @Transactional
    public void getAllMTimeattackQuestStagesByConditionIdIsNullOrNotNull() throws Exception {
        // Initialize the database
        mTimeattackQuestStageRepository.saveAndFlush(mTimeattackQuestStage);

        // Get all the mTimeattackQuestStageList where conditionId is not null
        defaultMTimeattackQuestStageShouldBeFound("conditionId.specified=true");

        // Get all the mTimeattackQuestStageList where conditionId is null
        defaultMTimeattackQuestStageShouldNotBeFound("conditionId.specified=false");
    }

    @Test
    @Transactional
    public void getAllMTimeattackQuestStagesByConditionIdIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mTimeattackQuestStageRepository.saveAndFlush(mTimeattackQuestStage);

        // Get all the mTimeattackQuestStageList where conditionId greater than or equals to DEFAULT_CONDITION_ID
        defaultMTimeattackQuestStageShouldBeFound("conditionId.greaterOrEqualThan=" + DEFAULT_CONDITION_ID);

        // Get all the mTimeattackQuestStageList where conditionId greater than or equals to UPDATED_CONDITION_ID
        defaultMTimeattackQuestStageShouldNotBeFound("conditionId.greaterOrEqualThan=" + UPDATED_CONDITION_ID);
    }

    @Test
    @Transactional
    public void getAllMTimeattackQuestStagesByConditionIdIsLessThanSomething() throws Exception {
        // Initialize the database
        mTimeattackQuestStageRepository.saveAndFlush(mTimeattackQuestStage);

        // Get all the mTimeattackQuestStageList where conditionId less than or equals to DEFAULT_CONDITION_ID
        defaultMTimeattackQuestStageShouldNotBeFound("conditionId.lessThan=" + DEFAULT_CONDITION_ID);

        // Get all the mTimeattackQuestStageList where conditionId less than or equals to UPDATED_CONDITION_ID
        defaultMTimeattackQuestStageShouldBeFound("conditionId.lessThan=" + UPDATED_CONDITION_ID);
    }


    @Test
    @Transactional
    public void getAllMTimeattackQuestStagesByOptionIdIsEqualToSomething() throws Exception {
        // Initialize the database
        mTimeattackQuestStageRepository.saveAndFlush(mTimeattackQuestStage);

        // Get all the mTimeattackQuestStageList where optionId equals to DEFAULT_OPTION_ID
        defaultMTimeattackQuestStageShouldBeFound("optionId.equals=" + DEFAULT_OPTION_ID);

        // Get all the mTimeattackQuestStageList where optionId equals to UPDATED_OPTION_ID
        defaultMTimeattackQuestStageShouldNotBeFound("optionId.equals=" + UPDATED_OPTION_ID);
    }

    @Test
    @Transactional
    public void getAllMTimeattackQuestStagesByOptionIdIsInShouldWork() throws Exception {
        // Initialize the database
        mTimeattackQuestStageRepository.saveAndFlush(mTimeattackQuestStage);

        // Get all the mTimeattackQuestStageList where optionId in DEFAULT_OPTION_ID or UPDATED_OPTION_ID
        defaultMTimeattackQuestStageShouldBeFound("optionId.in=" + DEFAULT_OPTION_ID + "," + UPDATED_OPTION_ID);

        // Get all the mTimeattackQuestStageList where optionId equals to UPDATED_OPTION_ID
        defaultMTimeattackQuestStageShouldNotBeFound("optionId.in=" + UPDATED_OPTION_ID);
    }

    @Test
    @Transactional
    public void getAllMTimeattackQuestStagesByOptionIdIsNullOrNotNull() throws Exception {
        // Initialize the database
        mTimeattackQuestStageRepository.saveAndFlush(mTimeattackQuestStage);

        // Get all the mTimeattackQuestStageList where optionId is not null
        defaultMTimeattackQuestStageShouldBeFound("optionId.specified=true");

        // Get all the mTimeattackQuestStageList where optionId is null
        defaultMTimeattackQuestStageShouldNotBeFound("optionId.specified=false");
    }

    @Test
    @Transactional
    public void getAllMTimeattackQuestStagesByOptionIdIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mTimeattackQuestStageRepository.saveAndFlush(mTimeattackQuestStage);

        // Get all the mTimeattackQuestStageList where optionId greater than or equals to DEFAULT_OPTION_ID
        defaultMTimeattackQuestStageShouldBeFound("optionId.greaterOrEqualThan=" + DEFAULT_OPTION_ID);

        // Get all the mTimeattackQuestStageList where optionId greater than or equals to UPDATED_OPTION_ID
        defaultMTimeattackQuestStageShouldNotBeFound("optionId.greaterOrEqualThan=" + UPDATED_OPTION_ID);
    }

    @Test
    @Transactional
    public void getAllMTimeattackQuestStagesByOptionIdIsLessThanSomething() throws Exception {
        // Initialize the database
        mTimeattackQuestStageRepository.saveAndFlush(mTimeattackQuestStage);

        // Get all the mTimeattackQuestStageList where optionId less than or equals to DEFAULT_OPTION_ID
        defaultMTimeattackQuestStageShouldNotBeFound("optionId.lessThan=" + DEFAULT_OPTION_ID);

        // Get all the mTimeattackQuestStageList where optionId less than or equals to UPDATED_OPTION_ID
        defaultMTimeattackQuestStageShouldBeFound("optionId.lessThan=" + UPDATED_OPTION_ID);
    }


    @Test
    @Transactional
    public void getAllMTimeattackQuestStagesByDeckConditionIdIsEqualToSomething() throws Exception {
        // Initialize the database
        mTimeattackQuestStageRepository.saveAndFlush(mTimeattackQuestStage);

        // Get all the mTimeattackQuestStageList where deckConditionId equals to DEFAULT_DECK_CONDITION_ID
        defaultMTimeattackQuestStageShouldBeFound("deckConditionId.equals=" + DEFAULT_DECK_CONDITION_ID);

        // Get all the mTimeattackQuestStageList where deckConditionId equals to UPDATED_DECK_CONDITION_ID
        defaultMTimeattackQuestStageShouldNotBeFound("deckConditionId.equals=" + UPDATED_DECK_CONDITION_ID);
    }

    @Test
    @Transactional
    public void getAllMTimeattackQuestStagesByDeckConditionIdIsInShouldWork() throws Exception {
        // Initialize the database
        mTimeattackQuestStageRepository.saveAndFlush(mTimeattackQuestStage);

        // Get all the mTimeattackQuestStageList where deckConditionId in DEFAULT_DECK_CONDITION_ID or UPDATED_DECK_CONDITION_ID
        defaultMTimeattackQuestStageShouldBeFound("deckConditionId.in=" + DEFAULT_DECK_CONDITION_ID + "," + UPDATED_DECK_CONDITION_ID);

        // Get all the mTimeattackQuestStageList where deckConditionId equals to UPDATED_DECK_CONDITION_ID
        defaultMTimeattackQuestStageShouldNotBeFound("deckConditionId.in=" + UPDATED_DECK_CONDITION_ID);
    }

    @Test
    @Transactional
    public void getAllMTimeattackQuestStagesByDeckConditionIdIsNullOrNotNull() throws Exception {
        // Initialize the database
        mTimeattackQuestStageRepository.saveAndFlush(mTimeattackQuestStage);

        // Get all the mTimeattackQuestStageList where deckConditionId is not null
        defaultMTimeattackQuestStageShouldBeFound("deckConditionId.specified=true");

        // Get all the mTimeattackQuestStageList where deckConditionId is null
        defaultMTimeattackQuestStageShouldNotBeFound("deckConditionId.specified=false");
    }

    @Test
    @Transactional
    public void getAllMTimeattackQuestStagesByDeckConditionIdIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mTimeattackQuestStageRepository.saveAndFlush(mTimeattackQuestStage);

        // Get all the mTimeattackQuestStageList where deckConditionId greater than or equals to DEFAULT_DECK_CONDITION_ID
        defaultMTimeattackQuestStageShouldBeFound("deckConditionId.greaterOrEqualThan=" + DEFAULT_DECK_CONDITION_ID);

        // Get all the mTimeattackQuestStageList where deckConditionId greater than or equals to UPDATED_DECK_CONDITION_ID
        defaultMTimeattackQuestStageShouldNotBeFound("deckConditionId.greaterOrEqualThan=" + UPDATED_DECK_CONDITION_ID);
    }

    @Test
    @Transactional
    public void getAllMTimeattackQuestStagesByDeckConditionIdIsLessThanSomething() throws Exception {
        // Initialize the database
        mTimeattackQuestStageRepository.saveAndFlush(mTimeattackQuestStage);

        // Get all the mTimeattackQuestStageList where deckConditionId less than or equals to DEFAULT_DECK_CONDITION_ID
        defaultMTimeattackQuestStageShouldNotBeFound("deckConditionId.lessThan=" + DEFAULT_DECK_CONDITION_ID);

        // Get all the mTimeattackQuestStageList where deckConditionId less than or equals to UPDATED_DECK_CONDITION_ID
        defaultMTimeattackQuestStageShouldBeFound("deckConditionId.lessThan=" + UPDATED_DECK_CONDITION_ID);
    }


    @Test
    @Transactional
    public void getAllMTimeattackQuestStagesByIdIsEqualToSomething() throws Exception {
        // Get already existing entity
        MTimeattackQuestWorld id = mTimeattackQuestStage.getId();
        mTimeattackQuestStageRepository.saveAndFlush(mTimeattackQuestStage);
        Long idId = id.getId();

        // Get all the mTimeattackQuestStageList where id equals to idId
        defaultMTimeattackQuestStageShouldBeFound("idId.equals=" + idId);

        // Get all the mTimeattackQuestStageList where id equals to idId + 1
        defaultMTimeattackQuestStageShouldNotBeFound("idId.equals=" + (idId + 1));
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultMTimeattackQuestStageShouldBeFound(String filter) throws Exception {
        restMTimeattackQuestStageMockMvc.perform(get("/api/m-timeattack-quest-stages?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(mTimeattackQuestStage.getId().intValue())))
            .andExpect(jsonPath("$.[*].worldId").value(hasItem(DEFAULT_WORLD_ID)))
            .andExpect(jsonPath("$.[*].number").value(hasItem(DEFAULT_NUMBER)))
            .andExpect(jsonPath("$.[*].startAt").value(hasItem(DEFAULT_START_AT)))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME.toString())))
            .andExpect(jsonPath("$.[*].kickoffDescription").value(hasItem(DEFAULT_KICKOFF_DESCRIPTION.toString())))
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
            .andExpect(jsonPath("$.[*].ticketId").value(hasItem(DEFAULT_TICKET_ID)))
            .andExpect(jsonPath("$.[*].ticketAmount").value(hasItem(DEFAULT_TICKET_AMOUNT)))
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
            .andExpect(jsonPath("$.[*].shortName").value(hasItem(DEFAULT_SHORT_NAME.toString())));

        // Check, that the count call also returns 1
        restMTimeattackQuestStageMockMvc.perform(get("/api/m-timeattack-quest-stages/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultMTimeattackQuestStageShouldNotBeFound(String filter) throws Exception {
        restMTimeattackQuestStageMockMvc.perform(get("/api/m-timeattack-quest-stages?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restMTimeattackQuestStageMockMvc.perform(get("/api/m-timeattack-quest-stages/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("0"));
    }


    @Test
    @Transactional
    public void getNonExistingMTimeattackQuestStage() throws Exception {
        // Get the mTimeattackQuestStage
        restMTimeattackQuestStageMockMvc.perform(get("/api/m-timeattack-quest-stages/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateMTimeattackQuestStage() throws Exception {
        // Initialize the database
        mTimeattackQuestStageRepository.saveAndFlush(mTimeattackQuestStage);

        int databaseSizeBeforeUpdate = mTimeattackQuestStageRepository.findAll().size();

        // Update the mTimeattackQuestStage
        MTimeattackQuestStage updatedMTimeattackQuestStage = mTimeattackQuestStageRepository.findById(mTimeattackQuestStage.getId()).get();
        // Disconnect from session so that the updates on updatedMTimeattackQuestStage are not directly saved in db
        em.detach(updatedMTimeattackQuestStage);
        updatedMTimeattackQuestStage
            .worldId(UPDATED_WORLD_ID)
            .number(UPDATED_NUMBER)
            .startAt(UPDATED_START_AT)
            .name(UPDATED_NAME)
            .kickoffDescription(UPDATED_KICKOFF_DESCRIPTION)
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
            .ticketId(UPDATED_TICKET_ID)
            .ticketAmount(UPDATED_TICKET_AMOUNT)
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
            .shortName(UPDATED_SHORT_NAME);
        MTimeattackQuestStageDTO mTimeattackQuestStageDTO = mTimeattackQuestStageMapper.toDto(updatedMTimeattackQuestStage);

        restMTimeattackQuestStageMockMvc.perform(put("/api/m-timeattack-quest-stages")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mTimeattackQuestStageDTO)))
            .andExpect(status().isOk());

        // Validate the MTimeattackQuestStage in the database
        List<MTimeattackQuestStage> mTimeattackQuestStageList = mTimeattackQuestStageRepository.findAll();
        assertThat(mTimeattackQuestStageList).hasSize(databaseSizeBeforeUpdate);
        MTimeattackQuestStage testMTimeattackQuestStage = mTimeattackQuestStageList.get(mTimeattackQuestStageList.size() - 1);
        assertThat(testMTimeattackQuestStage.getWorldId()).isEqualTo(UPDATED_WORLD_ID);
        assertThat(testMTimeattackQuestStage.getNumber()).isEqualTo(UPDATED_NUMBER);
        assertThat(testMTimeattackQuestStage.getStartAt()).isEqualTo(UPDATED_START_AT);
        assertThat(testMTimeattackQuestStage.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testMTimeattackQuestStage.getKickoffDescription()).isEqualTo(UPDATED_KICKOFF_DESCRIPTION);
        assertThat(testMTimeattackQuestStage.getImagePath()).isEqualTo(UPDATED_IMAGE_PATH);
        assertThat(testMTimeattackQuestStage.getCharacterThumbnailPath()).isEqualTo(UPDATED_CHARACTER_THUMBNAIL_PATH);
        assertThat(testMTimeattackQuestStage.getDifficulty()).isEqualTo(UPDATED_DIFFICULTY);
        assertThat(testMTimeattackQuestStage.getStageUnlockPattern()).isEqualTo(UPDATED_STAGE_UNLOCK_PATTERN);
        assertThat(testMTimeattackQuestStage.getStoryOnly()).isEqualTo(UPDATED_STORY_ONLY);
        assertThat(testMTimeattackQuestStage.getFirstHalfNpcDeckId()).isEqualTo(UPDATED_FIRST_HALF_NPC_DECK_ID);
        assertThat(testMTimeattackQuestStage.getFirstHalfEnvironmentId()).isEqualTo(UPDATED_FIRST_HALF_ENVIRONMENT_ID);
        assertThat(testMTimeattackQuestStage.getSecondHalfNpcDeckId()).isEqualTo(UPDATED_SECOND_HALF_NPC_DECK_ID);
        assertThat(testMTimeattackQuestStage.getSecondHalfEnvironmentId()).isEqualTo(UPDATED_SECOND_HALF_ENVIRONMENT_ID);
        assertThat(testMTimeattackQuestStage.getExtraFirstHalfNpcDeckId()).isEqualTo(UPDATED_EXTRA_FIRST_HALF_NPC_DECK_ID);
        assertThat(testMTimeattackQuestStage.getConsumeAp()).isEqualTo(UPDATED_CONSUME_AP);
        assertThat(testMTimeattackQuestStage.getTicketId()).isEqualTo(UPDATED_TICKET_ID);
        assertThat(testMTimeattackQuestStage.getTicketAmount()).isEqualTo(UPDATED_TICKET_AMOUNT);
        assertThat(testMTimeattackQuestStage.getKickOffType()).isEqualTo(UPDATED_KICK_OFF_TYPE);
        assertThat(testMTimeattackQuestStage.getMatchMinute()).isEqualTo(UPDATED_MATCH_MINUTE);
        assertThat(testMTimeattackQuestStage.getEnableExtra()).isEqualTo(UPDATED_ENABLE_EXTRA);
        assertThat(testMTimeattackQuestStage.getEnablePk()).isEqualTo(UPDATED_ENABLE_PK);
        assertThat(testMTimeattackQuestStage.getAiLevel()).isEqualTo(UPDATED_AI_LEVEL);
        assertThat(testMTimeattackQuestStage.getStartAtSecondHalf()).isEqualTo(UPDATED_START_AT_SECOND_HALF);
        assertThat(testMTimeattackQuestStage.getStartScore()).isEqualTo(UPDATED_START_SCORE);
        assertThat(testMTimeattackQuestStage.getStartScoreOpponent()).isEqualTo(UPDATED_START_SCORE_OPPONENT);
        assertThat(testMTimeattackQuestStage.getConditionId()).isEqualTo(UPDATED_CONDITION_ID);
        assertThat(testMTimeattackQuestStage.getOptionId()).isEqualTo(UPDATED_OPTION_ID);
        assertThat(testMTimeattackQuestStage.getDeckConditionId()).isEqualTo(UPDATED_DECK_CONDITION_ID);
        assertThat(testMTimeattackQuestStage.getShortName()).isEqualTo(UPDATED_SHORT_NAME);
    }

    @Test
    @Transactional
    public void updateNonExistingMTimeattackQuestStage() throws Exception {
        int databaseSizeBeforeUpdate = mTimeattackQuestStageRepository.findAll().size();

        // Create the MTimeattackQuestStage
        MTimeattackQuestStageDTO mTimeattackQuestStageDTO = mTimeattackQuestStageMapper.toDto(mTimeattackQuestStage);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restMTimeattackQuestStageMockMvc.perform(put("/api/m-timeattack-quest-stages")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mTimeattackQuestStageDTO)))
            .andExpect(status().isBadRequest());

        // Validate the MTimeattackQuestStage in the database
        List<MTimeattackQuestStage> mTimeattackQuestStageList = mTimeattackQuestStageRepository.findAll();
        assertThat(mTimeattackQuestStageList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteMTimeattackQuestStage() throws Exception {
        // Initialize the database
        mTimeattackQuestStageRepository.saveAndFlush(mTimeattackQuestStage);

        int databaseSizeBeforeDelete = mTimeattackQuestStageRepository.findAll().size();

        // Delete the mTimeattackQuestStage
        restMTimeattackQuestStageMockMvc.perform(delete("/api/m-timeattack-quest-stages/{id}", mTimeattackQuestStage.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database is empty
        List<MTimeattackQuestStage> mTimeattackQuestStageList = mTimeattackQuestStageRepository.findAll();
        assertThat(mTimeattackQuestStageList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(MTimeattackQuestStage.class);
        MTimeattackQuestStage mTimeattackQuestStage1 = new MTimeattackQuestStage();
        mTimeattackQuestStage1.setId(1L);
        MTimeattackQuestStage mTimeattackQuestStage2 = new MTimeattackQuestStage();
        mTimeattackQuestStage2.setId(mTimeattackQuestStage1.getId());
        assertThat(mTimeattackQuestStage1).isEqualTo(mTimeattackQuestStage2);
        mTimeattackQuestStage2.setId(2L);
        assertThat(mTimeattackQuestStage1).isNotEqualTo(mTimeattackQuestStage2);
        mTimeattackQuestStage1.setId(null);
        assertThat(mTimeattackQuestStage1).isNotEqualTo(mTimeattackQuestStage2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(MTimeattackQuestStageDTO.class);
        MTimeattackQuestStageDTO mTimeattackQuestStageDTO1 = new MTimeattackQuestStageDTO();
        mTimeattackQuestStageDTO1.setId(1L);
        MTimeattackQuestStageDTO mTimeattackQuestStageDTO2 = new MTimeattackQuestStageDTO();
        assertThat(mTimeattackQuestStageDTO1).isNotEqualTo(mTimeattackQuestStageDTO2);
        mTimeattackQuestStageDTO2.setId(mTimeattackQuestStageDTO1.getId());
        assertThat(mTimeattackQuestStageDTO1).isEqualTo(mTimeattackQuestStageDTO2);
        mTimeattackQuestStageDTO2.setId(2L);
        assertThat(mTimeattackQuestStageDTO1).isNotEqualTo(mTimeattackQuestStageDTO2);
        mTimeattackQuestStageDTO1.setId(null);
        assertThat(mTimeattackQuestStageDTO1).isNotEqualTo(mTimeattackQuestStageDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(mTimeattackQuestStageMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(mTimeattackQuestStageMapper.fromId(null)).isNull();
    }
}
