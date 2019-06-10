package io.shm.tsubasa.web.rest;

import io.shm.tsubasa.TsubasaApp;
import io.shm.tsubasa.domain.MAreaActionWeight;
import io.shm.tsubasa.repository.MAreaActionWeightRepository;
import io.shm.tsubasa.service.MAreaActionWeightService;
import io.shm.tsubasa.service.dto.MAreaActionWeightDTO;
import io.shm.tsubasa.service.mapper.MAreaActionWeightMapper;
import io.shm.tsubasa.web.rest.errors.ExceptionTranslator;
import io.shm.tsubasa.service.dto.MAreaActionWeightCriteria;
import io.shm.tsubasa.service.MAreaActionWeightQueryService;

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
 * Integration tests for the {@Link MAreaActionWeightResource} REST controller.
 */
@SpringBootTest(classes = TsubasaApp.class)
public class MAreaActionWeightResourceIT {

    private static final Integer DEFAULT_AREA_TYPE = 1;
    private static final Integer UPDATED_AREA_TYPE = 2;

    private static final Integer DEFAULT_DRIBBLE_RATE = 1;
    private static final Integer UPDATED_DRIBBLE_RATE = 2;

    private static final Integer DEFAULT_PASSING_RATE = 1;
    private static final Integer UPDATED_PASSING_RATE = 2;

    private static final Integer DEFAULT_ONETWO_RATE = 1;
    private static final Integer UPDATED_ONETWO_RATE = 2;

    private static final Integer DEFAULT_SHOOT_RATE = 1;
    private static final Integer UPDATED_SHOOT_RATE = 2;

    private static final Integer DEFAULT_VOLLEY_SHOOT_RATE = 1;
    private static final Integer UPDATED_VOLLEY_SHOOT_RATE = 2;

    private static final Integer DEFAULT_HEADING_SHOOT_RATE = 1;
    private static final Integer UPDATED_HEADING_SHOOT_RATE = 2;

    private static final Integer DEFAULT_TACKLE_RATE = 1;
    private static final Integer UPDATED_TACKLE_RATE = 2;

    private static final Integer DEFAULT_BLOCK_RATE = 1;
    private static final Integer UPDATED_BLOCK_RATE = 2;

    private static final Integer DEFAULT_PASS_CUT_RATE = 1;
    private static final Integer UPDATED_PASS_CUT_RATE = 2;

    private static final Integer DEFAULT_CLEAR_RATE = 1;
    private static final Integer UPDATED_CLEAR_RATE = 2;

    private static final Integer DEFAULT_COMPETE_RATE = 1;
    private static final Integer UPDATED_COMPETE_RATE = 2;

    private static final Integer DEFAULT_TRAP_RATE = 1;
    private static final Integer UPDATED_TRAP_RATE = 2;

    @Autowired
    private MAreaActionWeightRepository mAreaActionWeightRepository;

    @Autowired
    private MAreaActionWeightMapper mAreaActionWeightMapper;

    @Autowired
    private MAreaActionWeightService mAreaActionWeightService;

    @Autowired
    private MAreaActionWeightQueryService mAreaActionWeightQueryService;

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

    private MockMvc restMAreaActionWeightMockMvc;

    private MAreaActionWeight mAreaActionWeight;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final MAreaActionWeightResource mAreaActionWeightResource = new MAreaActionWeightResource(mAreaActionWeightService, mAreaActionWeightQueryService);
        this.restMAreaActionWeightMockMvc = MockMvcBuilders.standaloneSetup(mAreaActionWeightResource)
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
    public static MAreaActionWeight createEntity(EntityManager em) {
        MAreaActionWeight mAreaActionWeight = new MAreaActionWeight()
            .areaType(DEFAULT_AREA_TYPE)
            .dribbleRate(DEFAULT_DRIBBLE_RATE)
            .passingRate(DEFAULT_PASSING_RATE)
            .onetwoRate(DEFAULT_ONETWO_RATE)
            .shootRate(DEFAULT_SHOOT_RATE)
            .volleyShootRate(DEFAULT_VOLLEY_SHOOT_RATE)
            .headingShootRate(DEFAULT_HEADING_SHOOT_RATE)
            .tackleRate(DEFAULT_TACKLE_RATE)
            .blockRate(DEFAULT_BLOCK_RATE)
            .passCutRate(DEFAULT_PASS_CUT_RATE)
            .clearRate(DEFAULT_CLEAR_RATE)
            .competeRate(DEFAULT_COMPETE_RATE)
            .trapRate(DEFAULT_TRAP_RATE);
        return mAreaActionWeight;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static MAreaActionWeight createUpdatedEntity(EntityManager em) {
        MAreaActionWeight mAreaActionWeight = new MAreaActionWeight()
            .areaType(UPDATED_AREA_TYPE)
            .dribbleRate(UPDATED_DRIBBLE_RATE)
            .passingRate(UPDATED_PASSING_RATE)
            .onetwoRate(UPDATED_ONETWO_RATE)
            .shootRate(UPDATED_SHOOT_RATE)
            .volleyShootRate(UPDATED_VOLLEY_SHOOT_RATE)
            .headingShootRate(UPDATED_HEADING_SHOOT_RATE)
            .tackleRate(UPDATED_TACKLE_RATE)
            .blockRate(UPDATED_BLOCK_RATE)
            .passCutRate(UPDATED_PASS_CUT_RATE)
            .clearRate(UPDATED_CLEAR_RATE)
            .competeRate(UPDATED_COMPETE_RATE)
            .trapRate(UPDATED_TRAP_RATE);
        return mAreaActionWeight;
    }

    @BeforeEach
    public void initTest() {
        mAreaActionWeight = createEntity(em);
    }

    @Test
    @Transactional
    public void createMAreaActionWeight() throws Exception {
        int databaseSizeBeforeCreate = mAreaActionWeightRepository.findAll().size();

        // Create the MAreaActionWeight
        MAreaActionWeightDTO mAreaActionWeightDTO = mAreaActionWeightMapper.toDto(mAreaActionWeight);
        restMAreaActionWeightMockMvc.perform(post("/api/m-area-action-weights")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mAreaActionWeightDTO)))
            .andExpect(status().isCreated());

        // Validate the MAreaActionWeight in the database
        List<MAreaActionWeight> mAreaActionWeightList = mAreaActionWeightRepository.findAll();
        assertThat(mAreaActionWeightList).hasSize(databaseSizeBeforeCreate + 1);
        MAreaActionWeight testMAreaActionWeight = mAreaActionWeightList.get(mAreaActionWeightList.size() - 1);
        assertThat(testMAreaActionWeight.getAreaType()).isEqualTo(DEFAULT_AREA_TYPE);
        assertThat(testMAreaActionWeight.getDribbleRate()).isEqualTo(DEFAULT_DRIBBLE_RATE);
        assertThat(testMAreaActionWeight.getPassingRate()).isEqualTo(DEFAULT_PASSING_RATE);
        assertThat(testMAreaActionWeight.getOnetwoRate()).isEqualTo(DEFAULT_ONETWO_RATE);
        assertThat(testMAreaActionWeight.getShootRate()).isEqualTo(DEFAULT_SHOOT_RATE);
        assertThat(testMAreaActionWeight.getVolleyShootRate()).isEqualTo(DEFAULT_VOLLEY_SHOOT_RATE);
        assertThat(testMAreaActionWeight.getHeadingShootRate()).isEqualTo(DEFAULT_HEADING_SHOOT_RATE);
        assertThat(testMAreaActionWeight.getTackleRate()).isEqualTo(DEFAULT_TACKLE_RATE);
        assertThat(testMAreaActionWeight.getBlockRate()).isEqualTo(DEFAULT_BLOCK_RATE);
        assertThat(testMAreaActionWeight.getPassCutRate()).isEqualTo(DEFAULT_PASS_CUT_RATE);
        assertThat(testMAreaActionWeight.getClearRate()).isEqualTo(DEFAULT_CLEAR_RATE);
        assertThat(testMAreaActionWeight.getCompeteRate()).isEqualTo(DEFAULT_COMPETE_RATE);
        assertThat(testMAreaActionWeight.getTrapRate()).isEqualTo(DEFAULT_TRAP_RATE);
    }

    @Test
    @Transactional
    public void createMAreaActionWeightWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = mAreaActionWeightRepository.findAll().size();

        // Create the MAreaActionWeight with an existing ID
        mAreaActionWeight.setId(1L);
        MAreaActionWeightDTO mAreaActionWeightDTO = mAreaActionWeightMapper.toDto(mAreaActionWeight);

        // An entity with an existing ID cannot be created, so this API call must fail
        restMAreaActionWeightMockMvc.perform(post("/api/m-area-action-weights")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mAreaActionWeightDTO)))
            .andExpect(status().isBadRequest());

        // Validate the MAreaActionWeight in the database
        List<MAreaActionWeight> mAreaActionWeightList = mAreaActionWeightRepository.findAll();
        assertThat(mAreaActionWeightList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkAreaTypeIsRequired() throws Exception {
        int databaseSizeBeforeTest = mAreaActionWeightRepository.findAll().size();
        // set the field null
        mAreaActionWeight.setAreaType(null);

        // Create the MAreaActionWeight, which fails.
        MAreaActionWeightDTO mAreaActionWeightDTO = mAreaActionWeightMapper.toDto(mAreaActionWeight);

        restMAreaActionWeightMockMvc.perform(post("/api/m-area-action-weights")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mAreaActionWeightDTO)))
            .andExpect(status().isBadRequest());

        List<MAreaActionWeight> mAreaActionWeightList = mAreaActionWeightRepository.findAll();
        assertThat(mAreaActionWeightList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkDribbleRateIsRequired() throws Exception {
        int databaseSizeBeforeTest = mAreaActionWeightRepository.findAll().size();
        // set the field null
        mAreaActionWeight.setDribbleRate(null);

        // Create the MAreaActionWeight, which fails.
        MAreaActionWeightDTO mAreaActionWeightDTO = mAreaActionWeightMapper.toDto(mAreaActionWeight);

        restMAreaActionWeightMockMvc.perform(post("/api/m-area-action-weights")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mAreaActionWeightDTO)))
            .andExpect(status().isBadRequest());

        List<MAreaActionWeight> mAreaActionWeightList = mAreaActionWeightRepository.findAll();
        assertThat(mAreaActionWeightList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkPassingRateIsRequired() throws Exception {
        int databaseSizeBeforeTest = mAreaActionWeightRepository.findAll().size();
        // set the field null
        mAreaActionWeight.setPassingRate(null);

        // Create the MAreaActionWeight, which fails.
        MAreaActionWeightDTO mAreaActionWeightDTO = mAreaActionWeightMapper.toDto(mAreaActionWeight);

        restMAreaActionWeightMockMvc.perform(post("/api/m-area-action-weights")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mAreaActionWeightDTO)))
            .andExpect(status().isBadRequest());

        List<MAreaActionWeight> mAreaActionWeightList = mAreaActionWeightRepository.findAll();
        assertThat(mAreaActionWeightList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkOnetwoRateIsRequired() throws Exception {
        int databaseSizeBeforeTest = mAreaActionWeightRepository.findAll().size();
        // set the field null
        mAreaActionWeight.setOnetwoRate(null);

        // Create the MAreaActionWeight, which fails.
        MAreaActionWeightDTO mAreaActionWeightDTO = mAreaActionWeightMapper.toDto(mAreaActionWeight);

        restMAreaActionWeightMockMvc.perform(post("/api/m-area-action-weights")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mAreaActionWeightDTO)))
            .andExpect(status().isBadRequest());

        List<MAreaActionWeight> mAreaActionWeightList = mAreaActionWeightRepository.findAll();
        assertThat(mAreaActionWeightList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkShootRateIsRequired() throws Exception {
        int databaseSizeBeforeTest = mAreaActionWeightRepository.findAll().size();
        // set the field null
        mAreaActionWeight.setShootRate(null);

        // Create the MAreaActionWeight, which fails.
        MAreaActionWeightDTO mAreaActionWeightDTO = mAreaActionWeightMapper.toDto(mAreaActionWeight);

        restMAreaActionWeightMockMvc.perform(post("/api/m-area-action-weights")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mAreaActionWeightDTO)))
            .andExpect(status().isBadRequest());

        List<MAreaActionWeight> mAreaActionWeightList = mAreaActionWeightRepository.findAll();
        assertThat(mAreaActionWeightList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkVolleyShootRateIsRequired() throws Exception {
        int databaseSizeBeforeTest = mAreaActionWeightRepository.findAll().size();
        // set the field null
        mAreaActionWeight.setVolleyShootRate(null);

        // Create the MAreaActionWeight, which fails.
        MAreaActionWeightDTO mAreaActionWeightDTO = mAreaActionWeightMapper.toDto(mAreaActionWeight);

        restMAreaActionWeightMockMvc.perform(post("/api/m-area-action-weights")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mAreaActionWeightDTO)))
            .andExpect(status().isBadRequest());

        List<MAreaActionWeight> mAreaActionWeightList = mAreaActionWeightRepository.findAll();
        assertThat(mAreaActionWeightList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkHeadingShootRateIsRequired() throws Exception {
        int databaseSizeBeforeTest = mAreaActionWeightRepository.findAll().size();
        // set the field null
        mAreaActionWeight.setHeadingShootRate(null);

        // Create the MAreaActionWeight, which fails.
        MAreaActionWeightDTO mAreaActionWeightDTO = mAreaActionWeightMapper.toDto(mAreaActionWeight);

        restMAreaActionWeightMockMvc.perform(post("/api/m-area-action-weights")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mAreaActionWeightDTO)))
            .andExpect(status().isBadRequest());

        List<MAreaActionWeight> mAreaActionWeightList = mAreaActionWeightRepository.findAll();
        assertThat(mAreaActionWeightList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkTackleRateIsRequired() throws Exception {
        int databaseSizeBeforeTest = mAreaActionWeightRepository.findAll().size();
        // set the field null
        mAreaActionWeight.setTackleRate(null);

        // Create the MAreaActionWeight, which fails.
        MAreaActionWeightDTO mAreaActionWeightDTO = mAreaActionWeightMapper.toDto(mAreaActionWeight);

        restMAreaActionWeightMockMvc.perform(post("/api/m-area-action-weights")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mAreaActionWeightDTO)))
            .andExpect(status().isBadRequest());

        List<MAreaActionWeight> mAreaActionWeightList = mAreaActionWeightRepository.findAll();
        assertThat(mAreaActionWeightList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkBlockRateIsRequired() throws Exception {
        int databaseSizeBeforeTest = mAreaActionWeightRepository.findAll().size();
        // set the field null
        mAreaActionWeight.setBlockRate(null);

        // Create the MAreaActionWeight, which fails.
        MAreaActionWeightDTO mAreaActionWeightDTO = mAreaActionWeightMapper.toDto(mAreaActionWeight);

        restMAreaActionWeightMockMvc.perform(post("/api/m-area-action-weights")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mAreaActionWeightDTO)))
            .andExpect(status().isBadRequest());

        List<MAreaActionWeight> mAreaActionWeightList = mAreaActionWeightRepository.findAll();
        assertThat(mAreaActionWeightList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkPassCutRateIsRequired() throws Exception {
        int databaseSizeBeforeTest = mAreaActionWeightRepository.findAll().size();
        // set the field null
        mAreaActionWeight.setPassCutRate(null);

        // Create the MAreaActionWeight, which fails.
        MAreaActionWeightDTO mAreaActionWeightDTO = mAreaActionWeightMapper.toDto(mAreaActionWeight);

        restMAreaActionWeightMockMvc.perform(post("/api/m-area-action-weights")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mAreaActionWeightDTO)))
            .andExpect(status().isBadRequest());

        List<MAreaActionWeight> mAreaActionWeightList = mAreaActionWeightRepository.findAll();
        assertThat(mAreaActionWeightList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkClearRateIsRequired() throws Exception {
        int databaseSizeBeforeTest = mAreaActionWeightRepository.findAll().size();
        // set the field null
        mAreaActionWeight.setClearRate(null);

        // Create the MAreaActionWeight, which fails.
        MAreaActionWeightDTO mAreaActionWeightDTO = mAreaActionWeightMapper.toDto(mAreaActionWeight);

        restMAreaActionWeightMockMvc.perform(post("/api/m-area-action-weights")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mAreaActionWeightDTO)))
            .andExpect(status().isBadRequest());

        List<MAreaActionWeight> mAreaActionWeightList = mAreaActionWeightRepository.findAll();
        assertThat(mAreaActionWeightList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkCompeteRateIsRequired() throws Exception {
        int databaseSizeBeforeTest = mAreaActionWeightRepository.findAll().size();
        // set the field null
        mAreaActionWeight.setCompeteRate(null);

        // Create the MAreaActionWeight, which fails.
        MAreaActionWeightDTO mAreaActionWeightDTO = mAreaActionWeightMapper.toDto(mAreaActionWeight);

        restMAreaActionWeightMockMvc.perform(post("/api/m-area-action-weights")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mAreaActionWeightDTO)))
            .andExpect(status().isBadRequest());

        List<MAreaActionWeight> mAreaActionWeightList = mAreaActionWeightRepository.findAll();
        assertThat(mAreaActionWeightList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkTrapRateIsRequired() throws Exception {
        int databaseSizeBeforeTest = mAreaActionWeightRepository.findAll().size();
        // set the field null
        mAreaActionWeight.setTrapRate(null);

        // Create the MAreaActionWeight, which fails.
        MAreaActionWeightDTO mAreaActionWeightDTO = mAreaActionWeightMapper.toDto(mAreaActionWeight);

        restMAreaActionWeightMockMvc.perform(post("/api/m-area-action-weights")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mAreaActionWeightDTO)))
            .andExpect(status().isBadRequest());

        List<MAreaActionWeight> mAreaActionWeightList = mAreaActionWeightRepository.findAll();
        assertThat(mAreaActionWeightList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllMAreaActionWeights() throws Exception {
        // Initialize the database
        mAreaActionWeightRepository.saveAndFlush(mAreaActionWeight);

        // Get all the mAreaActionWeightList
        restMAreaActionWeightMockMvc.perform(get("/api/m-area-action-weights?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(mAreaActionWeight.getId().intValue())))
            .andExpect(jsonPath("$.[*].areaType").value(hasItem(DEFAULT_AREA_TYPE)))
            .andExpect(jsonPath("$.[*].dribbleRate").value(hasItem(DEFAULT_DRIBBLE_RATE)))
            .andExpect(jsonPath("$.[*].passingRate").value(hasItem(DEFAULT_PASSING_RATE)))
            .andExpect(jsonPath("$.[*].onetwoRate").value(hasItem(DEFAULT_ONETWO_RATE)))
            .andExpect(jsonPath("$.[*].shootRate").value(hasItem(DEFAULT_SHOOT_RATE)))
            .andExpect(jsonPath("$.[*].volleyShootRate").value(hasItem(DEFAULT_VOLLEY_SHOOT_RATE)))
            .andExpect(jsonPath("$.[*].headingShootRate").value(hasItem(DEFAULT_HEADING_SHOOT_RATE)))
            .andExpect(jsonPath("$.[*].tackleRate").value(hasItem(DEFAULT_TACKLE_RATE)))
            .andExpect(jsonPath("$.[*].blockRate").value(hasItem(DEFAULT_BLOCK_RATE)))
            .andExpect(jsonPath("$.[*].passCutRate").value(hasItem(DEFAULT_PASS_CUT_RATE)))
            .andExpect(jsonPath("$.[*].clearRate").value(hasItem(DEFAULT_CLEAR_RATE)))
            .andExpect(jsonPath("$.[*].competeRate").value(hasItem(DEFAULT_COMPETE_RATE)))
            .andExpect(jsonPath("$.[*].trapRate").value(hasItem(DEFAULT_TRAP_RATE)));
    }
    
    @Test
    @Transactional
    public void getMAreaActionWeight() throws Exception {
        // Initialize the database
        mAreaActionWeightRepository.saveAndFlush(mAreaActionWeight);

        // Get the mAreaActionWeight
        restMAreaActionWeightMockMvc.perform(get("/api/m-area-action-weights/{id}", mAreaActionWeight.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(mAreaActionWeight.getId().intValue()))
            .andExpect(jsonPath("$.areaType").value(DEFAULT_AREA_TYPE))
            .andExpect(jsonPath("$.dribbleRate").value(DEFAULT_DRIBBLE_RATE))
            .andExpect(jsonPath("$.passingRate").value(DEFAULT_PASSING_RATE))
            .andExpect(jsonPath("$.onetwoRate").value(DEFAULT_ONETWO_RATE))
            .andExpect(jsonPath("$.shootRate").value(DEFAULT_SHOOT_RATE))
            .andExpect(jsonPath("$.volleyShootRate").value(DEFAULT_VOLLEY_SHOOT_RATE))
            .andExpect(jsonPath("$.headingShootRate").value(DEFAULT_HEADING_SHOOT_RATE))
            .andExpect(jsonPath("$.tackleRate").value(DEFAULT_TACKLE_RATE))
            .andExpect(jsonPath("$.blockRate").value(DEFAULT_BLOCK_RATE))
            .andExpect(jsonPath("$.passCutRate").value(DEFAULT_PASS_CUT_RATE))
            .andExpect(jsonPath("$.clearRate").value(DEFAULT_CLEAR_RATE))
            .andExpect(jsonPath("$.competeRate").value(DEFAULT_COMPETE_RATE))
            .andExpect(jsonPath("$.trapRate").value(DEFAULT_TRAP_RATE));
    }

    @Test
    @Transactional
    public void getAllMAreaActionWeightsByAreaTypeIsEqualToSomething() throws Exception {
        // Initialize the database
        mAreaActionWeightRepository.saveAndFlush(mAreaActionWeight);

        // Get all the mAreaActionWeightList where areaType equals to DEFAULT_AREA_TYPE
        defaultMAreaActionWeightShouldBeFound("areaType.equals=" + DEFAULT_AREA_TYPE);

        // Get all the mAreaActionWeightList where areaType equals to UPDATED_AREA_TYPE
        defaultMAreaActionWeightShouldNotBeFound("areaType.equals=" + UPDATED_AREA_TYPE);
    }

    @Test
    @Transactional
    public void getAllMAreaActionWeightsByAreaTypeIsInShouldWork() throws Exception {
        // Initialize the database
        mAreaActionWeightRepository.saveAndFlush(mAreaActionWeight);

        // Get all the mAreaActionWeightList where areaType in DEFAULT_AREA_TYPE or UPDATED_AREA_TYPE
        defaultMAreaActionWeightShouldBeFound("areaType.in=" + DEFAULT_AREA_TYPE + "," + UPDATED_AREA_TYPE);

        // Get all the mAreaActionWeightList where areaType equals to UPDATED_AREA_TYPE
        defaultMAreaActionWeightShouldNotBeFound("areaType.in=" + UPDATED_AREA_TYPE);
    }

    @Test
    @Transactional
    public void getAllMAreaActionWeightsByAreaTypeIsNullOrNotNull() throws Exception {
        // Initialize the database
        mAreaActionWeightRepository.saveAndFlush(mAreaActionWeight);

        // Get all the mAreaActionWeightList where areaType is not null
        defaultMAreaActionWeightShouldBeFound("areaType.specified=true");

        // Get all the mAreaActionWeightList where areaType is null
        defaultMAreaActionWeightShouldNotBeFound("areaType.specified=false");
    }

    @Test
    @Transactional
    public void getAllMAreaActionWeightsByAreaTypeIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mAreaActionWeightRepository.saveAndFlush(mAreaActionWeight);

        // Get all the mAreaActionWeightList where areaType greater than or equals to DEFAULT_AREA_TYPE
        defaultMAreaActionWeightShouldBeFound("areaType.greaterOrEqualThan=" + DEFAULT_AREA_TYPE);

        // Get all the mAreaActionWeightList where areaType greater than or equals to UPDATED_AREA_TYPE
        defaultMAreaActionWeightShouldNotBeFound("areaType.greaterOrEqualThan=" + UPDATED_AREA_TYPE);
    }

    @Test
    @Transactional
    public void getAllMAreaActionWeightsByAreaTypeIsLessThanSomething() throws Exception {
        // Initialize the database
        mAreaActionWeightRepository.saveAndFlush(mAreaActionWeight);

        // Get all the mAreaActionWeightList where areaType less than or equals to DEFAULT_AREA_TYPE
        defaultMAreaActionWeightShouldNotBeFound("areaType.lessThan=" + DEFAULT_AREA_TYPE);

        // Get all the mAreaActionWeightList where areaType less than or equals to UPDATED_AREA_TYPE
        defaultMAreaActionWeightShouldBeFound("areaType.lessThan=" + UPDATED_AREA_TYPE);
    }


    @Test
    @Transactional
    public void getAllMAreaActionWeightsByDribbleRateIsEqualToSomething() throws Exception {
        // Initialize the database
        mAreaActionWeightRepository.saveAndFlush(mAreaActionWeight);

        // Get all the mAreaActionWeightList where dribbleRate equals to DEFAULT_DRIBBLE_RATE
        defaultMAreaActionWeightShouldBeFound("dribbleRate.equals=" + DEFAULT_DRIBBLE_RATE);

        // Get all the mAreaActionWeightList where dribbleRate equals to UPDATED_DRIBBLE_RATE
        defaultMAreaActionWeightShouldNotBeFound("dribbleRate.equals=" + UPDATED_DRIBBLE_RATE);
    }

    @Test
    @Transactional
    public void getAllMAreaActionWeightsByDribbleRateIsInShouldWork() throws Exception {
        // Initialize the database
        mAreaActionWeightRepository.saveAndFlush(mAreaActionWeight);

        // Get all the mAreaActionWeightList where dribbleRate in DEFAULT_DRIBBLE_RATE or UPDATED_DRIBBLE_RATE
        defaultMAreaActionWeightShouldBeFound("dribbleRate.in=" + DEFAULT_DRIBBLE_RATE + "," + UPDATED_DRIBBLE_RATE);

        // Get all the mAreaActionWeightList where dribbleRate equals to UPDATED_DRIBBLE_RATE
        defaultMAreaActionWeightShouldNotBeFound("dribbleRate.in=" + UPDATED_DRIBBLE_RATE);
    }

    @Test
    @Transactional
    public void getAllMAreaActionWeightsByDribbleRateIsNullOrNotNull() throws Exception {
        // Initialize the database
        mAreaActionWeightRepository.saveAndFlush(mAreaActionWeight);

        // Get all the mAreaActionWeightList where dribbleRate is not null
        defaultMAreaActionWeightShouldBeFound("dribbleRate.specified=true");

        // Get all the mAreaActionWeightList where dribbleRate is null
        defaultMAreaActionWeightShouldNotBeFound("dribbleRate.specified=false");
    }

    @Test
    @Transactional
    public void getAllMAreaActionWeightsByDribbleRateIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mAreaActionWeightRepository.saveAndFlush(mAreaActionWeight);

        // Get all the mAreaActionWeightList where dribbleRate greater than or equals to DEFAULT_DRIBBLE_RATE
        defaultMAreaActionWeightShouldBeFound("dribbleRate.greaterOrEqualThan=" + DEFAULT_DRIBBLE_RATE);

        // Get all the mAreaActionWeightList where dribbleRate greater than or equals to UPDATED_DRIBBLE_RATE
        defaultMAreaActionWeightShouldNotBeFound("dribbleRate.greaterOrEqualThan=" + UPDATED_DRIBBLE_RATE);
    }

    @Test
    @Transactional
    public void getAllMAreaActionWeightsByDribbleRateIsLessThanSomething() throws Exception {
        // Initialize the database
        mAreaActionWeightRepository.saveAndFlush(mAreaActionWeight);

        // Get all the mAreaActionWeightList where dribbleRate less than or equals to DEFAULT_DRIBBLE_RATE
        defaultMAreaActionWeightShouldNotBeFound("dribbleRate.lessThan=" + DEFAULT_DRIBBLE_RATE);

        // Get all the mAreaActionWeightList where dribbleRate less than or equals to UPDATED_DRIBBLE_RATE
        defaultMAreaActionWeightShouldBeFound("dribbleRate.lessThan=" + UPDATED_DRIBBLE_RATE);
    }


    @Test
    @Transactional
    public void getAllMAreaActionWeightsByPassingRateIsEqualToSomething() throws Exception {
        // Initialize the database
        mAreaActionWeightRepository.saveAndFlush(mAreaActionWeight);

        // Get all the mAreaActionWeightList where passingRate equals to DEFAULT_PASSING_RATE
        defaultMAreaActionWeightShouldBeFound("passingRate.equals=" + DEFAULT_PASSING_RATE);

        // Get all the mAreaActionWeightList where passingRate equals to UPDATED_PASSING_RATE
        defaultMAreaActionWeightShouldNotBeFound("passingRate.equals=" + UPDATED_PASSING_RATE);
    }

    @Test
    @Transactional
    public void getAllMAreaActionWeightsByPassingRateIsInShouldWork() throws Exception {
        // Initialize the database
        mAreaActionWeightRepository.saveAndFlush(mAreaActionWeight);

        // Get all the mAreaActionWeightList where passingRate in DEFAULT_PASSING_RATE or UPDATED_PASSING_RATE
        defaultMAreaActionWeightShouldBeFound("passingRate.in=" + DEFAULT_PASSING_RATE + "," + UPDATED_PASSING_RATE);

        // Get all the mAreaActionWeightList where passingRate equals to UPDATED_PASSING_RATE
        defaultMAreaActionWeightShouldNotBeFound("passingRate.in=" + UPDATED_PASSING_RATE);
    }

    @Test
    @Transactional
    public void getAllMAreaActionWeightsByPassingRateIsNullOrNotNull() throws Exception {
        // Initialize the database
        mAreaActionWeightRepository.saveAndFlush(mAreaActionWeight);

        // Get all the mAreaActionWeightList where passingRate is not null
        defaultMAreaActionWeightShouldBeFound("passingRate.specified=true");

        // Get all the mAreaActionWeightList where passingRate is null
        defaultMAreaActionWeightShouldNotBeFound("passingRate.specified=false");
    }

    @Test
    @Transactional
    public void getAllMAreaActionWeightsByPassingRateIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mAreaActionWeightRepository.saveAndFlush(mAreaActionWeight);

        // Get all the mAreaActionWeightList where passingRate greater than or equals to DEFAULT_PASSING_RATE
        defaultMAreaActionWeightShouldBeFound("passingRate.greaterOrEqualThan=" + DEFAULT_PASSING_RATE);

        // Get all the mAreaActionWeightList where passingRate greater than or equals to UPDATED_PASSING_RATE
        defaultMAreaActionWeightShouldNotBeFound("passingRate.greaterOrEqualThan=" + UPDATED_PASSING_RATE);
    }

    @Test
    @Transactional
    public void getAllMAreaActionWeightsByPassingRateIsLessThanSomething() throws Exception {
        // Initialize the database
        mAreaActionWeightRepository.saveAndFlush(mAreaActionWeight);

        // Get all the mAreaActionWeightList where passingRate less than or equals to DEFAULT_PASSING_RATE
        defaultMAreaActionWeightShouldNotBeFound("passingRate.lessThan=" + DEFAULT_PASSING_RATE);

        // Get all the mAreaActionWeightList where passingRate less than or equals to UPDATED_PASSING_RATE
        defaultMAreaActionWeightShouldBeFound("passingRate.lessThan=" + UPDATED_PASSING_RATE);
    }


    @Test
    @Transactional
    public void getAllMAreaActionWeightsByOnetwoRateIsEqualToSomething() throws Exception {
        // Initialize the database
        mAreaActionWeightRepository.saveAndFlush(mAreaActionWeight);

        // Get all the mAreaActionWeightList where onetwoRate equals to DEFAULT_ONETWO_RATE
        defaultMAreaActionWeightShouldBeFound("onetwoRate.equals=" + DEFAULT_ONETWO_RATE);

        // Get all the mAreaActionWeightList where onetwoRate equals to UPDATED_ONETWO_RATE
        defaultMAreaActionWeightShouldNotBeFound("onetwoRate.equals=" + UPDATED_ONETWO_RATE);
    }

    @Test
    @Transactional
    public void getAllMAreaActionWeightsByOnetwoRateIsInShouldWork() throws Exception {
        // Initialize the database
        mAreaActionWeightRepository.saveAndFlush(mAreaActionWeight);

        // Get all the mAreaActionWeightList where onetwoRate in DEFAULT_ONETWO_RATE or UPDATED_ONETWO_RATE
        defaultMAreaActionWeightShouldBeFound("onetwoRate.in=" + DEFAULT_ONETWO_RATE + "," + UPDATED_ONETWO_RATE);

        // Get all the mAreaActionWeightList where onetwoRate equals to UPDATED_ONETWO_RATE
        defaultMAreaActionWeightShouldNotBeFound("onetwoRate.in=" + UPDATED_ONETWO_RATE);
    }

    @Test
    @Transactional
    public void getAllMAreaActionWeightsByOnetwoRateIsNullOrNotNull() throws Exception {
        // Initialize the database
        mAreaActionWeightRepository.saveAndFlush(mAreaActionWeight);

        // Get all the mAreaActionWeightList where onetwoRate is not null
        defaultMAreaActionWeightShouldBeFound("onetwoRate.specified=true");

        // Get all the mAreaActionWeightList where onetwoRate is null
        defaultMAreaActionWeightShouldNotBeFound("onetwoRate.specified=false");
    }

    @Test
    @Transactional
    public void getAllMAreaActionWeightsByOnetwoRateIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mAreaActionWeightRepository.saveAndFlush(mAreaActionWeight);

        // Get all the mAreaActionWeightList where onetwoRate greater than or equals to DEFAULT_ONETWO_RATE
        defaultMAreaActionWeightShouldBeFound("onetwoRate.greaterOrEqualThan=" + DEFAULT_ONETWO_RATE);

        // Get all the mAreaActionWeightList where onetwoRate greater than or equals to UPDATED_ONETWO_RATE
        defaultMAreaActionWeightShouldNotBeFound("onetwoRate.greaterOrEqualThan=" + UPDATED_ONETWO_RATE);
    }

    @Test
    @Transactional
    public void getAllMAreaActionWeightsByOnetwoRateIsLessThanSomething() throws Exception {
        // Initialize the database
        mAreaActionWeightRepository.saveAndFlush(mAreaActionWeight);

        // Get all the mAreaActionWeightList where onetwoRate less than or equals to DEFAULT_ONETWO_RATE
        defaultMAreaActionWeightShouldNotBeFound("onetwoRate.lessThan=" + DEFAULT_ONETWO_RATE);

        // Get all the mAreaActionWeightList where onetwoRate less than or equals to UPDATED_ONETWO_RATE
        defaultMAreaActionWeightShouldBeFound("onetwoRate.lessThan=" + UPDATED_ONETWO_RATE);
    }


    @Test
    @Transactional
    public void getAllMAreaActionWeightsByShootRateIsEqualToSomething() throws Exception {
        // Initialize the database
        mAreaActionWeightRepository.saveAndFlush(mAreaActionWeight);

        // Get all the mAreaActionWeightList where shootRate equals to DEFAULT_SHOOT_RATE
        defaultMAreaActionWeightShouldBeFound("shootRate.equals=" + DEFAULT_SHOOT_RATE);

        // Get all the mAreaActionWeightList where shootRate equals to UPDATED_SHOOT_RATE
        defaultMAreaActionWeightShouldNotBeFound("shootRate.equals=" + UPDATED_SHOOT_RATE);
    }

    @Test
    @Transactional
    public void getAllMAreaActionWeightsByShootRateIsInShouldWork() throws Exception {
        // Initialize the database
        mAreaActionWeightRepository.saveAndFlush(mAreaActionWeight);

        // Get all the mAreaActionWeightList where shootRate in DEFAULT_SHOOT_RATE or UPDATED_SHOOT_RATE
        defaultMAreaActionWeightShouldBeFound("shootRate.in=" + DEFAULT_SHOOT_RATE + "," + UPDATED_SHOOT_RATE);

        // Get all the mAreaActionWeightList where shootRate equals to UPDATED_SHOOT_RATE
        defaultMAreaActionWeightShouldNotBeFound("shootRate.in=" + UPDATED_SHOOT_RATE);
    }

    @Test
    @Transactional
    public void getAllMAreaActionWeightsByShootRateIsNullOrNotNull() throws Exception {
        // Initialize the database
        mAreaActionWeightRepository.saveAndFlush(mAreaActionWeight);

        // Get all the mAreaActionWeightList where shootRate is not null
        defaultMAreaActionWeightShouldBeFound("shootRate.specified=true");

        // Get all the mAreaActionWeightList where shootRate is null
        defaultMAreaActionWeightShouldNotBeFound("shootRate.specified=false");
    }

    @Test
    @Transactional
    public void getAllMAreaActionWeightsByShootRateIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mAreaActionWeightRepository.saveAndFlush(mAreaActionWeight);

        // Get all the mAreaActionWeightList where shootRate greater than or equals to DEFAULT_SHOOT_RATE
        defaultMAreaActionWeightShouldBeFound("shootRate.greaterOrEqualThan=" + DEFAULT_SHOOT_RATE);

        // Get all the mAreaActionWeightList where shootRate greater than or equals to UPDATED_SHOOT_RATE
        defaultMAreaActionWeightShouldNotBeFound("shootRate.greaterOrEqualThan=" + UPDATED_SHOOT_RATE);
    }

    @Test
    @Transactional
    public void getAllMAreaActionWeightsByShootRateIsLessThanSomething() throws Exception {
        // Initialize the database
        mAreaActionWeightRepository.saveAndFlush(mAreaActionWeight);

        // Get all the mAreaActionWeightList where shootRate less than or equals to DEFAULT_SHOOT_RATE
        defaultMAreaActionWeightShouldNotBeFound("shootRate.lessThan=" + DEFAULT_SHOOT_RATE);

        // Get all the mAreaActionWeightList where shootRate less than or equals to UPDATED_SHOOT_RATE
        defaultMAreaActionWeightShouldBeFound("shootRate.lessThan=" + UPDATED_SHOOT_RATE);
    }


    @Test
    @Transactional
    public void getAllMAreaActionWeightsByVolleyShootRateIsEqualToSomething() throws Exception {
        // Initialize the database
        mAreaActionWeightRepository.saveAndFlush(mAreaActionWeight);

        // Get all the mAreaActionWeightList where volleyShootRate equals to DEFAULT_VOLLEY_SHOOT_RATE
        defaultMAreaActionWeightShouldBeFound("volleyShootRate.equals=" + DEFAULT_VOLLEY_SHOOT_RATE);

        // Get all the mAreaActionWeightList where volleyShootRate equals to UPDATED_VOLLEY_SHOOT_RATE
        defaultMAreaActionWeightShouldNotBeFound("volleyShootRate.equals=" + UPDATED_VOLLEY_SHOOT_RATE);
    }

    @Test
    @Transactional
    public void getAllMAreaActionWeightsByVolleyShootRateIsInShouldWork() throws Exception {
        // Initialize the database
        mAreaActionWeightRepository.saveAndFlush(mAreaActionWeight);

        // Get all the mAreaActionWeightList where volleyShootRate in DEFAULT_VOLLEY_SHOOT_RATE or UPDATED_VOLLEY_SHOOT_RATE
        defaultMAreaActionWeightShouldBeFound("volleyShootRate.in=" + DEFAULT_VOLLEY_SHOOT_RATE + "," + UPDATED_VOLLEY_SHOOT_RATE);

        // Get all the mAreaActionWeightList where volleyShootRate equals to UPDATED_VOLLEY_SHOOT_RATE
        defaultMAreaActionWeightShouldNotBeFound("volleyShootRate.in=" + UPDATED_VOLLEY_SHOOT_RATE);
    }

    @Test
    @Transactional
    public void getAllMAreaActionWeightsByVolleyShootRateIsNullOrNotNull() throws Exception {
        // Initialize the database
        mAreaActionWeightRepository.saveAndFlush(mAreaActionWeight);

        // Get all the mAreaActionWeightList where volleyShootRate is not null
        defaultMAreaActionWeightShouldBeFound("volleyShootRate.specified=true");

        // Get all the mAreaActionWeightList where volleyShootRate is null
        defaultMAreaActionWeightShouldNotBeFound("volleyShootRate.specified=false");
    }

    @Test
    @Transactional
    public void getAllMAreaActionWeightsByVolleyShootRateIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mAreaActionWeightRepository.saveAndFlush(mAreaActionWeight);

        // Get all the mAreaActionWeightList where volleyShootRate greater than or equals to DEFAULT_VOLLEY_SHOOT_RATE
        defaultMAreaActionWeightShouldBeFound("volleyShootRate.greaterOrEqualThan=" + DEFAULT_VOLLEY_SHOOT_RATE);

        // Get all the mAreaActionWeightList where volleyShootRate greater than or equals to UPDATED_VOLLEY_SHOOT_RATE
        defaultMAreaActionWeightShouldNotBeFound("volleyShootRate.greaterOrEqualThan=" + UPDATED_VOLLEY_SHOOT_RATE);
    }

    @Test
    @Transactional
    public void getAllMAreaActionWeightsByVolleyShootRateIsLessThanSomething() throws Exception {
        // Initialize the database
        mAreaActionWeightRepository.saveAndFlush(mAreaActionWeight);

        // Get all the mAreaActionWeightList where volleyShootRate less than or equals to DEFAULT_VOLLEY_SHOOT_RATE
        defaultMAreaActionWeightShouldNotBeFound("volleyShootRate.lessThan=" + DEFAULT_VOLLEY_SHOOT_RATE);

        // Get all the mAreaActionWeightList where volleyShootRate less than or equals to UPDATED_VOLLEY_SHOOT_RATE
        defaultMAreaActionWeightShouldBeFound("volleyShootRate.lessThan=" + UPDATED_VOLLEY_SHOOT_RATE);
    }


    @Test
    @Transactional
    public void getAllMAreaActionWeightsByHeadingShootRateIsEqualToSomething() throws Exception {
        // Initialize the database
        mAreaActionWeightRepository.saveAndFlush(mAreaActionWeight);

        // Get all the mAreaActionWeightList where headingShootRate equals to DEFAULT_HEADING_SHOOT_RATE
        defaultMAreaActionWeightShouldBeFound("headingShootRate.equals=" + DEFAULT_HEADING_SHOOT_RATE);

        // Get all the mAreaActionWeightList where headingShootRate equals to UPDATED_HEADING_SHOOT_RATE
        defaultMAreaActionWeightShouldNotBeFound("headingShootRate.equals=" + UPDATED_HEADING_SHOOT_RATE);
    }

    @Test
    @Transactional
    public void getAllMAreaActionWeightsByHeadingShootRateIsInShouldWork() throws Exception {
        // Initialize the database
        mAreaActionWeightRepository.saveAndFlush(mAreaActionWeight);

        // Get all the mAreaActionWeightList where headingShootRate in DEFAULT_HEADING_SHOOT_RATE or UPDATED_HEADING_SHOOT_RATE
        defaultMAreaActionWeightShouldBeFound("headingShootRate.in=" + DEFAULT_HEADING_SHOOT_RATE + "," + UPDATED_HEADING_SHOOT_RATE);

        // Get all the mAreaActionWeightList where headingShootRate equals to UPDATED_HEADING_SHOOT_RATE
        defaultMAreaActionWeightShouldNotBeFound("headingShootRate.in=" + UPDATED_HEADING_SHOOT_RATE);
    }

    @Test
    @Transactional
    public void getAllMAreaActionWeightsByHeadingShootRateIsNullOrNotNull() throws Exception {
        // Initialize the database
        mAreaActionWeightRepository.saveAndFlush(mAreaActionWeight);

        // Get all the mAreaActionWeightList where headingShootRate is not null
        defaultMAreaActionWeightShouldBeFound("headingShootRate.specified=true");

        // Get all the mAreaActionWeightList where headingShootRate is null
        defaultMAreaActionWeightShouldNotBeFound("headingShootRate.specified=false");
    }

    @Test
    @Transactional
    public void getAllMAreaActionWeightsByHeadingShootRateIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mAreaActionWeightRepository.saveAndFlush(mAreaActionWeight);

        // Get all the mAreaActionWeightList where headingShootRate greater than or equals to DEFAULT_HEADING_SHOOT_RATE
        defaultMAreaActionWeightShouldBeFound("headingShootRate.greaterOrEqualThan=" + DEFAULT_HEADING_SHOOT_RATE);

        // Get all the mAreaActionWeightList where headingShootRate greater than or equals to UPDATED_HEADING_SHOOT_RATE
        defaultMAreaActionWeightShouldNotBeFound("headingShootRate.greaterOrEqualThan=" + UPDATED_HEADING_SHOOT_RATE);
    }

    @Test
    @Transactional
    public void getAllMAreaActionWeightsByHeadingShootRateIsLessThanSomething() throws Exception {
        // Initialize the database
        mAreaActionWeightRepository.saveAndFlush(mAreaActionWeight);

        // Get all the mAreaActionWeightList where headingShootRate less than or equals to DEFAULT_HEADING_SHOOT_RATE
        defaultMAreaActionWeightShouldNotBeFound("headingShootRate.lessThan=" + DEFAULT_HEADING_SHOOT_RATE);

        // Get all the mAreaActionWeightList where headingShootRate less than or equals to UPDATED_HEADING_SHOOT_RATE
        defaultMAreaActionWeightShouldBeFound("headingShootRate.lessThan=" + UPDATED_HEADING_SHOOT_RATE);
    }


    @Test
    @Transactional
    public void getAllMAreaActionWeightsByTackleRateIsEqualToSomething() throws Exception {
        // Initialize the database
        mAreaActionWeightRepository.saveAndFlush(mAreaActionWeight);

        // Get all the mAreaActionWeightList where tackleRate equals to DEFAULT_TACKLE_RATE
        defaultMAreaActionWeightShouldBeFound("tackleRate.equals=" + DEFAULT_TACKLE_RATE);

        // Get all the mAreaActionWeightList where tackleRate equals to UPDATED_TACKLE_RATE
        defaultMAreaActionWeightShouldNotBeFound("tackleRate.equals=" + UPDATED_TACKLE_RATE);
    }

    @Test
    @Transactional
    public void getAllMAreaActionWeightsByTackleRateIsInShouldWork() throws Exception {
        // Initialize the database
        mAreaActionWeightRepository.saveAndFlush(mAreaActionWeight);

        // Get all the mAreaActionWeightList where tackleRate in DEFAULT_TACKLE_RATE or UPDATED_TACKLE_RATE
        defaultMAreaActionWeightShouldBeFound("tackleRate.in=" + DEFAULT_TACKLE_RATE + "," + UPDATED_TACKLE_RATE);

        // Get all the mAreaActionWeightList where tackleRate equals to UPDATED_TACKLE_RATE
        defaultMAreaActionWeightShouldNotBeFound("tackleRate.in=" + UPDATED_TACKLE_RATE);
    }

    @Test
    @Transactional
    public void getAllMAreaActionWeightsByTackleRateIsNullOrNotNull() throws Exception {
        // Initialize the database
        mAreaActionWeightRepository.saveAndFlush(mAreaActionWeight);

        // Get all the mAreaActionWeightList where tackleRate is not null
        defaultMAreaActionWeightShouldBeFound("tackleRate.specified=true");

        // Get all the mAreaActionWeightList where tackleRate is null
        defaultMAreaActionWeightShouldNotBeFound("tackleRate.specified=false");
    }

    @Test
    @Transactional
    public void getAllMAreaActionWeightsByTackleRateIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mAreaActionWeightRepository.saveAndFlush(mAreaActionWeight);

        // Get all the mAreaActionWeightList where tackleRate greater than or equals to DEFAULT_TACKLE_RATE
        defaultMAreaActionWeightShouldBeFound("tackleRate.greaterOrEqualThan=" + DEFAULT_TACKLE_RATE);

        // Get all the mAreaActionWeightList where tackleRate greater than or equals to UPDATED_TACKLE_RATE
        defaultMAreaActionWeightShouldNotBeFound("tackleRate.greaterOrEqualThan=" + UPDATED_TACKLE_RATE);
    }

    @Test
    @Transactional
    public void getAllMAreaActionWeightsByTackleRateIsLessThanSomething() throws Exception {
        // Initialize the database
        mAreaActionWeightRepository.saveAndFlush(mAreaActionWeight);

        // Get all the mAreaActionWeightList where tackleRate less than or equals to DEFAULT_TACKLE_RATE
        defaultMAreaActionWeightShouldNotBeFound("tackleRate.lessThan=" + DEFAULT_TACKLE_RATE);

        // Get all the mAreaActionWeightList where tackleRate less than or equals to UPDATED_TACKLE_RATE
        defaultMAreaActionWeightShouldBeFound("tackleRate.lessThan=" + UPDATED_TACKLE_RATE);
    }


    @Test
    @Transactional
    public void getAllMAreaActionWeightsByBlockRateIsEqualToSomething() throws Exception {
        // Initialize the database
        mAreaActionWeightRepository.saveAndFlush(mAreaActionWeight);

        // Get all the mAreaActionWeightList where blockRate equals to DEFAULT_BLOCK_RATE
        defaultMAreaActionWeightShouldBeFound("blockRate.equals=" + DEFAULT_BLOCK_RATE);

        // Get all the mAreaActionWeightList where blockRate equals to UPDATED_BLOCK_RATE
        defaultMAreaActionWeightShouldNotBeFound("blockRate.equals=" + UPDATED_BLOCK_RATE);
    }

    @Test
    @Transactional
    public void getAllMAreaActionWeightsByBlockRateIsInShouldWork() throws Exception {
        // Initialize the database
        mAreaActionWeightRepository.saveAndFlush(mAreaActionWeight);

        // Get all the mAreaActionWeightList where blockRate in DEFAULT_BLOCK_RATE or UPDATED_BLOCK_RATE
        defaultMAreaActionWeightShouldBeFound("blockRate.in=" + DEFAULT_BLOCK_RATE + "," + UPDATED_BLOCK_RATE);

        // Get all the mAreaActionWeightList where blockRate equals to UPDATED_BLOCK_RATE
        defaultMAreaActionWeightShouldNotBeFound("blockRate.in=" + UPDATED_BLOCK_RATE);
    }

    @Test
    @Transactional
    public void getAllMAreaActionWeightsByBlockRateIsNullOrNotNull() throws Exception {
        // Initialize the database
        mAreaActionWeightRepository.saveAndFlush(mAreaActionWeight);

        // Get all the mAreaActionWeightList where blockRate is not null
        defaultMAreaActionWeightShouldBeFound("blockRate.specified=true");

        // Get all the mAreaActionWeightList where blockRate is null
        defaultMAreaActionWeightShouldNotBeFound("blockRate.specified=false");
    }

    @Test
    @Transactional
    public void getAllMAreaActionWeightsByBlockRateIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mAreaActionWeightRepository.saveAndFlush(mAreaActionWeight);

        // Get all the mAreaActionWeightList where blockRate greater than or equals to DEFAULT_BLOCK_RATE
        defaultMAreaActionWeightShouldBeFound("blockRate.greaterOrEqualThan=" + DEFAULT_BLOCK_RATE);

        // Get all the mAreaActionWeightList where blockRate greater than or equals to UPDATED_BLOCK_RATE
        defaultMAreaActionWeightShouldNotBeFound("blockRate.greaterOrEqualThan=" + UPDATED_BLOCK_RATE);
    }

    @Test
    @Transactional
    public void getAllMAreaActionWeightsByBlockRateIsLessThanSomething() throws Exception {
        // Initialize the database
        mAreaActionWeightRepository.saveAndFlush(mAreaActionWeight);

        // Get all the mAreaActionWeightList where blockRate less than or equals to DEFAULT_BLOCK_RATE
        defaultMAreaActionWeightShouldNotBeFound("blockRate.lessThan=" + DEFAULT_BLOCK_RATE);

        // Get all the mAreaActionWeightList where blockRate less than or equals to UPDATED_BLOCK_RATE
        defaultMAreaActionWeightShouldBeFound("blockRate.lessThan=" + UPDATED_BLOCK_RATE);
    }


    @Test
    @Transactional
    public void getAllMAreaActionWeightsByPassCutRateIsEqualToSomething() throws Exception {
        // Initialize the database
        mAreaActionWeightRepository.saveAndFlush(mAreaActionWeight);

        // Get all the mAreaActionWeightList where passCutRate equals to DEFAULT_PASS_CUT_RATE
        defaultMAreaActionWeightShouldBeFound("passCutRate.equals=" + DEFAULT_PASS_CUT_RATE);

        // Get all the mAreaActionWeightList where passCutRate equals to UPDATED_PASS_CUT_RATE
        defaultMAreaActionWeightShouldNotBeFound("passCutRate.equals=" + UPDATED_PASS_CUT_RATE);
    }

    @Test
    @Transactional
    public void getAllMAreaActionWeightsByPassCutRateIsInShouldWork() throws Exception {
        // Initialize the database
        mAreaActionWeightRepository.saveAndFlush(mAreaActionWeight);

        // Get all the mAreaActionWeightList where passCutRate in DEFAULT_PASS_CUT_RATE or UPDATED_PASS_CUT_RATE
        defaultMAreaActionWeightShouldBeFound("passCutRate.in=" + DEFAULT_PASS_CUT_RATE + "," + UPDATED_PASS_CUT_RATE);

        // Get all the mAreaActionWeightList where passCutRate equals to UPDATED_PASS_CUT_RATE
        defaultMAreaActionWeightShouldNotBeFound("passCutRate.in=" + UPDATED_PASS_CUT_RATE);
    }

    @Test
    @Transactional
    public void getAllMAreaActionWeightsByPassCutRateIsNullOrNotNull() throws Exception {
        // Initialize the database
        mAreaActionWeightRepository.saveAndFlush(mAreaActionWeight);

        // Get all the mAreaActionWeightList where passCutRate is not null
        defaultMAreaActionWeightShouldBeFound("passCutRate.specified=true");

        // Get all the mAreaActionWeightList where passCutRate is null
        defaultMAreaActionWeightShouldNotBeFound("passCutRate.specified=false");
    }

    @Test
    @Transactional
    public void getAllMAreaActionWeightsByPassCutRateIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mAreaActionWeightRepository.saveAndFlush(mAreaActionWeight);

        // Get all the mAreaActionWeightList where passCutRate greater than or equals to DEFAULT_PASS_CUT_RATE
        defaultMAreaActionWeightShouldBeFound("passCutRate.greaterOrEqualThan=" + DEFAULT_PASS_CUT_RATE);

        // Get all the mAreaActionWeightList where passCutRate greater than or equals to UPDATED_PASS_CUT_RATE
        defaultMAreaActionWeightShouldNotBeFound("passCutRate.greaterOrEqualThan=" + UPDATED_PASS_CUT_RATE);
    }

    @Test
    @Transactional
    public void getAllMAreaActionWeightsByPassCutRateIsLessThanSomething() throws Exception {
        // Initialize the database
        mAreaActionWeightRepository.saveAndFlush(mAreaActionWeight);

        // Get all the mAreaActionWeightList where passCutRate less than or equals to DEFAULT_PASS_CUT_RATE
        defaultMAreaActionWeightShouldNotBeFound("passCutRate.lessThan=" + DEFAULT_PASS_CUT_RATE);

        // Get all the mAreaActionWeightList where passCutRate less than or equals to UPDATED_PASS_CUT_RATE
        defaultMAreaActionWeightShouldBeFound("passCutRate.lessThan=" + UPDATED_PASS_CUT_RATE);
    }


    @Test
    @Transactional
    public void getAllMAreaActionWeightsByClearRateIsEqualToSomething() throws Exception {
        // Initialize the database
        mAreaActionWeightRepository.saveAndFlush(mAreaActionWeight);

        // Get all the mAreaActionWeightList where clearRate equals to DEFAULT_CLEAR_RATE
        defaultMAreaActionWeightShouldBeFound("clearRate.equals=" + DEFAULT_CLEAR_RATE);

        // Get all the mAreaActionWeightList where clearRate equals to UPDATED_CLEAR_RATE
        defaultMAreaActionWeightShouldNotBeFound("clearRate.equals=" + UPDATED_CLEAR_RATE);
    }

    @Test
    @Transactional
    public void getAllMAreaActionWeightsByClearRateIsInShouldWork() throws Exception {
        // Initialize the database
        mAreaActionWeightRepository.saveAndFlush(mAreaActionWeight);

        // Get all the mAreaActionWeightList where clearRate in DEFAULT_CLEAR_RATE or UPDATED_CLEAR_RATE
        defaultMAreaActionWeightShouldBeFound("clearRate.in=" + DEFAULT_CLEAR_RATE + "," + UPDATED_CLEAR_RATE);

        // Get all the mAreaActionWeightList where clearRate equals to UPDATED_CLEAR_RATE
        defaultMAreaActionWeightShouldNotBeFound("clearRate.in=" + UPDATED_CLEAR_RATE);
    }

    @Test
    @Transactional
    public void getAllMAreaActionWeightsByClearRateIsNullOrNotNull() throws Exception {
        // Initialize the database
        mAreaActionWeightRepository.saveAndFlush(mAreaActionWeight);

        // Get all the mAreaActionWeightList where clearRate is not null
        defaultMAreaActionWeightShouldBeFound("clearRate.specified=true");

        // Get all the mAreaActionWeightList where clearRate is null
        defaultMAreaActionWeightShouldNotBeFound("clearRate.specified=false");
    }

    @Test
    @Transactional
    public void getAllMAreaActionWeightsByClearRateIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mAreaActionWeightRepository.saveAndFlush(mAreaActionWeight);

        // Get all the mAreaActionWeightList where clearRate greater than or equals to DEFAULT_CLEAR_RATE
        defaultMAreaActionWeightShouldBeFound("clearRate.greaterOrEqualThan=" + DEFAULT_CLEAR_RATE);

        // Get all the mAreaActionWeightList where clearRate greater than or equals to UPDATED_CLEAR_RATE
        defaultMAreaActionWeightShouldNotBeFound("clearRate.greaterOrEqualThan=" + UPDATED_CLEAR_RATE);
    }

    @Test
    @Transactional
    public void getAllMAreaActionWeightsByClearRateIsLessThanSomething() throws Exception {
        // Initialize the database
        mAreaActionWeightRepository.saveAndFlush(mAreaActionWeight);

        // Get all the mAreaActionWeightList where clearRate less than or equals to DEFAULT_CLEAR_RATE
        defaultMAreaActionWeightShouldNotBeFound("clearRate.lessThan=" + DEFAULT_CLEAR_RATE);

        // Get all the mAreaActionWeightList where clearRate less than or equals to UPDATED_CLEAR_RATE
        defaultMAreaActionWeightShouldBeFound("clearRate.lessThan=" + UPDATED_CLEAR_RATE);
    }


    @Test
    @Transactional
    public void getAllMAreaActionWeightsByCompeteRateIsEqualToSomething() throws Exception {
        // Initialize the database
        mAreaActionWeightRepository.saveAndFlush(mAreaActionWeight);

        // Get all the mAreaActionWeightList where competeRate equals to DEFAULT_COMPETE_RATE
        defaultMAreaActionWeightShouldBeFound("competeRate.equals=" + DEFAULT_COMPETE_RATE);

        // Get all the mAreaActionWeightList where competeRate equals to UPDATED_COMPETE_RATE
        defaultMAreaActionWeightShouldNotBeFound("competeRate.equals=" + UPDATED_COMPETE_RATE);
    }

    @Test
    @Transactional
    public void getAllMAreaActionWeightsByCompeteRateIsInShouldWork() throws Exception {
        // Initialize the database
        mAreaActionWeightRepository.saveAndFlush(mAreaActionWeight);

        // Get all the mAreaActionWeightList where competeRate in DEFAULT_COMPETE_RATE or UPDATED_COMPETE_RATE
        defaultMAreaActionWeightShouldBeFound("competeRate.in=" + DEFAULT_COMPETE_RATE + "," + UPDATED_COMPETE_RATE);

        // Get all the mAreaActionWeightList where competeRate equals to UPDATED_COMPETE_RATE
        defaultMAreaActionWeightShouldNotBeFound("competeRate.in=" + UPDATED_COMPETE_RATE);
    }

    @Test
    @Transactional
    public void getAllMAreaActionWeightsByCompeteRateIsNullOrNotNull() throws Exception {
        // Initialize the database
        mAreaActionWeightRepository.saveAndFlush(mAreaActionWeight);

        // Get all the mAreaActionWeightList where competeRate is not null
        defaultMAreaActionWeightShouldBeFound("competeRate.specified=true");

        // Get all the mAreaActionWeightList where competeRate is null
        defaultMAreaActionWeightShouldNotBeFound("competeRate.specified=false");
    }

    @Test
    @Transactional
    public void getAllMAreaActionWeightsByCompeteRateIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mAreaActionWeightRepository.saveAndFlush(mAreaActionWeight);

        // Get all the mAreaActionWeightList where competeRate greater than or equals to DEFAULT_COMPETE_RATE
        defaultMAreaActionWeightShouldBeFound("competeRate.greaterOrEqualThan=" + DEFAULT_COMPETE_RATE);

        // Get all the mAreaActionWeightList where competeRate greater than or equals to UPDATED_COMPETE_RATE
        defaultMAreaActionWeightShouldNotBeFound("competeRate.greaterOrEqualThan=" + UPDATED_COMPETE_RATE);
    }

    @Test
    @Transactional
    public void getAllMAreaActionWeightsByCompeteRateIsLessThanSomething() throws Exception {
        // Initialize the database
        mAreaActionWeightRepository.saveAndFlush(mAreaActionWeight);

        // Get all the mAreaActionWeightList where competeRate less than or equals to DEFAULT_COMPETE_RATE
        defaultMAreaActionWeightShouldNotBeFound("competeRate.lessThan=" + DEFAULT_COMPETE_RATE);

        // Get all the mAreaActionWeightList where competeRate less than or equals to UPDATED_COMPETE_RATE
        defaultMAreaActionWeightShouldBeFound("competeRate.lessThan=" + UPDATED_COMPETE_RATE);
    }


    @Test
    @Transactional
    public void getAllMAreaActionWeightsByTrapRateIsEqualToSomething() throws Exception {
        // Initialize the database
        mAreaActionWeightRepository.saveAndFlush(mAreaActionWeight);

        // Get all the mAreaActionWeightList where trapRate equals to DEFAULT_TRAP_RATE
        defaultMAreaActionWeightShouldBeFound("trapRate.equals=" + DEFAULT_TRAP_RATE);

        // Get all the mAreaActionWeightList where trapRate equals to UPDATED_TRAP_RATE
        defaultMAreaActionWeightShouldNotBeFound("trapRate.equals=" + UPDATED_TRAP_RATE);
    }

    @Test
    @Transactional
    public void getAllMAreaActionWeightsByTrapRateIsInShouldWork() throws Exception {
        // Initialize the database
        mAreaActionWeightRepository.saveAndFlush(mAreaActionWeight);

        // Get all the mAreaActionWeightList where trapRate in DEFAULT_TRAP_RATE or UPDATED_TRAP_RATE
        defaultMAreaActionWeightShouldBeFound("trapRate.in=" + DEFAULT_TRAP_RATE + "," + UPDATED_TRAP_RATE);

        // Get all the mAreaActionWeightList where trapRate equals to UPDATED_TRAP_RATE
        defaultMAreaActionWeightShouldNotBeFound("trapRate.in=" + UPDATED_TRAP_RATE);
    }

    @Test
    @Transactional
    public void getAllMAreaActionWeightsByTrapRateIsNullOrNotNull() throws Exception {
        // Initialize the database
        mAreaActionWeightRepository.saveAndFlush(mAreaActionWeight);

        // Get all the mAreaActionWeightList where trapRate is not null
        defaultMAreaActionWeightShouldBeFound("trapRate.specified=true");

        // Get all the mAreaActionWeightList where trapRate is null
        defaultMAreaActionWeightShouldNotBeFound("trapRate.specified=false");
    }

    @Test
    @Transactional
    public void getAllMAreaActionWeightsByTrapRateIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mAreaActionWeightRepository.saveAndFlush(mAreaActionWeight);

        // Get all the mAreaActionWeightList where trapRate greater than or equals to DEFAULT_TRAP_RATE
        defaultMAreaActionWeightShouldBeFound("trapRate.greaterOrEqualThan=" + DEFAULT_TRAP_RATE);

        // Get all the mAreaActionWeightList where trapRate greater than or equals to UPDATED_TRAP_RATE
        defaultMAreaActionWeightShouldNotBeFound("trapRate.greaterOrEqualThan=" + UPDATED_TRAP_RATE);
    }

    @Test
    @Transactional
    public void getAllMAreaActionWeightsByTrapRateIsLessThanSomething() throws Exception {
        // Initialize the database
        mAreaActionWeightRepository.saveAndFlush(mAreaActionWeight);

        // Get all the mAreaActionWeightList where trapRate less than or equals to DEFAULT_TRAP_RATE
        defaultMAreaActionWeightShouldNotBeFound("trapRate.lessThan=" + DEFAULT_TRAP_RATE);

        // Get all the mAreaActionWeightList where trapRate less than or equals to UPDATED_TRAP_RATE
        defaultMAreaActionWeightShouldBeFound("trapRate.lessThan=" + UPDATED_TRAP_RATE);
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultMAreaActionWeightShouldBeFound(String filter) throws Exception {
        restMAreaActionWeightMockMvc.perform(get("/api/m-area-action-weights?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(mAreaActionWeight.getId().intValue())))
            .andExpect(jsonPath("$.[*].areaType").value(hasItem(DEFAULT_AREA_TYPE)))
            .andExpect(jsonPath("$.[*].dribbleRate").value(hasItem(DEFAULT_DRIBBLE_RATE)))
            .andExpect(jsonPath("$.[*].passingRate").value(hasItem(DEFAULT_PASSING_RATE)))
            .andExpect(jsonPath("$.[*].onetwoRate").value(hasItem(DEFAULT_ONETWO_RATE)))
            .andExpect(jsonPath("$.[*].shootRate").value(hasItem(DEFAULT_SHOOT_RATE)))
            .andExpect(jsonPath("$.[*].volleyShootRate").value(hasItem(DEFAULT_VOLLEY_SHOOT_RATE)))
            .andExpect(jsonPath("$.[*].headingShootRate").value(hasItem(DEFAULT_HEADING_SHOOT_RATE)))
            .andExpect(jsonPath("$.[*].tackleRate").value(hasItem(DEFAULT_TACKLE_RATE)))
            .andExpect(jsonPath("$.[*].blockRate").value(hasItem(DEFAULT_BLOCK_RATE)))
            .andExpect(jsonPath("$.[*].passCutRate").value(hasItem(DEFAULT_PASS_CUT_RATE)))
            .andExpect(jsonPath("$.[*].clearRate").value(hasItem(DEFAULT_CLEAR_RATE)))
            .andExpect(jsonPath("$.[*].competeRate").value(hasItem(DEFAULT_COMPETE_RATE)))
            .andExpect(jsonPath("$.[*].trapRate").value(hasItem(DEFAULT_TRAP_RATE)));

        // Check, that the count call also returns 1
        restMAreaActionWeightMockMvc.perform(get("/api/m-area-action-weights/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultMAreaActionWeightShouldNotBeFound(String filter) throws Exception {
        restMAreaActionWeightMockMvc.perform(get("/api/m-area-action-weights?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restMAreaActionWeightMockMvc.perform(get("/api/m-area-action-weights/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("0"));
    }


    @Test
    @Transactional
    public void getNonExistingMAreaActionWeight() throws Exception {
        // Get the mAreaActionWeight
        restMAreaActionWeightMockMvc.perform(get("/api/m-area-action-weights/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateMAreaActionWeight() throws Exception {
        // Initialize the database
        mAreaActionWeightRepository.saveAndFlush(mAreaActionWeight);

        int databaseSizeBeforeUpdate = mAreaActionWeightRepository.findAll().size();

        // Update the mAreaActionWeight
        MAreaActionWeight updatedMAreaActionWeight = mAreaActionWeightRepository.findById(mAreaActionWeight.getId()).get();
        // Disconnect from session so that the updates on updatedMAreaActionWeight are not directly saved in db
        em.detach(updatedMAreaActionWeight);
        updatedMAreaActionWeight
            .areaType(UPDATED_AREA_TYPE)
            .dribbleRate(UPDATED_DRIBBLE_RATE)
            .passingRate(UPDATED_PASSING_RATE)
            .onetwoRate(UPDATED_ONETWO_RATE)
            .shootRate(UPDATED_SHOOT_RATE)
            .volleyShootRate(UPDATED_VOLLEY_SHOOT_RATE)
            .headingShootRate(UPDATED_HEADING_SHOOT_RATE)
            .tackleRate(UPDATED_TACKLE_RATE)
            .blockRate(UPDATED_BLOCK_RATE)
            .passCutRate(UPDATED_PASS_CUT_RATE)
            .clearRate(UPDATED_CLEAR_RATE)
            .competeRate(UPDATED_COMPETE_RATE)
            .trapRate(UPDATED_TRAP_RATE);
        MAreaActionWeightDTO mAreaActionWeightDTO = mAreaActionWeightMapper.toDto(updatedMAreaActionWeight);

        restMAreaActionWeightMockMvc.perform(put("/api/m-area-action-weights")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mAreaActionWeightDTO)))
            .andExpect(status().isOk());

        // Validate the MAreaActionWeight in the database
        List<MAreaActionWeight> mAreaActionWeightList = mAreaActionWeightRepository.findAll();
        assertThat(mAreaActionWeightList).hasSize(databaseSizeBeforeUpdate);
        MAreaActionWeight testMAreaActionWeight = mAreaActionWeightList.get(mAreaActionWeightList.size() - 1);
        assertThat(testMAreaActionWeight.getAreaType()).isEqualTo(UPDATED_AREA_TYPE);
        assertThat(testMAreaActionWeight.getDribbleRate()).isEqualTo(UPDATED_DRIBBLE_RATE);
        assertThat(testMAreaActionWeight.getPassingRate()).isEqualTo(UPDATED_PASSING_RATE);
        assertThat(testMAreaActionWeight.getOnetwoRate()).isEqualTo(UPDATED_ONETWO_RATE);
        assertThat(testMAreaActionWeight.getShootRate()).isEqualTo(UPDATED_SHOOT_RATE);
        assertThat(testMAreaActionWeight.getVolleyShootRate()).isEqualTo(UPDATED_VOLLEY_SHOOT_RATE);
        assertThat(testMAreaActionWeight.getHeadingShootRate()).isEqualTo(UPDATED_HEADING_SHOOT_RATE);
        assertThat(testMAreaActionWeight.getTackleRate()).isEqualTo(UPDATED_TACKLE_RATE);
        assertThat(testMAreaActionWeight.getBlockRate()).isEqualTo(UPDATED_BLOCK_RATE);
        assertThat(testMAreaActionWeight.getPassCutRate()).isEqualTo(UPDATED_PASS_CUT_RATE);
        assertThat(testMAreaActionWeight.getClearRate()).isEqualTo(UPDATED_CLEAR_RATE);
        assertThat(testMAreaActionWeight.getCompeteRate()).isEqualTo(UPDATED_COMPETE_RATE);
        assertThat(testMAreaActionWeight.getTrapRate()).isEqualTo(UPDATED_TRAP_RATE);
    }

    @Test
    @Transactional
    public void updateNonExistingMAreaActionWeight() throws Exception {
        int databaseSizeBeforeUpdate = mAreaActionWeightRepository.findAll().size();

        // Create the MAreaActionWeight
        MAreaActionWeightDTO mAreaActionWeightDTO = mAreaActionWeightMapper.toDto(mAreaActionWeight);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restMAreaActionWeightMockMvc.perform(put("/api/m-area-action-weights")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mAreaActionWeightDTO)))
            .andExpect(status().isBadRequest());

        // Validate the MAreaActionWeight in the database
        List<MAreaActionWeight> mAreaActionWeightList = mAreaActionWeightRepository.findAll();
        assertThat(mAreaActionWeightList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteMAreaActionWeight() throws Exception {
        // Initialize the database
        mAreaActionWeightRepository.saveAndFlush(mAreaActionWeight);

        int databaseSizeBeforeDelete = mAreaActionWeightRepository.findAll().size();

        // Delete the mAreaActionWeight
        restMAreaActionWeightMockMvc.perform(delete("/api/m-area-action-weights/{id}", mAreaActionWeight.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database is empty
        List<MAreaActionWeight> mAreaActionWeightList = mAreaActionWeightRepository.findAll();
        assertThat(mAreaActionWeightList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(MAreaActionWeight.class);
        MAreaActionWeight mAreaActionWeight1 = new MAreaActionWeight();
        mAreaActionWeight1.setId(1L);
        MAreaActionWeight mAreaActionWeight2 = new MAreaActionWeight();
        mAreaActionWeight2.setId(mAreaActionWeight1.getId());
        assertThat(mAreaActionWeight1).isEqualTo(mAreaActionWeight2);
        mAreaActionWeight2.setId(2L);
        assertThat(mAreaActionWeight1).isNotEqualTo(mAreaActionWeight2);
        mAreaActionWeight1.setId(null);
        assertThat(mAreaActionWeight1).isNotEqualTo(mAreaActionWeight2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(MAreaActionWeightDTO.class);
        MAreaActionWeightDTO mAreaActionWeightDTO1 = new MAreaActionWeightDTO();
        mAreaActionWeightDTO1.setId(1L);
        MAreaActionWeightDTO mAreaActionWeightDTO2 = new MAreaActionWeightDTO();
        assertThat(mAreaActionWeightDTO1).isNotEqualTo(mAreaActionWeightDTO2);
        mAreaActionWeightDTO2.setId(mAreaActionWeightDTO1.getId());
        assertThat(mAreaActionWeightDTO1).isEqualTo(mAreaActionWeightDTO2);
        mAreaActionWeightDTO2.setId(2L);
        assertThat(mAreaActionWeightDTO1).isNotEqualTo(mAreaActionWeightDTO2);
        mAreaActionWeightDTO1.setId(null);
        assertThat(mAreaActionWeightDTO1).isNotEqualTo(mAreaActionWeightDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(mAreaActionWeightMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(mAreaActionWeightMapper.fromId(null)).isNull();
    }
}
