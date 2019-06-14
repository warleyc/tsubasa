package io.shm.tsubasa.web.rest;

import io.shm.tsubasa.TsubasaApp;
import io.shm.tsubasa.domain.MRivalEncountCutin;
import io.shm.tsubasa.repository.MRivalEncountCutinRepository;
import io.shm.tsubasa.service.MRivalEncountCutinService;
import io.shm.tsubasa.service.dto.MRivalEncountCutinDTO;
import io.shm.tsubasa.service.mapper.MRivalEncountCutinMapper;
import io.shm.tsubasa.web.rest.errors.ExceptionTranslator;
import io.shm.tsubasa.service.dto.MRivalEncountCutinCriteria;
import io.shm.tsubasa.service.MRivalEncountCutinQueryService;

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
 * Integration tests for the {@Link MRivalEncountCutinResource} REST controller.
 */
@SpringBootTest(classes = TsubasaApp.class)
public class MRivalEncountCutinResourceIT {

    private static final Integer DEFAULT_OFFENSE_CHARACTER_ID = 1;
    private static final Integer UPDATED_OFFENSE_CHARACTER_ID = 2;

    private static final Integer DEFAULT_OFFENSE_TEAM_ID = 1;
    private static final Integer UPDATED_OFFENSE_TEAM_ID = 2;

    private static final Integer DEFAULT_DEFENSE_CHARACTER_ID = 1;
    private static final Integer UPDATED_DEFENSE_CHARACTER_ID = 2;

    private static final Integer DEFAULT_DEFENSE_TEAM_ID = 1;
    private static final Integer UPDATED_DEFENSE_TEAM_ID = 2;

    private static final Integer DEFAULT_CUTIN_TYPE = 1;
    private static final Integer UPDATED_CUTIN_TYPE = 2;

    private static final String DEFAULT_CHAPTER_1_TEXT = "AAAAAAAAAA";
    private static final String UPDATED_CHAPTER_1_TEXT = "BBBBBBBBBB";

    private static final String DEFAULT_CHAPTER_1_SOUND_EVENT = "AAAAAAAAAA";
    private static final String UPDATED_CHAPTER_1_SOUND_EVENT = "BBBBBBBBBB";

    private static final String DEFAULT_CHAPTER_2_TEXT = "AAAAAAAAAA";
    private static final String UPDATED_CHAPTER_2_TEXT = "BBBBBBBBBB";

    private static final String DEFAULT_CHAPTER_2_SOUND_EVENT = "AAAAAAAAAA";
    private static final String UPDATED_CHAPTER_2_SOUND_EVENT = "BBBBBBBBBB";

    @Autowired
    private MRivalEncountCutinRepository mRivalEncountCutinRepository;

    @Autowired
    private MRivalEncountCutinMapper mRivalEncountCutinMapper;

    @Autowired
    private MRivalEncountCutinService mRivalEncountCutinService;

    @Autowired
    private MRivalEncountCutinQueryService mRivalEncountCutinQueryService;

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

    private MockMvc restMRivalEncountCutinMockMvc;

    private MRivalEncountCutin mRivalEncountCutin;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final MRivalEncountCutinResource mRivalEncountCutinResource = new MRivalEncountCutinResource(mRivalEncountCutinService, mRivalEncountCutinQueryService);
        this.restMRivalEncountCutinMockMvc = MockMvcBuilders.standaloneSetup(mRivalEncountCutinResource)
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
    public static MRivalEncountCutin createEntity(EntityManager em) {
        MRivalEncountCutin mRivalEncountCutin = new MRivalEncountCutin()
            .offenseCharacterId(DEFAULT_OFFENSE_CHARACTER_ID)
            .offenseTeamId(DEFAULT_OFFENSE_TEAM_ID)
            .defenseCharacterId(DEFAULT_DEFENSE_CHARACTER_ID)
            .defenseTeamId(DEFAULT_DEFENSE_TEAM_ID)
            .cutinType(DEFAULT_CUTIN_TYPE)
            .chapter1Text(DEFAULT_CHAPTER_1_TEXT)
            .chapter1SoundEvent(DEFAULT_CHAPTER_1_SOUND_EVENT)
            .chapter2Text(DEFAULT_CHAPTER_2_TEXT)
            .chapter2SoundEvent(DEFAULT_CHAPTER_2_SOUND_EVENT);
        return mRivalEncountCutin;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static MRivalEncountCutin createUpdatedEntity(EntityManager em) {
        MRivalEncountCutin mRivalEncountCutin = new MRivalEncountCutin()
            .offenseCharacterId(UPDATED_OFFENSE_CHARACTER_ID)
            .offenseTeamId(UPDATED_OFFENSE_TEAM_ID)
            .defenseCharacterId(UPDATED_DEFENSE_CHARACTER_ID)
            .defenseTeamId(UPDATED_DEFENSE_TEAM_ID)
            .cutinType(UPDATED_CUTIN_TYPE)
            .chapter1Text(UPDATED_CHAPTER_1_TEXT)
            .chapter1SoundEvent(UPDATED_CHAPTER_1_SOUND_EVENT)
            .chapter2Text(UPDATED_CHAPTER_2_TEXT)
            .chapter2SoundEvent(UPDATED_CHAPTER_2_SOUND_EVENT);
        return mRivalEncountCutin;
    }

    @BeforeEach
    public void initTest() {
        mRivalEncountCutin = createEntity(em);
    }

    @Test
    @Transactional
    public void createMRivalEncountCutin() throws Exception {
        int databaseSizeBeforeCreate = mRivalEncountCutinRepository.findAll().size();

        // Create the MRivalEncountCutin
        MRivalEncountCutinDTO mRivalEncountCutinDTO = mRivalEncountCutinMapper.toDto(mRivalEncountCutin);
        restMRivalEncountCutinMockMvc.perform(post("/api/m-rival-encount-cutins")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mRivalEncountCutinDTO)))
            .andExpect(status().isCreated());

        // Validate the MRivalEncountCutin in the database
        List<MRivalEncountCutin> mRivalEncountCutinList = mRivalEncountCutinRepository.findAll();
        assertThat(mRivalEncountCutinList).hasSize(databaseSizeBeforeCreate + 1);
        MRivalEncountCutin testMRivalEncountCutin = mRivalEncountCutinList.get(mRivalEncountCutinList.size() - 1);
        assertThat(testMRivalEncountCutin.getOffenseCharacterId()).isEqualTo(DEFAULT_OFFENSE_CHARACTER_ID);
        assertThat(testMRivalEncountCutin.getOffenseTeamId()).isEqualTo(DEFAULT_OFFENSE_TEAM_ID);
        assertThat(testMRivalEncountCutin.getDefenseCharacterId()).isEqualTo(DEFAULT_DEFENSE_CHARACTER_ID);
        assertThat(testMRivalEncountCutin.getDefenseTeamId()).isEqualTo(DEFAULT_DEFENSE_TEAM_ID);
        assertThat(testMRivalEncountCutin.getCutinType()).isEqualTo(DEFAULT_CUTIN_TYPE);
        assertThat(testMRivalEncountCutin.getChapter1Text()).isEqualTo(DEFAULT_CHAPTER_1_TEXT);
        assertThat(testMRivalEncountCutin.getChapter1SoundEvent()).isEqualTo(DEFAULT_CHAPTER_1_SOUND_EVENT);
        assertThat(testMRivalEncountCutin.getChapter2Text()).isEqualTo(DEFAULT_CHAPTER_2_TEXT);
        assertThat(testMRivalEncountCutin.getChapter2SoundEvent()).isEqualTo(DEFAULT_CHAPTER_2_SOUND_EVENT);
    }

    @Test
    @Transactional
    public void createMRivalEncountCutinWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = mRivalEncountCutinRepository.findAll().size();

        // Create the MRivalEncountCutin with an existing ID
        mRivalEncountCutin.setId(1L);
        MRivalEncountCutinDTO mRivalEncountCutinDTO = mRivalEncountCutinMapper.toDto(mRivalEncountCutin);

        // An entity with an existing ID cannot be created, so this API call must fail
        restMRivalEncountCutinMockMvc.perform(post("/api/m-rival-encount-cutins")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mRivalEncountCutinDTO)))
            .andExpect(status().isBadRequest());

        // Validate the MRivalEncountCutin in the database
        List<MRivalEncountCutin> mRivalEncountCutinList = mRivalEncountCutinRepository.findAll();
        assertThat(mRivalEncountCutinList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkOffenseCharacterIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = mRivalEncountCutinRepository.findAll().size();
        // set the field null
        mRivalEncountCutin.setOffenseCharacterId(null);

        // Create the MRivalEncountCutin, which fails.
        MRivalEncountCutinDTO mRivalEncountCutinDTO = mRivalEncountCutinMapper.toDto(mRivalEncountCutin);

        restMRivalEncountCutinMockMvc.perform(post("/api/m-rival-encount-cutins")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mRivalEncountCutinDTO)))
            .andExpect(status().isBadRequest());

        List<MRivalEncountCutin> mRivalEncountCutinList = mRivalEncountCutinRepository.findAll();
        assertThat(mRivalEncountCutinList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkDefenseCharacterIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = mRivalEncountCutinRepository.findAll().size();
        // set the field null
        mRivalEncountCutin.setDefenseCharacterId(null);

        // Create the MRivalEncountCutin, which fails.
        MRivalEncountCutinDTO mRivalEncountCutinDTO = mRivalEncountCutinMapper.toDto(mRivalEncountCutin);

        restMRivalEncountCutinMockMvc.perform(post("/api/m-rival-encount-cutins")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mRivalEncountCutinDTO)))
            .andExpect(status().isBadRequest());

        List<MRivalEncountCutin> mRivalEncountCutinList = mRivalEncountCutinRepository.findAll();
        assertThat(mRivalEncountCutinList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkCutinTypeIsRequired() throws Exception {
        int databaseSizeBeforeTest = mRivalEncountCutinRepository.findAll().size();
        // set the field null
        mRivalEncountCutin.setCutinType(null);

        // Create the MRivalEncountCutin, which fails.
        MRivalEncountCutinDTO mRivalEncountCutinDTO = mRivalEncountCutinMapper.toDto(mRivalEncountCutin);

        restMRivalEncountCutinMockMvc.perform(post("/api/m-rival-encount-cutins")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mRivalEncountCutinDTO)))
            .andExpect(status().isBadRequest());

        List<MRivalEncountCutin> mRivalEncountCutinList = mRivalEncountCutinRepository.findAll();
        assertThat(mRivalEncountCutinList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllMRivalEncountCutins() throws Exception {
        // Initialize the database
        mRivalEncountCutinRepository.saveAndFlush(mRivalEncountCutin);

        // Get all the mRivalEncountCutinList
        restMRivalEncountCutinMockMvc.perform(get("/api/m-rival-encount-cutins?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(mRivalEncountCutin.getId().intValue())))
            .andExpect(jsonPath("$.[*].offenseCharacterId").value(hasItem(DEFAULT_OFFENSE_CHARACTER_ID)))
            .andExpect(jsonPath("$.[*].offenseTeamId").value(hasItem(DEFAULT_OFFENSE_TEAM_ID)))
            .andExpect(jsonPath("$.[*].defenseCharacterId").value(hasItem(DEFAULT_DEFENSE_CHARACTER_ID)))
            .andExpect(jsonPath("$.[*].defenseTeamId").value(hasItem(DEFAULT_DEFENSE_TEAM_ID)))
            .andExpect(jsonPath("$.[*].cutinType").value(hasItem(DEFAULT_CUTIN_TYPE)))
            .andExpect(jsonPath("$.[*].chapter1Text").value(hasItem(DEFAULT_CHAPTER_1_TEXT.toString())))
            .andExpect(jsonPath("$.[*].chapter1SoundEvent").value(hasItem(DEFAULT_CHAPTER_1_SOUND_EVENT.toString())))
            .andExpect(jsonPath("$.[*].chapter2Text").value(hasItem(DEFAULT_CHAPTER_2_TEXT.toString())))
            .andExpect(jsonPath("$.[*].chapter2SoundEvent").value(hasItem(DEFAULT_CHAPTER_2_SOUND_EVENT.toString())));
    }
    
    @Test
    @Transactional
    public void getMRivalEncountCutin() throws Exception {
        // Initialize the database
        mRivalEncountCutinRepository.saveAndFlush(mRivalEncountCutin);

        // Get the mRivalEncountCutin
        restMRivalEncountCutinMockMvc.perform(get("/api/m-rival-encount-cutins/{id}", mRivalEncountCutin.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(mRivalEncountCutin.getId().intValue()))
            .andExpect(jsonPath("$.offenseCharacterId").value(DEFAULT_OFFENSE_CHARACTER_ID))
            .andExpect(jsonPath("$.offenseTeamId").value(DEFAULT_OFFENSE_TEAM_ID))
            .andExpect(jsonPath("$.defenseCharacterId").value(DEFAULT_DEFENSE_CHARACTER_ID))
            .andExpect(jsonPath("$.defenseTeamId").value(DEFAULT_DEFENSE_TEAM_ID))
            .andExpect(jsonPath("$.cutinType").value(DEFAULT_CUTIN_TYPE))
            .andExpect(jsonPath("$.chapter1Text").value(DEFAULT_CHAPTER_1_TEXT.toString()))
            .andExpect(jsonPath("$.chapter1SoundEvent").value(DEFAULT_CHAPTER_1_SOUND_EVENT.toString()))
            .andExpect(jsonPath("$.chapter2Text").value(DEFAULT_CHAPTER_2_TEXT.toString()))
            .andExpect(jsonPath("$.chapter2SoundEvent").value(DEFAULT_CHAPTER_2_SOUND_EVENT.toString()));
    }

    @Test
    @Transactional
    public void getAllMRivalEncountCutinsByOffenseCharacterIdIsEqualToSomething() throws Exception {
        // Initialize the database
        mRivalEncountCutinRepository.saveAndFlush(mRivalEncountCutin);

        // Get all the mRivalEncountCutinList where offenseCharacterId equals to DEFAULT_OFFENSE_CHARACTER_ID
        defaultMRivalEncountCutinShouldBeFound("offenseCharacterId.equals=" + DEFAULT_OFFENSE_CHARACTER_ID);

        // Get all the mRivalEncountCutinList where offenseCharacterId equals to UPDATED_OFFENSE_CHARACTER_ID
        defaultMRivalEncountCutinShouldNotBeFound("offenseCharacterId.equals=" + UPDATED_OFFENSE_CHARACTER_ID);
    }

    @Test
    @Transactional
    public void getAllMRivalEncountCutinsByOffenseCharacterIdIsInShouldWork() throws Exception {
        // Initialize the database
        mRivalEncountCutinRepository.saveAndFlush(mRivalEncountCutin);

        // Get all the mRivalEncountCutinList where offenseCharacterId in DEFAULT_OFFENSE_CHARACTER_ID or UPDATED_OFFENSE_CHARACTER_ID
        defaultMRivalEncountCutinShouldBeFound("offenseCharacterId.in=" + DEFAULT_OFFENSE_CHARACTER_ID + "," + UPDATED_OFFENSE_CHARACTER_ID);

        // Get all the mRivalEncountCutinList where offenseCharacterId equals to UPDATED_OFFENSE_CHARACTER_ID
        defaultMRivalEncountCutinShouldNotBeFound("offenseCharacterId.in=" + UPDATED_OFFENSE_CHARACTER_ID);
    }

    @Test
    @Transactional
    public void getAllMRivalEncountCutinsByOffenseCharacterIdIsNullOrNotNull() throws Exception {
        // Initialize the database
        mRivalEncountCutinRepository.saveAndFlush(mRivalEncountCutin);

        // Get all the mRivalEncountCutinList where offenseCharacterId is not null
        defaultMRivalEncountCutinShouldBeFound("offenseCharacterId.specified=true");

        // Get all the mRivalEncountCutinList where offenseCharacterId is null
        defaultMRivalEncountCutinShouldNotBeFound("offenseCharacterId.specified=false");
    }

    @Test
    @Transactional
    public void getAllMRivalEncountCutinsByOffenseCharacterIdIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mRivalEncountCutinRepository.saveAndFlush(mRivalEncountCutin);

        // Get all the mRivalEncountCutinList where offenseCharacterId greater than or equals to DEFAULT_OFFENSE_CHARACTER_ID
        defaultMRivalEncountCutinShouldBeFound("offenseCharacterId.greaterOrEqualThan=" + DEFAULT_OFFENSE_CHARACTER_ID);

        // Get all the mRivalEncountCutinList where offenseCharacterId greater than or equals to UPDATED_OFFENSE_CHARACTER_ID
        defaultMRivalEncountCutinShouldNotBeFound("offenseCharacterId.greaterOrEqualThan=" + UPDATED_OFFENSE_CHARACTER_ID);
    }

    @Test
    @Transactional
    public void getAllMRivalEncountCutinsByOffenseCharacterIdIsLessThanSomething() throws Exception {
        // Initialize the database
        mRivalEncountCutinRepository.saveAndFlush(mRivalEncountCutin);

        // Get all the mRivalEncountCutinList where offenseCharacterId less than or equals to DEFAULT_OFFENSE_CHARACTER_ID
        defaultMRivalEncountCutinShouldNotBeFound("offenseCharacterId.lessThan=" + DEFAULT_OFFENSE_CHARACTER_ID);

        // Get all the mRivalEncountCutinList where offenseCharacterId less than or equals to UPDATED_OFFENSE_CHARACTER_ID
        defaultMRivalEncountCutinShouldBeFound("offenseCharacterId.lessThan=" + UPDATED_OFFENSE_CHARACTER_ID);
    }


    @Test
    @Transactional
    public void getAllMRivalEncountCutinsByOffenseTeamIdIsEqualToSomething() throws Exception {
        // Initialize the database
        mRivalEncountCutinRepository.saveAndFlush(mRivalEncountCutin);

        // Get all the mRivalEncountCutinList where offenseTeamId equals to DEFAULT_OFFENSE_TEAM_ID
        defaultMRivalEncountCutinShouldBeFound("offenseTeamId.equals=" + DEFAULT_OFFENSE_TEAM_ID);

        // Get all the mRivalEncountCutinList where offenseTeamId equals to UPDATED_OFFENSE_TEAM_ID
        defaultMRivalEncountCutinShouldNotBeFound("offenseTeamId.equals=" + UPDATED_OFFENSE_TEAM_ID);
    }

    @Test
    @Transactional
    public void getAllMRivalEncountCutinsByOffenseTeamIdIsInShouldWork() throws Exception {
        // Initialize the database
        mRivalEncountCutinRepository.saveAndFlush(mRivalEncountCutin);

        // Get all the mRivalEncountCutinList where offenseTeamId in DEFAULT_OFFENSE_TEAM_ID or UPDATED_OFFENSE_TEAM_ID
        defaultMRivalEncountCutinShouldBeFound("offenseTeamId.in=" + DEFAULT_OFFENSE_TEAM_ID + "," + UPDATED_OFFENSE_TEAM_ID);

        // Get all the mRivalEncountCutinList where offenseTeamId equals to UPDATED_OFFENSE_TEAM_ID
        defaultMRivalEncountCutinShouldNotBeFound("offenseTeamId.in=" + UPDATED_OFFENSE_TEAM_ID);
    }

    @Test
    @Transactional
    public void getAllMRivalEncountCutinsByOffenseTeamIdIsNullOrNotNull() throws Exception {
        // Initialize the database
        mRivalEncountCutinRepository.saveAndFlush(mRivalEncountCutin);

        // Get all the mRivalEncountCutinList where offenseTeamId is not null
        defaultMRivalEncountCutinShouldBeFound("offenseTeamId.specified=true");

        // Get all the mRivalEncountCutinList where offenseTeamId is null
        defaultMRivalEncountCutinShouldNotBeFound("offenseTeamId.specified=false");
    }

    @Test
    @Transactional
    public void getAllMRivalEncountCutinsByOffenseTeamIdIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mRivalEncountCutinRepository.saveAndFlush(mRivalEncountCutin);

        // Get all the mRivalEncountCutinList where offenseTeamId greater than or equals to DEFAULT_OFFENSE_TEAM_ID
        defaultMRivalEncountCutinShouldBeFound("offenseTeamId.greaterOrEqualThan=" + DEFAULT_OFFENSE_TEAM_ID);

        // Get all the mRivalEncountCutinList where offenseTeamId greater than or equals to UPDATED_OFFENSE_TEAM_ID
        defaultMRivalEncountCutinShouldNotBeFound("offenseTeamId.greaterOrEqualThan=" + UPDATED_OFFENSE_TEAM_ID);
    }

    @Test
    @Transactional
    public void getAllMRivalEncountCutinsByOffenseTeamIdIsLessThanSomething() throws Exception {
        // Initialize the database
        mRivalEncountCutinRepository.saveAndFlush(mRivalEncountCutin);

        // Get all the mRivalEncountCutinList where offenseTeamId less than or equals to DEFAULT_OFFENSE_TEAM_ID
        defaultMRivalEncountCutinShouldNotBeFound("offenseTeamId.lessThan=" + DEFAULT_OFFENSE_TEAM_ID);

        // Get all the mRivalEncountCutinList where offenseTeamId less than or equals to UPDATED_OFFENSE_TEAM_ID
        defaultMRivalEncountCutinShouldBeFound("offenseTeamId.lessThan=" + UPDATED_OFFENSE_TEAM_ID);
    }


    @Test
    @Transactional
    public void getAllMRivalEncountCutinsByDefenseCharacterIdIsEqualToSomething() throws Exception {
        // Initialize the database
        mRivalEncountCutinRepository.saveAndFlush(mRivalEncountCutin);

        // Get all the mRivalEncountCutinList where defenseCharacterId equals to DEFAULT_DEFENSE_CHARACTER_ID
        defaultMRivalEncountCutinShouldBeFound("defenseCharacterId.equals=" + DEFAULT_DEFENSE_CHARACTER_ID);

        // Get all the mRivalEncountCutinList where defenseCharacterId equals to UPDATED_DEFENSE_CHARACTER_ID
        defaultMRivalEncountCutinShouldNotBeFound("defenseCharacterId.equals=" + UPDATED_DEFENSE_CHARACTER_ID);
    }

    @Test
    @Transactional
    public void getAllMRivalEncountCutinsByDefenseCharacterIdIsInShouldWork() throws Exception {
        // Initialize the database
        mRivalEncountCutinRepository.saveAndFlush(mRivalEncountCutin);

        // Get all the mRivalEncountCutinList where defenseCharacterId in DEFAULT_DEFENSE_CHARACTER_ID or UPDATED_DEFENSE_CHARACTER_ID
        defaultMRivalEncountCutinShouldBeFound("defenseCharacterId.in=" + DEFAULT_DEFENSE_CHARACTER_ID + "," + UPDATED_DEFENSE_CHARACTER_ID);

        // Get all the mRivalEncountCutinList where defenseCharacterId equals to UPDATED_DEFENSE_CHARACTER_ID
        defaultMRivalEncountCutinShouldNotBeFound("defenseCharacterId.in=" + UPDATED_DEFENSE_CHARACTER_ID);
    }

    @Test
    @Transactional
    public void getAllMRivalEncountCutinsByDefenseCharacterIdIsNullOrNotNull() throws Exception {
        // Initialize the database
        mRivalEncountCutinRepository.saveAndFlush(mRivalEncountCutin);

        // Get all the mRivalEncountCutinList where defenseCharacterId is not null
        defaultMRivalEncountCutinShouldBeFound("defenseCharacterId.specified=true");

        // Get all the mRivalEncountCutinList where defenseCharacterId is null
        defaultMRivalEncountCutinShouldNotBeFound("defenseCharacterId.specified=false");
    }

    @Test
    @Transactional
    public void getAllMRivalEncountCutinsByDefenseCharacterIdIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mRivalEncountCutinRepository.saveAndFlush(mRivalEncountCutin);

        // Get all the mRivalEncountCutinList where defenseCharacterId greater than or equals to DEFAULT_DEFENSE_CHARACTER_ID
        defaultMRivalEncountCutinShouldBeFound("defenseCharacterId.greaterOrEqualThan=" + DEFAULT_DEFENSE_CHARACTER_ID);

        // Get all the mRivalEncountCutinList where defenseCharacterId greater than or equals to UPDATED_DEFENSE_CHARACTER_ID
        defaultMRivalEncountCutinShouldNotBeFound("defenseCharacterId.greaterOrEqualThan=" + UPDATED_DEFENSE_CHARACTER_ID);
    }

    @Test
    @Transactional
    public void getAllMRivalEncountCutinsByDefenseCharacterIdIsLessThanSomething() throws Exception {
        // Initialize the database
        mRivalEncountCutinRepository.saveAndFlush(mRivalEncountCutin);

        // Get all the mRivalEncountCutinList where defenseCharacterId less than or equals to DEFAULT_DEFENSE_CHARACTER_ID
        defaultMRivalEncountCutinShouldNotBeFound("defenseCharacterId.lessThan=" + DEFAULT_DEFENSE_CHARACTER_ID);

        // Get all the mRivalEncountCutinList where defenseCharacterId less than or equals to UPDATED_DEFENSE_CHARACTER_ID
        defaultMRivalEncountCutinShouldBeFound("defenseCharacterId.lessThan=" + UPDATED_DEFENSE_CHARACTER_ID);
    }


    @Test
    @Transactional
    public void getAllMRivalEncountCutinsByDefenseTeamIdIsEqualToSomething() throws Exception {
        // Initialize the database
        mRivalEncountCutinRepository.saveAndFlush(mRivalEncountCutin);

        // Get all the mRivalEncountCutinList where defenseTeamId equals to DEFAULT_DEFENSE_TEAM_ID
        defaultMRivalEncountCutinShouldBeFound("defenseTeamId.equals=" + DEFAULT_DEFENSE_TEAM_ID);

        // Get all the mRivalEncountCutinList where defenseTeamId equals to UPDATED_DEFENSE_TEAM_ID
        defaultMRivalEncountCutinShouldNotBeFound("defenseTeamId.equals=" + UPDATED_DEFENSE_TEAM_ID);
    }

    @Test
    @Transactional
    public void getAllMRivalEncountCutinsByDefenseTeamIdIsInShouldWork() throws Exception {
        // Initialize the database
        mRivalEncountCutinRepository.saveAndFlush(mRivalEncountCutin);

        // Get all the mRivalEncountCutinList where defenseTeamId in DEFAULT_DEFENSE_TEAM_ID or UPDATED_DEFENSE_TEAM_ID
        defaultMRivalEncountCutinShouldBeFound("defenseTeamId.in=" + DEFAULT_DEFENSE_TEAM_ID + "," + UPDATED_DEFENSE_TEAM_ID);

        // Get all the mRivalEncountCutinList where defenseTeamId equals to UPDATED_DEFENSE_TEAM_ID
        defaultMRivalEncountCutinShouldNotBeFound("defenseTeamId.in=" + UPDATED_DEFENSE_TEAM_ID);
    }

    @Test
    @Transactional
    public void getAllMRivalEncountCutinsByDefenseTeamIdIsNullOrNotNull() throws Exception {
        // Initialize the database
        mRivalEncountCutinRepository.saveAndFlush(mRivalEncountCutin);

        // Get all the mRivalEncountCutinList where defenseTeamId is not null
        defaultMRivalEncountCutinShouldBeFound("defenseTeamId.specified=true");

        // Get all the mRivalEncountCutinList where defenseTeamId is null
        defaultMRivalEncountCutinShouldNotBeFound("defenseTeamId.specified=false");
    }

    @Test
    @Transactional
    public void getAllMRivalEncountCutinsByDefenseTeamIdIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mRivalEncountCutinRepository.saveAndFlush(mRivalEncountCutin);

        // Get all the mRivalEncountCutinList where defenseTeamId greater than or equals to DEFAULT_DEFENSE_TEAM_ID
        defaultMRivalEncountCutinShouldBeFound("defenseTeamId.greaterOrEqualThan=" + DEFAULT_DEFENSE_TEAM_ID);

        // Get all the mRivalEncountCutinList where defenseTeamId greater than or equals to UPDATED_DEFENSE_TEAM_ID
        defaultMRivalEncountCutinShouldNotBeFound("defenseTeamId.greaterOrEqualThan=" + UPDATED_DEFENSE_TEAM_ID);
    }

    @Test
    @Transactional
    public void getAllMRivalEncountCutinsByDefenseTeamIdIsLessThanSomething() throws Exception {
        // Initialize the database
        mRivalEncountCutinRepository.saveAndFlush(mRivalEncountCutin);

        // Get all the mRivalEncountCutinList where defenseTeamId less than or equals to DEFAULT_DEFENSE_TEAM_ID
        defaultMRivalEncountCutinShouldNotBeFound("defenseTeamId.lessThan=" + DEFAULT_DEFENSE_TEAM_ID);

        // Get all the mRivalEncountCutinList where defenseTeamId less than or equals to UPDATED_DEFENSE_TEAM_ID
        defaultMRivalEncountCutinShouldBeFound("defenseTeamId.lessThan=" + UPDATED_DEFENSE_TEAM_ID);
    }


    @Test
    @Transactional
    public void getAllMRivalEncountCutinsByCutinTypeIsEqualToSomething() throws Exception {
        // Initialize the database
        mRivalEncountCutinRepository.saveAndFlush(mRivalEncountCutin);

        // Get all the mRivalEncountCutinList where cutinType equals to DEFAULT_CUTIN_TYPE
        defaultMRivalEncountCutinShouldBeFound("cutinType.equals=" + DEFAULT_CUTIN_TYPE);

        // Get all the mRivalEncountCutinList where cutinType equals to UPDATED_CUTIN_TYPE
        defaultMRivalEncountCutinShouldNotBeFound("cutinType.equals=" + UPDATED_CUTIN_TYPE);
    }

    @Test
    @Transactional
    public void getAllMRivalEncountCutinsByCutinTypeIsInShouldWork() throws Exception {
        // Initialize the database
        mRivalEncountCutinRepository.saveAndFlush(mRivalEncountCutin);

        // Get all the mRivalEncountCutinList where cutinType in DEFAULT_CUTIN_TYPE or UPDATED_CUTIN_TYPE
        defaultMRivalEncountCutinShouldBeFound("cutinType.in=" + DEFAULT_CUTIN_TYPE + "," + UPDATED_CUTIN_TYPE);

        // Get all the mRivalEncountCutinList where cutinType equals to UPDATED_CUTIN_TYPE
        defaultMRivalEncountCutinShouldNotBeFound("cutinType.in=" + UPDATED_CUTIN_TYPE);
    }

    @Test
    @Transactional
    public void getAllMRivalEncountCutinsByCutinTypeIsNullOrNotNull() throws Exception {
        // Initialize the database
        mRivalEncountCutinRepository.saveAndFlush(mRivalEncountCutin);

        // Get all the mRivalEncountCutinList where cutinType is not null
        defaultMRivalEncountCutinShouldBeFound("cutinType.specified=true");

        // Get all the mRivalEncountCutinList where cutinType is null
        defaultMRivalEncountCutinShouldNotBeFound("cutinType.specified=false");
    }

    @Test
    @Transactional
    public void getAllMRivalEncountCutinsByCutinTypeIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mRivalEncountCutinRepository.saveAndFlush(mRivalEncountCutin);

        // Get all the mRivalEncountCutinList where cutinType greater than or equals to DEFAULT_CUTIN_TYPE
        defaultMRivalEncountCutinShouldBeFound("cutinType.greaterOrEqualThan=" + DEFAULT_CUTIN_TYPE);

        // Get all the mRivalEncountCutinList where cutinType greater than or equals to UPDATED_CUTIN_TYPE
        defaultMRivalEncountCutinShouldNotBeFound("cutinType.greaterOrEqualThan=" + UPDATED_CUTIN_TYPE);
    }

    @Test
    @Transactional
    public void getAllMRivalEncountCutinsByCutinTypeIsLessThanSomething() throws Exception {
        // Initialize the database
        mRivalEncountCutinRepository.saveAndFlush(mRivalEncountCutin);

        // Get all the mRivalEncountCutinList where cutinType less than or equals to DEFAULT_CUTIN_TYPE
        defaultMRivalEncountCutinShouldNotBeFound("cutinType.lessThan=" + DEFAULT_CUTIN_TYPE);

        // Get all the mRivalEncountCutinList where cutinType less than or equals to UPDATED_CUTIN_TYPE
        defaultMRivalEncountCutinShouldBeFound("cutinType.lessThan=" + UPDATED_CUTIN_TYPE);
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultMRivalEncountCutinShouldBeFound(String filter) throws Exception {
        restMRivalEncountCutinMockMvc.perform(get("/api/m-rival-encount-cutins?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(mRivalEncountCutin.getId().intValue())))
            .andExpect(jsonPath("$.[*].offenseCharacterId").value(hasItem(DEFAULT_OFFENSE_CHARACTER_ID)))
            .andExpect(jsonPath("$.[*].offenseTeamId").value(hasItem(DEFAULT_OFFENSE_TEAM_ID)))
            .andExpect(jsonPath("$.[*].defenseCharacterId").value(hasItem(DEFAULT_DEFENSE_CHARACTER_ID)))
            .andExpect(jsonPath("$.[*].defenseTeamId").value(hasItem(DEFAULT_DEFENSE_TEAM_ID)))
            .andExpect(jsonPath("$.[*].cutinType").value(hasItem(DEFAULT_CUTIN_TYPE)))
            .andExpect(jsonPath("$.[*].chapter1Text").value(hasItem(DEFAULT_CHAPTER_1_TEXT.toString())))
            .andExpect(jsonPath("$.[*].chapter1SoundEvent").value(hasItem(DEFAULT_CHAPTER_1_SOUND_EVENT.toString())))
            .andExpect(jsonPath("$.[*].chapter2Text").value(hasItem(DEFAULT_CHAPTER_2_TEXT.toString())))
            .andExpect(jsonPath("$.[*].chapter2SoundEvent").value(hasItem(DEFAULT_CHAPTER_2_SOUND_EVENT.toString())));

        // Check, that the count call also returns 1
        restMRivalEncountCutinMockMvc.perform(get("/api/m-rival-encount-cutins/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultMRivalEncountCutinShouldNotBeFound(String filter) throws Exception {
        restMRivalEncountCutinMockMvc.perform(get("/api/m-rival-encount-cutins?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restMRivalEncountCutinMockMvc.perform(get("/api/m-rival-encount-cutins/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("0"));
    }


    @Test
    @Transactional
    public void getNonExistingMRivalEncountCutin() throws Exception {
        // Get the mRivalEncountCutin
        restMRivalEncountCutinMockMvc.perform(get("/api/m-rival-encount-cutins/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateMRivalEncountCutin() throws Exception {
        // Initialize the database
        mRivalEncountCutinRepository.saveAndFlush(mRivalEncountCutin);

        int databaseSizeBeforeUpdate = mRivalEncountCutinRepository.findAll().size();

        // Update the mRivalEncountCutin
        MRivalEncountCutin updatedMRivalEncountCutin = mRivalEncountCutinRepository.findById(mRivalEncountCutin.getId()).get();
        // Disconnect from session so that the updates on updatedMRivalEncountCutin are not directly saved in db
        em.detach(updatedMRivalEncountCutin);
        updatedMRivalEncountCutin
            .offenseCharacterId(UPDATED_OFFENSE_CHARACTER_ID)
            .offenseTeamId(UPDATED_OFFENSE_TEAM_ID)
            .defenseCharacterId(UPDATED_DEFENSE_CHARACTER_ID)
            .defenseTeamId(UPDATED_DEFENSE_TEAM_ID)
            .cutinType(UPDATED_CUTIN_TYPE)
            .chapter1Text(UPDATED_CHAPTER_1_TEXT)
            .chapter1SoundEvent(UPDATED_CHAPTER_1_SOUND_EVENT)
            .chapter2Text(UPDATED_CHAPTER_2_TEXT)
            .chapter2SoundEvent(UPDATED_CHAPTER_2_SOUND_EVENT);
        MRivalEncountCutinDTO mRivalEncountCutinDTO = mRivalEncountCutinMapper.toDto(updatedMRivalEncountCutin);

        restMRivalEncountCutinMockMvc.perform(put("/api/m-rival-encount-cutins")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mRivalEncountCutinDTO)))
            .andExpect(status().isOk());

        // Validate the MRivalEncountCutin in the database
        List<MRivalEncountCutin> mRivalEncountCutinList = mRivalEncountCutinRepository.findAll();
        assertThat(mRivalEncountCutinList).hasSize(databaseSizeBeforeUpdate);
        MRivalEncountCutin testMRivalEncountCutin = mRivalEncountCutinList.get(mRivalEncountCutinList.size() - 1);
        assertThat(testMRivalEncountCutin.getOffenseCharacterId()).isEqualTo(UPDATED_OFFENSE_CHARACTER_ID);
        assertThat(testMRivalEncountCutin.getOffenseTeamId()).isEqualTo(UPDATED_OFFENSE_TEAM_ID);
        assertThat(testMRivalEncountCutin.getDefenseCharacterId()).isEqualTo(UPDATED_DEFENSE_CHARACTER_ID);
        assertThat(testMRivalEncountCutin.getDefenseTeamId()).isEqualTo(UPDATED_DEFENSE_TEAM_ID);
        assertThat(testMRivalEncountCutin.getCutinType()).isEqualTo(UPDATED_CUTIN_TYPE);
        assertThat(testMRivalEncountCutin.getChapter1Text()).isEqualTo(UPDATED_CHAPTER_1_TEXT);
        assertThat(testMRivalEncountCutin.getChapter1SoundEvent()).isEqualTo(UPDATED_CHAPTER_1_SOUND_EVENT);
        assertThat(testMRivalEncountCutin.getChapter2Text()).isEqualTo(UPDATED_CHAPTER_2_TEXT);
        assertThat(testMRivalEncountCutin.getChapter2SoundEvent()).isEqualTo(UPDATED_CHAPTER_2_SOUND_EVENT);
    }

    @Test
    @Transactional
    public void updateNonExistingMRivalEncountCutin() throws Exception {
        int databaseSizeBeforeUpdate = mRivalEncountCutinRepository.findAll().size();

        // Create the MRivalEncountCutin
        MRivalEncountCutinDTO mRivalEncountCutinDTO = mRivalEncountCutinMapper.toDto(mRivalEncountCutin);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restMRivalEncountCutinMockMvc.perform(put("/api/m-rival-encount-cutins")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mRivalEncountCutinDTO)))
            .andExpect(status().isBadRequest());

        // Validate the MRivalEncountCutin in the database
        List<MRivalEncountCutin> mRivalEncountCutinList = mRivalEncountCutinRepository.findAll();
        assertThat(mRivalEncountCutinList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteMRivalEncountCutin() throws Exception {
        // Initialize the database
        mRivalEncountCutinRepository.saveAndFlush(mRivalEncountCutin);

        int databaseSizeBeforeDelete = mRivalEncountCutinRepository.findAll().size();

        // Delete the mRivalEncountCutin
        restMRivalEncountCutinMockMvc.perform(delete("/api/m-rival-encount-cutins/{id}", mRivalEncountCutin.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database is empty
        List<MRivalEncountCutin> mRivalEncountCutinList = mRivalEncountCutinRepository.findAll();
        assertThat(mRivalEncountCutinList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(MRivalEncountCutin.class);
        MRivalEncountCutin mRivalEncountCutin1 = new MRivalEncountCutin();
        mRivalEncountCutin1.setId(1L);
        MRivalEncountCutin mRivalEncountCutin2 = new MRivalEncountCutin();
        mRivalEncountCutin2.setId(mRivalEncountCutin1.getId());
        assertThat(mRivalEncountCutin1).isEqualTo(mRivalEncountCutin2);
        mRivalEncountCutin2.setId(2L);
        assertThat(mRivalEncountCutin1).isNotEqualTo(mRivalEncountCutin2);
        mRivalEncountCutin1.setId(null);
        assertThat(mRivalEncountCutin1).isNotEqualTo(mRivalEncountCutin2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(MRivalEncountCutinDTO.class);
        MRivalEncountCutinDTO mRivalEncountCutinDTO1 = new MRivalEncountCutinDTO();
        mRivalEncountCutinDTO1.setId(1L);
        MRivalEncountCutinDTO mRivalEncountCutinDTO2 = new MRivalEncountCutinDTO();
        assertThat(mRivalEncountCutinDTO1).isNotEqualTo(mRivalEncountCutinDTO2);
        mRivalEncountCutinDTO2.setId(mRivalEncountCutinDTO1.getId());
        assertThat(mRivalEncountCutinDTO1).isEqualTo(mRivalEncountCutinDTO2);
        mRivalEncountCutinDTO2.setId(2L);
        assertThat(mRivalEncountCutinDTO1).isNotEqualTo(mRivalEncountCutinDTO2);
        mRivalEncountCutinDTO1.setId(null);
        assertThat(mRivalEncountCutinDTO1).isNotEqualTo(mRivalEncountCutinDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(mRivalEncountCutinMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(mRivalEncountCutinMapper.fromId(null)).isNull();
    }
}
