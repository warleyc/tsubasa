package io.shm.tsubasa.web.rest;

import io.shm.tsubasa.TsubasaApp;
import io.shm.tsubasa.domain.MDeckCondition;
import io.shm.tsubasa.repository.MDeckConditionRepository;
import io.shm.tsubasa.service.MDeckConditionService;
import io.shm.tsubasa.service.dto.MDeckConditionDTO;
import io.shm.tsubasa.service.mapper.MDeckConditionMapper;
import io.shm.tsubasa.web.rest.errors.ExceptionTranslator;
import io.shm.tsubasa.service.dto.MDeckConditionCriteria;
import io.shm.tsubasa.service.MDeckConditionQueryService;

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
import org.springframework.validation.Validator;

import javax.persistence.EntityManager;
import java.util.List;

import static io.shm.tsubasa.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the {@Link MDeckConditionResource} REST controller.
 */
@SpringBootTest(classes = TsubasaApp.class)
public class MDeckConditionResourceIT {

    private static final Integer DEFAULT_TARGET_FORMATION_GROUP_ID = 1;
    private static final Integer UPDATED_TARGET_FORMATION_GROUP_ID = 2;

    private static final Integer DEFAULT_TARGET_CHARACTER_GROUP_MIN_ID = 1;
    private static final Integer UPDATED_TARGET_CHARACTER_GROUP_MIN_ID = 2;

    private static final Integer DEFAULT_TARGET_CHARACTER_GROUP_MAX_ID = 1;
    private static final Integer UPDATED_TARGET_CHARACTER_GROUP_MAX_ID = 2;

    private static final Integer DEFAULT_TARGET_PLAYABLE_CARD_GROUP_MIN_ID = 1;
    private static final Integer UPDATED_TARGET_PLAYABLE_CARD_GROUP_MIN_ID = 2;

    private static final Integer DEFAULT_TARGET_PLAYABLE_CARD_GROUP_MAX_ID = 1;
    private static final Integer UPDATED_TARGET_PLAYABLE_CARD_GROUP_MAX_ID = 2;

    private static final Integer DEFAULT_TARGET_RARITY_GROUP_ID = 1;
    private static final Integer UPDATED_TARGET_RARITY_GROUP_ID = 2;

    private static final Integer DEFAULT_TARGET_ATTRIBUTE = 1;
    private static final Integer UPDATED_TARGET_ATTRIBUTE = 2;

    private static final Integer DEFAULT_TARGET_NATIONALITY_GROUP_MIN_ID = 1;
    private static final Integer UPDATED_TARGET_NATIONALITY_GROUP_MIN_ID = 2;

    private static final Integer DEFAULT_TARGET_NATIONALITY_GROUP_MAX_ID = 1;
    private static final Integer UPDATED_TARGET_NATIONALITY_GROUP_MAX_ID = 2;

    private static final Integer DEFAULT_TARGET_TEAM_GROUP_MIN_ID = 1;
    private static final Integer UPDATED_TARGET_TEAM_GROUP_MIN_ID = 2;

    private static final Integer DEFAULT_TARGET_TEAM_GROUP_MAX_ID = 1;
    private static final Integer UPDATED_TARGET_TEAM_GROUP_MAX_ID = 2;

    private static final Integer DEFAULT_TARGET_ACTION_GROUP_MIN_ID = 1;
    private static final Integer UPDATED_TARGET_ACTION_GROUP_MIN_ID = 2;

    private static final Integer DEFAULT_TARGET_ACTION_GROUP_MAX_ID = 1;
    private static final Integer UPDATED_TARGET_ACTION_GROUP_MAX_ID = 2;

    private static final Integer DEFAULT_TARGET_TRIGGER_EFFECT_GROUP_MIN_ID = 1;
    private static final Integer UPDATED_TARGET_TRIGGER_EFFECT_GROUP_MIN_ID = 2;

    private static final Integer DEFAULT_TARGET_TRIGGER_EFFECT_GROUP_MAX_ID = 1;
    private static final Integer UPDATED_TARGET_TRIGGER_EFFECT_GROUP_MAX_ID = 2;

    private static final Integer DEFAULT_TARGET_CHARACTER_MIN_COUNT = 1;
    private static final Integer UPDATED_TARGET_CHARACTER_MIN_COUNT = 2;

    private static final Integer DEFAULT_TARGET_CHARACTER_MAX_COUNT = 1;
    private static final Integer UPDATED_TARGET_CHARACTER_MAX_COUNT = 2;

    private static final Integer DEFAULT_TARGET_PLAYABLE_CARD_MIN_COUNT = 1;
    private static final Integer UPDATED_TARGET_PLAYABLE_CARD_MIN_COUNT = 2;

    private static final Integer DEFAULT_TARGET_PLAYABLE_CARD_MAX_COUNT = 1;
    private static final Integer UPDATED_TARGET_PLAYABLE_CARD_MAX_COUNT = 2;

    private static final Integer DEFAULT_TARGET_RARITY_MAX_COUNT = 1;
    private static final Integer UPDATED_TARGET_RARITY_MAX_COUNT = 2;

    private static final Integer DEFAULT_TARGET_ATTRIBUTE_MIN_COUNT = 1;
    private static final Integer UPDATED_TARGET_ATTRIBUTE_MIN_COUNT = 2;

    private static final Integer DEFAULT_TARGET_NATIONALITY_MIN_COUNT = 1;
    private static final Integer UPDATED_TARGET_NATIONALITY_MIN_COUNT = 2;

    private static final Integer DEFAULT_TARGET_NATIONALITY_MAX_COUNT = 1;
    private static final Integer UPDATED_TARGET_NATIONALITY_MAX_COUNT = 2;

    private static final Integer DEFAULT_TARGET_TEAM_MIN_COUNT = 1;
    private static final Integer UPDATED_TARGET_TEAM_MIN_COUNT = 2;

    private static final Integer DEFAULT_TARGET_TEAM_MAX_COUNT = 1;
    private static final Integer UPDATED_TARGET_TEAM_MAX_COUNT = 2;

    private static final Integer DEFAULT_TARGET_ACTION_MIN_COUNT = 1;
    private static final Integer UPDATED_TARGET_ACTION_MIN_COUNT = 2;

    private static final Integer DEFAULT_TARGET_ACTION_MAX_COUNT = 1;
    private static final Integer UPDATED_TARGET_ACTION_MAX_COUNT = 2;

    private static final Integer DEFAULT_TARGET_TRIGGER_EFFECT_MIN_COUNT = 1;
    private static final Integer UPDATED_TARGET_TRIGGER_EFFECT_MIN_COUNT = 2;

    private static final Integer DEFAULT_TARGET_TRIGGER_EFFECT_MAX_COUNT = 1;
    private static final Integer UPDATED_TARGET_TRIGGER_EFFECT_MAX_COUNT = 2;

    private static final Integer DEFAULT_TARGET_CHARACTER_IS_STARTING_MIN = 1;
    private static final Integer UPDATED_TARGET_CHARACTER_IS_STARTING_MIN = 2;

    private static final Integer DEFAULT_TARGET_CHARACTER_IS_STARTING_MAX = 1;
    private static final Integer UPDATED_TARGET_CHARACTER_IS_STARTING_MAX = 2;

    private static final Integer DEFAULT_TARGET_PLAYABLE_CARD_IS_STARTING_MIN = 1;
    private static final Integer UPDATED_TARGET_PLAYABLE_CARD_IS_STARTING_MIN = 2;

    private static final Integer DEFAULT_TARGET_PLAYABLE_CARD_IS_STARTING_MAX = 1;
    private static final Integer UPDATED_TARGET_PLAYABLE_CARD_IS_STARTING_MAX = 2;

    private static final Integer DEFAULT_TARGET_RARITY_IS_STARTING = 1;
    private static final Integer UPDATED_TARGET_RARITY_IS_STARTING = 2;

    private static final Integer DEFAULT_TARGET_ATTRIBUTE_IS_STARTING = 1;
    private static final Integer UPDATED_TARGET_ATTRIBUTE_IS_STARTING = 2;

    private static final Integer DEFAULT_TARGET_NATIONALITY_IS_STARTING_MIN = 1;
    private static final Integer UPDATED_TARGET_NATIONALITY_IS_STARTING_MIN = 2;

    private static final Integer DEFAULT_TARGET_NATIONALITY_IS_STARTING_MAX = 1;
    private static final Integer UPDATED_TARGET_NATIONALITY_IS_STARTING_MAX = 2;

    private static final Integer DEFAULT_TARGET_TEAM_IS_STARTING_MIN = 1;
    private static final Integer UPDATED_TARGET_TEAM_IS_STARTING_MIN = 2;

    private static final Integer DEFAULT_TARGET_TEAM_IS_STARTING_MAX = 1;
    private static final Integer UPDATED_TARGET_TEAM_IS_STARTING_MAX = 2;

    private static final Integer DEFAULT_TARGET_ACTION_IS_STARTING_MIN = 1;
    private static final Integer UPDATED_TARGET_ACTION_IS_STARTING_MIN = 2;

    private static final Integer DEFAULT_TARGET_ACTION_IS_STARTING_MAX = 1;
    private static final Integer UPDATED_TARGET_ACTION_IS_STARTING_MAX = 2;

    private static final Integer DEFAULT_TARGET_TRIGGER_EFFECT_IS_STARTING_MIN = 1;
    private static final Integer UPDATED_TARGET_TRIGGER_EFFECT_IS_STARTING_MIN = 2;

    private static final Integer DEFAULT_TARGET_TRIGGER_EFFECT_IS_STARTING_MAX = 1;
    private static final Integer UPDATED_TARGET_TRIGGER_EFFECT_IS_STARTING_MAX = 2;

    @Autowired
    private MDeckConditionRepository mDeckConditionRepository;

    @Autowired
    private MDeckConditionMapper mDeckConditionMapper;

    @Autowired
    private MDeckConditionService mDeckConditionService;

    @Autowired
    private MDeckConditionQueryService mDeckConditionQueryService;

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

    private MockMvc restMDeckConditionMockMvc;

    private MDeckCondition mDeckCondition;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final MDeckConditionResource mDeckConditionResource = new MDeckConditionResource(mDeckConditionService, mDeckConditionQueryService);
        this.restMDeckConditionMockMvc = MockMvcBuilders.standaloneSetup(mDeckConditionResource)
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
    public static MDeckCondition createEntity(EntityManager em) {
        MDeckCondition mDeckCondition = new MDeckCondition()
            .targetFormationGroupId(DEFAULT_TARGET_FORMATION_GROUP_ID)
            .targetCharacterGroupMinId(DEFAULT_TARGET_CHARACTER_GROUP_MIN_ID)
            .targetCharacterGroupMaxId(DEFAULT_TARGET_CHARACTER_GROUP_MAX_ID)
            .targetPlayableCardGroupMinId(DEFAULT_TARGET_PLAYABLE_CARD_GROUP_MIN_ID)
            .targetPlayableCardGroupMaxId(DEFAULT_TARGET_PLAYABLE_CARD_GROUP_MAX_ID)
            .targetRarityGroupId(DEFAULT_TARGET_RARITY_GROUP_ID)
            .targetAttribute(DEFAULT_TARGET_ATTRIBUTE)
            .targetNationalityGroupMinId(DEFAULT_TARGET_NATIONALITY_GROUP_MIN_ID)
            .targetNationalityGroupMaxId(DEFAULT_TARGET_NATIONALITY_GROUP_MAX_ID)
            .targetTeamGroupMinId(DEFAULT_TARGET_TEAM_GROUP_MIN_ID)
            .targetTeamGroupMaxId(DEFAULT_TARGET_TEAM_GROUP_MAX_ID)
            .targetActionGroupMinId(DEFAULT_TARGET_ACTION_GROUP_MIN_ID)
            .targetActionGroupMaxId(DEFAULT_TARGET_ACTION_GROUP_MAX_ID)
            .targetTriggerEffectGroupMinId(DEFAULT_TARGET_TRIGGER_EFFECT_GROUP_MIN_ID)
            .targetTriggerEffectGroupMaxId(DEFAULT_TARGET_TRIGGER_EFFECT_GROUP_MAX_ID)
            .targetCharacterMinCount(DEFAULT_TARGET_CHARACTER_MIN_COUNT)
            .targetCharacterMaxCount(DEFAULT_TARGET_CHARACTER_MAX_COUNT)
            .targetPlayableCardMinCount(DEFAULT_TARGET_PLAYABLE_CARD_MIN_COUNT)
            .targetPlayableCardMaxCount(DEFAULT_TARGET_PLAYABLE_CARD_MAX_COUNT)
            .targetRarityMaxCount(DEFAULT_TARGET_RARITY_MAX_COUNT)
            .targetAttributeMinCount(DEFAULT_TARGET_ATTRIBUTE_MIN_COUNT)
            .targetNationalityMinCount(DEFAULT_TARGET_NATIONALITY_MIN_COUNT)
            .targetNationalityMaxCount(DEFAULT_TARGET_NATIONALITY_MAX_COUNT)
            .targetTeamMinCount(DEFAULT_TARGET_TEAM_MIN_COUNT)
            .targetTeamMaxCount(DEFAULT_TARGET_TEAM_MAX_COUNT)
            .targetActionMinCount(DEFAULT_TARGET_ACTION_MIN_COUNT)
            .targetActionMaxCount(DEFAULT_TARGET_ACTION_MAX_COUNT)
            .targetTriggerEffectMinCount(DEFAULT_TARGET_TRIGGER_EFFECT_MIN_COUNT)
            .targetTriggerEffectMaxCount(DEFAULT_TARGET_TRIGGER_EFFECT_MAX_COUNT)
            .targetCharacterIsStartingMin(DEFAULT_TARGET_CHARACTER_IS_STARTING_MIN)
            .targetCharacterIsStartingMax(DEFAULT_TARGET_CHARACTER_IS_STARTING_MAX)
            .targetPlayableCardIsStartingMin(DEFAULT_TARGET_PLAYABLE_CARD_IS_STARTING_MIN)
            .targetPlayableCardIsStartingMax(DEFAULT_TARGET_PLAYABLE_CARD_IS_STARTING_MAX)
            .targetRarityIsStarting(DEFAULT_TARGET_RARITY_IS_STARTING)
            .targetAttributeIsStarting(DEFAULT_TARGET_ATTRIBUTE_IS_STARTING)
            .targetNationalityIsStartingMin(DEFAULT_TARGET_NATIONALITY_IS_STARTING_MIN)
            .targetNationalityIsStartingMax(DEFAULT_TARGET_NATIONALITY_IS_STARTING_MAX)
            .targetTeamIsStartingMin(DEFAULT_TARGET_TEAM_IS_STARTING_MIN)
            .targetTeamIsStartingMax(DEFAULT_TARGET_TEAM_IS_STARTING_MAX)
            .targetActionIsStartingMin(DEFAULT_TARGET_ACTION_IS_STARTING_MIN)
            .targetActionIsStartingMax(DEFAULT_TARGET_ACTION_IS_STARTING_MAX)
            .targetTriggerEffectIsStartingMin(DEFAULT_TARGET_TRIGGER_EFFECT_IS_STARTING_MIN)
            .targetTriggerEffectIsStartingMax(DEFAULT_TARGET_TRIGGER_EFFECT_IS_STARTING_MAX);
        return mDeckCondition;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static MDeckCondition createUpdatedEntity(EntityManager em) {
        MDeckCondition mDeckCondition = new MDeckCondition()
            .targetFormationGroupId(UPDATED_TARGET_FORMATION_GROUP_ID)
            .targetCharacterGroupMinId(UPDATED_TARGET_CHARACTER_GROUP_MIN_ID)
            .targetCharacterGroupMaxId(UPDATED_TARGET_CHARACTER_GROUP_MAX_ID)
            .targetPlayableCardGroupMinId(UPDATED_TARGET_PLAYABLE_CARD_GROUP_MIN_ID)
            .targetPlayableCardGroupMaxId(UPDATED_TARGET_PLAYABLE_CARD_GROUP_MAX_ID)
            .targetRarityGroupId(UPDATED_TARGET_RARITY_GROUP_ID)
            .targetAttribute(UPDATED_TARGET_ATTRIBUTE)
            .targetNationalityGroupMinId(UPDATED_TARGET_NATIONALITY_GROUP_MIN_ID)
            .targetNationalityGroupMaxId(UPDATED_TARGET_NATIONALITY_GROUP_MAX_ID)
            .targetTeamGroupMinId(UPDATED_TARGET_TEAM_GROUP_MIN_ID)
            .targetTeamGroupMaxId(UPDATED_TARGET_TEAM_GROUP_MAX_ID)
            .targetActionGroupMinId(UPDATED_TARGET_ACTION_GROUP_MIN_ID)
            .targetActionGroupMaxId(UPDATED_TARGET_ACTION_GROUP_MAX_ID)
            .targetTriggerEffectGroupMinId(UPDATED_TARGET_TRIGGER_EFFECT_GROUP_MIN_ID)
            .targetTriggerEffectGroupMaxId(UPDATED_TARGET_TRIGGER_EFFECT_GROUP_MAX_ID)
            .targetCharacterMinCount(UPDATED_TARGET_CHARACTER_MIN_COUNT)
            .targetCharacterMaxCount(UPDATED_TARGET_CHARACTER_MAX_COUNT)
            .targetPlayableCardMinCount(UPDATED_TARGET_PLAYABLE_CARD_MIN_COUNT)
            .targetPlayableCardMaxCount(UPDATED_TARGET_PLAYABLE_CARD_MAX_COUNT)
            .targetRarityMaxCount(UPDATED_TARGET_RARITY_MAX_COUNT)
            .targetAttributeMinCount(UPDATED_TARGET_ATTRIBUTE_MIN_COUNT)
            .targetNationalityMinCount(UPDATED_TARGET_NATIONALITY_MIN_COUNT)
            .targetNationalityMaxCount(UPDATED_TARGET_NATIONALITY_MAX_COUNT)
            .targetTeamMinCount(UPDATED_TARGET_TEAM_MIN_COUNT)
            .targetTeamMaxCount(UPDATED_TARGET_TEAM_MAX_COUNT)
            .targetActionMinCount(UPDATED_TARGET_ACTION_MIN_COUNT)
            .targetActionMaxCount(UPDATED_TARGET_ACTION_MAX_COUNT)
            .targetTriggerEffectMinCount(UPDATED_TARGET_TRIGGER_EFFECT_MIN_COUNT)
            .targetTriggerEffectMaxCount(UPDATED_TARGET_TRIGGER_EFFECT_MAX_COUNT)
            .targetCharacterIsStartingMin(UPDATED_TARGET_CHARACTER_IS_STARTING_MIN)
            .targetCharacterIsStartingMax(UPDATED_TARGET_CHARACTER_IS_STARTING_MAX)
            .targetPlayableCardIsStartingMin(UPDATED_TARGET_PLAYABLE_CARD_IS_STARTING_MIN)
            .targetPlayableCardIsStartingMax(UPDATED_TARGET_PLAYABLE_CARD_IS_STARTING_MAX)
            .targetRarityIsStarting(UPDATED_TARGET_RARITY_IS_STARTING)
            .targetAttributeIsStarting(UPDATED_TARGET_ATTRIBUTE_IS_STARTING)
            .targetNationalityIsStartingMin(UPDATED_TARGET_NATIONALITY_IS_STARTING_MIN)
            .targetNationalityIsStartingMax(UPDATED_TARGET_NATIONALITY_IS_STARTING_MAX)
            .targetTeamIsStartingMin(UPDATED_TARGET_TEAM_IS_STARTING_MIN)
            .targetTeamIsStartingMax(UPDATED_TARGET_TEAM_IS_STARTING_MAX)
            .targetActionIsStartingMin(UPDATED_TARGET_ACTION_IS_STARTING_MIN)
            .targetActionIsStartingMax(UPDATED_TARGET_ACTION_IS_STARTING_MAX)
            .targetTriggerEffectIsStartingMin(UPDATED_TARGET_TRIGGER_EFFECT_IS_STARTING_MIN)
            .targetTriggerEffectIsStartingMax(UPDATED_TARGET_TRIGGER_EFFECT_IS_STARTING_MAX);
        return mDeckCondition;
    }

    @BeforeEach
    public void initTest() {
        mDeckCondition = createEntity(em);
    }

    @Test
    @Transactional
    public void createMDeckCondition() throws Exception {
        int databaseSizeBeforeCreate = mDeckConditionRepository.findAll().size();

        // Create the MDeckCondition
        MDeckConditionDTO mDeckConditionDTO = mDeckConditionMapper.toDto(mDeckCondition);
        restMDeckConditionMockMvc.perform(post("/api/m-deck-conditions")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mDeckConditionDTO)))
            .andExpect(status().isCreated());

        // Validate the MDeckCondition in the database
        List<MDeckCondition> mDeckConditionList = mDeckConditionRepository.findAll();
        assertThat(mDeckConditionList).hasSize(databaseSizeBeforeCreate + 1);
        MDeckCondition testMDeckCondition = mDeckConditionList.get(mDeckConditionList.size() - 1);
        assertThat(testMDeckCondition.getTargetFormationGroupId()).isEqualTo(DEFAULT_TARGET_FORMATION_GROUP_ID);
        assertThat(testMDeckCondition.getTargetCharacterGroupMinId()).isEqualTo(DEFAULT_TARGET_CHARACTER_GROUP_MIN_ID);
        assertThat(testMDeckCondition.getTargetCharacterGroupMaxId()).isEqualTo(DEFAULT_TARGET_CHARACTER_GROUP_MAX_ID);
        assertThat(testMDeckCondition.getTargetPlayableCardGroupMinId()).isEqualTo(DEFAULT_TARGET_PLAYABLE_CARD_GROUP_MIN_ID);
        assertThat(testMDeckCondition.getTargetPlayableCardGroupMaxId()).isEqualTo(DEFAULT_TARGET_PLAYABLE_CARD_GROUP_MAX_ID);
        assertThat(testMDeckCondition.getTargetRarityGroupId()).isEqualTo(DEFAULT_TARGET_RARITY_GROUP_ID);
        assertThat(testMDeckCondition.getTargetAttribute()).isEqualTo(DEFAULT_TARGET_ATTRIBUTE);
        assertThat(testMDeckCondition.getTargetNationalityGroupMinId()).isEqualTo(DEFAULT_TARGET_NATIONALITY_GROUP_MIN_ID);
        assertThat(testMDeckCondition.getTargetNationalityGroupMaxId()).isEqualTo(DEFAULT_TARGET_NATIONALITY_GROUP_MAX_ID);
        assertThat(testMDeckCondition.getTargetTeamGroupMinId()).isEqualTo(DEFAULT_TARGET_TEAM_GROUP_MIN_ID);
        assertThat(testMDeckCondition.getTargetTeamGroupMaxId()).isEqualTo(DEFAULT_TARGET_TEAM_GROUP_MAX_ID);
        assertThat(testMDeckCondition.getTargetActionGroupMinId()).isEqualTo(DEFAULT_TARGET_ACTION_GROUP_MIN_ID);
        assertThat(testMDeckCondition.getTargetActionGroupMaxId()).isEqualTo(DEFAULT_TARGET_ACTION_GROUP_MAX_ID);
        assertThat(testMDeckCondition.getTargetTriggerEffectGroupMinId()).isEqualTo(DEFAULT_TARGET_TRIGGER_EFFECT_GROUP_MIN_ID);
        assertThat(testMDeckCondition.getTargetTriggerEffectGroupMaxId()).isEqualTo(DEFAULT_TARGET_TRIGGER_EFFECT_GROUP_MAX_ID);
        assertThat(testMDeckCondition.getTargetCharacterMinCount()).isEqualTo(DEFAULT_TARGET_CHARACTER_MIN_COUNT);
        assertThat(testMDeckCondition.getTargetCharacterMaxCount()).isEqualTo(DEFAULT_TARGET_CHARACTER_MAX_COUNT);
        assertThat(testMDeckCondition.getTargetPlayableCardMinCount()).isEqualTo(DEFAULT_TARGET_PLAYABLE_CARD_MIN_COUNT);
        assertThat(testMDeckCondition.getTargetPlayableCardMaxCount()).isEqualTo(DEFAULT_TARGET_PLAYABLE_CARD_MAX_COUNT);
        assertThat(testMDeckCondition.getTargetRarityMaxCount()).isEqualTo(DEFAULT_TARGET_RARITY_MAX_COUNT);
        assertThat(testMDeckCondition.getTargetAttributeMinCount()).isEqualTo(DEFAULT_TARGET_ATTRIBUTE_MIN_COUNT);
        assertThat(testMDeckCondition.getTargetNationalityMinCount()).isEqualTo(DEFAULT_TARGET_NATIONALITY_MIN_COUNT);
        assertThat(testMDeckCondition.getTargetNationalityMaxCount()).isEqualTo(DEFAULT_TARGET_NATIONALITY_MAX_COUNT);
        assertThat(testMDeckCondition.getTargetTeamMinCount()).isEqualTo(DEFAULT_TARGET_TEAM_MIN_COUNT);
        assertThat(testMDeckCondition.getTargetTeamMaxCount()).isEqualTo(DEFAULT_TARGET_TEAM_MAX_COUNT);
        assertThat(testMDeckCondition.getTargetActionMinCount()).isEqualTo(DEFAULT_TARGET_ACTION_MIN_COUNT);
        assertThat(testMDeckCondition.getTargetActionMaxCount()).isEqualTo(DEFAULT_TARGET_ACTION_MAX_COUNT);
        assertThat(testMDeckCondition.getTargetTriggerEffectMinCount()).isEqualTo(DEFAULT_TARGET_TRIGGER_EFFECT_MIN_COUNT);
        assertThat(testMDeckCondition.getTargetTriggerEffectMaxCount()).isEqualTo(DEFAULT_TARGET_TRIGGER_EFFECT_MAX_COUNT);
        assertThat(testMDeckCondition.getTargetCharacterIsStartingMin()).isEqualTo(DEFAULT_TARGET_CHARACTER_IS_STARTING_MIN);
        assertThat(testMDeckCondition.getTargetCharacterIsStartingMax()).isEqualTo(DEFAULT_TARGET_CHARACTER_IS_STARTING_MAX);
        assertThat(testMDeckCondition.getTargetPlayableCardIsStartingMin()).isEqualTo(DEFAULT_TARGET_PLAYABLE_CARD_IS_STARTING_MIN);
        assertThat(testMDeckCondition.getTargetPlayableCardIsStartingMax()).isEqualTo(DEFAULT_TARGET_PLAYABLE_CARD_IS_STARTING_MAX);
        assertThat(testMDeckCondition.getTargetRarityIsStarting()).isEqualTo(DEFAULT_TARGET_RARITY_IS_STARTING);
        assertThat(testMDeckCondition.getTargetAttributeIsStarting()).isEqualTo(DEFAULT_TARGET_ATTRIBUTE_IS_STARTING);
        assertThat(testMDeckCondition.getTargetNationalityIsStartingMin()).isEqualTo(DEFAULT_TARGET_NATIONALITY_IS_STARTING_MIN);
        assertThat(testMDeckCondition.getTargetNationalityIsStartingMax()).isEqualTo(DEFAULT_TARGET_NATIONALITY_IS_STARTING_MAX);
        assertThat(testMDeckCondition.getTargetTeamIsStartingMin()).isEqualTo(DEFAULT_TARGET_TEAM_IS_STARTING_MIN);
        assertThat(testMDeckCondition.getTargetTeamIsStartingMax()).isEqualTo(DEFAULT_TARGET_TEAM_IS_STARTING_MAX);
        assertThat(testMDeckCondition.getTargetActionIsStartingMin()).isEqualTo(DEFAULT_TARGET_ACTION_IS_STARTING_MIN);
        assertThat(testMDeckCondition.getTargetActionIsStartingMax()).isEqualTo(DEFAULT_TARGET_ACTION_IS_STARTING_MAX);
        assertThat(testMDeckCondition.getTargetTriggerEffectIsStartingMin()).isEqualTo(DEFAULT_TARGET_TRIGGER_EFFECT_IS_STARTING_MIN);
        assertThat(testMDeckCondition.getTargetTriggerEffectIsStartingMax()).isEqualTo(DEFAULT_TARGET_TRIGGER_EFFECT_IS_STARTING_MAX);
    }

    @Test
    @Transactional
    public void createMDeckConditionWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = mDeckConditionRepository.findAll().size();

        // Create the MDeckCondition with an existing ID
        mDeckCondition.setId(1L);
        MDeckConditionDTO mDeckConditionDTO = mDeckConditionMapper.toDto(mDeckCondition);

        // An entity with an existing ID cannot be created, so this API call must fail
        restMDeckConditionMockMvc.perform(post("/api/m-deck-conditions")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mDeckConditionDTO)))
            .andExpect(status().isBadRequest());

        // Validate the MDeckCondition in the database
        List<MDeckCondition> mDeckConditionList = mDeckConditionRepository.findAll();
        assertThat(mDeckConditionList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkTargetCharacterMinCountIsRequired() throws Exception {
        int databaseSizeBeforeTest = mDeckConditionRepository.findAll().size();
        // set the field null
        mDeckCondition.setTargetCharacterMinCount(null);

        // Create the MDeckCondition, which fails.
        MDeckConditionDTO mDeckConditionDTO = mDeckConditionMapper.toDto(mDeckCondition);

        restMDeckConditionMockMvc.perform(post("/api/m-deck-conditions")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mDeckConditionDTO)))
            .andExpect(status().isBadRequest());

        List<MDeckCondition> mDeckConditionList = mDeckConditionRepository.findAll();
        assertThat(mDeckConditionList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkTargetCharacterMaxCountIsRequired() throws Exception {
        int databaseSizeBeforeTest = mDeckConditionRepository.findAll().size();
        // set the field null
        mDeckCondition.setTargetCharacterMaxCount(null);

        // Create the MDeckCondition, which fails.
        MDeckConditionDTO mDeckConditionDTO = mDeckConditionMapper.toDto(mDeckCondition);

        restMDeckConditionMockMvc.perform(post("/api/m-deck-conditions")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mDeckConditionDTO)))
            .andExpect(status().isBadRequest());

        List<MDeckCondition> mDeckConditionList = mDeckConditionRepository.findAll();
        assertThat(mDeckConditionList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkTargetPlayableCardMinCountIsRequired() throws Exception {
        int databaseSizeBeforeTest = mDeckConditionRepository.findAll().size();
        // set the field null
        mDeckCondition.setTargetPlayableCardMinCount(null);

        // Create the MDeckCondition, which fails.
        MDeckConditionDTO mDeckConditionDTO = mDeckConditionMapper.toDto(mDeckCondition);

        restMDeckConditionMockMvc.perform(post("/api/m-deck-conditions")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mDeckConditionDTO)))
            .andExpect(status().isBadRequest());

        List<MDeckCondition> mDeckConditionList = mDeckConditionRepository.findAll();
        assertThat(mDeckConditionList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkTargetPlayableCardMaxCountIsRequired() throws Exception {
        int databaseSizeBeforeTest = mDeckConditionRepository.findAll().size();
        // set the field null
        mDeckCondition.setTargetPlayableCardMaxCount(null);

        // Create the MDeckCondition, which fails.
        MDeckConditionDTO mDeckConditionDTO = mDeckConditionMapper.toDto(mDeckCondition);

        restMDeckConditionMockMvc.perform(post("/api/m-deck-conditions")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mDeckConditionDTO)))
            .andExpect(status().isBadRequest());

        List<MDeckCondition> mDeckConditionList = mDeckConditionRepository.findAll();
        assertThat(mDeckConditionList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkTargetRarityMaxCountIsRequired() throws Exception {
        int databaseSizeBeforeTest = mDeckConditionRepository.findAll().size();
        // set the field null
        mDeckCondition.setTargetRarityMaxCount(null);

        // Create the MDeckCondition, which fails.
        MDeckConditionDTO mDeckConditionDTO = mDeckConditionMapper.toDto(mDeckCondition);

        restMDeckConditionMockMvc.perform(post("/api/m-deck-conditions")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mDeckConditionDTO)))
            .andExpect(status().isBadRequest());

        List<MDeckCondition> mDeckConditionList = mDeckConditionRepository.findAll();
        assertThat(mDeckConditionList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkTargetAttributeMinCountIsRequired() throws Exception {
        int databaseSizeBeforeTest = mDeckConditionRepository.findAll().size();
        // set the field null
        mDeckCondition.setTargetAttributeMinCount(null);

        // Create the MDeckCondition, which fails.
        MDeckConditionDTO mDeckConditionDTO = mDeckConditionMapper.toDto(mDeckCondition);

        restMDeckConditionMockMvc.perform(post("/api/m-deck-conditions")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mDeckConditionDTO)))
            .andExpect(status().isBadRequest());

        List<MDeckCondition> mDeckConditionList = mDeckConditionRepository.findAll();
        assertThat(mDeckConditionList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkTargetNationalityMinCountIsRequired() throws Exception {
        int databaseSizeBeforeTest = mDeckConditionRepository.findAll().size();
        // set the field null
        mDeckCondition.setTargetNationalityMinCount(null);

        // Create the MDeckCondition, which fails.
        MDeckConditionDTO mDeckConditionDTO = mDeckConditionMapper.toDto(mDeckCondition);

        restMDeckConditionMockMvc.perform(post("/api/m-deck-conditions")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mDeckConditionDTO)))
            .andExpect(status().isBadRequest());

        List<MDeckCondition> mDeckConditionList = mDeckConditionRepository.findAll();
        assertThat(mDeckConditionList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkTargetNationalityMaxCountIsRequired() throws Exception {
        int databaseSizeBeforeTest = mDeckConditionRepository.findAll().size();
        // set the field null
        mDeckCondition.setTargetNationalityMaxCount(null);

        // Create the MDeckCondition, which fails.
        MDeckConditionDTO mDeckConditionDTO = mDeckConditionMapper.toDto(mDeckCondition);

        restMDeckConditionMockMvc.perform(post("/api/m-deck-conditions")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mDeckConditionDTO)))
            .andExpect(status().isBadRequest());

        List<MDeckCondition> mDeckConditionList = mDeckConditionRepository.findAll();
        assertThat(mDeckConditionList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkTargetTeamMinCountIsRequired() throws Exception {
        int databaseSizeBeforeTest = mDeckConditionRepository.findAll().size();
        // set the field null
        mDeckCondition.setTargetTeamMinCount(null);

        // Create the MDeckCondition, which fails.
        MDeckConditionDTO mDeckConditionDTO = mDeckConditionMapper.toDto(mDeckCondition);

        restMDeckConditionMockMvc.perform(post("/api/m-deck-conditions")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mDeckConditionDTO)))
            .andExpect(status().isBadRequest());

        List<MDeckCondition> mDeckConditionList = mDeckConditionRepository.findAll();
        assertThat(mDeckConditionList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkTargetTeamMaxCountIsRequired() throws Exception {
        int databaseSizeBeforeTest = mDeckConditionRepository.findAll().size();
        // set the field null
        mDeckCondition.setTargetTeamMaxCount(null);

        // Create the MDeckCondition, which fails.
        MDeckConditionDTO mDeckConditionDTO = mDeckConditionMapper.toDto(mDeckCondition);

        restMDeckConditionMockMvc.perform(post("/api/m-deck-conditions")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mDeckConditionDTO)))
            .andExpect(status().isBadRequest());

        List<MDeckCondition> mDeckConditionList = mDeckConditionRepository.findAll();
        assertThat(mDeckConditionList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkTargetActionMinCountIsRequired() throws Exception {
        int databaseSizeBeforeTest = mDeckConditionRepository.findAll().size();
        // set the field null
        mDeckCondition.setTargetActionMinCount(null);

        // Create the MDeckCondition, which fails.
        MDeckConditionDTO mDeckConditionDTO = mDeckConditionMapper.toDto(mDeckCondition);

        restMDeckConditionMockMvc.perform(post("/api/m-deck-conditions")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mDeckConditionDTO)))
            .andExpect(status().isBadRequest());

        List<MDeckCondition> mDeckConditionList = mDeckConditionRepository.findAll();
        assertThat(mDeckConditionList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkTargetActionMaxCountIsRequired() throws Exception {
        int databaseSizeBeforeTest = mDeckConditionRepository.findAll().size();
        // set the field null
        mDeckCondition.setTargetActionMaxCount(null);

        // Create the MDeckCondition, which fails.
        MDeckConditionDTO mDeckConditionDTO = mDeckConditionMapper.toDto(mDeckCondition);

        restMDeckConditionMockMvc.perform(post("/api/m-deck-conditions")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mDeckConditionDTO)))
            .andExpect(status().isBadRequest());

        List<MDeckCondition> mDeckConditionList = mDeckConditionRepository.findAll();
        assertThat(mDeckConditionList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkTargetTriggerEffectMinCountIsRequired() throws Exception {
        int databaseSizeBeforeTest = mDeckConditionRepository.findAll().size();
        // set the field null
        mDeckCondition.setTargetTriggerEffectMinCount(null);

        // Create the MDeckCondition, which fails.
        MDeckConditionDTO mDeckConditionDTO = mDeckConditionMapper.toDto(mDeckCondition);

        restMDeckConditionMockMvc.perform(post("/api/m-deck-conditions")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mDeckConditionDTO)))
            .andExpect(status().isBadRequest());

        List<MDeckCondition> mDeckConditionList = mDeckConditionRepository.findAll();
        assertThat(mDeckConditionList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkTargetTriggerEffectMaxCountIsRequired() throws Exception {
        int databaseSizeBeforeTest = mDeckConditionRepository.findAll().size();
        // set the field null
        mDeckCondition.setTargetTriggerEffectMaxCount(null);

        // Create the MDeckCondition, which fails.
        MDeckConditionDTO mDeckConditionDTO = mDeckConditionMapper.toDto(mDeckCondition);

        restMDeckConditionMockMvc.perform(post("/api/m-deck-conditions")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mDeckConditionDTO)))
            .andExpect(status().isBadRequest());

        List<MDeckCondition> mDeckConditionList = mDeckConditionRepository.findAll();
        assertThat(mDeckConditionList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkTargetCharacterIsStartingMinIsRequired() throws Exception {
        int databaseSizeBeforeTest = mDeckConditionRepository.findAll().size();
        // set the field null
        mDeckCondition.setTargetCharacterIsStartingMin(null);

        // Create the MDeckCondition, which fails.
        MDeckConditionDTO mDeckConditionDTO = mDeckConditionMapper.toDto(mDeckCondition);

        restMDeckConditionMockMvc.perform(post("/api/m-deck-conditions")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mDeckConditionDTO)))
            .andExpect(status().isBadRequest());

        List<MDeckCondition> mDeckConditionList = mDeckConditionRepository.findAll();
        assertThat(mDeckConditionList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkTargetCharacterIsStartingMaxIsRequired() throws Exception {
        int databaseSizeBeforeTest = mDeckConditionRepository.findAll().size();
        // set the field null
        mDeckCondition.setTargetCharacterIsStartingMax(null);

        // Create the MDeckCondition, which fails.
        MDeckConditionDTO mDeckConditionDTO = mDeckConditionMapper.toDto(mDeckCondition);

        restMDeckConditionMockMvc.perform(post("/api/m-deck-conditions")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mDeckConditionDTO)))
            .andExpect(status().isBadRequest());

        List<MDeckCondition> mDeckConditionList = mDeckConditionRepository.findAll();
        assertThat(mDeckConditionList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkTargetPlayableCardIsStartingMinIsRequired() throws Exception {
        int databaseSizeBeforeTest = mDeckConditionRepository.findAll().size();
        // set the field null
        mDeckCondition.setTargetPlayableCardIsStartingMin(null);

        // Create the MDeckCondition, which fails.
        MDeckConditionDTO mDeckConditionDTO = mDeckConditionMapper.toDto(mDeckCondition);

        restMDeckConditionMockMvc.perform(post("/api/m-deck-conditions")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mDeckConditionDTO)))
            .andExpect(status().isBadRequest());

        List<MDeckCondition> mDeckConditionList = mDeckConditionRepository.findAll();
        assertThat(mDeckConditionList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkTargetPlayableCardIsStartingMaxIsRequired() throws Exception {
        int databaseSizeBeforeTest = mDeckConditionRepository.findAll().size();
        // set the field null
        mDeckCondition.setTargetPlayableCardIsStartingMax(null);

        // Create the MDeckCondition, which fails.
        MDeckConditionDTO mDeckConditionDTO = mDeckConditionMapper.toDto(mDeckCondition);

        restMDeckConditionMockMvc.perform(post("/api/m-deck-conditions")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mDeckConditionDTO)))
            .andExpect(status().isBadRequest());

        List<MDeckCondition> mDeckConditionList = mDeckConditionRepository.findAll();
        assertThat(mDeckConditionList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkTargetRarityIsStartingIsRequired() throws Exception {
        int databaseSizeBeforeTest = mDeckConditionRepository.findAll().size();
        // set the field null
        mDeckCondition.setTargetRarityIsStarting(null);

        // Create the MDeckCondition, which fails.
        MDeckConditionDTO mDeckConditionDTO = mDeckConditionMapper.toDto(mDeckCondition);

        restMDeckConditionMockMvc.perform(post("/api/m-deck-conditions")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mDeckConditionDTO)))
            .andExpect(status().isBadRequest());

        List<MDeckCondition> mDeckConditionList = mDeckConditionRepository.findAll();
        assertThat(mDeckConditionList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkTargetAttributeIsStartingIsRequired() throws Exception {
        int databaseSizeBeforeTest = mDeckConditionRepository.findAll().size();
        // set the field null
        mDeckCondition.setTargetAttributeIsStarting(null);

        // Create the MDeckCondition, which fails.
        MDeckConditionDTO mDeckConditionDTO = mDeckConditionMapper.toDto(mDeckCondition);

        restMDeckConditionMockMvc.perform(post("/api/m-deck-conditions")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mDeckConditionDTO)))
            .andExpect(status().isBadRequest());

        List<MDeckCondition> mDeckConditionList = mDeckConditionRepository.findAll();
        assertThat(mDeckConditionList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkTargetNationalityIsStartingMinIsRequired() throws Exception {
        int databaseSizeBeforeTest = mDeckConditionRepository.findAll().size();
        // set the field null
        mDeckCondition.setTargetNationalityIsStartingMin(null);

        // Create the MDeckCondition, which fails.
        MDeckConditionDTO mDeckConditionDTO = mDeckConditionMapper.toDto(mDeckCondition);

        restMDeckConditionMockMvc.perform(post("/api/m-deck-conditions")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mDeckConditionDTO)))
            .andExpect(status().isBadRequest());

        List<MDeckCondition> mDeckConditionList = mDeckConditionRepository.findAll();
        assertThat(mDeckConditionList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkTargetNationalityIsStartingMaxIsRequired() throws Exception {
        int databaseSizeBeforeTest = mDeckConditionRepository.findAll().size();
        // set the field null
        mDeckCondition.setTargetNationalityIsStartingMax(null);

        // Create the MDeckCondition, which fails.
        MDeckConditionDTO mDeckConditionDTO = mDeckConditionMapper.toDto(mDeckCondition);

        restMDeckConditionMockMvc.perform(post("/api/m-deck-conditions")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mDeckConditionDTO)))
            .andExpect(status().isBadRequest());

        List<MDeckCondition> mDeckConditionList = mDeckConditionRepository.findAll();
        assertThat(mDeckConditionList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkTargetTeamIsStartingMinIsRequired() throws Exception {
        int databaseSizeBeforeTest = mDeckConditionRepository.findAll().size();
        // set the field null
        mDeckCondition.setTargetTeamIsStartingMin(null);

        // Create the MDeckCondition, which fails.
        MDeckConditionDTO mDeckConditionDTO = mDeckConditionMapper.toDto(mDeckCondition);

        restMDeckConditionMockMvc.perform(post("/api/m-deck-conditions")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mDeckConditionDTO)))
            .andExpect(status().isBadRequest());

        List<MDeckCondition> mDeckConditionList = mDeckConditionRepository.findAll();
        assertThat(mDeckConditionList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkTargetTeamIsStartingMaxIsRequired() throws Exception {
        int databaseSizeBeforeTest = mDeckConditionRepository.findAll().size();
        // set the field null
        mDeckCondition.setTargetTeamIsStartingMax(null);

        // Create the MDeckCondition, which fails.
        MDeckConditionDTO mDeckConditionDTO = mDeckConditionMapper.toDto(mDeckCondition);

        restMDeckConditionMockMvc.perform(post("/api/m-deck-conditions")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mDeckConditionDTO)))
            .andExpect(status().isBadRequest());

        List<MDeckCondition> mDeckConditionList = mDeckConditionRepository.findAll();
        assertThat(mDeckConditionList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkTargetActionIsStartingMinIsRequired() throws Exception {
        int databaseSizeBeforeTest = mDeckConditionRepository.findAll().size();
        // set the field null
        mDeckCondition.setTargetActionIsStartingMin(null);

        // Create the MDeckCondition, which fails.
        MDeckConditionDTO mDeckConditionDTO = mDeckConditionMapper.toDto(mDeckCondition);

        restMDeckConditionMockMvc.perform(post("/api/m-deck-conditions")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mDeckConditionDTO)))
            .andExpect(status().isBadRequest());

        List<MDeckCondition> mDeckConditionList = mDeckConditionRepository.findAll();
        assertThat(mDeckConditionList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkTargetActionIsStartingMaxIsRequired() throws Exception {
        int databaseSizeBeforeTest = mDeckConditionRepository.findAll().size();
        // set the field null
        mDeckCondition.setTargetActionIsStartingMax(null);

        // Create the MDeckCondition, which fails.
        MDeckConditionDTO mDeckConditionDTO = mDeckConditionMapper.toDto(mDeckCondition);

        restMDeckConditionMockMvc.perform(post("/api/m-deck-conditions")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mDeckConditionDTO)))
            .andExpect(status().isBadRequest());

        List<MDeckCondition> mDeckConditionList = mDeckConditionRepository.findAll();
        assertThat(mDeckConditionList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkTargetTriggerEffectIsStartingMinIsRequired() throws Exception {
        int databaseSizeBeforeTest = mDeckConditionRepository.findAll().size();
        // set the field null
        mDeckCondition.setTargetTriggerEffectIsStartingMin(null);

        // Create the MDeckCondition, which fails.
        MDeckConditionDTO mDeckConditionDTO = mDeckConditionMapper.toDto(mDeckCondition);

        restMDeckConditionMockMvc.perform(post("/api/m-deck-conditions")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mDeckConditionDTO)))
            .andExpect(status().isBadRequest());

        List<MDeckCondition> mDeckConditionList = mDeckConditionRepository.findAll();
        assertThat(mDeckConditionList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkTargetTriggerEffectIsStartingMaxIsRequired() throws Exception {
        int databaseSizeBeforeTest = mDeckConditionRepository.findAll().size();
        // set the field null
        mDeckCondition.setTargetTriggerEffectIsStartingMax(null);

        // Create the MDeckCondition, which fails.
        MDeckConditionDTO mDeckConditionDTO = mDeckConditionMapper.toDto(mDeckCondition);

        restMDeckConditionMockMvc.perform(post("/api/m-deck-conditions")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mDeckConditionDTO)))
            .andExpect(status().isBadRequest());

        List<MDeckCondition> mDeckConditionList = mDeckConditionRepository.findAll();
        assertThat(mDeckConditionList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllMDeckConditions() throws Exception {
        // Initialize the database
        mDeckConditionRepository.saveAndFlush(mDeckCondition);

        // Get all the mDeckConditionList
        restMDeckConditionMockMvc.perform(get("/api/m-deck-conditions?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(mDeckCondition.getId().intValue())))
            .andExpect(jsonPath("$.[*].targetFormationGroupId").value(hasItem(DEFAULT_TARGET_FORMATION_GROUP_ID)))
            .andExpect(jsonPath("$.[*].targetCharacterGroupMinId").value(hasItem(DEFAULT_TARGET_CHARACTER_GROUP_MIN_ID)))
            .andExpect(jsonPath("$.[*].targetCharacterGroupMaxId").value(hasItem(DEFAULT_TARGET_CHARACTER_GROUP_MAX_ID)))
            .andExpect(jsonPath("$.[*].targetPlayableCardGroupMinId").value(hasItem(DEFAULT_TARGET_PLAYABLE_CARD_GROUP_MIN_ID)))
            .andExpect(jsonPath("$.[*].targetPlayableCardGroupMaxId").value(hasItem(DEFAULT_TARGET_PLAYABLE_CARD_GROUP_MAX_ID)))
            .andExpect(jsonPath("$.[*].targetRarityGroupId").value(hasItem(DEFAULT_TARGET_RARITY_GROUP_ID)))
            .andExpect(jsonPath("$.[*].targetAttribute").value(hasItem(DEFAULT_TARGET_ATTRIBUTE)))
            .andExpect(jsonPath("$.[*].targetNationalityGroupMinId").value(hasItem(DEFAULT_TARGET_NATIONALITY_GROUP_MIN_ID)))
            .andExpect(jsonPath("$.[*].targetNationalityGroupMaxId").value(hasItem(DEFAULT_TARGET_NATIONALITY_GROUP_MAX_ID)))
            .andExpect(jsonPath("$.[*].targetTeamGroupMinId").value(hasItem(DEFAULT_TARGET_TEAM_GROUP_MIN_ID)))
            .andExpect(jsonPath("$.[*].targetTeamGroupMaxId").value(hasItem(DEFAULT_TARGET_TEAM_GROUP_MAX_ID)))
            .andExpect(jsonPath("$.[*].targetActionGroupMinId").value(hasItem(DEFAULT_TARGET_ACTION_GROUP_MIN_ID)))
            .andExpect(jsonPath("$.[*].targetActionGroupMaxId").value(hasItem(DEFAULT_TARGET_ACTION_GROUP_MAX_ID)))
            .andExpect(jsonPath("$.[*].targetTriggerEffectGroupMinId").value(hasItem(DEFAULT_TARGET_TRIGGER_EFFECT_GROUP_MIN_ID)))
            .andExpect(jsonPath("$.[*].targetTriggerEffectGroupMaxId").value(hasItem(DEFAULT_TARGET_TRIGGER_EFFECT_GROUP_MAX_ID)))
            .andExpect(jsonPath("$.[*].targetCharacterMinCount").value(hasItem(DEFAULT_TARGET_CHARACTER_MIN_COUNT)))
            .andExpect(jsonPath("$.[*].targetCharacterMaxCount").value(hasItem(DEFAULT_TARGET_CHARACTER_MAX_COUNT)))
            .andExpect(jsonPath("$.[*].targetPlayableCardMinCount").value(hasItem(DEFAULT_TARGET_PLAYABLE_CARD_MIN_COUNT)))
            .andExpect(jsonPath("$.[*].targetPlayableCardMaxCount").value(hasItem(DEFAULT_TARGET_PLAYABLE_CARD_MAX_COUNT)))
            .andExpect(jsonPath("$.[*].targetRarityMaxCount").value(hasItem(DEFAULT_TARGET_RARITY_MAX_COUNT)))
            .andExpect(jsonPath("$.[*].targetAttributeMinCount").value(hasItem(DEFAULT_TARGET_ATTRIBUTE_MIN_COUNT)))
            .andExpect(jsonPath("$.[*].targetNationalityMinCount").value(hasItem(DEFAULT_TARGET_NATIONALITY_MIN_COUNT)))
            .andExpect(jsonPath("$.[*].targetNationalityMaxCount").value(hasItem(DEFAULT_TARGET_NATIONALITY_MAX_COUNT)))
            .andExpect(jsonPath("$.[*].targetTeamMinCount").value(hasItem(DEFAULT_TARGET_TEAM_MIN_COUNT)))
            .andExpect(jsonPath("$.[*].targetTeamMaxCount").value(hasItem(DEFAULT_TARGET_TEAM_MAX_COUNT)))
            .andExpect(jsonPath("$.[*].targetActionMinCount").value(hasItem(DEFAULT_TARGET_ACTION_MIN_COUNT)))
            .andExpect(jsonPath("$.[*].targetActionMaxCount").value(hasItem(DEFAULT_TARGET_ACTION_MAX_COUNT)))
            .andExpect(jsonPath("$.[*].targetTriggerEffectMinCount").value(hasItem(DEFAULT_TARGET_TRIGGER_EFFECT_MIN_COUNT)))
            .andExpect(jsonPath("$.[*].targetTriggerEffectMaxCount").value(hasItem(DEFAULT_TARGET_TRIGGER_EFFECT_MAX_COUNT)))
            .andExpect(jsonPath("$.[*].targetCharacterIsStartingMin").value(hasItem(DEFAULT_TARGET_CHARACTER_IS_STARTING_MIN)))
            .andExpect(jsonPath("$.[*].targetCharacterIsStartingMax").value(hasItem(DEFAULT_TARGET_CHARACTER_IS_STARTING_MAX)))
            .andExpect(jsonPath("$.[*].targetPlayableCardIsStartingMin").value(hasItem(DEFAULT_TARGET_PLAYABLE_CARD_IS_STARTING_MIN)))
            .andExpect(jsonPath("$.[*].targetPlayableCardIsStartingMax").value(hasItem(DEFAULT_TARGET_PLAYABLE_CARD_IS_STARTING_MAX)))
            .andExpect(jsonPath("$.[*].targetRarityIsStarting").value(hasItem(DEFAULT_TARGET_RARITY_IS_STARTING)))
            .andExpect(jsonPath("$.[*].targetAttributeIsStarting").value(hasItem(DEFAULT_TARGET_ATTRIBUTE_IS_STARTING)))
            .andExpect(jsonPath("$.[*].targetNationalityIsStartingMin").value(hasItem(DEFAULT_TARGET_NATIONALITY_IS_STARTING_MIN)))
            .andExpect(jsonPath("$.[*].targetNationalityIsStartingMax").value(hasItem(DEFAULT_TARGET_NATIONALITY_IS_STARTING_MAX)))
            .andExpect(jsonPath("$.[*].targetTeamIsStartingMin").value(hasItem(DEFAULT_TARGET_TEAM_IS_STARTING_MIN)))
            .andExpect(jsonPath("$.[*].targetTeamIsStartingMax").value(hasItem(DEFAULT_TARGET_TEAM_IS_STARTING_MAX)))
            .andExpect(jsonPath("$.[*].targetActionIsStartingMin").value(hasItem(DEFAULT_TARGET_ACTION_IS_STARTING_MIN)))
            .andExpect(jsonPath("$.[*].targetActionIsStartingMax").value(hasItem(DEFAULT_TARGET_ACTION_IS_STARTING_MAX)))
            .andExpect(jsonPath("$.[*].targetTriggerEffectIsStartingMin").value(hasItem(DEFAULT_TARGET_TRIGGER_EFFECT_IS_STARTING_MIN)))
            .andExpect(jsonPath("$.[*].targetTriggerEffectIsStartingMax").value(hasItem(DEFAULT_TARGET_TRIGGER_EFFECT_IS_STARTING_MAX)));
    }
    
    @Test
    @Transactional
    public void getMDeckCondition() throws Exception {
        // Initialize the database
        mDeckConditionRepository.saveAndFlush(mDeckCondition);

        // Get the mDeckCondition
        restMDeckConditionMockMvc.perform(get("/api/m-deck-conditions/{id}", mDeckCondition.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(mDeckCondition.getId().intValue()))
            .andExpect(jsonPath("$.targetFormationGroupId").value(DEFAULT_TARGET_FORMATION_GROUP_ID))
            .andExpect(jsonPath("$.targetCharacterGroupMinId").value(DEFAULT_TARGET_CHARACTER_GROUP_MIN_ID))
            .andExpect(jsonPath("$.targetCharacterGroupMaxId").value(DEFAULT_TARGET_CHARACTER_GROUP_MAX_ID))
            .andExpect(jsonPath("$.targetPlayableCardGroupMinId").value(DEFAULT_TARGET_PLAYABLE_CARD_GROUP_MIN_ID))
            .andExpect(jsonPath("$.targetPlayableCardGroupMaxId").value(DEFAULT_TARGET_PLAYABLE_CARD_GROUP_MAX_ID))
            .andExpect(jsonPath("$.targetRarityGroupId").value(DEFAULT_TARGET_RARITY_GROUP_ID))
            .andExpect(jsonPath("$.targetAttribute").value(DEFAULT_TARGET_ATTRIBUTE))
            .andExpect(jsonPath("$.targetNationalityGroupMinId").value(DEFAULT_TARGET_NATIONALITY_GROUP_MIN_ID))
            .andExpect(jsonPath("$.targetNationalityGroupMaxId").value(DEFAULT_TARGET_NATIONALITY_GROUP_MAX_ID))
            .andExpect(jsonPath("$.targetTeamGroupMinId").value(DEFAULT_TARGET_TEAM_GROUP_MIN_ID))
            .andExpect(jsonPath("$.targetTeamGroupMaxId").value(DEFAULT_TARGET_TEAM_GROUP_MAX_ID))
            .andExpect(jsonPath("$.targetActionGroupMinId").value(DEFAULT_TARGET_ACTION_GROUP_MIN_ID))
            .andExpect(jsonPath("$.targetActionGroupMaxId").value(DEFAULT_TARGET_ACTION_GROUP_MAX_ID))
            .andExpect(jsonPath("$.targetTriggerEffectGroupMinId").value(DEFAULT_TARGET_TRIGGER_EFFECT_GROUP_MIN_ID))
            .andExpect(jsonPath("$.targetTriggerEffectGroupMaxId").value(DEFAULT_TARGET_TRIGGER_EFFECT_GROUP_MAX_ID))
            .andExpect(jsonPath("$.targetCharacterMinCount").value(DEFAULT_TARGET_CHARACTER_MIN_COUNT))
            .andExpect(jsonPath("$.targetCharacterMaxCount").value(DEFAULT_TARGET_CHARACTER_MAX_COUNT))
            .andExpect(jsonPath("$.targetPlayableCardMinCount").value(DEFAULT_TARGET_PLAYABLE_CARD_MIN_COUNT))
            .andExpect(jsonPath("$.targetPlayableCardMaxCount").value(DEFAULT_TARGET_PLAYABLE_CARD_MAX_COUNT))
            .andExpect(jsonPath("$.targetRarityMaxCount").value(DEFAULT_TARGET_RARITY_MAX_COUNT))
            .andExpect(jsonPath("$.targetAttributeMinCount").value(DEFAULT_TARGET_ATTRIBUTE_MIN_COUNT))
            .andExpect(jsonPath("$.targetNationalityMinCount").value(DEFAULT_TARGET_NATIONALITY_MIN_COUNT))
            .andExpect(jsonPath("$.targetNationalityMaxCount").value(DEFAULT_TARGET_NATIONALITY_MAX_COUNT))
            .andExpect(jsonPath("$.targetTeamMinCount").value(DEFAULT_TARGET_TEAM_MIN_COUNT))
            .andExpect(jsonPath("$.targetTeamMaxCount").value(DEFAULT_TARGET_TEAM_MAX_COUNT))
            .andExpect(jsonPath("$.targetActionMinCount").value(DEFAULT_TARGET_ACTION_MIN_COUNT))
            .andExpect(jsonPath("$.targetActionMaxCount").value(DEFAULT_TARGET_ACTION_MAX_COUNT))
            .andExpect(jsonPath("$.targetTriggerEffectMinCount").value(DEFAULT_TARGET_TRIGGER_EFFECT_MIN_COUNT))
            .andExpect(jsonPath("$.targetTriggerEffectMaxCount").value(DEFAULT_TARGET_TRIGGER_EFFECT_MAX_COUNT))
            .andExpect(jsonPath("$.targetCharacterIsStartingMin").value(DEFAULT_TARGET_CHARACTER_IS_STARTING_MIN))
            .andExpect(jsonPath("$.targetCharacterIsStartingMax").value(DEFAULT_TARGET_CHARACTER_IS_STARTING_MAX))
            .andExpect(jsonPath("$.targetPlayableCardIsStartingMin").value(DEFAULT_TARGET_PLAYABLE_CARD_IS_STARTING_MIN))
            .andExpect(jsonPath("$.targetPlayableCardIsStartingMax").value(DEFAULT_TARGET_PLAYABLE_CARD_IS_STARTING_MAX))
            .andExpect(jsonPath("$.targetRarityIsStarting").value(DEFAULT_TARGET_RARITY_IS_STARTING))
            .andExpect(jsonPath("$.targetAttributeIsStarting").value(DEFAULT_TARGET_ATTRIBUTE_IS_STARTING))
            .andExpect(jsonPath("$.targetNationalityIsStartingMin").value(DEFAULT_TARGET_NATIONALITY_IS_STARTING_MIN))
            .andExpect(jsonPath("$.targetNationalityIsStartingMax").value(DEFAULT_TARGET_NATIONALITY_IS_STARTING_MAX))
            .andExpect(jsonPath("$.targetTeamIsStartingMin").value(DEFAULT_TARGET_TEAM_IS_STARTING_MIN))
            .andExpect(jsonPath("$.targetTeamIsStartingMax").value(DEFAULT_TARGET_TEAM_IS_STARTING_MAX))
            .andExpect(jsonPath("$.targetActionIsStartingMin").value(DEFAULT_TARGET_ACTION_IS_STARTING_MIN))
            .andExpect(jsonPath("$.targetActionIsStartingMax").value(DEFAULT_TARGET_ACTION_IS_STARTING_MAX))
            .andExpect(jsonPath("$.targetTriggerEffectIsStartingMin").value(DEFAULT_TARGET_TRIGGER_EFFECT_IS_STARTING_MIN))
            .andExpect(jsonPath("$.targetTriggerEffectIsStartingMax").value(DEFAULT_TARGET_TRIGGER_EFFECT_IS_STARTING_MAX));
    }

    @Test
    @Transactional
    public void getAllMDeckConditionsByTargetFormationGroupIdIsEqualToSomething() throws Exception {
        // Initialize the database
        mDeckConditionRepository.saveAndFlush(mDeckCondition);

        // Get all the mDeckConditionList where targetFormationGroupId equals to DEFAULT_TARGET_FORMATION_GROUP_ID
        defaultMDeckConditionShouldBeFound("targetFormationGroupId.equals=" + DEFAULT_TARGET_FORMATION_GROUP_ID);

        // Get all the mDeckConditionList where targetFormationGroupId equals to UPDATED_TARGET_FORMATION_GROUP_ID
        defaultMDeckConditionShouldNotBeFound("targetFormationGroupId.equals=" + UPDATED_TARGET_FORMATION_GROUP_ID);
    }

    @Test
    @Transactional
    public void getAllMDeckConditionsByTargetFormationGroupIdIsInShouldWork() throws Exception {
        // Initialize the database
        mDeckConditionRepository.saveAndFlush(mDeckCondition);

        // Get all the mDeckConditionList where targetFormationGroupId in DEFAULT_TARGET_FORMATION_GROUP_ID or UPDATED_TARGET_FORMATION_GROUP_ID
        defaultMDeckConditionShouldBeFound("targetFormationGroupId.in=" + DEFAULT_TARGET_FORMATION_GROUP_ID + "," + UPDATED_TARGET_FORMATION_GROUP_ID);

        // Get all the mDeckConditionList where targetFormationGroupId equals to UPDATED_TARGET_FORMATION_GROUP_ID
        defaultMDeckConditionShouldNotBeFound("targetFormationGroupId.in=" + UPDATED_TARGET_FORMATION_GROUP_ID);
    }

    @Test
    @Transactional
    public void getAllMDeckConditionsByTargetFormationGroupIdIsNullOrNotNull() throws Exception {
        // Initialize the database
        mDeckConditionRepository.saveAndFlush(mDeckCondition);

        // Get all the mDeckConditionList where targetFormationGroupId is not null
        defaultMDeckConditionShouldBeFound("targetFormationGroupId.specified=true");

        // Get all the mDeckConditionList where targetFormationGroupId is null
        defaultMDeckConditionShouldNotBeFound("targetFormationGroupId.specified=false");
    }

    @Test
    @Transactional
    public void getAllMDeckConditionsByTargetFormationGroupIdIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mDeckConditionRepository.saveAndFlush(mDeckCondition);

        // Get all the mDeckConditionList where targetFormationGroupId greater than or equals to DEFAULT_TARGET_FORMATION_GROUP_ID
        defaultMDeckConditionShouldBeFound("targetFormationGroupId.greaterOrEqualThan=" + DEFAULT_TARGET_FORMATION_GROUP_ID);

        // Get all the mDeckConditionList where targetFormationGroupId greater than or equals to UPDATED_TARGET_FORMATION_GROUP_ID
        defaultMDeckConditionShouldNotBeFound("targetFormationGroupId.greaterOrEqualThan=" + UPDATED_TARGET_FORMATION_GROUP_ID);
    }

    @Test
    @Transactional
    public void getAllMDeckConditionsByTargetFormationGroupIdIsLessThanSomething() throws Exception {
        // Initialize the database
        mDeckConditionRepository.saveAndFlush(mDeckCondition);

        // Get all the mDeckConditionList where targetFormationGroupId less than or equals to DEFAULT_TARGET_FORMATION_GROUP_ID
        defaultMDeckConditionShouldNotBeFound("targetFormationGroupId.lessThan=" + DEFAULT_TARGET_FORMATION_GROUP_ID);

        // Get all the mDeckConditionList where targetFormationGroupId less than or equals to UPDATED_TARGET_FORMATION_GROUP_ID
        defaultMDeckConditionShouldBeFound("targetFormationGroupId.lessThan=" + UPDATED_TARGET_FORMATION_GROUP_ID);
    }


    @Test
    @Transactional
    public void getAllMDeckConditionsByTargetCharacterGroupMinIdIsEqualToSomething() throws Exception {
        // Initialize the database
        mDeckConditionRepository.saveAndFlush(mDeckCondition);

        // Get all the mDeckConditionList where targetCharacterGroupMinId equals to DEFAULT_TARGET_CHARACTER_GROUP_MIN_ID
        defaultMDeckConditionShouldBeFound("targetCharacterGroupMinId.equals=" + DEFAULT_TARGET_CHARACTER_GROUP_MIN_ID);

        // Get all the mDeckConditionList where targetCharacterGroupMinId equals to UPDATED_TARGET_CHARACTER_GROUP_MIN_ID
        defaultMDeckConditionShouldNotBeFound("targetCharacterGroupMinId.equals=" + UPDATED_TARGET_CHARACTER_GROUP_MIN_ID);
    }

    @Test
    @Transactional
    public void getAllMDeckConditionsByTargetCharacterGroupMinIdIsInShouldWork() throws Exception {
        // Initialize the database
        mDeckConditionRepository.saveAndFlush(mDeckCondition);

        // Get all the mDeckConditionList where targetCharacterGroupMinId in DEFAULT_TARGET_CHARACTER_GROUP_MIN_ID or UPDATED_TARGET_CHARACTER_GROUP_MIN_ID
        defaultMDeckConditionShouldBeFound("targetCharacterGroupMinId.in=" + DEFAULT_TARGET_CHARACTER_GROUP_MIN_ID + "," + UPDATED_TARGET_CHARACTER_GROUP_MIN_ID);

        // Get all the mDeckConditionList where targetCharacterGroupMinId equals to UPDATED_TARGET_CHARACTER_GROUP_MIN_ID
        defaultMDeckConditionShouldNotBeFound("targetCharacterGroupMinId.in=" + UPDATED_TARGET_CHARACTER_GROUP_MIN_ID);
    }

    @Test
    @Transactional
    public void getAllMDeckConditionsByTargetCharacterGroupMinIdIsNullOrNotNull() throws Exception {
        // Initialize the database
        mDeckConditionRepository.saveAndFlush(mDeckCondition);

        // Get all the mDeckConditionList where targetCharacterGroupMinId is not null
        defaultMDeckConditionShouldBeFound("targetCharacterGroupMinId.specified=true");

        // Get all the mDeckConditionList where targetCharacterGroupMinId is null
        defaultMDeckConditionShouldNotBeFound("targetCharacterGroupMinId.specified=false");
    }

    @Test
    @Transactional
    public void getAllMDeckConditionsByTargetCharacterGroupMinIdIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mDeckConditionRepository.saveAndFlush(mDeckCondition);

        // Get all the mDeckConditionList where targetCharacterGroupMinId greater than or equals to DEFAULT_TARGET_CHARACTER_GROUP_MIN_ID
        defaultMDeckConditionShouldBeFound("targetCharacterGroupMinId.greaterOrEqualThan=" + DEFAULT_TARGET_CHARACTER_GROUP_MIN_ID);

        // Get all the mDeckConditionList where targetCharacterGroupMinId greater than or equals to UPDATED_TARGET_CHARACTER_GROUP_MIN_ID
        defaultMDeckConditionShouldNotBeFound("targetCharacterGroupMinId.greaterOrEqualThan=" + UPDATED_TARGET_CHARACTER_GROUP_MIN_ID);
    }

    @Test
    @Transactional
    public void getAllMDeckConditionsByTargetCharacterGroupMinIdIsLessThanSomething() throws Exception {
        // Initialize the database
        mDeckConditionRepository.saveAndFlush(mDeckCondition);

        // Get all the mDeckConditionList where targetCharacterGroupMinId less than or equals to DEFAULT_TARGET_CHARACTER_GROUP_MIN_ID
        defaultMDeckConditionShouldNotBeFound("targetCharacterGroupMinId.lessThan=" + DEFAULT_TARGET_CHARACTER_GROUP_MIN_ID);

        // Get all the mDeckConditionList where targetCharacterGroupMinId less than or equals to UPDATED_TARGET_CHARACTER_GROUP_MIN_ID
        defaultMDeckConditionShouldBeFound("targetCharacterGroupMinId.lessThan=" + UPDATED_TARGET_CHARACTER_GROUP_MIN_ID);
    }


    @Test
    @Transactional
    public void getAllMDeckConditionsByTargetCharacterGroupMaxIdIsEqualToSomething() throws Exception {
        // Initialize the database
        mDeckConditionRepository.saveAndFlush(mDeckCondition);

        // Get all the mDeckConditionList where targetCharacterGroupMaxId equals to DEFAULT_TARGET_CHARACTER_GROUP_MAX_ID
        defaultMDeckConditionShouldBeFound("targetCharacterGroupMaxId.equals=" + DEFAULT_TARGET_CHARACTER_GROUP_MAX_ID);

        // Get all the mDeckConditionList where targetCharacterGroupMaxId equals to UPDATED_TARGET_CHARACTER_GROUP_MAX_ID
        defaultMDeckConditionShouldNotBeFound("targetCharacterGroupMaxId.equals=" + UPDATED_TARGET_CHARACTER_GROUP_MAX_ID);
    }

    @Test
    @Transactional
    public void getAllMDeckConditionsByTargetCharacterGroupMaxIdIsInShouldWork() throws Exception {
        // Initialize the database
        mDeckConditionRepository.saveAndFlush(mDeckCondition);

        // Get all the mDeckConditionList where targetCharacterGroupMaxId in DEFAULT_TARGET_CHARACTER_GROUP_MAX_ID or UPDATED_TARGET_CHARACTER_GROUP_MAX_ID
        defaultMDeckConditionShouldBeFound("targetCharacterGroupMaxId.in=" + DEFAULT_TARGET_CHARACTER_GROUP_MAX_ID + "," + UPDATED_TARGET_CHARACTER_GROUP_MAX_ID);

        // Get all the mDeckConditionList where targetCharacterGroupMaxId equals to UPDATED_TARGET_CHARACTER_GROUP_MAX_ID
        defaultMDeckConditionShouldNotBeFound("targetCharacterGroupMaxId.in=" + UPDATED_TARGET_CHARACTER_GROUP_MAX_ID);
    }

    @Test
    @Transactional
    public void getAllMDeckConditionsByTargetCharacterGroupMaxIdIsNullOrNotNull() throws Exception {
        // Initialize the database
        mDeckConditionRepository.saveAndFlush(mDeckCondition);

        // Get all the mDeckConditionList where targetCharacterGroupMaxId is not null
        defaultMDeckConditionShouldBeFound("targetCharacterGroupMaxId.specified=true");

        // Get all the mDeckConditionList where targetCharacterGroupMaxId is null
        defaultMDeckConditionShouldNotBeFound("targetCharacterGroupMaxId.specified=false");
    }

    @Test
    @Transactional
    public void getAllMDeckConditionsByTargetCharacterGroupMaxIdIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mDeckConditionRepository.saveAndFlush(mDeckCondition);

        // Get all the mDeckConditionList where targetCharacterGroupMaxId greater than or equals to DEFAULT_TARGET_CHARACTER_GROUP_MAX_ID
        defaultMDeckConditionShouldBeFound("targetCharacterGroupMaxId.greaterOrEqualThan=" + DEFAULT_TARGET_CHARACTER_GROUP_MAX_ID);

        // Get all the mDeckConditionList where targetCharacterGroupMaxId greater than or equals to UPDATED_TARGET_CHARACTER_GROUP_MAX_ID
        defaultMDeckConditionShouldNotBeFound("targetCharacterGroupMaxId.greaterOrEqualThan=" + UPDATED_TARGET_CHARACTER_GROUP_MAX_ID);
    }

    @Test
    @Transactional
    public void getAllMDeckConditionsByTargetCharacterGroupMaxIdIsLessThanSomething() throws Exception {
        // Initialize the database
        mDeckConditionRepository.saveAndFlush(mDeckCondition);

        // Get all the mDeckConditionList where targetCharacterGroupMaxId less than or equals to DEFAULT_TARGET_CHARACTER_GROUP_MAX_ID
        defaultMDeckConditionShouldNotBeFound("targetCharacterGroupMaxId.lessThan=" + DEFAULT_TARGET_CHARACTER_GROUP_MAX_ID);

        // Get all the mDeckConditionList where targetCharacterGroupMaxId less than or equals to UPDATED_TARGET_CHARACTER_GROUP_MAX_ID
        defaultMDeckConditionShouldBeFound("targetCharacterGroupMaxId.lessThan=" + UPDATED_TARGET_CHARACTER_GROUP_MAX_ID);
    }


    @Test
    @Transactional
    public void getAllMDeckConditionsByTargetPlayableCardGroupMinIdIsEqualToSomething() throws Exception {
        // Initialize the database
        mDeckConditionRepository.saveAndFlush(mDeckCondition);

        // Get all the mDeckConditionList where targetPlayableCardGroupMinId equals to DEFAULT_TARGET_PLAYABLE_CARD_GROUP_MIN_ID
        defaultMDeckConditionShouldBeFound("targetPlayableCardGroupMinId.equals=" + DEFAULT_TARGET_PLAYABLE_CARD_GROUP_MIN_ID);

        // Get all the mDeckConditionList where targetPlayableCardGroupMinId equals to UPDATED_TARGET_PLAYABLE_CARD_GROUP_MIN_ID
        defaultMDeckConditionShouldNotBeFound("targetPlayableCardGroupMinId.equals=" + UPDATED_TARGET_PLAYABLE_CARD_GROUP_MIN_ID);
    }

    @Test
    @Transactional
    public void getAllMDeckConditionsByTargetPlayableCardGroupMinIdIsInShouldWork() throws Exception {
        // Initialize the database
        mDeckConditionRepository.saveAndFlush(mDeckCondition);

        // Get all the mDeckConditionList where targetPlayableCardGroupMinId in DEFAULT_TARGET_PLAYABLE_CARD_GROUP_MIN_ID or UPDATED_TARGET_PLAYABLE_CARD_GROUP_MIN_ID
        defaultMDeckConditionShouldBeFound("targetPlayableCardGroupMinId.in=" + DEFAULT_TARGET_PLAYABLE_CARD_GROUP_MIN_ID + "," + UPDATED_TARGET_PLAYABLE_CARD_GROUP_MIN_ID);

        // Get all the mDeckConditionList where targetPlayableCardGroupMinId equals to UPDATED_TARGET_PLAYABLE_CARD_GROUP_MIN_ID
        defaultMDeckConditionShouldNotBeFound("targetPlayableCardGroupMinId.in=" + UPDATED_TARGET_PLAYABLE_CARD_GROUP_MIN_ID);
    }

    @Test
    @Transactional
    public void getAllMDeckConditionsByTargetPlayableCardGroupMinIdIsNullOrNotNull() throws Exception {
        // Initialize the database
        mDeckConditionRepository.saveAndFlush(mDeckCondition);

        // Get all the mDeckConditionList where targetPlayableCardGroupMinId is not null
        defaultMDeckConditionShouldBeFound("targetPlayableCardGroupMinId.specified=true");

        // Get all the mDeckConditionList where targetPlayableCardGroupMinId is null
        defaultMDeckConditionShouldNotBeFound("targetPlayableCardGroupMinId.specified=false");
    }

    @Test
    @Transactional
    public void getAllMDeckConditionsByTargetPlayableCardGroupMinIdIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mDeckConditionRepository.saveAndFlush(mDeckCondition);

        // Get all the mDeckConditionList where targetPlayableCardGroupMinId greater than or equals to DEFAULT_TARGET_PLAYABLE_CARD_GROUP_MIN_ID
        defaultMDeckConditionShouldBeFound("targetPlayableCardGroupMinId.greaterOrEqualThan=" + DEFAULT_TARGET_PLAYABLE_CARD_GROUP_MIN_ID);

        // Get all the mDeckConditionList where targetPlayableCardGroupMinId greater than or equals to UPDATED_TARGET_PLAYABLE_CARD_GROUP_MIN_ID
        defaultMDeckConditionShouldNotBeFound("targetPlayableCardGroupMinId.greaterOrEqualThan=" + UPDATED_TARGET_PLAYABLE_CARD_GROUP_MIN_ID);
    }

    @Test
    @Transactional
    public void getAllMDeckConditionsByTargetPlayableCardGroupMinIdIsLessThanSomething() throws Exception {
        // Initialize the database
        mDeckConditionRepository.saveAndFlush(mDeckCondition);

        // Get all the mDeckConditionList where targetPlayableCardGroupMinId less than or equals to DEFAULT_TARGET_PLAYABLE_CARD_GROUP_MIN_ID
        defaultMDeckConditionShouldNotBeFound("targetPlayableCardGroupMinId.lessThan=" + DEFAULT_TARGET_PLAYABLE_CARD_GROUP_MIN_ID);

        // Get all the mDeckConditionList where targetPlayableCardGroupMinId less than or equals to UPDATED_TARGET_PLAYABLE_CARD_GROUP_MIN_ID
        defaultMDeckConditionShouldBeFound("targetPlayableCardGroupMinId.lessThan=" + UPDATED_TARGET_PLAYABLE_CARD_GROUP_MIN_ID);
    }


    @Test
    @Transactional
    public void getAllMDeckConditionsByTargetPlayableCardGroupMaxIdIsEqualToSomething() throws Exception {
        // Initialize the database
        mDeckConditionRepository.saveAndFlush(mDeckCondition);

        // Get all the mDeckConditionList where targetPlayableCardGroupMaxId equals to DEFAULT_TARGET_PLAYABLE_CARD_GROUP_MAX_ID
        defaultMDeckConditionShouldBeFound("targetPlayableCardGroupMaxId.equals=" + DEFAULT_TARGET_PLAYABLE_CARD_GROUP_MAX_ID);

        // Get all the mDeckConditionList where targetPlayableCardGroupMaxId equals to UPDATED_TARGET_PLAYABLE_CARD_GROUP_MAX_ID
        defaultMDeckConditionShouldNotBeFound("targetPlayableCardGroupMaxId.equals=" + UPDATED_TARGET_PLAYABLE_CARD_GROUP_MAX_ID);
    }

    @Test
    @Transactional
    public void getAllMDeckConditionsByTargetPlayableCardGroupMaxIdIsInShouldWork() throws Exception {
        // Initialize the database
        mDeckConditionRepository.saveAndFlush(mDeckCondition);

        // Get all the mDeckConditionList where targetPlayableCardGroupMaxId in DEFAULT_TARGET_PLAYABLE_CARD_GROUP_MAX_ID or UPDATED_TARGET_PLAYABLE_CARD_GROUP_MAX_ID
        defaultMDeckConditionShouldBeFound("targetPlayableCardGroupMaxId.in=" + DEFAULT_TARGET_PLAYABLE_CARD_GROUP_MAX_ID + "," + UPDATED_TARGET_PLAYABLE_CARD_GROUP_MAX_ID);

        // Get all the mDeckConditionList where targetPlayableCardGroupMaxId equals to UPDATED_TARGET_PLAYABLE_CARD_GROUP_MAX_ID
        defaultMDeckConditionShouldNotBeFound("targetPlayableCardGroupMaxId.in=" + UPDATED_TARGET_PLAYABLE_CARD_GROUP_MAX_ID);
    }

    @Test
    @Transactional
    public void getAllMDeckConditionsByTargetPlayableCardGroupMaxIdIsNullOrNotNull() throws Exception {
        // Initialize the database
        mDeckConditionRepository.saveAndFlush(mDeckCondition);

        // Get all the mDeckConditionList where targetPlayableCardGroupMaxId is not null
        defaultMDeckConditionShouldBeFound("targetPlayableCardGroupMaxId.specified=true");

        // Get all the mDeckConditionList where targetPlayableCardGroupMaxId is null
        defaultMDeckConditionShouldNotBeFound("targetPlayableCardGroupMaxId.specified=false");
    }

    @Test
    @Transactional
    public void getAllMDeckConditionsByTargetPlayableCardGroupMaxIdIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mDeckConditionRepository.saveAndFlush(mDeckCondition);

        // Get all the mDeckConditionList where targetPlayableCardGroupMaxId greater than or equals to DEFAULT_TARGET_PLAYABLE_CARD_GROUP_MAX_ID
        defaultMDeckConditionShouldBeFound("targetPlayableCardGroupMaxId.greaterOrEqualThan=" + DEFAULT_TARGET_PLAYABLE_CARD_GROUP_MAX_ID);

        // Get all the mDeckConditionList where targetPlayableCardGroupMaxId greater than or equals to UPDATED_TARGET_PLAYABLE_CARD_GROUP_MAX_ID
        defaultMDeckConditionShouldNotBeFound("targetPlayableCardGroupMaxId.greaterOrEqualThan=" + UPDATED_TARGET_PLAYABLE_CARD_GROUP_MAX_ID);
    }

    @Test
    @Transactional
    public void getAllMDeckConditionsByTargetPlayableCardGroupMaxIdIsLessThanSomething() throws Exception {
        // Initialize the database
        mDeckConditionRepository.saveAndFlush(mDeckCondition);

        // Get all the mDeckConditionList where targetPlayableCardGroupMaxId less than or equals to DEFAULT_TARGET_PLAYABLE_CARD_GROUP_MAX_ID
        defaultMDeckConditionShouldNotBeFound("targetPlayableCardGroupMaxId.lessThan=" + DEFAULT_TARGET_PLAYABLE_CARD_GROUP_MAX_ID);

        // Get all the mDeckConditionList where targetPlayableCardGroupMaxId less than or equals to UPDATED_TARGET_PLAYABLE_CARD_GROUP_MAX_ID
        defaultMDeckConditionShouldBeFound("targetPlayableCardGroupMaxId.lessThan=" + UPDATED_TARGET_PLAYABLE_CARD_GROUP_MAX_ID);
    }


    @Test
    @Transactional
    public void getAllMDeckConditionsByTargetRarityGroupIdIsEqualToSomething() throws Exception {
        // Initialize the database
        mDeckConditionRepository.saveAndFlush(mDeckCondition);

        // Get all the mDeckConditionList where targetRarityGroupId equals to DEFAULT_TARGET_RARITY_GROUP_ID
        defaultMDeckConditionShouldBeFound("targetRarityGroupId.equals=" + DEFAULT_TARGET_RARITY_GROUP_ID);

        // Get all the mDeckConditionList where targetRarityGroupId equals to UPDATED_TARGET_RARITY_GROUP_ID
        defaultMDeckConditionShouldNotBeFound("targetRarityGroupId.equals=" + UPDATED_TARGET_RARITY_GROUP_ID);
    }

    @Test
    @Transactional
    public void getAllMDeckConditionsByTargetRarityGroupIdIsInShouldWork() throws Exception {
        // Initialize the database
        mDeckConditionRepository.saveAndFlush(mDeckCondition);

        // Get all the mDeckConditionList where targetRarityGroupId in DEFAULT_TARGET_RARITY_GROUP_ID or UPDATED_TARGET_RARITY_GROUP_ID
        defaultMDeckConditionShouldBeFound("targetRarityGroupId.in=" + DEFAULT_TARGET_RARITY_GROUP_ID + "," + UPDATED_TARGET_RARITY_GROUP_ID);

        // Get all the mDeckConditionList where targetRarityGroupId equals to UPDATED_TARGET_RARITY_GROUP_ID
        defaultMDeckConditionShouldNotBeFound("targetRarityGroupId.in=" + UPDATED_TARGET_RARITY_GROUP_ID);
    }

    @Test
    @Transactional
    public void getAllMDeckConditionsByTargetRarityGroupIdIsNullOrNotNull() throws Exception {
        // Initialize the database
        mDeckConditionRepository.saveAndFlush(mDeckCondition);

        // Get all the mDeckConditionList where targetRarityGroupId is not null
        defaultMDeckConditionShouldBeFound("targetRarityGroupId.specified=true");

        // Get all the mDeckConditionList where targetRarityGroupId is null
        defaultMDeckConditionShouldNotBeFound("targetRarityGroupId.specified=false");
    }

    @Test
    @Transactional
    public void getAllMDeckConditionsByTargetRarityGroupIdIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mDeckConditionRepository.saveAndFlush(mDeckCondition);

        // Get all the mDeckConditionList where targetRarityGroupId greater than or equals to DEFAULT_TARGET_RARITY_GROUP_ID
        defaultMDeckConditionShouldBeFound("targetRarityGroupId.greaterOrEqualThan=" + DEFAULT_TARGET_RARITY_GROUP_ID);

        // Get all the mDeckConditionList where targetRarityGroupId greater than or equals to UPDATED_TARGET_RARITY_GROUP_ID
        defaultMDeckConditionShouldNotBeFound("targetRarityGroupId.greaterOrEqualThan=" + UPDATED_TARGET_RARITY_GROUP_ID);
    }

    @Test
    @Transactional
    public void getAllMDeckConditionsByTargetRarityGroupIdIsLessThanSomething() throws Exception {
        // Initialize the database
        mDeckConditionRepository.saveAndFlush(mDeckCondition);

        // Get all the mDeckConditionList where targetRarityGroupId less than or equals to DEFAULT_TARGET_RARITY_GROUP_ID
        defaultMDeckConditionShouldNotBeFound("targetRarityGroupId.lessThan=" + DEFAULT_TARGET_RARITY_GROUP_ID);

        // Get all the mDeckConditionList where targetRarityGroupId less than or equals to UPDATED_TARGET_RARITY_GROUP_ID
        defaultMDeckConditionShouldBeFound("targetRarityGroupId.lessThan=" + UPDATED_TARGET_RARITY_GROUP_ID);
    }


    @Test
    @Transactional
    public void getAllMDeckConditionsByTargetAttributeIsEqualToSomething() throws Exception {
        // Initialize the database
        mDeckConditionRepository.saveAndFlush(mDeckCondition);

        // Get all the mDeckConditionList where targetAttribute equals to DEFAULT_TARGET_ATTRIBUTE
        defaultMDeckConditionShouldBeFound("targetAttribute.equals=" + DEFAULT_TARGET_ATTRIBUTE);

        // Get all the mDeckConditionList where targetAttribute equals to UPDATED_TARGET_ATTRIBUTE
        defaultMDeckConditionShouldNotBeFound("targetAttribute.equals=" + UPDATED_TARGET_ATTRIBUTE);
    }

    @Test
    @Transactional
    public void getAllMDeckConditionsByTargetAttributeIsInShouldWork() throws Exception {
        // Initialize the database
        mDeckConditionRepository.saveAndFlush(mDeckCondition);

        // Get all the mDeckConditionList where targetAttribute in DEFAULT_TARGET_ATTRIBUTE or UPDATED_TARGET_ATTRIBUTE
        defaultMDeckConditionShouldBeFound("targetAttribute.in=" + DEFAULT_TARGET_ATTRIBUTE + "," + UPDATED_TARGET_ATTRIBUTE);

        // Get all the mDeckConditionList where targetAttribute equals to UPDATED_TARGET_ATTRIBUTE
        defaultMDeckConditionShouldNotBeFound("targetAttribute.in=" + UPDATED_TARGET_ATTRIBUTE);
    }

    @Test
    @Transactional
    public void getAllMDeckConditionsByTargetAttributeIsNullOrNotNull() throws Exception {
        // Initialize the database
        mDeckConditionRepository.saveAndFlush(mDeckCondition);

        // Get all the mDeckConditionList where targetAttribute is not null
        defaultMDeckConditionShouldBeFound("targetAttribute.specified=true");

        // Get all the mDeckConditionList where targetAttribute is null
        defaultMDeckConditionShouldNotBeFound("targetAttribute.specified=false");
    }

    @Test
    @Transactional
    public void getAllMDeckConditionsByTargetAttributeIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mDeckConditionRepository.saveAndFlush(mDeckCondition);

        // Get all the mDeckConditionList where targetAttribute greater than or equals to DEFAULT_TARGET_ATTRIBUTE
        defaultMDeckConditionShouldBeFound("targetAttribute.greaterOrEqualThan=" + DEFAULT_TARGET_ATTRIBUTE);

        // Get all the mDeckConditionList where targetAttribute greater than or equals to UPDATED_TARGET_ATTRIBUTE
        defaultMDeckConditionShouldNotBeFound("targetAttribute.greaterOrEqualThan=" + UPDATED_TARGET_ATTRIBUTE);
    }

    @Test
    @Transactional
    public void getAllMDeckConditionsByTargetAttributeIsLessThanSomething() throws Exception {
        // Initialize the database
        mDeckConditionRepository.saveAndFlush(mDeckCondition);

        // Get all the mDeckConditionList where targetAttribute less than or equals to DEFAULT_TARGET_ATTRIBUTE
        defaultMDeckConditionShouldNotBeFound("targetAttribute.lessThan=" + DEFAULT_TARGET_ATTRIBUTE);

        // Get all the mDeckConditionList where targetAttribute less than or equals to UPDATED_TARGET_ATTRIBUTE
        defaultMDeckConditionShouldBeFound("targetAttribute.lessThan=" + UPDATED_TARGET_ATTRIBUTE);
    }


    @Test
    @Transactional
    public void getAllMDeckConditionsByTargetNationalityGroupMinIdIsEqualToSomething() throws Exception {
        // Initialize the database
        mDeckConditionRepository.saveAndFlush(mDeckCondition);

        // Get all the mDeckConditionList where targetNationalityGroupMinId equals to DEFAULT_TARGET_NATIONALITY_GROUP_MIN_ID
        defaultMDeckConditionShouldBeFound("targetNationalityGroupMinId.equals=" + DEFAULT_TARGET_NATIONALITY_GROUP_MIN_ID);

        // Get all the mDeckConditionList where targetNationalityGroupMinId equals to UPDATED_TARGET_NATIONALITY_GROUP_MIN_ID
        defaultMDeckConditionShouldNotBeFound("targetNationalityGroupMinId.equals=" + UPDATED_TARGET_NATIONALITY_GROUP_MIN_ID);
    }

    @Test
    @Transactional
    public void getAllMDeckConditionsByTargetNationalityGroupMinIdIsInShouldWork() throws Exception {
        // Initialize the database
        mDeckConditionRepository.saveAndFlush(mDeckCondition);

        // Get all the mDeckConditionList where targetNationalityGroupMinId in DEFAULT_TARGET_NATIONALITY_GROUP_MIN_ID or UPDATED_TARGET_NATIONALITY_GROUP_MIN_ID
        defaultMDeckConditionShouldBeFound("targetNationalityGroupMinId.in=" + DEFAULT_TARGET_NATIONALITY_GROUP_MIN_ID + "," + UPDATED_TARGET_NATIONALITY_GROUP_MIN_ID);

        // Get all the mDeckConditionList where targetNationalityGroupMinId equals to UPDATED_TARGET_NATIONALITY_GROUP_MIN_ID
        defaultMDeckConditionShouldNotBeFound("targetNationalityGroupMinId.in=" + UPDATED_TARGET_NATIONALITY_GROUP_MIN_ID);
    }

    @Test
    @Transactional
    public void getAllMDeckConditionsByTargetNationalityGroupMinIdIsNullOrNotNull() throws Exception {
        // Initialize the database
        mDeckConditionRepository.saveAndFlush(mDeckCondition);

        // Get all the mDeckConditionList where targetNationalityGroupMinId is not null
        defaultMDeckConditionShouldBeFound("targetNationalityGroupMinId.specified=true");

        // Get all the mDeckConditionList where targetNationalityGroupMinId is null
        defaultMDeckConditionShouldNotBeFound("targetNationalityGroupMinId.specified=false");
    }

    @Test
    @Transactional
    public void getAllMDeckConditionsByTargetNationalityGroupMinIdIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mDeckConditionRepository.saveAndFlush(mDeckCondition);

        // Get all the mDeckConditionList where targetNationalityGroupMinId greater than or equals to DEFAULT_TARGET_NATIONALITY_GROUP_MIN_ID
        defaultMDeckConditionShouldBeFound("targetNationalityGroupMinId.greaterOrEqualThan=" + DEFAULT_TARGET_NATIONALITY_GROUP_MIN_ID);

        // Get all the mDeckConditionList where targetNationalityGroupMinId greater than or equals to UPDATED_TARGET_NATIONALITY_GROUP_MIN_ID
        defaultMDeckConditionShouldNotBeFound("targetNationalityGroupMinId.greaterOrEqualThan=" + UPDATED_TARGET_NATIONALITY_GROUP_MIN_ID);
    }

    @Test
    @Transactional
    public void getAllMDeckConditionsByTargetNationalityGroupMinIdIsLessThanSomething() throws Exception {
        // Initialize the database
        mDeckConditionRepository.saveAndFlush(mDeckCondition);

        // Get all the mDeckConditionList where targetNationalityGroupMinId less than or equals to DEFAULT_TARGET_NATIONALITY_GROUP_MIN_ID
        defaultMDeckConditionShouldNotBeFound("targetNationalityGroupMinId.lessThan=" + DEFAULT_TARGET_NATIONALITY_GROUP_MIN_ID);

        // Get all the mDeckConditionList where targetNationalityGroupMinId less than or equals to UPDATED_TARGET_NATIONALITY_GROUP_MIN_ID
        defaultMDeckConditionShouldBeFound("targetNationalityGroupMinId.lessThan=" + UPDATED_TARGET_NATIONALITY_GROUP_MIN_ID);
    }


    @Test
    @Transactional
    public void getAllMDeckConditionsByTargetNationalityGroupMaxIdIsEqualToSomething() throws Exception {
        // Initialize the database
        mDeckConditionRepository.saveAndFlush(mDeckCondition);

        // Get all the mDeckConditionList where targetNationalityGroupMaxId equals to DEFAULT_TARGET_NATIONALITY_GROUP_MAX_ID
        defaultMDeckConditionShouldBeFound("targetNationalityGroupMaxId.equals=" + DEFAULT_TARGET_NATIONALITY_GROUP_MAX_ID);

        // Get all the mDeckConditionList where targetNationalityGroupMaxId equals to UPDATED_TARGET_NATIONALITY_GROUP_MAX_ID
        defaultMDeckConditionShouldNotBeFound("targetNationalityGroupMaxId.equals=" + UPDATED_TARGET_NATIONALITY_GROUP_MAX_ID);
    }

    @Test
    @Transactional
    public void getAllMDeckConditionsByTargetNationalityGroupMaxIdIsInShouldWork() throws Exception {
        // Initialize the database
        mDeckConditionRepository.saveAndFlush(mDeckCondition);

        // Get all the mDeckConditionList where targetNationalityGroupMaxId in DEFAULT_TARGET_NATIONALITY_GROUP_MAX_ID or UPDATED_TARGET_NATIONALITY_GROUP_MAX_ID
        defaultMDeckConditionShouldBeFound("targetNationalityGroupMaxId.in=" + DEFAULT_TARGET_NATIONALITY_GROUP_MAX_ID + "," + UPDATED_TARGET_NATIONALITY_GROUP_MAX_ID);

        // Get all the mDeckConditionList where targetNationalityGroupMaxId equals to UPDATED_TARGET_NATIONALITY_GROUP_MAX_ID
        defaultMDeckConditionShouldNotBeFound("targetNationalityGroupMaxId.in=" + UPDATED_TARGET_NATIONALITY_GROUP_MAX_ID);
    }

    @Test
    @Transactional
    public void getAllMDeckConditionsByTargetNationalityGroupMaxIdIsNullOrNotNull() throws Exception {
        // Initialize the database
        mDeckConditionRepository.saveAndFlush(mDeckCondition);

        // Get all the mDeckConditionList where targetNationalityGroupMaxId is not null
        defaultMDeckConditionShouldBeFound("targetNationalityGroupMaxId.specified=true");

        // Get all the mDeckConditionList where targetNationalityGroupMaxId is null
        defaultMDeckConditionShouldNotBeFound("targetNationalityGroupMaxId.specified=false");
    }

    @Test
    @Transactional
    public void getAllMDeckConditionsByTargetNationalityGroupMaxIdIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mDeckConditionRepository.saveAndFlush(mDeckCondition);

        // Get all the mDeckConditionList where targetNationalityGroupMaxId greater than or equals to DEFAULT_TARGET_NATIONALITY_GROUP_MAX_ID
        defaultMDeckConditionShouldBeFound("targetNationalityGroupMaxId.greaterOrEqualThan=" + DEFAULT_TARGET_NATIONALITY_GROUP_MAX_ID);

        // Get all the mDeckConditionList where targetNationalityGroupMaxId greater than or equals to UPDATED_TARGET_NATIONALITY_GROUP_MAX_ID
        defaultMDeckConditionShouldNotBeFound("targetNationalityGroupMaxId.greaterOrEqualThan=" + UPDATED_TARGET_NATIONALITY_GROUP_MAX_ID);
    }

    @Test
    @Transactional
    public void getAllMDeckConditionsByTargetNationalityGroupMaxIdIsLessThanSomething() throws Exception {
        // Initialize the database
        mDeckConditionRepository.saveAndFlush(mDeckCondition);

        // Get all the mDeckConditionList where targetNationalityGroupMaxId less than or equals to DEFAULT_TARGET_NATIONALITY_GROUP_MAX_ID
        defaultMDeckConditionShouldNotBeFound("targetNationalityGroupMaxId.lessThan=" + DEFAULT_TARGET_NATIONALITY_GROUP_MAX_ID);

        // Get all the mDeckConditionList where targetNationalityGroupMaxId less than or equals to UPDATED_TARGET_NATIONALITY_GROUP_MAX_ID
        defaultMDeckConditionShouldBeFound("targetNationalityGroupMaxId.lessThan=" + UPDATED_TARGET_NATIONALITY_GROUP_MAX_ID);
    }


    @Test
    @Transactional
    public void getAllMDeckConditionsByTargetTeamGroupMinIdIsEqualToSomething() throws Exception {
        // Initialize the database
        mDeckConditionRepository.saveAndFlush(mDeckCondition);

        // Get all the mDeckConditionList where targetTeamGroupMinId equals to DEFAULT_TARGET_TEAM_GROUP_MIN_ID
        defaultMDeckConditionShouldBeFound("targetTeamGroupMinId.equals=" + DEFAULT_TARGET_TEAM_GROUP_MIN_ID);

        // Get all the mDeckConditionList where targetTeamGroupMinId equals to UPDATED_TARGET_TEAM_GROUP_MIN_ID
        defaultMDeckConditionShouldNotBeFound("targetTeamGroupMinId.equals=" + UPDATED_TARGET_TEAM_GROUP_MIN_ID);
    }

    @Test
    @Transactional
    public void getAllMDeckConditionsByTargetTeamGroupMinIdIsInShouldWork() throws Exception {
        // Initialize the database
        mDeckConditionRepository.saveAndFlush(mDeckCondition);

        // Get all the mDeckConditionList where targetTeamGroupMinId in DEFAULT_TARGET_TEAM_GROUP_MIN_ID or UPDATED_TARGET_TEAM_GROUP_MIN_ID
        defaultMDeckConditionShouldBeFound("targetTeamGroupMinId.in=" + DEFAULT_TARGET_TEAM_GROUP_MIN_ID + "," + UPDATED_TARGET_TEAM_GROUP_MIN_ID);

        // Get all the mDeckConditionList where targetTeamGroupMinId equals to UPDATED_TARGET_TEAM_GROUP_MIN_ID
        defaultMDeckConditionShouldNotBeFound("targetTeamGroupMinId.in=" + UPDATED_TARGET_TEAM_GROUP_MIN_ID);
    }

    @Test
    @Transactional
    public void getAllMDeckConditionsByTargetTeamGroupMinIdIsNullOrNotNull() throws Exception {
        // Initialize the database
        mDeckConditionRepository.saveAndFlush(mDeckCondition);

        // Get all the mDeckConditionList where targetTeamGroupMinId is not null
        defaultMDeckConditionShouldBeFound("targetTeamGroupMinId.specified=true");

        // Get all the mDeckConditionList where targetTeamGroupMinId is null
        defaultMDeckConditionShouldNotBeFound("targetTeamGroupMinId.specified=false");
    }

    @Test
    @Transactional
    public void getAllMDeckConditionsByTargetTeamGroupMinIdIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mDeckConditionRepository.saveAndFlush(mDeckCondition);

        // Get all the mDeckConditionList where targetTeamGroupMinId greater than or equals to DEFAULT_TARGET_TEAM_GROUP_MIN_ID
        defaultMDeckConditionShouldBeFound("targetTeamGroupMinId.greaterOrEqualThan=" + DEFAULT_TARGET_TEAM_GROUP_MIN_ID);

        // Get all the mDeckConditionList where targetTeamGroupMinId greater than or equals to UPDATED_TARGET_TEAM_GROUP_MIN_ID
        defaultMDeckConditionShouldNotBeFound("targetTeamGroupMinId.greaterOrEqualThan=" + UPDATED_TARGET_TEAM_GROUP_MIN_ID);
    }

    @Test
    @Transactional
    public void getAllMDeckConditionsByTargetTeamGroupMinIdIsLessThanSomething() throws Exception {
        // Initialize the database
        mDeckConditionRepository.saveAndFlush(mDeckCondition);

        // Get all the mDeckConditionList where targetTeamGroupMinId less than or equals to DEFAULT_TARGET_TEAM_GROUP_MIN_ID
        defaultMDeckConditionShouldNotBeFound("targetTeamGroupMinId.lessThan=" + DEFAULT_TARGET_TEAM_GROUP_MIN_ID);

        // Get all the mDeckConditionList where targetTeamGroupMinId less than or equals to UPDATED_TARGET_TEAM_GROUP_MIN_ID
        defaultMDeckConditionShouldBeFound("targetTeamGroupMinId.lessThan=" + UPDATED_TARGET_TEAM_GROUP_MIN_ID);
    }


    @Test
    @Transactional
    public void getAllMDeckConditionsByTargetTeamGroupMaxIdIsEqualToSomething() throws Exception {
        // Initialize the database
        mDeckConditionRepository.saveAndFlush(mDeckCondition);

        // Get all the mDeckConditionList where targetTeamGroupMaxId equals to DEFAULT_TARGET_TEAM_GROUP_MAX_ID
        defaultMDeckConditionShouldBeFound("targetTeamGroupMaxId.equals=" + DEFAULT_TARGET_TEAM_GROUP_MAX_ID);

        // Get all the mDeckConditionList where targetTeamGroupMaxId equals to UPDATED_TARGET_TEAM_GROUP_MAX_ID
        defaultMDeckConditionShouldNotBeFound("targetTeamGroupMaxId.equals=" + UPDATED_TARGET_TEAM_GROUP_MAX_ID);
    }

    @Test
    @Transactional
    public void getAllMDeckConditionsByTargetTeamGroupMaxIdIsInShouldWork() throws Exception {
        // Initialize the database
        mDeckConditionRepository.saveAndFlush(mDeckCondition);

        // Get all the mDeckConditionList where targetTeamGroupMaxId in DEFAULT_TARGET_TEAM_GROUP_MAX_ID or UPDATED_TARGET_TEAM_GROUP_MAX_ID
        defaultMDeckConditionShouldBeFound("targetTeamGroupMaxId.in=" + DEFAULT_TARGET_TEAM_GROUP_MAX_ID + "," + UPDATED_TARGET_TEAM_GROUP_MAX_ID);

        // Get all the mDeckConditionList where targetTeamGroupMaxId equals to UPDATED_TARGET_TEAM_GROUP_MAX_ID
        defaultMDeckConditionShouldNotBeFound("targetTeamGroupMaxId.in=" + UPDATED_TARGET_TEAM_GROUP_MAX_ID);
    }

    @Test
    @Transactional
    public void getAllMDeckConditionsByTargetTeamGroupMaxIdIsNullOrNotNull() throws Exception {
        // Initialize the database
        mDeckConditionRepository.saveAndFlush(mDeckCondition);

        // Get all the mDeckConditionList where targetTeamGroupMaxId is not null
        defaultMDeckConditionShouldBeFound("targetTeamGroupMaxId.specified=true");

        // Get all the mDeckConditionList where targetTeamGroupMaxId is null
        defaultMDeckConditionShouldNotBeFound("targetTeamGroupMaxId.specified=false");
    }

    @Test
    @Transactional
    public void getAllMDeckConditionsByTargetTeamGroupMaxIdIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mDeckConditionRepository.saveAndFlush(mDeckCondition);

        // Get all the mDeckConditionList where targetTeamGroupMaxId greater than or equals to DEFAULT_TARGET_TEAM_GROUP_MAX_ID
        defaultMDeckConditionShouldBeFound("targetTeamGroupMaxId.greaterOrEqualThan=" + DEFAULT_TARGET_TEAM_GROUP_MAX_ID);

        // Get all the mDeckConditionList where targetTeamGroupMaxId greater than or equals to UPDATED_TARGET_TEAM_GROUP_MAX_ID
        defaultMDeckConditionShouldNotBeFound("targetTeamGroupMaxId.greaterOrEqualThan=" + UPDATED_TARGET_TEAM_GROUP_MAX_ID);
    }

    @Test
    @Transactional
    public void getAllMDeckConditionsByTargetTeamGroupMaxIdIsLessThanSomething() throws Exception {
        // Initialize the database
        mDeckConditionRepository.saveAndFlush(mDeckCondition);

        // Get all the mDeckConditionList where targetTeamGroupMaxId less than or equals to DEFAULT_TARGET_TEAM_GROUP_MAX_ID
        defaultMDeckConditionShouldNotBeFound("targetTeamGroupMaxId.lessThan=" + DEFAULT_TARGET_TEAM_GROUP_MAX_ID);

        // Get all the mDeckConditionList where targetTeamGroupMaxId less than or equals to UPDATED_TARGET_TEAM_GROUP_MAX_ID
        defaultMDeckConditionShouldBeFound("targetTeamGroupMaxId.lessThan=" + UPDATED_TARGET_TEAM_GROUP_MAX_ID);
    }


    @Test
    @Transactional
    public void getAllMDeckConditionsByTargetActionGroupMinIdIsEqualToSomething() throws Exception {
        // Initialize the database
        mDeckConditionRepository.saveAndFlush(mDeckCondition);

        // Get all the mDeckConditionList where targetActionGroupMinId equals to DEFAULT_TARGET_ACTION_GROUP_MIN_ID
        defaultMDeckConditionShouldBeFound("targetActionGroupMinId.equals=" + DEFAULT_TARGET_ACTION_GROUP_MIN_ID);

        // Get all the mDeckConditionList where targetActionGroupMinId equals to UPDATED_TARGET_ACTION_GROUP_MIN_ID
        defaultMDeckConditionShouldNotBeFound("targetActionGroupMinId.equals=" + UPDATED_TARGET_ACTION_GROUP_MIN_ID);
    }

    @Test
    @Transactional
    public void getAllMDeckConditionsByTargetActionGroupMinIdIsInShouldWork() throws Exception {
        // Initialize the database
        mDeckConditionRepository.saveAndFlush(mDeckCondition);

        // Get all the mDeckConditionList where targetActionGroupMinId in DEFAULT_TARGET_ACTION_GROUP_MIN_ID or UPDATED_TARGET_ACTION_GROUP_MIN_ID
        defaultMDeckConditionShouldBeFound("targetActionGroupMinId.in=" + DEFAULT_TARGET_ACTION_GROUP_MIN_ID + "," + UPDATED_TARGET_ACTION_GROUP_MIN_ID);

        // Get all the mDeckConditionList where targetActionGroupMinId equals to UPDATED_TARGET_ACTION_GROUP_MIN_ID
        defaultMDeckConditionShouldNotBeFound("targetActionGroupMinId.in=" + UPDATED_TARGET_ACTION_GROUP_MIN_ID);
    }

    @Test
    @Transactional
    public void getAllMDeckConditionsByTargetActionGroupMinIdIsNullOrNotNull() throws Exception {
        // Initialize the database
        mDeckConditionRepository.saveAndFlush(mDeckCondition);

        // Get all the mDeckConditionList where targetActionGroupMinId is not null
        defaultMDeckConditionShouldBeFound("targetActionGroupMinId.specified=true");

        // Get all the mDeckConditionList where targetActionGroupMinId is null
        defaultMDeckConditionShouldNotBeFound("targetActionGroupMinId.specified=false");
    }

    @Test
    @Transactional
    public void getAllMDeckConditionsByTargetActionGroupMinIdIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mDeckConditionRepository.saveAndFlush(mDeckCondition);

        // Get all the mDeckConditionList where targetActionGroupMinId greater than or equals to DEFAULT_TARGET_ACTION_GROUP_MIN_ID
        defaultMDeckConditionShouldBeFound("targetActionGroupMinId.greaterOrEqualThan=" + DEFAULT_TARGET_ACTION_GROUP_MIN_ID);

        // Get all the mDeckConditionList where targetActionGroupMinId greater than or equals to UPDATED_TARGET_ACTION_GROUP_MIN_ID
        defaultMDeckConditionShouldNotBeFound("targetActionGroupMinId.greaterOrEqualThan=" + UPDATED_TARGET_ACTION_GROUP_MIN_ID);
    }

    @Test
    @Transactional
    public void getAllMDeckConditionsByTargetActionGroupMinIdIsLessThanSomething() throws Exception {
        // Initialize the database
        mDeckConditionRepository.saveAndFlush(mDeckCondition);

        // Get all the mDeckConditionList where targetActionGroupMinId less than or equals to DEFAULT_TARGET_ACTION_GROUP_MIN_ID
        defaultMDeckConditionShouldNotBeFound("targetActionGroupMinId.lessThan=" + DEFAULT_TARGET_ACTION_GROUP_MIN_ID);

        // Get all the mDeckConditionList where targetActionGroupMinId less than or equals to UPDATED_TARGET_ACTION_GROUP_MIN_ID
        defaultMDeckConditionShouldBeFound("targetActionGroupMinId.lessThan=" + UPDATED_TARGET_ACTION_GROUP_MIN_ID);
    }


    @Test
    @Transactional
    public void getAllMDeckConditionsByTargetActionGroupMaxIdIsEqualToSomething() throws Exception {
        // Initialize the database
        mDeckConditionRepository.saveAndFlush(mDeckCondition);

        // Get all the mDeckConditionList where targetActionGroupMaxId equals to DEFAULT_TARGET_ACTION_GROUP_MAX_ID
        defaultMDeckConditionShouldBeFound("targetActionGroupMaxId.equals=" + DEFAULT_TARGET_ACTION_GROUP_MAX_ID);

        // Get all the mDeckConditionList where targetActionGroupMaxId equals to UPDATED_TARGET_ACTION_GROUP_MAX_ID
        defaultMDeckConditionShouldNotBeFound("targetActionGroupMaxId.equals=" + UPDATED_TARGET_ACTION_GROUP_MAX_ID);
    }

    @Test
    @Transactional
    public void getAllMDeckConditionsByTargetActionGroupMaxIdIsInShouldWork() throws Exception {
        // Initialize the database
        mDeckConditionRepository.saveAndFlush(mDeckCondition);

        // Get all the mDeckConditionList where targetActionGroupMaxId in DEFAULT_TARGET_ACTION_GROUP_MAX_ID or UPDATED_TARGET_ACTION_GROUP_MAX_ID
        defaultMDeckConditionShouldBeFound("targetActionGroupMaxId.in=" + DEFAULT_TARGET_ACTION_GROUP_MAX_ID + "," + UPDATED_TARGET_ACTION_GROUP_MAX_ID);

        // Get all the mDeckConditionList where targetActionGroupMaxId equals to UPDATED_TARGET_ACTION_GROUP_MAX_ID
        defaultMDeckConditionShouldNotBeFound("targetActionGroupMaxId.in=" + UPDATED_TARGET_ACTION_GROUP_MAX_ID);
    }

    @Test
    @Transactional
    public void getAllMDeckConditionsByTargetActionGroupMaxIdIsNullOrNotNull() throws Exception {
        // Initialize the database
        mDeckConditionRepository.saveAndFlush(mDeckCondition);

        // Get all the mDeckConditionList where targetActionGroupMaxId is not null
        defaultMDeckConditionShouldBeFound("targetActionGroupMaxId.specified=true");

        // Get all the mDeckConditionList where targetActionGroupMaxId is null
        defaultMDeckConditionShouldNotBeFound("targetActionGroupMaxId.specified=false");
    }

    @Test
    @Transactional
    public void getAllMDeckConditionsByTargetActionGroupMaxIdIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mDeckConditionRepository.saveAndFlush(mDeckCondition);

        // Get all the mDeckConditionList where targetActionGroupMaxId greater than or equals to DEFAULT_TARGET_ACTION_GROUP_MAX_ID
        defaultMDeckConditionShouldBeFound("targetActionGroupMaxId.greaterOrEqualThan=" + DEFAULT_TARGET_ACTION_GROUP_MAX_ID);

        // Get all the mDeckConditionList where targetActionGroupMaxId greater than or equals to UPDATED_TARGET_ACTION_GROUP_MAX_ID
        defaultMDeckConditionShouldNotBeFound("targetActionGroupMaxId.greaterOrEqualThan=" + UPDATED_TARGET_ACTION_GROUP_MAX_ID);
    }

    @Test
    @Transactional
    public void getAllMDeckConditionsByTargetActionGroupMaxIdIsLessThanSomething() throws Exception {
        // Initialize the database
        mDeckConditionRepository.saveAndFlush(mDeckCondition);

        // Get all the mDeckConditionList where targetActionGroupMaxId less than or equals to DEFAULT_TARGET_ACTION_GROUP_MAX_ID
        defaultMDeckConditionShouldNotBeFound("targetActionGroupMaxId.lessThan=" + DEFAULT_TARGET_ACTION_GROUP_MAX_ID);

        // Get all the mDeckConditionList where targetActionGroupMaxId less than or equals to UPDATED_TARGET_ACTION_GROUP_MAX_ID
        defaultMDeckConditionShouldBeFound("targetActionGroupMaxId.lessThan=" + UPDATED_TARGET_ACTION_GROUP_MAX_ID);
    }


    @Test
    @Transactional
    public void getAllMDeckConditionsByTargetTriggerEffectGroupMinIdIsEqualToSomething() throws Exception {
        // Initialize the database
        mDeckConditionRepository.saveAndFlush(mDeckCondition);

        // Get all the mDeckConditionList where targetTriggerEffectGroupMinId equals to DEFAULT_TARGET_TRIGGER_EFFECT_GROUP_MIN_ID
        defaultMDeckConditionShouldBeFound("targetTriggerEffectGroupMinId.equals=" + DEFAULT_TARGET_TRIGGER_EFFECT_GROUP_MIN_ID);

        // Get all the mDeckConditionList where targetTriggerEffectGroupMinId equals to UPDATED_TARGET_TRIGGER_EFFECT_GROUP_MIN_ID
        defaultMDeckConditionShouldNotBeFound("targetTriggerEffectGroupMinId.equals=" + UPDATED_TARGET_TRIGGER_EFFECT_GROUP_MIN_ID);
    }

    @Test
    @Transactional
    public void getAllMDeckConditionsByTargetTriggerEffectGroupMinIdIsInShouldWork() throws Exception {
        // Initialize the database
        mDeckConditionRepository.saveAndFlush(mDeckCondition);

        // Get all the mDeckConditionList where targetTriggerEffectGroupMinId in DEFAULT_TARGET_TRIGGER_EFFECT_GROUP_MIN_ID or UPDATED_TARGET_TRIGGER_EFFECT_GROUP_MIN_ID
        defaultMDeckConditionShouldBeFound("targetTriggerEffectGroupMinId.in=" + DEFAULT_TARGET_TRIGGER_EFFECT_GROUP_MIN_ID + "," + UPDATED_TARGET_TRIGGER_EFFECT_GROUP_MIN_ID);

        // Get all the mDeckConditionList where targetTriggerEffectGroupMinId equals to UPDATED_TARGET_TRIGGER_EFFECT_GROUP_MIN_ID
        defaultMDeckConditionShouldNotBeFound("targetTriggerEffectGroupMinId.in=" + UPDATED_TARGET_TRIGGER_EFFECT_GROUP_MIN_ID);
    }

    @Test
    @Transactional
    public void getAllMDeckConditionsByTargetTriggerEffectGroupMinIdIsNullOrNotNull() throws Exception {
        // Initialize the database
        mDeckConditionRepository.saveAndFlush(mDeckCondition);

        // Get all the mDeckConditionList where targetTriggerEffectGroupMinId is not null
        defaultMDeckConditionShouldBeFound("targetTriggerEffectGroupMinId.specified=true");

        // Get all the mDeckConditionList where targetTriggerEffectGroupMinId is null
        defaultMDeckConditionShouldNotBeFound("targetTriggerEffectGroupMinId.specified=false");
    }

    @Test
    @Transactional
    public void getAllMDeckConditionsByTargetTriggerEffectGroupMinIdIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mDeckConditionRepository.saveAndFlush(mDeckCondition);

        // Get all the mDeckConditionList where targetTriggerEffectGroupMinId greater than or equals to DEFAULT_TARGET_TRIGGER_EFFECT_GROUP_MIN_ID
        defaultMDeckConditionShouldBeFound("targetTriggerEffectGroupMinId.greaterOrEqualThan=" + DEFAULT_TARGET_TRIGGER_EFFECT_GROUP_MIN_ID);

        // Get all the mDeckConditionList where targetTriggerEffectGroupMinId greater than or equals to UPDATED_TARGET_TRIGGER_EFFECT_GROUP_MIN_ID
        defaultMDeckConditionShouldNotBeFound("targetTriggerEffectGroupMinId.greaterOrEqualThan=" + UPDATED_TARGET_TRIGGER_EFFECT_GROUP_MIN_ID);
    }

    @Test
    @Transactional
    public void getAllMDeckConditionsByTargetTriggerEffectGroupMinIdIsLessThanSomething() throws Exception {
        // Initialize the database
        mDeckConditionRepository.saveAndFlush(mDeckCondition);

        // Get all the mDeckConditionList where targetTriggerEffectGroupMinId less than or equals to DEFAULT_TARGET_TRIGGER_EFFECT_GROUP_MIN_ID
        defaultMDeckConditionShouldNotBeFound("targetTriggerEffectGroupMinId.lessThan=" + DEFAULT_TARGET_TRIGGER_EFFECT_GROUP_MIN_ID);

        // Get all the mDeckConditionList where targetTriggerEffectGroupMinId less than or equals to UPDATED_TARGET_TRIGGER_EFFECT_GROUP_MIN_ID
        defaultMDeckConditionShouldBeFound("targetTriggerEffectGroupMinId.lessThan=" + UPDATED_TARGET_TRIGGER_EFFECT_GROUP_MIN_ID);
    }


    @Test
    @Transactional
    public void getAllMDeckConditionsByTargetTriggerEffectGroupMaxIdIsEqualToSomething() throws Exception {
        // Initialize the database
        mDeckConditionRepository.saveAndFlush(mDeckCondition);

        // Get all the mDeckConditionList where targetTriggerEffectGroupMaxId equals to DEFAULT_TARGET_TRIGGER_EFFECT_GROUP_MAX_ID
        defaultMDeckConditionShouldBeFound("targetTriggerEffectGroupMaxId.equals=" + DEFAULT_TARGET_TRIGGER_EFFECT_GROUP_MAX_ID);

        // Get all the mDeckConditionList where targetTriggerEffectGroupMaxId equals to UPDATED_TARGET_TRIGGER_EFFECT_GROUP_MAX_ID
        defaultMDeckConditionShouldNotBeFound("targetTriggerEffectGroupMaxId.equals=" + UPDATED_TARGET_TRIGGER_EFFECT_GROUP_MAX_ID);
    }

    @Test
    @Transactional
    public void getAllMDeckConditionsByTargetTriggerEffectGroupMaxIdIsInShouldWork() throws Exception {
        // Initialize the database
        mDeckConditionRepository.saveAndFlush(mDeckCondition);

        // Get all the mDeckConditionList where targetTriggerEffectGroupMaxId in DEFAULT_TARGET_TRIGGER_EFFECT_GROUP_MAX_ID or UPDATED_TARGET_TRIGGER_EFFECT_GROUP_MAX_ID
        defaultMDeckConditionShouldBeFound("targetTriggerEffectGroupMaxId.in=" + DEFAULT_TARGET_TRIGGER_EFFECT_GROUP_MAX_ID + "," + UPDATED_TARGET_TRIGGER_EFFECT_GROUP_MAX_ID);

        // Get all the mDeckConditionList where targetTriggerEffectGroupMaxId equals to UPDATED_TARGET_TRIGGER_EFFECT_GROUP_MAX_ID
        defaultMDeckConditionShouldNotBeFound("targetTriggerEffectGroupMaxId.in=" + UPDATED_TARGET_TRIGGER_EFFECT_GROUP_MAX_ID);
    }

    @Test
    @Transactional
    public void getAllMDeckConditionsByTargetTriggerEffectGroupMaxIdIsNullOrNotNull() throws Exception {
        // Initialize the database
        mDeckConditionRepository.saveAndFlush(mDeckCondition);

        // Get all the mDeckConditionList where targetTriggerEffectGroupMaxId is not null
        defaultMDeckConditionShouldBeFound("targetTriggerEffectGroupMaxId.specified=true");

        // Get all the mDeckConditionList where targetTriggerEffectGroupMaxId is null
        defaultMDeckConditionShouldNotBeFound("targetTriggerEffectGroupMaxId.specified=false");
    }

    @Test
    @Transactional
    public void getAllMDeckConditionsByTargetTriggerEffectGroupMaxIdIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mDeckConditionRepository.saveAndFlush(mDeckCondition);

        // Get all the mDeckConditionList where targetTriggerEffectGroupMaxId greater than or equals to DEFAULT_TARGET_TRIGGER_EFFECT_GROUP_MAX_ID
        defaultMDeckConditionShouldBeFound("targetTriggerEffectGroupMaxId.greaterOrEqualThan=" + DEFAULT_TARGET_TRIGGER_EFFECT_GROUP_MAX_ID);

        // Get all the mDeckConditionList where targetTriggerEffectGroupMaxId greater than or equals to UPDATED_TARGET_TRIGGER_EFFECT_GROUP_MAX_ID
        defaultMDeckConditionShouldNotBeFound("targetTriggerEffectGroupMaxId.greaterOrEqualThan=" + UPDATED_TARGET_TRIGGER_EFFECT_GROUP_MAX_ID);
    }

    @Test
    @Transactional
    public void getAllMDeckConditionsByTargetTriggerEffectGroupMaxIdIsLessThanSomething() throws Exception {
        // Initialize the database
        mDeckConditionRepository.saveAndFlush(mDeckCondition);

        // Get all the mDeckConditionList where targetTriggerEffectGroupMaxId less than or equals to DEFAULT_TARGET_TRIGGER_EFFECT_GROUP_MAX_ID
        defaultMDeckConditionShouldNotBeFound("targetTriggerEffectGroupMaxId.lessThan=" + DEFAULT_TARGET_TRIGGER_EFFECT_GROUP_MAX_ID);

        // Get all the mDeckConditionList where targetTriggerEffectGroupMaxId less than or equals to UPDATED_TARGET_TRIGGER_EFFECT_GROUP_MAX_ID
        defaultMDeckConditionShouldBeFound("targetTriggerEffectGroupMaxId.lessThan=" + UPDATED_TARGET_TRIGGER_EFFECT_GROUP_MAX_ID);
    }


    @Test
    @Transactional
    public void getAllMDeckConditionsByTargetCharacterMinCountIsEqualToSomething() throws Exception {
        // Initialize the database
        mDeckConditionRepository.saveAndFlush(mDeckCondition);

        // Get all the mDeckConditionList where targetCharacterMinCount equals to DEFAULT_TARGET_CHARACTER_MIN_COUNT
        defaultMDeckConditionShouldBeFound("targetCharacterMinCount.equals=" + DEFAULT_TARGET_CHARACTER_MIN_COUNT);

        // Get all the mDeckConditionList where targetCharacterMinCount equals to UPDATED_TARGET_CHARACTER_MIN_COUNT
        defaultMDeckConditionShouldNotBeFound("targetCharacterMinCount.equals=" + UPDATED_TARGET_CHARACTER_MIN_COUNT);
    }

    @Test
    @Transactional
    public void getAllMDeckConditionsByTargetCharacterMinCountIsInShouldWork() throws Exception {
        // Initialize the database
        mDeckConditionRepository.saveAndFlush(mDeckCondition);

        // Get all the mDeckConditionList where targetCharacterMinCount in DEFAULT_TARGET_CHARACTER_MIN_COUNT or UPDATED_TARGET_CHARACTER_MIN_COUNT
        defaultMDeckConditionShouldBeFound("targetCharacterMinCount.in=" + DEFAULT_TARGET_CHARACTER_MIN_COUNT + "," + UPDATED_TARGET_CHARACTER_MIN_COUNT);

        // Get all the mDeckConditionList where targetCharacterMinCount equals to UPDATED_TARGET_CHARACTER_MIN_COUNT
        defaultMDeckConditionShouldNotBeFound("targetCharacterMinCount.in=" + UPDATED_TARGET_CHARACTER_MIN_COUNT);
    }

    @Test
    @Transactional
    public void getAllMDeckConditionsByTargetCharacterMinCountIsNullOrNotNull() throws Exception {
        // Initialize the database
        mDeckConditionRepository.saveAndFlush(mDeckCondition);

        // Get all the mDeckConditionList where targetCharacterMinCount is not null
        defaultMDeckConditionShouldBeFound("targetCharacterMinCount.specified=true");

        // Get all the mDeckConditionList where targetCharacterMinCount is null
        defaultMDeckConditionShouldNotBeFound("targetCharacterMinCount.specified=false");
    }

    @Test
    @Transactional
    public void getAllMDeckConditionsByTargetCharacterMinCountIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mDeckConditionRepository.saveAndFlush(mDeckCondition);

        // Get all the mDeckConditionList where targetCharacterMinCount greater than or equals to DEFAULT_TARGET_CHARACTER_MIN_COUNT
        defaultMDeckConditionShouldBeFound("targetCharacterMinCount.greaterOrEqualThan=" + DEFAULT_TARGET_CHARACTER_MIN_COUNT);

        // Get all the mDeckConditionList where targetCharacterMinCount greater than or equals to UPDATED_TARGET_CHARACTER_MIN_COUNT
        defaultMDeckConditionShouldNotBeFound("targetCharacterMinCount.greaterOrEqualThan=" + UPDATED_TARGET_CHARACTER_MIN_COUNT);
    }

    @Test
    @Transactional
    public void getAllMDeckConditionsByTargetCharacterMinCountIsLessThanSomething() throws Exception {
        // Initialize the database
        mDeckConditionRepository.saveAndFlush(mDeckCondition);

        // Get all the mDeckConditionList where targetCharacterMinCount less than or equals to DEFAULT_TARGET_CHARACTER_MIN_COUNT
        defaultMDeckConditionShouldNotBeFound("targetCharacterMinCount.lessThan=" + DEFAULT_TARGET_CHARACTER_MIN_COUNT);

        // Get all the mDeckConditionList where targetCharacterMinCount less than or equals to UPDATED_TARGET_CHARACTER_MIN_COUNT
        defaultMDeckConditionShouldBeFound("targetCharacterMinCount.lessThan=" + UPDATED_TARGET_CHARACTER_MIN_COUNT);
    }


    @Test
    @Transactional
    public void getAllMDeckConditionsByTargetCharacterMaxCountIsEqualToSomething() throws Exception {
        // Initialize the database
        mDeckConditionRepository.saveAndFlush(mDeckCondition);

        // Get all the mDeckConditionList where targetCharacterMaxCount equals to DEFAULT_TARGET_CHARACTER_MAX_COUNT
        defaultMDeckConditionShouldBeFound("targetCharacterMaxCount.equals=" + DEFAULT_TARGET_CHARACTER_MAX_COUNT);

        // Get all the mDeckConditionList where targetCharacterMaxCount equals to UPDATED_TARGET_CHARACTER_MAX_COUNT
        defaultMDeckConditionShouldNotBeFound("targetCharacterMaxCount.equals=" + UPDATED_TARGET_CHARACTER_MAX_COUNT);
    }

    @Test
    @Transactional
    public void getAllMDeckConditionsByTargetCharacterMaxCountIsInShouldWork() throws Exception {
        // Initialize the database
        mDeckConditionRepository.saveAndFlush(mDeckCondition);

        // Get all the mDeckConditionList where targetCharacterMaxCount in DEFAULT_TARGET_CHARACTER_MAX_COUNT or UPDATED_TARGET_CHARACTER_MAX_COUNT
        defaultMDeckConditionShouldBeFound("targetCharacterMaxCount.in=" + DEFAULT_TARGET_CHARACTER_MAX_COUNT + "," + UPDATED_TARGET_CHARACTER_MAX_COUNT);

        // Get all the mDeckConditionList where targetCharacterMaxCount equals to UPDATED_TARGET_CHARACTER_MAX_COUNT
        defaultMDeckConditionShouldNotBeFound("targetCharacterMaxCount.in=" + UPDATED_TARGET_CHARACTER_MAX_COUNT);
    }

    @Test
    @Transactional
    public void getAllMDeckConditionsByTargetCharacterMaxCountIsNullOrNotNull() throws Exception {
        // Initialize the database
        mDeckConditionRepository.saveAndFlush(mDeckCondition);

        // Get all the mDeckConditionList where targetCharacterMaxCount is not null
        defaultMDeckConditionShouldBeFound("targetCharacterMaxCount.specified=true");

        // Get all the mDeckConditionList where targetCharacterMaxCount is null
        defaultMDeckConditionShouldNotBeFound("targetCharacterMaxCount.specified=false");
    }

    @Test
    @Transactional
    public void getAllMDeckConditionsByTargetCharacterMaxCountIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mDeckConditionRepository.saveAndFlush(mDeckCondition);

        // Get all the mDeckConditionList where targetCharacterMaxCount greater than or equals to DEFAULT_TARGET_CHARACTER_MAX_COUNT
        defaultMDeckConditionShouldBeFound("targetCharacterMaxCount.greaterOrEqualThan=" + DEFAULT_TARGET_CHARACTER_MAX_COUNT);

        // Get all the mDeckConditionList where targetCharacterMaxCount greater than or equals to UPDATED_TARGET_CHARACTER_MAX_COUNT
        defaultMDeckConditionShouldNotBeFound("targetCharacterMaxCount.greaterOrEqualThan=" + UPDATED_TARGET_CHARACTER_MAX_COUNT);
    }

    @Test
    @Transactional
    public void getAllMDeckConditionsByTargetCharacterMaxCountIsLessThanSomething() throws Exception {
        // Initialize the database
        mDeckConditionRepository.saveAndFlush(mDeckCondition);

        // Get all the mDeckConditionList where targetCharacterMaxCount less than or equals to DEFAULT_TARGET_CHARACTER_MAX_COUNT
        defaultMDeckConditionShouldNotBeFound("targetCharacterMaxCount.lessThan=" + DEFAULT_TARGET_CHARACTER_MAX_COUNT);

        // Get all the mDeckConditionList where targetCharacterMaxCount less than or equals to UPDATED_TARGET_CHARACTER_MAX_COUNT
        defaultMDeckConditionShouldBeFound("targetCharacterMaxCount.lessThan=" + UPDATED_TARGET_CHARACTER_MAX_COUNT);
    }


    @Test
    @Transactional
    public void getAllMDeckConditionsByTargetPlayableCardMinCountIsEqualToSomething() throws Exception {
        // Initialize the database
        mDeckConditionRepository.saveAndFlush(mDeckCondition);

        // Get all the mDeckConditionList where targetPlayableCardMinCount equals to DEFAULT_TARGET_PLAYABLE_CARD_MIN_COUNT
        defaultMDeckConditionShouldBeFound("targetPlayableCardMinCount.equals=" + DEFAULT_TARGET_PLAYABLE_CARD_MIN_COUNT);

        // Get all the mDeckConditionList where targetPlayableCardMinCount equals to UPDATED_TARGET_PLAYABLE_CARD_MIN_COUNT
        defaultMDeckConditionShouldNotBeFound("targetPlayableCardMinCount.equals=" + UPDATED_TARGET_PLAYABLE_CARD_MIN_COUNT);
    }

    @Test
    @Transactional
    public void getAllMDeckConditionsByTargetPlayableCardMinCountIsInShouldWork() throws Exception {
        // Initialize the database
        mDeckConditionRepository.saveAndFlush(mDeckCondition);

        // Get all the mDeckConditionList where targetPlayableCardMinCount in DEFAULT_TARGET_PLAYABLE_CARD_MIN_COUNT or UPDATED_TARGET_PLAYABLE_CARD_MIN_COUNT
        defaultMDeckConditionShouldBeFound("targetPlayableCardMinCount.in=" + DEFAULT_TARGET_PLAYABLE_CARD_MIN_COUNT + "," + UPDATED_TARGET_PLAYABLE_CARD_MIN_COUNT);

        // Get all the mDeckConditionList where targetPlayableCardMinCount equals to UPDATED_TARGET_PLAYABLE_CARD_MIN_COUNT
        defaultMDeckConditionShouldNotBeFound("targetPlayableCardMinCount.in=" + UPDATED_TARGET_PLAYABLE_CARD_MIN_COUNT);
    }

    @Test
    @Transactional
    public void getAllMDeckConditionsByTargetPlayableCardMinCountIsNullOrNotNull() throws Exception {
        // Initialize the database
        mDeckConditionRepository.saveAndFlush(mDeckCondition);

        // Get all the mDeckConditionList where targetPlayableCardMinCount is not null
        defaultMDeckConditionShouldBeFound("targetPlayableCardMinCount.specified=true");

        // Get all the mDeckConditionList where targetPlayableCardMinCount is null
        defaultMDeckConditionShouldNotBeFound("targetPlayableCardMinCount.specified=false");
    }

    @Test
    @Transactional
    public void getAllMDeckConditionsByTargetPlayableCardMinCountIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mDeckConditionRepository.saveAndFlush(mDeckCondition);

        // Get all the mDeckConditionList where targetPlayableCardMinCount greater than or equals to DEFAULT_TARGET_PLAYABLE_CARD_MIN_COUNT
        defaultMDeckConditionShouldBeFound("targetPlayableCardMinCount.greaterOrEqualThan=" + DEFAULT_TARGET_PLAYABLE_CARD_MIN_COUNT);

        // Get all the mDeckConditionList where targetPlayableCardMinCount greater than or equals to UPDATED_TARGET_PLAYABLE_CARD_MIN_COUNT
        defaultMDeckConditionShouldNotBeFound("targetPlayableCardMinCount.greaterOrEqualThan=" + UPDATED_TARGET_PLAYABLE_CARD_MIN_COUNT);
    }

    @Test
    @Transactional
    public void getAllMDeckConditionsByTargetPlayableCardMinCountIsLessThanSomething() throws Exception {
        // Initialize the database
        mDeckConditionRepository.saveAndFlush(mDeckCondition);

        // Get all the mDeckConditionList where targetPlayableCardMinCount less than or equals to DEFAULT_TARGET_PLAYABLE_CARD_MIN_COUNT
        defaultMDeckConditionShouldNotBeFound("targetPlayableCardMinCount.lessThan=" + DEFAULT_TARGET_PLAYABLE_CARD_MIN_COUNT);

        // Get all the mDeckConditionList where targetPlayableCardMinCount less than or equals to UPDATED_TARGET_PLAYABLE_CARD_MIN_COUNT
        defaultMDeckConditionShouldBeFound("targetPlayableCardMinCount.lessThan=" + UPDATED_TARGET_PLAYABLE_CARD_MIN_COUNT);
    }


    @Test
    @Transactional
    public void getAllMDeckConditionsByTargetPlayableCardMaxCountIsEqualToSomething() throws Exception {
        // Initialize the database
        mDeckConditionRepository.saveAndFlush(mDeckCondition);

        // Get all the mDeckConditionList where targetPlayableCardMaxCount equals to DEFAULT_TARGET_PLAYABLE_CARD_MAX_COUNT
        defaultMDeckConditionShouldBeFound("targetPlayableCardMaxCount.equals=" + DEFAULT_TARGET_PLAYABLE_CARD_MAX_COUNT);

        // Get all the mDeckConditionList where targetPlayableCardMaxCount equals to UPDATED_TARGET_PLAYABLE_CARD_MAX_COUNT
        defaultMDeckConditionShouldNotBeFound("targetPlayableCardMaxCount.equals=" + UPDATED_TARGET_PLAYABLE_CARD_MAX_COUNT);
    }

    @Test
    @Transactional
    public void getAllMDeckConditionsByTargetPlayableCardMaxCountIsInShouldWork() throws Exception {
        // Initialize the database
        mDeckConditionRepository.saveAndFlush(mDeckCondition);

        // Get all the mDeckConditionList where targetPlayableCardMaxCount in DEFAULT_TARGET_PLAYABLE_CARD_MAX_COUNT or UPDATED_TARGET_PLAYABLE_CARD_MAX_COUNT
        defaultMDeckConditionShouldBeFound("targetPlayableCardMaxCount.in=" + DEFAULT_TARGET_PLAYABLE_CARD_MAX_COUNT + "," + UPDATED_TARGET_PLAYABLE_CARD_MAX_COUNT);

        // Get all the mDeckConditionList where targetPlayableCardMaxCount equals to UPDATED_TARGET_PLAYABLE_CARD_MAX_COUNT
        defaultMDeckConditionShouldNotBeFound("targetPlayableCardMaxCount.in=" + UPDATED_TARGET_PLAYABLE_CARD_MAX_COUNT);
    }

    @Test
    @Transactional
    public void getAllMDeckConditionsByTargetPlayableCardMaxCountIsNullOrNotNull() throws Exception {
        // Initialize the database
        mDeckConditionRepository.saveAndFlush(mDeckCondition);

        // Get all the mDeckConditionList where targetPlayableCardMaxCount is not null
        defaultMDeckConditionShouldBeFound("targetPlayableCardMaxCount.specified=true");

        // Get all the mDeckConditionList where targetPlayableCardMaxCount is null
        defaultMDeckConditionShouldNotBeFound("targetPlayableCardMaxCount.specified=false");
    }

    @Test
    @Transactional
    public void getAllMDeckConditionsByTargetPlayableCardMaxCountIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mDeckConditionRepository.saveAndFlush(mDeckCondition);

        // Get all the mDeckConditionList where targetPlayableCardMaxCount greater than or equals to DEFAULT_TARGET_PLAYABLE_CARD_MAX_COUNT
        defaultMDeckConditionShouldBeFound("targetPlayableCardMaxCount.greaterOrEqualThan=" + DEFAULT_TARGET_PLAYABLE_CARD_MAX_COUNT);

        // Get all the mDeckConditionList where targetPlayableCardMaxCount greater than or equals to UPDATED_TARGET_PLAYABLE_CARD_MAX_COUNT
        defaultMDeckConditionShouldNotBeFound("targetPlayableCardMaxCount.greaterOrEqualThan=" + UPDATED_TARGET_PLAYABLE_CARD_MAX_COUNT);
    }

    @Test
    @Transactional
    public void getAllMDeckConditionsByTargetPlayableCardMaxCountIsLessThanSomething() throws Exception {
        // Initialize the database
        mDeckConditionRepository.saveAndFlush(mDeckCondition);

        // Get all the mDeckConditionList where targetPlayableCardMaxCount less than or equals to DEFAULT_TARGET_PLAYABLE_CARD_MAX_COUNT
        defaultMDeckConditionShouldNotBeFound("targetPlayableCardMaxCount.lessThan=" + DEFAULT_TARGET_PLAYABLE_CARD_MAX_COUNT);

        // Get all the mDeckConditionList where targetPlayableCardMaxCount less than or equals to UPDATED_TARGET_PLAYABLE_CARD_MAX_COUNT
        defaultMDeckConditionShouldBeFound("targetPlayableCardMaxCount.lessThan=" + UPDATED_TARGET_PLAYABLE_CARD_MAX_COUNT);
    }


    @Test
    @Transactional
    public void getAllMDeckConditionsByTargetRarityMaxCountIsEqualToSomething() throws Exception {
        // Initialize the database
        mDeckConditionRepository.saveAndFlush(mDeckCondition);

        // Get all the mDeckConditionList where targetRarityMaxCount equals to DEFAULT_TARGET_RARITY_MAX_COUNT
        defaultMDeckConditionShouldBeFound("targetRarityMaxCount.equals=" + DEFAULT_TARGET_RARITY_MAX_COUNT);

        // Get all the mDeckConditionList where targetRarityMaxCount equals to UPDATED_TARGET_RARITY_MAX_COUNT
        defaultMDeckConditionShouldNotBeFound("targetRarityMaxCount.equals=" + UPDATED_TARGET_RARITY_MAX_COUNT);
    }

    @Test
    @Transactional
    public void getAllMDeckConditionsByTargetRarityMaxCountIsInShouldWork() throws Exception {
        // Initialize the database
        mDeckConditionRepository.saveAndFlush(mDeckCondition);

        // Get all the mDeckConditionList where targetRarityMaxCount in DEFAULT_TARGET_RARITY_MAX_COUNT or UPDATED_TARGET_RARITY_MAX_COUNT
        defaultMDeckConditionShouldBeFound("targetRarityMaxCount.in=" + DEFAULT_TARGET_RARITY_MAX_COUNT + "," + UPDATED_TARGET_RARITY_MAX_COUNT);

        // Get all the mDeckConditionList where targetRarityMaxCount equals to UPDATED_TARGET_RARITY_MAX_COUNT
        defaultMDeckConditionShouldNotBeFound("targetRarityMaxCount.in=" + UPDATED_TARGET_RARITY_MAX_COUNT);
    }

    @Test
    @Transactional
    public void getAllMDeckConditionsByTargetRarityMaxCountIsNullOrNotNull() throws Exception {
        // Initialize the database
        mDeckConditionRepository.saveAndFlush(mDeckCondition);

        // Get all the mDeckConditionList where targetRarityMaxCount is not null
        defaultMDeckConditionShouldBeFound("targetRarityMaxCount.specified=true");

        // Get all the mDeckConditionList where targetRarityMaxCount is null
        defaultMDeckConditionShouldNotBeFound("targetRarityMaxCount.specified=false");
    }

    @Test
    @Transactional
    public void getAllMDeckConditionsByTargetRarityMaxCountIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mDeckConditionRepository.saveAndFlush(mDeckCondition);

        // Get all the mDeckConditionList where targetRarityMaxCount greater than or equals to DEFAULT_TARGET_RARITY_MAX_COUNT
        defaultMDeckConditionShouldBeFound("targetRarityMaxCount.greaterOrEqualThan=" + DEFAULT_TARGET_RARITY_MAX_COUNT);

        // Get all the mDeckConditionList where targetRarityMaxCount greater than or equals to UPDATED_TARGET_RARITY_MAX_COUNT
        defaultMDeckConditionShouldNotBeFound("targetRarityMaxCount.greaterOrEqualThan=" + UPDATED_TARGET_RARITY_MAX_COUNT);
    }

    @Test
    @Transactional
    public void getAllMDeckConditionsByTargetRarityMaxCountIsLessThanSomething() throws Exception {
        // Initialize the database
        mDeckConditionRepository.saveAndFlush(mDeckCondition);

        // Get all the mDeckConditionList where targetRarityMaxCount less than or equals to DEFAULT_TARGET_RARITY_MAX_COUNT
        defaultMDeckConditionShouldNotBeFound("targetRarityMaxCount.lessThan=" + DEFAULT_TARGET_RARITY_MAX_COUNT);

        // Get all the mDeckConditionList where targetRarityMaxCount less than or equals to UPDATED_TARGET_RARITY_MAX_COUNT
        defaultMDeckConditionShouldBeFound("targetRarityMaxCount.lessThan=" + UPDATED_TARGET_RARITY_MAX_COUNT);
    }


    @Test
    @Transactional
    public void getAllMDeckConditionsByTargetAttributeMinCountIsEqualToSomething() throws Exception {
        // Initialize the database
        mDeckConditionRepository.saveAndFlush(mDeckCondition);

        // Get all the mDeckConditionList where targetAttributeMinCount equals to DEFAULT_TARGET_ATTRIBUTE_MIN_COUNT
        defaultMDeckConditionShouldBeFound("targetAttributeMinCount.equals=" + DEFAULT_TARGET_ATTRIBUTE_MIN_COUNT);

        // Get all the mDeckConditionList where targetAttributeMinCount equals to UPDATED_TARGET_ATTRIBUTE_MIN_COUNT
        defaultMDeckConditionShouldNotBeFound("targetAttributeMinCount.equals=" + UPDATED_TARGET_ATTRIBUTE_MIN_COUNT);
    }

    @Test
    @Transactional
    public void getAllMDeckConditionsByTargetAttributeMinCountIsInShouldWork() throws Exception {
        // Initialize the database
        mDeckConditionRepository.saveAndFlush(mDeckCondition);

        // Get all the mDeckConditionList where targetAttributeMinCount in DEFAULT_TARGET_ATTRIBUTE_MIN_COUNT or UPDATED_TARGET_ATTRIBUTE_MIN_COUNT
        defaultMDeckConditionShouldBeFound("targetAttributeMinCount.in=" + DEFAULT_TARGET_ATTRIBUTE_MIN_COUNT + "," + UPDATED_TARGET_ATTRIBUTE_MIN_COUNT);

        // Get all the mDeckConditionList where targetAttributeMinCount equals to UPDATED_TARGET_ATTRIBUTE_MIN_COUNT
        defaultMDeckConditionShouldNotBeFound("targetAttributeMinCount.in=" + UPDATED_TARGET_ATTRIBUTE_MIN_COUNT);
    }

    @Test
    @Transactional
    public void getAllMDeckConditionsByTargetAttributeMinCountIsNullOrNotNull() throws Exception {
        // Initialize the database
        mDeckConditionRepository.saveAndFlush(mDeckCondition);

        // Get all the mDeckConditionList where targetAttributeMinCount is not null
        defaultMDeckConditionShouldBeFound("targetAttributeMinCount.specified=true");

        // Get all the mDeckConditionList where targetAttributeMinCount is null
        defaultMDeckConditionShouldNotBeFound("targetAttributeMinCount.specified=false");
    }

    @Test
    @Transactional
    public void getAllMDeckConditionsByTargetAttributeMinCountIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mDeckConditionRepository.saveAndFlush(mDeckCondition);

        // Get all the mDeckConditionList where targetAttributeMinCount greater than or equals to DEFAULT_TARGET_ATTRIBUTE_MIN_COUNT
        defaultMDeckConditionShouldBeFound("targetAttributeMinCount.greaterOrEqualThan=" + DEFAULT_TARGET_ATTRIBUTE_MIN_COUNT);

        // Get all the mDeckConditionList where targetAttributeMinCount greater than or equals to UPDATED_TARGET_ATTRIBUTE_MIN_COUNT
        defaultMDeckConditionShouldNotBeFound("targetAttributeMinCount.greaterOrEqualThan=" + UPDATED_TARGET_ATTRIBUTE_MIN_COUNT);
    }

    @Test
    @Transactional
    public void getAllMDeckConditionsByTargetAttributeMinCountIsLessThanSomething() throws Exception {
        // Initialize the database
        mDeckConditionRepository.saveAndFlush(mDeckCondition);

        // Get all the mDeckConditionList where targetAttributeMinCount less than or equals to DEFAULT_TARGET_ATTRIBUTE_MIN_COUNT
        defaultMDeckConditionShouldNotBeFound("targetAttributeMinCount.lessThan=" + DEFAULT_TARGET_ATTRIBUTE_MIN_COUNT);

        // Get all the mDeckConditionList where targetAttributeMinCount less than or equals to UPDATED_TARGET_ATTRIBUTE_MIN_COUNT
        defaultMDeckConditionShouldBeFound("targetAttributeMinCount.lessThan=" + UPDATED_TARGET_ATTRIBUTE_MIN_COUNT);
    }


    @Test
    @Transactional
    public void getAllMDeckConditionsByTargetNationalityMinCountIsEqualToSomething() throws Exception {
        // Initialize the database
        mDeckConditionRepository.saveAndFlush(mDeckCondition);

        // Get all the mDeckConditionList where targetNationalityMinCount equals to DEFAULT_TARGET_NATIONALITY_MIN_COUNT
        defaultMDeckConditionShouldBeFound("targetNationalityMinCount.equals=" + DEFAULT_TARGET_NATIONALITY_MIN_COUNT);

        // Get all the mDeckConditionList where targetNationalityMinCount equals to UPDATED_TARGET_NATIONALITY_MIN_COUNT
        defaultMDeckConditionShouldNotBeFound("targetNationalityMinCount.equals=" + UPDATED_TARGET_NATIONALITY_MIN_COUNT);
    }

    @Test
    @Transactional
    public void getAllMDeckConditionsByTargetNationalityMinCountIsInShouldWork() throws Exception {
        // Initialize the database
        mDeckConditionRepository.saveAndFlush(mDeckCondition);

        // Get all the mDeckConditionList where targetNationalityMinCount in DEFAULT_TARGET_NATIONALITY_MIN_COUNT or UPDATED_TARGET_NATIONALITY_MIN_COUNT
        defaultMDeckConditionShouldBeFound("targetNationalityMinCount.in=" + DEFAULT_TARGET_NATIONALITY_MIN_COUNT + "," + UPDATED_TARGET_NATIONALITY_MIN_COUNT);

        // Get all the mDeckConditionList where targetNationalityMinCount equals to UPDATED_TARGET_NATIONALITY_MIN_COUNT
        defaultMDeckConditionShouldNotBeFound("targetNationalityMinCount.in=" + UPDATED_TARGET_NATIONALITY_MIN_COUNT);
    }

    @Test
    @Transactional
    public void getAllMDeckConditionsByTargetNationalityMinCountIsNullOrNotNull() throws Exception {
        // Initialize the database
        mDeckConditionRepository.saveAndFlush(mDeckCondition);

        // Get all the mDeckConditionList where targetNationalityMinCount is not null
        defaultMDeckConditionShouldBeFound("targetNationalityMinCount.specified=true");

        // Get all the mDeckConditionList where targetNationalityMinCount is null
        defaultMDeckConditionShouldNotBeFound("targetNationalityMinCount.specified=false");
    }

    @Test
    @Transactional
    public void getAllMDeckConditionsByTargetNationalityMinCountIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mDeckConditionRepository.saveAndFlush(mDeckCondition);

        // Get all the mDeckConditionList where targetNationalityMinCount greater than or equals to DEFAULT_TARGET_NATIONALITY_MIN_COUNT
        defaultMDeckConditionShouldBeFound("targetNationalityMinCount.greaterOrEqualThan=" + DEFAULT_TARGET_NATIONALITY_MIN_COUNT);

        // Get all the mDeckConditionList where targetNationalityMinCount greater than or equals to UPDATED_TARGET_NATIONALITY_MIN_COUNT
        defaultMDeckConditionShouldNotBeFound("targetNationalityMinCount.greaterOrEqualThan=" + UPDATED_TARGET_NATIONALITY_MIN_COUNT);
    }

    @Test
    @Transactional
    public void getAllMDeckConditionsByTargetNationalityMinCountIsLessThanSomething() throws Exception {
        // Initialize the database
        mDeckConditionRepository.saveAndFlush(mDeckCondition);

        // Get all the mDeckConditionList where targetNationalityMinCount less than or equals to DEFAULT_TARGET_NATIONALITY_MIN_COUNT
        defaultMDeckConditionShouldNotBeFound("targetNationalityMinCount.lessThan=" + DEFAULT_TARGET_NATIONALITY_MIN_COUNT);

        // Get all the mDeckConditionList where targetNationalityMinCount less than or equals to UPDATED_TARGET_NATIONALITY_MIN_COUNT
        defaultMDeckConditionShouldBeFound("targetNationalityMinCount.lessThan=" + UPDATED_TARGET_NATIONALITY_MIN_COUNT);
    }


    @Test
    @Transactional
    public void getAllMDeckConditionsByTargetNationalityMaxCountIsEqualToSomething() throws Exception {
        // Initialize the database
        mDeckConditionRepository.saveAndFlush(mDeckCondition);

        // Get all the mDeckConditionList where targetNationalityMaxCount equals to DEFAULT_TARGET_NATIONALITY_MAX_COUNT
        defaultMDeckConditionShouldBeFound("targetNationalityMaxCount.equals=" + DEFAULT_TARGET_NATIONALITY_MAX_COUNT);

        // Get all the mDeckConditionList where targetNationalityMaxCount equals to UPDATED_TARGET_NATIONALITY_MAX_COUNT
        defaultMDeckConditionShouldNotBeFound("targetNationalityMaxCount.equals=" + UPDATED_TARGET_NATIONALITY_MAX_COUNT);
    }

    @Test
    @Transactional
    public void getAllMDeckConditionsByTargetNationalityMaxCountIsInShouldWork() throws Exception {
        // Initialize the database
        mDeckConditionRepository.saveAndFlush(mDeckCondition);

        // Get all the mDeckConditionList where targetNationalityMaxCount in DEFAULT_TARGET_NATIONALITY_MAX_COUNT or UPDATED_TARGET_NATIONALITY_MAX_COUNT
        defaultMDeckConditionShouldBeFound("targetNationalityMaxCount.in=" + DEFAULT_TARGET_NATIONALITY_MAX_COUNT + "," + UPDATED_TARGET_NATIONALITY_MAX_COUNT);

        // Get all the mDeckConditionList where targetNationalityMaxCount equals to UPDATED_TARGET_NATIONALITY_MAX_COUNT
        defaultMDeckConditionShouldNotBeFound("targetNationalityMaxCount.in=" + UPDATED_TARGET_NATIONALITY_MAX_COUNT);
    }

    @Test
    @Transactional
    public void getAllMDeckConditionsByTargetNationalityMaxCountIsNullOrNotNull() throws Exception {
        // Initialize the database
        mDeckConditionRepository.saveAndFlush(mDeckCondition);

        // Get all the mDeckConditionList where targetNationalityMaxCount is not null
        defaultMDeckConditionShouldBeFound("targetNationalityMaxCount.specified=true");

        // Get all the mDeckConditionList where targetNationalityMaxCount is null
        defaultMDeckConditionShouldNotBeFound("targetNationalityMaxCount.specified=false");
    }

    @Test
    @Transactional
    public void getAllMDeckConditionsByTargetNationalityMaxCountIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mDeckConditionRepository.saveAndFlush(mDeckCondition);

        // Get all the mDeckConditionList where targetNationalityMaxCount greater than or equals to DEFAULT_TARGET_NATIONALITY_MAX_COUNT
        defaultMDeckConditionShouldBeFound("targetNationalityMaxCount.greaterOrEqualThan=" + DEFAULT_TARGET_NATIONALITY_MAX_COUNT);

        // Get all the mDeckConditionList where targetNationalityMaxCount greater than or equals to UPDATED_TARGET_NATIONALITY_MAX_COUNT
        defaultMDeckConditionShouldNotBeFound("targetNationalityMaxCount.greaterOrEqualThan=" + UPDATED_TARGET_NATIONALITY_MAX_COUNT);
    }

    @Test
    @Transactional
    public void getAllMDeckConditionsByTargetNationalityMaxCountIsLessThanSomething() throws Exception {
        // Initialize the database
        mDeckConditionRepository.saveAndFlush(mDeckCondition);

        // Get all the mDeckConditionList where targetNationalityMaxCount less than or equals to DEFAULT_TARGET_NATIONALITY_MAX_COUNT
        defaultMDeckConditionShouldNotBeFound("targetNationalityMaxCount.lessThan=" + DEFAULT_TARGET_NATIONALITY_MAX_COUNT);

        // Get all the mDeckConditionList where targetNationalityMaxCount less than or equals to UPDATED_TARGET_NATIONALITY_MAX_COUNT
        defaultMDeckConditionShouldBeFound("targetNationalityMaxCount.lessThan=" + UPDATED_TARGET_NATIONALITY_MAX_COUNT);
    }


    @Test
    @Transactional
    public void getAllMDeckConditionsByTargetTeamMinCountIsEqualToSomething() throws Exception {
        // Initialize the database
        mDeckConditionRepository.saveAndFlush(mDeckCondition);

        // Get all the mDeckConditionList where targetTeamMinCount equals to DEFAULT_TARGET_TEAM_MIN_COUNT
        defaultMDeckConditionShouldBeFound("targetTeamMinCount.equals=" + DEFAULT_TARGET_TEAM_MIN_COUNT);

        // Get all the mDeckConditionList where targetTeamMinCount equals to UPDATED_TARGET_TEAM_MIN_COUNT
        defaultMDeckConditionShouldNotBeFound("targetTeamMinCount.equals=" + UPDATED_TARGET_TEAM_MIN_COUNT);
    }

    @Test
    @Transactional
    public void getAllMDeckConditionsByTargetTeamMinCountIsInShouldWork() throws Exception {
        // Initialize the database
        mDeckConditionRepository.saveAndFlush(mDeckCondition);

        // Get all the mDeckConditionList where targetTeamMinCount in DEFAULT_TARGET_TEAM_MIN_COUNT or UPDATED_TARGET_TEAM_MIN_COUNT
        defaultMDeckConditionShouldBeFound("targetTeamMinCount.in=" + DEFAULT_TARGET_TEAM_MIN_COUNT + "," + UPDATED_TARGET_TEAM_MIN_COUNT);

        // Get all the mDeckConditionList where targetTeamMinCount equals to UPDATED_TARGET_TEAM_MIN_COUNT
        defaultMDeckConditionShouldNotBeFound("targetTeamMinCount.in=" + UPDATED_TARGET_TEAM_MIN_COUNT);
    }

    @Test
    @Transactional
    public void getAllMDeckConditionsByTargetTeamMinCountIsNullOrNotNull() throws Exception {
        // Initialize the database
        mDeckConditionRepository.saveAndFlush(mDeckCondition);

        // Get all the mDeckConditionList where targetTeamMinCount is not null
        defaultMDeckConditionShouldBeFound("targetTeamMinCount.specified=true");

        // Get all the mDeckConditionList where targetTeamMinCount is null
        defaultMDeckConditionShouldNotBeFound("targetTeamMinCount.specified=false");
    }

    @Test
    @Transactional
    public void getAllMDeckConditionsByTargetTeamMinCountIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mDeckConditionRepository.saveAndFlush(mDeckCondition);

        // Get all the mDeckConditionList where targetTeamMinCount greater than or equals to DEFAULT_TARGET_TEAM_MIN_COUNT
        defaultMDeckConditionShouldBeFound("targetTeamMinCount.greaterOrEqualThan=" + DEFAULT_TARGET_TEAM_MIN_COUNT);

        // Get all the mDeckConditionList where targetTeamMinCount greater than or equals to UPDATED_TARGET_TEAM_MIN_COUNT
        defaultMDeckConditionShouldNotBeFound("targetTeamMinCount.greaterOrEqualThan=" + UPDATED_TARGET_TEAM_MIN_COUNT);
    }

    @Test
    @Transactional
    public void getAllMDeckConditionsByTargetTeamMinCountIsLessThanSomething() throws Exception {
        // Initialize the database
        mDeckConditionRepository.saveAndFlush(mDeckCondition);

        // Get all the mDeckConditionList where targetTeamMinCount less than or equals to DEFAULT_TARGET_TEAM_MIN_COUNT
        defaultMDeckConditionShouldNotBeFound("targetTeamMinCount.lessThan=" + DEFAULT_TARGET_TEAM_MIN_COUNT);

        // Get all the mDeckConditionList where targetTeamMinCount less than or equals to UPDATED_TARGET_TEAM_MIN_COUNT
        defaultMDeckConditionShouldBeFound("targetTeamMinCount.lessThan=" + UPDATED_TARGET_TEAM_MIN_COUNT);
    }


    @Test
    @Transactional
    public void getAllMDeckConditionsByTargetTeamMaxCountIsEqualToSomething() throws Exception {
        // Initialize the database
        mDeckConditionRepository.saveAndFlush(mDeckCondition);

        // Get all the mDeckConditionList where targetTeamMaxCount equals to DEFAULT_TARGET_TEAM_MAX_COUNT
        defaultMDeckConditionShouldBeFound("targetTeamMaxCount.equals=" + DEFAULT_TARGET_TEAM_MAX_COUNT);

        // Get all the mDeckConditionList where targetTeamMaxCount equals to UPDATED_TARGET_TEAM_MAX_COUNT
        defaultMDeckConditionShouldNotBeFound("targetTeamMaxCount.equals=" + UPDATED_TARGET_TEAM_MAX_COUNT);
    }

    @Test
    @Transactional
    public void getAllMDeckConditionsByTargetTeamMaxCountIsInShouldWork() throws Exception {
        // Initialize the database
        mDeckConditionRepository.saveAndFlush(mDeckCondition);

        // Get all the mDeckConditionList where targetTeamMaxCount in DEFAULT_TARGET_TEAM_MAX_COUNT or UPDATED_TARGET_TEAM_MAX_COUNT
        defaultMDeckConditionShouldBeFound("targetTeamMaxCount.in=" + DEFAULT_TARGET_TEAM_MAX_COUNT + "," + UPDATED_TARGET_TEAM_MAX_COUNT);

        // Get all the mDeckConditionList where targetTeamMaxCount equals to UPDATED_TARGET_TEAM_MAX_COUNT
        defaultMDeckConditionShouldNotBeFound("targetTeamMaxCount.in=" + UPDATED_TARGET_TEAM_MAX_COUNT);
    }

    @Test
    @Transactional
    public void getAllMDeckConditionsByTargetTeamMaxCountIsNullOrNotNull() throws Exception {
        // Initialize the database
        mDeckConditionRepository.saveAndFlush(mDeckCondition);

        // Get all the mDeckConditionList where targetTeamMaxCount is not null
        defaultMDeckConditionShouldBeFound("targetTeamMaxCount.specified=true");

        // Get all the mDeckConditionList where targetTeamMaxCount is null
        defaultMDeckConditionShouldNotBeFound("targetTeamMaxCount.specified=false");
    }

    @Test
    @Transactional
    public void getAllMDeckConditionsByTargetTeamMaxCountIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mDeckConditionRepository.saveAndFlush(mDeckCondition);

        // Get all the mDeckConditionList where targetTeamMaxCount greater than or equals to DEFAULT_TARGET_TEAM_MAX_COUNT
        defaultMDeckConditionShouldBeFound("targetTeamMaxCount.greaterOrEqualThan=" + DEFAULT_TARGET_TEAM_MAX_COUNT);

        // Get all the mDeckConditionList where targetTeamMaxCount greater than or equals to UPDATED_TARGET_TEAM_MAX_COUNT
        defaultMDeckConditionShouldNotBeFound("targetTeamMaxCount.greaterOrEqualThan=" + UPDATED_TARGET_TEAM_MAX_COUNT);
    }

    @Test
    @Transactional
    public void getAllMDeckConditionsByTargetTeamMaxCountIsLessThanSomething() throws Exception {
        // Initialize the database
        mDeckConditionRepository.saveAndFlush(mDeckCondition);

        // Get all the mDeckConditionList where targetTeamMaxCount less than or equals to DEFAULT_TARGET_TEAM_MAX_COUNT
        defaultMDeckConditionShouldNotBeFound("targetTeamMaxCount.lessThan=" + DEFAULT_TARGET_TEAM_MAX_COUNT);

        // Get all the mDeckConditionList where targetTeamMaxCount less than or equals to UPDATED_TARGET_TEAM_MAX_COUNT
        defaultMDeckConditionShouldBeFound("targetTeamMaxCount.lessThan=" + UPDATED_TARGET_TEAM_MAX_COUNT);
    }


    @Test
    @Transactional
    public void getAllMDeckConditionsByTargetActionMinCountIsEqualToSomething() throws Exception {
        // Initialize the database
        mDeckConditionRepository.saveAndFlush(mDeckCondition);

        // Get all the mDeckConditionList where targetActionMinCount equals to DEFAULT_TARGET_ACTION_MIN_COUNT
        defaultMDeckConditionShouldBeFound("targetActionMinCount.equals=" + DEFAULT_TARGET_ACTION_MIN_COUNT);

        // Get all the mDeckConditionList where targetActionMinCount equals to UPDATED_TARGET_ACTION_MIN_COUNT
        defaultMDeckConditionShouldNotBeFound("targetActionMinCount.equals=" + UPDATED_TARGET_ACTION_MIN_COUNT);
    }

    @Test
    @Transactional
    public void getAllMDeckConditionsByTargetActionMinCountIsInShouldWork() throws Exception {
        // Initialize the database
        mDeckConditionRepository.saveAndFlush(mDeckCondition);

        // Get all the mDeckConditionList where targetActionMinCount in DEFAULT_TARGET_ACTION_MIN_COUNT or UPDATED_TARGET_ACTION_MIN_COUNT
        defaultMDeckConditionShouldBeFound("targetActionMinCount.in=" + DEFAULT_TARGET_ACTION_MIN_COUNT + "," + UPDATED_TARGET_ACTION_MIN_COUNT);

        // Get all the mDeckConditionList where targetActionMinCount equals to UPDATED_TARGET_ACTION_MIN_COUNT
        defaultMDeckConditionShouldNotBeFound("targetActionMinCount.in=" + UPDATED_TARGET_ACTION_MIN_COUNT);
    }

    @Test
    @Transactional
    public void getAllMDeckConditionsByTargetActionMinCountIsNullOrNotNull() throws Exception {
        // Initialize the database
        mDeckConditionRepository.saveAndFlush(mDeckCondition);

        // Get all the mDeckConditionList where targetActionMinCount is not null
        defaultMDeckConditionShouldBeFound("targetActionMinCount.specified=true");

        // Get all the mDeckConditionList where targetActionMinCount is null
        defaultMDeckConditionShouldNotBeFound("targetActionMinCount.specified=false");
    }

    @Test
    @Transactional
    public void getAllMDeckConditionsByTargetActionMinCountIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mDeckConditionRepository.saveAndFlush(mDeckCondition);

        // Get all the mDeckConditionList where targetActionMinCount greater than or equals to DEFAULT_TARGET_ACTION_MIN_COUNT
        defaultMDeckConditionShouldBeFound("targetActionMinCount.greaterOrEqualThan=" + DEFAULT_TARGET_ACTION_MIN_COUNT);

        // Get all the mDeckConditionList where targetActionMinCount greater than or equals to UPDATED_TARGET_ACTION_MIN_COUNT
        defaultMDeckConditionShouldNotBeFound("targetActionMinCount.greaterOrEqualThan=" + UPDATED_TARGET_ACTION_MIN_COUNT);
    }

    @Test
    @Transactional
    public void getAllMDeckConditionsByTargetActionMinCountIsLessThanSomething() throws Exception {
        // Initialize the database
        mDeckConditionRepository.saveAndFlush(mDeckCondition);

        // Get all the mDeckConditionList where targetActionMinCount less than or equals to DEFAULT_TARGET_ACTION_MIN_COUNT
        defaultMDeckConditionShouldNotBeFound("targetActionMinCount.lessThan=" + DEFAULT_TARGET_ACTION_MIN_COUNT);

        // Get all the mDeckConditionList where targetActionMinCount less than or equals to UPDATED_TARGET_ACTION_MIN_COUNT
        defaultMDeckConditionShouldBeFound("targetActionMinCount.lessThan=" + UPDATED_TARGET_ACTION_MIN_COUNT);
    }


    @Test
    @Transactional
    public void getAllMDeckConditionsByTargetActionMaxCountIsEqualToSomething() throws Exception {
        // Initialize the database
        mDeckConditionRepository.saveAndFlush(mDeckCondition);

        // Get all the mDeckConditionList where targetActionMaxCount equals to DEFAULT_TARGET_ACTION_MAX_COUNT
        defaultMDeckConditionShouldBeFound("targetActionMaxCount.equals=" + DEFAULT_TARGET_ACTION_MAX_COUNT);

        // Get all the mDeckConditionList where targetActionMaxCount equals to UPDATED_TARGET_ACTION_MAX_COUNT
        defaultMDeckConditionShouldNotBeFound("targetActionMaxCount.equals=" + UPDATED_TARGET_ACTION_MAX_COUNT);
    }

    @Test
    @Transactional
    public void getAllMDeckConditionsByTargetActionMaxCountIsInShouldWork() throws Exception {
        // Initialize the database
        mDeckConditionRepository.saveAndFlush(mDeckCondition);

        // Get all the mDeckConditionList where targetActionMaxCount in DEFAULT_TARGET_ACTION_MAX_COUNT or UPDATED_TARGET_ACTION_MAX_COUNT
        defaultMDeckConditionShouldBeFound("targetActionMaxCount.in=" + DEFAULT_TARGET_ACTION_MAX_COUNT + "," + UPDATED_TARGET_ACTION_MAX_COUNT);

        // Get all the mDeckConditionList where targetActionMaxCount equals to UPDATED_TARGET_ACTION_MAX_COUNT
        defaultMDeckConditionShouldNotBeFound("targetActionMaxCount.in=" + UPDATED_TARGET_ACTION_MAX_COUNT);
    }

    @Test
    @Transactional
    public void getAllMDeckConditionsByTargetActionMaxCountIsNullOrNotNull() throws Exception {
        // Initialize the database
        mDeckConditionRepository.saveAndFlush(mDeckCondition);

        // Get all the mDeckConditionList where targetActionMaxCount is not null
        defaultMDeckConditionShouldBeFound("targetActionMaxCount.specified=true");

        // Get all the mDeckConditionList where targetActionMaxCount is null
        defaultMDeckConditionShouldNotBeFound("targetActionMaxCount.specified=false");
    }

    @Test
    @Transactional
    public void getAllMDeckConditionsByTargetActionMaxCountIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mDeckConditionRepository.saveAndFlush(mDeckCondition);

        // Get all the mDeckConditionList where targetActionMaxCount greater than or equals to DEFAULT_TARGET_ACTION_MAX_COUNT
        defaultMDeckConditionShouldBeFound("targetActionMaxCount.greaterOrEqualThan=" + DEFAULT_TARGET_ACTION_MAX_COUNT);

        // Get all the mDeckConditionList where targetActionMaxCount greater than or equals to UPDATED_TARGET_ACTION_MAX_COUNT
        defaultMDeckConditionShouldNotBeFound("targetActionMaxCount.greaterOrEqualThan=" + UPDATED_TARGET_ACTION_MAX_COUNT);
    }

    @Test
    @Transactional
    public void getAllMDeckConditionsByTargetActionMaxCountIsLessThanSomething() throws Exception {
        // Initialize the database
        mDeckConditionRepository.saveAndFlush(mDeckCondition);

        // Get all the mDeckConditionList where targetActionMaxCount less than or equals to DEFAULT_TARGET_ACTION_MAX_COUNT
        defaultMDeckConditionShouldNotBeFound("targetActionMaxCount.lessThan=" + DEFAULT_TARGET_ACTION_MAX_COUNT);

        // Get all the mDeckConditionList where targetActionMaxCount less than or equals to UPDATED_TARGET_ACTION_MAX_COUNT
        defaultMDeckConditionShouldBeFound("targetActionMaxCount.lessThan=" + UPDATED_TARGET_ACTION_MAX_COUNT);
    }


    @Test
    @Transactional
    public void getAllMDeckConditionsByTargetTriggerEffectMinCountIsEqualToSomething() throws Exception {
        // Initialize the database
        mDeckConditionRepository.saveAndFlush(mDeckCondition);

        // Get all the mDeckConditionList where targetTriggerEffectMinCount equals to DEFAULT_TARGET_TRIGGER_EFFECT_MIN_COUNT
        defaultMDeckConditionShouldBeFound("targetTriggerEffectMinCount.equals=" + DEFAULT_TARGET_TRIGGER_EFFECT_MIN_COUNT);

        // Get all the mDeckConditionList where targetTriggerEffectMinCount equals to UPDATED_TARGET_TRIGGER_EFFECT_MIN_COUNT
        defaultMDeckConditionShouldNotBeFound("targetTriggerEffectMinCount.equals=" + UPDATED_TARGET_TRIGGER_EFFECT_MIN_COUNT);
    }

    @Test
    @Transactional
    public void getAllMDeckConditionsByTargetTriggerEffectMinCountIsInShouldWork() throws Exception {
        // Initialize the database
        mDeckConditionRepository.saveAndFlush(mDeckCondition);

        // Get all the mDeckConditionList where targetTriggerEffectMinCount in DEFAULT_TARGET_TRIGGER_EFFECT_MIN_COUNT or UPDATED_TARGET_TRIGGER_EFFECT_MIN_COUNT
        defaultMDeckConditionShouldBeFound("targetTriggerEffectMinCount.in=" + DEFAULT_TARGET_TRIGGER_EFFECT_MIN_COUNT + "," + UPDATED_TARGET_TRIGGER_EFFECT_MIN_COUNT);

        // Get all the mDeckConditionList where targetTriggerEffectMinCount equals to UPDATED_TARGET_TRIGGER_EFFECT_MIN_COUNT
        defaultMDeckConditionShouldNotBeFound("targetTriggerEffectMinCount.in=" + UPDATED_TARGET_TRIGGER_EFFECT_MIN_COUNT);
    }

    @Test
    @Transactional
    public void getAllMDeckConditionsByTargetTriggerEffectMinCountIsNullOrNotNull() throws Exception {
        // Initialize the database
        mDeckConditionRepository.saveAndFlush(mDeckCondition);

        // Get all the mDeckConditionList where targetTriggerEffectMinCount is not null
        defaultMDeckConditionShouldBeFound("targetTriggerEffectMinCount.specified=true");

        // Get all the mDeckConditionList where targetTriggerEffectMinCount is null
        defaultMDeckConditionShouldNotBeFound("targetTriggerEffectMinCount.specified=false");
    }

    @Test
    @Transactional
    public void getAllMDeckConditionsByTargetTriggerEffectMinCountIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mDeckConditionRepository.saveAndFlush(mDeckCondition);

        // Get all the mDeckConditionList where targetTriggerEffectMinCount greater than or equals to DEFAULT_TARGET_TRIGGER_EFFECT_MIN_COUNT
        defaultMDeckConditionShouldBeFound("targetTriggerEffectMinCount.greaterOrEqualThan=" + DEFAULT_TARGET_TRIGGER_EFFECT_MIN_COUNT);

        // Get all the mDeckConditionList where targetTriggerEffectMinCount greater than or equals to UPDATED_TARGET_TRIGGER_EFFECT_MIN_COUNT
        defaultMDeckConditionShouldNotBeFound("targetTriggerEffectMinCount.greaterOrEqualThan=" + UPDATED_TARGET_TRIGGER_EFFECT_MIN_COUNT);
    }

    @Test
    @Transactional
    public void getAllMDeckConditionsByTargetTriggerEffectMinCountIsLessThanSomething() throws Exception {
        // Initialize the database
        mDeckConditionRepository.saveAndFlush(mDeckCondition);

        // Get all the mDeckConditionList where targetTriggerEffectMinCount less than or equals to DEFAULT_TARGET_TRIGGER_EFFECT_MIN_COUNT
        defaultMDeckConditionShouldNotBeFound("targetTriggerEffectMinCount.lessThan=" + DEFAULT_TARGET_TRIGGER_EFFECT_MIN_COUNT);

        // Get all the mDeckConditionList where targetTriggerEffectMinCount less than or equals to UPDATED_TARGET_TRIGGER_EFFECT_MIN_COUNT
        defaultMDeckConditionShouldBeFound("targetTriggerEffectMinCount.lessThan=" + UPDATED_TARGET_TRIGGER_EFFECT_MIN_COUNT);
    }


    @Test
    @Transactional
    public void getAllMDeckConditionsByTargetTriggerEffectMaxCountIsEqualToSomething() throws Exception {
        // Initialize the database
        mDeckConditionRepository.saveAndFlush(mDeckCondition);

        // Get all the mDeckConditionList where targetTriggerEffectMaxCount equals to DEFAULT_TARGET_TRIGGER_EFFECT_MAX_COUNT
        defaultMDeckConditionShouldBeFound("targetTriggerEffectMaxCount.equals=" + DEFAULT_TARGET_TRIGGER_EFFECT_MAX_COUNT);

        // Get all the mDeckConditionList where targetTriggerEffectMaxCount equals to UPDATED_TARGET_TRIGGER_EFFECT_MAX_COUNT
        defaultMDeckConditionShouldNotBeFound("targetTriggerEffectMaxCount.equals=" + UPDATED_TARGET_TRIGGER_EFFECT_MAX_COUNT);
    }

    @Test
    @Transactional
    public void getAllMDeckConditionsByTargetTriggerEffectMaxCountIsInShouldWork() throws Exception {
        // Initialize the database
        mDeckConditionRepository.saveAndFlush(mDeckCondition);

        // Get all the mDeckConditionList where targetTriggerEffectMaxCount in DEFAULT_TARGET_TRIGGER_EFFECT_MAX_COUNT or UPDATED_TARGET_TRIGGER_EFFECT_MAX_COUNT
        defaultMDeckConditionShouldBeFound("targetTriggerEffectMaxCount.in=" + DEFAULT_TARGET_TRIGGER_EFFECT_MAX_COUNT + "," + UPDATED_TARGET_TRIGGER_EFFECT_MAX_COUNT);

        // Get all the mDeckConditionList where targetTriggerEffectMaxCount equals to UPDATED_TARGET_TRIGGER_EFFECT_MAX_COUNT
        defaultMDeckConditionShouldNotBeFound("targetTriggerEffectMaxCount.in=" + UPDATED_TARGET_TRIGGER_EFFECT_MAX_COUNT);
    }

    @Test
    @Transactional
    public void getAllMDeckConditionsByTargetTriggerEffectMaxCountIsNullOrNotNull() throws Exception {
        // Initialize the database
        mDeckConditionRepository.saveAndFlush(mDeckCondition);

        // Get all the mDeckConditionList where targetTriggerEffectMaxCount is not null
        defaultMDeckConditionShouldBeFound("targetTriggerEffectMaxCount.specified=true");

        // Get all the mDeckConditionList where targetTriggerEffectMaxCount is null
        defaultMDeckConditionShouldNotBeFound("targetTriggerEffectMaxCount.specified=false");
    }

    @Test
    @Transactional
    public void getAllMDeckConditionsByTargetTriggerEffectMaxCountIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mDeckConditionRepository.saveAndFlush(mDeckCondition);

        // Get all the mDeckConditionList where targetTriggerEffectMaxCount greater than or equals to DEFAULT_TARGET_TRIGGER_EFFECT_MAX_COUNT
        defaultMDeckConditionShouldBeFound("targetTriggerEffectMaxCount.greaterOrEqualThan=" + DEFAULT_TARGET_TRIGGER_EFFECT_MAX_COUNT);

        // Get all the mDeckConditionList where targetTriggerEffectMaxCount greater than or equals to UPDATED_TARGET_TRIGGER_EFFECT_MAX_COUNT
        defaultMDeckConditionShouldNotBeFound("targetTriggerEffectMaxCount.greaterOrEqualThan=" + UPDATED_TARGET_TRIGGER_EFFECT_MAX_COUNT);
    }

    @Test
    @Transactional
    public void getAllMDeckConditionsByTargetTriggerEffectMaxCountIsLessThanSomething() throws Exception {
        // Initialize the database
        mDeckConditionRepository.saveAndFlush(mDeckCondition);

        // Get all the mDeckConditionList where targetTriggerEffectMaxCount less than or equals to DEFAULT_TARGET_TRIGGER_EFFECT_MAX_COUNT
        defaultMDeckConditionShouldNotBeFound("targetTriggerEffectMaxCount.lessThan=" + DEFAULT_TARGET_TRIGGER_EFFECT_MAX_COUNT);

        // Get all the mDeckConditionList where targetTriggerEffectMaxCount less than or equals to UPDATED_TARGET_TRIGGER_EFFECT_MAX_COUNT
        defaultMDeckConditionShouldBeFound("targetTriggerEffectMaxCount.lessThan=" + UPDATED_TARGET_TRIGGER_EFFECT_MAX_COUNT);
    }


    @Test
    @Transactional
    public void getAllMDeckConditionsByTargetCharacterIsStartingMinIsEqualToSomething() throws Exception {
        // Initialize the database
        mDeckConditionRepository.saveAndFlush(mDeckCondition);

        // Get all the mDeckConditionList where targetCharacterIsStartingMin equals to DEFAULT_TARGET_CHARACTER_IS_STARTING_MIN
        defaultMDeckConditionShouldBeFound("targetCharacterIsStartingMin.equals=" + DEFAULT_TARGET_CHARACTER_IS_STARTING_MIN);

        // Get all the mDeckConditionList where targetCharacterIsStartingMin equals to UPDATED_TARGET_CHARACTER_IS_STARTING_MIN
        defaultMDeckConditionShouldNotBeFound("targetCharacterIsStartingMin.equals=" + UPDATED_TARGET_CHARACTER_IS_STARTING_MIN);
    }

    @Test
    @Transactional
    public void getAllMDeckConditionsByTargetCharacterIsStartingMinIsInShouldWork() throws Exception {
        // Initialize the database
        mDeckConditionRepository.saveAndFlush(mDeckCondition);

        // Get all the mDeckConditionList where targetCharacterIsStartingMin in DEFAULT_TARGET_CHARACTER_IS_STARTING_MIN or UPDATED_TARGET_CHARACTER_IS_STARTING_MIN
        defaultMDeckConditionShouldBeFound("targetCharacterIsStartingMin.in=" + DEFAULT_TARGET_CHARACTER_IS_STARTING_MIN + "," + UPDATED_TARGET_CHARACTER_IS_STARTING_MIN);

        // Get all the mDeckConditionList where targetCharacterIsStartingMin equals to UPDATED_TARGET_CHARACTER_IS_STARTING_MIN
        defaultMDeckConditionShouldNotBeFound("targetCharacterIsStartingMin.in=" + UPDATED_TARGET_CHARACTER_IS_STARTING_MIN);
    }

    @Test
    @Transactional
    public void getAllMDeckConditionsByTargetCharacterIsStartingMinIsNullOrNotNull() throws Exception {
        // Initialize the database
        mDeckConditionRepository.saveAndFlush(mDeckCondition);

        // Get all the mDeckConditionList where targetCharacterIsStartingMin is not null
        defaultMDeckConditionShouldBeFound("targetCharacterIsStartingMin.specified=true");

        // Get all the mDeckConditionList where targetCharacterIsStartingMin is null
        defaultMDeckConditionShouldNotBeFound("targetCharacterIsStartingMin.specified=false");
    }

    @Test
    @Transactional
    public void getAllMDeckConditionsByTargetCharacterIsStartingMinIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mDeckConditionRepository.saveAndFlush(mDeckCondition);

        // Get all the mDeckConditionList where targetCharacterIsStartingMin greater than or equals to DEFAULT_TARGET_CHARACTER_IS_STARTING_MIN
        defaultMDeckConditionShouldBeFound("targetCharacterIsStartingMin.greaterOrEqualThan=" + DEFAULT_TARGET_CHARACTER_IS_STARTING_MIN);

        // Get all the mDeckConditionList where targetCharacterIsStartingMin greater than or equals to UPDATED_TARGET_CHARACTER_IS_STARTING_MIN
        defaultMDeckConditionShouldNotBeFound("targetCharacterIsStartingMin.greaterOrEqualThan=" + UPDATED_TARGET_CHARACTER_IS_STARTING_MIN);
    }

    @Test
    @Transactional
    public void getAllMDeckConditionsByTargetCharacterIsStartingMinIsLessThanSomething() throws Exception {
        // Initialize the database
        mDeckConditionRepository.saveAndFlush(mDeckCondition);

        // Get all the mDeckConditionList where targetCharacterIsStartingMin less than or equals to DEFAULT_TARGET_CHARACTER_IS_STARTING_MIN
        defaultMDeckConditionShouldNotBeFound("targetCharacterIsStartingMin.lessThan=" + DEFAULT_TARGET_CHARACTER_IS_STARTING_MIN);

        // Get all the mDeckConditionList where targetCharacterIsStartingMin less than or equals to UPDATED_TARGET_CHARACTER_IS_STARTING_MIN
        defaultMDeckConditionShouldBeFound("targetCharacterIsStartingMin.lessThan=" + UPDATED_TARGET_CHARACTER_IS_STARTING_MIN);
    }


    @Test
    @Transactional
    public void getAllMDeckConditionsByTargetCharacterIsStartingMaxIsEqualToSomething() throws Exception {
        // Initialize the database
        mDeckConditionRepository.saveAndFlush(mDeckCondition);

        // Get all the mDeckConditionList where targetCharacterIsStartingMax equals to DEFAULT_TARGET_CHARACTER_IS_STARTING_MAX
        defaultMDeckConditionShouldBeFound("targetCharacterIsStartingMax.equals=" + DEFAULT_TARGET_CHARACTER_IS_STARTING_MAX);

        // Get all the mDeckConditionList where targetCharacterIsStartingMax equals to UPDATED_TARGET_CHARACTER_IS_STARTING_MAX
        defaultMDeckConditionShouldNotBeFound("targetCharacterIsStartingMax.equals=" + UPDATED_TARGET_CHARACTER_IS_STARTING_MAX);
    }

    @Test
    @Transactional
    public void getAllMDeckConditionsByTargetCharacterIsStartingMaxIsInShouldWork() throws Exception {
        // Initialize the database
        mDeckConditionRepository.saveAndFlush(mDeckCondition);

        // Get all the mDeckConditionList where targetCharacterIsStartingMax in DEFAULT_TARGET_CHARACTER_IS_STARTING_MAX or UPDATED_TARGET_CHARACTER_IS_STARTING_MAX
        defaultMDeckConditionShouldBeFound("targetCharacterIsStartingMax.in=" + DEFAULT_TARGET_CHARACTER_IS_STARTING_MAX + "," + UPDATED_TARGET_CHARACTER_IS_STARTING_MAX);

        // Get all the mDeckConditionList where targetCharacterIsStartingMax equals to UPDATED_TARGET_CHARACTER_IS_STARTING_MAX
        defaultMDeckConditionShouldNotBeFound("targetCharacterIsStartingMax.in=" + UPDATED_TARGET_CHARACTER_IS_STARTING_MAX);
    }

    @Test
    @Transactional
    public void getAllMDeckConditionsByTargetCharacterIsStartingMaxIsNullOrNotNull() throws Exception {
        // Initialize the database
        mDeckConditionRepository.saveAndFlush(mDeckCondition);

        // Get all the mDeckConditionList where targetCharacterIsStartingMax is not null
        defaultMDeckConditionShouldBeFound("targetCharacterIsStartingMax.specified=true");

        // Get all the mDeckConditionList where targetCharacterIsStartingMax is null
        defaultMDeckConditionShouldNotBeFound("targetCharacterIsStartingMax.specified=false");
    }

    @Test
    @Transactional
    public void getAllMDeckConditionsByTargetCharacterIsStartingMaxIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mDeckConditionRepository.saveAndFlush(mDeckCondition);

        // Get all the mDeckConditionList where targetCharacterIsStartingMax greater than or equals to DEFAULT_TARGET_CHARACTER_IS_STARTING_MAX
        defaultMDeckConditionShouldBeFound("targetCharacterIsStartingMax.greaterOrEqualThan=" + DEFAULT_TARGET_CHARACTER_IS_STARTING_MAX);

        // Get all the mDeckConditionList where targetCharacterIsStartingMax greater than or equals to UPDATED_TARGET_CHARACTER_IS_STARTING_MAX
        defaultMDeckConditionShouldNotBeFound("targetCharacterIsStartingMax.greaterOrEqualThan=" + UPDATED_TARGET_CHARACTER_IS_STARTING_MAX);
    }

    @Test
    @Transactional
    public void getAllMDeckConditionsByTargetCharacterIsStartingMaxIsLessThanSomething() throws Exception {
        // Initialize the database
        mDeckConditionRepository.saveAndFlush(mDeckCondition);

        // Get all the mDeckConditionList where targetCharacterIsStartingMax less than or equals to DEFAULT_TARGET_CHARACTER_IS_STARTING_MAX
        defaultMDeckConditionShouldNotBeFound("targetCharacterIsStartingMax.lessThan=" + DEFAULT_TARGET_CHARACTER_IS_STARTING_MAX);

        // Get all the mDeckConditionList where targetCharacterIsStartingMax less than or equals to UPDATED_TARGET_CHARACTER_IS_STARTING_MAX
        defaultMDeckConditionShouldBeFound("targetCharacterIsStartingMax.lessThan=" + UPDATED_TARGET_CHARACTER_IS_STARTING_MAX);
    }


    @Test
    @Transactional
    public void getAllMDeckConditionsByTargetPlayableCardIsStartingMinIsEqualToSomething() throws Exception {
        // Initialize the database
        mDeckConditionRepository.saveAndFlush(mDeckCondition);

        // Get all the mDeckConditionList where targetPlayableCardIsStartingMin equals to DEFAULT_TARGET_PLAYABLE_CARD_IS_STARTING_MIN
        defaultMDeckConditionShouldBeFound("targetPlayableCardIsStartingMin.equals=" + DEFAULT_TARGET_PLAYABLE_CARD_IS_STARTING_MIN);

        // Get all the mDeckConditionList where targetPlayableCardIsStartingMin equals to UPDATED_TARGET_PLAYABLE_CARD_IS_STARTING_MIN
        defaultMDeckConditionShouldNotBeFound("targetPlayableCardIsStartingMin.equals=" + UPDATED_TARGET_PLAYABLE_CARD_IS_STARTING_MIN);
    }

    @Test
    @Transactional
    public void getAllMDeckConditionsByTargetPlayableCardIsStartingMinIsInShouldWork() throws Exception {
        // Initialize the database
        mDeckConditionRepository.saveAndFlush(mDeckCondition);

        // Get all the mDeckConditionList where targetPlayableCardIsStartingMin in DEFAULT_TARGET_PLAYABLE_CARD_IS_STARTING_MIN or UPDATED_TARGET_PLAYABLE_CARD_IS_STARTING_MIN
        defaultMDeckConditionShouldBeFound("targetPlayableCardIsStartingMin.in=" + DEFAULT_TARGET_PLAYABLE_CARD_IS_STARTING_MIN + "," + UPDATED_TARGET_PLAYABLE_CARD_IS_STARTING_MIN);

        // Get all the mDeckConditionList where targetPlayableCardIsStartingMin equals to UPDATED_TARGET_PLAYABLE_CARD_IS_STARTING_MIN
        defaultMDeckConditionShouldNotBeFound("targetPlayableCardIsStartingMin.in=" + UPDATED_TARGET_PLAYABLE_CARD_IS_STARTING_MIN);
    }

    @Test
    @Transactional
    public void getAllMDeckConditionsByTargetPlayableCardIsStartingMinIsNullOrNotNull() throws Exception {
        // Initialize the database
        mDeckConditionRepository.saveAndFlush(mDeckCondition);

        // Get all the mDeckConditionList where targetPlayableCardIsStartingMin is not null
        defaultMDeckConditionShouldBeFound("targetPlayableCardIsStartingMin.specified=true");

        // Get all the mDeckConditionList where targetPlayableCardIsStartingMin is null
        defaultMDeckConditionShouldNotBeFound("targetPlayableCardIsStartingMin.specified=false");
    }

    @Test
    @Transactional
    public void getAllMDeckConditionsByTargetPlayableCardIsStartingMinIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mDeckConditionRepository.saveAndFlush(mDeckCondition);

        // Get all the mDeckConditionList where targetPlayableCardIsStartingMin greater than or equals to DEFAULT_TARGET_PLAYABLE_CARD_IS_STARTING_MIN
        defaultMDeckConditionShouldBeFound("targetPlayableCardIsStartingMin.greaterOrEqualThan=" + DEFAULT_TARGET_PLAYABLE_CARD_IS_STARTING_MIN);

        // Get all the mDeckConditionList where targetPlayableCardIsStartingMin greater than or equals to UPDATED_TARGET_PLAYABLE_CARD_IS_STARTING_MIN
        defaultMDeckConditionShouldNotBeFound("targetPlayableCardIsStartingMin.greaterOrEqualThan=" + UPDATED_TARGET_PLAYABLE_CARD_IS_STARTING_MIN);
    }

    @Test
    @Transactional
    public void getAllMDeckConditionsByTargetPlayableCardIsStartingMinIsLessThanSomething() throws Exception {
        // Initialize the database
        mDeckConditionRepository.saveAndFlush(mDeckCondition);

        // Get all the mDeckConditionList where targetPlayableCardIsStartingMin less than or equals to DEFAULT_TARGET_PLAYABLE_CARD_IS_STARTING_MIN
        defaultMDeckConditionShouldNotBeFound("targetPlayableCardIsStartingMin.lessThan=" + DEFAULT_TARGET_PLAYABLE_CARD_IS_STARTING_MIN);

        // Get all the mDeckConditionList where targetPlayableCardIsStartingMin less than or equals to UPDATED_TARGET_PLAYABLE_CARD_IS_STARTING_MIN
        defaultMDeckConditionShouldBeFound("targetPlayableCardIsStartingMin.lessThan=" + UPDATED_TARGET_PLAYABLE_CARD_IS_STARTING_MIN);
    }


    @Test
    @Transactional
    public void getAllMDeckConditionsByTargetPlayableCardIsStartingMaxIsEqualToSomething() throws Exception {
        // Initialize the database
        mDeckConditionRepository.saveAndFlush(mDeckCondition);

        // Get all the mDeckConditionList where targetPlayableCardIsStartingMax equals to DEFAULT_TARGET_PLAYABLE_CARD_IS_STARTING_MAX
        defaultMDeckConditionShouldBeFound("targetPlayableCardIsStartingMax.equals=" + DEFAULT_TARGET_PLAYABLE_CARD_IS_STARTING_MAX);

        // Get all the mDeckConditionList where targetPlayableCardIsStartingMax equals to UPDATED_TARGET_PLAYABLE_CARD_IS_STARTING_MAX
        defaultMDeckConditionShouldNotBeFound("targetPlayableCardIsStartingMax.equals=" + UPDATED_TARGET_PLAYABLE_CARD_IS_STARTING_MAX);
    }

    @Test
    @Transactional
    public void getAllMDeckConditionsByTargetPlayableCardIsStartingMaxIsInShouldWork() throws Exception {
        // Initialize the database
        mDeckConditionRepository.saveAndFlush(mDeckCondition);

        // Get all the mDeckConditionList where targetPlayableCardIsStartingMax in DEFAULT_TARGET_PLAYABLE_CARD_IS_STARTING_MAX or UPDATED_TARGET_PLAYABLE_CARD_IS_STARTING_MAX
        defaultMDeckConditionShouldBeFound("targetPlayableCardIsStartingMax.in=" + DEFAULT_TARGET_PLAYABLE_CARD_IS_STARTING_MAX + "," + UPDATED_TARGET_PLAYABLE_CARD_IS_STARTING_MAX);

        // Get all the mDeckConditionList where targetPlayableCardIsStartingMax equals to UPDATED_TARGET_PLAYABLE_CARD_IS_STARTING_MAX
        defaultMDeckConditionShouldNotBeFound("targetPlayableCardIsStartingMax.in=" + UPDATED_TARGET_PLAYABLE_CARD_IS_STARTING_MAX);
    }

    @Test
    @Transactional
    public void getAllMDeckConditionsByTargetPlayableCardIsStartingMaxIsNullOrNotNull() throws Exception {
        // Initialize the database
        mDeckConditionRepository.saveAndFlush(mDeckCondition);

        // Get all the mDeckConditionList where targetPlayableCardIsStartingMax is not null
        defaultMDeckConditionShouldBeFound("targetPlayableCardIsStartingMax.specified=true");

        // Get all the mDeckConditionList where targetPlayableCardIsStartingMax is null
        defaultMDeckConditionShouldNotBeFound("targetPlayableCardIsStartingMax.specified=false");
    }

    @Test
    @Transactional
    public void getAllMDeckConditionsByTargetPlayableCardIsStartingMaxIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mDeckConditionRepository.saveAndFlush(mDeckCondition);

        // Get all the mDeckConditionList where targetPlayableCardIsStartingMax greater than or equals to DEFAULT_TARGET_PLAYABLE_CARD_IS_STARTING_MAX
        defaultMDeckConditionShouldBeFound("targetPlayableCardIsStartingMax.greaterOrEqualThan=" + DEFAULT_TARGET_PLAYABLE_CARD_IS_STARTING_MAX);

        // Get all the mDeckConditionList where targetPlayableCardIsStartingMax greater than or equals to UPDATED_TARGET_PLAYABLE_CARD_IS_STARTING_MAX
        defaultMDeckConditionShouldNotBeFound("targetPlayableCardIsStartingMax.greaterOrEqualThan=" + UPDATED_TARGET_PLAYABLE_CARD_IS_STARTING_MAX);
    }

    @Test
    @Transactional
    public void getAllMDeckConditionsByTargetPlayableCardIsStartingMaxIsLessThanSomething() throws Exception {
        // Initialize the database
        mDeckConditionRepository.saveAndFlush(mDeckCondition);

        // Get all the mDeckConditionList where targetPlayableCardIsStartingMax less than or equals to DEFAULT_TARGET_PLAYABLE_CARD_IS_STARTING_MAX
        defaultMDeckConditionShouldNotBeFound("targetPlayableCardIsStartingMax.lessThan=" + DEFAULT_TARGET_PLAYABLE_CARD_IS_STARTING_MAX);

        // Get all the mDeckConditionList where targetPlayableCardIsStartingMax less than or equals to UPDATED_TARGET_PLAYABLE_CARD_IS_STARTING_MAX
        defaultMDeckConditionShouldBeFound("targetPlayableCardIsStartingMax.lessThan=" + UPDATED_TARGET_PLAYABLE_CARD_IS_STARTING_MAX);
    }


    @Test
    @Transactional
    public void getAllMDeckConditionsByTargetRarityIsStartingIsEqualToSomething() throws Exception {
        // Initialize the database
        mDeckConditionRepository.saveAndFlush(mDeckCondition);

        // Get all the mDeckConditionList where targetRarityIsStarting equals to DEFAULT_TARGET_RARITY_IS_STARTING
        defaultMDeckConditionShouldBeFound("targetRarityIsStarting.equals=" + DEFAULT_TARGET_RARITY_IS_STARTING);

        // Get all the mDeckConditionList where targetRarityIsStarting equals to UPDATED_TARGET_RARITY_IS_STARTING
        defaultMDeckConditionShouldNotBeFound("targetRarityIsStarting.equals=" + UPDATED_TARGET_RARITY_IS_STARTING);
    }

    @Test
    @Transactional
    public void getAllMDeckConditionsByTargetRarityIsStartingIsInShouldWork() throws Exception {
        // Initialize the database
        mDeckConditionRepository.saveAndFlush(mDeckCondition);

        // Get all the mDeckConditionList where targetRarityIsStarting in DEFAULT_TARGET_RARITY_IS_STARTING or UPDATED_TARGET_RARITY_IS_STARTING
        defaultMDeckConditionShouldBeFound("targetRarityIsStarting.in=" + DEFAULT_TARGET_RARITY_IS_STARTING + "," + UPDATED_TARGET_RARITY_IS_STARTING);

        // Get all the mDeckConditionList where targetRarityIsStarting equals to UPDATED_TARGET_RARITY_IS_STARTING
        defaultMDeckConditionShouldNotBeFound("targetRarityIsStarting.in=" + UPDATED_TARGET_RARITY_IS_STARTING);
    }

    @Test
    @Transactional
    public void getAllMDeckConditionsByTargetRarityIsStartingIsNullOrNotNull() throws Exception {
        // Initialize the database
        mDeckConditionRepository.saveAndFlush(mDeckCondition);

        // Get all the mDeckConditionList where targetRarityIsStarting is not null
        defaultMDeckConditionShouldBeFound("targetRarityIsStarting.specified=true");

        // Get all the mDeckConditionList where targetRarityIsStarting is null
        defaultMDeckConditionShouldNotBeFound("targetRarityIsStarting.specified=false");
    }

    @Test
    @Transactional
    public void getAllMDeckConditionsByTargetRarityIsStartingIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mDeckConditionRepository.saveAndFlush(mDeckCondition);

        // Get all the mDeckConditionList where targetRarityIsStarting greater than or equals to DEFAULT_TARGET_RARITY_IS_STARTING
        defaultMDeckConditionShouldBeFound("targetRarityIsStarting.greaterOrEqualThan=" + DEFAULT_TARGET_RARITY_IS_STARTING);

        // Get all the mDeckConditionList where targetRarityIsStarting greater than or equals to UPDATED_TARGET_RARITY_IS_STARTING
        defaultMDeckConditionShouldNotBeFound("targetRarityIsStarting.greaterOrEqualThan=" + UPDATED_TARGET_RARITY_IS_STARTING);
    }

    @Test
    @Transactional
    public void getAllMDeckConditionsByTargetRarityIsStartingIsLessThanSomething() throws Exception {
        // Initialize the database
        mDeckConditionRepository.saveAndFlush(mDeckCondition);

        // Get all the mDeckConditionList where targetRarityIsStarting less than or equals to DEFAULT_TARGET_RARITY_IS_STARTING
        defaultMDeckConditionShouldNotBeFound("targetRarityIsStarting.lessThan=" + DEFAULT_TARGET_RARITY_IS_STARTING);

        // Get all the mDeckConditionList where targetRarityIsStarting less than or equals to UPDATED_TARGET_RARITY_IS_STARTING
        defaultMDeckConditionShouldBeFound("targetRarityIsStarting.lessThan=" + UPDATED_TARGET_RARITY_IS_STARTING);
    }


    @Test
    @Transactional
    public void getAllMDeckConditionsByTargetAttributeIsStartingIsEqualToSomething() throws Exception {
        // Initialize the database
        mDeckConditionRepository.saveAndFlush(mDeckCondition);

        // Get all the mDeckConditionList where targetAttributeIsStarting equals to DEFAULT_TARGET_ATTRIBUTE_IS_STARTING
        defaultMDeckConditionShouldBeFound("targetAttributeIsStarting.equals=" + DEFAULT_TARGET_ATTRIBUTE_IS_STARTING);

        // Get all the mDeckConditionList where targetAttributeIsStarting equals to UPDATED_TARGET_ATTRIBUTE_IS_STARTING
        defaultMDeckConditionShouldNotBeFound("targetAttributeIsStarting.equals=" + UPDATED_TARGET_ATTRIBUTE_IS_STARTING);
    }

    @Test
    @Transactional
    public void getAllMDeckConditionsByTargetAttributeIsStartingIsInShouldWork() throws Exception {
        // Initialize the database
        mDeckConditionRepository.saveAndFlush(mDeckCondition);

        // Get all the mDeckConditionList where targetAttributeIsStarting in DEFAULT_TARGET_ATTRIBUTE_IS_STARTING or UPDATED_TARGET_ATTRIBUTE_IS_STARTING
        defaultMDeckConditionShouldBeFound("targetAttributeIsStarting.in=" + DEFAULT_TARGET_ATTRIBUTE_IS_STARTING + "," + UPDATED_TARGET_ATTRIBUTE_IS_STARTING);

        // Get all the mDeckConditionList where targetAttributeIsStarting equals to UPDATED_TARGET_ATTRIBUTE_IS_STARTING
        defaultMDeckConditionShouldNotBeFound("targetAttributeIsStarting.in=" + UPDATED_TARGET_ATTRIBUTE_IS_STARTING);
    }

    @Test
    @Transactional
    public void getAllMDeckConditionsByTargetAttributeIsStartingIsNullOrNotNull() throws Exception {
        // Initialize the database
        mDeckConditionRepository.saveAndFlush(mDeckCondition);

        // Get all the mDeckConditionList where targetAttributeIsStarting is not null
        defaultMDeckConditionShouldBeFound("targetAttributeIsStarting.specified=true");

        // Get all the mDeckConditionList where targetAttributeIsStarting is null
        defaultMDeckConditionShouldNotBeFound("targetAttributeIsStarting.specified=false");
    }

    @Test
    @Transactional
    public void getAllMDeckConditionsByTargetAttributeIsStartingIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mDeckConditionRepository.saveAndFlush(mDeckCondition);

        // Get all the mDeckConditionList where targetAttributeIsStarting greater than or equals to DEFAULT_TARGET_ATTRIBUTE_IS_STARTING
        defaultMDeckConditionShouldBeFound("targetAttributeIsStarting.greaterOrEqualThan=" + DEFAULT_TARGET_ATTRIBUTE_IS_STARTING);

        // Get all the mDeckConditionList where targetAttributeIsStarting greater than or equals to UPDATED_TARGET_ATTRIBUTE_IS_STARTING
        defaultMDeckConditionShouldNotBeFound("targetAttributeIsStarting.greaterOrEqualThan=" + UPDATED_TARGET_ATTRIBUTE_IS_STARTING);
    }

    @Test
    @Transactional
    public void getAllMDeckConditionsByTargetAttributeIsStartingIsLessThanSomething() throws Exception {
        // Initialize the database
        mDeckConditionRepository.saveAndFlush(mDeckCondition);

        // Get all the mDeckConditionList where targetAttributeIsStarting less than or equals to DEFAULT_TARGET_ATTRIBUTE_IS_STARTING
        defaultMDeckConditionShouldNotBeFound("targetAttributeIsStarting.lessThan=" + DEFAULT_TARGET_ATTRIBUTE_IS_STARTING);

        // Get all the mDeckConditionList where targetAttributeIsStarting less than or equals to UPDATED_TARGET_ATTRIBUTE_IS_STARTING
        defaultMDeckConditionShouldBeFound("targetAttributeIsStarting.lessThan=" + UPDATED_TARGET_ATTRIBUTE_IS_STARTING);
    }


    @Test
    @Transactional
    public void getAllMDeckConditionsByTargetNationalityIsStartingMinIsEqualToSomething() throws Exception {
        // Initialize the database
        mDeckConditionRepository.saveAndFlush(mDeckCondition);

        // Get all the mDeckConditionList where targetNationalityIsStartingMin equals to DEFAULT_TARGET_NATIONALITY_IS_STARTING_MIN
        defaultMDeckConditionShouldBeFound("targetNationalityIsStartingMin.equals=" + DEFAULT_TARGET_NATIONALITY_IS_STARTING_MIN);

        // Get all the mDeckConditionList where targetNationalityIsStartingMin equals to UPDATED_TARGET_NATIONALITY_IS_STARTING_MIN
        defaultMDeckConditionShouldNotBeFound("targetNationalityIsStartingMin.equals=" + UPDATED_TARGET_NATIONALITY_IS_STARTING_MIN);
    }

    @Test
    @Transactional
    public void getAllMDeckConditionsByTargetNationalityIsStartingMinIsInShouldWork() throws Exception {
        // Initialize the database
        mDeckConditionRepository.saveAndFlush(mDeckCondition);

        // Get all the mDeckConditionList where targetNationalityIsStartingMin in DEFAULT_TARGET_NATIONALITY_IS_STARTING_MIN or UPDATED_TARGET_NATIONALITY_IS_STARTING_MIN
        defaultMDeckConditionShouldBeFound("targetNationalityIsStartingMin.in=" + DEFAULT_TARGET_NATIONALITY_IS_STARTING_MIN + "," + UPDATED_TARGET_NATIONALITY_IS_STARTING_MIN);

        // Get all the mDeckConditionList where targetNationalityIsStartingMin equals to UPDATED_TARGET_NATIONALITY_IS_STARTING_MIN
        defaultMDeckConditionShouldNotBeFound("targetNationalityIsStartingMin.in=" + UPDATED_TARGET_NATIONALITY_IS_STARTING_MIN);
    }

    @Test
    @Transactional
    public void getAllMDeckConditionsByTargetNationalityIsStartingMinIsNullOrNotNull() throws Exception {
        // Initialize the database
        mDeckConditionRepository.saveAndFlush(mDeckCondition);

        // Get all the mDeckConditionList where targetNationalityIsStartingMin is not null
        defaultMDeckConditionShouldBeFound("targetNationalityIsStartingMin.specified=true");

        // Get all the mDeckConditionList where targetNationalityIsStartingMin is null
        defaultMDeckConditionShouldNotBeFound("targetNationalityIsStartingMin.specified=false");
    }

    @Test
    @Transactional
    public void getAllMDeckConditionsByTargetNationalityIsStartingMinIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mDeckConditionRepository.saveAndFlush(mDeckCondition);

        // Get all the mDeckConditionList where targetNationalityIsStartingMin greater than or equals to DEFAULT_TARGET_NATIONALITY_IS_STARTING_MIN
        defaultMDeckConditionShouldBeFound("targetNationalityIsStartingMin.greaterOrEqualThan=" + DEFAULT_TARGET_NATIONALITY_IS_STARTING_MIN);

        // Get all the mDeckConditionList where targetNationalityIsStartingMin greater than or equals to UPDATED_TARGET_NATIONALITY_IS_STARTING_MIN
        defaultMDeckConditionShouldNotBeFound("targetNationalityIsStartingMin.greaterOrEqualThan=" + UPDATED_TARGET_NATIONALITY_IS_STARTING_MIN);
    }

    @Test
    @Transactional
    public void getAllMDeckConditionsByTargetNationalityIsStartingMinIsLessThanSomething() throws Exception {
        // Initialize the database
        mDeckConditionRepository.saveAndFlush(mDeckCondition);

        // Get all the mDeckConditionList where targetNationalityIsStartingMin less than or equals to DEFAULT_TARGET_NATIONALITY_IS_STARTING_MIN
        defaultMDeckConditionShouldNotBeFound("targetNationalityIsStartingMin.lessThan=" + DEFAULT_TARGET_NATIONALITY_IS_STARTING_MIN);

        // Get all the mDeckConditionList where targetNationalityIsStartingMin less than or equals to UPDATED_TARGET_NATIONALITY_IS_STARTING_MIN
        defaultMDeckConditionShouldBeFound("targetNationalityIsStartingMin.lessThan=" + UPDATED_TARGET_NATIONALITY_IS_STARTING_MIN);
    }


    @Test
    @Transactional
    public void getAllMDeckConditionsByTargetNationalityIsStartingMaxIsEqualToSomething() throws Exception {
        // Initialize the database
        mDeckConditionRepository.saveAndFlush(mDeckCondition);

        // Get all the mDeckConditionList where targetNationalityIsStartingMax equals to DEFAULT_TARGET_NATIONALITY_IS_STARTING_MAX
        defaultMDeckConditionShouldBeFound("targetNationalityIsStartingMax.equals=" + DEFAULT_TARGET_NATIONALITY_IS_STARTING_MAX);

        // Get all the mDeckConditionList where targetNationalityIsStartingMax equals to UPDATED_TARGET_NATIONALITY_IS_STARTING_MAX
        defaultMDeckConditionShouldNotBeFound("targetNationalityIsStartingMax.equals=" + UPDATED_TARGET_NATIONALITY_IS_STARTING_MAX);
    }

    @Test
    @Transactional
    public void getAllMDeckConditionsByTargetNationalityIsStartingMaxIsInShouldWork() throws Exception {
        // Initialize the database
        mDeckConditionRepository.saveAndFlush(mDeckCondition);

        // Get all the mDeckConditionList where targetNationalityIsStartingMax in DEFAULT_TARGET_NATIONALITY_IS_STARTING_MAX or UPDATED_TARGET_NATIONALITY_IS_STARTING_MAX
        defaultMDeckConditionShouldBeFound("targetNationalityIsStartingMax.in=" + DEFAULT_TARGET_NATIONALITY_IS_STARTING_MAX + "," + UPDATED_TARGET_NATIONALITY_IS_STARTING_MAX);

        // Get all the mDeckConditionList where targetNationalityIsStartingMax equals to UPDATED_TARGET_NATIONALITY_IS_STARTING_MAX
        defaultMDeckConditionShouldNotBeFound("targetNationalityIsStartingMax.in=" + UPDATED_TARGET_NATIONALITY_IS_STARTING_MAX);
    }

    @Test
    @Transactional
    public void getAllMDeckConditionsByTargetNationalityIsStartingMaxIsNullOrNotNull() throws Exception {
        // Initialize the database
        mDeckConditionRepository.saveAndFlush(mDeckCondition);

        // Get all the mDeckConditionList where targetNationalityIsStartingMax is not null
        defaultMDeckConditionShouldBeFound("targetNationalityIsStartingMax.specified=true");

        // Get all the mDeckConditionList where targetNationalityIsStartingMax is null
        defaultMDeckConditionShouldNotBeFound("targetNationalityIsStartingMax.specified=false");
    }

    @Test
    @Transactional
    public void getAllMDeckConditionsByTargetNationalityIsStartingMaxIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mDeckConditionRepository.saveAndFlush(mDeckCondition);

        // Get all the mDeckConditionList where targetNationalityIsStartingMax greater than or equals to DEFAULT_TARGET_NATIONALITY_IS_STARTING_MAX
        defaultMDeckConditionShouldBeFound("targetNationalityIsStartingMax.greaterOrEqualThan=" + DEFAULT_TARGET_NATIONALITY_IS_STARTING_MAX);

        // Get all the mDeckConditionList where targetNationalityIsStartingMax greater than or equals to UPDATED_TARGET_NATIONALITY_IS_STARTING_MAX
        defaultMDeckConditionShouldNotBeFound("targetNationalityIsStartingMax.greaterOrEqualThan=" + UPDATED_TARGET_NATIONALITY_IS_STARTING_MAX);
    }

    @Test
    @Transactional
    public void getAllMDeckConditionsByTargetNationalityIsStartingMaxIsLessThanSomething() throws Exception {
        // Initialize the database
        mDeckConditionRepository.saveAndFlush(mDeckCondition);

        // Get all the mDeckConditionList where targetNationalityIsStartingMax less than or equals to DEFAULT_TARGET_NATIONALITY_IS_STARTING_MAX
        defaultMDeckConditionShouldNotBeFound("targetNationalityIsStartingMax.lessThan=" + DEFAULT_TARGET_NATIONALITY_IS_STARTING_MAX);

        // Get all the mDeckConditionList where targetNationalityIsStartingMax less than or equals to UPDATED_TARGET_NATIONALITY_IS_STARTING_MAX
        defaultMDeckConditionShouldBeFound("targetNationalityIsStartingMax.lessThan=" + UPDATED_TARGET_NATIONALITY_IS_STARTING_MAX);
    }


    @Test
    @Transactional
    public void getAllMDeckConditionsByTargetTeamIsStartingMinIsEqualToSomething() throws Exception {
        // Initialize the database
        mDeckConditionRepository.saveAndFlush(mDeckCondition);

        // Get all the mDeckConditionList where targetTeamIsStartingMin equals to DEFAULT_TARGET_TEAM_IS_STARTING_MIN
        defaultMDeckConditionShouldBeFound("targetTeamIsStartingMin.equals=" + DEFAULT_TARGET_TEAM_IS_STARTING_MIN);

        // Get all the mDeckConditionList where targetTeamIsStartingMin equals to UPDATED_TARGET_TEAM_IS_STARTING_MIN
        defaultMDeckConditionShouldNotBeFound("targetTeamIsStartingMin.equals=" + UPDATED_TARGET_TEAM_IS_STARTING_MIN);
    }

    @Test
    @Transactional
    public void getAllMDeckConditionsByTargetTeamIsStartingMinIsInShouldWork() throws Exception {
        // Initialize the database
        mDeckConditionRepository.saveAndFlush(mDeckCondition);

        // Get all the mDeckConditionList where targetTeamIsStartingMin in DEFAULT_TARGET_TEAM_IS_STARTING_MIN or UPDATED_TARGET_TEAM_IS_STARTING_MIN
        defaultMDeckConditionShouldBeFound("targetTeamIsStartingMin.in=" + DEFAULT_TARGET_TEAM_IS_STARTING_MIN + "," + UPDATED_TARGET_TEAM_IS_STARTING_MIN);

        // Get all the mDeckConditionList where targetTeamIsStartingMin equals to UPDATED_TARGET_TEAM_IS_STARTING_MIN
        defaultMDeckConditionShouldNotBeFound("targetTeamIsStartingMin.in=" + UPDATED_TARGET_TEAM_IS_STARTING_MIN);
    }

    @Test
    @Transactional
    public void getAllMDeckConditionsByTargetTeamIsStartingMinIsNullOrNotNull() throws Exception {
        // Initialize the database
        mDeckConditionRepository.saveAndFlush(mDeckCondition);

        // Get all the mDeckConditionList where targetTeamIsStartingMin is not null
        defaultMDeckConditionShouldBeFound("targetTeamIsStartingMin.specified=true");

        // Get all the mDeckConditionList where targetTeamIsStartingMin is null
        defaultMDeckConditionShouldNotBeFound("targetTeamIsStartingMin.specified=false");
    }

    @Test
    @Transactional
    public void getAllMDeckConditionsByTargetTeamIsStartingMinIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mDeckConditionRepository.saveAndFlush(mDeckCondition);

        // Get all the mDeckConditionList where targetTeamIsStartingMin greater than or equals to DEFAULT_TARGET_TEAM_IS_STARTING_MIN
        defaultMDeckConditionShouldBeFound("targetTeamIsStartingMin.greaterOrEqualThan=" + DEFAULT_TARGET_TEAM_IS_STARTING_MIN);

        // Get all the mDeckConditionList where targetTeamIsStartingMin greater than or equals to UPDATED_TARGET_TEAM_IS_STARTING_MIN
        defaultMDeckConditionShouldNotBeFound("targetTeamIsStartingMin.greaterOrEqualThan=" + UPDATED_TARGET_TEAM_IS_STARTING_MIN);
    }

    @Test
    @Transactional
    public void getAllMDeckConditionsByTargetTeamIsStartingMinIsLessThanSomething() throws Exception {
        // Initialize the database
        mDeckConditionRepository.saveAndFlush(mDeckCondition);

        // Get all the mDeckConditionList where targetTeamIsStartingMin less than or equals to DEFAULT_TARGET_TEAM_IS_STARTING_MIN
        defaultMDeckConditionShouldNotBeFound("targetTeamIsStartingMin.lessThan=" + DEFAULT_TARGET_TEAM_IS_STARTING_MIN);

        // Get all the mDeckConditionList where targetTeamIsStartingMin less than or equals to UPDATED_TARGET_TEAM_IS_STARTING_MIN
        defaultMDeckConditionShouldBeFound("targetTeamIsStartingMin.lessThan=" + UPDATED_TARGET_TEAM_IS_STARTING_MIN);
    }


    @Test
    @Transactional
    public void getAllMDeckConditionsByTargetTeamIsStartingMaxIsEqualToSomething() throws Exception {
        // Initialize the database
        mDeckConditionRepository.saveAndFlush(mDeckCondition);

        // Get all the mDeckConditionList where targetTeamIsStartingMax equals to DEFAULT_TARGET_TEAM_IS_STARTING_MAX
        defaultMDeckConditionShouldBeFound("targetTeamIsStartingMax.equals=" + DEFAULT_TARGET_TEAM_IS_STARTING_MAX);

        // Get all the mDeckConditionList where targetTeamIsStartingMax equals to UPDATED_TARGET_TEAM_IS_STARTING_MAX
        defaultMDeckConditionShouldNotBeFound("targetTeamIsStartingMax.equals=" + UPDATED_TARGET_TEAM_IS_STARTING_MAX);
    }

    @Test
    @Transactional
    public void getAllMDeckConditionsByTargetTeamIsStartingMaxIsInShouldWork() throws Exception {
        // Initialize the database
        mDeckConditionRepository.saveAndFlush(mDeckCondition);

        // Get all the mDeckConditionList where targetTeamIsStartingMax in DEFAULT_TARGET_TEAM_IS_STARTING_MAX or UPDATED_TARGET_TEAM_IS_STARTING_MAX
        defaultMDeckConditionShouldBeFound("targetTeamIsStartingMax.in=" + DEFAULT_TARGET_TEAM_IS_STARTING_MAX + "," + UPDATED_TARGET_TEAM_IS_STARTING_MAX);

        // Get all the mDeckConditionList where targetTeamIsStartingMax equals to UPDATED_TARGET_TEAM_IS_STARTING_MAX
        defaultMDeckConditionShouldNotBeFound("targetTeamIsStartingMax.in=" + UPDATED_TARGET_TEAM_IS_STARTING_MAX);
    }

    @Test
    @Transactional
    public void getAllMDeckConditionsByTargetTeamIsStartingMaxIsNullOrNotNull() throws Exception {
        // Initialize the database
        mDeckConditionRepository.saveAndFlush(mDeckCondition);

        // Get all the mDeckConditionList where targetTeamIsStartingMax is not null
        defaultMDeckConditionShouldBeFound("targetTeamIsStartingMax.specified=true");

        // Get all the mDeckConditionList where targetTeamIsStartingMax is null
        defaultMDeckConditionShouldNotBeFound("targetTeamIsStartingMax.specified=false");
    }

    @Test
    @Transactional
    public void getAllMDeckConditionsByTargetTeamIsStartingMaxIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mDeckConditionRepository.saveAndFlush(mDeckCondition);

        // Get all the mDeckConditionList where targetTeamIsStartingMax greater than or equals to DEFAULT_TARGET_TEAM_IS_STARTING_MAX
        defaultMDeckConditionShouldBeFound("targetTeamIsStartingMax.greaterOrEqualThan=" + DEFAULT_TARGET_TEAM_IS_STARTING_MAX);

        // Get all the mDeckConditionList where targetTeamIsStartingMax greater than or equals to UPDATED_TARGET_TEAM_IS_STARTING_MAX
        defaultMDeckConditionShouldNotBeFound("targetTeamIsStartingMax.greaterOrEqualThan=" + UPDATED_TARGET_TEAM_IS_STARTING_MAX);
    }

    @Test
    @Transactional
    public void getAllMDeckConditionsByTargetTeamIsStartingMaxIsLessThanSomething() throws Exception {
        // Initialize the database
        mDeckConditionRepository.saveAndFlush(mDeckCondition);

        // Get all the mDeckConditionList where targetTeamIsStartingMax less than or equals to DEFAULT_TARGET_TEAM_IS_STARTING_MAX
        defaultMDeckConditionShouldNotBeFound("targetTeamIsStartingMax.lessThan=" + DEFAULT_TARGET_TEAM_IS_STARTING_MAX);

        // Get all the mDeckConditionList where targetTeamIsStartingMax less than or equals to UPDATED_TARGET_TEAM_IS_STARTING_MAX
        defaultMDeckConditionShouldBeFound("targetTeamIsStartingMax.lessThan=" + UPDATED_TARGET_TEAM_IS_STARTING_MAX);
    }


    @Test
    @Transactional
    public void getAllMDeckConditionsByTargetActionIsStartingMinIsEqualToSomething() throws Exception {
        // Initialize the database
        mDeckConditionRepository.saveAndFlush(mDeckCondition);

        // Get all the mDeckConditionList where targetActionIsStartingMin equals to DEFAULT_TARGET_ACTION_IS_STARTING_MIN
        defaultMDeckConditionShouldBeFound("targetActionIsStartingMin.equals=" + DEFAULT_TARGET_ACTION_IS_STARTING_MIN);

        // Get all the mDeckConditionList where targetActionIsStartingMin equals to UPDATED_TARGET_ACTION_IS_STARTING_MIN
        defaultMDeckConditionShouldNotBeFound("targetActionIsStartingMin.equals=" + UPDATED_TARGET_ACTION_IS_STARTING_MIN);
    }

    @Test
    @Transactional
    public void getAllMDeckConditionsByTargetActionIsStartingMinIsInShouldWork() throws Exception {
        // Initialize the database
        mDeckConditionRepository.saveAndFlush(mDeckCondition);

        // Get all the mDeckConditionList where targetActionIsStartingMin in DEFAULT_TARGET_ACTION_IS_STARTING_MIN or UPDATED_TARGET_ACTION_IS_STARTING_MIN
        defaultMDeckConditionShouldBeFound("targetActionIsStartingMin.in=" + DEFAULT_TARGET_ACTION_IS_STARTING_MIN + "," + UPDATED_TARGET_ACTION_IS_STARTING_MIN);

        // Get all the mDeckConditionList where targetActionIsStartingMin equals to UPDATED_TARGET_ACTION_IS_STARTING_MIN
        defaultMDeckConditionShouldNotBeFound("targetActionIsStartingMin.in=" + UPDATED_TARGET_ACTION_IS_STARTING_MIN);
    }

    @Test
    @Transactional
    public void getAllMDeckConditionsByTargetActionIsStartingMinIsNullOrNotNull() throws Exception {
        // Initialize the database
        mDeckConditionRepository.saveAndFlush(mDeckCondition);

        // Get all the mDeckConditionList where targetActionIsStartingMin is not null
        defaultMDeckConditionShouldBeFound("targetActionIsStartingMin.specified=true");

        // Get all the mDeckConditionList where targetActionIsStartingMin is null
        defaultMDeckConditionShouldNotBeFound("targetActionIsStartingMin.specified=false");
    }

    @Test
    @Transactional
    public void getAllMDeckConditionsByTargetActionIsStartingMinIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mDeckConditionRepository.saveAndFlush(mDeckCondition);

        // Get all the mDeckConditionList where targetActionIsStartingMin greater than or equals to DEFAULT_TARGET_ACTION_IS_STARTING_MIN
        defaultMDeckConditionShouldBeFound("targetActionIsStartingMin.greaterOrEqualThan=" + DEFAULT_TARGET_ACTION_IS_STARTING_MIN);

        // Get all the mDeckConditionList where targetActionIsStartingMin greater than or equals to UPDATED_TARGET_ACTION_IS_STARTING_MIN
        defaultMDeckConditionShouldNotBeFound("targetActionIsStartingMin.greaterOrEqualThan=" + UPDATED_TARGET_ACTION_IS_STARTING_MIN);
    }

    @Test
    @Transactional
    public void getAllMDeckConditionsByTargetActionIsStartingMinIsLessThanSomething() throws Exception {
        // Initialize the database
        mDeckConditionRepository.saveAndFlush(mDeckCondition);

        // Get all the mDeckConditionList where targetActionIsStartingMin less than or equals to DEFAULT_TARGET_ACTION_IS_STARTING_MIN
        defaultMDeckConditionShouldNotBeFound("targetActionIsStartingMin.lessThan=" + DEFAULT_TARGET_ACTION_IS_STARTING_MIN);

        // Get all the mDeckConditionList where targetActionIsStartingMin less than or equals to UPDATED_TARGET_ACTION_IS_STARTING_MIN
        defaultMDeckConditionShouldBeFound("targetActionIsStartingMin.lessThan=" + UPDATED_TARGET_ACTION_IS_STARTING_MIN);
    }


    @Test
    @Transactional
    public void getAllMDeckConditionsByTargetActionIsStartingMaxIsEqualToSomething() throws Exception {
        // Initialize the database
        mDeckConditionRepository.saveAndFlush(mDeckCondition);

        // Get all the mDeckConditionList where targetActionIsStartingMax equals to DEFAULT_TARGET_ACTION_IS_STARTING_MAX
        defaultMDeckConditionShouldBeFound("targetActionIsStartingMax.equals=" + DEFAULT_TARGET_ACTION_IS_STARTING_MAX);

        // Get all the mDeckConditionList where targetActionIsStartingMax equals to UPDATED_TARGET_ACTION_IS_STARTING_MAX
        defaultMDeckConditionShouldNotBeFound("targetActionIsStartingMax.equals=" + UPDATED_TARGET_ACTION_IS_STARTING_MAX);
    }

    @Test
    @Transactional
    public void getAllMDeckConditionsByTargetActionIsStartingMaxIsInShouldWork() throws Exception {
        // Initialize the database
        mDeckConditionRepository.saveAndFlush(mDeckCondition);

        // Get all the mDeckConditionList where targetActionIsStartingMax in DEFAULT_TARGET_ACTION_IS_STARTING_MAX or UPDATED_TARGET_ACTION_IS_STARTING_MAX
        defaultMDeckConditionShouldBeFound("targetActionIsStartingMax.in=" + DEFAULT_TARGET_ACTION_IS_STARTING_MAX + "," + UPDATED_TARGET_ACTION_IS_STARTING_MAX);

        // Get all the mDeckConditionList where targetActionIsStartingMax equals to UPDATED_TARGET_ACTION_IS_STARTING_MAX
        defaultMDeckConditionShouldNotBeFound("targetActionIsStartingMax.in=" + UPDATED_TARGET_ACTION_IS_STARTING_MAX);
    }

    @Test
    @Transactional
    public void getAllMDeckConditionsByTargetActionIsStartingMaxIsNullOrNotNull() throws Exception {
        // Initialize the database
        mDeckConditionRepository.saveAndFlush(mDeckCondition);

        // Get all the mDeckConditionList where targetActionIsStartingMax is not null
        defaultMDeckConditionShouldBeFound("targetActionIsStartingMax.specified=true");

        // Get all the mDeckConditionList where targetActionIsStartingMax is null
        defaultMDeckConditionShouldNotBeFound("targetActionIsStartingMax.specified=false");
    }

    @Test
    @Transactional
    public void getAllMDeckConditionsByTargetActionIsStartingMaxIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mDeckConditionRepository.saveAndFlush(mDeckCondition);

        // Get all the mDeckConditionList where targetActionIsStartingMax greater than or equals to DEFAULT_TARGET_ACTION_IS_STARTING_MAX
        defaultMDeckConditionShouldBeFound("targetActionIsStartingMax.greaterOrEqualThan=" + DEFAULT_TARGET_ACTION_IS_STARTING_MAX);

        // Get all the mDeckConditionList where targetActionIsStartingMax greater than or equals to UPDATED_TARGET_ACTION_IS_STARTING_MAX
        defaultMDeckConditionShouldNotBeFound("targetActionIsStartingMax.greaterOrEqualThan=" + UPDATED_TARGET_ACTION_IS_STARTING_MAX);
    }

    @Test
    @Transactional
    public void getAllMDeckConditionsByTargetActionIsStartingMaxIsLessThanSomething() throws Exception {
        // Initialize the database
        mDeckConditionRepository.saveAndFlush(mDeckCondition);

        // Get all the mDeckConditionList where targetActionIsStartingMax less than or equals to DEFAULT_TARGET_ACTION_IS_STARTING_MAX
        defaultMDeckConditionShouldNotBeFound("targetActionIsStartingMax.lessThan=" + DEFAULT_TARGET_ACTION_IS_STARTING_MAX);

        // Get all the mDeckConditionList where targetActionIsStartingMax less than or equals to UPDATED_TARGET_ACTION_IS_STARTING_MAX
        defaultMDeckConditionShouldBeFound("targetActionIsStartingMax.lessThan=" + UPDATED_TARGET_ACTION_IS_STARTING_MAX);
    }


    @Test
    @Transactional
    public void getAllMDeckConditionsByTargetTriggerEffectIsStartingMinIsEqualToSomething() throws Exception {
        // Initialize the database
        mDeckConditionRepository.saveAndFlush(mDeckCondition);

        // Get all the mDeckConditionList where targetTriggerEffectIsStartingMin equals to DEFAULT_TARGET_TRIGGER_EFFECT_IS_STARTING_MIN
        defaultMDeckConditionShouldBeFound("targetTriggerEffectIsStartingMin.equals=" + DEFAULT_TARGET_TRIGGER_EFFECT_IS_STARTING_MIN);

        // Get all the mDeckConditionList where targetTriggerEffectIsStartingMin equals to UPDATED_TARGET_TRIGGER_EFFECT_IS_STARTING_MIN
        defaultMDeckConditionShouldNotBeFound("targetTriggerEffectIsStartingMin.equals=" + UPDATED_TARGET_TRIGGER_EFFECT_IS_STARTING_MIN);
    }

    @Test
    @Transactional
    public void getAllMDeckConditionsByTargetTriggerEffectIsStartingMinIsInShouldWork() throws Exception {
        // Initialize the database
        mDeckConditionRepository.saveAndFlush(mDeckCondition);

        // Get all the mDeckConditionList where targetTriggerEffectIsStartingMin in DEFAULT_TARGET_TRIGGER_EFFECT_IS_STARTING_MIN or UPDATED_TARGET_TRIGGER_EFFECT_IS_STARTING_MIN
        defaultMDeckConditionShouldBeFound("targetTriggerEffectIsStartingMin.in=" + DEFAULT_TARGET_TRIGGER_EFFECT_IS_STARTING_MIN + "," + UPDATED_TARGET_TRIGGER_EFFECT_IS_STARTING_MIN);

        // Get all the mDeckConditionList where targetTriggerEffectIsStartingMin equals to UPDATED_TARGET_TRIGGER_EFFECT_IS_STARTING_MIN
        defaultMDeckConditionShouldNotBeFound("targetTriggerEffectIsStartingMin.in=" + UPDATED_TARGET_TRIGGER_EFFECT_IS_STARTING_MIN);
    }

    @Test
    @Transactional
    public void getAllMDeckConditionsByTargetTriggerEffectIsStartingMinIsNullOrNotNull() throws Exception {
        // Initialize the database
        mDeckConditionRepository.saveAndFlush(mDeckCondition);

        // Get all the mDeckConditionList where targetTriggerEffectIsStartingMin is not null
        defaultMDeckConditionShouldBeFound("targetTriggerEffectIsStartingMin.specified=true");

        // Get all the mDeckConditionList where targetTriggerEffectIsStartingMin is null
        defaultMDeckConditionShouldNotBeFound("targetTriggerEffectIsStartingMin.specified=false");
    }

    @Test
    @Transactional
    public void getAllMDeckConditionsByTargetTriggerEffectIsStartingMinIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mDeckConditionRepository.saveAndFlush(mDeckCondition);

        // Get all the mDeckConditionList where targetTriggerEffectIsStartingMin greater than or equals to DEFAULT_TARGET_TRIGGER_EFFECT_IS_STARTING_MIN
        defaultMDeckConditionShouldBeFound("targetTriggerEffectIsStartingMin.greaterOrEqualThan=" + DEFAULT_TARGET_TRIGGER_EFFECT_IS_STARTING_MIN);

        // Get all the mDeckConditionList where targetTriggerEffectIsStartingMin greater than or equals to UPDATED_TARGET_TRIGGER_EFFECT_IS_STARTING_MIN
        defaultMDeckConditionShouldNotBeFound("targetTriggerEffectIsStartingMin.greaterOrEqualThan=" + UPDATED_TARGET_TRIGGER_EFFECT_IS_STARTING_MIN);
    }

    @Test
    @Transactional
    public void getAllMDeckConditionsByTargetTriggerEffectIsStartingMinIsLessThanSomething() throws Exception {
        // Initialize the database
        mDeckConditionRepository.saveAndFlush(mDeckCondition);

        // Get all the mDeckConditionList where targetTriggerEffectIsStartingMin less than or equals to DEFAULT_TARGET_TRIGGER_EFFECT_IS_STARTING_MIN
        defaultMDeckConditionShouldNotBeFound("targetTriggerEffectIsStartingMin.lessThan=" + DEFAULT_TARGET_TRIGGER_EFFECT_IS_STARTING_MIN);

        // Get all the mDeckConditionList where targetTriggerEffectIsStartingMin less than or equals to UPDATED_TARGET_TRIGGER_EFFECT_IS_STARTING_MIN
        defaultMDeckConditionShouldBeFound("targetTriggerEffectIsStartingMin.lessThan=" + UPDATED_TARGET_TRIGGER_EFFECT_IS_STARTING_MIN);
    }


    @Test
    @Transactional
    public void getAllMDeckConditionsByTargetTriggerEffectIsStartingMaxIsEqualToSomething() throws Exception {
        // Initialize the database
        mDeckConditionRepository.saveAndFlush(mDeckCondition);

        // Get all the mDeckConditionList where targetTriggerEffectIsStartingMax equals to DEFAULT_TARGET_TRIGGER_EFFECT_IS_STARTING_MAX
        defaultMDeckConditionShouldBeFound("targetTriggerEffectIsStartingMax.equals=" + DEFAULT_TARGET_TRIGGER_EFFECT_IS_STARTING_MAX);

        // Get all the mDeckConditionList where targetTriggerEffectIsStartingMax equals to UPDATED_TARGET_TRIGGER_EFFECT_IS_STARTING_MAX
        defaultMDeckConditionShouldNotBeFound("targetTriggerEffectIsStartingMax.equals=" + UPDATED_TARGET_TRIGGER_EFFECT_IS_STARTING_MAX);
    }

    @Test
    @Transactional
    public void getAllMDeckConditionsByTargetTriggerEffectIsStartingMaxIsInShouldWork() throws Exception {
        // Initialize the database
        mDeckConditionRepository.saveAndFlush(mDeckCondition);

        // Get all the mDeckConditionList where targetTriggerEffectIsStartingMax in DEFAULT_TARGET_TRIGGER_EFFECT_IS_STARTING_MAX or UPDATED_TARGET_TRIGGER_EFFECT_IS_STARTING_MAX
        defaultMDeckConditionShouldBeFound("targetTriggerEffectIsStartingMax.in=" + DEFAULT_TARGET_TRIGGER_EFFECT_IS_STARTING_MAX + "," + UPDATED_TARGET_TRIGGER_EFFECT_IS_STARTING_MAX);

        // Get all the mDeckConditionList where targetTriggerEffectIsStartingMax equals to UPDATED_TARGET_TRIGGER_EFFECT_IS_STARTING_MAX
        defaultMDeckConditionShouldNotBeFound("targetTriggerEffectIsStartingMax.in=" + UPDATED_TARGET_TRIGGER_EFFECT_IS_STARTING_MAX);
    }

    @Test
    @Transactional
    public void getAllMDeckConditionsByTargetTriggerEffectIsStartingMaxIsNullOrNotNull() throws Exception {
        // Initialize the database
        mDeckConditionRepository.saveAndFlush(mDeckCondition);

        // Get all the mDeckConditionList where targetTriggerEffectIsStartingMax is not null
        defaultMDeckConditionShouldBeFound("targetTriggerEffectIsStartingMax.specified=true");

        // Get all the mDeckConditionList where targetTriggerEffectIsStartingMax is null
        defaultMDeckConditionShouldNotBeFound("targetTriggerEffectIsStartingMax.specified=false");
    }

    @Test
    @Transactional
    public void getAllMDeckConditionsByTargetTriggerEffectIsStartingMaxIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mDeckConditionRepository.saveAndFlush(mDeckCondition);

        // Get all the mDeckConditionList where targetTriggerEffectIsStartingMax greater than or equals to DEFAULT_TARGET_TRIGGER_EFFECT_IS_STARTING_MAX
        defaultMDeckConditionShouldBeFound("targetTriggerEffectIsStartingMax.greaterOrEqualThan=" + DEFAULT_TARGET_TRIGGER_EFFECT_IS_STARTING_MAX);

        // Get all the mDeckConditionList where targetTriggerEffectIsStartingMax greater than or equals to UPDATED_TARGET_TRIGGER_EFFECT_IS_STARTING_MAX
        defaultMDeckConditionShouldNotBeFound("targetTriggerEffectIsStartingMax.greaterOrEqualThan=" + UPDATED_TARGET_TRIGGER_EFFECT_IS_STARTING_MAX);
    }

    @Test
    @Transactional
    public void getAllMDeckConditionsByTargetTriggerEffectIsStartingMaxIsLessThanSomething() throws Exception {
        // Initialize the database
        mDeckConditionRepository.saveAndFlush(mDeckCondition);

        // Get all the mDeckConditionList where targetTriggerEffectIsStartingMax less than or equals to DEFAULT_TARGET_TRIGGER_EFFECT_IS_STARTING_MAX
        defaultMDeckConditionShouldNotBeFound("targetTriggerEffectIsStartingMax.lessThan=" + DEFAULT_TARGET_TRIGGER_EFFECT_IS_STARTING_MAX);

        // Get all the mDeckConditionList where targetTriggerEffectIsStartingMax less than or equals to UPDATED_TARGET_TRIGGER_EFFECT_IS_STARTING_MAX
        defaultMDeckConditionShouldBeFound("targetTriggerEffectIsStartingMax.lessThan=" + UPDATED_TARGET_TRIGGER_EFFECT_IS_STARTING_MAX);
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultMDeckConditionShouldBeFound(String filter) throws Exception {
        restMDeckConditionMockMvc.perform(get("/api/m-deck-conditions?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(mDeckCondition.getId().intValue())))
            .andExpect(jsonPath("$.[*].targetFormationGroupId").value(hasItem(DEFAULT_TARGET_FORMATION_GROUP_ID)))
            .andExpect(jsonPath("$.[*].targetCharacterGroupMinId").value(hasItem(DEFAULT_TARGET_CHARACTER_GROUP_MIN_ID)))
            .andExpect(jsonPath("$.[*].targetCharacterGroupMaxId").value(hasItem(DEFAULT_TARGET_CHARACTER_GROUP_MAX_ID)))
            .andExpect(jsonPath("$.[*].targetPlayableCardGroupMinId").value(hasItem(DEFAULT_TARGET_PLAYABLE_CARD_GROUP_MIN_ID)))
            .andExpect(jsonPath("$.[*].targetPlayableCardGroupMaxId").value(hasItem(DEFAULT_TARGET_PLAYABLE_CARD_GROUP_MAX_ID)))
            .andExpect(jsonPath("$.[*].targetRarityGroupId").value(hasItem(DEFAULT_TARGET_RARITY_GROUP_ID)))
            .andExpect(jsonPath("$.[*].targetAttribute").value(hasItem(DEFAULT_TARGET_ATTRIBUTE)))
            .andExpect(jsonPath("$.[*].targetNationalityGroupMinId").value(hasItem(DEFAULT_TARGET_NATIONALITY_GROUP_MIN_ID)))
            .andExpect(jsonPath("$.[*].targetNationalityGroupMaxId").value(hasItem(DEFAULT_TARGET_NATIONALITY_GROUP_MAX_ID)))
            .andExpect(jsonPath("$.[*].targetTeamGroupMinId").value(hasItem(DEFAULT_TARGET_TEAM_GROUP_MIN_ID)))
            .andExpect(jsonPath("$.[*].targetTeamGroupMaxId").value(hasItem(DEFAULT_TARGET_TEAM_GROUP_MAX_ID)))
            .andExpect(jsonPath("$.[*].targetActionGroupMinId").value(hasItem(DEFAULT_TARGET_ACTION_GROUP_MIN_ID)))
            .andExpect(jsonPath("$.[*].targetActionGroupMaxId").value(hasItem(DEFAULT_TARGET_ACTION_GROUP_MAX_ID)))
            .andExpect(jsonPath("$.[*].targetTriggerEffectGroupMinId").value(hasItem(DEFAULT_TARGET_TRIGGER_EFFECT_GROUP_MIN_ID)))
            .andExpect(jsonPath("$.[*].targetTriggerEffectGroupMaxId").value(hasItem(DEFAULT_TARGET_TRIGGER_EFFECT_GROUP_MAX_ID)))
            .andExpect(jsonPath("$.[*].targetCharacterMinCount").value(hasItem(DEFAULT_TARGET_CHARACTER_MIN_COUNT)))
            .andExpect(jsonPath("$.[*].targetCharacterMaxCount").value(hasItem(DEFAULT_TARGET_CHARACTER_MAX_COUNT)))
            .andExpect(jsonPath("$.[*].targetPlayableCardMinCount").value(hasItem(DEFAULT_TARGET_PLAYABLE_CARD_MIN_COUNT)))
            .andExpect(jsonPath("$.[*].targetPlayableCardMaxCount").value(hasItem(DEFAULT_TARGET_PLAYABLE_CARD_MAX_COUNT)))
            .andExpect(jsonPath("$.[*].targetRarityMaxCount").value(hasItem(DEFAULT_TARGET_RARITY_MAX_COUNT)))
            .andExpect(jsonPath("$.[*].targetAttributeMinCount").value(hasItem(DEFAULT_TARGET_ATTRIBUTE_MIN_COUNT)))
            .andExpect(jsonPath("$.[*].targetNationalityMinCount").value(hasItem(DEFAULT_TARGET_NATIONALITY_MIN_COUNT)))
            .andExpect(jsonPath("$.[*].targetNationalityMaxCount").value(hasItem(DEFAULT_TARGET_NATIONALITY_MAX_COUNT)))
            .andExpect(jsonPath("$.[*].targetTeamMinCount").value(hasItem(DEFAULT_TARGET_TEAM_MIN_COUNT)))
            .andExpect(jsonPath("$.[*].targetTeamMaxCount").value(hasItem(DEFAULT_TARGET_TEAM_MAX_COUNT)))
            .andExpect(jsonPath("$.[*].targetActionMinCount").value(hasItem(DEFAULT_TARGET_ACTION_MIN_COUNT)))
            .andExpect(jsonPath("$.[*].targetActionMaxCount").value(hasItem(DEFAULT_TARGET_ACTION_MAX_COUNT)))
            .andExpect(jsonPath("$.[*].targetTriggerEffectMinCount").value(hasItem(DEFAULT_TARGET_TRIGGER_EFFECT_MIN_COUNT)))
            .andExpect(jsonPath("$.[*].targetTriggerEffectMaxCount").value(hasItem(DEFAULT_TARGET_TRIGGER_EFFECT_MAX_COUNT)))
            .andExpect(jsonPath("$.[*].targetCharacterIsStartingMin").value(hasItem(DEFAULT_TARGET_CHARACTER_IS_STARTING_MIN)))
            .andExpect(jsonPath("$.[*].targetCharacterIsStartingMax").value(hasItem(DEFAULT_TARGET_CHARACTER_IS_STARTING_MAX)))
            .andExpect(jsonPath("$.[*].targetPlayableCardIsStartingMin").value(hasItem(DEFAULT_TARGET_PLAYABLE_CARD_IS_STARTING_MIN)))
            .andExpect(jsonPath("$.[*].targetPlayableCardIsStartingMax").value(hasItem(DEFAULT_TARGET_PLAYABLE_CARD_IS_STARTING_MAX)))
            .andExpect(jsonPath("$.[*].targetRarityIsStarting").value(hasItem(DEFAULT_TARGET_RARITY_IS_STARTING)))
            .andExpect(jsonPath("$.[*].targetAttributeIsStarting").value(hasItem(DEFAULT_TARGET_ATTRIBUTE_IS_STARTING)))
            .andExpect(jsonPath("$.[*].targetNationalityIsStartingMin").value(hasItem(DEFAULT_TARGET_NATIONALITY_IS_STARTING_MIN)))
            .andExpect(jsonPath("$.[*].targetNationalityIsStartingMax").value(hasItem(DEFAULT_TARGET_NATIONALITY_IS_STARTING_MAX)))
            .andExpect(jsonPath("$.[*].targetTeamIsStartingMin").value(hasItem(DEFAULT_TARGET_TEAM_IS_STARTING_MIN)))
            .andExpect(jsonPath("$.[*].targetTeamIsStartingMax").value(hasItem(DEFAULT_TARGET_TEAM_IS_STARTING_MAX)))
            .andExpect(jsonPath("$.[*].targetActionIsStartingMin").value(hasItem(DEFAULT_TARGET_ACTION_IS_STARTING_MIN)))
            .andExpect(jsonPath("$.[*].targetActionIsStartingMax").value(hasItem(DEFAULT_TARGET_ACTION_IS_STARTING_MAX)))
            .andExpect(jsonPath("$.[*].targetTriggerEffectIsStartingMin").value(hasItem(DEFAULT_TARGET_TRIGGER_EFFECT_IS_STARTING_MIN)))
            .andExpect(jsonPath("$.[*].targetTriggerEffectIsStartingMax").value(hasItem(DEFAULT_TARGET_TRIGGER_EFFECT_IS_STARTING_MAX)));

        // Check, that the count call also returns 1
        restMDeckConditionMockMvc.perform(get("/api/m-deck-conditions/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultMDeckConditionShouldNotBeFound(String filter) throws Exception {
        restMDeckConditionMockMvc.perform(get("/api/m-deck-conditions?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restMDeckConditionMockMvc.perform(get("/api/m-deck-conditions/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("0"));
    }


    @Test
    @Transactional
    public void getNonExistingMDeckCondition() throws Exception {
        // Get the mDeckCondition
        restMDeckConditionMockMvc.perform(get("/api/m-deck-conditions/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateMDeckCondition() throws Exception {
        // Initialize the database
        mDeckConditionRepository.saveAndFlush(mDeckCondition);

        int databaseSizeBeforeUpdate = mDeckConditionRepository.findAll().size();

        // Update the mDeckCondition
        MDeckCondition updatedMDeckCondition = mDeckConditionRepository.findById(mDeckCondition.getId()).get();
        // Disconnect from session so that the updates on updatedMDeckCondition are not directly saved in db
        em.detach(updatedMDeckCondition);
        updatedMDeckCondition
            .targetFormationGroupId(UPDATED_TARGET_FORMATION_GROUP_ID)
            .targetCharacterGroupMinId(UPDATED_TARGET_CHARACTER_GROUP_MIN_ID)
            .targetCharacterGroupMaxId(UPDATED_TARGET_CHARACTER_GROUP_MAX_ID)
            .targetPlayableCardGroupMinId(UPDATED_TARGET_PLAYABLE_CARD_GROUP_MIN_ID)
            .targetPlayableCardGroupMaxId(UPDATED_TARGET_PLAYABLE_CARD_GROUP_MAX_ID)
            .targetRarityGroupId(UPDATED_TARGET_RARITY_GROUP_ID)
            .targetAttribute(UPDATED_TARGET_ATTRIBUTE)
            .targetNationalityGroupMinId(UPDATED_TARGET_NATIONALITY_GROUP_MIN_ID)
            .targetNationalityGroupMaxId(UPDATED_TARGET_NATIONALITY_GROUP_MAX_ID)
            .targetTeamGroupMinId(UPDATED_TARGET_TEAM_GROUP_MIN_ID)
            .targetTeamGroupMaxId(UPDATED_TARGET_TEAM_GROUP_MAX_ID)
            .targetActionGroupMinId(UPDATED_TARGET_ACTION_GROUP_MIN_ID)
            .targetActionGroupMaxId(UPDATED_TARGET_ACTION_GROUP_MAX_ID)
            .targetTriggerEffectGroupMinId(UPDATED_TARGET_TRIGGER_EFFECT_GROUP_MIN_ID)
            .targetTriggerEffectGroupMaxId(UPDATED_TARGET_TRIGGER_EFFECT_GROUP_MAX_ID)
            .targetCharacterMinCount(UPDATED_TARGET_CHARACTER_MIN_COUNT)
            .targetCharacterMaxCount(UPDATED_TARGET_CHARACTER_MAX_COUNT)
            .targetPlayableCardMinCount(UPDATED_TARGET_PLAYABLE_CARD_MIN_COUNT)
            .targetPlayableCardMaxCount(UPDATED_TARGET_PLAYABLE_CARD_MAX_COUNT)
            .targetRarityMaxCount(UPDATED_TARGET_RARITY_MAX_COUNT)
            .targetAttributeMinCount(UPDATED_TARGET_ATTRIBUTE_MIN_COUNT)
            .targetNationalityMinCount(UPDATED_TARGET_NATIONALITY_MIN_COUNT)
            .targetNationalityMaxCount(UPDATED_TARGET_NATIONALITY_MAX_COUNT)
            .targetTeamMinCount(UPDATED_TARGET_TEAM_MIN_COUNT)
            .targetTeamMaxCount(UPDATED_TARGET_TEAM_MAX_COUNT)
            .targetActionMinCount(UPDATED_TARGET_ACTION_MIN_COUNT)
            .targetActionMaxCount(UPDATED_TARGET_ACTION_MAX_COUNT)
            .targetTriggerEffectMinCount(UPDATED_TARGET_TRIGGER_EFFECT_MIN_COUNT)
            .targetTriggerEffectMaxCount(UPDATED_TARGET_TRIGGER_EFFECT_MAX_COUNT)
            .targetCharacterIsStartingMin(UPDATED_TARGET_CHARACTER_IS_STARTING_MIN)
            .targetCharacterIsStartingMax(UPDATED_TARGET_CHARACTER_IS_STARTING_MAX)
            .targetPlayableCardIsStartingMin(UPDATED_TARGET_PLAYABLE_CARD_IS_STARTING_MIN)
            .targetPlayableCardIsStartingMax(UPDATED_TARGET_PLAYABLE_CARD_IS_STARTING_MAX)
            .targetRarityIsStarting(UPDATED_TARGET_RARITY_IS_STARTING)
            .targetAttributeIsStarting(UPDATED_TARGET_ATTRIBUTE_IS_STARTING)
            .targetNationalityIsStartingMin(UPDATED_TARGET_NATIONALITY_IS_STARTING_MIN)
            .targetNationalityIsStartingMax(UPDATED_TARGET_NATIONALITY_IS_STARTING_MAX)
            .targetTeamIsStartingMin(UPDATED_TARGET_TEAM_IS_STARTING_MIN)
            .targetTeamIsStartingMax(UPDATED_TARGET_TEAM_IS_STARTING_MAX)
            .targetActionIsStartingMin(UPDATED_TARGET_ACTION_IS_STARTING_MIN)
            .targetActionIsStartingMax(UPDATED_TARGET_ACTION_IS_STARTING_MAX)
            .targetTriggerEffectIsStartingMin(UPDATED_TARGET_TRIGGER_EFFECT_IS_STARTING_MIN)
            .targetTriggerEffectIsStartingMax(UPDATED_TARGET_TRIGGER_EFFECT_IS_STARTING_MAX);
        MDeckConditionDTO mDeckConditionDTO = mDeckConditionMapper.toDto(updatedMDeckCondition);

        restMDeckConditionMockMvc.perform(put("/api/m-deck-conditions")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mDeckConditionDTO)))
            .andExpect(status().isOk());

        // Validate the MDeckCondition in the database
        List<MDeckCondition> mDeckConditionList = mDeckConditionRepository.findAll();
        assertThat(mDeckConditionList).hasSize(databaseSizeBeforeUpdate);
        MDeckCondition testMDeckCondition = mDeckConditionList.get(mDeckConditionList.size() - 1);
        assertThat(testMDeckCondition.getTargetFormationGroupId()).isEqualTo(UPDATED_TARGET_FORMATION_GROUP_ID);
        assertThat(testMDeckCondition.getTargetCharacterGroupMinId()).isEqualTo(UPDATED_TARGET_CHARACTER_GROUP_MIN_ID);
        assertThat(testMDeckCondition.getTargetCharacterGroupMaxId()).isEqualTo(UPDATED_TARGET_CHARACTER_GROUP_MAX_ID);
        assertThat(testMDeckCondition.getTargetPlayableCardGroupMinId()).isEqualTo(UPDATED_TARGET_PLAYABLE_CARD_GROUP_MIN_ID);
        assertThat(testMDeckCondition.getTargetPlayableCardGroupMaxId()).isEqualTo(UPDATED_TARGET_PLAYABLE_CARD_GROUP_MAX_ID);
        assertThat(testMDeckCondition.getTargetRarityGroupId()).isEqualTo(UPDATED_TARGET_RARITY_GROUP_ID);
        assertThat(testMDeckCondition.getTargetAttribute()).isEqualTo(UPDATED_TARGET_ATTRIBUTE);
        assertThat(testMDeckCondition.getTargetNationalityGroupMinId()).isEqualTo(UPDATED_TARGET_NATIONALITY_GROUP_MIN_ID);
        assertThat(testMDeckCondition.getTargetNationalityGroupMaxId()).isEqualTo(UPDATED_TARGET_NATIONALITY_GROUP_MAX_ID);
        assertThat(testMDeckCondition.getTargetTeamGroupMinId()).isEqualTo(UPDATED_TARGET_TEAM_GROUP_MIN_ID);
        assertThat(testMDeckCondition.getTargetTeamGroupMaxId()).isEqualTo(UPDATED_TARGET_TEAM_GROUP_MAX_ID);
        assertThat(testMDeckCondition.getTargetActionGroupMinId()).isEqualTo(UPDATED_TARGET_ACTION_GROUP_MIN_ID);
        assertThat(testMDeckCondition.getTargetActionGroupMaxId()).isEqualTo(UPDATED_TARGET_ACTION_GROUP_MAX_ID);
        assertThat(testMDeckCondition.getTargetTriggerEffectGroupMinId()).isEqualTo(UPDATED_TARGET_TRIGGER_EFFECT_GROUP_MIN_ID);
        assertThat(testMDeckCondition.getTargetTriggerEffectGroupMaxId()).isEqualTo(UPDATED_TARGET_TRIGGER_EFFECT_GROUP_MAX_ID);
        assertThat(testMDeckCondition.getTargetCharacterMinCount()).isEqualTo(UPDATED_TARGET_CHARACTER_MIN_COUNT);
        assertThat(testMDeckCondition.getTargetCharacterMaxCount()).isEqualTo(UPDATED_TARGET_CHARACTER_MAX_COUNT);
        assertThat(testMDeckCondition.getTargetPlayableCardMinCount()).isEqualTo(UPDATED_TARGET_PLAYABLE_CARD_MIN_COUNT);
        assertThat(testMDeckCondition.getTargetPlayableCardMaxCount()).isEqualTo(UPDATED_TARGET_PLAYABLE_CARD_MAX_COUNT);
        assertThat(testMDeckCondition.getTargetRarityMaxCount()).isEqualTo(UPDATED_TARGET_RARITY_MAX_COUNT);
        assertThat(testMDeckCondition.getTargetAttributeMinCount()).isEqualTo(UPDATED_TARGET_ATTRIBUTE_MIN_COUNT);
        assertThat(testMDeckCondition.getTargetNationalityMinCount()).isEqualTo(UPDATED_TARGET_NATIONALITY_MIN_COUNT);
        assertThat(testMDeckCondition.getTargetNationalityMaxCount()).isEqualTo(UPDATED_TARGET_NATIONALITY_MAX_COUNT);
        assertThat(testMDeckCondition.getTargetTeamMinCount()).isEqualTo(UPDATED_TARGET_TEAM_MIN_COUNT);
        assertThat(testMDeckCondition.getTargetTeamMaxCount()).isEqualTo(UPDATED_TARGET_TEAM_MAX_COUNT);
        assertThat(testMDeckCondition.getTargetActionMinCount()).isEqualTo(UPDATED_TARGET_ACTION_MIN_COUNT);
        assertThat(testMDeckCondition.getTargetActionMaxCount()).isEqualTo(UPDATED_TARGET_ACTION_MAX_COUNT);
        assertThat(testMDeckCondition.getTargetTriggerEffectMinCount()).isEqualTo(UPDATED_TARGET_TRIGGER_EFFECT_MIN_COUNT);
        assertThat(testMDeckCondition.getTargetTriggerEffectMaxCount()).isEqualTo(UPDATED_TARGET_TRIGGER_EFFECT_MAX_COUNT);
        assertThat(testMDeckCondition.getTargetCharacterIsStartingMin()).isEqualTo(UPDATED_TARGET_CHARACTER_IS_STARTING_MIN);
        assertThat(testMDeckCondition.getTargetCharacterIsStartingMax()).isEqualTo(UPDATED_TARGET_CHARACTER_IS_STARTING_MAX);
        assertThat(testMDeckCondition.getTargetPlayableCardIsStartingMin()).isEqualTo(UPDATED_TARGET_PLAYABLE_CARD_IS_STARTING_MIN);
        assertThat(testMDeckCondition.getTargetPlayableCardIsStartingMax()).isEqualTo(UPDATED_TARGET_PLAYABLE_CARD_IS_STARTING_MAX);
        assertThat(testMDeckCondition.getTargetRarityIsStarting()).isEqualTo(UPDATED_TARGET_RARITY_IS_STARTING);
        assertThat(testMDeckCondition.getTargetAttributeIsStarting()).isEqualTo(UPDATED_TARGET_ATTRIBUTE_IS_STARTING);
        assertThat(testMDeckCondition.getTargetNationalityIsStartingMin()).isEqualTo(UPDATED_TARGET_NATIONALITY_IS_STARTING_MIN);
        assertThat(testMDeckCondition.getTargetNationalityIsStartingMax()).isEqualTo(UPDATED_TARGET_NATIONALITY_IS_STARTING_MAX);
        assertThat(testMDeckCondition.getTargetTeamIsStartingMin()).isEqualTo(UPDATED_TARGET_TEAM_IS_STARTING_MIN);
        assertThat(testMDeckCondition.getTargetTeamIsStartingMax()).isEqualTo(UPDATED_TARGET_TEAM_IS_STARTING_MAX);
        assertThat(testMDeckCondition.getTargetActionIsStartingMin()).isEqualTo(UPDATED_TARGET_ACTION_IS_STARTING_MIN);
        assertThat(testMDeckCondition.getTargetActionIsStartingMax()).isEqualTo(UPDATED_TARGET_ACTION_IS_STARTING_MAX);
        assertThat(testMDeckCondition.getTargetTriggerEffectIsStartingMin()).isEqualTo(UPDATED_TARGET_TRIGGER_EFFECT_IS_STARTING_MIN);
        assertThat(testMDeckCondition.getTargetTriggerEffectIsStartingMax()).isEqualTo(UPDATED_TARGET_TRIGGER_EFFECT_IS_STARTING_MAX);
    }

    @Test
    @Transactional
    public void updateNonExistingMDeckCondition() throws Exception {
        int databaseSizeBeforeUpdate = mDeckConditionRepository.findAll().size();

        // Create the MDeckCondition
        MDeckConditionDTO mDeckConditionDTO = mDeckConditionMapper.toDto(mDeckCondition);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restMDeckConditionMockMvc.perform(put("/api/m-deck-conditions")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mDeckConditionDTO)))
            .andExpect(status().isBadRequest());

        // Validate the MDeckCondition in the database
        List<MDeckCondition> mDeckConditionList = mDeckConditionRepository.findAll();
        assertThat(mDeckConditionList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteMDeckCondition() throws Exception {
        // Initialize the database
        mDeckConditionRepository.saveAndFlush(mDeckCondition);

        int databaseSizeBeforeDelete = mDeckConditionRepository.findAll().size();

        // Delete the mDeckCondition
        restMDeckConditionMockMvc.perform(delete("/api/m-deck-conditions/{id}", mDeckCondition.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database is empty
        List<MDeckCondition> mDeckConditionList = mDeckConditionRepository.findAll();
        assertThat(mDeckConditionList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(MDeckCondition.class);
        MDeckCondition mDeckCondition1 = new MDeckCondition();
        mDeckCondition1.setId(1L);
        MDeckCondition mDeckCondition2 = new MDeckCondition();
        mDeckCondition2.setId(mDeckCondition1.getId());
        assertThat(mDeckCondition1).isEqualTo(mDeckCondition2);
        mDeckCondition2.setId(2L);
        assertThat(mDeckCondition1).isNotEqualTo(mDeckCondition2);
        mDeckCondition1.setId(null);
        assertThat(mDeckCondition1).isNotEqualTo(mDeckCondition2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(MDeckConditionDTO.class);
        MDeckConditionDTO mDeckConditionDTO1 = new MDeckConditionDTO();
        mDeckConditionDTO1.setId(1L);
        MDeckConditionDTO mDeckConditionDTO2 = new MDeckConditionDTO();
        assertThat(mDeckConditionDTO1).isNotEqualTo(mDeckConditionDTO2);
        mDeckConditionDTO2.setId(mDeckConditionDTO1.getId());
        assertThat(mDeckConditionDTO1).isEqualTo(mDeckConditionDTO2);
        mDeckConditionDTO2.setId(2L);
        assertThat(mDeckConditionDTO1).isNotEqualTo(mDeckConditionDTO2);
        mDeckConditionDTO1.setId(null);
        assertThat(mDeckConditionDTO1).isNotEqualTo(mDeckConditionDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(mDeckConditionMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(mDeckConditionMapper.fromId(null)).isNull();
    }
}
