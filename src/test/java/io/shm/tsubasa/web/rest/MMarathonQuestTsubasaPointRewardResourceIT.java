package io.shm.tsubasa.web.rest;

import io.shm.tsubasa.TsubasaApp;
import io.shm.tsubasa.domain.MMarathonQuestTsubasaPointReward;
import io.shm.tsubasa.repository.MMarathonQuestTsubasaPointRewardRepository;
import io.shm.tsubasa.service.MMarathonQuestTsubasaPointRewardService;
import io.shm.tsubasa.service.dto.MMarathonQuestTsubasaPointRewardDTO;
import io.shm.tsubasa.service.mapper.MMarathonQuestTsubasaPointRewardMapper;
import io.shm.tsubasa.web.rest.errors.ExceptionTranslator;
import io.shm.tsubasa.service.dto.MMarathonQuestTsubasaPointRewardCriteria;
import io.shm.tsubasa.service.MMarathonQuestTsubasaPointRewardQueryService;

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
 * Integration tests for the {@Link MMarathonQuestTsubasaPointRewardResource} REST controller.
 */
@SpringBootTest(classes = TsubasaApp.class)
public class MMarathonQuestTsubasaPointRewardResourceIT {

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
    private MMarathonQuestTsubasaPointRewardRepository mMarathonQuestTsubasaPointRewardRepository;

    @Autowired
    private MMarathonQuestTsubasaPointRewardMapper mMarathonQuestTsubasaPointRewardMapper;

    @Autowired
    private MMarathonQuestTsubasaPointRewardService mMarathonQuestTsubasaPointRewardService;

    @Autowired
    private MMarathonQuestTsubasaPointRewardQueryService mMarathonQuestTsubasaPointRewardQueryService;

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

    private MockMvc restMMarathonQuestTsubasaPointRewardMockMvc;

    private MMarathonQuestTsubasaPointReward mMarathonQuestTsubasaPointReward;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final MMarathonQuestTsubasaPointRewardResource mMarathonQuestTsubasaPointRewardResource = new MMarathonQuestTsubasaPointRewardResource(mMarathonQuestTsubasaPointRewardService, mMarathonQuestTsubasaPointRewardQueryService);
        this.restMMarathonQuestTsubasaPointRewardMockMvc = MockMvcBuilders.standaloneSetup(mMarathonQuestTsubasaPointRewardResource)
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
    public static MMarathonQuestTsubasaPointReward createEntity(EntityManager em) {
        MMarathonQuestTsubasaPointReward mMarathonQuestTsubasaPointReward = new MMarathonQuestTsubasaPointReward()
            .stageId(DEFAULT_STAGE_ID)
            .tsubasaPoint(DEFAULT_TSUBASA_POINT)
            .contentType(DEFAULT_CONTENT_TYPE)
            .contentId(DEFAULT_CONTENT_ID)
            .contentAmount(DEFAULT_CONTENT_AMOUNT);
        return mMarathonQuestTsubasaPointReward;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static MMarathonQuestTsubasaPointReward createUpdatedEntity(EntityManager em) {
        MMarathonQuestTsubasaPointReward mMarathonQuestTsubasaPointReward = new MMarathonQuestTsubasaPointReward()
            .stageId(UPDATED_STAGE_ID)
            .tsubasaPoint(UPDATED_TSUBASA_POINT)
            .contentType(UPDATED_CONTENT_TYPE)
            .contentId(UPDATED_CONTENT_ID)
            .contentAmount(UPDATED_CONTENT_AMOUNT);
        return mMarathonQuestTsubasaPointReward;
    }

    @BeforeEach
    public void initTest() {
        mMarathonQuestTsubasaPointReward = createEntity(em);
    }

    @Test
    @Transactional
    public void createMMarathonQuestTsubasaPointReward() throws Exception {
        int databaseSizeBeforeCreate = mMarathonQuestTsubasaPointRewardRepository.findAll().size();

        // Create the MMarathonQuestTsubasaPointReward
        MMarathonQuestTsubasaPointRewardDTO mMarathonQuestTsubasaPointRewardDTO = mMarathonQuestTsubasaPointRewardMapper.toDto(mMarathonQuestTsubasaPointReward);
        restMMarathonQuestTsubasaPointRewardMockMvc.perform(post("/api/m-marathon-quest-tsubasa-point-rewards")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mMarathonQuestTsubasaPointRewardDTO)))
            .andExpect(status().isCreated());

        // Validate the MMarathonQuestTsubasaPointReward in the database
        List<MMarathonQuestTsubasaPointReward> mMarathonQuestTsubasaPointRewardList = mMarathonQuestTsubasaPointRewardRepository.findAll();
        assertThat(mMarathonQuestTsubasaPointRewardList).hasSize(databaseSizeBeforeCreate + 1);
        MMarathonQuestTsubasaPointReward testMMarathonQuestTsubasaPointReward = mMarathonQuestTsubasaPointRewardList.get(mMarathonQuestTsubasaPointRewardList.size() - 1);
        assertThat(testMMarathonQuestTsubasaPointReward.getStageId()).isEqualTo(DEFAULT_STAGE_ID);
        assertThat(testMMarathonQuestTsubasaPointReward.getTsubasaPoint()).isEqualTo(DEFAULT_TSUBASA_POINT);
        assertThat(testMMarathonQuestTsubasaPointReward.getContentType()).isEqualTo(DEFAULT_CONTENT_TYPE);
        assertThat(testMMarathonQuestTsubasaPointReward.getContentId()).isEqualTo(DEFAULT_CONTENT_ID);
        assertThat(testMMarathonQuestTsubasaPointReward.getContentAmount()).isEqualTo(DEFAULT_CONTENT_AMOUNT);
    }

    @Test
    @Transactional
    public void createMMarathonQuestTsubasaPointRewardWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = mMarathonQuestTsubasaPointRewardRepository.findAll().size();

        // Create the MMarathonQuestTsubasaPointReward with an existing ID
        mMarathonQuestTsubasaPointReward.setId(1L);
        MMarathonQuestTsubasaPointRewardDTO mMarathonQuestTsubasaPointRewardDTO = mMarathonQuestTsubasaPointRewardMapper.toDto(mMarathonQuestTsubasaPointReward);

        // An entity with an existing ID cannot be created, so this API call must fail
        restMMarathonQuestTsubasaPointRewardMockMvc.perform(post("/api/m-marathon-quest-tsubasa-point-rewards")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mMarathonQuestTsubasaPointRewardDTO)))
            .andExpect(status().isBadRequest());

        // Validate the MMarathonQuestTsubasaPointReward in the database
        List<MMarathonQuestTsubasaPointReward> mMarathonQuestTsubasaPointRewardList = mMarathonQuestTsubasaPointRewardRepository.findAll();
        assertThat(mMarathonQuestTsubasaPointRewardList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkStageIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = mMarathonQuestTsubasaPointRewardRepository.findAll().size();
        // set the field null
        mMarathonQuestTsubasaPointReward.setStageId(null);

        // Create the MMarathonQuestTsubasaPointReward, which fails.
        MMarathonQuestTsubasaPointRewardDTO mMarathonQuestTsubasaPointRewardDTO = mMarathonQuestTsubasaPointRewardMapper.toDto(mMarathonQuestTsubasaPointReward);

        restMMarathonQuestTsubasaPointRewardMockMvc.perform(post("/api/m-marathon-quest-tsubasa-point-rewards")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mMarathonQuestTsubasaPointRewardDTO)))
            .andExpect(status().isBadRequest());

        List<MMarathonQuestTsubasaPointReward> mMarathonQuestTsubasaPointRewardList = mMarathonQuestTsubasaPointRewardRepository.findAll();
        assertThat(mMarathonQuestTsubasaPointRewardList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkTsubasaPointIsRequired() throws Exception {
        int databaseSizeBeforeTest = mMarathonQuestTsubasaPointRewardRepository.findAll().size();
        // set the field null
        mMarathonQuestTsubasaPointReward.setTsubasaPoint(null);

        // Create the MMarathonQuestTsubasaPointReward, which fails.
        MMarathonQuestTsubasaPointRewardDTO mMarathonQuestTsubasaPointRewardDTO = mMarathonQuestTsubasaPointRewardMapper.toDto(mMarathonQuestTsubasaPointReward);

        restMMarathonQuestTsubasaPointRewardMockMvc.perform(post("/api/m-marathon-quest-tsubasa-point-rewards")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mMarathonQuestTsubasaPointRewardDTO)))
            .andExpect(status().isBadRequest());

        List<MMarathonQuestTsubasaPointReward> mMarathonQuestTsubasaPointRewardList = mMarathonQuestTsubasaPointRewardRepository.findAll();
        assertThat(mMarathonQuestTsubasaPointRewardList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkContentTypeIsRequired() throws Exception {
        int databaseSizeBeforeTest = mMarathonQuestTsubasaPointRewardRepository.findAll().size();
        // set the field null
        mMarathonQuestTsubasaPointReward.setContentType(null);

        // Create the MMarathonQuestTsubasaPointReward, which fails.
        MMarathonQuestTsubasaPointRewardDTO mMarathonQuestTsubasaPointRewardDTO = mMarathonQuestTsubasaPointRewardMapper.toDto(mMarathonQuestTsubasaPointReward);

        restMMarathonQuestTsubasaPointRewardMockMvc.perform(post("/api/m-marathon-quest-tsubasa-point-rewards")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mMarathonQuestTsubasaPointRewardDTO)))
            .andExpect(status().isBadRequest());

        List<MMarathonQuestTsubasaPointReward> mMarathonQuestTsubasaPointRewardList = mMarathonQuestTsubasaPointRewardRepository.findAll();
        assertThat(mMarathonQuestTsubasaPointRewardList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkContentAmountIsRequired() throws Exception {
        int databaseSizeBeforeTest = mMarathonQuestTsubasaPointRewardRepository.findAll().size();
        // set the field null
        mMarathonQuestTsubasaPointReward.setContentAmount(null);

        // Create the MMarathonQuestTsubasaPointReward, which fails.
        MMarathonQuestTsubasaPointRewardDTO mMarathonQuestTsubasaPointRewardDTO = mMarathonQuestTsubasaPointRewardMapper.toDto(mMarathonQuestTsubasaPointReward);

        restMMarathonQuestTsubasaPointRewardMockMvc.perform(post("/api/m-marathon-quest-tsubasa-point-rewards")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mMarathonQuestTsubasaPointRewardDTO)))
            .andExpect(status().isBadRequest());

        List<MMarathonQuestTsubasaPointReward> mMarathonQuestTsubasaPointRewardList = mMarathonQuestTsubasaPointRewardRepository.findAll();
        assertThat(mMarathonQuestTsubasaPointRewardList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllMMarathonQuestTsubasaPointRewards() throws Exception {
        // Initialize the database
        mMarathonQuestTsubasaPointRewardRepository.saveAndFlush(mMarathonQuestTsubasaPointReward);

        // Get all the mMarathonQuestTsubasaPointRewardList
        restMMarathonQuestTsubasaPointRewardMockMvc.perform(get("/api/m-marathon-quest-tsubasa-point-rewards?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(mMarathonQuestTsubasaPointReward.getId().intValue())))
            .andExpect(jsonPath("$.[*].stageId").value(hasItem(DEFAULT_STAGE_ID)))
            .andExpect(jsonPath("$.[*].tsubasaPoint").value(hasItem(DEFAULT_TSUBASA_POINT)))
            .andExpect(jsonPath("$.[*].contentType").value(hasItem(DEFAULT_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].contentId").value(hasItem(DEFAULT_CONTENT_ID)))
            .andExpect(jsonPath("$.[*].contentAmount").value(hasItem(DEFAULT_CONTENT_AMOUNT)));
    }
    
    @Test
    @Transactional
    public void getMMarathonQuestTsubasaPointReward() throws Exception {
        // Initialize the database
        mMarathonQuestTsubasaPointRewardRepository.saveAndFlush(mMarathonQuestTsubasaPointReward);

        // Get the mMarathonQuestTsubasaPointReward
        restMMarathonQuestTsubasaPointRewardMockMvc.perform(get("/api/m-marathon-quest-tsubasa-point-rewards/{id}", mMarathonQuestTsubasaPointReward.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(mMarathonQuestTsubasaPointReward.getId().intValue()))
            .andExpect(jsonPath("$.stageId").value(DEFAULT_STAGE_ID))
            .andExpect(jsonPath("$.tsubasaPoint").value(DEFAULT_TSUBASA_POINT))
            .andExpect(jsonPath("$.contentType").value(DEFAULT_CONTENT_TYPE))
            .andExpect(jsonPath("$.contentId").value(DEFAULT_CONTENT_ID))
            .andExpect(jsonPath("$.contentAmount").value(DEFAULT_CONTENT_AMOUNT));
    }

    @Test
    @Transactional
    public void getAllMMarathonQuestTsubasaPointRewardsByStageIdIsEqualToSomething() throws Exception {
        // Initialize the database
        mMarathonQuestTsubasaPointRewardRepository.saveAndFlush(mMarathonQuestTsubasaPointReward);

        // Get all the mMarathonQuestTsubasaPointRewardList where stageId equals to DEFAULT_STAGE_ID
        defaultMMarathonQuestTsubasaPointRewardShouldBeFound("stageId.equals=" + DEFAULT_STAGE_ID);

        // Get all the mMarathonQuestTsubasaPointRewardList where stageId equals to UPDATED_STAGE_ID
        defaultMMarathonQuestTsubasaPointRewardShouldNotBeFound("stageId.equals=" + UPDATED_STAGE_ID);
    }

    @Test
    @Transactional
    public void getAllMMarathonQuestTsubasaPointRewardsByStageIdIsInShouldWork() throws Exception {
        // Initialize the database
        mMarathonQuestTsubasaPointRewardRepository.saveAndFlush(mMarathonQuestTsubasaPointReward);

        // Get all the mMarathonQuestTsubasaPointRewardList where stageId in DEFAULT_STAGE_ID or UPDATED_STAGE_ID
        defaultMMarathonQuestTsubasaPointRewardShouldBeFound("stageId.in=" + DEFAULT_STAGE_ID + "," + UPDATED_STAGE_ID);

        // Get all the mMarathonQuestTsubasaPointRewardList where stageId equals to UPDATED_STAGE_ID
        defaultMMarathonQuestTsubasaPointRewardShouldNotBeFound("stageId.in=" + UPDATED_STAGE_ID);
    }

    @Test
    @Transactional
    public void getAllMMarathonQuestTsubasaPointRewardsByStageIdIsNullOrNotNull() throws Exception {
        // Initialize the database
        mMarathonQuestTsubasaPointRewardRepository.saveAndFlush(mMarathonQuestTsubasaPointReward);

        // Get all the mMarathonQuestTsubasaPointRewardList where stageId is not null
        defaultMMarathonQuestTsubasaPointRewardShouldBeFound("stageId.specified=true");

        // Get all the mMarathonQuestTsubasaPointRewardList where stageId is null
        defaultMMarathonQuestTsubasaPointRewardShouldNotBeFound("stageId.specified=false");
    }

    @Test
    @Transactional
    public void getAllMMarathonQuestTsubasaPointRewardsByStageIdIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mMarathonQuestTsubasaPointRewardRepository.saveAndFlush(mMarathonQuestTsubasaPointReward);

        // Get all the mMarathonQuestTsubasaPointRewardList where stageId greater than or equals to DEFAULT_STAGE_ID
        defaultMMarathonQuestTsubasaPointRewardShouldBeFound("stageId.greaterOrEqualThan=" + DEFAULT_STAGE_ID);

        // Get all the mMarathonQuestTsubasaPointRewardList where stageId greater than or equals to UPDATED_STAGE_ID
        defaultMMarathonQuestTsubasaPointRewardShouldNotBeFound("stageId.greaterOrEqualThan=" + UPDATED_STAGE_ID);
    }

    @Test
    @Transactional
    public void getAllMMarathonQuestTsubasaPointRewardsByStageIdIsLessThanSomething() throws Exception {
        // Initialize the database
        mMarathonQuestTsubasaPointRewardRepository.saveAndFlush(mMarathonQuestTsubasaPointReward);

        // Get all the mMarathonQuestTsubasaPointRewardList where stageId less than or equals to DEFAULT_STAGE_ID
        defaultMMarathonQuestTsubasaPointRewardShouldNotBeFound("stageId.lessThan=" + DEFAULT_STAGE_ID);

        // Get all the mMarathonQuestTsubasaPointRewardList where stageId less than or equals to UPDATED_STAGE_ID
        defaultMMarathonQuestTsubasaPointRewardShouldBeFound("stageId.lessThan=" + UPDATED_STAGE_ID);
    }


    @Test
    @Transactional
    public void getAllMMarathonQuestTsubasaPointRewardsByTsubasaPointIsEqualToSomething() throws Exception {
        // Initialize the database
        mMarathonQuestTsubasaPointRewardRepository.saveAndFlush(mMarathonQuestTsubasaPointReward);

        // Get all the mMarathonQuestTsubasaPointRewardList where tsubasaPoint equals to DEFAULT_TSUBASA_POINT
        defaultMMarathonQuestTsubasaPointRewardShouldBeFound("tsubasaPoint.equals=" + DEFAULT_TSUBASA_POINT);

        // Get all the mMarathonQuestTsubasaPointRewardList where tsubasaPoint equals to UPDATED_TSUBASA_POINT
        defaultMMarathonQuestTsubasaPointRewardShouldNotBeFound("tsubasaPoint.equals=" + UPDATED_TSUBASA_POINT);
    }

    @Test
    @Transactional
    public void getAllMMarathonQuestTsubasaPointRewardsByTsubasaPointIsInShouldWork() throws Exception {
        // Initialize the database
        mMarathonQuestTsubasaPointRewardRepository.saveAndFlush(mMarathonQuestTsubasaPointReward);

        // Get all the mMarathonQuestTsubasaPointRewardList where tsubasaPoint in DEFAULT_TSUBASA_POINT or UPDATED_TSUBASA_POINT
        defaultMMarathonQuestTsubasaPointRewardShouldBeFound("tsubasaPoint.in=" + DEFAULT_TSUBASA_POINT + "," + UPDATED_TSUBASA_POINT);

        // Get all the mMarathonQuestTsubasaPointRewardList where tsubasaPoint equals to UPDATED_TSUBASA_POINT
        defaultMMarathonQuestTsubasaPointRewardShouldNotBeFound("tsubasaPoint.in=" + UPDATED_TSUBASA_POINT);
    }

    @Test
    @Transactional
    public void getAllMMarathonQuestTsubasaPointRewardsByTsubasaPointIsNullOrNotNull() throws Exception {
        // Initialize the database
        mMarathonQuestTsubasaPointRewardRepository.saveAndFlush(mMarathonQuestTsubasaPointReward);

        // Get all the mMarathonQuestTsubasaPointRewardList where tsubasaPoint is not null
        defaultMMarathonQuestTsubasaPointRewardShouldBeFound("tsubasaPoint.specified=true");

        // Get all the mMarathonQuestTsubasaPointRewardList where tsubasaPoint is null
        defaultMMarathonQuestTsubasaPointRewardShouldNotBeFound("tsubasaPoint.specified=false");
    }

    @Test
    @Transactional
    public void getAllMMarathonQuestTsubasaPointRewardsByTsubasaPointIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mMarathonQuestTsubasaPointRewardRepository.saveAndFlush(mMarathonQuestTsubasaPointReward);

        // Get all the mMarathonQuestTsubasaPointRewardList where tsubasaPoint greater than or equals to DEFAULT_TSUBASA_POINT
        defaultMMarathonQuestTsubasaPointRewardShouldBeFound("tsubasaPoint.greaterOrEqualThan=" + DEFAULT_TSUBASA_POINT);

        // Get all the mMarathonQuestTsubasaPointRewardList where tsubasaPoint greater than or equals to UPDATED_TSUBASA_POINT
        defaultMMarathonQuestTsubasaPointRewardShouldNotBeFound("tsubasaPoint.greaterOrEqualThan=" + UPDATED_TSUBASA_POINT);
    }

    @Test
    @Transactional
    public void getAllMMarathonQuestTsubasaPointRewardsByTsubasaPointIsLessThanSomething() throws Exception {
        // Initialize the database
        mMarathonQuestTsubasaPointRewardRepository.saveAndFlush(mMarathonQuestTsubasaPointReward);

        // Get all the mMarathonQuestTsubasaPointRewardList where tsubasaPoint less than or equals to DEFAULT_TSUBASA_POINT
        defaultMMarathonQuestTsubasaPointRewardShouldNotBeFound("tsubasaPoint.lessThan=" + DEFAULT_TSUBASA_POINT);

        // Get all the mMarathonQuestTsubasaPointRewardList where tsubasaPoint less than or equals to UPDATED_TSUBASA_POINT
        defaultMMarathonQuestTsubasaPointRewardShouldBeFound("tsubasaPoint.lessThan=" + UPDATED_TSUBASA_POINT);
    }


    @Test
    @Transactional
    public void getAllMMarathonQuestTsubasaPointRewardsByContentTypeIsEqualToSomething() throws Exception {
        // Initialize the database
        mMarathonQuestTsubasaPointRewardRepository.saveAndFlush(mMarathonQuestTsubasaPointReward);

        // Get all the mMarathonQuestTsubasaPointRewardList where contentType equals to DEFAULT_CONTENT_TYPE
        defaultMMarathonQuestTsubasaPointRewardShouldBeFound("contentType.equals=" + DEFAULT_CONTENT_TYPE);

        // Get all the mMarathonQuestTsubasaPointRewardList where contentType equals to UPDATED_CONTENT_TYPE
        defaultMMarathonQuestTsubasaPointRewardShouldNotBeFound("contentType.equals=" + UPDATED_CONTENT_TYPE);
    }

    @Test
    @Transactional
    public void getAllMMarathonQuestTsubasaPointRewardsByContentTypeIsInShouldWork() throws Exception {
        // Initialize the database
        mMarathonQuestTsubasaPointRewardRepository.saveAndFlush(mMarathonQuestTsubasaPointReward);

        // Get all the mMarathonQuestTsubasaPointRewardList where contentType in DEFAULT_CONTENT_TYPE or UPDATED_CONTENT_TYPE
        defaultMMarathonQuestTsubasaPointRewardShouldBeFound("contentType.in=" + DEFAULT_CONTENT_TYPE + "," + UPDATED_CONTENT_TYPE);

        // Get all the mMarathonQuestTsubasaPointRewardList where contentType equals to UPDATED_CONTENT_TYPE
        defaultMMarathonQuestTsubasaPointRewardShouldNotBeFound("contentType.in=" + UPDATED_CONTENT_TYPE);
    }

    @Test
    @Transactional
    public void getAllMMarathonQuestTsubasaPointRewardsByContentTypeIsNullOrNotNull() throws Exception {
        // Initialize the database
        mMarathonQuestTsubasaPointRewardRepository.saveAndFlush(mMarathonQuestTsubasaPointReward);

        // Get all the mMarathonQuestTsubasaPointRewardList where contentType is not null
        defaultMMarathonQuestTsubasaPointRewardShouldBeFound("contentType.specified=true");

        // Get all the mMarathonQuestTsubasaPointRewardList where contentType is null
        defaultMMarathonQuestTsubasaPointRewardShouldNotBeFound("contentType.specified=false");
    }

    @Test
    @Transactional
    public void getAllMMarathonQuestTsubasaPointRewardsByContentTypeIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mMarathonQuestTsubasaPointRewardRepository.saveAndFlush(mMarathonQuestTsubasaPointReward);

        // Get all the mMarathonQuestTsubasaPointRewardList where contentType greater than or equals to DEFAULT_CONTENT_TYPE
        defaultMMarathonQuestTsubasaPointRewardShouldBeFound("contentType.greaterOrEqualThan=" + DEFAULT_CONTENT_TYPE);

        // Get all the mMarathonQuestTsubasaPointRewardList where contentType greater than or equals to UPDATED_CONTENT_TYPE
        defaultMMarathonQuestTsubasaPointRewardShouldNotBeFound("contentType.greaterOrEqualThan=" + UPDATED_CONTENT_TYPE);
    }

    @Test
    @Transactional
    public void getAllMMarathonQuestTsubasaPointRewardsByContentTypeIsLessThanSomething() throws Exception {
        // Initialize the database
        mMarathonQuestTsubasaPointRewardRepository.saveAndFlush(mMarathonQuestTsubasaPointReward);

        // Get all the mMarathonQuestTsubasaPointRewardList where contentType less than or equals to DEFAULT_CONTENT_TYPE
        defaultMMarathonQuestTsubasaPointRewardShouldNotBeFound("contentType.lessThan=" + DEFAULT_CONTENT_TYPE);

        // Get all the mMarathonQuestTsubasaPointRewardList where contentType less than or equals to UPDATED_CONTENT_TYPE
        defaultMMarathonQuestTsubasaPointRewardShouldBeFound("contentType.lessThan=" + UPDATED_CONTENT_TYPE);
    }


    @Test
    @Transactional
    public void getAllMMarathonQuestTsubasaPointRewardsByContentIdIsEqualToSomething() throws Exception {
        // Initialize the database
        mMarathonQuestTsubasaPointRewardRepository.saveAndFlush(mMarathonQuestTsubasaPointReward);

        // Get all the mMarathonQuestTsubasaPointRewardList where contentId equals to DEFAULT_CONTENT_ID
        defaultMMarathonQuestTsubasaPointRewardShouldBeFound("contentId.equals=" + DEFAULT_CONTENT_ID);

        // Get all the mMarathonQuestTsubasaPointRewardList where contentId equals to UPDATED_CONTENT_ID
        defaultMMarathonQuestTsubasaPointRewardShouldNotBeFound("contentId.equals=" + UPDATED_CONTENT_ID);
    }

    @Test
    @Transactional
    public void getAllMMarathonQuestTsubasaPointRewardsByContentIdIsInShouldWork() throws Exception {
        // Initialize the database
        mMarathonQuestTsubasaPointRewardRepository.saveAndFlush(mMarathonQuestTsubasaPointReward);

        // Get all the mMarathonQuestTsubasaPointRewardList where contentId in DEFAULT_CONTENT_ID or UPDATED_CONTENT_ID
        defaultMMarathonQuestTsubasaPointRewardShouldBeFound("contentId.in=" + DEFAULT_CONTENT_ID + "," + UPDATED_CONTENT_ID);

        // Get all the mMarathonQuestTsubasaPointRewardList where contentId equals to UPDATED_CONTENT_ID
        defaultMMarathonQuestTsubasaPointRewardShouldNotBeFound("contentId.in=" + UPDATED_CONTENT_ID);
    }

    @Test
    @Transactional
    public void getAllMMarathonQuestTsubasaPointRewardsByContentIdIsNullOrNotNull() throws Exception {
        // Initialize the database
        mMarathonQuestTsubasaPointRewardRepository.saveAndFlush(mMarathonQuestTsubasaPointReward);

        // Get all the mMarathonQuestTsubasaPointRewardList where contentId is not null
        defaultMMarathonQuestTsubasaPointRewardShouldBeFound("contentId.specified=true");

        // Get all the mMarathonQuestTsubasaPointRewardList where contentId is null
        defaultMMarathonQuestTsubasaPointRewardShouldNotBeFound("contentId.specified=false");
    }

    @Test
    @Transactional
    public void getAllMMarathonQuestTsubasaPointRewardsByContentIdIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mMarathonQuestTsubasaPointRewardRepository.saveAndFlush(mMarathonQuestTsubasaPointReward);

        // Get all the mMarathonQuestTsubasaPointRewardList where contentId greater than or equals to DEFAULT_CONTENT_ID
        defaultMMarathonQuestTsubasaPointRewardShouldBeFound("contentId.greaterOrEqualThan=" + DEFAULT_CONTENT_ID);

        // Get all the mMarathonQuestTsubasaPointRewardList where contentId greater than or equals to UPDATED_CONTENT_ID
        defaultMMarathonQuestTsubasaPointRewardShouldNotBeFound("contentId.greaterOrEqualThan=" + UPDATED_CONTENT_ID);
    }

    @Test
    @Transactional
    public void getAllMMarathonQuestTsubasaPointRewardsByContentIdIsLessThanSomething() throws Exception {
        // Initialize the database
        mMarathonQuestTsubasaPointRewardRepository.saveAndFlush(mMarathonQuestTsubasaPointReward);

        // Get all the mMarathonQuestTsubasaPointRewardList where contentId less than or equals to DEFAULT_CONTENT_ID
        defaultMMarathonQuestTsubasaPointRewardShouldNotBeFound("contentId.lessThan=" + DEFAULT_CONTENT_ID);

        // Get all the mMarathonQuestTsubasaPointRewardList where contentId less than or equals to UPDATED_CONTENT_ID
        defaultMMarathonQuestTsubasaPointRewardShouldBeFound("contentId.lessThan=" + UPDATED_CONTENT_ID);
    }


    @Test
    @Transactional
    public void getAllMMarathonQuestTsubasaPointRewardsByContentAmountIsEqualToSomething() throws Exception {
        // Initialize the database
        mMarathonQuestTsubasaPointRewardRepository.saveAndFlush(mMarathonQuestTsubasaPointReward);

        // Get all the mMarathonQuestTsubasaPointRewardList where contentAmount equals to DEFAULT_CONTENT_AMOUNT
        defaultMMarathonQuestTsubasaPointRewardShouldBeFound("contentAmount.equals=" + DEFAULT_CONTENT_AMOUNT);

        // Get all the mMarathonQuestTsubasaPointRewardList where contentAmount equals to UPDATED_CONTENT_AMOUNT
        defaultMMarathonQuestTsubasaPointRewardShouldNotBeFound("contentAmount.equals=" + UPDATED_CONTENT_AMOUNT);
    }

    @Test
    @Transactional
    public void getAllMMarathonQuestTsubasaPointRewardsByContentAmountIsInShouldWork() throws Exception {
        // Initialize the database
        mMarathonQuestTsubasaPointRewardRepository.saveAndFlush(mMarathonQuestTsubasaPointReward);

        // Get all the mMarathonQuestTsubasaPointRewardList where contentAmount in DEFAULT_CONTENT_AMOUNT or UPDATED_CONTENT_AMOUNT
        defaultMMarathonQuestTsubasaPointRewardShouldBeFound("contentAmount.in=" + DEFAULT_CONTENT_AMOUNT + "," + UPDATED_CONTENT_AMOUNT);

        // Get all the mMarathonQuestTsubasaPointRewardList where contentAmount equals to UPDATED_CONTENT_AMOUNT
        defaultMMarathonQuestTsubasaPointRewardShouldNotBeFound("contentAmount.in=" + UPDATED_CONTENT_AMOUNT);
    }

    @Test
    @Transactional
    public void getAllMMarathonQuestTsubasaPointRewardsByContentAmountIsNullOrNotNull() throws Exception {
        // Initialize the database
        mMarathonQuestTsubasaPointRewardRepository.saveAndFlush(mMarathonQuestTsubasaPointReward);

        // Get all the mMarathonQuestTsubasaPointRewardList where contentAmount is not null
        defaultMMarathonQuestTsubasaPointRewardShouldBeFound("contentAmount.specified=true");

        // Get all the mMarathonQuestTsubasaPointRewardList where contentAmount is null
        defaultMMarathonQuestTsubasaPointRewardShouldNotBeFound("contentAmount.specified=false");
    }

    @Test
    @Transactional
    public void getAllMMarathonQuestTsubasaPointRewardsByContentAmountIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mMarathonQuestTsubasaPointRewardRepository.saveAndFlush(mMarathonQuestTsubasaPointReward);

        // Get all the mMarathonQuestTsubasaPointRewardList where contentAmount greater than or equals to DEFAULT_CONTENT_AMOUNT
        defaultMMarathonQuestTsubasaPointRewardShouldBeFound("contentAmount.greaterOrEqualThan=" + DEFAULT_CONTENT_AMOUNT);

        // Get all the mMarathonQuestTsubasaPointRewardList where contentAmount greater than or equals to UPDATED_CONTENT_AMOUNT
        defaultMMarathonQuestTsubasaPointRewardShouldNotBeFound("contentAmount.greaterOrEqualThan=" + UPDATED_CONTENT_AMOUNT);
    }

    @Test
    @Transactional
    public void getAllMMarathonQuestTsubasaPointRewardsByContentAmountIsLessThanSomething() throws Exception {
        // Initialize the database
        mMarathonQuestTsubasaPointRewardRepository.saveAndFlush(mMarathonQuestTsubasaPointReward);

        // Get all the mMarathonQuestTsubasaPointRewardList where contentAmount less than or equals to DEFAULT_CONTENT_AMOUNT
        defaultMMarathonQuestTsubasaPointRewardShouldNotBeFound("contentAmount.lessThan=" + DEFAULT_CONTENT_AMOUNT);

        // Get all the mMarathonQuestTsubasaPointRewardList where contentAmount less than or equals to UPDATED_CONTENT_AMOUNT
        defaultMMarathonQuestTsubasaPointRewardShouldBeFound("contentAmount.lessThan=" + UPDATED_CONTENT_AMOUNT);
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultMMarathonQuestTsubasaPointRewardShouldBeFound(String filter) throws Exception {
        restMMarathonQuestTsubasaPointRewardMockMvc.perform(get("/api/m-marathon-quest-tsubasa-point-rewards?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(mMarathonQuestTsubasaPointReward.getId().intValue())))
            .andExpect(jsonPath("$.[*].stageId").value(hasItem(DEFAULT_STAGE_ID)))
            .andExpect(jsonPath("$.[*].tsubasaPoint").value(hasItem(DEFAULT_TSUBASA_POINT)))
            .andExpect(jsonPath("$.[*].contentType").value(hasItem(DEFAULT_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].contentId").value(hasItem(DEFAULT_CONTENT_ID)))
            .andExpect(jsonPath("$.[*].contentAmount").value(hasItem(DEFAULT_CONTENT_AMOUNT)));

        // Check, that the count call also returns 1
        restMMarathonQuestTsubasaPointRewardMockMvc.perform(get("/api/m-marathon-quest-tsubasa-point-rewards/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultMMarathonQuestTsubasaPointRewardShouldNotBeFound(String filter) throws Exception {
        restMMarathonQuestTsubasaPointRewardMockMvc.perform(get("/api/m-marathon-quest-tsubasa-point-rewards?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restMMarathonQuestTsubasaPointRewardMockMvc.perform(get("/api/m-marathon-quest-tsubasa-point-rewards/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("0"));
    }


    @Test
    @Transactional
    public void getNonExistingMMarathonQuestTsubasaPointReward() throws Exception {
        // Get the mMarathonQuestTsubasaPointReward
        restMMarathonQuestTsubasaPointRewardMockMvc.perform(get("/api/m-marathon-quest-tsubasa-point-rewards/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateMMarathonQuestTsubasaPointReward() throws Exception {
        // Initialize the database
        mMarathonQuestTsubasaPointRewardRepository.saveAndFlush(mMarathonQuestTsubasaPointReward);

        int databaseSizeBeforeUpdate = mMarathonQuestTsubasaPointRewardRepository.findAll().size();

        // Update the mMarathonQuestTsubasaPointReward
        MMarathonQuestTsubasaPointReward updatedMMarathonQuestTsubasaPointReward = mMarathonQuestTsubasaPointRewardRepository.findById(mMarathonQuestTsubasaPointReward.getId()).get();
        // Disconnect from session so that the updates on updatedMMarathonQuestTsubasaPointReward are not directly saved in db
        em.detach(updatedMMarathonQuestTsubasaPointReward);
        updatedMMarathonQuestTsubasaPointReward
            .stageId(UPDATED_STAGE_ID)
            .tsubasaPoint(UPDATED_TSUBASA_POINT)
            .contentType(UPDATED_CONTENT_TYPE)
            .contentId(UPDATED_CONTENT_ID)
            .contentAmount(UPDATED_CONTENT_AMOUNT);
        MMarathonQuestTsubasaPointRewardDTO mMarathonQuestTsubasaPointRewardDTO = mMarathonQuestTsubasaPointRewardMapper.toDto(updatedMMarathonQuestTsubasaPointReward);

        restMMarathonQuestTsubasaPointRewardMockMvc.perform(put("/api/m-marathon-quest-tsubasa-point-rewards")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mMarathonQuestTsubasaPointRewardDTO)))
            .andExpect(status().isOk());

        // Validate the MMarathonQuestTsubasaPointReward in the database
        List<MMarathonQuestTsubasaPointReward> mMarathonQuestTsubasaPointRewardList = mMarathonQuestTsubasaPointRewardRepository.findAll();
        assertThat(mMarathonQuestTsubasaPointRewardList).hasSize(databaseSizeBeforeUpdate);
        MMarathonQuestTsubasaPointReward testMMarathonQuestTsubasaPointReward = mMarathonQuestTsubasaPointRewardList.get(mMarathonQuestTsubasaPointRewardList.size() - 1);
        assertThat(testMMarathonQuestTsubasaPointReward.getStageId()).isEqualTo(UPDATED_STAGE_ID);
        assertThat(testMMarathonQuestTsubasaPointReward.getTsubasaPoint()).isEqualTo(UPDATED_TSUBASA_POINT);
        assertThat(testMMarathonQuestTsubasaPointReward.getContentType()).isEqualTo(UPDATED_CONTENT_TYPE);
        assertThat(testMMarathonQuestTsubasaPointReward.getContentId()).isEqualTo(UPDATED_CONTENT_ID);
        assertThat(testMMarathonQuestTsubasaPointReward.getContentAmount()).isEqualTo(UPDATED_CONTENT_AMOUNT);
    }

    @Test
    @Transactional
    public void updateNonExistingMMarathonQuestTsubasaPointReward() throws Exception {
        int databaseSizeBeforeUpdate = mMarathonQuestTsubasaPointRewardRepository.findAll().size();

        // Create the MMarathonQuestTsubasaPointReward
        MMarathonQuestTsubasaPointRewardDTO mMarathonQuestTsubasaPointRewardDTO = mMarathonQuestTsubasaPointRewardMapper.toDto(mMarathonQuestTsubasaPointReward);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restMMarathonQuestTsubasaPointRewardMockMvc.perform(put("/api/m-marathon-quest-tsubasa-point-rewards")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mMarathonQuestTsubasaPointRewardDTO)))
            .andExpect(status().isBadRequest());

        // Validate the MMarathonQuestTsubasaPointReward in the database
        List<MMarathonQuestTsubasaPointReward> mMarathonQuestTsubasaPointRewardList = mMarathonQuestTsubasaPointRewardRepository.findAll();
        assertThat(mMarathonQuestTsubasaPointRewardList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteMMarathonQuestTsubasaPointReward() throws Exception {
        // Initialize the database
        mMarathonQuestTsubasaPointRewardRepository.saveAndFlush(mMarathonQuestTsubasaPointReward);

        int databaseSizeBeforeDelete = mMarathonQuestTsubasaPointRewardRepository.findAll().size();

        // Delete the mMarathonQuestTsubasaPointReward
        restMMarathonQuestTsubasaPointRewardMockMvc.perform(delete("/api/m-marathon-quest-tsubasa-point-rewards/{id}", mMarathonQuestTsubasaPointReward.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database is empty
        List<MMarathonQuestTsubasaPointReward> mMarathonQuestTsubasaPointRewardList = mMarathonQuestTsubasaPointRewardRepository.findAll();
        assertThat(mMarathonQuestTsubasaPointRewardList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(MMarathonQuestTsubasaPointReward.class);
        MMarathonQuestTsubasaPointReward mMarathonQuestTsubasaPointReward1 = new MMarathonQuestTsubasaPointReward();
        mMarathonQuestTsubasaPointReward1.setId(1L);
        MMarathonQuestTsubasaPointReward mMarathonQuestTsubasaPointReward2 = new MMarathonQuestTsubasaPointReward();
        mMarathonQuestTsubasaPointReward2.setId(mMarathonQuestTsubasaPointReward1.getId());
        assertThat(mMarathonQuestTsubasaPointReward1).isEqualTo(mMarathonQuestTsubasaPointReward2);
        mMarathonQuestTsubasaPointReward2.setId(2L);
        assertThat(mMarathonQuestTsubasaPointReward1).isNotEqualTo(mMarathonQuestTsubasaPointReward2);
        mMarathonQuestTsubasaPointReward1.setId(null);
        assertThat(mMarathonQuestTsubasaPointReward1).isNotEqualTo(mMarathonQuestTsubasaPointReward2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(MMarathonQuestTsubasaPointRewardDTO.class);
        MMarathonQuestTsubasaPointRewardDTO mMarathonQuestTsubasaPointRewardDTO1 = new MMarathonQuestTsubasaPointRewardDTO();
        mMarathonQuestTsubasaPointRewardDTO1.setId(1L);
        MMarathonQuestTsubasaPointRewardDTO mMarathonQuestTsubasaPointRewardDTO2 = new MMarathonQuestTsubasaPointRewardDTO();
        assertThat(mMarathonQuestTsubasaPointRewardDTO1).isNotEqualTo(mMarathonQuestTsubasaPointRewardDTO2);
        mMarathonQuestTsubasaPointRewardDTO2.setId(mMarathonQuestTsubasaPointRewardDTO1.getId());
        assertThat(mMarathonQuestTsubasaPointRewardDTO1).isEqualTo(mMarathonQuestTsubasaPointRewardDTO2);
        mMarathonQuestTsubasaPointRewardDTO2.setId(2L);
        assertThat(mMarathonQuestTsubasaPointRewardDTO1).isNotEqualTo(mMarathonQuestTsubasaPointRewardDTO2);
        mMarathonQuestTsubasaPointRewardDTO1.setId(null);
        assertThat(mMarathonQuestTsubasaPointRewardDTO1).isNotEqualTo(mMarathonQuestTsubasaPointRewardDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(mMarathonQuestTsubasaPointRewardMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(mMarathonQuestTsubasaPointRewardMapper.fromId(null)).isNull();
    }
}
