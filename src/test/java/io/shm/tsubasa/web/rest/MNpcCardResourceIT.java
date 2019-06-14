package io.shm.tsubasa.web.rest;

import io.shm.tsubasa.TsubasaApp;
import io.shm.tsubasa.domain.MNpcCard;
import io.shm.tsubasa.domain.MCharacter;
import io.shm.tsubasa.repository.MNpcCardRepository;
import io.shm.tsubasa.service.MNpcCardService;
import io.shm.tsubasa.service.dto.MNpcCardDTO;
import io.shm.tsubasa.service.mapper.MNpcCardMapper;
import io.shm.tsubasa.web.rest.errors.ExceptionTranslator;
import io.shm.tsubasa.service.dto.MNpcCardCriteria;
import io.shm.tsubasa.service.MNpcCardQueryService;

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
 * Integration tests for the {@Link MNpcCardResource} REST controller.
 */
@SpringBootTest(classes = TsubasaApp.class)
public class MNpcCardResourceIT {

    private static final Integer DEFAULT_CHARACTER_ID = 1;
    private static final Integer UPDATED_CHARACTER_ID = 2;

    private static final String DEFAULT_SHORT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_SHORT_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_NICK_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NICK_NAME = "BBBBBBBBBB";

    private static final Integer DEFAULT_TEAM_ID = 1;
    private static final Integer UPDATED_TEAM_ID = 2;

    private static final Integer DEFAULT_NATIONALITY_ID = 1;
    private static final Integer UPDATED_NATIONALITY_ID = 2;

    private static final Integer DEFAULT_RARITY = 1;
    private static final Integer UPDATED_RARITY = 2;

    private static final Integer DEFAULT_ATTRIBUTE = 1;
    private static final Integer UPDATED_ATTRIBUTE = 2;

    private static final Integer DEFAULT_MODEL_ID = 1;
    private static final Integer UPDATED_MODEL_ID = 2;

    private static final Integer DEFAULT_LEVEL = 1;
    private static final Integer UPDATED_LEVEL = 2;

    private static final Integer DEFAULT_THUMBNAIL_ASSETS_ID = 1;
    private static final Integer UPDATED_THUMBNAIL_ASSETS_ID = 2;

    private static final Integer DEFAULT_CARD_ILLUST_ASSETS_ID = 1;
    private static final Integer UPDATED_CARD_ILLUST_ASSETS_ID = 2;

    private static final Integer DEFAULT_PLAYABLE_ASSETS_ID = 1;
    private static final Integer UPDATED_PLAYABLE_ASSETS_ID = 2;

    private static final Integer DEFAULT_TEAM_EFFECT_ID = 1;
    private static final Integer UPDATED_TEAM_EFFECT_ID = 2;

    private static final Integer DEFAULT_TEAM_EFFECT_LEVEL = 1;
    private static final Integer UPDATED_TEAM_EFFECT_LEVEL = 2;

    private static final Integer DEFAULT_TRIGGER_EFFECT_ID = 1;
    private static final Integer UPDATED_TRIGGER_EFFECT_ID = 2;

    private static final Integer DEFAULT_ACTION_1_ID = 1;
    private static final Integer UPDATED_ACTION_1_ID = 2;

    private static final Integer DEFAULT_ACTION_1_LEVEL = 1;
    private static final Integer UPDATED_ACTION_1_LEVEL = 2;

    private static final Integer DEFAULT_ACTION_2_ID = 1;
    private static final Integer UPDATED_ACTION_2_ID = 2;

    private static final Integer DEFAULT_ACTION_2_LEVEL = 1;
    private static final Integer UPDATED_ACTION_2_LEVEL = 2;

    private static final Integer DEFAULT_ACTION_3_ID = 1;
    private static final Integer UPDATED_ACTION_3_ID = 2;

    private static final Integer DEFAULT_ACTION_3_LEVEL = 1;
    private static final Integer UPDATED_ACTION_3_LEVEL = 2;

    private static final Integer DEFAULT_ACTION_4_ID = 1;
    private static final Integer UPDATED_ACTION_4_ID = 2;

    private static final Integer DEFAULT_ACTION_4_LEVEL = 1;
    private static final Integer UPDATED_ACTION_4_LEVEL = 2;

    private static final Integer DEFAULT_ACTION_5_ID = 1;
    private static final Integer UPDATED_ACTION_5_ID = 2;

    private static final Integer DEFAULT_ACTION_5_LEVEL = 1;
    private static final Integer UPDATED_ACTION_5_LEVEL = 2;

    private static final Integer DEFAULT_STAMINA = 1;
    private static final Integer UPDATED_STAMINA = 2;

    private static final Integer DEFAULT_DRIBBLE = 1;
    private static final Integer UPDATED_DRIBBLE = 2;

    private static final Integer DEFAULT_SHOOT = 1;
    private static final Integer UPDATED_SHOOT = 2;

    private static final Integer DEFAULT_BALL_PASS = 1;
    private static final Integer UPDATED_BALL_PASS = 2;

    private static final Integer DEFAULT_TACKLE = 1;
    private static final Integer UPDATED_TACKLE = 2;

    private static final Integer DEFAULT_BLOCK = 1;
    private static final Integer UPDATED_BLOCK = 2;

    private static final Integer DEFAULT_INTERCEPT = 1;
    private static final Integer UPDATED_INTERCEPT = 2;

    private static final Integer DEFAULT_SPEED = 1;
    private static final Integer UPDATED_SPEED = 2;

    private static final Integer DEFAULT_POWER = 1;
    private static final Integer UPDATED_POWER = 2;

    private static final Integer DEFAULT_TECHNIQUE = 1;
    private static final Integer UPDATED_TECHNIQUE = 2;

    private static final Integer DEFAULT_PUNCHING = 1;
    private static final Integer UPDATED_PUNCHING = 2;

    private static final Integer DEFAULT_CATCHING = 1;
    private static final Integer UPDATED_CATCHING = 2;

    private static final Integer DEFAULT_HIGH_BALL_BONUS = 1;
    private static final Integer UPDATED_HIGH_BALL_BONUS = 2;

    private static final Integer DEFAULT_LOW_BALL_BONUS = 1;
    private static final Integer UPDATED_LOW_BALL_BONUS = 2;

    private static final Integer DEFAULT_PERSONALITY_ID = 1;
    private static final Integer UPDATED_PERSONALITY_ID = 2;

    private static final Integer DEFAULT_UNIFORM_NO = 1;
    private static final Integer UPDATED_UNIFORM_NO = 2;

    private static final Integer DEFAULT_LEVEL_GROUP_ID = 1;
    private static final Integer UPDATED_LEVEL_GROUP_ID = 2;

    @Autowired
    private MNpcCardRepository mNpcCardRepository;

    @Autowired
    private MNpcCardMapper mNpcCardMapper;

    @Autowired
    private MNpcCardService mNpcCardService;

    @Autowired
    private MNpcCardQueryService mNpcCardQueryService;

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

    private MockMvc restMNpcCardMockMvc;

    private MNpcCard mNpcCard;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final MNpcCardResource mNpcCardResource = new MNpcCardResource(mNpcCardService, mNpcCardQueryService);
        this.restMNpcCardMockMvc = MockMvcBuilders.standaloneSetup(mNpcCardResource)
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
    public static MNpcCard createEntity(EntityManager em) {
        MNpcCard mNpcCard = new MNpcCard()
            .characterId(DEFAULT_CHARACTER_ID)
            .shortName(DEFAULT_SHORT_NAME)
            .nickName(DEFAULT_NICK_NAME)
            .teamId(DEFAULT_TEAM_ID)
            .nationalityId(DEFAULT_NATIONALITY_ID)
            .rarity(DEFAULT_RARITY)
            .attribute(DEFAULT_ATTRIBUTE)
            .modelId(DEFAULT_MODEL_ID)
            .level(DEFAULT_LEVEL)
            .thumbnailAssetsId(DEFAULT_THUMBNAIL_ASSETS_ID)
            .cardIllustAssetsId(DEFAULT_CARD_ILLUST_ASSETS_ID)
            .playableAssetsId(DEFAULT_PLAYABLE_ASSETS_ID)
            .teamEffectId(DEFAULT_TEAM_EFFECT_ID)
            .teamEffectLevel(DEFAULT_TEAM_EFFECT_LEVEL)
            .triggerEffectId(DEFAULT_TRIGGER_EFFECT_ID)
            .action1Id(DEFAULT_ACTION_1_ID)
            .action1Level(DEFAULT_ACTION_1_LEVEL)
            .action2Id(DEFAULT_ACTION_2_ID)
            .action2Level(DEFAULT_ACTION_2_LEVEL)
            .action3Id(DEFAULT_ACTION_3_ID)
            .action3Level(DEFAULT_ACTION_3_LEVEL)
            .action4Id(DEFAULT_ACTION_4_ID)
            .action4Level(DEFAULT_ACTION_4_LEVEL)
            .action5Id(DEFAULT_ACTION_5_ID)
            .action5Level(DEFAULT_ACTION_5_LEVEL)
            .stamina(DEFAULT_STAMINA)
            .dribble(DEFAULT_DRIBBLE)
            .shoot(DEFAULT_SHOOT)
            .ballPass(DEFAULT_BALL_PASS)
            .tackle(DEFAULT_TACKLE)
            .block(DEFAULT_BLOCK)
            .intercept(DEFAULT_INTERCEPT)
            .speed(DEFAULT_SPEED)
            .power(DEFAULT_POWER)
            .technique(DEFAULT_TECHNIQUE)
            .punching(DEFAULT_PUNCHING)
            .catching(DEFAULT_CATCHING)
            .highBallBonus(DEFAULT_HIGH_BALL_BONUS)
            .lowBallBonus(DEFAULT_LOW_BALL_BONUS)
            .personalityId(DEFAULT_PERSONALITY_ID)
            .uniformNo(DEFAULT_UNIFORM_NO)
            .levelGroupId(DEFAULT_LEVEL_GROUP_ID);
        // Add required entity
        MCharacter mCharacter;
        if (TestUtil.findAll(em, MCharacter.class).isEmpty()) {
            mCharacter = MCharacterResourceIT.createEntity(em);
            em.persist(mCharacter);
            em.flush();
        } else {
            mCharacter = TestUtil.findAll(em, MCharacter.class).get(0);
        }
        mNpcCard.setId(mCharacter);
        return mNpcCard;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static MNpcCard createUpdatedEntity(EntityManager em) {
        MNpcCard mNpcCard = new MNpcCard()
            .characterId(UPDATED_CHARACTER_ID)
            .shortName(UPDATED_SHORT_NAME)
            .nickName(UPDATED_NICK_NAME)
            .teamId(UPDATED_TEAM_ID)
            .nationalityId(UPDATED_NATIONALITY_ID)
            .rarity(UPDATED_RARITY)
            .attribute(UPDATED_ATTRIBUTE)
            .modelId(UPDATED_MODEL_ID)
            .level(UPDATED_LEVEL)
            .thumbnailAssetsId(UPDATED_THUMBNAIL_ASSETS_ID)
            .cardIllustAssetsId(UPDATED_CARD_ILLUST_ASSETS_ID)
            .playableAssetsId(UPDATED_PLAYABLE_ASSETS_ID)
            .teamEffectId(UPDATED_TEAM_EFFECT_ID)
            .teamEffectLevel(UPDATED_TEAM_EFFECT_LEVEL)
            .triggerEffectId(UPDATED_TRIGGER_EFFECT_ID)
            .action1Id(UPDATED_ACTION_1_ID)
            .action1Level(UPDATED_ACTION_1_LEVEL)
            .action2Id(UPDATED_ACTION_2_ID)
            .action2Level(UPDATED_ACTION_2_LEVEL)
            .action3Id(UPDATED_ACTION_3_ID)
            .action3Level(UPDATED_ACTION_3_LEVEL)
            .action4Id(UPDATED_ACTION_4_ID)
            .action4Level(UPDATED_ACTION_4_LEVEL)
            .action5Id(UPDATED_ACTION_5_ID)
            .action5Level(UPDATED_ACTION_5_LEVEL)
            .stamina(UPDATED_STAMINA)
            .dribble(UPDATED_DRIBBLE)
            .shoot(UPDATED_SHOOT)
            .ballPass(UPDATED_BALL_PASS)
            .tackle(UPDATED_TACKLE)
            .block(UPDATED_BLOCK)
            .intercept(UPDATED_INTERCEPT)
            .speed(UPDATED_SPEED)
            .power(UPDATED_POWER)
            .technique(UPDATED_TECHNIQUE)
            .punching(UPDATED_PUNCHING)
            .catching(UPDATED_CATCHING)
            .highBallBonus(UPDATED_HIGH_BALL_BONUS)
            .lowBallBonus(UPDATED_LOW_BALL_BONUS)
            .personalityId(UPDATED_PERSONALITY_ID)
            .uniformNo(UPDATED_UNIFORM_NO)
            .levelGroupId(UPDATED_LEVEL_GROUP_ID);
        // Add required entity
        MCharacter mCharacter;
        if (TestUtil.findAll(em, MCharacter.class).isEmpty()) {
            mCharacter = MCharacterResourceIT.createUpdatedEntity(em);
            em.persist(mCharacter);
            em.flush();
        } else {
            mCharacter = TestUtil.findAll(em, MCharacter.class).get(0);
        }
        mNpcCard.setId(mCharacter);
        return mNpcCard;
    }

    @BeforeEach
    public void initTest() {
        mNpcCard = createEntity(em);
    }

    @Test
    @Transactional
    public void createMNpcCard() throws Exception {
        int databaseSizeBeforeCreate = mNpcCardRepository.findAll().size();

        // Create the MNpcCard
        MNpcCardDTO mNpcCardDTO = mNpcCardMapper.toDto(mNpcCard);
        restMNpcCardMockMvc.perform(post("/api/m-npc-cards")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mNpcCardDTO)))
            .andExpect(status().isCreated());

        // Validate the MNpcCard in the database
        List<MNpcCard> mNpcCardList = mNpcCardRepository.findAll();
        assertThat(mNpcCardList).hasSize(databaseSizeBeforeCreate + 1);
        MNpcCard testMNpcCard = mNpcCardList.get(mNpcCardList.size() - 1);
        assertThat(testMNpcCard.getCharacterId()).isEqualTo(DEFAULT_CHARACTER_ID);
        assertThat(testMNpcCard.getShortName()).isEqualTo(DEFAULT_SHORT_NAME);
        assertThat(testMNpcCard.getNickName()).isEqualTo(DEFAULT_NICK_NAME);
        assertThat(testMNpcCard.getTeamId()).isEqualTo(DEFAULT_TEAM_ID);
        assertThat(testMNpcCard.getNationalityId()).isEqualTo(DEFAULT_NATIONALITY_ID);
        assertThat(testMNpcCard.getRarity()).isEqualTo(DEFAULT_RARITY);
        assertThat(testMNpcCard.getAttribute()).isEqualTo(DEFAULT_ATTRIBUTE);
        assertThat(testMNpcCard.getModelId()).isEqualTo(DEFAULT_MODEL_ID);
        assertThat(testMNpcCard.getLevel()).isEqualTo(DEFAULT_LEVEL);
        assertThat(testMNpcCard.getThumbnailAssetsId()).isEqualTo(DEFAULT_THUMBNAIL_ASSETS_ID);
        assertThat(testMNpcCard.getCardIllustAssetsId()).isEqualTo(DEFAULT_CARD_ILLUST_ASSETS_ID);
        assertThat(testMNpcCard.getPlayableAssetsId()).isEqualTo(DEFAULT_PLAYABLE_ASSETS_ID);
        assertThat(testMNpcCard.getTeamEffectId()).isEqualTo(DEFAULT_TEAM_EFFECT_ID);
        assertThat(testMNpcCard.getTeamEffectLevel()).isEqualTo(DEFAULT_TEAM_EFFECT_LEVEL);
        assertThat(testMNpcCard.getTriggerEffectId()).isEqualTo(DEFAULT_TRIGGER_EFFECT_ID);
        assertThat(testMNpcCard.getAction1Id()).isEqualTo(DEFAULT_ACTION_1_ID);
        assertThat(testMNpcCard.getAction1Level()).isEqualTo(DEFAULT_ACTION_1_LEVEL);
        assertThat(testMNpcCard.getAction2Id()).isEqualTo(DEFAULT_ACTION_2_ID);
        assertThat(testMNpcCard.getAction2Level()).isEqualTo(DEFAULT_ACTION_2_LEVEL);
        assertThat(testMNpcCard.getAction3Id()).isEqualTo(DEFAULT_ACTION_3_ID);
        assertThat(testMNpcCard.getAction3Level()).isEqualTo(DEFAULT_ACTION_3_LEVEL);
        assertThat(testMNpcCard.getAction4Id()).isEqualTo(DEFAULT_ACTION_4_ID);
        assertThat(testMNpcCard.getAction4Level()).isEqualTo(DEFAULT_ACTION_4_LEVEL);
        assertThat(testMNpcCard.getAction5Id()).isEqualTo(DEFAULT_ACTION_5_ID);
        assertThat(testMNpcCard.getAction5Level()).isEqualTo(DEFAULT_ACTION_5_LEVEL);
        assertThat(testMNpcCard.getStamina()).isEqualTo(DEFAULT_STAMINA);
        assertThat(testMNpcCard.getDribble()).isEqualTo(DEFAULT_DRIBBLE);
        assertThat(testMNpcCard.getShoot()).isEqualTo(DEFAULT_SHOOT);
        assertThat(testMNpcCard.getBallPass()).isEqualTo(DEFAULT_BALL_PASS);
        assertThat(testMNpcCard.getTackle()).isEqualTo(DEFAULT_TACKLE);
        assertThat(testMNpcCard.getBlock()).isEqualTo(DEFAULT_BLOCK);
        assertThat(testMNpcCard.getIntercept()).isEqualTo(DEFAULT_INTERCEPT);
        assertThat(testMNpcCard.getSpeed()).isEqualTo(DEFAULT_SPEED);
        assertThat(testMNpcCard.getPower()).isEqualTo(DEFAULT_POWER);
        assertThat(testMNpcCard.getTechnique()).isEqualTo(DEFAULT_TECHNIQUE);
        assertThat(testMNpcCard.getPunching()).isEqualTo(DEFAULT_PUNCHING);
        assertThat(testMNpcCard.getCatching()).isEqualTo(DEFAULT_CATCHING);
        assertThat(testMNpcCard.getHighBallBonus()).isEqualTo(DEFAULT_HIGH_BALL_BONUS);
        assertThat(testMNpcCard.getLowBallBonus()).isEqualTo(DEFAULT_LOW_BALL_BONUS);
        assertThat(testMNpcCard.getPersonalityId()).isEqualTo(DEFAULT_PERSONALITY_ID);
        assertThat(testMNpcCard.getUniformNo()).isEqualTo(DEFAULT_UNIFORM_NO);
        assertThat(testMNpcCard.getLevelGroupId()).isEqualTo(DEFAULT_LEVEL_GROUP_ID);
    }

    @Test
    @Transactional
    public void createMNpcCardWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = mNpcCardRepository.findAll().size();

        // Create the MNpcCard with an existing ID
        mNpcCard.setId(1L);
        MNpcCardDTO mNpcCardDTO = mNpcCardMapper.toDto(mNpcCard);

        // An entity with an existing ID cannot be created, so this API call must fail
        restMNpcCardMockMvc.perform(post("/api/m-npc-cards")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mNpcCardDTO)))
            .andExpect(status().isBadRequest());

        // Validate the MNpcCard in the database
        List<MNpcCard> mNpcCardList = mNpcCardRepository.findAll();
        assertThat(mNpcCardList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkCharacterIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = mNpcCardRepository.findAll().size();
        // set the field null
        mNpcCard.setCharacterId(null);

        // Create the MNpcCard, which fails.
        MNpcCardDTO mNpcCardDTO = mNpcCardMapper.toDto(mNpcCard);

        restMNpcCardMockMvc.perform(post("/api/m-npc-cards")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mNpcCardDTO)))
            .andExpect(status().isBadRequest());

        List<MNpcCard> mNpcCardList = mNpcCardRepository.findAll();
        assertThat(mNpcCardList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkTeamIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = mNpcCardRepository.findAll().size();
        // set the field null
        mNpcCard.setTeamId(null);

        // Create the MNpcCard, which fails.
        MNpcCardDTO mNpcCardDTO = mNpcCardMapper.toDto(mNpcCard);

        restMNpcCardMockMvc.perform(post("/api/m-npc-cards")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mNpcCardDTO)))
            .andExpect(status().isBadRequest());

        List<MNpcCard> mNpcCardList = mNpcCardRepository.findAll();
        assertThat(mNpcCardList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkNationalityIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = mNpcCardRepository.findAll().size();
        // set the field null
        mNpcCard.setNationalityId(null);

        // Create the MNpcCard, which fails.
        MNpcCardDTO mNpcCardDTO = mNpcCardMapper.toDto(mNpcCard);

        restMNpcCardMockMvc.perform(post("/api/m-npc-cards")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mNpcCardDTO)))
            .andExpect(status().isBadRequest());

        List<MNpcCard> mNpcCardList = mNpcCardRepository.findAll();
        assertThat(mNpcCardList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkRarityIsRequired() throws Exception {
        int databaseSizeBeforeTest = mNpcCardRepository.findAll().size();
        // set the field null
        mNpcCard.setRarity(null);

        // Create the MNpcCard, which fails.
        MNpcCardDTO mNpcCardDTO = mNpcCardMapper.toDto(mNpcCard);

        restMNpcCardMockMvc.perform(post("/api/m-npc-cards")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mNpcCardDTO)))
            .andExpect(status().isBadRequest());

        List<MNpcCard> mNpcCardList = mNpcCardRepository.findAll();
        assertThat(mNpcCardList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkAttributeIsRequired() throws Exception {
        int databaseSizeBeforeTest = mNpcCardRepository.findAll().size();
        // set the field null
        mNpcCard.setAttribute(null);

        // Create the MNpcCard, which fails.
        MNpcCardDTO mNpcCardDTO = mNpcCardMapper.toDto(mNpcCard);

        restMNpcCardMockMvc.perform(post("/api/m-npc-cards")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mNpcCardDTO)))
            .andExpect(status().isBadRequest());

        List<MNpcCard> mNpcCardList = mNpcCardRepository.findAll();
        assertThat(mNpcCardList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkModelIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = mNpcCardRepository.findAll().size();
        // set the field null
        mNpcCard.setModelId(null);

        // Create the MNpcCard, which fails.
        MNpcCardDTO mNpcCardDTO = mNpcCardMapper.toDto(mNpcCard);

        restMNpcCardMockMvc.perform(post("/api/m-npc-cards")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mNpcCardDTO)))
            .andExpect(status().isBadRequest());

        List<MNpcCard> mNpcCardList = mNpcCardRepository.findAll();
        assertThat(mNpcCardList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkLevelIsRequired() throws Exception {
        int databaseSizeBeforeTest = mNpcCardRepository.findAll().size();
        // set the field null
        mNpcCard.setLevel(null);

        // Create the MNpcCard, which fails.
        MNpcCardDTO mNpcCardDTO = mNpcCardMapper.toDto(mNpcCard);

        restMNpcCardMockMvc.perform(post("/api/m-npc-cards")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mNpcCardDTO)))
            .andExpect(status().isBadRequest());

        List<MNpcCard> mNpcCardList = mNpcCardRepository.findAll();
        assertThat(mNpcCardList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkThumbnailAssetsIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = mNpcCardRepository.findAll().size();
        // set the field null
        mNpcCard.setThumbnailAssetsId(null);

        // Create the MNpcCard, which fails.
        MNpcCardDTO mNpcCardDTO = mNpcCardMapper.toDto(mNpcCard);

        restMNpcCardMockMvc.perform(post("/api/m-npc-cards")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mNpcCardDTO)))
            .andExpect(status().isBadRequest());

        List<MNpcCard> mNpcCardList = mNpcCardRepository.findAll();
        assertThat(mNpcCardList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkCardIllustAssetsIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = mNpcCardRepository.findAll().size();
        // set the field null
        mNpcCard.setCardIllustAssetsId(null);

        // Create the MNpcCard, which fails.
        MNpcCardDTO mNpcCardDTO = mNpcCardMapper.toDto(mNpcCard);

        restMNpcCardMockMvc.perform(post("/api/m-npc-cards")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mNpcCardDTO)))
            .andExpect(status().isBadRequest());

        List<MNpcCard> mNpcCardList = mNpcCardRepository.findAll();
        assertThat(mNpcCardList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkPlayableAssetsIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = mNpcCardRepository.findAll().size();
        // set the field null
        mNpcCard.setPlayableAssetsId(null);

        // Create the MNpcCard, which fails.
        MNpcCardDTO mNpcCardDTO = mNpcCardMapper.toDto(mNpcCard);

        restMNpcCardMockMvc.perform(post("/api/m-npc-cards")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mNpcCardDTO)))
            .andExpect(status().isBadRequest());

        List<MNpcCard> mNpcCardList = mNpcCardRepository.findAll();
        assertThat(mNpcCardList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkTeamEffectLevelIsRequired() throws Exception {
        int databaseSizeBeforeTest = mNpcCardRepository.findAll().size();
        // set the field null
        mNpcCard.setTeamEffectLevel(null);

        // Create the MNpcCard, which fails.
        MNpcCardDTO mNpcCardDTO = mNpcCardMapper.toDto(mNpcCard);

        restMNpcCardMockMvc.perform(post("/api/m-npc-cards")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mNpcCardDTO)))
            .andExpect(status().isBadRequest());

        List<MNpcCard> mNpcCardList = mNpcCardRepository.findAll();
        assertThat(mNpcCardList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkStaminaIsRequired() throws Exception {
        int databaseSizeBeforeTest = mNpcCardRepository.findAll().size();
        // set the field null
        mNpcCard.setStamina(null);

        // Create the MNpcCard, which fails.
        MNpcCardDTO mNpcCardDTO = mNpcCardMapper.toDto(mNpcCard);

        restMNpcCardMockMvc.perform(post("/api/m-npc-cards")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mNpcCardDTO)))
            .andExpect(status().isBadRequest());

        List<MNpcCard> mNpcCardList = mNpcCardRepository.findAll();
        assertThat(mNpcCardList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkDribbleIsRequired() throws Exception {
        int databaseSizeBeforeTest = mNpcCardRepository.findAll().size();
        // set the field null
        mNpcCard.setDribble(null);

        // Create the MNpcCard, which fails.
        MNpcCardDTO mNpcCardDTO = mNpcCardMapper.toDto(mNpcCard);

        restMNpcCardMockMvc.perform(post("/api/m-npc-cards")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mNpcCardDTO)))
            .andExpect(status().isBadRequest());

        List<MNpcCard> mNpcCardList = mNpcCardRepository.findAll();
        assertThat(mNpcCardList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkShootIsRequired() throws Exception {
        int databaseSizeBeforeTest = mNpcCardRepository.findAll().size();
        // set the field null
        mNpcCard.setShoot(null);

        // Create the MNpcCard, which fails.
        MNpcCardDTO mNpcCardDTO = mNpcCardMapper.toDto(mNpcCard);

        restMNpcCardMockMvc.perform(post("/api/m-npc-cards")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mNpcCardDTO)))
            .andExpect(status().isBadRequest());

        List<MNpcCard> mNpcCardList = mNpcCardRepository.findAll();
        assertThat(mNpcCardList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkBallPassIsRequired() throws Exception {
        int databaseSizeBeforeTest = mNpcCardRepository.findAll().size();
        // set the field null
        mNpcCard.setBallPass(null);

        // Create the MNpcCard, which fails.
        MNpcCardDTO mNpcCardDTO = mNpcCardMapper.toDto(mNpcCard);

        restMNpcCardMockMvc.perform(post("/api/m-npc-cards")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mNpcCardDTO)))
            .andExpect(status().isBadRequest());

        List<MNpcCard> mNpcCardList = mNpcCardRepository.findAll();
        assertThat(mNpcCardList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkTackleIsRequired() throws Exception {
        int databaseSizeBeforeTest = mNpcCardRepository.findAll().size();
        // set the field null
        mNpcCard.setTackle(null);

        // Create the MNpcCard, which fails.
        MNpcCardDTO mNpcCardDTO = mNpcCardMapper.toDto(mNpcCard);

        restMNpcCardMockMvc.perform(post("/api/m-npc-cards")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mNpcCardDTO)))
            .andExpect(status().isBadRequest());

        List<MNpcCard> mNpcCardList = mNpcCardRepository.findAll();
        assertThat(mNpcCardList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkBlockIsRequired() throws Exception {
        int databaseSizeBeforeTest = mNpcCardRepository.findAll().size();
        // set the field null
        mNpcCard.setBlock(null);

        // Create the MNpcCard, which fails.
        MNpcCardDTO mNpcCardDTO = mNpcCardMapper.toDto(mNpcCard);

        restMNpcCardMockMvc.perform(post("/api/m-npc-cards")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mNpcCardDTO)))
            .andExpect(status().isBadRequest());

        List<MNpcCard> mNpcCardList = mNpcCardRepository.findAll();
        assertThat(mNpcCardList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkInterceptIsRequired() throws Exception {
        int databaseSizeBeforeTest = mNpcCardRepository.findAll().size();
        // set the field null
        mNpcCard.setIntercept(null);

        // Create the MNpcCard, which fails.
        MNpcCardDTO mNpcCardDTO = mNpcCardMapper.toDto(mNpcCard);

        restMNpcCardMockMvc.perform(post("/api/m-npc-cards")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mNpcCardDTO)))
            .andExpect(status().isBadRequest());

        List<MNpcCard> mNpcCardList = mNpcCardRepository.findAll();
        assertThat(mNpcCardList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkSpeedIsRequired() throws Exception {
        int databaseSizeBeforeTest = mNpcCardRepository.findAll().size();
        // set the field null
        mNpcCard.setSpeed(null);

        // Create the MNpcCard, which fails.
        MNpcCardDTO mNpcCardDTO = mNpcCardMapper.toDto(mNpcCard);

        restMNpcCardMockMvc.perform(post("/api/m-npc-cards")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mNpcCardDTO)))
            .andExpect(status().isBadRequest());

        List<MNpcCard> mNpcCardList = mNpcCardRepository.findAll();
        assertThat(mNpcCardList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkPowerIsRequired() throws Exception {
        int databaseSizeBeforeTest = mNpcCardRepository.findAll().size();
        // set the field null
        mNpcCard.setPower(null);

        // Create the MNpcCard, which fails.
        MNpcCardDTO mNpcCardDTO = mNpcCardMapper.toDto(mNpcCard);

        restMNpcCardMockMvc.perform(post("/api/m-npc-cards")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mNpcCardDTO)))
            .andExpect(status().isBadRequest());

        List<MNpcCard> mNpcCardList = mNpcCardRepository.findAll();
        assertThat(mNpcCardList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkTechniqueIsRequired() throws Exception {
        int databaseSizeBeforeTest = mNpcCardRepository.findAll().size();
        // set the field null
        mNpcCard.setTechnique(null);

        // Create the MNpcCard, which fails.
        MNpcCardDTO mNpcCardDTO = mNpcCardMapper.toDto(mNpcCard);

        restMNpcCardMockMvc.perform(post("/api/m-npc-cards")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mNpcCardDTO)))
            .andExpect(status().isBadRequest());

        List<MNpcCard> mNpcCardList = mNpcCardRepository.findAll();
        assertThat(mNpcCardList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkPunchingIsRequired() throws Exception {
        int databaseSizeBeforeTest = mNpcCardRepository.findAll().size();
        // set the field null
        mNpcCard.setPunching(null);

        // Create the MNpcCard, which fails.
        MNpcCardDTO mNpcCardDTO = mNpcCardMapper.toDto(mNpcCard);

        restMNpcCardMockMvc.perform(post("/api/m-npc-cards")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mNpcCardDTO)))
            .andExpect(status().isBadRequest());

        List<MNpcCard> mNpcCardList = mNpcCardRepository.findAll();
        assertThat(mNpcCardList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkCatchingIsRequired() throws Exception {
        int databaseSizeBeforeTest = mNpcCardRepository.findAll().size();
        // set the field null
        mNpcCard.setCatching(null);

        // Create the MNpcCard, which fails.
        MNpcCardDTO mNpcCardDTO = mNpcCardMapper.toDto(mNpcCard);

        restMNpcCardMockMvc.perform(post("/api/m-npc-cards")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mNpcCardDTO)))
            .andExpect(status().isBadRequest());

        List<MNpcCard> mNpcCardList = mNpcCardRepository.findAll();
        assertThat(mNpcCardList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkHighBallBonusIsRequired() throws Exception {
        int databaseSizeBeforeTest = mNpcCardRepository.findAll().size();
        // set the field null
        mNpcCard.setHighBallBonus(null);

        // Create the MNpcCard, which fails.
        MNpcCardDTO mNpcCardDTO = mNpcCardMapper.toDto(mNpcCard);

        restMNpcCardMockMvc.perform(post("/api/m-npc-cards")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mNpcCardDTO)))
            .andExpect(status().isBadRequest());

        List<MNpcCard> mNpcCardList = mNpcCardRepository.findAll();
        assertThat(mNpcCardList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkLowBallBonusIsRequired() throws Exception {
        int databaseSizeBeforeTest = mNpcCardRepository.findAll().size();
        // set the field null
        mNpcCard.setLowBallBonus(null);

        // Create the MNpcCard, which fails.
        MNpcCardDTO mNpcCardDTO = mNpcCardMapper.toDto(mNpcCard);

        restMNpcCardMockMvc.perform(post("/api/m-npc-cards")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mNpcCardDTO)))
            .andExpect(status().isBadRequest());

        List<MNpcCard> mNpcCardList = mNpcCardRepository.findAll();
        assertThat(mNpcCardList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkPersonalityIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = mNpcCardRepository.findAll().size();
        // set the field null
        mNpcCard.setPersonalityId(null);

        // Create the MNpcCard, which fails.
        MNpcCardDTO mNpcCardDTO = mNpcCardMapper.toDto(mNpcCard);

        restMNpcCardMockMvc.perform(post("/api/m-npc-cards")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mNpcCardDTO)))
            .andExpect(status().isBadRequest());

        List<MNpcCard> mNpcCardList = mNpcCardRepository.findAll();
        assertThat(mNpcCardList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkUniformNoIsRequired() throws Exception {
        int databaseSizeBeforeTest = mNpcCardRepository.findAll().size();
        // set the field null
        mNpcCard.setUniformNo(null);

        // Create the MNpcCard, which fails.
        MNpcCardDTO mNpcCardDTO = mNpcCardMapper.toDto(mNpcCard);

        restMNpcCardMockMvc.perform(post("/api/m-npc-cards")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mNpcCardDTO)))
            .andExpect(status().isBadRequest());

        List<MNpcCard> mNpcCardList = mNpcCardRepository.findAll();
        assertThat(mNpcCardList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkLevelGroupIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = mNpcCardRepository.findAll().size();
        // set the field null
        mNpcCard.setLevelGroupId(null);

        // Create the MNpcCard, which fails.
        MNpcCardDTO mNpcCardDTO = mNpcCardMapper.toDto(mNpcCard);

        restMNpcCardMockMvc.perform(post("/api/m-npc-cards")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mNpcCardDTO)))
            .andExpect(status().isBadRequest());

        List<MNpcCard> mNpcCardList = mNpcCardRepository.findAll();
        assertThat(mNpcCardList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllMNpcCards() throws Exception {
        // Initialize the database
        mNpcCardRepository.saveAndFlush(mNpcCard);

        // Get all the mNpcCardList
        restMNpcCardMockMvc.perform(get("/api/m-npc-cards?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(mNpcCard.getId().intValue())))
            .andExpect(jsonPath("$.[*].characterId").value(hasItem(DEFAULT_CHARACTER_ID)))
            .andExpect(jsonPath("$.[*].shortName").value(hasItem(DEFAULT_SHORT_NAME.toString())))
            .andExpect(jsonPath("$.[*].nickName").value(hasItem(DEFAULT_NICK_NAME.toString())))
            .andExpect(jsonPath("$.[*].teamId").value(hasItem(DEFAULT_TEAM_ID)))
            .andExpect(jsonPath("$.[*].nationalityId").value(hasItem(DEFAULT_NATIONALITY_ID)))
            .andExpect(jsonPath("$.[*].rarity").value(hasItem(DEFAULT_RARITY)))
            .andExpect(jsonPath("$.[*].attribute").value(hasItem(DEFAULT_ATTRIBUTE)))
            .andExpect(jsonPath("$.[*].modelId").value(hasItem(DEFAULT_MODEL_ID)))
            .andExpect(jsonPath("$.[*].level").value(hasItem(DEFAULT_LEVEL)))
            .andExpect(jsonPath("$.[*].thumbnailAssetsId").value(hasItem(DEFAULT_THUMBNAIL_ASSETS_ID)))
            .andExpect(jsonPath("$.[*].cardIllustAssetsId").value(hasItem(DEFAULT_CARD_ILLUST_ASSETS_ID)))
            .andExpect(jsonPath("$.[*].playableAssetsId").value(hasItem(DEFAULT_PLAYABLE_ASSETS_ID)))
            .andExpect(jsonPath("$.[*].teamEffectId").value(hasItem(DEFAULT_TEAM_EFFECT_ID)))
            .andExpect(jsonPath("$.[*].teamEffectLevel").value(hasItem(DEFAULT_TEAM_EFFECT_LEVEL)))
            .andExpect(jsonPath("$.[*].triggerEffectId").value(hasItem(DEFAULT_TRIGGER_EFFECT_ID)))
            .andExpect(jsonPath("$.[*].action1Id").value(hasItem(DEFAULT_ACTION_1_ID)))
            .andExpect(jsonPath("$.[*].action1Level").value(hasItem(DEFAULT_ACTION_1_LEVEL)))
            .andExpect(jsonPath("$.[*].action2Id").value(hasItem(DEFAULT_ACTION_2_ID)))
            .andExpect(jsonPath("$.[*].action2Level").value(hasItem(DEFAULT_ACTION_2_LEVEL)))
            .andExpect(jsonPath("$.[*].action3Id").value(hasItem(DEFAULT_ACTION_3_ID)))
            .andExpect(jsonPath("$.[*].action3Level").value(hasItem(DEFAULT_ACTION_3_LEVEL)))
            .andExpect(jsonPath("$.[*].action4Id").value(hasItem(DEFAULT_ACTION_4_ID)))
            .andExpect(jsonPath("$.[*].action4Level").value(hasItem(DEFAULT_ACTION_4_LEVEL)))
            .andExpect(jsonPath("$.[*].action5Id").value(hasItem(DEFAULT_ACTION_5_ID)))
            .andExpect(jsonPath("$.[*].action5Level").value(hasItem(DEFAULT_ACTION_5_LEVEL)))
            .andExpect(jsonPath("$.[*].stamina").value(hasItem(DEFAULT_STAMINA)))
            .andExpect(jsonPath("$.[*].dribble").value(hasItem(DEFAULT_DRIBBLE)))
            .andExpect(jsonPath("$.[*].shoot").value(hasItem(DEFAULT_SHOOT)))
            .andExpect(jsonPath("$.[*].ballPass").value(hasItem(DEFAULT_BALL_PASS)))
            .andExpect(jsonPath("$.[*].tackle").value(hasItem(DEFAULT_TACKLE)))
            .andExpect(jsonPath("$.[*].block").value(hasItem(DEFAULT_BLOCK)))
            .andExpect(jsonPath("$.[*].intercept").value(hasItem(DEFAULT_INTERCEPT)))
            .andExpect(jsonPath("$.[*].speed").value(hasItem(DEFAULT_SPEED)))
            .andExpect(jsonPath("$.[*].power").value(hasItem(DEFAULT_POWER)))
            .andExpect(jsonPath("$.[*].technique").value(hasItem(DEFAULT_TECHNIQUE)))
            .andExpect(jsonPath("$.[*].punching").value(hasItem(DEFAULT_PUNCHING)))
            .andExpect(jsonPath("$.[*].catching").value(hasItem(DEFAULT_CATCHING)))
            .andExpect(jsonPath("$.[*].highBallBonus").value(hasItem(DEFAULT_HIGH_BALL_BONUS)))
            .andExpect(jsonPath("$.[*].lowBallBonus").value(hasItem(DEFAULT_LOW_BALL_BONUS)))
            .andExpect(jsonPath("$.[*].personalityId").value(hasItem(DEFAULT_PERSONALITY_ID)))
            .andExpect(jsonPath("$.[*].uniformNo").value(hasItem(DEFAULT_UNIFORM_NO)))
            .andExpect(jsonPath("$.[*].levelGroupId").value(hasItem(DEFAULT_LEVEL_GROUP_ID)));
    }
    
    @Test
    @Transactional
    public void getMNpcCard() throws Exception {
        // Initialize the database
        mNpcCardRepository.saveAndFlush(mNpcCard);

        // Get the mNpcCard
        restMNpcCardMockMvc.perform(get("/api/m-npc-cards/{id}", mNpcCard.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(mNpcCard.getId().intValue()))
            .andExpect(jsonPath("$.characterId").value(DEFAULT_CHARACTER_ID))
            .andExpect(jsonPath("$.shortName").value(DEFAULT_SHORT_NAME.toString()))
            .andExpect(jsonPath("$.nickName").value(DEFAULT_NICK_NAME.toString()))
            .andExpect(jsonPath("$.teamId").value(DEFAULT_TEAM_ID))
            .andExpect(jsonPath("$.nationalityId").value(DEFAULT_NATIONALITY_ID))
            .andExpect(jsonPath("$.rarity").value(DEFAULT_RARITY))
            .andExpect(jsonPath("$.attribute").value(DEFAULT_ATTRIBUTE))
            .andExpect(jsonPath("$.modelId").value(DEFAULT_MODEL_ID))
            .andExpect(jsonPath("$.level").value(DEFAULT_LEVEL))
            .andExpect(jsonPath("$.thumbnailAssetsId").value(DEFAULT_THUMBNAIL_ASSETS_ID))
            .andExpect(jsonPath("$.cardIllustAssetsId").value(DEFAULT_CARD_ILLUST_ASSETS_ID))
            .andExpect(jsonPath("$.playableAssetsId").value(DEFAULT_PLAYABLE_ASSETS_ID))
            .andExpect(jsonPath("$.teamEffectId").value(DEFAULT_TEAM_EFFECT_ID))
            .andExpect(jsonPath("$.teamEffectLevel").value(DEFAULT_TEAM_EFFECT_LEVEL))
            .andExpect(jsonPath("$.triggerEffectId").value(DEFAULT_TRIGGER_EFFECT_ID))
            .andExpect(jsonPath("$.action1Id").value(DEFAULT_ACTION_1_ID))
            .andExpect(jsonPath("$.action1Level").value(DEFAULT_ACTION_1_LEVEL))
            .andExpect(jsonPath("$.action2Id").value(DEFAULT_ACTION_2_ID))
            .andExpect(jsonPath("$.action2Level").value(DEFAULT_ACTION_2_LEVEL))
            .andExpect(jsonPath("$.action3Id").value(DEFAULT_ACTION_3_ID))
            .andExpect(jsonPath("$.action3Level").value(DEFAULT_ACTION_3_LEVEL))
            .andExpect(jsonPath("$.action4Id").value(DEFAULT_ACTION_4_ID))
            .andExpect(jsonPath("$.action4Level").value(DEFAULT_ACTION_4_LEVEL))
            .andExpect(jsonPath("$.action5Id").value(DEFAULT_ACTION_5_ID))
            .andExpect(jsonPath("$.action5Level").value(DEFAULT_ACTION_5_LEVEL))
            .andExpect(jsonPath("$.stamina").value(DEFAULT_STAMINA))
            .andExpect(jsonPath("$.dribble").value(DEFAULT_DRIBBLE))
            .andExpect(jsonPath("$.shoot").value(DEFAULT_SHOOT))
            .andExpect(jsonPath("$.ballPass").value(DEFAULT_BALL_PASS))
            .andExpect(jsonPath("$.tackle").value(DEFAULT_TACKLE))
            .andExpect(jsonPath("$.block").value(DEFAULT_BLOCK))
            .andExpect(jsonPath("$.intercept").value(DEFAULT_INTERCEPT))
            .andExpect(jsonPath("$.speed").value(DEFAULT_SPEED))
            .andExpect(jsonPath("$.power").value(DEFAULT_POWER))
            .andExpect(jsonPath("$.technique").value(DEFAULT_TECHNIQUE))
            .andExpect(jsonPath("$.punching").value(DEFAULT_PUNCHING))
            .andExpect(jsonPath("$.catching").value(DEFAULT_CATCHING))
            .andExpect(jsonPath("$.highBallBonus").value(DEFAULT_HIGH_BALL_BONUS))
            .andExpect(jsonPath("$.lowBallBonus").value(DEFAULT_LOW_BALL_BONUS))
            .andExpect(jsonPath("$.personalityId").value(DEFAULT_PERSONALITY_ID))
            .andExpect(jsonPath("$.uniformNo").value(DEFAULT_UNIFORM_NO))
            .andExpect(jsonPath("$.levelGroupId").value(DEFAULT_LEVEL_GROUP_ID));
    }

    @Test
    @Transactional
    public void getAllMNpcCardsByCharacterIdIsEqualToSomething() throws Exception {
        // Initialize the database
        mNpcCardRepository.saveAndFlush(mNpcCard);

        // Get all the mNpcCardList where characterId equals to DEFAULT_CHARACTER_ID
        defaultMNpcCardShouldBeFound("characterId.equals=" + DEFAULT_CHARACTER_ID);

        // Get all the mNpcCardList where characterId equals to UPDATED_CHARACTER_ID
        defaultMNpcCardShouldNotBeFound("characterId.equals=" + UPDATED_CHARACTER_ID);
    }

    @Test
    @Transactional
    public void getAllMNpcCardsByCharacterIdIsInShouldWork() throws Exception {
        // Initialize the database
        mNpcCardRepository.saveAndFlush(mNpcCard);

        // Get all the mNpcCardList where characterId in DEFAULT_CHARACTER_ID or UPDATED_CHARACTER_ID
        defaultMNpcCardShouldBeFound("characterId.in=" + DEFAULT_CHARACTER_ID + "," + UPDATED_CHARACTER_ID);

        // Get all the mNpcCardList where characterId equals to UPDATED_CHARACTER_ID
        defaultMNpcCardShouldNotBeFound("characterId.in=" + UPDATED_CHARACTER_ID);
    }

    @Test
    @Transactional
    public void getAllMNpcCardsByCharacterIdIsNullOrNotNull() throws Exception {
        // Initialize the database
        mNpcCardRepository.saveAndFlush(mNpcCard);

        // Get all the mNpcCardList where characterId is not null
        defaultMNpcCardShouldBeFound("characterId.specified=true");

        // Get all the mNpcCardList where characterId is null
        defaultMNpcCardShouldNotBeFound("characterId.specified=false");
    }

    @Test
    @Transactional
    public void getAllMNpcCardsByCharacterIdIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mNpcCardRepository.saveAndFlush(mNpcCard);

        // Get all the mNpcCardList where characterId greater than or equals to DEFAULT_CHARACTER_ID
        defaultMNpcCardShouldBeFound("characterId.greaterOrEqualThan=" + DEFAULT_CHARACTER_ID);

        // Get all the mNpcCardList where characterId greater than or equals to UPDATED_CHARACTER_ID
        defaultMNpcCardShouldNotBeFound("characterId.greaterOrEqualThan=" + UPDATED_CHARACTER_ID);
    }

    @Test
    @Transactional
    public void getAllMNpcCardsByCharacterIdIsLessThanSomething() throws Exception {
        // Initialize the database
        mNpcCardRepository.saveAndFlush(mNpcCard);

        // Get all the mNpcCardList where characterId less than or equals to DEFAULT_CHARACTER_ID
        defaultMNpcCardShouldNotBeFound("characterId.lessThan=" + DEFAULT_CHARACTER_ID);

        // Get all the mNpcCardList where characterId less than or equals to UPDATED_CHARACTER_ID
        defaultMNpcCardShouldBeFound("characterId.lessThan=" + UPDATED_CHARACTER_ID);
    }


    @Test
    @Transactional
    public void getAllMNpcCardsByTeamIdIsEqualToSomething() throws Exception {
        // Initialize the database
        mNpcCardRepository.saveAndFlush(mNpcCard);

        // Get all the mNpcCardList where teamId equals to DEFAULT_TEAM_ID
        defaultMNpcCardShouldBeFound("teamId.equals=" + DEFAULT_TEAM_ID);

        // Get all the mNpcCardList where teamId equals to UPDATED_TEAM_ID
        defaultMNpcCardShouldNotBeFound("teamId.equals=" + UPDATED_TEAM_ID);
    }

    @Test
    @Transactional
    public void getAllMNpcCardsByTeamIdIsInShouldWork() throws Exception {
        // Initialize the database
        mNpcCardRepository.saveAndFlush(mNpcCard);

        // Get all the mNpcCardList where teamId in DEFAULT_TEAM_ID or UPDATED_TEAM_ID
        defaultMNpcCardShouldBeFound("teamId.in=" + DEFAULT_TEAM_ID + "," + UPDATED_TEAM_ID);

        // Get all the mNpcCardList where teamId equals to UPDATED_TEAM_ID
        defaultMNpcCardShouldNotBeFound("teamId.in=" + UPDATED_TEAM_ID);
    }

    @Test
    @Transactional
    public void getAllMNpcCardsByTeamIdIsNullOrNotNull() throws Exception {
        // Initialize the database
        mNpcCardRepository.saveAndFlush(mNpcCard);

        // Get all the mNpcCardList where teamId is not null
        defaultMNpcCardShouldBeFound("teamId.specified=true");

        // Get all the mNpcCardList where teamId is null
        defaultMNpcCardShouldNotBeFound("teamId.specified=false");
    }

    @Test
    @Transactional
    public void getAllMNpcCardsByTeamIdIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mNpcCardRepository.saveAndFlush(mNpcCard);

        // Get all the mNpcCardList where teamId greater than or equals to DEFAULT_TEAM_ID
        defaultMNpcCardShouldBeFound("teamId.greaterOrEqualThan=" + DEFAULT_TEAM_ID);

        // Get all the mNpcCardList where teamId greater than or equals to UPDATED_TEAM_ID
        defaultMNpcCardShouldNotBeFound("teamId.greaterOrEqualThan=" + UPDATED_TEAM_ID);
    }

    @Test
    @Transactional
    public void getAllMNpcCardsByTeamIdIsLessThanSomething() throws Exception {
        // Initialize the database
        mNpcCardRepository.saveAndFlush(mNpcCard);

        // Get all the mNpcCardList where teamId less than or equals to DEFAULT_TEAM_ID
        defaultMNpcCardShouldNotBeFound("teamId.lessThan=" + DEFAULT_TEAM_ID);

        // Get all the mNpcCardList where teamId less than or equals to UPDATED_TEAM_ID
        defaultMNpcCardShouldBeFound("teamId.lessThan=" + UPDATED_TEAM_ID);
    }


    @Test
    @Transactional
    public void getAllMNpcCardsByNationalityIdIsEqualToSomething() throws Exception {
        // Initialize the database
        mNpcCardRepository.saveAndFlush(mNpcCard);

        // Get all the mNpcCardList where nationalityId equals to DEFAULT_NATIONALITY_ID
        defaultMNpcCardShouldBeFound("nationalityId.equals=" + DEFAULT_NATIONALITY_ID);

        // Get all the mNpcCardList where nationalityId equals to UPDATED_NATIONALITY_ID
        defaultMNpcCardShouldNotBeFound("nationalityId.equals=" + UPDATED_NATIONALITY_ID);
    }

    @Test
    @Transactional
    public void getAllMNpcCardsByNationalityIdIsInShouldWork() throws Exception {
        // Initialize the database
        mNpcCardRepository.saveAndFlush(mNpcCard);

        // Get all the mNpcCardList where nationalityId in DEFAULT_NATIONALITY_ID or UPDATED_NATIONALITY_ID
        defaultMNpcCardShouldBeFound("nationalityId.in=" + DEFAULT_NATIONALITY_ID + "," + UPDATED_NATIONALITY_ID);

        // Get all the mNpcCardList where nationalityId equals to UPDATED_NATIONALITY_ID
        defaultMNpcCardShouldNotBeFound("nationalityId.in=" + UPDATED_NATIONALITY_ID);
    }

    @Test
    @Transactional
    public void getAllMNpcCardsByNationalityIdIsNullOrNotNull() throws Exception {
        // Initialize the database
        mNpcCardRepository.saveAndFlush(mNpcCard);

        // Get all the mNpcCardList where nationalityId is not null
        defaultMNpcCardShouldBeFound("nationalityId.specified=true");

        // Get all the mNpcCardList where nationalityId is null
        defaultMNpcCardShouldNotBeFound("nationalityId.specified=false");
    }

    @Test
    @Transactional
    public void getAllMNpcCardsByNationalityIdIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mNpcCardRepository.saveAndFlush(mNpcCard);

        // Get all the mNpcCardList where nationalityId greater than or equals to DEFAULT_NATIONALITY_ID
        defaultMNpcCardShouldBeFound("nationalityId.greaterOrEqualThan=" + DEFAULT_NATIONALITY_ID);

        // Get all the mNpcCardList where nationalityId greater than or equals to UPDATED_NATIONALITY_ID
        defaultMNpcCardShouldNotBeFound("nationalityId.greaterOrEqualThan=" + UPDATED_NATIONALITY_ID);
    }

    @Test
    @Transactional
    public void getAllMNpcCardsByNationalityIdIsLessThanSomething() throws Exception {
        // Initialize the database
        mNpcCardRepository.saveAndFlush(mNpcCard);

        // Get all the mNpcCardList where nationalityId less than or equals to DEFAULT_NATIONALITY_ID
        defaultMNpcCardShouldNotBeFound("nationalityId.lessThan=" + DEFAULT_NATIONALITY_ID);

        // Get all the mNpcCardList where nationalityId less than or equals to UPDATED_NATIONALITY_ID
        defaultMNpcCardShouldBeFound("nationalityId.lessThan=" + UPDATED_NATIONALITY_ID);
    }


    @Test
    @Transactional
    public void getAllMNpcCardsByRarityIsEqualToSomething() throws Exception {
        // Initialize the database
        mNpcCardRepository.saveAndFlush(mNpcCard);

        // Get all the mNpcCardList where rarity equals to DEFAULT_RARITY
        defaultMNpcCardShouldBeFound("rarity.equals=" + DEFAULT_RARITY);

        // Get all the mNpcCardList where rarity equals to UPDATED_RARITY
        defaultMNpcCardShouldNotBeFound("rarity.equals=" + UPDATED_RARITY);
    }

    @Test
    @Transactional
    public void getAllMNpcCardsByRarityIsInShouldWork() throws Exception {
        // Initialize the database
        mNpcCardRepository.saveAndFlush(mNpcCard);

        // Get all the mNpcCardList where rarity in DEFAULT_RARITY or UPDATED_RARITY
        defaultMNpcCardShouldBeFound("rarity.in=" + DEFAULT_RARITY + "," + UPDATED_RARITY);

        // Get all the mNpcCardList where rarity equals to UPDATED_RARITY
        defaultMNpcCardShouldNotBeFound("rarity.in=" + UPDATED_RARITY);
    }

    @Test
    @Transactional
    public void getAllMNpcCardsByRarityIsNullOrNotNull() throws Exception {
        // Initialize the database
        mNpcCardRepository.saveAndFlush(mNpcCard);

        // Get all the mNpcCardList where rarity is not null
        defaultMNpcCardShouldBeFound("rarity.specified=true");

        // Get all the mNpcCardList where rarity is null
        defaultMNpcCardShouldNotBeFound("rarity.specified=false");
    }

    @Test
    @Transactional
    public void getAllMNpcCardsByRarityIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mNpcCardRepository.saveAndFlush(mNpcCard);

        // Get all the mNpcCardList where rarity greater than or equals to DEFAULT_RARITY
        defaultMNpcCardShouldBeFound("rarity.greaterOrEqualThan=" + DEFAULT_RARITY);

        // Get all the mNpcCardList where rarity greater than or equals to UPDATED_RARITY
        defaultMNpcCardShouldNotBeFound("rarity.greaterOrEqualThan=" + UPDATED_RARITY);
    }

    @Test
    @Transactional
    public void getAllMNpcCardsByRarityIsLessThanSomething() throws Exception {
        // Initialize the database
        mNpcCardRepository.saveAndFlush(mNpcCard);

        // Get all the mNpcCardList where rarity less than or equals to DEFAULT_RARITY
        defaultMNpcCardShouldNotBeFound("rarity.lessThan=" + DEFAULT_RARITY);

        // Get all the mNpcCardList where rarity less than or equals to UPDATED_RARITY
        defaultMNpcCardShouldBeFound("rarity.lessThan=" + UPDATED_RARITY);
    }


    @Test
    @Transactional
    public void getAllMNpcCardsByAttributeIsEqualToSomething() throws Exception {
        // Initialize the database
        mNpcCardRepository.saveAndFlush(mNpcCard);

        // Get all the mNpcCardList where attribute equals to DEFAULT_ATTRIBUTE
        defaultMNpcCardShouldBeFound("attribute.equals=" + DEFAULT_ATTRIBUTE);

        // Get all the mNpcCardList where attribute equals to UPDATED_ATTRIBUTE
        defaultMNpcCardShouldNotBeFound("attribute.equals=" + UPDATED_ATTRIBUTE);
    }

    @Test
    @Transactional
    public void getAllMNpcCardsByAttributeIsInShouldWork() throws Exception {
        // Initialize the database
        mNpcCardRepository.saveAndFlush(mNpcCard);

        // Get all the mNpcCardList where attribute in DEFAULT_ATTRIBUTE or UPDATED_ATTRIBUTE
        defaultMNpcCardShouldBeFound("attribute.in=" + DEFAULT_ATTRIBUTE + "," + UPDATED_ATTRIBUTE);

        // Get all the mNpcCardList where attribute equals to UPDATED_ATTRIBUTE
        defaultMNpcCardShouldNotBeFound("attribute.in=" + UPDATED_ATTRIBUTE);
    }

    @Test
    @Transactional
    public void getAllMNpcCardsByAttributeIsNullOrNotNull() throws Exception {
        // Initialize the database
        mNpcCardRepository.saveAndFlush(mNpcCard);

        // Get all the mNpcCardList where attribute is not null
        defaultMNpcCardShouldBeFound("attribute.specified=true");

        // Get all the mNpcCardList where attribute is null
        defaultMNpcCardShouldNotBeFound("attribute.specified=false");
    }

    @Test
    @Transactional
    public void getAllMNpcCardsByAttributeIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mNpcCardRepository.saveAndFlush(mNpcCard);

        // Get all the mNpcCardList where attribute greater than or equals to DEFAULT_ATTRIBUTE
        defaultMNpcCardShouldBeFound("attribute.greaterOrEqualThan=" + DEFAULT_ATTRIBUTE);

        // Get all the mNpcCardList where attribute greater than or equals to UPDATED_ATTRIBUTE
        defaultMNpcCardShouldNotBeFound("attribute.greaterOrEqualThan=" + UPDATED_ATTRIBUTE);
    }

    @Test
    @Transactional
    public void getAllMNpcCardsByAttributeIsLessThanSomething() throws Exception {
        // Initialize the database
        mNpcCardRepository.saveAndFlush(mNpcCard);

        // Get all the mNpcCardList where attribute less than or equals to DEFAULT_ATTRIBUTE
        defaultMNpcCardShouldNotBeFound("attribute.lessThan=" + DEFAULT_ATTRIBUTE);

        // Get all the mNpcCardList where attribute less than or equals to UPDATED_ATTRIBUTE
        defaultMNpcCardShouldBeFound("attribute.lessThan=" + UPDATED_ATTRIBUTE);
    }


    @Test
    @Transactional
    public void getAllMNpcCardsByModelIdIsEqualToSomething() throws Exception {
        // Initialize the database
        mNpcCardRepository.saveAndFlush(mNpcCard);

        // Get all the mNpcCardList where modelId equals to DEFAULT_MODEL_ID
        defaultMNpcCardShouldBeFound("modelId.equals=" + DEFAULT_MODEL_ID);

        // Get all the mNpcCardList where modelId equals to UPDATED_MODEL_ID
        defaultMNpcCardShouldNotBeFound("modelId.equals=" + UPDATED_MODEL_ID);
    }

    @Test
    @Transactional
    public void getAllMNpcCardsByModelIdIsInShouldWork() throws Exception {
        // Initialize the database
        mNpcCardRepository.saveAndFlush(mNpcCard);

        // Get all the mNpcCardList where modelId in DEFAULT_MODEL_ID or UPDATED_MODEL_ID
        defaultMNpcCardShouldBeFound("modelId.in=" + DEFAULT_MODEL_ID + "," + UPDATED_MODEL_ID);

        // Get all the mNpcCardList where modelId equals to UPDATED_MODEL_ID
        defaultMNpcCardShouldNotBeFound("modelId.in=" + UPDATED_MODEL_ID);
    }

    @Test
    @Transactional
    public void getAllMNpcCardsByModelIdIsNullOrNotNull() throws Exception {
        // Initialize the database
        mNpcCardRepository.saveAndFlush(mNpcCard);

        // Get all the mNpcCardList where modelId is not null
        defaultMNpcCardShouldBeFound("modelId.specified=true");

        // Get all the mNpcCardList where modelId is null
        defaultMNpcCardShouldNotBeFound("modelId.specified=false");
    }

    @Test
    @Transactional
    public void getAllMNpcCardsByModelIdIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mNpcCardRepository.saveAndFlush(mNpcCard);

        // Get all the mNpcCardList where modelId greater than or equals to DEFAULT_MODEL_ID
        defaultMNpcCardShouldBeFound("modelId.greaterOrEqualThan=" + DEFAULT_MODEL_ID);

        // Get all the mNpcCardList where modelId greater than or equals to UPDATED_MODEL_ID
        defaultMNpcCardShouldNotBeFound("modelId.greaterOrEqualThan=" + UPDATED_MODEL_ID);
    }

    @Test
    @Transactional
    public void getAllMNpcCardsByModelIdIsLessThanSomething() throws Exception {
        // Initialize the database
        mNpcCardRepository.saveAndFlush(mNpcCard);

        // Get all the mNpcCardList where modelId less than or equals to DEFAULT_MODEL_ID
        defaultMNpcCardShouldNotBeFound("modelId.lessThan=" + DEFAULT_MODEL_ID);

        // Get all the mNpcCardList where modelId less than or equals to UPDATED_MODEL_ID
        defaultMNpcCardShouldBeFound("modelId.lessThan=" + UPDATED_MODEL_ID);
    }


    @Test
    @Transactional
    public void getAllMNpcCardsByLevelIsEqualToSomething() throws Exception {
        // Initialize the database
        mNpcCardRepository.saveAndFlush(mNpcCard);

        // Get all the mNpcCardList where level equals to DEFAULT_LEVEL
        defaultMNpcCardShouldBeFound("level.equals=" + DEFAULT_LEVEL);

        // Get all the mNpcCardList where level equals to UPDATED_LEVEL
        defaultMNpcCardShouldNotBeFound("level.equals=" + UPDATED_LEVEL);
    }

    @Test
    @Transactional
    public void getAllMNpcCardsByLevelIsInShouldWork() throws Exception {
        // Initialize the database
        mNpcCardRepository.saveAndFlush(mNpcCard);

        // Get all the mNpcCardList where level in DEFAULT_LEVEL or UPDATED_LEVEL
        defaultMNpcCardShouldBeFound("level.in=" + DEFAULT_LEVEL + "," + UPDATED_LEVEL);

        // Get all the mNpcCardList where level equals to UPDATED_LEVEL
        defaultMNpcCardShouldNotBeFound("level.in=" + UPDATED_LEVEL);
    }

    @Test
    @Transactional
    public void getAllMNpcCardsByLevelIsNullOrNotNull() throws Exception {
        // Initialize the database
        mNpcCardRepository.saveAndFlush(mNpcCard);

        // Get all the mNpcCardList where level is not null
        defaultMNpcCardShouldBeFound("level.specified=true");

        // Get all the mNpcCardList where level is null
        defaultMNpcCardShouldNotBeFound("level.specified=false");
    }

    @Test
    @Transactional
    public void getAllMNpcCardsByLevelIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mNpcCardRepository.saveAndFlush(mNpcCard);

        // Get all the mNpcCardList where level greater than or equals to DEFAULT_LEVEL
        defaultMNpcCardShouldBeFound("level.greaterOrEqualThan=" + DEFAULT_LEVEL);

        // Get all the mNpcCardList where level greater than or equals to UPDATED_LEVEL
        defaultMNpcCardShouldNotBeFound("level.greaterOrEqualThan=" + UPDATED_LEVEL);
    }

    @Test
    @Transactional
    public void getAllMNpcCardsByLevelIsLessThanSomething() throws Exception {
        // Initialize the database
        mNpcCardRepository.saveAndFlush(mNpcCard);

        // Get all the mNpcCardList where level less than or equals to DEFAULT_LEVEL
        defaultMNpcCardShouldNotBeFound("level.lessThan=" + DEFAULT_LEVEL);

        // Get all the mNpcCardList where level less than or equals to UPDATED_LEVEL
        defaultMNpcCardShouldBeFound("level.lessThan=" + UPDATED_LEVEL);
    }


    @Test
    @Transactional
    public void getAllMNpcCardsByThumbnailAssetsIdIsEqualToSomething() throws Exception {
        // Initialize the database
        mNpcCardRepository.saveAndFlush(mNpcCard);

        // Get all the mNpcCardList where thumbnailAssetsId equals to DEFAULT_THUMBNAIL_ASSETS_ID
        defaultMNpcCardShouldBeFound("thumbnailAssetsId.equals=" + DEFAULT_THUMBNAIL_ASSETS_ID);

        // Get all the mNpcCardList where thumbnailAssetsId equals to UPDATED_THUMBNAIL_ASSETS_ID
        defaultMNpcCardShouldNotBeFound("thumbnailAssetsId.equals=" + UPDATED_THUMBNAIL_ASSETS_ID);
    }

    @Test
    @Transactional
    public void getAllMNpcCardsByThumbnailAssetsIdIsInShouldWork() throws Exception {
        // Initialize the database
        mNpcCardRepository.saveAndFlush(mNpcCard);

        // Get all the mNpcCardList where thumbnailAssetsId in DEFAULT_THUMBNAIL_ASSETS_ID or UPDATED_THUMBNAIL_ASSETS_ID
        defaultMNpcCardShouldBeFound("thumbnailAssetsId.in=" + DEFAULT_THUMBNAIL_ASSETS_ID + "," + UPDATED_THUMBNAIL_ASSETS_ID);

        // Get all the mNpcCardList where thumbnailAssetsId equals to UPDATED_THUMBNAIL_ASSETS_ID
        defaultMNpcCardShouldNotBeFound("thumbnailAssetsId.in=" + UPDATED_THUMBNAIL_ASSETS_ID);
    }

    @Test
    @Transactional
    public void getAllMNpcCardsByThumbnailAssetsIdIsNullOrNotNull() throws Exception {
        // Initialize the database
        mNpcCardRepository.saveAndFlush(mNpcCard);

        // Get all the mNpcCardList where thumbnailAssetsId is not null
        defaultMNpcCardShouldBeFound("thumbnailAssetsId.specified=true");

        // Get all the mNpcCardList where thumbnailAssetsId is null
        defaultMNpcCardShouldNotBeFound("thumbnailAssetsId.specified=false");
    }

    @Test
    @Transactional
    public void getAllMNpcCardsByThumbnailAssetsIdIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mNpcCardRepository.saveAndFlush(mNpcCard);

        // Get all the mNpcCardList where thumbnailAssetsId greater than or equals to DEFAULT_THUMBNAIL_ASSETS_ID
        defaultMNpcCardShouldBeFound("thumbnailAssetsId.greaterOrEqualThan=" + DEFAULT_THUMBNAIL_ASSETS_ID);

        // Get all the mNpcCardList where thumbnailAssetsId greater than or equals to UPDATED_THUMBNAIL_ASSETS_ID
        defaultMNpcCardShouldNotBeFound("thumbnailAssetsId.greaterOrEqualThan=" + UPDATED_THUMBNAIL_ASSETS_ID);
    }

    @Test
    @Transactional
    public void getAllMNpcCardsByThumbnailAssetsIdIsLessThanSomething() throws Exception {
        // Initialize the database
        mNpcCardRepository.saveAndFlush(mNpcCard);

        // Get all the mNpcCardList where thumbnailAssetsId less than or equals to DEFAULT_THUMBNAIL_ASSETS_ID
        defaultMNpcCardShouldNotBeFound("thumbnailAssetsId.lessThan=" + DEFAULT_THUMBNAIL_ASSETS_ID);

        // Get all the mNpcCardList where thumbnailAssetsId less than or equals to UPDATED_THUMBNAIL_ASSETS_ID
        defaultMNpcCardShouldBeFound("thumbnailAssetsId.lessThan=" + UPDATED_THUMBNAIL_ASSETS_ID);
    }


    @Test
    @Transactional
    public void getAllMNpcCardsByCardIllustAssetsIdIsEqualToSomething() throws Exception {
        // Initialize the database
        mNpcCardRepository.saveAndFlush(mNpcCard);

        // Get all the mNpcCardList where cardIllustAssetsId equals to DEFAULT_CARD_ILLUST_ASSETS_ID
        defaultMNpcCardShouldBeFound("cardIllustAssetsId.equals=" + DEFAULT_CARD_ILLUST_ASSETS_ID);

        // Get all the mNpcCardList where cardIllustAssetsId equals to UPDATED_CARD_ILLUST_ASSETS_ID
        defaultMNpcCardShouldNotBeFound("cardIllustAssetsId.equals=" + UPDATED_CARD_ILLUST_ASSETS_ID);
    }

    @Test
    @Transactional
    public void getAllMNpcCardsByCardIllustAssetsIdIsInShouldWork() throws Exception {
        // Initialize the database
        mNpcCardRepository.saveAndFlush(mNpcCard);

        // Get all the mNpcCardList where cardIllustAssetsId in DEFAULT_CARD_ILLUST_ASSETS_ID or UPDATED_CARD_ILLUST_ASSETS_ID
        defaultMNpcCardShouldBeFound("cardIllustAssetsId.in=" + DEFAULT_CARD_ILLUST_ASSETS_ID + "," + UPDATED_CARD_ILLUST_ASSETS_ID);

        // Get all the mNpcCardList where cardIllustAssetsId equals to UPDATED_CARD_ILLUST_ASSETS_ID
        defaultMNpcCardShouldNotBeFound("cardIllustAssetsId.in=" + UPDATED_CARD_ILLUST_ASSETS_ID);
    }

    @Test
    @Transactional
    public void getAllMNpcCardsByCardIllustAssetsIdIsNullOrNotNull() throws Exception {
        // Initialize the database
        mNpcCardRepository.saveAndFlush(mNpcCard);

        // Get all the mNpcCardList where cardIllustAssetsId is not null
        defaultMNpcCardShouldBeFound("cardIllustAssetsId.specified=true");

        // Get all the mNpcCardList where cardIllustAssetsId is null
        defaultMNpcCardShouldNotBeFound("cardIllustAssetsId.specified=false");
    }

    @Test
    @Transactional
    public void getAllMNpcCardsByCardIllustAssetsIdIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mNpcCardRepository.saveAndFlush(mNpcCard);

        // Get all the mNpcCardList where cardIllustAssetsId greater than or equals to DEFAULT_CARD_ILLUST_ASSETS_ID
        defaultMNpcCardShouldBeFound("cardIllustAssetsId.greaterOrEqualThan=" + DEFAULT_CARD_ILLUST_ASSETS_ID);

        // Get all the mNpcCardList where cardIllustAssetsId greater than or equals to UPDATED_CARD_ILLUST_ASSETS_ID
        defaultMNpcCardShouldNotBeFound("cardIllustAssetsId.greaterOrEqualThan=" + UPDATED_CARD_ILLUST_ASSETS_ID);
    }

    @Test
    @Transactional
    public void getAllMNpcCardsByCardIllustAssetsIdIsLessThanSomething() throws Exception {
        // Initialize the database
        mNpcCardRepository.saveAndFlush(mNpcCard);

        // Get all the mNpcCardList where cardIllustAssetsId less than or equals to DEFAULT_CARD_ILLUST_ASSETS_ID
        defaultMNpcCardShouldNotBeFound("cardIllustAssetsId.lessThan=" + DEFAULT_CARD_ILLUST_ASSETS_ID);

        // Get all the mNpcCardList where cardIllustAssetsId less than or equals to UPDATED_CARD_ILLUST_ASSETS_ID
        defaultMNpcCardShouldBeFound("cardIllustAssetsId.lessThan=" + UPDATED_CARD_ILLUST_ASSETS_ID);
    }


    @Test
    @Transactional
    public void getAllMNpcCardsByPlayableAssetsIdIsEqualToSomething() throws Exception {
        // Initialize the database
        mNpcCardRepository.saveAndFlush(mNpcCard);

        // Get all the mNpcCardList where playableAssetsId equals to DEFAULT_PLAYABLE_ASSETS_ID
        defaultMNpcCardShouldBeFound("playableAssetsId.equals=" + DEFAULT_PLAYABLE_ASSETS_ID);

        // Get all the mNpcCardList where playableAssetsId equals to UPDATED_PLAYABLE_ASSETS_ID
        defaultMNpcCardShouldNotBeFound("playableAssetsId.equals=" + UPDATED_PLAYABLE_ASSETS_ID);
    }

    @Test
    @Transactional
    public void getAllMNpcCardsByPlayableAssetsIdIsInShouldWork() throws Exception {
        // Initialize the database
        mNpcCardRepository.saveAndFlush(mNpcCard);

        // Get all the mNpcCardList where playableAssetsId in DEFAULT_PLAYABLE_ASSETS_ID or UPDATED_PLAYABLE_ASSETS_ID
        defaultMNpcCardShouldBeFound("playableAssetsId.in=" + DEFAULT_PLAYABLE_ASSETS_ID + "," + UPDATED_PLAYABLE_ASSETS_ID);

        // Get all the mNpcCardList where playableAssetsId equals to UPDATED_PLAYABLE_ASSETS_ID
        defaultMNpcCardShouldNotBeFound("playableAssetsId.in=" + UPDATED_PLAYABLE_ASSETS_ID);
    }

    @Test
    @Transactional
    public void getAllMNpcCardsByPlayableAssetsIdIsNullOrNotNull() throws Exception {
        // Initialize the database
        mNpcCardRepository.saveAndFlush(mNpcCard);

        // Get all the mNpcCardList where playableAssetsId is not null
        defaultMNpcCardShouldBeFound("playableAssetsId.specified=true");

        // Get all the mNpcCardList where playableAssetsId is null
        defaultMNpcCardShouldNotBeFound("playableAssetsId.specified=false");
    }

    @Test
    @Transactional
    public void getAllMNpcCardsByPlayableAssetsIdIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mNpcCardRepository.saveAndFlush(mNpcCard);

        // Get all the mNpcCardList where playableAssetsId greater than or equals to DEFAULT_PLAYABLE_ASSETS_ID
        defaultMNpcCardShouldBeFound("playableAssetsId.greaterOrEqualThan=" + DEFAULT_PLAYABLE_ASSETS_ID);

        // Get all the mNpcCardList where playableAssetsId greater than or equals to UPDATED_PLAYABLE_ASSETS_ID
        defaultMNpcCardShouldNotBeFound("playableAssetsId.greaterOrEqualThan=" + UPDATED_PLAYABLE_ASSETS_ID);
    }

    @Test
    @Transactional
    public void getAllMNpcCardsByPlayableAssetsIdIsLessThanSomething() throws Exception {
        // Initialize the database
        mNpcCardRepository.saveAndFlush(mNpcCard);

        // Get all the mNpcCardList where playableAssetsId less than or equals to DEFAULT_PLAYABLE_ASSETS_ID
        defaultMNpcCardShouldNotBeFound("playableAssetsId.lessThan=" + DEFAULT_PLAYABLE_ASSETS_ID);

        // Get all the mNpcCardList where playableAssetsId less than or equals to UPDATED_PLAYABLE_ASSETS_ID
        defaultMNpcCardShouldBeFound("playableAssetsId.lessThan=" + UPDATED_PLAYABLE_ASSETS_ID);
    }


    @Test
    @Transactional
    public void getAllMNpcCardsByTeamEffectIdIsEqualToSomething() throws Exception {
        // Initialize the database
        mNpcCardRepository.saveAndFlush(mNpcCard);

        // Get all the mNpcCardList where teamEffectId equals to DEFAULT_TEAM_EFFECT_ID
        defaultMNpcCardShouldBeFound("teamEffectId.equals=" + DEFAULT_TEAM_EFFECT_ID);

        // Get all the mNpcCardList where teamEffectId equals to UPDATED_TEAM_EFFECT_ID
        defaultMNpcCardShouldNotBeFound("teamEffectId.equals=" + UPDATED_TEAM_EFFECT_ID);
    }

    @Test
    @Transactional
    public void getAllMNpcCardsByTeamEffectIdIsInShouldWork() throws Exception {
        // Initialize the database
        mNpcCardRepository.saveAndFlush(mNpcCard);

        // Get all the mNpcCardList where teamEffectId in DEFAULT_TEAM_EFFECT_ID or UPDATED_TEAM_EFFECT_ID
        defaultMNpcCardShouldBeFound("teamEffectId.in=" + DEFAULT_TEAM_EFFECT_ID + "," + UPDATED_TEAM_EFFECT_ID);

        // Get all the mNpcCardList where teamEffectId equals to UPDATED_TEAM_EFFECT_ID
        defaultMNpcCardShouldNotBeFound("teamEffectId.in=" + UPDATED_TEAM_EFFECT_ID);
    }

    @Test
    @Transactional
    public void getAllMNpcCardsByTeamEffectIdIsNullOrNotNull() throws Exception {
        // Initialize the database
        mNpcCardRepository.saveAndFlush(mNpcCard);

        // Get all the mNpcCardList where teamEffectId is not null
        defaultMNpcCardShouldBeFound("teamEffectId.specified=true");

        // Get all the mNpcCardList where teamEffectId is null
        defaultMNpcCardShouldNotBeFound("teamEffectId.specified=false");
    }

    @Test
    @Transactional
    public void getAllMNpcCardsByTeamEffectIdIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mNpcCardRepository.saveAndFlush(mNpcCard);

        // Get all the mNpcCardList where teamEffectId greater than or equals to DEFAULT_TEAM_EFFECT_ID
        defaultMNpcCardShouldBeFound("teamEffectId.greaterOrEqualThan=" + DEFAULT_TEAM_EFFECT_ID);

        // Get all the mNpcCardList where teamEffectId greater than or equals to UPDATED_TEAM_EFFECT_ID
        defaultMNpcCardShouldNotBeFound("teamEffectId.greaterOrEqualThan=" + UPDATED_TEAM_EFFECT_ID);
    }

    @Test
    @Transactional
    public void getAllMNpcCardsByTeamEffectIdIsLessThanSomething() throws Exception {
        // Initialize the database
        mNpcCardRepository.saveAndFlush(mNpcCard);

        // Get all the mNpcCardList where teamEffectId less than or equals to DEFAULT_TEAM_EFFECT_ID
        defaultMNpcCardShouldNotBeFound("teamEffectId.lessThan=" + DEFAULT_TEAM_EFFECT_ID);

        // Get all the mNpcCardList where teamEffectId less than or equals to UPDATED_TEAM_EFFECT_ID
        defaultMNpcCardShouldBeFound("teamEffectId.lessThan=" + UPDATED_TEAM_EFFECT_ID);
    }


    @Test
    @Transactional
    public void getAllMNpcCardsByTeamEffectLevelIsEqualToSomething() throws Exception {
        // Initialize the database
        mNpcCardRepository.saveAndFlush(mNpcCard);

        // Get all the mNpcCardList where teamEffectLevel equals to DEFAULT_TEAM_EFFECT_LEVEL
        defaultMNpcCardShouldBeFound("teamEffectLevel.equals=" + DEFAULT_TEAM_EFFECT_LEVEL);

        // Get all the mNpcCardList where teamEffectLevel equals to UPDATED_TEAM_EFFECT_LEVEL
        defaultMNpcCardShouldNotBeFound("teamEffectLevel.equals=" + UPDATED_TEAM_EFFECT_LEVEL);
    }

    @Test
    @Transactional
    public void getAllMNpcCardsByTeamEffectLevelIsInShouldWork() throws Exception {
        // Initialize the database
        mNpcCardRepository.saveAndFlush(mNpcCard);

        // Get all the mNpcCardList where teamEffectLevel in DEFAULT_TEAM_EFFECT_LEVEL or UPDATED_TEAM_EFFECT_LEVEL
        defaultMNpcCardShouldBeFound("teamEffectLevel.in=" + DEFAULT_TEAM_EFFECT_LEVEL + "," + UPDATED_TEAM_EFFECT_LEVEL);

        // Get all the mNpcCardList where teamEffectLevel equals to UPDATED_TEAM_EFFECT_LEVEL
        defaultMNpcCardShouldNotBeFound("teamEffectLevel.in=" + UPDATED_TEAM_EFFECT_LEVEL);
    }

    @Test
    @Transactional
    public void getAllMNpcCardsByTeamEffectLevelIsNullOrNotNull() throws Exception {
        // Initialize the database
        mNpcCardRepository.saveAndFlush(mNpcCard);

        // Get all the mNpcCardList where teamEffectLevel is not null
        defaultMNpcCardShouldBeFound("teamEffectLevel.specified=true");

        // Get all the mNpcCardList where teamEffectLevel is null
        defaultMNpcCardShouldNotBeFound("teamEffectLevel.specified=false");
    }

    @Test
    @Transactional
    public void getAllMNpcCardsByTeamEffectLevelIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mNpcCardRepository.saveAndFlush(mNpcCard);

        // Get all the mNpcCardList where teamEffectLevel greater than or equals to DEFAULT_TEAM_EFFECT_LEVEL
        defaultMNpcCardShouldBeFound("teamEffectLevel.greaterOrEqualThan=" + DEFAULT_TEAM_EFFECT_LEVEL);

        // Get all the mNpcCardList where teamEffectLevel greater than or equals to UPDATED_TEAM_EFFECT_LEVEL
        defaultMNpcCardShouldNotBeFound("teamEffectLevel.greaterOrEqualThan=" + UPDATED_TEAM_EFFECT_LEVEL);
    }

    @Test
    @Transactional
    public void getAllMNpcCardsByTeamEffectLevelIsLessThanSomething() throws Exception {
        // Initialize the database
        mNpcCardRepository.saveAndFlush(mNpcCard);

        // Get all the mNpcCardList where teamEffectLevel less than or equals to DEFAULT_TEAM_EFFECT_LEVEL
        defaultMNpcCardShouldNotBeFound("teamEffectLevel.lessThan=" + DEFAULT_TEAM_EFFECT_LEVEL);

        // Get all the mNpcCardList where teamEffectLevel less than or equals to UPDATED_TEAM_EFFECT_LEVEL
        defaultMNpcCardShouldBeFound("teamEffectLevel.lessThan=" + UPDATED_TEAM_EFFECT_LEVEL);
    }


    @Test
    @Transactional
    public void getAllMNpcCardsByTriggerEffectIdIsEqualToSomething() throws Exception {
        // Initialize the database
        mNpcCardRepository.saveAndFlush(mNpcCard);

        // Get all the mNpcCardList where triggerEffectId equals to DEFAULT_TRIGGER_EFFECT_ID
        defaultMNpcCardShouldBeFound("triggerEffectId.equals=" + DEFAULT_TRIGGER_EFFECT_ID);

        // Get all the mNpcCardList where triggerEffectId equals to UPDATED_TRIGGER_EFFECT_ID
        defaultMNpcCardShouldNotBeFound("triggerEffectId.equals=" + UPDATED_TRIGGER_EFFECT_ID);
    }

    @Test
    @Transactional
    public void getAllMNpcCardsByTriggerEffectIdIsInShouldWork() throws Exception {
        // Initialize the database
        mNpcCardRepository.saveAndFlush(mNpcCard);

        // Get all the mNpcCardList where triggerEffectId in DEFAULT_TRIGGER_EFFECT_ID or UPDATED_TRIGGER_EFFECT_ID
        defaultMNpcCardShouldBeFound("triggerEffectId.in=" + DEFAULT_TRIGGER_EFFECT_ID + "," + UPDATED_TRIGGER_EFFECT_ID);

        // Get all the mNpcCardList where triggerEffectId equals to UPDATED_TRIGGER_EFFECT_ID
        defaultMNpcCardShouldNotBeFound("triggerEffectId.in=" + UPDATED_TRIGGER_EFFECT_ID);
    }

    @Test
    @Transactional
    public void getAllMNpcCardsByTriggerEffectIdIsNullOrNotNull() throws Exception {
        // Initialize the database
        mNpcCardRepository.saveAndFlush(mNpcCard);

        // Get all the mNpcCardList where triggerEffectId is not null
        defaultMNpcCardShouldBeFound("triggerEffectId.specified=true");

        // Get all the mNpcCardList where triggerEffectId is null
        defaultMNpcCardShouldNotBeFound("triggerEffectId.specified=false");
    }

    @Test
    @Transactional
    public void getAllMNpcCardsByTriggerEffectIdIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mNpcCardRepository.saveAndFlush(mNpcCard);

        // Get all the mNpcCardList where triggerEffectId greater than or equals to DEFAULT_TRIGGER_EFFECT_ID
        defaultMNpcCardShouldBeFound("triggerEffectId.greaterOrEqualThan=" + DEFAULT_TRIGGER_EFFECT_ID);

        // Get all the mNpcCardList where triggerEffectId greater than or equals to UPDATED_TRIGGER_EFFECT_ID
        defaultMNpcCardShouldNotBeFound("triggerEffectId.greaterOrEqualThan=" + UPDATED_TRIGGER_EFFECT_ID);
    }

    @Test
    @Transactional
    public void getAllMNpcCardsByTriggerEffectIdIsLessThanSomething() throws Exception {
        // Initialize the database
        mNpcCardRepository.saveAndFlush(mNpcCard);

        // Get all the mNpcCardList where triggerEffectId less than or equals to DEFAULT_TRIGGER_EFFECT_ID
        defaultMNpcCardShouldNotBeFound("triggerEffectId.lessThan=" + DEFAULT_TRIGGER_EFFECT_ID);

        // Get all the mNpcCardList where triggerEffectId less than or equals to UPDATED_TRIGGER_EFFECT_ID
        defaultMNpcCardShouldBeFound("triggerEffectId.lessThan=" + UPDATED_TRIGGER_EFFECT_ID);
    }


    @Test
    @Transactional
    public void getAllMNpcCardsByAction1IdIsEqualToSomething() throws Exception {
        // Initialize the database
        mNpcCardRepository.saveAndFlush(mNpcCard);

        // Get all the mNpcCardList where action1Id equals to DEFAULT_ACTION_1_ID
        defaultMNpcCardShouldBeFound("action1Id.equals=" + DEFAULT_ACTION_1_ID);

        // Get all the mNpcCardList where action1Id equals to UPDATED_ACTION_1_ID
        defaultMNpcCardShouldNotBeFound("action1Id.equals=" + UPDATED_ACTION_1_ID);
    }

    @Test
    @Transactional
    public void getAllMNpcCardsByAction1IdIsInShouldWork() throws Exception {
        // Initialize the database
        mNpcCardRepository.saveAndFlush(mNpcCard);

        // Get all the mNpcCardList where action1Id in DEFAULT_ACTION_1_ID or UPDATED_ACTION_1_ID
        defaultMNpcCardShouldBeFound("action1Id.in=" + DEFAULT_ACTION_1_ID + "," + UPDATED_ACTION_1_ID);

        // Get all the mNpcCardList where action1Id equals to UPDATED_ACTION_1_ID
        defaultMNpcCardShouldNotBeFound("action1Id.in=" + UPDATED_ACTION_1_ID);
    }

    @Test
    @Transactional
    public void getAllMNpcCardsByAction1IdIsNullOrNotNull() throws Exception {
        // Initialize the database
        mNpcCardRepository.saveAndFlush(mNpcCard);

        // Get all the mNpcCardList where action1Id is not null
        defaultMNpcCardShouldBeFound("action1Id.specified=true");

        // Get all the mNpcCardList where action1Id is null
        defaultMNpcCardShouldNotBeFound("action1Id.specified=false");
    }

    @Test
    @Transactional
    public void getAllMNpcCardsByAction1IdIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mNpcCardRepository.saveAndFlush(mNpcCard);

        // Get all the mNpcCardList where action1Id greater than or equals to DEFAULT_ACTION_1_ID
        defaultMNpcCardShouldBeFound("action1Id.greaterOrEqualThan=" + DEFAULT_ACTION_1_ID);

        // Get all the mNpcCardList where action1Id greater than or equals to UPDATED_ACTION_1_ID
        defaultMNpcCardShouldNotBeFound("action1Id.greaterOrEqualThan=" + UPDATED_ACTION_1_ID);
    }

    @Test
    @Transactional
    public void getAllMNpcCardsByAction1IdIsLessThanSomething() throws Exception {
        // Initialize the database
        mNpcCardRepository.saveAndFlush(mNpcCard);

        // Get all the mNpcCardList where action1Id less than or equals to DEFAULT_ACTION_1_ID
        defaultMNpcCardShouldNotBeFound("action1Id.lessThan=" + DEFAULT_ACTION_1_ID);

        // Get all the mNpcCardList where action1Id less than or equals to UPDATED_ACTION_1_ID
        defaultMNpcCardShouldBeFound("action1Id.lessThan=" + UPDATED_ACTION_1_ID);
    }


    @Test
    @Transactional
    public void getAllMNpcCardsByAction1LevelIsEqualToSomething() throws Exception {
        // Initialize the database
        mNpcCardRepository.saveAndFlush(mNpcCard);

        // Get all the mNpcCardList where action1Level equals to DEFAULT_ACTION_1_LEVEL
        defaultMNpcCardShouldBeFound("action1Level.equals=" + DEFAULT_ACTION_1_LEVEL);

        // Get all the mNpcCardList where action1Level equals to UPDATED_ACTION_1_LEVEL
        defaultMNpcCardShouldNotBeFound("action1Level.equals=" + UPDATED_ACTION_1_LEVEL);
    }

    @Test
    @Transactional
    public void getAllMNpcCardsByAction1LevelIsInShouldWork() throws Exception {
        // Initialize the database
        mNpcCardRepository.saveAndFlush(mNpcCard);

        // Get all the mNpcCardList where action1Level in DEFAULT_ACTION_1_LEVEL or UPDATED_ACTION_1_LEVEL
        defaultMNpcCardShouldBeFound("action1Level.in=" + DEFAULT_ACTION_1_LEVEL + "," + UPDATED_ACTION_1_LEVEL);

        // Get all the mNpcCardList where action1Level equals to UPDATED_ACTION_1_LEVEL
        defaultMNpcCardShouldNotBeFound("action1Level.in=" + UPDATED_ACTION_1_LEVEL);
    }

    @Test
    @Transactional
    public void getAllMNpcCardsByAction1LevelIsNullOrNotNull() throws Exception {
        // Initialize the database
        mNpcCardRepository.saveAndFlush(mNpcCard);

        // Get all the mNpcCardList where action1Level is not null
        defaultMNpcCardShouldBeFound("action1Level.specified=true");

        // Get all the mNpcCardList where action1Level is null
        defaultMNpcCardShouldNotBeFound("action1Level.specified=false");
    }

    @Test
    @Transactional
    public void getAllMNpcCardsByAction1LevelIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mNpcCardRepository.saveAndFlush(mNpcCard);

        // Get all the mNpcCardList where action1Level greater than or equals to DEFAULT_ACTION_1_LEVEL
        defaultMNpcCardShouldBeFound("action1Level.greaterOrEqualThan=" + DEFAULT_ACTION_1_LEVEL);

        // Get all the mNpcCardList where action1Level greater than or equals to UPDATED_ACTION_1_LEVEL
        defaultMNpcCardShouldNotBeFound("action1Level.greaterOrEqualThan=" + UPDATED_ACTION_1_LEVEL);
    }

    @Test
    @Transactional
    public void getAllMNpcCardsByAction1LevelIsLessThanSomething() throws Exception {
        // Initialize the database
        mNpcCardRepository.saveAndFlush(mNpcCard);

        // Get all the mNpcCardList where action1Level less than or equals to DEFAULT_ACTION_1_LEVEL
        defaultMNpcCardShouldNotBeFound("action1Level.lessThan=" + DEFAULT_ACTION_1_LEVEL);

        // Get all the mNpcCardList where action1Level less than or equals to UPDATED_ACTION_1_LEVEL
        defaultMNpcCardShouldBeFound("action1Level.lessThan=" + UPDATED_ACTION_1_LEVEL);
    }


    @Test
    @Transactional
    public void getAllMNpcCardsByAction2IdIsEqualToSomething() throws Exception {
        // Initialize the database
        mNpcCardRepository.saveAndFlush(mNpcCard);

        // Get all the mNpcCardList where action2Id equals to DEFAULT_ACTION_2_ID
        defaultMNpcCardShouldBeFound("action2Id.equals=" + DEFAULT_ACTION_2_ID);

        // Get all the mNpcCardList where action2Id equals to UPDATED_ACTION_2_ID
        defaultMNpcCardShouldNotBeFound("action2Id.equals=" + UPDATED_ACTION_2_ID);
    }

    @Test
    @Transactional
    public void getAllMNpcCardsByAction2IdIsInShouldWork() throws Exception {
        // Initialize the database
        mNpcCardRepository.saveAndFlush(mNpcCard);

        // Get all the mNpcCardList where action2Id in DEFAULT_ACTION_2_ID or UPDATED_ACTION_2_ID
        defaultMNpcCardShouldBeFound("action2Id.in=" + DEFAULT_ACTION_2_ID + "," + UPDATED_ACTION_2_ID);

        // Get all the mNpcCardList where action2Id equals to UPDATED_ACTION_2_ID
        defaultMNpcCardShouldNotBeFound("action2Id.in=" + UPDATED_ACTION_2_ID);
    }

    @Test
    @Transactional
    public void getAllMNpcCardsByAction2IdIsNullOrNotNull() throws Exception {
        // Initialize the database
        mNpcCardRepository.saveAndFlush(mNpcCard);

        // Get all the mNpcCardList where action2Id is not null
        defaultMNpcCardShouldBeFound("action2Id.specified=true");

        // Get all the mNpcCardList where action2Id is null
        defaultMNpcCardShouldNotBeFound("action2Id.specified=false");
    }

    @Test
    @Transactional
    public void getAllMNpcCardsByAction2IdIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mNpcCardRepository.saveAndFlush(mNpcCard);

        // Get all the mNpcCardList where action2Id greater than or equals to DEFAULT_ACTION_2_ID
        defaultMNpcCardShouldBeFound("action2Id.greaterOrEqualThan=" + DEFAULT_ACTION_2_ID);

        // Get all the mNpcCardList where action2Id greater than or equals to UPDATED_ACTION_2_ID
        defaultMNpcCardShouldNotBeFound("action2Id.greaterOrEqualThan=" + UPDATED_ACTION_2_ID);
    }

    @Test
    @Transactional
    public void getAllMNpcCardsByAction2IdIsLessThanSomething() throws Exception {
        // Initialize the database
        mNpcCardRepository.saveAndFlush(mNpcCard);

        // Get all the mNpcCardList where action2Id less than or equals to DEFAULT_ACTION_2_ID
        defaultMNpcCardShouldNotBeFound("action2Id.lessThan=" + DEFAULT_ACTION_2_ID);

        // Get all the mNpcCardList where action2Id less than or equals to UPDATED_ACTION_2_ID
        defaultMNpcCardShouldBeFound("action2Id.lessThan=" + UPDATED_ACTION_2_ID);
    }


    @Test
    @Transactional
    public void getAllMNpcCardsByAction2LevelIsEqualToSomething() throws Exception {
        // Initialize the database
        mNpcCardRepository.saveAndFlush(mNpcCard);

        // Get all the mNpcCardList where action2Level equals to DEFAULT_ACTION_2_LEVEL
        defaultMNpcCardShouldBeFound("action2Level.equals=" + DEFAULT_ACTION_2_LEVEL);

        // Get all the mNpcCardList where action2Level equals to UPDATED_ACTION_2_LEVEL
        defaultMNpcCardShouldNotBeFound("action2Level.equals=" + UPDATED_ACTION_2_LEVEL);
    }

    @Test
    @Transactional
    public void getAllMNpcCardsByAction2LevelIsInShouldWork() throws Exception {
        // Initialize the database
        mNpcCardRepository.saveAndFlush(mNpcCard);

        // Get all the mNpcCardList where action2Level in DEFAULT_ACTION_2_LEVEL or UPDATED_ACTION_2_LEVEL
        defaultMNpcCardShouldBeFound("action2Level.in=" + DEFAULT_ACTION_2_LEVEL + "," + UPDATED_ACTION_2_LEVEL);

        // Get all the mNpcCardList where action2Level equals to UPDATED_ACTION_2_LEVEL
        defaultMNpcCardShouldNotBeFound("action2Level.in=" + UPDATED_ACTION_2_LEVEL);
    }

    @Test
    @Transactional
    public void getAllMNpcCardsByAction2LevelIsNullOrNotNull() throws Exception {
        // Initialize the database
        mNpcCardRepository.saveAndFlush(mNpcCard);

        // Get all the mNpcCardList where action2Level is not null
        defaultMNpcCardShouldBeFound("action2Level.specified=true");

        // Get all the mNpcCardList where action2Level is null
        defaultMNpcCardShouldNotBeFound("action2Level.specified=false");
    }

    @Test
    @Transactional
    public void getAllMNpcCardsByAction2LevelIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mNpcCardRepository.saveAndFlush(mNpcCard);

        // Get all the mNpcCardList where action2Level greater than or equals to DEFAULT_ACTION_2_LEVEL
        defaultMNpcCardShouldBeFound("action2Level.greaterOrEqualThan=" + DEFAULT_ACTION_2_LEVEL);

        // Get all the mNpcCardList where action2Level greater than or equals to UPDATED_ACTION_2_LEVEL
        defaultMNpcCardShouldNotBeFound("action2Level.greaterOrEqualThan=" + UPDATED_ACTION_2_LEVEL);
    }

    @Test
    @Transactional
    public void getAllMNpcCardsByAction2LevelIsLessThanSomething() throws Exception {
        // Initialize the database
        mNpcCardRepository.saveAndFlush(mNpcCard);

        // Get all the mNpcCardList where action2Level less than or equals to DEFAULT_ACTION_2_LEVEL
        defaultMNpcCardShouldNotBeFound("action2Level.lessThan=" + DEFAULT_ACTION_2_LEVEL);

        // Get all the mNpcCardList where action2Level less than or equals to UPDATED_ACTION_2_LEVEL
        defaultMNpcCardShouldBeFound("action2Level.lessThan=" + UPDATED_ACTION_2_LEVEL);
    }


    @Test
    @Transactional
    public void getAllMNpcCardsByAction3IdIsEqualToSomething() throws Exception {
        // Initialize the database
        mNpcCardRepository.saveAndFlush(mNpcCard);

        // Get all the mNpcCardList where action3Id equals to DEFAULT_ACTION_3_ID
        defaultMNpcCardShouldBeFound("action3Id.equals=" + DEFAULT_ACTION_3_ID);

        // Get all the mNpcCardList where action3Id equals to UPDATED_ACTION_3_ID
        defaultMNpcCardShouldNotBeFound("action3Id.equals=" + UPDATED_ACTION_3_ID);
    }

    @Test
    @Transactional
    public void getAllMNpcCardsByAction3IdIsInShouldWork() throws Exception {
        // Initialize the database
        mNpcCardRepository.saveAndFlush(mNpcCard);

        // Get all the mNpcCardList where action3Id in DEFAULT_ACTION_3_ID or UPDATED_ACTION_3_ID
        defaultMNpcCardShouldBeFound("action3Id.in=" + DEFAULT_ACTION_3_ID + "," + UPDATED_ACTION_3_ID);

        // Get all the mNpcCardList where action3Id equals to UPDATED_ACTION_3_ID
        defaultMNpcCardShouldNotBeFound("action3Id.in=" + UPDATED_ACTION_3_ID);
    }

    @Test
    @Transactional
    public void getAllMNpcCardsByAction3IdIsNullOrNotNull() throws Exception {
        // Initialize the database
        mNpcCardRepository.saveAndFlush(mNpcCard);

        // Get all the mNpcCardList where action3Id is not null
        defaultMNpcCardShouldBeFound("action3Id.specified=true");

        // Get all the mNpcCardList where action3Id is null
        defaultMNpcCardShouldNotBeFound("action3Id.specified=false");
    }

    @Test
    @Transactional
    public void getAllMNpcCardsByAction3IdIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mNpcCardRepository.saveAndFlush(mNpcCard);

        // Get all the mNpcCardList where action3Id greater than or equals to DEFAULT_ACTION_3_ID
        defaultMNpcCardShouldBeFound("action3Id.greaterOrEqualThan=" + DEFAULT_ACTION_3_ID);

        // Get all the mNpcCardList where action3Id greater than or equals to UPDATED_ACTION_3_ID
        defaultMNpcCardShouldNotBeFound("action3Id.greaterOrEqualThan=" + UPDATED_ACTION_3_ID);
    }

    @Test
    @Transactional
    public void getAllMNpcCardsByAction3IdIsLessThanSomething() throws Exception {
        // Initialize the database
        mNpcCardRepository.saveAndFlush(mNpcCard);

        // Get all the mNpcCardList where action3Id less than or equals to DEFAULT_ACTION_3_ID
        defaultMNpcCardShouldNotBeFound("action3Id.lessThan=" + DEFAULT_ACTION_3_ID);

        // Get all the mNpcCardList where action3Id less than or equals to UPDATED_ACTION_3_ID
        defaultMNpcCardShouldBeFound("action3Id.lessThan=" + UPDATED_ACTION_3_ID);
    }


    @Test
    @Transactional
    public void getAllMNpcCardsByAction3LevelIsEqualToSomething() throws Exception {
        // Initialize the database
        mNpcCardRepository.saveAndFlush(mNpcCard);

        // Get all the mNpcCardList where action3Level equals to DEFAULT_ACTION_3_LEVEL
        defaultMNpcCardShouldBeFound("action3Level.equals=" + DEFAULT_ACTION_3_LEVEL);

        // Get all the mNpcCardList where action3Level equals to UPDATED_ACTION_3_LEVEL
        defaultMNpcCardShouldNotBeFound("action3Level.equals=" + UPDATED_ACTION_3_LEVEL);
    }

    @Test
    @Transactional
    public void getAllMNpcCardsByAction3LevelIsInShouldWork() throws Exception {
        // Initialize the database
        mNpcCardRepository.saveAndFlush(mNpcCard);

        // Get all the mNpcCardList where action3Level in DEFAULT_ACTION_3_LEVEL or UPDATED_ACTION_3_LEVEL
        defaultMNpcCardShouldBeFound("action3Level.in=" + DEFAULT_ACTION_3_LEVEL + "," + UPDATED_ACTION_3_LEVEL);

        // Get all the mNpcCardList where action3Level equals to UPDATED_ACTION_3_LEVEL
        defaultMNpcCardShouldNotBeFound("action3Level.in=" + UPDATED_ACTION_3_LEVEL);
    }

    @Test
    @Transactional
    public void getAllMNpcCardsByAction3LevelIsNullOrNotNull() throws Exception {
        // Initialize the database
        mNpcCardRepository.saveAndFlush(mNpcCard);

        // Get all the mNpcCardList where action3Level is not null
        defaultMNpcCardShouldBeFound("action3Level.specified=true");

        // Get all the mNpcCardList where action3Level is null
        defaultMNpcCardShouldNotBeFound("action3Level.specified=false");
    }

    @Test
    @Transactional
    public void getAllMNpcCardsByAction3LevelIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mNpcCardRepository.saveAndFlush(mNpcCard);

        // Get all the mNpcCardList where action3Level greater than or equals to DEFAULT_ACTION_3_LEVEL
        defaultMNpcCardShouldBeFound("action3Level.greaterOrEqualThan=" + DEFAULT_ACTION_3_LEVEL);

        // Get all the mNpcCardList where action3Level greater than or equals to UPDATED_ACTION_3_LEVEL
        defaultMNpcCardShouldNotBeFound("action3Level.greaterOrEqualThan=" + UPDATED_ACTION_3_LEVEL);
    }

    @Test
    @Transactional
    public void getAllMNpcCardsByAction3LevelIsLessThanSomething() throws Exception {
        // Initialize the database
        mNpcCardRepository.saveAndFlush(mNpcCard);

        // Get all the mNpcCardList where action3Level less than or equals to DEFAULT_ACTION_3_LEVEL
        defaultMNpcCardShouldNotBeFound("action3Level.lessThan=" + DEFAULT_ACTION_3_LEVEL);

        // Get all the mNpcCardList where action3Level less than or equals to UPDATED_ACTION_3_LEVEL
        defaultMNpcCardShouldBeFound("action3Level.lessThan=" + UPDATED_ACTION_3_LEVEL);
    }


    @Test
    @Transactional
    public void getAllMNpcCardsByAction4IdIsEqualToSomething() throws Exception {
        // Initialize the database
        mNpcCardRepository.saveAndFlush(mNpcCard);

        // Get all the mNpcCardList where action4Id equals to DEFAULT_ACTION_4_ID
        defaultMNpcCardShouldBeFound("action4Id.equals=" + DEFAULT_ACTION_4_ID);

        // Get all the mNpcCardList where action4Id equals to UPDATED_ACTION_4_ID
        defaultMNpcCardShouldNotBeFound("action4Id.equals=" + UPDATED_ACTION_4_ID);
    }

    @Test
    @Transactional
    public void getAllMNpcCardsByAction4IdIsInShouldWork() throws Exception {
        // Initialize the database
        mNpcCardRepository.saveAndFlush(mNpcCard);

        // Get all the mNpcCardList where action4Id in DEFAULT_ACTION_4_ID or UPDATED_ACTION_4_ID
        defaultMNpcCardShouldBeFound("action4Id.in=" + DEFAULT_ACTION_4_ID + "," + UPDATED_ACTION_4_ID);

        // Get all the mNpcCardList where action4Id equals to UPDATED_ACTION_4_ID
        defaultMNpcCardShouldNotBeFound("action4Id.in=" + UPDATED_ACTION_4_ID);
    }

    @Test
    @Transactional
    public void getAllMNpcCardsByAction4IdIsNullOrNotNull() throws Exception {
        // Initialize the database
        mNpcCardRepository.saveAndFlush(mNpcCard);

        // Get all the mNpcCardList where action4Id is not null
        defaultMNpcCardShouldBeFound("action4Id.specified=true");

        // Get all the mNpcCardList where action4Id is null
        defaultMNpcCardShouldNotBeFound("action4Id.specified=false");
    }

    @Test
    @Transactional
    public void getAllMNpcCardsByAction4IdIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mNpcCardRepository.saveAndFlush(mNpcCard);

        // Get all the mNpcCardList where action4Id greater than or equals to DEFAULT_ACTION_4_ID
        defaultMNpcCardShouldBeFound("action4Id.greaterOrEqualThan=" + DEFAULT_ACTION_4_ID);

        // Get all the mNpcCardList where action4Id greater than or equals to UPDATED_ACTION_4_ID
        defaultMNpcCardShouldNotBeFound("action4Id.greaterOrEqualThan=" + UPDATED_ACTION_4_ID);
    }

    @Test
    @Transactional
    public void getAllMNpcCardsByAction4IdIsLessThanSomething() throws Exception {
        // Initialize the database
        mNpcCardRepository.saveAndFlush(mNpcCard);

        // Get all the mNpcCardList where action4Id less than or equals to DEFAULT_ACTION_4_ID
        defaultMNpcCardShouldNotBeFound("action4Id.lessThan=" + DEFAULT_ACTION_4_ID);

        // Get all the mNpcCardList where action4Id less than or equals to UPDATED_ACTION_4_ID
        defaultMNpcCardShouldBeFound("action4Id.lessThan=" + UPDATED_ACTION_4_ID);
    }


    @Test
    @Transactional
    public void getAllMNpcCardsByAction4LevelIsEqualToSomething() throws Exception {
        // Initialize the database
        mNpcCardRepository.saveAndFlush(mNpcCard);

        // Get all the mNpcCardList where action4Level equals to DEFAULT_ACTION_4_LEVEL
        defaultMNpcCardShouldBeFound("action4Level.equals=" + DEFAULT_ACTION_4_LEVEL);

        // Get all the mNpcCardList where action4Level equals to UPDATED_ACTION_4_LEVEL
        defaultMNpcCardShouldNotBeFound("action4Level.equals=" + UPDATED_ACTION_4_LEVEL);
    }

    @Test
    @Transactional
    public void getAllMNpcCardsByAction4LevelIsInShouldWork() throws Exception {
        // Initialize the database
        mNpcCardRepository.saveAndFlush(mNpcCard);

        // Get all the mNpcCardList where action4Level in DEFAULT_ACTION_4_LEVEL or UPDATED_ACTION_4_LEVEL
        defaultMNpcCardShouldBeFound("action4Level.in=" + DEFAULT_ACTION_4_LEVEL + "," + UPDATED_ACTION_4_LEVEL);

        // Get all the mNpcCardList where action4Level equals to UPDATED_ACTION_4_LEVEL
        defaultMNpcCardShouldNotBeFound("action4Level.in=" + UPDATED_ACTION_4_LEVEL);
    }

    @Test
    @Transactional
    public void getAllMNpcCardsByAction4LevelIsNullOrNotNull() throws Exception {
        // Initialize the database
        mNpcCardRepository.saveAndFlush(mNpcCard);

        // Get all the mNpcCardList where action4Level is not null
        defaultMNpcCardShouldBeFound("action4Level.specified=true");

        // Get all the mNpcCardList where action4Level is null
        defaultMNpcCardShouldNotBeFound("action4Level.specified=false");
    }

    @Test
    @Transactional
    public void getAllMNpcCardsByAction4LevelIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mNpcCardRepository.saveAndFlush(mNpcCard);

        // Get all the mNpcCardList where action4Level greater than or equals to DEFAULT_ACTION_4_LEVEL
        defaultMNpcCardShouldBeFound("action4Level.greaterOrEqualThan=" + DEFAULT_ACTION_4_LEVEL);

        // Get all the mNpcCardList where action4Level greater than or equals to UPDATED_ACTION_4_LEVEL
        defaultMNpcCardShouldNotBeFound("action4Level.greaterOrEqualThan=" + UPDATED_ACTION_4_LEVEL);
    }

    @Test
    @Transactional
    public void getAllMNpcCardsByAction4LevelIsLessThanSomething() throws Exception {
        // Initialize the database
        mNpcCardRepository.saveAndFlush(mNpcCard);

        // Get all the mNpcCardList where action4Level less than or equals to DEFAULT_ACTION_4_LEVEL
        defaultMNpcCardShouldNotBeFound("action4Level.lessThan=" + DEFAULT_ACTION_4_LEVEL);

        // Get all the mNpcCardList where action4Level less than or equals to UPDATED_ACTION_4_LEVEL
        defaultMNpcCardShouldBeFound("action4Level.lessThan=" + UPDATED_ACTION_4_LEVEL);
    }


    @Test
    @Transactional
    public void getAllMNpcCardsByAction5IdIsEqualToSomething() throws Exception {
        // Initialize the database
        mNpcCardRepository.saveAndFlush(mNpcCard);

        // Get all the mNpcCardList where action5Id equals to DEFAULT_ACTION_5_ID
        defaultMNpcCardShouldBeFound("action5Id.equals=" + DEFAULT_ACTION_5_ID);

        // Get all the mNpcCardList where action5Id equals to UPDATED_ACTION_5_ID
        defaultMNpcCardShouldNotBeFound("action5Id.equals=" + UPDATED_ACTION_5_ID);
    }

    @Test
    @Transactional
    public void getAllMNpcCardsByAction5IdIsInShouldWork() throws Exception {
        // Initialize the database
        mNpcCardRepository.saveAndFlush(mNpcCard);

        // Get all the mNpcCardList where action5Id in DEFAULT_ACTION_5_ID or UPDATED_ACTION_5_ID
        defaultMNpcCardShouldBeFound("action5Id.in=" + DEFAULT_ACTION_5_ID + "," + UPDATED_ACTION_5_ID);

        // Get all the mNpcCardList where action5Id equals to UPDATED_ACTION_5_ID
        defaultMNpcCardShouldNotBeFound("action5Id.in=" + UPDATED_ACTION_5_ID);
    }

    @Test
    @Transactional
    public void getAllMNpcCardsByAction5IdIsNullOrNotNull() throws Exception {
        // Initialize the database
        mNpcCardRepository.saveAndFlush(mNpcCard);

        // Get all the mNpcCardList where action5Id is not null
        defaultMNpcCardShouldBeFound("action5Id.specified=true");

        // Get all the mNpcCardList where action5Id is null
        defaultMNpcCardShouldNotBeFound("action5Id.specified=false");
    }

    @Test
    @Transactional
    public void getAllMNpcCardsByAction5IdIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mNpcCardRepository.saveAndFlush(mNpcCard);

        // Get all the mNpcCardList where action5Id greater than or equals to DEFAULT_ACTION_5_ID
        defaultMNpcCardShouldBeFound("action5Id.greaterOrEqualThan=" + DEFAULT_ACTION_5_ID);

        // Get all the mNpcCardList where action5Id greater than or equals to UPDATED_ACTION_5_ID
        defaultMNpcCardShouldNotBeFound("action5Id.greaterOrEqualThan=" + UPDATED_ACTION_5_ID);
    }

    @Test
    @Transactional
    public void getAllMNpcCardsByAction5IdIsLessThanSomething() throws Exception {
        // Initialize the database
        mNpcCardRepository.saveAndFlush(mNpcCard);

        // Get all the mNpcCardList where action5Id less than or equals to DEFAULT_ACTION_5_ID
        defaultMNpcCardShouldNotBeFound("action5Id.lessThan=" + DEFAULT_ACTION_5_ID);

        // Get all the mNpcCardList where action5Id less than or equals to UPDATED_ACTION_5_ID
        defaultMNpcCardShouldBeFound("action5Id.lessThan=" + UPDATED_ACTION_5_ID);
    }


    @Test
    @Transactional
    public void getAllMNpcCardsByAction5LevelIsEqualToSomething() throws Exception {
        // Initialize the database
        mNpcCardRepository.saveAndFlush(mNpcCard);

        // Get all the mNpcCardList where action5Level equals to DEFAULT_ACTION_5_LEVEL
        defaultMNpcCardShouldBeFound("action5Level.equals=" + DEFAULT_ACTION_5_LEVEL);

        // Get all the mNpcCardList where action5Level equals to UPDATED_ACTION_5_LEVEL
        defaultMNpcCardShouldNotBeFound("action5Level.equals=" + UPDATED_ACTION_5_LEVEL);
    }

    @Test
    @Transactional
    public void getAllMNpcCardsByAction5LevelIsInShouldWork() throws Exception {
        // Initialize the database
        mNpcCardRepository.saveAndFlush(mNpcCard);

        // Get all the mNpcCardList where action5Level in DEFAULT_ACTION_5_LEVEL or UPDATED_ACTION_5_LEVEL
        defaultMNpcCardShouldBeFound("action5Level.in=" + DEFAULT_ACTION_5_LEVEL + "," + UPDATED_ACTION_5_LEVEL);

        // Get all the mNpcCardList where action5Level equals to UPDATED_ACTION_5_LEVEL
        defaultMNpcCardShouldNotBeFound("action5Level.in=" + UPDATED_ACTION_5_LEVEL);
    }

    @Test
    @Transactional
    public void getAllMNpcCardsByAction5LevelIsNullOrNotNull() throws Exception {
        // Initialize the database
        mNpcCardRepository.saveAndFlush(mNpcCard);

        // Get all the mNpcCardList where action5Level is not null
        defaultMNpcCardShouldBeFound("action5Level.specified=true");

        // Get all the mNpcCardList where action5Level is null
        defaultMNpcCardShouldNotBeFound("action5Level.specified=false");
    }

    @Test
    @Transactional
    public void getAllMNpcCardsByAction5LevelIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mNpcCardRepository.saveAndFlush(mNpcCard);

        // Get all the mNpcCardList where action5Level greater than or equals to DEFAULT_ACTION_5_LEVEL
        defaultMNpcCardShouldBeFound("action5Level.greaterOrEqualThan=" + DEFAULT_ACTION_5_LEVEL);

        // Get all the mNpcCardList where action5Level greater than or equals to UPDATED_ACTION_5_LEVEL
        defaultMNpcCardShouldNotBeFound("action5Level.greaterOrEqualThan=" + UPDATED_ACTION_5_LEVEL);
    }

    @Test
    @Transactional
    public void getAllMNpcCardsByAction5LevelIsLessThanSomething() throws Exception {
        // Initialize the database
        mNpcCardRepository.saveAndFlush(mNpcCard);

        // Get all the mNpcCardList where action5Level less than or equals to DEFAULT_ACTION_5_LEVEL
        defaultMNpcCardShouldNotBeFound("action5Level.lessThan=" + DEFAULT_ACTION_5_LEVEL);

        // Get all the mNpcCardList where action5Level less than or equals to UPDATED_ACTION_5_LEVEL
        defaultMNpcCardShouldBeFound("action5Level.lessThan=" + UPDATED_ACTION_5_LEVEL);
    }


    @Test
    @Transactional
    public void getAllMNpcCardsByStaminaIsEqualToSomething() throws Exception {
        // Initialize the database
        mNpcCardRepository.saveAndFlush(mNpcCard);

        // Get all the mNpcCardList where stamina equals to DEFAULT_STAMINA
        defaultMNpcCardShouldBeFound("stamina.equals=" + DEFAULT_STAMINA);

        // Get all the mNpcCardList where stamina equals to UPDATED_STAMINA
        defaultMNpcCardShouldNotBeFound("stamina.equals=" + UPDATED_STAMINA);
    }

    @Test
    @Transactional
    public void getAllMNpcCardsByStaminaIsInShouldWork() throws Exception {
        // Initialize the database
        mNpcCardRepository.saveAndFlush(mNpcCard);

        // Get all the mNpcCardList where stamina in DEFAULT_STAMINA or UPDATED_STAMINA
        defaultMNpcCardShouldBeFound("stamina.in=" + DEFAULT_STAMINA + "," + UPDATED_STAMINA);

        // Get all the mNpcCardList where stamina equals to UPDATED_STAMINA
        defaultMNpcCardShouldNotBeFound("stamina.in=" + UPDATED_STAMINA);
    }

    @Test
    @Transactional
    public void getAllMNpcCardsByStaminaIsNullOrNotNull() throws Exception {
        // Initialize the database
        mNpcCardRepository.saveAndFlush(mNpcCard);

        // Get all the mNpcCardList where stamina is not null
        defaultMNpcCardShouldBeFound("stamina.specified=true");

        // Get all the mNpcCardList where stamina is null
        defaultMNpcCardShouldNotBeFound("stamina.specified=false");
    }

    @Test
    @Transactional
    public void getAllMNpcCardsByStaminaIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mNpcCardRepository.saveAndFlush(mNpcCard);

        // Get all the mNpcCardList where stamina greater than or equals to DEFAULT_STAMINA
        defaultMNpcCardShouldBeFound("stamina.greaterOrEqualThan=" + DEFAULT_STAMINA);

        // Get all the mNpcCardList where stamina greater than or equals to UPDATED_STAMINA
        defaultMNpcCardShouldNotBeFound("stamina.greaterOrEqualThan=" + UPDATED_STAMINA);
    }

    @Test
    @Transactional
    public void getAllMNpcCardsByStaminaIsLessThanSomething() throws Exception {
        // Initialize the database
        mNpcCardRepository.saveAndFlush(mNpcCard);

        // Get all the mNpcCardList where stamina less than or equals to DEFAULT_STAMINA
        defaultMNpcCardShouldNotBeFound("stamina.lessThan=" + DEFAULT_STAMINA);

        // Get all the mNpcCardList where stamina less than or equals to UPDATED_STAMINA
        defaultMNpcCardShouldBeFound("stamina.lessThan=" + UPDATED_STAMINA);
    }


    @Test
    @Transactional
    public void getAllMNpcCardsByDribbleIsEqualToSomething() throws Exception {
        // Initialize the database
        mNpcCardRepository.saveAndFlush(mNpcCard);

        // Get all the mNpcCardList where dribble equals to DEFAULT_DRIBBLE
        defaultMNpcCardShouldBeFound("dribble.equals=" + DEFAULT_DRIBBLE);

        // Get all the mNpcCardList where dribble equals to UPDATED_DRIBBLE
        defaultMNpcCardShouldNotBeFound("dribble.equals=" + UPDATED_DRIBBLE);
    }

    @Test
    @Transactional
    public void getAllMNpcCardsByDribbleIsInShouldWork() throws Exception {
        // Initialize the database
        mNpcCardRepository.saveAndFlush(mNpcCard);

        // Get all the mNpcCardList where dribble in DEFAULT_DRIBBLE or UPDATED_DRIBBLE
        defaultMNpcCardShouldBeFound("dribble.in=" + DEFAULT_DRIBBLE + "," + UPDATED_DRIBBLE);

        // Get all the mNpcCardList where dribble equals to UPDATED_DRIBBLE
        defaultMNpcCardShouldNotBeFound("dribble.in=" + UPDATED_DRIBBLE);
    }

    @Test
    @Transactional
    public void getAllMNpcCardsByDribbleIsNullOrNotNull() throws Exception {
        // Initialize the database
        mNpcCardRepository.saveAndFlush(mNpcCard);

        // Get all the mNpcCardList where dribble is not null
        defaultMNpcCardShouldBeFound("dribble.specified=true");

        // Get all the mNpcCardList where dribble is null
        defaultMNpcCardShouldNotBeFound("dribble.specified=false");
    }

    @Test
    @Transactional
    public void getAllMNpcCardsByDribbleIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mNpcCardRepository.saveAndFlush(mNpcCard);

        // Get all the mNpcCardList where dribble greater than or equals to DEFAULT_DRIBBLE
        defaultMNpcCardShouldBeFound("dribble.greaterOrEqualThan=" + DEFAULT_DRIBBLE);

        // Get all the mNpcCardList where dribble greater than or equals to UPDATED_DRIBBLE
        defaultMNpcCardShouldNotBeFound("dribble.greaterOrEqualThan=" + UPDATED_DRIBBLE);
    }

    @Test
    @Transactional
    public void getAllMNpcCardsByDribbleIsLessThanSomething() throws Exception {
        // Initialize the database
        mNpcCardRepository.saveAndFlush(mNpcCard);

        // Get all the mNpcCardList where dribble less than or equals to DEFAULT_DRIBBLE
        defaultMNpcCardShouldNotBeFound("dribble.lessThan=" + DEFAULT_DRIBBLE);

        // Get all the mNpcCardList where dribble less than or equals to UPDATED_DRIBBLE
        defaultMNpcCardShouldBeFound("dribble.lessThan=" + UPDATED_DRIBBLE);
    }


    @Test
    @Transactional
    public void getAllMNpcCardsByShootIsEqualToSomething() throws Exception {
        // Initialize the database
        mNpcCardRepository.saveAndFlush(mNpcCard);

        // Get all the mNpcCardList where shoot equals to DEFAULT_SHOOT
        defaultMNpcCardShouldBeFound("shoot.equals=" + DEFAULT_SHOOT);

        // Get all the mNpcCardList where shoot equals to UPDATED_SHOOT
        defaultMNpcCardShouldNotBeFound("shoot.equals=" + UPDATED_SHOOT);
    }

    @Test
    @Transactional
    public void getAllMNpcCardsByShootIsInShouldWork() throws Exception {
        // Initialize the database
        mNpcCardRepository.saveAndFlush(mNpcCard);

        // Get all the mNpcCardList where shoot in DEFAULT_SHOOT or UPDATED_SHOOT
        defaultMNpcCardShouldBeFound("shoot.in=" + DEFAULT_SHOOT + "," + UPDATED_SHOOT);

        // Get all the mNpcCardList where shoot equals to UPDATED_SHOOT
        defaultMNpcCardShouldNotBeFound("shoot.in=" + UPDATED_SHOOT);
    }

    @Test
    @Transactional
    public void getAllMNpcCardsByShootIsNullOrNotNull() throws Exception {
        // Initialize the database
        mNpcCardRepository.saveAndFlush(mNpcCard);

        // Get all the mNpcCardList where shoot is not null
        defaultMNpcCardShouldBeFound("shoot.specified=true");

        // Get all the mNpcCardList where shoot is null
        defaultMNpcCardShouldNotBeFound("shoot.specified=false");
    }

    @Test
    @Transactional
    public void getAllMNpcCardsByShootIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mNpcCardRepository.saveAndFlush(mNpcCard);

        // Get all the mNpcCardList where shoot greater than or equals to DEFAULT_SHOOT
        defaultMNpcCardShouldBeFound("shoot.greaterOrEqualThan=" + DEFAULT_SHOOT);

        // Get all the mNpcCardList where shoot greater than or equals to UPDATED_SHOOT
        defaultMNpcCardShouldNotBeFound("shoot.greaterOrEqualThan=" + UPDATED_SHOOT);
    }

    @Test
    @Transactional
    public void getAllMNpcCardsByShootIsLessThanSomething() throws Exception {
        // Initialize the database
        mNpcCardRepository.saveAndFlush(mNpcCard);

        // Get all the mNpcCardList where shoot less than or equals to DEFAULT_SHOOT
        defaultMNpcCardShouldNotBeFound("shoot.lessThan=" + DEFAULT_SHOOT);

        // Get all the mNpcCardList where shoot less than or equals to UPDATED_SHOOT
        defaultMNpcCardShouldBeFound("shoot.lessThan=" + UPDATED_SHOOT);
    }


    @Test
    @Transactional
    public void getAllMNpcCardsByBallPassIsEqualToSomething() throws Exception {
        // Initialize the database
        mNpcCardRepository.saveAndFlush(mNpcCard);

        // Get all the mNpcCardList where ballPass equals to DEFAULT_BALL_PASS
        defaultMNpcCardShouldBeFound("ballPass.equals=" + DEFAULT_BALL_PASS);

        // Get all the mNpcCardList where ballPass equals to UPDATED_BALL_PASS
        defaultMNpcCardShouldNotBeFound("ballPass.equals=" + UPDATED_BALL_PASS);
    }

    @Test
    @Transactional
    public void getAllMNpcCardsByBallPassIsInShouldWork() throws Exception {
        // Initialize the database
        mNpcCardRepository.saveAndFlush(mNpcCard);

        // Get all the mNpcCardList where ballPass in DEFAULT_BALL_PASS or UPDATED_BALL_PASS
        defaultMNpcCardShouldBeFound("ballPass.in=" + DEFAULT_BALL_PASS + "," + UPDATED_BALL_PASS);

        // Get all the mNpcCardList where ballPass equals to UPDATED_BALL_PASS
        defaultMNpcCardShouldNotBeFound("ballPass.in=" + UPDATED_BALL_PASS);
    }

    @Test
    @Transactional
    public void getAllMNpcCardsByBallPassIsNullOrNotNull() throws Exception {
        // Initialize the database
        mNpcCardRepository.saveAndFlush(mNpcCard);

        // Get all the mNpcCardList where ballPass is not null
        defaultMNpcCardShouldBeFound("ballPass.specified=true");

        // Get all the mNpcCardList where ballPass is null
        defaultMNpcCardShouldNotBeFound("ballPass.specified=false");
    }

    @Test
    @Transactional
    public void getAllMNpcCardsByBallPassIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mNpcCardRepository.saveAndFlush(mNpcCard);

        // Get all the mNpcCardList where ballPass greater than or equals to DEFAULT_BALL_PASS
        defaultMNpcCardShouldBeFound("ballPass.greaterOrEqualThan=" + DEFAULT_BALL_PASS);

        // Get all the mNpcCardList where ballPass greater than or equals to UPDATED_BALL_PASS
        defaultMNpcCardShouldNotBeFound("ballPass.greaterOrEqualThan=" + UPDATED_BALL_PASS);
    }

    @Test
    @Transactional
    public void getAllMNpcCardsByBallPassIsLessThanSomething() throws Exception {
        // Initialize the database
        mNpcCardRepository.saveAndFlush(mNpcCard);

        // Get all the mNpcCardList where ballPass less than or equals to DEFAULT_BALL_PASS
        defaultMNpcCardShouldNotBeFound("ballPass.lessThan=" + DEFAULT_BALL_PASS);

        // Get all the mNpcCardList where ballPass less than or equals to UPDATED_BALL_PASS
        defaultMNpcCardShouldBeFound("ballPass.lessThan=" + UPDATED_BALL_PASS);
    }


    @Test
    @Transactional
    public void getAllMNpcCardsByTackleIsEqualToSomething() throws Exception {
        // Initialize the database
        mNpcCardRepository.saveAndFlush(mNpcCard);

        // Get all the mNpcCardList where tackle equals to DEFAULT_TACKLE
        defaultMNpcCardShouldBeFound("tackle.equals=" + DEFAULT_TACKLE);

        // Get all the mNpcCardList where tackle equals to UPDATED_TACKLE
        defaultMNpcCardShouldNotBeFound("tackle.equals=" + UPDATED_TACKLE);
    }

    @Test
    @Transactional
    public void getAllMNpcCardsByTackleIsInShouldWork() throws Exception {
        // Initialize the database
        mNpcCardRepository.saveAndFlush(mNpcCard);

        // Get all the mNpcCardList where tackle in DEFAULT_TACKLE or UPDATED_TACKLE
        defaultMNpcCardShouldBeFound("tackle.in=" + DEFAULT_TACKLE + "," + UPDATED_TACKLE);

        // Get all the mNpcCardList where tackle equals to UPDATED_TACKLE
        defaultMNpcCardShouldNotBeFound("tackle.in=" + UPDATED_TACKLE);
    }

    @Test
    @Transactional
    public void getAllMNpcCardsByTackleIsNullOrNotNull() throws Exception {
        // Initialize the database
        mNpcCardRepository.saveAndFlush(mNpcCard);

        // Get all the mNpcCardList where tackle is not null
        defaultMNpcCardShouldBeFound("tackle.specified=true");

        // Get all the mNpcCardList where tackle is null
        defaultMNpcCardShouldNotBeFound("tackle.specified=false");
    }

    @Test
    @Transactional
    public void getAllMNpcCardsByTackleIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mNpcCardRepository.saveAndFlush(mNpcCard);

        // Get all the mNpcCardList where tackle greater than or equals to DEFAULT_TACKLE
        defaultMNpcCardShouldBeFound("tackle.greaterOrEqualThan=" + DEFAULT_TACKLE);

        // Get all the mNpcCardList where tackle greater than or equals to UPDATED_TACKLE
        defaultMNpcCardShouldNotBeFound("tackle.greaterOrEqualThan=" + UPDATED_TACKLE);
    }

    @Test
    @Transactional
    public void getAllMNpcCardsByTackleIsLessThanSomething() throws Exception {
        // Initialize the database
        mNpcCardRepository.saveAndFlush(mNpcCard);

        // Get all the mNpcCardList where tackle less than or equals to DEFAULT_TACKLE
        defaultMNpcCardShouldNotBeFound("tackle.lessThan=" + DEFAULT_TACKLE);

        // Get all the mNpcCardList where tackle less than or equals to UPDATED_TACKLE
        defaultMNpcCardShouldBeFound("tackle.lessThan=" + UPDATED_TACKLE);
    }


    @Test
    @Transactional
    public void getAllMNpcCardsByBlockIsEqualToSomething() throws Exception {
        // Initialize the database
        mNpcCardRepository.saveAndFlush(mNpcCard);

        // Get all the mNpcCardList where block equals to DEFAULT_BLOCK
        defaultMNpcCardShouldBeFound("block.equals=" + DEFAULT_BLOCK);

        // Get all the mNpcCardList where block equals to UPDATED_BLOCK
        defaultMNpcCardShouldNotBeFound("block.equals=" + UPDATED_BLOCK);
    }

    @Test
    @Transactional
    public void getAllMNpcCardsByBlockIsInShouldWork() throws Exception {
        // Initialize the database
        mNpcCardRepository.saveAndFlush(mNpcCard);

        // Get all the mNpcCardList where block in DEFAULT_BLOCK or UPDATED_BLOCK
        defaultMNpcCardShouldBeFound("block.in=" + DEFAULT_BLOCK + "," + UPDATED_BLOCK);

        // Get all the mNpcCardList where block equals to UPDATED_BLOCK
        defaultMNpcCardShouldNotBeFound("block.in=" + UPDATED_BLOCK);
    }

    @Test
    @Transactional
    public void getAllMNpcCardsByBlockIsNullOrNotNull() throws Exception {
        // Initialize the database
        mNpcCardRepository.saveAndFlush(mNpcCard);

        // Get all the mNpcCardList where block is not null
        defaultMNpcCardShouldBeFound("block.specified=true");

        // Get all the mNpcCardList where block is null
        defaultMNpcCardShouldNotBeFound("block.specified=false");
    }

    @Test
    @Transactional
    public void getAllMNpcCardsByBlockIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mNpcCardRepository.saveAndFlush(mNpcCard);

        // Get all the mNpcCardList where block greater than or equals to DEFAULT_BLOCK
        defaultMNpcCardShouldBeFound("block.greaterOrEqualThan=" + DEFAULT_BLOCK);

        // Get all the mNpcCardList where block greater than or equals to UPDATED_BLOCK
        defaultMNpcCardShouldNotBeFound("block.greaterOrEqualThan=" + UPDATED_BLOCK);
    }

    @Test
    @Transactional
    public void getAllMNpcCardsByBlockIsLessThanSomething() throws Exception {
        // Initialize the database
        mNpcCardRepository.saveAndFlush(mNpcCard);

        // Get all the mNpcCardList where block less than or equals to DEFAULT_BLOCK
        defaultMNpcCardShouldNotBeFound("block.lessThan=" + DEFAULT_BLOCK);

        // Get all the mNpcCardList where block less than or equals to UPDATED_BLOCK
        defaultMNpcCardShouldBeFound("block.lessThan=" + UPDATED_BLOCK);
    }


    @Test
    @Transactional
    public void getAllMNpcCardsByInterceptIsEqualToSomething() throws Exception {
        // Initialize the database
        mNpcCardRepository.saveAndFlush(mNpcCard);

        // Get all the mNpcCardList where intercept equals to DEFAULT_INTERCEPT
        defaultMNpcCardShouldBeFound("intercept.equals=" + DEFAULT_INTERCEPT);

        // Get all the mNpcCardList where intercept equals to UPDATED_INTERCEPT
        defaultMNpcCardShouldNotBeFound("intercept.equals=" + UPDATED_INTERCEPT);
    }

    @Test
    @Transactional
    public void getAllMNpcCardsByInterceptIsInShouldWork() throws Exception {
        // Initialize the database
        mNpcCardRepository.saveAndFlush(mNpcCard);

        // Get all the mNpcCardList where intercept in DEFAULT_INTERCEPT or UPDATED_INTERCEPT
        defaultMNpcCardShouldBeFound("intercept.in=" + DEFAULT_INTERCEPT + "," + UPDATED_INTERCEPT);

        // Get all the mNpcCardList where intercept equals to UPDATED_INTERCEPT
        defaultMNpcCardShouldNotBeFound("intercept.in=" + UPDATED_INTERCEPT);
    }

    @Test
    @Transactional
    public void getAllMNpcCardsByInterceptIsNullOrNotNull() throws Exception {
        // Initialize the database
        mNpcCardRepository.saveAndFlush(mNpcCard);

        // Get all the mNpcCardList where intercept is not null
        defaultMNpcCardShouldBeFound("intercept.specified=true");

        // Get all the mNpcCardList where intercept is null
        defaultMNpcCardShouldNotBeFound("intercept.specified=false");
    }

    @Test
    @Transactional
    public void getAllMNpcCardsByInterceptIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mNpcCardRepository.saveAndFlush(mNpcCard);

        // Get all the mNpcCardList where intercept greater than or equals to DEFAULT_INTERCEPT
        defaultMNpcCardShouldBeFound("intercept.greaterOrEqualThan=" + DEFAULT_INTERCEPT);

        // Get all the mNpcCardList where intercept greater than or equals to UPDATED_INTERCEPT
        defaultMNpcCardShouldNotBeFound("intercept.greaterOrEqualThan=" + UPDATED_INTERCEPT);
    }

    @Test
    @Transactional
    public void getAllMNpcCardsByInterceptIsLessThanSomething() throws Exception {
        // Initialize the database
        mNpcCardRepository.saveAndFlush(mNpcCard);

        // Get all the mNpcCardList where intercept less than or equals to DEFAULT_INTERCEPT
        defaultMNpcCardShouldNotBeFound("intercept.lessThan=" + DEFAULT_INTERCEPT);

        // Get all the mNpcCardList where intercept less than or equals to UPDATED_INTERCEPT
        defaultMNpcCardShouldBeFound("intercept.lessThan=" + UPDATED_INTERCEPT);
    }


    @Test
    @Transactional
    public void getAllMNpcCardsBySpeedIsEqualToSomething() throws Exception {
        // Initialize the database
        mNpcCardRepository.saveAndFlush(mNpcCard);

        // Get all the mNpcCardList where speed equals to DEFAULT_SPEED
        defaultMNpcCardShouldBeFound("speed.equals=" + DEFAULT_SPEED);

        // Get all the mNpcCardList where speed equals to UPDATED_SPEED
        defaultMNpcCardShouldNotBeFound("speed.equals=" + UPDATED_SPEED);
    }

    @Test
    @Transactional
    public void getAllMNpcCardsBySpeedIsInShouldWork() throws Exception {
        // Initialize the database
        mNpcCardRepository.saveAndFlush(mNpcCard);

        // Get all the mNpcCardList where speed in DEFAULT_SPEED or UPDATED_SPEED
        defaultMNpcCardShouldBeFound("speed.in=" + DEFAULT_SPEED + "," + UPDATED_SPEED);

        // Get all the mNpcCardList where speed equals to UPDATED_SPEED
        defaultMNpcCardShouldNotBeFound("speed.in=" + UPDATED_SPEED);
    }

    @Test
    @Transactional
    public void getAllMNpcCardsBySpeedIsNullOrNotNull() throws Exception {
        // Initialize the database
        mNpcCardRepository.saveAndFlush(mNpcCard);

        // Get all the mNpcCardList where speed is not null
        defaultMNpcCardShouldBeFound("speed.specified=true");

        // Get all the mNpcCardList where speed is null
        defaultMNpcCardShouldNotBeFound("speed.specified=false");
    }

    @Test
    @Transactional
    public void getAllMNpcCardsBySpeedIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mNpcCardRepository.saveAndFlush(mNpcCard);

        // Get all the mNpcCardList where speed greater than or equals to DEFAULT_SPEED
        defaultMNpcCardShouldBeFound("speed.greaterOrEqualThan=" + DEFAULT_SPEED);

        // Get all the mNpcCardList where speed greater than or equals to UPDATED_SPEED
        defaultMNpcCardShouldNotBeFound("speed.greaterOrEqualThan=" + UPDATED_SPEED);
    }

    @Test
    @Transactional
    public void getAllMNpcCardsBySpeedIsLessThanSomething() throws Exception {
        // Initialize the database
        mNpcCardRepository.saveAndFlush(mNpcCard);

        // Get all the mNpcCardList where speed less than or equals to DEFAULT_SPEED
        defaultMNpcCardShouldNotBeFound("speed.lessThan=" + DEFAULT_SPEED);

        // Get all the mNpcCardList where speed less than or equals to UPDATED_SPEED
        defaultMNpcCardShouldBeFound("speed.lessThan=" + UPDATED_SPEED);
    }


    @Test
    @Transactional
    public void getAllMNpcCardsByPowerIsEqualToSomething() throws Exception {
        // Initialize the database
        mNpcCardRepository.saveAndFlush(mNpcCard);

        // Get all the mNpcCardList where power equals to DEFAULT_POWER
        defaultMNpcCardShouldBeFound("power.equals=" + DEFAULT_POWER);

        // Get all the mNpcCardList where power equals to UPDATED_POWER
        defaultMNpcCardShouldNotBeFound("power.equals=" + UPDATED_POWER);
    }

    @Test
    @Transactional
    public void getAllMNpcCardsByPowerIsInShouldWork() throws Exception {
        // Initialize the database
        mNpcCardRepository.saveAndFlush(mNpcCard);

        // Get all the mNpcCardList where power in DEFAULT_POWER or UPDATED_POWER
        defaultMNpcCardShouldBeFound("power.in=" + DEFAULT_POWER + "," + UPDATED_POWER);

        // Get all the mNpcCardList where power equals to UPDATED_POWER
        defaultMNpcCardShouldNotBeFound("power.in=" + UPDATED_POWER);
    }

    @Test
    @Transactional
    public void getAllMNpcCardsByPowerIsNullOrNotNull() throws Exception {
        // Initialize the database
        mNpcCardRepository.saveAndFlush(mNpcCard);

        // Get all the mNpcCardList where power is not null
        defaultMNpcCardShouldBeFound("power.specified=true");

        // Get all the mNpcCardList where power is null
        defaultMNpcCardShouldNotBeFound("power.specified=false");
    }

    @Test
    @Transactional
    public void getAllMNpcCardsByPowerIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mNpcCardRepository.saveAndFlush(mNpcCard);

        // Get all the mNpcCardList where power greater than or equals to DEFAULT_POWER
        defaultMNpcCardShouldBeFound("power.greaterOrEqualThan=" + DEFAULT_POWER);

        // Get all the mNpcCardList where power greater than or equals to UPDATED_POWER
        defaultMNpcCardShouldNotBeFound("power.greaterOrEqualThan=" + UPDATED_POWER);
    }

    @Test
    @Transactional
    public void getAllMNpcCardsByPowerIsLessThanSomething() throws Exception {
        // Initialize the database
        mNpcCardRepository.saveAndFlush(mNpcCard);

        // Get all the mNpcCardList where power less than or equals to DEFAULT_POWER
        defaultMNpcCardShouldNotBeFound("power.lessThan=" + DEFAULT_POWER);

        // Get all the mNpcCardList where power less than or equals to UPDATED_POWER
        defaultMNpcCardShouldBeFound("power.lessThan=" + UPDATED_POWER);
    }


    @Test
    @Transactional
    public void getAllMNpcCardsByTechniqueIsEqualToSomething() throws Exception {
        // Initialize the database
        mNpcCardRepository.saveAndFlush(mNpcCard);

        // Get all the mNpcCardList where technique equals to DEFAULT_TECHNIQUE
        defaultMNpcCardShouldBeFound("technique.equals=" + DEFAULT_TECHNIQUE);

        // Get all the mNpcCardList where technique equals to UPDATED_TECHNIQUE
        defaultMNpcCardShouldNotBeFound("technique.equals=" + UPDATED_TECHNIQUE);
    }

    @Test
    @Transactional
    public void getAllMNpcCardsByTechniqueIsInShouldWork() throws Exception {
        // Initialize the database
        mNpcCardRepository.saveAndFlush(mNpcCard);

        // Get all the mNpcCardList where technique in DEFAULT_TECHNIQUE or UPDATED_TECHNIQUE
        defaultMNpcCardShouldBeFound("technique.in=" + DEFAULT_TECHNIQUE + "," + UPDATED_TECHNIQUE);

        // Get all the mNpcCardList where technique equals to UPDATED_TECHNIQUE
        defaultMNpcCardShouldNotBeFound("technique.in=" + UPDATED_TECHNIQUE);
    }

    @Test
    @Transactional
    public void getAllMNpcCardsByTechniqueIsNullOrNotNull() throws Exception {
        // Initialize the database
        mNpcCardRepository.saveAndFlush(mNpcCard);

        // Get all the mNpcCardList where technique is not null
        defaultMNpcCardShouldBeFound("technique.specified=true");

        // Get all the mNpcCardList where technique is null
        defaultMNpcCardShouldNotBeFound("technique.specified=false");
    }

    @Test
    @Transactional
    public void getAllMNpcCardsByTechniqueIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mNpcCardRepository.saveAndFlush(mNpcCard);

        // Get all the mNpcCardList where technique greater than or equals to DEFAULT_TECHNIQUE
        defaultMNpcCardShouldBeFound("technique.greaterOrEqualThan=" + DEFAULT_TECHNIQUE);

        // Get all the mNpcCardList where technique greater than or equals to UPDATED_TECHNIQUE
        defaultMNpcCardShouldNotBeFound("technique.greaterOrEqualThan=" + UPDATED_TECHNIQUE);
    }

    @Test
    @Transactional
    public void getAllMNpcCardsByTechniqueIsLessThanSomething() throws Exception {
        // Initialize the database
        mNpcCardRepository.saveAndFlush(mNpcCard);

        // Get all the mNpcCardList where technique less than or equals to DEFAULT_TECHNIQUE
        defaultMNpcCardShouldNotBeFound("technique.lessThan=" + DEFAULT_TECHNIQUE);

        // Get all the mNpcCardList where technique less than or equals to UPDATED_TECHNIQUE
        defaultMNpcCardShouldBeFound("technique.lessThan=" + UPDATED_TECHNIQUE);
    }


    @Test
    @Transactional
    public void getAllMNpcCardsByPunchingIsEqualToSomething() throws Exception {
        // Initialize the database
        mNpcCardRepository.saveAndFlush(mNpcCard);

        // Get all the mNpcCardList where punching equals to DEFAULT_PUNCHING
        defaultMNpcCardShouldBeFound("punching.equals=" + DEFAULT_PUNCHING);

        // Get all the mNpcCardList where punching equals to UPDATED_PUNCHING
        defaultMNpcCardShouldNotBeFound("punching.equals=" + UPDATED_PUNCHING);
    }

    @Test
    @Transactional
    public void getAllMNpcCardsByPunchingIsInShouldWork() throws Exception {
        // Initialize the database
        mNpcCardRepository.saveAndFlush(mNpcCard);

        // Get all the mNpcCardList where punching in DEFAULT_PUNCHING or UPDATED_PUNCHING
        defaultMNpcCardShouldBeFound("punching.in=" + DEFAULT_PUNCHING + "," + UPDATED_PUNCHING);

        // Get all the mNpcCardList where punching equals to UPDATED_PUNCHING
        defaultMNpcCardShouldNotBeFound("punching.in=" + UPDATED_PUNCHING);
    }

    @Test
    @Transactional
    public void getAllMNpcCardsByPunchingIsNullOrNotNull() throws Exception {
        // Initialize the database
        mNpcCardRepository.saveAndFlush(mNpcCard);

        // Get all the mNpcCardList where punching is not null
        defaultMNpcCardShouldBeFound("punching.specified=true");

        // Get all the mNpcCardList where punching is null
        defaultMNpcCardShouldNotBeFound("punching.specified=false");
    }

    @Test
    @Transactional
    public void getAllMNpcCardsByPunchingIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mNpcCardRepository.saveAndFlush(mNpcCard);

        // Get all the mNpcCardList where punching greater than or equals to DEFAULT_PUNCHING
        defaultMNpcCardShouldBeFound("punching.greaterOrEqualThan=" + DEFAULT_PUNCHING);

        // Get all the mNpcCardList where punching greater than or equals to UPDATED_PUNCHING
        defaultMNpcCardShouldNotBeFound("punching.greaterOrEqualThan=" + UPDATED_PUNCHING);
    }

    @Test
    @Transactional
    public void getAllMNpcCardsByPunchingIsLessThanSomething() throws Exception {
        // Initialize the database
        mNpcCardRepository.saveAndFlush(mNpcCard);

        // Get all the mNpcCardList where punching less than or equals to DEFAULT_PUNCHING
        defaultMNpcCardShouldNotBeFound("punching.lessThan=" + DEFAULT_PUNCHING);

        // Get all the mNpcCardList where punching less than or equals to UPDATED_PUNCHING
        defaultMNpcCardShouldBeFound("punching.lessThan=" + UPDATED_PUNCHING);
    }


    @Test
    @Transactional
    public void getAllMNpcCardsByCatchingIsEqualToSomething() throws Exception {
        // Initialize the database
        mNpcCardRepository.saveAndFlush(mNpcCard);

        // Get all the mNpcCardList where catching equals to DEFAULT_CATCHING
        defaultMNpcCardShouldBeFound("catching.equals=" + DEFAULT_CATCHING);

        // Get all the mNpcCardList where catching equals to UPDATED_CATCHING
        defaultMNpcCardShouldNotBeFound("catching.equals=" + UPDATED_CATCHING);
    }

    @Test
    @Transactional
    public void getAllMNpcCardsByCatchingIsInShouldWork() throws Exception {
        // Initialize the database
        mNpcCardRepository.saveAndFlush(mNpcCard);

        // Get all the mNpcCardList where catching in DEFAULT_CATCHING or UPDATED_CATCHING
        defaultMNpcCardShouldBeFound("catching.in=" + DEFAULT_CATCHING + "," + UPDATED_CATCHING);

        // Get all the mNpcCardList where catching equals to UPDATED_CATCHING
        defaultMNpcCardShouldNotBeFound("catching.in=" + UPDATED_CATCHING);
    }

    @Test
    @Transactional
    public void getAllMNpcCardsByCatchingIsNullOrNotNull() throws Exception {
        // Initialize the database
        mNpcCardRepository.saveAndFlush(mNpcCard);

        // Get all the mNpcCardList where catching is not null
        defaultMNpcCardShouldBeFound("catching.specified=true");

        // Get all the mNpcCardList where catching is null
        defaultMNpcCardShouldNotBeFound("catching.specified=false");
    }

    @Test
    @Transactional
    public void getAllMNpcCardsByCatchingIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mNpcCardRepository.saveAndFlush(mNpcCard);

        // Get all the mNpcCardList where catching greater than or equals to DEFAULT_CATCHING
        defaultMNpcCardShouldBeFound("catching.greaterOrEqualThan=" + DEFAULT_CATCHING);

        // Get all the mNpcCardList where catching greater than or equals to UPDATED_CATCHING
        defaultMNpcCardShouldNotBeFound("catching.greaterOrEqualThan=" + UPDATED_CATCHING);
    }

    @Test
    @Transactional
    public void getAllMNpcCardsByCatchingIsLessThanSomething() throws Exception {
        // Initialize the database
        mNpcCardRepository.saveAndFlush(mNpcCard);

        // Get all the mNpcCardList where catching less than or equals to DEFAULT_CATCHING
        defaultMNpcCardShouldNotBeFound("catching.lessThan=" + DEFAULT_CATCHING);

        // Get all the mNpcCardList where catching less than or equals to UPDATED_CATCHING
        defaultMNpcCardShouldBeFound("catching.lessThan=" + UPDATED_CATCHING);
    }


    @Test
    @Transactional
    public void getAllMNpcCardsByHighBallBonusIsEqualToSomething() throws Exception {
        // Initialize the database
        mNpcCardRepository.saveAndFlush(mNpcCard);

        // Get all the mNpcCardList where highBallBonus equals to DEFAULT_HIGH_BALL_BONUS
        defaultMNpcCardShouldBeFound("highBallBonus.equals=" + DEFAULT_HIGH_BALL_BONUS);

        // Get all the mNpcCardList where highBallBonus equals to UPDATED_HIGH_BALL_BONUS
        defaultMNpcCardShouldNotBeFound("highBallBonus.equals=" + UPDATED_HIGH_BALL_BONUS);
    }

    @Test
    @Transactional
    public void getAllMNpcCardsByHighBallBonusIsInShouldWork() throws Exception {
        // Initialize the database
        mNpcCardRepository.saveAndFlush(mNpcCard);

        // Get all the mNpcCardList where highBallBonus in DEFAULT_HIGH_BALL_BONUS or UPDATED_HIGH_BALL_BONUS
        defaultMNpcCardShouldBeFound("highBallBonus.in=" + DEFAULT_HIGH_BALL_BONUS + "," + UPDATED_HIGH_BALL_BONUS);

        // Get all the mNpcCardList where highBallBonus equals to UPDATED_HIGH_BALL_BONUS
        defaultMNpcCardShouldNotBeFound("highBallBonus.in=" + UPDATED_HIGH_BALL_BONUS);
    }

    @Test
    @Transactional
    public void getAllMNpcCardsByHighBallBonusIsNullOrNotNull() throws Exception {
        // Initialize the database
        mNpcCardRepository.saveAndFlush(mNpcCard);

        // Get all the mNpcCardList where highBallBonus is not null
        defaultMNpcCardShouldBeFound("highBallBonus.specified=true");

        // Get all the mNpcCardList where highBallBonus is null
        defaultMNpcCardShouldNotBeFound("highBallBonus.specified=false");
    }

    @Test
    @Transactional
    public void getAllMNpcCardsByHighBallBonusIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mNpcCardRepository.saveAndFlush(mNpcCard);

        // Get all the mNpcCardList where highBallBonus greater than or equals to DEFAULT_HIGH_BALL_BONUS
        defaultMNpcCardShouldBeFound("highBallBonus.greaterOrEqualThan=" + DEFAULT_HIGH_BALL_BONUS);

        // Get all the mNpcCardList where highBallBonus greater than or equals to UPDATED_HIGH_BALL_BONUS
        defaultMNpcCardShouldNotBeFound("highBallBonus.greaterOrEqualThan=" + UPDATED_HIGH_BALL_BONUS);
    }

    @Test
    @Transactional
    public void getAllMNpcCardsByHighBallBonusIsLessThanSomething() throws Exception {
        // Initialize the database
        mNpcCardRepository.saveAndFlush(mNpcCard);

        // Get all the mNpcCardList where highBallBonus less than or equals to DEFAULT_HIGH_BALL_BONUS
        defaultMNpcCardShouldNotBeFound("highBallBonus.lessThan=" + DEFAULT_HIGH_BALL_BONUS);

        // Get all the mNpcCardList where highBallBonus less than or equals to UPDATED_HIGH_BALL_BONUS
        defaultMNpcCardShouldBeFound("highBallBonus.lessThan=" + UPDATED_HIGH_BALL_BONUS);
    }


    @Test
    @Transactional
    public void getAllMNpcCardsByLowBallBonusIsEqualToSomething() throws Exception {
        // Initialize the database
        mNpcCardRepository.saveAndFlush(mNpcCard);

        // Get all the mNpcCardList where lowBallBonus equals to DEFAULT_LOW_BALL_BONUS
        defaultMNpcCardShouldBeFound("lowBallBonus.equals=" + DEFAULT_LOW_BALL_BONUS);

        // Get all the mNpcCardList where lowBallBonus equals to UPDATED_LOW_BALL_BONUS
        defaultMNpcCardShouldNotBeFound("lowBallBonus.equals=" + UPDATED_LOW_BALL_BONUS);
    }

    @Test
    @Transactional
    public void getAllMNpcCardsByLowBallBonusIsInShouldWork() throws Exception {
        // Initialize the database
        mNpcCardRepository.saveAndFlush(mNpcCard);

        // Get all the mNpcCardList where lowBallBonus in DEFAULT_LOW_BALL_BONUS or UPDATED_LOW_BALL_BONUS
        defaultMNpcCardShouldBeFound("lowBallBonus.in=" + DEFAULT_LOW_BALL_BONUS + "," + UPDATED_LOW_BALL_BONUS);

        // Get all the mNpcCardList where lowBallBonus equals to UPDATED_LOW_BALL_BONUS
        defaultMNpcCardShouldNotBeFound("lowBallBonus.in=" + UPDATED_LOW_BALL_BONUS);
    }

    @Test
    @Transactional
    public void getAllMNpcCardsByLowBallBonusIsNullOrNotNull() throws Exception {
        // Initialize the database
        mNpcCardRepository.saveAndFlush(mNpcCard);

        // Get all the mNpcCardList where lowBallBonus is not null
        defaultMNpcCardShouldBeFound("lowBallBonus.specified=true");

        // Get all the mNpcCardList where lowBallBonus is null
        defaultMNpcCardShouldNotBeFound("lowBallBonus.specified=false");
    }

    @Test
    @Transactional
    public void getAllMNpcCardsByLowBallBonusIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mNpcCardRepository.saveAndFlush(mNpcCard);

        // Get all the mNpcCardList where lowBallBonus greater than or equals to DEFAULT_LOW_BALL_BONUS
        defaultMNpcCardShouldBeFound("lowBallBonus.greaterOrEqualThan=" + DEFAULT_LOW_BALL_BONUS);

        // Get all the mNpcCardList where lowBallBonus greater than or equals to UPDATED_LOW_BALL_BONUS
        defaultMNpcCardShouldNotBeFound("lowBallBonus.greaterOrEqualThan=" + UPDATED_LOW_BALL_BONUS);
    }

    @Test
    @Transactional
    public void getAllMNpcCardsByLowBallBonusIsLessThanSomething() throws Exception {
        // Initialize the database
        mNpcCardRepository.saveAndFlush(mNpcCard);

        // Get all the mNpcCardList where lowBallBonus less than or equals to DEFAULT_LOW_BALL_BONUS
        defaultMNpcCardShouldNotBeFound("lowBallBonus.lessThan=" + DEFAULT_LOW_BALL_BONUS);

        // Get all the mNpcCardList where lowBallBonus less than or equals to UPDATED_LOW_BALL_BONUS
        defaultMNpcCardShouldBeFound("lowBallBonus.lessThan=" + UPDATED_LOW_BALL_BONUS);
    }


    @Test
    @Transactional
    public void getAllMNpcCardsByPersonalityIdIsEqualToSomething() throws Exception {
        // Initialize the database
        mNpcCardRepository.saveAndFlush(mNpcCard);

        // Get all the mNpcCardList where personalityId equals to DEFAULT_PERSONALITY_ID
        defaultMNpcCardShouldBeFound("personalityId.equals=" + DEFAULT_PERSONALITY_ID);

        // Get all the mNpcCardList where personalityId equals to UPDATED_PERSONALITY_ID
        defaultMNpcCardShouldNotBeFound("personalityId.equals=" + UPDATED_PERSONALITY_ID);
    }

    @Test
    @Transactional
    public void getAllMNpcCardsByPersonalityIdIsInShouldWork() throws Exception {
        // Initialize the database
        mNpcCardRepository.saveAndFlush(mNpcCard);

        // Get all the mNpcCardList where personalityId in DEFAULT_PERSONALITY_ID or UPDATED_PERSONALITY_ID
        defaultMNpcCardShouldBeFound("personalityId.in=" + DEFAULT_PERSONALITY_ID + "," + UPDATED_PERSONALITY_ID);

        // Get all the mNpcCardList where personalityId equals to UPDATED_PERSONALITY_ID
        defaultMNpcCardShouldNotBeFound("personalityId.in=" + UPDATED_PERSONALITY_ID);
    }

    @Test
    @Transactional
    public void getAllMNpcCardsByPersonalityIdIsNullOrNotNull() throws Exception {
        // Initialize the database
        mNpcCardRepository.saveAndFlush(mNpcCard);

        // Get all the mNpcCardList where personalityId is not null
        defaultMNpcCardShouldBeFound("personalityId.specified=true");

        // Get all the mNpcCardList where personalityId is null
        defaultMNpcCardShouldNotBeFound("personalityId.specified=false");
    }

    @Test
    @Transactional
    public void getAllMNpcCardsByPersonalityIdIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mNpcCardRepository.saveAndFlush(mNpcCard);

        // Get all the mNpcCardList where personalityId greater than or equals to DEFAULT_PERSONALITY_ID
        defaultMNpcCardShouldBeFound("personalityId.greaterOrEqualThan=" + DEFAULT_PERSONALITY_ID);

        // Get all the mNpcCardList where personalityId greater than or equals to UPDATED_PERSONALITY_ID
        defaultMNpcCardShouldNotBeFound("personalityId.greaterOrEqualThan=" + UPDATED_PERSONALITY_ID);
    }

    @Test
    @Transactional
    public void getAllMNpcCardsByPersonalityIdIsLessThanSomething() throws Exception {
        // Initialize the database
        mNpcCardRepository.saveAndFlush(mNpcCard);

        // Get all the mNpcCardList where personalityId less than or equals to DEFAULT_PERSONALITY_ID
        defaultMNpcCardShouldNotBeFound("personalityId.lessThan=" + DEFAULT_PERSONALITY_ID);

        // Get all the mNpcCardList where personalityId less than or equals to UPDATED_PERSONALITY_ID
        defaultMNpcCardShouldBeFound("personalityId.lessThan=" + UPDATED_PERSONALITY_ID);
    }


    @Test
    @Transactional
    public void getAllMNpcCardsByUniformNoIsEqualToSomething() throws Exception {
        // Initialize the database
        mNpcCardRepository.saveAndFlush(mNpcCard);

        // Get all the mNpcCardList where uniformNo equals to DEFAULT_UNIFORM_NO
        defaultMNpcCardShouldBeFound("uniformNo.equals=" + DEFAULT_UNIFORM_NO);

        // Get all the mNpcCardList where uniformNo equals to UPDATED_UNIFORM_NO
        defaultMNpcCardShouldNotBeFound("uniformNo.equals=" + UPDATED_UNIFORM_NO);
    }

    @Test
    @Transactional
    public void getAllMNpcCardsByUniformNoIsInShouldWork() throws Exception {
        // Initialize the database
        mNpcCardRepository.saveAndFlush(mNpcCard);

        // Get all the mNpcCardList where uniformNo in DEFAULT_UNIFORM_NO or UPDATED_UNIFORM_NO
        defaultMNpcCardShouldBeFound("uniformNo.in=" + DEFAULT_UNIFORM_NO + "," + UPDATED_UNIFORM_NO);

        // Get all the mNpcCardList where uniformNo equals to UPDATED_UNIFORM_NO
        defaultMNpcCardShouldNotBeFound("uniformNo.in=" + UPDATED_UNIFORM_NO);
    }

    @Test
    @Transactional
    public void getAllMNpcCardsByUniformNoIsNullOrNotNull() throws Exception {
        // Initialize the database
        mNpcCardRepository.saveAndFlush(mNpcCard);

        // Get all the mNpcCardList where uniformNo is not null
        defaultMNpcCardShouldBeFound("uniformNo.specified=true");

        // Get all the mNpcCardList where uniformNo is null
        defaultMNpcCardShouldNotBeFound("uniformNo.specified=false");
    }

    @Test
    @Transactional
    public void getAllMNpcCardsByUniformNoIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mNpcCardRepository.saveAndFlush(mNpcCard);

        // Get all the mNpcCardList where uniformNo greater than or equals to DEFAULT_UNIFORM_NO
        defaultMNpcCardShouldBeFound("uniformNo.greaterOrEqualThan=" + DEFAULT_UNIFORM_NO);

        // Get all the mNpcCardList where uniformNo greater than or equals to UPDATED_UNIFORM_NO
        defaultMNpcCardShouldNotBeFound("uniformNo.greaterOrEqualThan=" + UPDATED_UNIFORM_NO);
    }

    @Test
    @Transactional
    public void getAllMNpcCardsByUniformNoIsLessThanSomething() throws Exception {
        // Initialize the database
        mNpcCardRepository.saveAndFlush(mNpcCard);

        // Get all the mNpcCardList where uniformNo less than or equals to DEFAULT_UNIFORM_NO
        defaultMNpcCardShouldNotBeFound("uniformNo.lessThan=" + DEFAULT_UNIFORM_NO);

        // Get all the mNpcCardList where uniformNo less than or equals to UPDATED_UNIFORM_NO
        defaultMNpcCardShouldBeFound("uniformNo.lessThan=" + UPDATED_UNIFORM_NO);
    }


    @Test
    @Transactional
    public void getAllMNpcCardsByLevelGroupIdIsEqualToSomething() throws Exception {
        // Initialize the database
        mNpcCardRepository.saveAndFlush(mNpcCard);

        // Get all the mNpcCardList where levelGroupId equals to DEFAULT_LEVEL_GROUP_ID
        defaultMNpcCardShouldBeFound("levelGroupId.equals=" + DEFAULT_LEVEL_GROUP_ID);

        // Get all the mNpcCardList where levelGroupId equals to UPDATED_LEVEL_GROUP_ID
        defaultMNpcCardShouldNotBeFound("levelGroupId.equals=" + UPDATED_LEVEL_GROUP_ID);
    }

    @Test
    @Transactional
    public void getAllMNpcCardsByLevelGroupIdIsInShouldWork() throws Exception {
        // Initialize the database
        mNpcCardRepository.saveAndFlush(mNpcCard);

        // Get all the mNpcCardList where levelGroupId in DEFAULT_LEVEL_GROUP_ID or UPDATED_LEVEL_GROUP_ID
        defaultMNpcCardShouldBeFound("levelGroupId.in=" + DEFAULT_LEVEL_GROUP_ID + "," + UPDATED_LEVEL_GROUP_ID);

        // Get all the mNpcCardList where levelGroupId equals to UPDATED_LEVEL_GROUP_ID
        defaultMNpcCardShouldNotBeFound("levelGroupId.in=" + UPDATED_LEVEL_GROUP_ID);
    }

    @Test
    @Transactional
    public void getAllMNpcCardsByLevelGroupIdIsNullOrNotNull() throws Exception {
        // Initialize the database
        mNpcCardRepository.saveAndFlush(mNpcCard);

        // Get all the mNpcCardList where levelGroupId is not null
        defaultMNpcCardShouldBeFound("levelGroupId.specified=true");

        // Get all the mNpcCardList where levelGroupId is null
        defaultMNpcCardShouldNotBeFound("levelGroupId.specified=false");
    }

    @Test
    @Transactional
    public void getAllMNpcCardsByLevelGroupIdIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mNpcCardRepository.saveAndFlush(mNpcCard);

        // Get all the mNpcCardList where levelGroupId greater than or equals to DEFAULT_LEVEL_GROUP_ID
        defaultMNpcCardShouldBeFound("levelGroupId.greaterOrEqualThan=" + DEFAULT_LEVEL_GROUP_ID);

        // Get all the mNpcCardList where levelGroupId greater than or equals to UPDATED_LEVEL_GROUP_ID
        defaultMNpcCardShouldNotBeFound("levelGroupId.greaterOrEqualThan=" + UPDATED_LEVEL_GROUP_ID);
    }

    @Test
    @Transactional
    public void getAllMNpcCardsByLevelGroupIdIsLessThanSomething() throws Exception {
        // Initialize the database
        mNpcCardRepository.saveAndFlush(mNpcCard);

        // Get all the mNpcCardList where levelGroupId less than or equals to DEFAULT_LEVEL_GROUP_ID
        defaultMNpcCardShouldNotBeFound("levelGroupId.lessThan=" + DEFAULT_LEVEL_GROUP_ID);

        // Get all the mNpcCardList where levelGroupId less than or equals to UPDATED_LEVEL_GROUP_ID
        defaultMNpcCardShouldBeFound("levelGroupId.lessThan=" + UPDATED_LEVEL_GROUP_ID);
    }


    @Test
    @Transactional
    public void getAllMNpcCardsByIdIsEqualToSomething() throws Exception {
        // Get already existing entity
        MCharacter id = mNpcCard.getId();
        mNpcCardRepository.saveAndFlush(mNpcCard);
        Long idId = id.getId();

        // Get all the mNpcCardList where id equals to idId
        defaultMNpcCardShouldBeFound("idId.equals=" + idId);

        // Get all the mNpcCardList where id equals to idId + 1
        defaultMNpcCardShouldNotBeFound("idId.equals=" + (idId + 1));
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultMNpcCardShouldBeFound(String filter) throws Exception {
        restMNpcCardMockMvc.perform(get("/api/m-npc-cards?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(mNpcCard.getId().intValue())))
            .andExpect(jsonPath("$.[*].characterId").value(hasItem(DEFAULT_CHARACTER_ID)))
            .andExpect(jsonPath("$.[*].shortName").value(hasItem(DEFAULT_SHORT_NAME.toString())))
            .andExpect(jsonPath("$.[*].nickName").value(hasItem(DEFAULT_NICK_NAME.toString())))
            .andExpect(jsonPath("$.[*].teamId").value(hasItem(DEFAULT_TEAM_ID)))
            .andExpect(jsonPath("$.[*].nationalityId").value(hasItem(DEFAULT_NATIONALITY_ID)))
            .andExpect(jsonPath("$.[*].rarity").value(hasItem(DEFAULT_RARITY)))
            .andExpect(jsonPath("$.[*].attribute").value(hasItem(DEFAULT_ATTRIBUTE)))
            .andExpect(jsonPath("$.[*].modelId").value(hasItem(DEFAULT_MODEL_ID)))
            .andExpect(jsonPath("$.[*].level").value(hasItem(DEFAULT_LEVEL)))
            .andExpect(jsonPath("$.[*].thumbnailAssetsId").value(hasItem(DEFAULT_THUMBNAIL_ASSETS_ID)))
            .andExpect(jsonPath("$.[*].cardIllustAssetsId").value(hasItem(DEFAULT_CARD_ILLUST_ASSETS_ID)))
            .andExpect(jsonPath("$.[*].playableAssetsId").value(hasItem(DEFAULT_PLAYABLE_ASSETS_ID)))
            .andExpect(jsonPath("$.[*].teamEffectId").value(hasItem(DEFAULT_TEAM_EFFECT_ID)))
            .andExpect(jsonPath("$.[*].teamEffectLevel").value(hasItem(DEFAULT_TEAM_EFFECT_LEVEL)))
            .andExpect(jsonPath("$.[*].triggerEffectId").value(hasItem(DEFAULT_TRIGGER_EFFECT_ID)))
            .andExpect(jsonPath("$.[*].action1Id").value(hasItem(DEFAULT_ACTION_1_ID)))
            .andExpect(jsonPath("$.[*].action1Level").value(hasItem(DEFAULT_ACTION_1_LEVEL)))
            .andExpect(jsonPath("$.[*].action2Id").value(hasItem(DEFAULT_ACTION_2_ID)))
            .andExpect(jsonPath("$.[*].action2Level").value(hasItem(DEFAULT_ACTION_2_LEVEL)))
            .andExpect(jsonPath("$.[*].action3Id").value(hasItem(DEFAULT_ACTION_3_ID)))
            .andExpect(jsonPath("$.[*].action3Level").value(hasItem(DEFAULT_ACTION_3_LEVEL)))
            .andExpect(jsonPath("$.[*].action4Id").value(hasItem(DEFAULT_ACTION_4_ID)))
            .andExpect(jsonPath("$.[*].action4Level").value(hasItem(DEFAULT_ACTION_4_LEVEL)))
            .andExpect(jsonPath("$.[*].action5Id").value(hasItem(DEFAULT_ACTION_5_ID)))
            .andExpect(jsonPath("$.[*].action5Level").value(hasItem(DEFAULT_ACTION_5_LEVEL)))
            .andExpect(jsonPath("$.[*].stamina").value(hasItem(DEFAULT_STAMINA)))
            .andExpect(jsonPath("$.[*].dribble").value(hasItem(DEFAULT_DRIBBLE)))
            .andExpect(jsonPath("$.[*].shoot").value(hasItem(DEFAULT_SHOOT)))
            .andExpect(jsonPath("$.[*].ballPass").value(hasItem(DEFAULT_BALL_PASS)))
            .andExpect(jsonPath("$.[*].tackle").value(hasItem(DEFAULT_TACKLE)))
            .andExpect(jsonPath("$.[*].block").value(hasItem(DEFAULT_BLOCK)))
            .andExpect(jsonPath("$.[*].intercept").value(hasItem(DEFAULT_INTERCEPT)))
            .andExpect(jsonPath("$.[*].speed").value(hasItem(DEFAULT_SPEED)))
            .andExpect(jsonPath("$.[*].power").value(hasItem(DEFAULT_POWER)))
            .andExpect(jsonPath("$.[*].technique").value(hasItem(DEFAULT_TECHNIQUE)))
            .andExpect(jsonPath("$.[*].punching").value(hasItem(DEFAULT_PUNCHING)))
            .andExpect(jsonPath("$.[*].catching").value(hasItem(DEFAULT_CATCHING)))
            .andExpect(jsonPath("$.[*].highBallBonus").value(hasItem(DEFAULT_HIGH_BALL_BONUS)))
            .andExpect(jsonPath("$.[*].lowBallBonus").value(hasItem(DEFAULT_LOW_BALL_BONUS)))
            .andExpect(jsonPath("$.[*].personalityId").value(hasItem(DEFAULT_PERSONALITY_ID)))
            .andExpect(jsonPath("$.[*].uniformNo").value(hasItem(DEFAULT_UNIFORM_NO)))
            .andExpect(jsonPath("$.[*].levelGroupId").value(hasItem(DEFAULT_LEVEL_GROUP_ID)));

        // Check, that the count call also returns 1
        restMNpcCardMockMvc.perform(get("/api/m-npc-cards/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultMNpcCardShouldNotBeFound(String filter) throws Exception {
        restMNpcCardMockMvc.perform(get("/api/m-npc-cards?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restMNpcCardMockMvc.perform(get("/api/m-npc-cards/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("0"));
    }


    @Test
    @Transactional
    public void getNonExistingMNpcCard() throws Exception {
        // Get the mNpcCard
        restMNpcCardMockMvc.perform(get("/api/m-npc-cards/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateMNpcCard() throws Exception {
        // Initialize the database
        mNpcCardRepository.saveAndFlush(mNpcCard);

        int databaseSizeBeforeUpdate = mNpcCardRepository.findAll().size();

        // Update the mNpcCard
        MNpcCard updatedMNpcCard = mNpcCardRepository.findById(mNpcCard.getId()).get();
        // Disconnect from session so that the updates on updatedMNpcCard are not directly saved in db
        em.detach(updatedMNpcCard);
        updatedMNpcCard
            .characterId(UPDATED_CHARACTER_ID)
            .shortName(UPDATED_SHORT_NAME)
            .nickName(UPDATED_NICK_NAME)
            .teamId(UPDATED_TEAM_ID)
            .nationalityId(UPDATED_NATIONALITY_ID)
            .rarity(UPDATED_RARITY)
            .attribute(UPDATED_ATTRIBUTE)
            .modelId(UPDATED_MODEL_ID)
            .level(UPDATED_LEVEL)
            .thumbnailAssetsId(UPDATED_THUMBNAIL_ASSETS_ID)
            .cardIllustAssetsId(UPDATED_CARD_ILLUST_ASSETS_ID)
            .playableAssetsId(UPDATED_PLAYABLE_ASSETS_ID)
            .teamEffectId(UPDATED_TEAM_EFFECT_ID)
            .teamEffectLevel(UPDATED_TEAM_EFFECT_LEVEL)
            .triggerEffectId(UPDATED_TRIGGER_EFFECT_ID)
            .action1Id(UPDATED_ACTION_1_ID)
            .action1Level(UPDATED_ACTION_1_LEVEL)
            .action2Id(UPDATED_ACTION_2_ID)
            .action2Level(UPDATED_ACTION_2_LEVEL)
            .action3Id(UPDATED_ACTION_3_ID)
            .action3Level(UPDATED_ACTION_3_LEVEL)
            .action4Id(UPDATED_ACTION_4_ID)
            .action4Level(UPDATED_ACTION_4_LEVEL)
            .action5Id(UPDATED_ACTION_5_ID)
            .action5Level(UPDATED_ACTION_5_LEVEL)
            .stamina(UPDATED_STAMINA)
            .dribble(UPDATED_DRIBBLE)
            .shoot(UPDATED_SHOOT)
            .ballPass(UPDATED_BALL_PASS)
            .tackle(UPDATED_TACKLE)
            .block(UPDATED_BLOCK)
            .intercept(UPDATED_INTERCEPT)
            .speed(UPDATED_SPEED)
            .power(UPDATED_POWER)
            .technique(UPDATED_TECHNIQUE)
            .punching(UPDATED_PUNCHING)
            .catching(UPDATED_CATCHING)
            .highBallBonus(UPDATED_HIGH_BALL_BONUS)
            .lowBallBonus(UPDATED_LOW_BALL_BONUS)
            .personalityId(UPDATED_PERSONALITY_ID)
            .uniformNo(UPDATED_UNIFORM_NO)
            .levelGroupId(UPDATED_LEVEL_GROUP_ID);
        MNpcCardDTO mNpcCardDTO = mNpcCardMapper.toDto(updatedMNpcCard);

        restMNpcCardMockMvc.perform(put("/api/m-npc-cards")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mNpcCardDTO)))
            .andExpect(status().isOk());

        // Validate the MNpcCard in the database
        List<MNpcCard> mNpcCardList = mNpcCardRepository.findAll();
        assertThat(mNpcCardList).hasSize(databaseSizeBeforeUpdate);
        MNpcCard testMNpcCard = mNpcCardList.get(mNpcCardList.size() - 1);
        assertThat(testMNpcCard.getCharacterId()).isEqualTo(UPDATED_CHARACTER_ID);
        assertThat(testMNpcCard.getShortName()).isEqualTo(UPDATED_SHORT_NAME);
        assertThat(testMNpcCard.getNickName()).isEqualTo(UPDATED_NICK_NAME);
        assertThat(testMNpcCard.getTeamId()).isEqualTo(UPDATED_TEAM_ID);
        assertThat(testMNpcCard.getNationalityId()).isEqualTo(UPDATED_NATIONALITY_ID);
        assertThat(testMNpcCard.getRarity()).isEqualTo(UPDATED_RARITY);
        assertThat(testMNpcCard.getAttribute()).isEqualTo(UPDATED_ATTRIBUTE);
        assertThat(testMNpcCard.getModelId()).isEqualTo(UPDATED_MODEL_ID);
        assertThat(testMNpcCard.getLevel()).isEqualTo(UPDATED_LEVEL);
        assertThat(testMNpcCard.getThumbnailAssetsId()).isEqualTo(UPDATED_THUMBNAIL_ASSETS_ID);
        assertThat(testMNpcCard.getCardIllustAssetsId()).isEqualTo(UPDATED_CARD_ILLUST_ASSETS_ID);
        assertThat(testMNpcCard.getPlayableAssetsId()).isEqualTo(UPDATED_PLAYABLE_ASSETS_ID);
        assertThat(testMNpcCard.getTeamEffectId()).isEqualTo(UPDATED_TEAM_EFFECT_ID);
        assertThat(testMNpcCard.getTeamEffectLevel()).isEqualTo(UPDATED_TEAM_EFFECT_LEVEL);
        assertThat(testMNpcCard.getTriggerEffectId()).isEqualTo(UPDATED_TRIGGER_EFFECT_ID);
        assertThat(testMNpcCard.getAction1Id()).isEqualTo(UPDATED_ACTION_1_ID);
        assertThat(testMNpcCard.getAction1Level()).isEqualTo(UPDATED_ACTION_1_LEVEL);
        assertThat(testMNpcCard.getAction2Id()).isEqualTo(UPDATED_ACTION_2_ID);
        assertThat(testMNpcCard.getAction2Level()).isEqualTo(UPDATED_ACTION_2_LEVEL);
        assertThat(testMNpcCard.getAction3Id()).isEqualTo(UPDATED_ACTION_3_ID);
        assertThat(testMNpcCard.getAction3Level()).isEqualTo(UPDATED_ACTION_3_LEVEL);
        assertThat(testMNpcCard.getAction4Id()).isEqualTo(UPDATED_ACTION_4_ID);
        assertThat(testMNpcCard.getAction4Level()).isEqualTo(UPDATED_ACTION_4_LEVEL);
        assertThat(testMNpcCard.getAction5Id()).isEqualTo(UPDATED_ACTION_5_ID);
        assertThat(testMNpcCard.getAction5Level()).isEqualTo(UPDATED_ACTION_5_LEVEL);
        assertThat(testMNpcCard.getStamina()).isEqualTo(UPDATED_STAMINA);
        assertThat(testMNpcCard.getDribble()).isEqualTo(UPDATED_DRIBBLE);
        assertThat(testMNpcCard.getShoot()).isEqualTo(UPDATED_SHOOT);
        assertThat(testMNpcCard.getBallPass()).isEqualTo(UPDATED_BALL_PASS);
        assertThat(testMNpcCard.getTackle()).isEqualTo(UPDATED_TACKLE);
        assertThat(testMNpcCard.getBlock()).isEqualTo(UPDATED_BLOCK);
        assertThat(testMNpcCard.getIntercept()).isEqualTo(UPDATED_INTERCEPT);
        assertThat(testMNpcCard.getSpeed()).isEqualTo(UPDATED_SPEED);
        assertThat(testMNpcCard.getPower()).isEqualTo(UPDATED_POWER);
        assertThat(testMNpcCard.getTechnique()).isEqualTo(UPDATED_TECHNIQUE);
        assertThat(testMNpcCard.getPunching()).isEqualTo(UPDATED_PUNCHING);
        assertThat(testMNpcCard.getCatching()).isEqualTo(UPDATED_CATCHING);
        assertThat(testMNpcCard.getHighBallBonus()).isEqualTo(UPDATED_HIGH_BALL_BONUS);
        assertThat(testMNpcCard.getLowBallBonus()).isEqualTo(UPDATED_LOW_BALL_BONUS);
        assertThat(testMNpcCard.getPersonalityId()).isEqualTo(UPDATED_PERSONALITY_ID);
        assertThat(testMNpcCard.getUniformNo()).isEqualTo(UPDATED_UNIFORM_NO);
        assertThat(testMNpcCard.getLevelGroupId()).isEqualTo(UPDATED_LEVEL_GROUP_ID);
    }

    @Test
    @Transactional
    public void updateNonExistingMNpcCard() throws Exception {
        int databaseSizeBeforeUpdate = mNpcCardRepository.findAll().size();

        // Create the MNpcCard
        MNpcCardDTO mNpcCardDTO = mNpcCardMapper.toDto(mNpcCard);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restMNpcCardMockMvc.perform(put("/api/m-npc-cards")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mNpcCardDTO)))
            .andExpect(status().isBadRequest());

        // Validate the MNpcCard in the database
        List<MNpcCard> mNpcCardList = mNpcCardRepository.findAll();
        assertThat(mNpcCardList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteMNpcCard() throws Exception {
        // Initialize the database
        mNpcCardRepository.saveAndFlush(mNpcCard);

        int databaseSizeBeforeDelete = mNpcCardRepository.findAll().size();

        // Delete the mNpcCard
        restMNpcCardMockMvc.perform(delete("/api/m-npc-cards/{id}", mNpcCard.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database is empty
        List<MNpcCard> mNpcCardList = mNpcCardRepository.findAll();
        assertThat(mNpcCardList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(MNpcCard.class);
        MNpcCard mNpcCard1 = new MNpcCard();
        mNpcCard1.setId(1L);
        MNpcCard mNpcCard2 = new MNpcCard();
        mNpcCard2.setId(mNpcCard1.getId());
        assertThat(mNpcCard1).isEqualTo(mNpcCard2);
        mNpcCard2.setId(2L);
        assertThat(mNpcCard1).isNotEqualTo(mNpcCard2);
        mNpcCard1.setId(null);
        assertThat(mNpcCard1).isNotEqualTo(mNpcCard2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(MNpcCardDTO.class);
        MNpcCardDTO mNpcCardDTO1 = new MNpcCardDTO();
        mNpcCardDTO1.setId(1L);
        MNpcCardDTO mNpcCardDTO2 = new MNpcCardDTO();
        assertThat(mNpcCardDTO1).isNotEqualTo(mNpcCardDTO2);
        mNpcCardDTO2.setId(mNpcCardDTO1.getId());
        assertThat(mNpcCardDTO1).isEqualTo(mNpcCardDTO2);
        mNpcCardDTO2.setId(2L);
        assertThat(mNpcCardDTO1).isNotEqualTo(mNpcCardDTO2);
        mNpcCardDTO1.setId(null);
        assertThat(mNpcCardDTO1).isNotEqualTo(mNpcCardDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(mNpcCardMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(mNpcCardMapper.fromId(null)).isNull();
    }
}
