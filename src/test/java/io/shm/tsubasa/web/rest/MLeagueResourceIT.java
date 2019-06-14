package io.shm.tsubasa.web.rest;

import io.shm.tsubasa.TsubasaApp;
import io.shm.tsubasa.domain.MLeague;
import io.shm.tsubasa.repository.MLeagueRepository;
import io.shm.tsubasa.service.MLeagueService;
import io.shm.tsubasa.service.dto.MLeagueDTO;
import io.shm.tsubasa.service.mapper.MLeagueMapper;
import io.shm.tsubasa.web.rest.errors.ExceptionTranslator;
import io.shm.tsubasa.service.dto.MLeagueCriteria;
import io.shm.tsubasa.service.MLeagueQueryService;

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
 * Integration tests for the {@Link MLeagueResource} REST controller.
 */
@SpringBootTest(classes = TsubasaApp.class)
public class MLeagueResourceIT {

    private static final Integer DEFAULT_HIERARCHY = 1;
    private static final Integer UPDATED_HIERARCHY = 2;

    private static final Integer DEFAULT_WEIGHT = 1;
    private static final Integer UPDATED_WEIGHT = 2;

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final Integer DEFAULT_ROOMS = 1;
    private static final Integer UPDATED_ROOMS = 2;

    private static final Integer DEFAULT_PROMOTION_RATE = 1;
    private static final Integer UPDATED_PROMOTION_RATE = 2;

    private static final Integer DEFAULT_DEMOTION_RATE = 1;
    private static final Integer UPDATED_DEMOTION_RATE = 2;

    private static final Integer DEFAULT_TOTAL_PARAMETER_RANGE_UPPER = 1;
    private static final Integer UPDATED_TOTAL_PARAMETER_RANGE_UPPER = 2;

    private static final Integer DEFAULT_TOTAL_PARAMETER_RANGE_LOWER = 1;
    private static final Integer UPDATED_TOTAL_PARAMETER_RANGE_LOWER = 2;

    private static final Integer DEFAULT_REQUIRED_MATCHES = 1;
    private static final Integer UPDATED_REQUIRED_MATCHES = 2;

    private static final Integer DEFAULT_START_WEEK_ID = 1;
    private static final Integer UPDATED_START_WEEK_ID = 2;

    @Autowired
    private MLeagueRepository mLeagueRepository;

    @Autowired
    private MLeagueMapper mLeagueMapper;

    @Autowired
    private MLeagueService mLeagueService;

    @Autowired
    private MLeagueQueryService mLeagueQueryService;

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

    private MockMvc restMLeagueMockMvc;

    private MLeague mLeague;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final MLeagueResource mLeagueResource = new MLeagueResource(mLeagueService, mLeagueQueryService);
        this.restMLeagueMockMvc = MockMvcBuilders.standaloneSetup(mLeagueResource)
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
    public static MLeague createEntity(EntityManager em) {
        MLeague mLeague = new MLeague()
            .hierarchy(DEFAULT_HIERARCHY)
            .weight(DEFAULT_WEIGHT)
            .name(DEFAULT_NAME)
            .rooms(DEFAULT_ROOMS)
            .promotionRate(DEFAULT_PROMOTION_RATE)
            .demotionRate(DEFAULT_DEMOTION_RATE)
            .totalParameterRangeUpper(DEFAULT_TOTAL_PARAMETER_RANGE_UPPER)
            .totalParameterRangeLower(DEFAULT_TOTAL_PARAMETER_RANGE_LOWER)
            .requiredMatches(DEFAULT_REQUIRED_MATCHES)
            .startWeekId(DEFAULT_START_WEEK_ID);
        return mLeague;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static MLeague createUpdatedEntity(EntityManager em) {
        MLeague mLeague = new MLeague()
            .hierarchy(UPDATED_HIERARCHY)
            .weight(UPDATED_WEIGHT)
            .name(UPDATED_NAME)
            .rooms(UPDATED_ROOMS)
            .promotionRate(UPDATED_PROMOTION_RATE)
            .demotionRate(UPDATED_DEMOTION_RATE)
            .totalParameterRangeUpper(UPDATED_TOTAL_PARAMETER_RANGE_UPPER)
            .totalParameterRangeLower(UPDATED_TOTAL_PARAMETER_RANGE_LOWER)
            .requiredMatches(UPDATED_REQUIRED_MATCHES)
            .startWeekId(UPDATED_START_WEEK_ID);
        return mLeague;
    }

    @BeforeEach
    public void initTest() {
        mLeague = createEntity(em);
    }

    @Test
    @Transactional
    public void createMLeague() throws Exception {
        int databaseSizeBeforeCreate = mLeagueRepository.findAll().size();

        // Create the MLeague
        MLeagueDTO mLeagueDTO = mLeagueMapper.toDto(mLeague);
        restMLeagueMockMvc.perform(post("/api/m-leagues")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mLeagueDTO)))
            .andExpect(status().isCreated());

        // Validate the MLeague in the database
        List<MLeague> mLeagueList = mLeagueRepository.findAll();
        assertThat(mLeagueList).hasSize(databaseSizeBeforeCreate + 1);
        MLeague testMLeague = mLeagueList.get(mLeagueList.size() - 1);
        assertThat(testMLeague.getHierarchy()).isEqualTo(DEFAULT_HIERARCHY);
        assertThat(testMLeague.getWeight()).isEqualTo(DEFAULT_WEIGHT);
        assertThat(testMLeague.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testMLeague.getRooms()).isEqualTo(DEFAULT_ROOMS);
        assertThat(testMLeague.getPromotionRate()).isEqualTo(DEFAULT_PROMOTION_RATE);
        assertThat(testMLeague.getDemotionRate()).isEqualTo(DEFAULT_DEMOTION_RATE);
        assertThat(testMLeague.getTotalParameterRangeUpper()).isEqualTo(DEFAULT_TOTAL_PARAMETER_RANGE_UPPER);
        assertThat(testMLeague.getTotalParameterRangeLower()).isEqualTo(DEFAULT_TOTAL_PARAMETER_RANGE_LOWER);
        assertThat(testMLeague.getRequiredMatches()).isEqualTo(DEFAULT_REQUIRED_MATCHES);
        assertThat(testMLeague.getStartWeekId()).isEqualTo(DEFAULT_START_WEEK_ID);
    }

    @Test
    @Transactional
    public void createMLeagueWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = mLeagueRepository.findAll().size();

        // Create the MLeague with an existing ID
        mLeague.setId(1L);
        MLeagueDTO mLeagueDTO = mLeagueMapper.toDto(mLeague);

        // An entity with an existing ID cannot be created, so this API call must fail
        restMLeagueMockMvc.perform(post("/api/m-leagues")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mLeagueDTO)))
            .andExpect(status().isBadRequest());

        // Validate the MLeague in the database
        List<MLeague> mLeagueList = mLeagueRepository.findAll();
        assertThat(mLeagueList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkHierarchyIsRequired() throws Exception {
        int databaseSizeBeforeTest = mLeagueRepository.findAll().size();
        // set the field null
        mLeague.setHierarchy(null);

        // Create the MLeague, which fails.
        MLeagueDTO mLeagueDTO = mLeagueMapper.toDto(mLeague);

        restMLeagueMockMvc.perform(post("/api/m-leagues")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mLeagueDTO)))
            .andExpect(status().isBadRequest());

        List<MLeague> mLeagueList = mLeagueRepository.findAll();
        assertThat(mLeagueList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkWeightIsRequired() throws Exception {
        int databaseSizeBeforeTest = mLeagueRepository.findAll().size();
        // set the field null
        mLeague.setWeight(null);

        // Create the MLeague, which fails.
        MLeagueDTO mLeagueDTO = mLeagueMapper.toDto(mLeague);

        restMLeagueMockMvc.perform(post("/api/m-leagues")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mLeagueDTO)))
            .andExpect(status().isBadRequest());

        List<MLeague> mLeagueList = mLeagueRepository.findAll();
        assertThat(mLeagueList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkRoomsIsRequired() throws Exception {
        int databaseSizeBeforeTest = mLeagueRepository.findAll().size();
        // set the field null
        mLeague.setRooms(null);

        // Create the MLeague, which fails.
        MLeagueDTO mLeagueDTO = mLeagueMapper.toDto(mLeague);

        restMLeagueMockMvc.perform(post("/api/m-leagues")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mLeagueDTO)))
            .andExpect(status().isBadRequest());

        List<MLeague> mLeagueList = mLeagueRepository.findAll();
        assertThat(mLeagueList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkPromotionRateIsRequired() throws Exception {
        int databaseSizeBeforeTest = mLeagueRepository.findAll().size();
        // set the field null
        mLeague.setPromotionRate(null);

        // Create the MLeague, which fails.
        MLeagueDTO mLeagueDTO = mLeagueMapper.toDto(mLeague);

        restMLeagueMockMvc.perform(post("/api/m-leagues")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mLeagueDTO)))
            .andExpect(status().isBadRequest());

        List<MLeague> mLeagueList = mLeagueRepository.findAll();
        assertThat(mLeagueList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkDemotionRateIsRequired() throws Exception {
        int databaseSizeBeforeTest = mLeagueRepository.findAll().size();
        // set the field null
        mLeague.setDemotionRate(null);

        // Create the MLeague, which fails.
        MLeagueDTO mLeagueDTO = mLeagueMapper.toDto(mLeague);

        restMLeagueMockMvc.perform(post("/api/m-leagues")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mLeagueDTO)))
            .andExpect(status().isBadRequest());

        List<MLeague> mLeagueList = mLeagueRepository.findAll();
        assertThat(mLeagueList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkTotalParameterRangeUpperIsRequired() throws Exception {
        int databaseSizeBeforeTest = mLeagueRepository.findAll().size();
        // set the field null
        mLeague.setTotalParameterRangeUpper(null);

        // Create the MLeague, which fails.
        MLeagueDTO mLeagueDTO = mLeagueMapper.toDto(mLeague);

        restMLeagueMockMvc.perform(post("/api/m-leagues")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mLeagueDTO)))
            .andExpect(status().isBadRequest());

        List<MLeague> mLeagueList = mLeagueRepository.findAll();
        assertThat(mLeagueList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkTotalParameterRangeLowerIsRequired() throws Exception {
        int databaseSizeBeforeTest = mLeagueRepository.findAll().size();
        // set the field null
        mLeague.setTotalParameterRangeLower(null);

        // Create the MLeague, which fails.
        MLeagueDTO mLeagueDTO = mLeagueMapper.toDto(mLeague);

        restMLeagueMockMvc.perform(post("/api/m-leagues")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mLeagueDTO)))
            .andExpect(status().isBadRequest());

        List<MLeague> mLeagueList = mLeagueRepository.findAll();
        assertThat(mLeagueList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkStartWeekIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = mLeagueRepository.findAll().size();
        // set the field null
        mLeague.setStartWeekId(null);

        // Create the MLeague, which fails.
        MLeagueDTO mLeagueDTO = mLeagueMapper.toDto(mLeague);

        restMLeagueMockMvc.perform(post("/api/m-leagues")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mLeagueDTO)))
            .andExpect(status().isBadRequest());

        List<MLeague> mLeagueList = mLeagueRepository.findAll();
        assertThat(mLeagueList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllMLeagues() throws Exception {
        // Initialize the database
        mLeagueRepository.saveAndFlush(mLeague);

        // Get all the mLeagueList
        restMLeagueMockMvc.perform(get("/api/m-leagues?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(mLeague.getId().intValue())))
            .andExpect(jsonPath("$.[*].hierarchy").value(hasItem(DEFAULT_HIERARCHY)))
            .andExpect(jsonPath("$.[*].weight").value(hasItem(DEFAULT_WEIGHT)))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME.toString())))
            .andExpect(jsonPath("$.[*].rooms").value(hasItem(DEFAULT_ROOMS)))
            .andExpect(jsonPath("$.[*].promotionRate").value(hasItem(DEFAULT_PROMOTION_RATE)))
            .andExpect(jsonPath("$.[*].demotionRate").value(hasItem(DEFAULT_DEMOTION_RATE)))
            .andExpect(jsonPath("$.[*].totalParameterRangeUpper").value(hasItem(DEFAULT_TOTAL_PARAMETER_RANGE_UPPER)))
            .andExpect(jsonPath("$.[*].totalParameterRangeLower").value(hasItem(DEFAULT_TOTAL_PARAMETER_RANGE_LOWER)))
            .andExpect(jsonPath("$.[*].requiredMatches").value(hasItem(DEFAULT_REQUIRED_MATCHES)))
            .andExpect(jsonPath("$.[*].startWeekId").value(hasItem(DEFAULT_START_WEEK_ID)));
    }
    
    @Test
    @Transactional
    public void getMLeague() throws Exception {
        // Initialize the database
        mLeagueRepository.saveAndFlush(mLeague);

        // Get the mLeague
        restMLeagueMockMvc.perform(get("/api/m-leagues/{id}", mLeague.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(mLeague.getId().intValue()))
            .andExpect(jsonPath("$.hierarchy").value(DEFAULT_HIERARCHY))
            .andExpect(jsonPath("$.weight").value(DEFAULT_WEIGHT))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME.toString()))
            .andExpect(jsonPath("$.rooms").value(DEFAULT_ROOMS))
            .andExpect(jsonPath("$.promotionRate").value(DEFAULT_PROMOTION_RATE))
            .andExpect(jsonPath("$.demotionRate").value(DEFAULT_DEMOTION_RATE))
            .andExpect(jsonPath("$.totalParameterRangeUpper").value(DEFAULT_TOTAL_PARAMETER_RANGE_UPPER))
            .andExpect(jsonPath("$.totalParameterRangeLower").value(DEFAULT_TOTAL_PARAMETER_RANGE_LOWER))
            .andExpect(jsonPath("$.requiredMatches").value(DEFAULT_REQUIRED_MATCHES))
            .andExpect(jsonPath("$.startWeekId").value(DEFAULT_START_WEEK_ID));
    }

    @Test
    @Transactional
    public void getAllMLeaguesByHierarchyIsEqualToSomething() throws Exception {
        // Initialize the database
        mLeagueRepository.saveAndFlush(mLeague);

        // Get all the mLeagueList where hierarchy equals to DEFAULT_HIERARCHY
        defaultMLeagueShouldBeFound("hierarchy.equals=" + DEFAULT_HIERARCHY);

        // Get all the mLeagueList where hierarchy equals to UPDATED_HIERARCHY
        defaultMLeagueShouldNotBeFound("hierarchy.equals=" + UPDATED_HIERARCHY);
    }

    @Test
    @Transactional
    public void getAllMLeaguesByHierarchyIsInShouldWork() throws Exception {
        // Initialize the database
        mLeagueRepository.saveAndFlush(mLeague);

        // Get all the mLeagueList where hierarchy in DEFAULT_HIERARCHY or UPDATED_HIERARCHY
        defaultMLeagueShouldBeFound("hierarchy.in=" + DEFAULT_HIERARCHY + "," + UPDATED_HIERARCHY);

        // Get all the mLeagueList where hierarchy equals to UPDATED_HIERARCHY
        defaultMLeagueShouldNotBeFound("hierarchy.in=" + UPDATED_HIERARCHY);
    }

    @Test
    @Transactional
    public void getAllMLeaguesByHierarchyIsNullOrNotNull() throws Exception {
        // Initialize the database
        mLeagueRepository.saveAndFlush(mLeague);

        // Get all the mLeagueList where hierarchy is not null
        defaultMLeagueShouldBeFound("hierarchy.specified=true");

        // Get all the mLeagueList where hierarchy is null
        defaultMLeagueShouldNotBeFound("hierarchy.specified=false");
    }

    @Test
    @Transactional
    public void getAllMLeaguesByHierarchyIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mLeagueRepository.saveAndFlush(mLeague);

        // Get all the mLeagueList where hierarchy greater than or equals to DEFAULT_HIERARCHY
        defaultMLeagueShouldBeFound("hierarchy.greaterOrEqualThan=" + DEFAULT_HIERARCHY);

        // Get all the mLeagueList where hierarchy greater than or equals to UPDATED_HIERARCHY
        defaultMLeagueShouldNotBeFound("hierarchy.greaterOrEqualThan=" + UPDATED_HIERARCHY);
    }

    @Test
    @Transactional
    public void getAllMLeaguesByHierarchyIsLessThanSomething() throws Exception {
        // Initialize the database
        mLeagueRepository.saveAndFlush(mLeague);

        // Get all the mLeagueList where hierarchy less than or equals to DEFAULT_HIERARCHY
        defaultMLeagueShouldNotBeFound("hierarchy.lessThan=" + DEFAULT_HIERARCHY);

        // Get all the mLeagueList where hierarchy less than or equals to UPDATED_HIERARCHY
        defaultMLeagueShouldBeFound("hierarchy.lessThan=" + UPDATED_HIERARCHY);
    }


    @Test
    @Transactional
    public void getAllMLeaguesByWeightIsEqualToSomething() throws Exception {
        // Initialize the database
        mLeagueRepository.saveAndFlush(mLeague);

        // Get all the mLeagueList where weight equals to DEFAULT_WEIGHT
        defaultMLeagueShouldBeFound("weight.equals=" + DEFAULT_WEIGHT);

        // Get all the mLeagueList where weight equals to UPDATED_WEIGHT
        defaultMLeagueShouldNotBeFound("weight.equals=" + UPDATED_WEIGHT);
    }

    @Test
    @Transactional
    public void getAllMLeaguesByWeightIsInShouldWork() throws Exception {
        // Initialize the database
        mLeagueRepository.saveAndFlush(mLeague);

        // Get all the mLeagueList where weight in DEFAULT_WEIGHT or UPDATED_WEIGHT
        defaultMLeagueShouldBeFound("weight.in=" + DEFAULT_WEIGHT + "," + UPDATED_WEIGHT);

        // Get all the mLeagueList where weight equals to UPDATED_WEIGHT
        defaultMLeagueShouldNotBeFound("weight.in=" + UPDATED_WEIGHT);
    }

    @Test
    @Transactional
    public void getAllMLeaguesByWeightIsNullOrNotNull() throws Exception {
        // Initialize the database
        mLeagueRepository.saveAndFlush(mLeague);

        // Get all the mLeagueList where weight is not null
        defaultMLeagueShouldBeFound("weight.specified=true");

        // Get all the mLeagueList where weight is null
        defaultMLeagueShouldNotBeFound("weight.specified=false");
    }

    @Test
    @Transactional
    public void getAllMLeaguesByWeightIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mLeagueRepository.saveAndFlush(mLeague);

        // Get all the mLeagueList where weight greater than or equals to DEFAULT_WEIGHT
        defaultMLeagueShouldBeFound("weight.greaterOrEqualThan=" + DEFAULT_WEIGHT);

        // Get all the mLeagueList where weight greater than or equals to UPDATED_WEIGHT
        defaultMLeagueShouldNotBeFound("weight.greaterOrEqualThan=" + UPDATED_WEIGHT);
    }

    @Test
    @Transactional
    public void getAllMLeaguesByWeightIsLessThanSomething() throws Exception {
        // Initialize the database
        mLeagueRepository.saveAndFlush(mLeague);

        // Get all the mLeagueList where weight less than or equals to DEFAULT_WEIGHT
        defaultMLeagueShouldNotBeFound("weight.lessThan=" + DEFAULT_WEIGHT);

        // Get all the mLeagueList where weight less than or equals to UPDATED_WEIGHT
        defaultMLeagueShouldBeFound("weight.lessThan=" + UPDATED_WEIGHT);
    }


    @Test
    @Transactional
    public void getAllMLeaguesByRoomsIsEqualToSomething() throws Exception {
        // Initialize the database
        mLeagueRepository.saveAndFlush(mLeague);

        // Get all the mLeagueList where rooms equals to DEFAULT_ROOMS
        defaultMLeagueShouldBeFound("rooms.equals=" + DEFAULT_ROOMS);

        // Get all the mLeagueList where rooms equals to UPDATED_ROOMS
        defaultMLeagueShouldNotBeFound("rooms.equals=" + UPDATED_ROOMS);
    }

    @Test
    @Transactional
    public void getAllMLeaguesByRoomsIsInShouldWork() throws Exception {
        // Initialize the database
        mLeagueRepository.saveAndFlush(mLeague);

        // Get all the mLeagueList where rooms in DEFAULT_ROOMS or UPDATED_ROOMS
        defaultMLeagueShouldBeFound("rooms.in=" + DEFAULT_ROOMS + "," + UPDATED_ROOMS);

        // Get all the mLeagueList where rooms equals to UPDATED_ROOMS
        defaultMLeagueShouldNotBeFound("rooms.in=" + UPDATED_ROOMS);
    }

    @Test
    @Transactional
    public void getAllMLeaguesByRoomsIsNullOrNotNull() throws Exception {
        // Initialize the database
        mLeagueRepository.saveAndFlush(mLeague);

        // Get all the mLeagueList where rooms is not null
        defaultMLeagueShouldBeFound("rooms.specified=true");

        // Get all the mLeagueList where rooms is null
        defaultMLeagueShouldNotBeFound("rooms.specified=false");
    }

    @Test
    @Transactional
    public void getAllMLeaguesByRoomsIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mLeagueRepository.saveAndFlush(mLeague);

        // Get all the mLeagueList where rooms greater than or equals to DEFAULT_ROOMS
        defaultMLeagueShouldBeFound("rooms.greaterOrEqualThan=" + DEFAULT_ROOMS);

        // Get all the mLeagueList where rooms greater than or equals to UPDATED_ROOMS
        defaultMLeagueShouldNotBeFound("rooms.greaterOrEqualThan=" + UPDATED_ROOMS);
    }

    @Test
    @Transactional
    public void getAllMLeaguesByRoomsIsLessThanSomething() throws Exception {
        // Initialize the database
        mLeagueRepository.saveAndFlush(mLeague);

        // Get all the mLeagueList where rooms less than or equals to DEFAULT_ROOMS
        defaultMLeagueShouldNotBeFound("rooms.lessThan=" + DEFAULT_ROOMS);

        // Get all the mLeagueList where rooms less than or equals to UPDATED_ROOMS
        defaultMLeagueShouldBeFound("rooms.lessThan=" + UPDATED_ROOMS);
    }


    @Test
    @Transactional
    public void getAllMLeaguesByPromotionRateIsEqualToSomething() throws Exception {
        // Initialize the database
        mLeagueRepository.saveAndFlush(mLeague);

        // Get all the mLeagueList where promotionRate equals to DEFAULT_PROMOTION_RATE
        defaultMLeagueShouldBeFound("promotionRate.equals=" + DEFAULT_PROMOTION_RATE);

        // Get all the mLeagueList where promotionRate equals to UPDATED_PROMOTION_RATE
        defaultMLeagueShouldNotBeFound("promotionRate.equals=" + UPDATED_PROMOTION_RATE);
    }

    @Test
    @Transactional
    public void getAllMLeaguesByPromotionRateIsInShouldWork() throws Exception {
        // Initialize the database
        mLeagueRepository.saveAndFlush(mLeague);

        // Get all the mLeagueList where promotionRate in DEFAULT_PROMOTION_RATE or UPDATED_PROMOTION_RATE
        defaultMLeagueShouldBeFound("promotionRate.in=" + DEFAULT_PROMOTION_RATE + "," + UPDATED_PROMOTION_RATE);

        // Get all the mLeagueList where promotionRate equals to UPDATED_PROMOTION_RATE
        defaultMLeagueShouldNotBeFound("promotionRate.in=" + UPDATED_PROMOTION_RATE);
    }

    @Test
    @Transactional
    public void getAllMLeaguesByPromotionRateIsNullOrNotNull() throws Exception {
        // Initialize the database
        mLeagueRepository.saveAndFlush(mLeague);

        // Get all the mLeagueList where promotionRate is not null
        defaultMLeagueShouldBeFound("promotionRate.specified=true");

        // Get all the mLeagueList where promotionRate is null
        defaultMLeagueShouldNotBeFound("promotionRate.specified=false");
    }

    @Test
    @Transactional
    public void getAllMLeaguesByPromotionRateIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mLeagueRepository.saveAndFlush(mLeague);

        // Get all the mLeagueList where promotionRate greater than or equals to DEFAULT_PROMOTION_RATE
        defaultMLeagueShouldBeFound("promotionRate.greaterOrEqualThan=" + DEFAULT_PROMOTION_RATE);

        // Get all the mLeagueList where promotionRate greater than or equals to UPDATED_PROMOTION_RATE
        defaultMLeagueShouldNotBeFound("promotionRate.greaterOrEqualThan=" + UPDATED_PROMOTION_RATE);
    }

    @Test
    @Transactional
    public void getAllMLeaguesByPromotionRateIsLessThanSomething() throws Exception {
        // Initialize the database
        mLeagueRepository.saveAndFlush(mLeague);

        // Get all the mLeagueList where promotionRate less than or equals to DEFAULT_PROMOTION_RATE
        defaultMLeagueShouldNotBeFound("promotionRate.lessThan=" + DEFAULT_PROMOTION_RATE);

        // Get all the mLeagueList where promotionRate less than or equals to UPDATED_PROMOTION_RATE
        defaultMLeagueShouldBeFound("promotionRate.lessThan=" + UPDATED_PROMOTION_RATE);
    }


    @Test
    @Transactional
    public void getAllMLeaguesByDemotionRateIsEqualToSomething() throws Exception {
        // Initialize the database
        mLeagueRepository.saveAndFlush(mLeague);

        // Get all the mLeagueList where demotionRate equals to DEFAULT_DEMOTION_RATE
        defaultMLeagueShouldBeFound("demotionRate.equals=" + DEFAULT_DEMOTION_RATE);

        // Get all the mLeagueList where demotionRate equals to UPDATED_DEMOTION_RATE
        defaultMLeagueShouldNotBeFound("demotionRate.equals=" + UPDATED_DEMOTION_RATE);
    }

    @Test
    @Transactional
    public void getAllMLeaguesByDemotionRateIsInShouldWork() throws Exception {
        // Initialize the database
        mLeagueRepository.saveAndFlush(mLeague);

        // Get all the mLeagueList where demotionRate in DEFAULT_DEMOTION_RATE or UPDATED_DEMOTION_RATE
        defaultMLeagueShouldBeFound("demotionRate.in=" + DEFAULT_DEMOTION_RATE + "," + UPDATED_DEMOTION_RATE);

        // Get all the mLeagueList where demotionRate equals to UPDATED_DEMOTION_RATE
        defaultMLeagueShouldNotBeFound("demotionRate.in=" + UPDATED_DEMOTION_RATE);
    }

    @Test
    @Transactional
    public void getAllMLeaguesByDemotionRateIsNullOrNotNull() throws Exception {
        // Initialize the database
        mLeagueRepository.saveAndFlush(mLeague);

        // Get all the mLeagueList where demotionRate is not null
        defaultMLeagueShouldBeFound("demotionRate.specified=true");

        // Get all the mLeagueList where demotionRate is null
        defaultMLeagueShouldNotBeFound("demotionRate.specified=false");
    }

    @Test
    @Transactional
    public void getAllMLeaguesByDemotionRateIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mLeagueRepository.saveAndFlush(mLeague);

        // Get all the mLeagueList where demotionRate greater than or equals to DEFAULT_DEMOTION_RATE
        defaultMLeagueShouldBeFound("demotionRate.greaterOrEqualThan=" + DEFAULT_DEMOTION_RATE);

        // Get all the mLeagueList where demotionRate greater than or equals to UPDATED_DEMOTION_RATE
        defaultMLeagueShouldNotBeFound("demotionRate.greaterOrEqualThan=" + UPDATED_DEMOTION_RATE);
    }

    @Test
    @Transactional
    public void getAllMLeaguesByDemotionRateIsLessThanSomething() throws Exception {
        // Initialize the database
        mLeagueRepository.saveAndFlush(mLeague);

        // Get all the mLeagueList where demotionRate less than or equals to DEFAULT_DEMOTION_RATE
        defaultMLeagueShouldNotBeFound("demotionRate.lessThan=" + DEFAULT_DEMOTION_RATE);

        // Get all the mLeagueList where demotionRate less than or equals to UPDATED_DEMOTION_RATE
        defaultMLeagueShouldBeFound("demotionRate.lessThan=" + UPDATED_DEMOTION_RATE);
    }


    @Test
    @Transactional
    public void getAllMLeaguesByTotalParameterRangeUpperIsEqualToSomething() throws Exception {
        // Initialize the database
        mLeagueRepository.saveAndFlush(mLeague);

        // Get all the mLeagueList where totalParameterRangeUpper equals to DEFAULT_TOTAL_PARAMETER_RANGE_UPPER
        defaultMLeagueShouldBeFound("totalParameterRangeUpper.equals=" + DEFAULT_TOTAL_PARAMETER_RANGE_UPPER);

        // Get all the mLeagueList where totalParameterRangeUpper equals to UPDATED_TOTAL_PARAMETER_RANGE_UPPER
        defaultMLeagueShouldNotBeFound("totalParameterRangeUpper.equals=" + UPDATED_TOTAL_PARAMETER_RANGE_UPPER);
    }

    @Test
    @Transactional
    public void getAllMLeaguesByTotalParameterRangeUpperIsInShouldWork() throws Exception {
        // Initialize the database
        mLeagueRepository.saveAndFlush(mLeague);

        // Get all the mLeagueList where totalParameterRangeUpper in DEFAULT_TOTAL_PARAMETER_RANGE_UPPER or UPDATED_TOTAL_PARAMETER_RANGE_UPPER
        defaultMLeagueShouldBeFound("totalParameterRangeUpper.in=" + DEFAULT_TOTAL_PARAMETER_RANGE_UPPER + "," + UPDATED_TOTAL_PARAMETER_RANGE_UPPER);

        // Get all the mLeagueList where totalParameterRangeUpper equals to UPDATED_TOTAL_PARAMETER_RANGE_UPPER
        defaultMLeagueShouldNotBeFound("totalParameterRangeUpper.in=" + UPDATED_TOTAL_PARAMETER_RANGE_UPPER);
    }

    @Test
    @Transactional
    public void getAllMLeaguesByTotalParameterRangeUpperIsNullOrNotNull() throws Exception {
        // Initialize the database
        mLeagueRepository.saveAndFlush(mLeague);

        // Get all the mLeagueList where totalParameterRangeUpper is not null
        defaultMLeagueShouldBeFound("totalParameterRangeUpper.specified=true");

        // Get all the mLeagueList where totalParameterRangeUpper is null
        defaultMLeagueShouldNotBeFound("totalParameterRangeUpper.specified=false");
    }

    @Test
    @Transactional
    public void getAllMLeaguesByTotalParameterRangeUpperIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mLeagueRepository.saveAndFlush(mLeague);

        // Get all the mLeagueList where totalParameterRangeUpper greater than or equals to DEFAULT_TOTAL_PARAMETER_RANGE_UPPER
        defaultMLeagueShouldBeFound("totalParameterRangeUpper.greaterOrEqualThan=" + DEFAULT_TOTAL_PARAMETER_RANGE_UPPER);

        // Get all the mLeagueList where totalParameterRangeUpper greater than or equals to UPDATED_TOTAL_PARAMETER_RANGE_UPPER
        defaultMLeagueShouldNotBeFound("totalParameterRangeUpper.greaterOrEqualThan=" + UPDATED_TOTAL_PARAMETER_RANGE_UPPER);
    }

    @Test
    @Transactional
    public void getAllMLeaguesByTotalParameterRangeUpperIsLessThanSomething() throws Exception {
        // Initialize the database
        mLeagueRepository.saveAndFlush(mLeague);

        // Get all the mLeagueList where totalParameterRangeUpper less than or equals to DEFAULT_TOTAL_PARAMETER_RANGE_UPPER
        defaultMLeagueShouldNotBeFound("totalParameterRangeUpper.lessThan=" + DEFAULT_TOTAL_PARAMETER_RANGE_UPPER);

        // Get all the mLeagueList where totalParameterRangeUpper less than or equals to UPDATED_TOTAL_PARAMETER_RANGE_UPPER
        defaultMLeagueShouldBeFound("totalParameterRangeUpper.lessThan=" + UPDATED_TOTAL_PARAMETER_RANGE_UPPER);
    }


    @Test
    @Transactional
    public void getAllMLeaguesByTotalParameterRangeLowerIsEqualToSomething() throws Exception {
        // Initialize the database
        mLeagueRepository.saveAndFlush(mLeague);

        // Get all the mLeagueList where totalParameterRangeLower equals to DEFAULT_TOTAL_PARAMETER_RANGE_LOWER
        defaultMLeagueShouldBeFound("totalParameterRangeLower.equals=" + DEFAULT_TOTAL_PARAMETER_RANGE_LOWER);

        // Get all the mLeagueList where totalParameterRangeLower equals to UPDATED_TOTAL_PARAMETER_RANGE_LOWER
        defaultMLeagueShouldNotBeFound("totalParameterRangeLower.equals=" + UPDATED_TOTAL_PARAMETER_RANGE_LOWER);
    }

    @Test
    @Transactional
    public void getAllMLeaguesByTotalParameterRangeLowerIsInShouldWork() throws Exception {
        // Initialize the database
        mLeagueRepository.saveAndFlush(mLeague);

        // Get all the mLeagueList where totalParameterRangeLower in DEFAULT_TOTAL_PARAMETER_RANGE_LOWER or UPDATED_TOTAL_PARAMETER_RANGE_LOWER
        defaultMLeagueShouldBeFound("totalParameterRangeLower.in=" + DEFAULT_TOTAL_PARAMETER_RANGE_LOWER + "," + UPDATED_TOTAL_PARAMETER_RANGE_LOWER);

        // Get all the mLeagueList where totalParameterRangeLower equals to UPDATED_TOTAL_PARAMETER_RANGE_LOWER
        defaultMLeagueShouldNotBeFound("totalParameterRangeLower.in=" + UPDATED_TOTAL_PARAMETER_RANGE_LOWER);
    }

    @Test
    @Transactional
    public void getAllMLeaguesByTotalParameterRangeLowerIsNullOrNotNull() throws Exception {
        // Initialize the database
        mLeagueRepository.saveAndFlush(mLeague);

        // Get all the mLeagueList where totalParameterRangeLower is not null
        defaultMLeagueShouldBeFound("totalParameterRangeLower.specified=true");

        // Get all the mLeagueList where totalParameterRangeLower is null
        defaultMLeagueShouldNotBeFound("totalParameterRangeLower.specified=false");
    }

    @Test
    @Transactional
    public void getAllMLeaguesByTotalParameterRangeLowerIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mLeagueRepository.saveAndFlush(mLeague);

        // Get all the mLeagueList where totalParameterRangeLower greater than or equals to DEFAULT_TOTAL_PARAMETER_RANGE_LOWER
        defaultMLeagueShouldBeFound("totalParameterRangeLower.greaterOrEqualThan=" + DEFAULT_TOTAL_PARAMETER_RANGE_LOWER);

        // Get all the mLeagueList where totalParameterRangeLower greater than or equals to UPDATED_TOTAL_PARAMETER_RANGE_LOWER
        defaultMLeagueShouldNotBeFound("totalParameterRangeLower.greaterOrEqualThan=" + UPDATED_TOTAL_PARAMETER_RANGE_LOWER);
    }

    @Test
    @Transactional
    public void getAllMLeaguesByTotalParameterRangeLowerIsLessThanSomething() throws Exception {
        // Initialize the database
        mLeagueRepository.saveAndFlush(mLeague);

        // Get all the mLeagueList where totalParameterRangeLower less than or equals to DEFAULT_TOTAL_PARAMETER_RANGE_LOWER
        defaultMLeagueShouldNotBeFound("totalParameterRangeLower.lessThan=" + DEFAULT_TOTAL_PARAMETER_RANGE_LOWER);

        // Get all the mLeagueList where totalParameterRangeLower less than or equals to UPDATED_TOTAL_PARAMETER_RANGE_LOWER
        defaultMLeagueShouldBeFound("totalParameterRangeLower.lessThan=" + UPDATED_TOTAL_PARAMETER_RANGE_LOWER);
    }


    @Test
    @Transactional
    public void getAllMLeaguesByRequiredMatchesIsEqualToSomething() throws Exception {
        // Initialize the database
        mLeagueRepository.saveAndFlush(mLeague);

        // Get all the mLeagueList where requiredMatches equals to DEFAULT_REQUIRED_MATCHES
        defaultMLeagueShouldBeFound("requiredMatches.equals=" + DEFAULT_REQUIRED_MATCHES);

        // Get all the mLeagueList where requiredMatches equals to UPDATED_REQUIRED_MATCHES
        defaultMLeagueShouldNotBeFound("requiredMatches.equals=" + UPDATED_REQUIRED_MATCHES);
    }

    @Test
    @Transactional
    public void getAllMLeaguesByRequiredMatchesIsInShouldWork() throws Exception {
        // Initialize the database
        mLeagueRepository.saveAndFlush(mLeague);

        // Get all the mLeagueList where requiredMatches in DEFAULT_REQUIRED_MATCHES or UPDATED_REQUIRED_MATCHES
        defaultMLeagueShouldBeFound("requiredMatches.in=" + DEFAULT_REQUIRED_MATCHES + "," + UPDATED_REQUIRED_MATCHES);

        // Get all the mLeagueList where requiredMatches equals to UPDATED_REQUIRED_MATCHES
        defaultMLeagueShouldNotBeFound("requiredMatches.in=" + UPDATED_REQUIRED_MATCHES);
    }

    @Test
    @Transactional
    public void getAllMLeaguesByRequiredMatchesIsNullOrNotNull() throws Exception {
        // Initialize the database
        mLeagueRepository.saveAndFlush(mLeague);

        // Get all the mLeagueList where requiredMatches is not null
        defaultMLeagueShouldBeFound("requiredMatches.specified=true");

        // Get all the mLeagueList where requiredMatches is null
        defaultMLeagueShouldNotBeFound("requiredMatches.specified=false");
    }

    @Test
    @Transactional
    public void getAllMLeaguesByRequiredMatchesIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mLeagueRepository.saveAndFlush(mLeague);

        // Get all the mLeagueList where requiredMatches greater than or equals to DEFAULT_REQUIRED_MATCHES
        defaultMLeagueShouldBeFound("requiredMatches.greaterOrEqualThan=" + DEFAULT_REQUIRED_MATCHES);

        // Get all the mLeagueList where requiredMatches greater than or equals to UPDATED_REQUIRED_MATCHES
        defaultMLeagueShouldNotBeFound("requiredMatches.greaterOrEqualThan=" + UPDATED_REQUIRED_MATCHES);
    }

    @Test
    @Transactional
    public void getAllMLeaguesByRequiredMatchesIsLessThanSomething() throws Exception {
        // Initialize the database
        mLeagueRepository.saveAndFlush(mLeague);

        // Get all the mLeagueList where requiredMatches less than or equals to DEFAULT_REQUIRED_MATCHES
        defaultMLeagueShouldNotBeFound("requiredMatches.lessThan=" + DEFAULT_REQUIRED_MATCHES);

        // Get all the mLeagueList where requiredMatches less than or equals to UPDATED_REQUIRED_MATCHES
        defaultMLeagueShouldBeFound("requiredMatches.lessThan=" + UPDATED_REQUIRED_MATCHES);
    }


    @Test
    @Transactional
    public void getAllMLeaguesByStartWeekIdIsEqualToSomething() throws Exception {
        // Initialize the database
        mLeagueRepository.saveAndFlush(mLeague);

        // Get all the mLeagueList where startWeekId equals to DEFAULT_START_WEEK_ID
        defaultMLeagueShouldBeFound("startWeekId.equals=" + DEFAULT_START_WEEK_ID);

        // Get all the mLeagueList where startWeekId equals to UPDATED_START_WEEK_ID
        defaultMLeagueShouldNotBeFound("startWeekId.equals=" + UPDATED_START_WEEK_ID);
    }

    @Test
    @Transactional
    public void getAllMLeaguesByStartWeekIdIsInShouldWork() throws Exception {
        // Initialize the database
        mLeagueRepository.saveAndFlush(mLeague);

        // Get all the mLeagueList where startWeekId in DEFAULT_START_WEEK_ID or UPDATED_START_WEEK_ID
        defaultMLeagueShouldBeFound("startWeekId.in=" + DEFAULT_START_WEEK_ID + "," + UPDATED_START_WEEK_ID);

        // Get all the mLeagueList where startWeekId equals to UPDATED_START_WEEK_ID
        defaultMLeagueShouldNotBeFound("startWeekId.in=" + UPDATED_START_WEEK_ID);
    }

    @Test
    @Transactional
    public void getAllMLeaguesByStartWeekIdIsNullOrNotNull() throws Exception {
        // Initialize the database
        mLeagueRepository.saveAndFlush(mLeague);

        // Get all the mLeagueList where startWeekId is not null
        defaultMLeagueShouldBeFound("startWeekId.specified=true");

        // Get all the mLeagueList where startWeekId is null
        defaultMLeagueShouldNotBeFound("startWeekId.specified=false");
    }

    @Test
    @Transactional
    public void getAllMLeaguesByStartWeekIdIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mLeagueRepository.saveAndFlush(mLeague);

        // Get all the mLeagueList where startWeekId greater than or equals to DEFAULT_START_WEEK_ID
        defaultMLeagueShouldBeFound("startWeekId.greaterOrEqualThan=" + DEFAULT_START_WEEK_ID);

        // Get all the mLeagueList where startWeekId greater than or equals to UPDATED_START_WEEK_ID
        defaultMLeagueShouldNotBeFound("startWeekId.greaterOrEqualThan=" + UPDATED_START_WEEK_ID);
    }

    @Test
    @Transactional
    public void getAllMLeaguesByStartWeekIdIsLessThanSomething() throws Exception {
        // Initialize the database
        mLeagueRepository.saveAndFlush(mLeague);

        // Get all the mLeagueList where startWeekId less than or equals to DEFAULT_START_WEEK_ID
        defaultMLeagueShouldNotBeFound("startWeekId.lessThan=" + DEFAULT_START_WEEK_ID);

        // Get all the mLeagueList where startWeekId less than or equals to UPDATED_START_WEEK_ID
        defaultMLeagueShouldBeFound("startWeekId.lessThan=" + UPDATED_START_WEEK_ID);
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultMLeagueShouldBeFound(String filter) throws Exception {
        restMLeagueMockMvc.perform(get("/api/m-leagues?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(mLeague.getId().intValue())))
            .andExpect(jsonPath("$.[*].hierarchy").value(hasItem(DEFAULT_HIERARCHY)))
            .andExpect(jsonPath("$.[*].weight").value(hasItem(DEFAULT_WEIGHT)))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME.toString())))
            .andExpect(jsonPath("$.[*].rooms").value(hasItem(DEFAULT_ROOMS)))
            .andExpect(jsonPath("$.[*].promotionRate").value(hasItem(DEFAULT_PROMOTION_RATE)))
            .andExpect(jsonPath("$.[*].demotionRate").value(hasItem(DEFAULT_DEMOTION_RATE)))
            .andExpect(jsonPath("$.[*].totalParameterRangeUpper").value(hasItem(DEFAULT_TOTAL_PARAMETER_RANGE_UPPER)))
            .andExpect(jsonPath("$.[*].totalParameterRangeLower").value(hasItem(DEFAULT_TOTAL_PARAMETER_RANGE_LOWER)))
            .andExpect(jsonPath("$.[*].requiredMatches").value(hasItem(DEFAULT_REQUIRED_MATCHES)))
            .andExpect(jsonPath("$.[*].startWeekId").value(hasItem(DEFAULT_START_WEEK_ID)));

        // Check, that the count call also returns 1
        restMLeagueMockMvc.perform(get("/api/m-leagues/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultMLeagueShouldNotBeFound(String filter) throws Exception {
        restMLeagueMockMvc.perform(get("/api/m-leagues?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restMLeagueMockMvc.perform(get("/api/m-leagues/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("0"));
    }


    @Test
    @Transactional
    public void getNonExistingMLeague() throws Exception {
        // Get the mLeague
        restMLeagueMockMvc.perform(get("/api/m-leagues/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateMLeague() throws Exception {
        // Initialize the database
        mLeagueRepository.saveAndFlush(mLeague);

        int databaseSizeBeforeUpdate = mLeagueRepository.findAll().size();

        // Update the mLeague
        MLeague updatedMLeague = mLeagueRepository.findById(mLeague.getId()).get();
        // Disconnect from session so that the updates on updatedMLeague are not directly saved in db
        em.detach(updatedMLeague);
        updatedMLeague
            .hierarchy(UPDATED_HIERARCHY)
            .weight(UPDATED_WEIGHT)
            .name(UPDATED_NAME)
            .rooms(UPDATED_ROOMS)
            .promotionRate(UPDATED_PROMOTION_RATE)
            .demotionRate(UPDATED_DEMOTION_RATE)
            .totalParameterRangeUpper(UPDATED_TOTAL_PARAMETER_RANGE_UPPER)
            .totalParameterRangeLower(UPDATED_TOTAL_PARAMETER_RANGE_LOWER)
            .requiredMatches(UPDATED_REQUIRED_MATCHES)
            .startWeekId(UPDATED_START_WEEK_ID);
        MLeagueDTO mLeagueDTO = mLeagueMapper.toDto(updatedMLeague);

        restMLeagueMockMvc.perform(put("/api/m-leagues")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mLeagueDTO)))
            .andExpect(status().isOk());

        // Validate the MLeague in the database
        List<MLeague> mLeagueList = mLeagueRepository.findAll();
        assertThat(mLeagueList).hasSize(databaseSizeBeforeUpdate);
        MLeague testMLeague = mLeagueList.get(mLeagueList.size() - 1);
        assertThat(testMLeague.getHierarchy()).isEqualTo(UPDATED_HIERARCHY);
        assertThat(testMLeague.getWeight()).isEqualTo(UPDATED_WEIGHT);
        assertThat(testMLeague.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testMLeague.getRooms()).isEqualTo(UPDATED_ROOMS);
        assertThat(testMLeague.getPromotionRate()).isEqualTo(UPDATED_PROMOTION_RATE);
        assertThat(testMLeague.getDemotionRate()).isEqualTo(UPDATED_DEMOTION_RATE);
        assertThat(testMLeague.getTotalParameterRangeUpper()).isEqualTo(UPDATED_TOTAL_PARAMETER_RANGE_UPPER);
        assertThat(testMLeague.getTotalParameterRangeLower()).isEqualTo(UPDATED_TOTAL_PARAMETER_RANGE_LOWER);
        assertThat(testMLeague.getRequiredMatches()).isEqualTo(UPDATED_REQUIRED_MATCHES);
        assertThat(testMLeague.getStartWeekId()).isEqualTo(UPDATED_START_WEEK_ID);
    }

    @Test
    @Transactional
    public void updateNonExistingMLeague() throws Exception {
        int databaseSizeBeforeUpdate = mLeagueRepository.findAll().size();

        // Create the MLeague
        MLeagueDTO mLeagueDTO = mLeagueMapper.toDto(mLeague);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restMLeagueMockMvc.perform(put("/api/m-leagues")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mLeagueDTO)))
            .andExpect(status().isBadRequest());

        // Validate the MLeague in the database
        List<MLeague> mLeagueList = mLeagueRepository.findAll();
        assertThat(mLeagueList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteMLeague() throws Exception {
        // Initialize the database
        mLeagueRepository.saveAndFlush(mLeague);

        int databaseSizeBeforeDelete = mLeagueRepository.findAll().size();

        // Delete the mLeague
        restMLeagueMockMvc.perform(delete("/api/m-leagues/{id}", mLeague.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database is empty
        List<MLeague> mLeagueList = mLeagueRepository.findAll();
        assertThat(mLeagueList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(MLeague.class);
        MLeague mLeague1 = new MLeague();
        mLeague1.setId(1L);
        MLeague mLeague2 = new MLeague();
        mLeague2.setId(mLeague1.getId());
        assertThat(mLeague1).isEqualTo(mLeague2);
        mLeague2.setId(2L);
        assertThat(mLeague1).isNotEqualTo(mLeague2);
        mLeague1.setId(null);
        assertThat(mLeague1).isNotEqualTo(mLeague2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(MLeagueDTO.class);
        MLeagueDTO mLeagueDTO1 = new MLeagueDTO();
        mLeagueDTO1.setId(1L);
        MLeagueDTO mLeagueDTO2 = new MLeagueDTO();
        assertThat(mLeagueDTO1).isNotEqualTo(mLeagueDTO2);
        mLeagueDTO2.setId(mLeagueDTO1.getId());
        assertThat(mLeagueDTO1).isEqualTo(mLeagueDTO2);
        mLeagueDTO2.setId(2L);
        assertThat(mLeagueDTO1).isNotEqualTo(mLeagueDTO2);
        mLeagueDTO1.setId(null);
        assertThat(mLeagueDTO1).isNotEqualTo(mLeagueDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(mLeagueMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(mLeagueMapper.fromId(null)).isNull();
    }
}
