package io.shm.tsubasa.web.rest;

import io.shm.tsubasa.TsubasaApp;
import io.shm.tsubasa.domain.MContentableCard;
import io.shm.tsubasa.repository.MContentableCardRepository;
import io.shm.tsubasa.service.MContentableCardService;
import io.shm.tsubasa.service.dto.MContentableCardDTO;
import io.shm.tsubasa.service.mapper.MContentableCardMapper;
import io.shm.tsubasa.web.rest.errors.ExceptionTranslator;
import io.shm.tsubasa.service.dto.MContentableCardCriteria;
import io.shm.tsubasa.service.MContentableCardQueryService;

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
 * Integration tests for the {@Link MContentableCardResource} REST controller.
 */
@SpringBootTest(classes = TsubasaApp.class)
public class MContentableCardResourceIT {

    private static final Integer DEFAULT_PLAYABLE_CARD_ID = 1;
    private static final Integer UPDATED_PLAYABLE_CARD_ID = 2;

    private static final Integer DEFAULT_LEVEL = 1;
    private static final Integer UPDATED_LEVEL = 2;

    private static final Integer DEFAULT_ACTION_MAIN_LEVEL = 1;
    private static final Integer UPDATED_ACTION_MAIN_LEVEL = 2;

    private static final Integer DEFAULT_ACTION_SUB_1_LEVEL = 1;
    private static final Integer UPDATED_ACTION_SUB_1_LEVEL = 2;

    private static final Integer DEFAULT_ACTION_SUB_2_LEVEL = 1;
    private static final Integer UPDATED_ACTION_SUB_2_LEVEL = 2;

    private static final Integer DEFAULT_ACTION_SUB_3_LEVEL = 1;
    private static final Integer UPDATED_ACTION_SUB_3_LEVEL = 2;

    private static final Integer DEFAULT_ACTION_SUB_4_LEVEL = 1;
    private static final Integer UPDATED_ACTION_SUB_4_LEVEL = 2;

    private static final Integer DEFAULT_PLUS_RATE = 1;
    private static final Integer UPDATED_PLUS_RATE = 2;

    @Autowired
    private MContentableCardRepository mContentableCardRepository;

    @Autowired
    private MContentableCardMapper mContentableCardMapper;

    @Autowired
    private MContentableCardService mContentableCardService;

    @Autowired
    private MContentableCardQueryService mContentableCardQueryService;

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

    private MockMvc restMContentableCardMockMvc;

    private MContentableCard mContentableCard;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final MContentableCardResource mContentableCardResource = new MContentableCardResource(mContentableCardService, mContentableCardQueryService);
        this.restMContentableCardMockMvc = MockMvcBuilders.standaloneSetup(mContentableCardResource)
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
    public static MContentableCard createEntity(EntityManager em) {
        MContentableCard mContentableCard = new MContentableCard()
            .playableCardId(DEFAULT_PLAYABLE_CARD_ID)
            .level(DEFAULT_LEVEL)
            .actionMainLevel(DEFAULT_ACTION_MAIN_LEVEL)
            .actionSub1Level(DEFAULT_ACTION_SUB_1_LEVEL)
            .actionSub2Level(DEFAULT_ACTION_SUB_2_LEVEL)
            .actionSub3Level(DEFAULT_ACTION_SUB_3_LEVEL)
            .actionSub4Level(DEFAULT_ACTION_SUB_4_LEVEL)
            .plusRate(DEFAULT_PLUS_RATE);
        return mContentableCard;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static MContentableCard createUpdatedEntity(EntityManager em) {
        MContentableCard mContentableCard = new MContentableCard()
            .playableCardId(UPDATED_PLAYABLE_CARD_ID)
            .level(UPDATED_LEVEL)
            .actionMainLevel(UPDATED_ACTION_MAIN_LEVEL)
            .actionSub1Level(UPDATED_ACTION_SUB_1_LEVEL)
            .actionSub2Level(UPDATED_ACTION_SUB_2_LEVEL)
            .actionSub3Level(UPDATED_ACTION_SUB_3_LEVEL)
            .actionSub4Level(UPDATED_ACTION_SUB_4_LEVEL)
            .plusRate(UPDATED_PLUS_RATE);
        return mContentableCard;
    }

    @BeforeEach
    public void initTest() {
        mContentableCard = createEntity(em);
    }

    @Test
    @Transactional
    public void createMContentableCard() throws Exception {
        int databaseSizeBeforeCreate = mContentableCardRepository.findAll().size();

        // Create the MContentableCard
        MContentableCardDTO mContentableCardDTO = mContentableCardMapper.toDto(mContentableCard);
        restMContentableCardMockMvc.perform(post("/api/m-contentable-cards")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mContentableCardDTO)))
            .andExpect(status().isCreated());

        // Validate the MContentableCard in the database
        List<MContentableCard> mContentableCardList = mContentableCardRepository.findAll();
        assertThat(mContentableCardList).hasSize(databaseSizeBeforeCreate + 1);
        MContentableCard testMContentableCard = mContentableCardList.get(mContentableCardList.size() - 1);
        assertThat(testMContentableCard.getPlayableCardId()).isEqualTo(DEFAULT_PLAYABLE_CARD_ID);
        assertThat(testMContentableCard.getLevel()).isEqualTo(DEFAULT_LEVEL);
        assertThat(testMContentableCard.getActionMainLevel()).isEqualTo(DEFAULT_ACTION_MAIN_LEVEL);
        assertThat(testMContentableCard.getActionSub1Level()).isEqualTo(DEFAULT_ACTION_SUB_1_LEVEL);
        assertThat(testMContentableCard.getActionSub2Level()).isEqualTo(DEFAULT_ACTION_SUB_2_LEVEL);
        assertThat(testMContentableCard.getActionSub3Level()).isEqualTo(DEFAULT_ACTION_SUB_3_LEVEL);
        assertThat(testMContentableCard.getActionSub4Level()).isEqualTo(DEFAULT_ACTION_SUB_4_LEVEL);
        assertThat(testMContentableCard.getPlusRate()).isEqualTo(DEFAULT_PLUS_RATE);
    }

    @Test
    @Transactional
    public void createMContentableCardWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = mContentableCardRepository.findAll().size();

        // Create the MContentableCard with an existing ID
        mContentableCard.setId(1L);
        MContentableCardDTO mContentableCardDTO = mContentableCardMapper.toDto(mContentableCard);

        // An entity with an existing ID cannot be created, so this API call must fail
        restMContentableCardMockMvc.perform(post("/api/m-contentable-cards")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mContentableCardDTO)))
            .andExpect(status().isBadRequest());

        // Validate the MContentableCard in the database
        List<MContentableCard> mContentableCardList = mContentableCardRepository.findAll();
        assertThat(mContentableCardList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkPlayableCardIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = mContentableCardRepository.findAll().size();
        // set the field null
        mContentableCard.setPlayableCardId(null);

        // Create the MContentableCard, which fails.
        MContentableCardDTO mContentableCardDTO = mContentableCardMapper.toDto(mContentableCard);

        restMContentableCardMockMvc.perform(post("/api/m-contentable-cards")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mContentableCardDTO)))
            .andExpect(status().isBadRequest());

        List<MContentableCard> mContentableCardList = mContentableCardRepository.findAll();
        assertThat(mContentableCardList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkLevelIsRequired() throws Exception {
        int databaseSizeBeforeTest = mContentableCardRepository.findAll().size();
        // set the field null
        mContentableCard.setLevel(null);

        // Create the MContentableCard, which fails.
        MContentableCardDTO mContentableCardDTO = mContentableCardMapper.toDto(mContentableCard);

        restMContentableCardMockMvc.perform(post("/api/m-contentable-cards")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mContentableCardDTO)))
            .andExpect(status().isBadRequest());

        List<MContentableCard> mContentableCardList = mContentableCardRepository.findAll();
        assertThat(mContentableCardList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkActionMainLevelIsRequired() throws Exception {
        int databaseSizeBeforeTest = mContentableCardRepository.findAll().size();
        // set the field null
        mContentableCard.setActionMainLevel(null);

        // Create the MContentableCard, which fails.
        MContentableCardDTO mContentableCardDTO = mContentableCardMapper.toDto(mContentableCard);

        restMContentableCardMockMvc.perform(post("/api/m-contentable-cards")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mContentableCardDTO)))
            .andExpect(status().isBadRequest());

        List<MContentableCard> mContentableCardList = mContentableCardRepository.findAll();
        assertThat(mContentableCardList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkActionSub1LevelIsRequired() throws Exception {
        int databaseSizeBeforeTest = mContentableCardRepository.findAll().size();
        // set the field null
        mContentableCard.setActionSub1Level(null);

        // Create the MContentableCard, which fails.
        MContentableCardDTO mContentableCardDTO = mContentableCardMapper.toDto(mContentableCard);

        restMContentableCardMockMvc.perform(post("/api/m-contentable-cards")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mContentableCardDTO)))
            .andExpect(status().isBadRequest());

        List<MContentableCard> mContentableCardList = mContentableCardRepository.findAll();
        assertThat(mContentableCardList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkActionSub2LevelIsRequired() throws Exception {
        int databaseSizeBeforeTest = mContentableCardRepository.findAll().size();
        // set the field null
        mContentableCard.setActionSub2Level(null);

        // Create the MContentableCard, which fails.
        MContentableCardDTO mContentableCardDTO = mContentableCardMapper.toDto(mContentableCard);

        restMContentableCardMockMvc.perform(post("/api/m-contentable-cards")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mContentableCardDTO)))
            .andExpect(status().isBadRequest());

        List<MContentableCard> mContentableCardList = mContentableCardRepository.findAll();
        assertThat(mContentableCardList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkActionSub3LevelIsRequired() throws Exception {
        int databaseSizeBeforeTest = mContentableCardRepository.findAll().size();
        // set the field null
        mContentableCard.setActionSub3Level(null);

        // Create the MContentableCard, which fails.
        MContentableCardDTO mContentableCardDTO = mContentableCardMapper.toDto(mContentableCard);

        restMContentableCardMockMvc.perform(post("/api/m-contentable-cards")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mContentableCardDTO)))
            .andExpect(status().isBadRequest());

        List<MContentableCard> mContentableCardList = mContentableCardRepository.findAll();
        assertThat(mContentableCardList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkActionSub4LevelIsRequired() throws Exception {
        int databaseSizeBeforeTest = mContentableCardRepository.findAll().size();
        // set the field null
        mContentableCard.setActionSub4Level(null);

        // Create the MContentableCard, which fails.
        MContentableCardDTO mContentableCardDTO = mContentableCardMapper.toDto(mContentableCard);

        restMContentableCardMockMvc.perform(post("/api/m-contentable-cards")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mContentableCardDTO)))
            .andExpect(status().isBadRequest());

        List<MContentableCard> mContentableCardList = mContentableCardRepository.findAll();
        assertThat(mContentableCardList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkPlusRateIsRequired() throws Exception {
        int databaseSizeBeforeTest = mContentableCardRepository.findAll().size();
        // set the field null
        mContentableCard.setPlusRate(null);

        // Create the MContentableCard, which fails.
        MContentableCardDTO mContentableCardDTO = mContentableCardMapper.toDto(mContentableCard);

        restMContentableCardMockMvc.perform(post("/api/m-contentable-cards")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mContentableCardDTO)))
            .andExpect(status().isBadRequest());

        List<MContentableCard> mContentableCardList = mContentableCardRepository.findAll();
        assertThat(mContentableCardList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllMContentableCards() throws Exception {
        // Initialize the database
        mContentableCardRepository.saveAndFlush(mContentableCard);

        // Get all the mContentableCardList
        restMContentableCardMockMvc.perform(get("/api/m-contentable-cards?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(mContentableCard.getId().intValue())))
            .andExpect(jsonPath("$.[*].playableCardId").value(hasItem(DEFAULT_PLAYABLE_CARD_ID)))
            .andExpect(jsonPath("$.[*].level").value(hasItem(DEFAULT_LEVEL)))
            .andExpect(jsonPath("$.[*].actionMainLevel").value(hasItem(DEFAULT_ACTION_MAIN_LEVEL)))
            .andExpect(jsonPath("$.[*].actionSub1Level").value(hasItem(DEFAULT_ACTION_SUB_1_LEVEL)))
            .andExpect(jsonPath("$.[*].actionSub2Level").value(hasItem(DEFAULT_ACTION_SUB_2_LEVEL)))
            .andExpect(jsonPath("$.[*].actionSub3Level").value(hasItem(DEFAULT_ACTION_SUB_3_LEVEL)))
            .andExpect(jsonPath("$.[*].actionSub4Level").value(hasItem(DEFAULT_ACTION_SUB_4_LEVEL)))
            .andExpect(jsonPath("$.[*].plusRate").value(hasItem(DEFAULT_PLUS_RATE)));
    }
    
    @Test
    @Transactional
    public void getMContentableCard() throws Exception {
        // Initialize the database
        mContentableCardRepository.saveAndFlush(mContentableCard);

        // Get the mContentableCard
        restMContentableCardMockMvc.perform(get("/api/m-contentable-cards/{id}", mContentableCard.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(mContentableCard.getId().intValue()))
            .andExpect(jsonPath("$.playableCardId").value(DEFAULT_PLAYABLE_CARD_ID))
            .andExpect(jsonPath("$.level").value(DEFAULT_LEVEL))
            .andExpect(jsonPath("$.actionMainLevel").value(DEFAULT_ACTION_MAIN_LEVEL))
            .andExpect(jsonPath("$.actionSub1Level").value(DEFAULT_ACTION_SUB_1_LEVEL))
            .andExpect(jsonPath("$.actionSub2Level").value(DEFAULT_ACTION_SUB_2_LEVEL))
            .andExpect(jsonPath("$.actionSub3Level").value(DEFAULT_ACTION_SUB_3_LEVEL))
            .andExpect(jsonPath("$.actionSub4Level").value(DEFAULT_ACTION_SUB_4_LEVEL))
            .andExpect(jsonPath("$.plusRate").value(DEFAULT_PLUS_RATE));
    }

    @Test
    @Transactional
    public void getAllMContentableCardsByPlayableCardIdIsEqualToSomething() throws Exception {
        // Initialize the database
        mContentableCardRepository.saveAndFlush(mContentableCard);

        // Get all the mContentableCardList where playableCardId equals to DEFAULT_PLAYABLE_CARD_ID
        defaultMContentableCardShouldBeFound("playableCardId.equals=" + DEFAULT_PLAYABLE_CARD_ID);

        // Get all the mContentableCardList where playableCardId equals to UPDATED_PLAYABLE_CARD_ID
        defaultMContentableCardShouldNotBeFound("playableCardId.equals=" + UPDATED_PLAYABLE_CARD_ID);
    }

    @Test
    @Transactional
    public void getAllMContentableCardsByPlayableCardIdIsInShouldWork() throws Exception {
        // Initialize the database
        mContentableCardRepository.saveAndFlush(mContentableCard);

        // Get all the mContentableCardList where playableCardId in DEFAULT_PLAYABLE_CARD_ID or UPDATED_PLAYABLE_CARD_ID
        defaultMContentableCardShouldBeFound("playableCardId.in=" + DEFAULT_PLAYABLE_CARD_ID + "," + UPDATED_PLAYABLE_CARD_ID);

        // Get all the mContentableCardList where playableCardId equals to UPDATED_PLAYABLE_CARD_ID
        defaultMContentableCardShouldNotBeFound("playableCardId.in=" + UPDATED_PLAYABLE_CARD_ID);
    }

    @Test
    @Transactional
    public void getAllMContentableCardsByPlayableCardIdIsNullOrNotNull() throws Exception {
        // Initialize the database
        mContentableCardRepository.saveAndFlush(mContentableCard);

        // Get all the mContentableCardList where playableCardId is not null
        defaultMContentableCardShouldBeFound("playableCardId.specified=true");

        // Get all the mContentableCardList where playableCardId is null
        defaultMContentableCardShouldNotBeFound("playableCardId.specified=false");
    }

    @Test
    @Transactional
    public void getAllMContentableCardsByPlayableCardIdIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mContentableCardRepository.saveAndFlush(mContentableCard);

        // Get all the mContentableCardList where playableCardId greater than or equals to DEFAULT_PLAYABLE_CARD_ID
        defaultMContentableCardShouldBeFound("playableCardId.greaterOrEqualThan=" + DEFAULT_PLAYABLE_CARD_ID);

        // Get all the mContentableCardList where playableCardId greater than or equals to UPDATED_PLAYABLE_CARD_ID
        defaultMContentableCardShouldNotBeFound("playableCardId.greaterOrEqualThan=" + UPDATED_PLAYABLE_CARD_ID);
    }

    @Test
    @Transactional
    public void getAllMContentableCardsByPlayableCardIdIsLessThanSomething() throws Exception {
        // Initialize the database
        mContentableCardRepository.saveAndFlush(mContentableCard);

        // Get all the mContentableCardList where playableCardId less than or equals to DEFAULT_PLAYABLE_CARD_ID
        defaultMContentableCardShouldNotBeFound("playableCardId.lessThan=" + DEFAULT_PLAYABLE_CARD_ID);

        // Get all the mContentableCardList where playableCardId less than or equals to UPDATED_PLAYABLE_CARD_ID
        defaultMContentableCardShouldBeFound("playableCardId.lessThan=" + UPDATED_PLAYABLE_CARD_ID);
    }


    @Test
    @Transactional
    public void getAllMContentableCardsByLevelIsEqualToSomething() throws Exception {
        // Initialize the database
        mContentableCardRepository.saveAndFlush(mContentableCard);

        // Get all the mContentableCardList where level equals to DEFAULT_LEVEL
        defaultMContentableCardShouldBeFound("level.equals=" + DEFAULT_LEVEL);

        // Get all the mContentableCardList where level equals to UPDATED_LEVEL
        defaultMContentableCardShouldNotBeFound("level.equals=" + UPDATED_LEVEL);
    }

    @Test
    @Transactional
    public void getAllMContentableCardsByLevelIsInShouldWork() throws Exception {
        // Initialize the database
        mContentableCardRepository.saveAndFlush(mContentableCard);

        // Get all the mContentableCardList where level in DEFAULT_LEVEL or UPDATED_LEVEL
        defaultMContentableCardShouldBeFound("level.in=" + DEFAULT_LEVEL + "," + UPDATED_LEVEL);

        // Get all the mContentableCardList where level equals to UPDATED_LEVEL
        defaultMContentableCardShouldNotBeFound("level.in=" + UPDATED_LEVEL);
    }

    @Test
    @Transactional
    public void getAllMContentableCardsByLevelIsNullOrNotNull() throws Exception {
        // Initialize the database
        mContentableCardRepository.saveAndFlush(mContentableCard);

        // Get all the mContentableCardList where level is not null
        defaultMContentableCardShouldBeFound("level.specified=true");

        // Get all the mContentableCardList where level is null
        defaultMContentableCardShouldNotBeFound("level.specified=false");
    }

    @Test
    @Transactional
    public void getAllMContentableCardsByLevelIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mContentableCardRepository.saveAndFlush(mContentableCard);

        // Get all the mContentableCardList where level greater than or equals to DEFAULT_LEVEL
        defaultMContentableCardShouldBeFound("level.greaterOrEqualThan=" + DEFAULT_LEVEL);

        // Get all the mContentableCardList where level greater than or equals to UPDATED_LEVEL
        defaultMContentableCardShouldNotBeFound("level.greaterOrEqualThan=" + UPDATED_LEVEL);
    }

    @Test
    @Transactional
    public void getAllMContentableCardsByLevelIsLessThanSomething() throws Exception {
        // Initialize the database
        mContentableCardRepository.saveAndFlush(mContentableCard);

        // Get all the mContentableCardList where level less than or equals to DEFAULT_LEVEL
        defaultMContentableCardShouldNotBeFound("level.lessThan=" + DEFAULT_LEVEL);

        // Get all the mContentableCardList where level less than or equals to UPDATED_LEVEL
        defaultMContentableCardShouldBeFound("level.lessThan=" + UPDATED_LEVEL);
    }


    @Test
    @Transactional
    public void getAllMContentableCardsByActionMainLevelIsEqualToSomething() throws Exception {
        // Initialize the database
        mContentableCardRepository.saveAndFlush(mContentableCard);

        // Get all the mContentableCardList where actionMainLevel equals to DEFAULT_ACTION_MAIN_LEVEL
        defaultMContentableCardShouldBeFound("actionMainLevel.equals=" + DEFAULT_ACTION_MAIN_LEVEL);

        // Get all the mContentableCardList where actionMainLevel equals to UPDATED_ACTION_MAIN_LEVEL
        defaultMContentableCardShouldNotBeFound("actionMainLevel.equals=" + UPDATED_ACTION_MAIN_LEVEL);
    }

    @Test
    @Transactional
    public void getAllMContentableCardsByActionMainLevelIsInShouldWork() throws Exception {
        // Initialize the database
        mContentableCardRepository.saveAndFlush(mContentableCard);

        // Get all the mContentableCardList where actionMainLevel in DEFAULT_ACTION_MAIN_LEVEL or UPDATED_ACTION_MAIN_LEVEL
        defaultMContentableCardShouldBeFound("actionMainLevel.in=" + DEFAULT_ACTION_MAIN_LEVEL + "," + UPDATED_ACTION_MAIN_LEVEL);

        // Get all the mContentableCardList where actionMainLevel equals to UPDATED_ACTION_MAIN_LEVEL
        defaultMContentableCardShouldNotBeFound("actionMainLevel.in=" + UPDATED_ACTION_MAIN_LEVEL);
    }

    @Test
    @Transactional
    public void getAllMContentableCardsByActionMainLevelIsNullOrNotNull() throws Exception {
        // Initialize the database
        mContentableCardRepository.saveAndFlush(mContentableCard);

        // Get all the mContentableCardList where actionMainLevel is not null
        defaultMContentableCardShouldBeFound("actionMainLevel.specified=true");

        // Get all the mContentableCardList where actionMainLevel is null
        defaultMContentableCardShouldNotBeFound("actionMainLevel.specified=false");
    }

    @Test
    @Transactional
    public void getAllMContentableCardsByActionMainLevelIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mContentableCardRepository.saveAndFlush(mContentableCard);

        // Get all the mContentableCardList where actionMainLevel greater than or equals to DEFAULT_ACTION_MAIN_LEVEL
        defaultMContentableCardShouldBeFound("actionMainLevel.greaterOrEqualThan=" + DEFAULT_ACTION_MAIN_LEVEL);

        // Get all the mContentableCardList where actionMainLevel greater than or equals to UPDATED_ACTION_MAIN_LEVEL
        defaultMContentableCardShouldNotBeFound("actionMainLevel.greaterOrEqualThan=" + UPDATED_ACTION_MAIN_LEVEL);
    }

    @Test
    @Transactional
    public void getAllMContentableCardsByActionMainLevelIsLessThanSomething() throws Exception {
        // Initialize the database
        mContentableCardRepository.saveAndFlush(mContentableCard);

        // Get all the mContentableCardList where actionMainLevel less than or equals to DEFAULT_ACTION_MAIN_LEVEL
        defaultMContentableCardShouldNotBeFound("actionMainLevel.lessThan=" + DEFAULT_ACTION_MAIN_LEVEL);

        // Get all the mContentableCardList where actionMainLevel less than or equals to UPDATED_ACTION_MAIN_LEVEL
        defaultMContentableCardShouldBeFound("actionMainLevel.lessThan=" + UPDATED_ACTION_MAIN_LEVEL);
    }


    @Test
    @Transactional
    public void getAllMContentableCardsByActionSub1LevelIsEqualToSomething() throws Exception {
        // Initialize the database
        mContentableCardRepository.saveAndFlush(mContentableCard);

        // Get all the mContentableCardList where actionSub1Level equals to DEFAULT_ACTION_SUB_1_LEVEL
        defaultMContentableCardShouldBeFound("actionSub1Level.equals=" + DEFAULT_ACTION_SUB_1_LEVEL);

        // Get all the mContentableCardList where actionSub1Level equals to UPDATED_ACTION_SUB_1_LEVEL
        defaultMContentableCardShouldNotBeFound("actionSub1Level.equals=" + UPDATED_ACTION_SUB_1_LEVEL);
    }

    @Test
    @Transactional
    public void getAllMContentableCardsByActionSub1LevelIsInShouldWork() throws Exception {
        // Initialize the database
        mContentableCardRepository.saveAndFlush(mContentableCard);

        // Get all the mContentableCardList where actionSub1Level in DEFAULT_ACTION_SUB_1_LEVEL or UPDATED_ACTION_SUB_1_LEVEL
        defaultMContentableCardShouldBeFound("actionSub1Level.in=" + DEFAULT_ACTION_SUB_1_LEVEL + "," + UPDATED_ACTION_SUB_1_LEVEL);

        // Get all the mContentableCardList where actionSub1Level equals to UPDATED_ACTION_SUB_1_LEVEL
        defaultMContentableCardShouldNotBeFound("actionSub1Level.in=" + UPDATED_ACTION_SUB_1_LEVEL);
    }

    @Test
    @Transactional
    public void getAllMContentableCardsByActionSub1LevelIsNullOrNotNull() throws Exception {
        // Initialize the database
        mContentableCardRepository.saveAndFlush(mContentableCard);

        // Get all the mContentableCardList where actionSub1Level is not null
        defaultMContentableCardShouldBeFound("actionSub1Level.specified=true");

        // Get all the mContentableCardList where actionSub1Level is null
        defaultMContentableCardShouldNotBeFound("actionSub1Level.specified=false");
    }

    @Test
    @Transactional
    public void getAllMContentableCardsByActionSub1LevelIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mContentableCardRepository.saveAndFlush(mContentableCard);

        // Get all the mContentableCardList where actionSub1Level greater than or equals to DEFAULT_ACTION_SUB_1_LEVEL
        defaultMContentableCardShouldBeFound("actionSub1Level.greaterOrEqualThan=" + DEFAULT_ACTION_SUB_1_LEVEL);

        // Get all the mContentableCardList where actionSub1Level greater than or equals to UPDATED_ACTION_SUB_1_LEVEL
        defaultMContentableCardShouldNotBeFound("actionSub1Level.greaterOrEqualThan=" + UPDATED_ACTION_SUB_1_LEVEL);
    }

    @Test
    @Transactional
    public void getAllMContentableCardsByActionSub1LevelIsLessThanSomething() throws Exception {
        // Initialize the database
        mContentableCardRepository.saveAndFlush(mContentableCard);

        // Get all the mContentableCardList where actionSub1Level less than or equals to DEFAULT_ACTION_SUB_1_LEVEL
        defaultMContentableCardShouldNotBeFound("actionSub1Level.lessThan=" + DEFAULT_ACTION_SUB_1_LEVEL);

        // Get all the mContentableCardList where actionSub1Level less than or equals to UPDATED_ACTION_SUB_1_LEVEL
        defaultMContentableCardShouldBeFound("actionSub1Level.lessThan=" + UPDATED_ACTION_SUB_1_LEVEL);
    }


    @Test
    @Transactional
    public void getAllMContentableCardsByActionSub2LevelIsEqualToSomething() throws Exception {
        // Initialize the database
        mContentableCardRepository.saveAndFlush(mContentableCard);

        // Get all the mContentableCardList where actionSub2Level equals to DEFAULT_ACTION_SUB_2_LEVEL
        defaultMContentableCardShouldBeFound("actionSub2Level.equals=" + DEFAULT_ACTION_SUB_2_LEVEL);

        // Get all the mContentableCardList where actionSub2Level equals to UPDATED_ACTION_SUB_2_LEVEL
        defaultMContentableCardShouldNotBeFound("actionSub2Level.equals=" + UPDATED_ACTION_SUB_2_LEVEL);
    }

    @Test
    @Transactional
    public void getAllMContentableCardsByActionSub2LevelIsInShouldWork() throws Exception {
        // Initialize the database
        mContentableCardRepository.saveAndFlush(mContentableCard);

        // Get all the mContentableCardList where actionSub2Level in DEFAULT_ACTION_SUB_2_LEVEL or UPDATED_ACTION_SUB_2_LEVEL
        defaultMContentableCardShouldBeFound("actionSub2Level.in=" + DEFAULT_ACTION_SUB_2_LEVEL + "," + UPDATED_ACTION_SUB_2_LEVEL);

        // Get all the mContentableCardList where actionSub2Level equals to UPDATED_ACTION_SUB_2_LEVEL
        defaultMContentableCardShouldNotBeFound("actionSub2Level.in=" + UPDATED_ACTION_SUB_2_LEVEL);
    }

    @Test
    @Transactional
    public void getAllMContentableCardsByActionSub2LevelIsNullOrNotNull() throws Exception {
        // Initialize the database
        mContentableCardRepository.saveAndFlush(mContentableCard);

        // Get all the mContentableCardList where actionSub2Level is not null
        defaultMContentableCardShouldBeFound("actionSub2Level.specified=true");

        // Get all the mContentableCardList where actionSub2Level is null
        defaultMContentableCardShouldNotBeFound("actionSub2Level.specified=false");
    }

    @Test
    @Transactional
    public void getAllMContentableCardsByActionSub2LevelIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mContentableCardRepository.saveAndFlush(mContentableCard);

        // Get all the mContentableCardList where actionSub2Level greater than or equals to DEFAULT_ACTION_SUB_2_LEVEL
        defaultMContentableCardShouldBeFound("actionSub2Level.greaterOrEqualThan=" + DEFAULT_ACTION_SUB_2_LEVEL);

        // Get all the mContentableCardList where actionSub2Level greater than or equals to UPDATED_ACTION_SUB_2_LEVEL
        defaultMContentableCardShouldNotBeFound("actionSub2Level.greaterOrEqualThan=" + UPDATED_ACTION_SUB_2_LEVEL);
    }

    @Test
    @Transactional
    public void getAllMContentableCardsByActionSub2LevelIsLessThanSomething() throws Exception {
        // Initialize the database
        mContentableCardRepository.saveAndFlush(mContentableCard);

        // Get all the mContentableCardList where actionSub2Level less than or equals to DEFAULT_ACTION_SUB_2_LEVEL
        defaultMContentableCardShouldNotBeFound("actionSub2Level.lessThan=" + DEFAULT_ACTION_SUB_2_LEVEL);

        // Get all the mContentableCardList where actionSub2Level less than or equals to UPDATED_ACTION_SUB_2_LEVEL
        defaultMContentableCardShouldBeFound("actionSub2Level.lessThan=" + UPDATED_ACTION_SUB_2_LEVEL);
    }


    @Test
    @Transactional
    public void getAllMContentableCardsByActionSub3LevelIsEqualToSomething() throws Exception {
        // Initialize the database
        mContentableCardRepository.saveAndFlush(mContentableCard);

        // Get all the mContentableCardList where actionSub3Level equals to DEFAULT_ACTION_SUB_3_LEVEL
        defaultMContentableCardShouldBeFound("actionSub3Level.equals=" + DEFAULT_ACTION_SUB_3_LEVEL);

        // Get all the mContentableCardList where actionSub3Level equals to UPDATED_ACTION_SUB_3_LEVEL
        defaultMContentableCardShouldNotBeFound("actionSub3Level.equals=" + UPDATED_ACTION_SUB_3_LEVEL);
    }

    @Test
    @Transactional
    public void getAllMContentableCardsByActionSub3LevelIsInShouldWork() throws Exception {
        // Initialize the database
        mContentableCardRepository.saveAndFlush(mContentableCard);

        // Get all the mContentableCardList where actionSub3Level in DEFAULT_ACTION_SUB_3_LEVEL or UPDATED_ACTION_SUB_3_LEVEL
        defaultMContentableCardShouldBeFound("actionSub3Level.in=" + DEFAULT_ACTION_SUB_3_LEVEL + "," + UPDATED_ACTION_SUB_3_LEVEL);

        // Get all the mContentableCardList where actionSub3Level equals to UPDATED_ACTION_SUB_3_LEVEL
        defaultMContentableCardShouldNotBeFound("actionSub3Level.in=" + UPDATED_ACTION_SUB_3_LEVEL);
    }

    @Test
    @Transactional
    public void getAllMContentableCardsByActionSub3LevelIsNullOrNotNull() throws Exception {
        // Initialize the database
        mContentableCardRepository.saveAndFlush(mContentableCard);

        // Get all the mContentableCardList where actionSub3Level is not null
        defaultMContentableCardShouldBeFound("actionSub3Level.specified=true");

        // Get all the mContentableCardList where actionSub3Level is null
        defaultMContentableCardShouldNotBeFound("actionSub3Level.specified=false");
    }

    @Test
    @Transactional
    public void getAllMContentableCardsByActionSub3LevelIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mContentableCardRepository.saveAndFlush(mContentableCard);

        // Get all the mContentableCardList where actionSub3Level greater than or equals to DEFAULT_ACTION_SUB_3_LEVEL
        defaultMContentableCardShouldBeFound("actionSub3Level.greaterOrEqualThan=" + DEFAULT_ACTION_SUB_3_LEVEL);

        // Get all the mContentableCardList where actionSub3Level greater than or equals to UPDATED_ACTION_SUB_3_LEVEL
        defaultMContentableCardShouldNotBeFound("actionSub3Level.greaterOrEqualThan=" + UPDATED_ACTION_SUB_3_LEVEL);
    }

    @Test
    @Transactional
    public void getAllMContentableCardsByActionSub3LevelIsLessThanSomething() throws Exception {
        // Initialize the database
        mContentableCardRepository.saveAndFlush(mContentableCard);

        // Get all the mContentableCardList where actionSub3Level less than or equals to DEFAULT_ACTION_SUB_3_LEVEL
        defaultMContentableCardShouldNotBeFound("actionSub3Level.lessThan=" + DEFAULT_ACTION_SUB_3_LEVEL);

        // Get all the mContentableCardList where actionSub3Level less than or equals to UPDATED_ACTION_SUB_3_LEVEL
        defaultMContentableCardShouldBeFound("actionSub3Level.lessThan=" + UPDATED_ACTION_SUB_3_LEVEL);
    }


    @Test
    @Transactional
    public void getAllMContentableCardsByActionSub4LevelIsEqualToSomething() throws Exception {
        // Initialize the database
        mContentableCardRepository.saveAndFlush(mContentableCard);

        // Get all the mContentableCardList where actionSub4Level equals to DEFAULT_ACTION_SUB_4_LEVEL
        defaultMContentableCardShouldBeFound("actionSub4Level.equals=" + DEFAULT_ACTION_SUB_4_LEVEL);

        // Get all the mContentableCardList where actionSub4Level equals to UPDATED_ACTION_SUB_4_LEVEL
        defaultMContentableCardShouldNotBeFound("actionSub4Level.equals=" + UPDATED_ACTION_SUB_4_LEVEL);
    }

    @Test
    @Transactional
    public void getAllMContentableCardsByActionSub4LevelIsInShouldWork() throws Exception {
        // Initialize the database
        mContentableCardRepository.saveAndFlush(mContentableCard);

        // Get all the mContentableCardList where actionSub4Level in DEFAULT_ACTION_SUB_4_LEVEL or UPDATED_ACTION_SUB_4_LEVEL
        defaultMContentableCardShouldBeFound("actionSub4Level.in=" + DEFAULT_ACTION_SUB_4_LEVEL + "," + UPDATED_ACTION_SUB_4_LEVEL);

        // Get all the mContentableCardList where actionSub4Level equals to UPDATED_ACTION_SUB_4_LEVEL
        defaultMContentableCardShouldNotBeFound("actionSub4Level.in=" + UPDATED_ACTION_SUB_4_LEVEL);
    }

    @Test
    @Transactional
    public void getAllMContentableCardsByActionSub4LevelIsNullOrNotNull() throws Exception {
        // Initialize the database
        mContentableCardRepository.saveAndFlush(mContentableCard);

        // Get all the mContentableCardList where actionSub4Level is not null
        defaultMContentableCardShouldBeFound("actionSub4Level.specified=true");

        // Get all the mContentableCardList where actionSub4Level is null
        defaultMContentableCardShouldNotBeFound("actionSub4Level.specified=false");
    }

    @Test
    @Transactional
    public void getAllMContentableCardsByActionSub4LevelIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mContentableCardRepository.saveAndFlush(mContentableCard);

        // Get all the mContentableCardList where actionSub4Level greater than or equals to DEFAULT_ACTION_SUB_4_LEVEL
        defaultMContentableCardShouldBeFound("actionSub4Level.greaterOrEqualThan=" + DEFAULT_ACTION_SUB_4_LEVEL);

        // Get all the mContentableCardList where actionSub4Level greater than or equals to UPDATED_ACTION_SUB_4_LEVEL
        defaultMContentableCardShouldNotBeFound("actionSub4Level.greaterOrEqualThan=" + UPDATED_ACTION_SUB_4_LEVEL);
    }

    @Test
    @Transactional
    public void getAllMContentableCardsByActionSub4LevelIsLessThanSomething() throws Exception {
        // Initialize the database
        mContentableCardRepository.saveAndFlush(mContentableCard);

        // Get all the mContentableCardList where actionSub4Level less than or equals to DEFAULT_ACTION_SUB_4_LEVEL
        defaultMContentableCardShouldNotBeFound("actionSub4Level.lessThan=" + DEFAULT_ACTION_SUB_4_LEVEL);

        // Get all the mContentableCardList where actionSub4Level less than or equals to UPDATED_ACTION_SUB_4_LEVEL
        defaultMContentableCardShouldBeFound("actionSub4Level.lessThan=" + UPDATED_ACTION_SUB_4_LEVEL);
    }


    @Test
    @Transactional
    public void getAllMContentableCardsByPlusRateIsEqualToSomething() throws Exception {
        // Initialize the database
        mContentableCardRepository.saveAndFlush(mContentableCard);

        // Get all the mContentableCardList where plusRate equals to DEFAULT_PLUS_RATE
        defaultMContentableCardShouldBeFound("plusRate.equals=" + DEFAULT_PLUS_RATE);

        // Get all the mContentableCardList where plusRate equals to UPDATED_PLUS_RATE
        defaultMContentableCardShouldNotBeFound("plusRate.equals=" + UPDATED_PLUS_RATE);
    }

    @Test
    @Transactional
    public void getAllMContentableCardsByPlusRateIsInShouldWork() throws Exception {
        // Initialize the database
        mContentableCardRepository.saveAndFlush(mContentableCard);

        // Get all the mContentableCardList where plusRate in DEFAULT_PLUS_RATE or UPDATED_PLUS_RATE
        defaultMContentableCardShouldBeFound("plusRate.in=" + DEFAULT_PLUS_RATE + "," + UPDATED_PLUS_RATE);

        // Get all the mContentableCardList where plusRate equals to UPDATED_PLUS_RATE
        defaultMContentableCardShouldNotBeFound("plusRate.in=" + UPDATED_PLUS_RATE);
    }

    @Test
    @Transactional
    public void getAllMContentableCardsByPlusRateIsNullOrNotNull() throws Exception {
        // Initialize the database
        mContentableCardRepository.saveAndFlush(mContentableCard);

        // Get all the mContentableCardList where plusRate is not null
        defaultMContentableCardShouldBeFound("plusRate.specified=true");

        // Get all the mContentableCardList where plusRate is null
        defaultMContentableCardShouldNotBeFound("plusRate.specified=false");
    }

    @Test
    @Transactional
    public void getAllMContentableCardsByPlusRateIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mContentableCardRepository.saveAndFlush(mContentableCard);

        // Get all the mContentableCardList where plusRate greater than or equals to DEFAULT_PLUS_RATE
        defaultMContentableCardShouldBeFound("plusRate.greaterOrEqualThan=" + DEFAULT_PLUS_RATE);

        // Get all the mContentableCardList where plusRate greater than or equals to UPDATED_PLUS_RATE
        defaultMContentableCardShouldNotBeFound("plusRate.greaterOrEqualThan=" + UPDATED_PLUS_RATE);
    }

    @Test
    @Transactional
    public void getAllMContentableCardsByPlusRateIsLessThanSomething() throws Exception {
        // Initialize the database
        mContentableCardRepository.saveAndFlush(mContentableCard);

        // Get all the mContentableCardList where plusRate less than or equals to DEFAULT_PLUS_RATE
        defaultMContentableCardShouldNotBeFound("plusRate.lessThan=" + DEFAULT_PLUS_RATE);

        // Get all the mContentableCardList where plusRate less than or equals to UPDATED_PLUS_RATE
        defaultMContentableCardShouldBeFound("plusRate.lessThan=" + UPDATED_PLUS_RATE);
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultMContentableCardShouldBeFound(String filter) throws Exception {
        restMContentableCardMockMvc.perform(get("/api/m-contentable-cards?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(mContentableCard.getId().intValue())))
            .andExpect(jsonPath("$.[*].playableCardId").value(hasItem(DEFAULT_PLAYABLE_CARD_ID)))
            .andExpect(jsonPath("$.[*].level").value(hasItem(DEFAULT_LEVEL)))
            .andExpect(jsonPath("$.[*].actionMainLevel").value(hasItem(DEFAULT_ACTION_MAIN_LEVEL)))
            .andExpect(jsonPath("$.[*].actionSub1Level").value(hasItem(DEFAULT_ACTION_SUB_1_LEVEL)))
            .andExpect(jsonPath("$.[*].actionSub2Level").value(hasItem(DEFAULT_ACTION_SUB_2_LEVEL)))
            .andExpect(jsonPath("$.[*].actionSub3Level").value(hasItem(DEFAULT_ACTION_SUB_3_LEVEL)))
            .andExpect(jsonPath("$.[*].actionSub4Level").value(hasItem(DEFAULT_ACTION_SUB_4_LEVEL)))
            .andExpect(jsonPath("$.[*].plusRate").value(hasItem(DEFAULT_PLUS_RATE)));

        // Check, that the count call also returns 1
        restMContentableCardMockMvc.perform(get("/api/m-contentable-cards/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultMContentableCardShouldNotBeFound(String filter) throws Exception {
        restMContentableCardMockMvc.perform(get("/api/m-contentable-cards?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restMContentableCardMockMvc.perform(get("/api/m-contentable-cards/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("0"));
    }


    @Test
    @Transactional
    public void getNonExistingMContentableCard() throws Exception {
        // Get the mContentableCard
        restMContentableCardMockMvc.perform(get("/api/m-contentable-cards/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateMContentableCard() throws Exception {
        // Initialize the database
        mContentableCardRepository.saveAndFlush(mContentableCard);

        int databaseSizeBeforeUpdate = mContentableCardRepository.findAll().size();

        // Update the mContentableCard
        MContentableCard updatedMContentableCard = mContentableCardRepository.findById(mContentableCard.getId()).get();
        // Disconnect from session so that the updates on updatedMContentableCard are not directly saved in db
        em.detach(updatedMContentableCard);
        updatedMContentableCard
            .playableCardId(UPDATED_PLAYABLE_CARD_ID)
            .level(UPDATED_LEVEL)
            .actionMainLevel(UPDATED_ACTION_MAIN_LEVEL)
            .actionSub1Level(UPDATED_ACTION_SUB_1_LEVEL)
            .actionSub2Level(UPDATED_ACTION_SUB_2_LEVEL)
            .actionSub3Level(UPDATED_ACTION_SUB_3_LEVEL)
            .actionSub4Level(UPDATED_ACTION_SUB_4_LEVEL)
            .plusRate(UPDATED_PLUS_RATE);
        MContentableCardDTO mContentableCardDTO = mContentableCardMapper.toDto(updatedMContentableCard);

        restMContentableCardMockMvc.perform(put("/api/m-contentable-cards")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mContentableCardDTO)))
            .andExpect(status().isOk());

        // Validate the MContentableCard in the database
        List<MContentableCard> mContentableCardList = mContentableCardRepository.findAll();
        assertThat(mContentableCardList).hasSize(databaseSizeBeforeUpdate);
        MContentableCard testMContentableCard = mContentableCardList.get(mContentableCardList.size() - 1);
        assertThat(testMContentableCard.getPlayableCardId()).isEqualTo(UPDATED_PLAYABLE_CARD_ID);
        assertThat(testMContentableCard.getLevel()).isEqualTo(UPDATED_LEVEL);
        assertThat(testMContentableCard.getActionMainLevel()).isEqualTo(UPDATED_ACTION_MAIN_LEVEL);
        assertThat(testMContentableCard.getActionSub1Level()).isEqualTo(UPDATED_ACTION_SUB_1_LEVEL);
        assertThat(testMContentableCard.getActionSub2Level()).isEqualTo(UPDATED_ACTION_SUB_2_LEVEL);
        assertThat(testMContentableCard.getActionSub3Level()).isEqualTo(UPDATED_ACTION_SUB_3_LEVEL);
        assertThat(testMContentableCard.getActionSub4Level()).isEqualTo(UPDATED_ACTION_SUB_4_LEVEL);
        assertThat(testMContentableCard.getPlusRate()).isEqualTo(UPDATED_PLUS_RATE);
    }

    @Test
    @Transactional
    public void updateNonExistingMContentableCard() throws Exception {
        int databaseSizeBeforeUpdate = mContentableCardRepository.findAll().size();

        // Create the MContentableCard
        MContentableCardDTO mContentableCardDTO = mContentableCardMapper.toDto(mContentableCard);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restMContentableCardMockMvc.perform(put("/api/m-contentable-cards")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mContentableCardDTO)))
            .andExpect(status().isBadRequest());

        // Validate the MContentableCard in the database
        List<MContentableCard> mContentableCardList = mContentableCardRepository.findAll();
        assertThat(mContentableCardList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteMContentableCard() throws Exception {
        // Initialize the database
        mContentableCardRepository.saveAndFlush(mContentableCard);

        int databaseSizeBeforeDelete = mContentableCardRepository.findAll().size();

        // Delete the mContentableCard
        restMContentableCardMockMvc.perform(delete("/api/m-contentable-cards/{id}", mContentableCard.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database is empty
        List<MContentableCard> mContentableCardList = mContentableCardRepository.findAll();
        assertThat(mContentableCardList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(MContentableCard.class);
        MContentableCard mContentableCard1 = new MContentableCard();
        mContentableCard1.setId(1L);
        MContentableCard mContentableCard2 = new MContentableCard();
        mContentableCard2.setId(mContentableCard1.getId());
        assertThat(mContentableCard1).isEqualTo(mContentableCard2);
        mContentableCard2.setId(2L);
        assertThat(mContentableCard1).isNotEqualTo(mContentableCard2);
        mContentableCard1.setId(null);
        assertThat(mContentableCard1).isNotEqualTo(mContentableCard2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(MContentableCardDTO.class);
        MContentableCardDTO mContentableCardDTO1 = new MContentableCardDTO();
        mContentableCardDTO1.setId(1L);
        MContentableCardDTO mContentableCardDTO2 = new MContentableCardDTO();
        assertThat(mContentableCardDTO1).isNotEqualTo(mContentableCardDTO2);
        mContentableCardDTO2.setId(mContentableCardDTO1.getId());
        assertThat(mContentableCardDTO1).isEqualTo(mContentableCardDTO2);
        mContentableCardDTO2.setId(2L);
        assertThat(mContentableCardDTO1).isNotEqualTo(mContentableCardDTO2);
        mContentableCardDTO1.setId(null);
        assertThat(mContentableCardDTO1).isNotEqualTo(mContentableCardDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(mContentableCardMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(mContentableCardMapper.fromId(null)).isNull();
    }
}
