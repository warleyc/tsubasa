package io.shm.tsubasa.web.rest;

import io.shm.tsubasa.TsubasaApp;
import io.shm.tsubasa.domain.MGoalEffectiveCard;
import io.shm.tsubasa.repository.MGoalEffectiveCardRepository;
import io.shm.tsubasa.service.MGoalEffectiveCardService;
import io.shm.tsubasa.service.dto.MGoalEffectiveCardDTO;
import io.shm.tsubasa.service.mapper.MGoalEffectiveCardMapper;
import io.shm.tsubasa.web.rest.errors.ExceptionTranslator;
import io.shm.tsubasa.service.dto.MGoalEffectiveCardCriteria;
import io.shm.tsubasa.service.MGoalEffectiveCardQueryService;

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
 * Integration tests for the {@Link MGoalEffectiveCardResource} REST controller.
 */
@SpringBootTest(classes = TsubasaApp.class)
public class MGoalEffectiveCardResourceIT {

    private static final Integer DEFAULT_EVENT_TYPE = 1;
    private static final Integer UPDATED_EVENT_TYPE = 2;

    private static final Integer DEFAULT_EVENT_ID = 1;
    private static final Integer UPDATED_EVENT_ID = 2;

    private static final Integer DEFAULT_PLAYABLE_CARD_ID = 1;
    private static final Integer UPDATED_PLAYABLE_CARD_ID = 2;

    private static final Integer DEFAULT_RATE = 1;
    private static final Integer UPDATED_RATE = 2;

    @Autowired
    private MGoalEffectiveCardRepository mGoalEffectiveCardRepository;

    @Autowired
    private MGoalEffectiveCardMapper mGoalEffectiveCardMapper;

    @Autowired
    private MGoalEffectiveCardService mGoalEffectiveCardService;

    @Autowired
    private MGoalEffectiveCardQueryService mGoalEffectiveCardQueryService;

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

    private MockMvc restMGoalEffectiveCardMockMvc;

    private MGoalEffectiveCard mGoalEffectiveCard;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final MGoalEffectiveCardResource mGoalEffectiveCardResource = new MGoalEffectiveCardResource(mGoalEffectiveCardService, mGoalEffectiveCardQueryService);
        this.restMGoalEffectiveCardMockMvc = MockMvcBuilders.standaloneSetup(mGoalEffectiveCardResource)
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
    public static MGoalEffectiveCard createEntity(EntityManager em) {
        MGoalEffectiveCard mGoalEffectiveCard = new MGoalEffectiveCard()
            .eventType(DEFAULT_EVENT_TYPE)
            .eventId(DEFAULT_EVENT_ID)
            .playableCardId(DEFAULT_PLAYABLE_CARD_ID)
            .rate(DEFAULT_RATE);
        return mGoalEffectiveCard;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static MGoalEffectiveCard createUpdatedEntity(EntityManager em) {
        MGoalEffectiveCard mGoalEffectiveCard = new MGoalEffectiveCard()
            .eventType(UPDATED_EVENT_TYPE)
            .eventId(UPDATED_EVENT_ID)
            .playableCardId(UPDATED_PLAYABLE_CARD_ID)
            .rate(UPDATED_RATE);
        return mGoalEffectiveCard;
    }

    @BeforeEach
    public void initTest() {
        mGoalEffectiveCard = createEntity(em);
    }

    @Test
    @Transactional
    public void createMGoalEffectiveCard() throws Exception {
        int databaseSizeBeforeCreate = mGoalEffectiveCardRepository.findAll().size();

        // Create the MGoalEffectiveCard
        MGoalEffectiveCardDTO mGoalEffectiveCardDTO = mGoalEffectiveCardMapper.toDto(mGoalEffectiveCard);
        restMGoalEffectiveCardMockMvc.perform(post("/api/m-goal-effective-cards")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mGoalEffectiveCardDTO)))
            .andExpect(status().isCreated());

        // Validate the MGoalEffectiveCard in the database
        List<MGoalEffectiveCard> mGoalEffectiveCardList = mGoalEffectiveCardRepository.findAll();
        assertThat(mGoalEffectiveCardList).hasSize(databaseSizeBeforeCreate + 1);
        MGoalEffectiveCard testMGoalEffectiveCard = mGoalEffectiveCardList.get(mGoalEffectiveCardList.size() - 1);
        assertThat(testMGoalEffectiveCard.getEventType()).isEqualTo(DEFAULT_EVENT_TYPE);
        assertThat(testMGoalEffectiveCard.getEventId()).isEqualTo(DEFAULT_EVENT_ID);
        assertThat(testMGoalEffectiveCard.getPlayableCardId()).isEqualTo(DEFAULT_PLAYABLE_CARD_ID);
        assertThat(testMGoalEffectiveCard.getRate()).isEqualTo(DEFAULT_RATE);
    }

    @Test
    @Transactional
    public void createMGoalEffectiveCardWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = mGoalEffectiveCardRepository.findAll().size();

        // Create the MGoalEffectiveCard with an existing ID
        mGoalEffectiveCard.setId(1L);
        MGoalEffectiveCardDTO mGoalEffectiveCardDTO = mGoalEffectiveCardMapper.toDto(mGoalEffectiveCard);

        // An entity with an existing ID cannot be created, so this API call must fail
        restMGoalEffectiveCardMockMvc.perform(post("/api/m-goal-effective-cards")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mGoalEffectiveCardDTO)))
            .andExpect(status().isBadRequest());

        // Validate the MGoalEffectiveCard in the database
        List<MGoalEffectiveCard> mGoalEffectiveCardList = mGoalEffectiveCardRepository.findAll();
        assertThat(mGoalEffectiveCardList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkEventTypeIsRequired() throws Exception {
        int databaseSizeBeforeTest = mGoalEffectiveCardRepository.findAll().size();
        // set the field null
        mGoalEffectiveCard.setEventType(null);

        // Create the MGoalEffectiveCard, which fails.
        MGoalEffectiveCardDTO mGoalEffectiveCardDTO = mGoalEffectiveCardMapper.toDto(mGoalEffectiveCard);

        restMGoalEffectiveCardMockMvc.perform(post("/api/m-goal-effective-cards")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mGoalEffectiveCardDTO)))
            .andExpect(status().isBadRequest());

        List<MGoalEffectiveCard> mGoalEffectiveCardList = mGoalEffectiveCardRepository.findAll();
        assertThat(mGoalEffectiveCardList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkEventIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = mGoalEffectiveCardRepository.findAll().size();
        // set the field null
        mGoalEffectiveCard.setEventId(null);

        // Create the MGoalEffectiveCard, which fails.
        MGoalEffectiveCardDTO mGoalEffectiveCardDTO = mGoalEffectiveCardMapper.toDto(mGoalEffectiveCard);

        restMGoalEffectiveCardMockMvc.perform(post("/api/m-goal-effective-cards")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mGoalEffectiveCardDTO)))
            .andExpect(status().isBadRequest());

        List<MGoalEffectiveCard> mGoalEffectiveCardList = mGoalEffectiveCardRepository.findAll();
        assertThat(mGoalEffectiveCardList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkPlayableCardIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = mGoalEffectiveCardRepository.findAll().size();
        // set the field null
        mGoalEffectiveCard.setPlayableCardId(null);

        // Create the MGoalEffectiveCard, which fails.
        MGoalEffectiveCardDTO mGoalEffectiveCardDTO = mGoalEffectiveCardMapper.toDto(mGoalEffectiveCard);

        restMGoalEffectiveCardMockMvc.perform(post("/api/m-goal-effective-cards")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mGoalEffectiveCardDTO)))
            .andExpect(status().isBadRequest());

        List<MGoalEffectiveCard> mGoalEffectiveCardList = mGoalEffectiveCardRepository.findAll();
        assertThat(mGoalEffectiveCardList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkRateIsRequired() throws Exception {
        int databaseSizeBeforeTest = mGoalEffectiveCardRepository.findAll().size();
        // set the field null
        mGoalEffectiveCard.setRate(null);

        // Create the MGoalEffectiveCard, which fails.
        MGoalEffectiveCardDTO mGoalEffectiveCardDTO = mGoalEffectiveCardMapper.toDto(mGoalEffectiveCard);

        restMGoalEffectiveCardMockMvc.perform(post("/api/m-goal-effective-cards")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mGoalEffectiveCardDTO)))
            .andExpect(status().isBadRequest());

        List<MGoalEffectiveCard> mGoalEffectiveCardList = mGoalEffectiveCardRepository.findAll();
        assertThat(mGoalEffectiveCardList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllMGoalEffectiveCards() throws Exception {
        // Initialize the database
        mGoalEffectiveCardRepository.saveAndFlush(mGoalEffectiveCard);

        // Get all the mGoalEffectiveCardList
        restMGoalEffectiveCardMockMvc.perform(get("/api/m-goal-effective-cards?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(mGoalEffectiveCard.getId().intValue())))
            .andExpect(jsonPath("$.[*].eventType").value(hasItem(DEFAULT_EVENT_TYPE)))
            .andExpect(jsonPath("$.[*].eventId").value(hasItem(DEFAULT_EVENT_ID)))
            .andExpect(jsonPath("$.[*].playableCardId").value(hasItem(DEFAULT_PLAYABLE_CARD_ID)))
            .andExpect(jsonPath("$.[*].rate").value(hasItem(DEFAULT_RATE)));
    }
    
    @Test
    @Transactional
    public void getMGoalEffectiveCard() throws Exception {
        // Initialize the database
        mGoalEffectiveCardRepository.saveAndFlush(mGoalEffectiveCard);

        // Get the mGoalEffectiveCard
        restMGoalEffectiveCardMockMvc.perform(get("/api/m-goal-effective-cards/{id}", mGoalEffectiveCard.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(mGoalEffectiveCard.getId().intValue()))
            .andExpect(jsonPath("$.eventType").value(DEFAULT_EVENT_TYPE))
            .andExpect(jsonPath("$.eventId").value(DEFAULT_EVENT_ID))
            .andExpect(jsonPath("$.playableCardId").value(DEFAULT_PLAYABLE_CARD_ID))
            .andExpect(jsonPath("$.rate").value(DEFAULT_RATE));
    }

    @Test
    @Transactional
    public void getAllMGoalEffectiveCardsByEventTypeIsEqualToSomething() throws Exception {
        // Initialize the database
        mGoalEffectiveCardRepository.saveAndFlush(mGoalEffectiveCard);

        // Get all the mGoalEffectiveCardList where eventType equals to DEFAULT_EVENT_TYPE
        defaultMGoalEffectiveCardShouldBeFound("eventType.equals=" + DEFAULT_EVENT_TYPE);

        // Get all the mGoalEffectiveCardList where eventType equals to UPDATED_EVENT_TYPE
        defaultMGoalEffectiveCardShouldNotBeFound("eventType.equals=" + UPDATED_EVENT_TYPE);
    }

    @Test
    @Transactional
    public void getAllMGoalEffectiveCardsByEventTypeIsInShouldWork() throws Exception {
        // Initialize the database
        mGoalEffectiveCardRepository.saveAndFlush(mGoalEffectiveCard);

        // Get all the mGoalEffectiveCardList where eventType in DEFAULT_EVENT_TYPE or UPDATED_EVENT_TYPE
        defaultMGoalEffectiveCardShouldBeFound("eventType.in=" + DEFAULT_EVENT_TYPE + "," + UPDATED_EVENT_TYPE);

        // Get all the mGoalEffectiveCardList where eventType equals to UPDATED_EVENT_TYPE
        defaultMGoalEffectiveCardShouldNotBeFound("eventType.in=" + UPDATED_EVENT_TYPE);
    }

    @Test
    @Transactional
    public void getAllMGoalEffectiveCardsByEventTypeIsNullOrNotNull() throws Exception {
        // Initialize the database
        mGoalEffectiveCardRepository.saveAndFlush(mGoalEffectiveCard);

        // Get all the mGoalEffectiveCardList where eventType is not null
        defaultMGoalEffectiveCardShouldBeFound("eventType.specified=true");

        // Get all the mGoalEffectiveCardList where eventType is null
        defaultMGoalEffectiveCardShouldNotBeFound("eventType.specified=false");
    }

    @Test
    @Transactional
    public void getAllMGoalEffectiveCardsByEventTypeIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mGoalEffectiveCardRepository.saveAndFlush(mGoalEffectiveCard);

        // Get all the mGoalEffectiveCardList where eventType greater than or equals to DEFAULT_EVENT_TYPE
        defaultMGoalEffectiveCardShouldBeFound("eventType.greaterOrEqualThan=" + DEFAULT_EVENT_TYPE);

        // Get all the mGoalEffectiveCardList where eventType greater than or equals to UPDATED_EVENT_TYPE
        defaultMGoalEffectiveCardShouldNotBeFound("eventType.greaterOrEqualThan=" + UPDATED_EVENT_TYPE);
    }

    @Test
    @Transactional
    public void getAllMGoalEffectiveCardsByEventTypeIsLessThanSomething() throws Exception {
        // Initialize the database
        mGoalEffectiveCardRepository.saveAndFlush(mGoalEffectiveCard);

        // Get all the mGoalEffectiveCardList where eventType less than or equals to DEFAULT_EVENT_TYPE
        defaultMGoalEffectiveCardShouldNotBeFound("eventType.lessThan=" + DEFAULT_EVENT_TYPE);

        // Get all the mGoalEffectiveCardList where eventType less than or equals to UPDATED_EVENT_TYPE
        defaultMGoalEffectiveCardShouldBeFound("eventType.lessThan=" + UPDATED_EVENT_TYPE);
    }


    @Test
    @Transactional
    public void getAllMGoalEffectiveCardsByEventIdIsEqualToSomething() throws Exception {
        // Initialize the database
        mGoalEffectiveCardRepository.saveAndFlush(mGoalEffectiveCard);

        // Get all the mGoalEffectiveCardList where eventId equals to DEFAULT_EVENT_ID
        defaultMGoalEffectiveCardShouldBeFound("eventId.equals=" + DEFAULT_EVENT_ID);

        // Get all the mGoalEffectiveCardList where eventId equals to UPDATED_EVENT_ID
        defaultMGoalEffectiveCardShouldNotBeFound("eventId.equals=" + UPDATED_EVENT_ID);
    }

    @Test
    @Transactional
    public void getAllMGoalEffectiveCardsByEventIdIsInShouldWork() throws Exception {
        // Initialize the database
        mGoalEffectiveCardRepository.saveAndFlush(mGoalEffectiveCard);

        // Get all the mGoalEffectiveCardList where eventId in DEFAULT_EVENT_ID or UPDATED_EVENT_ID
        defaultMGoalEffectiveCardShouldBeFound("eventId.in=" + DEFAULT_EVENT_ID + "," + UPDATED_EVENT_ID);

        // Get all the mGoalEffectiveCardList where eventId equals to UPDATED_EVENT_ID
        defaultMGoalEffectiveCardShouldNotBeFound("eventId.in=" + UPDATED_EVENT_ID);
    }

    @Test
    @Transactional
    public void getAllMGoalEffectiveCardsByEventIdIsNullOrNotNull() throws Exception {
        // Initialize the database
        mGoalEffectiveCardRepository.saveAndFlush(mGoalEffectiveCard);

        // Get all the mGoalEffectiveCardList where eventId is not null
        defaultMGoalEffectiveCardShouldBeFound("eventId.specified=true");

        // Get all the mGoalEffectiveCardList where eventId is null
        defaultMGoalEffectiveCardShouldNotBeFound("eventId.specified=false");
    }

    @Test
    @Transactional
    public void getAllMGoalEffectiveCardsByEventIdIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mGoalEffectiveCardRepository.saveAndFlush(mGoalEffectiveCard);

        // Get all the mGoalEffectiveCardList where eventId greater than or equals to DEFAULT_EVENT_ID
        defaultMGoalEffectiveCardShouldBeFound("eventId.greaterOrEqualThan=" + DEFAULT_EVENT_ID);

        // Get all the mGoalEffectiveCardList where eventId greater than or equals to UPDATED_EVENT_ID
        defaultMGoalEffectiveCardShouldNotBeFound("eventId.greaterOrEqualThan=" + UPDATED_EVENT_ID);
    }

    @Test
    @Transactional
    public void getAllMGoalEffectiveCardsByEventIdIsLessThanSomething() throws Exception {
        // Initialize the database
        mGoalEffectiveCardRepository.saveAndFlush(mGoalEffectiveCard);

        // Get all the mGoalEffectiveCardList where eventId less than or equals to DEFAULT_EVENT_ID
        defaultMGoalEffectiveCardShouldNotBeFound("eventId.lessThan=" + DEFAULT_EVENT_ID);

        // Get all the mGoalEffectiveCardList where eventId less than or equals to UPDATED_EVENT_ID
        defaultMGoalEffectiveCardShouldBeFound("eventId.lessThan=" + UPDATED_EVENT_ID);
    }


    @Test
    @Transactional
    public void getAllMGoalEffectiveCardsByPlayableCardIdIsEqualToSomething() throws Exception {
        // Initialize the database
        mGoalEffectiveCardRepository.saveAndFlush(mGoalEffectiveCard);

        // Get all the mGoalEffectiveCardList where playableCardId equals to DEFAULT_PLAYABLE_CARD_ID
        defaultMGoalEffectiveCardShouldBeFound("playableCardId.equals=" + DEFAULT_PLAYABLE_CARD_ID);

        // Get all the mGoalEffectiveCardList where playableCardId equals to UPDATED_PLAYABLE_CARD_ID
        defaultMGoalEffectiveCardShouldNotBeFound("playableCardId.equals=" + UPDATED_PLAYABLE_CARD_ID);
    }

    @Test
    @Transactional
    public void getAllMGoalEffectiveCardsByPlayableCardIdIsInShouldWork() throws Exception {
        // Initialize the database
        mGoalEffectiveCardRepository.saveAndFlush(mGoalEffectiveCard);

        // Get all the mGoalEffectiveCardList where playableCardId in DEFAULT_PLAYABLE_CARD_ID or UPDATED_PLAYABLE_CARD_ID
        defaultMGoalEffectiveCardShouldBeFound("playableCardId.in=" + DEFAULT_PLAYABLE_CARD_ID + "," + UPDATED_PLAYABLE_CARD_ID);

        // Get all the mGoalEffectiveCardList where playableCardId equals to UPDATED_PLAYABLE_CARD_ID
        defaultMGoalEffectiveCardShouldNotBeFound("playableCardId.in=" + UPDATED_PLAYABLE_CARD_ID);
    }

    @Test
    @Transactional
    public void getAllMGoalEffectiveCardsByPlayableCardIdIsNullOrNotNull() throws Exception {
        // Initialize the database
        mGoalEffectiveCardRepository.saveAndFlush(mGoalEffectiveCard);

        // Get all the mGoalEffectiveCardList where playableCardId is not null
        defaultMGoalEffectiveCardShouldBeFound("playableCardId.specified=true");

        // Get all the mGoalEffectiveCardList where playableCardId is null
        defaultMGoalEffectiveCardShouldNotBeFound("playableCardId.specified=false");
    }

    @Test
    @Transactional
    public void getAllMGoalEffectiveCardsByPlayableCardIdIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mGoalEffectiveCardRepository.saveAndFlush(mGoalEffectiveCard);

        // Get all the mGoalEffectiveCardList where playableCardId greater than or equals to DEFAULT_PLAYABLE_CARD_ID
        defaultMGoalEffectiveCardShouldBeFound("playableCardId.greaterOrEqualThan=" + DEFAULT_PLAYABLE_CARD_ID);

        // Get all the mGoalEffectiveCardList where playableCardId greater than or equals to UPDATED_PLAYABLE_CARD_ID
        defaultMGoalEffectiveCardShouldNotBeFound("playableCardId.greaterOrEqualThan=" + UPDATED_PLAYABLE_CARD_ID);
    }

    @Test
    @Transactional
    public void getAllMGoalEffectiveCardsByPlayableCardIdIsLessThanSomething() throws Exception {
        // Initialize the database
        mGoalEffectiveCardRepository.saveAndFlush(mGoalEffectiveCard);

        // Get all the mGoalEffectiveCardList where playableCardId less than or equals to DEFAULT_PLAYABLE_CARD_ID
        defaultMGoalEffectiveCardShouldNotBeFound("playableCardId.lessThan=" + DEFAULT_PLAYABLE_CARD_ID);

        // Get all the mGoalEffectiveCardList where playableCardId less than or equals to UPDATED_PLAYABLE_CARD_ID
        defaultMGoalEffectiveCardShouldBeFound("playableCardId.lessThan=" + UPDATED_PLAYABLE_CARD_ID);
    }


    @Test
    @Transactional
    public void getAllMGoalEffectiveCardsByRateIsEqualToSomething() throws Exception {
        // Initialize the database
        mGoalEffectiveCardRepository.saveAndFlush(mGoalEffectiveCard);

        // Get all the mGoalEffectiveCardList where rate equals to DEFAULT_RATE
        defaultMGoalEffectiveCardShouldBeFound("rate.equals=" + DEFAULT_RATE);

        // Get all the mGoalEffectiveCardList where rate equals to UPDATED_RATE
        defaultMGoalEffectiveCardShouldNotBeFound("rate.equals=" + UPDATED_RATE);
    }

    @Test
    @Transactional
    public void getAllMGoalEffectiveCardsByRateIsInShouldWork() throws Exception {
        // Initialize the database
        mGoalEffectiveCardRepository.saveAndFlush(mGoalEffectiveCard);

        // Get all the mGoalEffectiveCardList where rate in DEFAULT_RATE or UPDATED_RATE
        defaultMGoalEffectiveCardShouldBeFound("rate.in=" + DEFAULT_RATE + "," + UPDATED_RATE);

        // Get all the mGoalEffectiveCardList where rate equals to UPDATED_RATE
        defaultMGoalEffectiveCardShouldNotBeFound("rate.in=" + UPDATED_RATE);
    }

    @Test
    @Transactional
    public void getAllMGoalEffectiveCardsByRateIsNullOrNotNull() throws Exception {
        // Initialize the database
        mGoalEffectiveCardRepository.saveAndFlush(mGoalEffectiveCard);

        // Get all the mGoalEffectiveCardList where rate is not null
        defaultMGoalEffectiveCardShouldBeFound("rate.specified=true");

        // Get all the mGoalEffectiveCardList where rate is null
        defaultMGoalEffectiveCardShouldNotBeFound("rate.specified=false");
    }

    @Test
    @Transactional
    public void getAllMGoalEffectiveCardsByRateIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mGoalEffectiveCardRepository.saveAndFlush(mGoalEffectiveCard);

        // Get all the mGoalEffectiveCardList where rate greater than or equals to DEFAULT_RATE
        defaultMGoalEffectiveCardShouldBeFound("rate.greaterOrEqualThan=" + DEFAULT_RATE);

        // Get all the mGoalEffectiveCardList where rate greater than or equals to UPDATED_RATE
        defaultMGoalEffectiveCardShouldNotBeFound("rate.greaterOrEqualThan=" + UPDATED_RATE);
    }

    @Test
    @Transactional
    public void getAllMGoalEffectiveCardsByRateIsLessThanSomething() throws Exception {
        // Initialize the database
        mGoalEffectiveCardRepository.saveAndFlush(mGoalEffectiveCard);

        // Get all the mGoalEffectiveCardList where rate less than or equals to DEFAULT_RATE
        defaultMGoalEffectiveCardShouldNotBeFound("rate.lessThan=" + DEFAULT_RATE);

        // Get all the mGoalEffectiveCardList where rate less than or equals to UPDATED_RATE
        defaultMGoalEffectiveCardShouldBeFound("rate.lessThan=" + UPDATED_RATE);
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultMGoalEffectiveCardShouldBeFound(String filter) throws Exception {
        restMGoalEffectiveCardMockMvc.perform(get("/api/m-goal-effective-cards?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(mGoalEffectiveCard.getId().intValue())))
            .andExpect(jsonPath("$.[*].eventType").value(hasItem(DEFAULT_EVENT_TYPE)))
            .andExpect(jsonPath("$.[*].eventId").value(hasItem(DEFAULT_EVENT_ID)))
            .andExpect(jsonPath("$.[*].playableCardId").value(hasItem(DEFAULT_PLAYABLE_CARD_ID)))
            .andExpect(jsonPath("$.[*].rate").value(hasItem(DEFAULT_RATE)));

        // Check, that the count call also returns 1
        restMGoalEffectiveCardMockMvc.perform(get("/api/m-goal-effective-cards/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultMGoalEffectiveCardShouldNotBeFound(String filter) throws Exception {
        restMGoalEffectiveCardMockMvc.perform(get("/api/m-goal-effective-cards?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restMGoalEffectiveCardMockMvc.perform(get("/api/m-goal-effective-cards/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("0"));
    }


    @Test
    @Transactional
    public void getNonExistingMGoalEffectiveCard() throws Exception {
        // Get the mGoalEffectiveCard
        restMGoalEffectiveCardMockMvc.perform(get("/api/m-goal-effective-cards/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateMGoalEffectiveCard() throws Exception {
        // Initialize the database
        mGoalEffectiveCardRepository.saveAndFlush(mGoalEffectiveCard);

        int databaseSizeBeforeUpdate = mGoalEffectiveCardRepository.findAll().size();

        // Update the mGoalEffectiveCard
        MGoalEffectiveCard updatedMGoalEffectiveCard = mGoalEffectiveCardRepository.findById(mGoalEffectiveCard.getId()).get();
        // Disconnect from session so that the updates on updatedMGoalEffectiveCard are not directly saved in db
        em.detach(updatedMGoalEffectiveCard);
        updatedMGoalEffectiveCard
            .eventType(UPDATED_EVENT_TYPE)
            .eventId(UPDATED_EVENT_ID)
            .playableCardId(UPDATED_PLAYABLE_CARD_ID)
            .rate(UPDATED_RATE);
        MGoalEffectiveCardDTO mGoalEffectiveCardDTO = mGoalEffectiveCardMapper.toDto(updatedMGoalEffectiveCard);

        restMGoalEffectiveCardMockMvc.perform(put("/api/m-goal-effective-cards")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mGoalEffectiveCardDTO)))
            .andExpect(status().isOk());

        // Validate the MGoalEffectiveCard in the database
        List<MGoalEffectiveCard> mGoalEffectiveCardList = mGoalEffectiveCardRepository.findAll();
        assertThat(mGoalEffectiveCardList).hasSize(databaseSizeBeforeUpdate);
        MGoalEffectiveCard testMGoalEffectiveCard = mGoalEffectiveCardList.get(mGoalEffectiveCardList.size() - 1);
        assertThat(testMGoalEffectiveCard.getEventType()).isEqualTo(UPDATED_EVENT_TYPE);
        assertThat(testMGoalEffectiveCard.getEventId()).isEqualTo(UPDATED_EVENT_ID);
        assertThat(testMGoalEffectiveCard.getPlayableCardId()).isEqualTo(UPDATED_PLAYABLE_CARD_ID);
        assertThat(testMGoalEffectiveCard.getRate()).isEqualTo(UPDATED_RATE);
    }

    @Test
    @Transactional
    public void updateNonExistingMGoalEffectiveCard() throws Exception {
        int databaseSizeBeforeUpdate = mGoalEffectiveCardRepository.findAll().size();

        // Create the MGoalEffectiveCard
        MGoalEffectiveCardDTO mGoalEffectiveCardDTO = mGoalEffectiveCardMapper.toDto(mGoalEffectiveCard);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restMGoalEffectiveCardMockMvc.perform(put("/api/m-goal-effective-cards")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mGoalEffectiveCardDTO)))
            .andExpect(status().isBadRequest());

        // Validate the MGoalEffectiveCard in the database
        List<MGoalEffectiveCard> mGoalEffectiveCardList = mGoalEffectiveCardRepository.findAll();
        assertThat(mGoalEffectiveCardList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteMGoalEffectiveCard() throws Exception {
        // Initialize the database
        mGoalEffectiveCardRepository.saveAndFlush(mGoalEffectiveCard);

        int databaseSizeBeforeDelete = mGoalEffectiveCardRepository.findAll().size();

        // Delete the mGoalEffectiveCard
        restMGoalEffectiveCardMockMvc.perform(delete("/api/m-goal-effective-cards/{id}", mGoalEffectiveCard.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database is empty
        List<MGoalEffectiveCard> mGoalEffectiveCardList = mGoalEffectiveCardRepository.findAll();
        assertThat(mGoalEffectiveCardList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(MGoalEffectiveCard.class);
        MGoalEffectiveCard mGoalEffectiveCard1 = new MGoalEffectiveCard();
        mGoalEffectiveCard1.setId(1L);
        MGoalEffectiveCard mGoalEffectiveCard2 = new MGoalEffectiveCard();
        mGoalEffectiveCard2.setId(mGoalEffectiveCard1.getId());
        assertThat(mGoalEffectiveCard1).isEqualTo(mGoalEffectiveCard2);
        mGoalEffectiveCard2.setId(2L);
        assertThat(mGoalEffectiveCard1).isNotEqualTo(mGoalEffectiveCard2);
        mGoalEffectiveCard1.setId(null);
        assertThat(mGoalEffectiveCard1).isNotEqualTo(mGoalEffectiveCard2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(MGoalEffectiveCardDTO.class);
        MGoalEffectiveCardDTO mGoalEffectiveCardDTO1 = new MGoalEffectiveCardDTO();
        mGoalEffectiveCardDTO1.setId(1L);
        MGoalEffectiveCardDTO mGoalEffectiveCardDTO2 = new MGoalEffectiveCardDTO();
        assertThat(mGoalEffectiveCardDTO1).isNotEqualTo(mGoalEffectiveCardDTO2);
        mGoalEffectiveCardDTO2.setId(mGoalEffectiveCardDTO1.getId());
        assertThat(mGoalEffectiveCardDTO1).isEqualTo(mGoalEffectiveCardDTO2);
        mGoalEffectiveCardDTO2.setId(2L);
        assertThat(mGoalEffectiveCardDTO1).isNotEqualTo(mGoalEffectiveCardDTO2);
        mGoalEffectiveCardDTO1.setId(null);
        assertThat(mGoalEffectiveCardDTO1).isNotEqualTo(mGoalEffectiveCardDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(mGoalEffectiveCardMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(mGoalEffectiveCardMapper.fromId(null)).isNull();
    }
}
