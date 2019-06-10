package io.shm.tsubasa.web.rest;

import io.shm.tsubasa.TsubasaApp;
import io.shm.tsubasa.domain.MEncountersCommandCompatibility;
import io.shm.tsubasa.repository.MEncountersCommandCompatibilityRepository;
import io.shm.tsubasa.service.MEncountersCommandCompatibilityService;
import io.shm.tsubasa.service.dto.MEncountersCommandCompatibilityDTO;
import io.shm.tsubasa.service.mapper.MEncountersCommandCompatibilityMapper;
import io.shm.tsubasa.web.rest.errors.ExceptionTranslator;
import io.shm.tsubasa.service.dto.MEncountersCommandCompatibilityCriteria;
import io.shm.tsubasa.service.MEncountersCommandCompatibilityQueryService;

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
 * Integration tests for the {@Link MEncountersCommandCompatibilityResource} REST controller.
 */
@SpringBootTest(classes = TsubasaApp.class)
public class MEncountersCommandCompatibilityResourceIT {

    private static final Integer DEFAULT_ENCOUNTERS_TYPE = 1;
    private static final Integer UPDATED_ENCOUNTERS_TYPE = 2;

    private static final Integer DEFAULT_OFFENSE_COMMAND_TYPE = 1;
    private static final Integer UPDATED_OFFENSE_COMMAND_TYPE = 2;

    private static final Integer DEFAULT_DEFENSE_COMMAND_TYPE = 1;
    private static final Integer UPDATED_DEFENSE_COMMAND_TYPE = 2;

    private static final Integer DEFAULT_OFFENSE_MAGNIFICATION = 1;
    private static final Integer UPDATED_OFFENSE_MAGNIFICATION = 2;

    private static final Integer DEFAULT_DEFENSE_MAGNIFICATION = 1;
    private static final Integer UPDATED_DEFENSE_MAGNIFICATION = 2;

    @Autowired
    private MEncountersCommandCompatibilityRepository mEncountersCommandCompatibilityRepository;

    @Autowired
    private MEncountersCommandCompatibilityMapper mEncountersCommandCompatibilityMapper;

    @Autowired
    private MEncountersCommandCompatibilityService mEncountersCommandCompatibilityService;

    @Autowired
    private MEncountersCommandCompatibilityQueryService mEncountersCommandCompatibilityQueryService;

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

    private MockMvc restMEncountersCommandCompatibilityMockMvc;

    private MEncountersCommandCompatibility mEncountersCommandCompatibility;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final MEncountersCommandCompatibilityResource mEncountersCommandCompatibilityResource = new MEncountersCommandCompatibilityResource(mEncountersCommandCompatibilityService, mEncountersCommandCompatibilityQueryService);
        this.restMEncountersCommandCompatibilityMockMvc = MockMvcBuilders.standaloneSetup(mEncountersCommandCompatibilityResource)
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
    public static MEncountersCommandCompatibility createEntity(EntityManager em) {
        MEncountersCommandCompatibility mEncountersCommandCompatibility = new MEncountersCommandCompatibility()
            .encountersType(DEFAULT_ENCOUNTERS_TYPE)
            .offenseCommandType(DEFAULT_OFFENSE_COMMAND_TYPE)
            .defenseCommandType(DEFAULT_DEFENSE_COMMAND_TYPE)
            .offenseMagnification(DEFAULT_OFFENSE_MAGNIFICATION)
            .defenseMagnification(DEFAULT_DEFENSE_MAGNIFICATION);
        return mEncountersCommandCompatibility;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static MEncountersCommandCompatibility createUpdatedEntity(EntityManager em) {
        MEncountersCommandCompatibility mEncountersCommandCompatibility = new MEncountersCommandCompatibility()
            .encountersType(UPDATED_ENCOUNTERS_TYPE)
            .offenseCommandType(UPDATED_OFFENSE_COMMAND_TYPE)
            .defenseCommandType(UPDATED_DEFENSE_COMMAND_TYPE)
            .offenseMagnification(UPDATED_OFFENSE_MAGNIFICATION)
            .defenseMagnification(UPDATED_DEFENSE_MAGNIFICATION);
        return mEncountersCommandCompatibility;
    }

    @BeforeEach
    public void initTest() {
        mEncountersCommandCompatibility = createEntity(em);
    }

    @Test
    @Transactional
    public void createMEncountersCommandCompatibility() throws Exception {
        int databaseSizeBeforeCreate = mEncountersCommandCompatibilityRepository.findAll().size();

        // Create the MEncountersCommandCompatibility
        MEncountersCommandCompatibilityDTO mEncountersCommandCompatibilityDTO = mEncountersCommandCompatibilityMapper.toDto(mEncountersCommandCompatibility);
        restMEncountersCommandCompatibilityMockMvc.perform(post("/api/m-encounters-command-compatibilities")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mEncountersCommandCompatibilityDTO)))
            .andExpect(status().isCreated());

        // Validate the MEncountersCommandCompatibility in the database
        List<MEncountersCommandCompatibility> mEncountersCommandCompatibilityList = mEncountersCommandCompatibilityRepository.findAll();
        assertThat(mEncountersCommandCompatibilityList).hasSize(databaseSizeBeforeCreate + 1);
        MEncountersCommandCompatibility testMEncountersCommandCompatibility = mEncountersCommandCompatibilityList.get(mEncountersCommandCompatibilityList.size() - 1);
        assertThat(testMEncountersCommandCompatibility.getEncountersType()).isEqualTo(DEFAULT_ENCOUNTERS_TYPE);
        assertThat(testMEncountersCommandCompatibility.getOffenseCommandType()).isEqualTo(DEFAULT_OFFENSE_COMMAND_TYPE);
        assertThat(testMEncountersCommandCompatibility.getDefenseCommandType()).isEqualTo(DEFAULT_DEFENSE_COMMAND_TYPE);
        assertThat(testMEncountersCommandCompatibility.getOffenseMagnification()).isEqualTo(DEFAULT_OFFENSE_MAGNIFICATION);
        assertThat(testMEncountersCommandCompatibility.getDefenseMagnification()).isEqualTo(DEFAULT_DEFENSE_MAGNIFICATION);
    }

    @Test
    @Transactional
    public void createMEncountersCommandCompatibilityWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = mEncountersCommandCompatibilityRepository.findAll().size();

        // Create the MEncountersCommandCompatibility with an existing ID
        mEncountersCommandCompatibility.setId(1L);
        MEncountersCommandCompatibilityDTO mEncountersCommandCompatibilityDTO = mEncountersCommandCompatibilityMapper.toDto(mEncountersCommandCompatibility);

        // An entity with an existing ID cannot be created, so this API call must fail
        restMEncountersCommandCompatibilityMockMvc.perform(post("/api/m-encounters-command-compatibilities")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mEncountersCommandCompatibilityDTO)))
            .andExpect(status().isBadRequest());

        // Validate the MEncountersCommandCompatibility in the database
        List<MEncountersCommandCompatibility> mEncountersCommandCompatibilityList = mEncountersCommandCompatibilityRepository.findAll();
        assertThat(mEncountersCommandCompatibilityList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkEncountersTypeIsRequired() throws Exception {
        int databaseSizeBeforeTest = mEncountersCommandCompatibilityRepository.findAll().size();
        // set the field null
        mEncountersCommandCompatibility.setEncountersType(null);

        // Create the MEncountersCommandCompatibility, which fails.
        MEncountersCommandCompatibilityDTO mEncountersCommandCompatibilityDTO = mEncountersCommandCompatibilityMapper.toDto(mEncountersCommandCompatibility);

        restMEncountersCommandCompatibilityMockMvc.perform(post("/api/m-encounters-command-compatibilities")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mEncountersCommandCompatibilityDTO)))
            .andExpect(status().isBadRequest());

        List<MEncountersCommandCompatibility> mEncountersCommandCompatibilityList = mEncountersCommandCompatibilityRepository.findAll();
        assertThat(mEncountersCommandCompatibilityList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkOffenseCommandTypeIsRequired() throws Exception {
        int databaseSizeBeforeTest = mEncountersCommandCompatibilityRepository.findAll().size();
        // set the field null
        mEncountersCommandCompatibility.setOffenseCommandType(null);

        // Create the MEncountersCommandCompatibility, which fails.
        MEncountersCommandCompatibilityDTO mEncountersCommandCompatibilityDTO = mEncountersCommandCompatibilityMapper.toDto(mEncountersCommandCompatibility);

        restMEncountersCommandCompatibilityMockMvc.perform(post("/api/m-encounters-command-compatibilities")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mEncountersCommandCompatibilityDTO)))
            .andExpect(status().isBadRequest());

        List<MEncountersCommandCompatibility> mEncountersCommandCompatibilityList = mEncountersCommandCompatibilityRepository.findAll();
        assertThat(mEncountersCommandCompatibilityList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkDefenseCommandTypeIsRequired() throws Exception {
        int databaseSizeBeforeTest = mEncountersCommandCompatibilityRepository.findAll().size();
        // set the field null
        mEncountersCommandCompatibility.setDefenseCommandType(null);

        // Create the MEncountersCommandCompatibility, which fails.
        MEncountersCommandCompatibilityDTO mEncountersCommandCompatibilityDTO = mEncountersCommandCompatibilityMapper.toDto(mEncountersCommandCompatibility);

        restMEncountersCommandCompatibilityMockMvc.perform(post("/api/m-encounters-command-compatibilities")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mEncountersCommandCompatibilityDTO)))
            .andExpect(status().isBadRequest());

        List<MEncountersCommandCompatibility> mEncountersCommandCompatibilityList = mEncountersCommandCompatibilityRepository.findAll();
        assertThat(mEncountersCommandCompatibilityList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkOffenseMagnificationIsRequired() throws Exception {
        int databaseSizeBeforeTest = mEncountersCommandCompatibilityRepository.findAll().size();
        // set the field null
        mEncountersCommandCompatibility.setOffenseMagnification(null);

        // Create the MEncountersCommandCompatibility, which fails.
        MEncountersCommandCompatibilityDTO mEncountersCommandCompatibilityDTO = mEncountersCommandCompatibilityMapper.toDto(mEncountersCommandCompatibility);

        restMEncountersCommandCompatibilityMockMvc.perform(post("/api/m-encounters-command-compatibilities")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mEncountersCommandCompatibilityDTO)))
            .andExpect(status().isBadRequest());

        List<MEncountersCommandCompatibility> mEncountersCommandCompatibilityList = mEncountersCommandCompatibilityRepository.findAll();
        assertThat(mEncountersCommandCompatibilityList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkDefenseMagnificationIsRequired() throws Exception {
        int databaseSizeBeforeTest = mEncountersCommandCompatibilityRepository.findAll().size();
        // set the field null
        mEncountersCommandCompatibility.setDefenseMagnification(null);

        // Create the MEncountersCommandCompatibility, which fails.
        MEncountersCommandCompatibilityDTO mEncountersCommandCompatibilityDTO = mEncountersCommandCompatibilityMapper.toDto(mEncountersCommandCompatibility);

        restMEncountersCommandCompatibilityMockMvc.perform(post("/api/m-encounters-command-compatibilities")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mEncountersCommandCompatibilityDTO)))
            .andExpect(status().isBadRequest());

        List<MEncountersCommandCompatibility> mEncountersCommandCompatibilityList = mEncountersCommandCompatibilityRepository.findAll();
        assertThat(mEncountersCommandCompatibilityList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllMEncountersCommandCompatibilities() throws Exception {
        // Initialize the database
        mEncountersCommandCompatibilityRepository.saveAndFlush(mEncountersCommandCompatibility);

        // Get all the mEncountersCommandCompatibilityList
        restMEncountersCommandCompatibilityMockMvc.perform(get("/api/m-encounters-command-compatibilities?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(mEncountersCommandCompatibility.getId().intValue())))
            .andExpect(jsonPath("$.[*].encountersType").value(hasItem(DEFAULT_ENCOUNTERS_TYPE)))
            .andExpect(jsonPath("$.[*].offenseCommandType").value(hasItem(DEFAULT_OFFENSE_COMMAND_TYPE)))
            .andExpect(jsonPath("$.[*].defenseCommandType").value(hasItem(DEFAULT_DEFENSE_COMMAND_TYPE)))
            .andExpect(jsonPath("$.[*].offenseMagnification").value(hasItem(DEFAULT_OFFENSE_MAGNIFICATION)))
            .andExpect(jsonPath("$.[*].defenseMagnification").value(hasItem(DEFAULT_DEFENSE_MAGNIFICATION)));
    }
    
    @Test
    @Transactional
    public void getMEncountersCommandCompatibility() throws Exception {
        // Initialize the database
        mEncountersCommandCompatibilityRepository.saveAndFlush(mEncountersCommandCompatibility);

        // Get the mEncountersCommandCompatibility
        restMEncountersCommandCompatibilityMockMvc.perform(get("/api/m-encounters-command-compatibilities/{id}", mEncountersCommandCompatibility.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(mEncountersCommandCompatibility.getId().intValue()))
            .andExpect(jsonPath("$.encountersType").value(DEFAULT_ENCOUNTERS_TYPE))
            .andExpect(jsonPath("$.offenseCommandType").value(DEFAULT_OFFENSE_COMMAND_TYPE))
            .andExpect(jsonPath("$.defenseCommandType").value(DEFAULT_DEFENSE_COMMAND_TYPE))
            .andExpect(jsonPath("$.offenseMagnification").value(DEFAULT_OFFENSE_MAGNIFICATION))
            .andExpect(jsonPath("$.defenseMagnification").value(DEFAULT_DEFENSE_MAGNIFICATION));
    }

    @Test
    @Transactional
    public void getAllMEncountersCommandCompatibilitiesByEncountersTypeIsEqualToSomething() throws Exception {
        // Initialize the database
        mEncountersCommandCompatibilityRepository.saveAndFlush(mEncountersCommandCompatibility);

        // Get all the mEncountersCommandCompatibilityList where encountersType equals to DEFAULT_ENCOUNTERS_TYPE
        defaultMEncountersCommandCompatibilityShouldBeFound("encountersType.equals=" + DEFAULT_ENCOUNTERS_TYPE);

        // Get all the mEncountersCommandCompatibilityList where encountersType equals to UPDATED_ENCOUNTERS_TYPE
        defaultMEncountersCommandCompatibilityShouldNotBeFound("encountersType.equals=" + UPDATED_ENCOUNTERS_TYPE);
    }

    @Test
    @Transactional
    public void getAllMEncountersCommandCompatibilitiesByEncountersTypeIsInShouldWork() throws Exception {
        // Initialize the database
        mEncountersCommandCompatibilityRepository.saveAndFlush(mEncountersCommandCompatibility);

        // Get all the mEncountersCommandCompatibilityList where encountersType in DEFAULT_ENCOUNTERS_TYPE or UPDATED_ENCOUNTERS_TYPE
        defaultMEncountersCommandCompatibilityShouldBeFound("encountersType.in=" + DEFAULT_ENCOUNTERS_TYPE + "," + UPDATED_ENCOUNTERS_TYPE);

        // Get all the mEncountersCommandCompatibilityList where encountersType equals to UPDATED_ENCOUNTERS_TYPE
        defaultMEncountersCommandCompatibilityShouldNotBeFound("encountersType.in=" + UPDATED_ENCOUNTERS_TYPE);
    }

    @Test
    @Transactional
    public void getAllMEncountersCommandCompatibilitiesByEncountersTypeIsNullOrNotNull() throws Exception {
        // Initialize the database
        mEncountersCommandCompatibilityRepository.saveAndFlush(mEncountersCommandCompatibility);

        // Get all the mEncountersCommandCompatibilityList where encountersType is not null
        defaultMEncountersCommandCompatibilityShouldBeFound("encountersType.specified=true");

        // Get all the mEncountersCommandCompatibilityList where encountersType is null
        defaultMEncountersCommandCompatibilityShouldNotBeFound("encountersType.specified=false");
    }

    @Test
    @Transactional
    public void getAllMEncountersCommandCompatibilitiesByEncountersTypeIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mEncountersCommandCompatibilityRepository.saveAndFlush(mEncountersCommandCompatibility);

        // Get all the mEncountersCommandCompatibilityList where encountersType greater than or equals to DEFAULT_ENCOUNTERS_TYPE
        defaultMEncountersCommandCompatibilityShouldBeFound("encountersType.greaterOrEqualThan=" + DEFAULT_ENCOUNTERS_TYPE);

        // Get all the mEncountersCommandCompatibilityList where encountersType greater than or equals to UPDATED_ENCOUNTERS_TYPE
        defaultMEncountersCommandCompatibilityShouldNotBeFound("encountersType.greaterOrEqualThan=" + UPDATED_ENCOUNTERS_TYPE);
    }

    @Test
    @Transactional
    public void getAllMEncountersCommandCompatibilitiesByEncountersTypeIsLessThanSomething() throws Exception {
        // Initialize the database
        mEncountersCommandCompatibilityRepository.saveAndFlush(mEncountersCommandCompatibility);

        // Get all the mEncountersCommandCompatibilityList where encountersType less than or equals to DEFAULT_ENCOUNTERS_TYPE
        defaultMEncountersCommandCompatibilityShouldNotBeFound("encountersType.lessThan=" + DEFAULT_ENCOUNTERS_TYPE);

        // Get all the mEncountersCommandCompatibilityList where encountersType less than or equals to UPDATED_ENCOUNTERS_TYPE
        defaultMEncountersCommandCompatibilityShouldBeFound("encountersType.lessThan=" + UPDATED_ENCOUNTERS_TYPE);
    }


    @Test
    @Transactional
    public void getAllMEncountersCommandCompatibilitiesByOffenseCommandTypeIsEqualToSomething() throws Exception {
        // Initialize the database
        mEncountersCommandCompatibilityRepository.saveAndFlush(mEncountersCommandCompatibility);

        // Get all the mEncountersCommandCompatibilityList where offenseCommandType equals to DEFAULT_OFFENSE_COMMAND_TYPE
        defaultMEncountersCommandCompatibilityShouldBeFound("offenseCommandType.equals=" + DEFAULT_OFFENSE_COMMAND_TYPE);

        // Get all the mEncountersCommandCompatibilityList where offenseCommandType equals to UPDATED_OFFENSE_COMMAND_TYPE
        defaultMEncountersCommandCompatibilityShouldNotBeFound("offenseCommandType.equals=" + UPDATED_OFFENSE_COMMAND_TYPE);
    }

    @Test
    @Transactional
    public void getAllMEncountersCommandCompatibilitiesByOffenseCommandTypeIsInShouldWork() throws Exception {
        // Initialize the database
        mEncountersCommandCompatibilityRepository.saveAndFlush(mEncountersCommandCompatibility);

        // Get all the mEncountersCommandCompatibilityList where offenseCommandType in DEFAULT_OFFENSE_COMMAND_TYPE or UPDATED_OFFENSE_COMMAND_TYPE
        defaultMEncountersCommandCompatibilityShouldBeFound("offenseCommandType.in=" + DEFAULT_OFFENSE_COMMAND_TYPE + "," + UPDATED_OFFENSE_COMMAND_TYPE);

        // Get all the mEncountersCommandCompatibilityList where offenseCommandType equals to UPDATED_OFFENSE_COMMAND_TYPE
        defaultMEncountersCommandCompatibilityShouldNotBeFound("offenseCommandType.in=" + UPDATED_OFFENSE_COMMAND_TYPE);
    }

    @Test
    @Transactional
    public void getAllMEncountersCommandCompatibilitiesByOffenseCommandTypeIsNullOrNotNull() throws Exception {
        // Initialize the database
        mEncountersCommandCompatibilityRepository.saveAndFlush(mEncountersCommandCompatibility);

        // Get all the mEncountersCommandCompatibilityList where offenseCommandType is not null
        defaultMEncountersCommandCompatibilityShouldBeFound("offenseCommandType.specified=true");

        // Get all the mEncountersCommandCompatibilityList where offenseCommandType is null
        defaultMEncountersCommandCompatibilityShouldNotBeFound("offenseCommandType.specified=false");
    }

    @Test
    @Transactional
    public void getAllMEncountersCommandCompatibilitiesByOffenseCommandTypeIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mEncountersCommandCompatibilityRepository.saveAndFlush(mEncountersCommandCompatibility);

        // Get all the mEncountersCommandCompatibilityList where offenseCommandType greater than or equals to DEFAULT_OFFENSE_COMMAND_TYPE
        defaultMEncountersCommandCompatibilityShouldBeFound("offenseCommandType.greaterOrEqualThan=" + DEFAULT_OFFENSE_COMMAND_TYPE);

        // Get all the mEncountersCommandCompatibilityList where offenseCommandType greater than or equals to UPDATED_OFFENSE_COMMAND_TYPE
        defaultMEncountersCommandCompatibilityShouldNotBeFound("offenseCommandType.greaterOrEqualThan=" + UPDATED_OFFENSE_COMMAND_TYPE);
    }

    @Test
    @Transactional
    public void getAllMEncountersCommandCompatibilitiesByOffenseCommandTypeIsLessThanSomething() throws Exception {
        // Initialize the database
        mEncountersCommandCompatibilityRepository.saveAndFlush(mEncountersCommandCompatibility);

        // Get all the mEncountersCommandCompatibilityList where offenseCommandType less than or equals to DEFAULT_OFFENSE_COMMAND_TYPE
        defaultMEncountersCommandCompatibilityShouldNotBeFound("offenseCommandType.lessThan=" + DEFAULT_OFFENSE_COMMAND_TYPE);

        // Get all the mEncountersCommandCompatibilityList where offenseCommandType less than or equals to UPDATED_OFFENSE_COMMAND_TYPE
        defaultMEncountersCommandCompatibilityShouldBeFound("offenseCommandType.lessThan=" + UPDATED_OFFENSE_COMMAND_TYPE);
    }


    @Test
    @Transactional
    public void getAllMEncountersCommandCompatibilitiesByDefenseCommandTypeIsEqualToSomething() throws Exception {
        // Initialize the database
        mEncountersCommandCompatibilityRepository.saveAndFlush(mEncountersCommandCompatibility);

        // Get all the mEncountersCommandCompatibilityList where defenseCommandType equals to DEFAULT_DEFENSE_COMMAND_TYPE
        defaultMEncountersCommandCompatibilityShouldBeFound("defenseCommandType.equals=" + DEFAULT_DEFENSE_COMMAND_TYPE);

        // Get all the mEncountersCommandCompatibilityList where defenseCommandType equals to UPDATED_DEFENSE_COMMAND_TYPE
        defaultMEncountersCommandCompatibilityShouldNotBeFound("defenseCommandType.equals=" + UPDATED_DEFENSE_COMMAND_TYPE);
    }

    @Test
    @Transactional
    public void getAllMEncountersCommandCompatibilitiesByDefenseCommandTypeIsInShouldWork() throws Exception {
        // Initialize the database
        mEncountersCommandCompatibilityRepository.saveAndFlush(mEncountersCommandCompatibility);

        // Get all the mEncountersCommandCompatibilityList where defenseCommandType in DEFAULT_DEFENSE_COMMAND_TYPE or UPDATED_DEFENSE_COMMAND_TYPE
        defaultMEncountersCommandCompatibilityShouldBeFound("defenseCommandType.in=" + DEFAULT_DEFENSE_COMMAND_TYPE + "," + UPDATED_DEFENSE_COMMAND_TYPE);

        // Get all the mEncountersCommandCompatibilityList where defenseCommandType equals to UPDATED_DEFENSE_COMMAND_TYPE
        defaultMEncountersCommandCompatibilityShouldNotBeFound("defenseCommandType.in=" + UPDATED_DEFENSE_COMMAND_TYPE);
    }

    @Test
    @Transactional
    public void getAllMEncountersCommandCompatibilitiesByDefenseCommandTypeIsNullOrNotNull() throws Exception {
        // Initialize the database
        mEncountersCommandCompatibilityRepository.saveAndFlush(mEncountersCommandCompatibility);

        // Get all the mEncountersCommandCompatibilityList where defenseCommandType is not null
        defaultMEncountersCommandCompatibilityShouldBeFound("defenseCommandType.specified=true");

        // Get all the mEncountersCommandCompatibilityList where defenseCommandType is null
        defaultMEncountersCommandCompatibilityShouldNotBeFound("defenseCommandType.specified=false");
    }

    @Test
    @Transactional
    public void getAllMEncountersCommandCompatibilitiesByDefenseCommandTypeIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mEncountersCommandCompatibilityRepository.saveAndFlush(mEncountersCommandCompatibility);

        // Get all the mEncountersCommandCompatibilityList where defenseCommandType greater than or equals to DEFAULT_DEFENSE_COMMAND_TYPE
        defaultMEncountersCommandCompatibilityShouldBeFound("defenseCommandType.greaterOrEqualThan=" + DEFAULT_DEFENSE_COMMAND_TYPE);

        // Get all the mEncountersCommandCompatibilityList where defenseCommandType greater than or equals to UPDATED_DEFENSE_COMMAND_TYPE
        defaultMEncountersCommandCompatibilityShouldNotBeFound("defenseCommandType.greaterOrEqualThan=" + UPDATED_DEFENSE_COMMAND_TYPE);
    }

    @Test
    @Transactional
    public void getAllMEncountersCommandCompatibilitiesByDefenseCommandTypeIsLessThanSomething() throws Exception {
        // Initialize the database
        mEncountersCommandCompatibilityRepository.saveAndFlush(mEncountersCommandCompatibility);

        // Get all the mEncountersCommandCompatibilityList where defenseCommandType less than or equals to DEFAULT_DEFENSE_COMMAND_TYPE
        defaultMEncountersCommandCompatibilityShouldNotBeFound("defenseCommandType.lessThan=" + DEFAULT_DEFENSE_COMMAND_TYPE);

        // Get all the mEncountersCommandCompatibilityList where defenseCommandType less than or equals to UPDATED_DEFENSE_COMMAND_TYPE
        defaultMEncountersCommandCompatibilityShouldBeFound("defenseCommandType.lessThan=" + UPDATED_DEFENSE_COMMAND_TYPE);
    }


    @Test
    @Transactional
    public void getAllMEncountersCommandCompatibilitiesByOffenseMagnificationIsEqualToSomething() throws Exception {
        // Initialize the database
        mEncountersCommandCompatibilityRepository.saveAndFlush(mEncountersCommandCompatibility);

        // Get all the mEncountersCommandCompatibilityList where offenseMagnification equals to DEFAULT_OFFENSE_MAGNIFICATION
        defaultMEncountersCommandCompatibilityShouldBeFound("offenseMagnification.equals=" + DEFAULT_OFFENSE_MAGNIFICATION);

        // Get all the mEncountersCommandCompatibilityList where offenseMagnification equals to UPDATED_OFFENSE_MAGNIFICATION
        defaultMEncountersCommandCompatibilityShouldNotBeFound("offenseMagnification.equals=" + UPDATED_OFFENSE_MAGNIFICATION);
    }

    @Test
    @Transactional
    public void getAllMEncountersCommandCompatibilitiesByOffenseMagnificationIsInShouldWork() throws Exception {
        // Initialize the database
        mEncountersCommandCompatibilityRepository.saveAndFlush(mEncountersCommandCompatibility);

        // Get all the mEncountersCommandCompatibilityList where offenseMagnification in DEFAULT_OFFENSE_MAGNIFICATION or UPDATED_OFFENSE_MAGNIFICATION
        defaultMEncountersCommandCompatibilityShouldBeFound("offenseMagnification.in=" + DEFAULT_OFFENSE_MAGNIFICATION + "," + UPDATED_OFFENSE_MAGNIFICATION);

        // Get all the mEncountersCommandCompatibilityList where offenseMagnification equals to UPDATED_OFFENSE_MAGNIFICATION
        defaultMEncountersCommandCompatibilityShouldNotBeFound("offenseMagnification.in=" + UPDATED_OFFENSE_MAGNIFICATION);
    }

    @Test
    @Transactional
    public void getAllMEncountersCommandCompatibilitiesByOffenseMagnificationIsNullOrNotNull() throws Exception {
        // Initialize the database
        mEncountersCommandCompatibilityRepository.saveAndFlush(mEncountersCommandCompatibility);

        // Get all the mEncountersCommandCompatibilityList where offenseMagnification is not null
        defaultMEncountersCommandCompatibilityShouldBeFound("offenseMagnification.specified=true");

        // Get all the mEncountersCommandCompatibilityList where offenseMagnification is null
        defaultMEncountersCommandCompatibilityShouldNotBeFound("offenseMagnification.specified=false");
    }

    @Test
    @Transactional
    public void getAllMEncountersCommandCompatibilitiesByOffenseMagnificationIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mEncountersCommandCompatibilityRepository.saveAndFlush(mEncountersCommandCompatibility);

        // Get all the mEncountersCommandCompatibilityList where offenseMagnification greater than or equals to DEFAULT_OFFENSE_MAGNIFICATION
        defaultMEncountersCommandCompatibilityShouldBeFound("offenseMagnification.greaterOrEqualThan=" + DEFAULT_OFFENSE_MAGNIFICATION);

        // Get all the mEncountersCommandCompatibilityList where offenseMagnification greater than or equals to UPDATED_OFFENSE_MAGNIFICATION
        defaultMEncountersCommandCompatibilityShouldNotBeFound("offenseMagnification.greaterOrEqualThan=" + UPDATED_OFFENSE_MAGNIFICATION);
    }

    @Test
    @Transactional
    public void getAllMEncountersCommandCompatibilitiesByOffenseMagnificationIsLessThanSomething() throws Exception {
        // Initialize the database
        mEncountersCommandCompatibilityRepository.saveAndFlush(mEncountersCommandCompatibility);

        // Get all the mEncountersCommandCompatibilityList where offenseMagnification less than or equals to DEFAULT_OFFENSE_MAGNIFICATION
        defaultMEncountersCommandCompatibilityShouldNotBeFound("offenseMagnification.lessThan=" + DEFAULT_OFFENSE_MAGNIFICATION);

        // Get all the mEncountersCommandCompatibilityList where offenseMagnification less than or equals to UPDATED_OFFENSE_MAGNIFICATION
        defaultMEncountersCommandCompatibilityShouldBeFound("offenseMagnification.lessThan=" + UPDATED_OFFENSE_MAGNIFICATION);
    }


    @Test
    @Transactional
    public void getAllMEncountersCommandCompatibilitiesByDefenseMagnificationIsEqualToSomething() throws Exception {
        // Initialize the database
        mEncountersCommandCompatibilityRepository.saveAndFlush(mEncountersCommandCompatibility);

        // Get all the mEncountersCommandCompatibilityList where defenseMagnification equals to DEFAULT_DEFENSE_MAGNIFICATION
        defaultMEncountersCommandCompatibilityShouldBeFound("defenseMagnification.equals=" + DEFAULT_DEFENSE_MAGNIFICATION);

        // Get all the mEncountersCommandCompatibilityList where defenseMagnification equals to UPDATED_DEFENSE_MAGNIFICATION
        defaultMEncountersCommandCompatibilityShouldNotBeFound("defenseMagnification.equals=" + UPDATED_DEFENSE_MAGNIFICATION);
    }

    @Test
    @Transactional
    public void getAllMEncountersCommandCompatibilitiesByDefenseMagnificationIsInShouldWork() throws Exception {
        // Initialize the database
        mEncountersCommandCompatibilityRepository.saveAndFlush(mEncountersCommandCompatibility);

        // Get all the mEncountersCommandCompatibilityList where defenseMagnification in DEFAULT_DEFENSE_MAGNIFICATION or UPDATED_DEFENSE_MAGNIFICATION
        defaultMEncountersCommandCompatibilityShouldBeFound("defenseMagnification.in=" + DEFAULT_DEFENSE_MAGNIFICATION + "," + UPDATED_DEFENSE_MAGNIFICATION);

        // Get all the mEncountersCommandCompatibilityList where defenseMagnification equals to UPDATED_DEFENSE_MAGNIFICATION
        defaultMEncountersCommandCompatibilityShouldNotBeFound("defenseMagnification.in=" + UPDATED_DEFENSE_MAGNIFICATION);
    }

    @Test
    @Transactional
    public void getAllMEncountersCommandCompatibilitiesByDefenseMagnificationIsNullOrNotNull() throws Exception {
        // Initialize the database
        mEncountersCommandCompatibilityRepository.saveAndFlush(mEncountersCommandCompatibility);

        // Get all the mEncountersCommandCompatibilityList where defenseMagnification is not null
        defaultMEncountersCommandCompatibilityShouldBeFound("defenseMagnification.specified=true");

        // Get all the mEncountersCommandCompatibilityList where defenseMagnification is null
        defaultMEncountersCommandCompatibilityShouldNotBeFound("defenseMagnification.specified=false");
    }

    @Test
    @Transactional
    public void getAllMEncountersCommandCompatibilitiesByDefenseMagnificationIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mEncountersCommandCompatibilityRepository.saveAndFlush(mEncountersCommandCompatibility);

        // Get all the mEncountersCommandCompatibilityList where defenseMagnification greater than or equals to DEFAULT_DEFENSE_MAGNIFICATION
        defaultMEncountersCommandCompatibilityShouldBeFound("defenseMagnification.greaterOrEqualThan=" + DEFAULT_DEFENSE_MAGNIFICATION);

        // Get all the mEncountersCommandCompatibilityList where defenseMagnification greater than or equals to UPDATED_DEFENSE_MAGNIFICATION
        defaultMEncountersCommandCompatibilityShouldNotBeFound("defenseMagnification.greaterOrEqualThan=" + UPDATED_DEFENSE_MAGNIFICATION);
    }

    @Test
    @Transactional
    public void getAllMEncountersCommandCompatibilitiesByDefenseMagnificationIsLessThanSomething() throws Exception {
        // Initialize the database
        mEncountersCommandCompatibilityRepository.saveAndFlush(mEncountersCommandCompatibility);

        // Get all the mEncountersCommandCompatibilityList where defenseMagnification less than or equals to DEFAULT_DEFENSE_MAGNIFICATION
        defaultMEncountersCommandCompatibilityShouldNotBeFound("defenseMagnification.lessThan=" + DEFAULT_DEFENSE_MAGNIFICATION);

        // Get all the mEncountersCommandCompatibilityList where defenseMagnification less than or equals to UPDATED_DEFENSE_MAGNIFICATION
        defaultMEncountersCommandCompatibilityShouldBeFound("defenseMagnification.lessThan=" + UPDATED_DEFENSE_MAGNIFICATION);
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultMEncountersCommandCompatibilityShouldBeFound(String filter) throws Exception {
        restMEncountersCommandCompatibilityMockMvc.perform(get("/api/m-encounters-command-compatibilities?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(mEncountersCommandCompatibility.getId().intValue())))
            .andExpect(jsonPath("$.[*].encountersType").value(hasItem(DEFAULT_ENCOUNTERS_TYPE)))
            .andExpect(jsonPath("$.[*].offenseCommandType").value(hasItem(DEFAULT_OFFENSE_COMMAND_TYPE)))
            .andExpect(jsonPath("$.[*].defenseCommandType").value(hasItem(DEFAULT_DEFENSE_COMMAND_TYPE)))
            .andExpect(jsonPath("$.[*].offenseMagnification").value(hasItem(DEFAULT_OFFENSE_MAGNIFICATION)))
            .andExpect(jsonPath("$.[*].defenseMagnification").value(hasItem(DEFAULT_DEFENSE_MAGNIFICATION)));

        // Check, that the count call also returns 1
        restMEncountersCommandCompatibilityMockMvc.perform(get("/api/m-encounters-command-compatibilities/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultMEncountersCommandCompatibilityShouldNotBeFound(String filter) throws Exception {
        restMEncountersCommandCompatibilityMockMvc.perform(get("/api/m-encounters-command-compatibilities?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restMEncountersCommandCompatibilityMockMvc.perform(get("/api/m-encounters-command-compatibilities/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("0"));
    }


    @Test
    @Transactional
    public void getNonExistingMEncountersCommandCompatibility() throws Exception {
        // Get the mEncountersCommandCompatibility
        restMEncountersCommandCompatibilityMockMvc.perform(get("/api/m-encounters-command-compatibilities/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateMEncountersCommandCompatibility() throws Exception {
        // Initialize the database
        mEncountersCommandCompatibilityRepository.saveAndFlush(mEncountersCommandCompatibility);

        int databaseSizeBeforeUpdate = mEncountersCommandCompatibilityRepository.findAll().size();

        // Update the mEncountersCommandCompatibility
        MEncountersCommandCompatibility updatedMEncountersCommandCompatibility = mEncountersCommandCompatibilityRepository.findById(mEncountersCommandCompatibility.getId()).get();
        // Disconnect from session so that the updates on updatedMEncountersCommandCompatibility are not directly saved in db
        em.detach(updatedMEncountersCommandCompatibility);
        updatedMEncountersCommandCompatibility
            .encountersType(UPDATED_ENCOUNTERS_TYPE)
            .offenseCommandType(UPDATED_OFFENSE_COMMAND_TYPE)
            .defenseCommandType(UPDATED_DEFENSE_COMMAND_TYPE)
            .offenseMagnification(UPDATED_OFFENSE_MAGNIFICATION)
            .defenseMagnification(UPDATED_DEFENSE_MAGNIFICATION);
        MEncountersCommandCompatibilityDTO mEncountersCommandCompatibilityDTO = mEncountersCommandCompatibilityMapper.toDto(updatedMEncountersCommandCompatibility);

        restMEncountersCommandCompatibilityMockMvc.perform(put("/api/m-encounters-command-compatibilities")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mEncountersCommandCompatibilityDTO)))
            .andExpect(status().isOk());

        // Validate the MEncountersCommandCompatibility in the database
        List<MEncountersCommandCompatibility> mEncountersCommandCompatibilityList = mEncountersCommandCompatibilityRepository.findAll();
        assertThat(mEncountersCommandCompatibilityList).hasSize(databaseSizeBeforeUpdate);
        MEncountersCommandCompatibility testMEncountersCommandCompatibility = mEncountersCommandCompatibilityList.get(mEncountersCommandCompatibilityList.size() - 1);
        assertThat(testMEncountersCommandCompatibility.getEncountersType()).isEqualTo(UPDATED_ENCOUNTERS_TYPE);
        assertThat(testMEncountersCommandCompatibility.getOffenseCommandType()).isEqualTo(UPDATED_OFFENSE_COMMAND_TYPE);
        assertThat(testMEncountersCommandCompatibility.getDefenseCommandType()).isEqualTo(UPDATED_DEFENSE_COMMAND_TYPE);
        assertThat(testMEncountersCommandCompatibility.getOffenseMagnification()).isEqualTo(UPDATED_OFFENSE_MAGNIFICATION);
        assertThat(testMEncountersCommandCompatibility.getDefenseMagnification()).isEqualTo(UPDATED_DEFENSE_MAGNIFICATION);
    }

    @Test
    @Transactional
    public void updateNonExistingMEncountersCommandCompatibility() throws Exception {
        int databaseSizeBeforeUpdate = mEncountersCommandCompatibilityRepository.findAll().size();

        // Create the MEncountersCommandCompatibility
        MEncountersCommandCompatibilityDTO mEncountersCommandCompatibilityDTO = mEncountersCommandCompatibilityMapper.toDto(mEncountersCommandCompatibility);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restMEncountersCommandCompatibilityMockMvc.perform(put("/api/m-encounters-command-compatibilities")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mEncountersCommandCompatibilityDTO)))
            .andExpect(status().isBadRequest());

        // Validate the MEncountersCommandCompatibility in the database
        List<MEncountersCommandCompatibility> mEncountersCommandCompatibilityList = mEncountersCommandCompatibilityRepository.findAll();
        assertThat(mEncountersCommandCompatibilityList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteMEncountersCommandCompatibility() throws Exception {
        // Initialize the database
        mEncountersCommandCompatibilityRepository.saveAndFlush(mEncountersCommandCompatibility);

        int databaseSizeBeforeDelete = mEncountersCommandCompatibilityRepository.findAll().size();

        // Delete the mEncountersCommandCompatibility
        restMEncountersCommandCompatibilityMockMvc.perform(delete("/api/m-encounters-command-compatibilities/{id}", mEncountersCommandCompatibility.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database is empty
        List<MEncountersCommandCompatibility> mEncountersCommandCompatibilityList = mEncountersCommandCompatibilityRepository.findAll();
        assertThat(mEncountersCommandCompatibilityList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(MEncountersCommandCompatibility.class);
        MEncountersCommandCompatibility mEncountersCommandCompatibility1 = new MEncountersCommandCompatibility();
        mEncountersCommandCompatibility1.setId(1L);
        MEncountersCommandCompatibility mEncountersCommandCompatibility2 = new MEncountersCommandCompatibility();
        mEncountersCommandCompatibility2.setId(mEncountersCommandCompatibility1.getId());
        assertThat(mEncountersCommandCompatibility1).isEqualTo(mEncountersCommandCompatibility2);
        mEncountersCommandCompatibility2.setId(2L);
        assertThat(mEncountersCommandCompatibility1).isNotEqualTo(mEncountersCommandCompatibility2);
        mEncountersCommandCompatibility1.setId(null);
        assertThat(mEncountersCommandCompatibility1).isNotEqualTo(mEncountersCommandCompatibility2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(MEncountersCommandCompatibilityDTO.class);
        MEncountersCommandCompatibilityDTO mEncountersCommandCompatibilityDTO1 = new MEncountersCommandCompatibilityDTO();
        mEncountersCommandCompatibilityDTO1.setId(1L);
        MEncountersCommandCompatibilityDTO mEncountersCommandCompatibilityDTO2 = new MEncountersCommandCompatibilityDTO();
        assertThat(mEncountersCommandCompatibilityDTO1).isNotEqualTo(mEncountersCommandCompatibilityDTO2);
        mEncountersCommandCompatibilityDTO2.setId(mEncountersCommandCompatibilityDTO1.getId());
        assertThat(mEncountersCommandCompatibilityDTO1).isEqualTo(mEncountersCommandCompatibilityDTO2);
        mEncountersCommandCompatibilityDTO2.setId(2L);
        assertThat(mEncountersCommandCompatibilityDTO1).isNotEqualTo(mEncountersCommandCompatibilityDTO2);
        mEncountersCommandCompatibilityDTO1.setId(null);
        assertThat(mEncountersCommandCompatibilityDTO1).isNotEqualTo(mEncountersCommandCompatibilityDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(mEncountersCommandCompatibilityMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(mEncountersCommandCompatibilityMapper.fromId(null)).isNull();
    }
}
