package io.shm.tsubasa.web.rest;

import io.shm.tsubasa.TsubasaApp;
import io.shm.tsubasa.domain.MMarathonBoostSchedule;
import io.shm.tsubasa.repository.MMarathonBoostScheduleRepository;
import io.shm.tsubasa.service.MMarathonBoostScheduleService;
import io.shm.tsubasa.service.dto.MMarathonBoostScheduleDTO;
import io.shm.tsubasa.service.mapper.MMarathonBoostScheduleMapper;
import io.shm.tsubasa.web.rest.errors.ExceptionTranslator;
import io.shm.tsubasa.service.dto.MMarathonBoostScheduleCriteria;
import io.shm.tsubasa.service.MMarathonBoostScheduleQueryService;

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
 * Integration tests for the {@Link MMarathonBoostScheduleResource} REST controller.
 */
@SpringBootTest(classes = TsubasaApp.class)
public class MMarathonBoostScheduleResourceIT {

    private static final Integer DEFAULT_EVENT_ID = 1;
    private static final Integer UPDATED_EVENT_ID = 2;

    private static final Integer DEFAULT_BOOST_RATIO = 1;
    private static final Integer UPDATED_BOOST_RATIO = 2;

    private static final Integer DEFAULT_START_AT = 1;
    private static final Integer UPDATED_START_AT = 2;

    private static final Integer DEFAULT_END_AT = 1;
    private static final Integer UPDATED_END_AT = 2;

    @Autowired
    private MMarathonBoostScheduleRepository mMarathonBoostScheduleRepository;

    @Autowired
    private MMarathonBoostScheduleMapper mMarathonBoostScheduleMapper;

    @Autowired
    private MMarathonBoostScheduleService mMarathonBoostScheduleService;

    @Autowired
    private MMarathonBoostScheduleQueryService mMarathonBoostScheduleQueryService;

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

    private MockMvc restMMarathonBoostScheduleMockMvc;

    private MMarathonBoostSchedule mMarathonBoostSchedule;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final MMarathonBoostScheduleResource mMarathonBoostScheduleResource = new MMarathonBoostScheduleResource(mMarathonBoostScheduleService, mMarathonBoostScheduleQueryService);
        this.restMMarathonBoostScheduleMockMvc = MockMvcBuilders.standaloneSetup(mMarathonBoostScheduleResource)
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
    public static MMarathonBoostSchedule createEntity(EntityManager em) {
        MMarathonBoostSchedule mMarathonBoostSchedule = new MMarathonBoostSchedule()
            .eventId(DEFAULT_EVENT_ID)
            .boostRatio(DEFAULT_BOOST_RATIO)
            .startAt(DEFAULT_START_AT)
            .endAt(DEFAULT_END_AT);
        return mMarathonBoostSchedule;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static MMarathonBoostSchedule createUpdatedEntity(EntityManager em) {
        MMarathonBoostSchedule mMarathonBoostSchedule = new MMarathonBoostSchedule()
            .eventId(UPDATED_EVENT_ID)
            .boostRatio(UPDATED_BOOST_RATIO)
            .startAt(UPDATED_START_AT)
            .endAt(UPDATED_END_AT);
        return mMarathonBoostSchedule;
    }

    @BeforeEach
    public void initTest() {
        mMarathonBoostSchedule = createEntity(em);
    }

    @Test
    @Transactional
    public void createMMarathonBoostSchedule() throws Exception {
        int databaseSizeBeforeCreate = mMarathonBoostScheduleRepository.findAll().size();

        // Create the MMarathonBoostSchedule
        MMarathonBoostScheduleDTO mMarathonBoostScheduleDTO = mMarathonBoostScheduleMapper.toDto(mMarathonBoostSchedule);
        restMMarathonBoostScheduleMockMvc.perform(post("/api/m-marathon-boost-schedules")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mMarathonBoostScheduleDTO)))
            .andExpect(status().isCreated());

        // Validate the MMarathonBoostSchedule in the database
        List<MMarathonBoostSchedule> mMarathonBoostScheduleList = mMarathonBoostScheduleRepository.findAll();
        assertThat(mMarathonBoostScheduleList).hasSize(databaseSizeBeforeCreate + 1);
        MMarathonBoostSchedule testMMarathonBoostSchedule = mMarathonBoostScheduleList.get(mMarathonBoostScheduleList.size() - 1);
        assertThat(testMMarathonBoostSchedule.getEventId()).isEqualTo(DEFAULT_EVENT_ID);
        assertThat(testMMarathonBoostSchedule.getBoostRatio()).isEqualTo(DEFAULT_BOOST_RATIO);
        assertThat(testMMarathonBoostSchedule.getStartAt()).isEqualTo(DEFAULT_START_AT);
        assertThat(testMMarathonBoostSchedule.getEndAt()).isEqualTo(DEFAULT_END_AT);
    }

    @Test
    @Transactional
    public void createMMarathonBoostScheduleWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = mMarathonBoostScheduleRepository.findAll().size();

        // Create the MMarathonBoostSchedule with an existing ID
        mMarathonBoostSchedule.setId(1L);
        MMarathonBoostScheduleDTO mMarathonBoostScheduleDTO = mMarathonBoostScheduleMapper.toDto(mMarathonBoostSchedule);

        // An entity with an existing ID cannot be created, so this API call must fail
        restMMarathonBoostScheduleMockMvc.perform(post("/api/m-marathon-boost-schedules")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mMarathonBoostScheduleDTO)))
            .andExpect(status().isBadRequest());

        // Validate the MMarathonBoostSchedule in the database
        List<MMarathonBoostSchedule> mMarathonBoostScheduleList = mMarathonBoostScheduleRepository.findAll();
        assertThat(mMarathonBoostScheduleList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkEventIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = mMarathonBoostScheduleRepository.findAll().size();
        // set the field null
        mMarathonBoostSchedule.setEventId(null);

        // Create the MMarathonBoostSchedule, which fails.
        MMarathonBoostScheduleDTO mMarathonBoostScheduleDTO = mMarathonBoostScheduleMapper.toDto(mMarathonBoostSchedule);

        restMMarathonBoostScheduleMockMvc.perform(post("/api/m-marathon-boost-schedules")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mMarathonBoostScheduleDTO)))
            .andExpect(status().isBadRequest());

        List<MMarathonBoostSchedule> mMarathonBoostScheduleList = mMarathonBoostScheduleRepository.findAll();
        assertThat(mMarathonBoostScheduleList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkBoostRatioIsRequired() throws Exception {
        int databaseSizeBeforeTest = mMarathonBoostScheduleRepository.findAll().size();
        // set the field null
        mMarathonBoostSchedule.setBoostRatio(null);

        // Create the MMarathonBoostSchedule, which fails.
        MMarathonBoostScheduleDTO mMarathonBoostScheduleDTO = mMarathonBoostScheduleMapper.toDto(mMarathonBoostSchedule);

        restMMarathonBoostScheduleMockMvc.perform(post("/api/m-marathon-boost-schedules")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mMarathonBoostScheduleDTO)))
            .andExpect(status().isBadRequest());

        List<MMarathonBoostSchedule> mMarathonBoostScheduleList = mMarathonBoostScheduleRepository.findAll();
        assertThat(mMarathonBoostScheduleList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkStartAtIsRequired() throws Exception {
        int databaseSizeBeforeTest = mMarathonBoostScheduleRepository.findAll().size();
        // set the field null
        mMarathonBoostSchedule.setStartAt(null);

        // Create the MMarathonBoostSchedule, which fails.
        MMarathonBoostScheduleDTO mMarathonBoostScheduleDTO = mMarathonBoostScheduleMapper.toDto(mMarathonBoostSchedule);

        restMMarathonBoostScheduleMockMvc.perform(post("/api/m-marathon-boost-schedules")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mMarathonBoostScheduleDTO)))
            .andExpect(status().isBadRequest());

        List<MMarathonBoostSchedule> mMarathonBoostScheduleList = mMarathonBoostScheduleRepository.findAll();
        assertThat(mMarathonBoostScheduleList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkEndAtIsRequired() throws Exception {
        int databaseSizeBeforeTest = mMarathonBoostScheduleRepository.findAll().size();
        // set the field null
        mMarathonBoostSchedule.setEndAt(null);

        // Create the MMarathonBoostSchedule, which fails.
        MMarathonBoostScheduleDTO mMarathonBoostScheduleDTO = mMarathonBoostScheduleMapper.toDto(mMarathonBoostSchedule);

        restMMarathonBoostScheduleMockMvc.perform(post("/api/m-marathon-boost-schedules")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mMarathonBoostScheduleDTO)))
            .andExpect(status().isBadRequest());

        List<MMarathonBoostSchedule> mMarathonBoostScheduleList = mMarathonBoostScheduleRepository.findAll();
        assertThat(mMarathonBoostScheduleList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllMMarathonBoostSchedules() throws Exception {
        // Initialize the database
        mMarathonBoostScheduleRepository.saveAndFlush(mMarathonBoostSchedule);

        // Get all the mMarathonBoostScheduleList
        restMMarathonBoostScheduleMockMvc.perform(get("/api/m-marathon-boost-schedules?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(mMarathonBoostSchedule.getId().intValue())))
            .andExpect(jsonPath("$.[*].eventId").value(hasItem(DEFAULT_EVENT_ID)))
            .andExpect(jsonPath("$.[*].boostRatio").value(hasItem(DEFAULT_BOOST_RATIO)))
            .andExpect(jsonPath("$.[*].startAt").value(hasItem(DEFAULT_START_AT)))
            .andExpect(jsonPath("$.[*].endAt").value(hasItem(DEFAULT_END_AT)));
    }
    
    @Test
    @Transactional
    public void getMMarathonBoostSchedule() throws Exception {
        // Initialize the database
        mMarathonBoostScheduleRepository.saveAndFlush(mMarathonBoostSchedule);

        // Get the mMarathonBoostSchedule
        restMMarathonBoostScheduleMockMvc.perform(get("/api/m-marathon-boost-schedules/{id}", mMarathonBoostSchedule.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(mMarathonBoostSchedule.getId().intValue()))
            .andExpect(jsonPath("$.eventId").value(DEFAULT_EVENT_ID))
            .andExpect(jsonPath("$.boostRatio").value(DEFAULT_BOOST_RATIO))
            .andExpect(jsonPath("$.startAt").value(DEFAULT_START_AT))
            .andExpect(jsonPath("$.endAt").value(DEFAULT_END_AT));
    }

    @Test
    @Transactional
    public void getAllMMarathonBoostSchedulesByEventIdIsEqualToSomething() throws Exception {
        // Initialize the database
        mMarathonBoostScheduleRepository.saveAndFlush(mMarathonBoostSchedule);

        // Get all the mMarathonBoostScheduleList where eventId equals to DEFAULT_EVENT_ID
        defaultMMarathonBoostScheduleShouldBeFound("eventId.equals=" + DEFAULT_EVENT_ID);

        // Get all the mMarathonBoostScheduleList where eventId equals to UPDATED_EVENT_ID
        defaultMMarathonBoostScheduleShouldNotBeFound("eventId.equals=" + UPDATED_EVENT_ID);
    }

    @Test
    @Transactional
    public void getAllMMarathonBoostSchedulesByEventIdIsInShouldWork() throws Exception {
        // Initialize the database
        mMarathonBoostScheduleRepository.saveAndFlush(mMarathonBoostSchedule);

        // Get all the mMarathonBoostScheduleList where eventId in DEFAULT_EVENT_ID or UPDATED_EVENT_ID
        defaultMMarathonBoostScheduleShouldBeFound("eventId.in=" + DEFAULT_EVENT_ID + "," + UPDATED_EVENT_ID);

        // Get all the mMarathonBoostScheduleList where eventId equals to UPDATED_EVENT_ID
        defaultMMarathonBoostScheduleShouldNotBeFound("eventId.in=" + UPDATED_EVENT_ID);
    }

    @Test
    @Transactional
    public void getAllMMarathonBoostSchedulesByEventIdIsNullOrNotNull() throws Exception {
        // Initialize the database
        mMarathonBoostScheduleRepository.saveAndFlush(mMarathonBoostSchedule);

        // Get all the mMarathonBoostScheduleList where eventId is not null
        defaultMMarathonBoostScheduleShouldBeFound("eventId.specified=true");

        // Get all the mMarathonBoostScheduleList where eventId is null
        defaultMMarathonBoostScheduleShouldNotBeFound("eventId.specified=false");
    }

    @Test
    @Transactional
    public void getAllMMarathonBoostSchedulesByEventIdIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mMarathonBoostScheduleRepository.saveAndFlush(mMarathonBoostSchedule);

        // Get all the mMarathonBoostScheduleList where eventId greater than or equals to DEFAULT_EVENT_ID
        defaultMMarathonBoostScheduleShouldBeFound("eventId.greaterOrEqualThan=" + DEFAULT_EVENT_ID);

        // Get all the mMarathonBoostScheduleList where eventId greater than or equals to UPDATED_EVENT_ID
        defaultMMarathonBoostScheduleShouldNotBeFound("eventId.greaterOrEqualThan=" + UPDATED_EVENT_ID);
    }

    @Test
    @Transactional
    public void getAllMMarathonBoostSchedulesByEventIdIsLessThanSomething() throws Exception {
        // Initialize the database
        mMarathonBoostScheduleRepository.saveAndFlush(mMarathonBoostSchedule);

        // Get all the mMarathonBoostScheduleList where eventId less than or equals to DEFAULT_EVENT_ID
        defaultMMarathonBoostScheduleShouldNotBeFound("eventId.lessThan=" + DEFAULT_EVENT_ID);

        // Get all the mMarathonBoostScheduleList where eventId less than or equals to UPDATED_EVENT_ID
        defaultMMarathonBoostScheduleShouldBeFound("eventId.lessThan=" + UPDATED_EVENT_ID);
    }


    @Test
    @Transactional
    public void getAllMMarathonBoostSchedulesByBoostRatioIsEqualToSomething() throws Exception {
        // Initialize the database
        mMarathonBoostScheduleRepository.saveAndFlush(mMarathonBoostSchedule);

        // Get all the mMarathonBoostScheduleList where boostRatio equals to DEFAULT_BOOST_RATIO
        defaultMMarathonBoostScheduleShouldBeFound("boostRatio.equals=" + DEFAULT_BOOST_RATIO);

        // Get all the mMarathonBoostScheduleList where boostRatio equals to UPDATED_BOOST_RATIO
        defaultMMarathonBoostScheduleShouldNotBeFound("boostRatio.equals=" + UPDATED_BOOST_RATIO);
    }

    @Test
    @Transactional
    public void getAllMMarathonBoostSchedulesByBoostRatioIsInShouldWork() throws Exception {
        // Initialize the database
        mMarathonBoostScheduleRepository.saveAndFlush(mMarathonBoostSchedule);

        // Get all the mMarathonBoostScheduleList where boostRatio in DEFAULT_BOOST_RATIO or UPDATED_BOOST_RATIO
        defaultMMarathonBoostScheduleShouldBeFound("boostRatio.in=" + DEFAULT_BOOST_RATIO + "," + UPDATED_BOOST_RATIO);

        // Get all the mMarathonBoostScheduleList where boostRatio equals to UPDATED_BOOST_RATIO
        defaultMMarathonBoostScheduleShouldNotBeFound("boostRatio.in=" + UPDATED_BOOST_RATIO);
    }

    @Test
    @Transactional
    public void getAllMMarathonBoostSchedulesByBoostRatioIsNullOrNotNull() throws Exception {
        // Initialize the database
        mMarathonBoostScheduleRepository.saveAndFlush(mMarathonBoostSchedule);

        // Get all the mMarathonBoostScheduleList where boostRatio is not null
        defaultMMarathonBoostScheduleShouldBeFound("boostRatio.specified=true");

        // Get all the mMarathonBoostScheduleList where boostRatio is null
        defaultMMarathonBoostScheduleShouldNotBeFound("boostRatio.specified=false");
    }

    @Test
    @Transactional
    public void getAllMMarathonBoostSchedulesByBoostRatioIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mMarathonBoostScheduleRepository.saveAndFlush(mMarathonBoostSchedule);

        // Get all the mMarathonBoostScheduleList where boostRatio greater than or equals to DEFAULT_BOOST_RATIO
        defaultMMarathonBoostScheduleShouldBeFound("boostRatio.greaterOrEqualThan=" + DEFAULT_BOOST_RATIO);

        // Get all the mMarathonBoostScheduleList where boostRatio greater than or equals to UPDATED_BOOST_RATIO
        defaultMMarathonBoostScheduleShouldNotBeFound("boostRatio.greaterOrEqualThan=" + UPDATED_BOOST_RATIO);
    }

    @Test
    @Transactional
    public void getAllMMarathonBoostSchedulesByBoostRatioIsLessThanSomething() throws Exception {
        // Initialize the database
        mMarathonBoostScheduleRepository.saveAndFlush(mMarathonBoostSchedule);

        // Get all the mMarathonBoostScheduleList where boostRatio less than or equals to DEFAULT_BOOST_RATIO
        defaultMMarathonBoostScheduleShouldNotBeFound("boostRatio.lessThan=" + DEFAULT_BOOST_RATIO);

        // Get all the mMarathonBoostScheduleList where boostRatio less than or equals to UPDATED_BOOST_RATIO
        defaultMMarathonBoostScheduleShouldBeFound("boostRatio.lessThan=" + UPDATED_BOOST_RATIO);
    }


    @Test
    @Transactional
    public void getAllMMarathonBoostSchedulesByStartAtIsEqualToSomething() throws Exception {
        // Initialize the database
        mMarathonBoostScheduleRepository.saveAndFlush(mMarathonBoostSchedule);

        // Get all the mMarathonBoostScheduleList where startAt equals to DEFAULT_START_AT
        defaultMMarathonBoostScheduleShouldBeFound("startAt.equals=" + DEFAULT_START_AT);

        // Get all the mMarathonBoostScheduleList where startAt equals to UPDATED_START_AT
        defaultMMarathonBoostScheduleShouldNotBeFound("startAt.equals=" + UPDATED_START_AT);
    }

    @Test
    @Transactional
    public void getAllMMarathonBoostSchedulesByStartAtIsInShouldWork() throws Exception {
        // Initialize the database
        mMarathonBoostScheduleRepository.saveAndFlush(mMarathonBoostSchedule);

        // Get all the mMarathonBoostScheduleList where startAt in DEFAULT_START_AT or UPDATED_START_AT
        defaultMMarathonBoostScheduleShouldBeFound("startAt.in=" + DEFAULT_START_AT + "," + UPDATED_START_AT);

        // Get all the mMarathonBoostScheduleList where startAt equals to UPDATED_START_AT
        defaultMMarathonBoostScheduleShouldNotBeFound("startAt.in=" + UPDATED_START_AT);
    }

    @Test
    @Transactional
    public void getAllMMarathonBoostSchedulesByStartAtIsNullOrNotNull() throws Exception {
        // Initialize the database
        mMarathonBoostScheduleRepository.saveAndFlush(mMarathonBoostSchedule);

        // Get all the mMarathonBoostScheduleList where startAt is not null
        defaultMMarathonBoostScheduleShouldBeFound("startAt.specified=true");

        // Get all the mMarathonBoostScheduleList where startAt is null
        defaultMMarathonBoostScheduleShouldNotBeFound("startAt.specified=false");
    }

    @Test
    @Transactional
    public void getAllMMarathonBoostSchedulesByStartAtIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mMarathonBoostScheduleRepository.saveAndFlush(mMarathonBoostSchedule);

        // Get all the mMarathonBoostScheduleList where startAt greater than or equals to DEFAULT_START_AT
        defaultMMarathonBoostScheduleShouldBeFound("startAt.greaterOrEqualThan=" + DEFAULT_START_AT);

        // Get all the mMarathonBoostScheduleList where startAt greater than or equals to UPDATED_START_AT
        defaultMMarathonBoostScheduleShouldNotBeFound("startAt.greaterOrEqualThan=" + UPDATED_START_AT);
    }

    @Test
    @Transactional
    public void getAllMMarathonBoostSchedulesByStartAtIsLessThanSomething() throws Exception {
        // Initialize the database
        mMarathonBoostScheduleRepository.saveAndFlush(mMarathonBoostSchedule);

        // Get all the mMarathonBoostScheduleList where startAt less than or equals to DEFAULT_START_AT
        defaultMMarathonBoostScheduleShouldNotBeFound("startAt.lessThan=" + DEFAULT_START_AT);

        // Get all the mMarathonBoostScheduleList where startAt less than or equals to UPDATED_START_AT
        defaultMMarathonBoostScheduleShouldBeFound("startAt.lessThan=" + UPDATED_START_AT);
    }


    @Test
    @Transactional
    public void getAllMMarathonBoostSchedulesByEndAtIsEqualToSomething() throws Exception {
        // Initialize the database
        mMarathonBoostScheduleRepository.saveAndFlush(mMarathonBoostSchedule);

        // Get all the mMarathonBoostScheduleList where endAt equals to DEFAULT_END_AT
        defaultMMarathonBoostScheduleShouldBeFound("endAt.equals=" + DEFAULT_END_AT);

        // Get all the mMarathonBoostScheduleList where endAt equals to UPDATED_END_AT
        defaultMMarathonBoostScheduleShouldNotBeFound("endAt.equals=" + UPDATED_END_AT);
    }

    @Test
    @Transactional
    public void getAllMMarathonBoostSchedulesByEndAtIsInShouldWork() throws Exception {
        // Initialize the database
        mMarathonBoostScheduleRepository.saveAndFlush(mMarathonBoostSchedule);

        // Get all the mMarathonBoostScheduleList where endAt in DEFAULT_END_AT or UPDATED_END_AT
        defaultMMarathonBoostScheduleShouldBeFound("endAt.in=" + DEFAULT_END_AT + "," + UPDATED_END_AT);

        // Get all the mMarathonBoostScheduleList where endAt equals to UPDATED_END_AT
        defaultMMarathonBoostScheduleShouldNotBeFound("endAt.in=" + UPDATED_END_AT);
    }

    @Test
    @Transactional
    public void getAllMMarathonBoostSchedulesByEndAtIsNullOrNotNull() throws Exception {
        // Initialize the database
        mMarathonBoostScheduleRepository.saveAndFlush(mMarathonBoostSchedule);

        // Get all the mMarathonBoostScheduleList where endAt is not null
        defaultMMarathonBoostScheduleShouldBeFound("endAt.specified=true");

        // Get all the mMarathonBoostScheduleList where endAt is null
        defaultMMarathonBoostScheduleShouldNotBeFound("endAt.specified=false");
    }

    @Test
    @Transactional
    public void getAllMMarathonBoostSchedulesByEndAtIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mMarathonBoostScheduleRepository.saveAndFlush(mMarathonBoostSchedule);

        // Get all the mMarathonBoostScheduleList where endAt greater than or equals to DEFAULT_END_AT
        defaultMMarathonBoostScheduleShouldBeFound("endAt.greaterOrEqualThan=" + DEFAULT_END_AT);

        // Get all the mMarathonBoostScheduleList where endAt greater than or equals to UPDATED_END_AT
        defaultMMarathonBoostScheduleShouldNotBeFound("endAt.greaterOrEqualThan=" + UPDATED_END_AT);
    }

    @Test
    @Transactional
    public void getAllMMarathonBoostSchedulesByEndAtIsLessThanSomething() throws Exception {
        // Initialize the database
        mMarathonBoostScheduleRepository.saveAndFlush(mMarathonBoostSchedule);

        // Get all the mMarathonBoostScheduleList where endAt less than or equals to DEFAULT_END_AT
        defaultMMarathonBoostScheduleShouldNotBeFound("endAt.lessThan=" + DEFAULT_END_AT);

        // Get all the mMarathonBoostScheduleList where endAt less than or equals to UPDATED_END_AT
        defaultMMarathonBoostScheduleShouldBeFound("endAt.lessThan=" + UPDATED_END_AT);
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultMMarathonBoostScheduleShouldBeFound(String filter) throws Exception {
        restMMarathonBoostScheduleMockMvc.perform(get("/api/m-marathon-boost-schedules?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(mMarathonBoostSchedule.getId().intValue())))
            .andExpect(jsonPath("$.[*].eventId").value(hasItem(DEFAULT_EVENT_ID)))
            .andExpect(jsonPath("$.[*].boostRatio").value(hasItem(DEFAULT_BOOST_RATIO)))
            .andExpect(jsonPath("$.[*].startAt").value(hasItem(DEFAULT_START_AT)))
            .andExpect(jsonPath("$.[*].endAt").value(hasItem(DEFAULT_END_AT)));

        // Check, that the count call also returns 1
        restMMarathonBoostScheduleMockMvc.perform(get("/api/m-marathon-boost-schedules/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultMMarathonBoostScheduleShouldNotBeFound(String filter) throws Exception {
        restMMarathonBoostScheduleMockMvc.perform(get("/api/m-marathon-boost-schedules?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restMMarathonBoostScheduleMockMvc.perform(get("/api/m-marathon-boost-schedules/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("0"));
    }


    @Test
    @Transactional
    public void getNonExistingMMarathonBoostSchedule() throws Exception {
        // Get the mMarathonBoostSchedule
        restMMarathonBoostScheduleMockMvc.perform(get("/api/m-marathon-boost-schedules/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateMMarathonBoostSchedule() throws Exception {
        // Initialize the database
        mMarathonBoostScheduleRepository.saveAndFlush(mMarathonBoostSchedule);

        int databaseSizeBeforeUpdate = mMarathonBoostScheduleRepository.findAll().size();

        // Update the mMarathonBoostSchedule
        MMarathonBoostSchedule updatedMMarathonBoostSchedule = mMarathonBoostScheduleRepository.findById(mMarathonBoostSchedule.getId()).get();
        // Disconnect from session so that the updates on updatedMMarathonBoostSchedule are not directly saved in db
        em.detach(updatedMMarathonBoostSchedule);
        updatedMMarathonBoostSchedule
            .eventId(UPDATED_EVENT_ID)
            .boostRatio(UPDATED_BOOST_RATIO)
            .startAt(UPDATED_START_AT)
            .endAt(UPDATED_END_AT);
        MMarathonBoostScheduleDTO mMarathonBoostScheduleDTO = mMarathonBoostScheduleMapper.toDto(updatedMMarathonBoostSchedule);

        restMMarathonBoostScheduleMockMvc.perform(put("/api/m-marathon-boost-schedules")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mMarathonBoostScheduleDTO)))
            .andExpect(status().isOk());

        // Validate the MMarathonBoostSchedule in the database
        List<MMarathonBoostSchedule> mMarathonBoostScheduleList = mMarathonBoostScheduleRepository.findAll();
        assertThat(mMarathonBoostScheduleList).hasSize(databaseSizeBeforeUpdate);
        MMarathonBoostSchedule testMMarathonBoostSchedule = mMarathonBoostScheduleList.get(mMarathonBoostScheduleList.size() - 1);
        assertThat(testMMarathonBoostSchedule.getEventId()).isEqualTo(UPDATED_EVENT_ID);
        assertThat(testMMarathonBoostSchedule.getBoostRatio()).isEqualTo(UPDATED_BOOST_RATIO);
        assertThat(testMMarathonBoostSchedule.getStartAt()).isEqualTo(UPDATED_START_AT);
        assertThat(testMMarathonBoostSchedule.getEndAt()).isEqualTo(UPDATED_END_AT);
    }

    @Test
    @Transactional
    public void updateNonExistingMMarathonBoostSchedule() throws Exception {
        int databaseSizeBeforeUpdate = mMarathonBoostScheduleRepository.findAll().size();

        // Create the MMarathonBoostSchedule
        MMarathonBoostScheduleDTO mMarathonBoostScheduleDTO = mMarathonBoostScheduleMapper.toDto(mMarathonBoostSchedule);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restMMarathonBoostScheduleMockMvc.perform(put("/api/m-marathon-boost-schedules")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mMarathonBoostScheduleDTO)))
            .andExpect(status().isBadRequest());

        // Validate the MMarathonBoostSchedule in the database
        List<MMarathonBoostSchedule> mMarathonBoostScheduleList = mMarathonBoostScheduleRepository.findAll();
        assertThat(mMarathonBoostScheduleList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteMMarathonBoostSchedule() throws Exception {
        // Initialize the database
        mMarathonBoostScheduleRepository.saveAndFlush(mMarathonBoostSchedule);

        int databaseSizeBeforeDelete = mMarathonBoostScheduleRepository.findAll().size();

        // Delete the mMarathonBoostSchedule
        restMMarathonBoostScheduleMockMvc.perform(delete("/api/m-marathon-boost-schedules/{id}", mMarathonBoostSchedule.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database is empty
        List<MMarathonBoostSchedule> mMarathonBoostScheduleList = mMarathonBoostScheduleRepository.findAll();
        assertThat(mMarathonBoostScheduleList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(MMarathonBoostSchedule.class);
        MMarathonBoostSchedule mMarathonBoostSchedule1 = new MMarathonBoostSchedule();
        mMarathonBoostSchedule1.setId(1L);
        MMarathonBoostSchedule mMarathonBoostSchedule2 = new MMarathonBoostSchedule();
        mMarathonBoostSchedule2.setId(mMarathonBoostSchedule1.getId());
        assertThat(mMarathonBoostSchedule1).isEqualTo(mMarathonBoostSchedule2);
        mMarathonBoostSchedule2.setId(2L);
        assertThat(mMarathonBoostSchedule1).isNotEqualTo(mMarathonBoostSchedule2);
        mMarathonBoostSchedule1.setId(null);
        assertThat(mMarathonBoostSchedule1).isNotEqualTo(mMarathonBoostSchedule2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(MMarathonBoostScheduleDTO.class);
        MMarathonBoostScheduleDTO mMarathonBoostScheduleDTO1 = new MMarathonBoostScheduleDTO();
        mMarathonBoostScheduleDTO1.setId(1L);
        MMarathonBoostScheduleDTO mMarathonBoostScheduleDTO2 = new MMarathonBoostScheduleDTO();
        assertThat(mMarathonBoostScheduleDTO1).isNotEqualTo(mMarathonBoostScheduleDTO2);
        mMarathonBoostScheduleDTO2.setId(mMarathonBoostScheduleDTO1.getId());
        assertThat(mMarathonBoostScheduleDTO1).isEqualTo(mMarathonBoostScheduleDTO2);
        mMarathonBoostScheduleDTO2.setId(2L);
        assertThat(mMarathonBoostScheduleDTO1).isNotEqualTo(mMarathonBoostScheduleDTO2);
        mMarathonBoostScheduleDTO1.setId(null);
        assertThat(mMarathonBoostScheduleDTO1).isNotEqualTo(mMarathonBoostScheduleDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(mMarathonBoostScheduleMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(mMarathonBoostScheduleMapper.fromId(null)).isNull();
    }
}
