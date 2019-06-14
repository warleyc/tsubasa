package io.shm.tsubasa.web.rest;

import io.shm.tsubasa.TsubasaApp;
import io.shm.tsubasa.domain.MPvpGrade;
import io.shm.tsubasa.repository.MPvpGradeRepository;
import io.shm.tsubasa.service.MPvpGradeService;
import io.shm.tsubasa.service.dto.MPvpGradeDTO;
import io.shm.tsubasa.service.mapper.MPvpGradeMapper;
import io.shm.tsubasa.web.rest.errors.ExceptionTranslator;
import io.shm.tsubasa.service.dto.MPvpGradeCriteria;
import io.shm.tsubasa.service.MPvpGradeQueryService;

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
 * Integration tests for the {@Link MPvpGradeResource} REST controller.
 */
@SpringBootTest(classes = TsubasaApp.class)
public class MPvpGradeResourceIT {

    private static final Integer DEFAULT_WAVE_ID = 1;
    private static final Integer UPDATED_WAVE_ID = 2;

    private static final Integer DEFAULT_GRADE = 1;
    private static final Integer UPDATED_GRADE = 2;

    private static final Integer DEFAULT_IS_HIGHER = 1;
    private static final Integer UPDATED_IS_HIGHER = 2;

    private static final Integer DEFAULT_IS_LOWER = 1;
    private static final Integer UPDATED_IS_LOWER = 2;

    private static final String DEFAULT_GRADE_NAME = "AAAAAAAAAA";
    private static final String UPDATED_GRADE_NAME = "BBBBBBBBBB";

    private static final Integer DEFAULT_LOOK = 1;
    private static final Integer UPDATED_LOOK = 2;

    private static final Integer DEFAULT_UP_REQUIREMENT_ID = 1;
    private static final Integer UPDATED_UP_REQUIREMENT_ID = 2;

    private static final Integer DEFAULT_DOWN_REQUIREMENT_ID = 1;
    private static final Integer UPDATED_DOWN_REQUIREMENT_ID = 2;

    private static final Integer DEFAULT_WIN_POINT = 1;
    private static final Integer UPDATED_WIN_POINT = 2;

    private static final Integer DEFAULT_LOSE_POINT = 1;
    private static final Integer UPDATED_LOSE_POINT = 2;

    private static final String DEFAULT_GRADE_UP_SERIF = "AAAAAAAAAA";
    private static final String UPDATED_GRADE_UP_SERIF = "BBBBBBBBBB";

    private static final String DEFAULT_GRADE_DOWN_SERIF = "AAAAAAAAAA";
    private static final String UPDATED_GRADE_DOWN_SERIF = "BBBBBBBBBB";

    private static final String DEFAULT_GRADE_UP_CHARA_ASSET_NAME = "AAAAAAAAAA";
    private static final String UPDATED_GRADE_UP_CHARA_ASSET_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_GRADE_DOWN_CHARA_ASSET_NAME = "AAAAAAAAAA";
    private static final String UPDATED_GRADE_DOWN_CHARA_ASSET_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_GRADE_UP_VOICE_CHARA_ID = "AAAAAAAAAA";
    private static final String UPDATED_GRADE_UP_VOICE_CHARA_ID = "BBBBBBBBBB";

    private static final String DEFAULT_GRADE_DOWN_VOICE_CHARA_ID = "AAAAAAAAAA";
    private static final String UPDATED_GRADE_DOWN_VOICE_CHARA_ID = "BBBBBBBBBB";

    private static final Integer DEFAULT_TOTAL_PARAMETER_RANGE_UPPER = 1;
    private static final Integer UPDATED_TOTAL_PARAMETER_RANGE_UPPER = 2;

    private static final Integer DEFAULT_TOTAL_PARAMETER_RANGE_LOWER = 1;
    private static final Integer UPDATED_TOTAL_PARAMETER_RANGE_LOWER = 2;

    @Autowired
    private MPvpGradeRepository mPvpGradeRepository;

    @Autowired
    private MPvpGradeMapper mPvpGradeMapper;

    @Autowired
    private MPvpGradeService mPvpGradeService;

    @Autowired
    private MPvpGradeQueryService mPvpGradeQueryService;

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

    private MockMvc restMPvpGradeMockMvc;

    private MPvpGrade mPvpGrade;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final MPvpGradeResource mPvpGradeResource = new MPvpGradeResource(mPvpGradeService, mPvpGradeQueryService);
        this.restMPvpGradeMockMvc = MockMvcBuilders.standaloneSetup(mPvpGradeResource)
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
    public static MPvpGrade createEntity(EntityManager em) {
        MPvpGrade mPvpGrade = new MPvpGrade()
            .waveId(DEFAULT_WAVE_ID)
            .grade(DEFAULT_GRADE)
            .isHigher(DEFAULT_IS_HIGHER)
            .isLower(DEFAULT_IS_LOWER)
            .gradeName(DEFAULT_GRADE_NAME)
            .look(DEFAULT_LOOK)
            .upRequirementId(DEFAULT_UP_REQUIREMENT_ID)
            .downRequirementId(DEFAULT_DOWN_REQUIREMENT_ID)
            .winPoint(DEFAULT_WIN_POINT)
            .losePoint(DEFAULT_LOSE_POINT)
            .gradeUpSerif(DEFAULT_GRADE_UP_SERIF)
            .gradeDownSerif(DEFAULT_GRADE_DOWN_SERIF)
            .gradeUpCharaAssetName(DEFAULT_GRADE_UP_CHARA_ASSET_NAME)
            .gradeDownCharaAssetName(DEFAULT_GRADE_DOWN_CHARA_ASSET_NAME)
            .gradeUpVoiceCharaId(DEFAULT_GRADE_UP_VOICE_CHARA_ID)
            .gradeDownVoiceCharaId(DEFAULT_GRADE_DOWN_VOICE_CHARA_ID)
            .totalParameterRangeUpper(DEFAULT_TOTAL_PARAMETER_RANGE_UPPER)
            .totalParameterRangeLower(DEFAULT_TOTAL_PARAMETER_RANGE_LOWER);
        return mPvpGrade;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static MPvpGrade createUpdatedEntity(EntityManager em) {
        MPvpGrade mPvpGrade = new MPvpGrade()
            .waveId(UPDATED_WAVE_ID)
            .grade(UPDATED_GRADE)
            .isHigher(UPDATED_IS_HIGHER)
            .isLower(UPDATED_IS_LOWER)
            .gradeName(UPDATED_GRADE_NAME)
            .look(UPDATED_LOOK)
            .upRequirementId(UPDATED_UP_REQUIREMENT_ID)
            .downRequirementId(UPDATED_DOWN_REQUIREMENT_ID)
            .winPoint(UPDATED_WIN_POINT)
            .losePoint(UPDATED_LOSE_POINT)
            .gradeUpSerif(UPDATED_GRADE_UP_SERIF)
            .gradeDownSerif(UPDATED_GRADE_DOWN_SERIF)
            .gradeUpCharaAssetName(UPDATED_GRADE_UP_CHARA_ASSET_NAME)
            .gradeDownCharaAssetName(UPDATED_GRADE_DOWN_CHARA_ASSET_NAME)
            .gradeUpVoiceCharaId(UPDATED_GRADE_UP_VOICE_CHARA_ID)
            .gradeDownVoiceCharaId(UPDATED_GRADE_DOWN_VOICE_CHARA_ID)
            .totalParameterRangeUpper(UPDATED_TOTAL_PARAMETER_RANGE_UPPER)
            .totalParameterRangeLower(UPDATED_TOTAL_PARAMETER_RANGE_LOWER);
        return mPvpGrade;
    }

    @BeforeEach
    public void initTest() {
        mPvpGrade = createEntity(em);
    }

    @Test
    @Transactional
    public void createMPvpGrade() throws Exception {
        int databaseSizeBeforeCreate = mPvpGradeRepository.findAll().size();

        // Create the MPvpGrade
        MPvpGradeDTO mPvpGradeDTO = mPvpGradeMapper.toDto(mPvpGrade);
        restMPvpGradeMockMvc.perform(post("/api/m-pvp-grades")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mPvpGradeDTO)))
            .andExpect(status().isCreated());

        // Validate the MPvpGrade in the database
        List<MPvpGrade> mPvpGradeList = mPvpGradeRepository.findAll();
        assertThat(mPvpGradeList).hasSize(databaseSizeBeforeCreate + 1);
        MPvpGrade testMPvpGrade = mPvpGradeList.get(mPvpGradeList.size() - 1);
        assertThat(testMPvpGrade.getWaveId()).isEqualTo(DEFAULT_WAVE_ID);
        assertThat(testMPvpGrade.getGrade()).isEqualTo(DEFAULT_GRADE);
        assertThat(testMPvpGrade.getIsHigher()).isEqualTo(DEFAULT_IS_HIGHER);
        assertThat(testMPvpGrade.getIsLower()).isEqualTo(DEFAULT_IS_LOWER);
        assertThat(testMPvpGrade.getGradeName()).isEqualTo(DEFAULT_GRADE_NAME);
        assertThat(testMPvpGrade.getLook()).isEqualTo(DEFAULT_LOOK);
        assertThat(testMPvpGrade.getUpRequirementId()).isEqualTo(DEFAULT_UP_REQUIREMENT_ID);
        assertThat(testMPvpGrade.getDownRequirementId()).isEqualTo(DEFAULT_DOWN_REQUIREMENT_ID);
        assertThat(testMPvpGrade.getWinPoint()).isEqualTo(DEFAULT_WIN_POINT);
        assertThat(testMPvpGrade.getLosePoint()).isEqualTo(DEFAULT_LOSE_POINT);
        assertThat(testMPvpGrade.getGradeUpSerif()).isEqualTo(DEFAULT_GRADE_UP_SERIF);
        assertThat(testMPvpGrade.getGradeDownSerif()).isEqualTo(DEFAULT_GRADE_DOWN_SERIF);
        assertThat(testMPvpGrade.getGradeUpCharaAssetName()).isEqualTo(DEFAULT_GRADE_UP_CHARA_ASSET_NAME);
        assertThat(testMPvpGrade.getGradeDownCharaAssetName()).isEqualTo(DEFAULT_GRADE_DOWN_CHARA_ASSET_NAME);
        assertThat(testMPvpGrade.getGradeUpVoiceCharaId()).isEqualTo(DEFAULT_GRADE_UP_VOICE_CHARA_ID);
        assertThat(testMPvpGrade.getGradeDownVoiceCharaId()).isEqualTo(DEFAULT_GRADE_DOWN_VOICE_CHARA_ID);
        assertThat(testMPvpGrade.getTotalParameterRangeUpper()).isEqualTo(DEFAULT_TOTAL_PARAMETER_RANGE_UPPER);
        assertThat(testMPvpGrade.getTotalParameterRangeLower()).isEqualTo(DEFAULT_TOTAL_PARAMETER_RANGE_LOWER);
    }

    @Test
    @Transactional
    public void createMPvpGradeWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = mPvpGradeRepository.findAll().size();

        // Create the MPvpGrade with an existing ID
        mPvpGrade.setId(1L);
        MPvpGradeDTO mPvpGradeDTO = mPvpGradeMapper.toDto(mPvpGrade);

        // An entity with an existing ID cannot be created, so this API call must fail
        restMPvpGradeMockMvc.perform(post("/api/m-pvp-grades")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mPvpGradeDTO)))
            .andExpect(status().isBadRequest());

        // Validate the MPvpGrade in the database
        List<MPvpGrade> mPvpGradeList = mPvpGradeRepository.findAll();
        assertThat(mPvpGradeList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkWaveIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = mPvpGradeRepository.findAll().size();
        // set the field null
        mPvpGrade.setWaveId(null);

        // Create the MPvpGrade, which fails.
        MPvpGradeDTO mPvpGradeDTO = mPvpGradeMapper.toDto(mPvpGrade);

        restMPvpGradeMockMvc.perform(post("/api/m-pvp-grades")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mPvpGradeDTO)))
            .andExpect(status().isBadRequest());

        List<MPvpGrade> mPvpGradeList = mPvpGradeRepository.findAll();
        assertThat(mPvpGradeList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkGradeIsRequired() throws Exception {
        int databaseSizeBeforeTest = mPvpGradeRepository.findAll().size();
        // set the field null
        mPvpGrade.setGrade(null);

        // Create the MPvpGrade, which fails.
        MPvpGradeDTO mPvpGradeDTO = mPvpGradeMapper.toDto(mPvpGrade);

        restMPvpGradeMockMvc.perform(post("/api/m-pvp-grades")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mPvpGradeDTO)))
            .andExpect(status().isBadRequest());

        List<MPvpGrade> mPvpGradeList = mPvpGradeRepository.findAll();
        assertThat(mPvpGradeList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkIsHigherIsRequired() throws Exception {
        int databaseSizeBeforeTest = mPvpGradeRepository.findAll().size();
        // set the field null
        mPvpGrade.setIsHigher(null);

        // Create the MPvpGrade, which fails.
        MPvpGradeDTO mPvpGradeDTO = mPvpGradeMapper.toDto(mPvpGrade);

        restMPvpGradeMockMvc.perform(post("/api/m-pvp-grades")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mPvpGradeDTO)))
            .andExpect(status().isBadRequest());

        List<MPvpGrade> mPvpGradeList = mPvpGradeRepository.findAll();
        assertThat(mPvpGradeList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkIsLowerIsRequired() throws Exception {
        int databaseSizeBeforeTest = mPvpGradeRepository.findAll().size();
        // set the field null
        mPvpGrade.setIsLower(null);

        // Create the MPvpGrade, which fails.
        MPvpGradeDTO mPvpGradeDTO = mPvpGradeMapper.toDto(mPvpGrade);

        restMPvpGradeMockMvc.perform(post("/api/m-pvp-grades")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mPvpGradeDTO)))
            .andExpect(status().isBadRequest());

        List<MPvpGrade> mPvpGradeList = mPvpGradeRepository.findAll();
        assertThat(mPvpGradeList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkLookIsRequired() throws Exception {
        int databaseSizeBeforeTest = mPvpGradeRepository.findAll().size();
        // set the field null
        mPvpGrade.setLook(null);

        // Create the MPvpGrade, which fails.
        MPvpGradeDTO mPvpGradeDTO = mPvpGradeMapper.toDto(mPvpGrade);

        restMPvpGradeMockMvc.perform(post("/api/m-pvp-grades")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mPvpGradeDTO)))
            .andExpect(status().isBadRequest());

        List<MPvpGrade> mPvpGradeList = mPvpGradeRepository.findAll();
        assertThat(mPvpGradeList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkWinPointIsRequired() throws Exception {
        int databaseSizeBeforeTest = mPvpGradeRepository.findAll().size();
        // set the field null
        mPvpGrade.setWinPoint(null);

        // Create the MPvpGrade, which fails.
        MPvpGradeDTO mPvpGradeDTO = mPvpGradeMapper.toDto(mPvpGrade);

        restMPvpGradeMockMvc.perform(post("/api/m-pvp-grades")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mPvpGradeDTO)))
            .andExpect(status().isBadRequest());

        List<MPvpGrade> mPvpGradeList = mPvpGradeRepository.findAll();
        assertThat(mPvpGradeList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkLosePointIsRequired() throws Exception {
        int databaseSizeBeforeTest = mPvpGradeRepository.findAll().size();
        // set the field null
        mPvpGrade.setLosePoint(null);

        // Create the MPvpGrade, which fails.
        MPvpGradeDTO mPvpGradeDTO = mPvpGradeMapper.toDto(mPvpGrade);

        restMPvpGradeMockMvc.perform(post("/api/m-pvp-grades")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mPvpGradeDTO)))
            .andExpect(status().isBadRequest());

        List<MPvpGrade> mPvpGradeList = mPvpGradeRepository.findAll();
        assertThat(mPvpGradeList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkTotalParameterRangeUpperIsRequired() throws Exception {
        int databaseSizeBeforeTest = mPvpGradeRepository.findAll().size();
        // set the field null
        mPvpGrade.setTotalParameterRangeUpper(null);

        // Create the MPvpGrade, which fails.
        MPvpGradeDTO mPvpGradeDTO = mPvpGradeMapper.toDto(mPvpGrade);

        restMPvpGradeMockMvc.perform(post("/api/m-pvp-grades")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mPvpGradeDTO)))
            .andExpect(status().isBadRequest());

        List<MPvpGrade> mPvpGradeList = mPvpGradeRepository.findAll();
        assertThat(mPvpGradeList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkTotalParameterRangeLowerIsRequired() throws Exception {
        int databaseSizeBeforeTest = mPvpGradeRepository.findAll().size();
        // set the field null
        mPvpGrade.setTotalParameterRangeLower(null);

        // Create the MPvpGrade, which fails.
        MPvpGradeDTO mPvpGradeDTO = mPvpGradeMapper.toDto(mPvpGrade);

        restMPvpGradeMockMvc.perform(post("/api/m-pvp-grades")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mPvpGradeDTO)))
            .andExpect(status().isBadRequest());

        List<MPvpGrade> mPvpGradeList = mPvpGradeRepository.findAll();
        assertThat(mPvpGradeList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllMPvpGrades() throws Exception {
        // Initialize the database
        mPvpGradeRepository.saveAndFlush(mPvpGrade);

        // Get all the mPvpGradeList
        restMPvpGradeMockMvc.perform(get("/api/m-pvp-grades?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(mPvpGrade.getId().intValue())))
            .andExpect(jsonPath("$.[*].waveId").value(hasItem(DEFAULT_WAVE_ID)))
            .andExpect(jsonPath("$.[*].grade").value(hasItem(DEFAULT_GRADE)))
            .andExpect(jsonPath("$.[*].isHigher").value(hasItem(DEFAULT_IS_HIGHER)))
            .andExpect(jsonPath("$.[*].isLower").value(hasItem(DEFAULT_IS_LOWER)))
            .andExpect(jsonPath("$.[*].gradeName").value(hasItem(DEFAULT_GRADE_NAME.toString())))
            .andExpect(jsonPath("$.[*].look").value(hasItem(DEFAULT_LOOK)))
            .andExpect(jsonPath("$.[*].upRequirementId").value(hasItem(DEFAULT_UP_REQUIREMENT_ID)))
            .andExpect(jsonPath("$.[*].downRequirementId").value(hasItem(DEFAULT_DOWN_REQUIREMENT_ID)))
            .andExpect(jsonPath("$.[*].winPoint").value(hasItem(DEFAULT_WIN_POINT)))
            .andExpect(jsonPath("$.[*].losePoint").value(hasItem(DEFAULT_LOSE_POINT)))
            .andExpect(jsonPath("$.[*].gradeUpSerif").value(hasItem(DEFAULT_GRADE_UP_SERIF.toString())))
            .andExpect(jsonPath("$.[*].gradeDownSerif").value(hasItem(DEFAULT_GRADE_DOWN_SERIF.toString())))
            .andExpect(jsonPath("$.[*].gradeUpCharaAssetName").value(hasItem(DEFAULT_GRADE_UP_CHARA_ASSET_NAME.toString())))
            .andExpect(jsonPath("$.[*].gradeDownCharaAssetName").value(hasItem(DEFAULT_GRADE_DOWN_CHARA_ASSET_NAME.toString())))
            .andExpect(jsonPath("$.[*].gradeUpVoiceCharaId").value(hasItem(DEFAULT_GRADE_UP_VOICE_CHARA_ID.toString())))
            .andExpect(jsonPath("$.[*].gradeDownVoiceCharaId").value(hasItem(DEFAULT_GRADE_DOWN_VOICE_CHARA_ID.toString())))
            .andExpect(jsonPath("$.[*].totalParameterRangeUpper").value(hasItem(DEFAULT_TOTAL_PARAMETER_RANGE_UPPER)))
            .andExpect(jsonPath("$.[*].totalParameterRangeLower").value(hasItem(DEFAULT_TOTAL_PARAMETER_RANGE_LOWER)));
    }
    
    @Test
    @Transactional
    public void getMPvpGrade() throws Exception {
        // Initialize the database
        mPvpGradeRepository.saveAndFlush(mPvpGrade);

        // Get the mPvpGrade
        restMPvpGradeMockMvc.perform(get("/api/m-pvp-grades/{id}", mPvpGrade.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(mPvpGrade.getId().intValue()))
            .andExpect(jsonPath("$.waveId").value(DEFAULT_WAVE_ID))
            .andExpect(jsonPath("$.grade").value(DEFAULT_GRADE))
            .andExpect(jsonPath("$.isHigher").value(DEFAULT_IS_HIGHER))
            .andExpect(jsonPath("$.isLower").value(DEFAULT_IS_LOWER))
            .andExpect(jsonPath("$.gradeName").value(DEFAULT_GRADE_NAME.toString()))
            .andExpect(jsonPath("$.look").value(DEFAULT_LOOK))
            .andExpect(jsonPath("$.upRequirementId").value(DEFAULT_UP_REQUIREMENT_ID))
            .andExpect(jsonPath("$.downRequirementId").value(DEFAULT_DOWN_REQUIREMENT_ID))
            .andExpect(jsonPath("$.winPoint").value(DEFAULT_WIN_POINT))
            .andExpect(jsonPath("$.losePoint").value(DEFAULT_LOSE_POINT))
            .andExpect(jsonPath("$.gradeUpSerif").value(DEFAULT_GRADE_UP_SERIF.toString()))
            .andExpect(jsonPath("$.gradeDownSerif").value(DEFAULT_GRADE_DOWN_SERIF.toString()))
            .andExpect(jsonPath("$.gradeUpCharaAssetName").value(DEFAULT_GRADE_UP_CHARA_ASSET_NAME.toString()))
            .andExpect(jsonPath("$.gradeDownCharaAssetName").value(DEFAULT_GRADE_DOWN_CHARA_ASSET_NAME.toString()))
            .andExpect(jsonPath("$.gradeUpVoiceCharaId").value(DEFAULT_GRADE_UP_VOICE_CHARA_ID.toString()))
            .andExpect(jsonPath("$.gradeDownVoiceCharaId").value(DEFAULT_GRADE_DOWN_VOICE_CHARA_ID.toString()))
            .andExpect(jsonPath("$.totalParameterRangeUpper").value(DEFAULT_TOTAL_PARAMETER_RANGE_UPPER))
            .andExpect(jsonPath("$.totalParameterRangeLower").value(DEFAULT_TOTAL_PARAMETER_RANGE_LOWER));
    }

    @Test
    @Transactional
    public void getAllMPvpGradesByWaveIdIsEqualToSomething() throws Exception {
        // Initialize the database
        mPvpGradeRepository.saveAndFlush(mPvpGrade);

        // Get all the mPvpGradeList where waveId equals to DEFAULT_WAVE_ID
        defaultMPvpGradeShouldBeFound("waveId.equals=" + DEFAULT_WAVE_ID);

        // Get all the mPvpGradeList where waveId equals to UPDATED_WAVE_ID
        defaultMPvpGradeShouldNotBeFound("waveId.equals=" + UPDATED_WAVE_ID);
    }

    @Test
    @Transactional
    public void getAllMPvpGradesByWaveIdIsInShouldWork() throws Exception {
        // Initialize the database
        mPvpGradeRepository.saveAndFlush(mPvpGrade);

        // Get all the mPvpGradeList where waveId in DEFAULT_WAVE_ID or UPDATED_WAVE_ID
        defaultMPvpGradeShouldBeFound("waveId.in=" + DEFAULT_WAVE_ID + "," + UPDATED_WAVE_ID);

        // Get all the mPvpGradeList where waveId equals to UPDATED_WAVE_ID
        defaultMPvpGradeShouldNotBeFound("waveId.in=" + UPDATED_WAVE_ID);
    }

    @Test
    @Transactional
    public void getAllMPvpGradesByWaveIdIsNullOrNotNull() throws Exception {
        // Initialize the database
        mPvpGradeRepository.saveAndFlush(mPvpGrade);

        // Get all the mPvpGradeList where waveId is not null
        defaultMPvpGradeShouldBeFound("waveId.specified=true");

        // Get all the mPvpGradeList where waveId is null
        defaultMPvpGradeShouldNotBeFound("waveId.specified=false");
    }

    @Test
    @Transactional
    public void getAllMPvpGradesByWaveIdIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mPvpGradeRepository.saveAndFlush(mPvpGrade);

        // Get all the mPvpGradeList where waveId greater than or equals to DEFAULT_WAVE_ID
        defaultMPvpGradeShouldBeFound("waveId.greaterOrEqualThan=" + DEFAULT_WAVE_ID);

        // Get all the mPvpGradeList where waveId greater than or equals to UPDATED_WAVE_ID
        defaultMPvpGradeShouldNotBeFound("waveId.greaterOrEqualThan=" + UPDATED_WAVE_ID);
    }

    @Test
    @Transactional
    public void getAllMPvpGradesByWaveIdIsLessThanSomething() throws Exception {
        // Initialize the database
        mPvpGradeRepository.saveAndFlush(mPvpGrade);

        // Get all the mPvpGradeList where waveId less than or equals to DEFAULT_WAVE_ID
        defaultMPvpGradeShouldNotBeFound("waveId.lessThan=" + DEFAULT_WAVE_ID);

        // Get all the mPvpGradeList where waveId less than or equals to UPDATED_WAVE_ID
        defaultMPvpGradeShouldBeFound("waveId.lessThan=" + UPDATED_WAVE_ID);
    }


    @Test
    @Transactional
    public void getAllMPvpGradesByGradeIsEqualToSomething() throws Exception {
        // Initialize the database
        mPvpGradeRepository.saveAndFlush(mPvpGrade);

        // Get all the mPvpGradeList where grade equals to DEFAULT_GRADE
        defaultMPvpGradeShouldBeFound("grade.equals=" + DEFAULT_GRADE);

        // Get all the mPvpGradeList where grade equals to UPDATED_GRADE
        defaultMPvpGradeShouldNotBeFound("grade.equals=" + UPDATED_GRADE);
    }

    @Test
    @Transactional
    public void getAllMPvpGradesByGradeIsInShouldWork() throws Exception {
        // Initialize the database
        mPvpGradeRepository.saveAndFlush(mPvpGrade);

        // Get all the mPvpGradeList where grade in DEFAULT_GRADE or UPDATED_GRADE
        defaultMPvpGradeShouldBeFound("grade.in=" + DEFAULT_GRADE + "," + UPDATED_GRADE);

        // Get all the mPvpGradeList where grade equals to UPDATED_GRADE
        defaultMPvpGradeShouldNotBeFound("grade.in=" + UPDATED_GRADE);
    }

    @Test
    @Transactional
    public void getAllMPvpGradesByGradeIsNullOrNotNull() throws Exception {
        // Initialize the database
        mPvpGradeRepository.saveAndFlush(mPvpGrade);

        // Get all the mPvpGradeList where grade is not null
        defaultMPvpGradeShouldBeFound("grade.specified=true");

        // Get all the mPvpGradeList where grade is null
        defaultMPvpGradeShouldNotBeFound("grade.specified=false");
    }

    @Test
    @Transactional
    public void getAllMPvpGradesByGradeIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mPvpGradeRepository.saveAndFlush(mPvpGrade);

        // Get all the mPvpGradeList where grade greater than or equals to DEFAULT_GRADE
        defaultMPvpGradeShouldBeFound("grade.greaterOrEqualThan=" + DEFAULT_GRADE);

        // Get all the mPvpGradeList where grade greater than or equals to UPDATED_GRADE
        defaultMPvpGradeShouldNotBeFound("grade.greaterOrEqualThan=" + UPDATED_GRADE);
    }

    @Test
    @Transactional
    public void getAllMPvpGradesByGradeIsLessThanSomething() throws Exception {
        // Initialize the database
        mPvpGradeRepository.saveAndFlush(mPvpGrade);

        // Get all the mPvpGradeList where grade less than or equals to DEFAULT_GRADE
        defaultMPvpGradeShouldNotBeFound("grade.lessThan=" + DEFAULT_GRADE);

        // Get all the mPvpGradeList where grade less than or equals to UPDATED_GRADE
        defaultMPvpGradeShouldBeFound("grade.lessThan=" + UPDATED_GRADE);
    }


    @Test
    @Transactional
    public void getAllMPvpGradesByIsHigherIsEqualToSomething() throws Exception {
        // Initialize the database
        mPvpGradeRepository.saveAndFlush(mPvpGrade);

        // Get all the mPvpGradeList where isHigher equals to DEFAULT_IS_HIGHER
        defaultMPvpGradeShouldBeFound("isHigher.equals=" + DEFAULT_IS_HIGHER);

        // Get all the mPvpGradeList where isHigher equals to UPDATED_IS_HIGHER
        defaultMPvpGradeShouldNotBeFound("isHigher.equals=" + UPDATED_IS_HIGHER);
    }

    @Test
    @Transactional
    public void getAllMPvpGradesByIsHigherIsInShouldWork() throws Exception {
        // Initialize the database
        mPvpGradeRepository.saveAndFlush(mPvpGrade);

        // Get all the mPvpGradeList where isHigher in DEFAULT_IS_HIGHER or UPDATED_IS_HIGHER
        defaultMPvpGradeShouldBeFound("isHigher.in=" + DEFAULT_IS_HIGHER + "," + UPDATED_IS_HIGHER);

        // Get all the mPvpGradeList where isHigher equals to UPDATED_IS_HIGHER
        defaultMPvpGradeShouldNotBeFound("isHigher.in=" + UPDATED_IS_HIGHER);
    }

    @Test
    @Transactional
    public void getAllMPvpGradesByIsHigherIsNullOrNotNull() throws Exception {
        // Initialize the database
        mPvpGradeRepository.saveAndFlush(mPvpGrade);

        // Get all the mPvpGradeList where isHigher is not null
        defaultMPvpGradeShouldBeFound("isHigher.specified=true");

        // Get all the mPvpGradeList where isHigher is null
        defaultMPvpGradeShouldNotBeFound("isHigher.specified=false");
    }

    @Test
    @Transactional
    public void getAllMPvpGradesByIsHigherIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mPvpGradeRepository.saveAndFlush(mPvpGrade);

        // Get all the mPvpGradeList where isHigher greater than or equals to DEFAULT_IS_HIGHER
        defaultMPvpGradeShouldBeFound("isHigher.greaterOrEqualThan=" + DEFAULT_IS_HIGHER);

        // Get all the mPvpGradeList where isHigher greater than or equals to UPDATED_IS_HIGHER
        defaultMPvpGradeShouldNotBeFound("isHigher.greaterOrEqualThan=" + UPDATED_IS_HIGHER);
    }

    @Test
    @Transactional
    public void getAllMPvpGradesByIsHigherIsLessThanSomething() throws Exception {
        // Initialize the database
        mPvpGradeRepository.saveAndFlush(mPvpGrade);

        // Get all the mPvpGradeList where isHigher less than or equals to DEFAULT_IS_HIGHER
        defaultMPvpGradeShouldNotBeFound("isHigher.lessThan=" + DEFAULT_IS_HIGHER);

        // Get all the mPvpGradeList where isHigher less than or equals to UPDATED_IS_HIGHER
        defaultMPvpGradeShouldBeFound("isHigher.lessThan=" + UPDATED_IS_HIGHER);
    }


    @Test
    @Transactional
    public void getAllMPvpGradesByIsLowerIsEqualToSomething() throws Exception {
        // Initialize the database
        mPvpGradeRepository.saveAndFlush(mPvpGrade);

        // Get all the mPvpGradeList where isLower equals to DEFAULT_IS_LOWER
        defaultMPvpGradeShouldBeFound("isLower.equals=" + DEFAULT_IS_LOWER);

        // Get all the mPvpGradeList where isLower equals to UPDATED_IS_LOWER
        defaultMPvpGradeShouldNotBeFound("isLower.equals=" + UPDATED_IS_LOWER);
    }

    @Test
    @Transactional
    public void getAllMPvpGradesByIsLowerIsInShouldWork() throws Exception {
        // Initialize the database
        mPvpGradeRepository.saveAndFlush(mPvpGrade);

        // Get all the mPvpGradeList where isLower in DEFAULT_IS_LOWER or UPDATED_IS_LOWER
        defaultMPvpGradeShouldBeFound("isLower.in=" + DEFAULT_IS_LOWER + "," + UPDATED_IS_LOWER);

        // Get all the mPvpGradeList where isLower equals to UPDATED_IS_LOWER
        defaultMPvpGradeShouldNotBeFound("isLower.in=" + UPDATED_IS_LOWER);
    }

    @Test
    @Transactional
    public void getAllMPvpGradesByIsLowerIsNullOrNotNull() throws Exception {
        // Initialize the database
        mPvpGradeRepository.saveAndFlush(mPvpGrade);

        // Get all the mPvpGradeList where isLower is not null
        defaultMPvpGradeShouldBeFound("isLower.specified=true");

        // Get all the mPvpGradeList where isLower is null
        defaultMPvpGradeShouldNotBeFound("isLower.specified=false");
    }

    @Test
    @Transactional
    public void getAllMPvpGradesByIsLowerIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mPvpGradeRepository.saveAndFlush(mPvpGrade);

        // Get all the mPvpGradeList where isLower greater than or equals to DEFAULT_IS_LOWER
        defaultMPvpGradeShouldBeFound("isLower.greaterOrEqualThan=" + DEFAULT_IS_LOWER);

        // Get all the mPvpGradeList where isLower greater than or equals to UPDATED_IS_LOWER
        defaultMPvpGradeShouldNotBeFound("isLower.greaterOrEqualThan=" + UPDATED_IS_LOWER);
    }

    @Test
    @Transactional
    public void getAllMPvpGradesByIsLowerIsLessThanSomething() throws Exception {
        // Initialize the database
        mPvpGradeRepository.saveAndFlush(mPvpGrade);

        // Get all the mPvpGradeList where isLower less than or equals to DEFAULT_IS_LOWER
        defaultMPvpGradeShouldNotBeFound("isLower.lessThan=" + DEFAULT_IS_LOWER);

        // Get all the mPvpGradeList where isLower less than or equals to UPDATED_IS_LOWER
        defaultMPvpGradeShouldBeFound("isLower.lessThan=" + UPDATED_IS_LOWER);
    }


    @Test
    @Transactional
    public void getAllMPvpGradesByLookIsEqualToSomething() throws Exception {
        // Initialize the database
        mPvpGradeRepository.saveAndFlush(mPvpGrade);

        // Get all the mPvpGradeList where look equals to DEFAULT_LOOK
        defaultMPvpGradeShouldBeFound("look.equals=" + DEFAULT_LOOK);

        // Get all the mPvpGradeList where look equals to UPDATED_LOOK
        defaultMPvpGradeShouldNotBeFound("look.equals=" + UPDATED_LOOK);
    }

    @Test
    @Transactional
    public void getAllMPvpGradesByLookIsInShouldWork() throws Exception {
        // Initialize the database
        mPvpGradeRepository.saveAndFlush(mPvpGrade);

        // Get all the mPvpGradeList where look in DEFAULT_LOOK or UPDATED_LOOK
        defaultMPvpGradeShouldBeFound("look.in=" + DEFAULT_LOOK + "," + UPDATED_LOOK);

        // Get all the mPvpGradeList where look equals to UPDATED_LOOK
        defaultMPvpGradeShouldNotBeFound("look.in=" + UPDATED_LOOK);
    }

    @Test
    @Transactional
    public void getAllMPvpGradesByLookIsNullOrNotNull() throws Exception {
        // Initialize the database
        mPvpGradeRepository.saveAndFlush(mPvpGrade);

        // Get all the mPvpGradeList where look is not null
        defaultMPvpGradeShouldBeFound("look.specified=true");

        // Get all the mPvpGradeList where look is null
        defaultMPvpGradeShouldNotBeFound("look.specified=false");
    }

    @Test
    @Transactional
    public void getAllMPvpGradesByLookIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mPvpGradeRepository.saveAndFlush(mPvpGrade);

        // Get all the mPvpGradeList where look greater than or equals to DEFAULT_LOOK
        defaultMPvpGradeShouldBeFound("look.greaterOrEqualThan=" + DEFAULT_LOOK);

        // Get all the mPvpGradeList where look greater than or equals to UPDATED_LOOK
        defaultMPvpGradeShouldNotBeFound("look.greaterOrEqualThan=" + UPDATED_LOOK);
    }

    @Test
    @Transactional
    public void getAllMPvpGradesByLookIsLessThanSomething() throws Exception {
        // Initialize the database
        mPvpGradeRepository.saveAndFlush(mPvpGrade);

        // Get all the mPvpGradeList where look less than or equals to DEFAULT_LOOK
        defaultMPvpGradeShouldNotBeFound("look.lessThan=" + DEFAULT_LOOK);

        // Get all the mPvpGradeList where look less than or equals to UPDATED_LOOK
        defaultMPvpGradeShouldBeFound("look.lessThan=" + UPDATED_LOOK);
    }


    @Test
    @Transactional
    public void getAllMPvpGradesByUpRequirementIdIsEqualToSomething() throws Exception {
        // Initialize the database
        mPvpGradeRepository.saveAndFlush(mPvpGrade);

        // Get all the mPvpGradeList where upRequirementId equals to DEFAULT_UP_REQUIREMENT_ID
        defaultMPvpGradeShouldBeFound("upRequirementId.equals=" + DEFAULT_UP_REQUIREMENT_ID);

        // Get all the mPvpGradeList where upRequirementId equals to UPDATED_UP_REQUIREMENT_ID
        defaultMPvpGradeShouldNotBeFound("upRequirementId.equals=" + UPDATED_UP_REQUIREMENT_ID);
    }

    @Test
    @Transactional
    public void getAllMPvpGradesByUpRequirementIdIsInShouldWork() throws Exception {
        // Initialize the database
        mPvpGradeRepository.saveAndFlush(mPvpGrade);

        // Get all the mPvpGradeList where upRequirementId in DEFAULT_UP_REQUIREMENT_ID or UPDATED_UP_REQUIREMENT_ID
        defaultMPvpGradeShouldBeFound("upRequirementId.in=" + DEFAULT_UP_REQUIREMENT_ID + "," + UPDATED_UP_REQUIREMENT_ID);

        // Get all the mPvpGradeList where upRequirementId equals to UPDATED_UP_REQUIREMENT_ID
        defaultMPvpGradeShouldNotBeFound("upRequirementId.in=" + UPDATED_UP_REQUIREMENT_ID);
    }

    @Test
    @Transactional
    public void getAllMPvpGradesByUpRequirementIdIsNullOrNotNull() throws Exception {
        // Initialize the database
        mPvpGradeRepository.saveAndFlush(mPvpGrade);

        // Get all the mPvpGradeList where upRequirementId is not null
        defaultMPvpGradeShouldBeFound("upRequirementId.specified=true");

        // Get all the mPvpGradeList where upRequirementId is null
        defaultMPvpGradeShouldNotBeFound("upRequirementId.specified=false");
    }

    @Test
    @Transactional
    public void getAllMPvpGradesByUpRequirementIdIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mPvpGradeRepository.saveAndFlush(mPvpGrade);

        // Get all the mPvpGradeList where upRequirementId greater than or equals to DEFAULT_UP_REQUIREMENT_ID
        defaultMPvpGradeShouldBeFound("upRequirementId.greaterOrEqualThan=" + DEFAULT_UP_REQUIREMENT_ID);

        // Get all the mPvpGradeList where upRequirementId greater than or equals to UPDATED_UP_REQUIREMENT_ID
        defaultMPvpGradeShouldNotBeFound("upRequirementId.greaterOrEqualThan=" + UPDATED_UP_REQUIREMENT_ID);
    }

    @Test
    @Transactional
    public void getAllMPvpGradesByUpRequirementIdIsLessThanSomething() throws Exception {
        // Initialize the database
        mPvpGradeRepository.saveAndFlush(mPvpGrade);

        // Get all the mPvpGradeList where upRequirementId less than or equals to DEFAULT_UP_REQUIREMENT_ID
        defaultMPvpGradeShouldNotBeFound("upRequirementId.lessThan=" + DEFAULT_UP_REQUIREMENT_ID);

        // Get all the mPvpGradeList where upRequirementId less than or equals to UPDATED_UP_REQUIREMENT_ID
        defaultMPvpGradeShouldBeFound("upRequirementId.lessThan=" + UPDATED_UP_REQUIREMENT_ID);
    }


    @Test
    @Transactional
    public void getAllMPvpGradesByDownRequirementIdIsEqualToSomething() throws Exception {
        // Initialize the database
        mPvpGradeRepository.saveAndFlush(mPvpGrade);

        // Get all the mPvpGradeList where downRequirementId equals to DEFAULT_DOWN_REQUIREMENT_ID
        defaultMPvpGradeShouldBeFound("downRequirementId.equals=" + DEFAULT_DOWN_REQUIREMENT_ID);

        // Get all the mPvpGradeList where downRequirementId equals to UPDATED_DOWN_REQUIREMENT_ID
        defaultMPvpGradeShouldNotBeFound("downRequirementId.equals=" + UPDATED_DOWN_REQUIREMENT_ID);
    }

    @Test
    @Transactional
    public void getAllMPvpGradesByDownRequirementIdIsInShouldWork() throws Exception {
        // Initialize the database
        mPvpGradeRepository.saveAndFlush(mPvpGrade);

        // Get all the mPvpGradeList where downRequirementId in DEFAULT_DOWN_REQUIREMENT_ID or UPDATED_DOWN_REQUIREMENT_ID
        defaultMPvpGradeShouldBeFound("downRequirementId.in=" + DEFAULT_DOWN_REQUIREMENT_ID + "," + UPDATED_DOWN_REQUIREMENT_ID);

        // Get all the mPvpGradeList where downRequirementId equals to UPDATED_DOWN_REQUIREMENT_ID
        defaultMPvpGradeShouldNotBeFound("downRequirementId.in=" + UPDATED_DOWN_REQUIREMENT_ID);
    }

    @Test
    @Transactional
    public void getAllMPvpGradesByDownRequirementIdIsNullOrNotNull() throws Exception {
        // Initialize the database
        mPvpGradeRepository.saveAndFlush(mPvpGrade);

        // Get all the mPvpGradeList where downRequirementId is not null
        defaultMPvpGradeShouldBeFound("downRequirementId.specified=true");

        // Get all the mPvpGradeList where downRequirementId is null
        defaultMPvpGradeShouldNotBeFound("downRequirementId.specified=false");
    }

    @Test
    @Transactional
    public void getAllMPvpGradesByDownRequirementIdIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mPvpGradeRepository.saveAndFlush(mPvpGrade);

        // Get all the mPvpGradeList where downRequirementId greater than or equals to DEFAULT_DOWN_REQUIREMENT_ID
        defaultMPvpGradeShouldBeFound("downRequirementId.greaterOrEqualThan=" + DEFAULT_DOWN_REQUIREMENT_ID);

        // Get all the mPvpGradeList where downRequirementId greater than or equals to UPDATED_DOWN_REQUIREMENT_ID
        defaultMPvpGradeShouldNotBeFound("downRequirementId.greaterOrEqualThan=" + UPDATED_DOWN_REQUIREMENT_ID);
    }

    @Test
    @Transactional
    public void getAllMPvpGradesByDownRequirementIdIsLessThanSomething() throws Exception {
        // Initialize the database
        mPvpGradeRepository.saveAndFlush(mPvpGrade);

        // Get all the mPvpGradeList where downRequirementId less than or equals to DEFAULT_DOWN_REQUIREMENT_ID
        defaultMPvpGradeShouldNotBeFound("downRequirementId.lessThan=" + DEFAULT_DOWN_REQUIREMENT_ID);

        // Get all the mPvpGradeList where downRequirementId less than or equals to UPDATED_DOWN_REQUIREMENT_ID
        defaultMPvpGradeShouldBeFound("downRequirementId.lessThan=" + UPDATED_DOWN_REQUIREMENT_ID);
    }


    @Test
    @Transactional
    public void getAllMPvpGradesByWinPointIsEqualToSomething() throws Exception {
        // Initialize the database
        mPvpGradeRepository.saveAndFlush(mPvpGrade);

        // Get all the mPvpGradeList where winPoint equals to DEFAULT_WIN_POINT
        defaultMPvpGradeShouldBeFound("winPoint.equals=" + DEFAULT_WIN_POINT);

        // Get all the mPvpGradeList where winPoint equals to UPDATED_WIN_POINT
        defaultMPvpGradeShouldNotBeFound("winPoint.equals=" + UPDATED_WIN_POINT);
    }

    @Test
    @Transactional
    public void getAllMPvpGradesByWinPointIsInShouldWork() throws Exception {
        // Initialize the database
        mPvpGradeRepository.saveAndFlush(mPvpGrade);

        // Get all the mPvpGradeList where winPoint in DEFAULT_WIN_POINT or UPDATED_WIN_POINT
        defaultMPvpGradeShouldBeFound("winPoint.in=" + DEFAULT_WIN_POINT + "," + UPDATED_WIN_POINT);

        // Get all the mPvpGradeList where winPoint equals to UPDATED_WIN_POINT
        defaultMPvpGradeShouldNotBeFound("winPoint.in=" + UPDATED_WIN_POINT);
    }

    @Test
    @Transactional
    public void getAllMPvpGradesByWinPointIsNullOrNotNull() throws Exception {
        // Initialize the database
        mPvpGradeRepository.saveAndFlush(mPvpGrade);

        // Get all the mPvpGradeList where winPoint is not null
        defaultMPvpGradeShouldBeFound("winPoint.specified=true");

        // Get all the mPvpGradeList where winPoint is null
        defaultMPvpGradeShouldNotBeFound("winPoint.specified=false");
    }

    @Test
    @Transactional
    public void getAllMPvpGradesByWinPointIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mPvpGradeRepository.saveAndFlush(mPvpGrade);

        // Get all the mPvpGradeList where winPoint greater than or equals to DEFAULT_WIN_POINT
        defaultMPvpGradeShouldBeFound("winPoint.greaterOrEqualThan=" + DEFAULT_WIN_POINT);

        // Get all the mPvpGradeList where winPoint greater than or equals to UPDATED_WIN_POINT
        defaultMPvpGradeShouldNotBeFound("winPoint.greaterOrEqualThan=" + UPDATED_WIN_POINT);
    }

    @Test
    @Transactional
    public void getAllMPvpGradesByWinPointIsLessThanSomething() throws Exception {
        // Initialize the database
        mPvpGradeRepository.saveAndFlush(mPvpGrade);

        // Get all the mPvpGradeList where winPoint less than or equals to DEFAULT_WIN_POINT
        defaultMPvpGradeShouldNotBeFound("winPoint.lessThan=" + DEFAULT_WIN_POINT);

        // Get all the mPvpGradeList where winPoint less than or equals to UPDATED_WIN_POINT
        defaultMPvpGradeShouldBeFound("winPoint.lessThan=" + UPDATED_WIN_POINT);
    }


    @Test
    @Transactional
    public void getAllMPvpGradesByLosePointIsEqualToSomething() throws Exception {
        // Initialize the database
        mPvpGradeRepository.saveAndFlush(mPvpGrade);

        // Get all the mPvpGradeList where losePoint equals to DEFAULT_LOSE_POINT
        defaultMPvpGradeShouldBeFound("losePoint.equals=" + DEFAULT_LOSE_POINT);

        // Get all the mPvpGradeList where losePoint equals to UPDATED_LOSE_POINT
        defaultMPvpGradeShouldNotBeFound("losePoint.equals=" + UPDATED_LOSE_POINT);
    }

    @Test
    @Transactional
    public void getAllMPvpGradesByLosePointIsInShouldWork() throws Exception {
        // Initialize the database
        mPvpGradeRepository.saveAndFlush(mPvpGrade);

        // Get all the mPvpGradeList where losePoint in DEFAULT_LOSE_POINT or UPDATED_LOSE_POINT
        defaultMPvpGradeShouldBeFound("losePoint.in=" + DEFAULT_LOSE_POINT + "," + UPDATED_LOSE_POINT);

        // Get all the mPvpGradeList where losePoint equals to UPDATED_LOSE_POINT
        defaultMPvpGradeShouldNotBeFound("losePoint.in=" + UPDATED_LOSE_POINT);
    }

    @Test
    @Transactional
    public void getAllMPvpGradesByLosePointIsNullOrNotNull() throws Exception {
        // Initialize the database
        mPvpGradeRepository.saveAndFlush(mPvpGrade);

        // Get all the mPvpGradeList where losePoint is not null
        defaultMPvpGradeShouldBeFound("losePoint.specified=true");

        // Get all the mPvpGradeList where losePoint is null
        defaultMPvpGradeShouldNotBeFound("losePoint.specified=false");
    }

    @Test
    @Transactional
    public void getAllMPvpGradesByLosePointIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mPvpGradeRepository.saveAndFlush(mPvpGrade);

        // Get all the mPvpGradeList where losePoint greater than or equals to DEFAULT_LOSE_POINT
        defaultMPvpGradeShouldBeFound("losePoint.greaterOrEqualThan=" + DEFAULT_LOSE_POINT);

        // Get all the mPvpGradeList where losePoint greater than or equals to UPDATED_LOSE_POINT
        defaultMPvpGradeShouldNotBeFound("losePoint.greaterOrEqualThan=" + UPDATED_LOSE_POINT);
    }

    @Test
    @Transactional
    public void getAllMPvpGradesByLosePointIsLessThanSomething() throws Exception {
        // Initialize the database
        mPvpGradeRepository.saveAndFlush(mPvpGrade);

        // Get all the mPvpGradeList where losePoint less than or equals to DEFAULT_LOSE_POINT
        defaultMPvpGradeShouldNotBeFound("losePoint.lessThan=" + DEFAULT_LOSE_POINT);

        // Get all the mPvpGradeList where losePoint less than or equals to UPDATED_LOSE_POINT
        defaultMPvpGradeShouldBeFound("losePoint.lessThan=" + UPDATED_LOSE_POINT);
    }


    @Test
    @Transactional
    public void getAllMPvpGradesByTotalParameterRangeUpperIsEqualToSomething() throws Exception {
        // Initialize the database
        mPvpGradeRepository.saveAndFlush(mPvpGrade);

        // Get all the mPvpGradeList where totalParameterRangeUpper equals to DEFAULT_TOTAL_PARAMETER_RANGE_UPPER
        defaultMPvpGradeShouldBeFound("totalParameterRangeUpper.equals=" + DEFAULT_TOTAL_PARAMETER_RANGE_UPPER);

        // Get all the mPvpGradeList where totalParameterRangeUpper equals to UPDATED_TOTAL_PARAMETER_RANGE_UPPER
        defaultMPvpGradeShouldNotBeFound("totalParameterRangeUpper.equals=" + UPDATED_TOTAL_PARAMETER_RANGE_UPPER);
    }

    @Test
    @Transactional
    public void getAllMPvpGradesByTotalParameterRangeUpperIsInShouldWork() throws Exception {
        // Initialize the database
        mPvpGradeRepository.saveAndFlush(mPvpGrade);

        // Get all the mPvpGradeList where totalParameterRangeUpper in DEFAULT_TOTAL_PARAMETER_RANGE_UPPER or UPDATED_TOTAL_PARAMETER_RANGE_UPPER
        defaultMPvpGradeShouldBeFound("totalParameterRangeUpper.in=" + DEFAULT_TOTAL_PARAMETER_RANGE_UPPER + "," + UPDATED_TOTAL_PARAMETER_RANGE_UPPER);

        // Get all the mPvpGradeList where totalParameterRangeUpper equals to UPDATED_TOTAL_PARAMETER_RANGE_UPPER
        defaultMPvpGradeShouldNotBeFound("totalParameterRangeUpper.in=" + UPDATED_TOTAL_PARAMETER_RANGE_UPPER);
    }

    @Test
    @Transactional
    public void getAllMPvpGradesByTotalParameterRangeUpperIsNullOrNotNull() throws Exception {
        // Initialize the database
        mPvpGradeRepository.saveAndFlush(mPvpGrade);

        // Get all the mPvpGradeList where totalParameterRangeUpper is not null
        defaultMPvpGradeShouldBeFound("totalParameterRangeUpper.specified=true");

        // Get all the mPvpGradeList where totalParameterRangeUpper is null
        defaultMPvpGradeShouldNotBeFound("totalParameterRangeUpper.specified=false");
    }

    @Test
    @Transactional
    public void getAllMPvpGradesByTotalParameterRangeUpperIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mPvpGradeRepository.saveAndFlush(mPvpGrade);

        // Get all the mPvpGradeList where totalParameterRangeUpper greater than or equals to DEFAULT_TOTAL_PARAMETER_RANGE_UPPER
        defaultMPvpGradeShouldBeFound("totalParameterRangeUpper.greaterOrEqualThan=" + DEFAULT_TOTAL_PARAMETER_RANGE_UPPER);

        // Get all the mPvpGradeList where totalParameterRangeUpper greater than or equals to UPDATED_TOTAL_PARAMETER_RANGE_UPPER
        defaultMPvpGradeShouldNotBeFound("totalParameterRangeUpper.greaterOrEqualThan=" + UPDATED_TOTAL_PARAMETER_RANGE_UPPER);
    }

    @Test
    @Transactional
    public void getAllMPvpGradesByTotalParameterRangeUpperIsLessThanSomething() throws Exception {
        // Initialize the database
        mPvpGradeRepository.saveAndFlush(mPvpGrade);

        // Get all the mPvpGradeList where totalParameterRangeUpper less than or equals to DEFAULT_TOTAL_PARAMETER_RANGE_UPPER
        defaultMPvpGradeShouldNotBeFound("totalParameterRangeUpper.lessThan=" + DEFAULT_TOTAL_PARAMETER_RANGE_UPPER);

        // Get all the mPvpGradeList where totalParameterRangeUpper less than or equals to UPDATED_TOTAL_PARAMETER_RANGE_UPPER
        defaultMPvpGradeShouldBeFound("totalParameterRangeUpper.lessThan=" + UPDATED_TOTAL_PARAMETER_RANGE_UPPER);
    }


    @Test
    @Transactional
    public void getAllMPvpGradesByTotalParameterRangeLowerIsEqualToSomething() throws Exception {
        // Initialize the database
        mPvpGradeRepository.saveAndFlush(mPvpGrade);

        // Get all the mPvpGradeList where totalParameterRangeLower equals to DEFAULT_TOTAL_PARAMETER_RANGE_LOWER
        defaultMPvpGradeShouldBeFound("totalParameterRangeLower.equals=" + DEFAULT_TOTAL_PARAMETER_RANGE_LOWER);

        // Get all the mPvpGradeList where totalParameterRangeLower equals to UPDATED_TOTAL_PARAMETER_RANGE_LOWER
        defaultMPvpGradeShouldNotBeFound("totalParameterRangeLower.equals=" + UPDATED_TOTAL_PARAMETER_RANGE_LOWER);
    }

    @Test
    @Transactional
    public void getAllMPvpGradesByTotalParameterRangeLowerIsInShouldWork() throws Exception {
        // Initialize the database
        mPvpGradeRepository.saveAndFlush(mPvpGrade);

        // Get all the mPvpGradeList where totalParameterRangeLower in DEFAULT_TOTAL_PARAMETER_RANGE_LOWER or UPDATED_TOTAL_PARAMETER_RANGE_LOWER
        defaultMPvpGradeShouldBeFound("totalParameterRangeLower.in=" + DEFAULT_TOTAL_PARAMETER_RANGE_LOWER + "," + UPDATED_TOTAL_PARAMETER_RANGE_LOWER);

        // Get all the mPvpGradeList where totalParameterRangeLower equals to UPDATED_TOTAL_PARAMETER_RANGE_LOWER
        defaultMPvpGradeShouldNotBeFound("totalParameterRangeLower.in=" + UPDATED_TOTAL_PARAMETER_RANGE_LOWER);
    }

    @Test
    @Transactional
    public void getAllMPvpGradesByTotalParameterRangeLowerIsNullOrNotNull() throws Exception {
        // Initialize the database
        mPvpGradeRepository.saveAndFlush(mPvpGrade);

        // Get all the mPvpGradeList where totalParameterRangeLower is not null
        defaultMPvpGradeShouldBeFound("totalParameterRangeLower.specified=true");

        // Get all the mPvpGradeList where totalParameterRangeLower is null
        defaultMPvpGradeShouldNotBeFound("totalParameterRangeLower.specified=false");
    }

    @Test
    @Transactional
    public void getAllMPvpGradesByTotalParameterRangeLowerIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mPvpGradeRepository.saveAndFlush(mPvpGrade);

        // Get all the mPvpGradeList where totalParameterRangeLower greater than or equals to DEFAULT_TOTAL_PARAMETER_RANGE_LOWER
        defaultMPvpGradeShouldBeFound("totalParameterRangeLower.greaterOrEqualThan=" + DEFAULT_TOTAL_PARAMETER_RANGE_LOWER);

        // Get all the mPvpGradeList where totalParameterRangeLower greater than or equals to UPDATED_TOTAL_PARAMETER_RANGE_LOWER
        defaultMPvpGradeShouldNotBeFound("totalParameterRangeLower.greaterOrEqualThan=" + UPDATED_TOTAL_PARAMETER_RANGE_LOWER);
    }

    @Test
    @Transactional
    public void getAllMPvpGradesByTotalParameterRangeLowerIsLessThanSomething() throws Exception {
        // Initialize the database
        mPvpGradeRepository.saveAndFlush(mPvpGrade);

        // Get all the mPvpGradeList where totalParameterRangeLower less than or equals to DEFAULT_TOTAL_PARAMETER_RANGE_LOWER
        defaultMPvpGradeShouldNotBeFound("totalParameterRangeLower.lessThan=" + DEFAULT_TOTAL_PARAMETER_RANGE_LOWER);

        // Get all the mPvpGradeList where totalParameterRangeLower less than or equals to UPDATED_TOTAL_PARAMETER_RANGE_LOWER
        defaultMPvpGradeShouldBeFound("totalParameterRangeLower.lessThan=" + UPDATED_TOTAL_PARAMETER_RANGE_LOWER);
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultMPvpGradeShouldBeFound(String filter) throws Exception {
        restMPvpGradeMockMvc.perform(get("/api/m-pvp-grades?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(mPvpGrade.getId().intValue())))
            .andExpect(jsonPath("$.[*].waveId").value(hasItem(DEFAULT_WAVE_ID)))
            .andExpect(jsonPath("$.[*].grade").value(hasItem(DEFAULT_GRADE)))
            .andExpect(jsonPath("$.[*].isHigher").value(hasItem(DEFAULT_IS_HIGHER)))
            .andExpect(jsonPath("$.[*].isLower").value(hasItem(DEFAULT_IS_LOWER)))
            .andExpect(jsonPath("$.[*].gradeName").value(hasItem(DEFAULT_GRADE_NAME.toString())))
            .andExpect(jsonPath("$.[*].look").value(hasItem(DEFAULT_LOOK)))
            .andExpect(jsonPath("$.[*].upRequirementId").value(hasItem(DEFAULT_UP_REQUIREMENT_ID)))
            .andExpect(jsonPath("$.[*].downRequirementId").value(hasItem(DEFAULT_DOWN_REQUIREMENT_ID)))
            .andExpect(jsonPath("$.[*].winPoint").value(hasItem(DEFAULT_WIN_POINT)))
            .andExpect(jsonPath("$.[*].losePoint").value(hasItem(DEFAULT_LOSE_POINT)))
            .andExpect(jsonPath("$.[*].gradeUpSerif").value(hasItem(DEFAULT_GRADE_UP_SERIF.toString())))
            .andExpect(jsonPath("$.[*].gradeDownSerif").value(hasItem(DEFAULT_GRADE_DOWN_SERIF.toString())))
            .andExpect(jsonPath("$.[*].gradeUpCharaAssetName").value(hasItem(DEFAULT_GRADE_UP_CHARA_ASSET_NAME.toString())))
            .andExpect(jsonPath("$.[*].gradeDownCharaAssetName").value(hasItem(DEFAULT_GRADE_DOWN_CHARA_ASSET_NAME.toString())))
            .andExpect(jsonPath("$.[*].gradeUpVoiceCharaId").value(hasItem(DEFAULT_GRADE_UP_VOICE_CHARA_ID.toString())))
            .andExpect(jsonPath("$.[*].gradeDownVoiceCharaId").value(hasItem(DEFAULT_GRADE_DOWN_VOICE_CHARA_ID.toString())))
            .andExpect(jsonPath("$.[*].totalParameterRangeUpper").value(hasItem(DEFAULT_TOTAL_PARAMETER_RANGE_UPPER)))
            .andExpect(jsonPath("$.[*].totalParameterRangeLower").value(hasItem(DEFAULT_TOTAL_PARAMETER_RANGE_LOWER)));

        // Check, that the count call also returns 1
        restMPvpGradeMockMvc.perform(get("/api/m-pvp-grades/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultMPvpGradeShouldNotBeFound(String filter) throws Exception {
        restMPvpGradeMockMvc.perform(get("/api/m-pvp-grades?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restMPvpGradeMockMvc.perform(get("/api/m-pvp-grades/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("0"));
    }


    @Test
    @Transactional
    public void getNonExistingMPvpGrade() throws Exception {
        // Get the mPvpGrade
        restMPvpGradeMockMvc.perform(get("/api/m-pvp-grades/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateMPvpGrade() throws Exception {
        // Initialize the database
        mPvpGradeRepository.saveAndFlush(mPvpGrade);

        int databaseSizeBeforeUpdate = mPvpGradeRepository.findAll().size();

        // Update the mPvpGrade
        MPvpGrade updatedMPvpGrade = mPvpGradeRepository.findById(mPvpGrade.getId()).get();
        // Disconnect from session so that the updates on updatedMPvpGrade are not directly saved in db
        em.detach(updatedMPvpGrade);
        updatedMPvpGrade
            .waveId(UPDATED_WAVE_ID)
            .grade(UPDATED_GRADE)
            .isHigher(UPDATED_IS_HIGHER)
            .isLower(UPDATED_IS_LOWER)
            .gradeName(UPDATED_GRADE_NAME)
            .look(UPDATED_LOOK)
            .upRequirementId(UPDATED_UP_REQUIREMENT_ID)
            .downRequirementId(UPDATED_DOWN_REQUIREMENT_ID)
            .winPoint(UPDATED_WIN_POINT)
            .losePoint(UPDATED_LOSE_POINT)
            .gradeUpSerif(UPDATED_GRADE_UP_SERIF)
            .gradeDownSerif(UPDATED_GRADE_DOWN_SERIF)
            .gradeUpCharaAssetName(UPDATED_GRADE_UP_CHARA_ASSET_NAME)
            .gradeDownCharaAssetName(UPDATED_GRADE_DOWN_CHARA_ASSET_NAME)
            .gradeUpVoiceCharaId(UPDATED_GRADE_UP_VOICE_CHARA_ID)
            .gradeDownVoiceCharaId(UPDATED_GRADE_DOWN_VOICE_CHARA_ID)
            .totalParameterRangeUpper(UPDATED_TOTAL_PARAMETER_RANGE_UPPER)
            .totalParameterRangeLower(UPDATED_TOTAL_PARAMETER_RANGE_LOWER);
        MPvpGradeDTO mPvpGradeDTO = mPvpGradeMapper.toDto(updatedMPvpGrade);

        restMPvpGradeMockMvc.perform(put("/api/m-pvp-grades")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mPvpGradeDTO)))
            .andExpect(status().isOk());

        // Validate the MPvpGrade in the database
        List<MPvpGrade> mPvpGradeList = mPvpGradeRepository.findAll();
        assertThat(mPvpGradeList).hasSize(databaseSizeBeforeUpdate);
        MPvpGrade testMPvpGrade = mPvpGradeList.get(mPvpGradeList.size() - 1);
        assertThat(testMPvpGrade.getWaveId()).isEqualTo(UPDATED_WAVE_ID);
        assertThat(testMPvpGrade.getGrade()).isEqualTo(UPDATED_GRADE);
        assertThat(testMPvpGrade.getIsHigher()).isEqualTo(UPDATED_IS_HIGHER);
        assertThat(testMPvpGrade.getIsLower()).isEqualTo(UPDATED_IS_LOWER);
        assertThat(testMPvpGrade.getGradeName()).isEqualTo(UPDATED_GRADE_NAME);
        assertThat(testMPvpGrade.getLook()).isEqualTo(UPDATED_LOOK);
        assertThat(testMPvpGrade.getUpRequirementId()).isEqualTo(UPDATED_UP_REQUIREMENT_ID);
        assertThat(testMPvpGrade.getDownRequirementId()).isEqualTo(UPDATED_DOWN_REQUIREMENT_ID);
        assertThat(testMPvpGrade.getWinPoint()).isEqualTo(UPDATED_WIN_POINT);
        assertThat(testMPvpGrade.getLosePoint()).isEqualTo(UPDATED_LOSE_POINT);
        assertThat(testMPvpGrade.getGradeUpSerif()).isEqualTo(UPDATED_GRADE_UP_SERIF);
        assertThat(testMPvpGrade.getGradeDownSerif()).isEqualTo(UPDATED_GRADE_DOWN_SERIF);
        assertThat(testMPvpGrade.getGradeUpCharaAssetName()).isEqualTo(UPDATED_GRADE_UP_CHARA_ASSET_NAME);
        assertThat(testMPvpGrade.getGradeDownCharaAssetName()).isEqualTo(UPDATED_GRADE_DOWN_CHARA_ASSET_NAME);
        assertThat(testMPvpGrade.getGradeUpVoiceCharaId()).isEqualTo(UPDATED_GRADE_UP_VOICE_CHARA_ID);
        assertThat(testMPvpGrade.getGradeDownVoiceCharaId()).isEqualTo(UPDATED_GRADE_DOWN_VOICE_CHARA_ID);
        assertThat(testMPvpGrade.getTotalParameterRangeUpper()).isEqualTo(UPDATED_TOTAL_PARAMETER_RANGE_UPPER);
        assertThat(testMPvpGrade.getTotalParameterRangeLower()).isEqualTo(UPDATED_TOTAL_PARAMETER_RANGE_LOWER);
    }

    @Test
    @Transactional
    public void updateNonExistingMPvpGrade() throws Exception {
        int databaseSizeBeforeUpdate = mPvpGradeRepository.findAll().size();

        // Create the MPvpGrade
        MPvpGradeDTO mPvpGradeDTO = mPvpGradeMapper.toDto(mPvpGrade);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restMPvpGradeMockMvc.perform(put("/api/m-pvp-grades")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mPvpGradeDTO)))
            .andExpect(status().isBadRequest());

        // Validate the MPvpGrade in the database
        List<MPvpGrade> mPvpGradeList = mPvpGradeRepository.findAll();
        assertThat(mPvpGradeList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteMPvpGrade() throws Exception {
        // Initialize the database
        mPvpGradeRepository.saveAndFlush(mPvpGrade);

        int databaseSizeBeforeDelete = mPvpGradeRepository.findAll().size();

        // Delete the mPvpGrade
        restMPvpGradeMockMvc.perform(delete("/api/m-pvp-grades/{id}", mPvpGrade.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database is empty
        List<MPvpGrade> mPvpGradeList = mPvpGradeRepository.findAll();
        assertThat(mPvpGradeList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(MPvpGrade.class);
        MPvpGrade mPvpGrade1 = new MPvpGrade();
        mPvpGrade1.setId(1L);
        MPvpGrade mPvpGrade2 = new MPvpGrade();
        mPvpGrade2.setId(mPvpGrade1.getId());
        assertThat(mPvpGrade1).isEqualTo(mPvpGrade2);
        mPvpGrade2.setId(2L);
        assertThat(mPvpGrade1).isNotEqualTo(mPvpGrade2);
        mPvpGrade1.setId(null);
        assertThat(mPvpGrade1).isNotEqualTo(mPvpGrade2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(MPvpGradeDTO.class);
        MPvpGradeDTO mPvpGradeDTO1 = new MPvpGradeDTO();
        mPvpGradeDTO1.setId(1L);
        MPvpGradeDTO mPvpGradeDTO2 = new MPvpGradeDTO();
        assertThat(mPvpGradeDTO1).isNotEqualTo(mPvpGradeDTO2);
        mPvpGradeDTO2.setId(mPvpGradeDTO1.getId());
        assertThat(mPvpGradeDTO1).isEqualTo(mPvpGradeDTO2);
        mPvpGradeDTO2.setId(2L);
        assertThat(mPvpGradeDTO1).isNotEqualTo(mPvpGradeDTO2);
        mPvpGradeDTO1.setId(null);
        assertThat(mPvpGradeDTO1).isNotEqualTo(mPvpGradeDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(mPvpGradeMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(mPvpGradeMapper.fromId(null)).isNull();
    }
}
