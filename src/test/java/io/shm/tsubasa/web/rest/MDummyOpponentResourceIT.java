package io.shm.tsubasa.web.rest;

import io.shm.tsubasa.TsubasaApp;
import io.shm.tsubasa.domain.MDummyOpponent;
import io.shm.tsubasa.domain.MNpcDeck;
import io.shm.tsubasa.repository.MDummyOpponentRepository;
import io.shm.tsubasa.service.MDummyOpponentService;
import io.shm.tsubasa.service.dto.MDummyOpponentDTO;
import io.shm.tsubasa.service.mapper.MDummyOpponentMapper;
import io.shm.tsubasa.web.rest.errors.ExceptionTranslator;
import io.shm.tsubasa.service.dto.MDummyOpponentCriteria;
import io.shm.tsubasa.service.MDummyOpponentQueryService;

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
 * Integration tests for the {@Link MDummyOpponentResource} REST controller.
 */
@SpringBootTest(classes = TsubasaApp.class)
public class MDummyOpponentResourceIT {

    private static final Integer DEFAULT_NPC_DECK_ID = 1;
    private static final Integer UPDATED_NPC_DECK_ID = 2;

    private static final Integer DEFAULT_TOTAL_PARAMETER = 1;
    private static final Integer UPDATED_TOTAL_PARAMETER = 2;

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final Integer DEFAULT_RANK = 1;
    private static final Integer UPDATED_RANK = 2;

    private static final Integer DEFAULT_EMBLEM_ID = 1;
    private static final Integer UPDATED_EMBLEM_ID = 2;

    private static final Integer DEFAULT_FP_UNIFORM_UP_ID = 1;
    private static final Integer UPDATED_FP_UNIFORM_UP_ID = 2;

    private static final String DEFAULT_FP_UNIFORM_UP_COLOR = "AAAAAAAAAA";
    private static final String UPDATED_FP_UNIFORM_UP_COLOR = "BBBBBBBBBB";

    private static final Integer DEFAULT_FP_UNIFORM_BOTTOM_ID = 1;
    private static final Integer UPDATED_FP_UNIFORM_BOTTOM_ID = 2;

    private static final String DEFAULT_FP_UNIFORM_BOTTOM_COLOR = "AAAAAAAAAA";
    private static final String UPDATED_FP_UNIFORM_BOTTOM_COLOR = "BBBBBBBBBB";

    private static final Integer DEFAULT_GK_UNIFORM_UP_ID = 1;
    private static final Integer UPDATED_GK_UNIFORM_UP_ID = 2;

    private static final String DEFAULT_GK_UNIFORM_UP_COLOR = "AAAAAAAAAA";
    private static final String UPDATED_GK_UNIFORM_UP_COLOR = "BBBBBBBBBB";

    private static final Integer DEFAULT_GK_UNIFORM_BOTTOM_ID = 1;
    private static final Integer UPDATED_GK_UNIFORM_BOTTOM_ID = 2;

    private static final String DEFAULT_GK_UNIFORM_BOTTOM_COLOR = "AAAAAAAAAA";
    private static final String UPDATED_GK_UNIFORM_BOTTOM_COLOR = "BBBBBBBBBB";

    @Autowired
    private MDummyOpponentRepository mDummyOpponentRepository;

    @Autowired
    private MDummyOpponentMapper mDummyOpponentMapper;

    @Autowired
    private MDummyOpponentService mDummyOpponentService;

    @Autowired
    private MDummyOpponentQueryService mDummyOpponentQueryService;

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

    private MockMvc restMDummyOpponentMockMvc;

    private MDummyOpponent mDummyOpponent;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final MDummyOpponentResource mDummyOpponentResource = new MDummyOpponentResource(mDummyOpponentService, mDummyOpponentQueryService);
        this.restMDummyOpponentMockMvc = MockMvcBuilders.standaloneSetup(mDummyOpponentResource)
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
    public static MDummyOpponent createEntity(EntityManager em) {
        MDummyOpponent mDummyOpponent = new MDummyOpponent()
            .npcDeckId(DEFAULT_NPC_DECK_ID)
            .totalParameter(DEFAULT_TOTAL_PARAMETER)
            .name(DEFAULT_NAME)
            .rank(DEFAULT_RANK)
            .emblemId(DEFAULT_EMBLEM_ID)
            .fpUniformUpId(DEFAULT_FP_UNIFORM_UP_ID)
            .fpUniformUpColor(DEFAULT_FP_UNIFORM_UP_COLOR)
            .fpUniformBottomId(DEFAULT_FP_UNIFORM_BOTTOM_ID)
            .fpUniformBottomColor(DEFAULT_FP_UNIFORM_BOTTOM_COLOR)
            .gkUniformUpId(DEFAULT_GK_UNIFORM_UP_ID)
            .gkUniformUpColor(DEFAULT_GK_UNIFORM_UP_COLOR)
            .gkUniformBottomId(DEFAULT_GK_UNIFORM_BOTTOM_ID)
            .gkUniformBottomColor(DEFAULT_GK_UNIFORM_BOTTOM_COLOR);
        // Add required entity
        MNpcDeck mNpcDeck;
        if (TestUtil.findAll(em, MNpcDeck.class).isEmpty()) {
            mNpcDeck = MNpcDeckResourceIT.createEntity(em);
            em.persist(mNpcDeck);
            em.flush();
        } else {
            mNpcDeck = TestUtil.findAll(em, MNpcDeck.class).get(0);
        }
        mDummyOpponent.setId(mNpcDeck);
        return mDummyOpponent;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static MDummyOpponent createUpdatedEntity(EntityManager em) {
        MDummyOpponent mDummyOpponent = new MDummyOpponent()
            .npcDeckId(UPDATED_NPC_DECK_ID)
            .totalParameter(UPDATED_TOTAL_PARAMETER)
            .name(UPDATED_NAME)
            .rank(UPDATED_RANK)
            .emblemId(UPDATED_EMBLEM_ID)
            .fpUniformUpId(UPDATED_FP_UNIFORM_UP_ID)
            .fpUniformUpColor(UPDATED_FP_UNIFORM_UP_COLOR)
            .fpUniformBottomId(UPDATED_FP_UNIFORM_BOTTOM_ID)
            .fpUniformBottomColor(UPDATED_FP_UNIFORM_BOTTOM_COLOR)
            .gkUniformUpId(UPDATED_GK_UNIFORM_UP_ID)
            .gkUniformUpColor(UPDATED_GK_UNIFORM_UP_COLOR)
            .gkUniformBottomId(UPDATED_GK_UNIFORM_BOTTOM_ID)
            .gkUniformBottomColor(UPDATED_GK_UNIFORM_BOTTOM_COLOR);
        // Add required entity
        MNpcDeck mNpcDeck;
        if (TestUtil.findAll(em, MNpcDeck.class).isEmpty()) {
            mNpcDeck = MNpcDeckResourceIT.createUpdatedEntity(em);
            em.persist(mNpcDeck);
            em.flush();
        } else {
            mNpcDeck = TestUtil.findAll(em, MNpcDeck.class).get(0);
        }
        mDummyOpponent.setId(mNpcDeck);
        return mDummyOpponent;
    }

    @BeforeEach
    public void initTest() {
        mDummyOpponent = createEntity(em);
    }

    @Test
    @Transactional
    public void createMDummyOpponent() throws Exception {
        int databaseSizeBeforeCreate = mDummyOpponentRepository.findAll().size();

        // Create the MDummyOpponent
        MDummyOpponentDTO mDummyOpponentDTO = mDummyOpponentMapper.toDto(mDummyOpponent);
        restMDummyOpponentMockMvc.perform(post("/api/m-dummy-opponents")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mDummyOpponentDTO)))
            .andExpect(status().isCreated());

        // Validate the MDummyOpponent in the database
        List<MDummyOpponent> mDummyOpponentList = mDummyOpponentRepository.findAll();
        assertThat(mDummyOpponentList).hasSize(databaseSizeBeforeCreate + 1);
        MDummyOpponent testMDummyOpponent = mDummyOpponentList.get(mDummyOpponentList.size() - 1);
        assertThat(testMDummyOpponent.getNpcDeckId()).isEqualTo(DEFAULT_NPC_DECK_ID);
        assertThat(testMDummyOpponent.getTotalParameter()).isEqualTo(DEFAULT_TOTAL_PARAMETER);
        assertThat(testMDummyOpponent.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testMDummyOpponent.getRank()).isEqualTo(DEFAULT_RANK);
        assertThat(testMDummyOpponent.getEmblemId()).isEqualTo(DEFAULT_EMBLEM_ID);
        assertThat(testMDummyOpponent.getFpUniformUpId()).isEqualTo(DEFAULT_FP_UNIFORM_UP_ID);
        assertThat(testMDummyOpponent.getFpUniformUpColor()).isEqualTo(DEFAULT_FP_UNIFORM_UP_COLOR);
        assertThat(testMDummyOpponent.getFpUniformBottomId()).isEqualTo(DEFAULT_FP_UNIFORM_BOTTOM_ID);
        assertThat(testMDummyOpponent.getFpUniformBottomColor()).isEqualTo(DEFAULT_FP_UNIFORM_BOTTOM_COLOR);
        assertThat(testMDummyOpponent.getGkUniformUpId()).isEqualTo(DEFAULT_GK_UNIFORM_UP_ID);
        assertThat(testMDummyOpponent.getGkUniformUpColor()).isEqualTo(DEFAULT_GK_UNIFORM_UP_COLOR);
        assertThat(testMDummyOpponent.getGkUniformBottomId()).isEqualTo(DEFAULT_GK_UNIFORM_BOTTOM_ID);
        assertThat(testMDummyOpponent.getGkUniformBottomColor()).isEqualTo(DEFAULT_GK_UNIFORM_BOTTOM_COLOR);
    }

    @Test
    @Transactional
    public void createMDummyOpponentWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = mDummyOpponentRepository.findAll().size();

        // Create the MDummyOpponent with an existing ID
        mDummyOpponent.setId(1L);
        MDummyOpponentDTO mDummyOpponentDTO = mDummyOpponentMapper.toDto(mDummyOpponent);

        // An entity with an existing ID cannot be created, so this API call must fail
        restMDummyOpponentMockMvc.perform(post("/api/m-dummy-opponents")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mDummyOpponentDTO)))
            .andExpect(status().isBadRequest());

        // Validate the MDummyOpponent in the database
        List<MDummyOpponent> mDummyOpponentList = mDummyOpponentRepository.findAll();
        assertThat(mDummyOpponentList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkNpcDeckIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = mDummyOpponentRepository.findAll().size();
        // set the field null
        mDummyOpponent.setNpcDeckId(null);

        // Create the MDummyOpponent, which fails.
        MDummyOpponentDTO mDummyOpponentDTO = mDummyOpponentMapper.toDto(mDummyOpponent);

        restMDummyOpponentMockMvc.perform(post("/api/m-dummy-opponents")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mDummyOpponentDTO)))
            .andExpect(status().isBadRequest());

        List<MDummyOpponent> mDummyOpponentList = mDummyOpponentRepository.findAll();
        assertThat(mDummyOpponentList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkTotalParameterIsRequired() throws Exception {
        int databaseSizeBeforeTest = mDummyOpponentRepository.findAll().size();
        // set the field null
        mDummyOpponent.setTotalParameter(null);

        // Create the MDummyOpponent, which fails.
        MDummyOpponentDTO mDummyOpponentDTO = mDummyOpponentMapper.toDto(mDummyOpponent);

        restMDummyOpponentMockMvc.perform(post("/api/m-dummy-opponents")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mDummyOpponentDTO)))
            .andExpect(status().isBadRequest());

        List<MDummyOpponent> mDummyOpponentList = mDummyOpponentRepository.findAll();
        assertThat(mDummyOpponentList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkRankIsRequired() throws Exception {
        int databaseSizeBeforeTest = mDummyOpponentRepository.findAll().size();
        // set the field null
        mDummyOpponent.setRank(null);

        // Create the MDummyOpponent, which fails.
        MDummyOpponentDTO mDummyOpponentDTO = mDummyOpponentMapper.toDto(mDummyOpponent);

        restMDummyOpponentMockMvc.perform(post("/api/m-dummy-opponents")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mDummyOpponentDTO)))
            .andExpect(status().isBadRequest());

        List<MDummyOpponent> mDummyOpponentList = mDummyOpponentRepository.findAll();
        assertThat(mDummyOpponentList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkEmblemIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = mDummyOpponentRepository.findAll().size();
        // set the field null
        mDummyOpponent.setEmblemId(null);

        // Create the MDummyOpponent, which fails.
        MDummyOpponentDTO mDummyOpponentDTO = mDummyOpponentMapper.toDto(mDummyOpponent);

        restMDummyOpponentMockMvc.perform(post("/api/m-dummy-opponents")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mDummyOpponentDTO)))
            .andExpect(status().isBadRequest());

        List<MDummyOpponent> mDummyOpponentList = mDummyOpponentRepository.findAll();
        assertThat(mDummyOpponentList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkFpUniformUpIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = mDummyOpponentRepository.findAll().size();
        // set the field null
        mDummyOpponent.setFpUniformUpId(null);

        // Create the MDummyOpponent, which fails.
        MDummyOpponentDTO mDummyOpponentDTO = mDummyOpponentMapper.toDto(mDummyOpponent);

        restMDummyOpponentMockMvc.perform(post("/api/m-dummy-opponents")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mDummyOpponentDTO)))
            .andExpect(status().isBadRequest());

        List<MDummyOpponent> mDummyOpponentList = mDummyOpponentRepository.findAll();
        assertThat(mDummyOpponentList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkFpUniformBottomIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = mDummyOpponentRepository.findAll().size();
        // set the field null
        mDummyOpponent.setFpUniformBottomId(null);

        // Create the MDummyOpponent, which fails.
        MDummyOpponentDTO mDummyOpponentDTO = mDummyOpponentMapper.toDto(mDummyOpponent);

        restMDummyOpponentMockMvc.perform(post("/api/m-dummy-opponents")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mDummyOpponentDTO)))
            .andExpect(status().isBadRequest());

        List<MDummyOpponent> mDummyOpponentList = mDummyOpponentRepository.findAll();
        assertThat(mDummyOpponentList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkGkUniformUpIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = mDummyOpponentRepository.findAll().size();
        // set the field null
        mDummyOpponent.setGkUniformUpId(null);

        // Create the MDummyOpponent, which fails.
        MDummyOpponentDTO mDummyOpponentDTO = mDummyOpponentMapper.toDto(mDummyOpponent);

        restMDummyOpponentMockMvc.perform(post("/api/m-dummy-opponents")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mDummyOpponentDTO)))
            .andExpect(status().isBadRequest());

        List<MDummyOpponent> mDummyOpponentList = mDummyOpponentRepository.findAll();
        assertThat(mDummyOpponentList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkGkUniformBottomIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = mDummyOpponentRepository.findAll().size();
        // set the field null
        mDummyOpponent.setGkUniformBottomId(null);

        // Create the MDummyOpponent, which fails.
        MDummyOpponentDTO mDummyOpponentDTO = mDummyOpponentMapper.toDto(mDummyOpponent);

        restMDummyOpponentMockMvc.perform(post("/api/m-dummy-opponents")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mDummyOpponentDTO)))
            .andExpect(status().isBadRequest());

        List<MDummyOpponent> mDummyOpponentList = mDummyOpponentRepository.findAll();
        assertThat(mDummyOpponentList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllMDummyOpponents() throws Exception {
        // Initialize the database
        mDummyOpponentRepository.saveAndFlush(mDummyOpponent);

        // Get all the mDummyOpponentList
        restMDummyOpponentMockMvc.perform(get("/api/m-dummy-opponents?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(mDummyOpponent.getId().intValue())))
            .andExpect(jsonPath("$.[*].npcDeckId").value(hasItem(DEFAULT_NPC_DECK_ID)))
            .andExpect(jsonPath("$.[*].totalParameter").value(hasItem(DEFAULT_TOTAL_PARAMETER)))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME.toString())))
            .andExpect(jsonPath("$.[*].rank").value(hasItem(DEFAULT_RANK)))
            .andExpect(jsonPath("$.[*].emblemId").value(hasItem(DEFAULT_EMBLEM_ID)))
            .andExpect(jsonPath("$.[*].fpUniformUpId").value(hasItem(DEFAULT_FP_UNIFORM_UP_ID)))
            .andExpect(jsonPath("$.[*].fpUniformUpColor").value(hasItem(DEFAULT_FP_UNIFORM_UP_COLOR.toString())))
            .andExpect(jsonPath("$.[*].fpUniformBottomId").value(hasItem(DEFAULT_FP_UNIFORM_BOTTOM_ID)))
            .andExpect(jsonPath("$.[*].fpUniformBottomColor").value(hasItem(DEFAULT_FP_UNIFORM_BOTTOM_COLOR.toString())))
            .andExpect(jsonPath("$.[*].gkUniformUpId").value(hasItem(DEFAULT_GK_UNIFORM_UP_ID)))
            .andExpect(jsonPath("$.[*].gkUniformUpColor").value(hasItem(DEFAULT_GK_UNIFORM_UP_COLOR.toString())))
            .andExpect(jsonPath("$.[*].gkUniformBottomId").value(hasItem(DEFAULT_GK_UNIFORM_BOTTOM_ID)))
            .andExpect(jsonPath("$.[*].gkUniformBottomColor").value(hasItem(DEFAULT_GK_UNIFORM_BOTTOM_COLOR.toString())));
    }
    
    @Test
    @Transactional
    public void getMDummyOpponent() throws Exception {
        // Initialize the database
        mDummyOpponentRepository.saveAndFlush(mDummyOpponent);

        // Get the mDummyOpponent
        restMDummyOpponentMockMvc.perform(get("/api/m-dummy-opponents/{id}", mDummyOpponent.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(mDummyOpponent.getId().intValue()))
            .andExpect(jsonPath("$.npcDeckId").value(DEFAULT_NPC_DECK_ID))
            .andExpect(jsonPath("$.totalParameter").value(DEFAULT_TOTAL_PARAMETER))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME.toString()))
            .andExpect(jsonPath("$.rank").value(DEFAULT_RANK))
            .andExpect(jsonPath("$.emblemId").value(DEFAULT_EMBLEM_ID))
            .andExpect(jsonPath("$.fpUniformUpId").value(DEFAULT_FP_UNIFORM_UP_ID))
            .andExpect(jsonPath("$.fpUniformUpColor").value(DEFAULT_FP_UNIFORM_UP_COLOR.toString()))
            .andExpect(jsonPath("$.fpUniformBottomId").value(DEFAULT_FP_UNIFORM_BOTTOM_ID))
            .andExpect(jsonPath("$.fpUniformBottomColor").value(DEFAULT_FP_UNIFORM_BOTTOM_COLOR.toString()))
            .andExpect(jsonPath("$.gkUniformUpId").value(DEFAULT_GK_UNIFORM_UP_ID))
            .andExpect(jsonPath("$.gkUniformUpColor").value(DEFAULT_GK_UNIFORM_UP_COLOR.toString()))
            .andExpect(jsonPath("$.gkUniformBottomId").value(DEFAULT_GK_UNIFORM_BOTTOM_ID))
            .andExpect(jsonPath("$.gkUniformBottomColor").value(DEFAULT_GK_UNIFORM_BOTTOM_COLOR.toString()));
    }

    @Test
    @Transactional
    public void getAllMDummyOpponentsByNpcDeckIdIsEqualToSomething() throws Exception {
        // Initialize the database
        mDummyOpponentRepository.saveAndFlush(mDummyOpponent);

        // Get all the mDummyOpponentList where npcDeckId equals to DEFAULT_NPC_DECK_ID
        defaultMDummyOpponentShouldBeFound("npcDeckId.equals=" + DEFAULT_NPC_DECK_ID);

        // Get all the mDummyOpponentList where npcDeckId equals to UPDATED_NPC_DECK_ID
        defaultMDummyOpponentShouldNotBeFound("npcDeckId.equals=" + UPDATED_NPC_DECK_ID);
    }

    @Test
    @Transactional
    public void getAllMDummyOpponentsByNpcDeckIdIsInShouldWork() throws Exception {
        // Initialize the database
        mDummyOpponentRepository.saveAndFlush(mDummyOpponent);

        // Get all the mDummyOpponentList where npcDeckId in DEFAULT_NPC_DECK_ID or UPDATED_NPC_DECK_ID
        defaultMDummyOpponentShouldBeFound("npcDeckId.in=" + DEFAULT_NPC_DECK_ID + "," + UPDATED_NPC_DECK_ID);

        // Get all the mDummyOpponentList where npcDeckId equals to UPDATED_NPC_DECK_ID
        defaultMDummyOpponentShouldNotBeFound("npcDeckId.in=" + UPDATED_NPC_DECK_ID);
    }

    @Test
    @Transactional
    public void getAllMDummyOpponentsByNpcDeckIdIsNullOrNotNull() throws Exception {
        // Initialize the database
        mDummyOpponentRepository.saveAndFlush(mDummyOpponent);

        // Get all the mDummyOpponentList where npcDeckId is not null
        defaultMDummyOpponentShouldBeFound("npcDeckId.specified=true");

        // Get all the mDummyOpponentList where npcDeckId is null
        defaultMDummyOpponentShouldNotBeFound("npcDeckId.specified=false");
    }

    @Test
    @Transactional
    public void getAllMDummyOpponentsByNpcDeckIdIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mDummyOpponentRepository.saveAndFlush(mDummyOpponent);

        // Get all the mDummyOpponentList where npcDeckId greater than or equals to DEFAULT_NPC_DECK_ID
        defaultMDummyOpponentShouldBeFound("npcDeckId.greaterOrEqualThan=" + DEFAULT_NPC_DECK_ID);

        // Get all the mDummyOpponentList where npcDeckId greater than or equals to UPDATED_NPC_DECK_ID
        defaultMDummyOpponentShouldNotBeFound("npcDeckId.greaterOrEqualThan=" + UPDATED_NPC_DECK_ID);
    }

    @Test
    @Transactional
    public void getAllMDummyOpponentsByNpcDeckIdIsLessThanSomething() throws Exception {
        // Initialize the database
        mDummyOpponentRepository.saveAndFlush(mDummyOpponent);

        // Get all the mDummyOpponentList where npcDeckId less than or equals to DEFAULT_NPC_DECK_ID
        defaultMDummyOpponentShouldNotBeFound("npcDeckId.lessThan=" + DEFAULT_NPC_DECK_ID);

        // Get all the mDummyOpponentList where npcDeckId less than or equals to UPDATED_NPC_DECK_ID
        defaultMDummyOpponentShouldBeFound("npcDeckId.lessThan=" + UPDATED_NPC_DECK_ID);
    }


    @Test
    @Transactional
    public void getAllMDummyOpponentsByTotalParameterIsEqualToSomething() throws Exception {
        // Initialize the database
        mDummyOpponentRepository.saveAndFlush(mDummyOpponent);

        // Get all the mDummyOpponentList where totalParameter equals to DEFAULT_TOTAL_PARAMETER
        defaultMDummyOpponentShouldBeFound("totalParameter.equals=" + DEFAULT_TOTAL_PARAMETER);

        // Get all the mDummyOpponentList where totalParameter equals to UPDATED_TOTAL_PARAMETER
        defaultMDummyOpponentShouldNotBeFound("totalParameter.equals=" + UPDATED_TOTAL_PARAMETER);
    }

    @Test
    @Transactional
    public void getAllMDummyOpponentsByTotalParameterIsInShouldWork() throws Exception {
        // Initialize the database
        mDummyOpponentRepository.saveAndFlush(mDummyOpponent);

        // Get all the mDummyOpponentList where totalParameter in DEFAULT_TOTAL_PARAMETER or UPDATED_TOTAL_PARAMETER
        defaultMDummyOpponentShouldBeFound("totalParameter.in=" + DEFAULT_TOTAL_PARAMETER + "," + UPDATED_TOTAL_PARAMETER);

        // Get all the mDummyOpponentList where totalParameter equals to UPDATED_TOTAL_PARAMETER
        defaultMDummyOpponentShouldNotBeFound("totalParameter.in=" + UPDATED_TOTAL_PARAMETER);
    }

    @Test
    @Transactional
    public void getAllMDummyOpponentsByTotalParameterIsNullOrNotNull() throws Exception {
        // Initialize the database
        mDummyOpponentRepository.saveAndFlush(mDummyOpponent);

        // Get all the mDummyOpponentList where totalParameter is not null
        defaultMDummyOpponentShouldBeFound("totalParameter.specified=true");

        // Get all the mDummyOpponentList where totalParameter is null
        defaultMDummyOpponentShouldNotBeFound("totalParameter.specified=false");
    }

    @Test
    @Transactional
    public void getAllMDummyOpponentsByTotalParameterIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mDummyOpponentRepository.saveAndFlush(mDummyOpponent);

        // Get all the mDummyOpponentList where totalParameter greater than or equals to DEFAULT_TOTAL_PARAMETER
        defaultMDummyOpponentShouldBeFound("totalParameter.greaterOrEqualThan=" + DEFAULT_TOTAL_PARAMETER);

        // Get all the mDummyOpponentList where totalParameter greater than or equals to UPDATED_TOTAL_PARAMETER
        defaultMDummyOpponentShouldNotBeFound("totalParameter.greaterOrEqualThan=" + UPDATED_TOTAL_PARAMETER);
    }

    @Test
    @Transactional
    public void getAllMDummyOpponentsByTotalParameterIsLessThanSomething() throws Exception {
        // Initialize the database
        mDummyOpponentRepository.saveAndFlush(mDummyOpponent);

        // Get all the mDummyOpponentList where totalParameter less than or equals to DEFAULT_TOTAL_PARAMETER
        defaultMDummyOpponentShouldNotBeFound("totalParameter.lessThan=" + DEFAULT_TOTAL_PARAMETER);

        // Get all the mDummyOpponentList where totalParameter less than or equals to UPDATED_TOTAL_PARAMETER
        defaultMDummyOpponentShouldBeFound("totalParameter.lessThan=" + UPDATED_TOTAL_PARAMETER);
    }


    @Test
    @Transactional
    public void getAllMDummyOpponentsByRankIsEqualToSomething() throws Exception {
        // Initialize the database
        mDummyOpponentRepository.saveAndFlush(mDummyOpponent);

        // Get all the mDummyOpponentList where rank equals to DEFAULT_RANK
        defaultMDummyOpponentShouldBeFound("rank.equals=" + DEFAULT_RANK);

        // Get all the mDummyOpponentList where rank equals to UPDATED_RANK
        defaultMDummyOpponentShouldNotBeFound("rank.equals=" + UPDATED_RANK);
    }

    @Test
    @Transactional
    public void getAllMDummyOpponentsByRankIsInShouldWork() throws Exception {
        // Initialize the database
        mDummyOpponentRepository.saveAndFlush(mDummyOpponent);

        // Get all the mDummyOpponentList where rank in DEFAULT_RANK or UPDATED_RANK
        defaultMDummyOpponentShouldBeFound("rank.in=" + DEFAULT_RANK + "," + UPDATED_RANK);

        // Get all the mDummyOpponentList where rank equals to UPDATED_RANK
        defaultMDummyOpponentShouldNotBeFound("rank.in=" + UPDATED_RANK);
    }

    @Test
    @Transactional
    public void getAllMDummyOpponentsByRankIsNullOrNotNull() throws Exception {
        // Initialize the database
        mDummyOpponentRepository.saveAndFlush(mDummyOpponent);

        // Get all the mDummyOpponentList where rank is not null
        defaultMDummyOpponentShouldBeFound("rank.specified=true");

        // Get all the mDummyOpponentList where rank is null
        defaultMDummyOpponentShouldNotBeFound("rank.specified=false");
    }

    @Test
    @Transactional
    public void getAllMDummyOpponentsByRankIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mDummyOpponentRepository.saveAndFlush(mDummyOpponent);

        // Get all the mDummyOpponentList where rank greater than or equals to DEFAULT_RANK
        defaultMDummyOpponentShouldBeFound("rank.greaterOrEqualThan=" + DEFAULT_RANK);

        // Get all the mDummyOpponentList where rank greater than or equals to UPDATED_RANK
        defaultMDummyOpponentShouldNotBeFound("rank.greaterOrEqualThan=" + UPDATED_RANK);
    }

    @Test
    @Transactional
    public void getAllMDummyOpponentsByRankIsLessThanSomething() throws Exception {
        // Initialize the database
        mDummyOpponentRepository.saveAndFlush(mDummyOpponent);

        // Get all the mDummyOpponentList where rank less than or equals to DEFAULT_RANK
        defaultMDummyOpponentShouldNotBeFound("rank.lessThan=" + DEFAULT_RANK);

        // Get all the mDummyOpponentList where rank less than or equals to UPDATED_RANK
        defaultMDummyOpponentShouldBeFound("rank.lessThan=" + UPDATED_RANK);
    }


    @Test
    @Transactional
    public void getAllMDummyOpponentsByEmblemIdIsEqualToSomething() throws Exception {
        // Initialize the database
        mDummyOpponentRepository.saveAndFlush(mDummyOpponent);

        // Get all the mDummyOpponentList where emblemId equals to DEFAULT_EMBLEM_ID
        defaultMDummyOpponentShouldBeFound("emblemId.equals=" + DEFAULT_EMBLEM_ID);

        // Get all the mDummyOpponentList where emblemId equals to UPDATED_EMBLEM_ID
        defaultMDummyOpponentShouldNotBeFound("emblemId.equals=" + UPDATED_EMBLEM_ID);
    }

    @Test
    @Transactional
    public void getAllMDummyOpponentsByEmblemIdIsInShouldWork() throws Exception {
        // Initialize the database
        mDummyOpponentRepository.saveAndFlush(mDummyOpponent);

        // Get all the mDummyOpponentList where emblemId in DEFAULT_EMBLEM_ID or UPDATED_EMBLEM_ID
        defaultMDummyOpponentShouldBeFound("emblemId.in=" + DEFAULT_EMBLEM_ID + "," + UPDATED_EMBLEM_ID);

        // Get all the mDummyOpponentList where emblemId equals to UPDATED_EMBLEM_ID
        defaultMDummyOpponentShouldNotBeFound("emblemId.in=" + UPDATED_EMBLEM_ID);
    }

    @Test
    @Transactional
    public void getAllMDummyOpponentsByEmblemIdIsNullOrNotNull() throws Exception {
        // Initialize the database
        mDummyOpponentRepository.saveAndFlush(mDummyOpponent);

        // Get all the mDummyOpponentList where emblemId is not null
        defaultMDummyOpponentShouldBeFound("emblemId.specified=true");

        // Get all the mDummyOpponentList where emblemId is null
        defaultMDummyOpponentShouldNotBeFound("emblemId.specified=false");
    }

    @Test
    @Transactional
    public void getAllMDummyOpponentsByEmblemIdIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mDummyOpponentRepository.saveAndFlush(mDummyOpponent);

        // Get all the mDummyOpponentList where emblemId greater than or equals to DEFAULT_EMBLEM_ID
        defaultMDummyOpponentShouldBeFound("emblemId.greaterOrEqualThan=" + DEFAULT_EMBLEM_ID);

        // Get all the mDummyOpponentList where emblemId greater than or equals to UPDATED_EMBLEM_ID
        defaultMDummyOpponentShouldNotBeFound("emblemId.greaterOrEqualThan=" + UPDATED_EMBLEM_ID);
    }

    @Test
    @Transactional
    public void getAllMDummyOpponentsByEmblemIdIsLessThanSomething() throws Exception {
        // Initialize the database
        mDummyOpponentRepository.saveAndFlush(mDummyOpponent);

        // Get all the mDummyOpponentList where emblemId less than or equals to DEFAULT_EMBLEM_ID
        defaultMDummyOpponentShouldNotBeFound("emblemId.lessThan=" + DEFAULT_EMBLEM_ID);

        // Get all the mDummyOpponentList where emblemId less than or equals to UPDATED_EMBLEM_ID
        defaultMDummyOpponentShouldBeFound("emblemId.lessThan=" + UPDATED_EMBLEM_ID);
    }


    @Test
    @Transactional
    public void getAllMDummyOpponentsByFpUniformUpIdIsEqualToSomething() throws Exception {
        // Initialize the database
        mDummyOpponentRepository.saveAndFlush(mDummyOpponent);

        // Get all the mDummyOpponentList where fpUniformUpId equals to DEFAULT_FP_UNIFORM_UP_ID
        defaultMDummyOpponentShouldBeFound("fpUniformUpId.equals=" + DEFAULT_FP_UNIFORM_UP_ID);

        // Get all the mDummyOpponentList where fpUniformUpId equals to UPDATED_FP_UNIFORM_UP_ID
        defaultMDummyOpponentShouldNotBeFound("fpUniformUpId.equals=" + UPDATED_FP_UNIFORM_UP_ID);
    }

    @Test
    @Transactional
    public void getAllMDummyOpponentsByFpUniformUpIdIsInShouldWork() throws Exception {
        // Initialize the database
        mDummyOpponentRepository.saveAndFlush(mDummyOpponent);

        // Get all the mDummyOpponentList where fpUniformUpId in DEFAULT_FP_UNIFORM_UP_ID or UPDATED_FP_UNIFORM_UP_ID
        defaultMDummyOpponentShouldBeFound("fpUniformUpId.in=" + DEFAULT_FP_UNIFORM_UP_ID + "," + UPDATED_FP_UNIFORM_UP_ID);

        // Get all the mDummyOpponentList where fpUniformUpId equals to UPDATED_FP_UNIFORM_UP_ID
        defaultMDummyOpponentShouldNotBeFound("fpUniformUpId.in=" + UPDATED_FP_UNIFORM_UP_ID);
    }

    @Test
    @Transactional
    public void getAllMDummyOpponentsByFpUniformUpIdIsNullOrNotNull() throws Exception {
        // Initialize the database
        mDummyOpponentRepository.saveAndFlush(mDummyOpponent);

        // Get all the mDummyOpponentList where fpUniformUpId is not null
        defaultMDummyOpponentShouldBeFound("fpUniformUpId.specified=true");

        // Get all the mDummyOpponentList where fpUniformUpId is null
        defaultMDummyOpponentShouldNotBeFound("fpUniformUpId.specified=false");
    }

    @Test
    @Transactional
    public void getAllMDummyOpponentsByFpUniformUpIdIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mDummyOpponentRepository.saveAndFlush(mDummyOpponent);

        // Get all the mDummyOpponentList where fpUniformUpId greater than or equals to DEFAULT_FP_UNIFORM_UP_ID
        defaultMDummyOpponentShouldBeFound("fpUniformUpId.greaterOrEqualThan=" + DEFAULT_FP_UNIFORM_UP_ID);

        // Get all the mDummyOpponentList where fpUniformUpId greater than or equals to UPDATED_FP_UNIFORM_UP_ID
        defaultMDummyOpponentShouldNotBeFound("fpUniformUpId.greaterOrEqualThan=" + UPDATED_FP_UNIFORM_UP_ID);
    }

    @Test
    @Transactional
    public void getAllMDummyOpponentsByFpUniformUpIdIsLessThanSomething() throws Exception {
        // Initialize the database
        mDummyOpponentRepository.saveAndFlush(mDummyOpponent);

        // Get all the mDummyOpponentList where fpUniformUpId less than or equals to DEFAULT_FP_UNIFORM_UP_ID
        defaultMDummyOpponentShouldNotBeFound("fpUniformUpId.lessThan=" + DEFAULT_FP_UNIFORM_UP_ID);

        // Get all the mDummyOpponentList where fpUniformUpId less than or equals to UPDATED_FP_UNIFORM_UP_ID
        defaultMDummyOpponentShouldBeFound("fpUniformUpId.lessThan=" + UPDATED_FP_UNIFORM_UP_ID);
    }


    @Test
    @Transactional
    public void getAllMDummyOpponentsByFpUniformBottomIdIsEqualToSomething() throws Exception {
        // Initialize the database
        mDummyOpponentRepository.saveAndFlush(mDummyOpponent);

        // Get all the mDummyOpponentList where fpUniformBottomId equals to DEFAULT_FP_UNIFORM_BOTTOM_ID
        defaultMDummyOpponentShouldBeFound("fpUniformBottomId.equals=" + DEFAULT_FP_UNIFORM_BOTTOM_ID);

        // Get all the mDummyOpponentList where fpUniformBottomId equals to UPDATED_FP_UNIFORM_BOTTOM_ID
        defaultMDummyOpponentShouldNotBeFound("fpUniformBottomId.equals=" + UPDATED_FP_UNIFORM_BOTTOM_ID);
    }

    @Test
    @Transactional
    public void getAllMDummyOpponentsByFpUniformBottomIdIsInShouldWork() throws Exception {
        // Initialize the database
        mDummyOpponentRepository.saveAndFlush(mDummyOpponent);

        // Get all the mDummyOpponentList where fpUniformBottomId in DEFAULT_FP_UNIFORM_BOTTOM_ID or UPDATED_FP_UNIFORM_BOTTOM_ID
        defaultMDummyOpponentShouldBeFound("fpUniformBottomId.in=" + DEFAULT_FP_UNIFORM_BOTTOM_ID + "," + UPDATED_FP_UNIFORM_BOTTOM_ID);

        // Get all the mDummyOpponentList where fpUniformBottomId equals to UPDATED_FP_UNIFORM_BOTTOM_ID
        defaultMDummyOpponentShouldNotBeFound("fpUniformBottomId.in=" + UPDATED_FP_UNIFORM_BOTTOM_ID);
    }

    @Test
    @Transactional
    public void getAllMDummyOpponentsByFpUniformBottomIdIsNullOrNotNull() throws Exception {
        // Initialize the database
        mDummyOpponentRepository.saveAndFlush(mDummyOpponent);

        // Get all the mDummyOpponentList where fpUniformBottomId is not null
        defaultMDummyOpponentShouldBeFound("fpUniformBottomId.specified=true");

        // Get all the mDummyOpponentList where fpUniformBottomId is null
        defaultMDummyOpponentShouldNotBeFound("fpUniformBottomId.specified=false");
    }

    @Test
    @Transactional
    public void getAllMDummyOpponentsByFpUniformBottomIdIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mDummyOpponentRepository.saveAndFlush(mDummyOpponent);

        // Get all the mDummyOpponentList where fpUniformBottomId greater than or equals to DEFAULT_FP_UNIFORM_BOTTOM_ID
        defaultMDummyOpponentShouldBeFound("fpUniformBottomId.greaterOrEqualThan=" + DEFAULT_FP_UNIFORM_BOTTOM_ID);

        // Get all the mDummyOpponentList where fpUniformBottomId greater than or equals to UPDATED_FP_UNIFORM_BOTTOM_ID
        defaultMDummyOpponentShouldNotBeFound("fpUniformBottomId.greaterOrEqualThan=" + UPDATED_FP_UNIFORM_BOTTOM_ID);
    }

    @Test
    @Transactional
    public void getAllMDummyOpponentsByFpUniformBottomIdIsLessThanSomething() throws Exception {
        // Initialize the database
        mDummyOpponentRepository.saveAndFlush(mDummyOpponent);

        // Get all the mDummyOpponentList where fpUniformBottomId less than or equals to DEFAULT_FP_UNIFORM_BOTTOM_ID
        defaultMDummyOpponentShouldNotBeFound("fpUniformBottomId.lessThan=" + DEFAULT_FP_UNIFORM_BOTTOM_ID);

        // Get all the mDummyOpponentList where fpUniformBottomId less than or equals to UPDATED_FP_UNIFORM_BOTTOM_ID
        defaultMDummyOpponentShouldBeFound("fpUniformBottomId.lessThan=" + UPDATED_FP_UNIFORM_BOTTOM_ID);
    }


    @Test
    @Transactional
    public void getAllMDummyOpponentsByGkUniformUpIdIsEqualToSomething() throws Exception {
        // Initialize the database
        mDummyOpponentRepository.saveAndFlush(mDummyOpponent);

        // Get all the mDummyOpponentList where gkUniformUpId equals to DEFAULT_GK_UNIFORM_UP_ID
        defaultMDummyOpponentShouldBeFound("gkUniformUpId.equals=" + DEFAULT_GK_UNIFORM_UP_ID);

        // Get all the mDummyOpponentList where gkUniformUpId equals to UPDATED_GK_UNIFORM_UP_ID
        defaultMDummyOpponentShouldNotBeFound("gkUniformUpId.equals=" + UPDATED_GK_UNIFORM_UP_ID);
    }

    @Test
    @Transactional
    public void getAllMDummyOpponentsByGkUniformUpIdIsInShouldWork() throws Exception {
        // Initialize the database
        mDummyOpponentRepository.saveAndFlush(mDummyOpponent);

        // Get all the mDummyOpponentList where gkUniformUpId in DEFAULT_GK_UNIFORM_UP_ID or UPDATED_GK_UNIFORM_UP_ID
        defaultMDummyOpponentShouldBeFound("gkUniformUpId.in=" + DEFAULT_GK_UNIFORM_UP_ID + "," + UPDATED_GK_UNIFORM_UP_ID);

        // Get all the mDummyOpponentList where gkUniformUpId equals to UPDATED_GK_UNIFORM_UP_ID
        defaultMDummyOpponentShouldNotBeFound("gkUniformUpId.in=" + UPDATED_GK_UNIFORM_UP_ID);
    }

    @Test
    @Transactional
    public void getAllMDummyOpponentsByGkUniformUpIdIsNullOrNotNull() throws Exception {
        // Initialize the database
        mDummyOpponentRepository.saveAndFlush(mDummyOpponent);

        // Get all the mDummyOpponentList where gkUniformUpId is not null
        defaultMDummyOpponentShouldBeFound("gkUniformUpId.specified=true");

        // Get all the mDummyOpponentList where gkUniformUpId is null
        defaultMDummyOpponentShouldNotBeFound("gkUniformUpId.specified=false");
    }

    @Test
    @Transactional
    public void getAllMDummyOpponentsByGkUniformUpIdIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mDummyOpponentRepository.saveAndFlush(mDummyOpponent);

        // Get all the mDummyOpponentList where gkUniformUpId greater than or equals to DEFAULT_GK_UNIFORM_UP_ID
        defaultMDummyOpponentShouldBeFound("gkUniformUpId.greaterOrEqualThan=" + DEFAULT_GK_UNIFORM_UP_ID);

        // Get all the mDummyOpponentList where gkUniformUpId greater than or equals to UPDATED_GK_UNIFORM_UP_ID
        defaultMDummyOpponentShouldNotBeFound("gkUniformUpId.greaterOrEqualThan=" + UPDATED_GK_UNIFORM_UP_ID);
    }

    @Test
    @Transactional
    public void getAllMDummyOpponentsByGkUniformUpIdIsLessThanSomething() throws Exception {
        // Initialize the database
        mDummyOpponentRepository.saveAndFlush(mDummyOpponent);

        // Get all the mDummyOpponentList where gkUniformUpId less than or equals to DEFAULT_GK_UNIFORM_UP_ID
        defaultMDummyOpponentShouldNotBeFound("gkUniformUpId.lessThan=" + DEFAULT_GK_UNIFORM_UP_ID);

        // Get all the mDummyOpponentList where gkUniformUpId less than or equals to UPDATED_GK_UNIFORM_UP_ID
        defaultMDummyOpponentShouldBeFound("gkUniformUpId.lessThan=" + UPDATED_GK_UNIFORM_UP_ID);
    }


    @Test
    @Transactional
    public void getAllMDummyOpponentsByGkUniformBottomIdIsEqualToSomething() throws Exception {
        // Initialize the database
        mDummyOpponentRepository.saveAndFlush(mDummyOpponent);

        // Get all the mDummyOpponentList where gkUniformBottomId equals to DEFAULT_GK_UNIFORM_BOTTOM_ID
        defaultMDummyOpponentShouldBeFound("gkUniformBottomId.equals=" + DEFAULT_GK_UNIFORM_BOTTOM_ID);

        // Get all the mDummyOpponentList where gkUniformBottomId equals to UPDATED_GK_UNIFORM_BOTTOM_ID
        defaultMDummyOpponentShouldNotBeFound("gkUniformBottomId.equals=" + UPDATED_GK_UNIFORM_BOTTOM_ID);
    }

    @Test
    @Transactional
    public void getAllMDummyOpponentsByGkUniformBottomIdIsInShouldWork() throws Exception {
        // Initialize the database
        mDummyOpponentRepository.saveAndFlush(mDummyOpponent);

        // Get all the mDummyOpponentList where gkUniformBottomId in DEFAULT_GK_UNIFORM_BOTTOM_ID or UPDATED_GK_UNIFORM_BOTTOM_ID
        defaultMDummyOpponentShouldBeFound("gkUniformBottomId.in=" + DEFAULT_GK_UNIFORM_BOTTOM_ID + "," + UPDATED_GK_UNIFORM_BOTTOM_ID);

        // Get all the mDummyOpponentList where gkUniformBottomId equals to UPDATED_GK_UNIFORM_BOTTOM_ID
        defaultMDummyOpponentShouldNotBeFound("gkUniformBottomId.in=" + UPDATED_GK_UNIFORM_BOTTOM_ID);
    }

    @Test
    @Transactional
    public void getAllMDummyOpponentsByGkUniformBottomIdIsNullOrNotNull() throws Exception {
        // Initialize the database
        mDummyOpponentRepository.saveAndFlush(mDummyOpponent);

        // Get all the mDummyOpponentList where gkUniformBottomId is not null
        defaultMDummyOpponentShouldBeFound("gkUniformBottomId.specified=true");

        // Get all the mDummyOpponentList where gkUniformBottomId is null
        defaultMDummyOpponentShouldNotBeFound("gkUniformBottomId.specified=false");
    }

    @Test
    @Transactional
    public void getAllMDummyOpponentsByGkUniformBottomIdIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mDummyOpponentRepository.saveAndFlush(mDummyOpponent);

        // Get all the mDummyOpponentList where gkUniformBottomId greater than or equals to DEFAULT_GK_UNIFORM_BOTTOM_ID
        defaultMDummyOpponentShouldBeFound("gkUniformBottomId.greaterOrEqualThan=" + DEFAULT_GK_UNIFORM_BOTTOM_ID);

        // Get all the mDummyOpponentList where gkUniformBottomId greater than or equals to UPDATED_GK_UNIFORM_BOTTOM_ID
        defaultMDummyOpponentShouldNotBeFound("gkUniformBottomId.greaterOrEqualThan=" + UPDATED_GK_UNIFORM_BOTTOM_ID);
    }

    @Test
    @Transactional
    public void getAllMDummyOpponentsByGkUniformBottomIdIsLessThanSomething() throws Exception {
        // Initialize the database
        mDummyOpponentRepository.saveAndFlush(mDummyOpponent);

        // Get all the mDummyOpponentList where gkUniformBottomId less than or equals to DEFAULT_GK_UNIFORM_BOTTOM_ID
        defaultMDummyOpponentShouldNotBeFound("gkUniformBottomId.lessThan=" + DEFAULT_GK_UNIFORM_BOTTOM_ID);

        // Get all the mDummyOpponentList where gkUniformBottomId less than or equals to UPDATED_GK_UNIFORM_BOTTOM_ID
        defaultMDummyOpponentShouldBeFound("gkUniformBottomId.lessThan=" + UPDATED_GK_UNIFORM_BOTTOM_ID);
    }


    @Test
    @Transactional
    public void getAllMDummyOpponentsByIdIsEqualToSomething() throws Exception {
        // Get already existing entity
        MNpcDeck id = mDummyOpponent.getId();
        mDummyOpponentRepository.saveAndFlush(mDummyOpponent);
        Long idId = id.getId();

        // Get all the mDummyOpponentList where id equals to idId
        defaultMDummyOpponentShouldBeFound("idId.equals=" + idId);

        // Get all the mDummyOpponentList where id equals to idId + 1
        defaultMDummyOpponentShouldNotBeFound("idId.equals=" + (idId + 1));
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultMDummyOpponentShouldBeFound(String filter) throws Exception {
        restMDummyOpponentMockMvc.perform(get("/api/m-dummy-opponents?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(mDummyOpponent.getId().intValue())))
            .andExpect(jsonPath("$.[*].npcDeckId").value(hasItem(DEFAULT_NPC_DECK_ID)))
            .andExpect(jsonPath("$.[*].totalParameter").value(hasItem(DEFAULT_TOTAL_PARAMETER)))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME.toString())))
            .andExpect(jsonPath("$.[*].rank").value(hasItem(DEFAULT_RANK)))
            .andExpect(jsonPath("$.[*].emblemId").value(hasItem(DEFAULT_EMBLEM_ID)))
            .andExpect(jsonPath("$.[*].fpUniformUpId").value(hasItem(DEFAULT_FP_UNIFORM_UP_ID)))
            .andExpect(jsonPath("$.[*].fpUniformUpColor").value(hasItem(DEFAULT_FP_UNIFORM_UP_COLOR.toString())))
            .andExpect(jsonPath("$.[*].fpUniformBottomId").value(hasItem(DEFAULT_FP_UNIFORM_BOTTOM_ID)))
            .andExpect(jsonPath("$.[*].fpUniformBottomColor").value(hasItem(DEFAULT_FP_UNIFORM_BOTTOM_COLOR.toString())))
            .andExpect(jsonPath("$.[*].gkUniformUpId").value(hasItem(DEFAULT_GK_UNIFORM_UP_ID)))
            .andExpect(jsonPath("$.[*].gkUniformUpColor").value(hasItem(DEFAULT_GK_UNIFORM_UP_COLOR.toString())))
            .andExpect(jsonPath("$.[*].gkUniformBottomId").value(hasItem(DEFAULT_GK_UNIFORM_BOTTOM_ID)))
            .andExpect(jsonPath("$.[*].gkUniformBottomColor").value(hasItem(DEFAULT_GK_UNIFORM_BOTTOM_COLOR.toString())));

        // Check, that the count call also returns 1
        restMDummyOpponentMockMvc.perform(get("/api/m-dummy-opponents/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultMDummyOpponentShouldNotBeFound(String filter) throws Exception {
        restMDummyOpponentMockMvc.perform(get("/api/m-dummy-opponents?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restMDummyOpponentMockMvc.perform(get("/api/m-dummy-opponents/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("0"));
    }


    @Test
    @Transactional
    public void getNonExistingMDummyOpponent() throws Exception {
        // Get the mDummyOpponent
        restMDummyOpponentMockMvc.perform(get("/api/m-dummy-opponents/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateMDummyOpponent() throws Exception {
        // Initialize the database
        mDummyOpponentRepository.saveAndFlush(mDummyOpponent);

        int databaseSizeBeforeUpdate = mDummyOpponentRepository.findAll().size();

        // Update the mDummyOpponent
        MDummyOpponent updatedMDummyOpponent = mDummyOpponentRepository.findById(mDummyOpponent.getId()).get();
        // Disconnect from session so that the updates on updatedMDummyOpponent are not directly saved in db
        em.detach(updatedMDummyOpponent);
        updatedMDummyOpponent
            .npcDeckId(UPDATED_NPC_DECK_ID)
            .totalParameter(UPDATED_TOTAL_PARAMETER)
            .name(UPDATED_NAME)
            .rank(UPDATED_RANK)
            .emblemId(UPDATED_EMBLEM_ID)
            .fpUniformUpId(UPDATED_FP_UNIFORM_UP_ID)
            .fpUniformUpColor(UPDATED_FP_UNIFORM_UP_COLOR)
            .fpUniformBottomId(UPDATED_FP_UNIFORM_BOTTOM_ID)
            .fpUniformBottomColor(UPDATED_FP_UNIFORM_BOTTOM_COLOR)
            .gkUniformUpId(UPDATED_GK_UNIFORM_UP_ID)
            .gkUniformUpColor(UPDATED_GK_UNIFORM_UP_COLOR)
            .gkUniformBottomId(UPDATED_GK_UNIFORM_BOTTOM_ID)
            .gkUniformBottomColor(UPDATED_GK_UNIFORM_BOTTOM_COLOR);
        MDummyOpponentDTO mDummyOpponentDTO = mDummyOpponentMapper.toDto(updatedMDummyOpponent);

        restMDummyOpponentMockMvc.perform(put("/api/m-dummy-opponents")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mDummyOpponentDTO)))
            .andExpect(status().isOk());

        // Validate the MDummyOpponent in the database
        List<MDummyOpponent> mDummyOpponentList = mDummyOpponentRepository.findAll();
        assertThat(mDummyOpponentList).hasSize(databaseSizeBeforeUpdate);
        MDummyOpponent testMDummyOpponent = mDummyOpponentList.get(mDummyOpponentList.size() - 1);
        assertThat(testMDummyOpponent.getNpcDeckId()).isEqualTo(UPDATED_NPC_DECK_ID);
        assertThat(testMDummyOpponent.getTotalParameter()).isEqualTo(UPDATED_TOTAL_PARAMETER);
        assertThat(testMDummyOpponent.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testMDummyOpponent.getRank()).isEqualTo(UPDATED_RANK);
        assertThat(testMDummyOpponent.getEmblemId()).isEqualTo(UPDATED_EMBLEM_ID);
        assertThat(testMDummyOpponent.getFpUniformUpId()).isEqualTo(UPDATED_FP_UNIFORM_UP_ID);
        assertThat(testMDummyOpponent.getFpUniformUpColor()).isEqualTo(UPDATED_FP_UNIFORM_UP_COLOR);
        assertThat(testMDummyOpponent.getFpUniformBottomId()).isEqualTo(UPDATED_FP_UNIFORM_BOTTOM_ID);
        assertThat(testMDummyOpponent.getFpUniformBottomColor()).isEqualTo(UPDATED_FP_UNIFORM_BOTTOM_COLOR);
        assertThat(testMDummyOpponent.getGkUniformUpId()).isEqualTo(UPDATED_GK_UNIFORM_UP_ID);
        assertThat(testMDummyOpponent.getGkUniformUpColor()).isEqualTo(UPDATED_GK_UNIFORM_UP_COLOR);
        assertThat(testMDummyOpponent.getGkUniformBottomId()).isEqualTo(UPDATED_GK_UNIFORM_BOTTOM_ID);
        assertThat(testMDummyOpponent.getGkUniformBottomColor()).isEqualTo(UPDATED_GK_UNIFORM_BOTTOM_COLOR);
    }

    @Test
    @Transactional
    public void updateNonExistingMDummyOpponent() throws Exception {
        int databaseSizeBeforeUpdate = mDummyOpponentRepository.findAll().size();

        // Create the MDummyOpponent
        MDummyOpponentDTO mDummyOpponentDTO = mDummyOpponentMapper.toDto(mDummyOpponent);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restMDummyOpponentMockMvc.perform(put("/api/m-dummy-opponents")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mDummyOpponentDTO)))
            .andExpect(status().isBadRequest());

        // Validate the MDummyOpponent in the database
        List<MDummyOpponent> mDummyOpponentList = mDummyOpponentRepository.findAll();
        assertThat(mDummyOpponentList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteMDummyOpponent() throws Exception {
        // Initialize the database
        mDummyOpponentRepository.saveAndFlush(mDummyOpponent);

        int databaseSizeBeforeDelete = mDummyOpponentRepository.findAll().size();

        // Delete the mDummyOpponent
        restMDummyOpponentMockMvc.perform(delete("/api/m-dummy-opponents/{id}", mDummyOpponent.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database is empty
        List<MDummyOpponent> mDummyOpponentList = mDummyOpponentRepository.findAll();
        assertThat(mDummyOpponentList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(MDummyOpponent.class);
        MDummyOpponent mDummyOpponent1 = new MDummyOpponent();
        mDummyOpponent1.setId(1L);
        MDummyOpponent mDummyOpponent2 = new MDummyOpponent();
        mDummyOpponent2.setId(mDummyOpponent1.getId());
        assertThat(mDummyOpponent1).isEqualTo(mDummyOpponent2);
        mDummyOpponent2.setId(2L);
        assertThat(mDummyOpponent1).isNotEqualTo(mDummyOpponent2);
        mDummyOpponent1.setId(null);
        assertThat(mDummyOpponent1).isNotEqualTo(mDummyOpponent2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(MDummyOpponentDTO.class);
        MDummyOpponentDTO mDummyOpponentDTO1 = new MDummyOpponentDTO();
        mDummyOpponentDTO1.setId(1L);
        MDummyOpponentDTO mDummyOpponentDTO2 = new MDummyOpponentDTO();
        assertThat(mDummyOpponentDTO1).isNotEqualTo(mDummyOpponentDTO2);
        mDummyOpponentDTO2.setId(mDummyOpponentDTO1.getId());
        assertThat(mDummyOpponentDTO1).isEqualTo(mDummyOpponentDTO2);
        mDummyOpponentDTO2.setId(2L);
        assertThat(mDummyOpponentDTO1).isNotEqualTo(mDummyOpponentDTO2);
        mDummyOpponentDTO1.setId(null);
        assertThat(mDummyOpponentDTO1).isNotEqualTo(mDummyOpponentDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(mDummyOpponentMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(mDummyOpponentMapper.fromId(null)).isNull();
    }
}
