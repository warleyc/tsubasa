package io.shm.tsubasa.web.rest;

import io.shm.tsubasa.TsubasaApp;
import io.shm.tsubasa.domain.MCombinationCutPosition;
import io.shm.tsubasa.domain.MCharacter;
import io.shm.tsubasa.repository.MCombinationCutPositionRepository;
import io.shm.tsubasa.service.MCombinationCutPositionService;
import io.shm.tsubasa.service.dto.MCombinationCutPositionDTO;
import io.shm.tsubasa.service.mapper.MCombinationCutPositionMapper;
import io.shm.tsubasa.web.rest.errors.ExceptionTranslator;
import io.shm.tsubasa.service.dto.MCombinationCutPositionCriteria;
import io.shm.tsubasa.service.MCombinationCutPositionQueryService;

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
 * Integration tests for the {@Link MCombinationCutPositionResource} REST controller.
 */
@SpringBootTest(classes = TsubasaApp.class)
public class MCombinationCutPositionResourceIT {

    private static final Integer DEFAULT_ACTION_CUT_ID = 1;
    private static final Integer UPDATED_ACTION_CUT_ID = 2;

    private static final Integer DEFAULT_CHARACTER_ID = 1;
    private static final Integer UPDATED_CHARACTER_ID = 2;

    private static final Integer DEFAULT_ACTIVATOR_POSITION = 1;
    private static final Integer UPDATED_ACTIVATOR_POSITION = 2;

    private static final Integer DEFAULT_PARTICIPANT_POSITION_1 = 1;
    private static final Integer UPDATED_PARTICIPANT_POSITION_1 = 2;

    private static final Integer DEFAULT_PARTICIPANT_POSITION_2 = 1;
    private static final Integer UPDATED_PARTICIPANT_POSITION_2 = 2;

    private static final Integer DEFAULT_PARTICIPANT_POSITION_3 = 1;
    private static final Integer UPDATED_PARTICIPANT_POSITION_3 = 2;

    private static final Integer DEFAULT_PARTICIPANT_POSITION_4 = 1;
    private static final Integer UPDATED_PARTICIPANT_POSITION_4 = 2;

    private static final Integer DEFAULT_PARTICIPANT_POSITION_5 = 1;
    private static final Integer UPDATED_PARTICIPANT_POSITION_5 = 2;

    @Autowired
    private MCombinationCutPositionRepository mCombinationCutPositionRepository;

    @Autowired
    private MCombinationCutPositionMapper mCombinationCutPositionMapper;

    @Autowired
    private MCombinationCutPositionService mCombinationCutPositionService;

    @Autowired
    private MCombinationCutPositionQueryService mCombinationCutPositionQueryService;

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

    private MockMvc restMCombinationCutPositionMockMvc;

    private MCombinationCutPosition mCombinationCutPosition;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final MCombinationCutPositionResource mCombinationCutPositionResource = new MCombinationCutPositionResource(mCombinationCutPositionService, mCombinationCutPositionQueryService);
        this.restMCombinationCutPositionMockMvc = MockMvcBuilders.standaloneSetup(mCombinationCutPositionResource)
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
    public static MCombinationCutPosition createEntity(EntityManager em) {
        MCombinationCutPosition mCombinationCutPosition = new MCombinationCutPosition()
            .actionCutId(DEFAULT_ACTION_CUT_ID)
            .characterId(DEFAULT_CHARACTER_ID)
            .activatorPosition(DEFAULT_ACTIVATOR_POSITION)
            .participantPosition1(DEFAULT_PARTICIPANT_POSITION_1)
            .participantPosition2(DEFAULT_PARTICIPANT_POSITION_2)
            .participantPosition3(DEFAULT_PARTICIPANT_POSITION_3)
            .participantPosition4(DEFAULT_PARTICIPANT_POSITION_4)
            .participantPosition5(DEFAULT_PARTICIPANT_POSITION_5);
        // Add required entity
        MCharacter mCharacter;
        if (TestUtil.findAll(em, MCharacter.class).isEmpty()) {
            mCharacter = MCharacterResourceIT.createEntity(em);
            em.persist(mCharacter);
            em.flush();
        } else {
            mCharacter = TestUtil.findAll(em, MCharacter.class).get(0);
        }
        mCombinationCutPosition.setId(mCharacter);
        return mCombinationCutPosition;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static MCombinationCutPosition createUpdatedEntity(EntityManager em) {
        MCombinationCutPosition mCombinationCutPosition = new MCombinationCutPosition()
            .actionCutId(UPDATED_ACTION_CUT_ID)
            .characterId(UPDATED_CHARACTER_ID)
            .activatorPosition(UPDATED_ACTIVATOR_POSITION)
            .participantPosition1(UPDATED_PARTICIPANT_POSITION_1)
            .participantPosition2(UPDATED_PARTICIPANT_POSITION_2)
            .participantPosition3(UPDATED_PARTICIPANT_POSITION_3)
            .participantPosition4(UPDATED_PARTICIPANT_POSITION_4)
            .participantPosition5(UPDATED_PARTICIPANT_POSITION_5);
        // Add required entity
        MCharacter mCharacter;
        if (TestUtil.findAll(em, MCharacter.class).isEmpty()) {
            mCharacter = MCharacterResourceIT.createUpdatedEntity(em);
            em.persist(mCharacter);
            em.flush();
        } else {
            mCharacter = TestUtil.findAll(em, MCharacter.class).get(0);
        }
        mCombinationCutPosition.setId(mCharacter);
        return mCombinationCutPosition;
    }

    @BeforeEach
    public void initTest() {
        mCombinationCutPosition = createEntity(em);
    }

    @Test
    @Transactional
    public void createMCombinationCutPosition() throws Exception {
        int databaseSizeBeforeCreate = mCombinationCutPositionRepository.findAll().size();

        // Create the MCombinationCutPosition
        MCombinationCutPositionDTO mCombinationCutPositionDTO = mCombinationCutPositionMapper.toDto(mCombinationCutPosition);
        restMCombinationCutPositionMockMvc.perform(post("/api/m-combination-cut-positions")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mCombinationCutPositionDTO)))
            .andExpect(status().isCreated());

        // Validate the MCombinationCutPosition in the database
        List<MCombinationCutPosition> mCombinationCutPositionList = mCombinationCutPositionRepository.findAll();
        assertThat(mCombinationCutPositionList).hasSize(databaseSizeBeforeCreate + 1);
        MCombinationCutPosition testMCombinationCutPosition = mCombinationCutPositionList.get(mCombinationCutPositionList.size() - 1);
        assertThat(testMCombinationCutPosition.getActionCutId()).isEqualTo(DEFAULT_ACTION_CUT_ID);
        assertThat(testMCombinationCutPosition.getCharacterId()).isEqualTo(DEFAULT_CHARACTER_ID);
        assertThat(testMCombinationCutPosition.getActivatorPosition()).isEqualTo(DEFAULT_ACTIVATOR_POSITION);
        assertThat(testMCombinationCutPosition.getParticipantPosition1()).isEqualTo(DEFAULT_PARTICIPANT_POSITION_1);
        assertThat(testMCombinationCutPosition.getParticipantPosition2()).isEqualTo(DEFAULT_PARTICIPANT_POSITION_2);
        assertThat(testMCombinationCutPosition.getParticipantPosition3()).isEqualTo(DEFAULT_PARTICIPANT_POSITION_3);
        assertThat(testMCombinationCutPosition.getParticipantPosition4()).isEqualTo(DEFAULT_PARTICIPANT_POSITION_4);
        assertThat(testMCombinationCutPosition.getParticipantPosition5()).isEqualTo(DEFAULT_PARTICIPANT_POSITION_5);
    }

    @Test
    @Transactional
    public void createMCombinationCutPositionWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = mCombinationCutPositionRepository.findAll().size();

        // Create the MCombinationCutPosition with an existing ID
        mCombinationCutPosition.setId(1L);
        MCombinationCutPositionDTO mCombinationCutPositionDTO = mCombinationCutPositionMapper.toDto(mCombinationCutPosition);

        // An entity with an existing ID cannot be created, so this API call must fail
        restMCombinationCutPositionMockMvc.perform(post("/api/m-combination-cut-positions")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mCombinationCutPositionDTO)))
            .andExpect(status().isBadRequest());

        // Validate the MCombinationCutPosition in the database
        List<MCombinationCutPosition> mCombinationCutPositionList = mCombinationCutPositionRepository.findAll();
        assertThat(mCombinationCutPositionList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkActionCutIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = mCombinationCutPositionRepository.findAll().size();
        // set the field null
        mCombinationCutPosition.setActionCutId(null);

        // Create the MCombinationCutPosition, which fails.
        MCombinationCutPositionDTO mCombinationCutPositionDTO = mCombinationCutPositionMapper.toDto(mCombinationCutPosition);

        restMCombinationCutPositionMockMvc.perform(post("/api/m-combination-cut-positions")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mCombinationCutPositionDTO)))
            .andExpect(status().isBadRequest());

        List<MCombinationCutPosition> mCombinationCutPositionList = mCombinationCutPositionRepository.findAll();
        assertThat(mCombinationCutPositionList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkCharacterIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = mCombinationCutPositionRepository.findAll().size();
        // set the field null
        mCombinationCutPosition.setCharacterId(null);

        // Create the MCombinationCutPosition, which fails.
        MCombinationCutPositionDTO mCombinationCutPositionDTO = mCombinationCutPositionMapper.toDto(mCombinationCutPosition);

        restMCombinationCutPositionMockMvc.perform(post("/api/m-combination-cut-positions")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mCombinationCutPositionDTO)))
            .andExpect(status().isBadRequest());

        List<MCombinationCutPosition> mCombinationCutPositionList = mCombinationCutPositionRepository.findAll();
        assertThat(mCombinationCutPositionList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkActivatorPositionIsRequired() throws Exception {
        int databaseSizeBeforeTest = mCombinationCutPositionRepository.findAll().size();
        // set the field null
        mCombinationCutPosition.setActivatorPosition(null);

        // Create the MCombinationCutPosition, which fails.
        MCombinationCutPositionDTO mCombinationCutPositionDTO = mCombinationCutPositionMapper.toDto(mCombinationCutPosition);

        restMCombinationCutPositionMockMvc.perform(post("/api/m-combination-cut-positions")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mCombinationCutPositionDTO)))
            .andExpect(status().isBadRequest());

        List<MCombinationCutPosition> mCombinationCutPositionList = mCombinationCutPositionRepository.findAll();
        assertThat(mCombinationCutPositionList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkParticipantPosition1IsRequired() throws Exception {
        int databaseSizeBeforeTest = mCombinationCutPositionRepository.findAll().size();
        // set the field null
        mCombinationCutPosition.setParticipantPosition1(null);

        // Create the MCombinationCutPosition, which fails.
        MCombinationCutPositionDTO mCombinationCutPositionDTO = mCombinationCutPositionMapper.toDto(mCombinationCutPosition);

        restMCombinationCutPositionMockMvc.perform(post("/api/m-combination-cut-positions")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mCombinationCutPositionDTO)))
            .andExpect(status().isBadRequest());

        List<MCombinationCutPosition> mCombinationCutPositionList = mCombinationCutPositionRepository.findAll();
        assertThat(mCombinationCutPositionList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkParticipantPosition2IsRequired() throws Exception {
        int databaseSizeBeforeTest = mCombinationCutPositionRepository.findAll().size();
        // set the field null
        mCombinationCutPosition.setParticipantPosition2(null);

        // Create the MCombinationCutPosition, which fails.
        MCombinationCutPositionDTO mCombinationCutPositionDTO = mCombinationCutPositionMapper.toDto(mCombinationCutPosition);

        restMCombinationCutPositionMockMvc.perform(post("/api/m-combination-cut-positions")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mCombinationCutPositionDTO)))
            .andExpect(status().isBadRequest());

        List<MCombinationCutPosition> mCombinationCutPositionList = mCombinationCutPositionRepository.findAll();
        assertThat(mCombinationCutPositionList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkParticipantPosition3IsRequired() throws Exception {
        int databaseSizeBeforeTest = mCombinationCutPositionRepository.findAll().size();
        // set the field null
        mCombinationCutPosition.setParticipantPosition3(null);

        // Create the MCombinationCutPosition, which fails.
        MCombinationCutPositionDTO mCombinationCutPositionDTO = mCombinationCutPositionMapper.toDto(mCombinationCutPosition);

        restMCombinationCutPositionMockMvc.perform(post("/api/m-combination-cut-positions")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mCombinationCutPositionDTO)))
            .andExpect(status().isBadRequest());

        List<MCombinationCutPosition> mCombinationCutPositionList = mCombinationCutPositionRepository.findAll();
        assertThat(mCombinationCutPositionList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkParticipantPosition4IsRequired() throws Exception {
        int databaseSizeBeforeTest = mCombinationCutPositionRepository.findAll().size();
        // set the field null
        mCombinationCutPosition.setParticipantPosition4(null);

        // Create the MCombinationCutPosition, which fails.
        MCombinationCutPositionDTO mCombinationCutPositionDTO = mCombinationCutPositionMapper.toDto(mCombinationCutPosition);

        restMCombinationCutPositionMockMvc.perform(post("/api/m-combination-cut-positions")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mCombinationCutPositionDTO)))
            .andExpect(status().isBadRequest());

        List<MCombinationCutPosition> mCombinationCutPositionList = mCombinationCutPositionRepository.findAll();
        assertThat(mCombinationCutPositionList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkParticipantPosition5IsRequired() throws Exception {
        int databaseSizeBeforeTest = mCombinationCutPositionRepository.findAll().size();
        // set the field null
        mCombinationCutPosition.setParticipantPosition5(null);

        // Create the MCombinationCutPosition, which fails.
        MCombinationCutPositionDTO mCombinationCutPositionDTO = mCombinationCutPositionMapper.toDto(mCombinationCutPosition);

        restMCombinationCutPositionMockMvc.perform(post("/api/m-combination-cut-positions")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mCombinationCutPositionDTO)))
            .andExpect(status().isBadRequest());

        List<MCombinationCutPosition> mCombinationCutPositionList = mCombinationCutPositionRepository.findAll();
        assertThat(mCombinationCutPositionList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllMCombinationCutPositions() throws Exception {
        // Initialize the database
        mCombinationCutPositionRepository.saveAndFlush(mCombinationCutPosition);

        // Get all the mCombinationCutPositionList
        restMCombinationCutPositionMockMvc.perform(get("/api/m-combination-cut-positions?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(mCombinationCutPosition.getId().intValue())))
            .andExpect(jsonPath("$.[*].actionCutId").value(hasItem(DEFAULT_ACTION_CUT_ID)))
            .andExpect(jsonPath("$.[*].characterId").value(hasItem(DEFAULT_CHARACTER_ID)))
            .andExpect(jsonPath("$.[*].activatorPosition").value(hasItem(DEFAULT_ACTIVATOR_POSITION)))
            .andExpect(jsonPath("$.[*].participantPosition1").value(hasItem(DEFAULT_PARTICIPANT_POSITION_1)))
            .andExpect(jsonPath("$.[*].participantPosition2").value(hasItem(DEFAULT_PARTICIPANT_POSITION_2)))
            .andExpect(jsonPath("$.[*].participantPosition3").value(hasItem(DEFAULT_PARTICIPANT_POSITION_3)))
            .andExpect(jsonPath("$.[*].participantPosition4").value(hasItem(DEFAULT_PARTICIPANT_POSITION_4)))
            .andExpect(jsonPath("$.[*].participantPosition5").value(hasItem(DEFAULT_PARTICIPANT_POSITION_5)));
    }
    
    @Test
    @Transactional
    public void getMCombinationCutPosition() throws Exception {
        // Initialize the database
        mCombinationCutPositionRepository.saveAndFlush(mCombinationCutPosition);

        // Get the mCombinationCutPosition
        restMCombinationCutPositionMockMvc.perform(get("/api/m-combination-cut-positions/{id}", mCombinationCutPosition.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(mCombinationCutPosition.getId().intValue()))
            .andExpect(jsonPath("$.actionCutId").value(DEFAULT_ACTION_CUT_ID))
            .andExpect(jsonPath("$.characterId").value(DEFAULT_CHARACTER_ID))
            .andExpect(jsonPath("$.activatorPosition").value(DEFAULT_ACTIVATOR_POSITION))
            .andExpect(jsonPath("$.participantPosition1").value(DEFAULT_PARTICIPANT_POSITION_1))
            .andExpect(jsonPath("$.participantPosition2").value(DEFAULT_PARTICIPANT_POSITION_2))
            .andExpect(jsonPath("$.participantPosition3").value(DEFAULT_PARTICIPANT_POSITION_3))
            .andExpect(jsonPath("$.participantPosition4").value(DEFAULT_PARTICIPANT_POSITION_4))
            .andExpect(jsonPath("$.participantPosition5").value(DEFAULT_PARTICIPANT_POSITION_5));
    }

    @Test
    @Transactional
    public void getAllMCombinationCutPositionsByActionCutIdIsEqualToSomething() throws Exception {
        // Initialize the database
        mCombinationCutPositionRepository.saveAndFlush(mCombinationCutPosition);

        // Get all the mCombinationCutPositionList where actionCutId equals to DEFAULT_ACTION_CUT_ID
        defaultMCombinationCutPositionShouldBeFound("actionCutId.equals=" + DEFAULT_ACTION_CUT_ID);

        // Get all the mCombinationCutPositionList where actionCutId equals to UPDATED_ACTION_CUT_ID
        defaultMCombinationCutPositionShouldNotBeFound("actionCutId.equals=" + UPDATED_ACTION_CUT_ID);
    }

    @Test
    @Transactional
    public void getAllMCombinationCutPositionsByActionCutIdIsInShouldWork() throws Exception {
        // Initialize the database
        mCombinationCutPositionRepository.saveAndFlush(mCombinationCutPosition);

        // Get all the mCombinationCutPositionList where actionCutId in DEFAULT_ACTION_CUT_ID or UPDATED_ACTION_CUT_ID
        defaultMCombinationCutPositionShouldBeFound("actionCutId.in=" + DEFAULT_ACTION_CUT_ID + "," + UPDATED_ACTION_CUT_ID);

        // Get all the mCombinationCutPositionList where actionCutId equals to UPDATED_ACTION_CUT_ID
        defaultMCombinationCutPositionShouldNotBeFound("actionCutId.in=" + UPDATED_ACTION_CUT_ID);
    }

    @Test
    @Transactional
    public void getAllMCombinationCutPositionsByActionCutIdIsNullOrNotNull() throws Exception {
        // Initialize the database
        mCombinationCutPositionRepository.saveAndFlush(mCombinationCutPosition);

        // Get all the mCombinationCutPositionList where actionCutId is not null
        defaultMCombinationCutPositionShouldBeFound("actionCutId.specified=true");

        // Get all the mCombinationCutPositionList where actionCutId is null
        defaultMCombinationCutPositionShouldNotBeFound("actionCutId.specified=false");
    }

    @Test
    @Transactional
    public void getAllMCombinationCutPositionsByActionCutIdIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mCombinationCutPositionRepository.saveAndFlush(mCombinationCutPosition);

        // Get all the mCombinationCutPositionList where actionCutId greater than or equals to DEFAULT_ACTION_CUT_ID
        defaultMCombinationCutPositionShouldBeFound("actionCutId.greaterOrEqualThan=" + DEFAULT_ACTION_CUT_ID);

        // Get all the mCombinationCutPositionList where actionCutId greater than or equals to UPDATED_ACTION_CUT_ID
        defaultMCombinationCutPositionShouldNotBeFound("actionCutId.greaterOrEqualThan=" + UPDATED_ACTION_CUT_ID);
    }

    @Test
    @Transactional
    public void getAllMCombinationCutPositionsByActionCutIdIsLessThanSomething() throws Exception {
        // Initialize the database
        mCombinationCutPositionRepository.saveAndFlush(mCombinationCutPosition);

        // Get all the mCombinationCutPositionList where actionCutId less than or equals to DEFAULT_ACTION_CUT_ID
        defaultMCombinationCutPositionShouldNotBeFound("actionCutId.lessThan=" + DEFAULT_ACTION_CUT_ID);

        // Get all the mCombinationCutPositionList where actionCutId less than or equals to UPDATED_ACTION_CUT_ID
        defaultMCombinationCutPositionShouldBeFound("actionCutId.lessThan=" + UPDATED_ACTION_CUT_ID);
    }


    @Test
    @Transactional
    public void getAllMCombinationCutPositionsByCharacterIdIsEqualToSomething() throws Exception {
        // Initialize the database
        mCombinationCutPositionRepository.saveAndFlush(mCombinationCutPosition);

        // Get all the mCombinationCutPositionList where characterId equals to DEFAULT_CHARACTER_ID
        defaultMCombinationCutPositionShouldBeFound("characterId.equals=" + DEFAULT_CHARACTER_ID);

        // Get all the mCombinationCutPositionList where characterId equals to UPDATED_CHARACTER_ID
        defaultMCombinationCutPositionShouldNotBeFound("characterId.equals=" + UPDATED_CHARACTER_ID);
    }

    @Test
    @Transactional
    public void getAllMCombinationCutPositionsByCharacterIdIsInShouldWork() throws Exception {
        // Initialize the database
        mCombinationCutPositionRepository.saveAndFlush(mCombinationCutPosition);

        // Get all the mCombinationCutPositionList where characterId in DEFAULT_CHARACTER_ID or UPDATED_CHARACTER_ID
        defaultMCombinationCutPositionShouldBeFound("characterId.in=" + DEFAULT_CHARACTER_ID + "," + UPDATED_CHARACTER_ID);

        // Get all the mCombinationCutPositionList where characterId equals to UPDATED_CHARACTER_ID
        defaultMCombinationCutPositionShouldNotBeFound("characterId.in=" + UPDATED_CHARACTER_ID);
    }

    @Test
    @Transactional
    public void getAllMCombinationCutPositionsByCharacterIdIsNullOrNotNull() throws Exception {
        // Initialize the database
        mCombinationCutPositionRepository.saveAndFlush(mCombinationCutPosition);

        // Get all the mCombinationCutPositionList where characterId is not null
        defaultMCombinationCutPositionShouldBeFound("characterId.specified=true");

        // Get all the mCombinationCutPositionList where characterId is null
        defaultMCombinationCutPositionShouldNotBeFound("characterId.specified=false");
    }

    @Test
    @Transactional
    public void getAllMCombinationCutPositionsByCharacterIdIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mCombinationCutPositionRepository.saveAndFlush(mCombinationCutPosition);

        // Get all the mCombinationCutPositionList where characterId greater than or equals to DEFAULT_CHARACTER_ID
        defaultMCombinationCutPositionShouldBeFound("characterId.greaterOrEqualThan=" + DEFAULT_CHARACTER_ID);

        // Get all the mCombinationCutPositionList where characterId greater than or equals to UPDATED_CHARACTER_ID
        defaultMCombinationCutPositionShouldNotBeFound("characterId.greaterOrEqualThan=" + UPDATED_CHARACTER_ID);
    }

    @Test
    @Transactional
    public void getAllMCombinationCutPositionsByCharacterIdIsLessThanSomething() throws Exception {
        // Initialize the database
        mCombinationCutPositionRepository.saveAndFlush(mCombinationCutPosition);

        // Get all the mCombinationCutPositionList where characterId less than or equals to DEFAULT_CHARACTER_ID
        defaultMCombinationCutPositionShouldNotBeFound("characterId.lessThan=" + DEFAULT_CHARACTER_ID);

        // Get all the mCombinationCutPositionList where characterId less than or equals to UPDATED_CHARACTER_ID
        defaultMCombinationCutPositionShouldBeFound("characterId.lessThan=" + UPDATED_CHARACTER_ID);
    }


    @Test
    @Transactional
    public void getAllMCombinationCutPositionsByActivatorPositionIsEqualToSomething() throws Exception {
        // Initialize the database
        mCombinationCutPositionRepository.saveAndFlush(mCombinationCutPosition);

        // Get all the mCombinationCutPositionList where activatorPosition equals to DEFAULT_ACTIVATOR_POSITION
        defaultMCombinationCutPositionShouldBeFound("activatorPosition.equals=" + DEFAULT_ACTIVATOR_POSITION);

        // Get all the mCombinationCutPositionList where activatorPosition equals to UPDATED_ACTIVATOR_POSITION
        defaultMCombinationCutPositionShouldNotBeFound("activatorPosition.equals=" + UPDATED_ACTIVATOR_POSITION);
    }

    @Test
    @Transactional
    public void getAllMCombinationCutPositionsByActivatorPositionIsInShouldWork() throws Exception {
        // Initialize the database
        mCombinationCutPositionRepository.saveAndFlush(mCombinationCutPosition);

        // Get all the mCombinationCutPositionList where activatorPosition in DEFAULT_ACTIVATOR_POSITION or UPDATED_ACTIVATOR_POSITION
        defaultMCombinationCutPositionShouldBeFound("activatorPosition.in=" + DEFAULT_ACTIVATOR_POSITION + "," + UPDATED_ACTIVATOR_POSITION);

        // Get all the mCombinationCutPositionList where activatorPosition equals to UPDATED_ACTIVATOR_POSITION
        defaultMCombinationCutPositionShouldNotBeFound("activatorPosition.in=" + UPDATED_ACTIVATOR_POSITION);
    }

    @Test
    @Transactional
    public void getAllMCombinationCutPositionsByActivatorPositionIsNullOrNotNull() throws Exception {
        // Initialize the database
        mCombinationCutPositionRepository.saveAndFlush(mCombinationCutPosition);

        // Get all the mCombinationCutPositionList where activatorPosition is not null
        defaultMCombinationCutPositionShouldBeFound("activatorPosition.specified=true");

        // Get all the mCombinationCutPositionList where activatorPosition is null
        defaultMCombinationCutPositionShouldNotBeFound("activatorPosition.specified=false");
    }

    @Test
    @Transactional
    public void getAllMCombinationCutPositionsByActivatorPositionIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mCombinationCutPositionRepository.saveAndFlush(mCombinationCutPosition);

        // Get all the mCombinationCutPositionList where activatorPosition greater than or equals to DEFAULT_ACTIVATOR_POSITION
        defaultMCombinationCutPositionShouldBeFound("activatorPosition.greaterOrEqualThan=" + DEFAULT_ACTIVATOR_POSITION);

        // Get all the mCombinationCutPositionList where activatorPosition greater than or equals to UPDATED_ACTIVATOR_POSITION
        defaultMCombinationCutPositionShouldNotBeFound("activatorPosition.greaterOrEqualThan=" + UPDATED_ACTIVATOR_POSITION);
    }

    @Test
    @Transactional
    public void getAllMCombinationCutPositionsByActivatorPositionIsLessThanSomething() throws Exception {
        // Initialize the database
        mCombinationCutPositionRepository.saveAndFlush(mCombinationCutPosition);

        // Get all the mCombinationCutPositionList where activatorPosition less than or equals to DEFAULT_ACTIVATOR_POSITION
        defaultMCombinationCutPositionShouldNotBeFound("activatorPosition.lessThan=" + DEFAULT_ACTIVATOR_POSITION);

        // Get all the mCombinationCutPositionList where activatorPosition less than or equals to UPDATED_ACTIVATOR_POSITION
        defaultMCombinationCutPositionShouldBeFound("activatorPosition.lessThan=" + UPDATED_ACTIVATOR_POSITION);
    }


    @Test
    @Transactional
    public void getAllMCombinationCutPositionsByParticipantPosition1IsEqualToSomething() throws Exception {
        // Initialize the database
        mCombinationCutPositionRepository.saveAndFlush(mCombinationCutPosition);

        // Get all the mCombinationCutPositionList where participantPosition1 equals to DEFAULT_PARTICIPANT_POSITION_1
        defaultMCombinationCutPositionShouldBeFound("participantPosition1.equals=" + DEFAULT_PARTICIPANT_POSITION_1);

        // Get all the mCombinationCutPositionList where participantPosition1 equals to UPDATED_PARTICIPANT_POSITION_1
        defaultMCombinationCutPositionShouldNotBeFound("participantPosition1.equals=" + UPDATED_PARTICIPANT_POSITION_1);
    }

    @Test
    @Transactional
    public void getAllMCombinationCutPositionsByParticipantPosition1IsInShouldWork() throws Exception {
        // Initialize the database
        mCombinationCutPositionRepository.saveAndFlush(mCombinationCutPosition);

        // Get all the mCombinationCutPositionList where participantPosition1 in DEFAULT_PARTICIPANT_POSITION_1 or UPDATED_PARTICIPANT_POSITION_1
        defaultMCombinationCutPositionShouldBeFound("participantPosition1.in=" + DEFAULT_PARTICIPANT_POSITION_1 + "," + UPDATED_PARTICIPANT_POSITION_1);

        // Get all the mCombinationCutPositionList where participantPosition1 equals to UPDATED_PARTICIPANT_POSITION_1
        defaultMCombinationCutPositionShouldNotBeFound("participantPosition1.in=" + UPDATED_PARTICIPANT_POSITION_1);
    }

    @Test
    @Transactional
    public void getAllMCombinationCutPositionsByParticipantPosition1IsNullOrNotNull() throws Exception {
        // Initialize the database
        mCombinationCutPositionRepository.saveAndFlush(mCombinationCutPosition);

        // Get all the mCombinationCutPositionList where participantPosition1 is not null
        defaultMCombinationCutPositionShouldBeFound("participantPosition1.specified=true");

        // Get all the mCombinationCutPositionList where participantPosition1 is null
        defaultMCombinationCutPositionShouldNotBeFound("participantPosition1.specified=false");
    }

    @Test
    @Transactional
    public void getAllMCombinationCutPositionsByParticipantPosition1IsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mCombinationCutPositionRepository.saveAndFlush(mCombinationCutPosition);

        // Get all the mCombinationCutPositionList where participantPosition1 greater than or equals to DEFAULT_PARTICIPANT_POSITION_1
        defaultMCombinationCutPositionShouldBeFound("participantPosition1.greaterOrEqualThan=" + DEFAULT_PARTICIPANT_POSITION_1);

        // Get all the mCombinationCutPositionList where participantPosition1 greater than or equals to UPDATED_PARTICIPANT_POSITION_1
        defaultMCombinationCutPositionShouldNotBeFound("participantPosition1.greaterOrEqualThan=" + UPDATED_PARTICIPANT_POSITION_1);
    }

    @Test
    @Transactional
    public void getAllMCombinationCutPositionsByParticipantPosition1IsLessThanSomething() throws Exception {
        // Initialize the database
        mCombinationCutPositionRepository.saveAndFlush(mCombinationCutPosition);

        // Get all the mCombinationCutPositionList where participantPosition1 less than or equals to DEFAULT_PARTICIPANT_POSITION_1
        defaultMCombinationCutPositionShouldNotBeFound("participantPosition1.lessThan=" + DEFAULT_PARTICIPANT_POSITION_1);

        // Get all the mCombinationCutPositionList where participantPosition1 less than or equals to UPDATED_PARTICIPANT_POSITION_1
        defaultMCombinationCutPositionShouldBeFound("participantPosition1.lessThan=" + UPDATED_PARTICIPANT_POSITION_1);
    }


    @Test
    @Transactional
    public void getAllMCombinationCutPositionsByParticipantPosition2IsEqualToSomething() throws Exception {
        // Initialize the database
        mCombinationCutPositionRepository.saveAndFlush(mCombinationCutPosition);

        // Get all the mCombinationCutPositionList where participantPosition2 equals to DEFAULT_PARTICIPANT_POSITION_2
        defaultMCombinationCutPositionShouldBeFound("participantPosition2.equals=" + DEFAULT_PARTICIPANT_POSITION_2);

        // Get all the mCombinationCutPositionList where participantPosition2 equals to UPDATED_PARTICIPANT_POSITION_2
        defaultMCombinationCutPositionShouldNotBeFound("participantPosition2.equals=" + UPDATED_PARTICIPANT_POSITION_2);
    }

    @Test
    @Transactional
    public void getAllMCombinationCutPositionsByParticipantPosition2IsInShouldWork() throws Exception {
        // Initialize the database
        mCombinationCutPositionRepository.saveAndFlush(mCombinationCutPosition);

        // Get all the mCombinationCutPositionList where participantPosition2 in DEFAULT_PARTICIPANT_POSITION_2 or UPDATED_PARTICIPANT_POSITION_2
        defaultMCombinationCutPositionShouldBeFound("participantPosition2.in=" + DEFAULT_PARTICIPANT_POSITION_2 + "," + UPDATED_PARTICIPANT_POSITION_2);

        // Get all the mCombinationCutPositionList where participantPosition2 equals to UPDATED_PARTICIPANT_POSITION_2
        defaultMCombinationCutPositionShouldNotBeFound("participantPosition2.in=" + UPDATED_PARTICIPANT_POSITION_2);
    }

    @Test
    @Transactional
    public void getAllMCombinationCutPositionsByParticipantPosition2IsNullOrNotNull() throws Exception {
        // Initialize the database
        mCombinationCutPositionRepository.saveAndFlush(mCombinationCutPosition);

        // Get all the mCombinationCutPositionList where participantPosition2 is not null
        defaultMCombinationCutPositionShouldBeFound("participantPosition2.specified=true");

        // Get all the mCombinationCutPositionList where participantPosition2 is null
        defaultMCombinationCutPositionShouldNotBeFound("participantPosition2.specified=false");
    }

    @Test
    @Transactional
    public void getAllMCombinationCutPositionsByParticipantPosition2IsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mCombinationCutPositionRepository.saveAndFlush(mCombinationCutPosition);

        // Get all the mCombinationCutPositionList where participantPosition2 greater than or equals to DEFAULT_PARTICIPANT_POSITION_2
        defaultMCombinationCutPositionShouldBeFound("participantPosition2.greaterOrEqualThan=" + DEFAULT_PARTICIPANT_POSITION_2);

        // Get all the mCombinationCutPositionList where participantPosition2 greater than or equals to UPDATED_PARTICIPANT_POSITION_2
        defaultMCombinationCutPositionShouldNotBeFound("participantPosition2.greaterOrEqualThan=" + UPDATED_PARTICIPANT_POSITION_2);
    }

    @Test
    @Transactional
    public void getAllMCombinationCutPositionsByParticipantPosition2IsLessThanSomething() throws Exception {
        // Initialize the database
        mCombinationCutPositionRepository.saveAndFlush(mCombinationCutPosition);

        // Get all the mCombinationCutPositionList where participantPosition2 less than or equals to DEFAULT_PARTICIPANT_POSITION_2
        defaultMCombinationCutPositionShouldNotBeFound("participantPosition2.lessThan=" + DEFAULT_PARTICIPANT_POSITION_2);

        // Get all the mCombinationCutPositionList where participantPosition2 less than or equals to UPDATED_PARTICIPANT_POSITION_2
        defaultMCombinationCutPositionShouldBeFound("participantPosition2.lessThan=" + UPDATED_PARTICIPANT_POSITION_2);
    }


    @Test
    @Transactional
    public void getAllMCombinationCutPositionsByParticipantPosition3IsEqualToSomething() throws Exception {
        // Initialize the database
        mCombinationCutPositionRepository.saveAndFlush(mCombinationCutPosition);

        // Get all the mCombinationCutPositionList where participantPosition3 equals to DEFAULT_PARTICIPANT_POSITION_3
        defaultMCombinationCutPositionShouldBeFound("participantPosition3.equals=" + DEFAULT_PARTICIPANT_POSITION_3);

        // Get all the mCombinationCutPositionList where participantPosition3 equals to UPDATED_PARTICIPANT_POSITION_3
        defaultMCombinationCutPositionShouldNotBeFound("participantPosition3.equals=" + UPDATED_PARTICIPANT_POSITION_3);
    }

    @Test
    @Transactional
    public void getAllMCombinationCutPositionsByParticipantPosition3IsInShouldWork() throws Exception {
        // Initialize the database
        mCombinationCutPositionRepository.saveAndFlush(mCombinationCutPosition);

        // Get all the mCombinationCutPositionList where participantPosition3 in DEFAULT_PARTICIPANT_POSITION_3 or UPDATED_PARTICIPANT_POSITION_3
        defaultMCombinationCutPositionShouldBeFound("participantPosition3.in=" + DEFAULT_PARTICIPANT_POSITION_3 + "," + UPDATED_PARTICIPANT_POSITION_3);

        // Get all the mCombinationCutPositionList where participantPosition3 equals to UPDATED_PARTICIPANT_POSITION_3
        defaultMCombinationCutPositionShouldNotBeFound("participantPosition3.in=" + UPDATED_PARTICIPANT_POSITION_3);
    }

    @Test
    @Transactional
    public void getAllMCombinationCutPositionsByParticipantPosition3IsNullOrNotNull() throws Exception {
        // Initialize the database
        mCombinationCutPositionRepository.saveAndFlush(mCombinationCutPosition);

        // Get all the mCombinationCutPositionList where participantPosition3 is not null
        defaultMCombinationCutPositionShouldBeFound("participantPosition3.specified=true");

        // Get all the mCombinationCutPositionList where participantPosition3 is null
        defaultMCombinationCutPositionShouldNotBeFound("participantPosition3.specified=false");
    }

    @Test
    @Transactional
    public void getAllMCombinationCutPositionsByParticipantPosition3IsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mCombinationCutPositionRepository.saveAndFlush(mCombinationCutPosition);

        // Get all the mCombinationCutPositionList where participantPosition3 greater than or equals to DEFAULT_PARTICIPANT_POSITION_3
        defaultMCombinationCutPositionShouldBeFound("participantPosition3.greaterOrEqualThan=" + DEFAULT_PARTICIPANT_POSITION_3);

        // Get all the mCombinationCutPositionList where participantPosition3 greater than or equals to UPDATED_PARTICIPANT_POSITION_3
        defaultMCombinationCutPositionShouldNotBeFound("participantPosition3.greaterOrEqualThan=" + UPDATED_PARTICIPANT_POSITION_3);
    }

    @Test
    @Transactional
    public void getAllMCombinationCutPositionsByParticipantPosition3IsLessThanSomething() throws Exception {
        // Initialize the database
        mCombinationCutPositionRepository.saveAndFlush(mCombinationCutPosition);

        // Get all the mCombinationCutPositionList where participantPosition3 less than or equals to DEFAULT_PARTICIPANT_POSITION_3
        defaultMCombinationCutPositionShouldNotBeFound("participantPosition3.lessThan=" + DEFAULT_PARTICIPANT_POSITION_3);

        // Get all the mCombinationCutPositionList where participantPosition3 less than or equals to UPDATED_PARTICIPANT_POSITION_3
        defaultMCombinationCutPositionShouldBeFound("participantPosition3.lessThan=" + UPDATED_PARTICIPANT_POSITION_3);
    }


    @Test
    @Transactional
    public void getAllMCombinationCutPositionsByParticipantPosition4IsEqualToSomething() throws Exception {
        // Initialize the database
        mCombinationCutPositionRepository.saveAndFlush(mCombinationCutPosition);

        // Get all the mCombinationCutPositionList where participantPosition4 equals to DEFAULT_PARTICIPANT_POSITION_4
        defaultMCombinationCutPositionShouldBeFound("participantPosition4.equals=" + DEFAULT_PARTICIPANT_POSITION_4);

        // Get all the mCombinationCutPositionList where participantPosition4 equals to UPDATED_PARTICIPANT_POSITION_4
        defaultMCombinationCutPositionShouldNotBeFound("participantPosition4.equals=" + UPDATED_PARTICIPANT_POSITION_4);
    }

    @Test
    @Transactional
    public void getAllMCombinationCutPositionsByParticipantPosition4IsInShouldWork() throws Exception {
        // Initialize the database
        mCombinationCutPositionRepository.saveAndFlush(mCombinationCutPosition);

        // Get all the mCombinationCutPositionList where participantPosition4 in DEFAULT_PARTICIPANT_POSITION_4 or UPDATED_PARTICIPANT_POSITION_4
        defaultMCombinationCutPositionShouldBeFound("participantPosition4.in=" + DEFAULT_PARTICIPANT_POSITION_4 + "," + UPDATED_PARTICIPANT_POSITION_4);

        // Get all the mCombinationCutPositionList where participantPosition4 equals to UPDATED_PARTICIPANT_POSITION_4
        defaultMCombinationCutPositionShouldNotBeFound("participantPosition4.in=" + UPDATED_PARTICIPANT_POSITION_4);
    }

    @Test
    @Transactional
    public void getAllMCombinationCutPositionsByParticipantPosition4IsNullOrNotNull() throws Exception {
        // Initialize the database
        mCombinationCutPositionRepository.saveAndFlush(mCombinationCutPosition);

        // Get all the mCombinationCutPositionList where participantPosition4 is not null
        defaultMCombinationCutPositionShouldBeFound("participantPosition4.specified=true");

        // Get all the mCombinationCutPositionList where participantPosition4 is null
        defaultMCombinationCutPositionShouldNotBeFound("participantPosition4.specified=false");
    }

    @Test
    @Transactional
    public void getAllMCombinationCutPositionsByParticipantPosition4IsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mCombinationCutPositionRepository.saveAndFlush(mCombinationCutPosition);

        // Get all the mCombinationCutPositionList where participantPosition4 greater than or equals to DEFAULT_PARTICIPANT_POSITION_4
        defaultMCombinationCutPositionShouldBeFound("participantPosition4.greaterOrEqualThan=" + DEFAULT_PARTICIPANT_POSITION_4);

        // Get all the mCombinationCutPositionList where participantPosition4 greater than or equals to UPDATED_PARTICIPANT_POSITION_4
        defaultMCombinationCutPositionShouldNotBeFound("participantPosition4.greaterOrEqualThan=" + UPDATED_PARTICIPANT_POSITION_4);
    }

    @Test
    @Transactional
    public void getAllMCombinationCutPositionsByParticipantPosition4IsLessThanSomething() throws Exception {
        // Initialize the database
        mCombinationCutPositionRepository.saveAndFlush(mCombinationCutPosition);

        // Get all the mCombinationCutPositionList where participantPosition4 less than or equals to DEFAULT_PARTICIPANT_POSITION_4
        defaultMCombinationCutPositionShouldNotBeFound("participantPosition4.lessThan=" + DEFAULT_PARTICIPANT_POSITION_4);

        // Get all the mCombinationCutPositionList where participantPosition4 less than or equals to UPDATED_PARTICIPANT_POSITION_4
        defaultMCombinationCutPositionShouldBeFound("participantPosition4.lessThan=" + UPDATED_PARTICIPANT_POSITION_4);
    }


    @Test
    @Transactional
    public void getAllMCombinationCutPositionsByParticipantPosition5IsEqualToSomething() throws Exception {
        // Initialize the database
        mCombinationCutPositionRepository.saveAndFlush(mCombinationCutPosition);

        // Get all the mCombinationCutPositionList where participantPosition5 equals to DEFAULT_PARTICIPANT_POSITION_5
        defaultMCombinationCutPositionShouldBeFound("participantPosition5.equals=" + DEFAULT_PARTICIPANT_POSITION_5);

        // Get all the mCombinationCutPositionList where participantPosition5 equals to UPDATED_PARTICIPANT_POSITION_5
        defaultMCombinationCutPositionShouldNotBeFound("participantPosition5.equals=" + UPDATED_PARTICIPANT_POSITION_5);
    }

    @Test
    @Transactional
    public void getAllMCombinationCutPositionsByParticipantPosition5IsInShouldWork() throws Exception {
        // Initialize the database
        mCombinationCutPositionRepository.saveAndFlush(mCombinationCutPosition);

        // Get all the mCombinationCutPositionList where participantPosition5 in DEFAULT_PARTICIPANT_POSITION_5 or UPDATED_PARTICIPANT_POSITION_5
        defaultMCombinationCutPositionShouldBeFound("participantPosition5.in=" + DEFAULT_PARTICIPANT_POSITION_5 + "," + UPDATED_PARTICIPANT_POSITION_5);

        // Get all the mCombinationCutPositionList where participantPosition5 equals to UPDATED_PARTICIPANT_POSITION_5
        defaultMCombinationCutPositionShouldNotBeFound("participantPosition5.in=" + UPDATED_PARTICIPANT_POSITION_5);
    }

    @Test
    @Transactional
    public void getAllMCombinationCutPositionsByParticipantPosition5IsNullOrNotNull() throws Exception {
        // Initialize the database
        mCombinationCutPositionRepository.saveAndFlush(mCombinationCutPosition);

        // Get all the mCombinationCutPositionList where participantPosition5 is not null
        defaultMCombinationCutPositionShouldBeFound("participantPosition5.specified=true");

        // Get all the mCombinationCutPositionList where participantPosition5 is null
        defaultMCombinationCutPositionShouldNotBeFound("participantPosition5.specified=false");
    }

    @Test
    @Transactional
    public void getAllMCombinationCutPositionsByParticipantPosition5IsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mCombinationCutPositionRepository.saveAndFlush(mCombinationCutPosition);

        // Get all the mCombinationCutPositionList where participantPosition5 greater than or equals to DEFAULT_PARTICIPANT_POSITION_5
        defaultMCombinationCutPositionShouldBeFound("participantPosition5.greaterOrEqualThan=" + DEFAULT_PARTICIPANT_POSITION_5);

        // Get all the mCombinationCutPositionList where participantPosition5 greater than or equals to UPDATED_PARTICIPANT_POSITION_5
        defaultMCombinationCutPositionShouldNotBeFound("participantPosition5.greaterOrEqualThan=" + UPDATED_PARTICIPANT_POSITION_5);
    }

    @Test
    @Transactional
    public void getAllMCombinationCutPositionsByParticipantPosition5IsLessThanSomething() throws Exception {
        // Initialize the database
        mCombinationCutPositionRepository.saveAndFlush(mCombinationCutPosition);

        // Get all the mCombinationCutPositionList where participantPosition5 less than or equals to DEFAULT_PARTICIPANT_POSITION_5
        defaultMCombinationCutPositionShouldNotBeFound("participantPosition5.lessThan=" + DEFAULT_PARTICIPANT_POSITION_5);

        // Get all the mCombinationCutPositionList where participantPosition5 less than or equals to UPDATED_PARTICIPANT_POSITION_5
        defaultMCombinationCutPositionShouldBeFound("participantPosition5.lessThan=" + UPDATED_PARTICIPANT_POSITION_5);
    }


    @Test
    @Transactional
    public void getAllMCombinationCutPositionsByIdIsEqualToSomething() throws Exception {
        // Get already existing entity
        MCharacter id = mCombinationCutPosition.getId();
        mCombinationCutPositionRepository.saveAndFlush(mCombinationCutPosition);
        Long idId = id.getId();

        // Get all the mCombinationCutPositionList where id equals to idId
        defaultMCombinationCutPositionShouldBeFound("idId.equals=" + idId);

        // Get all the mCombinationCutPositionList where id equals to idId + 1
        defaultMCombinationCutPositionShouldNotBeFound("idId.equals=" + (idId + 1));
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultMCombinationCutPositionShouldBeFound(String filter) throws Exception {
        restMCombinationCutPositionMockMvc.perform(get("/api/m-combination-cut-positions?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(mCombinationCutPosition.getId().intValue())))
            .andExpect(jsonPath("$.[*].actionCutId").value(hasItem(DEFAULT_ACTION_CUT_ID)))
            .andExpect(jsonPath("$.[*].characterId").value(hasItem(DEFAULT_CHARACTER_ID)))
            .andExpect(jsonPath("$.[*].activatorPosition").value(hasItem(DEFAULT_ACTIVATOR_POSITION)))
            .andExpect(jsonPath("$.[*].participantPosition1").value(hasItem(DEFAULT_PARTICIPANT_POSITION_1)))
            .andExpect(jsonPath("$.[*].participantPosition2").value(hasItem(DEFAULT_PARTICIPANT_POSITION_2)))
            .andExpect(jsonPath("$.[*].participantPosition3").value(hasItem(DEFAULT_PARTICIPANT_POSITION_3)))
            .andExpect(jsonPath("$.[*].participantPosition4").value(hasItem(DEFAULT_PARTICIPANT_POSITION_4)))
            .andExpect(jsonPath("$.[*].participantPosition5").value(hasItem(DEFAULT_PARTICIPANT_POSITION_5)));

        // Check, that the count call also returns 1
        restMCombinationCutPositionMockMvc.perform(get("/api/m-combination-cut-positions/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultMCombinationCutPositionShouldNotBeFound(String filter) throws Exception {
        restMCombinationCutPositionMockMvc.perform(get("/api/m-combination-cut-positions?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restMCombinationCutPositionMockMvc.perform(get("/api/m-combination-cut-positions/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("0"));
    }


    @Test
    @Transactional
    public void getNonExistingMCombinationCutPosition() throws Exception {
        // Get the mCombinationCutPosition
        restMCombinationCutPositionMockMvc.perform(get("/api/m-combination-cut-positions/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateMCombinationCutPosition() throws Exception {
        // Initialize the database
        mCombinationCutPositionRepository.saveAndFlush(mCombinationCutPosition);

        int databaseSizeBeforeUpdate = mCombinationCutPositionRepository.findAll().size();

        // Update the mCombinationCutPosition
        MCombinationCutPosition updatedMCombinationCutPosition = mCombinationCutPositionRepository.findById(mCombinationCutPosition.getId()).get();
        // Disconnect from session so that the updates on updatedMCombinationCutPosition are not directly saved in db
        em.detach(updatedMCombinationCutPosition);
        updatedMCombinationCutPosition
            .actionCutId(UPDATED_ACTION_CUT_ID)
            .characterId(UPDATED_CHARACTER_ID)
            .activatorPosition(UPDATED_ACTIVATOR_POSITION)
            .participantPosition1(UPDATED_PARTICIPANT_POSITION_1)
            .participantPosition2(UPDATED_PARTICIPANT_POSITION_2)
            .participantPosition3(UPDATED_PARTICIPANT_POSITION_3)
            .participantPosition4(UPDATED_PARTICIPANT_POSITION_4)
            .participantPosition5(UPDATED_PARTICIPANT_POSITION_5);
        MCombinationCutPositionDTO mCombinationCutPositionDTO = mCombinationCutPositionMapper.toDto(updatedMCombinationCutPosition);

        restMCombinationCutPositionMockMvc.perform(put("/api/m-combination-cut-positions")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mCombinationCutPositionDTO)))
            .andExpect(status().isOk());

        // Validate the MCombinationCutPosition in the database
        List<MCombinationCutPosition> mCombinationCutPositionList = mCombinationCutPositionRepository.findAll();
        assertThat(mCombinationCutPositionList).hasSize(databaseSizeBeforeUpdate);
        MCombinationCutPosition testMCombinationCutPosition = mCombinationCutPositionList.get(mCombinationCutPositionList.size() - 1);
        assertThat(testMCombinationCutPosition.getActionCutId()).isEqualTo(UPDATED_ACTION_CUT_ID);
        assertThat(testMCombinationCutPosition.getCharacterId()).isEqualTo(UPDATED_CHARACTER_ID);
        assertThat(testMCombinationCutPosition.getActivatorPosition()).isEqualTo(UPDATED_ACTIVATOR_POSITION);
        assertThat(testMCombinationCutPosition.getParticipantPosition1()).isEqualTo(UPDATED_PARTICIPANT_POSITION_1);
        assertThat(testMCombinationCutPosition.getParticipantPosition2()).isEqualTo(UPDATED_PARTICIPANT_POSITION_2);
        assertThat(testMCombinationCutPosition.getParticipantPosition3()).isEqualTo(UPDATED_PARTICIPANT_POSITION_3);
        assertThat(testMCombinationCutPosition.getParticipantPosition4()).isEqualTo(UPDATED_PARTICIPANT_POSITION_4);
        assertThat(testMCombinationCutPosition.getParticipantPosition5()).isEqualTo(UPDATED_PARTICIPANT_POSITION_5);
    }

    @Test
    @Transactional
    public void updateNonExistingMCombinationCutPosition() throws Exception {
        int databaseSizeBeforeUpdate = mCombinationCutPositionRepository.findAll().size();

        // Create the MCombinationCutPosition
        MCombinationCutPositionDTO mCombinationCutPositionDTO = mCombinationCutPositionMapper.toDto(mCombinationCutPosition);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restMCombinationCutPositionMockMvc.perform(put("/api/m-combination-cut-positions")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mCombinationCutPositionDTO)))
            .andExpect(status().isBadRequest());

        // Validate the MCombinationCutPosition in the database
        List<MCombinationCutPosition> mCombinationCutPositionList = mCombinationCutPositionRepository.findAll();
        assertThat(mCombinationCutPositionList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteMCombinationCutPosition() throws Exception {
        // Initialize the database
        mCombinationCutPositionRepository.saveAndFlush(mCombinationCutPosition);

        int databaseSizeBeforeDelete = mCombinationCutPositionRepository.findAll().size();

        // Delete the mCombinationCutPosition
        restMCombinationCutPositionMockMvc.perform(delete("/api/m-combination-cut-positions/{id}", mCombinationCutPosition.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database is empty
        List<MCombinationCutPosition> mCombinationCutPositionList = mCombinationCutPositionRepository.findAll();
        assertThat(mCombinationCutPositionList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(MCombinationCutPosition.class);
        MCombinationCutPosition mCombinationCutPosition1 = new MCombinationCutPosition();
        mCombinationCutPosition1.setId(1L);
        MCombinationCutPosition mCombinationCutPosition2 = new MCombinationCutPosition();
        mCombinationCutPosition2.setId(mCombinationCutPosition1.getId());
        assertThat(mCombinationCutPosition1).isEqualTo(mCombinationCutPosition2);
        mCombinationCutPosition2.setId(2L);
        assertThat(mCombinationCutPosition1).isNotEqualTo(mCombinationCutPosition2);
        mCombinationCutPosition1.setId(null);
        assertThat(mCombinationCutPosition1).isNotEqualTo(mCombinationCutPosition2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(MCombinationCutPositionDTO.class);
        MCombinationCutPositionDTO mCombinationCutPositionDTO1 = new MCombinationCutPositionDTO();
        mCombinationCutPositionDTO1.setId(1L);
        MCombinationCutPositionDTO mCombinationCutPositionDTO2 = new MCombinationCutPositionDTO();
        assertThat(mCombinationCutPositionDTO1).isNotEqualTo(mCombinationCutPositionDTO2);
        mCombinationCutPositionDTO2.setId(mCombinationCutPositionDTO1.getId());
        assertThat(mCombinationCutPositionDTO1).isEqualTo(mCombinationCutPositionDTO2);
        mCombinationCutPositionDTO2.setId(2L);
        assertThat(mCombinationCutPositionDTO1).isNotEqualTo(mCombinationCutPositionDTO2);
        mCombinationCutPositionDTO1.setId(null);
        assertThat(mCombinationCutPositionDTO1).isNotEqualTo(mCombinationCutPositionDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(mCombinationCutPositionMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(mCombinationCutPositionMapper.fromId(null)).isNull();
    }
}
