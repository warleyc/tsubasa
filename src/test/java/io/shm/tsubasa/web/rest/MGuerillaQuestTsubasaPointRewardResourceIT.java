package io.shm.tsubasa.web.rest;

import io.shm.tsubasa.TsubasaApp;
import io.shm.tsubasa.domain.MGuerillaQuestTsubasaPointReward;
import io.shm.tsubasa.repository.MGuerillaQuestTsubasaPointRewardRepository;
import io.shm.tsubasa.service.MGuerillaQuestTsubasaPointRewardService;
import io.shm.tsubasa.service.dto.MGuerillaQuestTsubasaPointRewardDTO;
import io.shm.tsubasa.service.mapper.MGuerillaQuestTsubasaPointRewardMapper;
import io.shm.tsubasa.web.rest.errors.ExceptionTranslator;
import io.shm.tsubasa.service.dto.MGuerillaQuestTsubasaPointRewardCriteria;
import io.shm.tsubasa.service.MGuerillaQuestTsubasaPointRewardQueryService;

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
 * Integration tests for the {@Link MGuerillaQuestTsubasaPointRewardResource} REST controller.
 */
@SpringBootTest(classes = TsubasaApp.class)
public class MGuerillaQuestTsubasaPointRewardResourceIT {

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
    private MGuerillaQuestTsubasaPointRewardRepository mGuerillaQuestTsubasaPointRewardRepository;

    @Autowired
    private MGuerillaQuestTsubasaPointRewardMapper mGuerillaQuestTsubasaPointRewardMapper;

    @Autowired
    private MGuerillaQuestTsubasaPointRewardService mGuerillaQuestTsubasaPointRewardService;

    @Autowired
    private MGuerillaQuestTsubasaPointRewardQueryService mGuerillaQuestTsubasaPointRewardQueryService;

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

    private MockMvc restMGuerillaQuestTsubasaPointRewardMockMvc;

    private MGuerillaQuestTsubasaPointReward mGuerillaQuestTsubasaPointReward;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final MGuerillaQuestTsubasaPointRewardResource mGuerillaQuestTsubasaPointRewardResource = new MGuerillaQuestTsubasaPointRewardResource(mGuerillaQuestTsubasaPointRewardService, mGuerillaQuestTsubasaPointRewardQueryService);
        this.restMGuerillaQuestTsubasaPointRewardMockMvc = MockMvcBuilders.standaloneSetup(mGuerillaQuestTsubasaPointRewardResource)
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
    public static MGuerillaQuestTsubasaPointReward createEntity(EntityManager em) {
        MGuerillaQuestTsubasaPointReward mGuerillaQuestTsubasaPointReward = new MGuerillaQuestTsubasaPointReward()
            .stageId(DEFAULT_STAGE_ID)
            .tsubasaPoint(DEFAULT_TSUBASA_POINT)
            .contentType(DEFAULT_CONTENT_TYPE)
            .contentId(DEFAULT_CONTENT_ID)
            .contentAmount(DEFAULT_CONTENT_AMOUNT);
        return mGuerillaQuestTsubasaPointReward;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static MGuerillaQuestTsubasaPointReward createUpdatedEntity(EntityManager em) {
        MGuerillaQuestTsubasaPointReward mGuerillaQuestTsubasaPointReward = new MGuerillaQuestTsubasaPointReward()
            .stageId(UPDATED_STAGE_ID)
            .tsubasaPoint(UPDATED_TSUBASA_POINT)
            .contentType(UPDATED_CONTENT_TYPE)
            .contentId(UPDATED_CONTENT_ID)
            .contentAmount(UPDATED_CONTENT_AMOUNT);
        return mGuerillaQuestTsubasaPointReward;
    }

    @BeforeEach
    public void initTest() {
        mGuerillaQuestTsubasaPointReward = createEntity(em);
    }

    @Test
    @Transactional
    public void createMGuerillaQuestTsubasaPointReward() throws Exception {
        int databaseSizeBeforeCreate = mGuerillaQuestTsubasaPointRewardRepository.findAll().size();

        // Create the MGuerillaQuestTsubasaPointReward
        MGuerillaQuestTsubasaPointRewardDTO mGuerillaQuestTsubasaPointRewardDTO = mGuerillaQuestTsubasaPointRewardMapper.toDto(mGuerillaQuestTsubasaPointReward);
        restMGuerillaQuestTsubasaPointRewardMockMvc.perform(post("/api/m-guerilla-quest-tsubasa-point-rewards")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mGuerillaQuestTsubasaPointRewardDTO)))
            .andExpect(status().isCreated());

        // Validate the MGuerillaQuestTsubasaPointReward in the database
        List<MGuerillaQuestTsubasaPointReward> mGuerillaQuestTsubasaPointRewardList = mGuerillaQuestTsubasaPointRewardRepository.findAll();
        assertThat(mGuerillaQuestTsubasaPointRewardList).hasSize(databaseSizeBeforeCreate + 1);
        MGuerillaQuestTsubasaPointReward testMGuerillaQuestTsubasaPointReward = mGuerillaQuestTsubasaPointRewardList.get(mGuerillaQuestTsubasaPointRewardList.size() - 1);
        assertThat(testMGuerillaQuestTsubasaPointReward.getStageId()).isEqualTo(DEFAULT_STAGE_ID);
        assertThat(testMGuerillaQuestTsubasaPointReward.getTsubasaPoint()).isEqualTo(DEFAULT_TSUBASA_POINT);
        assertThat(testMGuerillaQuestTsubasaPointReward.getContentType()).isEqualTo(DEFAULT_CONTENT_TYPE);
        assertThat(testMGuerillaQuestTsubasaPointReward.getContentId()).isEqualTo(DEFAULT_CONTENT_ID);
        assertThat(testMGuerillaQuestTsubasaPointReward.getContentAmount()).isEqualTo(DEFAULT_CONTENT_AMOUNT);
    }

    @Test
    @Transactional
    public void createMGuerillaQuestTsubasaPointRewardWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = mGuerillaQuestTsubasaPointRewardRepository.findAll().size();

        // Create the MGuerillaQuestTsubasaPointReward with an existing ID
        mGuerillaQuestTsubasaPointReward.setId(1L);
        MGuerillaQuestTsubasaPointRewardDTO mGuerillaQuestTsubasaPointRewardDTO = mGuerillaQuestTsubasaPointRewardMapper.toDto(mGuerillaQuestTsubasaPointReward);

        // An entity with an existing ID cannot be created, so this API call must fail
        restMGuerillaQuestTsubasaPointRewardMockMvc.perform(post("/api/m-guerilla-quest-tsubasa-point-rewards")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mGuerillaQuestTsubasaPointRewardDTO)))
            .andExpect(status().isBadRequest());

        // Validate the MGuerillaQuestTsubasaPointReward in the database
        List<MGuerillaQuestTsubasaPointReward> mGuerillaQuestTsubasaPointRewardList = mGuerillaQuestTsubasaPointRewardRepository.findAll();
        assertThat(mGuerillaQuestTsubasaPointRewardList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkStageIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = mGuerillaQuestTsubasaPointRewardRepository.findAll().size();
        // set the field null
        mGuerillaQuestTsubasaPointReward.setStageId(null);

        // Create the MGuerillaQuestTsubasaPointReward, which fails.
        MGuerillaQuestTsubasaPointRewardDTO mGuerillaQuestTsubasaPointRewardDTO = mGuerillaQuestTsubasaPointRewardMapper.toDto(mGuerillaQuestTsubasaPointReward);

        restMGuerillaQuestTsubasaPointRewardMockMvc.perform(post("/api/m-guerilla-quest-tsubasa-point-rewards")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mGuerillaQuestTsubasaPointRewardDTO)))
            .andExpect(status().isBadRequest());

        List<MGuerillaQuestTsubasaPointReward> mGuerillaQuestTsubasaPointRewardList = mGuerillaQuestTsubasaPointRewardRepository.findAll();
        assertThat(mGuerillaQuestTsubasaPointRewardList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkTsubasaPointIsRequired() throws Exception {
        int databaseSizeBeforeTest = mGuerillaQuestTsubasaPointRewardRepository.findAll().size();
        // set the field null
        mGuerillaQuestTsubasaPointReward.setTsubasaPoint(null);

        // Create the MGuerillaQuestTsubasaPointReward, which fails.
        MGuerillaQuestTsubasaPointRewardDTO mGuerillaQuestTsubasaPointRewardDTO = mGuerillaQuestTsubasaPointRewardMapper.toDto(mGuerillaQuestTsubasaPointReward);

        restMGuerillaQuestTsubasaPointRewardMockMvc.perform(post("/api/m-guerilla-quest-tsubasa-point-rewards")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mGuerillaQuestTsubasaPointRewardDTO)))
            .andExpect(status().isBadRequest());

        List<MGuerillaQuestTsubasaPointReward> mGuerillaQuestTsubasaPointRewardList = mGuerillaQuestTsubasaPointRewardRepository.findAll();
        assertThat(mGuerillaQuestTsubasaPointRewardList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkContentTypeIsRequired() throws Exception {
        int databaseSizeBeforeTest = mGuerillaQuestTsubasaPointRewardRepository.findAll().size();
        // set the field null
        mGuerillaQuestTsubasaPointReward.setContentType(null);

        // Create the MGuerillaQuestTsubasaPointReward, which fails.
        MGuerillaQuestTsubasaPointRewardDTO mGuerillaQuestTsubasaPointRewardDTO = mGuerillaQuestTsubasaPointRewardMapper.toDto(mGuerillaQuestTsubasaPointReward);

        restMGuerillaQuestTsubasaPointRewardMockMvc.perform(post("/api/m-guerilla-quest-tsubasa-point-rewards")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mGuerillaQuestTsubasaPointRewardDTO)))
            .andExpect(status().isBadRequest());

        List<MGuerillaQuestTsubasaPointReward> mGuerillaQuestTsubasaPointRewardList = mGuerillaQuestTsubasaPointRewardRepository.findAll();
        assertThat(mGuerillaQuestTsubasaPointRewardList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkContentAmountIsRequired() throws Exception {
        int databaseSizeBeforeTest = mGuerillaQuestTsubasaPointRewardRepository.findAll().size();
        // set the field null
        mGuerillaQuestTsubasaPointReward.setContentAmount(null);

        // Create the MGuerillaQuestTsubasaPointReward, which fails.
        MGuerillaQuestTsubasaPointRewardDTO mGuerillaQuestTsubasaPointRewardDTO = mGuerillaQuestTsubasaPointRewardMapper.toDto(mGuerillaQuestTsubasaPointReward);

        restMGuerillaQuestTsubasaPointRewardMockMvc.perform(post("/api/m-guerilla-quest-tsubasa-point-rewards")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mGuerillaQuestTsubasaPointRewardDTO)))
            .andExpect(status().isBadRequest());

        List<MGuerillaQuestTsubasaPointReward> mGuerillaQuestTsubasaPointRewardList = mGuerillaQuestTsubasaPointRewardRepository.findAll();
        assertThat(mGuerillaQuestTsubasaPointRewardList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllMGuerillaQuestTsubasaPointRewards() throws Exception {
        // Initialize the database
        mGuerillaQuestTsubasaPointRewardRepository.saveAndFlush(mGuerillaQuestTsubasaPointReward);

        // Get all the mGuerillaQuestTsubasaPointRewardList
        restMGuerillaQuestTsubasaPointRewardMockMvc.perform(get("/api/m-guerilla-quest-tsubasa-point-rewards?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(mGuerillaQuestTsubasaPointReward.getId().intValue())))
            .andExpect(jsonPath("$.[*].stageId").value(hasItem(DEFAULT_STAGE_ID)))
            .andExpect(jsonPath("$.[*].tsubasaPoint").value(hasItem(DEFAULT_TSUBASA_POINT)))
            .andExpect(jsonPath("$.[*].contentType").value(hasItem(DEFAULT_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].contentId").value(hasItem(DEFAULT_CONTENT_ID)))
            .andExpect(jsonPath("$.[*].contentAmount").value(hasItem(DEFAULT_CONTENT_AMOUNT)));
    }
    
    @Test
    @Transactional
    public void getMGuerillaQuestTsubasaPointReward() throws Exception {
        // Initialize the database
        mGuerillaQuestTsubasaPointRewardRepository.saveAndFlush(mGuerillaQuestTsubasaPointReward);

        // Get the mGuerillaQuestTsubasaPointReward
        restMGuerillaQuestTsubasaPointRewardMockMvc.perform(get("/api/m-guerilla-quest-tsubasa-point-rewards/{id}", mGuerillaQuestTsubasaPointReward.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(mGuerillaQuestTsubasaPointReward.getId().intValue()))
            .andExpect(jsonPath("$.stageId").value(DEFAULT_STAGE_ID))
            .andExpect(jsonPath("$.tsubasaPoint").value(DEFAULT_TSUBASA_POINT))
            .andExpect(jsonPath("$.contentType").value(DEFAULT_CONTENT_TYPE))
            .andExpect(jsonPath("$.contentId").value(DEFAULT_CONTENT_ID))
            .andExpect(jsonPath("$.contentAmount").value(DEFAULT_CONTENT_AMOUNT));
    }

    @Test
    @Transactional
    public void getAllMGuerillaQuestTsubasaPointRewardsByStageIdIsEqualToSomething() throws Exception {
        // Initialize the database
        mGuerillaQuestTsubasaPointRewardRepository.saveAndFlush(mGuerillaQuestTsubasaPointReward);

        // Get all the mGuerillaQuestTsubasaPointRewardList where stageId equals to DEFAULT_STAGE_ID
        defaultMGuerillaQuestTsubasaPointRewardShouldBeFound("stageId.equals=" + DEFAULT_STAGE_ID);

        // Get all the mGuerillaQuestTsubasaPointRewardList where stageId equals to UPDATED_STAGE_ID
        defaultMGuerillaQuestTsubasaPointRewardShouldNotBeFound("stageId.equals=" + UPDATED_STAGE_ID);
    }

    @Test
    @Transactional
    public void getAllMGuerillaQuestTsubasaPointRewardsByStageIdIsInShouldWork() throws Exception {
        // Initialize the database
        mGuerillaQuestTsubasaPointRewardRepository.saveAndFlush(mGuerillaQuestTsubasaPointReward);

        // Get all the mGuerillaQuestTsubasaPointRewardList where stageId in DEFAULT_STAGE_ID or UPDATED_STAGE_ID
        defaultMGuerillaQuestTsubasaPointRewardShouldBeFound("stageId.in=" + DEFAULT_STAGE_ID + "," + UPDATED_STAGE_ID);

        // Get all the mGuerillaQuestTsubasaPointRewardList where stageId equals to UPDATED_STAGE_ID
        defaultMGuerillaQuestTsubasaPointRewardShouldNotBeFound("stageId.in=" + UPDATED_STAGE_ID);
    }

    @Test
    @Transactional
    public void getAllMGuerillaQuestTsubasaPointRewardsByStageIdIsNullOrNotNull() throws Exception {
        // Initialize the database
        mGuerillaQuestTsubasaPointRewardRepository.saveAndFlush(mGuerillaQuestTsubasaPointReward);

        // Get all the mGuerillaQuestTsubasaPointRewardList where stageId is not null
        defaultMGuerillaQuestTsubasaPointRewardShouldBeFound("stageId.specified=true");

        // Get all the mGuerillaQuestTsubasaPointRewardList where stageId is null
        defaultMGuerillaQuestTsubasaPointRewardShouldNotBeFound("stageId.specified=false");
    }

    @Test
    @Transactional
    public void getAllMGuerillaQuestTsubasaPointRewardsByStageIdIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mGuerillaQuestTsubasaPointRewardRepository.saveAndFlush(mGuerillaQuestTsubasaPointReward);

        // Get all the mGuerillaQuestTsubasaPointRewardList where stageId greater than or equals to DEFAULT_STAGE_ID
        defaultMGuerillaQuestTsubasaPointRewardShouldBeFound("stageId.greaterOrEqualThan=" + DEFAULT_STAGE_ID);

        // Get all the mGuerillaQuestTsubasaPointRewardList where stageId greater than or equals to UPDATED_STAGE_ID
        defaultMGuerillaQuestTsubasaPointRewardShouldNotBeFound("stageId.greaterOrEqualThan=" + UPDATED_STAGE_ID);
    }

    @Test
    @Transactional
    public void getAllMGuerillaQuestTsubasaPointRewardsByStageIdIsLessThanSomething() throws Exception {
        // Initialize the database
        mGuerillaQuestTsubasaPointRewardRepository.saveAndFlush(mGuerillaQuestTsubasaPointReward);

        // Get all the mGuerillaQuestTsubasaPointRewardList where stageId less than or equals to DEFAULT_STAGE_ID
        defaultMGuerillaQuestTsubasaPointRewardShouldNotBeFound("stageId.lessThan=" + DEFAULT_STAGE_ID);

        // Get all the mGuerillaQuestTsubasaPointRewardList where stageId less than or equals to UPDATED_STAGE_ID
        defaultMGuerillaQuestTsubasaPointRewardShouldBeFound("stageId.lessThan=" + UPDATED_STAGE_ID);
    }


    @Test
    @Transactional
    public void getAllMGuerillaQuestTsubasaPointRewardsByTsubasaPointIsEqualToSomething() throws Exception {
        // Initialize the database
        mGuerillaQuestTsubasaPointRewardRepository.saveAndFlush(mGuerillaQuestTsubasaPointReward);

        // Get all the mGuerillaQuestTsubasaPointRewardList where tsubasaPoint equals to DEFAULT_TSUBASA_POINT
        defaultMGuerillaQuestTsubasaPointRewardShouldBeFound("tsubasaPoint.equals=" + DEFAULT_TSUBASA_POINT);

        // Get all the mGuerillaQuestTsubasaPointRewardList where tsubasaPoint equals to UPDATED_TSUBASA_POINT
        defaultMGuerillaQuestTsubasaPointRewardShouldNotBeFound("tsubasaPoint.equals=" + UPDATED_TSUBASA_POINT);
    }

    @Test
    @Transactional
    public void getAllMGuerillaQuestTsubasaPointRewardsByTsubasaPointIsInShouldWork() throws Exception {
        // Initialize the database
        mGuerillaQuestTsubasaPointRewardRepository.saveAndFlush(mGuerillaQuestTsubasaPointReward);

        // Get all the mGuerillaQuestTsubasaPointRewardList where tsubasaPoint in DEFAULT_TSUBASA_POINT or UPDATED_TSUBASA_POINT
        defaultMGuerillaQuestTsubasaPointRewardShouldBeFound("tsubasaPoint.in=" + DEFAULT_TSUBASA_POINT + "," + UPDATED_TSUBASA_POINT);

        // Get all the mGuerillaQuestTsubasaPointRewardList where tsubasaPoint equals to UPDATED_TSUBASA_POINT
        defaultMGuerillaQuestTsubasaPointRewardShouldNotBeFound("tsubasaPoint.in=" + UPDATED_TSUBASA_POINT);
    }

    @Test
    @Transactional
    public void getAllMGuerillaQuestTsubasaPointRewardsByTsubasaPointIsNullOrNotNull() throws Exception {
        // Initialize the database
        mGuerillaQuestTsubasaPointRewardRepository.saveAndFlush(mGuerillaQuestTsubasaPointReward);

        // Get all the mGuerillaQuestTsubasaPointRewardList where tsubasaPoint is not null
        defaultMGuerillaQuestTsubasaPointRewardShouldBeFound("tsubasaPoint.specified=true");

        // Get all the mGuerillaQuestTsubasaPointRewardList where tsubasaPoint is null
        defaultMGuerillaQuestTsubasaPointRewardShouldNotBeFound("tsubasaPoint.specified=false");
    }

    @Test
    @Transactional
    public void getAllMGuerillaQuestTsubasaPointRewardsByTsubasaPointIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mGuerillaQuestTsubasaPointRewardRepository.saveAndFlush(mGuerillaQuestTsubasaPointReward);

        // Get all the mGuerillaQuestTsubasaPointRewardList where tsubasaPoint greater than or equals to DEFAULT_TSUBASA_POINT
        defaultMGuerillaQuestTsubasaPointRewardShouldBeFound("tsubasaPoint.greaterOrEqualThan=" + DEFAULT_TSUBASA_POINT);

        // Get all the mGuerillaQuestTsubasaPointRewardList where tsubasaPoint greater than or equals to UPDATED_TSUBASA_POINT
        defaultMGuerillaQuestTsubasaPointRewardShouldNotBeFound("tsubasaPoint.greaterOrEqualThan=" + UPDATED_TSUBASA_POINT);
    }

    @Test
    @Transactional
    public void getAllMGuerillaQuestTsubasaPointRewardsByTsubasaPointIsLessThanSomething() throws Exception {
        // Initialize the database
        mGuerillaQuestTsubasaPointRewardRepository.saveAndFlush(mGuerillaQuestTsubasaPointReward);

        // Get all the mGuerillaQuestTsubasaPointRewardList where tsubasaPoint less than or equals to DEFAULT_TSUBASA_POINT
        defaultMGuerillaQuestTsubasaPointRewardShouldNotBeFound("tsubasaPoint.lessThan=" + DEFAULT_TSUBASA_POINT);

        // Get all the mGuerillaQuestTsubasaPointRewardList where tsubasaPoint less than or equals to UPDATED_TSUBASA_POINT
        defaultMGuerillaQuestTsubasaPointRewardShouldBeFound("tsubasaPoint.lessThan=" + UPDATED_TSUBASA_POINT);
    }


    @Test
    @Transactional
    public void getAllMGuerillaQuestTsubasaPointRewardsByContentTypeIsEqualToSomething() throws Exception {
        // Initialize the database
        mGuerillaQuestTsubasaPointRewardRepository.saveAndFlush(mGuerillaQuestTsubasaPointReward);

        // Get all the mGuerillaQuestTsubasaPointRewardList where contentType equals to DEFAULT_CONTENT_TYPE
        defaultMGuerillaQuestTsubasaPointRewardShouldBeFound("contentType.equals=" + DEFAULT_CONTENT_TYPE);

        // Get all the mGuerillaQuestTsubasaPointRewardList where contentType equals to UPDATED_CONTENT_TYPE
        defaultMGuerillaQuestTsubasaPointRewardShouldNotBeFound("contentType.equals=" + UPDATED_CONTENT_TYPE);
    }

    @Test
    @Transactional
    public void getAllMGuerillaQuestTsubasaPointRewardsByContentTypeIsInShouldWork() throws Exception {
        // Initialize the database
        mGuerillaQuestTsubasaPointRewardRepository.saveAndFlush(mGuerillaQuestTsubasaPointReward);

        // Get all the mGuerillaQuestTsubasaPointRewardList where contentType in DEFAULT_CONTENT_TYPE or UPDATED_CONTENT_TYPE
        defaultMGuerillaQuestTsubasaPointRewardShouldBeFound("contentType.in=" + DEFAULT_CONTENT_TYPE + "," + UPDATED_CONTENT_TYPE);

        // Get all the mGuerillaQuestTsubasaPointRewardList where contentType equals to UPDATED_CONTENT_TYPE
        defaultMGuerillaQuestTsubasaPointRewardShouldNotBeFound("contentType.in=" + UPDATED_CONTENT_TYPE);
    }

    @Test
    @Transactional
    public void getAllMGuerillaQuestTsubasaPointRewardsByContentTypeIsNullOrNotNull() throws Exception {
        // Initialize the database
        mGuerillaQuestTsubasaPointRewardRepository.saveAndFlush(mGuerillaQuestTsubasaPointReward);

        // Get all the mGuerillaQuestTsubasaPointRewardList where contentType is not null
        defaultMGuerillaQuestTsubasaPointRewardShouldBeFound("contentType.specified=true");

        // Get all the mGuerillaQuestTsubasaPointRewardList where contentType is null
        defaultMGuerillaQuestTsubasaPointRewardShouldNotBeFound("contentType.specified=false");
    }

    @Test
    @Transactional
    public void getAllMGuerillaQuestTsubasaPointRewardsByContentTypeIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mGuerillaQuestTsubasaPointRewardRepository.saveAndFlush(mGuerillaQuestTsubasaPointReward);

        // Get all the mGuerillaQuestTsubasaPointRewardList where contentType greater than or equals to DEFAULT_CONTENT_TYPE
        defaultMGuerillaQuestTsubasaPointRewardShouldBeFound("contentType.greaterOrEqualThan=" + DEFAULT_CONTENT_TYPE);

        // Get all the mGuerillaQuestTsubasaPointRewardList where contentType greater than or equals to UPDATED_CONTENT_TYPE
        defaultMGuerillaQuestTsubasaPointRewardShouldNotBeFound("contentType.greaterOrEqualThan=" + UPDATED_CONTENT_TYPE);
    }

    @Test
    @Transactional
    public void getAllMGuerillaQuestTsubasaPointRewardsByContentTypeIsLessThanSomething() throws Exception {
        // Initialize the database
        mGuerillaQuestTsubasaPointRewardRepository.saveAndFlush(mGuerillaQuestTsubasaPointReward);

        // Get all the mGuerillaQuestTsubasaPointRewardList where contentType less than or equals to DEFAULT_CONTENT_TYPE
        defaultMGuerillaQuestTsubasaPointRewardShouldNotBeFound("contentType.lessThan=" + DEFAULT_CONTENT_TYPE);

        // Get all the mGuerillaQuestTsubasaPointRewardList where contentType less than or equals to UPDATED_CONTENT_TYPE
        defaultMGuerillaQuestTsubasaPointRewardShouldBeFound("contentType.lessThan=" + UPDATED_CONTENT_TYPE);
    }


    @Test
    @Transactional
    public void getAllMGuerillaQuestTsubasaPointRewardsByContentIdIsEqualToSomething() throws Exception {
        // Initialize the database
        mGuerillaQuestTsubasaPointRewardRepository.saveAndFlush(mGuerillaQuestTsubasaPointReward);

        // Get all the mGuerillaQuestTsubasaPointRewardList where contentId equals to DEFAULT_CONTENT_ID
        defaultMGuerillaQuestTsubasaPointRewardShouldBeFound("contentId.equals=" + DEFAULT_CONTENT_ID);

        // Get all the mGuerillaQuestTsubasaPointRewardList where contentId equals to UPDATED_CONTENT_ID
        defaultMGuerillaQuestTsubasaPointRewardShouldNotBeFound("contentId.equals=" + UPDATED_CONTENT_ID);
    }

    @Test
    @Transactional
    public void getAllMGuerillaQuestTsubasaPointRewardsByContentIdIsInShouldWork() throws Exception {
        // Initialize the database
        mGuerillaQuestTsubasaPointRewardRepository.saveAndFlush(mGuerillaQuestTsubasaPointReward);

        // Get all the mGuerillaQuestTsubasaPointRewardList where contentId in DEFAULT_CONTENT_ID or UPDATED_CONTENT_ID
        defaultMGuerillaQuestTsubasaPointRewardShouldBeFound("contentId.in=" + DEFAULT_CONTENT_ID + "," + UPDATED_CONTENT_ID);

        // Get all the mGuerillaQuestTsubasaPointRewardList where contentId equals to UPDATED_CONTENT_ID
        defaultMGuerillaQuestTsubasaPointRewardShouldNotBeFound("contentId.in=" + UPDATED_CONTENT_ID);
    }

    @Test
    @Transactional
    public void getAllMGuerillaQuestTsubasaPointRewardsByContentIdIsNullOrNotNull() throws Exception {
        // Initialize the database
        mGuerillaQuestTsubasaPointRewardRepository.saveAndFlush(mGuerillaQuestTsubasaPointReward);

        // Get all the mGuerillaQuestTsubasaPointRewardList where contentId is not null
        defaultMGuerillaQuestTsubasaPointRewardShouldBeFound("contentId.specified=true");

        // Get all the mGuerillaQuestTsubasaPointRewardList where contentId is null
        defaultMGuerillaQuestTsubasaPointRewardShouldNotBeFound("contentId.specified=false");
    }

    @Test
    @Transactional
    public void getAllMGuerillaQuestTsubasaPointRewardsByContentIdIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mGuerillaQuestTsubasaPointRewardRepository.saveAndFlush(mGuerillaQuestTsubasaPointReward);

        // Get all the mGuerillaQuestTsubasaPointRewardList where contentId greater than or equals to DEFAULT_CONTENT_ID
        defaultMGuerillaQuestTsubasaPointRewardShouldBeFound("contentId.greaterOrEqualThan=" + DEFAULT_CONTENT_ID);

        // Get all the mGuerillaQuestTsubasaPointRewardList where contentId greater than or equals to UPDATED_CONTENT_ID
        defaultMGuerillaQuestTsubasaPointRewardShouldNotBeFound("contentId.greaterOrEqualThan=" + UPDATED_CONTENT_ID);
    }

    @Test
    @Transactional
    public void getAllMGuerillaQuestTsubasaPointRewardsByContentIdIsLessThanSomething() throws Exception {
        // Initialize the database
        mGuerillaQuestTsubasaPointRewardRepository.saveAndFlush(mGuerillaQuestTsubasaPointReward);

        // Get all the mGuerillaQuestTsubasaPointRewardList where contentId less than or equals to DEFAULT_CONTENT_ID
        defaultMGuerillaQuestTsubasaPointRewardShouldNotBeFound("contentId.lessThan=" + DEFAULT_CONTENT_ID);

        // Get all the mGuerillaQuestTsubasaPointRewardList where contentId less than or equals to UPDATED_CONTENT_ID
        defaultMGuerillaQuestTsubasaPointRewardShouldBeFound("contentId.lessThan=" + UPDATED_CONTENT_ID);
    }


    @Test
    @Transactional
    public void getAllMGuerillaQuestTsubasaPointRewardsByContentAmountIsEqualToSomething() throws Exception {
        // Initialize the database
        mGuerillaQuestTsubasaPointRewardRepository.saveAndFlush(mGuerillaQuestTsubasaPointReward);

        // Get all the mGuerillaQuestTsubasaPointRewardList where contentAmount equals to DEFAULT_CONTENT_AMOUNT
        defaultMGuerillaQuestTsubasaPointRewardShouldBeFound("contentAmount.equals=" + DEFAULT_CONTENT_AMOUNT);

        // Get all the mGuerillaQuestTsubasaPointRewardList where contentAmount equals to UPDATED_CONTENT_AMOUNT
        defaultMGuerillaQuestTsubasaPointRewardShouldNotBeFound("contentAmount.equals=" + UPDATED_CONTENT_AMOUNT);
    }

    @Test
    @Transactional
    public void getAllMGuerillaQuestTsubasaPointRewardsByContentAmountIsInShouldWork() throws Exception {
        // Initialize the database
        mGuerillaQuestTsubasaPointRewardRepository.saveAndFlush(mGuerillaQuestTsubasaPointReward);

        // Get all the mGuerillaQuestTsubasaPointRewardList where contentAmount in DEFAULT_CONTENT_AMOUNT or UPDATED_CONTENT_AMOUNT
        defaultMGuerillaQuestTsubasaPointRewardShouldBeFound("contentAmount.in=" + DEFAULT_CONTENT_AMOUNT + "," + UPDATED_CONTENT_AMOUNT);

        // Get all the mGuerillaQuestTsubasaPointRewardList where contentAmount equals to UPDATED_CONTENT_AMOUNT
        defaultMGuerillaQuestTsubasaPointRewardShouldNotBeFound("contentAmount.in=" + UPDATED_CONTENT_AMOUNT);
    }

    @Test
    @Transactional
    public void getAllMGuerillaQuestTsubasaPointRewardsByContentAmountIsNullOrNotNull() throws Exception {
        // Initialize the database
        mGuerillaQuestTsubasaPointRewardRepository.saveAndFlush(mGuerillaQuestTsubasaPointReward);

        // Get all the mGuerillaQuestTsubasaPointRewardList where contentAmount is not null
        defaultMGuerillaQuestTsubasaPointRewardShouldBeFound("contentAmount.specified=true");

        // Get all the mGuerillaQuestTsubasaPointRewardList where contentAmount is null
        defaultMGuerillaQuestTsubasaPointRewardShouldNotBeFound("contentAmount.specified=false");
    }

    @Test
    @Transactional
    public void getAllMGuerillaQuestTsubasaPointRewardsByContentAmountIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mGuerillaQuestTsubasaPointRewardRepository.saveAndFlush(mGuerillaQuestTsubasaPointReward);

        // Get all the mGuerillaQuestTsubasaPointRewardList where contentAmount greater than or equals to DEFAULT_CONTENT_AMOUNT
        defaultMGuerillaQuestTsubasaPointRewardShouldBeFound("contentAmount.greaterOrEqualThan=" + DEFAULT_CONTENT_AMOUNT);

        // Get all the mGuerillaQuestTsubasaPointRewardList where contentAmount greater than or equals to UPDATED_CONTENT_AMOUNT
        defaultMGuerillaQuestTsubasaPointRewardShouldNotBeFound("contentAmount.greaterOrEqualThan=" + UPDATED_CONTENT_AMOUNT);
    }

    @Test
    @Transactional
    public void getAllMGuerillaQuestTsubasaPointRewardsByContentAmountIsLessThanSomething() throws Exception {
        // Initialize the database
        mGuerillaQuestTsubasaPointRewardRepository.saveAndFlush(mGuerillaQuestTsubasaPointReward);

        // Get all the mGuerillaQuestTsubasaPointRewardList where contentAmount less than or equals to DEFAULT_CONTENT_AMOUNT
        defaultMGuerillaQuestTsubasaPointRewardShouldNotBeFound("contentAmount.lessThan=" + DEFAULT_CONTENT_AMOUNT);

        // Get all the mGuerillaQuestTsubasaPointRewardList where contentAmount less than or equals to UPDATED_CONTENT_AMOUNT
        defaultMGuerillaQuestTsubasaPointRewardShouldBeFound("contentAmount.lessThan=" + UPDATED_CONTENT_AMOUNT);
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultMGuerillaQuestTsubasaPointRewardShouldBeFound(String filter) throws Exception {
        restMGuerillaQuestTsubasaPointRewardMockMvc.perform(get("/api/m-guerilla-quest-tsubasa-point-rewards?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(mGuerillaQuestTsubasaPointReward.getId().intValue())))
            .andExpect(jsonPath("$.[*].stageId").value(hasItem(DEFAULT_STAGE_ID)))
            .andExpect(jsonPath("$.[*].tsubasaPoint").value(hasItem(DEFAULT_TSUBASA_POINT)))
            .andExpect(jsonPath("$.[*].contentType").value(hasItem(DEFAULT_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].contentId").value(hasItem(DEFAULT_CONTENT_ID)))
            .andExpect(jsonPath("$.[*].contentAmount").value(hasItem(DEFAULT_CONTENT_AMOUNT)));

        // Check, that the count call also returns 1
        restMGuerillaQuestTsubasaPointRewardMockMvc.perform(get("/api/m-guerilla-quest-tsubasa-point-rewards/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultMGuerillaQuestTsubasaPointRewardShouldNotBeFound(String filter) throws Exception {
        restMGuerillaQuestTsubasaPointRewardMockMvc.perform(get("/api/m-guerilla-quest-tsubasa-point-rewards?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restMGuerillaQuestTsubasaPointRewardMockMvc.perform(get("/api/m-guerilla-quest-tsubasa-point-rewards/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("0"));
    }


    @Test
    @Transactional
    public void getNonExistingMGuerillaQuestTsubasaPointReward() throws Exception {
        // Get the mGuerillaQuestTsubasaPointReward
        restMGuerillaQuestTsubasaPointRewardMockMvc.perform(get("/api/m-guerilla-quest-tsubasa-point-rewards/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateMGuerillaQuestTsubasaPointReward() throws Exception {
        // Initialize the database
        mGuerillaQuestTsubasaPointRewardRepository.saveAndFlush(mGuerillaQuestTsubasaPointReward);

        int databaseSizeBeforeUpdate = mGuerillaQuestTsubasaPointRewardRepository.findAll().size();

        // Update the mGuerillaQuestTsubasaPointReward
        MGuerillaQuestTsubasaPointReward updatedMGuerillaQuestTsubasaPointReward = mGuerillaQuestTsubasaPointRewardRepository.findById(mGuerillaQuestTsubasaPointReward.getId()).get();
        // Disconnect from session so that the updates on updatedMGuerillaQuestTsubasaPointReward are not directly saved in db
        em.detach(updatedMGuerillaQuestTsubasaPointReward);
        updatedMGuerillaQuestTsubasaPointReward
            .stageId(UPDATED_STAGE_ID)
            .tsubasaPoint(UPDATED_TSUBASA_POINT)
            .contentType(UPDATED_CONTENT_TYPE)
            .contentId(UPDATED_CONTENT_ID)
            .contentAmount(UPDATED_CONTENT_AMOUNT);
        MGuerillaQuestTsubasaPointRewardDTO mGuerillaQuestTsubasaPointRewardDTO = mGuerillaQuestTsubasaPointRewardMapper.toDto(updatedMGuerillaQuestTsubasaPointReward);

        restMGuerillaQuestTsubasaPointRewardMockMvc.perform(put("/api/m-guerilla-quest-tsubasa-point-rewards")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mGuerillaQuestTsubasaPointRewardDTO)))
            .andExpect(status().isOk());

        // Validate the MGuerillaQuestTsubasaPointReward in the database
        List<MGuerillaQuestTsubasaPointReward> mGuerillaQuestTsubasaPointRewardList = mGuerillaQuestTsubasaPointRewardRepository.findAll();
        assertThat(mGuerillaQuestTsubasaPointRewardList).hasSize(databaseSizeBeforeUpdate);
        MGuerillaQuestTsubasaPointReward testMGuerillaQuestTsubasaPointReward = mGuerillaQuestTsubasaPointRewardList.get(mGuerillaQuestTsubasaPointRewardList.size() - 1);
        assertThat(testMGuerillaQuestTsubasaPointReward.getStageId()).isEqualTo(UPDATED_STAGE_ID);
        assertThat(testMGuerillaQuestTsubasaPointReward.getTsubasaPoint()).isEqualTo(UPDATED_TSUBASA_POINT);
        assertThat(testMGuerillaQuestTsubasaPointReward.getContentType()).isEqualTo(UPDATED_CONTENT_TYPE);
        assertThat(testMGuerillaQuestTsubasaPointReward.getContentId()).isEqualTo(UPDATED_CONTENT_ID);
        assertThat(testMGuerillaQuestTsubasaPointReward.getContentAmount()).isEqualTo(UPDATED_CONTENT_AMOUNT);
    }

    @Test
    @Transactional
    public void updateNonExistingMGuerillaQuestTsubasaPointReward() throws Exception {
        int databaseSizeBeforeUpdate = mGuerillaQuestTsubasaPointRewardRepository.findAll().size();

        // Create the MGuerillaQuestTsubasaPointReward
        MGuerillaQuestTsubasaPointRewardDTO mGuerillaQuestTsubasaPointRewardDTO = mGuerillaQuestTsubasaPointRewardMapper.toDto(mGuerillaQuestTsubasaPointReward);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restMGuerillaQuestTsubasaPointRewardMockMvc.perform(put("/api/m-guerilla-quest-tsubasa-point-rewards")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mGuerillaQuestTsubasaPointRewardDTO)))
            .andExpect(status().isBadRequest());

        // Validate the MGuerillaQuestTsubasaPointReward in the database
        List<MGuerillaQuestTsubasaPointReward> mGuerillaQuestTsubasaPointRewardList = mGuerillaQuestTsubasaPointRewardRepository.findAll();
        assertThat(mGuerillaQuestTsubasaPointRewardList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteMGuerillaQuestTsubasaPointReward() throws Exception {
        // Initialize the database
        mGuerillaQuestTsubasaPointRewardRepository.saveAndFlush(mGuerillaQuestTsubasaPointReward);

        int databaseSizeBeforeDelete = mGuerillaQuestTsubasaPointRewardRepository.findAll().size();

        // Delete the mGuerillaQuestTsubasaPointReward
        restMGuerillaQuestTsubasaPointRewardMockMvc.perform(delete("/api/m-guerilla-quest-tsubasa-point-rewards/{id}", mGuerillaQuestTsubasaPointReward.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database is empty
        List<MGuerillaQuestTsubasaPointReward> mGuerillaQuestTsubasaPointRewardList = mGuerillaQuestTsubasaPointRewardRepository.findAll();
        assertThat(mGuerillaQuestTsubasaPointRewardList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(MGuerillaQuestTsubasaPointReward.class);
        MGuerillaQuestTsubasaPointReward mGuerillaQuestTsubasaPointReward1 = new MGuerillaQuestTsubasaPointReward();
        mGuerillaQuestTsubasaPointReward1.setId(1L);
        MGuerillaQuestTsubasaPointReward mGuerillaQuestTsubasaPointReward2 = new MGuerillaQuestTsubasaPointReward();
        mGuerillaQuestTsubasaPointReward2.setId(mGuerillaQuestTsubasaPointReward1.getId());
        assertThat(mGuerillaQuestTsubasaPointReward1).isEqualTo(mGuerillaQuestTsubasaPointReward2);
        mGuerillaQuestTsubasaPointReward2.setId(2L);
        assertThat(mGuerillaQuestTsubasaPointReward1).isNotEqualTo(mGuerillaQuestTsubasaPointReward2);
        mGuerillaQuestTsubasaPointReward1.setId(null);
        assertThat(mGuerillaQuestTsubasaPointReward1).isNotEqualTo(mGuerillaQuestTsubasaPointReward2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(MGuerillaQuestTsubasaPointRewardDTO.class);
        MGuerillaQuestTsubasaPointRewardDTO mGuerillaQuestTsubasaPointRewardDTO1 = new MGuerillaQuestTsubasaPointRewardDTO();
        mGuerillaQuestTsubasaPointRewardDTO1.setId(1L);
        MGuerillaQuestTsubasaPointRewardDTO mGuerillaQuestTsubasaPointRewardDTO2 = new MGuerillaQuestTsubasaPointRewardDTO();
        assertThat(mGuerillaQuestTsubasaPointRewardDTO1).isNotEqualTo(mGuerillaQuestTsubasaPointRewardDTO2);
        mGuerillaQuestTsubasaPointRewardDTO2.setId(mGuerillaQuestTsubasaPointRewardDTO1.getId());
        assertThat(mGuerillaQuestTsubasaPointRewardDTO1).isEqualTo(mGuerillaQuestTsubasaPointRewardDTO2);
        mGuerillaQuestTsubasaPointRewardDTO2.setId(2L);
        assertThat(mGuerillaQuestTsubasaPointRewardDTO1).isNotEqualTo(mGuerillaQuestTsubasaPointRewardDTO2);
        mGuerillaQuestTsubasaPointRewardDTO1.setId(null);
        assertThat(mGuerillaQuestTsubasaPointRewardDTO1).isNotEqualTo(mGuerillaQuestTsubasaPointRewardDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(mGuerillaQuestTsubasaPointRewardMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(mGuerillaQuestTsubasaPointRewardMapper.fromId(null)).isNull();
    }
}
