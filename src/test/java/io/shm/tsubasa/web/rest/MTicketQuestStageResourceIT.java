package io.shm.tsubasa.web.rest;

import io.shm.tsubasa.TsubasaApp;
import io.shm.tsubasa.domain.MTicketQuestStage;
import io.shm.tsubasa.domain.MTicketQuestWorld;
import io.shm.tsubasa.repository.MTicketQuestStageRepository;
import io.shm.tsubasa.service.MTicketQuestStageService;
import io.shm.tsubasa.service.dto.MTicketQuestStageDTO;
import io.shm.tsubasa.service.mapper.MTicketQuestStageMapper;
import io.shm.tsubasa.web.rest.errors.ExceptionTranslator;
import io.shm.tsubasa.service.dto.MTicketQuestStageCriteria;
import io.shm.tsubasa.service.MTicketQuestStageQueryService;

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
 * Integration tests for the {@Link MTicketQuestStageResource} REST controller.
 */
@SpringBootTest(classes = TsubasaApp.class)
public class MTicketQuestStageResourceIT {

    private static final Integer DEFAULT_WORLD_ID = 1;
    private static final Integer UPDATED_WORLD_ID = 2;

    private static final Integer DEFAULT_NUMBER = 1;
    private static final Integer UPDATED_NUMBER = 2;

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final Integer DEFAULT_TICKET_ID = 1;
    private static final Integer UPDATED_TICKET_ID = 2;

    private static final Integer DEFAULT_TICKET_AMOUNT = 1;
    private static final Integer UPDATED_TICKET_AMOUNT = 2;

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
    private MTicketQuestStageRepository mTicketQuestStageRepository;

    @Autowired
    private MTicketQuestStageMapper mTicketQuestStageMapper;

    @Autowired
    private MTicketQuestStageService mTicketQuestStageService;

    @Autowired
    private MTicketQuestStageQueryService mTicketQuestStageQueryService;

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

    private MockMvc restMTicketQuestStageMockMvc;

    private MTicketQuestStage mTicketQuestStage;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final MTicketQuestStageResource mTicketQuestStageResource = new MTicketQuestStageResource(mTicketQuestStageService, mTicketQuestStageQueryService);
        this.restMTicketQuestStageMockMvc = MockMvcBuilders.standaloneSetup(mTicketQuestStageResource)
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
    public static MTicketQuestStage createEntity(EntityManager em) {
        MTicketQuestStage mTicketQuestStage = new MTicketQuestStage()
            .worldId(DEFAULT_WORLD_ID)
            .number(DEFAULT_NUMBER)
            .name(DEFAULT_NAME)
            .ticketId(DEFAULT_TICKET_ID)
            .ticketAmount(DEFAULT_TICKET_AMOUNT)
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
        MTicketQuestWorld mTicketQuestWorld;
        if (TestUtil.findAll(em, MTicketQuestWorld.class).isEmpty()) {
            mTicketQuestWorld = MTicketQuestWorldResourceIT.createEntity(em);
            em.persist(mTicketQuestWorld);
            em.flush();
        } else {
            mTicketQuestWorld = TestUtil.findAll(em, MTicketQuestWorld.class).get(0);
        }
        mTicketQuestStage.setMticketquestworld(mTicketQuestWorld);
        return mTicketQuestStage;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static MTicketQuestStage createUpdatedEntity(EntityManager em) {
        MTicketQuestStage mTicketQuestStage = new MTicketQuestStage()
            .worldId(UPDATED_WORLD_ID)
            .number(UPDATED_NUMBER)
            .name(UPDATED_NAME)
            .ticketId(UPDATED_TICKET_ID)
            .ticketAmount(UPDATED_TICKET_AMOUNT)
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
        MTicketQuestWorld mTicketQuestWorld;
        if (TestUtil.findAll(em, MTicketQuestWorld.class).isEmpty()) {
            mTicketQuestWorld = MTicketQuestWorldResourceIT.createUpdatedEntity(em);
            em.persist(mTicketQuestWorld);
            em.flush();
        } else {
            mTicketQuestWorld = TestUtil.findAll(em, MTicketQuestWorld.class).get(0);
        }
        mTicketQuestStage.setMticketquestworld(mTicketQuestWorld);
        return mTicketQuestStage;
    }

    @BeforeEach
    public void initTest() {
        mTicketQuestStage = createEntity(em);
    }

    @Test
    @Transactional
    public void createMTicketQuestStage() throws Exception {
        int databaseSizeBeforeCreate = mTicketQuestStageRepository.findAll().size();

        // Create the MTicketQuestStage
        MTicketQuestStageDTO mTicketQuestStageDTO = mTicketQuestStageMapper.toDto(mTicketQuestStage);
        restMTicketQuestStageMockMvc.perform(post("/api/m-ticket-quest-stages")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mTicketQuestStageDTO)))
            .andExpect(status().isCreated());

        // Validate the MTicketQuestStage in the database
        List<MTicketQuestStage> mTicketQuestStageList = mTicketQuestStageRepository.findAll();
        assertThat(mTicketQuestStageList).hasSize(databaseSizeBeforeCreate + 1);
        MTicketQuestStage testMTicketQuestStage = mTicketQuestStageList.get(mTicketQuestStageList.size() - 1);
        assertThat(testMTicketQuestStage.getWorldId()).isEqualTo(DEFAULT_WORLD_ID);
        assertThat(testMTicketQuestStage.getNumber()).isEqualTo(DEFAULT_NUMBER);
        assertThat(testMTicketQuestStage.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testMTicketQuestStage.getTicketId()).isEqualTo(DEFAULT_TICKET_ID);
        assertThat(testMTicketQuestStage.getTicketAmount()).isEqualTo(DEFAULT_TICKET_AMOUNT);
        assertThat(testMTicketQuestStage.getImagePath()).isEqualTo(DEFAULT_IMAGE_PATH);
        assertThat(testMTicketQuestStage.getCharacterThumbnailPath()).isEqualTo(DEFAULT_CHARACTER_THUMBNAIL_PATH);
        assertThat(testMTicketQuestStage.getDifficulty()).isEqualTo(DEFAULT_DIFFICULTY);
        assertThat(testMTicketQuestStage.getStageUnlockPattern()).isEqualTo(DEFAULT_STAGE_UNLOCK_PATTERN);
        assertThat(testMTicketQuestStage.getStoryOnly()).isEqualTo(DEFAULT_STORY_ONLY);
        assertThat(testMTicketQuestStage.getFirstHalfNpcDeckId()).isEqualTo(DEFAULT_FIRST_HALF_NPC_DECK_ID);
        assertThat(testMTicketQuestStage.getFirstHalfEnvironmentId()).isEqualTo(DEFAULT_FIRST_HALF_ENVIRONMENT_ID);
        assertThat(testMTicketQuestStage.getSecondHalfNpcDeckId()).isEqualTo(DEFAULT_SECOND_HALF_NPC_DECK_ID);
        assertThat(testMTicketQuestStage.getSecondHalfEnvironmentId()).isEqualTo(DEFAULT_SECOND_HALF_ENVIRONMENT_ID);
        assertThat(testMTicketQuestStage.getExtraFirstHalfNpcDeckId()).isEqualTo(DEFAULT_EXTRA_FIRST_HALF_NPC_DECK_ID);
        assertThat(testMTicketQuestStage.getExtraSecondHalfNpcDeckId()).isEqualTo(DEFAULT_EXTRA_SECOND_HALF_NPC_DECK_ID);
        assertThat(testMTicketQuestStage.getConsumeAp()).isEqualTo(DEFAULT_CONSUME_AP);
        assertThat(testMTicketQuestStage.getKickOffType()).isEqualTo(DEFAULT_KICK_OFF_TYPE);
        assertThat(testMTicketQuestStage.getMatchMinute()).isEqualTo(DEFAULT_MATCH_MINUTE);
        assertThat(testMTicketQuestStage.getEnableExtra()).isEqualTo(DEFAULT_ENABLE_EXTRA);
        assertThat(testMTicketQuestStage.getEnablePk()).isEqualTo(DEFAULT_ENABLE_PK);
        assertThat(testMTicketQuestStage.getAiLevel()).isEqualTo(DEFAULT_AI_LEVEL);
        assertThat(testMTicketQuestStage.getStartAtSecondHalf()).isEqualTo(DEFAULT_START_AT_SECOND_HALF);
        assertThat(testMTicketQuestStage.getStartScore()).isEqualTo(DEFAULT_START_SCORE);
        assertThat(testMTicketQuestStage.getStartScoreOpponent()).isEqualTo(DEFAULT_START_SCORE_OPPONENT);
        assertThat(testMTicketQuestStage.getConditionId()).isEqualTo(DEFAULT_CONDITION_ID);
        assertThat(testMTicketQuestStage.getOptionId()).isEqualTo(DEFAULT_OPTION_ID);
        assertThat(testMTicketQuestStage.getDeckConditionId()).isEqualTo(DEFAULT_DECK_CONDITION_ID);
    }

    @Test
    @Transactional
    public void createMTicketQuestStageWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = mTicketQuestStageRepository.findAll().size();

        // Create the MTicketQuestStage with an existing ID
        mTicketQuestStage.setId(1L);
        MTicketQuestStageDTO mTicketQuestStageDTO = mTicketQuestStageMapper.toDto(mTicketQuestStage);

        // An entity with an existing ID cannot be created, so this API call must fail
        restMTicketQuestStageMockMvc.perform(post("/api/m-ticket-quest-stages")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mTicketQuestStageDTO)))
            .andExpect(status().isBadRequest());

        // Validate the MTicketQuestStage in the database
        List<MTicketQuestStage> mTicketQuestStageList = mTicketQuestStageRepository.findAll();
        assertThat(mTicketQuestStageList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkWorldIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = mTicketQuestStageRepository.findAll().size();
        // set the field null
        mTicketQuestStage.setWorldId(null);

        // Create the MTicketQuestStage, which fails.
        MTicketQuestStageDTO mTicketQuestStageDTO = mTicketQuestStageMapper.toDto(mTicketQuestStage);

        restMTicketQuestStageMockMvc.perform(post("/api/m-ticket-quest-stages")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mTicketQuestStageDTO)))
            .andExpect(status().isBadRequest());

        List<MTicketQuestStage> mTicketQuestStageList = mTicketQuestStageRepository.findAll();
        assertThat(mTicketQuestStageList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkNumberIsRequired() throws Exception {
        int databaseSizeBeforeTest = mTicketQuestStageRepository.findAll().size();
        // set the field null
        mTicketQuestStage.setNumber(null);

        // Create the MTicketQuestStage, which fails.
        MTicketQuestStageDTO mTicketQuestStageDTO = mTicketQuestStageMapper.toDto(mTicketQuestStage);

        restMTicketQuestStageMockMvc.perform(post("/api/m-ticket-quest-stages")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mTicketQuestStageDTO)))
            .andExpect(status().isBadRequest());

        List<MTicketQuestStage> mTicketQuestStageList = mTicketQuestStageRepository.findAll();
        assertThat(mTicketQuestStageList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkTicketIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = mTicketQuestStageRepository.findAll().size();
        // set the field null
        mTicketQuestStage.setTicketId(null);

        // Create the MTicketQuestStage, which fails.
        MTicketQuestStageDTO mTicketQuestStageDTO = mTicketQuestStageMapper.toDto(mTicketQuestStage);

        restMTicketQuestStageMockMvc.perform(post("/api/m-ticket-quest-stages")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mTicketQuestStageDTO)))
            .andExpect(status().isBadRequest());

        List<MTicketQuestStage> mTicketQuestStageList = mTicketQuestStageRepository.findAll();
        assertThat(mTicketQuestStageList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkTicketAmountIsRequired() throws Exception {
        int databaseSizeBeforeTest = mTicketQuestStageRepository.findAll().size();
        // set the field null
        mTicketQuestStage.setTicketAmount(null);

        // Create the MTicketQuestStage, which fails.
        MTicketQuestStageDTO mTicketQuestStageDTO = mTicketQuestStageMapper.toDto(mTicketQuestStage);

        restMTicketQuestStageMockMvc.perform(post("/api/m-ticket-quest-stages")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mTicketQuestStageDTO)))
            .andExpect(status().isBadRequest());

        List<MTicketQuestStage> mTicketQuestStageList = mTicketQuestStageRepository.findAll();
        assertThat(mTicketQuestStageList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkDifficultyIsRequired() throws Exception {
        int databaseSizeBeforeTest = mTicketQuestStageRepository.findAll().size();
        // set the field null
        mTicketQuestStage.setDifficulty(null);

        // Create the MTicketQuestStage, which fails.
        MTicketQuestStageDTO mTicketQuestStageDTO = mTicketQuestStageMapper.toDto(mTicketQuestStage);

        restMTicketQuestStageMockMvc.perform(post("/api/m-ticket-quest-stages")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mTicketQuestStageDTO)))
            .andExpect(status().isBadRequest());

        List<MTicketQuestStage> mTicketQuestStageList = mTicketQuestStageRepository.findAll();
        assertThat(mTicketQuestStageList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkStageUnlockPatternIsRequired() throws Exception {
        int databaseSizeBeforeTest = mTicketQuestStageRepository.findAll().size();
        // set the field null
        mTicketQuestStage.setStageUnlockPattern(null);

        // Create the MTicketQuestStage, which fails.
        MTicketQuestStageDTO mTicketQuestStageDTO = mTicketQuestStageMapper.toDto(mTicketQuestStage);

        restMTicketQuestStageMockMvc.perform(post("/api/m-ticket-quest-stages")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mTicketQuestStageDTO)))
            .andExpect(status().isBadRequest());

        List<MTicketQuestStage> mTicketQuestStageList = mTicketQuestStageRepository.findAll();
        assertThat(mTicketQuestStageList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkStoryOnlyIsRequired() throws Exception {
        int databaseSizeBeforeTest = mTicketQuestStageRepository.findAll().size();
        // set the field null
        mTicketQuestStage.setStoryOnly(null);

        // Create the MTicketQuestStage, which fails.
        MTicketQuestStageDTO mTicketQuestStageDTO = mTicketQuestStageMapper.toDto(mTicketQuestStage);

        restMTicketQuestStageMockMvc.perform(post("/api/m-ticket-quest-stages")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mTicketQuestStageDTO)))
            .andExpect(status().isBadRequest());

        List<MTicketQuestStage> mTicketQuestStageList = mTicketQuestStageRepository.findAll();
        assertThat(mTicketQuestStageList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkFirstHalfNpcDeckIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = mTicketQuestStageRepository.findAll().size();
        // set the field null
        mTicketQuestStage.setFirstHalfNpcDeckId(null);

        // Create the MTicketQuestStage, which fails.
        MTicketQuestStageDTO mTicketQuestStageDTO = mTicketQuestStageMapper.toDto(mTicketQuestStage);

        restMTicketQuestStageMockMvc.perform(post("/api/m-ticket-quest-stages")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mTicketQuestStageDTO)))
            .andExpect(status().isBadRequest());

        List<MTicketQuestStage> mTicketQuestStageList = mTicketQuestStageRepository.findAll();
        assertThat(mTicketQuestStageList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkFirstHalfEnvironmentIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = mTicketQuestStageRepository.findAll().size();
        // set the field null
        mTicketQuestStage.setFirstHalfEnvironmentId(null);

        // Create the MTicketQuestStage, which fails.
        MTicketQuestStageDTO mTicketQuestStageDTO = mTicketQuestStageMapper.toDto(mTicketQuestStage);

        restMTicketQuestStageMockMvc.perform(post("/api/m-ticket-quest-stages")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mTicketQuestStageDTO)))
            .andExpect(status().isBadRequest());

        List<MTicketQuestStage> mTicketQuestStageList = mTicketQuestStageRepository.findAll();
        assertThat(mTicketQuestStageList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkSecondHalfNpcDeckIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = mTicketQuestStageRepository.findAll().size();
        // set the field null
        mTicketQuestStage.setSecondHalfNpcDeckId(null);

        // Create the MTicketQuestStage, which fails.
        MTicketQuestStageDTO mTicketQuestStageDTO = mTicketQuestStageMapper.toDto(mTicketQuestStage);

        restMTicketQuestStageMockMvc.perform(post("/api/m-ticket-quest-stages")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mTicketQuestStageDTO)))
            .andExpect(status().isBadRequest());

        List<MTicketQuestStage> mTicketQuestStageList = mTicketQuestStageRepository.findAll();
        assertThat(mTicketQuestStageList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkSecondHalfEnvironmentIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = mTicketQuestStageRepository.findAll().size();
        // set the field null
        mTicketQuestStage.setSecondHalfEnvironmentId(null);

        // Create the MTicketQuestStage, which fails.
        MTicketQuestStageDTO mTicketQuestStageDTO = mTicketQuestStageMapper.toDto(mTicketQuestStage);

        restMTicketQuestStageMockMvc.perform(post("/api/m-ticket-quest-stages")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mTicketQuestStageDTO)))
            .andExpect(status().isBadRequest());

        List<MTicketQuestStage> mTicketQuestStageList = mTicketQuestStageRepository.findAll();
        assertThat(mTicketQuestStageList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkExtraFirstHalfNpcDeckIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = mTicketQuestStageRepository.findAll().size();
        // set the field null
        mTicketQuestStage.setExtraFirstHalfNpcDeckId(null);

        // Create the MTicketQuestStage, which fails.
        MTicketQuestStageDTO mTicketQuestStageDTO = mTicketQuestStageMapper.toDto(mTicketQuestStage);

        restMTicketQuestStageMockMvc.perform(post("/api/m-ticket-quest-stages")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mTicketQuestStageDTO)))
            .andExpect(status().isBadRequest());

        List<MTicketQuestStage> mTicketQuestStageList = mTicketQuestStageRepository.findAll();
        assertThat(mTicketQuestStageList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkExtraSecondHalfNpcDeckIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = mTicketQuestStageRepository.findAll().size();
        // set the field null
        mTicketQuestStage.setExtraSecondHalfNpcDeckId(null);

        // Create the MTicketQuestStage, which fails.
        MTicketQuestStageDTO mTicketQuestStageDTO = mTicketQuestStageMapper.toDto(mTicketQuestStage);

        restMTicketQuestStageMockMvc.perform(post("/api/m-ticket-quest-stages")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mTicketQuestStageDTO)))
            .andExpect(status().isBadRequest());

        List<MTicketQuestStage> mTicketQuestStageList = mTicketQuestStageRepository.findAll();
        assertThat(mTicketQuestStageList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkConsumeApIsRequired() throws Exception {
        int databaseSizeBeforeTest = mTicketQuestStageRepository.findAll().size();
        // set the field null
        mTicketQuestStage.setConsumeAp(null);

        // Create the MTicketQuestStage, which fails.
        MTicketQuestStageDTO mTicketQuestStageDTO = mTicketQuestStageMapper.toDto(mTicketQuestStage);

        restMTicketQuestStageMockMvc.perform(post("/api/m-ticket-quest-stages")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mTicketQuestStageDTO)))
            .andExpect(status().isBadRequest());

        List<MTicketQuestStage> mTicketQuestStageList = mTicketQuestStageRepository.findAll();
        assertThat(mTicketQuestStageList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkKickOffTypeIsRequired() throws Exception {
        int databaseSizeBeforeTest = mTicketQuestStageRepository.findAll().size();
        // set the field null
        mTicketQuestStage.setKickOffType(null);

        // Create the MTicketQuestStage, which fails.
        MTicketQuestStageDTO mTicketQuestStageDTO = mTicketQuestStageMapper.toDto(mTicketQuestStage);

        restMTicketQuestStageMockMvc.perform(post("/api/m-ticket-quest-stages")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mTicketQuestStageDTO)))
            .andExpect(status().isBadRequest());

        List<MTicketQuestStage> mTicketQuestStageList = mTicketQuestStageRepository.findAll();
        assertThat(mTicketQuestStageList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkMatchMinuteIsRequired() throws Exception {
        int databaseSizeBeforeTest = mTicketQuestStageRepository.findAll().size();
        // set the field null
        mTicketQuestStage.setMatchMinute(null);

        // Create the MTicketQuestStage, which fails.
        MTicketQuestStageDTO mTicketQuestStageDTO = mTicketQuestStageMapper.toDto(mTicketQuestStage);

        restMTicketQuestStageMockMvc.perform(post("/api/m-ticket-quest-stages")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mTicketQuestStageDTO)))
            .andExpect(status().isBadRequest());

        List<MTicketQuestStage> mTicketQuestStageList = mTicketQuestStageRepository.findAll();
        assertThat(mTicketQuestStageList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkEnableExtraIsRequired() throws Exception {
        int databaseSizeBeforeTest = mTicketQuestStageRepository.findAll().size();
        // set the field null
        mTicketQuestStage.setEnableExtra(null);

        // Create the MTicketQuestStage, which fails.
        MTicketQuestStageDTO mTicketQuestStageDTO = mTicketQuestStageMapper.toDto(mTicketQuestStage);

        restMTicketQuestStageMockMvc.perform(post("/api/m-ticket-quest-stages")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mTicketQuestStageDTO)))
            .andExpect(status().isBadRequest());

        List<MTicketQuestStage> mTicketQuestStageList = mTicketQuestStageRepository.findAll();
        assertThat(mTicketQuestStageList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkEnablePkIsRequired() throws Exception {
        int databaseSizeBeforeTest = mTicketQuestStageRepository.findAll().size();
        // set the field null
        mTicketQuestStage.setEnablePk(null);

        // Create the MTicketQuestStage, which fails.
        MTicketQuestStageDTO mTicketQuestStageDTO = mTicketQuestStageMapper.toDto(mTicketQuestStage);

        restMTicketQuestStageMockMvc.perform(post("/api/m-ticket-quest-stages")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mTicketQuestStageDTO)))
            .andExpect(status().isBadRequest());

        List<MTicketQuestStage> mTicketQuestStageList = mTicketQuestStageRepository.findAll();
        assertThat(mTicketQuestStageList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkAiLevelIsRequired() throws Exception {
        int databaseSizeBeforeTest = mTicketQuestStageRepository.findAll().size();
        // set the field null
        mTicketQuestStage.setAiLevel(null);

        // Create the MTicketQuestStage, which fails.
        MTicketQuestStageDTO mTicketQuestStageDTO = mTicketQuestStageMapper.toDto(mTicketQuestStage);

        restMTicketQuestStageMockMvc.perform(post("/api/m-ticket-quest-stages")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mTicketQuestStageDTO)))
            .andExpect(status().isBadRequest());

        List<MTicketQuestStage> mTicketQuestStageList = mTicketQuestStageRepository.findAll();
        assertThat(mTicketQuestStageList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkStartAtSecondHalfIsRequired() throws Exception {
        int databaseSizeBeforeTest = mTicketQuestStageRepository.findAll().size();
        // set the field null
        mTicketQuestStage.setStartAtSecondHalf(null);

        // Create the MTicketQuestStage, which fails.
        MTicketQuestStageDTO mTicketQuestStageDTO = mTicketQuestStageMapper.toDto(mTicketQuestStage);

        restMTicketQuestStageMockMvc.perform(post("/api/m-ticket-quest-stages")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mTicketQuestStageDTO)))
            .andExpect(status().isBadRequest());

        List<MTicketQuestStage> mTicketQuestStageList = mTicketQuestStageRepository.findAll();
        assertThat(mTicketQuestStageList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkStartScoreIsRequired() throws Exception {
        int databaseSizeBeforeTest = mTicketQuestStageRepository.findAll().size();
        // set the field null
        mTicketQuestStage.setStartScore(null);

        // Create the MTicketQuestStage, which fails.
        MTicketQuestStageDTO mTicketQuestStageDTO = mTicketQuestStageMapper.toDto(mTicketQuestStage);

        restMTicketQuestStageMockMvc.perform(post("/api/m-ticket-quest-stages")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mTicketQuestStageDTO)))
            .andExpect(status().isBadRequest());

        List<MTicketQuestStage> mTicketQuestStageList = mTicketQuestStageRepository.findAll();
        assertThat(mTicketQuestStageList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkStartScoreOpponentIsRequired() throws Exception {
        int databaseSizeBeforeTest = mTicketQuestStageRepository.findAll().size();
        // set the field null
        mTicketQuestStage.setStartScoreOpponent(null);

        // Create the MTicketQuestStage, which fails.
        MTicketQuestStageDTO mTicketQuestStageDTO = mTicketQuestStageMapper.toDto(mTicketQuestStage);

        restMTicketQuestStageMockMvc.perform(post("/api/m-ticket-quest-stages")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mTicketQuestStageDTO)))
            .andExpect(status().isBadRequest());

        List<MTicketQuestStage> mTicketQuestStageList = mTicketQuestStageRepository.findAll();
        assertThat(mTicketQuestStageList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllMTicketQuestStages() throws Exception {
        // Initialize the database
        mTicketQuestStageRepository.saveAndFlush(mTicketQuestStage);

        // Get all the mTicketQuestStageList
        restMTicketQuestStageMockMvc.perform(get("/api/m-ticket-quest-stages?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(mTicketQuestStage.getId().intValue())))
            .andExpect(jsonPath("$.[*].worldId").value(hasItem(DEFAULT_WORLD_ID)))
            .andExpect(jsonPath("$.[*].number").value(hasItem(DEFAULT_NUMBER)))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME.toString())))
            .andExpect(jsonPath("$.[*].ticketId").value(hasItem(DEFAULT_TICKET_ID)))
            .andExpect(jsonPath("$.[*].ticketAmount").value(hasItem(DEFAULT_TICKET_AMOUNT)))
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
    public void getMTicketQuestStage() throws Exception {
        // Initialize the database
        mTicketQuestStageRepository.saveAndFlush(mTicketQuestStage);

        // Get the mTicketQuestStage
        restMTicketQuestStageMockMvc.perform(get("/api/m-ticket-quest-stages/{id}", mTicketQuestStage.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(mTicketQuestStage.getId().intValue()))
            .andExpect(jsonPath("$.worldId").value(DEFAULT_WORLD_ID))
            .andExpect(jsonPath("$.number").value(DEFAULT_NUMBER))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME.toString()))
            .andExpect(jsonPath("$.ticketId").value(DEFAULT_TICKET_ID))
            .andExpect(jsonPath("$.ticketAmount").value(DEFAULT_TICKET_AMOUNT))
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
    public void getAllMTicketQuestStagesByWorldIdIsEqualToSomething() throws Exception {
        // Initialize the database
        mTicketQuestStageRepository.saveAndFlush(mTicketQuestStage);

        // Get all the mTicketQuestStageList where worldId equals to DEFAULT_WORLD_ID
        defaultMTicketQuestStageShouldBeFound("worldId.equals=" + DEFAULT_WORLD_ID);

        // Get all the mTicketQuestStageList where worldId equals to UPDATED_WORLD_ID
        defaultMTicketQuestStageShouldNotBeFound("worldId.equals=" + UPDATED_WORLD_ID);
    }

    @Test
    @Transactional
    public void getAllMTicketQuestStagesByWorldIdIsInShouldWork() throws Exception {
        // Initialize the database
        mTicketQuestStageRepository.saveAndFlush(mTicketQuestStage);

        // Get all the mTicketQuestStageList where worldId in DEFAULT_WORLD_ID or UPDATED_WORLD_ID
        defaultMTicketQuestStageShouldBeFound("worldId.in=" + DEFAULT_WORLD_ID + "," + UPDATED_WORLD_ID);

        // Get all the mTicketQuestStageList where worldId equals to UPDATED_WORLD_ID
        defaultMTicketQuestStageShouldNotBeFound("worldId.in=" + UPDATED_WORLD_ID);
    }

    @Test
    @Transactional
    public void getAllMTicketQuestStagesByWorldIdIsNullOrNotNull() throws Exception {
        // Initialize the database
        mTicketQuestStageRepository.saveAndFlush(mTicketQuestStage);

        // Get all the mTicketQuestStageList where worldId is not null
        defaultMTicketQuestStageShouldBeFound("worldId.specified=true");

        // Get all the mTicketQuestStageList where worldId is null
        defaultMTicketQuestStageShouldNotBeFound("worldId.specified=false");
    }

    @Test
    @Transactional
    public void getAllMTicketQuestStagesByWorldIdIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mTicketQuestStageRepository.saveAndFlush(mTicketQuestStage);

        // Get all the mTicketQuestStageList where worldId greater than or equals to DEFAULT_WORLD_ID
        defaultMTicketQuestStageShouldBeFound("worldId.greaterOrEqualThan=" + DEFAULT_WORLD_ID);

        // Get all the mTicketQuestStageList where worldId greater than or equals to UPDATED_WORLD_ID
        defaultMTicketQuestStageShouldNotBeFound("worldId.greaterOrEqualThan=" + UPDATED_WORLD_ID);
    }

    @Test
    @Transactional
    public void getAllMTicketQuestStagesByWorldIdIsLessThanSomething() throws Exception {
        // Initialize the database
        mTicketQuestStageRepository.saveAndFlush(mTicketQuestStage);

        // Get all the mTicketQuestStageList where worldId less than or equals to DEFAULT_WORLD_ID
        defaultMTicketQuestStageShouldNotBeFound("worldId.lessThan=" + DEFAULT_WORLD_ID);

        // Get all the mTicketQuestStageList where worldId less than or equals to UPDATED_WORLD_ID
        defaultMTicketQuestStageShouldBeFound("worldId.lessThan=" + UPDATED_WORLD_ID);
    }


    @Test
    @Transactional
    public void getAllMTicketQuestStagesByNumberIsEqualToSomething() throws Exception {
        // Initialize the database
        mTicketQuestStageRepository.saveAndFlush(mTicketQuestStage);

        // Get all the mTicketQuestStageList where number equals to DEFAULT_NUMBER
        defaultMTicketQuestStageShouldBeFound("number.equals=" + DEFAULT_NUMBER);

        // Get all the mTicketQuestStageList where number equals to UPDATED_NUMBER
        defaultMTicketQuestStageShouldNotBeFound("number.equals=" + UPDATED_NUMBER);
    }

    @Test
    @Transactional
    public void getAllMTicketQuestStagesByNumberIsInShouldWork() throws Exception {
        // Initialize the database
        mTicketQuestStageRepository.saveAndFlush(mTicketQuestStage);

        // Get all the mTicketQuestStageList where number in DEFAULT_NUMBER or UPDATED_NUMBER
        defaultMTicketQuestStageShouldBeFound("number.in=" + DEFAULT_NUMBER + "," + UPDATED_NUMBER);

        // Get all the mTicketQuestStageList where number equals to UPDATED_NUMBER
        defaultMTicketQuestStageShouldNotBeFound("number.in=" + UPDATED_NUMBER);
    }

    @Test
    @Transactional
    public void getAllMTicketQuestStagesByNumberIsNullOrNotNull() throws Exception {
        // Initialize the database
        mTicketQuestStageRepository.saveAndFlush(mTicketQuestStage);

        // Get all the mTicketQuestStageList where number is not null
        defaultMTicketQuestStageShouldBeFound("number.specified=true");

        // Get all the mTicketQuestStageList where number is null
        defaultMTicketQuestStageShouldNotBeFound("number.specified=false");
    }

    @Test
    @Transactional
    public void getAllMTicketQuestStagesByNumberIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mTicketQuestStageRepository.saveAndFlush(mTicketQuestStage);

        // Get all the mTicketQuestStageList where number greater than or equals to DEFAULT_NUMBER
        defaultMTicketQuestStageShouldBeFound("number.greaterOrEqualThan=" + DEFAULT_NUMBER);

        // Get all the mTicketQuestStageList where number greater than or equals to UPDATED_NUMBER
        defaultMTicketQuestStageShouldNotBeFound("number.greaterOrEqualThan=" + UPDATED_NUMBER);
    }

    @Test
    @Transactional
    public void getAllMTicketQuestStagesByNumberIsLessThanSomething() throws Exception {
        // Initialize the database
        mTicketQuestStageRepository.saveAndFlush(mTicketQuestStage);

        // Get all the mTicketQuestStageList where number less than or equals to DEFAULT_NUMBER
        defaultMTicketQuestStageShouldNotBeFound("number.lessThan=" + DEFAULT_NUMBER);

        // Get all the mTicketQuestStageList where number less than or equals to UPDATED_NUMBER
        defaultMTicketQuestStageShouldBeFound("number.lessThan=" + UPDATED_NUMBER);
    }


    @Test
    @Transactional
    public void getAllMTicketQuestStagesByTicketIdIsEqualToSomething() throws Exception {
        // Initialize the database
        mTicketQuestStageRepository.saveAndFlush(mTicketQuestStage);

        // Get all the mTicketQuestStageList where ticketId equals to DEFAULT_TICKET_ID
        defaultMTicketQuestStageShouldBeFound("ticketId.equals=" + DEFAULT_TICKET_ID);

        // Get all the mTicketQuestStageList where ticketId equals to UPDATED_TICKET_ID
        defaultMTicketQuestStageShouldNotBeFound("ticketId.equals=" + UPDATED_TICKET_ID);
    }

    @Test
    @Transactional
    public void getAllMTicketQuestStagesByTicketIdIsInShouldWork() throws Exception {
        // Initialize the database
        mTicketQuestStageRepository.saveAndFlush(mTicketQuestStage);

        // Get all the mTicketQuestStageList where ticketId in DEFAULT_TICKET_ID or UPDATED_TICKET_ID
        defaultMTicketQuestStageShouldBeFound("ticketId.in=" + DEFAULT_TICKET_ID + "," + UPDATED_TICKET_ID);

        // Get all the mTicketQuestStageList where ticketId equals to UPDATED_TICKET_ID
        defaultMTicketQuestStageShouldNotBeFound("ticketId.in=" + UPDATED_TICKET_ID);
    }

    @Test
    @Transactional
    public void getAllMTicketQuestStagesByTicketIdIsNullOrNotNull() throws Exception {
        // Initialize the database
        mTicketQuestStageRepository.saveAndFlush(mTicketQuestStage);

        // Get all the mTicketQuestStageList where ticketId is not null
        defaultMTicketQuestStageShouldBeFound("ticketId.specified=true");

        // Get all the mTicketQuestStageList where ticketId is null
        defaultMTicketQuestStageShouldNotBeFound("ticketId.specified=false");
    }

    @Test
    @Transactional
    public void getAllMTicketQuestStagesByTicketIdIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mTicketQuestStageRepository.saveAndFlush(mTicketQuestStage);

        // Get all the mTicketQuestStageList where ticketId greater than or equals to DEFAULT_TICKET_ID
        defaultMTicketQuestStageShouldBeFound("ticketId.greaterOrEqualThan=" + DEFAULT_TICKET_ID);

        // Get all the mTicketQuestStageList where ticketId greater than or equals to UPDATED_TICKET_ID
        defaultMTicketQuestStageShouldNotBeFound("ticketId.greaterOrEqualThan=" + UPDATED_TICKET_ID);
    }

    @Test
    @Transactional
    public void getAllMTicketQuestStagesByTicketIdIsLessThanSomething() throws Exception {
        // Initialize the database
        mTicketQuestStageRepository.saveAndFlush(mTicketQuestStage);

        // Get all the mTicketQuestStageList where ticketId less than or equals to DEFAULT_TICKET_ID
        defaultMTicketQuestStageShouldNotBeFound("ticketId.lessThan=" + DEFAULT_TICKET_ID);

        // Get all the mTicketQuestStageList where ticketId less than or equals to UPDATED_TICKET_ID
        defaultMTicketQuestStageShouldBeFound("ticketId.lessThan=" + UPDATED_TICKET_ID);
    }


    @Test
    @Transactional
    public void getAllMTicketQuestStagesByTicketAmountIsEqualToSomething() throws Exception {
        // Initialize the database
        mTicketQuestStageRepository.saveAndFlush(mTicketQuestStage);

        // Get all the mTicketQuestStageList where ticketAmount equals to DEFAULT_TICKET_AMOUNT
        defaultMTicketQuestStageShouldBeFound("ticketAmount.equals=" + DEFAULT_TICKET_AMOUNT);

        // Get all the mTicketQuestStageList where ticketAmount equals to UPDATED_TICKET_AMOUNT
        defaultMTicketQuestStageShouldNotBeFound("ticketAmount.equals=" + UPDATED_TICKET_AMOUNT);
    }

    @Test
    @Transactional
    public void getAllMTicketQuestStagesByTicketAmountIsInShouldWork() throws Exception {
        // Initialize the database
        mTicketQuestStageRepository.saveAndFlush(mTicketQuestStage);

        // Get all the mTicketQuestStageList where ticketAmount in DEFAULT_TICKET_AMOUNT or UPDATED_TICKET_AMOUNT
        defaultMTicketQuestStageShouldBeFound("ticketAmount.in=" + DEFAULT_TICKET_AMOUNT + "," + UPDATED_TICKET_AMOUNT);

        // Get all the mTicketQuestStageList where ticketAmount equals to UPDATED_TICKET_AMOUNT
        defaultMTicketQuestStageShouldNotBeFound("ticketAmount.in=" + UPDATED_TICKET_AMOUNT);
    }

    @Test
    @Transactional
    public void getAllMTicketQuestStagesByTicketAmountIsNullOrNotNull() throws Exception {
        // Initialize the database
        mTicketQuestStageRepository.saveAndFlush(mTicketQuestStage);

        // Get all the mTicketQuestStageList where ticketAmount is not null
        defaultMTicketQuestStageShouldBeFound("ticketAmount.specified=true");

        // Get all the mTicketQuestStageList where ticketAmount is null
        defaultMTicketQuestStageShouldNotBeFound("ticketAmount.specified=false");
    }

    @Test
    @Transactional
    public void getAllMTicketQuestStagesByTicketAmountIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mTicketQuestStageRepository.saveAndFlush(mTicketQuestStage);

        // Get all the mTicketQuestStageList where ticketAmount greater than or equals to DEFAULT_TICKET_AMOUNT
        defaultMTicketQuestStageShouldBeFound("ticketAmount.greaterOrEqualThan=" + DEFAULT_TICKET_AMOUNT);

        // Get all the mTicketQuestStageList where ticketAmount greater than or equals to UPDATED_TICKET_AMOUNT
        defaultMTicketQuestStageShouldNotBeFound("ticketAmount.greaterOrEqualThan=" + UPDATED_TICKET_AMOUNT);
    }

    @Test
    @Transactional
    public void getAllMTicketQuestStagesByTicketAmountIsLessThanSomething() throws Exception {
        // Initialize the database
        mTicketQuestStageRepository.saveAndFlush(mTicketQuestStage);

        // Get all the mTicketQuestStageList where ticketAmount less than or equals to DEFAULT_TICKET_AMOUNT
        defaultMTicketQuestStageShouldNotBeFound("ticketAmount.lessThan=" + DEFAULT_TICKET_AMOUNT);

        // Get all the mTicketQuestStageList where ticketAmount less than or equals to UPDATED_TICKET_AMOUNT
        defaultMTicketQuestStageShouldBeFound("ticketAmount.lessThan=" + UPDATED_TICKET_AMOUNT);
    }


    @Test
    @Transactional
    public void getAllMTicketQuestStagesByDifficultyIsEqualToSomething() throws Exception {
        // Initialize the database
        mTicketQuestStageRepository.saveAndFlush(mTicketQuestStage);

        // Get all the mTicketQuestStageList where difficulty equals to DEFAULT_DIFFICULTY
        defaultMTicketQuestStageShouldBeFound("difficulty.equals=" + DEFAULT_DIFFICULTY);

        // Get all the mTicketQuestStageList where difficulty equals to UPDATED_DIFFICULTY
        defaultMTicketQuestStageShouldNotBeFound("difficulty.equals=" + UPDATED_DIFFICULTY);
    }

    @Test
    @Transactional
    public void getAllMTicketQuestStagesByDifficultyIsInShouldWork() throws Exception {
        // Initialize the database
        mTicketQuestStageRepository.saveAndFlush(mTicketQuestStage);

        // Get all the mTicketQuestStageList where difficulty in DEFAULT_DIFFICULTY or UPDATED_DIFFICULTY
        defaultMTicketQuestStageShouldBeFound("difficulty.in=" + DEFAULT_DIFFICULTY + "," + UPDATED_DIFFICULTY);

        // Get all the mTicketQuestStageList where difficulty equals to UPDATED_DIFFICULTY
        defaultMTicketQuestStageShouldNotBeFound("difficulty.in=" + UPDATED_DIFFICULTY);
    }

    @Test
    @Transactional
    public void getAllMTicketQuestStagesByDifficultyIsNullOrNotNull() throws Exception {
        // Initialize the database
        mTicketQuestStageRepository.saveAndFlush(mTicketQuestStage);

        // Get all the mTicketQuestStageList where difficulty is not null
        defaultMTicketQuestStageShouldBeFound("difficulty.specified=true");

        // Get all the mTicketQuestStageList where difficulty is null
        defaultMTicketQuestStageShouldNotBeFound("difficulty.specified=false");
    }

    @Test
    @Transactional
    public void getAllMTicketQuestStagesByDifficultyIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mTicketQuestStageRepository.saveAndFlush(mTicketQuestStage);

        // Get all the mTicketQuestStageList where difficulty greater than or equals to DEFAULT_DIFFICULTY
        defaultMTicketQuestStageShouldBeFound("difficulty.greaterOrEqualThan=" + DEFAULT_DIFFICULTY);

        // Get all the mTicketQuestStageList where difficulty greater than or equals to UPDATED_DIFFICULTY
        defaultMTicketQuestStageShouldNotBeFound("difficulty.greaterOrEqualThan=" + UPDATED_DIFFICULTY);
    }

    @Test
    @Transactional
    public void getAllMTicketQuestStagesByDifficultyIsLessThanSomething() throws Exception {
        // Initialize the database
        mTicketQuestStageRepository.saveAndFlush(mTicketQuestStage);

        // Get all the mTicketQuestStageList where difficulty less than or equals to DEFAULT_DIFFICULTY
        defaultMTicketQuestStageShouldNotBeFound("difficulty.lessThan=" + DEFAULT_DIFFICULTY);

        // Get all the mTicketQuestStageList where difficulty less than or equals to UPDATED_DIFFICULTY
        defaultMTicketQuestStageShouldBeFound("difficulty.lessThan=" + UPDATED_DIFFICULTY);
    }


    @Test
    @Transactional
    public void getAllMTicketQuestStagesByStageUnlockPatternIsEqualToSomething() throws Exception {
        // Initialize the database
        mTicketQuestStageRepository.saveAndFlush(mTicketQuestStage);

        // Get all the mTicketQuestStageList where stageUnlockPattern equals to DEFAULT_STAGE_UNLOCK_PATTERN
        defaultMTicketQuestStageShouldBeFound("stageUnlockPattern.equals=" + DEFAULT_STAGE_UNLOCK_PATTERN);

        // Get all the mTicketQuestStageList where stageUnlockPattern equals to UPDATED_STAGE_UNLOCK_PATTERN
        defaultMTicketQuestStageShouldNotBeFound("stageUnlockPattern.equals=" + UPDATED_STAGE_UNLOCK_PATTERN);
    }

    @Test
    @Transactional
    public void getAllMTicketQuestStagesByStageUnlockPatternIsInShouldWork() throws Exception {
        // Initialize the database
        mTicketQuestStageRepository.saveAndFlush(mTicketQuestStage);

        // Get all the mTicketQuestStageList where stageUnlockPattern in DEFAULT_STAGE_UNLOCK_PATTERN or UPDATED_STAGE_UNLOCK_PATTERN
        defaultMTicketQuestStageShouldBeFound("stageUnlockPattern.in=" + DEFAULT_STAGE_UNLOCK_PATTERN + "," + UPDATED_STAGE_UNLOCK_PATTERN);

        // Get all the mTicketQuestStageList where stageUnlockPattern equals to UPDATED_STAGE_UNLOCK_PATTERN
        defaultMTicketQuestStageShouldNotBeFound("stageUnlockPattern.in=" + UPDATED_STAGE_UNLOCK_PATTERN);
    }

    @Test
    @Transactional
    public void getAllMTicketQuestStagesByStageUnlockPatternIsNullOrNotNull() throws Exception {
        // Initialize the database
        mTicketQuestStageRepository.saveAndFlush(mTicketQuestStage);

        // Get all the mTicketQuestStageList where stageUnlockPattern is not null
        defaultMTicketQuestStageShouldBeFound("stageUnlockPattern.specified=true");

        // Get all the mTicketQuestStageList where stageUnlockPattern is null
        defaultMTicketQuestStageShouldNotBeFound("stageUnlockPattern.specified=false");
    }

    @Test
    @Transactional
    public void getAllMTicketQuestStagesByStageUnlockPatternIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mTicketQuestStageRepository.saveAndFlush(mTicketQuestStage);

        // Get all the mTicketQuestStageList where stageUnlockPattern greater than or equals to DEFAULT_STAGE_UNLOCK_PATTERN
        defaultMTicketQuestStageShouldBeFound("stageUnlockPattern.greaterOrEqualThan=" + DEFAULT_STAGE_UNLOCK_PATTERN);

        // Get all the mTicketQuestStageList where stageUnlockPattern greater than or equals to UPDATED_STAGE_UNLOCK_PATTERN
        defaultMTicketQuestStageShouldNotBeFound("stageUnlockPattern.greaterOrEqualThan=" + UPDATED_STAGE_UNLOCK_PATTERN);
    }

    @Test
    @Transactional
    public void getAllMTicketQuestStagesByStageUnlockPatternIsLessThanSomething() throws Exception {
        // Initialize the database
        mTicketQuestStageRepository.saveAndFlush(mTicketQuestStage);

        // Get all the mTicketQuestStageList where stageUnlockPattern less than or equals to DEFAULT_STAGE_UNLOCK_PATTERN
        defaultMTicketQuestStageShouldNotBeFound("stageUnlockPattern.lessThan=" + DEFAULT_STAGE_UNLOCK_PATTERN);

        // Get all the mTicketQuestStageList where stageUnlockPattern less than or equals to UPDATED_STAGE_UNLOCK_PATTERN
        defaultMTicketQuestStageShouldBeFound("stageUnlockPattern.lessThan=" + UPDATED_STAGE_UNLOCK_PATTERN);
    }


    @Test
    @Transactional
    public void getAllMTicketQuestStagesByStoryOnlyIsEqualToSomething() throws Exception {
        // Initialize the database
        mTicketQuestStageRepository.saveAndFlush(mTicketQuestStage);

        // Get all the mTicketQuestStageList where storyOnly equals to DEFAULT_STORY_ONLY
        defaultMTicketQuestStageShouldBeFound("storyOnly.equals=" + DEFAULT_STORY_ONLY);

        // Get all the mTicketQuestStageList where storyOnly equals to UPDATED_STORY_ONLY
        defaultMTicketQuestStageShouldNotBeFound("storyOnly.equals=" + UPDATED_STORY_ONLY);
    }

    @Test
    @Transactional
    public void getAllMTicketQuestStagesByStoryOnlyIsInShouldWork() throws Exception {
        // Initialize the database
        mTicketQuestStageRepository.saveAndFlush(mTicketQuestStage);

        // Get all the mTicketQuestStageList where storyOnly in DEFAULT_STORY_ONLY or UPDATED_STORY_ONLY
        defaultMTicketQuestStageShouldBeFound("storyOnly.in=" + DEFAULT_STORY_ONLY + "," + UPDATED_STORY_ONLY);

        // Get all the mTicketQuestStageList where storyOnly equals to UPDATED_STORY_ONLY
        defaultMTicketQuestStageShouldNotBeFound("storyOnly.in=" + UPDATED_STORY_ONLY);
    }

    @Test
    @Transactional
    public void getAllMTicketQuestStagesByStoryOnlyIsNullOrNotNull() throws Exception {
        // Initialize the database
        mTicketQuestStageRepository.saveAndFlush(mTicketQuestStage);

        // Get all the mTicketQuestStageList where storyOnly is not null
        defaultMTicketQuestStageShouldBeFound("storyOnly.specified=true");

        // Get all the mTicketQuestStageList where storyOnly is null
        defaultMTicketQuestStageShouldNotBeFound("storyOnly.specified=false");
    }

    @Test
    @Transactional
    public void getAllMTicketQuestStagesByStoryOnlyIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mTicketQuestStageRepository.saveAndFlush(mTicketQuestStage);

        // Get all the mTicketQuestStageList where storyOnly greater than or equals to DEFAULT_STORY_ONLY
        defaultMTicketQuestStageShouldBeFound("storyOnly.greaterOrEqualThan=" + DEFAULT_STORY_ONLY);

        // Get all the mTicketQuestStageList where storyOnly greater than or equals to UPDATED_STORY_ONLY
        defaultMTicketQuestStageShouldNotBeFound("storyOnly.greaterOrEqualThan=" + UPDATED_STORY_ONLY);
    }

    @Test
    @Transactional
    public void getAllMTicketQuestStagesByStoryOnlyIsLessThanSomething() throws Exception {
        // Initialize the database
        mTicketQuestStageRepository.saveAndFlush(mTicketQuestStage);

        // Get all the mTicketQuestStageList where storyOnly less than or equals to DEFAULT_STORY_ONLY
        defaultMTicketQuestStageShouldNotBeFound("storyOnly.lessThan=" + DEFAULT_STORY_ONLY);

        // Get all the mTicketQuestStageList where storyOnly less than or equals to UPDATED_STORY_ONLY
        defaultMTicketQuestStageShouldBeFound("storyOnly.lessThan=" + UPDATED_STORY_ONLY);
    }


    @Test
    @Transactional
    public void getAllMTicketQuestStagesByFirstHalfNpcDeckIdIsEqualToSomething() throws Exception {
        // Initialize the database
        mTicketQuestStageRepository.saveAndFlush(mTicketQuestStage);

        // Get all the mTicketQuestStageList where firstHalfNpcDeckId equals to DEFAULT_FIRST_HALF_NPC_DECK_ID
        defaultMTicketQuestStageShouldBeFound("firstHalfNpcDeckId.equals=" + DEFAULT_FIRST_HALF_NPC_DECK_ID);

        // Get all the mTicketQuestStageList where firstHalfNpcDeckId equals to UPDATED_FIRST_HALF_NPC_DECK_ID
        defaultMTicketQuestStageShouldNotBeFound("firstHalfNpcDeckId.equals=" + UPDATED_FIRST_HALF_NPC_DECK_ID);
    }

    @Test
    @Transactional
    public void getAllMTicketQuestStagesByFirstHalfNpcDeckIdIsInShouldWork() throws Exception {
        // Initialize the database
        mTicketQuestStageRepository.saveAndFlush(mTicketQuestStage);

        // Get all the mTicketQuestStageList where firstHalfNpcDeckId in DEFAULT_FIRST_HALF_NPC_DECK_ID or UPDATED_FIRST_HALF_NPC_DECK_ID
        defaultMTicketQuestStageShouldBeFound("firstHalfNpcDeckId.in=" + DEFAULT_FIRST_HALF_NPC_DECK_ID + "," + UPDATED_FIRST_HALF_NPC_DECK_ID);

        // Get all the mTicketQuestStageList where firstHalfNpcDeckId equals to UPDATED_FIRST_HALF_NPC_DECK_ID
        defaultMTicketQuestStageShouldNotBeFound("firstHalfNpcDeckId.in=" + UPDATED_FIRST_HALF_NPC_DECK_ID);
    }

    @Test
    @Transactional
    public void getAllMTicketQuestStagesByFirstHalfNpcDeckIdIsNullOrNotNull() throws Exception {
        // Initialize the database
        mTicketQuestStageRepository.saveAndFlush(mTicketQuestStage);

        // Get all the mTicketQuestStageList where firstHalfNpcDeckId is not null
        defaultMTicketQuestStageShouldBeFound("firstHalfNpcDeckId.specified=true");

        // Get all the mTicketQuestStageList where firstHalfNpcDeckId is null
        defaultMTicketQuestStageShouldNotBeFound("firstHalfNpcDeckId.specified=false");
    }

    @Test
    @Transactional
    public void getAllMTicketQuestStagesByFirstHalfNpcDeckIdIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mTicketQuestStageRepository.saveAndFlush(mTicketQuestStage);

        // Get all the mTicketQuestStageList where firstHalfNpcDeckId greater than or equals to DEFAULT_FIRST_HALF_NPC_DECK_ID
        defaultMTicketQuestStageShouldBeFound("firstHalfNpcDeckId.greaterOrEqualThan=" + DEFAULT_FIRST_HALF_NPC_DECK_ID);

        // Get all the mTicketQuestStageList where firstHalfNpcDeckId greater than or equals to UPDATED_FIRST_HALF_NPC_DECK_ID
        defaultMTicketQuestStageShouldNotBeFound("firstHalfNpcDeckId.greaterOrEqualThan=" + UPDATED_FIRST_HALF_NPC_DECK_ID);
    }

    @Test
    @Transactional
    public void getAllMTicketQuestStagesByFirstHalfNpcDeckIdIsLessThanSomething() throws Exception {
        // Initialize the database
        mTicketQuestStageRepository.saveAndFlush(mTicketQuestStage);

        // Get all the mTicketQuestStageList where firstHalfNpcDeckId less than or equals to DEFAULT_FIRST_HALF_NPC_DECK_ID
        defaultMTicketQuestStageShouldNotBeFound("firstHalfNpcDeckId.lessThan=" + DEFAULT_FIRST_HALF_NPC_DECK_ID);

        // Get all the mTicketQuestStageList where firstHalfNpcDeckId less than or equals to UPDATED_FIRST_HALF_NPC_DECK_ID
        defaultMTicketQuestStageShouldBeFound("firstHalfNpcDeckId.lessThan=" + UPDATED_FIRST_HALF_NPC_DECK_ID);
    }


    @Test
    @Transactional
    public void getAllMTicketQuestStagesByFirstHalfEnvironmentIdIsEqualToSomething() throws Exception {
        // Initialize the database
        mTicketQuestStageRepository.saveAndFlush(mTicketQuestStage);

        // Get all the mTicketQuestStageList where firstHalfEnvironmentId equals to DEFAULT_FIRST_HALF_ENVIRONMENT_ID
        defaultMTicketQuestStageShouldBeFound("firstHalfEnvironmentId.equals=" + DEFAULT_FIRST_HALF_ENVIRONMENT_ID);

        // Get all the mTicketQuestStageList where firstHalfEnvironmentId equals to UPDATED_FIRST_HALF_ENVIRONMENT_ID
        defaultMTicketQuestStageShouldNotBeFound("firstHalfEnvironmentId.equals=" + UPDATED_FIRST_HALF_ENVIRONMENT_ID);
    }

    @Test
    @Transactional
    public void getAllMTicketQuestStagesByFirstHalfEnvironmentIdIsInShouldWork() throws Exception {
        // Initialize the database
        mTicketQuestStageRepository.saveAndFlush(mTicketQuestStage);

        // Get all the mTicketQuestStageList where firstHalfEnvironmentId in DEFAULT_FIRST_HALF_ENVIRONMENT_ID or UPDATED_FIRST_HALF_ENVIRONMENT_ID
        defaultMTicketQuestStageShouldBeFound("firstHalfEnvironmentId.in=" + DEFAULT_FIRST_HALF_ENVIRONMENT_ID + "," + UPDATED_FIRST_HALF_ENVIRONMENT_ID);

        // Get all the mTicketQuestStageList where firstHalfEnvironmentId equals to UPDATED_FIRST_HALF_ENVIRONMENT_ID
        defaultMTicketQuestStageShouldNotBeFound("firstHalfEnvironmentId.in=" + UPDATED_FIRST_HALF_ENVIRONMENT_ID);
    }

    @Test
    @Transactional
    public void getAllMTicketQuestStagesByFirstHalfEnvironmentIdIsNullOrNotNull() throws Exception {
        // Initialize the database
        mTicketQuestStageRepository.saveAndFlush(mTicketQuestStage);

        // Get all the mTicketQuestStageList where firstHalfEnvironmentId is not null
        defaultMTicketQuestStageShouldBeFound("firstHalfEnvironmentId.specified=true");

        // Get all the mTicketQuestStageList where firstHalfEnvironmentId is null
        defaultMTicketQuestStageShouldNotBeFound("firstHalfEnvironmentId.specified=false");
    }

    @Test
    @Transactional
    public void getAllMTicketQuestStagesByFirstHalfEnvironmentIdIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mTicketQuestStageRepository.saveAndFlush(mTicketQuestStage);

        // Get all the mTicketQuestStageList where firstHalfEnvironmentId greater than or equals to DEFAULT_FIRST_HALF_ENVIRONMENT_ID
        defaultMTicketQuestStageShouldBeFound("firstHalfEnvironmentId.greaterOrEqualThan=" + DEFAULT_FIRST_HALF_ENVIRONMENT_ID);

        // Get all the mTicketQuestStageList where firstHalfEnvironmentId greater than or equals to UPDATED_FIRST_HALF_ENVIRONMENT_ID
        defaultMTicketQuestStageShouldNotBeFound("firstHalfEnvironmentId.greaterOrEqualThan=" + UPDATED_FIRST_HALF_ENVIRONMENT_ID);
    }

    @Test
    @Transactional
    public void getAllMTicketQuestStagesByFirstHalfEnvironmentIdIsLessThanSomething() throws Exception {
        // Initialize the database
        mTicketQuestStageRepository.saveAndFlush(mTicketQuestStage);

        // Get all the mTicketQuestStageList where firstHalfEnvironmentId less than or equals to DEFAULT_FIRST_HALF_ENVIRONMENT_ID
        defaultMTicketQuestStageShouldNotBeFound("firstHalfEnvironmentId.lessThan=" + DEFAULT_FIRST_HALF_ENVIRONMENT_ID);

        // Get all the mTicketQuestStageList where firstHalfEnvironmentId less than or equals to UPDATED_FIRST_HALF_ENVIRONMENT_ID
        defaultMTicketQuestStageShouldBeFound("firstHalfEnvironmentId.lessThan=" + UPDATED_FIRST_HALF_ENVIRONMENT_ID);
    }


    @Test
    @Transactional
    public void getAllMTicketQuestStagesBySecondHalfNpcDeckIdIsEqualToSomething() throws Exception {
        // Initialize the database
        mTicketQuestStageRepository.saveAndFlush(mTicketQuestStage);

        // Get all the mTicketQuestStageList where secondHalfNpcDeckId equals to DEFAULT_SECOND_HALF_NPC_DECK_ID
        defaultMTicketQuestStageShouldBeFound("secondHalfNpcDeckId.equals=" + DEFAULT_SECOND_HALF_NPC_DECK_ID);

        // Get all the mTicketQuestStageList where secondHalfNpcDeckId equals to UPDATED_SECOND_HALF_NPC_DECK_ID
        defaultMTicketQuestStageShouldNotBeFound("secondHalfNpcDeckId.equals=" + UPDATED_SECOND_HALF_NPC_DECK_ID);
    }

    @Test
    @Transactional
    public void getAllMTicketQuestStagesBySecondHalfNpcDeckIdIsInShouldWork() throws Exception {
        // Initialize the database
        mTicketQuestStageRepository.saveAndFlush(mTicketQuestStage);

        // Get all the mTicketQuestStageList where secondHalfNpcDeckId in DEFAULT_SECOND_HALF_NPC_DECK_ID or UPDATED_SECOND_HALF_NPC_DECK_ID
        defaultMTicketQuestStageShouldBeFound("secondHalfNpcDeckId.in=" + DEFAULT_SECOND_HALF_NPC_DECK_ID + "," + UPDATED_SECOND_HALF_NPC_DECK_ID);

        // Get all the mTicketQuestStageList where secondHalfNpcDeckId equals to UPDATED_SECOND_HALF_NPC_DECK_ID
        defaultMTicketQuestStageShouldNotBeFound("secondHalfNpcDeckId.in=" + UPDATED_SECOND_HALF_NPC_DECK_ID);
    }

    @Test
    @Transactional
    public void getAllMTicketQuestStagesBySecondHalfNpcDeckIdIsNullOrNotNull() throws Exception {
        // Initialize the database
        mTicketQuestStageRepository.saveAndFlush(mTicketQuestStage);

        // Get all the mTicketQuestStageList where secondHalfNpcDeckId is not null
        defaultMTicketQuestStageShouldBeFound("secondHalfNpcDeckId.specified=true");

        // Get all the mTicketQuestStageList where secondHalfNpcDeckId is null
        defaultMTicketQuestStageShouldNotBeFound("secondHalfNpcDeckId.specified=false");
    }

    @Test
    @Transactional
    public void getAllMTicketQuestStagesBySecondHalfNpcDeckIdIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mTicketQuestStageRepository.saveAndFlush(mTicketQuestStage);

        // Get all the mTicketQuestStageList where secondHalfNpcDeckId greater than or equals to DEFAULT_SECOND_HALF_NPC_DECK_ID
        defaultMTicketQuestStageShouldBeFound("secondHalfNpcDeckId.greaterOrEqualThan=" + DEFAULT_SECOND_HALF_NPC_DECK_ID);

        // Get all the mTicketQuestStageList where secondHalfNpcDeckId greater than or equals to UPDATED_SECOND_HALF_NPC_DECK_ID
        defaultMTicketQuestStageShouldNotBeFound("secondHalfNpcDeckId.greaterOrEqualThan=" + UPDATED_SECOND_HALF_NPC_DECK_ID);
    }

    @Test
    @Transactional
    public void getAllMTicketQuestStagesBySecondHalfNpcDeckIdIsLessThanSomething() throws Exception {
        // Initialize the database
        mTicketQuestStageRepository.saveAndFlush(mTicketQuestStage);

        // Get all the mTicketQuestStageList where secondHalfNpcDeckId less than or equals to DEFAULT_SECOND_HALF_NPC_DECK_ID
        defaultMTicketQuestStageShouldNotBeFound("secondHalfNpcDeckId.lessThan=" + DEFAULT_SECOND_HALF_NPC_DECK_ID);

        // Get all the mTicketQuestStageList where secondHalfNpcDeckId less than or equals to UPDATED_SECOND_HALF_NPC_DECK_ID
        defaultMTicketQuestStageShouldBeFound("secondHalfNpcDeckId.lessThan=" + UPDATED_SECOND_HALF_NPC_DECK_ID);
    }


    @Test
    @Transactional
    public void getAllMTicketQuestStagesBySecondHalfEnvironmentIdIsEqualToSomething() throws Exception {
        // Initialize the database
        mTicketQuestStageRepository.saveAndFlush(mTicketQuestStage);

        // Get all the mTicketQuestStageList where secondHalfEnvironmentId equals to DEFAULT_SECOND_HALF_ENVIRONMENT_ID
        defaultMTicketQuestStageShouldBeFound("secondHalfEnvironmentId.equals=" + DEFAULT_SECOND_HALF_ENVIRONMENT_ID);

        // Get all the mTicketQuestStageList where secondHalfEnvironmentId equals to UPDATED_SECOND_HALF_ENVIRONMENT_ID
        defaultMTicketQuestStageShouldNotBeFound("secondHalfEnvironmentId.equals=" + UPDATED_SECOND_HALF_ENVIRONMENT_ID);
    }

    @Test
    @Transactional
    public void getAllMTicketQuestStagesBySecondHalfEnvironmentIdIsInShouldWork() throws Exception {
        // Initialize the database
        mTicketQuestStageRepository.saveAndFlush(mTicketQuestStage);

        // Get all the mTicketQuestStageList where secondHalfEnvironmentId in DEFAULT_SECOND_HALF_ENVIRONMENT_ID or UPDATED_SECOND_HALF_ENVIRONMENT_ID
        defaultMTicketQuestStageShouldBeFound("secondHalfEnvironmentId.in=" + DEFAULT_SECOND_HALF_ENVIRONMENT_ID + "," + UPDATED_SECOND_HALF_ENVIRONMENT_ID);

        // Get all the mTicketQuestStageList where secondHalfEnvironmentId equals to UPDATED_SECOND_HALF_ENVIRONMENT_ID
        defaultMTicketQuestStageShouldNotBeFound("secondHalfEnvironmentId.in=" + UPDATED_SECOND_HALF_ENVIRONMENT_ID);
    }

    @Test
    @Transactional
    public void getAllMTicketQuestStagesBySecondHalfEnvironmentIdIsNullOrNotNull() throws Exception {
        // Initialize the database
        mTicketQuestStageRepository.saveAndFlush(mTicketQuestStage);

        // Get all the mTicketQuestStageList where secondHalfEnvironmentId is not null
        defaultMTicketQuestStageShouldBeFound("secondHalfEnvironmentId.specified=true");

        // Get all the mTicketQuestStageList where secondHalfEnvironmentId is null
        defaultMTicketQuestStageShouldNotBeFound("secondHalfEnvironmentId.specified=false");
    }

    @Test
    @Transactional
    public void getAllMTicketQuestStagesBySecondHalfEnvironmentIdIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mTicketQuestStageRepository.saveAndFlush(mTicketQuestStage);

        // Get all the mTicketQuestStageList where secondHalfEnvironmentId greater than or equals to DEFAULT_SECOND_HALF_ENVIRONMENT_ID
        defaultMTicketQuestStageShouldBeFound("secondHalfEnvironmentId.greaterOrEqualThan=" + DEFAULT_SECOND_HALF_ENVIRONMENT_ID);

        // Get all the mTicketQuestStageList where secondHalfEnvironmentId greater than or equals to UPDATED_SECOND_HALF_ENVIRONMENT_ID
        defaultMTicketQuestStageShouldNotBeFound("secondHalfEnvironmentId.greaterOrEqualThan=" + UPDATED_SECOND_HALF_ENVIRONMENT_ID);
    }

    @Test
    @Transactional
    public void getAllMTicketQuestStagesBySecondHalfEnvironmentIdIsLessThanSomething() throws Exception {
        // Initialize the database
        mTicketQuestStageRepository.saveAndFlush(mTicketQuestStage);

        // Get all the mTicketQuestStageList where secondHalfEnvironmentId less than or equals to DEFAULT_SECOND_HALF_ENVIRONMENT_ID
        defaultMTicketQuestStageShouldNotBeFound("secondHalfEnvironmentId.lessThan=" + DEFAULT_SECOND_HALF_ENVIRONMENT_ID);

        // Get all the mTicketQuestStageList where secondHalfEnvironmentId less than or equals to UPDATED_SECOND_HALF_ENVIRONMENT_ID
        defaultMTicketQuestStageShouldBeFound("secondHalfEnvironmentId.lessThan=" + UPDATED_SECOND_HALF_ENVIRONMENT_ID);
    }


    @Test
    @Transactional
    public void getAllMTicketQuestStagesByExtraFirstHalfNpcDeckIdIsEqualToSomething() throws Exception {
        // Initialize the database
        mTicketQuestStageRepository.saveAndFlush(mTicketQuestStage);

        // Get all the mTicketQuestStageList where extraFirstHalfNpcDeckId equals to DEFAULT_EXTRA_FIRST_HALF_NPC_DECK_ID
        defaultMTicketQuestStageShouldBeFound("extraFirstHalfNpcDeckId.equals=" + DEFAULT_EXTRA_FIRST_HALF_NPC_DECK_ID);

        // Get all the mTicketQuestStageList where extraFirstHalfNpcDeckId equals to UPDATED_EXTRA_FIRST_HALF_NPC_DECK_ID
        defaultMTicketQuestStageShouldNotBeFound("extraFirstHalfNpcDeckId.equals=" + UPDATED_EXTRA_FIRST_HALF_NPC_DECK_ID);
    }

    @Test
    @Transactional
    public void getAllMTicketQuestStagesByExtraFirstHalfNpcDeckIdIsInShouldWork() throws Exception {
        // Initialize the database
        mTicketQuestStageRepository.saveAndFlush(mTicketQuestStage);

        // Get all the mTicketQuestStageList where extraFirstHalfNpcDeckId in DEFAULT_EXTRA_FIRST_HALF_NPC_DECK_ID or UPDATED_EXTRA_FIRST_HALF_NPC_DECK_ID
        defaultMTicketQuestStageShouldBeFound("extraFirstHalfNpcDeckId.in=" + DEFAULT_EXTRA_FIRST_HALF_NPC_DECK_ID + "," + UPDATED_EXTRA_FIRST_HALF_NPC_DECK_ID);

        // Get all the mTicketQuestStageList where extraFirstHalfNpcDeckId equals to UPDATED_EXTRA_FIRST_HALF_NPC_DECK_ID
        defaultMTicketQuestStageShouldNotBeFound("extraFirstHalfNpcDeckId.in=" + UPDATED_EXTRA_FIRST_HALF_NPC_DECK_ID);
    }

    @Test
    @Transactional
    public void getAllMTicketQuestStagesByExtraFirstHalfNpcDeckIdIsNullOrNotNull() throws Exception {
        // Initialize the database
        mTicketQuestStageRepository.saveAndFlush(mTicketQuestStage);

        // Get all the mTicketQuestStageList where extraFirstHalfNpcDeckId is not null
        defaultMTicketQuestStageShouldBeFound("extraFirstHalfNpcDeckId.specified=true");

        // Get all the mTicketQuestStageList where extraFirstHalfNpcDeckId is null
        defaultMTicketQuestStageShouldNotBeFound("extraFirstHalfNpcDeckId.specified=false");
    }

    @Test
    @Transactional
    public void getAllMTicketQuestStagesByExtraFirstHalfNpcDeckIdIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mTicketQuestStageRepository.saveAndFlush(mTicketQuestStage);

        // Get all the mTicketQuestStageList where extraFirstHalfNpcDeckId greater than or equals to DEFAULT_EXTRA_FIRST_HALF_NPC_DECK_ID
        defaultMTicketQuestStageShouldBeFound("extraFirstHalfNpcDeckId.greaterOrEqualThan=" + DEFAULT_EXTRA_FIRST_HALF_NPC_DECK_ID);

        // Get all the mTicketQuestStageList where extraFirstHalfNpcDeckId greater than or equals to UPDATED_EXTRA_FIRST_HALF_NPC_DECK_ID
        defaultMTicketQuestStageShouldNotBeFound("extraFirstHalfNpcDeckId.greaterOrEqualThan=" + UPDATED_EXTRA_FIRST_HALF_NPC_DECK_ID);
    }

    @Test
    @Transactional
    public void getAllMTicketQuestStagesByExtraFirstHalfNpcDeckIdIsLessThanSomething() throws Exception {
        // Initialize the database
        mTicketQuestStageRepository.saveAndFlush(mTicketQuestStage);

        // Get all the mTicketQuestStageList where extraFirstHalfNpcDeckId less than or equals to DEFAULT_EXTRA_FIRST_HALF_NPC_DECK_ID
        defaultMTicketQuestStageShouldNotBeFound("extraFirstHalfNpcDeckId.lessThan=" + DEFAULT_EXTRA_FIRST_HALF_NPC_DECK_ID);

        // Get all the mTicketQuestStageList where extraFirstHalfNpcDeckId less than or equals to UPDATED_EXTRA_FIRST_HALF_NPC_DECK_ID
        defaultMTicketQuestStageShouldBeFound("extraFirstHalfNpcDeckId.lessThan=" + UPDATED_EXTRA_FIRST_HALF_NPC_DECK_ID);
    }


    @Test
    @Transactional
    public void getAllMTicketQuestStagesByExtraSecondHalfNpcDeckIdIsEqualToSomething() throws Exception {
        // Initialize the database
        mTicketQuestStageRepository.saveAndFlush(mTicketQuestStage);

        // Get all the mTicketQuestStageList where extraSecondHalfNpcDeckId equals to DEFAULT_EXTRA_SECOND_HALF_NPC_DECK_ID
        defaultMTicketQuestStageShouldBeFound("extraSecondHalfNpcDeckId.equals=" + DEFAULT_EXTRA_SECOND_HALF_NPC_DECK_ID);

        // Get all the mTicketQuestStageList where extraSecondHalfNpcDeckId equals to UPDATED_EXTRA_SECOND_HALF_NPC_DECK_ID
        defaultMTicketQuestStageShouldNotBeFound("extraSecondHalfNpcDeckId.equals=" + UPDATED_EXTRA_SECOND_HALF_NPC_DECK_ID);
    }

    @Test
    @Transactional
    public void getAllMTicketQuestStagesByExtraSecondHalfNpcDeckIdIsInShouldWork() throws Exception {
        // Initialize the database
        mTicketQuestStageRepository.saveAndFlush(mTicketQuestStage);

        // Get all the mTicketQuestStageList where extraSecondHalfNpcDeckId in DEFAULT_EXTRA_SECOND_HALF_NPC_DECK_ID or UPDATED_EXTRA_SECOND_HALF_NPC_DECK_ID
        defaultMTicketQuestStageShouldBeFound("extraSecondHalfNpcDeckId.in=" + DEFAULT_EXTRA_SECOND_HALF_NPC_DECK_ID + "," + UPDATED_EXTRA_SECOND_HALF_NPC_DECK_ID);

        // Get all the mTicketQuestStageList where extraSecondHalfNpcDeckId equals to UPDATED_EXTRA_SECOND_HALF_NPC_DECK_ID
        defaultMTicketQuestStageShouldNotBeFound("extraSecondHalfNpcDeckId.in=" + UPDATED_EXTRA_SECOND_HALF_NPC_DECK_ID);
    }

    @Test
    @Transactional
    public void getAllMTicketQuestStagesByExtraSecondHalfNpcDeckIdIsNullOrNotNull() throws Exception {
        // Initialize the database
        mTicketQuestStageRepository.saveAndFlush(mTicketQuestStage);

        // Get all the mTicketQuestStageList where extraSecondHalfNpcDeckId is not null
        defaultMTicketQuestStageShouldBeFound("extraSecondHalfNpcDeckId.specified=true");

        // Get all the mTicketQuestStageList where extraSecondHalfNpcDeckId is null
        defaultMTicketQuestStageShouldNotBeFound("extraSecondHalfNpcDeckId.specified=false");
    }

    @Test
    @Transactional
    public void getAllMTicketQuestStagesByExtraSecondHalfNpcDeckIdIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mTicketQuestStageRepository.saveAndFlush(mTicketQuestStage);

        // Get all the mTicketQuestStageList where extraSecondHalfNpcDeckId greater than or equals to DEFAULT_EXTRA_SECOND_HALF_NPC_DECK_ID
        defaultMTicketQuestStageShouldBeFound("extraSecondHalfNpcDeckId.greaterOrEqualThan=" + DEFAULT_EXTRA_SECOND_HALF_NPC_DECK_ID);

        // Get all the mTicketQuestStageList where extraSecondHalfNpcDeckId greater than or equals to UPDATED_EXTRA_SECOND_HALF_NPC_DECK_ID
        defaultMTicketQuestStageShouldNotBeFound("extraSecondHalfNpcDeckId.greaterOrEqualThan=" + UPDATED_EXTRA_SECOND_HALF_NPC_DECK_ID);
    }

    @Test
    @Transactional
    public void getAllMTicketQuestStagesByExtraSecondHalfNpcDeckIdIsLessThanSomething() throws Exception {
        // Initialize the database
        mTicketQuestStageRepository.saveAndFlush(mTicketQuestStage);

        // Get all the mTicketQuestStageList where extraSecondHalfNpcDeckId less than or equals to DEFAULT_EXTRA_SECOND_HALF_NPC_DECK_ID
        defaultMTicketQuestStageShouldNotBeFound("extraSecondHalfNpcDeckId.lessThan=" + DEFAULT_EXTRA_SECOND_HALF_NPC_DECK_ID);

        // Get all the mTicketQuestStageList where extraSecondHalfNpcDeckId less than or equals to UPDATED_EXTRA_SECOND_HALF_NPC_DECK_ID
        defaultMTicketQuestStageShouldBeFound("extraSecondHalfNpcDeckId.lessThan=" + UPDATED_EXTRA_SECOND_HALF_NPC_DECK_ID);
    }


    @Test
    @Transactional
    public void getAllMTicketQuestStagesByConsumeApIsEqualToSomething() throws Exception {
        // Initialize the database
        mTicketQuestStageRepository.saveAndFlush(mTicketQuestStage);

        // Get all the mTicketQuestStageList where consumeAp equals to DEFAULT_CONSUME_AP
        defaultMTicketQuestStageShouldBeFound("consumeAp.equals=" + DEFAULT_CONSUME_AP);

        // Get all the mTicketQuestStageList where consumeAp equals to UPDATED_CONSUME_AP
        defaultMTicketQuestStageShouldNotBeFound("consumeAp.equals=" + UPDATED_CONSUME_AP);
    }

    @Test
    @Transactional
    public void getAllMTicketQuestStagesByConsumeApIsInShouldWork() throws Exception {
        // Initialize the database
        mTicketQuestStageRepository.saveAndFlush(mTicketQuestStage);

        // Get all the mTicketQuestStageList where consumeAp in DEFAULT_CONSUME_AP or UPDATED_CONSUME_AP
        defaultMTicketQuestStageShouldBeFound("consumeAp.in=" + DEFAULT_CONSUME_AP + "," + UPDATED_CONSUME_AP);

        // Get all the mTicketQuestStageList where consumeAp equals to UPDATED_CONSUME_AP
        defaultMTicketQuestStageShouldNotBeFound("consumeAp.in=" + UPDATED_CONSUME_AP);
    }

    @Test
    @Transactional
    public void getAllMTicketQuestStagesByConsumeApIsNullOrNotNull() throws Exception {
        // Initialize the database
        mTicketQuestStageRepository.saveAndFlush(mTicketQuestStage);

        // Get all the mTicketQuestStageList where consumeAp is not null
        defaultMTicketQuestStageShouldBeFound("consumeAp.specified=true");

        // Get all the mTicketQuestStageList where consumeAp is null
        defaultMTicketQuestStageShouldNotBeFound("consumeAp.specified=false");
    }

    @Test
    @Transactional
    public void getAllMTicketQuestStagesByConsumeApIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mTicketQuestStageRepository.saveAndFlush(mTicketQuestStage);

        // Get all the mTicketQuestStageList where consumeAp greater than or equals to DEFAULT_CONSUME_AP
        defaultMTicketQuestStageShouldBeFound("consumeAp.greaterOrEqualThan=" + DEFAULT_CONSUME_AP);

        // Get all the mTicketQuestStageList where consumeAp greater than or equals to UPDATED_CONSUME_AP
        defaultMTicketQuestStageShouldNotBeFound("consumeAp.greaterOrEqualThan=" + UPDATED_CONSUME_AP);
    }

    @Test
    @Transactional
    public void getAllMTicketQuestStagesByConsumeApIsLessThanSomething() throws Exception {
        // Initialize the database
        mTicketQuestStageRepository.saveAndFlush(mTicketQuestStage);

        // Get all the mTicketQuestStageList where consumeAp less than or equals to DEFAULT_CONSUME_AP
        defaultMTicketQuestStageShouldNotBeFound("consumeAp.lessThan=" + DEFAULT_CONSUME_AP);

        // Get all the mTicketQuestStageList where consumeAp less than or equals to UPDATED_CONSUME_AP
        defaultMTicketQuestStageShouldBeFound("consumeAp.lessThan=" + UPDATED_CONSUME_AP);
    }


    @Test
    @Transactional
    public void getAllMTicketQuestStagesByKickOffTypeIsEqualToSomething() throws Exception {
        // Initialize the database
        mTicketQuestStageRepository.saveAndFlush(mTicketQuestStage);

        // Get all the mTicketQuestStageList where kickOffType equals to DEFAULT_KICK_OFF_TYPE
        defaultMTicketQuestStageShouldBeFound("kickOffType.equals=" + DEFAULT_KICK_OFF_TYPE);

        // Get all the mTicketQuestStageList where kickOffType equals to UPDATED_KICK_OFF_TYPE
        defaultMTicketQuestStageShouldNotBeFound("kickOffType.equals=" + UPDATED_KICK_OFF_TYPE);
    }

    @Test
    @Transactional
    public void getAllMTicketQuestStagesByKickOffTypeIsInShouldWork() throws Exception {
        // Initialize the database
        mTicketQuestStageRepository.saveAndFlush(mTicketQuestStage);

        // Get all the mTicketQuestStageList where kickOffType in DEFAULT_KICK_OFF_TYPE or UPDATED_KICK_OFF_TYPE
        defaultMTicketQuestStageShouldBeFound("kickOffType.in=" + DEFAULT_KICK_OFF_TYPE + "," + UPDATED_KICK_OFF_TYPE);

        // Get all the mTicketQuestStageList where kickOffType equals to UPDATED_KICK_OFF_TYPE
        defaultMTicketQuestStageShouldNotBeFound("kickOffType.in=" + UPDATED_KICK_OFF_TYPE);
    }

    @Test
    @Transactional
    public void getAllMTicketQuestStagesByKickOffTypeIsNullOrNotNull() throws Exception {
        // Initialize the database
        mTicketQuestStageRepository.saveAndFlush(mTicketQuestStage);

        // Get all the mTicketQuestStageList where kickOffType is not null
        defaultMTicketQuestStageShouldBeFound("kickOffType.specified=true");

        // Get all the mTicketQuestStageList where kickOffType is null
        defaultMTicketQuestStageShouldNotBeFound("kickOffType.specified=false");
    }

    @Test
    @Transactional
    public void getAllMTicketQuestStagesByKickOffTypeIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mTicketQuestStageRepository.saveAndFlush(mTicketQuestStage);

        // Get all the mTicketQuestStageList where kickOffType greater than or equals to DEFAULT_KICK_OFF_TYPE
        defaultMTicketQuestStageShouldBeFound("kickOffType.greaterOrEqualThan=" + DEFAULT_KICK_OFF_TYPE);

        // Get all the mTicketQuestStageList where kickOffType greater than or equals to UPDATED_KICK_OFF_TYPE
        defaultMTicketQuestStageShouldNotBeFound("kickOffType.greaterOrEqualThan=" + UPDATED_KICK_OFF_TYPE);
    }

    @Test
    @Transactional
    public void getAllMTicketQuestStagesByKickOffTypeIsLessThanSomething() throws Exception {
        // Initialize the database
        mTicketQuestStageRepository.saveAndFlush(mTicketQuestStage);

        // Get all the mTicketQuestStageList where kickOffType less than or equals to DEFAULT_KICK_OFF_TYPE
        defaultMTicketQuestStageShouldNotBeFound("kickOffType.lessThan=" + DEFAULT_KICK_OFF_TYPE);

        // Get all the mTicketQuestStageList where kickOffType less than or equals to UPDATED_KICK_OFF_TYPE
        defaultMTicketQuestStageShouldBeFound("kickOffType.lessThan=" + UPDATED_KICK_OFF_TYPE);
    }


    @Test
    @Transactional
    public void getAllMTicketQuestStagesByMatchMinuteIsEqualToSomething() throws Exception {
        // Initialize the database
        mTicketQuestStageRepository.saveAndFlush(mTicketQuestStage);

        // Get all the mTicketQuestStageList where matchMinute equals to DEFAULT_MATCH_MINUTE
        defaultMTicketQuestStageShouldBeFound("matchMinute.equals=" + DEFAULT_MATCH_MINUTE);

        // Get all the mTicketQuestStageList where matchMinute equals to UPDATED_MATCH_MINUTE
        defaultMTicketQuestStageShouldNotBeFound("matchMinute.equals=" + UPDATED_MATCH_MINUTE);
    }

    @Test
    @Transactional
    public void getAllMTicketQuestStagesByMatchMinuteIsInShouldWork() throws Exception {
        // Initialize the database
        mTicketQuestStageRepository.saveAndFlush(mTicketQuestStage);

        // Get all the mTicketQuestStageList where matchMinute in DEFAULT_MATCH_MINUTE or UPDATED_MATCH_MINUTE
        defaultMTicketQuestStageShouldBeFound("matchMinute.in=" + DEFAULT_MATCH_MINUTE + "," + UPDATED_MATCH_MINUTE);

        // Get all the mTicketQuestStageList where matchMinute equals to UPDATED_MATCH_MINUTE
        defaultMTicketQuestStageShouldNotBeFound("matchMinute.in=" + UPDATED_MATCH_MINUTE);
    }

    @Test
    @Transactional
    public void getAllMTicketQuestStagesByMatchMinuteIsNullOrNotNull() throws Exception {
        // Initialize the database
        mTicketQuestStageRepository.saveAndFlush(mTicketQuestStage);

        // Get all the mTicketQuestStageList where matchMinute is not null
        defaultMTicketQuestStageShouldBeFound("matchMinute.specified=true");

        // Get all the mTicketQuestStageList where matchMinute is null
        defaultMTicketQuestStageShouldNotBeFound("matchMinute.specified=false");
    }

    @Test
    @Transactional
    public void getAllMTicketQuestStagesByMatchMinuteIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mTicketQuestStageRepository.saveAndFlush(mTicketQuestStage);

        // Get all the mTicketQuestStageList where matchMinute greater than or equals to DEFAULT_MATCH_MINUTE
        defaultMTicketQuestStageShouldBeFound("matchMinute.greaterOrEqualThan=" + DEFAULT_MATCH_MINUTE);

        // Get all the mTicketQuestStageList where matchMinute greater than or equals to UPDATED_MATCH_MINUTE
        defaultMTicketQuestStageShouldNotBeFound("matchMinute.greaterOrEqualThan=" + UPDATED_MATCH_MINUTE);
    }

    @Test
    @Transactional
    public void getAllMTicketQuestStagesByMatchMinuteIsLessThanSomething() throws Exception {
        // Initialize the database
        mTicketQuestStageRepository.saveAndFlush(mTicketQuestStage);

        // Get all the mTicketQuestStageList where matchMinute less than or equals to DEFAULT_MATCH_MINUTE
        defaultMTicketQuestStageShouldNotBeFound("matchMinute.lessThan=" + DEFAULT_MATCH_MINUTE);

        // Get all the mTicketQuestStageList where matchMinute less than or equals to UPDATED_MATCH_MINUTE
        defaultMTicketQuestStageShouldBeFound("matchMinute.lessThan=" + UPDATED_MATCH_MINUTE);
    }


    @Test
    @Transactional
    public void getAllMTicketQuestStagesByEnableExtraIsEqualToSomething() throws Exception {
        // Initialize the database
        mTicketQuestStageRepository.saveAndFlush(mTicketQuestStage);

        // Get all the mTicketQuestStageList where enableExtra equals to DEFAULT_ENABLE_EXTRA
        defaultMTicketQuestStageShouldBeFound("enableExtra.equals=" + DEFAULT_ENABLE_EXTRA);

        // Get all the mTicketQuestStageList where enableExtra equals to UPDATED_ENABLE_EXTRA
        defaultMTicketQuestStageShouldNotBeFound("enableExtra.equals=" + UPDATED_ENABLE_EXTRA);
    }

    @Test
    @Transactional
    public void getAllMTicketQuestStagesByEnableExtraIsInShouldWork() throws Exception {
        // Initialize the database
        mTicketQuestStageRepository.saveAndFlush(mTicketQuestStage);

        // Get all the mTicketQuestStageList where enableExtra in DEFAULT_ENABLE_EXTRA or UPDATED_ENABLE_EXTRA
        defaultMTicketQuestStageShouldBeFound("enableExtra.in=" + DEFAULT_ENABLE_EXTRA + "," + UPDATED_ENABLE_EXTRA);

        // Get all the mTicketQuestStageList where enableExtra equals to UPDATED_ENABLE_EXTRA
        defaultMTicketQuestStageShouldNotBeFound("enableExtra.in=" + UPDATED_ENABLE_EXTRA);
    }

    @Test
    @Transactional
    public void getAllMTicketQuestStagesByEnableExtraIsNullOrNotNull() throws Exception {
        // Initialize the database
        mTicketQuestStageRepository.saveAndFlush(mTicketQuestStage);

        // Get all the mTicketQuestStageList where enableExtra is not null
        defaultMTicketQuestStageShouldBeFound("enableExtra.specified=true");

        // Get all the mTicketQuestStageList where enableExtra is null
        defaultMTicketQuestStageShouldNotBeFound("enableExtra.specified=false");
    }

    @Test
    @Transactional
    public void getAllMTicketQuestStagesByEnableExtraIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mTicketQuestStageRepository.saveAndFlush(mTicketQuestStage);

        // Get all the mTicketQuestStageList where enableExtra greater than or equals to DEFAULT_ENABLE_EXTRA
        defaultMTicketQuestStageShouldBeFound("enableExtra.greaterOrEqualThan=" + DEFAULT_ENABLE_EXTRA);

        // Get all the mTicketQuestStageList where enableExtra greater than or equals to UPDATED_ENABLE_EXTRA
        defaultMTicketQuestStageShouldNotBeFound("enableExtra.greaterOrEqualThan=" + UPDATED_ENABLE_EXTRA);
    }

    @Test
    @Transactional
    public void getAllMTicketQuestStagesByEnableExtraIsLessThanSomething() throws Exception {
        // Initialize the database
        mTicketQuestStageRepository.saveAndFlush(mTicketQuestStage);

        // Get all the mTicketQuestStageList where enableExtra less than or equals to DEFAULT_ENABLE_EXTRA
        defaultMTicketQuestStageShouldNotBeFound("enableExtra.lessThan=" + DEFAULT_ENABLE_EXTRA);

        // Get all the mTicketQuestStageList where enableExtra less than or equals to UPDATED_ENABLE_EXTRA
        defaultMTicketQuestStageShouldBeFound("enableExtra.lessThan=" + UPDATED_ENABLE_EXTRA);
    }


    @Test
    @Transactional
    public void getAllMTicketQuestStagesByEnablePkIsEqualToSomething() throws Exception {
        // Initialize the database
        mTicketQuestStageRepository.saveAndFlush(mTicketQuestStage);

        // Get all the mTicketQuestStageList where enablePk equals to DEFAULT_ENABLE_PK
        defaultMTicketQuestStageShouldBeFound("enablePk.equals=" + DEFAULT_ENABLE_PK);

        // Get all the mTicketQuestStageList where enablePk equals to UPDATED_ENABLE_PK
        defaultMTicketQuestStageShouldNotBeFound("enablePk.equals=" + UPDATED_ENABLE_PK);
    }

    @Test
    @Transactional
    public void getAllMTicketQuestStagesByEnablePkIsInShouldWork() throws Exception {
        // Initialize the database
        mTicketQuestStageRepository.saveAndFlush(mTicketQuestStage);

        // Get all the mTicketQuestStageList where enablePk in DEFAULT_ENABLE_PK or UPDATED_ENABLE_PK
        defaultMTicketQuestStageShouldBeFound("enablePk.in=" + DEFAULT_ENABLE_PK + "," + UPDATED_ENABLE_PK);

        // Get all the mTicketQuestStageList where enablePk equals to UPDATED_ENABLE_PK
        defaultMTicketQuestStageShouldNotBeFound("enablePk.in=" + UPDATED_ENABLE_PK);
    }

    @Test
    @Transactional
    public void getAllMTicketQuestStagesByEnablePkIsNullOrNotNull() throws Exception {
        // Initialize the database
        mTicketQuestStageRepository.saveAndFlush(mTicketQuestStage);

        // Get all the mTicketQuestStageList where enablePk is not null
        defaultMTicketQuestStageShouldBeFound("enablePk.specified=true");

        // Get all the mTicketQuestStageList where enablePk is null
        defaultMTicketQuestStageShouldNotBeFound("enablePk.specified=false");
    }

    @Test
    @Transactional
    public void getAllMTicketQuestStagesByEnablePkIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mTicketQuestStageRepository.saveAndFlush(mTicketQuestStage);

        // Get all the mTicketQuestStageList where enablePk greater than or equals to DEFAULT_ENABLE_PK
        defaultMTicketQuestStageShouldBeFound("enablePk.greaterOrEqualThan=" + DEFAULT_ENABLE_PK);

        // Get all the mTicketQuestStageList where enablePk greater than or equals to UPDATED_ENABLE_PK
        defaultMTicketQuestStageShouldNotBeFound("enablePk.greaterOrEqualThan=" + UPDATED_ENABLE_PK);
    }

    @Test
    @Transactional
    public void getAllMTicketQuestStagesByEnablePkIsLessThanSomething() throws Exception {
        // Initialize the database
        mTicketQuestStageRepository.saveAndFlush(mTicketQuestStage);

        // Get all the mTicketQuestStageList where enablePk less than or equals to DEFAULT_ENABLE_PK
        defaultMTicketQuestStageShouldNotBeFound("enablePk.lessThan=" + DEFAULT_ENABLE_PK);

        // Get all the mTicketQuestStageList where enablePk less than or equals to UPDATED_ENABLE_PK
        defaultMTicketQuestStageShouldBeFound("enablePk.lessThan=" + UPDATED_ENABLE_PK);
    }


    @Test
    @Transactional
    public void getAllMTicketQuestStagesByAiLevelIsEqualToSomething() throws Exception {
        // Initialize the database
        mTicketQuestStageRepository.saveAndFlush(mTicketQuestStage);

        // Get all the mTicketQuestStageList where aiLevel equals to DEFAULT_AI_LEVEL
        defaultMTicketQuestStageShouldBeFound("aiLevel.equals=" + DEFAULT_AI_LEVEL);

        // Get all the mTicketQuestStageList where aiLevel equals to UPDATED_AI_LEVEL
        defaultMTicketQuestStageShouldNotBeFound("aiLevel.equals=" + UPDATED_AI_LEVEL);
    }

    @Test
    @Transactional
    public void getAllMTicketQuestStagesByAiLevelIsInShouldWork() throws Exception {
        // Initialize the database
        mTicketQuestStageRepository.saveAndFlush(mTicketQuestStage);

        // Get all the mTicketQuestStageList where aiLevel in DEFAULT_AI_LEVEL or UPDATED_AI_LEVEL
        defaultMTicketQuestStageShouldBeFound("aiLevel.in=" + DEFAULT_AI_LEVEL + "," + UPDATED_AI_LEVEL);

        // Get all the mTicketQuestStageList where aiLevel equals to UPDATED_AI_LEVEL
        defaultMTicketQuestStageShouldNotBeFound("aiLevel.in=" + UPDATED_AI_LEVEL);
    }

    @Test
    @Transactional
    public void getAllMTicketQuestStagesByAiLevelIsNullOrNotNull() throws Exception {
        // Initialize the database
        mTicketQuestStageRepository.saveAndFlush(mTicketQuestStage);

        // Get all the mTicketQuestStageList where aiLevel is not null
        defaultMTicketQuestStageShouldBeFound("aiLevel.specified=true");

        // Get all the mTicketQuestStageList where aiLevel is null
        defaultMTicketQuestStageShouldNotBeFound("aiLevel.specified=false");
    }

    @Test
    @Transactional
    public void getAllMTicketQuestStagesByAiLevelIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mTicketQuestStageRepository.saveAndFlush(mTicketQuestStage);

        // Get all the mTicketQuestStageList where aiLevel greater than or equals to DEFAULT_AI_LEVEL
        defaultMTicketQuestStageShouldBeFound("aiLevel.greaterOrEqualThan=" + DEFAULT_AI_LEVEL);

        // Get all the mTicketQuestStageList where aiLevel greater than or equals to UPDATED_AI_LEVEL
        defaultMTicketQuestStageShouldNotBeFound("aiLevel.greaterOrEqualThan=" + UPDATED_AI_LEVEL);
    }

    @Test
    @Transactional
    public void getAllMTicketQuestStagesByAiLevelIsLessThanSomething() throws Exception {
        // Initialize the database
        mTicketQuestStageRepository.saveAndFlush(mTicketQuestStage);

        // Get all the mTicketQuestStageList where aiLevel less than or equals to DEFAULT_AI_LEVEL
        defaultMTicketQuestStageShouldNotBeFound("aiLevel.lessThan=" + DEFAULT_AI_LEVEL);

        // Get all the mTicketQuestStageList where aiLevel less than or equals to UPDATED_AI_LEVEL
        defaultMTicketQuestStageShouldBeFound("aiLevel.lessThan=" + UPDATED_AI_LEVEL);
    }


    @Test
    @Transactional
    public void getAllMTicketQuestStagesByStartAtSecondHalfIsEqualToSomething() throws Exception {
        // Initialize the database
        mTicketQuestStageRepository.saveAndFlush(mTicketQuestStage);

        // Get all the mTicketQuestStageList where startAtSecondHalf equals to DEFAULT_START_AT_SECOND_HALF
        defaultMTicketQuestStageShouldBeFound("startAtSecondHalf.equals=" + DEFAULT_START_AT_SECOND_HALF);

        // Get all the mTicketQuestStageList where startAtSecondHalf equals to UPDATED_START_AT_SECOND_HALF
        defaultMTicketQuestStageShouldNotBeFound("startAtSecondHalf.equals=" + UPDATED_START_AT_SECOND_HALF);
    }

    @Test
    @Transactional
    public void getAllMTicketQuestStagesByStartAtSecondHalfIsInShouldWork() throws Exception {
        // Initialize the database
        mTicketQuestStageRepository.saveAndFlush(mTicketQuestStage);

        // Get all the mTicketQuestStageList where startAtSecondHalf in DEFAULT_START_AT_SECOND_HALF or UPDATED_START_AT_SECOND_HALF
        defaultMTicketQuestStageShouldBeFound("startAtSecondHalf.in=" + DEFAULT_START_AT_SECOND_HALF + "," + UPDATED_START_AT_SECOND_HALF);

        // Get all the mTicketQuestStageList where startAtSecondHalf equals to UPDATED_START_AT_SECOND_HALF
        defaultMTicketQuestStageShouldNotBeFound("startAtSecondHalf.in=" + UPDATED_START_AT_SECOND_HALF);
    }

    @Test
    @Transactional
    public void getAllMTicketQuestStagesByStartAtSecondHalfIsNullOrNotNull() throws Exception {
        // Initialize the database
        mTicketQuestStageRepository.saveAndFlush(mTicketQuestStage);

        // Get all the mTicketQuestStageList where startAtSecondHalf is not null
        defaultMTicketQuestStageShouldBeFound("startAtSecondHalf.specified=true");

        // Get all the mTicketQuestStageList where startAtSecondHalf is null
        defaultMTicketQuestStageShouldNotBeFound("startAtSecondHalf.specified=false");
    }

    @Test
    @Transactional
    public void getAllMTicketQuestStagesByStartAtSecondHalfIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mTicketQuestStageRepository.saveAndFlush(mTicketQuestStage);

        // Get all the mTicketQuestStageList where startAtSecondHalf greater than or equals to DEFAULT_START_AT_SECOND_HALF
        defaultMTicketQuestStageShouldBeFound("startAtSecondHalf.greaterOrEqualThan=" + DEFAULT_START_AT_SECOND_HALF);

        // Get all the mTicketQuestStageList where startAtSecondHalf greater than or equals to UPDATED_START_AT_SECOND_HALF
        defaultMTicketQuestStageShouldNotBeFound("startAtSecondHalf.greaterOrEqualThan=" + UPDATED_START_AT_SECOND_HALF);
    }

    @Test
    @Transactional
    public void getAllMTicketQuestStagesByStartAtSecondHalfIsLessThanSomething() throws Exception {
        // Initialize the database
        mTicketQuestStageRepository.saveAndFlush(mTicketQuestStage);

        // Get all the mTicketQuestStageList where startAtSecondHalf less than or equals to DEFAULT_START_AT_SECOND_HALF
        defaultMTicketQuestStageShouldNotBeFound("startAtSecondHalf.lessThan=" + DEFAULT_START_AT_SECOND_HALF);

        // Get all the mTicketQuestStageList where startAtSecondHalf less than or equals to UPDATED_START_AT_SECOND_HALF
        defaultMTicketQuestStageShouldBeFound("startAtSecondHalf.lessThan=" + UPDATED_START_AT_SECOND_HALF);
    }


    @Test
    @Transactional
    public void getAllMTicketQuestStagesByStartScoreIsEqualToSomething() throws Exception {
        // Initialize the database
        mTicketQuestStageRepository.saveAndFlush(mTicketQuestStage);

        // Get all the mTicketQuestStageList where startScore equals to DEFAULT_START_SCORE
        defaultMTicketQuestStageShouldBeFound("startScore.equals=" + DEFAULT_START_SCORE);

        // Get all the mTicketQuestStageList where startScore equals to UPDATED_START_SCORE
        defaultMTicketQuestStageShouldNotBeFound("startScore.equals=" + UPDATED_START_SCORE);
    }

    @Test
    @Transactional
    public void getAllMTicketQuestStagesByStartScoreIsInShouldWork() throws Exception {
        // Initialize the database
        mTicketQuestStageRepository.saveAndFlush(mTicketQuestStage);

        // Get all the mTicketQuestStageList where startScore in DEFAULT_START_SCORE or UPDATED_START_SCORE
        defaultMTicketQuestStageShouldBeFound("startScore.in=" + DEFAULT_START_SCORE + "," + UPDATED_START_SCORE);

        // Get all the mTicketQuestStageList where startScore equals to UPDATED_START_SCORE
        defaultMTicketQuestStageShouldNotBeFound("startScore.in=" + UPDATED_START_SCORE);
    }

    @Test
    @Transactional
    public void getAllMTicketQuestStagesByStartScoreIsNullOrNotNull() throws Exception {
        // Initialize the database
        mTicketQuestStageRepository.saveAndFlush(mTicketQuestStage);

        // Get all the mTicketQuestStageList where startScore is not null
        defaultMTicketQuestStageShouldBeFound("startScore.specified=true");

        // Get all the mTicketQuestStageList where startScore is null
        defaultMTicketQuestStageShouldNotBeFound("startScore.specified=false");
    }

    @Test
    @Transactional
    public void getAllMTicketQuestStagesByStartScoreIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mTicketQuestStageRepository.saveAndFlush(mTicketQuestStage);

        // Get all the mTicketQuestStageList where startScore greater than or equals to DEFAULT_START_SCORE
        defaultMTicketQuestStageShouldBeFound("startScore.greaterOrEqualThan=" + DEFAULT_START_SCORE);

        // Get all the mTicketQuestStageList where startScore greater than or equals to UPDATED_START_SCORE
        defaultMTicketQuestStageShouldNotBeFound("startScore.greaterOrEqualThan=" + UPDATED_START_SCORE);
    }

    @Test
    @Transactional
    public void getAllMTicketQuestStagesByStartScoreIsLessThanSomething() throws Exception {
        // Initialize the database
        mTicketQuestStageRepository.saveAndFlush(mTicketQuestStage);

        // Get all the mTicketQuestStageList where startScore less than or equals to DEFAULT_START_SCORE
        defaultMTicketQuestStageShouldNotBeFound("startScore.lessThan=" + DEFAULT_START_SCORE);

        // Get all the mTicketQuestStageList where startScore less than or equals to UPDATED_START_SCORE
        defaultMTicketQuestStageShouldBeFound("startScore.lessThan=" + UPDATED_START_SCORE);
    }


    @Test
    @Transactional
    public void getAllMTicketQuestStagesByStartScoreOpponentIsEqualToSomething() throws Exception {
        // Initialize the database
        mTicketQuestStageRepository.saveAndFlush(mTicketQuestStage);

        // Get all the mTicketQuestStageList where startScoreOpponent equals to DEFAULT_START_SCORE_OPPONENT
        defaultMTicketQuestStageShouldBeFound("startScoreOpponent.equals=" + DEFAULT_START_SCORE_OPPONENT);

        // Get all the mTicketQuestStageList where startScoreOpponent equals to UPDATED_START_SCORE_OPPONENT
        defaultMTicketQuestStageShouldNotBeFound("startScoreOpponent.equals=" + UPDATED_START_SCORE_OPPONENT);
    }

    @Test
    @Transactional
    public void getAllMTicketQuestStagesByStartScoreOpponentIsInShouldWork() throws Exception {
        // Initialize the database
        mTicketQuestStageRepository.saveAndFlush(mTicketQuestStage);

        // Get all the mTicketQuestStageList where startScoreOpponent in DEFAULT_START_SCORE_OPPONENT or UPDATED_START_SCORE_OPPONENT
        defaultMTicketQuestStageShouldBeFound("startScoreOpponent.in=" + DEFAULT_START_SCORE_OPPONENT + "," + UPDATED_START_SCORE_OPPONENT);

        // Get all the mTicketQuestStageList where startScoreOpponent equals to UPDATED_START_SCORE_OPPONENT
        defaultMTicketQuestStageShouldNotBeFound("startScoreOpponent.in=" + UPDATED_START_SCORE_OPPONENT);
    }

    @Test
    @Transactional
    public void getAllMTicketQuestStagesByStartScoreOpponentIsNullOrNotNull() throws Exception {
        // Initialize the database
        mTicketQuestStageRepository.saveAndFlush(mTicketQuestStage);

        // Get all the mTicketQuestStageList where startScoreOpponent is not null
        defaultMTicketQuestStageShouldBeFound("startScoreOpponent.specified=true");

        // Get all the mTicketQuestStageList where startScoreOpponent is null
        defaultMTicketQuestStageShouldNotBeFound("startScoreOpponent.specified=false");
    }

    @Test
    @Transactional
    public void getAllMTicketQuestStagesByStartScoreOpponentIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mTicketQuestStageRepository.saveAndFlush(mTicketQuestStage);

        // Get all the mTicketQuestStageList where startScoreOpponent greater than or equals to DEFAULT_START_SCORE_OPPONENT
        defaultMTicketQuestStageShouldBeFound("startScoreOpponent.greaterOrEqualThan=" + DEFAULT_START_SCORE_OPPONENT);

        // Get all the mTicketQuestStageList where startScoreOpponent greater than or equals to UPDATED_START_SCORE_OPPONENT
        defaultMTicketQuestStageShouldNotBeFound("startScoreOpponent.greaterOrEqualThan=" + UPDATED_START_SCORE_OPPONENT);
    }

    @Test
    @Transactional
    public void getAllMTicketQuestStagesByStartScoreOpponentIsLessThanSomething() throws Exception {
        // Initialize the database
        mTicketQuestStageRepository.saveAndFlush(mTicketQuestStage);

        // Get all the mTicketQuestStageList where startScoreOpponent less than or equals to DEFAULT_START_SCORE_OPPONENT
        defaultMTicketQuestStageShouldNotBeFound("startScoreOpponent.lessThan=" + DEFAULT_START_SCORE_OPPONENT);

        // Get all the mTicketQuestStageList where startScoreOpponent less than or equals to UPDATED_START_SCORE_OPPONENT
        defaultMTicketQuestStageShouldBeFound("startScoreOpponent.lessThan=" + UPDATED_START_SCORE_OPPONENT);
    }


    @Test
    @Transactional
    public void getAllMTicketQuestStagesByConditionIdIsEqualToSomething() throws Exception {
        // Initialize the database
        mTicketQuestStageRepository.saveAndFlush(mTicketQuestStage);

        // Get all the mTicketQuestStageList where conditionId equals to DEFAULT_CONDITION_ID
        defaultMTicketQuestStageShouldBeFound("conditionId.equals=" + DEFAULT_CONDITION_ID);

        // Get all the mTicketQuestStageList where conditionId equals to UPDATED_CONDITION_ID
        defaultMTicketQuestStageShouldNotBeFound("conditionId.equals=" + UPDATED_CONDITION_ID);
    }

    @Test
    @Transactional
    public void getAllMTicketQuestStagesByConditionIdIsInShouldWork() throws Exception {
        // Initialize the database
        mTicketQuestStageRepository.saveAndFlush(mTicketQuestStage);

        // Get all the mTicketQuestStageList where conditionId in DEFAULT_CONDITION_ID or UPDATED_CONDITION_ID
        defaultMTicketQuestStageShouldBeFound("conditionId.in=" + DEFAULT_CONDITION_ID + "," + UPDATED_CONDITION_ID);

        // Get all the mTicketQuestStageList where conditionId equals to UPDATED_CONDITION_ID
        defaultMTicketQuestStageShouldNotBeFound("conditionId.in=" + UPDATED_CONDITION_ID);
    }

    @Test
    @Transactional
    public void getAllMTicketQuestStagesByConditionIdIsNullOrNotNull() throws Exception {
        // Initialize the database
        mTicketQuestStageRepository.saveAndFlush(mTicketQuestStage);

        // Get all the mTicketQuestStageList where conditionId is not null
        defaultMTicketQuestStageShouldBeFound("conditionId.specified=true");

        // Get all the mTicketQuestStageList where conditionId is null
        defaultMTicketQuestStageShouldNotBeFound("conditionId.specified=false");
    }

    @Test
    @Transactional
    public void getAllMTicketQuestStagesByConditionIdIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mTicketQuestStageRepository.saveAndFlush(mTicketQuestStage);

        // Get all the mTicketQuestStageList where conditionId greater than or equals to DEFAULT_CONDITION_ID
        defaultMTicketQuestStageShouldBeFound("conditionId.greaterOrEqualThan=" + DEFAULT_CONDITION_ID);

        // Get all the mTicketQuestStageList where conditionId greater than or equals to UPDATED_CONDITION_ID
        defaultMTicketQuestStageShouldNotBeFound("conditionId.greaterOrEqualThan=" + UPDATED_CONDITION_ID);
    }

    @Test
    @Transactional
    public void getAllMTicketQuestStagesByConditionIdIsLessThanSomething() throws Exception {
        // Initialize the database
        mTicketQuestStageRepository.saveAndFlush(mTicketQuestStage);

        // Get all the mTicketQuestStageList where conditionId less than or equals to DEFAULT_CONDITION_ID
        defaultMTicketQuestStageShouldNotBeFound("conditionId.lessThan=" + DEFAULT_CONDITION_ID);

        // Get all the mTicketQuestStageList where conditionId less than or equals to UPDATED_CONDITION_ID
        defaultMTicketQuestStageShouldBeFound("conditionId.lessThan=" + UPDATED_CONDITION_ID);
    }


    @Test
    @Transactional
    public void getAllMTicketQuestStagesByOptionIdIsEqualToSomething() throws Exception {
        // Initialize the database
        mTicketQuestStageRepository.saveAndFlush(mTicketQuestStage);

        // Get all the mTicketQuestStageList where optionId equals to DEFAULT_OPTION_ID
        defaultMTicketQuestStageShouldBeFound("optionId.equals=" + DEFAULT_OPTION_ID);

        // Get all the mTicketQuestStageList where optionId equals to UPDATED_OPTION_ID
        defaultMTicketQuestStageShouldNotBeFound("optionId.equals=" + UPDATED_OPTION_ID);
    }

    @Test
    @Transactional
    public void getAllMTicketQuestStagesByOptionIdIsInShouldWork() throws Exception {
        // Initialize the database
        mTicketQuestStageRepository.saveAndFlush(mTicketQuestStage);

        // Get all the mTicketQuestStageList where optionId in DEFAULT_OPTION_ID or UPDATED_OPTION_ID
        defaultMTicketQuestStageShouldBeFound("optionId.in=" + DEFAULT_OPTION_ID + "," + UPDATED_OPTION_ID);

        // Get all the mTicketQuestStageList where optionId equals to UPDATED_OPTION_ID
        defaultMTicketQuestStageShouldNotBeFound("optionId.in=" + UPDATED_OPTION_ID);
    }

    @Test
    @Transactional
    public void getAllMTicketQuestStagesByOptionIdIsNullOrNotNull() throws Exception {
        // Initialize the database
        mTicketQuestStageRepository.saveAndFlush(mTicketQuestStage);

        // Get all the mTicketQuestStageList where optionId is not null
        defaultMTicketQuestStageShouldBeFound("optionId.specified=true");

        // Get all the mTicketQuestStageList where optionId is null
        defaultMTicketQuestStageShouldNotBeFound("optionId.specified=false");
    }

    @Test
    @Transactional
    public void getAllMTicketQuestStagesByOptionIdIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mTicketQuestStageRepository.saveAndFlush(mTicketQuestStage);

        // Get all the mTicketQuestStageList where optionId greater than or equals to DEFAULT_OPTION_ID
        defaultMTicketQuestStageShouldBeFound("optionId.greaterOrEqualThan=" + DEFAULT_OPTION_ID);

        // Get all the mTicketQuestStageList where optionId greater than or equals to UPDATED_OPTION_ID
        defaultMTicketQuestStageShouldNotBeFound("optionId.greaterOrEqualThan=" + UPDATED_OPTION_ID);
    }

    @Test
    @Transactional
    public void getAllMTicketQuestStagesByOptionIdIsLessThanSomething() throws Exception {
        // Initialize the database
        mTicketQuestStageRepository.saveAndFlush(mTicketQuestStage);

        // Get all the mTicketQuestStageList where optionId less than or equals to DEFAULT_OPTION_ID
        defaultMTicketQuestStageShouldNotBeFound("optionId.lessThan=" + DEFAULT_OPTION_ID);

        // Get all the mTicketQuestStageList where optionId less than or equals to UPDATED_OPTION_ID
        defaultMTicketQuestStageShouldBeFound("optionId.lessThan=" + UPDATED_OPTION_ID);
    }


    @Test
    @Transactional
    public void getAllMTicketQuestStagesByDeckConditionIdIsEqualToSomething() throws Exception {
        // Initialize the database
        mTicketQuestStageRepository.saveAndFlush(mTicketQuestStage);

        // Get all the mTicketQuestStageList where deckConditionId equals to DEFAULT_DECK_CONDITION_ID
        defaultMTicketQuestStageShouldBeFound("deckConditionId.equals=" + DEFAULT_DECK_CONDITION_ID);

        // Get all the mTicketQuestStageList where deckConditionId equals to UPDATED_DECK_CONDITION_ID
        defaultMTicketQuestStageShouldNotBeFound("deckConditionId.equals=" + UPDATED_DECK_CONDITION_ID);
    }

    @Test
    @Transactional
    public void getAllMTicketQuestStagesByDeckConditionIdIsInShouldWork() throws Exception {
        // Initialize the database
        mTicketQuestStageRepository.saveAndFlush(mTicketQuestStage);

        // Get all the mTicketQuestStageList where deckConditionId in DEFAULT_DECK_CONDITION_ID or UPDATED_DECK_CONDITION_ID
        defaultMTicketQuestStageShouldBeFound("deckConditionId.in=" + DEFAULT_DECK_CONDITION_ID + "," + UPDATED_DECK_CONDITION_ID);

        // Get all the mTicketQuestStageList where deckConditionId equals to UPDATED_DECK_CONDITION_ID
        defaultMTicketQuestStageShouldNotBeFound("deckConditionId.in=" + UPDATED_DECK_CONDITION_ID);
    }

    @Test
    @Transactional
    public void getAllMTicketQuestStagesByDeckConditionIdIsNullOrNotNull() throws Exception {
        // Initialize the database
        mTicketQuestStageRepository.saveAndFlush(mTicketQuestStage);

        // Get all the mTicketQuestStageList where deckConditionId is not null
        defaultMTicketQuestStageShouldBeFound("deckConditionId.specified=true");

        // Get all the mTicketQuestStageList where deckConditionId is null
        defaultMTicketQuestStageShouldNotBeFound("deckConditionId.specified=false");
    }

    @Test
    @Transactional
    public void getAllMTicketQuestStagesByDeckConditionIdIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mTicketQuestStageRepository.saveAndFlush(mTicketQuestStage);

        // Get all the mTicketQuestStageList where deckConditionId greater than or equals to DEFAULT_DECK_CONDITION_ID
        defaultMTicketQuestStageShouldBeFound("deckConditionId.greaterOrEqualThan=" + DEFAULT_DECK_CONDITION_ID);

        // Get all the mTicketQuestStageList where deckConditionId greater than or equals to UPDATED_DECK_CONDITION_ID
        defaultMTicketQuestStageShouldNotBeFound("deckConditionId.greaterOrEqualThan=" + UPDATED_DECK_CONDITION_ID);
    }

    @Test
    @Transactional
    public void getAllMTicketQuestStagesByDeckConditionIdIsLessThanSomething() throws Exception {
        // Initialize the database
        mTicketQuestStageRepository.saveAndFlush(mTicketQuestStage);

        // Get all the mTicketQuestStageList where deckConditionId less than or equals to DEFAULT_DECK_CONDITION_ID
        defaultMTicketQuestStageShouldNotBeFound("deckConditionId.lessThan=" + DEFAULT_DECK_CONDITION_ID);

        // Get all the mTicketQuestStageList where deckConditionId less than or equals to UPDATED_DECK_CONDITION_ID
        defaultMTicketQuestStageShouldBeFound("deckConditionId.lessThan=" + UPDATED_DECK_CONDITION_ID);
    }


    @Test
    @Transactional
    public void getAllMTicketQuestStagesByMticketquestworldIsEqualToSomething() throws Exception {
        // Get already existing entity
        MTicketQuestWorld mticketquestworld = mTicketQuestStage.getMticketquestworld();
        mTicketQuestStageRepository.saveAndFlush(mTicketQuestStage);
        Long mticketquestworldId = mticketquestworld.getId();

        // Get all the mTicketQuestStageList where mticketquestworld equals to mticketquestworldId
        defaultMTicketQuestStageShouldBeFound("mticketquestworldId.equals=" + mticketquestworldId);

        // Get all the mTicketQuestStageList where mticketquestworld equals to mticketquestworldId + 1
        defaultMTicketQuestStageShouldNotBeFound("mticketquestworldId.equals=" + (mticketquestworldId + 1));
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultMTicketQuestStageShouldBeFound(String filter) throws Exception {
        restMTicketQuestStageMockMvc.perform(get("/api/m-ticket-quest-stages?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(mTicketQuestStage.getId().intValue())))
            .andExpect(jsonPath("$.[*].worldId").value(hasItem(DEFAULT_WORLD_ID)))
            .andExpect(jsonPath("$.[*].number").value(hasItem(DEFAULT_NUMBER)))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME.toString())))
            .andExpect(jsonPath("$.[*].ticketId").value(hasItem(DEFAULT_TICKET_ID)))
            .andExpect(jsonPath("$.[*].ticketAmount").value(hasItem(DEFAULT_TICKET_AMOUNT)))
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
        restMTicketQuestStageMockMvc.perform(get("/api/m-ticket-quest-stages/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultMTicketQuestStageShouldNotBeFound(String filter) throws Exception {
        restMTicketQuestStageMockMvc.perform(get("/api/m-ticket-quest-stages?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restMTicketQuestStageMockMvc.perform(get("/api/m-ticket-quest-stages/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("0"));
    }


    @Test
    @Transactional
    public void getNonExistingMTicketQuestStage() throws Exception {
        // Get the mTicketQuestStage
        restMTicketQuestStageMockMvc.perform(get("/api/m-ticket-quest-stages/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateMTicketQuestStage() throws Exception {
        // Initialize the database
        mTicketQuestStageRepository.saveAndFlush(mTicketQuestStage);

        int databaseSizeBeforeUpdate = mTicketQuestStageRepository.findAll().size();

        // Update the mTicketQuestStage
        MTicketQuestStage updatedMTicketQuestStage = mTicketQuestStageRepository.findById(mTicketQuestStage.getId()).get();
        // Disconnect from session so that the updates on updatedMTicketQuestStage are not directly saved in db
        em.detach(updatedMTicketQuestStage);
        updatedMTicketQuestStage
            .worldId(UPDATED_WORLD_ID)
            .number(UPDATED_NUMBER)
            .name(UPDATED_NAME)
            .ticketId(UPDATED_TICKET_ID)
            .ticketAmount(UPDATED_TICKET_AMOUNT)
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
        MTicketQuestStageDTO mTicketQuestStageDTO = mTicketQuestStageMapper.toDto(updatedMTicketQuestStage);

        restMTicketQuestStageMockMvc.perform(put("/api/m-ticket-quest-stages")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mTicketQuestStageDTO)))
            .andExpect(status().isOk());

        // Validate the MTicketQuestStage in the database
        List<MTicketQuestStage> mTicketQuestStageList = mTicketQuestStageRepository.findAll();
        assertThat(mTicketQuestStageList).hasSize(databaseSizeBeforeUpdate);
        MTicketQuestStage testMTicketQuestStage = mTicketQuestStageList.get(mTicketQuestStageList.size() - 1);
        assertThat(testMTicketQuestStage.getWorldId()).isEqualTo(UPDATED_WORLD_ID);
        assertThat(testMTicketQuestStage.getNumber()).isEqualTo(UPDATED_NUMBER);
        assertThat(testMTicketQuestStage.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testMTicketQuestStage.getTicketId()).isEqualTo(UPDATED_TICKET_ID);
        assertThat(testMTicketQuestStage.getTicketAmount()).isEqualTo(UPDATED_TICKET_AMOUNT);
        assertThat(testMTicketQuestStage.getImagePath()).isEqualTo(UPDATED_IMAGE_PATH);
        assertThat(testMTicketQuestStage.getCharacterThumbnailPath()).isEqualTo(UPDATED_CHARACTER_THUMBNAIL_PATH);
        assertThat(testMTicketQuestStage.getDifficulty()).isEqualTo(UPDATED_DIFFICULTY);
        assertThat(testMTicketQuestStage.getStageUnlockPattern()).isEqualTo(UPDATED_STAGE_UNLOCK_PATTERN);
        assertThat(testMTicketQuestStage.getStoryOnly()).isEqualTo(UPDATED_STORY_ONLY);
        assertThat(testMTicketQuestStage.getFirstHalfNpcDeckId()).isEqualTo(UPDATED_FIRST_HALF_NPC_DECK_ID);
        assertThat(testMTicketQuestStage.getFirstHalfEnvironmentId()).isEqualTo(UPDATED_FIRST_HALF_ENVIRONMENT_ID);
        assertThat(testMTicketQuestStage.getSecondHalfNpcDeckId()).isEqualTo(UPDATED_SECOND_HALF_NPC_DECK_ID);
        assertThat(testMTicketQuestStage.getSecondHalfEnvironmentId()).isEqualTo(UPDATED_SECOND_HALF_ENVIRONMENT_ID);
        assertThat(testMTicketQuestStage.getExtraFirstHalfNpcDeckId()).isEqualTo(UPDATED_EXTRA_FIRST_HALF_NPC_DECK_ID);
        assertThat(testMTicketQuestStage.getExtraSecondHalfNpcDeckId()).isEqualTo(UPDATED_EXTRA_SECOND_HALF_NPC_DECK_ID);
        assertThat(testMTicketQuestStage.getConsumeAp()).isEqualTo(UPDATED_CONSUME_AP);
        assertThat(testMTicketQuestStage.getKickOffType()).isEqualTo(UPDATED_KICK_OFF_TYPE);
        assertThat(testMTicketQuestStage.getMatchMinute()).isEqualTo(UPDATED_MATCH_MINUTE);
        assertThat(testMTicketQuestStage.getEnableExtra()).isEqualTo(UPDATED_ENABLE_EXTRA);
        assertThat(testMTicketQuestStage.getEnablePk()).isEqualTo(UPDATED_ENABLE_PK);
        assertThat(testMTicketQuestStage.getAiLevel()).isEqualTo(UPDATED_AI_LEVEL);
        assertThat(testMTicketQuestStage.getStartAtSecondHalf()).isEqualTo(UPDATED_START_AT_SECOND_HALF);
        assertThat(testMTicketQuestStage.getStartScore()).isEqualTo(UPDATED_START_SCORE);
        assertThat(testMTicketQuestStage.getStartScoreOpponent()).isEqualTo(UPDATED_START_SCORE_OPPONENT);
        assertThat(testMTicketQuestStage.getConditionId()).isEqualTo(UPDATED_CONDITION_ID);
        assertThat(testMTicketQuestStage.getOptionId()).isEqualTo(UPDATED_OPTION_ID);
        assertThat(testMTicketQuestStage.getDeckConditionId()).isEqualTo(UPDATED_DECK_CONDITION_ID);
    }

    @Test
    @Transactional
    public void updateNonExistingMTicketQuestStage() throws Exception {
        int databaseSizeBeforeUpdate = mTicketQuestStageRepository.findAll().size();

        // Create the MTicketQuestStage
        MTicketQuestStageDTO mTicketQuestStageDTO = mTicketQuestStageMapper.toDto(mTicketQuestStage);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restMTicketQuestStageMockMvc.perform(put("/api/m-ticket-quest-stages")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mTicketQuestStageDTO)))
            .andExpect(status().isBadRequest());

        // Validate the MTicketQuestStage in the database
        List<MTicketQuestStage> mTicketQuestStageList = mTicketQuestStageRepository.findAll();
        assertThat(mTicketQuestStageList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteMTicketQuestStage() throws Exception {
        // Initialize the database
        mTicketQuestStageRepository.saveAndFlush(mTicketQuestStage);

        int databaseSizeBeforeDelete = mTicketQuestStageRepository.findAll().size();

        // Delete the mTicketQuestStage
        restMTicketQuestStageMockMvc.perform(delete("/api/m-ticket-quest-stages/{id}", mTicketQuestStage.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database is empty
        List<MTicketQuestStage> mTicketQuestStageList = mTicketQuestStageRepository.findAll();
        assertThat(mTicketQuestStageList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(MTicketQuestStage.class);
        MTicketQuestStage mTicketQuestStage1 = new MTicketQuestStage();
        mTicketQuestStage1.setId(1L);
        MTicketQuestStage mTicketQuestStage2 = new MTicketQuestStage();
        mTicketQuestStage2.setId(mTicketQuestStage1.getId());
        assertThat(mTicketQuestStage1).isEqualTo(mTicketQuestStage2);
        mTicketQuestStage2.setId(2L);
        assertThat(mTicketQuestStage1).isNotEqualTo(mTicketQuestStage2);
        mTicketQuestStage1.setId(null);
        assertThat(mTicketQuestStage1).isNotEqualTo(mTicketQuestStage2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(MTicketQuestStageDTO.class);
        MTicketQuestStageDTO mTicketQuestStageDTO1 = new MTicketQuestStageDTO();
        mTicketQuestStageDTO1.setId(1L);
        MTicketQuestStageDTO mTicketQuestStageDTO2 = new MTicketQuestStageDTO();
        assertThat(mTicketQuestStageDTO1).isNotEqualTo(mTicketQuestStageDTO2);
        mTicketQuestStageDTO2.setId(mTicketQuestStageDTO1.getId());
        assertThat(mTicketQuestStageDTO1).isEqualTo(mTicketQuestStageDTO2);
        mTicketQuestStageDTO2.setId(2L);
        assertThat(mTicketQuestStageDTO1).isNotEqualTo(mTicketQuestStageDTO2);
        mTicketQuestStageDTO1.setId(null);
        assertThat(mTicketQuestStageDTO1).isNotEqualTo(mTicketQuestStageDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(mTicketQuestStageMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(mTicketQuestStageMapper.fromId(null)).isNull();
    }
}
