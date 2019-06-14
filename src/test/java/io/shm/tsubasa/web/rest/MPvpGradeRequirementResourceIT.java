package io.shm.tsubasa.web.rest;

import io.shm.tsubasa.TsubasaApp;
import io.shm.tsubasa.domain.MPvpGradeRequirement;
import io.shm.tsubasa.repository.MPvpGradeRequirementRepository;
import io.shm.tsubasa.service.MPvpGradeRequirementService;
import io.shm.tsubasa.service.dto.MPvpGradeRequirementDTO;
import io.shm.tsubasa.service.mapper.MPvpGradeRequirementMapper;
import io.shm.tsubasa.web.rest.errors.ExceptionTranslator;
import io.shm.tsubasa.service.dto.MPvpGradeRequirementCriteria;
import io.shm.tsubasa.service.MPvpGradeRequirementQueryService;

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
 * Integration tests for the {@Link MPvpGradeRequirementResource} REST controller.
 */
@SpringBootTest(classes = TsubasaApp.class)
public class MPvpGradeRequirementResourceIT {

    private static final String DEFAULT_UP_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_UP_DESCRIPTION = "BBBBBBBBBB";

    private static final String DEFAULT_DOWN_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_DOWN_DESCRIPTION = "BBBBBBBBBB";

    private static final Integer DEFAULT_TARGET_TYPE = 1;
    private static final Integer UPDATED_TARGET_TYPE = 2;

    private static final Integer DEFAULT_TARGET_WAVE = 1;
    private static final Integer UPDATED_TARGET_WAVE = 2;

    private static final Integer DEFAULT_TARGET_LOWER_GRADE = 1;
    private static final Integer UPDATED_TARGET_LOWER_GRADE = 2;

    private static final Integer DEFAULT_TARGET_POINT = 1;
    private static final Integer UPDATED_TARGET_POINT = 2;

    @Autowired
    private MPvpGradeRequirementRepository mPvpGradeRequirementRepository;

    @Autowired
    private MPvpGradeRequirementMapper mPvpGradeRequirementMapper;

    @Autowired
    private MPvpGradeRequirementService mPvpGradeRequirementService;

    @Autowired
    private MPvpGradeRequirementQueryService mPvpGradeRequirementQueryService;

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

    private MockMvc restMPvpGradeRequirementMockMvc;

    private MPvpGradeRequirement mPvpGradeRequirement;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final MPvpGradeRequirementResource mPvpGradeRequirementResource = new MPvpGradeRequirementResource(mPvpGradeRequirementService, mPvpGradeRequirementQueryService);
        this.restMPvpGradeRequirementMockMvc = MockMvcBuilders.standaloneSetup(mPvpGradeRequirementResource)
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
    public static MPvpGradeRequirement createEntity(EntityManager em) {
        MPvpGradeRequirement mPvpGradeRequirement = new MPvpGradeRequirement()
            .upDescription(DEFAULT_UP_DESCRIPTION)
            .downDescription(DEFAULT_DOWN_DESCRIPTION)
            .targetType(DEFAULT_TARGET_TYPE)
            .targetWave(DEFAULT_TARGET_WAVE)
            .targetLowerGrade(DEFAULT_TARGET_LOWER_GRADE)
            .targetPoint(DEFAULT_TARGET_POINT);
        return mPvpGradeRequirement;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static MPvpGradeRequirement createUpdatedEntity(EntityManager em) {
        MPvpGradeRequirement mPvpGradeRequirement = new MPvpGradeRequirement()
            .upDescription(UPDATED_UP_DESCRIPTION)
            .downDescription(UPDATED_DOWN_DESCRIPTION)
            .targetType(UPDATED_TARGET_TYPE)
            .targetWave(UPDATED_TARGET_WAVE)
            .targetLowerGrade(UPDATED_TARGET_LOWER_GRADE)
            .targetPoint(UPDATED_TARGET_POINT);
        return mPvpGradeRequirement;
    }

    @BeforeEach
    public void initTest() {
        mPvpGradeRequirement = createEntity(em);
    }

    @Test
    @Transactional
    public void createMPvpGradeRequirement() throws Exception {
        int databaseSizeBeforeCreate = mPvpGradeRequirementRepository.findAll().size();

        // Create the MPvpGradeRequirement
        MPvpGradeRequirementDTO mPvpGradeRequirementDTO = mPvpGradeRequirementMapper.toDto(mPvpGradeRequirement);
        restMPvpGradeRequirementMockMvc.perform(post("/api/m-pvp-grade-requirements")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mPvpGradeRequirementDTO)))
            .andExpect(status().isCreated());

        // Validate the MPvpGradeRequirement in the database
        List<MPvpGradeRequirement> mPvpGradeRequirementList = mPvpGradeRequirementRepository.findAll();
        assertThat(mPvpGradeRequirementList).hasSize(databaseSizeBeforeCreate + 1);
        MPvpGradeRequirement testMPvpGradeRequirement = mPvpGradeRequirementList.get(mPvpGradeRequirementList.size() - 1);
        assertThat(testMPvpGradeRequirement.getUpDescription()).isEqualTo(DEFAULT_UP_DESCRIPTION);
        assertThat(testMPvpGradeRequirement.getDownDescription()).isEqualTo(DEFAULT_DOWN_DESCRIPTION);
        assertThat(testMPvpGradeRequirement.getTargetType()).isEqualTo(DEFAULT_TARGET_TYPE);
        assertThat(testMPvpGradeRequirement.getTargetWave()).isEqualTo(DEFAULT_TARGET_WAVE);
        assertThat(testMPvpGradeRequirement.getTargetLowerGrade()).isEqualTo(DEFAULT_TARGET_LOWER_GRADE);
        assertThat(testMPvpGradeRequirement.getTargetPoint()).isEqualTo(DEFAULT_TARGET_POINT);
    }

    @Test
    @Transactional
    public void createMPvpGradeRequirementWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = mPvpGradeRequirementRepository.findAll().size();

        // Create the MPvpGradeRequirement with an existing ID
        mPvpGradeRequirement.setId(1L);
        MPvpGradeRequirementDTO mPvpGradeRequirementDTO = mPvpGradeRequirementMapper.toDto(mPvpGradeRequirement);

        // An entity with an existing ID cannot be created, so this API call must fail
        restMPvpGradeRequirementMockMvc.perform(post("/api/m-pvp-grade-requirements")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mPvpGradeRequirementDTO)))
            .andExpect(status().isBadRequest());

        // Validate the MPvpGradeRequirement in the database
        List<MPvpGradeRequirement> mPvpGradeRequirementList = mPvpGradeRequirementRepository.findAll();
        assertThat(mPvpGradeRequirementList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkTargetTypeIsRequired() throws Exception {
        int databaseSizeBeforeTest = mPvpGradeRequirementRepository.findAll().size();
        // set the field null
        mPvpGradeRequirement.setTargetType(null);

        // Create the MPvpGradeRequirement, which fails.
        MPvpGradeRequirementDTO mPvpGradeRequirementDTO = mPvpGradeRequirementMapper.toDto(mPvpGradeRequirement);

        restMPvpGradeRequirementMockMvc.perform(post("/api/m-pvp-grade-requirements")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mPvpGradeRequirementDTO)))
            .andExpect(status().isBadRequest());

        List<MPvpGradeRequirement> mPvpGradeRequirementList = mPvpGradeRequirementRepository.findAll();
        assertThat(mPvpGradeRequirementList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkTargetWaveIsRequired() throws Exception {
        int databaseSizeBeforeTest = mPvpGradeRequirementRepository.findAll().size();
        // set the field null
        mPvpGradeRequirement.setTargetWave(null);

        // Create the MPvpGradeRequirement, which fails.
        MPvpGradeRequirementDTO mPvpGradeRequirementDTO = mPvpGradeRequirementMapper.toDto(mPvpGradeRequirement);

        restMPvpGradeRequirementMockMvc.perform(post("/api/m-pvp-grade-requirements")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mPvpGradeRequirementDTO)))
            .andExpect(status().isBadRequest());

        List<MPvpGradeRequirement> mPvpGradeRequirementList = mPvpGradeRequirementRepository.findAll();
        assertThat(mPvpGradeRequirementList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkTargetLowerGradeIsRequired() throws Exception {
        int databaseSizeBeforeTest = mPvpGradeRequirementRepository.findAll().size();
        // set the field null
        mPvpGradeRequirement.setTargetLowerGrade(null);

        // Create the MPvpGradeRequirement, which fails.
        MPvpGradeRequirementDTO mPvpGradeRequirementDTO = mPvpGradeRequirementMapper.toDto(mPvpGradeRequirement);

        restMPvpGradeRequirementMockMvc.perform(post("/api/m-pvp-grade-requirements")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mPvpGradeRequirementDTO)))
            .andExpect(status().isBadRequest());

        List<MPvpGradeRequirement> mPvpGradeRequirementList = mPvpGradeRequirementRepository.findAll();
        assertThat(mPvpGradeRequirementList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkTargetPointIsRequired() throws Exception {
        int databaseSizeBeforeTest = mPvpGradeRequirementRepository.findAll().size();
        // set the field null
        mPvpGradeRequirement.setTargetPoint(null);

        // Create the MPvpGradeRequirement, which fails.
        MPvpGradeRequirementDTO mPvpGradeRequirementDTO = mPvpGradeRequirementMapper.toDto(mPvpGradeRequirement);

        restMPvpGradeRequirementMockMvc.perform(post("/api/m-pvp-grade-requirements")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mPvpGradeRequirementDTO)))
            .andExpect(status().isBadRequest());

        List<MPvpGradeRequirement> mPvpGradeRequirementList = mPvpGradeRequirementRepository.findAll();
        assertThat(mPvpGradeRequirementList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllMPvpGradeRequirements() throws Exception {
        // Initialize the database
        mPvpGradeRequirementRepository.saveAndFlush(mPvpGradeRequirement);

        // Get all the mPvpGradeRequirementList
        restMPvpGradeRequirementMockMvc.perform(get("/api/m-pvp-grade-requirements?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(mPvpGradeRequirement.getId().intValue())))
            .andExpect(jsonPath("$.[*].upDescription").value(hasItem(DEFAULT_UP_DESCRIPTION.toString())))
            .andExpect(jsonPath("$.[*].downDescription").value(hasItem(DEFAULT_DOWN_DESCRIPTION.toString())))
            .andExpect(jsonPath("$.[*].targetType").value(hasItem(DEFAULT_TARGET_TYPE)))
            .andExpect(jsonPath("$.[*].targetWave").value(hasItem(DEFAULT_TARGET_WAVE)))
            .andExpect(jsonPath("$.[*].targetLowerGrade").value(hasItem(DEFAULT_TARGET_LOWER_GRADE)))
            .andExpect(jsonPath("$.[*].targetPoint").value(hasItem(DEFAULT_TARGET_POINT)));
    }
    
    @Test
    @Transactional
    public void getMPvpGradeRequirement() throws Exception {
        // Initialize the database
        mPvpGradeRequirementRepository.saveAndFlush(mPvpGradeRequirement);

        // Get the mPvpGradeRequirement
        restMPvpGradeRequirementMockMvc.perform(get("/api/m-pvp-grade-requirements/{id}", mPvpGradeRequirement.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(mPvpGradeRequirement.getId().intValue()))
            .andExpect(jsonPath("$.upDescription").value(DEFAULT_UP_DESCRIPTION.toString()))
            .andExpect(jsonPath("$.downDescription").value(DEFAULT_DOWN_DESCRIPTION.toString()))
            .andExpect(jsonPath("$.targetType").value(DEFAULT_TARGET_TYPE))
            .andExpect(jsonPath("$.targetWave").value(DEFAULT_TARGET_WAVE))
            .andExpect(jsonPath("$.targetLowerGrade").value(DEFAULT_TARGET_LOWER_GRADE))
            .andExpect(jsonPath("$.targetPoint").value(DEFAULT_TARGET_POINT));
    }

    @Test
    @Transactional
    public void getAllMPvpGradeRequirementsByTargetTypeIsEqualToSomething() throws Exception {
        // Initialize the database
        mPvpGradeRequirementRepository.saveAndFlush(mPvpGradeRequirement);

        // Get all the mPvpGradeRequirementList where targetType equals to DEFAULT_TARGET_TYPE
        defaultMPvpGradeRequirementShouldBeFound("targetType.equals=" + DEFAULT_TARGET_TYPE);

        // Get all the mPvpGradeRequirementList where targetType equals to UPDATED_TARGET_TYPE
        defaultMPvpGradeRequirementShouldNotBeFound("targetType.equals=" + UPDATED_TARGET_TYPE);
    }

    @Test
    @Transactional
    public void getAllMPvpGradeRequirementsByTargetTypeIsInShouldWork() throws Exception {
        // Initialize the database
        mPvpGradeRequirementRepository.saveAndFlush(mPvpGradeRequirement);

        // Get all the mPvpGradeRequirementList where targetType in DEFAULT_TARGET_TYPE or UPDATED_TARGET_TYPE
        defaultMPvpGradeRequirementShouldBeFound("targetType.in=" + DEFAULT_TARGET_TYPE + "," + UPDATED_TARGET_TYPE);

        // Get all the mPvpGradeRequirementList where targetType equals to UPDATED_TARGET_TYPE
        defaultMPvpGradeRequirementShouldNotBeFound("targetType.in=" + UPDATED_TARGET_TYPE);
    }

    @Test
    @Transactional
    public void getAllMPvpGradeRequirementsByTargetTypeIsNullOrNotNull() throws Exception {
        // Initialize the database
        mPvpGradeRequirementRepository.saveAndFlush(mPvpGradeRequirement);

        // Get all the mPvpGradeRequirementList where targetType is not null
        defaultMPvpGradeRequirementShouldBeFound("targetType.specified=true");

        // Get all the mPvpGradeRequirementList where targetType is null
        defaultMPvpGradeRequirementShouldNotBeFound("targetType.specified=false");
    }

    @Test
    @Transactional
    public void getAllMPvpGradeRequirementsByTargetTypeIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mPvpGradeRequirementRepository.saveAndFlush(mPvpGradeRequirement);

        // Get all the mPvpGradeRequirementList where targetType greater than or equals to DEFAULT_TARGET_TYPE
        defaultMPvpGradeRequirementShouldBeFound("targetType.greaterOrEqualThan=" + DEFAULT_TARGET_TYPE);

        // Get all the mPvpGradeRequirementList where targetType greater than or equals to UPDATED_TARGET_TYPE
        defaultMPvpGradeRequirementShouldNotBeFound("targetType.greaterOrEqualThan=" + UPDATED_TARGET_TYPE);
    }

    @Test
    @Transactional
    public void getAllMPvpGradeRequirementsByTargetTypeIsLessThanSomething() throws Exception {
        // Initialize the database
        mPvpGradeRequirementRepository.saveAndFlush(mPvpGradeRequirement);

        // Get all the mPvpGradeRequirementList where targetType less than or equals to DEFAULT_TARGET_TYPE
        defaultMPvpGradeRequirementShouldNotBeFound("targetType.lessThan=" + DEFAULT_TARGET_TYPE);

        // Get all the mPvpGradeRequirementList where targetType less than or equals to UPDATED_TARGET_TYPE
        defaultMPvpGradeRequirementShouldBeFound("targetType.lessThan=" + UPDATED_TARGET_TYPE);
    }


    @Test
    @Transactional
    public void getAllMPvpGradeRequirementsByTargetWaveIsEqualToSomething() throws Exception {
        // Initialize the database
        mPvpGradeRequirementRepository.saveAndFlush(mPvpGradeRequirement);

        // Get all the mPvpGradeRequirementList where targetWave equals to DEFAULT_TARGET_WAVE
        defaultMPvpGradeRequirementShouldBeFound("targetWave.equals=" + DEFAULT_TARGET_WAVE);

        // Get all the mPvpGradeRequirementList where targetWave equals to UPDATED_TARGET_WAVE
        defaultMPvpGradeRequirementShouldNotBeFound("targetWave.equals=" + UPDATED_TARGET_WAVE);
    }

    @Test
    @Transactional
    public void getAllMPvpGradeRequirementsByTargetWaveIsInShouldWork() throws Exception {
        // Initialize the database
        mPvpGradeRequirementRepository.saveAndFlush(mPvpGradeRequirement);

        // Get all the mPvpGradeRequirementList where targetWave in DEFAULT_TARGET_WAVE or UPDATED_TARGET_WAVE
        defaultMPvpGradeRequirementShouldBeFound("targetWave.in=" + DEFAULT_TARGET_WAVE + "," + UPDATED_TARGET_WAVE);

        // Get all the mPvpGradeRequirementList where targetWave equals to UPDATED_TARGET_WAVE
        defaultMPvpGradeRequirementShouldNotBeFound("targetWave.in=" + UPDATED_TARGET_WAVE);
    }

    @Test
    @Transactional
    public void getAllMPvpGradeRequirementsByTargetWaveIsNullOrNotNull() throws Exception {
        // Initialize the database
        mPvpGradeRequirementRepository.saveAndFlush(mPvpGradeRequirement);

        // Get all the mPvpGradeRequirementList where targetWave is not null
        defaultMPvpGradeRequirementShouldBeFound("targetWave.specified=true");

        // Get all the mPvpGradeRequirementList where targetWave is null
        defaultMPvpGradeRequirementShouldNotBeFound("targetWave.specified=false");
    }

    @Test
    @Transactional
    public void getAllMPvpGradeRequirementsByTargetWaveIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mPvpGradeRequirementRepository.saveAndFlush(mPvpGradeRequirement);

        // Get all the mPvpGradeRequirementList where targetWave greater than or equals to DEFAULT_TARGET_WAVE
        defaultMPvpGradeRequirementShouldBeFound("targetWave.greaterOrEqualThan=" + DEFAULT_TARGET_WAVE);

        // Get all the mPvpGradeRequirementList where targetWave greater than or equals to UPDATED_TARGET_WAVE
        defaultMPvpGradeRequirementShouldNotBeFound("targetWave.greaterOrEqualThan=" + UPDATED_TARGET_WAVE);
    }

    @Test
    @Transactional
    public void getAllMPvpGradeRequirementsByTargetWaveIsLessThanSomething() throws Exception {
        // Initialize the database
        mPvpGradeRequirementRepository.saveAndFlush(mPvpGradeRequirement);

        // Get all the mPvpGradeRequirementList where targetWave less than or equals to DEFAULT_TARGET_WAVE
        defaultMPvpGradeRequirementShouldNotBeFound("targetWave.lessThan=" + DEFAULT_TARGET_WAVE);

        // Get all the mPvpGradeRequirementList where targetWave less than or equals to UPDATED_TARGET_WAVE
        defaultMPvpGradeRequirementShouldBeFound("targetWave.lessThan=" + UPDATED_TARGET_WAVE);
    }


    @Test
    @Transactional
    public void getAllMPvpGradeRequirementsByTargetLowerGradeIsEqualToSomething() throws Exception {
        // Initialize the database
        mPvpGradeRequirementRepository.saveAndFlush(mPvpGradeRequirement);

        // Get all the mPvpGradeRequirementList where targetLowerGrade equals to DEFAULT_TARGET_LOWER_GRADE
        defaultMPvpGradeRequirementShouldBeFound("targetLowerGrade.equals=" + DEFAULT_TARGET_LOWER_GRADE);

        // Get all the mPvpGradeRequirementList where targetLowerGrade equals to UPDATED_TARGET_LOWER_GRADE
        defaultMPvpGradeRequirementShouldNotBeFound("targetLowerGrade.equals=" + UPDATED_TARGET_LOWER_GRADE);
    }

    @Test
    @Transactional
    public void getAllMPvpGradeRequirementsByTargetLowerGradeIsInShouldWork() throws Exception {
        // Initialize the database
        mPvpGradeRequirementRepository.saveAndFlush(mPvpGradeRequirement);

        // Get all the mPvpGradeRequirementList where targetLowerGrade in DEFAULT_TARGET_LOWER_GRADE or UPDATED_TARGET_LOWER_GRADE
        defaultMPvpGradeRequirementShouldBeFound("targetLowerGrade.in=" + DEFAULT_TARGET_LOWER_GRADE + "," + UPDATED_TARGET_LOWER_GRADE);

        // Get all the mPvpGradeRequirementList where targetLowerGrade equals to UPDATED_TARGET_LOWER_GRADE
        defaultMPvpGradeRequirementShouldNotBeFound("targetLowerGrade.in=" + UPDATED_TARGET_LOWER_GRADE);
    }

    @Test
    @Transactional
    public void getAllMPvpGradeRequirementsByTargetLowerGradeIsNullOrNotNull() throws Exception {
        // Initialize the database
        mPvpGradeRequirementRepository.saveAndFlush(mPvpGradeRequirement);

        // Get all the mPvpGradeRequirementList where targetLowerGrade is not null
        defaultMPvpGradeRequirementShouldBeFound("targetLowerGrade.specified=true");

        // Get all the mPvpGradeRequirementList where targetLowerGrade is null
        defaultMPvpGradeRequirementShouldNotBeFound("targetLowerGrade.specified=false");
    }

    @Test
    @Transactional
    public void getAllMPvpGradeRequirementsByTargetLowerGradeIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mPvpGradeRequirementRepository.saveAndFlush(mPvpGradeRequirement);

        // Get all the mPvpGradeRequirementList where targetLowerGrade greater than or equals to DEFAULT_TARGET_LOWER_GRADE
        defaultMPvpGradeRequirementShouldBeFound("targetLowerGrade.greaterOrEqualThan=" + DEFAULT_TARGET_LOWER_GRADE);

        // Get all the mPvpGradeRequirementList where targetLowerGrade greater than or equals to UPDATED_TARGET_LOWER_GRADE
        defaultMPvpGradeRequirementShouldNotBeFound("targetLowerGrade.greaterOrEqualThan=" + UPDATED_TARGET_LOWER_GRADE);
    }

    @Test
    @Transactional
    public void getAllMPvpGradeRequirementsByTargetLowerGradeIsLessThanSomething() throws Exception {
        // Initialize the database
        mPvpGradeRequirementRepository.saveAndFlush(mPvpGradeRequirement);

        // Get all the mPvpGradeRequirementList where targetLowerGrade less than or equals to DEFAULT_TARGET_LOWER_GRADE
        defaultMPvpGradeRequirementShouldNotBeFound("targetLowerGrade.lessThan=" + DEFAULT_TARGET_LOWER_GRADE);

        // Get all the mPvpGradeRequirementList where targetLowerGrade less than or equals to UPDATED_TARGET_LOWER_GRADE
        defaultMPvpGradeRequirementShouldBeFound("targetLowerGrade.lessThan=" + UPDATED_TARGET_LOWER_GRADE);
    }


    @Test
    @Transactional
    public void getAllMPvpGradeRequirementsByTargetPointIsEqualToSomething() throws Exception {
        // Initialize the database
        mPvpGradeRequirementRepository.saveAndFlush(mPvpGradeRequirement);

        // Get all the mPvpGradeRequirementList where targetPoint equals to DEFAULT_TARGET_POINT
        defaultMPvpGradeRequirementShouldBeFound("targetPoint.equals=" + DEFAULT_TARGET_POINT);

        // Get all the mPvpGradeRequirementList where targetPoint equals to UPDATED_TARGET_POINT
        defaultMPvpGradeRequirementShouldNotBeFound("targetPoint.equals=" + UPDATED_TARGET_POINT);
    }

    @Test
    @Transactional
    public void getAllMPvpGradeRequirementsByTargetPointIsInShouldWork() throws Exception {
        // Initialize the database
        mPvpGradeRequirementRepository.saveAndFlush(mPvpGradeRequirement);

        // Get all the mPvpGradeRequirementList where targetPoint in DEFAULT_TARGET_POINT or UPDATED_TARGET_POINT
        defaultMPvpGradeRequirementShouldBeFound("targetPoint.in=" + DEFAULT_TARGET_POINT + "," + UPDATED_TARGET_POINT);

        // Get all the mPvpGradeRequirementList where targetPoint equals to UPDATED_TARGET_POINT
        defaultMPvpGradeRequirementShouldNotBeFound("targetPoint.in=" + UPDATED_TARGET_POINT);
    }

    @Test
    @Transactional
    public void getAllMPvpGradeRequirementsByTargetPointIsNullOrNotNull() throws Exception {
        // Initialize the database
        mPvpGradeRequirementRepository.saveAndFlush(mPvpGradeRequirement);

        // Get all the mPvpGradeRequirementList where targetPoint is not null
        defaultMPvpGradeRequirementShouldBeFound("targetPoint.specified=true");

        // Get all the mPvpGradeRequirementList where targetPoint is null
        defaultMPvpGradeRequirementShouldNotBeFound("targetPoint.specified=false");
    }

    @Test
    @Transactional
    public void getAllMPvpGradeRequirementsByTargetPointIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mPvpGradeRequirementRepository.saveAndFlush(mPvpGradeRequirement);

        // Get all the mPvpGradeRequirementList where targetPoint greater than or equals to DEFAULT_TARGET_POINT
        defaultMPvpGradeRequirementShouldBeFound("targetPoint.greaterOrEqualThan=" + DEFAULT_TARGET_POINT);

        // Get all the mPvpGradeRequirementList where targetPoint greater than or equals to UPDATED_TARGET_POINT
        defaultMPvpGradeRequirementShouldNotBeFound("targetPoint.greaterOrEqualThan=" + UPDATED_TARGET_POINT);
    }

    @Test
    @Transactional
    public void getAllMPvpGradeRequirementsByTargetPointIsLessThanSomething() throws Exception {
        // Initialize the database
        mPvpGradeRequirementRepository.saveAndFlush(mPvpGradeRequirement);

        // Get all the mPvpGradeRequirementList where targetPoint less than or equals to DEFAULT_TARGET_POINT
        defaultMPvpGradeRequirementShouldNotBeFound("targetPoint.lessThan=" + DEFAULT_TARGET_POINT);

        // Get all the mPvpGradeRequirementList where targetPoint less than or equals to UPDATED_TARGET_POINT
        defaultMPvpGradeRequirementShouldBeFound("targetPoint.lessThan=" + UPDATED_TARGET_POINT);
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultMPvpGradeRequirementShouldBeFound(String filter) throws Exception {
        restMPvpGradeRequirementMockMvc.perform(get("/api/m-pvp-grade-requirements?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(mPvpGradeRequirement.getId().intValue())))
            .andExpect(jsonPath("$.[*].upDescription").value(hasItem(DEFAULT_UP_DESCRIPTION.toString())))
            .andExpect(jsonPath("$.[*].downDescription").value(hasItem(DEFAULT_DOWN_DESCRIPTION.toString())))
            .andExpect(jsonPath("$.[*].targetType").value(hasItem(DEFAULT_TARGET_TYPE)))
            .andExpect(jsonPath("$.[*].targetWave").value(hasItem(DEFAULT_TARGET_WAVE)))
            .andExpect(jsonPath("$.[*].targetLowerGrade").value(hasItem(DEFAULT_TARGET_LOWER_GRADE)))
            .andExpect(jsonPath("$.[*].targetPoint").value(hasItem(DEFAULT_TARGET_POINT)));

        // Check, that the count call also returns 1
        restMPvpGradeRequirementMockMvc.perform(get("/api/m-pvp-grade-requirements/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultMPvpGradeRequirementShouldNotBeFound(String filter) throws Exception {
        restMPvpGradeRequirementMockMvc.perform(get("/api/m-pvp-grade-requirements?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restMPvpGradeRequirementMockMvc.perform(get("/api/m-pvp-grade-requirements/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("0"));
    }


    @Test
    @Transactional
    public void getNonExistingMPvpGradeRequirement() throws Exception {
        // Get the mPvpGradeRequirement
        restMPvpGradeRequirementMockMvc.perform(get("/api/m-pvp-grade-requirements/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateMPvpGradeRequirement() throws Exception {
        // Initialize the database
        mPvpGradeRequirementRepository.saveAndFlush(mPvpGradeRequirement);

        int databaseSizeBeforeUpdate = mPvpGradeRequirementRepository.findAll().size();

        // Update the mPvpGradeRequirement
        MPvpGradeRequirement updatedMPvpGradeRequirement = mPvpGradeRequirementRepository.findById(mPvpGradeRequirement.getId()).get();
        // Disconnect from session so that the updates on updatedMPvpGradeRequirement are not directly saved in db
        em.detach(updatedMPvpGradeRequirement);
        updatedMPvpGradeRequirement
            .upDescription(UPDATED_UP_DESCRIPTION)
            .downDescription(UPDATED_DOWN_DESCRIPTION)
            .targetType(UPDATED_TARGET_TYPE)
            .targetWave(UPDATED_TARGET_WAVE)
            .targetLowerGrade(UPDATED_TARGET_LOWER_GRADE)
            .targetPoint(UPDATED_TARGET_POINT);
        MPvpGradeRequirementDTO mPvpGradeRequirementDTO = mPvpGradeRequirementMapper.toDto(updatedMPvpGradeRequirement);

        restMPvpGradeRequirementMockMvc.perform(put("/api/m-pvp-grade-requirements")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mPvpGradeRequirementDTO)))
            .andExpect(status().isOk());

        // Validate the MPvpGradeRequirement in the database
        List<MPvpGradeRequirement> mPvpGradeRequirementList = mPvpGradeRequirementRepository.findAll();
        assertThat(mPvpGradeRequirementList).hasSize(databaseSizeBeforeUpdate);
        MPvpGradeRequirement testMPvpGradeRequirement = mPvpGradeRequirementList.get(mPvpGradeRequirementList.size() - 1);
        assertThat(testMPvpGradeRequirement.getUpDescription()).isEqualTo(UPDATED_UP_DESCRIPTION);
        assertThat(testMPvpGradeRequirement.getDownDescription()).isEqualTo(UPDATED_DOWN_DESCRIPTION);
        assertThat(testMPvpGradeRequirement.getTargetType()).isEqualTo(UPDATED_TARGET_TYPE);
        assertThat(testMPvpGradeRequirement.getTargetWave()).isEqualTo(UPDATED_TARGET_WAVE);
        assertThat(testMPvpGradeRequirement.getTargetLowerGrade()).isEqualTo(UPDATED_TARGET_LOWER_GRADE);
        assertThat(testMPvpGradeRequirement.getTargetPoint()).isEqualTo(UPDATED_TARGET_POINT);
    }

    @Test
    @Transactional
    public void updateNonExistingMPvpGradeRequirement() throws Exception {
        int databaseSizeBeforeUpdate = mPvpGradeRequirementRepository.findAll().size();

        // Create the MPvpGradeRequirement
        MPvpGradeRequirementDTO mPvpGradeRequirementDTO = mPvpGradeRequirementMapper.toDto(mPvpGradeRequirement);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restMPvpGradeRequirementMockMvc.perform(put("/api/m-pvp-grade-requirements")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mPvpGradeRequirementDTO)))
            .andExpect(status().isBadRequest());

        // Validate the MPvpGradeRequirement in the database
        List<MPvpGradeRequirement> mPvpGradeRequirementList = mPvpGradeRequirementRepository.findAll();
        assertThat(mPvpGradeRequirementList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteMPvpGradeRequirement() throws Exception {
        // Initialize the database
        mPvpGradeRequirementRepository.saveAndFlush(mPvpGradeRequirement);

        int databaseSizeBeforeDelete = mPvpGradeRequirementRepository.findAll().size();

        // Delete the mPvpGradeRequirement
        restMPvpGradeRequirementMockMvc.perform(delete("/api/m-pvp-grade-requirements/{id}", mPvpGradeRequirement.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database is empty
        List<MPvpGradeRequirement> mPvpGradeRequirementList = mPvpGradeRequirementRepository.findAll();
        assertThat(mPvpGradeRequirementList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(MPvpGradeRequirement.class);
        MPvpGradeRequirement mPvpGradeRequirement1 = new MPvpGradeRequirement();
        mPvpGradeRequirement1.setId(1L);
        MPvpGradeRequirement mPvpGradeRequirement2 = new MPvpGradeRequirement();
        mPvpGradeRequirement2.setId(mPvpGradeRequirement1.getId());
        assertThat(mPvpGradeRequirement1).isEqualTo(mPvpGradeRequirement2);
        mPvpGradeRequirement2.setId(2L);
        assertThat(mPvpGradeRequirement1).isNotEqualTo(mPvpGradeRequirement2);
        mPvpGradeRequirement1.setId(null);
        assertThat(mPvpGradeRequirement1).isNotEqualTo(mPvpGradeRequirement2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(MPvpGradeRequirementDTO.class);
        MPvpGradeRequirementDTO mPvpGradeRequirementDTO1 = new MPvpGradeRequirementDTO();
        mPvpGradeRequirementDTO1.setId(1L);
        MPvpGradeRequirementDTO mPvpGradeRequirementDTO2 = new MPvpGradeRequirementDTO();
        assertThat(mPvpGradeRequirementDTO1).isNotEqualTo(mPvpGradeRequirementDTO2);
        mPvpGradeRequirementDTO2.setId(mPvpGradeRequirementDTO1.getId());
        assertThat(mPvpGradeRequirementDTO1).isEqualTo(mPvpGradeRequirementDTO2);
        mPvpGradeRequirementDTO2.setId(2L);
        assertThat(mPvpGradeRequirementDTO1).isNotEqualTo(mPvpGradeRequirementDTO2);
        mPvpGradeRequirementDTO1.setId(null);
        assertThat(mPvpGradeRequirementDTO1).isNotEqualTo(mPvpGradeRequirementDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(mPvpGradeRequirementMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(mPvpGradeRequirementMapper.fromId(null)).isNull();
    }
}
