package io.shm.tsubasa.web.rest;

import io.shm.tsubasa.TsubasaApp;
import io.shm.tsubasa.domain.MQuestTsubasaPointReward;
import io.shm.tsubasa.repository.MQuestTsubasaPointRewardRepository;
import io.shm.tsubasa.service.MQuestTsubasaPointRewardService;
import io.shm.tsubasa.service.dto.MQuestTsubasaPointRewardDTO;
import io.shm.tsubasa.service.mapper.MQuestTsubasaPointRewardMapper;
import io.shm.tsubasa.web.rest.errors.ExceptionTranslator;
import io.shm.tsubasa.service.dto.MQuestTsubasaPointRewardCriteria;
import io.shm.tsubasa.service.MQuestTsubasaPointRewardQueryService;

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
 * Integration tests for the {@Link MQuestTsubasaPointRewardResource} REST controller.
 */
@SpringBootTest(classes = TsubasaApp.class)
public class MQuestTsubasaPointRewardResourceIT {

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
    private MQuestTsubasaPointRewardRepository mQuestTsubasaPointRewardRepository;

    @Autowired
    private MQuestTsubasaPointRewardMapper mQuestTsubasaPointRewardMapper;

    @Autowired
    private MQuestTsubasaPointRewardService mQuestTsubasaPointRewardService;

    @Autowired
    private MQuestTsubasaPointRewardQueryService mQuestTsubasaPointRewardQueryService;

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

    private MockMvc restMQuestTsubasaPointRewardMockMvc;

    private MQuestTsubasaPointReward mQuestTsubasaPointReward;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final MQuestTsubasaPointRewardResource mQuestTsubasaPointRewardResource = new MQuestTsubasaPointRewardResource(mQuestTsubasaPointRewardService, mQuestTsubasaPointRewardQueryService);
        this.restMQuestTsubasaPointRewardMockMvc = MockMvcBuilders.standaloneSetup(mQuestTsubasaPointRewardResource)
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
    public static MQuestTsubasaPointReward createEntity(EntityManager em) {
        MQuestTsubasaPointReward mQuestTsubasaPointReward = new MQuestTsubasaPointReward()
            .stageId(DEFAULT_STAGE_ID)
            .tsubasaPoint(DEFAULT_TSUBASA_POINT)
            .contentType(DEFAULT_CONTENT_TYPE)
            .contentId(DEFAULT_CONTENT_ID)
            .contentAmount(DEFAULT_CONTENT_AMOUNT);
        return mQuestTsubasaPointReward;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static MQuestTsubasaPointReward createUpdatedEntity(EntityManager em) {
        MQuestTsubasaPointReward mQuestTsubasaPointReward = new MQuestTsubasaPointReward()
            .stageId(UPDATED_STAGE_ID)
            .tsubasaPoint(UPDATED_TSUBASA_POINT)
            .contentType(UPDATED_CONTENT_TYPE)
            .contentId(UPDATED_CONTENT_ID)
            .contentAmount(UPDATED_CONTENT_AMOUNT);
        return mQuestTsubasaPointReward;
    }

    @BeforeEach
    public void initTest() {
        mQuestTsubasaPointReward = createEntity(em);
    }

    @Test
    @Transactional
    public void createMQuestTsubasaPointReward() throws Exception {
        int databaseSizeBeforeCreate = mQuestTsubasaPointRewardRepository.findAll().size();

        // Create the MQuestTsubasaPointReward
        MQuestTsubasaPointRewardDTO mQuestTsubasaPointRewardDTO = mQuestTsubasaPointRewardMapper.toDto(mQuestTsubasaPointReward);
        restMQuestTsubasaPointRewardMockMvc.perform(post("/api/m-quest-tsubasa-point-rewards")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mQuestTsubasaPointRewardDTO)))
            .andExpect(status().isCreated());

        // Validate the MQuestTsubasaPointReward in the database
        List<MQuestTsubasaPointReward> mQuestTsubasaPointRewardList = mQuestTsubasaPointRewardRepository.findAll();
        assertThat(mQuestTsubasaPointRewardList).hasSize(databaseSizeBeforeCreate + 1);
        MQuestTsubasaPointReward testMQuestTsubasaPointReward = mQuestTsubasaPointRewardList.get(mQuestTsubasaPointRewardList.size() - 1);
        assertThat(testMQuestTsubasaPointReward.getStageId()).isEqualTo(DEFAULT_STAGE_ID);
        assertThat(testMQuestTsubasaPointReward.getTsubasaPoint()).isEqualTo(DEFAULT_TSUBASA_POINT);
        assertThat(testMQuestTsubasaPointReward.getContentType()).isEqualTo(DEFAULT_CONTENT_TYPE);
        assertThat(testMQuestTsubasaPointReward.getContentId()).isEqualTo(DEFAULT_CONTENT_ID);
        assertThat(testMQuestTsubasaPointReward.getContentAmount()).isEqualTo(DEFAULT_CONTENT_AMOUNT);
    }

    @Test
    @Transactional
    public void createMQuestTsubasaPointRewardWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = mQuestTsubasaPointRewardRepository.findAll().size();

        // Create the MQuestTsubasaPointReward with an existing ID
        mQuestTsubasaPointReward.setId(1L);
        MQuestTsubasaPointRewardDTO mQuestTsubasaPointRewardDTO = mQuestTsubasaPointRewardMapper.toDto(mQuestTsubasaPointReward);

        // An entity with an existing ID cannot be created, so this API call must fail
        restMQuestTsubasaPointRewardMockMvc.perform(post("/api/m-quest-tsubasa-point-rewards")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mQuestTsubasaPointRewardDTO)))
            .andExpect(status().isBadRequest());

        // Validate the MQuestTsubasaPointReward in the database
        List<MQuestTsubasaPointReward> mQuestTsubasaPointRewardList = mQuestTsubasaPointRewardRepository.findAll();
        assertThat(mQuestTsubasaPointRewardList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkStageIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = mQuestTsubasaPointRewardRepository.findAll().size();
        // set the field null
        mQuestTsubasaPointReward.setStageId(null);

        // Create the MQuestTsubasaPointReward, which fails.
        MQuestTsubasaPointRewardDTO mQuestTsubasaPointRewardDTO = mQuestTsubasaPointRewardMapper.toDto(mQuestTsubasaPointReward);

        restMQuestTsubasaPointRewardMockMvc.perform(post("/api/m-quest-tsubasa-point-rewards")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mQuestTsubasaPointRewardDTO)))
            .andExpect(status().isBadRequest());

        List<MQuestTsubasaPointReward> mQuestTsubasaPointRewardList = mQuestTsubasaPointRewardRepository.findAll();
        assertThat(mQuestTsubasaPointRewardList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkTsubasaPointIsRequired() throws Exception {
        int databaseSizeBeforeTest = mQuestTsubasaPointRewardRepository.findAll().size();
        // set the field null
        mQuestTsubasaPointReward.setTsubasaPoint(null);

        // Create the MQuestTsubasaPointReward, which fails.
        MQuestTsubasaPointRewardDTO mQuestTsubasaPointRewardDTO = mQuestTsubasaPointRewardMapper.toDto(mQuestTsubasaPointReward);

        restMQuestTsubasaPointRewardMockMvc.perform(post("/api/m-quest-tsubasa-point-rewards")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mQuestTsubasaPointRewardDTO)))
            .andExpect(status().isBadRequest());

        List<MQuestTsubasaPointReward> mQuestTsubasaPointRewardList = mQuestTsubasaPointRewardRepository.findAll();
        assertThat(mQuestTsubasaPointRewardList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkContentTypeIsRequired() throws Exception {
        int databaseSizeBeforeTest = mQuestTsubasaPointRewardRepository.findAll().size();
        // set the field null
        mQuestTsubasaPointReward.setContentType(null);

        // Create the MQuestTsubasaPointReward, which fails.
        MQuestTsubasaPointRewardDTO mQuestTsubasaPointRewardDTO = mQuestTsubasaPointRewardMapper.toDto(mQuestTsubasaPointReward);

        restMQuestTsubasaPointRewardMockMvc.perform(post("/api/m-quest-tsubasa-point-rewards")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mQuestTsubasaPointRewardDTO)))
            .andExpect(status().isBadRequest());

        List<MQuestTsubasaPointReward> mQuestTsubasaPointRewardList = mQuestTsubasaPointRewardRepository.findAll();
        assertThat(mQuestTsubasaPointRewardList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkContentAmountIsRequired() throws Exception {
        int databaseSizeBeforeTest = mQuestTsubasaPointRewardRepository.findAll().size();
        // set the field null
        mQuestTsubasaPointReward.setContentAmount(null);

        // Create the MQuestTsubasaPointReward, which fails.
        MQuestTsubasaPointRewardDTO mQuestTsubasaPointRewardDTO = mQuestTsubasaPointRewardMapper.toDto(mQuestTsubasaPointReward);

        restMQuestTsubasaPointRewardMockMvc.perform(post("/api/m-quest-tsubasa-point-rewards")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mQuestTsubasaPointRewardDTO)))
            .andExpect(status().isBadRequest());

        List<MQuestTsubasaPointReward> mQuestTsubasaPointRewardList = mQuestTsubasaPointRewardRepository.findAll();
        assertThat(mQuestTsubasaPointRewardList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllMQuestTsubasaPointRewards() throws Exception {
        // Initialize the database
        mQuestTsubasaPointRewardRepository.saveAndFlush(mQuestTsubasaPointReward);

        // Get all the mQuestTsubasaPointRewardList
        restMQuestTsubasaPointRewardMockMvc.perform(get("/api/m-quest-tsubasa-point-rewards?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(mQuestTsubasaPointReward.getId().intValue())))
            .andExpect(jsonPath("$.[*].stageId").value(hasItem(DEFAULT_STAGE_ID)))
            .andExpect(jsonPath("$.[*].tsubasaPoint").value(hasItem(DEFAULT_TSUBASA_POINT)))
            .andExpect(jsonPath("$.[*].contentType").value(hasItem(DEFAULT_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].contentId").value(hasItem(DEFAULT_CONTENT_ID)))
            .andExpect(jsonPath("$.[*].contentAmount").value(hasItem(DEFAULT_CONTENT_AMOUNT)));
    }
    
    @Test
    @Transactional
    public void getMQuestTsubasaPointReward() throws Exception {
        // Initialize the database
        mQuestTsubasaPointRewardRepository.saveAndFlush(mQuestTsubasaPointReward);

        // Get the mQuestTsubasaPointReward
        restMQuestTsubasaPointRewardMockMvc.perform(get("/api/m-quest-tsubasa-point-rewards/{id}", mQuestTsubasaPointReward.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(mQuestTsubasaPointReward.getId().intValue()))
            .andExpect(jsonPath("$.stageId").value(DEFAULT_STAGE_ID))
            .andExpect(jsonPath("$.tsubasaPoint").value(DEFAULT_TSUBASA_POINT))
            .andExpect(jsonPath("$.contentType").value(DEFAULT_CONTENT_TYPE))
            .andExpect(jsonPath("$.contentId").value(DEFAULT_CONTENT_ID))
            .andExpect(jsonPath("$.contentAmount").value(DEFAULT_CONTENT_AMOUNT));
    }

    @Test
    @Transactional
    public void getAllMQuestTsubasaPointRewardsByStageIdIsEqualToSomething() throws Exception {
        // Initialize the database
        mQuestTsubasaPointRewardRepository.saveAndFlush(mQuestTsubasaPointReward);

        // Get all the mQuestTsubasaPointRewardList where stageId equals to DEFAULT_STAGE_ID
        defaultMQuestTsubasaPointRewardShouldBeFound("stageId.equals=" + DEFAULT_STAGE_ID);

        // Get all the mQuestTsubasaPointRewardList where stageId equals to UPDATED_STAGE_ID
        defaultMQuestTsubasaPointRewardShouldNotBeFound("stageId.equals=" + UPDATED_STAGE_ID);
    }

    @Test
    @Transactional
    public void getAllMQuestTsubasaPointRewardsByStageIdIsInShouldWork() throws Exception {
        // Initialize the database
        mQuestTsubasaPointRewardRepository.saveAndFlush(mQuestTsubasaPointReward);

        // Get all the mQuestTsubasaPointRewardList where stageId in DEFAULT_STAGE_ID or UPDATED_STAGE_ID
        defaultMQuestTsubasaPointRewardShouldBeFound("stageId.in=" + DEFAULT_STAGE_ID + "," + UPDATED_STAGE_ID);

        // Get all the mQuestTsubasaPointRewardList where stageId equals to UPDATED_STAGE_ID
        defaultMQuestTsubasaPointRewardShouldNotBeFound("stageId.in=" + UPDATED_STAGE_ID);
    }

    @Test
    @Transactional
    public void getAllMQuestTsubasaPointRewardsByStageIdIsNullOrNotNull() throws Exception {
        // Initialize the database
        mQuestTsubasaPointRewardRepository.saveAndFlush(mQuestTsubasaPointReward);

        // Get all the mQuestTsubasaPointRewardList where stageId is not null
        defaultMQuestTsubasaPointRewardShouldBeFound("stageId.specified=true");

        // Get all the mQuestTsubasaPointRewardList where stageId is null
        defaultMQuestTsubasaPointRewardShouldNotBeFound("stageId.specified=false");
    }

    @Test
    @Transactional
    public void getAllMQuestTsubasaPointRewardsByStageIdIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mQuestTsubasaPointRewardRepository.saveAndFlush(mQuestTsubasaPointReward);

        // Get all the mQuestTsubasaPointRewardList where stageId greater than or equals to DEFAULT_STAGE_ID
        defaultMQuestTsubasaPointRewardShouldBeFound("stageId.greaterOrEqualThan=" + DEFAULT_STAGE_ID);

        // Get all the mQuestTsubasaPointRewardList where stageId greater than or equals to UPDATED_STAGE_ID
        defaultMQuestTsubasaPointRewardShouldNotBeFound("stageId.greaterOrEqualThan=" + UPDATED_STAGE_ID);
    }

    @Test
    @Transactional
    public void getAllMQuestTsubasaPointRewardsByStageIdIsLessThanSomething() throws Exception {
        // Initialize the database
        mQuestTsubasaPointRewardRepository.saveAndFlush(mQuestTsubasaPointReward);

        // Get all the mQuestTsubasaPointRewardList where stageId less than or equals to DEFAULT_STAGE_ID
        defaultMQuestTsubasaPointRewardShouldNotBeFound("stageId.lessThan=" + DEFAULT_STAGE_ID);

        // Get all the mQuestTsubasaPointRewardList where stageId less than or equals to UPDATED_STAGE_ID
        defaultMQuestTsubasaPointRewardShouldBeFound("stageId.lessThan=" + UPDATED_STAGE_ID);
    }


    @Test
    @Transactional
    public void getAllMQuestTsubasaPointRewardsByTsubasaPointIsEqualToSomething() throws Exception {
        // Initialize the database
        mQuestTsubasaPointRewardRepository.saveAndFlush(mQuestTsubasaPointReward);

        // Get all the mQuestTsubasaPointRewardList where tsubasaPoint equals to DEFAULT_TSUBASA_POINT
        defaultMQuestTsubasaPointRewardShouldBeFound("tsubasaPoint.equals=" + DEFAULT_TSUBASA_POINT);

        // Get all the mQuestTsubasaPointRewardList where tsubasaPoint equals to UPDATED_TSUBASA_POINT
        defaultMQuestTsubasaPointRewardShouldNotBeFound("tsubasaPoint.equals=" + UPDATED_TSUBASA_POINT);
    }

    @Test
    @Transactional
    public void getAllMQuestTsubasaPointRewardsByTsubasaPointIsInShouldWork() throws Exception {
        // Initialize the database
        mQuestTsubasaPointRewardRepository.saveAndFlush(mQuestTsubasaPointReward);

        // Get all the mQuestTsubasaPointRewardList where tsubasaPoint in DEFAULT_TSUBASA_POINT or UPDATED_TSUBASA_POINT
        defaultMQuestTsubasaPointRewardShouldBeFound("tsubasaPoint.in=" + DEFAULT_TSUBASA_POINT + "," + UPDATED_TSUBASA_POINT);

        // Get all the mQuestTsubasaPointRewardList where tsubasaPoint equals to UPDATED_TSUBASA_POINT
        defaultMQuestTsubasaPointRewardShouldNotBeFound("tsubasaPoint.in=" + UPDATED_TSUBASA_POINT);
    }

    @Test
    @Transactional
    public void getAllMQuestTsubasaPointRewardsByTsubasaPointIsNullOrNotNull() throws Exception {
        // Initialize the database
        mQuestTsubasaPointRewardRepository.saveAndFlush(mQuestTsubasaPointReward);

        // Get all the mQuestTsubasaPointRewardList where tsubasaPoint is not null
        defaultMQuestTsubasaPointRewardShouldBeFound("tsubasaPoint.specified=true");

        // Get all the mQuestTsubasaPointRewardList where tsubasaPoint is null
        defaultMQuestTsubasaPointRewardShouldNotBeFound("tsubasaPoint.specified=false");
    }

    @Test
    @Transactional
    public void getAllMQuestTsubasaPointRewardsByTsubasaPointIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mQuestTsubasaPointRewardRepository.saveAndFlush(mQuestTsubasaPointReward);

        // Get all the mQuestTsubasaPointRewardList where tsubasaPoint greater than or equals to DEFAULT_TSUBASA_POINT
        defaultMQuestTsubasaPointRewardShouldBeFound("tsubasaPoint.greaterOrEqualThan=" + DEFAULT_TSUBASA_POINT);

        // Get all the mQuestTsubasaPointRewardList where tsubasaPoint greater than or equals to UPDATED_TSUBASA_POINT
        defaultMQuestTsubasaPointRewardShouldNotBeFound("tsubasaPoint.greaterOrEqualThan=" + UPDATED_TSUBASA_POINT);
    }

    @Test
    @Transactional
    public void getAllMQuestTsubasaPointRewardsByTsubasaPointIsLessThanSomething() throws Exception {
        // Initialize the database
        mQuestTsubasaPointRewardRepository.saveAndFlush(mQuestTsubasaPointReward);

        // Get all the mQuestTsubasaPointRewardList where tsubasaPoint less than or equals to DEFAULT_TSUBASA_POINT
        defaultMQuestTsubasaPointRewardShouldNotBeFound("tsubasaPoint.lessThan=" + DEFAULT_TSUBASA_POINT);

        // Get all the mQuestTsubasaPointRewardList where tsubasaPoint less than or equals to UPDATED_TSUBASA_POINT
        defaultMQuestTsubasaPointRewardShouldBeFound("tsubasaPoint.lessThan=" + UPDATED_TSUBASA_POINT);
    }


    @Test
    @Transactional
    public void getAllMQuestTsubasaPointRewardsByContentTypeIsEqualToSomething() throws Exception {
        // Initialize the database
        mQuestTsubasaPointRewardRepository.saveAndFlush(mQuestTsubasaPointReward);

        // Get all the mQuestTsubasaPointRewardList where contentType equals to DEFAULT_CONTENT_TYPE
        defaultMQuestTsubasaPointRewardShouldBeFound("contentType.equals=" + DEFAULT_CONTENT_TYPE);

        // Get all the mQuestTsubasaPointRewardList where contentType equals to UPDATED_CONTENT_TYPE
        defaultMQuestTsubasaPointRewardShouldNotBeFound("contentType.equals=" + UPDATED_CONTENT_TYPE);
    }

    @Test
    @Transactional
    public void getAllMQuestTsubasaPointRewardsByContentTypeIsInShouldWork() throws Exception {
        // Initialize the database
        mQuestTsubasaPointRewardRepository.saveAndFlush(mQuestTsubasaPointReward);

        // Get all the mQuestTsubasaPointRewardList where contentType in DEFAULT_CONTENT_TYPE or UPDATED_CONTENT_TYPE
        defaultMQuestTsubasaPointRewardShouldBeFound("contentType.in=" + DEFAULT_CONTENT_TYPE + "," + UPDATED_CONTENT_TYPE);

        // Get all the mQuestTsubasaPointRewardList where contentType equals to UPDATED_CONTENT_TYPE
        defaultMQuestTsubasaPointRewardShouldNotBeFound("contentType.in=" + UPDATED_CONTENT_TYPE);
    }

    @Test
    @Transactional
    public void getAllMQuestTsubasaPointRewardsByContentTypeIsNullOrNotNull() throws Exception {
        // Initialize the database
        mQuestTsubasaPointRewardRepository.saveAndFlush(mQuestTsubasaPointReward);

        // Get all the mQuestTsubasaPointRewardList where contentType is not null
        defaultMQuestTsubasaPointRewardShouldBeFound("contentType.specified=true");

        // Get all the mQuestTsubasaPointRewardList where contentType is null
        defaultMQuestTsubasaPointRewardShouldNotBeFound("contentType.specified=false");
    }

    @Test
    @Transactional
    public void getAllMQuestTsubasaPointRewardsByContentTypeIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mQuestTsubasaPointRewardRepository.saveAndFlush(mQuestTsubasaPointReward);

        // Get all the mQuestTsubasaPointRewardList where contentType greater than or equals to DEFAULT_CONTENT_TYPE
        defaultMQuestTsubasaPointRewardShouldBeFound("contentType.greaterOrEqualThan=" + DEFAULT_CONTENT_TYPE);

        // Get all the mQuestTsubasaPointRewardList where contentType greater than or equals to UPDATED_CONTENT_TYPE
        defaultMQuestTsubasaPointRewardShouldNotBeFound("contentType.greaterOrEqualThan=" + UPDATED_CONTENT_TYPE);
    }

    @Test
    @Transactional
    public void getAllMQuestTsubasaPointRewardsByContentTypeIsLessThanSomething() throws Exception {
        // Initialize the database
        mQuestTsubasaPointRewardRepository.saveAndFlush(mQuestTsubasaPointReward);

        // Get all the mQuestTsubasaPointRewardList where contentType less than or equals to DEFAULT_CONTENT_TYPE
        defaultMQuestTsubasaPointRewardShouldNotBeFound("contentType.lessThan=" + DEFAULT_CONTENT_TYPE);

        // Get all the mQuestTsubasaPointRewardList where contentType less than or equals to UPDATED_CONTENT_TYPE
        defaultMQuestTsubasaPointRewardShouldBeFound("contentType.lessThan=" + UPDATED_CONTENT_TYPE);
    }


    @Test
    @Transactional
    public void getAllMQuestTsubasaPointRewardsByContentIdIsEqualToSomething() throws Exception {
        // Initialize the database
        mQuestTsubasaPointRewardRepository.saveAndFlush(mQuestTsubasaPointReward);

        // Get all the mQuestTsubasaPointRewardList where contentId equals to DEFAULT_CONTENT_ID
        defaultMQuestTsubasaPointRewardShouldBeFound("contentId.equals=" + DEFAULT_CONTENT_ID);

        // Get all the mQuestTsubasaPointRewardList where contentId equals to UPDATED_CONTENT_ID
        defaultMQuestTsubasaPointRewardShouldNotBeFound("contentId.equals=" + UPDATED_CONTENT_ID);
    }

    @Test
    @Transactional
    public void getAllMQuestTsubasaPointRewardsByContentIdIsInShouldWork() throws Exception {
        // Initialize the database
        mQuestTsubasaPointRewardRepository.saveAndFlush(mQuestTsubasaPointReward);

        // Get all the mQuestTsubasaPointRewardList where contentId in DEFAULT_CONTENT_ID or UPDATED_CONTENT_ID
        defaultMQuestTsubasaPointRewardShouldBeFound("contentId.in=" + DEFAULT_CONTENT_ID + "," + UPDATED_CONTENT_ID);

        // Get all the mQuestTsubasaPointRewardList where contentId equals to UPDATED_CONTENT_ID
        defaultMQuestTsubasaPointRewardShouldNotBeFound("contentId.in=" + UPDATED_CONTENT_ID);
    }

    @Test
    @Transactional
    public void getAllMQuestTsubasaPointRewardsByContentIdIsNullOrNotNull() throws Exception {
        // Initialize the database
        mQuestTsubasaPointRewardRepository.saveAndFlush(mQuestTsubasaPointReward);

        // Get all the mQuestTsubasaPointRewardList where contentId is not null
        defaultMQuestTsubasaPointRewardShouldBeFound("contentId.specified=true");

        // Get all the mQuestTsubasaPointRewardList where contentId is null
        defaultMQuestTsubasaPointRewardShouldNotBeFound("contentId.specified=false");
    }

    @Test
    @Transactional
    public void getAllMQuestTsubasaPointRewardsByContentIdIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mQuestTsubasaPointRewardRepository.saveAndFlush(mQuestTsubasaPointReward);

        // Get all the mQuestTsubasaPointRewardList where contentId greater than or equals to DEFAULT_CONTENT_ID
        defaultMQuestTsubasaPointRewardShouldBeFound("contentId.greaterOrEqualThan=" + DEFAULT_CONTENT_ID);

        // Get all the mQuestTsubasaPointRewardList where contentId greater than or equals to UPDATED_CONTENT_ID
        defaultMQuestTsubasaPointRewardShouldNotBeFound("contentId.greaterOrEqualThan=" + UPDATED_CONTENT_ID);
    }

    @Test
    @Transactional
    public void getAllMQuestTsubasaPointRewardsByContentIdIsLessThanSomething() throws Exception {
        // Initialize the database
        mQuestTsubasaPointRewardRepository.saveAndFlush(mQuestTsubasaPointReward);

        // Get all the mQuestTsubasaPointRewardList where contentId less than or equals to DEFAULT_CONTENT_ID
        defaultMQuestTsubasaPointRewardShouldNotBeFound("contentId.lessThan=" + DEFAULT_CONTENT_ID);

        // Get all the mQuestTsubasaPointRewardList where contentId less than or equals to UPDATED_CONTENT_ID
        defaultMQuestTsubasaPointRewardShouldBeFound("contentId.lessThan=" + UPDATED_CONTENT_ID);
    }


    @Test
    @Transactional
    public void getAllMQuestTsubasaPointRewardsByContentAmountIsEqualToSomething() throws Exception {
        // Initialize the database
        mQuestTsubasaPointRewardRepository.saveAndFlush(mQuestTsubasaPointReward);

        // Get all the mQuestTsubasaPointRewardList where contentAmount equals to DEFAULT_CONTENT_AMOUNT
        defaultMQuestTsubasaPointRewardShouldBeFound("contentAmount.equals=" + DEFAULT_CONTENT_AMOUNT);

        // Get all the mQuestTsubasaPointRewardList where contentAmount equals to UPDATED_CONTENT_AMOUNT
        defaultMQuestTsubasaPointRewardShouldNotBeFound("contentAmount.equals=" + UPDATED_CONTENT_AMOUNT);
    }

    @Test
    @Transactional
    public void getAllMQuestTsubasaPointRewardsByContentAmountIsInShouldWork() throws Exception {
        // Initialize the database
        mQuestTsubasaPointRewardRepository.saveAndFlush(mQuestTsubasaPointReward);

        // Get all the mQuestTsubasaPointRewardList where contentAmount in DEFAULT_CONTENT_AMOUNT or UPDATED_CONTENT_AMOUNT
        defaultMQuestTsubasaPointRewardShouldBeFound("contentAmount.in=" + DEFAULT_CONTENT_AMOUNT + "," + UPDATED_CONTENT_AMOUNT);

        // Get all the mQuestTsubasaPointRewardList where contentAmount equals to UPDATED_CONTENT_AMOUNT
        defaultMQuestTsubasaPointRewardShouldNotBeFound("contentAmount.in=" + UPDATED_CONTENT_AMOUNT);
    }

    @Test
    @Transactional
    public void getAllMQuestTsubasaPointRewardsByContentAmountIsNullOrNotNull() throws Exception {
        // Initialize the database
        mQuestTsubasaPointRewardRepository.saveAndFlush(mQuestTsubasaPointReward);

        // Get all the mQuestTsubasaPointRewardList where contentAmount is not null
        defaultMQuestTsubasaPointRewardShouldBeFound("contentAmount.specified=true");

        // Get all the mQuestTsubasaPointRewardList where contentAmount is null
        defaultMQuestTsubasaPointRewardShouldNotBeFound("contentAmount.specified=false");
    }

    @Test
    @Transactional
    public void getAllMQuestTsubasaPointRewardsByContentAmountIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mQuestTsubasaPointRewardRepository.saveAndFlush(mQuestTsubasaPointReward);

        // Get all the mQuestTsubasaPointRewardList where contentAmount greater than or equals to DEFAULT_CONTENT_AMOUNT
        defaultMQuestTsubasaPointRewardShouldBeFound("contentAmount.greaterOrEqualThan=" + DEFAULT_CONTENT_AMOUNT);

        // Get all the mQuestTsubasaPointRewardList where contentAmount greater than or equals to UPDATED_CONTENT_AMOUNT
        defaultMQuestTsubasaPointRewardShouldNotBeFound("contentAmount.greaterOrEqualThan=" + UPDATED_CONTENT_AMOUNT);
    }

    @Test
    @Transactional
    public void getAllMQuestTsubasaPointRewardsByContentAmountIsLessThanSomething() throws Exception {
        // Initialize the database
        mQuestTsubasaPointRewardRepository.saveAndFlush(mQuestTsubasaPointReward);

        // Get all the mQuestTsubasaPointRewardList where contentAmount less than or equals to DEFAULT_CONTENT_AMOUNT
        defaultMQuestTsubasaPointRewardShouldNotBeFound("contentAmount.lessThan=" + DEFAULT_CONTENT_AMOUNT);

        // Get all the mQuestTsubasaPointRewardList where contentAmount less than or equals to UPDATED_CONTENT_AMOUNT
        defaultMQuestTsubasaPointRewardShouldBeFound("contentAmount.lessThan=" + UPDATED_CONTENT_AMOUNT);
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultMQuestTsubasaPointRewardShouldBeFound(String filter) throws Exception {
        restMQuestTsubasaPointRewardMockMvc.perform(get("/api/m-quest-tsubasa-point-rewards?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(mQuestTsubasaPointReward.getId().intValue())))
            .andExpect(jsonPath("$.[*].stageId").value(hasItem(DEFAULT_STAGE_ID)))
            .andExpect(jsonPath("$.[*].tsubasaPoint").value(hasItem(DEFAULT_TSUBASA_POINT)))
            .andExpect(jsonPath("$.[*].contentType").value(hasItem(DEFAULT_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].contentId").value(hasItem(DEFAULT_CONTENT_ID)))
            .andExpect(jsonPath("$.[*].contentAmount").value(hasItem(DEFAULT_CONTENT_AMOUNT)));

        // Check, that the count call also returns 1
        restMQuestTsubasaPointRewardMockMvc.perform(get("/api/m-quest-tsubasa-point-rewards/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultMQuestTsubasaPointRewardShouldNotBeFound(String filter) throws Exception {
        restMQuestTsubasaPointRewardMockMvc.perform(get("/api/m-quest-tsubasa-point-rewards?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restMQuestTsubasaPointRewardMockMvc.perform(get("/api/m-quest-tsubasa-point-rewards/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("0"));
    }


    @Test
    @Transactional
    public void getNonExistingMQuestTsubasaPointReward() throws Exception {
        // Get the mQuestTsubasaPointReward
        restMQuestTsubasaPointRewardMockMvc.perform(get("/api/m-quest-tsubasa-point-rewards/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateMQuestTsubasaPointReward() throws Exception {
        // Initialize the database
        mQuestTsubasaPointRewardRepository.saveAndFlush(mQuestTsubasaPointReward);

        int databaseSizeBeforeUpdate = mQuestTsubasaPointRewardRepository.findAll().size();

        // Update the mQuestTsubasaPointReward
        MQuestTsubasaPointReward updatedMQuestTsubasaPointReward = mQuestTsubasaPointRewardRepository.findById(mQuestTsubasaPointReward.getId()).get();
        // Disconnect from session so that the updates on updatedMQuestTsubasaPointReward are not directly saved in db
        em.detach(updatedMQuestTsubasaPointReward);
        updatedMQuestTsubasaPointReward
            .stageId(UPDATED_STAGE_ID)
            .tsubasaPoint(UPDATED_TSUBASA_POINT)
            .contentType(UPDATED_CONTENT_TYPE)
            .contentId(UPDATED_CONTENT_ID)
            .contentAmount(UPDATED_CONTENT_AMOUNT);
        MQuestTsubasaPointRewardDTO mQuestTsubasaPointRewardDTO = mQuestTsubasaPointRewardMapper.toDto(updatedMQuestTsubasaPointReward);

        restMQuestTsubasaPointRewardMockMvc.perform(put("/api/m-quest-tsubasa-point-rewards")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mQuestTsubasaPointRewardDTO)))
            .andExpect(status().isOk());

        // Validate the MQuestTsubasaPointReward in the database
        List<MQuestTsubasaPointReward> mQuestTsubasaPointRewardList = mQuestTsubasaPointRewardRepository.findAll();
        assertThat(mQuestTsubasaPointRewardList).hasSize(databaseSizeBeforeUpdate);
        MQuestTsubasaPointReward testMQuestTsubasaPointReward = mQuestTsubasaPointRewardList.get(mQuestTsubasaPointRewardList.size() - 1);
        assertThat(testMQuestTsubasaPointReward.getStageId()).isEqualTo(UPDATED_STAGE_ID);
        assertThat(testMQuestTsubasaPointReward.getTsubasaPoint()).isEqualTo(UPDATED_TSUBASA_POINT);
        assertThat(testMQuestTsubasaPointReward.getContentType()).isEqualTo(UPDATED_CONTENT_TYPE);
        assertThat(testMQuestTsubasaPointReward.getContentId()).isEqualTo(UPDATED_CONTENT_ID);
        assertThat(testMQuestTsubasaPointReward.getContentAmount()).isEqualTo(UPDATED_CONTENT_AMOUNT);
    }

    @Test
    @Transactional
    public void updateNonExistingMQuestTsubasaPointReward() throws Exception {
        int databaseSizeBeforeUpdate = mQuestTsubasaPointRewardRepository.findAll().size();

        // Create the MQuestTsubasaPointReward
        MQuestTsubasaPointRewardDTO mQuestTsubasaPointRewardDTO = mQuestTsubasaPointRewardMapper.toDto(mQuestTsubasaPointReward);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restMQuestTsubasaPointRewardMockMvc.perform(put("/api/m-quest-tsubasa-point-rewards")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mQuestTsubasaPointRewardDTO)))
            .andExpect(status().isBadRequest());

        // Validate the MQuestTsubasaPointReward in the database
        List<MQuestTsubasaPointReward> mQuestTsubasaPointRewardList = mQuestTsubasaPointRewardRepository.findAll();
        assertThat(mQuestTsubasaPointRewardList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteMQuestTsubasaPointReward() throws Exception {
        // Initialize the database
        mQuestTsubasaPointRewardRepository.saveAndFlush(mQuestTsubasaPointReward);

        int databaseSizeBeforeDelete = mQuestTsubasaPointRewardRepository.findAll().size();

        // Delete the mQuestTsubasaPointReward
        restMQuestTsubasaPointRewardMockMvc.perform(delete("/api/m-quest-tsubasa-point-rewards/{id}", mQuestTsubasaPointReward.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database is empty
        List<MQuestTsubasaPointReward> mQuestTsubasaPointRewardList = mQuestTsubasaPointRewardRepository.findAll();
        assertThat(mQuestTsubasaPointRewardList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(MQuestTsubasaPointReward.class);
        MQuestTsubasaPointReward mQuestTsubasaPointReward1 = new MQuestTsubasaPointReward();
        mQuestTsubasaPointReward1.setId(1L);
        MQuestTsubasaPointReward mQuestTsubasaPointReward2 = new MQuestTsubasaPointReward();
        mQuestTsubasaPointReward2.setId(mQuestTsubasaPointReward1.getId());
        assertThat(mQuestTsubasaPointReward1).isEqualTo(mQuestTsubasaPointReward2);
        mQuestTsubasaPointReward2.setId(2L);
        assertThat(mQuestTsubasaPointReward1).isNotEqualTo(mQuestTsubasaPointReward2);
        mQuestTsubasaPointReward1.setId(null);
        assertThat(mQuestTsubasaPointReward1).isNotEqualTo(mQuestTsubasaPointReward2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(MQuestTsubasaPointRewardDTO.class);
        MQuestTsubasaPointRewardDTO mQuestTsubasaPointRewardDTO1 = new MQuestTsubasaPointRewardDTO();
        mQuestTsubasaPointRewardDTO1.setId(1L);
        MQuestTsubasaPointRewardDTO mQuestTsubasaPointRewardDTO2 = new MQuestTsubasaPointRewardDTO();
        assertThat(mQuestTsubasaPointRewardDTO1).isNotEqualTo(mQuestTsubasaPointRewardDTO2);
        mQuestTsubasaPointRewardDTO2.setId(mQuestTsubasaPointRewardDTO1.getId());
        assertThat(mQuestTsubasaPointRewardDTO1).isEqualTo(mQuestTsubasaPointRewardDTO2);
        mQuestTsubasaPointRewardDTO2.setId(2L);
        assertThat(mQuestTsubasaPointRewardDTO1).isNotEqualTo(mQuestTsubasaPointRewardDTO2);
        mQuestTsubasaPointRewardDTO1.setId(null);
        assertThat(mQuestTsubasaPointRewardDTO1).isNotEqualTo(mQuestTsubasaPointRewardDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(mQuestTsubasaPointRewardMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(mQuestTsubasaPointRewardMapper.fromId(null)).isNull();
    }
}
