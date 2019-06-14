package io.shm.tsubasa.web.rest;

import io.shm.tsubasa.TsubasaApp;
import io.shm.tsubasa.domain.MLoginBonusIncentive;
import io.shm.tsubasa.repository.MLoginBonusIncentiveRepository;
import io.shm.tsubasa.service.MLoginBonusIncentiveService;
import io.shm.tsubasa.service.dto.MLoginBonusIncentiveDTO;
import io.shm.tsubasa.service.mapper.MLoginBonusIncentiveMapper;
import io.shm.tsubasa.web.rest.errors.ExceptionTranslator;
import io.shm.tsubasa.service.dto.MLoginBonusIncentiveCriteria;
import io.shm.tsubasa.service.MLoginBonusIncentiveQueryService;

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
 * Integration tests for the {@Link MLoginBonusIncentiveResource} REST controller.
 */
@SpringBootTest(classes = TsubasaApp.class)
public class MLoginBonusIncentiveResourceIT {

    private static final Integer DEFAULT_ROUND_ID = 1;
    private static final Integer UPDATED_ROUND_ID = 2;

    private static final Integer DEFAULT_DAY = 1;
    private static final Integer UPDATED_DAY = 2;

    private static final Integer DEFAULT_CONTENT_TYPE = 1;
    private static final Integer UPDATED_CONTENT_TYPE = 2;

    private static final Integer DEFAULT_CONTENT_ID = 1;
    private static final Integer UPDATED_CONTENT_ID = 2;

    private static final Integer DEFAULT_CONTENT_AMOUNT = 1;
    private static final Integer UPDATED_CONTENT_AMOUNT = 2;

    private static final String DEFAULT_PRESENT_DETAIL = "AAAAAAAAAA";
    private static final String UPDATED_PRESENT_DETAIL = "BBBBBBBBBB";

    private static final Integer DEFAULT_IS_DECORATED = 1;
    private static final Integer UPDATED_IS_DECORATED = 2;

    @Autowired
    private MLoginBonusIncentiveRepository mLoginBonusIncentiveRepository;

    @Autowired
    private MLoginBonusIncentiveMapper mLoginBonusIncentiveMapper;

    @Autowired
    private MLoginBonusIncentiveService mLoginBonusIncentiveService;

    @Autowired
    private MLoginBonusIncentiveQueryService mLoginBonusIncentiveQueryService;

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

    private MockMvc restMLoginBonusIncentiveMockMvc;

    private MLoginBonusIncentive mLoginBonusIncentive;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final MLoginBonusIncentiveResource mLoginBonusIncentiveResource = new MLoginBonusIncentiveResource(mLoginBonusIncentiveService, mLoginBonusIncentiveQueryService);
        this.restMLoginBonusIncentiveMockMvc = MockMvcBuilders.standaloneSetup(mLoginBonusIncentiveResource)
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
    public static MLoginBonusIncentive createEntity(EntityManager em) {
        MLoginBonusIncentive mLoginBonusIncentive = new MLoginBonusIncentive()
            .roundId(DEFAULT_ROUND_ID)
            .day(DEFAULT_DAY)
            .contentType(DEFAULT_CONTENT_TYPE)
            .contentId(DEFAULT_CONTENT_ID)
            .contentAmount(DEFAULT_CONTENT_AMOUNT)
            .presentDetail(DEFAULT_PRESENT_DETAIL)
            .isDecorated(DEFAULT_IS_DECORATED);
        return mLoginBonusIncentive;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static MLoginBonusIncentive createUpdatedEntity(EntityManager em) {
        MLoginBonusIncentive mLoginBonusIncentive = new MLoginBonusIncentive()
            .roundId(UPDATED_ROUND_ID)
            .day(UPDATED_DAY)
            .contentType(UPDATED_CONTENT_TYPE)
            .contentId(UPDATED_CONTENT_ID)
            .contentAmount(UPDATED_CONTENT_AMOUNT)
            .presentDetail(UPDATED_PRESENT_DETAIL)
            .isDecorated(UPDATED_IS_DECORATED);
        return mLoginBonusIncentive;
    }

    @BeforeEach
    public void initTest() {
        mLoginBonusIncentive = createEntity(em);
    }

    @Test
    @Transactional
    public void createMLoginBonusIncentive() throws Exception {
        int databaseSizeBeforeCreate = mLoginBonusIncentiveRepository.findAll().size();

        // Create the MLoginBonusIncentive
        MLoginBonusIncentiveDTO mLoginBonusIncentiveDTO = mLoginBonusIncentiveMapper.toDto(mLoginBonusIncentive);
        restMLoginBonusIncentiveMockMvc.perform(post("/api/m-login-bonus-incentives")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mLoginBonusIncentiveDTO)))
            .andExpect(status().isCreated());

        // Validate the MLoginBonusIncentive in the database
        List<MLoginBonusIncentive> mLoginBonusIncentiveList = mLoginBonusIncentiveRepository.findAll();
        assertThat(mLoginBonusIncentiveList).hasSize(databaseSizeBeforeCreate + 1);
        MLoginBonusIncentive testMLoginBonusIncentive = mLoginBonusIncentiveList.get(mLoginBonusIncentiveList.size() - 1);
        assertThat(testMLoginBonusIncentive.getRoundId()).isEqualTo(DEFAULT_ROUND_ID);
        assertThat(testMLoginBonusIncentive.getDay()).isEqualTo(DEFAULT_DAY);
        assertThat(testMLoginBonusIncentive.getContentType()).isEqualTo(DEFAULT_CONTENT_TYPE);
        assertThat(testMLoginBonusIncentive.getContentId()).isEqualTo(DEFAULT_CONTENT_ID);
        assertThat(testMLoginBonusIncentive.getContentAmount()).isEqualTo(DEFAULT_CONTENT_AMOUNT);
        assertThat(testMLoginBonusIncentive.getPresentDetail()).isEqualTo(DEFAULT_PRESENT_DETAIL);
        assertThat(testMLoginBonusIncentive.getIsDecorated()).isEqualTo(DEFAULT_IS_DECORATED);
    }

    @Test
    @Transactional
    public void createMLoginBonusIncentiveWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = mLoginBonusIncentiveRepository.findAll().size();

        // Create the MLoginBonusIncentive with an existing ID
        mLoginBonusIncentive.setId(1L);
        MLoginBonusIncentiveDTO mLoginBonusIncentiveDTO = mLoginBonusIncentiveMapper.toDto(mLoginBonusIncentive);

        // An entity with an existing ID cannot be created, so this API call must fail
        restMLoginBonusIncentiveMockMvc.perform(post("/api/m-login-bonus-incentives")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mLoginBonusIncentiveDTO)))
            .andExpect(status().isBadRequest());

        // Validate the MLoginBonusIncentive in the database
        List<MLoginBonusIncentive> mLoginBonusIncentiveList = mLoginBonusIncentiveRepository.findAll();
        assertThat(mLoginBonusIncentiveList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkRoundIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = mLoginBonusIncentiveRepository.findAll().size();
        // set the field null
        mLoginBonusIncentive.setRoundId(null);

        // Create the MLoginBonusIncentive, which fails.
        MLoginBonusIncentiveDTO mLoginBonusIncentiveDTO = mLoginBonusIncentiveMapper.toDto(mLoginBonusIncentive);

        restMLoginBonusIncentiveMockMvc.perform(post("/api/m-login-bonus-incentives")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mLoginBonusIncentiveDTO)))
            .andExpect(status().isBadRequest());

        List<MLoginBonusIncentive> mLoginBonusIncentiveList = mLoginBonusIncentiveRepository.findAll();
        assertThat(mLoginBonusIncentiveList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkDayIsRequired() throws Exception {
        int databaseSizeBeforeTest = mLoginBonusIncentiveRepository.findAll().size();
        // set the field null
        mLoginBonusIncentive.setDay(null);

        // Create the MLoginBonusIncentive, which fails.
        MLoginBonusIncentiveDTO mLoginBonusIncentiveDTO = mLoginBonusIncentiveMapper.toDto(mLoginBonusIncentive);

        restMLoginBonusIncentiveMockMvc.perform(post("/api/m-login-bonus-incentives")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mLoginBonusIncentiveDTO)))
            .andExpect(status().isBadRequest());

        List<MLoginBonusIncentive> mLoginBonusIncentiveList = mLoginBonusIncentiveRepository.findAll();
        assertThat(mLoginBonusIncentiveList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkContentTypeIsRequired() throws Exception {
        int databaseSizeBeforeTest = mLoginBonusIncentiveRepository.findAll().size();
        // set the field null
        mLoginBonusIncentive.setContentType(null);

        // Create the MLoginBonusIncentive, which fails.
        MLoginBonusIncentiveDTO mLoginBonusIncentiveDTO = mLoginBonusIncentiveMapper.toDto(mLoginBonusIncentive);

        restMLoginBonusIncentiveMockMvc.perform(post("/api/m-login-bonus-incentives")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mLoginBonusIncentiveDTO)))
            .andExpect(status().isBadRequest());

        List<MLoginBonusIncentive> mLoginBonusIncentiveList = mLoginBonusIncentiveRepository.findAll();
        assertThat(mLoginBonusIncentiveList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkContentAmountIsRequired() throws Exception {
        int databaseSizeBeforeTest = mLoginBonusIncentiveRepository.findAll().size();
        // set the field null
        mLoginBonusIncentive.setContentAmount(null);

        // Create the MLoginBonusIncentive, which fails.
        MLoginBonusIncentiveDTO mLoginBonusIncentiveDTO = mLoginBonusIncentiveMapper.toDto(mLoginBonusIncentive);

        restMLoginBonusIncentiveMockMvc.perform(post("/api/m-login-bonus-incentives")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mLoginBonusIncentiveDTO)))
            .andExpect(status().isBadRequest());

        List<MLoginBonusIncentive> mLoginBonusIncentiveList = mLoginBonusIncentiveRepository.findAll();
        assertThat(mLoginBonusIncentiveList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkIsDecoratedIsRequired() throws Exception {
        int databaseSizeBeforeTest = mLoginBonusIncentiveRepository.findAll().size();
        // set the field null
        mLoginBonusIncentive.setIsDecorated(null);

        // Create the MLoginBonusIncentive, which fails.
        MLoginBonusIncentiveDTO mLoginBonusIncentiveDTO = mLoginBonusIncentiveMapper.toDto(mLoginBonusIncentive);

        restMLoginBonusIncentiveMockMvc.perform(post("/api/m-login-bonus-incentives")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mLoginBonusIncentiveDTO)))
            .andExpect(status().isBadRequest());

        List<MLoginBonusIncentive> mLoginBonusIncentiveList = mLoginBonusIncentiveRepository.findAll();
        assertThat(mLoginBonusIncentiveList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllMLoginBonusIncentives() throws Exception {
        // Initialize the database
        mLoginBonusIncentiveRepository.saveAndFlush(mLoginBonusIncentive);

        // Get all the mLoginBonusIncentiveList
        restMLoginBonusIncentiveMockMvc.perform(get("/api/m-login-bonus-incentives?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(mLoginBonusIncentive.getId().intValue())))
            .andExpect(jsonPath("$.[*].roundId").value(hasItem(DEFAULT_ROUND_ID)))
            .andExpect(jsonPath("$.[*].day").value(hasItem(DEFAULT_DAY)))
            .andExpect(jsonPath("$.[*].contentType").value(hasItem(DEFAULT_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].contentId").value(hasItem(DEFAULT_CONTENT_ID)))
            .andExpect(jsonPath("$.[*].contentAmount").value(hasItem(DEFAULT_CONTENT_AMOUNT)))
            .andExpect(jsonPath("$.[*].presentDetail").value(hasItem(DEFAULT_PRESENT_DETAIL.toString())))
            .andExpect(jsonPath("$.[*].isDecorated").value(hasItem(DEFAULT_IS_DECORATED)));
    }
    
    @Test
    @Transactional
    public void getMLoginBonusIncentive() throws Exception {
        // Initialize the database
        mLoginBonusIncentiveRepository.saveAndFlush(mLoginBonusIncentive);

        // Get the mLoginBonusIncentive
        restMLoginBonusIncentiveMockMvc.perform(get("/api/m-login-bonus-incentives/{id}", mLoginBonusIncentive.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(mLoginBonusIncentive.getId().intValue()))
            .andExpect(jsonPath("$.roundId").value(DEFAULT_ROUND_ID))
            .andExpect(jsonPath("$.day").value(DEFAULT_DAY))
            .andExpect(jsonPath("$.contentType").value(DEFAULT_CONTENT_TYPE))
            .andExpect(jsonPath("$.contentId").value(DEFAULT_CONTENT_ID))
            .andExpect(jsonPath("$.contentAmount").value(DEFAULT_CONTENT_AMOUNT))
            .andExpect(jsonPath("$.presentDetail").value(DEFAULT_PRESENT_DETAIL.toString()))
            .andExpect(jsonPath("$.isDecorated").value(DEFAULT_IS_DECORATED));
    }

    @Test
    @Transactional
    public void getAllMLoginBonusIncentivesByRoundIdIsEqualToSomething() throws Exception {
        // Initialize the database
        mLoginBonusIncentiveRepository.saveAndFlush(mLoginBonusIncentive);

        // Get all the mLoginBonusIncentiveList where roundId equals to DEFAULT_ROUND_ID
        defaultMLoginBonusIncentiveShouldBeFound("roundId.equals=" + DEFAULT_ROUND_ID);

        // Get all the mLoginBonusIncentiveList where roundId equals to UPDATED_ROUND_ID
        defaultMLoginBonusIncentiveShouldNotBeFound("roundId.equals=" + UPDATED_ROUND_ID);
    }

    @Test
    @Transactional
    public void getAllMLoginBonusIncentivesByRoundIdIsInShouldWork() throws Exception {
        // Initialize the database
        mLoginBonusIncentiveRepository.saveAndFlush(mLoginBonusIncentive);

        // Get all the mLoginBonusIncentiveList where roundId in DEFAULT_ROUND_ID or UPDATED_ROUND_ID
        defaultMLoginBonusIncentiveShouldBeFound("roundId.in=" + DEFAULT_ROUND_ID + "," + UPDATED_ROUND_ID);

        // Get all the mLoginBonusIncentiveList where roundId equals to UPDATED_ROUND_ID
        defaultMLoginBonusIncentiveShouldNotBeFound("roundId.in=" + UPDATED_ROUND_ID);
    }

    @Test
    @Transactional
    public void getAllMLoginBonusIncentivesByRoundIdIsNullOrNotNull() throws Exception {
        // Initialize the database
        mLoginBonusIncentiveRepository.saveAndFlush(mLoginBonusIncentive);

        // Get all the mLoginBonusIncentiveList where roundId is not null
        defaultMLoginBonusIncentiveShouldBeFound("roundId.specified=true");

        // Get all the mLoginBonusIncentiveList where roundId is null
        defaultMLoginBonusIncentiveShouldNotBeFound("roundId.specified=false");
    }

    @Test
    @Transactional
    public void getAllMLoginBonusIncentivesByRoundIdIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mLoginBonusIncentiveRepository.saveAndFlush(mLoginBonusIncentive);

        // Get all the mLoginBonusIncentiveList where roundId greater than or equals to DEFAULT_ROUND_ID
        defaultMLoginBonusIncentiveShouldBeFound("roundId.greaterOrEqualThan=" + DEFAULT_ROUND_ID);

        // Get all the mLoginBonusIncentiveList where roundId greater than or equals to UPDATED_ROUND_ID
        defaultMLoginBonusIncentiveShouldNotBeFound("roundId.greaterOrEqualThan=" + UPDATED_ROUND_ID);
    }

    @Test
    @Transactional
    public void getAllMLoginBonusIncentivesByRoundIdIsLessThanSomething() throws Exception {
        // Initialize the database
        mLoginBonusIncentiveRepository.saveAndFlush(mLoginBonusIncentive);

        // Get all the mLoginBonusIncentiveList where roundId less than or equals to DEFAULT_ROUND_ID
        defaultMLoginBonusIncentiveShouldNotBeFound("roundId.lessThan=" + DEFAULT_ROUND_ID);

        // Get all the mLoginBonusIncentiveList where roundId less than or equals to UPDATED_ROUND_ID
        defaultMLoginBonusIncentiveShouldBeFound("roundId.lessThan=" + UPDATED_ROUND_ID);
    }


    @Test
    @Transactional
    public void getAllMLoginBonusIncentivesByDayIsEqualToSomething() throws Exception {
        // Initialize the database
        mLoginBonusIncentiveRepository.saveAndFlush(mLoginBonusIncentive);

        // Get all the mLoginBonusIncentiveList where day equals to DEFAULT_DAY
        defaultMLoginBonusIncentiveShouldBeFound("day.equals=" + DEFAULT_DAY);

        // Get all the mLoginBonusIncentiveList where day equals to UPDATED_DAY
        defaultMLoginBonusIncentiveShouldNotBeFound("day.equals=" + UPDATED_DAY);
    }

    @Test
    @Transactional
    public void getAllMLoginBonusIncentivesByDayIsInShouldWork() throws Exception {
        // Initialize the database
        mLoginBonusIncentiveRepository.saveAndFlush(mLoginBonusIncentive);

        // Get all the mLoginBonusIncentiveList where day in DEFAULT_DAY or UPDATED_DAY
        defaultMLoginBonusIncentiveShouldBeFound("day.in=" + DEFAULT_DAY + "," + UPDATED_DAY);

        // Get all the mLoginBonusIncentiveList where day equals to UPDATED_DAY
        defaultMLoginBonusIncentiveShouldNotBeFound("day.in=" + UPDATED_DAY);
    }

    @Test
    @Transactional
    public void getAllMLoginBonusIncentivesByDayIsNullOrNotNull() throws Exception {
        // Initialize the database
        mLoginBonusIncentiveRepository.saveAndFlush(mLoginBonusIncentive);

        // Get all the mLoginBonusIncentiveList where day is not null
        defaultMLoginBonusIncentiveShouldBeFound("day.specified=true");

        // Get all the mLoginBonusIncentiveList where day is null
        defaultMLoginBonusIncentiveShouldNotBeFound("day.specified=false");
    }

    @Test
    @Transactional
    public void getAllMLoginBonusIncentivesByDayIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mLoginBonusIncentiveRepository.saveAndFlush(mLoginBonusIncentive);

        // Get all the mLoginBonusIncentiveList where day greater than or equals to DEFAULT_DAY
        defaultMLoginBonusIncentiveShouldBeFound("day.greaterOrEqualThan=" + DEFAULT_DAY);

        // Get all the mLoginBonusIncentiveList where day greater than or equals to UPDATED_DAY
        defaultMLoginBonusIncentiveShouldNotBeFound("day.greaterOrEqualThan=" + UPDATED_DAY);
    }

    @Test
    @Transactional
    public void getAllMLoginBonusIncentivesByDayIsLessThanSomething() throws Exception {
        // Initialize the database
        mLoginBonusIncentiveRepository.saveAndFlush(mLoginBonusIncentive);

        // Get all the mLoginBonusIncentiveList where day less than or equals to DEFAULT_DAY
        defaultMLoginBonusIncentiveShouldNotBeFound("day.lessThan=" + DEFAULT_DAY);

        // Get all the mLoginBonusIncentiveList where day less than or equals to UPDATED_DAY
        defaultMLoginBonusIncentiveShouldBeFound("day.lessThan=" + UPDATED_DAY);
    }


    @Test
    @Transactional
    public void getAllMLoginBonusIncentivesByContentTypeIsEqualToSomething() throws Exception {
        // Initialize the database
        mLoginBonusIncentiveRepository.saveAndFlush(mLoginBonusIncentive);

        // Get all the mLoginBonusIncentiveList where contentType equals to DEFAULT_CONTENT_TYPE
        defaultMLoginBonusIncentiveShouldBeFound("contentType.equals=" + DEFAULT_CONTENT_TYPE);

        // Get all the mLoginBonusIncentiveList where contentType equals to UPDATED_CONTENT_TYPE
        defaultMLoginBonusIncentiveShouldNotBeFound("contentType.equals=" + UPDATED_CONTENT_TYPE);
    }

    @Test
    @Transactional
    public void getAllMLoginBonusIncentivesByContentTypeIsInShouldWork() throws Exception {
        // Initialize the database
        mLoginBonusIncentiveRepository.saveAndFlush(mLoginBonusIncentive);

        // Get all the mLoginBonusIncentiveList where contentType in DEFAULT_CONTENT_TYPE or UPDATED_CONTENT_TYPE
        defaultMLoginBonusIncentiveShouldBeFound("contentType.in=" + DEFAULT_CONTENT_TYPE + "," + UPDATED_CONTENT_TYPE);

        // Get all the mLoginBonusIncentiveList where contentType equals to UPDATED_CONTENT_TYPE
        defaultMLoginBonusIncentiveShouldNotBeFound("contentType.in=" + UPDATED_CONTENT_TYPE);
    }

    @Test
    @Transactional
    public void getAllMLoginBonusIncentivesByContentTypeIsNullOrNotNull() throws Exception {
        // Initialize the database
        mLoginBonusIncentiveRepository.saveAndFlush(mLoginBonusIncentive);

        // Get all the mLoginBonusIncentiveList where contentType is not null
        defaultMLoginBonusIncentiveShouldBeFound("contentType.specified=true");

        // Get all the mLoginBonusIncentiveList where contentType is null
        defaultMLoginBonusIncentiveShouldNotBeFound("contentType.specified=false");
    }

    @Test
    @Transactional
    public void getAllMLoginBonusIncentivesByContentTypeIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mLoginBonusIncentiveRepository.saveAndFlush(mLoginBonusIncentive);

        // Get all the mLoginBonusIncentiveList where contentType greater than or equals to DEFAULT_CONTENT_TYPE
        defaultMLoginBonusIncentiveShouldBeFound("contentType.greaterOrEqualThan=" + DEFAULT_CONTENT_TYPE);

        // Get all the mLoginBonusIncentiveList where contentType greater than or equals to UPDATED_CONTENT_TYPE
        defaultMLoginBonusIncentiveShouldNotBeFound("contentType.greaterOrEqualThan=" + UPDATED_CONTENT_TYPE);
    }

    @Test
    @Transactional
    public void getAllMLoginBonusIncentivesByContentTypeIsLessThanSomething() throws Exception {
        // Initialize the database
        mLoginBonusIncentiveRepository.saveAndFlush(mLoginBonusIncentive);

        // Get all the mLoginBonusIncentiveList where contentType less than or equals to DEFAULT_CONTENT_TYPE
        defaultMLoginBonusIncentiveShouldNotBeFound("contentType.lessThan=" + DEFAULT_CONTENT_TYPE);

        // Get all the mLoginBonusIncentiveList where contentType less than or equals to UPDATED_CONTENT_TYPE
        defaultMLoginBonusIncentiveShouldBeFound("contentType.lessThan=" + UPDATED_CONTENT_TYPE);
    }


    @Test
    @Transactional
    public void getAllMLoginBonusIncentivesByContentIdIsEqualToSomething() throws Exception {
        // Initialize the database
        mLoginBonusIncentiveRepository.saveAndFlush(mLoginBonusIncentive);

        // Get all the mLoginBonusIncentiveList where contentId equals to DEFAULT_CONTENT_ID
        defaultMLoginBonusIncentiveShouldBeFound("contentId.equals=" + DEFAULT_CONTENT_ID);

        // Get all the mLoginBonusIncentiveList where contentId equals to UPDATED_CONTENT_ID
        defaultMLoginBonusIncentiveShouldNotBeFound("contentId.equals=" + UPDATED_CONTENT_ID);
    }

    @Test
    @Transactional
    public void getAllMLoginBonusIncentivesByContentIdIsInShouldWork() throws Exception {
        // Initialize the database
        mLoginBonusIncentiveRepository.saveAndFlush(mLoginBonusIncentive);

        // Get all the mLoginBonusIncentiveList where contentId in DEFAULT_CONTENT_ID or UPDATED_CONTENT_ID
        defaultMLoginBonusIncentiveShouldBeFound("contentId.in=" + DEFAULT_CONTENT_ID + "," + UPDATED_CONTENT_ID);

        // Get all the mLoginBonusIncentiveList where contentId equals to UPDATED_CONTENT_ID
        defaultMLoginBonusIncentiveShouldNotBeFound("contentId.in=" + UPDATED_CONTENT_ID);
    }

    @Test
    @Transactional
    public void getAllMLoginBonusIncentivesByContentIdIsNullOrNotNull() throws Exception {
        // Initialize the database
        mLoginBonusIncentiveRepository.saveAndFlush(mLoginBonusIncentive);

        // Get all the mLoginBonusIncentiveList where contentId is not null
        defaultMLoginBonusIncentiveShouldBeFound("contentId.specified=true");

        // Get all the mLoginBonusIncentiveList where contentId is null
        defaultMLoginBonusIncentiveShouldNotBeFound("contentId.specified=false");
    }

    @Test
    @Transactional
    public void getAllMLoginBonusIncentivesByContentIdIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mLoginBonusIncentiveRepository.saveAndFlush(mLoginBonusIncentive);

        // Get all the mLoginBonusIncentiveList where contentId greater than or equals to DEFAULT_CONTENT_ID
        defaultMLoginBonusIncentiveShouldBeFound("contentId.greaterOrEqualThan=" + DEFAULT_CONTENT_ID);

        // Get all the mLoginBonusIncentiveList where contentId greater than or equals to UPDATED_CONTENT_ID
        defaultMLoginBonusIncentiveShouldNotBeFound("contentId.greaterOrEqualThan=" + UPDATED_CONTENT_ID);
    }

    @Test
    @Transactional
    public void getAllMLoginBonusIncentivesByContentIdIsLessThanSomething() throws Exception {
        // Initialize the database
        mLoginBonusIncentiveRepository.saveAndFlush(mLoginBonusIncentive);

        // Get all the mLoginBonusIncentiveList where contentId less than or equals to DEFAULT_CONTENT_ID
        defaultMLoginBonusIncentiveShouldNotBeFound("contentId.lessThan=" + DEFAULT_CONTENT_ID);

        // Get all the mLoginBonusIncentiveList where contentId less than or equals to UPDATED_CONTENT_ID
        defaultMLoginBonusIncentiveShouldBeFound("contentId.lessThan=" + UPDATED_CONTENT_ID);
    }


    @Test
    @Transactional
    public void getAllMLoginBonusIncentivesByContentAmountIsEqualToSomething() throws Exception {
        // Initialize the database
        mLoginBonusIncentiveRepository.saveAndFlush(mLoginBonusIncentive);

        // Get all the mLoginBonusIncentiveList where contentAmount equals to DEFAULT_CONTENT_AMOUNT
        defaultMLoginBonusIncentiveShouldBeFound("contentAmount.equals=" + DEFAULT_CONTENT_AMOUNT);

        // Get all the mLoginBonusIncentiveList where contentAmount equals to UPDATED_CONTENT_AMOUNT
        defaultMLoginBonusIncentiveShouldNotBeFound("contentAmount.equals=" + UPDATED_CONTENT_AMOUNT);
    }

    @Test
    @Transactional
    public void getAllMLoginBonusIncentivesByContentAmountIsInShouldWork() throws Exception {
        // Initialize the database
        mLoginBonusIncentiveRepository.saveAndFlush(mLoginBonusIncentive);

        // Get all the mLoginBonusIncentiveList where contentAmount in DEFAULT_CONTENT_AMOUNT or UPDATED_CONTENT_AMOUNT
        defaultMLoginBonusIncentiveShouldBeFound("contentAmount.in=" + DEFAULT_CONTENT_AMOUNT + "," + UPDATED_CONTENT_AMOUNT);

        // Get all the mLoginBonusIncentiveList where contentAmount equals to UPDATED_CONTENT_AMOUNT
        defaultMLoginBonusIncentiveShouldNotBeFound("contentAmount.in=" + UPDATED_CONTENT_AMOUNT);
    }

    @Test
    @Transactional
    public void getAllMLoginBonusIncentivesByContentAmountIsNullOrNotNull() throws Exception {
        // Initialize the database
        mLoginBonusIncentiveRepository.saveAndFlush(mLoginBonusIncentive);

        // Get all the mLoginBonusIncentiveList where contentAmount is not null
        defaultMLoginBonusIncentiveShouldBeFound("contentAmount.specified=true");

        // Get all the mLoginBonusIncentiveList where contentAmount is null
        defaultMLoginBonusIncentiveShouldNotBeFound("contentAmount.specified=false");
    }

    @Test
    @Transactional
    public void getAllMLoginBonusIncentivesByContentAmountIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mLoginBonusIncentiveRepository.saveAndFlush(mLoginBonusIncentive);

        // Get all the mLoginBonusIncentiveList where contentAmount greater than or equals to DEFAULT_CONTENT_AMOUNT
        defaultMLoginBonusIncentiveShouldBeFound("contentAmount.greaterOrEqualThan=" + DEFAULT_CONTENT_AMOUNT);

        // Get all the mLoginBonusIncentiveList where contentAmount greater than or equals to UPDATED_CONTENT_AMOUNT
        defaultMLoginBonusIncentiveShouldNotBeFound("contentAmount.greaterOrEqualThan=" + UPDATED_CONTENT_AMOUNT);
    }

    @Test
    @Transactional
    public void getAllMLoginBonusIncentivesByContentAmountIsLessThanSomething() throws Exception {
        // Initialize the database
        mLoginBonusIncentiveRepository.saveAndFlush(mLoginBonusIncentive);

        // Get all the mLoginBonusIncentiveList where contentAmount less than or equals to DEFAULT_CONTENT_AMOUNT
        defaultMLoginBonusIncentiveShouldNotBeFound("contentAmount.lessThan=" + DEFAULT_CONTENT_AMOUNT);

        // Get all the mLoginBonusIncentiveList where contentAmount less than or equals to UPDATED_CONTENT_AMOUNT
        defaultMLoginBonusIncentiveShouldBeFound("contentAmount.lessThan=" + UPDATED_CONTENT_AMOUNT);
    }


    @Test
    @Transactional
    public void getAllMLoginBonusIncentivesByIsDecoratedIsEqualToSomething() throws Exception {
        // Initialize the database
        mLoginBonusIncentiveRepository.saveAndFlush(mLoginBonusIncentive);

        // Get all the mLoginBonusIncentiveList where isDecorated equals to DEFAULT_IS_DECORATED
        defaultMLoginBonusIncentiveShouldBeFound("isDecorated.equals=" + DEFAULT_IS_DECORATED);

        // Get all the mLoginBonusIncentiveList where isDecorated equals to UPDATED_IS_DECORATED
        defaultMLoginBonusIncentiveShouldNotBeFound("isDecorated.equals=" + UPDATED_IS_DECORATED);
    }

    @Test
    @Transactional
    public void getAllMLoginBonusIncentivesByIsDecoratedIsInShouldWork() throws Exception {
        // Initialize the database
        mLoginBonusIncentiveRepository.saveAndFlush(mLoginBonusIncentive);

        // Get all the mLoginBonusIncentiveList where isDecorated in DEFAULT_IS_DECORATED or UPDATED_IS_DECORATED
        defaultMLoginBonusIncentiveShouldBeFound("isDecorated.in=" + DEFAULT_IS_DECORATED + "," + UPDATED_IS_DECORATED);

        // Get all the mLoginBonusIncentiveList where isDecorated equals to UPDATED_IS_DECORATED
        defaultMLoginBonusIncentiveShouldNotBeFound("isDecorated.in=" + UPDATED_IS_DECORATED);
    }

    @Test
    @Transactional
    public void getAllMLoginBonusIncentivesByIsDecoratedIsNullOrNotNull() throws Exception {
        // Initialize the database
        mLoginBonusIncentiveRepository.saveAndFlush(mLoginBonusIncentive);

        // Get all the mLoginBonusIncentiveList where isDecorated is not null
        defaultMLoginBonusIncentiveShouldBeFound("isDecorated.specified=true");

        // Get all the mLoginBonusIncentiveList where isDecorated is null
        defaultMLoginBonusIncentiveShouldNotBeFound("isDecorated.specified=false");
    }

    @Test
    @Transactional
    public void getAllMLoginBonusIncentivesByIsDecoratedIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mLoginBonusIncentiveRepository.saveAndFlush(mLoginBonusIncentive);

        // Get all the mLoginBonusIncentiveList where isDecorated greater than or equals to DEFAULT_IS_DECORATED
        defaultMLoginBonusIncentiveShouldBeFound("isDecorated.greaterOrEqualThan=" + DEFAULT_IS_DECORATED);

        // Get all the mLoginBonusIncentiveList where isDecorated greater than or equals to UPDATED_IS_DECORATED
        defaultMLoginBonusIncentiveShouldNotBeFound("isDecorated.greaterOrEqualThan=" + UPDATED_IS_DECORATED);
    }

    @Test
    @Transactional
    public void getAllMLoginBonusIncentivesByIsDecoratedIsLessThanSomething() throws Exception {
        // Initialize the database
        mLoginBonusIncentiveRepository.saveAndFlush(mLoginBonusIncentive);

        // Get all the mLoginBonusIncentiveList where isDecorated less than or equals to DEFAULT_IS_DECORATED
        defaultMLoginBonusIncentiveShouldNotBeFound("isDecorated.lessThan=" + DEFAULT_IS_DECORATED);

        // Get all the mLoginBonusIncentiveList where isDecorated less than or equals to UPDATED_IS_DECORATED
        defaultMLoginBonusIncentiveShouldBeFound("isDecorated.lessThan=" + UPDATED_IS_DECORATED);
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultMLoginBonusIncentiveShouldBeFound(String filter) throws Exception {
        restMLoginBonusIncentiveMockMvc.perform(get("/api/m-login-bonus-incentives?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(mLoginBonusIncentive.getId().intValue())))
            .andExpect(jsonPath("$.[*].roundId").value(hasItem(DEFAULT_ROUND_ID)))
            .andExpect(jsonPath("$.[*].day").value(hasItem(DEFAULT_DAY)))
            .andExpect(jsonPath("$.[*].contentType").value(hasItem(DEFAULT_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].contentId").value(hasItem(DEFAULT_CONTENT_ID)))
            .andExpect(jsonPath("$.[*].contentAmount").value(hasItem(DEFAULT_CONTENT_AMOUNT)))
            .andExpect(jsonPath("$.[*].presentDetail").value(hasItem(DEFAULT_PRESENT_DETAIL.toString())))
            .andExpect(jsonPath("$.[*].isDecorated").value(hasItem(DEFAULT_IS_DECORATED)));

        // Check, that the count call also returns 1
        restMLoginBonusIncentiveMockMvc.perform(get("/api/m-login-bonus-incentives/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultMLoginBonusIncentiveShouldNotBeFound(String filter) throws Exception {
        restMLoginBonusIncentiveMockMvc.perform(get("/api/m-login-bonus-incentives?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restMLoginBonusIncentiveMockMvc.perform(get("/api/m-login-bonus-incentives/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("0"));
    }


    @Test
    @Transactional
    public void getNonExistingMLoginBonusIncentive() throws Exception {
        // Get the mLoginBonusIncentive
        restMLoginBonusIncentiveMockMvc.perform(get("/api/m-login-bonus-incentives/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateMLoginBonusIncentive() throws Exception {
        // Initialize the database
        mLoginBonusIncentiveRepository.saveAndFlush(mLoginBonusIncentive);

        int databaseSizeBeforeUpdate = mLoginBonusIncentiveRepository.findAll().size();

        // Update the mLoginBonusIncentive
        MLoginBonusIncentive updatedMLoginBonusIncentive = mLoginBonusIncentiveRepository.findById(mLoginBonusIncentive.getId()).get();
        // Disconnect from session so that the updates on updatedMLoginBonusIncentive are not directly saved in db
        em.detach(updatedMLoginBonusIncentive);
        updatedMLoginBonusIncentive
            .roundId(UPDATED_ROUND_ID)
            .day(UPDATED_DAY)
            .contentType(UPDATED_CONTENT_TYPE)
            .contentId(UPDATED_CONTENT_ID)
            .contentAmount(UPDATED_CONTENT_AMOUNT)
            .presentDetail(UPDATED_PRESENT_DETAIL)
            .isDecorated(UPDATED_IS_DECORATED);
        MLoginBonusIncentiveDTO mLoginBonusIncentiveDTO = mLoginBonusIncentiveMapper.toDto(updatedMLoginBonusIncentive);

        restMLoginBonusIncentiveMockMvc.perform(put("/api/m-login-bonus-incentives")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mLoginBonusIncentiveDTO)))
            .andExpect(status().isOk());

        // Validate the MLoginBonusIncentive in the database
        List<MLoginBonusIncentive> mLoginBonusIncentiveList = mLoginBonusIncentiveRepository.findAll();
        assertThat(mLoginBonusIncentiveList).hasSize(databaseSizeBeforeUpdate);
        MLoginBonusIncentive testMLoginBonusIncentive = mLoginBonusIncentiveList.get(mLoginBonusIncentiveList.size() - 1);
        assertThat(testMLoginBonusIncentive.getRoundId()).isEqualTo(UPDATED_ROUND_ID);
        assertThat(testMLoginBonusIncentive.getDay()).isEqualTo(UPDATED_DAY);
        assertThat(testMLoginBonusIncentive.getContentType()).isEqualTo(UPDATED_CONTENT_TYPE);
        assertThat(testMLoginBonusIncentive.getContentId()).isEqualTo(UPDATED_CONTENT_ID);
        assertThat(testMLoginBonusIncentive.getContentAmount()).isEqualTo(UPDATED_CONTENT_AMOUNT);
        assertThat(testMLoginBonusIncentive.getPresentDetail()).isEqualTo(UPDATED_PRESENT_DETAIL);
        assertThat(testMLoginBonusIncentive.getIsDecorated()).isEqualTo(UPDATED_IS_DECORATED);
    }

    @Test
    @Transactional
    public void updateNonExistingMLoginBonusIncentive() throws Exception {
        int databaseSizeBeforeUpdate = mLoginBonusIncentiveRepository.findAll().size();

        // Create the MLoginBonusIncentive
        MLoginBonusIncentiveDTO mLoginBonusIncentiveDTO = mLoginBonusIncentiveMapper.toDto(mLoginBonusIncentive);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restMLoginBonusIncentiveMockMvc.perform(put("/api/m-login-bonus-incentives")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mLoginBonusIncentiveDTO)))
            .andExpect(status().isBadRequest());

        // Validate the MLoginBonusIncentive in the database
        List<MLoginBonusIncentive> mLoginBonusIncentiveList = mLoginBonusIncentiveRepository.findAll();
        assertThat(mLoginBonusIncentiveList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteMLoginBonusIncentive() throws Exception {
        // Initialize the database
        mLoginBonusIncentiveRepository.saveAndFlush(mLoginBonusIncentive);

        int databaseSizeBeforeDelete = mLoginBonusIncentiveRepository.findAll().size();

        // Delete the mLoginBonusIncentive
        restMLoginBonusIncentiveMockMvc.perform(delete("/api/m-login-bonus-incentives/{id}", mLoginBonusIncentive.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database is empty
        List<MLoginBonusIncentive> mLoginBonusIncentiveList = mLoginBonusIncentiveRepository.findAll();
        assertThat(mLoginBonusIncentiveList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(MLoginBonusIncentive.class);
        MLoginBonusIncentive mLoginBonusIncentive1 = new MLoginBonusIncentive();
        mLoginBonusIncentive1.setId(1L);
        MLoginBonusIncentive mLoginBonusIncentive2 = new MLoginBonusIncentive();
        mLoginBonusIncentive2.setId(mLoginBonusIncentive1.getId());
        assertThat(mLoginBonusIncentive1).isEqualTo(mLoginBonusIncentive2);
        mLoginBonusIncentive2.setId(2L);
        assertThat(mLoginBonusIncentive1).isNotEqualTo(mLoginBonusIncentive2);
        mLoginBonusIncentive1.setId(null);
        assertThat(mLoginBonusIncentive1).isNotEqualTo(mLoginBonusIncentive2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(MLoginBonusIncentiveDTO.class);
        MLoginBonusIncentiveDTO mLoginBonusIncentiveDTO1 = new MLoginBonusIncentiveDTO();
        mLoginBonusIncentiveDTO1.setId(1L);
        MLoginBonusIncentiveDTO mLoginBonusIncentiveDTO2 = new MLoginBonusIncentiveDTO();
        assertThat(mLoginBonusIncentiveDTO1).isNotEqualTo(mLoginBonusIncentiveDTO2);
        mLoginBonusIncentiveDTO2.setId(mLoginBonusIncentiveDTO1.getId());
        assertThat(mLoginBonusIncentiveDTO1).isEqualTo(mLoginBonusIncentiveDTO2);
        mLoginBonusIncentiveDTO2.setId(2L);
        assertThat(mLoginBonusIncentiveDTO1).isNotEqualTo(mLoginBonusIncentiveDTO2);
        mLoginBonusIncentiveDTO1.setId(null);
        assertThat(mLoginBonusIncentiveDTO1).isNotEqualTo(mLoginBonusIncentiveDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(mLoginBonusIncentiveMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(mLoginBonusIncentiveMapper.fromId(null)).isNull();
    }
}
