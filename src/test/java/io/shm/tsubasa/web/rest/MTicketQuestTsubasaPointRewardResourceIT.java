package io.shm.tsubasa.web.rest;

import io.shm.tsubasa.TsubasaApp;
import io.shm.tsubasa.domain.MTicketQuestTsubasaPointReward;
import io.shm.tsubasa.repository.MTicketQuestTsubasaPointRewardRepository;
import io.shm.tsubasa.service.MTicketQuestTsubasaPointRewardService;
import io.shm.tsubasa.service.dto.MTicketQuestTsubasaPointRewardDTO;
import io.shm.tsubasa.service.mapper.MTicketQuestTsubasaPointRewardMapper;
import io.shm.tsubasa.web.rest.errors.ExceptionTranslator;
import io.shm.tsubasa.service.dto.MTicketQuestTsubasaPointRewardCriteria;
import io.shm.tsubasa.service.MTicketQuestTsubasaPointRewardQueryService;

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
 * Integration tests for the {@Link MTicketQuestTsubasaPointRewardResource} REST controller.
 */
@SpringBootTest(classes = TsubasaApp.class)
public class MTicketQuestTsubasaPointRewardResourceIT {

    private static final Integer DEFAULT_STAGE_ID = 1;
    private static final Integer UPDATED_STAGE_ID = 2;

    private static final Integer DEFAULT_TSUBASA_POINT = 1;
    private static final Integer UPDATED_TSUBASA_POINT = 2;

    private static final Integer DEFAULT_CONTENT_TYPE = 1;
    private static final Integer UPDATED_CONTENT_TYPE = 2;

    private static final Integer DEFAULT_CONTENT_ID = 1;
    private static final Integer UPDATED_CONTENT_ID = 2;

    private static final Integer DEFAULT_CONTENT_AMOUNT = 1;
    private static final Integer UPDATED_CONTENT_AMOUNT = 2;

    @Autowired
    private MTicketQuestTsubasaPointRewardRepository mTicketQuestTsubasaPointRewardRepository;

    @Autowired
    private MTicketQuestTsubasaPointRewardMapper mTicketQuestTsubasaPointRewardMapper;

    @Autowired
    private MTicketQuestTsubasaPointRewardService mTicketQuestTsubasaPointRewardService;

    @Autowired
    private MTicketQuestTsubasaPointRewardQueryService mTicketQuestTsubasaPointRewardQueryService;

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

    private MockMvc restMTicketQuestTsubasaPointRewardMockMvc;

    private MTicketQuestTsubasaPointReward mTicketQuestTsubasaPointReward;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final MTicketQuestTsubasaPointRewardResource mTicketQuestTsubasaPointRewardResource = new MTicketQuestTsubasaPointRewardResource(mTicketQuestTsubasaPointRewardService, mTicketQuestTsubasaPointRewardQueryService);
        this.restMTicketQuestTsubasaPointRewardMockMvc = MockMvcBuilders.standaloneSetup(mTicketQuestTsubasaPointRewardResource)
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
    public static MTicketQuestTsubasaPointReward createEntity(EntityManager em) {
        MTicketQuestTsubasaPointReward mTicketQuestTsubasaPointReward = new MTicketQuestTsubasaPointReward()
            .stageId(DEFAULT_STAGE_ID)
            .tsubasaPoint(DEFAULT_TSUBASA_POINT)
            .contentType(DEFAULT_CONTENT_TYPE)
            .contentId(DEFAULT_CONTENT_ID)
            .contentAmount(DEFAULT_CONTENT_AMOUNT);
        return mTicketQuestTsubasaPointReward;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static MTicketQuestTsubasaPointReward createUpdatedEntity(EntityManager em) {
        MTicketQuestTsubasaPointReward mTicketQuestTsubasaPointReward = new MTicketQuestTsubasaPointReward()
            .stageId(UPDATED_STAGE_ID)
            .tsubasaPoint(UPDATED_TSUBASA_POINT)
            .contentType(UPDATED_CONTENT_TYPE)
            .contentId(UPDATED_CONTENT_ID)
            .contentAmount(UPDATED_CONTENT_AMOUNT);
        return mTicketQuestTsubasaPointReward;
    }

    @BeforeEach
    public void initTest() {
        mTicketQuestTsubasaPointReward = createEntity(em);
    }

    @Test
    @Transactional
    public void createMTicketQuestTsubasaPointReward() throws Exception {
        int databaseSizeBeforeCreate = mTicketQuestTsubasaPointRewardRepository.findAll().size();

        // Create the MTicketQuestTsubasaPointReward
        MTicketQuestTsubasaPointRewardDTO mTicketQuestTsubasaPointRewardDTO = mTicketQuestTsubasaPointRewardMapper.toDto(mTicketQuestTsubasaPointReward);
        restMTicketQuestTsubasaPointRewardMockMvc.perform(post("/api/m-ticket-quest-tsubasa-point-rewards")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mTicketQuestTsubasaPointRewardDTO)))
            .andExpect(status().isCreated());

        // Validate the MTicketQuestTsubasaPointReward in the database
        List<MTicketQuestTsubasaPointReward> mTicketQuestTsubasaPointRewardList = mTicketQuestTsubasaPointRewardRepository.findAll();
        assertThat(mTicketQuestTsubasaPointRewardList).hasSize(databaseSizeBeforeCreate + 1);
        MTicketQuestTsubasaPointReward testMTicketQuestTsubasaPointReward = mTicketQuestTsubasaPointRewardList.get(mTicketQuestTsubasaPointRewardList.size() - 1);
        assertThat(testMTicketQuestTsubasaPointReward.getStageId()).isEqualTo(DEFAULT_STAGE_ID);
        assertThat(testMTicketQuestTsubasaPointReward.getTsubasaPoint()).isEqualTo(DEFAULT_TSUBASA_POINT);
        assertThat(testMTicketQuestTsubasaPointReward.getContentType()).isEqualTo(DEFAULT_CONTENT_TYPE);
        assertThat(testMTicketQuestTsubasaPointReward.getContentId()).isEqualTo(DEFAULT_CONTENT_ID);
        assertThat(testMTicketQuestTsubasaPointReward.getContentAmount()).isEqualTo(DEFAULT_CONTENT_AMOUNT);
    }

    @Test
    @Transactional
    public void createMTicketQuestTsubasaPointRewardWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = mTicketQuestTsubasaPointRewardRepository.findAll().size();

        // Create the MTicketQuestTsubasaPointReward with an existing ID
        mTicketQuestTsubasaPointReward.setId(1L);
        MTicketQuestTsubasaPointRewardDTO mTicketQuestTsubasaPointRewardDTO = mTicketQuestTsubasaPointRewardMapper.toDto(mTicketQuestTsubasaPointReward);

        // An entity with an existing ID cannot be created, so this API call must fail
        restMTicketQuestTsubasaPointRewardMockMvc.perform(post("/api/m-ticket-quest-tsubasa-point-rewards")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mTicketQuestTsubasaPointRewardDTO)))
            .andExpect(status().isBadRequest());

        // Validate the MTicketQuestTsubasaPointReward in the database
        List<MTicketQuestTsubasaPointReward> mTicketQuestTsubasaPointRewardList = mTicketQuestTsubasaPointRewardRepository.findAll();
        assertThat(mTicketQuestTsubasaPointRewardList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkStageIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = mTicketQuestTsubasaPointRewardRepository.findAll().size();
        // set the field null
        mTicketQuestTsubasaPointReward.setStageId(null);

        // Create the MTicketQuestTsubasaPointReward, which fails.
        MTicketQuestTsubasaPointRewardDTO mTicketQuestTsubasaPointRewardDTO = mTicketQuestTsubasaPointRewardMapper.toDto(mTicketQuestTsubasaPointReward);

        restMTicketQuestTsubasaPointRewardMockMvc.perform(post("/api/m-ticket-quest-tsubasa-point-rewards")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mTicketQuestTsubasaPointRewardDTO)))
            .andExpect(status().isBadRequest());

        List<MTicketQuestTsubasaPointReward> mTicketQuestTsubasaPointRewardList = mTicketQuestTsubasaPointRewardRepository.findAll();
        assertThat(mTicketQuestTsubasaPointRewardList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkTsubasaPointIsRequired() throws Exception {
        int databaseSizeBeforeTest = mTicketQuestTsubasaPointRewardRepository.findAll().size();
        // set the field null
        mTicketQuestTsubasaPointReward.setTsubasaPoint(null);

        // Create the MTicketQuestTsubasaPointReward, which fails.
        MTicketQuestTsubasaPointRewardDTO mTicketQuestTsubasaPointRewardDTO = mTicketQuestTsubasaPointRewardMapper.toDto(mTicketQuestTsubasaPointReward);

        restMTicketQuestTsubasaPointRewardMockMvc.perform(post("/api/m-ticket-quest-tsubasa-point-rewards")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mTicketQuestTsubasaPointRewardDTO)))
            .andExpect(status().isBadRequest());

        List<MTicketQuestTsubasaPointReward> mTicketQuestTsubasaPointRewardList = mTicketQuestTsubasaPointRewardRepository.findAll();
        assertThat(mTicketQuestTsubasaPointRewardList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkContentTypeIsRequired() throws Exception {
        int databaseSizeBeforeTest = mTicketQuestTsubasaPointRewardRepository.findAll().size();
        // set the field null
        mTicketQuestTsubasaPointReward.setContentType(null);

        // Create the MTicketQuestTsubasaPointReward, which fails.
        MTicketQuestTsubasaPointRewardDTO mTicketQuestTsubasaPointRewardDTO = mTicketQuestTsubasaPointRewardMapper.toDto(mTicketQuestTsubasaPointReward);

        restMTicketQuestTsubasaPointRewardMockMvc.perform(post("/api/m-ticket-quest-tsubasa-point-rewards")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mTicketQuestTsubasaPointRewardDTO)))
            .andExpect(status().isBadRequest());

        List<MTicketQuestTsubasaPointReward> mTicketQuestTsubasaPointRewardList = mTicketQuestTsubasaPointRewardRepository.findAll();
        assertThat(mTicketQuestTsubasaPointRewardList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkContentAmountIsRequired() throws Exception {
        int databaseSizeBeforeTest = mTicketQuestTsubasaPointRewardRepository.findAll().size();
        // set the field null
        mTicketQuestTsubasaPointReward.setContentAmount(null);

        // Create the MTicketQuestTsubasaPointReward, which fails.
        MTicketQuestTsubasaPointRewardDTO mTicketQuestTsubasaPointRewardDTO = mTicketQuestTsubasaPointRewardMapper.toDto(mTicketQuestTsubasaPointReward);

        restMTicketQuestTsubasaPointRewardMockMvc.perform(post("/api/m-ticket-quest-tsubasa-point-rewards")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mTicketQuestTsubasaPointRewardDTO)))
            .andExpect(status().isBadRequest());

        List<MTicketQuestTsubasaPointReward> mTicketQuestTsubasaPointRewardList = mTicketQuestTsubasaPointRewardRepository.findAll();
        assertThat(mTicketQuestTsubasaPointRewardList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllMTicketQuestTsubasaPointRewards() throws Exception {
        // Initialize the database
        mTicketQuestTsubasaPointRewardRepository.saveAndFlush(mTicketQuestTsubasaPointReward);

        // Get all the mTicketQuestTsubasaPointRewardList
        restMTicketQuestTsubasaPointRewardMockMvc.perform(get("/api/m-ticket-quest-tsubasa-point-rewards?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(mTicketQuestTsubasaPointReward.getId().intValue())))
            .andExpect(jsonPath("$.[*].stageId").value(hasItem(DEFAULT_STAGE_ID)))
            .andExpect(jsonPath("$.[*].tsubasaPoint").value(hasItem(DEFAULT_TSUBASA_POINT)))
            .andExpect(jsonPath("$.[*].contentType").value(hasItem(DEFAULT_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].contentId").value(hasItem(DEFAULT_CONTENT_ID)))
            .andExpect(jsonPath("$.[*].contentAmount").value(hasItem(DEFAULT_CONTENT_AMOUNT)));
    }
    
    @Test
    @Transactional
    public void getMTicketQuestTsubasaPointReward() throws Exception {
        // Initialize the database
        mTicketQuestTsubasaPointRewardRepository.saveAndFlush(mTicketQuestTsubasaPointReward);

        // Get the mTicketQuestTsubasaPointReward
        restMTicketQuestTsubasaPointRewardMockMvc.perform(get("/api/m-ticket-quest-tsubasa-point-rewards/{id}", mTicketQuestTsubasaPointReward.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(mTicketQuestTsubasaPointReward.getId().intValue()))
            .andExpect(jsonPath("$.stageId").value(DEFAULT_STAGE_ID))
            .andExpect(jsonPath("$.tsubasaPoint").value(DEFAULT_TSUBASA_POINT))
            .andExpect(jsonPath("$.contentType").value(DEFAULT_CONTENT_TYPE))
            .andExpect(jsonPath("$.contentId").value(DEFAULT_CONTENT_ID))
            .andExpect(jsonPath("$.contentAmount").value(DEFAULT_CONTENT_AMOUNT));
    }

    @Test
    @Transactional
    public void getAllMTicketQuestTsubasaPointRewardsByStageIdIsEqualToSomething() throws Exception {
        // Initialize the database
        mTicketQuestTsubasaPointRewardRepository.saveAndFlush(mTicketQuestTsubasaPointReward);

        // Get all the mTicketQuestTsubasaPointRewardList where stageId equals to DEFAULT_STAGE_ID
        defaultMTicketQuestTsubasaPointRewardShouldBeFound("stageId.equals=" + DEFAULT_STAGE_ID);

        // Get all the mTicketQuestTsubasaPointRewardList where stageId equals to UPDATED_STAGE_ID
        defaultMTicketQuestTsubasaPointRewardShouldNotBeFound("stageId.equals=" + UPDATED_STAGE_ID);
    }

    @Test
    @Transactional
    public void getAllMTicketQuestTsubasaPointRewardsByStageIdIsInShouldWork() throws Exception {
        // Initialize the database
        mTicketQuestTsubasaPointRewardRepository.saveAndFlush(mTicketQuestTsubasaPointReward);

        // Get all the mTicketQuestTsubasaPointRewardList where stageId in DEFAULT_STAGE_ID or UPDATED_STAGE_ID
        defaultMTicketQuestTsubasaPointRewardShouldBeFound("stageId.in=" + DEFAULT_STAGE_ID + "," + UPDATED_STAGE_ID);

        // Get all the mTicketQuestTsubasaPointRewardList where stageId equals to UPDATED_STAGE_ID
        defaultMTicketQuestTsubasaPointRewardShouldNotBeFound("stageId.in=" + UPDATED_STAGE_ID);
    }

    @Test
    @Transactional
    public void getAllMTicketQuestTsubasaPointRewardsByStageIdIsNullOrNotNull() throws Exception {
        // Initialize the database
        mTicketQuestTsubasaPointRewardRepository.saveAndFlush(mTicketQuestTsubasaPointReward);

        // Get all the mTicketQuestTsubasaPointRewardList where stageId is not null
        defaultMTicketQuestTsubasaPointRewardShouldBeFound("stageId.specified=true");

        // Get all the mTicketQuestTsubasaPointRewardList where stageId is null
        defaultMTicketQuestTsubasaPointRewardShouldNotBeFound("stageId.specified=false");
    }

    @Test
    @Transactional
    public void getAllMTicketQuestTsubasaPointRewardsByStageIdIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mTicketQuestTsubasaPointRewardRepository.saveAndFlush(mTicketQuestTsubasaPointReward);

        // Get all the mTicketQuestTsubasaPointRewardList where stageId greater than or equals to DEFAULT_STAGE_ID
        defaultMTicketQuestTsubasaPointRewardShouldBeFound("stageId.greaterOrEqualThan=" + DEFAULT_STAGE_ID);

        // Get all the mTicketQuestTsubasaPointRewardList where stageId greater than or equals to UPDATED_STAGE_ID
        defaultMTicketQuestTsubasaPointRewardShouldNotBeFound("stageId.greaterOrEqualThan=" + UPDATED_STAGE_ID);
    }

    @Test
    @Transactional
    public void getAllMTicketQuestTsubasaPointRewardsByStageIdIsLessThanSomething() throws Exception {
        // Initialize the database
        mTicketQuestTsubasaPointRewardRepository.saveAndFlush(mTicketQuestTsubasaPointReward);

        // Get all the mTicketQuestTsubasaPointRewardList where stageId less than or equals to DEFAULT_STAGE_ID
        defaultMTicketQuestTsubasaPointRewardShouldNotBeFound("stageId.lessThan=" + DEFAULT_STAGE_ID);

        // Get all the mTicketQuestTsubasaPointRewardList where stageId less than or equals to UPDATED_STAGE_ID
        defaultMTicketQuestTsubasaPointRewardShouldBeFound("stageId.lessThan=" + UPDATED_STAGE_ID);
    }


    @Test
    @Transactional
    public void getAllMTicketQuestTsubasaPointRewardsByTsubasaPointIsEqualToSomething() throws Exception {
        // Initialize the database
        mTicketQuestTsubasaPointRewardRepository.saveAndFlush(mTicketQuestTsubasaPointReward);

        // Get all the mTicketQuestTsubasaPointRewardList where tsubasaPoint equals to DEFAULT_TSUBASA_POINT
        defaultMTicketQuestTsubasaPointRewardShouldBeFound("tsubasaPoint.equals=" + DEFAULT_TSUBASA_POINT);

        // Get all the mTicketQuestTsubasaPointRewardList where tsubasaPoint equals to UPDATED_TSUBASA_POINT
        defaultMTicketQuestTsubasaPointRewardShouldNotBeFound("tsubasaPoint.equals=" + UPDATED_TSUBASA_POINT);
    }

    @Test
    @Transactional
    public void getAllMTicketQuestTsubasaPointRewardsByTsubasaPointIsInShouldWork() throws Exception {
        // Initialize the database
        mTicketQuestTsubasaPointRewardRepository.saveAndFlush(mTicketQuestTsubasaPointReward);

        // Get all the mTicketQuestTsubasaPointRewardList where tsubasaPoint in DEFAULT_TSUBASA_POINT or UPDATED_TSUBASA_POINT
        defaultMTicketQuestTsubasaPointRewardShouldBeFound("tsubasaPoint.in=" + DEFAULT_TSUBASA_POINT + "," + UPDATED_TSUBASA_POINT);

        // Get all the mTicketQuestTsubasaPointRewardList where tsubasaPoint equals to UPDATED_TSUBASA_POINT
        defaultMTicketQuestTsubasaPointRewardShouldNotBeFound("tsubasaPoint.in=" + UPDATED_TSUBASA_POINT);
    }

    @Test
    @Transactional
    public void getAllMTicketQuestTsubasaPointRewardsByTsubasaPointIsNullOrNotNull() throws Exception {
        // Initialize the database
        mTicketQuestTsubasaPointRewardRepository.saveAndFlush(mTicketQuestTsubasaPointReward);

        // Get all the mTicketQuestTsubasaPointRewardList where tsubasaPoint is not null
        defaultMTicketQuestTsubasaPointRewardShouldBeFound("tsubasaPoint.specified=true");

        // Get all the mTicketQuestTsubasaPointRewardList where tsubasaPoint is null
        defaultMTicketQuestTsubasaPointRewardShouldNotBeFound("tsubasaPoint.specified=false");
    }

    @Test
    @Transactional
    public void getAllMTicketQuestTsubasaPointRewardsByTsubasaPointIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mTicketQuestTsubasaPointRewardRepository.saveAndFlush(mTicketQuestTsubasaPointReward);

        // Get all the mTicketQuestTsubasaPointRewardList where tsubasaPoint greater than or equals to DEFAULT_TSUBASA_POINT
        defaultMTicketQuestTsubasaPointRewardShouldBeFound("tsubasaPoint.greaterOrEqualThan=" + DEFAULT_TSUBASA_POINT);

        // Get all the mTicketQuestTsubasaPointRewardList where tsubasaPoint greater than or equals to UPDATED_TSUBASA_POINT
        defaultMTicketQuestTsubasaPointRewardShouldNotBeFound("tsubasaPoint.greaterOrEqualThan=" + UPDATED_TSUBASA_POINT);
    }

    @Test
    @Transactional
    public void getAllMTicketQuestTsubasaPointRewardsByTsubasaPointIsLessThanSomething() throws Exception {
        // Initialize the database
        mTicketQuestTsubasaPointRewardRepository.saveAndFlush(mTicketQuestTsubasaPointReward);

        // Get all the mTicketQuestTsubasaPointRewardList where tsubasaPoint less than or equals to DEFAULT_TSUBASA_POINT
        defaultMTicketQuestTsubasaPointRewardShouldNotBeFound("tsubasaPoint.lessThan=" + DEFAULT_TSUBASA_POINT);

        // Get all the mTicketQuestTsubasaPointRewardList where tsubasaPoint less than or equals to UPDATED_TSUBASA_POINT
        defaultMTicketQuestTsubasaPointRewardShouldBeFound("tsubasaPoint.lessThan=" + UPDATED_TSUBASA_POINT);
    }


    @Test
    @Transactional
    public void getAllMTicketQuestTsubasaPointRewardsByContentTypeIsEqualToSomething() throws Exception {
        // Initialize the database
        mTicketQuestTsubasaPointRewardRepository.saveAndFlush(mTicketQuestTsubasaPointReward);

        // Get all the mTicketQuestTsubasaPointRewardList where contentType equals to DEFAULT_CONTENT_TYPE
        defaultMTicketQuestTsubasaPointRewardShouldBeFound("contentType.equals=" + DEFAULT_CONTENT_TYPE);

        // Get all the mTicketQuestTsubasaPointRewardList where contentType equals to UPDATED_CONTENT_TYPE
        defaultMTicketQuestTsubasaPointRewardShouldNotBeFound("contentType.equals=" + UPDATED_CONTENT_TYPE);
    }

    @Test
    @Transactional
    public void getAllMTicketQuestTsubasaPointRewardsByContentTypeIsInShouldWork() throws Exception {
        // Initialize the database
        mTicketQuestTsubasaPointRewardRepository.saveAndFlush(mTicketQuestTsubasaPointReward);

        // Get all the mTicketQuestTsubasaPointRewardList where contentType in DEFAULT_CONTENT_TYPE or UPDATED_CONTENT_TYPE
        defaultMTicketQuestTsubasaPointRewardShouldBeFound("contentType.in=" + DEFAULT_CONTENT_TYPE + "," + UPDATED_CONTENT_TYPE);

        // Get all the mTicketQuestTsubasaPointRewardList where contentType equals to UPDATED_CONTENT_TYPE
        defaultMTicketQuestTsubasaPointRewardShouldNotBeFound("contentType.in=" + UPDATED_CONTENT_TYPE);
    }

    @Test
    @Transactional
    public void getAllMTicketQuestTsubasaPointRewardsByContentTypeIsNullOrNotNull() throws Exception {
        // Initialize the database
        mTicketQuestTsubasaPointRewardRepository.saveAndFlush(mTicketQuestTsubasaPointReward);

        // Get all the mTicketQuestTsubasaPointRewardList where contentType is not null
        defaultMTicketQuestTsubasaPointRewardShouldBeFound("contentType.specified=true");

        // Get all the mTicketQuestTsubasaPointRewardList where contentType is null
        defaultMTicketQuestTsubasaPointRewardShouldNotBeFound("contentType.specified=false");
    }

    @Test
    @Transactional
    public void getAllMTicketQuestTsubasaPointRewardsByContentTypeIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mTicketQuestTsubasaPointRewardRepository.saveAndFlush(mTicketQuestTsubasaPointReward);

        // Get all the mTicketQuestTsubasaPointRewardList where contentType greater than or equals to DEFAULT_CONTENT_TYPE
        defaultMTicketQuestTsubasaPointRewardShouldBeFound("contentType.greaterOrEqualThan=" + DEFAULT_CONTENT_TYPE);

        // Get all the mTicketQuestTsubasaPointRewardList where contentType greater than or equals to UPDATED_CONTENT_TYPE
        defaultMTicketQuestTsubasaPointRewardShouldNotBeFound("contentType.greaterOrEqualThan=" + UPDATED_CONTENT_TYPE);
    }

    @Test
    @Transactional
    public void getAllMTicketQuestTsubasaPointRewardsByContentTypeIsLessThanSomething() throws Exception {
        // Initialize the database
        mTicketQuestTsubasaPointRewardRepository.saveAndFlush(mTicketQuestTsubasaPointReward);

        // Get all the mTicketQuestTsubasaPointRewardList where contentType less than or equals to DEFAULT_CONTENT_TYPE
        defaultMTicketQuestTsubasaPointRewardShouldNotBeFound("contentType.lessThan=" + DEFAULT_CONTENT_TYPE);

        // Get all the mTicketQuestTsubasaPointRewardList where contentType less than or equals to UPDATED_CONTENT_TYPE
        defaultMTicketQuestTsubasaPointRewardShouldBeFound("contentType.lessThan=" + UPDATED_CONTENT_TYPE);
    }


    @Test
    @Transactional
    public void getAllMTicketQuestTsubasaPointRewardsByContentIdIsEqualToSomething() throws Exception {
        // Initialize the database
        mTicketQuestTsubasaPointRewardRepository.saveAndFlush(mTicketQuestTsubasaPointReward);

        // Get all the mTicketQuestTsubasaPointRewardList where contentId equals to DEFAULT_CONTENT_ID
        defaultMTicketQuestTsubasaPointRewardShouldBeFound("contentId.equals=" + DEFAULT_CONTENT_ID);

        // Get all the mTicketQuestTsubasaPointRewardList where contentId equals to UPDATED_CONTENT_ID
        defaultMTicketQuestTsubasaPointRewardShouldNotBeFound("contentId.equals=" + UPDATED_CONTENT_ID);
    }

    @Test
    @Transactional
    public void getAllMTicketQuestTsubasaPointRewardsByContentIdIsInShouldWork() throws Exception {
        // Initialize the database
        mTicketQuestTsubasaPointRewardRepository.saveAndFlush(mTicketQuestTsubasaPointReward);

        // Get all the mTicketQuestTsubasaPointRewardList where contentId in DEFAULT_CONTENT_ID or UPDATED_CONTENT_ID
        defaultMTicketQuestTsubasaPointRewardShouldBeFound("contentId.in=" + DEFAULT_CONTENT_ID + "," + UPDATED_CONTENT_ID);

        // Get all the mTicketQuestTsubasaPointRewardList where contentId equals to UPDATED_CONTENT_ID
        defaultMTicketQuestTsubasaPointRewardShouldNotBeFound("contentId.in=" + UPDATED_CONTENT_ID);
    }

    @Test
    @Transactional
    public void getAllMTicketQuestTsubasaPointRewardsByContentIdIsNullOrNotNull() throws Exception {
        // Initialize the database
        mTicketQuestTsubasaPointRewardRepository.saveAndFlush(mTicketQuestTsubasaPointReward);

        // Get all the mTicketQuestTsubasaPointRewardList where contentId is not null
        defaultMTicketQuestTsubasaPointRewardShouldBeFound("contentId.specified=true");

        // Get all the mTicketQuestTsubasaPointRewardList where contentId is null
        defaultMTicketQuestTsubasaPointRewardShouldNotBeFound("contentId.specified=false");
    }

    @Test
    @Transactional
    public void getAllMTicketQuestTsubasaPointRewardsByContentIdIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mTicketQuestTsubasaPointRewardRepository.saveAndFlush(mTicketQuestTsubasaPointReward);

        // Get all the mTicketQuestTsubasaPointRewardList where contentId greater than or equals to DEFAULT_CONTENT_ID
        defaultMTicketQuestTsubasaPointRewardShouldBeFound("contentId.greaterOrEqualThan=" + DEFAULT_CONTENT_ID);

        // Get all the mTicketQuestTsubasaPointRewardList where contentId greater than or equals to UPDATED_CONTENT_ID
        defaultMTicketQuestTsubasaPointRewardShouldNotBeFound("contentId.greaterOrEqualThan=" + UPDATED_CONTENT_ID);
    }

    @Test
    @Transactional
    public void getAllMTicketQuestTsubasaPointRewardsByContentIdIsLessThanSomething() throws Exception {
        // Initialize the database
        mTicketQuestTsubasaPointRewardRepository.saveAndFlush(mTicketQuestTsubasaPointReward);

        // Get all the mTicketQuestTsubasaPointRewardList where contentId less than or equals to DEFAULT_CONTENT_ID
        defaultMTicketQuestTsubasaPointRewardShouldNotBeFound("contentId.lessThan=" + DEFAULT_CONTENT_ID);

        // Get all the mTicketQuestTsubasaPointRewardList where contentId less than or equals to UPDATED_CONTENT_ID
        defaultMTicketQuestTsubasaPointRewardShouldBeFound("contentId.lessThan=" + UPDATED_CONTENT_ID);
    }


    @Test
    @Transactional
    public void getAllMTicketQuestTsubasaPointRewardsByContentAmountIsEqualToSomething() throws Exception {
        // Initialize the database
        mTicketQuestTsubasaPointRewardRepository.saveAndFlush(mTicketQuestTsubasaPointReward);

        // Get all the mTicketQuestTsubasaPointRewardList where contentAmount equals to DEFAULT_CONTENT_AMOUNT
        defaultMTicketQuestTsubasaPointRewardShouldBeFound("contentAmount.equals=" + DEFAULT_CONTENT_AMOUNT);

        // Get all the mTicketQuestTsubasaPointRewardList where contentAmount equals to UPDATED_CONTENT_AMOUNT
        defaultMTicketQuestTsubasaPointRewardShouldNotBeFound("contentAmount.equals=" + UPDATED_CONTENT_AMOUNT);
    }

    @Test
    @Transactional
    public void getAllMTicketQuestTsubasaPointRewardsByContentAmountIsInShouldWork() throws Exception {
        // Initialize the database
        mTicketQuestTsubasaPointRewardRepository.saveAndFlush(mTicketQuestTsubasaPointReward);

        // Get all the mTicketQuestTsubasaPointRewardList where contentAmount in DEFAULT_CONTENT_AMOUNT or UPDATED_CONTENT_AMOUNT
        defaultMTicketQuestTsubasaPointRewardShouldBeFound("contentAmount.in=" + DEFAULT_CONTENT_AMOUNT + "," + UPDATED_CONTENT_AMOUNT);

        // Get all the mTicketQuestTsubasaPointRewardList where contentAmount equals to UPDATED_CONTENT_AMOUNT
        defaultMTicketQuestTsubasaPointRewardShouldNotBeFound("contentAmount.in=" + UPDATED_CONTENT_AMOUNT);
    }

    @Test
    @Transactional
    public void getAllMTicketQuestTsubasaPointRewardsByContentAmountIsNullOrNotNull() throws Exception {
        // Initialize the database
        mTicketQuestTsubasaPointRewardRepository.saveAndFlush(mTicketQuestTsubasaPointReward);

        // Get all the mTicketQuestTsubasaPointRewardList where contentAmount is not null
        defaultMTicketQuestTsubasaPointRewardShouldBeFound("contentAmount.specified=true");

        // Get all the mTicketQuestTsubasaPointRewardList where contentAmount is null
        defaultMTicketQuestTsubasaPointRewardShouldNotBeFound("contentAmount.specified=false");
    }

    @Test
    @Transactional
    public void getAllMTicketQuestTsubasaPointRewardsByContentAmountIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mTicketQuestTsubasaPointRewardRepository.saveAndFlush(mTicketQuestTsubasaPointReward);

        // Get all the mTicketQuestTsubasaPointRewardList where contentAmount greater than or equals to DEFAULT_CONTENT_AMOUNT
        defaultMTicketQuestTsubasaPointRewardShouldBeFound("contentAmount.greaterOrEqualThan=" + DEFAULT_CONTENT_AMOUNT);

        // Get all the mTicketQuestTsubasaPointRewardList where contentAmount greater than or equals to UPDATED_CONTENT_AMOUNT
        defaultMTicketQuestTsubasaPointRewardShouldNotBeFound("contentAmount.greaterOrEqualThan=" + UPDATED_CONTENT_AMOUNT);
    }

    @Test
    @Transactional
    public void getAllMTicketQuestTsubasaPointRewardsByContentAmountIsLessThanSomething() throws Exception {
        // Initialize the database
        mTicketQuestTsubasaPointRewardRepository.saveAndFlush(mTicketQuestTsubasaPointReward);

        // Get all the mTicketQuestTsubasaPointRewardList where contentAmount less than or equals to DEFAULT_CONTENT_AMOUNT
        defaultMTicketQuestTsubasaPointRewardShouldNotBeFound("contentAmount.lessThan=" + DEFAULT_CONTENT_AMOUNT);

        // Get all the mTicketQuestTsubasaPointRewardList where contentAmount less than or equals to UPDATED_CONTENT_AMOUNT
        defaultMTicketQuestTsubasaPointRewardShouldBeFound("contentAmount.lessThan=" + UPDATED_CONTENT_AMOUNT);
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultMTicketQuestTsubasaPointRewardShouldBeFound(String filter) throws Exception {
        restMTicketQuestTsubasaPointRewardMockMvc.perform(get("/api/m-ticket-quest-tsubasa-point-rewards?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(mTicketQuestTsubasaPointReward.getId().intValue())))
            .andExpect(jsonPath("$.[*].stageId").value(hasItem(DEFAULT_STAGE_ID)))
            .andExpect(jsonPath("$.[*].tsubasaPoint").value(hasItem(DEFAULT_TSUBASA_POINT)))
            .andExpect(jsonPath("$.[*].contentType").value(hasItem(DEFAULT_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].contentId").value(hasItem(DEFAULT_CONTENT_ID)))
            .andExpect(jsonPath("$.[*].contentAmount").value(hasItem(DEFAULT_CONTENT_AMOUNT)));

        // Check, that the count call also returns 1
        restMTicketQuestTsubasaPointRewardMockMvc.perform(get("/api/m-ticket-quest-tsubasa-point-rewards/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultMTicketQuestTsubasaPointRewardShouldNotBeFound(String filter) throws Exception {
        restMTicketQuestTsubasaPointRewardMockMvc.perform(get("/api/m-ticket-quest-tsubasa-point-rewards?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restMTicketQuestTsubasaPointRewardMockMvc.perform(get("/api/m-ticket-quest-tsubasa-point-rewards/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("0"));
    }


    @Test
    @Transactional
    public void getNonExistingMTicketQuestTsubasaPointReward() throws Exception {
        // Get the mTicketQuestTsubasaPointReward
        restMTicketQuestTsubasaPointRewardMockMvc.perform(get("/api/m-ticket-quest-tsubasa-point-rewards/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateMTicketQuestTsubasaPointReward() throws Exception {
        // Initialize the database
        mTicketQuestTsubasaPointRewardRepository.saveAndFlush(mTicketQuestTsubasaPointReward);

        int databaseSizeBeforeUpdate = mTicketQuestTsubasaPointRewardRepository.findAll().size();

        // Update the mTicketQuestTsubasaPointReward
        MTicketQuestTsubasaPointReward updatedMTicketQuestTsubasaPointReward = mTicketQuestTsubasaPointRewardRepository.findById(mTicketQuestTsubasaPointReward.getId()).get();
        // Disconnect from session so that the updates on updatedMTicketQuestTsubasaPointReward are not directly saved in db
        em.detach(updatedMTicketQuestTsubasaPointReward);
        updatedMTicketQuestTsubasaPointReward
            .stageId(UPDATED_STAGE_ID)
            .tsubasaPoint(UPDATED_TSUBASA_POINT)
            .contentType(UPDATED_CONTENT_TYPE)
            .contentId(UPDATED_CONTENT_ID)
            .contentAmount(UPDATED_CONTENT_AMOUNT);
        MTicketQuestTsubasaPointRewardDTO mTicketQuestTsubasaPointRewardDTO = mTicketQuestTsubasaPointRewardMapper.toDto(updatedMTicketQuestTsubasaPointReward);

        restMTicketQuestTsubasaPointRewardMockMvc.perform(put("/api/m-ticket-quest-tsubasa-point-rewards")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mTicketQuestTsubasaPointRewardDTO)))
            .andExpect(status().isOk());

        // Validate the MTicketQuestTsubasaPointReward in the database
        List<MTicketQuestTsubasaPointReward> mTicketQuestTsubasaPointRewardList = mTicketQuestTsubasaPointRewardRepository.findAll();
        assertThat(mTicketQuestTsubasaPointRewardList).hasSize(databaseSizeBeforeUpdate);
        MTicketQuestTsubasaPointReward testMTicketQuestTsubasaPointReward = mTicketQuestTsubasaPointRewardList.get(mTicketQuestTsubasaPointRewardList.size() - 1);
        assertThat(testMTicketQuestTsubasaPointReward.getStageId()).isEqualTo(UPDATED_STAGE_ID);
        assertThat(testMTicketQuestTsubasaPointReward.getTsubasaPoint()).isEqualTo(UPDATED_TSUBASA_POINT);
        assertThat(testMTicketQuestTsubasaPointReward.getContentType()).isEqualTo(UPDATED_CONTENT_TYPE);
        assertThat(testMTicketQuestTsubasaPointReward.getContentId()).isEqualTo(UPDATED_CONTENT_ID);
        assertThat(testMTicketQuestTsubasaPointReward.getContentAmount()).isEqualTo(UPDATED_CONTENT_AMOUNT);
    }

    @Test
    @Transactional
    public void updateNonExistingMTicketQuestTsubasaPointReward() throws Exception {
        int databaseSizeBeforeUpdate = mTicketQuestTsubasaPointRewardRepository.findAll().size();

        // Create the MTicketQuestTsubasaPointReward
        MTicketQuestTsubasaPointRewardDTO mTicketQuestTsubasaPointRewardDTO = mTicketQuestTsubasaPointRewardMapper.toDto(mTicketQuestTsubasaPointReward);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restMTicketQuestTsubasaPointRewardMockMvc.perform(put("/api/m-ticket-quest-tsubasa-point-rewards")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mTicketQuestTsubasaPointRewardDTO)))
            .andExpect(status().isBadRequest());

        // Validate the MTicketQuestTsubasaPointReward in the database
        List<MTicketQuestTsubasaPointReward> mTicketQuestTsubasaPointRewardList = mTicketQuestTsubasaPointRewardRepository.findAll();
        assertThat(mTicketQuestTsubasaPointRewardList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteMTicketQuestTsubasaPointReward() throws Exception {
        // Initialize the database
        mTicketQuestTsubasaPointRewardRepository.saveAndFlush(mTicketQuestTsubasaPointReward);

        int databaseSizeBeforeDelete = mTicketQuestTsubasaPointRewardRepository.findAll().size();

        // Delete the mTicketQuestTsubasaPointReward
        restMTicketQuestTsubasaPointRewardMockMvc.perform(delete("/api/m-ticket-quest-tsubasa-point-rewards/{id}", mTicketQuestTsubasaPointReward.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database is empty
        List<MTicketQuestTsubasaPointReward> mTicketQuestTsubasaPointRewardList = mTicketQuestTsubasaPointRewardRepository.findAll();
        assertThat(mTicketQuestTsubasaPointRewardList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(MTicketQuestTsubasaPointReward.class);
        MTicketQuestTsubasaPointReward mTicketQuestTsubasaPointReward1 = new MTicketQuestTsubasaPointReward();
        mTicketQuestTsubasaPointReward1.setId(1L);
        MTicketQuestTsubasaPointReward mTicketQuestTsubasaPointReward2 = new MTicketQuestTsubasaPointReward();
        mTicketQuestTsubasaPointReward2.setId(mTicketQuestTsubasaPointReward1.getId());
        assertThat(mTicketQuestTsubasaPointReward1).isEqualTo(mTicketQuestTsubasaPointReward2);
        mTicketQuestTsubasaPointReward2.setId(2L);
        assertThat(mTicketQuestTsubasaPointReward1).isNotEqualTo(mTicketQuestTsubasaPointReward2);
        mTicketQuestTsubasaPointReward1.setId(null);
        assertThat(mTicketQuestTsubasaPointReward1).isNotEqualTo(mTicketQuestTsubasaPointReward2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(MTicketQuestTsubasaPointRewardDTO.class);
        MTicketQuestTsubasaPointRewardDTO mTicketQuestTsubasaPointRewardDTO1 = new MTicketQuestTsubasaPointRewardDTO();
        mTicketQuestTsubasaPointRewardDTO1.setId(1L);
        MTicketQuestTsubasaPointRewardDTO mTicketQuestTsubasaPointRewardDTO2 = new MTicketQuestTsubasaPointRewardDTO();
        assertThat(mTicketQuestTsubasaPointRewardDTO1).isNotEqualTo(mTicketQuestTsubasaPointRewardDTO2);
        mTicketQuestTsubasaPointRewardDTO2.setId(mTicketQuestTsubasaPointRewardDTO1.getId());
        assertThat(mTicketQuestTsubasaPointRewardDTO1).isEqualTo(mTicketQuestTsubasaPointRewardDTO2);
        mTicketQuestTsubasaPointRewardDTO2.setId(2L);
        assertThat(mTicketQuestTsubasaPointRewardDTO1).isNotEqualTo(mTicketQuestTsubasaPointRewardDTO2);
        mTicketQuestTsubasaPointRewardDTO1.setId(null);
        assertThat(mTicketQuestTsubasaPointRewardDTO1).isNotEqualTo(mTicketQuestTsubasaPointRewardDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(mTicketQuestTsubasaPointRewardMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(mTicketQuestTsubasaPointRewardMapper.fromId(null)).isNull();
    }
}
