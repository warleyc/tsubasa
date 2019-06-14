package io.shm.tsubasa.web.rest;

import io.shm.tsubasa.TsubasaApp;
import io.shm.tsubasa.domain.MPvpRegulation;
import io.shm.tsubasa.domain.MMatchOption;
import io.shm.tsubasa.repository.MPvpRegulationRepository;
import io.shm.tsubasa.service.MPvpRegulationService;
import io.shm.tsubasa.service.dto.MPvpRegulationDTO;
import io.shm.tsubasa.service.mapper.MPvpRegulationMapper;
import io.shm.tsubasa.web.rest.errors.ExceptionTranslator;
import io.shm.tsubasa.service.dto.MPvpRegulationCriteria;
import io.shm.tsubasa.service.MPvpRegulationQueryService;

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
 * Integration tests for the {@Link MPvpRegulationResource} REST controller.
 */
@SpringBootTest(classes = TsubasaApp.class)
public class MPvpRegulationResourceIT {

    private static final Integer DEFAULT_START_AT = 1;
    private static final Integer UPDATED_START_AT = 2;

    private static final Integer DEFAULT_END_AT = 1;
    private static final Integer UPDATED_END_AT = 2;

    private static final Integer DEFAULT_MATCH_OPTION_ID = 1;
    private static final Integer UPDATED_MATCH_OPTION_ID = 2;

    private static final Integer DEFAULT_DECK_CONDITION_ID = 1;
    private static final Integer UPDATED_DECK_CONDITION_ID = 2;

    private static final Integer DEFAULT_RULE_TUTORIAL_ID = 1;
    private static final Integer UPDATED_RULE_TUTORIAL_ID = 2;

    @Autowired
    private MPvpRegulationRepository mPvpRegulationRepository;

    @Autowired
    private MPvpRegulationMapper mPvpRegulationMapper;

    @Autowired
    private MPvpRegulationService mPvpRegulationService;

    @Autowired
    private MPvpRegulationQueryService mPvpRegulationQueryService;

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

    private MockMvc restMPvpRegulationMockMvc;

    private MPvpRegulation mPvpRegulation;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final MPvpRegulationResource mPvpRegulationResource = new MPvpRegulationResource(mPvpRegulationService, mPvpRegulationQueryService);
        this.restMPvpRegulationMockMvc = MockMvcBuilders.standaloneSetup(mPvpRegulationResource)
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
    public static MPvpRegulation createEntity(EntityManager em) {
        MPvpRegulation mPvpRegulation = new MPvpRegulation()
            .startAt(DEFAULT_START_AT)
            .endAt(DEFAULT_END_AT)
            .matchOptionId(DEFAULT_MATCH_OPTION_ID)
            .deckConditionId(DEFAULT_DECK_CONDITION_ID)
            .ruleTutorialId(DEFAULT_RULE_TUTORIAL_ID);
        // Add required entity
        MMatchOption mMatchOption;
        if (TestUtil.findAll(em, MMatchOption.class).isEmpty()) {
            mMatchOption = MMatchOptionResourceIT.createEntity(em);
            em.persist(mMatchOption);
            em.flush();
        } else {
            mMatchOption = TestUtil.findAll(em, MMatchOption.class).get(0);
        }
        mPvpRegulation.setMmatchoption(mMatchOption);
        return mPvpRegulation;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static MPvpRegulation createUpdatedEntity(EntityManager em) {
        MPvpRegulation mPvpRegulation = new MPvpRegulation()
            .startAt(UPDATED_START_AT)
            .endAt(UPDATED_END_AT)
            .matchOptionId(UPDATED_MATCH_OPTION_ID)
            .deckConditionId(UPDATED_DECK_CONDITION_ID)
            .ruleTutorialId(UPDATED_RULE_TUTORIAL_ID);
        // Add required entity
        MMatchOption mMatchOption;
        if (TestUtil.findAll(em, MMatchOption.class).isEmpty()) {
            mMatchOption = MMatchOptionResourceIT.createUpdatedEntity(em);
            em.persist(mMatchOption);
            em.flush();
        } else {
            mMatchOption = TestUtil.findAll(em, MMatchOption.class).get(0);
        }
        mPvpRegulation.setMmatchoption(mMatchOption);
        return mPvpRegulation;
    }

    @BeforeEach
    public void initTest() {
        mPvpRegulation = createEntity(em);
    }

    @Test
    @Transactional
    public void createMPvpRegulation() throws Exception {
        int databaseSizeBeforeCreate = mPvpRegulationRepository.findAll().size();

        // Create the MPvpRegulation
        MPvpRegulationDTO mPvpRegulationDTO = mPvpRegulationMapper.toDto(mPvpRegulation);
        restMPvpRegulationMockMvc.perform(post("/api/m-pvp-regulations")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mPvpRegulationDTO)))
            .andExpect(status().isCreated());

        // Validate the MPvpRegulation in the database
        List<MPvpRegulation> mPvpRegulationList = mPvpRegulationRepository.findAll();
        assertThat(mPvpRegulationList).hasSize(databaseSizeBeforeCreate + 1);
        MPvpRegulation testMPvpRegulation = mPvpRegulationList.get(mPvpRegulationList.size() - 1);
        assertThat(testMPvpRegulation.getStartAt()).isEqualTo(DEFAULT_START_AT);
        assertThat(testMPvpRegulation.getEndAt()).isEqualTo(DEFAULT_END_AT);
        assertThat(testMPvpRegulation.getMatchOptionId()).isEqualTo(DEFAULT_MATCH_OPTION_ID);
        assertThat(testMPvpRegulation.getDeckConditionId()).isEqualTo(DEFAULT_DECK_CONDITION_ID);
        assertThat(testMPvpRegulation.getRuleTutorialId()).isEqualTo(DEFAULT_RULE_TUTORIAL_ID);
    }

    @Test
    @Transactional
    public void createMPvpRegulationWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = mPvpRegulationRepository.findAll().size();

        // Create the MPvpRegulation with an existing ID
        mPvpRegulation.setId(1L);
        MPvpRegulationDTO mPvpRegulationDTO = mPvpRegulationMapper.toDto(mPvpRegulation);

        // An entity with an existing ID cannot be created, so this API call must fail
        restMPvpRegulationMockMvc.perform(post("/api/m-pvp-regulations")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mPvpRegulationDTO)))
            .andExpect(status().isBadRequest());

        // Validate the MPvpRegulation in the database
        List<MPvpRegulation> mPvpRegulationList = mPvpRegulationRepository.findAll();
        assertThat(mPvpRegulationList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkStartAtIsRequired() throws Exception {
        int databaseSizeBeforeTest = mPvpRegulationRepository.findAll().size();
        // set the field null
        mPvpRegulation.setStartAt(null);

        // Create the MPvpRegulation, which fails.
        MPvpRegulationDTO mPvpRegulationDTO = mPvpRegulationMapper.toDto(mPvpRegulation);

        restMPvpRegulationMockMvc.perform(post("/api/m-pvp-regulations")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mPvpRegulationDTO)))
            .andExpect(status().isBadRequest());

        List<MPvpRegulation> mPvpRegulationList = mPvpRegulationRepository.findAll();
        assertThat(mPvpRegulationList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkEndAtIsRequired() throws Exception {
        int databaseSizeBeforeTest = mPvpRegulationRepository.findAll().size();
        // set the field null
        mPvpRegulation.setEndAt(null);

        // Create the MPvpRegulation, which fails.
        MPvpRegulationDTO mPvpRegulationDTO = mPvpRegulationMapper.toDto(mPvpRegulation);

        restMPvpRegulationMockMvc.perform(post("/api/m-pvp-regulations")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mPvpRegulationDTO)))
            .andExpect(status().isBadRequest());

        List<MPvpRegulation> mPvpRegulationList = mPvpRegulationRepository.findAll();
        assertThat(mPvpRegulationList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkMatchOptionIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = mPvpRegulationRepository.findAll().size();
        // set the field null
        mPvpRegulation.setMatchOptionId(null);

        // Create the MPvpRegulation, which fails.
        MPvpRegulationDTO mPvpRegulationDTO = mPvpRegulationMapper.toDto(mPvpRegulation);

        restMPvpRegulationMockMvc.perform(post("/api/m-pvp-regulations")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mPvpRegulationDTO)))
            .andExpect(status().isBadRequest());

        List<MPvpRegulation> mPvpRegulationList = mPvpRegulationRepository.findAll();
        assertThat(mPvpRegulationList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkDeckConditionIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = mPvpRegulationRepository.findAll().size();
        // set the field null
        mPvpRegulation.setDeckConditionId(null);

        // Create the MPvpRegulation, which fails.
        MPvpRegulationDTO mPvpRegulationDTO = mPvpRegulationMapper.toDto(mPvpRegulation);

        restMPvpRegulationMockMvc.perform(post("/api/m-pvp-regulations")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mPvpRegulationDTO)))
            .andExpect(status().isBadRequest());

        List<MPvpRegulation> mPvpRegulationList = mPvpRegulationRepository.findAll();
        assertThat(mPvpRegulationList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkRuleTutorialIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = mPvpRegulationRepository.findAll().size();
        // set the field null
        mPvpRegulation.setRuleTutorialId(null);

        // Create the MPvpRegulation, which fails.
        MPvpRegulationDTO mPvpRegulationDTO = mPvpRegulationMapper.toDto(mPvpRegulation);

        restMPvpRegulationMockMvc.perform(post("/api/m-pvp-regulations")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mPvpRegulationDTO)))
            .andExpect(status().isBadRequest());

        List<MPvpRegulation> mPvpRegulationList = mPvpRegulationRepository.findAll();
        assertThat(mPvpRegulationList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllMPvpRegulations() throws Exception {
        // Initialize the database
        mPvpRegulationRepository.saveAndFlush(mPvpRegulation);

        // Get all the mPvpRegulationList
        restMPvpRegulationMockMvc.perform(get("/api/m-pvp-regulations?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(mPvpRegulation.getId().intValue())))
            .andExpect(jsonPath("$.[*].startAt").value(hasItem(DEFAULT_START_AT)))
            .andExpect(jsonPath("$.[*].endAt").value(hasItem(DEFAULT_END_AT)))
            .andExpect(jsonPath("$.[*].matchOptionId").value(hasItem(DEFAULT_MATCH_OPTION_ID)))
            .andExpect(jsonPath("$.[*].deckConditionId").value(hasItem(DEFAULT_DECK_CONDITION_ID)))
            .andExpect(jsonPath("$.[*].ruleTutorialId").value(hasItem(DEFAULT_RULE_TUTORIAL_ID)));
    }
    
    @Test
    @Transactional
    public void getMPvpRegulation() throws Exception {
        // Initialize the database
        mPvpRegulationRepository.saveAndFlush(mPvpRegulation);

        // Get the mPvpRegulation
        restMPvpRegulationMockMvc.perform(get("/api/m-pvp-regulations/{id}", mPvpRegulation.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(mPvpRegulation.getId().intValue()))
            .andExpect(jsonPath("$.startAt").value(DEFAULT_START_AT))
            .andExpect(jsonPath("$.endAt").value(DEFAULT_END_AT))
            .andExpect(jsonPath("$.matchOptionId").value(DEFAULT_MATCH_OPTION_ID))
            .andExpect(jsonPath("$.deckConditionId").value(DEFAULT_DECK_CONDITION_ID))
            .andExpect(jsonPath("$.ruleTutorialId").value(DEFAULT_RULE_TUTORIAL_ID));
    }

    @Test
    @Transactional
    public void getAllMPvpRegulationsByStartAtIsEqualToSomething() throws Exception {
        // Initialize the database
        mPvpRegulationRepository.saveAndFlush(mPvpRegulation);

        // Get all the mPvpRegulationList where startAt equals to DEFAULT_START_AT
        defaultMPvpRegulationShouldBeFound("startAt.equals=" + DEFAULT_START_AT);

        // Get all the mPvpRegulationList where startAt equals to UPDATED_START_AT
        defaultMPvpRegulationShouldNotBeFound("startAt.equals=" + UPDATED_START_AT);
    }

    @Test
    @Transactional
    public void getAllMPvpRegulationsByStartAtIsInShouldWork() throws Exception {
        // Initialize the database
        mPvpRegulationRepository.saveAndFlush(mPvpRegulation);

        // Get all the mPvpRegulationList where startAt in DEFAULT_START_AT or UPDATED_START_AT
        defaultMPvpRegulationShouldBeFound("startAt.in=" + DEFAULT_START_AT + "," + UPDATED_START_AT);

        // Get all the mPvpRegulationList where startAt equals to UPDATED_START_AT
        defaultMPvpRegulationShouldNotBeFound("startAt.in=" + UPDATED_START_AT);
    }

    @Test
    @Transactional
    public void getAllMPvpRegulationsByStartAtIsNullOrNotNull() throws Exception {
        // Initialize the database
        mPvpRegulationRepository.saveAndFlush(mPvpRegulation);

        // Get all the mPvpRegulationList where startAt is not null
        defaultMPvpRegulationShouldBeFound("startAt.specified=true");

        // Get all the mPvpRegulationList where startAt is null
        defaultMPvpRegulationShouldNotBeFound("startAt.specified=false");
    }

    @Test
    @Transactional
    public void getAllMPvpRegulationsByStartAtIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mPvpRegulationRepository.saveAndFlush(mPvpRegulation);

        // Get all the mPvpRegulationList where startAt greater than or equals to DEFAULT_START_AT
        defaultMPvpRegulationShouldBeFound("startAt.greaterOrEqualThan=" + DEFAULT_START_AT);

        // Get all the mPvpRegulationList where startAt greater than or equals to UPDATED_START_AT
        defaultMPvpRegulationShouldNotBeFound("startAt.greaterOrEqualThan=" + UPDATED_START_AT);
    }

    @Test
    @Transactional
    public void getAllMPvpRegulationsByStartAtIsLessThanSomething() throws Exception {
        // Initialize the database
        mPvpRegulationRepository.saveAndFlush(mPvpRegulation);

        // Get all the mPvpRegulationList where startAt less than or equals to DEFAULT_START_AT
        defaultMPvpRegulationShouldNotBeFound("startAt.lessThan=" + DEFAULT_START_AT);

        // Get all the mPvpRegulationList where startAt less than or equals to UPDATED_START_AT
        defaultMPvpRegulationShouldBeFound("startAt.lessThan=" + UPDATED_START_AT);
    }


    @Test
    @Transactional
    public void getAllMPvpRegulationsByEndAtIsEqualToSomething() throws Exception {
        // Initialize the database
        mPvpRegulationRepository.saveAndFlush(mPvpRegulation);

        // Get all the mPvpRegulationList where endAt equals to DEFAULT_END_AT
        defaultMPvpRegulationShouldBeFound("endAt.equals=" + DEFAULT_END_AT);

        // Get all the mPvpRegulationList where endAt equals to UPDATED_END_AT
        defaultMPvpRegulationShouldNotBeFound("endAt.equals=" + UPDATED_END_AT);
    }

    @Test
    @Transactional
    public void getAllMPvpRegulationsByEndAtIsInShouldWork() throws Exception {
        // Initialize the database
        mPvpRegulationRepository.saveAndFlush(mPvpRegulation);

        // Get all the mPvpRegulationList where endAt in DEFAULT_END_AT or UPDATED_END_AT
        defaultMPvpRegulationShouldBeFound("endAt.in=" + DEFAULT_END_AT + "," + UPDATED_END_AT);

        // Get all the mPvpRegulationList where endAt equals to UPDATED_END_AT
        defaultMPvpRegulationShouldNotBeFound("endAt.in=" + UPDATED_END_AT);
    }

    @Test
    @Transactional
    public void getAllMPvpRegulationsByEndAtIsNullOrNotNull() throws Exception {
        // Initialize the database
        mPvpRegulationRepository.saveAndFlush(mPvpRegulation);

        // Get all the mPvpRegulationList where endAt is not null
        defaultMPvpRegulationShouldBeFound("endAt.specified=true");

        // Get all the mPvpRegulationList where endAt is null
        defaultMPvpRegulationShouldNotBeFound("endAt.specified=false");
    }

    @Test
    @Transactional
    public void getAllMPvpRegulationsByEndAtIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mPvpRegulationRepository.saveAndFlush(mPvpRegulation);

        // Get all the mPvpRegulationList where endAt greater than or equals to DEFAULT_END_AT
        defaultMPvpRegulationShouldBeFound("endAt.greaterOrEqualThan=" + DEFAULT_END_AT);

        // Get all the mPvpRegulationList where endAt greater than or equals to UPDATED_END_AT
        defaultMPvpRegulationShouldNotBeFound("endAt.greaterOrEqualThan=" + UPDATED_END_AT);
    }

    @Test
    @Transactional
    public void getAllMPvpRegulationsByEndAtIsLessThanSomething() throws Exception {
        // Initialize the database
        mPvpRegulationRepository.saveAndFlush(mPvpRegulation);

        // Get all the mPvpRegulationList where endAt less than or equals to DEFAULT_END_AT
        defaultMPvpRegulationShouldNotBeFound("endAt.lessThan=" + DEFAULT_END_AT);

        // Get all the mPvpRegulationList where endAt less than or equals to UPDATED_END_AT
        defaultMPvpRegulationShouldBeFound("endAt.lessThan=" + UPDATED_END_AT);
    }


    @Test
    @Transactional
    public void getAllMPvpRegulationsByMatchOptionIdIsEqualToSomething() throws Exception {
        // Initialize the database
        mPvpRegulationRepository.saveAndFlush(mPvpRegulation);

        // Get all the mPvpRegulationList where matchOptionId equals to DEFAULT_MATCH_OPTION_ID
        defaultMPvpRegulationShouldBeFound("matchOptionId.equals=" + DEFAULT_MATCH_OPTION_ID);

        // Get all the mPvpRegulationList where matchOptionId equals to UPDATED_MATCH_OPTION_ID
        defaultMPvpRegulationShouldNotBeFound("matchOptionId.equals=" + UPDATED_MATCH_OPTION_ID);
    }

    @Test
    @Transactional
    public void getAllMPvpRegulationsByMatchOptionIdIsInShouldWork() throws Exception {
        // Initialize the database
        mPvpRegulationRepository.saveAndFlush(mPvpRegulation);

        // Get all the mPvpRegulationList where matchOptionId in DEFAULT_MATCH_OPTION_ID or UPDATED_MATCH_OPTION_ID
        defaultMPvpRegulationShouldBeFound("matchOptionId.in=" + DEFAULT_MATCH_OPTION_ID + "," + UPDATED_MATCH_OPTION_ID);

        // Get all the mPvpRegulationList where matchOptionId equals to UPDATED_MATCH_OPTION_ID
        defaultMPvpRegulationShouldNotBeFound("matchOptionId.in=" + UPDATED_MATCH_OPTION_ID);
    }

    @Test
    @Transactional
    public void getAllMPvpRegulationsByMatchOptionIdIsNullOrNotNull() throws Exception {
        // Initialize the database
        mPvpRegulationRepository.saveAndFlush(mPvpRegulation);

        // Get all the mPvpRegulationList where matchOptionId is not null
        defaultMPvpRegulationShouldBeFound("matchOptionId.specified=true");

        // Get all the mPvpRegulationList where matchOptionId is null
        defaultMPvpRegulationShouldNotBeFound("matchOptionId.specified=false");
    }

    @Test
    @Transactional
    public void getAllMPvpRegulationsByMatchOptionIdIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mPvpRegulationRepository.saveAndFlush(mPvpRegulation);

        // Get all the mPvpRegulationList where matchOptionId greater than or equals to DEFAULT_MATCH_OPTION_ID
        defaultMPvpRegulationShouldBeFound("matchOptionId.greaterOrEqualThan=" + DEFAULT_MATCH_OPTION_ID);

        // Get all the mPvpRegulationList where matchOptionId greater than or equals to UPDATED_MATCH_OPTION_ID
        defaultMPvpRegulationShouldNotBeFound("matchOptionId.greaterOrEqualThan=" + UPDATED_MATCH_OPTION_ID);
    }

    @Test
    @Transactional
    public void getAllMPvpRegulationsByMatchOptionIdIsLessThanSomething() throws Exception {
        // Initialize the database
        mPvpRegulationRepository.saveAndFlush(mPvpRegulation);

        // Get all the mPvpRegulationList where matchOptionId less than or equals to DEFAULT_MATCH_OPTION_ID
        defaultMPvpRegulationShouldNotBeFound("matchOptionId.lessThan=" + DEFAULT_MATCH_OPTION_ID);

        // Get all the mPvpRegulationList where matchOptionId less than or equals to UPDATED_MATCH_OPTION_ID
        defaultMPvpRegulationShouldBeFound("matchOptionId.lessThan=" + UPDATED_MATCH_OPTION_ID);
    }


    @Test
    @Transactional
    public void getAllMPvpRegulationsByDeckConditionIdIsEqualToSomething() throws Exception {
        // Initialize the database
        mPvpRegulationRepository.saveAndFlush(mPvpRegulation);

        // Get all the mPvpRegulationList where deckConditionId equals to DEFAULT_DECK_CONDITION_ID
        defaultMPvpRegulationShouldBeFound("deckConditionId.equals=" + DEFAULT_DECK_CONDITION_ID);

        // Get all the mPvpRegulationList where deckConditionId equals to UPDATED_DECK_CONDITION_ID
        defaultMPvpRegulationShouldNotBeFound("deckConditionId.equals=" + UPDATED_DECK_CONDITION_ID);
    }

    @Test
    @Transactional
    public void getAllMPvpRegulationsByDeckConditionIdIsInShouldWork() throws Exception {
        // Initialize the database
        mPvpRegulationRepository.saveAndFlush(mPvpRegulation);

        // Get all the mPvpRegulationList where deckConditionId in DEFAULT_DECK_CONDITION_ID or UPDATED_DECK_CONDITION_ID
        defaultMPvpRegulationShouldBeFound("deckConditionId.in=" + DEFAULT_DECK_CONDITION_ID + "," + UPDATED_DECK_CONDITION_ID);

        // Get all the mPvpRegulationList where deckConditionId equals to UPDATED_DECK_CONDITION_ID
        defaultMPvpRegulationShouldNotBeFound("deckConditionId.in=" + UPDATED_DECK_CONDITION_ID);
    }

    @Test
    @Transactional
    public void getAllMPvpRegulationsByDeckConditionIdIsNullOrNotNull() throws Exception {
        // Initialize the database
        mPvpRegulationRepository.saveAndFlush(mPvpRegulation);

        // Get all the mPvpRegulationList where deckConditionId is not null
        defaultMPvpRegulationShouldBeFound("deckConditionId.specified=true");

        // Get all the mPvpRegulationList where deckConditionId is null
        defaultMPvpRegulationShouldNotBeFound("deckConditionId.specified=false");
    }

    @Test
    @Transactional
    public void getAllMPvpRegulationsByDeckConditionIdIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mPvpRegulationRepository.saveAndFlush(mPvpRegulation);

        // Get all the mPvpRegulationList where deckConditionId greater than or equals to DEFAULT_DECK_CONDITION_ID
        defaultMPvpRegulationShouldBeFound("deckConditionId.greaterOrEqualThan=" + DEFAULT_DECK_CONDITION_ID);

        // Get all the mPvpRegulationList where deckConditionId greater than or equals to UPDATED_DECK_CONDITION_ID
        defaultMPvpRegulationShouldNotBeFound("deckConditionId.greaterOrEqualThan=" + UPDATED_DECK_CONDITION_ID);
    }

    @Test
    @Transactional
    public void getAllMPvpRegulationsByDeckConditionIdIsLessThanSomething() throws Exception {
        // Initialize the database
        mPvpRegulationRepository.saveAndFlush(mPvpRegulation);

        // Get all the mPvpRegulationList where deckConditionId less than or equals to DEFAULT_DECK_CONDITION_ID
        defaultMPvpRegulationShouldNotBeFound("deckConditionId.lessThan=" + DEFAULT_DECK_CONDITION_ID);

        // Get all the mPvpRegulationList where deckConditionId less than or equals to UPDATED_DECK_CONDITION_ID
        defaultMPvpRegulationShouldBeFound("deckConditionId.lessThan=" + UPDATED_DECK_CONDITION_ID);
    }


    @Test
    @Transactional
    public void getAllMPvpRegulationsByRuleTutorialIdIsEqualToSomething() throws Exception {
        // Initialize the database
        mPvpRegulationRepository.saveAndFlush(mPvpRegulation);

        // Get all the mPvpRegulationList where ruleTutorialId equals to DEFAULT_RULE_TUTORIAL_ID
        defaultMPvpRegulationShouldBeFound("ruleTutorialId.equals=" + DEFAULT_RULE_TUTORIAL_ID);

        // Get all the mPvpRegulationList where ruleTutorialId equals to UPDATED_RULE_TUTORIAL_ID
        defaultMPvpRegulationShouldNotBeFound("ruleTutorialId.equals=" + UPDATED_RULE_TUTORIAL_ID);
    }

    @Test
    @Transactional
    public void getAllMPvpRegulationsByRuleTutorialIdIsInShouldWork() throws Exception {
        // Initialize the database
        mPvpRegulationRepository.saveAndFlush(mPvpRegulation);

        // Get all the mPvpRegulationList where ruleTutorialId in DEFAULT_RULE_TUTORIAL_ID or UPDATED_RULE_TUTORIAL_ID
        defaultMPvpRegulationShouldBeFound("ruleTutorialId.in=" + DEFAULT_RULE_TUTORIAL_ID + "," + UPDATED_RULE_TUTORIAL_ID);

        // Get all the mPvpRegulationList where ruleTutorialId equals to UPDATED_RULE_TUTORIAL_ID
        defaultMPvpRegulationShouldNotBeFound("ruleTutorialId.in=" + UPDATED_RULE_TUTORIAL_ID);
    }

    @Test
    @Transactional
    public void getAllMPvpRegulationsByRuleTutorialIdIsNullOrNotNull() throws Exception {
        // Initialize the database
        mPvpRegulationRepository.saveAndFlush(mPvpRegulation);

        // Get all the mPvpRegulationList where ruleTutorialId is not null
        defaultMPvpRegulationShouldBeFound("ruleTutorialId.specified=true");

        // Get all the mPvpRegulationList where ruleTutorialId is null
        defaultMPvpRegulationShouldNotBeFound("ruleTutorialId.specified=false");
    }

    @Test
    @Transactional
    public void getAllMPvpRegulationsByRuleTutorialIdIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mPvpRegulationRepository.saveAndFlush(mPvpRegulation);

        // Get all the mPvpRegulationList where ruleTutorialId greater than or equals to DEFAULT_RULE_TUTORIAL_ID
        defaultMPvpRegulationShouldBeFound("ruleTutorialId.greaterOrEqualThan=" + DEFAULT_RULE_TUTORIAL_ID);

        // Get all the mPvpRegulationList where ruleTutorialId greater than or equals to UPDATED_RULE_TUTORIAL_ID
        defaultMPvpRegulationShouldNotBeFound("ruleTutorialId.greaterOrEqualThan=" + UPDATED_RULE_TUTORIAL_ID);
    }

    @Test
    @Transactional
    public void getAllMPvpRegulationsByRuleTutorialIdIsLessThanSomething() throws Exception {
        // Initialize the database
        mPvpRegulationRepository.saveAndFlush(mPvpRegulation);

        // Get all the mPvpRegulationList where ruleTutorialId less than or equals to DEFAULT_RULE_TUTORIAL_ID
        defaultMPvpRegulationShouldNotBeFound("ruleTutorialId.lessThan=" + DEFAULT_RULE_TUTORIAL_ID);

        // Get all the mPvpRegulationList where ruleTutorialId less than or equals to UPDATED_RULE_TUTORIAL_ID
        defaultMPvpRegulationShouldBeFound("ruleTutorialId.lessThan=" + UPDATED_RULE_TUTORIAL_ID);
    }


    @Test
    @Transactional
    public void getAllMPvpRegulationsByMmatchoptionIsEqualToSomething() throws Exception {
        // Get already existing entity
        MMatchOption mmatchoption = mPvpRegulation.getMmatchoption();
        mPvpRegulationRepository.saveAndFlush(mPvpRegulation);
        Long mmatchoptionId = mmatchoption.getId();

        // Get all the mPvpRegulationList where mmatchoption equals to mmatchoptionId
        defaultMPvpRegulationShouldBeFound("mmatchoptionId.equals=" + mmatchoptionId);

        // Get all the mPvpRegulationList where mmatchoption equals to mmatchoptionId + 1
        defaultMPvpRegulationShouldNotBeFound("mmatchoptionId.equals=" + (mmatchoptionId + 1));
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultMPvpRegulationShouldBeFound(String filter) throws Exception {
        restMPvpRegulationMockMvc.perform(get("/api/m-pvp-regulations?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(mPvpRegulation.getId().intValue())))
            .andExpect(jsonPath("$.[*].startAt").value(hasItem(DEFAULT_START_AT)))
            .andExpect(jsonPath("$.[*].endAt").value(hasItem(DEFAULT_END_AT)))
            .andExpect(jsonPath("$.[*].matchOptionId").value(hasItem(DEFAULT_MATCH_OPTION_ID)))
            .andExpect(jsonPath("$.[*].deckConditionId").value(hasItem(DEFAULT_DECK_CONDITION_ID)))
            .andExpect(jsonPath("$.[*].ruleTutorialId").value(hasItem(DEFAULT_RULE_TUTORIAL_ID)));

        // Check, that the count call also returns 1
        restMPvpRegulationMockMvc.perform(get("/api/m-pvp-regulations/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultMPvpRegulationShouldNotBeFound(String filter) throws Exception {
        restMPvpRegulationMockMvc.perform(get("/api/m-pvp-regulations?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restMPvpRegulationMockMvc.perform(get("/api/m-pvp-regulations/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("0"));
    }


    @Test
    @Transactional
    public void getNonExistingMPvpRegulation() throws Exception {
        // Get the mPvpRegulation
        restMPvpRegulationMockMvc.perform(get("/api/m-pvp-regulations/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateMPvpRegulation() throws Exception {
        // Initialize the database
        mPvpRegulationRepository.saveAndFlush(mPvpRegulation);

        int databaseSizeBeforeUpdate = mPvpRegulationRepository.findAll().size();

        // Update the mPvpRegulation
        MPvpRegulation updatedMPvpRegulation = mPvpRegulationRepository.findById(mPvpRegulation.getId()).get();
        // Disconnect from session so that the updates on updatedMPvpRegulation are not directly saved in db
        em.detach(updatedMPvpRegulation);
        updatedMPvpRegulation
            .startAt(UPDATED_START_AT)
            .endAt(UPDATED_END_AT)
            .matchOptionId(UPDATED_MATCH_OPTION_ID)
            .deckConditionId(UPDATED_DECK_CONDITION_ID)
            .ruleTutorialId(UPDATED_RULE_TUTORIAL_ID);
        MPvpRegulationDTO mPvpRegulationDTO = mPvpRegulationMapper.toDto(updatedMPvpRegulation);

        restMPvpRegulationMockMvc.perform(put("/api/m-pvp-regulations")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mPvpRegulationDTO)))
            .andExpect(status().isOk());

        // Validate the MPvpRegulation in the database
        List<MPvpRegulation> mPvpRegulationList = mPvpRegulationRepository.findAll();
        assertThat(mPvpRegulationList).hasSize(databaseSizeBeforeUpdate);
        MPvpRegulation testMPvpRegulation = mPvpRegulationList.get(mPvpRegulationList.size() - 1);
        assertThat(testMPvpRegulation.getStartAt()).isEqualTo(UPDATED_START_AT);
        assertThat(testMPvpRegulation.getEndAt()).isEqualTo(UPDATED_END_AT);
        assertThat(testMPvpRegulation.getMatchOptionId()).isEqualTo(UPDATED_MATCH_OPTION_ID);
        assertThat(testMPvpRegulation.getDeckConditionId()).isEqualTo(UPDATED_DECK_CONDITION_ID);
        assertThat(testMPvpRegulation.getRuleTutorialId()).isEqualTo(UPDATED_RULE_TUTORIAL_ID);
    }

    @Test
    @Transactional
    public void updateNonExistingMPvpRegulation() throws Exception {
        int databaseSizeBeforeUpdate = mPvpRegulationRepository.findAll().size();

        // Create the MPvpRegulation
        MPvpRegulationDTO mPvpRegulationDTO = mPvpRegulationMapper.toDto(mPvpRegulation);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restMPvpRegulationMockMvc.perform(put("/api/m-pvp-regulations")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mPvpRegulationDTO)))
            .andExpect(status().isBadRequest());

        // Validate the MPvpRegulation in the database
        List<MPvpRegulation> mPvpRegulationList = mPvpRegulationRepository.findAll();
        assertThat(mPvpRegulationList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteMPvpRegulation() throws Exception {
        // Initialize the database
        mPvpRegulationRepository.saveAndFlush(mPvpRegulation);

        int databaseSizeBeforeDelete = mPvpRegulationRepository.findAll().size();

        // Delete the mPvpRegulation
        restMPvpRegulationMockMvc.perform(delete("/api/m-pvp-regulations/{id}", mPvpRegulation.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database is empty
        List<MPvpRegulation> mPvpRegulationList = mPvpRegulationRepository.findAll();
        assertThat(mPvpRegulationList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(MPvpRegulation.class);
        MPvpRegulation mPvpRegulation1 = new MPvpRegulation();
        mPvpRegulation1.setId(1L);
        MPvpRegulation mPvpRegulation2 = new MPvpRegulation();
        mPvpRegulation2.setId(mPvpRegulation1.getId());
        assertThat(mPvpRegulation1).isEqualTo(mPvpRegulation2);
        mPvpRegulation2.setId(2L);
        assertThat(mPvpRegulation1).isNotEqualTo(mPvpRegulation2);
        mPvpRegulation1.setId(null);
        assertThat(mPvpRegulation1).isNotEqualTo(mPvpRegulation2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(MPvpRegulationDTO.class);
        MPvpRegulationDTO mPvpRegulationDTO1 = new MPvpRegulationDTO();
        mPvpRegulationDTO1.setId(1L);
        MPvpRegulationDTO mPvpRegulationDTO2 = new MPvpRegulationDTO();
        assertThat(mPvpRegulationDTO1).isNotEqualTo(mPvpRegulationDTO2);
        mPvpRegulationDTO2.setId(mPvpRegulationDTO1.getId());
        assertThat(mPvpRegulationDTO1).isEqualTo(mPvpRegulationDTO2);
        mPvpRegulationDTO2.setId(2L);
        assertThat(mPvpRegulationDTO1).isNotEqualTo(mPvpRegulationDTO2);
        mPvpRegulationDTO1.setId(null);
        assertThat(mPvpRegulationDTO1).isNotEqualTo(mPvpRegulationDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(mPvpRegulationMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(mPvpRegulationMapper.fromId(null)).isNull();
    }
}
