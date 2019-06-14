package io.shm.tsubasa.web.rest;

import io.shm.tsubasa.TsubasaApp;
import io.shm.tsubasa.domain.MTsubasaPoint;
import io.shm.tsubasa.repository.MTsubasaPointRepository;
import io.shm.tsubasa.service.MTsubasaPointService;
import io.shm.tsubasa.service.dto.MTsubasaPointDTO;
import io.shm.tsubasa.service.mapper.MTsubasaPointMapper;
import io.shm.tsubasa.web.rest.errors.ExceptionTranslator;
import io.shm.tsubasa.service.dto.MTsubasaPointCriteria;
import io.shm.tsubasa.service.MTsubasaPointQueryService;

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
 * Integration tests for the {@Link MTsubasaPointResource} REST controller.
 */
@SpringBootTest(classes = TsubasaApp.class)
public class MTsubasaPointResourceIT {

    private static final Integer DEFAULT_MATCH_TYPE = 1;
    private static final Integer UPDATED_MATCH_TYPE = 2;

    private static final Integer DEFAULT_POINT_TYPE = 1;
    private static final Integer UPDATED_POINT_TYPE = 2;

    private static final Integer DEFAULT_CALC_TYPE = 1;
    private static final Integer UPDATED_CALC_TYPE = 2;

    private static final Integer DEFAULT_A_VALUE = 1;
    private static final Integer UPDATED_A_VALUE = 2;

    private static final Integer DEFAULT_B_VALUE = 1;
    private static final Integer UPDATED_B_VALUE = 2;

    private static final Integer DEFAULT_ORDER_NUM = 1;
    private static final Integer UPDATED_ORDER_NUM = 2;

    @Autowired
    private MTsubasaPointRepository mTsubasaPointRepository;

    @Autowired
    private MTsubasaPointMapper mTsubasaPointMapper;

    @Autowired
    private MTsubasaPointService mTsubasaPointService;

    @Autowired
    private MTsubasaPointQueryService mTsubasaPointQueryService;

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

    private MockMvc restMTsubasaPointMockMvc;

    private MTsubasaPoint mTsubasaPoint;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final MTsubasaPointResource mTsubasaPointResource = new MTsubasaPointResource(mTsubasaPointService, mTsubasaPointQueryService);
        this.restMTsubasaPointMockMvc = MockMvcBuilders.standaloneSetup(mTsubasaPointResource)
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
    public static MTsubasaPoint createEntity(EntityManager em) {
        MTsubasaPoint mTsubasaPoint = new MTsubasaPoint()
            .matchType(DEFAULT_MATCH_TYPE)
            .pointType(DEFAULT_POINT_TYPE)
            .calcType(DEFAULT_CALC_TYPE)
            .aValue(DEFAULT_A_VALUE)
            .bValue(DEFAULT_B_VALUE)
            .orderNum(DEFAULT_ORDER_NUM);
        return mTsubasaPoint;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static MTsubasaPoint createUpdatedEntity(EntityManager em) {
        MTsubasaPoint mTsubasaPoint = new MTsubasaPoint()
            .matchType(UPDATED_MATCH_TYPE)
            .pointType(UPDATED_POINT_TYPE)
            .calcType(UPDATED_CALC_TYPE)
            .aValue(UPDATED_A_VALUE)
            .bValue(UPDATED_B_VALUE)
            .orderNum(UPDATED_ORDER_NUM);
        return mTsubasaPoint;
    }

    @BeforeEach
    public void initTest() {
        mTsubasaPoint = createEntity(em);
    }

    @Test
    @Transactional
    public void createMTsubasaPoint() throws Exception {
        int databaseSizeBeforeCreate = mTsubasaPointRepository.findAll().size();

        // Create the MTsubasaPoint
        MTsubasaPointDTO mTsubasaPointDTO = mTsubasaPointMapper.toDto(mTsubasaPoint);
        restMTsubasaPointMockMvc.perform(post("/api/m-tsubasa-points")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mTsubasaPointDTO)))
            .andExpect(status().isCreated());

        // Validate the MTsubasaPoint in the database
        List<MTsubasaPoint> mTsubasaPointList = mTsubasaPointRepository.findAll();
        assertThat(mTsubasaPointList).hasSize(databaseSizeBeforeCreate + 1);
        MTsubasaPoint testMTsubasaPoint = mTsubasaPointList.get(mTsubasaPointList.size() - 1);
        assertThat(testMTsubasaPoint.getMatchType()).isEqualTo(DEFAULT_MATCH_TYPE);
        assertThat(testMTsubasaPoint.getPointType()).isEqualTo(DEFAULT_POINT_TYPE);
        assertThat(testMTsubasaPoint.getCalcType()).isEqualTo(DEFAULT_CALC_TYPE);
        assertThat(testMTsubasaPoint.getaValue()).isEqualTo(DEFAULT_A_VALUE);
        assertThat(testMTsubasaPoint.getbValue()).isEqualTo(DEFAULT_B_VALUE);
        assertThat(testMTsubasaPoint.getOrderNum()).isEqualTo(DEFAULT_ORDER_NUM);
    }

    @Test
    @Transactional
    public void createMTsubasaPointWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = mTsubasaPointRepository.findAll().size();

        // Create the MTsubasaPoint with an existing ID
        mTsubasaPoint.setId(1L);
        MTsubasaPointDTO mTsubasaPointDTO = mTsubasaPointMapper.toDto(mTsubasaPoint);

        // An entity with an existing ID cannot be created, so this API call must fail
        restMTsubasaPointMockMvc.perform(post("/api/m-tsubasa-points")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mTsubasaPointDTO)))
            .andExpect(status().isBadRequest());

        // Validate the MTsubasaPoint in the database
        List<MTsubasaPoint> mTsubasaPointList = mTsubasaPointRepository.findAll();
        assertThat(mTsubasaPointList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkMatchTypeIsRequired() throws Exception {
        int databaseSizeBeforeTest = mTsubasaPointRepository.findAll().size();
        // set the field null
        mTsubasaPoint.setMatchType(null);

        // Create the MTsubasaPoint, which fails.
        MTsubasaPointDTO mTsubasaPointDTO = mTsubasaPointMapper.toDto(mTsubasaPoint);

        restMTsubasaPointMockMvc.perform(post("/api/m-tsubasa-points")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mTsubasaPointDTO)))
            .andExpect(status().isBadRequest());

        List<MTsubasaPoint> mTsubasaPointList = mTsubasaPointRepository.findAll();
        assertThat(mTsubasaPointList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkPointTypeIsRequired() throws Exception {
        int databaseSizeBeforeTest = mTsubasaPointRepository.findAll().size();
        // set the field null
        mTsubasaPoint.setPointType(null);

        // Create the MTsubasaPoint, which fails.
        MTsubasaPointDTO mTsubasaPointDTO = mTsubasaPointMapper.toDto(mTsubasaPoint);

        restMTsubasaPointMockMvc.perform(post("/api/m-tsubasa-points")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mTsubasaPointDTO)))
            .andExpect(status().isBadRequest());

        List<MTsubasaPoint> mTsubasaPointList = mTsubasaPointRepository.findAll();
        assertThat(mTsubasaPointList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkCalcTypeIsRequired() throws Exception {
        int databaseSizeBeforeTest = mTsubasaPointRepository.findAll().size();
        // set the field null
        mTsubasaPoint.setCalcType(null);

        // Create the MTsubasaPoint, which fails.
        MTsubasaPointDTO mTsubasaPointDTO = mTsubasaPointMapper.toDto(mTsubasaPoint);

        restMTsubasaPointMockMvc.perform(post("/api/m-tsubasa-points")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mTsubasaPointDTO)))
            .andExpect(status().isBadRequest());

        List<MTsubasaPoint> mTsubasaPointList = mTsubasaPointRepository.findAll();
        assertThat(mTsubasaPointList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkaValueIsRequired() throws Exception {
        int databaseSizeBeforeTest = mTsubasaPointRepository.findAll().size();
        // set the field null
        mTsubasaPoint.setaValue(null);

        // Create the MTsubasaPoint, which fails.
        MTsubasaPointDTO mTsubasaPointDTO = mTsubasaPointMapper.toDto(mTsubasaPoint);

        restMTsubasaPointMockMvc.perform(post("/api/m-tsubasa-points")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mTsubasaPointDTO)))
            .andExpect(status().isBadRequest());

        List<MTsubasaPoint> mTsubasaPointList = mTsubasaPointRepository.findAll();
        assertThat(mTsubasaPointList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkbValueIsRequired() throws Exception {
        int databaseSizeBeforeTest = mTsubasaPointRepository.findAll().size();
        // set the field null
        mTsubasaPoint.setbValue(null);

        // Create the MTsubasaPoint, which fails.
        MTsubasaPointDTO mTsubasaPointDTO = mTsubasaPointMapper.toDto(mTsubasaPoint);

        restMTsubasaPointMockMvc.perform(post("/api/m-tsubasa-points")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mTsubasaPointDTO)))
            .andExpect(status().isBadRequest());

        List<MTsubasaPoint> mTsubasaPointList = mTsubasaPointRepository.findAll();
        assertThat(mTsubasaPointList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkOrderNumIsRequired() throws Exception {
        int databaseSizeBeforeTest = mTsubasaPointRepository.findAll().size();
        // set the field null
        mTsubasaPoint.setOrderNum(null);

        // Create the MTsubasaPoint, which fails.
        MTsubasaPointDTO mTsubasaPointDTO = mTsubasaPointMapper.toDto(mTsubasaPoint);

        restMTsubasaPointMockMvc.perform(post("/api/m-tsubasa-points")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mTsubasaPointDTO)))
            .andExpect(status().isBadRequest());

        List<MTsubasaPoint> mTsubasaPointList = mTsubasaPointRepository.findAll();
        assertThat(mTsubasaPointList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllMTsubasaPoints() throws Exception {
        // Initialize the database
        mTsubasaPointRepository.saveAndFlush(mTsubasaPoint);

        // Get all the mTsubasaPointList
        restMTsubasaPointMockMvc.perform(get("/api/m-tsubasa-points?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(mTsubasaPoint.getId().intValue())))
            .andExpect(jsonPath("$.[*].matchType").value(hasItem(DEFAULT_MATCH_TYPE)))
            .andExpect(jsonPath("$.[*].pointType").value(hasItem(DEFAULT_POINT_TYPE)))
            .andExpect(jsonPath("$.[*].calcType").value(hasItem(DEFAULT_CALC_TYPE)))
            .andExpect(jsonPath("$.[*].aValue").value(hasItem(DEFAULT_A_VALUE)))
            .andExpect(jsonPath("$.[*].bValue").value(hasItem(DEFAULT_B_VALUE)))
            .andExpect(jsonPath("$.[*].orderNum").value(hasItem(DEFAULT_ORDER_NUM)));
    }
    
    @Test
    @Transactional
    public void getMTsubasaPoint() throws Exception {
        // Initialize the database
        mTsubasaPointRepository.saveAndFlush(mTsubasaPoint);

        // Get the mTsubasaPoint
        restMTsubasaPointMockMvc.perform(get("/api/m-tsubasa-points/{id}", mTsubasaPoint.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(mTsubasaPoint.getId().intValue()))
            .andExpect(jsonPath("$.matchType").value(DEFAULT_MATCH_TYPE))
            .andExpect(jsonPath("$.pointType").value(DEFAULT_POINT_TYPE))
            .andExpect(jsonPath("$.calcType").value(DEFAULT_CALC_TYPE))
            .andExpect(jsonPath("$.aValue").value(DEFAULT_A_VALUE))
            .andExpect(jsonPath("$.bValue").value(DEFAULT_B_VALUE))
            .andExpect(jsonPath("$.orderNum").value(DEFAULT_ORDER_NUM));
    }

    @Test
    @Transactional
    public void getAllMTsubasaPointsByMatchTypeIsEqualToSomething() throws Exception {
        // Initialize the database
        mTsubasaPointRepository.saveAndFlush(mTsubasaPoint);

        // Get all the mTsubasaPointList where matchType equals to DEFAULT_MATCH_TYPE
        defaultMTsubasaPointShouldBeFound("matchType.equals=" + DEFAULT_MATCH_TYPE);

        // Get all the mTsubasaPointList where matchType equals to UPDATED_MATCH_TYPE
        defaultMTsubasaPointShouldNotBeFound("matchType.equals=" + UPDATED_MATCH_TYPE);
    }

    @Test
    @Transactional
    public void getAllMTsubasaPointsByMatchTypeIsInShouldWork() throws Exception {
        // Initialize the database
        mTsubasaPointRepository.saveAndFlush(mTsubasaPoint);

        // Get all the mTsubasaPointList where matchType in DEFAULT_MATCH_TYPE or UPDATED_MATCH_TYPE
        defaultMTsubasaPointShouldBeFound("matchType.in=" + DEFAULT_MATCH_TYPE + "," + UPDATED_MATCH_TYPE);

        // Get all the mTsubasaPointList where matchType equals to UPDATED_MATCH_TYPE
        defaultMTsubasaPointShouldNotBeFound("matchType.in=" + UPDATED_MATCH_TYPE);
    }

    @Test
    @Transactional
    public void getAllMTsubasaPointsByMatchTypeIsNullOrNotNull() throws Exception {
        // Initialize the database
        mTsubasaPointRepository.saveAndFlush(mTsubasaPoint);

        // Get all the mTsubasaPointList where matchType is not null
        defaultMTsubasaPointShouldBeFound("matchType.specified=true");

        // Get all the mTsubasaPointList where matchType is null
        defaultMTsubasaPointShouldNotBeFound("matchType.specified=false");
    }

    @Test
    @Transactional
    public void getAllMTsubasaPointsByMatchTypeIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mTsubasaPointRepository.saveAndFlush(mTsubasaPoint);

        // Get all the mTsubasaPointList where matchType greater than or equals to DEFAULT_MATCH_TYPE
        defaultMTsubasaPointShouldBeFound("matchType.greaterOrEqualThan=" + DEFAULT_MATCH_TYPE);

        // Get all the mTsubasaPointList where matchType greater than or equals to UPDATED_MATCH_TYPE
        defaultMTsubasaPointShouldNotBeFound("matchType.greaterOrEqualThan=" + UPDATED_MATCH_TYPE);
    }

    @Test
    @Transactional
    public void getAllMTsubasaPointsByMatchTypeIsLessThanSomething() throws Exception {
        // Initialize the database
        mTsubasaPointRepository.saveAndFlush(mTsubasaPoint);

        // Get all the mTsubasaPointList where matchType less than or equals to DEFAULT_MATCH_TYPE
        defaultMTsubasaPointShouldNotBeFound("matchType.lessThan=" + DEFAULT_MATCH_TYPE);

        // Get all the mTsubasaPointList where matchType less than or equals to UPDATED_MATCH_TYPE
        defaultMTsubasaPointShouldBeFound("matchType.lessThan=" + UPDATED_MATCH_TYPE);
    }


    @Test
    @Transactional
    public void getAllMTsubasaPointsByPointTypeIsEqualToSomething() throws Exception {
        // Initialize the database
        mTsubasaPointRepository.saveAndFlush(mTsubasaPoint);

        // Get all the mTsubasaPointList where pointType equals to DEFAULT_POINT_TYPE
        defaultMTsubasaPointShouldBeFound("pointType.equals=" + DEFAULT_POINT_TYPE);

        // Get all the mTsubasaPointList where pointType equals to UPDATED_POINT_TYPE
        defaultMTsubasaPointShouldNotBeFound("pointType.equals=" + UPDATED_POINT_TYPE);
    }

    @Test
    @Transactional
    public void getAllMTsubasaPointsByPointTypeIsInShouldWork() throws Exception {
        // Initialize the database
        mTsubasaPointRepository.saveAndFlush(mTsubasaPoint);

        // Get all the mTsubasaPointList where pointType in DEFAULT_POINT_TYPE or UPDATED_POINT_TYPE
        defaultMTsubasaPointShouldBeFound("pointType.in=" + DEFAULT_POINT_TYPE + "," + UPDATED_POINT_TYPE);

        // Get all the mTsubasaPointList where pointType equals to UPDATED_POINT_TYPE
        defaultMTsubasaPointShouldNotBeFound("pointType.in=" + UPDATED_POINT_TYPE);
    }

    @Test
    @Transactional
    public void getAllMTsubasaPointsByPointTypeIsNullOrNotNull() throws Exception {
        // Initialize the database
        mTsubasaPointRepository.saveAndFlush(mTsubasaPoint);

        // Get all the mTsubasaPointList where pointType is not null
        defaultMTsubasaPointShouldBeFound("pointType.specified=true");

        // Get all the mTsubasaPointList where pointType is null
        defaultMTsubasaPointShouldNotBeFound("pointType.specified=false");
    }

    @Test
    @Transactional
    public void getAllMTsubasaPointsByPointTypeIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mTsubasaPointRepository.saveAndFlush(mTsubasaPoint);

        // Get all the mTsubasaPointList where pointType greater than or equals to DEFAULT_POINT_TYPE
        defaultMTsubasaPointShouldBeFound("pointType.greaterOrEqualThan=" + DEFAULT_POINT_TYPE);

        // Get all the mTsubasaPointList where pointType greater than or equals to UPDATED_POINT_TYPE
        defaultMTsubasaPointShouldNotBeFound("pointType.greaterOrEqualThan=" + UPDATED_POINT_TYPE);
    }

    @Test
    @Transactional
    public void getAllMTsubasaPointsByPointTypeIsLessThanSomething() throws Exception {
        // Initialize the database
        mTsubasaPointRepository.saveAndFlush(mTsubasaPoint);

        // Get all the mTsubasaPointList where pointType less than or equals to DEFAULT_POINT_TYPE
        defaultMTsubasaPointShouldNotBeFound("pointType.lessThan=" + DEFAULT_POINT_TYPE);

        // Get all the mTsubasaPointList where pointType less than or equals to UPDATED_POINT_TYPE
        defaultMTsubasaPointShouldBeFound("pointType.lessThan=" + UPDATED_POINT_TYPE);
    }


    @Test
    @Transactional
    public void getAllMTsubasaPointsByCalcTypeIsEqualToSomething() throws Exception {
        // Initialize the database
        mTsubasaPointRepository.saveAndFlush(mTsubasaPoint);

        // Get all the mTsubasaPointList where calcType equals to DEFAULT_CALC_TYPE
        defaultMTsubasaPointShouldBeFound("calcType.equals=" + DEFAULT_CALC_TYPE);

        // Get all the mTsubasaPointList where calcType equals to UPDATED_CALC_TYPE
        defaultMTsubasaPointShouldNotBeFound("calcType.equals=" + UPDATED_CALC_TYPE);
    }

    @Test
    @Transactional
    public void getAllMTsubasaPointsByCalcTypeIsInShouldWork() throws Exception {
        // Initialize the database
        mTsubasaPointRepository.saveAndFlush(mTsubasaPoint);

        // Get all the mTsubasaPointList where calcType in DEFAULT_CALC_TYPE or UPDATED_CALC_TYPE
        defaultMTsubasaPointShouldBeFound("calcType.in=" + DEFAULT_CALC_TYPE + "," + UPDATED_CALC_TYPE);

        // Get all the mTsubasaPointList where calcType equals to UPDATED_CALC_TYPE
        defaultMTsubasaPointShouldNotBeFound("calcType.in=" + UPDATED_CALC_TYPE);
    }

    @Test
    @Transactional
    public void getAllMTsubasaPointsByCalcTypeIsNullOrNotNull() throws Exception {
        // Initialize the database
        mTsubasaPointRepository.saveAndFlush(mTsubasaPoint);

        // Get all the mTsubasaPointList where calcType is not null
        defaultMTsubasaPointShouldBeFound("calcType.specified=true");

        // Get all the mTsubasaPointList where calcType is null
        defaultMTsubasaPointShouldNotBeFound("calcType.specified=false");
    }

    @Test
    @Transactional
    public void getAllMTsubasaPointsByCalcTypeIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mTsubasaPointRepository.saveAndFlush(mTsubasaPoint);

        // Get all the mTsubasaPointList where calcType greater than or equals to DEFAULT_CALC_TYPE
        defaultMTsubasaPointShouldBeFound("calcType.greaterOrEqualThan=" + DEFAULT_CALC_TYPE);

        // Get all the mTsubasaPointList where calcType greater than or equals to UPDATED_CALC_TYPE
        defaultMTsubasaPointShouldNotBeFound("calcType.greaterOrEqualThan=" + UPDATED_CALC_TYPE);
    }

    @Test
    @Transactional
    public void getAllMTsubasaPointsByCalcTypeIsLessThanSomething() throws Exception {
        // Initialize the database
        mTsubasaPointRepository.saveAndFlush(mTsubasaPoint);

        // Get all the mTsubasaPointList where calcType less than or equals to DEFAULT_CALC_TYPE
        defaultMTsubasaPointShouldNotBeFound("calcType.lessThan=" + DEFAULT_CALC_TYPE);

        // Get all the mTsubasaPointList where calcType less than or equals to UPDATED_CALC_TYPE
        defaultMTsubasaPointShouldBeFound("calcType.lessThan=" + UPDATED_CALC_TYPE);
    }


    @Test
    @Transactional
    public void getAllMTsubasaPointsByaValueIsEqualToSomething() throws Exception {
        // Initialize the database
        mTsubasaPointRepository.saveAndFlush(mTsubasaPoint);

        // Get all the mTsubasaPointList where aValue equals to DEFAULT_A_VALUE
        defaultMTsubasaPointShouldBeFound("aValue.equals=" + DEFAULT_A_VALUE);

        // Get all the mTsubasaPointList where aValue equals to UPDATED_A_VALUE
        defaultMTsubasaPointShouldNotBeFound("aValue.equals=" + UPDATED_A_VALUE);
    }

    @Test
    @Transactional
    public void getAllMTsubasaPointsByaValueIsInShouldWork() throws Exception {
        // Initialize the database
        mTsubasaPointRepository.saveAndFlush(mTsubasaPoint);

        // Get all the mTsubasaPointList where aValue in DEFAULT_A_VALUE or UPDATED_A_VALUE
        defaultMTsubasaPointShouldBeFound("aValue.in=" + DEFAULT_A_VALUE + "," + UPDATED_A_VALUE);

        // Get all the mTsubasaPointList where aValue equals to UPDATED_A_VALUE
        defaultMTsubasaPointShouldNotBeFound("aValue.in=" + UPDATED_A_VALUE);
    }

    @Test
    @Transactional
    public void getAllMTsubasaPointsByaValueIsNullOrNotNull() throws Exception {
        // Initialize the database
        mTsubasaPointRepository.saveAndFlush(mTsubasaPoint);

        // Get all the mTsubasaPointList where aValue is not null
        defaultMTsubasaPointShouldBeFound("aValue.specified=true");

        // Get all the mTsubasaPointList where aValue is null
        defaultMTsubasaPointShouldNotBeFound("aValue.specified=false");
    }

    @Test
    @Transactional
    public void getAllMTsubasaPointsByaValueIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mTsubasaPointRepository.saveAndFlush(mTsubasaPoint);

        // Get all the mTsubasaPointList where aValue greater than or equals to DEFAULT_A_VALUE
        defaultMTsubasaPointShouldBeFound("aValue.greaterOrEqualThan=" + DEFAULT_A_VALUE);

        // Get all the mTsubasaPointList where aValue greater than or equals to UPDATED_A_VALUE
        defaultMTsubasaPointShouldNotBeFound("aValue.greaterOrEqualThan=" + UPDATED_A_VALUE);
    }

    @Test
    @Transactional
    public void getAllMTsubasaPointsByaValueIsLessThanSomething() throws Exception {
        // Initialize the database
        mTsubasaPointRepository.saveAndFlush(mTsubasaPoint);

        // Get all the mTsubasaPointList where aValue less than or equals to DEFAULT_A_VALUE
        defaultMTsubasaPointShouldNotBeFound("aValue.lessThan=" + DEFAULT_A_VALUE);

        // Get all the mTsubasaPointList where aValue less than or equals to UPDATED_A_VALUE
        defaultMTsubasaPointShouldBeFound("aValue.lessThan=" + UPDATED_A_VALUE);
    }


    @Test
    @Transactional
    public void getAllMTsubasaPointsBybValueIsEqualToSomething() throws Exception {
        // Initialize the database
        mTsubasaPointRepository.saveAndFlush(mTsubasaPoint);

        // Get all the mTsubasaPointList where bValue equals to DEFAULT_B_VALUE
        defaultMTsubasaPointShouldBeFound("bValue.equals=" + DEFAULT_B_VALUE);

        // Get all the mTsubasaPointList where bValue equals to UPDATED_B_VALUE
        defaultMTsubasaPointShouldNotBeFound("bValue.equals=" + UPDATED_B_VALUE);
    }

    @Test
    @Transactional
    public void getAllMTsubasaPointsBybValueIsInShouldWork() throws Exception {
        // Initialize the database
        mTsubasaPointRepository.saveAndFlush(mTsubasaPoint);

        // Get all the mTsubasaPointList where bValue in DEFAULT_B_VALUE or UPDATED_B_VALUE
        defaultMTsubasaPointShouldBeFound("bValue.in=" + DEFAULT_B_VALUE + "," + UPDATED_B_VALUE);

        // Get all the mTsubasaPointList where bValue equals to UPDATED_B_VALUE
        defaultMTsubasaPointShouldNotBeFound("bValue.in=" + UPDATED_B_VALUE);
    }

    @Test
    @Transactional
    public void getAllMTsubasaPointsBybValueIsNullOrNotNull() throws Exception {
        // Initialize the database
        mTsubasaPointRepository.saveAndFlush(mTsubasaPoint);

        // Get all the mTsubasaPointList where bValue is not null
        defaultMTsubasaPointShouldBeFound("bValue.specified=true");

        // Get all the mTsubasaPointList where bValue is null
        defaultMTsubasaPointShouldNotBeFound("bValue.specified=false");
    }

    @Test
    @Transactional
    public void getAllMTsubasaPointsBybValueIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mTsubasaPointRepository.saveAndFlush(mTsubasaPoint);

        // Get all the mTsubasaPointList where bValue greater than or equals to DEFAULT_B_VALUE
        defaultMTsubasaPointShouldBeFound("bValue.greaterOrEqualThan=" + DEFAULT_B_VALUE);

        // Get all the mTsubasaPointList where bValue greater than or equals to UPDATED_B_VALUE
        defaultMTsubasaPointShouldNotBeFound("bValue.greaterOrEqualThan=" + UPDATED_B_VALUE);
    }

    @Test
    @Transactional
    public void getAllMTsubasaPointsBybValueIsLessThanSomething() throws Exception {
        // Initialize the database
        mTsubasaPointRepository.saveAndFlush(mTsubasaPoint);

        // Get all the mTsubasaPointList where bValue less than or equals to DEFAULT_B_VALUE
        defaultMTsubasaPointShouldNotBeFound("bValue.lessThan=" + DEFAULT_B_VALUE);

        // Get all the mTsubasaPointList where bValue less than or equals to UPDATED_B_VALUE
        defaultMTsubasaPointShouldBeFound("bValue.lessThan=" + UPDATED_B_VALUE);
    }


    @Test
    @Transactional
    public void getAllMTsubasaPointsByOrderNumIsEqualToSomething() throws Exception {
        // Initialize the database
        mTsubasaPointRepository.saveAndFlush(mTsubasaPoint);

        // Get all the mTsubasaPointList where orderNum equals to DEFAULT_ORDER_NUM
        defaultMTsubasaPointShouldBeFound("orderNum.equals=" + DEFAULT_ORDER_NUM);

        // Get all the mTsubasaPointList where orderNum equals to UPDATED_ORDER_NUM
        defaultMTsubasaPointShouldNotBeFound("orderNum.equals=" + UPDATED_ORDER_NUM);
    }

    @Test
    @Transactional
    public void getAllMTsubasaPointsByOrderNumIsInShouldWork() throws Exception {
        // Initialize the database
        mTsubasaPointRepository.saveAndFlush(mTsubasaPoint);

        // Get all the mTsubasaPointList where orderNum in DEFAULT_ORDER_NUM or UPDATED_ORDER_NUM
        defaultMTsubasaPointShouldBeFound("orderNum.in=" + DEFAULT_ORDER_NUM + "," + UPDATED_ORDER_NUM);

        // Get all the mTsubasaPointList where orderNum equals to UPDATED_ORDER_NUM
        defaultMTsubasaPointShouldNotBeFound("orderNum.in=" + UPDATED_ORDER_NUM);
    }

    @Test
    @Transactional
    public void getAllMTsubasaPointsByOrderNumIsNullOrNotNull() throws Exception {
        // Initialize the database
        mTsubasaPointRepository.saveAndFlush(mTsubasaPoint);

        // Get all the mTsubasaPointList where orderNum is not null
        defaultMTsubasaPointShouldBeFound("orderNum.specified=true");

        // Get all the mTsubasaPointList where orderNum is null
        defaultMTsubasaPointShouldNotBeFound("orderNum.specified=false");
    }

    @Test
    @Transactional
    public void getAllMTsubasaPointsByOrderNumIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mTsubasaPointRepository.saveAndFlush(mTsubasaPoint);

        // Get all the mTsubasaPointList where orderNum greater than or equals to DEFAULT_ORDER_NUM
        defaultMTsubasaPointShouldBeFound("orderNum.greaterOrEqualThan=" + DEFAULT_ORDER_NUM);

        // Get all the mTsubasaPointList where orderNum greater than or equals to UPDATED_ORDER_NUM
        defaultMTsubasaPointShouldNotBeFound("orderNum.greaterOrEqualThan=" + UPDATED_ORDER_NUM);
    }

    @Test
    @Transactional
    public void getAllMTsubasaPointsByOrderNumIsLessThanSomething() throws Exception {
        // Initialize the database
        mTsubasaPointRepository.saveAndFlush(mTsubasaPoint);

        // Get all the mTsubasaPointList where orderNum less than or equals to DEFAULT_ORDER_NUM
        defaultMTsubasaPointShouldNotBeFound("orderNum.lessThan=" + DEFAULT_ORDER_NUM);

        // Get all the mTsubasaPointList where orderNum less than or equals to UPDATED_ORDER_NUM
        defaultMTsubasaPointShouldBeFound("orderNum.lessThan=" + UPDATED_ORDER_NUM);
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultMTsubasaPointShouldBeFound(String filter) throws Exception {
        restMTsubasaPointMockMvc.perform(get("/api/m-tsubasa-points?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(mTsubasaPoint.getId().intValue())))
            .andExpect(jsonPath("$.[*].matchType").value(hasItem(DEFAULT_MATCH_TYPE)))
            .andExpect(jsonPath("$.[*].pointType").value(hasItem(DEFAULT_POINT_TYPE)))
            .andExpect(jsonPath("$.[*].calcType").value(hasItem(DEFAULT_CALC_TYPE)))
            .andExpect(jsonPath("$.[*].aValue").value(hasItem(DEFAULT_A_VALUE)))
            .andExpect(jsonPath("$.[*].bValue").value(hasItem(DEFAULT_B_VALUE)))
            .andExpect(jsonPath("$.[*].orderNum").value(hasItem(DEFAULT_ORDER_NUM)));

        // Check, that the count call also returns 1
        restMTsubasaPointMockMvc.perform(get("/api/m-tsubasa-points/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultMTsubasaPointShouldNotBeFound(String filter) throws Exception {
        restMTsubasaPointMockMvc.perform(get("/api/m-tsubasa-points?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restMTsubasaPointMockMvc.perform(get("/api/m-tsubasa-points/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("0"));
    }


    @Test
    @Transactional
    public void getNonExistingMTsubasaPoint() throws Exception {
        // Get the mTsubasaPoint
        restMTsubasaPointMockMvc.perform(get("/api/m-tsubasa-points/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateMTsubasaPoint() throws Exception {
        // Initialize the database
        mTsubasaPointRepository.saveAndFlush(mTsubasaPoint);

        int databaseSizeBeforeUpdate = mTsubasaPointRepository.findAll().size();

        // Update the mTsubasaPoint
        MTsubasaPoint updatedMTsubasaPoint = mTsubasaPointRepository.findById(mTsubasaPoint.getId()).get();
        // Disconnect from session so that the updates on updatedMTsubasaPoint are not directly saved in db
        em.detach(updatedMTsubasaPoint);
        updatedMTsubasaPoint
            .matchType(UPDATED_MATCH_TYPE)
            .pointType(UPDATED_POINT_TYPE)
            .calcType(UPDATED_CALC_TYPE)
            .aValue(UPDATED_A_VALUE)
            .bValue(UPDATED_B_VALUE)
            .orderNum(UPDATED_ORDER_NUM);
        MTsubasaPointDTO mTsubasaPointDTO = mTsubasaPointMapper.toDto(updatedMTsubasaPoint);

        restMTsubasaPointMockMvc.perform(put("/api/m-tsubasa-points")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mTsubasaPointDTO)))
            .andExpect(status().isOk());

        // Validate the MTsubasaPoint in the database
        List<MTsubasaPoint> mTsubasaPointList = mTsubasaPointRepository.findAll();
        assertThat(mTsubasaPointList).hasSize(databaseSizeBeforeUpdate);
        MTsubasaPoint testMTsubasaPoint = mTsubasaPointList.get(mTsubasaPointList.size() - 1);
        assertThat(testMTsubasaPoint.getMatchType()).isEqualTo(UPDATED_MATCH_TYPE);
        assertThat(testMTsubasaPoint.getPointType()).isEqualTo(UPDATED_POINT_TYPE);
        assertThat(testMTsubasaPoint.getCalcType()).isEqualTo(UPDATED_CALC_TYPE);
        assertThat(testMTsubasaPoint.getaValue()).isEqualTo(UPDATED_A_VALUE);
        assertThat(testMTsubasaPoint.getbValue()).isEqualTo(UPDATED_B_VALUE);
        assertThat(testMTsubasaPoint.getOrderNum()).isEqualTo(UPDATED_ORDER_NUM);
    }

    @Test
    @Transactional
    public void updateNonExistingMTsubasaPoint() throws Exception {
        int databaseSizeBeforeUpdate = mTsubasaPointRepository.findAll().size();

        // Create the MTsubasaPoint
        MTsubasaPointDTO mTsubasaPointDTO = mTsubasaPointMapper.toDto(mTsubasaPoint);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restMTsubasaPointMockMvc.perform(put("/api/m-tsubasa-points")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mTsubasaPointDTO)))
            .andExpect(status().isBadRequest());

        // Validate the MTsubasaPoint in the database
        List<MTsubasaPoint> mTsubasaPointList = mTsubasaPointRepository.findAll();
        assertThat(mTsubasaPointList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteMTsubasaPoint() throws Exception {
        // Initialize the database
        mTsubasaPointRepository.saveAndFlush(mTsubasaPoint);

        int databaseSizeBeforeDelete = mTsubasaPointRepository.findAll().size();

        // Delete the mTsubasaPoint
        restMTsubasaPointMockMvc.perform(delete("/api/m-tsubasa-points/{id}", mTsubasaPoint.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database is empty
        List<MTsubasaPoint> mTsubasaPointList = mTsubasaPointRepository.findAll();
        assertThat(mTsubasaPointList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(MTsubasaPoint.class);
        MTsubasaPoint mTsubasaPoint1 = new MTsubasaPoint();
        mTsubasaPoint1.setId(1L);
        MTsubasaPoint mTsubasaPoint2 = new MTsubasaPoint();
        mTsubasaPoint2.setId(mTsubasaPoint1.getId());
        assertThat(mTsubasaPoint1).isEqualTo(mTsubasaPoint2);
        mTsubasaPoint2.setId(2L);
        assertThat(mTsubasaPoint1).isNotEqualTo(mTsubasaPoint2);
        mTsubasaPoint1.setId(null);
        assertThat(mTsubasaPoint1).isNotEqualTo(mTsubasaPoint2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(MTsubasaPointDTO.class);
        MTsubasaPointDTO mTsubasaPointDTO1 = new MTsubasaPointDTO();
        mTsubasaPointDTO1.setId(1L);
        MTsubasaPointDTO mTsubasaPointDTO2 = new MTsubasaPointDTO();
        assertThat(mTsubasaPointDTO1).isNotEqualTo(mTsubasaPointDTO2);
        mTsubasaPointDTO2.setId(mTsubasaPointDTO1.getId());
        assertThat(mTsubasaPointDTO1).isEqualTo(mTsubasaPointDTO2);
        mTsubasaPointDTO2.setId(2L);
        assertThat(mTsubasaPointDTO1).isNotEqualTo(mTsubasaPointDTO2);
        mTsubasaPointDTO1.setId(null);
        assertThat(mTsubasaPointDTO1).isNotEqualTo(mTsubasaPointDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(mTsubasaPointMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(mTsubasaPointMapper.fromId(null)).isNull();
    }
}
