package io.shm.tsubasa.web.rest;

import io.shm.tsubasa.TsubasaApp;
import io.shm.tsubasa.domain.MInitUserDeck;
import io.shm.tsubasa.repository.MInitUserDeckRepository;
import io.shm.tsubasa.service.MInitUserDeckService;
import io.shm.tsubasa.service.dto.MInitUserDeckDTO;
import io.shm.tsubasa.service.mapper.MInitUserDeckMapper;
import io.shm.tsubasa.web.rest.errors.ExceptionTranslator;
import io.shm.tsubasa.service.dto.MInitUserDeckCriteria;
import io.shm.tsubasa.service.MInitUserDeckQueryService;

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
 * Integration tests for the {@Link MInitUserDeckResource} REST controller.
 */
@SpringBootTest(classes = TsubasaApp.class)
public class MInitUserDeckResourceIT {

    private static final Integer DEFAULT_DECK_ID = 1;
    private static final Integer UPDATED_DECK_ID = 2;

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final Integer DEFAULT_FORMATION_ID = 1;
    private static final Integer UPDATED_FORMATION_ID = 2;

    private static final Integer DEFAULT_CAPTAIN_CARD_ID = 1;
    private static final Integer UPDATED_CAPTAIN_CARD_ID = 2;

    private static final Integer DEFAULT_GK_CARD_ID = 1;
    private static final Integer UPDATED_GK_CARD_ID = 2;

    private static final Integer DEFAULT_FP_1_CARD_ID = 1;
    private static final Integer UPDATED_FP_1_CARD_ID = 2;

    private static final Integer DEFAULT_FP_2_CARD_ID = 1;
    private static final Integer UPDATED_FP_2_CARD_ID = 2;

    private static final Integer DEFAULT_FP_3_CARD_ID = 1;
    private static final Integer UPDATED_FP_3_CARD_ID = 2;

    private static final Integer DEFAULT_FP_4_CARD_ID = 1;
    private static final Integer UPDATED_FP_4_CARD_ID = 2;

    private static final Integer DEFAULT_FP_5_CARD_ID = 1;
    private static final Integer UPDATED_FP_5_CARD_ID = 2;

    private static final Integer DEFAULT_FP_6_CARD_ID = 1;
    private static final Integer UPDATED_FP_6_CARD_ID = 2;

    private static final Integer DEFAULT_FP_7_CARD_ID = 1;
    private static final Integer UPDATED_FP_7_CARD_ID = 2;

    private static final Integer DEFAULT_FP_8_CARD_ID = 1;
    private static final Integer UPDATED_FP_8_CARD_ID = 2;

    private static final Integer DEFAULT_FP_9_CARD_ID = 1;
    private static final Integer UPDATED_FP_9_CARD_ID = 2;

    private static final Integer DEFAULT_FP_10_CARD_ID = 1;
    private static final Integer UPDATED_FP_10_CARD_ID = 2;

    private static final Integer DEFAULT_SUB_1_CARD_ID = 1;
    private static final Integer UPDATED_SUB_1_CARD_ID = 2;

    private static final Integer DEFAULT_SUB_2_CARD_ID = 1;
    private static final Integer UPDATED_SUB_2_CARD_ID = 2;

    private static final Integer DEFAULT_SUB_3_CARD_ID = 1;
    private static final Integer UPDATED_SUB_3_CARD_ID = 2;

    private static final Integer DEFAULT_SUB_4_CARD_ID = 1;
    private static final Integer UPDATED_SUB_4_CARD_ID = 2;

    private static final Integer DEFAULT_SUB_5_CARD_ID = 1;
    private static final Integer UPDATED_SUB_5_CARD_ID = 2;

    private static final Integer DEFAULT_TEAM_EFFECT_1_CARD_ID = 1;
    private static final Integer UPDATED_TEAM_EFFECT_1_CARD_ID = 2;

    private static final Integer DEFAULT_TEAM_EFFECT_2_CARD_ID = 1;
    private static final Integer UPDATED_TEAM_EFFECT_2_CARD_ID = 2;

    private static final Integer DEFAULT_TEAM_EFFECT_3_CARD_ID = 1;
    private static final Integer UPDATED_TEAM_EFFECT_3_CARD_ID = 2;

    @Autowired
    private MInitUserDeckRepository mInitUserDeckRepository;

    @Autowired
    private MInitUserDeckMapper mInitUserDeckMapper;

    @Autowired
    private MInitUserDeckService mInitUserDeckService;

    @Autowired
    private MInitUserDeckQueryService mInitUserDeckQueryService;

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

    private MockMvc restMInitUserDeckMockMvc;

    private MInitUserDeck mInitUserDeck;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final MInitUserDeckResource mInitUserDeckResource = new MInitUserDeckResource(mInitUserDeckService, mInitUserDeckQueryService);
        this.restMInitUserDeckMockMvc = MockMvcBuilders.standaloneSetup(mInitUserDeckResource)
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
    public static MInitUserDeck createEntity(EntityManager em) {
        MInitUserDeck mInitUserDeck = new MInitUserDeck()
            .deckId(DEFAULT_DECK_ID)
            .name(DEFAULT_NAME)
            .formationId(DEFAULT_FORMATION_ID)
            .captainCardId(DEFAULT_CAPTAIN_CARD_ID)
            .gkCardId(DEFAULT_GK_CARD_ID)
            .fp1CardId(DEFAULT_FP_1_CARD_ID)
            .fp2CardId(DEFAULT_FP_2_CARD_ID)
            .fp3CardId(DEFAULT_FP_3_CARD_ID)
            .fp4CardId(DEFAULT_FP_4_CARD_ID)
            .fp5CardId(DEFAULT_FP_5_CARD_ID)
            .fp6CardId(DEFAULT_FP_6_CARD_ID)
            .fp7CardId(DEFAULT_FP_7_CARD_ID)
            .fp8CardId(DEFAULT_FP_8_CARD_ID)
            .fp9CardId(DEFAULT_FP_9_CARD_ID)
            .fp10CardId(DEFAULT_FP_10_CARD_ID)
            .sub1CardId(DEFAULT_SUB_1_CARD_ID)
            .sub2CardId(DEFAULT_SUB_2_CARD_ID)
            .sub3CardId(DEFAULT_SUB_3_CARD_ID)
            .sub4CardId(DEFAULT_SUB_4_CARD_ID)
            .sub5CardId(DEFAULT_SUB_5_CARD_ID)
            .teamEffect1CardId(DEFAULT_TEAM_EFFECT_1_CARD_ID)
            .teamEffect2CardId(DEFAULT_TEAM_EFFECT_2_CARD_ID)
            .teamEffect3CardId(DEFAULT_TEAM_EFFECT_3_CARD_ID);
        return mInitUserDeck;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static MInitUserDeck createUpdatedEntity(EntityManager em) {
        MInitUserDeck mInitUserDeck = new MInitUserDeck()
            .deckId(UPDATED_DECK_ID)
            .name(UPDATED_NAME)
            .formationId(UPDATED_FORMATION_ID)
            .captainCardId(UPDATED_CAPTAIN_CARD_ID)
            .gkCardId(UPDATED_GK_CARD_ID)
            .fp1CardId(UPDATED_FP_1_CARD_ID)
            .fp2CardId(UPDATED_FP_2_CARD_ID)
            .fp3CardId(UPDATED_FP_3_CARD_ID)
            .fp4CardId(UPDATED_FP_4_CARD_ID)
            .fp5CardId(UPDATED_FP_5_CARD_ID)
            .fp6CardId(UPDATED_FP_6_CARD_ID)
            .fp7CardId(UPDATED_FP_7_CARD_ID)
            .fp8CardId(UPDATED_FP_8_CARD_ID)
            .fp9CardId(UPDATED_FP_9_CARD_ID)
            .fp10CardId(UPDATED_FP_10_CARD_ID)
            .sub1CardId(UPDATED_SUB_1_CARD_ID)
            .sub2CardId(UPDATED_SUB_2_CARD_ID)
            .sub3CardId(UPDATED_SUB_3_CARD_ID)
            .sub4CardId(UPDATED_SUB_4_CARD_ID)
            .sub5CardId(UPDATED_SUB_5_CARD_ID)
            .teamEffect1CardId(UPDATED_TEAM_EFFECT_1_CARD_ID)
            .teamEffect2CardId(UPDATED_TEAM_EFFECT_2_CARD_ID)
            .teamEffect3CardId(UPDATED_TEAM_EFFECT_3_CARD_ID);
        return mInitUserDeck;
    }

    @BeforeEach
    public void initTest() {
        mInitUserDeck = createEntity(em);
    }

    @Test
    @Transactional
    public void createMInitUserDeck() throws Exception {
        int databaseSizeBeforeCreate = mInitUserDeckRepository.findAll().size();

        // Create the MInitUserDeck
        MInitUserDeckDTO mInitUserDeckDTO = mInitUserDeckMapper.toDto(mInitUserDeck);
        restMInitUserDeckMockMvc.perform(post("/api/m-init-user-decks")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mInitUserDeckDTO)))
            .andExpect(status().isCreated());

        // Validate the MInitUserDeck in the database
        List<MInitUserDeck> mInitUserDeckList = mInitUserDeckRepository.findAll();
        assertThat(mInitUserDeckList).hasSize(databaseSizeBeforeCreate + 1);
        MInitUserDeck testMInitUserDeck = mInitUserDeckList.get(mInitUserDeckList.size() - 1);
        assertThat(testMInitUserDeck.getDeckId()).isEqualTo(DEFAULT_DECK_ID);
        assertThat(testMInitUserDeck.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testMInitUserDeck.getFormationId()).isEqualTo(DEFAULT_FORMATION_ID);
        assertThat(testMInitUserDeck.getCaptainCardId()).isEqualTo(DEFAULT_CAPTAIN_CARD_ID);
        assertThat(testMInitUserDeck.getGkCardId()).isEqualTo(DEFAULT_GK_CARD_ID);
        assertThat(testMInitUserDeck.getFp1CardId()).isEqualTo(DEFAULT_FP_1_CARD_ID);
        assertThat(testMInitUserDeck.getFp2CardId()).isEqualTo(DEFAULT_FP_2_CARD_ID);
        assertThat(testMInitUserDeck.getFp3CardId()).isEqualTo(DEFAULT_FP_3_CARD_ID);
        assertThat(testMInitUserDeck.getFp4CardId()).isEqualTo(DEFAULT_FP_4_CARD_ID);
        assertThat(testMInitUserDeck.getFp5CardId()).isEqualTo(DEFAULT_FP_5_CARD_ID);
        assertThat(testMInitUserDeck.getFp6CardId()).isEqualTo(DEFAULT_FP_6_CARD_ID);
        assertThat(testMInitUserDeck.getFp7CardId()).isEqualTo(DEFAULT_FP_7_CARD_ID);
        assertThat(testMInitUserDeck.getFp8CardId()).isEqualTo(DEFAULT_FP_8_CARD_ID);
        assertThat(testMInitUserDeck.getFp9CardId()).isEqualTo(DEFAULT_FP_9_CARD_ID);
        assertThat(testMInitUserDeck.getFp10CardId()).isEqualTo(DEFAULT_FP_10_CARD_ID);
        assertThat(testMInitUserDeck.getSub1CardId()).isEqualTo(DEFAULT_SUB_1_CARD_ID);
        assertThat(testMInitUserDeck.getSub2CardId()).isEqualTo(DEFAULT_SUB_2_CARD_ID);
        assertThat(testMInitUserDeck.getSub3CardId()).isEqualTo(DEFAULT_SUB_3_CARD_ID);
        assertThat(testMInitUserDeck.getSub4CardId()).isEqualTo(DEFAULT_SUB_4_CARD_ID);
        assertThat(testMInitUserDeck.getSub5CardId()).isEqualTo(DEFAULT_SUB_5_CARD_ID);
        assertThat(testMInitUserDeck.getTeamEffect1CardId()).isEqualTo(DEFAULT_TEAM_EFFECT_1_CARD_ID);
        assertThat(testMInitUserDeck.getTeamEffect2CardId()).isEqualTo(DEFAULT_TEAM_EFFECT_2_CARD_ID);
        assertThat(testMInitUserDeck.getTeamEffect3CardId()).isEqualTo(DEFAULT_TEAM_EFFECT_3_CARD_ID);
    }

    @Test
    @Transactional
    public void createMInitUserDeckWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = mInitUserDeckRepository.findAll().size();

        // Create the MInitUserDeck with an existing ID
        mInitUserDeck.setId(1L);
        MInitUserDeckDTO mInitUserDeckDTO = mInitUserDeckMapper.toDto(mInitUserDeck);

        // An entity with an existing ID cannot be created, so this API call must fail
        restMInitUserDeckMockMvc.perform(post("/api/m-init-user-decks")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mInitUserDeckDTO)))
            .andExpect(status().isBadRequest());

        // Validate the MInitUserDeck in the database
        List<MInitUserDeck> mInitUserDeckList = mInitUserDeckRepository.findAll();
        assertThat(mInitUserDeckList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkDeckIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = mInitUserDeckRepository.findAll().size();
        // set the field null
        mInitUserDeck.setDeckId(null);

        // Create the MInitUserDeck, which fails.
        MInitUserDeckDTO mInitUserDeckDTO = mInitUserDeckMapper.toDto(mInitUserDeck);

        restMInitUserDeckMockMvc.perform(post("/api/m-init-user-decks")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mInitUserDeckDTO)))
            .andExpect(status().isBadRequest());

        List<MInitUserDeck> mInitUserDeckList = mInitUserDeckRepository.findAll();
        assertThat(mInitUserDeckList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkFormationIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = mInitUserDeckRepository.findAll().size();
        // set the field null
        mInitUserDeck.setFormationId(null);

        // Create the MInitUserDeck, which fails.
        MInitUserDeckDTO mInitUserDeckDTO = mInitUserDeckMapper.toDto(mInitUserDeck);

        restMInitUserDeckMockMvc.perform(post("/api/m-init-user-decks")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mInitUserDeckDTO)))
            .andExpect(status().isBadRequest());

        List<MInitUserDeck> mInitUserDeckList = mInitUserDeckRepository.findAll();
        assertThat(mInitUserDeckList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllMInitUserDecks() throws Exception {
        // Initialize the database
        mInitUserDeckRepository.saveAndFlush(mInitUserDeck);

        // Get all the mInitUserDeckList
        restMInitUserDeckMockMvc.perform(get("/api/m-init-user-decks?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(mInitUserDeck.getId().intValue())))
            .andExpect(jsonPath("$.[*].deckId").value(hasItem(DEFAULT_DECK_ID)))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME.toString())))
            .andExpect(jsonPath("$.[*].formationId").value(hasItem(DEFAULT_FORMATION_ID)))
            .andExpect(jsonPath("$.[*].captainCardId").value(hasItem(DEFAULT_CAPTAIN_CARD_ID)))
            .andExpect(jsonPath("$.[*].gkCardId").value(hasItem(DEFAULT_GK_CARD_ID)))
            .andExpect(jsonPath("$.[*].fp1CardId").value(hasItem(DEFAULT_FP_1_CARD_ID)))
            .andExpect(jsonPath("$.[*].fp2CardId").value(hasItem(DEFAULT_FP_2_CARD_ID)))
            .andExpect(jsonPath("$.[*].fp3CardId").value(hasItem(DEFAULT_FP_3_CARD_ID)))
            .andExpect(jsonPath("$.[*].fp4CardId").value(hasItem(DEFAULT_FP_4_CARD_ID)))
            .andExpect(jsonPath("$.[*].fp5CardId").value(hasItem(DEFAULT_FP_5_CARD_ID)))
            .andExpect(jsonPath("$.[*].fp6CardId").value(hasItem(DEFAULT_FP_6_CARD_ID)))
            .andExpect(jsonPath("$.[*].fp7CardId").value(hasItem(DEFAULT_FP_7_CARD_ID)))
            .andExpect(jsonPath("$.[*].fp8CardId").value(hasItem(DEFAULT_FP_8_CARD_ID)))
            .andExpect(jsonPath("$.[*].fp9CardId").value(hasItem(DEFAULT_FP_9_CARD_ID)))
            .andExpect(jsonPath("$.[*].fp10CardId").value(hasItem(DEFAULT_FP_10_CARD_ID)))
            .andExpect(jsonPath("$.[*].sub1CardId").value(hasItem(DEFAULT_SUB_1_CARD_ID)))
            .andExpect(jsonPath("$.[*].sub2CardId").value(hasItem(DEFAULT_SUB_2_CARD_ID)))
            .andExpect(jsonPath("$.[*].sub3CardId").value(hasItem(DEFAULT_SUB_3_CARD_ID)))
            .andExpect(jsonPath("$.[*].sub4CardId").value(hasItem(DEFAULT_SUB_4_CARD_ID)))
            .andExpect(jsonPath("$.[*].sub5CardId").value(hasItem(DEFAULT_SUB_5_CARD_ID)))
            .andExpect(jsonPath("$.[*].teamEffect1CardId").value(hasItem(DEFAULT_TEAM_EFFECT_1_CARD_ID)))
            .andExpect(jsonPath("$.[*].teamEffect2CardId").value(hasItem(DEFAULT_TEAM_EFFECT_2_CARD_ID)))
            .andExpect(jsonPath("$.[*].teamEffect3CardId").value(hasItem(DEFAULT_TEAM_EFFECT_3_CARD_ID)));
    }
    
    @Test
    @Transactional
    public void getMInitUserDeck() throws Exception {
        // Initialize the database
        mInitUserDeckRepository.saveAndFlush(mInitUserDeck);

        // Get the mInitUserDeck
        restMInitUserDeckMockMvc.perform(get("/api/m-init-user-decks/{id}", mInitUserDeck.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(mInitUserDeck.getId().intValue()))
            .andExpect(jsonPath("$.deckId").value(DEFAULT_DECK_ID))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME.toString()))
            .andExpect(jsonPath("$.formationId").value(DEFAULT_FORMATION_ID))
            .andExpect(jsonPath("$.captainCardId").value(DEFAULT_CAPTAIN_CARD_ID))
            .andExpect(jsonPath("$.gkCardId").value(DEFAULT_GK_CARD_ID))
            .andExpect(jsonPath("$.fp1CardId").value(DEFAULT_FP_1_CARD_ID))
            .andExpect(jsonPath("$.fp2CardId").value(DEFAULT_FP_2_CARD_ID))
            .andExpect(jsonPath("$.fp3CardId").value(DEFAULT_FP_3_CARD_ID))
            .andExpect(jsonPath("$.fp4CardId").value(DEFAULT_FP_4_CARD_ID))
            .andExpect(jsonPath("$.fp5CardId").value(DEFAULT_FP_5_CARD_ID))
            .andExpect(jsonPath("$.fp6CardId").value(DEFAULT_FP_6_CARD_ID))
            .andExpect(jsonPath("$.fp7CardId").value(DEFAULT_FP_7_CARD_ID))
            .andExpect(jsonPath("$.fp8CardId").value(DEFAULT_FP_8_CARD_ID))
            .andExpect(jsonPath("$.fp9CardId").value(DEFAULT_FP_9_CARD_ID))
            .andExpect(jsonPath("$.fp10CardId").value(DEFAULT_FP_10_CARD_ID))
            .andExpect(jsonPath("$.sub1CardId").value(DEFAULT_SUB_1_CARD_ID))
            .andExpect(jsonPath("$.sub2CardId").value(DEFAULT_SUB_2_CARD_ID))
            .andExpect(jsonPath("$.sub3CardId").value(DEFAULT_SUB_3_CARD_ID))
            .andExpect(jsonPath("$.sub4CardId").value(DEFAULT_SUB_4_CARD_ID))
            .andExpect(jsonPath("$.sub5CardId").value(DEFAULT_SUB_5_CARD_ID))
            .andExpect(jsonPath("$.teamEffect1CardId").value(DEFAULT_TEAM_EFFECT_1_CARD_ID))
            .andExpect(jsonPath("$.teamEffect2CardId").value(DEFAULT_TEAM_EFFECT_2_CARD_ID))
            .andExpect(jsonPath("$.teamEffect3CardId").value(DEFAULT_TEAM_EFFECT_3_CARD_ID));
    }

    @Test
    @Transactional
    public void getAllMInitUserDecksByDeckIdIsEqualToSomething() throws Exception {
        // Initialize the database
        mInitUserDeckRepository.saveAndFlush(mInitUserDeck);

        // Get all the mInitUserDeckList where deckId equals to DEFAULT_DECK_ID
        defaultMInitUserDeckShouldBeFound("deckId.equals=" + DEFAULT_DECK_ID);

        // Get all the mInitUserDeckList where deckId equals to UPDATED_DECK_ID
        defaultMInitUserDeckShouldNotBeFound("deckId.equals=" + UPDATED_DECK_ID);
    }

    @Test
    @Transactional
    public void getAllMInitUserDecksByDeckIdIsInShouldWork() throws Exception {
        // Initialize the database
        mInitUserDeckRepository.saveAndFlush(mInitUserDeck);

        // Get all the mInitUserDeckList where deckId in DEFAULT_DECK_ID or UPDATED_DECK_ID
        defaultMInitUserDeckShouldBeFound("deckId.in=" + DEFAULT_DECK_ID + "," + UPDATED_DECK_ID);

        // Get all the mInitUserDeckList where deckId equals to UPDATED_DECK_ID
        defaultMInitUserDeckShouldNotBeFound("deckId.in=" + UPDATED_DECK_ID);
    }

    @Test
    @Transactional
    public void getAllMInitUserDecksByDeckIdIsNullOrNotNull() throws Exception {
        // Initialize the database
        mInitUserDeckRepository.saveAndFlush(mInitUserDeck);

        // Get all the mInitUserDeckList where deckId is not null
        defaultMInitUserDeckShouldBeFound("deckId.specified=true");

        // Get all the mInitUserDeckList where deckId is null
        defaultMInitUserDeckShouldNotBeFound("deckId.specified=false");
    }

    @Test
    @Transactional
    public void getAllMInitUserDecksByDeckIdIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mInitUserDeckRepository.saveAndFlush(mInitUserDeck);

        // Get all the mInitUserDeckList where deckId greater than or equals to DEFAULT_DECK_ID
        defaultMInitUserDeckShouldBeFound("deckId.greaterOrEqualThan=" + DEFAULT_DECK_ID);

        // Get all the mInitUserDeckList where deckId greater than or equals to UPDATED_DECK_ID
        defaultMInitUserDeckShouldNotBeFound("deckId.greaterOrEqualThan=" + UPDATED_DECK_ID);
    }

    @Test
    @Transactional
    public void getAllMInitUserDecksByDeckIdIsLessThanSomething() throws Exception {
        // Initialize the database
        mInitUserDeckRepository.saveAndFlush(mInitUserDeck);

        // Get all the mInitUserDeckList where deckId less than or equals to DEFAULT_DECK_ID
        defaultMInitUserDeckShouldNotBeFound("deckId.lessThan=" + DEFAULT_DECK_ID);

        // Get all the mInitUserDeckList where deckId less than or equals to UPDATED_DECK_ID
        defaultMInitUserDeckShouldBeFound("deckId.lessThan=" + UPDATED_DECK_ID);
    }


    @Test
    @Transactional
    public void getAllMInitUserDecksByFormationIdIsEqualToSomething() throws Exception {
        // Initialize the database
        mInitUserDeckRepository.saveAndFlush(mInitUserDeck);

        // Get all the mInitUserDeckList where formationId equals to DEFAULT_FORMATION_ID
        defaultMInitUserDeckShouldBeFound("formationId.equals=" + DEFAULT_FORMATION_ID);

        // Get all the mInitUserDeckList where formationId equals to UPDATED_FORMATION_ID
        defaultMInitUserDeckShouldNotBeFound("formationId.equals=" + UPDATED_FORMATION_ID);
    }

    @Test
    @Transactional
    public void getAllMInitUserDecksByFormationIdIsInShouldWork() throws Exception {
        // Initialize the database
        mInitUserDeckRepository.saveAndFlush(mInitUserDeck);

        // Get all the mInitUserDeckList where formationId in DEFAULT_FORMATION_ID or UPDATED_FORMATION_ID
        defaultMInitUserDeckShouldBeFound("formationId.in=" + DEFAULT_FORMATION_ID + "," + UPDATED_FORMATION_ID);

        // Get all the mInitUserDeckList where formationId equals to UPDATED_FORMATION_ID
        defaultMInitUserDeckShouldNotBeFound("formationId.in=" + UPDATED_FORMATION_ID);
    }

    @Test
    @Transactional
    public void getAllMInitUserDecksByFormationIdIsNullOrNotNull() throws Exception {
        // Initialize the database
        mInitUserDeckRepository.saveAndFlush(mInitUserDeck);

        // Get all the mInitUserDeckList where formationId is not null
        defaultMInitUserDeckShouldBeFound("formationId.specified=true");

        // Get all the mInitUserDeckList where formationId is null
        defaultMInitUserDeckShouldNotBeFound("formationId.specified=false");
    }

    @Test
    @Transactional
    public void getAllMInitUserDecksByFormationIdIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mInitUserDeckRepository.saveAndFlush(mInitUserDeck);

        // Get all the mInitUserDeckList where formationId greater than or equals to DEFAULT_FORMATION_ID
        defaultMInitUserDeckShouldBeFound("formationId.greaterOrEqualThan=" + DEFAULT_FORMATION_ID);

        // Get all the mInitUserDeckList where formationId greater than or equals to UPDATED_FORMATION_ID
        defaultMInitUserDeckShouldNotBeFound("formationId.greaterOrEqualThan=" + UPDATED_FORMATION_ID);
    }

    @Test
    @Transactional
    public void getAllMInitUserDecksByFormationIdIsLessThanSomething() throws Exception {
        // Initialize the database
        mInitUserDeckRepository.saveAndFlush(mInitUserDeck);

        // Get all the mInitUserDeckList where formationId less than or equals to DEFAULT_FORMATION_ID
        defaultMInitUserDeckShouldNotBeFound("formationId.lessThan=" + DEFAULT_FORMATION_ID);

        // Get all the mInitUserDeckList where formationId less than or equals to UPDATED_FORMATION_ID
        defaultMInitUserDeckShouldBeFound("formationId.lessThan=" + UPDATED_FORMATION_ID);
    }


    @Test
    @Transactional
    public void getAllMInitUserDecksByCaptainCardIdIsEqualToSomething() throws Exception {
        // Initialize the database
        mInitUserDeckRepository.saveAndFlush(mInitUserDeck);

        // Get all the mInitUserDeckList where captainCardId equals to DEFAULT_CAPTAIN_CARD_ID
        defaultMInitUserDeckShouldBeFound("captainCardId.equals=" + DEFAULT_CAPTAIN_CARD_ID);

        // Get all the mInitUserDeckList where captainCardId equals to UPDATED_CAPTAIN_CARD_ID
        defaultMInitUserDeckShouldNotBeFound("captainCardId.equals=" + UPDATED_CAPTAIN_CARD_ID);
    }

    @Test
    @Transactional
    public void getAllMInitUserDecksByCaptainCardIdIsInShouldWork() throws Exception {
        // Initialize the database
        mInitUserDeckRepository.saveAndFlush(mInitUserDeck);

        // Get all the mInitUserDeckList where captainCardId in DEFAULT_CAPTAIN_CARD_ID or UPDATED_CAPTAIN_CARD_ID
        defaultMInitUserDeckShouldBeFound("captainCardId.in=" + DEFAULT_CAPTAIN_CARD_ID + "," + UPDATED_CAPTAIN_CARD_ID);

        // Get all the mInitUserDeckList where captainCardId equals to UPDATED_CAPTAIN_CARD_ID
        defaultMInitUserDeckShouldNotBeFound("captainCardId.in=" + UPDATED_CAPTAIN_CARD_ID);
    }

    @Test
    @Transactional
    public void getAllMInitUserDecksByCaptainCardIdIsNullOrNotNull() throws Exception {
        // Initialize the database
        mInitUserDeckRepository.saveAndFlush(mInitUserDeck);

        // Get all the mInitUserDeckList where captainCardId is not null
        defaultMInitUserDeckShouldBeFound("captainCardId.specified=true");

        // Get all the mInitUserDeckList where captainCardId is null
        defaultMInitUserDeckShouldNotBeFound("captainCardId.specified=false");
    }

    @Test
    @Transactional
    public void getAllMInitUserDecksByCaptainCardIdIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mInitUserDeckRepository.saveAndFlush(mInitUserDeck);

        // Get all the mInitUserDeckList where captainCardId greater than or equals to DEFAULT_CAPTAIN_CARD_ID
        defaultMInitUserDeckShouldBeFound("captainCardId.greaterOrEqualThan=" + DEFAULT_CAPTAIN_CARD_ID);

        // Get all the mInitUserDeckList where captainCardId greater than or equals to UPDATED_CAPTAIN_CARD_ID
        defaultMInitUserDeckShouldNotBeFound("captainCardId.greaterOrEqualThan=" + UPDATED_CAPTAIN_CARD_ID);
    }

    @Test
    @Transactional
    public void getAllMInitUserDecksByCaptainCardIdIsLessThanSomething() throws Exception {
        // Initialize the database
        mInitUserDeckRepository.saveAndFlush(mInitUserDeck);

        // Get all the mInitUserDeckList where captainCardId less than or equals to DEFAULT_CAPTAIN_CARD_ID
        defaultMInitUserDeckShouldNotBeFound("captainCardId.lessThan=" + DEFAULT_CAPTAIN_CARD_ID);

        // Get all the mInitUserDeckList where captainCardId less than or equals to UPDATED_CAPTAIN_CARD_ID
        defaultMInitUserDeckShouldBeFound("captainCardId.lessThan=" + UPDATED_CAPTAIN_CARD_ID);
    }


    @Test
    @Transactional
    public void getAllMInitUserDecksByGkCardIdIsEqualToSomething() throws Exception {
        // Initialize the database
        mInitUserDeckRepository.saveAndFlush(mInitUserDeck);

        // Get all the mInitUserDeckList where gkCardId equals to DEFAULT_GK_CARD_ID
        defaultMInitUserDeckShouldBeFound("gkCardId.equals=" + DEFAULT_GK_CARD_ID);

        // Get all the mInitUserDeckList where gkCardId equals to UPDATED_GK_CARD_ID
        defaultMInitUserDeckShouldNotBeFound("gkCardId.equals=" + UPDATED_GK_CARD_ID);
    }

    @Test
    @Transactional
    public void getAllMInitUserDecksByGkCardIdIsInShouldWork() throws Exception {
        // Initialize the database
        mInitUserDeckRepository.saveAndFlush(mInitUserDeck);

        // Get all the mInitUserDeckList where gkCardId in DEFAULT_GK_CARD_ID or UPDATED_GK_CARD_ID
        defaultMInitUserDeckShouldBeFound("gkCardId.in=" + DEFAULT_GK_CARD_ID + "," + UPDATED_GK_CARD_ID);

        // Get all the mInitUserDeckList where gkCardId equals to UPDATED_GK_CARD_ID
        defaultMInitUserDeckShouldNotBeFound("gkCardId.in=" + UPDATED_GK_CARD_ID);
    }

    @Test
    @Transactional
    public void getAllMInitUserDecksByGkCardIdIsNullOrNotNull() throws Exception {
        // Initialize the database
        mInitUserDeckRepository.saveAndFlush(mInitUserDeck);

        // Get all the mInitUserDeckList where gkCardId is not null
        defaultMInitUserDeckShouldBeFound("gkCardId.specified=true");

        // Get all the mInitUserDeckList where gkCardId is null
        defaultMInitUserDeckShouldNotBeFound("gkCardId.specified=false");
    }

    @Test
    @Transactional
    public void getAllMInitUserDecksByGkCardIdIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mInitUserDeckRepository.saveAndFlush(mInitUserDeck);

        // Get all the mInitUserDeckList where gkCardId greater than or equals to DEFAULT_GK_CARD_ID
        defaultMInitUserDeckShouldBeFound("gkCardId.greaterOrEqualThan=" + DEFAULT_GK_CARD_ID);

        // Get all the mInitUserDeckList where gkCardId greater than or equals to UPDATED_GK_CARD_ID
        defaultMInitUserDeckShouldNotBeFound("gkCardId.greaterOrEqualThan=" + UPDATED_GK_CARD_ID);
    }

    @Test
    @Transactional
    public void getAllMInitUserDecksByGkCardIdIsLessThanSomething() throws Exception {
        // Initialize the database
        mInitUserDeckRepository.saveAndFlush(mInitUserDeck);

        // Get all the mInitUserDeckList where gkCardId less than or equals to DEFAULT_GK_CARD_ID
        defaultMInitUserDeckShouldNotBeFound("gkCardId.lessThan=" + DEFAULT_GK_CARD_ID);

        // Get all the mInitUserDeckList where gkCardId less than or equals to UPDATED_GK_CARD_ID
        defaultMInitUserDeckShouldBeFound("gkCardId.lessThan=" + UPDATED_GK_CARD_ID);
    }


    @Test
    @Transactional
    public void getAllMInitUserDecksByFp1CardIdIsEqualToSomething() throws Exception {
        // Initialize the database
        mInitUserDeckRepository.saveAndFlush(mInitUserDeck);

        // Get all the mInitUserDeckList where fp1CardId equals to DEFAULT_FP_1_CARD_ID
        defaultMInitUserDeckShouldBeFound("fp1CardId.equals=" + DEFAULT_FP_1_CARD_ID);

        // Get all the mInitUserDeckList where fp1CardId equals to UPDATED_FP_1_CARD_ID
        defaultMInitUserDeckShouldNotBeFound("fp1CardId.equals=" + UPDATED_FP_1_CARD_ID);
    }

    @Test
    @Transactional
    public void getAllMInitUserDecksByFp1CardIdIsInShouldWork() throws Exception {
        // Initialize the database
        mInitUserDeckRepository.saveAndFlush(mInitUserDeck);

        // Get all the mInitUserDeckList where fp1CardId in DEFAULT_FP_1_CARD_ID or UPDATED_FP_1_CARD_ID
        defaultMInitUserDeckShouldBeFound("fp1CardId.in=" + DEFAULT_FP_1_CARD_ID + "," + UPDATED_FP_1_CARD_ID);

        // Get all the mInitUserDeckList where fp1CardId equals to UPDATED_FP_1_CARD_ID
        defaultMInitUserDeckShouldNotBeFound("fp1CardId.in=" + UPDATED_FP_1_CARD_ID);
    }

    @Test
    @Transactional
    public void getAllMInitUserDecksByFp1CardIdIsNullOrNotNull() throws Exception {
        // Initialize the database
        mInitUserDeckRepository.saveAndFlush(mInitUserDeck);

        // Get all the mInitUserDeckList where fp1CardId is not null
        defaultMInitUserDeckShouldBeFound("fp1CardId.specified=true");

        // Get all the mInitUserDeckList where fp1CardId is null
        defaultMInitUserDeckShouldNotBeFound("fp1CardId.specified=false");
    }

    @Test
    @Transactional
    public void getAllMInitUserDecksByFp1CardIdIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mInitUserDeckRepository.saveAndFlush(mInitUserDeck);

        // Get all the mInitUserDeckList where fp1CardId greater than or equals to DEFAULT_FP_1_CARD_ID
        defaultMInitUserDeckShouldBeFound("fp1CardId.greaterOrEqualThan=" + DEFAULT_FP_1_CARD_ID);

        // Get all the mInitUserDeckList where fp1CardId greater than or equals to UPDATED_FP_1_CARD_ID
        defaultMInitUserDeckShouldNotBeFound("fp1CardId.greaterOrEqualThan=" + UPDATED_FP_1_CARD_ID);
    }

    @Test
    @Transactional
    public void getAllMInitUserDecksByFp1CardIdIsLessThanSomething() throws Exception {
        // Initialize the database
        mInitUserDeckRepository.saveAndFlush(mInitUserDeck);

        // Get all the mInitUserDeckList where fp1CardId less than or equals to DEFAULT_FP_1_CARD_ID
        defaultMInitUserDeckShouldNotBeFound("fp1CardId.lessThan=" + DEFAULT_FP_1_CARD_ID);

        // Get all the mInitUserDeckList where fp1CardId less than or equals to UPDATED_FP_1_CARD_ID
        defaultMInitUserDeckShouldBeFound("fp1CardId.lessThan=" + UPDATED_FP_1_CARD_ID);
    }


    @Test
    @Transactional
    public void getAllMInitUserDecksByFp2CardIdIsEqualToSomething() throws Exception {
        // Initialize the database
        mInitUserDeckRepository.saveAndFlush(mInitUserDeck);

        // Get all the mInitUserDeckList where fp2CardId equals to DEFAULT_FP_2_CARD_ID
        defaultMInitUserDeckShouldBeFound("fp2CardId.equals=" + DEFAULT_FP_2_CARD_ID);

        // Get all the mInitUserDeckList where fp2CardId equals to UPDATED_FP_2_CARD_ID
        defaultMInitUserDeckShouldNotBeFound("fp2CardId.equals=" + UPDATED_FP_2_CARD_ID);
    }

    @Test
    @Transactional
    public void getAllMInitUserDecksByFp2CardIdIsInShouldWork() throws Exception {
        // Initialize the database
        mInitUserDeckRepository.saveAndFlush(mInitUserDeck);

        // Get all the mInitUserDeckList where fp2CardId in DEFAULT_FP_2_CARD_ID or UPDATED_FP_2_CARD_ID
        defaultMInitUserDeckShouldBeFound("fp2CardId.in=" + DEFAULT_FP_2_CARD_ID + "," + UPDATED_FP_2_CARD_ID);

        // Get all the mInitUserDeckList where fp2CardId equals to UPDATED_FP_2_CARD_ID
        defaultMInitUserDeckShouldNotBeFound("fp2CardId.in=" + UPDATED_FP_2_CARD_ID);
    }

    @Test
    @Transactional
    public void getAllMInitUserDecksByFp2CardIdIsNullOrNotNull() throws Exception {
        // Initialize the database
        mInitUserDeckRepository.saveAndFlush(mInitUserDeck);

        // Get all the mInitUserDeckList where fp2CardId is not null
        defaultMInitUserDeckShouldBeFound("fp2CardId.specified=true");

        // Get all the mInitUserDeckList where fp2CardId is null
        defaultMInitUserDeckShouldNotBeFound("fp2CardId.specified=false");
    }

    @Test
    @Transactional
    public void getAllMInitUserDecksByFp2CardIdIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mInitUserDeckRepository.saveAndFlush(mInitUserDeck);

        // Get all the mInitUserDeckList where fp2CardId greater than or equals to DEFAULT_FP_2_CARD_ID
        defaultMInitUserDeckShouldBeFound("fp2CardId.greaterOrEqualThan=" + DEFAULT_FP_2_CARD_ID);

        // Get all the mInitUserDeckList where fp2CardId greater than or equals to UPDATED_FP_2_CARD_ID
        defaultMInitUserDeckShouldNotBeFound("fp2CardId.greaterOrEqualThan=" + UPDATED_FP_2_CARD_ID);
    }

    @Test
    @Transactional
    public void getAllMInitUserDecksByFp2CardIdIsLessThanSomething() throws Exception {
        // Initialize the database
        mInitUserDeckRepository.saveAndFlush(mInitUserDeck);

        // Get all the mInitUserDeckList where fp2CardId less than or equals to DEFAULT_FP_2_CARD_ID
        defaultMInitUserDeckShouldNotBeFound("fp2CardId.lessThan=" + DEFAULT_FP_2_CARD_ID);

        // Get all the mInitUserDeckList where fp2CardId less than or equals to UPDATED_FP_2_CARD_ID
        defaultMInitUserDeckShouldBeFound("fp2CardId.lessThan=" + UPDATED_FP_2_CARD_ID);
    }


    @Test
    @Transactional
    public void getAllMInitUserDecksByFp3CardIdIsEqualToSomething() throws Exception {
        // Initialize the database
        mInitUserDeckRepository.saveAndFlush(mInitUserDeck);

        // Get all the mInitUserDeckList where fp3CardId equals to DEFAULT_FP_3_CARD_ID
        defaultMInitUserDeckShouldBeFound("fp3CardId.equals=" + DEFAULT_FP_3_CARD_ID);

        // Get all the mInitUserDeckList where fp3CardId equals to UPDATED_FP_3_CARD_ID
        defaultMInitUserDeckShouldNotBeFound("fp3CardId.equals=" + UPDATED_FP_3_CARD_ID);
    }

    @Test
    @Transactional
    public void getAllMInitUserDecksByFp3CardIdIsInShouldWork() throws Exception {
        // Initialize the database
        mInitUserDeckRepository.saveAndFlush(mInitUserDeck);

        // Get all the mInitUserDeckList where fp3CardId in DEFAULT_FP_3_CARD_ID or UPDATED_FP_3_CARD_ID
        defaultMInitUserDeckShouldBeFound("fp3CardId.in=" + DEFAULT_FP_3_CARD_ID + "," + UPDATED_FP_3_CARD_ID);

        // Get all the mInitUserDeckList where fp3CardId equals to UPDATED_FP_3_CARD_ID
        defaultMInitUserDeckShouldNotBeFound("fp3CardId.in=" + UPDATED_FP_3_CARD_ID);
    }

    @Test
    @Transactional
    public void getAllMInitUserDecksByFp3CardIdIsNullOrNotNull() throws Exception {
        // Initialize the database
        mInitUserDeckRepository.saveAndFlush(mInitUserDeck);

        // Get all the mInitUserDeckList where fp3CardId is not null
        defaultMInitUserDeckShouldBeFound("fp3CardId.specified=true");

        // Get all the mInitUserDeckList where fp3CardId is null
        defaultMInitUserDeckShouldNotBeFound("fp3CardId.specified=false");
    }

    @Test
    @Transactional
    public void getAllMInitUserDecksByFp3CardIdIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mInitUserDeckRepository.saveAndFlush(mInitUserDeck);

        // Get all the mInitUserDeckList where fp3CardId greater than or equals to DEFAULT_FP_3_CARD_ID
        defaultMInitUserDeckShouldBeFound("fp3CardId.greaterOrEqualThan=" + DEFAULT_FP_3_CARD_ID);

        // Get all the mInitUserDeckList where fp3CardId greater than or equals to UPDATED_FP_3_CARD_ID
        defaultMInitUserDeckShouldNotBeFound("fp3CardId.greaterOrEqualThan=" + UPDATED_FP_3_CARD_ID);
    }

    @Test
    @Transactional
    public void getAllMInitUserDecksByFp3CardIdIsLessThanSomething() throws Exception {
        // Initialize the database
        mInitUserDeckRepository.saveAndFlush(mInitUserDeck);

        // Get all the mInitUserDeckList where fp3CardId less than or equals to DEFAULT_FP_3_CARD_ID
        defaultMInitUserDeckShouldNotBeFound("fp3CardId.lessThan=" + DEFAULT_FP_3_CARD_ID);

        // Get all the mInitUserDeckList where fp3CardId less than or equals to UPDATED_FP_3_CARD_ID
        defaultMInitUserDeckShouldBeFound("fp3CardId.lessThan=" + UPDATED_FP_3_CARD_ID);
    }


    @Test
    @Transactional
    public void getAllMInitUserDecksByFp4CardIdIsEqualToSomething() throws Exception {
        // Initialize the database
        mInitUserDeckRepository.saveAndFlush(mInitUserDeck);

        // Get all the mInitUserDeckList where fp4CardId equals to DEFAULT_FP_4_CARD_ID
        defaultMInitUserDeckShouldBeFound("fp4CardId.equals=" + DEFAULT_FP_4_CARD_ID);

        // Get all the mInitUserDeckList where fp4CardId equals to UPDATED_FP_4_CARD_ID
        defaultMInitUserDeckShouldNotBeFound("fp4CardId.equals=" + UPDATED_FP_4_CARD_ID);
    }

    @Test
    @Transactional
    public void getAllMInitUserDecksByFp4CardIdIsInShouldWork() throws Exception {
        // Initialize the database
        mInitUserDeckRepository.saveAndFlush(mInitUserDeck);

        // Get all the mInitUserDeckList where fp4CardId in DEFAULT_FP_4_CARD_ID or UPDATED_FP_4_CARD_ID
        defaultMInitUserDeckShouldBeFound("fp4CardId.in=" + DEFAULT_FP_4_CARD_ID + "," + UPDATED_FP_4_CARD_ID);

        // Get all the mInitUserDeckList where fp4CardId equals to UPDATED_FP_4_CARD_ID
        defaultMInitUserDeckShouldNotBeFound("fp4CardId.in=" + UPDATED_FP_4_CARD_ID);
    }

    @Test
    @Transactional
    public void getAllMInitUserDecksByFp4CardIdIsNullOrNotNull() throws Exception {
        // Initialize the database
        mInitUserDeckRepository.saveAndFlush(mInitUserDeck);

        // Get all the mInitUserDeckList where fp4CardId is not null
        defaultMInitUserDeckShouldBeFound("fp4CardId.specified=true");

        // Get all the mInitUserDeckList where fp4CardId is null
        defaultMInitUserDeckShouldNotBeFound("fp4CardId.specified=false");
    }

    @Test
    @Transactional
    public void getAllMInitUserDecksByFp4CardIdIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mInitUserDeckRepository.saveAndFlush(mInitUserDeck);

        // Get all the mInitUserDeckList where fp4CardId greater than or equals to DEFAULT_FP_4_CARD_ID
        defaultMInitUserDeckShouldBeFound("fp4CardId.greaterOrEqualThan=" + DEFAULT_FP_4_CARD_ID);

        // Get all the mInitUserDeckList where fp4CardId greater than or equals to UPDATED_FP_4_CARD_ID
        defaultMInitUserDeckShouldNotBeFound("fp4CardId.greaterOrEqualThan=" + UPDATED_FP_4_CARD_ID);
    }

    @Test
    @Transactional
    public void getAllMInitUserDecksByFp4CardIdIsLessThanSomething() throws Exception {
        // Initialize the database
        mInitUserDeckRepository.saveAndFlush(mInitUserDeck);

        // Get all the mInitUserDeckList where fp4CardId less than or equals to DEFAULT_FP_4_CARD_ID
        defaultMInitUserDeckShouldNotBeFound("fp4CardId.lessThan=" + DEFAULT_FP_4_CARD_ID);

        // Get all the mInitUserDeckList where fp4CardId less than or equals to UPDATED_FP_4_CARD_ID
        defaultMInitUserDeckShouldBeFound("fp4CardId.lessThan=" + UPDATED_FP_4_CARD_ID);
    }


    @Test
    @Transactional
    public void getAllMInitUserDecksByFp5CardIdIsEqualToSomething() throws Exception {
        // Initialize the database
        mInitUserDeckRepository.saveAndFlush(mInitUserDeck);

        // Get all the mInitUserDeckList where fp5CardId equals to DEFAULT_FP_5_CARD_ID
        defaultMInitUserDeckShouldBeFound("fp5CardId.equals=" + DEFAULT_FP_5_CARD_ID);

        // Get all the mInitUserDeckList where fp5CardId equals to UPDATED_FP_5_CARD_ID
        defaultMInitUserDeckShouldNotBeFound("fp5CardId.equals=" + UPDATED_FP_5_CARD_ID);
    }

    @Test
    @Transactional
    public void getAllMInitUserDecksByFp5CardIdIsInShouldWork() throws Exception {
        // Initialize the database
        mInitUserDeckRepository.saveAndFlush(mInitUserDeck);

        // Get all the mInitUserDeckList where fp5CardId in DEFAULT_FP_5_CARD_ID or UPDATED_FP_5_CARD_ID
        defaultMInitUserDeckShouldBeFound("fp5CardId.in=" + DEFAULT_FP_5_CARD_ID + "," + UPDATED_FP_5_CARD_ID);

        // Get all the mInitUserDeckList where fp5CardId equals to UPDATED_FP_5_CARD_ID
        defaultMInitUserDeckShouldNotBeFound("fp5CardId.in=" + UPDATED_FP_5_CARD_ID);
    }

    @Test
    @Transactional
    public void getAllMInitUserDecksByFp5CardIdIsNullOrNotNull() throws Exception {
        // Initialize the database
        mInitUserDeckRepository.saveAndFlush(mInitUserDeck);

        // Get all the mInitUserDeckList where fp5CardId is not null
        defaultMInitUserDeckShouldBeFound("fp5CardId.specified=true");

        // Get all the mInitUserDeckList where fp5CardId is null
        defaultMInitUserDeckShouldNotBeFound("fp5CardId.specified=false");
    }

    @Test
    @Transactional
    public void getAllMInitUserDecksByFp5CardIdIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mInitUserDeckRepository.saveAndFlush(mInitUserDeck);

        // Get all the mInitUserDeckList where fp5CardId greater than or equals to DEFAULT_FP_5_CARD_ID
        defaultMInitUserDeckShouldBeFound("fp5CardId.greaterOrEqualThan=" + DEFAULT_FP_5_CARD_ID);

        // Get all the mInitUserDeckList where fp5CardId greater than or equals to UPDATED_FP_5_CARD_ID
        defaultMInitUserDeckShouldNotBeFound("fp5CardId.greaterOrEqualThan=" + UPDATED_FP_5_CARD_ID);
    }

    @Test
    @Transactional
    public void getAllMInitUserDecksByFp5CardIdIsLessThanSomething() throws Exception {
        // Initialize the database
        mInitUserDeckRepository.saveAndFlush(mInitUserDeck);

        // Get all the mInitUserDeckList where fp5CardId less than or equals to DEFAULT_FP_5_CARD_ID
        defaultMInitUserDeckShouldNotBeFound("fp5CardId.lessThan=" + DEFAULT_FP_5_CARD_ID);

        // Get all the mInitUserDeckList where fp5CardId less than or equals to UPDATED_FP_5_CARD_ID
        defaultMInitUserDeckShouldBeFound("fp5CardId.lessThan=" + UPDATED_FP_5_CARD_ID);
    }


    @Test
    @Transactional
    public void getAllMInitUserDecksByFp6CardIdIsEqualToSomething() throws Exception {
        // Initialize the database
        mInitUserDeckRepository.saveAndFlush(mInitUserDeck);

        // Get all the mInitUserDeckList where fp6CardId equals to DEFAULT_FP_6_CARD_ID
        defaultMInitUserDeckShouldBeFound("fp6CardId.equals=" + DEFAULT_FP_6_CARD_ID);

        // Get all the mInitUserDeckList where fp6CardId equals to UPDATED_FP_6_CARD_ID
        defaultMInitUserDeckShouldNotBeFound("fp6CardId.equals=" + UPDATED_FP_6_CARD_ID);
    }

    @Test
    @Transactional
    public void getAllMInitUserDecksByFp6CardIdIsInShouldWork() throws Exception {
        // Initialize the database
        mInitUserDeckRepository.saveAndFlush(mInitUserDeck);

        // Get all the mInitUserDeckList where fp6CardId in DEFAULT_FP_6_CARD_ID or UPDATED_FP_6_CARD_ID
        defaultMInitUserDeckShouldBeFound("fp6CardId.in=" + DEFAULT_FP_6_CARD_ID + "," + UPDATED_FP_6_CARD_ID);

        // Get all the mInitUserDeckList where fp6CardId equals to UPDATED_FP_6_CARD_ID
        defaultMInitUserDeckShouldNotBeFound("fp6CardId.in=" + UPDATED_FP_6_CARD_ID);
    }

    @Test
    @Transactional
    public void getAllMInitUserDecksByFp6CardIdIsNullOrNotNull() throws Exception {
        // Initialize the database
        mInitUserDeckRepository.saveAndFlush(mInitUserDeck);

        // Get all the mInitUserDeckList where fp6CardId is not null
        defaultMInitUserDeckShouldBeFound("fp6CardId.specified=true");

        // Get all the mInitUserDeckList where fp6CardId is null
        defaultMInitUserDeckShouldNotBeFound("fp6CardId.specified=false");
    }

    @Test
    @Transactional
    public void getAllMInitUserDecksByFp6CardIdIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mInitUserDeckRepository.saveAndFlush(mInitUserDeck);

        // Get all the mInitUserDeckList where fp6CardId greater than or equals to DEFAULT_FP_6_CARD_ID
        defaultMInitUserDeckShouldBeFound("fp6CardId.greaterOrEqualThan=" + DEFAULT_FP_6_CARD_ID);

        // Get all the mInitUserDeckList where fp6CardId greater than or equals to UPDATED_FP_6_CARD_ID
        defaultMInitUserDeckShouldNotBeFound("fp6CardId.greaterOrEqualThan=" + UPDATED_FP_6_CARD_ID);
    }

    @Test
    @Transactional
    public void getAllMInitUserDecksByFp6CardIdIsLessThanSomething() throws Exception {
        // Initialize the database
        mInitUserDeckRepository.saveAndFlush(mInitUserDeck);

        // Get all the mInitUserDeckList where fp6CardId less than or equals to DEFAULT_FP_6_CARD_ID
        defaultMInitUserDeckShouldNotBeFound("fp6CardId.lessThan=" + DEFAULT_FP_6_CARD_ID);

        // Get all the mInitUserDeckList where fp6CardId less than or equals to UPDATED_FP_6_CARD_ID
        defaultMInitUserDeckShouldBeFound("fp6CardId.lessThan=" + UPDATED_FP_6_CARD_ID);
    }


    @Test
    @Transactional
    public void getAllMInitUserDecksByFp7CardIdIsEqualToSomething() throws Exception {
        // Initialize the database
        mInitUserDeckRepository.saveAndFlush(mInitUserDeck);

        // Get all the mInitUserDeckList where fp7CardId equals to DEFAULT_FP_7_CARD_ID
        defaultMInitUserDeckShouldBeFound("fp7CardId.equals=" + DEFAULT_FP_7_CARD_ID);

        // Get all the mInitUserDeckList where fp7CardId equals to UPDATED_FP_7_CARD_ID
        defaultMInitUserDeckShouldNotBeFound("fp7CardId.equals=" + UPDATED_FP_7_CARD_ID);
    }

    @Test
    @Transactional
    public void getAllMInitUserDecksByFp7CardIdIsInShouldWork() throws Exception {
        // Initialize the database
        mInitUserDeckRepository.saveAndFlush(mInitUserDeck);

        // Get all the mInitUserDeckList where fp7CardId in DEFAULT_FP_7_CARD_ID or UPDATED_FP_7_CARD_ID
        defaultMInitUserDeckShouldBeFound("fp7CardId.in=" + DEFAULT_FP_7_CARD_ID + "," + UPDATED_FP_7_CARD_ID);

        // Get all the mInitUserDeckList where fp7CardId equals to UPDATED_FP_7_CARD_ID
        defaultMInitUserDeckShouldNotBeFound("fp7CardId.in=" + UPDATED_FP_7_CARD_ID);
    }

    @Test
    @Transactional
    public void getAllMInitUserDecksByFp7CardIdIsNullOrNotNull() throws Exception {
        // Initialize the database
        mInitUserDeckRepository.saveAndFlush(mInitUserDeck);

        // Get all the mInitUserDeckList where fp7CardId is not null
        defaultMInitUserDeckShouldBeFound("fp7CardId.specified=true");

        // Get all the mInitUserDeckList where fp7CardId is null
        defaultMInitUserDeckShouldNotBeFound("fp7CardId.specified=false");
    }

    @Test
    @Transactional
    public void getAllMInitUserDecksByFp7CardIdIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mInitUserDeckRepository.saveAndFlush(mInitUserDeck);

        // Get all the mInitUserDeckList where fp7CardId greater than or equals to DEFAULT_FP_7_CARD_ID
        defaultMInitUserDeckShouldBeFound("fp7CardId.greaterOrEqualThan=" + DEFAULT_FP_7_CARD_ID);

        // Get all the mInitUserDeckList where fp7CardId greater than or equals to UPDATED_FP_7_CARD_ID
        defaultMInitUserDeckShouldNotBeFound("fp7CardId.greaterOrEqualThan=" + UPDATED_FP_7_CARD_ID);
    }

    @Test
    @Transactional
    public void getAllMInitUserDecksByFp7CardIdIsLessThanSomething() throws Exception {
        // Initialize the database
        mInitUserDeckRepository.saveAndFlush(mInitUserDeck);

        // Get all the mInitUserDeckList where fp7CardId less than or equals to DEFAULT_FP_7_CARD_ID
        defaultMInitUserDeckShouldNotBeFound("fp7CardId.lessThan=" + DEFAULT_FP_7_CARD_ID);

        // Get all the mInitUserDeckList where fp7CardId less than or equals to UPDATED_FP_7_CARD_ID
        defaultMInitUserDeckShouldBeFound("fp7CardId.lessThan=" + UPDATED_FP_7_CARD_ID);
    }


    @Test
    @Transactional
    public void getAllMInitUserDecksByFp8CardIdIsEqualToSomething() throws Exception {
        // Initialize the database
        mInitUserDeckRepository.saveAndFlush(mInitUserDeck);

        // Get all the mInitUserDeckList where fp8CardId equals to DEFAULT_FP_8_CARD_ID
        defaultMInitUserDeckShouldBeFound("fp8CardId.equals=" + DEFAULT_FP_8_CARD_ID);

        // Get all the mInitUserDeckList where fp8CardId equals to UPDATED_FP_8_CARD_ID
        defaultMInitUserDeckShouldNotBeFound("fp8CardId.equals=" + UPDATED_FP_8_CARD_ID);
    }

    @Test
    @Transactional
    public void getAllMInitUserDecksByFp8CardIdIsInShouldWork() throws Exception {
        // Initialize the database
        mInitUserDeckRepository.saveAndFlush(mInitUserDeck);

        // Get all the mInitUserDeckList where fp8CardId in DEFAULT_FP_8_CARD_ID or UPDATED_FP_8_CARD_ID
        defaultMInitUserDeckShouldBeFound("fp8CardId.in=" + DEFAULT_FP_8_CARD_ID + "," + UPDATED_FP_8_CARD_ID);

        // Get all the mInitUserDeckList where fp8CardId equals to UPDATED_FP_8_CARD_ID
        defaultMInitUserDeckShouldNotBeFound("fp8CardId.in=" + UPDATED_FP_8_CARD_ID);
    }

    @Test
    @Transactional
    public void getAllMInitUserDecksByFp8CardIdIsNullOrNotNull() throws Exception {
        // Initialize the database
        mInitUserDeckRepository.saveAndFlush(mInitUserDeck);

        // Get all the mInitUserDeckList where fp8CardId is not null
        defaultMInitUserDeckShouldBeFound("fp8CardId.specified=true");

        // Get all the mInitUserDeckList where fp8CardId is null
        defaultMInitUserDeckShouldNotBeFound("fp8CardId.specified=false");
    }

    @Test
    @Transactional
    public void getAllMInitUserDecksByFp8CardIdIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mInitUserDeckRepository.saveAndFlush(mInitUserDeck);

        // Get all the mInitUserDeckList where fp8CardId greater than or equals to DEFAULT_FP_8_CARD_ID
        defaultMInitUserDeckShouldBeFound("fp8CardId.greaterOrEqualThan=" + DEFAULT_FP_8_CARD_ID);

        // Get all the mInitUserDeckList where fp8CardId greater than or equals to UPDATED_FP_8_CARD_ID
        defaultMInitUserDeckShouldNotBeFound("fp8CardId.greaterOrEqualThan=" + UPDATED_FP_8_CARD_ID);
    }

    @Test
    @Transactional
    public void getAllMInitUserDecksByFp8CardIdIsLessThanSomething() throws Exception {
        // Initialize the database
        mInitUserDeckRepository.saveAndFlush(mInitUserDeck);

        // Get all the mInitUserDeckList where fp8CardId less than or equals to DEFAULT_FP_8_CARD_ID
        defaultMInitUserDeckShouldNotBeFound("fp8CardId.lessThan=" + DEFAULT_FP_8_CARD_ID);

        // Get all the mInitUserDeckList where fp8CardId less than or equals to UPDATED_FP_8_CARD_ID
        defaultMInitUserDeckShouldBeFound("fp8CardId.lessThan=" + UPDATED_FP_8_CARD_ID);
    }


    @Test
    @Transactional
    public void getAllMInitUserDecksByFp9CardIdIsEqualToSomething() throws Exception {
        // Initialize the database
        mInitUserDeckRepository.saveAndFlush(mInitUserDeck);

        // Get all the mInitUserDeckList where fp9CardId equals to DEFAULT_FP_9_CARD_ID
        defaultMInitUserDeckShouldBeFound("fp9CardId.equals=" + DEFAULT_FP_9_CARD_ID);

        // Get all the mInitUserDeckList where fp9CardId equals to UPDATED_FP_9_CARD_ID
        defaultMInitUserDeckShouldNotBeFound("fp9CardId.equals=" + UPDATED_FP_9_CARD_ID);
    }

    @Test
    @Transactional
    public void getAllMInitUserDecksByFp9CardIdIsInShouldWork() throws Exception {
        // Initialize the database
        mInitUserDeckRepository.saveAndFlush(mInitUserDeck);

        // Get all the mInitUserDeckList where fp9CardId in DEFAULT_FP_9_CARD_ID or UPDATED_FP_9_CARD_ID
        defaultMInitUserDeckShouldBeFound("fp9CardId.in=" + DEFAULT_FP_9_CARD_ID + "," + UPDATED_FP_9_CARD_ID);

        // Get all the mInitUserDeckList where fp9CardId equals to UPDATED_FP_9_CARD_ID
        defaultMInitUserDeckShouldNotBeFound("fp9CardId.in=" + UPDATED_FP_9_CARD_ID);
    }

    @Test
    @Transactional
    public void getAllMInitUserDecksByFp9CardIdIsNullOrNotNull() throws Exception {
        // Initialize the database
        mInitUserDeckRepository.saveAndFlush(mInitUserDeck);

        // Get all the mInitUserDeckList where fp9CardId is not null
        defaultMInitUserDeckShouldBeFound("fp9CardId.specified=true");

        // Get all the mInitUserDeckList where fp9CardId is null
        defaultMInitUserDeckShouldNotBeFound("fp9CardId.specified=false");
    }

    @Test
    @Transactional
    public void getAllMInitUserDecksByFp9CardIdIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mInitUserDeckRepository.saveAndFlush(mInitUserDeck);

        // Get all the mInitUserDeckList where fp9CardId greater than or equals to DEFAULT_FP_9_CARD_ID
        defaultMInitUserDeckShouldBeFound("fp9CardId.greaterOrEqualThan=" + DEFAULT_FP_9_CARD_ID);

        // Get all the mInitUserDeckList where fp9CardId greater than or equals to UPDATED_FP_9_CARD_ID
        defaultMInitUserDeckShouldNotBeFound("fp9CardId.greaterOrEqualThan=" + UPDATED_FP_9_CARD_ID);
    }

    @Test
    @Transactional
    public void getAllMInitUserDecksByFp9CardIdIsLessThanSomething() throws Exception {
        // Initialize the database
        mInitUserDeckRepository.saveAndFlush(mInitUserDeck);

        // Get all the mInitUserDeckList where fp9CardId less than or equals to DEFAULT_FP_9_CARD_ID
        defaultMInitUserDeckShouldNotBeFound("fp9CardId.lessThan=" + DEFAULT_FP_9_CARD_ID);

        // Get all the mInitUserDeckList where fp9CardId less than or equals to UPDATED_FP_9_CARD_ID
        defaultMInitUserDeckShouldBeFound("fp9CardId.lessThan=" + UPDATED_FP_9_CARD_ID);
    }


    @Test
    @Transactional
    public void getAllMInitUserDecksByFp10CardIdIsEqualToSomething() throws Exception {
        // Initialize the database
        mInitUserDeckRepository.saveAndFlush(mInitUserDeck);

        // Get all the mInitUserDeckList where fp10CardId equals to DEFAULT_FP_10_CARD_ID
        defaultMInitUserDeckShouldBeFound("fp10CardId.equals=" + DEFAULT_FP_10_CARD_ID);

        // Get all the mInitUserDeckList where fp10CardId equals to UPDATED_FP_10_CARD_ID
        defaultMInitUserDeckShouldNotBeFound("fp10CardId.equals=" + UPDATED_FP_10_CARD_ID);
    }

    @Test
    @Transactional
    public void getAllMInitUserDecksByFp10CardIdIsInShouldWork() throws Exception {
        // Initialize the database
        mInitUserDeckRepository.saveAndFlush(mInitUserDeck);

        // Get all the mInitUserDeckList where fp10CardId in DEFAULT_FP_10_CARD_ID or UPDATED_FP_10_CARD_ID
        defaultMInitUserDeckShouldBeFound("fp10CardId.in=" + DEFAULT_FP_10_CARD_ID + "," + UPDATED_FP_10_CARD_ID);

        // Get all the mInitUserDeckList where fp10CardId equals to UPDATED_FP_10_CARD_ID
        defaultMInitUserDeckShouldNotBeFound("fp10CardId.in=" + UPDATED_FP_10_CARD_ID);
    }

    @Test
    @Transactional
    public void getAllMInitUserDecksByFp10CardIdIsNullOrNotNull() throws Exception {
        // Initialize the database
        mInitUserDeckRepository.saveAndFlush(mInitUserDeck);

        // Get all the mInitUserDeckList where fp10CardId is not null
        defaultMInitUserDeckShouldBeFound("fp10CardId.specified=true");

        // Get all the mInitUserDeckList where fp10CardId is null
        defaultMInitUserDeckShouldNotBeFound("fp10CardId.specified=false");
    }

    @Test
    @Transactional
    public void getAllMInitUserDecksByFp10CardIdIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mInitUserDeckRepository.saveAndFlush(mInitUserDeck);

        // Get all the mInitUserDeckList where fp10CardId greater than or equals to DEFAULT_FP_10_CARD_ID
        defaultMInitUserDeckShouldBeFound("fp10CardId.greaterOrEqualThan=" + DEFAULT_FP_10_CARD_ID);

        // Get all the mInitUserDeckList where fp10CardId greater than or equals to UPDATED_FP_10_CARD_ID
        defaultMInitUserDeckShouldNotBeFound("fp10CardId.greaterOrEqualThan=" + UPDATED_FP_10_CARD_ID);
    }

    @Test
    @Transactional
    public void getAllMInitUserDecksByFp10CardIdIsLessThanSomething() throws Exception {
        // Initialize the database
        mInitUserDeckRepository.saveAndFlush(mInitUserDeck);

        // Get all the mInitUserDeckList where fp10CardId less than or equals to DEFAULT_FP_10_CARD_ID
        defaultMInitUserDeckShouldNotBeFound("fp10CardId.lessThan=" + DEFAULT_FP_10_CARD_ID);

        // Get all the mInitUserDeckList where fp10CardId less than or equals to UPDATED_FP_10_CARD_ID
        defaultMInitUserDeckShouldBeFound("fp10CardId.lessThan=" + UPDATED_FP_10_CARD_ID);
    }


    @Test
    @Transactional
    public void getAllMInitUserDecksBySub1CardIdIsEqualToSomething() throws Exception {
        // Initialize the database
        mInitUserDeckRepository.saveAndFlush(mInitUserDeck);

        // Get all the mInitUserDeckList where sub1CardId equals to DEFAULT_SUB_1_CARD_ID
        defaultMInitUserDeckShouldBeFound("sub1CardId.equals=" + DEFAULT_SUB_1_CARD_ID);

        // Get all the mInitUserDeckList where sub1CardId equals to UPDATED_SUB_1_CARD_ID
        defaultMInitUserDeckShouldNotBeFound("sub1CardId.equals=" + UPDATED_SUB_1_CARD_ID);
    }

    @Test
    @Transactional
    public void getAllMInitUserDecksBySub1CardIdIsInShouldWork() throws Exception {
        // Initialize the database
        mInitUserDeckRepository.saveAndFlush(mInitUserDeck);

        // Get all the mInitUserDeckList where sub1CardId in DEFAULT_SUB_1_CARD_ID or UPDATED_SUB_1_CARD_ID
        defaultMInitUserDeckShouldBeFound("sub1CardId.in=" + DEFAULT_SUB_1_CARD_ID + "," + UPDATED_SUB_1_CARD_ID);

        // Get all the mInitUserDeckList where sub1CardId equals to UPDATED_SUB_1_CARD_ID
        defaultMInitUserDeckShouldNotBeFound("sub1CardId.in=" + UPDATED_SUB_1_CARD_ID);
    }

    @Test
    @Transactional
    public void getAllMInitUserDecksBySub1CardIdIsNullOrNotNull() throws Exception {
        // Initialize the database
        mInitUserDeckRepository.saveAndFlush(mInitUserDeck);

        // Get all the mInitUserDeckList where sub1CardId is not null
        defaultMInitUserDeckShouldBeFound("sub1CardId.specified=true");

        // Get all the mInitUserDeckList where sub1CardId is null
        defaultMInitUserDeckShouldNotBeFound("sub1CardId.specified=false");
    }

    @Test
    @Transactional
    public void getAllMInitUserDecksBySub1CardIdIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mInitUserDeckRepository.saveAndFlush(mInitUserDeck);

        // Get all the mInitUserDeckList where sub1CardId greater than or equals to DEFAULT_SUB_1_CARD_ID
        defaultMInitUserDeckShouldBeFound("sub1CardId.greaterOrEqualThan=" + DEFAULT_SUB_1_CARD_ID);

        // Get all the mInitUserDeckList where sub1CardId greater than or equals to UPDATED_SUB_1_CARD_ID
        defaultMInitUserDeckShouldNotBeFound("sub1CardId.greaterOrEqualThan=" + UPDATED_SUB_1_CARD_ID);
    }

    @Test
    @Transactional
    public void getAllMInitUserDecksBySub1CardIdIsLessThanSomething() throws Exception {
        // Initialize the database
        mInitUserDeckRepository.saveAndFlush(mInitUserDeck);

        // Get all the mInitUserDeckList where sub1CardId less than or equals to DEFAULT_SUB_1_CARD_ID
        defaultMInitUserDeckShouldNotBeFound("sub1CardId.lessThan=" + DEFAULT_SUB_1_CARD_ID);

        // Get all the mInitUserDeckList where sub1CardId less than or equals to UPDATED_SUB_1_CARD_ID
        defaultMInitUserDeckShouldBeFound("sub1CardId.lessThan=" + UPDATED_SUB_1_CARD_ID);
    }


    @Test
    @Transactional
    public void getAllMInitUserDecksBySub2CardIdIsEqualToSomething() throws Exception {
        // Initialize the database
        mInitUserDeckRepository.saveAndFlush(mInitUserDeck);

        // Get all the mInitUserDeckList where sub2CardId equals to DEFAULT_SUB_2_CARD_ID
        defaultMInitUserDeckShouldBeFound("sub2CardId.equals=" + DEFAULT_SUB_2_CARD_ID);

        // Get all the mInitUserDeckList where sub2CardId equals to UPDATED_SUB_2_CARD_ID
        defaultMInitUserDeckShouldNotBeFound("sub2CardId.equals=" + UPDATED_SUB_2_CARD_ID);
    }

    @Test
    @Transactional
    public void getAllMInitUserDecksBySub2CardIdIsInShouldWork() throws Exception {
        // Initialize the database
        mInitUserDeckRepository.saveAndFlush(mInitUserDeck);

        // Get all the mInitUserDeckList where sub2CardId in DEFAULT_SUB_2_CARD_ID or UPDATED_SUB_2_CARD_ID
        defaultMInitUserDeckShouldBeFound("sub2CardId.in=" + DEFAULT_SUB_2_CARD_ID + "," + UPDATED_SUB_2_CARD_ID);

        // Get all the mInitUserDeckList where sub2CardId equals to UPDATED_SUB_2_CARD_ID
        defaultMInitUserDeckShouldNotBeFound("sub2CardId.in=" + UPDATED_SUB_2_CARD_ID);
    }

    @Test
    @Transactional
    public void getAllMInitUserDecksBySub2CardIdIsNullOrNotNull() throws Exception {
        // Initialize the database
        mInitUserDeckRepository.saveAndFlush(mInitUserDeck);

        // Get all the mInitUserDeckList where sub2CardId is not null
        defaultMInitUserDeckShouldBeFound("sub2CardId.specified=true");

        // Get all the mInitUserDeckList where sub2CardId is null
        defaultMInitUserDeckShouldNotBeFound("sub2CardId.specified=false");
    }

    @Test
    @Transactional
    public void getAllMInitUserDecksBySub2CardIdIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mInitUserDeckRepository.saveAndFlush(mInitUserDeck);

        // Get all the mInitUserDeckList where sub2CardId greater than or equals to DEFAULT_SUB_2_CARD_ID
        defaultMInitUserDeckShouldBeFound("sub2CardId.greaterOrEqualThan=" + DEFAULT_SUB_2_CARD_ID);

        // Get all the mInitUserDeckList where sub2CardId greater than or equals to UPDATED_SUB_2_CARD_ID
        defaultMInitUserDeckShouldNotBeFound("sub2CardId.greaterOrEqualThan=" + UPDATED_SUB_2_CARD_ID);
    }

    @Test
    @Transactional
    public void getAllMInitUserDecksBySub2CardIdIsLessThanSomething() throws Exception {
        // Initialize the database
        mInitUserDeckRepository.saveAndFlush(mInitUserDeck);

        // Get all the mInitUserDeckList where sub2CardId less than or equals to DEFAULT_SUB_2_CARD_ID
        defaultMInitUserDeckShouldNotBeFound("sub2CardId.lessThan=" + DEFAULT_SUB_2_CARD_ID);

        // Get all the mInitUserDeckList where sub2CardId less than or equals to UPDATED_SUB_2_CARD_ID
        defaultMInitUserDeckShouldBeFound("sub2CardId.lessThan=" + UPDATED_SUB_2_CARD_ID);
    }


    @Test
    @Transactional
    public void getAllMInitUserDecksBySub3CardIdIsEqualToSomething() throws Exception {
        // Initialize the database
        mInitUserDeckRepository.saveAndFlush(mInitUserDeck);

        // Get all the mInitUserDeckList where sub3CardId equals to DEFAULT_SUB_3_CARD_ID
        defaultMInitUserDeckShouldBeFound("sub3CardId.equals=" + DEFAULT_SUB_3_CARD_ID);

        // Get all the mInitUserDeckList where sub3CardId equals to UPDATED_SUB_3_CARD_ID
        defaultMInitUserDeckShouldNotBeFound("sub3CardId.equals=" + UPDATED_SUB_3_CARD_ID);
    }

    @Test
    @Transactional
    public void getAllMInitUserDecksBySub3CardIdIsInShouldWork() throws Exception {
        // Initialize the database
        mInitUserDeckRepository.saveAndFlush(mInitUserDeck);

        // Get all the mInitUserDeckList where sub3CardId in DEFAULT_SUB_3_CARD_ID or UPDATED_SUB_3_CARD_ID
        defaultMInitUserDeckShouldBeFound("sub3CardId.in=" + DEFAULT_SUB_3_CARD_ID + "," + UPDATED_SUB_3_CARD_ID);

        // Get all the mInitUserDeckList where sub3CardId equals to UPDATED_SUB_3_CARD_ID
        defaultMInitUserDeckShouldNotBeFound("sub3CardId.in=" + UPDATED_SUB_3_CARD_ID);
    }

    @Test
    @Transactional
    public void getAllMInitUserDecksBySub3CardIdIsNullOrNotNull() throws Exception {
        // Initialize the database
        mInitUserDeckRepository.saveAndFlush(mInitUserDeck);

        // Get all the mInitUserDeckList where sub3CardId is not null
        defaultMInitUserDeckShouldBeFound("sub3CardId.specified=true");

        // Get all the mInitUserDeckList where sub3CardId is null
        defaultMInitUserDeckShouldNotBeFound("sub3CardId.specified=false");
    }

    @Test
    @Transactional
    public void getAllMInitUserDecksBySub3CardIdIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mInitUserDeckRepository.saveAndFlush(mInitUserDeck);

        // Get all the mInitUserDeckList where sub3CardId greater than or equals to DEFAULT_SUB_3_CARD_ID
        defaultMInitUserDeckShouldBeFound("sub3CardId.greaterOrEqualThan=" + DEFAULT_SUB_3_CARD_ID);

        // Get all the mInitUserDeckList where sub3CardId greater than or equals to UPDATED_SUB_3_CARD_ID
        defaultMInitUserDeckShouldNotBeFound("sub3CardId.greaterOrEqualThan=" + UPDATED_SUB_3_CARD_ID);
    }

    @Test
    @Transactional
    public void getAllMInitUserDecksBySub3CardIdIsLessThanSomething() throws Exception {
        // Initialize the database
        mInitUserDeckRepository.saveAndFlush(mInitUserDeck);

        // Get all the mInitUserDeckList where sub3CardId less than or equals to DEFAULT_SUB_3_CARD_ID
        defaultMInitUserDeckShouldNotBeFound("sub3CardId.lessThan=" + DEFAULT_SUB_3_CARD_ID);

        // Get all the mInitUserDeckList where sub3CardId less than or equals to UPDATED_SUB_3_CARD_ID
        defaultMInitUserDeckShouldBeFound("sub3CardId.lessThan=" + UPDATED_SUB_3_CARD_ID);
    }


    @Test
    @Transactional
    public void getAllMInitUserDecksBySub4CardIdIsEqualToSomething() throws Exception {
        // Initialize the database
        mInitUserDeckRepository.saveAndFlush(mInitUserDeck);

        // Get all the mInitUserDeckList where sub4CardId equals to DEFAULT_SUB_4_CARD_ID
        defaultMInitUserDeckShouldBeFound("sub4CardId.equals=" + DEFAULT_SUB_4_CARD_ID);

        // Get all the mInitUserDeckList where sub4CardId equals to UPDATED_SUB_4_CARD_ID
        defaultMInitUserDeckShouldNotBeFound("sub4CardId.equals=" + UPDATED_SUB_4_CARD_ID);
    }

    @Test
    @Transactional
    public void getAllMInitUserDecksBySub4CardIdIsInShouldWork() throws Exception {
        // Initialize the database
        mInitUserDeckRepository.saveAndFlush(mInitUserDeck);

        // Get all the mInitUserDeckList where sub4CardId in DEFAULT_SUB_4_CARD_ID or UPDATED_SUB_4_CARD_ID
        defaultMInitUserDeckShouldBeFound("sub4CardId.in=" + DEFAULT_SUB_4_CARD_ID + "," + UPDATED_SUB_4_CARD_ID);

        // Get all the mInitUserDeckList where sub4CardId equals to UPDATED_SUB_4_CARD_ID
        defaultMInitUserDeckShouldNotBeFound("sub4CardId.in=" + UPDATED_SUB_4_CARD_ID);
    }

    @Test
    @Transactional
    public void getAllMInitUserDecksBySub4CardIdIsNullOrNotNull() throws Exception {
        // Initialize the database
        mInitUserDeckRepository.saveAndFlush(mInitUserDeck);

        // Get all the mInitUserDeckList where sub4CardId is not null
        defaultMInitUserDeckShouldBeFound("sub4CardId.specified=true");

        // Get all the mInitUserDeckList where sub4CardId is null
        defaultMInitUserDeckShouldNotBeFound("sub4CardId.specified=false");
    }

    @Test
    @Transactional
    public void getAllMInitUserDecksBySub4CardIdIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mInitUserDeckRepository.saveAndFlush(mInitUserDeck);

        // Get all the mInitUserDeckList where sub4CardId greater than or equals to DEFAULT_SUB_4_CARD_ID
        defaultMInitUserDeckShouldBeFound("sub4CardId.greaterOrEqualThan=" + DEFAULT_SUB_4_CARD_ID);

        // Get all the mInitUserDeckList where sub4CardId greater than or equals to UPDATED_SUB_4_CARD_ID
        defaultMInitUserDeckShouldNotBeFound("sub4CardId.greaterOrEqualThan=" + UPDATED_SUB_4_CARD_ID);
    }

    @Test
    @Transactional
    public void getAllMInitUserDecksBySub4CardIdIsLessThanSomething() throws Exception {
        // Initialize the database
        mInitUserDeckRepository.saveAndFlush(mInitUserDeck);

        // Get all the mInitUserDeckList where sub4CardId less than or equals to DEFAULT_SUB_4_CARD_ID
        defaultMInitUserDeckShouldNotBeFound("sub4CardId.lessThan=" + DEFAULT_SUB_4_CARD_ID);

        // Get all the mInitUserDeckList where sub4CardId less than or equals to UPDATED_SUB_4_CARD_ID
        defaultMInitUserDeckShouldBeFound("sub4CardId.lessThan=" + UPDATED_SUB_4_CARD_ID);
    }


    @Test
    @Transactional
    public void getAllMInitUserDecksBySub5CardIdIsEqualToSomething() throws Exception {
        // Initialize the database
        mInitUserDeckRepository.saveAndFlush(mInitUserDeck);

        // Get all the mInitUserDeckList where sub5CardId equals to DEFAULT_SUB_5_CARD_ID
        defaultMInitUserDeckShouldBeFound("sub5CardId.equals=" + DEFAULT_SUB_5_CARD_ID);

        // Get all the mInitUserDeckList where sub5CardId equals to UPDATED_SUB_5_CARD_ID
        defaultMInitUserDeckShouldNotBeFound("sub5CardId.equals=" + UPDATED_SUB_5_CARD_ID);
    }

    @Test
    @Transactional
    public void getAllMInitUserDecksBySub5CardIdIsInShouldWork() throws Exception {
        // Initialize the database
        mInitUserDeckRepository.saveAndFlush(mInitUserDeck);

        // Get all the mInitUserDeckList where sub5CardId in DEFAULT_SUB_5_CARD_ID or UPDATED_SUB_5_CARD_ID
        defaultMInitUserDeckShouldBeFound("sub5CardId.in=" + DEFAULT_SUB_5_CARD_ID + "," + UPDATED_SUB_5_CARD_ID);

        // Get all the mInitUserDeckList where sub5CardId equals to UPDATED_SUB_5_CARD_ID
        defaultMInitUserDeckShouldNotBeFound("sub5CardId.in=" + UPDATED_SUB_5_CARD_ID);
    }

    @Test
    @Transactional
    public void getAllMInitUserDecksBySub5CardIdIsNullOrNotNull() throws Exception {
        // Initialize the database
        mInitUserDeckRepository.saveAndFlush(mInitUserDeck);

        // Get all the mInitUserDeckList where sub5CardId is not null
        defaultMInitUserDeckShouldBeFound("sub5CardId.specified=true");

        // Get all the mInitUserDeckList where sub5CardId is null
        defaultMInitUserDeckShouldNotBeFound("sub5CardId.specified=false");
    }

    @Test
    @Transactional
    public void getAllMInitUserDecksBySub5CardIdIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mInitUserDeckRepository.saveAndFlush(mInitUserDeck);

        // Get all the mInitUserDeckList where sub5CardId greater than or equals to DEFAULT_SUB_5_CARD_ID
        defaultMInitUserDeckShouldBeFound("sub5CardId.greaterOrEqualThan=" + DEFAULT_SUB_5_CARD_ID);

        // Get all the mInitUserDeckList where sub5CardId greater than or equals to UPDATED_SUB_5_CARD_ID
        defaultMInitUserDeckShouldNotBeFound("sub5CardId.greaterOrEqualThan=" + UPDATED_SUB_5_CARD_ID);
    }

    @Test
    @Transactional
    public void getAllMInitUserDecksBySub5CardIdIsLessThanSomething() throws Exception {
        // Initialize the database
        mInitUserDeckRepository.saveAndFlush(mInitUserDeck);

        // Get all the mInitUserDeckList where sub5CardId less than or equals to DEFAULT_SUB_5_CARD_ID
        defaultMInitUserDeckShouldNotBeFound("sub5CardId.lessThan=" + DEFAULT_SUB_5_CARD_ID);

        // Get all the mInitUserDeckList where sub5CardId less than or equals to UPDATED_SUB_5_CARD_ID
        defaultMInitUserDeckShouldBeFound("sub5CardId.lessThan=" + UPDATED_SUB_5_CARD_ID);
    }


    @Test
    @Transactional
    public void getAllMInitUserDecksByTeamEffect1CardIdIsEqualToSomething() throws Exception {
        // Initialize the database
        mInitUserDeckRepository.saveAndFlush(mInitUserDeck);

        // Get all the mInitUserDeckList where teamEffect1CardId equals to DEFAULT_TEAM_EFFECT_1_CARD_ID
        defaultMInitUserDeckShouldBeFound("teamEffect1CardId.equals=" + DEFAULT_TEAM_EFFECT_1_CARD_ID);

        // Get all the mInitUserDeckList where teamEffect1CardId equals to UPDATED_TEAM_EFFECT_1_CARD_ID
        defaultMInitUserDeckShouldNotBeFound("teamEffect1CardId.equals=" + UPDATED_TEAM_EFFECT_1_CARD_ID);
    }

    @Test
    @Transactional
    public void getAllMInitUserDecksByTeamEffect1CardIdIsInShouldWork() throws Exception {
        // Initialize the database
        mInitUserDeckRepository.saveAndFlush(mInitUserDeck);

        // Get all the mInitUserDeckList where teamEffect1CardId in DEFAULT_TEAM_EFFECT_1_CARD_ID or UPDATED_TEAM_EFFECT_1_CARD_ID
        defaultMInitUserDeckShouldBeFound("teamEffect1CardId.in=" + DEFAULT_TEAM_EFFECT_1_CARD_ID + "," + UPDATED_TEAM_EFFECT_1_CARD_ID);

        // Get all the mInitUserDeckList where teamEffect1CardId equals to UPDATED_TEAM_EFFECT_1_CARD_ID
        defaultMInitUserDeckShouldNotBeFound("teamEffect1CardId.in=" + UPDATED_TEAM_EFFECT_1_CARD_ID);
    }

    @Test
    @Transactional
    public void getAllMInitUserDecksByTeamEffect1CardIdIsNullOrNotNull() throws Exception {
        // Initialize the database
        mInitUserDeckRepository.saveAndFlush(mInitUserDeck);

        // Get all the mInitUserDeckList where teamEffect1CardId is not null
        defaultMInitUserDeckShouldBeFound("teamEffect1CardId.specified=true");

        // Get all the mInitUserDeckList where teamEffect1CardId is null
        defaultMInitUserDeckShouldNotBeFound("teamEffect1CardId.specified=false");
    }

    @Test
    @Transactional
    public void getAllMInitUserDecksByTeamEffect1CardIdIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mInitUserDeckRepository.saveAndFlush(mInitUserDeck);

        // Get all the mInitUserDeckList where teamEffect1CardId greater than or equals to DEFAULT_TEAM_EFFECT_1_CARD_ID
        defaultMInitUserDeckShouldBeFound("teamEffect1CardId.greaterOrEqualThan=" + DEFAULT_TEAM_EFFECT_1_CARD_ID);

        // Get all the mInitUserDeckList where teamEffect1CardId greater than or equals to UPDATED_TEAM_EFFECT_1_CARD_ID
        defaultMInitUserDeckShouldNotBeFound("teamEffect1CardId.greaterOrEqualThan=" + UPDATED_TEAM_EFFECT_1_CARD_ID);
    }

    @Test
    @Transactional
    public void getAllMInitUserDecksByTeamEffect1CardIdIsLessThanSomething() throws Exception {
        // Initialize the database
        mInitUserDeckRepository.saveAndFlush(mInitUserDeck);

        // Get all the mInitUserDeckList where teamEffect1CardId less than or equals to DEFAULT_TEAM_EFFECT_1_CARD_ID
        defaultMInitUserDeckShouldNotBeFound("teamEffect1CardId.lessThan=" + DEFAULT_TEAM_EFFECT_1_CARD_ID);

        // Get all the mInitUserDeckList where teamEffect1CardId less than or equals to UPDATED_TEAM_EFFECT_1_CARD_ID
        defaultMInitUserDeckShouldBeFound("teamEffect1CardId.lessThan=" + UPDATED_TEAM_EFFECT_1_CARD_ID);
    }


    @Test
    @Transactional
    public void getAllMInitUserDecksByTeamEffect2CardIdIsEqualToSomething() throws Exception {
        // Initialize the database
        mInitUserDeckRepository.saveAndFlush(mInitUserDeck);

        // Get all the mInitUserDeckList where teamEffect2CardId equals to DEFAULT_TEAM_EFFECT_2_CARD_ID
        defaultMInitUserDeckShouldBeFound("teamEffect2CardId.equals=" + DEFAULT_TEAM_EFFECT_2_CARD_ID);

        // Get all the mInitUserDeckList where teamEffect2CardId equals to UPDATED_TEAM_EFFECT_2_CARD_ID
        defaultMInitUserDeckShouldNotBeFound("teamEffect2CardId.equals=" + UPDATED_TEAM_EFFECT_2_CARD_ID);
    }

    @Test
    @Transactional
    public void getAllMInitUserDecksByTeamEffect2CardIdIsInShouldWork() throws Exception {
        // Initialize the database
        mInitUserDeckRepository.saveAndFlush(mInitUserDeck);

        // Get all the mInitUserDeckList where teamEffect2CardId in DEFAULT_TEAM_EFFECT_2_CARD_ID or UPDATED_TEAM_EFFECT_2_CARD_ID
        defaultMInitUserDeckShouldBeFound("teamEffect2CardId.in=" + DEFAULT_TEAM_EFFECT_2_CARD_ID + "," + UPDATED_TEAM_EFFECT_2_CARD_ID);

        // Get all the mInitUserDeckList where teamEffect2CardId equals to UPDATED_TEAM_EFFECT_2_CARD_ID
        defaultMInitUserDeckShouldNotBeFound("teamEffect2CardId.in=" + UPDATED_TEAM_EFFECT_2_CARD_ID);
    }

    @Test
    @Transactional
    public void getAllMInitUserDecksByTeamEffect2CardIdIsNullOrNotNull() throws Exception {
        // Initialize the database
        mInitUserDeckRepository.saveAndFlush(mInitUserDeck);

        // Get all the mInitUserDeckList where teamEffect2CardId is not null
        defaultMInitUserDeckShouldBeFound("teamEffect2CardId.specified=true");

        // Get all the mInitUserDeckList where teamEffect2CardId is null
        defaultMInitUserDeckShouldNotBeFound("teamEffect2CardId.specified=false");
    }

    @Test
    @Transactional
    public void getAllMInitUserDecksByTeamEffect2CardIdIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mInitUserDeckRepository.saveAndFlush(mInitUserDeck);

        // Get all the mInitUserDeckList where teamEffect2CardId greater than or equals to DEFAULT_TEAM_EFFECT_2_CARD_ID
        defaultMInitUserDeckShouldBeFound("teamEffect2CardId.greaterOrEqualThan=" + DEFAULT_TEAM_EFFECT_2_CARD_ID);

        // Get all the mInitUserDeckList where teamEffect2CardId greater than or equals to UPDATED_TEAM_EFFECT_2_CARD_ID
        defaultMInitUserDeckShouldNotBeFound("teamEffect2CardId.greaterOrEqualThan=" + UPDATED_TEAM_EFFECT_2_CARD_ID);
    }

    @Test
    @Transactional
    public void getAllMInitUserDecksByTeamEffect2CardIdIsLessThanSomething() throws Exception {
        // Initialize the database
        mInitUserDeckRepository.saveAndFlush(mInitUserDeck);

        // Get all the mInitUserDeckList where teamEffect2CardId less than or equals to DEFAULT_TEAM_EFFECT_2_CARD_ID
        defaultMInitUserDeckShouldNotBeFound("teamEffect2CardId.lessThan=" + DEFAULT_TEAM_EFFECT_2_CARD_ID);

        // Get all the mInitUserDeckList where teamEffect2CardId less than or equals to UPDATED_TEAM_EFFECT_2_CARD_ID
        defaultMInitUserDeckShouldBeFound("teamEffect2CardId.lessThan=" + UPDATED_TEAM_EFFECT_2_CARD_ID);
    }


    @Test
    @Transactional
    public void getAllMInitUserDecksByTeamEffect3CardIdIsEqualToSomething() throws Exception {
        // Initialize the database
        mInitUserDeckRepository.saveAndFlush(mInitUserDeck);

        // Get all the mInitUserDeckList where teamEffect3CardId equals to DEFAULT_TEAM_EFFECT_3_CARD_ID
        defaultMInitUserDeckShouldBeFound("teamEffect3CardId.equals=" + DEFAULT_TEAM_EFFECT_3_CARD_ID);

        // Get all the mInitUserDeckList where teamEffect3CardId equals to UPDATED_TEAM_EFFECT_3_CARD_ID
        defaultMInitUserDeckShouldNotBeFound("teamEffect3CardId.equals=" + UPDATED_TEAM_EFFECT_3_CARD_ID);
    }

    @Test
    @Transactional
    public void getAllMInitUserDecksByTeamEffect3CardIdIsInShouldWork() throws Exception {
        // Initialize the database
        mInitUserDeckRepository.saveAndFlush(mInitUserDeck);

        // Get all the mInitUserDeckList where teamEffect3CardId in DEFAULT_TEAM_EFFECT_3_CARD_ID or UPDATED_TEAM_EFFECT_3_CARD_ID
        defaultMInitUserDeckShouldBeFound("teamEffect3CardId.in=" + DEFAULT_TEAM_EFFECT_3_CARD_ID + "," + UPDATED_TEAM_EFFECT_3_CARD_ID);

        // Get all the mInitUserDeckList where teamEffect3CardId equals to UPDATED_TEAM_EFFECT_3_CARD_ID
        defaultMInitUserDeckShouldNotBeFound("teamEffect3CardId.in=" + UPDATED_TEAM_EFFECT_3_CARD_ID);
    }

    @Test
    @Transactional
    public void getAllMInitUserDecksByTeamEffect3CardIdIsNullOrNotNull() throws Exception {
        // Initialize the database
        mInitUserDeckRepository.saveAndFlush(mInitUserDeck);

        // Get all the mInitUserDeckList where teamEffect3CardId is not null
        defaultMInitUserDeckShouldBeFound("teamEffect3CardId.specified=true");

        // Get all the mInitUserDeckList where teamEffect3CardId is null
        defaultMInitUserDeckShouldNotBeFound("teamEffect3CardId.specified=false");
    }

    @Test
    @Transactional
    public void getAllMInitUserDecksByTeamEffect3CardIdIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mInitUserDeckRepository.saveAndFlush(mInitUserDeck);

        // Get all the mInitUserDeckList where teamEffect3CardId greater than or equals to DEFAULT_TEAM_EFFECT_3_CARD_ID
        defaultMInitUserDeckShouldBeFound("teamEffect3CardId.greaterOrEqualThan=" + DEFAULT_TEAM_EFFECT_3_CARD_ID);

        // Get all the mInitUserDeckList where teamEffect3CardId greater than or equals to UPDATED_TEAM_EFFECT_3_CARD_ID
        defaultMInitUserDeckShouldNotBeFound("teamEffect3CardId.greaterOrEqualThan=" + UPDATED_TEAM_EFFECT_3_CARD_ID);
    }

    @Test
    @Transactional
    public void getAllMInitUserDecksByTeamEffect3CardIdIsLessThanSomething() throws Exception {
        // Initialize the database
        mInitUserDeckRepository.saveAndFlush(mInitUserDeck);

        // Get all the mInitUserDeckList where teamEffect3CardId less than or equals to DEFAULT_TEAM_EFFECT_3_CARD_ID
        defaultMInitUserDeckShouldNotBeFound("teamEffect3CardId.lessThan=" + DEFAULT_TEAM_EFFECT_3_CARD_ID);

        // Get all the mInitUserDeckList where teamEffect3CardId less than or equals to UPDATED_TEAM_EFFECT_3_CARD_ID
        defaultMInitUserDeckShouldBeFound("teamEffect3CardId.lessThan=" + UPDATED_TEAM_EFFECT_3_CARD_ID);
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultMInitUserDeckShouldBeFound(String filter) throws Exception {
        restMInitUserDeckMockMvc.perform(get("/api/m-init-user-decks?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(mInitUserDeck.getId().intValue())))
            .andExpect(jsonPath("$.[*].deckId").value(hasItem(DEFAULT_DECK_ID)))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME.toString())))
            .andExpect(jsonPath("$.[*].formationId").value(hasItem(DEFAULT_FORMATION_ID)))
            .andExpect(jsonPath("$.[*].captainCardId").value(hasItem(DEFAULT_CAPTAIN_CARD_ID)))
            .andExpect(jsonPath("$.[*].gkCardId").value(hasItem(DEFAULT_GK_CARD_ID)))
            .andExpect(jsonPath("$.[*].fp1CardId").value(hasItem(DEFAULT_FP_1_CARD_ID)))
            .andExpect(jsonPath("$.[*].fp2CardId").value(hasItem(DEFAULT_FP_2_CARD_ID)))
            .andExpect(jsonPath("$.[*].fp3CardId").value(hasItem(DEFAULT_FP_3_CARD_ID)))
            .andExpect(jsonPath("$.[*].fp4CardId").value(hasItem(DEFAULT_FP_4_CARD_ID)))
            .andExpect(jsonPath("$.[*].fp5CardId").value(hasItem(DEFAULT_FP_5_CARD_ID)))
            .andExpect(jsonPath("$.[*].fp6CardId").value(hasItem(DEFAULT_FP_6_CARD_ID)))
            .andExpect(jsonPath("$.[*].fp7CardId").value(hasItem(DEFAULT_FP_7_CARD_ID)))
            .andExpect(jsonPath("$.[*].fp8CardId").value(hasItem(DEFAULT_FP_8_CARD_ID)))
            .andExpect(jsonPath("$.[*].fp9CardId").value(hasItem(DEFAULT_FP_9_CARD_ID)))
            .andExpect(jsonPath("$.[*].fp10CardId").value(hasItem(DEFAULT_FP_10_CARD_ID)))
            .andExpect(jsonPath("$.[*].sub1CardId").value(hasItem(DEFAULT_SUB_1_CARD_ID)))
            .andExpect(jsonPath("$.[*].sub2CardId").value(hasItem(DEFAULT_SUB_2_CARD_ID)))
            .andExpect(jsonPath("$.[*].sub3CardId").value(hasItem(DEFAULT_SUB_3_CARD_ID)))
            .andExpect(jsonPath("$.[*].sub4CardId").value(hasItem(DEFAULT_SUB_4_CARD_ID)))
            .andExpect(jsonPath("$.[*].sub5CardId").value(hasItem(DEFAULT_SUB_5_CARD_ID)))
            .andExpect(jsonPath("$.[*].teamEffect1CardId").value(hasItem(DEFAULT_TEAM_EFFECT_1_CARD_ID)))
            .andExpect(jsonPath("$.[*].teamEffect2CardId").value(hasItem(DEFAULT_TEAM_EFFECT_2_CARD_ID)))
            .andExpect(jsonPath("$.[*].teamEffect3CardId").value(hasItem(DEFAULT_TEAM_EFFECT_3_CARD_ID)));

        // Check, that the count call also returns 1
        restMInitUserDeckMockMvc.perform(get("/api/m-init-user-decks/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultMInitUserDeckShouldNotBeFound(String filter) throws Exception {
        restMInitUserDeckMockMvc.perform(get("/api/m-init-user-decks?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restMInitUserDeckMockMvc.perform(get("/api/m-init-user-decks/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("0"));
    }


    @Test
    @Transactional
    public void getNonExistingMInitUserDeck() throws Exception {
        // Get the mInitUserDeck
        restMInitUserDeckMockMvc.perform(get("/api/m-init-user-decks/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateMInitUserDeck() throws Exception {
        // Initialize the database
        mInitUserDeckRepository.saveAndFlush(mInitUserDeck);

        int databaseSizeBeforeUpdate = mInitUserDeckRepository.findAll().size();

        // Update the mInitUserDeck
        MInitUserDeck updatedMInitUserDeck = mInitUserDeckRepository.findById(mInitUserDeck.getId()).get();
        // Disconnect from session so that the updates on updatedMInitUserDeck are not directly saved in db
        em.detach(updatedMInitUserDeck);
        updatedMInitUserDeck
            .deckId(UPDATED_DECK_ID)
            .name(UPDATED_NAME)
            .formationId(UPDATED_FORMATION_ID)
            .captainCardId(UPDATED_CAPTAIN_CARD_ID)
            .gkCardId(UPDATED_GK_CARD_ID)
            .fp1CardId(UPDATED_FP_1_CARD_ID)
            .fp2CardId(UPDATED_FP_2_CARD_ID)
            .fp3CardId(UPDATED_FP_3_CARD_ID)
            .fp4CardId(UPDATED_FP_4_CARD_ID)
            .fp5CardId(UPDATED_FP_5_CARD_ID)
            .fp6CardId(UPDATED_FP_6_CARD_ID)
            .fp7CardId(UPDATED_FP_7_CARD_ID)
            .fp8CardId(UPDATED_FP_8_CARD_ID)
            .fp9CardId(UPDATED_FP_9_CARD_ID)
            .fp10CardId(UPDATED_FP_10_CARD_ID)
            .sub1CardId(UPDATED_SUB_1_CARD_ID)
            .sub2CardId(UPDATED_SUB_2_CARD_ID)
            .sub3CardId(UPDATED_SUB_3_CARD_ID)
            .sub4CardId(UPDATED_SUB_4_CARD_ID)
            .sub5CardId(UPDATED_SUB_5_CARD_ID)
            .teamEffect1CardId(UPDATED_TEAM_EFFECT_1_CARD_ID)
            .teamEffect2CardId(UPDATED_TEAM_EFFECT_2_CARD_ID)
            .teamEffect3CardId(UPDATED_TEAM_EFFECT_3_CARD_ID);
        MInitUserDeckDTO mInitUserDeckDTO = mInitUserDeckMapper.toDto(updatedMInitUserDeck);

        restMInitUserDeckMockMvc.perform(put("/api/m-init-user-decks")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mInitUserDeckDTO)))
            .andExpect(status().isOk());

        // Validate the MInitUserDeck in the database
        List<MInitUserDeck> mInitUserDeckList = mInitUserDeckRepository.findAll();
        assertThat(mInitUserDeckList).hasSize(databaseSizeBeforeUpdate);
        MInitUserDeck testMInitUserDeck = mInitUserDeckList.get(mInitUserDeckList.size() - 1);
        assertThat(testMInitUserDeck.getDeckId()).isEqualTo(UPDATED_DECK_ID);
        assertThat(testMInitUserDeck.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testMInitUserDeck.getFormationId()).isEqualTo(UPDATED_FORMATION_ID);
        assertThat(testMInitUserDeck.getCaptainCardId()).isEqualTo(UPDATED_CAPTAIN_CARD_ID);
        assertThat(testMInitUserDeck.getGkCardId()).isEqualTo(UPDATED_GK_CARD_ID);
        assertThat(testMInitUserDeck.getFp1CardId()).isEqualTo(UPDATED_FP_1_CARD_ID);
        assertThat(testMInitUserDeck.getFp2CardId()).isEqualTo(UPDATED_FP_2_CARD_ID);
        assertThat(testMInitUserDeck.getFp3CardId()).isEqualTo(UPDATED_FP_3_CARD_ID);
        assertThat(testMInitUserDeck.getFp4CardId()).isEqualTo(UPDATED_FP_4_CARD_ID);
        assertThat(testMInitUserDeck.getFp5CardId()).isEqualTo(UPDATED_FP_5_CARD_ID);
        assertThat(testMInitUserDeck.getFp6CardId()).isEqualTo(UPDATED_FP_6_CARD_ID);
        assertThat(testMInitUserDeck.getFp7CardId()).isEqualTo(UPDATED_FP_7_CARD_ID);
        assertThat(testMInitUserDeck.getFp8CardId()).isEqualTo(UPDATED_FP_8_CARD_ID);
        assertThat(testMInitUserDeck.getFp9CardId()).isEqualTo(UPDATED_FP_9_CARD_ID);
        assertThat(testMInitUserDeck.getFp10CardId()).isEqualTo(UPDATED_FP_10_CARD_ID);
        assertThat(testMInitUserDeck.getSub1CardId()).isEqualTo(UPDATED_SUB_1_CARD_ID);
        assertThat(testMInitUserDeck.getSub2CardId()).isEqualTo(UPDATED_SUB_2_CARD_ID);
        assertThat(testMInitUserDeck.getSub3CardId()).isEqualTo(UPDATED_SUB_3_CARD_ID);
        assertThat(testMInitUserDeck.getSub4CardId()).isEqualTo(UPDATED_SUB_4_CARD_ID);
        assertThat(testMInitUserDeck.getSub5CardId()).isEqualTo(UPDATED_SUB_5_CARD_ID);
        assertThat(testMInitUserDeck.getTeamEffect1CardId()).isEqualTo(UPDATED_TEAM_EFFECT_1_CARD_ID);
        assertThat(testMInitUserDeck.getTeamEffect2CardId()).isEqualTo(UPDATED_TEAM_EFFECT_2_CARD_ID);
        assertThat(testMInitUserDeck.getTeamEffect3CardId()).isEqualTo(UPDATED_TEAM_EFFECT_3_CARD_ID);
    }

    @Test
    @Transactional
    public void updateNonExistingMInitUserDeck() throws Exception {
        int databaseSizeBeforeUpdate = mInitUserDeckRepository.findAll().size();

        // Create the MInitUserDeck
        MInitUserDeckDTO mInitUserDeckDTO = mInitUserDeckMapper.toDto(mInitUserDeck);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restMInitUserDeckMockMvc.perform(put("/api/m-init-user-decks")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mInitUserDeckDTO)))
            .andExpect(status().isBadRequest());

        // Validate the MInitUserDeck in the database
        List<MInitUserDeck> mInitUserDeckList = mInitUserDeckRepository.findAll();
        assertThat(mInitUserDeckList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteMInitUserDeck() throws Exception {
        // Initialize the database
        mInitUserDeckRepository.saveAndFlush(mInitUserDeck);

        int databaseSizeBeforeDelete = mInitUserDeckRepository.findAll().size();

        // Delete the mInitUserDeck
        restMInitUserDeckMockMvc.perform(delete("/api/m-init-user-decks/{id}", mInitUserDeck.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database is empty
        List<MInitUserDeck> mInitUserDeckList = mInitUserDeckRepository.findAll();
        assertThat(mInitUserDeckList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(MInitUserDeck.class);
        MInitUserDeck mInitUserDeck1 = new MInitUserDeck();
        mInitUserDeck1.setId(1L);
        MInitUserDeck mInitUserDeck2 = new MInitUserDeck();
        mInitUserDeck2.setId(mInitUserDeck1.getId());
        assertThat(mInitUserDeck1).isEqualTo(mInitUserDeck2);
        mInitUserDeck2.setId(2L);
        assertThat(mInitUserDeck1).isNotEqualTo(mInitUserDeck2);
        mInitUserDeck1.setId(null);
        assertThat(mInitUserDeck1).isNotEqualTo(mInitUserDeck2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(MInitUserDeckDTO.class);
        MInitUserDeckDTO mInitUserDeckDTO1 = new MInitUserDeckDTO();
        mInitUserDeckDTO1.setId(1L);
        MInitUserDeckDTO mInitUserDeckDTO2 = new MInitUserDeckDTO();
        assertThat(mInitUserDeckDTO1).isNotEqualTo(mInitUserDeckDTO2);
        mInitUserDeckDTO2.setId(mInitUserDeckDTO1.getId());
        assertThat(mInitUserDeckDTO1).isEqualTo(mInitUserDeckDTO2);
        mInitUserDeckDTO2.setId(2L);
        assertThat(mInitUserDeckDTO1).isNotEqualTo(mInitUserDeckDTO2);
        mInitUserDeckDTO1.setId(null);
        assertThat(mInitUserDeckDTO1).isNotEqualTo(mInitUserDeckDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(mInitUserDeckMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(mInitUserDeckMapper.fromId(null)).isNull();
    }
}
