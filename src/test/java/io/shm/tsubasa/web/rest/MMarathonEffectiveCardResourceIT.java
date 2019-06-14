package io.shm.tsubasa.web.rest;

import io.shm.tsubasa.TsubasaApp;
import io.shm.tsubasa.domain.MMarathonEffectiveCard;
import io.shm.tsubasa.repository.MMarathonEffectiveCardRepository;
import io.shm.tsubasa.service.MMarathonEffectiveCardService;
import io.shm.tsubasa.service.dto.MMarathonEffectiveCardDTO;
import io.shm.tsubasa.service.mapper.MMarathonEffectiveCardMapper;
import io.shm.tsubasa.web.rest.errors.ExceptionTranslator;
import io.shm.tsubasa.service.dto.MMarathonEffectiveCardCriteria;
import io.shm.tsubasa.service.MMarathonEffectiveCardQueryService;

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
 * Integration tests for the {@Link MMarathonEffectiveCardResource} REST controller.
 */
@SpringBootTest(classes = TsubasaApp.class)
public class MMarathonEffectiveCardResourceIT {

    private static final Integer DEFAULT_EVENT_ID = 1;
    private static final Integer UPDATED_EVENT_ID = 2;

    private static final Integer DEFAULT_PLAYABLE_CARD_ID = 1;
    private static final Integer UPDATED_PLAYABLE_CARD_ID = 2;

    private static final Integer DEFAULT_RATE = 1;
    private static final Integer UPDATED_RATE = 2;

    @Autowired
    private MMarathonEffectiveCardRepository mMarathonEffectiveCardRepository;

    @Autowired
    private MMarathonEffectiveCardMapper mMarathonEffectiveCardMapper;

    @Autowired
    private MMarathonEffectiveCardService mMarathonEffectiveCardService;

    @Autowired
    private MMarathonEffectiveCardQueryService mMarathonEffectiveCardQueryService;

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

    private MockMvc restMMarathonEffectiveCardMockMvc;

    private MMarathonEffectiveCard mMarathonEffectiveCard;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final MMarathonEffectiveCardResource mMarathonEffectiveCardResource = new MMarathonEffectiveCardResource(mMarathonEffectiveCardService, mMarathonEffectiveCardQueryService);
        this.restMMarathonEffectiveCardMockMvc = MockMvcBuilders.standaloneSetup(mMarathonEffectiveCardResource)
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
    public static MMarathonEffectiveCard createEntity(EntityManager em) {
        MMarathonEffectiveCard mMarathonEffectiveCard = new MMarathonEffectiveCard()
            .eventId(DEFAULT_EVENT_ID)
            .playableCardId(DEFAULT_PLAYABLE_CARD_ID)
            .rate(DEFAULT_RATE);
        return mMarathonEffectiveCard;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static MMarathonEffectiveCard createUpdatedEntity(EntityManager em) {
        MMarathonEffectiveCard mMarathonEffectiveCard = new MMarathonEffectiveCard()
            .eventId(UPDATED_EVENT_ID)
            .playableCardId(UPDATED_PLAYABLE_CARD_ID)
            .rate(UPDATED_RATE);
        return mMarathonEffectiveCard;
    }

    @BeforeEach
    public void initTest() {
        mMarathonEffectiveCard = createEntity(em);
    }

    @Test
    @Transactional
    public void createMMarathonEffectiveCard() throws Exception {
        int databaseSizeBeforeCreate = mMarathonEffectiveCardRepository.findAll().size();

        // Create the MMarathonEffectiveCard
        MMarathonEffectiveCardDTO mMarathonEffectiveCardDTO = mMarathonEffectiveCardMapper.toDto(mMarathonEffectiveCard);
        restMMarathonEffectiveCardMockMvc.perform(post("/api/m-marathon-effective-cards")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mMarathonEffectiveCardDTO)))
            .andExpect(status().isCreated());

        // Validate the MMarathonEffectiveCard in the database
        List<MMarathonEffectiveCard> mMarathonEffectiveCardList = mMarathonEffectiveCardRepository.findAll();
        assertThat(mMarathonEffectiveCardList).hasSize(databaseSizeBeforeCreate + 1);
        MMarathonEffectiveCard testMMarathonEffectiveCard = mMarathonEffectiveCardList.get(mMarathonEffectiveCardList.size() - 1);
        assertThat(testMMarathonEffectiveCard.getEventId()).isEqualTo(DEFAULT_EVENT_ID);
        assertThat(testMMarathonEffectiveCard.getPlayableCardId()).isEqualTo(DEFAULT_PLAYABLE_CARD_ID);
        assertThat(testMMarathonEffectiveCard.getRate()).isEqualTo(DEFAULT_RATE);
    }

    @Test
    @Transactional
    public void createMMarathonEffectiveCardWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = mMarathonEffectiveCardRepository.findAll().size();

        // Create the MMarathonEffectiveCard with an existing ID
        mMarathonEffectiveCard.setId(1L);
        MMarathonEffectiveCardDTO mMarathonEffectiveCardDTO = mMarathonEffectiveCardMapper.toDto(mMarathonEffectiveCard);

        // An entity with an existing ID cannot be created, so this API call must fail
        restMMarathonEffectiveCardMockMvc.perform(post("/api/m-marathon-effective-cards")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mMarathonEffectiveCardDTO)))
            .andExpect(status().isBadRequest());

        // Validate the MMarathonEffectiveCard in the database
        List<MMarathonEffectiveCard> mMarathonEffectiveCardList = mMarathonEffectiveCardRepository.findAll();
        assertThat(mMarathonEffectiveCardList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkEventIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = mMarathonEffectiveCardRepository.findAll().size();
        // set the field null
        mMarathonEffectiveCard.setEventId(null);

        // Create the MMarathonEffectiveCard, which fails.
        MMarathonEffectiveCardDTO mMarathonEffectiveCardDTO = mMarathonEffectiveCardMapper.toDto(mMarathonEffectiveCard);

        restMMarathonEffectiveCardMockMvc.perform(post("/api/m-marathon-effective-cards")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mMarathonEffectiveCardDTO)))
            .andExpect(status().isBadRequest());

        List<MMarathonEffectiveCard> mMarathonEffectiveCardList = mMarathonEffectiveCardRepository.findAll();
        assertThat(mMarathonEffectiveCardList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkPlayableCardIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = mMarathonEffectiveCardRepository.findAll().size();
        // set the field null
        mMarathonEffectiveCard.setPlayableCardId(null);

        // Create the MMarathonEffectiveCard, which fails.
        MMarathonEffectiveCardDTO mMarathonEffectiveCardDTO = mMarathonEffectiveCardMapper.toDto(mMarathonEffectiveCard);

        restMMarathonEffectiveCardMockMvc.perform(post("/api/m-marathon-effective-cards")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mMarathonEffectiveCardDTO)))
            .andExpect(status().isBadRequest());

        List<MMarathonEffectiveCard> mMarathonEffectiveCardList = mMarathonEffectiveCardRepository.findAll();
        assertThat(mMarathonEffectiveCardList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkRateIsRequired() throws Exception {
        int databaseSizeBeforeTest = mMarathonEffectiveCardRepository.findAll().size();
        // set the field null
        mMarathonEffectiveCard.setRate(null);

        // Create the MMarathonEffectiveCard, which fails.
        MMarathonEffectiveCardDTO mMarathonEffectiveCardDTO = mMarathonEffectiveCardMapper.toDto(mMarathonEffectiveCard);

        restMMarathonEffectiveCardMockMvc.perform(post("/api/m-marathon-effective-cards")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mMarathonEffectiveCardDTO)))
            .andExpect(status().isBadRequest());

        List<MMarathonEffectiveCard> mMarathonEffectiveCardList = mMarathonEffectiveCardRepository.findAll();
        assertThat(mMarathonEffectiveCardList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllMMarathonEffectiveCards() throws Exception {
        // Initialize the database
        mMarathonEffectiveCardRepository.saveAndFlush(mMarathonEffectiveCard);

        // Get all the mMarathonEffectiveCardList
        restMMarathonEffectiveCardMockMvc.perform(get("/api/m-marathon-effective-cards?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(mMarathonEffectiveCard.getId().intValue())))
            .andExpect(jsonPath("$.[*].eventId").value(hasItem(DEFAULT_EVENT_ID)))
            .andExpect(jsonPath("$.[*].playableCardId").value(hasItem(DEFAULT_PLAYABLE_CARD_ID)))
            .andExpect(jsonPath("$.[*].rate").value(hasItem(DEFAULT_RATE)));
    }
    
    @Test
    @Transactional
    public void getMMarathonEffectiveCard() throws Exception {
        // Initialize the database
        mMarathonEffectiveCardRepository.saveAndFlush(mMarathonEffectiveCard);

        // Get the mMarathonEffectiveCard
        restMMarathonEffectiveCardMockMvc.perform(get("/api/m-marathon-effective-cards/{id}", mMarathonEffectiveCard.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(mMarathonEffectiveCard.getId().intValue()))
            .andExpect(jsonPath("$.eventId").value(DEFAULT_EVENT_ID))
            .andExpect(jsonPath("$.playableCardId").value(DEFAULT_PLAYABLE_CARD_ID))
            .andExpect(jsonPath("$.rate").value(DEFAULT_RATE));
    }

    @Test
    @Transactional
    public void getAllMMarathonEffectiveCardsByEventIdIsEqualToSomething() throws Exception {
        // Initialize the database
        mMarathonEffectiveCardRepository.saveAndFlush(mMarathonEffectiveCard);

        // Get all the mMarathonEffectiveCardList where eventId equals to DEFAULT_EVENT_ID
        defaultMMarathonEffectiveCardShouldBeFound("eventId.equals=" + DEFAULT_EVENT_ID);

        // Get all the mMarathonEffectiveCardList where eventId equals to UPDATED_EVENT_ID
        defaultMMarathonEffectiveCardShouldNotBeFound("eventId.equals=" + UPDATED_EVENT_ID);
    }

    @Test
    @Transactional
    public void getAllMMarathonEffectiveCardsByEventIdIsInShouldWork() throws Exception {
        // Initialize the database
        mMarathonEffectiveCardRepository.saveAndFlush(mMarathonEffectiveCard);

        // Get all the mMarathonEffectiveCardList where eventId in DEFAULT_EVENT_ID or UPDATED_EVENT_ID
        defaultMMarathonEffectiveCardShouldBeFound("eventId.in=" + DEFAULT_EVENT_ID + "," + UPDATED_EVENT_ID);

        // Get all the mMarathonEffectiveCardList where eventId equals to UPDATED_EVENT_ID
        defaultMMarathonEffectiveCardShouldNotBeFound("eventId.in=" + UPDATED_EVENT_ID);
    }

    @Test
    @Transactional
    public void getAllMMarathonEffectiveCardsByEventIdIsNullOrNotNull() throws Exception {
        // Initialize the database
        mMarathonEffectiveCardRepository.saveAndFlush(mMarathonEffectiveCard);

        // Get all the mMarathonEffectiveCardList where eventId is not null
        defaultMMarathonEffectiveCardShouldBeFound("eventId.specified=true");

        // Get all the mMarathonEffectiveCardList where eventId is null
        defaultMMarathonEffectiveCardShouldNotBeFound("eventId.specified=false");
    }

    @Test
    @Transactional
    public void getAllMMarathonEffectiveCardsByEventIdIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mMarathonEffectiveCardRepository.saveAndFlush(mMarathonEffectiveCard);

        // Get all the mMarathonEffectiveCardList where eventId greater than or equals to DEFAULT_EVENT_ID
        defaultMMarathonEffectiveCardShouldBeFound("eventId.greaterOrEqualThan=" + DEFAULT_EVENT_ID);

        // Get all the mMarathonEffectiveCardList where eventId greater than or equals to UPDATED_EVENT_ID
        defaultMMarathonEffectiveCardShouldNotBeFound("eventId.greaterOrEqualThan=" + UPDATED_EVENT_ID);
    }

    @Test
    @Transactional
    public void getAllMMarathonEffectiveCardsByEventIdIsLessThanSomething() throws Exception {
        // Initialize the database
        mMarathonEffectiveCardRepository.saveAndFlush(mMarathonEffectiveCard);

        // Get all the mMarathonEffectiveCardList where eventId less than or equals to DEFAULT_EVENT_ID
        defaultMMarathonEffectiveCardShouldNotBeFound("eventId.lessThan=" + DEFAULT_EVENT_ID);

        // Get all the mMarathonEffectiveCardList where eventId less than or equals to UPDATED_EVENT_ID
        defaultMMarathonEffectiveCardShouldBeFound("eventId.lessThan=" + UPDATED_EVENT_ID);
    }


    @Test
    @Transactional
    public void getAllMMarathonEffectiveCardsByPlayableCardIdIsEqualToSomething() throws Exception {
        // Initialize the database
        mMarathonEffectiveCardRepository.saveAndFlush(mMarathonEffectiveCard);

        // Get all the mMarathonEffectiveCardList where playableCardId equals to DEFAULT_PLAYABLE_CARD_ID
        defaultMMarathonEffectiveCardShouldBeFound("playableCardId.equals=" + DEFAULT_PLAYABLE_CARD_ID);

        // Get all the mMarathonEffectiveCardList where playableCardId equals to UPDATED_PLAYABLE_CARD_ID
        defaultMMarathonEffectiveCardShouldNotBeFound("playableCardId.equals=" + UPDATED_PLAYABLE_CARD_ID);
    }

    @Test
    @Transactional
    public void getAllMMarathonEffectiveCardsByPlayableCardIdIsInShouldWork() throws Exception {
        // Initialize the database
        mMarathonEffectiveCardRepository.saveAndFlush(mMarathonEffectiveCard);

        // Get all the mMarathonEffectiveCardList where playableCardId in DEFAULT_PLAYABLE_CARD_ID or UPDATED_PLAYABLE_CARD_ID
        defaultMMarathonEffectiveCardShouldBeFound("playableCardId.in=" + DEFAULT_PLAYABLE_CARD_ID + "," + UPDATED_PLAYABLE_CARD_ID);

        // Get all the mMarathonEffectiveCardList where playableCardId equals to UPDATED_PLAYABLE_CARD_ID
        defaultMMarathonEffectiveCardShouldNotBeFound("playableCardId.in=" + UPDATED_PLAYABLE_CARD_ID);
    }

    @Test
    @Transactional
    public void getAllMMarathonEffectiveCardsByPlayableCardIdIsNullOrNotNull() throws Exception {
        // Initialize the database
        mMarathonEffectiveCardRepository.saveAndFlush(mMarathonEffectiveCard);

        // Get all the mMarathonEffectiveCardList where playableCardId is not null
        defaultMMarathonEffectiveCardShouldBeFound("playableCardId.specified=true");

        // Get all the mMarathonEffectiveCardList where playableCardId is null
        defaultMMarathonEffectiveCardShouldNotBeFound("playableCardId.specified=false");
    }

    @Test
    @Transactional
    public void getAllMMarathonEffectiveCardsByPlayableCardIdIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mMarathonEffectiveCardRepository.saveAndFlush(mMarathonEffectiveCard);

        // Get all the mMarathonEffectiveCardList where playableCardId greater than or equals to DEFAULT_PLAYABLE_CARD_ID
        defaultMMarathonEffectiveCardShouldBeFound("playableCardId.greaterOrEqualThan=" + DEFAULT_PLAYABLE_CARD_ID);

        // Get all the mMarathonEffectiveCardList where playableCardId greater than or equals to UPDATED_PLAYABLE_CARD_ID
        defaultMMarathonEffectiveCardShouldNotBeFound("playableCardId.greaterOrEqualThan=" + UPDATED_PLAYABLE_CARD_ID);
    }

    @Test
    @Transactional
    public void getAllMMarathonEffectiveCardsByPlayableCardIdIsLessThanSomething() throws Exception {
        // Initialize the database
        mMarathonEffectiveCardRepository.saveAndFlush(mMarathonEffectiveCard);

        // Get all the mMarathonEffectiveCardList where playableCardId less than or equals to DEFAULT_PLAYABLE_CARD_ID
        defaultMMarathonEffectiveCardShouldNotBeFound("playableCardId.lessThan=" + DEFAULT_PLAYABLE_CARD_ID);

        // Get all the mMarathonEffectiveCardList where playableCardId less than or equals to UPDATED_PLAYABLE_CARD_ID
        defaultMMarathonEffectiveCardShouldBeFound("playableCardId.lessThan=" + UPDATED_PLAYABLE_CARD_ID);
    }


    @Test
    @Transactional
    public void getAllMMarathonEffectiveCardsByRateIsEqualToSomething() throws Exception {
        // Initialize the database
        mMarathonEffectiveCardRepository.saveAndFlush(mMarathonEffectiveCard);

        // Get all the mMarathonEffectiveCardList where rate equals to DEFAULT_RATE
        defaultMMarathonEffectiveCardShouldBeFound("rate.equals=" + DEFAULT_RATE);

        // Get all the mMarathonEffectiveCardList where rate equals to UPDATED_RATE
        defaultMMarathonEffectiveCardShouldNotBeFound("rate.equals=" + UPDATED_RATE);
    }

    @Test
    @Transactional
    public void getAllMMarathonEffectiveCardsByRateIsInShouldWork() throws Exception {
        // Initialize the database
        mMarathonEffectiveCardRepository.saveAndFlush(mMarathonEffectiveCard);

        // Get all the mMarathonEffectiveCardList where rate in DEFAULT_RATE or UPDATED_RATE
        defaultMMarathonEffectiveCardShouldBeFound("rate.in=" + DEFAULT_RATE + "," + UPDATED_RATE);

        // Get all the mMarathonEffectiveCardList where rate equals to UPDATED_RATE
        defaultMMarathonEffectiveCardShouldNotBeFound("rate.in=" + UPDATED_RATE);
    }

    @Test
    @Transactional
    public void getAllMMarathonEffectiveCardsByRateIsNullOrNotNull() throws Exception {
        // Initialize the database
        mMarathonEffectiveCardRepository.saveAndFlush(mMarathonEffectiveCard);

        // Get all the mMarathonEffectiveCardList where rate is not null
        defaultMMarathonEffectiveCardShouldBeFound("rate.specified=true");

        // Get all the mMarathonEffectiveCardList where rate is null
        defaultMMarathonEffectiveCardShouldNotBeFound("rate.specified=false");
    }

    @Test
    @Transactional
    public void getAllMMarathonEffectiveCardsByRateIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mMarathonEffectiveCardRepository.saveAndFlush(mMarathonEffectiveCard);

        // Get all the mMarathonEffectiveCardList where rate greater than or equals to DEFAULT_RATE
        defaultMMarathonEffectiveCardShouldBeFound("rate.greaterOrEqualThan=" + DEFAULT_RATE);

        // Get all the mMarathonEffectiveCardList where rate greater than or equals to UPDATED_RATE
        defaultMMarathonEffectiveCardShouldNotBeFound("rate.greaterOrEqualThan=" + UPDATED_RATE);
    }

    @Test
    @Transactional
    public void getAllMMarathonEffectiveCardsByRateIsLessThanSomething() throws Exception {
        // Initialize the database
        mMarathonEffectiveCardRepository.saveAndFlush(mMarathonEffectiveCard);

        // Get all the mMarathonEffectiveCardList where rate less than or equals to DEFAULT_RATE
        defaultMMarathonEffectiveCardShouldNotBeFound("rate.lessThan=" + DEFAULT_RATE);

        // Get all the mMarathonEffectiveCardList where rate less than or equals to UPDATED_RATE
        defaultMMarathonEffectiveCardShouldBeFound("rate.lessThan=" + UPDATED_RATE);
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultMMarathonEffectiveCardShouldBeFound(String filter) throws Exception {
        restMMarathonEffectiveCardMockMvc.perform(get("/api/m-marathon-effective-cards?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(mMarathonEffectiveCard.getId().intValue())))
            .andExpect(jsonPath("$.[*].eventId").value(hasItem(DEFAULT_EVENT_ID)))
            .andExpect(jsonPath("$.[*].playableCardId").value(hasItem(DEFAULT_PLAYABLE_CARD_ID)))
            .andExpect(jsonPath("$.[*].rate").value(hasItem(DEFAULT_RATE)));

        // Check, that the count call also returns 1
        restMMarathonEffectiveCardMockMvc.perform(get("/api/m-marathon-effective-cards/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultMMarathonEffectiveCardShouldNotBeFound(String filter) throws Exception {
        restMMarathonEffectiveCardMockMvc.perform(get("/api/m-marathon-effective-cards?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restMMarathonEffectiveCardMockMvc.perform(get("/api/m-marathon-effective-cards/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("0"));
    }


    @Test
    @Transactional
    public void getNonExistingMMarathonEffectiveCard() throws Exception {
        // Get the mMarathonEffectiveCard
        restMMarathonEffectiveCardMockMvc.perform(get("/api/m-marathon-effective-cards/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateMMarathonEffectiveCard() throws Exception {
        // Initialize the database
        mMarathonEffectiveCardRepository.saveAndFlush(mMarathonEffectiveCard);

        int databaseSizeBeforeUpdate = mMarathonEffectiveCardRepository.findAll().size();

        // Update the mMarathonEffectiveCard
        MMarathonEffectiveCard updatedMMarathonEffectiveCard = mMarathonEffectiveCardRepository.findById(mMarathonEffectiveCard.getId()).get();
        // Disconnect from session so that the updates on updatedMMarathonEffectiveCard are not directly saved in db
        em.detach(updatedMMarathonEffectiveCard);
        updatedMMarathonEffectiveCard
            .eventId(UPDATED_EVENT_ID)
            .playableCardId(UPDATED_PLAYABLE_CARD_ID)
            .rate(UPDATED_RATE);
        MMarathonEffectiveCardDTO mMarathonEffectiveCardDTO = mMarathonEffectiveCardMapper.toDto(updatedMMarathonEffectiveCard);

        restMMarathonEffectiveCardMockMvc.perform(put("/api/m-marathon-effective-cards")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mMarathonEffectiveCardDTO)))
            .andExpect(status().isOk());

        // Validate the MMarathonEffectiveCard in the database
        List<MMarathonEffectiveCard> mMarathonEffectiveCardList = mMarathonEffectiveCardRepository.findAll();
        assertThat(mMarathonEffectiveCardList).hasSize(databaseSizeBeforeUpdate);
        MMarathonEffectiveCard testMMarathonEffectiveCard = mMarathonEffectiveCardList.get(mMarathonEffectiveCardList.size() - 1);
        assertThat(testMMarathonEffectiveCard.getEventId()).isEqualTo(UPDATED_EVENT_ID);
        assertThat(testMMarathonEffectiveCard.getPlayableCardId()).isEqualTo(UPDATED_PLAYABLE_CARD_ID);
        assertThat(testMMarathonEffectiveCard.getRate()).isEqualTo(UPDATED_RATE);
    }

    @Test
    @Transactional
    public void updateNonExistingMMarathonEffectiveCard() throws Exception {
        int databaseSizeBeforeUpdate = mMarathonEffectiveCardRepository.findAll().size();

        // Create the MMarathonEffectiveCard
        MMarathonEffectiveCardDTO mMarathonEffectiveCardDTO = mMarathonEffectiveCardMapper.toDto(mMarathonEffectiveCard);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restMMarathonEffectiveCardMockMvc.perform(put("/api/m-marathon-effective-cards")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mMarathonEffectiveCardDTO)))
            .andExpect(status().isBadRequest());

        // Validate the MMarathonEffectiveCard in the database
        List<MMarathonEffectiveCard> mMarathonEffectiveCardList = mMarathonEffectiveCardRepository.findAll();
        assertThat(mMarathonEffectiveCardList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteMMarathonEffectiveCard() throws Exception {
        // Initialize the database
        mMarathonEffectiveCardRepository.saveAndFlush(mMarathonEffectiveCard);

        int databaseSizeBeforeDelete = mMarathonEffectiveCardRepository.findAll().size();

        // Delete the mMarathonEffectiveCard
        restMMarathonEffectiveCardMockMvc.perform(delete("/api/m-marathon-effective-cards/{id}", mMarathonEffectiveCard.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database is empty
        List<MMarathonEffectiveCard> mMarathonEffectiveCardList = mMarathonEffectiveCardRepository.findAll();
        assertThat(mMarathonEffectiveCardList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(MMarathonEffectiveCard.class);
        MMarathonEffectiveCard mMarathonEffectiveCard1 = new MMarathonEffectiveCard();
        mMarathonEffectiveCard1.setId(1L);
        MMarathonEffectiveCard mMarathonEffectiveCard2 = new MMarathonEffectiveCard();
        mMarathonEffectiveCard2.setId(mMarathonEffectiveCard1.getId());
        assertThat(mMarathonEffectiveCard1).isEqualTo(mMarathonEffectiveCard2);
        mMarathonEffectiveCard2.setId(2L);
        assertThat(mMarathonEffectiveCard1).isNotEqualTo(mMarathonEffectiveCard2);
        mMarathonEffectiveCard1.setId(null);
        assertThat(mMarathonEffectiveCard1).isNotEqualTo(mMarathonEffectiveCard2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(MMarathonEffectiveCardDTO.class);
        MMarathonEffectiveCardDTO mMarathonEffectiveCardDTO1 = new MMarathonEffectiveCardDTO();
        mMarathonEffectiveCardDTO1.setId(1L);
        MMarathonEffectiveCardDTO mMarathonEffectiveCardDTO2 = new MMarathonEffectiveCardDTO();
        assertThat(mMarathonEffectiveCardDTO1).isNotEqualTo(mMarathonEffectiveCardDTO2);
        mMarathonEffectiveCardDTO2.setId(mMarathonEffectiveCardDTO1.getId());
        assertThat(mMarathonEffectiveCardDTO1).isEqualTo(mMarathonEffectiveCardDTO2);
        mMarathonEffectiveCardDTO2.setId(2L);
        assertThat(mMarathonEffectiveCardDTO1).isNotEqualTo(mMarathonEffectiveCardDTO2);
        mMarathonEffectiveCardDTO1.setId(null);
        assertThat(mMarathonEffectiveCardDTO1).isNotEqualTo(mMarathonEffectiveCardDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(mMarathonEffectiveCardMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(mMarathonEffectiveCardMapper.fromId(null)).isNull();
    }
}
