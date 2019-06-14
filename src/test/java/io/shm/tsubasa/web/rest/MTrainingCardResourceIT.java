package io.shm.tsubasa.web.rest;

import io.shm.tsubasa.TsubasaApp;
import io.shm.tsubasa.domain.MTrainingCard;
import io.shm.tsubasa.domain.MCardThumbnailAssets;
import io.shm.tsubasa.repository.MTrainingCardRepository;
import io.shm.tsubasa.service.MTrainingCardService;
import io.shm.tsubasa.service.dto.MTrainingCardDTO;
import io.shm.tsubasa.service.mapper.MTrainingCardMapper;
import io.shm.tsubasa.web.rest.errors.ExceptionTranslator;
import io.shm.tsubasa.service.dto.MTrainingCardCriteria;
import io.shm.tsubasa.service.MTrainingCardQueryService;

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
 * Integration tests for the {@Link MTrainingCardResource} REST controller.
 */
@SpringBootTest(classes = TsubasaApp.class)
public class MTrainingCardResourceIT {

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_SHORT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_SHORT_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPTION = "BBBBBBBBBB";

    private static final Integer DEFAULT_RARITY = 1;
    private static final Integer UPDATED_RARITY = 2;

    private static final Integer DEFAULT_ATTRIBUTE = 1;
    private static final Integer UPDATED_ATTRIBUTE = 2;

    private static final Integer DEFAULT_GAIN_EXP = 1;
    private static final Integer UPDATED_GAIN_EXP = 2;

    private static final Integer DEFAULT_COIN = 1;
    private static final Integer UPDATED_COIN = 2;

    private static final Integer DEFAULT_SELL_MEDAL_ID = 1;
    private static final Integer UPDATED_SELL_MEDAL_ID = 2;

    private static final Integer DEFAULT_PLUS_DRIBBLE = 1;
    private static final Integer UPDATED_PLUS_DRIBBLE = 2;

    private static final Integer DEFAULT_PLUS_SHOOT = 1;
    private static final Integer UPDATED_PLUS_SHOOT = 2;

    private static final Integer DEFAULT_PLUS_PASS = 1;
    private static final Integer UPDATED_PLUS_PASS = 2;

    private static final Integer DEFAULT_PLUS_TACKLE = 1;
    private static final Integer UPDATED_PLUS_TACKLE = 2;

    private static final Integer DEFAULT_PLUS_BLOCK = 1;
    private static final Integer UPDATED_PLUS_BLOCK = 2;

    private static final Integer DEFAULT_PLUS_INTERCEPT = 1;
    private static final Integer UPDATED_PLUS_INTERCEPT = 2;

    private static final Integer DEFAULT_PLUS_SPEED = 1;
    private static final Integer UPDATED_PLUS_SPEED = 2;

    private static final Integer DEFAULT_PLUS_POWER = 1;
    private static final Integer UPDATED_PLUS_POWER = 2;

    private static final Integer DEFAULT_PLUS_TECHNIQUE = 1;
    private static final Integer UPDATED_PLUS_TECHNIQUE = 2;

    private static final Integer DEFAULT_PLUS_PUNCHING = 1;
    private static final Integer UPDATED_PLUS_PUNCHING = 2;

    private static final Integer DEFAULT_PLUS_CATCHING = 1;
    private static final Integer UPDATED_PLUS_CATCHING = 2;

    private static final Integer DEFAULT_THUMBNAIL_ASSETS_ID = 1;
    private static final Integer UPDATED_THUMBNAIL_ASSETS_ID = 2;

    private static final Integer DEFAULT_CARD_ILLUST_ASSETS_ID = 1;
    private static final Integer UPDATED_CARD_ILLUST_ASSETS_ID = 2;

    @Autowired
    private MTrainingCardRepository mTrainingCardRepository;

    @Autowired
    private MTrainingCardMapper mTrainingCardMapper;

    @Autowired
    private MTrainingCardService mTrainingCardService;

    @Autowired
    private MTrainingCardQueryService mTrainingCardQueryService;

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

    private MockMvc restMTrainingCardMockMvc;

    private MTrainingCard mTrainingCard;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final MTrainingCardResource mTrainingCardResource = new MTrainingCardResource(mTrainingCardService, mTrainingCardQueryService);
        this.restMTrainingCardMockMvc = MockMvcBuilders.standaloneSetup(mTrainingCardResource)
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
    public static MTrainingCard createEntity(EntityManager em) {
        MTrainingCard mTrainingCard = new MTrainingCard()
            .name(DEFAULT_NAME)
            .shortName(DEFAULT_SHORT_NAME)
            .description(DEFAULT_DESCRIPTION)
            .rarity(DEFAULT_RARITY)
            .attribute(DEFAULT_ATTRIBUTE)
            .gainExp(DEFAULT_GAIN_EXP)
            .coin(DEFAULT_COIN)
            .sellMedalId(DEFAULT_SELL_MEDAL_ID)
            .plusDribble(DEFAULT_PLUS_DRIBBLE)
            .plusShoot(DEFAULT_PLUS_SHOOT)
            .plusPass(DEFAULT_PLUS_PASS)
            .plusTackle(DEFAULT_PLUS_TACKLE)
            .plusBlock(DEFAULT_PLUS_BLOCK)
            .plusIntercept(DEFAULT_PLUS_INTERCEPT)
            .plusSpeed(DEFAULT_PLUS_SPEED)
            .plusPower(DEFAULT_PLUS_POWER)
            .plusTechnique(DEFAULT_PLUS_TECHNIQUE)
            .plusPunching(DEFAULT_PLUS_PUNCHING)
            .plusCatching(DEFAULT_PLUS_CATCHING)
            .thumbnailAssetsId(DEFAULT_THUMBNAIL_ASSETS_ID)
            .cardIllustAssetsId(DEFAULT_CARD_ILLUST_ASSETS_ID);
        // Add required entity
        MCardThumbnailAssets mCardThumbnailAssets;
        if (TestUtil.findAll(em, MCardThumbnailAssets.class).isEmpty()) {
            mCardThumbnailAssets = MCardThumbnailAssetsResourceIT.createEntity(em);
            em.persist(mCardThumbnailAssets);
            em.flush();
        } else {
            mCardThumbnailAssets = TestUtil.findAll(em, MCardThumbnailAssets.class).get(0);
        }
        mTrainingCard.setMcardthumbnailassets(mCardThumbnailAssets);
        return mTrainingCard;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static MTrainingCard createUpdatedEntity(EntityManager em) {
        MTrainingCard mTrainingCard = new MTrainingCard()
            .name(UPDATED_NAME)
            .shortName(UPDATED_SHORT_NAME)
            .description(UPDATED_DESCRIPTION)
            .rarity(UPDATED_RARITY)
            .attribute(UPDATED_ATTRIBUTE)
            .gainExp(UPDATED_GAIN_EXP)
            .coin(UPDATED_COIN)
            .sellMedalId(UPDATED_SELL_MEDAL_ID)
            .plusDribble(UPDATED_PLUS_DRIBBLE)
            .plusShoot(UPDATED_PLUS_SHOOT)
            .plusPass(UPDATED_PLUS_PASS)
            .plusTackle(UPDATED_PLUS_TACKLE)
            .plusBlock(UPDATED_PLUS_BLOCK)
            .plusIntercept(UPDATED_PLUS_INTERCEPT)
            .plusSpeed(UPDATED_PLUS_SPEED)
            .plusPower(UPDATED_PLUS_POWER)
            .plusTechnique(UPDATED_PLUS_TECHNIQUE)
            .plusPunching(UPDATED_PLUS_PUNCHING)
            .plusCatching(UPDATED_PLUS_CATCHING)
            .thumbnailAssetsId(UPDATED_THUMBNAIL_ASSETS_ID)
            .cardIllustAssetsId(UPDATED_CARD_ILLUST_ASSETS_ID);
        // Add required entity
        MCardThumbnailAssets mCardThumbnailAssets;
        if (TestUtil.findAll(em, MCardThumbnailAssets.class).isEmpty()) {
            mCardThumbnailAssets = MCardThumbnailAssetsResourceIT.createUpdatedEntity(em);
            em.persist(mCardThumbnailAssets);
            em.flush();
        } else {
            mCardThumbnailAssets = TestUtil.findAll(em, MCardThumbnailAssets.class).get(0);
        }
        mTrainingCard.setMcardthumbnailassets(mCardThumbnailAssets);
        return mTrainingCard;
    }

    @BeforeEach
    public void initTest() {
        mTrainingCard = createEntity(em);
    }

    @Test
    @Transactional
    public void createMTrainingCard() throws Exception {
        int databaseSizeBeforeCreate = mTrainingCardRepository.findAll().size();

        // Create the MTrainingCard
        MTrainingCardDTO mTrainingCardDTO = mTrainingCardMapper.toDto(mTrainingCard);
        restMTrainingCardMockMvc.perform(post("/api/m-training-cards")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mTrainingCardDTO)))
            .andExpect(status().isCreated());

        // Validate the MTrainingCard in the database
        List<MTrainingCard> mTrainingCardList = mTrainingCardRepository.findAll();
        assertThat(mTrainingCardList).hasSize(databaseSizeBeforeCreate + 1);
        MTrainingCard testMTrainingCard = mTrainingCardList.get(mTrainingCardList.size() - 1);
        assertThat(testMTrainingCard.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testMTrainingCard.getShortName()).isEqualTo(DEFAULT_SHORT_NAME);
        assertThat(testMTrainingCard.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
        assertThat(testMTrainingCard.getRarity()).isEqualTo(DEFAULT_RARITY);
        assertThat(testMTrainingCard.getAttribute()).isEqualTo(DEFAULT_ATTRIBUTE);
        assertThat(testMTrainingCard.getGainExp()).isEqualTo(DEFAULT_GAIN_EXP);
        assertThat(testMTrainingCard.getCoin()).isEqualTo(DEFAULT_COIN);
        assertThat(testMTrainingCard.getSellMedalId()).isEqualTo(DEFAULT_SELL_MEDAL_ID);
        assertThat(testMTrainingCard.getPlusDribble()).isEqualTo(DEFAULT_PLUS_DRIBBLE);
        assertThat(testMTrainingCard.getPlusShoot()).isEqualTo(DEFAULT_PLUS_SHOOT);
        assertThat(testMTrainingCard.getPlusPass()).isEqualTo(DEFAULT_PLUS_PASS);
        assertThat(testMTrainingCard.getPlusTackle()).isEqualTo(DEFAULT_PLUS_TACKLE);
        assertThat(testMTrainingCard.getPlusBlock()).isEqualTo(DEFAULT_PLUS_BLOCK);
        assertThat(testMTrainingCard.getPlusIntercept()).isEqualTo(DEFAULT_PLUS_INTERCEPT);
        assertThat(testMTrainingCard.getPlusSpeed()).isEqualTo(DEFAULT_PLUS_SPEED);
        assertThat(testMTrainingCard.getPlusPower()).isEqualTo(DEFAULT_PLUS_POWER);
        assertThat(testMTrainingCard.getPlusTechnique()).isEqualTo(DEFAULT_PLUS_TECHNIQUE);
        assertThat(testMTrainingCard.getPlusPunching()).isEqualTo(DEFAULT_PLUS_PUNCHING);
        assertThat(testMTrainingCard.getPlusCatching()).isEqualTo(DEFAULT_PLUS_CATCHING);
        assertThat(testMTrainingCard.getThumbnailAssetsId()).isEqualTo(DEFAULT_THUMBNAIL_ASSETS_ID);
        assertThat(testMTrainingCard.getCardIllustAssetsId()).isEqualTo(DEFAULT_CARD_ILLUST_ASSETS_ID);
    }

    @Test
    @Transactional
    public void createMTrainingCardWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = mTrainingCardRepository.findAll().size();

        // Create the MTrainingCard with an existing ID
        mTrainingCard.setId(1L);
        MTrainingCardDTO mTrainingCardDTO = mTrainingCardMapper.toDto(mTrainingCard);

        // An entity with an existing ID cannot be created, so this API call must fail
        restMTrainingCardMockMvc.perform(post("/api/m-training-cards")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mTrainingCardDTO)))
            .andExpect(status().isBadRequest());

        // Validate the MTrainingCard in the database
        List<MTrainingCard> mTrainingCardList = mTrainingCardRepository.findAll();
        assertThat(mTrainingCardList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkRarityIsRequired() throws Exception {
        int databaseSizeBeforeTest = mTrainingCardRepository.findAll().size();
        // set the field null
        mTrainingCard.setRarity(null);

        // Create the MTrainingCard, which fails.
        MTrainingCardDTO mTrainingCardDTO = mTrainingCardMapper.toDto(mTrainingCard);

        restMTrainingCardMockMvc.perform(post("/api/m-training-cards")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mTrainingCardDTO)))
            .andExpect(status().isBadRequest());

        List<MTrainingCard> mTrainingCardList = mTrainingCardRepository.findAll();
        assertThat(mTrainingCardList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkAttributeIsRequired() throws Exception {
        int databaseSizeBeforeTest = mTrainingCardRepository.findAll().size();
        // set the field null
        mTrainingCard.setAttribute(null);

        // Create the MTrainingCard, which fails.
        MTrainingCardDTO mTrainingCardDTO = mTrainingCardMapper.toDto(mTrainingCard);

        restMTrainingCardMockMvc.perform(post("/api/m-training-cards")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mTrainingCardDTO)))
            .andExpect(status().isBadRequest());

        List<MTrainingCard> mTrainingCardList = mTrainingCardRepository.findAll();
        assertThat(mTrainingCardList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkGainExpIsRequired() throws Exception {
        int databaseSizeBeforeTest = mTrainingCardRepository.findAll().size();
        // set the field null
        mTrainingCard.setGainExp(null);

        // Create the MTrainingCard, which fails.
        MTrainingCardDTO mTrainingCardDTO = mTrainingCardMapper.toDto(mTrainingCard);

        restMTrainingCardMockMvc.perform(post("/api/m-training-cards")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mTrainingCardDTO)))
            .andExpect(status().isBadRequest());

        List<MTrainingCard> mTrainingCardList = mTrainingCardRepository.findAll();
        assertThat(mTrainingCardList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkCoinIsRequired() throws Exception {
        int databaseSizeBeforeTest = mTrainingCardRepository.findAll().size();
        // set the field null
        mTrainingCard.setCoin(null);

        // Create the MTrainingCard, which fails.
        MTrainingCardDTO mTrainingCardDTO = mTrainingCardMapper.toDto(mTrainingCard);

        restMTrainingCardMockMvc.perform(post("/api/m-training-cards")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mTrainingCardDTO)))
            .andExpect(status().isBadRequest());

        List<MTrainingCard> mTrainingCardList = mTrainingCardRepository.findAll();
        assertThat(mTrainingCardList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkPlusDribbleIsRequired() throws Exception {
        int databaseSizeBeforeTest = mTrainingCardRepository.findAll().size();
        // set the field null
        mTrainingCard.setPlusDribble(null);

        // Create the MTrainingCard, which fails.
        MTrainingCardDTO mTrainingCardDTO = mTrainingCardMapper.toDto(mTrainingCard);

        restMTrainingCardMockMvc.perform(post("/api/m-training-cards")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mTrainingCardDTO)))
            .andExpect(status().isBadRequest());

        List<MTrainingCard> mTrainingCardList = mTrainingCardRepository.findAll();
        assertThat(mTrainingCardList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkPlusShootIsRequired() throws Exception {
        int databaseSizeBeforeTest = mTrainingCardRepository.findAll().size();
        // set the field null
        mTrainingCard.setPlusShoot(null);

        // Create the MTrainingCard, which fails.
        MTrainingCardDTO mTrainingCardDTO = mTrainingCardMapper.toDto(mTrainingCard);

        restMTrainingCardMockMvc.perform(post("/api/m-training-cards")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mTrainingCardDTO)))
            .andExpect(status().isBadRequest());

        List<MTrainingCard> mTrainingCardList = mTrainingCardRepository.findAll();
        assertThat(mTrainingCardList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkPlusPassIsRequired() throws Exception {
        int databaseSizeBeforeTest = mTrainingCardRepository.findAll().size();
        // set the field null
        mTrainingCard.setPlusPass(null);

        // Create the MTrainingCard, which fails.
        MTrainingCardDTO mTrainingCardDTO = mTrainingCardMapper.toDto(mTrainingCard);

        restMTrainingCardMockMvc.perform(post("/api/m-training-cards")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mTrainingCardDTO)))
            .andExpect(status().isBadRequest());

        List<MTrainingCard> mTrainingCardList = mTrainingCardRepository.findAll();
        assertThat(mTrainingCardList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkPlusTackleIsRequired() throws Exception {
        int databaseSizeBeforeTest = mTrainingCardRepository.findAll().size();
        // set the field null
        mTrainingCard.setPlusTackle(null);

        // Create the MTrainingCard, which fails.
        MTrainingCardDTO mTrainingCardDTO = mTrainingCardMapper.toDto(mTrainingCard);

        restMTrainingCardMockMvc.perform(post("/api/m-training-cards")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mTrainingCardDTO)))
            .andExpect(status().isBadRequest());

        List<MTrainingCard> mTrainingCardList = mTrainingCardRepository.findAll();
        assertThat(mTrainingCardList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkPlusBlockIsRequired() throws Exception {
        int databaseSizeBeforeTest = mTrainingCardRepository.findAll().size();
        // set the field null
        mTrainingCard.setPlusBlock(null);

        // Create the MTrainingCard, which fails.
        MTrainingCardDTO mTrainingCardDTO = mTrainingCardMapper.toDto(mTrainingCard);

        restMTrainingCardMockMvc.perform(post("/api/m-training-cards")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mTrainingCardDTO)))
            .andExpect(status().isBadRequest());

        List<MTrainingCard> mTrainingCardList = mTrainingCardRepository.findAll();
        assertThat(mTrainingCardList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkPlusInterceptIsRequired() throws Exception {
        int databaseSizeBeforeTest = mTrainingCardRepository.findAll().size();
        // set the field null
        mTrainingCard.setPlusIntercept(null);

        // Create the MTrainingCard, which fails.
        MTrainingCardDTO mTrainingCardDTO = mTrainingCardMapper.toDto(mTrainingCard);

        restMTrainingCardMockMvc.perform(post("/api/m-training-cards")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mTrainingCardDTO)))
            .andExpect(status().isBadRequest());

        List<MTrainingCard> mTrainingCardList = mTrainingCardRepository.findAll();
        assertThat(mTrainingCardList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkPlusSpeedIsRequired() throws Exception {
        int databaseSizeBeforeTest = mTrainingCardRepository.findAll().size();
        // set the field null
        mTrainingCard.setPlusSpeed(null);

        // Create the MTrainingCard, which fails.
        MTrainingCardDTO mTrainingCardDTO = mTrainingCardMapper.toDto(mTrainingCard);

        restMTrainingCardMockMvc.perform(post("/api/m-training-cards")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mTrainingCardDTO)))
            .andExpect(status().isBadRequest());

        List<MTrainingCard> mTrainingCardList = mTrainingCardRepository.findAll();
        assertThat(mTrainingCardList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkPlusPowerIsRequired() throws Exception {
        int databaseSizeBeforeTest = mTrainingCardRepository.findAll().size();
        // set the field null
        mTrainingCard.setPlusPower(null);

        // Create the MTrainingCard, which fails.
        MTrainingCardDTO mTrainingCardDTO = mTrainingCardMapper.toDto(mTrainingCard);

        restMTrainingCardMockMvc.perform(post("/api/m-training-cards")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mTrainingCardDTO)))
            .andExpect(status().isBadRequest());

        List<MTrainingCard> mTrainingCardList = mTrainingCardRepository.findAll();
        assertThat(mTrainingCardList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkPlusTechniqueIsRequired() throws Exception {
        int databaseSizeBeforeTest = mTrainingCardRepository.findAll().size();
        // set the field null
        mTrainingCard.setPlusTechnique(null);

        // Create the MTrainingCard, which fails.
        MTrainingCardDTO mTrainingCardDTO = mTrainingCardMapper.toDto(mTrainingCard);

        restMTrainingCardMockMvc.perform(post("/api/m-training-cards")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mTrainingCardDTO)))
            .andExpect(status().isBadRequest());

        List<MTrainingCard> mTrainingCardList = mTrainingCardRepository.findAll();
        assertThat(mTrainingCardList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkPlusPunchingIsRequired() throws Exception {
        int databaseSizeBeforeTest = mTrainingCardRepository.findAll().size();
        // set the field null
        mTrainingCard.setPlusPunching(null);

        // Create the MTrainingCard, which fails.
        MTrainingCardDTO mTrainingCardDTO = mTrainingCardMapper.toDto(mTrainingCard);

        restMTrainingCardMockMvc.perform(post("/api/m-training-cards")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mTrainingCardDTO)))
            .andExpect(status().isBadRequest());

        List<MTrainingCard> mTrainingCardList = mTrainingCardRepository.findAll();
        assertThat(mTrainingCardList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkPlusCatchingIsRequired() throws Exception {
        int databaseSizeBeforeTest = mTrainingCardRepository.findAll().size();
        // set the field null
        mTrainingCard.setPlusCatching(null);

        // Create the MTrainingCard, which fails.
        MTrainingCardDTO mTrainingCardDTO = mTrainingCardMapper.toDto(mTrainingCard);

        restMTrainingCardMockMvc.perform(post("/api/m-training-cards")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mTrainingCardDTO)))
            .andExpect(status().isBadRequest());

        List<MTrainingCard> mTrainingCardList = mTrainingCardRepository.findAll();
        assertThat(mTrainingCardList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkThumbnailAssetsIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = mTrainingCardRepository.findAll().size();
        // set the field null
        mTrainingCard.setThumbnailAssetsId(null);

        // Create the MTrainingCard, which fails.
        MTrainingCardDTO mTrainingCardDTO = mTrainingCardMapper.toDto(mTrainingCard);

        restMTrainingCardMockMvc.perform(post("/api/m-training-cards")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mTrainingCardDTO)))
            .andExpect(status().isBadRequest());

        List<MTrainingCard> mTrainingCardList = mTrainingCardRepository.findAll();
        assertThat(mTrainingCardList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkCardIllustAssetsIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = mTrainingCardRepository.findAll().size();
        // set the field null
        mTrainingCard.setCardIllustAssetsId(null);

        // Create the MTrainingCard, which fails.
        MTrainingCardDTO mTrainingCardDTO = mTrainingCardMapper.toDto(mTrainingCard);

        restMTrainingCardMockMvc.perform(post("/api/m-training-cards")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mTrainingCardDTO)))
            .andExpect(status().isBadRequest());

        List<MTrainingCard> mTrainingCardList = mTrainingCardRepository.findAll();
        assertThat(mTrainingCardList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllMTrainingCards() throws Exception {
        // Initialize the database
        mTrainingCardRepository.saveAndFlush(mTrainingCard);

        // Get all the mTrainingCardList
        restMTrainingCardMockMvc.perform(get("/api/m-training-cards?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(mTrainingCard.getId().intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME.toString())))
            .andExpect(jsonPath("$.[*].shortName").value(hasItem(DEFAULT_SHORT_NAME.toString())))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION.toString())))
            .andExpect(jsonPath("$.[*].rarity").value(hasItem(DEFAULT_RARITY)))
            .andExpect(jsonPath("$.[*].attribute").value(hasItem(DEFAULT_ATTRIBUTE)))
            .andExpect(jsonPath("$.[*].gainExp").value(hasItem(DEFAULT_GAIN_EXP)))
            .andExpect(jsonPath("$.[*].coin").value(hasItem(DEFAULT_COIN)))
            .andExpect(jsonPath("$.[*].sellMedalId").value(hasItem(DEFAULT_SELL_MEDAL_ID)))
            .andExpect(jsonPath("$.[*].plusDribble").value(hasItem(DEFAULT_PLUS_DRIBBLE)))
            .andExpect(jsonPath("$.[*].plusShoot").value(hasItem(DEFAULT_PLUS_SHOOT)))
            .andExpect(jsonPath("$.[*].plusPass").value(hasItem(DEFAULT_PLUS_PASS)))
            .andExpect(jsonPath("$.[*].plusTackle").value(hasItem(DEFAULT_PLUS_TACKLE)))
            .andExpect(jsonPath("$.[*].plusBlock").value(hasItem(DEFAULT_PLUS_BLOCK)))
            .andExpect(jsonPath("$.[*].plusIntercept").value(hasItem(DEFAULT_PLUS_INTERCEPT)))
            .andExpect(jsonPath("$.[*].plusSpeed").value(hasItem(DEFAULT_PLUS_SPEED)))
            .andExpect(jsonPath("$.[*].plusPower").value(hasItem(DEFAULT_PLUS_POWER)))
            .andExpect(jsonPath("$.[*].plusTechnique").value(hasItem(DEFAULT_PLUS_TECHNIQUE)))
            .andExpect(jsonPath("$.[*].plusPunching").value(hasItem(DEFAULT_PLUS_PUNCHING)))
            .andExpect(jsonPath("$.[*].plusCatching").value(hasItem(DEFAULT_PLUS_CATCHING)))
            .andExpect(jsonPath("$.[*].thumbnailAssetsId").value(hasItem(DEFAULT_THUMBNAIL_ASSETS_ID)))
            .andExpect(jsonPath("$.[*].cardIllustAssetsId").value(hasItem(DEFAULT_CARD_ILLUST_ASSETS_ID)));
    }
    
    @Test
    @Transactional
    public void getMTrainingCard() throws Exception {
        // Initialize the database
        mTrainingCardRepository.saveAndFlush(mTrainingCard);

        // Get the mTrainingCard
        restMTrainingCardMockMvc.perform(get("/api/m-training-cards/{id}", mTrainingCard.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(mTrainingCard.getId().intValue()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME.toString()))
            .andExpect(jsonPath("$.shortName").value(DEFAULT_SHORT_NAME.toString()))
            .andExpect(jsonPath("$.description").value(DEFAULT_DESCRIPTION.toString()))
            .andExpect(jsonPath("$.rarity").value(DEFAULT_RARITY))
            .andExpect(jsonPath("$.attribute").value(DEFAULT_ATTRIBUTE))
            .andExpect(jsonPath("$.gainExp").value(DEFAULT_GAIN_EXP))
            .andExpect(jsonPath("$.coin").value(DEFAULT_COIN))
            .andExpect(jsonPath("$.sellMedalId").value(DEFAULT_SELL_MEDAL_ID))
            .andExpect(jsonPath("$.plusDribble").value(DEFAULT_PLUS_DRIBBLE))
            .andExpect(jsonPath("$.plusShoot").value(DEFAULT_PLUS_SHOOT))
            .andExpect(jsonPath("$.plusPass").value(DEFAULT_PLUS_PASS))
            .andExpect(jsonPath("$.plusTackle").value(DEFAULT_PLUS_TACKLE))
            .andExpect(jsonPath("$.plusBlock").value(DEFAULT_PLUS_BLOCK))
            .andExpect(jsonPath("$.plusIntercept").value(DEFAULT_PLUS_INTERCEPT))
            .andExpect(jsonPath("$.plusSpeed").value(DEFAULT_PLUS_SPEED))
            .andExpect(jsonPath("$.plusPower").value(DEFAULT_PLUS_POWER))
            .andExpect(jsonPath("$.plusTechnique").value(DEFAULT_PLUS_TECHNIQUE))
            .andExpect(jsonPath("$.plusPunching").value(DEFAULT_PLUS_PUNCHING))
            .andExpect(jsonPath("$.plusCatching").value(DEFAULT_PLUS_CATCHING))
            .andExpect(jsonPath("$.thumbnailAssetsId").value(DEFAULT_THUMBNAIL_ASSETS_ID))
            .andExpect(jsonPath("$.cardIllustAssetsId").value(DEFAULT_CARD_ILLUST_ASSETS_ID));
    }

    @Test
    @Transactional
    public void getAllMTrainingCardsByRarityIsEqualToSomething() throws Exception {
        // Initialize the database
        mTrainingCardRepository.saveAndFlush(mTrainingCard);

        // Get all the mTrainingCardList where rarity equals to DEFAULT_RARITY
        defaultMTrainingCardShouldBeFound("rarity.equals=" + DEFAULT_RARITY);

        // Get all the mTrainingCardList where rarity equals to UPDATED_RARITY
        defaultMTrainingCardShouldNotBeFound("rarity.equals=" + UPDATED_RARITY);
    }

    @Test
    @Transactional
    public void getAllMTrainingCardsByRarityIsInShouldWork() throws Exception {
        // Initialize the database
        mTrainingCardRepository.saveAndFlush(mTrainingCard);

        // Get all the mTrainingCardList where rarity in DEFAULT_RARITY or UPDATED_RARITY
        defaultMTrainingCardShouldBeFound("rarity.in=" + DEFAULT_RARITY + "," + UPDATED_RARITY);

        // Get all the mTrainingCardList where rarity equals to UPDATED_RARITY
        defaultMTrainingCardShouldNotBeFound("rarity.in=" + UPDATED_RARITY);
    }

    @Test
    @Transactional
    public void getAllMTrainingCardsByRarityIsNullOrNotNull() throws Exception {
        // Initialize the database
        mTrainingCardRepository.saveAndFlush(mTrainingCard);

        // Get all the mTrainingCardList where rarity is not null
        defaultMTrainingCardShouldBeFound("rarity.specified=true");

        // Get all the mTrainingCardList where rarity is null
        defaultMTrainingCardShouldNotBeFound("rarity.specified=false");
    }

    @Test
    @Transactional
    public void getAllMTrainingCardsByRarityIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mTrainingCardRepository.saveAndFlush(mTrainingCard);

        // Get all the mTrainingCardList where rarity greater than or equals to DEFAULT_RARITY
        defaultMTrainingCardShouldBeFound("rarity.greaterOrEqualThan=" + DEFAULT_RARITY);

        // Get all the mTrainingCardList where rarity greater than or equals to UPDATED_RARITY
        defaultMTrainingCardShouldNotBeFound("rarity.greaterOrEqualThan=" + UPDATED_RARITY);
    }

    @Test
    @Transactional
    public void getAllMTrainingCardsByRarityIsLessThanSomething() throws Exception {
        // Initialize the database
        mTrainingCardRepository.saveAndFlush(mTrainingCard);

        // Get all the mTrainingCardList where rarity less than or equals to DEFAULT_RARITY
        defaultMTrainingCardShouldNotBeFound("rarity.lessThan=" + DEFAULT_RARITY);

        // Get all the mTrainingCardList where rarity less than or equals to UPDATED_RARITY
        defaultMTrainingCardShouldBeFound("rarity.lessThan=" + UPDATED_RARITY);
    }


    @Test
    @Transactional
    public void getAllMTrainingCardsByAttributeIsEqualToSomething() throws Exception {
        // Initialize the database
        mTrainingCardRepository.saveAndFlush(mTrainingCard);

        // Get all the mTrainingCardList where attribute equals to DEFAULT_ATTRIBUTE
        defaultMTrainingCardShouldBeFound("attribute.equals=" + DEFAULT_ATTRIBUTE);

        // Get all the mTrainingCardList where attribute equals to UPDATED_ATTRIBUTE
        defaultMTrainingCardShouldNotBeFound("attribute.equals=" + UPDATED_ATTRIBUTE);
    }

    @Test
    @Transactional
    public void getAllMTrainingCardsByAttributeIsInShouldWork() throws Exception {
        // Initialize the database
        mTrainingCardRepository.saveAndFlush(mTrainingCard);

        // Get all the mTrainingCardList where attribute in DEFAULT_ATTRIBUTE or UPDATED_ATTRIBUTE
        defaultMTrainingCardShouldBeFound("attribute.in=" + DEFAULT_ATTRIBUTE + "," + UPDATED_ATTRIBUTE);

        // Get all the mTrainingCardList where attribute equals to UPDATED_ATTRIBUTE
        defaultMTrainingCardShouldNotBeFound("attribute.in=" + UPDATED_ATTRIBUTE);
    }

    @Test
    @Transactional
    public void getAllMTrainingCardsByAttributeIsNullOrNotNull() throws Exception {
        // Initialize the database
        mTrainingCardRepository.saveAndFlush(mTrainingCard);

        // Get all the mTrainingCardList where attribute is not null
        defaultMTrainingCardShouldBeFound("attribute.specified=true");

        // Get all the mTrainingCardList where attribute is null
        defaultMTrainingCardShouldNotBeFound("attribute.specified=false");
    }

    @Test
    @Transactional
    public void getAllMTrainingCardsByAttributeIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mTrainingCardRepository.saveAndFlush(mTrainingCard);

        // Get all the mTrainingCardList where attribute greater than or equals to DEFAULT_ATTRIBUTE
        defaultMTrainingCardShouldBeFound("attribute.greaterOrEqualThan=" + DEFAULT_ATTRIBUTE);

        // Get all the mTrainingCardList where attribute greater than or equals to UPDATED_ATTRIBUTE
        defaultMTrainingCardShouldNotBeFound("attribute.greaterOrEqualThan=" + UPDATED_ATTRIBUTE);
    }

    @Test
    @Transactional
    public void getAllMTrainingCardsByAttributeIsLessThanSomething() throws Exception {
        // Initialize the database
        mTrainingCardRepository.saveAndFlush(mTrainingCard);

        // Get all the mTrainingCardList where attribute less than or equals to DEFAULT_ATTRIBUTE
        defaultMTrainingCardShouldNotBeFound("attribute.lessThan=" + DEFAULT_ATTRIBUTE);

        // Get all the mTrainingCardList where attribute less than or equals to UPDATED_ATTRIBUTE
        defaultMTrainingCardShouldBeFound("attribute.lessThan=" + UPDATED_ATTRIBUTE);
    }


    @Test
    @Transactional
    public void getAllMTrainingCardsByGainExpIsEqualToSomething() throws Exception {
        // Initialize the database
        mTrainingCardRepository.saveAndFlush(mTrainingCard);

        // Get all the mTrainingCardList where gainExp equals to DEFAULT_GAIN_EXP
        defaultMTrainingCardShouldBeFound("gainExp.equals=" + DEFAULT_GAIN_EXP);

        // Get all the mTrainingCardList where gainExp equals to UPDATED_GAIN_EXP
        defaultMTrainingCardShouldNotBeFound("gainExp.equals=" + UPDATED_GAIN_EXP);
    }

    @Test
    @Transactional
    public void getAllMTrainingCardsByGainExpIsInShouldWork() throws Exception {
        // Initialize the database
        mTrainingCardRepository.saveAndFlush(mTrainingCard);

        // Get all the mTrainingCardList where gainExp in DEFAULT_GAIN_EXP or UPDATED_GAIN_EXP
        defaultMTrainingCardShouldBeFound("gainExp.in=" + DEFAULT_GAIN_EXP + "," + UPDATED_GAIN_EXP);

        // Get all the mTrainingCardList where gainExp equals to UPDATED_GAIN_EXP
        defaultMTrainingCardShouldNotBeFound("gainExp.in=" + UPDATED_GAIN_EXP);
    }

    @Test
    @Transactional
    public void getAllMTrainingCardsByGainExpIsNullOrNotNull() throws Exception {
        // Initialize the database
        mTrainingCardRepository.saveAndFlush(mTrainingCard);

        // Get all the mTrainingCardList where gainExp is not null
        defaultMTrainingCardShouldBeFound("gainExp.specified=true");

        // Get all the mTrainingCardList where gainExp is null
        defaultMTrainingCardShouldNotBeFound("gainExp.specified=false");
    }

    @Test
    @Transactional
    public void getAllMTrainingCardsByGainExpIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mTrainingCardRepository.saveAndFlush(mTrainingCard);

        // Get all the mTrainingCardList where gainExp greater than or equals to DEFAULT_GAIN_EXP
        defaultMTrainingCardShouldBeFound("gainExp.greaterOrEqualThan=" + DEFAULT_GAIN_EXP);

        // Get all the mTrainingCardList where gainExp greater than or equals to UPDATED_GAIN_EXP
        defaultMTrainingCardShouldNotBeFound("gainExp.greaterOrEqualThan=" + UPDATED_GAIN_EXP);
    }

    @Test
    @Transactional
    public void getAllMTrainingCardsByGainExpIsLessThanSomething() throws Exception {
        // Initialize the database
        mTrainingCardRepository.saveAndFlush(mTrainingCard);

        // Get all the mTrainingCardList where gainExp less than or equals to DEFAULT_GAIN_EXP
        defaultMTrainingCardShouldNotBeFound("gainExp.lessThan=" + DEFAULT_GAIN_EXP);

        // Get all the mTrainingCardList where gainExp less than or equals to UPDATED_GAIN_EXP
        defaultMTrainingCardShouldBeFound("gainExp.lessThan=" + UPDATED_GAIN_EXP);
    }


    @Test
    @Transactional
    public void getAllMTrainingCardsByCoinIsEqualToSomething() throws Exception {
        // Initialize the database
        mTrainingCardRepository.saveAndFlush(mTrainingCard);

        // Get all the mTrainingCardList where coin equals to DEFAULT_COIN
        defaultMTrainingCardShouldBeFound("coin.equals=" + DEFAULT_COIN);

        // Get all the mTrainingCardList where coin equals to UPDATED_COIN
        defaultMTrainingCardShouldNotBeFound("coin.equals=" + UPDATED_COIN);
    }

    @Test
    @Transactional
    public void getAllMTrainingCardsByCoinIsInShouldWork() throws Exception {
        // Initialize the database
        mTrainingCardRepository.saveAndFlush(mTrainingCard);

        // Get all the mTrainingCardList where coin in DEFAULT_COIN or UPDATED_COIN
        defaultMTrainingCardShouldBeFound("coin.in=" + DEFAULT_COIN + "," + UPDATED_COIN);

        // Get all the mTrainingCardList where coin equals to UPDATED_COIN
        defaultMTrainingCardShouldNotBeFound("coin.in=" + UPDATED_COIN);
    }

    @Test
    @Transactional
    public void getAllMTrainingCardsByCoinIsNullOrNotNull() throws Exception {
        // Initialize the database
        mTrainingCardRepository.saveAndFlush(mTrainingCard);

        // Get all the mTrainingCardList where coin is not null
        defaultMTrainingCardShouldBeFound("coin.specified=true");

        // Get all the mTrainingCardList where coin is null
        defaultMTrainingCardShouldNotBeFound("coin.specified=false");
    }

    @Test
    @Transactional
    public void getAllMTrainingCardsByCoinIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mTrainingCardRepository.saveAndFlush(mTrainingCard);

        // Get all the mTrainingCardList where coin greater than or equals to DEFAULT_COIN
        defaultMTrainingCardShouldBeFound("coin.greaterOrEqualThan=" + DEFAULT_COIN);

        // Get all the mTrainingCardList where coin greater than or equals to UPDATED_COIN
        defaultMTrainingCardShouldNotBeFound("coin.greaterOrEqualThan=" + UPDATED_COIN);
    }

    @Test
    @Transactional
    public void getAllMTrainingCardsByCoinIsLessThanSomething() throws Exception {
        // Initialize the database
        mTrainingCardRepository.saveAndFlush(mTrainingCard);

        // Get all the mTrainingCardList where coin less than or equals to DEFAULT_COIN
        defaultMTrainingCardShouldNotBeFound("coin.lessThan=" + DEFAULT_COIN);

        // Get all the mTrainingCardList where coin less than or equals to UPDATED_COIN
        defaultMTrainingCardShouldBeFound("coin.lessThan=" + UPDATED_COIN);
    }


    @Test
    @Transactional
    public void getAllMTrainingCardsBySellMedalIdIsEqualToSomething() throws Exception {
        // Initialize the database
        mTrainingCardRepository.saveAndFlush(mTrainingCard);

        // Get all the mTrainingCardList where sellMedalId equals to DEFAULT_SELL_MEDAL_ID
        defaultMTrainingCardShouldBeFound("sellMedalId.equals=" + DEFAULT_SELL_MEDAL_ID);

        // Get all the mTrainingCardList where sellMedalId equals to UPDATED_SELL_MEDAL_ID
        defaultMTrainingCardShouldNotBeFound("sellMedalId.equals=" + UPDATED_SELL_MEDAL_ID);
    }

    @Test
    @Transactional
    public void getAllMTrainingCardsBySellMedalIdIsInShouldWork() throws Exception {
        // Initialize the database
        mTrainingCardRepository.saveAndFlush(mTrainingCard);

        // Get all the mTrainingCardList where sellMedalId in DEFAULT_SELL_MEDAL_ID or UPDATED_SELL_MEDAL_ID
        defaultMTrainingCardShouldBeFound("sellMedalId.in=" + DEFAULT_SELL_MEDAL_ID + "," + UPDATED_SELL_MEDAL_ID);

        // Get all the mTrainingCardList where sellMedalId equals to UPDATED_SELL_MEDAL_ID
        defaultMTrainingCardShouldNotBeFound("sellMedalId.in=" + UPDATED_SELL_MEDAL_ID);
    }

    @Test
    @Transactional
    public void getAllMTrainingCardsBySellMedalIdIsNullOrNotNull() throws Exception {
        // Initialize the database
        mTrainingCardRepository.saveAndFlush(mTrainingCard);

        // Get all the mTrainingCardList where sellMedalId is not null
        defaultMTrainingCardShouldBeFound("sellMedalId.specified=true");

        // Get all the mTrainingCardList where sellMedalId is null
        defaultMTrainingCardShouldNotBeFound("sellMedalId.specified=false");
    }

    @Test
    @Transactional
    public void getAllMTrainingCardsBySellMedalIdIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mTrainingCardRepository.saveAndFlush(mTrainingCard);

        // Get all the mTrainingCardList where sellMedalId greater than or equals to DEFAULT_SELL_MEDAL_ID
        defaultMTrainingCardShouldBeFound("sellMedalId.greaterOrEqualThan=" + DEFAULT_SELL_MEDAL_ID);

        // Get all the mTrainingCardList where sellMedalId greater than or equals to UPDATED_SELL_MEDAL_ID
        defaultMTrainingCardShouldNotBeFound("sellMedalId.greaterOrEqualThan=" + UPDATED_SELL_MEDAL_ID);
    }

    @Test
    @Transactional
    public void getAllMTrainingCardsBySellMedalIdIsLessThanSomething() throws Exception {
        // Initialize the database
        mTrainingCardRepository.saveAndFlush(mTrainingCard);

        // Get all the mTrainingCardList where sellMedalId less than or equals to DEFAULT_SELL_MEDAL_ID
        defaultMTrainingCardShouldNotBeFound("sellMedalId.lessThan=" + DEFAULT_SELL_MEDAL_ID);

        // Get all the mTrainingCardList where sellMedalId less than or equals to UPDATED_SELL_MEDAL_ID
        defaultMTrainingCardShouldBeFound("sellMedalId.lessThan=" + UPDATED_SELL_MEDAL_ID);
    }


    @Test
    @Transactional
    public void getAllMTrainingCardsByPlusDribbleIsEqualToSomething() throws Exception {
        // Initialize the database
        mTrainingCardRepository.saveAndFlush(mTrainingCard);

        // Get all the mTrainingCardList where plusDribble equals to DEFAULT_PLUS_DRIBBLE
        defaultMTrainingCardShouldBeFound("plusDribble.equals=" + DEFAULT_PLUS_DRIBBLE);

        // Get all the mTrainingCardList where plusDribble equals to UPDATED_PLUS_DRIBBLE
        defaultMTrainingCardShouldNotBeFound("plusDribble.equals=" + UPDATED_PLUS_DRIBBLE);
    }

    @Test
    @Transactional
    public void getAllMTrainingCardsByPlusDribbleIsInShouldWork() throws Exception {
        // Initialize the database
        mTrainingCardRepository.saveAndFlush(mTrainingCard);

        // Get all the mTrainingCardList where plusDribble in DEFAULT_PLUS_DRIBBLE or UPDATED_PLUS_DRIBBLE
        defaultMTrainingCardShouldBeFound("plusDribble.in=" + DEFAULT_PLUS_DRIBBLE + "," + UPDATED_PLUS_DRIBBLE);

        // Get all the mTrainingCardList where plusDribble equals to UPDATED_PLUS_DRIBBLE
        defaultMTrainingCardShouldNotBeFound("plusDribble.in=" + UPDATED_PLUS_DRIBBLE);
    }

    @Test
    @Transactional
    public void getAllMTrainingCardsByPlusDribbleIsNullOrNotNull() throws Exception {
        // Initialize the database
        mTrainingCardRepository.saveAndFlush(mTrainingCard);

        // Get all the mTrainingCardList where plusDribble is not null
        defaultMTrainingCardShouldBeFound("plusDribble.specified=true");

        // Get all the mTrainingCardList where plusDribble is null
        defaultMTrainingCardShouldNotBeFound("plusDribble.specified=false");
    }

    @Test
    @Transactional
    public void getAllMTrainingCardsByPlusDribbleIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mTrainingCardRepository.saveAndFlush(mTrainingCard);

        // Get all the mTrainingCardList where plusDribble greater than or equals to DEFAULT_PLUS_DRIBBLE
        defaultMTrainingCardShouldBeFound("plusDribble.greaterOrEqualThan=" + DEFAULT_PLUS_DRIBBLE);

        // Get all the mTrainingCardList where plusDribble greater than or equals to UPDATED_PLUS_DRIBBLE
        defaultMTrainingCardShouldNotBeFound("plusDribble.greaterOrEqualThan=" + UPDATED_PLUS_DRIBBLE);
    }

    @Test
    @Transactional
    public void getAllMTrainingCardsByPlusDribbleIsLessThanSomething() throws Exception {
        // Initialize the database
        mTrainingCardRepository.saveAndFlush(mTrainingCard);

        // Get all the mTrainingCardList where plusDribble less than or equals to DEFAULT_PLUS_DRIBBLE
        defaultMTrainingCardShouldNotBeFound("plusDribble.lessThan=" + DEFAULT_PLUS_DRIBBLE);

        // Get all the mTrainingCardList where plusDribble less than or equals to UPDATED_PLUS_DRIBBLE
        defaultMTrainingCardShouldBeFound("plusDribble.lessThan=" + UPDATED_PLUS_DRIBBLE);
    }


    @Test
    @Transactional
    public void getAllMTrainingCardsByPlusShootIsEqualToSomething() throws Exception {
        // Initialize the database
        mTrainingCardRepository.saveAndFlush(mTrainingCard);

        // Get all the mTrainingCardList where plusShoot equals to DEFAULT_PLUS_SHOOT
        defaultMTrainingCardShouldBeFound("plusShoot.equals=" + DEFAULT_PLUS_SHOOT);

        // Get all the mTrainingCardList where plusShoot equals to UPDATED_PLUS_SHOOT
        defaultMTrainingCardShouldNotBeFound("plusShoot.equals=" + UPDATED_PLUS_SHOOT);
    }

    @Test
    @Transactional
    public void getAllMTrainingCardsByPlusShootIsInShouldWork() throws Exception {
        // Initialize the database
        mTrainingCardRepository.saveAndFlush(mTrainingCard);

        // Get all the mTrainingCardList where plusShoot in DEFAULT_PLUS_SHOOT or UPDATED_PLUS_SHOOT
        defaultMTrainingCardShouldBeFound("plusShoot.in=" + DEFAULT_PLUS_SHOOT + "," + UPDATED_PLUS_SHOOT);

        // Get all the mTrainingCardList where plusShoot equals to UPDATED_PLUS_SHOOT
        defaultMTrainingCardShouldNotBeFound("plusShoot.in=" + UPDATED_PLUS_SHOOT);
    }

    @Test
    @Transactional
    public void getAllMTrainingCardsByPlusShootIsNullOrNotNull() throws Exception {
        // Initialize the database
        mTrainingCardRepository.saveAndFlush(mTrainingCard);

        // Get all the mTrainingCardList where plusShoot is not null
        defaultMTrainingCardShouldBeFound("plusShoot.specified=true");

        // Get all the mTrainingCardList where plusShoot is null
        defaultMTrainingCardShouldNotBeFound("plusShoot.specified=false");
    }

    @Test
    @Transactional
    public void getAllMTrainingCardsByPlusShootIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mTrainingCardRepository.saveAndFlush(mTrainingCard);

        // Get all the mTrainingCardList where plusShoot greater than or equals to DEFAULT_PLUS_SHOOT
        defaultMTrainingCardShouldBeFound("plusShoot.greaterOrEqualThan=" + DEFAULT_PLUS_SHOOT);

        // Get all the mTrainingCardList where plusShoot greater than or equals to UPDATED_PLUS_SHOOT
        defaultMTrainingCardShouldNotBeFound("plusShoot.greaterOrEqualThan=" + UPDATED_PLUS_SHOOT);
    }

    @Test
    @Transactional
    public void getAllMTrainingCardsByPlusShootIsLessThanSomething() throws Exception {
        // Initialize the database
        mTrainingCardRepository.saveAndFlush(mTrainingCard);

        // Get all the mTrainingCardList where plusShoot less than or equals to DEFAULT_PLUS_SHOOT
        defaultMTrainingCardShouldNotBeFound("plusShoot.lessThan=" + DEFAULT_PLUS_SHOOT);

        // Get all the mTrainingCardList where plusShoot less than or equals to UPDATED_PLUS_SHOOT
        defaultMTrainingCardShouldBeFound("plusShoot.lessThan=" + UPDATED_PLUS_SHOOT);
    }


    @Test
    @Transactional
    public void getAllMTrainingCardsByPlusPassIsEqualToSomething() throws Exception {
        // Initialize the database
        mTrainingCardRepository.saveAndFlush(mTrainingCard);

        // Get all the mTrainingCardList where plusPass equals to DEFAULT_PLUS_PASS
        defaultMTrainingCardShouldBeFound("plusPass.equals=" + DEFAULT_PLUS_PASS);

        // Get all the mTrainingCardList where plusPass equals to UPDATED_PLUS_PASS
        defaultMTrainingCardShouldNotBeFound("plusPass.equals=" + UPDATED_PLUS_PASS);
    }

    @Test
    @Transactional
    public void getAllMTrainingCardsByPlusPassIsInShouldWork() throws Exception {
        // Initialize the database
        mTrainingCardRepository.saveAndFlush(mTrainingCard);

        // Get all the mTrainingCardList where plusPass in DEFAULT_PLUS_PASS or UPDATED_PLUS_PASS
        defaultMTrainingCardShouldBeFound("plusPass.in=" + DEFAULT_PLUS_PASS + "," + UPDATED_PLUS_PASS);

        // Get all the mTrainingCardList where plusPass equals to UPDATED_PLUS_PASS
        defaultMTrainingCardShouldNotBeFound("plusPass.in=" + UPDATED_PLUS_PASS);
    }

    @Test
    @Transactional
    public void getAllMTrainingCardsByPlusPassIsNullOrNotNull() throws Exception {
        // Initialize the database
        mTrainingCardRepository.saveAndFlush(mTrainingCard);

        // Get all the mTrainingCardList where plusPass is not null
        defaultMTrainingCardShouldBeFound("plusPass.specified=true");

        // Get all the mTrainingCardList where plusPass is null
        defaultMTrainingCardShouldNotBeFound("plusPass.specified=false");
    }

    @Test
    @Transactional
    public void getAllMTrainingCardsByPlusPassIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mTrainingCardRepository.saveAndFlush(mTrainingCard);

        // Get all the mTrainingCardList where plusPass greater than or equals to DEFAULT_PLUS_PASS
        defaultMTrainingCardShouldBeFound("plusPass.greaterOrEqualThan=" + DEFAULT_PLUS_PASS);

        // Get all the mTrainingCardList where plusPass greater than or equals to UPDATED_PLUS_PASS
        defaultMTrainingCardShouldNotBeFound("plusPass.greaterOrEqualThan=" + UPDATED_PLUS_PASS);
    }

    @Test
    @Transactional
    public void getAllMTrainingCardsByPlusPassIsLessThanSomething() throws Exception {
        // Initialize the database
        mTrainingCardRepository.saveAndFlush(mTrainingCard);

        // Get all the mTrainingCardList where plusPass less than or equals to DEFAULT_PLUS_PASS
        defaultMTrainingCardShouldNotBeFound("plusPass.lessThan=" + DEFAULT_PLUS_PASS);

        // Get all the mTrainingCardList where plusPass less than or equals to UPDATED_PLUS_PASS
        defaultMTrainingCardShouldBeFound("plusPass.lessThan=" + UPDATED_PLUS_PASS);
    }


    @Test
    @Transactional
    public void getAllMTrainingCardsByPlusTackleIsEqualToSomething() throws Exception {
        // Initialize the database
        mTrainingCardRepository.saveAndFlush(mTrainingCard);

        // Get all the mTrainingCardList where plusTackle equals to DEFAULT_PLUS_TACKLE
        defaultMTrainingCardShouldBeFound("plusTackle.equals=" + DEFAULT_PLUS_TACKLE);

        // Get all the mTrainingCardList where plusTackle equals to UPDATED_PLUS_TACKLE
        defaultMTrainingCardShouldNotBeFound("plusTackle.equals=" + UPDATED_PLUS_TACKLE);
    }

    @Test
    @Transactional
    public void getAllMTrainingCardsByPlusTackleIsInShouldWork() throws Exception {
        // Initialize the database
        mTrainingCardRepository.saveAndFlush(mTrainingCard);

        // Get all the mTrainingCardList where plusTackle in DEFAULT_PLUS_TACKLE or UPDATED_PLUS_TACKLE
        defaultMTrainingCardShouldBeFound("plusTackle.in=" + DEFAULT_PLUS_TACKLE + "," + UPDATED_PLUS_TACKLE);

        // Get all the mTrainingCardList where plusTackle equals to UPDATED_PLUS_TACKLE
        defaultMTrainingCardShouldNotBeFound("plusTackle.in=" + UPDATED_PLUS_TACKLE);
    }

    @Test
    @Transactional
    public void getAllMTrainingCardsByPlusTackleIsNullOrNotNull() throws Exception {
        // Initialize the database
        mTrainingCardRepository.saveAndFlush(mTrainingCard);

        // Get all the mTrainingCardList where plusTackle is not null
        defaultMTrainingCardShouldBeFound("plusTackle.specified=true");

        // Get all the mTrainingCardList where plusTackle is null
        defaultMTrainingCardShouldNotBeFound("plusTackle.specified=false");
    }

    @Test
    @Transactional
    public void getAllMTrainingCardsByPlusTackleIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mTrainingCardRepository.saveAndFlush(mTrainingCard);

        // Get all the mTrainingCardList where plusTackle greater than or equals to DEFAULT_PLUS_TACKLE
        defaultMTrainingCardShouldBeFound("plusTackle.greaterOrEqualThan=" + DEFAULT_PLUS_TACKLE);

        // Get all the mTrainingCardList where plusTackle greater than or equals to UPDATED_PLUS_TACKLE
        defaultMTrainingCardShouldNotBeFound("plusTackle.greaterOrEqualThan=" + UPDATED_PLUS_TACKLE);
    }

    @Test
    @Transactional
    public void getAllMTrainingCardsByPlusTackleIsLessThanSomething() throws Exception {
        // Initialize the database
        mTrainingCardRepository.saveAndFlush(mTrainingCard);

        // Get all the mTrainingCardList where plusTackle less than or equals to DEFAULT_PLUS_TACKLE
        defaultMTrainingCardShouldNotBeFound("plusTackle.lessThan=" + DEFAULT_PLUS_TACKLE);

        // Get all the mTrainingCardList where plusTackle less than or equals to UPDATED_PLUS_TACKLE
        defaultMTrainingCardShouldBeFound("plusTackle.lessThan=" + UPDATED_PLUS_TACKLE);
    }


    @Test
    @Transactional
    public void getAllMTrainingCardsByPlusBlockIsEqualToSomething() throws Exception {
        // Initialize the database
        mTrainingCardRepository.saveAndFlush(mTrainingCard);

        // Get all the mTrainingCardList where plusBlock equals to DEFAULT_PLUS_BLOCK
        defaultMTrainingCardShouldBeFound("plusBlock.equals=" + DEFAULT_PLUS_BLOCK);

        // Get all the mTrainingCardList where plusBlock equals to UPDATED_PLUS_BLOCK
        defaultMTrainingCardShouldNotBeFound("plusBlock.equals=" + UPDATED_PLUS_BLOCK);
    }

    @Test
    @Transactional
    public void getAllMTrainingCardsByPlusBlockIsInShouldWork() throws Exception {
        // Initialize the database
        mTrainingCardRepository.saveAndFlush(mTrainingCard);

        // Get all the mTrainingCardList where plusBlock in DEFAULT_PLUS_BLOCK or UPDATED_PLUS_BLOCK
        defaultMTrainingCardShouldBeFound("plusBlock.in=" + DEFAULT_PLUS_BLOCK + "," + UPDATED_PLUS_BLOCK);

        // Get all the mTrainingCardList where plusBlock equals to UPDATED_PLUS_BLOCK
        defaultMTrainingCardShouldNotBeFound("plusBlock.in=" + UPDATED_PLUS_BLOCK);
    }

    @Test
    @Transactional
    public void getAllMTrainingCardsByPlusBlockIsNullOrNotNull() throws Exception {
        // Initialize the database
        mTrainingCardRepository.saveAndFlush(mTrainingCard);

        // Get all the mTrainingCardList where plusBlock is not null
        defaultMTrainingCardShouldBeFound("plusBlock.specified=true");

        // Get all the mTrainingCardList where plusBlock is null
        defaultMTrainingCardShouldNotBeFound("plusBlock.specified=false");
    }

    @Test
    @Transactional
    public void getAllMTrainingCardsByPlusBlockIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mTrainingCardRepository.saveAndFlush(mTrainingCard);

        // Get all the mTrainingCardList where plusBlock greater than or equals to DEFAULT_PLUS_BLOCK
        defaultMTrainingCardShouldBeFound("plusBlock.greaterOrEqualThan=" + DEFAULT_PLUS_BLOCK);

        // Get all the mTrainingCardList where plusBlock greater than or equals to UPDATED_PLUS_BLOCK
        defaultMTrainingCardShouldNotBeFound("plusBlock.greaterOrEqualThan=" + UPDATED_PLUS_BLOCK);
    }

    @Test
    @Transactional
    public void getAllMTrainingCardsByPlusBlockIsLessThanSomething() throws Exception {
        // Initialize the database
        mTrainingCardRepository.saveAndFlush(mTrainingCard);

        // Get all the mTrainingCardList where plusBlock less than or equals to DEFAULT_PLUS_BLOCK
        defaultMTrainingCardShouldNotBeFound("plusBlock.lessThan=" + DEFAULT_PLUS_BLOCK);

        // Get all the mTrainingCardList where plusBlock less than or equals to UPDATED_PLUS_BLOCK
        defaultMTrainingCardShouldBeFound("plusBlock.lessThan=" + UPDATED_PLUS_BLOCK);
    }


    @Test
    @Transactional
    public void getAllMTrainingCardsByPlusInterceptIsEqualToSomething() throws Exception {
        // Initialize the database
        mTrainingCardRepository.saveAndFlush(mTrainingCard);

        // Get all the mTrainingCardList where plusIntercept equals to DEFAULT_PLUS_INTERCEPT
        defaultMTrainingCardShouldBeFound("plusIntercept.equals=" + DEFAULT_PLUS_INTERCEPT);

        // Get all the mTrainingCardList where plusIntercept equals to UPDATED_PLUS_INTERCEPT
        defaultMTrainingCardShouldNotBeFound("plusIntercept.equals=" + UPDATED_PLUS_INTERCEPT);
    }

    @Test
    @Transactional
    public void getAllMTrainingCardsByPlusInterceptIsInShouldWork() throws Exception {
        // Initialize the database
        mTrainingCardRepository.saveAndFlush(mTrainingCard);

        // Get all the mTrainingCardList where plusIntercept in DEFAULT_PLUS_INTERCEPT or UPDATED_PLUS_INTERCEPT
        defaultMTrainingCardShouldBeFound("plusIntercept.in=" + DEFAULT_PLUS_INTERCEPT + "," + UPDATED_PLUS_INTERCEPT);

        // Get all the mTrainingCardList where plusIntercept equals to UPDATED_PLUS_INTERCEPT
        defaultMTrainingCardShouldNotBeFound("plusIntercept.in=" + UPDATED_PLUS_INTERCEPT);
    }

    @Test
    @Transactional
    public void getAllMTrainingCardsByPlusInterceptIsNullOrNotNull() throws Exception {
        // Initialize the database
        mTrainingCardRepository.saveAndFlush(mTrainingCard);

        // Get all the mTrainingCardList where plusIntercept is not null
        defaultMTrainingCardShouldBeFound("plusIntercept.specified=true");

        // Get all the mTrainingCardList where plusIntercept is null
        defaultMTrainingCardShouldNotBeFound("plusIntercept.specified=false");
    }

    @Test
    @Transactional
    public void getAllMTrainingCardsByPlusInterceptIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mTrainingCardRepository.saveAndFlush(mTrainingCard);

        // Get all the mTrainingCardList where plusIntercept greater than or equals to DEFAULT_PLUS_INTERCEPT
        defaultMTrainingCardShouldBeFound("plusIntercept.greaterOrEqualThan=" + DEFAULT_PLUS_INTERCEPT);

        // Get all the mTrainingCardList where plusIntercept greater than or equals to UPDATED_PLUS_INTERCEPT
        defaultMTrainingCardShouldNotBeFound("plusIntercept.greaterOrEqualThan=" + UPDATED_PLUS_INTERCEPT);
    }

    @Test
    @Transactional
    public void getAllMTrainingCardsByPlusInterceptIsLessThanSomething() throws Exception {
        // Initialize the database
        mTrainingCardRepository.saveAndFlush(mTrainingCard);

        // Get all the mTrainingCardList where plusIntercept less than or equals to DEFAULT_PLUS_INTERCEPT
        defaultMTrainingCardShouldNotBeFound("plusIntercept.lessThan=" + DEFAULT_PLUS_INTERCEPT);

        // Get all the mTrainingCardList where plusIntercept less than or equals to UPDATED_PLUS_INTERCEPT
        defaultMTrainingCardShouldBeFound("plusIntercept.lessThan=" + UPDATED_PLUS_INTERCEPT);
    }


    @Test
    @Transactional
    public void getAllMTrainingCardsByPlusSpeedIsEqualToSomething() throws Exception {
        // Initialize the database
        mTrainingCardRepository.saveAndFlush(mTrainingCard);

        // Get all the mTrainingCardList where plusSpeed equals to DEFAULT_PLUS_SPEED
        defaultMTrainingCardShouldBeFound("plusSpeed.equals=" + DEFAULT_PLUS_SPEED);

        // Get all the mTrainingCardList where plusSpeed equals to UPDATED_PLUS_SPEED
        defaultMTrainingCardShouldNotBeFound("plusSpeed.equals=" + UPDATED_PLUS_SPEED);
    }

    @Test
    @Transactional
    public void getAllMTrainingCardsByPlusSpeedIsInShouldWork() throws Exception {
        // Initialize the database
        mTrainingCardRepository.saveAndFlush(mTrainingCard);

        // Get all the mTrainingCardList where plusSpeed in DEFAULT_PLUS_SPEED or UPDATED_PLUS_SPEED
        defaultMTrainingCardShouldBeFound("plusSpeed.in=" + DEFAULT_PLUS_SPEED + "," + UPDATED_PLUS_SPEED);

        // Get all the mTrainingCardList where plusSpeed equals to UPDATED_PLUS_SPEED
        defaultMTrainingCardShouldNotBeFound("plusSpeed.in=" + UPDATED_PLUS_SPEED);
    }

    @Test
    @Transactional
    public void getAllMTrainingCardsByPlusSpeedIsNullOrNotNull() throws Exception {
        // Initialize the database
        mTrainingCardRepository.saveAndFlush(mTrainingCard);

        // Get all the mTrainingCardList where plusSpeed is not null
        defaultMTrainingCardShouldBeFound("plusSpeed.specified=true");

        // Get all the mTrainingCardList where plusSpeed is null
        defaultMTrainingCardShouldNotBeFound("plusSpeed.specified=false");
    }

    @Test
    @Transactional
    public void getAllMTrainingCardsByPlusSpeedIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mTrainingCardRepository.saveAndFlush(mTrainingCard);

        // Get all the mTrainingCardList where plusSpeed greater than or equals to DEFAULT_PLUS_SPEED
        defaultMTrainingCardShouldBeFound("plusSpeed.greaterOrEqualThan=" + DEFAULT_PLUS_SPEED);

        // Get all the mTrainingCardList where plusSpeed greater than or equals to UPDATED_PLUS_SPEED
        defaultMTrainingCardShouldNotBeFound("plusSpeed.greaterOrEqualThan=" + UPDATED_PLUS_SPEED);
    }

    @Test
    @Transactional
    public void getAllMTrainingCardsByPlusSpeedIsLessThanSomething() throws Exception {
        // Initialize the database
        mTrainingCardRepository.saveAndFlush(mTrainingCard);

        // Get all the mTrainingCardList where plusSpeed less than or equals to DEFAULT_PLUS_SPEED
        defaultMTrainingCardShouldNotBeFound("plusSpeed.lessThan=" + DEFAULT_PLUS_SPEED);

        // Get all the mTrainingCardList where plusSpeed less than or equals to UPDATED_PLUS_SPEED
        defaultMTrainingCardShouldBeFound("plusSpeed.lessThan=" + UPDATED_PLUS_SPEED);
    }


    @Test
    @Transactional
    public void getAllMTrainingCardsByPlusPowerIsEqualToSomething() throws Exception {
        // Initialize the database
        mTrainingCardRepository.saveAndFlush(mTrainingCard);

        // Get all the mTrainingCardList where plusPower equals to DEFAULT_PLUS_POWER
        defaultMTrainingCardShouldBeFound("plusPower.equals=" + DEFAULT_PLUS_POWER);

        // Get all the mTrainingCardList where plusPower equals to UPDATED_PLUS_POWER
        defaultMTrainingCardShouldNotBeFound("plusPower.equals=" + UPDATED_PLUS_POWER);
    }

    @Test
    @Transactional
    public void getAllMTrainingCardsByPlusPowerIsInShouldWork() throws Exception {
        // Initialize the database
        mTrainingCardRepository.saveAndFlush(mTrainingCard);

        // Get all the mTrainingCardList where plusPower in DEFAULT_PLUS_POWER or UPDATED_PLUS_POWER
        defaultMTrainingCardShouldBeFound("plusPower.in=" + DEFAULT_PLUS_POWER + "," + UPDATED_PLUS_POWER);

        // Get all the mTrainingCardList where plusPower equals to UPDATED_PLUS_POWER
        defaultMTrainingCardShouldNotBeFound("plusPower.in=" + UPDATED_PLUS_POWER);
    }

    @Test
    @Transactional
    public void getAllMTrainingCardsByPlusPowerIsNullOrNotNull() throws Exception {
        // Initialize the database
        mTrainingCardRepository.saveAndFlush(mTrainingCard);

        // Get all the mTrainingCardList where plusPower is not null
        defaultMTrainingCardShouldBeFound("plusPower.specified=true");

        // Get all the mTrainingCardList where plusPower is null
        defaultMTrainingCardShouldNotBeFound("plusPower.specified=false");
    }

    @Test
    @Transactional
    public void getAllMTrainingCardsByPlusPowerIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mTrainingCardRepository.saveAndFlush(mTrainingCard);

        // Get all the mTrainingCardList where plusPower greater than or equals to DEFAULT_PLUS_POWER
        defaultMTrainingCardShouldBeFound("plusPower.greaterOrEqualThan=" + DEFAULT_PLUS_POWER);

        // Get all the mTrainingCardList where plusPower greater than or equals to UPDATED_PLUS_POWER
        defaultMTrainingCardShouldNotBeFound("plusPower.greaterOrEqualThan=" + UPDATED_PLUS_POWER);
    }

    @Test
    @Transactional
    public void getAllMTrainingCardsByPlusPowerIsLessThanSomething() throws Exception {
        // Initialize the database
        mTrainingCardRepository.saveAndFlush(mTrainingCard);

        // Get all the mTrainingCardList where plusPower less than or equals to DEFAULT_PLUS_POWER
        defaultMTrainingCardShouldNotBeFound("plusPower.lessThan=" + DEFAULT_PLUS_POWER);

        // Get all the mTrainingCardList where plusPower less than or equals to UPDATED_PLUS_POWER
        defaultMTrainingCardShouldBeFound("plusPower.lessThan=" + UPDATED_PLUS_POWER);
    }


    @Test
    @Transactional
    public void getAllMTrainingCardsByPlusTechniqueIsEqualToSomething() throws Exception {
        // Initialize the database
        mTrainingCardRepository.saveAndFlush(mTrainingCard);

        // Get all the mTrainingCardList where plusTechnique equals to DEFAULT_PLUS_TECHNIQUE
        defaultMTrainingCardShouldBeFound("plusTechnique.equals=" + DEFAULT_PLUS_TECHNIQUE);

        // Get all the mTrainingCardList where plusTechnique equals to UPDATED_PLUS_TECHNIQUE
        defaultMTrainingCardShouldNotBeFound("plusTechnique.equals=" + UPDATED_PLUS_TECHNIQUE);
    }

    @Test
    @Transactional
    public void getAllMTrainingCardsByPlusTechniqueIsInShouldWork() throws Exception {
        // Initialize the database
        mTrainingCardRepository.saveAndFlush(mTrainingCard);

        // Get all the mTrainingCardList where plusTechnique in DEFAULT_PLUS_TECHNIQUE or UPDATED_PLUS_TECHNIQUE
        defaultMTrainingCardShouldBeFound("plusTechnique.in=" + DEFAULT_PLUS_TECHNIQUE + "," + UPDATED_PLUS_TECHNIQUE);

        // Get all the mTrainingCardList where plusTechnique equals to UPDATED_PLUS_TECHNIQUE
        defaultMTrainingCardShouldNotBeFound("plusTechnique.in=" + UPDATED_PLUS_TECHNIQUE);
    }

    @Test
    @Transactional
    public void getAllMTrainingCardsByPlusTechniqueIsNullOrNotNull() throws Exception {
        // Initialize the database
        mTrainingCardRepository.saveAndFlush(mTrainingCard);

        // Get all the mTrainingCardList where plusTechnique is not null
        defaultMTrainingCardShouldBeFound("plusTechnique.specified=true");

        // Get all the mTrainingCardList where plusTechnique is null
        defaultMTrainingCardShouldNotBeFound("plusTechnique.specified=false");
    }

    @Test
    @Transactional
    public void getAllMTrainingCardsByPlusTechniqueIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mTrainingCardRepository.saveAndFlush(mTrainingCard);

        // Get all the mTrainingCardList where plusTechnique greater than or equals to DEFAULT_PLUS_TECHNIQUE
        defaultMTrainingCardShouldBeFound("plusTechnique.greaterOrEqualThan=" + DEFAULT_PLUS_TECHNIQUE);

        // Get all the mTrainingCardList where plusTechnique greater than or equals to UPDATED_PLUS_TECHNIQUE
        defaultMTrainingCardShouldNotBeFound("plusTechnique.greaterOrEqualThan=" + UPDATED_PLUS_TECHNIQUE);
    }

    @Test
    @Transactional
    public void getAllMTrainingCardsByPlusTechniqueIsLessThanSomething() throws Exception {
        // Initialize the database
        mTrainingCardRepository.saveAndFlush(mTrainingCard);

        // Get all the mTrainingCardList where plusTechnique less than or equals to DEFAULT_PLUS_TECHNIQUE
        defaultMTrainingCardShouldNotBeFound("plusTechnique.lessThan=" + DEFAULT_PLUS_TECHNIQUE);

        // Get all the mTrainingCardList where plusTechnique less than or equals to UPDATED_PLUS_TECHNIQUE
        defaultMTrainingCardShouldBeFound("plusTechnique.lessThan=" + UPDATED_PLUS_TECHNIQUE);
    }


    @Test
    @Transactional
    public void getAllMTrainingCardsByPlusPunchingIsEqualToSomething() throws Exception {
        // Initialize the database
        mTrainingCardRepository.saveAndFlush(mTrainingCard);

        // Get all the mTrainingCardList where plusPunching equals to DEFAULT_PLUS_PUNCHING
        defaultMTrainingCardShouldBeFound("plusPunching.equals=" + DEFAULT_PLUS_PUNCHING);

        // Get all the mTrainingCardList where plusPunching equals to UPDATED_PLUS_PUNCHING
        defaultMTrainingCardShouldNotBeFound("plusPunching.equals=" + UPDATED_PLUS_PUNCHING);
    }

    @Test
    @Transactional
    public void getAllMTrainingCardsByPlusPunchingIsInShouldWork() throws Exception {
        // Initialize the database
        mTrainingCardRepository.saveAndFlush(mTrainingCard);

        // Get all the mTrainingCardList where plusPunching in DEFAULT_PLUS_PUNCHING or UPDATED_PLUS_PUNCHING
        defaultMTrainingCardShouldBeFound("plusPunching.in=" + DEFAULT_PLUS_PUNCHING + "," + UPDATED_PLUS_PUNCHING);

        // Get all the mTrainingCardList where plusPunching equals to UPDATED_PLUS_PUNCHING
        defaultMTrainingCardShouldNotBeFound("plusPunching.in=" + UPDATED_PLUS_PUNCHING);
    }

    @Test
    @Transactional
    public void getAllMTrainingCardsByPlusPunchingIsNullOrNotNull() throws Exception {
        // Initialize the database
        mTrainingCardRepository.saveAndFlush(mTrainingCard);

        // Get all the mTrainingCardList where plusPunching is not null
        defaultMTrainingCardShouldBeFound("plusPunching.specified=true");

        // Get all the mTrainingCardList where plusPunching is null
        defaultMTrainingCardShouldNotBeFound("plusPunching.specified=false");
    }

    @Test
    @Transactional
    public void getAllMTrainingCardsByPlusPunchingIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mTrainingCardRepository.saveAndFlush(mTrainingCard);

        // Get all the mTrainingCardList where plusPunching greater than or equals to DEFAULT_PLUS_PUNCHING
        defaultMTrainingCardShouldBeFound("plusPunching.greaterOrEqualThan=" + DEFAULT_PLUS_PUNCHING);

        // Get all the mTrainingCardList where plusPunching greater than or equals to UPDATED_PLUS_PUNCHING
        defaultMTrainingCardShouldNotBeFound("plusPunching.greaterOrEqualThan=" + UPDATED_PLUS_PUNCHING);
    }

    @Test
    @Transactional
    public void getAllMTrainingCardsByPlusPunchingIsLessThanSomething() throws Exception {
        // Initialize the database
        mTrainingCardRepository.saveAndFlush(mTrainingCard);

        // Get all the mTrainingCardList where plusPunching less than or equals to DEFAULT_PLUS_PUNCHING
        defaultMTrainingCardShouldNotBeFound("plusPunching.lessThan=" + DEFAULT_PLUS_PUNCHING);

        // Get all the mTrainingCardList where plusPunching less than or equals to UPDATED_PLUS_PUNCHING
        defaultMTrainingCardShouldBeFound("plusPunching.lessThan=" + UPDATED_PLUS_PUNCHING);
    }


    @Test
    @Transactional
    public void getAllMTrainingCardsByPlusCatchingIsEqualToSomething() throws Exception {
        // Initialize the database
        mTrainingCardRepository.saveAndFlush(mTrainingCard);

        // Get all the mTrainingCardList where plusCatching equals to DEFAULT_PLUS_CATCHING
        defaultMTrainingCardShouldBeFound("plusCatching.equals=" + DEFAULT_PLUS_CATCHING);

        // Get all the mTrainingCardList where plusCatching equals to UPDATED_PLUS_CATCHING
        defaultMTrainingCardShouldNotBeFound("plusCatching.equals=" + UPDATED_PLUS_CATCHING);
    }

    @Test
    @Transactional
    public void getAllMTrainingCardsByPlusCatchingIsInShouldWork() throws Exception {
        // Initialize the database
        mTrainingCardRepository.saveAndFlush(mTrainingCard);

        // Get all the mTrainingCardList where plusCatching in DEFAULT_PLUS_CATCHING or UPDATED_PLUS_CATCHING
        defaultMTrainingCardShouldBeFound("plusCatching.in=" + DEFAULT_PLUS_CATCHING + "," + UPDATED_PLUS_CATCHING);

        // Get all the mTrainingCardList where plusCatching equals to UPDATED_PLUS_CATCHING
        defaultMTrainingCardShouldNotBeFound("plusCatching.in=" + UPDATED_PLUS_CATCHING);
    }

    @Test
    @Transactional
    public void getAllMTrainingCardsByPlusCatchingIsNullOrNotNull() throws Exception {
        // Initialize the database
        mTrainingCardRepository.saveAndFlush(mTrainingCard);

        // Get all the mTrainingCardList where plusCatching is not null
        defaultMTrainingCardShouldBeFound("plusCatching.specified=true");

        // Get all the mTrainingCardList where plusCatching is null
        defaultMTrainingCardShouldNotBeFound("plusCatching.specified=false");
    }

    @Test
    @Transactional
    public void getAllMTrainingCardsByPlusCatchingIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mTrainingCardRepository.saveAndFlush(mTrainingCard);

        // Get all the mTrainingCardList where plusCatching greater than or equals to DEFAULT_PLUS_CATCHING
        defaultMTrainingCardShouldBeFound("plusCatching.greaterOrEqualThan=" + DEFAULT_PLUS_CATCHING);

        // Get all the mTrainingCardList where plusCatching greater than or equals to UPDATED_PLUS_CATCHING
        defaultMTrainingCardShouldNotBeFound("plusCatching.greaterOrEqualThan=" + UPDATED_PLUS_CATCHING);
    }

    @Test
    @Transactional
    public void getAllMTrainingCardsByPlusCatchingIsLessThanSomething() throws Exception {
        // Initialize the database
        mTrainingCardRepository.saveAndFlush(mTrainingCard);

        // Get all the mTrainingCardList where plusCatching less than or equals to DEFAULT_PLUS_CATCHING
        defaultMTrainingCardShouldNotBeFound("plusCatching.lessThan=" + DEFAULT_PLUS_CATCHING);

        // Get all the mTrainingCardList where plusCatching less than or equals to UPDATED_PLUS_CATCHING
        defaultMTrainingCardShouldBeFound("plusCatching.lessThan=" + UPDATED_PLUS_CATCHING);
    }


    @Test
    @Transactional
    public void getAllMTrainingCardsByThumbnailAssetsIdIsEqualToSomething() throws Exception {
        // Initialize the database
        mTrainingCardRepository.saveAndFlush(mTrainingCard);

        // Get all the mTrainingCardList where thumbnailAssetsId equals to DEFAULT_THUMBNAIL_ASSETS_ID
        defaultMTrainingCardShouldBeFound("thumbnailAssetsId.equals=" + DEFAULT_THUMBNAIL_ASSETS_ID);

        // Get all the mTrainingCardList where thumbnailAssetsId equals to UPDATED_THUMBNAIL_ASSETS_ID
        defaultMTrainingCardShouldNotBeFound("thumbnailAssetsId.equals=" + UPDATED_THUMBNAIL_ASSETS_ID);
    }

    @Test
    @Transactional
    public void getAllMTrainingCardsByThumbnailAssetsIdIsInShouldWork() throws Exception {
        // Initialize the database
        mTrainingCardRepository.saveAndFlush(mTrainingCard);

        // Get all the mTrainingCardList where thumbnailAssetsId in DEFAULT_THUMBNAIL_ASSETS_ID or UPDATED_THUMBNAIL_ASSETS_ID
        defaultMTrainingCardShouldBeFound("thumbnailAssetsId.in=" + DEFAULT_THUMBNAIL_ASSETS_ID + "," + UPDATED_THUMBNAIL_ASSETS_ID);

        // Get all the mTrainingCardList where thumbnailAssetsId equals to UPDATED_THUMBNAIL_ASSETS_ID
        defaultMTrainingCardShouldNotBeFound("thumbnailAssetsId.in=" + UPDATED_THUMBNAIL_ASSETS_ID);
    }

    @Test
    @Transactional
    public void getAllMTrainingCardsByThumbnailAssetsIdIsNullOrNotNull() throws Exception {
        // Initialize the database
        mTrainingCardRepository.saveAndFlush(mTrainingCard);

        // Get all the mTrainingCardList where thumbnailAssetsId is not null
        defaultMTrainingCardShouldBeFound("thumbnailAssetsId.specified=true");

        // Get all the mTrainingCardList where thumbnailAssetsId is null
        defaultMTrainingCardShouldNotBeFound("thumbnailAssetsId.specified=false");
    }

    @Test
    @Transactional
    public void getAllMTrainingCardsByThumbnailAssetsIdIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mTrainingCardRepository.saveAndFlush(mTrainingCard);

        // Get all the mTrainingCardList where thumbnailAssetsId greater than or equals to DEFAULT_THUMBNAIL_ASSETS_ID
        defaultMTrainingCardShouldBeFound("thumbnailAssetsId.greaterOrEqualThan=" + DEFAULT_THUMBNAIL_ASSETS_ID);

        // Get all the mTrainingCardList where thumbnailAssetsId greater than or equals to UPDATED_THUMBNAIL_ASSETS_ID
        defaultMTrainingCardShouldNotBeFound("thumbnailAssetsId.greaterOrEqualThan=" + UPDATED_THUMBNAIL_ASSETS_ID);
    }

    @Test
    @Transactional
    public void getAllMTrainingCardsByThumbnailAssetsIdIsLessThanSomething() throws Exception {
        // Initialize the database
        mTrainingCardRepository.saveAndFlush(mTrainingCard);

        // Get all the mTrainingCardList where thumbnailAssetsId less than or equals to DEFAULT_THUMBNAIL_ASSETS_ID
        defaultMTrainingCardShouldNotBeFound("thumbnailAssetsId.lessThan=" + DEFAULT_THUMBNAIL_ASSETS_ID);

        // Get all the mTrainingCardList where thumbnailAssetsId less than or equals to UPDATED_THUMBNAIL_ASSETS_ID
        defaultMTrainingCardShouldBeFound("thumbnailAssetsId.lessThan=" + UPDATED_THUMBNAIL_ASSETS_ID);
    }


    @Test
    @Transactional
    public void getAllMTrainingCardsByCardIllustAssetsIdIsEqualToSomething() throws Exception {
        // Initialize the database
        mTrainingCardRepository.saveAndFlush(mTrainingCard);

        // Get all the mTrainingCardList where cardIllustAssetsId equals to DEFAULT_CARD_ILLUST_ASSETS_ID
        defaultMTrainingCardShouldBeFound("cardIllustAssetsId.equals=" + DEFAULT_CARD_ILLUST_ASSETS_ID);

        // Get all the mTrainingCardList where cardIllustAssetsId equals to UPDATED_CARD_ILLUST_ASSETS_ID
        defaultMTrainingCardShouldNotBeFound("cardIllustAssetsId.equals=" + UPDATED_CARD_ILLUST_ASSETS_ID);
    }

    @Test
    @Transactional
    public void getAllMTrainingCardsByCardIllustAssetsIdIsInShouldWork() throws Exception {
        // Initialize the database
        mTrainingCardRepository.saveAndFlush(mTrainingCard);

        // Get all the mTrainingCardList where cardIllustAssetsId in DEFAULT_CARD_ILLUST_ASSETS_ID or UPDATED_CARD_ILLUST_ASSETS_ID
        defaultMTrainingCardShouldBeFound("cardIllustAssetsId.in=" + DEFAULT_CARD_ILLUST_ASSETS_ID + "," + UPDATED_CARD_ILLUST_ASSETS_ID);

        // Get all the mTrainingCardList where cardIllustAssetsId equals to UPDATED_CARD_ILLUST_ASSETS_ID
        defaultMTrainingCardShouldNotBeFound("cardIllustAssetsId.in=" + UPDATED_CARD_ILLUST_ASSETS_ID);
    }

    @Test
    @Transactional
    public void getAllMTrainingCardsByCardIllustAssetsIdIsNullOrNotNull() throws Exception {
        // Initialize the database
        mTrainingCardRepository.saveAndFlush(mTrainingCard);

        // Get all the mTrainingCardList where cardIllustAssetsId is not null
        defaultMTrainingCardShouldBeFound("cardIllustAssetsId.specified=true");

        // Get all the mTrainingCardList where cardIllustAssetsId is null
        defaultMTrainingCardShouldNotBeFound("cardIllustAssetsId.specified=false");
    }

    @Test
    @Transactional
    public void getAllMTrainingCardsByCardIllustAssetsIdIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mTrainingCardRepository.saveAndFlush(mTrainingCard);

        // Get all the mTrainingCardList where cardIllustAssetsId greater than or equals to DEFAULT_CARD_ILLUST_ASSETS_ID
        defaultMTrainingCardShouldBeFound("cardIllustAssetsId.greaterOrEqualThan=" + DEFAULT_CARD_ILLUST_ASSETS_ID);

        // Get all the mTrainingCardList where cardIllustAssetsId greater than or equals to UPDATED_CARD_ILLUST_ASSETS_ID
        defaultMTrainingCardShouldNotBeFound("cardIllustAssetsId.greaterOrEqualThan=" + UPDATED_CARD_ILLUST_ASSETS_ID);
    }

    @Test
    @Transactional
    public void getAllMTrainingCardsByCardIllustAssetsIdIsLessThanSomething() throws Exception {
        // Initialize the database
        mTrainingCardRepository.saveAndFlush(mTrainingCard);

        // Get all the mTrainingCardList where cardIllustAssetsId less than or equals to DEFAULT_CARD_ILLUST_ASSETS_ID
        defaultMTrainingCardShouldNotBeFound("cardIllustAssetsId.lessThan=" + DEFAULT_CARD_ILLUST_ASSETS_ID);

        // Get all the mTrainingCardList where cardIllustAssetsId less than or equals to UPDATED_CARD_ILLUST_ASSETS_ID
        defaultMTrainingCardShouldBeFound("cardIllustAssetsId.lessThan=" + UPDATED_CARD_ILLUST_ASSETS_ID);
    }


    @Test
    @Transactional
    public void getAllMTrainingCardsByMcardthumbnailassetsIsEqualToSomething() throws Exception {
        // Get already existing entity
        MCardThumbnailAssets mcardthumbnailassets = mTrainingCard.getMcardthumbnailassets();
        mTrainingCardRepository.saveAndFlush(mTrainingCard);
        Long mcardthumbnailassetsId = mcardthumbnailassets.getId();

        // Get all the mTrainingCardList where mcardthumbnailassets equals to mcardthumbnailassetsId
        defaultMTrainingCardShouldBeFound("mcardthumbnailassetsId.equals=" + mcardthumbnailassetsId);

        // Get all the mTrainingCardList where mcardthumbnailassets equals to mcardthumbnailassetsId + 1
        defaultMTrainingCardShouldNotBeFound("mcardthumbnailassetsId.equals=" + (mcardthumbnailassetsId + 1));
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultMTrainingCardShouldBeFound(String filter) throws Exception {
        restMTrainingCardMockMvc.perform(get("/api/m-training-cards?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(mTrainingCard.getId().intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME.toString())))
            .andExpect(jsonPath("$.[*].shortName").value(hasItem(DEFAULT_SHORT_NAME.toString())))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION.toString())))
            .andExpect(jsonPath("$.[*].rarity").value(hasItem(DEFAULT_RARITY)))
            .andExpect(jsonPath("$.[*].attribute").value(hasItem(DEFAULT_ATTRIBUTE)))
            .andExpect(jsonPath("$.[*].gainExp").value(hasItem(DEFAULT_GAIN_EXP)))
            .andExpect(jsonPath("$.[*].coin").value(hasItem(DEFAULT_COIN)))
            .andExpect(jsonPath("$.[*].sellMedalId").value(hasItem(DEFAULT_SELL_MEDAL_ID)))
            .andExpect(jsonPath("$.[*].plusDribble").value(hasItem(DEFAULT_PLUS_DRIBBLE)))
            .andExpect(jsonPath("$.[*].plusShoot").value(hasItem(DEFAULT_PLUS_SHOOT)))
            .andExpect(jsonPath("$.[*].plusPass").value(hasItem(DEFAULT_PLUS_PASS)))
            .andExpect(jsonPath("$.[*].plusTackle").value(hasItem(DEFAULT_PLUS_TACKLE)))
            .andExpect(jsonPath("$.[*].plusBlock").value(hasItem(DEFAULT_PLUS_BLOCK)))
            .andExpect(jsonPath("$.[*].plusIntercept").value(hasItem(DEFAULT_PLUS_INTERCEPT)))
            .andExpect(jsonPath("$.[*].plusSpeed").value(hasItem(DEFAULT_PLUS_SPEED)))
            .andExpect(jsonPath("$.[*].plusPower").value(hasItem(DEFAULT_PLUS_POWER)))
            .andExpect(jsonPath("$.[*].plusTechnique").value(hasItem(DEFAULT_PLUS_TECHNIQUE)))
            .andExpect(jsonPath("$.[*].plusPunching").value(hasItem(DEFAULT_PLUS_PUNCHING)))
            .andExpect(jsonPath("$.[*].plusCatching").value(hasItem(DEFAULT_PLUS_CATCHING)))
            .andExpect(jsonPath("$.[*].thumbnailAssetsId").value(hasItem(DEFAULT_THUMBNAIL_ASSETS_ID)))
            .andExpect(jsonPath("$.[*].cardIllustAssetsId").value(hasItem(DEFAULT_CARD_ILLUST_ASSETS_ID)));

        // Check, that the count call also returns 1
        restMTrainingCardMockMvc.perform(get("/api/m-training-cards/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultMTrainingCardShouldNotBeFound(String filter) throws Exception {
        restMTrainingCardMockMvc.perform(get("/api/m-training-cards?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restMTrainingCardMockMvc.perform(get("/api/m-training-cards/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("0"));
    }


    @Test
    @Transactional
    public void getNonExistingMTrainingCard() throws Exception {
        // Get the mTrainingCard
        restMTrainingCardMockMvc.perform(get("/api/m-training-cards/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateMTrainingCard() throws Exception {
        // Initialize the database
        mTrainingCardRepository.saveAndFlush(mTrainingCard);

        int databaseSizeBeforeUpdate = mTrainingCardRepository.findAll().size();

        // Update the mTrainingCard
        MTrainingCard updatedMTrainingCard = mTrainingCardRepository.findById(mTrainingCard.getId()).get();
        // Disconnect from session so that the updates on updatedMTrainingCard are not directly saved in db
        em.detach(updatedMTrainingCard);
        updatedMTrainingCard
            .name(UPDATED_NAME)
            .shortName(UPDATED_SHORT_NAME)
            .description(UPDATED_DESCRIPTION)
            .rarity(UPDATED_RARITY)
            .attribute(UPDATED_ATTRIBUTE)
            .gainExp(UPDATED_GAIN_EXP)
            .coin(UPDATED_COIN)
            .sellMedalId(UPDATED_SELL_MEDAL_ID)
            .plusDribble(UPDATED_PLUS_DRIBBLE)
            .plusShoot(UPDATED_PLUS_SHOOT)
            .plusPass(UPDATED_PLUS_PASS)
            .plusTackle(UPDATED_PLUS_TACKLE)
            .plusBlock(UPDATED_PLUS_BLOCK)
            .plusIntercept(UPDATED_PLUS_INTERCEPT)
            .plusSpeed(UPDATED_PLUS_SPEED)
            .plusPower(UPDATED_PLUS_POWER)
            .plusTechnique(UPDATED_PLUS_TECHNIQUE)
            .plusPunching(UPDATED_PLUS_PUNCHING)
            .plusCatching(UPDATED_PLUS_CATCHING)
            .thumbnailAssetsId(UPDATED_THUMBNAIL_ASSETS_ID)
            .cardIllustAssetsId(UPDATED_CARD_ILLUST_ASSETS_ID);
        MTrainingCardDTO mTrainingCardDTO = mTrainingCardMapper.toDto(updatedMTrainingCard);

        restMTrainingCardMockMvc.perform(put("/api/m-training-cards")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mTrainingCardDTO)))
            .andExpect(status().isOk());

        // Validate the MTrainingCard in the database
        List<MTrainingCard> mTrainingCardList = mTrainingCardRepository.findAll();
        assertThat(mTrainingCardList).hasSize(databaseSizeBeforeUpdate);
        MTrainingCard testMTrainingCard = mTrainingCardList.get(mTrainingCardList.size() - 1);
        assertThat(testMTrainingCard.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testMTrainingCard.getShortName()).isEqualTo(UPDATED_SHORT_NAME);
        assertThat(testMTrainingCard.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
        assertThat(testMTrainingCard.getRarity()).isEqualTo(UPDATED_RARITY);
        assertThat(testMTrainingCard.getAttribute()).isEqualTo(UPDATED_ATTRIBUTE);
        assertThat(testMTrainingCard.getGainExp()).isEqualTo(UPDATED_GAIN_EXP);
        assertThat(testMTrainingCard.getCoin()).isEqualTo(UPDATED_COIN);
        assertThat(testMTrainingCard.getSellMedalId()).isEqualTo(UPDATED_SELL_MEDAL_ID);
        assertThat(testMTrainingCard.getPlusDribble()).isEqualTo(UPDATED_PLUS_DRIBBLE);
        assertThat(testMTrainingCard.getPlusShoot()).isEqualTo(UPDATED_PLUS_SHOOT);
        assertThat(testMTrainingCard.getPlusPass()).isEqualTo(UPDATED_PLUS_PASS);
        assertThat(testMTrainingCard.getPlusTackle()).isEqualTo(UPDATED_PLUS_TACKLE);
        assertThat(testMTrainingCard.getPlusBlock()).isEqualTo(UPDATED_PLUS_BLOCK);
        assertThat(testMTrainingCard.getPlusIntercept()).isEqualTo(UPDATED_PLUS_INTERCEPT);
        assertThat(testMTrainingCard.getPlusSpeed()).isEqualTo(UPDATED_PLUS_SPEED);
        assertThat(testMTrainingCard.getPlusPower()).isEqualTo(UPDATED_PLUS_POWER);
        assertThat(testMTrainingCard.getPlusTechnique()).isEqualTo(UPDATED_PLUS_TECHNIQUE);
        assertThat(testMTrainingCard.getPlusPunching()).isEqualTo(UPDATED_PLUS_PUNCHING);
        assertThat(testMTrainingCard.getPlusCatching()).isEqualTo(UPDATED_PLUS_CATCHING);
        assertThat(testMTrainingCard.getThumbnailAssetsId()).isEqualTo(UPDATED_THUMBNAIL_ASSETS_ID);
        assertThat(testMTrainingCard.getCardIllustAssetsId()).isEqualTo(UPDATED_CARD_ILLUST_ASSETS_ID);
    }

    @Test
    @Transactional
    public void updateNonExistingMTrainingCard() throws Exception {
        int databaseSizeBeforeUpdate = mTrainingCardRepository.findAll().size();

        // Create the MTrainingCard
        MTrainingCardDTO mTrainingCardDTO = mTrainingCardMapper.toDto(mTrainingCard);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restMTrainingCardMockMvc.perform(put("/api/m-training-cards")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mTrainingCardDTO)))
            .andExpect(status().isBadRequest());

        // Validate the MTrainingCard in the database
        List<MTrainingCard> mTrainingCardList = mTrainingCardRepository.findAll();
        assertThat(mTrainingCardList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteMTrainingCard() throws Exception {
        // Initialize the database
        mTrainingCardRepository.saveAndFlush(mTrainingCard);

        int databaseSizeBeforeDelete = mTrainingCardRepository.findAll().size();

        // Delete the mTrainingCard
        restMTrainingCardMockMvc.perform(delete("/api/m-training-cards/{id}", mTrainingCard.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database is empty
        List<MTrainingCard> mTrainingCardList = mTrainingCardRepository.findAll();
        assertThat(mTrainingCardList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(MTrainingCard.class);
        MTrainingCard mTrainingCard1 = new MTrainingCard();
        mTrainingCard1.setId(1L);
        MTrainingCard mTrainingCard2 = new MTrainingCard();
        mTrainingCard2.setId(mTrainingCard1.getId());
        assertThat(mTrainingCard1).isEqualTo(mTrainingCard2);
        mTrainingCard2.setId(2L);
        assertThat(mTrainingCard1).isNotEqualTo(mTrainingCard2);
        mTrainingCard1.setId(null);
        assertThat(mTrainingCard1).isNotEqualTo(mTrainingCard2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(MTrainingCardDTO.class);
        MTrainingCardDTO mTrainingCardDTO1 = new MTrainingCardDTO();
        mTrainingCardDTO1.setId(1L);
        MTrainingCardDTO mTrainingCardDTO2 = new MTrainingCardDTO();
        assertThat(mTrainingCardDTO1).isNotEqualTo(mTrainingCardDTO2);
        mTrainingCardDTO2.setId(mTrainingCardDTO1.getId());
        assertThat(mTrainingCardDTO1).isEqualTo(mTrainingCardDTO2);
        mTrainingCardDTO2.setId(2L);
        assertThat(mTrainingCardDTO1).isNotEqualTo(mTrainingCardDTO2);
        mTrainingCardDTO1.setId(null);
        assertThat(mTrainingCardDTO1).isNotEqualTo(mTrainingCardDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(mTrainingCardMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(mTrainingCardMapper.fromId(null)).isNull();
    }
}
