package io.shm.tsubasa.web.rest;

import io.shm.tsubasa.TsubasaApp;
import io.shm.tsubasa.domain.MLeagueRegulation;
import io.shm.tsubasa.domain.MMatchOption;
import io.shm.tsubasa.repository.MLeagueRegulationRepository;
import io.shm.tsubasa.service.MLeagueRegulationService;
import io.shm.tsubasa.service.dto.MLeagueRegulationDTO;
import io.shm.tsubasa.service.mapper.MLeagueRegulationMapper;
import io.shm.tsubasa.web.rest.errors.ExceptionTranslator;
import io.shm.tsubasa.service.dto.MLeagueRegulationCriteria;
import io.shm.tsubasa.service.MLeagueRegulationQueryService;

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
 * Integration tests for the {@Link MLeagueRegulationResource} REST controller.
 */
@SpringBootTest(classes = TsubasaApp.class)
public class MLeagueRegulationResourceIT {

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
    private MLeagueRegulationRepository mLeagueRegulationRepository;

    @Autowired
    private MLeagueRegulationMapper mLeagueRegulationMapper;

    @Autowired
    private MLeagueRegulationService mLeagueRegulationService;

    @Autowired
    private MLeagueRegulationQueryService mLeagueRegulationQueryService;

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

    private MockMvc restMLeagueRegulationMockMvc;

    private MLeagueRegulation mLeagueRegulation;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final MLeagueRegulationResource mLeagueRegulationResource = new MLeagueRegulationResource(mLeagueRegulationService, mLeagueRegulationQueryService);
        this.restMLeagueRegulationMockMvc = MockMvcBuilders.standaloneSetup(mLeagueRegulationResource)
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
    public static MLeagueRegulation createEntity(EntityManager em) {
        MLeagueRegulation mLeagueRegulation = new MLeagueRegulation()
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
        mLeagueRegulation.setId(mMatchOption);
        return mLeagueRegulation;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static MLeagueRegulation createUpdatedEntity(EntityManager em) {
        MLeagueRegulation mLeagueRegulation = new MLeagueRegulation()
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
        mLeagueRegulation.setId(mMatchOption);
        return mLeagueRegulation;
    }

    @BeforeEach
    public void initTest() {
        mLeagueRegulation = createEntity(em);
    }

    @Test
    @Transactional
    public void createMLeagueRegulation() throws Exception {
        int databaseSizeBeforeCreate = mLeagueRegulationRepository.findAll().size();

        // Create the MLeagueRegulation
        MLeagueRegulationDTO mLeagueRegulationDTO = mLeagueRegulationMapper.toDto(mLeagueRegulation);
        restMLeagueRegulationMockMvc.perform(post("/api/m-league-regulations")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mLeagueRegulationDTO)))
            .andExpect(status().isCreated());

        // Validate the MLeagueRegulation in the database
        List<MLeagueRegulation> mLeagueRegulationList = mLeagueRegulationRepository.findAll();
        assertThat(mLeagueRegulationList).hasSize(databaseSizeBeforeCreate + 1);
        MLeagueRegulation testMLeagueRegulation = mLeagueRegulationList.get(mLeagueRegulationList.size() - 1);
        assertThat(testMLeagueRegulation.getStartAt()).isEqualTo(DEFAULT_START_AT);
        assertThat(testMLeagueRegulation.getEndAt()).isEqualTo(DEFAULT_END_AT);
        assertThat(testMLeagueRegulation.getMatchOptionId()).isEqualTo(DEFAULT_MATCH_OPTION_ID);
        assertThat(testMLeagueRegulation.getDeckConditionId()).isEqualTo(DEFAULT_DECK_CONDITION_ID);
        assertThat(testMLeagueRegulation.getRuleTutorialId()).isEqualTo(DEFAULT_RULE_TUTORIAL_ID);
    }

    @Test
    @Transactional
    public void createMLeagueRegulationWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = mLeagueRegulationRepository.findAll().size();

        // Create the MLeagueRegulation with an existing ID
        mLeagueRegulation.setId(1L);
        MLeagueRegulationDTO mLeagueRegulationDTO = mLeagueRegulationMapper.toDto(mLeagueRegulation);

        // An entity with an existing ID cannot be created, so this API call must fail
        restMLeagueRegulationMockMvc.perform(post("/api/m-league-regulations")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mLeagueRegulationDTO)))
            .andExpect(status().isBadRequest());

        // Validate the MLeagueRegulation in the database
        List<MLeagueRegulation> mLeagueRegulationList = mLeagueRegulationRepository.findAll();
        assertThat(mLeagueRegulationList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkStartAtIsRequired() throws Exception {
        int databaseSizeBeforeTest = mLeagueRegulationRepository.findAll().size();
        // set the field null
        mLeagueRegulation.setStartAt(null);

        // Create the MLeagueRegulation, which fails.
        MLeagueRegulationDTO mLeagueRegulationDTO = mLeagueRegulationMapper.toDto(mLeagueRegulation);

        restMLeagueRegulationMockMvc.perform(post("/api/m-league-regulations")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mLeagueRegulationDTO)))
            .andExpect(status().isBadRequest());

        List<MLeagueRegulation> mLeagueRegulationList = mLeagueRegulationRepository.findAll();
        assertThat(mLeagueRegulationList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkEndAtIsRequired() throws Exception {
        int databaseSizeBeforeTest = mLeagueRegulationRepository.findAll().size();
        // set the field null
        mLeagueRegulation.setEndAt(null);

        // Create the MLeagueRegulation, which fails.
        MLeagueRegulationDTO mLeagueRegulationDTO = mLeagueRegulationMapper.toDto(mLeagueRegulation);

        restMLeagueRegulationMockMvc.perform(post("/api/m-league-regulations")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mLeagueRegulationDTO)))
            .andExpect(status().isBadRequest());

        List<MLeagueRegulation> mLeagueRegulationList = mLeagueRegulationRepository.findAll();
        assertThat(mLeagueRegulationList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkMatchOptionIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = mLeagueRegulationRepository.findAll().size();
        // set the field null
        mLeagueRegulation.setMatchOptionId(null);

        // Create the MLeagueRegulation, which fails.
        MLeagueRegulationDTO mLeagueRegulationDTO = mLeagueRegulationMapper.toDto(mLeagueRegulation);

        restMLeagueRegulationMockMvc.perform(post("/api/m-league-regulations")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mLeagueRegulationDTO)))
            .andExpect(status().isBadRequest());

        List<MLeagueRegulation> mLeagueRegulationList = mLeagueRegulationRepository.findAll();
        assertThat(mLeagueRegulationList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkDeckConditionIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = mLeagueRegulationRepository.findAll().size();
        // set the field null
        mLeagueRegulation.setDeckConditionId(null);

        // Create the MLeagueRegulation, which fails.
        MLeagueRegulationDTO mLeagueRegulationDTO = mLeagueRegulationMapper.toDto(mLeagueRegulation);

        restMLeagueRegulationMockMvc.perform(post("/api/m-league-regulations")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mLeagueRegulationDTO)))
            .andExpect(status().isBadRequest());

        List<MLeagueRegulation> mLeagueRegulationList = mLeagueRegulationRepository.findAll();
        assertThat(mLeagueRegulationList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkRuleTutorialIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = mLeagueRegulationRepository.findAll().size();
        // set the field null
        mLeagueRegulation.setRuleTutorialId(null);

        // Create the MLeagueRegulation, which fails.
        MLeagueRegulationDTO mLeagueRegulationDTO = mLeagueRegulationMapper.toDto(mLeagueRegulation);

        restMLeagueRegulationMockMvc.perform(post("/api/m-league-regulations")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mLeagueRegulationDTO)))
            .andExpect(status().isBadRequest());

        List<MLeagueRegulation> mLeagueRegulationList = mLeagueRegulationRepository.findAll();
        assertThat(mLeagueRegulationList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllMLeagueRegulations() throws Exception {
        // Initialize the database
        mLeagueRegulationRepository.saveAndFlush(mLeagueRegulation);

        // Get all the mLeagueRegulationList
        restMLeagueRegulationMockMvc.perform(get("/api/m-league-regulations?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(mLeagueRegulation.getId().intValue())))
            .andExpect(jsonPath("$.[*].startAt").value(hasItem(DEFAULT_START_AT)))
            .andExpect(jsonPath("$.[*].endAt").value(hasItem(DEFAULT_END_AT)))
            .andExpect(jsonPath("$.[*].matchOptionId").value(hasItem(DEFAULT_MATCH_OPTION_ID)))
            .andExpect(jsonPath("$.[*].deckConditionId").value(hasItem(DEFAULT_DECK_CONDITION_ID)))
            .andExpect(jsonPath("$.[*].ruleTutorialId").value(hasItem(DEFAULT_RULE_TUTORIAL_ID)));
    }
    
    @Test
    @Transactional
    public void getMLeagueRegulation() throws Exception {
        // Initialize the database
        mLeagueRegulationRepository.saveAndFlush(mLeagueRegulation);

        // Get the mLeagueRegulation
        restMLeagueRegulationMockMvc.perform(get("/api/m-league-regulations/{id}", mLeagueRegulation.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(mLeagueRegulation.getId().intValue()))
            .andExpect(jsonPath("$.startAt").value(DEFAULT_START_AT))
            .andExpect(jsonPath("$.endAt").value(DEFAULT_END_AT))
            .andExpect(jsonPath("$.matchOptionId").value(DEFAULT_MATCH_OPTION_ID))
            .andExpect(jsonPath("$.deckConditionId").value(DEFAULT_DECK_CONDITION_ID))
            .andExpect(jsonPath("$.ruleTutorialId").value(DEFAULT_RULE_TUTORIAL_ID));
    }

    @Test
    @Transactional
    public void getAllMLeagueRegulationsByStartAtIsEqualToSomething() throws Exception {
        // Initialize the database
        mLeagueRegulationRepository.saveAndFlush(mLeagueRegulation);

        // Get all the mLeagueRegulationList where startAt equals to DEFAULT_START_AT
        defaultMLeagueRegulationShouldBeFound("startAt.equals=" + DEFAULT_START_AT);

        // Get all the mLeagueRegulationList where startAt equals to UPDATED_START_AT
        defaultMLeagueRegulationShouldNotBeFound("startAt.equals=" + UPDATED_START_AT);
    }

    @Test
    @Transactional
    public void getAllMLeagueRegulationsByStartAtIsInShouldWork() throws Exception {
        // Initialize the database
        mLeagueRegulationRepository.saveAndFlush(mLeagueRegulation);

        // Get all the mLeagueRegulationList where startAt in DEFAULT_START_AT or UPDATED_START_AT
        defaultMLeagueRegulationShouldBeFound("startAt.in=" + DEFAULT_START_AT + "," + UPDATED_START_AT);

        // Get all the mLeagueRegulationList where startAt equals to UPDATED_START_AT
        defaultMLeagueRegulationShouldNotBeFound("startAt.in=" + UPDATED_START_AT);
    }

    @Test
    @Transactional
    public void getAllMLeagueRegulationsByStartAtIsNullOrNotNull() throws Exception {
        // Initialize the database
        mLeagueRegulationRepository.saveAndFlush(mLeagueRegulation);

        // Get all the mLeagueRegulationList where startAt is not null
        defaultMLeagueRegulationShouldBeFound("startAt.specified=true");

        // Get all the mLeagueRegulationList where startAt is null
        defaultMLeagueRegulationShouldNotBeFound("startAt.specified=false");
    }

    @Test
    @Transactional
    public void getAllMLeagueRegulationsByStartAtIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mLeagueRegulationRepository.saveAndFlush(mLeagueRegulation);

        // Get all the mLeagueRegulationList where startAt greater than or equals to DEFAULT_START_AT
        defaultMLeagueRegulationShouldBeFound("startAt.greaterOrEqualThan=" + DEFAULT_START_AT);

        // Get all the mLeagueRegulationList where startAt greater than or equals to UPDATED_START_AT
        defaultMLeagueRegulationShouldNotBeFound("startAt.greaterOrEqualThan=" + UPDATED_START_AT);
    }

    @Test
    @Transactional
    public void getAllMLeagueRegulationsByStartAtIsLessThanSomething() throws Exception {
        // Initialize the database
        mLeagueRegulationRepository.saveAndFlush(mLeagueRegulation);

        // Get all the mLeagueRegulationList where startAt less than or equals to DEFAULT_START_AT
        defaultMLeagueRegulationShouldNotBeFound("startAt.lessThan=" + DEFAULT_START_AT);

        // Get all the mLeagueRegulationList where startAt less than or equals to UPDATED_START_AT
        defaultMLeagueRegulationShouldBeFound("startAt.lessThan=" + UPDATED_START_AT);
    }


    @Test
    @Transactional
    public void getAllMLeagueRegulationsByEndAtIsEqualToSomething() throws Exception {
        // Initialize the database
        mLeagueRegulationRepository.saveAndFlush(mLeagueRegulation);

        // Get all the mLeagueRegulationList where endAt equals to DEFAULT_END_AT
        defaultMLeagueRegulationShouldBeFound("endAt.equals=" + DEFAULT_END_AT);

        // Get all the mLeagueRegulationList where endAt equals to UPDATED_END_AT
        defaultMLeagueRegulationShouldNotBeFound("endAt.equals=" + UPDATED_END_AT);
    }

    @Test
    @Transactional
    public void getAllMLeagueRegulationsByEndAtIsInShouldWork() throws Exception {
        // Initialize the database
        mLeagueRegulationRepository.saveAndFlush(mLeagueRegulation);

        // Get all the mLeagueRegulationList where endAt in DEFAULT_END_AT or UPDATED_END_AT
        defaultMLeagueRegulationShouldBeFound("endAt.in=" + DEFAULT_END_AT + "," + UPDATED_END_AT);

        // Get all the mLeagueRegulationList where endAt equals to UPDATED_END_AT
        defaultMLeagueRegulationShouldNotBeFound("endAt.in=" + UPDATED_END_AT);
    }

    @Test
    @Transactional
    public void getAllMLeagueRegulationsByEndAtIsNullOrNotNull() throws Exception {
        // Initialize the database
        mLeagueRegulationRepository.saveAndFlush(mLeagueRegulation);

        // Get all the mLeagueRegulationList where endAt is not null
        defaultMLeagueRegulationShouldBeFound("endAt.specified=true");

        // Get all the mLeagueRegulationList where endAt is null
        defaultMLeagueRegulationShouldNotBeFound("endAt.specified=false");
    }

    @Test
    @Transactional
    public void getAllMLeagueRegulationsByEndAtIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mLeagueRegulationRepository.saveAndFlush(mLeagueRegulation);

        // Get all the mLeagueRegulationList where endAt greater than or equals to DEFAULT_END_AT
        defaultMLeagueRegulationShouldBeFound("endAt.greaterOrEqualThan=" + DEFAULT_END_AT);

        // Get all the mLeagueRegulationList where endAt greater than or equals to UPDATED_END_AT
        defaultMLeagueRegulationShouldNotBeFound("endAt.greaterOrEqualThan=" + UPDATED_END_AT);
    }

    @Test
    @Transactional
    public void getAllMLeagueRegulationsByEndAtIsLessThanSomething() throws Exception {
        // Initialize the database
        mLeagueRegulationRepository.saveAndFlush(mLeagueRegulation);

        // Get all the mLeagueRegulationList where endAt less than or equals to DEFAULT_END_AT
        defaultMLeagueRegulationShouldNotBeFound("endAt.lessThan=" + DEFAULT_END_AT);

        // Get all the mLeagueRegulationList where endAt less than or equals to UPDATED_END_AT
        defaultMLeagueRegulationShouldBeFound("endAt.lessThan=" + UPDATED_END_AT);
    }


    @Test
    @Transactional
    public void getAllMLeagueRegulationsByMatchOptionIdIsEqualToSomething() throws Exception {
        // Initialize the database
        mLeagueRegulationRepository.saveAndFlush(mLeagueRegulation);

        // Get all the mLeagueRegulationList where matchOptionId equals to DEFAULT_MATCH_OPTION_ID
        defaultMLeagueRegulationShouldBeFound("matchOptionId.equals=" + DEFAULT_MATCH_OPTION_ID);

        // Get all the mLeagueRegulationList where matchOptionId equals to UPDATED_MATCH_OPTION_ID
        defaultMLeagueRegulationShouldNotBeFound("matchOptionId.equals=" + UPDATED_MATCH_OPTION_ID);
    }

    @Test
    @Transactional
    public void getAllMLeagueRegulationsByMatchOptionIdIsInShouldWork() throws Exception {
        // Initialize the database
        mLeagueRegulationRepository.saveAndFlush(mLeagueRegulation);

        // Get all the mLeagueRegulationList where matchOptionId in DEFAULT_MATCH_OPTION_ID or UPDATED_MATCH_OPTION_ID
        defaultMLeagueRegulationShouldBeFound("matchOptionId.in=" + DEFAULT_MATCH_OPTION_ID + "," + UPDATED_MATCH_OPTION_ID);

        // Get all the mLeagueRegulationList where matchOptionId equals to UPDATED_MATCH_OPTION_ID
        defaultMLeagueRegulationShouldNotBeFound("matchOptionId.in=" + UPDATED_MATCH_OPTION_ID);
    }

    @Test
    @Transactional
    public void getAllMLeagueRegulationsByMatchOptionIdIsNullOrNotNull() throws Exception {
        // Initialize the database
        mLeagueRegulationRepository.saveAndFlush(mLeagueRegulation);

        // Get all the mLeagueRegulationList where matchOptionId is not null
        defaultMLeagueRegulationShouldBeFound("matchOptionId.specified=true");

        // Get all the mLeagueRegulationList where matchOptionId is null
        defaultMLeagueRegulationShouldNotBeFound("matchOptionId.specified=false");
    }

    @Test
    @Transactional
    public void getAllMLeagueRegulationsByMatchOptionIdIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mLeagueRegulationRepository.saveAndFlush(mLeagueRegulation);

        // Get all the mLeagueRegulationList where matchOptionId greater than or equals to DEFAULT_MATCH_OPTION_ID
        defaultMLeagueRegulationShouldBeFound("matchOptionId.greaterOrEqualThan=" + DEFAULT_MATCH_OPTION_ID);

        // Get all the mLeagueRegulationList where matchOptionId greater than or equals to UPDATED_MATCH_OPTION_ID
        defaultMLeagueRegulationShouldNotBeFound("matchOptionId.greaterOrEqualThan=" + UPDATED_MATCH_OPTION_ID);
    }

    @Test
    @Transactional
    public void getAllMLeagueRegulationsByMatchOptionIdIsLessThanSomething() throws Exception {
        // Initialize the database
        mLeagueRegulationRepository.saveAndFlush(mLeagueRegulation);

        // Get all the mLeagueRegulationList where matchOptionId less than or equals to DEFAULT_MATCH_OPTION_ID
        defaultMLeagueRegulationShouldNotBeFound("matchOptionId.lessThan=" + DEFAULT_MATCH_OPTION_ID);

        // Get all the mLeagueRegulationList where matchOptionId less than or equals to UPDATED_MATCH_OPTION_ID
        defaultMLeagueRegulationShouldBeFound("matchOptionId.lessThan=" + UPDATED_MATCH_OPTION_ID);
    }


    @Test
    @Transactional
    public void getAllMLeagueRegulationsByDeckConditionIdIsEqualToSomething() throws Exception {
        // Initialize the database
        mLeagueRegulationRepository.saveAndFlush(mLeagueRegulation);

        // Get all the mLeagueRegulationList where deckConditionId equals to DEFAULT_DECK_CONDITION_ID
        defaultMLeagueRegulationShouldBeFound("deckConditionId.equals=" + DEFAULT_DECK_CONDITION_ID);

        // Get all the mLeagueRegulationList where deckConditionId equals to UPDATED_DECK_CONDITION_ID
        defaultMLeagueRegulationShouldNotBeFound("deckConditionId.equals=" + UPDATED_DECK_CONDITION_ID);
    }

    @Test
    @Transactional
    public void getAllMLeagueRegulationsByDeckConditionIdIsInShouldWork() throws Exception {
        // Initialize the database
        mLeagueRegulationRepository.saveAndFlush(mLeagueRegulation);

        // Get all the mLeagueRegulationList where deckConditionId in DEFAULT_DECK_CONDITION_ID or UPDATED_DECK_CONDITION_ID
        defaultMLeagueRegulationShouldBeFound("deckConditionId.in=" + DEFAULT_DECK_CONDITION_ID + "," + UPDATED_DECK_CONDITION_ID);

        // Get all the mLeagueRegulationList where deckConditionId equals to UPDATED_DECK_CONDITION_ID
        defaultMLeagueRegulationShouldNotBeFound("deckConditionId.in=" + UPDATED_DECK_CONDITION_ID);
    }

    @Test
    @Transactional
    public void getAllMLeagueRegulationsByDeckConditionIdIsNullOrNotNull() throws Exception {
        // Initialize the database
        mLeagueRegulationRepository.saveAndFlush(mLeagueRegulation);

        // Get all the mLeagueRegulationList where deckConditionId is not null
        defaultMLeagueRegulationShouldBeFound("deckConditionId.specified=true");

        // Get all the mLeagueRegulationList where deckConditionId is null
        defaultMLeagueRegulationShouldNotBeFound("deckConditionId.specified=false");
    }

    @Test
    @Transactional
    public void getAllMLeagueRegulationsByDeckConditionIdIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mLeagueRegulationRepository.saveAndFlush(mLeagueRegulation);

        // Get all the mLeagueRegulationList where deckConditionId greater than or equals to DEFAULT_DECK_CONDITION_ID
        defaultMLeagueRegulationShouldBeFound("deckConditionId.greaterOrEqualThan=" + DEFAULT_DECK_CONDITION_ID);

        // Get all the mLeagueRegulationList where deckConditionId greater than or equals to UPDATED_DECK_CONDITION_ID
        defaultMLeagueRegulationShouldNotBeFound("deckConditionId.greaterOrEqualThan=" + UPDATED_DECK_CONDITION_ID);
    }

    @Test
    @Transactional
    public void getAllMLeagueRegulationsByDeckConditionIdIsLessThanSomething() throws Exception {
        // Initialize the database
        mLeagueRegulationRepository.saveAndFlush(mLeagueRegulation);

        // Get all the mLeagueRegulationList where deckConditionId less than or equals to DEFAULT_DECK_CONDITION_ID
        defaultMLeagueRegulationShouldNotBeFound("deckConditionId.lessThan=" + DEFAULT_DECK_CONDITION_ID);

        // Get all the mLeagueRegulationList where deckConditionId less than or equals to UPDATED_DECK_CONDITION_ID
        defaultMLeagueRegulationShouldBeFound("deckConditionId.lessThan=" + UPDATED_DECK_CONDITION_ID);
    }


    @Test
    @Transactional
    public void getAllMLeagueRegulationsByRuleTutorialIdIsEqualToSomething() throws Exception {
        // Initialize the database
        mLeagueRegulationRepository.saveAndFlush(mLeagueRegulation);

        // Get all the mLeagueRegulationList where ruleTutorialId equals to DEFAULT_RULE_TUTORIAL_ID
        defaultMLeagueRegulationShouldBeFound("ruleTutorialId.equals=" + DEFAULT_RULE_TUTORIAL_ID);

        // Get all the mLeagueRegulationList where ruleTutorialId equals to UPDATED_RULE_TUTORIAL_ID
        defaultMLeagueRegulationShouldNotBeFound("ruleTutorialId.equals=" + UPDATED_RULE_TUTORIAL_ID);
    }

    @Test
    @Transactional
    public void getAllMLeagueRegulationsByRuleTutorialIdIsInShouldWork() throws Exception {
        // Initialize the database
        mLeagueRegulationRepository.saveAndFlush(mLeagueRegulation);

        // Get all the mLeagueRegulationList where ruleTutorialId in DEFAULT_RULE_TUTORIAL_ID or UPDATED_RULE_TUTORIAL_ID
        defaultMLeagueRegulationShouldBeFound("ruleTutorialId.in=" + DEFAULT_RULE_TUTORIAL_ID + "," + UPDATED_RULE_TUTORIAL_ID);

        // Get all the mLeagueRegulationList where ruleTutorialId equals to UPDATED_RULE_TUTORIAL_ID
        defaultMLeagueRegulationShouldNotBeFound("ruleTutorialId.in=" + UPDATED_RULE_TUTORIAL_ID);
    }

    @Test
    @Transactional
    public void getAllMLeagueRegulationsByRuleTutorialIdIsNullOrNotNull() throws Exception {
        // Initialize the database
        mLeagueRegulationRepository.saveAndFlush(mLeagueRegulation);

        // Get all the mLeagueRegulationList where ruleTutorialId is not null
        defaultMLeagueRegulationShouldBeFound("ruleTutorialId.specified=true");

        // Get all the mLeagueRegulationList where ruleTutorialId is null
        defaultMLeagueRegulationShouldNotBeFound("ruleTutorialId.specified=false");
    }

    @Test
    @Transactional
    public void getAllMLeagueRegulationsByRuleTutorialIdIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mLeagueRegulationRepository.saveAndFlush(mLeagueRegulation);

        // Get all the mLeagueRegulationList where ruleTutorialId greater than or equals to DEFAULT_RULE_TUTORIAL_ID
        defaultMLeagueRegulationShouldBeFound("ruleTutorialId.greaterOrEqualThan=" + DEFAULT_RULE_TUTORIAL_ID);

        // Get all the mLeagueRegulationList where ruleTutorialId greater than or equals to UPDATED_RULE_TUTORIAL_ID
        defaultMLeagueRegulationShouldNotBeFound("ruleTutorialId.greaterOrEqualThan=" + UPDATED_RULE_TUTORIAL_ID);
    }

    @Test
    @Transactional
    public void getAllMLeagueRegulationsByRuleTutorialIdIsLessThanSomething() throws Exception {
        // Initialize the database
        mLeagueRegulationRepository.saveAndFlush(mLeagueRegulation);

        // Get all the mLeagueRegulationList where ruleTutorialId less than or equals to DEFAULT_RULE_TUTORIAL_ID
        defaultMLeagueRegulationShouldNotBeFound("ruleTutorialId.lessThan=" + DEFAULT_RULE_TUTORIAL_ID);

        // Get all the mLeagueRegulationList where ruleTutorialId less than or equals to UPDATED_RULE_TUTORIAL_ID
        defaultMLeagueRegulationShouldBeFound("ruleTutorialId.lessThan=" + UPDATED_RULE_TUTORIAL_ID);
    }


    @Test
    @Transactional
    public void getAllMLeagueRegulationsByIdIsEqualToSomething() throws Exception {
        // Get already existing entity
        MMatchOption id = mLeagueRegulation.getId();
        mLeagueRegulationRepository.saveAndFlush(mLeagueRegulation);
        Long idId = id.getId();

        // Get all the mLeagueRegulationList where id equals to idId
        defaultMLeagueRegulationShouldBeFound("idId.equals=" + idId);

        // Get all the mLeagueRegulationList where id equals to idId + 1
        defaultMLeagueRegulationShouldNotBeFound("idId.equals=" + (idId + 1));
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultMLeagueRegulationShouldBeFound(String filter) throws Exception {
        restMLeagueRegulationMockMvc.perform(get("/api/m-league-regulations?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(mLeagueRegulation.getId().intValue())))
            .andExpect(jsonPath("$.[*].startAt").value(hasItem(DEFAULT_START_AT)))
            .andExpect(jsonPath("$.[*].endAt").value(hasItem(DEFAULT_END_AT)))
            .andExpect(jsonPath("$.[*].matchOptionId").value(hasItem(DEFAULT_MATCH_OPTION_ID)))
            .andExpect(jsonPath("$.[*].deckConditionId").value(hasItem(DEFAULT_DECK_CONDITION_ID)))
            .andExpect(jsonPath("$.[*].ruleTutorialId").value(hasItem(DEFAULT_RULE_TUTORIAL_ID)));

        // Check, that the count call also returns 1
        restMLeagueRegulationMockMvc.perform(get("/api/m-league-regulations/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultMLeagueRegulationShouldNotBeFound(String filter) throws Exception {
        restMLeagueRegulationMockMvc.perform(get("/api/m-league-regulations?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restMLeagueRegulationMockMvc.perform(get("/api/m-league-regulations/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("0"));
    }


    @Test
    @Transactional
    public void getNonExistingMLeagueRegulation() throws Exception {
        // Get the mLeagueRegulation
        restMLeagueRegulationMockMvc.perform(get("/api/m-league-regulations/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateMLeagueRegulation() throws Exception {
        // Initialize the database
        mLeagueRegulationRepository.saveAndFlush(mLeagueRegulation);

        int databaseSizeBeforeUpdate = mLeagueRegulationRepository.findAll().size();

        // Update the mLeagueRegulation
        MLeagueRegulation updatedMLeagueRegulation = mLeagueRegulationRepository.findById(mLeagueRegulation.getId()).get();
        // Disconnect from session so that the updates on updatedMLeagueRegulation are not directly saved in db
        em.detach(updatedMLeagueRegulation);
        updatedMLeagueRegulation
            .startAt(UPDATED_START_AT)
            .endAt(UPDATED_END_AT)
            .matchOptionId(UPDATED_MATCH_OPTION_ID)
            .deckConditionId(UPDATED_DECK_CONDITION_ID)
            .ruleTutorialId(UPDATED_RULE_TUTORIAL_ID);
        MLeagueRegulationDTO mLeagueRegulationDTO = mLeagueRegulationMapper.toDto(updatedMLeagueRegulation);

        restMLeagueRegulationMockMvc.perform(put("/api/m-league-regulations")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mLeagueRegulationDTO)))
            .andExpect(status().isOk());

        // Validate the MLeagueRegulation in the database
        List<MLeagueRegulation> mLeagueRegulationList = mLeagueRegulationRepository.findAll();
        assertThat(mLeagueRegulationList).hasSize(databaseSizeBeforeUpdate);
        MLeagueRegulation testMLeagueRegulation = mLeagueRegulationList.get(mLeagueRegulationList.size() - 1);
        assertThat(testMLeagueRegulation.getStartAt()).isEqualTo(UPDATED_START_AT);
        assertThat(testMLeagueRegulation.getEndAt()).isEqualTo(UPDATED_END_AT);
        assertThat(testMLeagueRegulation.getMatchOptionId()).isEqualTo(UPDATED_MATCH_OPTION_ID);
        assertThat(testMLeagueRegulation.getDeckConditionId()).isEqualTo(UPDATED_DECK_CONDITION_ID);
        assertThat(testMLeagueRegulation.getRuleTutorialId()).isEqualTo(UPDATED_RULE_TUTORIAL_ID);
    }

    @Test
    @Transactional
    public void updateNonExistingMLeagueRegulation() throws Exception {
        int databaseSizeBeforeUpdate = mLeagueRegulationRepository.findAll().size();

        // Create the MLeagueRegulation
        MLeagueRegulationDTO mLeagueRegulationDTO = mLeagueRegulationMapper.toDto(mLeagueRegulation);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restMLeagueRegulationMockMvc.perform(put("/api/m-league-regulations")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mLeagueRegulationDTO)))
            .andExpect(status().isBadRequest());

        // Validate the MLeagueRegulation in the database
        List<MLeagueRegulation> mLeagueRegulationList = mLeagueRegulationRepository.findAll();
        assertThat(mLeagueRegulationList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteMLeagueRegulation() throws Exception {
        // Initialize the database
        mLeagueRegulationRepository.saveAndFlush(mLeagueRegulation);

        int databaseSizeBeforeDelete = mLeagueRegulationRepository.findAll().size();

        // Delete the mLeagueRegulation
        restMLeagueRegulationMockMvc.perform(delete("/api/m-league-regulations/{id}", mLeagueRegulation.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database is empty
        List<MLeagueRegulation> mLeagueRegulationList = mLeagueRegulationRepository.findAll();
        assertThat(mLeagueRegulationList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(MLeagueRegulation.class);
        MLeagueRegulation mLeagueRegulation1 = new MLeagueRegulation();
        mLeagueRegulation1.setId(1L);
        MLeagueRegulation mLeagueRegulation2 = new MLeagueRegulation();
        mLeagueRegulation2.setId(mLeagueRegulation1.getId());
        assertThat(mLeagueRegulation1).isEqualTo(mLeagueRegulation2);
        mLeagueRegulation2.setId(2L);
        assertThat(mLeagueRegulation1).isNotEqualTo(mLeagueRegulation2);
        mLeagueRegulation1.setId(null);
        assertThat(mLeagueRegulation1).isNotEqualTo(mLeagueRegulation2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(MLeagueRegulationDTO.class);
        MLeagueRegulationDTO mLeagueRegulationDTO1 = new MLeagueRegulationDTO();
        mLeagueRegulationDTO1.setId(1L);
        MLeagueRegulationDTO mLeagueRegulationDTO2 = new MLeagueRegulationDTO();
        assertThat(mLeagueRegulationDTO1).isNotEqualTo(mLeagueRegulationDTO2);
        mLeagueRegulationDTO2.setId(mLeagueRegulationDTO1.getId());
        assertThat(mLeagueRegulationDTO1).isEqualTo(mLeagueRegulationDTO2);
        mLeagueRegulationDTO2.setId(2L);
        assertThat(mLeagueRegulationDTO1).isNotEqualTo(mLeagueRegulationDTO2);
        mLeagueRegulationDTO1.setId(null);
        assertThat(mLeagueRegulationDTO1).isNotEqualTo(mLeagueRegulationDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(mLeagueRegulationMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(mLeagueRegulationMapper.fromId(null)).isNull();
    }
}
