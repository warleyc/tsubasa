package io.shm.tsubasa.web.rest;

import io.shm.tsubasa.TsubasaApp;
import io.shm.tsubasa.domain.MPlayableCard;
import io.shm.tsubasa.domain.MModelCard;
import io.shm.tsubasa.domain.MArousal;
import io.shm.tsubasa.domain.MTargetPlayableCardGroup;
import io.shm.tsubasa.repository.MPlayableCardRepository;
import io.shm.tsubasa.service.MPlayableCardService;
import io.shm.tsubasa.service.dto.MPlayableCardDTO;
import io.shm.tsubasa.service.mapper.MPlayableCardMapper;
import io.shm.tsubasa.web.rest.errors.ExceptionTranslator;
import io.shm.tsubasa.service.dto.MPlayableCardCriteria;
import io.shm.tsubasa.service.MPlayableCardQueryService;

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
 * Integration tests for the {@Link MPlayableCardResource} REST controller.
 */
@SpringBootTest(classes = TsubasaApp.class)
public class MPlayableCardResourceIT {

    private static final Integer DEFAULT_MODEL_ID = 1;
    private static final Integer UPDATED_MODEL_ID = 2;

    private static final Integer DEFAULT_PROPER_POSITION_GK = 1;
    private static final Integer UPDATED_PROPER_POSITION_GK = 2;

    private static final Integer DEFAULT_PROPER_POSITION_FW = 1;
    private static final Integer UPDATED_PROPER_POSITION_FW = 2;

    private static final Integer DEFAULT_PROPER_POSITION_OMF = 1;
    private static final Integer UPDATED_PROPER_POSITION_OMF = 2;

    private static final Integer DEFAULT_PROPER_POSITION_DMF = 1;
    private static final Integer UPDATED_PROPER_POSITION_DMF = 2;

    private static final Integer DEFAULT_PROPER_POSITION_DF = 1;
    private static final Integer UPDATED_PROPER_POSITION_DF = 2;

    private static final Integer DEFAULT_CHARACTER_ID = 1;
    private static final Integer UPDATED_CHARACTER_ID = 2;

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

    private static final Integer DEFAULT_THUMBNAIL_ASSETS_ID = 1;
    private static final Integer UPDATED_THUMBNAIL_ASSETS_ID = 2;

    private static final Integer DEFAULT_CARD_ILLUST_ASSETS_ID = 1;
    private static final Integer UPDATED_CARD_ILLUST_ASSETS_ID = 2;

    private static final Integer DEFAULT_PLAYABLE_ASSETS_ID = 1;
    private static final Integer UPDATED_PLAYABLE_ASSETS_ID = 2;

    private static final Integer DEFAULT_TEAM_EFFECT_ID = 1;
    private static final Integer UPDATED_TEAM_EFFECT_ID = 2;

    private static final Integer DEFAULT_TRIGGER_EFFECT_ID = 1;
    private static final Integer UPDATED_TRIGGER_EFFECT_ID = 2;

    private static final Integer DEFAULT_MAX_ACTION_SLOT = 1;
    private static final Integer UPDATED_MAX_ACTION_SLOT = 2;

    private static final Integer DEFAULT_INITIAL_ACTION_ID_1 = 1;
    private static final Integer UPDATED_INITIAL_ACTION_ID_1 = 2;

    private static final Integer DEFAULT_INITIAL_ACTION_ID_2 = 1;
    private static final Integer UPDATED_INITIAL_ACTION_ID_2 = 2;

    private static final Integer DEFAULT_INITIAL_ACTION_ID_3 = 1;
    private static final Integer UPDATED_INITIAL_ACTION_ID_3 = 2;

    private static final Integer DEFAULT_INITIAL_ACTION_ID_4 = 1;
    private static final Integer UPDATED_INITIAL_ACTION_ID_4 = 2;

    private static final Integer DEFAULT_INITIAL_ACTION_ID_5 = 1;
    private static final Integer UPDATED_INITIAL_ACTION_ID_5 = 2;

    private static final Integer DEFAULT_INITIAL_STAMINA = 1;
    private static final Integer UPDATED_INITIAL_STAMINA = 2;

    private static final Integer DEFAULT_INITIAL_DRIBBLE = 1;
    private static final Integer UPDATED_INITIAL_DRIBBLE = 2;

    private static final Integer DEFAULT_INITIAL_SHOOT = 1;
    private static final Integer UPDATED_INITIAL_SHOOT = 2;

    private static final Integer DEFAULT_INITIAL_PASS = 1;
    private static final Integer UPDATED_INITIAL_PASS = 2;

    private static final Integer DEFAULT_INITIAL_TACKLE = 1;
    private static final Integer UPDATED_INITIAL_TACKLE = 2;

    private static final Integer DEFAULT_INITIAL_BLOCK = 1;
    private static final Integer UPDATED_INITIAL_BLOCK = 2;

    private static final Integer DEFAULT_INITIAL_INTERCEPT = 1;
    private static final Integer UPDATED_INITIAL_INTERCEPT = 2;

    private static final Integer DEFAULT_INITIAL_SPEED = 1;
    private static final Integer UPDATED_INITIAL_SPEED = 2;

    private static final Integer DEFAULT_INITIAL_POWER = 1;
    private static final Integer UPDATED_INITIAL_POWER = 2;

    private static final Integer DEFAULT_INITIAL_TECHNIQUE = 1;
    private static final Integer UPDATED_INITIAL_TECHNIQUE = 2;

    private static final Integer DEFAULT_INITIAL_PUNCHING = 1;
    private static final Integer UPDATED_INITIAL_PUNCHING = 2;

    private static final Integer DEFAULT_INITIAL_CATCHING = 1;
    private static final Integer UPDATED_INITIAL_CATCHING = 2;

    private static final Integer DEFAULT_MAX_STAMINA = 1;
    private static final Integer UPDATED_MAX_STAMINA = 2;

    private static final Integer DEFAULT_MAX_DRIBBLE = 1;
    private static final Integer UPDATED_MAX_DRIBBLE = 2;

    private static final Integer DEFAULT_MAX_SHOOT = 1;
    private static final Integer UPDATED_MAX_SHOOT = 2;

    private static final Integer DEFAULT_MAX_PASS = 1;
    private static final Integer UPDATED_MAX_PASS = 2;

    private static final Integer DEFAULT_MAX_TACKLE = 1;
    private static final Integer UPDATED_MAX_TACKLE = 2;

    private static final Integer DEFAULT_MAX_BLOCK = 1;
    private static final Integer UPDATED_MAX_BLOCK = 2;

    private static final Integer DEFAULT_MAX_INTERCEPT = 1;
    private static final Integer UPDATED_MAX_INTERCEPT = 2;

    private static final Integer DEFAULT_MAX_SPEED = 1;
    private static final Integer UPDATED_MAX_SPEED = 2;

    private static final Integer DEFAULT_MAX_POWER = 1;
    private static final Integer UPDATED_MAX_POWER = 2;

    private static final Integer DEFAULT_MAX_TECHNIQUE = 1;
    private static final Integer UPDATED_MAX_TECHNIQUE = 2;

    private static final Integer DEFAULT_MAX_PUNCHING = 1;
    private static final Integer UPDATED_MAX_PUNCHING = 2;

    private static final Integer DEFAULT_MAX_CATCHING = 1;
    private static final Integer UPDATED_MAX_CATCHING = 2;

    private static final Integer DEFAULT_MAX_PLUS_DRIBBLE = 1;
    private static final Integer UPDATED_MAX_PLUS_DRIBBLE = 2;

    private static final Integer DEFAULT_MAX_PLUS_SHOOT = 1;
    private static final Integer UPDATED_MAX_PLUS_SHOOT = 2;

    private static final Integer DEFAULT_MAX_PLUS_PASS = 1;
    private static final Integer UPDATED_MAX_PLUS_PASS = 2;

    private static final Integer DEFAULT_MAX_PLUS_TACKLE = 1;
    private static final Integer UPDATED_MAX_PLUS_TACKLE = 2;

    private static final Integer DEFAULT_MAX_PLUS_BLOCK = 1;
    private static final Integer UPDATED_MAX_PLUS_BLOCK = 2;

    private static final Integer DEFAULT_MAX_PLUS_INTERCEPT = 1;
    private static final Integer UPDATED_MAX_PLUS_INTERCEPT = 2;

    private static final Integer DEFAULT_MAX_PLUS_SPEED = 1;
    private static final Integer UPDATED_MAX_PLUS_SPEED = 2;

    private static final Integer DEFAULT_MAX_PLUS_POWER = 1;
    private static final Integer UPDATED_MAX_PLUS_POWER = 2;

    private static final Integer DEFAULT_MAX_PLUS_TECHNIQUE = 1;
    private static final Integer UPDATED_MAX_PLUS_TECHNIQUE = 2;

    private static final Integer DEFAULT_MAX_PLUS_PUNCHING = 1;
    private static final Integer UPDATED_MAX_PLUS_PUNCHING = 2;

    private static final Integer DEFAULT_MAX_PLUS_CATCHING = 1;
    private static final Integer UPDATED_MAX_PLUS_CATCHING = 2;

    private static final Integer DEFAULT_HIGH_BALL_BONUS = 1;
    private static final Integer UPDATED_HIGH_BALL_BONUS = 2;

    private static final Integer DEFAULT_LOW_BALL_BONUS = 1;
    private static final Integer UPDATED_LOW_BALL_BONUS = 2;

    private static final Integer DEFAULT_IS_DROP_ONLY = 1;
    private static final Integer UPDATED_IS_DROP_ONLY = 2;

    private static final Integer DEFAULT_SELL_COIN_GROUP_NUM = 1;
    private static final Integer UPDATED_SELL_COIN_GROUP_NUM = 2;

    private static final Integer DEFAULT_SELL_MEDAL_ID = 1;
    private static final Integer UPDATED_SELL_MEDAL_ID = 2;

    private static final Integer DEFAULT_CHARACTER_BOOK_ID = 1;
    private static final Integer UPDATED_CHARACTER_BOOK_ID = 2;

    private static final Integer DEFAULT_BOOK_NO = 1;
    private static final Integer UPDATED_BOOK_NO = 2;

    private static final Integer DEFAULT_IS_SHOW_BOOK = 1;
    private static final Integer UPDATED_IS_SHOW_BOOK = 2;

    private static final Integer DEFAULT_LEVEL_GROUP_ID = 1;
    private static final Integer UPDATED_LEVEL_GROUP_ID = 2;

    private static final Integer DEFAULT_START_AT = 1;
    private static final Integer UPDATED_START_AT = 2;

    @Autowired
    private MPlayableCardRepository mPlayableCardRepository;

    @Autowired
    private MPlayableCardMapper mPlayableCardMapper;

    @Autowired
    private MPlayableCardService mPlayableCardService;

    @Autowired
    private MPlayableCardQueryService mPlayableCardQueryService;

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

    private MockMvc restMPlayableCardMockMvc;

    private MPlayableCard mPlayableCard;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final MPlayableCardResource mPlayableCardResource = new MPlayableCardResource(mPlayableCardService, mPlayableCardQueryService);
        this.restMPlayableCardMockMvc = MockMvcBuilders.standaloneSetup(mPlayableCardResource)
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
    public static MPlayableCard createEntity(EntityManager em) {
        MPlayableCard mPlayableCard = new MPlayableCard()
            .modelId(DEFAULT_MODEL_ID)
            .properPositionGk(DEFAULT_PROPER_POSITION_GK)
            .properPositionFw(DEFAULT_PROPER_POSITION_FW)
            .properPositionOmf(DEFAULT_PROPER_POSITION_OMF)
            .properPositionDmf(DEFAULT_PROPER_POSITION_DMF)
            .properPositionDf(DEFAULT_PROPER_POSITION_DF)
            .characterId(DEFAULT_CHARACTER_ID)
            .nickName(DEFAULT_NICK_NAME)
            .teamId(DEFAULT_TEAM_ID)
            .nationalityId(DEFAULT_NATIONALITY_ID)
            .rarity(DEFAULT_RARITY)
            .attribute(DEFAULT_ATTRIBUTE)
            .thumbnailAssetsId(DEFAULT_THUMBNAIL_ASSETS_ID)
            .cardIllustAssetsId(DEFAULT_CARD_ILLUST_ASSETS_ID)
            .playableAssetsId(DEFAULT_PLAYABLE_ASSETS_ID)
            .teamEffectId(DEFAULT_TEAM_EFFECT_ID)
            .triggerEffectId(DEFAULT_TRIGGER_EFFECT_ID)
            .maxActionSlot(DEFAULT_MAX_ACTION_SLOT)
            .initialActionId1(DEFAULT_INITIAL_ACTION_ID_1)
            .initialActionId2(DEFAULT_INITIAL_ACTION_ID_2)
            .initialActionId3(DEFAULT_INITIAL_ACTION_ID_3)
            .initialActionId4(DEFAULT_INITIAL_ACTION_ID_4)
            .initialActionId5(DEFAULT_INITIAL_ACTION_ID_5)
            .initialStamina(DEFAULT_INITIAL_STAMINA)
            .initialDribble(DEFAULT_INITIAL_DRIBBLE)
            .initialShoot(DEFAULT_INITIAL_SHOOT)
            .initialPass(DEFAULT_INITIAL_PASS)
            .initialTackle(DEFAULT_INITIAL_TACKLE)
            .initialBlock(DEFAULT_INITIAL_BLOCK)
            .initialIntercept(DEFAULT_INITIAL_INTERCEPT)
            .initialSpeed(DEFAULT_INITIAL_SPEED)
            .initialPower(DEFAULT_INITIAL_POWER)
            .initialTechnique(DEFAULT_INITIAL_TECHNIQUE)
            .initialPunching(DEFAULT_INITIAL_PUNCHING)
            .initialCatching(DEFAULT_INITIAL_CATCHING)
            .maxStamina(DEFAULT_MAX_STAMINA)
            .maxDribble(DEFAULT_MAX_DRIBBLE)
            .maxShoot(DEFAULT_MAX_SHOOT)
            .maxPass(DEFAULT_MAX_PASS)
            .maxTackle(DEFAULT_MAX_TACKLE)
            .maxBlock(DEFAULT_MAX_BLOCK)
            .maxIntercept(DEFAULT_MAX_INTERCEPT)
            .maxSpeed(DEFAULT_MAX_SPEED)
            .maxPower(DEFAULT_MAX_POWER)
            .maxTechnique(DEFAULT_MAX_TECHNIQUE)
            .maxPunching(DEFAULT_MAX_PUNCHING)
            .maxCatching(DEFAULT_MAX_CATCHING)
            .maxPlusDribble(DEFAULT_MAX_PLUS_DRIBBLE)
            .maxPlusShoot(DEFAULT_MAX_PLUS_SHOOT)
            .maxPlusPass(DEFAULT_MAX_PLUS_PASS)
            .maxPlusTackle(DEFAULT_MAX_PLUS_TACKLE)
            .maxPlusBlock(DEFAULT_MAX_PLUS_BLOCK)
            .maxPlusIntercept(DEFAULT_MAX_PLUS_INTERCEPT)
            .maxPlusSpeed(DEFAULT_MAX_PLUS_SPEED)
            .maxPlusPower(DEFAULT_MAX_PLUS_POWER)
            .maxPlusTechnique(DEFAULT_MAX_PLUS_TECHNIQUE)
            .maxPlusPunching(DEFAULT_MAX_PLUS_PUNCHING)
            .maxPlusCatching(DEFAULT_MAX_PLUS_CATCHING)
            .highBallBonus(DEFAULT_HIGH_BALL_BONUS)
            .lowBallBonus(DEFAULT_LOW_BALL_BONUS)
            .isDropOnly(DEFAULT_IS_DROP_ONLY)
            .sellCoinGroupNum(DEFAULT_SELL_COIN_GROUP_NUM)
            .sellMedalId(DEFAULT_SELL_MEDAL_ID)
            .characterBookId(DEFAULT_CHARACTER_BOOK_ID)
            .bookNo(DEFAULT_BOOK_NO)
            .isShowBook(DEFAULT_IS_SHOW_BOOK)
            .levelGroupId(DEFAULT_LEVEL_GROUP_ID)
            .startAt(DEFAULT_START_AT);
        // Add required entity
        MModelCard mModelCard;
        if (TestUtil.findAll(em, MModelCard.class).isEmpty()) {
            mModelCard = MModelCardResourceIT.createEntity(em);
            em.persist(mModelCard);
            em.flush();
        } else {
            mModelCard = TestUtil.findAll(em, MModelCard.class).get(0);
        }
        mPlayableCard.setId(mModelCard);
        return mPlayableCard;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static MPlayableCard createUpdatedEntity(EntityManager em) {
        MPlayableCard mPlayableCard = new MPlayableCard()
            .modelId(UPDATED_MODEL_ID)
            .properPositionGk(UPDATED_PROPER_POSITION_GK)
            .properPositionFw(UPDATED_PROPER_POSITION_FW)
            .properPositionOmf(UPDATED_PROPER_POSITION_OMF)
            .properPositionDmf(UPDATED_PROPER_POSITION_DMF)
            .properPositionDf(UPDATED_PROPER_POSITION_DF)
            .characterId(UPDATED_CHARACTER_ID)
            .nickName(UPDATED_NICK_NAME)
            .teamId(UPDATED_TEAM_ID)
            .nationalityId(UPDATED_NATIONALITY_ID)
            .rarity(UPDATED_RARITY)
            .attribute(UPDATED_ATTRIBUTE)
            .thumbnailAssetsId(UPDATED_THUMBNAIL_ASSETS_ID)
            .cardIllustAssetsId(UPDATED_CARD_ILLUST_ASSETS_ID)
            .playableAssetsId(UPDATED_PLAYABLE_ASSETS_ID)
            .teamEffectId(UPDATED_TEAM_EFFECT_ID)
            .triggerEffectId(UPDATED_TRIGGER_EFFECT_ID)
            .maxActionSlot(UPDATED_MAX_ACTION_SLOT)
            .initialActionId1(UPDATED_INITIAL_ACTION_ID_1)
            .initialActionId2(UPDATED_INITIAL_ACTION_ID_2)
            .initialActionId3(UPDATED_INITIAL_ACTION_ID_3)
            .initialActionId4(UPDATED_INITIAL_ACTION_ID_4)
            .initialActionId5(UPDATED_INITIAL_ACTION_ID_5)
            .initialStamina(UPDATED_INITIAL_STAMINA)
            .initialDribble(UPDATED_INITIAL_DRIBBLE)
            .initialShoot(UPDATED_INITIAL_SHOOT)
            .initialPass(UPDATED_INITIAL_PASS)
            .initialTackle(UPDATED_INITIAL_TACKLE)
            .initialBlock(UPDATED_INITIAL_BLOCK)
            .initialIntercept(UPDATED_INITIAL_INTERCEPT)
            .initialSpeed(UPDATED_INITIAL_SPEED)
            .initialPower(UPDATED_INITIAL_POWER)
            .initialTechnique(UPDATED_INITIAL_TECHNIQUE)
            .initialPunching(UPDATED_INITIAL_PUNCHING)
            .initialCatching(UPDATED_INITIAL_CATCHING)
            .maxStamina(UPDATED_MAX_STAMINA)
            .maxDribble(UPDATED_MAX_DRIBBLE)
            .maxShoot(UPDATED_MAX_SHOOT)
            .maxPass(UPDATED_MAX_PASS)
            .maxTackle(UPDATED_MAX_TACKLE)
            .maxBlock(UPDATED_MAX_BLOCK)
            .maxIntercept(UPDATED_MAX_INTERCEPT)
            .maxSpeed(UPDATED_MAX_SPEED)
            .maxPower(UPDATED_MAX_POWER)
            .maxTechnique(UPDATED_MAX_TECHNIQUE)
            .maxPunching(UPDATED_MAX_PUNCHING)
            .maxCatching(UPDATED_MAX_CATCHING)
            .maxPlusDribble(UPDATED_MAX_PLUS_DRIBBLE)
            .maxPlusShoot(UPDATED_MAX_PLUS_SHOOT)
            .maxPlusPass(UPDATED_MAX_PLUS_PASS)
            .maxPlusTackle(UPDATED_MAX_PLUS_TACKLE)
            .maxPlusBlock(UPDATED_MAX_PLUS_BLOCK)
            .maxPlusIntercept(UPDATED_MAX_PLUS_INTERCEPT)
            .maxPlusSpeed(UPDATED_MAX_PLUS_SPEED)
            .maxPlusPower(UPDATED_MAX_PLUS_POWER)
            .maxPlusTechnique(UPDATED_MAX_PLUS_TECHNIQUE)
            .maxPlusPunching(UPDATED_MAX_PLUS_PUNCHING)
            .maxPlusCatching(UPDATED_MAX_PLUS_CATCHING)
            .highBallBonus(UPDATED_HIGH_BALL_BONUS)
            .lowBallBonus(UPDATED_LOW_BALL_BONUS)
            .isDropOnly(UPDATED_IS_DROP_ONLY)
            .sellCoinGroupNum(UPDATED_SELL_COIN_GROUP_NUM)
            .sellMedalId(UPDATED_SELL_MEDAL_ID)
            .characterBookId(UPDATED_CHARACTER_BOOK_ID)
            .bookNo(UPDATED_BOOK_NO)
            .isShowBook(UPDATED_IS_SHOW_BOOK)
            .levelGroupId(UPDATED_LEVEL_GROUP_ID)
            .startAt(UPDATED_START_AT);
        // Add required entity
        MModelCard mModelCard;
        if (TestUtil.findAll(em, MModelCard.class).isEmpty()) {
            mModelCard = MModelCardResourceIT.createUpdatedEntity(em);
            em.persist(mModelCard);
            em.flush();
        } else {
            mModelCard = TestUtil.findAll(em, MModelCard.class).get(0);
        }
        mPlayableCard.setId(mModelCard);
        return mPlayableCard;
    }

    @BeforeEach
    public void initTest() {
        mPlayableCard = createEntity(em);
    }

    @Test
    @Transactional
    public void createMPlayableCard() throws Exception {
        int databaseSizeBeforeCreate = mPlayableCardRepository.findAll().size();

        // Create the MPlayableCard
        MPlayableCardDTO mPlayableCardDTO = mPlayableCardMapper.toDto(mPlayableCard);
        restMPlayableCardMockMvc.perform(post("/api/m-playable-cards")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mPlayableCardDTO)))
            .andExpect(status().isCreated());

        // Validate the MPlayableCard in the database
        List<MPlayableCard> mPlayableCardList = mPlayableCardRepository.findAll();
        assertThat(mPlayableCardList).hasSize(databaseSizeBeforeCreate + 1);
        MPlayableCard testMPlayableCard = mPlayableCardList.get(mPlayableCardList.size() - 1);
        assertThat(testMPlayableCard.getModelId()).isEqualTo(DEFAULT_MODEL_ID);
        assertThat(testMPlayableCard.getProperPositionGk()).isEqualTo(DEFAULT_PROPER_POSITION_GK);
        assertThat(testMPlayableCard.getProperPositionFw()).isEqualTo(DEFAULT_PROPER_POSITION_FW);
        assertThat(testMPlayableCard.getProperPositionOmf()).isEqualTo(DEFAULT_PROPER_POSITION_OMF);
        assertThat(testMPlayableCard.getProperPositionDmf()).isEqualTo(DEFAULT_PROPER_POSITION_DMF);
        assertThat(testMPlayableCard.getProperPositionDf()).isEqualTo(DEFAULT_PROPER_POSITION_DF);
        assertThat(testMPlayableCard.getCharacterId()).isEqualTo(DEFAULT_CHARACTER_ID);
        assertThat(testMPlayableCard.getNickName()).isEqualTo(DEFAULT_NICK_NAME);
        assertThat(testMPlayableCard.getTeamId()).isEqualTo(DEFAULT_TEAM_ID);
        assertThat(testMPlayableCard.getNationalityId()).isEqualTo(DEFAULT_NATIONALITY_ID);
        assertThat(testMPlayableCard.getRarity()).isEqualTo(DEFAULT_RARITY);
        assertThat(testMPlayableCard.getAttribute()).isEqualTo(DEFAULT_ATTRIBUTE);
        assertThat(testMPlayableCard.getThumbnailAssetsId()).isEqualTo(DEFAULT_THUMBNAIL_ASSETS_ID);
        assertThat(testMPlayableCard.getCardIllustAssetsId()).isEqualTo(DEFAULT_CARD_ILLUST_ASSETS_ID);
        assertThat(testMPlayableCard.getPlayableAssetsId()).isEqualTo(DEFAULT_PLAYABLE_ASSETS_ID);
        assertThat(testMPlayableCard.getTeamEffectId()).isEqualTo(DEFAULT_TEAM_EFFECT_ID);
        assertThat(testMPlayableCard.getTriggerEffectId()).isEqualTo(DEFAULT_TRIGGER_EFFECT_ID);
        assertThat(testMPlayableCard.getMaxActionSlot()).isEqualTo(DEFAULT_MAX_ACTION_SLOT);
        assertThat(testMPlayableCard.getInitialActionId1()).isEqualTo(DEFAULT_INITIAL_ACTION_ID_1);
        assertThat(testMPlayableCard.getInitialActionId2()).isEqualTo(DEFAULT_INITIAL_ACTION_ID_2);
        assertThat(testMPlayableCard.getInitialActionId3()).isEqualTo(DEFAULT_INITIAL_ACTION_ID_3);
        assertThat(testMPlayableCard.getInitialActionId4()).isEqualTo(DEFAULT_INITIAL_ACTION_ID_4);
        assertThat(testMPlayableCard.getInitialActionId5()).isEqualTo(DEFAULT_INITIAL_ACTION_ID_5);
        assertThat(testMPlayableCard.getInitialStamina()).isEqualTo(DEFAULT_INITIAL_STAMINA);
        assertThat(testMPlayableCard.getInitialDribble()).isEqualTo(DEFAULT_INITIAL_DRIBBLE);
        assertThat(testMPlayableCard.getInitialShoot()).isEqualTo(DEFAULT_INITIAL_SHOOT);
        assertThat(testMPlayableCard.getInitialPass()).isEqualTo(DEFAULT_INITIAL_PASS);
        assertThat(testMPlayableCard.getInitialTackle()).isEqualTo(DEFAULT_INITIAL_TACKLE);
        assertThat(testMPlayableCard.getInitialBlock()).isEqualTo(DEFAULT_INITIAL_BLOCK);
        assertThat(testMPlayableCard.getInitialIntercept()).isEqualTo(DEFAULT_INITIAL_INTERCEPT);
        assertThat(testMPlayableCard.getInitialSpeed()).isEqualTo(DEFAULT_INITIAL_SPEED);
        assertThat(testMPlayableCard.getInitialPower()).isEqualTo(DEFAULT_INITIAL_POWER);
        assertThat(testMPlayableCard.getInitialTechnique()).isEqualTo(DEFAULT_INITIAL_TECHNIQUE);
        assertThat(testMPlayableCard.getInitialPunching()).isEqualTo(DEFAULT_INITIAL_PUNCHING);
        assertThat(testMPlayableCard.getInitialCatching()).isEqualTo(DEFAULT_INITIAL_CATCHING);
        assertThat(testMPlayableCard.getMaxStamina()).isEqualTo(DEFAULT_MAX_STAMINA);
        assertThat(testMPlayableCard.getMaxDribble()).isEqualTo(DEFAULT_MAX_DRIBBLE);
        assertThat(testMPlayableCard.getMaxShoot()).isEqualTo(DEFAULT_MAX_SHOOT);
        assertThat(testMPlayableCard.getMaxPass()).isEqualTo(DEFAULT_MAX_PASS);
        assertThat(testMPlayableCard.getMaxTackle()).isEqualTo(DEFAULT_MAX_TACKLE);
        assertThat(testMPlayableCard.getMaxBlock()).isEqualTo(DEFAULT_MAX_BLOCK);
        assertThat(testMPlayableCard.getMaxIntercept()).isEqualTo(DEFAULT_MAX_INTERCEPT);
        assertThat(testMPlayableCard.getMaxSpeed()).isEqualTo(DEFAULT_MAX_SPEED);
        assertThat(testMPlayableCard.getMaxPower()).isEqualTo(DEFAULT_MAX_POWER);
        assertThat(testMPlayableCard.getMaxTechnique()).isEqualTo(DEFAULT_MAX_TECHNIQUE);
        assertThat(testMPlayableCard.getMaxPunching()).isEqualTo(DEFAULT_MAX_PUNCHING);
        assertThat(testMPlayableCard.getMaxCatching()).isEqualTo(DEFAULT_MAX_CATCHING);
        assertThat(testMPlayableCard.getMaxPlusDribble()).isEqualTo(DEFAULT_MAX_PLUS_DRIBBLE);
        assertThat(testMPlayableCard.getMaxPlusShoot()).isEqualTo(DEFAULT_MAX_PLUS_SHOOT);
        assertThat(testMPlayableCard.getMaxPlusPass()).isEqualTo(DEFAULT_MAX_PLUS_PASS);
        assertThat(testMPlayableCard.getMaxPlusTackle()).isEqualTo(DEFAULT_MAX_PLUS_TACKLE);
        assertThat(testMPlayableCard.getMaxPlusBlock()).isEqualTo(DEFAULT_MAX_PLUS_BLOCK);
        assertThat(testMPlayableCard.getMaxPlusIntercept()).isEqualTo(DEFAULT_MAX_PLUS_INTERCEPT);
        assertThat(testMPlayableCard.getMaxPlusSpeed()).isEqualTo(DEFAULT_MAX_PLUS_SPEED);
        assertThat(testMPlayableCard.getMaxPlusPower()).isEqualTo(DEFAULT_MAX_PLUS_POWER);
        assertThat(testMPlayableCard.getMaxPlusTechnique()).isEqualTo(DEFAULT_MAX_PLUS_TECHNIQUE);
        assertThat(testMPlayableCard.getMaxPlusPunching()).isEqualTo(DEFAULT_MAX_PLUS_PUNCHING);
        assertThat(testMPlayableCard.getMaxPlusCatching()).isEqualTo(DEFAULT_MAX_PLUS_CATCHING);
        assertThat(testMPlayableCard.getHighBallBonus()).isEqualTo(DEFAULT_HIGH_BALL_BONUS);
        assertThat(testMPlayableCard.getLowBallBonus()).isEqualTo(DEFAULT_LOW_BALL_BONUS);
        assertThat(testMPlayableCard.getIsDropOnly()).isEqualTo(DEFAULT_IS_DROP_ONLY);
        assertThat(testMPlayableCard.getSellCoinGroupNum()).isEqualTo(DEFAULT_SELL_COIN_GROUP_NUM);
        assertThat(testMPlayableCard.getSellMedalId()).isEqualTo(DEFAULT_SELL_MEDAL_ID);
        assertThat(testMPlayableCard.getCharacterBookId()).isEqualTo(DEFAULT_CHARACTER_BOOK_ID);
        assertThat(testMPlayableCard.getBookNo()).isEqualTo(DEFAULT_BOOK_NO);
        assertThat(testMPlayableCard.getIsShowBook()).isEqualTo(DEFAULT_IS_SHOW_BOOK);
        assertThat(testMPlayableCard.getLevelGroupId()).isEqualTo(DEFAULT_LEVEL_GROUP_ID);
        assertThat(testMPlayableCard.getStartAt()).isEqualTo(DEFAULT_START_AT);
    }

    @Test
    @Transactional
    public void createMPlayableCardWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = mPlayableCardRepository.findAll().size();

        // Create the MPlayableCard with an existing ID
        mPlayableCard.setId(1L);
        MPlayableCardDTO mPlayableCardDTO = mPlayableCardMapper.toDto(mPlayableCard);

        // An entity with an existing ID cannot be created, so this API call must fail
        restMPlayableCardMockMvc.perform(post("/api/m-playable-cards")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mPlayableCardDTO)))
            .andExpect(status().isBadRequest());

        // Validate the MPlayableCard in the database
        List<MPlayableCard> mPlayableCardList = mPlayableCardRepository.findAll();
        assertThat(mPlayableCardList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkModelIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = mPlayableCardRepository.findAll().size();
        // set the field null
        mPlayableCard.setModelId(null);

        // Create the MPlayableCard, which fails.
        MPlayableCardDTO mPlayableCardDTO = mPlayableCardMapper.toDto(mPlayableCard);

        restMPlayableCardMockMvc.perform(post("/api/m-playable-cards")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mPlayableCardDTO)))
            .andExpect(status().isBadRequest());

        List<MPlayableCard> mPlayableCardList = mPlayableCardRepository.findAll();
        assertThat(mPlayableCardList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkProperPositionGkIsRequired() throws Exception {
        int databaseSizeBeforeTest = mPlayableCardRepository.findAll().size();
        // set the field null
        mPlayableCard.setProperPositionGk(null);

        // Create the MPlayableCard, which fails.
        MPlayableCardDTO mPlayableCardDTO = mPlayableCardMapper.toDto(mPlayableCard);

        restMPlayableCardMockMvc.perform(post("/api/m-playable-cards")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mPlayableCardDTO)))
            .andExpect(status().isBadRequest());

        List<MPlayableCard> mPlayableCardList = mPlayableCardRepository.findAll();
        assertThat(mPlayableCardList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkProperPositionFwIsRequired() throws Exception {
        int databaseSizeBeforeTest = mPlayableCardRepository.findAll().size();
        // set the field null
        mPlayableCard.setProperPositionFw(null);

        // Create the MPlayableCard, which fails.
        MPlayableCardDTO mPlayableCardDTO = mPlayableCardMapper.toDto(mPlayableCard);

        restMPlayableCardMockMvc.perform(post("/api/m-playable-cards")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mPlayableCardDTO)))
            .andExpect(status().isBadRequest());

        List<MPlayableCard> mPlayableCardList = mPlayableCardRepository.findAll();
        assertThat(mPlayableCardList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkProperPositionOmfIsRequired() throws Exception {
        int databaseSizeBeforeTest = mPlayableCardRepository.findAll().size();
        // set the field null
        mPlayableCard.setProperPositionOmf(null);

        // Create the MPlayableCard, which fails.
        MPlayableCardDTO mPlayableCardDTO = mPlayableCardMapper.toDto(mPlayableCard);

        restMPlayableCardMockMvc.perform(post("/api/m-playable-cards")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mPlayableCardDTO)))
            .andExpect(status().isBadRequest());

        List<MPlayableCard> mPlayableCardList = mPlayableCardRepository.findAll();
        assertThat(mPlayableCardList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkProperPositionDmfIsRequired() throws Exception {
        int databaseSizeBeforeTest = mPlayableCardRepository.findAll().size();
        // set the field null
        mPlayableCard.setProperPositionDmf(null);

        // Create the MPlayableCard, which fails.
        MPlayableCardDTO mPlayableCardDTO = mPlayableCardMapper.toDto(mPlayableCard);

        restMPlayableCardMockMvc.perform(post("/api/m-playable-cards")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mPlayableCardDTO)))
            .andExpect(status().isBadRequest());

        List<MPlayableCard> mPlayableCardList = mPlayableCardRepository.findAll();
        assertThat(mPlayableCardList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkProperPositionDfIsRequired() throws Exception {
        int databaseSizeBeforeTest = mPlayableCardRepository.findAll().size();
        // set the field null
        mPlayableCard.setProperPositionDf(null);

        // Create the MPlayableCard, which fails.
        MPlayableCardDTO mPlayableCardDTO = mPlayableCardMapper.toDto(mPlayableCard);

        restMPlayableCardMockMvc.perform(post("/api/m-playable-cards")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mPlayableCardDTO)))
            .andExpect(status().isBadRequest());

        List<MPlayableCard> mPlayableCardList = mPlayableCardRepository.findAll();
        assertThat(mPlayableCardList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkCharacterIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = mPlayableCardRepository.findAll().size();
        // set the field null
        mPlayableCard.setCharacterId(null);

        // Create the MPlayableCard, which fails.
        MPlayableCardDTO mPlayableCardDTO = mPlayableCardMapper.toDto(mPlayableCard);

        restMPlayableCardMockMvc.perform(post("/api/m-playable-cards")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mPlayableCardDTO)))
            .andExpect(status().isBadRequest());

        List<MPlayableCard> mPlayableCardList = mPlayableCardRepository.findAll();
        assertThat(mPlayableCardList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkTeamIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = mPlayableCardRepository.findAll().size();
        // set the field null
        mPlayableCard.setTeamId(null);

        // Create the MPlayableCard, which fails.
        MPlayableCardDTO mPlayableCardDTO = mPlayableCardMapper.toDto(mPlayableCard);

        restMPlayableCardMockMvc.perform(post("/api/m-playable-cards")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mPlayableCardDTO)))
            .andExpect(status().isBadRequest());

        List<MPlayableCard> mPlayableCardList = mPlayableCardRepository.findAll();
        assertThat(mPlayableCardList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkNationalityIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = mPlayableCardRepository.findAll().size();
        // set the field null
        mPlayableCard.setNationalityId(null);

        // Create the MPlayableCard, which fails.
        MPlayableCardDTO mPlayableCardDTO = mPlayableCardMapper.toDto(mPlayableCard);

        restMPlayableCardMockMvc.perform(post("/api/m-playable-cards")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mPlayableCardDTO)))
            .andExpect(status().isBadRequest());

        List<MPlayableCard> mPlayableCardList = mPlayableCardRepository.findAll();
        assertThat(mPlayableCardList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkRarityIsRequired() throws Exception {
        int databaseSizeBeforeTest = mPlayableCardRepository.findAll().size();
        // set the field null
        mPlayableCard.setRarity(null);

        // Create the MPlayableCard, which fails.
        MPlayableCardDTO mPlayableCardDTO = mPlayableCardMapper.toDto(mPlayableCard);

        restMPlayableCardMockMvc.perform(post("/api/m-playable-cards")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mPlayableCardDTO)))
            .andExpect(status().isBadRequest());

        List<MPlayableCard> mPlayableCardList = mPlayableCardRepository.findAll();
        assertThat(mPlayableCardList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkAttributeIsRequired() throws Exception {
        int databaseSizeBeforeTest = mPlayableCardRepository.findAll().size();
        // set the field null
        mPlayableCard.setAttribute(null);

        // Create the MPlayableCard, which fails.
        MPlayableCardDTO mPlayableCardDTO = mPlayableCardMapper.toDto(mPlayableCard);

        restMPlayableCardMockMvc.perform(post("/api/m-playable-cards")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mPlayableCardDTO)))
            .andExpect(status().isBadRequest());

        List<MPlayableCard> mPlayableCardList = mPlayableCardRepository.findAll();
        assertThat(mPlayableCardList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkThumbnailAssetsIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = mPlayableCardRepository.findAll().size();
        // set the field null
        mPlayableCard.setThumbnailAssetsId(null);

        // Create the MPlayableCard, which fails.
        MPlayableCardDTO mPlayableCardDTO = mPlayableCardMapper.toDto(mPlayableCard);

        restMPlayableCardMockMvc.perform(post("/api/m-playable-cards")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mPlayableCardDTO)))
            .andExpect(status().isBadRequest());

        List<MPlayableCard> mPlayableCardList = mPlayableCardRepository.findAll();
        assertThat(mPlayableCardList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkCardIllustAssetsIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = mPlayableCardRepository.findAll().size();
        // set the field null
        mPlayableCard.setCardIllustAssetsId(null);

        // Create the MPlayableCard, which fails.
        MPlayableCardDTO mPlayableCardDTO = mPlayableCardMapper.toDto(mPlayableCard);

        restMPlayableCardMockMvc.perform(post("/api/m-playable-cards")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mPlayableCardDTO)))
            .andExpect(status().isBadRequest());

        List<MPlayableCard> mPlayableCardList = mPlayableCardRepository.findAll();
        assertThat(mPlayableCardList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkPlayableAssetsIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = mPlayableCardRepository.findAll().size();
        // set the field null
        mPlayableCard.setPlayableAssetsId(null);

        // Create the MPlayableCard, which fails.
        MPlayableCardDTO mPlayableCardDTO = mPlayableCardMapper.toDto(mPlayableCard);

        restMPlayableCardMockMvc.perform(post("/api/m-playable-cards")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mPlayableCardDTO)))
            .andExpect(status().isBadRequest());

        List<MPlayableCard> mPlayableCardList = mPlayableCardRepository.findAll();
        assertThat(mPlayableCardList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkMaxActionSlotIsRequired() throws Exception {
        int databaseSizeBeforeTest = mPlayableCardRepository.findAll().size();
        // set the field null
        mPlayableCard.setMaxActionSlot(null);

        // Create the MPlayableCard, which fails.
        MPlayableCardDTO mPlayableCardDTO = mPlayableCardMapper.toDto(mPlayableCard);

        restMPlayableCardMockMvc.perform(post("/api/m-playable-cards")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mPlayableCardDTO)))
            .andExpect(status().isBadRequest());

        List<MPlayableCard> mPlayableCardList = mPlayableCardRepository.findAll();
        assertThat(mPlayableCardList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkInitialStaminaIsRequired() throws Exception {
        int databaseSizeBeforeTest = mPlayableCardRepository.findAll().size();
        // set the field null
        mPlayableCard.setInitialStamina(null);

        // Create the MPlayableCard, which fails.
        MPlayableCardDTO mPlayableCardDTO = mPlayableCardMapper.toDto(mPlayableCard);

        restMPlayableCardMockMvc.perform(post("/api/m-playable-cards")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mPlayableCardDTO)))
            .andExpect(status().isBadRequest());

        List<MPlayableCard> mPlayableCardList = mPlayableCardRepository.findAll();
        assertThat(mPlayableCardList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkInitialDribbleIsRequired() throws Exception {
        int databaseSizeBeforeTest = mPlayableCardRepository.findAll().size();
        // set the field null
        mPlayableCard.setInitialDribble(null);

        // Create the MPlayableCard, which fails.
        MPlayableCardDTO mPlayableCardDTO = mPlayableCardMapper.toDto(mPlayableCard);

        restMPlayableCardMockMvc.perform(post("/api/m-playable-cards")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mPlayableCardDTO)))
            .andExpect(status().isBadRequest());

        List<MPlayableCard> mPlayableCardList = mPlayableCardRepository.findAll();
        assertThat(mPlayableCardList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkInitialShootIsRequired() throws Exception {
        int databaseSizeBeforeTest = mPlayableCardRepository.findAll().size();
        // set the field null
        mPlayableCard.setInitialShoot(null);

        // Create the MPlayableCard, which fails.
        MPlayableCardDTO mPlayableCardDTO = mPlayableCardMapper.toDto(mPlayableCard);

        restMPlayableCardMockMvc.perform(post("/api/m-playable-cards")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mPlayableCardDTO)))
            .andExpect(status().isBadRequest());

        List<MPlayableCard> mPlayableCardList = mPlayableCardRepository.findAll();
        assertThat(mPlayableCardList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkInitialPassIsRequired() throws Exception {
        int databaseSizeBeforeTest = mPlayableCardRepository.findAll().size();
        // set the field null
        mPlayableCard.setInitialPass(null);

        // Create the MPlayableCard, which fails.
        MPlayableCardDTO mPlayableCardDTO = mPlayableCardMapper.toDto(mPlayableCard);

        restMPlayableCardMockMvc.perform(post("/api/m-playable-cards")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mPlayableCardDTO)))
            .andExpect(status().isBadRequest());

        List<MPlayableCard> mPlayableCardList = mPlayableCardRepository.findAll();
        assertThat(mPlayableCardList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkInitialTackleIsRequired() throws Exception {
        int databaseSizeBeforeTest = mPlayableCardRepository.findAll().size();
        // set the field null
        mPlayableCard.setInitialTackle(null);

        // Create the MPlayableCard, which fails.
        MPlayableCardDTO mPlayableCardDTO = mPlayableCardMapper.toDto(mPlayableCard);

        restMPlayableCardMockMvc.perform(post("/api/m-playable-cards")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mPlayableCardDTO)))
            .andExpect(status().isBadRequest());

        List<MPlayableCard> mPlayableCardList = mPlayableCardRepository.findAll();
        assertThat(mPlayableCardList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkInitialBlockIsRequired() throws Exception {
        int databaseSizeBeforeTest = mPlayableCardRepository.findAll().size();
        // set the field null
        mPlayableCard.setInitialBlock(null);

        // Create the MPlayableCard, which fails.
        MPlayableCardDTO mPlayableCardDTO = mPlayableCardMapper.toDto(mPlayableCard);

        restMPlayableCardMockMvc.perform(post("/api/m-playable-cards")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mPlayableCardDTO)))
            .andExpect(status().isBadRequest());

        List<MPlayableCard> mPlayableCardList = mPlayableCardRepository.findAll();
        assertThat(mPlayableCardList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkInitialInterceptIsRequired() throws Exception {
        int databaseSizeBeforeTest = mPlayableCardRepository.findAll().size();
        // set the field null
        mPlayableCard.setInitialIntercept(null);

        // Create the MPlayableCard, which fails.
        MPlayableCardDTO mPlayableCardDTO = mPlayableCardMapper.toDto(mPlayableCard);

        restMPlayableCardMockMvc.perform(post("/api/m-playable-cards")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mPlayableCardDTO)))
            .andExpect(status().isBadRequest());

        List<MPlayableCard> mPlayableCardList = mPlayableCardRepository.findAll();
        assertThat(mPlayableCardList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkInitialSpeedIsRequired() throws Exception {
        int databaseSizeBeforeTest = mPlayableCardRepository.findAll().size();
        // set the field null
        mPlayableCard.setInitialSpeed(null);

        // Create the MPlayableCard, which fails.
        MPlayableCardDTO mPlayableCardDTO = mPlayableCardMapper.toDto(mPlayableCard);

        restMPlayableCardMockMvc.perform(post("/api/m-playable-cards")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mPlayableCardDTO)))
            .andExpect(status().isBadRequest());

        List<MPlayableCard> mPlayableCardList = mPlayableCardRepository.findAll();
        assertThat(mPlayableCardList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkInitialPowerIsRequired() throws Exception {
        int databaseSizeBeforeTest = mPlayableCardRepository.findAll().size();
        // set the field null
        mPlayableCard.setInitialPower(null);

        // Create the MPlayableCard, which fails.
        MPlayableCardDTO mPlayableCardDTO = mPlayableCardMapper.toDto(mPlayableCard);

        restMPlayableCardMockMvc.perform(post("/api/m-playable-cards")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mPlayableCardDTO)))
            .andExpect(status().isBadRequest());

        List<MPlayableCard> mPlayableCardList = mPlayableCardRepository.findAll();
        assertThat(mPlayableCardList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkInitialTechniqueIsRequired() throws Exception {
        int databaseSizeBeforeTest = mPlayableCardRepository.findAll().size();
        // set the field null
        mPlayableCard.setInitialTechnique(null);

        // Create the MPlayableCard, which fails.
        MPlayableCardDTO mPlayableCardDTO = mPlayableCardMapper.toDto(mPlayableCard);

        restMPlayableCardMockMvc.perform(post("/api/m-playable-cards")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mPlayableCardDTO)))
            .andExpect(status().isBadRequest());

        List<MPlayableCard> mPlayableCardList = mPlayableCardRepository.findAll();
        assertThat(mPlayableCardList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkInitialPunchingIsRequired() throws Exception {
        int databaseSizeBeforeTest = mPlayableCardRepository.findAll().size();
        // set the field null
        mPlayableCard.setInitialPunching(null);

        // Create the MPlayableCard, which fails.
        MPlayableCardDTO mPlayableCardDTO = mPlayableCardMapper.toDto(mPlayableCard);

        restMPlayableCardMockMvc.perform(post("/api/m-playable-cards")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mPlayableCardDTO)))
            .andExpect(status().isBadRequest());

        List<MPlayableCard> mPlayableCardList = mPlayableCardRepository.findAll();
        assertThat(mPlayableCardList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkInitialCatchingIsRequired() throws Exception {
        int databaseSizeBeforeTest = mPlayableCardRepository.findAll().size();
        // set the field null
        mPlayableCard.setInitialCatching(null);

        // Create the MPlayableCard, which fails.
        MPlayableCardDTO mPlayableCardDTO = mPlayableCardMapper.toDto(mPlayableCard);

        restMPlayableCardMockMvc.perform(post("/api/m-playable-cards")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mPlayableCardDTO)))
            .andExpect(status().isBadRequest());

        List<MPlayableCard> mPlayableCardList = mPlayableCardRepository.findAll();
        assertThat(mPlayableCardList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkMaxStaminaIsRequired() throws Exception {
        int databaseSizeBeforeTest = mPlayableCardRepository.findAll().size();
        // set the field null
        mPlayableCard.setMaxStamina(null);

        // Create the MPlayableCard, which fails.
        MPlayableCardDTO mPlayableCardDTO = mPlayableCardMapper.toDto(mPlayableCard);

        restMPlayableCardMockMvc.perform(post("/api/m-playable-cards")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mPlayableCardDTO)))
            .andExpect(status().isBadRequest());

        List<MPlayableCard> mPlayableCardList = mPlayableCardRepository.findAll();
        assertThat(mPlayableCardList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkMaxDribbleIsRequired() throws Exception {
        int databaseSizeBeforeTest = mPlayableCardRepository.findAll().size();
        // set the field null
        mPlayableCard.setMaxDribble(null);

        // Create the MPlayableCard, which fails.
        MPlayableCardDTO mPlayableCardDTO = mPlayableCardMapper.toDto(mPlayableCard);

        restMPlayableCardMockMvc.perform(post("/api/m-playable-cards")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mPlayableCardDTO)))
            .andExpect(status().isBadRequest());

        List<MPlayableCard> mPlayableCardList = mPlayableCardRepository.findAll();
        assertThat(mPlayableCardList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkMaxShootIsRequired() throws Exception {
        int databaseSizeBeforeTest = mPlayableCardRepository.findAll().size();
        // set the field null
        mPlayableCard.setMaxShoot(null);

        // Create the MPlayableCard, which fails.
        MPlayableCardDTO mPlayableCardDTO = mPlayableCardMapper.toDto(mPlayableCard);

        restMPlayableCardMockMvc.perform(post("/api/m-playable-cards")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mPlayableCardDTO)))
            .andExpect(status().isBadRequest());

        List<MPlayableCard> mPlayableCardList = mPlayableCardRepository.findAll();
        assertThat(mPlayableCardList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkMaxPassIsRequired() throws Exception {
        int databaseSizeBeforeTest = mPlayableCardRepository.findAll().size();
        // set the field null
        mPlayableCard.setMaxPass(null);

        // Create the MPlayableCard, which fails.
        MPlayableCardDTO mPlayableCardDTO = mPlayableCardMapper.toDto(mPlayableCard);

        restMPlayableCardMockMvc.perform(post("/api/m-playable-cards")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mPlayableCardDTO)))
            .andExpect(status().isBadRequest());

        List<MPlayableCard> mPlayableCardList = mPlayableCardRepository.findAll();
        assertThat(mPlayableCardList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkMaxTackleIsRequired() throws Exception {
        int databaseSizeBeforeTest = mPlayableCardRepository.findAll().size();
        // set the field null
        mPlayableCard.setMaxTackle(null);

        // Create the MPlayableCard, which fails.
        MPlayableCardDTO mPlayableCardDTO = mPlayableCardMapper.toDto(mPlayableCard);

        restMPlayableCardMockMvc.perform(post("/api/m-playable-cards")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mPlayableCardDTO)))
            .andExpect(status().isBadRequest());

        List<MPlayableCard> mPlayableCardList = mPlayableCardRepository.findAll();
        assertThat(mPlayableCardList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkMaxBlockIsRequired() throws Exception {
        int databaseSizeBeforeTest = mPlayableCardRepository.findAll().size();
        // set the field null
        mPlayableCard.setMaxBlock(null);

        // Create the MPlayableCard, which fails.
        MPlayableCardDTO mPlayableCardDTO = mPlayableCardMapper.toDto(mPlayableCard);

        restMPlayableCardMockMvc.perform(post("/api/m-playable-cards")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mPlayableCardDTO)))
            .andExpect(status().isBadRequest());

        List<MPlayableCard> mPlayableCardList = mPlayableCardRepository.findAll();
        assertThat(mPlayableCardList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkMaxInterceptIsRequired() throws Exception {
        int databaseSizeBeforeTest = mPlayableCardRepository.findAll().size();
        // set the field null
        mPlayableCard.setMaxIntercept(null);

        // Create the MPlayableCard, which fails.
        MPlayableCardDTO mPlayableCardDTO = mPlayableCardMapper.toDto(mPlayableCard);

        restMPlayableCardMockMvc.perform(post("/api/m-playable-cards")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mPlayableCardDTO)))
            .andExpect(status().isBadRequest());

        List<MPlayableCard> mPlayableCardList = mPlayableCardRepository.findAll();
        assertThat(mPlayableCardList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkMaxSpeedIsRequired() throws Exception {
        int databaseSizeBeforeTest = mPlayableCardRepository.findAll().size();
        // set the field null
        mPlayableCard.setMaxSpeed(null);

        // Create the MPlayableCard, which fails.
        MPlayableCardDTO mPlayableCardDTO = mPlayableCardMapper.toDto(mPlayableCard);

        restMPlayableCardMockMvc.perform(post("/api/m-playable-cards")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mPlayableCardDTO)))
            .andExpect(status().isBadRequest());

        List<MPlayableCard> mPlayableCardList = mPlayableCardRepository.findAll();
        assertThat(mPlayableCardList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkMaxPowerIsRequired() throws Exception {
        int databaseSizeBeforeTest = mPlayableCardRepository.findAll().size();
        // set the field null
        mPlayableCard.setMaxPower(null);

        // Create the MPlayableCard, which fails.
        MPlayableCardDTO mPlayableCardDTO = mPlayableCardMapper.toDto(mPlayableCard);

        restMPlayableCardMockMvc.perform(post("/api/m-playable-cards")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mPlayableCardDTO)))
            .andExpect(status().isBadRequest());

        List<MPlayableCard> mPlayableCardList = mPlayableCardRepository.findAll();
        assertThat(mPlayableCardList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkMaxTechniqueIsRequired() throws Exception {
        int databaseSizeBeforeTest = mPlayableCardRepository.findAll().size();
        // set the field null
        mPlayableCard.setMaxTechnique(null);

        // Create the MPlayableCard, which fails.
        MPlayableCardDTO mPlayableCardDTO = mPlayableCardMapper.toDto(mPlayableCard);

        restMPlayableCardMockMvc.perform(post("/api/m-playable-cards")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mPlayableCardDTO)))
            .andExpect(status().isBadRequest());

        List<MPlayableCard> mPlayableCardList = mPlayableCardRepository.findAll();
        assertThat(mPlayableCardList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkMaxPunchingIsRequired() throws Exception {
        int databaseSizeBeforeTest = mPlayableCardRepository.findAll().size();
        // set the field null
        mPlayableCard.setMaxPunching(null);

        // Create the MPlayableCard, which fails.
        MPlayableCardDTO mPlayableCardDTO = mPlayableCardMapper.toDto(mPlayableCard);

        restMPlayableCardMockMvc.perform(post("/api/m-playable-cards")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mPlayableCardDTO)))
            .andExpect(status().isBadRequest());

        List<MPlayableCard> mPlayableCardList = mPlayableCardRepository.findAll();
        assertThat(mPlayableCardList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkMaxCatchingIsRequired() throws Exception {
        int databaseSizeBeforeTest = mPlayableCardRepository.findAll().size();
        // set the field null
        mPlayableCard.setMaxCatching(null);

        // Create the MPlayableCard, which fails.
        MPlayableCardDTO mPlayableCardDTO = mPlayableCardMapper.toDto(mPlayableCard);

        restMPlayableCardMockMvc.perform(post("/api/m-playable-cards")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mPlayableCardDTO)))
            .andExpect(status().isBadRequest());

        List<MPlayableCard> mPlayableCardList = mPlayableCardRepository.findAll();
        assertThat(mPlayableCardList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkMaxPlusDribbleIsRequired() throws Exception {
        int databaseSizeBeforeTest = mPlayableCardRepository.findAll().size();
        // set the field null
        mPlayableCard.setMaxPlusDribble(null);

        // Create the MPlayableCard, which fails.
        MPlayableCardDTO mPlayableCardDTO = mPlayableCardMapper.toDto(mPlayableCard);

        restMPlayableCardMockMvc.perform(post("/api/m-playable-cards")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mPlayableCardDTO)))
            .andExpect(status().isBadRequest());

        List<MPlayableCard> mPlayableCardList = mPlayableCardRepository.findAll();
        assertThat(mPlayableCardList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkMaxPlusShootIsRequired() throws Exception {
        int databaseSizeBeforeTest = mPlayableCardRepository.findAll().size();
        // set the field null
        mPlayableCard.setMaxPlusShoot(null);

        // Create the MPlayableCard, which fails.
        MPlayableCardDTO mPlayableCardDTO = mPlayableCardMapper.toDto(mPlayableCard);

        restMPlayableCardMockMvc.perform(post("/api/m-playable-cards")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mPlayableCardDTO)))
            .andExpect(status().isBadRequest());

        List<MPlayableCard> mPlayableCardList = mPlayableCardRepository.findAll();
        assertThat(mPlayableCardList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkMaxPlusPassIsRequired() throws Exception {
        int databaseSizeBeforeTest = mPlayableCardRepository.findAll().size();
        // set the field null
        mPlayableCard.setMaxPlusPass(null);

        // Create the MPlayableCard, which fails.
        MPlayableCardDTO mPlayableCardDTO = mPlayableCardMapper.toDto(mPlayableCard);

        restMPlayableCardMockMvc.perform(post("/api/m-playable-cards")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mPlayableCardDTO)))
            .andExpect(status().isBadRequest());

        List<MPlayableCard> mPlayableCardList = mPlayableCardRepository.findAll();
        assertThat(mPlayableCardList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkMaxPlusTackleIsRequired() throws Exception {
        int databaseSizeBeforeTest = mPlayableCardRepository.findAll().size();
        // set the field null
        mPlayableCard.setMaxPlusTackle(null);

        // Create the MPlayableCard, which fails.
        MPlayableCardDTO mPlayableCardDTO = mPlayableCardMapper.toDto(mPlayableCard);

        restMPlayableCardMockMvc.perform(post("/api/m-playable-cards")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mPlayableCardDTO)))
            .andExpect(status().isBadRequest());

        List<MPlayableCard> mPlayableCardList = mPlayableCardRepository.findAll();
        assertThat(mPlayableCardList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkMaxPlusBlockIsRequired() throws Exception {
        int databaseSizeBeforeTest = mPlayableCardRepository.findAll().size();
        // set the field null
        mPlayableCard.setMaxPlusBlock(null);

        // Create the MPlayableCard, which fails.
        MPlayableCardDTO mPlayableCardDTO = mPlayableCardMapper.toDto(mPlayableCard);

        restMPlayableCardMockMvc.perform(post("/api/m-playable-cards")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mPlayableCardDTO)))
            .andExpect(status().isBadRequest());

        List<MPlayableCard> mPlayableCardList = mPlayableCardRepository.findAll();
        assertThat(mPlayableCardList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkMaxPlusInterceptIsRequired() throws Exception {
        int databaseSizeBeforeTest = mPlayableCardRepository.findAll().size();
        // set the field null
        mPlayableCard.setMaxPlusIntercept(null);

        // Create the MPlayableCard, which fails.
        MPlayableCardDTO mPlayableCardDTO = mPlayableCardMapper.toDto(mPlayableCard);

        restMPlayableCardMockMvc.perform(post("/api/m-playable-cards")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mPlayableCardDTO)))
            .andExpect(status().isBadRequest());

        List<MPlayableCard> mPlayableCardList = mPlayableCardRepository.findAll();
        assertThat(mPlayableCardList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkMaxPlusSpeedIsRequired() throws Exception {
        int databaseSizeBeforeTest = mPlayableCardRepository.findAll().size();
        // set the field null
        mPlayableCard.setMaxPlusSpeed(null);

        // Create the MPlayableCard, which fails.
        MPlayableCardDTO mPlayableCardDTO = mPlayableCardMapper.toDto(mPlayableCard);

        restMPlayableCardMockMvc.perform(post("/api/m-playable-cards")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mPlayableCardDTO)))
            .andExpect(status().isBadRequest());

        List<MPlayableCard> mPlayableCardList = mPlayableCardRepository.findAll();
        assertThat(mPlayableCardList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkMaxPlusPowerIsRequired() throws Exception {
        int databaseSizeBeforeTest = mPlayableCardRepository.findAll().size();
        // set the field null
        mPlayableCard.setMaxPlusPower(null);

        // Create the MPlayableCard, which fails.
        MPlayableCardDTO mPlayableCardDTO = mPlayableCardMapper.toDto(mPlayableCard);

        restMPlayableCardMockMvc.perform(post("/api/m-playable-cards")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mPlayableCardDTO)))
            .andExpect(status().isBadRequest());

        List<MPlayableCard> mPlayableCardList = mPlayableCardRepository.findAll();
        assertThat(mPlayableCardList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkMaxPlusTechniqueIsRequired() throws Exception {
        int databaseSizeBeforeTest = mPlayableCardRepository.findAll().size();
        // set the field null
        mPlayableCard.setMaxPlusTechnique(null);

        // Create the MPlayableCard, which fails.
        MPlayableCardDTO mPlayableCardDTO = mPlayableCardMapper.toDto(mPlayableCard);

        restMPlayableCardMockMvc.perform(post("/api/m-playable-cards")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mPlayableCardDTO)))
            .andExpect(status().isBadRequest());

        List<MPlayableCard> mPlayableCardList = mPlayableCardRepository.findAll();
        assertThat(mPlayableCardList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkMaxPlusPunchingIsRequired() throws Exception {
        int databaseSizeBeforeTest = mPlayableCardRepository.findAll().size();
        // set the field null
        mPlayableCard.setMaxPlusPunching(null);

        // Create the MPlayableCard, which fails.
        MPlayableCardDTO mPlayableCardDTO = mPlayableCardMapper.toDto(mPlayableCard);

        restMPlayableCardMockMvc.perform(post("/api/m-playable-cards")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mPlayableCardDTO)))
            .andExpect(status().isBadRequest());

        List<MPlayableCard> mPlayableCardList = mPlayableCardRepository.findAll();
        assertThat(mPlayableCardList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkMaxPlusCatchingIsRequired() throws Exception {
        int databaseSizeBeforeTest = mPlayableCardRepository.findAll().size();
        // set the field null
        mPlayableCard.setMaxPlusCatching(null);

        // Create the MPlayableCard, which fails.
        MPlayableCardDTO mPlayableCardDTO = mPlayableCardMapper.toDto(mPlayableCard);

        restMPlayableCardMockMvc.perform(post("/api/m-playable-cards")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mPlayableCardDTO)))
            .andExpect(status().isBadRequest());

        List<MPlayableCard> mPlayableCardList = mPlayableCardRepository.findAll();
        assertThat(mPlayableCardList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkHighBallBonusIsRequired() throws Exception {
        int databaseSizeBeforeTest = mPlayableCardRepository.findAll().size();
        // set the field null
        mPlayableCard.setHighBallBonus(null);

        // Create the MPlayableCard, which fails.
        MPlayableCardDTO mPlayableCardDTO = mPlayableCardMapper.toDto(mPlayableCard);

        restMPlayableCardMockMvc.perform(post("/api/m-playable-cards")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mPlayableCardDTO)))
            .andExpect(status().isBadRequest());

        List<MPlayableCard> mPlayableCardList = mPlayableCardRepository.findAll();
        assertThat(mPlayableCardList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkLowBallBonusIsRequired() throws Exception {
        int databaseSizeBeforeTest = mPlayableCardRepository.findAll().size();
        // set the field null
        mPlayableCard.setLowBallBonus(null);

        // Create the MPlayableCard, which fails.
        MPlayableCardDTO mPlayableCardDTO = mPlayableCardMapper.toDto(mPlayableCard);

        restMPlayableCardMockMvc.perform(post("/api/m-playable-cards")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mPlayableCardDTO)))
            .andExpect(status().isBadRequest());

        List<MPlayableCard> mPlayableCardList = mPlayableCardRepository.findAll();
        assertThat(mPlayableCardList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkIsDropOnlyIsRequired() throws Exception {
        int databaseSizeBeforeTest = mPlayableCardRepository.findAll().size();
        // set the field null
        mPlayableCard.setIsDropOnly(null);

        // Create the MPlayableCard, which fails.
        MPlayableCardDTO mPlayableCardDTO = mPlayableCardMapper.toDto(mPlayableCard);

        restMPlayableCardMockMvc.perform(post("/api/m-playable-cards")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mPlayableCardDTO)))
            .andExpect(status().isBadRequest());

        List<MPlayableCard> mPlayableCardList = mPlayableCardRepository.findAll();
        assertThat(mPlayableCardList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkSellCoinGroupNumIsRequired() throws Exception {
        int databaseSizeBeforeTest = mPlayableCardRepository.findAll().size();
        // set the field null
        mPlayableCard.setSellCoinGroupNum(null);

        // Create the MPlayableCard, which fails.
        MPlayableCardDTO mPlayableCardDTO = mPlayableCardMapper.toDto(mPlayableCard);

        restMPlayableCardMockMvc.perform(post("/api/m-playable-cards")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mPlayableCardDTO)))
            .andExpect(status().isBadRequest());

        List<MPlayableCard> mPlayableCardList = mPlayableCardRepository.findAll();
        assertThat(mPlayableCardList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkCharacterBookIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = mPlayableCardRepository.findAll().size();
        // set the field null
        mPlayableCard.setCharacterBookId(null);

        // Create the MPlayableCard, which fails.
        MPlayableCardDTO mPlayableCardDTO = mPlayableCardMapper.toDto(mPlayableCard);

        restMPlayableCardMockMvc.perform(post("/api/m-playable-cards")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mPlayableCardDTO)))
            .andExpect(status().isBadRequest());

        List<MPlayableCard> mPlayableCardList = mPlayableCardRepository.findAll();
        assertThat(mPlayableCardList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkBookNoIsRequired() throws Exception {
        int databaseSizeBeforeTest = mPlayableCardRepository.findAll().size();
        // set the field null
        mPlayableCard.setBookNo(null);

        // Create the MPlayableCard, which fails.
        MPlayableCardDTO mPlayableCardDTO = mPlayableCardMapper.toDto(mPlayableCard);

        restMPlayableCardMockMvc.perform(post("/api/m-playable-cards")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mPlayableCardDTO)))
            .andExpect(status().isBadRequest());

        List<MPlayableCard> mPlayableCardList = mPlayableCardRepository.findAll();
        assertThat(mPlayableCardList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkIsShowBookIsRequired() throws Exception {
        int databaseSizeBeforeTest = mPlayableCardRepository.findAll().size();
        // set the field null
        mPlayableCard.setIsShowBook(null);

        // Create the MPlayableCard, which fails.
        MPlayableCardDTO mPlayableCardDTO = mPlayableCardMapper.toDto(mPlayableCard);

        restMPlayableCardMockMvc.perform(post("/api/m-playable-cards")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mPlayableCardDTO)))
            .andExpect(status().isBadRequest());

        List<MPlayableCard> mPlayableCardList = mPlayableCardRepository.findAll();
        assertThat(mPlayableCardList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkLevelGroupIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = mPlayableCardRepository.findAll().size();
        // set the field null
        mPlayableCard.setLevelGroupId(null);

        // Create the MPlayableCard, which fails.
        MPlayableCardDTO mPlayableCardDTO = mPlayableCardMapper.toDto(mPlayableCard);

        restMPlayableCardMockMvc.perform(post("/api/m-playable-cards")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mPlayableCardDTO)))
            .andExpect(status().isBadRequest());

        List<MPlayableCard> mPlayableCardList = mPlayableCardRepository.findAll();
        assertThat(mPlayableCardList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllMPlayableCards() throws Exception {
        // Initialize the database
        mPlayableCardRepository.saveAndFlush(mPlayableCard);

        // Get all the mPlayableCardList
        restMPlayableCardMockMvc.perform(get("/api/m-playable-cards?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(mPlayableCard.getId().intValue())))
            .andExpect(jsonPath("$.[*].modelId").value(hasItem(DEFAULT_MODEL_ID)))
            .andExpect(jsonPath("$.[*].properPositionGk").value(hasItem(DEFAULT_PROPER_POSITION_GK)))
            .andExpect(jsonPath("$.[*].properPositionFw").value(hasItem(DEFAULT_PROPER_POSITION_FW)))
            .andExpect(jsonPath("$.[*].properPositionOmf").value(hasItem(DEFAULT_PROPER_POSITION_OMF)))
            .andExpect(jsonPath("$.[*].properPositionDmf").value(hasItem(DEFAULT_PROPER_POSITION_DMF)))
            .andExpect(jsonPath("$.[*].properPositionDf").value(hasItem(DEFAULT_PROPER_POSITION_DF)))
            .andExpect(jsonPath("$.[*].characterId").value(hasItem(DEFAULT_CHARACTER_ID)))
            .andExpect(jsonPath("$.[*].nickName").value(hasItem(DEFAULT_NICK_NAME.toString())))
            .andExpect(jsonPath("$.[*].teamId").value(hasItem(DEFAULT_TEAM_ID)))
            .andExpect(jsonPath("$.[*].nationalityId").value(hasItem(DEFAULT_NATIONALITY_ID)))
            .andExpect(jsonPath("$.[*].rarity").value(hasItem(DEFAULT_RARITY)))
            .andExpect(jsonPath("$.[*].attribute").value(hasItem(DEFAULT_ATTRIBUTE)))
            .andExpect(jsonPath("$.[*].thumbnailAssetsId").value(hasItem(DEFAULT_THUMBNAIL_ASSETS_ID)))
            .andExpect(jsonPath("$.[*].cardIllustAssetsId").value(hasItem(DEFAULT_CARD_ILLUST_ASSETS_ID)))
            .andExpect(jsonPath("$.[*].playableAssetsId").value(hasItem(DEFAULT_PLAYABLE_ASSETS_ID)))
            .andExpect(jsonPath("$.[*].teamEffectId").value(hasItem(DEFAULT_TEAM_EFFECT_ID)))
            .andExpect(jsonPath("$.[*].triggerEffectId").value(hasItem(DEFAULT_TRIGGER_EFFECT_ID)))
            .andExpect(jsonPath("$.[*].maxActionSlot").value(hasItem(DEFAULT_MAX_ACTION_SLOT)))
            .andExpect(jsonPath("$.[*].initialActionId1").value(hasItem(DEFAULT_INITIAL_ACTION_ID_1)))
            .andExpect(jsonPath("$.[*].initialActionId2").value(hasItem(DEFAULT_INITIAL_ACTION_ID_2)))
            .andExpect(jsonPath("$.[*].initialActionId3").value(hasItem(DEFAULT_INITIAL_ACTION_ID_3)))
            .andExpect(jsonPath("$.[*].initialActionId4").value(hasItem(DEFAULT_INITIAL_ACTION_ID_4)))
            .andExpect(jsonPath("$.[*].initialActionId5").value(hasItem(DEFAULT_INITIAL_ACTION_ID_5)))
            .andExpect(jsonPath("$.[*].initialStamina").value(hasItem(DEFAULT_INITIAL_STAMINA)))
            .andExpect(jsonPath("$.[*].initialDribble").value(hasItem(DEFAULT_INITIAL_DRIBBLE)))
            .andExpect(jsonPath("$.[*].initialShoot").value(hasItem(DEFAULT_INITIAL_SHOOT)))
            .andExpect(jsonPath("$.[*].initialPass").value(hasItem(DEFAULT_INITIAL_PASS)))
            .andExpect(jsonPath("$.[*].initialTackle").value(hasItem(DEFAULT_INITIAL_TACKLE)))
            .andExpect(jsonPath("$.[*].initialBlock").value(hasItem(DEFAULT_INITIAL_BLOCK)))
            .andExpect(jsonPath("$.[*].initialIntercept").value(hasItem(DEFAULT_INITIAL_INTERCEPT)))
            .andExpect(jsonPath("$.[*].initialSpeed").value(hasItem(DEFAULT_INITIAL_SPEED)))
            .andExpect(jsonPath("$.[*].initialPower").value(hasItem(DEFAULT_INITIAL_POWER)))
            .andExpect(jsonPath("$.[*].initialTechnique").value(hasItem(DEFAULT_INITIAL_TECHNIQUE)))
            .andExpect(jsonPath("$.[*].initialPunching").value(hasItem(DEFAULT_INITIAL_PUNCHING)))
            .andExpect(jsonPath("$.[*].initialCatching").value(hasItem(DEFAULT_INITIAL_CATCHING)))
            .andExpect(jsonPath("$.[*].maxStamina").value(hasItem(DEFAULT_MAX_STAMINA)))
            .andExpect(jsonPath("$.[*].maxDribble").value(hasItem(DEFAULT_MAX_DRIBBLE)))
            .andExpect(jsonPath("$.[*].maxShoot").value(hasItem(DEFAULT_MAX_SHOOT)))
            .andExpect(jsonPath("$.[*].maxPass").value(hasItem(DEFAULT_MAX_PASS)))
            .andExpect(jsonPath("$.[*].maxTackle").value(hasItem(DEFAULT_MAX_TACKLE)))
            .andExpect(jsonPath("$.[*].maxBlock").value(hasItem(DEFAULT_MAX_BLOCK)))
            .andExpect(jsonPath("$.[*].maxIntercept").value(hasItem(DEFAULT_MAX_INTERCEPT)))
            .andExpect(jsonPath("$.[*].maxSpeed").value(hasItem(DEFAULT_MAX_SPEED)))
            .andExpect(jsonPath("$.[*].maxPower").value(hasItem(DEFAULT_MAX_POWER)))
            .andExpect(jsonPath("$.[*].maxTechnique").value(hasItem(DEFAULT_MAX_TECHNIQUE)))
            .andExpect(jsonPath("$.[*].maxPunching").value(hasItem(DEFAULT_MAX_PUNCHING)))
            .andExpect(jsonPath("$.[*].maxCatching").value(hasItem(DEFAULT_MAX_CATCHING)))
            .andExpect(jsonPath("$.[*].maxPlusDribble").value(hasItem(DEFAULT_MAX_PLUS_DRIBBLE)))
            .andExpect(jsonPath("$.[*].maxPlusShoot").value(hasItem(DEFAULT_MAX_PLUS_SHOOT)))
            .andExpect(jsonPath("$.[*].maxPlusPass").value(hasItem(DEFAULT_MAX_PLUS_PASS)))
            .andExpect(jsonPath("$.[*].maxPlusTackle").value(hasItem(DEFAULT_MAX_PLUS_TACKLE)))
            .andExpect(jsonPath("$.[*].maxPlusBlock").value(hasItem(DEFAULT_MAX_PLUS_BLOCK)))
            .andExpect(jsonPath("$.[*].maxPlusIntercept").value(hasItem(DEFAULT_MAX_PLUS_INTERCEPT)))
            .andExpect(jsonPath("$.[*].maxPlusSpeed").value(hasItem(DEFAULT_MAX_PLUS_SPEED)))
            .andExpect(jsonPath("$.[*].maxPlusPower").value(hasItem(DEFAULT_MAX_PLUS_POWER)))
            .andExpect(jsonPath("$.[*].maxPlusTechnique").value(hasItem(DEFAULT_MAX_PLUS_TECHNIQUE)))
            .andExpect(jsonPath("$.[*].maxPlusPunching").value(hasItem(DEFAULT_MAX_PLUS_PUNCHING)))
            .andExpect(jsonPath("$.[*].maxPlusCatching").value(hasItem(DEFAULT_MAX_PLUS_CATCHING)))
            .andExpect(jsonPath("$.[*].highBallBonus").value(hasItem(DEFAULT_HIGH_BALL_BONUS)))
            .andExpect(jsonPath("$.[*].lowBallBonus").value(hasItem(DEFAULT_LOW_BALL_BONUS)))
            .andExpect(jsonPath("$.[*].isDropOnly").value(hasItem(DEFAULT_IS_DROP_ONLY)))
            .andExpect(jsonPath("$.[*].sellCoinGroupNum").value(hasItem(DEFAULT_SELL_COIN_GROUP_NUM)))
            .andExpect(jsonPath("$.[*].sellMedalId").value(hasItem(DEFAULT_SELL_MEDAL_ID)))
            .andExpect(jsonPath("$.[*].characterBookId").value(hasItem(DEFAULT_CHARACTER_BOOK_ID)))
            .andExpect(jsonPath("$.[*].bookNo").value(hasItem(DEFAULT_BOOK_NO)))
            .andExpect(jsonPath("$.[*].isShowBook").value(hasItem(DEFAULT_IS_SHOW_BOOK)))
            .andExpect(jsonPath("$.[*].levelGroupId").value(hasItem(DEFAULT_LEVEL_GROUP_ID)))
            .andExpect(jsonPath("$.[*].startAt").value(hasItem(DEFAULT_START_AT)));
    }
    
    @Test
    @Transactional
    public void getMPlayableCard() throws Exception {
        // Initialize the database
        mPlayableCardRepository.saveAndFlush(mPlayableCard);

        // Get the mPlayableCard
        restMPlayableCardMockMvc.perform(get("/api/m-playable-cards/{id}", mPlayableCard.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(mPlayableCard.getId().intValue()))
            .andExpect(jsonPath("$.modelId").value(DEFAULT_MODEL_ID))
            .andExpect(jsonPath("$.properPositionGk").value(DEFAULT_PROPER_POSITION_GK))
            .andExpect(jsonPath("$.properPositionFw").value(DEFAULT_PROPER_POSITION_FW))
            .andExpect(jsonPath("$.properPositionOmf").value(DEFAULT_PROPER_POSITION_OMF))
            .andExpect(jsonPath("$.properPositionDmf").value(DEFAULT_PROPER_POSITION_DMF))
            .andExpect(jsonPath("$.properPositionDf").value(DEFAULT_PROPER_POSITION_DF))
            .andExpect(jsonPath("$.characterId").value(DEFAULT_CHARACTER_ID))
            .andExpect(jsonPath("$.nickName").value(DEFAULT_NICK_NAME.toString()))
            .andExpect(jsonPath("$.teamId").value(DEFAULT_TEAM_ID))
            .andExpect(jsonPath("$.nationalityId").value(DEFAULT_NATIONALITY_ID))
            .andExpect(jsonPath("$.rarity").value(DEFAULT_RARITY))
            .andExpect(jsonPath("$.attribute").value(DEFAULT_ATTRIBUTE))
            .andExpect(jsonPath("$.thumbnailAssetsId").value(DEFAULT_THUMBNAIL_ASSETS_ID))
            .andExpect(jsonPath("$.cardIllustAssetsId").value(DEFAULT_CARD_ILLUST_ASSETS_ID))
            .andExpect(jsonPath("$.playableAssetsId").value(DEFAULT_PLAYABLE_ASSETS_ID))
            .andExpect(jsonPath("$.teamEffectId").value(DEFAULT_TEAM_EFFECT_ID))
            .andExpect(jsonPath("$.triggerEffectId").value(DEFAULT_TRIGGER_EFFECT_ID))
            .andExpect(jsonPath("$.maxActionSlot").value(DEFAULT_MAX_ACTION_SLOT))
            .andExpect(jsonPath("$.initialActionId1").value(DEFAULT_INITIAL_ACTION_ID_1))
            .andExpect(jsonPath("$.initialActionId2").value(DEFAULT_INITIAL_ACTION_ID_2))
            .andExpect(jsonPath("$.initialActionId3").value(DEFAULT_INITIAL_ACTION_ID_3))
            .andExpect(jsonPath("$.initialActionId4").value(DEFAULT_INITIAL_ACTION_ID_4))
            .andExpect(jsonPath("$.initialActionId5").value(DEFAULT_INITIAL_ACTION_ID_5))
            .andExpect(jsonPath("$.initialStamina").value(DEFAULT_INITIAL_STAMINA))
            .andExpect(jsonPath("$.initialDribble").value(DEFAULT_INITIAL_DRIBBLE))
            .andExpect(jsonPath("$.initialShoot").value(DEFAULT_INITIAL_SHOOT))
            .andExpect(jsonPath("$.initialPass").value(DEFAULT_INITIAL_PASS))
            .andExpect(jsonPath("$.initialTackle").value(DEFAULT_INITIAL_TACKLE))
            .andExpect(jsonPath("$.initialBlock").value(DEFAULT_INITIAL_BLOCK))
            .andExpect(jsonPath("$.initialIntercept").value(DEFAULT_INITIAL_INTERCEPT))
            .andExpect(jsonPath("$.initialSpeed").value(DEFAULT_INITIAL_SPEED))
            .andExpect(jsonPath("$.initialPower").value(DEFAULT_INITIAL_POWER))
            .andExpect(jsonPath("$.initialTechnique").value(DEFAULT_INITIAL_TECHNIQUE))
            .andExpect(jsonPath("$.initialPunching").value(DEFAULT_INITIAL_PUNCHING))
            .andExpect(jsonPath("$.initialCatching").value(DEFAULT_INITIAL_CATCHING))
            .andExpect(jsonPath("$.maxStamina").value(DEFAULT_MAX_STAMINA))
            .andExpect(jsonPath("$.maxDribble").value(DEFAULT_MAX_DRIBBLE))
            .andExpect(jsonPath("$.maxShoot").value(DEFAULT_MAX_SHOOT))
            .andExpect(jsonPath("$.maxPass").value(DEFAULT_MAX_PASS))
            .andExpect(jsonPath("$.maxTackle").value(DEFAULT_MAX_TACKLE))
            .andExpect(jsonPath("$.maxBlock").value(DEFAULT_MAX_BLOCK))
            .andExpect(jsonPath("$.maxIntercept").value(DEFAULT_MAX_INTERCEPT))
            .andExpect(jsonPath("$.maxSpeed").value(DEFAULT_MAX_SPEED))
            .andExpect(jsonPath("$.maxPower").value(DEFAULT_MAX_POWER))
            .andExpect(jsonPath("$.maxTechnique").value(DEFAULT_MAX_TECHNIQUE))
            .andExpect(jsonPath("$.maxPunching").value(DEFAULT_MAX_PUNCHING))
            .andExpect(jsonPath("$.maxCatching").value(DEFAULT_MAX_CATCHING))
            .andExpect(jsonPath("$.maxPlusDribble").value(DEFAULT_MAX_PLUS_DRIBBLE))
            .andExpect(jsonPath("$.maxPlusShoot").value(DEFAULT_MAX_PLUS_SHOOT))
            .andExpect(jsonPath("$.maxPlusPass").value(DEFAULT_MAX_PLUS_PASS))
            .andExpect(jsonPath("$.maxPlusTackle").value(DEFAULT_MAX_PLUS_TACKLE))
            .andExpect(jsonPath("$.maxPlusBlock").value(DEFAULT_MAX_PLUS_BLOCK))
            .andExpect(jsonPath("$.maxPlusIntercept").value(DEFAULT_MAX_PLUS_INTERCEPT))
            .andExpect(jsonPath("$.maxPlusSpeed").value(DEFAULT_MAX_PLUS_SPEED))
            .andExpect(jsonPath("$.maxPlusPower").value(DEFAULT_MAX_PLUS_POWER))
            .andExpect(jsonPath("$.maxPlusTechnique").value(DEFAULT_MAX_PLUS_TECHNIQUE))
            .andExpect(jsonPath("$.maxPlusPunching").value(DEFAULT_MAX_PLUS_PUNCHING))
            .andExpect(jsonPath("$.maxPlusCatching").value(DEFAULT_MAX_PLUS_CATCHING))
            .andExpect(jsonPath("$.highBallBonus").value(DEFAULT_HIGH_BALL_BONUS))
            .andExpect(jsonPath("$.lowBallBonus").value(DEFAULT_LOW_BALL_BONUS))
            .andExpect(jsonPath("$.isDropOnly").value(DEFAULT_IS_DROP_ONLY))
            .andExpect(jsonPath("$.sellCoinGroupNum").value(DEFAULT_SELL_COIN_GROUP_NUM))
            .andExpect(jsonPath("$.sellMedalId").value(DEFAULT_SELL_MEDAL_ID))
            .andExpect(jsonPath("$.characterBookId").value(DEFAULT_CHARACTER_BOOK_ID))
            .andExpect(jsonPath("$.bookNo").value(DEFAULT_BOOK_NO))
            .andExpect(jsonPath("$.isShowBook").value(DEFAULT_IS_SHOW_BOOK))
            .andExpect(jsonPath("$.levelGroupId").value(DEFAULT_LEVEL_GROUP_ID))
            .andExpect(jsonPath("$.startAt").value(DEFAULT_START_AT));
    }

    @Test
    @Transactional
    public void getAllMPlayableCardsByModelIdIsEqualToSomething() throws Exception {
        // Initialize the database
        mPlayableCardRepository.saveAndFlush(mPlayableCard);

        // Get all the mPlayableCardList where modelId equals to DEFAULT_MODEL_ID
        defaultMPlayableCardShouldBeFound("modelId.equals=" + DEFAULT_MODEL_ID);

        // Get all the mPlayableCardList where modelId equals to UPDATED_MODEL_ID
        defaultMPlayableCardShouldNotBeFound("modelId.equals=" + UPDATED_MODEL_ID);
    }

    @Test
    @Transactional
    public void getAllMPlayableCardsByModelIdIsInShouldWork() throws Exception {
        // Initialize the database
        mPlayableCardRepository.saveAndFlush(mPlayableCard);

        // Get all the mPlayableCardList where modelId in DEFAULT_MODEL_ID or UPDATED_MODEL_ID
        defaultMPlayableCardShouldBeFound("modelId.in=" + DEFAULT_MODEL_ID + "," + UPDATED_MODEL_ID);

        // Get all the mPlayableCardList where modelId equals to UPDATED_MODEL_ID
        defaultMPlayableCardShouldNotBeFound("modelId.in=" + UPDATED_MODEL_ID);
    }

    @Test
    @Transactional
    public void getAllMPlayableCardsByModelIdIsNullOrNotNull() throws Exception {
        // Initialize the database
        mPlayableCardRepository.saveAndFlush(mPlayableCard);

        // Get all the mPlayableCardList where modelId is not null
        defaultMPlayableCardShouldBeFound("modelId.specified=true");

        // Get all the mPlayableCardList where modelId is null
        defaultMPlayableCardShouldNotBeFound("modelId.specified=false");
    }

    @Test
    @Transactional
    public void getAllMPlayableCardsByModelIdIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mPlayableCardRepository.saveAndFlush(mPlayableCard);

        // Get all the mPlayableCardList where modelId greater than or equals to DEFAULT_MODEL_ID
        defaultMPlayableCardShouldBeFound("modelId.greaterOrEqualThan=" + DEFAULT_MODEL_ID);

        // Get all the mPlayableCardList where modelId greater than or equals to UPDATED_MODEL_ID
        defaultMPlayableCardShouldNotBeFound("modelId.greaterOrEqualThan=" + UPDATED_MODEL_ID);
    }

    @Test
    @Transactional
    public void getAllMPlayableCardsByModelIdIsLessThanSomething() throws Exception {
        // Initialize the database
        mPlayableCardRepository.saveAndFlush(mPlayableCard);

        // Get all the mPlayableCardList where modelId less than or equals to DEFAULT_MODEL_ID
        defaultMPlayableCardShouldNotBeFound("modelId.lessThan=" + DEFAULT_MODEL_ID);

        // Get all the mPlayableCardList where modelId less than or equals to UPDATED_MODEL_ID
        defaultMPlayableCardShouldBeFound("modelId.lessThan=" + UPDATED_MODEL_ID);
    }


    @Test
    @Transactional
    public void getAllMPlayableCardsByProperPositionGkIsEqualToSomething() throws Exception {
        // Initialize the database
        mPlayableCardRepository.saveAndFlush(mPlayableCard);

        // Get all the mPlayableCardList where properPositionGk equals to DEFAULT_PROPER_POSITION_GK
        defaultMPlayableCardShouldBeFound("properPositionGk.equals=" + DEFAULT_PROPER_POSITION_GK);

        // Get all the mPlayableCardList where properPositionGk equals to UPDATED_PROPER_POSITION_GK
        defaultMPlayableCardShouldNotBeFound("properPositionGk.equals=" + UPDATED_PROPER_POSITION_GK);
    }

    @Test
    @Transactional
    public void getAllMPlayableCardsByProperPositionGkIsInShouldWork() throws Exception {
        // Initialize the database
        mPlayableCardRepository.saveAndFlush(mPlayableCard);

        // Get all the mPlayableCardList where properPositionGk in DEFAULT_PROPER_POSITION_GK or UPDATED_PROPER_POSITION_GK
        defaultMPlayableCardShouldBeFound("properPositionGk.in=" + DEFAULT_PROPER_POSITION_GK + "," + UPDATED_PROPER_POSITION_GK);

        // Get all the mPlayableCardList where properPositionGk equals to UPDATED_PROPER_POSITION_GK
        defaultMPlayableCardShouldNotBeFound("properPositionGk.in=" + UPDATED_PROPER_POSITION_GK);
    }

    @Test
    @Transactional
    public void getAllMPlayableCardsByProperPositionGkIsNullOrNotNull() throws Exception {
        // Initialize the database
        mPlayableCardRepository.saveAndFlush(mPlayableCard);

        // Get all the mPlayableCardList where properPositionGk is not null
        defaultMPlayableCardShouldBeFound("properPositionGk.specified=true");

        // Get all the mPlayableCardList where properPositionGk is null
        defaultMPlayableCardShouldNotBeFound("properPositionGk.specified=false");
    }

    @Test
    @Transactional
    public void getAllMPlayableCardsByProperPositionGkIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mPlayableCardRepository.saveAndFlush(mPlayableCard);

        // Get all the mPlayableCardList where properPositionGk greater than or equals to DEFAULT_PROPER_POSITION_GK
        defaultMPlayableCardShouldBeFound("properPositionGk.greaterOrEqualThan=" + DEFAULT_PROPER_POSITION_GK);

        // Get all the mPlayableCardList where properPositionGk greater than or equals to UPDATED_PROPER_POSITION_GK
        defaultMPlayableCardShouldNotBeFound("properPositionGk.greaterOrEqualThan=" + UPDATED_PROPER_POSITION_GK);
    }

    @Test
    @Transactional
    public void getAllMPlayableCardsByProperPositionGkIsLessThanSomething() throws Exception {
        // Initialize the database
        mPlayableCardRepository.saveAndFlush(mPlayableCard);

        // Get all the mPlayableCardList where properPositionGk less than or equals to DEFAULT_PROPER_POSITION_GK
        defaultMPlayableCardShouldNotBeFound("properPositionGk.lessThan=" + DEFAULT_PROPER_POSITION_GK);

        // Get all the mPlayableCardList where properPositionGk less than or equals to UPDATED_PROPER_POSITION_GK
        defaultMPlayableCardShouldBeFound("properPositionGk.lessThan=" + UPDATED_PROPER_POSITION_GK);
    }


    @Test
    @Transactional
    public void getAllMPlayableCardsByProperPositionFwIsEqualToSomething() throws Exception {
        // Initialize the database
        mPlayableCardRepository.saveAndFlush(mPlayableCard);

        // Get all the mPlayableCardList where properPositionFw equals to DEFAULT_PROPER_POSITION_FW
        defaultMPlayableCardShouldBeFound("properPositionFw.equals=" + DEFAULT_PROPER_POSITION_FW);

        // Get all the mPlayableCardList where properPositionFw equals to UPDATED_PROPER_POSITION_FW
        defaultMPlayableCardShouldNotBeFound("properPositionFw.equals=" + UPDATED_PROPER_POSITION_FW);
    }

    @Test
    @Transactional
    public void getAllMPlayableCardsByProperPositionFwIsInShouldWork() throws Exception {
        // Initialize the database
        mPlayableCardRepository.saveAndFlush(mPlayableCard);

        // Get all the mPlayableCardList where properPositionFw in DEFAULT_PROPER_POSITION_FW or UPDATED_PROPER_POSITION_FW
        defaultMPlayableCardShouldBeFound("properPositionFw.in=" + DEFAULT_PROPER_POSITION_FW + "," + UPDATED_PROPER_POSITION_FW);

        // Get all the mPlayableCardList where properPositionFw equals to UPDATED_PROPER_POSITION_FW
        defaultMPlayableCardShouldNotBeFound("properPositionFw.in=" + UPDATED_PROPER_POSITION_FW);
    }

    @Test
    @Transactional
    public void getAllMPlayableCardsByProperPositionFwIsNullOrNotNull() throws Exception {
        // Initialize the database
        mPlayableCardRepository.saveAndFlush(mPlayableCard);

        // Get all the mPlayableCardList where properPositionFw is not null
        defaultMPlayableCardShouldBeFound("properPositionFw.specified=true");

        // Get all the mPlayableCardList where properPositionFw is null
        defaultMPlayableCardShouldNotBeFound("properPositionFw.specified=false");
    }

    @Test
    @Transactional
    public void getAllMPlayableCardsByProperPositionFwIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mPlayableCardRepository.saveAndFlush(mPlayableCard);

        // Get all the mPlayableCardList where properPositionFw greater than or equals to DEFAULT_PROPER_POSITION_FW
        defaultMPlayableCardShouldBeFound("properPositionFw.greaterOrEqualThan=" + DEFAULT_PROPER_POSITION_FW);

        // Get all the mPlayableCardList where properPositionFw greater than or equals to UPDATED_PROPER_POSITION_FW
        defaultMPlayableCardShouldNotBeFound("properPositionFw.greaterOrEqualThan=" + UPDATED_PROPER_POSITION_FW);
    }

    @Test
    @Transactional
    public void getAllMPlayableCardsByProperPositionFwIsLessThanSomething() throws Exception {
        // Initialize the database
        mPlayableCardRepository.saveAndFlush(mPlayableCard);

        // Get all the mPlayableCardList where properPositionFw less than or equals to DEFAULT_PROPER_POSITION_FW
        defaultMPlayableCardShouldNotBeFound("properPositionFw.lessThan=" + DEFAULT_PROPER_POSITION_FW);

        // Get all the mPlayableCardList where properPositionFw less than or equals to UPDATED_PROPER_POSITION_FW
        defaultMPlayableCardShouldBeFound("properPositionFw.lessThan=" + UPDATED_PROPER_POSITION_FW);
    }


    @Test
    @Transactional
    public void getAllMPlayableCardsByProperPositionOmfIsEqualToSomething() throws Exception {
        // Initialize the database
        mPlayableCardRepository.saveAndFlush(mPlayableCard);

        // Get all the mPlayableCardList where properPositionOmf equals to DEFAULT_PROPER_POSITION_OMF
        defaultMPlayableCardShouldBeFound("properPositionOmf.equals=" + DEFAULT_PROPER_POSITION_OMF);

        // Get all the mPlayableCardList where properPositionOmf equals to UPDATED_PROPER_POSITION_OMF
        defaultMPlayableCardShouldNotBeFound("properPositionOmf.equals=" + UPDATED_PROPER_POSITION_OMF);
    }

    @Test
    @Transactional
    public void getAllMPlayableCardsByProperPositionOmfIsInShouldWork() throws Exception {
        // Initialize the database
        mPlayableCardRepository.saveAndFlush(mPlayableCard);

        // Get all the mPlayableCardList where properPositionOmf in DEFAULT_PROPER_POSITION_OMF or UPDATED_PROPER_POSITION_OMF
        defaultMPlayableCardShouldBeFound("properPositionOmf.in=" + DEFAULT_PROPER_POSITION_OMF + "," + UPDATED_PROPER_POSITION_OMF);

        // Get all the mPlayableCardList where properPositionOmf equals to UPDATED_PROPER_POSITION_OMF
        defaultMPlayableCardShouldNotBeFound("properPositionOmf.in=" + UPDATED_PROPER_POSITION_OMF);
    }

    @Test
    @Transactional
    public void getAllMPlayableCardsByProperPositionOmfIsNullOrNotNull() throws Exception {
        // Initialize the database
        mPlayableCardRepository.saveAndFlush(mPlayableCard);

        // Get all the mPlayableCardList where properPositionOmf is not null
        defaultMPlayableCardShouldBeFound("properPositionOmf.specified=true");

        // Get all the mPlayableCardList where properPositionOmf is null
        defaultMPlayableCardShouldNotBeFound("properPositionOmf.specified=false");
    }

    @Test
    @Transactional
    public void getAllMPlayableCardsByProperPositionOmfIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mPlayableCardRepository.saveAndFlush(mPlayableCard);

        // Get all the mPlayableCardList where properPositionOmf greater than or equals to DEFAULT_PROPER_POSITION_OMF
        defaultMPlayableCardShouldBeFound("properPositionOmf.greaterOrEqualThan=" + DEFAULT_PROPER_POSITION_OMF);

        // Get all the mPlayableCardList where properPositionOmf greater than or equals to UPDATED_PROPER_POSITION_OMF
        defaultMPlayableCardShouldNotBeFound("properPositionOmf.greaterOrEqualThan=" + UPDATED_PROPER_POSITION_OMF);
    }

    @Test
    @Transactional
    public void getAllMPlayableCardsByProperPositionOmfIsLessThanSomething() throws Exception {
        // Initialize the database
        mPlayableCardRepository.saveAndFlush(mPlayableCard);

        // Get all the mPlayableCardList where properPositionOmf less than or equals to DEFAULT_PROPER_POSITION_OMF
        defaultMPlayableCardShouldNotBeFound("properPositionOmf.lessThan=" + DEFAULT_PROPER_POSITION_OMF);

        // Get all the mPlayableCardList where properPositionOmf less than or equals to UPDATED_PROPER_POSITION_OMF
        defaultMPlayableCardShouldBeFound("properPositionOmf.lessThan=" + UPDATED_PROPER_POSITION_OMF);
    }


    @Test
    @Transactional
    public void getAllMPlayableCardsByProperPositionDmfIsEqualToSomething() throws Exception {
        // Initialize the database
        mPlayableCardRepository.saveAndFlush(mPlayableCard);

        // Get all the mPlayableCardList where properPositionDmf equals to DEFAULT_PROPER_POSITION_DMF
        defaultMPlayableCardShouldBeFound("properPositionDmf.equals=" + DEFAULT_PROPER_POSITION_DMF);

        // Get all the mPlayableCardList where properPositionDmf equals to UPDATED_PROPER_POSITION_DMF
        defaultMPlayableCardShouldNotBeFound("properPositionDmf.equals=" + UPDATED_PROPER_POSITION_DMF);
    }

    @Test
    @Transactional
    public void getAllMPlayableCardsByProperPositionDmfIsInShouldWork() throws Exception {
        // Initialize the database
        mPlayableCardRepository.saveAndFlush(mPlayableCard);

        // Get all the mPlayableCardList where properPositionDmf in DEFAULT_PROPER_POSITION_DMF or UPDATED_PROPER_POSITION_DMF
        defaultMPlayableCardShouldBeFound("properPositionDmf.in=" + DEFAULT_PROPER_POSITION_DMF + "," + UPDATED_PROPER_POSITION_DMF);

        // Get all the mPlayableCardList where properPositionDmf equals to UPDATED_PROPER_POSITION_DMF
        defaultMPlayableCardShouldNotBeFound("properPositionDmf.in=" + UPDATED_PROPER_POSITION_DMF);
    }

    @Test
    @Transactional
    public void getAllMPlayableCardsByProperPositionDmfIsNullOrNotNull() throws Exception {
        // Initialize the database
        mPlayableCardRepository.saveAndFlush(mPlayableCard);

        // Get all the mPlayableCardList where properPositionDmf is not null
        defaultMPlayableCardShouldBeFound("properPositionDmf.specified=true");

        // Get all the mPlayableCardList where properPositionDmf is null
        defaultMPlayableCardShouldNotBeFound("properPositionDmf.specified=false");
    }

    @Test
    @Transactional
    public void getAllMPlayableCardsByProperPositionDmfIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mPlayableCardRepository.saveAndFlush(mPlayableCard);

        // Get all the mPlayableCardList where properPositionDmf greater than or equals to DEFAULT_PROPER_POSITION_DMF
        defaultMPlayableCardShouldBeFound("properPositionDmf.greaterOrEqualThan=" + DEFAULT_PROPER_POSITION_DMF);

        // Get all the mPlayableCardList where properPositionDmf greater than or equals to UPDATED_PROPER_POSITION_DMF
        defaultMPlayableCardShouldNotBeFound("properPositionDmf.greaterOrEqualThan=" + UPDATED_PROPER_POSITION_DMF);
    }

    @Test
    @Transactional
    public void getAllMPlayableCardsByProperPositionDmfIsLessThanSomething() throws Exception {
        // Initialize the database
        mPlayableCardRepository.saveAndFlush(mPlayableCard);

        // Get all the mPlayableCardList where properPositionDmf less than or equals to DEFAULT_PROPER_POSITION_DMF
        defaultMPlayableCardShouldNotBeFound("properPositionDmf.lessThan=" + DEFAULT_PROPER_POSITION_DMF);

        // Get all the mPlayableCardList where properPositionDmf less than or equals to UPDATED_PROPER_POSITION_DMF
        defaultMPlayableCardShouldBeFound("properPositionDmf.lessThan=" + UPDATED_PROPER_POSITION_DMF);
    }


    @Test
    @Transactional
    public void getAllMPlayableCardsByProperPositionDfIsEqualToSomething() throws Exception {
        // Initialize the database
        mPlayableCardRepository.saveAndFlush(mPlayableCard);

        // Get all the mPlayableCardList where properPositionDf equals to DEFAULT_PROPER_POSITION_DF
        defaultMPlayableCardShouldBeFound("properPositionDf.equals=" + DEFAULT_PROPER_POSITION_DF);

        // Get all the mPlayableCardList where properPositionDf equals to UPDATED_PROPER_POSITION_DF
        defaultMPlayableCardShouldNotBeFound("properPositionDf.equals=" + UPDATED_PROPER_POSITION_DF);
    }

    @Test
    @Transactional
    public void getAllMPlayableCardsByProperPositionDfIsInShouldWork() throws Exception {
        // Initialize the database
        mPlayableCardRepository.saveAndFlush(mPlayableCard);

        // Get all the mPlayableCardList where properPositionDf in DEFAULT_PROPER_POSITION_DF or UPDATED_PROPER_POSITION_DF
        defaultMPlayableCardShouldBeFound("properPositionDf.in=" + DEFAULT_PROPER_POSITION_DF + "," + UPDATED_PROPER_POSITION_DF);

        // Get all the mPlayableCardList where properPositionDf equals to UPDATED_PROPER_POSITION_DF
        defaultMPlayableCardShouldNotBeFound("properPositionDf.in=" + UPDATED_PROPER_POSITION_DF);
    }

    @Test
    @Transactional
    public void getAllMPlayableCardsByProperPositionDfIsNullOrNotNull() throws Exception {
        // Initialize the database
        mPlayableCardRepository.saveAndFlush(mPlayableCard);

        // Get all the mPlayableCardList where properPositionDf is not null
        defaultMPlayableCardShouldBeFound("properPositionDf.specified=true");

        // Get all the mPlayableCardList where properPositionDf is null
        defaultMPlayableCardShouldNotBeFound("properPositionDf.specified=false");
    }

    @Test
    @Transactional
    public void getAllMPlayableCardsByProperPositionDfIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mPlayableCardRepository.saveAndFlush(mPlayableCard);

        // Get all the mPlayableCardList where properPositionDf greater than or equals to DEFAULT_PROPER_POSITION_DF
        defaultMPlayableCardShouldBeFound("properPositionDf.greaterOrEqualThan=" + DEFAULT_PROPER_POSITION_DF);

        // Get all the mPlayableCardList where properPositionDf greater than or equals to UPDATED_PROPER_POSITION_DF
        defaultMPlayableCardShouldNotBeFound("properPositionDf.greaterOrEqualThan=" + UPDATED_PROPER_POSITION_DF);
    }

    @Test
    @Transactional
    public void getAllMPlayableCardsByProperPositionDfIsLessThanSomething() throws Exception {
        // Initialize the database
        mPlayableCardRepository.saveAndFlush(mPlayableCard);

        // Get all the mPlayableCardList where properPositionDf less than or equals to DEFAULT_PROPER_POSITION_DF
        defaultMPlayableCardShouldNotBeFound("properPositionDf.lessThan=" + DEFAULT_PROPER_POSITION_DF);

        // Get all the mPlayableCardList where properPositionDf less than or equals to UPDATED_PROPER_POSITION_DF
        defaultMPlayableCardShouldBeFound("properPositionDf.lessThan=" + UPDATED_PROPER_POSITION_DF);
    }


    @Test
    @Transactional
    public void getAllMPlayableCardsByCharacterIdIsEqualToSomething() throws Exception {
        // Initialize the database
        mPlayableCardRepository.saveAndFlush(mPlayableCard);

        // Get all the mPlayableCardList where characterId equals to DEFAULT_CHARACTER_ID
        defaultMPlayableCardShouldBeFound("characterId.equals=" + DEFAULT_CHARACTER_ID);

        // Get all the mPlayableCardList where characterId equals to UPDATED_CHARACTER_ID
        defaultMPlayableCardShouldNotBeFound("characterId.equals=" + UPDATED_CHARACTER_ID);
    }

    @Test
    @Transactional
    public void getAllMPlayableCardsByCharacterIdIsInShouldWork() throws Exception {
        // Initialize the database
        mPlayableCardRepository.saveAndFlush(mPlayableCard);

        // Get all the mPlayableCardList where characterId in DEFAULT_CHARACTER_ID or UPDATED_CHARACTER_ID
        defaultMPlayableCardShouldBeFound("characterId.in=" + DEFAULT_CHARACTER_ID + "," + UPDATED_CHARACTER_ID);

        // Get all the mPlayableCardList where characterId equals to UPDATED_CHARACTER_ID
        defaultMPlayableCardShouldNotBeFound("characterId.in=" + UPDATED_CHARACTER_ID);
    }

    @Test
    @Transactional
    public void getAllMPlayableCardsByCharacterIdIsNullOrNotNull() throws Exception {
        // Initialize the database
        mPlayableCardRepository.saveAndFlush(mPlayableCard);

        // Get all the mPlayableCardList where characterId is not null
        defaultMPlayableCardShouldBeFound("characterId.specified=true");

        // Get all the mPlayableCardList where characterId is null
        defaultMPlayableCardShouldNotBeFound("characterId.specified=false");
    }

    @Test
    @Transactional
    public void getAllMPlayableCardsByCharacterIdIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mPlayableCardRepository.saveAndFlush(mPlayableCard);

        // Get all the mPlayableCardList where characterId greater than or equals to DEFAULT_CHARACTER_ID
        defaultMPlayableCardShouldBeFound("characterId.greaterOrEqualThan=" + DEFAULT_CHARACTER_ID);

        // Get all the mPlayableCardList where characterId greater than or equals to UPDATED_CHARACTER_ID
        defaultMPlayableCardShouldNotBeFound("characterId.greaterOrEqualThan=" + UPDATED_CHARACTER_ID);
    }

    @Test
    @Transactional
    public void getAllMPlayableCardsByCharacterIdIsLessThanSomething() throws Exception {
        // Initialize the database
        mPlayableCardRepository.saveAndFlush(mPlayableCard);

        // Get all the mPlayableCardList where characterId less than or equals to DEFAULT_CHARACTER_ID
        defaultMPlayableCardShouldNotBeFound("characterId.lessThan=" + DEFAULT_CHARACTER_ID);

        // Get all the mPlayableCardList where characterId less than or equals to UPDATED_CHARACTER_ID
        defaultMPlayableCardShouldBeFound("characterId.lessThan=" + UPDATED_CHARACTER_ID);
    }


    @Test
    @Transactional
    public void getAllMPlayableCardsByTeamIdIsEqualToSomething() throws Exception {
        // Initialize the database
        mPlayableCardRepository.saveAndFlush(mPlayableCard);

        // Get all the mPlayableCardList where teamId equals to DEFAULT_TEAM_ID
        defaultMPlayableCardShouldBeFound("teamId.equals=" + DEFAULT_TEAM_ID);

        // Get all the mPlayableCardList where teamId equals to UPDATED_TEAM_ID
        defaultMPlayableCardShouldNotBeFound("teamId.equals=" + UPDATED_TEAM_ID);
    }

    @Test
    @Transactional
    public void getAllMPlayableCardsByTeamIdIsInShouldWork() throws Exception {
        // Initialize the database
        mPlayableCardRepository.saveAndFlush(mPlayableCard);

        // Get all the mPlayableCardList where teamId in DEFAULT_TEAM_ID or UPDATED_TEAM_ID
        defaultMPlayableCardShouldBeFound("teamId.in=" + DEFAULT_TEAM_ID + "," + UPDATED_TEAM_ID);

        // Get all the mPlayableCardList where teamId equals to UPDATED_TEAM_ID
        defaultMPlayableCardShouldNotBeFound("teamId.in=" + UPDATED_TEAM_ID);
    }

    @Test
    @Transactional
    public void getAllMPlayableCardsByTeamIdIsNullOrNotNull() throws Exception {
        // Initialize the database
        mPlayableCardRepository.saveAndFlush(mPlayableCard);

        // Get all the mPlayableCardList where teamId is not null
        defaultMPlayableCardShouldBeFound("teamId.specified=true");

        // Get all the mPlayableCardList where teamId is null
        defaultMPlayableCardShouldNotBeFound("teamId.specified=false");
    }

    @Test
    @Transactional
    public void getAllMPlayableCardsByTeamIdIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mPlayableCardRepository.saveAndFlush(mPlayableCard);

        // Get all the mPlayableCardList where teamId greater than or equals to DEFAULT_TEAM_ID
        defaultMPlayableCardShouldBeFound("teamId.greaterOrEqualThan=" + DEFAULT_TEAM_ID);

        // Get all the mPlayableCardList where teamId greater than or equals to UPDATED_TEAM_ID
        defaultMPlayableCardShouldNotBeFound("teamId.greaterOrEqualThan=" + UPDATED_TEAM_ID);
    }

    @Test
    @Transactional
    public void getAllMPlayableCardsByTeamIdIsLessThanSomething() throws Exception {
        // Initialize the database
        mPlayableCardRepository.saveAndFlush(mPlayableCard);

        // Get all the mPlayableCardList where teamId less than or equals to DEFAULT_TEAM_ID
        defaultMPlayableCardShouldNotBeFound("teamId.lessThan=" + DEFAULT_TEAM_ID);

        // Get all the mPlayableCardList where teamId less than or equals to UPDATED_TEAM_ID
        defaultMPlayableCardShouldBeFound("teamId.lessThan=" + UPDATED_TEAM_ID);
    }


    @Test
    @Transactional
    public void getAllMPlayableCardsByNationalityIdIsEqualToSomething() throws Exception {
        // Initialize the database
        mPlayableCardRepository.saveAndFlush(mPlayableCard);

        // Get all the mPlayableCardList where nationalityId equals to DEFAULT_NATIONALITY_ID
        defaultMPlayableCardShouldBeFound("nationalityId.equals=" + DEFAULT_NATIONALITY_ID);

        // Get all the mPlayableCardList where nationalityId equals to UPDATED_NATIONALITY_ID
        defaultMPlayableCardShouldNotBeFound("nationalityId.equals=" + UPDATED_NATIONALITY_ID);
    }

    @Test
    @Transactional
    public void getAllMPlayableCardsByNationalityIdIsInShouldWork() throws Exception {
        // Initialize the database
        mPlayableCardRepository.saveAndFlush(mPlayableCard);

        // Get all the mPlayableCardList where nationalityId in DEFAULT_NATIONALITY_ID or UPDATED_NATIONALITY_ID
        defaultMPlayableCardShouldBeFound("nationalityId.in=" + DEFAULT_NATIONALITY_ID + "," + UPDATED_NATIONALITY_ID);

        // Get all the mPlayableCardList where nationalityId equals to UPDATED_NATIONALITY_ID
        defaultMPlayableCardShouldNotBeFound("nationalityId.in=" + UPDATED_NATIONALITY_ID);
    }

    @Test
    @Transactional
    public void getAllMPlayableCardsByNationalityIdIsNullOrNotNull() throws Exception {
        // Initialize the database
        mPlayableCardRepository.saveAndFlush(mPlayableCard);

        // Get all the mPlayableCardList where nationalityId is not null
        defaultMPlayableCardShouldBeFound("nationalityId.specified=true");

        // Get all the mPlayableCardList where nationalityId is null
        defaultMPlayableCardShouldNotBeFound("nationalityId.specified=false");
    }

    @Test
    @Transactional
    public void getAllMPlayableCardsByNationalityIdIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mPlayableCardRepository.saveAndFlush(mPlayableCard);

        // Get all the mPlayableCardList where nationalityId greater than or equals to DEFAULT_NATIONALITY_ID
        defaultMPlayableCardShouldBeFound("nationalityId.greaterOrEqualThan=" + DEFAULT_NATIONALITY_ID);

        // Get all the mPlayableCardList where nationalityId greater than or equals to UPDATED_NATIONALITY_ID
        defaultMPlayableCardShouldNotBeFound("nationalityId.greaterOrEqualThan=" + UPDATED_NATIONALITY_ID);
    }

    @Test
    @Transactional
    public void getAllMPlayableCardsByNationalityIdIsLessThanSomething() throws Exception {
        // Initialize the database
        mPlayableCardRepository.saveAndFlush(mPlayableCard);

        // Get all the mPlayableCardList where nationalityId less than or equals to DEFAULT_NATIONALITY_ID
        defaultMPlayableCardShouldNotBeFound("nationalityId.lessThan=" + DEFAULT_NATIONALITY_ID);

        // Get all the mPlayableCardList where nationalityId less than or equals to UPDATED_NATIONALITY_ID
        defaultMPlayableCardShouldBeFound("nationalityId.lessThan=" + UPDATED_NATIONALITY_ID);
    }


    @Test
    @Transactional
    public void getAllMPlayableCardsByRarityIsEqualToSomething() throws Exception {
        // Initialize the database
        mPlayableCardRepository.saveAndFlush(mPlayableCard);

        // Get all the mPlayableCardList where rarity equals to DEFAULT_RARITY
        defaultMPlayableCardShouldBeFound("rarity.equals=" + DEFAULT_RARITY);

        // Get all the mPlayableCardList where rarity equals to UPDATED_RARITY
        defaultMPlayableCardShouldNotBeFound("rarity.equals=" + UPDATED_RARITY);
    }

    @Test
    @Transactional
    public void getAllMPlayableCardsByRarityIsInShouldWork() throws Exception {
        // Initialize the database
        mPlayableCardRepository.saveAndFlush(mPlayableCard);

        // Get all the mPlayableCardList where rarity in DEFAULT_RARITY or UPDATED_RARITY
        defaultMPlayableCardShouldBeFound("rarity.in=" + DEFAULT_RARITY + "," + UPDATED_RARITY);

        // Get all the mPlayableCardList where rarity equals to UPDATED_RARITY
        defaultMPlayableCardShouldNotBeFound("rarity.in=" + UPDATED_RARITY);
    }

    @Test
    @Transactional
    public void getAllMPlayableCardsByRarityIsNullOrNotNull() throws Exception {
        // Initialize the database
        mPlayableCardRepository.saveAndFlush(mPlayableCard);

        // Get all the mPlayableCardList where rarity is not null
        defaultMPlayableCardShouldBeFound("rarity.specified=true");

        // Get all the mPlayableCardList where rarity is null
        defaultMPlayableCardShouldNotBeFound("rarity.specified=false");
    }

    @Test
    @Transactional
    public void getAllMPlayableCardsByRarityIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mPlayableCardRepository.saveAndFlush(mPlayableCard);

        // Get all the mPlayableCardList where rarity greater than or equals to DEFAULT_RARITY
        defaultMPlayableCardShouldBeFound("rarity.greaterOrEqualThan=" + DEFAULT_RARITY);

        // Get all the mPlayableCardList where rarity greater than or equals to UPDATED_RARITY
        defaultMPlayableCardShouldNotBeFound("rarity.greaterOrEqualThan=" + UPDATED_RARITY);
    }

    @Test
    @Transactional
    public void getAllMPlayableCardsByRarityIsLessThanSomething() throws Exception {
        // Initialize the database
        mPlayableCardRepository.saveAndFlush(mPlayableCard);

        // Get all the mPlayableCardList where rarity less than or equals to DEFAULT_RARITY
        defaultMPlayableCardShouldNotBeFound("rarity.lessThan=" + DEFAULT_RARITY);

        // Get all the mPlayableCardList where rarity less than or equals to UPDATED_RARITY
        defaultMPlayableCardShouldBeFound("rarity.lessThan=" + UPDATED_RARITY);
    }


    @Test
    @Transactional
    public void getAllMPlayableCardsByAttributeIsEqualToSomething() throws Exception {
        // Initialize the database
        mPlayableCardRepository.saveAndFlush(mPlayableCard);

        // Get all the mPlayableCardList where attribute equals to DEFAULT_ATTRIBUTE
        defaultMPlayableCardShouldBeFound("attribute.equals=" + DEFAULT_ATTRIBUTE);

        // Get all the mPlayableCardList where attribute equals to UPDATED_ATTRIBUTE
        defaultMPlayableCardShouldNotBeFound("attribute.equals=" + UPDATED_ATTRIBUTE);
    }

    @Test
    @Transactional
    public void getAllMPlayableCardsByAttributeIsInShouldWork() throws Exception {
        // Initialize the database
        mPlayableCardRepository.saveAndFlush(mPlayableCard);

        // Get all the mPlayableCardList where attribute in DEFAULT_ATTRIBUTE or UPDATED_ATTRIBUTE
        defaultMPlayableCardShouldBeFound("attribute.in=" + DEFAULT_ATTRIBUTE + "," + UPDATED_ATTRIBUTE);

        // Get all the mPlayableCardList where attribute equals to UPDATED_ATTRIBUTE
        defaultMPlayableCardShouldNotBeFound("attribute.in=" + UPDATED_ATTRIBUTE);
    }

    @Test
    @Transactional
    public void getAllMPlayableCardsByAttributeIsNullOrNotNull() throws Exception {
        // Initialize the database
        mPlayableCardRepository.saveAndFlush(mPlayableCard);

        // Get all the mPlayableCardList where attribute is not null
        defaultMPlayableCardShouldBeFound("attribute.specified=true");

        // Get all the mPlayableCardList where attribute is null
        defaultMPlayableCardShouldNotBeFound("attribute.specified=false");
    }

    @Test
    @Transactional
    public void getAllMPlayableCardsByAttributeIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mPlayableCardRepository.saveAndFlush(mPlayableCard);

        // Get all the mPlayableCardList where attribute greater than or equals to DEFAULT_ATTRIBUTE
        defaultMPlayableCardShouldBeFound("attribute.greaterOrEqualThan=" + DEFAULT_ATTRIBUTE);

        // Get all the mPlayableCardList where attribute greater than or equals to UPDATED_ATTRIBUTE
        defaultMPlayableCardShouldNotBeFound("attribute.greaterOrEqualThan=" + UPDATED_ATTRIBUTE);
    }

    @Test
    @Transactional
    public void getAllMPlayableCardsByAttributeIsLessThanSomething() throws Exception {
        // Initialize the database
        mPlayableCardRepository.saveAndFlush(mPlayableCard);

        // Get all the mPlayableCardList where attribute less than or equals to DEFAULT_ATTRIBUTE
        defaultMPlayableCardShouldNotBeFound("attribute.lessThan=" + DEFAULT_ATTRIBUTE);

        // Get all the mPlayableCardList where attribute less than or equals to UPDATED_ATTRIBUTE
        defaultMPlayableCardShouldBeFound("attribute.lessThan=" + UPDATED_ATTRIBUTE);
    }


    @Test
    @Transactional
    public void getAllMPlayableCardsByThumbnailAssetsIdIsEqualToSomething() throws Exception {
        // Initialize the database
        mPlayableCardRepository.saveAndFlush(mPlayableCard);

        // Get all the mPlayableCardList where thumbnailAssetsId equals to DEFAULT_THUMBNAIL_ASSETS_ID
        defaultMPlayableCardShouldBeFound("thumbnailAssetsId.equals=" + DEFAULT_THUMBNAIL_ASSETS_ID);

        // Get all the mPlayableCardList where thumbnailAssetsId equals to UPDATED_THUMBNAIL_ASSETS_ID
        defaultMPlayableCardShouldNotBeFound("thumbnailAssetsId.equals=" + UPDATED_THUMBNAIL_ASSETS_ID);
    }

    @Test
    @Transactional
    public void getAllMPlayableCardsByThumbnailAssetsIdIsInShouldWork() throws Exception {
        // Initialize the database
        mPlayableCardRepository.saveAndFlush(mPlayableCard);

        // Get all the mPlayableCardList where thumbnailAssetsId in DEFAULT_THUMBNAIL_ASSETS_ID or UPDATED_THUMBNAIL_ASSETS_ID
        defaultMPlayableCardShouldBeFound("thumbnailAssetsId.in=" + DEFAULT_THUMBNAIL_ASSETS_ID + "," + UPDATED_THUMBNAIL_ASSETS_ID);

        // Get all the mPlayableCardList where thumbnailAssetsId equals to UPDATED_THUMBNAIL_ASSETS_ID
        defaultMPlayableCardShouldNotBeFound("thumbnailAssetsId.in=" + UPDATED_THUMBNAIL_ASSETS_ID);
    }

    @Test
    @Transactional
    public void getAllMPlayableCardsByThumbnailAssetsIdIsNullOrNotNull() throws Exception {
        // Initialize the database
        mPlayableCardRepository.saveAndFlush(mPlayableCard);

        // Get all the mPlayableCardList where thumbnailAssetsId is not null
        defaultMPlayableCardShouldBeFound("thumbnailAssetsId.specified=true");

        // Get all the mPlayableCardList where thumbnailAssetsId is null
        defaultMPlayableCardShouldNotBeFound("thumbnailAssetsId.specified=false");
    }

    @Test
    @Transactional
    public void getAllMPlayableCardsByThumbnailAssetsIdIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mPlayableCardRepository.saveAndFlush(mPlayableCard);

        // Get all the mPlayableCardList where thumbnailAssetsId greater than or equals to DEFAULT_THUMBNAIL_ASSETS_ID
        defaultMPlayableCardShouldBeFound("thumbnailAssetsId.greaterOrEqualThan=" + DEFAULT_THUMBNAIL_ASSETS_ID);

        // Get all the mPlayableCardList where thumbnailAssetsId greater than or equals to UPDATED_THUMBNAIL_ASSETS_ID
        defaultMPlayableCardShouldNotBeFound("thumbnailAssetsId.greaterOrEqualThan=" + UPDATED_THUMBNAIL_ASSETS_ID);
    }

    @Test
    @Transactional
    public void getAllMPlayableCardsByThumbnailAssetsIdIsLessThanSomething() throws Exception {
        // Initialize the database
        mPlayableCardRepository.saveAndFlush(mPlayableCard);

        // Get all the mPlayableCardList where thumbnailAssetsId less than or equals to DEFAULT_THUMBNAIL_ASSETS_ID
        defaultMPlayableCardShouldNotBeFound("thumbnailAssetsId.lessThan=" + DEFAULT_THUMBNAIL_ASSETS_ID);

        // Get all the mPlayableCardList where thumbnailAssetsId less than or equals to UPDATED_THUMBNAIL_ASSETS_ID
        defaultMPlayableCardShouldBeFound("thumbnailAssetsId.lessThan=" + UPDATED_THUMBNAIL_ASSETS_ID);
    }


    @Test
    @Transactional
    public void getAllMPlayableCardsByCardIllustAssetsIdIsEqualToSomething() throws Exception {
        // Initialize the database
        mPlayableCardRepository.saveAndFlush(mPlayableCard);

        // Get all the mPlayableCardList where cardIllustAssetsId equals to DEFAULT_CARD_ILLUST_ASSETS_ID
        defaultMPlayableCardShouldBeFound("cardIllustAssetsId.equals=" + DEFAULT_CARD_ILLUST_ASSETS_ID);

        // Get all the mPlayableCardList where cardIllustAssetsId equals to UPDATED_CARD_ILLUST_ASSETS_ID
        defaultMPlayableCardShouldNotBeFound("cardIllustAssetsId.equals=" + UPDATED_CARD_ILLUST_ASSETS_ID);
    }

    @Test
    @Transactional
    public void getAllMPlayableCardsByCardIllustAssetsIdIsInShouldWork() throws Exception {
        // Initialize the database
        mPlayableCardRepository.saveAndFlush(mPlayableCard);

        // Get all the mPlayableCardList where cardIllustAssetsId in DEFAULT_CARD_ILLUST_ASSETS_ID or UPDATED_CARD_ILLUST_ASSETS_ID
        defaultMPlayableCardShouldBeFound("cardIllustAssetsId.in=" + DEFAULT_CARD_ILLUST_ASSETS_ID + "," + UPDATED_CARD_ILLUST_ASSETS_ID);

        // Get all the mPlayableCardList where cardIllustAssetsId equals to UPDATED_CARD_ILLUST_ASSETS_ID
        defaultMPlayableCardShouldNotBeFound("cardIllustAssetsId.in=" + UPDATED_CARD_ILLUST_ASSETS_ID);
    }

    @Test
    @Transactional
    public void getAllMPlayableCardsByCardIllustAssetsIdIsNullOrNotNull() throws Exception {
        // Initialize the database
        mPlayableCardRepository.saveAndFlush(mPlayableCard);

        // Get all the mPlayableCardList where cardIllustAssetsId is not null
        defaultMPlayableCardShouldBeFound("cardIllustAssetsId.specified=true");

        // Get all the mPlayableCardList where cardIllustAssetsId is null
        defaultMPlayableCardShouldNotBeFound("cardIllustAssetsId.specified=false");
    }

    @Test
    @Transactional
    public void getAllMPlayableCardsByCardIllustAssetsIdIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mPlayableCardRepository.saveAndFlush(mPlayableCard);

        // Get all the mPlayableCardList where cardIllustAssetsId greater than or equals to DEFAULT_CARD_ILLUST_ASSETS_ID
        defaultMPlayableCardShouldBeFound("cardIllustAssetsId.greaterOrEqualThan=" + DEFAULT_CARD_ILLUST_ASSETS_ID);

        // Get all the mPlayableCardList where cardIllustAssetsId greater than or equals to UPDATED_CARD_ILLUST_ASSETS_ID
        defaultMPlayableCardShouldNotBeFound("cardIllustAssetsId.greaterOrEqualThan=" + UPDATED_CARD_ILLUST_ASSETS_ID);
    }

    @Test
    @Transactional
    public void getAllMPlayableCardsByCardIllustAssetsIdIsLessThanSomething() throws Exception {
        // Initialize the database
        mPlayableCardRepository.saveAndFlush(mPlayableCard);

        // Get all the mPlayableCardList where cardIllustAssetsId less than or equals to DEFAULT_CARD_ILLUST_ASSETS_ID
        defaultMPlayableCardShouldNotBeFound("cardIllustAssetsId.lessThan=" + DEFAULT_CARD_ILLUST_ASSETS_ID);

        // Get all the mPlayableCardList where cardIllustAssetsId less than or equals to UPDATED_CARD_ILLUST_ASSETS_ID
        defaultMPlayableCardShouldBeFound("cardIllustAssetsId.lessThan=" + UPDATED_CARD_ILLUST_ASSETS_ID);
    }


    @Test
    @Transactional
    public void getAllMPlayableCardsByPlayableAssetsIdIsEqualToSomething() throws Exception {
        // Initialize the database
        mPlayableCardRepository.saveAndFlush(mPlayableCard);

        // Get all the mPlayableCardList where playableAssetsId equals to DEFAULT_PLAYABLE_ASSETS_ID
        defaultMPlayableCardShouldBeFound("playableAssetsId.equals=" + DEFAULT_PLAYABLE_ASSETS_ID);

        // Get all the mPlayableCardList where playableAssetsId equals to UPDATED_PLAYABLE_ASSETS_ID
        defaultMPlayableCardShouldNotBeFound("playableAssetsId.equals=" + UPDATED_PLAYABLE_ASSETS_ID);
    }

    @Test
    @Transactional
    public void getAllMPlayableCardsByPlayableAssetsIdIsInShouldWork() throws Exception {
        // Initialize the database
        mPlayableCardRepository.saveAndFlush(mPlayableCard);

        // Get all the mPlayableCardList where playableAssetsId in DEFAULT_PLAYABLE_ASSETS_ID or UPDATED_PLAYABLE_ASSETS_ID
        defaultMPlayableCardShouldBeFound("playableAssetsId.in=" + DEFAULT_PLAYABLE_ASSETS_ID + "," + UPDATED_PLAYABLE_ASSETS_ID);

        // Get all the mPlayableCardList where playableAssetsId equals to UPDATED_PLAYABLE_ASSETS_ID
        defaultMPlayableCardShouldNotBeFound("playableAssetsId.in=" + UPDATED_PLAYABLE_ASSETS_ID);
    }

    @Test
    @Transactional
    public void getAllMPlayableCardsByPlayableAssetsIdIsNullOrNotNull() throws Exception {
        // Initialize the database
        mPlayableCardRepository.saveAndFlush(mPlayableCard);

        // Get all the mPlayableCardList where playableAssetsId is not null
        defaultMPlayableCardShouldBeFound("playableAssetsId.specified=true");

        // Get all the mPlayableCardList where playableAssetsId is null
        defaultMPlayableCardShouldNotBeFound("playableAssetsId.specified=false");
    }

    @Test
    @Transactional
    public void getAllMPlayableCardsByPlayableAssetsIdIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mPlayableCardRepository.saveAndFlush(mPlayableCard);

        // Get all the mPlayableCardList where playableAssetsId greater than or equals to DEFAULT_PLAYABLE_ASSETS_ID
        defaultMPlayableCardShouldBeFound("playableAssetsId.greaterOrEqualThan=" + DEFAULT_PLAYABLE_ASSETS_ID);

        // Get all the mPlayableCardList where playableAssetsId greater than or equals to UPDATED_PLAYABLE_ASSETS_ID
        defaultMPlayableCardShouldNotBeFound("playableAssetsId.greaterOrEqualThan=" + UPDATED_PLAYABLE_ASSETS_ID);
    }

    @Test
    @Transactional
    public void getAllMPlayableCardsByPlayableAssetsIdIsLessThanSomething() throws Exception {
        // Initialize the database
        mPlayableCardRepository.saveAndFlush(mPlayableCard);

        // Get all the mPlayableCardList where playableAssetsId less than or equals to DEFAULT_PLAYABLE_ASSETS_ID
        defaultMPlayableCardShouldNotBeFound("playableAssetsId.lessThan=" + DEFAULT_PLAYABLE_ASSETS_ID);

        // Get all the mPlayableCardList where playableAssetsId less than or equals to UPDATED_PLAYABLE_ASSETS_ID
        defaultMPlayableCardShouldBeFound("playableAssetsId.lessThan=" + UPDATED_PLAYABLE_ASSETS_ID);
    }


    @Test
    @Transactional
    public void getAllMPlayableCardsByTeamEffectIdIsEqualToSomething() throws Exception {
        // Initialize the database
        mPlayableCardRepository.saveAndFlush(mPlayableCard);

        // Get all the mPlayableCardList where teamEffectId equals to DEFAULT_TEAM_EFFECT_ID
        defaultMPlayableCardShouldBeFound("teamEffectId.equals=" + DEFAULT_TEAM_EFFECT_ID);

        // Get all the mPlayableCardList where teamEffectId equals to UPDATED_TEAM_EFFECT_ID
        defaultMPlayableCardShouldNotBeFound("teamEffectId.equals=" + UPDATED_TEAM_EFFECT_ID);
    }

    @Test
    @Transactional
    public void getAllMPlayableCardsByTeamEffectIdIsInShouldWork() throws Exception {
        // Initialize the database
        mPlayableCardRepository.saveAndFlush(mPlayableCard);

        // Get all the mPlayableCardList where teamEffectId in DEFAULT_TEAM_EFFECT_ID or UPDATED_TEAM_EFFECT_ID
        defaultMPlayableCardShouldBeFound("teamEffectId.in=" + DEFAULT_TEAM_EFFECT_ID + "," + UPDATED_TEAM_EFFECT_ID);

        // Get all the mPlayableCardList where teamEffectId equals to UPDATED_TEAM_EFFECT_ID
        defaultMPlayableCardShouldNotBeFound("teamEffectId.in=" + UPDATED_TEAM_EFFECT_ID);
    }

    @Test
    @Transactional
    public void getAllMPlayableCardsByTeamEffectIdIsNullOrNotNull() throws Exception {
        // Initialize the database
        mPlayableCardRepository.saveAndFlush(mPlayableCard);

        // Get all the mPlayableCardList where teamEffectId is not null
        defaultMPlayableCardShouldBeFound("teamEffectId.specified=true");

        // Get all the mPlayableCardList where teamEffectId is null
        defaultMPlayableCardShouldNotBeFound("teamEffectId.specified=false");
    }

    @Test
    @Transactional
    public void getAllMPlayableCardsByTeamEffectIdIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mPlayableCardRepository.saveAndFlush(mPlayableCard);

        // Get all the mPlayableCardList where teamEffectId greater than or equals to DEFAULT_TEAM_EFFECT_ID
        defaultMPlayableCardShouldBeFound("teamEffectId.greaterOrEqualThan=" + DEFAULT_TEAM_EFFECT_ID);

        // Get all the mPlayableCardList where teamEffectId greater than or equals to UPDATED_TEAM_EFFECT_ID
        defaultMPlayableCardShouldNotBeFound("teamEffectId.greaterOrEqualThan=" + UPDATED_TEAM_EFFECT_ID);
    }

    @Test
    @Transactional
    public void getAllMPlayableCardsByTeamEffectIdIsLessThanSomething() throws Exception {
        // Initialize the database
        mPlayableCardRepository.saveAndFlush(mPlayableCard);

        // Get all the mPlayableCardList where teamEffectId less than or equals to DEFAULT_TEAM_EFFECT_ID
        defaultMPlayableCardShouldNotBeFound("teamEffectId.lessThan=" + DEFAULT_TEAM_EFFECT_ID);

        // Get all the mPlayableCardList where teamEffectId less than or equals to UPDATED_TEAM_EFFECT_ID
        defaultMPlayableCardShouldBeFound("teamEffectId.lessThan=" + UPDATED_TEAM_EFFECT_ID);
    }


    @Test
    @Transactional
    public void getAllMPlayableCardsByTriggerEffectIdIsEqualToSomething() throws Exception {
        // Initialize the database
        mPlayableCardRepository.saveAndFlush(mPlayableCard);

        // Get all the mPlayableCardList where triggerEffectId equals to DEFAULT_TRIGGER_EFFECT_ID
        defaultMPlayableCardShouldBeFound("triggerEffectId.equals=" + DEFAULT_TRIGGER_EFFECT_ID);

        // Get all the mPlayableCardList where triggerEffectId equals to UPDATED_TRIGGER_EFFECT_ID
        defaultMPlayableCardShouldNotBeFound("triggerEffectId.equals=" + UPDATED_TRIGGER_EFFECT_ID);
    }

    @Test
    @Transactional
    public void getAllMPlayableCardsByTriggerEffectIdIsInShouldWork() throws Exception {
        // Initialize the database
        mPlayableCardRepository.saveAndFlush(mPlayableCard);

        // Get all the mPlayableCardList where triggerEffectId in DEFAULT_TRIGGER_EFFECT_ID or UPDATED_TRIGGER_EFFECT_ID
        defaultMPlayableCardShouldBeFound("triggerEffectId.in=" + DEFAULT_TRIGGER_EFFECT_ID + "," + UPDATED_TRIGGER_EFFECT_ID);

        // Get all the mPlayableCardList where triggerEffectId equals to UPDATED_TRIGGER_EFFECT_ID
        defaultMPlayableCardShouldNotBeFound("triggerEffectId.in=" + UPDATED_TRIGGER_EFFECT_ID);
    }

    @Test
    @Transactional
    public void getAllMPlayableCardsByTriggerEffectIdIsNullOrNotNull() throws Exception {
        // Initialize the database
        mPlayableCardRepository.saveAndFlush(mPlayableCard);

        // Get all the mPlayableCardList where triggerEffectId is not null
        defaultMPlayableCardShouldBeFound("triggerEffectId.specified=true");

        // Get all the mPlayableCardList where triggerEffectId is null
        defaultMPlayableCardShouldNotBeFound("triggerEffectId.specified=false");
    }

    @Test
    @Transactional
    public void getAllMPlayableCardsByTriggerEffectIdIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mPlayableCardRepository.saveAndFlush(mPlayableCard);

        // Get all the mPlayableCardList where triggerEffectId greater than or equals to DEFAULT_TRIGGER_EFFECT_ID
        defaultMPlayableCardShouldBeFound("triggerEffectId.greaterOrEqualThan=" + DEFAULT_TRIGGER_EFFECT_ID);

        // Get all the mPlayableCardList where triggerEffectId greater than or equals to UPDATED_TRIGGER_EFFECT_ID
        defaultMPlayableCardShouldNotBeFound("triggerEffectId.greaterOrEqualThan=" + UPDATED_TRIGGER_EFFECT_ID);
    }

    @Test
    @Transactional
    public void getAllMPlayableCardsByTriggerEffectIdIsLessThanSomething() throws Exception {
        // Initialize the database
        mPlayableCardRepository.saveAndFlush(mPlayableCard);

        // Get all the mPlayableCardList where triggerEffectId less than or equals to DEFAULT_TRIGGER_EFFECT_ID
        defaultMPlayableCardShouldNotBeFound("triggerEffectId.lessThan=" + DEFAULT_TRIGGER_EFFECT_ID);

        // Get all the mPlayableCardList where triggerEffectId less than or equals to UPDATED_TRIGGER_EFFECT_ID
        defaultMPlayableCardShouldBeFound("triggerEffectId.lessThan=" + UPDATED_TRIGGER_EFFECT_ID);
    }


    @Test
    @Transactional
    public void getAllMPlayableCardsByMaxActionSlotIsEqualToSomething() throws Exception {
        // Initialize the database
        mPlayableCardRepository.saveAndFlush(mPlayableCard);

        // Get all the mPlayableCardList where maxActionSlot equals to DEFAULT_MAX_ACTION_SLOT
        defaultMPlayableCardShouldBeFound("maxActionSlot.equals=" + DEFAULT_MAX_ACTION_SLOT);

        // Get all the mPlayableCardList where maxActionSlot equals to UPDATED_MAX_ACTION_SLOT
        defaultMPlayableCardShouldNotBeFound("maxActionSlot.equals=" + UPDATED_MAX_ACTION_SLOT);
    }

    @Test
    @Transactional
    public void getAllMPlayableCardsByMaxActionSlotIsInShouldWork() throws Exception {
        // Initialize the database
        mPlayableCardRepository.saveAndFlush(mPlayableCard);

        // Get all the mPlayableCardList where maxActionSlot in DEFAULT_MAX_ACTION_SLOT or UPDATED_MAX_ACTION_SLOT
        defaultMPlayableCardShouldBeFound("maxActionSlot.in=" + DEFAULT_MAX_ACTION_SLOT + "," + UPDATED_MAX_ACTION_SLOT);

        // Get all the mPlayableCardList where maxActionSlot equals to UPDATED_MAX_ACTION_SLOT
        defaultMPlayableCardShouldNotBeFound("maxActionSlot.in=" + UPDATED_MAX_ACTION_SLOT);
    }

    @Test
    @Transactional
    public void getAllMPlayableCardsByMaxActionSlotIsNullOrNotNull() throws Exception {
        // Initialize the database
        mPlayableCardRepository.saveAndFlush(mPlayableCard);

        // Get all the mPlayableCardList where maxActionSlot is not null
        defaultMPlayableCardShouldBeFound("maxActionSlot.specified=true");

        // Get all the mPlayableCardList where maxActionSlot is null
        defaultMPlayableCardShouldNotBeFound("maxActionSlot.specified=false");
    }

    @Test
    @Transactional
    public void getAllMPlayableCardsByMaxActionSlotIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mPlayableCardRepository.saveAndFlush(mPlayableCard);

        // Get all the mPlayableCardList where maxActionSlot greater than or equals to DEFAULT_MAX_ACTION_SLOT
        defaultMPlayableCardShouldBeFound("maxActionSlot.greaterOrEqualThan=" + DEFAULT_MAX_ACTION_SLOT);

        // Get all the mPlayableCardList where maxActionSlot greater than or equals to UPDATED_MAX_ACTION_SLOT
        defaultMPlayableCardShouldNotBeFound("maxActionSlot.greaterOrEqualThan=" + UPDATED_MAX_ACTION_SLOT);
    }

    @Test
    @Transactional
    public void getAllMPlayableCardsByMaxActionSlotIsLessThanSomething() throws Exception {
        // Initialize the database
        mPlayableCardRepository.saveAndFlush(mPlayableCard);

        // Get all the mPlayableCardList where maxActionSlot less than or equals to DEFAULT_MAX_ACTION_SLOT
        defaultMPlayableCardShouldNotBeFound("maxActionSlot.lessThan=" + DEFAULT_MAX_ACTION_SLOT);

        // Get all the mPlayableCardList where maxActionSlot less than or equals to UPDATED_MAX_ACTION_SLOT
        defaultMPlayableCardShouldBeFound("maxActionSlot.lessThan=" + UPDATED_MAX_ACTION_SLOT);
    }


    @Test
    @Transactional
    public void getAllMPlayableCardsByInitialActionId1IsEqualToSomething() throws Exception {
        // Initialize the database
        mPlayableCardRepository.saveAndFlush(mPlayableCard);

        // Get all the mPlayableCardList where initialActionId1 equals to DEFAULT_INITIAL_ACTION_ID_1
        defaultMPlayableCardShouldBeFound("initialActionId1.equals=" + DEFAULT_INITIAL_ACTION_ID_1);

        // Get all the mPlayableCardList where initialActionId1 equals to UPDATED_INITIAL_ACTION_ID_1
        defaultMPlayableCardShouldNotBeFound("initialActionId1.equals=" + UPDATED_INITIAL_ACTION_ID_1);
    }

    @Test
    @Transactional
    public void getAllMPlayableCardsByInitialActionId1IsInShouldWork() throws Exception {
        // Initialize the database
        mPlayableCardRepository.saveAndFlush(mPlayableCard);

        // Get all the mPlayableCardList where initialActionId1 in DEFAULT_INITIAL_ACTION_ID_1 or UPDATED_INITIAL_ACTION_ID_1
        defaultMPlayableCardShouldBeFound("initialActionId1.in=" + DEFAULT_INITIAL_ACTION_ID_1 + "," + UPDATED_INITIAL_ACTION_ID_1);

        // Get all the mPlayableCardList where initialActionId1 equals to UPDATED_INITIAL_ACTION_ID_1
        defaultMPlayableCardShouldNotBeFound("initialActionId1.in=" + UPDATED_INITIAL_ACTION_ID_1);
    }

    @Test
    @Transactional
    public void getAllMPlayableCardsByInitialActionId1IsNullOrNotNull() throws Exception {
        // Initialize the database
        mPlayableCardRepository.saveAndFlush(mPlayableCard);

        // Get all the mPlayableCardList where initialActionId1 is not null
        defaultMPlayableCardShouldBeFound("initialActionId1.specified=true");

        // Get all the mPlayableCardList where initialActionId1 is null
        defaultMPlayableCardShouldNotBeFound("initialActionId1.specified=false");
    }

    @Test
    @Transactional
    public void getAllMPlayableCardsByInitialActionId1IsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mPlayableCardRepository.saveAndFlush(mPlayableCard);

        // Get all the mPlayableCardList where initialActionId1 greater than or equals to DEFAULT_INITIAL_ACTION_ID_1
        defaultMPlayableCardShouldBeFound("initialActionId1.greaterOrEqualThan=" + DEFAULT_INITIAL_ACTION_ID_1);

        // Get all the mPlayableCardList where initialActionId1 greater than or equals to UPDATED_INITIAL_ACTION_ID_1
        defaultMPlayableCardShouldNotBeFound("initialActionId1.greaterOrEqualThan=" + UPDATED_INITIAL_ACTION_ID_1);
    }

    @Test
    @Transactional
    public void getAllMPlayableCardsByInitialActionId1IsLessThanSomething() throws Exception {
        // Initialize the database
        mPlayableCardRepository.saveAndFlush(mPlayableCard);

        // Get all the mPlayableCardList where initialActionId1 less than or equals to DEFAULT_INITIAL_ACTION_ID_1
        defaultMPlayableCardShouldNotBeFound("initialActionId1.lessThan=" + DEFAULT_INITIAL_ACTION_ID_1);

        // Get all the mPlayableCardList where initialActionId1 less than or equals to UPDATED_INITIAL_ACTION_ID_1
        defaultMPlayableCardShouldBeFound("initialActionId1.lessThan=" + UPDATED_INITIAL_ACTION_ID_1);
    }


    @Test
    @Transactional
    public void getAllMPlayableCardsByInitialActionId2IsEqualToSomething() throws Exception {
        // Initialize the database
        mPlayableCardRepository.saveAndFlush(mPlayableCard);

        // Get all the mPlayableCardList where initialActionId2 equals to DEFAULT_INITIAL_ACTION_ID_2
        defaultMPlayableCardShouldBeFound("initialActionId2.equals=" + DEFAULT_INITIAL_ACTION_ID_2);

        // Get all the mPlayableCardList where initialActionId2 equals to UPDATED_INITIAL_ACTION_ID_2
        defaultMPlayableCardShouldNotBeFound("initialActionId2.equals=" + UPDATED_INITIAL_ACTION_ID_2);
    }

    @Test
    @Transactional
    public void getAllMPlayableCardsByInitialActionId2IsInShouldWork() throws Exception {
        // Initialize the database
        mPlayableCardRepository.saveAndFlush(mPlayableCard);

        // Get all the mPlayableCardList where initialActionId2 in DEFAULT_INITIAL_ACTION_ID_2 or UPDATED_INITIAL_ACTION_ID_2
        defaultMPlayableCardShouldBeFound("initialActionId2.in=" + DEFAULT_INITIAL_ACTION_ID_2 + "," + UPDATED_INITIAL_ACTION_ID_2);

        // Get all the mPlayableCardList where initialActionId2 equals to UPDATED_INITIAL_ACTION_ID_2
        defaultMPlayableCardShouldNotBeFound("initialActionId2.in=" + UPDATED_INITIAL_ACTION_ID_2);
    }

    @Test
    @Transactional
    public void getAllMPlayableCardsByInitialActionId2IsNullOrNotNull() throws Exception {
        // Initialize the database
        mPlayableCardRepository.saveAndFlush(mPlayableCard);

        // Get all the mPlayableCardList where initialActionId2 is not null
        defaultMPlayableCardShouldBeFound("initialActionId2.specified=true");

        // Get all the mPlayableCardList where initialActionId2 is null
        defaultMPlayableCardShouldNotBeFound("initialActionId2.specified=false");
    }

    @Test
    @Transactional
    public void getAllMPlayableCardsByInitialActionId2IsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mPlayableCardRepository.saveAndFlush(mPlayableCard);

        // Get all the mPlayableCardList where initialActionId2 greater than or equals to DEFAULT_INITIAL_ACTION_ID_2
        defaultMPlayableCardShouldBeFound("initialActionId2.greaterOrEqualThan=" + DEFAULT_INITIAL_ACTION_ID_2);

        // Get all the mPlayableCardList where initialActionId2 greater than or equals to UPDATED_INITIAL_ACTION_ID_2
        defaultMPlayableCardShouldNotBeFound("initialActionId2.greaterOrEqualThan=" + UPDATED_INITIAL_ACTION_ID_2);
    }

    @Test
    @Transactional
    public void getAllMPlayableCardsByInitialActionId2IsLessThanSomething() throws Exception {
        // Initialize the database
        mPlayableCardRepository.saveAndFlush(mPlayableCard);

        // Get all the mPlayableCardList where initialActionId2 less than or equals to DEFAULT_INITIAL_ACTION_ID_2
        defaultMPlayableCardShouldNotBeFound("initialActionId2.lessThan=" + DEFAULT_INITIAL_ACTION_ID_2);

        // Get all the mPlayableCardList where initialActionId2 less than or equals to UPDATED_INITIAL_ACTION_ID_2
        defaultMPlayableCardShouldBeFound("initialActionId2.lessThan=" + UPDATED_INITIAL_ACTION_ID_2);
    }


    @Test
    @Transactional
    public void getAllMPlayableCardsByInitialActionId3IsEqualToSomething() throws Exception {
        // Initialize the database
        mPlayableCardRepository.saveAndFlush(mPlayableCard);

        // Get all the mPlayableCardList where initialActionId3 equals to DEFAULT_INITIAL_ACTION_ID_3
        defaultMPlayableCardShouldBeFound("initialActionId3.equals=" + DEFAULT_INITIAL_ACTION_ID_3);

        // Get all the mPlayableCardList where initialActionId3 equals to UPDATED_INITIAL_ACTION_ID_3
        defaultMPlayableCardShouldNotBeFound("initialActionId3.equals=" + UPDATED_INITIAL_ACTION_ID_3);
    }

    @Test
    @Transactional
    public void getAllMPlayableCardsByInitialActionId3IsInShouldWork() throws Exception {
        // Initialize the database
        mPlayableCardRepository.saveAndFlush(mPlayableCard);

        // Get all the mPlayableCardList where initialActionId3 in DEFAULT_INITIAL_ACTION_ID_3 or UPDATED_INITIAL_ACTION_ID_3
        defaultMPlayableCardShouldBeFound("initialActionId3.in=" + DEFAULT_INITIAL_ACTION_ID_3 + "," + UPDATED_INITIAL_ACTION_ID_3);

        // Get all the mPlayableCardList where initialActionId3 equals to UPDATED_INITIAL_ACTION_ID_3
        defaultMPlayableCardShouldNotBeFound("initialActionId3.in=" + UPDATED_INITIAL_ACTION_ID_3);
    }

    @Test
    @Transactional
    public void getAllMPlayableCardsByInitialActionId3IsNullOrNotNull() throws Exception {
        // Initialize the database
        mPlayableCardRepository.saveAndFlush(mPlayableCard);

        // Get all the mPlayableCardList where initialActionId3 is not null
        defaultMPlayableCardShouldBeFound("initialActionId3.specified=true");

        // Get all the mPlayableCardList where initialActionId3 is null
        defaultMPlayableCardShouldNotBeFound("initialActionId3.specified=false");
    }

    @Test
    @Transactional
    public void getAllMPlayableCardsByInitialActionId3IsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mPlayableCardRepository.saveAndFlush(mPlayableCard);

        // Get all the mPlayableCardList where initialActionId3 greater than or equals to DEFAULT_INITIAL_ACTION_ID_3
        defaultMPlayableCardShouldBeFound("initialActionId3.greaterOrEqualThan=" + DEFAULT_INITIAL_ACTION_ID_3);

        // Get all the mPlayableCardList where initialActionId3 greater than or equals to UPDATED_INITIAL_ACTION_ID_3
        defaultMPlayableCardShouldNotBeFound("initialActionId3.greaterOrEqualThan=" + UPDATED_INITIAL_ACTION_ID_3);
    }

    @Test
    @Transactional
    public void getAllMPlayableCardsByInitialActionId3IsLessThanSomething() throws Exception {
        // Initialize the database
        mPlayableCardRepository.saveAndFlush(mPlayableCard);

        // Get all the mPlayableCardList where initialActionId3 less than or equals to DEFAULT_INITIAL_ACTION_ID_3
        defaultMPlayableCardShouldNotBeFound("initialActionId3.lessThan=" + DEFAULT_INITIAL_ACTION_ID_3);

        // Get all the mPlayableCardList where initialActionId3 less than or equals to UPDATED_INITIAL_ACTION_ID_3
        defaultMPlayableCardShouldBeFound("initialActionId3.lessThan=" + UPDATED_INITIAL_ACTION_ID_3);
    }


    @Test
    @Transactional
    public void getAllMPlayableCardsByInitialActionId4IsEqualToSomething() throws Exception {
        // Initialize the database
        mPlayableCardRepository.saveAndFlush(mPlayableCard);

        // Get all the mPlayableCardList where initialActionId4 equals to DEFAULT_INITIAL_ACTION_ID_4
        defaultMPlayableCardShouldBeFound("initialActionId4.equals=" + DEFAULT_INITIAL_ACTION_ID_4);

        // Get all the mPlayableCardList where initialActionId4 equals to UPDATED_INITIAL_ACTION_ID_4
        defaultMPlayableCardShouldNotBeFound("initialActionId4.equals=" + UPDATED_INITIAL_ACTION_ID_4);
    }

    @Test
    @Transactional
    public void getAllMPlayableCardsByInitialActionId4IsInShouldWork() throws Exception {
        // Initialize the database
        mPlayableCardRepository.saveAndFlush(mPlayableCard);

        // Get all the mPlayableCardList where initialActionId4 in DEFAULT_INITIAL_ACTION_ID_4 or UPDATED_INITIAL_ACTION_ID_4
        defaultMPlayableCardShouldBeFound("initialActionId4.in=" + DEFAULT_INITIAL_ACTION_ID_4 + "," + UPDATED_INITIAL_ACTION_ID_4);

        // Get all the mPlayableCardList where initialActionId4 equals to UPDATED_INITIAL_ACTION_ID_4
        defaultMPlayableCardShouldNotBeFound("initialActionId4.in=" + UPDATED_INITIAL_ACTION_ID_4);
    }

    @Test
    @Transactional
    public void getAllMPlayableCardsByInitialActionId4IsNullOrNotNull() throws Exception {
        // Initialize the database
        mPlayableCardRepository.saveAndFlush(mPlayableCard);

        // Get all the mPlayableCardList where initialActionId4 is not null
        defaultMPlayableCardShouldBeFound("initialActionId4.specified=true");

        // Get all the mPlayableCardList where initialActionId4 is null
        defaultMPlayableCardShouldNotBeFound("initialActionId4.specified=false");
    }

    @Test
    @Transactional
    public void getAllMPlayableCardsByInitialActionId4IsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mPlayableCardRepository.saveAndFlush(mPlayableCard);

        // Get all the mPlayableCardList where initialActionId4 greater than or equals to DEFAULT_INITIAL_ACTION_ID_4
        defaultMPlayableCardShouldBeFound("initialActionId4.greaterOrEqualThan=" + DEFAULT_INITIAL_ACTION_ID_4);

        // Get all the mPlayableCardList where initialActionId4 greater than or equals to UPDATED_INITIAL_ACTION_ID_4
        defaultMPlayableCardShouldNotBeFound("initialActionId4.greaterOrEqualThan=" + UPDATED_INITIAL_ACTION_ID_4);
    }

    @Test
    @Transactional
    public void getAllMPlayableCardsByInitialActionId4IsLessThanSomething() throws Exception {
        // Initialize the database
        mPlayableCardRepository.saveAndFlush(mPlayableCard);

        // Get all the mPlayableCardList where initialActionId4 less than or equals to DEFAULT_INITIAL_ACTION_ID_4
        defaultMPlayableCardShouldNotBeFound("initialActionId4.lessThan=" + DEFAULT_INITIAL_ACTION_ID_4);

        // Get all the mPlayableCardList where initialActionId4 less than or equals to UPDATED_INITIAL_ACTION_ID_4
        defaultMPlayableCardShouldBeFound("initialActionId4.lessThan=" + UPDATED_INITIAL_ACTION_ID_4);
    }


    @Test
    @Transactional
    public void getAllMPlayableCardsByInitialActionId5IsEqualToSomething() throws Exception {
        // Initialize the database
        mPlayableCardRepository.saveAndFlush(mPlayableCard);

        // Get all the mPlayableCardList where initialActionId5 equals to DEFAULT_INITIAL_ACTION_ID_5
        defaultMPlayableCardShouldBeFound("initialActionId5.equals=" + DEFAULT_INITIAL_ACTION_ID_5);

        // Get all the mPlayableCardList where initialActionId5 equals to UPDATED_INITIAL_ACTION_ID_5
        defaultMPlayableCardShouldNotBeFound("initialActionId5.equals=" + UPDATED_INITIAL_ACTION_ID_5);
    }

    @Test
    @Transactional
    public void getAllMPlayableCardsByInitialActionId5IsInShouldWork() throws Exception {
        // Initialize the database
        mPlayableCardRepository.saveAndFlush(mPlayableCard);

        // Get all the mPlayableCardList where initialActionId5 in DEFAULT_INITIAL_ACTION_ID_5 or UPDATED_INITIAL_ACTION_ID_5
        defaultMPlayableCardShouldBeFound("initialActionId5.in=" + DEFAULT_INITIAL_ACTION_ID_5 + "," + UPDATED_INITIAL_ACTION_ID_5);

        // Get all the mPlayableCardList where initialActionId5 equals to UPDATED_INITIAL_ACTION_ID_5
        defaultMPlayableCardShouldNotBeFound("initialActionId5.in=" + UPDATED_INITIAL_ACTION_ID_5);
    }

    @Test
    @Transactional
    public void getAllMPlayableCardsByInitialActionId5IsNullOrNotNull() throws Exception {
        // Initialize the database
        mPlayableCardRepository.saveAndFlush(mPlayableCard);

        // Get all the mPlayableCardList where initialActionId5 is not null
        defaultMPlayableCardShouldBeFound("initialActionId5.specified=true");

        // Get all the mPlayableCardList where initialActionId5 is null
        defaultMPlayableCardShouldNotBeFound("initialActionId5.specified=false");
    }

    @Test
    @Transactional
    public void getAllMPlayableCardsByInitialActionId5IsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mPlayableCardRepository.saveAndFlush(mPlayableCard);

        // Get all the mPlayableCardList where initialActionId5 greater than or equals to DEFAULT_INITIAL_ACTION_ID_5
        defaultMPlayableCardShouldBeFound("initialActionId5.greaterOrEqualThan=" + DEFAULT_INITIAL_ACTION_ID_5);

        // Get all the mPlayableCardList where initialActionId5 greater than or equals to UPDATED_INITIAL_ACTION_ID_5
        defaultMPlayableCardShouldNotBeFound("initialActionId5.greaterOrEqualThan=" + UPDATED_INITIAL_ACTION_ID_5);
    }

    @Test
    @Transactional
    public void getAllMPlayableCardsByInitialActionId5IsLessThanSomething() throws Exception {
        // Initialize the database
        mPlayableCardRepository.saveAndFlush(mPlayableCard);

        // Get all the mPlayableCardList where initialActionId5 less than or equals to DEFAULT_INITIAL_ACTION_ID_5
        defaultMPlayableCardShouldNotBeFound("initialActionId5.lessThan=" + DEFAULT_INITIAL_ACTION_ID_5);

        // Get all the mPlayableCardList where initialActionId5 less than or equals to UPDATED_INITIAL_ACTION_ID_5
        defaultMPlayableCardShouldBeFound("initialActionId5.lessThan=" + UPDATED_INITIAL_ACTION_ID_5);
    }


    @Test
    @Transactional
    public void getAllMPlayableCardsByInitialStaminaIsEqualToSomething() throws Exception {
        // Initialize the database
        mPlayableCardRepository.saveAndFlush(mPlayableCard);

        // Get all the mPlayableCardList where initialStamina equals to DEFAULT_INITIAL_STAMINA
        defaultMPlayableCardShouldBeFound("initialStamina.equals=" + DEFAULT_INITIAL_STAMINA);

        // Get all the mPlayableCardList where initialStamina equals to UPDATED_INITIAL_STAMINA
        defaultMPlayableCardShouldNotBeFound("initialStamina.equals=" + UPDATED_INITIAL_STAMINA);
    }

    @Test
    @Transactional
    public void getAllMPlayableCardsByInitialStaminaIsInShouldWork() throws Exception {
        // Initialize the database
        mPlayableCardRepository.saveAndFlush(mPlayableCard);

        // Get all the mPlayableCardList where initialStamina in DEFAULT_INITIAL_STAMINA or UPDATED_INITIAL_STAMINA
        defaultMPlayableCardShouldBeFound("initialStamina.in=" + DEFAULT_INITIAL_STAMINA + "," + UPDATED_INITIAL_STAMINA);

        // Get all the mPlayableCardList where initialStamina equals to UPDATED_INITIAL_STAMINA
        defaultMPlayableCardShouldNotBeFound("initialStamina.in=" + UPDATED_INITIAL_STAMINA);
    }

    @Test
    @Transactional
    public void getAllMPlayableCardsByInitialStaminaIsNullOrNotNull() throws Exception {
        // Initialize the database
        mPlayableCardRepository.saveAndFlush(mPlayableCard);

        // Get all the mPlayableCardList where initialStamina is not null
        defaultMPlayableCardShouldBeFound("initialStamina.specified=true");

        // Get all the mPlayableCardList where initialStamina is null
        defaultMPlayableCardShouldNotBeFound("initialStamina.specified=false");
    }

    @Test
    @Transactional
    public void getAllMPlayableCardsByInitialStaminaIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mPlayableCardRepository.saveAndFlush(mPlayableCard);

        // Get all the mPlayableCardList where initialStamina greater than or equals to DEFAULT_INITIAL_STAMINA
        defaultMPlayableCardShouldBeFound("initialStamina.greaterOrEqualThan=" + DEFAULT_INITIAL_STAMINA);

        // Get all the mPlayableCardList where initialStamina greater than or equals to UPDATED_INITIAL_STAMINA
        defaultMPlayableCardShouldNotBeFound("initialStamina.greaterOrEqualThan=" + UPDATED_INITIAL_STAMINA);
    }

    @Test
    @Transactional
    public void getAllMPlayableCardsByInitialStaminaIsLessThanSomething() throws Exception {
        // Initialize the database
        mPlayableCardRepository.saveAndFlush(mPlayableCard);

        // Get all the mPlayableCardList where initialStamina less than or equals to DEFAULT_INITIAL_STAMINA
        defaultMPlayableCardShouldNotBeFound("initialStamina.lessThan=" + DEFAULT_INITIAL_STAMINA);

        // Get all the mPlayableCardList where initialStamina less than or equals to UPDATED_INITIAL_STAMINA
        defaultMPlayableCardShouldBeFound("initialStamina.lessThan=" + UPDATED_INITIAL_STAMINA);
    }


    @Test
    @Transactional
    public void getAllMPlayableCardsByInitialDribbleIsEqualToSomething() throws Exception {
        // Initialize the database
        mPlayableCardRepository.saveAndFlush(mPlayableCard);

        // Get all the mPlayableCardList where initialDribble equals to DEFAULT_INITIAL_DRIBBLE
        defaultMPlayableCardShouldBeFound("initialDribble.equals=" + DEFAULT_INITIAL_DRIBBLE);

        // Get all the mPlayableCardList where initialDribble equals to UPDATED_INITIAL_DRIBBLE
        defaultMPlayableCardShouldNotBeFound("initialDribble.equals=" + UPDATED_INITIAL_DRIBBLE);
    }

    @Test
    @Transactional
    public void getAllMPlayableCardsByInitialDribbleIsInShouldWork() throws Exception {
        // Initialize the database
        mPlayableCardRepository.saveAndFlush(mPlayableCard);

        // Get all the mPlayableCardList where initialDribble in DEFAULT_INITIAL_DRIBBLE or UPDATED_INITIAL_DRIBBLE
        defaultMPlayableCardShouldBeFound("initialDribble.in=" + DEFAULT_INITIAL_DRIBBLE + "," + UPDATED_INITIAL_DRIBBLE);

        // Get all the mPlayableCardList where initialDribble equals to UPDATED_INITIAL_DRIBBLE
        defaultMPlayableCardShouldNotBeFound("initialDribble.in=" + UPDATED_INITIAL_DRIBBLE);
    }

    @Test
    @Transactional
    public void getAllMPlayableCardsByInitialDribbleIsNullOrNotNull() throws Exception {
        // Initialize the database
        mPlayableCardRepository.saveAndFlush(mPlayableCard);

        // Get all the mPlayableCardList where initialDribble is not null
        defaultMPlayableCardShouldBeFound("initialDribble.specified=true");

        // Get all the mPlayableCardList where initialDribble is null
        defaultMPlayableCardShouldNotBeFound("initialDribble.specified=false");
    }

    @Test
    @Transactional
    public void getAllMPlayableCardsByInitialDribbleIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mPlayableCardRepository.saveAndFlush(mPlayableCard);

        // Get all the mPlayableCardList where initialDribble greater than or equals to DEFAULT_INITIAL_DRIBBLE
        defaultMPlayableCardShouldBeFound("initialDribble.greaterOrEqualThan=" + DEFAULT_INITIAL_DRIBBLE);

        // Get all the mPlayableCardList where initialDribble greater than or equals to UPDATED_INITIAL_DRIBBLE
        defaultMPlayableCardShouldNotBeFound("initialDribble.greaterOrEqualThan=" + UPDATED_INITIAL_DRIBBLE);
    }

    @Test
    @Transactional
    public void getAllMPlayableCardsByInitialDribbleIsLessThanSomething() throws Exception {
        // Initialize the database
        mPlayableCardRepository.saveAndFlush(mPlayableCard);

        // Get all the mPlayableCardList where initialDribble less than or equals to DEFAULT_INITIAL_DRIBBLE
        defaultMPlayableCardShouldNotBeFound("initialDribble.lessThan=" + DEFAULT_INITIAL_DRIBBLE);

        // Get all the mPlayableCardList where initialDribble less than or equals to UPDATED_INITIAL_DRIBBLE
        defaultMPlayableCardShouldBeFound("initialDribble.lessThan=" + UPDATED_INITIAL_DRIBBLE);
    }


    @Test
    @Transactional
    public void getAllMPlayableCardsByInitialShootIsEqualToSomething() throws Exception {
        // Initialize the database
        mPlayableCardRepository.saveAndFlush(mPlayableCard);

        // Get all the mPlayableCardList where initialShoot equals to DEFAULT_INITIAL_SHOOT
        defaultMPlayableCardShouldBeFound("initialShoot.equals=" + DEFAULT_INITIAL_SHOOT);

        // Get all the mPlayableCardList where initialShoot equals to UPDATED_INITIAL_SHOOT
        defaultMPlayableCardShouldNotBeFound("initialShoot.equals=" + UPDATED_INITIAL_SHOOT);
    }

    @Test
    @Transactional
    public void getAllMPlayableCardsByInitialShootIsInShouldWork() throws Exception {
        // Initialize the database
        mPlayableCardRepository.saveAndFlush(mPlayableCard);

        // Get all the mPlayableCardList where initialShoot in DEFAULT_INITIAL_SHOOT or UPDATED_INITIAL_SHOOT
        defaultMPlayableCardShouldBeFound("initialShoot.in=" + DEFAULT_INITIAL_SHOOT + "," + UPDATED_INITIAL_SHOOT);

        // Get all the mPlayableCardList where initialShoot equals to UPDATED_INITIAL_SHOOT
        defaultMPlayableCardShouldNotBeFound("initialShoot.in=" + UPDATED_INITIAL_SHOOT);
    }

    @Test
    @Transactional
    public void getAllMPlayableCardsByInitialShootIsNullOrNotNull() throws Exception {
        // Initialize the database
        mPlayableCardRepository.saveAndFlush(mPlayableCard);

        // Get all the mPlayableCardList where initialShoot is not null
        defaultMPlayableCardShouldBeFound("initialShoot.specified=true");

        // Get all the mPlayableCardList where initialShoot is null
        defaultMPlayableCardShouldNotBeFound("initialShoot.specified=false");
    }

    @Test
    @Transactional
    public void getAllMPlayableCardsByInitialShootIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mPlayableCardRepository.saveAndFlush(mPlayableCard);

        // Get all the mPlayableCardList where initialShoot greater than or equals to DEFAULT_INITIAL_SHOOT
        defaultMPlayableCardShouldBeFound("initialShoot.greaterOrEqualThan=" + DEFAULT_INITIAL_SHOOT);

        // Get all the mPlayableCardList where initialShoot greater than or equals to UPDATED_INITIAL_SHOOT
        defaultMPlayableCardShouldNotBeFound("initialShoot.greaterOrEqualThan=" + UPDATED_INITIAL_SHOOT);
    }

    @Test
    @Transactional
    public void getAllMPlayableCardsByInitialShootIsLessThanSomething() throws Exception {
        // Initialize the database
        mPlayableCardRepository.saveAndFlush(mPlayableCard);

        // Get all the mPlayableCardList where initialShoot less than or equals to DEFAULT_INITIAL_SHOOT
        defaultMPlayableCardShouldNotBeFound("initialShoot.lessThan=" + DEFAULT_INITIAL_SHOOT);

        // Get all the mPlayableCardList where initialShoot less than or equals to UPDATED_INITIAL_SHOOT
        defaultMPlayableCardShouldBeFound("initialShoot.lessThan=" + UPDATED_INITIAL_SHOOT);
    }


    @Test
    @Transactional
    public void getAllMPlayableCardsByInitialPassIsEqualToSomething() throws Exception {
        // Initialize the database
        mPlayableCardRepository.saveAndFlush(mPlayableCard);

        // Get all the mPlayableCardList where initialPass equals to DEFAULT_INITIAL_PASS
        defaultMPlayableCardShouldBeFound("initialPass.equals=" + DEFAULT_INITIAL_PASS);

        // Get all the mPlayableCardList where initialPass equals to UPDATED_INITIAL_PASS
        defaultMPlayableCardShouldNotBeFound("initialPass.equals=" + UPDATED_INITIAL_PASS);
    }

    @Test
    @Transactional
    public void getAllMPlayableCardsByInitialPassIsInShouldWork() throws Exception {
        // Initialize the database
        mPlayableCardRepository.saveAndFlush(mPlayableCard);

        // Get all the mPlayableCardList where initialPass in DEFAULT_INITIAL_PASS or UPDATED_INITIAL_PASS
        defaultMPlayableCardShouldBeFound("initialPass.in=" + DEFAULT_INITIAL_PASS + "," + UPDATED_INITIAL_PASS);

        // Get all the mPlayableCardList where initialPass equals to UPDATED_INITIAL_PASS
        defaultMPlayableCardShouldNotBeFound("initialPass.in=" + UPDATED_INITIAL_PASS);
    }

    @Test
    @Transactional
    public void getAllMPlayableCardsByInitialPassIsNullOrNotNull() throws Exception {
        // Initialize the database
        mPlayableCardRepository.saveAndFlush(mPlayableCard);

        // Get all the mPlayableCardList where initialPass is not null
        defaultMPlayableCardShouldBeFound("initialPass.specified=true");

        // Get all the mPlayableCardList where initialPass is null
        defaultMPlayableCardShouldNotBeFound("initialPass.specified=false");
    }

    @Test
    @Transactional
    public void getAllMPlayableCardsByInitialPassIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mPlayableCardRepository.saveAndFlush(mPlayableCard);

        // Get all the mPlayableCardList where initialPass greater than or equals to DEFAULT_INITIAL_PASS
        defaultMPlayableCardShouldBeFound("initialPass.greaterOrEqualThan=" + DEFAULT_INITIAL_PASS);

        // Get all the mPlayableCardList where initialPass greater than or equals to UPDATED_INITIAL_PASS
        defaultMPlayableCardShouldNotBeFound("initialPass.greaterOrEqualThan=" + UPDATED_INITIAL_PASS);
    }

    @Test
    @Transactional
    public void getAllMPlayableCardsByInitialPassIsLessThanSomething() throws Exception {
        // Initialize the database
        mPlayableCardRepository.saveAndFlush(mPlayableCard);

        // Get all the mPlayableCardList where initialPass less than or equals to DEFAULT_INITIAL_PASS
        defaultMPlayableCardShouldNotBeFound("initialPass.lessThan=" + DEFAULT_INITIAL_PASS);

        // Get all the mPlayableCardList where initialPass less than or equals to UPDATED_INITIAL_PASS
        defaultMPlayableCardShouldBeFound("initialPass.lessThan=" + UPDATED_INITIAL_PASS);
    }


    @Test
    @Transactional
    public void getAllMPlayableCardsByInitialTackleIsEqualToSomething() throws Exception {
        // Initialize the database
        mPlayableCardRepository.saveAndFlush(mPlayableCard);

        // Get all the mPlayableCardList where initialTackle equals to DEFAULT_INITIAL_TACKLE
        defaultMPlayableCardShouldBeFound("initialTackle.equals=" + DEFAULT_INITIAL_TACKLE);

        // Get all the mPlayableCardList where initialTackle equals to UPDATED_INITIAL_TACKLE
        defaultMPlayableCardShouldNotBeFound("initialTackle.equals=" + UPDATED_INITIAL_TACKLE);
    }

    @Test
    @Transactional
    public void getAllMPlayableCardsByInitialTackleIsInShouldWork() throws Exception {
        // Initialize the database
        mPlayableCardRepository.saveAndFlush(mPlayableCard);

        // Get all the mPlayableCardList where initialTackle in DEFAULT_INITIAL_TACKLE or UPDATED_INITIAL_TACKLE
        defaultMPlayableCardShouldBeFound("initialTackle.in=" + DEFAULT_INITIAL_TACKLE + "," + UPDATED_INITIAL_TACKLE);

        // Get all the mPlayableCardList where initialTackle equals to UPDATED_INITIAL_TACKLE
        defaultMPlayableCardShouldNotBeFound("initialTackle.in=" + UPDATED_INITIAL_TACKLE);
    }

    @Test
    @Transactional
    public void getAllMPlayableCardsByInitialTackleIsNullOrNotNull() throws Exception {
        // Initialize the database
        mPlayableCardRepository.saveAndFlush(mPlayableCard);

        // Get all the mPlayableCardList where initialTackle is not null
        defaultMPlayableCardShouldBeFound("initialTackle.specified=true");

        // Get all the mPlayableCardList where initialTackle is null
        defaultMPlayableCardShouldNotBeFound("initialTackle.specified=false");
    }

    @Test
    @Transactional
    public void getAllMPlayableCardsByInitialTackleIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mPlayableCardRepository.saveAndFlush(mPlayableCard);

        // Get all the mPlayableCardList where initialTackle greater than or equals to DEFAULT_INITIAL_TACKLE
        defaultMPlayableCardShouldBeFound("initialTackle.greaterOrEqualThan=" + DEFAULT_INITIAL_TACKLE);

        // Get all the mPlayableCardList where initialTackle greater than or equals to UPDATED_INITIAL_TACKLE
        defaultMPlayableCardShouldNotBeFound("initialTackle.greaterOrEqualThan=" + UPDATED_INITIAL_TACKLE);
    }

    @Test
    @Transactional
    public void getAllMPlayableCardsByInitialTackleIsLessThanSomething() throws Exception {
        // Initialize the database
        mPlayableCardRepository.saveAndFlush(mPlayableCard);

        // Get all the mPlayableCardList where initialTackle less than or equals to DEFAULT_INITIAL_TACKLE
        defaultMPlayableCardShouldNotBeFound("initialTackle.lessThan=" + DEFAULT_INITIAL_TACKLE);

        // Get all the mPlayableCardList where initialTackle less than or equals to UPDATED_INITIAL_TACKLE
        defaultMPlayableCardShouldBeFound("initialTackle.lessThan=" + UPDATED_INITIAL_TACKLE);
    }


    @Test
    @Transactional
    public void getAllMPlayableCardsByInitialBlockIsEqualToSomething() throws Exception {
        // Initialize the database
        mPlayableCardRepository.saveAndFlush(mPlayableCard);

        // Get all the mPlayableCardList where initialBlock equals to DEFAULT_INITIAL_BLOCK
        defaultMPlayableCardShouldBeFound("initialBlock.equals=" + DEFAULT_INITIAL_BLOCK);

        // Get all the mPlayableCardList where initialBlock equals to UPDATED_INITIAL_BLOCK
        defaultMPlayableCardShouldNotBeFound("initialBlock.equals=" + UPDATED_INITIAL_BLOCK);
    }

    @Test
    @Transactional
    public void getAllMPlayableCardsByInitialBlockIsInShouldWork() throws Exception {
        // Initialize the database
        mPlayableCardRepository.saveAndFlush(mPlayableCard);

        // Get all the mPlayableCardList where initialBlock in DEFAULT_INITIAL_BLOCK or UPDATED_INITIAL_BLOCK
        defaultMPlayableCardShouldBeFound("initialBlock.in=" + DEFAULT_INITIAL_BLOCK + "," + UPDATED_INITIAL_BLOCK);

        // Get all the mPlayableCardList where initialBlock equals to UPDATED_INITIAL_BLOCK
        defaultMPlayableCardShouldNotBeFound("initialBlock.in=" + UPDATED_INITIAL_BLOCK);
    }

    @Test
    @Transactional
    public void getAllMPlayableCardsByInitialBlockIsNullOrNotNull() throws Exception {
        // Initialize the database
        mPlayableCardRepository.saveAndFlush(mPlayableCard);

        // Get all the mPlayableCardList where initialBlock is not null
        defaultMPlayableCardShouldBeFound("initialBlock.specified=true");

        // Get all the mPlayableCardList where initialBlock is null
        defaultMPlayableCardShouldNotBeFound("initialBlock.specified=false");
    }

    @Test
    @Transactional
    public void getAllMPlayableCardsByInitialBlockIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mPlayableCardRepository.saveAndFlush(mPlayableCard);

        // Get all the mPlayableCardList where initialBlock greater than or equals to DEFAULT_INITIAL_BLOCK
        defaultMPlayableCardShouldBeFound("initialBlock.greaterOrEqualThan=" + DEFAULT_INITIAL_BLOCK);

        // Get all the mPlayableCardList where initialBlock greater than or equals to UPDATED_INITIAL_BLOCK
        defaultMPlayableCardShouldNotBeFound("initialBlock.greaterOrEqualThan=" + UPDATED_INITIAL_BLOCK);
    }

    @Test
    @Transactional
    public void getAllMPlayableCardsByInitialBlockIsLessThanSomething() throws Exception {
        // Initialize the database
        mPlayableCardRepository.saveAndFlush(mPlayableCard);

        // Get all the mPlayableCardList where initialBlock less than or equals to DEFAULT_INITIAL_BLOCK
        defaultMPlayableCardShouldNotBeFound("initialBlock.lessThan=" + DEFAULT_INITIAL_BLOCK);

        // Get all the mPlayableCardList where initialBlock less than or equals to UPDATED_INITIAL_BLOCK
        defaultMPlayableCardShouldBeFound("initialBlock.lessThan=" + UPDATED_INITIAL_BLOCK);
    }


    @Test
    @Transactional
    public void getAllMPlayableCardsByInitialInterceptIsEqualToSomething() throws Exception {
        // Initialize the database
        mPlayableCardRepository.saveAndFlush(mPlayableCard);

        // Get all the mPlayableCardList where initialIntercept equals to DEFAULT_INITIAL_INTERCEPT
        defaultMPlayableCardShouldBeFound("initialIntercept.equals=" + DEFAULT_INITIAL_INTERCEPT);

        // Get all the mPlayableCardList where initialIntercept equals to UPDATED_INITIAL_INTERCEPT
        defaultMPlayableCardShouldNotBeFound("initialIntercept.equals=" + UPDATED_INITIAL_INTERCEPT);
    }

    @Test
    @Transactional
    public void getAllMPlayableCardsByInitialInterceptIsInShouldWork() throws Exception {
        // Initialize the database
        mPlayableCardRepository.saveAndFlush(mPlayableCard);

        // Get all the mPlayableCardList where initialIntercept in DEFAULT_INITIAL_INTERCEPT or UPDATED_INITIAL_INTERCEPT
        defaultMPlayableCardShouldBeFound("initialIntercept.in=" + DEFAULT_INITIAL_INTERCEPT + "," + UPDATED_INITIAL_INTERCEPT);

        // Get all the mPlayableCardList where initialIntercept equals to UPDATED_INITIAL_INTERCEPT
        defaultMPlayableCardShouldNotBeFound("initialIntercept.in=" + UPDATED_INITIAL_INTERCEPT);
    }

    @Test
    @Transactional
    public void getAllMPlayableCardsByInitialInterceptIsNullOrNotNull() throws Exception {
        // Initialize the database
        mPlayableCardRepository.saveAndFlush(mPlayableCard);

        // Get all the mPlayableCardList where initialIntercept is not null
        defaultMPlayableCardShouldBeFound("initialIntercept.specified=true");

        // Get all the mPlayableCardList where initialIntercept is null
        defaultMPlayableCardShouldNotBeFound("initialIntercept.specified=false");
    }

    @Test
    @Transactional
    public void getAllMPlayableCardsByInitialInterceptIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mPlayableCardRepository.saveAndFlush(mPlayableCard);

        // Get all the mPlayableCardList where initialIntercept greater than or equals to DEFAULT_INITIAL_INTERCEPT
        defaultMPlayableCardShouldBeFound("initialIntercept.greaterOrEqualThan=" + DEFAULT_INITIAL_INTERCEPT);

        // Get all the mPlayableCardList where initialIntercept greater than or equals to UPDATED_INITIAL_INTERCEPT
        defaultMPlayableCardShouldNotBeFound("initialIntercept.greaterOrEqualThan=" + UPDATED_INITIAL_INTERCEPT);
    }

    @Test
    @Transactional
    public void getAllMPlayableCardsByInitialInterceptIsLessThanSomething() throws Exception {
        // Initialize the database
        mPlayableCardRepository.saveAndFlush(mPlayableCard);

        // Get all the mPlayableCardList where initialIntercept less than or equals to DEFAULT_INITIAL_INTERCEPT
        defaultMPlayableCardShouldNotBeFound("initialIntercept.lessThan=" + DEFAULT_INITIAL_INTERCEPT);

        // Get all the mPlayableCardList where initialIntercept less than or equals to UPDATED_INITIAL_INTERCEPT
        defaultMPlayableCardShouldBeFound("initialIntercept.lessThan=" + UPDATED_INITIAL_INTERCEPT);
    }


    @Test
    @Transactional
    public void getAllMPlayableCardsByInitialSpeedIsEqualToSomething() throws Exception {
        // Initialize the database
        mPlayableCardRepository.saveAndFlush(mPlayableCard);

        // Get all the mPlayableCardList where initialSpeed equals to DEFAULT_INITIAL_SPEED
        defaultMPlayableCardShouldBeFound("initialSpeed.equals=" + DEFAULT_INITIAL_SPEED);

        // Get all the mPlayableCardList where initialSpeed equals to UPDATED_INITIAL_SPEED
        defaultMPlayableCardShouldNotBeFound("initialSpeed.equals=" + UPDATED_INITIAL_SPEED);
    }

    @Test
    @Transactional
    public void getAllMPlayableCardsByInitialSpeedIsInShouldWork() throws Exception {
        // Initialize the database
        mPlayableCardRepository.saveAndFlush(mPlayableCard);

        // Get all the mPlayableCardList where initialSpeed in DEFAULT_INITIAL_SPEED or UPDATED_INITIAL_SPEED
        defaultMPlayableCardShouldBeFound("initialSpeed.in=" + DEFAULT_INITIAL_SPEED + "," + UPDATED_INITIAL_SPEED);

        // Get all the mPlayableCardList where initialSpeed equals to UPDATED_INITIAL_SPEED
        defaultMPlayableCardShouldNotBeFound("initialSpeed.in=" + UPDATED_INITIAL_SPEED);
    }

    @Test
    @Transactional
    public void getAllMPlayableCardsByInitialSpeedIsNullOrNotNull() throws Exception {
        // Initialize the database
        mPlayableCardRepository.saveAndFlush(mPlayableCard);

        // Get all the mPlayableCardList where initialSpeed is not null
        defaultMPlayableCardShouldBeFound("initialSpeed.specified=true");

        // Get all the mPlayableCardList where initialSpeed is null
        defaultMPlayableCardShouldNotBeFound("initialSpeed.specified=false");
    }

    @Test
    @Transactional
    public void getAllMPlayableCardsByInitialSpeedIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mPlayableCardRepository.saveAndFlush(mPlayableCard);

        // Get all the mPlayableCardList where initialSpeed greater than or equals to DEFAULT_INITIAL_SPEED
        defaultMPlayableCardShouldBeFound("initialSpeed.greaterOrEqualThan=" + DEFAULT_INITIAL_SPEED);

        // Get all the mPlayableCardList where initialSpeed greater than or equals to UPDATED_INITIAL_SPEED
        defaultMPlayableCardShouldNotBeFound("initialSpeed.greaterOrEqualThan=" + UPDATED_INITIAL_SPEED);
    }

    @Test
    @Transactional
    public void getAllMPlayableCardsByInitialSpeedIsLessThanSomething() throws Exception {
        // Initialize the database
        mPlayableCardRepository.saveAndFlush(mPlayableCard);

        // Get all the mPlayableCardList where initialSpeed less than or equals to DEFAULT_INITIAL_SPEED
        defaultMPlayableCardShouldNotBeFound("initialSpeed.lessThan=" + DEFAULT_INITIAL_SPEED);

        // Get all the mPlayableCardList where initialSpeed less than or equals to UPDATED_INITIAL_SPEED
        defaultMPlayableCardShouldBeFound("initialSpeed.lessThan=" + UPDATED_INITIAL_SPEED);
    }


    @Test
    @Transactional
    public void getAllMPlayableCardsByInitialPowerIsEqualToSomething() throws Exception {
        // Initialize the database
        mPlayableCardRepository.saveAndFlush(mPlayableCard);

        // Get all the mPlayableCardList where initialPower equals to DEFAULT_INITIAL_POWER
        defaultMPlayableCardShouldBeFound("initialPower.equals=" + DEFAULT_INITIAL_POWER);

        // Get all the mPlayableCardList where initialPower equals to UPDATED_INITIAL_POWER
        defaultMPlayableCardShouldNotBeFound("initialPower.equals=" + UPDATED_INITIAL_POWER);
    }

    @Test
    @Transactional
    public void getAllMPlayableCardsByInitialPowerIsInShouldWork() throws Exception {
        // Initialize the database
        mPlayableCardRepository.saveAndFlush(mPlayableCard);

        // Get all the mPlayableCardList where initialPower in DEFAULT_INITIAL_POWER or UPDATED_INITIAL_POWER
        defaultMPlayableCardShouldBeFound("initialPower.in=" + DEFAULT_INITIAL_POWER + "," + UPDATED_INITIAL_POWER);

        // Get all the mPlayableCardList where initialPower equals to UPDATED_INITIAL_POWER
        defaultMPlayableCardShouldNotBeFound("initialPower.in=" + UPDATED_INITIAL_POWER);
    }

    @Test
    @Transactional
    public void getAllMPlayableCardsByInitialPowerIsNullOrNotNull() throws Exception {
        // Initialize the database
        mPlayableCardRepository.saveAndFlush(mPlayableCard);

        // Get all the mPlayableCardList where initialPower is not null
        defaultMPlayableCardShouldBeFound("initialPower.specified=true");

        // Get all the mPlayableCardList where initialPower is null
        defaultMPlayableCardShouldNotBeFound("initialPower.specified=false");
    }

    @Test
    @Transactional
    public void getAllMPlayableCardsByInitialPowerIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mPlayableCardRepository.saveAndFlush(mPlayableCard);

        // Get all the mPlayableCardList where initialPower greater than or equals to DEFAULT_INITIAL_POWER
        defaultMPlayableCardShouldBeFound("initialPower.greaterOrEqualThan=" + DEFAULT_INITIAL_POWER);

        // Get all the mPlayableCardList where initialPower greater than or equals to UPDATED_INITIAL_POWER
        defaultMPlayableCardShouldNotBeFound("initialPower.greaterOrEqualThan=" + UPDATED_INITIAL_POWER);
    }

    @Test
    @Transactional
    public void getAllMPlayableCardsByInitialPowerIsLessThanSomething() throws Exception {
        // Initialize the database
        mPlayableCardRepository.saveAndFlush(mPlayableCard);

        // Get all the mPlayableCardList where initialPower less than or equals to DEFAULT_INITIAL_POWER
        defaultMPlayableCardShouldNotBeFound("initialPower.lessThan=" + DEFAULT_INITIAL_POWER);

        // Get all the mPlayableCardList where initialPower less than or equals to UPDATED_INITIAL_POWER
        defaultMPlayableCardShouldBeFound("initialPower.lessThan=" + UPDATED_INITIAL_POWER);
    }


    @Test
    @Transactional
    public void getAllMPlayableCardsByInitialTechniqueIsEqualToSomething() throws Exception {
        // Initialize the database
        mPlayableCardRepository.saveAndFlush(mPlayableCard);

        // Get all the mPlayableCardList where initialTechnique equals to DEFAULT_INITIAL_TECHNIQUE
        defaultMPlayableCardShouldBeFound("initialTechnique.equals=" + DEFAULT_INITIAL_TECHNIQUE);

        // Get all the mPlayableCardList where initialTechnique equals to UPDATED_INITIAL_TECHNIQUE
        defaultMPlayableCardShouldNotBeFound("initialTechnique.equals=" + UPDATED_INITIAL_TECHNIQUE);
    }

    @Test
    @Transactional
    public void getAllMPlayableCardsByInitialTechniqueIsInShouldWork() throws Exception {
        // Initialize the database
        mPlayableCardRepository.saveAndFlush(mPlayableCard);

        // Get all the mPlayableCardList where initialTechnique in DEFAULT_INITIAL_TECHNIQUE or UPDATED_INITIAL_TECHNIQUE
        defaultMPlayableCardShouldBeFound("initialTechnique.in=" + DEFAULT_INITIAL_TECHNIQUE + "," + UPDATED_INITIAL_TECHNIQUE);

        // Get all the mPlayableCardList where initialTechnique equals to UPDATED_INITIAL_TECHNIQUE
        defaultMPlayableCardShouldNotBeFound("initialTechnique.in=" + UPDATED_INITIAL_TECHNIQUE);
    }

    @Test
    @Transactional
    public void getAllMPlayableCardsByInitialTechniqueIsNullOrNotNull() throws Exception {
        // Initialize the database
        mPlayableCardRepository.saveAndFlush(mPlayableCard);

        // Get all the mPlayableCardList where initialTechnique is not null
        defaultMPlayableCardShouldBeFound("initialTechnique.specified=true");

        // Get all the mPlayableCardList where initialTechnique is null
        defaultMPlayableCardShouldNotBeFound("initialTechnique.specified=false");
    }

    @Test
    @Transactional
    public void getAllMPlayableCardsByInitialTechniqueIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mPlayableCardRepository.saveAndFlush(mPlayableCard);

        // Get all the mPlayableCardList where initialTechnique greater than or equals to DEFAULT_INITIAL_TECHNIQUE
        defaultMPlayableCardShouldBeFound("initialTechnique.greaterOrEqualThan=" + DEFAULT_INITIAL_TECHNIQUE);

        // Get all the mPlayableCardList where initialTechnique greater than or equals to UPDATED_INITIAL_TECHNIQUE
        defaultMPlayableCardShouldNotBeFound("initialTechnique.greaterOrEqualThan=" + UPDATED_INITIAL_TECHNIQUE);
    }

    @Test
    @Transactional
    public void getAllMPlayableCardsByInitialTechniqueIsLessThanSomething() throws Exception {
        // Initialize the database
        mPlayableCardRepository.saveAndFlush(mPlayableCard);

        // Get all the mPlayableCardList where initialTechnique less than or equals to DEFAULT_INITIAL_TECHNIQUE
        defaultMPlayableCardShouldNotBeFound("initialTechnique.lessThan=" + DEFAULT_INITIAL_TECHNIQUE);

        // Get all the mPlayableCardList where initialTechnique less than or equals to UPDATED_INITIAL_TECHNIQUE
        defaultMPlayableCardShouldBeFound("initialTechnique.lessThan=" + UPDATED_INITIAL_TECHNIQUE);
    }


    @Test
    @Transactional
    public void getAllMPlayableCardsByInitialPunchingIsEqualToSomething() throws Exception {
        // Initialize the database
        mPlayableCardRepository.saveAndFlush(mPlayableCard);

        // Get all the mPlayableCardList where initialPunching equals to DEFAULT_INITIAL_PUNCHING
        defaultMPlayableCardShouldBeFound("initialPunching.equals=" + DEFAULT_INITIAL_PUNCHING);

        // Get all the mPlayableCardList where initialPunching equals to UPDATED_INITIAL_PUNCHING
        defaultMPlayableCardShouldNotBeFound("initialPunching.equals=" + UPDATED_INITIAL_PUNCHING);
    }

    @Test
    @Transactional
    public void getAllMPlayableCardsByInitialPunchingIsInShouldWork() throws Exception {
        // Initialize the database
        mPlayableCardRepository.saveAndFlush(mPlayableCard);

        // Get all the mPlayableCardList where initialPunching in DEFAULT_INITIAL_PUNCHING or UPDATED_INITIAL_PUNCHING
        defaultMPlayableCardShouldBeFound("initialPunching.in=" + DEFAULT_INITIAL_PUNCHING + "," + UPDATED_INITIAL_PUNCHING);

        // Get all the mPlayableCardList where initialPunching equals to UPDATED_INITIAL_PUNCHING
        defaultMPlayableCardShouldNotBeFound("initialPunching.in=" + UPDATED_INITIAL_PUNCHING);
    }

    @Test
    @Transactional
    public void getAllMPlayableCardsByInitialPunchingIsNullOrNotNull() throws Exception {
        // Initialize the database
        mPlayableCardRepository.saveAndFlush(mPlayableCard);

        // Get all the mPlayableCardList where initialPunching is not null
        defaultMPlayableCardShouldBeFound("initialPunching.specified=true");

        // Get all the mPlayableCardList where initialPunching is null
        defaultMPlayableCardShouldNotBeFound("initialPunching.specified=false");
    }

    @Test
    @Transactional
    public void getAllMPlayableCardsByInitialPunchingIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mPlayableCardRepository.saveAndFlush(mPlayableCard);

        // Get all the mPlayableCardList where initialPunching greater than or equals to DEFAULT_INITIAL_PUNCHING
        defaultMPlayableCardShouldBeFound("initialPunching.greaterOrEqualThan=" + DEFAULT_INITIAL_PUNCHING);

        // Get all the mPlayableCardList where initialPunching greater than or equals to UPDATED_INITIAL_PUNCHING
        defaultMPlayableCardShouldNotBeFound("initialPunching.greaterOrEqualThan=" + UPDATED_INITIAL_PUNCHING);
    }

    @Test
    @Transactional
    public void getAllMPlayableCardsByInitialPunchingIsLessThanSomething() throws Exception {
        // Initialize the database
        mPlayableCardRepository.saveAndFlush(mPlayableCard);

        // Get all the mPlayableCardList where initialPunching less than or equals to DEFAULT_INITIAL_PUNCHING
        defaultMPlayableCardShouldNotBeFound("initialPunching.lessThan=" + DEFAULT_INITIAL_PUNCHING);

        // Get all the mPlayableCardList where initialPunching less than or equals to UPDATED_INITIAL_PUNCHING
        defaultMPlayableCardShouldBeFound("initialPunching.lessThan=" + UPDATED_INITIAL_PUNCHING);
    }


    @Test
    @Transactional
    public void getAllMPlayableCardsByInitialCatchingIsEqualToSomething() throws Exception {
        // Initialize the database
        mPlayableCardRepository.saveAndFlush(mPlayableCard);

        // Get all the mPlayableCardList where initialCatching equals to DEFAULT_INITIAL_CATCHING
        defaultMPlayableCardShouldBeFound("initialCatching.equals=" + DEFAULT_INITIAL_CATCHING);

        // Get all the mPlayableCardList where initialCatching equals to UPDATED_INITIAL_CATCHING
        defaultMPlayableCardShouldNotBeFound("initialCatching.equals=" + UPDATED_INITIAL_CATCHING);
    }

    @Test
    @Transactional
    public void getAllMPlayableCardsByInitialCatchingIsInShouldWork() throws Exception {
        // Initialize the database
        mPlayableCardRepository.saveAndFlush(mPlayableCard);

        // Get all the mPlayableCardList where initialCatching in DEFAULT_INITIAL_CATCHING or UPDATED_INITIAL_CATCHING
        defaultMPlayableCardShouldBeFound("initialCatching.in=" + DEFAULT_INITIAL_CATCHING + "," + UPDATED_INITIAL_CATCHING);

        // Get all the mPlayableCardList where initialCatching equals to UPDATED_INITIAL_CATCHING
        defaultMPlayableCardShouldNotBeFound("initialCatching.in=" + UPDATED_INITIAL_CATCHING);
    }

    @Test
    @Transactional
    public void getAllMPlayableCardsByInitialCatchingIsNullOrNotNull() throws Exception {
        // Initialize the database
        mPlayableCardRepository.saveAndFlush(mPlayableCard);

        // Get all the mPlayableCardList where initialCatching is not null
        defaultMPlayableCardShouldBeFound("initialCatching.specified=true");

        // Get all the mPlayableCardList where initialCatching is null
        defaultMPlayableCardShouldNotBeFound("initialCatching.specified=false");
    }

    @Test
    @Transactional
    public void getAllMPlayableCardsByInitialCatchingIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mPlayableCardRepository.saveAndFlush(mPlayableCard);

        // Get all the mPlayableCardList where initialCatching greater than or equals to DEFAULT_INITIAL_CATCHING
        defaultMPlayableCardShouldBeFound("initialCatching.greaterOrEqualThan=" + DEFAULT_INITIAL_CATCHING);

        // Get all the mPlayableCardList where initialCatching greater than or equals to UPDATED_INITIAL_CATCHING
        defaultMPlayableCardShouldNotBeFound("initialCatching.greaterOrEqualThan=" + UPDATED_INITIAL_CATCHING);
    }

    @Test
    @Transactional
    public void getAllMPlayableCardsByInitialCatchingIsLessThanSomething() throws Exception {
        // Initialize the database
        mPlayableCardRepository.saveAndFlush(mPlayableCard);

        // Get all the mPlayableCardList where initialCatching less than or equals to DEFAULT_INITIAL_CATCHING
        defaultMPlayableCardShouldNotBeFound("initialCatching.lessThan=" + DEFAULT_INITIAL_CATCHING);

        // Get all the mPlayableCardList where initialCatching less than or equals to UPDATED_INITIAL_CATCHING
        defaultMPlayableCardShouldBeFound("initialCatching.lessThan=" + UPDATED_INITIAL_CATCHING);
    }


    @Test
    @Transactional
    public void getAllMPlayableCardsByMaxStaminaIsEqualToSomething() throws Exception {
        // Initialize the database
        mPlayableCardRepository.saveAndFlush(mPlayableCard);

        // Get all the mPlayableCardList where maxStamina equals to DEFAULT_MAX_STAMINA
        defaultMPlayableCardShouldBeFound("maxStamina.equals=" + DEFAULT_MAX_STAMINA);

        // Get all the mPlayableCardList where maxStamina equals to UPDATED_MAX_STAMINA
        defaultMPlayableCardShouldNotBeFound("maxStamina.equals=" + UPDATED_MAX_STAMINA);
    }

    @Test
    @Transactional
    public void getAllMPlayableCardsByMaxStaminaIsInShouldWork() throws Exception {
        // Initialize the database
        mPlayableCardRepository.saveAndFlush(mPlayableCard);

        // Get all the mPlayableCardList where maxStamina in DEFAULT_MAX_STAMINA or UPDATED_MAX_STAMINA
        defaultMPlayableCardShouldBeFound("maxStamina.in=" + DEFAULT_MAX_STAMINA + "," + UPDATED_MAX_STAMINA);

        // Get all the mPlayableCardList where maxStamina equals to UPDATED_MAX_STAMINA
        defaultMPlayableCardShouldNotBeFound("maxStamina.in=" + UPDATED_MAX_STAMINA);
    }

    @Test
    @Transactional
    public void getAllMPlayableCardsByMaxStaminaIsNullOrNotNull() throws Exception {
        // Initialize the database
        mPlayableCardRepository.saveAndFlush(mPlayableCard);

        // Get all the mPlayableCardList where maxStamina is not null
        defaultMPlayableCardShouldBeFound("maxStamina.specified=true");

        // Get all the mPlayableCardList where maxStamina is null
        defaultMPlayableCardShouldNotBeFound("maxStamina.specified=false");
    }

    @Test
    @Transactional
    public void getAllMPlayableCardsByMaxStaminaIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mPlayableCardRepository.saveAndFlush(mPlayableCard);

        // Get all the mPlayableCardList where maxStamina greater than or equals to DEFAULT_MAX_STAMINA
        defaultMPlayableCardShouldBeFound("maxStamina.greaterOrEqualThan=" + DEFAULT_MAX_STAMINA);

        // Get all the mPlayableCardList where maxStamina greater than or equals to UPDATED_MAX_STAMINA
        defaultMPlayableCardShouldNotBeFound("maxStamina.greaterOrEqualThan=" + UPDATED_MAX_STAMINA);
    }

    @Test
    @Transactional
    public void getAllMPlayableCardsByMaxStaminaIsLessThanSomething() throws Exception {
        // Initialize the database
        mPlayableCardRepository.saveAndFlush(mPlayableCard);

        // Get all the mPlayableCardList where maxStamina less than or equals to DEFAULT_MAX_STAMINA
        defaultMPlayableCardShouldNotBeFound("maxStamina.lessThan=" + DEFAULT_MAX_STAMINA);

        // Get all the mPlayableCardList where maxStamina less than or equals to UPDATED_MAX_STAMINA
        defaultMPlayableCardShouldBeFound("maxStamina.lessThan=" + UPDATED_MAX_STAMINA);
    }


    @Test
    @Transactional
    public void getAllMPlayableCardsByMaxDribbleIsEqualToSomething() throws Exception {
        // Initialize the database
        mPlayableCardRepository.saveAndFlush(mPlayableCard);

        // Get all the mPlayableCardList where maxDribble equals to DEFAULT_MAX_DRIBBLE
        defaultMPlayableCardShouldBeFound("maxDribble.equals=" + DEFAULT_MAX_DRIBBLE);

        // Get all the mPlayableCardList where maxDribble equals to UPDATED_MAX_DRIBBLE
        defaultMPlayableCardShouldNotBeFound("maxDribble.equals=" + UPDATED_MAX_DRIBBLE);
    }

    @Test
    @Transactional
    public void getAllMPlayableCardsByMaxDribbleIsInShouldWork() throws Exception {
        // Initialize the database
        mPlayableCardRepository.saveAndFlush(mPlayableCard);

        // Get all the mPlayableCardList where maxDribble in DEFAULT_MAX_DRIBBLE or UPDATED_MAX_DRIBBLE
        defaultMPlayableCardShouldBeFound("maxDribble.in=" + DEFAULT_MAX_DRIBBLE + "," + UPDATED_MAX_DRIBBLE);

        // Get all the mPlayableCardList where maxDribble equals to UPDATED_MAX_DRIBBLE
        defaultMPlayableCardShouldNotBeFound("maxDribble.in=" + UPDATED_MAX_DRIBBLE);
    }

    @Test
    @Transactional
    public void getAllMPlayableCardsByMaxDribbleIsNullOrNotNull() throws Exception {
        // Initialize the database
        mPlayableCardRepository.saveAndFlush(mPlayableCard);

        // Get all the mPlayableCardList where maxDribble is not null
        defaultMPlayableCardShouldBeFound("maxDribble.specified=true");

        // Get all the mPlayableCardList where maxDribble is null
        defaultMPlayableCardShouldNotBeFound("maxDribble.specified=false");
    }

    @Test
    @Transactional
    public void getAllMPlayableCardsByMaxDribbleIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mPlayableCardRepository.saveAndFlush(mPlayableCard);

        // Get all the mPlayableCardList where maxDribble greater than or equals to DEFAULT_MAX_DRIBBLE
        defaultMPlayableCardShouldBeFound("maxDribble.greaterOrEqualThan=" + DEFAULT_MAX_DRIBBLE);

        // Get all the mPlayableCardList where maxDribble greater than or equals to UPDATED_MAX_DRIBBLE
        defaultMPlayableCardShouldNotBeFound("maxDribble.greaterOrEqualThan=" + UPDATED_MAX_DRIBBLE);
    }

    @Test
    @Transactional
    public void getAllMPlayableCardsByMaxDribbleIsLessThanSomething() throws Exception {
        // Initialize the database
        mPlayableCardRepository.saveAndFlush(mPlayableCard);

        // Get all the mPlayableCardList where maxDribble less than or equals to DEFAULT_MAX_DRIBBLE
        defaultMPlayableCardShouldNotBeFound("maxDribble.lessThan=" + DEFAULT_MAX_DRIBBLE);

        // Get all the mPlayableCardList where maxDribble less than or equals to UPDATED_MAX_DRIBBLE
        defaultMPlayableCardShouldBeFound("maxDribble.lessThan=" + UPDATED_MAX_DRIBBLE);
    }


    @Test
    @Transactional
    public void getAllMPlayableCardsByMaxShootIsEqualToSomething() throws Exception {
        // Initialize the database
        mPlayableCardRepository.saveAndFlush(mPlayableCard);

        // Get all the mPlayableCardList where maxShoot equals to DEFAULT_MAX_SHOOT
        defaultMPlayableCardShouldBeFound("maxShoot.equals=" + DEFAULT_MAX_SHOOT);

        // Get all the mPlayableCardList where maxShoot equals to UPDATED_MAX_SHOOT
        defaultMPlayableCardShouldNotBeFound("maxShoot.equals=" + UPDATED_MAX_SHOOT);
    }

    @Test
    @Transactional
    public void getAllMPlayableCardsByMaxShootIsInShouldWork() throws Exception {
        // Initialize the database
        mPlayableCardRepository.saveAndFlush(mPlayableCard);

        // Get all the mPlayableCardList where maxShoot in DEFAULT_MAX_SHOOT or UPDATED_MAX_SHOOT
        defaultMPlayableCardShouldBeFound("maxShoot.in=" + DEFAULT_MAX_SHOOT + "," + UPDATED_MAX_SHOOT);

        // Get all the mPlayableCardList where maxShoot equals to UPDATED_MAX_SHOOT
        defaultMPlayableCardShouldNotBeFound("maxShoot.in=" + UPDATED_MAX_SHOOT);
    }

    @Test
    @Transactional
    public void getAllMPlayableCardsByMaxShootIsNullOrNotNull() throws Exception {
        // Initialize the database
        mPlayableCardRepository.saveAndFlush(mPlayableCard);

        // Get all the mPlayableCardList where maxShoot is not null
        defaultMPlayableCardShouldBeFound("maxShoot.specified=true");

        // Get all the mPlayableCardList where maxShoot is null
        defaultMPlayableCardShouldNotBeFound("maxShoot.specified=false");
    }

    @Test
    @Transactional
    public void getAllMPlayableCardsByMaxShootIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mPlayableCardRepository.saveAndFlush(mPlayableCard);

        // Get all the mPlayableCardList where maxShoot greater than or equals to DEFAULT_MAX_SHOOT
        defaultMPlayableCardShouldBeFound("maxShoot.greaterOrEqualThan=" + DEFAULT_MAX_SHOOT);

        // Get all the mPlayableCardList where maxShoot greater than or equals to UPDATED_MAX_SHOOT
        defaultMPlayableCardShouldNotBeFound("maxShoot.greaterOrEqualThan=" + UPDATED_MAX_SHOOT);
    }

    @Test
    @Transactional
    public void getAllMPlayableCardsByMaxShootIsLessThanSomething() throws Exception {
        // Initialize the database
        mPlayableCardRepository.saveAndFlush(mPlayableCard);

        // Get all the mPlayableCardList where maxShoot less than or equals to DEFAULT_MAX_SHOOT
        defaultMPlayableCardShouldNotBeFound("maxShoot.lessThan=" + DEFAULT_MAX_SHOOT);

        // Get all the mPlayableCardList where maxShoot less than or equals to UPDATED_MAX_SHOOT
        defaultMPlayableCardShouldBeFound("maxShoot.lessThan=" + UPDATED_MAX_SHOOT);
    }


    @Test
    @Transactional
    public void getAllMPlayableCardsByMaxPassIsEqualToSomething() throws Exception {
        // Initialize the database
        mPlayableCardRepository.saveAndFlush(mPlayableCard);

        // Get all the mPlayableCardList where maxPass equals to DEFAULT_MAX_PASS
        defaultMPlayableCardShouldBeFound("maxPass.equals=" + DEFAULT_MAX_PASS);

        // Get all the mPlayableCardList where maxPass equals to UPDATED_MAX_PASS
        defaultMPlayableCardShouldNotBeFound("maxPass.equals=" + UPDATED_MAX_PASS);
    }

    @Test
    @Transactional
    public void getAllMPlayableCardsByMaxPassIsInShouldWork() throws Exception {
        // Initialize the database
        mPlayableCardRepository.saveAndFlush(mPlayableCard);

        // Get all the mPlayableCardList where maxPass in DEFAULT_MAX_PASS or UPDATED_MAX_PASS
        defaultMPlayableCardShouldBeFound("maxPass.in=" + DEFAULT_MAX_PASS + "," + UPDATED_MAX_PASS);

        // Get all the mPlayableCardList where maxPass equals to UPDATED_MAX_PASS
        defaultMPlayableCardShouldNotBeFound("maxPass.in=" + UPDATED_MAX_PASS);
    }

    @Test
    @Transactional
    public void getAllMPlayableCardsByMaxPassIsNullOrNotNull() throws Exception {
        // Initialize the database
        mPlayableCardRepository.saveAndFlush(mPlayableCard);

        // Get all the mPlayableCardList where maxPass is not null
        defaultMPlayableCardShouldBeFound("maxPass.specified=true");

        // Get all the mPlayableCardList where maxPass is null
        defaultMPlayableCardShouldNotBeFound("maxPass.specified=false");
    }

    @Test
    @Transactional
    public void getAllMPlayableCardsByMaxPassIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mPlayableCardRepository.saveAndFlush(mPlayableCard);

        // Get all the mPlayableCardList where maxPass greater than or equals to DEFAULT_MAX_PASS
        defaultMPlayableCardShouldBeFound("maxPass.greaterOrEqualThan=" + DEFAULT_MAX_PASS);

        // Get all the mPlayableCardList where maxPass greater than or equals to UPDATED_MAX_PASS
        defaultMPlayableCardShouldNotBeFound("maxPass.greaterOrEqualThan=" + UPDATED_MAX_PASS);
    }

    @Test
    @Transactional
    public void getAllMPlayableCardsByMaxPassIsLessThanSomething() throws Exception {
        // Initialize the database
        mPlayableCardRepository.saveAndFlush(mPlayableCard);

        // Get all the mPlayableCardList where maxPass less than or equals to DEFAULT_MAX_PASS
        defaultMPlayableCardShouldNotBeFound("maxPass.lessThan=" + DEFAULT_MAX_PASS);

        // Get all the mPlayableCardList where maxPass less than or equals to UPDATED_MAX_PASS
        defaultMPlayableCardShouldBeFound("maxPass.lessThan=" + UPDATED_MAX_PASS);
    }


    @Test
    @Transactional
    public void getAllMPlayableCardsByMaxTackleIsEqualToSomething() throws Exception {
        // Initialize the database
        mPlayableCardRepository.saveAndFlush(mPlayableCard);

        // Get all the mPlayableCardList where maxTackle equals to DEFAULT_MAX_TACKLE
        defaultMPlayableCardShouldBeFound("maxTackle.equals=" + DEFAULT_MAX_TACKLE);

        // Get all the mPlayableCardList where maxTackle equals to UPDATED_MAX_TACKLE
        defaultMPlayableCardShouldNotBeFound("maxTackle.equals=" + UPDATED_MAX_TACKLE);
    }

    @Test
    @Transactional
    public void getAllMPlayableCardsByMaxTackleIsInShouldWork() throws Exception {
        // Initialize the database
        mPlayableCardRepository.saveAndFlush(mPlayableCard);

        // Get all the mPlayableCardList where maxTackle in DEFAULT_MAX_TACKLE or UPDATED_MAX_TACKLE
        defaultMPlayableCardShouldBeFound("maxTackle.in=" + DEFAULT_MAX_TACKLE + "," + UPDATED_MAX_TACKLE);

        // Get all the mPlayableCardList where maxTackle equals to UPDATED_MAX_TACKLE
        defaultMPlayableCardShouldNotBeFound("maxTackle.in=" + UPDATED_MAX_TACKLE);
    }

    @Test
    @Transactional
    public void getAllMPlayableCardsByMaxTackleIsNullOrNotNull() throws Exception {
        // Initialize the database
        mPlayableCardRepository.saveAndFlush(mPlayableCard);

        // Get all the mPlayableCardList where maxTackle is not null
        defaultMPlayableCardShouldBeFound("maxTackle.specified=true");

        // Get all the mPlayableCardList where maxTackle is null
        defaultMPlayableCardShouldNotBeFound("maxTackle.specified=false");
    }

    @Test
    @Transactional
    public void getAllMPlayableCardsByMaxTackleIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mPlayableCardRepository.saveAndFlush(mPlayableCard);

        // Get all the mPlayableCardList where maxTackle greater than or equals to DEFAULT_MAX_TACKLE
        defaultMPlayableCardShouldBeFound("maxTackle.greaterOrEqualThan=" + DEFAULT_MAX_TACKLE);

        // Get all the mPlayableCardList where maxTackle greater than or equals to UPDATED_MAX_TACKLE
        defaultMPlayableCardShouldNotBeFound("maxTackle.greaterOrEqualThan=" + UPDATED_MAX_TACKLE);
    }

    @Test
    @Transactional
    public void getAllMPlayableCardsByMaxTackleIsLessThanSomething() throws Exception {
        // Initialize the database
        mPlayableCardRepository.saveAndFlush(mPlayableCard);

        // Get all the mPlayableCardList where maxTackle less than or equals to DEFAULT_MAX_TACKLE
        defaultMPlayableCardShouldNotBeFound("maxTackle.lessThan=" + DEFAULT_MAX_TACKLE);

        // Get all the mPlayableCardList where maxTackle less than or equals to UPDATED_MAX_TACKLE
        defaultMPlayableCardShouldBeFound("maxTackle.lessThan=" + UPDATED_MAX_TACKLE);
    }


    @Test
    @Transactional
    public void getAllMPlayableCardsByMaxBlockIsEqualToSomething() throws Exception {
        // Initialize the database
        mPlayableCardRepository.saveAndFlush(mPlayableCard);

        // Get all the mPlayableCardList where maxBlock equals to DEFAULT_MAX_BLOCK
        defaultMPlayableCardShouldBeFound("maxBlock.equals=" + DEFAULT_MAX_BLOCK);

        // Get all the mPlayableCardList where maxBlock equals to UPDATED_MAX_BLOCK
        defaultMPlayableCardShouldNotBeFound("maxBlock.equals=" + UPDATED_MAX_BLOCK);
    }

    @Test
    @Transactional
    public void getAllMPlayableCardsByMaxBlockIsInShouldWork() throws Exception {
        // Initialize the database
        mPlayableCardRepository.saveAndFlush(mPlayableCard);

        // Get all the mPlayableCardList where maxBlock in DEFAULT_MAX_BLOCK or UPDATED_MAX_BLOCK
        defaultMPlayableCardShouldBeFound("maxBlock.in=" + DEFAULT_MAX_BLOCK + "," + UPDATED_MAX_BLOCK);

        // Get all the mPlayableCardList where maxBlock equals to UPDATED_MAX_BLOCK
        defaultMPlayableCardShouldNotBeFound("maxBlock.in=" + UPDATED_MAX_BLOCK);
    }

    @Test
    @Transactional
    public void getAllMPlayableCardsByMaxBlockIsNullOrNotNull() throws Exception {
        // Initialize the database
        mPlayableCardRepository.saveAndFlush(mPlayableCard);

        // Get all the mPlayableCardList where maxBlock is not null
        defaultMPlayableCardShouldBeFound("maxBlock.specified=true");

        // Get all the mPlayableCardList where maxBlock is null
        defaultMPlayableCardShouldNotBeFound("maxBlock.specified=false");
    }

    @Test
    @Transactional
    public void getAllMPlayableCardsByMaxBlockIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mPlayableCardRepository.saveAndFlush(mPlayableCard);

        // Get all the mPlayableCardList where maxBlock greater than or equals to DEFAULT_MAX_BLOCK
        defaultMPlayableCardShouldBeFound("maxBlock.greaterOrEqualThan=" + DEFAULT_MAX_BLOCK);

        // Get all the mPlayableCardList where maxBlock greater than or equals to UPDATED_MAX_BLOCK
        defaultMPlayableCardShouldNotBeFound("maxBlock.greaterOrEqualThan=" + UPDATED_MAX_BLOCK);
    }

    @Test
    @Transactional
    public void getAllMPlayableCardsByMaxBlockIsLessThanSomething() throws Exception {
        // Initialize the database
        mPlayableCardRepository.saveAndFlush(mPlayableCard);

        // Get all the mPlayableCardList where maxBlock less than or equals to DEFAULT_MAX_BLOCK
        defaultMPlayableCardShouldNotBeFound("maxBlock.lessThan=" + DEFAULT_MAX_BLOCK);

        // Get all the mPlayableCardList where maxBlock less than or equals to UPDATED_MAX_BLOCK
        defaultMPlayableCardShouldBeFound("maxBlock.lessThan=" + UPDATED_MAX_BLOCK);
    }


    @Test
    @Transactional
    public void getAllMPlayableCardsByMaxInterceptIsEqualToSomething() throws Exception {
        // Initialize the database
        mPlayableCardRepository.saveAndFlush(mPlayableCard);

        // Get all the mPlayableCardList where maxIntercept equals to DEFAULT_MAX_INTERCEPT
        defaultMPlayableCardShouldBeFound("maxIntercept.equals=" + DEFAULT_MAX_INTERCEPT);

        // Get all the mPlayableCardList where maxIntercept equals to UPDATED_MAX_INTERCEPT
        defaultMPlayableCardShouldNotBeFound("maxIntercept.equals=" + UPDATED_MAX_INTERCEPT);
    }

    @Test
    @Transactional
    public void getAllMPlayableCardsByMaxInterceptIsInShouldWork() throws Exception {
        // Initialize the database
        mPlayableCardRepository.saveAndFlush(mPlayableCard);

        // Get all the mPlayableCardList where maxIntercept in DEFAULT_MAX_INTERCEPT or UPDATED_MAX_INTERCEPT
        defaultMPlayableCardShouldBeFound("maxIntercept.in=" + DEFAULT_MAX_INTERCEPT + "," + UPDATED_MAX_INTERCEPT);

        // Get all the mPlayableCardList where maxIntercept equals to UPDATED_MAX_INTERCEPT
        defaultMPlayableCardShouldNotBeFound("maxIntercept.in=" + UPDATED_MAX_INTERCEPT);
    }

    @Test
    @Transactional
    public void getAllMPlayableCardsByMaxInterceptIsNullOrNotNull() throws Exception {
        // Initialize the database
        mPlayableCardRepository.saveAndFlush(mPlayableCard);

        // Get all the mPlayableCardList where maxIntercept is not null
        defaultMPlayableCardShouldBeFound("maxIntercept.specified=true");

        // Get all the mPlayableCardList where maxIntercept is null
        defaultMPlayableCardShouldNotBeFound("maxIntercept.specified=false");
    }

    @Test
    @Transactional
    public void getAllMPlayableCardsByMaxInterceptIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mPlayableCardRepository.saveAndFlush(mPlayableCard);

        // Get all the mPlayableCardList where maxIntercept greater than or equals to DEFAULT_MAX_INTERCEPT
        defaultMPlayableCardShouldBeFound("maxIntercept.greaterOrEqualThan=" + DEFAULT_MAX_INTERCEPT);

        // Get all the mPlayableCardList where maxIntercept greater than or equals to UPDATED_MAX_INTERCEPT
        defaultMPlayableCardShouldNotBeFound("maxIntercept.greaterOrEqualThan=" + UPDATED_MAX_INTERCEPT);
    }

    @Test
    @Transactional
    public void getAllMPlayableCardsByMaxInterceptIsLessThanSomething() throws Exception {
        // Initialize the database
        mPlayableCardRepository.saveAndFlush(mPlayableCard);

        // Get all the mPlayableCardList where maxIntercept less than or equals to DEFAULT_MAX_INTERCEPT
        defaultMPlayableCardShouldNotBeFound("maxIntercept.lessThan=" + DEFAULT_MAX_INTERCEPT);

        // Get all the mPlayableCardList where maxIntercept less than or equals to UPDATED_MAX_INTERCEPT
        defaultMPlayableCardShouldBeFound("maxIntercept.lessThan=" + UPDATED_MAX_INTERCEPT);
    }


    @Test
    @Transactional
    public void getAllMPlayableCardsByMaxSpeedIsEqualToSomething() throws Exception {
        // Initialize the database
        mPlayableCardRepository.saveAndFlush(mPlayableCard);

        // Get all the mPlayableCardList where maxSpeed equals to DEFAULT_MAX_SPEED
        defaultMPlayableCardShouldBeFound("maxSpeed.equals=" + DEFAULT_MAX_SPEED);

        // Get all the mPlayableCardList where maxSpeed equals to UPDATED_MAX_SPEED
        defaultMPlayableCardShouldNotBeFound("maxSpeed.equals=" + UPDATED_MAX_SPEED);
    }

    @Test
    @Transactional
    public void getAllMPlayableCardsByMaxSpeedIsInShouldWork() throws Exception {
        // Initialize the database
        mPlayableCardRepository.saveAndFlush(mPlayableCard);

        // Get all the mPlayableCardList where maxSpeed in DEFAULT_MAX_SPEED or UPDATED_MAX_SPEED
        defaultMPlayableCardShouldBeFound("maxSpeed.in=" + DEFAULT_MAX_SPEED + "," + UPDATED_MAX_SPEED);

        // Get all the mPlayableCardList where maxSpeed equals to UPDATED_MAX_SPEED
        defaultMPlayableCardShouldNotBeFound("maxSpeed.in=" + UPDATED_MAX_SPEED);
    }

    @Test
    @Transactional
    public void getAllMPlayableCardsByMaxSpeedIsNullOrNotNull() throws Exception {
        // Initialize the database
        mPlayableCardRepository.saveAndFlush(mPlayableCard);

        // Get all the mPlayableCardList where maxSpeed is not null
        defaultMPlayableCardShouldBeFound("maxSpeed.specified=true");

        // Get all the mPlayableCardList where maxSpeed is null
        defaultMPlayableCardShouldNotBeFound("maxSpeed.specified=false");
    }

    @Test
    @Transactional
    public void getAllMPlayableCardsByMaxSpeedIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mPlayableCardRepository.saveAndFlush(mPlayableCard);

        // Get all the mPlayableCardList where maxSpeed greater than or equals to DEFAULT_MAX_SPEED
        defaultMPlayableCardShouldBeFound("maxSpeed.greaterOrEqualThan=" + DEFAULT_MAX_SPEED);

        // Get all the mPlayableCardList where maxSpeed greater than or equals to UPDATED_MAX_SPEED
        defaultMPlayableCardShouldNotBeFound("maxSpeed.greaterOrEqualThan=" + UPDATED_MAX_SPEED);
    }

    @Test
    @Transactional
    public void getAllMPlayableCardsByMaxSpeedIsLessThanSomething() throws Exception {
        // Initialize the database
        mPlayableCardRepository.saveAndFlush(mPlayableCard);

        // Get all the mPlayableCardList where maxSpeed less than or equals to DEFAULT_MAX_SPEED
        defaultMPlayableCardShouldNotBeFound("maxSpeed.lessThan=" + DEFAULT_MAX_SPEED);

        // Get all the mPlayableCardList where maxSpeed less than or equals to UPDATED_MAX_SPEED
        defaultMPlayableCardShouldBeFound("maxSpeed.lessThan=" + UPDATED_MAX_SPEED);
    }


    @Test
    @Transactional
    public void getAllMPlayableCardsByMaxPowerIsEqualToSomething() throws Exception {
        // Initialize the database
        mPlayableCardRepository.saveAndFlush(mPlayableCard);

        // Get all the mPlayableCardList where maxPower equals to DEFAULT_MAX_POWER
        defaultMPlayableCardShouldBeFound("maxPower.equals=" + DEFAULT_MAX_POWER);

        // Get all the mPlayableCardList where maxPower equals to UPDATED_MAX_POWER
        defaultMPlayableCardShouldNotBeFound("maxPower.equals=" + UPDATED_MAX_POWER);
    }

    @Test
    @Transactional
    public void getAllMPlayableCardsByMaxPowerIsInShouldWork() throws Exception {
        // Initialize the database
        mPlayableCardRepository.saveAndFlush(mPlayableCard);

        // Get all the mPlayableCardList where maxPower in DEFAULT_MAX_POWER or UPDATED_MAX_POWER
        defaultMPlayableCardShouldBeFound("maxPower.in=" + DEFAULT_MAX_POWER + "," + UPDATED_MAX_POWER);

        // Get all the mPlayableCardList where maxPower equals to UPDATED_MAX_POWER
        defaultMPlayableCardShouldNotBeFound("maxPower.in=" + UPDATED_MAX_POWER);
    }

    @Test
    @Transactional
    public void getAllMPlayableCardsByMaxPowerIsNullOrNotNull() throws Exception {
        // Initialize the database
        mPlayableCardRepository.saveAndFlush(mPlayableCard);

        // Get all the mPlayableCardList where maxPower is not null
        defaultMPlayableCardShouldBeFound("maxPower.specified=true");

        // Get all the mPlayableCardList where maxPower is null
        defaultMPlayableCardShouldNotBeFound("maxPower.specified=false");
    }

    @Test
    @Transactional
    public void getAllMPlayableCardsByMaxPowerIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mPlayableCardRepository.saveAndFlush(mPlayableCard);

        // Get all the mPlayableCardList where maxPower greater than or equals to DEFAULT_MAX_POWER
        defaultMPlayableCardShouldBeFound("maxPower.greaterOrEqualThan=" + DEFAULT_MAX_POWER);

        // Get all the mPlayableCardList where maxPower greater than or equals to UPDATED_MAX_POWER
        defaultMPlayableCardShouldNotBeFound("maxPower.greaterOrEqualThan=" + UPDATED_MAX_POWER);
    }

    @Test
    @Transactional
    public void getAllMPlayableCardsByMaxPowerIsLessThanSomething() throws Exception {
        // Initialize the database
        mPlayableCardRepository.saveAndFlush(mPlayableCard);

        // Get all the mPlayableCardList where maxPower less than or equals to DEFAULT_MAX_POWER
        defaultMPlayableCardShouldNotBeFound("maxPower.lessThan=" + DEFAULT_MAX_POWER);

        // Get all the mPlayableCardList where maxPower less than or equals to UPDATED_MAX_POWER
        defaultMPlayableCardShouldBeFound("maxPower.lessThan=" + UPDATED_MAX_POWER);
    }


    @Test
    @Transactional
    public void getAllMPlayableCardsByMaxTechniqueIsEqualToSomething() throws Exception {
        // Initialize the database
        mPlayableCardRepository.saveAndFlush(mPlayableCard);

        // Get all the mPlayableCardList where maxTechnique equals to DEFAULT_MAX_TECHNIQUE
        defaultMPlayableCardShouldBeFound("maxTechnique.equals=" + DEFAULT_MAX_TECHNIQUE);

        // Get all the mPlayableCardList where maxTechnique equals to UPDATED_MAX_TECHNIQUE
        defaultMPlayableCardShouldNotBeFound("maxTechnique.equals=" + UPDATED_MAX_TECHNIQUE);
    }

    @Test
    @Transactional
    public void getAllMPlayableCardsByMaxTechniqueIsInShouldWork() throws Exception {
        // Initialize the database
        mPlayableCardRepository.saveAndFlush(mPlayableCard);

        // Get all the mPlayableCardList where maxTechnique in DEFAULT_MAX_TECHNIQUE or UPDATED_MAX_TECHNIQUE
        defaultMPlayableCardShouldBeFound("maxTechnique.in=" + DEFAULT_MAX_TECHNIQUE + "," + UPDATED_MAX_TECHNIQUE);

        // Get all the mPlayableCardList where maxTechnique equals to UPDATED_MAX_TECHNIQUE
        defaultMPlayableCardShouldNotBeFound("maxTechnique.in=" + UPDATED_MAX_TECHNIQUE);
    }

    @Test
    @Transactional
    public void getAllMPlayableCardsByMaxTechniqueIsNullOrNotNull() throws Exception {
        // Initialize the database
        mPlayableCardRepository.saveAndFlush(mPlayableCard);

        // Get all the mPlayableCardList where maxTechnique is not null
        defaultMPlayableCardShouldBeFound("maxTechnique.specified=true");

        // Get all the mPlayableCardList where maxTechnique is null
        defaultMPlayableCardShouldNotBeFound("maxTechnique.specified=false");
    }

    @Test
    @Transactional
    public void getAllMPlayableCardsByMaxTechniqueIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mPlayableCardRepository.saveAndFlush(mPlayableCard);

        // Get all the mPlayableCardList where maxTechnique greater than or equals to DEFAULT_MAX_TECHNIQUE
        defaultMPlayableCardShouldBeFound("maxTechnique.greaterOrEqualThan=" + DEFAULT_MAX_TECHNIQUE);

        // Get all the mPlayableCardList where maxTechnique greater than or equals to UPDATED_MAX_TECHNIQUE
        defaultMPlayableCardShouldNotBeFound("maxTechnique.greaterOrEqualThan=" + UPDATED_MAX_TECHNIQUE);
    }

    @Test
    @Transactional
    public void getAllMPlayableCardsByMaxTechniqueIsLessThanSomething() throws Exception {
        // Initialize the database
        mPlayableCardRepository.saveAndFlush(mPlayableCard);

        // Get all the mPlayableCardList where maxTechnique less than or equals to DEFAULT_MAX_TECHNIQUE
        defaultMPlayableCardShouldNotBeFound("maxTechnique.lessThan=" + DEFAULT_MAX_TECHNIQUE);

        // Get all the mPlayableCardList where maxTechnique less than or equals to UPDATED_MAX_TECHNIQUE
        defaultMPlayableCardShouldBeFound("maxTechnique.lessThan=" + UPDATED_MAX_TECHNIQUE);
    }


    @Test
    @Transactional
    public void getAllMPlayableCardsByMaxPunchingIsEqualToSomething() throws Exception {
        // Initialize the database
        mPlayableCardRepository.saveAndFlush(mPlayableCard);

        // Get all the mPlayableCardList where maxPunching equals to DEFAULT_MAX_PUNCHING
        defaultMPlayableCardShouldBeFound("maxPunching.equals=" + DEFAULT_MAX_PUNCHING);

        // Get all the mPlayableCardList where maxPunching equals to UPDATED_MAX_PUNCHING
        defaultMPlayableCardShouldNotBeFound("maxPunching.equals=" + UPDATED_MAX_PUNCHING);
    }

    @Test
    @Transactional
    public void getAllMPlayableCardsByMaxPunchingIsInShouldWork() throws Exception {
        // Initialize the database
        mPlayableCardRepository.saveAndFlush(mPlayableCard);

        // Get all the mPlayableCardList where maxPunching in DEFAULT_MAX_PUNCHING or UPDATED_MAX_PUNCHING
        defaultMPlayableCardShouldBeFound("maxPunching.in=" + DEFAULT_MAX_PUNCHING + "," + UPDATED_MAX_PUNCHING);

        // Get all the mPlayableCardList where maxPunching equals to UPDATED_MAX_PUNCHING
        defaultMPlayableCardShouldNotBeFound("maxPunching.in=" + UPDATED_MAX_PUNCHING);
    }

    @Test
    @Transactional
    public void getAllMPlayableCardsByMaxPunchingIsNullOrNotNull() throws Exception {
        // Initialize the database
        mPlayableCardRepository.saveAndFlush(mPlayableCard);

        // Get all the mPlayableCardList where maxPunching is not null
        defaultMPlayableCardShouldBeFound("maxPunching.specified=true");

        // Get all the mPlayableCardList where maxPunching is null
        defaultMPlayableCardShouldNotBeFound("maxPunching.specified=false");
    }

    @Test
    @Transactional
    public void getAllMPlayableCardsByMaxPunchingIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mPlayableCardRepository.saveAndFlush(mPlayableCard);

        // Get all the mPlayableCardList where maxPunching greater than or equals to DEFAULT_MAX_PUNCHING
        defaultMPlayableCardShouldBeFound("maxPunching.greaterOrEqualThan=" + DEFAULT_MAX_PUNCHING);

        // Get all the mPlayableCardList where maxPunching greater than or equals to UPDATED_MAX_PUNCHING
        defaultMPlayableCardShouldNotBeFound("maxPunching.greaterOrEqualThan=" + UPDATED_MAX_PUNCHING);
    }

    @Test
    @Transactional
    public void getAllMPlayableCardsByMaxPunchingIsLessThanSomething() throws Exception {
        // Initialize the database
        mPlayableCardRepository.saveAndFlush(mPlayableCard);

        // Get all the mPlayableCardList where maxPunching less than or equals to DEFAULT_MAX_PUNCHING
        defaultMPlayableCardShouldNotBeFound("maxPunching.lessThan=" + DEFAULT_MAX_PUNCHING);

        // Get all the mPlayableCardList where maxPunching less than or equals to UPDATED_MAX_PUNCHING
        defaultMPlayableCardShouldBeFound("maxPunching.lessThan=" + UPDATED_MAX_PUNCHING);
    }


    @Test
    @Transactional
    public void getAllMPlayableCardsByMaxCatchingIsEqualToSomething() throws Exception {
        // Initialize the database
        mPlayableCardRepository.saveAndFlush(mPlayableCard);

        // Get all the mPlayableCardList where maxCatching equals to DEFAULT_MAX_CATCHING
        defaultMPlayableCardShouldBeFound("maxCatching.equals=" + DEFAULT_MAX_CATCHING);

        // Get all the mPlayableCardList where maxCatching equals to UPDATED_MAX_CATCHING
        defaultMPlayableCardShouldNotBeFound("maxCatching.equals=" + UPDATED_MAX_CATCHING);
    }

    @Test
    @Transactional
    public void getAllMPlayableCardsByMaxCatchingIsInShouldWork() throws Exception {
        // Initialize the database
        mPlayableCardRepository.saveAndFlush(mPlayableCard);

        // Get all the mPlayableCardList where maxCatching in DEFAULT_MAX_CATCHING or UPDATED_MAX_CATCHING
        defaultMPlayableCardShouldBeFound("maxCatching.in=" + DEFAULT_MAX_CATCHING + "," + UPDATED_MAX_CATCHING);

        // Get all the mPlayableCardList where maxCatching equals to UPDATED_MAX_CATCHING
        defaultMPlayableCardShouldNotBeFound("maxCatching.in=" + UPDATED_MAX_CATCHING);
    }

    @Test
    @Transactional
    public void getAllMPlayableCardsByMaxCatchingIsNullOrNotNull() throws Exception {
        // Initialize the database
        mPlayableCardRepository.saveAndFlush(mPlayableCard);

        // Get all the mPlayableCardList where maxCatching is not null
        defaultMPlayableCardShouldBeFound("maxCatching.specified=true");

        // Get all the mPlayableCardList where maxCatching is null
        defaultMPlayableCardShouldNotBeFound("maxCatching.specified=false");
    }

    @Test
    @Transactional
    public void getAllMPlayableCardsByMaxCatchingIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mPlayableCardRepository.saveAndFlush(mPlayableCard);

        // Get all the mPlayableCardList where maxCatching greater than or equals to DEFAULT_MAX_CATCHING
        defaultMPlayableCardShouldBeFound("maxCatching.greaterOrEqualThan=" + DEFAULT_MAX_CATCHING);

        // Get all the mPlayableCardList where maxCatching greater than or equals to UPDATED_MAX_CATCHING
        defaultMPlayableCardShouldNotBeFound("maxCatching.greaterOrEqualThan=" + UPDATED_MAX_CATCHING);
    }

    @Test
    @Transactional
    public void getAllMPlayableCardsByMaxCatchingIsLessThanSomething() throws Exception {
        // Initialize the database
        mPlayableCardRepository.saveAndFlush(mPlayableCard);

        // Get all the mPlayableCardList where maxCatching less than or equals to DEFAULT_MAX_CATCHING
        defaultMPlayableCardShouldNotBeFound("maxCatching.lessThan=" + DEFAULT_MAX_CATCHING);

        // Get all the mPlayableCardList where maxCatching less than or equals to UPDATED_MAX_CATCHING
        defaultMPlayableCardShouldBeFound("maxCatching.lessThan=" + UPDATED_MAX_CATCHING);
    }


    @Test
    @Transactional
    public void getAllMPlayableCardsByMaxPlusDribbleIsEqualToSomething() throws Exception {
        // Initialize the database
        mPlayableCardRepository.saveAndFlush(mPlayableCard);

        // Get all the mPlayableCardList where maxPlusDribble equals to DEFAULT_MAX_PLUS_DRIBBLE
        defaultMPlayableCardShouldBeFound("maxPlusDribble.equals=" + DEFAULT_MAX_PLUS_DRIBBLE);

        // Get all the mPlayableCardList where maxPlusDribble equals to UPDATED_MAX_PLUS_DRIBBLE
        defaultMPlayableCardShouldNotBeFound("maxPlusDribble.equals=" + UPDATED_MAX_PLUS_DRIBBLE);
    }

    @Test
    @Transactional
    public void getAllMPlayableCardsByMaxPlusDribbleIsInShouldWork() throws Exception {
        // Initialize the database
        mPlayableCardRepository.saveAndFlush(mPlayableCard);

        // Get all the mPlayableCardList where maxPlusDribble in DEFAULT_MAX_PLUS_DRIBBLE or UPDATED_MAX_PLUS_DRIBBLE
        defaultMPlayableCardShouldBeFound("maxPlusDribble.in=" + DEFAULT_MAX_PLUS_DRIBBLE + "," + UPDATED_MAX_PLUS_DRIBBLE);

        // Get all the mPlayableCardList where maxPlusDribble equals to UPDATED_MAX_PLUS_DRIBBLE
        defaultMPlayableCardShouldNotBeFound("maxPlusDribble.in=" + UPDATED_MAX_PLUS_DRIBBLE);
    }

    @Test
    @Transactional
    public void getAllMPlayableCardsByMaxPlusDribbleIsNullOrNotNull() throws Exception {
        // Initialize the database
        mPlayableCardRepository.saveAndFlush(mPlayableCard);

        // Get all the mPlayableCardList where maxPlusDribble is not null
        defaultMPlayableCardShouldBeFound("maxPlusDribble.specified=true");

        // Get all the mPlayableCardList where maxPlusDribble is null
        defaultMPlayableCardShouldNotBeFound("maxPlusDribble.specified=false");
    }

    @Test
    @Transactional
    public void getAllMPlayableCardsByMaxPlusDribbleIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mPlayableCardRepository.saveAndFlush(mPlayableCard);

        // Get all the mPlayableCardList where maxPlusDribble greater than or equals to DEFAULT_MAX_PLUS_DRIBBLE
        defaultMPlayableCardShouldBeFound("maxPlusDribble.greaterOrEqualThan=" + DEFAULT_MAX_PLUS_DRIBBLE);

        // Get all the mPlayableCardList where maxPlusDribble greater than or equals to UPDATED_MAX_PLUS_DRIBBLE
        defaultMPlayableCardShouldNotBeFound("maxPlusDribble.greaterOrEqualThan=" + UPDATED_MAX_PLUS_DRIBBLE);
    }

    @Test
    @Transactional
    public void getAllMPlayableCardsByMaxPlusDribbleIsLessThanSomething() throws Exception {
        // Initialize the database
        mPlayableCardRepository.saveAndFlush(mPlayableCard);

        // Get all the mPlayableCardList where maxPlusDribble less than or equals to DEFAULT_MAX_PLUS_DRIBBLE
        defaultMPlayableCardShouldNotBeFound("maxPlusDribble.lessThan=" + DEFAULT_MAX_PLUS_DRIBBLE);

        // Get all the mPlayableCardList where maxPlusDribble less than or equals to UPDATED_MAX_PLUS_DRIBBLE
        defaultMPlayableCardShouldBeFound("maxPlusDribble.lessThan=" + UPDATED_MAX_PLUS_DRIBBLE);
    }


    @Test
    @Transactional
    public void getAllMPlayableCardsByMaxPlusShootIsEqualToSomething() throws Exception {
        // Initialize the database
        mPlayableCardRepository.saveAndFlush(mPlayableCard);

        // Get all the mPlayableCardList where maxPlusShoot equals to DEFAULT_MAX_PLUS_SHOOT
        defaultMPlayableCardShouldBeFound("maxPlusShoot.equals=" + DEFAULT_MAX_PLUS_SHOOT);

        // Get all the mPlayableCardList where maxPlusShoot equals to UPDATED_MAX_PLUS_SHOOT
        defaultMPlayableCardShouldNotBeFound("maxPlusShoot.equals=" + UPDATED_MAX_PLUS_SHOOT);
    }

    @Test
    @Transactional
    public void getAllMPlayableCardsByMaxPlusShootIsInShouldWork() throws Exception {
        // Initialize the database
        mPlayableCardRepository.saveAndFlush(mPlayableCard);

        // Get all the mPlayableCardList where maxPlusShoot in DEFAULT_MAX_PLUS_SHOOT or UPDATED_MAX_PLUS_SHOOT
        defaultMPlayableCardShouldBeFound("maxPlusShoot.in=" + DEFAULT_MAX_PLUS_SHOOT + "," + UPDATED_MAX_PLUS_SHOOT);

        // Get all the mPlayableCardList where maxPlusShoot equals to UPDATED_MAX_PLUS_SHOOT
        defaultMPlayableCardShouldNotBeFound("maxPlusShoot.in=" + UPDATED_MAX_PLUS_SHOOT);
    }

    @Test
    @Transactional
    public void getAllMPlayableCardsByMaxPlusShootIsNullOrNotNull() throws Exception {
        // Initialize the database
        mPlayableCardRepository.saveAndFlush(mPlayableCard);

        // Get all the mPlayableCardList where maxPlusShoot is not null
        defaultMPlayableCardShouldBeFound("maxPlusShoot.specified=true");

        // Get all the mPlayableCardList where maxPlusShoot is null
        defaultMPlayableCardShouldNotBeFound("maxPlusShoot.specified=false");
    }

    @Test
    @Transactional
    public void getAllMPlayableCardsByMaxPlusShootIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mPlayableCardRepository.saveAndFlush(mPlayableCard);

        // Get all the mPlayableCardList where maxPlusShoot greater than or equals to DEFAULT_MAX_PLUS_SHOOT
        defaultMPlayableCardShouldBeFound("maxPlusShoot.greaterOrEqualThan=" + DEFAULT_MAX_PLUS_SHOOT);

        // Get all the mPlayableCardList where maxPlusShoot greater than or equals to UPDATED_MAX_PLUS_SHOOT
        defaultMPlayableCardShouldNotBeFound("maxPlusShoot.greaterOrEqualThan=" + UPDATED_MAX_PLUS_SHOOT);
    }

    @Test
    @Transactional
    public void getAllMPlayableCardsByMaxPlusShootIsLessThanSomething() throws Exception {
        // Initialize the database
        mPlayableCardRepository.saveAndFlush(mPlayableCard);

        // Get all the mPlayableCardList where maxPlusShoot less than or equals to DEFAULT_MAX_PLUS_SHOOT
        defaultMPlayableCardShouldNotBeFound("maxPlusShoot.lessThan=" + DEFAULT_MAX_PLUS_SHOOT);

        // Get all the mPlayableCardList where maxPlusShoot less than or equals to UPDATED_MAX_PLUS_SHOOT
        defaultMPlayableCardShouldBeFound("maxPlusShoot.lessThan=" + UPDATED_MAX_PLUS_SHOOT);
    }


    @Test
    @Transactional
    public void getAllMPlayableCardsByMaxPlusPassIsEqualToSomething() throws Exception {
        // Initialize the database
        mPlayableCardRepository.saveAndFlush(mPlayableCard);

        // Get all the mPlayableCardList where maxPlusPass equals to DEFAULT_MAX_PLUS_PASS
        defaultMPlayableCardShouldBeFound("maxPlusPass.equals=" + DEFAULT_MAX_PLUS_PASS);

        // Get all the mPlayableCardList where maxPlusPass equals to UPDATED_MAX_PLUS_PASS
        defaultMPlayableCardShouldNotBeFound("maxPlusPass.equals=" + UPDATED_MAX_PLUS_PASS);
    }

    @Test
    @Transactional
    public void getAllMPlayableCardsByMaxPlusPassIsInShouldWork() throws Exception {
        // Initialize the database
        mPlayableCardRepository.saveAndFlush(mPlayableCard);

        // Get all the mPlayableCardList where maxPlusPass in DEFAULT_MAX_PLUS_PASS or UPDATED_MAX_PLUS_PASS
        defaultMPlayableCardShouldBeFound("maxPlusPass.in=" + DEFAULT_MAX_PLUS_PASS + "," + UPDATED_MAX_PLUS_PASS);

        // Get all the mPlayableCardList where maxPlusPass equals to UPDATED_MAX_PLUS_PASS
        defaultMPlayableCardShouldNotBeFound("maxPlusPass.in=" + UPDATED_MAX_PLUS_PASS);
    }

    @Test
    @Transactional
    public void getAllMPlayableCardsByMaxPlusPassIsNullOrNotNull() throws Exception {
        // Initialize the database
        mPlayableCardRepository.saveAndFlush(mPlayableCard);

        // Get all the mPlayableCardList where maxPlusPass is not null
        defaultMPlayableCardShouldBeFound("maxPlusPass.specified=true");

        // Get all the mPlayableCardList where maxPlusPass is null
        defaultMPlayableCardShouldNotBeFound("maxPlusPass.specified=false");
    }

    @Test
    @Transactional
    public void getAllMPlayableCardsByMaxPlusPassIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mPlayableCardRepository.saveAndFlush(mPlayableCard);

        // Get all the mPlayableCardList where maxPlusPass greater than or equals to DEFAULT_MAX_PLUS_PASS
        defaultMPlayableCardShouldBeFound("maxPlusPass.greaterOrEqualThan=" + DEFAULT_MAX_PLUS_PASS);

        // Get all the mPlayableCardList where maxPlusPass greater than or equals to UPDATED_MAX_PLUS_PASS
        defaultMPlayableCardShouldNotBeFound("maxPlusPass.greaterOrEqualThan=" + UPDATED_MAX_PLUS_PASS);
    }

    @Test
    @Transactional
    public void getAllMPlayableCardsByMaxPlusPassIsLessThanSomething() throws Exception {
        // Initialize the database
        mPlayableCardRepository.saveAndFlush(mPlayableCard);

        // Get all the mPlayableCardList where maxPlusPass less than or equals to DEFAULT_MAX_PLUS_PASS
        defaultMPlayableCardShouldNotBeFound("maxPlusPass.lessThan=" + DEFAULT_MAX_PLUS_PASS);

        // Get all the mPlayableCardList where maxPlusPass less than or equals to UPDATED_MAX_PLUS_PASS
        defaultMPlayableCardShouldBeFound("maxPlusPass.lessThan=" + UPDATED_MAX_PLUS_PASS);
    }


    @Test
    @Transactional
    public void getAllMPlayableCardsByMaxPlusTackleIsEqualToSomething() throws Exception {
        // Initialize the database
        mPlayableCardRepository.saveAndFlush(mPlayableCard);

        // Get all the mPlayableCardList where maxPlusTackle equals to DEFAULT_MAX_PLUS_TACKLE
        defaultMPlayableCardShouldBeFound("maxPlusTackle.equals=" + DEFAULT_MAX_PLUS_TACKLE);

        // Get all the mPlayableCardList where maxPlusTackle equals to UPDATED_MAX_PLUS_TACKLE
        defaultMPlayableCardShouldNotBeFound("maxPlusTackle.equals=" + UPDATED_MAX_PLUS_TACKLE);
    }

    @Test
    @Transactional
    public void getAllMPlayableCardsByMaxPlusTackleIsInShouldWork() throws Exception {
        // Initialize the database
        mPlayableCardRepository.saveAndFlush(mPlayableCard);

        // Get all the mPlayableCardList where maxPlusTackle in DEFAULT_MAX_PLUS_TACKLE or UPDATED_MAX_PLUS_TACKLE
        defaultMPlayableCardShouldBeFound("maxPlusTackle.in=" + DEFAULT_MAX_PLUS_TACKLE + "," + UPDATED_MAX_PLUS_TACKLE);

        // Get all the mPlayableCardList where maxPlusTackle equals to UPDATED_MAX_PLUS_TACKLE
        defaultMPlayableCardShouldNotBeFound("maxPlusTackle.in=" + UPDATED_MAX_PLUS_TACKLE);
    }

    @Test
    @Transactional
    public void getAllMPlayableCardsByMaxPlusTackleIsNullOrNotNull() throws Exception {
        // Initialize the database
        mPlayableCardRepository.saveAndFlush(mPlayableCard);

        // Get all the mPlayableCardList where maxPlusTackle is not null
        defaultMPlayableCardShouldBeFound("maxPlusTackle.specified=true");

        // Get all the mPlayableCardList where maxPlusTackle is null
        defaultMPlayableCardShouldNotBeFound("maxPlusTackle.specified=false");
    }

    @Test
    @Transactional
    public void getAllMPlayableCardsByMaxPlusTackleIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mPlayableCardRepository.saveAndFlush(mPlayableCard);

        // Get all the mPlayableCardList where maxPlusTackle greater than or equals to DEFAULT_MAX_PLUS_TACKLE
        defaultMPlayableCardShouldBeFound("maxPlusTackle.greaterOrEqualThan=" + DEFAULT_MAX_PLUS_TACKLE);

        // Get all the mPlayableCardList where maxPlusTackle greater than or equals to UPDATED_MAX_PLUS_TACKLE
        defaultMPlayableCardShouldNotBeFound("maxPlusTackle.greaterOrEqualThan=" + UPDATED_MAX_PLUS_TACKLE);
    }

    @Test
    @Transactional
    public void getAllMPlayableCardsByMaxPlusTackleIsLessThanSomething() throws Exception {
        // Initialize the database
        mPlayableCardRepository.saveAndFlush(mPlayableCard);

        // Get all the mPlayableCardList where maxPlusTackle less than or equals to DEFAULT_MAX_PLUS_TACKLE
        defaultMPlayableCardShouldNotBeFound("maxPlusTackle.lessThan=" + DEFAULT_MAX_PLUS_TACKLE);

        // Get all the mPlayableCardList where maxPlusTackle less than or equals to UPDATED_MAX_PLUS_TACKLE
        defaultMPlayableCardShouldBeFound("maxPlusTackle.lessThan=" + UPDATED_MAX_PLUS_TACKLE);
    }


    @Test
    @Transactional
    public void getAllMPlayableCardsByMaxPlusBlockIsEqualToSomething() throws Exception {
        // Initialize the database
        mPlayableCardRepository.saveAndFlush(mPlayableCard);

        // Get all the mPlayableCardList where maxPlusBlock equals to DEFAULT_MAX_PLUS_BLOCK
        defaultMPlayableCardShouldBeFound("maxPlusBlock.equals=" + DEFAULT_MAX_PLUS_BLOCK);

        // Get all the mPlayableCardList where maxPlusBlock equals to UPDATED_MAX_PLUS_BLOCK
        defaultMPlayableCardShouldNotBeFound("maxPlusBlock.equals=" + UPDATED_MAX_PLUS_BLOCK);
    }

    @Test
    @Transactional
    public void getAllMPlayableCardsByMaxPlusBlockIsInShouldWork() throws Exception {
        // Initialize the database
        mPlayableCardRepository.saveAndFlush(mPlayableCard);

        // Get all the mPlayableCardList where maxPlusBlock in DEFAULT_MAX_PLUS_BLOCK or UPDATED_MAX_PLUS_BLOCK
        defaultMPlayableCardShouldBeFound("maxPlusBlock.in=" + DEFAULT_MAX_PLUS_BLOCK + "," + UPDATED_MAX_PLUS_BLOCK);

        // Get all the mPlayableCardList where maxPlusBlock equals to UPDATED_MAX_PLUS_BLOCK
        defaultMPlayableCardShouldNotBeFound("maxPlusBlock.in=" + UPDATED_MAX_PLUS_BLOCK);
    }

    @Test
    @Transactional
    public void getAllMPlayableCardsByMaxPlusBlockIsNullOrNotNull() throws Exception {
        // Initialize the database
        mPlayableCardRepository.saveAndFlush(mPlayableCard);

        // Get all the mPlayableCardList where maxPlusBlock is not null
        defaultMPlayableCardShouldBeFound("maxPlusBlock.specified=true");

        // Get all the mPlayableCardList where maxPlusBlock is null
        defaultMPlayableCardShouldNotBeFound("maxPlusBlock.specified=false");
    }

    @Test
    @Transactional
    public void getAllMPlayableCardsByMaxPlusBlockIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mPlayableCardRepository.saveAndFlush(mPlayableCard);

        // Get all the mPlayableCardList where maxPlusBlock greater than or equals to DEFAULT_MAX_PLUS_BLOCK
        defaultMPlayableCardShouldBeFound("maxPlusBlock.greaterOrEqualThan=" + DEFAULT_MAX_PLUS_BLOCK);

        // Get all the mPlayableCardList where maxPlusBlock greater than or equals to UPDATED_MAX_PLUS_BLOCK
        defaultMPlayableCardShouldNotBeFound("maxPlusBlock.greaterOrEqualThan=" + UPDATED_MAX_PLUS_BLOCK);
    }

    @Test
    @Transactional
    public void getAllMPlayableCardsByMaxPlusBlockIsLessThanSomething() throws Exception {
        // Initialize the database
        mPlayableCardRepository.saveAndFlush(mPlayableCard);

        // Get all the mPlayableCardList where maxPlusBlock less than or equals to DEFAULT_MAX_PLUS_BLOCK
        defaultMPlayableCardShouldNotBeFound("maxPlusBlock.lessThan=" + DEFAULT_MAX_PLUS_BLOCK);

        // Get all the mPlayableCardList where maxPlusBlock less than or equals to UPDATED_MAX_PLUS_BLOCK
        defaultMPlayableCardShouldBeFound("maxPlusBlock.lessThan=" + UPDATED_MAX_PLUS_BLOCK);
    }


    @Test
    @Transactional
    public void getAllMPlayableCardsByMaxPlusInterceptIsEqualToSomething() throws Exception {
        // Initialize the database
        mPlayableCardRepository.saveAndFlush(mPlayableCard);

        // Get all the mPlayableCardList where maxPlusIntercept equals to DEFAULT_MAX_PLUS_INTERCEPT
        defaultMPlayableCardShouldBeFound("maxPlusIntercept.equals=" + DEFAULT_MAX_PLUS_INTERCEPT);

        // Get all the mPlayableCardList where maxPlusIntercept equals to UPDATED_MAX_PLUS_INTERCEPT
        defaultMPlayableCardShouldNotBeFound("maxPlusIntercept.equals=" + UPDATED_MAX_PLUS_INTERCEPT);
    }

    @Test
    @Transactional
    public void getAllMPlayableCardsByMaxPlusInterceptIsInShouldWork() throws Exception {
        // Initialize the database
        mPlayableCardRepository.saveAndFlush(mPlayableCard);

        // Get all the mPlayableCardList where maxPlusIntercept in DEFAULT_MAX_PLUS_INTERCEPT or UPDATED_MAX_PLUS_INTERCEPT
        defaultMPlayableCardShouldBeFound("maxPlusIntercept.in=" + DEFAULT_MAX_PLUS_INTERCEPT + "," + UPDATED_MAX_PLUS_INTERCEPT);

        // Get all the mPlayableCardList where maxPlusIntercept equals to UPDATED_MAX_PLUS_INTERCEPT
        defaultMPlayableCardShouldNotBeFound("maxPlusIntercept.in=" + UPDATED_MAX_PLUS_INTERCEPT);
    }

    @Test
    @Transactional
    public void getAllMPlayableCardsByMaxPlusInterceptIsNullOrNotNull() throws Exception {
        // Initialize the database
        mPlayableCardRepository.saveAndFlush(mPlayableCard);

        // Get all the mPlayableCardList where maxPlusIntercept is not null
        defaultMPlayableCardShouldBeFound("maxPlusIntercept.specified=true");

        // Get all the mPlayableCardList where maxPlusIntercept is null
        defaultMPlayableCardShouldNotBeFound("maxPlusIntercept.specified=false");
    }

    @Test
    @Transactional
    public void getAllMPlayableCardsByMaxPlusInterceptIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mPlayableCardRepository.saveAndFlush(mPlayableCard);

        // Get all the mPlayableCardList where maxPlusIntercept greater than or equals to DEFAULT_MAX_PLUS_INTERCEPT
        defaultMPlayableCardShouldBeFound("maxPlusIntercept.greaterOrEqualThan=" + DEFAULT_MAX_PLUS_INTERCEPT);

        // Get all the mPlayableCardList where maxPlusIntercept greater than or equals to UPDATED_MAX_PLUS_INTERCEPT
        defaultMPlayableCardShouldNotBeFound("maxPlusIntercept.greaterOrEqualThan=" + UPDATED_MAX_PLUS_INTERCEPT);
    }

    @Test
    @Transactional
    public void getAllMPlayableCardsByMaxPlusInterceptIsLessThanSomething() throws Exception {
        // Initialize the database
        mPlayableCardRepository.saveAndFlush(mPlayableCard);

        // Get all the mPlayableCardList where maxPlusIntercept less than or equals to DEFAULT_MAX_PLUS_INTERCEPT
        defaultMPlayableCardShouldNotBeFound("maxPlusIntercept.lessThan=" + DEFAULT_MAX_PLUS_INTERCEPT);

        // Get all the mPlayableCardList where maxPlusIntercept less than or equals to UPDATED_MAX_PLUS_INTERCEPT
        defaultMPlayableCardShouldBeFound("maxPlusIntercept.lessThan=" + UPDATED_MAX_PLUS_INTERCEPT);
    }


    @Test
    @Transactional
    public void getAllMPlayableCardsByMaxPlusSpeedIsEqualToSomething() throws Exception {
        // Initialize the database
        mPlayableCardRepository.saveAndFlush(mPlayableCard);

        // Get all the mPlayableCardList where maxPlusSpeed equals to DEFAULT_MAX_PLUS_SPEED
        defaultMPlayableCardShouldBeFound("maxPlusSpeed.equals=" + DEFAULT_MAX_PLUS_SPEED);

        // Get all the mPlayableCardList where maxPlusSpeed equals to UPDATED_MAX_PLUS_SPEED
        defaultMPlayableCardShouldNotBeFound("maxPlusSpeed.equals=" + UPDATED_MAX_PLUS_SPEED);
    }

    @Test
    @Transactional
    public void getAllMPlayableCardsByMaxPlusSpeedIsInShouldWork() throws Exception {
        // Initialize the database
        mPlayableCardRepository.saveAndFlush(mPlayableCard);

        // Get all the mPlayableCardList where maxPlusSpeed in DEFAULT_MAX_PLUS_SPEED or UPDATED_MAX_PLUS_SPEED
        defaultMPlayableCardShouldBeFound("maxPlusSpeed.in=" + DEFAULT_MAX_PLUS_SPEED + "," + UPDATED_MAX_PLUS_SPEED);

        // Get all the mPlayableCardList where maxPlusSpeed equals to UPDATED_MAX_PLUS_SPEED
        defaultMPlayableCardShouldNotBeFound("maxPlusSpeed.in=" + UPDATED_MAX_PLUS_SPEED);
    }

    @Test
    @Transactional
    public void getAllMPlayableCardsByMaxPlusSpeedIsNullOrNotNull() throws Exception {
        // Initialize the database
        mPlayableCardRepository.saveAndFlush(mPlayableCard);

        // Get all the mPlayableCardList where maxPlusSpeed is not null
        defaultMPlayableCardShouldBeFound("maxPlusSpeed.specified=true");

        // Get all the mPlayableCardList where maxPlusSpeed is null
        defaultMPlayableCardShouldNotBeFound("maxPlusSpeed.specified=false");
    }

    @Test
    @Transactional
    public void getAllMPlayableCardsByMaxPlusSpeedIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mPlayableCardRepository.saveAndFlush(mPlayableCard);

        // Get all the mPlayableCardList where maxPlusSpeed greater than or equals to DEFAULT_MAX_PLUS_SPEED
        defaultMPlayableCardShouldBeFound("maxPlusSpeed.greaterOrEqualThan=" + DEFAULT_MAX_PLUS_SPEED);

        // Get all the mPlayableCardList where maxPlusSpeed greater than or equals to UPDATED_MAX_PLUS_SPEED
        defaultMPlayableCardShouldNotBeFound("maxPlusSpeed.greaterOrEqualThan=" + UPDATED_MAX_PLUS_SPEED);
    }

    @Test
    @Transactional
    public void getAllMPlayableCardsByMaxPlusSpeedIsLessThanSomething() throws Exception {
        // Initialize the database
        mPlayableCardRepository.saveAndFlush(mPlayableCard);

        // Get all the mPlayableCardList where maxPlusSpeed less than or equals to DEFAULT_MAX_PLUS_SPEED
        defaultMPlayableCardShouldNotBeFound("maxPlusSpeed.lessThan=" + DEFAULT_MAX_PLUS_SPEED);

        // Get all the mPlayableCardList where maxPlusSpeed less than or equals to UPDATED_MAX_PLUS_SPEED
        defaultMPlayableCardShouldBeFound("maxPlusSpeed.lessThan=" + UPDATED_MAX_PLUS_SPEED);
    }


    @Test
    @Transactional
    public void getAllMPlayableCardsByMaxPlusPowerIsEqualToSomething() throws Exception {
        // Initialize the database
        mPlayableCardRepository.saveAndFlush(mPlayableCard);

        // Get all the mPlayableCardList where maxPlusPower equals to DEFAULT_MAX_PLUS_POWER
        defaultMPlayableCardShouldBeFound("maxPlusPower.equals=" + DEFAULT_MAX_PLUS_POWER);

        // Get all the mPlayableCardList where maxPlusPower equals to UPDATED_MAX_PLUS_POWER
        defaultMPlayableCardShouldNotBeFound("maxPlusPower.equals=" + UPDATED_MAX_PLUS_POWER);
    }

    @Test
    @Transactional
    public void getAllMPlayableCardsByMaxPlusPowerIsInShouldWork() throws Exception {
        // Initialize the database
        mPlayableCardRepository.saveAndFlush(mPlayableCard);

        // Get all the mPlayableCardList where maxPlusPower in DEFAULT_MAX_PLUS_POWER or UPDATED_MAX_PLUS_POWER
        defaultMPlayableCardShouldBeFound("maxPlusPower.in=" + DEFAULT_MAX_PLUS_POWER + "," + UPDATED_MAX_PLUS_POWER);

        // Get all the mPlayableCardList where maxPlusPower equals to UPDATED_MAX_PLUS_POWER
        defaultMPlayableCardShouldNotBeFound("maxPlusPower.in=" + UPDATED_MAX_PLUS_POWER);
    }

    @Test
    @Transactional
    public void getAllMPlayableCardsByMaxPlusPowerIsNullOrNotNull() throws Exception {
        // Initialize the database
        mPlayableCardRepository.saveAndFlush(mPlayableCard);

        // Get all the mPlayableCardList where maxPlusPower is not null
        defaultMPlayableCardShouldBeFound("maxPlusPower.specified=true");

        // Get all the mPlayableCardList where maxPlusPower is null
        defaultMPlayableCardShouldNotBeFound("maxPlusPower.specified=false");
    }

    @Test
    @Transactional
    public void getAllMPlayableCardsByMaxPlusPowerIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mPlayableCardRepository.saveAndFlush(mPlayableCard);

        // Get all the mPlayableCardList where maxPlusPower greater than or equals to DEFAULT_MAX_PLUS_POWER
        defaultMPlayableCardShouldBeFound("maxPlusPower.greaterOrEqualThan=" + DEFAULT_MAX_PLUS_POWER);

        // Get all the mPlayableCardList where maxPlusPower greater than or equals to UPDATED_MAX_PLUS_POWER
        defaultMPlayableCardShouldNotBeFound("maxPlusPower.greaterOrEqualThan=" + UPDATED_MAX_PLUS_POWER);
    }

    @Test
    @Transactional
    public void getAllMPlayableCardsByMaxPlusPowerIsLessThanSomething() throws Exception {
        // Initialize the database
        mPlayableCardRepository.saveAndFlush(mPlayableCard);

        // Get all the mPlayableCardList where maxPlusPower less than or equals to DEFAULT_MAX_PLUS_POWER
        defaultMPlayableCardShouldNotBeFound("maxPlusPower.lessThan=" + DEFAULT_MAX_PLUS_POWER);

        // Get all the mPlayableCardList where maxPlusPower less than or equals to UPDATED_MAX_PLUS_POWER
        defaultMPlayableCardShouldBeFound("maxPlusPower.lessThan=" + UPDATED_MAX_PLUS_POWER);
    }


    @Test
    @Transactional
    public void getAllMPlayableCardsByMaxPlusTechniqueIsEqualToSomething() throws Exception {
        // Initialize the database
        mPlayableCardRepository.saveAndFlush(mPlayableCard);

        // Get all the mPlayableCardList where maxPlusTechnique equals to DEFAULT_MAX_PLUS_TECHNIQUE
        defaultMPlayableCardShouldBeFound("maxPlusTechnique.equals=" + DEFAULT_MAX_PLUS_TECHNIQUE);

        // Get all the mPlayableCardList where maxPlusTechnique equals to UPDATED_MAX_PLUS_TECHNIQUE
        defaultMPlayableCardShouldNotBeFound("maxPlusTechnique.equals=" + UPDATED_MAX_PLUS_TECHNIQUE);
    }

    @Test
    @Transactional
    public void getAllMPlayableCardsByMaxPlusTechniqueIsInShouldWork() throws Exception {
        // Initialize the database
        mPlayableCardRepository.saveAndFlush(mPlayableCard);

        // Get all the mPlayableCardList where maxPlusTechnique in DEFAULT_MAX_PLUS_TECHNIQUE or UPDATED_MAX_PLUS_TECHNIQUE
        defaultMPlayableCardShouldBeFound("maxPlusTechnique.in=" + DEFAULT_MAX_PLUS_TECHNIQUE + "," + UPDATED_MAX_PLUS_TECHNIQUE);

        // Get all the mPlayableCardList where maxPlusTechnique equals to UPDATED_MAX_PLUS_TECHNIQUE
        defaultMPlayableCardShouldNotBeFound("maxPlusTechnique.in=" + UPDATED_MAX_PLUS_TECHNIQUE);
    }

    @Test
    @Transactional
    public void getAllMPlayableCardsByMaxPlusTechniqueIsNullOrNotNull() throws Exception {
        // Initialize the database
        mPlayableCardRepository.saveAndFlush(mPlayableCard);

        // Get all the mPlayableCardList where maxPlusTechnique is not null
        defaultMPlayableCardShouldBeFound("maxPlusTechnique.specified=true");

        // Get all the mPlayableCardList where maxPlusTechnique is null
        defaultMPlayableCardShouldNotBeFound("maxPlusTechnique.specified=false");
    }

    @Test
    @Transactional
    public void getAllMPlayableCardsByMaxPlusTechniqueIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mPlayableCardRepository.saveAndFlush(mPlayableCard);

        // Get all the mPlayableCardList where maxPlusTechnique greater than or equals to DEFAULT_MAX_PLUS_TECHNIQUE
        defaultMPlayableCardShouldBeFound("maxPlusTechnique.greaterOrEqualThan=" + DEFAULT_MAX_PLUS_TECHNIQUE);

        // Get all the mPlayableCardList where maxPlusTechnique greater than or equals to UPDATED_MAX_PLUS_TECHNIQUE
        defaultMPlayableCardShouldNotBeFound("maxPlusTechnique.greaterOrEqualThan=" + UPDATED_MAX_PLUS_TECHNIQUE);
    }

    @Test
    @Transactional
    public void getAllMPlayableCardsByMaxPlusTechniqueIsLessThanSomething() throws Exception {
        // Initialize the database
        mPlayableCardRepository.saveAndFlush(mPlayableCard);

        // Get all the mPlayableCardList where maxPlusTechnique less than or equals to DEFAULT_MAX_PLUS_TECHNIQUE
        defaultMPlayableCardShouldNotBeFound("maxPlusTechnique.lessThan=" + DEFAULT_MAX_PLUS_TECHNIQUE);

        // Get all the mPlayableCardList where maxPlusTechnique less than or equals to UPDATED_MAX_PLUS_TECHNIQUE
        defaultMPlayableCardShouldBeFound("maxPlusTechnique.lessThan=" + UPDATED_MAX_PLUS_TECHNIQUE);
    }


    @Test
    @Transactional
    public void getAllMPlayableCardsByMaxPlusPunchingIsEqualToSomething() throws Exception {
        // Initialize the database
        mPlayableCardRepository.saveAndFlush(mPlayableCard);

        // Get all the mPlayableCardList where maxPlusPunching equals to DEFAULT_MAX_PLUS_PUNCHING
        defaultMPlayableCardShouldBeFound("maxPlusPunching.equals=" + DEFAULT_MAX_PLUS_PUNCHING);

        // Get all the mPlayableCardList where maxPlusPunching equals to UPDATED_MAX_PLUS_PUNCHING
        defaultMPlayableCardShouldNotBeFound("maxPlusPunching.equals=" + UPDATED_MAX_PLUS_PUNCHING);
    }

    @Test
    @Transactional
    public void getAllMPlayableCardsByMaxPlusPunchingIsInShouldWork() throws Exception {
        // Initialize the database
        mPlayableCardRepository.saveAndFlush(mPlayableCard);

        // Get all the mPlayableCardList where maxPlusPunching in DEFAULT_MAX_PLUS_PUNCHING or UPDATED_MAX_PLUS_PUNCHING
        defaultMPlayableCardShouldBeFound("maxPlusPunching.in=" + DEFAULT_MAX_PLUS_PUNCHING + "," + UPDATED_MAX_PLUS_PUNCHING);

        // Get all the mPlayableCardList where maxPlusPunching equals to UPDATED_MAX_PLUS_PUNCHING
        defaultMPlayableCardShouldNotBeFound("maxPlusPunching.in=" + UPDATED_MAX_PLUS_PUNCHING);
    }

    @Test
    @Transactional
    public void getAllMPlayableCardsByMaxPlusPunchingIsNullOrNotNull() throws Exception {
        // Initialize the database
        mPlayableCardRepository.saveAndFlush(mPlayableCard);

        // Get all the mPlayableCardList where maxPlusPunching is not null
        defaultMPlayableCardShouldBeFound("maxPlusPunching.specified=true");

        // Get all the mPlayableCardList where maxPlusPunching is null
        defaultMPlayableCardShouldNotBeFound("maxPlusPunching.specified=false");
    }

    @Test
    @Transactional
    public void getAllMPlayableCardsByMaxPlusPunchingIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mPlayableCardRepository.saveAndFlush(mPlayableCard);

        // Get all the mPlayableCardList where maxPlusPunching greater than or equals to DEFAULT_MAX_PLUS_PUNCHING
        defaultMPlayableCardShouldBeFound("maxPlusPunching.greaterOrEqualThan=" + DEFAULT_MAX_PLUS_PUNCHING);

        // Get all the mPlayableCardList where maxPlusPunching greater than or equals to UPDATED_MAX_PLUS_PUNCHING
        defaultMPlayableCardShouldNotBeFound("maxPlusPunching.greaterOrEqualThan=" + UPDATED_MAX_PLUS_PUNCHING);
    }

    @Test
    @Transactional
    public void getAllMPlayableCardsByMaxPlusPunchingIsLessThanSomething() throws Exception {
        // Initialize the database
        mPlayableCardRepository.saveAndFlush(mPlayableCard);

        // Get all the mPlayableCardList where maxPlusPunching less than or equals to DEFAULT_MAX_PLUS_PUNCHING
        defaultMPlayableCardShouldNotBeFound("maxPlusPunching.lessThan=" + DEFAULT_MAX_PLUS_PUNCHING);

        // Get all the mPlayableCardList where maxPlusPunching less than or equals to UPDATED_MAX_PLUS_PUNCHING
        defaultMPlayableCardShouldBeFound("maxPlusPunching.lessThan=" + UPDATED_MAX_PLUS_PUNCHING);
    }


    @Test
    @Transactional
    public void getAllMPlayableCardsByMaxPlusCatchingIsEqualToSomething() throws Exception {
        // Initialize the database
        mPlayableCardRepository.saveAndFlush(mPlayableCard);

        // Get all the mPlayableCardList where maxPlusCatching equals to DEFAULT_MAX_PLUS_CATCHING
        defaultMPlayableCardShouldBeFound("maxPlusCatching.equals=" + DEFAULT_MAX_PLUS_CATCHING);

        // Get all the mPlayableCardList where maxPlusCatching equals to UPDATED_MAX_PLUS_CATCHING
        defaultMPlayableCardShouldNotBeFound("maxPlusCatching.equals=" + UPDATED_MAX_PLUS_CATCHING);
    }

    @Test
    @Transactional
    public void getAllMPlayableCardsByMaxPlusCatchingIsInShouldWork() throws Exception {
        // Initialize the database
        mPlayableCardRepository.saveAndFlush(mPlayableCard);

        // Get all the mPlayableCardList where maxPlusCatching in DEFAULT_MAX_PLUS_CATCHING or UPDATED_MAX_PLUS_CATCHING
        defaultMPlayableCardShouldBeFound("maxPlusCatching.in=" + DEFAULT_MAX_PLUS_CATCHING + "," + UPDATED_MAX_PLUS_CATCHING);

        // Get all the mPlayableCardList where maxPlusCatching equals to UPDATED_MAX_PLUS_CATCHING
        defaultMPlayableCardShouldNotBeFound("maxPlusCatching.in=" + UPDATED_MAX_PLUS_CATCHING);
    }

    @Test
    @Transactional
    public void getAllMPlayableCardsByMaxPlusCatchingIsNullOrNotNull() throws Exception {
        // Initialize the database
        mPlayableCardRepository.saveAndFlush(mPlayableCard);

        // Get all the mPlayableCardList where maxPlusCatching is not null
        defaultMPlayableCardShouldBeFound("maxPlusCatching.specified=true");

        // Get all the mPlayableCardList where maxPlusCatching is null
        defaultMPlayableCardShouldNotBeFound("maxPlusCatching.specified=false");
    }

    @Test
    @Transactional
    public void getAllMPlayableCardsByMaxPlusCatchingIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mPlayableCardRepository.saveAndFlush(mPlayableCard);

        // Get all the mPlayableCardList where maxPlusCatching greater than or equals to DEFAULT_MAX_PLUS_CATCHING
        defaultMPlayableCardShouldBeFound("maxPlusCatching.greaterOrEqualThan=" + DEFAULT_MAX_PLUS_CATCHING);

        // Get all the mPlayableCardList where maxPlusCatching greater than or equals to UPDATED_MAX_PLUS_CATCHING
        defaultMPlayableCardShouldNotBeFound("maxPlusCatching.greaterOrEqualThan=" + UPDATED_MAX_PLUS_CATCHING);
    }

    @Test
    @Transactional
    public void getAllMPlayableCardsByMaxPlusCatchingIsLessThanSomething() throws Exception {
        // Initialize the database
        mPlayableCardRepository.saveAndFlush(mPlayableCard);

        // Get all the mPlayableCardList where maxPlusCatching less than or equals to DEFAULT_MAX_PLUS_CATCHING
        defaultMPlayableCardShouldNotBeFound("maxPlusCatching.lessThan=" + DEFAULT_MAX_PLUS_CATCHING);

        // Get all the mPlayableCardList where maxPlusCatching less than or equals to UPDATED_MAX_PLUS_CATCHING
        defaultMPlayableCardShouldBeFound("maxPlusCatching.lessThan=" + UPDATED_MAX_PLUS_CATCHING);
    }


    @Test
    @Transactional
    public void getAllMPlayableCardsByHighBallBonusIsEqualToSomething() throws Exception {
        // Initialize the database
        mPlayableCardRepository.saveAndFlush(mPlayableCard);

        // Get all the mPlayableCardList where highBallBonus equals to DEFAULT_HIGH_BALL_BONUS
        defaultMPlayableCardShouldBeFound("highBallBonus.equals=" + DEFAULT_HIGH_BALL_BONUS);

        // Get all the mPlayableCardList where highBallBonus equals to UPDATED_HIGH_BALL_BONUS
        defaultMPlayableCardShouldNotBeFound("highBallBonus.equals=" + UPDATED_HIGH_BALL_BONUS);
    }

    @Test
    @Transactional
    public void getAllMPlayableCardsByHighBallBonusIsInShouldWork() throws Exception {
        // Initialize the database
        mPlayableCardRepository.saveAndFlush(mPlayableCard);

        // Get all the mPlayableCardList where highBallBonus in DEFAULT_HIGH_BALL_BONUS or UPDATED_HIGH_BALL_BONUS
        defaultMPlayableCardShouldBeFound("highBallBonus.in=" + DEFAULT_HIGH_BALL_BONUS + "," + UPDATED_HIGH_BALL_BONUS);

        // Get all the mPlayableCardList where highBallBonus equals to UPDATED_HIGH_BALL_BONUS
        defaultMPlayableCardShouldNotBeFound("highBallBonus.in=" + UPDATED_HIGH_BALL_BONUS);
    }

    @Test
    @Transactional
    public void getAllMPlayableCardsByHighBallBonusIsNullOrNotNull() throws Exception {
        // Initialize the database
        mPlayableCardRepository.saveAndFlush(mPlayableCard);

        // Get all the mPlayableCardList where highBallBonus is not null
        defaultMPlayableCardShouldBeFound("highBallBonus.specified=true");

        // Get all the mPlayableCardList where highBallBonus is null
        defaultMPlayableCardShouldNotBeFound("highBallBonus.specified=false");
    }

    @Test
    @Transactional
    public void getAllMPlayableCardsByHighBallBonusIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mPlayableCardRepository.saveAndFlush(mPlayableCard);

        // Get all the mPlayableCardList where highBallBonus greater than or equals to DEFAULT_HIGH_BALL_BONUS
        defaultMPlayableCardShouldBeFound("highBallBonus.greaterOrEqualThan=" + DEFAULT_HIGH_BALL_BONUS);

        // Get all the mPlayableCardList where highBallBonus greater than or equals to UPDATED_HIGH_BALL_BONUS
        defaultMPlayableCardShouldNotBeFound("highBallBonus.greaterOrEqualThan=" + UPDATED_HIGH_BALL_BONUS);
    }

    @Test
    @Transactional
    public void getAllMPlayableCardsByHighBallBonusIsLessThanSomething() throws Exception {
        // Initialize the database
        mPlayableCardRepository.saveAndFlush(mPlayableCard);

        // Get all the mPlayableCardList where highBallBonus less than or equals to DEFAULT_HIGH_BALL_BONUS
        defaultMPlayableCardShouldNotBeFound("highBallBonus.lessThan=" + DEFAULT_HIGH_BALL_BONUS);

        // Get all the mPlayableCardList where highBallBonus less than or equals to UPDATED_HIGH_BALL_BONUS
        defaultMPlayableCardShouldBeFound("highBallBonus.lessThan=" + UPDATED_HIGH_BALL_BONUS);
    }


    @Test
    @Transactional
    public void getAllMPlayableCardsByLowBallBonusIsEqualToSomething() throws Exception {
        // Initialize the database
        mPlayableCardRepository.saveAndFlush(mPlayableCard);

        // Get all the mPlayableCardList where lowBallBonus equals to DEFAULT_LOW_BALL_BONUS
        defaultMPlayableCardShouldBeFound("lowBallBonus.equals=" + DEFAULT_LOW_BALL_BONUS);

        // Get all the mPlayableCardList where lowBallBonus equals to UPDATED_LOW_BALL_BONUS
        defaultMPlayableCardShouldNotBeFound("lowBallBonus.equals=" + UPDATED_LOW_BALL_BONUS);
    }

    @Test
    @Transactional
    public void getAllMPlayableCardsByLowBallBonusIsInShouldWork() throws Exception {
        // Initialize the database
        mPlayableCardRepository.saveAndFlush(mPlayableCard);

        // Get all the mPlayableCardList where lowBallBonus in DEFAULT_LOW_BALL_BONUS or UPDATED_LOW_BALL_BONUS
        defaultMPlayableCardShouldBeFound("lowBallBonus.in=" + DEFAULT_LOW_BALL_BONUS + "," + UPDATED_LOW_BALL_BONUS);

        // Get all the mPlayableCardList where lowBallBonus equals to UPDATED_LOW_BALL_BONUS
        defaultMPlayableCardShouldNotBeFound("lowBallBonus.in=" + UPDATED_LOW_BALL_BONUS);
    }

    @Test
    @Transactional
    public void getAllMPlayableCardsByLowBallBonusIsNullOrNotNull() throws Exception {
        // Initialize the database
        mPlayableCardRepository.saveAndFlush(mPlayableCard);

        // Get all the mPlayableCardList where lowBallBonus is not null
        defaultMPlayableCardShouldBeFound("lowBallBonus.specified=true");

        // Get all the mPlayableCardList where lowBallBonus is null
        defaultMPlayableCardShouldNotBeFound("lowBallBonus.specified=false");
    }

    @Test
    @Transactional
    public void getAllMPlayableCardsByLowBallBonusIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mPlayableCardRepository.saveAndFlush(mPlayableCard);

        // Get all the mPlayableCardList where lowBallBonus greater than or equals to DEFAULT_LOW_BALL_BONUS
        defaultMPlayableCardShouldBeFound("lowBallBonus.greaterOrEqualThan=" + DEFAULT_LOW_BALL_BONUS);

        // Get all the mPlayableCardList where lowBallBonus greater than or equals to UPDATED_LOW_BALL_BONUS
        defaultMPlayableCardShouldNotBeFound("lowBallBonus.greaterOrEqualThan=" + UPDATED_LOW_BALL_BONUS);
    }

    @Test
    @Transactional
    public void getAllMPlayableCardsByLowBallBonusIsLessThanSomething() throws Exception {
        // Initialize the database
        mPlayableCardRepository.saveAndFlush(mPlayableCard);

        // Get all the mPlayableCardList where lowBallBonus less than or equals to DEFAULT_LOW_BALL_BONUS
        defaultMPlayableCardShouldNotBeFound("lowBallBonus.lessThan=" + DEFAULT_LOW_BALL_BONUS);

        // Get all the mPlayableCardList where lowBallBonus less than or equals to UPDATED_LOW_BALL_BONUS
        defaultMPlayableCardShouldBeFound("lowBallBonus.lessThan=" + UPDATED_LOW_BALL_BONUS);
    }


    @Test
    @Transactional
    public void getAllMPlayableCardsByIsDropOnlyIsEqualToSomething() throws Exception {
        // Initialize the database
        mPlayableCardRepository.saveAndFlush(mPlayableCard);

        // Get all the mPlayableCardList where isDropOnly equals to DEFAULT_IS_DROP_ONLY
        defaultMPlayableCardShouldBeFound("isDropOnly.equals=" + DEFAULT_IS_DROP_ONLY);

        // Get all the mPlayableCardList where isDropOnly equals to UPDATED_IS_DROP_ONLY
        defaultMPlayableCardShouldNotBeFound("isDropOnly.equals=" + UPDATED_IS_DROP_ONLY);
    }

    @Test
    @Transactional
    public void getAllMPlayableCardsByIsDropOnlyIsInShouldWork() throws Exception {
        // Initialize the database
        mPlayableCardRepository.saveAndFlush(mPlayableCard);

        // Get all the mPlayableCardList where isDropOnly in DEFAULT_IS_DROP_ONLY or UPDATED_IS_DROP_ONLY
        defaultMPlayableCardShouldBeFound("isDropOnly.in=" + DEFAULT_IS_DROP_ONLY + "," + UPDATED_IS_DROP_ONLY);

        // Get all the mPlayableCardList where isDropOnly equals to UPDATED_IS_DROP_ONLY
        defaultMPlayableCardShouldNotBeFound("isDropOnly.in=" + UPDATED_IS_DROP_ONLY);
    }

    @Test
    @Transactional
    public void getAllMPlayableCardsByIsDropOnlyIsNullOrNotNull() throws Exception {
        // Initialize the database
        mPlayableCardRepository.saveAndFlush(mPlayableCard);

        // Get all the mPlayableCardList where isDropOnly is not null
        defaultMPlayableCardShouldBeFound("isDropOnly.specified=true");

        // Get all the mPlayableCardList where isDropOnly is null
        defaultMPlayableCardShouldNotBeFound("isDropOnly.specified=false");
    }

    @Test
    @Transactional
    public void getAllMPlayableCardsByIsDropOnlyIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mPlayableCardRepository.saveAndFlush(mPlayableCard);

        // Get all the mPlayableCardList where isDropOnly greater than or equals to DEFAULT_IS_DROP_ONLY
        defaultMPlayableCardShouldBeFound("isDropOnly.greaterOrEqualThan=" + DEFAULT_IS_DROP_ONLY);

        // Get all the mPlayableCardList where isDropOnly greater than or equals to UPDATED_IS_DROP_ONLY
        defaultMPlayableCardShouldNotBeFound("isDropOnly.greaterOrEqualThan=" + UPDATED_IS_DROP_ONLY);
    }

    @Test
    @Transactional
    public void getAllMPlayableCardsByIsDropOnlyIsLessThanSomething() throws Exception {
        // Initialize the database
        mPlayableCardRepository.saveAndFlush(mPlayableCard);

        // Get all the mPlayableCardList where isDropOnly less than or equals to DEFAULT_IS_DROP_ONLY
        defaultMPlayableCardShouldNotBeFound("isDropOnly.lessThan=" + DEFAULT_IS_DROP_ONLY);

        // Get all the mPlayableCardList where isDropOnly less than or equals to UPDATED_IS_DROP_ONLY
        defaultMPlayableCardShouldBeFound("isDropOnly.lessThan=" + UPDATED_IS_DROP_ONLY);
    }


    @Test
    @Transactional
    public void getAllMPlayableCardsBySellCoinGroupNumIsEqualToSomething() throws Exception {
        // Initialize the database
        mPlayableCardRepository.saveAndFlush(mPlayableCard);

        // Get all the mPlayableCardList where sellCoinGroupNum equals to DEFAULT_SELL_COIN_GROUP_NUM
        defaultMPlayableCardShouldBeFound("sellCoinGroupNum.equals=" + DEFAULT_SELL_COIN_GROUP_NUM);

        // Get all the mPlayableCardList where sellCoinGroupNum equals to UPDATED_SELL_COIN_GROUP_NUM
        defaultMPlayableCardShouldNotBeFound("sellCoinGroupNum.equals=" + UPDATED_SELL_COIN_GROUP_NUM);
    }

    @Test
    @Transactional
    public void getAllMPlayableCardsBySellCoinGroupNumIsInShouldWork() throws Exception {
        // Initialize the database
        mPlayableCardRepository.saveAndFlush(mPlayableCard);

        // Get all the mPlayableCardList where sellCoinGroupNum in DEFAULT_SELL_COIN_GROUP_NUM or UPDATED_SELL_COIN_GROUP_NUM
        defaultMPlayableCardShouldBeFound("sellCoinGroupNum.in=" + DEFAULT_SELL_COIN_GROUP_NUM + "," + UPDATED_SELL_COIN_GROUP_NUM);

        // Get all the mPlayableCardList where sellCoinGroupNum equals to UPDATED_SELL_COIN_GROUP_NUM
        defaultMPlayableCardShouldNotBeFound("sellCoinGroupNum.in=" + UPDATED_SELL_COIN_GROUP_NUM);
    }

    @Test
    @Transactional
    public void getAllMPlayableCardsBySellCoinGroupNumIsNullOrNotNull() throws Exception {
        // Initialize the database
        mPlayableCardRepository.saveAndFlush(mPlayableCard);

        // Get all the mPlayableCardList where sellCoinGroupNum is not null
        defaultMPlayableCardShouldBeFound("sellCoinGroupNum.specified=true");

        // Get all the mPlayableCardList where sellCoinGroupNum is null
        defaultMPlayableCardShouldNotBeFound("sellCoinGroupNum.specified=false");
    }

    @Test
    @Transactional
    public void getAllMPlayableCardsBySellCoinGroupNumIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mPlayableCardRepository.saveAndFlush(mPlayableCard);

        // Get all the mPlayableCardList where sellCoinGroupNum greater than or equals to DEFAULT_SELL_COIN_GROUP_NUM
        defaultMPlayableCardShouldBeFound("sellCoinGroupNum.greaterOrEqualThan=" + DEFAULT_SELL_COIN_GROUP_NUM);

        // Get all the mPlayableCardList where sellCoinGroupNum greater than or equals to UPDATED_SELL_COIN_GROUP_NUM
        defaultMPlayableCardShouldNotBeFound("sellCoinGroupNum.greaterOrEqualThan=" + UPDATED_SELL_COIN_GROUP_NUM);
    }

    @Test
    @Transactional
    public void getAllMPlayableCardsBySellCoinGroupNumIsLessThanSomething() throws Exception {
        // Initialize the database
        mPlayableCardRepository.saveAndFlush(mPlayableCard);

        // Get all the mPlayableCardList where sellCoinGroupNum less than or equals to DEFAULT_SELL_COIN_GROUP_NUM
        defaultMPlayableCardShouldNotBeFound("sellCoinGroupNum.lessThan=" + DEFAULT_SELL_COIN_GROUP_NUM);

        // Get all the mPlayableCardList where sellCoinGroupNum less than or equals to UPDATED_SELL_COIN_GROUP_NUM
        defaultMPlayableCardShouldBeFound("sellCoinGroupNum.lessThan=" + UPDATED_SELL_COIN_GROUP_NUM);
    }


    @Test
    @Transactional
    public void getAllMPlayableCardsBySellMedalIdIsEqualToSomething() throws Exception {
        // Initialize the database
        mPlayableCardRepository.saveAndFlush(mPlayableCard);

        // Get all the mPlayableCardList where sellMedalId equals to DEFAULT_SELL_MEDAL_ID
        defaultMPlayableCardShouldBeFound("sellMedalId.equals=" + DEFAULT_SELL_MEDAL_ID);

        // Get all the mPlayableCardList where sellMedalId equals to UPDATED_SELL_MEDAL_ID
        defaultMPlayableCardShouldNotBeFound("sellMedalId.equals=" + UPDATED_SELL_MEDAL_ID);
    }

    @Test
    @Transactional
    public void getAllMPlayableCardsBySellMedalIdIsInShouldWork() throws Exception {
        // Initialize the database
        mPlayableCardRepository.saveAndFlush(mPlayableCard);

        // Get all the mPlayableCardList where sellMedalId in DEFAULT_SELL_MEDAL_ID or UPDATED_SELL_MEDAL_ID
        defaultMPlayableCardShouldBeFound("sellMedalId.in=" + DEFAULT_SELL_MEDAL_ID + "," + UPDATED_SELL_MEDAL_ID);

        // Get all the mPlayableCardList where sellMedalId equals to UPDATED_SELL_MEDAL_ID
        defaultMPlayableCardShouldNotBeFound("sellMedalId.in=" + UPDATED_SELL_MEDAL_ID);
    }

    @Test
    @Transactional
    public void getAllMPlayableCardsBySellMedalIdIsNullOrNotNull() throws Exception {
        // Initialize the database
        mPlayableCardRepository.saveAndFlush(mPlayableCard);

        // Get all the mPlayableCardList where sellMedalId is not null
        defaultMPlayableCardShouldBeFound("sellMedalId.specified=true");

        // Get all the mPlayableCardList where sellMedalId is null
        defaultMPlayableCardShouldNotBeFound("sellMedalId.specified=false");
    }

    @Test
    @Transactional
    public void getAllMPlayableCardsBySellMedalIdIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mPlayableCardRepository.saveAndFlush(mPlayableCard);

        // Get all the mPlayableCardList where sellMedalId greater than or equals to DEFAULT_SELL_MEDAL_ID
        defaultMPlayableCardShouldBeFound("sellMedalId.greaterOrEqualThan=" + DEFAULT_SELL_MEDAL_ID);

        // Get all the mPlayableCardList where sellMedalId greater than or equals to UPDATED_SELL_MEDAL_ID
        defaultMPlayableCardShouldNotBeFound("sellMedalId.greaterOrEqualThan=" + UPDATED_SELL_MEDAL_ID);
    }

    @Test
    @Transactional
    public void getAllMPlayableCardsBySellMedalIdIsLessThanSomething() throws Exception {
        // Initialize the database
        mPlayableCardRepository.saveAndFlush(mPlayableCard);

        // Get all the mPlayableCardList where sellMedalId less than or equals to DEFAULT_SELL_MEDAL_ID
        defaultMPlayableCardShouldNotBeFound("sellMedalId.lessThan=" + DEFAULT_SELL_MEDAL_ID);

        // Get all the mPlayableCardList where sellMedalId less than or equals to UPDATED_SELL_MEDAL_ID
        defaultMPlayableCardShouldBeFound("sellMedalId.lessThan=" + UPDATED_SELL_MEDAL_ID);
    }


    @Test
    @Transactional
    public void getAllMPlayableCardsByCharacterBookIdIsEqualToSomething() throws Exception {
        // Initialize the database
        mPlayableCardRepository.saveAndFlush(mPlayableCard);

        // Get all the mPlayableCardList where characterBookId equals to DEFAULT_CHARACTER_BOOK_ID
        defaultMPlayableCardShouldBeFound("characterBookId.equals=" + DEFAULT_CHARACTER_BOOK_ID);

        // Get all the mPlayableCardList where characterBookId equals to UPDATED_CHARACTER_BOOK_ID
        defaultMPlayableCardShouldNotBeFound("characterBookId.equals=" + UPDATED_CHARACTER_BOOK_ID);
    }

    @Test
    @Transactional
    public void getAllMPlayableCardsByCharacterBookIdIsInShouldWork() throws Exception {
        // Initialize the database
        mPlayableCardRepository.saveAndFlush(mPlayableCard);

        // Get all the mPlayableCardList where characterBookId in DEFAULT_CHARACTER_BOOK_ID or UPDATED_CHARACTER_BOOK_ID
        defaultMPlayableCardShouldBeFound("characterBookId.in=" + DEFAULT_CHARACTER_BOOK_ID + "," + UPDATED_CHARACTER_BOOK_ID);

        // Get all the mPlayableCardList where characterBookId equals to UPDATED_CHARACTER_BOOK_ID
        defaultMPlayableCardShouldNotBeFound("characterBookId.in=" + UPDATED_CHARACTER_BOOK_ID);
    }

    @Test
    @Transactional
    public void getAllMPlayableCardsByCharacterBookIdIsNullOrNotNull() throws Exception {
        // Initialize the database
        mPlayableCardRepository.saveAndFlush(mPlayableCard);

        // Get all the mPlayableCardList where characterBookId is not null
        defaultMPlayableCardShouldBeFound("characterBookId.specified=true");

        // Get all the mPlayableCardList where characterBookId is null
        defaultMPlayableCardShouldNotBeFound("characterBookId.specified=false");
    }

    @Test
    @Transactional
    public void getAllMPlayableCardsByCharacterBookIdIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mPlayableCardRepository.saveAndFlush(mPlayableCard);

        // Get all the mPlayableCardList where characterBookId greater than or equals to DEFAULT_CHARACTER_BOOK_ID
        defaultMPlayableCardShouldBeFound("characterBookId.greaterOrEqualThan=" + DEFAULT_CHARACTER_BOOK_ID);

        // Get all the mPlayableCardList where characterBookId greater than or equals to UPDATED_CHARACTER_BOOK_ID
        defaultMPlayableCardShouldNotBeFound("characterBookId.greaterOrEqualThan=" + UPDATED_CHARACTER_BOOK_ID);
    }

    @Test
    @Transactional
    public void getAllMPlayableCardsByCharacterBookIdIsLessThanSomething() throws Exception {
        // Initialize the database
        mPlayableCardRepository.saveAndFlush(mPlayableCard);

        // Get all the mPlayableCardList where characterBookId less than or equals to DEFAULT_CHARACTER_BOOK_ID
        defaultMPlayableCardShouldNotBeFound("characterBookId.lessThan=" + DEFAULT_CHARACTER_BOOK_ID);

        // Get all the mPlayableCardList where characterBookId less than or equals to UPDATED_CHARACTER_BOOK_ID
        defaultMPlayableCardShouldBeFound("characterBookId.lessThan=" + UPDATED_CHARACTER_BOOK_ID);
    }


    @Test
    @Transactional
    public void getAllMPlayableCardsByBookNoIsEqualToSomething() throws Exception {
        // Initialize the database
        mPlayableCardRepository.saveAndFlush(mPlayableCard);

        // Get all the mPlayableCardList where bookNo equals to DEFAULT_BOOK_NO
        defaultMPlayableCardShouldBeFound("bookNo.equals=" + DEFAULT_BOOK_NO);

        // Get all the mPlayableCardList where bookNo equals to UPDATED_BOOK_NO
        defaultMPlayableCardShouldNotBeFound("bookNo.equals=" + UPDATED_BOOK_NO);
    }

    @Test
    @Transactional
    public void getAllMPlayableCardsByBookNoIsInShouldWork() throws Exception {
        // Initialize the database
        mPlayableCardRepository.saveAndFlush(mPlayableCard);

        // Get all the mPlayableCardList where bookNo in DEFAULT_BOOK_NO or UPDATED_BOOK_NO
        defaultMPlayableCardShouldBeFound("bookNo.in=" + DEFAULT_BOOK_NO + "," + UPDATED_BOOK_NO);

        // Get all the mPlayableCardList where bookNo equals to UPDATED_BOOK_NO
        defaultMPlayableCardShouldNotBeFound("bookNo.in=" + UPDATED_BOOK_NO);
    }

    @Test
    @Transactional
    public void getAllMPlayableCardsByBookNoIsNullOrNotNull() throws Exception {
        // Initialize the database
        mPlayableCardRepository.saveAndFlush(mPlayableCard);

        // Get all the mPlayableCardList where bookNo is not null
        defaultMPlayableCardShouldBeFound("bookNo.specified=true");

        // Get all the mPlayableCardList where bookNo is null
        defaultMPlayableCardShouldNotBeFound("bookNo.specified=false");
    }

    @Test
    @Transactional
    public void getAllMPlayableCardsByBookNoIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mPlayableCardRepository.saveAndFlush(mPlayableCard);

        // Get all the mPlayableCardList where bookNo greater than or equals to DEFAULT_BOOK_NO
        defaultMPlayableCardShouldBeFound("bookNo.greaterOrEqualThan=" + DEFAULT_BOOK_NO);

        // Get all the mPlayableCardList where bookNo greater than or equals to UPDATED_BOOK_NO
        defaultMPlayableCardShouldNotBeFound("bookNo.greaterOrEqualThan=" + UPDATED_BOOK_NO);
    }

    @Test
    @Transactional
    public void getAllMPlayableCardsByBookNoIsLessThanSomething() throws Exception {
        // Initialize the database
        mPlayableCardRepository.saveAndFlush(mPlayableCard);

        // Get all the mPlayableCardList where bookNo less than or equals to DEFAULT_BOOK_NO
        defaultMPlayableCardShouldNotBeFound("bookNo.lessThan=" + DEFAULT_BOOK_NO);

        // Get all the mPlayableCardList where bookNo less than or equals to UPDATED_BOOK_NO
        defaultMPlayableCardShouldBeFound("bookNo.lessThan=" + UPDATED_BOOK_NO);
    }


    @Test
    @Transactional
    public void getAllMPlayableCardsByIsShowBookIsEqualToSomething() throws Exception {
        // Initialize the database
        mPlayableCardRepository.saveAndFlush(mPlayableCard);

        // Get all the mPlayableCardList where isShowBook equals to DEFAULT_IS_SHOW_BOOK
        defaultMPlayableCardShouldBeFound("isShowBook.equals=" + DEFAULT_IS_SHOW_BOOK);

        // Get all the mPlayableCardList where isShowBook equals to UPDATED_IS_SHOW_BOOK
        defaultMPlayableCardShouldNotBeFound("isShowBook.equals=" + UPDATED_IS_SHOW_BOOK);
    }

    @Test
    @Transactional
    public void getAllMPlayableCardsByIsShowBookIsInShouldWork() throws Exception {
        // Initialize the database
        mPlayableCardRepository.saveAndFlush(mPlayableCard);

        // Get all the mPlayableCardList where isShowBook in DEFAULT_IS_SHOW_BOOK or UPDATED_IS_SHOW_BOOK
        defaultMPlayableCardShouldBeFound("isShowBook.in=" + DEFAULT_IS_SHOW_BOOK + "," + UPDATED_IS_SHOW_BOOK);

        // Get all the mPlayableCardList where isShowBook equals to UPDATED_IS_SHOW_BOOK
        defaultMPlayableCardShouldNotBeFound("isShowBook.in=" + UPDATED_IS_SHOW_BOOK);
    }

    @Test
    @Transactional
    public void getAllMPlayableCardsByIsShowBookIsNullOrNotNull() throws Exception {
        // Initialize the database
        mPlayableCardRepository.saveAndFlush(mPlayableCard);

        // Get all the mPlayableCardList where isShowBook is not null
        defaultMPlayableCardShouldBeFound("isShowBook.specified=true");

        // Get all the mPlayableCardList where isShowBook is null
        defaultMPlayableCardShouldNotBeFound("isShowBook.specified=false");
    }

    @Test
    @Transactional
    public void getAllMPlayableCardsByIsShowBookIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mPlayableCardRepository.saveAndFlush(mPlayableCard);

        // Get all the mPlayableCardList where isShowBook greater than or equals to DEFAULT_IS_SHOW_BOOK
        defaultMPlayableCardShouldBeFound("isShowBook.greaterOrEqualThan=" + DEFAULT_IS_SHOW_BOOK);

        // Get all the mPlayableCardList where isShowBook greater than or equals to UPDATED_IS_SHOW_BOOK
        defaultMPlayableCardShouldNotBeFound("isShowBook.greaterOrEqualThan=" + UPDATED_IS_SHOW_BOOK);
    }

    @Test
    @Transactional
    public void getAllMPlayableCardsByIsShowBookIsLessThanSomething() throws Exception {
        // Initialize the database
        mPlayableCardRepository.saveAndFlush(mPlayableCard);

        // Get all the mPlayableCardList where isShowBook less than or equals to DEFAULT_IS_SHOW_BOOK
        defaultMPlayableCardShouldNotBeFound("isShowBook.lessThan=" + DEFAULT_IS_SHOW_BOOK);

        // Get all the mPlayableCardList where isShowBook less than or equals to UPDATED_IS_SHOW_BOOK
        defaultMPlayableCardShouldBeFound("isShowBook.lessThan=" + UPDATED_IS_SHOW_BOOK);
    }


    @Test
    @Transactional
    public void getAllMPlayableCardsByLevelGroupIdIsEqualToSomething() throws Exception {
        // Initialize the database
        mPlayableCardRepository.saveAndFlush(mPlayableCard);

        // Get all the mPlayableCardList where levelGroupId equals to DEFAULT_LEVEL_GROUP_ID
        defaultMPlayableCardShouldBeFound("levelGroupId.equals=" + DEFAULT_LEVEL_GROUP_ID);

        // Get all the mPlayableCardList where levelGroupId equals to UPDATED_LEVEL_GROUP_ID
        defaultMPlayableCardShouldNotBeFound("levelGroupId.equals=" + UPDATED_LEVEL_GROUP_ID);
    }

    @Test
    @Transactional
    public void getAllMPlayableCardsByLevelGroupIdIsInShouldWork() throws Exception {
        // Initialize the database
        mPlayableCardRepository.saveAndFlush(mPlayableCard);

        // Get all the mPlayableCardList where levelGroupId in DEFAULT_LEVEL_GROUP_ID or UPDATED_LEVEL_GROUP_ID
        defaultMPlayableCardShouldBeFound("levelGroupId.in=" + DEFAULT_LEVEL_GROUP_ID + "," + UPDATED_LEVEL_GROUP_ID);

        // Get all the mPlayableCardList where levelGroupId equals to UPDATED_LEVEL_GROUP_ID
        defaultMPlayableCardShouldNotBeFound("levelGroupId.in=" + UPDATED_LEVEL_GROUP_ID);
    }

    @Test
    @Transactional
    public void getAllMPlayableCardsByLevelGroupIdIsNullOrNotNull() throws Exception {
        // Initialize the database
        mPlayableCardRepository.saveAndFlush(mPlayableCard);

        // Get all the mPlayableCardList where levelGroupId is not null
        defaultMPlayableCardShouldBeFound("levelGroupId.specified=true");

        // Get all the mPlayableCardList where levelGroupId is null
        defaultMPlayableCardShouldNotBeFound("levelGroupId.specified=false");
    }

    @Test
    @Transactional
    public void getAllMPlayableCardsByLevelGroupIdIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mPlayableCardRepository.saveAndFlush(mPlayableCard);

        // Get all the mPlayableCardList where levelGroupId greater than or equals to DEFAULT_LEVEL_GROUP_ID
        defaultMPlayableCardShouldBeFound("levelGroupId.greaterOrEqualThan=" + DEFAULT_LEVEL_GROUP_ID);

        // Get all the mPlayableCardList where levelGroupId greater than or equals to UPDATED_LEVEL_GROUP_ID
        defaultMPlayableCardShouldNotBeFound("levelGroupId.greaterOrEqualThan=" + UPDATED_LEVEL_GROUP_ID);
    }

    @Test
    @Transactional
    public void getAllMPlayableCardsByLevelGroupIdIsLessThanSomething() throws Exception {
        // Initialize the database
        mPlayableCardRepository.saveAndFlush(mPlayableCard);

        // Get all the mPlayableCardList where levelGroupId less than or equals to DEFAULT_LEVEL_GROUP_ID
        defaultMPlayableCardShouldNotBeFound("levelGroupId.lessThan=" + DEFAULT_LEVEL_GROUP_ID);

        // Get all the mPlayableCardList where levelGroupId less than or equals to UPDATED_LEVEL_GROUP_ID
        defaultMPlayableCardShouldBeFound("levelGroupId.lessThan=" + UPDATED_LEVEL_GROUP_ID);
    }


    @Test
    @Transactional
    public void getAllMPlayableCardsByStartAtIsEqualToSomething() throws Exception {
        // Initialize the database
        mPlayableCardRepository.saveAndFlush(mPlayableCard);

        // Get all the mPlayableCardList where startAt equals to DEFAULT_START_AT
        defaultMPlayableCardShouldBeFound("startAt.equals=" + DEFAULT_START_AT);

        // Get all the mPlayableCardList where startAt equals to UPDATED_START_AT
        defaultMPlayableCardShouldNotBeFound("startAt.equals=" + UPDATED_START_AT);
    }

    @Test
    @Transactional
    public void getAllMPlayableCardsByStartAtIsInShouldWork() throws Exception {
        // Initialize the database
        mPlayableCardRepository.saveAndFlush(mPlayableCard);

        // Get all the mPlayableCardList where startAt in DEFAULT_START_AT or UPDATED_START_AT
        defaultMPlayableCardShouldBeFound("startAt.in=" + DEFAULT_START_AT + "," + UPDATED_START_AT);

        // Get all the mPlayableCardList where startAt equals to UPDATED_START_AT
        defaultMPlayableCardShouldNotBeFound("startAt.in=" + UPDATED_START_AT);
    }

    @Test
    @Transactional
    public void getAllMPlayableCardsByStartAtIsNullOrNotNull() throws Exception {
        // Initialize the database
        mPlayableCardRepository.saveAndFlush(mPlayableCard);

        // Get all the mPlayableCardList where startAt is not null
        defaultMPlayableCardShouldBeFound("startAt.specified=true");

        // Get all the mPlayableCardList where startAt is null
        defaultMPlayableCardShouldNotBeFound("startAt.specified=false");
    }

    @Test
    @Transactional
    public void getAllMPlayableCardsByStartAtIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mPlayableCardRepository.saveAndFlush(mPlayableCard);

        // Get all the mPlayableCardList where startAt greater than or equals to DEFAULT_START_AT
        defaultMPlayableCardShouldBeFound("startAt.greaterOrEqualThan=" + DEFAULT_START_AT);

        // Get all the mPlayableCardList where startAt greater than or equals to UPDATED_START_AT
        defaultMPlayableCardShouldNotBeFound("startAt.greaterOrEqualThan=" + UPDATED_START_AT);
    }

    @Test
    @Transactional
    public void getAllMPlayableCardsByStartAtIsLessThanSomething() throws Exception {
        // Initialize the database
        mPlayableCardRepository.saveAndFlush(mPlayableCard);

        // Get all the mPlayableCardList where startAt less than or equals to DEFAULT_START_AT
        defaultMPlayableCardShouldNotBeFound("startAt.lessThan=" + DEFAULT_START_AT);

        // Get all the mPlayableCardList where startAt less than or equals to UPDATED_START_AT
        defaultMPlayableCardShouldBeFound("startAt.lessThan=" + UPDATED_START_AT);
    }


    @Test
    @Transactional
    public void getAllMPlayableCardsByIdIsEqualToSomething() throws Exception {
        // Get already existing entity
        MModelCard id = mPlayableCard.getId();
        mPlayableCardRepository.saveAndFlush(mPlayableCard);
        Long idId = id.getId();

        // Get all the mPlayableCardList where id equals to idId
        defaultMPlayableCardShouldBeFound("idId.equals=" + idId);

        // Get all the mPlayableCardList where id equals to idId + 1
        defaultMPlayableCardShouldNotBeFound("idId.equals=" + (idId + 1));
    }


    @Test
    @Transactional
    public void getAllMPlayableCardsByMArousalIsEqualToSomething() throws Exception {
        // Initialize the database
        MArousal mArousal = MArousalResourceIT.createEntity(em);
        em.persist(mArousal);
        em.flush();
        mPlayableCard.addMArousal(mArousal);
        mPlayableCardRepository.saveAndFlush(mPlayableCard);
        Long mArousalId = mArousal.getId();

        // Get all the mPlayableCardList where mArousal equals to mArousalId
        defaultMPlayableCardShouldBeFound("mArousalId.equals=" + mArousalId);

        // Get all the mPlayableCardList where mArousal equals to mArousalId + 1
        defaultMPlayableCardShouldNotBeFound("mArousalId.equals=" + (mArousalId + 1));
    }


    @Test
    @Transactional
    public void getAllMPlayableCardsByMTargetPlayableCardGroupIsEqualToSomething() throws Exception {
        // Initialize the database
        MTargetPlayableCardGroup mTargetPlayableCardGroup = MTargetPlayableCardGroupResourceIT.createEntity(em);
        em.persist(mTargetPlayableCardGroup);
        em.flush();
        mPlayableCard.addMTargetPlayableCardGroup(mTargetPlayableCardGroup);
        mPlayableCardRepository.saveAndFlush(mPlayableCard);
        Long mTargetPlayableCardGroupId = mTargetPlayableCardGroup.getId();

        // Get all the mPlayableCardList where mTargetPlayableCardGroup equals to mTargetPlayableCardGroupId
        defaultMPlayableCardShouldBeFound("mTargetPlayableCardGroupId.equals=" + mTargetPlayableCardGroupId);

        // Get all the mPlayableCardList where mTargetPlayableCardGroup equals to mTargetPlayableCardGroupId + 1
        defaultMPlayableCardShouldNotBeFound("mTargetPlayableCardGroupId.equals=" + (mTargetPlayableCardGroupId + 1));
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultMPlayableCardShouldBeFound(String filter) throws Exception {
        restMPlayableCardMockMvc.perform(get("/api/m-playable-cards?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(mPlayableCard.getId().intValue())))
            .andExpect(jsonPath("$.[*].modelId").value(hasItem(DEFAULT_MODEL_ID)))
            .andExpect(jsonPath("$.[*].properPositionGk").value(hasItem(DEFAULT_PROPER_POSITION_GK)))
            .andExpect(jsonPath("$.[*].properPositionFw").value(hasItem(DEFAULT_PROPER_POSITION_FW)))
            .andExpect(jsonPath("$.[*].properPositionOmf").value(hasItem(DEFAULT_PROPER_POSITION_OMF)))
            .andExpect(jsonPath("$.[*].properPositionDmf").value(hasItem(DEFAULT_PROPER_POSITION_DMF)))
            .andExpect(jsonPath("$.[*].properPositionDf").value(hasItem(DEFAULT_PROPER_POSITION_DF)))
            .andExpect(jsonPath("$.[*].characterId").value(hasItem(DEFAULT_CHARACTER_ID)))
            .andExpect(jsonPath("$.[*].nickName").value(hasItem(DEFAULT_NICK_NAME.toString())))
            .andExpect(jsonPath("$.[*].teamId").value(hasItem(DEFAULT_TEAM_ID)))
            .andExpect(jsonPath("$.[*].nationalityId").value(hasItem(DEFAULT_NATIONALITY_ID)))
            .andExpect(jsonPath("$.[*].rarity").value(hasItem(DEFAULT_RARITY)))
            .andExpect(jsonPath("$.[*].attribute").value(hasItem(DEFAULT_ATTRIBUTE)))
            .andExpect(jsonPath("$.[*].thumbnailAssetsId").value(hasItem(DEFAULT_THUMBNAIL_ASSETS_ID)))
            .andExpect(jsonPath("$.[*].cardIllustAssetsId").value(hasItem(DEFAULT_CARD_ILLUST_ASSETS_ID)))
            .andExpect(jsonPath("$.[*].playableAssetsId").value(hasItem(DEFAULT_PLAYABLE_ASSETS_ID)))
            .andExpect(jsonPath("$.[*].teamEffectId").value(hasItem(DEFAULT_TEAM_EFFECT_ID)))
            .andExpect(jsonPath("$.[*].triggerEffectId").value(hasItem(DEFAULT_TRIGGER_EFFECT_ID)))
            .andExpect(jsonPath("$.[*].maxActionSlot").value(hasItem(DEFAULT_MAX_ACTION_SLOT)))
            .andExpect(jsonPath("$.[*].initialActionId1").value(hasItem(DEFAULT_INITIAL_ACTION_ID_1)))
            .andExpect(jsonPath("$.[*].initialActionId2").value(hasItem(DEFAULT_INITIAL_ACTION_ID_2)))
            .andExpect(jsonPath("$.[*].initialActionId3").value(hasItem(DEFAULT_INITIAL_ACTION_ID_3)))
            .andExpect(jsonPath("$.[*].initialActionId4").value(hasItem(DEFAULT_INITIAL_ACTION_ID_4)))
            .andExpect(jsonPath("$.[*].initialActionId5").value(hasItem(DEFAULT_INITIAL_ACTION_ID_5)))
            .andExpect(jsonPath("$.[*].initialStamina").value(hasItem(DEFAULT_INITIAL_STAMINA)))
            .andExpect(jsonPath("$.[*].initialDribble").value(hasItem(DEFAULT_INITIAL_DRIBBLE)))
            .andExpect(jsonPath("$.[*].initialShoot").value(hasItem(DEFAULT_INITIAL_SHOOT)))
            .andExpect(jsonPath("$.[*].initialPass").value(hasItem(DEFAULT_INITIAL_PASS)))
            .andExpect(jsonPath("$.[*].initialTackle").value(hasItem(DEFAULT_INITIAL_TACKLE)))
            .andExpect(jsonPath("$.[*].initialBlock").value(hasItem(DEFAULT_INITIAL_BLOCK)))
            .andExpect(jsonPath("$.[*].initialIntercept").value(hasItem(DEFAULT_INITIAL_INTERCEPT)))
            .andExpect(jsonPath("$.[*].initialSpeed").value(hasItem(DEFAULT_INITIAL_SPEED)))
            .andExpect(jsonPath("$.[*].initialPower").value(hasItem(DEFAULT_INITIAL_POWER)))
            .andExpect(jsonPath("$.[*].initialTechnique").value(hasItem(DEFAULT_INITIAL_TECHNIQUE)))
            .andExpect(jsonPath("$.[*].initialPunching").value(hasItem(DEFAULT_INITIAL_PUNCHING)))
            .andExpect(jsonPath("$.[*].initialCatching").value(hasItem(DEFAULT_INITIAL_CATCHING)))
            .andExpect(jsonPath("$.[*].maxStamina").value(hasItem(DEFAULT_MAX_STAMINA)))
            .andExpect(jsonPath("$.[*].maxDribble").value(hasItem(DEFAULT_MAX_DRIBBLE)))
            .andExpect(jsonPath("$.[*].maxShoot").value(hasItem(DEFAULT_MAX_SHOOT)))
            .andExpect(jsonPath("$.[*].maxPass").value(hasItem(DEFAULT_MAX_PASS)))
            .andExpect(jsonPath("$.[*].maxTackle").value(hasItem(DEFAULT_MAX_TACKLE)))
            .andExpect(jsonPath("$.[*].maxBlock").value(hasItem(DEFAULT_MAX_BLOCK)))
            .andExpect(jsonPath("$.[*].maxIntercept").value(hasItem(DEFAULT_MAX_INTERCEPT)))
            .andExpect(jsonPath("$.[*].maxSpeed").value(hasItem(DEFAULT_MAX_SPEED)))
            .andExpect(jsonPath("$.[*].maxPower").value(hasItem(DEFAULT_MAX_POWER)))
            .andExpect(jsonPath("$.[*].maxTechnique").value(hasItem(DEFAULT_MAX_TECHNIQUE)))
            .andExpect(jsonPath("$.[*].maxPunching").value(hasItem(DEFAULT_MAX_PUNCHING)))
            .andExpect(jsonPath("$.[*].maxCatching").value(hasItem(DEFAULT_MAX_CATCHING)))
            .andExpect(jsonPath("$.[*].maxPlusDribble").value(hasItem(DEFAULT_MAX_PLUS_DRIBBLE)))
            .andExpect(jsonPath("$.[*].maxPlusShoot").value(hasItem(DEFAULT_MAX_PLUS_SHOOT)))
            .andExpect(jsonPath("$.[*].maxPlusPass").value(hasItem(DEFAULT_MAX_PLUS_PASS)))
            .andExpect(jsonPath("$.[*].maxPlusTackle").value(hasItem(DEFAULT_MAX_PLUS_TACKLE)))
            .andExpect(jsonPath("$.[*].maxPlusBlock").value(hasItem(DEFAULT_MAX_PLUS_BLOCK)))
            .andExpect(jsonPath("$.[*].maxPlusIntercept").value(hasItem(DEFAULT_MAX_PLUS_INTERCEPT)))
            .andExpect(jsonPath("$.[*].maxPlusSpeed").value(hasItem(DEFAULT_MAX_PLUS_SPEED)))
            .andExpect(jsonPath("$.[*].maxPlusPower").value(hasItem(DEFAULT_MAX_PLUS_POWER)))
            .andExpect(jsonPath("$.[*].maxPlusTechnique").value(hasItem(DEFAULT_MAX_PLUS_TECHNIQUE)))
            .andExpect(jsonPath("$.[*].maxPlusPunching").value(hasItem(DEFAULT_MAX_PLUS_PUNCHING)))
            .andExpect(jsonPath("$.[*].maxPlusCatching").value(hasItem(DEFAULT_MAX_PLUS_CATCHING)))
            .andExpect(jsonPath("$.[*].highBallBonus").value(hasItem(DEFAULT_HIGH_BALL_BONUS)))
            .andExpect(jsonPath("$.[*].lowBallBonus").value(hasItem(DEFAULT_LOW_BALL_BONUS)))
            .andExpect(jsonPath("$.[*].isDropOnly").value(hasItem(DEFAULT_IS_DROP_ONLY)))
            .andExpect(jsonPath("$.[*].sellCoinGroupNum").value(hasItem(DEFAULT_SELL_COIN_GROUP_NUM)))
            .andExpect(jsonPath("$.[*].sellMedalId").value(hasItem(DEFAULT_SELL_MEDAL_ID)))
            .andExpect(jsonPath("$.[*].characterBookId").value(hasItem(DEFAULT_CHARACTER_BOOK_ID)))
            .andExpect(jsonPath("$.[*].bookNo").value(hasItem(DEFAULT_BOOK_NO)))
            .andExpect(jsonPath("$.[*].isShowBook").value(hasItem(DEFAULT_IS_SHOW_BOOK)))
            .andExpect(jsonPath("$.[*].levelGroupId").value(hasItem(DEFAULT_LEVEL_GROUP_ID)))
            .andExpect(jsonPath("$.[*].startAt").value(hasItem(DEFAULT_START_AT)));

        // Check, that the count call also returns 1
        restMPlayableCardMockMvc.perform(get("/api/m-playable-cards/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultMPlayableCardShouldNotBeFound(String filter) throws Exception {
        restMPlayableCardMockMvc.perform(get("/api/m-playable-cards?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restMPlayableCardMockMvc.perform(get("/api/m-playable-cards/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("0"));
    }


    @Test
    @Transactional
    public void getNonExistingMPlayableCard() throws Exception {
        // Get the mPlayableCard
        restMPlayableCardMockMvc.perform(get("/api/m-playable-cards/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateMPlayableCard() throws Exception {
        // Initialize the database
        mPlayableCardRepository.saveAndFlush(mPlayableCard);

        int databaseSizeBeforeUpdate = mPlayableCardRepository.findAll().size();

        // Update the mPlayableCard
        MPlayableCard updatedMPlayableCard = mPlayableCardRepository.findById(mPlayableCard.getId()).get();
        // Disconnect from session so that the updates on updatedMPlayableCard are not directly saved in db
        em.detach(updatedMPlayableCard);
        updatedMPlayableCard
            .modelId(UPDATED_MODEL_ID)
            .properPositionGk(UPDATED_PROPER_POSITION_GK)
            .properPositionFw(UPDATED_PROPER_POSITION_FW)
            .properPositionOmf(UPDATED_PROPER_POSITION_OMF)
            .properPositionDmf(UPDATED_PROPER_POSITION_DMF)
            .properPositionDf(UPDATED_PROPER_POSITION_DF)
            .characterId(UPDATED_CHARACTER_ID)
            .nickName(UPDATED_NICK_NAME)
            .teamId(UPDATED_TEAM_ID)
            .nationalityId(UPDATED_NATIONALITY_ID)
            .rarity(UPDATED_RARITY)
            .attribute(UPDATED_ATTRIBUTE)
            .thumbnailAssetsId(UPDATED_THUMBNAIL_ASSETS_ID)
            .cardIllustAssetsId(UPDATED_CARD_ILLUST_ASSETS_ID)
            .playableAssetsId(UPDATED_PLAYABLE_ASSETS_ID)
            .teamEffectId(UPDATED_TEAM_EFFECT_ID)
            .triggerEffectId(UPDATED_TRIGGER_EFFECT_ID)
            .maxActionSlot(UPDATED_MAX_ACTION_SLOT)
            .initialActionId1(UPDATED_INITIAL_ACTION_ID_1)
            .initialActionId2(UPDATED_INITIAL_ACTION_ID_2)
            .initialActionId3(UPDATED_INITIAL_ACTION_ID_3)
            .initialActionId4(UPDATED_INITIAL_ACTION_ID_4)
            .initialActionId5(UPDATED_INITIAL_ACTION_ID_5)
            .initialStamina(UPDATED_INITIAL_STAMINA)
            .initialDribble(UPDATED_INITIAL_DRIBBLE)
            .initialShoot(UPDATED_INITIAL_SHOOT)
            .initialPass(UPDATED_INITIAL_PASS)
            .initialTackle(UPDATED_INITIAL_TACKLE)
            .initialBlock(UPDATED_INITIAL_BLOCK)
            .initialIntercept(UPDATED_INITIAL_INTERCEPT)
            .initialSpeed(UPDATED_INITIAL_SPEED)
            .initialPower(UPDATED_INITIAL_POWER)
            .initialTechnique(UPDATED_INITIAL_TECHNIQUE)
            .initialPunching(UPDATED_INITIAL_PUNCHING)
            .initialCatching(UPDATED_INITIAL_CATCHING)
            .maxStamina(UPDATED_MAX_STAMINA)
            .maxDribble(UPDATED_MAX_DRIBBLE)
            .maxShoot(UPDATED_MAX_SHOOT)
            .maxPass(UPDATED_MAX_PASS)
            .maxTackle(UPDATED_MAX_TACKLE)
            .maxBlock(UPDATED_MAX_BLOCK)
            .maxIntercept(UPDATED_MAX_INTERCEPT)
            .maxSpeed(UPDATED_MAX_SPEED)
            .maxPower(UPDATED_MAX_POWER)
            .maxTechnique(UPDATED_MAX_TECHNIQUE)
            .maxPunching(UPDATED_MAX_PUNCHING)
            .maxCatching(UPDATED_MAX_CATCHING)
            .maxPlusDribble(UPDATED_MAX_PLUS_DRIBBLE)
            .maxPlusShoot(UPDATED_MAX_PLUS_SHOOT)
            .maxPlusPass(UPDATED_MAX_PLUS_PASS)
            .maxPlusTackle(UPDATED_MAX_PLUS_TACKLE)
            .maxPlusBlock(UPDATED_MAX_PLUS_BLOCK)
            .maxPlusIntercept(UPDATED_MAX_PLUS_INTERCEPT)
            .maxPlusSpeed(UPDATED_MAX_PLUS_SPEED)
            .maxPlusPower(UPDATED_MAX_PLUS_POWER)
            .maxPlusTechnique(UPDATED_MAX_PLUS_TECHNIQUE)
            .maxPlusPunching(UPDATED_MAX_PLUS_PUNCHING)
            .maxPlusCatching(UPDATED_MAX_PLUS_CATCHING)
            .highBallBonus(UPDATED_HIGH_BALL_BONUS)
            .lowBallBonus(UPDATED_LOW_BALL_BONUS)
            .isDropOnly(UPDATED_IS_DROP_ONLY)
            .sellCoinGroupNum(UPDATED_SELL_COIN_GROUP_NUM)
            .sellMedalId(UPDATED_SELL_MEDAL_ID)
            .characterBookId(UPDATED_CHARACTER_BOOK_ID)
            .bookNo(UPDATED_BOOK_NO)
            .isShowBook(UPDATED_IS_SHOW_BOOK)
            .levelGroupId(UPDATED_LEVEL_GROUP_ID)
            .startAt(UPDATED_START_AT);
        MPlayableCardDTO mPlayableCardDTO = mPlayableCardMapper.toDto(updatedMPlayableCard);

        restMPlayableCardMockMvc.perform(put("/api/m-playable-cards")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mPlayableCardDTO)))
            .andExpect(status().isOk());

        // Validate the MPlayableCard in the database
        List<MPlayableCard> mPlayableCardList = mPlayableCardRepository.findAll();
        assertThat(mPlayableCardList).hasSize(databaseSizeBeforeUpdate);
        MPlayableCard testMPlayableCard = mPlayableCardList.get(mPlayableCardList.size() - 1);
        assertThat(testMPlayableCard.getModelId()).isEqualTo(UPDATED_MODEL_ID);
        assertThat(testMPlayableCard.getProperPositionGk()).isEqualTo(UPDATED_PROPER_POSITION_GK);
        assertThat(testMPlayableCard.getProperPositionFw()).isEqualTo(UPDATED_PROPER_POSITION_FW);
        assertThat(testMPlayableCard.getProperPositionOmf()).isEqualTo(UPDATED_PROPER_POSITION_OMF);
        assertThat(testMPlayableCard.getProperPositionDmf()).isEqualTo(UPDATED_PROPER_POSITION_DMF);
        assertThat(testMPlayableCard.getProperPositionDf()).isEqualTo(UPDATED_PROPER_POSITION_DF);
        assertThat(testMPlayableCard.getCharacterId()).isEqualTo(UPDATED_CHARACTER_ID);
        assertThat(testMPlayableCard.getNickName()).isEqualTo(UPDATED_NICK_NAME);
        assertThat(testMPlayableCard.getTeamId()).isEqualTo(UPDATED_TEAM_ID);
        assertThat(testMPlayableCard.getNationalityId()).isEqualTo(UPDATED_NATIONALITY_ID);
        assertThat(testMPlayableCard.getRarity()).isEqualTo(UPDATED_RARITY);
        assertThat(testMPlayableCard.getAttribute()).isEqualTo(UPDATED_ATTRIBUTE);
        assertThat(testMPlayableCard.getThumbnailAssetsId()).isEqualTo(UPDATED_THUMBNAIL_ASSETS_ID);
        assertThat(testMPlayableCard.getCardIllustAssetsId()).isEqualTo(UPDATED_CARD_ILLUST_ASSETS_ID);
        assertThat(testMPlayableCard.getPlayableAssetsId()).isEqualTo(UPDATED_PLAYABLE_ASSETS_ID);
        assertThat(testMPlayableCard.getTeamEffectId()).isEqualTo(UPDATED_TEAM_EFFECT_ID);
        assertThat(testMPlayableCard.getTriggerEffectId()).isEqualTo(UPDATED_TRIGGER_EFFECT_ID);
        assertThat(testMPlayableCard.getMaxActionSlot()).isEqualTo(UPDATED_MAX_ACTION_SLOT);
        assertThat(testMPlayableCard.getInitialActionId1()).isEqualTo(UPDATED_INITIAL_ACTION_ID_1);
        assertThat(testMPlayableCard.getInitialActionId2()).isEqualTo(UPDATED_INITIAL_ACTION_ID_2);
        assertThat(testMPlayableCard.getInitialActionId3()).isEqualTo(UPDATED_INITIAL_ACTION_ID_3);
        assertThat(testMPlayableCard.getInitialActionId4()).isEqualTo(UPDATED_INITIAL_ACTION_ID_4);
        assertThat(testMPlayableCard.getInitialActionId5()).isEqualTo(UPDATED_INITIAL_ACTION_ID_5);
        assertThat(testMPlayableCard.getInitialStamina()).isEqualTo(UPDATED_INITIAL_STAMINA);
        assertThat(testMPlayableCard.getInitialDribble()).isEqualTo(UPDATED_INITIAL_DRIBBLE);
        assertThat(testMPlayableCard.getInitialShoot()).isEqualTo(UPDATED_INITIAL_SHOOT);
        assertThat(testMPlayableCard.getInitialPass()).isEqualTo(UPDATED_INITIAL_PASS);
        assertThat(testMPlayableCard.getInitialTackle()).isEqualTo(UPDATED_INITIAL_TACKLE);
        assertThat(testMPlayableCard.getInitialBlock()).isEqualTo(UPDATED_INITIAL_BLOCK);
        assertThat(testMPlayableCard.getInitialIntercept()).isEqualTo(UPDATED_INITIAL_INTERCEPT);
        assertThat(testMPlayableCard.getInitialSpeed()).isEqualTo(UPDATED_INITIAL_SPEED);
        assertThat(testMPlayableCard.getInitialPower()).isEqualTo(UPDATED_INITIAL_POWER);
        assertThat(testMPlayableCard.getInitialTechnique()).isEqualTo(UPDATED_INITIAL_TECHNIQUE);
        assertThat(testMPlayableCard.getInitialPunching()).isEqualTo(UPDATED_INITIAL_PUNCHING);
        assertThat(testMPlayableCard.getInitialCatching()).isEqualTo(UPDATED_INITIAL_CATCHING);
        assertThat(testMPlayableCard.getMaxStamina()).isEqualTo(UPDATED_MAX_STAMINA);
        assertThat(testMPlayableCard.getMaxDribble()).isEqualTo(UPDATED_MAX_DRIBBLE);
        assertThat(testMPlayableCard.getMaxShoot()).isEqualTo(UPDATED_MAX_SHOOT);
        assertThat(testMPlayableCard.getMaxPass()).isEqualTo(UPDATED_MAX_PASS);
        assertThat(testMPlayableCard.getMaxTackle()).isEqualTo(UPDATED_MAX_TACKLE);
        assertThat(testMPlayableCard.getMaxBlock()).isEqualTo(UPDATED_MAX_BLOCK);
        assertThat(testMPlayableCard.getMaxIntercept()).isEqualTo(UPDATED_MAX_INTERCEPT);
        assertThat(testMPlayableCard.getMaxSpeed()).isEqualTo(UPDATED_MAX_SPEED);
        assertThat(testMPlayableCard.getMaxPower()).isEqualTo(UPDATED_MAX_POWER);
        assertThat(testMPlayableCard.getMaxTechnique()).isEqualTo(UPDATED_MAX_TECHNIQUE);
        assertThat(testMPlayableCard.getMaxPunching()).isEqualTo(UPDATED_MAX_PUNCHING);
        assertThat(testMPlayableCard.getMaxCatching()).isEqualTo(UPDATED_MAX_CATCHING);
        assertThat(testMPlayableCard.getMaxPlusDribble()).isEqualTo(UPDATED_MAX_PLUS_DRIBBLE);
        assertThat(testMPlayableCard.getMaxPlusShoot()).isEqualTo(UPDATED_MAX_PLUS_SHOOT);
        assertThat(testMPlayableCard.getMaxPlusPass()).isEqualTo(UPDATED_MAX_PLUS_PASS);
        assertThat(testMPlayableCard.getMaxPlusTackle()).isEqualTo(UPDATED_MAX_PLUS_TACKLE);
        assertThat(testMPlayableCard.getMaxPlusBlock()).isEqualTo(UPDATED_MAX_PLUS_BLOCK);
        assertThat(testMPlayableCard.getMaxPlusIntercept()).isEqualTo(UPDATED_MAX_PLUS_INTERCEPT);
        assertThat(testMPlayableCard.getMaxPlusSpeed()).isEqualTo(UPDATED_MAX_PLUS_SPEED);
        assertThat(testMPlayableCard.getMaxPlusPower()).isEqualTo(UPDATED_MAX_PLUS_POWER);
        assertThat(testMPlayableCard.getMaxPlusTechnique()).isEqualTo(UPDATED_MAX_PLUS_TECHNIQUE);
        assertThat(testMPlayableCard.getMaxPlusPunching()).isEqualTo(UPDATED_MAX_PLUS_PUNCHING);
        assertThat(testMPlayableCard.getMaxPlusCatching()).isEqualTo(UPDATED_MAX_PLUS_CATCHING);
        assertThat(testMPlayableCard.getHighBallBonus()).isEqualTo(UPDATED_HIGH_BALL_BONUS);
        assertThat(testMPlayableCard.getLowBallBonus()).isEqualTo(UPDATED_LOW_BALL_BONUS);
        assertThat(testMPlayableCard.getIsDropOnly()).isEqualTo(UPDATED_IS_DROP_ONLY);
        assertThat(testMPlayableCard.getSellCoinGroupNum()).isEqualTo(UPDATED_SELL_COIN_GROUP_NUM);
        assertThat(testMPlayableCard.getSellMedalId()).isEqualTo(UPDATED_SELL_MEDAL_ID);
        assertThat(testMPlayableCard.getCharacterBookId()).isEqualTo(UPDATED_CHARACTER_BOOK_ID);
        assertThat(testMPlayableCard.getBookNo()).isEqualTo(UPDATED_BOOK_NO);
        assertThat(testMPlayableCard.getIsShowBook()).isEqualTo(UPDATED_IS_SHOW_BOOK);
        assertThat(testMPlayableCard.getLevelGroupId()).isEqualTo(UPDATED_LEVEL_GROUP_ID);
        assertThat(testMPlayableCard.getStartAt()).isEqualTo(UPDATED_START_AT);
    }

    @Test
    @Transactional
    public void updateNonExistingMPlayableCard() throws Exception {
        int databaseSizeBeforeUpdate = mPlayableCardRepository.findAll().size();

        // Create the MPlayableCard
        MPlayableCardDTO mPlayableCardDTO = mPlayableCardMapper.toDto(mPlayableCard);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restMPlayableCardMockMvc.perform(put("/api/m-playable-cards")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mPlayableCardDTO)))
            .andExpect(status().isBadRequest());

        // Validate the MPlayableCard in the database
        List<MPlayableCard> mPlayableCardList = mPlayableCardRepository.findAll();
        assertThat(mPlayableCardList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteMPlayableCard() throws Exception {
        // Initialize the database
        mPlayableCardRepository.saveAndFlush(mPlayableCard);

        int databaseSizeBeforeDelete = mPlayableCardRepository.findAll().size();

        // Delete the mPlayableCard
        restMPlayableCardMockMvc.perform(delete("/api/m-playable-cards/{id}", mPlayableCard.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database is empty
        List<MPlayableCard> mPlayableCardList = mPlayableCardRepository.findAll();
        assertThat(mPlayableCardList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(MPlayableCard.class);
        MPlayableCard mPlayableCard1 = new MPlayableCard();
        mPlayableCard1.setId(1L);
        MPlayableCard mPlayableCard2 = new MPlayableCard();
        mPlayableCard2.setId(mPlayableCard1.getId());
        assertThat(mPlayableCard1).isEqualTo(mPlayableCard2);
        mPlayableCard2.setId(2L);
        assertThat(mPlayableCard1).isNotEqualTo(mPlayableCard2);
        mPlayableCard1.setId(null);
        assertThat(mPlayableCard1).isNotEqualTo(mPlayableCard2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(MPlayableCardDTO.class);
        MPlayableCardDTO mPlayableCardDTO1 = new MPlayableCardDTO();
        mPlayableCardDTO1.setId(1L);
        MPlayableCardDTO mPlayableCardDTO2 = new MPlayableCardDTO();
        assertThat(mPlayableCardDTO1).isNotEqualTo(mPlayableCardDTO2);
        mPlayableCardDTO2.setId(mPlayableCardDTO1.getId());
        assertThat(mPlayableCardDTO1).isEqualTo(mPlayableCardDTO2);
        mPlayableCardDTO2.setId(2L);
        assertThat(mPlayableCardDTO1).isNotEqualTo(mPlayableCardDTO2);
        mPlayableCardDTO1.setId(null);
        assertThat(mPlayableCardDTO1).isNotEqualTo(mPlayableCardDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(mPlayableCardMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(mPlayableCardMapper.fromId(null)).isNull();
    }
}
